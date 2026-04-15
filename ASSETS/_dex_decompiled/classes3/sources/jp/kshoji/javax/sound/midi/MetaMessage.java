package jp.kshoji.javax.sound.midi;

import java.util.Arrays;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class MetaMessage extends MidiMessage {
    public static final int META = 255;
    public static final int TYPE_END_OF_TRACK = 47;
    public static final int TYPE_TEMPO = 81;
    private static final byte[] defaultMessage = {-1, 0};
    private int dataLength;

    private static int getMidiValuesLength(long j) {
        int i = 0;
        do {
            j >>= 7;
            i++;
        } while (j > 0);
        return i;
    }

    public MetaMessage() {
        this(defaultMessage);
    }

    protected MetaMessage(byte[] bArr) throws NegativeArraySizeException {
        super(bArr);
        this.dataLength = 0;
        if (bArr.length >= 3) {
            this.dataLength = bArr.length - 3;
            int i = 2;
            while (i < bArr.length && (bArr[i] & 128) != 0) {
                this.dataLength--;
                i++;
            }
            if (this.dataLength < 0) {
                throw new NegativeArraySizeException("Invalid meta event. data: " + Arrays.toString(bArr));
            }
            return;
        }
        throw new NegativeArraySizeException("Invalid meta event. data: " + Arrays.toString(bArr));
    }

    public MetaMessage(int i, byte[] bArr, int i2) throws InvalidMidiDataException {
        super((byte[]) null);
        this.dataLength = 0;
        setMessage(i, bArr, i2);
    }

    public void setMessage(int i, byte[] bArr, int i2) throws InvalidMidiDataException {
        if (i >= 128 || i < 0) {
            throw new InvalidMidiDataException("Invalid meta event. type: " + i);
        }
        int midiValuesLength = getMidiValuesLength((long) bArr.length) + 2;
        this.dataLength = bArr.length;
        this.data = new byte[(bArr.length + midiValuesLength)];
        this.length = this.data.length;
        this.data[0] = -1;
        this.data[1] = (byte) i;
        this.data[2] = (byte) Math.min(bArr.length, WorkQueueKt.MASK);
        if (bArr.length > 0) {
            System.arraycopy(bArr, 0, this.data, midiValuesLength, bArr.length);
        }
    }

    public int getType() {
        if (this.data.length >= 2) {
            return this.data[1] & 255;
        }
        return 0;
    }

    public byte[] getData() {
        byte[] bArr = new byte[this.dataLength];
        byte[] bArr2 = this.data;
        int length = this.data.length;
        int i = this.dataLength;
        System.arraycopy(bArr2, length - i, bArr, 0, i);
        return bArr;
    }

    public Object clone() {
        byte[] bArr = new byte[this.data.length];
        System.arraycopy(this.data, 0, bArr, 0, this.data.length);
        return new MetaMessage(bArr);
    }

    private static void writeMidiValues(byte[] bArr, int i, long j) {
        int i2 = 63;
        while (i2 > 0 && (((long) (WorkQueueKt.MASK << i2)) & j) == 0) {
            i2 -= 7;
        }
        while (i2 > 0) {
            bArr[i] = (byte) ((int) (((((long) (WorkQueueKt.MASK << i2)) & j) >> i2) | 128));
            i2 -= 7;
            i++;
        }
        bArr[i] = (byte) ((int) (j & 127));
    }
}
