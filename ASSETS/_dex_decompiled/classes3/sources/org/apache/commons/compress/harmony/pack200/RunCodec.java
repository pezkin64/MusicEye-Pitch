package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class RunCodec extends Codec {
    private final Codec aCodec;
    private final Codec bCodec;
    private int k;
    private int last;

    public RunCodec(int i, Codec codec, Codec codec2) throws Pack200Exception {
        if (i <= 0) {
            throw new Pack200Exception("Cannot have a RunCodec for a negative number of numbers");
        } else if (codec == null || codec2 == null) {
            throw new Pack200Exception("Must supply both codecs for a RunCodec");
        } else {
            this.k = i;
            this.aCodec = codec;
            this.bCodec = codec2;
        }
    }

    public int decode(InputStream inputStream) throws IOException, Pack200Exception {
        return decode(inputStream, (long) this.last);
    }

    public int decode(InputStream inputStream, long j) throws IOException, Pack200Exception {
        int i = this.k - 1;
        this.k = i;
        if (i >= 0) {
            int decode = this.aCodec.decode(inputStream, (long) this.last);
            this.last = this.k == 0 ? 0 : decode;
            return normalise(decode, this.aCodec);
        }
        int decode2 = this.bCodec.decode(inputStream, (long) this.last);
        this.last = decode2;
        return normalise(decode2, this.bCodec);
    }

    private int normalise(int i, Codec codec) {
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            if (bHSDCodec.isDelta()) {
                long cardinality = bHSDCodec.cardinality();
                while (true) {
                    long j = (long) i;
                    if (j <= bHSDCodec.largest()) {
                        break;
                    }
                    i = (int) (j - cardinality);
                }
                while (true) {
                    long j2 = (long) i;
                    if (j2 >= bHSDCodec.smallest()) {
                        break;
                    }
                    i = (int) (j2 + cardinality);
                }
            }
        }
        return i;
    }

    public int[] decodeInts(int i, InputStream inputStream) throws IOException, Pack200Exception {
        int[] iArr = new int[i];
        int[] decodeInts = this.aCodec.decodeInts(this.k, inputStream);
        normalise(decodeInts, this.aCodec);
        int[] decodeInts2 = this.bCodec.decodeInts(i - this.k, inputStream);
        normalise(decodeInts2, this.bCodec);
        System.arraycopy(decodeInts, 0, iArr, 0, this.k);
        int i2 = this.k;
        System.arraycopy(decodeInts2, 0, iArr, i2, i - i2);
        this.lastBandLength = this.aCodec.lastBandLength + this.bCodec.lastBandLength;
        return iArr;
    }

    private void normalise(int[] iArr, Codec codec) {
        int i = 0;
        if (codec instanceof BHSDCodec) {
            BHSDCodec bHSDCodec = (BHSDCodec) codec;
            if (bHSDCodec.isDelta()) {
                long cardinality = bHSDCodec.cardinality();
                while (i < iArr.length) {
                    while (((long) iArr[i]) > bHSDCodec.largest()) {
                        iArr[i] = (int) (((long) iArr[i]) - cardinality);
                    }
                    while (((long) iArr[i]) < bHSDCodec.smallest()) {
                        iArr[i] = (int) (((long) iArr[i]) + cardinality);
                    }
                    i++;
                }
            }
        } else if (codec instanceof PopulationCodec) {
            PopulationCodec populationCodec = (PopulationCodec) codec;
            int[] iArr2 = (int[]) populationCodec.getFavoured().clone();
            Arrays.sort(iArr2);
            while (i < iArr.length) {
                Codec favouredCodec = Arrays.binarySearch(iArr2, iArr[i]) > -1 ? populationCodec.getFavouredCodec() : populationCodec.getUnfavouredCodec();
                if (favouredCodec instanceof BHSDCodec) {
                    BHSDCodec bHSDCodec2 = (BHSDCodec) favouredCodec;
                    if (bHSDCodec2.isDelta()) {
                        long cardinality2 = bHSDCodec2.cardinality();
                        while (((long) iArr[i]) > bHSDCodec2.largest()) {
                            iArr[i] = (int) (((long) iArr[i]) - cardinality2);
                        }
                        while (((long) iArr[i]) < bHSDCodec2.smallest()) {
                            iArr[i] = (int) (((long) iArr[i]) + cardinality2);
                        }
                    }
                }
                i++;
            }
        }
    }

    public String toString() {
        return "RunCodec[k=" + this.k + ";aCodec=" + this.aCodec + "bCodec=" + this.bCodec + "]";
    }

    public byte[] encode(int i, int i2) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    public byte[] encode(int i) throws Pack200Exception {
        throw new Pack200Exception("Must encode entire band at once with a RunCodec");
    }

    public int getK() {
        return this.k;
    }

    public Codec getACodec() {
        return this.aCodec;
    }

    public Codec getBCodec() {
        return this.bCodec;
    }
}
