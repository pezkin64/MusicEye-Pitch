package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.commons.compress.utils.MultiReadOnlySeekableByteChannel;

public class ZipSplitReadOnlySeekableByteChannel extends MultiReadOnlySeekableByteChannel {
    private static final int ZIP_SPLIT_SIGNATURE_LENGTH = 4;
    private final ByteBuffer zipSplitSignatureByteBuffer = ByteBuffer.allocate(4);

    public ZipSplitReadOnlySeekableByteChannel(List<SeekableByteChannel> list) throws IOException {
        super(list);
        assertSplitSignature(list);
    }

    private void assertSplitSignature(List<SeekableByteChannel> list) throws IOException {
        SeekableByteChannel m = StreamsKt$$ExternalSyntheticApiModelOutline0.m((Object) list.get(0));
        SeekableByteChannel unused = m.position(0);
        this.zipSplitSignatureByteBuffer.rewind();
        int unused2 = m.read(this.zipSplitSignatureByteBuffer);
        if (new ZipLong(this.zipSplitSignatureByteBuffer.array()).equals(ZipLong.DD_SIG)) {
            SeekableByteChannel unused3 = m.position(0);
        } else {
            SeekableByteChannel unused4 = m.position(0);
            throw new IOException("The first zip split segment does not begin with split zip file signature");
        }
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel... seekableByteChannelArr) throws IOException {
        if (((SeekableByteChannel[]) Objects.requireNonNull(seekableByteChannelArr, "channels must not be null")).length == 1) {
            return seekableByteChannelArr[0];
        }
        return new ZipSplitReadOnlySeekableByteChannel(Arrays.asList(seekableByteChannelArr));
    }

    public static SeekableByteChannel forOrderedSeekableByteChannels(SeekableByteChannel seekableByteChannel, Iterable<SeekableByteChannel> iterable) throws IOException {
        Objects.requireNonNull(iterable, "channels");
        Objects.requireNonNull(seekableByteChannel, "lastSegmentChannel");
        ArrayList arrayList = new ArrayList();
        for (SeekableByteChannel m : iterable) {
            arrayList.add(StreamsKt$$ExternalSyntheticApiModelOutline0.m((Object) m));
        }
        arrayList.add(seekableByteChannel);
        return forOrderedSeekableByteChannels((SeekableByteChannel[]) arrayList.toArray(new SeekableByteChannel[0]));
    }

    public static SeekableByteChannel buildFromLastSplitSegment(File file) throws IOException {
        if (FileNameUtils.getExtension(file.getCanonicalPath()).equalsIgnoreCase(ArchiveStreamFactory.ZIP)) {
            File parentFile = file.getParentFile();
            String baseName = FileNameUtils.getBaseName(file.getCanonicalPath());
            ArrayList arrayList = new ArrayList();
            Pattern compile = Pattern.compile(Pattern.quote(baseName) + ".[zZ][0-9]+");
            File[] listFiles = parentFile.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    if (compile.matcher(file2.getName()).matches()) {
                        arrayList.add(file2);
                    }
                }
            }
            arrayList.sort(new ZipSplitSegmentComparator());
            return forFiles(file, arrayList);
        }
        throw new IllegalArgumentException("The extension of last zip split segment should be .zip");
    }

    public static SeekableByteChannel forFiles(File... fileArr) throws IOException {
        ArrayList arrayList = new ArrayList();
        for (File m : (File[]) Objects.requireNonNull(fileArr, "files must not be null")) {
            arrayList.add(StreamsKt$$ExternalSyntheticApiModelOutline0.m(m.toPath(), new OpenOption[]{StreamsKt$$ExternalSyntheticApiModelOutline0.m$3()}));
        }
        if (arrayList.size() == 1) {
            return StreamsKt$$ExternalSyntheticApiModelOutline0.m(arrayList.get(0));
        }
        return new ZipSplitReadOnlySeekableByteChannel(arrayList);
    }

    public static SeekableByteChannel forFiles(File file, Iterable<File> iterable) throws IOException {
        Objects.requireNonNull(iterable, "files");
        Objects.requireNonNull(file, "lastSegmentFile");
        ArrayList arrayList = new ArrayList();
        for (File add : iterable) {
            arrayList.add(add);
        }
        arrayList.add(file);
        return forFiles((File[]) arrayList.toArray(new File[0]));
    }

    private static class ZipSplitSegmentComparator implements Comparator<File>, Serializable {
        private static final long serialVersionUID = 20200123;

        private ZipSplitSegmentComparator() {
        }

        public int compare(File file, File file2) {
            String extension = FileNameUtils.getExtension(file.getPath());
            String extension2 = FileNameUtils.getExtension(file2.getPath());
            if (!extension.startsWith(CompressorStreamFactory.Z)) {
                return -1;
            }
            if (!extension2.startsWith(CompressorStreamFactory.Z)) {
                return 1;
            }
            return Integer.valueOf(Integer.parseInt(extension.substring(1))).compareTo(Integer.valueOf(Integer.parseInt(extension2.substring(1))));
        }
    }
}
