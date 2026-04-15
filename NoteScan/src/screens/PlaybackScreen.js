import React, { useState, useEffect, useRef, useCallback, useMemo } from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Pressable,
  ScrollView,
  Alert,
  Platform,
  StatusBar,
  Modal,
  FlatList,
  Animated,
  Easing,
} from 'react-native';
import { File, Paths } from 'expo-file-system/next';
import * as Sharing from 'expo-sharing';
import Slider from '@react-native-community/slider';
import { Feather } from '@expo/vector-icons';
import { WebView } from 'react-native-webview';
import { useSafeAreaInsets } from 'react-native-safe-area-context';
import { AudioPlaybackService } from '../services/AudioPlaybackService';
import { PlaybackVisualization } from '../components/PlaybackVisualization';
import { RenderedScoreView } from '../components/RenderedScoreView';
import { ScoreInfoPanel } from '../components/ScoreInfoPanel';
import { OMRSettings } from '../services/OMRSettings';
import { OMRCacheService } from '../services/OMRCacheService';

/* ─── Theme ─── */
const palette = {
  background: '#F9F7F1',
  surface: '#FBFAF5',
  surfaceStrong: '#F1EEE4',
  border: '#D6D0C4',
  ink: '#3E3C37',
  inkMuted: '#6E675E',
};

const barPalette = {
  bar: '#1C1B19',
  barRaised: '#2A2925',
  barBorder: '#3C3A35',
  barText: '#F3F1EA',
  barTextMuted: '#C8C4BA',
  accent: '#E05A2A',
};

// Temporary debug switch: disable pitch panel to isolate playback behavior.
const ENABLE_PITCH_VIEW = false;
const AUDIO_TIMELINE_MODE = 'canonical';
const RENDERED_PLAYHEAD_MODES = ['line', 'notes', 'both'];
const RENDERED_HIGHLIGHT_COLORS = ['#F08A45', '#C94B1A', '#6B4226', '#8B5E3C'];
const RENDER_VIEW_PRESETS = ['smart', 'fit'];
const RENDER_VISUAL_LEAD_MS = 0;
const PLAYBACK_UI_UPDATE_MS = 16;


const loadingEyeHtml = `
<!doctype html>
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <style>
      html, body {
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        background: transparent;
        overflow: hidden;
      }
      @keyframes blink {
        0%, 88%, 100% { transform: scaleY(1); }
        92%, 96% { transform: scaleY(0.04); }
      }
      @keyframes drift {
        0%, 25% { transform: translate(0,0); }
        30%, 55% { transform: translate(5px,-3px); }
        60%, 85% { transform: translate(-4px,3px); }
        90%, 100% { transform: translate(0,0); }
      }
      @keyframes pulseRing {
        0% { r: 22; opacity: 0.9; stroke-width: 2.5; }
        100% { r: 44; opacity: 0; stroke-width: 0.5; }
      }
      .wrap {
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .eye {
        animation: blink 6s ease-in-out infinite;
        transform-origin: 41px 41px;
      }
      .pupil {
        animation: drift 8s cubic-bezier(.45,.05,.55,.95) infinite;
        transform-origin: 41px 41px;
      }
      #noteLayer {
        opacity: 0;
        transition: opacity 0.35s ease;
        transform-box: fill-box;
        transform-origin: center;
      }
      #noteTxt {
        transition: fill 0.25s ease, font-size 0.2s ease;
      }
    </style>
  </head>
  <body>
    <div class="wrap">
      <svg width="110" height="82" viewBox="0 0 82 82" style="overflow:visible;">
        <defs><clipPath id="loadingMainClip"><path d="M4,41 Q41,-8 78,41 Q41,90 4,41Z"/></clipPath></defs>
        <g class="eye">
          <path d="M4,41 Q41,-8 78,41 Q41,90 4,41Z" fill="#faf7f2" stroke="#2a1e10" stroke-width="1.5"/>
          <g clip-path="url(#loadingMainClip)">
            <circle cx="41" cy="41" r="22" fill="#e8dcc8"/>
            <circle cx="41" cy="41" r="22" fill="none" stroke="#2a1e10" stroke-width="1"/>
            <circle id="pr" cx="41" cy="41" r="22" fill="none" stroke="#c9a96e" stroke-width="2" opacity="0"/>
            <g class="pupil">
              <circle cx="41" cy="41" r="13" fill="#2a1e10"/>
              <g id="noteLayer">
                <text id="noteTxt" x="41" y="45" text-anchor="middle" dominant-baseline="middle" font-family="Georgia,serif" font-size="16" fill="white">♪</text>
              </g>
              <circle cx="35" cy="34" r="2.5" fill="white" opacity="0.85"/>
            </g>
          </g>
          <path d="M4,41 Q41,-8 78,41" fill="none" stroke="#2a1e10" stroke-width="1.5" stroke-linecap="round"/>
          <path d="M4,41 Q41,90 78,41" fill="none" stroke="#2a1e10" stroke-width="1" stroke-linecap="round"/>
        </g>
      </svg>
    </div>

    <script>
      const NOTES = ['♪','♫','♩','♬'];
      let idx = 0;

      function runCycle(noteIdx) {
        const nl = document.getElementById('noteLayer');
        const nt = document.getElementById('noteTxt');
        const pr = document.getElementById('pr');
        if (!nl || !nt) return;

        const sym = NOTES[noteIdx % NOTES.length];
        const normalFill = 'white';
        const detectFill = '#f0b840';

        nl.style.transition = 'none';
        nl.style.transform = 'translateX(16px)';
        nl.style.opacity = '0';
        nt.textContent = sym;
        nt.style.fill = normalFill;
        nt.style.fontSize = '16px';

        requestAnimationFrame(() => requestAnimationFrame(() => {
          nl.style.transition = 'opacity 0.38s ease, transform 0.42s cubic-bezier(.22,.68,0,1.2)';
          nl.style.transform = 'translateX(0)';
          nl.style.opacity = '1';
        }));

        setTimeout(() => {
          nt.style.fill = detectFill;
          nt.style.fontSize = '19px';
          if (pr) { pr.style.animation = 'none'; pr.style.opacity = '0'; }
          requestAnimationFrame(() => { if (pr) pr.style.animation = 'pulseRing 0.85s ease-out forwards'; });
        }, 680);

        setTimeout(() => {
          nt.style.fill = normalFill;
          nt.style.fontSize = '16px';
          nl.style.transition = 'opacity 0.35s ease, transform 0.4s cubic-bezier(.55,0,1,.45)';
          nl.style.transform = 'translateX(-16px)';
          nl.style.opacity = '0';
        }, 1550);
      }

      function tick() {
        runCycle(idx);
        idx = (idx + 1) % NOTES.length;
      }

      tick();
      setInterval(tick, 2800);
    </script>
  </body>
</html>
`;

function buildPitchTimelineFromRawEvents(noteEvents, tempoBpm) {
  if (!Array.isArray(noteEvents) || !noteEvents.length || !Number.isFinite(tempoBpm) || tempoBpm <= 0) {
    return [];
  }
  const spb = 60 / tempoBpm;
  return noteEvents
    .map((e) => ({
      time: e.beatOffset * spb,
      endTime: (e.beatOffset + e.durationBeats) * spb,
      midiNote: e.midiNote,
      voice: e.voice,
    }))
    .sort((a, b) => a.time - b.time);
}

function normalizeVoiceLabel(voice) {
  const raw = String(voice || '').trim().toLowerCase();
  if (!raw) return '';
  if (raw === 's' || raw === 'soprano' || raw.includes('soprano')) return 'Soprano';
  if (raw === 'a' || raw === 'alto' || raw.includes('alto')) return 'Alto';
  if (raw === 't' || raw === 'tenor' || raw.includes('tenor')) return 'Tenor';
  if (raw === 'b' || raw === 'bass' || raw.includes('bass')) return 'Bass';
  return '';
}

function isVoiceAllowed(voiceSelection, entryVoice) {
  if (!voiceSelection || Object.values(voiceSelection).every(Boolean)) return true;
  const canonical = normalizeVoiceLabel(entryVoice);
  if (canonical && Object.prototype.hasOwnProperty.call(voiceSelection, canonical)) {
    return !!voiceSelection[canonical];
  }
  return true;
}

function getLiteralPlaybackNotes(scoreData) {
  return scoreData?.notes || [];
}

/* ─── Component ─── */
export const PlaybackScreen = ({ imageUri, scoreData: incomingScoreData, scoreEntry, onNavigateBack, onScoredSaved }) => {
  const insets = useSafeAreaInsets();
  /* ── State ── */
  const [scoreData, setScoreData] = useState(incomingScoreData || null);
  const [scoreError, setScoreError] = useState(null);
  const [processing, setProcessing] = useState(!incomingScoreData); // Don't process if scoreData already provided
  const [processingStage, setProcessingStage] = useState('');
  const [scanProgress, setScanProgress] = useState(0);

  const [isPlaying, setIsPlaying] = useState(false);
  const [isPaused, setIsPaused] = useState(false);
  const [preparing, setPreparing] = useState(false);
  const [tempo, setTempo] = useState(incomingScoreData?.metadata?.tempo || 120);
  const [sliderTempo, setSliderTempo] = useState(incomingScoreData?.metadata?.tempo || 120);
  const [showTempoSlider, setShowTempoSlider] = useState(false);
  const [pitchHz, setPitchHz] = useState(440);
  const [pitchSliderHz, setPitchSliderHz] = useState(440);
  const [showPitchSlider, setShowPitchSlider] = useState(false);

  const [playbackTime, setPlaybackTime] = useState(0);
  const [totalDuration, setTotalDuration] = useState(0);
  const [seekSliderValue, setSeekSliderValue] = useState(0);
  const [isSeekSliding, setIsSeekSliding] = useState(false);
  const [preparingStatusText, setPreparingStatusText] = useState('');

  // Instrument preset selection
  const [availablePresets, setAvailablePresets] = useState([]);
  const [selectedPresetIndex, setSelectedPresetIndex] = useState(0);
  const [showInstrumentPicker, setShowInstrumentPicker] = useState(false);

  // Cursor data
  const [pitchTimeline, setPitchTimeline] = useState([]);
  const [scoreViewMode, setScoreViewMode] = useState('rendered');
  const [renderedPlayheadMode, setRenderedPlayheadMode] = useState('notes');
  const [renderedHighlightColorIdx, setRenderedHighlightColorIdx] = useState(0);
  const [renderViewPreset, setRenderViewPreset] = useState('fit');
  const [preparedPlaybackEvents, setPreparedPlaybackEvents] = useState([]);
  const [preparedTimepointGraph, setPreparedTimepointGraph] = useState([]);
  const [preparedTotalBeats, setPreparedTotalBeats] = useState(0);

  const audioFileUriRef = useRef(null);
  const mixedVoiceSelectionKeyRef = useRef('');
  const prepareIdRef = useRef(0);
  const renderTempoRef = useRef(incomingScoreData?.metadata?.tempo || 120);
  const webAudioReadyRef = useRef(false);
  const preparedScoreRef = useRef(null);
  const fallbackWavPreparedRef = useRef(false);
  const fallbackRenderSignatureRef = useRef('');
  const rawNoteEventsRef = useRef([]);
  const renderedScoreRef = useRef(null);
  const lastPlaybackUiUpdateRef = useRef(0);
  const lastVisualTimeRef = useRef(0);
  const transportAnchorSecRef = useRef(0);
  const transportAnchorTsRef = useRef(0);
  const loadingBarAnim = useRef(new Animated.Value(0)).current;
  const currentScanIdRef = useRef(0);

  const [voiceSelection, setVoiceSelection] = useState({
    Soprano: true,
    Alto: true,
    Tenor: true,
    Bass: true,
  });

  const effectiveVoiceSelection = useMemo(() => {
    return voiceSelection;
  }, [voiceSelection]);

  const playbackVoiceSelection = useMemo(() => {
    return effectiveVoiceSelection;
  }, [effectiveVoiceSelection]);

  const allVisibleVoicesSelected = useMemo(() => {
    return Object.values(voiceSelection).every(Boolean);
  }, [voiceSelection]);

  const activeClefPreset = useMemo(() => {
    const s = !!voiceSelection.Soprano;
    const a = !!voiceSelection.Alto;
    const t = !!voiceSelection.Tenor;
    const b = !!voiceSelection.Bass;
    if (s && a && t && b) return 'both';
    if (s && a && !t && !b) return 'upper';
    if (!s && !a && t && b) return 'lower';
    return 'custom';
  }, [voiceSelection]);

  const getVoiceSelectionKey = useCallback((selection) => {
    const ordered = ['Soprano', 'Alto', 'Tenor', 'Bass'];
    return ordered.map((voice) => (selection?.[voice] ? '1' : '0')).join('');
  }, []);

  const getSelectedVoiceNames = useCallback((selection) => {
    const ordered = ['Soprano', 'Alto', 'Tenor', 'Bass'];
    const active = ordered.filter((voice) => !!selection?.[voice]);
    if (active.length === 0) return 'selected voice';
    if (active.length === 1) return active[0];
    if (active.length === 2) return `${active[0]} and ${active[1]}`;
    return `${active.slice(0, -1).join(', ')}, and ${active[active.length - 1]}`;
  }, []);

  const commitVoiceSelection = useCallback((nextSelection) => {
    setVoiceSelection(nextSelection);
  }, []);

  const updateVoiceSelectionForClef = useCallback((updater) => {
    setVoiceSelection((prev) => {
      const next = updater({
        Soprano: !!prev.Soprano,
        Alto: !!prev.Alto,
        Tenor: !!prev.Tenor,
        Bass: !!prev.Bass,
      });
      return next;
    });
  }, []);

  const changeClefFilter = useCallback((nextFilter) => {
    mixedVoiceSelectionKeyRef.current = '';
    if (nextFilter === 'upper') {
      commitVoiceSelection({ Soprano: true, Alto: true, Tenor: false, Bass: false });
      return;
    }
    if (nextFilter === 'lower') {
      commitVoiceSelection({ Soprano: false, Alto: false, Tenor: true, Bass: true });
      return;
    }
    commitVoiceSelection({ Soprano: true, Alto: true, Tenor: true, Bass: true });
  }, [commitVoiceSelection]);

  const scanningFileName = useMemo(() => {
    if (!imageUri) return 'Selected file';
    try {
      const raw = decodeURIComponent(String(imageUri).split('/').pop() || 'Selected file');
      if (raw.length <= 30) return raw;
      return `${raw.slice(0, 14)}...${raw.slice(-12)}`;
    } catch (_) {
      const raw = String(imageUri).split('/').pop() || 'Selected file';
      if (raw.length <= 30) return raw;
      return `${raw.slice(0, 14)}...${raw.slice(-12)}`;
    }
  }, [imageUri]);

  const headerTitle = useMemo(() => {
    const raw = String(scoreData?.metadata?.title || '').trim();
    if (!raw) return 'Score Viewer';

    const normalized = raw
      .replace(/[_-]+/g, ' ')
      .replace(/\s+/g, ' ')
      .trim();

    if (/^scanned score/i.test(normalized)) {
      return 'Scanned Score';
    }

    if (normalized.length > 34) {
      return `${normalized.slice(0, 31)}...`;
    }

    return normalized;
  }, [scoreData?.metadata?.title]);

  useEffect(() => {
    AudioPlaybackService.setPitchHz(pitchHz);
    fallbackWavPreparedRef.current = false;
    fallbackRenderSignatureRef.current = '';

    if (isPlaying || isPaused) {
      AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);
      setPlaybackTime(0);
      lastVisualTimeRef.current = 0;
      transportAnchorSecRef.current = 0;
      transportAnchorTsRef.current = 0;
      renderedScoreRef.current?.resetPlayback?.();
    }
  }, [pitchHz]);

  useEffect(() => {
    if (!processing) {
      setScanProgress(0);
      loadingBarAnim.setValue(0);
      return;
    }

    const barAnim = Animated.timing(loadingBarAnim, {
      toValue: scanProgress,
      duration: 240,
      easing: Easing.out(Easing.quad),
      useNativeDriver: false,
    });

    barAnim.start();

    return () => {
      barAnim.stop();
    };
  }, [processing, scanProgress, loadingBarAnim]);

  useEffect(() => {
    if (!processing) return;
    const t = setInterval(() => {
      setScanProgress((prev) => {
        if (prev >= 0.95) return prev;
        return Math.min(0.95, prev + 0.012);
      });
    }, 90);
    return () => clearInterval(t);
  }, [processing]);

  const cancelScan = useCallback(() => {
    // Invalidate any in-flight async scan work and return control to user immediately.
    currentScanIdRef.current += 1;
    setProcessing(false);
    setProcessingStage('');
    setScoreError('Scan cancelled by user');
  }, []);

  const updateScanProgress = useCallback((next) => {
    if (!Number.isFinite(next)) return;
    setScanProgress((prev) => Math.max(prev, Math.min(1, next)));
  }, []);

  /* ── Process score via selected OMR engine (with caching) ── */
  const processScore = useCallback(async () => {
    const scanId = currentScanIdRef.current + 1;
    currentScanIdRef.current = scanId;
    const isCancelled = () => currentScanIdRef.current !== scanId;

    if (!imageUri) {
      setProcessing(false);
      setScoreError('No image selected');
      return;
    }
    setProcessing(true);
    setScoreError(null);
    setScanProgress(0.04);
    const selectedEngine = OMRSettings.getEngine();

    const ENGINE_LABELS = {
      zemsky: 'Music eye',
    };

    const ENGINE_TIMEOUTS = {
      zemsky: {
        baseMs: 120000,
        perMbMs: 15000,
        maxMs: 420000,
        stallMs: 180000,
      },
    };

    const runEngine = async (engineKey, isFallback = false) => {
      const engineName = ENGINE_LABELS[engineKey] || engineKey;
      const timeoutCfg = ENGINE_TIMEOUTS[engineKey] || {
        baseMs: 120000,
        perMbMs: 10000,
        maxMs: 360000,
        stallMs: 90000,
      };

      const imageBytes = (() => {
        try {
          const f = new File(imageUri);
          return Number.isFinite(f.size) ? f.size : 0;
        } catch (_) {
          return 0;
        }
      })();
      const imageMb = imageBytes > 0 ? imageBytes / (1024 * 1024) : 0;
      const hardTimeoutMs = Math.min(
        timeoutCfg.maxMs,
        timeoutCfg.baseMs + Math.round(imageMb * timeoutCfg.perMbMs)
      );
      const stallTimeoutMs = timeoutCfg.stallMs;

      if (isFallback) {
        setProcessingStage(`Primary engine failed. Trying ${engineName}...`);
      } else {
        setProcessingStage(`Starting ${engineName}...`);
      }

      let service;
      if (engineKey === selectedEngine) {
        service = OMRSettings.getService();
      } else if (engineKey === 'zemsky') {
        const { ZemskyEmulatorService } = require('../services/ZemskyEmulatorService');
        service = ZemskyEmulatorService;
      } else {
        service = OMRSettings.getService();
      }

      // Skip blocking health probes here. They can add multi-second delays
      // when endpoint probing retries; processSheet already handles connectivity.

      let watchdog = null;
      let lastActivityAt = Date.now();
      const startedAt = Date.now();

      const reportProgressStage = (stage) => {
        if (isCancelled()) return;
        lastActivityAt = Date.now();

        const raw = String(stage || '');
        const lower = raw.toLowerCase();
        let displayStage = raw;

        if (lower.includes('processing') || lower.includes('starting')) {
          displayStage = 'Loading image data...';
        } else if (lower.includes('staff')) {
          displayStage = '🎼 Detecting staff lines...';
        } else if (lower.includes('symbol') || lower.includes('detect')) {
          displayStage = '📍 Classifying notes & symbols...';
        } else if (lower.includes('note')) {
          displayStage = '🎵 Extracting note values...';
        } else if (lower.includes('musicxml') || lower.includes('building')) {
          displayStage = '✓ Building MusicXML...';
        }
        setProcessingStage(displayStage);
      };

      const watchdogPromise = new Promise((_, rej) => {
        watchdog = setInterval(() => {
          if (isCancelled()) {
            rej(new Error('Scan cancelled'));
            return;
          }
          const now = Date.now();
          const totalElapsed = now - startedAt;
          const idleElapsed = now - lastActivityAt;

          if (idleElapsed > stallTimeoutMs) {
            rej(new Error(
              `${engineName} appears stalled (no progress for ${Math.round(stallTimeoutMs / 1000)}s)`
            ));
            return;
          }

          if (totalElapsed > hardTimeoutMs) {
            rej(new Error(
              `${engineName} timed out after ${Math.round(hardTimeoutMs / 1000)}s`
            ));
          }
        }, 1000);
      });

      let result;
      try {
        result = await Promise.race([
          service.processSheet(imageUri, reportProgressStage),
          watchdogPromise,
        ]);
        if (isCancelled()) throw new Error('Scan cancelled');
      } finally {
        if (watchdog) clearInterval(watchdog);
      }

      const totalEvents = result.notes?.length || 0;
      const noteCount = result.notes?.filter((n) => n.type === 'note').length || 0;
      const restCount = result.notes?.filter((n) => n.type === 'rest').length || 0;
      console.log(`🎼 ${engineName} result: ${noteCount} notes, ${restCount} rests (${totalEvents} events), source=${result.metadata?.source}`);
      return { result, engineKey, engineName };
    };

    // ── Check cache first ──
    const CACHE_LOOKUP_TIMEOUT_MS = 1500;
    let skipCacheForThisRun = false;
    setProcessingStage('Checking cache...');
    try {
      const cached = await Promise.race([
        OMRCacheService.get(imageUri, selectedEngine),
        new Promise((_, rej) => setTimeout(() => rej(new Error('Cache lookup timed out')), CACHE_LOOKUP_TIMEOUT_MS)),
      ]);
      if (isCancelled()) return;
      if (cached && cached.notes && cached.notes.length > 0) {
        const cachedEngineName = ENGINE_LABELS[selectedEngine] || selectedEngine;
        const cachedNotes = cached.notes.filter((n) => n.type === 'note').length;
        const cachedRests = cached.notes.filter((n) => n.type === 'rest').length;
        console.log(`🗂️ Cache hit — ${cachedNotes} notes, ${cachedRests} rests (${cached.notes.length} events), skipping ${cachedEngineName}`);
        setScoreData(cached);
        setScanProgress(1);
        setProcessing(false);
        setProcessingStage('');
        return;
      }
    } catch (e) {
      console.warn('Cache lookup failed or stalled, proceeding with OMR:', e.message);
      skipCacheForThisRun = true;
      setProcessingStage('Cache slow. Continuing scan...');
    }

    // ── No cache — run OMR engine ──
    try {
      let run = await runEngine(selectedEngine, false);
      if (isCancelled()) return;

      if (!run.result?.notes || run.result.notes.length === 0) {
        throw new Error(`${run.engineName} could not detect any notes`);
      }

      setProcessingStage('✓ Finalizing score...');
      updateScanProgress(1);

      const detectedTempo = Number(run.result?.metadata?.tempo);
      if (Number.isFinite(detectedTempo) && detectedTempo >= 40 && detectedTempo <= 240) {
        setSliderTempo(Math.round(detectedTempo));
        setTempo(Math.round(detectedTempo));
      }

      setScoreData(run.result);
      if (!skipCacheForThisRun) {
        OMRCacheService.set(imageUri, run.engineKey, run.result).catch(() => {});
      }
    } catch (e) {
      if ((e?.message || '') === 'Scan cancelled') {
        return;
      }
      console.warn(`Primary engine (${selectedEngine}) failed:`, e?.message || e);
      setScoreError(e?.message || 'Failed to process music sheet');
    } finally {
      if (isCancelled()) return;
      // Let users see the bar complete before switching away from the loading UI.
      setScanProgress(1);
      await new Promise((resolve) => setTimeout(resolve, 260));
      setProcessing(false);
      setProcessingStage('');
    }
  }, [imageUri, updateScanProgress]);

  useEffect(() => {
    // Only process image if imageUri is provided; skip if scoreData was already loaded
    if (imageUri && !incomingScoreData) {
      processScore();
    }
    // Load SoundFont for high-quality playback (non-blocking)
    AudioPlaybackService.loadSoundFont(
      require('../../assets/SheetMusicScanner.sf2')
    ).then(() => {
      const presets = AudioPlaybackService.getAvailablePresets();
      if (presets.length > 0) {
        setAvailablePresets(presets);
        setSelectedPresetIndex(0);
      }
    });
  }, [processScore, imageUri, incomingScoreData]);

  useEffect(() => {
    if (scoreViewMode !== 'rendered') return;
    renderedScoreRef.current?.resetPlayback?.();
  }, [scoreViewMode]);

  useEffect(() => {
    if (scoreViewMode !== 'rendered') return;
    renderedScoreRef.current?.setVoiceSelection?.(effectiveVoiceSelection);
  }, [scoreViewMode, effectiveVoiceSelection]);

  /* ── Phase 1: Prepare note events when scoreData or instrument changes ── */
  /* Parse notes once, build event list. No audio rendering here — just data.  */
  useEffect(() => {
    if (!scoreData) return;
    let cancelled = false;

    if (preparedScoreRef.current !== scoreData) {
      preparedScoreRef.current = scoreData;
      fallbackWavPreparedRef.current = false;
      fallbackRenderSignatureRef.current = '';
    }

    AudioPlaybackService.initAudio();

    const doPrepare = async () => {
      const myId = ++prepareIdRef.current;

      // Stop current playback
      await AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);

      setPreparing(true);
      try {
        AudioPlaybackService.selectPreset(selectedPresetIndex);
        renderTempoRef.current = tempo;

        const playbackNotes = getLiteralPlaybackNotes(scoreData);

        // Prepare playback note events through the service path.
        const evtResult = AudioPlaybackService.prepareNoteEvents(playbackNotes, {
          measureBeats: scoreData?.metadata?.measureBeats,
        });
        rawNoteEventsRef.current = AudioPlaybackService.getPreparedNoteEvents();
        setPreparedPlaybackEvents(rawNoteEventsRef.current);
        setPreparedTimepointGraph(AudioPlaybackService.getPreparedTimepointGraph());
        setPreparedTotalBeats(AudioPlaybackService.getPreparedTotalBeats());
        const canUseWebAudio = AudioPlaybackService.isWebAudioAvailable() && !!evtResult;
        if (cancelled || myId !== prepareIdRef.current) return;

        setPitchTimeline(
          ENABLE_PITCH_VIEW
            ? buildPitchTimelineFromRawEvents(rawNoteEventsRef.current, tempo)
            : []
        );

        if (canUseWebAudio) {
          // Web Audio path: note events prepared, build timing map
          AudioPlaybackService._useWebAudio = true;
          webAudioReadyRef.current = true;
          mixedVoiceSelectionKeyRef.current = '';
          const dur = evtResult.totalBeats * (60 / tempo);
          setTotalDuration(dur);
          setPlaybackTime(0);
          fallbackRenderSignatureRef.current = '';
          console.log(`✅ Web Audio ready [${myId}]: ${dur.toFixed(1)}s, ${evtResult.noteEvents.length} events`);
        } else {
          // Legacy WAV path: render once per loaded score for basic fallback playback.
          // UI interactions (voice/tempo/preset changes) should not regenerate WAV.
          webAudioReadyRef.current = false;
          AudioPlaybackService._useWebAudio = false;
          const renderSignature = `${selectedPresetIndex}|${Math.round(pitchHz * 10)}|${tempo}`;
          const needsPreRender = !fallbackWavPreparedRef.current || fallbackRenderSignatureRef.current !== renderSignature;
          if (needsPreRender) {
            const result = await AudioPlaybackService.preRenderVoiceTracks(playbackNotes, tempo, {
              measureBeats: scoreData?.metadata?.measureBeats,
            });
            if (cancelled || myId !== prepareIdRef.current) return;
            if (result) {
              fallbackWavPreparedRef.current = true;
              fallbackRenderSignatureRef.current = renderSignature;
              await doMix(myId, playbackVoiceSelection);
            } else {
              throw new Error('Clean pipeline requires beatOffset timing data; legacy prepare fallback is disabled.');
            }
          } else {
            const fallbackBeats = Number.isFinite(evtResult?.totalBeats)
              ? evtResult.totalBeats
              : 0;
            if (fallbackBeats > 0) {
              setTotalDuration(fallbackBeats * (60 / tempo));
            }
            setPlaybackTime(0);
            await doMix(myId, playbackVoiceSelection);
          }
        }
      } catch (e) {
        console.error(`prepare [${myId}] error:`, e);
        if (myId === prepareIdRef.current) {
          Alert.alert('Error', 'Failed to prepare audio: ' + e.message);
        }
      } finally {
        if (myId === prepareIdRef.current) setPreparing(false);
      }
    };

    doPrepare();

    return () => { cancelled = true; };
  }, [scoreData, selectedPresetIndex, pitchHz]);

  /* ── Phase 2: Voice selection changes ── */
  /* Web Audio: voice selection is applied at play-time.                        */
  /* Legacy WAV fallback: do not regenerate from UI interaction.                */
  useEffect(() => {
    if (!scoreData) return;
    if (webAudioReadyRef.current) {
      // Web Audio path: voice selection is applied at play time, nothing to do
      // If currently playing, reschedule with new voice selection
      if (AudioPlaybackService.isPlaying) {
        AudioPlaybackService.changeTempo(tempo, playbackVoiceSelection);
      }
    } else if (AudioPlaybackService.isPlaying) {
      console.log('ℹ️ Voice selection changed during WAV playback; changes apply on next play session.');
    };
  }, [playbackVoiceSelection, tempo]);

  /** Mix pre-rendered voice buffers into a single WAV based on effective voice selection */
  const doMix = async (myId, selectionOverride = playbackVoiceSelection, statusText = 'Preparing audio...') => {
    audioFileUriRef.current = null;
    mixedVoiceSelectionKeyRef.current = '';
    setPreparing(true);
    setPreparingStatusText(statusText);
    try {
      // Check at least one voice has notes
      const activeVoices = Object.entries(selectionOverride || {})
        .filter(([, v]) => v).map(([k]) => k);
      console.log(`🎵 mix [${myId}]: voices=${activeVoices.join(',')}`);

      const { fileUri, timingMap, totalDuration: dur } =
        await AudioPlaybackService.mixVoiceTracks(selectionOverride);

      if (myId !== prepareIdRef.current) {
        console.log(`🎵 mix [${myId}]: stale, abandoning`);
        return;
      }

      if (!fileUri || !timingMap.length || dur <= 0) {
        Alert.alert('No playable notes', 'No notes detected for playback.');
        audioFileUriRef.current = null;
        setTotalDuration(0);
        setPreparing(false);
        return;
      }

      audioFileUriRef.current = fileUri;
      mixedVoiceSelectionKeyRef.current = getVoiceSelectionKey(selectionOverride);
      setTotalDuration(dur);
      setPlaybackTime(0);

      console.log(`✅ Audio ready [${myId}]: ${dur.toFixed(1)}s, file=${fileUri ? 'OK' : 'MISSING'}`);
      return true;
    } catch (e) {
      console.error(`mix [${myId}] error:`, e);
      if (myId === prepareIdRef.current) {
        audioFileUriRef.current = null;
        Alert.alert('Error', 'Failed to mix audio: ' + e.message);
      }
      return false;
    } finally {
      if (myId === prepareIdRef.current) {
        setPreparing(false);
        setPreparingStatusText('');
      }
    }
  };

  /**
   * Change tempo — instant for Web Audio, re-render for legacy.
   */
  const reRenderForTempo = async (newTempo) => {
    if (!scoreData) return;
    if (newTempo === renderTempoRef.current) return;
    renderTempoRef.current = newTempo;

    if (webAudioReadyRef.current) {
      // Web Audio path: instant tempo change (just reschedule events)
      const result = AudioPlaybackService.changeTempo(newTempo, playbackVoiceSelection);
      if (result) {
        setTotalDuration(result.totalDuration);
        setPitchTimeline(
          ENABLE_PITCH_VIEW
            ? buildPitchTimelineFromRawEvents(rawNoteEventsRef.current, newTempo)
            : []
        );
        setPlaybackTime(0);
      }
      console.log(`✅ Instant tempo change: ${newTempo} BPM`);
      return;
    }

    // Legacy WAV fallback: regenerate mixed audio at the selected tempo.
    if (isPlaying || isPaused) {
      await AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);
    }

    const myId = ++prepareIdRef.current;
    const playbackNotes = getLiteralPlaybackNotes(scoreData);
    const preRender = await AudioPlaybackService.preRenderVoiceTracks(playbackNotes, newTempo, {
      measureBeats: scoreData?.metadata?.measureBeats,
    });

    if (!preRender || myId !== prepareIdRef.current) {
      return;
    }

    fallbackWavPreparedRef.current = true;
    fallbackRenderSignatureRef.current = `${selectedPresetIndex}|${Math.round(pitchHz * 10)}|${newTempo}`;
    setPitchTimeline(
      ENABLE_PITCH_VIEW
        ? buildPitchTimelineFromRawEvents(rawNoteEventsRef.current, newTempo)
        : []
    );
    setPlaybackTime(0);
    await doMix(myId, playbackVoiceSelection, 'Applying tempo...');
    console.log(`✅ Tempo changed to ${newTempo} BPM with WAV regeneration`);
  };

  /* ── Playback controls ── */
  const handlePlay = async () => {
    if (preparing) return;
    AudioPlaybackService.setExternalBeatGuideOnsets(sharedBeatOffsetsForAudio, {
      measureBeats: scoreData?.metadata?.measureBeats,
      totalBeats: graphTotalBeats > 0 ? graphTotalBeats : preparedTotalBeats,
    });

    if (isPaused) {
      transportAnchorSecRef.current = playbackTime;
      transportAnchorTsRef.current = Date.now();
      await AudioPlaybackService.resume(playbackVoiceSelection);
      setIsPlaying(true);
      setIsPaused(false);
      return;
    }

    setIsPlaying(true);
    setIsPaused(false);
    setPlaybackTime(0);
    lastPlaybackUiUpdateRef.current = 0;
    lastVisualTimeRef.current = 0;
    transportAnchorSecRef.current = 0;
    transportAnchorTsRef.current = Date.now();
    renderedScoreRef.current?.resetPlayback?.();

    if (webAudioReadyRef.current) {
      // Web Audio path: schedule all notes, no file needed
      console.log(`▶️ handlePlay: Web Audio at ${tempo} BPM`);
      AudioPlaybackService._onFinished = () => {
        setIsPlaying(false);
        setIsPaused(false);
        updatePlaybackPosition(totalDuration, true);
      };
      AudioPlaybackService.playWebAudio(tempo, playbackVoiceSelection,
        null,
        () => { setIsPlaying(false); setIsPaused(false); updatePlaybackPosition(totalDuration, true); }
      );
      return;
    }

    const desiredVoiceSelectionKey = getVoiceSelectionKey(playbackVoiceSelection);
    if (desiredVoiceSelectionKey !== mixedVoiceSelectionKeyRef.current) {
      const voiceLabel = getSelectedVoiceNames(playbackVoiceSelection);
      const myId = ++prepareIdRef.current;
      const mixed = await doMix(myId, playbackVoiceSelection, `Detecting ${voiceLabel}...`);
      if (!mixed || !audioFileUriRef.current) {
        setIsPlaying(false);
        return;
      }
    }

    // Non-WebAudio transport path: play pre-rendered file via expo-av
    if (!audioFileUriRef.current) {
      console.warn('handlePlay: audioFileUriRef is null');
      Alert.alert('Not Ready', 'Audio is still preparing. Please wait.');
      setIsPlaying(false);
      return;
    }
    const fileUri = audioFileUriRef.current;
    console.log(`▶️ handlePlay: playing ${fileUri}`);
    try {
      await AudioPlaybackService.play(
        fileUri,
        (timeSec) => {
          if (!Number.isFinite(timeSec)) return;
          const next = Math.max(0, timeSec);
          if (next + 0.06 < transportAnchorSecRef.current) return;
          transportAnchorSecRef.current = Math.max(transportAnchorSecRef.current, next);
          transportAnchorTsRef.current = Date.now();
        },
        () => { setIsPlaying(false); setIsPaused(false); updatePlaybackPosition(totalDuration, true); }
      );
    } catch (e) {
      console.error('Play error:', e);
      setIsPlaying(false);
    }
  };

  const handlePause = async () => {
    await AudioPlaybackService.pause();
    transportAnchorSecRef.current = playbackTime;
    transportAnchorTsRef.current = Date.now();
    setIsPlaying(false);
    setIsPaused(true);
  };

  const handleStop = async () => {
    await AudioPlaybackService.stop();
    setIsPlaying(false);
    setIsPaused(false);
    setPlaybackTime(0);
    lastVisualTimeRef.current = 0;
    transportAnchorSecRef.current = 0;
    transportAnchorTsRef.current = 0;
    renderedScoreRef.current?.resetPlayback?.();
  };

  const handleSeek = async (timeSec) => {
    if (isPlaying) {
      await AudioPlaybackService.pause();
      setIsPlaying(false);
      setIsPaused(true);
    }

    AudioPlaybackService.setExternalBeatGuideOnsets(sharedBeatOffsetsForAudio, {
      measureBeats: scoreData?.metadata?.measureBeats,
      totalBeats: graphTotalBeats > 0 ? graphTotalBeats : preparedTotalBeats,
    });
    const clamped = Math.max(0, Math.min(timeSec, totalDuration));
    transportAnchorSecRef.current = clamped;
    transportAnchorTsRef.current = Date.now();
    const renderedTotal = graphTotalDuration > 0 ? graphTotalDuration : totalDuration;
    const seekVisualLead = RENDER_VISUAL_LEAD_MS / 1000;
    lastVisualTimeRef.current = Math.max(
      0,
      Math.min(clamped + seekVisualLead, renderedTotal > 0 ? renderedTotal : clamped + seekVisualLead)
    );
    setPlaybackTime(clamped);

    const beatTotalForRender = playbackTotalBeats;
    if (beatTotalForRender > 0) {
      const beat = clamped / (60 / tempo);
      renderedScoreRef.current?.syncBeat?.(beat, beatTotalForRender);
    } else {
      renderedScoreRef.current?.syncPlayback?.(clamped, totalDuration);
    }
    await AudioPlaybackService.seekTo(clamped, playbackVoiceSelection);
  };

  const toggleVoice = (voice) => {
    // Stop playback so audio re-prepares with new voice selection
    if (isPlaying || isPaused) {
      AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);
      setPlaybackTime(0);
    }
    mixedVoiceSelectionKeyRef.current = '';
    updateVoiceSelectionForClef((prev) => {
      const next = { ...prev, [voice]: !prev[voice] };
      if (!Object.values(next).some(Boolean)) {
        Alert.alert('Select Voice', 'At least one voice must be selected');
        return prev;
      }
      return next;
    });
  };

  const soloVoice = (voice) => {
    // Stop playback so audio re-prepares
    if (isPlaying || isPaused) {
      AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);
      setPlaybackTime(0);
    }
    mixedVoiceSelectionKeyRef.current = '';
    updateVoiceSelectionForClef((prev) => {
      const activeCount = Object.values(prev).filter(Boolean).length;
      const onlyThisActive = prev[voice] && activeCount === 1;

      if (onlyThisActive) {
        // Already solo'd on this voice — re-enable all
        console.log(`🎤 unsolo ${voice} → all voices on`);
        return { Soprano: true, Alto: true, Tenor: true, Bass: true };
      }

      // Solo this voice (deactivate all others)
      console.log(`🎤 solo ${voice}`);
      return {
        Soprano: voice === 'Soprano',
        Alto: voice === 'Alto',
        Tenor: voice === 'Tenor',
        Bass: voice === 'Bass',
      };
    });
  };

  const allVoicesOn = () => {
    if (isPlaying || isPaused) {
      AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);
      setPlaybackTime(0);
    }
    mixedVoiceSelectionKeyRef.current = '';
    commitVoiceSelection({ Soprano: true, Alto: true, Tenor: true, Bass: true });
  };

  /* ── Instrument selection ── */
  const handleSelectInstrument = (presetIndex) => {
    if (isPlaying) return;
    setSelectedPresetIndex(presetIndex);
    setShowInstrumentPicker(false);
  };

  const handleExportMusicXml = async () => {
    const musicXml = scoreData?.musicXml;
    if (!musicXml || musicXml.length < 20) {
      Alert.alert('No MusicXML', 'This scan result does not include exportable MusicXML. Please rescan the score.');
      return;
    }

    try {
      const titleBase = (scoreData?.metadata?.title || 'notescan_score')
        .replace(/[^a-zA-Z0-9_-]+/g, '_')
        .replace(/^_+|_+$/g, '')
        .slice(0, 50) || 'notescan_score';

      const stamp = new Date().toISOString().replace(/[:.]/g, '-');
      const fileName = `${titleBase}_${stamp}.musicxml`;
      const file = new File(Paths.document, fileName);

      file.write(musicXml);

      const canShare = await Sharing.isAvailableAsync();
      if (!canShare) {
        Alert.alert('Exported', `Saved MusicXML to: ${file.uri}`);
        return;
      }

      await Sharing.shareAsync(file.uri, {
        mimeType: 'application/vnd.recordare.musicxml+xml',
        dialogTitle: 'Export MusicXML',
        UTI: 'public.xml',
      });
    } catch (e) {
      console.error('MusicXML export failed:', e);
      Alert.alert('Export failed', e?.message || 'Could not export MusicXML file.');
    }
  };

  const handleExportWav = async () => {
    if (!scoreData?.notes?.length) {
      Alert.alert('No score data', 'No notes detected for WAV export. Please rescan the score.');
      return;
    }

    const playbackNotes = getLiteralPlaybackNotes(scoreData);

    try {
      setPreparing(true);
      setPreparingStatusText('Preparing WAV export...');
      await AudioPlaybackService.stop();
      setIsPlaying(false);
      setIsPaused(false);

      const preRender = await AudioPlaybackService.preRenderVoiceTracks(playbackNotes, tempo, {
        measureBeats: scoreData?.metadata?.measureBeats,
      });
      if (!preRender) {
        throw new Error('Cannot export WAV because beatOffset timing data is unavailable.');
      }

      const { fileUri } = await AudioPlaybackService.mixVoiceTracks(effectiveVoiceSelection);
      if (!fileUri) {
        throw new Error('WAV export failed to produce an output file.');
      }

      const canShare = await Sharing.isAvailableAsync();
      if (!canShare) {
        Alert.alert('Exported', `Saved WAV to: ${fileUri}`);
        return;
      }

      await Sharing.shareAsync(fileUri, {
        mimeType: 'audio/wav',
        dialogTitle: 'Export WAV',
        UTI: 'com.microsoft.waveform-audio',
      });
    } catch (e) {
      console.error('WAV export failed:', e);
      Alert.alert('Export failed', e?.message || 'Could not export WAV file.');
    } finally {
      setPreparing(false);
      setPreparingStatusText('');
    }
  };

  const currentInstrumentName = availablePresets[selectedPresetIndex]?.name || 'Piano';
  const pitchShiftLabel = `${Math.round(pitchHz)} Hz`;
  const timepointGraph = useMemo(() => {
    return Array.isArray(preparedTimepointGraph) ? preparedTimepointGraph : [];
  }, [preparedTimepointGraph]);
  const graphTotalBeats = useMemo(() => {
    if (!Array.isArray(timepointGraph) || timepointGraph.length === 0) return 0;
    let maxBeat = 0;
    for (const tp of timepointGraph) {
      const base = Number.isFinite(tp?.beatOffset) ? tp.beatOffset : 0;
      const barEnd = Number.isFinite(tp?.barEndBeat) ? tp.barEndBeat : null;
      maxBeat = Math.max(maxBeat, base);
      if (Number.isFinite(barEnd)) {
        maxBeat = Math.max(maxBeat, barEnd);
      }
    }
    return maxBeat;
  }, [timepointGraph]);
  const graphTotalDuration = useMemo(() => {
    if (!Number.isFinite(graphTotalBeats) || graphTotalBeats <= 0) return 0;
    return graphTotalBeats * (60 / tempo);
  }, [graphTotalBeats, tempo]);
  const playbackTotalBeats = useMemo(() => {
    if (Number.isFinite(totalDuration) && totalDuration > 0 && Number.isFinite(tempo) && tempo > 0) {
      return totalDuration / (60 / tempo);
    }
    if (Number.isFinite(preparedTotalBeats) && preparedTotalBeats > 0) {
      return preparedTotalBeats;
    }
    if (Number.isFinite(graphTotalBeats) && graphTotalBeats > 0) {
      return graphTotalBeats;
    }
    return 0;
  }, [totalDuration, tempo, preparedTotalBeats, graphTotalBeats]);
  const sharedBeatOffsets = useMemo(() => {
    if (!Array.isArray(timepointGraph) || timepointGraph.length === 0) return [];
    return timepointGraph
      .filter((tp) => (tp.sounds || []).some((s) => isVoiceAllowed(effectiveVoiceSelection, s.voice)))
      .map((tp) => tp.beatOffset)
      .sort((a, b) => a - b);
  }, [timepointGraph, effectiveVoiceSelection]);
  const highlightBuckets = useMemo(() => {
    if (!Array.isArray(timepointGraph) || timepointGraph.length === 0) return [];
    return timepointGraph
      .map((tp) => {
        const ids = [];
        for (const s of tp.sounds || []) {
          if (!s.elementId) continue;
          if (!isVoiceAllowed(effectiveVoiceSelection, s.voice)) continue;
          if (!ids.includes(s.elementId)) ids.push(s.elementId);
        }
        return { beatOffset: tp.beatOffset, elementIds: ids };
      })
      .filter((bucket) => Number.isFinite(bucket.beatOffset) && bucket.elementIds.length > 0)
      .sort((a, b) => a.beatOffset - b.beatOffset);
  }, [timepointGraph, effectiveVoiceSelection]);
  const sharedBeatOffsetsForAudio = useMemo(() => {
    return highlightBuckets.map((bucket) => bucket.beatOffset);
  }, [highlightBuckets]);

  useEffect(() => {
    AudioPlaybackService.setExternalBeatGuideOnsets(sharedBeatOffsetsForAudio, {
      measureBeats: scoreData?.metadata?.measureBeats,
      totalBeats: graphTotalBeats > 0 ? graphTotalBeats : preparedTotalBeats,
    });
  }, [sharedBeatOffsetsForAudio, scoreData, preparedTotalBeats, graphTotalBeats]);

  useEffect(() => {
    const beatTotalForRender = playbackTotalBeats;
    if (!renderedScoreRef.current?.setHighlightBuckets) return;
    renderedScoreRef.current.setHighlightBuckets(highlightBuckets, beatTotalForRender);
  }, [highlightBuckets, playbackTotalBeats]);

  const updatePlaybackPosition = useCallback((timeSec, force = false) => {
    const audioTotal = totalDuration || timeSec || 0;
    const clamped = Math.max(0, Math.min(timeSec, audioTotal));
    const renderedTotal = graphTotalDuration > 0 ? graphTotalDuration : totalDuration;
    const atAudioEnd = audioTotal > 0 && clamped >= audioTotal - 0.02;
    const renderBaseTime =
      force && atAudioEnd && renderedTotal > 0
        ? renderedTotal
        : Math.max(0, Math.min(timeSec, renderedTotal || timeSec || 0));
    const visualLeadSec = RENDER_VISUAL_LEAD_MS / 1000;
    const visualTimeSecRaw = Math.max(
      0,
      Math.min(
        renderBaseTime + visualLeadSec,
        renderedTotal > 0 ? renderedTotal : (renderBaseTime + visualLeadSec)
      )
    );
    const visualTimeSec = force
      ? visualTimeSecRaw
      : Math.max(visualTimeSecRaw, lastVisualTimeRef.current);
    lastVisualTimeRef.current = visualTimeSec;
    const secondsPerBeat = 60 / tempo;
    const beatForRender = visualTimeSec / secondsPerBeat;

    const beatTotalForRender = playbackTotalBeats;
    if (renderedScoreRef.current?.syncBeat && beatTotalForRender > 0) {
      renderedScoreRef.current.syncBeat(beatForRender, beatTotalForRender);
    } else if (renderedScoreRef.current?.syncPlayback && renderedTotal > 0) {
      renderedScoreRef.current.syncPlayback(visualTimeSec, renderedTotal);
    }

    const now = Date.now();
    if (force || now - lastPlaybackUiUpdateRef.current >= PLAYBACK_UI_UPDATE_MS) {
      lastPlaybackUiUpdateRef.current = now;
      setPlaybackTime(clamped);
    }
  }, [
    totalDuration,
    graphTotalDuration,
    tempo,
    playbackTotalBeats,
  ]);

  useEffect(() => {
    if (!isPlaying) return;
    let frame = null;

    const tick = () => {
      if (!AudioPlaybackService.isPlaying) {
        frame = requestAnimationFrame(tick);
        return;
      }

      let timeSec = 0;
      if (webAudioReadyRef.current) {
        timeSec = AudioPlaybackService.getPlaybackPositionSec();
      } else {
        const base = transportAnchorSecRef.current;
        const anchorTs = transportAnchorTsRef.current;
        const dtSec = anchorTs > 0
          ? Math.min(0.35, Math.max(0, (Date.now() - anchorTs) / 1000))
          : 0;
        timeSec = base + dtSec;
      }

      if (Number.isFinite(timeSec)) {
        updatePlaybackPosition(timeSec);
      }
      frame = requestAnimationFrame(tick);
    };

    frame = requestAnimationFrame(tick);
    return () => {
      if (frame != null) cancelAnimationFrame(frame);
    };
  }, [isPlaying, updatePlaybackPosition]);

  // Compute current beat from the prepared rule-based event timeline.
  const currentBeat = playbackTime / (60 / tempo);
  
  // Prefer graph-driven progress when available; otherwise fall back to audio duration.
  const renderProgress = (graphTotalDuration > 0)
    ? Math.min(1, playbackTime / graphTotalDuration)
    : (totalDuration > 0 ? Math.min(1, playbackTime / totalDuration) : 0);
  const renderedProgressProp = isPlaying ? Number.NaN : renderProgress;
  const seekMaxDuration = (Number.isFinite(totalDuration) && totalDuration > 0)
    ? totalDuration
    : ((Number.isFinite(graphTotalDuration) && graphTotalDuration > 0) ? graphTotalDuration : 0);
  const seekDisplayTime = isSeekSliding ? seekSliderValue : playbackTime;
  const seekSliderRenderValue = isSeekSliding ? seekSliderValue : playbackTime;

  const previewSeekCursor = useCallback((previewTimeSec) => {
    if (scoreViewMode !== 'rendered') return;
    if (!Number.isFinite(previewTimeSec)) return;

    const renderedTotal = graphTotalDuration > 0 ? graphTotalDuration : totalDuration;
    const clamped = Math.max(0, Math.min(previewTimeSec, renderedTotal > 0 ? renderedTotal : previewTimeSec));
    const beatTotalForRender = playbackTotalBeats;

    if (beatTotalForRender > 0) {
      const beat = clamped / (60 / tempo);
      renderedScoreRef.current?.syncBeat?.(beat, beatTotalForRender);
    } else if (renderedTotal > 0) {
      renderedScoreRef.current?.syncPlayback?.(clamped, renderedTotal);
    }
  }, [scoreViewMode, graphTotalDuration, totalDuration, playbackTotalBeats, tempo]);

  /* ── Render ── */
  if (processing) {
    const barWidth = loadingBarAnim.interpolate({
      inputRange: [0, 1],
      outputRange: [0, 188],
    });

    return (
      <View style={styles.centerContainer}>
        <StatusBar barStyle="dark-content" backgroundColor={palette.background} />
        <View style={styles.loadingLogoWrap}>
          <WebView
            originWhitelist={["*"]}
            source={{ html: loadingEyeHtml }}
            style={styles.loadingLogoWebView}
            scrollEnabled={false}
            showsHorizontalScrollIndicator={false}
            showsVerticalScrollIndicator={false}
          />
        </View>

        <Text style={styles.scanningText}>Scanning...</Text>
        <Text style={styles.scanningFileText}>{scanningFileName}</Text>
        {processingStage && (
          <Text style={styles.processingStageText}>{processingStage}</Text>
        )}

        <View style={styles.loadingBarTrack}>
          <Animated.View style={[styles.loadingBarPulse, { width: barWidth }]} />
        </View>
        <TouchableOpacity style={styles.cancelScanButton} onPress={cancelScan}>
          <Text style={styles.cancelScanButtonText}>Cancel Scan</Text>
        </TouchableOpacity>
      </View>
    );
  }

  if (scoreError || !scoreData) {
    return (
      <View style={styles.centerContainer}>
        <StatusBar barStyle="dark-content" backgroundColor={palette.background} />
        <Text style={styles.errorTitle}>Unable to load score</Text>
        <Text style={styles.errorText}>{scoreError || 'Unknown error'}</Text>
        <TouchableOpacity style={styles.retryButton} onPress={processScore}>
          <Text style={styles.retryButtonText}>Retry</Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.retryButton, { marginTop: 10 }]} onPress={onNavigateBack}>
          <Text style={styles.retryButtonText}>Back</Text>
        </TouchableOpacity>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor={palette.background} />

      {/* Header */}
      <View
        style={[
          styles.header,
          {
            paddingTop: insets.top + 16,
            paddingLeft: 24 + insets.left,
            paddingRight: 24 + insets.right,
          },
        ]}
      >
        <TouchableOpacity onPress={onNavigateBack}>
          <Text style={styles.linkText}>← Back</Text>
        </TouchableOpacity>
        <Text style={styles.title}>
          {headerTitle}
        </Text>
        <View style={{ width: 60 }} />
      </View>

      {/* Voice controls moved to top for quick access */}
      <View style={[styles.topVoiceBar, { paddingLeft: 12 + insets.left, paddingRight: 12 + insets.right }]}>
        <View style={styles.topVoicePill}>
          <Pressable
            style={({ pressed }) => [
              styles.topVoiceDot,
              allVisibleVoicesSelected ? styles.topVoiceDotAll : styles.topVoiceDotInactive,
              pressed && styles.pressedDot,
            ]}
            onPress={allVoicesOn}
          >
            <Text
              style={[
                styles.topVoiceDotText,
                allVisibleVoicesSelected ? styles.topVoiceDotTextActive : styles.topVoiceDotTextInactive,
              ]}
            >
              All
            </Text>
          </Pressable>
          {Object.keys(voiceSelection).map((voice) => {
            const vc = scoreData?.metadata?.voiceCounts || {};
            const hasVoiceCounts = Object.keys(vc).length > 0;
            const cnt = vc[voice] || 0;
            const isEmpty = hasVoiceCounts && cnt === 0;
            const voiceLabel =
              voice === 'Soprano'
                ? 'Soprano'
                : voice === 'Alto'
                  ? 'Alto'
                  : voice === 'Tenor'
                    ? 'Tenor'
                    : voice === 'Bass'
                      ? 'Bass'
                      : voice;
            return (
              <Pressable
                key={voice}
                style={({ pressed }) => [
                  styles.topVoiceDot,
                  isEmpty
                    ? styles.topVoiceDotEmpty
                    : effectiveVoiceSelection[voice]
                      ? styles.topVoiceDotActive
                      : styles.topVoiceDotInactive,
                  pressed && !isEmpty && styles.pressedDot,
                ]}
                onPress={() => (isEmpty ? null : toggleVoice(voice))}
                onLongPress={() => (isEmpty ? null : soloVoice(voice))}
                disabled={isEmpty}
              >
                <Text
                  style={[
                    styles.topVoiceDotText,
                    isEmpty
                      ? styles.topVoiceDotTextInactive
                      : effectiveVoiceSelection[voice]
                        ? styles.topVoiceDotTextActive
                        : styles.topVoiceDotTextInactive,
                  ]}
                >
                  {voiceLabel}{isEmpty ? ' ⊘' : ''}
                </Text>
              </Pressable>
            );
          })}
        </View>
      </View>

      {/* Score view */}
      <View style={[styles.viewerArea, { marginLeft: 12 + insets.left, marginRight: 12 + insets.right }]}>
        {scoreViewMode === 'rendered' ? (
          <RenderedScoreView
            ref={renderedScoreRef}
            musicXml={scoreData?.musicXml || ''}
            currentBeat={renderedProgressProp}
            playheadMode={renderedPlayheadMode}
            playheadColor={RENDERED_HIGHLIGHT_COLORS[renderedHighlightColorIdx]}
            renderViewPreset={renderViewPreset}
            showControlBar={false}
            onSeekNormalizedBeat={(normalizedBeat) => {
              if (!Number.isFinite(normalizedBeat)) return;
              const seekTotal = Number.isFinite(totalDuration) && totalDuration > 0
                ? totalDuration
                : (Number.isFinite(graphTotalDuration) && graphTotalDuration > 0 ? graphTotalDuration : 0);
              if (seekTotal <= 0) return;
              handleSeek(normalizedBeat * seekTotal);
            }}
            onPlayheadModeChange={setRenderedPlayheadMode}
            onRenderViewPresetChange={setRenderViewPreset}
            onPlayheadColorChange={(color) => {
              const idx = RENDERED_HIGHLIGHT_COLORS.indexOf(color);
              if (idx >= 0) {
                setRenderedHighlightColorIdx(idx);
              }
            }}
          />
        ) : (
          <PlaybackVisualization
            imageUri={scoreData?.processedImageUri || imageUri}
            currentTime={playbackTime}
            totalDuration={totalDuration}
            isPlaying={isPlaying}
            cursorInfo={null}
            onSeek={handleSeek}
            measureBeats={scoreData?.metadata?.measureBeats}
            tempo={tempo}
            pitchTimeline={ENABLE_PITCH_VIEW ? pitchTimeline : []}
            voiceSelection={voiceSelection}
            debugNotes={scoreData?.notes}
            timelineMode={AUDIO_TIMELINE_MODE}
          />
        )}
      </View>

      {/* Tempo slider drawer */}
      {showTempoSlider && (
        <View style={[styles.tempoDrawer, { paddingLeft: 16 + insets.left, paddingRight: 16 + insets.right }]}>
          <View style={styles.tempoDrawerRow}>
            <Text style={styles.tempoDrawerLabel}>Tempo</Text>
            <Text style={styles.tempoDrawerValue}>♩ = {sliderTempo}</Text>
          </View>
          <Slider
            style={styles.tempoSlider}
            minimumValue={40}
            maximumValue={240}
            step={1}
            value={sliderTempo}
            onValueChange={(v) => {
              const bpm = Math.round(v);
              setSliderTempo(bpm);
              // Web Audio: instant live tempo change while dragging
              if (webAudioReadyRef.current) {
                setTempo(bpm);
                reRenderForTempo(bpm);
              }
            }}
            onSlidingComplete={(v) => {
              const bpm = Math.round(v);
              setSliderTempo(bpm);
              setTempo(bpm);
              reRenderForTempo(bpm);
            }}
            minimumTrackTintColor={barPalette.accent}
            maximumTrackTintColor={barPalette.barBorder}
            thumbTintColor={barPalette.accent}
          />
          <View style={styles.tempoPresets}>
            {[
              { label: 'Slow',    bpm: 72 },
              { label: 'Original', bpm: scoreData?.metadata?.tempo || 120 },
              { label: 'Fast',    bpm: 160 },
            ].map((p) => (
              <TouchableOpacity
                key={p.label}
                style={[
                  styles.tempoPresetBtn,
                  Math.abs(tempo - p.bpm) < 10 && styles.tempoPresetBtnActive,
                ]}
                onPress={() => { setSliderTempo(p.bpm); setTempo(p.bpm); reRenderForTempo(p.bpm); }}
              >
                <Text
                  style={[
                    styles.tempoPresetText,
                    Math.abs(tempo - p.bpm) < 10 && styles.tempoPresetTextActive,
                  ]}
                >
                  {p.label}
                </Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>
      )}

      {showPitchSlider && (
        <View style={[styles.tempoDrawer, { paddingLeft: 16 + insets.left, paddingRight: 16 + insets.right }]}>
          <View style={styles.tempoDrawerRow}>
            <Text style={styles.tempoDrawerLabel}>Pitch Ref</Text>
            <Text style={styles.tempoDrawerValue}>{pitchShiftLabel}</Text>
          </View>
          <Slider
            style={styles.tempoSlider}
            minimumValue={380}
            maximumValue={480}
            step={1}
            value={pitchSliderHz}
            onValueChange={(v) => setPitchSliderHz(Math.round(v))}
            onSlidingComplete={(v) => {
              const nextHz = Math.max(380, Math.min(480, Math.round(v)));
              setPitchSliderHz(nextHz);
              if (nextHz !== pitchHz) {
                setPitchHz(nextHz);
              }
            }}
            minimumTrackTintColor={barPalette.accent}
            maximumTrackTintColor={barPalette.barBorder}
            thumbTintColor={barPalette.accent}
          />
          <View style={styles.tempoPresets}>
            {[
              { label: '432', hz: 432 },
              { label: '440', hz: 440 },
              { label: '442', hz: 442 },
              { label: 'Baroque', hz: 415 },
            ].map((p) => (
              <TouchableOpacity
                key={p.label}
                style={[
                  styles.tempoPresetBtn,
                  pitchHz === p.hz && styles.tempoPresetBtnActive,
                ]}
                onPress={() => {
                  setPitchSliderHz(p.hz);
                  setPitchHz(p.hz);
                }}
              >
                <Text
                  style={[
                    styles.tempoPresetText,
                    pitchHz === p.hz && styles.tempoPresetTextActive,
                  ]}
                >
                  {p.label}
                </Text>
              </TouchableOpacity>
            ))}
          </View>
        </View>
      )}

      {/* Timeline seek slider */}
      <View style={[styles.seekBarWrap, { paddingLeft: 14 + insets.left, paddingRight: 14 + insets.right }]}>
        <View style={styles.seekBarHeader}>
          <Text style={styles.seekBarLabel}>Timeline</Text>
          <Text style={styles.seekBarTimeText}>
            {formatTime(seekDisplayTime)} / {formatTime(seekMaxDuration)}
          </Text>
        </View>
        <Slider
          style={styles.seekSlider}
          minimumValue={0}
          maximumValue={Math.max(0.001, seekMaxDuration)}
          value={Math.max(0, Math.min(seekSliderRenderValue, Math.max(0.001, seekMaxDuration)))}
          onSlidingStart={() => {
            setSeekSliderValue(Math.max(0, Math.min(playbackTime, Math.max(0.001, seekMaxDuration))));
            setIsSeekSliding(true);
            previewSeekCursor(playbackTime);
          }}
          onValueChange={(v) => {
            if (!isSeekSliding) return;
            const target = Math.max(0, Math.min(v, seekMaxDuration));
            setSeekSliderValue((prev) => (Math.abs(prev - target) > 0.005 ? target : prev));
            previewSeekCursor(target);
          }}
          onSlidingComplete={async (v) => {
            const target = Math.max(0, Math.min(v, seekMaxDuration));
            setSeekSliderValue(target);
            previewSeekCursor(target);
            if (seekMaxDuration > 0) {
              await handleSeek(target);
            }
            setIsSeekSliding(false);
          }}
          disabled={seekMaxDuration <= 0}
          minimumTrackTintColor={barPalette.accent}
          maximumTrackTintColor={barPalette.barBorder}
          thumbTintColor={barPalette.accent}
        />
      </View>

      {/* Transport bar */}
      <View
        style={[
          styles.bottomBar,
          {
            paddingBottom: insets.bottom + 16,
            paddingLeft: insets.left,
            paddingRight: insets.right,
          },
        ]}
      >
        <ScrollView
          horizontal
          showsHorizontalScrollIndicator={false}
          contentContainerStyle={styles.barScroll}
        >
          {/* Play / Pause */}
          <Pressable
            style={({ pressed }) => [
              styles.playPill,
              isPaused && !isPlaying && styles.playPillPaused,
              pressed && styles.pressedPill,
            ]}
            onPress={isPlaying ? handlePause : handlePlay}
            disabled={preparing}
          >
            <Feather
              name={isPlaying ? 'pause' : 'play'}
              size={14}
              color={isPaused && !isPlaying ? barPalette.accent : barPalette.barText}
            />
            <Text style={styles.playPillText}>
              {preparing ? 'Preparing...' : isPlaying ? 'Pause' : isPaused ? 'Resume' : 'Play'}
            </Text>
          </Pressable>

          {/* Stop */}
          <Pressable style={({ pressed }) => [styles.iconPill, pressed && styles.pressedPill]} onPress={handleStop}>
            <Feather name="square" size={14} color={barPalette.barText} />
          </Pressable>

          {/* Tempo */}
          <Pressable
            style={({ pressed }) => [styles.zoomPill, showTempoSlider && { borderColor: barPalette.accent }, pressed && styles.pressedPill]}
            onPress={() => {
              setShowPitchSlider(false);
              setShowTempoSlider((v) => !v);
            }}
          >
            <Feather name="activity" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>{tempo} BPM</Text>
          </Pressable>

          {/* Pitch reference */}
          <Pressable
            style={({ pressed }) => [
              styles.zoomPill,
              showPitchSlider && { borderColor: barPalette.accent },
              pressed && styles.pressedPill,
            ]}
            onPress={() => {
              setShowTempoSlider(false);
              setShowPitchSlider((v) => !v);
            }}
          >
            <Feather name="music" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>Pitch {pitchShiftLabel}</Text>
          </Pressable>

          {/* Export MusicXML */}
          <Pressable
            style={({ pressed }) => [styles.zoomPill, pressed && styles.pressedPill]}
            onPress={handleExportMusicXml}
          >
            <Feather name="download" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>Export XML</Text>
          </Pressable>

          <Pressable
            style={({ pressed }) => [styles.zoomPill, pressed && styles.pressedPill]}
            onPress={handleExportWav}
          >
            <Feather name="music" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>Export WAV</Text>
          </Pressable>

          {scoreViewMode === 'rendered' && (
            <Pressable
              style={({ pressed }) => [
                styles.zoomPill,
                pressed && styles.pressedPill,
                renderViewPreset === 'smart' && { borderColor: barPalette.accent },
              ]}
              onPress={() => {
                setRenderViewPreset((preset) => (preset === 'fit' ? 'smart' : 'fit'));
              }}
            >
              <Feather name="maximize" size={12} color={barPalette.barTextMuted} />
              <Text style={styles.pillText}>View {renderViewPreset}</Text>
            </Pressable>
          )}

          {scoreViewMode === 'rendered' && (
            <Pressable
              style={({ pressed }) => [
                styles.zoomPill,
                pressed && styles.pressedPill,
                renderedPlayheadMode !== 'notes' && { borderColor: barPalette.accent },
              ]}
              onPress={() => setRenderedPlayheadMode((mode) => {
                const idx = RENDERED_PLAYHEAD_MODES.indexOf(mode);
                return RENDERED_PLAYHEAD_MODES[(idx + 1) % RENDERED_PLAYHEAD_MODES.length];
              })}
            >
              <Feather
                name={renderedPlayheadMode === 'line' ? 'minus' : renderedPlayheadMode === 'both' ? 'crosshair' : 'disc'}
                size={12}
                color={barPalette.barTextMuted}
              />
              <Text style={styles.pillText}>Playhead {renderedPlayheadMode}</Text>
            </Pressable>
          )}

          {/* Clef filters */}
          <Pressable
            style={({ pressed }) => [
              styles.zoomPill,
              pressed && styles.pressedPill,
              activeClefPreset === 'upper' && { borderColor: barPalette.accent },
            ]}
            onPress={() => changeClefFilter('upper')}
          >
            <Feather name="arrow-up" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>Upper Clef</Text>
          </Pressable>

          <Pressable
            style={({ pressed }) => [
              styles.zoomPill,
              pressed && styles.pressedPill,
              activeClefPreset === 'lower' && { borderColor: barPalette.accent },
            ]}
            onPress={() => changeClefFilter('lower')}
          >
            <Feather name="arrow-down" size={12} color={barPalette.barTextMuted} />
            <Text style={styles.pillText}>Lower Clef</Text>
          </Pressable>

          {/* Score view mode */}
          <Pressable
            style={({ pressed }) => [
              styles.zoomPill,
              pressed && styles.pressedPill,
              scoreViewMode === 'rendered' && { borderColor: barPalette.accent },
            ]}
            onPress={() => setScoreViewMode((m) => (m === 'rendered' ? 'scan' : 'rendered'))}
          >
            <Feather
              name={scoreViewMode === 'rendered' ? 'layout' : 'image'}
              size={12}
              color={barPalette.barTextMuted}
            />
            <Text
              style={[
                styles.scoreModeText,
                scoreViewMode === 'scan' && styles.scoreModeTextScan,
              ]}
            >
              {scoreViewMode === 'rendered' ? 'Rendered score' : 'Scanned score'}
            </Text>
          </Pressable>

          {/* Progress indicator */}
          <View style={styles.viewPill}>
            <Text style={styles.pillText}>
              {preparing && preparingStatusText
                ? preparingStatusText
                : isPaused && !isPlaying
                ? `Paused • ${formatTime(playbackTime)} / ${formatTime(totalDuration)}`
                : `${formatTime(playbackTime)} / ${formatTime(totalDuration)}`}
            </Text>
          </View>

          {/* Score info button (compact pill) */}
          <ScoreInfoPanel
            metadata={scoreData.metadata}
            currentBeat={currentBeat}
            isPlaying={isPlaying}
          />

          {/* Instrument selector */}
          {availablePresets.length > 0 && (
            <Pressable
              style={({ pressed }) => [
                styles.zoomPill,
                showInstrumentPicker && { borderColor: barPalette.accent },
                pressed && styles.pressedPill,
              ]}
              onPress={() => !isPlaying && setShowInstrumentPicker(true)}
              disabled={isPlaying}
            >
              <Feather name="music" size={12} color={barPalette.barTextMuted} />
              <Text style={styles.pillText} numberOfLines={1}>
                {currentInstrumentName}
              </Text>
            </Pressable>
          )}
        </ScrollView>
      </View>

      {/* Instrument picker modal */}
      <Modal
        visible={showInstrumentPicker}
        transparent
        animationType="slide"
        onRequestClose={() => setShowInstrumentPicker(false)}
      >
        <View style={styles.modalOverlay}>
          <View style={[styles.modalContent, { paddingBottom: insets.bottom + 16 }]}>
            <View style={styles.modalHeader}>
              <Text style={styles.modalTitle}>Select Instrument</Text>
              <TouchableOpacity onPress={() => setShowInstrumentPicker(false)}>
                <Feather name="x" size={22} color={palette.ink} />
              </TouchableOpacity>
            </View>
            <FlatList
              data={availablePresets}
              keyExtractor={(item) => String(item.index)}
              renderItem={({ item }) => (
                <TouchableOpacity
                  style={[
                    styles.instrumentRow,
                    item.index === selectedPresetIndex && styles.instrumentRowActive,
                  ]}
                  onPress={() => handleSelectInstrument(item.index)}
                >
                  <Text
                    style={[
                      styles.instrumentName,
                      item.index === selectedPresetIndex && styles.instrumentNameActive,
                    ]}
                  >
                    {item.name}
                  </Text>
                  {item.index === selectedPresetIndex && (
                    <Feather name="check" size={16} color={barPalette.accent} />
                  )}
                </TouchableOpacity>
              )}
            />
          </View>
        </View>
      </Modal>
    </View>
  );
};

function formatTime(sec) {
  if (!Number.isFinite(sec) || sec < 0) return '0:00';
  const m = Math.floor(sec / 60);
  const s = Math.floor(sec % 60);
  return `${m}:${s < 10 ? '0' : ''}${s}`;
}

/* ─── Styles ─── */
const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: palette.background },
  centerContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 24,
    backgroundColor: palette.background,
  },
  loadingLogoWrap: {
    width: 96,
    height: 72,
    borderRadius: 14,
    overflow: 'hidden',
    marginBottom: 14,
  },
  loadingLogoWebView: {
    width: 96,
    height: 72,
    backgroundColor: 'transparent',
  },
  scanningText: {
    fontSize: 14,
    color: palette.ink,
    fontWeight: '700',
    letterSpacing: 0.2,
    marginBottom: 4,
  },
  scanningFileText: {
    fontSize: 12,
    color: palette.inkMuted,
    fontWeight: '600',
    marginBottom: 10,
  },
  processingStageText: {
    fontSize: 11,
    color: palette.inkMuted,
    fontWeight: '500',
    marginBottom: 12,
    fontStyle: 'italic',
    letterSpacing: 0.3,
  },
  loadingBarTrack: {
    width: 188,
    height: 3,
    backgroundColor: '#D8D2C6',
    borderRadius: 2,
    overflow: 'hidden',
  },
  loadingBarPulse: {
    height: 3,
    backgroundColor: barPalette.accent,
    borderRadius: 2,
  },
  cancelScanButton: {
    marginTop: 14,
    paddingHorizontal: 14,
    paddingVertical: 8,
    borderRadius: 10,
    borderWidth: 1,
    borderColor: '#D6D0C4',
    backgroundColor: '#F1EEE4',
  },
  cancelScanButtonText: {
    fontSize: 11,
    fontWeight: '700',
    letterSpacing: 0.4,
    color: '#6E675E',
    textTransform: 'uppercase',
  },
  errorTitle: { fontSize: 18, fontWeight: '700', color: palette.ink, marginBottom: 8 },
  errorText: { fontSize: 13, color: palette.inkMuted, textAlign: 'center', marginBottom: 16, paddingHorizontal: 16 },
  retryButton: {
    backgroundColor: palette.surface,
    paddingHorizontal: 18,
    paddingVertical: 10,
    borderRadius: 12,
    borderWidth: 2,
    borderColor: palette.border,
  },
  retryButtonText: {
    color: palette.ink,
    fontSize: 12,
    fontWeight: '700',
    textTransform: 'uppercase',
    letterSpacing: 0.6,
  },
  header: {
    paddingHorizontal: 24,
    paddingBottom: 12,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    backgroundColor: palette.background,
  },
  title: {
    fontSize: 21,
    fontWeight: '600',
    color: palette.ink,
    letterSpacing: -0.2,
    fontFamily: Platform.OS === 'ios' ? 'Avenir Next' : 'sans-serif-medium',
  },
  linkText: { fontSize: 14, color: palette.inkMuted, fontWeight: '600' },
  topVoiceBar: {
    paddingHorizontal: 12,
    paddingBottom: 10,
    paddingTop: 8,
    backgroundColor: palette.background,
    alignItems: 'flex-start',
  },
  topVoicePill: {
    flexDirection: 'row',
    gap: 6,
    backgroundColor: '#FFFFFF',
    borderRadius: 14,
    paddingVertical: 8,
    paddingHorizontal: 8,
    borderWidth: 1,
    borderColor: '#E4DACB',
  },
  topVoiceDot: {
    minWidth: 54,
    height: 32,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1,
    paddingHorizontal: 10,
  },
  topVoiceDotActive: {
    backgroundColor: '#F3EBDE',
    borderColor: '#D2BFA3',
  },
  topVoiceDotAll: {
    backgroundColor: '#EEF3EB',
    borderColor: '#B8C7B4',
  },
  topVoiceDotEmpty: {
    backgroundColor: '#FAF8F3',
    borderColor: '#E0D7C8',
    opacity: 0.55,
  },
  topVoiceDotInactive: {
    backgroundColor: '#FFFEFC',
    borderColor: '#E8DDCD',
  },
  topVoiceDotText: {
    fontSize: 12,
    fontWeight: '700',
    letterSpacing: 0.15,
  },
  topVoiceDotTextActive: {
    color: '#3E3C37',
  },
  topVoiceDotTextInactive: {
    color: '#6E675E',
  },
  viewerArea: {
    flex: 1,
    backgroundColor: '#fff',
    marginHorizontal: 12,
    borderRadius: 12,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: '#E6E2D8',
  },
  tempoDrawer: {
    backgroundColor: barPalette.barRaised,
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderTopWidth: 1,
    borderTopColor: barPalette.barBorder,
  },
  tempoDrawerRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 2,
  },
  tempoDrawerLabel: {
    color: barPalette.barTextMuted,
    fontSize: 12,
    fontWeight: '600',
    textTransform: 'uppercase',
    letterSpacing: 0.8,
  },
  tempoDrawerValue: {
    color: barPalette.accent,
    fontSize: 14,
    fontWeight: '800',
  },
  tempoSlider: {
    width: '100%',
    height: 32,
  },
  tempoPresets: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginTop: 4,
  },
  tempoPresetBtn: {
    paddingHorizontal: 10,
    paddingVertical: 5,
    borderRadius: 12,
    backgroundColor: barPalette.bar,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  tempoPresetBtnActive: {
    backgroundColor: barPalette.accent,
    borderColor: barPalette.accent,
  },
  tempoPresetText: {
    color: barPalette.barTextMuted,
    fontSize: 10,
    fontWeight: '700',
  },
  tempoPresetTextActive: {
    color: barPalette.barText,
  },
  seekBarWrap: {
    backgroundColor: barPalette.bar,
    paddingHorizontal: 14,
    paddingTop: 10,
    paddingBottom: 2,
    borderTopWidth: 1,
    borderTopColor: barPalette.barBorder,
  },
  seekBarHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 2,
  },
  seekBarLabel: {
    color: barPalette.barTextMuted,
    fontSize: 11,
    fontWeight: '700',
    letterSpacing: 0.5,
    textTransform: 'uppercase',
  },
  seekBarTimeText: {
    color: barPalette.barText,
    fontSize: 12,
    fontWeight: '700',
  },
  seekSlider: {
    width: '100%',
    height: 30,
  },
  bottomBar: {
    backgroundColor: barPalette.bar,
    paddingTop: 8,
    borderTopWidth: 1,
    borderTopColor: barPalette.barBorder,
  },
  barScroll: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 10,
    paddingHorizontal: 12,
  },
  playPill: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    backgroundColor: barPalette.barRaised,
    borderRadius: 16,
    paddingVertical: 8,
    paddingHorizontal: 12,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  playPillPaused: {
    borderColor: barPalette.accent,
    backgroundColor: '#332015',
  },
  playPillText: { color: barPalette.barText, fontSize: 12, fontWeight: '700' },
  zoomPill: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 6,
    backgroundColor: barPalette.barRaised,
    borderRadius: 16,
    paddingVertical: 6,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  pillText: { color: barPalette.barText, fontSize: 12, fontWeight: '600' },
  scoreModeText: {
    color: '#DED8CC',
    fontSize: 12,
    fontWeight: '500',
    letterSpacing: 0.2,
    fontFamily: Platform.OS === 'ios' ? 'Avenir Next' : 'sans-serif-medium',
  },
  scoreModeTextScan: {
    color: '#F2ECE0',
  },
  voicePill: {
    flexDirection: 'row',
    gap: 6,
    backgroundColor: barPalette.barRaised,
    borderRadius: 16,
    paddingVertical: 6,
    paddingHorizontal: 8,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  voiceDot: {
    minWidth: 26,
    height: 26,
    borderRadius: 13,
    alignItems: 'center',
    justifyContent: 'center',
    borderWidth: 1,
    paddingHorizontal: 4,
  },
  voiceDotActive: { backgroundColor: barPalette.accent, borderColor: barPalette.accent },
  voiceDotAll: { backgroundColor: '#2A7B3D', borderColor: '#2A7B3D' },
  voiceDotEmpty: { backgroundColor: barPalette.bar, borderColor: '#555', opacity: 0.4 },
  voiceDotInactive: { backgroundColor: barPalette.bar, borderColor: barPalette.barBorder },
  voiceDotText: { fontSize: 11, fontWeight: '700' },
  voiceDotTextActive: { color: barPalette.barText },
  voiceDotTextInactive: { color: barPalette.barTextMuted },
  viewPill: {
    backgroundColor: barPalette.barRaised,
    borderRadius: 16,
    paddingVertical: 6,
    paddingHorizontal: 10,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  iconPill: {
    width: 32,
    height: 32,
    borderRadius: 10,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: barPalette.barRaised,
    borderWidth: 1,
    borderColor: barPalette.barBorder,
  },
  pressedPill: {
    opacity: 0.6,
    transform: [{ scale: 0.96 }],
  },
  pressedDot: {
    opacity: 0.6,
    transform: [{ scale: 0.9 }],
  },
  modalOverlay: {
    flex: 1,
    backgroundColor: 'rgba(0,0,0,0.5)',
    justifyContent: 'flex-end',
  },
  modalContent: {
    backgroundColor: palette.background,
    borderTopLeftRadius: 20,
    borderTopRightRadius: 20,
    maxHeight: '60%',
  },
  modalHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    paddingHorizontal: 20,
    paddingVertical: 14,
    borderBottomWidth: 1,
    borderBottomColor: palette.border,
  },
  modalTitle: {
    fontSize: 17,
    fontWeight: '800',
    color: palette.ink,
    letterSpacing: -0.3,
  },
  instrumentRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'space-between',
    paddingHorizontal: 20,
    paddingVertical: 13,
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: palette.border,
  },
  instrumentRowActive: {
    backgroundColor: palette.surfaceStrong,
  },
  instrumentName: {
    fontSize: 14,
    fontWeight: '600',
    color: palette.ink,
    flex: 1,
  },
  instrumentNameActive: {
    color: barPalette.accent,
    fontWeight: '800',
  },
});
