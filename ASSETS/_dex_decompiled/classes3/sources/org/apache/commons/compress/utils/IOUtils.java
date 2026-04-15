package org.apache.commons.compress.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;

public final class IOUtils {
    private static final int COPY_BUF_SIZE = 8024;
    public static final LinkOption[] EMPTY_LINK_OPTIONS = new LinkOption[0];
    private static final byte[] SKIP_BUF = new byte[4096];
    private static final int SKIP_BUF_SIZE = 4096;

    private IOUtils() {
    }

    public static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, COPY_BUF_SIZE);
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        if (i >= 1) {
            byte[] bArr = new byte[i];
            long j = 0;
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    return j;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
            }
        } else {
            throw new IllegalArgumentException("buffersize must be bigger than 0");
        }
    }

    public static long skip(InputStream inputStream, long j) throws IOException {
        int readFully;
        long j2 = j;
        while (j2 > 0) {
            long skip = inputStream.skip(j2);
            if (skip == 0) {
                break;
            }
            j2 -= skip;
        }
        while (j2 > 0 && (readFully = readFully(inputStream, SKIP_BUF, 0, (int) Math.min(j2, 4096))) >= 1) {
            j2 -= (long) readFully;
        }
        return j - j2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        if (r2 != null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001f, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0020, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0023, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int read(java.io.File r2, byte[] r3) throws java.io.IOException {
        /*
            java.nio.file.Path r2 = r2.toPath()
            r0 = 0
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r0]
            java.io.InputStream r2 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r2, (java.nio.file.OpenOption[]) r1)
            int r1 = r3.length     // Catch:{ all -> 0x0016 }
            int r3 = readFully(r2, r3, r0, r1)     // Catch:{ all -> 0x0016 }
            if (r2 == 0) goto L_0x0015
            r2.close()
        L_0x0015:
            return r3
        L_0x0016:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0018 }
        L_0x0018:
            r0 = move-exception
            if (r2 == 0) goto L_0x0023
            r2.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0023:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.utils.IOUtils.read(java.io.File, byte[]):int");
    }

    public static int readFully(InputStream inputStream, byte[] bArr) throws IOException {
        return readFully(inputStream, bArr, 0, bArr.length);
    }

    public static int readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (i2 < 0 || i < 0 || (i3 = i2 + i) > bArr.length || i3 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i4 = 0;
        while (i4 != i2) {
            int read = inputStream.read(bArr, i + i4, i2 - i4);
            if (read == -1) {
                break;
            }
            i4 += read;
        }
        return i4;
    }

    public static void readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        int i = 0;
        while (i < remaining) {
            int read = readableByteChannel.read(byteBuffer);
            if (read <= 0) {
                break;
            }
            i += read;
        }
        if (i < remaining) {
            throw new EOFException();
        }
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, (OutputStream) byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void copy(File file, OutputStream outputStream) throws IOException {
        long unused = Files.copy(file.toPath(), outputStream);
    }

    public static long copyRange(InputStream inputStream, long j, OutputStream outputStream) throws IOException {
        return copyRange(inputStream, j, outputStream, COPY_BUF_SIZE);
    }

    public static long copyRange(InputStream inputStream, long j, OutputStream outputStream, int i) throws IOException {
        int read;
        if (i >= 1) {
            int min = (int) Math.min((long) i, j);
            byte[] bArr = new byte[min];
            long j2 = 0;
            while (j2 < j && -1 != (read = inputStream.read(bArr, 0, (int) Math.min(j - j2, (long) min)))) {
                outputStream.write(bArr, 0, read);
                j2 += (long) read;
            }
            return j2;
        }
        throw new IllegalArgumentException("buffersize must be bigger than 0");
    }

    public static byte[] readRange(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copyRange(inputStream, (long) i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] readRange(ReadableByteChannel readableByteChannel, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteBuffer allocate = ByteBuffer.allocate(Math.min(i, COPY_BUF_SIZE));
        int i2 = 0;
        while (i2 < i) {
            int read = readableByteChannel.read(allocate);
            if (read <= 0) {
                break;
            }
            byteArrayOutputStream.write(allocate.array(), 0, read);
            allocate.rewind();
            i2 += read;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
