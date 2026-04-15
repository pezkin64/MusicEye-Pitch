package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.ShortMessage;

public class SoftShortMessage extends ShortMessage {
    int channel = 0;

    public SoftShortMessage() {
    }

    public SoftShortMessage(int i, int i2, int i3, int i4) throws InvalidMidiDataException {
        setMessage(i, i2, i3, i4);
    }

    public int getChannel() {
        return this.channel;
    }

    public void setMessage(int i, int i2, int i3, int i4) throws InvalidMidiDataException {
        this.channel = i2;
        SoftShortMessage.super.setMessage(i, i2 & 15, i3, i4);
    }

    public Object clone() {
        SoftShortMessage softShortMessage = new SoftShortMessage();
        try {
            softShortMessage.setMessage(getCommand(), getChannel(), getData1(), getData2());
            return softShortMessage;
        } catch (InvalidMidiDataException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
