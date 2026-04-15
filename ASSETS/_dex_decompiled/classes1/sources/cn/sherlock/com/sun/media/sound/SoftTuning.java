package cn.sherlock.com.sun.media.sound;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import jp.kshoji.javax.sound.midi.Patch;

public class SoftTuning {
    private String name;
    private Patch patch;
    private double[] tuning;

    public SoftTuning() {
        this.tuning = new double[128];
        this.patch = null;
        this.name = "12-TET";
        int i = 0;
        while (true) {
            double[] dArr = this.tuning;
            if (i < dArr.length) {
                dArr[i] = (double) (i * 100);
                i++;
            } else {
                return;
            }
        }
    }

    public SoftTuning(byte[] bArr) {
        this.name = null;
        this.tuning = new double[128];
        this.patch = null;
        int i = 0;
        while (true) {
            double[] dArr = this.tuning;
            if (i < dArr.length) {
                dArr[i] = (double) (i * 100);
                i++;
            } else {
                load(bArr);
                return;
            }
        }
    }

    public SoftTuning(Patch patch2) {
        this.tuning = new double[128];
        this.patch = patch2;
        this.name = "12-TET";
        int i = 0;
        while (true) {
            double[] dArr = this.tuning;
            if (i < dArr.length) {
                dArr[i] = (double) (i * 100);
                i++;
            } else {
                return;
            }
        }
    }

    public SoftTuning(Patch patch2, byte[] bArr) {
        this.name = null;
        this.tuning = new double[128];
        this.patch = patch2;
        int i = 0;
        while (true) {
            double[] dArr = this.tuning;
            if (i < dArr.length) {
                dArr[i] = (double) (i * 100);
                i++;
            } else {
                load(bArr);
                return;
            }
        }
    }

    private boolean checksumOK(byte[] bArr) {
        byte b = bArr[1] & 255;
        for (int i = 2; i < bArr.length - 2; i++) {
            b ^= bArr[i] & 255;
        }
        if ((bArr[bArr.length - 2] & 255) == (b & Ascii.DEL)) {
            return true;
        }
        return false;
    }

    public void load(byte[] bArr) {
        byte[] bArr2 = bArr;
        byte b = bArr2[1];
        if ((b & 255) == 126 || (b & 255) == Byte.MAX_VALUE) {
            int i = 8;
            if ((bArr2[3] & 255) == 8) {
                int i2 = 7;
                switch (bArr2[4] & 255) {
                    case 1:
                        try {
                            this.name = new String(bArr2, 6, 16, "ascii");
                        } catch (UnsupportedEncodingException unused) {
                            this.name = null;
                        }
                        int i3 = 22;
                        for (int i4 = 0; i4 < 128; i4++) {
                            byte b2 = bArr2[i3] & 255;
                            int i5 = i3 + 2;
                            byte b3 = bArr2[i3 + 1] & 255;
                            i3 += 3;
                            byte b4 = bArr2[i5] & 255;
                            if (b2 != Byte.MAX_VALUE || b3 != Byte.MAX_VALUE || b4 != Byte.MAX_VALUE) {
                                this.tuning[i4] = (((double) (((b2 * Ascii.NUL) + (b3 * 128)) + b4)) / 16384.0d) * 100.0d;
                            }
                        }
                        return;
                    case 2:
                        byte b5 = bArr2[6] & 255;
                        for (int i6 = 0; i6 < b5; i6++) {
                            byte b6 = bArr2[i2] & 255;
                            byte b7 = bArr2[i2 + 1] & 255;
                            int i7 = i2 + 3;
                            byte b8 = bArr2[i2 + 2] & 255;
                            i2 += 4;
                            byte b9 = bArr2[i7] & 255;
                            if (b7 != Byte.MAX_VALUE || b8 != Byte.MAX_VALUE || b9 != Byte.MAX_VALUE) {
                                this.tuning[b6] = (((double) (((b7 * Ascii.NUL) + (b8 * 128)) + b9)) / 16384.0d) * 100.0d;
                            }
                        }
                        return;
                    case 4:
                        if (checksumOK(bArr)) {
                            try {
                                this.name = new String(bArr2, 7, 16, "ascii");
                            } catch (UnsupportedEncodingException unused2) {
                                this.name = null;
                            }
                            int i8 = 23;
                            for (int i9 = 0; i9 < 128; i9++) {
                                byte b10 = bArr2[i8] & 255;
                                int i10 = i8 + 2;
                                byte b11 = bArr2[i8 + 1] & 255;
                                i8 += 3;
                                byte b12 = bArr2[i10] & 255;
                                if (b10 != Byte.MAX_VALUE || b11 != Byte.MAX_VALUE || b12 != Byte.MAX_VALUE) {
                                    this.tuning[i9] = (((double) (((b10 * Ascii.NUL) + (b11 * 128)) + b12)) / 16384.0d) * 100.0d;
                                }
                            }
                            return;
                        }
                        return;
                    case 5:
                        if (checksumOK(bArr)) {
                            try {
                                this.name = new String(bArr2, 7, 16, "ascii");
                            } catch (UnsupportedEncodingException unused3) {
                                this.name = null;
                            }
                            int[] iArr = new int[12];
                            for (int i11 = 0; i11 < 12; i11++) {
                                iArr[i11] = (bArr2[i11 + 23] & 255) - 64;
                            }
                            int i12 = 0;
                            while (true) {
                                double[] dArr = this.tuning;
                                if (i12 < dArr.length) {
                                    dArr[i12] = (double) ((i12 * 100) + iArr[i12 % 12]);
                                    i12++;
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    case 6:
                        if (checksumOK(bArr)) {
                            try {
                                this.name = new String(bArr2, 7, 16, "ascii");
                            } catch (UnsupportedEncodingException unused4) {
                                this.name = null;
                            }
                            double[] dArr2 = new double[12];
                            for (int i13 = 0; i13 < 12; i13++) {
                                int i14 = i13 * 2;
                                dArr2[i13] = ((((double) (((bArr2[i14 + 23] & 255) * 128) + (bArr2[i14 + 24] & 255))) / 8192.0d) - 1.0d) * 100.0d;
                            }
                            int i15 = 0;
                            while (true) {
                                double[] dArr3 = this.tuning;
                                if (i15 < dArr3.length) {
                                    dArr3[i15] = ((double) (i15 * 100)) + dArr2[i15 % 12];
                                    i15++;
                                } else {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    case 7:
                        byte b13 = bArr2[7] & 255;
                        for (int i16 = 0; i16 < b13; i16++) {
                            byte b14 = bArr2[i] & 255;
                            byte b15 = bArr2[i + 1] & 255;
                            int i17 = i + 3;
                            byte b16 = bArr2[i + 2] & 255;
                            i += 4;
                            byte b17 = bArr2[i17] & 255;
                            if (b15 != Byte.MAX_VALUE || b16 != Byte.MAX_VALUE || b17 != Byte.MAX_VALUE) {
                                this.tuning[b14] = (((double) (((b15 * Ascii.NUL) + (b16 * 128)) + b17)) / 16384.0d) * 100.0d;
                            }
                        }
                        return;
                    case 8:
                        int[] iArr2 = new int[12];
                        for (int i18 = 0; i18 < 12; i18++) {
                            iArr2[i18] = (bArr2[i18 + 8] & 255) - 64;
                        }
                        int i19 = 0;
                        while (true) {
                            double[] dArr4 = this.tuning;
                            if (i19 < dArr4.length) {
                                dArr4[i19] = (double) ((i19 * 100) + iArr2[i19 % 12]);
                                i19++;
                            } else {
                                return;
                            }
                        }
                    case 9:
                        double[] dArr5 = new double[12];
                        for (int i20 = 0; i20 < 12; i20++) {
                            int i21 = i20 * 2;
                            dArr5[i20] = ((((double) (((bArr2[i21 + 8] & 255) * 128) + (bArr2[i21 + 9] & 255))) / 8192.0d) - 1.0d) * 100.0d;
                        }
                        int i22 = 0;
                        while (true) {
                            double[] dArr6 = this.tuning;
                            if (i22 < dArr6.length) {
                                dArr6[i22] = ((double) (i22 * 100)) + dArr5[i22 % 12];
                                i22++;
                            } else {
                                return;
                            }
                        }
                    default:
                        return;
                }
            }
        }
    }

    public double[] getTuning() {
        return this.tuning;
    }

    public double getTuning(int i) {
        return this.tuning[i];
    }

    public Patch getPatch() {
        return this.patch;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }
}
