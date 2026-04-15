package com.google.android.gms.internal.play_billing;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbd {
    static int zza(byte[] bArr, int i, zzbc zzbc) throws zzdc {
        int zzh = zzh(bArr, i, zzbc);
        int i2 = zzbc.zza;
        if (i2 < 0) {
            throw zzdc.zzd();
        } else if (i2 > bArr.length - zzh) {
            throw zzdc.zzg();
        } else if (i2 == 0) {
            zzbc.zzc = zzbq.zzb;
            return zzh;
        } else {
            zzbc.zzc = zzbq.zzl(bArr, zzh, i2);
            return zzh + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << Ascii.DLE);
    }

    static int zzc(zzeo zzeo, byte[] bArr, int i, int i2, int i3, zzbc zzbc) throws IOException {
        Object zze = zzeo.zze();
        zzeo zzeo2 = zzeo;
        zzbc zzbc2 = zzbc;
        int zzl = zzl(zze, zzeo2, bArr, i, i2, i3, zzbc2);
        zzeo2.zzf(zze);
        zzbc2.zzc = zze;
        return zzl;
    }

    static int zzd(zzeo zzeo, byte[] bArr, int i, int i2, zzbc zzbc) throws IOException {
        Object zze = zzeo.zze();
        zzeo zzeo2 = zzeo;
        zzbc zzbc2 = zzbc;
        int zzm = zzm(zze, zzeo2, bArr, i, i2, zzbc2);
        zzeo2.zzf(zze);
        zzbc2.zzc = zze;
        return zzm;
    }

    static int zze(zzeo zzeo, int i, byte[] bArr, int i2, int i3, zzcz zzcz, zzbc zzbc) throws IOException {
        int zzd = zzd(zzeo, bArr, i2, i3, zzbc);
        zzcz.add(zzbc.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzbc);
            if (i != zzbc.zza) {
                break;
            }
            zzd = zzd(zzeo, bArr, zzh, i3, zzbc);
            zzcz.add(zzbc.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzcz zzcz, zzbc zzbc) throws IOException {
        zzct zzct = (zzct) zzcz;
        int zzh = zzh(bArr, i, zzbc);
        int i2 = zzbc.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzbc);
            zzct.zzg(zzbc.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw zzdc.zzg();
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzfg zzfg, zzbc zzbc) throws zzdc {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                zzbc zzbc2 = zzbc;
                int zzk = zzk(bArr, i2, zzbc2);
                zzfg.zzj(i, Long.valueOf(zzbc2.zzb));
                return zzk;
            } else if (i4 == 1) {
                zzfg.zzj(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                byte[] bArr2 = bArr;
                zzbc zzbc3 = zzbc;
                int zzh = zzh(bArr2, i2, zzbc3);
                int i5 = zzbc3.zza;
                if (i5 < 0) {
                    throw zzdc.zzd();
                } else if (i5 <= bArr2.length - zzh) {
                    if (i5 == 0) {
                        zzfg.zzj(i, zzbq.zzb);
                    } else {
                        zzfg.zzj(i, zzbq.zzl(bArr2, zzh, i5));
                    }
                    return zzh + i5;
                } else {
                    throw zzdc.zzg();
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzfg zzf = zzfg.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i2, zzbc);
                    i7 = zzbc.zza;
                    if (i7 == i6) {
                        i2 = zzh2;
                        break;
                    }
                    i2 = zzg(i7, bArr, zzh2, i3, zzf, zzbc);
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzdc.zze();
                }
                zzfg.zzj(i, zzf);
                return i2;
            } else if (i4 == 5) {
                zzfg.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzdc.zzb();
            }
        } else {
            throw zzdc.zzb();
        }
    }

    static int zzh(byte[] bArr, int i, zzbc zzbc) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return zzi(b, bArr, i2, zzbc);
        }
        zzbc.zza = b;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzbc zzbc) {
        byte b = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b >= 0) {
            zzbc.zza = i4 | (b << 7);
            return i3;
        }
        int i5 = i4 | ((b & Ascii.DEL) << 7);
        int i6 = i2 + 2;
        byte b2 = bArr[i3];
        if (b2 >= 0) {
            zzbc.zza = i5 | (b2 << Ascii.SO);
            return i6;
        }
        int i7 = i5 | ((b2 & Ascii.DEL) << Ascii.SO);
        int i8 = i2 + 3;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzbc.zza = i7 | (b3 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b3 & Ascii.DEL) << Ascii.NAK);
        int i10 = i2 + 4;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzbc.zza = i9 | (b4 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b4 & Ascii.DEL) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzbc.zza = i11;
                return i12;
            }
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzcz zzcz, zzbc zzbc) {
        zzct zzct = (zzct) zzcz;
        int zzh = zzh(bArr, i2, zzbc);
        zzct.zzg(zzbc.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzbc);
            if (i != zzbc.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzbc);
            zzct.zzg(zzbc.zza);
        }
        return zzh;
    }

    static int zzk(byte[] bArr, int i, zzbc zzbc) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzbc.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b = bArr[i2];
        long j2 = (j & 127) | (((long) (b & Ascii.DEL)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b2 & Ascii.DEL)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        zzbc.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzeo zzeo, byte[] bArr, int i, int i2, int i3, zzbc zzbc) throws IOException {
        zzef zzef = (zzef) zzeo;
        Object obj2 = obj;
        int zzc = zzef.zzc(obj2, bArr, i, i2, i3, zzbc);
        zzbc.zzc = obj2;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzm(java.lang.Object r6, com.google.android.gms.internal.play_billing.zzeo r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.play_billing.zzbc r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzi(r9, r8, r0, r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r4 = r3 + r9
            r1 = r6
            r0 = r7
            r2 = r8
            r5 = r11
            r0.zzh(r1, r2, r3, r4, r5)
            r5.zzc = r1
            return r4
        L_0x001e:
            com.google.android.gms.internal.play_billing.zzdc r6 = com.google.android.gms.internal.play_billing.zzdc.zzg()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzbd.zzm(java.lang.Object, com.google.android.gms.internal.play_billing.zzeo, byte[], int, int, com.google.android.gms.internal.play_billing.zzbc):int");
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }
}
