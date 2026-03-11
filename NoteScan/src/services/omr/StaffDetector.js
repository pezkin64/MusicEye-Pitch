/**
 * StaffDetector — Detect staff lines via column-sliced horizontal projection.
 *
 * Zemsky-inspired approach (staffaDetect / rleGetRowRuns):
 *   1. Split image into vertical strips
 *   2. Horizontal projection per strip → find peak rows
 *   3. Merge peaks across strips → full staff lines
 *   4. Group into 5-line staves with consistent spacing
 *   5. Compute interline, line thickness, staff angle
 *
 * Staff lines are the most robust visual feature in any photo of sheet music.
 * Detect them FIRST, then use their geometry to guide everything else.
 */

import {
  horizontalProjection,
  isBlack,
} from './ImageUtils';

/**
 * @typedef {Object} StaffLine
 * @property {number} y - row position (center)
 * @property {number} thickness - line thickness in pixels
 * @property {number} angle - angle in degrees
 */

/**
 * @typedef {Object} Staff
 * @property {StaffLine[]} lines - exactly 5 lines, top to bottom
 * @property {number} top - y of topmost line
 * @property {number} bottom - y of bottommost line
 * @property {number} interline - average distance between adjacent lines
 * @property {number} lineHeight - average line thickness
 * @property {number} spaceHeight - average gap between lines
 * @property {number} angle - overall angle in degrees
 * @property {number} left - leftmost x of the staff
 * @property {number} right - rightmost x of the staff
 */

/**
 * Detect all staves in a binary image.
 * @param {import('./ImageUtils').GrayImage} bin - binary image (0=black, 255=white)
 * @returns {Staff[]}
 */
export function detectStaffs(bin) {
  const { width, height } = bin;

  // --- Step 1: Column-sliced horizontal projection ---
  const N_STRIPS = Math.max(1, Math.min(8, Math.floor(width / 50)));
  const stripW = Math.floor(width / N_STRIPS);

  const stripLines = []; // list of list of {cy, thickness}

  for (let s = 0; s < N_STRIPS; s++) {
    const x0 = s * stripW;
    const x1 = s < N_STRIPS - 1 ? x0 + stripW : width;
    const sw = x1 - x0;

    // Horizontal projection for this strip
    const proj = new Int32Array(height);
    for (let y = 0; y < height; y++) {
      let count = 0;
      for (let x = x0; x < x1; x++) {
        if (bin.data[y * width + x] === 0) count++;
      }
      proj[y] = count;
    }

    // Find peak threshold
    let maxProj = 0;
    for (let y = 0; y < height; y++) {
      if (proj[y] > maxProj) maxProj = proj[y];
    }
    const threshold = Math.max(maxProj * 0.4, 5);

    // Find contiguous peak groups
    const segs = [];
    let inPeak = false;
    let peakStart = 0;
    for (let y = 0; y <= height; y++) {
      const val = y < height ? proj[y] : 0;
      if (val >= threshold && !inPeak) {
        inPeak = true;
        peakStart = y;
      } else if (val < threshold && inPeak) {
        inPeak = false;
        const cy = Math.round((peakStart + y - 1) / 2);
        const thick = y - peakStart;
        segs.push({ cy, thickness: thick });
      }
    }
    stripLines.push(segs);
  }

  // --- Step 2: Use best strip as reference, trace across strips ---
  let bestStrip = 0;
  let bestCount = 0;
  for (let s = 0; s < N_STRIPS; s++) {
    if (stripLines[s].length > bestCount) {
      bestCount = stripLines[s].length;
      bestStrip = s;
    }
  }

  const refSegs = stripLines[bestStrip];
  if (refSegs.length < 5) return [];

  const maxDrift = Math.floor(stripW * Math.tan(5 * Math.PI / 180)) + 5;

  /** @type {StaffLine[]} */
  const lines = [];

  for (const { cy: refCy, thickness } of refSegs) {
    const trace = new Map(); // stripIndex → y
    trace.set(bestStrip, refCy);

    // Trace right
    let curY = refCy;
    for (let si = bestStrip + 1; si < N_STRIPS; si++) {
      let found = false;
      for (const seg of stripLines[si]) {
        if (Math.abs(seg.cy - curY) <= maxDrift) {
          trace.set(si, seg.cy);
          curY = seg.cy;
          found = true;
          break;
        }
      }
      if (!found) break;
    }

    // Trace left
    curY = refCy;
    for (let si = bestStrip - 1; si >= 0; si--) {
      let found = false;
      for (const seg of stripLines[si]) {
        if (Math.abs(seg.cy - curY) <= maxDrift) {
          trace.set(si, seg.cy);
          curY = seg.cy;
          found = true;
          break;
        }
      }
      if (!found) break;
    }

    // Need matches in >= 50% of strips
    if (trace.size >= Math.max(N_STRIPS * 0.5, 3)) {
      const sorted = [...trace.entries()].sort((a, b) => a[0] - b[0]);
      const [siL, cyL] = sorted[0];
      const [siR, cyR] = sorted[sorted.length - 1];
      let angle = 0;
      if (siR > siL) {
        const dx = (siR - siL) * stripW;
        const dy = cyR - cyL;
        angle = (Math.atan2(dy, dx) * 180) / Math.PI;
        if (Math.abs(angle) >= 10) angle = 0;
      }

      lines.push({ y: refCy, thickness: Math.max(thickness, 1), angle });
    }
  }

  if (lines.length < 5) return [];

  // Filter unreasonably thick bands
  const thicknesses = lines.map(l => l.thickness).sort((a, b) => a - b);
  const medThick = thicknesses[Math.floor(thicknesses.length / 2)];
  const maxThick = Math.max(medThick * 3, 20);
  const filtered = lines.filter(l => l.thickness <= maxThick);
  if (filtered.length < 5) return [];

  // --- Step 3: Group into 5-line staves ---
  const gaps = [];
  for (let i = 0; i < filtered.length - 1; i++) {
    gaps.push(filtered[i + 1].y - filtered[i].y);
  }

  // Find reasonable interline gaps
  const reasonable = gaps.filter(g => g > 3 && g < height * 0.05);
  if (reasonable.length < 4) return [];

  reasonable.sort((a, b) => a - b);
  const interlineEst = reasonable[Math.floor(reasonable.length / 2)];

  // Group lines
  const staffGroups = [];
  let currentGroup = [filtered[0]];

  for (let i = 0; i < filtered.length - 1; i++) {
    const gap = filtered[i + 1].y - filtered[i].y;
    if (Math.abs(gap - interlineEst) < interlineEst * 0.4) {
      currentGroup.push(filtered[i + 1]);
    } else {
      if (currentGroup.length >= 5) {
        staffGroups.push(currentGroup.slice(0, 5));
      }
      currentGroup = [filtered[i + 1]];
    }
  }
  if (currentGroup.length >= 5) {
    staffGroups.push(currentGroup.slice(0, 5));
  }

  if (staffGroups.length === 0) return [];

  // --- Step 4: Build Staff objects ---
  /** @type {Staff[]} */
  const staves = [];

  for (const group of staffGroups) {
    const ys = group.map(l => l.y);
    const interlines = [];
    for (let j = 0; j < 4; j++) interlines.push(ys[j + 1] - ys[j]);
    const avgInterline = interlines.reduce((a, b) => a + b) / interlines.length;
    const avgThickness = group.reduce((a, l) => a + l.thickness, 0) / group.length;
    const avgAngle = group.reduce((a, l) => a + l.angle, 0) / group.length;

    // Find horizontal extent of this staff (scan for leftmost/rightmost ink near staff lines)
    let left = width, right = 0;
    for (const line of group) {
      for (let x = 0; x < width; x++) {
        if (isBlack(bin, x, line.y)) {
          if (x < left) left = x;
          if (x > right) right = x;
        }
      }
    }

    staves.push({
      lines: group,
      top: ys[0],
      bottom: ys[4],
      interline: avgInterline,
      lineHeight: avgThickness,
      spaceHeight: Math.max(avgInterline - avgThickness, 1),
      angle: avgAngle,
      left,
      right,
    });
  }

  return staves;
}

/**
 * Get the pitch position (staff position) for a y-coordinate relative to a staff.
 * Returns a value where:
 *   0 = on the middle line (3rd line)
 *  +2 = one space above middle line
 *  -2 = one space below middle line
 *  +1 = on the line above middle
 * etc. (each step = half an interline)
 *
 * Zemsky's yIndex convention: position relative to the middle line B4 in treble.
 * @param {Staff} staff
 * @param {number} y - pixel y coordinate
 * @returns {number} pitch position (integer, negative = below middle, positive = above)
 */
export function getStaffPosition(staff, y) {
  const middleY = staff.lines[2].y; // 3rd line (middle)
  const halfInterline = staff.interline / 2;
  return -Math.round((y - middleY) / halfInterline);
}
