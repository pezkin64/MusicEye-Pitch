# ZemskyHarness

Android harness app that loads the uploaded Zemsky native OMR libraries and exposes a local HTTP API inside the emulator.

Endpoints:
- `GET http://127.0.0.1:8084/`
- `GET http://127.0.0.1:8084/docs`
- `POST http://127.0.0.1:8084/process`

`POST /process` expects multipart form-data with a `file` field and returns:

```json
{
  "musicxml": "...",
  "notePositions": null,
  "processedImage": null
}
```

## Build

```bash
cd ZemskyHarness
./gradlew installDebug
```

## Launch in emulator

```bash
adb shell am start -n com.musiceye.zemskyharness/.MainActivity
```

## Use from NoteScan

1. Start an Android emulator.
2. Launch `ZemskyHarness` in that emulator.
3. Run `expo start --android` from `NoteScan`.
4. In NoteScan settings, select `Zemsky Emulator`.
5. Scan an image. NoteScan will POST it to `http://127.0.0.1:8084/process` inside the emulator.

## Native payload source

The harness packages these files copied from the uploaded bundle:
- `app/src/main/jniLibs/arm64-v8a/*.so`
- `app/src/main/assets/nnModels/*`
- `app/src/main/assets/templates/*`
