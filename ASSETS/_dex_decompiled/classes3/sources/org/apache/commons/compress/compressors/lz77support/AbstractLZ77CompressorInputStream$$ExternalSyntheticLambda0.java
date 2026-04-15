package org.apache.commons.compress.compressors.lz77support;

import org.apache.commons.compress.utils.ByteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AbstractLZ77CompressorInputStream$$ExternalSyntheticLambda0 implements ByteUtils.ByteSupplier {
    public final /* synthetic */ AbstractLZ77CompressorInputStream f$0;

    public /* synthetic */ AbstractLZ77CompressorInputStream$$ExternalSyntheticLambda0(AbstractLZ77CompressorInputStream abstractLZ77CompressorInputStream) {
        this.f$0 = abstractLZ77CompressorInputStream;
    }

    public final int getAsByte() {
        return this.f$0.readOneByte();
    }
}
