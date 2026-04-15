package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.Patch;

public class ModelMappedInstrument extends ModelInstrument {
    private ModelInstrument ins;

    public ModelMappedInstrument(ModelInstrument modelInstrument, Patch patch) {
        super(modelInstrument.getSoundbank(), patch, modelInstrument.getName(), modelInstrument.getDataClass());
        this.ins = modelInstrument;
    }

    public Object getData() {
        return this.ins.getData();
    }

    public ModelPerformer[] getPerformers() {
        return this.ins.getPerformers();
    }

    public ModelDirector getDirector(ModelPerformer[] modelPerformerArr, MidiChannel midiChannel, ModelDirectedPlayer modelDirectedPlayer) {
        return this.ins.getDirector(modelPerformerArr, midiChannel, modelDirectedPlayer);
    }

    public ModelChannelMixer getChannelMixer(MidiChannel midiChannel, AudioFormat audioFormat) {
        return this.ins.getChannelMixer(midiChannel, audioFormat);
    }
}
