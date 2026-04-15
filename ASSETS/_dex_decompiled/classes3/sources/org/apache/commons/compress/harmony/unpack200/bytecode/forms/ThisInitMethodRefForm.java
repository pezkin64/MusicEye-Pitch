package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class ThisInitMethodRefForm extends InitMethodReferenceForm {
    public ThisInitMethodRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public String context(OperandManager operandManager) {
        return operandManager.getCurrentClass();
    }
}
