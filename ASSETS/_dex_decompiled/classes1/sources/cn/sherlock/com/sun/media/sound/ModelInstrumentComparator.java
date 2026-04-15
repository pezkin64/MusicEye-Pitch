package cn.sherlock.com.sun.media.sound;

import java.util.Comparator;
import jp.kshoji.javax.sound.midi.Instrument;

public class ModelInstrumentComparator implements Comparator<Instrument> {
    public int compare(Instrument instrument, Instrument instrument2) {
        ModelPatch patch = instrument.getPatch();
        ModelPatch patch2 = instrument2.getPatch();
        int bank = (patch.getBank() * 128) + patch.getProgram();
        int bank2 = (patch2.getBank() * 128) + patch2.getProgram();
        int i = 2097152;
        if (patch instanceof ModelPatch) {
            bank += patch.isPercussion() ? 2097152 : 0;
        }
        if (patch2 instanceof ModelPatch) {
            if (!patch2.isPercussion()) {
                i = 0;
            }
            bank2 += i;
        }
        return bank - bank2;
    }
}
