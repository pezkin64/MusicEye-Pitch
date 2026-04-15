package jp.kshoji.javax.sound.midi;

public interface MidiChannel {
    void allNotesOff();

    void allSoundOff();

    void controlChange(int i, int i2);

    int getChannelPressure();

    int getController(int i);

    boolean getMono();

    boolean getMute();

    boolean getOmni();

    int getPitchBend();

    int getPolyPressure(int i);

    int getProgram();

    boolean getSolo();

    boolean localControl(boolean z);

    void noteOff(int i);

    void noteOff(int i, int i2);

    void noteOn(int i, int i2);

    void programChange(int i);

    void programChange(int i, int i2);

    void resetAllControllers();

    void setChannelPressure(int i);

    void setMono(boolean z);

    void setMute(boolean z);

    void setOmni(boolean z);

    void setPitchBend(int i);

    void setPolyPressure(int i, int i2);

    void setSolo(boolean z);
}
