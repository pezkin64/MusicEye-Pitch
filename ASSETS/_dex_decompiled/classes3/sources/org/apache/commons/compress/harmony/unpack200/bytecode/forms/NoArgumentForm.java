package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class NoArgumentForm extends ByteCodeForm {
    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
    }

    public NoArgumentForm(int i, String str) {
        super(i, str);
    }
}
