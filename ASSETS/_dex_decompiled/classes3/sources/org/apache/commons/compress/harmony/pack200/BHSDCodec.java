package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public final class BHSDCodec extends Codec {
    private final int b;
    private long cardinality;
    private final int d;
    private final int h;
    private final int l;
    private final long largest;
    private final long[] powers;
    private final int s;
    private final long smallest;

    public BHSDCodec(int i, int i2) {
        this(i, i2, 0, 0);
    }

    public BHSDCodec(int i, int i2, int i3) {
        this(i, i2, i3, 0);
    }

    public BHSDCodec(int i, int i2, int i3, int i4) {
        if (i < 1 || i > 5) {
            throw new IllegalArgumentException("1<=b<=5");
        } else if (i2 < 1 || i2 > 256) {
            throw new IllegalArgumentException("1<=h<=256");
        } else if (i3 < 0 || i3 > 2) {
            throw new IllegalArgumentException("0<=s<=2");
        } else if (i4 < 0 || i4 > 1) {
            throw new IllegalArgumentException("0<=d<=1");
        } else if (i == 1 && i2 != 256) {
            throw new IllegalArgumentException("b=1 -> h=256");
        } else if (i2 == 256 && i == 5) {
            throw new IllegalArgumentException("h=256 -> b!=5");
        } else {
            this.b = i;
            this.h = i2;
            this.s = i3;
            this.d = i4;
            int i5 = 256 - i2;
            this.l = i5;
            if (i2 == 1) {
                this.cardinality = (long) ((i * 255) + 1);
            } else {
                double d2 = (double) i2;
                double d3 = (double) i;
                this.cardinality = (long) (((double) ((long) ((((double) i5) * (1.0d - Math.pow(d2, d3))) / ((double) (1 - i2))))) + Math.pow(d2, d3));
            }
            this.smallest = calculateSmallest();
            this.largest = calculateLargest();
            this.powers = new long[i];
            for (int i6 = 0; i6 < i; i6++) {
                this.powers[i6] = (long) Math.pow((double) i2, (double) i6);
            }
        }
    }

    public long cardinality() {
        return this.cardinality;
    }

    public int decode(InputStream inputStream) throws IOException, Pack200Exception {
        if (this.d == 0) {
            return decode(inputStream, 0);
        }
        throw new Pack200Exception("Delta encoding used without passing in last value; this is a coding error");
    }

    public int decode(InputStream inputStream, long j) throws IOException, Pack200Exception {
        long read;
        int i = 0;
        long j2 = 0;
        do {
            read = (long) inputStream.read();
            this.lastBandLength++;
            j2 += this.powers[i] * read;
            i++;
            if (read < ((long) this.l) || i >= this.b) {
            }
            read = (long) inputStream.read();
            this.lastBandLength++;
            j2 += this.powers[i] * read;
            i++;
            break;
        } while (i >= this.b);
        if (read != -1) {
            if (isSigned()) {
                int i2 = this.s;
                long j3 = (long) ((1 << i2) - 1);
                j2 = (j2 & j3) == j3 ? ~(j2 >>> i2) : j2 - (j2 >>> i2);
            }
            if (isDelta()) {
                j2 += j;
            }
            return (int) j2;
        }
        throw new EOFException("End of stream reached whilst decoding");
    }

    public int[] decodeInts(int i, InputStream inputStream) throws IOException, Pack200Exception {
        int[] decodeInts = super.decodeInts(i, inputStream);
        if (isDelta()) {
            for (int i2 = 0; i2 < decodeInts.length; i2++) {
                while (true) {
                    int i3 = decodeInts[i2];
                    if (((long) i3) <= this.largest) {
                        break;
                    }
                    decodeInts[i2] = (int) (((long) i3) - this.cardinality);
                }
                while (true) {
                    int i4 = decodeInts[i2];
                    if (((long) i4) >= this.smallest) {
                        break;
                    }
                    decodeInts[i2] = (int) (((long) i4) + this.cardinality);
                }
            }
        }
        return decodeInts;
    }

    public int[] decodeInts(int i, InputStream inputStream, int i2) throws IOException, Pack200Exception {
        int[] decodeInts = super.decodeInts(i, inputStream, i2);
        if (isDelta()) {
            for (int i3 = 0; i3 < decodeInts.length; i3++) {
                while (true) {
                    int i4 = decodeInts[i3];
                    if (((long) i4) <= this.largest) {
                        break;
                    }
                    decodeInts[i3] = (int) (((long) i4) - this.cardinality);
                }
                while (true) {
                    int i5 = decodeInts[i3];
                    if (((long) i5) >= this.smallest) {
                        break;
                    }
                    decodeInts[i3] = (int) (((long) i5) + this.cardinality);
                }
            }
        }
        return decodeInts;
    }

    public boolean encodes(long j) {
        return j >= this.smallest && j <= this.largest;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encode(int r8, int r9) throws org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            r7 = this;
            long r0 = (long) r8
            boolean r2 = r7.encodes(r0)
            if (r2 == 0) goto L_0x00b8
            boolean r8 = r7.isDelta()
            if (r8 == 0) goto L_0x000f
            long r8 = (long) r9
            long r0 = r0 - r8
        L_0x000f:
            boolean r8 = r7.isSigned()
            r2 = 0
            r4 = 4294967296(0x100000000, double:2.121995791E-314)
            if (r8 == 0) goto L_0x0049
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 >= 0) goto L_0x0025
            long r0 = r0 + r4
            goto L_0x002d
        L_0x0025:
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 <= 0) goto L_0x002d
            long r0 = r0 - r4
        L_0x002d:
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x003a
            long r8 = -r0
            int r0 = r7.s
            long r8 = r8 << r0
            r0 = 1
            long r0 = r8 - r0
            goto L_0x0056
        L_0x003a:
            int r8 = r7.s
            r9 = 1
            if (r8 != r9) goto L_0x0041
            long r0 = r0 << r8
            goto L_0x0056
        L_0x0041:
            r8 = 3
            long r4 = r0 % r8
            long r4 = r0 - r4
            long r4 = r4 / r8
            goto L_0x0055
        L_0x0049:
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 >= 0) goto L_0x0056
            long r8 = r7.cardinality
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x0055
            long r0 = r0 + r8
            goto L_0x0056
        L_0x0055:
            long r0 = r0 + r4
        L_0x0056:
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 < 0) goto L_0x00b0
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r9 = 0
            r2 = r9
        L_0x0061:
            int r3 = r7.b
            if (r2 >= r3) goto L_0x0098
            int r3 = r7.l
            long r3 = (long) r3
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x006e
            r3 = r0
            goto L_0x007f
        L_0x006e:
            int r3 = r7.h
            long r3 = (long) r3
            long r3 = r0 % r3
        L_0x0073:
            int r5 = r7.l
            long r5 = (long) r5
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x007f
            int r5 = r7.h
            long r5 = (long) r5
            long r3 = r3 + r5
            goto L_0x0073
        L_0x007f:
            int r5 = (int) r3
            byte r5 = (byte) r5
            java.lang.Byte r5 = java.lang.Byte.valueOf(r5)
            r8.add(r5)
            int r5 = r7.l
            long r5 = (long) r5
            int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x0090
            goto L_0x0098
        L_0x0090:
            long r0 = r0 - r3
            int r3 = r7.h
            long r3 = (long) r3
            long r0 = r0 / r3
            int r2 = r2 + 1
            goto L_0x0061
        L_0x0098:
            int r0 = r8.size()
            byte[] r1 = new byte[r0]
        L_0x009e:
            if (r9 >= r0) goto L_0x00af
            java.lang.Object r2 = r8.get(r9)
            java.lang.Byte r2 = (java.lang.Byte) r2
            byte r2 = r2.byteValue()
            r1[r9] = r2
            int r9 = r9 + 1
            goto L_0x009e
        L_0x00af:
            return r1
        L_0x00b0:
            org.apache.commons.compress.harmony.pack200.Pack200Exception r8 = new org.apache.commons.compress.harmony.pack200.Pack200Exception
            java.lang.String r9 = "unable to encode"
            r8.<init>(r9)
            throw r8
        L_0x00b8:
            org.apache.commons.compress.harmony.pack200.Pack200Exception r9 = new org.apache.commons.compress.harmony.pack200.Pack200Exception
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "The codec "
            r0.<init>(r1)
            java.lang.String r1 = r7.toString()
            r0.append(r1)
            java.lang.String r1 = " does not encode the value "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BHSDCodec.encode(int, int):byte[]");
    }

    public byte[] encode(int i) throws Pack200Exception {
        return encode(i, 0);
    }

    public boolean isDelta() {
        return this.d != 0;
    }

    public boolean isSigned() {
        return this.s != 0;
    }

    public long largest() {
        return this.largest;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private long calculateLargest() {
        /*
            r6 = this;
            int r0 = r6.d
            r1 = 1
            if (r0 != r1) goto L_0x0013
            org.apache.commons.compress.harmony.pack200.BHSDCodec r0 = new org.apache.commons.compress.harmony.pack200.BHSDCodec
            int r1 = r6.b
            int r2 = r6.h
            r0.<init>(r1, r2)
            long r0 = r0.largest()
            return r0
        L_0x0013:
            int r0 = r6.s
            r2 = 1
            if (r0 != 0) goto L_0x001f
            long r0 = r6.cardinality()
        L_0x001d:
            long r0 = r0 - r2
            goto L_0x0038
        L_0x001f:
            if (r0 != r1) goto L_0x0029
            long r0 = r6.cardinality()
            r4 = 2
            long r0 = r0 / r4
            goto L_0x001d
        L_0x0029:
            r1 = 2
            if (r0 != r1) goto L_0x004b
            r0 = 3
            long r4 = r6.cardinality()
            long r4 = r4 * r0
            r0 = 4
            long r4 = r4 / r0
            long r0 = r4 - r2
        L_0x0038:
            int r4 = r6.s
            if (r4 != 0) goto L_0x0042
            r4 = 4294967294(0xfffffffe, double:2.12199579E-314)
            goto L_0x0045
        L_0x0042:
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x0045:
            long r4 = r4 - r2
            long r0 = java.lang.Math.min(r4, r0)
            return r0
        L_0x004b:
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "Unknown s value"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BHSDCodec.calculateLargest():long");
    }

    public long smallest() {
        return this.smallest;
    }

    private long calculateSmallest() {
        if (this.d != 1 && isSigned()) {
            return Math.max(-2147483648L, (-cardinality()) / ((long) (1 << this.s)));
        }
        if (this.cardinality >= 4294967296L) {
            return -2147483648L;
        }
        return 0;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(11);
        stringBuffer.append('(');
        stringBuffer.append(this.b);
        stringBuffer.append(',');
        stringBuffer.append(this.h);
        if (!(this.s == 0 && this.d == 0)) {
            stringBuffer.append(',');
            stringBuffer.append(this.s);
        }
        if (this.d != 0) {
            stringBuffer.append(',');
            stringBuffer.append(this.d);
        }
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public int getB() {
        return this.b;
    }

    public int getH() {
        return this.h;
    }

    public int getS() {
        return this.s;
    }

    public int getL() {
        return this.l;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) obj;
            if (bHSDCodec.b == this.b && bHSDCodec.h == this.h && bHSDCodec.s == this.s && bHSDCodec.d == this.d) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return (((((this.b * 37) + this.h) * 37) + this.s) * 37) + this.d;
    }
}
