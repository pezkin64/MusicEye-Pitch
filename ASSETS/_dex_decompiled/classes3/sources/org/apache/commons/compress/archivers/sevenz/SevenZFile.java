package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.zip.CRC32;
import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class SevenZFile implements Closeable {
    private static final String DEFAULT_FILE_NAME = "unknown archive";
    private static final CharsetEncoder PASSWORD_ENCODER = StandardCharsets.UTF_16LE.newEncoder();
    static final int SIGNATURE_HEADER_SIZE = 32;
    static final byte[] sevenZSignature = {TarConstants.LF_CONTIG, 122, -68, -81, 39, 28};
    private final Archive archive;
    private SeekableByteChannel channel;
    /* access modifiers changed from: private */
    public long compressedBytesReadFromCurrentEntry;
    private int currentEntryIndex;
    private int currentFolderIndex;
    private InputStream currentFolderInputStream;
    private final ArrayList<InputStream> deferredBlockStreams;
    private final String fileName;
    private final SevenZFileOptions options;
    private byte[] password;
    /* access modifiers changed from: private */
    public long uncompressedBytesReadFromCurrentEntry;

    public SevenZFile(File file, char[] cArr) throws IOException {
        this(file, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StreamsKt$$ExternalSyntheticApiModelOutline0.m$3()), new FileAttribute[0]), file.getAbsolutePath(), utf16Decode(cArr), true, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(File file, byte[] bArr) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StreamsKt$$ExternalSyntheticApiModelOutline0.m$3()), new FileAttribute[0]), file.getAbsolutePath(), bArr, true, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, (char[]) null, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr) throws IOException {
        this(seekableByteChannel, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, cArr, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr) throws IOException {
        this(seekableByteChannel, str, cArr, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, char[] cArr, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, utf16Decode(cArr), false, sevenZFileOptions);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, str, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(seekableByteChannel, str, (byte[]) null, false, sevenZFileOptions);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, byte[] bArr) throws IOException {
        this(seekableByteChannel, DEFAULT_FILE_NAME, bArr);
    }

    @Deprecated
    public SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr) throws IOException {
        this(seekableByteChannel, str, bArr, false, SevenZFileOptions.DEFAULT);
    }

    private SevenZFile(SeekableByteChannel seekableByteChannel, String str, byte[] bArr, boolean z, SevenZFileOptions sevenZFileOptions) throws IOException {
        this.currentEntryIndex = -1;
        this.currentFolderIndex = -1;
        this.deferredBlockStreams = new ArrayList<>();
        this.channel = seekableByteChannel;
        this.fileName = str;
        this.options = sevenZFileOptions;
        try {
            this.archive = readHeaders(bArr);
            if (bArr != null) {
                this.password = Arrays.copyOf(bArr, bArr.length);
            } else {
                this.password = null;
            }
        } catch (Throwable th) {
            if (z) {
                StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.channel);
            }
            throw th;
        }
    }

    public SevenZFile(File file) throws IOException {
        this(file, SevenZFileOptions.DEFAULT);
    }

    public SevenZFile(File file, SevenZFileOptions sevenZFileOptions) throws IOException {
        this(file, (char[]) null, sevenZFileOptions);
    }

    public void close() throws IOException {
        SeekableByteChannel seekableByteChannel = this.channel;
        if (seekableByteChannel != null) {
            try {
                StreamsKt$$ExternalSyntheticApiModelOutline0.m(seekableByteChannel);
            } finally {
                this.channel = null;
                byte[] bArr = this.password;
                if (bArr != null) {
                    Arrays.fill(bArr, (byte) 0);
                }
                this.password = null;
            }
        }
    }

    public SevenZArchiveEntry getNextEntry() throws IOException {
        if (this.currentEntryIndex >= this.archive.files.length - 1) {
            return null;
        }
        this.currentEntryIndex++;
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[this.currentEntryIndex];
        if (sevenZArchiveEntry.getName() == null && this.options.getUseDefaultNameForUnnamedEntries()) {
            sevenZArchiveEntry.setName(getDefaultName());
        }
        buildDecodingStream(this.currentEntryIndex, false);
        this.compressedBytesReadFromCurrentEntry = 0;
        this.uncompressedBytesReadFromCurrentEntry = 0;
        return sevenZArchiveEntry;
    }

    public Iterable<SevenZArchiveEntry> getEntries() {
        return new ArrayList(Arrays.asList(this.archive.files));
    }

    private Archive readHeaders(byte[] bArr) throws IOException {
        ByteBuffer order = ByteBuffer.allocate(12).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        byte[] bArr2 = new byte[6];
        order.get(bArr2);
        if (Arrays.equals(bArr2, sevenZSignature)) {
            byte b = order.get();
            byte b2 = order.get();
            if (b == 0) {
                long j = ((long) order.getInt()) & 4294967295L;
                if (j == 0) {
                    long m = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.channel);
                    ByteBuffer allocate = ByteBuffer.allocate(20);
                    readFully(allocate);
                    SeekableByteChannel unused = this.channel.position(m);
                    while (allocate.hasRemaining()) {
                        if (allocate.get() != 0) {
                        }
                    }
                    if (this.options.getTryToRecoverBrokenArchives()) {
                        return tryToLocateEndHeader(bArr);
                    }
                    throw new IOException("archive seems to be invalid.\nYou may want to retry and enable the tryToRecoverBrokenArchives if the archive could be a multi volume archive that has been closed prematurely.");
                }
                return initializeArchive(readStartHeader(j), bArr, true);
            }
            throw new IOException(String.format("Unsupported 7z version (%d,%d)", new Object[]{Byte.valueOf(b), Byte.valueOf(b2)}));
        }
        throw new IOException("Bad 7z signature");
    }

    private Archive tryToLocateEndHeader(byte[] bArr) throws IOException {
        long j;
        ByteBuffer allocate = ByteBuffer.allocate(1);
        long m = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.channel) + 20;
        if (StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.channel) + 1048576 > this.channel.size()) {
            j = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.channel);
        } else {
            j = this.channel.size() - 1048576;
        }
        long m$1 = this.channel.size() - 1;
        while (m$1 > j) {
            m$1--;
            SeekableByteChannel unused = this.channel.position(m$1);
            allocate.rewind();
            if (this.channel.read(allocate) >= 1) {
                byte b = allocate.array()[0];
                if (b == 23 || b == 1) {
                    try {
                        StartHeader startHeader = new StartHeader();
                        startHeader.nextHeaderOffset = m$1 - m;
                        startHeader.nextHeaderSize = this.channel.size() - m$1;
                        Archive initializeArchive = initializeArchive(startHeader, bArr, false);
                        if (initializeArchive.packSizes.length > 0 && initializeArchive.files.length > 0) {
                            return initializeArchive;
                        }
                    } catch (Exception unused2) {
                        continue;
                    }
                }
            } else {
                throw new EOFException();
            }
        }
        throw new IOException("Start header corrupt and unable to guess end header");
    }

    private Archive initializeArchive(StartHeader startHeader, byte[] bArr, boolean z) throws IOException {
        assertFitsIntoNonNegativeInt("nextHeaderSize", startHeader.nextHeaderSize);
        SeekableByteChannel unused = this.channel.position(startHeader.nextHeaderOffset + 32);
        ByteBuffer order = ByteBuffer.allocate((int) startHeader.nextHeaderSize).order(ByteOrder.LITTLE_ENDIAN);
        readFully(order);
        if (z) {
            CRC32 crc32 = new CRC32();
            crc32.update(order.array());
            if (startHeader.nextHeaderCrc != crc32.getValue()) {
                throw new IOException("NextHeader CRC mismatch");
            }
        }
        Archive archive2 = new Archive();
        int unsignedByte = getUnsignedByte(order);
        if (unsignedByte == 23) {
            order = readEncodedHeader(order, archive2, bArr);
            archive2 = new Archive();
            unsignedByte = getUnsignedByte(order);
        }
        if (unsignedByte == 1) {
            readHeader(order, archive2);
            archive2.subStreamsInfo = null;
            return archive2;
        }
        throw new IOException("Broken or unsupported archive: no Header");
    }

    private StartHeader readStartHeader(long j) throws IOException {
        Throwable th;
        StartHeader startHeader = new StartHeader();
        DataInputStream dataInputStream = new DataInputStream(new CRC32VerifyingInputStream((InputStream) new BoundedSeekableByteChannelInputStream(this.channel, 20), 20, j));
        try {
            startHeader.nextHeaderOffset = Long.reverseBytes(dataInputStream.readLong());
            if (startHeader.nextHeaderOffset < 0 || startHeader.nextHeaderOffset + 32 > this.channel.size()) {
                throw new IOException("nextHeaderOffset is out of bounds");
            }
            startHeader.nextHeaderSize = Long.reverseBytes(dataInputStream.readLong());
            long j2 = startHeader.nextHeaderOffset + startHeader.nextHeaderSize;
            if (j2 < startHeader.nextHeaderOffset || j2 + 32 > this.channel.size()) {
                throw new IOException("nextHeaderSize is out of bounds");
            }
            startHeader.nextHeaderCrc = ((long) Integer.reverseBytes(dataInputStream.readInt())) & 4294967295L;
            dataInputStream.close();
            return startHeader;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            try {
                dataInputStream.close();
            } catch (Throwable th4) {
                th.addSuppressed(th4);
            }
            throw th3;
        }
    }

    private void readHeader(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        int position = byteBuffer.position();
        sanityCheckAndCollectStatistics(byteBuffer).assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            readArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 3) {
            if (unsignedByte == 4) {
                readStreamsInfo(byteBuffer, archive2);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 5) {
                readFilesInfo(byteBuffer, archive2);
                getUnsignedByte(byteBuffer);
                return;
            }
            return;
        }
        throw new IOException("Additional streams unsupported");
    }

    private ArchiveStatistics sanityCheckAndCollectStatistics(ByteBuffer byteBuffer) throws IOException {
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 2) {
            sanityCheckArchiveProperties(byteBuffer);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 3) {
            if (unsignedByte == 4) {
                sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 5) {
                sanityCheckFilesInfo(byteBuffer, archiveStatistics);
                unsignedByte = getUnsignedByte(byteBuffer);
            }
            if (unsignedByte == 0) {
                return archiveStatistics;
            }
            throw new IOException("Badly terminated header, found " + unsignedByte);
        }
        throw new IOException("Additional streams unsupported");
    }

    private void readArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            get(byteBuffer, new byte[((int) readUint64(byteBuffer))]);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckArchiveProperties(ByteBuffer byteBuffer) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        while (unsignedByte != 0) {
            long assertFitsIntoNonNegativeInt = (long) assertFitsIntoNonNegativeInt("propertySize", readUint64(byteBuffer));
            if (skipBytesFully(byteBuffer, assertFitsIntoNonNegativeInt) >= assertFitsIntoNonNegativeInt) {
                unsignedByte = getUnsignedByte(byteBuffer);
            } else {
                throw new IOException("invalid property size");
            }
        }
    }

    private ByteBuffer readEncodedHeader(ByteBuffer byteBuffer, Archive archive2, byte[] bArr) throws IOException {
        int position = byteBuffer.position();
        ArchiveStatistics archiveStatistics = new ArchiveStatistics();
        sanityCheckStreamsInfo(byteBuffer, archiveStatistics);
        archiveStatistics.assertValidity(this.options.getMaxMemoryLimitInKb());
        byteBuffer.position(position);
        readStreamsInfo(byteBuffer, archive2);
        if (archive2.folders == null || archive2.folders.length == 0) {
            throw new IOException("no folders, can't read encoded header");
        } else if (archive2.packSizes == null || archive2.packSizes.length == 0) {
            throw new IOException("no packed streams, can't read encoded header");
        } else {
            Folder folder = archive2.folders[0];
            SeekableByteChannel unused = this.channel.position(archive2.packPos + 32);
            BoundedSeekableByteChannelInputStream boundedSeekableByteChannelInputStream = new BoundedSeekableByteChannelInputStream(this.channel, archive2.packSizes[0]);
            CRC32VerifyingInputStream cRC32VerifyingInputStream = boundedSeekableByteChannelInputStream;
            for (Coder next : folder.getOrderedCoders()) {
                if (next.numInStreams == 1 && next.numOutStreams == 1) {
                    cRC32VerifyingInputStream = Coders.addDecoder(this.fileName, cRC32VerifyingInputStream, folder.getUnpackSizeForCoder(next), next, bArr, this.options.getMaxMemoryLimitInKb());
                } else {
                    throw new IOException("Multi input/output stream coders are not yet supported");
                }
            }
            if (folder.hasCrc) {
                cRC32VerifyingInputStream = new CRC32VerifyingInputStream(cRC32VerifyingInputStream, folder.getUnpackSize(), folder.crc);
            }
            int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("unpackSize", folder.getUnpackSize());
            byte[] readRange = IOUtils.readRange(cRC32VerifyingInputStream, assertFitsIntoNonNegativeInt);
            if (readRange.length >= assertFitsIntoNonNegativeInt) {
                cRC32VerifyingInputStream.close();
                return ByteBuffer.wrap(readRange).order(ByteOrder.LITTLE_ENDIAN);
            }
            throw new IOException("premature end of stream");
        }
    }

    private void sanityCheckStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            sanityCheckPackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            sanityCheckUnpackInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 8) {
            sanityCheckSubStreamsInfo(byteBuffer, archiveStatistics);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated StreamsInfo");
        }
    }

    private void readStreamsInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 6) {
            readPackInfo(byteBuffer, archive2);
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 7) {
            readUnpackInfo(byteBuffer, archive2);
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            archive2.folders = Folder.EMPTY_FOLDER_ARRAY;
        }
        if (unsignedByte == 8) {
            readSubStreamsInfo(byteBuffer, archive2);
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckPackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        long readUint64 = readUint64(byteBuffer2);
        long j = 0;
        if (readUint64 >= 0) {
            long j2 = 32 + readUint64;
            if (j2 <= this.channel.size() && j2 >= 0) {
                ArchiveStatistics archiveStatistics2 = archiveStatistics;
                int unused = archiveStatistics2.numberOfPackedStreams = assertFitsIntoNonNegativeInt("numPackStreams", readUint64(byteBuffer2));
                int unsignedByte = getUnsignedByte(byteBuffer2);
                if (unsignedByte == 9) {
                    int i = 0;
                    long j3 = 0;
                    while (i < archiveStatistics2.numberOfPackedStreams) {
                        long readUint642 = readUint64(byteBuffer2);
                        j3 += readUint642;
                        long j4 = j2 + j3;
                        if (readUint642 < j || j4 > this.channel.size() || j4 < readUint64) {
                            throw new IOException("packSize (" + readUint642 + ") is out of range");
                        }
                        i++;
                        j = 0;
                    }
                    unsignedByte = getUnsignedByte(byteBuffer2);
                }
                if (unsignedByte == 10) {
                    long cardinality = (long) (readAllOrBits(byteBuffer2, archiveStatistics2.numberOfPackedStreams).cardinality() * 4);
                    if (skipBytesFully(byteBuffer2, cardinality) >= cardinality) {
                        unsignedByte = getUnsignedByte(byteBuffer2);
                    } else {
                        throw new IOException("invalid number of CRCs in PackInfo");
                    }
                }
                if (unsignedByte != 0) {
                    throw new IOException("Badly terminated PackInfo (" + unsignedByte + ")");
                }
                return;
            }
        }
        throw new IOException("packPos (" + readUint64 + ") is out of range");
    }

    private void readPackInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        archive2.packPos = readUint64(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 9) {
            archive2.packSizes = new long[readUint64];
            for (int i = 0; i < archive2.packSizes.length; i++) {
                archive2.packSizes[i] = readUint64(byteBuffer);
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (unsignedByte == 10) {
            archive2.packCrcsDefined = readAllOrBits(byteBuffer, readUint64);
            archive2.packCrcs = new long[readUint64];
            for (int i2 = 0; i2 < readUint64; i2++) {
                if (archive2.packCrcsDefined.get(i2)) {
                    archive2.packCrcs[i2] = ((long) getInt(byteBuffer)) & 4294967295L;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckUnpackInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 11) {
            int unused = archiveStatistics.numberOfFolders = assertFitsIntoNonNegativeInt("numFolders", readUint64(byteBuffer));
            if (getUnsignedByte(byteBuffer) == 0) {
                LinkedList<Integer> linkedList = new LinkedList<>();
                for (int i = 0; i < archiveStatistics.numberOfFolders; i++) {
                    linkedList.add(Integer.valueOf(sanityCheckFolder(byteBuffer, archiveStatistics)));
                }
                if (archiveStatistics.numberOfInStreams - (archiveStatistics.numberOfOutStreams - ((long) archiveStatistics.numberOfFolders)) >= ((long) archiveStatistics.numberOfPackedStreams)) {
                    int unsignedByte2 = getUnsignedByte(byteBuffer);
                    if (unsignedByte2 == 12) {
                        for (Integer intValue : linkedList) {
                            int intValue2 = intValue.intValue();
                            int i2 = 0;
                            while (true) {
                                if (i2 < intValue2) {
                                    if (readUint64(byteBuffer) >= 0) {
                                        i2++;
                                    } else {
                                        throw new IllegalArgumentException("negative unpackSize");
                                    }
                                }
                            }
                        }
                        int unsignedByte3 = getUnsignedByte(byteBuffer);
                        if (unsignedByte3 == 10) {
                            BitSet unused2 = archiveStatistics.folderHasCrc = readAllOrBits(byteBuffer, archiveStatistics.numberOfFolders);
                            long cardinality = (long) (archiveStatistics.folderHasCrc.cardinality() * 4);
                            if (skipBytesFully(byteBuffer, cardinality) >= cardinality) {
                                unsignedByte3 = getUnsignedByte(byteBuffer);
                            } else {
                                throw new IOException("invalid number of CRCs in UnpackInfo");
                            }
                        }
                        if (unsignedByte3 != 0) {
                            throw new IOException("Badly terminated UnpackInfo");
                        }
                        return;
                    }
                    throw new IOException("Expected kCodersUnpackSize, got " + unsignedByte2);
                }
                throw new IOException("archive doesn't contain enough packed streams");
            }
            throw new IOException("External unsupported");
        }
        throw new IOException("Expected kFolder, got " + unsignedByte);
    }

    private void readUnpackInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        getUnsignedByte(byteBuffer);
        int readUint64 = (int) readUint64(byteBuffer);
        Folder[] folderArr = new Folder[readUint64];
        archive2.folders = folderArr;
        getUnsignedByte(byteBuffer);
        for (int i = 0; i < readUint64; i++) {
            folderArr[i] = readFolder(byteBuffer);
        }
        getUnsignedByte(byteBuffer);
        for (int i2 = 0; i2 < readUint64; i2++) {
            Folder folder = folderArr[i2];
            assertFitsIntoNonNegativeInt("totalOutputStreams", folder.totalOutputStreams);
            folder.unpackSizes = new long[((int) folder.totalOutputStreams)];
            for (int i3 = 0; ((long) i3) < folder.totalOutputStreams; i3++) {
                folder.unpackSizes[i3] = readUint64(byteBuffer);
            }
        }
        if (getUnsignedByte(byteBuffer) == 10) {
            BitSet readAllOrBits = readAllOrBits(byteBuffer, readUint64);
            for (int i4 = 0; i4 < readUint64; i4++) {
                if (readAllOrBits.get(i4)) {
                    folderArr[i4].hasCrc = true;
                    folderArr[i4].crc = ((long) getInt(byteBuffer)) & 4294967295L;
                } else {
                    folderArr[i4].hasCrc = false;
                }
            }
            getUnsignedByte(byteBuffer);
        }
    }

    private void sanityCheckSubStreamsInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int i;
        int unsignedByte = getUnsignedByte(byteBuffer);
        LinkedList<Integer> linkedList = new LinkedList<>();
        int i2 = 0;
        if (unsignedByte == 13) {
            for (int i3 = 0; i3 < archiveStatistics.numberOfFolders; i3++) {
                linkedList.add(Integer.valueOf(assertFitsIntoNonNegativeInt("numStreams", readUint64(byteBuffer))));
            }
            long unused = archiveStatistics.numberOfUnpackSubStreams = ((Long) linkedList.stream().collect(StreamsKt$$ExternalSyntheticApiModelOutline0.m((ToLongFunction) new SevenZFile$$ExternalSyntheticLambda6()))).longValue();
            unsignedByte = getUnsignedByte(byteBuffer);
        } else {
            long unused2 = archiveStatistics.numberOfUnpackSubStreams = (long) archiveStatistics.numberOfFolders;
        }
        assertFitsIntoNonNegativeInt("totalUnpackStreams", archiveStatistics.numberOfUnpackSubStreams);
        if (unsignedByte == 9) {
            for (Integer intValue : linkedList) {
                int intValue2 = intValue.intValue();
                if (intValue2 != 0) {
                    int i4 = 0;
                    while (i4 < intValue2 - 1) {
                        if (readUint64(byteBuffer) >= 0) {
                            i4++;
                        } else {
                            throw new IOException("negative unpackSize");
                        }
                    }
                    continue;
                }
            }
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        if (!linkedList.isEmpty()) {
            int i5 = 0;
            for (Integer intValue3 : linkedList) {
                int intValue4 = intValue3.intValue();
                if (intValue4 == 1 && archiveStatistics.folderHasCrc != null) {
                    int i6 = i5 + 1;
                    if (!archiveStatistics.folderHasCrc.get(i5)) {
                        i5 = i6;
                    } else {
                        i5 = i6;
                    }
                }
                i2 += intValue4;
            }
            i = i2;
        } else if (archiveStatistics.folderHasCrc == null) {
            i = archiveStatistics.numberOfFolders;
        } else {
            i = archiveStatistics.numberOfFolders - archiveStatistics.folderHasCrc.cardinality();
        }
        if (unsignedByte == 10) {
            assertFitsIntoNonNegativeInt("numDigests", (long) i);
            long cardinality = (long) (readAllOrBits(byteBuffer, i).cardinality() * 4);
            if (skipBytesFully(byteBuffer, cardinality) >= cardinality) {
                unsignedByte = getUnsignedByte(byteBuffer);
            } else {
                throw new IOException("invalid number of missing CRCs in SubStreamInfo");
            }
        }
        if (unsignedByte != 0) {
            throw new IOException("Badly terminated SubStreamsInfo");
        }
    }

    private void readSubStreamsInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        Archive archive3 = archive2;
        for (Folder folder : archive3.folders) {
            folder.numUnpackSubStreams = 1;
        }
        long length = (long) archive3.folders.length;
        int unsignedByte = getUnsignedByte(byteBuffer);
        if (unsignedByte == 13) {
            long j = 0;
            for (Folder folder2 : archive3.folders) {
                long readUint64 = readUint64(byteBuffer);
                folder2.numUnpackSubStreams = (int) readUint64;
                j += readUint64;
            }
            unsignedByte = getUnsignedByte(byteBuffer);
            length = j;
        }
        int i = (int) length;
        SubStreamsInfo subStreamsInfo = new SubStreamsInfo();
        subStreamsInfo.unpackSizes = new long[i];
        subStreamsInfo.hasCrc = new BitSet(i);
        subStreamsInfo.crcs = new long[i];
        int i2 = 0;
        for (Folder folder3 : archive3.folders) {
            if (folder3.numUnpackSubStreams != 0) {
                long j2 = 0;
                if (unsignedByte == 9) {
                    int i3 = 0;
                    while (i3 < folder3.numUnpackSubStreams - 1) {
                        long readUint642 = readUint64(byteBuffer);
                        subStreamsInfo.unpackSizes[i2] = readUint642;
                        j2 += readUint642;
                        i3++;
                        i2++;
                    }
                }
                if (j2 <= folder3.getUnpackSize()) {
                    subStreamsInfo.unpackSizes[i2] = folder3.getUnpackSize() - j2;
                    i2++;
                } else {
                    throw new IOException("sum of unpack sizes of folder exceeds total unpack size");
                }
            }
        }
        if (unsignedByte == 9) {
            unsignedByte = getUnsignedByte(byteBuffer);
        }
        int i4 = 0;
        for (Folder folder4 : archive3.folders) {
            if (folder4.numUnpackSubStreams != 1 || !folder4.hasCrc) {
                i4 += folder4.numUnpackSubStreams;
            }
        }
        if (unsignedByte == 10) {
            ByteBuffer byteBuffer2 = byteBuffer;
            BitSet readAllOrBits = readAllOrBits(byteBuffer2, i4);
            long[] jArr = new long[i4];
            for (int i5 = 0; i5 < i4; i5++) {
                if (readAllOrBits.get(i5)) {
                    jArr[i5] = ((long) getInt(byteBuffer2)) & 4294967295L;
                }
            }
            int i6 = 0;
            int i7 = 0;
            for (Folder folder5 : archive3.folders) {
                if (folder5.numUnpackSubStreams != 1 || !folder5.hasCrc) {
                    for (int i8 = 0; i8 < folder5.numUnpackSubStreams; i8++) {
                        subStreamsInfo.hasCrc.set(i6, readAllOrBits.get(i7));
                        subStreamsInfo.crcs[i6] = jArr[i7];
                        i6++;
                        i7++;
                    }
                } else {
                    subStreamsInfo.hasCrc.set(i6, true);
                    subStreamsInfo.crcs[i6] = folder5.crc;
                    i6++;
                }
            }
            getUnsignedByte(byteBuffer2);
        }
        archive3.subStreamsInfo = subStreamsInfo;
    }

    private int sanityCheckFolder(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        ArchiveStatistics archiveStatistics2 = archiveStatistics;
        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("numCoders", readUint64(byteBuffer2));
        if (assertFitsIntoNonNegativeInt != 0) {
            long unused = archiveStatistics2.numberOfCoders = archiveStatistics2.numberOfCoders + ((long) assertFitsIntoNonNegativeInt);
            int i = 0;
            long j = 0;
            long j2 = 0;
            int i2 = 0;
            while (true) {
                long j3 = 1;
                boolean z = true;
                if (i2 < assertFitsIntoNonNegativeInt) {
                    int unsignedByte = getUnsignedByte(byteBuffer2);
                    get(byteBuffer2, new byte[(unsignedByte & 15)]);
                    boolean z2 = (unsignedByte & 16) == 0;
                    if ((unsignedByte & 32) == 0) {
                        z = false;
                    }
                    if ((unsignedByte & 128) == 0) {
                        if (z2) {
                            j++;
                        } else {
                            j += (long) assertFitsIntoNonNegativeInt("numInStreams", readUint64(byteBuffer2));
                            j3 = (long) assertFitsIntoNonNegativeInt("numOutStreams", readUint64(byteBuffer2));
                        }
                        j2 += j3;
                        if (z) {
                            long assertFitsIntoNonNegativeInt2 = (long) assertFitsIntoNonNegativeInt("propertiesSize", readUint64(byteBuffer2));
                            if (skipBytesFully(byteBuffer2, assertFitsIntoNonNegativeInt2) < assertFitsIntoNonNegativeInt2) {
                                throw new IOException("invalid propertiesSize in folder");
                            }
                        }
                        i2++;
                    } else {
                        throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
                    }
                } else {
                    assertFitsIntoNonNegativeInt("totalInStreams", j);
                    assertFitsIntoNonNegativeInt("totalOutStreams", j2);
                    long unused2 = archiveStatistics2.numberOfOutStreams = archiveStatistics2.numberOfOutStreams + j2;
                    long unused3 = archiveStatistics2.numberOfInStreams = archiveStatistics2.numberOfInStreams + j;
                    if (j2 != 0) {
                        int assertFitsIntoNonNegativeInt3 = assertFitsIntoNonNegativeInt("numBindPairs", j2 - 1);
                        long j4 = (long) assertFitsIntoNonNegativeInt3;
                        if (j >= j4) {
                            BitSet bitSet = new BitSet((int) j);
                            int i3 = 0;
                            while (i3 < assertFitsIntoNonNegativeInt3) {
                                int assertFitsIntoNonNegativeInt4 = assertFitsIntoNonNegativeInt("inIndex", readUint64(byteBuffer2));
                                if (j > ((long) assertFitsIntoNonNegativeInt4)) {
                                    bitSet.set(assertFitsIntoNonNegativeInt4);
                                    if (j2 > ((long) assertFitsIntoNonNegativeInt("outIndex", readUint64(byteBuffer2)))) {
                                        i3++;
                                    } else {
                                        throw new IOException("outIndex is bigger than number of outStreams");
                                    }
                                } else {
                                    throw new IOException("inIndex is bigger than number of inStreams");
                                }
                            }
                            int assertFitsIntoNonNegativeInt5 = assertFitsIntoNonNegativeInt("numPackedStreams", j - j4);
                            if (assertFitsIntoNonNegativeInt5 != 1) {
                                while (i < assertFitsIntoNonNegativeInt5) {
                                    if (((long) assertFitsIntoNonNegativeInt("packedStreamIndex", readUint64(byteBuffer2))) < j) {
                                        i++;
                                    } else {
                                        throw new IOException("packedStreamIndex is bigger than number of totalInStreams");
                                    }
                                }
                            } else if (bitSet.nextClearBit(0) == -1) {
                                throw new IOException("Couldn't find stream's bind pair index");
                            }
                            return (int) j2;
                        }
                        throw new IOException("Total input streams can't be less than the number of bind pairs");
                    }
                    throw new IOException("Total output streams can't be 0");
                }
            }
        } else {
            throw new IOException("Folder without coders");
        }
    }

    private Folder readFolder(ByteBuffer byteBuffer) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        Folder folder = new Folder();
        int readUint64 = (int) readUint64(byteBuffer2);
        Coder[] coderArr = new Coder[readUint64];
        long j = 0;
        long j2 = 0;
        int i = 0;
        while (i < readUint64) {
            coderArr[i] = new Coder();
            int unsignedByte = getUnsignedByte(byteBuffer2);
            int i2 = unsignedByte & 15;
            boolean z = true;
            boolean z2 = (unsignedByte & 16) == 0;
            boolean z3 = (unsignedByte & 32) != 0;
            if ((unsignedByte & 128) == 0) {
                z = false;
            }
            coderArr[i].decompressionMethodId = new byte[i2];
            get(byteBuffer2, coderArr[i].decompressionMethodId);
            if (z2) {
                coderArr[i].numInStreams = 1;
                coderArr[i].numOutStreams = 1;
            } else {
                coderArr[i].numInStreams = readUint64(byteBuffer2);
                coderArr[i].numOutStreams = readUint64(byteBuffer2);
            }
            j += coderArr[i].numInStreams;
            j2 += coderArr[i].numOutStreams;
            if (z3) {
                coderArr[i].properties = new byte[((int) readUint64(byteBuffer2))];
                get(byteBuffer2, coderArr[i].properties);
            }
            if (!z) {
                i++;
            } else {
                throw new IOException("Alternative methods are unsupported, please report. The reference implementation doesn't support them either.");
            }
        }
        folder.coders = coderArr;
        folder.totalInputStreams = j;
        folder.totalOutputStreams = j2;
        long j3 = j2 - 1;
        int i3 = (int) j3;
        BindPair[] bindPairArr = new BindPair[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            BindPair bindPair = new BindPair();
            bindPairArr[i4] = bindPair;
            bindPair.inIndex = readUint64(byteBuffer2);
            bindPairArr[i4].outIndex = readUint64(byteBuffer2);
        }
        folder.bindPairs = bindPairArr;
        long j4 = j - j3;
        int i5 = (int) j4;
        long[] jArr = new long[i5];
        if (j4 == 1) {
            int i6 = 0;
            while (i6 < ((int) j) && folder.findBindPairForInStream(i6) >= 0) {
                i6++;
            }
            jArr[0] = (long) i6;
        } else {
            for (int i7 = 0; i7 < i5; i7++) {
                jArr[i7] = readUint64(byteBuffer2);
            }
        }
        folder.packedStreams = jArr;
        return folder;
    }

    private BitSet readAllOrBits(ByteBuffer byteBuffer, int i) throws IOException {
        if (getUnsignedByte(byteBuffer) == 0) {
            return readBits(byteBuffer, i);
        }
        BitSet bitSet = new BitSet(i);
        for (int i2 = 0; i2 < i; i2++) {
            bitSet.set(i2, true);
        }
        return bitSet;
    }

    private BitSet readBits(ByteBuffer byteBuffer, int i) throws IOException {
        BitSet bitSet = new BitSet(i);
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            if (i2 == 0) {
                i3 = getUnsignedByte(byteBuffer);
                i2 = 128;
            }
            bitSet.set(i4, (i3 & i2) != 0);
            i2 >>>= 1;
        }
        return bitSet;
    }

    private void sanityCheckFilesInfo(ByteBuffer byteBuffer, ArchiveStatistics archiveStatistics) throws IOException {
        int unused = archiveStatistics.numberOfEntries = assertFitsIntoNonNegativeInt("numFiles", readUint64(byteBuffer));
        int i = -1;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer);
            if (unsignedByte == 0) {
                int access$800 = archiveStatistics.numberOfEntries;
                if (i <= 0) {
                    i = 0;
                }
                int unused2 = archiveStatistics.numberOfEntriesWithStream = access$800 - i;
                return;
            }
            long readUint64 = readUint64(byteBuffer);
            switch (unsignedByte) {
                case 14:
                    i = readBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    break;
                case 15:
                    if (i != -1) {
                        readBits(byteBuffer, i);
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kEmptyFile");
                    }
                case 16:
                    if (i != -1) {
                        readBits(byteBuffer, i);
                        break;
                    } else {
                        throw new IOException("Header format error: kEmptyStream must appear before kAnti");
                    }
                case 17:
                    if (getUnsignedByte(byteBuffer) == 0) {
                        int assertFitsIntoNonNegativeInt = assertFitsIntoNonNegativeInt("file names length", readUint64 - 1);
                        if ((assertFitsIntoNonNegativeInt & 1) == 0) {
                            int i2 = 0;
                            for (int i3 = 0; i3 < assertFitsIntoNonNegativeInt; i3 += 2) {
                                if (getChar(byteBuffer) == 0) {
                                    i2++;
                                }
                            }
                            if (i2 == archiveStatistics.numberOfEntries) {
                                break;
                            } else {
                                throw new IOException("Invalid number of file names (" + i2 + " instead of " + archiveStatistics.numberOfEntries + ")");
                            }
                        } else {
                            throw new IOException("File names length invalid");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 18:
                    int cardinality = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j = (long) (cardinality * 8);
                        if (skipBytesFully(byteBuffer, j) >= j) {
                            break;
                        } else {
                            throw new IOException("invalid creation dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 19:
                    int cardinality2 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j2 = (long) (cardinality2 * 8);
                        if (skipBytesFully(byteBuffer, j2) >= j2) {
                            break;
                        } else {
                            throw new IOException("invalid access dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 20:
                    int cardinality3 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j3 = (long) (cardinality3 * 8);
                        if (skipBytesFully(byteBuffer, j3) >= j3) {
                            break;
                        } else {
                            throw new IOException("invalid modification dates size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 21:
                    int cardinality4 = readAllOrBits(byteBuffer, archiveStatistics.numberOfEntries).cardinality();
                    if (getUnsignedByte(byteBuffer) == 0) {
                        long j4 = (long) (cardinality4 * 4);
                        if (skipBytesFully(byteBuffer, j4) >= j4) {
                            break;
                        } else {
                            throw new IOException("invalid windows attributes size");
                        }
                    } else {
                        throw new IOException("Not implemented");
                    }
                case 24:
                    throw new IOException("kStartPos is unsupported, please report");
                case 25:
                    if (skipBytesFully(byteBuffer, readUint64) >= readUint64) {
                        break;
                    } else {
                        throw new IOException("Incomplete kDummy property");
                    }
                default:
                    if (skipBytesFully(byteBuffer, readUint64) >= readUint64) {
                        break;
                    } else {
                        throw new IOException("Incomplete property of type " + unsignedByte);
                    }
            }
        }
    }

    private void readFilesInfo(ByteBuffer byteBuffer, Archive archive2) throws IOException {
        ByteBuffer byteBuffer2 = byteBuffer;
        Archive archive3 = archive2;
        int readUint64 = (int) readUint64(byteBuffer2);
        HashMap hashMap = new HashMap();
        BitSet bitSet = null;
        BitSet bitSet2 = null;
        BitSet bitSet3 = null;
        while (true) {
            int unsignedByte = getUnsignedByte(byteBuffer2);
            int i = 0;
            if (unsignedByte == 0) {
                int i2 = 0;
                int i3 = 0;
                for (int i4 = 0; i4 < readUint64; i4++) {
                    SevenZArchiveEntry sevenZArchiveEntry = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i4));
                    if (sevenZArchiveEntry != null) {
                        boolean z = true;
                        sevenZArchiveEntry.setHasStream(bitSet == null || !bitSet.get(i4));
                        if (!sevenZArchiveEntry.hasStream()) {
                            sevenZArchiveEntry.setDirectory(bitSet2 == null || !bitSet2.get(i2));
                            if (bitSet3 == null || !bitSet3.get(i2)) {
                                z = false;
                            }
                            sevenZArchiveEntry.setAntiItem(z);
                            sevenZArchiveEntry.setHasCrc(false);
                            sevenZArchiveEntry.setSize(0);
                            i2++;
                        } else if (archive3.subStreamsInfo != null) {
                            sevenZArchiveEntry.setDirectory(false);
                            sevenZArchiveEntry.setAntiItem(false);
                            sevenZArchiveEntry.setHasCrc(archive3.subStreamsInfo.hasCrc.get(i3));
                            sevenZArchiveEntry.setCrcValue(archive3.subStreamsInfo.crcs[i3]);
                            sevenZArchiveEntry.setSize(archive3.subStreamsInfo.unpackSizes[i3]);
                            if (sevenZArchiveEntry.getSize() >= 0) {
                                i3++;
                            } else {
                                throw new IOException("broken archive, entry with negative size");
                            }
                        } else {
                            throw new IOException("Archive contains file with streams but no subStreamsInfo");
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                for (SevenZArchiveEntry sevenZArchiveEntry2 : hashMap.values()) {
                    if (sevenZArchiveEntry2 != null) {
                        arrayList.add(sevenZArchiveEntry2);
                    }
                }
                archive3.files = (SevenZArchiveEntry[]) arrayList.toArray(SevenZArchiveEntry.EMPTY_SEVEN_Z_ARCHIVE_ENTRY_ARRAY);
                calculateStreamMap(archive3);
                return;
            }
            long readUint642 = readUint64(byteBuffer2);
            if (unsignedByte != 25) {
                switch (unsignedByte) {
                    case 14:
                        bitSet = readBits(byteBuffer2, readUint64);
                        break;
                    case 15:
                        bitSet2 = readBits(byteBuffer2, bitSet.cardinality());
                        break;
                    case 16:
                        bitSet3 = readBits(byteBuffer2, bitSet.cardinality());
                        break;
                    case 17:
                        getUnsignedByte(byteBuffer2);
                        int i5 = (int) (readUint642 - 1);
                        byte[] bArr = new byte[i5];
                        get(byteBuffer2, bArr);
                        int i6 = 0;
                        int i7 = 0;
                        while (i < i5) {
                            if (bArr[i] == 0 && bArr[i + 1] == 0) {
                                checkEntryIsInitialized(hashMap, i7);
                                ((SevenZArchiveEntry) hashMap.get(Integer.valueOf(i7))).setName(new String(bArr, i6, i - i6, StandardCharsets.UTF_16LE));
                                i7++;
                                i6 = i + 2;
                            }
                            i += 2;
                            Archive archive4 = archive2;
                        }
                        if (i6 != i5 || i7 != readUint64) {
                            break;
                        } else {
                            break;
                        }
                        break;
                    case 18:
                        BitSet readAllOrBits = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer2);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry3 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry3.setHasCreationDate(readAllOrBits.get(i));
                            if (sevenZArchiveEntry3.getHasCreationDate()) {
                                sevenZArchiveEntry3.setCreationDate(getLong(byteBuffer2));
                            }
                            i++;
                        }
                        break;
                    case 19:
                        BitSet readAllOrBits2 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer2);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry4 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry4.setHasAccessDate(readAllOrBits2.get(i));
                            if (sevenZArchiveEntry4.getHasAccessDate()) {
                                sevenZArchiveEntry4.setAccessDate(getLong(byteBuffer2));
                            }
                            i++;
                        }
                        break;
                    case 20:
                        BitSet readAllOrBits3 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer2);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry5 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry5.setHasLastModifiedDate(readAllOrBits3.get(i));
                            if (sevenZArchiveEntry5.getHasLastModifiedDate()) {
                                sevenZArchiveEntry5.setLastModifiedDate(getLong(byteBuffer2));
                            }
                            i++;
                        }
                        break;
                    case 21:
                        BitSet readAllOrBits4 = readAllOrBits(byteBuffer2, readUint64);
                        getUnsignedByte(byteBuffer2);
                        while (i < readUint64) {
                            checkEntryIsInitialized(hashMap, i);
                            SevenZArchiveEntry sevenZArchiveEntry6 = (SevenZArchiveEntry) hashMap.get(Integer.valueOf(i));
                            sevenZArchiveEntry6.setHasWindowsAttributes(readAllOrBits4.get(i));
                            if (sevenZArchiveEntry6.getHasWindowsAttributes()) {
                                sevenZArchiveEntry6.setWindowsAttributes(getInt(byteBuffer2));
                            }
                            i++;
                        }
                        break;
                    default:
                        skipBytesFully(byteBuffer2, readUint642);
                        break;
                }
            } else {
                skipBytesFully(byteBuffer2, readUint642);
            }
            archive3 = archive2;
        }
        throw new IOException("Error parsing file names");
    }

    private void checkEntryIsInitialized(Map<Integer, SevenZArchiveEntry> map, int i) {
        if (map.get(Integer.valueOf(i)) == null) {
            map.put(Integer.valueOf(i), new SevenZArchiveEntry());
        }
    }

    private void calculateStreamMap(Archive archive2) throws IOException {
        StreamMap streamMap = new StreamMap();
        int length = archive2.folders != null ? archive2.folders.length : 0;
        streamMap.folderFirstPackStreamIndex = new int[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            streamMap.folderFirstPackStreamIndex[i2] = i;
            i += archive2.folders[i2].packedStreams.length;
        }
        int length2 = archive2.packSizes.length;
        streamMap.packStreamOffsets = new long[length2];
        long j = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            streamMap.packStreamOffsets[i3] = j;
            j += archive2.packSizes[i3];
        }
        streamMap.folderFirstFileIndex = new int[length];
        streamMap.fileFolderIndex = new int[archive2.files.length];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < archive2.files.length; i6++) {
            if (archive2.files[i6].hasStream() || i4 != 0) {
                if (i4 == 0) {
                    while (i5 < archive2.folders.length) {
                        streamMap.folderFirstFileIndex[i5] = i6;
                        if (archive2.folders[i5].numUnpackSubStreams > 0) {
                            break;
                        }
                        i5++;
                    }
                    if (i5 >= archive2.folders.length) {
                        throw new IOException("Too few folders in archive");
                    }
                }
                streamMap.fileFolderIndex[i6] = i5;
                if (archive2.files[i6].hasStream() && (i4 = i4 + 1) >= archive2.folders[i5].numUnpackSubStreams) {
                    i5++;
                    i4 = 0;
                }
            } else {
                streamMap.fileFolderIndex[i6] = -1;
            }
        }
        archive2.streamMap = streamMap;
    }

    private void buildDecodingStream(int i, boolean z) throws IOException {
        boolean z2;
        if (this.archive.streamMap != null) {
            int i2 = this.archive.streamMap.fileFolderIndex[i];
            if (i2 < 0) {
                this.deferredBlockStreams.clear();
                return;
            }
            SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
            boolean z3 = false;
            if (this.currentFolderIndex == i2) {
                if (i > 0) {
                    sevenZArchiveEntry.setContentMethods(this.archive.files[i - 1].getContentMethods());
                }
                if (z && sevenZArchiveEntry.getContentMethods() == null) {
                    sevenZArchiveEntry.setContentMethods(this.archive.files[this.archive.streamMap.folderFirstFileIndex[i2]].getContentMethods());
                }
                z2 = true;
            } else {
                this.currentFolderIndex = i2;
                reopenFolderInputStream(i2, sevenZArchiveEntry);
                z2 = false;
            }
            if (z) {
                z3 = skipEntriesWhenNeeded(i, z2, i2);
            }
            if (!z || this.currentEntryIndex != i || z3) {
                InputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry.getSize());
                if (sevenZArchiveEntry.getHasCrc()) {
                    boundedInputStream = new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry.getSize(), sevenZArchiveEntry.getCrcValue());
                }
                this.deferredBlockStreams.add(boundedInputStream);
                return;
            }
            return;
        }
        throw new IOException("Archive doesn't contain stream information to read entries");
    }

    private void reopenFolderInputStream(int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        this.deferredBlockStreams.clear();
        InputStream inputStream = this.currentFolderInputStream;
        if (inputStream != null) {
            inputStream.close();
            this.currentFolderInputStream = null;
        }
        Folder folder = this.archive.folders[i];
        int i2 = this.archive.streamMap.folderFirstPackStreamIndex[i];
        this.currentFolderInputStream = buildDecoderStack(folder, this.archive.streamMap.packStreamOffsets[i2] + this.archive.packPos + 32, i2, sevenZArchiveEntry);
    }

    private boolean skipEntriesWhenNeeded(int i, boolean z, int i2) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = this.archive.files[i];
        if (this.currentEntryIndex == i && !hasCurrentEntryBeenRead()) {
            return false;
        }
        int i3 = this.archive.streamMap.folderFirstFileIndex[this.currentFolderIndex];
        if (z) {
            int i4 = this.currentEntryIndex;
            if (i4 < i) {
                i3 = i4 + 1;
            } else {
                reopenFolderInputStream(i2, sevenZArchiveEntry);
            }
        }
        while (i3 < i) {
            SevenZArchiveEntry sevenZArchiveEntry2 = this.archive.files[i3];
            InputStream boundedInputStream = new BoundedInputStream(this.currentFolderInputStream, sevenZArchiveEntry2.getSize());
            if (sevenZArchiveEntry2.getHasCrc()) {
                boundedInputStream = new CRC32VerifyingInputStream(boundedInputStream, sevenZArchiveEntry2.getSize(), sevenZArchiveEntry2.getCrcValue());
            }
            this.deferredBlockStreams.add(boundedInputStream);
            sevenZArchiveEntry2.setContentMethods(sevenZArchiveEntry.getContentMethods());
            i3++;
        }
        return true;
    }

    private boolean hasCurrentEntryBeenRead() {
        if (this.deferredBlockStreams.isEmpty()) {
            return false;
        }
        ArrayList<InputStream> arrayList = this.deferredBlockStreams;
        InputStream inputStream = arrayList.get(arrayList.size() - 1);
        boolean z = (inputStream instanceof CRC32VerifyingInputStream) && ((CRC32VerifyingInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize();
        if (!(inputStream instanceof BoundedInputStream)) {
            return z;
        }
        if (((BoundedInputStream) inputStream).getBytesRemaining() != this.archive.files[this.currentEntryIndex].getSize()) {
            return true;
        }
        return false;
    }

    private InputStream buildDecoderStack(Folder folder, long j, int i, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        SeekableByteChannel unused = this.channel.position(j);
        AnonymousClass1 r9 = new FilterInputStream(new BufferedInputStream(new BoundedSeekableByteChannelInputStream(this.channel, this.archive.packSizes[i]))) {
            public int read() throws IOException {
                int read = this.in.read();
                if (read >= 0) {
                    count(1);
                }
                return read;
            }

            public int read(byte[] bArr) throws IOException {
                return read(bArr, 0, bArr.length);
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (i2 == 0) {
                    return 0;
                }
                int read = this.in.read(bArr, i, i2);
                if (read >= 0) {
                    count(read);
                }
                return read;
            }

            private void count(int i) {
                SevenZFile sevenZFile = SevenZFile.this;
                long unused = sevenZFile.compressedBytesReadFromCurrentEntry = sevenZFile.compressedBytesReadFromCurrentEntry + ((long) i);
            }
        };
        LinkedList linkedList = new LinkedList();
        InputStream inputStream = r9;
        for (Coder next : folder.getOrderedCoders()) {
            if (next.numInStreams == 1 && next.numOutStreams == 1) {
                SevenZMethod byId = SevenZMethod.byId(next.decompressionMethodId);
                inputStream = Coders.addDecoder(this.fileName, inputStream, folder.getUnpackSizeForCoder(next), next, this.password, this.options.getMaxMemoryLimitInKb());
                linkedList.addFirst(new SevenZMethodConfiguration(byId, Coders.findByMethod(byId).getOptionsFromCoder(next, inputStream)));
            } else {
                throw new IOException("Multi input/output stream coders are not yet supported");
            }
        }
        sevenZArchiveEntry.setContentMethods(linkedList);
        return folder.hasCrc ? new CRC32VerifyingInputStream(inputStream, folder.getUnpackSize(), folder.crc) : inputStream;
    }

    public int read() throws IOException {
        int read = getCurrentStream().read();
        if (read >= 0) {
            this.uncompressedBytesReadFromCurrentEntry++;
        }
        return read;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0046, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        if (r0 != null) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004e, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.io.InputStream getCurrentStream() throws java.io.IOException {
        /*
            r6 = this;
            org.apache.commons.compress.archivers.sevenz.Archive r0 = r6.archive
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry[] r0 = r0.files
            int r1 = r6.currentEntryIndex
            r0 = r0[r1]
            long r0 = r0.getSize()
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x001a
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            byte[] r1 = org.apache.commons.compress.utils.ByteUtils.EMPTY_BYTE_ARRAY
            r0.<init>(r1)
            return r0
        L_0x001a:
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x005b
        L_0x0022:
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            int r0 = r0.size()
            r1 = 1
            r4 = 0
            if (r0 <= r1) goto L_0x0052
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            java.lang.Object r0 = r0.remove(r4)
            java.io.InputStream r0 = (java.io.InputStream) r0
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            org.apache.commons.compress.utils.IOUtils.skip(r0, r4)     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0041
            r0.close()
        L_0x0041:
            r6.compressedBytesReadFromCurrentEntry = r2
            goto L_0x0022
        L_0x0044:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0046 }
        L_0x0046:
            r2 = move-exception
            if (r0 == 0) goto L_0x0051
            r0.close()     // Catch:{ all -> 0x004d }
            goto L_0x0051
        L_0x004d:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x0051:
            throw r2
        L_0x0052:
            java.util.ArrayList<java.io.InputStream> r0 = r6.deferredBlockStreams
            java.lang.Object r0 = r0.get(r4)
            java.io.InputStream r0 = (java.io.InputStream) r0
            return r0
        L_0x005b:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "No current 7z entry (call getNextEntry() first)."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.SevenZFile.getCurrentStream():java.io.InputStream");
    }

    public InputStream getInputStream(SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        int i = 0;
        while (true) {
            if (i >= this.archive.files.length) {
                i = -1;
                break;
            } else if (sevenZArchiveEntry == this.archive.files[i]) {
                break;
            } else {
                i++;
            }
        }
        if (i >= 0) {
            buildDecodingStream(i, true);
            this.currentEntryIndex = i;
            this.currentFolderIndex = this.archive.streamMap.fileFolderIndex[i];
            return getCurrentStream();
        }
        throw new IllegalArgumentException("Can not find " + sevenZArchiveEntry.getName() + " in " + this.fileName);
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = getCurrentStream().read(bArr, i, i2);
        if (read > 0) {
            this.uncompressedBytesReadFromCurrentEntry += (long) read;
        }
        return read;
    }

    public InputStreamStatistics getStatisticsForCurrentEntry() {
        return new InputStreamStatistics() {
            public long getCompressedCount() {
                return SevenZFile.this.compressedBytesReadFromCurrentEntry;
            }

            public long getUncompressedCount() {
                return SevenZFile.this.uncompressedBytesReadFromCurrentEntry;
            }
        };
    }

    private static long readUint64(ByteBuffer byteBuffer) throws IOException {
        long unsignedByte = (long) getUnsignedByte(byteBuffer);
        int i = 128;
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            if ((((long) i) & unsignedByte) == 0) {
                return ((unsignedByte & ((long) (i - 1))) << (i2 * 8)) | j;
            }
            j |= ((long) getUnsignedByte(byteBuffer)) << (i2 * 8);
            i >>>= 1;
        }
        return j;
    }

    private static char getChar(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 2) {
            return byteBuffer.getChar();
        }
        throw new EOFException();
    }

    private static int getInt(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 4) {
            return byteBuffer.getInt();
        }
        throw new EOFException();
    }

    private static long getLong(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 8) {
            return byteBuffer.getLong();
        }
        throw new EOFException();
    }

    private static void get(ByteBuffer byteBuffer, byte[] bArr) throws IOException {
        if (byteBuffer.remaining() >= bArr.length) {
            byteBuffer.get(bArr);
            return;
        }
        throw new EOFException();
    }

    private static int getUnsignedByte(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasRemaining()) {
            return byteBuffer.get() & 255;
        }
        throw new EOFException();
    }

    public static boolean matches(byte[] bArr, int i) {
        if (i < sevenZSignature.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = sevenZSignature;
            if (i2 >= bArr2.length) {
                return true;
            }
            if (bArr[i2] != bArr2[i2]) {
                return false;
            }
            i2++;
        }
    }

    private static long skipBytesFully(ByteBuffer byteBuffer, long j) throws IOException {
        if (j < 1) {
            return 0;
        }
        int position = byteBuffer.position();
        long remaining = (long) byteBuffer.remaining();
        if (remaining < j) {
            j = remaining;
        }
        byteBuffer.position(position + ((int) j));
        return j;
    }

    private void readFully(ByteBuffer byteBuffer) throws IOException {
        byteBuffer.rewind();
        IOUtils.readFully((ReadableByteChannel) this.channel, byteBuffer);
        byteBuffer.flip();
    }

    public String toString() {
        return this.archive.toString();
    }

    public String getDefaultName() {
        if (DEFAULT_FILE_NAME.equals(this.fileName) || this.fileName == null) {
            return null;
        }
        String name = new File(this.fileName).getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf > 0) {
            return name.substring(0, lastIndexOf);
        }
        return name + "~";
    }

    private static byte[] utf16Decode(char[] cArr) throws IOException {
        if (cArr == null) {
            return null;
        }
        ByteBuffer encode = PASSWORD_ENCODER.encode(CharBuffer.wrap(cArr));
        if (encode.hasArray()) {
            return encode.array();
        }
        byte[] bArr = new byte[encode.remaining()];
        encode.get(bArr);
        return bArr;
    }

    private static int assertFitsIntoNonNegativeInt(String str, long j) throws IOException {
        if (j <= 2147483647L && j >= 0) {
            return (int) j;
        }
        throw new IOException("Cannot handle " + str + " " + j);
    }

    private static class ArchiveStatistics {
        /* access modifiers changed from: private */
        public BitSet folderHasCrc;
        /* access modifiers changed from: private */
        public long numberOfCoders;
        /* access modifiers changed from: private */
        public int numberOfEntries;
        /* access modifiers changed from: private */
        public int numberOfEntriesWithStream;
        /* access modifiers changed from: private */
        public int numberOfFolders;
        /* access modifiers changed from: private */
        public long numberOfInStreams;
        /* access modifiers changed from: private */
        public long numberOfOutStreams;
        /* access modifiers changed from: private */
        public int numberOfPackedStreams;
        /* access modifiers changed from: private */
        public long numberOfUnpackSubStreams;

        private long bindPairSize() {
            return 16;
        }

        private long coderSize() {
            return 22;
        }

        private long entrySize() {
            return 100;
        }

        private long folderSize() {
            return 30;
        }

        private ArchiveStatistics() {
        }

        public String toString() {
            return "Archive with " + this.numberOfEntries + " entries in " + this.numberOfFolders + " folders. Estimated size " + (estimateSize() / 1024) + " kB.";
        }

        /* access modifiers changed from: package-private */
        public long estimateSize() {
            int i = this.numberOfPackedStreams;
            long folderSize = (((long) i) * 16) + ((long) (i / 8)) + (((long) this.numberOfFolders) * folderSize()) + (this.numberOfCoders * coderSize()) + ((this.numberOfOutStreams - ((long) this.numberOfFolders)) * bindPairSize());
            long j = this.numberOfInStreams;
            long j2 = this.numberOfOutStreams;
            return (folderSize + (((j - j2) + ((long) this.numberOfFolders)) * 8) + (j2 * 8) + (((long) this.numberOfEntries) * entrySize()) + streamMapSize()) * 2;
        }

        /* access modifiers changed from: package-private */
        public void assertValidity(int i) throws IOException {
            int i2 = this.numberOfEntriesWithStream;
            if (i2 > 0 && this.numberOfFolders == 0) {
                throw new IOException("archive with entries but no folders");
            } else if (((long) i2) <= this.numberOfUnpackSubStreams) {
                long estimateSize = estimateSize() / 1024;
                if (((long) i) < estimateSize) {
                    throw new MemoryLimitException(estimateSize, i);
                }
            } else {
                throw new IOException("archive doesn't contain enough substreams for entries");
            }
        }

        private long streamMapSize() {
            return (long) ((this.numberOfFolders * 8) + (this.numberOfPackedStreams * 8) + (this.numberOfEntries * 4));
        }
    }
}
