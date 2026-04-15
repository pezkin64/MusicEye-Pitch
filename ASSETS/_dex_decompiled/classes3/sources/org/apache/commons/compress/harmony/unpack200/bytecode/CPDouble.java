package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPDouble extends CPConstantNumber {
    public CPDouble(Double d, int i) {
        super((byte) 6, d, i);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeDouble(getNumber().doubleValue());
    }

    public String toString() {
        return "Double: " + getValue();
    }
}
