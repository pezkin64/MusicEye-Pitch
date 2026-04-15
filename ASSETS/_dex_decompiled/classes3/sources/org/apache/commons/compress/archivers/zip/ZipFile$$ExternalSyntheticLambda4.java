package org.apache.commons.compress.archivers.zip;

import java.util.function.ToLongFunction;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ZipFile$$ExternalSyntheticLambda4 implements ToLongFunction {
    public final long applyAsLong(Object obj) {
        return ((ZipArchiveEntry) obj).getLocalHeaderOffset();
    }
}
