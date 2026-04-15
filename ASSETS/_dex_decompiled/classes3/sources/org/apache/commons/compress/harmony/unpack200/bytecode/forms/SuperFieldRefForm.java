package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class SuperFieldRefForm extends ClassSpecificReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 10;
    }

    public SuperFieldRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextSuperFieldRef();
    }

    /* access modifiers changed from: protected */
    public String context(OperandManager operandManager) {
        return operandManager.getSuperClass();
    }
}
