package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.AudioSystem;
import cn.sherlock.javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AudioFloatInputStream {
    public abstract int available() throws IOException;

    public abstract void close() throws IOException;

    public abstract AudioFormat getFormat();

    public abstract long getFrameLength();

    public abstract void mark(int i);

    public abstract boolean markSupported();

    public abstract int read(float[] fArr, int i, int i2) throws IOException;

    public abstract void reset() throws IOException;

    public abstract long skip(long j) throws IOException;

    private static class BytaArrayAudioFloatInputStream extends AudioFloatInputStream {
        private byte[] buffer;
        private int buffer_len;
        private int buffer_offset;
        private AudioFloatConverter converter;
        private AudioFormat format;
        private int framesize_pc;
        private int markpos = 0;
        private int pos = 0;

        public void close() throws IOException {
        }

        public boolean markSupported() {
            return true;
        }

        public BytaArrayAudioFloatInputStream(AudioFloatConverter audioFloatConverter, byte[] bArr, int i, int i2) {
            this.converter = audioFloatConverter;
            AudioFormat format2 = audioFloatConverter.getFormat();
            this.format = format2;
            this.buffer = bArr;
            this.buffer_offset = i;
            int frameSize = format2.getFrameSize() / this.format.getChannels();
            this.framesize_pc = frameSize;
            this.buffer_len = i2 / frameSize;
        }

        public AudioFormat getFormat() {
            return this.format;
        }

        public long getFrameLength() {
            return (long) this.buffer_len;
        }

        public int read(float[] fArr, int i, int i2) throws IOException {
            fArr.getClass();
            if (i < 0 || i2 < 0 || i2 > fArr.length - i) {
                throw new IndexOutOfBoundsException();
            }
            int i3 = this.pos;
            int i4 = this.buffer_len;
            if (i3 >= i4) {
                return -1;
            }
            if (i2 == 0) {
                return 0;
            }
            if (i3 + i2 > i4) {
                i2 = i4 - i3;
            }
            int i5 = i2;
            this.converter.toFloatArray(this.buffer, this.buffer_offset + (i3 * this.framesize_pc), fArr, i, i5);
            this.pos += i5;
            return i5;
        }

        public long skip(long j) throws IOException {
            int i = this.pos;
            int i2 = this.buffer_len;
            if (i >= i2) {
                return -1;
            }
            if (j <= 0) {
                return 0;
            }
            if (((long) i) + j > ((long) i2)) {
                j = (long) (i2 - i);
            }
            this.pos = (int) (((long) i) + j);
            return j;
        }

        public int available() throws IOException {
            return this.buffer_len - this.pos;
        }

        public void mark(int i) {
            this.markpos = this.pos;
        }

        public void reset() throws IOException {
            this.pos = this.markpos;
        }
    }

    private static class DirectAudioFloatInputStream extends AudioFloatInputStream {
        private byte[] buffer;
        private AudioFloatConverter converter;
        private int framesize_pc;
        private AudioInputStream stream;

        public DirectAudioFloatInputStream(AudioInputStream audioInputStream) {
            AudioFormat audioFormat;
            AudioFloatConverter converter2 = AudioFloatConverter.getConverter(audioInputStream.getFormat());
            this.converter = converter2;
            if (converter2 == null) {
                AudioFormat format = audioInputStream.getFormat();
                AudioFormat[] targetFormats = AudioSystem.getTargetFormats(AudioFormat.Encoding.PCM_SIGNED, format);
                if (targetFormats.length != 0) {
                    audioFormat = targetFormats[0];
                } else {
                    float sampleRate = format.getSampleRate();
                    format.getSampleSizeInBits();
                    format.getFrameSize();
                    format.getFrameRate();
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sampleRate, 16, format.getChannels(), format.getChannels() * 2, sampleRate, false);
                }
                audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
                this.converter = AudioFloatConverter.getConverter(audioInputStream.getFormat());
            }
            this.framesize_pc = audioInputStream.getFormat().getFrameSize() / audioInputStream.getFormat().getChannels();
            this.stream = audioInputStream;
        }

        public AudioFormat getFormat() {
            return this.stream.getFormat();
        }

        public long getFrameLength() {
            return this.stream.getFrameLength();
        }

        public int read(float[] fArr, int i, int i2) throws IOException {
            int i3 = i2 * this.framesize_pc;
            byte[] bArr = this.buffer;
            if (bArr == null || bArr.length < i3) {
                this.buffer = new byte[i3];
            }
            int read = this.stream.read(this.buffer, 0, i3);
            if (read == -1) {
                return -1;
            }
            this.converter.toFloatArray(this.buffer, fArr, i, read / this.framesize_pc);
            return read / this.framesize_pc;
        }

        public long skip(long j) throws IOException {
            long skip = this.stream.skip(j * ((long) this.framesize_pc));
            if (skip == -1) {
                return -1;
            }
            return skip / ((long) this.framesize_pc);
        }

        public int available() throws IOException {
            return this.stream.available() / this.framesize_pc;
        }

        public void close() throws IOException {
            this.stream.close();
        }

        public void mark(int i) {
            this.stream.mark(i * this.framesize_pc);
        }

        public boolean markSupported() {
            return this.stream.markSupported();
        }

        public void reset() throws IOException {
            this.stream.reset();
        }
    }

    public static AudioFloatInputStream getInputStream(URL url) throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFloatInputStream(AudioSystem.getAudioInputStream(url));
    }

    public static AudioFloatInputStream getInputStream(File file) throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFloatInputStream(AudioSystem.getAudioInputStream(file));
    }

    public static AudioFloatInputStream getInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        return new DirectAudioFloatInputStream(AudioSystem.getAudioInputStream(inputStream));
    }

    public static AudioFloatInputStream getInputStream(AudioInputStream audioInputStream) {
        return new DirectAudioFloatInputStream(audioInputStream);
    }

    public static AudioFloatInputStream getInputStream(AudioFormat audioFormat, byte[] bArr, int i, int i2) {
        long j;
        AudioFloatConverter converter = AudioFloatConverter.getConverter(audioFormat);
        if (converter != null) {
            return new BytaArrayAudioFloatInputStream(converter, bArr, i, i2);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
        if (audioFormat.getFrameSize() == -1) {
            j = -1;
        } else {
            j = (long) (i2 / audioFormat.getFrameSize());
        }
        return getInputStream(new AudioInputStream(byteArrayInputStream, audioFormat, j));
    }

    public int read(float[] fArr) throws IOException {
        return read(fArr, 0, fArr.length);
    }

    public float read() throws IOException {
        float[] fArr = new float[1];
        int read = read(fArr, 0, 1);
        if (read == -1 || read == 0) {
            return 0.0f;
        }
        return fArr[0];
    }
}
