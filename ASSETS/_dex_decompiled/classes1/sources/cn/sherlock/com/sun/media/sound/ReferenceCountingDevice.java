package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.MidiUnavailableException;
import jp.kshoji.javax.sound.midi.Receiver;
import jp.kshoji.javax.sound.midi.Transmitter;

public interface ReferenceCountingDevice {
    Receiver getReceiverReferenceCounting() throws MidiUnavailableException;

    Transmitter getTransmitterReferenceCounting() throws MidiUnavailableException;
}
