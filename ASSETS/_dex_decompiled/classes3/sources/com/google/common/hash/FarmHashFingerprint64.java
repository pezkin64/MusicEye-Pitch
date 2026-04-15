package com.google.common.hash;

import com.google.common.base.Preconditions;

final class FarmHashFingerprint64 extends AbstractNonStreamingHashFunction {
    static final HashFunction FARMHASH_FINGERPRINT_64 = new FarmHashFingerprint64();
    private static final long K0 = -4348849565147123417L;
    private static final long K1 = -5435081209227447693L;
    private static final long K2 = -7286425919675154353L;

    private static long hashLength16(long j, long j2, long j3) {
        long j4 = (j ^ j2) * j3;
        long j5 = ((j4 ^ (j4 >>> 47)) ^ j2) * j3;
        return (j5 ^ (j5 >>> 47)) * j3;
    }

    private static long shiftMix(long j) {
        return j ^ (j >>> 47);
    }

    public int bits() {
        return 64;
    }

    FarmHashFingerprint64() {
    }

    public HashCode hashBytes(byte[] bArr, int i, int i2) {
        Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
        return HashCode.fromLong(fingerprint(bArr, i, i2));
    }

    public String toString() {
        return "Hashing.farmHashFingerprint64()";
    }

    static long fingerprint(byte[] bArr, int i, int i2) {
        if (i2 <= 32) {
            if (i2 <= 16) {
                return hashLength0to16(bArr, i, i2);
            }
            return hashLength17to32(bArr, i, i2);
        } else if (i2 <= 64) {
            return hashLength33To64(bArr, i, i2);
        } else {
            return hashLength65Plus(bArr, i, i2);
        }
    }

    private static void weakHashLength32WithSeeds(byte[] bArr, int i, long j, long j2, long[] jArr) {
        long load64 = LittleEndianByteArray.load64(bArr, i);
        long load642 = LittleEndianByteArray.load64(bArr, i + 8);
        long load643 = LittleEndianByteArray.load64(bArr, i + 16);
        long load644 = LittleEndianByteArray.load64(bArr, i + 24);
        long j3 = j + load64;
        long j4 = load642 + j3 + load643;
        jArr[0] = j4 + load644;
        jArr[1] = Long.rotateRight(j2 + j3 + load644, 21) + Long.rotateRight(j4, 44) + j3;
    }

    private static long hashLength0to16(byte[] bArr, int i, int i2) {
        if (i2 >= 8) {
            long j = ((long) (i2 * 2)) + K2;
            long load64 = LittleEndianByteArray.load64(bArr, i) + K2;
            long load642 = LittleEndianByteArray.load64(bArr, (i + i2) - 8);
            return hashLength16((Long.rotateRight(load642, 37) * j) + load64, (Long.rotateRight(load64, 25) + load642) * j, j);
        } else if (i2 >= 4) {
            return hashLength16(((long) i2) + ((((long) LittleEndianByteArray.load32(bArr, i)) & 4294967295L) << 3), ((long) LittleEndianByteArray.load32(bArr, (i + i2) - 4)) & 4294967295L, ((long) (i2 * 2)) + K2);
        } else if (i2 <= 0) {
            return K2;
        } else {
            return shiftMix((((long) ((bArr[i] & 255) + ((bArr[(i2 >> 1) + i] & 255) << 8))) * K2) ^ (((long) (i2 + ((bArr[i + (i2 - 1)] & 255) << 2))) * K0)) * K2;
        }
    }

    private static long hashLength17to32(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        long j = ((long) (i2 * 2)) + K2;
        long load64 = LittleEndianByteArray.load64(bArr, i) * K1;
        long load642 = LittleEndianByteArray.load64(bArr2, i + 8);
        int i3 = i + i2;
        long load643 = LittleEndianByteArray.load64(bArr2, i3 - 8) * j;
        return hashLength16(Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30) + (LittleEndianByteArray.load64(bArr2, i3 - 16) * K2), load64 + Long.rotateRight(load642 + K2, 18) + load643, j);
    }

    private static long hashLength33To64(byte[] bArr, int i, int i2) {
        byte[] bArr2 = bArr;
        long j = ((long) (i2 * 2)) + K2;
        long load64 = LittleEndianByteArray.load64(bArr, i) * K2;
        long load642 = LittleEndianByteArray.load64(bArr2, i + 8);
        int i3 = i + i2;
        long load643 = LittleEndianByteArray.load64(bArr2, i3 - 8) * j;
        long load644 = LittleEndianByteArray.load64(bArr2, i3 - 16) * K2;
        long rotateRight = load643 + Long.rotateRight(load642 + K2, 18) + load64;
        long rotateRight2 = Long.rotateRight(load64 + load642, 43) + Long.rotateRight(load643, 30) + load644;
        long hashLength16 = hashLength16(rotateRight2, rotateRight, j);
        long load645 = LittleEndianByteArray.load64(bArr2, i + 16) * j;
        long load646 = LittleEndianByteArray.load64(bArr2, i + 24);
        long load647 = (rotateRight2 + LittleEndianByteArray.load64(bArr2, i3 - 32)) * j;
        return hashLength16(Long.rotateRight(load645 + load646, 43) + Long.rotateRight(load647, 30) + ((hashLength16 + LittleEndianByteArray.load64(bArr2, i3 - 24)) * j), load645 + Long.rotateRight(load64 + load646, 18) + load647, j);
    }

    /* JADX WARNING: type inference failed for: r16v1, types: [long] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long hashLength65Plus(byte[] r30, int r31, int r32) {
        /*
            r0 = r30
            r1 = -7956866745689871395(0x919387da22096bdd, double:-5.27643297140616E-224)
            long r1 = shiftMix(r1)
            r3 = -7286425919675154353(0x9ae16a3b2f90404f, double:-3.35749372464804E-179)
            long r1 = r1 * r3
            r3 = 2
            long[] r6 = new long[r3]
            long[] r7 = new long[r3]
            r3 = 95310865018149119(0x1529cba0ca458ff, double:2.714078450271586E-302)
            long r8 = com.google.common.hash.LittleEndianByteArray.load64(r30, r31)
            long r3 = r3 + r8
            r8 = 1
            int r5 = r32 + -1
            int r9 = r5 / 64
            int r9 = r9 * 64
            int r9 = r31 + r9
            r10 = r5 & 63
            int r11 = r9 + r10
            int r12 = r11 + -63
            r13 = 2480279821605975764(0x226bb95b4e64b6d4, double:7.104748899679321E-143)
            r4 = r3
            r2 = r1
            r1 = r31
        L_0x0038:
            long r4 = r4 + r13
            r15 = 0
            r16 = r6[r15]
            long r4 = r4 + r16
            r16 = r8
            int r8 = r1 + 8
            long r17 = com.google.common.hash.LittleEndianByteArray.load64(r0, r8)
            long r4 = r4 + r17
            r8 = 37
            long r4 = java.lang.Long.rotateRight(r4, r8)
            r17 = -5435081209227447693(0xb492b66fbe98f273, double:-1.9079014105469082E-55)
            long r4 = r4 * r17
            r19 = r6[r16]
            long r13 = r13 + r19
            r31 = r15
            int r15 = r1 + 48
            long r19 = com.google.common.hash.LittleEndianByteArray.load64(r0, r15)
            long r13 = r13 + r19
            r15 = 42
            long r13 = java.lang.Long.rotateRight(r13, r15)
            long r13 = r13 * r17
            r19 = r7[r16]
            long r19 = r4 ^ r19
            r4 = r6[r31]
            int r15 = r1 + 40
            long r21 = com.google.common.hash.LittleEndianByteArray.load64(r0, r15)
            long r4 = r4 + r21
            long r13 = r13 + r4
            r4 = r7[r31]
            long r2 = r2 + r4
            r15 = 33
            long r2 = java.lang.Long.rotateRight(r2, r15)
            long r21 = r2 * r17
            r2 = r6[r16]
            long r2 = r2 * r17
            r4 = r7[r31]
            long r4 = r19 + r4
            weakHashLength32WithSeeds(r0, r1, r2, r4, r6)
            r24 = r1
            r23 = r6
            int r1 = r24 + 32
            r2 = r7[r16]
            long r2 = r21 + r2
            int r4 = r24 + 16
            long r4 = com.google.common.hash.LittleEndianByteArray.load64(r0, r4)
            long r4 = r4 + r13
            r6 = r7
            weakHashLength32WithSeeds(r0, r1, r2, r4, r6)
            int r1 = r24 + 64
            if (r1 != r9) goto L_0x0147
            r1 = 255(0xff, double:1.26E-321)
            long r1 = r19 & r1
            long r1 = r1 << r16
            long r28 = r1 + r17
            r1 = r7[r31]
            long r3 = (long) r10
            long r1 = r1 + r3
            r7[r31] = r1
            r3 = r23[r31]
            long r3 = r3 + r1
            r23[r31] = r3
            r1 = r7[r31]
            long r1 = r1 + r3
            r7[r31] = r1
            long r21 = r21 + r13
            r1 = r23[r31]
            long r21 = r21 + r1
            int r1 = r11 + -55
            long r1 = com.google.common.hash.LittleEndianByteArray.load64(r0, r1)
            long r1 = r21 + r1
            long r1 = java.lang.Long.rotateRight(r1, r8)
            long r1 = r1 * r28
            r3 = r23[r16]
            long r13 = r13 + r3
            int r3 = r11 + -15
            long r3 = com.google.common.hash.LittleEndianByteArray.load64(r0, r3)
            long r13 = r13 + r3
            r3 = 42
            long r3 = java.lang.Long.rotateRight(r13, r3)
            long r3 = r3 * r28
            r5 = r7[r16]
            r8 = 9
            long r5 = r5 * r8
            long r13 = r1 ^ r5
            r1 = r23[r31]
            long r1 = r1 * r8
            int r5 = r11 + -23
            long r5 = com.google.common.hash.LittleEndianByteArray.load64(r0, r5)
            long r1 = r1 + r5
            long r8 = r3 + r1
            r1 = r7[r31]
            long r1 = r19 + r1
            long r1 = java.lang.Long.rotateRight(r1, r15)
            long r17 = r1 * r28
            r1 = r23[r16]
            long r2 = r1 * r28
            r4 = r7[r31]
            long r4 = r4 + r13
            r1 = r12
            r6 = r23
            weakHashLength32WithSeeds(r0, r1, r2, r4, r6)
            int r1 = r11 + -31
            r2 = r7[r16]
            long r2 = r17 + r2
            int r11 = r11 + -47
            long r4 = com.google.common.hash.LittleEndianByteArray.load64(r0, r11)
            long r4 = r4 + r8
            r6 = r7
            weakHashLength32WithSeeds(r0, r1, r2, r4, r6)
            r24 = r23[r31]
            r26 = r6[r31]
            long r0 = hashLength16(r24, r26, r28)
            long r2 = shiftMix(r8)
            r4 = -4348849565147123417(0xc3a5c85c97cb3127, double:-7.8480313857871552E17)
            long r2 = r2 * r4
            long r0 = r0 + r2
            long r0 = r0 + r13
            r24 = r23[r16]
            r26 = r6[r16]
            long r2 = hashLength16(r24, r26, r28)
            long r26 = r2 + r17
            r24 = r0
            long r0 = hashLength16(r24, r26, r28)
            return r0
        L_0x0147:
            r0 = r30
            r8 = r16
            r2 = r19
            r4 = r21
            r6 = r23
            goto L_0x0038
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.FarmHashFingerprint64.hashLength65Plus(byte[], int, int):long");
    }
}
