/**
 * SymbolDetector — Detect noteheads, stems, beams, rests, and other symbols.
 *
 * Zemsky-inspired pipeline:
 *   1. Connected component analysis on staff-removed image
 *   2. Classify components by size and shape:
 *      - Noteheads: roughly circular, width ≈ 1.2–1.8 × interline
 *      - Stems: tall and thin verticals
 *      - Beams: wide and short horizontals
 *      - Rests: various shapes matched by RLE contour
 *      - Dots: tiny circles near noteheads
 *      - Accidentals: small symbols left of noteheads
 *   3. Template matching for confirmation (correlation score)
 *   4. Geometric rules for association (stem→head, beam→stem, dot→note)
 */

import {
  connectedComponents,
  cropImage,
  countBlack,
  horizontalRLE,
  verticalRLE,
  correlationScore,
  createImage,
  isBlack,
} from './ImageUtils';
import { getStaffPosition } from './StaffDetector';

/**
 * @typedef {Object} NoteHead
 * @property {number} x - bounding box x
 * @property {number} y - bounding box y
 * @property {number} w - width
 * @property {number} h - height
 * @property {number} cx - center x
 * @property {number} cy - center y
 * @property {number} area - pixel area
 * @property {number} staffIndex - which staff (0-based)
 * @property {number} staffPosition - pitch position on staff
 * @property {'filled'|'hollow'|'whole'} type - head type
 * @property {number} componentId - CC label
 */

/**
 * @typedef {Object} Stem
 * @property {number} x - x position (thin, so x ≈ center)
 * @property {number} top - top y
 * @property {number} bottom - bottom y
 * @property {number} length - pixel length
 * @property {number} staffIndex
 * @property {number} componentId
 */

/**
 * @typedef {Object} Beam
 * @property {number} x - left x
 * @property {number} y - top y
 * @property {number} w - width
 * @property {number} h - height
 * @property {number} staffIndex
 * @property {number} componentId
 */

/**
 * @typedef {Object} DetectedSymbols
 * @property {NoteHead[]} noteHeads
 * @property {Stem[]} stems
 * @property {Beam[]} beams
 * @property {Object[]} rests
 * @property {Object[]} dots
 * @property {Object[]} accidentals
 * @property {Object[]} barlines
 */

/**
 * Detect all symbols in the staff-removed image.
 * @param {import('./ImageUtils').GrayImage} staffRemoved - binary image with staff lines erased
 * @param {import('./ImageUtils').GrayImage} original - original binary (with staff lines)
 * @param {import('./StaffDetector').Staff[]} staves
 * @returns {DetectedSymbols}
 */
export function detectSymbols(staffRemoved, original, staves) {
  const { labels, count, stats } = connectedComponents(staffRemoved);

  const noteHeads = [];
  const stems = [];
  const beams = [];
  const rests = [];
  const dots = [];
  const accidentals = [];
  const barlines = [];

  // Reference metrics from staves
  const avgInterline = staves.reduce((s, st) => s + st.interline, 0) / staves.length;
  const avgLineHeight = staves.reduce((s, st) => s + st.lineHeight, 0) / staves.length;

  // Size thresholds based on staff geometry (Zemsky scales everything by interline)
  const noteWidth = avgInterline * 1.4;     // typical filled notehead width
  const minNoteW = avgInterline * 0.8;
  const maxNoteW = avgInterline * 2.2;
  const minNoteH = avgInterline * 0.6;
  const maxNoteH = avgInterline * 1.8;
  const minStemLength = avgInterline * 2.5;  // stems are at least 2.5 interlines
  const maxStemWidth = Math.max(avgLineHeight * 2.5, 4);
  const minBeamWidth = avgInterline * 2;
  const maxBeamHeight = avgInterline * 0.8;
  const minDotArea = 4;
  const maxDotSize = avgInterline * 0.6;

  for (const comp of stats) {
    if (comp.area < 3) continue; // noise

    // Determine which staff this component belongs to
    const staffIndex = _findStaffIndex(staves, comp.cy);
    if (staffIndex < 0) continue;
    const staff = staves[staffIndex];

    const aspectRatio = comp.w / Math.max(comp.h, 1);
    const fillRatio = comp.area / (comp.w * comp.h);

    // --- Classification by shape heuristics ---

    // 1. BARLINE: very tall, very thin, spans most of staff height
    if (comp.w <= maxStemWidth * 1.5 &&
        comp.h >= (staff.bottom - staff.top) * 0.7) {
      barlines.push({
        x: comp.cx, y: comp.y, w: comp.w, h: comp.h,
        staffIndex, componentId: comp.id,
      });
      continue;
    }

    // 2. STEM: tall and thin vertical
    if (comp.w <= maxStemWidth &&
        comp.h >= minStemLength &&
        aspectRatio < 0.3) {
      stems.push({
        x: comp.cx, top: comp.y, bottom: comp.y + comp.h - 1,
        length: comp.h, staffIndex, componentId: comp.id,
        w: comp.w,
      });
      continue;
    }

    // 3. BEAM: wide and short horizontal
    if (comp.w >= minBeamWidth &&
        comp.h <= maxBeamHeight &&
        aspectRatio > 3) {
      beams.push({
        x: comp.x, y: comp.y, w: comp.w, h: comp.h,
        staffIndex, componentId: comp.id,
      });
      continue;
    }

    // 4. DOT: tiny near-circular component
    if (comp.w <= maxDotSize && comp.h <= maxDotSize &&
        comp.area >= minDotArea && comp.area <= maxDotSize * maxDotSize &&
        fillRatio > 0.4) {
      dots.push({
        x: comp.cx, y: comp.cy, w: comp.w, h: comp.h,
        area: comp.area, staffIndex, componentId: comp.id,
      });
      continue;
    }

    // 5. NOTEHEAD: roughly oval, width ≈ 1.2-1.8× interline
    if (comp.w >= minNoteW && comp.w <= maxNoteW &&
        comp.h >= minNoteH && comp.h <= maxNoteH) {

      const headType = _classifyHeadType(staffRemoved, comp, avgInterline, fillRatio);

      if (headType) {
        const staffPos = getStaffPosition(staff, comp.cy);
        noteHeads.push({
          x: comp.x, y: comp.y, w: comp.w, h: comp.h,
          cx: comp.cx, cy: comp.cy, area: comp.area,
          staffIndex, staffPosition: staffPos,
          type: headType, componentId: comp.id,
        });
        continue;
      }
    }

    // 6. REST: medium-sized, not matching note/stem/beam shapes
    // Rests are identified by size range and RLE contour shape
    if (comp.w >= avgInterline * 0.4 && comp.w <= avgInterline * 3 &&
        comp.h >= avgInterline * 0.5 && comp.h <= avgInterline * 4) {
      const restType = _classifyRest(staffRemoved, comp, staff, avgInterline, avgLineHeight);
      if (restType) {
        rests.push({
          x: comp.x, y: comp.y, w: comp.w, h: comp.h,
          cx: comp.cx, cy: comp.cy,
          restType, staffIndex, componentId: comp.id,
          staffPosition: getStaffPosition(staff, comp.cy),
        });
        continue;
      }
    }

    // 7. ACCIDENTAL: small symbol, width < interline, typically left of notehead area
    if (comp.w >= avgInterline * 0.3 && comp.w <= avgInterline * 1.2 &&
        comp.h >= avgInterline * 0.8 && comp.h <= avgInterline * 2.5 &&
        comp.area >= avgInterline * 2) {
      const accType = _classifyAccidental(staffRemoved, comp, avgInterline);
      if (accType) {
        accidentals.push({
          x: comp.x, y: comp.y, w: comp.w, h: comp.h,
          cx: comp.cx, cy: comp.cy,
          accidentalType: accType, staffIndex, componentId: comp.id,
          staffPosition: getStaffPosition(staff, comp.cy),
        });
      }
    }
  }

  return { noteHeads, stems, beams, rests, dots, accidentals, barlines };
}

/**
 * Find which staff a y-coordinate belongs to.
 */
function _findStaffIndex(staves, y) {
  let bestIdx = -1;
  let bestDist = Infinity;
  for (let i = 0; i < staves.length; i++) {
    const staff = staves[i];
    const margin = staff.interline * 3; // allow ledger lines
    const center = (staff.top + staff.bottom) / 2;
    const dist = Math.abs(y - center);
    if (y >= staff.top - margin && y <= staff.bottom + margin && dist < bestDist) {
      bestDist = dist;
      bestIdx = i;
    }
  }
  return bestIdx;
}

/**
 * Classify a notehead candidate as filled, hollow, or whole.
 *
 * Zemsky's approach:
 *   - Filled (quarter/eighth/etc.): high fill ratio (>55% black area)
 *   - Hollow (half note): low fill ratio (~25-55%), oval with vertical internal gap
 *   - Whole note: low fill ratio, wider oval, no stem indicator
 */
function _classifyHeadType(bin, comp, interline, fillRatio) {
  // Basic shape check: noteheads are roughly oval
  const aspectRatio = comp.w / Math.max(comp.h, 1);
  if (aspectRatio < 0.6 || aspectRatio > 2.5) return null;

  // Filled notehead: dense black area
  if (fillRatio > 0.55) {
    return 'filled';
  }

  // Hollow check: look for internal white area (the "hole")
  if (fillRatio >= 0.25 && fillRatio <= 0.55) {
    // Check for hollow interior by scanning the middle rows
    const crop = cropImage(bin, comp.x, comp.y, comp.w, comp.h);
    const midRows = [];
    const startY = Math.floor(comp.h * 0.25);
    const endY = Math.ceil(comp.h * 0.75);

    for (let dy = startY; dy < endY; dy++) {
      let blackCount = 0;
      for (let dx = 0; dx < comp.w; dx++) {
        if (crop.data[dy * comp.w + dx] === 0) blackCount++;
      }
      midRows.push(blackCount / comp.w);
    }

    // Hollow notes have low ink density in the middle
    const avgMidDensity = midRows.reduce((a, b) => a + b, 0) / midRows.length;
    if (avgMidDensity < 0.5) {
      // Distinguish whole note (wider) vs half note (narrower)
      if (comp.w >= interline * 1.5) {
        return 'whole';
      }
      return 'hollow';
    }
  }

  // Might still be a filled note with staff-line removal artifacts
  if (fillRatio >= 0.4) return 'filled';

  return null;
}

/**
 * Classify a rest by its shape using RLE contour analysis.
 *
 * Zemsky's recognizeRectRests + recognizeShortRests:
 *   - Whole rest: rectangle hanging below 4th line
 *   - Half rest: rectangle sitting on 3rd line
 *   - Quarter rest: characteristic S-curve shape
 *   - 8th/16th rest: dot + flag shape
 */
function _classifyRest(bin, comp, staff, interline, lineHeight) {
  const fillRatio = comp.area / (comp.w * comp.h);
  const aspectRatio = comp.w / Math.max(comp.h, 1);

  // Rectangular rests (whole and half)
  // ~rectangular shape, width ≈ interline, height ≈ 0.5× interline
  if (comp.w >= interline * 0.6 && comp.w <= interline * 1.8 &&
      comp.h >= interline * 0.3 && comp.h <= interline * 0.9 &&
      fillRatio > 0.7) {
    // Position determines whole vs half:
    // Whole rest hangs from line 4 (below), half rest sits on line 3 (above)
    const midLine = staff.lines[2].y; // 3rd line
    if (comp.cy < midLine) {
      return 'wholeRest'; // hanging from above
    } else {
      return 'halfRest'; // sitting below
    }
  }

  // Quarter rest (crotchet): characteristic S/zigzag shape
  // Height ≈ 2-3 interlines, narrow width
  if (comp.h >= interline * 1.5 && comp.h <= interline * 3.5 &&
      comp.w >= interline * 0.3 && comp.w <= interline * 1.2 &&
      fillRatio >= 0.2 && fillRatio <= 0.6) {
    return 'quarterRest';
  }

  // 8th rest: small with a dot and a flag
  if (comp.h >= interline * 0.8 && comp.h <= interline * 2.0 &&
      comp.w >= interline * 0.3 && comp.w <= interline * 1.2 &&
      fillRatio >= 0.15 && fillRatio <= 0.5) {
    if (comp.h < interline * 1.5) return 'eighthRest';
    return 'sixteenthRest';
  }

  return null;
}

/**
 * Classify an accidental by shape analysis.
 *
 * Zemsky's isSharp/isFlat/isNatural:
 *   - Sharp: two vertical lines + two horizontal bars → high coverage for size
 *   - Flat: left vertical + right belly → asymmetric
 *   - Natural: similar to sharp but simpler
 */
function _classifyAccidental(bin, comp, interline) {
  const fillRatio = comp.area / (comp.w * comp.h);
  const aspectRatio = comp.w / Math.max(comp.h, 1);

  // Sharp: relatively dense, taller than wide
  if (fillRatio >= 0.25 && fillRatio <= 0.55 &&
      comp.h >= interline * 1.2 && aspectRatio < 0.8) {
    // Check for vertical lines using vertical RLE on the crop
    const crop = cropImage(bin, comp.x, comp.y, comp.w, comp.h);
    const midX = Math.floor(comp.w / 2);

    // Sharp has two verticals — check left quarter and right quarter
    let leftBlack = 0, rightBlack = 0;
    for (let dy = 0; dy < comp.h; dy++) {
      if (crop.data[dy * comp.w + Math.floor(comp.w * 0.25)] === 0) leftBlack++;
      if (crop.data[dy * comp.w + Math.floor(comp.w * 0.75)] === 0) rightBlack++;
    }

    if (leftBlack / comp.h > 0.5 && rightBlack / comp.h > 0.5) {
      return 'sharp';
    }
  }

  // Flat: left vertical + right belly, asymmetric
  if (fillRatio >= 0.2 && fillRatio <= 0.5 &&
      comp.h >= interline * 1.0 && aspectRatio < 0.9) {
    const crop = cropImage(bin, comp.x, comp.y, comp.w, comp.h);

    // Flat has a strong left vertical
    let leftVertical = 0;
    for (let dy = 0; dy < comp.h; dy++) {
      if (crop.data[dy * comp.w] === 0 || crop.data[dy * comp.w + 1] === 0) leftVertical++;
    }

    // Belly is in the bottom half, right side
    let bellyInk = 0;
    const midY = Math.floor(comp.h / 2);
    for (let dy = midY; dy < comp.h; dy++) {
      for (let dx = Math.floor(comp.w / 2); dx < comp.w; dx++) {
        if (crop.data[dy * comp.w + dx] === 0) bellyInk++;
      }
    }

    if (leftVertical / comp.h > 0.6 && bellyInk > comp.area * 0.15) {
      return 'flat';
    }
  }

  // Natural: similar structure to sharp but typically thinner
  if (fillRatio >= 0.15 && fillRatio <= 0.45 &&
      comp.h >= interline * 1.0 && aspectRatio < 0.7) {
    return 'natural';
  }

  return null;
}

/**
 * Associate stems with noteheads — find which stem connects to which head.
 *
 * Zemsky's mergeHeadsWithStems: checks spatial proximity and vertical alignment.
 * A stem connects to a head if:
 *   - The stem's x is within the head's horizontal range (±tolerance)
 *   - The stem's top or bottom touches the head's y range
 *
 * @returns {Map<number, number[]>} stemComponentId → array of noteHead indices
 */
export function associateStemsWithHeads(stems, noteHeads, interline) {
  const stemToHeads = new Map();
  const headToStem = new Map(); // headIndex → stemIndex

  const tolerance = Math.max(interline * 0.4, 3);

  for (let si = 0; si < stems.length; si++) {
    const stem = stems[si];
    const associatedHeads = [];

    for (let hi = 0; hi < noteHeads.length; hi++) {
      const head = noteHeads[hi];
      if (head.staffIndex !== stem.staffIndex) continue;

      // Check horizontal alignment
      const headLeft = head.x;
      const headRight = head.x + head.w;
      const stemX = stem.x;

      if (stemX < headLeft - tolerance || stemX > headRight + tolerance) continue;

      // Check vertical connection — stem touches head
      const headTop = head.y;
      const headBottom = head.y + head.h;

      // Stem above head (stem up) or below head (stem down)
      const stemTouchesHead =
        (stem.top <= headBottom + tolerance && stem.top >= headTop - tolerance) ||
        (stem.bottom >= headTop - tolerance && stem.bottom <= headBottom + tolerance) ||
        (stem.top <= headTop && stem.bottom >= headBottom); // stem spans head

      if (stemTouchesHead) {
        associatedHeads.push(hi);
        headToStem.set(hi, si);
      }
    }

    if (associatedHeads.length > 0) {
      stemToHeads.set(si, associatedHeads);
    }
  }

  return { stemToHeads, headToStem };
}

/**
 * Associate beams with stems.
 * A beam connects to stems that intersect it horizontally and are vertically adjacent.
 *
 * @returns {Map<number, number[]>} beamIndex → array of stem indices
 */
export function associateBeamsWithStems(beams, stems, interline) {
  const beamToStems = new Map();
  const tolerance = Math.max(interline * 0.5, 4);

  for (let bi = 0; bi < beams.length; bi++) {
    const beam = beams[bi];
    const connectedStems = [];

    for (let si = 0; si < stems.length; si++) {
      const stem = stems[si];
      if (stem.staffIndex !== beam.staffIndex) continue;

      // Stem must be within beam's horizontal range
      if (stem.x < beam.x - tolerance || stem.x > beam.x + beam.w + tolerance) continue;

      // Stem endpoint must be near beam's vertical range
      const beamMid = beam.y + beam.h / 2;
      if (Math.abs(stem.top - beamMid) <= tolerance ||
          Math.abs(stem.bottom - beamMid) <= tolerance) {
        connectedStems.push(si);
      }
    }

    if (connectedStems.length > 0) {
      beamToStems.set(bi, connectedStems);
    }
  }

  return beamToStems;
}

/**
 * Find duration dots associated with noteheads.
 * Dots appear to the right of noteheads, at the same staff position.
 *
 * @returns {Map<number, number>} dotIndex → noteHead index
 */
export function associateDotsWithNotes(dots, noteHeads, interline) {
  const dotToHead = new Map();
  const maxDotDist = interline * 1.5; // dots are close to their note

  for (let di = 0; di < dots.length; di++) {
    const dot = dots[di];
    let bestHead = -1;
    let bestDist = Infinity;

    for (let hi = 0; hi < noteHeads.length; hi++) {
      const head = noteHeads[hi];
      if (head.staffIndex !== dot.staffIndex) continue;

      // Dot must be to the right of the notehead
      if (dot.x <= head.cx) continue;

      // Dot must be at roughly the same vertical position
      const yDist = Math.abs(dot.y - head.cy);
      if (yDist > interline * 0.7) continue;

      const xDist = dot.x - (head.x + head.w);
      if (xDist > 0 && xDist < maxDotDist && xDist < bestDist) {
        bestDist = xDist;
        bestHead = hi;
      }
    }

    if (bestHead >= 0) {
      dotToHead.set(di, bestHead);
    }
  }

  return dotToHead;
}

/**
 * Find accidentals associated with noteheads.
 * Accidentals appear to the left of noteheads, at the same staff position.
 */
export function associateAccidentalsWithNotes(accidentals, noteHeads, interline) {
  const accToHead = new Map();
  const maxAccDist = interline * 2;

  for (let ai = 0; ai < accidentals.length; ai++) {
    const acc = accidentals[ai];
    let bestHead = -1;
    let bestDist = Infinity;

    for (let hi = 0; hi < noteHeads.length; hi++) {
      const head = noteHeads[hi];
      if (head.staffIndex !== acc.staffIndex) continue;

      // Accidental must be to the left
      if (acc.cx >= head.cx) continue;

      // Similar vertical position
      const yDist = Math.abs(acc.cy - head.cy);
      if (yDist > interline * 0.8) continue;

      const xDist = head.x - (acc.x + acc.w);
      if (xDist >= 0 && xDist < maxAccDist && xDist < bestDist) {
        bestDist = xDist;
        bestHead = hi;
      }
    }

    if (bestHead >= 0) {
      accToHead.set(ai, bestHead);
    }
  }

  return accToHead;
}
