package com.google.android.material.transition.platform;

import android.graphics.RectF;

class FitModeEvaluators {
    private static final FitModeEvaluator HEIGHT = new FitModeEvaluator() {
        public FitModeResult evaluate(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float f8 = f5;
            float f9 = f7;
            float lerp = TransitionUtils.lerp(f8, f9, f2, f3, f, true);
            float f10 = lerp / f8;
            float f11 = lerp / f9;
            return new FitModeResult(f10, f11, f4 * f10, lerp, f6 * f11, lerp);
        }

        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartWidth > fitModeResult.currentEndWidth;
        }

        public void applyMask(RectF rectF, float f, FitModeResult fitModeResult) {
            float abs = (Math.abs(fitModeResult.currentEndWidth - fitModeResult.currentStartWidth) / 2.0f) * f;
            rectF.left += abs;
            rectF.right -= abs;
        }
    };
    private static final FitModeEvaluator WIDTH = new FitModeEvaluator() {
        public FitModeResult evaluate(float f, float f2, float f3, float f4, float f5, float f6, float f7) {
            float f8 = f4;
            float f9 = f6;
            float lerp = TransitionUtils.lerp(f8, f9, f2, f3, f, true);
            float f10 = lerp / f8;
            float f11 = lerp / f9;
            return new FitModeResult(f10, f11, lerp, f5 * f10, lerp, f7 * f11);
        }

        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartHeight > fitModeResult.currentEndHeight;
        }

        public void applyMask(RectF rectF, float f, FitModeResult fitModeResult) {
            rectF.bottom -= Math.abs(fitModeResult.currentEndHeight - fitModeResult.currentStartHeight) * f;
        }
    };

    static FitModeEvaluator get(int i, boolean z, RectF rectF, RectF rectF2) {
        if (i == 0) {
            return shouldAutoFitToWidth(z, rectF, rectF2) ? WIDTH : HEIGHT;
        }
        if (i == 1) {
            return WIDTH;
        }
        if (i == 2) {
            return HEIGHT;
        }
        throw new IllegalArgumentException("Invalid fit mode: " + i);
    }

    private static boolean shouldAutoFitToWidth(boolean z, RectF rectF, RectF rectF2) {
        float width = rectF.width();
        float height = rectF.height();
        float width2 = rectF2.width();
        float height2 = rectF2.height();
        return z ? (height2 * width) / width2 >= height : (width2 * height) / width >= height2;
    }

    private FitModeEvaluators() {
    }
}
