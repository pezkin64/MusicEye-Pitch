package com.xemsoft.sheetmusicscanner2.sources;

public final class symbolType {
    public static final symbolType kSAccFlat;
    public static final symbolType kSAccNatural;
    public static final symbolType kSAccSharp;
    public static final symbolType kSClefAlto;
    public static final symbolType kSClefBass;
    public static final symbolType kSClefTreble;
    public static final symbolType kSDurationDot;
    public static final symbolType kSNotIdentified;
    public static final symbolType kSNote16;
    public static final symbolType kSNote32;
    public static final symbolType kSNote4;
    public static final symbolType kSNote8;
    public static final symbolType kSNoteHalf;
    public static final symbolType kSNoteWhole;
    public static final symbolType kSRest16;
    public static final symbolType kSRest32;
    public static final symbolType kSRest8;
    public static final symbolType kSRestCrotchet;
    public static final symbolType kSRestHalf;
    public static final symbolType kSRestMeasure;
    public static final symbolType kSRestWhole;
    public static final symbolType kSTimeSignatureCommon;
    public static final symbolType kSTimeSignatureCut;
    public static final symbolType kSTimeSignatureNormal;
    public static final symbolType kSTimeSignatureUnIdentified;
    private static int swigNext = 0;
    private static symbolType[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        symbolType symboltype = new symbolType("kSNotIdentified", JniSourceJNI.kSNotIdentified_get());
        kSNotIdentified = symboltype;
        symbolType symboltype2 = new symbolType("kSClefTreble", JniSourceJNI.kSClefTreble_get());
        kSClefTreble = symboltype2;
        symbolType symboltype3 = new symbolType("kSClefBass", JniSourceJNI.kSClefBass_get());
        kSClefBass = symboltype3;
        symbolType symboltype4 = new symbolType("kSClefAlto", JniSourceJNI.kSClefAlto_get());
        kSClefAlto = symboltype4;
        symbolType symboltype5 = new symbolType("kSNote4", JniSourceJNI.kSNote4_get());
        kSNote4 = symboltype5;
        symbolType symboltype6 = new symbolType("kSNoteWhole", JniSourceJNI.kSNoteWhole_get());
        kSNoteWhole = symboltype6;
        symbolType symboltype7 = new symbolType("kSNoteHalf", JniSourceJNI.kSNoteHalf_get());
        kSNoteHalf = symboltype7;
        symbolType symboltype8 = new symbolType("kSNote8", JniSourceJNI.kSNote8_get());
        kSNote8 = symboltype8;
        symbolType symboltype9 = new symbolType("kSNote16", JniSourceJNI.kSNote16_get());
        kSNote16 = symboltype9;
        symbolType symboltype10 = new symbolType("kSNote32", JniSourceJNI.kSNote32_get());
        kSNote32 = symboltype10;
        symbolType symboltype11 = new symbolType("kSAccFlat", JniSourceJNI.kSAccFlat_get());
        kSAccFlat = symboltype11;
        symbolType symboltype12 = new symbolType("kSAccNatural", JniSourceJNI.kSAccNatural_get());
        kSAccNatural = symboltype12;
        symbolType symboltype13 = new symbolType("kSAccSharp", JniSourceJNI.kSAccSharp_get());
        kSAccSharp = symboltype13;
        symbolType symboltype14 = new symbolType("kSRestMeasure", JniSourceJNI.kSRestMeasure_get());
        kSRestMeasure = symboltype14;
        symbolType symboltype15 = new symbolType("kSRestWhole", JniSourceJNI.kSRestWhole_get());
        kSRestWhole = symboltype15;
        symbolType symboltype16 = new symbolType("kSRestHalf", JniSourceJNI.kSRestHalf_get());
        kSRestHalf = symboltype16;
        symbolType symboltype17 = new symbolType("kSRestCrotchet", JniSourceJNI.kSRestCrotchet_get());
        kSRestCrotchet = symboltype17;
        symbolType symboltype18 = new symbolType("kSRest8", JniSourceJNI.kSRest8_get());
        kSRest8 = symboltype18;
        symbolType symboltype19 = new symbolType("kSRest16", JniSourceJNI.kSRest16_get());
        kSRest16 = symboltype19;
        symbolType symboltype20 = new symbolType("kSRest32", JniSourceJNI.kSRest32_get());
        kSRest32 = symboltype20;
        symbolType symboltype21 = new symbolType("kSDurationDot", JniSourceJNI.kSDurationDot_get());
        kSDurationDot = symboltype21;
        symbolType symboltype22 = new symbolType("kSTimeSignatureUnIdentified", JniSourceJNI.kSTimeSignatureUnIdentified_get());
        kSTimeSignatureUnIdentified = symboltype22;
        symbolType symboltype23 = new symbolType("kSTimeSignatureNormal", JniSourceJNI.kSTimeSignatureNormal_get());
        kSTimeSignatureNormal = symboltype23;
        symbolType symboltype24 = new symbolType("kSTimeSignatureCommon", JniSourceJNI.kSTimeSignatureCommon_get());
        kSTimeSignatureCommon = symboltype24;
        symbolType symboltype25 = new symbolType("kSTimeSignatureCut", JniSourceJNI.kSTimeSignatureCut_get());
        kSTimeSignatureCut = symboltype25;
        swigValues = new symbolType[]{symboltype, symboltype2, symboltype3, symboltype4, symboltype5, symboltype6, symboltype7, symboltype8, symboltype9, symboltype10, symboltype11, symboltype12, symboltype13, symboltype14, symboltype15, symboltype16, symboltype17, symboltype18, symboltype19, symboltype20, symboltype21, symboltype22, symboltype23, symboltype24, symboltype25};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static symbolType swigToEnum(int i) {
        symbolType[] symboltypeArr = swigValues;
        if (i < symboltypeArr.length && i >= 0) {
            symbolType symboltype = symboltypeArr[i];
            if (symboltype.swigValue == i) {
                return symboltype;
            }
        }
        int i2 = 0;
        while (true) {
            symbolType[] symboltypeArr2 = swigValues;
            if (i2 < symboltypeArr2.length) {
                symbolType symboltype2 = symboltypeArr2[i2];
                if (symboltype2.swigValue == i) {
                    return symboltype2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + symbolType.class + " with value " + i);
            }
        }
    }

    private symbolType(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private symbolType(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private symbolType(String str, symbolType symboltype) {
        this.swigName = str;
        int i = symboltype.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
