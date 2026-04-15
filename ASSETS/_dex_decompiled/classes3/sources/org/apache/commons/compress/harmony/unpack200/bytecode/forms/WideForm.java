package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class WideForm extends VariableInstructionForm {
    public WideForm(int i, String str) {
        super(i, str);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextWideByteCode = operandManager.nextWideByteCode();
        if (nextWideByteCode == 132) {
            setByteCodeOperandsFormat2(nextWideByteCode, byteCode, operandManager, i);
        } else {
            setByteCodeOperandsFormat1(nextWideByteCode, byteCode, operandManager, i);
        }
    }

    /* access modifiers changed from: protected */
    public void setByteCodeOperandsFormat1(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int nextLocal = operandManager.nextLocal();
        int[] iArr = new int[4];
        iArr[0] = byteCode.getOpcode();
        iArr[1] = i;
        setRewrite2Bytes(nextLocal, 2, iArr);
        byteCode.setRewrite(iArr);
    }

    /* access modifiers changed from: protected */
    public void setByteCodeOperandsFormat2(int i, ByteCode byteCode, OperandManager operandManager, int i2) {
        int nextLocal = operandManager.nextLocal();
        int nextShort = operandManager.nextShort();
        int[] iArr = new int[6];
        iArr[0] = byteCode.getOpcode();
        iArr[1] = i;
        setRewrite2Bytes(nextLocal, 2, iArr);
        setRewrite2Bytes(nextShort, 4, iArr);
        byteCode.setRewrite(iArr);
    }
}
