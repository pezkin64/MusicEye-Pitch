/**
 * OMRSettings — Shared settings for OMR engine.
 * Supports one engine:
 *   - 'zemsky'    — Android emulator harness backed by native .so files
 */
import AsyncStorage from '@react-native-async-storage/async-storage';

const STORAGE_KEY = '@omr_engine';
const RENDERER_STORAGE_KEY = '@score_renderer';

class OMRSettingsClass {
  _engine = 'zemsky';
  _defaultRenderer = 'osmd';

  /**
   * Get the current engine name.
   * @returns {'zemsky'}
   */
  getEngine() {
    return this._engine;
  }

  /**
   * Get the default score renderer.
   * @returns {'osmd' | 'verovio'}
   */
  getDefaultRenderer() {
    return this._defaultRenderer;
  }

  /** Load saved engine preference. */
  async load() {
    try {
      const saved = await AsyncStorage.getItem(STORAGE_KEY);
      if (saved === 'zemsky') {
        this._engine = saved;
      }

      const savedRenderer = await AsyncStorage.getItem(RENDERER_STORAGE_KEY);
      if (savedRenderer === 'osmd') {
        this._defaultRenderer = 'osmd';
      } else if (savedRenderer === 'verovio') {
        // Migrate existing installs back to OSMD as the safe default.
        this._defaultRenderer = 'osmd';
        try {
          await AsyncStorage.setItem(RENDERER_STORAGE_KEY, 'osmd');
        } catch (e) {
          // ignore
        }
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

  /** Set default rendered score engine. */
  async setDefaultRenderer(renderer) {
    if (renderer === 'osmd' || renderer === 'verovio') {
      this._defaultRenderer = renderer;
      try {
        await AsyncStorage.setItem(RENDERER_STORAGE_KEY, renderer);
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
