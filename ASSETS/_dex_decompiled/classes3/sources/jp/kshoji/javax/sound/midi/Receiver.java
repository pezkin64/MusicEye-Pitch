package jp.kshoji.javax.sound.midi;

public interface Receiver {
    void close();

    void send(MidiMessage midiMessage, long j);
}
