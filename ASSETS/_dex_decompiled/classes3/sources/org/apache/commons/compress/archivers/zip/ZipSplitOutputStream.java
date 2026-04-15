package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.OpenOption;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.compress.utils.FileNameUtils;

class ZipSplitOutputStream extends OutputStream {
    private static final long ZIP_SEGMENT_MAX_SIZE = 4294967295L;
    private static final long ZIP_SEGMENT_MIN_SIZE = 65536;
    private long currentSplitSegmentBytesWritten;
    private int currentSplitSegmentIndex;
    private boolean finished;
    private OutputStream outputStream;
    private final byte[] singleByte = new byte[1];
    private final long splitSize;
    private File zipFile;

    public ZipSplitOutputStream(File file, long j) throws IllegalArgumentException, IOException {
        if (j < ZIP_SEGMENT_MIN_SIZE || j > ZIP_SEGMENT_MAX_SIZE) {
            throw new IllegalArgumentException("zip split segment size should between 64K and 4,294,967,295");
        }
        this.zipFile = file;
        this.splitSize = j;
        this.outputStream = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(file.toPath(), new OpenOption[0]);
        writeZipSplitSignature();
    }

    public void prepareToWriteUnsplittableContent(long j) throws IllegalArgumentException, IOException {
        long j2 = this.splitSize;
        if (j > j2) {
            throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
        } else if (j2 - this.currentSplitSegmentBytesWritten < j) {
            openNewSplitSegment();
        }
    }

    public void write(int i) throws IOException {
        byte[] bArr = this.singleByte;
        bArr[0] = (byte) (i & 255);
        write(bArr);
    }

    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i2 > 0) {
            long j = this.currentSplitSegmentBytesWritten;
            long j2 = this.splitSize;
            if (j >= j2) {
                openNewSplitSegment();
                write(bArr, i, i2);
                return;
            }
            long j3 = (long) i2;
            if (j + j3 > j2) {
                int i3 = ((int) j2) - ((int) j);
                write(bArr, i, i3);
                openNewSplitSegment();
                write(bArr, i + i3, i2 - i3);
                return;
            }
            this.outputStream.write(bArr, i, i2);
            this.currentSplitSegmentBytesWritten += j3;
        }
    }

    public void close() throws IOException {
        if (!this.finished) {
            finish();
        }
    }

    private void finish() throws IOException {
        if (!this.finished) {
            String baseName = FileNameUtils.getBaseName(this.zipFile.getName());
            File parentFile = this.zipFile.getParentFile();
            File file = new File(parentFile, baseName + ".zip");
            this.outputStream.close();
            if (this.zipFile.renameTo(file)) {
                this.finished = true;
                return;
            }
            throw new IOException("Failed to rename " + this.zipFile + " to " + file);
        }
        throw new IOException("This archive has already been finished");
    }

    private void openNewSplitSegment() throws IOException {
        if (this.currentSplitSegmentIndex == 0) {
            this.outputStream.close();
            File createNewSplitSegmentFile = createNewSplitSegmentFile(1);
            if (!this.zipFile.renameTo(createNewSplitSegmentFile)) {
                throw new IOException("Failed to rename " + this.zipFile + " to " + createNewSplitSegmentFile);
            }
        }
        File createNewSplitSegmentFile2 = createNewSplitSegmentFile((Integer) null);
        this.outputStream.close();
        this.outputStream = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(createNewSplitSegmentFile2.toPath(), new OpenOption[0]);
        this.currentSplitSegmentBytesWritten = 0;
        this.zipFile = createNewSplitSegmentFile2;
        this.currentSplitSegmentIndex++;
    }

    private void writeZipSplitSignature() throws IOException {
        this.outputStream.write(ZipArchiveOutputStream.DD_SIG);
        this.currentSplitSegmentBytesWritten += (long) ZipArchiveOutputStream.DD_SIG.length;
    }

    private File createNewSplitSegmentFile(Integer num) throws IOException {
        String str;
        int intValue = num == null ? this.currentSplitSegmentIndex + 2 : num.intValue();
        String baseName = FileNameUtils.getBaseName(this.zipFile.getName());
        if (intValue <= 9) {
            str = ".z0" + intValue;
        } else {
            str = ".z" + intValue;
        }
        File file = new File(this.zipFile.getParent(), baseName + str);
        if (!file.exists()) {
            return file;
        }
        throw new IOException("split zip segment " + baseName + str + " already exists");
    }

    public int getCurrentSplitSegmentIndex() {
        return this.currentSplitSegmentIndex;
    }

    public long getCurrentSplitSegmentBytesWritten() {
        return this.currentSplitSegmentBytesWritten;
    }
}
