package org.apache.commons.compress.compressors.zstandard;

import com.github.luben.zstd.ZstdOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;

public class ZstdCompressorOutputStream extends CompressorOutputStream {
    private final ZstdOutputStream encOS;

    public ZstdCompressorOutputStream(OutputStream outputStream, int i, boolean z, boolean z2) throws IOException {
        ZstdOutputStream zstdOutputStream = new ZstdOutputStream(outputStream, i);
        this.encOS = zstdOutputStream;
        zstdOutputStream.setCloseFrameOnFlush(z);
        zstdOutputStream.setChecksum(z2);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream, int i, boolean z) throws IOException {
        ZstdOutputStream zstdOutputStream = new ZstdOutputStream(outputStream, i);
        this.encOS = zstdOutputStream;
        zstdOutputStream.setCloseFrameOnFlush(z);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream, int i) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream, i);
    }

    public ZstdCompressorOutputStream(OutputStream outputStream) throws IOException {
        this.encOS = new ZstdOutputStream(outputStream);
    }

    public void close() throws IOException {
        this.encOS.close();
    }

    public void write(int i) throws IOException {
        this.encOS.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.encOS.write(bArr, i, i2);
    }

    public String toString() {
        return this.encOS.toString();
    }

    public void flush() throws IOException {
        this.encOS.flush();
    }
}
