package cn.sherlock.com.sun.media.sound;

import java.util.Arrays;

public class SoftChorus implements SoftAudioProcessor {
    private float controlrate;
    private boolean dirty = true;
    private double dirty_vdelay1L_depth;
    private float dirty_vdelay1L_feedback;
    private double dirty_vdelay1L_rate;
    private float dirty_vdelay1L_reverbsendgain;
    private double dirty_vdelay1R_depth;
    private float dirty_vdelay1R_feedback;
    private double dirty_vdelay1R_rate;
    private float dirty_vdelay1R_reverbsendgain;
    private SoftAudioBuffer inputA;
    private SoftAudioBuffer left;
    private boolean mix = true;
    private SoftAudioBuffer reverb;
    private float rgain = 0.0f;
    private SoftAudioBuffer right;
    double silentcounter = 1000.0d;
    private LFODelay vdelay1L;
    private LFODelay vdelay1R;

    private static class VariableDelay {
        private float delay = 0.0f;
        private float[] delaybuffer;
        private float feedback = 0.0f;
        private float gain = 1.0f;
        private float lastdelay = 0.0f;
        private float rgain = 0.0f;
        private int rovepos = 0;

        public VariableDelay(int i) {
            this.delaybuffer = new float[i];
        }

        public void setDelay(float f) {
            this.delay = f;
        }

        public void setFeedBack(float f) {
            this.feedback = f;
        }

        public void setGain(float f) {
            this.gain = f;
        }

        public void setReverbSendGain(float f) {
            this.rgain = f;
        }

        public void processMix(float[] fArr, float[] fArr2, float[] fArr3) {
            float[] fArr4 = fArr;
            float f = this.gain;
            float f2 = this.delay;
            float f3 = this.feedback;
            float[] fArr5 = this.delaybuffer;
            int length = fArr4.length;
            float f4 = (f2 - this.lastdelay) / ((float) length);
            int length2 = fArr5.length;
            int i = this.rovepos;
            int i2 = 0;
            if (fArr3 == null) {
                while (i2 < length) {
                    float f5 = this.lastdelay;
                    float f6 = (((float) i) - (f5 + 2.0f)) + ((float) length2);
                    int i3 = (int) f6;
                    float f7 = f6 - ((float) i3);
                    float f8 = (fArr5[i3 % length2] * (1.0f - f7)) + (fArr5[(i3 + 1) % length2] * f7);
                    fArr2[i2] = fArr2[i2] + (f8 * f);
                    fArr5[i] = fArr4[i2] + (f8 * f3);
                    i = (i + 1) % length2;
                    this.lastdelay = f5 + f4;
                    i2++;
                }
            } else {
                while (i2 < length) {
                    float f9 = this.lastdelay;
                    float f10 = (((float) i) - (f9 + 2.0f)) + ((float) length2);
                    int i4 = (int) f10;
                    float f11 = f10 - ((float) i4);
                    float f12 = (fArr5[i4 % length2] * (1.0f - f11)) + (fArr5[(i4 + 1) % length2] * f11);
                    fArr2[i2] = fArr2[i2] + (f12 * f);
                    fArr3[i2] = fArr3[i2] + (this.rgain * f12);
                    fArr5[i] = fArr4[i2] + (f12 * f3);
                    i = (i + 1) % length2;
                    this.lastdelay = f9 + f4;
                    i2++;
                }
            }
            this.rovepos = i;
            this.lastdelay = f2;
        }

        public void processReplace(float[] fArr, float[] fArr2, float[] fArr3) {
            Arrays.fill(fArr2, 0.0f);
            Arrays.fill(fArr3, 0.0f);
            processMix(fArr, fArr2, fArr3);
        }
    }

    private static class LFODelay {
        private double controlrate;
        private double depth = 0.0d;
        private double phase = 1.0d;
        private double phase_step = 0.0d;
        private double samplerate;
        private VariableDelay vdelay;

        public LFODelay(double d, double d2) {
            this.samplerate = d;
            this.controlrate = d2;
            this.vdelay = new VariableDelay((int) ((this.depth + 10.0d) * 2.0d));
        }

        public void setDepth(double d) {
            this.depth = d * this.samplerate;
            this.vdelay = new VariableDelay((int) ((this.depth + 10.0d) * 2.0d));
        }

        public void setRate(double d) {
            this.phase_step = (d / this.controlrate) * 6.283185307179586d;
        }

        public void setPhase(double d) {
            this.phase = d;
        }

        public void setFeedBack(float f) {
            this.vdelay.setFeedBack(f);
        }

        public void setGain(float f) {
            this.vdelay.setGain(f);
        }

        public void setReverbSendGain(float f) {
            this.vdelay.setReverbSendGain(f);
        }

        public void processMix(float[] fArr, float[] fArr2, float[] fArr3) {
            this.phase += this.phase_step;
            while (true) {
                double d = this.phase;
                if (d > 6.283185307179586d) {
                    this.phase = d - 6.283185307179586d;
                } else {
                    this.vdelay.setDelay((float) (this.depth * 0.5d * (Math.cos(d) + 2.0d)));
                    this.vdelay.processMix(fArr, fArr2, fArr3);
                    return;
                }
            }
        }

        public void processReplace(float[] fArr, float[] fArr2, float[] fArr3) {
            this.phase += this.phase_step;
            while (true) {
                double d = this.phase;
                if (d > 6.283185307179586d) {
                    this.phase = d - 6.283185307179586d;
                } else {
                    this.vdelay.setDelay((float) (this.depth * 0.5d * (Math.cos(d) + 2.0d)));
                    this.vdelay.processReplace(fArr, fArr2, fArr3);
                    return;
                }
            }
        }
    }

    public void init(float f, float f2) {
        this.controlrate = f2;
        double d = (double) f;
        double d2 = (double) f2;
        this.vdelay1L = new LFODelay(d, d2);
        this.vdelay1R = new LFODelay(d, d2);
        this.vdelay1L.setGain(1.0f);
        this.vdelay1R.setGain(1.0f);
        this.vdelay1L.setPhase(1.5707963267948966d);
        this.vdelay1R.setPhase(0.0d);
        globalParameterControlChange(new int[]{130}, 0, 2);
    }

    public void globalParameterControlChange(int[] iArr, long j, long j2) {
        if (iArr.length == 1 && iArr[0] == 130) {
            if (j == 0) {
                int i = (int) j2;
                if (i == 0) {
                    int[] iArr2 = iArr;
                    globalParameterControlChange(iArr2, 3, 0);
                    globalParameterControlChange(iArr2, 1, 3);
                    globalParameterControlChange(iArr2, 2, 5);
                    globalParameterControlChange(iArr2, 4, 0);
                } else if (i == 1) {
                    int[] iArr3 = iArr;
                    globalParameterControlChange(iArr3, 3, 5);
                    globalParameterControlChange(iArr3, 1, 9);
                    globalParameterControlChange(iArr3, 2, 19);
                    globalParameterControlChange(iArr3, 4, 0);
                } else if (i == 2) {
                    int[] iArr4 = iArr;
                    globalParameterControlChange(iArr4, 3, 8);
                    globalParameterControlChange(iArr4, 1, 3);
                    globalParameterControlChange(iArr4, 2, 19);
                    globalParameterControlChange(iArr4, 4, 0);
                } else if (i == 3) {
                    int[] iArr5 = iArr;
                    globalParameterControlChange(iArr5, 3, 16);
                    globalParameterControlChange(iArr5, 1, 9);
                    globalParameterControlChange(iArr5, 2, 16);
                    globalParameterControlChange(iArr5, 4, 0);
                } else if (i == 4) {
                    int[] iArr6 = iArr;
                    globalParameterControlChange(iArr6, 3, 64);
                    globalParameterControlChange(iArr6, 1, 2);
                    globalParameterControlChange(iArr6, 2, 24);
                    globalParameterControlChange(iArr6, 4, 0);
                } else if (i == 5) {
                    int[] iArr7 = iArr;
                    globalParameterControlChange(iArr7, 3, 112);
                    globalParameterControlChange(iArr7, 1, 1);
                    globalParameterControlChange(iArr7, 2, 5);
                    globalParameterControlChange(iArr7, 4, 0);
                }
            } else if (j == 1) {
                double d = ((double) j2) * 0.122d;
                this.dirty_vdelay1L_rate = d;
                this.dirty_vdelay1R_rate = d;
                this.dirty = true;
            } else if (j == 2) {
                double d2 = ((double) (1 + j2)) / 3200.0d;
                this.dirty_vdelay1L_depth = d2;
                this.dirty_vdelay1R_depth = d2;
                this.dirty = true;
            } else if (j == 3) {
                float f = ((float) j2) * 0.00763f;
                this.dirty_vdelay1L_feedback = f;
                this.dirty_vdelay1R_feedback = f;
                this.dirty = true;
            }
            if (j == 4) {
                float f2 = ((float) j2) * 0.00787f;
                this.rgain = f2;
                this.dirty_vdelay1L_reverbsendgain = f2;
                this.dirty_vdelay1R_reverbsendgain = f2;
                this.dirty = true;
            }
        }
    }

    public void processControlLogic() {
        if (this.dirty) {
            this.dirty = false;
            this.vdelay1L.setRate(this.dirty_vdelay1L_rate);
            this.vdelay1R.setRate(this.dirty_vdelay1R_rate);
            this.vdelay1L.setDepth(this.dirty_vdelay1L_depth);
            this.vdelay1R.setDepth(this.dirty_vdelay1R_depth);
            this.vdelay1L.setFeedBack(this.dirty_vdelay1L_feedback);
            this.vdelay1R.setFeedBack(this.dirty_vdelay1R_feedback);
            this.vdelay1L.setReverbSendGain(this.dirty_vdelay1L_reverbsendgain);
            this.vdelay1R.setReverbSendGain(this.dirty_vdelay1R_reverbsendgain);
        }
    }

    public void processAudio() {
        if (this.inputA.isSilent()) {
            double d = this.silentcounter + ((double) (1.0f / this.controlrate));
            this.silentcounter = d;
            if (d > 1.0d) {
                if (!this.mix) {
                    this.left.clear();
                    this.right.clear();
                    return;
                }
                return;
            }
        } else {
            this.silentcounter = 0.0d;
        }
        float[] array = this.inputA.array();
        float[] array2 = this.left.array();
        SoftAudioBuffer softAudioBuffer = this.right;
        float[] fArr = null;
        float[] array3 = softAudioBuffer == null ? null : softAudioBuffer.array();
        if (this.rgain != 0.0f) {
            fArr = this.reverb.array();
        }
        if (this.mix) {
            this.vdelay1L.processMix(array, array2, fArr);
            if (array3 != null) {
                this.vdelay1R.processMix(array, array3, fArr);
                return;
            }
            return;
        }
        this.vdelay1L.processReplace(array, array2, fArr);
        if (array3 != null) {
            this.vdelay1R.processReplace(array, array3, fArr);
        }
    }

    public void setInput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.inputA = softAudioBuffer;
        }
    }

    public void setMixMode(boolean z) {
        this.mix = z;
    }

    public void setOutput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.left = softAudioBuffer;
        }
        if (i == 1) {
            this.right = softAudioBuffer;
        }
        if (i == 2) {
            this.reverb = softAudioBuffer;
        }
    }
}
