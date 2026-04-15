package com.google.android.material.color.utilities;

public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = defaultWithBackgroundLstar(50.0d);
    private final double aw;
    private final double c;
    private final double fl;
    private final double flRoot;
    private final double n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;
    private final double z;

    public double getAw() {
        return this.aw;
    }

    public double getN() {
        return this.n;
    }

    public double getNbb() {
        return this.nbb;
    }

    /* access modifiers changed from: package-private */
    public double getNcb() {
        return this.ncb;
    }

    /* access modifiers changed from: package-private */
    public double getC() {
        return this.c;
    }

    /* access modifiers changed from: package-private */
    public double getNc() {
        return this.nc;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    /* access modifiers changed from: package-private */
    public double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    /* access modifiers changed from: package-private */
    public double getZ() {
        return this.z;
    }

    public static ViewingConditions make(double[] dArr, double d, double d2, double d3, boolean z2) {
        double d4;
        double d5;
        double d6;
        double d7 = d;
        double max = Math.max(0.1d, d2);
        double[][] dArr2 = Cam16.XYZ_TO_CAM16RGB;
        double d8 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d9 = dArr[1];
        double d10 = dArr[2];
        double d11 = (dArr3[0] * d8) + (dArr3[1] * d9) + (dArr3[2] * d10);
        double[] dArr4 = dArr2[1];
        double d12 = (dArr4[0] * d8) + (dArr4[1] * d9) + (dArr4[2] * d10);
        double[] dArr5 = dArr2[2];
        double d13 = (d8 * dArr5[0]) + (d9 * dArr5[1]) + (d10 * dArr5[2]);
        double d14 = (d3 / 10.0d) + 0.8d;
        if (d14 >= 0.9d) {
            d4 = MathUtils.lerp(0.59d, 0.69d, (d14 - 0.9d) * 10.0d);
        } else {
            d4 = MathUtils.lerp(0.525d, 0.59d, (d14 - 0.8d) * 10.0d);
        }
        double d15 = d4;
        if (z2) {
            d6 = 0.1d;
            d5 = 1.0d;
        } else {
            d6 = 0.1d;
            d5 = (1.0d - (Math.exp(((-d7) - 42.0d) / 92.0d) * 0.2777777777777778d)) * d14;
        }
        double clampDouble = MathUtils.clampDouble(0.0d, 1.0d, d5);
        double[] dArr6 = {(((100.0d / d11) * clampDouble) + 1.0d) - clampDouble, (((100.0d / d12) * clampDouble) + 1.0d) - clampDouble, (((100.0d / d13) * clampDouble) + 1.0d) - clampDouble};
        double d16 = 5.0d * d7;
        double d17 = 1.0d / (d16 + 1.0d);
        double d18 = d17 * d17 * d17 * d17;
        double d19 = 1.0d - d18;
        double cbrt = (d18 * d7) + (d19 * d6 * d19 * Math.cbrt(d16));
        double yFromLstar = ColorUtils.yFromLstar(max) / dArr[1];
        double sqrt = Math.sqrt(yFromLstar) + 1.48d;
        double pow = 0.725d / Math.pow(yFromLstar, 0.2d);
        double[] dArr7 = {Math.pow(((dArr6[0] * cbrt) * d11) / 100.0d, 0.42d), Math.pow(((dArr6[1] * cbrt) * d12) / 100.0d, 0.42d), Math.pow(((dArr6[2] * cbrt) * d13) / 100.0d, 0.42d)};
        double d20 = dArr7[0];
        double d21 = (d20 * 400.0d) / (d20 + 27.13d);
        double d22 = dArr7[1];
        double d23 = (d22 * 400.0d) / (d22 + 27.13d);
        double d24 = dArr7[2];
        double[] dArr8 = {d21, d23, (400.0d * d24) / (d24 + 27.13d)};
        return new ViewingConditions(yFromLstar, ((dArr8[0] * 2.0d) + dArr8[1] + (dArr8[2] * 0.05d)) * pow, pow, pow, d15, d14, dArr6, cbrt, Math.pow(cbrt, 0.25d), sqrt);
    }

    public static ViewingConditions defaultWithBackgroundLstar(double d) {
        return make(ColorUtils.whitePointD65(), (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, d, 2.0d, false);
    }

    private ViewingConditions(double d, double d2, double d3, double d4, double d5, double d6, double[] dArr, double d7, double d8, double d9) {
        this.n = d;
        this.aw = d2;
        this.nbb = d3;
        this.ncb = d4;
        this.c = d5;
        this.nc = d6;
        this.rgbD = dArr;
        this.fl = d7;
        this.flRoot = d8;
        this.z = d9;
    }
}
