package com.xemsoft.sheetmusicscanner2.leptonica;

public final class GPLOT_SCALING {
    public static final GPLOT_SCALING GPLOT_LINEAR_SCALE;
    public static final GPLOT_SCALING GPLOT_LOG_SCALE_X;
    public static final GPLOT_SCALING GPLOT_LOG_SCALE_X_Y;
    public static final GPLOT_SCALING GPLOT_LOG_SCALE_Y;
    private static int swigNext = 0;
    private static GPLOT_SCALING[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        GPLOT_SCALING gplot_scaling = new GPLOT_SCALING("GPLOT_LINEAR_SCALE", JniLeptonicaJNI.GPLOT_LINEAR_SCALE_get());
        GPLOT_LINEAR_SCALE = gplot_scaling;
        GPLOT_SCALING gplot_scaling2 = new GPLOT_SCALING("GPLOT_LOG_SCALE_X", JniLeptonicaJNI.GPLOT_LOG_SCALE_X_get());
        GPLOT_LOG_SCALE_X = gplot_scaling2;
        GPLOT_SCALING gplot_scaling3 = new GPLOT_SCALING("GPLOT_LOG_SCALE_Y", JniLeptonicaJNI.GPLOT_LOG_SCALE_Y_get());
        GPLOT_LOG_SCALE_Y = gplot_scaling3;
        GPLOT_SCALING gplot_scaling4 = new GPLOT_SCALING("GPLOT_LOG_SCALE_X_Y", JniLeptonicaJNI.GPLOT_LOG_SCALE_X_Y_get());
        GPLOT_LOG_SCALE_X_Y = gplot_scaling4;
        swigValues = new GPLOT_SCALING[]{gplot_scaling, gplot_scaling2, gplot_scaling3, gplot_scaling4};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static GPLOT_SCALING swigToEnum(int i) {
        GPLOT_SCALING[] gplot_scalingArr = swigValues;
        if (i < gplot_scalingArr.length && i >= 0) {
            GPLOT_SCALING gplot_scaling = gplot_scalingArr[i];
            if (gplot_scaling.swigValue == i) {
                return gplot_scaling;
            }
        }
        int i2 = 0;
        while (true) {
            GPLOT_SCALING[] gplot_scalingArr2 = swigValues;
            if (i2 < gplot_scalingArr2.length) {
                GPLOT_SCALING gplot_scaling2 = gplot_scalingArr2[i2];
                if (gplot_scaling2.swigValue == i) {
                    return gplot_scaling2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + GPLOT_SCALING.class + " with value " + i);
            }
        }
    }

    private GPLOT_SCALING(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private GPLOT_SCALING(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private GPLOT_SCALING(String str, GPLOT_SCALING gplot_scaling) {
        this.swigName = str;
        int i = gplot_scaling.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
