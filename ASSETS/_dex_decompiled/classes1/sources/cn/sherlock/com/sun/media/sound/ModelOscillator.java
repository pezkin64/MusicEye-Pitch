package cn.sherlock.com.sun.media.sound;

public interface ModelOscillator {
    float getAttenuation();

    int getChannels();

    ModelOscillatorStream open(float f);
}
