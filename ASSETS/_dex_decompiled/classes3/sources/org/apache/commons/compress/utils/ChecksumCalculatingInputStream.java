package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.Checksum;

public class ChecksumCalculatingInputStream extends InputStream {
    private final Checksum checksum;
    private final InputStream in;

    public ChecksumCalculatingInputStream(Checksum checksum2, InputStream inputStream) {
        Objects.requireNonNull(checksum2, "checksum");
        Objects.requireNonNull(inputStream, "in");
        this.checksum = checksum2;
        this.in = inputStream;
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read >= 0) {
            this.checksum.update(read);
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
            this.checksum.update(bArr, i, read);
        }
        return read;
    }

    public long skip(long j) throws IOException {
        return read() >= 0 ? 1 : 0;
    }

    public long getValue() {
        return this.checksum.getValue();
    }
}
