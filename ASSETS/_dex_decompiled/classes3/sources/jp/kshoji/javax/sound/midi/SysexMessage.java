package jp.kshoji.javax.sound.midi;

public class SysexMessage extends MidiMessage {
    public SysexMessage() {
        this(new byte[]{-16, -9});
    }

    protected SysexMessage(byte[] bArr) {
        super(bArr);
    }

    public SysexMessage(byte[] bArr, int i) throws InvalidMidiDataException {
        super((byte[]) null);
        setMessage(bArr, i);
    }

    public SysexMessage(int i, byte[] bArr, int i2) throws InvalidMidiDataException {
        super((byte[]) null);
        setMessage(i, bArr, i2);
    }

    public void setMessage(byte[] bArr, int i) throws InvalidMidiDataException {
        if (bArr != null) {
            byte b = bArr[0] & 255;
            if (b == 240 || b == 247) {
                super.setMessage(bArr, i);
                return;
            }
            throw new InvalidMidiDataException("Invalid status byte for SysexMessage: 0x" + Integer.toHexString(b));
        }
        throw new InvalidMidiDataException("SysexMessage data is null");
    }

    public void setMessage(int i, byte[] bArr, int i2) throws InvalidMidiDataException {
        if (i == 240 || i == 247) {
            this.data = new byte[(bArr.length + 1)];
            this.length = this.data.length;
            this.data[0] = (byte) (i & 255);
            if (bArr.length > 0) {
                System.arraycopy(bArr, 0, this.data, 1, bArr.length);
                return;
            }
            return;
        }
        throw new InvalidMidiDataException("Invalid status byte for SysexMessage: 0x" + Integer.toHexString(i));
    }

    public byte[] getData() {
        int length = this.data.length;
        byte[] bArr = new byte[length];
        System.arraycopy(this.data, 0, bArr, 0, length);
        return bArr;
    }

    public Object clone() {
        return new SysexMessage(getData());
    }
}
