package org.apache.commons.compress.harmony.pack200;

import org.objectweb.asm.ClassReader;

public class Pack200ClassReader extends ClassReader {
    private boolean anySyntheticAttributes;
    private String fileName;
    private boolean lastConstantHadWideIndex;
    private int lastUnsignedShort;

    public Pack200ClassReader(byte[] bArr) {
        super(bArr);
    }

    public int readUnsignedShort(int i) {
        int readUnsignedShort = Pack200ClassReader.super.readUnsignedShort(i);
        if (this.b[i - 1] == 19) {
            this.lastUnsignedShort = readUnsignedShort;
            return readUnsignedShort;
        }
        this.lastUnsignedShort = -32768;
        return readUnsignedShort;
    }

    public Object readConst(int i, char[] cArr) {
        this.lastConstantHadWideIndex = i == this.lastUnsignedShort;
        return Pack200ClassReader.super.readConst(i, cArr);
    }

    public String readUTF8(int i, char[] cArr) {
        String readUTF8 = Pack200ClassReader.super.readUTF8(i, cArr);
        if (!this.anySyntheticAttributes && "Synthetic".equals(readUTF8)) {
            this.anySyntheticAttributes = true;
        }
        return readUTF8;
    }

    public boolean lastConstantHadWideIndex() {
        return this.lastConstantHadWideIndex;
    }

    public boolean hasSyntheticAttributes() {
        return this.anySyntheticAttributes;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String getFileName() {
        return this.fileName;
    }
}
