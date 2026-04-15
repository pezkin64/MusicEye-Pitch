package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public abstract class ClassSpecificReferenceForm extends ReferenceForm {
    /* access modifiers changed from: protected */
    public abstract String context(OperandManager operandManager);

    /* access modifiers changed from: protected */
    public abstract int getOffset(OperandManager operandManager);

    /* access modifiers changed from: protected */
    public abstract int getPoolID();

    public ClassSpecificReferenceForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        byteCode.setNested(new ClassFileEntry[]{operandManager.globalConstantPool().getClassSpecificPoolEntry(getPoolID(), (long) i, context(operandManager))});
        byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
    }
}
