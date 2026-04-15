package cn.sherlock.com.sun.media.sound;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

public class RIFFWriter extends OutputStream {
    private RIFFWriter childchunk;
    private long chunksizepointer;
    private int chunktype;
    private boolean open;
    private RandomAccessWriter raf;
    private long startpointer;
    private boolean writeoverride;

    private interface RandomAccessWriter {
        void close() throws IOException;

        long getPointer() throws IOException;

        long length() throws IOException;

        void seek(long j) throws IOException;

        void setLength(long j) throws IOException;

        void write(int i) throws IOException;

        void write(byte[] bArr) throws IOException;

        void write(byte[] bArr, int i, int i2) throws IOException;
    }

    private static class RandomAccessFileWriter implements RandomAccessWriter {
        RandomAccessFile raf;

        public RandomAccessFileWriter(File file) throws FileNotFoundException {
            this.raf = new RandomAccessFile(file, "rw");
        }

        public RandomAccessFileWriter(String str) throws FileNotFoundException {
            this.raf = new RandomAccessFile(str, "rw");
        }

        public void seek(long j) throws IOException {
            this.raf.seek(j);
        }

        public long getPointer() throws IOException {
            return this.raf.getFilePointer();
        }

        public void close() throws IOException {
            this.raf.close();
        }

        public void write(int i) throws IOException {
            this.raf.write(i);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.raf.write(bArr, i, i2);
        }

        public void write(byte[] bArr) throws IOException {
            this.raf.write(bArr);
        }

        public long length() throws IOException {
            return this.raf.length();
        }

        public void setLength(long j) throws IOException {
            this.raf.setLength(j);
        }
    }

    private static class RandomAccessByteWriter implements RandomAccessWriter {
        byte[] buff = new byte[32];
        int length = 0;
        int pos = 0;
        byte[] s;
        OutputStream stream;

        public RandomAccessByteWriter(OutputStream outputStream) {
            this.stream = outputStream;
        }

        public void seek(long j) throws IOException {
            this.pos = (int) j;
        }

        public long getPointer() throws IOException {
            return (long) this.pos;
        }

        public void close() throws IOException {
            this.stream.write(this.buff, 0, this.length);
            this.stream.close();
        }

        public void write(int i) throws IOException {
            if (this.s == null) {
                this.s = new byte[1];
            }
            byte[] bArr = this.s;
            bArr[0] = (byte) i;
            write(bArr, 0, 1);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            int i3 = this.pos + i2;
            if (i3 > this.length) {
                setLength((long) i3);
            }
            int i4 = i2 + i;
            while (i < i4) {
                byte[] bArr2 = this.buff;
                int i5 = this.pos;
                this.pos = i5 + 1;
                bArr2[i5] = bArr[i];
                i++;
            }
        }

        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        public long length() throws IOException {
            return (long) this.length;
        }

        public void setLength(long j) throws IOException {
            int i = (int) j;
            this.length = i;
            byte[] bArr = this.buff;
            if (i > bArr.length) {
                byte[] bArr2 = new byte[Math.max(bArr.length << 1, i)];
                byte[] bArr3 = this.buff;
                System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
                this.buff = bArr2;
            }
        }
    }

    public RIFFWriter(String str, String str2) throws IOException {
        this(new RandomAccessFileWriter(str), str2, 0);
    }

    public RIFFWriter(File file, String str) throws IOException {
        this(new RandomAccessFileWriter(file), str, 0);
    }

    public RIFFWriter(OutputStream outputStream, String str) throws IOException {
        this(new RandomAccessByteWriter(outputStream), str, 0);
    }

    private RIFFWriter(RandomAccessWriter randomAccessWriter, String str, int i) throws IOException {
        this.chunktype = 0;
        this.childchunk = null;
        this.open = true;
        this.writeoverride = false;
        if (i == 0 && randomAccessWriter.length() != 0) {
            randomAccessWriter.setLength(0);
        }
        this.raf = randomAccessWriter;
        if (randomAccessWriter.getPointer() % 2 != 0) {
            randomAccessWriter.write(0);
        }
        if (i == 0) {
            randomAccessWriter.write("RIFF".getBytes("ascii"));
        } else if (i == 1) {
            randomAccessWriter.write("LIST".getBytes("ascii"));
        } else {
            randomAccessWriter.write((str + "    ").substring(0, 4).getBytes("ascii"));
        }
        this.chunksizepointer = randomAccessWriter.getPointer();
        this.chunktype = 2;
        writeUnsignedInt(0);
        this.chunktype = i;
        this.startpointer = randomAccessWriter.getPointer();
        if (i != 2) {
            randomAccessWriter.write((str + "    ").substring(0, 4).getBytes("ascii"));
        }
    }

    public void seek(long j) throws IOException {
        this.raf.seek(j);
    }

    public long getFilePointer() throws IOException {
        return this.raf.getPointer();
    }

    public void setWriteOverride(boolean z) {
        this.writeoverride = z;
    }

    public boolean getWriteOverride() {
        return this.writeoverride;
    }

    public void close() throws IOException {
        if (this.open) {
            RIFFWriter rIFFWriter = this.childchunk;
            if (rIFFWriter != null) {
                rIFFWriter.close();
                this.childchunk = null;
            }
            int i = this.chunktype;
            long pointer = this.raf.getPointer();
            this.raf.seek(this.chunksizepointer);
            this.chunktype = 2;
            writeUnsignedInt(pointer - this.startpointer);
            if (i == 0) {
                this.raf.close();
            } else {
                this.raf.seek(pointer);
            }
            this.open = false;
            this.raf = null;
        }
    }

    public void write(int i) throws IOException {
        if (!this.writeoverride) {
            if (this.chunktype == 2) {
                RIFFWriter rIFFWriter = this.childchunk;
                if (rIFFWriter != null) {
                    rIFFWriter.close();
                    this.childchunk = null;
                }
            } else {
                throw new IllegalArgumentException("Only chunks can write bytes!");
            }
        }
        this.raf.write(i);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.writeoverride) {
            if (this.chunktype == 2) {
                RIFFWriter rIFFWriter = this.childchunk;
                if (rIFFWriter != null) {
                    rIFFWriter.close();
                    this.childchunk = null;
                }
            } else {
                throw new IllegalArgumentException("Only chunks can write bytes!");
            }
        }
        this.raf.write(bArr, i, i2);
    }

    public RIFFWriter writeList(String str) throws IOException {
        if (this.chunktype != 2) {
            RIFFWriter rIFFWriter = this.childchunk;
            if (rIFFWriter != null) {
                rIFFWriter.close();
                this.childchunk = null;
            }
            RIFFWriter rIFFWriter2 = new RIFFWriter(this.raf, str, 1);
            this.childchunk = rIFFWriter2;
            return rIFFWriter2;
        }
        throw new IllegalArgumentException("Only LIST and RIFF can write lists!");
    }

    public RIFFWriter writeChunk(String str) throws IOException {
        if (this.chunktype != 2) {
            RIFFWriter rIFFWriter = this.childchunk;
            if (rIFFWriter != null) {
                rIFFWriter.close();
                this.childchunk = null;
            }
            RIFFWriter rIFFWriter2 = new RIFFWriter(this.raf, str, 2);
            this.childchunk = rIFFWriter2;
            return rIFFWriter2;
        }
        throw new IllegalArgumentException("Only LIST and RIFF can write chunks!");
    }

    public void writeString(String str) throws IOException {
        write(str.getBytes());
    }

    public void writeString(String str, int i) throws IOException {
        byte[] bytes = str.getBytes();
        if (bytes.length > i) {
            write(bytes, 0, i);
            return;
        }
        write(bytes);
        for (int length = bytes.length; length < i; length++) {
            write(0);
        }
    }

    public void writeByte(int i) throws IOException {
        write(i);
    }

    public void writeShort(short s) throws IOException {
        write(s & 255);
        write((s >>> 8) & 255);
    }

    public void writeInt(int i) throws IOException {
        write(i & 255);
        write((i >>> 8) & 255);
        write((i >>> 16) & 255);
        write((i >>> 24) & 255);
    }

    public void writeLong(long j) throws IOException {
        write(((int) j) & 255);
        write(((int) (j >>> 8)) & 255);
        write(((int) (j >>> 16)) & 255);
        write(((int) (j >>> 24)) & 255);
        write(((int) (j >>> 32)) & 255);
        write(((int) (j >>> 40)) & 255);
        write(((int) (j >>> 48)) & 255);
        write(((int) (j >>> 56)) & 255);
    }

    public void writeUnsignedByte(int i) throws IOException {
        writeByte((byte) i);
    }

    public void writeUnsignedShort(int i) throws IOException {
        writeShort((short) i);
    }

    public void writeUnsignedInt(long j) throws IOException {
        writeInt((int) j);
    }
}
