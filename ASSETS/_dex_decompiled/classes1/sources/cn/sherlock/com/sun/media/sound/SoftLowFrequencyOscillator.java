package cn.sherlock.com.sun.media.sound;

import java.lang.reflect.Array;

public class SoftLowFrequencyOscillator implements SoftProcess {
    private static double PI2 = 6.283185307179586d;
    private double control_time;
    private double[][] delay;
    private double[][] delay2;
    private int[] delay_counter;
    private double[][] freq;
    private int max_count = 10;
    private double[][] out;
    private double sin_factor;
    private double[] sin_phase;
    private double[] sin_step;
    private double[] sin_stepfreq;
    private int used_count;

    public SoftLowFrequencyOscillator() {
        int i = 0;
        this.used_count = 0;
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = 10;
        this.out = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int i2 = this.max_count;
        int[] iArr2 = new int[2];
        iArr2[1] = 1;
        iArr2[0] = i2;
        this.delay = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        int i3 = this.max_count;
        int[] iArr3 = new int[2];
        iArr3[1] = 1;
        iArr3[0] = i3;
        this.delay2 = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        int i4 = this.max_count;
        int[] iArr4 = new int[2];
        iArr4[1] = 1;
        iArr4[0] = i4;
        this.freq = (double[][]) Array.newInstance(Double.TYPE, iArr4);
        int i5 = this.max_count;
        this.delay_counter = new int[i5];
        this.sin_phase = new double[i5];
        this.sin_stepfreq = new double[i5];
        this.sin_step = new double[i5];
        this.control_time = 0.0d;
        this.sin_factor = 0.0d;
        while (true) {
            double[] dArr = this.sin_stepfreq;
            if (i < dArr.length) {
                dArr[i] = Double.NEGATIVE_INFINITY;
                i++;
            } else {
                return;
            }
        }
    }

    public void reset() {
        for (int i = 0; i < this.used_count; i++) {
            this.out[i][0] = 0.0d;
            this.delay[i][0] = 0.0d;
            this.delay2[i][0] = 0.0d;
            this.freq[i][0] = 0.0d;
            this.delay_counter[i] = 0;
            this.sin_phase[i] = 0.0d;
            this.sin_stepfreq[i] = Double.NEGATIVE_INFINITY;
            this.sin_step[i] = 0.0d;
        }
        this.used_count = 0;
    }

    public void init(SoftSynthesizer softSynthesizer) {
        double controlRate = 1.0d / ((double) softSynthesizer.getControlRate());
        this.control_time = controlRate;
        this.sin_factor = controlRate * 2.0d * 3.141592653589793d;
        for (int i = 0; i < this.used_count; i++) {
            int[] iArr = this.delay_counter;
            double pow = Math.pow(2.0d, this.delay[i][0] / 1200.0d);
            double d = this.control_time;
            iArr[i] = (int) (pow / d);
            int[] iArr2 = this.delay_counter;
            iArr2[i] = iArr2[i] + ((int) (this.delay2[i][0] / (d * 1000.0d)));
        }
        processControlLogic();
    }

    public void processControlLogic() {
        for (int i = 0; i < this.used_count; i++) {
            int[] iArr = this.delay_counter;
            int i2 = iArr[i];
            if (i2 > 0) {
                iArr[i] = i2 - 1;
                this.out[i][0] = 0.5d;
            } else {
                double d = this.freq[i][0];
                double[] dArr = this.sin_stepfreq;
                if (dArr[i] != d) {
                    dArr[i] = d;
                    this.sin_step[i] = Math.exp((d - 6900.0d) * (Math.log(2.0d) / 1200.0d)) * 440.0d * this.sin_factor;
                }
                double d2 = this.sin_phase[i] + this.sin_step[i];
                while (true) {
                    double d3 = PI2;
                    if (d2 <= d3) {
                        break;
                    }
                    d2 -= d3;
                }
                this.out[i][0] = (Math.sin(d2) * 0.5d) + 0.5d;
                this.sin_phase[i] = d2;
            }
        }
    }

    public double[] get(int i, String str) {
        if (i >= this.used_count) {
            this.used_count = i + 1;
        }
        if (str == null) {
            return this.out[i];
        }
        if (str.equals("delay")) {
            return this.delay[i];
        }
        if (str.equals("delay2")) {
            return this.delay2[i];
        }
        if (str.equals("freq")) {
            return this.freq[i];
        }
        return null;
    }
}
