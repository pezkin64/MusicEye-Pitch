package com.musiceye.audio

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.util.Log
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.scheduleAtFixedRate

/**
 * Real-time DSP audio engine for Music Eye.
 *
 * Handles:
 * - Audio callback (AudioTrack's PCM write loop)
 * - Note scheduling (Timer-based, like competitor)
 * - Real-time parameter automation (pitch, tempo)
 *
 * Architecture:
 * JS → NativeAudioBridge → NativeAudioModule.dispatch(command)
 *   → Audio callback mixes audio every 10ms
 *   → JSBridge.sendEvent(status) → React state update
 */

data class NativeNote(
    val pitch: Int,        // MIDI note 0-127
    val velocity: Int,     // 0-127
    val startTimeMs: Long,
    val durationMs: Long
)

data class SynthState(
    var pitchCents: Double = 0.0,    // Real-time pitch offset
    var tempo: Double = 120.0,       // BPM
    var voiceIndex: Int = 0          // Voice 1 or 2
)

class NativeAudioModule(private val context: Context) {
    companion object {
        private const val TAG = "MusicEyeAudio"
        private const val SAMPLE_RATE = 48000
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_OUT_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        private const val BUFFER_SIZE_FRAMES = 512  // ~10ms @ 48kHz
    }

    // Audio output
    private var audioTrack: AudioTrack? = null
    private var pcmBuffer = ShortArray(BUFFER_SIZE_FRAMES)

    // Scheduling
    private val scheduler = Timer()
    private val noteQueue = mutableListOf<NativeNote>()
    private val activeNotes = mutableListOf<ActiveNote>()
    private var callbackTimer: Timer? = null

    // Parameter automation
    private val synthState = SynthState()

    // Status callbacks
    private var onStatus: ((String, Map<String, Any>) -> Unit)? = null

    // Flags
    private var isPlaying = false
    private var isPaused = false
    private var currentTimeMs: Long = 0

    /**
     * Initialize audio engine with sample rate.
     */
    fun init(sampleRate: Int = SAMPLE_RATE) {
        if (audioTrack != null) {
            Log.w(TAG, "Already initialized")
            return
        }

        val bufferSize = AudioTrack.getMinBufferSize(sampleRate, CHANNEL_CONFIG, AUDIO_FORMAT)
        val bufferSizeFrames = maxOf(bufferSize / 2, BUFFER_SIZE_FRAMES * 4)

        audioTrack = AudioTrack(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build(),
            AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setChannelMask(CHANNEL_CONFIG)
                .setEncoding(AUDIO_FORMAT)
                .build(),
            bufferSizeFrames,
            AudioTrack.MODE_STREAM,
            android.media.AudioManager.AUDIO_SESSION_ID_GENERATE
        )

        Log.d(TAG, "Initialized AudioTrack: sampleRate=$sampleRate bufferSize=$bufferSizeFrames")
    }

    /**
     * Play notes with given configuration.
     *
     * Command: { type: 'PLAY', notes: [...], config: { sampleRate: 48000 } }
     */
    fun play(notes: List<NativeNote>, config: Map<String, Any>? = null) {
        if (audioTrack == null) {
            init()
        }

        // Reset state
        noteQueue.clear()
        activeNotes.clear()
        currentTimeMs = 0
        synthState.tempo = 120.0
        synthState.pitchCents = 0.0

        // Load notes into queue
        noteQueue.addAll(notes)

        // Schedule all notes
        scheduleAllNotes()

        // Start audio playback
        audioTrack?.play()
        isPlaying = true
        isPaused = false

        callbackTimer?.cancel()
        callbackTimer = Timer("MusicEyeAudioCallback", true)
        callbackTimer?.scheduleAtFixedRate(0L, 10L) {
            onAudioCallback()
        }

        Log.d(TAG, "Playing ${notes.size} notes")
        onStatus?.invoke("PLAYING", mapOf("currentTimeMs" to 0))
    }

    /**
     * Stop playback and clean up.
     */
    fun stop() {
        if (!isPlaying) return

        audioTrack?.stop()
        audioTrack?.flush()
        isPlaying = false
        isPaused = false
        callbackTimer?.cancel()
        callbackTimer = null

        scheduler.purge()
        activeNotes.clear()
        noteQueue.clear()
        currentTimeMs = 0

        Log.d(TAG, "Stopped")
        onStatus?.invoke("STOPPED", mapOf())
    }

    /**
     * Pause playback (can resume).
     */
    fun pause() {
        audioTrack?.pause()
        isPaused = true
        Log.d(TAG, "Paused")
    }

    /**
     * Resume from pause.
     */
    fun resume() {
        audioTrack?.play()
        isPaused = false
        Log.d(TAG, "Resumed")
    }

    /**
     * Handle parameter automation (pitch, tempo).
     *
     * Command: { type: 'PARAM', key: 'PITCH', value: 880.0 }
     *          { type: 'PARAM', key: 'TEMPO', value: 150 }
     */
    fun setParameter(key: String, value: Double) {
        when (key) {
            "PITCH" -> {
                // Convert Hz to cents offset from A440
                val cents = 1200.0 * Math.log(value / 440.0) / Math.log(2.0)
                synthState.pitchCents = cents
                Log.d(TAG, "Set pitch to $value Hz ($cents cents)")
            }
            "TEMPO" -> {
                val oldTempo = synthState.tempo
                synthState.tempo = value
                Log.d(TAG, "Set tempo to $value BPM (was $oldTempo)")
                // TODO: Re-schedule notes with new tempo
            }
            else -> {
                Log.w(TAG, "Unknown parameter: $key")
            }
        }
    }

    /**
     * Seek to time in milliseconds.
     */
    fun seek(timeMs: Long) {
        currentTimeMs = timeMs
        // TODO: Reschedule remaining notes from this point
        Log.d(TAG, "Seek to $timeMs ms")
    }

    /**
     * Schedule all notes using Timer.
     *
     * Matches competitor's TargetRequest pattern:
     * - Each note gets a scheduled noteOn at startTime
     * - Each note gets a scheduled noteOff at (startTime + duration)
     */
    private fun scheduleAllNotes() {
        for (note in noteQueue) {
            // Schedule noteOn
            scheduler.schedule(note.startTimeMs) {
                if (isPlaying && !isPaused) {
                    noteOn(note.pitch, note.velocity)
                }
            }

            // Schedule noteOff
            scheduler.schedule(note.startTimeMs + note.durationMs) {
                if (isPlaying && !isPaused) {
                    noteOff(note.pitch)
                }
            }
        }
    }

    /**
     * Audio callback - called every ~10ms by AudioTrack.
     *
     * Responsibility:
     * 1. Mix all active notes into pcmBuffer
     * 2. Write buffer to AudioTrack
     * 3. Update currentTimeMs
     */
    private fun onAudioCallback() {
        if (!isPlaying || isPaused) return

        // Fill PCM buffer by mixing active notes
        mixChunk(pcmBuffer, BUFFER_SIZE_FRAMES)

        // Write to AudioTrack
        val written = audioTrack?.write(pcmBuffer, 0, BUFFER_SIZE_FRAMES) ?: 0
        if (written > 0) {
            currentTimeMs += (written * 1000) / SAMPLE_RATE
        }

        // Periodic status update
        if (currentTimeMs % 100 < 10) {  // Every ~100ms
            onStatus?.invoke("PLAYING", mapOf("currentTimeMs" to currentTimeMs))
        }
    }

    /**
     * Mix active notes into output buffer.
     *
     * TODO: Implement actual waveform synthesis from SoundFont samples.
     * For POC, generate simple sine wave.
     */
    private fun mixChunk(buffer: ShortArray, frameCount: Int) {
        // Clear buffer
        buffer.fill(0)

        // For each active note, add its waveform
        for (activeNote in activeNotes) {
            addNoteToBuffer(buffer, activeNote, frameCount)
        }

        // Normalize to prevent clipping
        val maxSample = buffer.maxOrNull() ?: 1
        if (maxSample > 32000) {
            val scale = 32000.0 / maxSample
            for (i in buffer.indices) {
                buffer[i] = (buffer[i] * scale).toInt().toShort()
            }
        }
    }

    /**
     * Add a single note's waveform to the output buffer.
     *
     * POC: Generate sine wave at MIDI pitch + pitchCents offset.
     * TODO: Replace with SoundFont sample-based synthesis.
     */
    private fun addNoteToBuffer(buffer: ShortArray, note: ActiveNote, frameCount: Int) {
        val midiNote = note.pitch
        val baseFrequency = 440.0 * Math.pow(2.0, (midiNote - 69) / 12.0)
        val pitchHz = baseFrequency * Math.pow(2.0, synthState.pitchCents / 1200.0)

        val amplitude = (note.velocity / 127.0) * 20000.0  // Scale to PCM range

        for (i in 0 until frameCount) {
            val phase = (2.0 * Math.PI * pitchHz * (i.toDouble() / SAMPLE_RATE)) % (2.0 * Math.PI)
            val sample = (amplitude * Math.sin(phase)).toInt().toShort()
            buffer[i] = (buffer[i].toInt() + sample).toShort()
        }
    }

    /**
     * Internal: Note on event.
     */
    private fun noteOn(pitch: Int, velocity: Int) {
        activeNotes.add(ActiveNote(pitch, velocity, 0))
        Log.d(TAG, "Note ON: pitch=$pitch velocity=$velocity (${activeNotes.size} active)")
    }

    /**
     * Internal: Note off event.
     */
    private fun noteOff(pitch: Int) {
        activeNotes.removeAll { it.pitch == pitch }
        Log.d(TAG, "Note OFF: pitch=$pitch (${activeNotes.size} active)")
    }

    /**
     * Set status callback.
     */
    fun setStatusCallback(callback: (String, Map<String, Any>) -> Unit) {
        onStatus = callback
    }

    /**
     * Clean up resources.
     */
    fun release() {
        stop()
        scheduler.cancel()
        audioTrack?.release()
        audioTrack = null
        Log.d(TAG, "Released")
    }

    /**
     * Internal: Tracks a currently-sounding note.
     */
    private data class ActiveNote(
        val pitch: Int,
        val velocity: Int,
        var sampleIndex: Long
    )
}
