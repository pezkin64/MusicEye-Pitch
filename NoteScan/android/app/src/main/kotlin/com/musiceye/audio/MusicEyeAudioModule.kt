package com.musiceye.audio

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.modules.core.DeviceEventManagerModule
import android.util.Log

/**
 * Exposes NativeAudioModule to React Native via Native Modules API.
 *
 * Usage from JS:
 *   const module = NativeModules.MusicEyeAudio;
 *   module.play([notes], {}, (status) => { console.log(status) });
 */
class MusicEyeAudioModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    companion object {
        private const val TAG = "MusicEyeAudioModule"
        private const val MODULE_NAME = "MusicEyeAudio"
    }

    private var audioModule: NativeAudioModule? = null

    init {
        audioModule = NativeAudioModule(reactContext)
        audioModule?.setStatusCallback { event, data ->
            sendEvent(event, data)
        }
        Log.d(TAG, "Initialized MusicEyeAudioModule")
    }

    override fun getName(): String = MODULE_NAME

    /**
     * Initialize the audio engine.
     *
     * @param sampleRate Audio sample rate (default 48000)
     * @param promise Resolve when ready
     */
    @ReactMethod
    fun init(sampleRate: Int, promise: Promise) {
        try {
            audioModule?.init(sampleRate)
            promise.resolve(mapOf("status" to "ok"))
        } catch (e: Exception) {
            Log.e(TAG, "Init failed", e)
            promise.reject("INIT_ERROR", e.message)
        }
    }

    /**
     * Play notes.
     *
     * @param notesArray [
     *   { pitch: 60, velocity: 100, startTimeMs: 0, durationMs: 500 },
     *   ...
     * ]
     * @param config { sampleRate: 48000 }
     * @param promise Resolve when started
     */
    @ReactMethod
    fun play(notesArray: ReadableArray, config: ReadableMap?, promise: Promise) {
        try {
            val notes = mutableListOf<NativeNote>()
            for (i in 0 until notesArray.size()) {
                val noteMap = notesArray.getMap(i) ?: continue
                notes.add(
                    NativeNote(
                        pitch = noteMap.getInt("pitch"),
                        velocity = noteMap.getInt("velocity"),
                        startTimeMs = noteMap.getLong("startTimeMs"),
                        durationMs = noteMap.getLong("durationMs")
                    )
                )
            }

            audioModule?.play(notes, null)
            promise.resolve(mapOf("status" to "playing", "noteCount" to notes.size))
        } catch (e: Exception) {
            Log.e(TAG, "Play failed", e)
            promise.reject("PLAY_ERROR", e.message)
        }
    }

    /**
     * Stop playback.
     */
    @ReactMethod
    fun stop(promise: Promise) {
        try {
            audioModule?.stop()
            promise.resolve(mapOf("status" to "stopped"))
        } catch (e: Exception) {
            Log.e(TAG, "Stop failed", e)
            promise.reject("STOP_ERROR", e.message)
        }
    }

    /**
     * Pause playback.
     */
    @ReactMethod
    fun pause(promise: Promise) {
        try {
            audioModule?.pause()
            promise.resolve(mapOf("status" to "paused"))
        } catch (e: Exception) {
            Log.e(TAG, "Pause failed", e)
            promise.reject("PAUSE_ERROR", e.message)
        }
    }

    /**
     * Resume from pause.
     */
    @ReactMethod
    fun resume(promise: Promise) {
        try {
            audioModule?.resume()
            promise.resolve(mapOf("status" to "resumed"))
        } catch (e: Exception) {
            Log.e(TAG, "Resume failed", e)
            promise.reject("RESUME_ERROR", e.message)
        }
    }

    /**
     * Set real-time parameter (pitch or tempo).
     *
     * @param key "PITCH" | "TEMPO"
     * @param value frequency in Hz or tempo in BPM
     */
    @ReactMethod
    fun setParameter(key: String, value: Double, promise: Promise) {
        try {
            audioModule?.setParameter(key, value)
            promise.resolve(mapOf("status" to "ok", "key" to key, "value" to value))
        } catch (e: Exception) {
            Log.e(TAG, "setParameter failed", e)
            promise.reject("PARAM_ERROR", e.message)
        }
    }

    /**
     * Seek to time in milliseconds.
     */
    @ReactMethod
    fun seek(timeMs: Long, promise: Promise) {
        try {
            audioModule?.seek(timeMs)
            promise.resolve(mapOf("status" to "ok", "timeMs" to timeMs))
        } catch (e: Exception) {
            Log.e(TAG, "Seek failed", e)
            promise.reject("SEEK_ERROR", e.message)
        }
    }

    /**
     * Release audio resources.
     */
    @ReactMethod
    fun release(promise: Promise) {
        try {
            audioModule?.release()
            promise.resolve(mapOf("status" to "released"))
        } catch (e: Exception) {
            Log.e(TAG, "Release failed", e)
            promise.reject("RELEASE_ERROR", e.message)
        }
    }

    /**
     * Send status event to JS.
     */
    private fun sendEvent(eventName: String, data: Map<String, Any>) {
        reactApplicationContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit("MusicEyeAudio_$eventName", data)
    }
}
