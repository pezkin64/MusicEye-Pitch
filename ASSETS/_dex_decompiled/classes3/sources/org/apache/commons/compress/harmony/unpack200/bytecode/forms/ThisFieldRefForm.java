package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class ThisFieldRefForm extends ClassSpecificReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 10;
    }

    public ThisFieldRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextThisFieldRef();
    }

    /* access modifiers changed from: protected */
    public String context(OperandManager operandManager) {
        return operandManager.getCurrentClass();
    }
}
