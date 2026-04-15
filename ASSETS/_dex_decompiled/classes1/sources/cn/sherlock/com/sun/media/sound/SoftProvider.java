package cn.sherlock.com.sun.media.sound;

import jp.kshoji.javax.sound.midi.MidiDevice;
import jp.kshoji.javax.sound.midi.spi.MidiDeviceProvider;

public class SoftProvider extends MidiDeviceProvider {
    protected static final MidiDevice.Info softinfo;
    private static MidiDevice.Info[] softinfos;

    static {
        MidiDevice.Info info = SoftSynthesizer.info;
        softinfo = info;
        softinfos = new MidiDevice.Info[]{info};
    }

    public MidiDevice.Info[] getDeviceInfo() {
        return softinfos;
    }

    public MidiDevice getDevice(MidiDevice.Info info) {
        if (info == softinfo) {
            return new SoftSynthesizer();
        }
        return null;
    }
}
