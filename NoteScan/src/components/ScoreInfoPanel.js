import React, { useMemo, useState } from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Modal } from 'react-native';

/* ─── Theme (matches PlaybackScreen) ─── */
const palette = {
  background: '#F9F7F1',
  surface: '#FBFAF5',
  surfaceStrong: '#F1EEE4',
  border: '#D6D0C4',
  ink: '#3E3C37',
  inkMuted: '#6E675E',
};
const accent = '#E05A2A';
const barPalette = {
  bar: '#1C1B19',
  barRaised: '#2A2925',
  barBorder: '#3A3935',
  barText: '#EDEBE6',
  barTextMuted: '#8A857C',
  accent: '#E05A2A',
};

/* ─── Key name lookup ─── */
const KEY_NAMES = {
  '-7': 'C♭ Maj', '-6': 'G♭ Maj', '-5': 'D♭ Maj', '-4': 'A♭ Maj',
  '-3': 'E♭ Maj', '-2': 'B♭ Maj', '-1': 'F Maj',
  '0': 'C Maj',
  '1': 'G Maj', '2': 'D Maj', '3': 'A Maj', '4': 'E Maj',
  '5': 'B Maj', '6': 'F♯ Maj', '7': 'C♯ Maj',
};

/**
 * ScoreInfoPanel — compact pill that sits in the transport bar.
 * Shows key + time sig + live measure:beat inline.
 * Tap to expand a bottom-sheet popup with full details.
 *
 * Props:
 *   metadata    – scoreData.metadata from MusicXMLParser
 *   currentBeat – current beat position (beatOffset from playback time)
 *   isPlaying   – whether audio is playing
 */
export const ScoreInfoPanel = ({ metadata, currentBeat, isPlaying }) => {
  const [expanded, setExpanded] = useState(false);

  if (!metadata) return null;

  const {
    keySignature,
    timeSignature,
    noteRange,
    totalMeasures,
    measureBeats = [],
    totalBeats = 0,
  } = metadata;

  // Compute current measure and beat from currentBeat
  const { measureNum, beatInMeasure } = useMemo(() => {
    if (!measureBeats.length || currentBeat == null) {
      return { measureNum: 1, beatInMeasure: 1 };
    }
    for (let i = measureBeats.length - 1; i >= 0; i--) {
      const mb = measureBeats[i];
      if (currentBeat >= mb.startBeat - 0.001) {
        const beatInM = Math.floor(currentBeat - mb.startBeat) + 1;
        return {
          measureNum: mb.measureNum,
          beatInMeasure: Math.max(1, Math.min(beatInM, mb.beats)),
        };
      }
    }
    return { measureNum: 1, beatInMeasure: 1 };
  }, [currentBeat, measureBeats]);

  // Key signature display
  const keyFifths = keySignature?.fifths ?? 0;
  const keyName = KEY_NAMES[String(keyFifths)] || 'C Maj';
  const keyAccidentals = keySignature?.count > 0
    ? (keySignature.type === 'Sharps'
        ? '♯'.repeat(Math.min(keySignature.count, 3))
        : '♭'.repeat(Math.min(keySignature.count, 3)))
    : '';

  // Time signature
  const timeSigTop = timeSignature?.beats ?? 4;
  const timeSigBot = timeSignature?.beatType ?? 4;

  // Compact inline label
  const inlineLabel = `${keyAccidentals ? keyAccidentals + ' ' : ''}${keyName}  ${timeSigTop}/${timeSigBot}`;

  return (
    <>
      {/* ──── Compact pill in the transport bar ──── */}
      <TouchableOpacity
        style={styles.infoPill}
        onPress={() => setExpanded(true)}
        activeOpacity={0.7}
      >
        <Text style={styles.infoSymbol}>i</Text>
      </TouchableOpacity>

      {/* ──── Expanded popover modal ──── */}
      <Modal
        visible={expanded}
        transparent
        animationType="fade"
        onRequestClose={() => setExpanded(false)}
      >
        <TouchableOpacity
          style={styles.overlay}
          activeOpacity={1}
          onPress={() => setExpanded(false)}
        >
          <View style={styles.popup}>
            {/* Header */}
            <View style={styles.popupHeader}>
              <Text style={styles.popupTitle}>Score Info</Text>
              <TouchableOpacity onPress={() => setExpanded(false)}>
                <Text style={styles.popupClose}>✕</Text>
              </TouchableOpacity>
            </View>

            {/* Grid of info items */}
            <View style={styles.popupGrid}>
              {/* Key Signature */}
              <View style={[styles.popupItem, styles.keyItem]}>
                <Text style={styles.popupItemLabel}>Key</Text>
                <Text style={styles.popupItemValue}>
                  {keyAccidentals ? keyAccidentals + ' ' : ''}{keyName}
                </Text>
              </View>

              {/* Time Signature */}
              <View style={[styles.popupItem, styles.timeItem]}>
                <Text style={styles.popupItemLabel}>Time</Text>
                <View style={styles.timeSigStack}>
                  <Text style={styles.timeSigNum}>{timeSigTop}</Text>
                  <View style={styles.timeSigLine} />
                  <Text style={styles.timeSigNum}>{timeSigBot}</Text>
                </View>
              </View>

              {/* Note Range */}
              {noteRange && (
                <View style={[styles.popupItem, styles.rangeItem]}>
                  <Text style={styles.popupItemLabel}>Range</Text>
                  <Text style={styles.popupItemValue}>
                    {noteRange.low.pitch} → {noteRange.high.pitch}
                  </Text>
                </View>
              )}

              {/* Current Measure / Beat */}
              <View style={[styles.popupItem, styles.counterItem]}>
                <Text style={styles.popupItemLabel}>Position</Text>
                <Text style={[styles.popupItemValue, isPlaying && { color: accent }]}>
                  M{measureNum} : Beat {beatInMeasure}
                </Text>
              </View>

              {/* Totals */}
              {totalMeasures > 0 && (
                <View style={[styles.popupItem, styles.statsItem]}>
                  <Text style={styles.popupItemLabel}>Measures</Text>
                  <Text style={styles.popupItemValue}>{totalMeasures}</Text>
                </View>
              )}
              {totalBeats > 0 && (
                <View style={[styles.popupItem, styles.statsItem]}>
                  <Text style={styles.popupItemLabel}>Total Beats</Text>
                  <Text style={styles.popupItemValue}>{Math.round(totalBeats)}</Text>
                </View>
              )}
            </View>
          </View>
        </TouchableOpacity>
      </Modal>
    </>
  );
};

/* ─── Styles ─── */
const styles = StyleSheet.create({
  /* ── Compact pill (lives in transport bar) ── */
  infoPill: {
    width: 44,
    height: 44,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#22211E',
    borderRadius: 14,
    borderWidth: 1,
    borderColor: '#4B463D',
    shadowColor: '#000',
    shadowOpacity: 0.22,
    shadowRadius: 8,
    shadowOffset: { width: 0, height: 3 },
    elevation: 3,
  },
  infoSymbol: {
    fontSize: 16,
    fontWeight: '800',
    color: '#F5F2EB',
    lineHeight: 18,
  },
  inlineKey: {
    fontSize: 12,
    fontWeight: '700',
    color: '#F5F2EB',
    letterSpacing: 0.15,
  },
  mbDivider: {
    width: 1,
    height: 16,
    backgroundColor: '#5C554A',
    marginHorizontal: 9,
  },
  mbText: {
    fontSize: 12,
    fontWeight: '800',
    color: '#D2C9BC',
    letterSpacing: 0.3,
  },
  mbTextLive: {
    color: accent,
  },

  /* ── Overlay / Popup ── */
  overlay: {
    flex: 1,
    backgroundColor: 'transparent',
    justifyContent: 'center',
    alignItems: 'center',
    paddingHorizontal: 18,
  },
  popup: {
    width: '100%',
    maxWidth: 520,
    backgroundColor: '#FAF7F0',
    borderRadius: 24,
    paddingTop: 18,
    paddingBottom: 20,
    paddingHorizontal: 18,
    borderWidth: 1,
    borderColor: '#D9D2C6',
    shadowColor: '#000',
    shadowOpacity: 0.26,
    shadowRadius: 16,
    shadowOffset: { width: 0, height: 6 },
    elevation: 8,
  },
  popupHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 14,
    paddingBottom: 10,
    borderBottomWidth: 1,
    borderBottomColor: '#E4DCCE',
  },
  popupTitle: {
    fontSize: 19,
    fontWeight: '800',
    color: '#2E2B26',
    letterSpacing: -0.2,
  },
  popupClose: {
    width: 30,
    height: 30,
    textAlign: 'center',
    textAlignVertical: 'center',
    borderRadius: 15,
    backgroundColor: '#EFE9DE',
    fontSize: 15,
    color: '#5D554B',
    fontWeight: '700',
    overflow: 'hidden',
  },

  /* ── Info grid ── */
  popupGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    gap: 10,
  },
  popupItem: {
    borderRadius: 14,
    paddingHorizontal: 14,
    paddingVertical: 12,
    borderWidth: 1,
    minWidth: '45%',
    flexGrow: 1,
    backgroundColor: '#F5F0E6',
    borderColor: '#DDD3C2',
  },
  popupItemLabel: {
    fontSize: 10,
    fontWeight: '700',
    color: '#6D655A',
    textTransform: 'uppercase',
    letterSpacing: 0.6,
    marginBottom: 5,
  },
  popupItemValue: {
    fontSize: 16,
    fontWeight: '800',
    color: '#2F2B25',
  },

  /* ── Item color variants ── */
  keyItem: {
    borderLeftWidth: 3,
    borderLeftColor: accent,
  },
  timeItem: {
    borderLeftWidth: 3,
    borderLeftColor: '#9E917C',
  },
  rangeItem: {
    borderLeftWidth: 3,
    borderLeftColor: '#9E917C',
  },
  counterItem: {
    borderLeftWidth: 3,
    borderLeftColor: accent,
  },
  statsItem: {
    borderLeftWidth: 3,
    borderLeftColor: '#9E917C',
  },

  /* ── Time sig in popup ── */
  timeSigStack: {
    flexDirection: 'row',
    alignItems: 'center',
    gap: 4,
  },
  timeSigNum: {
    fontSize: 18,
    fontWeight: '900',
    color: '#2F2B25',
  },
  timeSigLine: {
    width: 1.5,
    height: 18,
    backgroundColor: '#A19687',
    marginHorizontal: 2,
  },
});
