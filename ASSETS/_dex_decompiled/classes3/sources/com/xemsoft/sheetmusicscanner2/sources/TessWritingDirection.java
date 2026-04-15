package com.xemsoft.sheetmusicscanner2.sources;

public final class TessWritingDirection {
    public static final TessWritingDirection WRITING_DIRECTION_LEFT_TO_RIGHT;
    public static final TessWritingDirection WRITING_DIRECTION_RIGHT_TO_LEFT;
    public static final TessWritingDirection WRITING_DIRECTION_TOP_TO_BOTTOM;
    private static int swigNext = 0;
    private static TessWritingDirection[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessWritingDirection tessWritingDirection = new TessWritingDirection("WRITING_DIRECTION_LEFT_TO_RIGHT");
        WRITING_DIRECTION_LEFT_TO_RIGHT = tessWritingDirection;
        TessWritingDirection tessWritingDirection2 = new TessWritingDirection("WRITING_DIRECTION_RIGHT_TO_LEFT");
        WRITING_DIRECTION_RIGHT_TO_LEFT = tessWritingDirection2;
        TessWritingDirection tessWritingDirection3 = new TessWritingDirection("WRITING_DIRECTION_TOP_TO_BOTTOM");
        WRITING_DIRECTION_TOP_TO_BOTTOM = tessWritingDirection3;
        swigValues = new TessWritingDirection[]{tessWritingDirection, tessWritingDirection2, tessWritingDirection3};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessWritingDirection swigToEnum(int i) {
        TessWritingDirection[] tessWritingDirectionArr = swigValues;
        if (i < tessWritingDirectionArr.length && i >= 0) {
            TessWritingDirection tessWritingDirection = tessWritingDirectionArr[i];
            if (tessWritingDirection.swigValue == i) {
                return tessWritingDirection;
            }
        }
        int i2 = 0;
        while (true) {
            TessWritingDirection[] tessWritingDirectionArr2 = swigValues;
            if (i2 < tessWritingDirectionArr2.length) {
                TessWritingDirection tessWritingDirection2 = tessWritingDirectionArr2[i2];
                if (tessWritingDirection2.swigValue == i) {
                    return tessWritingDirection2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessWritingDirection.class + " with value " + i);
            }
        }
    }

    private TessWritingDirection(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessWritingDirection(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessWritingDirection(String str, TessWritingDirection tessWritingDirection) {
        this.swigName = str;
        int i = tessWritingDirection.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
