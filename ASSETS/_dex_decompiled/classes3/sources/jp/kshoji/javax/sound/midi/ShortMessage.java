package jp.kshoji.javax.sound.midi;

import com.google.common.primitives.SignedBytes;
import kotlin.jvm.internal.ByteCompanionObject;

public class ShortMessage extends MidiMessage {
    public static final int ACTIVE_SENSING = 254;
    public static final int BUS_SELECT = 245;
    public static final int CHANNEL_PRESSURE = 208;
    public static final int CONTINUE = 251;
    public static final int CONTROL_CHANGE = 176;
    public static final int END_OF_EXCLUSIVE = 247;
    public static final int MASK_CHANNEL = 15;
    public static final int MASK_EVENT = 240;
    public static final int MIDI_TIME_CODE = 241;
    public static final int NOTE_OFF = 128;
    public static final int NOTE_ON = 144;
    public static final int PITCH_BEND = 224;
    public static final int POLY_PRESSURE = 160;
    public static final int PROGRAM_CHANGE = 192;
    public static final int SONG_POSITION_POINTER = 242;
    public static final int SONG_SELECT = 243;
    public static final int START = 250;
    public static final int START_OF_EXCLUSIVE = 240;
    public static final int STOP = 252;
    public static final int SYSTEM_RESET = 255;
    public static final int TIMING_CLOCK = 248;
    public static final int TUNE_REQUEST = 246;

    public ShortMessage() {
        this(new byte[]{-112, SignedBytes.MAX_POWER_OF_TWO, ByteCompanionObject.MAX_VALUE});
    }

    protected ShortMessage(byte[] bArr) {
        super(bArr);
    }

    public ShortMessage(int i) throws InvalidMidiDataException {
        super((byte[]) null);
        setMessage(i);
    }

    public ShortMessage(int i, int i2, int i3) throws InvalidMidiDataException {
        super((byte[]) null);
        setMessage(i, i2, i3);
    }

    public ShortMessage(int i, int i2, int i3, int i4) throws InvalidMidiDataException {
        super((byte[]) null);
        setMessage(i, i2, i3, i4);
    }

    public void setMessage(int i) throws InvalidMidiDataException {
        int dataLength = getDataLength(i);
        if (dataLength == 0) {
            setMessage(i, 0, 0);
            return;
        }
        throw new InvalidMidiDataException("Status byte: " + i + " requires " + dataLength + " data bytes length");
    }

    public void setMessage(int i, int i2, int i3) throws InvalidMidiDataException {
        int dataLength = getDataLength(i);
        if (dataLength > 0) {
            if (i2 < 0 || i2 > 127) {
                throw new InvalidMidiDataException("data1 out of range: " + i2);
            } else if (dataLength > 1 && (i3 < 0 || i3 > 127)) {
                throw new InvalidMidiDataException("data2 out of range: " + i3);
            }
        }
        if (this.data == null || this.data.length != dataLength + 1) {
            this.data = new byte[(dataLength + 1)];
        }
        this.length = this.data.length;
        this.data[0] = (byte) (i & 255);
        if (this.data.length > 1) {
            this.data[1] = (byte) (i2 & 255);
            if (this.data.length > 2) {
                this.data[2] = (byte) (i3 & 255);
            }
        }
    }

    public void setMessage(int i, int i2, int i3, int i4) throws InvalidMidiDataException {
        if (i >= 240 || i < 128) {
            throw new InvalidMidiDataException("command out of range: 0x" + Integer.toHexString(i));
        } else if (i2 <= 15) {
            setMessage((i & 240) | (i2 & 15), i3, i4);
        } else {
            throw new InvalidMidiDataException("channel out of range: " + i2);
        }
    }

    public int getChannel() {
        return getStatus() & 15;
    }

    public int getCommand() {
        return getStatus() & 240;
    }

    public int getData1() {
        if (this.data.length > 1) {
            return this.data[1] & 255;
        }
        return 0;
    }

    public int getData2() {
        if (this.data.length > 2) {
            return this.data[2] & 255;
        }
        return 0;
    }

    public Object clone() {
        int length = this.data.length;
        byte[] bArr = new byte[length];
        System.arraycopy(this.data, 0, bArr, 0, length);
        return new ShortMessage(bArr);
    }

    protected static int getDataLength(int i) throws InvalidMidiDataException {
        switch (i) {
            case MIDI_TIME_CODE /*241*/:
            case SONG_SELECT /*243*/:
                return 1;
            case SONG_POSITION_POINTER /*242*/:
                return 2;
            case TUNE_REQUEST /*246*/:
            case END_OF_EXCLUSIVE /*247*/:
            case TIMING_CLOCK /*248*/:
            case 249:
            case START /*250*/:
            case CONTINUE /*251*/:
            case STOP /*252*/:
            case 253:
            case ACTIVE_SENSING /*254*/:
            case 255:
                return 0;
            default:
                int i2 = i & 240;
                if (!(i2 == 128 || i2 == 144 || i2 == 160 || i2 == 176)) {
                    if (i2 == 192 || i2 == 208) {
                        return 1;
                    }
                    if (i2 != 224) {
                        throw new InvalidMidiDataException("Invalid status byte: " + i);
                    }
                }
                return 2;
        }
    }
}
