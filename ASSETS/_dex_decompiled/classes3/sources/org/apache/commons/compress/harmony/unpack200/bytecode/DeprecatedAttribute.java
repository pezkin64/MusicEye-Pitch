package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class DeprecatedAttribute extends Attribute {
    private static CPUTF8 attributeName;

    /* access modifiers changed from: protected */
    public int getLength() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public DeprecatedAttribute() {
        super(attributeName);
    }

    public String toString() {
        return "Deprecated Attribute";
    }
}
