package jp.kshoji.javax.sound.midi.spi;

import jp.kshoji.javax.sound.midi.MidiDevice;

public abstract class MidiDeviceProvider {
    public abstract MidiDevice getDevice(MidiDevice.Info info) throws IllegalArgumentException;

    public abstract MidiDevice.Info[] getDeviceInfo();

    public boolean isDeviceSupported(MidiDevice.Info info) {
        for (MidiDevice.Info equals : getDeviceInfo()) {
            if (info.equals(equals)) {
                return true;
            }
        }
        return false;
    }
}
