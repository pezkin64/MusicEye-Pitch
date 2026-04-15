package org.apache.commons.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.PKWareExtraHeader;

public class X0017_StrongEncryptionHeader extends PKWareExtraHeader {
    private PKWareExtraHeader.EncryptionAlgorithm algId;
    private int bitlen;
    private byte[] erdData;
    private int flags;
    private int format;
    private PKWareExtraHeader.HashAlgorithm hashAlg;
    private int hashSize;
    private byte[] ivData;
    private byte[] keyBlob;
    private long rcount;
    private byte[] recipientKeyHash;
    private byte[] vCRC32;
    private byte[] vData;

    public X0017_StrongEncryptionHeader() {
        super(new ZipShort(23));
    }

    public long getRecordCount() {
        return this.rcount;
    }

    public PKWareExtraHeader.HashAlgorithm getHashAlgorithm() {
        return this.hashAlg;
    }

    public PKWareExtraHeader.EncryptionAlgorithm getEncryptionAlgorithm() {
        return this.algId;
    }

    public void parseCentralDirectoryFormat(byte[] bArr, int i, int i2) throws ZipException {
        assertMinimalLength(12, i2);
        this.format = ZipShort.getValue(bArr, i);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 2));
        this.bitlen = ZipShort.getValue(bArr, i + 4);
        this.flags = ZipShort.getValue(bArr, i + 6);
        long value = ZipLong.getValue(bArr, i + 8);
        this.rcount = value;
        if (value > 0) {
            assertMinimalLength(16, i2);
            this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i + 12));
            this.hashSize = ZipShort.getValue(bArr, i + 14);
        }
    }

    public void parseFileFormat(byte[] bArr, int i, int i2) throws ZipException {
        assertMinimalLength(4, i2);
        int value = ZipShort.getValue(bArr, i);
        assertDynamicLengthFits("ivSize", value, 4, i2);
        int i3 = i + 4;
        assertMinimalLength(i3, value);
        this.ivData = Arrays.copyOfRange(bArr, i3, value);
        int i4 = value + 16;
        assertMinimalLength(i4, i2);
        int i5 = i + value;
        this.format = ZipShort.getValue(bArr, i5 + 6);
        this.algId = PKWareExtraHeader.EncryptionAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i5 + 8));
        this.bitlen = ZipShort.getValue(bArr, i5 + 10);
        this.flags = ZipShort.getValue(bArr, i5 + 12);
        int value2 = ZipShort.getValue(bArr, i5 + 14);
        assertDynamicLengthFits("erdSize", value2, i4, i2);
        int i6 = i5 + 16;
        assertMinimalLength(i6, value2);
        this.erdData = Arrays.copyOfRange(bArr, i6, value2);
        int i7 = value + 20 + value2;
        assertMinimalLength(i7, i2);
        long value3 = ZipLong.getValue(bArr, i6 + value2);
        this.rcount = value3;
        if (value3 == 0) {
            assertMinimalLength(i7 + 2, i2);
            int value4 = ZipShort.getValue(bArr, i5 + 20 + value2);
            assertDynamicLengthFits("vSize", value4, value + 22 + value2, i2);
            if (value4 >= 4) {
                int i8 = i5 + 22 + value2;
                int i9 = value4 - 4;
                assertMinimalLength(i8, i9);
                this.vData = Arrays.copyOfRange(bArr, i8, i9);
                int i10 = (i8 + value4) - 4;
                assertMinimalLength(i10, 4);
                this.vCRC32 = Arrays.copyOfRange(bArr, i10, 4);
                return;
            }
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + value4 + " is too small to hold CRC");
        }
        assertMinimalLength(i7 + 6, i2);
        this.hashAlg = PKWareExtraHeader.HashAlgorithm.getAlgorithmByCode(ZipShort.getValue(bArr, i5 + 20 + value2));
        int i11 = i5 + 22 + value2;
        this.hashSize = ZipShort.getValue(bArr, i11);
        int i12 = i5 + 24 + value2;
        int value5 = ZipShort.getValue(bArr, i12);
        int i13 = this.hashSize;
        if (value5 >= i13) {
            this.recipientKeyHash = new byte[i13];
            this.keyBlob = new byte[(value5 - i13)];
            assertDynamicLengthFits("resize", value5, value + 24 + value2, i2);
            System.arraycopy(bArr, i12, this.recipientKeyHash, 0, this.hashSize);
            int i14 = this.hashSize;
            System.arraycopy(bArr, i12 + i14, this.keyBlob, 0, value5 - i14);
            assertMinimalLength(value + 26 + value2 + value5 + 2, i2);
            int value6 = ZipShort.getValue(bArr, i5 + 26 + value2 + value5);
            if (value6 >= 4) {
                assertDynamicLengthFits("vSize", value6, value + 22 + value2 + value5, i2);
                int i15 = value6 - 4;
                byte[] bArr2 = new byte[i15];
                this.vData = bArr2;
                this.vCRC32 = new byte[4];
                int i16 = i11 + value5;
                System.arraycopy(bArr, i16, bArr2, 0, i15);
                System.arraycopy(bArr, (i16 + value6) - 4, this.vCRC32, 0, 4);
                return;
            }
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + value6 + " is too small to hold CRC");
        }
        throw new ZipException("Invalid X0017_StrongEncryptionHeader: resize " + value5 + " is too small to hold hashSize" + this.hashSize);
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) throws ZipException {
        super.parseFromLocalFileData(bArr, i, i2);
        parseFileFormat(bArr, i, i2);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        super.parseFromCentralDirectoryData(bArr, i, i2);
        parseCentralDirectoryFormat(bArr, i, i2);
    }

    private void assertDynamicLengthFits(String str, int i, int i2, int i3) throws ZipException {
        if (i2 + i > i3) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: " + str + " " + i + " doesn't fit into " + i3 + " bytes of data at position " + i2);
        }
    }
}
