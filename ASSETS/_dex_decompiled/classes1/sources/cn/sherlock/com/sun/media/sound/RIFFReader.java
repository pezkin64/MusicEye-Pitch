package cn.sherlock.com.sun.media.sound;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class RIFFReader extends InputStream {
    private long avail;
    private long ckSize = 0;
    private long filepointer = 0;
    private String fourcc;
    private RIFFReader lastiterator = null;
    private String riff_type = null;
    private RIFFReader root;
    private InputStream stream;

    public RIFFReader(InputStream inputStream) throws IOException {
        int read;
        if (inputStream instanceof RIFFReader) {
            this.root = ((RIFFReader) inputStream).root;
        } else {
            this.root = this;
        }
        this.stream = inputStream;
        this.avail = 2147483647L;
        this.ckSize = 2147483647L;
        do {
            read = read();
            if (read == -1) {
                this.fourcc = "";
                this.riff_type = null;
                this.avail = 0;
                return;
            }
        } while (read == 0);
        byte[] bArr = new byte[4];
        bArr[0] = (byte) read;
        readFully(bArr, 1, 3);
        this.fourcc = new String(bArr, "ascii");
        long readUnsignedInt = readUnsignedInt();
        this.ckSize = readUnsignedInt;
        this.avail = readUnsignedInt;
        if (getFormat().equals("RIFF") || getFormat().equals("LIST")) {
            byte[] bArr2 = new byte[4];
            readFully(bArr2);
            this.riff_type = new String(bArr2, "ascii");
        }
    }

    public long getFilePointer() throws IOException {
        return this.root.filepointer;
    }

    public boolean hasNextChunk() throws IOException {
        RIFFReader rIFFReader = this.lastiterator;
        if (rIFFReader != null) {
            rIFFReader.finish();
        }
        return this.avail != 0;
    }

    public RIFFReader nextChunk() throws IOException {
        RIFFReader rIFFReader = this.lastiterator;
        if (rIFFReader != null) {
            rIFFReader.finish();
        }
        if (this.avail == 0) {
            return null;
        }
        RIFFReader rIFFReader2 = new RIFFReader(this);
        this.lastiterator = rIFFReader2;
        return rIFFReader2;
    }

    public String getFormat() {
        return this.fourcc;
    }

    public String getType() {
        return this.riff_type;
    }

    public long getSize() {
        return this.ckSize;
    }

    public int read() throws IOException {
        int read;
        if (this.avail == 0 || (read = this.stream.read()) == -1) {
            return -1;
        }
        this.avail--;
        this.filepointer++;
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = this.avail;
        if (j == 0) {
            return -1;
        }
        if (((long) i2) > j) {
            int read = this.stream.read(bArr, i, (int) j);
            if (read != -1) {
                this.filepointer += (long) read;
            }
            this.avail = 0;
            return read;
        }
        int read2 = this.stream.read(bArr, i, i2);
        if (read2 == -1) {
            return -1;
        }
        long j2 = (long) read2;
        this.avail -= j2;
        this.filepointer += j2;
        return read2;
    }

    public final void readFully(byte[] bArr) throws IOException {
        readFully(bArr, 0, bArr.length);
    }

    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            while (i2 > 0) {
                int read = read(bArr, i, i2);
                if (read >= 0) {
                    if (read == 0) {
                        Thread.yield();
                    }
                    i += read;
                    i2 -= read;
                } else {
                    throw new EOFException();
                }
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    public final long skipBytes(long j) throws IOException {
        int i;
        if (j < 0) {
            return 0;
        }
        long j2 = 0;
        while (j2 != j && r4 >= 0) {
            if (i == 0) {
                Thread.yield();
            }
            j2 += (r4 = skip(j - j2));
        }
        return j2;
    }

    public long skip(long j) throws IOException {
        long j2 = this.avail;
        if (j2 == 0) {
            return -1;
        }
        if (j > j2) {
            long skip = this.stream.skip(j2);
            if (skip != -1) {
                this.filepointer += skip;
            }
            this.avail = 0;
            return skip;
        }
        long skip2 = this.stream.skip(j);
        if (skip2 == -1) {
            return -1;
        }
        this.avail -= skip2;
        this.filepointer += skip2;
        return skip2;
    }

    public int available() {
        return (int) this.avail;
    }

    public void finish() throws IOException {
        long j = this.avail;
        if (j != 0) {
            skipBytes(j);
        }
    }

    public String readString(int i) throws IOException {
        byte[] bArr = new byte[i];
        readFully(bArr);
        for (int i2 = 0; i2 < i; i2++) {
            if (bArr[i2] == 0) {
                return new String(bArr, 0, i2, "ascii");
            }
        }
        return new String(bArr, "ascii");
    }

    public byte readByte() throws IOException {
        int read = read();
        if (read >= 0) {
            return (byte) read;
        }
        throw new EOFException();
    }

    public short readShort() throws IOException {
        int read = read();
        int read2 = read();
        if (read < 0) {
            throw new EOFException();
        } else if (read2 >= 0) {
            return (short) (read | (read2 << 8));
        } else {
            throw new EOFException();
        }
    }

    public int readInt() throws IOException {
        int read = read();
        int read2 = read();
        int read3 = read();
        int read4 = read();
        if (read < 0) {
            throw new EOFException();
        } else if (read2 < 0) {
            throw new EOFException();
        } else if (read3 < 0) {
            throw new EOFException();
        } else if (read4 >= 0) {
            return (read + (read2 << 8)) | (read3 << 16) | (read4 << 24);
        } else {
            throw new EOFException();
        }
    }

    public long readLong() throws IOException {
        long read = (long) read();
        long read2 = (long) read();
        long read3 = (long) read();
        long read4 = (long) read();
        long read5 = (long) read();
        long read6 = (long) read();
        long read7 = (long) read();
        long read8 = (long) read();
        if (read < 0) {
            throw new EOFException();
        } else if (read2 < 0) {
            throw new EOFException();
        } else if (read3 < 0) {
            throw new EOFException();
        } else if (read4 < 0) {
            throw new EOFException();
        } else if (read5 < 0) {
            throw new EOFException();
        } else if (read6 < 0) {
            throw new EOFException();
        } else if (read7 < 0) {
            throw new EOFException();
        } else if (read8 >= 0) {
            return read | (read2 << 8) | (read3 << 16) | (read4 << 24) | (read5 << 32) | (read6 << 40) | (read7 << 48) | (read8 << 56);
        } else {
            throw new EOFException();
        }
    }

    public int readUnsignedByte() throws IOException {
        int read = read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    public int readUnsignedShort() throws IOException {
        int read = read();
        int read2 = read();
        if (read < 0) {
            throw new EOFException();
        } else if (read2 >= 0) {
            return read | (read2 << 8);
        } else {
            throw new EOFException();
        }
    }

    public long readUnsignedInt() throws IOException {
        long read = (long) read();
        long read2 = (long) read();
        long read3 = (long) read();
        long read4 = (long) read();
        if (read < 0) {
            throw new EOFException();
        } else if (read2 < 0) {
            throw new EOFException();
        } else if (read3 < 0) {
            throw new EOFException();
        } else if (read4 >= 0) {
            return (read + (read2 << 8)) | (read3 << 16) | (read4 << 24);
        } else {
            throw new EOFException();
        }
    }

    public void close() throws IOException {
        finish();
        if (this == this.root) {
            this.stream.close();
        }
        this.stream = null;
    }
}
