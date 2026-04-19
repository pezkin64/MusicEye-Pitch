# Real-Time DSP Architecture Design

> This document outlines the technical design for migrating from pre-render (JS) to callback-based (native) audio for instant pitch/tempo response.

---

## 1. Current vs. Target Architecture

### Current (Pre-Render, JS-Based)
```
PlaybackScreen (React)
    ↓ handlePlayClick()
AudioPlaybackService.playWebAudio()
    ├─ Create AudioBufferQueueSourceNode
    ├─ Prepare all note waveforms (MIX_CHUNK_SIZE × note count)
    │   └─ ~100-500ms on slow devices
    ├─ Feed chunks into queue every ~30ms
    └─ On pitch/tempo change:
        ├─ Stop playback
        ├─ Rebuild all chunks from scratch
        └─ Resume (~100-500ms latency visible)
```

**Problem**: Parameter changes require destructive re-render. Not interactive.

### Target (Callback-Based, Native)
```
PlaybackScreen (React)
    ↓ handlePlayClick() / handleSliderChange()
NativeAudioBridge.js (JS→Kotlin/Swift wrapper)
    ↓ dispatch({ type: 'PITCH', hz: 440 }) or { type: 'TEMPO', bpm: 140 }
[Native Audio Thread]
    Audio Callback (~20ms chunks)
    ├─ Current: Check param queue
    ├─ If pitch changed: Update synth.setPitchHz()
    ├─ If tempo changed: Adjust scheduler timer interval
    ├─ Synthesize waveform (single-pass, no re-render)
    └─ Write 20ms PCM to speaker
```

**Benefit**: Parameters become automation signals. Instant response, no interruption.

---

## 2. JS↔Native Bridge API Design

### Proposed RPC Protocol

```typescript
// JS → Native (fire-and-forget events)
type AudioCommand =
  | { type: 'INIT'; config: { sampleRate: 48000; bufferSize: 512 } }
  | { type: 'PLAY'; notes: NativeNote[] }
  | { type: 'STOP' }
  | { type: 'PAUSE' }
  | { type: 'RESUME' }
  | { type: 'PARAM'; key: 'PITCH' | 'TEMPO'; value: number }  // sent per-frame during slider drag
  | { type: 'SEEK'; timeMs: number }
  | { type: 'RELEASE' }  // cleanup;

// Native → JS (async callback for status)
type AudioStatus =
  | { event: 'READY' }
  | { event: 'PLAYING'; currentTimeMs: number }
  | { event: 'STOPPED' }
  | { event: 'ERROR'; message: string }
;
```

### Example Usage (in React)

```javascript
// PlaybackScreen.js
const nativeAudio = useRef(null);

useEffect(() => {
  if (Platform.OS === 'android' || Platform.OS === 'ios') {
    nativeAudio.current = NativeAudioBridge.create();
  }
}, []);

const handlePlay = async () => {
  await nativeAudio.current?.dispatch({
    type: 'PLAY',
    notes: convertedNotes,
    config: { sampleRate: 48000 }
  });
};

const handlePitchSlider = (hz) => {
  // Instant, no prepare delay
  nativeAudio.current?.dispatch({
    type: 'PARAM',
    key: 'PITCH',
    value: hz
  });
};

const handleTempoSlider = (bpm) => {
  nativeAudio.current?.dispatch({
    type: 'PARAM',
    key: 'TEMPO',
    value: bpm
  });
};
```

---

## 3. Synth Library Decision

### Option A: TinyMIDI (Custom Lightweight)
- **Pros**: Full control, tiny footprint, easy to integrate with SoundFont
- **Cons**: More code to write, less tested
- **Decision**: ✅ **RECOMMENDED** — Gives us exact competitor parity

### Option B: libfluidsynth
- **Pros**: Battle-tested, full MIDI spec support
- **Cons**: ~2MB native lib, harder to optimize for phone
- **Decision**: ❌ Overkill for our use case

### Option C: Pure Custom MIDI Synth
- **Pros**: Minimal, exactly tailored
- **Cons**: Max development effort
- **Decision**: ❌ Too much work for this phase

### Recommendation: **TinyMIDI-inspired synth**
We'll write a lean synth engine in Kotlin/Swift that:
1. **Loads SoundFont waveforms** (from SheetMusicScanner.sf2) into memory once at startup
2. **Keeps active note list** with real-time pitch/velocity
3. **On each audio callback**: Mix all active notes at current pitch (cents offset via linear interpolation)
4. **No stream recreation**: Just update synth state, callback re-calculates output

---

## 4. Scheduling Strategy

### Option A: java.util.Timer (Competitor Uses This)
```kotlin
private val scheduler = Timer()

fun scheduleNote(note: Note, startTimeMs: Long) {
  scheduler.schedule({
    synth.noteOn(note.pitch, note.velocity)
  }, startTimeMs)
  
  scheduler.schedule({
    synth.noteOff(note.pitch)
  }, startTimeMs + note.durationMs)
}
```
- **Pros**: Matches competitor, simple, flexible
- **Cons**: Timer has ~20ms granularity on Android

### Option B: Handler (Android Standard)
- **Pros**: Integrated with Android lifecycle
- **Cons**: UI thread affinity, less precise

### Option C: MediaPlayer Callback
- **Pros**: System-backed, precise
- **Cons**: Less flexible for our multi-voice case

### Recommendation: **java.util.Timer with ~20ms tuning**
We'll use Timer for note scheduling, compensate granularity with tight callback buffer (~512 samples @ 48kHz = 10ms).

---

## 5. Parameter Automation

### How Pitch Updates Work

```
User slides pitch slider 440 → 880 Hz
  ↓ (every 50ms during drag, ~20× per second)
PlaybackScreen.handlePitchSlider(880)
  ↓
nativeAudio.dispatch({ type: 'PARAM', key: 'PITCH', value: 880 })
  ↓
Kotlin NativeAudioBridge puts (880) into queue
  ↓
Audio callback (runs every 10-20ms) reads queue
  ↓
synth.setPitchCents(88.29)  // 880 / 440 = 2 octaves = 2400 cents
  ↓
Next call to mix(): resynthesizes chunk at new pitch (via wavetable resampling)
  ↓
User hears pitch change <20ms after slider change ✅
```

**Key**: No stopping/starting, just continuous parameter updates fed to synth.

### How Tempo Updates Work

```
User slides tempo slider 120 → 150 BPM (1.25×)
  ↓
nativeAudio.dispatch({ type: 'PARAM', key: 'TEMPO', value: 150 })
  ↓
Kotlin scheduler updates note timing:
  - currentBpm = 150
  - For each pending note: adjust scheduled time proportionally
  - Re-fire timer with new intervals
  ↓
Notes fire at new tempo intervals ✅
```

---

## 6. Implementation Phases

### Phase 1: Design & API (This Week)
- [x] Finalize architecture above
- [ ] Sign off on bridge protocol
- [ ] Choose TinyMIDI vs. other

### Phase 2: Android POC (1-2 weeks)
- [ ] Create `android/app/src/main/kotlin/com/musiceye/audio/NativeAudioModule.kt`
- [ ] Implement AudioTrack + callback
- [ ] Implement note scheduler
- [ ] Wire through to JS via NativeModules

### Phase 3: iOS POC (1-2 weeks)
- [ ] Create `ios/NoteScan/Audio/NativeAudioModule.swift`
- [ ] Implement RemoteIO + callback
- [ ] Mirror Android logic
- [ ] Update React bridge (Platform.select)

### Phase 4: Validation (1 week)
- [ ] Test latency on multiple devices
- [ ] Profile CPU/memory usage
- [ ] Audio glitch testing (edge cases)

### Phase 5: Full Migration (2-3 weeks)
- [ ] Move SoundFont loading to native
- [ ] Port full OMR playback flow
- [ ] Retire JS AudioPlaybackService (with fallback)
- [ ] Polish UI

---

## 7. Risk Mitigation

| Risk | Mitigation |
|------|-----------|
| Native code complexity | Start with POC, heavily comment, use Kotlin (easier than C++) |
| Cross-platform divergence | Mirror Android→iOS 1:1, extensive testing |
| Latency not improved | Weekly profiling checkpoints, easy rollback to JS |
| Audio glitches | Conservative buffer tuning initially, then optimize |
| Device compatibility | Test on SDK 21–34 (Android), iOS 14–17 |

---

## 8. Success Metrics

- [ ] Pitch/tempo changes feel instant (<10ms) to user
- [ ] No audio pops/glitches on parameter change
- [ ] Works on Android SDK 21+ and iOS 14+
- [ ] Can fallback to JS if native fails
- [ ] <5% app size increase
- [ ] <10% CPU increase vs. JS baseline

---

## 9. Next Steps

1. **Review & Sign-off**: Confirm above approach with team
2. **Scaffold Android Module**: Create Kotlin class stubs + build.gradle integration
3. **Create JS Bridge Stub**: Write NativeAudioBridge.js with mock implementation
4. **Start Android Implementation**: Week 1 goal is single-note playback test

