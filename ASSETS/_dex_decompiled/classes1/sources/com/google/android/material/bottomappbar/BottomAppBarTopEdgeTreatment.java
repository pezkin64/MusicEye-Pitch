package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.fabMargin = f;
        this.roundedCornerRadius = f2;
        setCradleVerticalOffset(f3);
        this.horizontalOffset = 0.0f;
    }

    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        float f4;
        float f5;
        float f6 = f;
        ShapePath shapePath2 = shapePath;
        float f7 = this.fabDiameter;
        if (f7 == 0.0f) {
            shapePath2.lineTo(f6, 0.0f);
            return;
        }
        float f8 = ((this.fabMargin * 2.0f) + f7) / 2.0f;
        float f9 = f3 * this.roundedCornerRadius;
        float f10 = f2 + this.horizontalOffset;
        float f11 = (this.cradleVerticalOffset * f3) + ((1.0f - f3) * f8);
        if (f11 / f8 >= 1.0f) {
            shapePath2.lineTo(f6, 0.0f);
            return;
        }
        float f12 = this.fabCornerSize;
        float f13 = f12 * f3;
        boolean z = f12 == -1.0f || Math.abs((f12 * 2.0f) - f7) < 0.1f;
        if (!z) {
            f5 = ROUNDED_CORNER_FAB_OFFSET;
            f4 = 0.0f;
        } else {
            f4 = f11;
            f5 = 0.0f;
        }
        float f14 = f8 + f9;
        float f15 = f4 + f9;
        float sqrt = (float) Math.sqrt((double) ((f14 * f14) - (f15 * f15)));
        float f16 = f10 - sqrt;
        float f17 = f10 + sqrt;
        float degrees = (float) Math.toDegrees(Math.atan((double) (sqrt / f15)));
        float f18 = (90.0f - degrees) + f5;
        shapePath2.lineTo(f16, 0.0f);
        float f19 = f16 - f9;
        float f20 = f16 + f9;
        float f21 = f9 * 2.0f;
        shapePath2.addArc(f19, 0.0f, f20, f21, 270.0f, degrees);
        float f22 = f21;
        float f23 = degrees;
        if (z) {
            shapePath.addArc(f10 - f8, (-f8) - f4, f10 + f8, f8 - f4, 180.0f - f18, (f18 * 2.0f) - 180.0f);
        } else {
            float f24 = this.fabMargin;
            float f25 = f13 * 2.0f;
            float f26 = f24 + f25;
            float f27 = f24;
            float f28 = f10 - f8;
            float f29 = f27 + f13;
            ShapePath shapePath3 = shapePath;
            shapePath3.addArc(f28, -(f13 + f27), f26 + f28, f29, 180.0f - f18, ((f18 * 2.0f) - 180.0f) / 2.0f);
            float f30 = f10 + f8;
            float f31 = this.fabMargin;
            shapePath3.lineTo(f30 - ((f31 / 2.0f) + f13), f31 + f13);
            float f32 = this.fabMargin;
            shapePath3.addArc(f30 - (f25 + f32), -(f13 + f32), f30, f32 + f13, 90.0f, f18 - 0.049804688f);
        }
        ShapePath shapePath4 = shapePath;
        shapePath4.addArc(f17 - f9, 0.0f, f17 + f9, f22, 270.0f - f23, f23);
        shapePath4.lineTo(f6, 0.0f);
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public void setFabDiameter(float f) {
        this.fabDiameter = f;
    }

    /* access modifiers changed from: package-private */
    public void setHorizontalOffset(float f) {
        this.horizontalOffset = f;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    /* access modifiers changed from: package-private */
    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    /* access modifiers changed from: package-private */
    public void setCradleVerticalOffset(float f) {
        if (f >= 0.0f) {
            this.cradleVerticalOffset = f;
            return;
        }
        throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleMargin(float f) {
        this.fabMargin = f;
    }

    /* access modifiers changed from: package-private */
    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    /* access modifiers changed from: package-private */
    public void setFabCradleRoundedCornerRadius(float f) {
        this.roundedCornerRadius = f;
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public void setFabCornerSize(float f) {
        this.fabCornerSize = f;
    }
}
