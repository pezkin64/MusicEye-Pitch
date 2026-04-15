package jp.kshoji.javax.sound.midi;

public interface Synthesizer extends MidiDevice {
    Instrument[] getAvailableInstruments();

    MidiChannel[] getChannels();

    Soundbank getDefaultSoundbank();

    long getLatency();

    Instrument[] getLoadedInstruments();

    int getMaxPolyphony();

    VoiceStatus[] getVoiceStatus();

    boolean isSoundbankSupported(Soundbank soundbank);

    boolean loadAllInstruments(Soundbank soundbank);

    boolean loadInstrument(Instrument instrument);

    boolean loadInstruments(Soundbank soundbank, Patch[] patchArr);

    boolean remapInstrument(Instrument instrument, Instrument instrument2);

    void unloadAllInstruments(Soundbank soundbank);

    void unloadInstrument(Instrument instrument);

    void unloadInstruments(Soundbank soundbank, Patch[] patchArr);
}
