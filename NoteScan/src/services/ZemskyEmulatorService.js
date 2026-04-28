/**
 * Zemsky Emulator OMR Service
 *
 * Talks to the separate Android harness app running in the same emulator.
 * The harness exposes:
 *   GET  /docs
 *   POST /process
 * on http://127.0.0.1:8084
 */
import { File } from 'expo-file-system';
import { MusicXMLParser } from './MusicXMLParser';
import { injectDeterministicNoteIds, normalizeMusicXmlSoftwareTag } from './MusicXmlBranding';

class ZemskyEmulatorServiceClass {
  _serverUrl = 'http://127.0.0.1:8084';

  _candidateBaseUrls() {
    const base = this.getServerUrl().replace(/\/+$/, '');
    const out = [base];

    const fallbacks = [
      'http://127.0.0.1:8084',
      'http://localhost:8084',
      'http://10.0.2.2:8084',
    ];

    for (const f of fallbacks) {
      if (!out.includes(f)) out.push(f);
    }
    return out;
  }

  setServerUrl(url) {
    this._serverUrl = url.replace(/\/+$/, '');
  }

  getServerUrl() {
    return this._serverUrl;
  }

  _getProcessUrl(baseUrl = this.getServerUrl()) {
    return `${baseUrl.replace(/\/+$/, '')}/process`;
  }

  _getDocsUrl(baseUrl = this.getServerUrl()) {
    return `${baseUrl.replace(/\/+$/, '')}/docs`;
  }

  _getRootUrl(baseUrl = this.getServerUrl()) {
    return `${baseUrl.replace(/\/+$/, '')}/`;
  }

  async _fetchWithTimeout(url, options = {}, timeoutMs = 20000) {
    const controller = new AbortController();
    const timer = setTimeout(() => controller.abort(), timeoutMs);
    try {
      return await fetch(url, { ...options, signal: controller.signal });
    } finally {
      clearTimeout(timer);
    }
  }

  async checkHealth() {
    const urls = this._candidateBaseUrls();
    let lastErr = null;
    for (const baseUrl of urls) {
      for (let attempt = 1; attempt <= 2; attempt++) {
        try {
          // Primary health endpoint
          let response = await this._fetchWithTimeout(this._getDocsUrl(baseUrl), { method: 'GET' }, 7000);
          if (!response.ok) {
            // Fallback to root endpoint if /docs is unavailable
            response = await this._fetchWithTimeout(this._getRootUrl(baseUrl), { method: 'GET' }, 7000);
          }

          if (response.ok) {
            if (baseUrl !== this.getServerUrl()) this.setServerUrl(baseUrl);
            return { ok: true, message: `Music eye engine is reachable (${baseUrl})` };
          }
          lastErr = new Error(`HTTP ${response.status} on ${baseUrl}`);
        } catch (e) {
          lastErr = e;
        }

        // Small retry delay, useful when harness app just resumed.
        if (attempt < 2) {
          await new Promise((resolve) => setTimeout(resolve, 300));
        }
      }
    }
    return {
      ok: false,
      message: `Cannot reach Music eye engine: ${lastErr?.message || 'Unknown error'}`,
    };
  }

  async _postToAvailableEndpoint({ imageUri, fileName, mimeType }) {
    const urls = this._candidateBaseUrls();
    let lastErr = null;

    for (const baseUrl of urls) {
      for (let attempt = 1; attempt <= 2; attempt++) {
        try {
          // Rebuild multipart body for each attempt; some RN transports do not like reused FormData.
          const formData = new FormData();
          formData.append('file', {
            uri: imageUri,
            name: fileName,
            type: mimeType,
          });

          const response = await this._fetchWithTimeout(
            this._getProcessUrl(baseUrl),
            {
              method: 'POST',
              body: formData,
              headers: {
                Accept: 'application/json, application/xml, text/xml, */*',
              },
            },
            180000
          );

          if (!response.ok) {
            const body = await response.text().catch(() => '');
            lastErr = new Error(
              `HTTP ${response.status} from ${baseUrl}${body ? `: ${body.substring(0, 300)}` : ''}`
            );
            if (attempt < 2) {
              await new Promise((resolve) => setTimeout(resolve, 350));
            }
            continue;
          }

          if (baseUrl !== this.getServerUrl()) this.setServerUrl(baseUrl);
          return response;
        } catch (e) {
          lastErr = e;
        }

        if (attempt < 2) {
          await new Promise((resolve) => setTimeout(resolve, 350));
        }
      }
    }

    throw lastErr || new Error('No reachable Zemsky endpoint');
  }

  async processImage(imageUri, onProgress) {
    const report = (msg) => {
      console.log(`🎼 Music eye: ${msg}`);
      if (onProgress) onProgress(msg);
    };

    report('Loading image data...');

    const file = new File(imageUri);
    if (!file.exists) {
      throw new Error('Image file not found: ' + imageUri);
    }

    const fileName = imageUri.split('/').pop() || 'sheet_music.jpg';
    const ext = (fileName.split('.').pop() || '').toLowerCase();
    const mimeType =
      ext === 'png' ? 'image/png'
      : (ext === 'jpg' || ext === 'jpeg') ? 'image/jpeg'
      : ext === 'pdf' ? 'application/pdf'
      : (ext === 'heic' || ext === 'heif') ? 'image/heic'
      : ext === 'webp' ? 'image/webp'
      : 'image/jpeg';

    let response;
    let heartbeat = null;
    try {
      // Keep UI watchdog alive while native OMR works on dense pages.
      heartbeat = setInterval(() => {
        report('Processing in Music eye...');
      }, 8000);

      response = await this._postToAvailableEndpoint({
        imageUri,
        fileName,
        mimeType,
      });
    } catch (e) {
      throw new Error(
        `Failed to connect to Music eye engine: ${e.message}\n\n` +
        'Make sure the helper app is open on the same phone as NoteScan.'
      );
    } finally {
      if (heartbeat) clearInterval(heartbeat);
    }

    report('Received Music eye response...');
    report('Detecting staff lines...');
    report('Classifying symbols...');
    report('Extracting note values...');
    
    const raw = await response.text();
    let json;
    try {
      json = JSON.parse(raw);
    } catch (e) {
      throw new Error(
        `Music eye returned a non-JSON response: ${e.message}` +
        (raw ? `\n\n${raw.substring(0, 500)}` : '')
      );
    }
    
    report('Building MusicXML...');
    
    const rawMusicXml = typeof json.musicxml === 'string' ? json.musicxml : '';
    const parsedInputMusicXml = injectDeterministicNoteIds(
      normalizeMusicXmlSoftwareTag(rawMusicXml)
    );
    if (!parsedInputMusicXml || parsedInputMusicXml.length < 50) {
      throw new Error('Music eye returned an empty or invalid MusicXML payload');
    }

    const parsed = MusicXMLParser.parse(parsedInputMusicXml, {
      strictMusicXml: true,
      collapseTies: true,
    });

    // Preserve native tempo exactly as emitted by libsource-lib.so for debugging/transparency.
    const nativeTempoMatch = [...rawMusicXml.matchAll(/<sound[^>]*\btempo="([\d.]+)"/g)];
    const nativeTempo = nativeTempoMatch.length > 0
      ? Number.parseFloat(nativeTempoMatch[nativeTempoMatch.length - 1][1])
      : null;

    report('Done!');

    return {
      // Keep export payload pure: exactly what the native library returned.
      musicXml: rawMusicXml,
      parsedMusicXml: parsed.musicXml || parsedInputMusicXml,
      notes: parsed.notes,
      metadata: {
        ...parsed.metadata,
        nativeTempo: Number.isFinite(nativeTempo) ? nativeTempo : null,
        source: 'zemsky-emulator',
      },
      notePositions: null,
      processedImageUri: null,
    };
  }

  async processSheet(imageUri, onProgress) {
    return this.processImage(imageUri, onProgress);
  }
}

export const ZemskyEmulatorService = new ZemskyEmulatorServiceClass();
