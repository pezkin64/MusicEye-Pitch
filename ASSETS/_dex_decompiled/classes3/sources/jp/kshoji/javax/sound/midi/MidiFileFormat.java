package jp.kshoji.javax.sound.midi;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MidiFileFormat {
    public static final int HEADER_MThd = 1297377380;
    public static final int HEADER_MTrk = 1297379947;
    public static final int UNKNOWN_LENGTH = -1;
    protected int byteLength;
    protected float divisionType;
    protected long microsecondLength;
    private HashMap<String, Object> properties;
    protected int resolution;
    protected int type;

    public MidiFileFormat(int i, float f, int i2, int i3, long j) {
        this.type = i;
        this.divisionType = f;
        this.resolution = i2;
        this.byteLength = i3;
        this.microsecondLength = j;
        this.properties = new HashMap<>();
    }

    public MidiFileFormat(int i, float f, int i2, int i3, long j, Map<String, Object> map) {
        this(i, f, i2, i3, j);
        this.properties.putAll(map);
    }

    public int getByteLength() {
        return this.byteLength;
    }

    public float getDivisionType() {
        return this.divisionType;
    }

    public long getMicrosecondLength() {
        return this.microsecondLength;
    }

    public Object getProperty(String str) {
        return this.properties.get(str);
    }

    public int getResolution() {
        return this.resolution;
    }

    public int getType() {
        return this.type;
    }

    public Map<String, Object> properties() {
        return Collections.unmodifiableMap(this.properties);
    }
}
