package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class IMethodRefForm extends ReferenceForm {
    /* access modifiers changed from: protected */
    public int getPoolID() {
        return 12;
    }

    public IMethodRefForm(int i, String str, int[] iArr) {
        super(i, str, iArr);
    }

    /* access modifiers changed from: protected */
    public int getOffset(OperandManager operandManager) {
        return operandManager.nextIMethodRef();
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        super.setByteCodeOperands(byteCode, operandManager, i);
        byteCode.getRewrite()[3] = ((CPInterfaceMethodRef) byteCode.getNestedClassFileEntries()[0]).invokeInterfaceCount();
    }
}
