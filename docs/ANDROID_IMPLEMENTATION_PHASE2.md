# Phase 2: Android DSP Implementation Guide

> Real-time audio engine integration for Music Eye (Android).

---

## Overview

This phase brings the native audio callback architecture to Android. We've created three Kotlin classes + a JavaScript bridge to enable instant pitch/tempo updates during playback.

---

## What Was Created

### Kotlin (Android Audio Engine)

1. **`NativeAudioModule.kt`** (core engine)
   - Manages AudioTrack + PCM callback loop
   - Schedules notes via java.util.Timer (competitor pattern)
   - Handles parameter automation (pitch, tempo)
   - Synthesizes waveforms in callback (~10ms chunks)
   - Location: `android/app/src/main/kotlin/com/musiceye/audio/`

2. **`MusicEyeAudioModule.kt`** (React Native bridge)
   - Exposes NativeAudioModule to JS via NativeModules API
   - Methods: init, play, stop, pause, resume, setParameter, seek, release
   - Sends status events back to JS
   - Location: `android/app/src/main/kotlin/com/musiceye/audio/`

3. **`MusicEyeAudioPackage.kt`** (package provider)
   - Registers MusicEyeAudioModule with React Native
   - Location: `android/app/src/main/kotlin/com/musiceye/audio/`

### JavaScript (React Native Layer)

4. **`src/services/NativeAudioBridge.js`** (JS wrapper)
   - Clean async API: play(), stop(), pause(), resume()
   - Real-time methods: setPitch(), setTempo() (no prepare delay)
   - Event subscriptions for status changes
   - Graceful fallback if native unavailable

---

## Installation Steps

### 1. Verify Kotlin Build Config

Edit `android/app/build.gradle`:

```gradle
android {
    compileSdk 34
    
    buildFeatures {
        buildConfig = true
        compose = true  // Ensure Kotlin is enabled
    }
}

dependencies {
    // Already present in expo projects
    implementation 'com.facebook.react:react-android:0.81.5'
}
```

### 2. MainApplication Registration ✅ DONE

Already updated `android/app/src/main/java/com/pezkin/musiceye/notescan/MainApplication.kt`:

```kotlin
import com.musiceye.audio.MusicEyeAudioPackage

class MainApplication : Application(), ReactApplication {
  override val reactNativeHost: ReactNativeHost = ReactNativeHostWrapper(
    this,
    object : DefaultReactNativeHost(this) {
      override fun getPackages(): List<ReactPackage> =
        PackageList(this).packages.apply {
          add(MusicEyeAudioPackage())  // <-- Added
        }
    }
  )
}
```

### 3. Android Permissions

Add to `android/app/src/main/AndroidManifest.xml`:

```xml
<manifest ...>
  <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
  <uses-permission android:name="android.permission.RECORD_AUDIO" />
  <!-- RECORD_AUDIO required even for output on some devices -->
  
  <application ...>
    <!-- Rest of manifest -->
  </application>
</manifest>
```

---

## Usage in React

### Basic Playback

```javascript
// PlaybackScreen.js
import NativeAudioBridge from '../services/NativeAudioBridge';

export default function PlaybackScreen() {
  const nativeAudio = useRef(null);

  useEffect(() => {
    nativeAudio.current = NativeAudioBridge;

    // Listen for audio events
    nativeAudio.current.onStatusChange((event, data) => {
      console.log(`Audio ${event}:`, data);
      if (event === 'PLAYING') {
        // Update UI with current time
      }
    });

    return () => {
      // Cleanup
    };
  }, []);

  const handlePlayClick = async () => {
    try {
      // Convert score notes to native format
      const notes = scoreData.notes.map((note) => ({
        pitch: note.midiPitch,
        velocity: note.velocity || 100,
        startTimeMs: note.startTime * 1000,
        durationMs: note.duration * 1000,
      }));

      await nativeAudio.current.play(notes);
      setIsPlaying(true);
    } catch (e) {
      console.error('Play failed:', e);
    }
  };

  return (
    <View>
      <Pressable onPress={handlePlayClick}>
        <Text>Play</Text>
      </Pressable>
    </View>
  );
}
```

### Real-Time Pitch Updates (No Prepare!)

```javascript
const handlePitchSlider = (hz) => {
  // This is instant - no re-prepare needed
  nativeAudio.current.setPitch(hz);
  setPitchHz(hz);  // Update UI label
};

return (
  <Slider
    min={220}
    max={880}
    step={1}
    value={pitchHz}
    onValueChange={handlePitchSlider}
  />
);
```

### Real-Time Tempo Updates (No Prepare!)

```javascript
const handleTempoSlider = (bpm) => {
  // This reschedules notes in real-time
  nativeAudio.current.setTempo(bpm);
  setTempo(bpm);
};

return (
  <Slider
    min={60}
    max={200}
    step={1}
    value={tempo}
    onValueChange={handleTempoSlider}
  />
);
```

---

## Testing Checklist

### Single-Note Test
- [ ] Build APK: `cd NoteScan && npx eas build --platform android --local`
- [ ] Install on device/emulator
- [ ] Open app, navigate to PlaybackScreen
- [ ] Tap Play with a single-note score
- [ ] Verify: Audio plays without crash
- [ ] Check logcat: `adb logcat | grep MusicEyeAudio`

### Pitch Test
- [ ] Play score
- [ ] Drag pitch slider to 880 Hz (one octave up)
- [ ] Verify: Pitch changes instantly (no audio stop/restart)
- [ ] Check logcat for `Pitch → 880 Hz` message

### Tempo Test
- [ ] Play score
- [ ] Drag tempo slider to 150 BPM
- [ ] Verify: Playback speeds up instantly (no skip)
- [ ] Check logcat for `Tempo → 150 BPM` message

### Multi-Voice Test
- [ ] Play score with notes in both voices
- [ ] Verify: Both voices audible and in correct rhythm

### Stress Test
- [ ] Play score with 100+ notes
- [ ] Monitor: CPU usage (should be <30%), memory (stable)
- [ ] Verify: No audio glitches or dropouts

---

## Architecture Diagram

```
React Native Layer
  ↓
PlaybackScreen.js
  │
  ├─ Calls: nativeAudio.play(notes)
  └─ Calls: nativeAudio.setPitch(hz)  [instant, no prepare]
  │
  ↓ (NativeModules bridge)
JavaScript/Kotlin Bridge
  ├─ MusicEyeAudioModule.kt (native method implementations)
  │
  ↓
Native Audio Engine
  ├─ NativeAudioModule.kt
  │   ├─ AudioTrack (PCM output)
  │   ├─ Timer (note scheduling)
  │   ├─ Parameter Queue (pitch, tempo)
  │   └─ Synth Engine (waveform synthesis)
  │
  ↓
Speaker Output (instant parameter response)
```

---

## Performance Expectations

### Latency

| Operation | Current (JS Pre-Render) | Native DSP | Improvement |
|-----------|------------------------|-----------|-------------|
| Play start | 100-500ms | 50ms | 2-10× faster |
| Pitch change | 100-500ms | <10ms | 10-50× faster |
| Tempo change | 100-500ms | <10ms | 10-50× faster |

### Resource Usage

| Metric | Expected |
|--------|----------|
| CPU usage | <10% (peak) |
| Memory | +2-3MB (native module) |
| App size | +500KB (minimal native binary) |

---

## Next Steps (Phase 3)

Once Android POC is solid:
1. Test extensively on real devices (Android 10-14)
2. Profile latency with audio measurements
3. Implement iOS mirror (Swift + RemoteIO)
4. Compare latency vs. competitor

---

## Troubleshooting

### "Module not found" Error
```
ReferenceError: Can't find native module 'MusicEyeAudio'
```
**→** Verify MainApplication registration (step 2 above)
**→** Rebuild: `cd NoteScan && npx eas build --platform android --local`

### Audio Doesn't Play
```
E/MusicEyeAudio: Play failed: ...
```
**→** Check Android permissions (AndroidManifest.xml)
**→** Check logcat for full error: `adb logcat | grep MusicEyeAudio`

### Pitch/Tempo Changes Don't Work
```
D/MusicEyeAudio: setPitch ignored (not playing)
```
**→** Verify playback is active before changing parameters
**→** Check NativeAudioBridge.isAvailable flag

### Audio Glitches/Pops
**→** Reduce SAMPLE_RATE to 44100 in NativeAudioModule.kt
**→** Increase BUFFER_SIZE from 512 to 1024 frames
**→** Profile with Android Profiler (CPU usage)

---

## Files Created/Modified

```
NoteScan/
├── android/
│   ├── app/
│   │   ├── src/main/
│   │   │   ├── kotlin/com/musiceye/audio/
│   │   │   │   ├── NativeAudioModule.kt          [NEW]
│   │   │   │   ├── MusicEyeAudioModule.kt        [NEW]
│   │   │   │   └── MusicEyeAudioPackage.kt       [NEW]
│   │   │   └── java/com/pezkin/musiceye/notescan/
│   │   │       └── MainApplication.kt            [MODIFIED - add import + register]
├── src/
│   └── services/
│       └── NativeAudioBridge.js                  [NEW - JS wrapper]
└── docs/
    ├── DSP_ARCHITECTURE_DESIGN.md                [NEW - design doc]
    └── ANDROID_IMPLEMENTATION.md                 [THIS FILE]
```

---

## Key Differences from JS Approach

| Aspect | JS Pre-Render | Native DSP |
|--------|--------------|-----------|
| Prepare time | 100-500ms block | Amortized into playback |
| Parameter changes | Requires full re-render | Update parameter queue |
| Latency | ~200ms per change | <10ms per change |
| Scheduling | JS event loop | Dedicated audio thread |
| Waveform synthesis | JavaScript (slow) | Native C/Kotlin (fast) |
| User perception | Laggy, unresponsive | Instant, professional |

---

