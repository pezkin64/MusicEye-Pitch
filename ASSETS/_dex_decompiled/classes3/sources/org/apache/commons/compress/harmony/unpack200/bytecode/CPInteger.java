package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPInteger extends CPConstantNumber {
    public CPInteger(Integer num, int i) {
        super((byte) 3, num, i);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(getNumber().intValue());
    }

    public String toString() {
        return "Integer: " + getValue();
    }
}
