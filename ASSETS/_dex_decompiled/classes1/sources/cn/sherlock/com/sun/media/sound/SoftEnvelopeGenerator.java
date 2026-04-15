package cn.sherlock.com.sun.media.sound;

import java.lang.reflect.Array;

public class SoftEnvelopeGenerator implements SoftProcess {
    public static final int EG_ATTACK = 2;
    public static final int EG_DECAY = 4;
    public static final int EG_DELAY = 1;
    public static final int EG_END = 8;
    public static final int EG_HOLD = 3;
    public static final int EG_OFF = 0;
    public static final int EG_RELEASE = 6;
    public static final int EG_SHUTDOWN = 7;
    public static final int EG_SUSTAIN = 5;
    private double[][] active;
    private double[][] attack;
    private double[][] attack2;
    private double control_time;
    private double[][] decay;
    private double[][] decay2;
    private double[][] delay;
    private double[][] hold;
    int max_count = 10;
    private double[][] on;
    private double[][] out;
    private double[][] release;
    private double[][] release2;
    private double[][] shutdown;
    private int[] stage = new int[10];
    private int[] stage_count = new int[10];
    private int[] stage_ix = new int[10];
    private double[] stage_v = new double[10];
    private double[][] sustain;
    int used_count = 0;

    public SoftEnvelopeGenerator() {
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = 10;
        this.on = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int i = this.max_count;
        int[] iArr2 = new int[2];
        iArr2[1] = 1;
        iArr2[0] = i;
        this.active = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        int i2 = this.max_count;
        int[] iArr3 = new int[2];
        iArr3[1] = 1;
        iArr3[0] = i2;
        this.out = (double[][]) Array.newInstance(Double.TYPE, iArr3);
        int i3 = this.max_count;
        int[] iArr4 = new int[2];
        iArr4[1] = 1;
        iArr4[0] = i3;
        this.delay = (double[][]) Array.newInstance(Double.TYPE, iArr4);
        int i4 = this.max_count;
        int[] iArr5 = new int[2];
        iArr5[1] = 1;
        iArr5[0] = i4;
        this.attack = (double[][]) Array.newInstance(Double.TYPE, iArr5);
        int i5 = this.max_count;
        int[] iArr6 = new int[2];
        iArr6[1] = 1;
        iArr6[0] = i5;
        this.hold = (double[][]) Array.newInstance(Double.TYPE, iArr6);
        int i6 = this.max_count;
        int[] iArr7 = new int[2];
        iArr7[1] = 1;
        iArr7[0] = i6;
        this.decay = (double[][]) Array.newInstance(Double.TYPE, iArr7);
        int i7 = this.max_count;
        int[] iArr8 = new int[2];
        iArr8[1] = 1;
        iArr8[0] = i7;
        this.sustain = (double[][]) Array.newInstance(Double.TYPE, iArr8);
        int i8 = this.max_count;
        int[] iArr9 = new int[2];
        iArr9[1] = 1;
        iArr9[0] = i8;
        this.release = (double[][]) Array.newInstance(Double.TYPE, iArr9);
        int i9 = this.max_count;
        int[] iArr10 = new int[2];
        iArr10[1] = 1;
        iArr10[0] = i9;
        this.shutdown = (double[][]) Array.newInstance(Double.TYPE, iArr10);
        int i10 = this.max_count;
        int[] iArr11 = new int[2];
        iArr11[1] = 1;
        iArr11[0] = i10;
        this.release2 = (double[][]) Array.newInstance(Double.TYPE, iArr11);
        int i11 = this.max_count;
        int[] iArr12 = new int[2];
        iArr12[1] = 1;
        iArr12[0] = i11;
        this.attack2 = (double[][]) Array.newInstance(Double.TYPE, iArr12);
        int i12 = this.max_count;
        int[] iArr13 = new int[2];
        iArr13[1] = 1;
        iArr13[0] = i12;
        this.decay2 = (double[][]) Array.newInstance(Double.TYPE, iArr13);
        this.control_time = 0.0d;
    }

    public void reset() {
        for (int i = 0; i < this.used_count; i++) {
            this.stage[i] = 0;
            this.on[i][0] = 0.0d;
            this.out[i][0] = 0.0d;
            this.delay[i][0] = 0.0d;
            this.attack[i][0] = 0.0d;
            this.hold[i][0] = 0.0d;
            this.decay[i][0] = 0.0d;
            this.sustain[i][0] = 0.0d;
            this.release[i][0] = 0.0d;
            this.shutdown[i][0] = 0.0d;
            this.attack2[i][0] = 0.0d;
            this.decay2[i][0] = 0.0d;
            this.release2[i][0] = 0.0d;
        }
        this.used_count = 0;
    }

    public void init(SoftSynthesizer softSynthesizer) {
        this.control_time = 1.0d / ((double) softSynthesizer.getControlRate());
        processControlLogic();
    }

    public double[] get(int i, String str) {
        if (i >= this.used_count) {
            this.used_count = i + 1;
        }
        if (str == null) {
            return this.out[i];
        }
        if (str.equals("on")) {
            return this.on[i];
        }
        if (str.equals("active")) {
            return this.active[i];
        }
        if (str.equals("delay")) {
            return this.delay[i];
        }
        if (str.equals("attack")) {
            return this.attack[i];
        }
        if (str.equals("hold")) {
            return this.hold[i];
        }
        if (str.equals("decay")) {
            return this.decay[i];
        }
        if (str.equals("sustain")) {
            return this.sustain[i];
        }
        if (str.equals("release")) {
            return this.release[i];
        }
        if (str.equals("shutdown")) {
            return this.shutdown[i];
        }
        if (str.equals("attack2")) {
            return this.attack2[i];
        }
        if (str.equals("decay2")) {
            return this.decay2[i];
        }
        if (str.equals("release2")) {
            return this.release2[i];
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0356  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02bf  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x02f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processControlLogic() {
        /*
            r30 = this;
            r0 = r30
            r2 = 0
        L_0x0003:
            int r3 = r0.used_count
            if (r2 >= r3) goto L_0x035e
            int[] r3 = r0.stage
            r3 = r3[r2]
            r4 = 8
            if (r3 != r4) goto L_0x0015
            r18 = r2
            r17 = 0
            goto L_0x035a
        L_0x0015:
            r5 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r11 = 7
            r12 = 6
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
            r15 = 4652992471259676672(0x4092c00000000000, double:1200.0)
            r18 = r2
            r17 = 0
            r1 = 4611686018427387904(0x4000000000000000, double:2.0)
            r19 = 0
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r3 <= 0) goto L_0x00ef
            if (r3 >= r12) goto L_0x00ef
            double[][] r3 = r0.on
            r3 = r3[r18]
            r23 = r3[r17]
            int r3 = (r23 > r9 ? 1 : (r23 == r9 ? 0 : -1))
            if (r3 >= 0) goto L_0x00ef
            int r3 = (r23 > r5 ? 1 : (r23 == r5 ? 0 : -1))
            if (r3 >= 0) goto L_0x007a
            int[] r3 = r0.stage_count
            r23 = r4
            double[][] r4 = r0.shutdown
            r4 = r4[r18]
            r24 = r4[r17]
            r26 = r5
            double r5 = r24 / r15
            double r4 = java.lang.Math.pow(r1, r5)
            r24 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            double r7 = r0.control_time
            double r4 = r4 / r7
            int r4 = (int) r4
            r3[r18] = r4
            int[] r3 = r0.stage_count
            r4 = r3[r18]
            if (r4 >= 0) goto L_0x0066
            r3[r18] = r17
        L_0x0066:
            double[] r3 = r0.stage_v
            double[][] r4 = r0.out
            r4 = r4[r18]
            r5 = r4[r17]
            r3[r18] = r5
            int[] r3 = r0.stage_ix
            r3[r18] = r17
            int[] r3 = r0.stage
            r3[r18] = r11
            goto L_0x00f8
        L_0x007a:
            r23 = r4
            r26 = r5
            r24 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            double[][] r3 = r0.release2
            r3 = r3[r18]
            r4 = r3[r17]
            int r3 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r3 >= 0) goto L_0x00af
            double[][] r3 = r0.release
            r3 = r3[r18]
            r4 = r3[r17]
            int r3 = (r4 > r19 ? 1 : (r4 == r19 ? 0 : -1))
            if (r3 >= 0) goto L_0x00af
            boolean r3 = java.lang.Double.isInfinite(r4)
            if (r3 == 0) goto L_0x00af
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r19
            double[][] r1 = r0.active
            r1 = r1[r18]
            r1[r17] = r19
            int[] r1 = r0.stage
            r1[r18] = r23
            goto L_0x035a
        L_0x00af:
            int[] r3 = r0.stage_count
            double[][] r4 = r0.release
            r4 = r4[r18]
            r5 = r4[r17]
            double r5 = r5 / r15
            double r4 = java.lang.Math.pow(r1, r5)
            double r6 = r0.control_time
            double r4 = r4 / r6
            int r4 = (int) r4
            r3[r18] = r4
            int[] r3 = r0.stage_count
            r4 = r3[r18]
            double[][] r5 = r0.release2
            r5 = r5[r18]
            r28 = r5[r17]
            double r6 = r6 * r13
            double r5 = r28 / r6
            int r5 = (int) r5
            int r4 = r4 + r5
            r3[r18] = r4
            if (r4 >= 0) goto L_0x00d7
            r3[r18] = r17
        L_0x00d7:
            int[] r4 = r0.stage_ix
            r4[r18] = r17
            double[][] r5 = r0.out
            r5 = r5[r18]
            r6 = r5[r17]
            double r5 = r21 - r6
            r3 = r3[r18]
            double r7 = (double) r3
            double r7 = r7 * r5
            int r3 = (int) r7
            r4[r18] = r3
            int[] r3 = r0.stage
            r3[r18] = r12
            goto L_0x00f8
        L_0x00ef:
            r23 = r4
            r26 = r5
            r24 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
        L_0x00f8:
            int[] r3 = r0.stage
            r4 = r3[r18]
            r5 = 2
            r6 = 3
            r7 = 1
            if (r4 == 0) goto L_0x02bf
            if (r4 == r7) goto L_0x02f0
            if (r4 == r5) goto L_0x027c
            r5 = 4
            if (r4 == r6) goto L_0x0241
            r24 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            if (r4 == r5) goto L_0x0203
            if (r4 == r12) goto L_0x0145
            if (r4 == r11) goto L_0x0115
            goto L_0x035a
        L_0x0115:
            int[] r1 = r0.stage_ix
            r2 = r1[r18]
            int r2 = r2 + r7
            r1[r18] = r2
            int[] r1 = r0.stage_count
            r1 = r1[r18]
            if (r2 < r1) goto L_0x0132
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r19
            double[][] r1 = r0.active
            r1 = r1[r18]
            r1[r17] = r19
            r3[r18] = r23
            goto L_0x035a
        L_0x0132:
            double r2 = (double) r2
            double r4 = (double) r1
            double r2 = r2 / r4
            double[][] r1 = r0.out
            r1 = r1[r18]
            double r21 = r21 - r2
            double[] r2 = r0.stage_v
            r3 = r2[r18]
            double r21 = r21 * r3
            r1[r17] = r21
            goto L_0x035a
        L_0x0145:
            int[] r4 = r0.stage_ix
            r6 = r4[r18]
            int r6 = r6 + r7
            r4[r18] = r6
            int[] r4 = r0.stage_count
            r7 = r4[r18]
            if (r6 < r7) goto L_0x0162
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r19
            double[][] r1 = r0.active
            r1 = r1[r18]
            r1[r17] = r19
            r3[r18] = r23
            goto L_0x035a
        L_0x0162:
            r8 = r5
            double r5 = (double) r6
            r28 = r9
            r10 = r8
            double r8 = (double) r7
            double r5 = r5 / r8
            double[][] r3 = r0.out
            r3 = r3[r18]
            double r5 = r21 - r5
            r3[r17] = r5
            double[][] r3 = r0.on
            r3 = r3[r18]
            r5 = r3[r17]
            int r3 = (r5 > r26 ? 1 : (r5 == r26 ? 0 : -1))
            if (r3 >= 0) goto L_0x01a6
            double[][] r3 = r0.shutdown
            r3 = r3[r18]
            r5 = r3[r17]
            double r5 = r5 / r15
            double r5 = java.lang.Math.pow(r1, r5)
            double r7 = r0.control_time
            double r5 = r5 / r7
            int r3 = (int) r5
            r4[r18] = r3
            int[] r3 = r0.stage_count
            r4 = r3[r18]
            if (r4 >= 0) goto L_0x0194
            r3[r18] = r17
        L_0x0194:
            double[] r3 = r0.stage_v
            double[][] r4 = r0.out
            r4 = r4[r18]
            r5 = r4[r17]
            r3[r18] = r5
            int[] r3 = r0.stage_ix
            r3[r18] = r17
            int[] r3 = r0.stage
            r3[r18] = r11
        L_0x01a6:
            double[][] r3 = r0.on
            r3 = r3[r18]
            r4 = r3[r17]
            int r3 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r3 <= 0) goto L_0x035a
            double[][] r3 = r0.sustain
            r3 = r3[r18]
            r4 = r3[r17]
            double r4 = r4 * r24
            double[][] r3 = r0.out
            r3 = r3[r18]
            r6 = r3[r17]
            int r3 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x035a
            int[] r3 = r0.stage
            r3[r18] = r10
            int[] r3 = r0.stage_count
            double[][] r6 = r0.decay
            r6 = r6[r18]
            r7 = r6[r17]
            double r7 = r7 / r15
            double r1 = java.lang.Math.pow(r1, r7)
            double r6 = r0.control_time
            double r1 = r1 / r6
            int r1 = (int) r1
            r3[r18] = r1
            int[] r1 = r0.stage_count
            r2 = r1[r18]
            double[][] r3 = r0.decay2
            r3 = r3[r18]
            r8 = r3[r17]
            double r6 = r6 * r13
            double r8 = r8 / r6
            int r3 = (int) r8
            int r2 = r2 + r3
            r1[r18] = r2
            if (r2 >= 0) goto L_0x01ed
            r1[r18] = r17
        L_0x01ed:
            double[][] r2 = r0.out
            r2 = r2[r18]
            r6 = r2[r17]
            double r6 = r6 - r21
            double r4 = r4 - r21
            double r6 = r6 / r4
            int[] r2 = r0.stage_ix
            r1 = r1[r18]
            double r3 = (double) r1
            double r3 = r3 * r6
            int r1 = (int) r3
            r2[r18] = r1
            goto L_0x035a
        L_0x0203:
            int[] r1 = r0.stage_ix
            r2 = r1[r18]
            int r2 = r2 + r7
            r1[r18] = r2
            double[][] r1 = r0.sustain
            r1 = r1[r18]
            r4 = r1[r17]
            double r4 = r4 * r24
            int[] r1 = r0.stage_count
            r1 = r1[r18]
            if (r2 < r1) goto L_0x0231
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r4
            r2 = 5
            r3[r18] = r2
            int r2 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r2 >= 0) goto L_0x035a
            r1[r17] = r19
            double[][] r1 = r0.active
            r1 = r1[r18]
            r1[r17] = r19
            r3[r18] = r23
            goto L_0x035a
        L_0x0231:
            double r2 = (double) r2
            double r6 = (double) r1
            double r2 = r2 / r6
            double[][] r1 = r0.out
            r1 = r1[r18]
            double r21 = r21 - r2
            double r4 = r4 * r2
            double r21 = r21 + r4
            r1[r17] = r21
            goto L_0x035a
        L_0x0241:
            r10 = r5
            int[] r4 = r0.stage_ix
            r5 = r4[r18]
            int r5 = r5 + r7
            r4[r18] = r5
            int[] r4 = r0.stage_count
            r6 = r4[r18]
            if (r5 < r6) goto L_0x035a
            r3[r18] = r10
            double[][] r3 = r0.decay
            r3 = r3[r18]
            r5 = r3[r17]
            double r5 = r5 / r15
            double r1 = java.lang.Math.pow(r1, r5)
            double r5 = r0.control_time
            double r1 = r1 / r5
            int r1 = (int) r1
            r4[r18] = r1
            int[] r1 = r0.stage_count
            r2 = r1[r18]
            double[][] r3 = r0.decay2
            r3 = r3[r18]
            r7 = r3[r17]
            double r5 = r5 * r13
            double r7 = r7 / r5
            int r3 = (int) r7
            int r2 = r2 + r3
            r1[r18] = r2
            if (r2 >= 0) goto L_0x0276
            r1[r18] = r17
        L_0x0276:
            int[] r1 = r0.stage_ix
            r1[r18] = r17
            goto L_0x035a
        L_0x027c:
            int[] r1 = r0.stage_ix
            r2 = r1[r18]
            int r2 = r2 + r7
            r1[r18] = r2
            int[] r1 = r0.stage_count
            r1 = r1[r18]
            if (r2 < r1) goto L_0x0293
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r21
            r3[r18] = r6
            goto L_0x035a
        L_0x0293:
            double r2 = (double) r2
            double r4 = (double) r1
            double r2 = r2 / r4
            r4 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r4 = java.lang.Math.log(r4)
            r6 = 4601177619296856747(0x3fdaaaaaaaaaaaab, double:0.4166666666666667)
            double r6 = r6 / r4
            double r1 = java.lang.Math.log(r2)
            double r6 = r6 * r1
            double r6 = r6 + r21
            int r1 = (r6 > r19 ? 1 : (r6 == r19 ? 0 : -1))
            if (r1 >= 0) goto L_0x02ae
            goto L_0x02b7
        L_0x02ae:
            int r1 = (r6 > r21 ? 1 : (r6 == r21 ? 0 : -1))
            if (r1 <= 0) goto L_0x02b5
            r19 = r21
            goto L_0x02b7
        L_0x02b5:
            r19 = r6
        L_0x02b7:
            double[][] r1 = r0.out
            r1 = r1[r18]
            r1[r17] = r19
            goto L_0x035a
        L_0x02bf:
            r28 = r9
            double[][] r4 = r0.active
            r4 = r4[r18]
            r4[r17] = r21
            double[][] r4 = r0.on
            r4 = r4[r18]
            r8 = r4[r17]
            int r4 = (r8 > r28 ? 1 : (r8 == r28 ? 0 : -1))
            if (r4 >= 0) goto L_0x02d3
            goto L_0x035a
        L_0x02d3:
            r3[r18] = r7
            int[] r3 = r0.stage_ix
            double[][] r4 = r0.delay
            r4 = r4[r18]
            r7 = r4[r17]
            double r7 = r7 / r15
            double r7 = java.lang.Math.pow(r1, r7)
            double r9 = r0.control_time
            double r7 = r7 / r9
            int r4 = (int) r7
            r3[r18] = r4
            int[] r3 = r0.stage_ix
            r4 = r3[r18]
            if (r4 >= 0) goto L_0x02f0
            r3[r18] = r17
        L_0x02f0:
            int[] r3 = r0.stage_ix
            r4 = r3[r18]
            if (r4 != 0) goto L_0x0356
            double[][] r3 = r0.attack
            r3 = r3[r18]
            r7 = r3[r17]
            double[][] r3 = r0.attack2
            r3 = r3[r18]
            r9 = r3[r17]
            int r3 = (r9 > r24 ? 1 : (r9 == r24 ? 0 : -1))
            if (r3 >= 0) goto L_0x0332
            int r3 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r3 >= 0) goto L_0x0332
            boolean r3 = java.lang.Double.isInfinite(r7)
            if (r3 == 0) goto L_0x0332
            double[][] r3 = r0.out
            r3 = r3[r18]
            r3[r17] = r21
            int[] r3 = r0.stage
            r3[r18] = r6
            int[] r3 = r0.stage_count
            double[][] r4 = r0.hold
            r4 = r4[r18]
            r5 = r4[r17]
            double r5 = r5 / r15
            double r1 = java.lang.Math.pow(r1, r5)
            double r4 = r0.control_time
            double r1 = r1 / r4
            int r1 = (int) r1
            r3[r18] = r1
            int[] r1 = r0.stage_ix
            r1[r18] = r17
            goto L_0x035a
        L_0x0332:
            int[] r3 = r0.stage
            r3[r18] = r5
            int[] r3 = r0.stage_count
            double r7 = r7 / r15
            double r1 = java.lang.Math.pow(r1, r7)
            double r4 = r0.control_time
            double r1 = r1 / r4
            int r1 = (int) r1
            r3[r18] = r1
            int[] r1 = r0.stage_count
            r2 = r1[r18]
            double r4 = r4 * r13
            double r9 = r9 / r4
            int r3 = (int) r9
            int r2 = r2 + r3
            r1[r18] = r2
            if (r2 >= 0) goto L_0x0351
            r1[r18] = r17
        L_0x0351:
            int[] r1 = r0.stage_ix
            r1[r18] = r17
            goto L_0x035a
        L_0x0356:
            int r4 = r4 + -1
            r3[r18] = r4
        L_0x035a:
            int r2 = r18 + 1
            goto L_0x0003
        L_0x035e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftEnvelopeGenerator.processControlLogic():void");
    }
}
