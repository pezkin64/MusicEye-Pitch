package com.xemsoft.sheetmusicscanner2.persist;

import jp.kshoji.javax.sound.midi.Sequence;

public class VoiceSettings {
    public boolean isMuted;
    public float volume;

    public VoiceSettings() {
        this.isMuted = false;
        this.volume = 1.0f;
    }

    public VoiceSettings(boolean z, float f) {
        this.isMuted = z;
        this.volume = f;
    }

    public boolean isAudible() {
        return !this.isMuted && this.volume > Sequence.PPQ;
    }
}
