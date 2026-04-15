package org.apache.commons.compress.archivers.tar;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class TarArchiveInputStream extends ArchiveInputStream {
    private static final int SMALL_BUFFER_SIZE = 256;
    private final int blockSize;
    private TarArchiveEntry currEntry;
    private int currentSparseInputStreamIndex;
    final String encoding;
    private long entryOffset;
    private long entrySize;
    private Map<String, String> globalPaxHeaders;
    private final List<TarArchiveStructSparse> globalSparseHeaders;
    private boolean hasHitEOF;
    private final InputStream inputStream;
    private final boolean lenient;
    private final byte[] recordBuffer;
    private final int recordSize;
    private final byte[] smallBuf;
    private List<InputStream> sparseInputStreams;
    private final ZipEncoding zipEncoding;

    public boolean markSupported() {
        return false;
    }

    public TarArchiveInputStream(InputStream inputStream2) {
        this(inputStream2, (int) TarConstants.DEFAULT_BLKSIZE, 512);
    }

    public TarArchiveInputStream(InputStream inputStream2, boolean z) {
        this(inputStream2, TarConstants.DEFAULT_BLKSIZE, 512, (String) null, z);
    }

    public TarArchiveInputStream(InputStream inputStream2, String str) {
        this(inputStream2, TarConstants.DEFAULT_BLKSIZE, 512, str);
    }

    public TarArchiveInputStream(InputStream inputStream2, int i) {
        this(inputStream2, i, 512);
    }

    public TarArchiveInputStream(InputStream inputStream2, int i, String str) {
        this(inputStream2, i, 512, str);
    }

    public TarArchiveInputStream(InputStream inputStream2, int i, int i2) {
        this(inputStream2, i, i2, (String) null);
    }

    public TarArchiveInputStream(InputStream inputStream2, int i, int i2, String str) {
        this(inputStream2, i, i2, str, false);
    }

    public TarArchiveInputStream(InputStream inputStream2, int i, int i2, String str, boolean z) {
        this.smallBuf = new byte[256];
        this.globalPaxHeaders = new HashMap();
        this.globalSparseHeaders = new ArrayList();
        this.inputStream = inputStream2;
        this.hasHitEOF = false;
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.recordSize = i2;
        this.recordBuffer = new byte[i2];
        this.blockSize = i;
        this.lenient = z;
    }

    public void close() throws IOException {
        List<InputStream> list = this.sparseInputStreams;
        if (list != null) {
            for (InputStream close : list) {
                close.close();
            }
        }
        this.inputStream.close();
    }

    public int getRecordSize() {
        return this.recordSize;
    }

    public int available() throws IOException {
        if (isDirectory()) {
            return 0;
        }
        if (this.currEntry.getRealSize() - this.entryOffset > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) (this.currEntry.getRealSize() - this.entryOffset);
    }

    public long skip(long j) throws IOException {
        long j2;
        TarArchiveInputStream tarArchiveInputStream;
        if (j <= 0 || isDirectory()) {
            return 0;
        }
        long available = (long) this.inputStream.available();
        long min = Math.min(j, this.currEntry.getRealSize() - this.entryOffset);
        if (!this.currEntry.isSparse()) {
            j2 = getActuallySkipped(available, IOUtils.skip(this.inputStream, min), min);
            tarArchiveInputStream = this;
        } else {
            tarArchiveInputStream = this;
            j2 = skipSparse(min);
        }
        count(j2);
        tarArchiveInputStream.entryOffset += j2;
        return j2;
    }

    private long skipSparse(long j) throws IOException {
        List<InputStream> list = this.sparseInputStreams;
        if (list == null || list.isEmpty()) {
            return this.inputStream.skip(j);
        }
        long j2 = 0;
        while (j2 < j && this.currentSparseInputStreamIndex < this.sparseInputStreams.size()) {
            j2 += this.sparseInputStreams.get(this.currentSparseInputStreamIndex).skip(j - j2);
            if (j2 < j) {
                this.currentSparseInputStreamIndex++;
            }
        }
        return j2;
    }

    public synchronized void mark(int i) {
    }

    public synchronized void reset() {
    }

    public TarArchiveEntry getNextTarEntry() throws IOException {
        if (isAtEOF()) {
            return null;
        }
        if (this.currEntry != null) {
            IOUtils.skip(this, LongCompanionObject.MAX_VALUE);
            skipRecordPadding();
        }
        byte[] record = getRecord();
        if (record == null) {
            this.currEntry = null;
            return null;
        }
        try {
            TarArchiveEntry tarArchiveEntry = new TarArchiveEntry(record, this.zipEncoding, this.lenient);
            this.currEntry = tarArchiveEntry;
            this.entryOffset = 0;
            this.entrySize = tarArchiveEntry.getSize();
            if (this.currEntry.isGNULongLinkEntry()) {
                byte[] longNameData = getLongNameData();
                if (longNameData == null) {
                    return null;
                }
                this.currEntry.setLinkName(this.zipEncoding.decode(longNameData));
            }
            if (this.currEntry.isGNULongNameEntry()) {
                byte[] longNameData2 = getLongNameData();
                if (longNameData2 == null) {
                    return null;
                }
                String decode = this.zipEncoding.decode(longNameData2);
                this.currEntry.setName(decode);
                if (this.currEntry.isDirectory() && !decode.endsWith("/")) {
                    TarArchiveEntry tarArchiveEntry2 = this.currEntry;
                    tarArchiveEntry2.setName(decode + "/");
                }
            }
            if (this.currEntry.isGlobalPaxHeader()) {
                readGlobalPaxHeaders();
            }
            try {
                if (this.currEntry.isPaxHeader()) {
                    paxHeaders();
                } else if (!this.globalPaxHeaders.isEmpty()) {
                    applyPaxHeadersToCurrentEntry(this.globalPaxHeaders, this.globalSparseHeaders);
                }
                if (this.currEntry.isOldGNUSparse()) {
                    readOldGNUSparse();
                }
                this.entrySize = this.currEntry.getSize();
                return this.currEntry;
            } catch (NumberFormatException e) {
                throw new IOException("Error detected parsing the pax header", e);
            }
        } catch (IllegalArgumentException e2) {
            throw new IOException("Error detected parsing the header", e2);
        }
    }

    private void skipRecordPadding() throws IOException {
        if (!isDirectory()) {
            long j = this.entrySize;
            if (j > 0 && j % ((long) this.recordSize) != 0) {
                long available = (long) this.inputStream.available();
                long j2 = this.entrySize;
                int i = this.recordSize;
                long j3 = (((j2 / ((long) i)) + 1) * ((long) i)) - j2;
                count(getActuallySkipped(available, IOUtils.skip(this.inputStream, j3), j3));
                return;
            }
        }
    }

    private long getActuallySkipped(long j, long j2, long j3) throws IOException {
        if (this.inputStream instanceof FileInputStream) {
            j2 = Math.min(j2, j);
        }
        if (j2 == j3) {
            return j2;
        }
        throw new IOException("Truncated TAR archive");
    }

    /* access modifiers changed from: protected */
    public byte[] getLongNameData() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = read(this.smallBuf);
            if (read < 0) {
                break;
            }
            byteArrayOutputStream.write(this.smallBuf, 0, read);
        }
        getNextEntry();
        if (this.currEntry == null) {
            return null;
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        while (length > 0 && byteArray[length - 1] == 0) {
            length--;
        }
        if (length == byteArray.length) {
            return byteArray;
        }
        byte[] bArr = new byte[length];
        System.arraycopy(byteArray, 0, bArr, 0, length);
        return bArr;
    }

    private byte[] getRecord() throws IOException {
        byte[] readRecord = readRecord();
        setAtEOF(isEOFRecord(readRecord));
        if (!isAtEOF() || readRecord == null) {
            return readRecord;
        }
        tryToConsumeSecondEOFRecord();
        consumeRemainderOfLastBlock();
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isEOFRecord(byte[] bArr) {
        return bArr == null || ArchiveUtils.isArrayZero(bArr, this.recordSize);
    }

    /* access modifiers changed from: protected */
    public byte[] readRecord() throws IOException {
        int readFully = IOUtils.readFully(this.inputStream, this.recordBuffer);
        count(readFully);
        if (readFully != this.recordSize) {
            return null;
        }
        return this.recordBuffer;
    }

    private void readGlobalPaxHeaders() throws IOException {
        this.globalPaxHeaders = TarUtils.parsePaxHeaders(this, this.globalSparseHeaders, this.globalPaxHeaders, this.entrySize);
        getNextEntry();
        if (this.currEntry == null) {
            throw new IOException("Error detected parsing the pax header");
        }
    }

    private void paxHeaders() throws IOException {
        ArrayList arrayList = new ArrayList();
        Map<String, String> parsePaxHeaders = TarUtils.parsePaxHeaders(this, arrayList, this.globalPaxHeaders, this.entrySize);
        if (parsePaxHeaders.containsKey("GNU.sparse.map")) {
            arrayList = new ArrayList(TarUtils.parseFromPAX01SparseHeaders(parsePaxHeaders.get("GNU.sparse.map")));
        }
        getNextEntry();
        if (this.currEntry != null) {
            applyPaxHeadersToCurrentEntry(parsePaxHeaders, arrayList);
            if (this.currEntry.isPaxGNU1XSparse()) {
                this.currEntry.setSparseHeaders(TarUtils.parsePAX1XSparseHeaders(this.inputStream, this.recordSize));
            }
            buildSparseInputStreams();
            return;
        }
        throw new IOException("premature end of tar archive. Didn't find any entry after PAX header.");
    }

    private void applyPaxHeadersToCurrentEntry(Map<String, String> map, List<TarArchiveStructSparse> list) throws IOException {
        this.currEntry.updateEntryFromPaxHeaders(map);
        this.currEntry.setSparseHeaders(list);
    }

    private void readOldGNUSparse() throws IOException {
        TarArchiveSparseEntry tarArchiveSparseEntry;
        if (this.currEntry.isExtended()) {
            do {
                byte[] record = getRecord();
                if (record != null) {
                    tarArchiveSparseEntry = new TarArchiveSparseEntry(record);
                    this.currEntry.getSparseHeaders().addAll(tarArchiveSparseEntry.getSparseHeaders());
                } else {
                    throw new IOException("premature end of tar archive. Didn't find extended_header after header with extended flag.");
                }
            } while (tarArchiveSparseEntry.isExtended());
        }
        buildSparseInputStreams();
    }

    private boolean isDirectory() {
        TarArchiveEntry tarArchiveEntry = this.currEntry;
        return tarArchiveEntry != null && tarArchiveEntry.isDirectory();
    }

    public ArchiveEntry getNextEntry() throws IOException {
        return getNextTarEntry();
    }

    private void tryToConsumeSecondEOFRecord() throws IOException {
        boolean markSupported = this.inputStream.markSupported();
        if (markSupported) {
            this.inputStream.mark(this.recordSize);
        }
        try {
            if (!isEOFRecord(readRecord()) && markSupported) {
            }
        } finally {
            if (markSupported) {
                pushedBackBytes((long) this.recordSize);
                this.inputStream.reset();
            }
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 == 0) {
            return 0;
        }
        if (isAtEOF() || isDirectory()) {
            return -1;
        }
        TarArchiveEntry tarArchiveEntry = this.currEntry;
        if (tarArchiveEntry == null) {
            throw new IllegalStateException("No current tar entry");
        } else if (this.entryOffset >= tarArchiveEntry.getRealSize()) {
            return -1;
        } else {
            int min = Math.min(i2, available());
            if (this.currEntry.isSparse()) {
                i3 = readSparse(bArr, i, min);
            } else {
                i3 = this.inputStream.read(bArr, i, min);
            }
            if (i3 != -1) {
                count(i3);
                this.entryOffset += (long) i3;
                return i3;
            } else if (min <= 0) {
                setAtEOF(true);
                return i3;
            } else {
                throw new IOException("Truncated TAR archive");
            }
        }
    }

    private int readSparse(byte[] bArr, int i, int i2) throws IOException {
        List<InputStream> list = this.sparseInputStreams;
        if (list == null || list.isEmpty()) {
            return this.inputStream.read(bArr, i, i2);
        }
        if (this.currentSparseInputStreamIndex >= this.sparseInputStreams.size()) {
            return -1;
        }
        int read = this.sparseInputStreams.get(this.currentSparseInputStreamIndex).read(bArr, i, i2);
        if (this.currentSparseInputStreamIndex == this.sparseInputStreams.size() - 1) {
            return read;
        }
        if (read == -1) {
            this.currentSparseInputStreamIndex++;
            return readSparse(bArr, i, i2);
        } else if (read >= i2) {
            return read;
        } else {
            this.currentSparseInputStreamIndex++;
            int readSparse = readSparse(bArr, i + read, i2 - read);
            return readSparse == -1 ? read : read + readSparse;
        }
    }

    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return archiveEntry instanceof TarArchiveEntry;
    }

    public TarArchiveEntry getCurrentEntry() {
        return this.currEntry;
    }

    /* access modifiers changed from: protected */
    public final void setCurrentEntry(TarArchiveEntry tarArchiveEntry) {
        this.currEntry = tarArchiveEntry;
    }

    /* access modifiers changed from: protected */
    public final boolean isAtEOF() {
        return this.hasHitEOF;
    }

    /* access modifiers changed from: protected */
    public final void setAtEOF(boolean z) {
        this.hasHitEOF = z;
    }

    private void consumeRemainderOfLastBlock() throws IOException {
        long bytesRead = getBytesRead();
        int i = this.blockSize;
        long j = bytesRead % ((long) i);
        if (j > 0) {
            count(IOUtils.skip(this.inputStream, ((long) i) - j));
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < 265) {
            return false;
        }
        if (ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, TarConstants.MAGIC_OFFSET, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_POSIX, bArr, TarConstants.VERSION_OFFSET, 2)) {
            return true;
        }
        if (!ArchiveUtils.matchAsciiBuffer(TarConstants.MAGIC_GNU, bArr, TarConstants.MAGIC_OFFSET, 6) || (!ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_SPACE, bArr, TarConstants.VERSION_OFFSET, 2) && !ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_GNU_ZERO, bArr, TarConstants.VERSION_OFFSET, 2))) {
            return ArchiveUtils.matchAsciiBuffer("ustar\u0000", bArr, TarConstants.MAGIC_OFFSET, 6) && ArchiveUtils.matchAsciiBuffer(TarConstants.VERSION_ANT, bArr, TarConstants.VERSION_OFFSET, 2);
        }
        return true;
    }

    private void buildSparseInputStreams() throws IOException {
        this.currentSparseInputStreamIndex = -1;
        this.sparseInputStreams = new ArrayList();
        List<TarArchiveStructSparse> orderedSparseHeaders = this.currEntry.getOrderedSparseHeaders();
        TarArchiveSparseZeroInputStream tarArchiveSparseZeroInputStream = new TarArchiveSparseZeroInputStream();
        long j = 0;
        for (TarArchiveStructSparse next : orderedSparseHeaders) {
            int i = ((next.getOffset() - j) > 0 ? 1 : ((next.getOffset() - j) == 0 ? 0 : -1));
            if (i >= 0) {
                if (i > 0) {
                    this.sparseInputStreams.add(new BoundedInputStream(tarArchiveSparseZeroInputStream, next.getOffset() - j));
                }
                if (next.getNumbytes() > 0) {
                    this.sparseInputStreams.add(new BoundedInputStream(this.inputStream, next.getNumbytes()));
                }
                j = next.getOffset() + next.getNumbytes();
            } else {
                throw new IOException("Corrupted struct sparse detected");
            }
        }
        if (!this.sparseInputStreams.isEmpty()) {
            this.currentSparseInputStreamIndex = 0;
        }
    }
}
