package com.xemsoft.sheetmusicscanner2.sources;

public final class TessPageSegMode {
    public static final TessPageSegMode PSM_AUTO;
    public static final TessPageSegMode PSM_AUTO_ONLY;
    public static final TessPageSegMode PSM_AUTO_OSD;
    public static final TessPageSegMode PSM_CIRCLE_WORD;
    public static final TessPageSegMode PSM_COUNT;
    public static final TessPageSegMode PSM_OSD_ONLY;
    public static final TessPageSegMode PSM_SINGLE_BLOCK;
    public static final TessPageSegMode PSM_SINGLE_BLOCK_VERT_TEXT;
    public static final TessPageSegMode PSM_SINGLE_CHAR;
    public static final TessPageSegMode PSM_SINGLE_COLUMN;
    public static final TessPageSegMode PSM_SINGLE_LINE;
    public static final TessPageSegMode PSM_SINGLE_WORD;
    public static final TessPageSegMode PSM_SPARSE_TEXT;
    public static final TessPageSegMode PSM_SPARSE_TEXT_OSD;
    private static int swigNext = 0;
    private static TessPageSegMode[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessPageSegMode tessPageSegMode = new TessPageSegMode("PSM_OSD_ONLY");
        PSM_OSD_ONLY = tessPageSegMode;
        TessPageSegMode tessPageSegMode2 = new TessPageSegMode("PSM_AUTO_OSD");
        PSM_AUTO_OSD = tessPageSegMode2;
        TessPageSegMode tessPageSegMode3 = new TessPageSegMode("PSM_AUTO_ONLY");
        PSM_AUTO_ONLY = tessPageSegMode3;
        TessPageSegMode tessPageSegMode4 = new TessPageSegMode("PSM_AUTO");
        PSM_AUTO = tessPageSegMode4;
        TessPageSegMode tessPageSegMode5 = new TessPageSegMode("PSM_SINGLE_COLUMN");
        PSM_SINGLE_COLUMN = tessPageSegMode5;
        TessPageSegMode tessPageSegMode6 = new TessPageSegMode("PSM_SINGLE_BLOCK_VERT_TEXT");
        PSM_SINGLE_BLOCK_VERT_TEXT = tessPageSegMode6;
        TessPageSegMode tessPageSegMode7 = new TessPageSegMode("PSM_SINGLE_BLOCK");
        PSM_SINGLE_BLOCK = tessPageSegMode7;
        TessPageSegMode tessPageSegMode8 = new TessPageSegMode("PSM_SINGLE_LINE");
        PSM_SINGLE_LINE = tessPageSegMode8;
        TessPageSegMode tessPageSegMode9 = new TessPageSegMode("PSM_SINGLE_WORD");
        PSM_SINGLE_WORD = tessPageSegMode9;
        TessPageSegMode tessPageSegMode10 = new TessPageSegMode("PSM_CIRCLE_WORD");
        PSM_CIRCLE_WORD = tessPageSegMode10;
        TessPageSegMode tessPageSegMode11 = new TessPageSegMode("PSM_SINGLE_CHAR");
        PSM_SINGLE_CHAR = tessPageSegMode11;
        TessPageSegMode tessPageSegMode12 = new TessPageSegMode("PSM_SPARSE_TEXT");
        PSM_SPARSE_TEXT = tessPageSegMode12;
        TessPageSegMode tessPageSegMode13 = new TessPageSegMode("PSM_SPARSE_TEXT_OSD");
        PSM_SPARSE_TEXT_OSD = tessPageSegMode13;
        TessPageSegMode tessPageSegMode14 = new TessPageSegMode("PSM_COUNT");
        PSM_COUNT = tessPageSegMode14;
        swigValues = new TessPageSegMode[]{tessPageSegMode, tessPageSegMode2, tessPageSegMode3, tessPageSegMode4, tessPageSegMode5, tessPageSegMode6, tessPageSegMode7, tessPageSegMode8, tessPageSegMode9, tessPageSegMode10, tessPageSegMode11, tessPageSegMode12, tessPageSegMode13, tessPageSegMode14};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessPageSegMode swigToEnum(int i) {
        TessPageSegMode[] tessPageSegModeArr = swigValues;
        if (i < tessPageSegModeArr.length && i >= 0) {
            TessPageSegMode tessPageSegMode = tessPageSegModeArr[i];
            if (tessPageSegMode.swigValue == i) {
                return tessPageSegMode;
            }
        }
        int i2 = 0;
        while (true) {
            TessPageSegMode[] tessPageSegModeArr2 = swigValues;
            if (i2 < tessPageSegModeArr2.length) {
                TessPageSegMode tessPageSegMode2 = tessPageSegModeArr2[i2];
                if (tessPageSegMode2.swigValue == i) {
                    return tessPageSegMode2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessPageSegMode.class + " with value " + i);
            }
        }
    }

    private TessPageSegMode(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessPageSegMode(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessPageSegMode(String str, TessPageSegMode tessPageSegMode) {
        this.swigName = str;
        int i = tessPageSegMode.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
