package cn.sherlock.com.sun.media.sound;

import androidx.exifinterface.media.ExifInterface;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import jp.kshoji.javax.sound.midi.VoiceStatus;

public class SoftVoice extends VoiceStatus {
    private boolean audiostarted = false;
    protected ModelChannelMixer channelmixer = null;
    private SoftControl co_filter;
    /* access modifiers changed from: private */
    public double[] co_filter_freq;
    /* access modifiers changed from: private */
    public double[] co_filter_q;
    /* access modifiers changed from: private */
    public double[] co_filter_type;
    private SoftControl co_mixer;
    /* access modifiers changed from: private */
    public double[] co_mixer_active;
    /* access modifiers changed from: private */
    public double[] co_mixer_balance;
    /* access modifiers changed from: private */
    public double[] co_mixer_chorus;
    /* access modifiers changed from: private */
    public double[] co_mixer_gain;
    /* access modifiers changed from: private */
    public double[] co_mixer_pan;
    /* access modifiers changed from: private */
    public double[] co_mixer_reverb;
    private SoftControl co_noteon;
    protected double[] co_noteon_keynumber;
    protected double[] co_noteon_on;
    protected double[] co_noteon_velocity;
    private SoftControl co_osc;
    /* access modifiers changed from: private */
    public double[] co_osc_pitch;
    private ModelConnectionBlock[] connections;
    private double[][] connections_dst;
    private double[] connections_last = new double[50];
    private double[][][] connections_src;
    private int[][] connections_src_kc;
    private int delay = 0;
    private SoftProcess eg = new SoftEnvelopeGenerator();
    public int exclusiveClass = 0;
    protected ModelConnectionBlock[] extendedConnectionBlocks = null;
    private SoftFilter filter_left;
    private SoftFilter filter_right;
    protected SoftInstrument instrument;
    private float lastMuteValue;
    private float lastSoloMuteValue;
    private float last_out_mixer_effect1 = 0.0f;
    private float last_out_mixer_effect2 = 0.0f;
    private float last_out_mixer_left = 0.0f;
    private float last_out_mixer_right = 0.0f;
    private SoftProcess lfo = new SoftLowFrequencyOscillator();
    private int noteOff_velocity = 0;
    private int noteOn_noteNumber = 0;
    private int noteOn_velocity = 0;
    private int nrofchannels;
    protected Map<String, SoftControl> objects = new HashMap();
    protected boolean on = false;
    private float osc_attenuation = 0.0f;
    private float[][] osc_buff = new float[2][];
    private ModelOscillatorStream osc_stream;
    private int osc_stream_nrofchannels;
    private boolean osc_stream_off_transmitted = false;
    private float out_mixer_effect1 = 0.0f;
    private float out_mixer_effect2 = 0.0f;
    private boolean out_mixer_end = false;
    private float out_mixer_left = 0.0f;
    private float out_mixer_right = 0.0f;
    protected SoftPerformer performer;
    protected boolean portamento = false;
    public boolean releaseTriggered = false;
    protected SoftResamplerStreamer resampler;
    protected SoftChannel softchannel = null;
    protected boolean sostenuto = false;
    private boolean soundoff;
    private boolean started = false;
    protected SoftChannel stealer_channel = null;
    protected ModelChannelMixer stealer_channelmixer = null;
    protected ModelConnectionBlock[] stealer_extendedConnectionBlocks = null;
    protected int stealer_noteNumber = 0;
    protected SoftPerformer stealer_performer = null;
    protected boolean stealer_releaseTriggered = false;
    protected int stealer_velocity = 0;
    protected int stealer_voiceID = -1;
    private boolean stopping = false;
    protected boolean sustain = false;
    protected SoftSynthesizer synthesizer;
    protected double tunedKey = 0.0d;
    protected SoftTuning tuning = null;
    protected int voiceID = -1;

    public SoftVoice(SoftSynthesizer softSynthesizer) {
        int[] iArr = new int[2];
        iArr[1] = 3;
        iArr[0] = 50;
        this.connections_src = (double[][][]) Array.newInstance(double[].class, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = 3;
        iArr2[0] = 50;
        this.connections_src_kc = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        this.connections_dst = new double[50][];
        this.soundoff = false;
        this.lastMuteValue = 0.0f;
        this.lastSoloMuteValue = 0.0f;
        this.co_noteon_keynumber = new double[1];
        this.co_noteon_velocity = new double[1];
        this.co_noteon_on = new double[1];
        this.co_noteon = new SoftControl() {
            double[] keynumber;
            double[] on;
            double[] velocity;

            {
                this.keynumber = SoftVoice.this.co_noteon_keynumber;
                this.velocity = SoftVoice.this.co_noteon_velocity;
                this.on = SoftVoice.this.co_noteon_on;
            }

            public double[] get(int i, String str) {
                if (str == null) {
                    return null;
                }
                if (str.equals("keynumber")) {
                    return this.keynumber;
                }
                if (str.equals("velocity")) {
                    return this.velocity;
                }
                if (str.equals("on")) {
                    return this.on;
                }
                return null;
            }
        };
        this.co_mixer_active = new double[1];
        this.co_mixer_gain = new double[1];
        this.co_mixer_pan = new double[1];
        this.co_mixer_balance = new double[1];
        this.co_mixer_reverb = new double[1];
        this.co_mixer_chorus = new double[1];
        this.co_mixer = new SoftControl() {
            double[] active;
            double[] balance;
            double[] chorus;
            double[] gain;
            double[] pan;
            double[] reverb;

            {
                this.active = SoftVoice.this.co_mixer_active;
                this.gain = SoftVoice.this.co_mixer_gain;
                this.pan = SoftVoice.this.co_mixer_pan;
                this.balance = SoftVoice.this.co_mixer_balance;
                this.reverb = SoftVoice.this.co_mixer_reverb;
                this.chorus = SoftVoice.this.co_mixer_chorus;
            }

            public double[] get(int i, String str) {
                if (str == null) {
                    return null;
                }
                if (str.equals("active")) {
                    return this.active;
                }
                if (str.equals("gain")) {
                    return this.gain;
                }
                if (str.equals("pan")) {
                    return this.pan;
                }
                if (str.equals("balance")) {
                    return this.balance;
                }
                if (str.equals("reverb")) {
                    return this.reverb;
                }
                if (str.equals("chorus")) {
                    return this.chorus;
                }
                return null;
            }
        };
        this.co_osc_pitch = new double[1];
        this.co_osc = new SoftControl() {
            double[] pitch;

            {
                this.pitch = SoftVoice.this.co_osc_pitch;
            }

            public double[] get(int i, String str) {
                if (str != null && str.equals("pitch")) {
                    return this.pitch;
                }
                return null;
            }
        };
        this.co_filter_freq = new double[1];
        this.co_filter_type = new double[1];
        this.co_filter_q = new double[1];
        this.co_filter = new SoftControl() {
            double[] freq;
            double[] ftype;
            double[] q;

            {
                this.freq = SoftVoice.this.co_filter_freq;
                this.ftype = SoftVoice.this.co_filter_type;
                this.q = SoftVoice.this.co_filter_q;
            }

            public double[] get(int i, String str) {
                if (str == null) {
                    return null;
                }
                if (str.equals("freq")) {
                    return this.freq;
                }
                if (str.equals("type")) {
                    return this.ftype;
                }
                if (str.equals("q")) {
                    return this.q;
                }
                return null;
            }
        };
        this.synthesizer = softSynthesizer;
        this.filter_left = new SoftFilter(softSynthesizer.getFormat().getSampleRate());
        this.filter_right = new SoftFilter(softSynthesizer.getFormat().getSampleRate());
        this.nrofchannels = softSynthesizer.getFormat().getChannels();
    }

    private int getValueKC(ModelIdentifier modelIdentifier) {
        if (modelIdentifier.getObject().equals("midi_cc")) {
            int parseInt = Integer.parseInt(modelIdentifier.getVariable());
            if (parseInt == 0 || parseInt == 32 || parseInt >= 120) {
                return -1;
            }
            return parseInt;
        } else if (!modelIdentifier.getObject().equals("midi_rpn")) {
            return -1;
        } else {
            if (modelIdentifier.getVariable().equals("1")) {
                return 120;
            }
            return modelIdentifier.getVariable().equals(ExifInterface.GPS_MEASUREMENT_2D) ? 121 : -1;
        }
    }

    private double[] getValue(ModelIdentifier modelIdentifier) {
        SoftControl softControl = this.objects.get(modelIdentifier.getObject());
        if (softControl == null) {
            return null;
        }
        return softControl.get(modelIdentifier.getInstance(), modelIdentifier.getVariable());
    }

    private double transformValue(double d, ModelSource modelSource) {
        return modelSource.getTransform() != null ? modelSource.getTransform().transform(d) : d;
    }

    private double transformValue(double d, ModelDestination modelDestination) {
        return modelDestination.getTransform() != null ? modelDestination.getTransform().transform(d) : d;
    }

    private double processKeyBasedController(double d, int i) {
        if (i == -1 || this.softchannel.keybasedcontroller_active == null || this.softchannel.keybasedcontroller_active[this.note] == null || !this.softchannel.keybasedcontroller_active[this.note][i]) {
            return d;
        }
        double d2 = this.softchannel.keybasedcontroller_value[this.note][i];
        if (i == 10 || i == 91 || i == 93) {
            return d2;
        }
        double d3 = d + ((d2 * 2.0d) - 1.0d);
        if (d3 > 1.0d) {
            return 1.0d;
        }
        if (d3 < 0.0d) {
            return 0.0d;
        }
        return d3;
    }

    private void processConnection(int i) {
        ModelConnectionBlock modelConnectionBlock = this.connections[i];
        double[][] dArr = this.connections_src[i];
        double[] dArr2 = this.connections_dst[i];
        if (dArr2 != null && !Double.isInfinite(dArr2[0])) {
            double scale = modelConnectionBlock.getScale();
            if (this.softchannel.keybasedcontroller_active == null) {
                ModelSource[] sources = modelConnectionBlock.getSources();
                for (int i2 = 0; i2 < sources.length; i2++) {
                    scale *= transformValue(dArr[i2][0], sources[i2]);
                    if (scale == 0.0d) {
                        break;
                    }
                }
            } else {
                ModelSource[] sources2 = modelConnectionBlock.getSources();
                int[] iArr = this.connections_src_kc[i];
                for (int i3 = 0; i3 < sources2.length; i3++) {
                    scale *= transformValue(processKeyBasedController(dArr[i3][0], iArr[i3]), sources2[i3]);
                    if (scale == 0.0d) {
                        break;
                    }
                }
            }
            double transformValue = transformValue(scale, modelConnectionBlock.getDestination());
            double d = dArr2[0];
            double[] dArr3 = this.connections_last;
            dArr2[0] = (d - dArr3[i]) + transformValue;
            dArr3[i] = transformValue;
        }
    }

    /* access modifiers changed from: protected */
    public void updateTuning(SoftTuning softTuning) {
        int[] iArr;
        this.tuning = softTuning;
        double tuning2 = softTuning.getTuning(this.note) / 100.0d;
        this.tunedKey = tuning2;
        if (!this.portamento) {
            this.co_noteon_keynumber[0] = tuning2 * 0.0078125d;
            SoftPerformer softPerformer = this.performer;
            if (softPerformer != null && (iArr = softPerformer.midi_connections[4]) != null) {
                for (int processConnection : iArr) {
                    processConnection(processConnection);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setNote(int i) {
        this.note = i;
        this.tunedKey = this.tuning.getTuning(i) / 100.0d;
    }

    /* access modifiers changed from: protected */
    public void noteOn(int i, int i2, int i3) {
        int i4;
        int i5;
        double d;
        int i6 = i2;
        int i7 = 0;
        this.sustain = false;
        this.sostenuto = false;
        this.portamento = false;
        this.soundoff = false;
        this.on = true;
        this.active = true;
        this.started = true;
        this.noteOn_noteNumber = i;
        this.noteOn_velocity = i6;
        this.delay = i3;
        this.lastMuteValue = 0.0f;
        this.lastSoloMuteValue = 0.0f;
        setNote(i);
        if (this.performer.forcedKeynumber) {
            this.co_noteon_keynumber[0] = 0.0d;
        } else {
            this.co_noteon_keynumber[0] = this.tunedKey * 0.0078125d;
        }
        if (this.performer.forcedVelocity) {
            this.co_noteon_velocity[0] = 0.0d;
        } else {
            this.co_noteon_velocity[0] = (double) (((float) i6) * 0.0078125f);
        }
        this.co_mixer_active[0] = 0.0d;
        this.co_mixer_gain[0] = 0.0d;
        this.co_mixer_pan[0] = 0.0d;
        this.co_mixer_balance[0] = 0.0d;
        this.co_mixer_reverb[0] = 0.0d;
        this.co_mixer_chorus[0] = 0.0d;
        this.co_osc_pitch[0] = 0.0d;
        this.co_filter_freq[0] = 0.0d;
        this.co_filter_q[0] = 0.0d;
        this.co_filter_type[0] = 0.0d;
        this.co_noteon_on[0] = 1.0d;
        this.eg.reset();
        this.lfo.reset();
        this.filter_left.reset();
        this.filter_right.reset();
        this.objects.put("master", this.synthesizer.getMainMixer().co_master);
        this.objects.put("eg", this.eg);
        this.objects.put("lfo", this.lfo);
        this.objects.put("noteon", this.co_noteon);
        this.objects.put("osc", this.co_osc);
        this.objects.put("mixer", this.co_mixer);
        this.objects.put("filter", this.co_filter);
        ModelConnectionBlock[] modelConnectionBlockArr = this.performer.connections;
        this.connections = modelConnectionBlockArr;
        double[] dArr = this.connections_last;
        if (dArr == null || dArr.length < modelConnectionBlockArr.length) {
            this.connections_last = new double[modelConnectionBlockArr.length];
        }
        double[][][] dArr2 = this.connections_src;
        if (dArr2 == null || dArr2.length < modelConnectionBlockArr.length) {
            this.connections_src = new double[modelConnectionBlockArr.length][][];
            this.connections_src_kc = new int[modelConnectionBlockArr.length][];
        }
        double[][] dArr3 = this.connections_dst;
        if (dArr3 == null || dArr3.length < modelConnectionBlockArr.length) {
            this.connections_dst = new double[modelConnectionBlockArr.length][];
        }
        int i8 = 0;
        while (true) {
            ModelConnectionBlock[] modelConnectionBlockArr2 = this.connections;
            if (i8 >= modelConnectionBlockArr2.length) {
                break;
            }
            ModelConnectionBlock modelConnectionBlock = modelConnectionBlockArr2[i8];
            this.connections_last[i8] = 0.0d;
            if (modelConnectionBlock.getSources() != null) {
                ModelSource[] sources = modelConnectionBlock.getSources();
                double[][][] dArr4 = this.connections_src;
                double[][] dArr5 = dArr4[i8];
                if (dArr5 == null || dArr5.length < sources.length) {
                    dArr4[i8] = new double[sources.length][];
                    this.connections_src_kc[i8] = new int[sources.length];
                }
                double[][] dArr6 = dArr4[i8];
                int[] iArr = this.connections_src_kc[i8];
                dArr4[i8] = dArr6;
                for (int i9 = 0; i9 < sources.length; i9++) {
                    iArr[i9] = getValueKC(sources[i9].getIdentifier());
                    dArr6[i9] = getValue(sources[i9].getIdentifier());
                }
            }
            if (modelConnectionBlock.getDestination() != null) {
                this.connections_dst[i8] = getValue(modelConnectionBlock.getDestination().getIdentifier());
            } else {
                this.connections_dst[i8] = null;
            }
            i8++;
        }
        for (int i10 = 0; i10 < this.connections.length; i10++) {
            processConnection(i10);
        }
        ModelConnectionBlock[] modelConnectionBlockArr3 = this.extendedConnectionBlocks;
        if (modelConnectionBlockArr3 != null) {
            int length = modelConnectionBlockArr3.length;
            int i11 = 0;
            while (i11 < length) {
                ModelConnectionBlock modelConnectionBlock2 = modelConnectionBlockArr3[i11];
                if (this.softchannel.keybasedcontroller_active == null) {
                    ModelSource[] sources2 = modelConnectionBlock2.getSources();
                    int length2 = sources2.length;
                    int i12 = i7;
                    d = 0.0d;
                    while (i12 < length2) {
                        ModelSource modelSource = sources2[i12];
                        int i13 = i7;
                        int i14 = length;
                        double d2 = getValue(modelSource.getIdentifier())[i13];
                        ModelTransform transform = modelSource.getTransform();
                        if (transform != null) {
                            d2 = transform.transform(d2);
                        }
                        d += d2;
                        i12++;
                        length = i14;
                        i7 = i13;
                    }
                    i5 = i7;
                    i4 = length;
                } else {
                    i5 = i7;
                    i4 = length;
                    ModelSource[] sources3 = modelConnectionBlock2.getSources();
                    int length3 = sources3.length;
                    double d3 = 0.0d;
                    for (int i15 = i5; i15 < length3; i15++) {
                        ModelSource modelSource2 = sources3[i15];
                        double processKeyBasedController = processKeyBasedController(getValue(modelSource2.getIdentifier())[i5], getValueKC(modelSource2.getIdentifier()));
                        ModelTransform transform2 = modelSource2.getTransform();
                        if (transform2 == null) {
                            d3 = d + processKeyBasedController;
                        } else {
                            d3 = d + transform2.transform(processKeyBasedController);
                        }
                    }
                }
                ModelDestination destination = modelConnectionBlock2.getDestination();
                ModelTransform transform3 = destination.getTransform();
                if (transform3 != null) {
                    d = transform3.transform(d);
                }
                double[] value = getValue(destination.getIdentifier());
                value[i5] = value[i5] + d;
                i11++;
                length = i4;
                i7 = i5;
            }
        }
        this.eg.init(this.synthesizer);
        this.lfo.init(this.synthesizer);
    }

    /* access modifiers changed from: protected */
    public void setPolyPressure(int i) {
        int[] iArr;
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null && (iArr = softPerformer.midi_connections[2]) != null) {
            for (int processConnection : iArr) {
                processConnection(processConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setChannelPressure(int i) {
        int[] iArr;
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null && (iArr = softPerformer.midi_connections[1]) != null) {
            for (int processConnection : iArr) {
                processConnection(processConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void controlChange(int i, int i2) {
        int[] iArr;
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null && (iArr = softPerformer.midi_ctrl_connections[i]) != null) {
            for (int processConnection : iArr) {
                processConnection(processConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void nrpnChange(int i, int i2) {
        int[] iArr;
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null && (iArr = softPerformer.midi_nrpn_connections.get(Integer.valueOf(i))) != null) {
            for (int processConnection : iArr) {
                processConnection(processConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void rpnChange(int i, int i2) {
        int[] iArr;
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null && (iArr = softPerformer.midi_rpn_connections.get(Integer.valueOf(i))) != null) {
            for (int processConnection : iArr) {
                processConnection(processConnection);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setPitchBend(int i) {
        SoftPerformer softPerformer = this.performer;
        if (softPerformer != null) {
            int[] iArr = softPerformer.midi_connections[0];
            if (iArr != null) {
                for (int processConnection : iArr) {
                    processConnection(processConnection);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setMute(boolean z) {
        double[] dArr = this.co_mixer_gain;
        double d = dArr[0] - ((double) this.lastMuteValue);
        dArr[0] = d;
        float f = z ? -960.0f : 0.0f;
        this.lastMuteValue = f;
        dArr[0] = d + ((double) f);
    }

    /* access modifiers changed from: protected */
    public void setSoloMute(boolean z) {
        double[] dArr = this.co_mixer_gain;
        double d = dArr[0] - ((double) this.lastSoloMuteValue);
        dArr[0] = d;
        float f = z ? -960.0f : 0.0f;
        this.lastSoloMuteValue = f;
        dArr[0] = d + ((double) f);
    }

    /* access modifiers changed from: protected */
    public void shutdown() {
        int[] iArr;
        double[] dArr = this.co_noteon_on;
        if (dArr[0] >= -0.5d) {
            this.on = false;
            dArr[0] = -1.0d;
            SoftPerformer softPerformer = this.performer;
            if (softPerformer != null && (iArr = softPerformer.midi_connections[3]) != null) {
                for (int processConnection : iArr) {
                    processConnection(processConnection);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void soundOff() {
        this.on = false;
        this.soundoff = true;
    }

    /* access modifiers changed from: protected */
    public void noteOff(int i) {
        int[] iArr;
        if (this.on) {
            this.on = false;
            this.noteOff_velocity = i;
            if (this.softchannel.sustain) {
                this.sustain = true;
            } else if (!this.sostenuto) {
                this.co_noteon_on[0] = 0.0d;
                SoftPerformer softPerformer = this.performer;
                if (softPerformer != null && (iArr = softPerformer.midi_connections[3]) != null) {
                    for (int processConnection : iArr) {
                        processConnection(processConnection);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void redamp() {
        int[] iArr;
        double[] dArr = this.co_noteon_on;
        double d = dArr[0];
        if (d <= 0.5d && d >= -0.5d) {
            this.sustain = true;
            dArr[0] = 1.0d;
            SoftPerformer softPerformer = this.performer;
            if (softPerformer != null && (iArr = softPerformer.midi_connections[3]) != null) {
                for (int processConnection : iArr) {
                    processConnection(processConnection);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void processControlLogic() {
        double d;
        double d2;
        if (this.stopping) {
            this.active = false;
            this.stopping = false;
            this.audiostarted = false;
            this.instrument = null;
            this.performer = null;
            this.connections = null;
            this.extendedConnectionBlocks = null;
            this.channelmixer = null;
            ModelOscillatorStream modelOscillatorStream = this.osc_stream;
            if (modelOscillatorStream != null) {
                try {
                    modelOscillatorStream.close();
                } catch (IOException unused) {
                }
            }
            SoftChannel softChannel = this.stealer_channel;
            if (softChannel != null) {
                softChannel.initVoice(this, this.stealer_performer, this.stealer_voiceID, this.stealer_noteNumber, this.stealer_velocity, 0, this.stealer_extendedConnectionBlocks, this.stealer_channelmixer, this.stealer_releaseTriggered);
                this.stealer_releaseTriggered = false;
                this.stealer_channel = null;
                this.stealer_performer = null;
                this.stealer_voiceID = -1;
                this.stealer_noteNumber = 0;
                this.stealer_velocity = 0;
                this.stealer_extendedConnectionBlocks = null;
                this.stealer_channelmixer = null;
            }
        }
        if (this.started) {
            this.audiostarted = true;
            ModelOscillator modelOscillator = this.performer.oscillators[0];
            this.osc_stream_off_transmitted = false;
            if (modelOscillator instanceof ModelWavetable) {
                try {
                    this.resampler.open((ModelWavetable) modelOscillator, this.synthesizer.getFormat().getSampleRate());
                    this.osc_stream = this.resampler;
                } catch (IOException unused2) {
                }
            } else {
                this.osc_stream = modelOscillator.open(this.synthesizer.getFormat().getSampleRate());
            }
            this.osc_attenuation = modelOscillator.getAttenuation();
            int channels = modelOscillator.getChannels();
            this.osc_stream_nrofchannels = channels;
            float[][] fArr = this.osc_buff;
            if (fArr == null || fArr.length < channels) {
                this.osc_buff = new float[channels][];
            }
            ModelOscillatorStream modelOscillatorStream2 = this.osc_stream;
            if (modelOscillatorStream2 != null) {
                modelOscillatorStream2.noteOn(this.softchannel, this, this.noteOn_noteNumber, this.noteOn_velocity);
            }
        }
        if (this.audiostarted) {
            if (this.portamento) {
                double d3 = this.tunedKey - (this.co_noteon_keynumber[0] * 128.0d);
                double abs = Math.abs(d3);
                if (abs < 1.0E-10d) {
                    this.co_noteon_keynumber[0] = this.tunedKey * 0.0078125d;
                    this.portamento = false;
                } else {
                    if (abs > this.softchannel.portamento_time) {
                        d3 = Math.signum(d3) * this.softchannel.portamento_time;
                    }
                    double[] dArr = this.co_noteon_keynumber;
                    dArr[0] = dArr[0] + (d3 * 0.0078125d);
                }
                int[] iArr = this.performer.midi_connections[4];
                if (iArr != null) {
                    for (int processConnection : iArr) {
                        processConnection(processConnection);
                    }
                } else {
                    return;
                }
            }
            this.eg.processControlLogic();
            this.lfo.processControlLogic();
            for (int processConnection2 : this.performer.ctrl_connections) {
                processConnection(processConnection2);
            }
            this.osc_stream.setPitch((float) this.co_osc_pitch[0]);
            int i = (int) this.co_filter_type[0];
            double d4 = this.co_filter_freq[0];
            if (d4 == 13500.0d) {
                d = 19912.126958213175d;
            } else {
                d = Math.exp((d4 - 6900.0d) * (Math.log(2.0d) / 1200.0d)) * 440.0d;
            }
            double d5 = this.co_filter_q[0] / 10.0d;
            this.filter_left.setFilterType(i);
            this.filter_left.setFrequency(d);
            this.filter_left.setResonance(d5);
            this.filter_right.setFilterType(i);
            this.filter_right.setFrequency(d);
            this.filter_right.setResonance(d5);
            float exp = (float) Math.exp((((double) (-this.osc_attenuation)) + this.co_mixer_gain[0]) * (Math.log(10.0d) / 200.0d));
            if (this.co_mixer_gain[0] <= -960.0d) {
                exp = 0.0f;
            }
            if (this.soundoff) {
                this.stopping = true;
                exp = 0.0f;
            }
            this.volume = (int) (Math.sqrt((double) exp) * 128.0d);
            double d6 = this.co_mixer_pan[0] * 0.001d;
            if (d6 < 0.0d) {
                d6 = 0.0d;
            } else if (d6 > 1.0d) {
                d6 = 1.0d;
            }
            if (d6 == 0.5d) {
                float f = 0.70710677f * exp;
                this.out_mixer_left = f;
                this.out_mixer_right = f;
                d2 = 2.0d;
            } else {
                double d7 = d6 * 3.141592653589793d * 0.5d;
                d2 = 2.0d;
                this.out_mixer_left = ((float) Math.cos(d7)) * exp;
                this.out_mixer_right = ((float) Math.sin(d7)) * exp;
            }
            double d8 = this.co_mixer_balance[0] * 0.001d;
            int i2 = (d8 > 0.5d ? 1 : (d8 == 0.5d ? 0 : -1));
            if (i2 != 0) {
                if (i2 > 0) {
                    this.out_mixer_left = (float) (((double) this.out_mixer_left) * (1.0d - d8) * d2);
                } else {
                    this.out_mixer_right = (float) (((double) this.out_mixer_right) * d8 * d2);
                }
            }
            if (this.synthesizer.reverb_on) {
                this.out_mixer_effect1 = ((float) (this.co_mixer_reverb[0] * 0.001d)) * exp;
            } else {
                this.out_mixer_effect1 = 0.0f;
            }
            if (this.synthesizer.chorus_on) {
                this.out_mixer_effect2 = ((float) (this.co_mixer_chorus[0] * 0.001d)) * exp;
            } else {
                this.out_mixer_effect2 = 0.0f;
            }
            this.out_mixer_end = this.co_mixer_active[0] < 0.5d;
            if (!this.on && !this.osc_stream_off_transmitted) {
                this.osc_stream_off_transmitted = true;
                ModelOscillatorStream modelOscillatorStream3 = this.osc_stream;
                if (modelOscillatorStream3 != null) {
                    modelOscillatorStream3.noteOff(this.noteOff_velocity);
                }
            }
        }
        if (this.started) {
            this.last_out_mixer_left = this.out_mixer_left;
            this.last_out_mixer_right = this.out_mixer_right;
            this.last_out_mixer_effect1 = this.out_mixer_effect1;
            this.last_out_mixer_effect2 = this.out_mixer_effect2;
            this.started = false;
        }
    }

    /* access modifiers changed from: protected */
    public void mixAudioStream(SoftAudioBuffer softAudioBuffer, SoftAudioBuffer softAudioBuffer2, SoftAudioBuffer softAudioBuffer3, float f, float f2) {
        int size = softAudioBuffer.getSize();
        if (((double) f) >= 1.0E-9d || ((double) f2) >= 1.0E-9d) {
            int i = 0;
            if (softAudioBuffer3 == null || this.delay == 0) {
                if (f == f2) {
                    float[] array = softAudioBuffer2.array();
                    float[] array2 = softAudioBuffer.array();
                    while (i < size) {
                        array[i] = array[i] + (array2[i] * f2);
                        i++;
                    }
                    return;
                }
                float f3 = (f2 - f) / ((float) size);
                float[] array3 = softAudioBuffer2.array();
                float[] array4 = softAudioBuffer.array();
                while (i < size) {
                    f += f3;
                    array3[i] = array3[i] + (array4[i] * f);
                    i++;
                }
            } else if (f == f2) {
                float[] array5 = softAudioBuffer2.array();
                float[] array6 = softAudioBuffer.array();
                int i2 = this.delay;
                int i3 = 0;
                while (i2 < size) {
                    array5[i2] = array5[i2] + (array6[i3] * f2);
                    i2++;
                    i3++;
                }
                float[] array7 = softAudioBuffer3.array();
                while (i < this.delay) {
                    array7[i] = array7[i] + (array6[i3] * f2);
                    i++;
                    i3++;
                }
            } else {
                float f4 = (f2 - f) / ((float) size);
                float[] array8 = softAudioBuffer2.array();
                float[] array9 = softAudioBuffer.array();
                int i4 = this.delay;
                int i5 = 0;
                while (i4 < size) {
                    f += f4;
                    array8[i4] = array8[i4] + (array9[i5] * f);
                    i4++;
                    i5++;
                }
                float[] array10 = softAudioBuffer3.array();
                while (i < this.delay) {
                    f += f4;
                    array10[i] = array10[i] + (array9[i5] * f);
                    i++;
                    i5++;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0107  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processAudioLogic(cn.sherlock.com.sun.media.sound.SoftAudioBuffer[] r19) {
        /*
            r18 = this;
            r0 = r18
            boolean r1 = r0.audiostarted
            if (r1 != 0) goto L_0x0008
            goto L_0x0156
        L_0x0008:
            r1 = 0
            r2 = r19[r1]
            int r2 = r2.getSize()
            r3 = 11
            r4 = 10
            r6 = 1
            float[][] r5 = r0.osc_buff     // Catch:{ IOException -> 0x004f }
            r7 = r19[r4]     // Catch:{ IOException -> 0x004f }
            float[] r7 = r7.array()     // Catch:{ IOException -> 0x004f }
            r5[r1] = r7     // Catch:{ IOException -> 0x004f }
            int r5 = r0.nrofchannels     // Catch:{ IOException -> 0x004f }
            if (r5 == r6) goto L_0x002c
            float[][] r5 = r0.osc_buff     // Catch:{ IOException -> 0x004f }
            r7 = r19[r3]     // Catch:{ IOException -> 0x004f }
            float[] r7 = r7.array()     // Catch:{ IOException -> 0x004f }
            r5[r6] = r7     // Catch:{ IOException -> 0x004f }
        L_0x002c:
            cn.sherlock.com.sun.media.sound.ModelOscillatorStream r5 = r0.osc_stream     // Catch:{ IOException -> 0x004f }
            float[][] r7 = r0.osc_buff     // Catch:{ IOException -> 0x004f }
            int r5 = r5.read(r7, r1, r2)     // Catch:{ IOException -> 0x004f }
            r7 = -1
            if (r5 != r7) goto L_0x003a
            r0.stopping = r6     // Catch:{ IOException -> 0x004f }
            return
        L_0x003a:
            if (r5 == r2) goto L_0x004f
            float[][] r7 = r0.osc_buff     // Catch:{ IOException -> 0x004f }
            r7 = r7[r1]     // Catch:{ IOException -> 0x004f }
            r8 = 0
            java.util.Arrays.fill(r7, r5, r2, r8)     // Catch:{ IOException -> 0x004f }
            int r7 = r0.nrofchannels     // Catch:{ IOException -> 0x004f }
            if (r7 == r6) goto L_0x004f
            float[][] r7 = r0.osc_buff     // Catch:{ IOException -> 0x004f }
            r7 = r7[r6]     // Catch:{ IOException -> 0x004f }
            java.util.Arrays.fill(r7, r5, r2, r8)     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            r2 = r19[r1]
            r7 = r19[r6]
            r5 = 2
            r5 = r19[r5]
            r8 = 6
            r8 = r19[r8]
            r9 = 7
            r9 = r19[r9]
            r10 = 3
            r10 = r19[r10]
            r11 = 4
            r11 = r19[r11]
            r12 = 5
            r12 = r19[r12]
            r13 = 8
            r13 = r19[r13]
            r14 = 9
            r14 = r19[r14]
            r4 = r19[r4]
            r3 = r19[r3]
            int r15 = r0.osc_stream_nrofchannels
            if (r15 != r6) goto L_0x0076
            r3 = 0
        L_0x0076:
            r15 = r3
            double[] r3 = r0.co_filter_freq
            r16 = r3[r1]
            boolean r1 = java.lang.Double.isInfinite(r16)
            if (r1 != 0) goto L_0x008d
            cn.sherlock.com.sun.media.sound.SoftFilter r1 = r0.filter_left
            r1.processAudio(r4)
            if (r15 == 0) goto L_0x008d
            cn.sherlock.com.sun.media.sound.SoftFilter r1 = r0.filter_right
            r1.processAudio(r15)
        L_0x008d:
            int r1 = r0.nrofchannels
            if (r1 != r6) goto L_0x00b0
            float r1 = r0.out_mixer_left
            float r3 = r0.out_mixer_right
            float r1 = r1 + r3
            r3 = 1073741824(0x40000000, float:2.0)
            float r5 = r1 / r3
            r0.out_mixer_left = r5
            r1 = r4
            float r4 = r0.last_out_mixer_left
            r3 = r10
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            r10 = r1
            if (r15 == 0) goto L_0x00ae
            float r4 = r0.last_out_mixer_left
            float r5 = r0.out_mixer_left
            r1 = r15
            r0.mixAudioStream(r1, r2, r3, r4, r5)
        L_0x00ae:
            r1 = r10
            goto L_0x00f2
        L_0x00b0:
            r16 = r2
            r17 = r10
            r10 = r4
            if (r15 != 0) goto L_0x00ce
            float r4 = r0.last_out_mixer_left
            float r1 = r0.last_out_mixer_right
            int r1 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00ce
            r2 = r5
            float r5 = r0.out_mixer_left
            float r1 = r0.out_mixer_right
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x00ce
            r1 = r10
            r3 = r12
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            goto L_0x00f2
        L_0x00ce:
            r1 = r10
            float r4 = r0.last_out_mixer_left
            float r5 = r0.out_mixer_left
            r2 = r16
            r3 = r17
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            r10 = r1
            if (r15 == 0) goto L_0x00e8
            float r4 = r0.last_out_mixer_right
            float r5 = r0.out_mixer_right
            r2 = r7
            r3 = r11
            r1 = r15
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            goto L_0x00ae
        L_0x00e8:
            r2 = r7
            r3 = r11
            float r4 = r0.last_out_mixer_right
            float r5 = r0.out_mixer_right
            r1 = r10
            r0.mixAudioStream(r1, r2, r3, r4, r5)
        L_0x00f2:
            if (r15 != 0) goto L_0x0107
            float r4 = r0.last_out_mixer_effect1
            float r5 = r0.out_mixer_effect1
            r2 = r8
            r3 = r13
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            float r4 = r0.last_out_mixer_effect2
            float r5 = r0.out_mixer_effect2
            r2 = r9
            r3 = r14
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            goto L_0x0140
        L_0x0107:
            r2 = r8
            r7 = r9
            r3 = r13
            r8 = r14
            float r4 = r0.last_out_mixer_effect1
            r9 = 1056964608(0x3f000000, float:0.5)
            float r4 = r4 * r9
            float r5 = r0.out_mixer_effect1
            float r5 = r5 * r9
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            r10 = r2
            r11 = r3
            float r2 = r0.last_out_mixer_effect2
            float r4 = r2 * r9
            float r2 = r0.out_mixer_effect2
            float r5 = r2 * r9
            r2 = r7
            r3 = r8
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            float r1 = r0.last_out_mixer_effect1
            float r4 = r1 * r9
            float r1 = r0.out_mixer_effect1
            float r5 = r1 * r9
            r2 = r10
            r3 = r11
            r1 = r15
            r0.mixAudioStream(r1, r2, r3, r4, r5)
            float r2 = r0.last_out_mixer_effect2
            float r4 = r2 * r9
            float r2 = r0.out_mixer_effect2
            float r5 = r2 * r9
            r2 = r7
            r3 = r8
            r0.mixAudioStream(r1, r2, r3, r4, r5)
        L_0x0140:
            float r1 = r0.out_mixer_left
            r0.last_out_mixer_left = r1
            float r1 = r0.out_mixer_right
            r0.last_out_mixer_right = r1
            float r1 = r0.out_mixer_effect1
            r0.last_out_mixer_effect1 = r1
            float r1 = r0.out_mixer_effect2
            r0.last_out_mixer_effect2 = r1
            boolean r1 = r0.out_mixer_end
            if (r1 == 0) goto L_0x0156
            r0.stopping = r6
        L_0x0156:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftVoice.processAudioLogic(cn.sherlock.com.sun.media.sound.SoftAudioBuffer[]):void");
    }
}
