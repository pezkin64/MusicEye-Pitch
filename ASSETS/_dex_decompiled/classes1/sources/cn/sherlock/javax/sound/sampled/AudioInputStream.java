package cn.sherlock.javax.sound.sampled;

import java.io.IOException;
import java.io.InputStream;

public class AudioInputStream extends InputStream {
    protected AudioFormat format;
    protected long frameLength;
    protected long framePos;
    protected int frameSize;
    private byte[] markPushBackBuffer = null;
    private int markPushBackLen = 0;
    private long markpos;
    private byte[] pushBackBuffer = null;
    private int pushBackLen = 0;
    private InputStream stream;

    public AudioInputStream(InputStream inputStream, AudioFormat audioFormat, long j) {
        this.format = audioFormat;
        this.frameLength = j;
        int frameSize2 = audioFormat.getFrameSize();
        this.frameSize = frameSize2;
        if (frameSize2 == -1 || frameSize2 <= 0) {
            this.frameSize = 1;
        }
        this.stream = inputStream;
        this.framePos = 0;
        this.markpos = 0;
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public long getFrameLength() {
        return this.frameLength;
    }

    public int read() throws IOException {
        if (this.frameSize == 1) {
            byte[] bArr = new byte[1];
            if (read(bArr) <= 0) {
                return -1;
            }
            return bArr[0] & 255;
        }
        throw new IOException("cannot read a single byte if frame size > 1");
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        int i4;
        int i5 = this.frameSize;
        if (i2 % i5 != 0 && (i2 = i2 - (i2 % i5)) == 0) {
            return 0;
        }
        long j = this.frameLength;
        if (j != -1) {
            long j2 = this.framePos;
            if (j2 >= j) {
                return -1;
            }
            if (((long) (i2 / i5)) > j - j2) {
                i2 = ((int) (j - j2)) * i5;
            }
        }
        int i6 = this.pushBackLen;
        if (i6 <= 0 || i2 < i6) {
            i3 = i;
            i4 = 0;
        } else {
            System.arraycopy(this.pushBackBuffer, 0, bArr, i, i6);
            i4 = this.pushBackLen;
            i3 = i + i4;
            i2 -= i4;
            this.pushBackLen = 0;
        }
        int read = this.stream.read(bArr, i3, i2);
        if (read == -1) {
            return -1;
        }
        if (read > 0) {
            i4 += read;
        }
        if (i4 > 0) {
            int i7 = this.frameSize;
            int i8 = i4 % i7;
            this.pushBackLen = i8;
            if (i8 > 0) {
                if (this.pushBackBuffer == null) {
                    this.pushBackBuffer = new byte[i7];
                }
                System.arraycopy(bArr, (i + i4) - i8, this.pushBackBuffer, 0, i8);
                i4 -= this.pushBackLen;
            }
            this.framePos += (long) (i4 / this.frameSize);
        }
        return i4;
    }

    public long skip(long j) throws IOException {
        int i = this.frameSize;
        if (j % ((long) i) != 0) {
            j -= j % ((long) i);
        }
        long j2 = this.frameLength;
        if (j2 != -1) {
            long j3 = this.framePos;
            if (j / ((long) i) > j2 - j3) {
                j = ((long) i) * (j2 - j3);
            }
        }
        long skip = this.stream.skip(j);
        int i2 = this.frameSize;
        if (skip % ((long) i2) == 0) {
            if (skip >= 0) {
                this.framePos += skip / ((long) i2);
            }
            return skip;
        }
        throw new IOException("Could not skip an integer number of frames.");
    }

    public int available() throws IOException {
        int available = this.stream.available();
        long j = this.frameLength;
        if (j == -1) {
            return available;
        }
        int i = this.frameSize;
        long j2 = this.framePos;
        return ((long) (available / i)) > j - j2 ? ((int) (j - j2)) * i : available;
    }

    public void close() throws IOException {
        this.stream.close();
    }

    public void mark(int i) {
        this.stream.mark(i);
        if (markSupported()) {
            this.markpos = this.framePos;
            int i2 = this.pushBackLen;
            this.markPushBackLen = i2;
            if (i2 > 0) {
                if (this.markPushBackBuffer == null) {
                    this.markPushBackBuffer = new byte[this.frameSize];
                }
                System.arraycopy(this.pushBackBuffer, 0, this.markPushBackBuffer, 0, i2);
            }
        }
    }

    public void reset() throws IOException {
        this.stream.reset();
        this.framePos = this.markpos;
        int i = this.markPushBackLen;
        this.pushBackLen = i;
        if (i > 0) {
            if (this.pushBackBuffer == null) {
                this.pushBackBuffer = new byte[(this.frameSize - 1)];
            }
            System.arraycopy(this.markPushBackBuffer, 0, this.pushBackBuffer, 0, i);
        }
    }

    public boolean markSupported() {
        return this.stream.markSupported();
    }
}
