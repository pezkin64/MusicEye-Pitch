package org.apache.commons.compress.archivers.examples;

import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.archivers.tar.TarFile;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda8 implements Expander.EntryWriter {
    public final /* synthetic */ TarFile f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda8(TarFile tarFile) {
        this.f$0 = tarFile;
    }

    public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
        Expander.lambda$expand$3(this.f$0, archiveEntry, outputStream);
    }
}
