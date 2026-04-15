package cn.sherlock.com.sun.media.sound;

public interface ModelDirector {
    void close();

    void noteOff(int i, int i2);

    void noteOn(int i, int i2);
}
