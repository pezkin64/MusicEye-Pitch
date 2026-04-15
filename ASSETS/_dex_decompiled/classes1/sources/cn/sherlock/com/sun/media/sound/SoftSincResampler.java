package cn.sherlock.com.sun.media.sound;

import java.lang.reflect.Array;

public class SoftSincResampler extends SoftAbstractResampler {
    int sinc_scale_size = 100;
    float[][][] sinc_table;
    int sinc_table_center = (30 / 2);
    int sinc_table_fsize = 800;
    int sinc_table_size = 30;

    public SoftSincResampler() {
        int[] iArr = new int[2];
        iArr[1] = 800;
        iArr[0] = 100;
        this.sinc_table = (float[][][]) Array.newInstance(float[].class, iArr);
        for (int i = 0; i < this.sinc_scale_size; i++) {
            float pow = (float) (1.0d / ((Math.pow((double) i, 1.1d) / 10.0d) + 1.0d));
            int i2 = 0;
            while (true) {
                int i3 = this.sinc_table_fsize;
                if (i2 >= i3) {
                    break;
                }
                this.sinc_table[i][i2] = sincTable(this.sinc_table_size, ((float) (-i2)) / ((float) i3), pow);
                i2++;
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

    public static float[] wHanning(int i, float f) {
        float[] fArr = new float[i];
        for (int i2 = 0; i2 < i; i2++) {
            fArr[i2] = (float) ((Math.cos((((double) (((float) i2) + f)) * 6.283185307179586d) / ((double) i)) * -0.5d) + 0.5d);
        }
        return fArr;
    }

    public static float[] sincTable(int i, float f, float f2) {
        int i2 = i / 2;
        float[] wHanning = wHanning(i, f);
        for (int i3 = 0; i3 < i; i3++) {
            wHanning[i3] = (float) (((double) wHanning[i3]) * sinc((double) ((((float) ((-i2) + i3)) + f) * f2)) * ((double) f2));
        }
        return wHanning;
    }

    public int getPadding() {
        return (this.sinc_table_size / 2) + 2;
    }

    public void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i) {
        int i2 = i;
        float f3 = fArr3[0];
        float f4 = fArr2[0];
        int i3 = iArr[0];
        int i4 = this.sinc_scale_size - 1;
        if (f2 == 0.0f) {
            int i5 = (int) ((f3 - 1.0f) * 10.0f);
            if (i5 < 0) {
                i4 = 0;
            } else if (i5 <= i4) {
                i4 = i5;
            }
            float[][] fArr5 = this.sinc_table[i4];
            while (f4 < f && i3 < i2) {
                int i6 = (int) f4;
                float[] fArr6 = fArr5[(int) ((f4 - ((float) i6)) * ((float) this.sinc_table_fsize))];
                int i7 = i6 - this.sinc_table_center;
                int i8 = 0;
                float f5 = 0.0f;
                while (i8 < this.sinc_table_size) {
                    f5 += fArr[i7] * fArr6[i8];
                    i8++;
                    i7++;
                }
                fArr4[i3] = f5;
                f4 += f3;
                i3++;
            }
        } else {
            while (f4 < f && i3 < i2) {
                int i9 = (int) f4;
                int i10 = (int) ((f3 - 1.0f) * 10.0f);
                if (i10 < 0) {
                    i10 = 0;
                } else if (i10 > i4) {
                    i10 = i4;
                }
                float[] fArr7 = this.sinc_table[i10][(int) ((f4 - ((float) i9)) * ((float) this.sinc_table_fsize))];
                int i11 = i9 - this.sinc_table_center;
                int i12 = 0;
                float f6 = 0.0f;
                while (i12 < this.sinc_table_size) {
                    f6 += fArr[i11] * fArr7[i12];
                    i12++;
                    i11++;
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
