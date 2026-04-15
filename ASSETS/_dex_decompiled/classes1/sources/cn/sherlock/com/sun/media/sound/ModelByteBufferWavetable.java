package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.io.InputStream;

public class ModelByteBufferWavetable implements ModelWavetable {
    private float attenuation;
    /* access modifiers changed from: private */
    public ModelByteBuffer buffer;
    /* access modifiers changed from: private */
    public ModelByteBuffer buffer8;
    /* access modifiers changed from: private */
    public AudioFormat format;
    private float loopLength;
    private float loopStart;
    private int loopType;
    private float pitchcorrection;

    public ModelOscillatorStream open(float f) {
        return null;
    }

    private class Buffer8PlusInputStream extends InputStream {
        private boolean bigendian;
        private int framesize_pc;
        int markpos = 0;
        int markpos2 = 0;
        int pos = 0;
        int pos2 = 0;

        public boolean markSupported() {
            return true;
        }

        public Buffer8PlusInputStream() {
            this.framesize_pc = ModelByteBufferWavetable.this.format.getFrameSize() / ModelByteBufferWavetable.this.format.getChannels();
            this.bigendian = ModelByteBufferWavetable.this.format.isBigEndian();
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int available = available();
            if (available <= 0) {
                return -1;
            }
            if (i2 > available) {
                i2 = available;
            }
            byte[] array = ModelByteBufferWavetable.this.buffer.array();
            byte[] array2 = ModelByteBufferWavetable.this.buffer8.array();
            this.pos = (int) (((long) this.pos) + ModelByteBufferWavetable.this.buffer.arrayOffset());
            this.pos2 = (int) (((long) this.pos2) + ModelByteBufferWavetable.this.buffer8.arrayOffset());
            int i3 = 0;
            if (this.bigendian) {
                while (i3 < i2) {
                    System.arraycopy(array, this.pos, bArr, i3, this.framesize_pc);
                    System.arraycopy(array2, this.pos2, bArr, this.framesize_pc + i3, 1);
                    int i4 = this.pos;
                    int i5 = this.framesize_pc;
                    this.pos = i4 + i5;
                    this.pos2++;
                    i3 += i5 + 1;
                }
            } else {
                while (i3 < i2) {
                    System.arraycopy(array2, this.pos2, bArr, i3, 1);
                    System.arraycopy(array, this.pos, bArr, i3 + 1, this.framesize_pc);
                    int i6 = this.pos;
                    int i7 = this.framesize_pc;
                    this.pos = i6 + i7;
                    this.pos2++;
                    i3 += i7 + 1;
                }
            }
            this.pos = (int) (((long) this.pos) - ModelByteBufferWavetable.this.buffer.arrayOffset());
            this.pos2 = (int) (((long) this.pos2) - ModelByteBufferWavetable.this.buffer8.arrayOffset());
            return i2;
        }

        public long skip(long j) throws IOException {
            int available = available();
            if (available <= 0) {
                return -1;
            }
            long j2 = (long) available;
            if (j > j2) {
                j = j2;
            }
            int i = this.framesize_pc;
            this.pos = (int) (((long) this.pos) + ((j / ((long) (i + 1))) * ((long) i)));
            this.pos2 = (int) (((long) this.pos2) + (j / ((long) (i + 1))));
            return super.skip(j);
        }

        public int read(byte[] bArr) throws IOException {
            return read(bArr, 0, bArr.length);
        }

        public int read() throws IOException {
            if (read(new byte[1], 0, 1) == -1) {
                return -1;
            }
            return 0;
        }

        public int available() throws IOException {
            return ((((int) ModelByteBufferWavetable.this.buffer.capacity()) + ((int) ModelByteBufferWavetable.this.buffer8.capacity())) - this.pos) - this.pos2;
        }

        public synchronized void mark(int i) {
            this.markpos = this.pos;
            this.markpos2 = this.pos2;
        }

        public synchronized void reset() throws IOException {
            this.pos = this.markpos;
            this.pos2 = this.markpos2;
        }
    }

    public ModelByteBufferWavetable(ModelByteBuffer modelByteBuffer) {
        this.loopStart = -1.0f;
        this.loopLength = -1.0f;
        this.buffer8 = null;
        this.format = null;
        this.pitchcorrection = 0.0f;
        this.attenuation = 0.0f;
        this.loopType = 0;
        this.buffer = modelByteBuffer;
    }

    public ModelByteBufferWavetable(ModelByteBuffer modelByteBuffer, float f) {
        this.loopStart = -1.0f;
        this.loopLength = -1.0f;
        this.buffer8 = null;
        this.format = null;
        this.attenuation = 0.0f;
        this.loopType = 0;
        this.buffer = modelByteBuffer;
        this.pitchcorrection = f;
    }

    public ModelByteBufferWavetable(ModelByteBuffer modelByteBuffer, AudioFormat audioFormat) {
        this.loopStart = -1.0f;
        this.loopLength = -1.0f;
        this.buffer8 = null;
        this.pitchcorrection = 0.0f;
        this.attenuation = 0.0f;
        this.loopType = 0;
        this.format = audioFormat;
        this.buffer = modelByteBuffer;
    }

    public ModelByteBufferWavetable(ModelByteBuffer modelByteBuffer, AudioFormat audioFormat, float f) {
        this.loopStart = -1.0f;
        this.loopLength = -1.0f;
        this.buffer8 = null;
        this.attenuation = 0.0f;
        this.loopType = 0;
        this.format = audioFormat;
        this.buffer = modelByteBuffer;
        this.pitchcorrection = f;
    }

    public void set8BitExtensionBuffer(ModelByteBuffer modelByteBuffer) {
        this.buffer8 = modelByteBuffer;
    }

    public ModelByteBuffer get8BitExtensionBuffer() {
        return this.buffer8;
    }

    public ModelByteBuffer getBuffer() {
        return this.buffer;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:5|6|7|8|9|10) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0016 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public cn.sherlock.javax.sound.sampled.AudioFormat getFormat() {
        /*
            r3 = this;
            cn.sherlock.javax.sound.sampled.AudioFormat r0 = r3.format
            if (r0 != 0) goto L_0x001a
            cn.sherlock.com.sun.media.sound.ModelByteBuffer r0 = r3.buffer
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            java.io.InputStream r0 = r0.getInputStream()
            cn.sherlock.javax.sound.sampled.AudioFileFormat r2 = cn.sherlock.javax.sound.sampled.AudioSystem.getAudioFileFormat(r0)     // Catch:{ Exception -> 0x0016 }
            cn.sherlock.javax.sound.sampled.AudioFormat r1 = r2.getFormat()     // Catch:{ Exception -> 0x0016 }
        L_0x0016:
            r0.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0019:
            return r1
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.ModelByteBufferWavetable.getFormat():cn.sherlock.javax.sound.sampled.AudioFormat");
    }

    public AudioFloatInputStream openStream() {
        ModelByteBuffer modelByteBuffer = this.buffer;
        if (modelByteBuffer == null) {
            return null;
        }
        if (this.format == null) {
            try {
                return AudioFloatInputStream.getInputStream(AudioSystem.getAudioInputStream(modelByteBuffer.getInputStream()));
            } catch (Exception unused) {
                return null;
            }
        } else if (modelByteBuffer.array() == null) {
            return AudioFloatInputStream.getInputStream(new AudioInputStream(this.buffer.getInputStream(), this.format, this.buffer.capacity() / ((long) this.format.getFrameSize())));
        } else {
            if (this.buffer8 == null || (!this.format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) && !this.format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))) {
                return AudioFloatInputStream.getInputStream(this.format, this.buffer.array(), (int) this.buffer.arrayOffset(), (int) this.buffer.capacity());
            }
            return AudioFloatInputStream.getInputStream(new AudioInputStream(new Buffer8PlusInputStream(), new AudioFormat(this.format.getEncoding(), this.format.getSampleRate(), this.format.getSampleSizeInBits() + 8, this.format.getChannels(), this.format.getFrameSize() + this.format.getChannels(), this.format.getFrameRate(), this.format.isBigEndian()), this.buffer.capacity() / ((long) this.format.getFrameSize())));
        }
    }

    public int getChannels() {
        return getFormat().getChannels();
    }

    public float getAttenuation() {
        return this.attenuation;
    }

    public void setAttenuation(float f) {
        this.attenuation = f;
    }

    public float getLoopLength() {
        return this.loopLength;
    }

    public void setLoopLength(float f) {
        this.loopLength = f;
    }

    public float getLoopStart() {
        return this.loopStart;
    }

    public void setLoopStart(float f) {
        this.loopStart = f;
    }

    public void setLoopType(int i) {
        this.loopType = i;
    }

    public int getLoopType() {
        return this.loopType;
    }

    public float getPitchcorrection() {
        return this.pitchcorrection;
    }

    public void setPitchcorrection(float f) {
        this.pitchcorrection = f;
    }
}
