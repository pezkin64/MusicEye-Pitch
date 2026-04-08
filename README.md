# Music Eye — Powered by ZemEmu

Music Eye is a React Native (Expo) sheet music scanner and playback app powered by ZemEmu.

Current runtime architecture is:
- NoteScan app captures or picks an image
- NoteScan sends the image to the local ZemEmu harness endpoint
- ZemEmu returns MusicXML
- MusicXML is parsed to timed note events
- AudioPlaybackService renders and plays the score with SoundFont samples

## Architecture

```
┌──────────────────────┐        HTTP POST         ┌──────────────────────────┐
│ Music Eye (NoteScan) │ ───────────────────────▶ │ ZemskyHarness (ZemEmu)  │
│ React Native + Expo  │      /process            │ Android app, port 8084  │
│                      │ ◀─────────────────────── │ Returns MusicXML JSON    │
│ MusicXMLParser       │                          └──────────────────────────┘
│ AudioPlaybackService │
│ PlaybackVisualization│
└──────────────────────┘
```

## Quick Start

### 1) Install NoteScan dependencies

```bash
cd NoteScan
npm install
```

### 2) Build and launch ZemskyHarness in Android emulator

```bash
cd ../ZemskyHarness
./gradlew installDebug
adb shell am start -n com.musiceye.zemskyharness/.MainActivity
```

### 3) Start NoteScan

```bash
cd ../NoteScan
npx expo start --android
```

For tunnel mode:

```bash
npx expo start -- --tunnel
```

## Endpoints Used by NoteScan

ZemskyHarness serves:
- GET http://127.0.0.1:8084/
- GET http://127.0.0.1:8084/docs
- POST http://127.0.0.1:8084/process

`POST /process` expects multipart form-data with a `file` field and returns JSON:

```json
{
    "musicxml": "...",
    "notePositions": null,
    "processedImage": null
}
```

## Project Structure

```
NoteScan/
├── App.js
├── package.json
├── assets/
│   └── SheetMusicScanner.sf2
└── src/
        ├── screens/
        │   ├── HomeScreen.js
        │   ├── PlaybackScreen.js
        │   └── SettingsScreen.js
        ├── services/
        │   ├── OMRSettings.js
        │   ├── ZemskyEmulatorService.js
        │   ├── MusicXMLParser.js
        │   ├── AudioPlaybackService.js
        │   ├── SoundFontService.js
        │   └── OMRCacheService.js
        └── components/
                ├── PlaybackVisualization.js
                └── ScoreInfoPanel.js
```

## Notes

- Engine key in code remains `zemsky` for compatibility.
- Product/UI wording uses ZemEmu.
- App and harness should run in the same Android emulator environment for localhost `127.0.0.1:8084` access.