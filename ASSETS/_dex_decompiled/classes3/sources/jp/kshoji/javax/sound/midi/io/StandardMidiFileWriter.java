package jp.kshoji.javax.sound.midi.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import jp.kshoji.javax.sound.midi.MetaMessage;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.MidiFileFormat;
import jp.kshoji.javax.sound.midi.Sequence;
import jp.kshoji.javax.sound.midi.Track;
import jp.kshoji.javax.sound.midi.spi.MidiFileWriter;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class StandardMidiFileWriter extends MidiFileWriter {

    static class MidiDataOutputStream extends DataOutputStream {
        private static int getValueToWrite(int i) {
            int i2 = i & WorkQueueKt.MASK;
            while (true) {
                i >>= 7;
                if (i == 0) {
                    return i2;
                }
                i2 = (i2 << 8) | (i & WorkQueueKt.MASK) | 128;
            }
        }

        public MidiDataOutputStream(OutputStream outputStream) {
            super(outputStream);
        }

        public static int variableLengthIntLength(int i) {
            int valueToWrite = getValueToWrite(i);
            int i2 = 0;
            while (true) {
                i2++;
                if ((valueToWrite & 128) == 0) {
                    return i2;
                }
                valueToWrite >>>= 8;
            }
        }

        public synchronized void writeVariableLengthInt(int i) throws IOException {
            int valueToWrite = getValueToWrite(i);
            while (true) {
                writeByte(valueToWrite & 255);
                if ((valueToWrite & 128) != 0) {
                    valueToWrite >>>= 8;
                }
            }
        }
    }

    public int[] getMidiFileTypes() {
        return new int[]{0, 1};
    }

    public int[] getMidiFileTypes(Sequence sequence) {
        if (sequence.getTracks().length > 1) {
            return new int[]{1};
        }
        return new int[]{0, 1};
    }

    public int write(Sequence sequence, int i, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        int write = write(sequence, i, (OutputStream) fileOutputStream);
        fileOutputStream.close();
        return write;
    }

    public int write(Sequence sequence, int i, OutputStream outputStream) throws IOException {
        MidiDataOutputStream midiDataOutputStream = new MidiDataOutputStream(outputStream);
        Track[] tracks = sequence.getTracks();
        midiDataOutputStream.writeInt(MidiFileFormat.HEADER_MThd);
        midiDataOutputStream.writeInt(6);
        midiDataOutputStream.writeShort(i);
        midiDataOutputStream.writeShort(tracks.length);
        float divisionType = sequence.getDivisionType();
        int resolution = sequence.getResolution();
        midiDataOutputStream.writeShort(divisionType == Sequence.PPQ ? resolution & 32767 : divisionType == 24.0f ? (resolution & 255) - 6144 : divisionType == 25.0f ? (resolution & 255) - 6400 : divisionType == 29.97f ? (resolution & 255) - 7424 : divisionType == 30.0f ? (resolution & 255) - 7680 : 0);
        int i2 = 0;
        for (Track writeTrack : tracks) {
            i2 += writeTrack(writeTrack, midiDataOutputStream);
        }
        midiDataOutputStream.close();
        return i2 + 14;
    }

    private static int writeTrack(Track track, MidiDataOutputStream midiDataOutputStream) throws IOException {
        boolean z;
        int size = track.size();
        midiDataOutputStream.writeInt(MidiFileFormat.HEADER_MTrk);
        long j = 0;
        MidiEvent midiEvent = null;
        int i = 0;
        int i2 = 0;
        long j2 = 0;
        while (i < size) {
            midiEvent = track.get(i);
            long tick = midiEvent.getTick();
            i2 = i2 + MidiDataOutputStream.variableLengthIntLength((int) (tick - j2)) + midiEvent.getMessage().getLength();
            i++;
            j2 = tick;
        }
        if (midiEvent == null || !(midiEvent.getMessage() instanceof MetaMessage) || ((MetaMessage) midiEvent.getMessage()).getType() != 47) {
            i2 += 4;
            z = false;
        } else {
            z = true;
        }
        midiDataOutputStream.writeInt(i2);
        int i3 = 0;
        while (i3 < size) {
            MidiEvent midiEvent2 = track.get(i3);
            long tick2 = midiEvent2.getTick();
            midiDataOutputStream.writeVariableLengthInt((int) (tick2 - j));
            midiDataOutputStream.write(midiEvent2.getMessage().getMessage(), 0, midiEvent2.getMessage().getLength());
            i3++;
            j = tick2;
        }
        if (!z) {
            midiDataOutputStream.writeVariableLengthInt(0);
            midiDataOutputStream.writeByte(255);
            midiDataOutputStream.writeByte(47);
            midiDataOutputStream.writeVariableLengthInt(0);
        }
        return i2 + 4;
    }
}
