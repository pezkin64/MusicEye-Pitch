package com.xemsoft.sheetmusicscanner2.sources;

public final class TessPageIteratorLevel {
    public static final TessPageIteratorLevel RIL_BLOCK;
    public static final TessPageIteratorLevel RIL_PARA;
    public static final TessPageIteratorLevel RIL_SYMBOL;
    public static final TessPageIteratorLevel RIL_TEXTLINE;
    public static final TessPageIteratorLevel RIL_WORD;
    private static int swigNext = 0;
    private static TessPageIteratorLevel[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessPageIteratorLevel tessPageIteratorLevel = new TessPageIteratorLevel("RIL_BLOCK");
        RIL_BLOCK = tessPageIteratorLevel;
        TessPageIteratorLevel tessPageIteratorLevel2 = new TessPageIteratorLevel("RIL_PARA");
        RIL_PARA = tessPageIteratorLevel2;
        TessPageIteratorLevel tessPageIteratorLevel3 = new TessPageIteratorLevel("RIL_TEXTLINE");
        RIL_TEXTLINE = tessPageIteratorLevel3;
        TessPageIteratorLevel tessPageIteratorLevel4 = new TessPageIteratorLevel("RIL_WORD");
        RIL_WORD = tessPageIteratorLevel4;
        TessPageIteratorLevel tessPageIteratorLevel5 = new TessPageIteratorLevel("RIL_SYMBOL");
        RIL_SYMBOL = tessPageIteratorLevel5;
        swigValues = new TessPageIteratorLevel[]{tessPageIteratorLevel, tessPageIteratorLevel2, tessPageIteratorLevel3, tessPageIteratorLevel4, tessPageIteratorLevel5};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessPageIteratorLevel swigToEnum(int i) {
        TessPageIteratorLevel[] tessPageIteratorLevelArr = swigValues;
        if (i < tessPageIteratorLevelArr.length && i >= 0) {
            TessPageIteratorLevel tessPageIteratorLevel = tessPageIteratorLevelArr[i];
            if (tessPageIteratorLevel.swigValue == i) {
                return tessPageIteratorLevel;
            }
        }
        int i2 = 0;
        while (true) {
            TessPageIteratorLevel[] tessPageIteratorLevelArr2 = swigValues;
            if (i2 < tessPageIteratorLevelArr2.length) {
                TessPageIteratorLevel tessPageIteratorLevel2 = tessPageIteratorLevelArr2[i2];
                if (tessPageIteratorLevel2.swigValue == i) {
                    return tessPageIteratorLevel2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessPageIteratorLevel.class + " with value " + i);
            }
        }
    }

    private TessPageIteratorLevel(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessPageIteratorLevel(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessPageIteratorLevel(String str, TessPageIteratorLevel tessPageIteratorLevel) {
        this.swigName = str;
        int i = tessPageIteratorLevel.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
