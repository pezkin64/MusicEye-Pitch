package cn.sherlock.com.sun.media.sound;

import androidx.core.view.MotionEventCompat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SF2Region {
    public static final int GENERATOR_ATTACKMODENV = 26;
    public static final int GENERATOR_ATTACKVOLENV = 34;
    public static final int GENERATOR_CHORUSEFFECTSSEND = 15;
    public static final int GENERATOR_COARSETUNE = 51;
    public static final int GENERATOR_DECAYMODENV = 28;
    public static final int GENERATOR_DECAYVOLENV = 36;
    public static final int GENERATOR_DELAYMODENV = 25;
    public static final int GENERATOR_DELAYMODLFO = 21;
    public static final int GENERATOR_DELAYVIBLFO = 23;
    public static final int GENERATOR_DELAYVOLENV = 33;
    public static final int GENERATOR_ENDADDRSCOARSEOFFSET = 12;
    public static final int GENERATOR_ENDADDRSOFFSET = 1;
    public static final int GENERATOR_ENDLOOPADDRSCOARSEOFFSET = 50;
    public static final int GENERATOR_ENDLOOPADDRSOFFSET = 3;
    public static final int GENERATOR_ENDOPR = 60;
    public static final int GENERATOR_EXCLUSIVECLASS = 57;
    public static final int GENERATOR_FINETUNE = 52;
    public static final int GENERATOR_FREQMODLFO = 22;
    public static final int GENERATOR_FREQVIBLFO = 24;
    public static final int GENERATOR_HOLDMODENV = 27;
    public static final int GENERATOR_HOLDVOLENV = 35;
    public static final int GENERATOR_INITIALATTENUATION = 48;
    public static final int GENERATOR_INITIALFILTERFC = 8;
    public static final int GENERATOR_INITIALFILTERQ = 9;
    public static final int GENERATOR_INSTRUMENT = 41;
    public static final int GENERATOR_KEYNUM = 46;
    public static final int GENERATOR_KEYNUMTOMODENVDECAY = 32;
    public static final int GENERATOR_KEYNUMTOMODENVHOLD = 31;
    public static final int GENERATOR_KEYNUMTOVOLENVDECAY = 40;
    public static final int GENERATOR_KEYNUMTOVOLENVHOLD = 39;
    public static final int GENERATOR_KEYRANGE = 43;
    public static final int GENERATOR_MODENVTOFILTERFC = 11;
    public static final int GENERATOR_MODENVTOPITCH = 7;
    public static final int GENERATOR_MODLFOTOFILTERFC = 10;
    public static final int GENERATOR_MODLFOTOPITCH = 5;
    public static final int GENERATOR_MODLFOTOVOLUME = 13;
    public static final int GENERATOR_OVERRIDINGROOTKEY = 58;
    public static final int GENERATOR_PAN = 17;
    public static final int GENERATOR_RELEASEMODENV = 30;
    public static final int GENERATOR_RELEASEVOLENV = 38;
    public static final int GENERATOR_RESERVED1 = 42;
    public static final int GENERATOR_RESERVED2 = 49;
    public static final int GENERATOR_RESERVED3 = 55;
    public static final int GENERATOR_REVERBEFFECTSSEND = 16;
    public static final int GENERATOR_SAMPLEID = 53;
    public static final int GENERATOR_SAMPLEMODES = 54;
    public static final int GENERATOR_SCALETUNING = 56;
    public static final int GENERATOR_STARTADDRSCOARSEOFFSET = 4;
    public static final int GENERATOR_STARTADDRSOFFSET = 0;
    public static final int GENERATOR_STARTLOOPADDRSCOARSEOFFSET = 45;
    public static final int GENERATOR_STARTLOOPADDRSOFFSET = 2;
    public static final int GENERATOR_SUSTAINMODENV = 29;
    public static final int GENERATOR_SUSTAINVOLENV = 37;
    public static final int GENERATOR_UNUSED1 = 14;
    public static final int GENERATOR_UNUSED2 = 18;
    public static final int GENERATOR_UNUSED3 = 19;
    public static final int GENERATOR_UNUSED4 = 20;
    public static final int GENERATOR_UNUSED5 = 59;
    public static final int GENERATOR_VELOCITY = 47;
    public static final int GENERATOR_VELRANGE = 44;
    public static final int GENERATOR_VIBLFOTOPITCH = 6;
    protected Map<Integer, Short> generators = new HashMap();
    protected List<SF2Modulator> modulators = new ArrayList();

    public static short getDefaultValue(int i) {
        if (i == 8) {
            return 13500;
        }
        if (i == 21 || i == 23 || i == 25 || i == 26 || i == 27 || i == 28 || i == 30 || i == 33 || i == 34 || i == 35 || i == 36 || i == 38) {
            return -12000;
        }
        if (i == 43 || i == 44) {
            return 32512;
        }
        if (i == 46 || i == 47) {
            return -1;
        }
        if (i == 56) {
            return 100;
        }
        return i == 58 ? (short) -1 : 0;
    }

    public Map<Integer, Short> getGenerators() {
        return this.generators;
    }

    public boolean contains(int i) {
        return this.generators.containsKey(Integer.valueOf(i));
    }

    public short getShort(int i) {
        if (!contains(i)) {
            return getDefaultValue(i);
        }
        return this.generators.get(Integer.valueOf(i)).shortValue();
    }

    public void putShort(int i, short s) {
        this.generators.put(Integer.valueOf(i), Short.valueOf(s));
    }

    public byte[] getBytes(int i) {
        int integer = getInteger(i);
        return new byte[]{(byte) (integer & 255), (byte) ((integer & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8)};
    }

    public void putBytes(int i, byte[] bArr) {
        this.generators.put(Integer.valueOf(i), Short.valueOf((short) (bArr[0] + (bArr[1] << 8))));
    }

    public int getInteger(int i) {
        return getShort(i) & 65535;
    }

    public void putInteger(int i, int i2) {
        this.generators.put(Integer.valueOf(i), Short.valueOf((short) i2));
    }

    public List<SF2Modulator> getModulators() {
        return this.modulators;
    }
}
