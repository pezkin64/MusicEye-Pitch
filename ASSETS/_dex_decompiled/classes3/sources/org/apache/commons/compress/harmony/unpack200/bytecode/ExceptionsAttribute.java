package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ExceptionsAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private transient int[] exceptionIndexes;
    private final CPClass[] exceptions;

    private static int hashCode(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        int i = 1;
        for (int i2 = 0; i2 < objArr.length; i2++) {
            int i3 = i * 31;
            Object obj = objArr[i2];
            i = i3 + (obj == null ? 0 : obj.hashCode());
        }
        return i;
    }

    public ExceptionsAttribute(CPClass[] cPClassArr) {
        super(attributeName);
        this.exceptions = cPClassArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return super.equals(obj) && getClass() == obj.getClass() && Arrays.equals(this.exceptions, ((ExceptionsAttribute) obj).exceptions);
    }

    /* access modifiers changed from: protected */
    public int getLength() {
        return (this.exceptions.length * 2) + 2;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[(this.exceptions.length + 1)];
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i < cPClassArr.length) {
                classFileEntryArr[i] = cPClassArr[i];
                i++;
            } else {
                classFileEntryArr[cPClassArr.length] = getAttributeName();
                return classFileEntryArr;
            }
        }
    }

    public int hashCode() {
        return (super.hashCode() * 31) + hashCode(this.exceptions);
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.exceptionIndexes = new int[this.exceptions.length];
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i < cPClassArr.length) {
                cPClassArr[i].resolve(classConstantPool);
                this.exceptionIndexes[i] = classConstantPool.indexOf(this.exceptions[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Exceptions: ");
        int i = 0;
        while (true) {
            CPClass[] cPClassArr = this.exceptions;
            if (i >= cPClassArr.length) {
                return stringBuffer.toString();
            }
            stringBuffer.append(cPClassArr[i]);
            stringBuffer.append(' ');
            i++;
        }
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.exceptionIndexes.length);
        int i = 0;
        while (true) {
            int[] iArr = this.exceptionIndexes;
            if (i < iArr.length) {
                dataOutputStream.writeShort(iArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }
}
