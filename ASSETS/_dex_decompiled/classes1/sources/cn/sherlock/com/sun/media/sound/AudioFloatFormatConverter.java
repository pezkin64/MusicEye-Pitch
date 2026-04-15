package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.spi.FormatConversionProvider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AudioFloatFormatConverter extends FormatConversionProvider {
    private AudioFormat.Encoding[] formats = {AudioFormat.Encoding.PCM_SIGNED, AudioFormat.Encoding.PCM_UNSIGNED, AudioFloatConverter.PCM_FLOAT};

    private static class AudioFloatFormatConverterInputStream extends InputStream {
        private AudioFloatConverter converter;
        private int fsize = 0;
        private float[] readfloatbuffer;
        private AudioFloatInputStream stream;

        public AudioFloatFormatConverterInputStream(AudioFormat audioFormat, AudioFloatInputStream audioFloatInputStream) {
            this.stream = audioFloatInputStream;
            this.converter = AudioFloatConverter.getConverter(audioFormat);
            this.fsize = (audioFormat.getSampleSizeInBits() + 7) / 8;
        }

        public int read() throws IOException {
            byte[] bArr = new byte[1];
            int read = read(bArr);
            if (read < 0) {
                return read;
            }
            return bArr[0] & 255;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = i2 / this.fsize;
            float[] fArr = this.readfloatbuffer;
            if (fArr == null || fArr.length < i3) {
                this.readfloatbuffer = new float[i3];
            }
            int read = this.stream.read(this.readfloatbuffer, 0, i3);
            if (read < 0) {
                return read;
            }
            this.converter.toByteArray(this.readfloatbuffer, 0, read, bArr, i);
            return read * this.fsize;
        }

        public int available() throws IOException {
            int available = this.stream.available();
            if (available < 0) {
                return available;
            }
            return available * this.fsize;
        }

        public void close() throws IOException {
            this.stream.close();
        }

        public synchronized void mark(int i) {
            this.stream.mark(i * this.fsize);
        }

        public boolean markSupported() {
            return this.stream.markSupported();
        }

        public synchronized void reset() throws IOException {
            this.stream.reset();
        }

        public long skip(long j) throws IOException {
            long skip = this.stream.skip(j / ((long) this.fsize));
            if (skip < 0) {
                return skip;
            }
            return skip * ((long) this.fsize);
        }
    }

    private static class AudioFloatInputStreamChannelMixer extends AudioFloatInputStream {
        private AudioFloatInputStream ais;
        private float[] conversion_buffer;
        private int sourceChannels;
        private int targetChannels;
        private AudioFormat targetFormat;

        public AudioFloatInputStreamChannelMixer(AudioFloatInputStream audioFloatInputStream, int i) {
            this.sourceChannels = audioFloatInputStream.getFormat().getChannels();
            this.targetChannels = i;
            this.ais = audioFloatInputStream;
            AudioFormat format = audioFloatInputStream.getFormat();
            this.targetFormat = new AudioFormat(format.getEncoding(), format.getSampleRate(), format.getSampleSizeInBits(), i, (format.getFrameSize() / this.sourceChannels) * i, format.getFrameRate(), format.isBigEndian());
        }

        public int available() throws IOException {
            return (this.ais.available() / this.sourceChannels) * this.targetChannels;
        }

        public void close() throws IOException {
            this.ais.close();
        }

        public AudioFormat getFormat() {
            return this.targetFormat;
        }

        public long getFrameLength() {
            return this.ais.getFrameLength();
        }

        public void mark(int i) {
            this.ais.mark((i / this.targetChannels) * this.sourceChannels);
        }

        public boolean markSupported() {
            return this.ais.markSupported();
        }

        public int read(float[] fArr, int i, int i2) throws IOException {
            int i3;
            int i4 = (i2 / this.targetChannels) * this.sourceChannels;
            float[] fArr2 = this.conversion_buffer;
            if (fArr2 == null || fArr2.length < i4) {
                this.conversion_buffer = new float[i4];
            }
            int i5 = 0;
            int read = this.ais.read(this.conversion_buffer, 0, i4);
            if (read < 0) {
                return read;
            }
            int i6 = this.sourceChannels;
            if (i6 == 1) {
                int i7 = this.targetChannels;
                for (int i8 = 0; i8 < this.targetChannels; i8++) {
                    int i9 = i + i8;
                    int i10 = 0;
                    while (i10 < i4) {
                        fArr[i9] = this.conversion_buffer[i10];
                        i10++;
                        i9 += i7;
                    }
                }
            } else {
                int i11 = this.targetChannels;
                if (i11 == 1) {
                    int i12 = i;
                    int i13 = 0;
                    while (i13 < i4) {
                        fArr[i12] = this.conversion_buffer[i13];
                        i13 += i6;
                        i12++;
                    }
                    int i14 = 1;
                    while (true) {
                        i3 = this.sourceChannels;
                        if (i14 >= i3) {
                            break;
                        }
                        int i15 = i;
                        int i16 = i14;
                        while (i16 < i4) {
                            fArr[i15] = fArr[i15] + this.conversion_buffer[i16];
                            i16 += i6;
                            i15++;
                        }
                        i14++;
                    }
                    float f = 1.0f / ((float) i3);
                    while (i5 < i4) {
                        fArr[i] = fArr[i] * f;
                        i5 += i6;
                        i++;
                    }
                } else {
                    int min = Math.min(i6, i11);
                    int i17 = i2 + i;
                    int i18 = this.targetChannels;
                    int i19 = this.sourceChannels;
                    while (i5 < min) {
                        int i20 = i + i5;
                        int i21 = i5;
                        while (i20 < i17) {
                            fArr[i20] = this.conversion_buffer[i21];
                            i20 += i18;
                            i21 += i19;
                        }
                        i5++;
                    }
                    while (min < this.targetChannels) {
                        for (int i22 = i + min; i22 < i17; i22 += i18) {
                            fArr[i22] = 0.0f;
                        }
                        min++;
                    }
                }
            }
            return (read / this.sourceChannels) * this.targetChannels;
        }

        public void reset() throws IOException {
            this.ais.reset();
        }

        public long skip(long j) throws IOException {
            long skip = this.ais.skip((j / ((long) this.targetChannels)) * ((long) this.sourceChannels));
            if (skip < 0) {
                return skip;
            }
            return (skip / ((long) this.sourceChannels)) * ((long) this.targetChannels);
        }
    }

    private static class AudioFloatInputStreamResampler extends AudioFloatInputStream {
        private AudioFloatInputStream ais;
        private int buffer_len = 512;
        private float[][] cbuffer;
        private float[][] ibuffer;
        private float[] ibuffer2;
        private float ibuffer_index = 0.0f;
        private int ibuffer_len = 0;
        private float[] ix = new float[1];
        private float[][] mark_ibuffer = null;
        private float mark_ibuffer_index = 0.0f;
        private int mark_ibuffer_len = 0;
        private int nrofchannels = 0;
        private int[] ox = new int[1];
        private int pad;
        private int pad2;
        private float[] pitch = new float[1];
        private SoftAbstractResampler resampler;
        private float[] skipbuffer;
        private AudioFormat targetFormat;

        public int available() throws IOException {
            return 0;
        }

        public long getFrameLength() {
            return -1;
        }

        public AudioFloatInputStreamResampler(AudioFloatInputStream audioFloatInputStream, AudioFormat audioFormat) {
            this.ais = audioFloatInputStream;
            AudioFormat format = audioFloatInputStream.getFormat();
            AudioFormat audioFormat2 = new AudioFormat(format.getEncoding(), audioFormat.getSampleRate(), format.getSampleSizeInBits(), format.getChannels(), format.getFrameSize(), audioFormat.getSampleRate(), format.isBigEndian());
            this.targetFormat = audioFormat2;
            this.nrofchannels = audioFormat2.getChannels();
            Object property = audioFormat.getProperty("interpolation");
            if (property != null && (property instanceof String)) {
                String str = (String) property;
                if (str.equalsIgnoreCase("point")) {
                    this.resampler = new SoftPointResampler();
                }
                if (str.equalsIgnoreCase("linear")) {
                    this.resampler = new SoftLinearResampler2();
                }
                if (str.equalsIgnoreCase("linear1")) {
                    this.resampler = new SoftLinearResampler();
                }
                if (str.equalsIgnoreCase("linear2")) {
                    this.resampler = new SoftLinearResampler2();
                }
                if (str.equalsIgnoreCase("cubic")) {
                    this.resampler = new SoftCubicResampler();
                }
                if (str.equalsIgnoreCase("lanczos")) {
                    this.resampler = new SoftLanczosResampler();
                }
                if (str.equalsIgnoreCase("sinc")) {
                    this.resampler = new SoftSincResampler();
                }
            }
            if (this.resampler == null) {
                this.resampler = new SoftLinearResampler2();
            }
            this.pitch[0] = format.getSampleRate() / audioFormat.getSampleRate();
            int padding = this.resampler.getPadding();
            this.pad = padding;
            int i = padding * 2;
            this.pad2 = i;
            int i2 = this.nrofchannels;
            int i3 = this.buffer_len + i;
            int[] iArr = new int[2];
            iArr[1] = i3;
            iArr[0] = i2;
            this.ibuffer = (float[][]) Array.newInstance(Float.TYPE, iArr);
            int i4 = this.nrofchannels;
            int i5 = this.buffer_len;
            this.ibuffer2 = new float[(i4 * i5)];
            this.ibuffer_index = (float) (this.pad + i5);
            this.ibuffer_len = i5;
        }

        public void close() throws IOException {
            this.ais.close();
        }

        public AudioFormat getFormat() {
            return this.targetFormat;
        }

        public void mark(int i) {
            this.ais.mark((int) (((float) i) * this.pitch[0]));
            this.mark_ibuffer_index = this.ibuffer_index;
            this.mark_ibuffer_len = this.ibuffer_len;
            if (this.mark_ibuffer == null) {
                float[][] fArr = this.ibuffer;
                int length = fArr.length;
                int[] iArr = new int[2];
                iArr[1] = fArr[0].length;
                iArr[0] = length;
                this.mark_ibuffer = (float[][]) Array.newInstance(Float.TYPE, iArr);
            }
            int i2 = 0;
            while (true) {
                float[][] fArr2 = this.ibuffer;
                if (i2 < fArr2.length) {
                    float[] fArr3 = fArr2[i2];
                    float[] fArr4 = this.mark_ibuffer[i2];
                    for (int i3 = 0; i3 < fArr4.length; i3++) {
                        fArr4[i3] = fArr3[i3];
                    }
                    i2++;
                } else {
                    return;
                }
            }
        }

        public boolean markSupported() {
            return this.ais.markSupported();
        }

        private void readNextBuffer() throws IOException {
            int read;
            if (this.ibuffer_len != -1) {
                for (int i = 0; i < this.nrofchannels; i++) {
                    float[] fArr = this.ibuffer[i];
                    int i2 = this.ibuffer_len;
                    int i3 = this.pad2 + i2;
                    int i4 = 0;
                    while (i2 < i3) {
                        fArr[i4] = fArr[i2];
                        i2++;
                        i4++;
                    }
                }
                this.ibuffer_index -= (float) this.ibuffer_len;
                int read2 = this.ais.read(this.ibuffer2);
                this.ibuffer_len = read2;
                if (read2 >= 0) {
                    while (true) {
                        int i5 = this.ibuffer_len;
                        float[] fArr2 = this.ibuffer2;
                        if (i5 >= fArr2.length || (read = this.ais.read(fArr2, i5, fArr2.length - i5)) == -1) {
                            float[] fArr3 = this.ibuffer2;
                            Arrays.fill(fArr3, this.ibuffer_len, fArr3.length, 0.0f);
                            this.ibuffer_len /= this.nrofchannels;
                        } else {
                            this.ibuffer_len += read;
                        }
                    }
                    float[] fArr32 = this.ibuffer2;
                    Arrays.fill(fArr32, this.ibuffer_len, fArr32.length, 0.0f);
                    this.ibuffer_len /= this.nrofchannels;
                } else {
                    float[] fArr4 = this.ibuffer2;
                    Arrays.fill(fArr4, 0, fArr4.length, 0.0f);
                }
                int length = this.ibuffer2.length;
                for (int i6 = 0; i6 < this.nrofchannels; i6++) {
                    float[] fArr5 = this.ibuffer[i6];
                    int i7 = this.pad2;
                    int i8 = i6;
                    while (i8 < length) {
                        fArr5[i7] = this.ibuffer2[i8];
                        i8 += this.nrofchannels;
                        i7++;
                    }
                }
            }
        }

        public int read(float[] fArr, int i, int i2) throws IOException {
            float[][] fArr2 = this.cbuffer;
            if (fArr2 == null || fArr2[0].length < i2 / this.nrofchannels) {
                int i3 = this.nrofchannels;
                int[] iArr = new int[2];
                iArr[1] = i2 / i3;
                iArr[0] = i3;
                this.cbuffer = (float[][]) Array.newInstance(Float.TYPE, iArr);
            }
            int i4 = this.ibuffer_len;
            if (i4 == -1) {
                return -1;
            }
            if (i2 < 0) {
                return 0;
            }
            int i5 = i + i2;
            int i6 = i2 / this.nrofchannels;
            int i7 = 0;
            while (i6 > 0) {
                int i8 = this.ibuffer_len;
                if (i8 >= 0) {
                    if (this.ibuffer_index >= ((float) (i8 + this.pad))) {
                        readNextBuffer();
                    }
                    i4 = this.ibuffer_len + this.pad;
                }
                if (this.ibuffer_len < 0) {
                    i4 = this.pad2;
                    if (this.ibuffer_index >= ((float) i4)) {
                        break;
                    }
                }
                if (this.ibuffer_index < 0.0f) {
                    break;
                }
                int i9 = 0;
                while (true) {
                    int i10 = this.nrofchannels;
                    if (i9 >= i10) {
                        break;
                    }
                    float[] fArr3 = this.ix;
                    fArr3[0] = this.ibuffer_index;
                    int[] iArr2 = this.ox;
                    iArr2[0] = i7;
                    this.resampler.interpolate(this.ibuffer[i9], fArr3, (float) i4, this.pitch, 0.0f, this.cbuffer[i9], iArr2, i2 / i10);
                    i9++;
                }
                this.ibuffer_index = this.ix[0];
                int i11 = this.ox[0];
                i6 -= i11 - i7;
                i7 = i11;
            }
            int i12 = 0;
            while (true) {
                int i13 = this.nrofchannels;
                if (i12 >= i13) {
                    return i2 - (i6 * i13);
                }
                float[] fArr4 = this.cbuffer[i12];
                int i14 = i12 + i;
                int i15 = 0;
                while (i14 < i5) {
                    fArr[i14] = fArr4[i15];
                    i14 += this.nrofchannels;
                    i15++;
                }
                i12++;
            }
        }

        public void reset() throws IOException {
            this.ais.reset();
            if (this.mark_ibuffer != null) {
                this.ibuffer_index = this.mark_ibuffer_index;
                this.ibuffer_len = this.mark_ibuffer_len;
                int i = 0;
                while (true) {
                    float[][] fArr = this.ibuffer;
                    if (i < fArr.length) {
                        float[] fArr2 = this.mark_ibuffer[i];
                        float[] fArr3 = fArr[i];
                        for (int i2 = 0; i2 < fArr3.length; i2++) {
                            fArr3[i2] = fArr2[i2];
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }

        public long skip(long j) throws IOException {
            if (j < 0) {
                return 0;
            }
            if (this.skipbuffer == null) {
                this.skipbuffer = new float[(this.targetFormat.getFrameSize() * 1024)];
            }
            float[] fArr = this.skipbuffer;
            long j2 = j;
            while (true) {
                if (j2 <= 0) {
                    break;
                }
                int read = read(fArr, 0, (int) Math.min(j2, (long) this.skipbuffer.length));
                if (read >= 0) {
                    j2 -= (long) read;
                } else if (j2 == j) {
                    return (long) read;
                }
            }
            return j - j2;
        }
    }

    public AudioInputStream getAudioInputStream(AudioFormat.Encoding encoding, AudioInputStream audioInputStream) {
        if (audioInputStream.getFormat().getEncoding().equals(encoding)) {
            return audioInputStream;
        }
        AudioFormat format = audioInputStream.getFormat();
        int channels = format.getChannels();
        float sampleRate = format.getSampleRate();
        int sampleSizeInBits = format.getSampleSizeInBits();
        boolean isBigEndian = format.isBigEndian();
        if (encoding.equals(AudioFloatConverter.PCM_FLOAT)) {
            sampleSizeInBits = 32;
        }
        int i = sampleSizeInBits;
        return getAudioInputStream(new AudioFormat(encoding, sampleRate, i, channels, (channels * i) / 8, sampleRate, isBigEndian), audioInputStream);
    }

    public AudioInputStream getAudioInputStream(AudioFormat audioFormat, AudioInputStream audioInputStream) {
        if (isConversionSupported(audioFormat, audioInputStream.getFormat())) {
            return getAudioInputStream(audioFormat, AudioFloatInputStream.getInputStream(audioInputStream));
        }
        throw new IllegalArgumentException("Unsupported conversion: " + audioInputStream.getFormat().toString() + " to " + audioFormat.toString());
    }

    public AudioInputStream getAudioInputStream(AudioFormat audioFormat, AudioFloatInputStreamResampler audioFloatInputStreamResampler) {
        if (isConversionSupported(audioFormat, audioFloatInputStreamResampler.getFormat())) {
            if (audioFormat.getChannels() != audioFloatInputStreamResampler.getFormat().getChannels()) {
                audioFloatInputStreamResampler = new AudioFloatInputStreamChannelMixer(audioFloatInputStreamResampler, audioFormat.getChannels());
            }
            if (((double) Math.abs(audioFormat.getSampleRate() - audioFloatInputStreamResampler.getFormat().getSampleRate())) > 1.0E-6d) {
                audioFloatInputStreamResampler = new AudioFloatInputStreamResampler(audioFloatInputStreamResampler, audioFormat);
            }
            return new AudioInputStream(new AudioFloatFormatConverterInputStream(audioFormat, audioFloatInputStreamResampler), audioFormat, audioFloatInputStreamResampler.getFrameLength());
        }
        throw new IllegalArgumentException("Unsupported conversion: " + audioFloatInputStreamResampler.getFormat().toString() + " to " + audioFormat.toString());
    }

    public AudioFormat.Encoding[] getSourceEncodings() {
        return new AudioFormat.Encoding[]{AudioFormat.Encoding.PCM_SIGNED, AudioFormat.Encoding.PCM_UNSIGNED, AudioFloatConverter.PCM_FLOAT};
    }

    public AudioFormat.Encoding[] getTargetEncodings() {
        return new AudioFormat.Encoding[]{AudioFormat.Encoding.PCM_SIGNED, AudioFormat.Encoding.PCM_UNSIGNED, AudioFloatConverter.PCM_FLOAT};
    }

    public AudioFormat.Encoding[] getTargetEncodings(AudioFormat audioFormat) {
        if (AudioFloatConverter.getConverter(audioFormat) == null) {
            return new AudioFormat.Encoding[0];
        }
        return new AudioFormat.Encoding[]{AudioFormat.Encoding.PCM_SIGNED, AudioFormat.Encoding.PCM_UNSIGNED, AudioFloatConverter.PCM_FLOAT};
    }

    public AudioFormat[] getTargetFormats(AudioFormat.Encoding encoding, AudioFormat audioFormat) {
        if (AudioFloatConverter.getConverter(audioFormat) == null) {
            return new AudioFormat[0];
        }
        int channels = audioFormat.getChannels();
        ArrayList arrayList = new ArrayList();
        if (encoding.equals(AudioFormat.Encoding.PCM_SIGNED)) {
            arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, -1.0f, 8, channels, channels, -1.0f, false));
        }
        if (encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED)) {
            arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, -1.0f, 8, channels, channels, -1.0f, false));
        }
        for (int i = 16; i < 32; i += 8) {
            if (encoding.equals(AudioFormat.Encoding.PCM_SIGNED)) {
                int i2 = (channels * i) / 8;
                arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, -1.0f, i, channels, i2, -1.0f, false));
                arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, -1.0f, i, channels, i2, -1.0f, true));
            }
            if (encoding.equals(AudioFormat.Encoding.PCM_UNSIGNED)) {
                int i3 = (channels * i) / 8;
                arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, -1.0f, i, channels, i3, -1.0f, true));
                arrayList.add(new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, -1.0f, i, channels, i3, -1.0f, false));
            }
        }
        if (encoding.equals(AudioFloatConverter.PCM_FLOAT)) {
            int i4 = channels * 4;
            arrayList.add(new AudioFormat(AudioFloatConverter.PCM_FLOAT, -1.0f, 32, channels, i4, -1.0f, false));
            arrayList.add(new AudioFormat(AudioFloatConverter.PCM_FLOAT, -1.0f, 32, channels, i4, -1.0f, true));
            int i5 = channels * 8;
            arrayList.add(new AudioFormat(AudioFloatConverter.PCM_FLOAT, -1.0f, 64, channels, i5, -1.0f, false));
            arrayList.add(new AudioFormat(AudioFloatConverter.PCM_FLOAT, -1.0f, 64, channels, i5, -1.0f, true));
        }
        return (AudioFormat[]) arrayList.toArray(new AudioFormat[arrayList.size()]);
    }

    public boolean isConversionSupported(AudioFormat audioFormat, AudioFormat audioFormat2) {
        if (AudioFloatConverter.getConverter(audioFormat2) != null && AudioFloatConverter.getConverter(audioFormat) != null && audioFormat2.getChannels() > 0 && audioFormat.getChannels() > 0) {
            return true;
        }
        return false;
    }

    public boolean isConversionSupported(AudioFormat.Encoding encoding, AudioFormat audioFormat) {
        if (AudioFloatConverter.getConverter(audioFormat) == null) {
            return false;
        }
        int i = 0;
        while (true) {
            AudioFormat.Encoding[] encodingArr = this.formats;
            if (i >= encodingArr.length) {
                return false;
            }
            if (encoding.equals(encodingArr[i])) {
                return true;
            }
            i++;
        }
    }
}
