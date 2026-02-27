/**
 * HOMR (Homer's Optical Music Recognition) Server Integration Service
 *
 * Replaces the on-device MusicSheetProcessor with server-side OMR.
 * Sends sheet music images to a HOMR server and receives MusicXML back.
 *
 * HOMR API:  POST /process  (multipart/form-data with file field)
 *            Returns: MusicXML file
 */
import { File, Paths } from 'expo-file-system';
import Constants from 'expo-constants';
import { MusicXMLParser } from './MusicXMLParser';

// Auto-detect the Expo dev server URL so we can proxy through the tunnel.
// The Metro config proxies /process to the local HOMR server.
function getDefaultServerUrl() {
  try {
    // In Expo Go, the manifest has the dev server URL (including tunnel)
    const debuggerHost =
      Constants.expoConfig?.hostUri ||
      Constants.manifest?.debuggerHost ||
      Constants.manifest2?.extra?.expoGo?.debuggerHost;
    if (debuggerHost) {
      // Tunnel URLs (e.g. xxx.exp.direct) are HTTPS on default port
      // LAN URLs (e.g. 192.168.1.5:8081) are HTTP with explicit port
      if (debuggerHost.includes('.exp.direct') || debuggerHost.includes('ngrok') || debuggerHost.includes('tunnel')) {
        const host = debuggerHost.split(':')[0];
        return `https://${host}`;
      }
      // LAN mode — use http with the port
      const host = debuggerHost.split(':')[0];
      const port = debuggerHost.split(':')[1] || '8081';
      return `http://${host}:${port}`;
    }
  } catch (e) {
    console.log('Could not auto-detect dev server URL:', e.message);
  }
  return 'http://localhost:8081';
}

class HOMRServiceClass {
  _serverUrl = null; // Will auto-detect on first use
  _timeout = 120000; // 2 minutes — OMR can be slow on large scores

  /**
   * Set the HOMR server URL.
   * @param {string} url - e.g. 'http://192.168.1.100:8080' or 'https://homr.example.com'
   */
  setServerUrl(url) {
    // Remove trailing slash
    this._serverUrl = url.replace(/\/+$/, '');
    console.log(`🌐 HOMR server set to: ${this._serverUrl}`);
  }

  getServerUrl() {
    if (!this._serverUrl) {
      this._serverUrl = getDefaultServerUrl();
      console.log(`🌐 HOMR server auto-detected: ${this._serverUrl}`);
    }
    return this._serverUrl;
  }

  /**
   * Set the request timeout in milliseconds.
   * @param {number} ms
   */
  setTimeout(ms) {
    this._timeout = ms;
  }

  /**
   * Check if the HOMR server is reachable.
   * @returns {Promise<{ok: boolean, message: string}>}
   */
  async checkHealth() {
    const serverUrl = this.getServerUrl();
    console.log(`🔍 Health check → ${serverUrl}/docs`);
    try {
      const controller = new AbortController();
      const timeout = setTimeout(() => controller.abort(), 20000); // 20s for tunnel

      const response = await fetch(`${serverUrl}/docs`, {
        method: 'GET',
        signal: controller.signal,
      });
      clearTimeout(timeout);

      return {
        ok: response.ok || response.status === 200,
        message: response.ok
          ? `Server is reachable at ${serverUrl}`
          : `Server responded with ${response.status}`,
      };
    } catch (e) {
      return {
        ok: false,
        message: e.name === 'AbortError'
          ? `Server timed out (20s) — URL: ${serverUrl}`
          : `Cannot reach server at ${serverUrl}: ${e.message}`,
      };
    }
  }

  /**
   * Send an image to the HOMR server for OMR processing.
   *
   * @param {string} imageUri - local file URI (from camera or gallery)
   * @param {function} [onProgress] - optional callback: (stage: string) => void
   * @returns {Promise<{musicXml: string, notes: Array, metadata: Object}>}
   */
  async processImage(imageUri, onProgress) {
    const report = (msg) => {
      console.log(`🎼 HOMR: ${msg}`);
      if (onProgress) onProgress(msg);
    };

    report('Preparing image for upload...');

    // Verify the image file exists using the new File API
    const file = new File(imageUri);
    if (!file.exists) {
      throw new Error('Image file not found: ' + imageUri);
    }

    report('Uploading to HOMR server...');

    // Determine filename and mime type
    const fileName = imageUri.split('/').pop() || 'sheet_music.jpg';
    const ext = fileName.split('.').pop().toLowerCase();
    const mimeType = ext === 'png' ? 'image/png' : 'image/jpeg';

    // Upload using fetch + FormData (replaces deprecated FileSystem.uploadAsync)
    let response;
    try {
      // Read file as blob for upload
      const fileBlob = file;
      const formData = new FormData();
      formData.append('file', {
        uri: imageUri,
        name: fileName,
        type: mimeType,
      });

      response = await fetch(`${this.getServerUrl()}/process`, {
        method: 'POST',
        body: formData,
        headers: {
          'Accept': 'application/xml, text/xml, */*',
        },
      });
    } catch (e) {
      throw new Error(
        `Failed to connect to HOMR server at ${this.getServerUrl()}: ${e.message}\n\n` +
        'Make sure the HOMR server is running and accessible from this device.'
      );
    }

    if (!response.ok) {
      const body = await response.text().catch(() => '');
      throw new Error(
        `HOMR server returned status ${response.status}.\n` +
        `Response: ${body.substring(0, 500)}`
      );
    }

    report('Parsing MusicXML response...');

    const musicXml = await response.text();
    if (!musicXml || musicXml.length < 50) {
      throw new Error('HOMR server returned an empty or invalid response');
    }

    // Parse MusicXML into the note format expected by PlaybackScreen
    report('Converting to playback format...');
    const parsed = MusicXMLParser.parse(musicXml);

    report('Processing complete!');

    return {
      musicXml,
      notes: parsed.notes,
      metadata: parsed.metadata,
    };
  }

  /**
   * Process an image and return a scoreData object compatible with PlaybackScreen.
   * This is the main entry point that matches the old MusicSheetProcessor.processSheet API.
   *
   * @param {string} imageUri
   * @param {function} [onProgress]
   * @returns {Promise<Object>} scoreData compatible with PlaybackScreen
   */
  async processSheet(imageUri, onProgress) {
    const { notes, metadata } = await this.processImage(imageUri, onProgress);

    // Get image dimensions for cursor positioning
    let imageWidth = 1400;
    let imageHeight = 1000;
    try {
      const { Image } = require('react-native');
      await new Promise((resolve) => {
        Image.getSize(
          imageUri,
          (w, h) => {
            imageWidth = w;
            imageHeight = h;
            resolve();
          },
          () => resolve()
        );
      });
    } catch (e) {
      console.warn('Could not get image dimensions:', e.message);
    }

    // Assign synthetic x/y positions for cursor tracking.
    // Since HOMR returns MusicXML (no pixel coords), we use the
    // beatOffset from the parser to position notes proportionally
    // so that cursor tracking aligns with actual playback timing.
    const systemCount = metadata.systems || 1;
    const systemHeight = imageHeight / systemCount;
    const MARGIN_X = imageWidth * 0.08;
    const usableWidth = imageWidth - 2 * MARGIN_X;

    // Group notes by system
    const notesBySystem = {};
    for (const note of notes) {
      const sys = note.systemIndex || 0;
      if (!notesBySystem[sys]) notesBySystem[sys] = [];
      notesBySystem[sys].push(note);
    }

    // Assign x/y positions based on beatOffset (proportional to timing)
    for (const [sysIdx, sysNotes] of Object.entries(notesBySystem)) {
      const idx = parseInt(sysIdx);
      const systemTop = idx * systemHeight;
      const systemMid = systemTop + systemHeight / 2;

      // Find the beat range for this system
      const beats = sysNotes
        .map(n => n.beatOffset)
        .filter(b => typeof b === 'number' && Number.isFinite(b));
      const minBeat = beats.length > 0 ? Math.min(...beats) : 0;
      const maxBeat = beats.length > 0 ? Math.max(...beats) : 1;
      const beatRange = maxBeat - minBeat;

      for (const note of sysNotes) {
        // Position x proportional to beatOffset within this system's beat range
        if (typeof note.beatOffset === 'number' && Number.isFinite(note.beatOffset) && beatRange > 0) {
          const fraction = (note.beatOffset - minBeat) / beatRange;
          note.x = MARGIN_X + fraction * usableWidth;
        } else {
          note.x = MARGIN_X; // fallback
        }
        // Treble staff notes above center, bass below
        const staffOffset = note.staffIndex % 2 === 0 ? -systemHeight * 0.15 : systemHeight * 0.15;
        note.y = systemMid + staffOffset;
      }
    }

    // Build staff groups and system bounds for PlaybackVisualization
    const staffGroups = [];
    const systemBounds = [];
    for (let s = 0; s < systemCount; s++) {
      const top = s * systemHeight + systemHeight * 0.1;
      const bottom = (s + 1) * systemHeight - systemHeight * 0.1;
      const staffIndices = metadata.stavesPerSystem
        ? Array.from({ length: metadata.stavesPerSystem }, (_, i) => s * metadata.stavesPerSystem + i)
        : [s * 2, s * 2 + 1];

      systemBounds.push({ top, bottom, staffIndices });
      for (const si of staffIndices) {
        const mid = (top + bottom) / 2;
        const offset = (si - staffIndices[0]) / Math.max(1, staffIndices.length - 1);
        // Generate 5 staff lines per staff
        const staffTop = top + offset * (bottom - top) * 0.6;
        const spacing = (bottom - top) * 0.08;
        staffGroups.push(Array.from({ length: 5 }, (_, i) => staffTop + i * spacing));
      }
    }

    // Merge notes + rests and sort
    const allEvents = [...notes].sort((a, b) => {
      const sa = Number.isFinite(a.staffIndex) ? a.staffIndex : 999;
      const sb = Number.isFinite(b.staffIndex) ? b.staffIndex : 999;
      if (sa !== sb) return sa - sb;
      return (a.x || 0) - (b.x || 0);
    });

    const totalNotes = notes.filter((n) => n.type !== 'rest').length;
    const totalRests = notes.filter((n) => n.type === 'rest').length;

    return {
      notes: allEvents,
      staves: metadata.staves || 2,
      measures: [],
      metadata: {
        imageWidth,
        imageHeight,
        staffGroups,
        keySignature: metadata.keySignature || { type: 'None', count: 0 },
        timeSignature: metadata.timeSignature || { beats: 4, beatType: 4 },
        clefs: metadata.clefs || ['treble', 'bass'],
        barLines: [],
        ledgerLines: 0,
        systems: systemBounds,
        timestamp: new Date().toISOString(),
        totalNotes,
        totalRests,
        source: 'HOMR',
        title: metadata.title || '',
      },
    };
  }
}

export const HOMRService = new HOMRServiceClass();
