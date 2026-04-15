package cn.sherlock.com.sun.media.sound;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.spi.SoundbankReader;

public class SF2SoundbankReader extends SoundbankReader {
    public Soundbank getSoundbank(URL url) throws InvalidMidiDataException, IOException {
        try {
            return new SF2Soundbank(url);
        } catch (RIFFInvalidFormatException | IOException unused) {
            return null;
        }
    }

    public Soundbank getSoundbank(InputStream inputStream) throws InvalidMidiDataException, IOException {
        try {
            inputStream.mark(512);
            return new SF2Soundbank(inputStream);
        } catch (RIFFInvalidFormatException unused) {
            inputStream.reset();
            return null;
        }
    }

    public Soundbank getSoundbank(File file) throws InvalidMidiDataException, IOException {
        try {
            return new SF2Soundbank(file);
        } catch (RIFFInvalidFormatException unused) {
            return null;
        }
    }
}
