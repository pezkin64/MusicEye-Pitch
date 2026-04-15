package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import java.util.Arrays;

public class SoftAudioBuffer {
    private float[] buffer;
    private AudioFloatConverter converter;
    private byte[] converter_buffer;
    private boolean empty = true;
    private AudioFormat format;
    private int size;

    public SoftAudioBuffer(int i, AudioFormat audioFormat) {
        this.size = i;
        this.format = audioFormat;
        this.converter = AudioFloatConverter.getConverter(audioFormat);
    }

    public void swap(SoftAudioBuffer softAudioBuffer) {
        int i = this.size;
        float[] fArr = this.buffer;
        boolean z = this.empty;
        AudioFormat audioFormat = this.format;
        AudioFloatConverter audioFloatConverter = this.converter;
        byte[] bArr = this.converter_buffer;
        this.size = softAudioBuffer.size;
        this.buffer = softAudioBuffer.buffer;
        this.empty = softAudioBuffer.empty;
        this.format = softAudioBuffer.format;
        this.converter = softAudioBuffer.converter;
        this.converter_buffer = softAudioBuffer.converter_buffer;
        softAudioBuffer.size = i;
        softAudioBuffer.buffer = fArr;
        softAudioBuffer.empty = z;
        softAudioBuffer.format = audioFormat;
        softAudioBuffer.converter = audioFloatConverter;
        softAudioBuffer.converter_buffer = bArr;
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public int getSize() {
        return this.size;
    }

    public void clear() {
        if (!this.empty) {
            Arrays.fill(this.buffer, 0.0f);
            this.empty = true;
        }
    }

    public boolean isSilent() {
        return this.empty;
    }

    public float[] array() {
        this.empty = false;
        if (this.buffer == null) {
            this.buffer = new float[this.size];
        }
        return this.buffer;
    }

    public void get(byte[] bArr, int i) {
        int frameSize = this.format.getFrameSize() / this.format.getChannels();
        int i2 = this.size * frameSize;
        byte[] bArr2 = this.converter_buffer;
        if (bArr2 == null || bArr2.length < i2) {
            this.converter_buffer = new byte[i2];
        }
        if (this.format.getChannels() == 1) {
            this.converter.toByteArray(array(), this.size, bArr);
            return;
        }
        this.converter.toByteArray(array(), this.size, this.converter_buffer);
        if (i < this.format.getChannels()) {
            int channels = this.format.getChannels() * frameSize;
            for (int i3 = 0; i3 < frameSize; i3++) {
                int i4 = (i * frameSize) + i3;
                int i5 = i3;
                for (int i6 = 0; i6 < this.size; i6++) {
                    bArr[i4] = this.converter_buffer[i5];
                    i4 += channels;
                    i5 += frameSize;
                }
            }
        }
    }
}
