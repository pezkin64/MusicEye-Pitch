package jp.kshoji.javax.sound.midi;

public abstract class MidiMessage implements Cloneable {
    protected byte[] data;
    protected int length;

    public abstract Object clone();

    protected MidiMessage(byte[] bArr) {
        this.data = bArr;
        if (bArr == null) {
            this.length = 0;
        } else {
            this.length = bArr.length;
        }
    }

    public void setMessage(byte[] bArr, int i) throws InvalidMidiDataException {
        if (bArr == null) {
            this.data = null;
            this.length = 0;
            return;
        }
        if (this.data.length != bArr.length) {
            this.data = new byte[bArr.length];
        }
        this.length = bArr.length;
        System.arraycopy(bArr, 0, this.data, 0, bArr.length);
    }

    public byte[] getMessage() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int getStatus() {
        byte[] bArr = this.data;
        if (bArr == null || bArr.length < 1) {
            return 0;
        }
        return bArr[0] & 255;
    }

    public int getLength() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder("[");
        int length2 = bArr.length;
        int i = 0;
        boolean z = false;
        while (i < length2) {
            byte b = bArr[i];
            if (z) {
                sb.append(", ");
            }
            sb.append(String.format("%02x", new Object[]{Integer.valueOf(b & 255)}));
            i++;
            z = true;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        return getClass().getName() + ":" + toHexString(this.data);
    }
}
