package org.apache.commons.compress.archivers.examples;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.examples.Expander;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda1 implements Expander.ArchiveEntrySupplier {
    public final /* synthetic */ ArchiveInputStream f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda1(ArchiveInputStream archiveInputStream) {
        this.f$0 = archiveInputStream;
    }

    public final ArchiveEntry getNextReadableEntry() {
        return Expander.lambda$expand$0(this.f$0);
    }
}
