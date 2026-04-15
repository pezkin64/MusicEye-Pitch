package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.MidiChannel;

public class SoftInstrument extends Instrument {
    private Object data;
    private ModelInstrument ins;
    private ModelPerformer[] modelperformers;
    private SoftPerformer[] performers;

    public SoftInstrument(ModelInstrument modelInstrument) {
        super(modelInstrument.getSoundbank(), modelInstrument.getPatch(), modelInstrument.getName(), modelInstrument.getDataClass());
        this.data = modelInstrument.getData();
        this.ins = modelInstrument;
        initPerformers(modelInstrument.getPerformers());
    }

    public SoftInstrument(ModelInstrument modelInstrument, ModelPerformer[] modelPerformerArr) {
        super(modelInstrument.getSoundbank(), modelInstrument.getPatch(), modelInstrument.getName(), modelInstrument.getDataClass());
        this.data = modelInstrument.getData();
        this.ins = modelInstrument;
        initPerformers(modelPerformerArr);
    }

    private void initPerformers(ModelPerformer[] modelPerformerArr) {
        this.modelperformers = modelPerformerArr;
        this.performers = new SoftPerformer[modelPerformerArr.length];
        for (int i = 0; i < modelPerformerArr.length; i++) {
            this.performers[i] = new SoftPerformer(modelPerformerArr[i]);
        }
    }

    public ModelDirector getDirector(MidiChannel midiChannel, ModelDirectedPlayer modelDirectedPlayer) {
        return this.ins.getDirector(this.modelperformers, midiChannel, modelDirectedPlayer);
    }

    public ModelInstrument getSourceInstrument() {
        return this.ins;
    }

    public Object getData() {
        return this.data;
    }

    public SoftPerformer[] getPerformers() {
        return this.performers;
    }
}
