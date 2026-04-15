package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class SourceFileAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final CPUTF8 name;
    private int nameIndex;

    /* access modifiers changed from: protected */
    public int getLength() {
        return 2;
    }

    public boolean isSourceFileAttribute() {
        return true;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public SourceFileAttribute(CPUTF8 cputf8) {
        super(attributeName);
        this.name = cputf8;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        SourceFileAttribute sourceFileAttribute = (SourceFileAttribute) obj;
        CPUTF8 cputf8 = this.name;
        if (cputf8 == null) {
            if (sourceFileAttribute.name != null) {
                return false;
            }
        } else if (!cputf8.equals(sourceFileAttribute.name)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.name};
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        CPUTF8 cputf8 = this.name;
        return hashCode + (cputf8 == null ? 0 : cputf8.hashCode());
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameIndex = classConstantPool.indexOf(this.name);
    }

    public String toString() {
        return "SourceFile: " + this.name;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.nameIndex);
    }
}
