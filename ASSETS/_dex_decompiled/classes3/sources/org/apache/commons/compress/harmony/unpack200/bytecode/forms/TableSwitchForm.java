package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class TableSwitchForm extends SwitchForm {
    public TableSwitchForm(int i, String str) {
        super(i, str);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextCaseCount = operandManager.nextCaseCount();
        int nextLabel = operandManager.nextLabel();
        int nextCaseValues = operandManager.nextCaseValues();
        int[] iArr = new int[nextCaseCount];
        for (int i2 = 0; i2 < nextCaseCount; i2++) {
            iArr[i2] = operandManager.nextLabel();
        }
        int i3 = nextCaseCount + 1;
        int[] iArr2 = new int[i3];
        iArr2[0] = nextLabel;
        int i4 = 1;
        for (int i5 = 1; i5 < i3; i5++) {
            iArr2[i5] = iArr[i5 - 1];
        }
        byteCode.setByteCodeTargets(iArr2);
        int i6 = (nextCaseValues + nextCaseCount) - 1;
        int i7 = i % 4;
        int i8 = 3 - i7;
        int[] iArr3 = new int[((16 - i7) + (nextCaseCount * 4))];
        iArr3[0] = byteCode.getOpcode();
        int i9 = 0;
        while (i9 < i8) {
            iArr3[i4] = 0;
            i9++;
            i4++;
        }
        iArr3[i4] = -1;
        iArr3[i4 + 1] = -1;
        iArr3[i4 + 2] = -1;
        iArr3[i4 + 3] = -1;
        setRewrite4Bytes(nextCaseValues, i4 + 4, iArr3);
        setRewrite4Bytes(i6, i4 + 8, iArr3);
        int i10 = i4 + 12;
        for (int i11 = 0; i11 < nextCaseCount; i11++) {
            iArr3[i10] = -1;
            iArr3[i10 + 1] = -1;
            int i12 = i10 + 3;
            iArr3[i10 + 2] = -1;
            i10 += 4;
            iArr3[i12] = -1;
        }
        byteCode.setRewrite(iArr3);
    }
}
