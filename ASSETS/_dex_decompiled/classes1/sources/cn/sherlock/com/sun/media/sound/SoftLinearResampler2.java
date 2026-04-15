package cn.sherlock.com.sun.media.sound;

public class SoftLinearResampler2 extends SoftAbstractResampler {
    public int getPadding() {
        return 2;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        int i2 = i;
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i3 = iArr[0];
        if (f4 < f && i3 < i2) {
            int i4 = (int) (f4 * 32768.0f);
            int i5 = (int) (f * 32768.0f);
            int i6 = (int) (f3 * 32768.0f);
            float f5 = ((float) i6) * 3.0517578E-5f;
            if (f2 == 0.0f) {
                int i7 = i5 - i4;
                int i8 = i7 % i6;
                if (i8 != 0) {
                    i7 += i6 - i8;
                }
                int i9 = (i7 / i6) + i3;
                if (i9 >= i2) {
                    i9 = i2;
                }
                while (i3 < i9) {
                    int i10 = i4 >> 15;
                    float f6 = fArr[i10];
                    fArr4[i3] = f6 + ((fArr[i10 + 1] - f6) * (f4 - ((float) i10)));
                    i4 += i6;
                    f4 += f5;
                    i3++;
                }
            } else {
                int i11 = (int) (32768.0f * f2);
                float f7 = ((float) i11) * 3.0517578E-5f;
                while (i4 < i5 && i3 < i2) {
                    int i12 = i4 >> 15;
                    float f8 = fArr[i12];
                    fArr4[i3] = f8 + ((fArr[i12 + 1] - f8) * (f4 - ((float) i12)));
                    f4 += f5;
                    i4 += i6;
                    f5 += f7;
                    i6 += i11;
                    i3++;
                }
            }
            fArr2[0] = f4;
            iArr[0] = i3;
            fArr3[0] = f5;
        }
    }
}
