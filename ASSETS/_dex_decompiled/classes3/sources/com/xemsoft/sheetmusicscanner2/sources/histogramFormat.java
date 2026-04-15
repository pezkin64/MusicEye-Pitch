package com.xemsoft.sheetmusicscanner2.sources;

public final class histogramFormat {
    public static final histogramFormat kByColumn;
    public static final histogramFormat kByRow;
    private static int swigNext = 0;
    private static histogramFormat[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        histogramFormat histogramformat = new histogramFormat("kByRow");
        kByRow = histogramformat;
        histogramFormat histogramformat2 = new histogramFormat("kByColumn");
        kByColumn = histogramformat2;
        swigValues = new histogramFormat[]{histogramformat, histogramformat2};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static histogramFormat swigToEnum(int i) {
        histogramFormat[] histogramformatArr = swigValues;
        if (i < histogramformatArr.length && i >= 0) {
            histogramFormat histogramformat = histogramformatArr[i];
            if (histogramformat.swigValue == i) {
                return histogramformat;
            }
        }
        int i2 = 0;
        while (true) {
            histogramFormat[] histogramformatArr2 = swigValues;
            if (i2 < histogramformatArr2.length) {
                histogramFormat histogramformat2 = histogramformatArr2[i2];
                if (histogramformat2.swigValue == i) {
                    return histogramformat2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + histogramFormat.class + " with value " + i);
            }
        }
    }

    private histogramFormat(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private histogramFormat(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private histogramFormat(String str, histogramFormat histogramformat) {
        this.swigName = str;
        int i = histogramformat.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
