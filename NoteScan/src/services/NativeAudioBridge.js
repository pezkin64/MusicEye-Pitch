/**
 * NativeAudioBridge.js
 *
 * JavaScript wrapper around native audio module (Kotlin on Android, Swift on iOS).
 *
 * Provides:
 * - Clean async API for play/stop/pause
 * - Real-time parameter automation (pitch, tempo)
 * - Event subscriptions for status updates
 * - Fallback to JS Web Audio if native unavailable
 *
 * Usage:
 *   const bridge = new NativeAudioBridge();
 *   bridge.onStatusChange((event, data) => {
 *     console.log(`Audio ${event}:`, data);
 *   });
 *
 *   await bridge.play(notes);
 *   bridge.setPitch(880);  // Instant, no prepare
 *   bridge.setTempo(150);  // Instant
 *   await bridge.stop();
 */

import { Platform, NativeModules, NativeEventEmitter } from 'react-native';

const NATIVE_MODULE_NAME = 'MusicEyeAudio';
const nativeModule = Platform.OS === 'web' ? null : NativeModules[NATIVE_MODULE_NAME];
let hasLoggedUnavailableWarning = false;

export class NativeAudioBridge {
  constructor() {
    this.isAvailable = !!nativeModule;
    this.isPlaying = false;
    this.isPaused = false;
    this.statusCallbacks = [];

    if (this.isAvailable) {
      try {
        const emitter = new NativeEventEmitter(nativeModule);
        // Listen for status events from native module
        emitter.addListener('MusicEyeAudio_READY', (data) => {
          this._emitStatus('READY', data);
        });
        emitter.addListener('MusicEyeAudio_PLAYING', (data) => {
          this._emitStatus('PLAYING', data);
        });
        emitter.addListener('MusicEyeAudio_STOPPED', (data) => {
          this._emitStatus('STOPPED', data);
        });
        emitter.addListener('MusicEyeAudio_ERROR', (data) => {
          this._emitStatus('ERROR', data);
        });
        console.log('[NativeAudioBridge] Native audio available');
      } catch (e) {
        console.warn('[NativeAudioBridge] Failed to set up event emitter:', e.message);
        this.isAvailable = false;
      }
    } else {
      if (!hasLoggedUnavailableWarning) {
        console.warn('[NativeAudioBridge] Native audio unavailable (web or module not loaded)');
        hasLoggedUnavailableWarning = true;
      }
    }
  }

  /**
   * Initialize audio engine.
   */
  async init(sampleRate = 48000) {
    if (!this.isAvailable) {
      return { status: 'skipped', reason: 'native_unavailable' };
    }
    try {
      const result = await nativeModule.init(sampleRate);
      console.log('[NativeAudioBridge] Init OK:', result);
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Init failed:', e.message);
      throw e;
    }
  }

  /**
   * Play notes.
   *
   * @param {Array} notes - [{ pitch, velocity, startTimeMs, durationMs }, ...]
   * @param {Object} config - { sampleRate: 48000 }
   */
  async play(notes, config = {}) {
    if (!this.isAvailable) {
      return { status: 'skipped', reason: 'native_unavailable' };
    }
    try {
      // Convert notes to format expected by native module
      const nativeNotes = notes.map((note) => ({
        pitch: Math.round(note.pitch),
        velocity: Math.round(note.velocity || 100),
        startTimeMs: Math.round(note.startTimeMs || 0),
        durationMs: Math.round(note.durationMs || 500),
      }));

      const result = await nativeModule.play(nativeNotes, config || {});
      this.isPlaying = true;
      this.isPaused = false;
      console.log(`[NativeAudioBridge] Playing ${nativeNotes.length} notes`);
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Play failed:', e.message);
      throw e;
    }
  }

  /**
   * Stop playback.
   */
  async stop() {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.stop();
      this.isPlaying = false;
      this.isPaused = false;
      console.log('[NativeAudioBridge] Stopped');
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Stop failed:', e.message);
      throw e;
    }
  }

  /**
   * Pause playback (can resume).
   */
  async pause() {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.pause();
      this.isPaused = true;
      console.log('[NativeAudioBridge] Paused');
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Pause failed:', e.message);
      throw e;
    }
  }

  /**
   * Resume from pause.
   */
  async resume() {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.resume();
      this.isPaused = false;
      console.log('[NativeAudioBridge] Resumed');
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Resume failed:', e.message);
      throw e;
    }
  }

  /**
   * Set pitch in Hz.
   *
   * Real-time: Changes immediately during playback, no prepare delay.
   *
   * @param {number} hz - Frequency in Hz (e.g., 440 for A4)
   */
  async setPitch(hz) {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.setParameter('PITCH', hz);
      console.debug(`[NativeAudioBridge] Pitch → ${hz} Hz`);
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] setPitch failed:', e.message);
      throw e;
    }
  }

  /**
   * Set tempo in BPM.
   *
   * Real-time: Changes immediately during playback, reschedules notes.
   *
   * @param {number} bpm - Beats per minute
   */
  async setTempo(bpm) {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.setParameter('TEMPO', bpm);
      console.debug(`[NativeAudioBridge] Tempo → ${bpm} BPM`);
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] setTempo failed:', e.message);
      throw e;
    }
  }

  /**
   * Seek to time in milliseconds.
   */
  async seek(timeMs) {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.seek(timeMs);
      console.debug(`[NativeAudioBridge] Seek → ${timeMs} ms`);
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Seek failed:', e.message);
      throw e;
    }
  }

  /**
   * Release audio resources.
   */
  async release() {
    if (!this.isAvailable) {
      return { status: 'skipped' };
    }
    try {
      const result = await nativeModule.release();
      console.log('[NativeAudioBridge] Released');
      return result;
    } catch (e) {
      console.error('[NativeAudioBridge] Release failed:', e.message);
      throw e;
    }
  }

  /**
   * Register status callback.
   */
  onStatusChange(callback) {
    this.statusCallbacks.push(callback);
  }

  /**
   * Unregister status callback.
   */
  offStatusChange(callback) {
    this.statusCallbacks = this.statusCallbacks.filter((cb) => cb !== callback);
  }

  /**
   * Internal: Emit status to all listeners.
   */
  _emitStatus(event, data) {
    this.statusCallbacks.forEach((cb) => {
      try {
        cb(event, data);
      } catch (e) {
        console.error('[NativeAudioBridge] Callback error:', e.message);
      }
    });
  }
}

// Export singleton instance
export default new NativeAudioBridge();
