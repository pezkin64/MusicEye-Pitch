package cn.sherlock.com.sun.media.sound;

import java.io.IOException;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.VoiceStatus;

public interface ModelOscillatorStream {
    void close() throws IOException;

    void noteOff(int i);

    void noteOn(MidiChannel midiChannel, VoiceStatus voiceStatus, int i, int i2);

    int read(float[][] fArr, int i, int i2) throws IOException;

    void setPitch(float f);
}
