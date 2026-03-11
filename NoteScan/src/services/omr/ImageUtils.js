/**
 * ImageUtils — Binary image manipulation for OMR.
 *
 * All operations work on flat Uint8Array grayscale buffers (0=black, 255=white)
 * with explicit width/height.  This avoids canvas/DOM dependencies and
 * runs efficiently in React Native's JS engine.
 *
 * Inspired by Leptonica's PIX operations used in Zemsky's engine.
 */

/**
 * @typedef {Object} GrayImage
 * @property {Uint8Array} data - pixel values, 0=black, 255=white
 * @property {number} width
 * @property {number} height
 */

/**
 * Create a new blank (white) image.
 */
export function createImage(width, height, fill = 255) {
  const data = new Uint8Array(width * height);
  if (fill !== 0) data.fill(fill);
  return { data, width, height };
}

/**
 * Clone an image.
 */
export function cloneImage(img) {
  return { data: new Uint8Array(img.data), width: img.width, height: img.height };
}

/**
 * Get pixel value at (x, y).  Returns 255 for out-of-bounds.
 */
export function getPixel(img, x, y) {
  if (x < 0 || x >= img.width || y < 0 || y >= img.height) return 255;
  return img.data[y * img.width + x];
}

/**
 * Set pixel value at (x, y).
 */
export function setPixel(img, x, y, val) {
  if (x >= 0 && x < img.width && y >= 0 && y < img.height) {
    img.data[y * img.width + x] = val;
  }
}

/**
 * Is pixel black? (threshold-based)
 */
export function isBlack(img, x, y, threshold = 128) {
  return getPixel(img, x, y) < threshold;
}

/**
 * Convert RGBA image data (from canvas/camera) to grayscale GrayImage.
 */
export function rgbaToGray(rgbaData, width, height) {
  const gray = createImage(width, height);
  for (let i = 0; i < width * height; i++) {
    const r = rgbaData[i * 4];
    const g = rgbaData[i * 4 + 1];
    const b = rgbaData[i * 4 + 2];
    gray.data[i] = Math.round(0.299 * r + 0.587 * g + 0.114 * b);
  }
  return gray;
}

/**
 * Otsu binarization — find optimal threshold to split foreground/background.
 */
export function otsuBinarize(gray) {
  const { data, width, height } = gray;
  const N = width * height;

  // Build histogram
  const hist = new Int32Array(256);
  for (let i = 0; i < N; i++) hist[data[i]]++;

  // Otsu's method
  let sumTotal = 0;
  for (let i = 0; i < 256; i++) sumTotal += i * hist[i];

  let sumB = 0, wB = 0, wF;
  let maxVariance = 0, threshold = 128;

  for (let t = 0; t < 256; t++) {
    wB += hist[t];
    if (wB === 0) continue;
    wF = N - wB;
    if (wF === 0) break;
    sumB += t * hist[t];
    const mB = sumB / wB;
    const mF = (sumTotal - sumB) / wF;
    const variance = wB * wF * (mB - mF) * (mB - mF);
    if (variance > maxVariance) {
      maxVariance = variance;
      threshold = t;
    }
  }

  const out = createImage(width, height);
  for (let i = 0; i < N; i++) {
    out.data[i] = data[i] <= threshold ? 0 : 255;
  }
  return out;
}

/**
 * Sauvola adaptive binarization using integral images.
 * T(x,y) = mean * (1 + k * (std/R - 1))
 */
export function sauvolaBinarize(gray, windowRatio = 40, k = 0.15, R = 128) {
  const { data, width, height } = gray;
  const N = width * height;

  let win = Math.max(Math.floor(Math.max(width, height) / windowRatio), 11);
  if (win % 2 === 0) win++;
  const pad = Math.floor(win / 2);

  // Build integral images for sum and sum-of-squares
  const integral = new Float64Array((width + 1) * (height + 1));
  const integralSq = new Float64Array((width + 1) * (height + 1));
  const iw = width + 1;

  for (let y = 0; y < height; y++) {
    let rowSum = 0, rowSumSq = 0;
    for (let x = 0; x < width; x++) {
      const v = data[y * width + x];
      rowSum += v;
      rowSumSq += v * v;
      integral[(y + 1) * iw + (x + 1)] = integral[y * iw + (x + 1)] + rowSum;
      integralSq[(y + 1) * iw + (x + 1)] = integralSq[y * iw + (x + 1)] + rowSumSq;
    }
  }

  const out = createImage(width, height);

  for (let y = 0; y < height; y++) {
    const y1 = Math.max(0, y - pad);
    const y2 = Math.min(height, y + pad + 1);
    for (let x = 0; x < width; x++) {
      const x1 = Math.max(0, x - pad);
      const x2 = Math.min(width, x + pad + 1);
      const count = (y2 - y1) * (x2 - x1);

      const sum = integral[y2 * iw + x2] - integral[y1 * iw + x2]
                - integral[y2 * iw + x1] + integral[y1 * iw + x1];
      const sumSq = integralSq[y2 * iw + x2] - integralSq[y1 * iw + x2]
                   - integralSq[y2 * iw + x1] + integralSq[y1 * iw + x1];

      const mean = sum / count;
      const std = Math.sqrt(Math.max(sumSq / count - mean * mean, 0));
      const thresh = mean * (1 + k * (std / R - 1));

      out.data[y * width + x] = data[y * width + x] > thresh ? 255 : 0;
    }
  }

  return out;
}

/**
 * Horizontal projection — count black pixels per row.
 * Returns Int32Array of length height.
 */
export function horizontalProjection(bin) {
  const proj = new Int32Array(bin.height);
  for (let y = 0; y < bin.height; y++) {
    let count = 0;
    const offset = y * bin.width;
    for (let x = 0; x < bin.width; x++) {
      if (bin.data[offset + x] === 0) count++;
    }
    proj[y] = count;
  }
  return proj;
}

/**
 * Vertical projection — count black pixels per column.
 */
export function verticalProjection(bin) {
  const proj = new Int32Array(bin.width);
  for (let x = 0; x < bin.width; x++) {
    let count = 0;
    for (let y = 0; y < bin.height; y++) {
      if (bin.data[y * bin.width + x] === 0) count++;
    }
    proj[x] = count;
  }
  return proj;
}

/**
 * Horizontal RLE — for each row, find runs of black pixels.
 * Returns array of arrays: runs[y] = [{start, end, length}, ...]
 */
export function horizontalRLE(bin) {
  const runs = [];
  for (let y = 0; y < bin.height; y++) {
    const rowRuns = [];
    let inRun = false;
    let runStart = 0;
    const offset = y * bin.width;
    for (let x = 0; x < bin.width; x++) {
      const black = bin.data[offset + x] === 0;
      if (black && !inRun) {
        inRun = true;
        runStart = x;
      } else if (!black && inRun) {
        inRun = false;
        rowRuns.push({ start: runStart, end: x - 1, length: x - runStart });
      }
    }
    if (inRun) {
      rowRuns.push({ start: runStart, end: bin.width - 1, length: bin.width - runStart });
    }
    runs.push(rowRuns);
  }
  return runs;
}

/**
 * Vertical RLE — for each column, find runs of black pixels.
 */
export function verticalRLE(bin) {
  const runs = [];
  for (let x = 0; x < bin.width; x++) {
    const colRuns = [];
    let inRun = false;
    let runStart = 0;
    for (let y = 0; y < bin.height; y++) {
      const black = bin.data[y * bin.width + x] === 0;
      if (black && !inRun) {
        inRun = true;
        runStart = y;
      } else if (!black && inRun) {
        inRun = false;
        colRuns.push({ start: runStart, end: y - 1, length: y - runStart });
      }
    }
    if (inRun) {
      colRuns.push({ start: runStart, end: bin.height - 1, length: bin.height - runStart });
    }
    runs.push(colRuns);
  }
  return runs;
}

/**
 * Connected component labeling (8-connected).
 * Returns { labels: Int32Array, count, stats: [{id, x, y, w, h, area, cx, cy}] }
 */
export function connectedComponents(bin) {
  const { width, height } = bin;
  const labels = new Int32Array(width * height);
  let nextLabel = 1;
  const parent = [0]; // union-find

  function find(x) {
    while (parent[x] !== x) {
      parent[x] = parent[parent[x]];
      x = parent[x];
    }
    return x;
  }

  function union(a, b) {
    a = find(a);
    b = find(b);
    if (a !== b) parent[Math.max(a, b)] = Math.min(a, b);
  }

  // First pass
  for (let y = 0; y < height; y++) {
    for (let x = 0; x < width; x++) {
      if (bin.data[y * width + x] !== 0) continue; // skip white

      const neighbors = [];
      // Check 8 neighbors that have already been visited (above and left)
      for (const [dx, dy] of [[-1, -1], [0, -1], [1, -1], [-1, 0]]) {
        const nx = x + dx, ny = y + dy;
        if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
          const nl = labels[ny * width + nx];
          if (nl > 0) neighbors.push(nl);
        }
      }

      if (neighbors.length === 0) {
        labels[y * width + x] = nextLabel;
        parent.push(nextLabel);
        nextLabel++;
      } else {
        const minLabel = Math.min(...neighbors);
        labels[y * width + x] = minLabel;
        for (const nl of neighbors) union(nl, minLabel);
      }
    }
  }

  // Second pass — resolve labels
  const remap = new Int32Array(nextLabel);
  let finalCount = 0;
  for (let i = 1; i < nextLabel; i++) {
    const root = find(i);
    if (remap[root] === 0) {
      finalCount++;
      remap[root] = finalCount;
    }
    remap[i] = remap[root];
  }

  // Build stats
  const stats = new Array(finalCount + 1);
  for (let i = 0; i <= finalCount; i++) {
    stats[i] = { id: i, minX: width, minY: height, maxX: 0, maxY: 0, area: 0, sumX: 0, sumY: 0 };
  }

  for (let y = 0; y < height; y++) {
    for (let x = 0; x < width; x++) {
      const idx = y * width + x;
      if (labels[idx] > 0) {
        const l = remap[labels[idx]];
        labels[idx] = l;
        const s = stats[l];
        s.area++;
        s.sumX += x;
        s.sumY += y;
        if (x < s.minX) s.minX = x;
        if (x > s.maxX) s.maxX = x;
        if (y < s.minY) s.minY = y;
        if (y > s.maxY) s.maxY = y;
      }
    }
  }

  const finalStats = [];
  for (let i = 1; i <= finalCount; i++) {
    const s = stats[i];
    finalStats.push({
      id: i,
      x: s.minX,
      y: s.minY,
      w: s.maxX - s.minX + 1,
      h: s.maxY - s.minY + 1,
      area: s.area,
      cx: Math.round(s.sumX / s.area),
      cy: Math.round(s.sumY / s.area),
    });
  }

  return { labels, count: finalCount, stats: finalStats };
}

/**
 * Morphological erosion (horizontal structuring element).
 */
export function erodeHorizontal(bin, radius) {
  const out = cloneImage(bin);
  for (let y = 0; y < bin.height; y++) {
    for (let x = 0; x < bin.width; x++) {
      let allBlack = true;
      for (let dx = -radius; dx <= radius; dx++) {
        if (!isBlack(bin, x + dx, y)) { allBlack = false; break; }
      }
      out.data[y * bin.width + x] = allBlack ? 0 : 255;
    }
  }
  return out;
}

/**
 * Morphological dilation (horizontal structuring element).
 */
export function dilateHorizontal(bin, radius) {
  const out = cloneImage(bin);
  for (let y = 0; y < bin.height; y++) {
    for (let x = 0; x < bin.width; x++) {
      let anyBlack = false;
      for (let dx = -radius; dx <= radius; dx++) {
        if (isBlack(bin, x + dx, y)) { anyBlack = true; break; }
      }
      out.data[y * bin.width + x] = anyBlack ? 0 : 255;
    }
  }
  return out;
}

/**
 * Morphological opening = erode then dilate (horizontal).
 * Removes narrow vertical features, keeps horizontal ones.
 */
export function openHorizontal(bin, radius) {
  return dilateHorizontal(erodeHorizontal(bin, radius), radius);
}

/**
 * Extract a sub-image (crop).
 */
export function cropImage(img, x, y, w, h) {
  const out = createImage(w, h);
  for (let dy = 0; dy < h; dy++) {
    for (let dx = 0; dx < w; dx++) {
      out.data[dy * w + dx] = getPixel(img, x + dx, y + dy);
    }
  }
  return out;
}

/**
 * Scale image by factor (nearest-neighbor for binary, bilinear for gray).
 */
export function scaleImage(img, factor) {
  const nw = Math.round(img.width * factor);
  const nh = Math.round(img.height * factor);
  const out = createImage(nw, nh);
  for (let y = 0; y < nh; y++) {
    const sy = Math.min(Math.floor(y / factor), img.height - 1);
    for (let x = 0; x < nw; x++) {
      const sx = Math.min(Math.floor(x / factor), img.width - 1);
      out.data[y * nw + x] = img.data[sy * img.width + sx];
    }
  }
  return out;
}

/**
 * Binary correlation score between two equal-sized binary images.
 * Like Leptonica's pixCorrelationScore:
 *   score = matching_black / total_black
 */
export function correlationScore(img1, img2) {
  if (img1.width !== img2.width || img1.height !== img2.height) return 0;
  const N = img1.width * img1.height;
  let both = 0, total1 = 0, total2 = 0;
  for (let i = 0; i < N; i++) {
    const b1 = img1.data[i] === 0;
    const b2 = img2.data[i] === 0;
    if (b1) total1++;
    if (b2) total2++;
    if (b1 && b2) both++;
  }
  const total = Math.max(total1, total2);
  return total === 0 ? 0 : both / total;
}

/**
 * Count black pixels in a region.
 */
export function countBlack(img, x0 = 0, y0 = 0, w = img.width, h = img.height) {
  let count = 0;
  for (let y = y0; y < y0 + h && y < img.height; y++) {
    for (let x = x0; x < x0 + w && x < img.width; x++) {
      if (img.data[y * img.width + x] === 0) count++;
    }
  }
  return count;
}
