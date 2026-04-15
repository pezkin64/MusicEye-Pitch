package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CPLong extends CPConstantNumber {
    public CPLong(Long l, int i) {
        super((byte) 5, l, i);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeLong(getNumber().longValue());
    }

    public String toString() {
        return "Long: " + getValue();
    }
}
