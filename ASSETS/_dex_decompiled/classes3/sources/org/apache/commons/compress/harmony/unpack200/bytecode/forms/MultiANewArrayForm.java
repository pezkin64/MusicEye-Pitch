package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class MultiANewArrayForm extends ClassRefForm {
    public MultiANewArrayForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        super.setByteCodeOperands(byteCode, operandManager, i);
        byteCode.setOperandByte(operandManager.nextByte(), 2);
    }
}
