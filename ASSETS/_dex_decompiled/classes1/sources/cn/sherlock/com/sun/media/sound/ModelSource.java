package cn.sherlock.com.sun.media.sound;

public class ModelSource {
    public static final ModelIdentifier SOURCE_EG1 = new ModelIdentifier("eg", (String) null, 0);
    public static final ModelIdentifier SOURCE_EG2 = new ModelIdentifier("eg", (String) null, 1);
    public static final ModelIdentifier SOURCE_LFO1 = new ModelIdentifier("lfo", (String) null, 0);
    public static final ModelIdentifier SOURCE_LFO2 = new ModelIdentifier("lfo", (String) null, 1);
    public static final ModelIdentifier SOURCE_MIDI_CC_0 = new ModelIdentifier("midi_cc", "0", 0);
    public static final ModelIdentifier SOURCE_MIDI_CHANNEL_PRESSURE = new ModelIdentifier("midi", "channel_pressure", 0);
    public static final ModelIdentifier SOURCE_MIDI_PITCH = new ModelIdentifier("midi", "pitch", 0);
    public static final ModelIdentifier SOURCE_MIDI_POLY_PRESSURE = new ModelIdentifier("midi", "poly_pressure", 0);
    public static final ModelIdentifier SOURCE_MIDI_RPN_0 = new ModelIdentifier("midi_rpn", "0", 0);
    public static final ModelIdentifier SOURCE_NONE = null;
    public static final ModelIdentifier SOURCE_NOTEON_KEYNUMBER = new ModelIdentifier("noteon", "keynumber");
    public static final ModelIdentifier SOURCE_NOTEON_VELOCITY = new ModelIdentifier("noteon", "velocity");
    private ModelIdentifier source;
    private ModelTransform transform;

    public ModelSource() {
        this.source = SOURCE_NONE;
        this.transform = new ModelStandardTransform();
    }

    public ModelSource(ModelIdentifier modelIdentifier) {
        this.source = modelIdentifier;
        this.transform = new ModelStandardTransform();
    }

    public ModelSource(ModelIdentifier modelIdentifier, boolean z) {
        this.source = modelIdentifier;
        this.transform = new ModelStandardTransform(z);
    }

    public ModelSource(ModelIdentifier modelIdentifier, boolean z, boolean z2) {
        this.source = modelIdentifier;
        this.transform = new ModelStandardTransform(z, z2);
    }

    public ModelSource(ModelIdentifier modelIdentifier, boolean z, boolean z2, int i) {
        this.source = modelIdentifier;
        this.transform = new ModelStandardTransform(z, z2, i);
    }

    public ModelSource(ModelIdentifier modelIdentifier, ModelTransform modelTransform) {
        this.source = modelIdentifier;
        this.transform = modelTransform;
    }

    public ModelIdentifier getIdentifier() {
        return this.source;
    }

    public void setIdentifier(ModelIdentifier modelIdentifier) {
        this.source = modelIdentifier;
    }

    public ModelTransform getTransform() {
        return this.transform;
    }

    public void setTransform(ModelTransform modelTransform) {
        this.transform = modelTransform;
    }
}
