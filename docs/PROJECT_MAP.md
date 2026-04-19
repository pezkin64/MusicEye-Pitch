# Music Eye Project Map

This document captures the current architecture, development workflow, and release target for Music Eye so the project context is easy to recover later.

## 1. What The Project Is

Music Eye is a React Native / Expo sheet-music scanner app.

The app flow is:
- User captures or selects a sheet-music image.
- The app runs OMR on that image.
- The result is converted to MusicXML and playable notes.
- Playback is rendered in the UI with cursor/score visualization.

## 2. Current Workspace Areas

### NoteScan

Path: [NoteScan](../NoteScan)

This is the main React Native app.

Important files:
- [App.js](../NoteScan/App.js) - top-level navigation and screen switching.
- [src/screens/HomeScreen.js](../NoteScan/src/screens/HomeScreen.js) - main entry UI and engine status.
- [src/screens/PlaybackScreen.js](../NoteScan/src/screens/PlaybackScreen.js) - processing, playback, tempo/pitch controls, cursor display.
- [src/screens/SettingsScreen.js](../NoteScan/src/screens/SettingsScreen.js) - engine selection and cache controls.
- [src/screens/FileUploadScreen.js](../NoteScan/src/screens/FileUploadScreen.js) - file picker flow.
- [src/screens/LibraryScreen.js](../NoteScan/src/screens/LibraryScreen.js) - saved scores browser.
- [src/services/OMRSettings.js](../NoteScan/src/services/OMRSettings.js) - engine selector and persistence.
- [src/services/ZemskyEmulatorService.js](../NoteScan/src/services/ZemskyEmulatorService.js) - HTTP client that talks to the helper app on port 8084.
- [src/services/AudioPlaybackService.js](../NoteScan/src/services/AudioPlaybackService.js) - current JS audio playback path.
- [src/services/NativeAudioBridge.js](../NoteScan/src/services/NativeAudioBridge.js) - experimental native-audio bridge scaffolding.
- [src/services/LibraryService.js](../NoteScan/src/services/LibraryService.js) - persisted score library.

### ZemskyHarness

Path: [ZemskyHarness](../ZemskyHarness)

This is the separate Android helper app used during development.

Its role is:
- Start a local HTTP server inside the emulator or device.
- Listen on `http://127.0.0.1:8084`.
- Accept uploaded images at `/process`.
- Return MusicXML and metadata to NoteScan.

Important files:
- [app/src/main/java/com/musiceye/zemskyharness/MainActivity.java](../ZemskyHarness/app/src/main/java/com/musiceye/zemskyharness/MainActivity.java) - boots the foreground service.
- [app/src/main/java/com/musiceye/zemskyharness/LocalOmrHttpServer.java](../ZemskyHarness/app/src/main/java/com/musiceye/zemskyharness/LocalOmrHttpServer.java) - local HTTP API on port 8084.
- [app/src/main/java/com/musiceye/zemskyharness/OmrEngine.java](../ZemskyHarness/app/src/main/java/com/musiceye/zemskyharness/OmrEngine.java) - native OMR pipeline using the bundled .so libraries.

### tools/zemsky_eval

Path: [tools/zemsky_eval](../tools/zemsky_eval)

This is an offline evaluation tool for regression analysis.

It is not part of the runtime app. It is used to compare masks and generate metrics from datasets under ASSETS.

## 3. Current Development Workflow

The current test workflow is two-process:

1. Run NoteScan in Expo.
2. Run ZemskyHarness separately on the same Android device or emulator.
3. NoteScan sends the selected image to the helper over local HTTP port 8084.
4. The helper processes the image with the native OMR stack.
5. NoteScan receives MusicXML and playback data back from the helper.

This is a development setup only.

## 4. Current Runtime Topology

```text
User action in NoteScan (Expo)
  -> image selection / camera capture
  -> OMRSettings selects ZemskyEmulatorService
  -> POST image to http://127.0.0.1:8084/process
  -> ZemskyHarness LocalOmrHttpServer receives request
  -> OmrEngine runs native OMR and exports MusicXML
  -> JSON response returns to NoteScan
  -> PlaybackScreen parses MusicXML and plays audio
```

## 5. Release Target

The commercial release target is a single Music Eye app in the Play Store.

That means:
- No separate helper APK for end users.
- No separate native engine app for end users.
- The release build should package the needed functionality into Music Eye itself or into an internal native module.

The helper app is only for development / bridging while native integration is being validated.

## 6. Expo Constraint

The current app is being tested through Expo.

That matters because:
- Expo is good for the JavaScript/UI layer.
- Plain Expo Go cannot directly host the helper's native .so library.
- The separate helper app is therefore a valid temporary workflow.

## 7. What Future Work Should Remember

When returning to this project, assume:
- The user wants one final app for release.
- The helper APK is not user-facing.
- The current work is about bridging Expo testing with native OMR experimentation.
- If a change affects release architecture, prefer a path that keeps the final product to one install.
