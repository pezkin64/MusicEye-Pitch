package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class StringRefForm extends SingleByteReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 6;
    }

    public StringRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public StringRefForm(int i, String str, int[] iArr, boolean z) {
        this(i, str, iArr);
        this.widened = z;
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextStringRef();
    }

    /* access modifiers changed from: protected */
    public void setNestedEntries(ByteCode byteCode, OperandManager operandManager, int i) throws Pack200Exception {
        byteCode.setNested(new ClassFileEntry[]{(CPString) operandManager.globalConstantPool().getValue(getPoolID(), (long) i)});
        if (this.widened) {
            byteCode.setNestedPositions(new int[][]{new int[]{0, 2}});
            return;
        }
        byteCode.setNestedPositions(new int[][]{new int[]{0, 1}});
    }
}
