package com.xemsoft.sheetmusicscanner2.sources;

public final class TessOcrEngineMode {
    public static final TessOcrEngineMode OEM_CUBE_ONLY;
    public static final TessOcrEngineMode OEM_DEFAULT;
    public static final TessOcrEngineMode OEM_TESSERACT_CUBE_COMBINED;
    public static final TessOcrEngineMode OEM_TESSERACT_ONLY;
    private static int swigNext = 0;
    private static TessOcrEngineMode[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessOcrEngineMode tessOcrEngineMode = new TessOcrEngineMode("OEM_TESSERACT_ONLY");
        OEM_TESSERACT_ONLY = tessOcrEngineMode;
        TessOcrEngineMode tessOcrEngineMode2 = new TessOcrEngineMode("OEM_CUBE_ONLY");
        OEM_CUBE_ONLY = tessOcrEngineMode2;
        TessOcrEngineMode tessOcrEngineMode3 = new TessOcrEngineMode("OEM_TESSERACT_CUBE_COMBINED");
        OEM_TESSERACT_CUBE_COMBINED = tessOcrEngineMode3;
        TessOcrEngineMode tessOcrEngineMode4 = new TessOcrEngineMode("OEM_DEFAULT");
        OEM_DEFAULT = tessOcrEngineMode4;
        swigValues = new TessOcrEngineMode[]{tessOcrEngineMode, tessOcrEngineMode2, tessOcrEngineMode3, tessOcrEngineMode4};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessOcrEngineMode swigToEnum(int i) {
        TessOcrEngineMode[] tessOcrEngineModeArr = swigValues;
        if (i < tessOcrEngineModeArr.length && i >= 0) {
            TessOcrEngineMode tessOcrEngineMode = tessOcrEngineModeArr[i];
            if (tessOcrEngineMode.swigValue == i) {
                return tessOcrEngineMode;
            }
        }
        int i2 = 0;
        while (true) {
            TessOcrEngineMode[] tessOcrEngineModeArr2 = swigValues;
            if (i2 < tessOcrEngineModeArr2.length) {
                TessOcrEngineMode tessOcrEngineMode2 = tessOcrEngineModeArr2[i2];
                if (tessOcrEngineMode2.swigValue == i) {
                    return tessOcrEngineMode2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessOcrEngineMode.class + " with value " + i);
            }
        }
    }

    private TessOcrEngineMode(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessOcrEngineMode(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessOcrEngineMode(String str, TessOcrEngineMode tessOcrEngineMode) {
        this.swigName = str;
        int i = tessOcrEngineMode.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
