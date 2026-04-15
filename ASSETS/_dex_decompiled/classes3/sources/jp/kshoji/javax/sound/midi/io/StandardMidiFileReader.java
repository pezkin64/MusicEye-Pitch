package jp.kshoji.javax.sound.midi.io;

import android.content.res.AssetManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.MetaMessage;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.MidiFileFormat;
import jp.kshoji.javax.sound.midi.Sequence;
import jp.kshoji.javax.sound.midi.ShortMessage;
import jp.kshoji.javax.sound.midi.SysexMessage;
import jp.kshoji.javax.sound.midi.Track;
import jp.kshoji.javax.sound.midi.spi.MidiFileReader;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.ShortCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class StandardMidiFileReader extends MidiFileReader {

    class ExtendedMidiFileFormat extends MidiFileFormat {
        private int numberOfTracks;

        public int getNumberTracks() {
            return this.numberOfTracks;
        }

        public ExtendedMidiFileFormat(int i, float f, int i2, int i3, long j, int i4) {
            super(i, f, i2, i3, j);
            this.numberOfTracks = i4;
        }
    }

    class MidiDataInputStream extends DataInputStream {
        public MidiDataInputStream(InputStream inputStream) {
            super(inputStream);
        }

        public int readVariableLengthInt() throws IOException {
            byte readByte;
            int readByte2 = readByte();
            if ((readByte2 & 128) != 0) {
                readByte2 &= WorkQueueKt.MASK;
                do {
                    readByte = readByte();
                    readByte2 = (readByte2 << 7) + (readByte & ByteCompanionObject.MAX_VALUE);
                } while ((readByte & 128) != 0);
            }
            return readByte2;
        }
    }

    public MidiFileFormat getMidiFileFormat(InputStream inputStream) throws InvalidMidiDataException, IOException {
        DataInputStream dataInputStream;
        float f;
        short s;
        DataInputStream dataInputStream2;
        if (inputStream instanceof DataInputStream) {
            dataInputStream = (DataInputStream) inputStream;
        } else {
            if (inputStream instanceof AssetManager.AssetInputStream) {
                dataInputStream2 = new MidiDataInputStream(convertToByteArrayInputStream(inputStream));
            } else {
                dataInputStream2 = new DataInputStream(inputStream);
            }
            dataInputStream = dataInputStream2;
        }
        try {
            if (dataInputStream.readInt() == 1297377380) {
                int readInt = dataInputStream.readInt();
                if (readInt >= 6) {
                    short readShort = dataInputStream.readShort();
                    if (readShort < 0 || readShort > 2) {
                        throw new InvalidMidiDataException("Invalid header");
                    }
                    short readShort2 = dataInputStream.readShort();
                    if (readShort2 > 0) {
                        short readShort3 = dataInputStream.readShort();
                        if ((32768 & readShort3) != 0) {
                            int i = -((readShort3 >>> 8) & 255);
                            if (i == 24) {
                                f = 24.0f;
                            } else if (i == 25) {
                                f = 25.0f;
                            } else if (i == 29) {
                                f = 29.97f;
                            } else if (i == 30) {
                                f = 30.0f;
                            } else {
                                throw new InvalidMidiDataException("Invalid sequence information");
                            }
                            s = i & 255;
                        } else {
                            s = readShort3 & ShortCompanionObject.MAX_VALUE;
                            f = Sequence.PPQ;
                        }
                        float f2 = f;
                        dataInputStream.skip((long) (readInt - 6));
                        return new ExtendedMidiFileFormat(readShort, f2, s, -1, -1, readShort2);
                    }
                    throw new InvalidMidiDataException("Invalid tracks");
                }
                throw new InvalidMidiDataException("Invalid header");
            }
            throw new InvalidMidiDataException("Invalid header");
        } finally {
            dataInputStream.close();
        }
    }

    public MidiFileFormat getMidiFileFormat(URL url) throws InvalidMidiDataException, IOException {
        InputStream openStream = url.openStream();
        try {
            return getMidiFileFormat(openStream);
        } finally {
            openStream.close();
        }
    }

    public MidiFileFormat getMidiFileFormat(File file) throws InvalidMidiDataException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return getMidiFileFormat((InputStream) fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public Sequence getSequence(InputStream inputStream) throws InvalidMidiDataException, IOException {
        int i;
        int readUnsignedByte;
        ShortMessage shortMessage;
        ShortMessage processSystemMessage;
        MidiDataInputStream midiDataInputStream = new MidiDataInputStream(convertToByteArrayInputStream(inputStream));
        try {
            ExtendedMidiFileFormat extendedMidiFileFormat = (ExtendedMidiFileFormat) getMidiFileFormat((InputStream) midiDataInputStream);
            Sequence sequence = new Sequence(extendedMidiFileFormat.getDivisionType(), extendedMidiFileFormat.getResolution());
            int numberTracks = extendedMidiFileFormat.getNumberTracks();
            loop0:
            while (true) {
                int i2 = numberTracks - 1;
                if (numberTracks <= 0) {
                    return sequence;
                }
                Track createTrack = sequence.createTrack();
                if (midiDataInputStream.readInt() == 1297379947) {
                    midiDataInputStream.readInt();
                    boolean z = false;
                    int i3 = 0;
                    i = -1;
                    while (!z) {
                        i3 += midiDataInputStream.readVariableLengthInt();
                        readUnsignedByte = midiDataInputStream.readUnsignedByte();
                        if (readUnsignedByte >= 128) {
                            if (readUnsignedByte < 240) {
                                processSystemMessage = processRunningMessage(readUnsignedByte, midiDataInputStream.readUnsignedByte(), midiDataInputStream);
                            } else {
                                if (readUnsignedByte != 240) {
                                    if (readUnsignedByte != 247) {
                                        if (readUnsignedByte == 255) {
                                            int readUnsignedByte2 = midiDataInputStream.readUnsignedByte();
                                            int readVariableLengthInt = midiDataInputStream.readVariableLengthInt();
                                            byte[] bArr = new byte[readVariableLengthInt];
                                            midiDataInputStream.readFully(bArr);
                                            MetaMessage metaMessage = new MetaMessage(readUnsignedByte2, bArr, readVariableLengthInt);
                                            if (readUnsignedByte2 == 47) {
                                                z = true;
                                            }
                                            i = -1;
                                            shortMessage = metaMessage;
                                        } else {
                                            processSystemMessage = processSystemMessage(readUnsignedByte, (Integer) null, midiDataInputStream);
                                        }
                                    }
                                }
                                int readVariableLengthInt2 = midiDataInputStream.readVariableLengthInt();
                                byte[] bArr2 = new byte[readVariableLengthInt2];
                                midiDataInputStream.readFully(bArr2);
                                SysexMessage sysexMessage = new SysexMessage();
                                sysexMessage.setMessage(readUnsignedByte, bArr2, readVariableLengthInt2);
                                i = -1;
                                shortMessage = sysexMessage;
                            }
                            int i4 = readUnsignedByte;
                            shortMessage = processSystemMessage;
                            i = i4;
                        } else if (i >= 0 && i < 240) {
                            shortMessage = processRunningMessage(i, readUnsignedByte, midiDataInputStream);
                        } else if (i < 240 || i > 255) {
                        } else {
                            shortMessage = processSystemMessage(i, Integer.valueOf(readUnsignedByte), midiDataInputStream);
                        }
                        createTrack.add(new MidiEvent(shortMessage, (long) i3));
                    }
                    Track.TrackUtils.sortEvents(createTrack);
                    numberTracks = i2;
                } else {
                    throw new InvalidMidiDataException("Invalid track header");
                }
            }
            throw new InvalidMidiDataException(String.format("Invalid data: %02x %02x", new Object[]{Integer.valueOf(i), Integer.valueOf(readUnsignedByte)}));
        } finally {
            midiDataInputStream.close();
        }
    }

    private static ShortMessage processSystemMessage(int i, Integer num, MidiDataInputStream midiDataInputStream) throws InvalidMidiDataException, IOException {
        switch (i) {
            case ShortMessage.SONG_POSITION_POINTER:
                ShortMessage shortMessage = new ShortMessage();
                if (num == null) {
                    shortMessage.setMessage(i, midiDataInputStream.readUnsignedByte(), midiDataInputStream.readUnsignedByte());
                    return shortMessage;
                }
                shortMessage.setMessage(i, num.intValue(), midiDataInputStream.readUnsignedByte());
                return shortMessage;
            case ShortMessage.SONG_SELECT:
            case ShortMessage.BUS_SELECT:
                ShortMessage shortMessage2 = new ShortMessage();
                if (num == null) {
                    shortMessage2.setMessage(i, midiDataInputStream.readUnsignedByte(), 0);
                    return shortMessage2;
                }
                shortMessage2.setMessage(i, num.intValue(), 0);
                return shortMessage2;
            case ShortMessage.TUNE_REQUEST:
            case ShortMessage.TIMING_CLOCK:
            case ShortMessage.START:
            case ShortMessage.CONTINUE:
            case ShortMessage.STOP:
            case ShortMessage.ACTIVE_SENSING:
                if (num == null) {
                    ShortMessage shortMessage3 = new ShortMessage();
                    shortMessage3.setMessage(i, 0, 0);
                    return shortMessage3;
                }
                throw new InvalidMidiDataException(String.format("Invalid data: %02x", new Object[]{num}));
            default:
                throw new InvalidMidiDataException(String.format("Invalid data: %02x", new Object[]{Integer.valueOf(i)}));
        }
    }

    private static ShortMessage processRunningMessage(int i, int i2, MidiDataInputStream midiDataInputStream) throws InvalidMidiDataException, IOException {
        int i3 = i & 240;
        if (!(i3 == 128 || i3 == 144 || i3 == 160 || i3 == 176)) {
            if (i3 == 192 || i3 == 208) {
                ShortMessage shortMessage = new ShortMessage();
                shortMessage.setMessage(i, i2, 0);
                return shortMessage;
            } else if (i3 != 224) {
                throw new InvalidMidiDataException(String.format("Invalid data: %02x %02x", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
            }
        }
        ShortMessage shortMessage2 = new ShortMessage();
        shortMessage2.setMessage(i, i2, midiDataInputStream.readUnsignedByte());
        return shortMessage2;
    }

    private static ByteArrayInputStream convertToByteArrayInputStream(InputStream inputStream) throws IOException {
        if (inputStream instanceof ByteArrayInputStream) {
            return (ByteArrayInputStream) inputStream;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[TarConstants.DEFAULT_BLKSIZE];
        while (true) {
            int read = inputStream.read(bArr);
            if (read < 0) {
                return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public Sequence getSequence(URL url) throws InvalidMidiDataException, IOException {
        InputStream openStream = url.openStream();
        try {
            return getSequence(openStream);
        } finally {
            openStream.close();
        }
    }

    public Sequence getSequence(File file) throws InvalidMidiDataException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return getSequence((InputStream) fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }
}
