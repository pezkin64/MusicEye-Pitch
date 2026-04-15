package org.apache.commons.compress.harmony.unpack200.bytecode;

public abstract class CPConstantNumber extends CPConstant {
    public CPConstantNumber(byte b, Object obj, int i) {
        super(b, obj, i);
    }

    /* access modifiers changed from: protected */
    public Number getNumber() {
        return (Number) getValue();
    }
}
