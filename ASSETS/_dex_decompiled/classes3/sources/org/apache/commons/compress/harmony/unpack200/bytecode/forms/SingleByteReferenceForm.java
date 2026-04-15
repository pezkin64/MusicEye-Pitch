package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public abstract class SingleByteReferenceForm extends ReferenceForm {
    protected boolean widened;

    /* access modifiers changed from: protected */
    public abstract int getOffset(OperandManager operandManager);

    /* access modifiers changed from: protected */
    public abstract int getPoolID();

    public SingleByteReferenceForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        super.setNestedEntries(byteCode, operandManager, i);
        if (this.widened) {
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
            return;
        }
        byteCode.setNestedPositions(new int[][]{new int[]{0, 1}});
    }

    public boolean nestedMustStartClassPool() {
        return !this.widened;
    }
}
