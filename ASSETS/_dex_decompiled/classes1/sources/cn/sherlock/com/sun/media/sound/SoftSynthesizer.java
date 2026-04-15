package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.AudioSystem;
import cn.sherlock.javax.sound.sampled.LineUnavailableException;
import cn.sherlock.javax.sound.sampled.SourceDataLine;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.MidiDevice;
import jp.kshoji.javax.sound.midi.MidiUnavailableException;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Receiver;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.Transmitter;
import jp.kshoji.javax.sound.midi.VoiceStatus;

public class SoftSynthesizer implements AudioSynthesizer, ReferenceCountingDevice {
    protected static final String INFO_DESCRIPTION = "Software MIDI Synthesizer";
    protected static final String INFO_NAME = "Gervill";
    protected static final String INFO_VENDOR = "OpenJDK";
    protected static final String INFO_VERSION = "1.0";
    private static final String LOGTAG = "SoftSynthesizer";
    public static final int SOFT_CHANNEL_COUNT = 41;
    private static Soundbank defaultSoundBank = null;
    protected static final MidiDevice.Info info = new Info();
    private static SourceDataLine testline = null;
    protected boolean agc_on = true;
    protected SoftChannel[] channels;
    protected boolean chorus_on = true;
    protected Object control_mutex = this;
    private float controlrate = 147.0f;
    private int deviceid = 0;
    protected SoftChannelProxy[] external_channels = null;
    private AudioFormat format = new AudioFormat(44100.0f, 16, 2, true, false);
    private int gmmode = 0;
    private boolean implicitOpen = false;
    private Map<String, SoftInstrument> inslist = new HashMap();
    private boolean jitter_correction = false;
    private boolean largemode = false;
    private long latency = 200000;
    protected boolean load_default_soundbank = false;
    private Map<String, ModelInstrument> loadedlist = new HashMap();
    private SoftMainMixer mainmixer;
    private int maxpoly = 64;
    private int number_of_midi_channels = 41;
    private boolean open = false;
    private SoftAudioPusher pusher = null;
    private AudioInputStream pusher_stream = null;
    private ArrayList<Receiver> recvslist = new ArrayList<>();
    private SoftResampler resampler = new SoftLinearResampler();
    private String resamplerType = "linear";
    protected boolean reverb_light = true;
    protected boolean reverb_on = true;
    private SourceDataLine sourceDataLine = null;
    private Map<String, SoftTuning> tunings = new HashMap();
    protected int voiceIDCounter = 0;
    protected int voice_allocation_mode = 0;
    private SoftVoice[] voices;
    protected WeakAudioStream weakstream = null;

    public Soundbank getDefaultSoundbank() {
        return null;
    }

    public int getMaxReceivers() {
        return -1;
    }

    public int getMaxTransmitters() {
        return 0;
    }

    protected static class WeakAudioStream extends InputStream {
        private AudioFloatConverter converter;
        private int framesize = 0;
        public AudioInputStream jitter_stream = null;
        public SoftAudioPusher pusher = null;
        private int samplesize;
        public volatile long silent_samples = 0;
        private float[] silentbuffer = null;
        public SourceDataLine sourceDataLine = null;
        private volatile AudioInputStream stream;
        private WeakReference<AudioInputStream> weak_stream_link;

        public void setInputStream(AudioInputStream audioInputStream) {
            this.stream = audioInputStream;
        }

        public int available() throws IOException {
            AudioInputStream audioInputStream = this.stream;
            if (audioInputStream != null) {
                return audioInputStream.available();
            }
            return 0;
        }

        public int read() throws IOException {
            byte[] bArr = new byte[1];
            if (read(bArr) == -1) {
                return -1;
            }
            return bArr[0] & 255;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            AudioInputStream audioInputStream = this.stream;
            if (audioInputStream != null) {
                return audioInputStream.read(bArr, i, i2);
            }
            int i3 = i2 / this.samplesize;
            float[] fArr = this.silentbuffer;
            if (fArr == null || fArr.length < i3) {
                this.silentbuffer = new float[i3];
            }
            this.converter.toByteArray(this.silentbuffer, i3, bArr, i);
            this.silent_samples += (long) (i2 / this.framesize);
            if (this.pusher != null && this.weak_stream_link.get() == null) {
                AnonymousClass1 r4 = new Runnable() {
                    AudioInputStream _jitter_stream;
                    SoftAudioPusher _pusher;
                    SourceDataLine _sourceDataLine;

                    {
                        this._pusher = WeakAudioStream.this.pusher;
                        this._jitter_stream = WeakAudioStream.this.jitter_stream;
                        this._sourceDataLine = WeakAudioStream.this.sourceDataLine;
                    }

                    public void run() {
                        this._pusher.stop();
                        AudioInputStream audioInputStream = this._jitter_stream;
                        if (audioInputStream != null) {
                            try {
                                audioInputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        SourceDataLine sourceDataLine = this._sourceDataLine;
                        if (sourceDataLine != null) {
                            sourceDataLine.close();
                        }
                    }
                };
                this.pusher = null;
                this.jitter_stream = null;
                this.sourceDataLine = null;
                new Thread(r4).start();
            }
            return i2;
        }

        public WeakAudioStream(AudioInputStream audioInputStream) {
            this.stream = audioInputStream;
            this.weak_stream_link = new WeakReference<>(audioInputStream);
            this.converter = AudioFloatConverter.getConverter(audioInputStream.getFormat());
            this.samplesize = audioInputStream.getFormat().getFrameSize() / audioInputStream.getFormat().getChannels();
            this.framesize = audioInputStream.getFormat().getFrameSize();
        }

        public AudioInputStream getAudioInputStream() {
            return new AudioInputStream(this, this.stream.getFormat(), -1);
        }

        public void close() throws IOException {
            AudioInputStream audioInputStream = (AudioInputStream) this.weak_stream_link.get();
            if (audioInputStream != null) {
                audioInputStream.close();
            }
        }
    }

    private static class Info extends MidiDevice.Info {
        public Info() {
            super(SoftSynthesizer.INFO_NAME, SoftSynthesizer.INFO_VENDOR, SoftSynthesizer.INFO_DESCRIPTION, SoftSynthesizer.INFO_VERSION);
        }
    }

    private void getBuffers(ModelInstrument modelInstrument, List<ModelByteBuffer> list) {
        for (ModelPerformer modelPerformer : modelInstrument.getPerformers()) {
            if (modelPerformer.getOscillators() != null) {
                for (ModelOscillator next : modelPerformer.getOscillators()) {
                    if (next instanceof ModelByteBufferWavetable) {
                        ModelByteBufferWavetable modelByteBufferWavetable = (ModelByteBufferWavetable) next;
                        ModelByteBuffer buffer = modelByteBufferWavetable.getBuffer();
                        if (buffer != null) {
                            list.add(buffer);
                        }
                        ModelByteBuffer modelByteBuffer = modelByteBufferWavetable.get8BitExtensionBuffer();
                        if (modelByteBuffer != null) {
                            list.add(modelByteBuffer);
                        }
                    }
                }
            }
        }
    }

    private boolean loadSamples(List<ModelInstrument> list) {
        if (this.largemode) {
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (ModelInstrument buffers : list) {
            getBuffers(buffers, arrayList);
        }
        try {
            ModelByteBuffer.loadAll(arrayList);
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    private boolean loadInstruments(List<ModelInstrument> list) {
        if (!isOpen() || !loadSamples(list)) {
            return false;
        }
        synchronized (this.control_mutex) {
            SoftChannel[] softChannelArr = this.channels;
            if (softChannelArr != null) {
                for (SoftChannel softChannel : softChannelArr) {
                    softChannel.current_instrument = null;
                    softChannel.current_director = null;
                }
            }
            for (ModelInstrument modelInstrument : list) {
                String patchToString = patchToString(modelInstrument.getPatch());
                this.inslist.put(patchToString, new SoftInstrument(modelInstrument));
                this.loadedlist.put(patchToString, modelInstrument);
            }
        }
        return true;
    }

    private void processPropertyInfo(Map<String, Object> map) {
        AudioSynthesizerPropertyInfo[] propertyInfo = getPropertyInfo(map);
        String str = (String) propertyInfo[0].value;
        if (str.equalsIgnoreCase("point")) {
            this.resampler = new SoftPointResampler();
            this.resamplerType = "point";
        } else if (str.equalsIgnoreCase("linear")) {
            this.resampler = new SoftLinearResampler2();
            this.resamplerType = "linear";
        } else if (str.equalsIgnoreCase("linear1")) {
            this.resampler = new SoftLinearResampler();
            this.resamplerType = "linear1";
        } else if (str.equalsIgnoreCase("linear2")) {
            this.resampler = new SoftLinearResampler2();
            this.resamplerType = "linear2";
        } else if (str.equalsIgnoreCase("cubic")) {
            this.resampler = new SoftCubicResampler();
            this.resamplerType = "cubic";
        } else if (str.equalsIgnoreCase("lanczos")) {
            this.resampler = new SoftLanczosResampler();
            this.resamplerType = "lanczos";
        } else if (str.equalsIgnoreCase("sinc")) {
            this.resampler = new SoftSincResampler();
            this.resamplerType = "sinc";
        }
        setFormat((AudioFormat) propertyInfo[2].value);
        this.controlrate = ((Float) propertyInfo[1].value).floatValue();
        this.latency = ((Long) propertyInfo[3].value).longValue();
        this.deviceid = ((Integer) propertyInfo[4].value).intValue();
        this.maxpoly = ((Integer) propertyInfo[5].value).intValue();
        this.reverb_on = ((Boolean) propertyInfo[6].value).booleanValue();
        this.chorus_on = ((Boolean) propertyInfo[7].value).booleanValue();
        this.agc_on = ((Boolean) propertyInfo[8].value).booleanValue();
        this.largemode = ((Boolean) propertyInfo[9].value).booleanValue();
        this.number_of_midi_channels = ((Integer) propertyInfo[10].value).intValue();
        this.jitter_correction = ((Boolean) propertyInfo[11].value).booleanValue();
        this.reverb_light = ((Boolean) propertyInfo[12].value).booleanValue();
        this.load_default_soundbank = ((Boolean) propertyInfo[13].value).booleanValue();
    }

    private String patchToString(Patch patch) {
        if (!(patch instanceof ModelPatch) || !((ModelPatch) patch).isPercussion()) {
            return patch.getProgram() + "." + patch.getBank();
        }
        return "p." + patch.getProgram() + "." + patch.getBank();
    }

    private void setFormat(AudioFormat audioFormat) {
        if (audioFormat.getChannels() > 2) {
            throw new IllegalArgumentException("Only mono and stereo audio supported.");
        } else if (AudioFloatConverter.getConverter(audioFormat) != null) {
            this.format = audioFormat;
        } else {
            throw new IllegalArgumentException("Audio format not supported.");
        }
    }

    /* access modifiers changed from: protected */
    public void removeReceiver(Receiver receiver) {
        boolean z;
        synchronized (this.control_mutex) {
            z = this.recvslist.remove(receiver) && this.implicitOpen && this.recvslist.isEmpty();
        }
        if (z) {
            close();
        }
    }

    /* access modifiers changed from: protected */
    public SoftMainMixer getMainMixer() {
        if (!isOpen()) {
            return null;
        }
        return this.mainmixer;
    }

    /* access modifiers changed from: protected */
    public SoftInstrument findInstrument(int i, int i2, int i3) {
        int i4 = i2 >> 7;
        String str = "p.";
        if (i4 == 120 || i4 == 121) {
            Map<String, SoftInstrument> map = this.inslist;
            SoftInstrument softInstrument = map.get(i + "." + i2);
            if (softInstrument != null) {
                return softInstrument;
            }
            if (i4 != 120) {
                str = "";
            }
            Map<String, SoftInstrument> map2 = this.inslist;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(i);
            sb.append(".");
            int i5 = i2 & 128;
            sb.append(i5 << 7);
            SoftInstrument softInstrument2 = map2.get(sb.toString());
            if (softInstrument2 != null) {
                return softInstrument2;
            }
            Map<String, SoftInstrument> map3 = this.inslist;
            SoftInstrument softInstrument3 = map3.get(str + i + "." + i5);
            if (softInstrument3 != null) {
                return softInstrument3;
            }
            Map<String, SoftInstrument> map4 = this.inslist;
            SoftInstrument softInstrument4 = map4.get(str + i + ".0");
            if (softInstrument4 != null) {
                return softInstrument4;
            }
            Map<String, SoftInstrument> map5 = this.inslist;
            SoftInstrument softInstrument5 = map5.get(str + i + "0.0");
            if (softInstrument5 != null) {
                return softInstrument5;
            }
            return null;
        }
        if (i3 != 9) {
            str = "";
        }
        Map<String, SoftInstrument> map6 = this.inslist;
        SoftInstrument softInstrument6 = map6.get(str + i + "." + i2);
        if (softInstrument6 != null) {
            return softInstrument6;
        }
        Map<String, SoftInstrument> map7 = this.inslist;
        SoftInstrument softInstrument7 = map7.get(str + i + ".0");
        if (softInstrument7 != null) {
            return softInstrument7;
        }
        SoftInstrument softInstrument8 = this.inslist.get(str.concat("0.0"));
        if (softInstrument8 != null) {
            return softInstrument8;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public int getVoiceAllocationMode() {
        return this.voice_allocation_mode;
    }

    /* access modifiers changed from: protected */
    public int getGeneralMidiMode() {
        return this.gmmode;
    }

    /* access modifiers changed from: protected */
    public void setGeneralMidiMode(int i) {
        this.gmmode = i;
    }

    /* access modifiers changed from: protected */
    public int getDeviceID() {
        return this.deviceid;
    }

    /* access modifiers changed from: protected */
    public float getControlRate() {
        return this.controlrate;
    }

    /* access modifiers changed from: protected */
    public SoftVoice[] getVoices() {
        return this.voices;
    }

    /* access modifiers changed from: protected */
    public SoftTuning getTuning(Patch patch) {
        String patchToString = patchToString(patch);
        SoftTuning softTuning = this.tunings.get(patchToString);
        if (softTuning != null) {
            return softTuning;
        }
        SoftTuning softTuning2 = new SoftTuning(patch);
        this.tunings.put(patchToString, softTuning2);
        return softTuning2;
    }

    public long getLatency() {
        long j;
        synchronized (this.control_mutex) {
            j = this.latency;
        }
        return j;
    }

    public AudioFormat getFormat() {
        AudioFormat audioFormat;
        synchronized (this.control_mutex) {
            audioFormat = this.format;
        }
        return audioFormat;
    }

    public int getMaxPolyphony() {
        int i;
        synchronized (this.control_mutex) {
            i = this.maxpoly;
        }
        return i;
    }

    public MidiChannel[] getChannels() {
        MidiChannel[] midiChannelArr;
        synchronized (this.control_mutex) {
            if (this.external_channels == null) {
                this.external_channels = new SoftChannelProxy[16];
                int i = 0;
                while (true) {
                    SoftChannelProxy[] softChannelProxyArr = this.external_channels;
                    if (i >= softChannelProxyArr.length) {
                        break;
                    }
                    softChannelProxyArr[i] = new SoftChannelProxy();
                    i++;
                }
            }
            if (isOpen()) {
                midiChannelArr = new MidiChannel[this.channels.length];
            } else {
                midiChannelArr = new MidiChannel[16];
            }
            for (int i2 = 0; i2 < midiChannelArr.length; i2++) {
                midiChannelArr[i2] = this.external_channels[i2];
            }
        }
        return midiChannelArr;
    }

    public VoiceStatus[] getVoiceStatus() {
        VoiceStatus[] voiceStatusArr;
        int i = 0;
        if (!isOpen()) {
            int maxPolyphony = getMaxPolyphony();
            VoiceStatus[] voiceStatusArr2 = new VoiceStatus[maxPolyphony];
            for (int i2 = 0; i2 < maxPolyphony; i2++) {
                VoiceStatus voiceStatus = new VoiceStatus();
                voiceStatus.active = false;
                voiceStatus.bank = 0;
                voiceStatus.channel = 0;
                voiceStatus.note = 0;
                voiceStatus.program = 0;
                voiceStatus.volume = 0;
                voiceStatusArr2[i2] = voiceStatus;
            }
            return voiceStatusArr2;
        }
        synchronized (this.control_mutex) {
            voiceStatusArr = new VoiceStatus[this.voices.length];
            while (true) {
                VoiceStatus[] voiceStatusArr3 = this.voices;
                if (i < voiceStatusArr3.length) {
                    VoiceStatus voiceStatus2 = voiceStatusArr3[i];
                    VoiceStatus voiceStatus3 = new VoiceStatus();
                    voiceStatus3.active = voiceStatus2.active;
                    voiceStatus3.bank = voiceStatus2.bank;
                    voiceStatus3.channel = voiceStatus2.channel;
                    voiceStatus3.note = voiceStatus2.note;
                    voiceStatus3.program = voiceStatus2.program;
                    voiceStatus3.volume = voiceStatus2.volume;
                    voiceStatusArr[i] = voiceStatus3;
                    i++;
                }
            }
        }
        return voiceStatusArr;
    }

    public boolean isSoundbankSupported(Soundbank soundbank) {
        for (Instrument instrument : soundbank.getInstruments()) {
            if (!(instrument instanceof ModelInstrument)) {
                return false;
            }
        }
        return true;
    }

    public boolean loadInstrument(Instrument instrument) {
        if (instrument == null || !(instrument instanceof ModelInstrument)) {
            throw new IllegalArgumentException("Unsupported instrument: " + instrument);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add((ModelInstrument) instrument);
        return loadInstruments(arrayList);
    }

    public void unloadInstrument(Instrument instrument) {
        if (instrument == null || !(instrument instanceof ModelInstrument)) {
            throw new IllegalArgumentException("Unsupported instrument: " + instrument);
        } else if (isOpen()) {
            String patchToString = patchToString(instrument.getPatch());
            synchronized (this.control_mutex) {
                int i = 0;
                for (SoftChannel softChannel : this.channels) {
                    softChannel.current_instrument = null;
                }
                this.inslist.remove(patchToString);
                this.loadedlist.remove(patchToString);
                while (true) {
                    SoftChannel[] softChannelArr = this.channels;
                    if (i < softChannelArr.length) {
                        softChannelArr[i].allSoundOff();
                        i++;
                    }
                }
            }
        }
    }

    public boolean remapInstrument(Instrument instrument, Instrument instrument2) {
        boolean loadInstrument;
        instrument.getClass();
        instrument2.getClass();
        if (!(instrument instanceof ModelInstrument)) {
            throw new IllegalArgumentException("Unsupported instrument: " + instrument.toString());
        } else if (!(instrument2 instanceof ModelInstrument)) {
            throw new IllegalArgumentException("Unsupported instrument: " + instrument2.toString());
        } else if (!isOpen()) {
            return false;
        } else {
            synchronized (this.control_mutex) {
                if (this.loadedlist.containsValue(instrument2)) {
                    unloadInstrument(instrument);
                    loadInstrument = loadInstrument(new ModelMappedInstrument((ModelInstrument) instrument2, instrument.getPatch()));
                } else {
                    throw new IllegalArgumentException("Instrument to is not loaded.");
                }
            }
            return loadInstrument;
        }
    }

    public Instrument[] getAvailableInstruments() {
        Soundbank defaultSoundbank = getDefaultSoundbank();
        if (defaultSoundbank == null) {
            return new Instrument[0];
        }
        Instrument[] instruments = defaultSoundbank.getInstruments();
        Arrays.sort(instruments, new ModelInstrumentComparator());
        return instruments;
    }

    public Instrument[] getLoadedInstruments() {
        ModelInstrument[] modelInstrumentArr;
        if (!isOpen()) {
            return new Instrument[0];
        }
        synchronized (this.control_mutex) {
            modelInstrumentArr = new ModelInstrument[this.loadedlist.values().size()];
            this.loadedlist.values().toArray(modelInstrumentArr);
            Arrays.sort(modelInstrumentArr, new ModelInstrumentComparator());
        }
        return modelInstrumentArr;
    }

    public boolean loadAllInstruments(Soundbank soundbank) {
        ArrayList arrayList = new ArrayList();
        for (ModelInstrument modelInstrument : soundbank.getInstruments()) {
            if (modelInstrument == null || !(modelInstrument instanceof ModelInstrument)) {
                throw new IllegalArgumentException("Unsupported instrument: " + modelInstrument);
            }
            arrayList.add(modelInstrument);
        }
        return loadInstruments(arrayList);
    }

    public void unloadAllInstruments(Soundbank soundbank) {
        if (soundbank == null || !isSoundbankSupported(soundbank)) {
            throw new IllegalArgumentException("Unsupported soundbank: " + soundbank);
        } else if (isOpen()) {
            for (Instrument instrument : soundbank.getInstruments()) {
                if (instrument instanceof ModelInstrument) {
                    unloadInstrument(instrument);
                }
            }
        }
    }

    public boolean loadInstruments(Soundbank soundbank, Patch[] patchArr) {
        ArrayList arrayList = new ArrayList();
        for (Patch instrument : patchArr) {
            ModelInstrument instrument2 = soundbank.getInstrument(instrument);
            if (instrument2 == null || !(instrument2 instanceof ModelInstrument)) {
                throw new IllegalArgumentException("Unsupported instrument: " + instrument2);
            }
            arrayList.add(instrument2);
        }
        return loadInstruments(arrayList);
    }

    public void unloadInstruments(Soundbank soundbank, Patch[] patchArr) {
        if (soundbank == null || !isSoundbankSupported(soundbank)) {
            throw new IllegalArgumentException("Unsupported soundbank: " + soundbank);
        } else if (isOpen()) {
            for (Patch instrument : patchArr) {
                Instrument instrument2 = soundbank.getInstrument(instrument);
                if (instrument2 instanceof ModelInstrument) {
                    unloadInstrument(instrument2);
                }
            }
        }
    }

    public MidiDevice.Info getDeviceInfo() {
        return info;
    }

    private Properties getStoredProperties() {
        return (Properties) AccessController.doPrivileged(new PrivilegedAction<Properties>() {
            public Properties run() {
                Properties properties = new Properties();
                try {
                    Preferences userRoot = Preferences.userRoot();
                    if (userRoot.nodeExists("/com/sun/media/sound/softsynthesizer")) {
                        Preferences node = userRoot.node("/com/sun/media/sound/softsynthesizer");
                        for (String str : node.keys()) {
                            String str2 = node.get(str, (String) null);
                            if (str2 != null) {
                                properties.setProperty(str, str2);
                            }
                        }
                    }
                } catch (SecurityException | BackingStoreException unused) {
                }
                return properties;
            }
        });
    }

    public AudioSynthesizerPropertyInfo[] getPropertyInfo(Map<String, Object> map) {
        Object obj;
        Map<String, Object> map2 = map;
        ArrayList arrayList = new ArrayList();
        boolean z = map2 == null && this.open;
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo = new AudioSynthesizerPropertyInfo("interpolation", z ? this.resamplerType : "linear");
        audioSynthesizerPropertyInfo.choices = new String[]{"linear", "linear1", "linear2", "cubic", "lanczos", "sinc", "point"};
        audioSynthesizerPropertyInfo.description = "Interpolation method";
        arrayList.add(audioSynthesizerPropertyInfo);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo2 = new AudioSynthesizerPropertyInfo("control rate", Float.valueOf(z ? this.controlrate : 147.0f));
        audioSynthesizerPropertyInfo2.description = "Control rate";
        arrayList.add(audioSynthesizerPropertyInfo2);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo3 = new AudioSynthesizerPropertyInfo("format", z ? this.format : new AudioFormat(44100.0f, 16, 2, true, false));
        audioSynthesizerPropertyInfo3.description = "Default audio format";
        arrayList.add(audioSynthesizerPropertyInfo3);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo4 = new AudioSynthesizerPropertyInfo("latency", Long.valueOf(z ? this.latency : 120000));
        audioSynthesizerPropertyInfo4.description = "Default latency";
        arrayList.add(audioSynthesizerPropertyInfo4);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo5 = new AudioSynthesizerPropertyInfo("device id", Integer.valueOf(z ? this.deviceid : 0));
        audioSynthesizerPropertyInfo5.description = "Device ID for SysEx Messages";
        arrayList.add(audioSynthesizerPropertyInfo5);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo6 = new AudioSynthesizerPropertyInfo("max polyphony", Integer.valueOf(z ? this.maxpoly : 64));
        audioSynthesizerPropertyInfo6.description = "Maximum polyphony";
        arrayList.add(audioSynthesizerPropertyInfo6);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo7 = new AudioSynthesizerPropertyInfo("reverb", Boolean.valueOf(z ? this.reverb_on : true));
        audioSynthesizerPropertyInfo7.description = "Turn reverb effect on or off";
        arrayList.add(audioSynthesizerPropertyInfo7);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo8 = new AudioSynthesizerPropertyInfo("chorus", Boolean.valueOf(z ? this.chorus_on : true));
        audioSynthesizerPropertyInfo8.description = "Turn chorus effect on or off";
        arrayList.add(audioSynthesizerPropertyInfo8);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo9 = new AudioSynthesizerPropertyInfo("auto gain control", Boolean.valueOf(z ? this.agc_on : true));
        audioSynthesizerPropertyInfo9.description = "Turn auto gain control on or off";
        arrayList.add(audioSynthesizerPropertyInfo9);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo10 = new AudioSynthesizerPropertyInfo("large mode", Boolean.valueOf(z ? this.largemode : false));
        audioSynthesizerPropertyInfo10.description = "Turn large mode on or off.";
        arrayList.add(audioSynthesizerPropertyInfo10);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo11 = new AudioSynthesizerPropertyInfo("midi channels", Integer.valueOf(z ? this.channels.length : 41));
        audioSynthesizerPropertyInfo11.description = "Number of midi channels.";
        arrayList.add(audioSynthesizerPropertyInfo11);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo12 = new AudioSynthesizerPropertyInfo("jitter correction", Boolean.valueOf(z ? this.jitter_correction : true));
        audioSynthesizerPropertyInfo12.description = "Turn jitter correction on or off.";
        arrayList.add(audioSynthesizerPropertyInfo12);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo13 = new AudioSynthesizerPropertyInfo("light reverb", Boolean.valueOf(z ? this.reverb_light : true));
        audioSynthesizerPropertyInfo13.description = "Turn light reverb mode on or off";
        arrayList.add(audioSynthesizerPropertyInfo13);
        AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo14 = new AudioSynthesizerPropertyInfo("load default soundbank", Boolean.valueOf(z ? this.load_default_soundbank : true));
        audioSynthesizerPropertyInfo14.description = "Enabled/disable loading default soundbank";
        arrayList.add(audioSynthesizerPropertyInfo14);
        AudioSynthesizerPropertyInfo[] audioSynthesizerPropertyInfoArr = (AudioSynthesizerPropertyInfo[]) arrayList.toArray(new AudioSynthesizerPropertyInfo[arrayList.size()]);
        Properties storedProperties = getStoredProperties();
        for (AudioSynthesizerPropertyInfo audioSynthesizerPropertyInfo15 : audioSynthesizerPropertyInfoArr) {
            if (map2 == null) {
                obj = null;
            } else {
                obj = map2.get(audioSynthesizerPropertyInfo15.name);
            }
            if (obj == null) {
                obj = storedProperties.getProperty(audioSynthesizerPropertyInfo15.name);
            }
            if (obj != null) {
                Class<AudioFormat> cls = audioSynthesizerPropertyInfo15.valueClass;
                if (cls.isInstance(obj)) {
                    audioSynthesizerPropertyInfo15.value = obj;
                } else if (obj instanceof String) {
                    String str = (String) obj;
                    if (cls == Boolean.class) {
                        if (str.equalsIgnoreCase("true")) {
                            audioSynthesizerPropertyInfo15.value = Boolean.TRUE;
                        }
                        if (str.equalsIgnoreCase("false")) {
                            audioSynthesizerPropertyInfo15.value = Boolean.FALSE;
                        }
                    } else if (cls == AudioFormat.class) {
                        try {
                            StringTokenizer stringTokenizer = new StringTokenizer(str, ", ");
                            String str2 = "";
                            int i = 16;
                            float f = 44100.0f;
                            boolean z2 = true;
                            int i2 = 2;
                            boolean z3 = false;
                            while (stringTokenizer.hasMoreTokens()) {
                                String lowerCase = stringTokenizer.nextToken().toLowerCase();
                                if (lowerCase.equals("mono")) {
                                    i2 = 1;
                                }
                                if (lowerCase.startsWith("channel")) {
                                    i2 = Integer.parseInt(str2);
                                }
                                if (lowerCase.contains("unsigned")) {
                                    z2 = false;
                                }
                                if (lowerCase.equals("big-endian")) {
                                    z3 = true;
                                }
                                if (lowerCase.equals("bit")) {
                                    i = Integer.parseInt(str2);
                                }
                                if (lowerCase.equals("hz")) {
                                    f = Float.parseFloat(str2);
                                }
                                str2 = lowerCase;
                            }
                            audioSynthesizerPropertyInfo15.value = new AudioFormat(f, i, i2, z2, z3);
                        } catch (NumberFormatException unused) {
                        }
                    } else if (cls == Byte.class) {
                        audioSynthesizerPropertyInfo15.value = Byte.valueOf(str);
                    } else if (cls == Short.class) {
                        audioSynthesizerPropertyInfo15.value = Short.valueOf(str);
                    } else if (cls == Integer.class) {
                        audioSynthesizerPropertyInfo15.value = Integer.valueOf(str);
                    } else if (cls == Long.class) {
                        audioSynthesizerPropertyInfo15.value = Long.valueOf(str);
                    } else if (cls == Float.class) {
                        audioSynthesizerPropertyInfo15.value = Float.valueOf(str);
                    } else if (cls == Double.class) {
                        audioSynthesizerPropertyInfo15.value = Double.valueOf(str);
                    }
                } else if (obj instanceof Number) {
                    Number number = (Number) obj;
                    if (cls == Byte.class) {
                        audioSynthesizerPropertyInfo15.value = Byte.valueOf(number.byteValue());
                    }
                    if (cls == Short.class) {
                        audioSynthesizerPropertyInfo15.value = Short.valueOf(number.shortValue());
                    }
                    if (cls == Integer.class) {
                        audioSynthesizerPropertyInfo15.value = Integer.valueOf(number.intValue());
                    }
                    if (cls == Long.class) {
                        audioSynthesizerPropertyInfo15.value = Long.valueOf(number.longValue());
                    }
                    if (cls == Float.class) {
                        audioSynthesizerPropertyInfo15.value = Float.valueOf(number.floatValue());
                    }
                    if (cls == Double.class) {
                        audioSynthesizerPropertyInfo15.value = Double.valueOf(number.doubleValue());
                    }
                }
            }
        }
        return audioSynthesizerPropertyInfoArr;
    }

    public void open() throws MidiUnavailableException {
        if (isOpen()) {
            synchronized (this.control_mutex) {
                this.implicitOpen = false;
            }
            return;
        }
        open((SourceDataLine) null, (Map<String, Object>) null);
    }

    public void open(SourceDataLine sourceDataLine2, Map<String, Object> map) throws MidiUnavailableException {
        int i;
        if (isOpen()) {
            synchronized (this.control_mutex) {
                this.implicitOpen = false;
            }
            return;
        }
        synchronized (this.control_mutex) {
            if (sourceDataLine2 != null) {
                try {
                    setFormat(sourceDataLine2.getFormat());
                } catch (LineUnavailableException | IllegalArgumentException | SecurityException e) {
                    e = e;
                }
            }
            WeakAudioStream weakAudioStream = new WeakAudioStream(openStream(getFormat(), map));
            this.weakstream = weakAudioStream;
            AudioInputStream audioInputStream = weakAudioStream.getAudioInputStream();
            if (sourceDataLine2 == null) {
                sourceDataLine2 = testline;
                if (sourceDataLine2 == null) {
                    sourceDataLine2 = AudioSystem.getSourceDataLine(getFormat());
                }
            }
            double d = (double) this.latency;
            if (!sourceDataLine2.isOpen()) {
                sourceDataLine2.open(getFormat(), getFormat().getFrameSize() * ((int) (((double) getFormat().getFrameRate()) * (d / 1000000.0d))));
                this.sourceDataLine = sourceDataLine2;
            }
            if (!sourceDataLine2.isActive()) {
                sourceDataLine2.start();
            }
            try {
                i = audioInputStream.available();
            } catch (IOException unused) {
                i = 512;
            }
            int bufferSize = sourceDataLine2.getBufferSize();
            int i2 = bufferSize - (bufferSize % i);
            int i3 = i * 3;
            if (i2 < i3) {
                i2 = i3;
            }
            if (this.jitter_correction) {
                SoftJitterCorrector softJitterCorrector = new SoftJitterCorrector(audioInputStream, i2, i);
                WeakAudioStream weakAudioStream2 = this.weakstream;
                if (weakAudioStream2 != null) {
                    weakAudioStream2.jitter_stream = softJitterCorrector;
                }
                audioInputStream = softJitterCorrector;
            }
            SoftAudioPusher softAudioPusher = new SoftAudioPusher(sourceDataLine2, audioInputStream, i);
            this.pusher = softAudioPusher;
            this.pusher_stream = audioInputStream;
            softAudioPusher.start();
            WeakAudioStream weakAudioStream3 = this.weakstream;
            if (weakAudioStream3 != null) {
                weakAudioStream3.pusher = this.pusher;
                this.weakstream.sourceDataLine = this.sourceDataLine;
            }
            e = null;
            if (e != null) {
                if (isOpen()) {
                    close();
                }
                MidiUnavailableException midiUnavailableException = new MidiUnavailableException("Can not open line");
                midiUnavailableException.initCause(e);
                throw midiUnavailableException;
            }
        }
    }

    public AudioInputStream openStream(AudioFormat audioFormat, Map<String, Object> map) throws MidiUnavailableException {
        SoftChannel[] softChannelArr;
        AudioInputStream inputStream;
        SoftChannelProxy[] softChannelProxyArr;
        Soundbank defaultSoundbank;
        if (!isOpen()) {
            synchronized (this.control_mutex) {
                this.gmmode = 0;
                this.voice_allocation_mode = 0;
                processPropertyInfo(map);
                this.open = true;
                this.implicitOpen = false;
                if (audioFormat != null) {
                    setFormat(audioFormat);
                }
                if (this.load_default_soundbank && (defaultSoundbank = getDefaultSoundbank()) != null) {
                    loadAllInstruments(defaultSoundbank);
                }
                this.voices = new SoftVoice[this.maxpoly];
                for (int i = 0; i < this.maxpoly; i++) {
                    this.voices[i] = new SoftVoice(this);
                }
                this.mainmixer = new SoftMainMixer(this);
                this.channels = new SoftChannel[this.number_of_midi_channels];
                int i2 = 0;
                while (true) {
                    softChannelArr = this.channels;
                    if (i2 >= softChannelArr.length) {
                        break;
                    }
                    softChannelArr[i2] = new SoftChannel(this, i2);
                    i2++;
                }
                SoftChannelProxy[] softChannelProxyArr2 = this.external_channels;
                if (softChannelProxyArr2 == null) {
                    if (softChannelArr.length < 16) {
                        this.external_channels = new SoftChannelProxy[16];
                    } else {
                        this.external_channels = new SoftChannelProxy[softChannelArr.length];
                    }
                    int i3 = 0;
                    while (true) {
                        SoftChannelProxy[] softChannelProxyArr3 = this.external_channels;
                        if (i3 >= softChannelProxyArr3.length) {
                            break;
                        }
                        softChannelProxyArr3[i3] = new SoftChannelProxy();
                        i3++;
                    }
                } else if (softChannelArr.length > softChannelProxyArr2.length) {
                    int length = softChannelArr.length;
                    SoftChannelProxy[] softChannelProxyArr4 = new SoftChannelProxy[length];
                    int i4 = 0;
                    while (true) {
                        softChannelProxyArr = this.external_channels;
                        if (i4 >= softChannelProxyArr.length) {
                            break;
                        }
                        softChannelProxyArr4[i4] = softChannelProxyArr[i4];
                        i4++;
                    }
                    for (int length2 = softChannelProxyArr.length; length2 < length; length2++) {
                        softChannelProxyArr4[length2] = new SoftChannelProxy();
                    }
                }
                int i5 = 0;
                while (true) {
                    MidiChannel[] midiChannelArr = this.channels;
                    if (i5 >= midiChannelArr.length) {
                        break;
                    }
                    this.external_channels[i5].setChannel(midiChannelArr[i5]);
                    i5++;
                }
                for (SoftVoice softVoice : getVoices()) {
                    softVoice.resampler = this.resampler.openStreamer();
                }
                Iterator<Receiver> it = getReceivers().iterator();
                while (it.hasNext()) {
                    SoftReceiver softReceiver = (Receiver) it.next();
                    softReceiver.open = this.open;
                    softReceiver.mainmixer = this.mainmixer;
                    softReceiver.midimessages = this.mainmixer.midimessages;
                }
                inputStream = this.mainmixer.getInputStream();
            }
            return inputStream;
        }
        throw new MidiUnavailableException("Synthesizer is already open");
    }

    public void close() {
        SoftAudioPusher softAudioPusher;
        AudioInputStream audioInputStream;
        if (isOpen()) {
            synchronized (this.control_mutex) {
                softAudioPusher = this.pusher;
                if (softAudioPusher != null) {
                    audioInputStream = this.pusher_stream;
                    this.pusher = null;
                    this.pusher_stream = null;
                } else {
                    softAudioPusher = null;
                    audioInputStream = null;
                }
            }
            if (softAudioPusher != null) {
                softAudioPusher.stop();
                try {
                    audioInputStream.close();
                } catch (IOException unused) {
                }
            }
            synchronized (this.control_mutex) {
                SoftMainMixer softMainMixer = this.mainmixer;
                if (softMainMixer != null) {
                    softMainMixer.close();
                }
                int i = 0;
                this.open = false;
                this.implicitOpen = false;
                this.mainmixer = null;
                this.voices = null;
                this.channels = null;
                if (this.external_channels != null) {
                    while (true) {
                        SoftChannelProxy[] softChannelProxyArr = this.external_channels;
                        if (i >= softChannelProxyArr.length) {
                            break;
                        }
                        softChannelProxyArr[i].setChannel((MidiChannel) null);
                        i++;
                    }
                }
                SourceDataLine sourceDataLine2 = this.sourceDataLine;
                if (sourceDataLine2 != null) {
                    sourceDataLine2.close();
                    this.sourceDataLine = null;
                }
                this.inslist.clear();
                this.loadedlist.clear();
                this.tunings.clear();
                while (this.recvslist.size() != 0) {
                    ArrayList<Receiver> arrayList = this.recvslist;
                    arrayList.get(arrayList.size() - 1).close();
                }
            }
        }
    }

    public boolean isOpen() {
        boolean z;
        synchronized (this.control_mutex) {
            z = this.open;
        }
        return z;
    }

    public long getMicrosecondPosition() {
        long microsecondPosition;
        if (!isOpen()) {
            return 0;
        }
        synchronized (this.control_mutex) {
            microsecondPosition = this.mainmixer.getMicrosecondPosition();
        }
        return microsecondPosition;
    }

    public Receiver getReceiver() throws MidiUnavailableException {
        SoftReceiver softReceiver;
        synchronized (this.control_mutex) {
            softReceiver = new SoftReceiver(this);
            softReceiver.open = this.open;
            this.recvslist.add(softReceiver);
        }
        return softReceiver;
    }

    public List<Receiver> getReceivers() {
        ArrayList arrayList;
        synchronized (this.control_mutex) {
            arrayList = new ArrayList();
            arrayList.addAll(this.recvslist);
        }
        return arrayList;
    }

    public Transmitter getTransmitter() throws MidiUnavailableException {
        throw new MidiUnavailableException("No transmitter available");
    }

    public List<Transmitter> getTransmitters() {
        return new ArrayList();
    }

    public Receiver getReceiverReferenceCounting() throws MidiUnavailableException {
        if (!isOpen()) {
            open();
            synchronized (this.control_mutex) {
                this.implicitOpen = true;
            }
        }
        return getReceiver();
    }

    public Transmitter getTransmitterReferenceCounting() throws MidiUnavailableException {
        throw new MidiUnavailableException("No transmitter available");
    }
}
