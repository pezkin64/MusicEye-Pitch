package jp.kshoji.javax.sound.midi;

import java.util.Vector;

public class Sequence {
    public static final float PPQ = 0.0f;
    public static final float SMPTE_24 = 24.0f;
    public static final float SMPTE_25 = 25.0f;
    public static final float SMPTE_30 = 30.0f;
    public static final float SMPTE_30DROP = 29.97f;
    protected float divisionType;
    protected int resolution;
    protected Vector<Track> tracks;

    private static boolean isSupportingDivisionType(float f) {
        return f == PPQ || f == 24.0f || f == 25.0f || f == 30.0f || f == 29.97f;
    }

    public Sequence(float f, int i) throws InvalidMidiDataException {
        if (isSupportingDivisionType(f)) {
            this.divisionType = f;
            this.resolution = i;
            this.tracks = new Vector<>();
            return;
        }
        throw new InvalidMidiDataException("Unsupported division type: " + f);
    }

    public Sequence(float f, int i, int i2) throws InvalidMidiDataException {
        this(f, i);
        if (i2 > 0) {
            for (int i3 = 0; i3 < i2; i3++) {
                this.tracks.add(new Track());
            }
        }
    }

    public Track createTrack() {
        Track track = new Track();
        this.tracks.add(track);
        return track;
    }

    public boolean deleteTrack(Track track) {
        return this.tracks.remove(track);
    }

    public float getDivisionType() {
        return this.divisionType;
    }

    public long getMicrosecondLength() {
        float tickLength = ((float) getTickLength()) * 1000000.0f;
        float f = this.divisionType;
        if (f == PPQ) {
            f = 2.0f;
        }
        return (long) (tickLength / ((f * ((float) this.resolution)) * 1.0f));
    }

    public int getResolution() {
        return this.resolution;
    }

    public long getTickLength() {
        long j = 0;
        for (int i = 0; i < this.tracks.size(); i++) {
            j = Math.max(j, this.tracks.get(i).ticks());
        }
        return j;
    }

    public Track[] getTracks() {
        Track[] trackArr = new Track[this.tracks.size()];
        this.tracks.toArray(trackArr);
        return trackArr;
    }

    public Patch[] getPatchList() {
        return new Patch[0];
    }
}
