package com.xemsoft.sheetmusicscanner2.leptonica;

public final class GPLOT_STYLE {
    public static final GPLOT_STYLE GPLOT_DOTS;
    public static final GPLOT_STYLE GPLOT_IMPULSES;
    public static final GPLOT_STYLE GPLOT_LINES;
    public static final GPLOT_STYLE GPLOT_LINESPOINTS;
    public static final GPLOT_STYLE GPLOT_POINTS;
    private static int swigNext = 0;
    private static GPLOT_STYLE[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        GPLOT_STYLE gplot_style = new GPLOT_STYLE("GPLOT_LINES", JniLeptonicaJNI.GPLOT_LINES_get());
        GPLOT_LINES = gplot_style;
        GPLOT_STYLE gplot_style2 = new GPLOT_STYLE("GPLOT_POINTS", JniLeptonicaJNI.GPLOT_POINTS_get());
        GPLOT_POINTS = gplot_style2;
        GPLOT_STYLE gplot_style3 = new GPLOT_STYLE("GPLOT_IMPULSES", JniLeptonicaJNI.GPLOT_IMPULSES_get());
        GPLOT_IMPULSES = gplot_style3;
        GPLOT_STYLE gplot_style4 = new GPLOT_STYLE("GPLOT_LINESPOINTS", JniLeptonicaJNI.GPLOT_LINESPOINTS_get());
        GPLOT_LINESPOINTS = gplot_style4;
        GPLOT_STYLE gplot_style5 = new GPLOT_STYLE("GPLOT_DOTS", JniLeptonicaJNI.GPLOT_DOTS_get());
        GPLOT_DOTS = gplot_style5;
        swigValues = new GPLOT_STYLE[]{gplot_style, gplot_style2, gplot_style3, gplot_style4, gplot_style5};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static GPLOT_STYLE swigToEnum(int i) {
        GPLOT_STYLE[] gplot_styleArr = swigValues;
        if (i < gplot_styleArr.length && i >= 0) {
            GPLOT_STYLE gplot_style = gplot_styleArr[i];
            if (gplot_style.swigValue == i) {
                return gplot_style;
            }
        }
        int i2 = 0;
        while (true) {
            GPLOT_STYLE[] gplot_styleArr2 = swigValues;
            if (i2 < gplot_styleArr2.length) {
                GPLOT_STYLE gplot_style2 = gplot_styleArr2[i2];
                if (gplot_style2.swigValue == i) {
                    return gplot_style2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + GPLOT_STYLE.class + " with value " + i);
            }
        }
    }

    private GPLOT_STYLE(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private GPLOT_STYLE(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private GPLOT_STYLE(String str, GPLOT_STYLE gplot_style) {
        this.swigName = str;
        int i = gplot_style.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
