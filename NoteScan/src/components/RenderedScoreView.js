import React, { useEffect, useMemo, useRef, useImperativeHandle, forwardRef, useState } from 'react';
import { Pressable, StyleSheet, Text, View } from 'react-native';
import { WebView } from 'react-native-webview';
import { Asset } from 'expo-asset';
import { File } from 'expo-file-system/next';

const DEFAULT_RENDER_ZOOM = 0.76;
const SMART_SECTION_LABEL_RE = /\b(chorus|refrain|bridge|verse|intro|outro|interlude|pre-chorus|prechorus|coda|tag|ending)\b/i;
const SYSTEM_BREAK_HINT_RE = /<print\b[^>]*\bnew-(?:system|page)\s*=\s*"yes"[^>]*\/>/i;

let cachedOsmdScriptText = null;
let osmdScriptLoadPromise = null;
const OSMD_RUNTIME_SOURCE = 'local-js'; // change to 'bundled-html' or 'cdn' to roll back
const OSMD_RUNTIME_ASSETS = {
  'local-js': require('../../assets/opensheetmusicdisplay.min.js'),
  'bundled-html': require('../../assets/opensheetmusicdisplay.min.html'),
};

function decodeUtf8Bytes(bytes) {
  if (typeof TextDecoder !== 'undefined') {
    return new TextDecoder('utf-8').decode(bytes);
  }

  let out = '';
  const chunkSize = 0x8000;
  for (let i = 0; i < bytes.length; i += chunkSize) {
    const chunk = bytes.subarray(i, i + chunkSize);
    out += String.fromCharCode(...chunk);
  }
  return out;
}

async function loadLocalOsmdRuntimeScript() {
  if (cachedOsmdScriptText) return cachedOsmdScriptText;
  if (osmdScriptLoadPromise) return osmdScriptLoadPromise;

  osmdScriptLoadPromise = (async () => {
    const loadAssetText = async (assetModule) => {
      const asset = Asset.fromModule(assetModule);
      if (!asset.localUri) {
        await asset.downloadAsync();
      }

      const uri = asset.localUri || asset.uri;
      const file = new File(uri);
      const bytes = await file.bytes();
      return decodeUtf8Bytes(bytes);
    };

    try {
      if (OSMD_RUNTIME_SOURCE === 'local-js') {
        cachedOsmdScriptText = await loadAssetText(OSMD_RUNTIME_ASSETS['local-js']);
      } else if (OSMD_RUNTIME_SOURCE === 'bundled-html') {
        cachedOsmdScriptText = await loadAssetText(OSMD_RUNTIME_ASSETS['bundled-html']);
      } else {
        throw new Error('Use CDN fallback path');
      }
      return cachedOsmdScriptText;
    } catch (localError) {
      try {
        cachedOsmdScriptText = await loadAssetText(OSMD_RUNTIME_ASSETS['bundled-html']);
        return cachedOsmdScriptText;
      } catch (bundledError) {
        const response = await fetch('https://cdn.jsdelivr.net/npm/opensheetmusicdisplay@1.9.7/build/opensheetmusicdisplay.min.js');
        cachedOsmdScriptText = await response.text();
        return cachedOsmdScriptText;
      }
    }
  })();

  return osmdScriptLoadPromise;
}

function normalizeRenderPreset(preset) {
  if (preset === 'smart' || preset === 'fit') return preset;
  return 'fit';
}

function hasPartGroups(musicXml) {
  return typeof musicXml === 'string' && /<part-group\b/i.test(musicXml);
}

function hasPartNames(musicXml) {
  return typeof musicXml === 'string' && /<part-name\b/i.test(musicXml);
}

function ensurePartGroupingForPairedStaves(musicXml) {
  if (typeof musicXml !== 'string' || musicXml.length < 20) return musicXml;
  if (/<part-group\b/i.test(musicXml)) return musicXml;

  const scorePartIds = [...musicXml.matchAll(/<score-part\s+id="([^"]+)"/gi)].map((m) => m[1]);
  if (scorePartIds.length !== 2) return musicXml;

  const partNames = [...musicXml.matchAll(/<score-part\b[\s\S]*?<part-name\b[^>]*>([\s\S]*?)<\/part-name>[\s\S]*?<\/score-part>/gi)]
    .map((m) => (m[1] || '').replace(/\s+/g, ' ').trim().toLowerCase())
    .filter(Boolean);

  const shouldGroup = partNames.length === 2
    ? partNames[0] === partNames[1]
    : true;
  if (!shouldGroup) return musicXml;

  return musicXml.replace(/<part-list>([\s\S]*?)<\/part-list>/i, (_full, body) => {
    const groupStart = '<part-group number="1" type="start"><group-symbol>brace</group-symbol><group-barline>yes</group-barline></part-group>';
    const groupStop = '<part-group number="1" type="stop"/>';
    return `<part-list>${groupStart}${body}${groupStop}</part-list>`;
  });
}

function normalizeVoicesByStaff(musicXml) {
  if (typeof musicXml !== 'string' || musicXml.length < 20) return musicXml;

  const partPattern = /(<part\b[^>]*>)([\s\S]*?)(<\/part>)/gi;
  return musicXml.replace(partPattern, (_partFull, partOpen, partBody, partClose) => {
    const measurePattern = /(<measure\b[^>]*>)([\s\S]*?)(<\/measure>)/gi;
    const normalizedPartBody = partBody.replace(measurePattern, (_measureFull, measureOpen, measureBody, measureClose) => {
      const voiceRemap = new Map();
      let nextVoice = 1;

      const normalizedMeasureBody = measureBody.replace(/<note\b[^>]*>[\s\S]*?<\/note>/gi, (noteXml) => {
        const voiceMatch = noteXml.match(/<voice>(\d+)<\/voice>/i);
        const staffMatch = noteXml.match(/<staff>(\d+)<\/staff>/i);
        if (!voiceMatch || !staffMatch) return noteXml;

        const originalVoice = parseInt(voiceMatch[1], 10);
        const staffNum = parseInt(staffMatch[1], 10);
        if (!Number.isFinite(originalVoice) || !Number.isFinite(staffNum)) return noteXml;

        const key = `${staffNum}_${originalVoice}`;
        if (!voiceRemap.has(key)) {
          voiceRemap.set(key, nextVoice);
          nextVoice += 1;
        }
        const mappedVoice = voiceRemap.get(key);
        return noteXml.replace(/<voice>\d+<\/voice>/i, `<voice>${mappedVoice}</voice>`);
      });

      return `${measureOpen}${normalizedMeasureBody}${measureClose}`;
    });

    return `${partOpen}${normalizedPartBody}${partClose}`;
  });
}

function estimatePresetZoom(musicXml, renderViewPreset = 'fit') {
  if (typeof musicXml !== 'string' || musicXml.length < 20) return DEFAULT_RENDER_ZOOM;
  const preset = normalizeRenderPreset(renderViewPreset);

  const measureCount = (musicXml.match(/<measure\b/gi) || []).length;
  const scorePartCount = (musicXml.match(/<score-part\b/gi) || []).length;
  const partCount = scorePartCount > 0 ? scorePartCount : (musicXml.match(/<part\b/gi) || []).length;
  const stavesMatches = [...musicXml.matchAll(/<staves>\s*(\d+)\s*<\/staves>/gi)];
  const maxStaves = stavesMatches.reduce((acc, m) => {
    const n = parseInt(m[1], 10);
    return Number.isFinite(n) ? Math.max(acc, n) : acc;
  }, 1);
  const density = measureCount * Math.max(1, partCount, maxStaves);

  if (preset === 'fit') {
    if (density >= 240) return 0.56;
    if (density >= 180) return 0.60;
    if (density >= 130) return 0.64;
    if (density >= 95) return 0.68;
    if (density >= 65) return 0.72;
    return 0.76;
  }

  if (density >= 240) return 0.62;
  if (density >= 180) return 0.66;
  if (density >= 130) return 0.70;
  if (density >= 95) return 0.74;
  if (density >= 65) return 0.78;
  return 0.82;
}

function getPresetLayoutSettings(renderViewPreset = 'fit') {
  const preset = normalizeRenderPreset(renderViewPreset);
  if (preset === 'fit') {
    return {
      drawingParameters: 'default',
      minComfortZoom: 0.56,
      overflowTolerance: 1.02,
      fitPasses: 2,
      fitBias: 0.98,
      notationFontScale: 35,
      staffDistance: 8.2,
      betweenStaffDistance: 6.5,
      minSkyBottomDistBetweenSystems: 3.0,
      minDistanceBetweenSystems: 3.0,
      // Prioritize stable cross-staff alignment over aggressive compression.
      voiceSpacingMultiplierVexflow: 0.72,
      voiceSpacingAddendVexflow: 7.0,
      measureXSpacingFactor: 1.2,
    };
  }
  return {
    drawingParameters: 'compact',
    minComfortZoom: 0.66,
    overflowTolerance: 1.1,
    fitPasses: 1,
    fitBias: 1.05,
    notationFontScale: 35,
    staffDistance: 7.8,
    betweenStaffDistance: 6.2,
    minSkyBottomDistBetweenSystems: 2.8,
    minDistanceBetweenSystems: 2.8,
    voiceSpacingMultiplierVexflow: 0.94,
    voiceSpacingAddendVexflow: 4.0,
    measureXSpacingFactor: 1.03,
  };
}

function hasSmartSectionLabel(measureBody) {
  if (typeof measureBody !== 'string' || !measureBody) return false;
  const labels = measureBody.match(/<(words|rehearsal)\b[^>]*>[\s\S]*?<\/\1>/gi) || [];
  for (const labelXml of labels) {
    const labelText = labelXml
      .replace(/<[^>]+>/g, ' ')
      .replace(/\s+/g, ' ')
      .trim();
    if (SMART_SECTION_LABEL_RE.test(labelText)) {
      return true;
    }
  }
  return false;
}

function forceSmartSystemBreaks(musicXml) {
  if (typeof musicXml !== 'string' || musicXml.length < 20) return musicXml;

  const measurePattern = /(<measure\b[^>]*>)([\s\S]*?)(<\/measure>)/gi;
  return musicXml.replace(measurePattern, (full, openTag, body, closeTag) => {
    if (!hasSmartSectionLabel(body)) return full;

    const hasNewSystem = SYSTEM_BREAK_HINT_RE.test(body);
    if (hasNewSystem) return full;

    return `${openTag}<print new-system="yes"/>${body}${closeTag}`;
  });
}

function stripSystemBreakHints(musicXml) {
  if (typeof musicXml !== 'string' || musicXml.length < 20) return musicXml;

  // Remove self-closing print tags that force system/page breaks.
  let out = musicXml.replace(/\s*<print\b[^>]*\bnew-(?:system|page)\s*=\s*"yes"[^>]*\/?>\s*/gi, '');

  // Remove paired print tags that force system/page breaks.
  out = out.replace(/\s*<print\b[^>]*\bnew-(?:system|page)\s*=\s*"yes"[^>]*>[\s\S]*?<\/print>\s*/gi, '');

  return out;
}

function buildHtml(musicXml, renderViewPreset = 'fit', osmdRuntimeScriptText = '', allowCdnFallback = false) {
  const groupedXml = ensurePartGroupingForPairedStaves(musicXml || '');
  const voiceNormalizedXml = normalizeVoicesByStaff(groupedXml);
  // Remove explicit break hints from noisy OMR output so OSMD can compute
  // consistent multi-staff alignment for treble and bass.
  const preparedXml = stripSystemBreakHints(voiceNormalizedXml);
  const xml = JSON.stringify(preparedXml);
  const renderZoom = estimatePresetZoom(preparedXml, renderViewPreset);
  const layoutSettings = getPresetLayoutSettings(renderViewPreset);
  const drawPartStructure = hasPartGroups(preparedXml) || hasPartNames(preparedXml);
  const escapedRuntimeScript = typeof osmdRuntimeScriptText === 'string' && osmdRuntimeScriptText.length > 100
    ? osmdRuntimeScriptText.replace(/<\/script/gi, '<\\/script')
    : '';
  const runtimeScriptTag = escapedRuntimeScript
    ? `<script>${escapedRuntimeScript}</script>`
    : allowCdnFallback
      ? '<script src="https://cdn.jsdelivr.net/npm/opensheetmusicdisplay@1.8.9/build/opensheetmusicdisplay.min.js"></script>'
      : '';
  return `<!doctype html>
<html>
<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=0.9, maximum-scale=3, user-scalable=yes" />
  <style>
    html, body {
      margin: 0;
      padding: 0;
      background: #f9f7f1;
      overflow: auto;
      overflow-x: hidden;
      height: 100%;
      width: 100%;
    }
    #score {
      width: 100%;
      min-height: 100%;
      box-sizing: border-box;
      padding: 4px 0 10px;
      background: #f9f7f1;
    }
    #score svg {
      width: 100% !important;
      max-width: 100% !important;
      height: auto !important;
      display: block;
      shape-rendering: geometricPrecision;
      text-rendering: optimizeLegibility;
    }
    #score.playhead-notes .osmd-cursor,
    #score.playhead-notes .osmd-cursor * {
      display: none !important;
      opacity: 0 !important;
      visibility: hidden !important;
    }
    .active-note path,
    .active-note ellipse,
    .active-note circle,
    .active-note polygon,
    .active-note rect,
    .active-note line,
    .active-note polyline,
    .active-note use {
      fill: var(--playhead-color, #e05a2a) !important;
      stroke: var(--playhead-color, #e05a2a) !important;
      stroke-width: 1.15px !important;
    }
    .active-note * {
      fill: var(--playhead-color, #e05a2a) !important;
      stroke: var(--playhead-color, #e05a2a) !important;
    }
    .active-note {
      filter: drop-shadow(0 0 2.5px var(--playhead-glow, rgba(224, 90, 42, 0.78))) saturate(1.1);
    }
    .osmd-cursor {
      opacity: 1 !important;
      stroke: var(--playhead-color, #e05a2a) !important;
      stroke-width: 1.8px !important;
    }
    .osmd-cursor > line {
      stroke: var(--playhead-color, #e05a2a) !important;
      stroke-width: 1.8px !important;
    }
  </style>
</head>
<body>
  <div id="score"></div>

  ${runtimeScriptTag}
  <script>
    (function () {
      const xml = ${xml};
      const renderZoom = ${renderZoom};
      const layoutSettings = ${JSON.stringify(layoutSettings)};
      const scoreEl = document.getElementById('score');
      let osmd = null;
      let noteTimestamps = []; // Store all note positions with their timestamps
      let glyphIndexReady = false;
      let lastBeat = 0;
      let lastTargetPos = 0;
      let displayedCursorRect = null;
      let cursorResetTimer = null;
      let smoothScrollRaf = 0;
      let smoothScrollTargetTop = null;
      let smoothScrollEl = null;
      let lastSeekTapTs = 0;
      let userScrollLockUntil = 0;
      let suppressTapUntil = 0;
      let pointerGesture = null;
      let touchGesture = null;
      let playheadMode = 'both'; // 'line' | 'notes' | 'both'
      let playheadColor = '#F08A45';
      let activeGraphicalNotes = [];
      let activeElementIds = new Set();
      let graphicalNotesByElementId = new Map();
      let highlightBuckets = [];
      let activeVoiceSelection = {
        Soprano: true,
        Alto: true,
        Tenor: true,
        Bass: true,
      };

      function post(type, payload) {
        if (!window.ReactNativeWebView) return;
        window.ReactNativeWebView.postMessage(JSON.stringify({ type, payload }));
      }

      /**
       * Extract all note timestamps from OSMD's iterator.
       * Returns array with realValue and position for cursor mapping.
       */
      function extractNoteTimestamps() {
        if (!osmd || !osmd.cursor) return [];
        
        const stamps = [];
        try {
          if (!glyphIndexReady) indexGlyphCandidates();
          osmd.cursor.reset();
          let maxRealValue = 0;
          while (!osmd.cursor.iterator.EndReached) {
            const ts = osmd.cursor.iterator.currentTimeStamp;
            if (ts && ts.realValue !== undefined) {
              const iterator = osmd.cursor.iterator;
              const graphicalMeasure = iterator
                ? (iterator.currentMeasure || iterator.CurrentMeasure || null)
                : null;
              const sourceMeasure = graphicalMeasure
                ? (
                  graphicalMeasure.parentSourceMeasure ||
                  graphicalMeasure.ParentSourceMeasure ||
                  graphicalMeasure.sourceMeasure ||
                  graphicalMeasure.SourceMeasure ||
                  null
                )
                : null;
              const measureNumberRaw = sourceMeasure
                ? (
                  sourceMeasure.MeasureNumber ??
                  sourceMeasure.measureNumber ??
                  sourceMeasure.Number ??
                  sourceMeasure.number
                )
                : (
                  graphicalMeasure
                    ? (
                      graphicalMeasure.MeasureNumber ??
                      graphicalMeasure.measureNumber ??
                      graphicalMeasure.Number ??
                      graphicalMeasure.number
                    )
                    : null
                );
              const measureNumber = Number(measureNumberRaw);
              const measureKey = Number.isFinite(measureNumber)
                ? String(Math.round(measureNumber))
                : null;

              const cursorRect = getCursorRect();
              const glyphIds = cursorRect ? collectGlyphIdsForRect(cursorRect) : [];
              stamps.push({
                realValue: ts.realValue,
                position: stamps.length,
                glyphIds,
                measureKey,
              });
              maxRealValue = Math.max(maxRealValue, ts.realValue);
            }
            osmd.cursor.next();
          }
          // Store the max so we can normalize beat values
          if (stamps.length > 0) {
            stamps.maxRealValue = maxRealValue || 1;

            const firstIndexByMeasureKey = new Map();
            for (let i = 0; i < stamps.length; i += 1) {
              const key = stamps[i] && typeof stamps[i].measureKey === 'string' ? stamps[i].measureKey : null;
              if (!key || firstIndexByMeasureKey.has(key)) continue;
              firstIndexByMeasureKey.set(key, i);
            }
            stamps.firstIndexByMeasureKey = firstIndexByMeasureKey;
          }
        } catch (e) {
          console.warn('Note timestamp extraction failed:', e);
        }
        osmd.cursor.reset();
        return stamps;
      }

      function indexGlyphCandidates() {
        const groups = scoreEl.querySelectorAll('g');
        let gid = 0;
        groups.forEach((el) => {
          if (typeof el.getBBox !== 'function') return;
          try {
            const bb = el.getBBox();
            if (!Number.isFinite(bb.x) || !Number.isFinite(bb.y)) return;
            if (bb.width <= 0 || bb.height <= 0) return;
            if (bb.width > 30 || bb.height > 30) return;
            el.setAttribute('data-me-gid', String(gid));
            gid += 1;
          } catch (e) {
            // ignore unsupported elements
          }
        });
        glyphIndexReady = true;
      }

      function collectGlyphIdsForRect(rect) {
        const cx = rect.x + (rect.width * 0.5);
        const cy = rect.y + (rect.height * 0.5);
        const maxDx = 12;
        const maxDy = 14;

        const hits = [];
        const candidates = scoreEl.querySelectorAll('g[data-me-gid]');
        candidates.forEach((el) => {
          try {
            const bb = el.getBBox();
            const aspect = bb.height > 0 ? (bb.width / bb.height) : 0;
            const area = bb.width * bb.height;
            const gx = bb.x + (bb.width * 0.5);
            const gy = bb.y + (bb.height * 0.5);
            const dx = Math.abs(gx - cx);
            const dy = Math.abs(gy - cy);
            const noteLike = area >= 8 && area <= 900 && aspect >= 0.25 && aspect <= 4.5;
            if (noteLike && dx <= maxDx && dy <= maxDy) {
              hits.push({
                id: el.getAttribute('data-me-gid'),
                dist: dx + dy + Math.abs(1 - aspect) * 8 + Math.min(10, Math.abs(40 - area) / 10),
              });
            }
          } catch (e) {
            // ignore
          }
        });

        hits.sort((a, b) => a.dist - b.dist);
        return hits.slice(0, 8).map((h) => h.id);
      }

      function getActiveNoteRect(entry, fallbackEl) {
        let bestRect = null;
        let bestScore = Number.POSITIVE_INFINITY;

        if (entry && Array.isArray(entry.glyphIds)) {
          for (let i = 0; i < entry.glyphIds.length; i += 1) {
            const gid = entry.glyphIds[i];
            const el = scoreEl.querySelector('g[data-me-gid="' + gid + '"]');
            if (!el || typeof el.getBoundingClientRect !== 'function') continue;
            try {
              const rect = el.getBoundingClientRect();
              if (rect && rect.width > 0 && rect.height > 0) {
                const aspect = rect.height > 0 ? (rect.width / rect.height) : 0;
                const area = rect.width * rect.height;
                const score = Math.abs(1 - aspect) * 6 + Math.abs(40 - area) / 12;
                if (score < bestScore) {
                  bestScore = score;
                  bestRect = rect;
                }
              }
            } catch (e) {
              // ignore and keep searching for a more reliable glyph
            }
          }
        }

        if (fallbackEl && typeof fallbackEl.getBoundingClientRect === 'function') {
          try {
            const rect = fallbackEl.getBoundingClientRect();
            if (rect && rect.width > 0 && rect.height > 0 && !bestRect) {
              bestRect = rect;
            }
          } catch (e) {
            // ignore
          }
        }

        return bestRect;
      }

      function clearActiveNotes() {
        if (activeGraphicalNotes.length) {
          activeGraphicalNotes.forEach((graphicalNote) => {
            restoreGraphicalNote(graphicalNote);
          });
          activeGraphicalNotes = [];
        }

        const active = scoreEl.querySelectorAll('.active-note');
        active.forEach((el) => el.classList.remove('active-note'));
      }

      function isAllVoicesEnabled() {
        return !!(
          activeVoiceSelection.Soprano &&
          activeVoiceSelection.Alto &&
          activeVoiceSelection.Tenor &&
          activeVoiceSelection.Bass
        );
      }

      function setVoiceSelection(selection) {
        if (!selection || typeof selection !== 'object') return;
        activeVoiceSelection = {
          Soprano: selection.Soprano !== false,
          Alto: selection.Alto !== false,
          Tenor: selection.Tenor !== false,
          Bass: selection.Bass !== false,
        };
      }

      function normalizeElementId(raw) {
        if (raw === null || raw === undefined) return '';
        const id = String(raw).trim();
        if (!id) return '';
        if (id.startsWith('note-')) return id.slice(5);
        if (id.startsWith('chord-')) return id.slice(6);
        if (id.startsWith('rest-')) return id.slice(5);
        return id;
      }

      function setActiveNoteIds(ids) {
        const next = new Set();
        (Array.isArray(ids) ? ids : []).forEach((id) => {
          const normalized = normalizeElementId(id);
          if (normalized) next.add(normalized);
        });
        activeElementIds = next;
      }

      function setHighlightBucketsPayload(payload, totalBeats) {
        const buckets = Array.isArray(payload) ? payload : [];
        highlightBuckets = buckets
          .map((bucket) => ({
            beatOffset: Number(bucket?.beatOffset),
            elementIds: Array.isArray(bucket?.elementIds)
              ? bucket.elementIds
                  .map((id) => normalizeElementId(id))
                  .filter((id) => !!id)
              : [],
          }))
          .filter((bucket) => Number.isFinite(bucket.beatOffset))
          .sort((a, b) => a.beatOffset - b.beatOffset);

        // totalBeats is passed for consistency with the native bridge API.
        // Bucket lookup is beat-based and does not require additional scaling.
      }

      function findHighlightBucketIndexForBeat(beat) {
        if (!Number.isFinite(beat) || !Array.isArray(highlightBuckets) || highlightBuckets.length === 0) {
          return -1;
        }
        let lo = 0;
        let hi = highlightBuckets.length - 1;
        let ans = -1;
        while (lo <= hi) {
          const mid = (lo + hi) >> 1;
          if (highlightBuckets[mid].beatOffset <= beat + 0.001) {
            ans = mid;
            lo = mid + 1;
          } else {
            hi = mid - 1;
          }
        }
        return ans;
      }

      function syncHighlightIdsFromCanonicalBeat(beat) {
        const idx = findHighlightBucketIndexForBeat(beat);
        if (idx < 0) {
          setActiveNoteIds([]);
          return;
        }
        setActiveNoteIds(highlightBuckets[idx].elementIds || []);
      }

      function getGraphicalNoteElementId(graphicalNote) {
        if (!graphicalNote || !graphicalNote.sourceNote) return '';
        const source = graphicalNote.sourceNote;
        const candidate =
          source.NoteId ??
          source.noteId ??
          source.Id ??
          source.id ??
          source.xmlId ??
          null;
        return normalizeElementId(candidate);
      }

      function filterGraphicalNotesByActiveIds(graphicalNotes) {
        if (!activeElementIds || activeElementIds.size === 0) return graphicalNotes;
        const notes = Array.isArray(graphicalNotes) ? graphicalNotes : [];
        return notes.filter((note) => {
          const id = getGraphicalNoteElementId(note);
          return id && activeElementIds.has(id);
        });
      }

      function getActiveGraphicalNotesFromIds() {
        if (!activeElementIds || activeElementIds.size === 0) return [];

        const next = [];
        const seen = new Set();
        activeElementIds.forEach((id) => {
          const matches = graphicalNotesByElementId.get(id);
          if (!Array.isArray(matches)) return;
          matches.forEach((note) => {
            if (!note || seen.has(note)) return;
            seen.add(note);
            next.push(note);
          });
        });
        return next;
      }

      function indexGraphicalNotesByElementId() {
        graphicalNotesByElementId = new Map();
        if (!osmd || !osmd.cursor || !osmd.cursor.iterator) return;

        try {
          osmd.cursor.reset();
          osmd.cursor.show();

          let steps = 0;
          const maxSteps = Math.max(512, (noteTimestamps.length || 0) * 6);

          while (steps < maxSteps) {
            const notes = getCursorGraphicalNotes();
            notes.forEach((note) => {
              const id = getGraphicalNoteElementId(note);
              if (!id) return;
              const bucket = graphicalNotesByElementId.get(id) || [];
              if (!bucket.includes(note)) {
                bucket.push(note);
                graphicalNotesByElementId.set(id, bucket);
              }
            });

            if (osmd.cursor.iterator.EndReached) break;
            osmd.cursor.next();
            steps += 1;
          }
        } catch (e) {
          // Keep playback functional even if traversal fails on some scores.
        } finally {
          try {
            osmd.cursor.reset();
            osmd.cursor.show();
            lastTargetPos = 0;
            displayedCursorRect = getCursorRect();
          } catch (e) {
            // ignore
          }
        }
      }

      function pickGlyphIdsForSelectedVoices(candidateIds) {
        const ranked = [];
        (Array.isArray(candidateIds) ? candidateIds : []).forEach((gid) => {
          const el = scoreEl.querySelector('g[data-me-gid="' + gid + '"]');
          if (!el || typeof el.getBBox !== 'function') return;
          try {
            const bb = el.getBBox();
            ranked.push({ id: gid, y: bb.y + (bb.height * 0.5) });
          } catch (e) {
            // ignore invalid glyph
          }
        });

        if (!ranked.length) return [];
        ranked.sort((a, b) => a.y - b.y);

        const top = 0;
        const second = Math.min(1, ranked.length - 1);
        const third = Math.max(0, ranked.length - 2);
        const bottom = ranked.length - 1;

        const picks = [];
        if (activeVoiceSelection.Soprano) picks.push(top);
        if (activeVoiceSelection.Alto) picks.push(second);
        if (activeVoiceSelection.Tenor) picks.push(third);
        if (activeVoiceSelection.Bass) picks.push(bottom);

        const unique = [];
        const seen = new Set();
        picks.forEach((idx) => {
          if (idx < 0 || idx >= ranked.length) return;
          if (seen.has(idx)) return;
          seen.add(idx);
          unique.push(ranked[idx].id);
        });

        return unique;
      }

      function getCursorGraphicalNotes() {
        if (!osmd || !osmd.cursor || typeof osmd.cursor.GNotesUnderCursor !== 'function') return [];
        try {
          return osmd.cursor.GNotesUnderCursor().filter(Boolean);
        } catch (e) {
          return [];
        }
      }

      function getGraphicalNoteCenterY(graphicalNote) {
        if (!graphicalNote || typeof graphicalNote.getNoteheadSVGs !== 'function') return null;
        try {
          const noteheads = graphicalNote.getNoteheadSVGs() || [];
          for (let i = 0; i < noteheads.length; i += 1) {
            const notehead = noteheads[i];
            if (!notehead || typeof notehead.getBoundingClientRect !== 'function') continue;
            const rect = notehead.getBoundingClientRect();
            if (rect && rect.width > 0 && rect.height > 0) {
              return rect.top + (rect.height * 0.5);
            }
          }
        } catch (e) {
          // ignore and fall back to null
        }
        return null;
      }

      function filterGraphicalNotesByVoiceSelection(graphicalNotes) {
        const notes = Array.isArray(graphicalNotes) ? graphicalNotes.filter(Boolean) : [];
        if (!notes.length) return [];
        if (isAllVoicesEnabled()) return notes;

        const ranked = notes
          .map((note) => ({ note, y: getGraphicalNoteCenterY(note) }))
          .filter((entry) => Number.isFinite(entry.y))
          .sort((a, b) => a.y - b.y);

        if (!ranked.length) return [];

        const top = 0;
        const second = Math.min(1, ranked.length - 1);
        const third = Math.max(0, ranked.length - 2);
        const bottom = ranked.length - 1;

        const picks = [];
        if (activeVoiceSelection.Soprano) picks.push(top);
        if (activeVoiceSelection.Alto) picks.push(second);
        if (activeVoiceSelection.Tenor) picks.push(third);
        if (activeVoiceSelection.Bass) picks.push(bottom);

        const unique = [];
        const seen = new Set();
        picks.forEach((idx) => {
          if (idx < 0 || idx >= ranked.length) return;
          if (seen.has(idx)) return;
          seen.add(idx);
          unique.push(ranked[idx].note);
        });

        return unique;
      }

      function getGraphicalNoteColor(graphicalNote) {
        if (!graphicalNote || !graphicalNote.sourceNote) return '#000000';
        return graphicalNote.sourceNote.NoteheadColorCurrentlyRendered
          || graphicalNote.sourceNote.NoteheadColor
          || '#000000';
      }

      function applyGraphicalNoteColor(graphicalNote, color) {
        if (!graphicalNote || typeof graphicalNote.setColor !== 'function') return false;
        try {
          graphicalNote.setColor(color, {
            applyToBeams: true,
            applyToFlag: true,
            applyToLedgerLines: false,
            applyToLyrics: false,
            applyToModifiers: true,
            applyToNoteheads: true,
            applyToSlurs: false,
            applyToStem: true,
            applyToTies: false,
            applyToMultiRestMeasure: true,
            applyToMultiRestMeasureNumber: true,
            applyToMultiRestMeasureRestBar: true,
          });
          return true;
        } catch (e) {
          return false;
        }
      }

      function restoreGraphicalNote(graphicalNote) {
        if (!graphicalNote) return;
        applyGraphicalNoteColor(graphicalNote, getGraphicalNoteColor(graphicalNote));
      }

      function syncActiveGraphicalNotes(nextGraphicalNotes) {
        const nextNotes = Array.isArray(nextGraphicalNotes) ? nextGraphicalNotes.filter(Boolean) : [];
        if (!nextNotes.length) {
          clearActiveNotes();
          return false;
        }

        const nextSet = new Set(nextNotes);
        const prevNotes = activeGraphicalNotes;

        prevNotes.forEach((graphicalNote) => {
          if (!nextSet.has(graphicalNote)) {
            restoreGraphicalNote(graphicalNote);
          }
        });

        let applied = false;
        nextNotes.forEach((graphicalNote) => {
          applied = applyGraphicalNoteColor(graphicalNote, playheadColor) || applied;
        });

        activeGraphicalNotes = nextNotes;
        return applied || nextNotes.length > 0;
      }

      function getGraphicalNoteRect(graphicalNotes, fallbackEl) {
        if (Array.isArray(graphicalNotes)) {
          for (const graphicalNote of graphicalNotes) {
            if (!graphicalNote || typeof graphicalNote.getNoteheadSVGs !== 'function') continue;
            try {
              const noteheads = graphicalNote.getNoteheadSVGs();
              for (const notehead of noteheads || []) {
                if (!notehead || typeof notehead.getBoundingClientRect !== 'function') continue;
                const rect = notehead.getBoundingClientRect();
                if (rect && rect.width > 0 && rect.height > 0) {
                  return rect;
                }
              }
            } catch (e) {
              // ignore and fall back to the cursor rectangle
            }
          }
        }

        if (fallbackEl && typeof fallbackEl.getBoundingClientRect === 'function') {
          try {
            const rect = fallbackEl.getBoundingClientRect();
            if (rect && rect.width > 0 && rect.height > 0) {
              return rect;
            }
          } catch (e) {
            // ignore
          }
        }

        return null;
      }

      function cancelSmoothScroll() {
        if (smoothScrollRaf) {
          cancelAnimationFrame(smoothScrollRaf);
          smoothScrollRaf = 0;
        }
      }

      function noteUserScrollInteraction(lockMs = 450) {
        userScrollLockUntil = Date.now() + lockMs;
        suppressTapUntil = Math.max(suppressTapUntil, Date.now() + Math.min(650, Math.max(220, lockMs)));
        cancelSmoothScroll();
      }

      function isUserScrollLocked() {
        return Date.now() < userScrollLockUntil;
      }

      function requestSmoothScroll(scrollEl, targetTop, urgency = 1) {
        if (!scrollEl || !Number.isFinite(targetTop)) return;

        smoothScrollEl = scrollEl;
        const maxScrollTop = Math.max(0, scrollEl.scrollHeight - scrollEl.clientHeight);
        smoothScrollTargetTop = Math.max(0, Math.min(targetTop, maxScrollTop));
        const clampedUrgency = Math.max(1, Math.min(2.2, urgency));

        if (smoothScrollRaf) return;

        const step = () => {
          if (!smoothScrollEl || !Number.isFinite(smoothScrollTargetTop)) {
            cancelSmoothScroll();
            return;
          }

          const current = smoothScrollEl.scrollTop;
          const diff = smoothScrollTargetTop - current;
          const absDiff = Math.abs(diff);

          // Dead-zone avoids tiny jitter while highlights vibrate around the same y.
          if (absDiff < 0.75) {
            smoothScrollEl.scrollTop = smoothScrollTargetTop;
            cancelSmoothScroll();
            return;
          }

          const viewportHeight = Math.max(1, smoothScrollEl.clientHeight || 1);
          const baseLerp = 0.2;
          const lerp = Math.min(0.38, baseLerp * clampedUrgency);
          const maxStep = viewportHeight * (0.045 + 0.03 * clampedUrgency);
          const rawStep = diff * lerp;
          const boundedStep = Math.max(-maxStep, Math.min(maxStep, rawStep));

          smoothScrollEl.scrollTop = current + boundedStep;
          smoothScrollRaf = requestAnimationFrame(step);
        };

        smoothScrollRaf = requestAnimationFrame(step);
      }

      function keepRectVisible(rect) {
        if (!rect || !scoreEl || !scoreEl.parentElement) return;
        if (isUserScrollLocked()) return;
        const scrollEl = scoreEl.parentElement;
        if (typeof scrollEl.getBoundingClientRect !== 'function') return;

        try {
          const viewportRect = scrollEl.getBoundingClientRect();
          const viewportHeight = Math.max(1, viewportRect.height || scrollEl.clientHeight || 1);
          const topMargin = viewportHeight * 0.22;
          const bottomMargin = viewportHeight * 0.30;

          const upperBound = viewportRect.top + topMargin;
          const lowerBound = viewportRect.bottom - bottomMargin;

          if (rect.bottom > lowerBound) {
            const overflow = rect.bottom - lowerBound;
            const desiredTop = scrollEl.scrollTop + overflow;
            const urgency = 1 + Math.min(1.2, overflow / Math.max(1, viewportHeight));
            requestSmoothScroll(scrollEl, desiredTop, urgency);
          } else if (rect.top < upperBound) {
            const overflow = upperBound - rect.top;
            const desiredTop = scrollEl.scrollTop - overflow;
            const urgency = 1 + Math.min(1.2, overflow / Math.max(1, viewportHeight));
            requestSmoothScroll(scrollEl, desiredTop, urgency);
          }
        } catch (e) {
          // Best-effort: don't interrupt rendering on scroll errors.
        }
      }

      function applyVisualMode() {
        const cursor = getCursorElement();
        scoreEl.classList.toggle('playhead-notes', playheadMode === 'notes');
        if (cursor) {
          if (playheadMode === 'notes') {
            cursor.style.opacity = '0';
            cursor.style.visibility = 'hidden';
            cursor.style.display = 'none';
          } else if (playheadMode === 'both') {
            cursor.style.display = '';
            cursor.style.visibility = 'visible';
            cursor.style.opacity = '0.18';
          } else {
            cursor.style.display = '';
            cursor.style.visibility = 'visible';
            cursor.style.opacity = '1';
          }
        }

        syncOsmdImageCursor();
      }

      function getOsmdImageCursorElement() {
        return scoreEl.querySelector('img[id^="cursorImg-"]');
      }

      function syncOsmdImageCursor() {
        const cursorImg = getOsmdImageCursorElement();
        const osmdCursor = osmd && osmd.cursor ? osmd.cursor : null;
        if (!osmdCursor) {
          if (cursorImg) {
            if (playheadMode === 'notes') {
              cursorImg.style.display = 'none';
              cursorImg.style.visibility = 'hidden';
              cursorImg.style.opacity = '0';
            } else if (playheadMode === 'both') {
              cursorImg.style.display = '';
              cursorImg.style.visibility = 'visible';
              cursorImg.style.opacity = '0.18';
            } else {
              cursorImg.style.display = '';
              cursorImg.style.visibility = 'visible';
              cursorImg.style.opacity = '1';
            }
          }
          return;
        }

        try {
          const currentOptions = osmdCursor.CursorOptions || {};
          const nextOptions = {
            ...currentOptions,
            color: playheadColor,
            alpha: playheadMode === 'both' ? 0.18 : 1,
          };

          osmdCursor.CursorOptions = nextOptions;
          const targetCursor = osmdCursor.cursorElement || cursorImg;
          const width = targetCursor && Number(targetCursor.width) > 0
            ? Number(targetCursor.width)
            : 32;

          if (typeof osmdCursor.updateStyle === 'function') {
            osmdCursor.updateStyle(width, nextOptions);
          }

          if (targetCursor) {
            if (playheadMode === 'notes') {
              targetCursor.style.display = 'none';
              targetCursor.style.visibility = 'hidden';
              targetCursor.style.opacity = '0';
            } else if (playheadMode === 'both') {
              targetCursor.style.display = '';
              targetCursor.style.visibility = 'visible';
              targetCursor.style.opacity = '0.18';
            } else {
              targetCursor.style.display = '';
              targetCursor.style.visibility = 'visible';
              targetCursor.style.opacity = '1';
            }
          }
        } catch (err) {
          // Keep playback working even if OSMD internals differ across versions.
        }
      }

      function setPlayheadMode(mode) {
        if (mode === 'line' || mode === 'notes' || mode === 'both') {
          playheadMode = mode;
          if (playheadMode === 'line') {
            clearActiveNotes();
          }
          applyVisualMode();
        }
      }

      function setPlayheadColor(color) {
        if (typeof color !== 'string' || color.length < 4) return;
        playheadColor = color;
        document.documentElement.style.setProperty('--playhead-color', playheadColor);

        const hex = playheadColor.replace('#', '');
        if (hex.length === 6) {
          const r = parseInt(hex.slice(0, 2), 16);
          const g = parseInt(hex.slice(2, 4), 16);
          const b = parseInt(hex.slice(4, 6), 16);
          if (Number.isFinite(r) && Number.isFinite(g) && Number.isFinite(b)) {
            document.documentElement.style.setProperty('--playhead-glow', 'rgba(' + r + ',' + g + ',' + b + ',0.85)');
          }
        }

        // Directly update all cursor elements to override OSMD's default colors.
        // Use setProperty(..., 'important') because assigning "... !important"
        // to style.stroke/style.fill is ignored by the browser.
        const cursorEls = scoreEl.querySelectorAll('.osmd-cursor, .osmd-cursor line, .osmd-cursor path, .osmd-cursor rect');
        cursorEls.forEach((el) => {
          el.style.setProperty('stroke', playheadColor, 'important');
          el.setAttribute('stroke', playheadColor);
          // Remove any fill that can force legacy green cursor rectangles.
          const tag = el.tagName.toLowerCase();
          if (tag === 'line' || tag === 'rect' || tag === 'path') {
            el.style.setProperty('fill', 'none', 'important');
            el.setAttribute('fill', 'none');
          }
        });

        syncOsmdImageCursor();

        if (activeGraphicalNotes.length) {
          activeGraphicalNotes.forEach((graphicalNote) => {
            applyGraphicalNoteColor(graphicalNote, playheadColor);
          });
        }
      }

      function getCursorElement() {
        return scoreEl.querySelector('.osmd-cursor');
      }

      function getCursorRect() {
        const cursor = getCursorElement();
        if (!cursor || typeof cursor.getBBox !== 'function') return null;
        return cursor.getBBox();
      }

      function animateCursorToTarget(cursor, targetRect) {
        if (!cursor || !targetRect) return;

        if (cursorResetTimer) {
          clearTimeout(cursorResetTimer);
          cursorResetTimer = null;
        }

        cursor.style.transition = 'none';
        cursor.style.transformOrigin = '0 0';
        cursor.style.transform = 'translate(0px, 0px)';

        displayedCursorRect = targetRect;
      }

      function highlightByPositionIndex(positionIndex) {
        if (playheadMode === 'line') {
          clearActiveNotes();
          return;
        }

        const entry = noteTimestamps[positionIndex];
        if (!entry) {
          clearActiveNotes();
          return;
        }

        const cursorEl = getCursorElement();

        // Grounded mode: if caller provided exact note IDs for this tick,
        // color those notes globally instead of restricting to the cursor position.
        if (activeElementIds && activeElementIds.size > 0) {
          const idMatchedGraphicalNotes = getActiveGraphicalNotesFromIds();
          if (idMatchedGraphicalNotes.length) {
            const voiceFilteredByIds = filterGraphicalNotesByVoiceSelection(idMatchedGraphicalNotes);
            syncActiveGraphicalNotes(
              voiceFilteredByIds.length ? voiceFilteredByIds : idMatchedGraphicalNotes
            );
            return;
          }

          clearActiveNotes();
          return;
        }

        const cursorGraphicalNotes = getCursorGraphicalNotes();
        const voiceFilteredGraphicalNotes = filterGraphicalNotesByVoiceSelection(cursorGraphicalNotes);
        const filteredGraphicalNotes = filterGraphicalNotesByActiveIds(voiceFilteredGraphicalNotes);
        const directColorApplied = syncActiveGraphicalNotes(filteredGraphicalNotes);

        let foundAny = directColorApplied;
        if (!directColorApplied) {
          clearActiveNotes();
          const ids = isAllVoicesEnabled()
            ? (Array.isArray(entry.glyphIds) ? entry.glyphIds : [])
            : pickGlyphIdsForSelectedVoices(entry.glyphIds);
          ids.forEach((gid) => {
            const el = scoreEl.querySelector('g[data-me-gid="' + gid + '"]');
            if (el) {
              el.classList.add('active-note');
              foundAny = true;
            }
          });

          if (!foundAny && playheadMode === 'both' && cursorEl) {
            cursorEl.classList.add('active-note');
          }
        }

      }

      function findTargetPosition(targetRealValue) {
        if (!noteTimestamps.length) return 0;
        let lo = 0;
        let hi = noteTimestamps.length - 1;
        let ans = 0;
        while (lo <= hi) {
          const mid = (lo + hi) >> 1;
          if (noteTimestamps[mid].realValue <= targetRealValue) {
            ans = mid;
            lo = mid + 1;
          } else {
            hi = mid - 1;
          }
        }

        // Avoid trailing behind audio: if the next timestamp is closer, snap forward.
        const next = Math.min(ans + 1, noteTimestamps.length - 1);
        const distCurr = Math.abs(targetRealValue - noteTimestamps[ans].realValue);
        const distNext = Math.abs(noteTimestamps[next].realValue - targetRealValue);
        if (next !== ans && distNext < distCurr) return next;
        return ans;
      }

      /**
       * Position cursor intelligently by interpreting beat value.
       * Now expects currentBeat as a normalized progress (0-1) through the score.
       * Maps this to OSMD's internal realValue units for smooth cursor positioning.
       */
      function setBeat(beat) {
        if (!osmd || !osmd.cursor) return;

        try {
          if (noteTimestamps.length === 0) {
            // No timestamps cached yet
            osmd.cursor.reset();
            osmd.cursor.show();
            lastBeat = beat;
            lastTargetPos = 0;
            displayedCursorRect = getCursorRect();
            highlightByPositionIndex(0);
            return;
          }

          const maxRealValue = noteTimestamps.maxRealValue || 1;
          
          // beat should now be 0-1; scale to realValue range
          const targetRealValue = beat * maxRealValue;
          
          // Find the position where realValue <= targetRealValue
          const targetPos = findTargetPosition(targetRealValue);

          // If the beat moved backwards or jumped far ahead, re-sync from the start.
          const needsResync = beat < lastBeat - 0.02 || targetPos < lastTargetPos;

          if (needsResync) {
            osmd.cursor.reset();
            osmd.cursor.show();
            lastTargetPos = 0;
            displayedCursorRect = null;
          }

          // Move cursor forward only as much as needed.
          while (lastTargetPos < targetPos && !osmd.cursor.iterator.EndReached) {
            osmd.cursor.next();
            lastTargetPos += 1;
          }

          lastBeat = beat;
          const cursorEl = getCursorElement();
          const cursorRect = getCursorRect();
          if (cursorEl && cursorRect) {
            cursorEl.style.transition = 'none';
            cursorEl.style.transform = 'none';
            displayedCursorRect = cursorRect;
          }

          highlightByPositionIndex(targetPos);

          // Auto-scroll should track the highlighted note location in notes/both modes.
          // Cursor rect is only a fallback when note rects are unavailable.
          const cursorElForScroll = getCursorElement();
          const activeRect = getGraphicalNoteRect(activeGraphicalNotes, cursorElForScroll);
          if (activeRect) {
            keepRectVisible(activeRect);
          } else if (cursorElForScroll && typeof cursorElForScroll.getBoundingClientRect === 'function') {
            keepRectVisible(cursorElForScroll.getBoundingClientRect());
          }
        } catch (e) {
          post('error', String(e && e.message ? e.message : e));
        }
      }

      function syncPlayback(timeSec, totalSec) {
        if (!Number.isFinite(timeSec) || !Number.isFinite(totalSec) || totalSec <= 0) return;
        setBeat(Math.max(0, Math.min(1, timeSec / totalSec)));
      }

      function syncCanonicalBeat(beat, totalBeats) {
        if (!Number.isFinite(beat)) return;
        if (!Number.isFinite(totalBeats) || totalBeats <= 0) {
          setBeat(0);
          return;
        }

        syncHighlightIdsFromCanonicalBeat(Math.max(0, beat));
        setBeat(Math.max(0, Math.min(1, beat / totalBeats)));
      }

      function findNearestTimestampIndexForClientPoint(clientX, clientY) {
        if (!Array.isArray(noteTimestamps) || noteTimestamps.length === 0) return -1;

        const directHitCandidates = [];
        const entryCandidates = [];

        for (let i = 0; i < noteTimestamps.length; i += 1) {
          const entry = noteTimestamps[i];
          if (!entry || !Array.isArray(entry.glyphIds) || entry.glyphIds.length === 0) continue;

          let entryBestDist = Number.POSITIVE_INFINITY;
          let entryBestDx = Number.POSITIVE_INFINITY;
          let entryBestDy = Number.POSITIVE_INFINITY;
          let hasRect = false;

          for (let g = 0; g < entry.glyphIds.length; g += 1) {
            const gid = entry.glyphIds[g];
            const el = scoreEl.querySelector('g[data-me-gid="' + gid + '"]');
            if (!el || typeof el.getBoundingClientRect !== 'function') continue;

            try {
              const rect = el.getBoundingClientRect();
              if (!rect || rect.width <= 0 || rect.height <= 0) continue;

              hasRect = true;
              const cx = rect.left + (rect.width * 0.5);
              const cy = rect.top + (rect.height * 0.5);
              const dx = Math.abs(cx - clientX);
              const dy = Math.abs(cy - clientY);
              const dist = (dx * dx) + (dy * dy);

              // Slightly expanded hitbox makes intentional taps more forgiving.
              const padX = Math.max(7, rect.width * 0.33);
              const padY = Math.max(7, rect.height * 0.33);
              const insideX = clientX >= (rect.left - padX) && clientX <= (rect.right + padX);
              const insideY = clientY >= (rect.top - padY) && clientY <= (rect.bottom + padY);
              if (insideX && insideY) {
                // Prefer targets where the tap lands closest to glyph center.
                directHitCandidates.push({ index: i, dist, dx, dy });
              }

              if (dist < entryBestDist) {
                entryBestDist = dist;
                entryBestDx = dx;
                entryBestDy = dy;
              }
            } catch (e) {
              // ignore unsupported element bounds
            }
          }

          if (hasRect) {
            entryCandidates.push({
              index: i,
              dist: entryBestDist,
              dx: entryBestDx,
              dy: entryBestDy,
            });
          }
        }

        if (directHitCandidates.length > 0) {
          directHitCandidates.sort((a, b) => a.dist - b.dist);
          return directHitCandidates[0].index;
        }

        if (entryCandidates.length > 0) {
          // Prefer entries on the same staff/system row as the tap before x-proximity.
          const minDy = Math.min(...entryCandidates.map((c) => c.dy));
          const verticalBand = Math.max(14, Math.min(54, minDy + 24));
          const sameBand = entryCandidates.filter((c) => c.dy <= verticalBand);
          const pool = sameBand.length > 0 ? sameBand : entryCandidates;

          pool.sort((a, b) => {
            const scoreA = (a.dy * 1.9) + (a.dx * 1.0);
            const scoreB = (b.dy * 1.9) + (b.dx * 1.0);
            if (scoreA !== scoreB) return scoreA - scoreB;
            return a.dist - b.dist;
          });
          return pool[0].index;
        }

        // Fallback: map tap Y position to timeline index so tap always seeks.
        try {
          const scrollEl = scoreEl && scoreEl.parentElement ? scoreEl.parentElement : null;
          if (scrollEl && typeof scrollEl.getBoundingClientRect === 'function') {
            const viewportRect = scrollEl.getBoundingClientRect();
            const yInContent = (clientY - viewportRect.top) + scrollEl.scrollTop;
            const contentHeight = Math.max(1, scoreEl.scrollHeight || scrollEl.scrollHeight || 1);
            const normalizedY = Math.max(0, Math.min(1, yInContent / contentHeight));
            const approx = Math.round(normalizedY * (noteTimestamps.length - 1));
            return Math.max(0, Math.min(noteTimestamps.length - 1, approx));
          }
        } catch (e) {
          // ignore fallback failure
        }

        return -1;
      }

      function seekToTapPoint(clientX, clientY) {
        if (!Number.isFinite(clientX) || !Number.isFinite(clientY)) return;
        const idx = findNearestTimestampIndexForClientPoint(clientX, clientY);
        if (idx < 0 || idx >= noteTimestamps.length) return;

        let targetIdx = idx;
        const tappedEntry = noteTimestamps[idx];
        const measureKey = tappedEntry && typeof tappedEntry.measureKey === 'string' ? tappedEntry.measureKey : null;
        const firstIndexByMeasureKey = noteTimestamps.firstIndexByMeasureKey;
        if (
          measureKey &&
          firstIndexByMeasureKey &&
          typeof firstIndexByMeasureKey.get === 'function'
        ) {
          const firstIdx = firstIndexByMeasureKey.get(measureKey);
          if (Number.isInteger(firstIdx) && firstIdx >= 0 && firstIdx < noteTimestamps.length) {
            targetIdx = firstIdx;
          }
        }

        const entry = noteTimestamps[targetIdx];
        const maxRealValue = noteTimestamps.maxRealValue || 1;
        if (!entry || !Number.isFinite(entry.realValue) || !Number.isFinite(maxRealValue) || maxRealValue <= 0) return;

        const normalizedBeat = Math.max(0, Math.min(1, entry.realValue / maxRealValue));
        post('seekToBeat', { normalizedBeat });
        setBeat(normalizedBeat);
      }

      function handleScoreTap(event) {
        if (!event) return;
        const now = Date.now();
        if (isUserScrollLocked()) return;
        if (now < suppressTapUntil) return;
        if (now - lastSeekTapTs < 140) return;

        let clientX = Number(event.clientX);
        let clientY = Number(event.clientY);

        if ((!Number.isFinite(clientX) || !Number.isFinite(clientY)) && event.changedTouches && event.changedTouches.length > 0) {
          const t = event.changedTouches[0];
          clientX = Number(t && t.clientX);
          clientY = Number(t && t.clientY);
        }

        if (!Number.isFinite(clientX) || !Number.isFinite(clientY)) return;
        lastSeekTapTs = now;
        seekToTapPoint(clientX, clientY);
      }

      function handlePointerDown(event) {
        if (!event) return;
        pointerGesture = {
          pointerId: event.pointerId,
          startX: Number(event.clientX),
          startY: Number(event.clientY),
          moved: false,
        };
      }

      function handlePointerMove(event) {
        if (!event || !pointerGesture) return;
        if (pointerGesture.pointerId !== undefined && event.pointerId !== undefined && pointerGesture.pointerId !== event.pointerId) return;

        const x = Number(event.clientX);
        const y = Number(event.clientY);
        if (!Number.isFinite(x) || !Number.isFinite(y) || !Number.isFinite(pointerGesture.startX) || !Number.isFinite(pointerGesture.startY)) return;

        const dx = x - pointerGesture.startX;
        const dy = y - pointerGesture.startY;
        const movedPx = Math.hypot(dx, dy);
        if (movedPx > 9) {
          pointerGesture.moved = true;
          noteUserScrollInteraction(600);
        }
      }

      function handlePointerUp(event) {
        if (!pointerGesture) {
          handleScoreTap(event);
          return;
        }

        const moved = !!pointerGesture.moved;
        pointerGesture = null;
        if (moved) return;
        handleScoreTap(event);
      }

      function handlePointerCancel() {
        pointerGesture = null;
      }

      function handleTouchStart(event) {
        if (!event || !event.touches || event.touches.length === 0) return;
        const t = event.touches[0];
        touchGesture = {
          startX: Number(t && t.clientX),
          startY: Number(t && t.clientY),
          moved: false,
        };
      }

      function handleTouchMove(event) {
        if (!touchGesture || !event || !event.touches || event.touches.length === 0) return;
        const t = event.touches[0];
        const x = Number(t && t.clientX);
        const y = Number(t && t.clientY);
        if (!Number.isFinite(x) || !Number.isFinite(y) || !Number.isFinite(touchGesture.startX) || !Number.isFinite(touchGesture.startY)) return;

        const dx = x - touchGesture.startX;
        const dy = y - touchGesture.startY;
        const movedPx = Math.hypot(dx, dy);
        if (movedPx > 9) {
          touchGesture.moved = true;
          noteUserScrollInteraction(700);
        }
      }

      function handleTouchEnd(event) {
        if (!touchGesture) {
          handleScoreTap(event);
          return;
        }

        const moved = !!touchGesture.moved;
        touchGesture = null;
        if (moved) return;
        handleScoreTap(event);
      }

      function handleTouchCancel() {
        touchGesture = null;
      }

      function resetPlayback() {
        lastBeat = 0;
        lastTargetPos = 0;
        displayedCursorRect = null;
        clearActiveNotes();
        if (osmd && osmd.cursor) {
          try {
            osmd.cursor.reset();
            osmd.cursor.show();
          } catch (e) {
            // ignore
          }
        }
      }

      async function fitScoreToViewport() {
        if (!osmd) return;
        const viewportHeight = Math.max(1, window.innerHeight || 1);
        const minComfortZoom = layoutSettings.minComfortZoom;
        const overflowTolerance = layoutSettings.overflowTolerance;
        const fitPasses = layoutSettings.fitPasses;
        const fitBias = layoutSettings.fitBias;

        // Single soft-fit pass: prioritize readability over forcing zero scroll.
        for (let i = 0; i < fitPasses; i += 1) {
          const contentHeight = Math.max(1, scoreEl.scrollHeight || 1);
          if (contentHeight <= (viewportHeight * overflowTolerance)) {
            return;
          }

          const fitFactor = (viewportHeight * fitBias) / contentHeight;
          if (!Number.isFinite(fitFactor) || fitFactor >= 0.995) {
            return;
          }

          osmd.Zoom = Math.max(minComfortZoom, osmd.Zoom * fitFactor);
          await osmd.render();
        }
      }

      async function fitScoreToWidth() {
        if (!osmd) return;
        const viewportWidth = Math.max(1, (scoreEl.parentElement && scoreEl.parentElement.clientWidth) || window.innerWidth || 1);
        const svg = scoreEl.querySelector('svg');
        if (!svg) return;

        const contentWidth = Math.max(
          1,
          (svg.getBoundingClientRect && svg.getBoundingClientRect().width) ||
          scoreEl.scrollWidth ||
          1
        );
        const widthFillTarget = 0.985;

        // Expand score so notation uses most of the available page width.
        const currentFill = contentWidth / viewportWidth;
        if (!Number.isFinite(currentFill) || currentFill >= widthFillTarget) return;

        const fitFactor = (viewportWidth * widthFillTarget) / contentWidth;
        if (!Number.isFinite(fitFactor) || fitFactor <= 1.01) return;

        osmd.Zoom = osmd.Zoom * fitFactor;
        await osmd.render();
      }

      window.__setBeat = setBeat;
      window.__syncPlayback = syncPlayback;
      window.__syncCanonicalBeat = syncCanonicalBeat;
      window.__resetPlayback = resetPlayback;
      window.__setPlayheadMode = setPlayheadMode;
      window.__setPlayheadColor = setPlayheadColor;
      window.__setVoiceSelection = setVoiceSelection;
      window.__setActiveNoteIds = setActiveNoteIds;
      window.__setHighlightBuckets = setHighlightBucketsPayload;

      async function boot() {
        if (!window.opensheetmusicdisplay || !window.opensheetmusicdisplay.OpenSheetMusicDisplay) {
          post('error', 'OSMD failed to load from CDN');
          return;
        }

        try {
          osmd = new window.opensheetmusicdisplay.OpenSheetMusicDisplay(scoreEl, {
            backend: 'svg',
            drawTitle: false,
            autoResize: true,
            followCursor: false,
            drawingParameters: layoutSettings.drawingParameters,
            drawPartNames: ${drawPartStructure ? 'true' : 'false'},
            drawPartAbbreviations: ${drawPartStructure ? 'true' : 'false'},
            drawPartConnectors: ${drawPartStructure ? 'true' : 'false'},
            drawFingerings: false,
            drawCredits: false,
            drawMeasureNumbers: true,
          });

          // Render slightly smaller on mobile so more SATB content fits before scrolling.
          osmd.Zoom = renderZoom * 0.95;

          await osmd.load(xml);

          if (osmd.rules) {
            if (typeof osmd.rules.DefaultColorCursor === 'string') {
              osmd.rules.DefaultColorCursor = playheadColor;
            }
            if (Number.isFinite(layoutSettings.notationFontScale)) {
              osmd.rules.VexFlowDefaultNotationFontScale = layoutSettings.notationFontScale;
            }
            if (Number.isFinite(layoutSettings.staffDistance)) {
              osmd.rules.StaffDistance = layoutSettings.staffDistance;
            }
            if (Number.isFinite(layoutSettings.betweenStaffDistance)) {
              osmd.rules.BetweenStaffDistance = layoutSettings.betweenStaffDistance;
            }
            if (Number.isFinite(layoutSettings.minSkyBottomDistBetweenSystems)) {
              osmd.rules.MinSkyBottomDistBetweenSystems = layoutSettings.minSkyBottomDistBetweenSystems;
            }
            if (Number.isFinite(layoutSettings.minDistanceBetweenSystems)) {
              osmd.rules.MinimumDistanceBetweenSystems = layoutSettings.minDistanceBetweenSystems;
            }
            if (Number.isFinite(layoutSettings.voiceSpacingMultiplierVexflow)) {
              osmd.rules.VoiceSpacingMultiplierVexflow = layoutSettings.voiceSpacingMultiplierVexflow;
            }
            if (Number.isFinite(layoutSettings.voiceSpacingAddendVexflow)) {
              osmd.rules.VoiceSpacingAddendVexflow = layoutSettings.voiceSpacingAddendVexflow;
            }
            if (Number.isFinite(layoutSettings.measureXSpacingFactor)) {
              osmd.rules.MeasureXSpacingFactor = layoutSettings.measureXSpacingFactor;
            }
          }

          await osmd.render();

          await fitScoreToWidth();
          await fitScoreToViewport();
          await fitScoreToWidth();

          // Cache all note/rest timestamps for smooth positioning
          noteTimestamps = extractNoteTimestamps();
          indexGraphicalNotesByElementId();
          const scrollEl = scoreEl.parentElement;
          if (scrollEl) {
            scrollEl.addEventListener('wheel', () => noteUserScrollInteraction(550), { passive: true });
            scrollEl.addEventListener('scroll', () => noteUserScrollInteraction(420), { passive: true });
          }
          scoreEl.addEventListener('pointerdown', handlePointerDown, { passive: true });
          scoreEl.addEventListener('pointermove', handlePointerMove, { passive: true });
          scoreEl.addEventListener('pointerup', handlePointerUp, { passive: true });
          scoreEl.addEventListener('pointercancel', handlePointerCancel, { passive: true });
          scoreEl.addEventListener('touchstart', handleTouchStart, { passive: true });
          scoreEl.addEventListener('touchmove', handleTouchMove, { passive: true });
          scoreEl.addEventListener('touchend', handleTouchEnd, { passive: true });
          scoreEl.addEventListener('touchcancel', handleTouchCancel, { passive: true });
          setPlayheadColor(playheadColor);
          setPlayheadMode(playheadMode);
          
          osmd.cursor.show();
          setBeat(0);

          post('ready', { ok: true, noteCount: noteTimestamps.length });
        } catch (e) {
          post('error', String(e && e.message ? e.message : e));
        }
      }

      boot();
    })();
  </script>
</body>
</html>`;
}

export const RenderedScoreView = forwardRef(({ musicXml, currentBeat, playheadMode = 'notes', playheadColor = '#F08A45', renderViewPreset = 'fit', showControlBar = true, onPlayheadModeChange, onPlayheadColorChange, onRenderViewPresetChange, onSeekNormalizedBeat }, ref) => {
  const webViewRef = useRef(null);
  const lastInjectedBeatRef = useRef(-1);
  const [runtimeScriptText, setRuntimeScriptText] = useState('');
  const [runtimeFailed, setRuntimeFailed] = useState(false);

  useEffect(() => {
    let cancelled = false;

    loadLocalOsmdRuntimeScript()
      .then((text) => {
        if (cancelled) return;
        setRuntimeScriptText(text || '');
      })
      .catch((error) => {
        console.warn('Local OSMD runtime load failed, falling back to CDN:', error);
        if (cancelled) return;
        setRuntimeFailed(true);
      });

    return () => {
      cancelled = true;
    };
  }, []);

  const html = useMemo(
    () => buildHtml(musicXml, renderViewPreset, runtimeScriptText, runtimeFailed),
    [musicXml, renderViewPreset, runtimeScriptText, runtimeFailed]
  );

  useImperativeHandle(ref, () => ({
    syncBeat: (beat, totalBeats) => {
      if (!webViewRef.current || !Number.isFinite(beat) || !Number.isFinite(totalBeats) || totalBeats <= 0) return;
      const normalizedBeat = Math.max(0, Math.min(1, beat / totalBeats));
      lastInjectedBeatRef.current = normalizedBeat;
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__syncCanonicalBeat && typeof window.__syncCanonicalBeat === 'function') {
            try {
              window.__syncCanonicalBeat(${beat.toFixed(6)}, ${totalBeats.toFixed(6)});
            } catch (e) {
              console.error('syncCanonicalBeat error:', e);
            }
          } else if (window.__setBeat && typeof window.__setBeat === 'function') {
            try {
              window.__setBeat(${normalizedBeat.toFixed(6)});
            } catch (e) {
              console.error('setBeat fallback error:', e);
            }
          }
          true;
        })();`
      );
    },
    syncPlayback: (timeSec, totalSec) => {
      if (!webViewRef.current || !Number.isFinite(timeSec) || !Number.isFinite(totalSec) || totalSec <= 0) return;
      const normalizedBeat = Math.max(0, Math.min(1, timeSec / totalSec));
      lastInjectedBeatRef.current = normalizedBeat;
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__syncPlayback && typeof window.__syncPlayback === 'function') {
            try {
              window.__syncPlayback(${timeSec.toFixed(6)}, ${totalSec.toFixed(6)});
            } catch (e) {
              console.error('syncPlayback error:', e);
            }
          } else if (window.__setBeat && typeof window.__setBeat === 'function') {
            try {
              window.__setBeat(${normalizedBeat.toFixed(6)});
            } catch (e) {
              console.error('setBeat error:', e);
            }
          }
          true;
        })();`
      );
    },
    resetPlayback: () => {
      lastInjectedBeatRef.current = -1;
      if (!webViewRef.current) return;
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__resetPlayback && typeof window.__resetPlayback === 'function') {
            try {
              window.__resetPlayback();
            } catch (e) {
              console.error('resetPlayback error:', e);
            }
          }
          true;
        })();`
      );
    },
    setPlayheadMode: (mode) => {
      if (!webViewRef.current) return;
      const safeMode = mode === 'line' || mode === 'notes' || mode === 'both' ? mode : 'both';
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__setPlayheadMode && typeof window.__setPlayheadMode === 'function') {
            try {
              window.__setPlayheadMode('${safeMode}');
            } catch (e) {
              console.error('setPlayheadMode error:', e);
            }
          }
          true;
        })();`
      );
    },
    setPlayheadColor: (color) => {
      if (!webViewRef.current || typeof color !== 'string') return;
      const safeColor = color.replace(/'/g, '');
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__setPlayheadColor && typeof window.__setPlayheadColor === 'function') {
            try {
              window.__setPlayheadColor('${safeColor}');
            } catch (e) {
              console.error('setPlayheadColor error:', e);
            }
          }
          true;
        })();`
      );
    },
    setVoiceSelection: (selection) => {
      if (!webViewRef.current || !selection || typeof selection !== 'object') return;
      const payload = JSON.stringify({
        Soprano: selection.Soprano !== false,
        Alto: selection.Alto !== false,
        Tenor: selection.Tenor !== false,
        Bass: selection.Bass !== false,
      });
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__setVoiceSelection && typeof window.__setVoiceSelection === 'function') {
            try {
              window.__setVoiceSelection(${payload});
            } catch (e) {
              console.error('setVoiceSelection error:', e);
            }
          }
          true;
        })();`
      );
    },
    syncActiveNoteIds: (noteIds) => {
      if (!webViewRef.current) return;
      const safeIds = Array.isArray(noteIds)
        ? noteIds.filter((id) => typeof id === 'string' && id.length > 0)
        : [];
      const payload = JSON.stringify(safeIds);
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__setActiveNoteIds && typeof window.__setActiveNoteIds === 'function') {
            try {
              window.__setActiveNoteIds(${payload});
            } catch (e) {
              console.error('setActiveNoteIds error:', e);
            }
          }
          true;
        })();`
      );
    },
    setHighlightBuckets: (buckets, totalBeats) => {
      if (!webViewRef.current) return;
      const safeBuckets = Array.isArray(buckets)
        ? buckets.map((bucket) => ({
            beatOffset: Number(bucket?.beatOffset),
            elementIds: Array.isArray(bucket?.elementIds)
              ? bucket.elementIds.filter((id) => typeof id === 'string' && id.length > 0)
              : [],
          }))
        : [];
      const payload = JSON.stringify(safeBuckets);
      const safeTotalBeats = Number.isFinite(totalBeats) ? totalBeats : 0;
      webViewRef.current.injectJavaScript(
        `(function() {
          if (window.__setHighlightBuckets && typeof window.__setHighlightBuckets === 'function') {
            try {
              window.__setHighlightBuckets(${payload}, ${safeTotalBeats.toFixed(6)});
            } catch (e) {
              console.error('setHighlightBuckets error:', e);
            }
          }
          true;
        })();`
      );
    },
  }));

  useEffect(() => {
    if (!webViewRef.current || !Number.isFinite(currentBeat)) return;
    const normalizedBeat = Math.max(0, Math.min(1, currentBeat));
    if (Math.abs(normalizedBeat - lastInjectedBeatRef.current) < 0.0005) return;
    lastInjectedBeatRef.current = normalizedBeat;
    webViewRef.current.injectJavaScript(
      `(function() {
        if (window.__setBeat && typeof window.__setBeat === 'function') {
          try {
            window.__setBeat(${normalizedBeat.toFixed(6)});
          } catch (e) {
            console.error('setBeat prop fallback error:', e);
          }
        }
        true;
      })();`
    );
  }, [currentBeat]);

  useEffect(() => {
    if (!webViewRef.current) return;
    const safeMode = playheadMode === 'line' || playheadMode === 'notes' || playheadMode === 'both'
      ? playheadMode
      : 'both';
    webViewRef.current.injectJavaScript(
      `(function() {
        if (window.__setPlayheadMode && typeof window.__setPlayheadMode === 'function') {
          try {
            window.__setPlayheadMode('${safeMode}');
          } catch (e) {
            console.error('setPlayheadMode prop error:', e);
          }
        }
        true;
      })();`
    );
  }, [playheadMode]);

  useEffect(() => {
    if (!webViewRef.current || typeof playheadColor !== 'string') return;
    const safeColor = playheadColor.replace(/'/g, '');
    if (safeColor.length < 4) return;
    
    webViewRef.current.injectJavaScript(
      `(function() {
        if (window.__setPlayheadColor && typeof window.__setPlayheadColor === 'function') {
          try {
            console.log('Updating playhead color to:', '${safeColor}');
            window.__setPlayheadColor('${safeColor}');
            
            // Verify the update
            const cursorEls = document.querySelectorAll('.osmd-cursor');
            if (cursorEls.length > 0) {
              console.log('Found', cursorEls.length, 'cursor elements, color applied');
            } else {
              console.warn('No cursor elements found to update');
            }
          } catch (e) {
            console.error('setPlayheadColor prop error:', e);
          }
        } else {
          console.warn('__setPlayheadColor not available');
        }
        true;
      })();`
    );
  }, [playheadColor]);

  if (!musicXml || musicXml.length < 20) {
    return (
      <View style={styles.centered}>
        <Text style={styles.muted}>No MusicXML available for rendered score.</Text>
      </View>
    );
  }

  if (!runtimeScriptText && !runtimeFailed) {
    return (
      <View style={styles.centered}>
        <Text style={styles.muted}>Loading local OSMD runtime...</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      {showControlBar && (
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

          <Pressable
            style={({ pressed }) => [styles.controlPill, pressed && styles.pressedPill]}
            onPress={() => {
              if (typeof onPlayheadModeChange === 'function') {
                const modes = ['notes', 'both', 'line'];
                const idx = modes.indexOf(playheadMode);
                onPlayheadModeChange(modes[(idx + 1) % modes.length]);
              }
            }}
          >
            <Text style={styles.controlText}>Playhead {playheadMode}</Text>
          </Pressable>

          <Pressable
            style={({ pressed }) => [styles.colorPill, pressed && styles.pressedPill]}
            onPress={() => {
              if (typeof onPlayheadColorChange === 'function') {
                const colors = ['#F08A45', '#C94B1A', '#6B4226', '#8B5E3C'];
                const idx = Math.max(0, colors.indexOf(playheadColor));
                const nextColor = colors[(idx + 1) % colors.length];
                console.log('Color pill tapped: current=', playheadColor, 'next=', nextColor);
                onPlayheadColorChange(nextColor);
              }
            }}
          >
            <View style={[styles.colorSwatch, { backgroundColor: playheadColor }]} />
          </Pressable>
        </View>
      )}
      <WebView
        ref={webViewRef}
        originWhitelist={["*"]}
        source={{ html }}
        javaScriptEnabled
        domStorageEnabled
        setBuiltInZoomControls={true}
        setDisplayZoomControls={false}
        automaticallyAdjustContentInsets={false}
        startInLoadingState
        setSupportMultipleWindows={false}
        style={styles.webview}
        onMessage={(event) => {
          try {
            const raw = event?.nativeEvent?.data;
            if (!raw || typeof raw !== 'string') return;
            const message = JSON.parse(raw);
            if (!message || typeof message !== 'object') return;

            if (message.type === 'seekToBeat' && typeof onSeekNormalizedBeat === 'function') {
              const normalizedBeat = Number(message?.payload?.normalizedBeat);
              if (Number.isFinite(normalizedBeat)) {
                onSeekNormalizedBeat(Math.max(0, Math.min(1, normalizedBeat)));
              }
            }
          } catch (e) {
            // ignore malformed webview messages
          }
        }}
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
  colorPill: {
    width: 30,
    height: 30,
    borderRadius: 15,
    borderWidth: 1,
    borderColor: '#d6d0c4',
    backgroundColor: '#fbfaf5',
    alignItems: 'center',
    justifyContent: 'center',
  },
  colorSwatch: {
    width: 14,
    height: 14,
    borderRadius: 7,
  },
  controlText: {
    fontSize: 12,
    color: '#3e3c37',
    fontWeight: '600',
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
