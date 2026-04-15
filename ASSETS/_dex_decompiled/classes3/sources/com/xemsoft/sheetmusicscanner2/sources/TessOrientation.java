package com.xemsoft.sheetmusicscanner2.sources;

public final class TessOrientation {
    public static final TessOrientation ORIENTATION_PAGE_DOWN;
    public static final TessOrientation ORIENTATION_PAGE_LEFT;
    public static final TessOrientation ORIENTATION_PAGE_RIGHT;
    public static final TessOrientation ORIENTATION_PAGE_UP;
    private static int swigNext = 0;
    private static TessOrientation[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessOrientation tessOrientation = new TessOrientation("ORIENTATION_PAGE_UP");
        ORIENTATION_PAGE_UP = tessOrientation;
        TessOrientation tessOrientation2 = new TessOrientation("ORIENTATION_PAGE_RIGHT");
        ORIENTATION_PAGE_RIGHT = tessOrientation2;
        TessOrientation tessOrientation3 = new TessOrientation("ORIENTATION_PAGE_DOWN");
        ORIENTATION_PAGE_DOWN = tessOrientation3;
        TessOrientation tessOrientation4 = new TessOrientation("ORIENTATION_PAGE_LEFT");
        ORIENTATION_PAGE_LEFT = tessOrientation4;
        swigValues = new TessOrientation[]{tessOrientation, tessOrientation2, tessOrientation3, tessOrientation4};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessOrientation swigToEnum(int i) {
        TessOrientation[] tessOrientationArr = swigValues;
        if (i < tessOrientationArr.length && i >= 0) {
            TessOrientation tessOrientation = tessOrientationArr[i];
            if (tessOrientation.swigValue == i) {
                return tessOrientation;
            }
        }
        int i2 = 0;
        while (true) {
            TessOrientation[] tessOrientationArr2 = swigValues;
            if (i2 < tessOrientationArr2.length) {
                TessOrientation tessOrientation2 = tessOrientationArr2[i2];
                if (tessOrientation2.swigValue == i) {
                    return tessOrientation2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessOrientation.class + " with value " + i);
            }
        }
    }

    private TessOrientation(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessOrientation(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessOrientation(String str, TessOrientation tessOrientation) {
        this.swigName = str;
        int i = tessOrientation.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
