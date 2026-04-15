package com.google.common.hash;

import com.google.errorprone.annotations.Immutable;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Immutable
final class Murmur3_128HashFunction extends AbstractHashFunction implements Serializable {
    static final HashFunction GOOD_FAST_HASH_128 = new Murmur3_128HashFunction(Hashing.GOOD_FAST_HASH_SEED);
    static final HashFunction MURMUR3_128 = new Murmur3_128HashFunction(0);
    private static final long serialVersionUID = 0;
    private final int seed;

    public int bits() {
        return 128;
    }

    Murmur3_128HashFunction(int i) {
        this.seed = i;
    }

    public Hasher newHasher() {
        return new Murmur3_128Hasher(this.seed);
    }

    public String toString() {
        return "Hashing.murmur3_128(" + this.seed + ")";
    }

    public boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof Murmur3_128HashFunction) || this.seed != ((Murmur3_128HashFunction) obj).seed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getClass().hashCode() ^ this.seed;
    }

    private static final class Murmur3_128Hasher extends AbstractStreamingHasher {
        private static final long C1 = -8663945395140668459L;
        private static final long C2 = 5545529020109919103L;
        private static final int CHUNK_SIZE = 16;
        private long h1;
        private long h2;
        private int length = 0;

        private static long fmix64(long j) {
            long j2 = (j ^ (j >>> 33)) * -49064778989728563L;
            long j3 = (j2 ^ (j2 >>> 33)) * -4265267296055464877L;
            return j3 ^ (j3 >>> 33);
        }

        Murmur3_128Hasher(int i) {
            super(16);
            long j = (long) i;
            this.h1 = j;
            this.h2 = j;
        }

        /* access modifiers changed from: protected */
        public void process(ByteBuffer byteBuffer) {
            bmix64(byteBuffer.getLong(), byteBuffer.getLong());
            this.length += 16;
        }

        private void bmix64(long j, long j2) {
            long mixK1 = mixK1(j) ^ this.h1;
            this.h1 = mixK1;
            long rotateLeft = Long.rotateLeft(mixK1, 27);
            long j3 = this.h2;
            this.h1 = ((rotateLeft + j3) * 5) + 1390208809;
            long mixK2 = mixK2(j2) ^ j3;
            this.h2 = mixK2;
            this.h2 = ((Long.rotateLeft(mixK2, 31) + this.h1) * 5) + 944331445;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x0073, code lost:
            r7 = r7 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(8)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x007d, code lost:
            r0 = r12.getLong();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0090, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(5))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x009e, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(4))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x00ad, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(3))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x00bb, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(2))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x00c9, code lost:
            r0 = r0 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(1))) << 8);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x00d7, code lost:
            r0 = r0 ^ ((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(0)));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x00e2, code lost:
            r11.h1 = mixK1(r0) ^ r11.h1;
            r11.h2 ^= mixK2(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x00f4, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:5:0x0032, code lost:
            r7 = r7 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(13))) << 40);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x003f, code lost:
            r7 = r7 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(12))) << 32);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x004c, code lost:
            r7 = r7 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(11))) << 24);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0059, code lost:
            r7 = r7 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(10))) << 16);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0066, code lost:
            r7 = r7 ^ (((long) com.google.common.primitives.UnsignedBytes.toInt(r12.get(9))) << 8);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void processRemaining(java.nio.ByteBuffer r12) {
            /*
                r11 = this;
                int r0 = r11.length
                int r1 = r12.remaining()
                int r0 = r0 + r1
                r11.length = r0
                int r0 = r12.remaining()
                r1 = 48
                r2 = 40
                r3 = 32
                r4 = 24
                r5 = 16
                r6 = 8
                r7 = 0
                switch(r0) {
                    case 1: goto L_0x00d6;
                    case 2: goto L_0x00c8;
                    case 3: goto L_0x00ba;
                    case 4: goto L_0x00ac;
                    case 5: goto L_0x009d;
                    case 6: goto L_0x008f;
                    case 7: goto L_0x0082;
                    case 8: goto L_0x007d;
                    case 9: goto L_0x0073;
                    case 10: goto L_0x0066;
                    case 11: goto L_0x0059;
                    case 12: goto L_0x004c;
                    case 13: goto L_0x003f;
                    case 14: goto L_0x0032;
                    case 15: goto L_0x0026;
                    default: goto L_0x001e;
                }
            L_0x001e:
                java.lang.AssertionError r12 = new java.lang.AssertionError
                java.lang.String r0 = "Should never get here."
                r12.<init>(r0)
                throw r12
            L_0x0026:
                r0 = 14
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r7 = (long) r0
                long r7 = r7 << r1
            L_0x0032:
                r0 = 13
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r0 = r0 << r2
                long r7 = r7 ^ r0
            L_0x003f:
                r0 = 12
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r0 = r0 << r3
                long r7 = r7 ^ r0
            L_0x004c:
                r0 = 11
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r0 = r0 << r4
                long r7 = r7 ^ r0
            L_0x0059:
                r0 = 10
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r0 = r0 << r5
                long r7 = r7 ^ r0
            L_0x0066:
                r0 = 9
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r0 = r0 << r6
                long r7 = r7 ^ r0
            L_0x0073:
                byte r0 = r12.get(r6)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r0 = (long) r0
                long r7 = r7 ^ r0
            L_0x007d:
                long r0 = r12.getLong()
                goto L_0x00e2
            L_0x0082:
                r0 = 6
                byte r0 = r12.get(r0)
                int r0 = com.google.common.primitives.UnsignedBytes.toInt(r0)
                long r9 = (long) r0
                long r0 = r9 << r1
                goto L_0x0090
            L_0x008f:
                r0 = r7
            L_0x0090:
                r9 = 5
                byte r9 = r12.get(r9)
                int r9 = com.google.common.primitives.UnsignedBytes.toInt(r9)
                long r9 = (long) r9
                long r9 = r9 << r2
                long r0 = r0 ^ r9
                goto L_0x009e
            L_0x009d:
                r0 = r7
            L_0x009e:
                r2 = 4
                byte r2 = r12.get(r2)
                int r2 = com.google.common.primitives.UnsignedBytes.toInt(r2)
                long r9 = (long) r2
                long r2 = r9 << r3
                long r0 = r0 ^ r2
                goto L_0x00ad
            L_0x00ac:
                r0 = r7
            L_0x00ad:
                r2 = 3
                byte r2 = r12.get(r2)
                int r2 = com.google.common.primitives.UnsignedBytes.toInt(r2)
                long r2 = (long) r2
                long r2 = r2 << r4
                long r0 = r0 ^ r2
                goto L_0x00bb
            L_0x00ba:
                r0 = r7
            L_0x00bb:
                r2 = 2
                byte r2 = r12.get(r2)
                int r2 = com.google.common.primitives.UnsignedBytes.toInt(r2)
                long r2 = (long) r2
                long r2 = r2 << r5
                long r0 = r0 ^ r2
                goto L_0x00c9
            L_0x00c8:
                r0 = r7
            L_0x00c9:
                r2 = 1
                byte r2 = r12.get(r2)
                int r2 = com.google.common.primitives.UnsignedBytes.toInt(r2)
                long r2 = (long) r2
                long r2 = r2 << r6
                long r0 = r0 ^ r2
                goto L_0x00d7
            L_0x00d6:
                r0 = r7
            L_0x00d7:
                r2 = 0
                byte r12 = r12.get(r2)
                int r12 = com.google.common.primitives.UnsignedBytes.toInt(r12)
                long r2 = (long) r12
                long r0 = r0 ^ r2
            L_0x00e2:
                long r2 = r11.h1
                long r0 = mixK1(r0)
                long r0 = r0 ^ r2
                r11.h1 = r0
                long r0 = r11.h2
                long r2 = mixK2(r7)
                long r0 = r0 ^ r2
                r11.h2 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.hash.Murmur3_128HashFunction.Murmur3_128Hasher.processRemaining(java.nio.ByteBuffer):void");
        }

        public HashCode makeHash() {
            long j = this.h1;
            int i = this.length;
            long j2 = this.h2 ^ ((long) i);
            long j3 = (j ^ ((long) i)) + j2;
            this.h1 = j3;
            this.h2 = j2 + j3;
            this.h1 = fmix64(j3);
            long fmix64 = fmix64(this.h2);
            long j4 = this.h1 + fmix64;
            this.h1 = j4;
            this.h2 = fmix64 + j4;
            return HashCode.fromBytesNoCopy(ByteBuffer.wrap(new byte[16]).order(ByteOrder.LITTLE_ENDIAN).putLong(this.h1).putLong(this.h2).array());
        }

        private static long mixK1(long j) {
            return Long.rotateLeft(j * C1, 31) * C2;
        }

        private static long mixK2(long j) {
            return Long.rotateLeft(j * C2, 33) * C1;
        }
    }
}
