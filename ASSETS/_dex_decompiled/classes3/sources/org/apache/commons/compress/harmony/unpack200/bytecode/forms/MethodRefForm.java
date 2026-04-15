package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class MethodRefForm extends ReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 11;
    }

    public MethodRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextMethodRef();
    }
}
