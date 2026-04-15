package cn.sherlock.com.sun.media.sound;

import java.io.IOException;

public interface SoftResamplerStreamer extends ModelOscillatorStream {
    void open(ModelWavetable modelWavetable, float f) throws IOException;
}
