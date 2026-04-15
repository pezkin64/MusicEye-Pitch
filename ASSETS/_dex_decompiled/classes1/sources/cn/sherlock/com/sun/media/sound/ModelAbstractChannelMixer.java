package cn.sherlock.com.sun.media.sound;

public abstract class ModelAbstractChannelMixer implements ModelChannelMixer {
    public void allNotesOff() {
    }

    public void allSoundOff() {
    }

    public void controlChange(int i, int i2) {
    }

    public int getChannelPressure() {
        return 0;
    }

    public int getController(int i) {
        return 0;
    }

    public boolean getMono() {
        return false;
    }

    public boolean getMute() {
        return false;
    }

    public boolean getOmni() {
        return false;
    }

    public int getPitchBend() {
        return 0;
    }

    public int getPolyPressure(int i) {
        return 0;
    }

    public int getProgram() {
        return 0;
    }

    public boolean getSolo() {
        return false;
    }

    public boolean localControl(boolean z) {
        return false;
    }

    public void noteOff(int i) {
    }

    public void noteOff(int i, int i2) {
    }

    public void noteOn(int i, int i2) {
    }

    public abstract boolean process(float[][] fArr, int i, int i2);

    public void programChange(int i) {
    }

    public void programChange(int i, int i2) {
    }

    public void resetAllControllers() {
    }

    public void setChannelPressure(int i) {
    }

    public void setMono(boolean z) {
    }

    public void setMute(boolean z) {
    }

    public void setOmni(boolean z) {
    }

    public void setPitchBend(int i) {
    }

    public void setPolyPressure(int i, int i2) {
    }

    public void setSolo(boolean z) {
    }

    public abstract void stop();
}
