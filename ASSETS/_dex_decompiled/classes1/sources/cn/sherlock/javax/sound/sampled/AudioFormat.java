package cn.sherlock.javax.sound.sampled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AudioFormat {
    protected boolean bigEndian;
    protected int channels;
    protected Encoding encoding;
    protected float frameRate;
    protected int frameSize;
    private HashMap<String, Object> properties;
    protected float sampleRate;
    protected int sampleSizeInBits;

    public AudioFormat(Encoding encoding2, float f, int i, int i2, int i3, float f2, boolean z) {
        this.encoding = encoding2;
        this.sampleRate = f;
        this.sampleSizeInBits = i;
        this.channels = i2;
        this.frameSize = i3;
        this.frameRate = f2;
        this.bigEndian = z;
        this.properties = null;
    }

    public AudioFormat(Encoding encoding2, float f, int i, int i2, int i3, float f2, boolean z, Map<String, Object> map) {
        this(encoding2, f, i, i2, i3, f2, z);
        this.properties = new HashMap<>(map);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AudioFormat(float r9, int r10, int r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            r0 = 1
            if (r12 != r0) goto L_0x0006
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r12 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED
            goto L_0x0008
        L_0x0006:
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r12 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_UNSIGNED
        L_0x0008:
            r1 = r12
            r12 = -1
            if (r11 == r12) goto L_0x0014
            if (r10 != r12) goto L_0x000f
            goto L_0x0014
        L_0x000f:
            int r12 = r10 + 7
            int r12 = r12 / 8
            int r12 = r12 * r11
        L_0x0014:
            r5 = r12
            r6 = r9
            r0 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r7 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.javax.sound.sampled.AudioFormat.<init>(float, int, int, boolean, boolean):void");
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public float getSampleRate() {
        return this.sampleRate;
    }

    public int getSampleSizeInBits() {
        return this.sampleSizeInBits;
    }

    public int getChannels() {
        return this.channels;
    }

    public int getFrameSize() {
        return this.frameSize;
    }

    public float getFrameRate() {
        return this.frameRate;
    }

    public boolean isBigEndian() {
        return this.bigEndian;
    }

    public Map<String, Object> properties() {
        Map map;
        HashMap<String, Object> hashMap = this.properties;
        if (hashMap == null) {
            map = new HashMap(0);
        } else {
            map = (Map) hashMap.clone();
        }
        return Collections.unmodifiableMap(map);
    }

    public Object getProperty(String str) {
        HashMap<String, Object> hashMap = this.properties;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(str);
    }

    public boolean matches(AudioFormat audioFormat) {
        if (!audioFormat.getEncoding().equals(getEncoding())) {
            return false;
        }
        if (audioFormat.getChannels() != -1 && audioFormat.getChannels() != getChannels()) {
            return false;
        }
        if (audioFormat.getSampleRate() != -1.0f && audioFormat.getSampleRate() != getSampleRate()) {
            return false;
        }
        if (audioFormat.getSampleSizeInBits() != -1 && audioFormat.getSampleSizeInBits() != getSampleSizeInBits()) {
            return false;
        }
        if (audioFormat.getFrameRate() != -1.0f && audioFormat.getFrameRate() != getFrameRate()) {
            return false;
        }
        if (audioFormat.getFrameSize() == -1 || audioFormat.getFrameSize() == getFrameSize()) {
            return getSampleSizeInBits() <= 8 || audioFormat.isBigEndian() == isBigEndian();
        }
        return false;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7 = "";
        if (getEncoding() != null) {
            str = getEncoding().toString() + " ";
        } else {
            str = str7;
        }
        if (getSampleRate() == -1.0f) {
            str2 = "unknown sample rate, ";
        } else {
            str2 = str7 + getSampleRate() + " Hz, ";
        }
        if (((float) getSampleSizeInBits()) == -1.0f) {
            str3 = "unknown bits per sample, ";
        } else {
            str3 = str7 + getSampleSizeInBits() + " bit, ";
        }
        if (getChannels() == 1) {
            str4 = "mono, ";
        } else if (getChannels() == 2) {
            str4 = "stereo, ";
        } else if (getChannels() == -1) {
            str4 = " unknown number of channels, ";
        } else {
            str4 = str7 + getChannels() + " channels, ";
        }
        if (((float) getFrameSize()) == -1.0f) {
            str5 = "unknown frame size, ";
        } else {
            str5 = str7 + getFrameSize() + " bytes/frame, ";
        }
        if (((double) Math.abs(getSampleRate() - getFrameRate())) <= 1.0E-5d) {
            str6 = str7;
        } else if (getFrameRate() == -1.0f) {
            str6 = "unknown frame rate, ";
        } else {
            str6 = getFrameRate() + " frames/second, ";
        }
        if ((getEncoding().equals(Encoding.PCM_SIGNED) || getEncoding().equals(Encoding.PCM_UNSIGNED)) && (getSampleSizeInBits() > 8 || getSampleSizeInBits() == -1)) {
            str7 = isBigEndian() ? "big-endian" : "little-endian";
        }
        return str + str2 + str3 + str4 + str5 + str6 + str7;
    }

    public static class Encoding {
        public static final Encoding ALAW = new Encoding("ALAW");
        public static final Encoding PCM_FLOAT = new Encoding("PCM_FLOAT");
        public static final Encoding PCM_SIGNED = new Encoding("PCM_SIGNED");
        public static final Encoding PCM_UNSIGNED = new Encoding("PCM_UNSIGNED");
        public static final Encoding ULAW = new Encoding("ULAW");
        private String name;

        public Encoding(String str) {
            this.name = str;
        }

        public final boolean equals(Object obj) {
            if (toString() == null) {
                if (obj == null || obj.toString() != null) {
                    return false;
                }
                return true;
            } else if (obj instanceof Encoding) {
                return toString().equals(obj.toString());
            } else {
                return false;
            }
        }

        public final int hashCode() {
            if (toString() == null) {
                return 0;
            }
            return toString().hashCode();
        }

        public final String toString() {
            return this.name;
        }
    }
}
