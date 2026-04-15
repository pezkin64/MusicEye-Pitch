package org.apache.commons.compress.archivers.examples;

import java.util.Enumeration;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.examples.Expander;
import org.apache.commons.compress.archivers.zip.ZipFile;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Expander$$ExternalSyntheticLambda3 implements Expander.ArchiveEntrySupplier {
    public final /* synthetic */ Enumeration f$0;
    public final /* synthetic */ ZipFile f$1;

    public /* synthetic */ Expander$$ExternalSyntheticLambda3(Enumeration enumeration, ZipFile zipFile) {
        this.f$0 = enumeration;
        this.f$1 = zipFile;
    }

    public final ArchiveEntry getNextReadableEntry() {
        return Expander.lambda$expand$4(this.f$0, this.f$1);
    }
}
