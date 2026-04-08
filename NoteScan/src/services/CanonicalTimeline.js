export class CanonicalTimeline {
  constructor(scoreData) {
    this.notes = Array.isArray(scoreData?.notes) ? scoreData.notes : [];
    this.metadata = scoreData?.metadata || {};

    const metadataTotal = Number(this.metadata.totalBeats);
    const computedTotal = this.notes.reduce((mx, n) => {
      if (!Number.isFinite(n?.beatOffset)) return mx;
      const dur = Number.isFinite(n?.tiedBeats)
        ? n.tiedBeats
        : Number.isFinite(n?.durationBeats)
          ? n.durationBeats
          : 0;
      return Math.max(mx, n.beatOffset + Math.max(0, dur));
    }, 0);

    this.totalBeats = Number.isFinite(metadataTotal) && metadataTotal > 0
      ? metadataTotal
      : Math.max(0, computedTotal);
  }

  timeToBeat(secondsElapsed, tempoBpm) {
    if (!Number.isFinite(secondsElapsed) || !Number.isFinite(tempoBpm) || tempoBpm <= 0) {
      return 0;
    }
    return (secondsElapsed * tempoBpm) / 60;
  }

  beatToTime(beatOffset, tempoBpm) {
    if (!Number.isFinite(beatOffset) || !Number.isFinite(tempoBpm) || tempoBpm <= 0) {
      return 0;
    }
    return (beatOffset * 60) / tempoBpm;
  }

  totalDurationSeconds(tempoBpm) {
    return this.beatToTime(this.totalBeats, tempoBpm);
  }

  progressFromTime(secondsElapsed, tempoBpm) {
    const total = this.totalDurationSeconds(tempoBpm);
    if (!Number.isFinite(total) || total <= 0 || !Number.isFinite(secondsElapsed)) {
      return 0;
    }
    return Math.max(0, Math.min(1, secondsElapsed / total));
  }

  getMeasureAtBeat(beatOffset) {
    const measures = Array.isArray(this.metadata.measureBeats) ? this.metadata.measureBeats : [];
    if (!Number.isFinite(beatOffset) || measures.length === 0) return null;

    for (const m of measures) {
      if (beatOffset >= m.startBeat - 0.001 && beatOffset < m.endBeat + 0.001) {
        return m;
      }
    }
    return null;
  }
}
