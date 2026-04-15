package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

public abstract class BCIRenumberedAttribute extends Attribute {
    protected boolean renumbered;

    /* access modifiers changed from: protected */
    public abstract int getLength();

    /* access modifiers changed from: protected */
    public abstract int[] getStartPCs();

    public boolean hasBCIRenumbering() {
        return true;
    }

    public abstract String toString();

    /* access modifiers changed from: protected */
    public abstract void writeBody(DataOutputStream dataOutputStream) throws IOException;

    public BCIRenumberedAttribute(CPUTF8 cputf8) {
        super(cputf8);
    }

    public void renumber(List list) throws Pack200Exception {
        if (!this.renumbered) {
            this.renumbered = true;
            int[] startPCs = getStartPCs();
            for (int i = 0; i < startPCs.length; i++) {
                startPCs[i] = ((Integer) list.get(startPCs[i])).intValue();
            }
            return;
        }
        throw new Error("Trying to renumber a line number table that has already been renumbered");
    }
}
