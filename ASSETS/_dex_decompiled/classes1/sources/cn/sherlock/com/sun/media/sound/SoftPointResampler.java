package cn.sherlock.com.sun.media.sound;

public class SoftPointResampler extends SoftAbstractResampler {
    public int getPadding() {
        return 100;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i2 = iArr[0];
        float f5 = (float) i;
        if (f2 == 0.0f) {
            while (f4 < f && ((float) i2) < f5) {
                fArr4[i2] = fArr[(int) f4];
                f4 += f3;
                i2++;
            }
        } else {
            while (f4 < f && ((float) i2) < f5) {
                fArr4[i2] = fArr[(int) f4];
                f4 += f3;
                f3 += f2;
                i2++;
            }
        }
        fArr2[0] = f4;
        iArr[0] = i2;
        fArr3[0] = f3;
    }
}
