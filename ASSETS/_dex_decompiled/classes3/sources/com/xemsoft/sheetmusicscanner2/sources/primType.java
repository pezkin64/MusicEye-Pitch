package com.xemsoft.sheetmusicscanner2.sources;

public final class primType {
    public static final primType kPAccFlat;
    public static final primType kPAccNatural;
    public static final primType kPAccSharp;
    public static final primType kPBarLine;
    public static final primType kPBarLineThick;
    public static final primType kPClefAlto;
    public static final primType kPClefBass;
    public static final primType kPClefTreble;
    public static final primType kPDurationDot;
    public static final primType kPHook16Down;
    public static final primType kPHook16Up;
    public static final primType kPHook32Down;
    public static final primType kPHook32Up;
    public static final primType kPHook8Down;
    public static final primType kPHook8Up;
    public static final primType kPKeySignature;
    public static final primType kPNotIdentified;
    public static final primType kPNote16;
    public static final primType kPNote32;
    public static final primType kPNote4;
    public static final primType kPNote8;
    public static final primType kPNoteBeam;
    public static final primType kPNoteHalf;
    public static final primType kPNoteStem;
    public static final primType kPNoteWhole;
    public static final primType kPRest16;
    public static final primType kPRest32;
    public static final primType kPRest8;
    public static final primType kPRestCrotchet;
    public static final primType kPRestHalf;
    public static final primType kPRestMeasure;
    public static final primType kPRestWhole;
    public static final primType kPTimeSignature;
    private static int swigNext = 0;
    private static primType[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        primType primtype = new primType("kPNotIdentified", JniSourceJNI.kPNotIdentified_get());
        kPNotIdentified = primtype;
        primType primtype2 = new primType("kPClefTreble", JniSourceJNI.kPClefTreble_get());
        kPClefTreble = primtype2;
        primType primtype3 = new primType("kPClefBass", JniSourceJNI.kPClefBass_get());
        kPClefBass = primtype3;
        primType primtype4 = new primType("kPClefAlto", JniSourceJNI.kPClefAlto_get());
        kPClefAlto = primtype4;
        primType primtype5 = new primType("kPTimeSignature", JniSourceJNI.kPTimeSignature_get());
        kPTimeSignature = primtype5;
        primType primtype6 = new primType("kPNote4", JniSourceJNI.kPNote4_get());
        kPNote4 = primtype6;
        primType primtype7 = new primType("kPNoteWhole", JniSourceJNI.kPNoteWhole_get());
        kPNoteWhole = primtype7;
        primType primtype8 = new primType("kPNoteHalf", JniSourceJNI.kPNoteHalf_get());
        kPNoteHalf = primtype8;
        primType primtype9 = new primType("kPNote8", JniSourceJNI.kPNote8_get());
        kPNote8 = primtype9;
        primType primtype10 = new primType("kPNote16", JniSourceJNI.kPNote16_get());
        kPNote16 = primtype10;
        primType primtype11 = new primType("kPNote32", JniSourceJNI.kPNote32_get());
        kPNote32 = primtype11;
        primType primtype12 = new primType("kPAccFlat", JniSourceJNI.kPAccFlat_get());
        kPAccFlat = primtype12;
        primType primtype13 = new primType("kPAccNatural", JniSourceJNI.kPAccNatural_get());
        kPAccNatural = primtype13;
        primType primtype14 = new primType("kPAccSharp", JniSourceJNI.kPAccSharp_get());
        kPAccSharp = primtype14;
        primType primtype15 = new primType("kPRestMeasure", JniSourceJNI.kPRestMeasure_get());
        kPRestMeasure = primtype15;
        primType primtype16 = new primType("kPRestWhole", JniSourceJNI.kPRestWhole_get());
        kPRestWhole = primtype16;
        primType primtype17 = new primType("kPRestHalf", JniSourceJNI.kPRestHalf_get());
        kPRestHalf = primtype17;
        primType primtype18 = new primType("kPRestCrotchet", JniSourceJNI.kPRestCrotchet_get());
        kPRestCrotchet = primtype18;
        primType primtype19 = new primType("kPRest8", JniSourceJNI.kPRest8_get());
        kPRest8 = primtype19;
        primType primtype20 = new primType("kPRest16", JniSourceJNI.kPRest16_get());
        kPRest16 = primtype20;
        primType primtype21 = new primType("kPRest32", JniSourceJNI.kPRest32_get());
        kPRest32 = primtype21;
        primType primtype22 = new primType("kPDurationDot", JniSourceJNI.kPDurationDot_get());
        kPDurationDot = primtype22;
        primType primtype23 = new primType("kPHook8Down", JniSourceJNI.kPHook8Down_get());
        kPHook8Down = primtype23;
        primType primtype24 = new primType("kPHook8Up", JniSourceJNI.kPHook8Up_get());
        kPHook8Up = primtype24;
        primType primtype25 = new primType("kPHook16Down", JniSourceJNI.kPHook16Down_get());
        kPHook16Down = primtype25;
        primType primtype26 = new primType("kPHook16Up", JniSourceJNI.kPHook16Up_get());
        kPHook16Up = primtype26;
        primType primtype27 = new primType("kPHook32Down", JniSourceJNI.kPHook32Down_get());
        kPHook32Down = primtype27;
        primType primtype28 = new primType("kPHook32Up", JniSourceJNI.kPHook32Up_get());
        kPHook32Up = primtype28;
        primType primtype29 = new primType("kPNoteBeam", JniSourceJNI.kPNoteBeam_get());
        kPNoteBeam = primtype29;
        primType primtype30 = new primType("kPNoteStem", JniSourceJNI.kPNoteStem_get());
        kPNoteStem = primtype30;
        primType primtype31 = new primType("kPBarLine", JniSourceJNI.kPBarLine_get());
        kPBarLine = primtype31;
        primType primtype32 = new primType("kPBarLineThick", JniSourceJNI.kPBarLineThick_get());
        kPBarLineThick = primtype32;
        primType primtype33 = new primType("kPKeySignature", JniSourceJNI.kPKeySignature_get());
        kPKeySignature = primtype33;
        swigValues = new primType[]{primtype, primtype2, primtype3, primtype4, primtype5, primtype6, primtype7, primtype8, primtype9, primtype10, primtype11, primtype12, primtype13, primtype14, primtype15, primtype16, primtype17, primtype18, primtype19, primtype20, primtype21, primtype22, primtype23, primtype24, primtype25, primtype26, primtype27, primtype28, primtype29, primtype30, primtype31, primtype32, primtype33};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static primType swigToEnum(int i) {
        primType[] primtypeArr = swigValues;
        if (i < primtypeArr.length && i >= 0) {
            primType primtype = primtypeArr[i];
            if (primtype.swigValue == i) {
                return primtype;
            }
        }
        int i2 = 0;
        while (true) {
            primType[] primtypeArr2 = swigValues;
            if (i2 < primtypeArr2.length) {
                primType primtype2 = primtypeArr2[i2];
                if (primtype2.swigValue == i) {
                    return primtype2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + primType.class + " with value " + i);
            }
        }
    }

    private primType(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private primType(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private primType(String str, primType primtype) {
        this.swigName = str;
        int i = primtype.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
