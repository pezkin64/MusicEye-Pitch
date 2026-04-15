package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.CodecEncoding;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

public abstract class BandSet {
    protected SegmentHeader header;
    protected Segment segment;

    public abstract void read(InputStream inputStream) throws IOException, Pack200Exception;

    public abstract void unpack() throws IOException, Pack200Exception;

    public void unpack(InputStream inputStream) throws IOException, Pack200Exception {
        read(inputStream);
        unpack();
    }

    public BandSet(Segment segment2) {
        this.segment = segment2;
        this.header = segment2.getSegmentHeader();
    }

    public int[] decodeBandInt(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        Codec codec;
        int[] iArr;
        if (bHSDCodec.getB() == 1 || i == 0) {
            return bHSDCodec.decodeInts(i, inputStream);
        }
        int[] decodeInts = bHSDCodec.decodeInts(1, inputStream);
        if (decodeInts.length == 0) {
            return decodeInts;
        }
        int i2 = decodeInts[0];
        if (bHSDCodec.isSigned() && i2 >= -256 && i2 <= -1) {
            Codec codec2 = CodecEncoding.getCodec(-1 - i2, this.header.getBandHeadersInputStream(), bHSDCodec);
            iArr = codec2.decodeInts(i, inputStream);
            codec = codec2;
        } else if (bHSDCodec.isSigned() || i2 < bHSDCodec.getL() || i2 > bHSDCodec.getL() + 255) {
            iArr = bHSDCodec.decodeInts(i - 1, inputStream, i2);
            codec = bHSDCodec;
        } else {
            Codec codec3 = CodecEncoding.getCodec(i2 - bHSDCodec.getL(), this.header.getBandHeadersInputStream(), bHSDCodec);
            iArr = codec3.decodeInts(i, inputStream);
            codec = codec3;
        }
        if (codec instanceof PopulationCodec) {
            PopulationCodec populationCodec = (PopulationCodec) codec;
            int[] iArr2 = (int[]) populationCodec.getFavoured().clone();
            Arrays.sort(iArr2);
            for (int i3 = 0; i3 < iArr.length; i3++) {
                Codec favouredCodec = Arrays.binarySearch(iArr2, iArr[i3]) > -1 ? populationCodec.getFavouredCodec() : populationCodec.getUnfavouredCodec();
                if (favouredCodec instanceof BHSDCodec) {
                    BHSDCodec bHSDCodec2 = (BHSDCodec) favouredCodec;
                    if (bHSDCodec2.isDelta()) {
                        long cardinality = bHSDCodec2.cardinality();
                        while (((long) iArr[i3]) > bHSDCodec2.largest()) {
                            iArr[i3] = (int) (((long) iArr[i3]) - cardinality);
                        }
                        while (((long) iArr[i3]) < bHSDCodec2.smallest()) {
                            iArr[i3] = (int) (((long) iArr[i3]) + cardinality);
                        }
                    }
                }
            }
        }
        return iArr;
    }

    public int[][] decodeBandInt(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws IOException, Pack200Exception {
        int length = iArr.length;
        int[][] iArr2 = new int[length][];
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            iArr2[i4] = new int[iArr[i4]];
            int i5 = 0;
            while (true) {
                int[] iArr3 = iArr2[i4];
                if (i5 >= iArr3.length) {
                    break;
                }
                iArr3[i5] = decodeBandInt[i3];
                i3++;
                i5++;
            }
        }
        return iArr2;
    }

    public long[] parseFlags(String str, InputStream inputStream, int i, BHSDCodec bHSDCodec, boolean z) throws IOException, Pack200Exception {
        BHSDCodec bHSDCodec2;
        BHSDCodec bHSDCodec3;
        int[] iArr = {i};
        if (z) {
            bHSDCodec3 = bHSDCodec;
            bHSDCodec2 = bHSDCodec3;
        } else {
            bHSDCodec3 = null;
            bHSDCodec2 = bHSDCodec;
        }
        return parseFlags(str, inputStream, iArr, bHSDCodec3, bHSDCodec2)[0];
    }

    public long[][] parseFlags(String str, InputStream inputStream, int[] iArr, BHSDCodec bHSDCodec, boolean z) throws IOException, Pack200Exception {
        BHSDCodec bHSDCodec2;
        BHSDCodec bHSDCodec3;
        if (z) {
            bHSDCodec3 = bHSDCodec;
            bHSDCodec2 = bHSDCodec3;
        } else {
            bHSDCodec2 = bHSDCodec;
            bHSDCodec3 = null;
        }
        return parseFlags(str, inputStream, iArr, bHSDCodec3, bHSDCodec2);
    }

    public long[] parseFlags(String str, InputStream inputStream, int i, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2) throws IOException, Pack200Exception {
        return parseFlags(str, inputStream, new int[]{i}, bHSDCodec, bHSDCodec2)[0];
    }

    public long[][] parseFlags(String str, InputStream inputStream, int[] iArr, BHSDCodec bHSDCodec, BHSDCodec bHSDCodec2) throws IOException, Pack200Exception {
        int[] iArr2;
        int[] iArr3;
        int length = iArr.length;
        if (length == 0) {
            return new long[][]{new long[0]};
        }
        long[][] jArr = new long[length][];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            jArr[i2] = new long[i3];
            i += i3;
        }
        if (bHSDCodec != null) {
            iArr2 = decodeBandInt(str, inputStream, bHSDCodec, i);
            iArr3 = decodeBandInt(str, inputStream, bHSDCodec2, i);
        } else {
            iArr3 = decodeBandInt(str, inputStream, bHSDCodec2, i);
            iArr2 = null;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < length; i5++) {
            int i6 = 0;
            while (true) {
                long[] jArr2 = jArr[i5];
                if (i6 >= jArr2.length) {
                    break;
                }
                if (iArr2 != null) {
                    jArr2[i6] = (((long) iArr2[i4]) << 32) | (((long) iArr3[i4]) & 4294967295L);
                } else {
                    jArr2[i6] = (long) iArr3[i4];
                }
                i4++;
                i6++;
            }
        }
        return jArr;
    }

    public String[] parseReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i, String[] strArr) throws IOException, Pack200Exception {
        return parseReferences(str, inputStream, bHSDCodec, new int[]{i}, strArr)[0];
    }

    public String[][] parseReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr, String[] strArr) throws IOException, Pack200Exception {
        int length = iArr.length;
        if (length == 0) {
            return new String[][]{new String[0]};
        }
        String[][] strArr2 = new String[length][];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArr[i2];
            strArr2[i2] = new String[i3];
            i += i3;
        }
        String[] strArr3 = new String[i];
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = decodeBandInt[i4];
            if (i5 < 0 || i5 >= strArr.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i5 + ", array size = " + strArr.length);
            }
            strArr3[i4] = strArr[i5];
        }
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = iArr[i7];
            String[] strArr4 = new String[i8];
            strArr2[i7] = strArr4;
            System.arraycopy(strArr3, i6, strArr4, 0, i8);
            i6 += i8;
        }
        return strArr2;
    }

    public CPInteger[] parseCPIntReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] cpInt = this.segment.getCpBands().getCpInt();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPInteger[] cPIntegerArr = new CPInteger[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = decodeBandInt[i2];
            if (i3 < 0 || i3 >= cpInt.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i3 + ", array size = " + cpInt.length);
            }
            cPIntegerArr[i2] = this.segment.getCpBands().cpIntegerValue(i3);
        }
        return cPIntegerArr;
    }

    public CPDouble[] parseCPDoubleReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPDouble[] cPDoubleArr = new CPDouble[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPDoubleArr[i2] = this.segment.getCpBands().cpDoubleValue(decodeBandInt[i2]);
        }
        return cPDoubleArr;
    }

    public CPFloat[] parseCPFloatReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPFloat[] cPFloatArr = new CPFloat[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPFloatArr[i2] = this.segment.getCpBands().cpFloatValue(decodeBandInt[i2]);
        }
        return cPFloatArr;
    }

    public CPLong[] parseCPLongReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        long[] cpLong = this.segment.getCpBands().getCpLong();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPLong[] cPLongArr = new CPLong[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = decodeBandInt[i2];
            if (i3 < 0 || i3 >= cpLong.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + i3 + ", array size = " + cpLong.length);
            }
            cPLongArr[i2] = this.segment.getCpBands().cpLongValue(i3);
        }
        return cPLongArr;
    }

    public CPUTF8[] parseCPUTF8References(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPUTF8[] cputf8Arr = new CPUTF8[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cputf8Arr[i2] = this.segment.getCpBands().cpUTF8Value(decodeBandInt[i2]);
        }
        return cputf8Arr;
    }

    public CPUTF8[][] parseCPUTF8References(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws IOException, Pack200Exception {
        CPUTF8[][] cputf8Arr = new CPUTF8[iArr.length][];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            cputf8Arr[i2] = new CPUTF8[i3];
            i += i3;
        }
        CPUTF8[] cputf8Arr2 = new CPUTF8[i];
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            cputf8Arr2[i4] = this.segment.getCpBands().cpUTF8Value(decodeBandInt[i4]);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            CPUTF8[] cputf8Arr3 = new CPUTF8[i7];
            cputf8Arr[i6] = cputf8Arr3;
            System.arraycopy(cputf8Arr2, i5, cputf8Arr3, 0, i7);
            i5 += i7;
        }
        return cputf8Arr;
    }

    public CPString[] parseCPStringReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPString[] cPStringArr = new CPString[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPStringArr[i2] = this.segment.getCpBands().cpStringValue(decodeBandInt[i2]);
        }
        return cPStringArr;
    }

    public CPInterfaceMethodRef[] parseCPInterfaceMethodRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPInterfaceMethodRef[] cPInterfaceMethodRefArr = new CPInterfaceMethodRef[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPInterfaceMethodRefArr[i2] = cpBands.cpIMethodValue(decodeBandInt[i2]);
        }
        return cPInterfaceMethodRefArr;
    }

    public CPMethodRef[] parseCPMethodRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPMethodRef[] cPMethodRefArr = new CPMethodRef[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPMethodRefArr[i2] = cpBands.cpMethodValue(decodeBandInt[i2]);
        }
        return cPMethodRefArr;
    }

    public CPFieldRef[] parseCPFieldRefReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPFieldRef[] cPFieldRefArr = new CPFieldRef[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPFieldRefArr[i2] = cpBands.cpFieldValue(decodeBandInt[i2]);
        }
        return cPFieldRefArr;
    }

    public CPNameAndType[] parseCPDescriptorReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPNameAndType[] cPNameAndTypeArr = new CPNameAndType[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPNameAndTypeArr[i2] = cpBands.cpNameAndTypeValue(decodeBandInt[i2]);
        }
        return cPNameAndTypeArr;
    }

    public CPUTF8[] parseCPSignatureReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPUTF8[] cputf8Arr = new CPUTF8[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cputf8Arr[i2] = this.segment.getCpBands().cpSignatureValue(decodeBandInt[i2]);
        }
        return cputf8Arr;
    }

    /* access modifiers changed from: protected */
    public CPUTF8[][] parseCPSignatureReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int[] iArr) throws IOException, Pack200Exception {
        CPUTF8[][] cputf8Arr = new CPUTF8[iArr.length][];
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            cputf8Arr[i2] = new CPUTF8[i3];
            i += i3;
        }
        CPUTF8[] cputf8Arr2 = new CPUTF8[i];
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        for (int i4 = 0; i4 < i; i4++) {
            cputf8Arr2[i4] = this.segment.getCpBands().cpSignatureValue(decodeBandInt[i4]);
        }
        int i5 = 0;
        for (int i6 = 0; i6 < iArr.length; i6++) {
            int i7 = iArr[i6];
            CPUTF8[] cputf8Arr3 = new CPUTF8[i7];
            cputf8Arr[i6] = cputf8Arr3;
            System.arraycopy(cputf8Arr2, i5, cputf8Arr3, 0, i7);
            i5 += i7;
        }
        return cputf8Arr;
    }

    public CPClass[] parseCPClassReferences(String str, InputStream inputStream, BHSDCodec bHSDCodec, int i) throws IOException, Pack200Exception {
        int[] decodeBandInt = decodeBandInt(str, inputStream, bHSDCodec, i);
        CPClass[] cPClassArr = new CPClass[decodeBandInt.length];
        for (int i2 = 0; i2 < i; i2++) {
            cPClassArr[i2] = this.segment.getCpBands().cpClassValue(decodeBandInt[i2]);
        }
        return cPClassArr;
    }

    /* access modifiers changed from: protected */
    public String[] getReferences(int[] iArr, String[] strArr) {
        int length = iArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = strArr[iArr[i]];
        }
        return strArr2;
    }

    /* access modifiers changed from: protected */
    public String[][] getReferences(int[][] iArr, String[] strArr) {
        int length = iArr.length;
        String[][] strArr2 = new String[length][];
        for (int i = 0; i < length; i++) {
            strArr2[i] = new String[iArr[i].length];
            int i2 = 0;
            while (true) {
                String[] strArr3 = strArr2[i];
                if (i2 >= strArr3.length) {
                    break;
                }
                strArr3[i2] = strArr[iArr[i][i2]];
                i2++;
            }
        }
        return strArr2;
    }
}
