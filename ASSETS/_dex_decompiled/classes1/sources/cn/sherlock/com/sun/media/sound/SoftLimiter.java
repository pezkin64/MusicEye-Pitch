package cn.sherlock.com.sun.media.sound;

public class SoftLimiter implements SoftAudioProcessor {
    SoftAudioBuffer bufferL;
    SoftAudioBuffer bufferLout;
    SoftAudioBuffer bufferR;
    SoftAudioBuffer bufferRout;
    float controlrate;
    float gain = 1.0f;
    float lastmax = 0.0f;
    boolean mix = false;
    double silentcounter = 0.0d;
    float[] temp_bufferL;
    float[] temp_bufferR;

    public void globalParameterControlChange(int[] iArr, long j, long j2) {
    }

    public void processControlLogic() {
    }

    public void init(float f, float f2) {
        this.controlrate = f2;
    }

    public void setInput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.bufferL = softAudioBuffer;
        }
        if (i == 1) {
            this.bufferR = softAudioBuffer;
        }
    }

    public void setOutput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.bufferLout = softAudioBuffer;
        }
        if (i == 1) {
            this.bufferRout = softAudioBuffer;
        }
    }

    public void setMixMode(boolean z) {
        this.mix = z;
    }

    public void processAudio() {
        float[] fArr;
        SoftAudioBuffer softAudioBuffer;
        float f = 1.0f;
        if (!this.bufferL.isSilent() || ((softAudioBuffer = this.bufferR) != null && !softAudioBuffer.isSilent())) {
            this.silentcounter = 0.0d;
        } else {
            double d = this.silentcounter + ((double) (1.0f / this.controlrate));
            this.silentcounter = d;
            if (d > 60.0d) {
                if (!this.mix) {
                    this.bufferLout.clear();
                    SoftAudioBuffer softAudioBuffer2 = this.bufferRout;
                    if (softAudioBuffer2 != null) {
                        softAudioBuffer2.clear();
                        return;
                    }
                    return;
                }
                return;
            }
        }
        float[] array = this.bufferL.array();
        SoftAudioBuffer softAudioBuffer3 = this.bufferR;
        float[] fArr2 = null;
        float[] array2 = softAudioBuffer3 == null ? null : softAudioBuffer3.array();
        float[] array3 = this.bufferLout.array();
        SoftAudioBuffer softAudioBuffer4 = this.bufferRout;
        if (softAudioBuffer4 != null) {
            fArr2 = softAudioBuffer4.array();
        }
        float[] fArr3 = this.temp_bufferL;
        if (fArr3 == null || fArr3.length < array.length) {
            this.temp_bufferL = new float[array.length];
        }
        if (array2 != null && ((fArr = this.temp_bufferR) == null || fArr.length < array2.length)) {
            this.temp_bufferR = new float[array2.length];
        }
        int i = 0;
        float f2 = 0.0f;
        if (array2 == null) {
            for (float f3 : array) {
                if (f3 > f2) {
                    f2 = f3;
                }
                if ((-f3) > f2) {
                    f2 = -f3;
                }
            }
        } else {
            for (int i2 = 0; i2 < r5; i2++) {
                float f4 = array[i2];
                if (f4 > f2) {
                    f2 = f4;
                }
                float f5 = array2[i2];
                if (f5 > f2) {
                    f2 = f5;
                }
                if ((-f4) > f2) {
                    f2 = -f4;
                }
                if ((-f5) > f2) {
                    f2 = -f5;
                }
            }
        }
        float f6 = this.lastmax;
        this.lastmax = f2;
        if (f6 > f2) {
            f2 = f6;
        }
        if (f2 > 0.99f) {
            f = 0.99f / f2;
        }
        float f7 = this.gain;
        if (f > f7) {
            f = (f + (9.0f * f7)) / 10.0f;
        }
        float f8 = (f - f7) / ((float) r5);
        if (this.mix) {
            if (array2 == null) {
                while (i < r5) {
                    float f9 = this.gain + f8;
                    this.gain = f9;
                    float f10 = array[i];
                    float[] fArr4 = this.temp_bufferL;
                    float f11 = fArr4[i];
                    fArr4[i] = f10;
                    array3[i] = array3[i] + (f11 * f9);
                    i++;
                }
            } else {
                while (i < r5) {
                    float f12 = this.gain + f8;
                    this.gain = f12;
                    float f13 = array[i];
                    float f14 = array2[i];
                    float[] fArr5 = this.temp_bufferL;
                    float f15 = fArr5[i];
                    float[] fArr6 = this.temp_bufferR;
                    float f16 = fArr6[i];
                    fArr5[i] = f13;
                    fArr6[i] = f14;
                    array3[i] = array3[i] + (f15 * f12);
                    fArr2[i] = fArr2[i] + (f16 * f12);
                    i++;
                }
            }
        } else if (array2 == null) {
            while (i < r5) {
                float f17 = this.gain + f8;
                this.gain = f17;
                float f18 = array[i];
                float[] fArr7 = this.temp_bufferL;
                float f19 = fArr7[i];
                fArr7[i] = f18;
                array3[i] = f19 * f17;
                i++;
            }
        } else {
            while (i < r5) {
                float f20 = this.gain + f8;
                this.gain = f20;
                float f21 = array[i];
                float f22 = array2[i];
                float[] fArr8 = this.temp_bufferL;
                float f23 = fArr8[i];
                float[] fArr9 = this.temp_bufferR;
                float f24 = fArr9[i];
                fArr8[i] = f21;
                fArr9[i] = f22;
                array3[i] = f23 * f20;
                fArr2[i] = f24 * f20;
                i++;
            }
        }
        this.gain = f;
    }
}
