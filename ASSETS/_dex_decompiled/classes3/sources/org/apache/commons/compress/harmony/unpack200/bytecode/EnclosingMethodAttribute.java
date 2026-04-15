package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;

public class EnclosingMethodAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private int class_index;
    private final CPClass cpClass;
    private final CPNameAndType method;
    private int method_index;

    /* access modifiers changed from: protected */
    public int getLength() {
        return 4;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public EnclosingMethodAttribute(CPClass cPClass, CPNameAndType cPNameAndType) {
        super(attributeName);
        this.cpClass = cPClass;
        this.method = cPNameAndType;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        CPNameAndType cPNameAndType = this.method;
        if (cPNameAndType != null) {
            return new ClassFileEntry[]{attributeName, this.cpClass, cPNameAndType};
        }
        return new ClassFileEntry[]{attributeName, this.cpClass};
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        this.cpClass.resolve(classConstantPool);
        this.class_index = classConstantPool.indexOf(this.cpClass);
        CPNameAndType cPNameAndType = this.method;
        if (cPNameAndType != null) {
            cPNameAndType.resolve(classConstantPool);
            this.method_index = classConstantPool.indexOf(this.method);
            return;
        }
        this.method_index = 0;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.class_index);
        dataOutputStream.writeShort(this.method_index);
    }

    public String toString() {
        return AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD;
    }
}
