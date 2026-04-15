package cn.sherlock.com.sun.media.sound;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class ModelByteBuffer {
    private byte[] buffer;
    /* access modifiers changed from: private */
    public File file;
    /* access modifiers changed from: private */
    public long fileoffset;
    private final long len;
    private long offset;
    /* access modifiers changed from: private */
    public ModelByteBuffer root = this;

    private class RandomFileInputStream extends InputStream {
        private long left;
        private long mark = 0;
        private long markleft = 0;
        private RandomAccessFile raf;

        public boolean markSupported() {
            return true;
        }

        public RandomFileInputStream() throws IOException {
            RandomAccessFile randomAccessFile = new RandomAccessFile(ModelByteBuffer.this.root.file, "r");
            this.raf = randomAccessFile;
            randomAccessFile.seek(ModelByteBuffer.this.root.fileoffset + ModelByteBuffer.this.arrayOffset());
            this.left = ModelByteBuffer.this.capacity();
        }

        public int available() throws IOException {
            long j = this.left;
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            return (int) j;
        }

        /*  JADX ERROR: JadxRuntimeException in pass: BlockFinish
            jadx.core.utils.exceptions.JadxRuntimeException: Dominance frontier not set for block: B:3:0x000e
            	at jadx.core.dex.nodes.BlockNode.lock(BlockNode.java:75)
            	at jadx.core.utils.ImmutableList.forEach(ImmutableList.java:108)
            	at jadx.core.dex.nodes.MethodNode.finishBasicBlocks(MethodNode.java:472)
            	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:27)
            */
        public synchronized void mark(int r3) {
            /*
                r2 = this;
                monitor-enter(r2)
                java.io.RandomAccessFile r3 = r2.raf     // Catch:{ IOException -> 0x0011 }
                long r0 = r3.getFilePointer()     // Catch:{ IOException -> 0x0011 }
                r2.mark = r0     // Catch:{ IOException -> 0x0011 }
                long r0 = r2.left     // Catch:{ IOException -> 0x0011 }
                r2.markleft = r0     // Catch:{ IOException -> 0x0011 }
                goto L_0x0011
            L_0x000e:
                r3 = move-exception
                monitor-exit(r2)     // Catch:{ all -> 0x000e }
                throw r3
            L_0x0011:
                monitor-exit(r2)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.ModelByteBuffer.RandomFileInputStream.mark(int):void");
        }

        public synchronized void reset() throws IOException {
            this.raf.seek(this.mark);
            this.left = this.markleft;
        }

        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            long j2 = this.left;
            if (j > j2) {
                j = j2;
            }
            this.raf.seek(this.raf.getFilePointer() + j);
            this.left -= j;
            return j;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read;
            long j = this.left;
            if (((long) i2) > j) {
                i2 = (int) j;
            }
            if (j == 0 || (read = this.raf.read(bArr, i, i2)) == -1) {
                return -1;
            }
            this.left -= (long) read;
            return read;
        }

        public int read(byte[] bArr) throws IOException {
            int read;
            int length = bArr.length;
            long j = this.left;
            if (((long) length) > j) {
                length = (int) j;
            }
            if (j == 0 || (read = this.raf.read(bArr, 0, length)) == -1) {
                return -1;
            }
            this.left -= (long) read;
            return read;
        }

        public int read() throws IOException {
            int read;
            if (this.left == 0 || (read = this.raf.read()) == -1) {
                return -1;
            }
            this.left--;
            return read;
        }

        public void close() throws IOException {
            this.raf.close();
        }
    }

    private ModelByteBuffer(ModelByteBuffer modelByteBuffer, long j, long j2, boolean z) {
        ModelByteBuffer modelByteBuffer2 = modelByteBuffer.root;
        this.root = modelByteBuffer2;
        this.offset = 0;
        long j3 = modelByteBuffer.len;
        j = j < 0 ? 0 : j;
        j = j > j3 ? j3 : j;
        j2 = j2 < 0 ? 0 : j2;
        j3 = j2 <= j3 ? j2 : j3;
        j = j > j3 ? j3 : j;
        this.offset = j;
        this.len = j3 - j;
        if (z) {
            this.buffer = modelByteBuffer2.buffer;
            File file2 = modelByteBuffer2.file;
            if (file2 != null) {
                this.file = file2;
                this.fileoffset = modelByteBuffer2.fileoffset + arrayOffset();
                this.offset = 0;
            } else {
                this.offset = arrayOffset();
            }
            this.root = this;
        }
    }

    public ModelByteBuffer(byte[] bArr) {
        this.buffer = bArr;
        this.offset = 0;
        this.len = (long) bArr.length;
    }

    public ModelByteBuffer(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.offset = (long) i;
        this.len = (long) i2;
    }

    public ModelByteBuffer(File file2) {
        this.file = file2;
        this.fileoffset = 0;
        this.len = file2.length();
    }

    public ModelByteBuffer(File file2, long j, long j2) {
        this.file = file2;
        this.fileoffset = j;
        this.len = j2;
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        ModelByteBuffer modelByteBuffer = this.root;
        if (modelByteBuffer.file == null || modelByteBuffer.buffer != null) {
            outputStream.write(array(), (int) arrayOffset(), (int) capacity());
            return;
        }
        InputStream inputStream = getInputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public InputStream getInputStream() {
        ModelByteBuffer modelByteBuffer = this.root;
        if (modelByteBuffer.file == null || modelByteBuffer.buffer != null) {
            return new ByteArrayInputStream(array(), (int) arrayOffset(), (int) capacity());
        }
        try {
            return new RandomFileInputStream();
        } catch (IOException unused) {
            return null;
        }
    }

    public ModelByteBuffer subbuffer(long j) {
        return subbuffer(j, capacity());
    }

    public ModelByteBuffer subbuffer(long j, long j2) {
        return subbuffer(j, j2, false);
    }

    public ModelByteBuffer subbuffer(long j, long j2, boolean z) {
        return new ModelByteBuffer(this, j, j2, z);
    }

    public byte[] array() {
        return this.root.buffer;
    }

    public long arrayOffset() {
        ModelByteBuffer modelByteBuffer = this.root;
        if (modelByteBuffer != this) {
            return modelByteBuffer.arrayOffset() + this.offset;
        }
        return this.offset;
    }

    public long capacity() {
        return this.len;
    }

    public ModelByteBuffer getRoot() {
        return this.root;
    }

    public File getFile() {
        return this.file;
    }

    public long getFilePointer() {
        return this.fileoffset;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void loadAll(java.util.Collection<cn.sherlock.com.sun.media.sound.ModelByteBuffer> r9) throws java.io.IOException {
        /*
            r0 = 0
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0068 }
            r1 = r0
            r2 = r1
        L_0x0007:
            boolean r3 = r9.hasNext()     // Catch:{ all -> 0x0065 }
            if (r3 == 0) goto L_0x005f
            java.lang.Object r3 = r9.next()     // Catch:{ all -> 0x0065 }
            cn.sherlock.com.sun.media.sound.ModelByteBuffer r3 = (cn.sherlock.com.sun.media.sound.ModelByteBuffer) r3     // Catch:{ all -> 0x0065 }
            cn.sherlock.com.sun.media.sound.ModelByteBuffer r3 = r3.root     // Catch:{ all -> 0x0065 }
            java.io.File r4 = r3.file     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x001a
            goto L_0x0007
        L_0x001a:
            byte[] r5 = r3.buffer     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x001f
            goto L_0x0007
        L_0x001f:
            if (r2 == 0) goto L_0x0027
            boolean r4 = r2.equals(r4)     // Catch:{ all -> 0x0065 }
            if (r4 != 0) goto L_0x0039
        L_0x0027:
            if (r1 == 0) goto L_0x002d
            r1.close()     // Catch:{ all -> 0x0065 }
            r1 = r0
        L_0x002d:
            java.io.File r2 = r3.file     // Catch:{ all -> 0x0065 }
            java.io.RandomAccessFile r4 = new java.io.RandomAccessFile     // Catch:{ all -> 0x0065 }
            java.io.File r5 = r3.file     // Catch:{ all -> 0x0065 }
            java.lang.String r6 = "r"
            r4.<init>(r5, r6)     // Catch:{ all -> 0x0065 }
            r1 = r4
        L_0x0039:
            long r4 = r3.fileoffset     // Catch:{ all -> 0x0065 }
            r1.seek(r4)     // Catch:{ all -> 0x0065 }
            long r4 = r3.capacity()     // Catch:{ all -> 0x0065 }
            int r4 = (int) r4     // Catch:{ all -> 0x0065 }
            byte[] r5 = new byte[r4]     // Catch:{ all -> 0x0065 }
            r6 = 0
        L_0x0046:
            if (r6 == r4) goto L_0x0058
            int r7 = r4 - r6
            r8 = 65536(0x10000, float:9.18355E-41)
            if (r7 <= r8) goto L_0x0053
            r1.readFully(r5, r6, r8)     // Catch:{ all -> 0x0065 }
            int r6 = r6 + r8
            goto L_0x0046
        L_0x0053:
            r1.readFully(r5, r6, r7)     // Catch:{ all -> 0x0065 }
            r6 = r4
            goto L_0x0046
        L_0x0058:
            r3.buffer = r5     // Catch:{ all -> 0x0065 }
            r4 = 0
            r3.offset = r4     // Catch:{ all -> 0x0065 }
            goto L_0x0007
        L_0x005f:
            if (r1 == 0) goto L_0x0064
            r1.close()
        L_0x0064:
            return
        L_0x0065:
            r9 = move-exception
            r0 = r1
            goto L_0x0069
        L_0x0068:
            r9 = move-exception
        L_0x0069:
            if (r0 == 0) goto L_0x006e
            r0.close()
        L_0x006e:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.ModelByteBuffer.loadAll(java.util.Collection):void");
    }

    public void load() throws IOException {
        ModelByteBuffer modelByteBuffer = this.root;
        if (modelByteBuffer != this) {
            modelByteBuffer.load();
        } else if (this.buffer == null) {
            if (this.file != null) {
                DataInputStream dataInputStream = new DataInputStream(getInputStream());
                byte[] bArr = new byte[((int) capacity())];
                this.buffer = bArr;
                this.offset = 0;
                dataInputStream.readFully(bArr);
                dataInputStream.close();
                return;
            }
            throw new IllegalStateException("No file associated with this ByteBuffer!");
        }
    }

    public void unload() {
        ModelByteBuffer modelByteBuffer = this.root;
        if (modelByteBuffer != this) {
            modelByteBuffer.unload();
        } else if (this.file != null) {
            modelByteBuffer.buffer = null;
        } else {
            throw new IllegalStateException("No file associated with this ByteBuffer!");
        }
    }
}
