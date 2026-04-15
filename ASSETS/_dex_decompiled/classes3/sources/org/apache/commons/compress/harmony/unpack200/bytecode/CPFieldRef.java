package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPFieldRef extends ConstantPoolEntry {
    private int cachedHashCode;
    CPClass className;
    transient int classNameIndex;
    private boolean hashcodeComputed;
    private final CPNameAndType nameAndType;
    transient int nameAndTypeIndex;

    public CPFieldRef(CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super((byte) 9, i);
        this.className = cPClass;
        this.nameAndType = cPNameAndType;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.nameAndTypeIndex = classConstantPool.indexOf(this.nameAndType);
        this.classNameIndex = classConstantPool.indexOf(this.className);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.classNameIndex);
        dataOutputStream.writeShort(this.nameAndTypeIndex);
    }

    public String toString() {
        return "FieldRef: " + this.className + "#" + this.nameAndType;
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        CPClass cPClass = this.className;
        int i = 0;
        int hashCode = ((cPClass == null ? 0 : cPClass.hashCode()) + 31) * 31;
        CPNameAndType cPNameAndType = this.nameAndType;
        if (cPNameAndType != null) {
            i = cPNameAndType.hashCode();
        }
        this.cachedHashCode = hashCode + i;
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPFieldRef cPFieldRef = (CPFieldRef) obj;
        CPClass cPClass = this.className;
        if (cPClass == null) {
            if (cPFieldRef.className != null) {
                return false;
            }
        } else if (!cPClass.equals(cPFieldRef.className)) {
            return false;
        }
        CPNameAndType cPNameAndType = this.nameAndType;
        if (cPNameAndType == null) {
            if (cPFieldRef.nameAndType != null) {
                return false;
            }
        } else if (!cPNameAndType.equals(cPFieldRef.nameAndType)) {
            return false;
        }
        return true;
    }
}
