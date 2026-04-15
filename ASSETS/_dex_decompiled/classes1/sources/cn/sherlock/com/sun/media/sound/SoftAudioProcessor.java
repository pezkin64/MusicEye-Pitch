package cn.sherlock.com.sun.media.sound;

public interface SoftAudioProcessor {
    void globalParameterControlChange(int[] iArr, long j, long j2);

    void init(float f, float f2);

    void processAudio();

    void processControlLogic();

    void setInput(int i, SoftAudioBuffer softAudioBuffer);

    void setMixMode(boolean z);

    void setOutput(int i, SoftAudioBuffer softAudioBuffer);
}
