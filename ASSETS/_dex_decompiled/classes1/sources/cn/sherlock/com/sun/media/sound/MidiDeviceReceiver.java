package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.MidiDevice;
import jp.kshoji.javax.sound.midi.Receiver;

public interface MidiDeviceReceiver extends Receiver {
    MidiDevice getMidiDevice();
}
