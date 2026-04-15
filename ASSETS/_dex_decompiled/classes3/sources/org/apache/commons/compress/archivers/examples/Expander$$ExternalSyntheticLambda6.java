package org.apache.commons.compress.archivers.examples;

import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda6 implements Expander.EntryWriter {
    public final /* synthetic */ SevenZFile f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda6(SevenZFile sevenZFile) {
        this.f$0 = sevenZFile;
    }

    public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
        Expander.lambda$expand$6(this.f$0, archiveEntry, outputStream);
    }
}
