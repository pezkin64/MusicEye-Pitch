package cn.sherlock.com.sun.media.sound;

public class SoftLinearResampler extends SoftAbstractResampler {
    public int getPadding() {
        return 2;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        int i2 = i;
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i3 = iArr[0];
        if (f2 == 0.0f) {
            while (f4 < f && i3 < i2) {
                int i4 = (int) f4;
                float f5 = fArr[i4];
                fArr4[i3] = f5 + ((fArr[i4 + 1] - f5) * (f4 - ((float) i4)));
                f4 += f3;
                i3++;
            }
        } else {
            while (f4 < f && i3 < i2) {
                int i5 = (int) f4;
                float f6 = fArr[i5];
                fArr4[i3] = f6 + ((fArr[i5 + 1] - f6) * (f4 - ((float) i5)));
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
