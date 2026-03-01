import React, { useState, useEffect, useRef, useCallback, useMemo } from 'react';
import {
  View,
  Text,
  StyleSheet,
  Dimensions,
  Image,
  ScrollView,
  Pressable,
  Animated,
} from 'react-native';

const ACCENT = '#E05A2A';
const ACCENT_LIGHT = 'rgba(224, 90, 42, 0.18)';
const ACCENT_GLOW = 'rgba(224, 90, 42, 0.35)';
const BAR_HEIGHT = 6;

/**
 * Horizontal-scroll score viewer with:
 *  - Full score displayed at natural scale (fit height to screen)
 *  - Orange vertical cursor bar that snaps note-by-note
 *  - Orange horizontal progress bar (scrubber) at the top
 *  - Note-position highlights during playback (pulse animation)
 *  - Horizontal auto-scroll to follow the cursor
 *  - Tap-to-seek on both the sheet image and progress bar
 */
export const PlaybackVisualization = ({
  imageUri,
  currentTime,
  totalDuration,
  isPlaying,
  cursorInfo,
  onSeek, // (timeSeconds: number) => void
  measureBeats, // [{ measureNum, startBeat, endBeat }] from metadata
  tempo, // BPM for converting beats to time
  // cursorInfo = { positions, systemBounds, imageWidth, imageHeight, xRange }
}) => {
  const scrollViewRef = useRef(null);
  const [containerWidth, setContainerWidth] = useState(0);
  const [containerHeight, setContainerHeight] = useState(0);
  const [imageNaturalWidth, setImageNaturalWidth] = useState(0);
  const [imageNaturalHeight, setImageNaturalHeight] = useState(0);

  // Pulse animation for note highlights
  const pulseAnim = useRef(new Animated.Value(1)).current;
  useEffect(() => {
    if (!isPlaying) {
      pulseAnim.setValue(1);
      return;
    }
    const loop = Animated.loop(
      Animated.sequence([
        Animated.timing(pulseAnim, { toValue: 0.55, duration: 400, useNativeDriver: true }),
        Animated.timing(pulseAnim, { toValue: 1, duration: 400, useNativeDriver: true }),
      ])
    );
    loop.start();
    return () => loop.stop();
  }, [isPlaying]);

  // Load natural image dimensions
  useEffect(() => {
    if (!imageUri) return;
    Image.getSize(
      imageUri,
      (w, h) => {
        setImageNaturalWidth(w);
        setImageNaturalHeight(h);
      },
      () => {}
    );
  }, [imageUri]);

  // ─── Cursor data ───
  const positions = cursorInfo?.positions || [];
  const systemBounds = cursorInfo?.systemBounds || [];
  const xRange = cursorInfo?.xRange || { min: 0, max: 1 };
  const rangeSpan = Math.max(1, xRange.max - xRange.min);

  // Find active cursor index by snapping to the latest timing entry
  let activeIndex = 0;
  if (positions.length > 0) {
    for (let i = 0; i < positions.length; i++) {
      if (currentTime >= positions[i].time) {
        activeIndex = i;
      } else {
        break;
      }
    }
  }

  const activeEntry = positions[activeIndex] || null;
  const nextEntry = positions[activeIndex + 1] || null;
  const activeSystemIndex = activeEntry?.systemIndex ?? 0;

  // ─── Smooth lerp: interpolate ratio between current and next position ───
  let activeRatio = activeEntry?.ratio ?? 0;
  if (activeEntry && nextEntry && nextEntry.systemIndex === activeEntry.systemIndex) {
    // Same system — smoothly slide from current position to next
    const elapsed = currentTime - activeEntry.time;
    const span = nextEntry.time - activeEntry.time;
    if (span > 0) {
      const t = Math.max(0, Math.min(1, elapsed / span));
      activeRatio = activeEntry.ratio + t * (nextEntry.ratio - activeEntry.ratio);
    }
  }

  const system = systemBounds[activeSystemIndex];

  // ─── Layout metrics ───
  const PROGRESS_AREA_HEIGHT = 30;
  const sheetHeight = Math.max(50, containerHeight - PROGRESS_AREA_HEIGHT);
  const hasImage = imageNaturalWidth > 0 && imageNaturalHeight > 0;

  // ─── Scale: fit image height to container, let width overflow for horizontal scroll ───
  let zoomScale = 1;
  let imageOffsetY = 0;
  let renderWidth = containerWidth || Dimensions.get('window').width;
  let renderHeight = 400;

  if (hasImage && containerWidth > 0 && containerHeight > 0) {
    // Scale so the full image height fits the sheet area
    zoomScale = sheetHeight / imageNaturalHeight;
    renderWidth = imageNaturalWidth * zoomScale;
    renderHeight = imageNaturalHeight * zoomScale;
    imageOffsetY = 0;
  }

  // ─── Cursor position in zoomed-image coordinates ───
  const cursorImageX = xRange.min + activeRatio * rangeSpan;
  const cursorX = cursorImageX * zoomScale;
  const clampedCursorX = Math.max(0, Math.min(cursorX, renderWidth - 3));

  let cursorTop = 0;
  let cursorHeight = renderHeight;
  if (system) {
    const pad = Math.max(4, (system.bottom - system.top) * 0.1);
    cursorTop = (system.top - pad) * zoomScale;
    cursorHeight = (system.bottom - system.top + pad * 2) * zoomScale;
  }

  // ─── Highlighted notes (all notes sharing the same time slot) ───
  const highlightedNotes = useMemo(() => {
    if (!activeEntry || positions.length === 0) return [];
    const activeTime = activeEntry.time;
    return positions.filter((p) => Math.abs(p.time - activeTime) < 0.001);
  }, [activeIndex, positions]);

  const dotSize = Math.max(16, Math.min(32, sheetHeight * 0.05));

  // ─── Progress ratio for the scrubber bar ───
  const progressRatio =
    totalDuration > 0 ? Math.max(0, Math.min(1, currentTime / totalDuration)) : 0;

  // ─── Horizontal auto-scroll to keep cursor visible ───
  useEffect(() => {
    if (!scrollViewRef.current) return;
    if ((isPlaying || currentTime > 0) && positions.length > 0) {
      // Position cursor at ~35% from the left edge of the viewport
      const targetX = Math.max(0, cursorX - containerWidth * 0.35);
      const maxScroll = Math.max(0, renderWidth - containerWidth);
      const clampedX = Math.min(targetX, maxScroll);
      scrollViewRef.current.scrollTo({ x: clampedX, animated: true });
    }
  }, [activeIndex, isPlaying]);

  const showCursor = (isPlaying || currentTime > 0) && positions.length > 0;

  // ─── Tap-to-seek on the sheet image ───
  const handleSheetPress = useCallback(
    (evt) => {
      if (!onSeek || positions.length === 0) return;

      const { locationX, locationY } = evt.nativeEvent;

      // Convert tap coordinates back to image-space
      const tapImgX = locationX / zoomScale;
      const tapImgY = locationY / zoomScale;

      // Determine which system the tap landed in
      let tappedSystemIdx = -1;
      for (let i = 0; i < systemBounds.length; i++) {
        const sys = systemBounds[i];
        const pad = Math.max(10, (sys.bottom - sys.top) * 0.3);
        if (tapImgY >= sys.top - pad && tapImgY <= sys.bottom + pad) {
          tappedSystemIdx = i;
          break;
        }
      }

      // Find the nearest cursor position within the tapped system
      let bestIdx = 0;
      let bestDist = Infinity;

      for (let i = 0; i < positions.length; i++) {
        const pos = positions[i];
        if (tappedSystemIdx >= 0 && pos.systemIndex !== tappedSystemIdx) continue;
        const posImgX = xRange.min + pos.ratio * rangeSpan;
        const dist = Math.abs(posImgX - tapImgX);
        if (dist < bestDist) {
          bestDist = dist;
          bestIdx = i;
        }
      }

      // Fallback: global nearest
      if (bestDist === Infinity) {
        for (let i = 0; i < positions.length; i++) {
          const posImgX = xRange.min + positions[i].ratio * rangeSpan;
          const dist = Math.abs(posImgX - tapImgX);
          if (dist < bestDist) {
            bestDist = dist;
            bestIdx = i;
          }
        }
      }

      onSeek(positions[bestIdx].time);
    },
    [onSeek, positions, systemBounds, zoomScale, xRange, rangeSpan]
  );

  // ─── Tap on the progress bar ───
  const handleProgressBarPress = useCallback(
    (evt) => {
      if (!onSeek || totalDuration <= 0) return;
      const { locationX } = evt.nativeEvent;
      const ratio = Math.max(0, Math.min(1, locationX / containerWidth));
      onSeek(ratio * totalDuration);
    },
    [onSeek, totalDuration, containerWidth]
  );

  return (
    <View
      style={styles.container}
      onLayout={(e) => {
        setContainerWidth(e.nativeEvent.layout.width);
        setContainerHeight(e.nativeEvent.layout.height);
      }}
    >
      {/* ─── Horizontal progress bar (scrubber) ─── */}
      <Pressable onPress={handleProgressBarPress} style={styles.progressBarOuter}>
        <View style={styles.progressBarTrack}>
          {/* Measure markers */}
          {measureBeats && measureBeats.length > 1 && totalDuration > 0 && tempo > 0 && (
            measureBeats.slice(1).map((mb, i) => {
              const secPerBeat = 60 / tempo;
              const markerTime = mb.startBeat * secPerBeat;
              const pct = (markerTime / totalDuration) * 100;
              if (pct <= 0 || pct >= 100) return null;
              return (
                <View key={`m-${i}`} style={[styles.measureMarker, { left: `${pct}%` }]}>
                  {(mb.measureNum % Math.max(1, Math.ceil(measureBeats.length / 12)) === 0 ||
                    mb.measureNum === 1) && (
                    <Text style={styles.measureMarkerNum}>{mb.measureNum}</Text>
                  )}
                </View>
              );
            })
          )}
          <View style={[styles.progressBarFill, { width: `${progressRatio * 100}%` }]} />
          {showCursor && (
            <View
              style={[
                styles.progressThumb,
                { left: `${progressRatio * 100}%` },
              ]}
            />
          )}
        </View>
      </Pressable>

      {/* ─── Horizontally scrolling sheet music ─── */}
      <ScrollView
        ref={scrollViewRef}
        horizontal
        style={styles.scrollView}
        showsHorizontalScrollIndicator={false}
        scrollEventThrottle={16}
      >
        {imageUri ? (
          <Pressable onPress={handleSheetPress}>
            <View style={[styles.sheetClip, { width: renderWidth, height: renderHeight }]}>
              <View
                style={{
                  width: renderWidth,
                  height: renderHeight,
                }}
              >
                <Image
                  source={{ uri: imageUri }}
                  style={{ width: renderWidth, height: renderHeight }}
                  resizeMode="contain"
                />

                {/* Note highlights: circles on notes at the active time */}
                {showCursor &&
                  highlightedNotes.map((note, idx) => {
                    const nImgX = xRange.min + note.ratio * rangeSpan;
                    const nX = nImgX * zoomScale;
                    const noteSys = systemBounds[note.systemIndex];
                    const nY = noteSys
                      ? ((noteSys.top + noteSys.bottom) / 2) * zoomScale
                      : cursorTop + cursorHeight / 2;

                    return (
                      <Animated.View
                        key={`hl-${idx}`}
                        style={[
                          styles.noteHighlight,
                          {
                            left: nX - dotSize / 2,
                            top: nY - dotSize / 2,
                            width: dotSize,
                            height: dotSize,
                            borderRadius: dotSize / 2,
                            opacity: pulseAnim,
                          },
                        ]}
                      />
                    );
                  })}

                {/* Vertical cursor bar */}
                {showCursor && (
                  <View
                    style={[
                      styles.cursor,
                      {
                        left: clampedCursorX,
                        top: cursorTop,
                        height: cursorHeight,
                      },
                    ]}
                  />
                )}

                {/* Played-region highlight behind the cursor */}
                {showCursor && system && (
                  <View
                    style={[
                      styles.systemHighlight,
                      {
                        top: cursorTop,
                        height: cursorHeight,
                        width: clampedCursorX,
                      },
                    ]}
                  />
                )}
              </View>
            </View>
          </Pressable>
        ) : (
          <View style={styles.noImage}>
            <Text style={styles.noImageText}>No sheet image</Text>
          </View>
        )}
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
  },
  /* ─── Progress bar ─── */
  progressBarOuter: {
    paddingVertical: 6,
    paddingHorizontal: 12,
    backgroundColor: '#FAFAF7',
    borderBottomWidth: StyleSheet.hairlineWidth,
    borderBottomColor: '#E6E2D8',
  },
  progressBarTrack: {
    height: BAR_HEIGHT,
    borderRadius: BAR_HEIGHT / 2,
    backgroundColor: '#E8E4DA',
    overflow: 'visible',
    position: 'relative',
  },
  measureMarker: {
    position: 'absolute',
    top: -2,
    width: 1,
    height: BAR_HEIGHT + 4,
    backgroundColor: 'rgba(62, 60, 55, 0.2)',
    zIndex: 1,
  },
  measureMarkerNum: {
    position: 'absolute',
    top: BAR_HEIGHT + 5,
    fontSize: 7,
    color: '#999',
    fontWeight: '700',
    textAlign: 'center',
    width: 16,
    marginLeft: -8,
  },
  progressBarFill: {
    position: 'absolute',
    left: 0,
    top: 0,
    height: BAR_HEIGHT,
    borderRadius: BAR_HEIGHT / 2,
    backgroundColor: ACCENT,
  },
  progressThumb: {
    position: 'absolute',
    top: -(10 - BAR_HEIGHT) / 2,
    width: 10,
    height: 10,
    borderRadius: 5,
    backgroundColor: ACCENT,
    marginLeft: -5,
    borderWidth: 2,
    borderColor: '#fff',
    elevation: 3,
    shadowColor: ACCENT,
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.4,
    shadowRadius: 2,
  },
  /* ─── Sheet area ─── */
  scrollView: {
    flex: 1,
  },
  sheetClip: {
    overflow: 'hidden',
    position: 'relative',
  },
  /* ─── Cursor & highlights ─── */
  cursor: {
    position: 'absolute',
    width: 3,
    backgroundColor: ACCENT,
    borderRadius: 2,
    opacity: 0.9,
    zIndex: 10,
  },
  systemHighlight: {
    position: 'absolute',
    left: 0,
    backgroundColor: ACCENT_LIGHT,
    zIndex: 5,
  },
  noteHighlight: {
    position: 'absolute',
    backgroundColor: ACCENT_GLOW,
    borderWidth: 2,
    borderColor: ACCENT,
    zIndex: 11,
  },
  noImage: {
    height: 200,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f5f5f5',
    borderRadius: 8,
  },
  noImageText: {
    fontSize: 14,
    color: '#999',
  },
});
