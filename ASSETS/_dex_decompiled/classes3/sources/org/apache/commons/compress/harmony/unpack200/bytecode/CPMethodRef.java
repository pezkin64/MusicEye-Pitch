package org.apache.commons.compress.harmony.unpack200.bytecode;

public class CPMethodRef extends CPRef {
    private int cachedHashCode;
    private boolean hashcodeComputed;

    public CPMethodRef(CPClass cPClass, CPNameAndType cPNameAndType, int i) {
        super((byte) 10, cPClass, cPNameAndType, i);
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{this.className, this.nameAndType};
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = ((this.className.hashCode() + 31) * 31) + this.nameAndType.hashCode();
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }
}
