/**
 * OMRSettings — Shared settings for OMR engine.
 * Supports one engine:
 *   - 'zemsky'    — Android emulator harness backed by native .so files
 */
import AsyncStorage from '@react-native-async-storage/async-storage';

const STORAGE_KEY = '@omr_engine';

class OMRSettingsClass {
  _engine = 'zemsky';

  /**
   * Get the current engine name.
   * @returns {'zemsky'}
   */
  getEngine() {
    return this._engine;
  }

  /** Load saved engine preference. */
  async load() {
    try {
      const saved = await AsyncStorage.getItem(STORAGE_KEY);
      if (saved === 'zemsky') {
        this._engine = saved;
      }
    } catch (e) {
      // ignore — use default
    }
  }

  /** Switch engine. */
  async setEngine(engine) {
    if (engine === 'zemsky') {
      this._engine = engine;
      try {
        await AsyncStorage.setItem(STORAGE_KEY, engine);
      } catch (e) {
        // ignore
      }
    }
  }

  /**
   * Get the active service instance.
   */
  getService() {
    const { ZemskyEmulatorService } = require('./ZemskyEmulatorService');
    return ZemskyEmulatorService;
  }
}

export const OMRSettings = new OMRSettingsClass();
