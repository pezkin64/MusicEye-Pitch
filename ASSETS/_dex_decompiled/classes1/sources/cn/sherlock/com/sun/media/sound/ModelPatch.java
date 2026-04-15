package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.Patch;

public class ModelPatch extends Patch {
    private boolean percussion;

    public ModelPatch(int i, int i2) {
        super(i, i2);
        this.percussion = false;
    }

    public ModelPatch(int i, int i2, boolean z) {
        super(i, i2);
        this.percussion = z;
    }

    public boolean isPercussion() {
        return this.percussion;
    }
}
