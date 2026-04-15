package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SignatureAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final CPUTF8 signature;
    private int signature_index;

    /* access modifiers changed from: protected */
    public int getLength() {
        return 2;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public SignatureAttribute(CPUTF8 cputf8) {
        super(attributeName);
        this.signature = cputf8;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.signature};
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.signature.resolve(classConstantPool);
        this.signature_index = classConstantPool.indexOf(this.signature);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.signature_index);
    }

    public String toString() {
        return "Signature: " + this.signature;
    }
}
