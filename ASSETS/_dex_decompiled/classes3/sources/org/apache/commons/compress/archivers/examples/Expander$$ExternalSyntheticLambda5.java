package org.apache.commons.compress.archivers.examples;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda5 implements Expander.ArchiveEntrySupplier {
    public final /* synthetic */ SevenZFile f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda5(SevenZFile sevenZFile) {
        this.f$0 = sevenZFile;
    }

    public final ArchiveEntry getNextReadableEntry() {
        return this.f$0.getNextEntry();
    }
}
