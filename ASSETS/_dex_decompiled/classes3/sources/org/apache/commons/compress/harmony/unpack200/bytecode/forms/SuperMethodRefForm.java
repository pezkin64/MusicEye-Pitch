package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class SuperMethodRefForm extends ClassSpecificReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 11;
    }

    public SuperMethodRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextSuperMethodRef();
    }

    /* access modifiers changed from: protected */
    public String context(OperandManager operandManager) {
        return operandManager.getSuperClass();
    }
}
