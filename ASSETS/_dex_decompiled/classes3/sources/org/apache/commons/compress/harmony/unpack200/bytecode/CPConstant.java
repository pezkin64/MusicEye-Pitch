package org.apache.commons.compress.harmony.unpack200.bytecode;

public abstract class CPConstant extends ConstantPoolEntry {
    private final Object value;

    public CPConstant(byte b, Object obj, int i) {
        super(b, i);
        this.value = obj;
        if (obj == null) {
            throw new NullPointerException("Null arguments are not allowed");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CPConstant cPConstant = (CPConstant) obj;
        Object obj2 = this.value;
        if (obj2 == null) {
            if (cPConstant.value != null) {
                return false;
            }
        } else if (!obj2.equals(cPConstant.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.value;
        return 31 + (obj == null ? 0 : obj.hashCode());
    }

    /* access modifiers changed from: protected */
    public Object getValue() {
        return this.value;
    }
}
