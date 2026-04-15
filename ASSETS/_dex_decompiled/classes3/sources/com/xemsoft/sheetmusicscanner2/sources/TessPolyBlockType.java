package com.xemsoft.sheetmusicscanner2.sources;

public final class TessPolyBlockType {
    public static final TessPolyBlockType PT_CAPTION_TEXT;
    public static final TessPolyBlockType PT_COUNT;
    public static final TessPolyBlockType PT_FLOWING_IMAGE;
    public static final TessPolyBlockType PT_FLOWING_TEXT;
    public static final TessPolyBlockType PT_HEADING_IMAGE;
    public static final TessPolyBlockType PT_HEADING_TEXT;
    public static final TessPolyBlockType PT_HORZ_LINE;
    public static final TessPolyBlockType PT_NOISE;
    public static final TessPolyBlockType PT_PULLOUT_IMAGE;
    public static final TessPolyBlockType PT_PULLOUT_TEXT;
    public static final TessPolyBlockType PT_TABLE;
    public static final TessPolyBlockType PT_UNKNOWN;
    public static final TessPolyBlockType PT_VERTICAL_TEXT;
    public static final TessPolyBlockType PT_VERT_LINE;
    private static int swigNext = 0;
    private static TessPolyBlockType[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessPolyBlockType tessPolyBlockType = new TessPolyBlockType("PT_UNKNOWN");
        PT_UNKNOWN = tessPolyBlockType;
        TessPolyBlockType tessPolyBlockType2 = new TessPolyBlockType("PT_FLOWING_TEXT");
        PT_FLOWING_TEXT = tessPolyBlockType2;
        TessPolyBlockType tessPolyBlockType3 = new TessPolyBlockType("PT_HEADING_TEXT");
        PT_HEADING_TEXT = tessPolyBlockType3;
        TessPolyBlockType tessPolyBlockType4 = new TessPolyBlockType("PT_PULLOUT_TEXT");
        PT_PULLOUT_TEXT = tessPolyBlockType4;
        TessPolyBlockType tessPolyBlockType5 = new TessPolyBlockType("PT_TABLE");
        PT_TABLE = tessPolyBlockType5;
        TessPolyBlockType tessPolyBlockType6 = new TessPolyBlockType("PT_VERTICAL_TEXT");
        PT_VERTICAL_TEXT = tessPolyBlockType6;
        TessPolyBlockType tessPolyBlockType7 = new TessPolyBlockType("PT_CAPTION_TEXT");
        PT_CAPTION_TEXT = tessPolyBlockType7;
        TessPolyBlockType tessPolyBlockType8 = new TessPolyBlockType("PT_FLOWING_IMAGE");
        PT_FLOWING_IMAGE = tessPolyBlockType8;
        TessPolyBlockType tessPolyBlockType9 = new TessPolyBlockType("PT_HEADING_IMAGE");
        PT_HEADING_IMAGE = tessPolyBlockType9;
        TessPolyBlockType tessPolyBlockType10 = new TessPolyBlockType("PT_PULLOUT_IMAGE");
        PT_PULLOUT_IMAGE = tessPolyBlockType10;
        TessPolyBlockType tessPolyBlockType11 = new TessPolyBlockType("PT_HORZ_LINE");
        PT_HORZ_LINE = tessPolyBlockType11;
        TessPolyBlockType tessPolyBlockType12 = new TessPolyBlockType("PT_VERT_LINE");
        PT_VERT_LINE = tessPolyBlockType12;
        TessPolyBlockType tessPolyBlockType13 = new TessPolyBlockType("PT_NOISE");
        PT_NOISE = tessPolyBlockType13;
        TessPolyBlockType tessPolyBlockType14 = new TessPolyBlockType("PT_COUNT");
        PT_COUNT = tessPolyBlockType14;
        swigValues = new TessPolyBlockType[]{tessPolyBlockType, tessPolyBlockType2, tessPolyBlockType3, tessPolyBlockType4, tessPolyBlockType5, tessPolyBlockType6, tessPolyBlockType7, tessPolyBlockType8, tessPolyBlockType9, tessPolyBlockType10, tessPolyBlockType11, tessPolyBlockType12, tessPolyBlockType13, tessPolyBlockType14};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessPolyBlockType swigToEnum(int i) {
        TessPolyBlockType[] tessPolyBlockTypeArr = swigValues;
        if (i < tessPolyBlockTypeArr.length && i >= 0) {
            TessPolyBlockType tessPolyBlockType = tessPolyBlockTypeArr[i];
            if (tessPolyBlockType.swigValue == i) {
                return tessPolyBlockType;
            }
        }
        int i2 = 0;
        while (true) {
            TessPolyBlockType[] tessPolyBlockTypeArr2 = swigValues;
            if (i2 < tessPolyBlockTypeArr2.length) {
                TessPolyBlockType tessPolyBlockType2 = tessPolyBlockTypeArr2[i2];
                if (tessPolyBlockType2.swigValue == i) {
                    return tessPolyBlockType2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessPolyBlockType.class + " with value " + i);
            }
        }
    }

    private TessPolyBlockType(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessPolyBlockType(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessPolyBlockType(String str, TessPolyBlockType tessPolyBlockType) {
        this.swigName = str;
        int i = tessPolyBlockType.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
