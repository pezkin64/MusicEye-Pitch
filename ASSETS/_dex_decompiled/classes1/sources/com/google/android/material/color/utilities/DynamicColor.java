package com.google.android.material.color.utilities;

import androidx.core.view.ViewCompat;
import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final Function<DynamicScheme, Double> chroma;
    private final HashMap<DynamicScheme, Hct> hctCache = new HashMap<>();
    public final Function<DynamicScheme, Double> hue;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaConstraint> toneDeltaConstraint;
    public final Function<DynamicScheme, Double> toneMaxContrast;
    public final Function<DynamicScheme, Double> toneMinContrast;

    static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    static /* synthetic */ DynamicColor lambda$getTone$11(DynamicColor dynamicColor, DynamicScheme dynamicScheme) {
        return dynamicColor;
    }

    static /* synthetic */ Double lambda$toneMinContrastDefault$16(Double d) {
        return d;
    }

    public DynamicColor(Function<DynamicScheme, Double> function, Function<DynamicScheme, Double> function2, Function<DynamicScheme, Double> function3, Function<DynamicScheme, Double> function4, Function<DynamicScheme, DynamicColor> function5, Function<DynamicScheme, Double> function6, Function<DynamicScheme, Double> function7, Function<DynamicScheme, ToneDeltaConstraint> function8) {
        this.hue = function;
        this.chroma = function2;
        this.tone = function3;
        this.opacity = function4;
        this.background = function5;
        this.toneMinContrast = function6;
        this.toneMaxContrast = function7;
        this.toneDeltaConstraint = function8;
    }

    public static DynamicColor fromArgb(int i) {
        return fromPalette(new DynamicColor$$ExternalSyntheticLambda6(TonalPalette.fromInt(i)), new DynamicColor$$ExternalSyntheticLambda7(Hct.fromInt(i)));
    }

    public static DynamicColor fromArgb(int i, Function<DynamicScheme, Double> function) {
        return fromPalette(new DynamicColor$$ExternalSyntheticLambda14(i), function);
    }

    public static DynamicColor fromArgb(int i, Function<DynamicScheme, Double> function, Function<DynamicScheme, DynamicColor> function2) {
        return fromPalette(new DynamicColor$$ExternalSyntheticLambda13(i), function, function2);
    }

    public static DynamicColor fromArgb(int i, Function<DynamicScheme, Double> function, Function<DynamicScheme, DynamicColor> function2, Function<DynamicScheme, ToneDeltaConstraint> function3) {
        return fromPalette(new DynamicColor$$ExternalSyntheticLambda20(i), function, function2, function3);
    }

    public static DynamicColor fromPalette(Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2) {
        return fromPalette(function, function2, (Function<DynamicScheme, DynamicColor>) null, (Function<DynamicScheme, ToneDeltaConstraint>) null);
    }

    public static DynamicColor fromPalette(Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, Function<DynamicScheme, DynamicColor> function3) {
        return fromPalette(function, function2, function3, (Function<DynamicScheme, ToneDeltaConstraint>) null);
    }

    public static DynamicColor fromPalette(Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, ToneDeltaConstraint> function4) {
        return new DynamicColor(new DynamicColor$$ExternalSyntheticLambda2(function), new DynamicColor$$ExternalSyntheticLambda3(function), function2, (Function<DynamicScheme, Double>) null, function3, new DynamicColor$$ExternalSyntheticLambda4(function2, function3, function4), new DynamicColor$$ExternalSyntheticLambda5(function2, function3, function4), function4);
    }

    public int getArgb(DynamicScheme dynamicScheme) {
        int i = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(((Double) function.apply(dynamicScheme)).doubleValue() * 255.0d)) << 24) | (i & ViewCompat.MEASURED_SIZE_MASK);
    }

    public Hct getHct(DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct from = Hct.from(((Double) this.hue.apply(dynamicScheme)).doubleValue(), ((Double) this.chroma.apply(dynamicScheme)).doubleValue(), getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, from);
        return from;
    }

    public double getTone(DynamicScheme dynamicScheme) {
        double d;
        DynamicScheme dynamicScheme2 = dynamicScheme;
        double doubleValue = ((Double) this.tone.apply(dynamicScheme)).doubleValue();
        boolean z = true;
        boolean z2 = dynamicScheme2.contrastLevel < 0.0d;
        if (dynamicScheme2.contrastLevel != 0.0d) {
            double doubleValue2 = ((Double) this.tone.apply(dynamicScheme)).doubleValue();
            doubleValue = doubleValue2 + ((((Double) (z2 ? this.toneMinContrast.apply(dynamicScheme) : this.toneMaxContrast.apply(dynamicScheme))).doubleValue() - doubleValue2) * Math.abs(dynamicScheme2.contrastLevel));
        }
        Function<DynamicScheme, DynamicColor> function = this.background;
        DynamicColor dynamicColor = function == null ? null : (DynamicColor) function.apply(dynamicScheme);
        double d2 = 1.0d;
        if (dynamicColor != null) {
            Function<DynamicScheme, DynamicColor> function2 = dynamicColor.background;
            if (function2 == null || function2.apply(dynamicScheme) == null) {
                z = false;
            }
            double ratioOfTones = Contrast.ratioOfTones(((Double) this.tone.apply(dynamicScheme)).doubleValue(), ((Double) dynamicColor.tone.apply(dynamicScheme)).doubleValue());
            if (z2) {
                double ratioOfTones2 = Contrast.ratioOfTones(((Double) this.toneMinContrast.apply(dynamicScheme)).doubleValue(), ((Double) dynamicColor.toneMinContrast.apply(dynamicScheme)).doubleValue());
                if (z) {
                    d2 = ratioOfTones2;
                }
                d = ratioOfTones;
            } else {
                double ratioOfTones3 = Contrast.ratioOfTones(((Double) this.toneMaxContrast.apply(dynamicScheme)).doubleValue(), ((Double) dynamicColor.toneMaxContrast.apply(dynamicScheme)).doubleValue());
                if (z) {
                    d2 = Math.min(ratioOfTones3, ratioOfTones);
                }
                if (z) {
                    d = Math.max(ratioOfTones3, ratioOfTones);
                }
            }
            return calculateDynamicTone(dynamicScheme2, this.tone, new DynamicColor$$ExternalSyntheticLambda15(dynamicScheme), new DynamicColor$$ExternalSyntheticLambda16(doubleValue), new DynamicColor$$ExternalSyntheticLambda17(dynamicColor), this.toneDeltaConstraint, new DynamicColor$$ExternalSyntheticLambda18(d2), new DynamicColor$$ExternalSyntheticLambda19(d));
        }
        d = 21.0d;
        return calculateDynamicTone(dynamicScheme2, this.tone, new DynamicColor$$ExternalSyntheticLambda15(dynamicScheme), new DynamicColor$$ExternalSyntheticLambda16(doubleValue), new DynamicColor$$ExternalSyntheticLambda17(dynamicColor), this.toneDeltaConstraint, new DynamicColor$$ExternalSyntheticLambda18(d2), new DynamicColor$$ExternalSyntheticLambda19(d));
    }

    public static double toneMinContrastDefault(Function<DynamicScheme, Double> function, Function<DynamicScheme, DynamicColor> function2, DynamicScheme dynamicScheme, Function<DynamicScheme, ToneDeltaConstraint> function3) {
        return calculateDynamicTone(dynamicScheme, function, new DynamicColor$$ExternalSyntheticLambda10(dynamicScheme), new DynamicColor$$ExternalSyntheticLambda11(function, dynamicScheme, function2), function2, function3, (Function<Double, Double>) null, new DynamicColor$$ExternalSyntheticLambda12());
    }

    static /* synthetic */ Double lambda$toneMinContrastDefault$14(DynamicScheme dynamicScheme, DynamicColor dynamicColor) {
        return (Double) dynamicColor.toneMinContrast.apply(dynamicScheme);
    }

    static /* synthetic */ Double lambda$toneMinContrastDefault$15(Function function, DynamicScheme dynamicScheme, Function function2, Double d, Double d2) {
        double doubleValue = ((Double) function.apply(dynamicScheme)).doubleValue();
        if (d.doubleValue() >= 7.0d) {
            doubleValue = contrastingTone(d2.doubleValue(), 4.5d);
        } else if (d.doubleValue() >= 3.0d) {
            doubleValue = contrastingTone(d2.doubleValue(), 3.0d);
        } else if (!(function2 == null || function2.apply(dynamicScheme) == null || ((DynamicColor) function2.apply(dynamicScheme)).background == null || ((DynamicColor) function2.apply(dynamicScheme)).background.apply(dynamicScheme) == null)) {
            doubleValue = contrastingTone(d2.doubleValue(), d.doubleValue());
        }
        return Double.valueOf(doubleValue);
    }

    public static double toneMaxContrastDefault(Function<DynamicScheme, Double> function, Function<DynamicScheme, DynamicColor> function2, DynamicScheme dynamicScheme, Function<DynamicScheme, ToneDeltaConstraint> function3) {
        return calculateDynamicTone(dynamicScheme, function, new DynamicColor$$ExternalSyntheticLambda8(dynamicScheme), new DynamicColor$$ExternalSyntheticLambda9(function2, dynamicScheme), function2, function3, (Function<Double, Double>) null, (Function<Double, Double>) null);
    }

    static /* synthetic */ Double lambda$toneMaxContrastDefault$17(DynamicScheme dynamicScheme, DynamicColor dynamicColor) {
        return (Double) dynamicColor.toneMaxContrast.apply(dynamicScheme);
    }

    static /* synthetic */ Double lambda$toneMaxContrastDefault$18(Function function, DynamicScheme dynamicScheme, Double d, Double d2) {
        if (function == null || function.apply(dynamicScheme) == null || ((DynamicColor) function.apply(dynamicScheme)).background == null || ((DynamicColor) function.apply(dynamicScheme)).background.apply(dynamicScheme) == null) {
            return Double.valueOf(contrastingTone(d2.doubleValue(), Math.max(7.0d, d.doubleValue())));
        }
        return Double.valueOf(contrastingTone(d2.doubleValue(), 7.0d));
    }

    public static double calculateDynamicTone(DynamicScheme dynamicScheme, Function<DynamicScheme, Double> function, Function<DynamicColor, Double> function2, BiFunction<Double, Double, Double> biFunction, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, ToneDeltaConstraint> function4, Function<Double, Double> function5, Function<Double, Double> function6) {
        DynamicColor dynamicColor;
        DynamicScheme dynamicScheme2 = dynamicScheme;
        Function<DynamicScheme, DynamicColor> function7 = function3;
        Function<Double, Double> function8 = function5;
        Function<Double, Double> function9 = function6;
        double doubleValue = ((Double) function.apply(dynamicScheme2)).doubleValue();
        if (function7 == null) {
            dynamicColor = null;
        } else {
            dynamicColor = (DynamicColor) function7.apply(dynamicScheme2);
        }
        if (dynamicColor == null) {
            return doubleValue;
        }
        double ratioOfTones = Contrast.ratioOfTones(doubleValue, ((Double) dynamicColor.tone.apply(dynamicScheme2)).doubleValue());
        Function<DynamicColor, Double> function10 = function2;
        Double d = (Double) function10.apply(dynamicColor);
        double doubleValue2 = d.doubleValue();
        double doubleValue3 = ((Double) biFunction.apply(Double.valueOf(ratioOfTones), d)).doubleValue();
        double ratioOfTones2 = Contrast.ratioOfTones(doubleValue2, doubleValue3);
        double d2 = 1.0d;
        if (!(function8 == null || function8.apply(Double.valueOf(ratioOfTones)) == null)) {
            d2 = ((Double) function8.apply(Double.valueOf(ratioOfTones))).doubleValue();
        }
        double d3 = 21.0d;
        if (!(function9 == null || function9.apply(Double.valueOf(ratioOfTones)) == null)) {
            d3 = ((Double) function9.apply(Double.valueOf(ratioOfTones))).doubleValue();
        }
        double clampDouble = MathUtils.clampDouble(d2, d3, ratioOfTones2);
        if (clampDouble != ratioOfTones2) {
            doubleValue3 = contrastingTone(doubleValue2, clampDouble);
        }
        Function<DynamicScheme, DynamicColor> function11 = dynamicColor.background;
        if (function11 == null || function11.apply(dynamicScheme2) == null) {
            doubleValue3 = enableLightForeground(doubleValue3);
        }
        return ensureToneDelta(doubleValue3, doubleValue, dynamicScheme2, function4, function10);
    }

    static double ensureToneDelta(double d, double d2, DynamicScheme dynamicScheme, Function<DynamicScheme, ToneDeltaConstraint> function, Function<DynamicColor, Double> function2) {
        ToneDeltaConstraint toneDeltaConstraint2 = function == null ? null : (ToneDeltaConstraint) function.apply(dynamicScheme);
        if (toneDeltaConstraint2 != null) {
            double d3 = toneDeltaConstraint2.delta;
            double doubleValue = ((Double) function2.apply(toneDeltaConstraint2.keepAway)).doubleValue();
            double abs = Math.abs(d - doubleValue);
            if (abs < d3) {
                int i = AnonymousClass1.$SwitchMap$com$google$android$material$color$utilities$TonePolarity[toneDeltaConstraint2.keepAwayPolarity.ordinal()];
                boolean z = true;
                if (i == 1) {
                    return MathUtils.clampDouble(0.0d, 100.0d, doubleValue + d3);
                }
                if (i == 2) {
                    return MathUtils.clampDouble(0.0d, 100.0d, doubleValue - d3);
                }
                if (i == 3) {
                    if (d2 <= ((Double) toneDeltaConstraint2.keepAway.tone.apply(dynamicScheme)).doubleValue()) {
                        z = false;
                    }
                    double abs2 = Math.abs(abs - d3);
                    return (!z ? d >= abs2 : d + abs2 > 100.0d) ? d - abs2 : d + abs2;
                }
            }
        }
        return d;
    }

    /* renamed from: com.google.android.material.color.utilities.DynamicColor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$color$utilities$TonePolarity;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.google.android.material.color.utilities.TonePolarity[] r0 = com.google.android.material.color.utilities.TonePolarity.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$material$color$utilities$TonePolarity = r0
                com.google.android.material.color.utilities.TonePolarity r1 = com.google.android.material.color.utilities.TonePolarity.DARKER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$TonePolarity     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.material.color.utilities.TonePolarity r1 = com.google.android.material.color.utilities.TonePolarity.LIGHTER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$android$material$color$utilities$TonePolarity     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.material.color.utilities.TonePolarity r1 = com.google.android.material.color.utilities.TonePolarity.NO_PREFERENCE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.DynamicColor.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double contrastingTone(double r10, double r12) {
        /*
            double r0 = com.google.android.material.color.utilities.Contrast.lighterUnsafe(r10, r12)
            double r2 = com.google.android.material.color.utilities.Contrast.darkerUnsafe(r10, r12)
            double r4 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r0, r10)
            double r6 = com.google.android.material.color.utilities.Contrast.ratioOfTones(r2, r10)
            boolean r10 = tonePrefersLightForeground(r10)
            if (r10 == 0) goto L_0x003b
            double r10 = r4 - r6
            double r10 = java.lang.Math.abs(r10)
            r8 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            int r10 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r10 >= 0) goto L_0x002f
            int r10 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r10 >= 0) goto L_0x002f
            int r10 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r10 >= 0) goto L_0x002f
            r10 = 1
            goto L_0x0030
        L_0x002f:
            r10 = 0
        L_0x0030:
            int r11 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r11 >= 0) goto L_0x0044
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 >= 0) goto L_0x0044
            if (r10 == 0) goto L_0x0045
            goto L_0x0044
        L_0x003b:
            int r10 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r10 >= 0) goto L_0x0045
            int r10 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r10 < 0) goto L_0x0044
            goto L_0x0045
        L_0x0044:
            return r0
        L_0x0045:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.DynamicColor.contrastingTone(double, double):double");
    }

    public static double enableLightForeground(double d) {
        if (!tonePrefersLightForeground(d) || toneAllowsLightForeground(d)) {
            return d;
        }
        return 49.0d;
    }

    public static boolean tonePrefersLightForeground(double d) {
        return Math.round(d) <= 60;
    }

    public static boolean toneAllowsLightForeground(double d) {
        return Math.round(d) <= 49;
    }
}
