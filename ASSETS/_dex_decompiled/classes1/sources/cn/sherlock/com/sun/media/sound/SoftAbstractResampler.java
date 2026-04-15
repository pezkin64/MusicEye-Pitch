package cn.sherlock.com.sun.media.sound;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.VoiceStatus;

public abstract class SoftAbstractResampler implements SoftResampler {
    public abstract int getPadding();

    public abstract void interpolate(float[] fArr, float[] fArr2, float f, float[] fArr3, float f2, float[] fArr4, int[] iArr, int i);

    private class ModelAbstractResamplerStream implements SoftResamplerStreamer {
        float[] current_pitch = new float[1];
        boolean eof;
        float[][] ibuffer;
        boolean ibuffer_order = true;
        float[] ix = new float[1];
        boolean loopdirection = true;
        float looplen;
        int loopmode;
        float loopstart;
        int marklimit = 0;
        boolean markset = false;
        boolean noteOff_flag = false;
        int nrofchannels = 2;
        int[] ox = new int[1];
        int pad;
        int pad2;
        float pitchcorrection = 0.0f;
        float samplerateconv = 1.0f;
        float[] sbuffer;
        int sector_loopstart = -1;
        int sector_pos = 0;
        int sector_size = 400;
        boolean started;
        AudioFloatInputStream stream;
        boolean stream_eof = false;
        int streampos = 0;
        float target_pitch;

        public void noteOn(MidiChannel midiChannel, VoiceStatus voiceStatus, int i, int i2) {
        }

        public ModelAbstractResamplerStream() {
            this.pad = SoftAbstractResampler.this.getPadding();
            int padding = SoftAbstractResampler.this.getPadding() * 2;
            this.pad2 = padding;
            int i = this.sector_size + padding;
            int[] iArr = new int[2];
            iArr[1] = i;
            iArr[0] = 2;
            this.ibuffer = (float[][]) Array.newInstance(Float.TYPE, iArr);
            this.ibuffer_order = true;
        }

        public void noteOff(int i) {
            this.noteOff_flag = true;
        }

        public void open(ModelWavetable modelWavetable, float f) throws IOException {
            this.eof = false;
            int channels = modelWavetable.getChannels();
            this.nrofchannels = channels;
            if (this.ibuffer.length < channels) {
                int[] iArr = new int[2];
                iArr[1] = this.sector_size + this.pad2;
                iArr[0] = channels;
                this.ibuffer = (float[][]) Array.newInstance(Float.TYPE, iArr);
            }
            this.stream = modelWavetable.openStream();
            this.streampos = 0;
            this.stream_eof = false;
            this.pitchcorrection = modelWavetable.getPitchcorrection();
            this.samplerateconv = this.stream.getFormat().getSampleRate() / f;
            this.looplen = modelWavetable.getLoopLength();
            float loopStart = modelWavetable.getLoopStart();
            this.loopstart = loopStart;
            int i = ((int) (loopStart / ((float) this.sector_size))) - 1;
            this.sector_loopstart = i;
            this.sector_pos = 0;
            if (i < 0) {
                this.sector_loopstart = 0;
            }
            this.started = false;
            int loopType = modelWavetable.getLoopType();
            this.loopmode = loopType;
            if (loopType != 0) {
                this.markset = false;
                this.marklimit = this.nrofchannels * ((int) (this.looplen + ((float) this.pad2) + 1.0f));
            } else {
                this.markset = true;
            }
            float f2 = this.samplerateconv;
            this.target_pitch = f2;
            this.current_pitch[0] = f2;
            this.ibuffer_order = true;
            this.loopdirection = true;
            this.noteOff_flag = false;
            for (int i2 = 0; i2 < this.nrofchannels; i2++) {
                float[] fArr = this.ibuffer[i2];
                int i3 = this.sector_size;
                Arrays.fill(fArr, i3, this.pad2 + i3, 0.0f);
            }
            float[] fArr2 = this.ix;
            int i4 = this.pad;
            fArr2[0] = (float) i4;
            this.eof = false;
            int i5 = this.sector_size;
            fArr2[0] = (float) (i4 + i5);
            this.sector_pos = -1;
            this.streampos = -i5;
            nextBuffer();
        }

        public void setPitch(float f) {
            float exp = ((float) Math.exp(((double) (this.pitchcorrection + f)) * (Math.log(2.0d) / 1200.0d))) * this.samplerateconv;
            this.target_pitch = exp;
            if (!this.started) {
                this.current_pitch[0] = exp;
            }
        }

        public void nextBuffer() throws IOException {
            int i;
            int i2;
            if (this.ix[0] < ((float) this.pad) && this.markset) {
                this.stream.reset();
                float[] fArr = this.ix;
                float f = fArr[0];
                int i3 = this.streampos;
                int i4 = this.sector_loopstart;
                int i5 = this.sector_size;
                float f2 = f + ((float) (i3 - (i4 * i5)));
                fArr[0] = f2;
                this.sector_pos = i4;
                int i6 = i4 * i5;
                this.streampos = i6;
                fArr[0] = f2 + ((float) i5);
                this.sector_pos = i4 - 1;
                this.streampos = i6 - i5;
                this.stream_eof = false;
            }
            float[] fArr2 = this.ix;
            float f3 = fArr2[0];
            int i7 = this.sector_size;
            int i8 = this.pad;
            if (f3 < ((float) (i7 + i8)) || !this.stream_eof) {
                if (f3 >= ((float) ((i7 * 4) + i8))) {
                    int i9 = (int) (((f3 - ((float) (i7 * 4))) + ((float) i8)) / ((float) i7));
                    fArr2[0] = f3 - ((float) (i7 * i9));
                    this.sector_pos += i9;
                    this.streampos += i7 * i9;
                    this.stream.skip((long) (i7 * i9));
                }
                while (this.ix[0] >= ((float) (this.sector_size + this.pad))) {
                    if (!this.markset && this.sector_pos + 1 == this.sector_loopstart) {
                        this.stream.mark(this.marklimit);
                        this.markset = true;
                    }
                    float[] fArr3 = this.ix;
                    float f4 = fArr3[0];
                    int i10 = this.sector_size;
                    fArr3[0] = f4 - ((float) i10);
                    this.sector_pos++;
                    this.streampos += i10;
                    int i11 = 0;
                    while (true) {
                        i = this.nrofchannels;
                        if (i11 >= i) {
                            break;
                        }
                        float[] fArr4 = this.ibuffer[i11];
                        for (int i12 = 0; i12 < this.pad2; i12++) {
                            fArr4[i12] = fArr4[this.sector_size + i12];
                        }
                        i11++;
                    }
                    if (i != 1) {
                        int i13 = this.sector_size * i;
                        float[] fArr5 = this.sbuffer;
                        if (fArr5 == null || fArr5.length < i13) {
                            this.sbuffer = new float[i13];
                        }
                        int read = this.stream.read(this.sbuffer, 0, i13);
                        if (read != -1) {
                            i2 = read / this.nrofchannels;
                            int i14 = 0;
                            while (true) {
                                int i15 = this.nrofchannels;
                                if (i14 >= i15) {
                                    break;
                                }
                                float[] fArr6 = this.ibuffer[i14];
                                int i16 = this.pad2;
                                int i17 = 0;
                                int i18 = i14;
                                while (i17 < i2) {
                                    fArr6[i16] = this.sbuffer[i18];
                                    i17++;
                                    i18 += i15;
                                    i16++;
                                }
                                i14++;
                            }
                        } else {
                            i2 = -1;
                        }
                    } else {
                        i2 = this.stream.read(this.ibuffer[0], this.pad2, this.sector_size);
                    }
                    if (i2 == -1) {
                        this.stream_eof = true;
                        for (int i19 = 0; i19 < this.nrofchannels; i19++) {
                            float[] fArr7 = this.ibuffer[i19];
                            int i20 = this.pad2;
                            Arrays.fill(fArr7, i20, this.sector_size + i20, 0.0f);
                        }
                        return;
                    }
                    if (i2 != this.sector_size) {
                        for (int i21 = 0; i21 < this.nrofchannels; i21++) {
                            float[] fArr8 = this.ibuffer[i21];
                            int i22 = this.pad2;
                            Arrays.fill(fArr8, i22 + i2, i22 + this.sector_size, 0.0f);
                        }
                    }
                    this.ibuffer_order = true;
                }
                return;
            }
            this.eof = true;
        }

        public void reverseBuffers() {
            this.ibuffer_order = !this.ibuffer_order;
            for (int i = 0; i < this.nrofchannels; i++) {
                float[] fArr = this.ibuffer[i];
                int length = fArr.length - 1;
                int length2 = fArr.length / 2;
                for (int i2 = 0; i2 < length2; i2++) {
                    float f = fArr[i2];
                    int i3 = length - i2;
                    fArr[i2] = fArr[i3];
                    fArr[i3] = f;
                }
            }
        }

        public int read(float[][] fArr, int i, int i2) throws IOException {
            float f;
            int i3;
            int i4;
            int i5;
            int i6 = i2;
            if (this.eof) {
                return -1;
            }
            int i7 = 0;
            if (this.noteOff_flag && (this.loopmode & 2) != 0 && this.loopdirection) {
                this.loopmode = 0;
            }
            float f2 = this.target_pitch;
            float[] fArr2 = this.current_pitch;
            float f3 = (f2 - fArr2[0]) / ((float) i6);
            boolean z = true;
            this.started = true;
            int[] iArr = this.ox;
            iArr[0] = i;
            int i8 = i6 + i;
            int i9 = this.sector_size;
            int i10 = this.pad;
            float f4 = (float) (i9 + i10);
            if (!this.loopdirection) {
                f4 = (float) i10;
            }
            while (iArr[i7] != i8) {
                nextBuffer();
                boolean z2 = this.loopdirection;
                if (!z2) {
                    int i11 = this.streampos;
                    float f5 = this.loopstart;
                    int i12 = this.pad;
                    if (((float) i11) < ((float) i12) + f5) {
                        f = ((float) this.pad2) + (f5 - ((float) i11));
                        float[] fArr3 = this.ix;
                        float f6 = fArr3[i7];
                        if (f6 <= f) {
                            if ((this.loopmode & 4) != 0) {
                                this.loopdirection = z;
                                f = (float) (this.sector_size + i12);
                            } else {
                                fArr3[i7] = f6 + this.looplen;
                                f = (float) i12;
                            }
                        }
                    }
                    if (this.ibuffer_order != z2) {
                        reverseBuffers();
                    }
                    float[] fArr4 = this.ix;
                    int i13 = this.sector_size;
                    int i14 = this.pad2;
                    float f7 = ((float) (i13 + i14)) - fArr4[i7];
                    fArr4[i7] = f7;
                    float f8 = (((float) (i13 + i14)) - f) + 1.0f;
                    int i15 = iArr[i7];
                    float f9 = fArr2[i7];
                    int i16 = i7;
                    while (i16 < this.nrofchannels) {
                        float[] fArr5 = fArr[i16];
                        if (fArr5 != null) {
                            float[] fArr6 = this.ix;
                            fArr6[i7] = f7;
                            iArr[i7] = i15;
                            fArr2[i7] = f9;
                            int i17 = i16;
                            i5 = i17;
                            SoftAbstractResampler.this.interpolate(this.ibuffer[i17], fArr6, f8, fArr2, f3, fArr5, iArr, i8);
                        } else {
                            i5 = i16;
                        }
                        i16 = i5 + 1;
                    }
                    float[] fArr7 = this.ix;
                    int i18 = this.sector_size;
                    int i19 = this.pad2;
                    fArr7[i7] = ((float) (i18 + i19)) - fArr7[i7];
                    f = ((float) (i18 + i19)) - (f8 - 1.0f);
                    if (this.eof) {
                        fArr2[i7] = this.target_pitch;
                        i4 = iArr[i7];
                    }
                    z = true;
                } else {
                    int i20 = this.loopmode;
                    if (i20 != 0) {
                        int i21 = this.streampos;
                        int i22 = this.sector_size;
                        float f10 = this.looplen;
                        float f11 = this.loopstart;
                        i3 = i7;
                        int i23 = this.pad;
                        if (((float) (i21 + i22)) > f10 + f11 + ((float) i23)) {
                            f = ((f11 + f10) - ((float) i21)) + ((float) this.pad2);
                            float[] fArr8 = this.ix;
                            float f12 = fArr8[i3];
                            if (f12 >= f) {
                                if ((i20 & 4) == 0 && (i20 & 8) == 0) {
                                    f = (float) (i22 + i23);
                                    fArr8[i3] = f12 - f10;
                                    int i24 = i2;
                                    i7 = i3;
                                    z = true;
                                } else {
                                    this.loopdirection = i3;
                                    f = (float) i23;
                                    int i25 = i2;
                                    z = true;
                                    i7 = 0;
                                }
                            }
                        }
                    }
                    float f13 = f;
                    if (this.ibuffer_order != z2) {
                        reverseBuffers();
                    }
                    char c = 0;
                    float f14 = this.ix[0];
                    int i26 = iArr[0];
                    float f15 = fArr2[0];
                    int i27 = 0;
                    while (i27 < this.nrofchannels) {
                        float[] fArr9 = fArr[i27];
                        if (fArr9 != null) {
                            float[] fArr10 = this.ix;
                            fArr10[c] = f14;
                            iArr[c] = i26;
                            fArr2[c] = f15;
                            SoftAbstractResampler.this.interpolate(this.ibuffer[i27], fArr10, f13, fArr2, f3, fArr9, iArr, i8);
                        }
                        i27++;
                        c = 0;
                    }
                    if (this.eof) {
                        fArr2[0] = this.target_pitch;
                        i4 = iArr[0];
                    } else {
                        i3 = 0;
                        int i28 = i2;
                        f = f13;
                        i7 = i3;
                        z = true;
                    }
                }
                return i4 - i;
            }
            fArr2[i7] = this.target_pitch;
            return i2;
        }

        public void close() throws IOException {
            this.stream.close();
        }
    }

    public SoftResamplerStreamer openStreamer() {
        return new ModelAbstractResamplerStream();
    }
}
