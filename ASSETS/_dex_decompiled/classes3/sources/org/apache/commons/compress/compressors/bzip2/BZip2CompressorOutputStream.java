package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import jp.kshoji.javax.sound.midi.ShortMessage;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class BZip2CompressorOutputStream extends CompressorOutputStream implements BZip2Constants {
    private static final int GREATER_ICOST = 15;
    private static final int LESSER_ICOST = 0;
    public static final int MAX_BLOCKSIZE = 9;
    public static final int MIN_BLOCKSIZE = 1;
    private final int allowableBlockSize;
    private int blockCRC;
    private final int blockSize100k;
    private BlockSort blockSorter;
    private int bsBuff;
    private int bsLive;
    private volatile boolean closed;
    private int combinedCRC;
    private final CRC crc;
    private int currentChar;
    private Data data;
    private int last;
    private int nInUse;
    private int nMTF;
    private OutputStream out;
    private int runLength;

    private static void hbMakeCodeLengths(byte[] bArr, int[] iArr, Data data2, int i, int i2) {
        int i3;
        int i4;
        Data data3 = data2;
        int i5 = i;
        int[] iArr2 = data3.heap;
        int[] iArr3 = data3.weight;
        int[] iArr4 = data3.parent;
        int i6 = i5;
        while (true) {
            int i7 = i6 - 1;
            i3 = 1;
            if (i7 < 0) {
                break;
            }
            int i8 = iArr[i7];
            if (i8 != 0) {
                i3 = i8;
            }
            iArr3[i6] = i3 << 8;
            i6 = i7;
        }
        int i9 = 1;
        while (i9 != 0) {
            iArr2[0] = 0;
            iArr3[0] = 0;
            iArr4[0] = -2;
            int i10 = 0;
            for (int i11 = i3; i11 <= i5; i11++) {
                iArr4[i11] = -1;
                i10++;
                iArr2[i10] = i11;
                int i12 = i10;
                while (true) {
                    int i13 = iArr3[i11];
                    int i14 = i12 >> 1;
                    int i15 = iArr2[i14];
                    if (i13 >= iArr3[i15]) {
                        break;
                    }
                    iArr2[i12] = i15;
                    i12 = i14;
                }
                iArr2[i12] = i11;
            }
            int i16 = i5;
            while (i10 > i3) {
                int i17 = iArr2[i3];
                int i18 = iArr2[i10];
                iArr2[i3] = i18;
                int i19 = i10 - 1;
                int i20 = i3;
                while (true) {
                    int i21 = i20 << 1;
                    if (i21 > i19) {
                        break;
                    }
                    if (i21 < i19) {
                        int i22 = i21 + 1;
                        if (iArr3[iArr2[i22]] < iArr3[iArr2[i21]]) {
                            i21 = i22;
                        }
                    }
                    int i23 = iArr3[i18];
                    int i24 = iArr2[i21];
                    if (i23 < iArr3[i24]) {
                        break;
                    }
                    int i25 = i3;
                    iArr2[i20] = i24;
                    i20 = i21;
                }
                iArr2[i20] = i18;
                int i26 = iArr2[i3];
                int i27 = iArr2[i19];
                iArr2[i3] = i27;
                int i28 = i10 - 2;
                int i29 = i3;
                while (true) {
                    int i30 = i29 << 1;
                    if (i30 > i28) {
                        i4 = i3;
                        break;
                    }
                    if (i30 < i28) {
                        int i31 = i30 + 1;
                        i4 = i3;
                        if (iArr3[iArr2[i31]] < iArr3[iArr2[i30]]) {
                            i30 = i31;
                        }
                    } else {
                        i4 = i3;
                    }
                    int i32 = iArr3[i27];
                    int i33 = iArr2[i30];
                    if (i32 < iArr3[i33]) {
                        break;
                    }
                    iArr2[i29] = i33;
                    i3 = i4;
                    i29 = i30;
                }
                iArr2[i29] = i27;
                i16++;
                iArr4[i26] = i16;
                iArr4[i17] = i16;
                int i34 = iArr3[i17];
                int i35 = iArr3[i26];
                int i36 = (i34 & -256) + (i35 & -256);
                int i37 = i34 & 255;
                int i38 = i35 & 255;
                if (i37 <= i38) {
                    i37 = i38;
                }
                iArr3[i16] = i36 | (i37 + 1);
                iArr4[i16] = -1;
                i10--;
                iArr2[i10] = i16;
                int i39 = iArr3[i16];
                int i40 = i10;
                while (true) {
                    int i41 = i40 >> 1;
                    int i42 = iArr2[i41];
                    if (i39 >= iArr3[i42]) {
                        break;
                    }
                    iArr2[i40] = i42;
                    i40 = i41;
                }
                iArr2[i40] = i16;
                i3 = i4;
            }
            int i43 = i3;
            i9 = 0;
            for (int i44 = i43; i44 <= i5; i44++) {
                int i45 = i44;
                int i46 = 0;
                while (true) {
                    i45 = iArr4[i45];
                    if (i45 < 0) {
                        break;
                    }
                    i46++;
                }
                bArr[i44 - 1] = (byte) i46;
                if (i46 > i2) {
                    i9 = i43;
                }
            }
            int i47 = i2;
            if (i9 != 0) {
                for (int i48 = i43; i48 < i5; i48++) {
                    iArr3[i48] = ((iArr3[i48] >> 9) + 1) << 8;
                }
            }
            i3 = i43;
        }
    }

    public static int chooseBlockSize(long j) {
        if (j > 0) {
            return (int) Math.min((j / 132000) + 1, 9);
        }
        return 9;
    }

    public BZip2CompressorOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, 9);
    }

    public BZip2CompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.crc = new CRC();
        this.currentChar = -1;
        if (i < 1) {
            throw new IllegalArgumentException("blockSize(" + i + ") < 1");
        } else if (i <= 9) {
            this.blockSize100k = i;
            this.out = outputStream;
            this.allowableBlockSize = (i * BZip2Constants.BASEBLOCKSIZE) - 20;
            init();
        } else {
            throw new IllegalArgumentException("blockSize(" + i + ") > 9");
        }
    }

    public void write(int i) throws IOException {
        if (!this.closed) {
            write0(i);
            return;
        }
        throw new IOException("Closed");
    }

    private void writeRun() throws IOException {
        int i = this.last;
        if (i < this.allowableBlockSize) {
            int i2 = this.currentChar;
            Data data2 = this.data;
            data2.inUse[i2] = true;
            byte b = (byte) i2;
            int i3 = this.runLength;
            this.crc.updateCRC(i2, i3);
            if (i3 == 1) {
                data2.block[i + 2] = b;
                this.last = i + 1;
            } else if (i3 == 2) {
                int i4 = i + 2;
                data2.block[i4] = b;
                data2.block[i + 3] = b;
                this.last = i4;
            } else if (i3 != 3) {
                int i5 = i3 - 4;
                data2.inUse[i5] = true;
                byte[] bArr = data2.block;
                bArr[i + 2] = b;
                bArr[i + 3] = b;
                bArr[i + 4] = b;
                int i6 = i + 5;
                bArr[i6] = b;
                bArr[i + 6] = (byte) i5;
                this.last = i6;
            } else {
                byte[] bArr2 = data2.block;
                bArr2[i + 2] = b;
                int i7 = i + 3;
                bArr2[i7] = b;
                bArr2[i + 4] = b;
                this.last = i7;
            }
        } else {
            endBlock();
            initBlock();
            writeRun();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (!this.closed) {
            System.err.println("Unclosed BZip2CompressorOutputStream detected, will *not* close it");
        }
        super.finalize();
    }

    public void finish() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                if (this.runLength > 0) {
                    writeRun();
                }
                this.currentChar = -1;
                endBlock();
                endCompression();
            } finally {
                this.out = null;
                this.blockSorter = null;
                this.data = null;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0011, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0012, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0019, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x001c, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void close() throws java.io.IOException {
        /*
            r3 = this;
            boolean r0 = r3.closed
            if (r0 != 0) goto L_0x001d
            java.io.OutputStream r0 = r3.out
            r3.finish()     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x001d
            r0.close()
            return
        L_0x000f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ all -> 0x0018 }
            goto L_0x001c
        L_0x0018:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001c:
            throw r2
        L_0x001d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.close():void");
    }

    public void flush() throws IOException {
        OutputStream outputStream = this.out;
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    private void init() throws IOException {
        bsPutUByte(66);
        bsPutUByte(90);
        this.data = new Data(this.blockSize100k);
        this.blockSorter = new BlockSort(this.data);
        bsPutUByte(104);
        bsPutUByte(this.blockSize100k + 48);
        this.combinedCRC = 0;
        initBlock();
    }

    private void initBlock() {
        this.crc.initializeCRC();
        this.last = -1;
        boolean[] zArr = this.data.inUse;
        int i = 256;
        while (true) {
            i--;
            if (i >= 0) {
                zArr[i] = false;
            } else {
                return;
            }
        }
    }

    private void endBlock() throws IOException {
        int finalCRC = this.crc.getFinalCRC();
        this.blockCRC = finalCRC;
        int i = this.combinedCRC;
        this.combinedCRC = finalCRC ^ ((i >>> 31) | (i << 1));
        if (this.last != -1) {
            blockSort();
            bsPutUByte(49);
            bsPutUByte(65);
            bsPutUByte(89);
            bsPutUByte(38);
            bsPutUByte(83);
            bsPutUByte(89);
            bsPutInt(this.blockCRC);
            bsW(1, 0);
            moveToFrontCodeAndSend();
        }
    }

    private void endCompression() throws IOException {
        bsPutUByte(23);
        bsPutUByte(114);
        bsPutUByte(69);
        bsPutUByte(56);
        bsPutUByte(80);
        bsPutUByte(ShortMessage.NOTE_ON);
        bsPutInt(this.combinedCRC);
        bsFinishedWithStream();
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0) {
            throw new IndexOutOfBoundsException("offs(" + i + ") < 0.");
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 > bArr.length) {
                throw new IndexOutOfBoundsException("offs(" + i + ") + len(" + i2 + ") > buf.length(" + bArr.length + ").");
            } else if (!this.closed) {
                while (i < i3) {
                    write0(bArr[i]);
                    i++;
                }
            } else {
                throw new IOException("Stream closed");
            }
        } else {
            throw new IndexOutOfBoundsException("len(" + i2 + ") < 0.");
        }
    }

    private void write0(int i) throws IOException {
        int i2 = this.currentChar;
        if (i2 != -1) {
            int i3 = i & 255;
            if (i2 == i3) {
                int i4 = this.runLength + 1;
                this.runLength = i4;
                if (i4 > 254) {
                    writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                    return;
                }
                return;
            }
            writeRun();
            this.runLength = 1;
            this.currentChar = i3;
            return;
        }
        this.currentChar = i & 255;
        this.runLength++;
    }

    private static void hbAssignCodes(int[] iArr, byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i <= i2) {
            for (int i5 = 0; i5 < i3; i5++) {
                if ((bArr[i5] & 255) == i) {
                    iArr[i5] = i4;
                    i4++;
                }
            }
            i4 <<= 1;
            i++;
        }
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            this.out.write(this.bsBuff >> 24);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    private void bsW(int i, int i2) throws IOException {
        OutputStream outputStream = this.out;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        while (i3 >= 8) {
            outputStream.write(i4 >> 24);
            i4 <<= 8;
            i3 -= 8;
        }
        this.bsBuff = (i2 << ((32 - i3) - i)) | i4;
        this.bsLive = i3 + i;
    }

    private void bsPutUByte(int i) throws IOException {
        bsW(8, i);
    }

    private void bsPutInt(int i) throws IOException {
        bsW(8, (i >> 24) & 255);
        bsW(8, (i >> 16) & 255);
        bsW(8, (i >> 8) & 255);
        bsW(8, i & 255);
    }

    private void sendMTFValues() throws IOException {
        byte[][] bArr = this.data.sendMTFValues_len;
        int i = 2;
        int i2 = this.nInUse + 2;
        int i3 = 6;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            byte[] bArr2 = bArr[i3];
            int i4 = i2;
            while (true) {
                i4--;
                if (i4 >= 0) {
                    bArr2[i4] = 15;
                }
            }
        }
        int i5 = this.nMTF;
        if (i5 >= 200) {
            i = i5 < 600 ? 3 : i5 < 1200 ? 4 : i5 < 2400 ? 5 : 6;
        }
        sendMTFValues0(i, i2);
        int sendMTFValues1 = sendMTFValues1(i, i2);
        sendMTFValues2(i, sendMTFValues1);
        sendMTFValues3(i, i2);
        sendMTFValues4();
        sendMTFValues5(i, sendMTFValues1);
        sendMTFValues6(i, i2);
        sendMTFValues7();
    }

    private void sendMTFValues0(int i, int i2) {
        byte[][] bArr = this.data.sendMTFValues_len;
        int[] iArr = this.data.mtfFreq;
        int i3 = this.nMTF;
        int i4 = 0;
        for (int i5 = i; i5 > 0; i5--) {
            int i6 = i3 / i5;
            int i7 = i4 - 1;
            int i8 = i2 - 1;
            int i9 = 0;
            while (i9 < i6 && i7 < i8) {
                i7++;
                i9 += iArr[i7];
            }
            if (!(i7 <= i4 || i5 == i || i5 == 1 || (1 & (i - i5)) == 0)) {
                i9 -= iArr[i7];
                i7--;
            }
            byte[] bArr2 = bArr[i5 - 1];
            int i10 = i2;
            while (true) {
                i10--;
                if (i10 < 0) {
                    break;
                } else if (i10 < i4 || i10 > i7) {
                    bArr2[i10] = 15;
                } else {
                    bArr2[i10] = 0;
                }
            }
            i4 = i7 + 1;
            i3 -= i9;
        }
    }

    private int sendMTFValues1(int i, int i2) {
        int[][] iArr;
        int i3 = i;
        Data data2 = this.data;
        int[][] iArr2 = data2.sendMTFValues_rfreq;
        int[] iArr3 = data2.sendMTFValues_fave;
        short[] sArr = data2.sendMTFValues_cost;
        char[] cArr = data2.sfmap;
        byte[] bArr = data2.selector;
        byte[][] bArr2 = data2.sendMTFValues_len;
        byte[] bArr3 = bArr2[0];
        byte[] bArr4 = bArr2[1];
        byte[] bArr5 = bArr2[2];
        char c = 3;
        byte[] bArr6 = bArr2[3];
        int i4 = 4;
        byte[] bArr7 = bArr2[4];
        byte[] bArr8 = bArr2[5];
        int i5 = this.nMTF;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i4) {
            int i8 = i3;
            while (true) {
                i8--;
                if (i8 < 0) {
                    break;
                }
                iArr3[i8] = 0;
                int[] iArr4 = iArr2[i8];
                int i9 = i2;
                while (true) {
                    i9--;
                    if (i9 >= 0) {
                        iArr4[i9] = 0;
                    }
                }
            }
            int i10 = i4;
            char c2 = c;
            int i11 = 0;
            i7 = 0;
            while (i11 < this.nMTF) {
                byte[][] bArr9 = bArr2;
                int min = Math.min(i11 + 49, i5 - 1);
                if (i3 == 6) {
                    int i12 = i11;
                    short s = 0;
                    short s2 = 0;
                    short s3 = 0;
                    short s4 = 0;
                    short s5 = 0;
                    short s6 = 0;
                    while (i12 <= min) {
                        char c3 = cArr[i12];
                        s = (short) (s + (bArr3[c3] & 255));
                        s2 = (short) (s2 + (bArr4[c3] & 255));
                        s3 = (short) (s3 + (bArr5[c3] & 255));
                        s4 = (short) (s4 + (bArr6[c3] & 255));
                        s5 = (short) (s5 + (bArr7[c3] & 255));
                        i12++;
                        s6 = (short) (s6 + (bArr8[c3] & 255));
                        iArr2 = iArr2;
                    }
                    iArr = iArr2;
                    sArr[0] = s;
                    sArr[1] = s2;
                    sArr[2] = s3;
                    sArr[c2] = s4;
                    sArr[i10] = s5;
                    sArr[5] = s6;
                } else {
                    iArr = iArr2;
                    int i13 = i3;
                    while (true) {
                        i13--;
                        if (i13 < 0) {
                            break;
                        }
                        sArr[i13] = 0;
                    }
                    int i14 = i11;
                    while (i14 <= min) {
                        char c4 = cArr[i14];
                        int i15 = i3;
                        while (true) {
                            i15--;
                            if (i15 < 0) {
                                break;
                            }
                            sArr[i15] = (short) (sArr[i15] + (bArr9[i15][c4] & 255));
                            i14 = i14;
                        }
                        i14++;
                    }
                }
                short s7 = 999999999;
                int i16 = i3;
                int[] iArr5 = iArr3;
                int i17 = -1;
                while (true) {
                    i16--;
                    if (i16 < 0) {
                        break;
                    }
                    short[] sArr2 = sArr;
                    short s8 = sArr2[i16];
                    if (s8 < s7) {
                        s7 = s8;
                        i17 = i16;
                    }
                    sArr = sArr2;
                }
                short[] sArr3 = sArr;
                iArr5[i17] = iArr5[i17] + 1;
                bArr[i7] = (byte) i17;
                i7++;
                int[] iArr6 = iArr[i17];
                while (i11 <= min) {
                    char c5 = cArr[i11];
                    iArr6[c5] = iArr6[c5] + 1;
                    i11++;
                }
                i11 = min + 1;
                bArr2 = bArr9;
                iArr3 = iArr5;
                sArr = sArr3;
                iArr2 = iArr;
            }
            byte[][] bArr10 = bArr2;
            int[][] iArr7 = iArr2;
            int[] iArr8 = iArr3;
            short[] sArr4 = sArr;
            for (int i18 = 0; i18 < i3; i18++) {
                hbMakeCodeLengths(bArr10[i18], iArr7[i18], this.data, i2, 20);
            }
            int i19 = i2;
            i6++;
            i4 = i10;
            c = c2;
            bArr2 = bArr10;
            iArr3 = iArr8;
            sArr = sArr4;
            iArr2 = iArr7;
        }
        return i7;
    }

    private void sendMTFValues2(int i, int i2) {
        Data data2 = this.data;
        byte[] bArr = data2.sendMTFValues2_pos;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            bArr[i] = (byte) i;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = data2.selector[i3];
            byte b2 = bArr[0];
            int i4 = 0;
            while (b != b2) {
                i4++;
                byte b3 = bArr[i4];
                bArr[i4] = b2;
                b2 = b3;
            }
            bArr[0] = b2;
            data2.selectorMtf[i3] = (byte) i4;
        }
    }

    private void sendMTFValues3(int i, int i2) {
        int[][] iArr = this.data.sendMTFValues_code;
        byte[][] bArr = this.data.sendMTFValues_len;
        for (int i3 = 0; i3 < i; i3++) {
            byte[] bArr2 = bArr[i3];
            byte b = 32;
            int i4 = i2;
            byte b2 = 0;
            while (true) {
                i4--;
                if (i4 < 0) {
                    break;
                }
                byte b3 = bArr2[i4] & 255;
                if (b3 > b2) {
                    b2 = b3;
                }
                if (b3 < b) {
                    b = b3;
                }
            }
            hbAssignCodes(iArr[i3], bArr[i3], b, b2, i2);
        }
    }

    private void sendMTFValues4() throws IOException {
        boolean[] zArr = this.data.inUse;
        boolean[] zArr2 = this.data.sentMTFValues4_inUse16;
        int i = 16;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            zArr2[i] = false;
            int i2 = i * 16;
            int i3 = 16;
            while (true) {
                i3--;
                if (i3 >= 0) {
                    if (zArr[i2 + i3]) {
                        zArr2[i] = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        for (int i4 = 0; i4 < 16; i4++) {
            bsW(1, zArr2[i4] ? 1 : 0);
        }
        OutputStream outputStream = this.out;
        int i5 = this.bsLive;
        int i6 = this.bsBuff;
        for (int i7 = 0; i7 < 16; i7++) {
            if (zArr2[i7]) {
                int i8 = i7 * 16;
                for (int i9 = 0; i9 < 16; i9++) {
                    while (i5 >= 8) {
                        outputStream.write(i6 >> 24);
                        i6 <<= 8;
                        i5 -= 8;
                    }
                    if (zArr[i8 + i9]) {
                        i6 |= 1 << (31 - i5);
                    }
                    i5++;
                }
            }
        }
        this.bsBuff = i6;
        this.bsLive = i5;
    }

    private void sendMTFValues5(int i, int i2) throws IOException {
        bsW(3, i);
        bsW(15, i2);
        OutputStream outputStream = this.out;
        byte[] bArr = this.data.selectorMtf;
        int i3 = this.bsLive;
        int i4 = this.bsBuff;
        for (int i5 = 0; i5 < i2; i5++) {
            byte b = bArr[i5] & 255;
            for (int i6 = 0; i6 < b; i6++) {
                while (i3 >= 8) {
                    outputStream.write(i4 >> 24);
                    i4 <<= 8;
                    i3 -= 8;
                }
                i4 |= 1 << (31 - i3);
                i3++;
            }
            while (i3 >= 8) {
                outputStream.write(i4 >> 24);
                i4 <<= 8;
                i3 -= 8;
            }
            i3++;
        }
        this.bsBuff = i4;
        this.bsLive = i3;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v9, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v10, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v11, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendMTFValues6(int r14, int r15) throws java.io.IOException {
        /*
            r13 = this;
            org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream$Data r0 = r13.data
            byte[][] r0 = r0.sendMTFValues_len
            java.io.OutputStream r1 = r13.out
            int r2 = r13.bsLive
            int r3 = r13.bsBuff
            r4 = 0
            r5 = r4
        L_0x000c:
            if (r5 >= r14) goto L_0x0075
            r6 = r0[r5]
            byte r7 = r6[r4]
            r7 = r7 & 255(0xff, float:3.57E-43)
        L_0x0014:
            r8 = 8
            if (r2 < r8) goto L_0x0022
            int r8 = r3 >> 24
            r1.write(r8)
            int r3 = r3 << 8
            int r2 = r2 + -8
            goto L_0x0014
        L_0x0022:
            int r9 = 27 - r2
            int r9 = r7 << r9
            r3 = r3 | r9
            int r2 = r2 + 5
            r9 = r4
        L_0x002a:
            if (r9 >= r15) goto L_0x0072
            byte r10 = r6[r9]
            r10 = r10 & 255(0xff, float:3.57E-43)
        L_0x0030:
            if (r7 >= r10) goto L_0x0049
        L_0x0032:
            if (r2 < r8) goto L_0x003e
            int r11 = r3 >> 24
            r1.write(r11)
            int r3 = r3 << 8
            int r2 = r2 + -8
            goto L_0x0032
        L_0x003e:
            int r11 = 30 - r2
            r12 = 2
            int r11 = r12 << r11
            r3 = r3 | r11
            int r2 = r2 + 2
            int r7 = r7 + 1
            goto L_0x0030
        L_0x0049:
            if (r7 <= r10) goto L_0x0061
        L_0x004b:
            if (r2 < r8) goto L_0x0057
            int r11 = r3 >> 24
            r1.write(r11)
            int r3 = r3 << 8
            int r2 = r2 + -8
            goto L_0x004b
        L_0x0057:
            r11 = 3
            int r12 = 30 - r2
            int r11 = r11 << r12
            r3 = r3 | r11
            int r2 = r2 + 2
            int r7 = r7 + -1
            goto L_0x0049
        L_0x0061:
            if (r2 < r8) goto L_0x006d
            int r10 = r3 >> 24
            r1.write(r10)
            int r3 = r3 << 8
            int r2 = r2 + -8
            goto L_0x0061
        L_0x006d:
            int r2 = r2 + 1
            int r9 = r9 + 1
            goto L_0x002a
        L_0x0072:
            int r5 = r5 + 1
            goto L_0x000c
        L_0x0075:
            r13.bsBuff = r3
            r13.bsLive = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream.sendMTFValues6(int, int):void");
    }

    private void sendMTFValues7() throws IOException {
        Data data2 = this.data;
        byte[][] bArr = data2.sendMTFValues_len;
        int[][] iArr = data2.sendMTFValues_code;
        OutputStream outputStream = this.out;
        byte[] bArr2 = data2.selector;
        char[] cArr = data2.sfmap;
        int i = this.nMTF;
        int i2 = this.bsLive;
        int i3 = this.bsBuff;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int min = Math.min(i4 + 49, i - 1);
            byte b = bArr2[i5] & 255;
            int[] iArr2 = iArr[b];
            byte[] bArr3 = bArr[b];
            while (i4 <= min) {
                char c = cArr[i4];
                while (i2 >= 8) {
                    outputStream.write(i3 >> 24);
                    i3 <<= 8;
                    i2 -= 8;
                }
                byte b2 = bArr3[c] & 255;
                i3 |= iArr2[c] << ((32 - i2) - b2);
                i2 += b2;
                i4++;
            }
            i4 = min + 1;
            i5++;
        }
        this.bsBuff = i3;
        this.bsLive = i2;
    }

    private void moveToFrontCodeAndSend() throws IOException {
        bsW(24, this.data.origPtr);
        generateMTFValues();
        sendMTFValues();
    }

    private void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    private void generateMTFValues() {
        int i;
        int i2 = this.last;
        Data data2 = this.data;
        boolean[] zArr = data2.inUse;
        byte[] bArr = data2.block;
        int[] iArr = data2.fmap;
        char[] cArr = data2.sfmap;
        int[] iArr2 = data2.mtfFreq;
        byte[] bArr2 = data2.unseqToSeq;
        byte[] bArr3 = data2.generateMTFValues_yy;
        char c = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 256; i4++) {
            if (zArr[i4]) {
                bArr2[i4] = (byte) i3;
                i3++;
            }
        }
        this.nInUse = i3;
        int i5 = i3 + 1;
        for (int i6 = i5; i6 >= 0; i6--) {
            iArr2[i6] = 0;
        }
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            bArr3[i3] = (byte) i3;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (i7 <= i2) {
            byte b = bArr2[bArr[iArr[i7]] & 255];
            char c2 = c;
            byte b2 = bArr3[c];
            int i10 = c2;
            while (b != b2) {
                i10++;
                byte b3 = bArr3[i10];
                bArr3[i10] = b2;
                b2 = b3;
            }
            bArr3[c2] = b2;
            if (i10 == 0) {
                i8++;
            } else {
                if (i8 > 0) {
                    int i11 = i8 - 1;
                    while (true) {
                        if ((i11 & 1) == 0) {
                            cArr[i9] = c2;
                            i9++;
                            iArr2[c2] = iArr2[c2] + 1;
                        } else {
                            cArr[i9] = 1;
                            i9++;
                            iArr2[1] = iArr2[1] + 1;
                        }
                        if (i11 < 2) {
                            break;
                        }
                        i11 = (i11 - 2) >> 1;
                    }
                    i8 = c2;
                }
                int i12 = i10 + 1;
                cArr[i9] = (char) i12;
                i9++;
                iArr2[i12] = iArr2[i12] + 1;
            }
            i7++;
            c = c2;
        }
        char c3 = c;
        if (i8 > 0) {
            int i13 = i8 - 1;
            while (true) {
                if ((i13 & 1) == 0) {
                    cArr[i9] = c3;
                    i = i9 + 1;
                    iArr2[c3] = iArr2[c3] + 1;
                } else {
                    cArr[i9] = 1;
                    i = i9 + 1;
                    iArr2[1] = iArr2[1] + 1;
                }
                if (i13 < 2) {
                    break;
                }
                i13 = (i13 - 2) >> 1;
            }
        }
        cArr[i9] = (char) i5;
        iArr2[i5] = iArr2[i5] + 1;
        this.nMTF = i9 + 1;
    }

    static final class Data {
        final byte[] block;
        final int[] fmap;
        final byte[] generateMTFValues_yy = new byte[256];
        final int[] heap;
        final boolean[] inUse = new boolean[256];
        final int[] mtfFreq = new int[BZip2Constants.MAX_ALPHA_SIZE];
        int origPtr;
        final int[] parent;
        final byte[] selector = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] selectorMtf = new byte[BZip2Constants.MAX_SELECTORS];
        final byte[] sendMTFValues2_pos;
        final int[][] sendMTFValues_code;
        final short[] sendMTFValues_cost;
        final int[] sendMTFValues_fave;
        final byte[][] sendMTFValues_len;
        final int[][] sendMTFValues_rfreq;
        final boolean[] sentMTFValues4_inUse16;
        final char[] sfmap;
        final byte[] unseqToSeq = new byte[256];
        final int[] weight;

        Data(int i) {
            int[] iArr = new int[2];
            iArr[1] = 258;
            iArr[0] = 6;
            this.sendMTFValues_len = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
            int[] iArr2 = new int[2];
            iArr2[1] = 258;
            iArr2[0] = 6;
            this.sendMTFValues_rfreq = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
            this.sendMTFValues_fave = new int[6];
            this.sendMTFValues_cost = new short[6];
            int[] iArr3 = new int[2];
            iArr3[1] = 258;
            iArr3[0] = 6;
            this.sendMTFValues_code = (int[][]) Array.newInstance(Integer.TYPE, iArr3);
            this.sendMTFValues2_pos = new byte[6];
            this.sentMTFValues4_inUse16 = new boolean[16];
            this.heap = new int[260];
            this.weight = new int[516];
            this.parent = new int[516];
            int i2 = BZip2Constants.BASEBLOCKSIZE * i;
            this.block = new byte[(i2 + 21)];
            this.fmap = new int[i2];
            this.sfmap = new char[(i * 200000)];
        }
    }
}
