package cn.sherlock.com.sun.media.sound;

public class SoftFilter {
    public static final int FILTERTYPE_BP12 = 33;
    public static final int FILTERTYPE_HP12 = 17;
    public static final int FILTERTYPE_HP24 = 19;
    public static final int FILTERTYPE_LP12 = 1;
    public static final int FILTERTYPE_LP24 = 3;
    public static final int FILTERTYPE_LP6 = 0;
    public static final int FILTERTYPE_NP12 = 49;
    private float a0;
    private float a1;
    private float a2;
    private float b1;
    private float b2;
    private double cutoff = 44100.0d;
    private boolean dirty;
    private int filtertype = 0;
    private float gain = 1.0f;
    private float last_a0;
    private float last_a1;
    private float last_a2;
    private float last_b1;
    private float last_b2;
    private float last_gain;
    private float last_q;
    private boolean last_set = false;
    private float last_wet = 0.0f;
    private float q;
    private double resonancedB = 0.0d;
    private float samplerate;
    private float wet = 0.0f;
    private float x1;
    private float x2;
    private float xx1;
    private float xx2;
    private float y1;
    private float y2;
    private float yy1;
    private float yy2;

    public SoftFilter(float f) {
        this.samplerate = f;
        this.dirty = true;
    }

    public void setFrequency(double d) {
        if (this.cutoff != d) {
            this.cutoff = d;
            this.dirty = true;
        }
    }

    public void setResonance(double d) {
        if (this.resonancedB != d) {
            this.resonancedB = d;
            this.dirty = true;
        }
    }

    public void reset() {
        this.dirty = true;
        this.last_set = false;
        this.x1 = 0.0f;
        this.x2 = 0.0f;
        this.y1 = 0.0f;
        this.y2 = 0.0f;
        this.xx1 = 0.0f;
        this.xx2 = 0.0f;
        this.yy1 = 0.0f;
        this.yy2 = 0.0f;
        this.wet = 0.0f;
        this.gain = 1.0f;
        this.a0 = 0.0f;
        this.a1 = 0.0f;
        this.a2 = 0.0f;
        this.b1 = 0.0f;
        this.b2 = 0.0f;
    }

    public void setFilterType(int i) {
        this.filtertype = i;
    }

    public void processAudio(SoftAudioBuffer softAudioBuffer) {
        if (this.filtertype == 0) {
            filter1(softAudioBuffer);
        }
        if (this.filtertype == 1) {
            filter2(softAudioBuffer);
        }
        if (this.filtertype == 17) {
            filter2(softAudioBuffer);
        }
        if (this.filtertype == 33) {
            filter2(softAudioBuffer);
        }
        if (this.filtertype == 49) {
            filter2(softAudioBuffer);
        }
        if (this.filtertype == 3) {
            filter4(softAudioBuffer);
        }
        if (this.filtertype == 19) {
            filter4(softAudioBuffer);
        }
    }

    public void filter4(SoftAudioBuffer softAudioBuffer) {
        float f;
        float f2;
        float f3;
        float[] array = softAudioBuffer.array();
        if (this.dirty) {
            filter2calc();
            this.dirty = false;
        }
        if (!this.last_set) {
            this.last_a0 = this.a0;
            this.last_a1 = this.a1;
            this.last_a2 = this.a2;
            this.last_b1 = this.b1;
            this.last_b2 = this.b2;
            this.last_gain = this.gain;
            this.last_wet = this.wet;
            this.last_set = true;
        }
        float f4 = this.wet;
        if (f4 > 0.0f || this.last_wet > 0.0f) {
            int length = array.length;
            float f5 = this.last_a0;
            float f6 = this.last_a1;
            float f7 = this.last_a2;
            float f8 = this.last_b1;
            float f9 = this.last_b2;
            float f10 = this.last_gain;
            float f11 = this.last_wet;
            float f12 = (float) length;
            float f13 = (this.a0 - f5) / f12;
            float f14 = (this.a1 - f6) / f12;
            float f15 = (this.a2 - f7) / f12;
            float f16 = (this.b1 - f8) / f12;
            float[] fArr = array;
            float f17 = (this.b2 - f9) / f12;
            float f18 = (this.gain - f10) / f12;
            float f19 = (f4 - f11) / f12;
            float f20 = this.x1;
            float f21 = f18;
            float f22 = this.x2;
            float f23 = this.y1;
            float f24 = this.y2;
            float f25 = this.xx1;
            float f26 = this.xx2;
            float f27 = this.yy1;
            float f28 = this.yy2;
            if (f19 != 0.0f) {
                float f29 = f5;
                f2 = f28;
                f3 = f22;
                float f30 = f11;
                float f31 = f9;
                float f32 = f7;
                float f33 = f29;
                f = f20;
                float f34 = f10;
                float f35 = f8;
                float f36 = f6;
                int i = 0;
                while (i < length) {
                    f33 += f13;
                    f36 += f14;
                    f32 += f15;
                    f35 += f16;
                    f31 += f17;
                    f34 += f21;
                    f30 += f19;
                    float f37 = fArr[i];
                    float f38 = ((((f33 * f37) + (f36 * f)) + (f3 * f32)) - (f35 * f23)) - (f24 * f31);
                    float f39 = 1.0f - f30;
                    float f40 = (f38 * f34 * f30) + (f37 * f39);
                    float f41 = ((((f33 * f40) + (f36 * f25)) + (f26 * f32)) - (f35 * f27)) - (f2 * f31);
                    fArr[i] = (f41 * f34 * f30) + (f39 * f40);
                    i++;
                    float f42 = f27;
                    f27 = f41;
                    f2 = f42;
                    f26 = f25;
                    f25 = f40;
                    f24 = f23;
                    f23 = f38;
                    f3 = f;
                    f = f37;
                }
            } else if (f13 == 0.0f && f14 == 0.0f && f15 == 0.0f && f16 == 0.0f && f17 == 0.0f) {
                float f43 = f28;
                float f44 = f22;
                int i2 = 0;
                while (i2 < length) {
                    float f45 = fArr[i2];
                    float f46 = ((((f5 * f45) + (f6 * f20)) + (f3 * f7)) - (f8 * f23)) - (f24 * f9);
                    float f47 = 1.0f - f11;
                    float f48 = (f46 * f10 * f11) + (f45 * f47);
                    float f49 = ((((f5 * f48) + (f6 * f25)) + (f26 * f7)) - (f8 * f27)) - (f43 * f9);
                    fArr[i2] = (f49 * f10 * f11) + (f47 * f48);
                    i2++;
                    float f50 = f27;
                    f27 = f49;
                    f43 = f50;
                    f24 = f23;
                    f26 = f25;
                    f23 = f46;
                    f25 = f48;
                    f44 = f20;
                    f20 = f45;
                }
                f2 = f43;
                f = f20;
            } else {
                float f51 = f28;
                float f52 = f22;
                float f53 = f20;
                float f54 = f10;
                float f55 = f9;
                float f56 = f8;
                float f57 = f7;
                float f58 = f6;
                float f59 = f5;
                int i3 = 0;
                while (i3 < length) {
                    f59 += f13;
                    f58 += f14;
                    f57 += f15;
                    f56 += f16;
                    f55 += f17;
                    f54 += f21;
                    float f60 = fArr[i3];
                    float f61 = ((((f59 * f60) + (f58 * f53)) + (f3 * f57)) - (f56 * f23)) - (f24 * f55);
                    float f62 = 1.0f - f11;
                    float f63 = (f61 * f54 * f11) + (f60 * f62);
                    float f64 = ((((f59 * f63) + (f58 * f25)) + (f26 * f57)) - (f56 * f27)) - (f51 * f55);
                    fArr[i3] = (f64 * f54 * f11) + (f62 * f63);
                    i3++;
                    float f65 = f27;
                    f27 = f64;
                    f51 = f65;
                    f26 = f25;
                    f25 = f63;
                    f24 = f23;
                    f23 = f61;
                    f52 = f53;
                    f53 = f60;
                }
                f2 = f51;
                f = f53;
            }
            float f66 = f25;
            float f67 = f26;
            float f68 = f27;
            float f69 = ((double) Math.abs(f)) < 1.0E-8d ? 0.0f : f;
            if (((double) Math.abs(f3)) < 1.0E-8d) {
                f3 = 0.0f;
            }
            float f70 = ((double) Math.abs(f23)) < 1.0E-8d ? 0.0f : f23;
            float f71 = ((double) Math.abs(f24)) < 1.0E-8d ? 0.0f : f24;
            this.x1 = f69;
            this.x2 = f3;
            this.y1 = f70;
            this.y2 = f71;
            this.xx1 = f66;
            this.xx2 = f67;
            this.yy1 = f68;
            this.yy2 = f2;
        }
        this.last_a0 = this.a0;
        this.last_a1 = this.a1;
        this.last_a2 = this.a2;
        this.last_b1 = this.b1;
        this.last_b2 = this.b2;
        this.last_gain = this.gain;
        this.last_wet = this.wet;
    }

    private double sinh(double d) {
        return (Math.exp(d) - Math.exp(-d)) * 0.5d;
    }

    public void filter2calc() {
        double d;
        double d2;
        double d3 = this.resonancedB;
        if (d3 < 0.0d) {
            d3 = 0.0d;
        }
        if (d3 > 30.0d) {
            d3 = 30.0d;
        }
        int i = this.filtertype;
        if (i == 3 || i == 19) {
            d3 *= 0.6d;
        }
        double d4 = 0.45d;
        if (i == 33) {
            this.wet = 1.0f;
            d2 = -2.0d;
            d = 20.0d;
            double d5 = this.cutoff / ((double) this.samplerate);
            if (d5 > 0.45d) {
                d5 = 0.45d;
            }
            double d6 = d5 * 6.283185307179586d;
            double cos = Math.cos(d6);
            double sin = Math.sin(d6);
            double sinh = sinh(((Math.log(2.0d) * (Math.pow(10.0d, -(d3 / 20.0d)) * 3.141592653589793d)) * d6) / (sin * 2.0d)) * sin;
            double d7 = 1.0d / (sinh + 1.0d);
            this.b1 = (float) (cos * -2.0d * d7);
            this.b2 = (float) ((1.0d - sinh) * d7);
            this.a0 = (float) (sinh * d7);
            this.a1 = (float) (d7 * 0.0d);
            this.a2 = (float) ((-sinh) * d7);
        } else {
            d2 = -2.0d;
            d = 20.0d;
        }
        if (this.filtertype == 49) {
            this.wet = 1.0f;
            double d8 = this.cutoff / ((double) this.samplerate);
            if (d8 > 0.45d) {
                d8 = 0.45d;
            }
            double d9 = d8 * 6.283185307179586d;
            double cos2 = Math.cos(d9);
            double sin2 = Math.sin(d9);
            double sinh2 = sin2 * sinh(((Math.log(2.0d) * (Math.pow(10.0d, -(d3 / d)) * 3.141592653589793d)) * d9) / (sin2 * 2.0d));
            double d10 = 1.0d / (sinh2 + 1.0d);
            float f = (float) (cos2 * d2 * d10);
            this.b1 = f;
            this.b2 = (float) ((1.0d - sinh2) * d10);
            float f2 = (float) (d10 * 1.0d);
            this.a0 = f2;
            this.a1 = f;
            this.a2 = f2;
        }
        int i2 = this.filtertype;
        if (i2 == 1 || i2 == 3) {
            double d11 = this.cutoff / ((double) this.samplerate);
            if (d11 > 0.45d) {
                if (this.wet == 0.0f) {
                    if (d3 < 1.0E-5d) {
                        this.wet = 0.0f;
                    } else {
                        this.wet = 1.0f;
                    }
                }
                d11 = 0.45d;
            } else {
                this.wet = 1.0f;
            }
            double tan = 1.0d / Math.tan(d11 * 3.141592653589793d);
            double d12 = tan * tan;
            double sqrt = Math.sqrt(2.0d) * Math.pow(10.0d, -(d3 / d)) * tan;
            double d13 = 1.0d / ((sqrt + 1.0d) + d12);
            double d14 = d13 * 2.0d;
            double d15 = ((1.0d - sqrt) + d12) * d13;
            float f3 = (float) d13;
            this.a0 = f3;
            this.a1 = (float) d14;
            this.a2 = f3;
            this.b1 = (float) (d14 * (1.0d - d12));
            this.b2 = (float) d15;
        }
        int i3 = this.filtertype;
        if (i3 == 17 || i3 == 19) {
            double d16 = this.cutoff / ((double) this.samplerate);
            if (d16 <= 0.45d) {
                d4 = d16;
            }
            if (d4 < 1.0E-4d) {
                d4 = 1.0E-4d;
            }
            this.wet = 1.0f;
            double tan2 = Math.tan(d4 * 3.141592653589793d);
            double d17 = tan2 * tan2;
            double sqrt2 = Math.sqrt(2.0d) * Math.pow(10.0d, -(d3 / d)) * tan2;
            double d18 = 1.0d / ((sqrt2 + 1.0d) + d17);
            double d19 = d18 * d2;
            double d20 = d17 - 1.0d;
            double d21 = d18 * ((1.0d - sqrt2) + d17);
            float f4 = (float) d18;
            this.a0 = f4;
            this.a1 = (float) d19;
            this.a2 = f4;
            this.b1 = (float) (d20 * 2.0d * d18);
            this.b2 = (float) d21;
        }
    }

    public void filter2(SoftAudioBuffer softAudioBuffer) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float[] array = softAudioBuffer.array();
        if (this.dirty) {
            filter2calc();
            this.dirty = false;
        }
        if (!this.last_set) {
            this.last_a0 = this.a0;
            this.last_a1 = this.a1;
            this.last_a2 = this.a2;
            this.last_b1 = this.b1;
            this.last_b2 = this.b2;
            this.last_q = this.q;
            this.last_gain = this.gain;
            this.last_wet = this.wet;
            this.last_set = true;
        }
        float f6 = this.wet;
        if (f6 > 0.0f || this.last_wet > 0.0f) {
            int length = array.length;
            float f7 = this.last_a0;
            float f8 = this.last_a1;
            float f9 = this.last_a2;
            float f10 = this.last_b1;
            float f11 = this.last_b2;
            float f12 = this.last_gain;
            float f13 = this.last_wet;
            float f14 = (float) length;
            float f15 = (this.a0 - f7) / f14;
            float f16 = (this.a1 - f8) / f14;
            float f17 = (this.a2 - f9) / f14;
            float f18 = (this.b1 - f10) / f14;
            float[] fArr = array;
            float f19 = (this.b2 - f11) / f14;
            float f20 = (this.gain - f12) / f14;
            float f21 = (f6 - f13) / f14;
            float f22 = this.x1;
            float f23 = f20;
            float f24 = this.x2;
            float f25 = this.y1;
            float f26 = this.y2;
            if (f21 != 0.0f) {
                float f27 = f7;
                f2 = f26;
                f3 = f24;
                float f28 = f13;
                float f29 = f11;
                float f30 = f9;
                float f31 = f27;
                f = f22;
                float f32 = f12;
                float f33 = f10;
                float f34 = f8;
                int i = 0;
                while (i < length) {
                    f31 += f15;
                    f34 += f16;
                    f30 += f17;
                    f33 += f18;
                    f29 += f19;
                    f32 += f23;
                    f28 += f21;
                    float f35 = fArr[i];
                    float f36 = ((((f31 * f35) + (f34 * f)) + (f3 * f30)) - (f33 * f25)) - (f2 * f29);
                    fArr[i] = (f36 * f32 * f28) + ((1.0f - f28) * f35);
                    i++;
                    f2 = f25;
                    f25 = f36;
                    f3 = f;
                    f = f35;
                }
            } else {
                if (f15 == 0.0f && f16 == 0.0f && f17 == 0.0f && f18 == 0.0f && f19 == 0.0f) {
                    f4 = f26;
                    f5 = f24;
                    int i2 = 0;
                    while (i2 < length) {
                        float f37 = fArr[i2];
                        float f38 = ((((f7 * f37) + (f8 * f22)) + (f5 * f9)) - (f10 * f25)) - (f4 * f11);
                        fArr[i2] = f38 * f12;
                        i2++;
                        f4 = f25;
                        f25 = f38;
                        f5 = f22;
                        f22 = f37;
                    }
                } else {
                    float f39 = f26;
                    float f40 = f12;
                    float f41 = f24;
                    float f42 = f11;
                    float f43 = f10;
                    float f44 = f9;
                    float f45 = f8;
                    float f46 = f7;
                    int i3 = 0;
                    while (i3 < length) {
                        f46 += f15;
                        f45 += f16;
                        f44 += f17;
                        f43 += f18;
                        f42 += f19;
                        f40 += f23;
                        float f47 = fArr[i3];
                        float f48 = ((((f46 * f47) + (f45 * f22)) + (f5 * f44)) - (f43 * f25)) - (f4 * f42);
                        fArr[i3] = f48 * f40;
                        i3++;
                        f39 = f25;
                        f25 = f48;
                        f41 = f22;
                        f22 = f47;
                    }
                }
                f2 = f4;
                f = f22;
            }
            float f49 = ((double) Math.abs(f)) < 1.0E-8d ? 0.0f : f;
            if (((double) Math.abs(f3)) < 1.0E-8d) {
                f3 = 0.0f;
            }
            float f50 = ((double) Math.abs(f25)) < 1.0E-8d ? 0.0f : f25;
            float f51 = ((double) Math.abs(f2)) < 1.0E-8d ? 0.0f : f2;
            this.x1 = f49;
            this.x2 = f3;
            this.y1 = f50;
            this.y2 = f51;
        }
        this.last_a0 = this.a0;
        this.last_a1 = this.a1;
        this.last_a2 = this.a2;
        this.last_b1 = this.b1;
        this.last_b2 = this.b2;
        this.last_q = this.q;
        this.last_gain = this.gain;
        this.last_wet = this.wet;
    }

    public void filter1calc() {
        if (this.cutoff < 120.0d) {
            this.cutoff = 120.0d;
        }
        double d = (this.cutoff * 7.3303828583761845d) / ((double) this.samplerate);
        if (d > 1.0d) {
            d = 1.0d;
        }
        this.a0 = (float) (Math.sqrt(1.0d - Math.cos(d)) * Math.sqrt(1.5707963267948966d));
        if (this.resonancedB < 0.0d) {
            this.resonancedB = 0.0d;
        }
        if (this.resonancedB > 20.0d) {
            this.resonancedB = 20.0d;
        }
        this.q = (float) (Math.sqrt(0.5d) * Math.pow(10.0d, -(this.resonancedB / 20.0d)));
        this.gain = (float) Math.pow(10.0d, (-this.resonancedB) / 40.0d);
        if (this.wet != 0.0f) {
            return;
        }
        if (this.resonancedB > 1.0E-5d || d < 0.9999999d) {
            this.wet = 1.0f;
        }
    }

    public void filter1(SoftAudioBuffer softAudioBuffer) {
        int i = 0;
        if (this.dirty) {
            filter1calc();
            this.dirty = false;
        }
        if (!this.last_set) {
            this.last_a0 = this.a0;
            this.last_q = this.q;
            this.last_gain = this.gain;
            this.last_wet = this.wet;
            this.last_set = true;
        }
        float f = 0.0f;
        if (this.wet > 0.0f || this.last_wet > 0.0f) {
            float[] array = softAudioBuffer.array();
            int length = array.length;
            float f2 = this.last_a0;
            float f3 = this.last_q;
            float f4 = this.last_gain;
            float f5 = this.last_wet;
            float f6 = (float) length;
            float f7 = (this.a0 - f2) / f6;
            float f8 = (this.q - f3) / f6;
            float f9 = (this.gain - f4) / f6;
            float f10 = (this.wet - f5) / f6;
            float f11 = this.y2;
            float f12 = this.y1;
            if (f10 != 0.0f) {
                while (i < length) {
                    f2 += f7;
                    f3 += f8;
                    f4 += f9;
                    f5 += f10;
                    float f13 = 1.0f - (f3 * f2);
                    float f14 = array[i];
                    f12 = (f12 * f13) + ((f14 - f11) * f2);
                    f11 = (f2 * f12) + (f13 * f11);
                    array[i] = (f11 * f4 * f5) + (f14 * (1.0f - f5));
                    i++;
                }
            } else if (f7 == 0.0f && f8 == 0.0f) {
                float f15 = 1.0f - (f3 * f2);
                while (i < length) {
                    f12 = (f12 * f15) + ((array[i] - f11) * f2);
                    f11 = (f11 * f15) + (f2 * f12);
                    array[i] = f11 * f4;
                    i++;
                }
            } else {
                while (i < length) {
                    f2 += f7;
                    f3 += f8;
                    f4 += f9;
                    float f16 = 1.0f - (f3 * f2);
                    f12 = (f12 * f16) + ((array[i] - f11) * f2);
                    f11 = (f2 * f12) + (f16 * f11);
                    array[i] = f11 * f4;
                    i++;
                }
            }
            if (((double) Math.abs(f11)) < 1.0E-8d) {
                f11 = 0.0f;
            }
            if (((double) Math.abs(f12)) >= 1.0E-8d) {
                f = f12;
            }
            this.y2 = f11;
            this.y1 = f;
        }
        this.last_a0 = this.a0;
        this.last_q = this.q;
        this.last_gain = this.gain;
        this.last_wet = this.wet;
    }
}
