/**
 * OMRSettings — Shared settings for OMR engine.
 * Audiveris is the sole engine.
 */

class OMRSettingsClass {
  /**
   * Get the current engine name.
   * @returns {'audiveris'}
   */
  getEngine() {
    return 'audiveris';
  }

  /** No-op — kept for compatibility. */
  async load() {}

  /** No-op — kept for compatibility. */
  async setEngine(_engine) {}

  /**
   * Get the Audiveris service instance.
   */
  getService() {
    const { AudiverisService } = require('./AudiverisService');
    return AudiverisService;
  }
}

export const OMRSettings = new OMRSettingsClass();
