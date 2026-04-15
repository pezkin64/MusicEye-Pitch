package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int constantIndex;
    private final ClassFileEntry entry;

    /* access modifiers changed from: protected */
    public int getLength() {
        return 2;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public ConstantValueAttribute(ClassFileEntry classFileEntry) {
        super(attributeName);
        classFileEntry.getClass();
        this.entry = classFileEntry;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        ConstantValueAttribute constantValueAttribute = (ConstantValueAttribute) obj;
        ClassFileEntry classFileEntry = this.entry;
        if (classFileEntry == null) {
            if (constantValueAttribute.entry != null) {
                return false;
            }
        } else if (!classFileEntry.equals(constantValueAttribute.entry)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName(), this.entry};
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        ClassFileEntry classFileEntry = this.entry;
        return hashCode + (classFileEntry == null ? 0 : classFileEntry.hashCode());
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.entry.resolve(classConstantPool);
        this.constantIndex = classConstantPool.indexOf(this.entry);
    }

    public String toString() {
        return "Constant:" + this.entry;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.constantIndex);
    }
}
