package cn.sherlock.com.sun.media.sound;

public class SoftLanczosResampler extends SoftAbstractResampler {
    float[][] sinc_table = new float[2000][];
    int sinc_table_center = (5 / 2);
    int sinc_table_fsize = 2000;
    int sinc_table_size = 5;

    public SoftLanczosResampler() {
        int i = 0;
        while (true) {
            int i2 = this.sinc_table_fsize;
            if (i < i2) {
                this.sinc_table[i] = sincTable(this.sinc_table_size, ((float) (-i)) / ((float) i2));
                i++;
            } else {
                return;
            }
        }
    }

    public static double sinc(double d) {
        if (d == 0.0d) {
            return 1.0d;
        }
        double d2 = d * 3.141592653589793d;
        return Math.sin(d2) / d2;
    }

    public static float[] sincTable(int i, float f) {
        int i2 = i / 2;
        float[] fArr = new float[i];
        for (int i3 = 0; i3 < i; i3++) {
            float f2 = ((float) ((-i2) + i3)) + f;
            if (f2 < -2.0f || f2 > 2.0f) {
                fArr[i3] = 0.0f;
            } else if (f2 == 0.0f) {
                fArr[i3] = 1.0f;
            } else {
                double d = ((double) f2) * 3.141592653589793d;
                fArr[i3] = (float) (((Math.sin(d) * 2.0d) * Math.sin(d / 2.0d)) / (d * d));
            }
        }
        return fArr;
    }

    public int getPadding() {
        return (this.sinc_table_size / 2) + 2;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        int i2 = i;
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i3 = iArr[0];
        if (f2 == 0.0f) {
            while (f4 < f && i3 < i2) {
                int i4 = (int) f4;
                float[] fArr5 = this.sinc_table[(int) ((f4 - ((float) i4)) * ((float) this.sinc_table_fsize))];
                int i5 = i4 - this.sinc_table_center;
                int i6 = 0;
                float f5 = 0.0f;
                while (i6 < this.sinc_table_size) {
                    f5 += fArr[i5] * fArr5[i6];
                    i6++;
                    i5++;
                }
                fArr4[i3] = f5;
                f4 += f3;
                i3++;
            }
        } else {
            while (f4 < f && i3 < i2) {
                int i7 = (int) f4;
                float[] fArr6 = this.sinc_table[(int) ((f4 - ((float) i7)) * ((float) this.sinc_table_fsize))];
                int i8 = i7 - this.sinc_table_center;
                int i9 = 0;
                float f6 = 0.0f;
                while (i9 < this.sinc_table_size) {
                    f6 += fArr[i8] * fArr6[i9];
                    i9++;
                    i8++;
                }
                fArr4[i3] = f6;
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
