package org.apache.commons.compress.archivers.zip;

import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ParallelScatterZipCreator$$ExternalSyntheticLambda0 implements Callable {
    public final /* synthetic */ ParallelScatterZipCreator f$0;
    public final /* synthetic */ ZipArchiveEntryRequestSupplier f$1;

    public /* synthetic */ ParallelScatterZipCreator$$ExternalSyntheticLambda0(ParallelScatterZipCreator parallelScatterZipCreator, ZipArchiveEntryRequestSupplier zipArchiveEntryRequestSupplier) {
        this.f$0 = parallelScatterZipCreator;
        this.f$1 = zipArchiveEntryRequestSupplier;
    }

    public final Object call() {
        return this.f$0.m1586lambda$createCallable$2$orgapachecommonscompressarchiverszipParallelScatterZipCreator(this.f$1);
    }
}
