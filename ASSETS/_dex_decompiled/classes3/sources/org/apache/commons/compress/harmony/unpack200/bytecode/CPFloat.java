package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPFloat extends CPConstantNumber {
    public CPFloat(Float f, int i) {
        super((byte) 4, f, i);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeFloat(getNumber().floatValue());
    }

    public String toString() {
        return "Float: " + getValue();
    }
}
