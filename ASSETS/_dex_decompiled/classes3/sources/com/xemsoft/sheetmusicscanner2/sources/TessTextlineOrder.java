package com.xemsoft.sheetmusicscanner2.sources;

public final class TessTextlineOrder {
    public static final TessTextlineOrder TEXTLINE_ORDER_LEFT_TO_RIGHT;
    public static final TessTextlineOrder TEXTLINE_ORDER_RIGHT_TO_LEFT;
    public static final TessTextlineOrder TEXTLINE_ORDER_TOP_TO_BOTTOM;
    private static int swigNext = 0;
    private static TessTextlineOrder[] swigValues;
    private final String swigName;
    private final int swigValue;

    static {
        TessTextlineOrder tessTextlineOrder = new TessTextlineOrder("TEXTLINE_ORDER_LEFT_TO_RIGHT");
        TEXTLINE_ORDER_LEFT_TO_RIGHT = tessTextlineOrder;
        TessTextlineOrder tessTextlineOrder2 = new TessTextlineOrder("TEXTLINE_ORDER_RIGHT_TO_LEFT");
        TEXTLINE_ORDER_RIGHT_TO_LEFT = tessTextlineOrder2;
        TessTextlineOrder tessTextlineOrder3 = new TessTextlineOrder("TEXTLINE_ORDER_TOP_TO_BOTTOM");
        TEXTLINE_ORDER_TOP_TO_BOTTOM = tessTextlineOrder3;
        swigValues = new TessTextlineOrder[]{tessTextlineOrder, tessTextlineOrder2, tessTextlineOrder3};
    }

    public final int swigValue() {
        return this.swigValue;
    }

    public String toString() {
        return this.swigName;
    }

    public static TessTextlineOrder swigToEnum(int i) {
        TessTextlineOrder[] tessTextlineOrderArr = swigValues;
        if (i < tessTextlineOrderArr.length && i >= 0) {
            TessTextlineOrder tessTextlineOrder = tessTextlineOrderArr[i];
            if (tessTextlineOrder.swigValue == i) {
                return tessTextlineOrder;
            }
        }
        int i2 = 0;
        while (true) {
            TessTextlineOrder[] tessTextlineOrderArr2 = swigValues;
            if (i2 < tessTextlineOrderArr2.length) {
                TessTextlineOrder tessTextlineOrder2 = tessTextlineOrderArr2[i2];
                if (tessTextlineOrder2.swigValue == i) {
                    return tessTextlineOrder2;
                }
                i2++;
            } else {
                throw new IllegalArgumentException("No enum " + TessTextlineOrder.class + " with value " + i);
            }
        }
    }

    private TessTextlineOrder(String str) {
        this.swigName = str;
        int i = swigNext;
        swigNext = i + 1;
        this.swigValue = i;
    }

    private TessTextlineOrder(String str, int i) {
        this.swigName = str;
        this.swigValue = i;
        swigNext = i + 1;
    }

    private TessTextlineOrder(String str, TessTextlineOrder tessTextlineOrder) {
        this.swigName = str;
        int i = tessTextlineOrder.swigValue;
        this.swigValue = i;
        swigNext = i + 1;
    }
}
