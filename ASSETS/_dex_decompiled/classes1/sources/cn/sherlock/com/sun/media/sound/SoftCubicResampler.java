package cn.sherlock.com.sun.media.sound;

public class SoftCubicResampler extends SoftAbstractResampler {
    public int getPadding() {
        return 3;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        int i2 = i;
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i3 = iArr[0];
        if (f2 == 0.0f) {
            while (f4 < f && i3 < i2) {
                int i4 = (int) f4;
                float f5 = f4 - ((float) i4);
                float f6 = fArr[i4 - 1];
                float f7 = fArr[i4];
                float f8 = fArr[i4 + 1];
                float f9 = ((fArr[i4 + 2] - f8) + f7) - f6;
                float f10 = f8 - f6;
                fArr4[i3] = (((((f9 * f5) + ((f6 - f7) - f9)) * f5) + f10) * f5) + f7;
                f4 += f3;
                i3++;
            }
        } else {
            while (f4 < f && i3 < i2) {
                int i5 = (int) f4;
                float f11 = f4 - ((float) i5);
                float f12 = fArr[i5 - 1];
                float f13 = fArr[i5];
                float f14 = fArr[i5 + 1];
                float f15 = ((fArr[i5 + 2] - f14) + f13) - f12;
                float f16 = f14 - f12;
                fArr4[i3] = (((((f15 * f11) + ((f12 - f13) - f15)) * f11) + f16) * f11) + f13;
                f4 += f3;
                f3 += f2;
                i3++;
            }
        }
        fArr2[0] = f4;
        iArr[0] = i3;
        fArr3[0] = f3;
    }
}
