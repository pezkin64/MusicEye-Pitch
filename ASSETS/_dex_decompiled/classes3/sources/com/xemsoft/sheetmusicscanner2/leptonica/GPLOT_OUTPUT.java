package com.xemsoft.sheetmusicscanner2.leptonica;

public final class GPLOT_OUTPUT {
    public static final GPLOT_OUTPUT GPLOT_EPS;
    public static final GPLOT_OUTPUT GPLOT_LATEX;
    public static final GPLOT_OUTPUT GPLOT_NONE;
    public static final GPLOT_OUTPUT GPLOT_PNG;
    public static final GPLOT_OUTPUT GPLOT_PS;
    public static final GPLOT_OUTPUT GPLOT_X11;
    private static int swigNext = 0;
    private static GPLOT_OUTPUT[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        GPLOT_OUTPUT gplot_output = new GPLOT_OUTPUT("GPLOT_NONE", JniLeptonicaJNI.GPLOT_NONE_get());
        GPLOT_NONE = gplot_output;
        GPLOT_OUTPUT gplot_output2 = new GPLOT_OUTPUT("GPLOT_PNG", JniLeptonicaJNI.GPLOT_PNG_get());
        GPLOT_PNG = gplot_output2;
        GPLOT_OUTPUT gplot_output3 = new GPLOT_OUTPUT("GPLOT_PS", JniLeptonicaJNI.GPLOT_PS_get());
        GPLOT_PS = gplot_output3;
        GPLOT_OUTPUT gplot_output4 = new GPLOT_OUTPUT("GPLOT_EPS", JniLeptonicaJNI.GPLOT_EPS_get());
        GPLOT_EPS = gplot_output4;
        GPLOT_OUTPUT gplot_output5 = new GPLOT_OUTPUT("GPLOT_X11", JniLeptonicaJNI.GPLOT_X11_get());
        GPLOT_X11 = gplot_output5;
        GPLOT_OUTPUT gplot_output6 = new GPLOT_OUTPUT("GPLOT_LATEX", JniLeptonicaJNI.GPLOT_LATEX_get());
        GPLOT_LATEX = gplot_output6;
        swigValues = new GPLOT_OUTPUT[]{gplot_output, gplot_output2, gplot_output3, gplot_output4, gplot_output5, gplot_output6};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static GPLOT_OUTPUT swigToEnum(int i) {
        GPLOT_OUTPUT[] gplot_outputArr = swigValues;
        if (i < gplot_outputArr.length && i >= 0) {
            GPLOT_OUTPUT gplot_output = gplot_outputArr[i];
            if (gplot_output.swigValue == i) {
                return gplot_output;
            }
        }
        int i2 = 0;
        while (true) {
            GPLOT_OUTPUT[] gplot_outputArr2 = swigValues;
            if (i2 < gplot_outputArr2.length) {
                GPLOT_OUTPUT gplot_output2 = gplot_outputArr2[i2];
                if (gplot_output2.swigValue == i) {
                    return gplot_output2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + GPLOT_OUTPUT.class + " with value " + i);
            }
        }
    }

    private GPLOT_OUTPUT(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private GPLOT_OUTPUT(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private GPLOT_OUTPUT(String str, GPLOT_OUTPUT gplot_output) {
        this.swigName = str;
        int i = gplot_output.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
