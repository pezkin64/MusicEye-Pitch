package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.MidiChannel;

public class SoftChannelProxy implements MidiChannel {
    private MidiChannel channel = null;

    public MidiChannel getChannel() {
        return this.channel;
    }

    public void setChannel(MidiChannel midiChannel) {
        this.channel = midiChannel;
    }

    public void allNotesOff() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.allNotesOff();
        }
    }

    public void allSoundOff() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.allSoundOff();
        }
    }

    public void controlChange(int i, int i2) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.controlChange(i, i2);
        }
    }

    public int getChannelPressure() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return 0;
        }
        return midiChannel.getChannelPressure();
    }

    public int getController(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return 0;
        }
        return midiChannel.getController(i);
    }

    public boolean getMono() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return false;
        }
        return midiChannel.getMono();
    }

    public boolean getMute() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return false;
        }
        return midiChannel.getMute();
    }

    public boolean getOmni() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return false;
        }
        return midiChannel.getOmni();
    }

    public int getPitchBend() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return 8192;
        }
        return midiChannel.getPitchBend();
    }

    public int getPolyPressure(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return 0;
        }
        return midiChannel.getPolyPressure(i);
    }

    public int getProgram() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return 0;
        }
        return midiChannel.getProgram();
    }

    public boolean getSolo() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return false;
        }
        return midiChannel.getSolo();
    }

    public boolean localControl(boolean z) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel == null) {
            return false;
        }
        return midiChannel.localControl(z);
    }

    public void noteOff(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.noteOff(i);
        }
    }

    public void noteOff(int i, int i2) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.noteOff(i, i2);
        }
    }

    public void noteOn(int i, int i2) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.noteOn(i, i2);
        }
    }

    public void programChange(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.programChange(i);
        }
    }

    public void programChange(int i, int i2) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.programChange(i, i2);
        }
    }

    public void resetAllControllers() {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.resetAllControllers();
        }
    }

    public void setChannelPressure(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setChannelPressure(i);
        }
    }

    public void setMono(boolean z) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setMono(z);
        }
    }

    public void setMute(boolean z) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setMute(z);
        }
    }

    public void setOmni(boolean z) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setOmni(z);
        }
    }

    public void setPitchBend(int i) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setPitchBend(i);
        }
    }

    public void setPolyPressure(int i, int i2) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setPolyPressure(i, i2);
        }
    }

    public void setSolo(boolean z) {
        MidiChannel midiChannel = this.channel;
        if (midiChannel != null) {
            midiChannel.setSolo(z);
        }
    }
}
