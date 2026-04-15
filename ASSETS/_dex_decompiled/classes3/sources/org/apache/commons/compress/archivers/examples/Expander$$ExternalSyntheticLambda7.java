package org.apache.commons.compress.archivers.examples;

import java.util.Iterator;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda7 implements Expander.ArchiveEntrySupplier {
    public final /* synthetic */ Iterator f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda7(Iterator it) {
        this.f$0 = it;
    }

    public final ArchiveEntry getNextReadableEntry() {
        return Expander.lambda$expand$2(this.f$0);
    }
}
