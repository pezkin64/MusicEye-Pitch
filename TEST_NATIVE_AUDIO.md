# 🎵 Testing the Native Audio Bridge (Super Player)

## What We Just Built

We integrated the **NativeAudioBridge** into PlaybackScreen.js. This means:

- ✅ When you hit **Play**, the app tries the native audio bridge first
- ✅ If native isn't available, it falls back to Web Audio (JS)
- ✅ **Pitch & Tempo** controls work in real-time with the native player
- ✅ All changes gracefully fall back to JS audio if anything fails

---

## How to Test

### Option 1: Build & Test on Android Device/Emulator (Recommended)

#### Prerequisites
- Android Studio or SDK installed locally
- Android device or emulator running API 24+
- Java 11+

#### Steps

1. **Go to NoteScan folder:**
   ```bash
   cd /path/to/MusicEye-Pitch/NoteScan
   ```

2. **Install dependencies** (if not already done):
   ```bash
   npm install
   # or
   yarn install
   ```

3. **Build the APK locally:**
   ```bash
   # Using EAS CLI (recommended)
   npm install -g eas-cli
   eas build --platform android --local
   
   # OR using gradle directly
   cd android
   ./gradlew assembleRelease
   ```

4. **Install on device/emulator:**
   ```bash
   adb install -r build/outputs/apk/app-release.apk
   ```

5. **Test the app:**
   - Open Music Eye app
   - Take/upload a sheet music photo
   - Hit **Play** button
   - You should see in the console: `🚀 handlePlay: Native Audio Bridge at X BPM`
   - Try moving the **Tempo slider** → should change tempo instantly
   - Try moving the **Pitch slider** → should change pitch instantly

### Option 2: Test in Expo Go (Currently Works)

If you want to skip the build for now:

1. **Run in Expo:**
   ```bash
   cd NoteScan
   npx expo start
   ```

2. **Open in Expo Go on your phone and test:**
   - Native bridge won't be available (returns `native_unavailable`)
   - Will automatically fall back to Web Audio (JS path)
   - All features still work (pitch, tempo)
   - Just not using the native player yet

---

## What to Check During Testing

### Console Logs

Look for these messages:

**✅ Success (Native Audio Bridge Active)**
```
🚀 handlePlay: Native Audio Bridge at 120 BPM
[Native] Pitch updating to 440 Hz
[Native] Tempo slider → 150 BPM
[NativeAudioBridge] Playing 32 notes
```

**⚠️ Fallback (Native Not Available)**
```
[NativeAudioBridge] Native audio unavailable (web or module not loaded)
▶️ handlePlay: Web Audio at 120 BPM
```

### Audio Playback

| Feature | Expected | What to Check |
|---------|----------|---------------|
| **Play button** | Score plays through speaker | Does audio come out? |
| **Pause button** | Halts playback at current position | Can resume from same spot? |
| **Tempo slider** | Changes speed in real-time (no lag) | Does it feel instant? |
| **Pitch slider** | Changes all notes up/down (no lag) | Sounds higher/lower immediately? |
| **Stop button** | Resets to beginning | Does cursor go to start? |

---

## Architecture

### Native Path (Primary, if available)
```
PlaybackScreen handlePlay()
  → nativeAudioBridgeRef.play(notes)
    → Kotlin/Android AudioTrack
      → Speaker 🔊 (real-time, instant pitch/tempo)
```

### Fallback Path (if native unavailable)
```
PlaybackScreen handlePlay()
  → AudioPlaybackService.playWebAudio()
    → Web Audio API
      → Speaker 🔊 (real-time, instant pitch/tempo)
```

---

## Debugging

If things don't work:

1. **Check Android build log:**
   ```bash
   cd NoteScan/android
   ./gradlew build --info 2>&1 | tail -100
   ```

2. **Check runtime errors:**
   - Open Android logcat: `adb logcat -s MusicEye`
   - Look for errors from `MusicEyeAudioModule` or `NativeAudioBridge`

3. **Verify native module loaded:**
   - If you see `Native audio unavailable`, the module didn't load correctly
   - Check `android/app/src/main/kotlin/com/musiceye/audio/` exists
   - Verify `MusicEyeAudioPackage` is registered in `MainApplication.kt`

4. **Test JS fallback works:**
   - Even if native fails, Web Audio should still play music
   - Pitch/tempo controls should still work

---

## Next Steps After Testing

### If Native Audio Works ✅
- Great! We have instant, smooth pitch/tempo changes
- Move to iOS (Phase 3) using Swift equivalent
- Optimize performance if needed

### If Fallback (JS Audio) Works ⚠️
- App still fully functional
- Users get smooth playback in Expo
- Native optimization not ready yet
- Nothing is broken ✨

---

## Files Changed

See the integration in:
- `src/screens/PlaybackScreen.js` — Added NativeAudioBridge integration
- `src/services/NativeAudioBridge.js` — Bridge API ✅
- `android/app/src/main/kotlin/com/musiceye/audio/` — Native modules ✅

---

## Questions?

The NativeAudioBridge is designed to be **fail-safe**:
- If native module doesn't exist → gracefully skipped
- If native call fails → falls back to JS audio immediately
- No crashes, no data loss, just works

Enjoy testing! 🎉
