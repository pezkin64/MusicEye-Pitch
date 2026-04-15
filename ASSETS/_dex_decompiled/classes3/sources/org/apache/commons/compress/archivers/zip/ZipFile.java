package org.apache.commons.compress.archivers.zip;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.BoundedArchiveInputStream;
import org.apache.commons.compress.utils.BoundedSeekableByteChannelInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class ZipFile implements Closeable {
    static final int BYTE_SHIFT = 8;
    private static final int CFD_DISK_OFFSET = 6;
    private static final int CFD_LOCATOR_OFFSET = 16;
    private static final int CFD_LOCATOR_RELATIVE_OFFSET = 8;
    private static final int CFH_LEN = 42;
    private static final long CFH_SIG = ZipLong.getValue(ZipArchiveOutputStream.CFH_SIG);
    private static final int HASH_SIZE = 509;
    private static final long LFH_OFFSET_FOR_FILENAME_LENGTH = 26;
    private static final int MAX_EOCD_SIZE = 65557;
    static final int MIN_EOCD_SIZE = 22;
    static final int NIBLET_MASK = 15;
    private static final byte[] ONE_ZERO_BYTE = new byte[1];
    private static final int POS_0 = 0;
    private static final int POS_1 = 1;
    private static final int POS_2 = 2;
    private static final int POS_3 = 3;
    private static final int ZIP64_EOCDL_LENGTH = 20;
    private static final int ZIP64_EOCDL_LOCATOR_OFFSET = 8;
    private static final int ZIP64_EOCD_CFD_DISK_OFFSET = 20;
    private static final int ZIP64_EOCD_CFD_LOCATOR_OFFSET = 48;
    private static final int ZIP64_EOCD_CFD_LOCATOR_RELATIVE_OFFSET = 24;
    /* access modifiers changed from: private */
    public final SeekableByteChannel archive;
    private final String archiveName;
    private long centralDirectoryStartDiskNumber;
    private long centralDirectoryStartOffset;
    private long centralDirectoryStartRelativeOffset;
    private final ByteBuffer cfhBbuf;
    private final byte[] cfhBuf;
    private volatile boolean closed;
    private final ByteBuffer dwordBbuf;
    private final byte[] dwordBuf;
    private final String encoding;
    private final List<ZipArchiveEntry> entries;
    private final boolean isSplitZipArchive;
    private final Map<String, LinkedList<ZipArchiveEntry>> nameMap;
    private final Comparator<ZipArchiveEntry> offsetComparator;
    private final ByteBuffer shortBbuf;
    private final byte[] shortBuf;
    private final boolean useUnicodeExtraFields;
    private final ByteBuffer wordBbuf;
    private final byte[] wordBuf;
    private final ZipEncoding zipEncoding;

    public ZipFile(File file) throws IOException {
        this(file, "UTF8");
    }

    public ZipFile(String str) throws IOException {
        this(new File(str), "UTF8");
    }

    public ZipFile(String str, String str2) throws IOException {
        this(new File(str), str2, true);
    }

    public ZipFile(File file, String str) throws IOException {
        this(file, str, true);
    }

    public ZipFile(File file, String str, boolean z) throws IOException {
        this(file, str, z, false);
    }

    public ZipFile(File file, String str, boolean z, boolean z2) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StreamsKt$$ExternalSyntheticApiModelOutline0.m$3()), new FileAttribute[0]), file.getAbsolutePath(), str, z, true, z2);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this(seekableByteChannel, "unknown archive", "UTF8", true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        this(seekableByteChannel, "unknown archive", str, true);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z) throws IOException {
        this(seekableByteChannel, str, str2, z, false, false);
    }

    public ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z, boolean z2) throws IOException {
        this(seekableByteChannel, str, str2, z, false, z2);
    }

    private ZipFile(SeekableByteChannel seekableByteChannel, String str, String str2, boolean z, boolean z2, boolean z3) throws IOException {
        this.entries = new LinkedList();
        this.nameMap = new HashMap(HASH_SIZE);
        this.closed = true;
        byte[] bArr = new byte[8];
        this.dwordBuf = bArr;
        byte[] bArr2 = new byte[4];
        this.wordBuf = bArr2;
        byte[] bArr3 = new byte[42];
        this.cfhBuf = bArr3;
        byte[] bArr4 = new byte[2];
        this.shortBuf = bArr4;
        this.dwordBbuf = ByteBuffer.wrap(bArr);
        this.wordBbuf = ByteBuffer.wrap(bArr2);
        this.cfhBbuf = ByteBuffer.wrap(bArr3);
        this.shortBbuf = ByteBuffer.wrap(bArr4);
        this.offsetComparator = StreamsKt$$ExternalSyntheticApiModelOutline0.m((ToLongFunction) new ZipFile$$ExternalSyntheticLambda3()).thenComparingLong(new ZipFile$$ExternalSyntheticLambda4());
        this.isSplitZipArchive = seekableByteChannel instanceof ZipSplitReadOnlySeekableByteChannel;
        this.archiveName = str;
        this.encoding = str2;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(str2);
        this.useUnicodeExtraFields = z;
        this.archive = seekableByteChannel;
        try {
            Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory = populateFromCentralDirectory();
            if (!z3) {
                resolveLocalFileHeaderData(populateFromCentralDirectory);
            }
            fillNameMap();
            this.closed = false;
        } catch (IOException e) {
            throw new IOException("Error on ZipFile " + str, e);
        } catch (Throwable th) {
            this.closed = true;
            if (z2) {
                IOUtils.closeQuietly(this.archive);
            }
            throw th;
        }
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void close() throws IOException {
        this.closed = true;
        StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.archive);
    }

    public static void closeQuietly(ZipFile zipFile) {
        IOUtils.closeQuietly(zipFile);
    }

    public Enumeration<ZipArchiveEntry> getEntries() {
        return Collections.enumeration(this.entries);
    }

    public Enumeration<ZipArchiveEntry> getEntriesInPhysicalOrder() {
        ZipArchiveEntry[] zipArchiveEntryArr = (ZipArchiveEntry[]) this.entries.toArray(ZipArchiveEntry.EMPTY_ZIP_ARCHIVE_ENTRY_ARRAY);
        Arrays.sort(zipArchiveEntryArr, this.offsetComparator);
        return Collections.enumeration(Arrays.asList(zipArchiveEntryArr));
    }

    public ZipArchiveEntry getEntry(String str) {
        LinkedList linkedList = this.nameMap.get(str);
        if (linkedList != null) {
            return (ZipArchiveEntry) linkedList.getFirst();
        }
        return null;
    }

    public Iterable<ZipArchiveEntry> getEntries(String str) {
        List list = this.nameMap.get(str);
        if (list != null) {
            return list;
        }
        return Collections.EMPTY_LIST;
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [java.lang.Object[]] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Iterable<org.apache.commons.compress.archivers.zip.ZipArchiveEntry> getEntriesInPhysicalOrder(java.lang.String r3) {
        /*
            r2 = this;
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry[] r0 = org.apache.commons.compress.archivers.zip.ZipArchiveEntry.EMPTY_ZIP_ARCHIVE_ENTRY_ARRAY
            java.util.Map<java.lang.String, java.util.LinkedList<org.apache.commons.compress.archivers.zip.ZipArchiveEntry>> r1 = r2.nameMap
            boolean r1 = r1.containsKey(r3)
            if (r1 == 0) goto L_0x001e
            java.util.Map<java.lang.String, java.util.LinkedList<org.apache.commons.compress.archivers.zip.ZipArchiveEntry>> r1 = r2.nameMap
            java.lang.Object r3 = r1.get(r3)
            java.util.LinkedList r3 = (java.util.LinkedList) r3
            java.lang.Object[] r3 = r3.toArray(r0)
            r0 = r3
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry[] r0 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry[]) r0
            java.util.Comparator<org.apache.commons.compress.archivers.zip.ZipArchiveEntry> r3 = r2.offsetComparator
            java.util.Arrays.sort(r0, r3)
        L_0x001e:
            java.util.List r3 = java.util.Arrays.asList(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipFile.getEntriesInPhysicalOrder(java.lang.String):java.lang.Iterable");
    }

    public boolean canReadEntryData(ZipArchiveEntry zipArchiveEntry) {
        return ZipUtil.canHandleEntryData(zipArchiveEntry);
    }

    public InputStream getRawInputStream(ZipArchiveEntry zipArchiveEntry) {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        long dataOffset = zipArchiveEntry.getDataOffset();
        if (dataOffset == -1) {
            return null;
        }
        return createBoundedInputStream(dataOffset, zipArchiveEntry.getCompressedSize());
    }

    public void copyRawEntries(ZipArchiveOutputStream zipArchiveOutputStream, ZipArchiveEntryPredicate zipArchiveEntryPredicate) throws IOException {
        Enumeration<ZipArchiveEntry> entriesInPhysicalOrder = getEntriesInPhysicalOrder();
        while (entriesInPhysicalOrder.hasMoreElements()) {
            ZipArchiveEntry nextElement = entriesInPhysicalOrder.nextElement();
            if (zipArchiveEntryPredicate.test(nextElement)) {
                zipArchiveOutputStream.addRawArchiveEntry(nextElement, getRawInputStream(nextElement));
            }
        }
    }

    public InputStream getInputStream(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (!(zipArchiveEntry instanceof Entry)) {
            return null;
        }
        ZipUtil.checkRequestedFeatures(zipArchiveEntry);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(createBoundedInputStream(getDataOffset(zipArchiveEntry), zipArchiveEntry.getCompressedSize()));
        switch (AnonymousClass2.$SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod[ZipMethod.getMethodByCode(zipArchiveEntry.getMethod()).ordinal()]) {
            case 1:
                return new StoredStatisticsStream(bufferedInputStream);
            case 2:
                return new UnshrinkingInputStream(bufferedInputStream);
            case 3:
                try {
                    return new ExplodingInputStream(zipArchiveEntry.getGeneralPurposeBit().getSlidingDictionarySize(), zipArchiveEntry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), bufferedInputStream);
                } catch (IllegalArgumentException e) {
                    throw new IOException("bad IMPLODE data", e);
                }
            case 4:
                final Inflater inflater = new Inflater(true);
                return new InflaterInputStreamWithStatistics(new SequenceInputStream(bufferedInputStream, new ByteArrayInputStream(ONE_ZERO_BYTE)), inflater) {
                    public void close() throws IOException {
                        try {
                            super.close();
                        } finally {
                            inflater.end();
                        }
                    }
                };
            case 5:
                return new BZip2CompressorInputStream(bufferedInputStream);
            case 6:
                return new Deflate64CompressorInputStream((InputStream) bufferedInputStream);
            default:
                throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(zipArchiveEntry.getMethod()), zipArchiveEntry);
        }
    }

    /* renamed from: org.apache.commons.compress.archivers.zip.ZipFile$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod;

        /* JADX WARNING: Can't wrap try/catch for region: R(38:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(40:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cc */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x00d8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.compress.archivers.zip.ZipMethod[] r0 = org.apache.commons.compress.archivers.zip.ZipMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod = r0
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.STORED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.UNSHRINKING     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.IMPLODING     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.DEFLATED     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.BZIP2     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.ENHANCED_DEFLATED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0054 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.AES_ENCRYPTED     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0060 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.EXPANDING_LEVEL_1     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x006c }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.EXPANDING_LEVEL_2     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0078 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.EXPANDING_LEVEL_3     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0084 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.EXPANDING_LEVEL_4     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x0090 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.JPEG     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x009c }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.LZMA     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00a8 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.PKWARE_IMPLODING     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00b4 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.PPMD     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00c0 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.TOKENIZATION     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00cc }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00d8 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.WAVPACK     // Catch:{ NoSuchFieldError -> 0x00d8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00d8 }
                r2 = 18
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00d8 }
            L_0x00d8:
                int[] r0 = $SwitchMap$org$apache$commons$compress$archivers$zip$ZipMethod     // Catch:{ NoSuchFieldError -> 0x00e4 }
                org.apache.commons.compress.archivers.zip.ZipMethod r1 = org.apache.commons.compress.archivers.zip.ZipMethod.XZ     // Catch:{ NoSuchFieldError -> 0x00e4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00e4 }
                r2 = 19
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00e4 }
            L_0x00e4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipFile.AnonymousClass2.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        if (r3 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0025, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0026, code lost:
        r0.addSuppressed(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0029, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getUnixSymlink(org.apache.commons.compress.archivers.zip.ZipArchiveEntry r3) throws java.io.IOException {
        /*
            r2 = this;
            if (r3 == 0) goto L_0x002a
            boolean r0 = r3.isUnixSymlink()
            if (r0 == 0) goto L_0x002a
            java.io.InputStream r3 = r2.getInputStream(r3)
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = r2.zipEncoding     // Catch:{ all -> 0x001c }
            byte[] r1 = org.apache.commons.compress.utils.IOUtils.toByteArray(r3)     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r0.decode(r1)     // Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x001b
            r3.close()
        L_0x001b:
            return r0
        L_0x001c:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            if (r3 == 0) goto L_0x0029
            r3.close()     // Catch:{ all -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r3 = move-exception
            r0.addSuppressed(r3)
        L_0x0029:
            throw r1
        L_0x002a:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.zip.ZipFile.getUnixSymlink(org.apache.commons.compress.archivers.zip.ZipArchiveEntry):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (!this.closed) {
                PrintStream printStream = System.err;
                printStream.println("Cleaning up unclosed ZipFile for archive " + this.archiveName);
                close();
            }
        } finally {
            super.finalize();
        }
    }

    private Map<ZipArchiveEntry, NameAndComment> populateFromCentralDirectory() throws IOException {
        HashMap hashMap = new HashMap();
        positionAtCentralDirectory();
        this.centralDirectoryStartOffset = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.archive);
        this.wordBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
        long value = ZipLong.getValue(this.wordBuf);
        if (value == CFH_SIG || !startsWithLocalFileHeader()) {
            while (value == CFH_SIG) {
                readCentralDirectoryEntry(hashMap);
                this.wordBbuf.rewind();
                IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
                value = ZipLong.getValue(this.wordBuf);
            }
            return hashMap;
        }
        throw new IOException("Central directory is empty, can't expand corrupt archive.");
    }

    private void readCentralDirectoryEntry(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        this.cfhBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.cfhBbuf);
        Entry entry = new Entry();
        int value = ZipShort.getValue(this.cfhBuf, 0);
        entry.setVersionMadeBy(value);
        entry.setPlatform((value >> 8) & 15);
        entry.setVersionRequired(ZipShort.getValue(this.cfhBuf, 2));
        GeneralPurposeBit parse = GeneralPurposeBit.parse(this.cfhBuf, 4);
        boolean usesUTF8ForNames = parse.usesUTF8ForNames();
        ZipEncoding zipEncoding2 = usesUTF8ForNames ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
        if (usesUTF8ForNames) {
            entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
        }
        entry.setGeneralPurposeBit(parse);
        entry.setRawFlag(ZipShort.getValue(this.cfhBuf, 4));
        entry.setMethod(ZipShort.getValue(this.cfhBuf, 6));
        entry.setTime(ZipUtil.dosToJavaTime(ZipLong.getValue(this.cfhBuf, 8)));
        entry.setCrc(ZipLong.getValue(this.cfhBuf, 12));
        long value2 = ZipLong.getValue(this.cfhBuf, 16);
        if (value2 >= 0) {
            entry.setCompressedSize(value2);
            long value3 = ZipLong.getValue(this.cfhBuf, 20);
            if (value3 >= 0) {
                entry.setSize(value3);
                int value4 = ZipShort.getValue(this.cfhBuf, 24);
                if (value4 >= 0) {
                    int value5 = ZipShort.getValue(this.cfhBuf, 26);
                    if (value5 >= 0) {
                        int value6 = ZipShort.getValue(this.cfhBuf, 28);
                        if (value6 >= 0) {
                            entry.setDiskNumberStart((long) ZipShort.getValue(this.cfhBuf, 30));
                            entry.setInternalAttributes(ZipShort.getValue(this.cfhBuf, 32));
                            entry.setExternalAttributes(ZipLong.getValue(this.cfhBuf, 34));
                            byte[] readRange = IOUtils.readRange((ReadableByteChannel) this.archive, value4);
                            if (readRange.length >= value4) {
                                entry.setName(zipEncoding2.decode(readRange), readRange);
                                entry.setLocalHeaderOffset(ZipLong.getValue(this.cfhBuf, 38));
                                this.entries.add(entry);
                                byte[] readRange2 = IOUtils.readRange((ReadableByteChannel) this.archive, value5);
                                if (readRange2.length >= value5) {
                                    try {
                                        entry.setCentralDirectoryExtra(readRange2);
                                        setSizesAndOffsetFromZip64Extra(entry);
                                        sanityCheckLFHOffset(entry);
                                        byte[] readRange3 = IOUtils.readRange((ReadableByteChannel) this.archive, value6);
                                        if (readRange3.length >= value6) {
                                            entry.setComment(zipEncoding2.decode(readRange3));
                                            if (!usesUTF8ForNames && this.useUnicodeExtraFields) {
                                                map.put(entry, new NameAndComment(readRange, readRange3));
                                            }
                                            entry.setStreamContiguous(true);
                                            return;
                                        }
                                        throw new EOFException();
                                    } catch (RuntimeException e) {
                                        ZipException zipException = new ZipException("Invalid extra data in entry " + entry.getName());
                                        zipException.initCause(e);
                                        throw zipException;
                                    }
                                } else {
                                    throw new EOFException();
                                }
                            } else {
                                throw new EOFException();
                            }
                        } else {
                            throw new IOException("broken archive, entry with negative commentLen");
                        }
                    } else {
                        throw new IOException("broken archive, entry with negative extraLen");
                    }
                } else {
                    throw new IOException("broken archive, entry with negative fileNameLen");
                }
            } else {
                throw new IOException("broken archive, entry with negative size");
            }
        } else {
            throw new IOException("broken archive, entry with negative compressed size");
        }
    }

    private void sanityCheckLFHOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        if (zipArchiveEntry.getDiskNumberStart() < 0) {
            throw new IOException("broken archive, entry with negative disk number");
        } else if (zipArchiveEntry.getLocalHeaderOffset() < 0) {
            throw new IOException("broken archive, entry with negative local file header offset");
        } else if (this.isSplitZipArchive) {
            if (zipArchiveEntry.getDiskNumberStart() > this.centralDirectoryStartDiskNumber) {
                throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts on a later disk than central directory");
            } else if (zipArchiveEntry.getDiskNumberStart() == this.centralDirectoryStartDiskNumber && zipArchiveEntry.getLocalHeaderOffset() > this.centralDirectoryStartRelativeOffset) {
                throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts after central directory");
            }
        } else if (zipArchiveEntry.getLocalHeaderOffset() > this.centralDirectoryStartOffset) {
            throw new IOException("local file header for " + zipArchiveEntry.getName() + " starts after central directory");
        }
    }

    private void setSizesAndOffsetFromZip64Extra(ZipArchiveEntry zipArchiveEntry) throws IOException {
        ZipExtraField extraField = zipArchiveEntry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extraField == null || (extraField instanceof Zip64ExtendedInformationExtraField)) {
            Zip64ExtendedInformationExtraField zip64ExtendedInformationExtraField = (Zip64ExtendedInformationExtraField) extraField;
            if (zip64ExtendedInformationExtraField != null) {
                boolean z = true;
                boolean z2 = zipArchiveEntry.getSize() == 4294967295L;
                boolean z3 = zipArchiveEntry.getCompressedSize() == 4294967295L;
                boolean z4 = zipArchiveEntry.getLocalHeaderOffset() == 4294967295L;
                if (zipArchiveEntry.getDiskNumberStart() != 65535) {
                    z = false;
                }
                zip64ExtendedInformationExtraField.reparseCentralDirectoryData(z2, z3, z4, z);
                if (z2) {
                    long longValue = zip64ExtendedInformationExtraField.getSize().getLongValue();
                    if (longValue >= 0) {
                        zipArchiveEntry.setSize(longValue);
                    } else {
                        throw new IOException("broken archive, entry with negative size");
                    }
                } else if (z3) {
                    zip64ExtendedInformationExtraField.setSize(new ZipEightByteInteger(zipArchiveEntry.getSize()));
                }
                if (z3) {
                    long longValue2 = zip64ExtendedInformationExtraField.getCompressedSize().getLongValue();
                    if (longValue2 >= 0) {
                        zipArchiveEntry.setCompressedSize(longValue2);
                    } else {
                        throw new IOException("broken archive, entry with negative compressed size");
                    }
                } else if (z2) {
                    zip64ExtendedInformationExtraField.setCompressedSize(new ZipEightByteInteger(zipArchiveEntry.getCompressedSize()));
                }
                if (z4) {
                    zipArchiveEntry.setLocalHeaderOffset(zip64ExtendedInformationExtraField.getRelativeHeaderOffset().getLongValue());
                }
                if (z) {
                    zipArchiveEntry.setDiskNumberStart(zip64ExtendedInformationExtraField.getDiskStartNumber().getValue());
                    return;
                }
                return;
            }
            return;
        }
        throw new ZipException("archive contains unparseable zip64 extra field");
    }

    private void positionAtCentralDirectory() throws IOException {
        positionAtEndOfCentralDirectoryRecord();
        boolean z = false;
        boolean z2 = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.archive) > 20;
        if (z2) {
            SeekableByteChannel seekableByteChannel = this.archive;
            SeekableByteChannel unused = seekableByteChannel.position(StreamsKt$$ExternalSyntheticApiModelOutline0.m(seekableByteChannel) - 20);
            this.wordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
            z = Arrays.equals(ZipArchiveOutputStream.ZIP64_EOCD_LOC_SIG, this.wordBuf);
        }
        if (!z) {
            if (z2) {
                skipBytes(16);
            }
            positionAtCentralDirectory32();
            return;
        }
        positionAtCentralDirectory64();
    }

    private void positionAtCentralDirectory64() throws IOException {
        if (this.isSplitZipArchive) {
            this.wordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
            long value = ZipLong.getValue(this.wordBuf);
            this.dwordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.dwordBbuf);
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(value, ZipEightByteInteger.getLongValue(this.dwordBuf));
        } else {
            skipBytes(4);
            this.dwordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.dwordBbuf);
            SeekableByteChannel unused = this.archive.position(ZipEightByteInteger.getLongValue(this.dwordBuf));
        }
        this.wordBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
        if (!Arrays.equals(this.wordBuf, ZipArchiveOutputStream.ZIP64_EOCD_SIG)) {
            throw new ZipException("Archive's ZIP64 end of central directory locator is corrupt.");
        } else if (this.isSplitZipArchive) {
            skipBytes(16);
            this.wordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
            this.centralDirectoryStartDiskNumber = ZipLong.getValue(this.wordBuf);
            skipBytes(24);
            this.dwordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.dwordBbuf);
            long longValue = ZipEightByteInteger.getLongValue(this.dwordBuf);
            this.centralDirectoryStartRelativeOffset = longValue;
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, longValue);
        } else {
            skipBytes(44);
            this.dwordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.dwordBbuf);
            this.centralDirectoryStartDiskNumber = 0;
            long longValue2 = ZipEightByteInteger.getLongValue(this.dwordBuf);
            this.centralDirectoryStartRelativeOffset = longValue2;
            SeekableByteChannel unused2 = this.archive.position(longValue2);
        }
    }

    private void positionAtCentralDirectory32() throws IOException {
        if (this.isSplitZipArchive) {
            skipBytes(6);
            this.shortBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.shortBbuf);
            this.centralDirectoryStartDiskNumber = (long) ZipShort.getValue(this.shortBuf);
            skipBytes(8);
            this.wordBbuf.rewind();
            IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
            long value = ZipLong.getValue(this.wordBuf);
            this.centralDirectoryStartRelativeOffset = value;
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(this.centralDirectoryStartDiskNumber, value);
            return;
        }
        skipBytes(16);
        this.wordBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
        this.centralDirectoryStartDiskNumber = 0;
        long value2 = ZipLong.getValue(this.wordBuf);
        this.centralDirectoryStartRelativeOffset = value2;
        SeekableByteChannel unused = this.archive.position(value2);
    }

    private void positionAtEndOfCentralDirectoryRecord() throws IOException {
        if (!tryToLocateSignature(22, 65557, ZipArchiveOutputStream.EOCD_SIG)) {
            throw new ZipException("Archive is not a ZIP archive");
        }
    }

    private boolean tryToLocateSignature(long j, long j2, byte[] bArr) throws IOException {
        long m$1 = this.archive.size() - j;
        long max = Math.max(0, this.archive.size() - j2);
        boolean z = false;
        if (m$1 >= 0) {
            while (true) {
                if (m$1 < max) {
                    break;
                }
                SeekableByteChannel unused = this.archive.position(m$1);
                try {
                    this.wordBbuf.rewind();
                    IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
                    this.wordBbuf.flip();
                    if (this.wordBbuf.get() == bArr[0] && this.wordBbuf.get() == bArr[1] && this.wordBbuf.get() == bArr[2] && this.wordBbuf.get() == bArr[3]) {
                        z = true;
                        break;
                    }
                    m$1--;
                } catch (EOFException unused2) {
                }
            }
        }
        if (z) {
            SeekableByteChannel unused3 = this.archive.position(m$1);
        }
        return z;
    }

    private void skipBytes(int i) throws IOException {
        long m = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.archive) + ((long) i);
        if (m <= this.archive.size()) {
            SeekableByteChannel unused = this.archive.position(m);
            return;
        }
        throw new EOFException();
    }

    private void resolveLocalFileHeaderData(Map<ZipArchiveEntry, NameAndComment> map) throws IOException {
        Iterator<ZipArchiveEntry> it = this.entries.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int[] dataOffset = setDataOffset(entry);
            int i = dataOffset[0];
            int i2 = dataOffset[1];
            skipBytes(i);
            byte[] readRange = IOUtils.readRange((ReadableByteChannel) this.archive, i2);
            if (readRange.length >= i2) {
                try {
                    entry.setExtra(readRange);
                    if (map.containsKey(entry)) {
                        NameAndComment nameAndComment = map.get(entry);
                        ZipUtil.setNameAndCommentFromExtraFields(entry, nameAndComment.name, nameAndComment.comment);
                    }
                } catch (RuntimeException e) {
                    ZipException zipException = new ZipException("Invalid extra data in entry " + entry.getName());
                    zipException.initCause(e);
                    throw zipException;
                }
            } else {
                throw new EOFException();
            }
        }
    }

    private void fillNameMap() {
        for (ZipArchiveEntry next : this.entries) {
            ((LinkedList) this.nameMap.computeIfAbsent(next.getName(), new ZipFile$$ExternalSyntheticLambda2())).addLast(next);
        }
    }

    static /* synthetic */ LinkedList lambda$fillNameMap$0(String str) {
        return new LinkedList();
    }

    private int[] setDataOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        long localHeaderOffset = zipArchiveEntry.getLocalHeaderOffset();
        if (this.isSplitZipArchive) {
            ((ZipSplitReadOnlySeekableByteChannel) this.archive).position(zipArchiveEntry.getDiskNumberStart(), localHeaderOffset + LFH_OFFSET_FOR_FILENAME_LENGTH);
            localHeaderOffset = StreamsKt$$ExternalSyntheticApiModelOutline0.m(this.archive) - LFH_OFFSET_FOR_FILENAME_LENGTH;
        } else {
            SeekableByteChannel unused = this.archive.position(LFH_OFFSET_FOR_FILENAME_LENGTH + localHeaderOffset);
        }
        this.wordBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
        this.wordBbuf.flip();
        this.wordBbuf.get(this.shortBuf);
        int value = ZipShort.getValue(this.shortBuf);
        this.wordBbuf.get(this.shortBuf);
        int value2 = ZipShort.getValue(this.shortBuf);
        zipArchiveEntry.setDataOffset(localHeaderOffset + 30 + ((long) value) + ((long) value2));
        if (zipArchiveEntry.getDataOffset() + zipArchiveEntry.getCompressedSize() <= this.centralDirectoryStartOffset) {
            return new int[]{value, value2};
        }
        throw new IOException("data for " + zipArchiveEntry.getName() + " overlaps with central directory.");
    }

    private long getDataOffset(ZipArchiveEntry zipArchiveEntry) throws IOException {
        long dataOffset = zipArchiveEntry.getDataOffset();
        if (dataOffset != -1) {
            return dataOffset;
        }
        setDataOffset(zipArchiveEntry);
        return zipArchiveEntry.getDataOffset();
    }

    private boolean startsWithLocalFileHeader() throws IOException {
        SeekableByteChannel unused = this.archive.position(0);
        this.wordBbuf.rewind();
        IOUtils.readFully((ReadableByteChannel) this.archive, this.wordBbuf);
        return Arrays.equals(this.wordBuf, ZipArchiveOutputStream.LFH_SIG);
    }

    private BoundedArchiveInputStream createBoundedInputStream(long j, long j2) {
        if (j < 0 || j2 < 0 || j + j2 < j) {
            throw new IllegalArgumentException("Corrupted archive, stream boundaries are out of range");
        } else if (this.archive instanceof FileChannel) {
            return new BoundedFileChannelInputStream(j, j2);
        } else {
            return new BoundedSeekableByteChannelInputStream(j, j2, this.archive);
        }
    }

    private class BoundedFileChannelInputStream extends BoundedArchiveInputStream {
        private final FileChannel archive;

        BoundedFileChannelInputStream(long j, long j2) {
            super(j, j2);
            this.archive = (FileChannel) ZipFile.this.archive;
        }

        /* access modifiers changed from: protected */
        public int read(long j, ByteBuffer byteBuffer) throws IOException {
            int read = this.archive.read(byteBuffer, j);
            byteBuffer.flip();
            return read;
        }
    }

    private static final class NameAndComment {
        /* access modifiers changed from: private */
        public final byte[] comment;
        /* access modifiers changed from: private */
        public final byte[] name;

        private NameAndComment(byte[] bArr, byte[] bArr2) {
            this.name = bArr;
            this.comment = bArr2;
        }
    }

    private static class Entry extends ZipArchiveEntry {
        Entry() {
        }

        public int hashCode() {
            return (super.hashCode() * 3) + ((int) getLocalHeaderOffset()) + ((int) (getLocalHeaderOffset() >> 32));
        }

        public boolean equals(Object obj) {
            if (super.equals(obj)) {
                Entry entry = (Entry) obj;
                if (getLocalHeaderOffset() == entry.getLocalHeaderOffset() && super.getDataOffset() == entry.getDataOffset() && super.getDiskNumberStart() == entry.getDiskNumberStart()) {
                    return true;
                }
                return false;
            }
            return false;
        }
    }

    private static class StoredStatisticsStream extends CountingInputStream implements InputStreamStatistics {
        StoredStatisticsStream(InputStream inputStream) {
            super(inputStream);
        }

        public long getCompressedCount() {
            return super.getBytesRead();
        }

        public long getUncompressedCount() {
            return getCompressedCount();
        }
    }
}
