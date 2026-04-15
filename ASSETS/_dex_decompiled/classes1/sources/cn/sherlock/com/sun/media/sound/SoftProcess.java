package cn.sherlock.com.sun.media.sound;

public interface SoftProcess extends SoftControl {
    double[] get(int i, String str);

    void init(SoftSynthesizer softSynthesizer);

    void processControlLogic();

    void reset();
}
