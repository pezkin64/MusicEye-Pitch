package org.apache.commons.compress.archivers.examples;

import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.archivers.zip.ZipFile;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda4 implements Expander.EntryWriter {
    public final /* synthetic */ ZipFile f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda4(ZipFile zipFile) {
        this.f$0 = zipFile;
    }

    public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
        Expander.lambda$expand$5(this.f$0, archiveEntry, outputStream);
    }
}
