package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public class LookupSwitchForm extends SwitchForm {
    public LookupSwitchForm(int i, String str) {
        super(i, str);
    }

    public void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i) {
        int nextCaseCount = operandManager.nextCaseCount();
        int nextLabel = operandManager.nextLabel();
        int[] iArr = new int[nextCaseCount];
        for (int i2 = 0; i2 < nextCaseCount; i2++) {
            iArr[i2] = operandManager.nextCaseValues();
        }
        int[] iArr2 = new int[nextCaseCount];
        for (int i3 = 0; i3 < nextCaseCount; i3++) {
            iArr2[i3] = operandManager.nextLabel();
        }
        int i4 = nextCaseCount + 1;
        int[] iArr3 = new int[i4];
        iArr3[0] = nextLabel;
        int i5 = 1;
        for (int i6 = 1; i6 < i4; i6++) {
            iArr3[i6] = iArr2[i6 - 1];
        }
        byteCode.setByteCodeTargets(iArr3);
        int i7 = i % 4;
        int i8 = 3 - i7;
        int i9 = nextCaseCount * 4;
        int[] iArr4 = new int[((12 - i7) + i9 + i9)];
        iArr4[0] = byteCode.getOpcode();
        int i10 = 0;
        while (i10 < i8) {
            iArr4[i5] = 0;
            i10++;
            i5++;
        }
        iArr4[i5] = -1;
        iArr4[i5 + 1] = -1;
        iArr4[i5 + 2] = -1;
        iArr4[i5 + 3] = -1;
        setRewrite4Bytes(nextCaseCount, i5 + 4, iArr4);
        int i11 = i5 + 8;
        for (int i12 = 0; i12 < nextCaseCount; i12++) {
            setRewrite4Bytes(iArr[i12], i11, iArr4);
            iArr4[i11 + 4] = -1;
            iArr4[i11 + 5] = -1;
            int i13 = i11 + 7;
            iArr4[i11 + 6] = -1;
            i11 += 8;
            iArr4[i13] = -1;
        }
        byteCode.setRewrite(iArr4);
    }
}
