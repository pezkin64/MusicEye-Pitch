package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class ClassFileEntry {
    protected static final ClassFileEntry[] NONE = new ClassFileEntry[0];
    private boolean resolved;

    /* access modifiers changed from: protected */
    public abstract void doWrite(DataOutputStream dataOutputStream) throws IOException;

    public abstract boolean equals(Object obj);

    public abstract int hashCode();

    public abstract String toString();

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return NONE;
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        this.resolved = true;
    }

    /* access modifiers changed from: protected */
    public int objectHashCode() {
        return super.hashCode();
    }

    public final void write(DataOutputStream dataOutputStream) throws IOException {
        if (this.resolved) {
            doWrite(dataOutputStream);
            return;
        }
        throw new IllegalStateException("Entry has not been resolved");
    }
}
