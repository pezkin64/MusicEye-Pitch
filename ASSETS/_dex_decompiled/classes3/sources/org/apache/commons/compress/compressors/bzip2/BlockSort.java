package org.apache.commons.compress.compressors.bzip2;

import java.util.BitSet;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

class BlockSort {
    private static final int CLEARMASK = -2097153;
    private static final int DEPTH_THRESH = 10;
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int[] INCS = {1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int SETMASK = 2097152;
    private static final int SMALL_THRESH = 20;
    private static final int STACK_SIZE = 1000;
    private static final int WORK_FACTOR = 30;
    private int[] eclass;
    private boolean firstAttempt;
    private final int[] ftab = new int[65537];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] mainSort_copy = new int[256];
    private final int[] mainSort_runningOrder = new int[256];
    private final char[] quadrant;
    private final int[] stack_dd = new int[TarArchiveEntry.MILLIS_PER_SECOND];
    private final int[] stack_hh = new int[TarArchiveEntry.MILLIS_PER_SECOND];
    private final int[] stack_ll = new int[TarArchiveEntry.MILLIS_PER_SECOND];
    private int workDone;
    private int workLimit;

    private int fmin(int i, int i2) {
        return i < i2 ? i : i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x000d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x000e A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte med3(byte r0, byte r1, byte r2) {
        /*
            if (r0 >= r1) goto L_0x0008
            if (r1 >= r2) goto L_0x0005
            goto L_0x000a
        L_0x0005:
            if (r0 >= r2) goto L_0x000e
            goto L_0x000d
        L_0x0008:
            if (r1 <= r2) goto L_0x000b
        L_0x000a:
            return r1
        L_0x000b:
            if (r0 <= r2) goto L_0x000e
        L_0x000d:
            return r2
        L_0x000e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.med3(byte, byte, byte):byte");
    }

    BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    /* access modifiers changed from: package-private */
    public void blockSort(BZip2CompressorOutputStream.Data data, int i) {
        this.workLimit = i * 30;
        this.workDone = 0;
        this.firstAttempt = true;
        if (i + 1 < 10000) {
            fallbackSort(data, i);
        } else {
            mainSort(data, i);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                fallbackSort(data, i);
            }
        }
        int[] iArr = data.fmap;
        data.origPtr = -1;
        for (int i2 = 0; i2 <= i; i2++) {
            if (iArr[i2] == 0) {
                data.origPtr = i2;
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void fallbackSort(BZip2CompressorOutputStream.Data data, int i) {
        int i2 = i + 1;
        data.block[0] = data.block[i2];
        fallbackSort(data.fmap, data.block, i2);
        for (int i3 = 0; i3 < i2; i3++) {
            int[] iArr = data.fmap;
            iArr[i3] = iArr[i3] - 1;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            if (data.fmap[i4] == -1) {
                data.fmap[i4] = i;
                return;
            }
        }
    }

    private void fallbackSimpleSort(int[] iArr, int[] iArr2, int i, int i2) {
        if (i != i2) {
            if (i2 - i > 3) {
                for (int i3 = i2 - 4; i3 >= i; i3--) {
                    int i4 = iArr[i3];
                    int i5 = iArr2[i4];
                    int i6 = i3 + 4;
                    while (i6 <= i2) {
                        int i7 = iArr[i6];
                        if (i5 <= iArr2[i7]) {
                            break;
                        }
                        iArr[i6 - 4] = i7;
                        i6 += 4;
                    }
                    iArr[i6 - 4] = i4;
                }
            }
            for (int i8 = i2 - 1; i8 >= i; i8--) {
                int i9 = iArr[i8];
                int i10 = iArr2[i9];
                int i11 = i8 + 1;
                while (i11 <= i2) {
                    int i12 = iArr[i11];
                    if (i10 <= iArr2[i12]) {
                        break;
                    }
                    iArr[i11 - 1] = i12;
                    i11++;
                }
                iArr[i11 - 1] = i9;
            }
        }
    }

    private void fswap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private void fvswap(int[] iArr, int i, int i2, int i3) {
        while (i3 > 0) {
            fswap(iArr, i, i2);
            i++;
            i2++;
            i3--;
        }
    }

    private void fpush(int i, int i2, int i3) {
        this.stack_ll[i] = i2;
        this.stack_hh[i] = i3;
    }

    private int[] fpop(int i) {
        return new int[]{this.stack_ll[i], this.stack_hh[i]};
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008c A[EDGE_INSN: B:44:0x008c->B:28:0x008c ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fallbackQSort3(int[] r20, int[] r21, int r22, int r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = 0
            r4 = r22
            r5 = r23
            r0.fpush(r3, r4, r5)
            r4 = 0
            r6 = 1
            r8 = r4
            r7 = r6
        L_0x0013:
            if (r7 <= 0) goto L_0x00e6
            int r10 = r7 + -1
            int[] r11 = r0.fpop(r10)
            r12 = r11[r3]
            r11 = r11[r6]
            int r13 = r11 - r12
            r14 = 10
            if (r13 >= r14) goto L_0x002a
            r0.fallbackSimpleSort(r1, r2, r12, r11)
            r7 = r10
            goto L_0x0013
        L_0x002a:
            r13 = 7621(0x1dc5, double:3.7653E-320)
            long r8 = r8 * r13
            r13 = 1
            long r8 = r8 + r13
            r15 = 32768(0x8000, double:1.61895E-319)
            long r8 = r8 % r15
            r15 = 3
            long r15 = r8 % r15
            int r17 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r17 != 0) goto L_0x0042
            r13 = r1[r12]
            r13 = r2[r13]
        L_0x0040:
            long r13 = (long) r13
            goto L_0x0053
        L_0x0042:
            int r13 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r13 != 0) goto L_0x004e
            int r13 = r12 + r11
            int r13 = r13 >>> r6
            r13 = r1[r13]
            r13 = r2[r13]
            goto L_0x0040
        L_0x004e:
            r13 = r1[r11]
            r13 = r2[r13]
            goto L_0x0040
        L_0x0053:
            r3 = r11
            r5 = r3
            r4 = r12
            r15 = r4
        L_0x0057:
            if (r15 <= r3) goto L_0x005c
            r18 = r6
            goto L_0x0073
        L_0x005c:
            r17 = r1[r15]
            r17 = r2[r17]
            r18 = r6
            int r6 = (int) r13
            int r17 = r17 - r6
            if (r17 != 0) goto L_0x0071
            r0.fswap(r1, r15, r4)
            int r4 = r4 + 1
            int r15 = r15 + 1
        L_0x006e:
            r6 = r18
            goto L_0x0057
        L_0x0071:
            if (r17 <= 0) goto L_0x00e1
        L_0x0073:
            if (r15 <= r3) goto L_0x0076
            goto L_0x008a
        L_0x0076:
            r6 = r1[r3]
            r6 = r2[r6]
            int r2 = (int) r13
            int r6 = r6 - r2
            if (r6 != 0) goto L_0x0088
            r0.fswap(r1, r3, r5)
            int r5 = r5 + -1
        L_0x0083:
            int r3 = r3 + -1
            r2 = r21
            goto L_0x0073
        L_0x0088:
            if (r6 >= 0) goto L_0x0083
        L_0x008a:
            if (r15 <= r3) goto L_0x00d9
            if (r5 >= r4) goto L_0x0098
            r2 = r21
            r7 = r10
        L_0x0091:
            r6 = r18
            r3 = 0
            r4 = 0
            goto L_0x0013
        L_0x0098:
            int r2 = r4 - r12
            int r6 = r15 - r4
            int r2 = r0.fmin(r2, r6)
            int r6 = r15 - r2
            r0.fvswap(r1, r12, r6, r2)
            int r2 = r11 - r5
            int r5 = r5 - r3
            int r2 = r0.fmin(r2, r5)
            int r3 = r3 + 1
            int r6 = r11 - r2
            int r6 = r6 + 1
            r0.fvswap(r1, r3, r6, r2)
            int r15 = r15 + r12
            int r15 = r15 - r4
            int r15 = r15 + -1
            int r2 = r11 - r5
            int r2 = r2 + 1
            int r3 = r15 - r12
            int r4 = r11 - r2
            if (r3 <= r4) goto L_0x00cd
            r0.fpush(r10, r12, r15)
            int r3 = r7 + 1
            r0.fpush(r7, r2, r11)
            r7 = r3
            goto L_0x00d6
        L_0x00cd:
            r0.fpush(r10, r2, r11)
            int r2 = r7 + 1
            r0.fpush(r7, r12, r15)
            r7 = r2
        L_0x00d6:
            r2 = r21
            goto L_0x0091
        L_0x00d9:
            r0.fswap(r1, r15, r3)
            int r15 = r15 + 1
            int r3 = r3 + -1
            goto L_0x00e3
        L_0x00e1:
            int r15 = r15 + 1
        L_0x00e3:
            r2 = r21
            goto L_0x006e
        L_0x00e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.fallbackQSort3(int[], int[], int, int):void");
    }

    private int[] getEclass() {
        if (this.eclass == null) {
            this.eclass = new int[(this.quadrant.length / 2)];
        }
        return this.eclass;
    }

    /* access modifiers changed from: package-private */
    public final void fallbackSort(int[] iArr, byte[] bArr, int i) {
        int i2;
        int[] iArr2 = new int[TarConstants.MAGIC_OFFSET];
        int[] eclass2 = getEclass();
        for (int i3 = 0; i3 < i; i3++) {
            eclass2[i3] = 0;
        }
        for (int i4 = 0; i4 < i; i4++) {
            byte b = bArr[i4] & 255;
            iArr2[b] = iArr2[b] + 1;
        }
        for (int i5 = 1; i5 < 257; i5++) {
            iArr2[i5] = iArr2[i5] + iArr2[i5 - 1];
        }
        for (int i6 = 0; i6 < i; i6++) {
            byte b2 = bArr[i6] & 255;
            int i7 = iArr2[b2] - 1;
            iArr2[b2] = i7;
            iArr[i7] = i6;
        }
        BitSet bitSet = new BitSet(i + 64);
        for (int i8 = 0; i8 < 256; i8++) {
            bitSet.set(iArr2[i8]);
        }
        for (int i9 = 0; i9 < 32; i9++) {
            int i10 = (i9 * 2) + i;
            bitSet.set(i10);
            bitSet.clear(i10 + 1);
        }
        int i11 = 1;
        do {
            int i12 = 0;
            for (int i13 = 0; i13 < i; i13++) {
                if (bitSet.get(i13)) {
                    i12 = i13;
                }
                int i14 = iArr[i13] - i11;
                if (i14 < 0) {
                    i14 += i;
                }
                eclass2[i14] = i12;
            }
            int i15 = -1;
            i2 = 0;
            while (true) {
                int nextClearBit = bitSet.nextClearBit(i15 + 1);
                int i16 = nextClearBit - 1;
                if (i16 < i && (i15 = bitSet.nextSetBit(nextClearBit + 1) - 1) < i) {
                    if (i15 > i16) {
                        i2 += (i15 - i16) + 1;
                        fallbackQSort3(iArr, eclass2, i16, i15);
                        int i17 = -1;
                        while (i16 <= i15) {
                            int i18 = eclass2[iArr[i16]];
                            if (i17 != i18) {
                                bitSet.set(i16);
                                i17 = i18;
                            }
                            i16++;
                        }
                    }
                }
            }
            i11 *= 2;
            if (i11 > i) {
                return;
            }
        } while (i2 != 0);
    }

    private boolean mainSimpleSort(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        boolean z;
        boolean z2;
        byte[] bArr;
        boolean z3;
        int[] iArr;
        boolean z4;
        int i5;
        int i6;
        BZip2CompressorOutputStream.Data data2 = data;
        int i7 = i2;
        boolean z5 = true;
        int i8 = (i7 - i) + 1;
        boolean z6 = false;
        if (i8 < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int i9 = 0;
        while (INCS[i9] < i8) {
            i9++;
        }
        int[] iArr2 = data2.fmap;
        char[] cArr = this.quadrant;
        byte[] bArr2 = data2.block;
        int i10 = i4 + 1;
        boolean z7 = this.firstAttempt;
        int i11 = this.workLimit;
        int i12 = this.workDone;
        loop1:
        while (true) {
            i9--;
            if (i9 < 0) {
                z = z5;
                z2 = z6;
                break;
            }
            int i13 = INCS[i9];
            int i14 = i + i13;
            int i15 = i14 - 1;
            while (true) {
                if (i14 <= i7) {
                    int i16 = 3;
                    while (i14 <= i7) {
                        i16--;
                        if (i16 < 0) {
                            break;
                        }
                        int i17 = iArr2[i14];
                        int i18 = i17 + i3;
                        boolean z8 = z6;
                        int i19 = z8;
                        int i20 = i14;
                        while (true) {
                            if (z8) {
                                iArr2[i20] = i19;
                                z4 = z5;
                                i5 = i20 - i13;
                                if (i5 <= i15) {
                                    bArr = bArr2;
                                    iArr = iArr2;
                                    z3 = z6;
                                    break;
                                }
                                i20 = i5;
                            } else {
                                z4 = z5;
                                z8 = z4;
                            }
                            int i21 = iArr2[i20 - i13];
                            int i22 = i21 + i3;
                            z3 = z6;
                            byte b = bArr2[i22 + 1];
                            bArr = bArr2;
                            byte b2 = bArr[i18 + 1];
                            if (b != b2) {
                                iArr = iArr2;
                                i6 = i21;
                                if ((b & 255) <= (b2 & 255)) {
                                    break;
                                }
                            } else {
                                byte b3 = bArr[i22 + 2];
                                byte b4 = bArr[i18 + 2];
                                if (b3 != b4) {
                                    iArr = iArr2;
                                    i6 = i21;
                                    if ((b3 & 255) <= (b4 & 255)) {
                                        break;
                                    }
                                } else {
                                    byte b5 = bArr[i22 + 3];
                                    byte b6 = bArr[i18 + 3];
                                    if (b5 != b6) {
                                        iArr = iArr2;
                                        i6 = i21;
                                        if ((b5 & 255) <= (b6 & 255)) {
                                            break;
                                        }
                                    } else {
                                        byte b7 = bArr[i22 + 4];
                                        byte b8 = bArr[i18 + 4];
                                        if (b7 != b8) {
                                            iArr = iArr2;
                                            i6 = i21;
                                            if ((b7 & 255) <= (b8 & 255)) {
                                                break;
                                            }
                                        } else {
                                            byte b9 = bArr[i22 + 5];
                                            byte b10 = bArr[i18 + 5];
                                            if (b9 != b10) {
                                                iArr = iArr2;
                                                i6 = i21;
                                                if ((b9 & 255) <= (b10 & 255)) {
                                                    break;
                                                }
                                            } else {
                                                int i23 = i22 + 6;
                                                byte b11 = bArr[i23];
                                                int i24 = i18 + 6;
                                                iArr = iArr2;
                                                byte b12 = bArr[i24];
                                                if (b11 != b12) {
                                                    i6 = i21;
                                                    if ((b11 & 255) <= (b12 & 255)) {
                                                        break;
                                                    }
                                                } else {
                                                    int i25 = i4;
                                                    while (true) {
                                                        if (i25 <= 0) {
                                                            break;
                                                        }
                                                        int i26 = i23 + 1;
                                                        int i27 = i25 - 4;
                                                        byte b13 = bArr[i26];
                                                        int i28 = i24 + 1;
                                                        int i29 = i26;
                                                        byte b14 = bArr[i28];
                                                        if (b13 != b14) {
                                                            i6 = i21;
                                                            if ((b13 & 255) <= (b14 & 255)) {
                                                                break;
                                                            }
                                                        } else {
                                                            char c = cArr[i23];
                                                            char c2 = cArr[i24];
                                                            if (c != c2) {
                                                                i6 = i21;
                                                                if (c <= c2) {
                                                                    break;
                                                                }
                                                            } else {
                                                                int i30 = i23 + 2;
                                                                byte b15 = bArr[i30];
                                                                int i31 = i24 + 2;
                                                                int i32 = i30;
                                                                byte b16 = bArr[i31];
                                                                if (b15 != b16) {
                                                                    i6 = i21;
                                                                    if ((b15 & 255) <= (b16 & 255)) {
                                                                        break;
                                                                    }
                                                                } else {
                                                                    char c3 = cArr[i29];
                                                                    char c4 = cArr[i28];
                                                                    if (c3 != c4) {
                                                                        i6 = i21;
                                                                        if (c3 <= c4) {
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        int i33 = i23 + 3;
                                                                        byte b17 = bArr[i33];
                                                                        int i34 = i24 + 3;
                                                                        int i35 = i33;
                                                                        byte b18 = bArr[i34];
                                                                        if (b17 != b18) {
                                                                            i6 = i21;
                                                                            if ((b17 & 255) <= (b18 & 255)) {
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            char c5 = cArr[i32];
                                                                            char c6 = cArr[i31];
                                                                            if (c5 != c6) {
                                                                                i6 = i21;
                                                                                if (c5 <= c6) {
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                int i36 = i23 + 4;
                                                                                byte b19 = bArr[i36];
                                                                                i24 += 4;
                                                                                i6 = i21;
                                                                                byte b20 = bArr[i24];
                                                                                if (b19 != b20) {
                                                                                    if ((b19 & 255) <= (b20 & 255)) {
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    char c7 = cArr[i35];
                                                                                    char c8 = cArr[i34];
                                                                                    if (c7 != c8) {
                                                                                        if (c7 <= c8) {
                                                                                            break;
                                                                                        }
                                                                                    } else {
                                                                                        if (i36 >= i10) {
                                                                                            i36 -= i10;
                                                                                        }
                                                                                        if (i24 >= i10) {
                                                                                            i24 -= i10;
                                                                                        }
                                                                                        i12++;
                                                                                        i21 = i6;
                                                                                        i23 = i36;
                                                                                        i25 = i27;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            bArr2 = bArr;
                            z5 = z4;
                            i19 = i6;
                            iArr2 = iArr;
                            z6 = z3;
                        }
                        i5 = i20;
                        iArr[i5] = i17;
                        i14++;
                        bArr2 = bArr;
                        z5 = z4;
                        iArr2 = iArr;
                        z6 = z3;
                    }
                    byte[] bArr3 = bArr2;
                    int[] iArr3 = iArr2;
                    z = z5;
                    z2 = z6;
                    if (z7 && i14 <= i7 && i12 > i11) {
                        break loop1;
                    }
                    bArr2 = bArr3;
                    z5 = z;
                    iArr2 = iArr3;
                    z6 = z2;
                }
            }
        }
        this.workDone = i12;
        return (!z7 || i12 <= i11) ? z2 : z;
    }

    private static void vswap(int[] iArr, int i, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            int i5 = iArr[i];
            iArr[i] = iArr[i2];
            iArr[i2] = i5;
            i2++;
            i++;
        }
    }

    private void mainQSort3(BZip2CompressorOutputStream.Data data, int i, int i2, int i3, int i4) {
        boolean z;
        int i5;
        int i6;
        int i7;
        BlockSort blockSort = this;
        BZip2CompressorOutputStream.Data data2 = data;
        int[] iArr = blockSort.stack_ll;
        int[] iArr2 = blockSort.stack_hh;
        int[] iArr3 = blockSort.stack_dd;
        int[] iArr4 = data2.fmap;
        byte[] bArr = data2.block;
        iArr[0] = i;
        iArr2[0] = i2;
        iArr3[0] = i3;
        boolean z2 = true;
        int i8 = 1;
        while (true) {
            int i9 = i8 - 1;
            if (i9 >= 0) {
                int i10 = i8;
                int i11 = iArr[i9];
                int i12 = i10;
                int i13 = iArr2[i9];
                int i14 = i12;
                int i15 = iArr3[i9];
                if (i13 - i11 < 20) {
                    blockSort = this;
                    data2 = data;
                    z = z2;
                    i5 = i4;
                } else if (i15 > 10) {
                    i5 = i4;
                    z = z2;
                } else {
                    int i16 = i15 + 1;
                    z = z2;
                    byte med3 = med3(bArr[iArr4[i11] + i16], bArr[iArr4[i13] + i16], bArr[iArr4[(i11 + i13) >>> 1] + i16]) & 255;
                    int i17 = i11;
                    int i18 = i17;
                    int i19 = i13;
                    int i20 = i19;
                    while (true) {
                        if (i18 <= i19) {
                            int i21 = iArr4[i18];
                            int i22 = (bArr[i21 + i16] & 255) - med3;
                            if (i22 == 0) {
                                iArr4[i18] = iArr4[i17];
                                iArr4[i17] = i21;
                                i17++;
                                i18++;
                            } else if (i22 < 0) {
                                i18++;
                            }
                            BZip2CompressorOutputStream.Data data3 = data;
                        }
                        i6 = i20;
                        while (true) {
                            if (i18 > i19) {
                                i7 = i13;
                                break;
                            }
                            int i23 = iArr4[i19];
                            i7 = i13;
                            int i24 = (bArr[i23 + i16] & 255) - med3;
                            if (i24 != 0) {
                                if (i24 <= 0) {
                                    break;
                                }
                                i19--;
                            } else {
                                iArr4[i19] = iArr4[i6];
                                iArr4[i6] = i23;
                                i6--;
                                i19--;
                            }
                            i13 = i7;
                        }
                        if (i18 > i19) {
                            break;
                        }
                        int i25 = iArr4[i18];
                        iArr4[i18] = iArr4[i19];
                        iArr4[i19] = i25;
                        i13 = i7;
                        i19--;
                        i18++;
                        i20 = i6;
                        BZip2CompressorOutputStream.Data data32 = data;
                    }
                    if (i6 < i17) {
                        iArr[i9] = i11;
                        iArr2[i9] = i7;
                        iArr3[i9] = i16;
                        i8 = i14;
                    } else {
                        int i26 = i17 - i11;
                        int i27 = i18 - i17;
                        if (i26 >= i27) {
                            i26 = i27;
                        }
                        vswap(iArr4, i11, i18 - i26, i26);
                        int i28 = i7 - i6;
                        int i29 = i6 - i19;
                        if (i28 >= i29) {
                            i28 = i29;
                        }
                        vswap(iArr4, i18, (i7 - i28) + 1, i28);
                        int i30 = (i18 + i11) - i17;
                        int i31 = i7 - i29;
                        iArr[i9] = i11;
                        iArr2[i9] = i30 - 1;
                        iArr3[i9] = i15;
                        iArr[i14] = i30;
                        iArr2[i14] = i31;
                        iArr3[i14] = i16;
                        int i32 = i14 + 1;
                        iArr[i32] = i31 + 1;
                        iArr2[i32] = i7;
                        iArr3[i32] = i15;
                        i8 = i14 + 2;
                    }
                    blockSort = this;
                    data2 = data;
                    z2 = z;
                }
                if (!blockSort.mainSimpleSort(data2, i11, i13, i15, i5)) {
                    i8 = i9;
                    blockSort = this;
                    data2 = data;
                    z2 = z;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0169, code lost:
        r0 = r26;
        r24 = SETMASK;
        r18 = r6;
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0177, code lost:
        if (r1 > 255) goto L_0x0185;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0179, code lost:
        r7[r1] = r9[(r1 << 8) + r21] & CLEARMASK;
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0185, code lost:
        r1 = r21 << 8;
        r2 = r9[r1] & CLEARMASK;
        r3 = (r21 + 1) << 8;
        r5 = r9[r3] & CLEARMASK;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0191, code lost:
        if (r2 >= r5) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0193, code lost:
        r16 = r11[r2];
        r22 = r4;
        r4 = r10[r16] & 255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x019e, code lost:
        if (r8[r4] != false) goto L_0x01b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01a0, code lost:
        r6 = r7[r4];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a2, code lost:
        if (r16 != 0) goto L_0x01a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01a4, code lost:
        r16 = r28;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a7, code lost:
        r16 = r16 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01a9, code lost:
        r11[r6] = r16;
        r7[r4] = r7[r4] + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b1, code lost:
        r2 = r2 + 1;
        r4 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b8, code lost:
        r22 = r4;
        r2 = 256;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01bc, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01be, code lost:
        if (r2 < 0) goto L_0x01cb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c0, code lost:
        r4 = (r2 << 8) + r21;
        r9[r4] = r9[r4] | r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01cb, code lost:
        r8[r21] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01cf, code lost:
        if (r15 >= 255) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01d1, code lost:
        r1 = r9[r1] & r22;
        r3 = (r9[r3] & r22) - r1;
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01e1, code lost:
        if ((r3 >> r4) <= 65534) goto L_0x01e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01e3, code lost:
        r4 = r4 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01e6, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e8, code lost:
        if (r5 >= r3) goto L_0x0204;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01ea, code lost:
        r6 = r11[r1 + r5];
        r2 = (char) (r5 >> r4);
        r12[r6] = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01f5, code lost:
        if (r6 >= 20) goto L_0x01fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01f7, code lost:
        r12[(r6 + r28) + 1] = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01fd, code lost:
        r5 = r5 + 1;
        r0 = r26;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0204, code lost:
        r15 = r15 + 1;
        r1 = r27;
        r5 = r28;
        r6 = r18;
        r0 = 255;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mainSort(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.Data r27, int r28) {
        /*
            r26 = this;
            r0 = r26
            r1 = r27
            r5 = r28
            int[] r6 = r0.mainSort_runningOrder
            int[] r7 = r0.mainSort_copy
            boolean[] r8 = r0.mainSort_bigDone
            int[] r9 = r0.ftab
            byte[] r10 = r1.block
            int[] r11 = r1.fmap
            char[] r12 = r0.quadrant
            int r13 = r0.workLimit
            boolean r14 = r0.firstAttempt
            r2 = 65537(0x10001, float:9.1837E-41)
        L_0x001b:
            int r2 = r2 + -1
            r15 = 0
            if (r2 < 0) goto L_0x0023
            r9[r2] = r15
            goto L_0x001b
        L_0x0023:
            r2 = r15
        L_0x0024:
            r3 = 20
            r4 = 1
            if (r2 >= r3) goto L_0x003a
            int r3 = r5 + r2
            int r3 = r3 + 2
            int r16 = r5 + 1
            int r16 = r2 % r16
            int r16 = r16 + 1
            byte r4 = r10[r16]
            r10[r3] = r4
            int r2 = r2 + 1
            goto L_0x0024
        L_0x003a:
            int r2 = r5 + 21
        L_0x003c:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x0043
            r12[r2] = r15
            goto L_0x003c
        L_0x0043:
            int r2 = r5 + 1
            byte r3 = r10[r2]
            r10[r15] = r3
            r17 = r15
            r15 = 255(0xff, float:3.57E-43)
            r3 = r3 & r15
            r18 = r4
            r4 = r17
        L_0x0052:
            if (r4 > r5) goto L_0x0066
            int r4 = r4 + 1
            byte r0 = r10[r4]
            r0 = r0 & r15
            int r3 = r3 << 8
            int r3 = r3 + r0
            r19 = r9[r3]
            int r19 = r19 + 1
            r9[r3] = r19
            r3 = r0
            r0 = r26
            goto L_0x0052
        L_0x0066:
            r0 = r18
        L_0x0068:
            r3 = 65536(0x10000, float:9.18355E-41)
            if (r0 > r3) goto L_0x0078
            r3 = r9[r0]
            int r4 = r0 + -1
            r4 = r9[r4]
            int r3 = r3 + r4
            r9[r0] = r3
            int r0 = r0 + 1
            goto L_0x0068
        L_0x0078:
            byte r0 = r10[r18]
            r0 = r0 & r15
            r3 = r17
        L_0x007d:
            if (r3 >= r5) goto L_0x0093
            int r4 = r3 + 2
            byte r4 = r10[r4]
            r4 = r4 & r15
            int r0 = r0 << 8
            int r0 = r0 + r4
            r19 = r9[r0]
            int r19 = r19 + -1
            r9[r0] = r19
            r11[r19] = r3
            int r3 = r3 + 1
            r0 = r4
            goto L_0x007d
        L_0x0093:
            byte r0 = r10[r2]
            r0 = r0 & r15
            int r0 = r0 << 8
            byte r2 = r10[r18]
            r2 = r2 & r15
            int r0 = r0 + r2
            r2 = r9[r0]
            int r2 = r2 + -1
            r9[r0] = r2
            r11[r2] = r5
            r19 = 256(0x100, float:3.59E-43)
            r0 = r19
        L_0x00a8:
            int r0 = r0 + -1
            if (r0 < 0) goto L_0x00b1
            r8[r0] = r17
            r6[r0] = r0
            goto L_0x00a8
        L_0x00b1:
            r0 = 364(0x16c, float:5.1E-43)
            r2 = r18
        L_0x00b5:
            if (r0 == r2) goto L_0x0101
            int r0 = r0 / 3
            r2 = r0
        L_0x00ba:
            if (r2 > r15) goto L_0x00fd
            r3 = r6[r2]
            int r4 = r3 + 1
            int r4 = r4 << 8
            r4 = r9[r4]
            int r20 = r3 << 8
            r20 = r9[r20]
            int r4 = r4 - r20
            int r15 = r0 + -1
            int r21 = r2 - r0
            r21 = r6[r21]
            r22 = r2
        L_0x00d2:
            int r23 = r21 + 1
            int r23 = r23 << 8
            r23 = r9[r23]
            int r24 = r21 << 8
            r24 = r9[r24]
            r25 = r0
            int r0 = r23 - r24
            if (r0 <= r4) goto L_0x00f4
            r6[r22] = r21
            int r0 = r22 - r25
            if (r0 > r15) goto L_0x00eb
            r22 = r0
            goto L_0x00f4
        L_0x00eb:
            int r21 = r0 - r25
            r21 = r6[r21]
            r22 = r0
            r0 = r25
            goto L_0x00d2
        L_0x00f4:
            r6[r22] = r3
            int r2 = r2 + 1
            r0 = r25
            r15 = 255(0xff, float:3.57E-43)
            goto L_0x00ba
        L_0x00fd:
            r25 = r0
            r2 = 1
            goto L_0x00b5
        L_0x0101:
            r0 = r15
            r15 = r17
        L_0x0104:
            if (r15 > r0) goto L_0x0212
            r21 = r6[r15]
            r2 = r17
        L_0x010a:
            r3 = 2097152(0x200000, float:2.938736E-39)
            r4 = -2097153(0xffffffffffdfffff, float:NaN)
            if (r2 > r0) goto L_0x0169
            int r0 = r21 << 8
            int r22 = r0 + r2
            r23 = r9[r22]
            r0 = r23 & r3
            if (r0 == r3) goto L_0x0154
            r0 = r2
            r2 = r23 & r4
            int r24 = r22 + 1
            r24 = r9[r24]
            r4 = r24 & r4
            r18 = 1
            int r4 = r4 + -1
            if (r4 <= r2) goto L_0x0143
            r24 = r3
            r3 = r4
            r4 = 2
            r16 = r0
            r25 = r18
            r0 = r26
            r18 = r6
            r6 = 20
            r0.mainQSort3(r1, r2, r3, r4, r5)
            if (r14 == 0) goto L_0x014f
            int r1 = r0.workDone
            if (r1 <= r13) goto L_0x014f
            goto L_0x0212
        L_0x0143:
            r16 = r0
            r24 = r3
            r25 = r18
            r0 = r26
            r18 = r6
            r6 = 20
        L_0x014f:
            r1 = r23 | r24
            r9[r22] = r1
            goto L_0x015e
        L_0x0154:
            r0 = r26
            r16 = r2
            r18 = r6
            r6 = 20
            r25 = 1
        L_0x015e:
            int r2 = r16 + 1
            r1 = r27
            r5 = r28
            r6 = r18
            r0 = 255(0xff, float:3.57E-43)
            goto L_0x010a
        L_0x0169:
            r0 = r26
            r24 = r3
            r18 = r6
            r6 = 20
            r25 = 1
            r1 = r17
        L_0x0175:
            r2 = 255(0xff, float:3.57E-43)
            if (r1 > r2) goto L_0x0185
            int r2 = r1 << 8
            int r2 = r2 + r21
            r2 = r9[r2]
            r2 = r2 & r4
            r7[r1] = r2
            int r1 = r1 + 1
            goto L_0x0175
        L_0x0185:
            int r1 = r21 << 8
            r2 = r9[r1]
            r2 = r2 & r4
            int r3 = r21 + 1
            int r3 = r3 << 8
            r5 = r9[r3]
            r5 = r5 & r4
        L_0x0191:
            if (r2 >= r5) goto L_0x01b8
            r16 = r11[r2]
            r22 = r4
            byte r4 = r10[r16]
            r6 = 255(0xff, float:3.57E-43)
            r4 = r4 & r6
            boolean r6 = r8[r4]
            if (r6 != 0) goto L_0x01b1
            r6 = r7[r4]
            if (r16 != 0) goto L_0x01a7
            r16 = r28
            goto L_0x01a9
        L_0x01a7:
            int r16 = r16 + -1
        L_0x01a9:
            r11[r6] = r16
            r6 = r7[r4]
            int r6 = r6 + 1
            r7[r4] = r6
        L_0x01b1:
            int r2 = r2 + 1
            r4 = r22
            r6 = 20
            goto L_0x0191
        L_0x01b8:
            r22 = r4
            r2 = r19
        L_0x01bc:
            int r2 = r2 + -1
            if (r2 < 0) goto L_0x01cb
            int r4 = r2 << 8
            int r4 = r4 + r21
            r5 = r9[r4]
            r5 = r5 | r24
            r9[r4] = r5
            goto L_0x01bc
        L_0x01cb:
            r8[r21] = r25
            r2 = 255(0xff, float:3.57E-43)
            if (r15 >= r2) goto L_0x0204
            r1 = r9[r1]
            r1 = r1 & r22
            r3 = r9[r3]
            r3 = r3 & r22
            int r3 = r3 - r1
            r4 = r17
        L_0x01dc:
            int r5 = r3 >> r4
            r6 = 65534(0xfffe, float:9.1833E-41)
            if (r5 <= r6) goto L_0x01e6
            int r4 = r4 + 1
            goto L_0x01dc
        L_0x01e6:
            r5 = r17
        L_0x01e8:
            if (r5 >= r3) goto L_0x0204
            int r6 = r1 + r5
            r6 = r11[r6]
            int r2 = r5 >> r4
            char r2 = (char) r2
            r12[r6] = r2
            r0 = 20
            if (r6 >= r0) goto L_0x01fd
            int r6 = r6 + r28
            int r6 = r6 + 1
            r12[r6] = r2
        L_0x01fd:
            int r5 = r5 + 1
            r0 = r26
            r2 = 255(0xff, float:3.57E-43)
            goto L_0x01e8
        L_0x0204:
            r0 = 20
            int r15 = r15 + 1
            r1 = r27
            r5 = r28
            r6 = r18
            r0 = 255(0xff, float:3.57E-43)
            goto L_0x0104
        L_0x0212:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BlockSort.mainSort(org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data, int):void");
    }
}
