package cn.sherlock.com.sun.media.sound;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.Patch;

public class SoftChannel implements MidiChannel, ModelDirectedPlayer {
    private static final int RPN_NULL_VALUE = 16383;
    private static boolean[] dontResetControls = new boolean[128];
    private int bank;
    protected ModelConnectionBlock[] cds_channelpressure_connections = null;
    protected ModelConnectionBlock[] cds_control_connections = null;
    protected int cds_control_number = -1;
    protected ModelConnectionBlock[] cds_polypressure_connections = null;
    private int channel;
    private int channelpressure = 0;
    private SoftControl[] co_midi = new SoftControl[128];
    private SoftControl co_midi_cc;
    /* access modifiers changed from: private */
    public double[][] co_midi_cc_cc;
    /* access modifiers changed from: private */
    public double[] co_midi_channel_pressure = new double[1];
    private SoftControl co_midi_nrpn;
    Map<Integer, double[]> co_midi_nrpn_nrpn;
    Map<Integer, int[]> co_midi_nrpn_nrpn_i;
    /* access modifiers changed from: private */
    public double[] co_midi_pitch = new double[1];
    private SoftControl co_midi_rpn;
    Map<Integer, double[]> co_midi_rpn_rpn;
    Map<Integer, int[]> co_midi_rpn_rpn_i;
    private Object control_mutex;
    private int[] controller = new int[128];
    protected ModelDirector current_director = null;
    protected SoftInstrument current_instrument = null;
    protected ModelChannelMixer current_mixer = null;
    private boolean firstVoice;
    protected boolean[][] keybasedcontroller_active = null;
    protected double[][] keybasedcontroller_value = null;
    private int[] lastVelocity;
    private SoftMainMixer mainmixer;
    private boolean mono = false;
    private boolean mute = false;
    private int nrpn_control = RPN_NULL_VALUE;
    private int pitchbend;
    private int play_delay;
    private int play_noteNumber;
    private boolean play_releasetriggered;
    private int play_velocity;
    private int[] polypressure = new int[128];
    private boolean portamento = false;
    protected int[] portamento_lastnote = new int[128];
    protected int portamento_lastnote_ix = 0;
    protected double portamento_time = 1.0d;
    private int prevVoiceID;
    private int program;
    private int rpn_control = RPN_NULL_VALUE;
    private boolean solo = false;
    private boolean solomute = false;
    protected boolean sustain = false;
    private SoftSynthesizer synthesizer;
    protected SoftTuning tuning = new SoftTuning();
    protected int tuning_bank = 0;
    protected int tuning_program = 0;
    private int voiceNo;
    private SoftVoice[] voices;

    private static int restrict14Bit(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 16256) {
            return 16256;
        }
        return i;
    }

    private static int restrict7Bit(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 127) {
            return 127;
        }
        return i;
    }

    public boolean getOmni() {
        return false;
    }

    public boolean localControl(boolean z) {
        return false;
    }

    static {
        int i = 0;
        while (true) {
            boolean[] zArr = dontResetControls;
            if (i < zArr.length) {
                zArr[i] = false;
                i++;
            } else {
                zArr[0] = true;
                zArr[32] = true;
                zArr[7] = true;
                zArr[8] = true;
                zArr[10] = true;
                zArr[11] = true;
                zArr[91] = true;
                zArr[92] = true;
                zArr[93] = true;
                zArr[94] = true;
                zArr[95] = true;
                zArr[70] = true;
                zArr[71] = true;
                zArr[72] = true;
                zArr[73] = true;
                zArr[74] = true;
                zArr[75] = true;
                zArr[76] = true;
                zArr[77] = true;
                zArr[78] = true;
                zArr[79] = true;
                zArr[120] = true;
                zArr[121] = true;
                zArr[122] = true;
                zArr[123] = true;
                zArr[124] = true;
                zArr[125] = true;
                zArr[126] = true;
                zArr[127] = true;
                zArr[6] = true;
                zArr[38] = true;
                zArr[96] = true;
                zArr[97] = true;
                zArr[98] = true;
                zArr[99] = true;
                zArr[100] = true;
                zArr[101] = true;
                return;
            }
        }
    }

    private class MidiControlObject implements SoftControl {
        double[] channel_pressure;
        double[] pitch;
        double[] poly_pressure;

        private MidiControlObject() {
            this.pitch = SoftChannel.this.co_midi_pitch;
            this.channel_pressure = SoftChannel.this.co_midi_channel_pressure;
            this.poly_pressure = new double[1];
        }

        public double[] get(int i, String str) {
            if (str == null) {
                return null;
            }
            if (str.equals("pitch")) {
                return this.pitch;
            }
            if (str.equals("channel_pressure")) {
                return this.channel_pressure;
            }
            if (str.equals("poly_pressure")) {
                return this.poly_pressure;
            }
            return null;
        }
    }

    public SoftChannel(SoftSynthesizer softSynthesizer, int i) {
        int i2 = 0;
        while (true) {
            SoftControl[] softControlArr = this.co_midi;
            if (i2 < softControlArr.length) {
                softControlArr[i2] = new MidiControlObject();
                i2++;
            } else {
                int[] iArr = new int[2];
                iArr[1] = 1;
                iArr[0] = 128;
                this.co_midi_cc_cc = (double[][]) Array.newInstance(Double.TYPE, iArr);
                this.co_midi_cc = new SoftControl() {
                    double[][] cc;

                    {
                        this.cc = SoftChannel.this.co_midi_cc_cc;
                    }

                    public double[] get(int i, String str) {
                        if (str == null) {
                            return null;
                        }
                        return this.cc[Integer.parseInt(str)];
                    }
                };
                this.co_midi_rpn_rpn_i = new HashMap();
                this.co_midi_rpn_rpn = new HashMap();
                this.co_midi_rpn = new SoftControl() {
                    Map<Integer, double[]> rpn;

                    {
                        this.rpn = SoftChannel.this.co_midi_rpn_rpn;
                    }

                    public double[] get(int i, String str) {
                        if (str == null) {
                            return null;
                        }
                        int parseInt = Integer.parseInt(str);
                        double[] dArr = this.rpn.get(Integer.valueOf(parseInt));
                        if (dArr != null) {
                            return dArr;
                        }
                        double[] dArr2 = new double[1];
                        this.rpn.put(Integer.valueOf(parseInt), dArr2);
                        return dArr2;
                    }
                };
                this.co_midi_nrpn_nrpn_i = new HashMap();
                this.co_midi_nrpn_nrpn = new HashMap();
                this.co_midi_nrpn = new SoftControl() {
                    Map<Integer, double[]> nrpn;

                    {
                        this.nrpn = SoftChannel.this.co_midi_nrpn_nrpn;
                    }

                    public double[] get(int i, String str) {
                        if (str == null) {
                            return null;
                        }
                        int parseInt = Integer.parseInt(str);
                        double[] dArr = this.nrpn.get(Integer.valueOf(parseInt));
                        if (dArr != null) {
                            return dArr;
                        }
                        double[] dArr2 = new double[1];
                        this.nrpn.put(Integer.valueOf(parseInt), dArr2);
                        return dArr2;
                    }
                };
                this.lastVelocity = new int[128];
                this.firstVoice = true;
                this.voiceNo = 0;
                this.play_noteNumber = 0;
                this.play_velocity = 0;
                this.play_delay = 0;
                this.play_releasetriggered = false;
                this.channel = i;
                this.voices = softSynthesizer.getVoices();
                this.synthesizer = softSynthesizer;
                this.mainmixer = softSynthesizer.getMainMixer();
                this.control_mutex = softSynthesizer.control_mutex;
                resetAllControllers(true);
                return;
            }
        }
    }

    private int findFreeVoice(int i) {
        if (i == -1) {
            return -1;
        }
        while (true) {
            SoftVoice[] softVoiceArr = this.voices;
            if (i >= softVoiceArr.length) {
                SoftVoice softVoice = null;
                int i2 = 0;
                if (this.synthesizer.getVoiceAllocationMode() == 1) {
                    int i3 = this.channel;
                    int i4 = 0;
                    while (true) {
                        SoftVoice[] softVoiceArr2 = this.voices;
                        if (i4 >= softVoiceArr2.length) {
                            break;
                        }
                        if (softVoiceArr2[i4].stealer_channel == null) {
                            if (i3 == 9) {
                                i3 = this.voices[i4].channel;
                            } else if (this.voices[i4].channel != 9 && this.voices[i4].channel > i3) {
                                i3 = this.voices[i4].channel;
                            }
                        }
                        i4++;
                    }
                    int i5 = -1;
                    int i6 = 0;
                    while (true) {
                        SoftVoice[] softVoiceArr3 = this.voices;
                        if (i6 >= softVoiceArr3.length) {
                            break;
                        }
                        if (softVoiceArr3[i6].channel == i3 && this.voices[i6].stealer_channel == null && !this.voices[i6].on) {
                            if (softVoice == null) {
                                softVoice = this.voices[i6];
                                i5 = i6;
                            }
                            if (this.voices[i6].voiceID < softVoice.voiceID) {
                                softVoice = this.voices[i6];
                                i5 = i6;
                            }
                        }
                        i6++;
                    }
                    if (i5 == -1) {
                        while (true) {
                            SoftVoice[] softVoiceArr4 = this.voices;
                            if (i2 >= softVoiceArr4.length) {
                                break;
                            }
                            if (softVoiceArr4[i2].channel == i3 && this.voices[i2].stealer_channel == null) {
                                if (softVoice == null) {
                                    softVoice = this.voices[i2];
                                    i5 = i2;
                                }
                                if (this.voices[i2].voiceID < softVoice.voiceID) {
                                    softVoice = this.voices[i2];
                                    i5 = i2;
                                }
                            }
                            i2++;
                        }
                    }
                    return i5;
                }
                int i7 = -1;
                int i8 = 0;
                while (true) {
                    SoftVoice[] softVoiceArr5 = this.voices;
                    if (i8 >= softVoiceArr5.length) {
                        break;
                    }
                    if (softVoiceArr5[i8].stealer_channel == null && !this.voices[i8].on) {
                        if (softVoice == null) {
                            softVoice = this.voices[i8];
                            i7 = i8;
                        }
                        if (this.voices[i8].voiceID < softVoice.voiceID) {
                            softVoice = this.voices[i8];
                            i7 = i8;
                        }
                    }
                    i8++;
                }
                if (i7 == -1) {
                    while (true) {
                        SoftVoice[] softVoiceArr6 = this.voices;
                        if (i2 >= softVoiceArr6.length) {
                            break;
                        }
                        if (softVoiceArr6[i2].stealer_channel == null) {
                            if (softVoice == null) {
                                softVoice = this.voices[i2];
                                i7 = i2;
                            }
                            if (this.voices[i2].voiceID < softVoice.voiceID) {
                                softVoice = this.voices[i2];
                                i7 = i2;
                            }
                        }
                        i2++;
                    }
                }
                return i7;
            } else if (!softVoiceArr[i].active) {
                return i;
            } else {
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initVoice(SoftVoice softVoice, SoftPerformer softPerformer, int i, int i2, int i3, int i4, ModelConnectionBlock[] modelConnectionBlockArr, ModelChannelMixer modelChannelMixer, boolean z) {
        int i5 = 0;
        if (softVoice.active) {
            softVoice.stealer_channel = this;
            softVoice.stealer_performer = softPerformer;
            softVoice.stealer_voiceID = i;
            softVoice.stealer_noteNumber = i2;
            softVoice.stealer_velocity = i3;
            softVoice.stealer_extendedConnectionBlocks = modelConnectionBlockArr;
            softVoice.stealer_channelmixer = modelChannelMixer;
            softVoice.stealer_releaseTriggered = z;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i5 < softVoiceArr.length) {
                    if (softVoiceArr[i5].active && this.voices[i5].voiceID == softVoice.voiceID) {
                        this.voices[i5].soundOff();
                    }
                    i5++;
                } else {
                    return;
                }
            }
        } else {
            softVoice.extendedConnectionBlocks = modelConnectionBlockArr;
            softVoice.channelmixer = modelChannelMixer;
            softVoice.releaseTriggered = z;
            softVoice.voiceID = i;
            softVoice.tuning = this.tuning;
            softVoice.exclusiveClass = softPerformer.exclusiveClass;
            softVoice.softchannel = this;
            softVoice.channel = this.channel;
            softVoice.bank = this.bank;
            softVoice.program = this.program;
            softVoice.instrument = this.current_instrument;
            softVoice.performer = softPerformer;
            softVoice.objects.clear();
            softVoice.objects.put("midi", this.co_midi[i2]);
            softVoice.objects.put("midi_cc", this.co_midi_cc);
            softVoice.objects.put("midi_rpn", this.co_midi_rpn);
            softVoice.objects.put("midi_nrpn", this.co_midi_nrpn);
            softVoice.noteOn(i2, i3, i4);
            softVoice.setMute(this.mute);
            softVoice.setSoloMute(this.solomute);
            if (!z) {
                if (this.controller[84] != 0) {
                    softVoice.co_noteon_keynumber[0] = (this.tuning.getTuning(this.controller[84]) / 100.0d) * 0.0078125d;
                    softVoice.portamento = true;
                    controlChange(84, 0);
                } else if (!this.portamento) {
                } else {
                    if (this.mono) {
                        if (this.portamento_lastnote[0] != -1) {
                            softVoice.co_noteon_keynumber[0] = (this.tuning.getTuning(this.portamento_lastnote[0]) / 100.0d) * 0.0078125d;
                            softVoice.portamento = true;
                            controlChange(84, 0);
                        }
                        this.portamento_lastnote[0] = i2;
                        return;
                    }
                    int i6 = this.portamento_lastnote_ix;
                    if (i6 != 0) {
                        this.portamento_lastnote_ix = i6 - 1;
                        softVoice.co_noteon_keynumber[0] = (this.tuning.getTuning(this.portamento_lastnote[this.portamento_lastnote_ix]) / 100.0d) * 0.0078125d;
                        softVoice.portamento = true;
                    }
                }
            }
        }
    }

    public void noteOn(int i, int i2) {
        noteOn(i, i2, 0);
    }

    /* access modifiers changed from: protected */
    public void noteOn(int i, int i2, int i3) {
        int restrict7Bit = restrict7Bit(i);
        int restrict7Bit2 = restrict7Bit(i2);
        noteOn_internal(restrict7Bit, restrict7Bit2, i3);
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.noteOn(restrict7Bit, restrict7Bit2);
        }
    }

    private void noteOn_internal(int i, int i2, int i3) {
        if (i2 == 0) {
            noteOff_internal(i, 64);
            return;
        }
        synchronized (this.control_mutex) {
            if (this.sustain) {
                this.sustain = false;
                int i4 = 0;
                while (true) {
                    SoftVoice[] softVoiceArr = this.voices;
                    if (i4 >= softVoiceArr.length) {
                        break;
                    }
                    if ((softVoiceArr[i4].sustain || this.voices[i4].on) && this.voices[i4].channel == this.channel && this.voices[i4].active && this.voices[i4].note == i) {
                        this.voices[i4].sustain = false;
                        this.voices[i4].on = true;
                        this.voices[i4].noteOff(0);
                    }
                    i4++;
                }
                this.sustain = true;
            }
            this.mainmixer.activity();
            if (this.mono) {
                if (this.portamento) {
                    int i5 = 0;
                    boolean z = false;
                    while (true) {
                        SoftVoice[] softVoiceArr2 = this.voices;
                        if (i5 >= softVoiceArr2.length) {
                            break;
                        }
                        if (softVoiceArr2[i5].on && this.voices[i5].channel == this.channel && this.voices[i5].active && !this.voices[i5].releaseTriggered) {
                            this.voices[i5].portamento = true;
                            this.voices[i5].setNote(i);
                            z = true;
                        }
                        i5++;
                    }
                    if (z) {
                        this.portamento_lastnote[0] = i;
                        return;
                    }
                }
                if (this.controller[84] != 0) {
                    int i6 = 0;
                    boolean z2 = false;
                    while (true) {
                        SoftVoice[] softVoiceArr3 = this.voices;
                        if (i6 >= softVoiceArr3.length) {
                            break;
                        }
                        if (softVoiceArr3[i6].on && this.voices[i6].channel == this.channel && this.voices[i6].active && this.voices[i6].note == this.controller[84] && !this.voices[i6].releaseTriggered) {
                            this.voices[i6].portamento = true;
                            this.voices[i6].setNote(i);
                            z2 = true;
                        }
                        i6++;
                    }
                    controlChange(84, 0);
                    if (z2) {
                        return;
                    }
                }
            }
            if (this.mono) {
                allNotesOff();
            }
            if (this.current_instrument == null) {
                SoftInstrument findInstrument = this.synthesizer.findInstrument(this.program, this.bank, this.channel);
                this.current_instrument = findInstrument;
                if (findInstrument != null) {
                    ModelChannelMixer modelChannelMixer = this.current_mixer;
                    if (modelChannelMixer != null) {
                        this.mainmixer.stopMixer(modelChannelMixer);
                    }
                    ModelChannelMixer channelMixer = this.current_instrument.getSourceInstrument().getChannelMixer(this, this.synthesizer.getFormat());
                    this.current_mixer = channelMixer;
                    if (channelMixer != null) {
                        this.mainmixer.registerMixer(channelMixer);
                    }
                    this.current_director = this.current_instrument.getDirector(this, this);
                    applyInstrumentCustomization();
                } else {
                    return;
                }
            }
            SoftSynthesizer softSynthesizer = this.synthesizer;
            int i7 = softSynthesizer.voiceIDCounter;
            softSynthesizer.voiceIDCounter = i7 + 1;
            this.prevVoiceID = i7;
            this.firstVoice = true;
            this.voiceNo = 0;
            this.play_noteNumber = i;
            this.play_velocity = i2;
            this.play_delay = i3;
            this.play_releasetriggered = false;
            this.lastVelocity[i] = i2;
            this.current_director.noteOn((int) Math.round(this.tuning.getTuning()[i] / 100.0d), i2);
        }
    }

    public void noteOff(int i, int i2) {
        int restrict7Bit = restrict7Bit(i);
        int restrict7Bit2 = restrict7Bit(i2);
        noteOff_internal(restrict7Bit, restrict7Bit2);
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.noteOff(restrict7Bit, restrict7Bit2);
        }
    }

    private void noteOff_internal(int i, int i2) {
        int i3;
        synchronized (this.control_mutex) {
            if (!this.mono && this.portamento && (i3 = this.portamento_lastnote_ix) != 127) {
                this.portamento_lastnote[i3] = i;
                this.portamento_lastnote_ix = i3 + 1;
            }
            this.mainmixer.activity();
            int i4 = 0;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i4 >= softVoiceArr.length) {
                    break;
                }
                if (softVoiceArr[i4].on && this.voices[i4].channel == this.channel && this.voices[i4].note == i && !this.voices[i4].releaseTriggered) {
                    this.voices[i4].noteOff(i2);
                }
                if (this.voices[i4].stealer_channel == this && this.voices[i4].stealer_noteNumber == i) {
                    SoftVoice softVoice = this.voices[i4];
                    softVoice.stealer_releaseTriggered = false;
                    softVoice.stealer_channel = null;
                    softVoice.stealer_performer = null;
                    softVoice.stealer_voiceID = -1;
                    softVoice.stealer_noteNumber = 0;
                    softVoice.stealer_velocity = 0;
                    softVoice.stealer_extendedConnectionBlocks = null;
                    softVoice.stealer_channelmixer = null;
                }
                i4++;
            }
            if (this.current_instrument == null) {
                SoftInstrument findInstrument = this.synthesizer.findInstrument(this.program, this.bank, this.channel);
                this.current_instrument = findInstrument;
                if (findInstrument != null) {
                    ModelChannelMixer modelChannelMixer = this.current_mixer;
                    if (modelChannelMixer != null) {
                        this.mainmixer.stopMixer(modelChannelMixer);
                    }
                    ModelChannelMixer channelMixer = this.current_instrument.getSourceInstrument().getChannelMixer(this, this.synthesizer.getFormat());
                    this.current_mixer = channelMixer;
                    if (channelMixer != null) {
                        this.mainmixer.registerMixer(channelMixer);
                    }
                    this.current_director = this.current_instrument.getDirector(this, this);
                    applyInstrumentCustomization();
                } else {
                    return;
                }
            }
            SoftSynthesizer softSynthesizer = this.synthesizer;
            int i5 = softSynthesizer.voiceIDCounter;
            softSynthesizer.voiceIDCounter = i5 + 1;
            this.prevVoiceID = i5;
            this.firstVoice = true;
            this.voiceNo = 0;
            this.play_noteNumber = i;
            this.play_velocity = this.lastVelocity[i];
            this.play_releasetriggered = true;
            this.play_delay = 0;
            this.current_director.noteOff((int) Math.round(this.tuning.getTuning()[i] / 100.0d), i2);
        }
    }

    public void play(int i, ModelConnectionBlock[] modelConnectionBlockArr) {
        int i2 = this.play_noteNumber;
        int i3 = this.play_velocity;
        int i4 = this.play_delay;
        boolean z = this.play_releasetriggered;
        SoftPerformer softPerformer = this.current_instrument.getPerformers()[i];
        if (this.firstVoice) {
            int i5 = 0;
            this.firstVoice = false;
            if (softPerformer.exclusiveClass != 0) {
                int i6 = softPerformer.exclusiveClass;
                while (true) {
                    SoftVoice[] softVoiceArr = this.voices;
                    if (i5 >= softVoiceArr.length) {
                        break;
                    }
                    if (softVoiceArr[i5].active && this.voices[i5].channel == this.channel && this.voices[i5].exclusiveClass == i6 && (!softPerformer.selfNonExclusive || this.voices[i5].note != i2)) {
                        this.voices[i5].shutdown();
                    }
                    i5++;
                }
            }
        }
        int findFreeVoice = findFreeVoice(this.voiceNo);
        this.voiceNo = findFreeVoice;
        if (findFreeVoice != -1) {
            initVoice(this.voices[findFreeVoice], softPerformer, this.prevVoiceID, i2, i3, i4, modelConnectionBlockArr, this.current_mixer, z);
        }
    }

    public void noteOff(int i) {
        if (i >= 0 && i <= 127) {
            noteOff_internal(i, 64);
        }
    }

    public void setPolyPressure(int i, int i2) {
        int restrict7Bit = restrict7Bit(i);
        int restrict7Bit2 = restrict7Bit(i2);
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setPolyPressure(restrict7Bit, restrict7Bit2);
        }
        synchronized (this.control_mutex) {
            this.mainmixer.activity();
            int i3 = 0;
            this.co_midi[restrict7Bit].get(0, "poly_pressure")[0] = ((double) restrict7Bit2) * 0.0078125d;
            this.polypressure[restrict7Bit] = restrict7Bit2;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i3 < softVoiceArr.length) {
                    if (softVoiceArr[i3].active && this.voices[i3].note == restrict7Bit) {
                        this.voices[i3].setPolyPressure(restrict7Bit2);
                    }
                    i3++;
                }
            }
        }
    }

    public int getPolyPressure(int i) {
        int i2;
        synchronized (this.control_mutex) {
            i2 = this.polypressure[i];
        }
        return i2;
    }

    public void setChannelPressure(int i) {
        int restrict7Bit = restrict7Bit(i);
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setChannelPressure(restrict7Bit);
        }
        synchronized (this.control_mutex) {
            this.mainmixer.activity();
            int i2 = 0;
            this.co_midi_channel_pressure[0] = ((double) restrict7Bit) * 0.0078125d;
            this.channelpressure = restrict7Bit;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i2 < softVoiceArr.length) {
                    if (softVoiceArr[i2].active) {
                        this.voices[i2].setChannelPressure(restrict7Bit);
                    }
                    i2++;
                }
            }
        }
    }

    public int getChannelPressure() {
        int i;
        synchronized (this.control_mutex) {
            i = this.channelpressure;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void applyInstrumentCustomization() {
        boolean z;
        boolean z2;
        boolean z3;
        if (this.cds_control_connections != null || this.cds_channelpressure_connections != null || this.cds_polypressure_connections != null) {
            ModelInstrument sourceInstrument = this.current_instrument.getSourceInstrument();
            ModelPerformer[] performers = sourceInstrument.getPerformers();
            int length = performers.length;
            ModelPerformer[] modelPerformerArr = new ModelPerformer[length];
            for (int i = 0; i < length; i++) {
                ModelPerformer modelPerformer = performers[i];
                ModelPerformer modelPerformer2 = new ModelPerformer();
                modelPerformer2.setName(modelPerformer.getName());
                modelPerformer2.setExclusiveClass(modelPerformer.getExclusiveClass());
                modelPerformer2.setKeyFrom(modelPerformer.getKeyFrom());
                modelPerformer2.setKeyTo(modelPerformer.getKeyTo());
                modelPerformer2.setVelFrom(modelPerformer.getVelFrom());
                modelPerformer2.setVelTo(modelPerformer.getVelTo());
                modelPerformer2.getOscillators().addAll(modelPerformer.getOscillators());
                modelPerformer2.getConnectionBlocks().addAll(modelPerformer.getConnectionBlocks());
                modelPerformerArr[i] = modelPerformer2;
                List<ModelConnectionBlock> connectionBlocks = modelPerformer2.getConnectionBlocks();
                if (this.cds_control_connections != null) {
                    String num = Integer.toString(this.cds_control_number);
                    Iterator<ModelConnectionBlock> it = connectionBlocks.iterator();
                    while (it.hasNext()) {
                        ModelSource[] sources = it.next().getSources();
                        if (sources != null) {
                            z3 = false;
                            for (ModelSource modelSource : sources) {
                                if ("midi_cc".equals(modelSource.getIdentifier().getObject()) && num.equals(modelSource.getIdentifier().getVariable())) {
                                    z3 = true;
                                }
                            }
                        } else {
                            z3 = false;
                        }
                        if (z3) {
                            it.remove();
                        }
                    }
                    int i2 = 0;
                    while (true) {
                        ModelConnectionBlock[] modelConnectionBlockArr = this.cds_control_connections;
                        if (i2 >= modelConnectionBlockArr.length) {
                            break;
                        }
                        connectionBlocks.add(modelConnectionBlockArr[i2]);
                        i2++;
                    }
                }
                if (this.cds_polypressure_connections != null) {
                    Iterator<ModelConnectionBlock> it2 = connectionBlocks.iterator();
                    while (it2.hasNext()) {
                        ModelSource[] sources2 = it2.next().getSources();
                        if (sources2 != null) {
                            z2 = false;
                            for (ModelSource modelSource2 : sources2) {
                                if ("midi".equals(modelSource2.getIdentifier().getObject()) && "poly_pressure".equals(modelSource2.getIdentifier().getVariable())) {
                                    z2 = true;
                                }
                            }
                        } else {
                            z2 = false;
                        }
                        if (z2) {
                            it2.remove();
                        }
                    }
                    int i3 = 0;
                    while (true) {
                        ModelConnectionBlock[] modelConnectionBlockArr2 = this.cds_polypressure_connections;
                        if (i3 >= modelConnectionBlockArr2.length) {
                            break;
                        }
                        connectionBlocks.add(modelConnectionBlockArr2[i3]);
                        i3++;
                    }
                }
                if (this.cds_channelpressure_connections != null) {
                    Iterator<ModelConnectionBlock> it3 = connectionBlocks.iterator();
                    while (it3.hasNext()) {
                        ModelSource[] sources3 = it3.next().getSources();
                        if (sources3 != null) {
                            z = false;
                            for (ModelSource identifier : sources3) {
                                ModelIdentifier identifier2 = identifier.getIdentifier();
                                if ("midi".equals(identifier2.getObject()) && "channel_pressure".equals(identifier2.getVariable())) {
                                    z = true;
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (z) {
                            it3.remove();
                        }
                    }
                    int i4 = 0;
                    while (true) {
                        ModelConnectionBlock[] modelConnectionBlockArr3 = this.cds_channelpressure_connections;
                        if (i4 >= modelConnectionBlockArr3.length) {
                            break;
                        }
                        connectionBlocks.add(modelConnectionBlockArr3[i4]);
                        i4++;
                    }
                }
            }
            this.current_instrument = new SoftInstrument(sourceInstrument, modelPerformerArr);
        }
    }

    private ModelConnectionBlock[] createModelConnections(ModelIdentifier modelIdentifier, int[] iArr, int[] iArr2) {
        double d;
        double d2;
        double d3;
        ModelConnectionBlock modelConnectionBlock;
        ModelIdentifier modelIdentifier2 = modelIdentifier;
        int[] iArr3 = iArr;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iArr3.length; i++) {
            int i2 = iArr3[i];
            int i3 = iArr2[i];
            if (i2 == 0) {
                arrayList.add(new ModelConnectionBlock(new ModelSource(modelIdentifier2, false, false, 0), (double) ((i3 - 64) * 100), new ModelDestination(new ModelIdentifier("osc", "pitch"))));
            }
            if (i2 == 1) {
                d3 = 9600.0d;
                double d4 = ((((double) i3) / 64.0d) - 1.0d) * 9600.0d;
                if (d4 > 0.0d) {
                    d2 = 1.0d;
                    d = 64.0d;
                    modelConnectionBlock = new ModelConnectionBlock(new ModelSource(modelIdentifier2, true, false, 0), -d4, new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ));
                } else {
                    d2 = 1.0d;
                    d = 64.0d;
                    modelConnectionBlock = new ModelConnectionBlock(new ModelSource(modelIdentifier2, false, false, 0), d4, new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ));
                }
                arrayList.add(modelConnectionBlock);
            } else {
                d2 = 1.0d;
                d = 64.0d;
                d3 = 9600.0d;
            }
            if (i2 == 2) {
                arrayList.add(new ModelConnectionBlock(new ModelSource(modelIdentifier2, (ModelTransform) new ModelTransform(((double) i3) / d) {
                    double s;
                    final /* synthetic */ double val$scale;

                    {
                        this.val$scale = r2;
                        this.s = r2;
                    }

                    public double transform(double d) {
                        double d2;
                        double d3 = this.s;
                        if (d3 < 1.0d) {
                            d2 = d3 + (d * (1.0d - d3));
                        } else if (d3 <= 1.0d) {
                            return 0.0d;
                        } else {
                            d2 = (d * (d3 - 1.0d)) + 1.0d;
                        }
                        return (-(0.4166666666666667d / Math.log(10.0d))) * Math.log(d2);
                    }
                }), -960.0d, new ModelDestination(ModelDestination.DESTINATION_GAIN)));
            }
            if (i2 == 3) {
                arrayList.add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, true, 0), new ModelSource(modelIdentifier2, false, false, 0), ((((double) i3) / d) - d2) * d3, new ModelDestination(ModelDestination.DESTINATION_PITCH)));
            }
            if (i2 == 4) {
                arrayList.add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, true, 0), new ModelSource(modelIdentifier2, false, false, 0), (((double) i3) / 128.0d) * 2400.0d, new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ)));
            }
            if (i2 == 5) {
                arrayList.add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, false, 0), new ModelSource(modelIdentifier2, (ModelTransform) new ModelTransform(((double) i3) / 127.0d) {
                    double s;
                    final /* synthetic */ double val$scale;

                    {
                        this.val$scale = r2;
                        this.s = r2;
                    }

                    public double transform(double d) {
                        return (-(0.4166666666666667d / Math.log(10.0d))) * Math.log(1.0d - (d * this.s));
                    }
                }), -960.0d, new ModelDestination(ModelDestination.DESTINATION_GAIN)));
            }
        }
        return (ModelConnectionBlock[]) arrayList.toArray(new ModelConnectionBlock[arrayList.size()]);
    }

    public void mapPolyPressureToDestination(int[] iArr, int[] iArr2) {
        this.current_instrument = null;
        if (iArr.length == 0) {
            this.cds_polypressure_connections = null;
        } else {
            this.cds_polypressure_connections = createModelConnections(new ModelIdentifier("midi", "poly_pressure"), iArr, iArr2);
        }
    }

    public void mapChannelPressureToDestination(int[] iArr, int[] iArr2) {
        this.current_instrument = null;
        if (iArr.length == 0) {
            this.cds_channelpressure_connections = null;
        } else {
            this.cds_channelpressure_connections = createModelConnections(new ModelIdentifier("midi", "channel_pressure"), iArr, iArr2);
        }
    }

    public void mapControlToDestination(int i, int[] iArr, int[] iArr2) {
        if ((i < 1 || i > 31) && (i < 64 || i > 95)) {
            this.cds_control_connections = null;
            return;
        }
        this.current_instrument = null;
        this.cds_control_number = i;
        if (iArr.length == 0) {
            this.cds_control_connections = null;
        } else {
            this.cds_control_connections = createModelConnections(new ModelIdentifier("midi_cc", Integer.toString(i)), iArr, iArr2);
        }
    }

    public void controlChangePerNote(int i, int i2, int i3) {
        if (this.keybasedcontroller_active == null) {
            this.keybasedcontroller_active = new boolean[128][];
            this.keybasedcontroller_value = new double[128][];
        }
        boolean[][] zArr = this.keybasedcontroller_active;
        int i4 = 0;
        if (zArr[i] == null) {
            boolean[] zArr2 = new boolean[128];
            zArr[i] = zArr2;
            Arrays.fill(zArr2, false);
            double[] dArr = new double[128];
            this.keybasedcontroller_value[i] = dArr;
            Arrays.fill(dArr, 0.0d);
        }
        if (i3 == -1) {
            this.keybasedcontroller_active[i][i2] = false;
        } else {
            this.keybasedcontroller_active[i][i2] = true;
            this.keybasedcontroller_value[i][i2] = ((double) i3) / 128.0d;
        }
        if (i2 < 120) {
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i4 < softVoiceArr.length) {
                    if (softVoiceArr[i4].active) {
                        this.voices[i4].controlChange(i2, -1);
                    }
                    i4++;
                } else {
                    return;
                }
            }
        } else if (i2 == 120) {
            while (true) {
                SoftVoice[] softVoiceArr2 = this.voices;
                if (i4 < softVoiceArr2.length) {
                    if (softVoiceArr2[i4].active) {
                        this.voices[i4].rpnChange(1, -1);
                    }
                    i4++;
                } else {
                    return;
                }
            }
        } else if (i2 == 121) {
            while (true) {
                SoftVoice[] softVoiceArr3 = this.voices;
                if (i4 < softVoiceArr3.length) {
                    if (softVoiceArr3[i4].active) {
                        this.voices[i4].rpnChange(2, -1);
                    }
                    i4++;
                } else {
                    return;
                }
            }
        }
    }

    public int getControlPerNote(int i, int i2) {
        boolean[] zArr;
        boolean[][] zArr2 = this.keybasedcontroller_active;
        if (zArr2 == null || (zArr = zArr2[i]) == null || !zArr[i2]) {
            return -1;
        }
        return (int) (this.keybasedcontroller_value[i][i2] * 128.0d);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r5v10 */
    /* JADX WARNING: type inference failed for: r5v12 */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r5v16 */
    /* JADX WARNING: type inference failed for: r5v18 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void controlChange(int r10, int r11) {
        /*
            r9 = this;
            int r10 = restrict7Bit(r10)
            int r11 = restrict7Bit(r11)
            cn.sherlock.com.sun.media.sound.ModelChannelMixer r0 = r9.current_mixer
            if (r0 == 0) goto L_0x000f
            r0.controlChange(r10, r11)
        L_0x000f:
            java.lang.Object r0 = r9.control_mutex
            monitor-enter(r0)
            r1 = 5
            r2 = 0
            if (r10 == r1) goto L_0x01d7
            r1 = 38
            r3 = 6
            r4 = 16383(0x3fff, float:2.2957E-41)
            r5 = 1
            if (r10 == r3) goto L_0x0173
            if (r10 == r1) goto L_0x0173
            r6 = 64
            switch(r10) {
                case 64: goto L_0x010a;
                case 65: goto L_0x00fb;
                case 66: goto L_0x008f;
                default: goto L_0x0025;
            }
        L_0x0025:
            r7 = 127(0x7f, float:1.78E-43)
            switch(r10) {
                case 96: goto L_0x0173;
                case 97: goto L_0x0173;
                case 98: goto L_0x0084;
                case 99: goto L_0x0078;
                case 100: goto L_0x006d;
                case 101: goto L_0x0061;
                default: goto L_0x002a;
            }
        L_0x002a:
            switch(r10) {
                case 120: goto L_0x005c;
                case 121: goto L_0x0053;
                case 122: goto L_0x004a;
                case 123: goto L_0x0045;
                case 124: goto L_0x0040;
                case 125: goto L_0x003b;
                case 126: goto L_0x0034;
                case 127: goto L_0x002f;
                default: goto L_0x002d;
            }
        L_0x002d:
            goto L_0x020c
        L_0x002f:
            r9.setMono(r2)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0034:
            if (r11 != r5) goto L_0x020c
            r9.setMono(r5)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x003b:
            r9.setOmni(r5)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0040:
            r9.setOmni(r2)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0045:
            r9.allNotesOff()     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x004a:
            if (r11 < r6) goto L_0x004d
            goto L_0x004e
        L_0x004d:
            r5 = r2
        L_0x004e:
            r9.localControl(r5)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0053:
            if (r11 != r7) goto L_0x0056
            goto L_0x0057
        L_0x0056:
            r5 = r2
        L_0x0057:
            r9.resetAllControllers(r5)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x005c:
            r9.allSoundOff()     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0061:
            int r1 = r9.rpn_control     // Catch:{ all -> 0x024c }
            r1 = r1 & r7
            int r3 = r11 << 7
            int r1 = r1 + r3
            r9.rpn_control = r1     // Catch:{ all -> 0x024c }
            r9.nrpn_control = r4     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x006d:
            int r1 = r9.rpn_control     // Catch:{ all -> 0x024c }
            r1 = r1 & 16256(0x3f80, float:2.278E-41)
            int r1 = r1 + r11
            r9.rpn_control = r1     // Catch:{ all -> 0x024c }
            r9.nrpn_control = r4     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0078:
            int r1 = r9.nrpn_control     // Catch:{ all -> 0x024c }
            r1 = r1 & r7
            int r3 = r11 << 7
            int r1 = r1 + r3
            r9.nrpn_control = r1     // Catch:{ all -> 0x024c }
            r9.rpn_control = r4     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x0084:
            int r1 = r9.nrpn_control     // Catch:{ all -> 0x024c }
            r1 = r1 & 16256(0x3f80, float:2.278E-41)
            int r1 = r1 + r11
            r9.nrpn_control = r1     // Catch:{ all -> 0x024c }
            r9.rpn_control = r4     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x008f:
            if (r11 < r6) goto L_0x0093
            r1 = r5
            goto L_0x0094
        L_0x0093:
            r1 = r2
        L_0x0094:
            if (r1 == 0) goto L_0x00bd
            r3 = r2
        L_0x0097:
            cn.sherlock.com.sun.media.sound.SoftVoice[] r4 = r9.voices     // Catch:{ all -> 0x024c }
            int r6 = r4.length     // Catch:{ all -> 0x024c }
            if (r3 >= r6) goto L_0x00bd
            r4 = r4[r3]     // Catch:{ all -> 0x024c }
            boolean r4 = r4.active     // Catch:{ all -> 0x024c }
            if (r4 == 0) goto L_0x00ba
            cn.sherlock.com.sun.media.sound.SoftVoice[] r4 = r9.voices     // Catch:{ all -> 0x024c }
            r4 = r4[r3]     // Catch:{ all -> 0x024c }
            boolean r4 = r4.on     // Catch:{ all -> 0x024c }
            if (r4 == 0) goto L_0x00ba
            cn.sherlock.com.sun.media.sound.SoftVoice[] r4 = r9.voices     // Catch:{ all -> 0x024c }
            r4 = r4[r3]     // Catch:{ all -> 0x024c }
            int r4 = r4.channel     // Catch:{ all -> 0x024c }
            int r6 = r9.channel     // Catch:{ all -> 0x024c }
            if (r4 != r6) goto L_0x00ba
            cn.sherlock.com.sun.media.sound.SoftVoice[] r4 = r9.voices     // Catch:{ all -> 0x024c }
            r4 = r4[r3]     // Catch:{ all -> 0x024c }
            r4.sostenuto = r5     // Catch:{ all -> 0x024c }
        L_0x00ba:
            int r3 = r3 + 1
            goto L_0x0097
        L_0x00bd:
            if (r1 != 0) goto L_0x020c
            r1 = r2
        L_0x00c0:
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            int r4 = r3.length     // Catch:{ all -> 0x024c }
            if (r1 >= r4) goto L_0x020c
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.active     // Catch:{ all -> 0x024c }
            if (r3 == 0) goto L_0x00f8
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.sostenuto     // Catch:{ all -> 0x024c }
            if (r3 == 0) goto L_0x00f8
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            int r3 = r3.channel     // Catch:{ all -> 0x024c }
            int r4 = r9.channel     // Catch:{ all -> 0x024c }
            if (r3 != r4) goto L_0x00f8
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.sostenuto = r2     // Catch:{ all -> 0x024c }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.on     // Catch:{ all -> 0x024c }
            if (r3 != 0) goto L_0x00f8
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.on = r5     // Catch:{ all -> 0x024c }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.noteOff(r2)     // Catch:{ all -> 0x024c }
        L_0x00f8:
            int r1 = r1 + 1
            goto L_0x00c0
        L_0x00fb:
            if (r11 < r6) goto L_0x00fe
            goto L_0x00ff
        L_0x00fe:
            r5 = r2
        L_0x00ff:
            r9.portamento = r5     // Catch:{ all -> 0x024c }
            int[] r1 = r9.portamento_lastnote     // Catch:{ all -> 0x024c }
            r3 = -1
            r1[r2] = r3     // Catch:{ all -> 0x024c }
            r9.portamento_lastnote_ix = r2     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x010a:
            if (r11 < r6) goto L_0x010e
            r1 = r5
            goto L_0x010f
        L_0x010e:
            r1 = r2
        L_0x010f:
            boolean r3 = r9.sustain     // Catch:{ all -> 0x024c }
            if (r3 == r1) goto L_0x020c
            r9.sustain = r1     // Catch:{ all -> 0x024c }
            if (r1 != 0) goto L_0x0153
            r1 = r2
        L_0x0118:
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            int r4 = r3.length     // Catch:{ all -> 0x024c }
            if (r1 >= r4) goto L_0x020c
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.active     // Catch:{ all -> 0x024c }
            if (r3 == 0) goto L_0x0150
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.sustain     // Catch:{ all -> 0x024c }
            if (r3 == 0) goto L_0x0150
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            int r3 = r3.channel     // Catch:{ all -> 0x024c }
            int r4 = r9.channel     // Catch:{ all -> 0x024c }
            if (r3 != r4) goto L_0x0150
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.sustain = r2     // Catch:{ all -> 0x024c }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.on     // Catch:{ all -> 0x024c }
            if (r3 != 0) goto L_0x0150
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.on = r5     // Catch:{ all -> 0x024c }
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.noteOff(r2)     // Catch:{ all -> 0x024c }
        L_0x0150:
            int r1 = r1 + 1
            goto L_0x0118
        L_0x0153:
            r1 = r2
        L_0x0154:
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            int r4 = r3.length     // Catch:{ all -> 0x024c }
            if (r1 >= r4) goto L_0x020c
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            boolean r3 = r3.active     // Catch:{ all -> 0x024c }
            if (r3 == 0) goto L_0x0170
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            int r3 = r3.channel     // Catch:{ all -> 0x024c }
            int r4 = r9.channel     // Catch:{ all -> 0x024c }
            if (r3 != r4) goto L_0x0170
            cn.sherlock.com.sun.media.sound.SoftVoice[] r3 = r9.voices     // Catch:{ all -> 0x024c }
            r3 = r3[r1]     // Catch:{ all -> 0x024c }
            r3.redamp()     // Catch:{ all -> 0x024c }
        L_0x0170:
            int r1 = r1 + 1
            goto L_0x0154
        L_0x0173:
            int r6 = r9.nrpn_control     // Catch:{ all -> 0x024c }
            if (r6 == r4) goto L_0x0188
            java.util.Map<java.lang.Integer, int[]> r7 = r9.co_midi_nrpn_nrpn_i     // Catch:{ all -> 0x024c }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x024c }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x024c }
            int[] r6 = (int[]) r6     // Catch:{ all -> 0x024c }
            if (r6 == 0) goto L_0x0188
            r6 = r6[r2]     // Catch:{ all -> 0x024c }
            goto L_0x0189
        L_0x0188:
            r6 = r2
        L_0x0189:
            int r7 = r9.rpn_control     // Catch:{ all -> 0x024c }
            if (r7 == r4) goto L_0x019d
            java.util.Map<java.lang.Integer, int[]> r8 = r9.co_midi_rpn_rpn_i     // Catch:{ all -> 0x024c }
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x024c }
            java.lang.Object r7 = r8.get(r7)     // Catch:{ all -> 0x024c }
            int[] r7 = (int[]) r7     // Catch:{ all -> 0x024c }
            if (r7 == 0) goto L_0x019d
            r6 = r7[r2]     // Catch:{ all -> 0x024c }
        L_0x019d:
            if (r10 != r3) goto L_0x01a6
            r1 = r6 & 127(0x7f, float:1.78E-43)
            int r3 = r11 << 7
            int r6 = r1 + r3
            goto L_0x01c8
        L_0x01a6:
            if (r10 != r1) goto L_0x01ad
            r1 = r6 & 16256(0x3f80, float:2.278E-41)
            int r6 = r1 + r11
            goto L_0x01c8
        L_0x01ad:
            r1 = 97
            r3 = 96
            if (r10 == r3) goto L_0x01b5
            if (r10 != r1) goto L_0x01c8
        L_0x01b5:
            int r7 = r9.rpn_control     // Catch:{ all -> 0x024c }
            r8 = 2
            if (r7 == r8) goto L_0x01c0
            r8 = 3
            if (r7 == r8) goto L_0x01c0
            r8 = 4
            if (r7 != r8) goto L_0x01c2
        L_0x01c0:
            r5 = 128(0x80, float:1.794E-43)
        L_0x01c2:
            if (r10 != r3) goto L_0x01c5
            int r6 = r6 + r5
        L_0x01c5:
            if (r10 != r1) goto L_0x01c8
            int r6 = r6 - r5
        L_0x01c8:
            int r1 = r9.nrpn_control     // Catch:{ all -> 0x024c }
            if (r1 == r4) goto L_0x01cf
            r9.nrpnChange(r1, r6)     // Catch:{ all -> 0x024c }
        L_0x01cf:
            int r1 = r9.rpn_control     // Catch:{ all -> 0x024c }
            if (r1 == r4) goto L_0x020c
            r9.rpnChange(r1, r6)     // Catch:{ all -> 0x024c }
            goto L_0x020c
        L_0x01d7:
            double r3 = (double) r11     // Catch:{ all -> 0x024c }
            r5 = 4638707616191610880(0x4060000000000000, double:128.0)
            double r3 = r3 / r5
            r5 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r3 = r3 * r5
            r5 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r3 = r3 - r5
            double r3 = java.lang.Math.asin(r3)     // Catch:{ all -> 0x024c }
            double r3 = -r3
            r5 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r3 = r3 / r5
            r5 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r3 = r3 + r5
            r5 = 4681608360884174848(0x40f86a0000000000, double:100000.0)
            double r3 = java.lang.Math.pow(r5, r3)     // Catch:{ all -> 0x024c }
            r5 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r3 = r3 / r5
            double r3 = r3 / r5
            r5 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r3 = r3 * r5
            cn.sherlock.com.sun.media.sound.SoftSynthesizer r1 = r9.synthesizer     // Catch:{ all -> 0x024c }
            float r1 = r1.getControlRate()     // Catch:{ all -> 0x024c }
            double r5 = (double) r1     // Catch:{ all -> 0x024c }
            double r3 = r3 / r5
            r9.portamento_time = r3     // Catch:{ all -> 0x024c }
        L_0x020c:
            double[][] r1 = r9.co_midi_cc_cc     // Catch:{ all -> 0x024c }
            r1 = r1[r10]     // Catch:{ all -> 0x024c }
            double r3 = (double) r11     // Catch:{ all -> 0x024c }
            r5 = 4575657221408423936(0x3f80000000000000, double:0.0078125)
            double r3 = r3 * r5
            r1[r2] = r3     // Catch:{ all -> 0x024c }
            if (r10 != 0) goto L_0x021e
            int r10 = r11 << 7
            r9.bank = r10     // Catch:{ all -> 0x024c }
            monitor-exit(r0)     // Catch:{ all -> 0x024c }
            return
        L_0x021e:
            r1 = 32
            if (r10 != r1) goto L_0x022b
            int r10 = r9.bank     // Catch:{ all -> 0x024c }
            r10 = r10 & 16256(0x3f80, float:2.278E-41)
            int r10 = r10 + r11
            r9.bank = r10     // Catch:{ all -> 0x024c }
            monitor-exit(r0)     // Catch:{ all -> 0x024c }
            return
        L_0x022b:
            int[] r3 = r9.controller     // Catch:{ all -> 0x024c }
            r3[r10] = r11     // Catch:{ all -> 0x024c }
            if (r10 >= r1) goto L_0x0235
            int r1 = r10 + 32
            r3[r1] = r2     // Catch:{ all -> 0x024c }
        L_0x0235:
            cn.sherlock.com.sun.media.sound.SoftVoice[] r1 = r9.voices     // Catch:{ all -> 0x024c }
            int r3 = r1.length     // Catch:{ all -> 0x024c }
            if (r2 >= r3) goto L_0x024a
            r1 = r1[r2]     // Catch:{ all -> 0x024c }
            boolean r1 = r1.active     // Catch:{ all -> 0x024c }
            if (r1 == 0) goto L_0x0247
            cn.sherlock.com.sun.media.sound.SoftVoice[] r1 = r9.voices     // Catch:{ all -> 0x024c }
            r1 = r1[r2]     // Catch:{ all -> 0x024c }
            r1.controlChange(r10, r11)     // Catch:{ all -> 0x024c }
        L_0x0247:
            int r2 = r2 + 1
            goto L_0x0235
        L_0x024a:
            monitor-exit(r0)     // Catch:{ all -> 0x024c }
            return
        L_0x024c:
            r10 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x024c }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftChannel.controlChange(int, int):void");
    }

    public int getController(int i) {
        int i2;
        synchronized (this.control_mutex) {
            i2 = this.controller[i] & 127;
        }
        return i2;
    }

    public void tuningChange(int i) {
        tuningChange(0, i);
    }

    public void tuningChange(int i, int i2) {
        synchronized (this.control_mutex) {
            this.tuning = this.synthesizer.getTuning(new Patch(i, i2));
        }
    }

    public void programChange(int i) {
        programChange(this.bank, i);
    }

    public void programChange(int i, int i2) {
        int restrict14Bit = restrict14Bit(i);
        int restrict7Bit = restrict7Bit(i2);
        synchronized (this.control_mutex) {
            this.mainmixer.activity();
            if (!(this.bank == restrict14Bit && this.program == restrict7Bit)) {
                this.bank = restrict14Bit;
                this.program = restrict7Bit;
                this.current_instrument = null;
            }
        }
    }

    public int getProgram() {
        int i;
        synchronized (this.control_mutex) {
            i = this.program;
        }
        return i;
    }

    public void setPitchBend(int i) {
        int restrict14Bit = restrict14Bit(i);
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setPitchBend(restrict14Bit);
        }
        synchronized (this.control_mutex) {
            this.mainmixer.activity();
            int i2 = 0;
            this.co_midi_pitch[0] = ((double) restrict14Bit) * 6.103515625E-5d;
            this.pitchbend = restrict14Bit;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i2 < softVoiceArr.length) {
                    if (softVoiceArr[i2].active) {
                        this.voices[i2].setPitchBend(restrict14Bit);
                    }
                    i2++;
                }
            }
        }
    }

    public int getPitchBend() {
        int i;
        synchronized (this.control_mutex) {
            i = this.pitchbend;
        }
        return i;
    }

    public void nrpnChange(int i, int i2) {
        if (this.synthesizer.getGeneralMidiMode() == 0) {
            if (i == 136) {
                controlChange(76, i2 >> 7);
            }
            if (i == 137) {
                controlChange(77, i2 >> 7);
            }
            if (i == 138) {
                controlChange(78, i2 >> 7);
            }
            if (i == 160) {
                controlChange(74, i2 >> 7);
            }
            if (i == 161) {
                controlChange(71, i2 >> 7);
            }
            if (i == 227) {
                controlChange(73, i2 >> 7);
            }
            if (i == 228) {
                controlChange(75, i2 >> 7);
            }
            if (i == 230) {
                controlChange(72, i2 >> 7);
            }
            int i3 = i >> 7;
            if (i3 == 24) {
                controlChangePerNote(i % 128, 120, i2 >> 7);
            }
            if (i3 == 26) {
                controlChangePerNote(i % 128, 7, i2 >> 7);
            }
            if (i3 == 28) {
                controlChangePerNote(i % 128, 10, i2 >> 7);
            }
            if (i3 == 29) {
                controlChangePerNote(i % 128, 91, i2 >> 7);
            }
            if (i3 == 30) {
                controlChangePerNote(i % 128, 93, i2 >> 7);
            }
        }
        int[] iArr = this.co_midi_nrpn_nrpn_i.get(Integer.valueOf(i));
        double[] dArr = this.co_midi_nrpn_nrpn.get(Integer.valueOf(i));
        if (iArr == null) {
            iArr = new int[1];
            this.co_midi_nrpn_nrpn_i.put(Integer.valueOf(i), iArr);
        }
        if (dArr == null) {
            dArr = new double[1];
            this.co_midi_nrpn_nrpn.put(Integer.valueOf(i), dArr);
        }
        iArr[0] = i2;
        dArr[0] = ((double) i2) * 6.103515625E-5d;
        int i4 = 0;
        while (true) {
            SoftVoice[] softVoiceArr = this.voices;
            if (i4 < softVoiceArr.length) {
                if (softVoiceArr[i4].active) {
                    this.voices[i4].nrpnChange(i, iArr[0]);
                }
                i4++;
            } else {
                return;
            }
        }
    }

    public void rpnChange(int i, int i2) {
        if (i == 3) {
            int i3 = (i2 >> 7) & 127;
            this.tuning_program = i3;
            tuningChange(this.tuning_bank, i3);
        }
        if (i == 4) {
            this.tuning_bank = (i2 >> 7) & 127;
        }
        int[] iArr = this.co_midi_rpn_rpn_i.get(Integer.valueOf(i));
        double[] dArr = this.co_midi_rpn_rpn.get(Integer.valueOf(i));
        if (iArr == null) {
            iArr = new int[1];
            this.co_midi_rpn_rpn_i.put(Integer.valueOf(i), iArr);
        }
        if (dArr == null) {
            dArr = new double[1];
            this.co_midi_rpn_rpn.put(Integer.valueOf(i), dArr);
        }
        iArr[0] = i2;
        dArr[0] = ((double) i2) * 6.103515625E-5d;
        int i4 = 0;
        while (true) {
            SoftVoice[] softVoiceArr = this.voices;
            if (i4 < softVoiceArr.length) {
                if (softVoiceArr[i4].active) {
                    this.voices[i4].rpnChange(i, iArr[0]);
                }
                i4++;
            } else {
                return;
            }
        }
    }

    public void resetAllControllers() {
        resetAllControllers(false);
    }

    public void resetAllControllers(boolean z) {
        synchronized (this.control_mutex) {
            this.mainmixer.activity();
            for (int i = 0; i < 128; i++) {
                setPolyPressure(i, 0);
            }
            setChannelPressure(0);
            setPitchBend(8192);
            for (int i2 = 0; i2 < 128; i2++) {
                if (!dontResetControls[i2]) {
                    controlChange(i2, 0);
                }
            }
            controlChange(71, 64);
            controlChange(72, 64);
            controlChange(73, 64);
            controlChange(74, 64);
            controlChange(75, 64);
            controlChange(76, 64);
            controlChange(77, 64);
            controlChange(78, 64);
            controlChange(8, 64);
            controlChange(11, 127);
            controlChange(98, 127);
            controlChange(99, 127);
            controlChange(100, 127);
            controlChange(101, 127);
            if (z) {
                this.keybasedcontroller_active = null;
                this.keybasedcontroller_value = null;
                controlChange(7, 100);
                controlChange(10, 64);
                controlChange(91, 40);
                for (Integer intValue : this.co_midi_rpn_rpn.keySet()) {
                    int intValue2 = intValue.intValue();
                    if (!(intValue2 == 3 || intValue2 == 4)) {
                        rpnChange(intValue2, 0);
                    }
                }
                for (Integer intValue3 : this.co_midi_nrpn_nrpn.keySet()) {
                    nrpnChange(intValue3.intValue(), 0);
                }
                rpnChange(0, 256);
                rpnChange(1, 8192);
                rpnChange(2, 8192);
                rpnChange(5, 64);
                this.tuning_bank = 0;
                this.tuning_program = 0;
                this.tuning = new SoftTuning();
            }
        }
    }

    public void allNotesOff() {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.allNotesOff();
        }
        synchronized (this.control_mutex) {
            int i = 0;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i < softVoiceArr.length) {
                    if (softVoiceArr[i].on && this.voices[i].channel == this.channel && !this.voices[i].releaseTriggered) {
                        this.voices[i].noteOff(0);
                    }
                    i++;
                }
            }
        }
    }

    public void allSoundOff() {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.allSoundOff();
        }
        synchronized (this.control_mutex) {
            int i = 0;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i < softVoiceArr.length) {
                    if (softVoiceArr[i].on && this.voices[i].channel == this.channel) {
                        this.voices[i].soundOff();
                    }
                    i++;
                }
            }
        }
    }

    public void setMono(boolean z) {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setMono(z);
        }
        synchronized (this.control_mutex) {
            allNotesOff();
            this.mono = z;
        }
    }

    public boolean getMono() {
        boolean z;
        synchronized (this.control_mutex) {
            z = this.mono;
        }
        return z;
    }

    public void setOmni(boolean z) {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setOmni(z);
        }
        allNotesOff();
    }

    public void setMute(boolean z) {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setMute(z);
        }
        synchronized (this.control_mutex) {
            this.mute = z;
            int i = 0;
            while (true) {
                SoftVoice[] softVoiceArr = this.voices;
                if (i < softVoiceArr.length) {
                    if (softVoiceArr[i].active && this.voices[i].channel == this.channel) {
                        this.voices[i].setMute(z);
                    }
                    i++;
                }
            }
        }
    }

    public boolean getMute() {
        boolean z;
        synchronized (this.control_mutex) {
            z = this.mute;
        }
        return z;
    }

    public void setSolo(boolean z) {
        ModelChannelMixer modelChannelMixer = this.current_mixer;
        if (modelChannelMixer != null) {
            modelChannelMixer.setSolo(z);
        }
        synchronized (this.control_mutex) {
            this.solo = z;
            for (SoftChannel softChannel : this.synthesizer.channels) {
                if (softChannel.solo) {
                    for (SoftChannel softChannel2 : this.synthesizer.channels) {
                        softChannel2.setSoloMute(!softChannel2.solo);
                    }
                    return;
                }
            }
            for (SoftChannel soloMute : this.synthesizer.channels) {
                soloMute.setSoloMute(false);
            }
        }
    }

    private void setSoloMute(boolean z) {
        synchronized (this.control_mutex) {
            if (this.solomute != z) {
                this.solomute = z;
                int i = 0;
                while (true) {
                    SoftVoice[] softVoiceArr = this.voices;
                    if (i < softVoiceArr.length) {
                        if (softVoiceArr[i].active && this.voices[i].channel == this.channel) {
                            this.voices[i].setSoloMute(this.solomute);
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public boolean getSolo() {
        boolean z;
        synchronized (this.control_mutex) {
            z = this.solo;
        }
        return z;
    }
}
