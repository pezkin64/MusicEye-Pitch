package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class FloatRefForm extends SingleByteReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 3;
    }

    public FloatRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public FloatRefForm(int i, String str, int[] iArr, boolean z) {
        this(i, str, iArr);
        this.widened = z;
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextFloatRef();
    }
}
