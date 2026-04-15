package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioInputStream;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import jp.kshoji.javax.sound.midi.MidiMessage;
import jp.kshoji.javax.sound.midi.ShortMessage;

public class SoftMainMixer {
    public static final int CHANNEL_DELAY_EFFECT1 = 8;
    public static final int CHANNEL_DELAY_EFFECT2 = 9;
    public static final int CHANNEL_DELAY_LEFT = 3;
    public static final int CHANNEL_DELAY_MONO = 5;
    public static final int CHANNEL_DELAY_RIGHT = 4;
    public static final int CHANNEL_EFFECT1 = 6;
    public static final int CHANNEL_EFFECT2 = 7;
    public static final int CHANNEL_LEFT = 0;
    public static final int CHANNEL_LEFT_DRY = 10;
    public static final int CHANNEL_MONO = 2;
    public static final int CHANNEL_RIGHT = 1;
    public static final int CHANNEL_RIGHT_DRY = 11;
    public static final int CHANNEL_SCRATCH1 = 12;
    public static final int CHANNEL_SCRATCH2 = 13;
    private static final String LOGTAG = "SoftMainMixer";
    protected boolean active_sensing_on = false;
    private SoftAudioProcessor agc;
    private AudioInputStream ais;
    private int buffer_len = 0;
    /* access modifiers changed from: private */
    public SoftAudioBuffer[] buffers;
    private SoftAudioProcessor chorus;
    protected SoftControl co_master = new SoftControl() {
        double[] balance;
        double[] coarse_tuning;
        double[] fine_tuning;
        double[] volume;

        {
            this.balance = SoftMainMixer.this.co_master_balance;
            this.volume = SoftMainMixer.this.co_master_volume;
            this.coarse_tuning = SoftMainMixer.this.co_master_coarse_tuning;
            this.fine_tuning = SoftMainMixer.this.co_master_fine_tuning;
        }

        public double[] get(int i, String str) {
            if (str == null) {
                return null;
            }
            if (str.equals("balance")) {
                return this.balance;
            }
            if (str.equals("volume")) {
                return this.volume;
            }
            if (str.equals("coarse_tuning")) {
                return this.coarse_tuning;
            }
            if (str.equals("fine_tuning")) {
                return this.fine_tuning;
            }
            return null;
        }
    };
    /* access modifiers changed from: private */
    public double[] co_master_balance = new double[1];
    /* access modifiers changed from: private */
    public double[] co_master_coarse_tuning = new double[1];
    /* access modifiers changed from: private */
    public double[] co_master_fine_tuning = new double[1];
    /* access modifiers changed from: private */
    public double[] co_master_volume = new double[1];
    private Object control_mutex;
    private SoftChannelMixerContainer[] cur_registeredMixers = null;
    private int delay_midievent = 0;
    double last_volume_left = 1.0d;
    double last_volume_right = 1.0d;
    private int max_delay_midievent = 0;
    protected TreeMap<Long, Object> midimessages = new TreeMap<>();
    private long msec_buffer_len = 0;
    private long msec_last_activity = -1;
    private int nrofchannels = 2;
    private boolean pusher_silent = false;
    private int pusher_silent_count = 0;
    protected boolean readfully = true;
    private Set<SoftChannelMixerContainer> registeredMixers = null;
    private SoftReverb reverb;
    private long sample_pos = 0;
    private float samplerate = 44100.0f;
    private Set<ModelChannelMixer> stoppedMixers = null;
    /* access modifiers changed from: private */
    public SoftSynthesizer synth;
    private SoftVoice[] voicestatus = null;

    public void close() {
    }

    private class SoftChannelMixerContainer {
        SoftAudioBuffer[] buffers;
        ModelChannelMixer mixer;

        private SoftChannelMixerContainer() {
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processSystemExclusiveMessage(byte[] r18) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r2 = r1.synth
            java.lang.Object r2 = r2.control_mutex
            monitor-enter(r2)
            r1.activity()     // Catch:{ all -> 0x034d }
            r3 = 1
            byte r4 = r0[r3]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            r5 = 126(0x7e, float:1.77E-43)
            r6 = 127(0x7f, float:1.78E-43)
            r7 = 3
            r8 = 6
            r9 = 4
            r10 = 7
            r11 = 5
            r12 = 0
            r13 = 2
            if (r4 != r5) goto L_0x0101
            byte r4 = r0[r13]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r6) goto L_0x002c
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r5 = r1.synth     // Catch:{ all -> 0x034d }
            int r5 = r5.getDeviceID()     // Catch:{ all -> 0x034d }
            if (r4 != r5) goto L_0x0101
        L_0x002c:
            byte r4 = r0[r7]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            switch(r4) {
                case 8: goto L_0x009c;
                case 9: goto L_0x0073;
                case 10: goto L_0x0035;
                default: goto L_0x0033;
            }     // Catch:{ all -> 0x034d }
        L_0x0033:
            goto L_0x0101
        L_0x0035:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x005d
            if (r4 == r13) goto L_0x004f
            if (r4 == r7) goto L_0x0049
            if (r4 == r9) goto L_0x0043
            goto L_0x0101
        L_0x0043:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.voice_allocation_mode = r3     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x0049:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.voice_allocation_mode = r12     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x004f:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.setGeneralMidiMode(r12)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.voice_allocation_mode = r12     // Catch:{ all -> 0x034d }
            r1.reset()     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x005d:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            int r4 = r4.getGeneralMidiMode()     // Catch:{ all -> 0x034d }
            if (r4 != 0) goto L_0x006a
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.setGeneralMidiMode(r3)     // Catch:{ all -> 0x034d }
        L_0x006a:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.voice_allocation_mode = r3     // Catch:{ all -> 0x034d }
            r1.reset()     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x0073:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x0093
            if (r4 == r13) goto L_0x0089
            if (r4 == r7) goto L_0x007f
            goto L_0x0101
        L_0x007f:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.setGeneralMidiMode(r13)     // Catch:{ all -> 0x034d }
            r1.reset()     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x0089:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.setGeneralMidiMode(r12)     // Catch:{ all -> 0x034d }
            r1.reset()     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x0093:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            r4.setGeneralMidiMode(r3)     // Catch:{ all -> 0x034d }
            r1.reset()     // Catch:{ all -> 0x034d }
            goto L_0x0101
        L_0x009c:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x00ec
            switch(r4) {
                case 4: goto L_0x00d3;
                case 5: goto L_0x00d3;
                case 6: goto L_0x00d3;
                case 7: goto L_0x00d3;
                case 8: goto L_0x00a6;
                case 9: goto L_0x00a6;
                default: goto L_0x00a5;
            }     // Catch:{ all -> 0x034d }
        L_0x00a5:
            goto L_0x0101
        L_0x00a6:
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = new cn.sherlock.com.sun.media.sound.SoftTuning     // Catch:{ all -> 0x034d }
            r4.<init>((byte[]) r0)     // Catch:{ all -> 0x034d }
            byte r5 = r0[r11]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 * 16384
            byte r14 = r0[r8]     // Catch:{ all -> 0x034d }
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r14 = r14 * 128
            int r5 = r5 + r14
            byte r14 = r0[r10]     // Catch:{ all -> 0x034d }
            r14 = r14 & 255(0xff, float:3.57E-43)
            int r5 = r5 + r14
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r14 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r14 = r14.channels     // Catch:{ all -> 0x034d }
            r16 = r8
            r15 = r12
        L_0x00c4:
            int r8 = r14.length     // Catch:{ all -> 0x034d }
            if (r15 >= r8) goto L_0x0103
            int r8 = r3 << r15
            r8 = r8 & r5
            if (r8 == 0) goto L_0x00d0
            r8 = r14[r15]     // Catch:{ all -> 0x034d }
            r8.tuning = r4     // Catch:{ all -> 0x034d }
        L_0x00d0:
            int r15 = r15 + 1
            goto L_0x00c4
        L_0x00d3:
            r16 = r8
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            jp.kshoji.javax.sound.midi.Patch r5 = new jp.kshoji.javax.sound.midi.Patch     // Catch:{ all -> 0x034d }
            byte r8 = r0[r11]     // Catch:{ all -> 0x034d }
            r8 = r8 & 255(0xff, float:3.57E-43)
            byte r14 = r0[r16]     // Catch:{ all -> 0x034d }
            r14 = r14 & 255(0xff, float:3.57E-43)
            r5.<init>(r8, r14)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = r4.getTuning(r5)     // Catch:{ all -> 0x034d }
            r4.load(r0)     // Catch:{ all -> 0x034d }
            goto L_0x0103
        L_0x00ec:
            r16 = r8
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r4 = r1.synth     // Catch:{ all -> 0x034d }
            jp.kshoji.javax.sound.midi.Patch r5 = new jp.kshoji.javax.sound.midi.Patch     // Catch:{ all -> 0x034d }
            byte r8 = r0[r11]     // Catch:{ all -> 0x034d }
            r8 = r8 & 255(0xff, float:3.57E-43)
            r5.<init>(r12, r8)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = r4.getTuning(r5)     // Catch:{ all -> 0x034d }
            r4.load(r0)     // Catch:{ all -> 0x034d }
            goto L_0x0103
        L_0x0101:
            r16 = r8
        L_0x0103:
            byte r4 = r0[r3]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 != r6) goto L_0x034b
            byte r4 = r0[r13]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r6) goto L_0x0117
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r5 = r1.synth     // Catch:{ all -> 0x034d }
            int r5 = r5.getDeviceID()     // Catch:{ all -> 0x034d }
            if (r4 != r5) goto L_0x034b
        L_0x0117:
            byte r4 = r0[r7]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            r5 = 8
            if (r4 == r9) goto L_0x02af
            switch(r4) {
                case 8: goto L_0x01f2;
                case 9: goto L_0x014e;
                case 10: goto L_0x0124;
                default: goto L_0x0122;
            }     // Catch:{ all -> 0x034d }
        L_0x0122:
            goto L_0x034b
        L_0x0124:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x012c
            goto L_0x034b
        L_0x012c:
            byte r4 = r0[r11]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r5 = r0[r16]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r6 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r6 = r6.channels     // Catch:{ all -> 0x034d }
            r4 = r6[r4]     // Catch:{ all -> 0x034d }
        L_0x013a:
            int r6 = r0.length     // Catch:{ all -> 0x034d }
            int r6 = r6 - r3
            if (r10 >= r6) goto L_0x034b
            byte r6 = r0[r10]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r7 = r10 + 1
            byte r7 = r0[r7]     // Catch:{ all -> 0x034d }
            r7 = r7 & 255(0xff, float:3.57E-43)
            r4.controlChangePerNote(r5, r6, r7)     // Catch:{ all -> 0x034d }
            int r10 = r10 + 2
            goto L_0x013a
        L_0x014e:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x01c0
            if (r4 == r13) goto L_0x018e
            if (r4 == r7) goto L_0x015a
            goto L_0x034b
        L_0x015a:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            int r4 = r4 - r10
            int r4 = r4 / r13
            int[] r4 = new int[r4]     // Catch:{ all -> 0x034d }
            int r5 = r0.length     // Catch:{ all -> 0x034d }
            int r5 = r5 - r10
            int r5 = r5 / r13
            int[] r5 = new int[r5]     // Catch:{ all -> 0x034d }
        L_0x0164:
            int r6 = r0.length     // Catch:{ all -> 0x034d }
            int r6 = r6 - r3
            if (r10 >= r6) goto L_0x017b
            byte r6 = r0[r10]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4[r12] = r6     // Catch:{ all -> 0x034d }
            int r6 = r10 + 1
            byte r6 = r0[r6]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5[r12] = r6     // Catch:{ all -> 0x034d }
            int r12 = r12 + 1
            int r10 = r10 + 2
            goto L_0x0164
        L_0x017b:
            byte r3 = r0[r11]     // Catch:{ all -> 0x034d }
            r3 = r3 & 255(0xff, float:3.57E-43)
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r6 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r6 = r6.channels     // Catch:{ all -> 0x034d }
            r3 = r6[r3]     // Catch:{ all -> 0x034d }
            byte r0 = r0[r16]     // Catch:{ all -> 0x034d }
            r0 = r0 & 255(0xff, float:3.57E-43)
            r3.mapControlToDestination(r0, r4, r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x018e:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            int r4 = r4 - r10
            int r4 = r4 / r13
            int[] r4 = new int[r4]     // Catch:{ all -> 0x034d }
            int r5 = r0.length     // Catch:{ all -> 0x034d }
            int r5 = r5 - r10
            int r5 = r5 / r13
            int[] r5 = new int[r5]     // Catch:{ all -> 0x034d }
            r8 = r16
        L_0x019a:
            int r6 = r0.length     // Catch:{ all -> 0x034d }
            int r6 = r6 - r3
            if (r8 >= r6) goto L_0x01b1
            byte r6 = r0[r8]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4[r12] = r6     // Catch:{ all -> 0x034d }
            int r6 = r8 + 1
            byte r6 = r0[r6]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5[r12] = r6     // Catch:{ all -> 0x034d }
            int r12 = r12 + 1
            int r8 = r8 + 2
            goto L_0x019a
        L_0x01b1:
            byte r0 = r0[r11]     // Catch:{ all -> 0x034d }
            r0 = r0 & 255(0xff, float:3.57E-43)
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r3 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r3 = r3.channels     // Catch:{ all -> 0x034d }
            r0 = r3[r0]     // Catch:{ all -> 0x034d }
            r0.mapPolyPressureToDestination(r4, r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x01c0:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            int r4 = r4 - r10
            int r4 = r4 / r13
            int[] r4 = new int[r4]     // Catch:{ all -> 0x034d }
            int r5 = r0.length     // Catch:{ all -> 0x034d }
            int r5 = r5 - r10
            int r5 = r5 / r13
            int[] r5 = new int[r5]     // Catch:{ all -> 0x034d }
            r8 = r16
        L_0x01cc:
            int r6 = r0.length     // Catch:{ all -> 0x034d }
            int r6 = r6 - r3
            if (r8 >= r6) goto L_0x01e3
            byte r6 = r0[r8]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4[r12] = r6     // Catch:{ all -> 0x034d }
            int r6 = r8 + 1
            byte r6 = r0[r6]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r5[r12] = r6     // Catch:{ all -> 0x034d }
            int r12 = r12 + 1
            int r8 = r8 + 2
            goto L_0x01cc
        L_0x01e3:
            byte r0 = r0[r11]     // Catch:{ all -> 0x034d }
            r0 = r0 & 255(0xff, float:3.57E-43)
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r3 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r3 = r3.channels     // Catch:{ all -> 0x034d }
            r0 = r3[r0]     // Catch:{ all -> 0x034d }
            r0.mapChannelPressureToDestination(r4, r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x01f2:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r13) goto L_0x0280
            if (r4 == r10) goto L_0x024d
            if (r4 == r5) goto L_0x0202
            r5 = 9
            if (r4 == r5) goto L_0x0202
            goto L_0x034b
        L_0x0202:
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = new cn.sherlock.com.sun.media.sound.SoftTuning     // Catch:{ all -> 0x034d }
            r4.<init>((byte[]) r0)     // Catch:{ all -> 0x034d }
            byte r5 = r0[r11]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            int r5 = r5 * 16384
            byte r6 = r0[r16]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            int r6 = r6 * 128
            int r5 = r5 + r6
            byte r0 = r0[r10]     // Catch:{ all -> 0x034d }
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r5 = r5 + r0
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r0 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftChannel[] r0 = r0.channels     // Catch:{ all -> 0x034d }
            r6 = r12
        L_0x021e:
            int r7 = r0.length     // Catch:{ all -> 0x034d }
            if (r6 >= r7) goto L_0x022d
            int r7 = r3 << r6
            r7 = r7 & r5
            if (r7 == 0) goto L_0x022a
            r7 = r0[r6]     // Catch:{ all -> 0x034d }
            r7.tuning = r4     // Catch:{ all -> 0x034d }
        L_0x022a:
            int r6 = r6 + 1
            goto L_0x021e
        L_0x022d:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r0 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r0 = r0.getVoices()     // Catch:{ all -> 0x034d }
        L_0x0233:
            int r6 = r0.length     // Catch:{ all -> 0x034d }
            if (r12 >= r6) goto L_0x034b
            r6 = r0[r12]     // Catch:{ all -> 0x034d }
            boolean r6 = r6.active     // Catch:{ all -> 0x034d }
            if (r6 == 0) goto L_0x024a
            r6 = r0[r12]     // Catch:{ all -> 0x034d }
            int r6 = r6.channel     // Catch:{ all -> 0x034d }
            int r6 = r3 << r6
            r6 = r6 & r5
            if (r6 == 0) goto L_0x024a
            r6 = r0[r12]     // Catch:{ all -> 0x034d }
            r6.updateTuning(r4)     // Catch:{ all -> 0x034d }
        L_0x024a:
            int r12 = r12 + 1
            goto L_0x0233
        L_0x024d:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r3 = r1.synth     // Catch:{ all -> 0x034d }
            jp.kshoji.javax.sound.midi.Patch r4 = new jp.kshoji.javax.sound.midi.Patch     // Catch:{ all -> 0x034d }
            byte r5 = r0[r11]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r6 = r0[r16]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            r4.<init>(r5, r6)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r3 = r3.getTuning(r4)     // Catch:{ all -> 0x034d }
            r3.load(r0)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r0 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r0 = r0.getVoices()     // Catch:{ all -> 0x034d }
        L_0x0269:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            if (r12 >= r4) goto L_0x034b
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            boolean r4 = r4.active     // Catch:{ all -> 0x034d }
            if (r4 == 0) goto L_0x027d
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = r4.tuning     // Catch:{ all -> 0x034d }
            if (r4 != r3) goto L_0x027d
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            r4.updateTuning(r3)     // Catch:{ all -> 0x034d }
        L_0x027d:
            int r12 = r12 + 1
            goto L_0x0269
        L_0x0280:
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r3 = r1.synth     // Catch:{ all -> 0x034d }
            jp.kshoji.javax.sound.midi.Patch r4 = new jp.kshoji.javax.sound.midi.Patch     // Catch:{ all -> 0x034d }
            byte r5 = r0[r11]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            r4.<init>(r12, r5)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r3 = r3.getTuning(r4)     // Catch:{ all -> 0x034d }
            r3.load(r0)     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r0 = r1.synth     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r0 = r0.getVoices()     // Catch:{ all -> 0x034d }
        L_0x0298:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            if (r12 >= r4) goto L_0x034b
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            boolean r4 = r4.active     // Catch:{ all -> 0x034d }
            if (r4 == 0) goto L_0x02ac
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            cn.sherlock.com.sun.media.sound.SoftTuning r4 = r4.tuning     // Catch:{ all -> 0x034d }
            if (r4 != r3) goto L_0x02ac
            r4 = r0[r12]     // Catch:{ all -> 0x034d }
            r4.updateTuning(r3)     // Catch:{ all -> 0x034d }
        L_0x02ac:
            int r12 = r12 + 1
            goto L_0x0298
        L_0x02af:
            byte r4 = r0[r9]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            if (r4 == r3) goto L_0x032b
            if (r4 == r13) goto L_0x032b
            if (r4 == r7) goto L_0x032b
            if (r4 == r9) goto L_0x032b
            if (r4 == r11) goto L_0x02bf
            goto L_0x034b
        L_0x02bf:
            byte r4 = r0[r11]     // Catch:{ all -> 0x034d }
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r6 = r0[r16]     // Catch:{ all -> 0x034d }
            r6 = r6 & 255(0xff, float:3.57E-43)
            byte r7 = r0[r10]     // Catch:{ all -> 0x034d }
            r7 = r7 & 255(0xff, float:3.57E-43)
            int[] r8 = new int[r4]     // Catch:{ all -> 0x034d }
            r9 = r12
        L_0x02ce:
            if (r9 >= r4) goto L_0x02e4
            int r10 = r5 + 1
            byte r11 = r0[r5]     // Catch:{ all -> 0x034d }
            r11 = r11 & 255(0xff, float:3.57E-43)
            int r5 = r5 + 2
            byte r10 = r0[r10]     // Catch:{ all -> 0x034d }
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r11 = r11 * 128
            int r11 = r11 + r10
            r8[r9] = r11     // Catch:{ all -> 0x034d }
            int r9 = r9 + 1
            goto L_0x02ce
        L_0x02e4:
            int r4 = r0.length     // Catch:{ all -> 0x034d }
            int r4 = r4 - r3
            int r4 = r4 - r5
            int r3 = r6 + r7
            int r4 = r4 / r3
            long[] r3 = new long[r4]     // Catch:{ all -> 0x034d }
            long[] r9 = new long[r4]     // Catch:{ all -> 0x034d }
            r10 = r12
        L_0x02ef:
            if (r10 >= r4) goto L_0x0327
            r13 = 0
            r9[r10] = r13     // Catch:{ all -> 0x034d }
            r11 = r12
        L_0x02f6:
            r13 = 128(0x80, double:6.32E-322)
            if (r11 >= r6) goto L_0x030d
            r15 = r3[r10]     // Catch:{ all -> 0x034d }
            long r15 = r15 * r13
            int r13 = r5 + 1
            byte r5 = r0[r5]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            r14 = r13
            long r12 = (long) r5     // Catch:{ all -> 0x034d }
            long r15 = r15 + r12
            r3[r10] = r15     // Catch:{ all -> 0x034d }
            int r11 = r11 + 1
            r5 = r14
            r12 = 0
            goto L_0x02f6
        L_0x030d:
            r11 = 0
        L_0x030e:
            if (r11 >= r7) goto L_0x0323
            r15 = r9[r10]     // Catch:{ all -> 0x034d }
            long r15 = r15 * r13
            int r12 = r5 + 1
            byte r5 = r0[r5]     // Catch:{ all -> 0x034d }
            r5 = r5 & 255(0xff, float:3.57E-43)
            long r13 = (long) r5     // Catch:{ all -> 0x034d }
            long r15 = r15 + r13
            r9[r10] = r15     // Catch:{ all -> 0x034d }
            int r11 = r11 + 1
            r5 = r12
            r13 = 128(0x80, double:6.32E-322)
            goto L_0x030e
        L_0x0323:
            int r10 = r10 + 1
            r12 = 0
            goto L_0x02ef
        L_0x0327:
            r1.globalParameterControlChange(r8, r3, r9)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x032b:
            byte r5 = r0[r11]     // Catch:{ all -> 0x034d }
            r5 = r5 & r6
            byte r0 = r0[r16]     // Catch:{ all -> 0x034d }
            r0 = r0 & r6
            int r0 = r0 * 128
            int r5 = r5 + r0
            if (r4 != r3) goto L_0x033a
            r1.setVolume(r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x033a:
            if (r4 != r13) goto L_0x0340
            r1.setBalance(r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x0340:
            if (r4 != r7) goto L_0x0346
            r1.setFineTuning(r5)     // Catch:{ all -> 0x034d }
            goto L_0x034b
        L_0x0346:
            if (r4 != r9) goto L_0x034b
            r1.setCoarseTuning(r5)     // Catch:{ all -> 0x034d }
        L_0x034b:
            monitor-exit(r2)     // Catch:{ all -> 0x034d }
            return
        L_0x034d:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x034d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftMainMixer.processSystemExclusiveMessage(byte[]):void");
    }

    private void processMessages(long j) {
        Iterator<Map.Entry<Long, Object>> it = this.midimessages.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry next = it.next();
            if (((Long) next.getKey()).longValue() < this.msec_buffer_len + j) {
                int longValue = (int) ((((double) (((Long) next.getKey()).longValue() - j)) * (((double) this.samplerate) / 1000000.0d)) + 0.5d);
                this.delay_midievent = longValue;
                int i = this.max_delay_midievent;
                if (longValue > i) {
                    this.delay_midievent = i;
                }
                if (this.delay_midievent < 0) {
                    this.delay_midievent = 0;
                }
                processMessage(next.getValue());
                it.remove();
            } else {
                return;
            }
        }
        this.delay_midievent = 0;
    }

    /* access modifiers changed from: protected */
    public void processAudioBuffers() {
        SoftAudioBuffer[] softAudioBufferArr;
        double d;
        double d2;
        SoftChannelMixerContainer[] softChannelMixerContainerArr;
        char c;
        int i;
        double d3;
        int size;
        SoftAudioBuffer softAudioBuffer;
        Set<SoftChannelMixerContainer> set;
        if (!(this.synth.weakstream == null || this.synth.weakstream.silent_samples == 0)) {
            this.sample_pos += this.synth.weakstream.silent_samples;
            this.synth.weakstream.silent_samples = 0;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            softAudioBufferArr = this.buffers;
            if (i3 >= softAudioBufferArr.length) {
                break;
            }
            if (!(i3 == 3 || i3 == 4 || i3 == 5 || i3 == 8 || i3 == 9)) {
                softAudioBufferArr[i3].clear();
            }
            i3++;
        }
        if (!softAudioBufferArr[3].isSilent()) {
            SoftAudioBuffer[] softAudioBufferArr2 = this.buffers;
            softAudioBufferArr2[0].swap(softAudioBufferArr2[3]);
        }
        int i4 = 1;
        if (!this.buffers[4].isSilent()) {
            SoftAudioBuffer[] softAudioBufferArr3 = this.buffers;
            softAudioBufferArr3[1].swap(softAudioBufferArr3[4]);
        }
        if (!this.buffers[5].isSilent()) {
            SoftAudioBuffer[] softAudioBufferArr4 = this.buffers;
            softAudioBufferArr4[2].swap(softAudioBufferArr4[5]);
        }
        if (!this.buffers[8].isSilent()) {
            SoftAudioBuffer[] softAudioBufferArr5 = this.buffers;
            softAudioBufferArr5[6].swap(softAudioBufferArr5[8]);
        }
        if (!this.buffers[9].isSilent()) {
            SoftAudioBuffer[] softAudioBufferArr6 = this.buffers;
            softAudioBufferArr6[7].swap(softAudioBufferArr6[9]);
        }
        synchronized (this.control_mutex) {
            long j = (long) (((double) this.sample_pos) * (1000000.0d / ((double) this.samplerate)));
            processMessages(j);
            if (this.active_sensing_on && j - this.msec_last_activity > 1000000) {
                this.active_sensing_on = false;
                for (SoftChannel allSoundOff : this.synth.channels) {
                    allSoundOff.allSoundOff();
                }
            }
            int i5 = 0;
            while (true) {
                SoftVoice[] softVoiceArr = this.voicestatus;
                if (i5 >= softVoiceArr.length) {
                    break;
                }
                if (softVoiceArr[i5].active) {
                    this.voicestatus[i5].processControlLogic();
                }
                i5++;
            }
            this.sample_pos += (long) this.buffer_len;
            d = this.co_master_volume[0];
            double d4 = this.co_master_balance[0];
            if (d4 > 0.5d) {
                d2 = d;
                d = (1.0d - d4) * 2.0d * d;
            } else {
                d2 = d4 * 2.0d * d;
            }
            this.chorus.processControlLogic();
            this.reverb.processControlLogic();
            this.agc.processControlLogic();
            if (this.cur_registeredMixers == null && (set = this.registeredMixers) != null) {
                SoftChannelMixerContainer[] softChannelMixerContainerArr2 = new SoftChannelMixerContainer[set.size()];
                this.cur_registeredMixers = softChannelMixerContainerArr2;
                this.registeredMixers.toArray(softChannelMixerContainerArr2);
            }
            softChannelMixerContainerArr = this.cur_registeredMixers;
            if (softChannelMixerContainerArr != null) {
                c = 2;
                if (softChannelMixerContainerArr.length == 0) {
                    softChannelMixerContainerArr = null;
                }
            } else {
                c = 2;
            }
        }
        if (softChannelMixerContainerArr != null) {
            SoftAudioBuffer[] softAudioBufferArr7 = this.buffers;
            SoftAudioBuffer softAudioBuffer2 = softAudioBufferArr7[0];
            SoftAudioBuffer softAudioBuffer3 = softAudioBufferArr7[1];
            SoftAudioBuffer softAudioBuffer4 = softAudioBufferArr7[c];
            SoftAudioBuffer softAudioBuffer5 = softAudioBufferArr7[3];
            SoftAudioBuffer softAudioBuffer6 = softAudioBufferArr7[4];
            SoftAudioBuffer softAudioBuffer7 = softAudioBufferArr7[5];
            int size2 = softAudioBuffer2.getSize();
            int i6 = this.nrofchannels;
            d3 = 1.0d;
            float[][] fArr = new float[i6][];
            float[][] fArr2 = new float[i6][];
            fArr2[0] = softAudioBuffer2.array();
            i = 5;
            if (this.nrofchannels != 1) {
                fArr2[1] = softAudioBuffer3.array();
            }
            int length = softChannelMixerContainerArr.length;
            int i7 = 0;
            while (i7 < length) {
                int i8 = i2;
                SoftChannelMixerContainer softChannelMixerContainer = softChannelMixerContainerArr[i7];
                int i9 = i4;
                SoftAudioBuffer softAudioBuffer8 = softAudioBuffer7;
                this.buffers[i8] = softChannelMixerContainer.buffers[i8];
                this.buffers[i9] = softChannelMixerContainer.buffers[i9];
                this.buffers[c] = softChannelMixerContainer.buffers[c];
                this.buffers[3] = softChannelMixerContainer.buffers[3];
                this.buffers[4] = softChannelMixerContainer.buffers[4];
                this.buffers[5] = softChannelMixerContainer.buffers[5];
                this.buffers[i8].clear();
                this.buffers[i9].clear();
                this.buffers[c].clear();
                if (!this.buffers[3].isSilent()) {
                    SoftAudioBuffer[] softAudioBufferArr8 = this.buffers;
                    softAudioBufferArr8[i8].swap(softAudioBufferArr8[3]);
                }
                if (!this.buffers[4].isSilent()) {
                    SoftAudioBuffer[] softAudioBufferArr9 = this.buffers;
                    softAudioBufferArr9[i9].swap(softAudioBufferArr9[4]);
                }
                if (!this.buffers[5].isSilent()) {
                    SoftAudioBuffer[] softAudioBufferArr10 = this.buffers;
                    softAudioBufferArr10[c].swap(softAudioBufferArr10[5]);
                }
                fArr[i8] = this.buffers[i8].array();
                int i10 = i9;
                if (this.nrofchannels != i10) {
                    fArr[i10] = this.buffers[i10].array();
                }
                int i11 = i8;
                int i12 = i11;
                while (true) {
                    SoftVoice[] softVoiceArr2 = this.voicestatus;
                    softAudioBuffer = softAudioBuffer2;
                    if (i11 >= softVoiceArr2.length) {
                        break;
                    }
                    if (softVoiceArr2[i11].active && this.voicestatus[i11].channelmixer == softChannelMixerContainer.mixer) {
                        this.voicestatus[i11].processAudioLogic(this.buffers);
                        i12 = 1;
                    }
                    i11++;
                    softAudioBuffer2 = softAudioBuffer;
                }
                if (!this.buffers[c].isSilent()) {
                    float[] array = this.buffers[c].array();
                    float[] array2 = this.buffers[i8].array();
                    float[] fArr3 = array;
                    if (this.nrofchannels != 1) {
                        float[] array3 = this.buffers[1].array();
                        for (int i13 = i8; i13 < size2; i13++) {
                            float f = fArr3[i13];
                            array2[i13] = array2[i13] + f;
                            array3[i13] = array3[i13] + f;
                        }
                    } else {
                        for (int i14 = i8; i14 < size2; i14++) {
                            array2[i14] = array2[i14] + fArr3[i14];
                        }
                    }
                }
                if (!softChannelMixerContainer.mixer.process(fArr, i8, size2)) {
                    synchronized (this.control_mutex) {
                        this.registeredMixers.remove(softChannelMixerContainer);
                        this.cur_registeredMixers = null;
                    }
                }
                int i15 = 0;
                while (i15 < i6) {
                    float[] fArr4 = fArr[i15];
                    float[] fArr5 = fArr2[i15];
                    int i16 = i15;
                    for (int i17 = 0; i17 < size2; i17++) {
                        fArr5[i17] = fArr5[i17] + fArr4[i17];
                    }
                    i15 = i16 + 1;
                }
                if (i12 == 0) {
                    synchronized (this.control_mutex) {
                        Set<ModelChannelMixer> set2 = this.stoppedMixers;
                        if (set2 != null && set2.contains(softChannelMixerContainer)) {
                            this.stoppedMixers.remove(softChannelMixerContainer);
                            softChannelMixerContainer.mixer.stop();
                        }
                    }
                }
                i7++;
                softAudioBuffer7 = softAudioBuffer8;
                softAudioBuffer2 = softAudioBuffer;
                i2 = 0;
                i4 = 1;
            }
            SoftAudioBuffer[] softAudioBufferArr11 = this.buffers;
            softAudioBufferArr11[0] = softAudioBuffer2;
            softAudioBufferArr11[1] = softAudioBuffer3;
            softAudioBufferArr11[c] = softAudioBuffer4;
            softAudioBufferArr11[3] = softAudioBuffer5;
            softAudioBufferArr11[4] = softAudioBuffer6;
            softAudioBufferArr11[5] = softAudioBuffer7;
        } else {
            i = 5;
            d3 = 1.0d;
        }
        int i18 = 0;
        while (true) {
            SoftVoice[] softVoiceArr3 = this.voicestatus;
            if (i18 >= softVoiceArr3.length) {
                break;
            }
            if (softVoiceArr3[i18].active && this.voicestatus[i18].channelmixer == null) {
                this.voicestatus[i18].processAudioLogic(this.buffers);
            }
            i18++;
        }
        if (!this.buffers[c].isSilent()) {
            float[] array4 = this.buffers[c].array();
            float[] array5 = this.buffers[0].array();
            int size3 = this.buffers[0].getSize();
            if (this.nrofchannels != 1) {
                float[] array6 = this.buffers[1].array();
                for (int i19 = 0; i19 < size3; i19++) {
                    float f2 = array4[i19];
                    array5[i19] = array5[i19] + f2;
                    array6[i19] = array6[i19] + f2;
                }
            } else {
                for (int i20 = 0; i20 < size3; i20++) {
                    array5[i20] = array5[i20] + array4[i20];
                }
            }
        }
        if (this.synth.chorus_on) {
            this.chorus.processAudio();
        }
        if (this.synth.reverb_on) {
            this.reverb.processAudio();
        }
        if (this.nrofchannels == 1) {
            d = (d + d2) / 2.0d;
        }
        if (this.last_volume_left != d || this.last_volume_right != d2) {
            float[] array7 = this.buffers[0].array();
            float[] array8 = this.buffers[1].array();
            int size4 = this.buffers[0].getSize();
            double d5 = this.last_volume_left;
            float f3 = (float) (d5 * d5);
            float f4 = (float) (((d * d) - ((double) f3)) / ((double) size4));
            for (int i21 = 0; i21 < size4; i21++) {
                f3 += f4;
                array7[i21] = array7[i21] * f3;
            }
            if (this.nrofchannels != 1) {
                for (int i22 = 0; i22 < size4; i22++) {
                    array8[i22] = (float) (((double) array8[i22]) * d2);
                }
            }
            this.last_volume_left = d;
            this.last_volume_right = d2;
        } else if (!(d == d3 && d2 == d3)) {
            float[] array9 = this.buffers[0].array();
            float[] array10 = this.buffers[1].array();
            int size5 = this.buffers[0].getSize();
            float f5 = (float) (d * d);
            for (int i23 = 0; i23 < size5; i23++) {
                array9[i23] = array9[i23] * f5;
            }
            if (this.nrofchannels != 1) {
                float f6 = (float) (d2 * d2);
                for (int i24 = 0; i24 < size5; i24++) {
                    array10[i24] = array10[i24] * f6;
                }
            }
        }
        if (!this.buffers[0].isSilent() || !this.buffers[1].isSilent()) {
            this.pusher_silent_count = 0;
        } else {
            synchronized (this.control_mutex) {
                size = this.midimessages.size();
            }
            if (size == 0) {
                int i25 = this.pusher_silent_count + 1;
                this.pusher_silent_count = i25;
                if (i25 > i) {
                    this.pusher_silent_count = 0;
                    synchronized (this.control_mutex) {
                        this.pusher_silent = true;
                        if (this.synth.weakstream != null) {
                            this.synth.weakstream.setInputStream((AudioInputStream) null);
                        }
                    }
                }
            }
        }
        if (this.synth.agc_on) {
            this.agc.processAudio();
        }
    }

    public void activity() {
        long j;
        if (this.pusher_silent) {
            this.pusher_silent = false;
            if (this.synth.weakstream != null) {
                this.synth.weakstream.setInputStream(this.ais);
                j = this.synth.weakstream.silent_samples;
                this.msec_last_activity = (long) (((double) (this.sample_pos + j)) * (1000000.0d / ((double) this.samplerate)));
            }
        }
        j = 0;
        this.msec_last_activity = (long) (((double) (this.sample_pos + j)) * (1000000.0d / ((double) this.samplerate)));
    }

    public void stopMixer(ModelChannelMixer modelChannelMixer) {
        if (this.stoppedMixers == null) {
            this.stoppedMixers = new HashSet();
        }
        this.stoppedMixers.add(modelChannelMixer);
    }

    public void registerMixer(ModelChannelMixer modelChannelMixer) {
        if (this.registeredMixers == null) {
            this.registeredMixers = new HashSet();
        }
        SoftChannelMixerContainer softChannelMixerContainer = new SoftChannelMixerContainer();
        softChannelMixerContainer.buffers = new SoftAudioBuffer[6];
        for (int i = 0; i < softChannelMixerContainer.buffers.length; i++) {
            softChannelMixerContainer.buffers[i] = new SoftAudioBuffer(this.buffer_len, this.synth.getFormat());
        }
        softChannelMixerContainer.mixer = modelChannelMixer;
        this.registeredMixers.add(softChannelMixerContainer);
        this.cur_registeredMixers = null;
    }

    public SoftMainMixer(SoftSynthesizer softSynthesizer) {
        this.synth = softSynthesizer;
        this.sample_pos = 0;
        this.co_master_balance[0] = 0.5d;
        this.co_master_volume[0] = 1.0d;
        this.co_master_coarse_tuning[0] = 0.5d;
        this.co_master_fine_tuning[0] = 0.5d;
        this.msec_buffer_len = (long) (1000000.0d / ((double) softSynthesizer.getControlRate()));
        this.samplerate = softSynthesizer.getFormat().getSampleRate();
        this.nrofchannels = softSynthesizer.getFormat().getChannels();
        int sampleRate = (int) (softSynthesizer.getFormat().getSampleRate() / softSynthesizer.getControlRate());
        this.buffer_len = sampleRate;
        this.max_delay_midievent = sampleRate;
        this.control_mutex = softSynthesizer.control_mutex;
        this.buffers = new SoftAudioBuffer[14];
        int i = 0;
        while (true) {
            SoftAudioBuffer[] softAudioBufferArr = this.buffers;
            if (i >= softAudioBufferArr.length) {
                break;
            }
            softAudioBufferArr[i] = new SoftAudioBuffer(sampleRate, softSynthesizer.getFormat());
            i++;
        }
        this.voicestatus = softSynthesizer.getVoices();
        this.reverb = new SoftReverb();
        this.chorus = new SoftChorus();
        this.agc = new SoftLimiter();
        float sampleRate2 = softSynthesizer.getFormat().getSampleRate();
        float controlRate = softSynthesizer.getControlRate();
        this.reverb.init(sampleRate2, controlRate);
        this.chorus.init(sampleRate2, controlRate);
        this.agc.init(sampleRate2, controlRate);
        this.reverb.setLightMode(softSynthesizer.reverb_light);
        this.reverb.setMixMode(true);
        this.chorus.setMixMode(true);
        this.agc.setMixMode(false);
        this.chorus.setInput(0, this.buffers[7]);
        this.chorus.setOutput(0, this.buffers[0]);
        if (this.nrofchannels != 1) {
            this.chorus.setOutput(1, this.buffers[1]);
        }
        this.chorus.setOutput(2, this.buffers[6]);
        this.reverb.setInput(0, this.buffers[6]);
        this.reverb.setOutput(0, this.buffers[0]);
        if (this.nrofchannels != 1) {
            this.reverb.setOutput(1, this.buffers[1]);
        }
        this.agc.setInput(0, this.buffers[0]);
        if (this.nrofchannels != 1) {
            this.agc.setInput(1, this.buffers[1]);
        }
        this.agc.setOutput(0, this.buffers[0]);
        if (this.nrofchannels != 1) {
            this.agc.setOutput(1, this.buffers[1]);
        }
        this.ais = new AudioInputStream(new InputStream() {
            private byte[] bbuffer;
            private int bbuffer_pos = 0;
            private SoftAudioBuffer[] buffers;
            private int buffersize;
            private int nrofchannels;
            private byte[] single = new byte[1];

            {
                this.buffers = SoftMainMixer.this.buffers;
                this.nrofchannels = SoftMainMixer.this.synth.getFormat().getChannels();
                int size = this.buffers[0].getSize();
                this.buffersize = size;
                this.bbuffer = new byte[(size * (SoftMainMixer.this.synth.getFormat().getSampleSizeInBits() / 8) * this.nrofchannels)];
            }

            public void fillBuffer() {
                SoftMainMixer.this.processAudioBuffers();
                for (int i = 0; i < this.nrofchannels; i++) {
                    this.buffers[i].get(this.bbuffer, i);
                }
                this.bbuffer_pos = 0;
            }

            public int read(byte[] bArr, int i, int i2) {
                byte[] bArr2 = this.bbuffer;
                int length = bArr2.length;
                int i3 = i + i2;
                int i4 = i;
                while (i4 < i3) {
                    if (available() == 0) {
                        fillBuffer();
                    } else {
                        int i5 = this.bbuffer_pos;
                        while (i4 < i3 && i5 < length) {
                            bArr[i4] = bArr2[i5];
                            i4++;
                            i5++;
                        }
                        this.bbuffer_pos = i5;
                        if (!SoftMainMixer.this.readfully) {
                            return i4 - i;
                        }
                    }
                }
                return i2;
            }

            public int read() throws IOException {
                if (read(this.single) == -1) {
                    return -1;
                }
                return this.single[0] & 255;
            }

            public int available() {
                return this.bbuffer.length - this.bbuffer_pos;
            }

            public void close() {
                SoftMainMixer.this.synth.close();
            }
        }, softSynthesizer.getFormat(), -1);
    }

    public AudioInputStream getInputStream() {
        return this.ais;
    }

    public void reset() {
        SoftChannel[] softChannelArr = this.synth.channels;
        for (int i = 0; i < softChannelArr.length; i++) {
            softChannelArr[i].allSoundOff();
            softChannelArr[i].resetAllControllers(true);
            if (this.synth.getGeneralMidiMode() != 2) {
                softChannelArr[i].programChange(0, 0);
            } else if (i == 9) {
                softChannelArr[i].programChange(0, 15360);
            } else {
                softChannelArr[i].programChange(0, 15488);
            }
        }
        setVolume(16383);
        setBalance(8192);
        setCoarseTuning(8192);
        setFineTuning(8192);
        globalParameterControlChange(new int[]{129}, new long[]{0}, new long[]{4});
        globalParameterControlChange(new int[]{130}, new long[]{0}, new long[]{2});
    }

    public void setVolume(int i) {
        synchronized (this.control_mutex) {
            this.co_master_volume[0] = ((double) i) / 16384.0d;
        }
    }

    public void setBalance(int i) {
        synchronized (this.control_mutex) {
            this.co_master_balance[0] = ((double) i) / 16384.0d;
        }
    }

    public void setFineTuning(int i) {
        synchronized (this.control_mutex) {
            this.co_master_fine_tuning[0] = ((double) i) / 16384.0d;
        }
    }

    public void setCoarseTuning(int i) {
        synchronized (this.control_mutex) {
            this.co_master_coarse_tuning[0] = ((double) i) / 16384.0d;
        }
    }

    public int getVolume() {
        int i;
        synchronized (this.control_mutex) {
            i = (int) (this.co_master_volume[0] * 16384.0d);
        }
        return i;
    }

    public int getBalance() {
        int i;
        synchronized (this.control_mutex) {
            i = (int) (this.co_master_balance[0] * 16384.0d);
        }
        return i;
    }

    public int getFineTuning() {
        int i;
        synchronized (this.control_mutex) {
            i = (int) (this.co_master_fine_tuning[0] * 16384.0d);
        }
        return i;
    }

    public int getCoarseTuning() {
        int i;
        synchronized (this.control_mutex) {
            i = (int) (this.co_master_coarse_tuning[0] * 16384.0d);
        }
        return i;
    }

    public void globalParameterControlChange(int[] iArr, long[] jArr, long[] jArr2) {
        if (iArr.length != 0) {
            synchronized (this.control_mutex) {
                if (iArr[0] == 129) {
                    for (int i = 0; i < jArr2.length; i++) {
                        this.reverb.globalParameterControlChange(iArr, jArr[i], jArr2[i]);
                    }
                }
                if (iArr[0] == 130) {
                    for (int i2 = 0; i2 < jArr2.length; i2++) {
                        this.chorus.globalParameterControlChange(iArr, jArr[i2], jArr2[i2]);
                    }
                }
            }
        }
    }

    public void processMessage(Object obj) {
        if (obj instanceof byte[]) {
            processMessage((byte[]) obj);
        }
        if (obj instanceof MidiMessage) {
            processMessage((MidiMessage) obj);
        }
    }

    public void processMessage(MidiMessage midiMessage) {
        if (midiMessage instanceof ShortMessage) {
            ShortMessage shortMessage = (ShortMessage) midiMessage;
            processMessage(shortMessage.getChannel(), shortMessage.getCommand(), shortMessage.getData1(), shortMessage.getData2());
            return;
        }
        processMessage(midiMessage.getMessage());
    }

    public void processMessage(byte[] bArr) {
        byte b = 0;
        byte b2 = bArr.length > 0 ? bArr[0] & 255 : 0;
        if (b2 == 240) {
            processSystemExclusiveMessage(bArr);
            return;
        }
        byte b3 = b2 & 240;
        byte b4 = b2 & Ascii.SI;
        byte b5 = bArr.length > 1 ? bArr[1] & 255 : 0;
        if (bArr.length > 2) {
            b = bArr[2] & 255;
        }
        processMessage(b4, b3, b5, b);
    }

    public void processMessage(int i, int i2, int i3, int i4) {
        synchronized (this.synth.control_mutex) {
            activity();
        }
        if (i2 != 240) {
            SoftChannel[] softChannelArr = this.synth.channels;
            if (i < softChannelArr.length) {
                SoftChannel softChannel = softChannelArr[i];
                if (i2 == 128) {
                    softChannel.noteOff(i3, i4);
                } else if (i2 == 144) {
                    int i5 = this.delay_midievent;
                    if (i5 != 0) {
                        softChannel.noteOn(i3, i4, i5);
                    } else {
                        softChannel.noteOn(i3, i4);
                    }
                } else if (i2 == 160) {
                    softChannel.setPolyPressure(i3, i4);
                } else if (i2 == 176) {
                    softChannel.controlChange(i3, i4);
                } else if (i2 == 192) {
                    softChannel.programChange(i3);
                } else if (i2 == 208) {
                    softChannel.setChannelPressure(i3);
                } else if (i2 == 224) {
                    softChannel.setPitchBend(i3 + (i4 * 128));
                }
            }
        } else if ((i | i2) == 254) {
            synchronized (this.synth.control_mutex) {
                this.active_sensing_on = true;
            }
        }
    }

    public long getMicrosecondPosition() {
        double d;
        float f;
        if (!this.pusher_silent || this.synth.weakstream == null) {
            d = (double) this.sample_pos;
            f = this.samplerate;
        } else {
            d = (double) (this.sample_pos + this.synth.weakstream.silent_samples);
            f = this.samplerate;
        }
        return (long) (d * (1000000.0d / ((double) f)));
    }
}
