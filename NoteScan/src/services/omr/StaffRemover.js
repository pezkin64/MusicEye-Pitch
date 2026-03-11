/**
 * StaffRemover — Erase staff lines while preserving intersecting symbols.
 *
 * Zemsky's approach (staffRemoveWithScan):
 *   For each staff line, scan each column:
 *     - Find the vertical run of black pixels at the line position
 *     - If the run length ≈ lineHeight → it's just the staff line → erase it
 *     - If the run is much taller → a symbol (stem, notehead) crosses → keep it
 *     - At intersections, only erase the staff-line portion (restore symbol)
 *
 * This preserves noteheads, stems, and other symbols that cross staff lines,
 * which is critical for the subsequent template matching stage.
 */

import { getPixel, setPixel, isBlack, createImage, cloneImage } from './ImageUtils';

/**
 * Remove staff lines from a binary image.
 * @param {import('./ImageUtils').GrayImage} bin - binary image
 * @param {import('./StaffDetector').Staff[]} staves - detected staves
 * @returns {import('./ImageUtils').GrayImage} image with staff lines removed
 */
export function removeStaffLines(bin, staves) {
  const out = cloneImage(bin);
  const { width, height } = out;

  for (const staff of staves) {
    const tolerance = Math.max(Math.ceil(staff.lineHeight * 1.5), 3);

    for (const line of staff.lines) {
      for (let x = staff.left; x <= staff.right && x < width; x++) {
        // Account for staff angle: compute actual y at this x
        const midX = (staff.left + staff.right) / 2;
        const angleRad = (staff.angle * Math.PI) / 180;
        const actualY = Math.round(line.y + (x - midX) * Math.tan(angleRad));

        // Find the vertical black run containing this line position
        const runInfo = _findVerticalRun(bin, x, actualY, height);
        if (!runInfo) continue;

        const { top: runTop, bottom: runBottom } = runInfo;
        const runLength = runBottom - runTop + 1;

        // Decision: erase or keep
        if (runLength <= tolerance) {
          // Pure staff line — erase entirely
          for (let y = runTop; y <= runBottom; y++) {
            setPixel(out, x, y, 255);
          }
        } else {
          // Symbol crossing — only erase the staff-line-width portion
          // at the exact line position, preserving the rest
          const halfLine = Math.floor(staff.lineHeight / 2);
          const eraseTop = Math.max(runTop, actualY - halfLine);
          const eraseBot = Math.min(runBottom, actualY + halfLine);

          // Only erase if there's symbol above AND below (true intersection)
          const hasAbove = eraseTop > runTop;
          const hasBelow = eraseBot < runBottom;

          if (hasAbove && hasBelow) {
            // True intersection — erase just the line thickness
            for (let y = eraseTop; y <= eraseBot; y++) {
              setPixel(out, x, y, 255);
            }
          }
          // If symbol is only on one side, don't erase — it might be
          // a notehead sitting on the line
        }
      }
    }
  }

  return out;
}

/**
 * Find the vertical run of black pixels containing the given y position.
 * Returns {top, bottom} or null if position is white.
 */
function _findVerticalRun(bin, x, y, height) {
  if (!isBlack(bin, x, y)) return null;

  let top = y;
  while (top > 0 && isBlack(bin, x, top - 1)) top--;

  let bottom = y;
  while (bottom < height - 1 && isBlack(bin, x, bottom + 1)) bottom++;

  return { top, bottom };
}
