package org.apache.commons.compress.archivers.examples;

import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.utils.IOUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda2 implements Expander.EntryWriter {
    public final /* synthetic */ ArchiveInputStream f$0;

    public /* synthetic */ Expander$$ExternalSyntheticLambda2(ArchiveInputStream archiveInputStream) {
        this.f$0 = archiveInputStream;
    }

    public final void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) {
        IOUtils.copy((InputStream) this.f$0, outputStream);
    }
}
