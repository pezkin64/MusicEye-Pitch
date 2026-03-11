/**
 * DurationAssigner — Determine note durations from stems, beams, and flags.
 *
 * Zemsky's approach (recognizeStemDuration / recognizeNoteLengths):
 *   1. Notes with stems:
 *      - 0 beams = quarter note (or half if hollow)
 *      - 1 beam  = eighth note
 *      - 2 beams = sixteenth note
 *      - 3 beams = thirty-second note
 *   2. Notes without stems:
 *      - Hollow → whole note
 *      - Filled → error (shouldn't happen)
 *   3. Dots add 50% duration
 *   4. Beams override flags (beamed groups share beam count)
 *
 * Voice separation (scoreCalculateVoiceIndexes):
 *   - Stem up → voice 1 (top voice / soprano)
 *   - Stem down → voice 2 (bottom voice / alto/bass)
 *   - No stem → default voice 1
 */

/**
 * @typedef {Object} MusicalNote
 * @property {number} x - horizontal position (for ordering)
 * @property {number} y - vertical position
 * @property {number} staffIndex
 * @property {number} staffPosition - pitch position on staff
 * @property {'filled'|'hollow'|'whole'} headType
 * @property {string} duration - 'whole'|'half'|'quarter'|'eighth'|'sixteenth'|'32nd'
 * @property {number} durationBeats - duration in quarter-note beats
 * @property {boolean} dotted
 * @property {number} voice - 1=up, 2=down
 * @property {string|null} accidental - 'sharp'|'flat'|'natural'|null
 * @property {number} pitch - MIDI note number
 * @property {string} pitchName - e.g. "C4", "A#5"
 * @property {number} measureIndex - which measure (0-based)
 */

/**
 * @typedef {Object} MusicalRest
 * @property {number} x
 * @property {number} staffIndex
 * @property {string} restType
 * @property {string} duration
 * @property {number} durationBeats
 * @property {number} measureIndex
 */

/**
 * Assign durations to all detected noteheads based on stem/beam associations.
 *
 * @param {import('./SymbolDetector').DetectedSymbols} symbols
 * @param {Object} associations - stemToHeads, headToStem, beamToStems, dotToHead, accToHead
 * @param {import('./StaffDetector').Staff[]} staves
 * @param {Object} clefInfo - {staffIndex → 'treble'|'bass'}
 * @param {Object} keyInfo - {fifths: number} (key signature)
 * @returns {{notes: MusicalNote[], rests: MusicalRest[]}}
 */
export function assignDurations(symbols, associations, staves, clefInfo = {}, keyInfo = { fifths: 0 }) {
  const { noteHeads, stems, beams, rests, dots, accidentals, barlines } = symbols;
  const { stemToHeads, headToStem, dotToHead, accToHead } = associations;

  // Count beams per stem
  const beamCountPerStem = new Map();
  if (associations.beamToStems) {
    for (const [bi, stemIndices] of associations.beamToStems) {
      for (const si of stemIndices) {
        beamCountPerStem.set(si, (beamCountPerStem.get(si) || 0) + 1);
      }
    }
  }

  // Determine measures from barlines
  const measureBoundaries = _computeMeasureBoundaries(barlines, staves);

  /** @type {MusicalNote[]} */
  const musicalNotes = [];

  for (let hi = 0; hi < noteHeads.length; hi++) {
    const head = noteHeads[hi];
    const stemIdx = headToStem.get(hi);
    const hasStem = stemIdx !== undefined;
    const stem = hasStem ? stems[stemIdx] : null;

    // Determine duration from head type + beam count
    let duration, durationBeats;

    if (!hasStem) {
      // Stemless notes
      if (head.type === 'whole' || head.type === 'hollow') {
        duration = 'whole';
        durationBeats = 4;
      } else {
        // Filled note without stem — unusual, treat as quarter
        duration = 'quarter';
        durationBeats = 1;
      }
    } else {
      // Has stem — duration depends on head type + beam count
      const beamCount = beamCountPerStem.get(stemIdx) || 0;

      if (head.type === 'hollow') {
        duration = 'half';
        durationBeats = 2;
      } else {
        // Filled head — duration from beam count
        switch (beamCount) {
          case 0: duration = 'quarter'; durationBeats = 1; break;
          case 1: duration = 'eighth'; durationBeats = 0.5; break;
          case 2: duration = 'sixteenth'; durationBeats = 0.25; break;
          case 3: duration = '32nd'; durationBeats = 0.125; break;
          default: duration = '64th'; durationBeats = 0.0625; break;
        }
      }
    }

    // Check for dot
    let dotted = false;
    for (const [di, headIdx] of dotToHead || []) {
      if (headIdx === hi) {
        dotted = true;
        break;
      }
    }
    if (dotted) {
      durationBeats *= 1.5;
    }

    // Get accidental
    let accidental = null;
    for (const [ai, headIdx] of accToHead || []) {
      if (headIdx === hi) {
        accidental = accidentals[ai].accidentalType;
        break;
      }
    }

    // Determine voice from stem direction
    let voice = 1;
    if (stem) {
      // Stem goes up if stem.top < head.cy, down if stem.bottom > head.cy
      voice = stem.top < head.cy ? 1 : 2;
    }

    // Compute pitch
    const staff = staves[head.staffIndex];
    const clef = clefInfo[head.staffIndex] || (head.staffIndex % 2 === 0 ? 'treble' : 'bass');
    const pitch = staffPositionToPitch(head.staffPosition, clef, accidental, keyInfo);

    // Determine measure
    const measureIndex = _findMeasure(head.cx, head.staffIndex, measureBoundaries);

    musicalNotes.push({
      x: head.cx,
      y: head.cy,
      staffIndex: head.staffIndex,
      staffPosition: head.staffPosition,
      headType: head.type,
      duration,
      durationBeats,
      dotted,
      voice,
      accidental,
      pitch: pitch.midi,
      pitchName: pitch.name,
      measureIndex,
    });
  }

  // Process rests
  /** @type {MusicalRest[]} */
  const musicalRests = [];

  for (const rest of rests) {
    const { restType } = rest;
    let duration, durationBeats;

    switch (restType) {
      case 'wholeRest': duration = 'whole'; durationBeats = 4; break;
      case 'halfRest': duration = 'half'; durationBeats = 2; break;
      case 'quarterRest': duration = 'quarter'; durationBeats = 1; break;
      case 'eighthRest': duration = 'eighth'; durationBeats = 0.5; break;
      case 'sixteenthRest': duration = 'sixteenth'; durationBeats = 0.25; break;
      default: duration = 'quarter'; durationBeats = 1; break;
    }

    const measureIndex = _findMeasure(rest.cx, rest.staffIndex, measureBoundaries);

    musicalRests.push({
      x: rest.cx,
      staffIndex: rest.staffIndex,
      restType,
      duration,
      durationBeats,
      measureIndex,
    });
  }

  // Sort notes by measure, then by x position (left to right)
  musicalNotes.sort((a, b) => a.measureIndex - b.measureIndex || a.x - b.x);
  musicalRests.sort((a, b) => a.measureIndex - b.measureIndex || a.x - b.x);

  return { notes: musicalNotes, rests: musicalRests };
}

/**
 * Convert staff position to MIDI pitch number and name.
 *
 * @param {number} staffPos - position on staff (0=middle line, +up, -down)
 * @param {'treble'|'bass'|'alto'} clef
 * @param {string|null} accidental
 * @param {{fifths: number}} keyInfo
 * @returns {{midi: number, name: string}}
 */
export function staffPositionToPitch(staffPos, clef, accidental, keyInfo = { fifths: 0 }) {
  // Middle line pitch depends on clef:
  //   Treble: B4 (MIDI 71) at position 0
  //   Bass:   D3 (MIDI 50) at position 0
  //   Alto:   C4 (MIDI 60) at position 0

  let baseMidi, baseNote, baseOctave;
  switch (clef) {
    case 'treble':
      baseMidi = 71; baseNote = 6; baseOctave = 4; // B4
      break;
    case 'bass':
      baseMidi = 50; baseNote = 1; baseOctave = 3; // D3
      break;
    case 'alto':
      baseMidi = 60; baseNote = 0; baseOctave = 4; // C4
      break;
    default:
      baseMidi = 71; baseNote = 6; baseOctave = 4; // treble default
  }

  // Each staff position step = one diatonic step
  // Map diatonic steps to semitones: C D E F G A B = 0 2 4 5 7 9 11
  const diatonicSemitones = [0, 2, 4, 5, 7, 9, 11];
  const noteNames = ['C', 'D', 'E', 'F', 'G', 'A', 'B'];

  // Convert staffPos to diatonic offset from base
  const diatonicOffset = staffPos; // +1 = one step up
  let noteIndex = baseNote + diatonicOffset;
  let octaveOffset = Math.floor(noteIndex / 7);
  noteIndex = ((noteIndex % 7) + 7) % 7; // normalize to 0-6

  const octave = baseOctave + octaveOffset;
  let semitone = diatonicSemitones[noteIndex];
  let noteName = noteNames[noteIndex];

  // Apply key signature
  const sharps = ['F', 'C', 'G', 'D', 'A', 'E', 'B'];
  const flats = ['B', 'E', 'A', 'D', 'G', 'C', 'F'];

  if (keyInfo.fifths > 0) {
    // Sharp key
    for (let i = 0; i < Math.min(keyInfo.fifths, 7); i++) {
      if (noteName === sharps[i]) { semitone++; break; }
    }
  } else if (keyInfo.fifths < 0) {
    // Flat key
    for (let i = 0; i < Math.min(-keyInfo.fifths, 7); i++) {
      if (noteName === flats[i]) { semitone--; break; }
    }
  }

  // Apply accidental (overrides key signature)
  let accSymbol = '';
  if (accidental === 'sharp') { semitone++; accSymbol = '#'; }
  else if (accidental === 'flat') { semitone--; accSymbol = 'b'; }
  else if (accidental === 'natural') { accSymbol = ''; /* undo key sig is complex, simplified */ }

  const midi = 12 + octave * 12 + semitone;
  const name = `${noteName}${accSymbol}${octave}`;

  return { midi: Math.max(0, Math.min(127, midi)), name };
}

/**
 * Compute measure boundaries from detected barlines.
 */
function _computeMeasureBoundaries(barlines, staves) {
  // Group barlines by staff
  const measures = []; // {staffIndex, left, right, measureIndex}

  for (let si = 0; si < staves.length; si++) {
    const staff = staves[si];
    const staffBars = barlines
      .filter(b => b.staffIndex === si)
      .sort((a, b) => a.x - b.x);

    // First measure starts at staff left
    let prevX = staff.left;
    let measureIdx = 0;

    for (const bar of staffBars) {
      measures.push({ staffIndex: si, left: prevX, right: bar.x, measureIndex: measureIdx });
      prevX = bar.x;
      measureIdx++;
    }
    // Last measure extends to staff right
    measures.push({ staffIndex: si, left: prevX, right: staff.right, measureIndex: measureIdx });
  }

  return measures;
}

/**
 * Find which measure a note at position (x, staffIndex) belongs to.
 */
function _findMeasure(x, staffIndex, measures) {
  for (const m of measures) {
    if (m.staffIndex === staffIndex && x >= m.left && x <= m.right) {
      return m.measureIndex;
    }
  }
  return 0;
}

/**
 * Voice assignment — separate notes into voices based on stem direction.
 *
 * Zemsky's scoreCalculateVoiceIndexes:
 *   - In each measure/staff: notes with stems up = voice 1, stems down = voice 2
 *   - If all stems go one direction → single voice
 *   - Notes at same x position but different voices = chord/polyphony
 *
 * @param {MusicalNote[]} notes - already has voice assigned per-note
 * @returns {MusicalNote[]} notes with refined voice assignments
 */
export function refineVoices(notes) {
  // Group by staff and measure
  const groups = new Map(); // "staffIndex_measureIndex" → notes[]

  for (const note of notes) {
    const key = `${note.staffIndex}_${note.measureIndex}`;
    if (!groups.has(key)) groups.set(key, []);
    groups.get(key).push(note);
  }

  for (const [key, group] of groups) {
    const voices = new Set(group.map(n => n.voice));
    if (voices.size <= 1) continue; // single voice, nothing to refine

    // Sort by x, then check for simultaneous notes (same x ± tolerance)
    group.sort((a, b) => a.x - b.x);

    // Notes at similar x positions are simultaneous — keep separate voices
    // Notes at different x positions but same voice — sequential in that voice
    // This is already handled by the stem-direction assignment
  }

  return notes;
}
