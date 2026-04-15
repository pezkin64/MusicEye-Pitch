package jp.kshoji.javax.sound.midi;

public class MidiEvent {
    private MidiMessage message;
    private long tick;

    public MidiEvent(MidiMessage midiMessage, long j) {
        this.message = midiMessage;
        this.tick = j;
    }

    public MidiMessage getMessage() {
        return this.message;
    }

    public long getTick() {
        return this.tick;
    }

    public void setTick(long j) {
        this.tick = j;
    }
}
