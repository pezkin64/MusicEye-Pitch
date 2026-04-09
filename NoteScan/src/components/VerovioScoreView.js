import React, { forwardRef, useEffect, useImperativeHandle, useMemo, useRef, useState } from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { WebView } from 'react-native-webview';
import { Asset } from 'expo-asset';

function normalizeRenderPreset(preset) {
  if (preset === 'smart' || preset === 'fit') return preset;
  return 'fit';
}

function estimateScale(musicXml, renderViewPreset = 'fit') {
  const preset = normalizeRenderPreset(renderViewPreset);
  const measureCount = (musicXml.match(/<measure\b/gi) || []).length;
  const partCount = (musicXml.match(/<part\b/gi) || []).length || 1;
  const density = measureCount * partCount;

  if (preset === 'fit') {
    if (density >= 180) return 18;
    if (density >= 120) return 20;
    if (density >= 70) return 24;
    return 28;
  }

  if (density >= 180) return 22;
  if (density >= 120) return 26;
  if (density >= 70) return 30;
  return 34;
}

function buildHtml(musicXml, runtimeUri, renderViewPreset = 'fit') {
  const xml = JSON.stringify(musicXml || '');
  const safeRuntimeUri = typeof runtimeUri === 'string' ? runtimeUri.replace(/"/g, '&quot;') : '';
  const scale = estimateScale(musicXml || '', renderViewPreset);

  return `<!doctype html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=3, user-scalable=yes" />
  <style>
    html, body { margin:0; padding:0; background:#f9f7f1; color:#3e3c37; }
    #topbar {
      display:flex; justify-content:space-between; align-items:center;
      padding:10px 12px 8px; border-bottom:1px solid #ddd6ca; background:#f9f7f1;
      position:sticky; top:0; z-index:10;
    }
    #label { font-size:12px; font-weight:700; letter-spacing:0.04em; text-transform:uppercase; color:#6e675e; }
    #status { font-size:12px; color:#6e675e; }
    #score { padding:10px 10px 20px; }
    .page {
      margin:0 auto 18px; max-width:100%; background:#fffdf7;
      border:1px solid #e7dfd1; border-radius:14px; overflow:hidden;
      box-shadow:0 8px 26px rgba(28, 27, 25, 0.05);
    }
    .page svg { width:100% !important; height:auto !important; display:block; }
    .loading, .error { padding:18px 14px; font-size:14px; color:#6e675e; }
    .error { color:#9d3f28; }
    .active-verovio-note path,
    .active-verovio-note ellipse,
    .active-verovio-note circle,
    .active-verovio-note polygon,
    .active-verovio-note rect,
    .active-verovio-note line,
    .active-verovio-note polyline,
    .active-verovio-note use {
      fill: var(--playhead-color, #e05a2a) !important;
      stroke: var(--playhead-color, #e05a2a) !important;
      stroke-width: 1.1px !important;
    }
    .active-verovio-note {
      filter: drop-shadow(0 0 2.5px rgba(224, 90, 42, 0.7));
    }
  </style>
</head>
<body>
  <div id="topbar">
    <div id="label">Verovio</div>
    <div id="status">Loading runtime…</div>
  </div>
  <div id="score"><div class="loading">Preparing engraved score…</div></div>

  <script>
    (function () {
      const xml = ${xml};
      const runtimeUri = ${JSON.stringify(safeRuntimeUri)};
      const scoreEl = document.getElementById('score');
      const statusEl = document.getElementById('status');
      const scale = ${scale};
      let currentToolkit = null;
      let activeNodes = [];
      let domIdIndex = new Map();
      let playheadMode = 'notes';
      let playheadColor = '#F08A45';

      function post(type, payload) {
        if (!window.ReactNativeWebView) return;
        window.ReactNativeWebView.postMessage(JSON.stringify({ type, payload }));
      }

      function fail(message) {
        scoreEl.innerHTML = '<div class="error">' + message + '</div>';
        statusEl.textContent = 'Renderer unavailable';
        post('error', message);
      }

      function clearActiveNotes() {
        if (!activeNodes.length) return;
        for (let i = 0; i < activeNodes.length; i += 1) {
          const n = activeNodes[i];
          if (!n) continue;
          n.style.removeProperty('--playhead-color');
          n.classList.remove('active-verovio-note');
        }
        activeNodes = [];
      }

      function applyNodeColor(node) {
        if (!node) return;
        node.style.setProperty('--playhead-color', playheadColor);
      }

      function updatePlayheadColor(color) {
        if (typeof color !== 'string' || color.length < 4) return;
        playheadColor = color;
        document.documentElement.style.setProperty('--playhead-color', playheadColor);
      }

      function addIndexEntry(key, node) {
        if (!key || !node) return;
        if (!domIdIndex.has(key)) domIdIndex.set(key, []);
        domIdIndex.get(key).push(node);
      }

      function indexIdVariants(rawId, node) {
        if (!rawId || !node) return;
        addIndexEntry(rawId, node);
        if (rawId.startsWith('note-')) addIndexEntry(rawId.slice(5), node);
        if (rawId.startsWith('chord-')) addIndexEntry(rawId.slice(6), node);
        if (rawId.startsWith('rest-')) addIndexEntry(rawId.slice(5), node);
      }

      function rebuildDomIdIndex() {
        domIdIndex = new Map();
        const nodes = scoreEl.querySelectorAll('[id]');
        for (let i = 0; i < nodes.length; i += 1) {
          const node = nodes[i];
          const id = node.getAttribute('id');
          if (!id) continue;
          indexIdVariants(id, node);
        }
      }

      function resolveTimedNodes(ids) {
        const out = [];
        if (!Array.isArray(ids) || !ids.length) return out;
        for (let i = 0; i < ids.length; i += 1) {
          const rawId = String(ids[i] || '').trim();
          if (!rawId) continue;
          const candidates = domIdIndex.has(rawId) ? domIdIndex.get(rawId) : [];

          for (let c = 0; c < candidates.length; c += 1) {
            const n = candidates[c];
            if (n && out.indexOf(n) === -1) out.push(n);
          }
        }
        return out;
      }

      function highlightByNoteIds(noteIds) {
        if (!Array.isArray(noteIds) || !noteIds.length) {
          return false;
        }
        const timedNodes = resolveTimedNodes(noteIds);
        if (!timedNodes.length) return false;
        clearActiveNotes();
        for (let i = 0; i < timedNodes.length; i += 1) {
          applyNodeColor(timedNodes[i]);
          timedNodes[i].classList.add('active-verovio-note');
        }
        activeNodes = timedNodes;
        return true;
      }

      function highlightAtTimeSeconds(timeSec) {
        if (!currentToolkit || !Number.isFinite(timeSec) || timeSec < 0) return false;
        try {
          const ms = Math.max(0, Math.round(timeSec * 1000));
          const atTime = currentToolkit.getElementsAtTime(ms);
          if (!atTime || typeof atTime !== 'object') return false;

          const ids = [];
          if (Array.isArray(atTime.notes)) ids.push(...atTime.notes);
          if (Array.isArray(atTime.chords)) ids.push(...atTime.chords);
          if (Array.isArray(atTime.rests)) ids.push(...atTime.rests);
          if (!ids.length) return false;

          // Determine the latest onset among currently sounding elements,
          // then highlight only that onset group.
          let latestOnsetMs = -Infinity;
          const onsetById = new Map();
          for (let i = 0; i < ids.length; i += 1) {
            const id = ids[i];
            if (!id) continue;
            let onsetMs = NaN;
            try {
              onsetMs = Number(currentToolkit.getTimeForElement(id));
            } catch (e) {
              onsetMs = NaN;
            }
            if (!Number.isFinite(onsetMs)) continue;
            onsetById.set(id, onsetMs);
            if (onsetMs > latestOnsetMs) latestOnsetMs = onsetMs;
          }

          let highlightIds = [];
          if (Number.isFinite(latestOnsetMs)) {
            const EPS_MS = 8;
            highlightIds = ids.filter((id) => {
              const onsetMs = onsetById.get(id);
              return Number.isFinite(onsetMs) && Math.abs(onsetMs - latestOnsetMs) <= EPS_MS;
            });
          } else {
            // Fallback if onset lookup is unavailable on this runtime.
            highlightIds = ids;
          }

          const timedNodes = resolveTimedNodes(highlightIds);
          if (!timedNodes.length) return activeNodes.length > 0;

          clearActiveNotes();
          for (let i = 0; i < timedNodes.length; i += 1) {
            applyNodeColor(timedNodes[i]);
            timedNodes[i].classList.add('active-verovio-note');
          }
          activeNodes = timedNodes;
          return true;
        } catch (e) {
          return false;
        }
      }

      function syncActiveNoteIds(noteIds, timeSec) {
        if (playheadMode === 'line') {
          clearActiveNotes();
          return true;
        }

        if (highlightByNoteIds(noteIds)) {
          return true;
        }

        // Debug: if id-based failed, log what we're looking for vs what's available
        if (Array.isArray(noteIds) && noteIds.length > 0) {
          const available = Array.from(domIdIndex.keys()).slice(0, 10);
          console.warn('[Verovio] ID mismatch - looking for', noteIds, 'available:', available);
        }

        // Fallback to time-based
        if (Number.isFinite(timeSec) && highlightAtTimeSeconds(timeSec)) {
          return true;
        }
        clearActiveNotes();
        return false;
      }

      function renderScore() {
        try {
          if (!window.verovio || !window.verovio.toolkit) {
            fail('Verovio toolkit did not initialize');
            return;
          }

          currentToolkit = new window.verovio.toolkit();
          currentToolkit.setOptions({
            inputFormat: 'musicxml',
            scale,
            adjustPageHeight: true,
            pageWidth: Math.max(900, Math.floor(window.innerWidth * 3)),
            pageHeight: Math.max(1200, Math.floor(window.innerHeight * 2)),
          });

          currentToolkit.loadData(xml);
          const pageCount = currentToolkit.getPageCount();
          if (!Number.isFinite(pageCount) || pageCount <= 0) {
            fail('No pages produced by Verovio');
            return;
          }

          scoreEl.innerHTML = '';
          const svg = currentToolkit.renderToSVG(1, {});
          const pageDiv = document.createElement('div');
          pageDiv.className = 'page';
          pageDiv.innerHTML = svg;
          scoreEl.appendChild(pageDiv);
          rebuildDomIdIndex();
          updatePlayheadColor(playheadColor);

          statusEl.textContent = pageCount + ' page' + (pageCount === 1 ? '' : 's');
          post('ready', { ok: true, pageCount });

          // Render remaining pages lazily so first paint appears quickly.
          let nextPage = 2;
          function renderNext() {
            if (nextPage > pageCount) return;
            statusEl.textContent = 'Rendering page ' + nextPage + ' of ' + pageCount + '…';
            const nextSvg = currentToolkit.renderToSVG(nextPage, {});
            const nextPageDiv = document.createElement('div');
            nextPageDiv.className = 'page';
            nextPageDiv.innerHTML = nextSvg;
            scoreEl.appendChild(nextPageDiv);
            rebuildDomIdIndex();
            nextPage += 1;
            if (nextPage <= pageCount) {
              setTimeout(renderNext, 30);
            } else {
              statusEl.textContent = pageCount + ' page' + (pageCount === 1 ? '' : 's') + ' rendered';
            }
          }
          if (pageCount > 1) setTimeout(renderNext, 0);
        } catch (err) {
          fail(String(err && err.message ? err.message : err));
        }
      }

      function waitForToolkitAndRender() {
        let attempts = 0;
        const maxAttempts = 120;
        const timer = setInterval(function () {
          attempts += 1;
          if (window.verovio && window.verovio.toolkit) {
            clearInterval(timer);
            renderScore();
            return;
          }
          if (attempts >= maxAttempts) {
            clearInterval(timer);
            fail('Timed out waiting for Verovio runtime');
          }
        }, 50);
      }

      if (!runtimeUri) {
        fail('Local Verovio runtime URI missing');
        return;
      }

      const script = document.createElement('script');
      script.src = runtimeUri;
      script.async = true;
      script.onload = function () {
        statusEl.textContent = 'Initializing runtime…';
        waitForToolkitAndRender();
      };
      script.onerror = function () {
        fail('Failed to load local Verovio runtime script');
      };
      document.head.appendChild(script);

      window.__resetPlayback = function () {
        clearActiveNotes();
      };
      window.__setPlayheadMode = function (mode) {
        if (mode === 'line' || mode === 'notes' || mode === 'both') {
          playheadMode = mode;
          clearActiveNotes();
        }
      };
      window.__setPlayheadColor = function (color) {
        updatePlayheadColor(color);
      };
      window.__syncBeat = function () {
        // Intentionally ignored in deterministic mode.
      };
      window.__syncPlayback = function () {
        if (playheadMode === 'line') {
          clearActiveNotes();
        }
      };
      window.__syncActiveNoteIds = syncActiveNoteIds;
    })();
  </script>
</body>
</html>`;
}

export const VerovioScoreView = forwardRef(({
  musicXml,
  currentBeat,
  playheadMode = 'notes',
  playheadColor = '#F08A45',
  renderViewPreset = 'fit',
  onPlayheadModeChange,
  onPlayheadColorChange,
  onRenderViewPresetChange,
}, ref) => {
  const webViewRef = useRef(null);
  const [runtimeUri, setRuntimeUri] = useState('');

  useEffect(() => {
    let cancelled = false;

    (async () => {
      try {
        const asset = Asset.fromModule(require('../../assets/verovio-toolkit-wasm.html'));
        if (!asset.localUri) await asset.downloadAsync();
        const localRuntimeUri = asset.localUri || '';
        if (!localRuntimeUri) {
          throw new Error('Local Verovio runtime URI is unavailable');
        }
        if (!cancelled) setRuntimeUri(localRuntimeUri);
      } catch (e) {
        if (!cancelled) setRuntimeUri('');
      }
    })();

    return () => {
      cancelled = true;
    };
  }, []);

  const html = useMemo(() => buildHtml(musicXml || '', runtimeUri, renderViewPreset), [musicXml, runtimeUri, renderViewPreset]);

  useImperativeHandle(ref, () => ({
    syncBeat: (beat, totalBeats) => {
      if (!webViewRef.current || !Number.isFinite(beat) || !Number.isFinite(totalBeats) || totalBeats <= 0) return;
      webViewRef.current.injectJavaScript(`(function(){ if (window.__syncBeat) { try { window.__syncBeat(${beat.toFixed(6)}, ${totalBeats.toFixed(6)}); } catch (e) {} } true; })();`);
    },
    syncPlayback: (timeSec, totalSec) => {
      if (!webViewRef.current || !Number.isFinite(timeSec) || !Number.isFinite(totalSec) || totalSec <= 0) return;
      webViewRef.current.injectJavaScript(`(function(){ if (window.__syncPlayback) { try { window.__syncPlayback(${timeSec.toFixed(6)}, ${totalSec.toFixed(6)}); } catch (e) {} } true; })();`);
    },
    syncActiveNoteIds: (noteIds, timeSec, totalSec, onsetProgress) => {
      if (!webViewRef.current) return false;
      const safeIds = Array.isArray(noteIds) ? noteIds.filter((id) => typeof id === 'string' && id.length > 0) : [];
      const idsJson = JSON.stringify(safeIds);
      const safeTime = Number.isFinite(timeSec) ? timeSec.toFixed(6) : '0';
      const safeTotal = Number.isFinite(totalSec) ? totalSec.toFixed(6) : '0';
      const safeOnsetProgress = Number.isFinite(onsetProgress) ? onsetProgress.toFixed(6) : 'NaN';
      webViewRef.current.injectJavaScript(`(function(){ if (window.__syncActiveNoteIds) { try { window.__syncActiveNoteIds(${idsJson}, ${safeTime}, ${safeTotal}, ${safeOnsetProgress}); } catch (e) {} } true; })();`);
      return true;
    },
    resetPlayback: () => {
      if (!webViewRef.current) return;
      webViewRef.current.injectJavaScript(`(function(){ if (window.__resetPlayback) { try { window.__resetPlayback(); } catch (e) {} } true; })();`);
    },
    setPlayheadMode: (mode) => {
      if (!webViewRef.current) return;
      const safeMode = mode === 'line' || mode === 'notes' || mode === 'both' ? mode : 'notes';
      webViewRef.current.injectJavaScript(`(function(){ if (window.__setPlayheadMode) { try { window.__setPlayheadMode('${safeMode}'); } catch (e) {} } true; })();`);
    },
    setPlayheadColor: (color) => {
      if (!webViewRef.current || typeof color !== 'string') return;
      const safeColor = color.replace(/'/g, '');
      webViewRef.current.injectJavaScript(`(function(){ if (window.__setPlayheadColor) { try { window.__setPlayheadColor('${safeColor}'); } catch (e) {} } true; })();`);
    },
    setVoiceSelection: () => {},
  }));

  useEffect(() => {
    if (!webViewRef.current || !Number.isFinite(currentBeat)) return;
    // Verovio highlighting is driven by playback timestamps and active note IDs.
  }, [currentBeat]);

  useEffect(() => {
    if (!webViewRef.current) return;
    const safeMode = playheadMode === 'line' || playheadMode === 'notes' || playheadMode === 'both' ? playheadMode : 'notes';
    webViewRef.current.injectJavaScript(`(function(){ if (window.__setPlayheadMode) { try { window.__setPlayheadMode('${safeMode}'); } catch (e) {} } true; })();`);
  }, [playheadMode]);

  useEffect(() => {
    if (!webViewRef.current || typeof playheadColor !== 'string') return;
    const safeColor = playheadColor.replace(/'/g, '');
    if (safeColor.length < 4) return;
    webViewRef.current.injectJavaScript(`(function(){ if (window.__setPlayheadColor) { try { window.__setPlayheadColor('${safeColor}'); } catch (e) {} } true; })();`);
  }, [playheadColor]);

  if (!musicXml || musicXml.length < 20) {
    return (
      <View style={styles.centered}>
        <Text style={styles.muted}>No MusicXML available for rendered score.</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <View style={styles.controlBar}>
        <Pressable
          style={({ pressed }) => [styles.controlPill, pressed && styles.pressedPill]}
          onPress={() => {
            if (typeof onRenderViewPresetChange === 'function') {
              const presets = ['smart', 'fit'];
              const idx = presets.indexOf(renderViewPreset);
              onRenderViewPresetChange(presets[(idx + 1) % presets.length]);
            }
          }}
        >
          <Text style={styles.controlText}>View {renderViewPreset}</Text>
        </Pressable>
        <Text style={styles.engineText}>Verovio renderer</Text>
      </View>
      <WebView
        ref={webViewRef}
        originWhitelist={["*"]}
        source={{ html }}
        javaScriptEnabled
        domStorageEnabled
        allowFileAccess
        allowFileAccessFromFileURLs
        allowUniversalAccessFromFileURLs
        setBuiltInZoomControls
        setDisplayZoomControls={false}
        automaticallyAdjustContentInsets={false}
        startInLoadingState
        setSupportMultipleWindows={false}
        onMessage={() => {}}
        style={styles.webview}
      />
    </View>
  );
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f9f7f1',
  },
  controlBar: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 8,
    paddingHorizontal: 12,
    paddingTop: 10,
    paddingBottom: 8,
    backgroundColor: '#f9f7f1',
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#ddd6ca',
  },
  controlPill: {
    height: 30,
    paddingHorizontal: 10,
    borderRadius: 15,
    borderWidth: 1,
    borderColor: '#d6d0c4',
    backgroundColor: '#fbfaf5',
    alignItems: 'center',
    justifyContent: 'center',
  },
  controlText: {
    fontSize: 12,
    color: '#3e3c37',
    fontWeight: '600',
  },
  engineText: {
    fontSize: 12,
    color: '#6e675e',
    fontWeight: '600',
    marginLeft: 'auto',
  },
  pressedPill: {
    opacity: 0.7,
  },
  webview: {
    flex: 1,
    backgroundColor: '#f9f7f1',
  },
  centered: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    padding: 16,
  },
  muted: {
    color: '#6e675e',
    fontSize: 13,
    textAlign: 'center',
  },
});
