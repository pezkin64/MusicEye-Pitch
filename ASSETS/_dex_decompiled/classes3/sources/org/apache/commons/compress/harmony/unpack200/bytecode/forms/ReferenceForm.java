package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public abstract class ReferenceForm extends ByteCodeForm {
    /* access modifiers changed from: protected */
    public abstract int getOffset(OperandManager operandManager);

    /* access modifiers changed from: protected */
    public abstract int getPoolID();

    public ReferenceForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        ClassFileEntry[] classFileEntryArr = {operandManager.globalConstantPool().getConstantPoolEntry(getPoolID(), (long) i)};
        if (classFileEntryArr[0] != null) {
            byteCode.setNested(classFileEntryArr);
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
            return;
        }
        throw new NullPointerException("Null nested entries are not allowed");
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        try {
            setNestedEntries(byteCode, operandManager, getOffset(operandManager));
        } catch (Pack200Exception unused) {
            throw new Error("Got a pack200 exception. What to do?");
        }
    }
}
