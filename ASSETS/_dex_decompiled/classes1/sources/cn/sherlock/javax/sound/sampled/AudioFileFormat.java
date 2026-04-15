package cn.sherlock.javax.sound.sampled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AudioFileFormat {
    private int byteLength;
    private AudioFormat format;
    private int frameLength;
    private HashMap<String, Object> properties;
    private Type type;

    protected AudioFileFormat(Type type2, int i, AudioFormat audioFormat, int i2) {
        this.type = type2;
        this.byteLength = i;
        this.format = audioFormat;
        this.frameLength = i2;
        this.properties = null;
    }

    public AudioFileFormat(Type type2, AudioFormat audioFormat, int i) {
        this(type2, -1, audioFormat, i);
    }

    public AudioFileFormat(Type type2, AudioFormat audioFormat, int i, Map<String, Object> map) {
        this(type2, -1, audioFormat, i);
        this.properties = new HashMap<>(map);
    }

    public Type getType() {
        return this.type;
    }

    public int getByteLength() {
        return this.byteLength;
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public int getFrameLength() {
        return this.frameLength;
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        if (this.type != null) {
            stringBuffer.append(this.type.toString() + " (." + this.type.getExtension() + ") file");
        } else {
            stringBuffer.append("unknown file format");
        }
        if (this.byteLength != -1) {
            stringBuffer.append(", byte length: " + this.byteLength);
        }
        stringBuffer.append(", data format: " + this.format);
        if (this.frameLength != -1) {
            stringBuffer.append(", frame length: " + this.frameLength);
        }
        return new String(stringBuffer);
    }

    public static class Type {
        public static final Type AIFC = new Type("AIFF-C", "aifc");
        public static final Type AIFF = new Type("AIFF", "aif");
        public static final Type AU = new Type("AU", "au");
        public static final Type SND = new Type("SND", "snd");
        public static final Type WAVE = new Type("WAVE", "wav");
        private final String extension;
        private final String name;

        public Type(String str, String str2) {
            this.name = str;
            this.extension = str2;
        }

        public final boolean equals(Object obj) {
            if (toString() == null) {
                if (obj == null || obj.toString() != null) {
                    return false;
                }
                return true;
            } else if (obj instanceof Type) {
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

        public String getExtension() {
            return this.extension;
        }
    }
}
