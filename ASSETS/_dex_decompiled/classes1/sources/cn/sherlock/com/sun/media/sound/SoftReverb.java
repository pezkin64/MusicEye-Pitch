package cn.sherlock.com.sun.media.sound;

import java.util.Arrays;

public class SoftReverb implements SoftAudioProcessor {
    private AllPass[] allpassL;
    private AllPass[] allpassR;
    private Comb[] combL;
    private Comb[] combR;
    private float damp;
    private Delay delay;
    private boolean denormal_flip = false;
    private boolean dirty = true;
    private float dirty_damp;
    private float dirty_gain;
    private float dirty_predelay;
    private float dirty_roomsize;
    private float gain = 1.0f;
    private float[] input;
    private SoftAudioBuffer inputA;
    private SoftAudioBuffer left;
    private boolean light = true;
    private boolean mix = true;
    private float[] out;
    private float[] pre1;
    private float[] pre2;
    private float[] pre3;
    private SoftAudioBuffer right;
    private float roomsize;
    private float samplerate;
    private boolean silent = true;

    private static final class Delay {
        private float[] delaybuffer = null;
        private int rovepos = 0;

        public void setDelay(int i) {
            if (i == 0) {
                this.delaybuffer = null;
            } else {
                this.delaybuffer = new float[i];
            }
            this.rovepos = 0;
        }

        public void processReplace(float[] fArr) {
            float[] fArr2 = this.delaybuffer;
            if (fArr2 != null) {
                int length = fArr.length;
                int length2 = fArr2.length;
                int i = this.rovepos;
                for (int i2 = 0; i2 < length; i2++) {
                    float f = fArr[i2];
                    float[] fArr3 = this.delaybuffer;
                    fArr[i2] = fArr3[i];
                    fArr3[i] = f;
                    i++;
                    if (i == length2) {
                        i = 0;
                    }
                }
                this.rovepos = i;
            }
        }
    }

    private static final class AllPass {
        private final float[] delaybuffer;
        private final int delaybuffersize;
        private float feedback;
        private int rovepos = 0;

        public AllPass(int i) {
            this.delaybuffer = new float[i];
            this.delaybuffersize = i;
        }

        public void setFeedBack(float f) {
            this.feedback = f;
        }

        public void processReplace(float[] fArr) {
            int length = fArr.length;
            int i = this.delaybuffersize;
            int i2 = this.rovepos;
            for (int i3 = 0; i3 < length; i3++) {
                float[] fArr2 = this.delaybuffer;
                float f = fArr2[i2];
                float f2 = fArr[i3];
                fArr[i3] = f - f2;
                fArr2[i2] = f2 + (f * this.feedback);
                i2++;
                if (i2 == i) {
                    i2 = 0;
                }
            }
            this.rovepos = i2;
        }

        public void processReplace(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int i = this.delaybuffersize;
            int i2 = this.rovepos;
            for (int i3 = 0; i3 < length; i3++) {
                float[] fArr3 = this.delaybuffer;
                float f = fArr3[i2];
                float f2 = fArr[i3];
                fArr2[i3] = f - f2;
                fArr3[i2] = f2 + (f * this.feedback);
                i2++;
                if (i2 == i) {
                    i2 = 0;
                }
            }
            this.rovepos = i2;
        }
    }

    private static final class Comb {
        private final float[] delaybuffer;
        private final int delaybuffersize;
        /* access modifiers changed from: private */
        public float feedback;
        private float filtercoeff1 = 0.0f;
        private float filtercoeff2 = 1.0f;
        private float filtertemp = 0.0f;
        private int rovepos = 0;

        public Comb(int i) {
            this.delaybuffer = new float[i];
            this.delaybuffersize = i;
        }

        public void setFeedBack(float f) {
            this.feedback = f;
            this.filtercoeff2 = (1.0f - this.filtercoeff1) * f;
        }

        public void processMix(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int i = this.delaybuffersize;
            int i2 = this.rovepos;
            float f = this.filtertemp;
            float f2 = this.filtercoeff1;
            float f3 = this.filtercoeff2;
            for (int i3 = 0; i3 < length; i3++) {
                float[] fArr3 = this.delaybuffer;
                float f4 = fArr3[i2];
                f = (f * f2) + (f4 * f3);
                fArr2[i3] = fArr2[i3] + f4;
                fArr3[i2] = fArr[i3] + f;
                i2++;
                if (i2 == i) {
                    i2 = 0;
                }
            }
            this.filtertemp = f;
            this.rovepos = i2;
        }

        public void processReplace(float[] fArr, float[] fArr2) {
            int length = fArr.length;
            int i = this.delaybuffersize;
            int i2 = this.rovepos;
            float f = this.filtertemp;
            float f2 = this.filtercoeff1;
            float f3 = this.filtercoeff2;
            for (int i3 = 0; i3 < length; i3++) {
                float[] fArr3 = this.delaybuffer;
                float f4 = fArr3[i2];
                f = (f * f2) + (f4 * f3);
                fArr2[i3] = f4;
                fArr3[i2] = fArr[i3] + f;
                i2++;
                if (i2 == i) {
                    i2 = 0;
                }
            }
            this.filtertemp = f;
            this.rovepos = i2;
        }

        public void setDamp(float f) {
            this.filtercoeff1 = f;
            this.filtercoeff2 = (1.0f - f) * this.feedback;
        }
    }

    public void init(float f, float f2) {
        this.samplerate = f;
        double d = ((double) f) / 44100.0d;
        this.delay = new Delay();
        Comb[] combArr = new Comb[8];
        this.combL = combArr;
        this.combR = new Comb[8];
        int i = 0;
        combArr[0] = new Comb((int) (1116.0d * d));
        this.combR[0] = new Comb((int) (((double) 1139) * d));
        this.combL[1] = new Comb((int) (1188.0d * d));
        this.combR[1] = new Comb((int) (((double) 1211) * d));
        this.combL[2] = new Comb((int) (1277.0d * d));
        this.combR[2] = new Comb((int) (((double) 1300) * d));
        this.combL[3] = new Comb((int) (1356.0d * d));
        this.combR[3] = new Comb((int) (((double) 1379) * d));
        this.combL[4] = new Comb((int) (1422.0d * d));
        this.combR[4] = new Comb((int) (((double) 1445) * d));
        this.combL[5] = new Comb((int) (1491.0d * d));
        this.combR[5] = new Comb((int) (((double) 1514) * d));
        this.combL[6] = new Comb((int) (1557.0d * d));
        this.combR[6] = new Comb((int) (((double) 1580) * d));
        this.combL[7] = new Comb((int) (1617.0d * d));
        this.combR[7] = new Comb((int) (((double) 1640) * d));
        AllPass[] allPassArr = new AllPass[4];
        this.allpassL = allPassArr;
        this.allpassR = new AllPass[4];
        allPassArr[0] = new AllPass((int) (556.0d * d));
        this.allpassR[0] = new AllPass((int) (((double) 579) * d));
        this.allpassL[1] = new AllPass((int) (441.0d * d));
        this.allpassR[1] = new AllPass((int) (((double) 464) * d));
        this.allpassL[2] = new AllPass((int) (341.0d * d));
        this.allpassR[2] = new AllPass((int) (((double) 364) * d));
        this.allpassL[3] = new AllPass((int) (225.0d * d));
        this.allpassR[3] = new AllPass((int) (d * ((double) 248)));
        while (true) {
            AllPass[] allPassArr2 = this.allpassL;
            if (i < allPassArr2.length) {
                allPassArr2[i].setFeedBack(0.5f);
                this.allpassR[i].setFeedBack(0.5f);
                i++;
            } else {
                globalParameterControlChange(new int[]{129}, 0, 4);
                return;
            }
        }
    }

    public void setInput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.inputA = softAudioBuffer;
        }
    }

    public void setOutput(int i, SoftAudioBuffer softAudioBuffer) {
        if (i == 0) {
            this.left = softAudioBuffer;
        }
        if (i == 1) {
            this.right = softAudioBuffer;
        }
    }

    public void setMixMode(boolean z) {
        this.mix = z;
    }

    public void processAudio() {
        Comb[] combArr;
        boolean isSilent = this.inputA.isSilent();
        if (!isSilent) {
            this.silent = false;
        }
        if (!this.silent) {
            float[] array = this.inputA.array();
            float[] array2 = this.left.array();
            SoftAudioBuffer softAudioBuffer = this.right;
            float[] array3 = softAudioBuffer == null ? null : softAudioBuffer.array();
            int length = array.length;
            float[] fArr = this.input;
            if (fArr == null || fArr.length < length) {
                this.input = new float[length];
            }
            float f = (this.gain * 0.018f) / 2.0f;
            boolean z = this.denormal_flip;
            this.denormal_flip = !z;
            if (!z) {
                for (int i = 0; i < length; i++) {
                    this.input[i] = (array[i] * f) + 1.0E-20f;
                }
            } else {
                for (int i2 = 0; i2 < length; i2++) {
                    this.input[i2] = (array[i2] * f) - 1.0E-20f;
                }
            }
            this.delay.processReplace(this.input);
            if (!this.light || array3 == null) {
                float[] fArr2 = this.out;
                if (fArr2 == null || fArr2.length < length) {
                    this.out = new float[length];
                }
                if (array3 != null) {
                    if (!this.mix) {
                        Arrays.fill(array3, 0.0f);
                    }
                    this.allpassR[0].processReplace(this.input, this.out);
                    int i3 = 1;
                    while (true) {
                        AllPass[] allPassArr = this.allpassR;
                        if (i3 >= allPassArr.length) {
                            break;
                        }
                        allPassArr[i3].processReplace(this.out);
                        i3++;
                    }
                    int i4 = 0;
                    while (true) {
                        Comb[] combArr2 = this.combR;
                        if (i4 >= combArr2.length) {
                            break;
                        }
                        combArr2[i4].processMix(this.out, array3);
                        i4++;
                    }
                }
                if (!this.mix) {
                    Arrays.fill(array2, 0.0f);
                }
                this.allpassL[0].processReplace(this.input, this.out);
                int i5 = 1;
                while (true) {
                    AllPass[] allPassArr2 = this.allpassL;
                    if (i5 >= allPassArr2.length) {
                        break;
                    }
                    allPassArr2[i5].processReplace(this.out);
                    i5++;
                }
                int i6 = 0;
                while (true) {
                    Comb[] combArr3 = this.combL;
                    if (i6 >= combArr3.length) {
                        break;
                    }
                    combArr3[i6].processMix(this.out, array2);
                    i6++;
                }
            } else {
                float[] fArr3 = this.pre1;
                if (fArr3 == null || fArr3.length < length) {
                    this.pre1 = new float[length];
                    this.pre2 = new float[length];
                    this.pre3 = new float[length];
                }
                int i7 = 0;
                while (true) {
                    AllPass[] allPassArr3 = this.allpassL;
                    if (i7 >= allPassArr3.length) {
                        break;
                    }
                    allPassArr3[i7].processReplace(this.input);
                    i7++;
                }
                this.combL[0].processReplace(this.input, this.pre3);
                this.combL[1].processReplace(this.input, this.pre3);
                this.combL[2].processReplace(this.input, this.pre1);
                int i8 = 4;
                while (true) {
                    combArr = this.combL;
                    if (i8 >= combArr.length - 2) {
                        break;
                    }
                    combArr[i8].processMix(this.input, this.pre1);
                    i8 += 2;
                }
                combArr[3].processReplace(this.input, this.pre2);
                int i9 = 5;
                while (true) {
                    Comb[] combArr4 = this.combL;
                    if (i9 >= combArr4.length - 2) {
                        break;
                    }
                    combArr4[i9].processMix(this.input, this.pre2);
                    i9 += 2;
                }
                if (!this.mix) {
                    Arrays.fill(array3, 0.0f);
                    Arrays.fill(array2, 0.0f);
                }
                int length2 = this.combR.length - 2;
                while (true) {
                    Comb[] combArr5 = this.combR;
                    if (length2 >= combArr5.length) {
                        break;
                    }
                    combArr5[length2].processMix(this.input, array3);
                    length2++;
                }
                int length3 = this.combL.length - 2;
                while (true) {
                    Comb[] combArr6 = this.combL;
                    if (length3 >= combArr6.length) {
                        break;
                    }
                    combArr6[length3].processMix(this.input, array2);
                    length3++;
                }
                for (int i10 = 0; i10 < length; i10++) {
                    float f2 = this.pre1[i10] - this.pre2[i10];
                    float f3 = this.pre3[i10];
                    array2[i10] = array2[i10] + f3 + f2;
                    array3[i10] = array3[i10] + (f3 - f2);
                }
            }
            if (isSilent) {
                this.silent = true;
                for (int i11 = 0; i11 < length; i11++) {
                    double d = (double) array2[i11];
                    if (d > 1.0E-10d || d < -1.0E-10d) {
                        this.silent = false;
                        return;
                    }
                }
            }
        } else if (!this.mix) {
            this.left.clear();
            this.right.clear();
        }
    }

    public void globalParameterControlChange(int[] iArr, long j, long j2) {
        if (iArr.length != 1 || iArr[0] != 129) {
            return;
        }
        if (j == 0) {
            if (j2 == 0) {
                this.dirty_roomsize = 1.1f;
                this.dirty_damp = 5000.0f;
                this.dirty_predelay = 0.0f;
                this.dirty_gain = 4.0f;
                this.dirty = true;
            }
            if (j2 == 1) {
                this.dirty_roomsize = 1.3f;
                this.dirty_damp = 5000.0f;
                this.dirty_predelay = 0.0f;
                this.dirty_gain = 3.0f;
                this.dirty = true;
            }
            if (j2 == 2) {
                this.dirty_roomsize = 1.5f;
                this.dirty_damp = 5000.0f;
                this.dirty_predelay = 0.0f;
                this.dirty_gain = 2.0f;
                this.dirty = true;
            }
            if (j2 == 3) {
                this.dirty_roomsize = 1.8f;
                this.dirty_damp = 24000.0f;
                this.dirty_predelay = 0.02f;
                this.dirty_gain = 1.5f;
                this.dirty = true;
            }
            if (j2 == 4) {
                this.dirty_roomsize = 1.8f;
                this.dirty_damp = 24000.0f;
                this.dirty_predelay = 0.03f;
                this.dirty_gain = 1.5f;
                this.dirty = true;
            }
            if (j2 == 8) {
                this.dirty_roomsize = 1.3f;
                this.dirty_damp = 2500.0f;
                this.dirty_predelay = 0.0f;
                this.dirty_gain = 6.0f;
                this.dirty = true;
            }
        } else if (j == 1) {
            this.dirty_roomsize = (float) Math.exp(((double) (j2 - 40)) * 0.025d);
            this.dirty = true;
        }
    }

    public void processControlLogic() {
        if (this.dirty) {
            this.dirty = false;
            setRoomSize(this.dirty_roomsize);
            setDamp(this.dirty_damp);
            setPreDelay(this.dirty_predelay);
            setGain(this.dirty_gain);
        }
    }

    public void setRoomSize(float f) {
        this.roomsize = 1.0f - (0.17f / f);
        int i = 0;
        while (true) {
            Comb[] combArr = this.combL;
            if (i < combArr.length) {
                float unused = combArr[i].feedback = this.roomsize;
                float unused2 = this.combR[i].feedback = this.roomsize;
                i++;
            } else {
                return;
            }
        }
    }

    public void setPreDelay(float f) {
        this.delay.setDelay((int) (f * this.samplerate));
    }

    public void setGain(float f) {
        this.gain = f;
    }

    public void setDamp(float f) {
        double cos = 2.0d - Math.cos(((double) (f / this.samplerate)) * 6.283185307179586d);
        float sqrt = (float) (cos - Math.sqrt((cos * cos) - 1.0d));
        this.damp = sqrt;
        if (sqrt > 1.0f) {
            this.damp = 1.0f;
        }
        if (this.damp < 0.0f) {
            this.damp = 0.0f;
        }
        int i = 0;
        while (true) {
            Comb[] combArr = this.combL;
            if (i < combArr.length) {
                combArr[i].setDamp(this.damp);
                this.combR[i].setDamp(this.damp);
                i++;
            } else {
                return;
            }
        }
    }

    public void setLightMode(boolean z) {
        this.light = z;
    }
}
