package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CPRef extends ConstantPoolEntry {
    protected String cachedToString;
    CPClass className;
    transient int classNameIndex;
    protected CPNameAndType nameAndType;
    transient int nameAndTypeIndex;

    public CPRef(byte b, CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super(b, i);
        this.className = cPClass;
        this.nameAndType = cPNameAndType;
        if (cPNameAndType == null || cPClass == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || hashCode() != obj.hashCode()) {
            return false;
        }
        CPRef cPRef = (CPRef) obj;
        return this.className.equals(cPRef.className) && this.nameAndType.equals(cPRef.nameAndType);
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

    public String toString() {
        String str;
        if (this.cachedToString == null) {
            if (getTag() == 9) {
                str = "FieldRef";
            } else if (getTag() == 10) {
                str = "MethoddRef";
            } else if (getTag() == 11) {
                str = "InterfaceMethodRef";
            } else {
                str = "unknown";
            }
            this.cachedToString = str + ": " + this.className + "#" + this.nameAndType;
        }
        return this.cachedToString;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.classNameIndex);
        dataOutputStream.writeShort(this.nameAndTypeIndex);
    }
}
