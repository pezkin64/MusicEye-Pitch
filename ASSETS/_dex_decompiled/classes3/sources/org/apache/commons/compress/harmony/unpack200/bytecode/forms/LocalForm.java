package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class LocalForm extends ByteCodeForm {
    public LocalForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        byteCode.setOperandBytes(new int[]{operandManager.nextLocal()});
    }
}
