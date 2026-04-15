package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.MidiChannel;

public interface ModelChannelMixer extends MidiChannel {
    boolean process(float[][] fArr, int i, int i2);

    void stop();
}
