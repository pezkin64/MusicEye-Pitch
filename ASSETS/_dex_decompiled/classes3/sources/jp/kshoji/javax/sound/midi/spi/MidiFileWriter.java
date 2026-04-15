package jp.kshoji.javax.sound.midi.spi;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import jp.kshoji.javax.sound.midi.Sequence;

public abstract class MidiFileWriter {
    public abstract int[] getMidiFileTypes();

    public abstract int[] getMidiFileTypes(Sequence sequence);

    public abstract int write(Sequence sequence, int i, File file) throws IOException;

    public abstract int write(Sequence sequence, int i, OutputStream outputStream) throws IOException;

    public boolean isFileTypeSupported(int i) {
        for (int i2 : getMidiFileTypes()) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public boolean isFileTypeSupported(int i, Sequence sequence) {
        for (int i2 : getMidiFileTypes(sequence)) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }
}
