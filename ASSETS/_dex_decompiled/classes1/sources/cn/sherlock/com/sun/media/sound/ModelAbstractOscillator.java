package cn.sherlock.com.sun.media.sound;

import java.io.IOException;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.SoundbankResource;
import jp.kshoji.javax.sound.midi.VoiceStatus;

public abstract class ModelAbstractOscillator implements ModelOscillator, ModelOscillatorStream, Soundbank {
    protected MidiChannel channel;
    protected int noteNumber;
    protected boolean on = false;
    protected float pitch = 6000.0f;
    protected float samplerate;
    protected int velocity;
    protected VoiceStatus voice;

    public void close() throws IOException {
    }

    public float getAttenuation() {
        return 0.0f;
    }

    public int getChannels() {
        return 1;
    }

    public String getVendor() {
        return null;
    }

    public String getVersion() {
        return null;
    }

    public void init() {
    }

    public int read(float[][] fArr, int i, int i2) throws IOException {
        return -1;
    }

    public void noteOff(int i) {
        this.on = false;
    }

    public void noteOn(MidiChannel midiChannel, VoiceStatus voiceStatus, int i, int i2) {
        this.channel = midiChannel;
        this.voice = voiceStatus;
        this.noteNumber = i;
        this.velocity = i2;
        this.on = true;
    }

    public MidiChannel getChannel() {
        return this.channel;
    }

    public VoiceStatus getVoice() {
        return this.voice;
    }

    public int getNoteNumber() {
        return this.noteNumber;
    }

    public int getVelocity() {
        return this.velocity;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setPitch(float f) {
        this.pitch = f;
    }

    public float getPitch() {
        return this.pitch;
    }

    public void setSampleRate(float f) {
        this.samplerate = f;
    }

    public float getSampleRate() {
        return this.samplerate;
    }

    public String getName() {
        return getClass().getName();
    }

    public Patch getPatch() {
        return new Patch(0, 0);
    }

    public ModelOscillatorStream open(float f) {
        try {
            ModelAbstractOscillator modelAbstractOscillator = (ModelAbstractOscillator) getClass().newInstance();
            modelAbstractOscillator.setSampleRate(f);
            modelAbstractOscillator.init();
            return modelAbstractOscillator;
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public ModelPerformer getPerformer() {
        ModelPerformer modelPerformer = new ModelPerformer();
        modelPerformer.getOscillators().add(this);
        return modelPerformer;
    }

    public ModelInstrument getInstrument() {
        SimpleInstrument simpleInstrument = new SimpleInstrument();
        simpleInstrument.setName(getName());
        simpleInstrument.add(getPerformer());
        simpleInstrument.setPatch(getPatch());
        return simpleInstrument;
    }

    public Soundbank getSoundBank() {
        SimpleSoundbank simpleSoundbank = new SimpleSoundbank();
        simpleSoundbank.addInstrument(getInstrument());
        return simpleSoundbank;
    }

    public String getDescription() {
        return getName();
    }

    public Instrument getInstrument(Patch patch) {
        ModelInstrument instrument = getInstrument();
        ModelPatch patch2 = instrument.getPatch();
        if (patch2.getBank() != patch.getBank() || patch2.getProgram() != patch.getProgram()) {
            return null;
        }
        if (!(patch2 instanceof ModelPatch) || !(patch instanceof ModelPatch) || patch2.isPercussion() == ((ModelPatch) patch).isPercussion()) {
            return instrument;
        }
        return null;
    }

    public Instrument[] getInstruments() {
        return new Instrument[]{getInstrument()};
    }

    public SoundbankResource[] getResources() {
        return new SoundbankResource[0];
    }
}
