/**
 * OnDeviceOMR — Complete on-device Optical Music Recognition engine.
 *
 * Zemsky-inspired rule-based pipeline running entirely in JavaScript.
 * No server required — processes images directly on the phone.
 *
 * Pipeline:
 *   1. Binarization (Sauvola adaptive threshold)
 *   2. Staff detection (column-sliced horizontal projection)
 *   3. Staff removal (scan-based with intersection preservation)
 *   4. Symbol detection (connected components + shape classification)
 *   5. Symbol association (stem↔head, beam↔stem, dot↔note, accidental↔note)
 *   6. Duration assignment (beam count → note duration)
 *   7. Pitch calculation (staff position → MIDI)
 *   8. Voice separation (stem direction)
 *   9. MusicXML export
 *
 * Usage:
 *   const result = await OnDeviceOMR.processImage(grayImageData, width, height);
 *   // result.musicxml — MusicXML string
 *   // result.notes    — parsed note array for playback
 */

import {
  createImage,
  rgbaToGray,
  otsuBinarize,
  sauvolaBinarize,
  scaleImage,
} from './ImageUtils';
import { detectStaffs } from './StaffDetector';
import { removeStaffLines } from './StaffRemover';
import {
  detectSymbols,
  associateStemsWithHeads,
  associateBeamsWithStems,
  associateDotsWithNotes,
  associateAccidentalsWithNotes,
} from './SymbolDetector';
import { assignDurations, refineVoices } from './DurationAssigner';
import { exportToMusicXML } from './MusicXMLExporter';

const LOG_PREFIX = '🎵 OMR';

function log(msg) {
  console.log(`${LOG_PREFIX}: ${msg}`);
}

class OnDeviceOMRClass {
  /**
   * Process a grayscale image buffer and return recognized music.
   *
   * @param {Uint8Array} grayData - grayscale pixel data (0-255)
   * @param {number} width
   * @param {number} height
   * @param {Function} onProgress - optional progress callback
   * @returns {Promise<{musicxml: string, notes: Array, metadata: Object}>}
   */
  async processGrayImage(grayData, width, height, onProgress) {
    const report = (msg) => {
      log(msg);
      if (onProgress) onProgress(msg);
    };

    const startTime = Date.now();

    // Wrap in GrayImage
    const gray = { data: new Uint8Array(grayData), width, height };
    report(`Input: ${width}×${height}`);

    // --- Step 1: Resize if needed ---
    let processed = gray;
    const longEdge = Math.max(width, height);
    const MIN_LONG = 2000;
    const MAX_LONG = 3500;

    if (longEdge < MIN_LONG) {
      const scale = MIN_LONG / longEdge;
      processed = scaleImage(processed, scale);
      report(`Upscaled → ${processed.width}×${processed.height}`);
    } else if (longEdge > MAX_LONG) {
      const scale = MAX_LONG / longEdge;
      processed = scaleImage(processed, scale);
      report(`Downscaled → ${processed.width}×${processed.height}`);
    }

    // --- Step 2: Binarize ---
    report('Binarizing...');
    let binary;
    try {
      binary = sauvolaBinarize(processed, 40, 0.15);
    } catch (e) {
      report('Sauvola failed, falling back to Otsu');
      binary = otsuBinarize(processed);
    }

    // Validate ink coverage
    let inkCount = 0;
    for (let i = 0; i < binary.data.length; i++) {
      if (binary.data[i] === 0) inkCount++;
    }
    const inkRatio = inkCount / binary.data.length;
    report(`Ink coverage: ${(inkRatio * 100).toFixed(1)}%`);

    if (inkRatio < 0.01 || inkRatio > 0.5) {
      report('Unusual ink coverage — trying Otsu fallback');
      binary = otsuBinarize(processed);
    }

    // --- Step 3: Staff detection ---
    report('Detecting staves...');
    const staves = detectStaffs(binary);
    report(`Found ${staves.length} staves`);

    if (staves.length === 0) {
      throw new Error(
        'No staff lines detected. Make sure the image shows clear music notation ' +
        'with visible staff lines.'
      );
    }

    // Log staff details
    for (let i = 0; i < staves.length; i++) {
      const s = staves[i];
      report(`  Staff ${i + 1}: y=${s.top}-${s.bottom} interline=${s.interline.toFixed(1)}px angle=${s.angle.toFixed(2)}°`);
    }

    const avgInterline = staves.reduce((s, st) => s + st.interline, 0) / staves.length;

    // --- Step 4: Staff removal ---
    report('Removing staff lines...');
    const staffRemoved = removeStaffLines(binary, staves);

    // --- Step 5: Symbol detection ---
    report('Detecting symbols...');
    const symbols = detectSymbols(staffRemoved, binary, staves);
    report(`Detected: ${symbols.noteHeads.length} heads, ${symbols.stems.length} stems, ` +
           `${symbols.beams.length} beams, ${symbols.rests.length} rests, ` +
           `${symbols.dots.length} dots, ${symbols.accidentals.length} accidentals, ` +
           `${symbols.barlines.length} barlines`);

    // --- Step 6: Symbol association ---
    report('Associating symbols...');
    const { stemToHeads, headToStem } = associateStemsWithHeads(
      symbols.stems, symbols.noteHeads, avgInterline
    );
    const beamToStems = associateBeamsWithStems(symbols.beams, symbols.stems, avgInterline);
    const dotToHead = associateDotsWithNotes(symbols.dots, symbols.noteHeads, avgInterline);
    const accToHead = associateAccidentalsWithNotes(symbols.accidentals, symbols.noteHeads, avgInterline);

    report(`Associated: ${headToStem.size}/${symbols.noteHeads.length} heads with stems, ` +
           `${beamToStems.size} beam groups`);

    // --- Step 7: Duration assignment + pitch ---
    report('Assigning durations and pitches...');

    // Detect clefs (simplified: first staff = treble, second = bass for grand staff)
    const clefs = {};
    for (let i = 0; i < staves.length; i++) {
      // Default: alternating treble/bass for grand staff pairs
      clefs[i] = i % 2 === 0 ? 'treble' : 'bass';
    }

    const { notes: musicalNotes, rests: musicalRests } = assignDurations(
      symbols,
      { stemToHeads, headToStem, beamToStems, dotToHead, accToHead },
      staves,
      clefs,
      { fifths: 0 } // TODO: detect key signature
    );

    // Refine voices
    const refinedNotes = refineVoices(musicalNotes);

    report(`Result: ${refinedNotes.length} notes, ${musicalRests.length} rests`);

    // --- Step 8: MusicXML export ---
    report('Generating MusicXML...');
    const musicxml = exportToMusicXML({
      notes: refinedNotes,
      rests: musicalRests,
      staves,
      timeSignature: { beats: 4, beatType: 4 }, // TODO: detect time signature
      keySignature: { fifths: 0 },
      clefs,
      title: 'Scanned Score',
    });

    const elapsed = Date.now() - startTime;
    report(`Done in ${elapsed}ms — ${musicxml.length} chars MusicXML`);

    // Convert notes to the format expected by AudioPlaybackService
    const playbackNotes = _convertToPlaybackFormat(refinedNotes, musicalRests);

    return {
      musicxml,
      notes: playbackNotes,
      metadata: {
        staves: staves.length,
        systems: 1, // TODO: detect system breaks
        timeSignature: { beats: 4, beatType: 4 },
        keySignature: { type: 'None', count: 0 },
        totalNotes: refinedNotes.length,
        totalRests: musicalRests.length,
        processingTimeMs: elapsed,
        engine: 'on-device',
      },
    };
  }

  /**
   * Process RGBA image data (from camera/picker).
   */
  async processRGBAImage(rgbaData, width, height, onProgress) {
    const gray = rgbaToGray(rgbaData, width, height);
    return this.processGrayImage(gray.data, gray.width, gray.height, onProgress);
  }
}

/**
 * Convert our internal note format to the format expected by AudioPlaybackService/PlaybackScreen.
 */
function _convertToPlaybackFormat(notes, rests) {
  const PITCH_NAMES = ['C', 'C#', 'D', 'D#', 'E', 'F', 'F#', 'G', 'G#', 'A', 'A#', 'B'];

  let beatOffset = 0;
  const allEvents = [
    ...notes.map(n => ({ ...n, isRest: false })),
    ...rests.map(r => ({ ...r, isRest: true })),
  ].sort((a, b) => a.measureIndex - b.measureIndex || a.x - b.x);

  const result = [];
  let lastMeasure = -1;
  let measureBeatOffset = 0;

  for (const event of allEvents) {
    if (event.measureIndex !== lastMeasure) {
      if (lastMeasure >= 0) {
        measureBeatOffset += 4; // TODO: use actual time signature
      }
      lastMeasure = event.measureIndex;
    }

    const durationBeats = event.durationBeats || 1;

    if (event.isRest) {
      result.push({
        type: 'rest',
        pitch: null,
        midiNote: 0,
        duration: event.duration,
        durationBeats,
        dotted: false,
        voice: 'Soprano',
        staffIndex: event.staffIndex || 0,
        systemIndex: 0,
        measureIndex: event.measureIndex || 0,
        accidental: null,
        tiedBeats: durationBeats,
        beatOffset: measureBeatOffset,
      });
    } else {
      const midi = event.pitch || 60;
      const noteInOctave = midi % 12;
      const octave = Math.floor(midi / 12) - 1;
      const pitchName = `${PITCH_NAMES[noteInOctave]}${octave}`;

      const voiceName = event.voice === 1 ? 'Soprano' :
                        event.voice === 2 ? 'Alto' :
                        event.voice === 3 ? 'Tenor' : 'Bass';

      result.push({
        type: 'note',
        pitch: pitchName,
        midiNote: midi,
        duration: event.duration,
        durationBeats,
        dotted: event.dotted || false,
        voice: voiceName,
        staffIndex: event.staffIndex || 0,
        systemIndex: 0,
        measureIndex: event.measureIndex || 0,
        accidental: event.accidental || null,
        tiedBeats: durationBeats,
        beatOffset: measureBeatOffset,
      });
    }

    measureBeatOffset += durationBeats;
  }

  return result;
}

export const OnDeviceOMR = new OnDeviceOMRClass();
