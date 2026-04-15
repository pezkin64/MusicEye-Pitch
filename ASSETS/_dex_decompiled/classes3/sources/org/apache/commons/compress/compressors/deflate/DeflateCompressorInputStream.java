package org.apache.commons.compress.compressors.deflate;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class DeflateCompressorInputStream extends CompressorInputStream implements InputStreamStatistics {
    private static final int MAGIC_1 = 120;
    private static final int MAGIC_2a = 1;
    private static final int MAGIC_2b = 94;
    private static final int MAGIC_2c = 156;
    private static final int MAGIC_2d = 218;
    private final CountingInputStream countingStream;
    private final InputStream in;
    private final Inflater inflater;

    public DeflateCompressorInputStream(InputStream inputStream) {
        this(inputStream, new DeflateParameters());
    }

    public DeflateCompressorInputStream(InputStream inputStream, DeflateParameters deflateParameters) {
        Inflater inflater2 = new Inflater(!deflateParameters.withZlibHeader());
        this.inflater = inflater2;
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        this.countingStream = countingInputStream;
        this.in = new InflaterInputStream(countingInputStream, inflater2);
    }

    public int read() throws IOException {
        int read = this.in.read();
        count(read == -1 ? 0 : 1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int read = this.in.read(bArr, i, i2);
        count(read);
        return read;
    }

    public long skip(long j) throws IOException {
        return IOUtils.skip(this.in, j);
    }

    public int available() throws IOException {
        return this.in.available();
    }

    public void close() throws IOException {
        try {
            this.in.close();
        } finally {
            this.inflater.end();
        }
    }

    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        r2 = r2[1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean matches(byte[] r2, int r3) {
        /*
            r0 = 3
            r1 = 0
            if (r3 <= r0) goto L_0x001c
            byte r3 = r2[r1]
            r0 = 120(0x78, float:1.68E-43)
            if (r3 != r0) goto L_0x001c
            r3 = 1
            byte r2 = r2[r3]
            if (r2 == r3) goto L_0x001b
            r0 = 94
            if (r2 == r0) goto L_0x001b
            r0 = -100
            if (r2 == r0) goto L_0x001b
            r0 = -38
            if (r2 != r0) goto L_0x001c
        L_0x001b:
            return r3
        L_0x001c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream.matches(byte[], int):boolean");
    }
}
