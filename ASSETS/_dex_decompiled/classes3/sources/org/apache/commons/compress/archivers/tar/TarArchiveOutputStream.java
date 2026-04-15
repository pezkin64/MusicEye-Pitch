package org.apache.commons.compress.archivers.tar;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;

public class TarArchiveOutputStream extends ArchiveOutputStream {
    private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding("ASCII");
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_POSIX = 2;
    public static final int BIGNUMBER_STAR = 1;
    private static final int BLOCK_SIZE_UNSPECIFIED = -511;
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int LONGFILE_TRUNCATE = 1;
    private static final int RECORD_SIZE = 512;
    private boolean addPaxHeadersForNonAsciiNames;
    private int bigNumberMode;
    private boolean closed;
    private final CountingOutputStream countingOut;
    private long currBytes;
    private String currName;
    private long currSize;
    final String encoding;
    private boolean finished;
    private boolean haveUnclosedEntry;
    private int longFileMode;
    private final FixedLengthBlockOutputStream out;
    private final byte[] recordBuf;
    private final int recordsPerBlock;
    private int recordsWritten;
    private final ZipEncoding zipEncoding;

    private boolean shouldBeReplaced(char c) {
        return c == 0 || c == '/' || c == '\\';
    }

    @Deprecated
    public int getRecordSize() {
        return 512;
    }

    public TarArchiveOutputStream(OutputStream outputStream) {
        this(outputStream, (int) BLOCK_SIZE_UNSPECIFIED);
    }

    public TarArchiveOutputStream(OutputStream outputStream, String str) {
        this(outputStream, (int) BLOCK_SIZE_UNSPECIFIED, str);
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i) {
        this(outputStream, i, (String) null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2) {
        this(outputStream, i, i2, (String) null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream outputStream, int i, int i2, String str) {
        this(outputStream, i, str);
        if (i2 != 512) {
            throw new IllegalArgumentException("Tar record size must always be 512 bytes. Attempt to set size of " + i2);
        }
    }

    public TarArchiveOutputStream(OutputStream outputStream, int i, String str) {
        this.longFileMode = 0;
        this.bigNumberMode = 0;
        int i2 = BLOCK_SIZE_UNSPECIFIED == i ? 512 : i;
        if (i2 <= 0 || i2 % 512 != 0) {
            throw new IllegalArgumentException("Block size must be a multiple of 512 bytes. Attempt to use set size of " + i);
        }
        CountingOutputStream countingOutputStream = new CountingOutputStream(outputStream);
        this.countingOut = countingOutputStream;
        this.out = new FixedLengthBlockOutputStream((OutputStream) countingOutputStream, 512);
        this.encoding = str;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str);
        this.recordBuf = new byte[512];
        this.recordsPerBlock = i2 / 512;
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    public void setBigNumberMode(int i) {
        this.bigNumberMode = i;
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean z) {
        this.addPaxHeadersForNonAsciiNames = z;
    }

    @Deprecated
    public int getCount() {
        return (int) getBytesWritten();
    }

    public long getBytesWritten() {
        return this.countingOut.getBytesWritten();
    }

    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        } else if (!this.haveUnclosedEntry) {
            writeEOFRecord();
            writeEOFRecord();
            padAsNeeded();
            this.out.flush();
            this.finished = true;
        } else {
            throw new IOException("This archive contains unclosed entries.");
        }
    }

    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            if (!this.closed) {
                this.out.close();
                this.closed = true;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putArchiveEntry(org.apache.commons.compress.archivers.ArchiveEntry r13) throws java.io.IOException {
        /*
            r12 = this;
            boolean r0 = r12.finished
            if (r0 != 0) goto L_0x00e9
            r2 = r13
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r2 = (org.apache.commons.compress.archivers.tar.TarArchiveEntry) r2
            boolean r13 = r2.isGlobalPaxHeader()
            r8 = 0
            r0 = 0
            r10 = 1
            if (r13 == 0) goto L_0x0040
            java.util.Map r13 = r2.getExtraPaxHeaders()
            byte[] r13 = r12.encodeExtendedPaxHeadersContents(r13)
            int r1 = r13.length
            long r3 = (long) r1
            r2.setSize(r3)
            byte[] r1 = r12.recordBuf
            org.apache.commons.compress.archivers.zip.ZipEncoding r3 = r12.zipEncoding
            int r4 = r12.bigNumberMode
            if (r4 != r10) goto L_0x0027
            r0 = r10
        L_0x0027:
            r2.writeEntryHeader(r1, r3, r0)
            byte[] r0 = r12.recordBuf
            r12.writeRecord(r0)
            long r0 = r2.getSize()
            r12.currSize = r0
            r12.currBytes = r8
            r12.haveUnclosedEntry = r10
            r12.write(r13)
            r12.closeArchiveEntry()
            return
        L_0x0040:
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.lang.String r3 = r2.getName()
            r6 = 76
            java.lang.String r7 = "file name"
            java.lang.String r5 = "path"
            r1 = r12
            boolean r13 = r1.handleLongName(r2, r3, r4, r5, r6, r7)
            r11 = r3
            java.lang.String r3 = r2.getLinkName()
            if (r3 == 0) goto L_0x0070
            boolean r1 = r3.isEmpty()
            if (r1 != 0) goto L_0x0070
            r6 = 75
            java.lang.String r7 = "link name"
            java.lang.String r5 = "linkpath"
            r1 = r12
            boolean r5 = r1.handleLongName(r2, r3, r4, r5, r6, r7)
            if (r5 == 0) goto L_0x0071
            r5 = r10
            goto L_0x0072
        L_0x0070:
            r1 = r12
        L_0x0071:
            r5 = r0
        L_0x0072:
            int r6 = r1.bigNumberMode
            r7 = 2
            if (r6 != r7) goto L_0x007b
            r12.addPaxHeadersForBigNumbers(r4, r2)
            goto L_0x0080
        L_0x007b:
            if (r6 == r10) goto L_0x0080
            r12.failForBigNumbers(r2)
        L_0x0080:
            boolean r6 = r1.addPaxHeadersForNonAsciiNames
            if (r6 == 0) goto L_0x0093
            if (r13 != 0) goto L_0x0093
            org.apache.commons.compress.archivers.zip.ZipEncoding r13 = ASCII
            boolean r13 = r13.canEncode(r11)
            if (r13 != 0) goto L_0x0093
            java.lang.String r13 = "path"
            r4.put(r13, r11)
        L_0x0093:
            boolean r13 = r1.addPaxHeadersForNonAsciiNames
            if (r13 == 0) goto L_0x00b2
            if (r5 != 0) goto L_0x00b2
            boolean r13 = r2.isLink()
            if (r13 != 0) goto L_0x00a5
            boolean r13 = r2.isSymbolicLink()
            if (r13 == 0) goto L_0x00b2
        L_0x00a5:
            org.apache.commons.compress.archivers.zip.ZipEncoding r13 = ASCII
            boolean r13 = r13.canEncode(r3)
            if (r13 != 0) goto L_0x00b2
            java.lang.String r13 = "linkpath"
            r4.put(r13, r3)
        L_0x00b2:
            java.util.Map r13 = r2.getExtraPaxHeaders()
            r4.putAll(r13)
            boolean r13 = r4.isEmpty()
            if (r13 != 0) goto L_0x00c2
            r12.writePaxHeaders(r2, r11, r4)
        L_0x00c2:
            byte[] r13 = r1.recordBuf
            org.apache.commons.compress.archivers.zip.ZipEncoding r3 = r1.zipEncoding
            int r4 = r1.bigNumberMode
            if (r4 != r10) goto L_0x00cb
            r0 = r10
        L_0x00cb:
            r2.writeEntryHeader(r13, r3, r0)
            byte[] r13 = r1.recordBuf
            r12.writeRecord(r13)
            r1.currBytes = r8
            boolean r13 = r2.isDirectory()
            if (r13 == 0) goto L_0x00de
            r1.currSize = r8
            goto L_0x00e4
        L_0x00de:
            long r2 = r2.getSize()
            r1.currSize = r2
        L_0x00e4:
            r1.currName = r11
            r1.haveUnclosedEntry = r10
            return
        L_0x00e9:
            r1 = r12
            java.io.IOException r13 = new java.io.IOException
            java.lang.String r0 = "Stream has already been finished"
            r13.<init>(r0)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarArchiveOutputStream.putArchiveEntry(org.apache.commons.compress.archivers.ArchiveEntry):void");
    }

    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        } else if (this.haveUnclosedEntry) {
            this.out.flushBlock();
            long j = this.currBytes;
            long j2 = this.currSize;
            if (j >= j2) {
                int i = (int) (((long) this.recordsWritten) + (j2 / 512));
                this.recordsWritten = i;
                if (0 != j2 % 512) {
                    this.recordsWritten = i + 1;
                }
                this.haveUnclosedEntry = false;
                return;
            }
            throw new IOException("Entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
        } else {
            throw new IOException("No current entry to close");
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.haveUnclosedEntry) {
            long j = (long) i2;
            if (this.currBytes + j <= this.currSize) {
                this.out.write(bArr, i, i2);
                this.currBytes += j;
                return;
            }
            throw new IOException("Request to write '" + i2 + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
        }
        throw new IllegalStateException("No current tar entry");
    }

    /* access modifiers changed from: package-private */
    public void writePaxHeaders(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map) throws IOException {
        String str2 = "./PaxHeaders.X/" + stripTo7Bits(str);
        if (str2.length() >= 100) {
            str2 = str2.substring(0, 99);
        }
        TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(str2, (byte) TarConstants.LF_PAX_EXTENDED_HEADER_LC);
        transferModTime(tarArchiveEntry, tarArchiveEntry2);
        byte[] encodeExtendedPaxHeadersContents = encodeExtendedPaxHeadersContents(map);
        tarArchiveEntry2.setSize((long) encodeExtendedPaxHeadersContents.length);
        putArchiveEntry(tarArchiveEntry2);
        write(encodeExtendedPaxHeadersContents);
        closeArchiveEntry();
    }

    private byte[] encodeExtendedPaxHeadersContents(Map<String, String> map) {
        StringWriter stringWriter = new StringWriter();
        for (Map.Entry next : map.entrySet()) {
            String str = (String) next.getKey();
            String str2 = (String) next.getValue();
            int length = str.length() + str2.length() + 5;
            String str3 = length + " " + str + "=" + str2 + "\n";
            int length2 = str3.getBytes(StandardCharsets.UTF_8).length;
            while (length != length2) {
                str3 = length2 + " " + str + "=" + str2 + "\n";
                int i = length2;
                length2 = str3.getBytes(StandardCharsets.UTF_8).length;
                length = i;
            }
            stringWriter.write(str3);
        }
        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }

    private String stripTo7Bits(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = (char) (str.charAt(i) & 127);
            if (shouldBeReplaced(charAt)) {
                sb.append("_");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    private void writeEOFRecord() throws IOException {
        Arrays.fill(this.recordBuf, (byte) 0);
        writeRecord(this.recordBuf);
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (!this.finished) {
            return new TarArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }

    public ArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        if (!this.finished) {
            return new TarArchiveEntry(path, str, linkOptionArr);
        }
        throw new IOException("Stream has already been finished");
    }

    private void writeRecord(byte[] bArr) throws IOException {
        if (bArr.length == 512) {
            this.out.write(bArr);
            this.recordsWritten++;
            return;
        }
        throw new IOException("Record to write has length '" + bArr.length + "' which is not the record size of '512'");
    }

    private void padAsNeeded() throws IOException {
        int i = this.recordsWritten % this.recordsPerBlock;
        if (i != 0) {
            while (i < this.recordsPerBlock) {
                writeEOFRecord();
                i++;
            }
        }
    }

    private void addPaxHeadersForBigNumbers(Map<String, String> map, TarArchiveEntry tarArchiveEntry) {
        addPaxHeaderForBigNumber(map, "size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        Map<String, String> map2 = map;
        addPaxHeaderForBigNumber(map2, "gid", tarArchiveEntry.getLongGroupId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map2, "mtime", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        addPaxHeaderForBigNumber(map2, "uid", tarArchiveEntry.getLongUserId(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map2, "SCHILY.devmajor", (long) tarArchiveEntry.getDevMajor(), TarConstants.MAXID);
        addPaxHeaderForBigNumber(map2, "SCHILY.devminor", (long) tarArchiveEntry.getDevMinor(), TarConstants.MAXID);
        failForBigNumber("mode", (long) tarArchiveEntry.getMode(), TarConstants.MAXID);
    }

    private void addPaxHeaderForBigNumber(Map<String, String> map, String str, long j, long j2) {
        if (j < 0 || j > j2) {
            map.put(str, String.valueOf(j));
        }
    }

    private void failForBigNumbers(TarArchiveEntry tarArchiveEntry) {
        failForBigNumber("entry size", tarArchiveEntry.getSize(), TarConstants.MAXSIZE);
        failForBigNumberWithPosixMessage("group id", tarArchiveEntry.getLongGroupId(), TarConstants.MAXID);
        failForBigNumber("last modification time", tarArchiveEntry.getModTime().getTime() / 1000, TarConstants.MAXSIZE);
        failForBigNumber("user id", tarArchiveEntry.getLongUserId(), TarConstants.MAXID);
        failForBigNumber("mode", (long) tarArchiveEntry.getMode(), TarConstants.MAXID);
        failForBigNumber("major device number", (long) tarArchiveEntry.getDevMajor(), TarConstants.MAXID);
        failForBigNumber("minor device number", (long) tarArchiveEntry.getDevMinor(), TarConstants.MAXID);
    }

    private void failForBigNumber(String str, long j, long j2) {
        failForBigNumber(str, j, j2, BuildConfig.FLAVOR);
    }

    private void failForBigNumberWithPosixMessage(String str, long j, long j2) {
        failForBigNumber(str, j, j2, " Use STAR or POSIX extensions to overcome this limit");
    }

    private void failForBigNumber(String str, long j, long j2, String str2) {
        if (j < 0 || j > j2) {
            throw new IllegalArgumentException(str + " '" + j + "' is too big ( > " + j2 + " )." + str2);
        }
    }

    private boolean handleLongName(TarArchiveEntry tarArchiveEntry, String str, Map<String, String> map, String str2, byte b, String str3) throws IOException {
        ByteBuffer encode = this.zipEncoding.encode(str);
        int limit = encode.limit() - encode.position();
        if (limit >= 100) {
            int i = this.longFileMode;
            if (i == 3) {
                map.put(str2, str);
                return true;
            } else if (i == 2) {
                TarArchiveEntry tarArchiveEntry2 = new TarArchiveEntry(TarConstants.GNU_LONGLINK, b);
                tarArchiveEntry2.setSize(((long) limit) + 1);
                transferModTime(tarArchiveEntry, tarArchiveEntry2);
                putArchiveEntry(tarArchiveEntry2);
                write(encode.array(), encode.arrayOffset(), limit);
                write(0);
                closeArchiveEntry();
            } else if (i != 1) {
                throw new IllegalArgumentException(str3 + " '" + str + "' is too long ( > 100 bytes)");
            }
        }
        return false;
    }

    private void transferModTime(TarArchiveEntry tarArchiveEntry, TarArchiveEntry tarArchiveEntry2) {
        Date modTime = tarArchiveEntry.getModTime();
        long time = modTime.getTime() / 1000;
        if (time < 0 || time > TarConstants.MAXSIZE) {
            modTime = new Date(0);
        }
        tarArchiveEntry2.setModTime(modTime);
    }
}
