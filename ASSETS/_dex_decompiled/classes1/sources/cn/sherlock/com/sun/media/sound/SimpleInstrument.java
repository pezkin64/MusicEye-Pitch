package cn.sherlock.com.sun.media.sound;

import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;

public class SimpleInstrument extends ModelInstrument {
    protected int bank = 0;
    protected String name = "";
    protected List<SimpleInstrumentPart> parts = new ArrayList();
    protected boolean percussion = false;
    protected int preset = 0;

    public Object getData() {
        return null;
    }

    private static class SimpleInstrumentPart {
        int exclusiveClass;
        int keyFrom;
        int keyTo;
        ModelPerformer[] performers;
        int velFrom;
        int velTo;

        private SimpleInstrumentPart() {
        }
    }

    public SimpleInstrument() {
        super((Soundbank) null, (Patch) null, (String) null, (Class<?>) null);
    }

    public void clear() {
        this.parts.clear();
    }

    public void add(ModelPerformer[] modelPerformerArr, int i, int i2, int i3, int i4, int i5) {
        SimpleInstrumentPart simpleInstrumentPart = new SimpleInstrumentPart();
        simpleInstrumentPart.performers = modelPerformerArr;
        simpleInstrumentPart.keyFrom = i;
        simpleInstrumentPart.keyTo = i2;
        simpleInstrumentPart.velFrom = i3;
        simpleInstrumentPart.velTo = i4;
        simpleInstrumentPart.exclusiveClass = i5;
        this.parts.add(simpleInstrumentPart);
    }

    public void add(ModelPerformer[] modelPerformerArr, int i, int i2, int i3, int i4) {
        add(modelPerformerArr, i, i2, i3, i4, -1);
    }

    public void add(ModelPerformer[] modelPerformerArr, int i, int i2) {
        add(modelPerformerArr, i, i2, 0, 127, -1);
    }

    public void add(ModelPerformer[] modelPerformerArr) {
        add(modelPerformerArr, 0, 127, 0, 127, -1);
    }

    public void add(ModelPerformer modelPerformer, int i, int i2, int i3, int i4, int i5) {
        add(new ModelPerformer[]{modelPerformer}, i, i2, i3, i4, i5);
    }

    public void add(ModelPerformer modelPerformer, int i, int i2, int i3, int i4) {
        add(new ModelPerformer[]{modelPerformer}, i, i2, i3, i4);
    }

    public void add(ModelPerformer modelPerformer, int i, int i2) {
        add(new ModelPerformer[]{modelPerformer}, i, i2);
    }

    public void add(ModelPerformer modelPerformer) {
        add(new ModelPerformer[]{modelPerformer});
    }

    public void add(ModelInstrument modelInstrument, int i, int i2, int i3, int i4, int i5) {
        add(modelInstrument.getPerformers(), i, i2, i3, i4, i5);
    }

    public void add(ModelInstrument modelInstrument, int i, int i2, int i3, int i4) {
        add(modelInstrument.getPerformers(), i, i2, i3, i4);
    }

    public void add(ModelInstrument modelInstrument, int i, int i2) {
        add(modelInstrument.getPerformers(), i, i2);
    }

    public void add(ModelInstrument modelInstrument) {
        add(modelInstrument.getPerformers());
    }

    public ModelPerformer[] getPerformers() {
        int i = 0;
        for (SimpleInstrumentPart next : this.parts) {
            if (next.performers != null) {
                i += next.performers.length;
            }
        }
        ModelPerformer[] modelPerformerArr = new ModelPerformer[i];
        int i2 = 0;
        for (SimpleInstrumentPart next2 : this.parts) {
            if (next2.performers != null) {
                ModelPerformer[] modelPerformerArr2 = next2.performers;
                int length = modelPerformerArr2.length;
                int i3 = 0;
                while (i3 < length) {
                    ModelPerformer modelPerformer = modelPerformerArr2[i3];
                    ModelPerformer modelPerformer2 = new ModelPerformer();
                    modelPerformer2.setName(getName());
                    int i4 = i2 + 1;
                    modelPerformerArr[i2] = modelPerformer2;
                    modelPerformer2.setDefaultConnectionsEnabled(modelPerformer.isDefaultConnectionsEnabled());
                    modelPerformer2.setKeyFrom(modelPerformer.getKeyFrom());
                    modelPerformer2.setKeyTo(modelPerformer.getKeyTo());
                    modelPerformer2.setVelFrom(modelPerformer.getVelFrom());
                    modelPerformer2.setVelTo(modelPerformer.getVelTo());
                    modelPerformer2.setExclusiveClass(modelPerformer.getExclusiveClass());
                    modelPerformer2.setSelfNonExclusive(modelPerformer.isSelfNonExclusive());
                    modelPerformer2.setReleaseTriggered(modelPerformer.isReleaseTriggered());
                    if (next2.exclusiveClass != -1) {
                        modelPerformer2.setExclusiveClass(next2.exclusiveClass);
                    }
                    if (next2.keyFrom > modelPerformer2.getKeyFrom()) {
                        modelPerformer2.setKeyFrom(next2.keyFrom);
                    }
                    if (next2.keyTo < modelPerformer2.getKeyTo()) {
                        modelPerformer2.setKeyTo(next2.keyTo);
                    }
                    if (next2.velFrom > modelPerformer2.getVelFrom()) {
                        modelPerformer2.setVelFrom(next2.velFrom);
                    }
                    if (next2.velTo < modelPerformer2.getVelTo()) {
                        modelPerformer2.setVelTo(next2.velTo);
                    }
                    modelPerformer2.getOscillators().addAll(modelPerformer.getOscillators());
                    modelPerformer2.getConnectionBlocks().addAll(modelPerformer.getConnectionBlocks());
                    i3++;
                    i2 = i4;
                }
            }
        }
        return modelPerformerArr;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public ModelPatch getPatch() {
        return new ModelPatch(this.bank, this.preset, this.percussion);
    }

    public void setPatch(Patch patch) {
        if (!(patch instanceof ModelPatch) || !((ModelPatch) patch).isPercussion()) {
            this.percussion = false;
            this.bank = patch.getBank();
            this.preset = patch.getProgram();
            return;
        }
        this.percussion = true;
        this.bank = patch.getBank();
        this.preset = patch.getProgram();
    }
}
