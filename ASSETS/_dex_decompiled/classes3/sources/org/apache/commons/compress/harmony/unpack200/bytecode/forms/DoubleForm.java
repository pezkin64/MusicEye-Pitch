package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class DoubleForm extends ReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 5;
    }

    public DoubleForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextDoubleRef();
    }
}
