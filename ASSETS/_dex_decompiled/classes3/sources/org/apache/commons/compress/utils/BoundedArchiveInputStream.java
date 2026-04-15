package org.apache.commons.compress.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public abstract class BoundedArchiveInputStream extends InputStream {
    private final long end;
    private long loc;
    private ByteBuffer singleByteBuffer;

    /* access modifiers changed from: protected */
    public abstract int read(long j, ByteBuffer byteBuffer) throws IOException;

    public BoundedArchiveInputStream(long j, long j2) {
        long j3 = j + j2;
        this.end = j3;
        if (j3 >= j) {
            this.loc = j;
            return;
        }
        throw new IllegalArgumentException("Invalid length of stream at offset=" + j + ", length=" + j2);
    }

    public synchronized int read() throws IOException {
        if (this.loc >= this.end) {
            return -1;
        }
        ByteBuffer byteBuffer = this.singleByteBuffer;
        if (byteBuffer == null) {
            this.singleByteBuffer = ByteBuffer.allocate(1);
        } else {
            byteBuffer.rewind();
        }
        if (read(this.loc, this.singleByteBuffer) < 1) {
            return -1;
        }
        this.loc++;
        return this.singleByteBuffer.get() & 255;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003b, code lost:
        return r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.loc     // Catch:{ all -> 0x0044 }
            long r2 = r6.end     // Catch:{ all -> 0x0044 }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x000c
            monitor-exit(r6)
            r7 = -1
            return r7
        L_0x000c:
            long r4 = (long) r9
            long r2 = r2 - r0
            long r0 = java.lang.Math.min(r4, r2)     // Catch:{ all -> 0x0044 }
            r2 = 0
            int r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r9 > 0) goto L_0x001b
            monitor-exit(r6)
            r7 = 0
            return r7
        L_0x001b:
            if (r8 < 0) goto L_0x003c
            int r9 = r7.length     // Catch:{ all -> 0x0044 }
            if (r8 > r9) goto L_0x003c
            int r9 = r7.length     // Catch:{ all -> 0x0044 }
            int r9 = r9 - r8
            long r2 = (long) r9     // Catch:{ all -> 0x0044 }
            int r9 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r9 > 0) goto L_0x003c
            int r9 = (int) r0     // Catch:{ all -> 0x0044 }
            java.nio.ByteBuffer r7 = java.nio.ByteBuffer.wrap(r7, r8, r9)     // Catch:{ all -> 0x0044 }
            long r8 = r6.loc     // Catch:{ all -> 0x0044 }
            int r7 = r6.read(r8, r7)     // Catch:{ all -> 0x0044 }
            if (r7 <= 0) goto L_0x003a
            long r8 = r6.loc     // Catch:{ all -> 0x0044 }
            long r0 = (long) r7     // Catch:{ all -> 0x0044 }
            long r8 = r8 + r0
            r6.loc = r8     // Catch:{ all -> 0x0044 }
        L_0x003a:
            monitor-exit(r6)
            return r7
        L_0x003c:
            java.lang.IndexOutOfBoundsException r7 = new java.lang.IndexOutOfBoundsException     // Catch:{ all -> 0x0044 }
            java.lang.String r8 = "offset or len are out of bounds"
            r7.<init>(r8)     // Catch:{ all -> 0x0044 }
            throw r7     // Catch:{ all -> 0x0044 }
        L_0x0044:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0044 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.utils.BoundedArchiveInputStream.read(byte[], int, int):int");
    }
}
