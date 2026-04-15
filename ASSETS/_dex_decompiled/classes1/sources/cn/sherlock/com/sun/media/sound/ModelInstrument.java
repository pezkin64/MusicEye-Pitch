package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;

public abstract class ModelInstrument extends Instrument {
    public ModelChannelMixer getChannelMixer(MidiChannel midiChannel, AudioFormat audioFormat) {
        return null;
    }

    protected ModelInstrument(Soundbank soundbank, Patch patch, String str, Class<?> cls) {
        super(soundbank, patch, str, cls);
    }

    public ModelDirector getDirector(ModelPerformer[] modelPerformerArr, MidiChannel midiChannel, ModelDirectedPlayer modelDirectedPlayer) {
        return new ModelStandardIndexedDirector(modelPerformerArr, modelDirectedPlayer);
    }

    public ModelPerformer[] getPerformers() {
        return new ModelPerformer[0];
    }

    public Patch getPatchAlias() {
        Patch patch = getPatch();
        int program = patch.getProgram();
        if (patch.getBank() != 0) {
            return patch;
        }
        if (getPatch() instanceof ModelPatch ? getPatch().isPercussion() : false) {
            return new Patch(15360, program);
        }
        return new Patch(15488, program);
    }

    public String[] getKeys() {
        String[] strArr = new String[128];
        for (ModelPerformer modelPerformer : getPerformers()) {
            for (int keyFrom = modelPerformer.getKeyFrom(); keyFrom <= modelPerformer.getKeyTo(); keyFrom++) {
                if (keyFrom >= 0 && keyFrom < 128 && strArr[keyFrom] == null) {
                    String name = modelPerformer.getName();
                    if (name == null) {
                        name = "untitled";
                    }
                    strArr[keyFrom] = name;
                }
            }
        }
        return strArr;
    }

    public boolean[] getChannels() {
        if (getPatch() instanceof ModelPatch ? getPatch().isPercussion() : false) {
            boolean[] zArr = new boolean[16];
            for (int i = 0; i < 16; i++) {
                zArr[i] = false;
            }
            zArr[9] = true;
            return zArr;
        }
        int bank = getPatch().getBank() >> 7;
        if (bank == 120 || bank == 121) {
            boolean[] zArr2 = new boolean[16];
            for (int i2 = 0; i2 < 16; i2++) {
                zArr2[i2] = true;
            }
            return zArr2;
        }
        boolean[] zArr3 = new boolean[16];
        for (int i3 = 0; i3 < 16; i3++) {
            zArr3[i3] = true;
        }
        zArr3[9] = false;
        return zArr3;
    }
}
