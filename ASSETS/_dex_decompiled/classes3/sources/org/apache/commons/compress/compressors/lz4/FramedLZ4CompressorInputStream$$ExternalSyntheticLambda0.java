package org.apache.commons.compress.compressors.lz4;

import org.apache.commons.compress.utils.ByteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FramedLZ4CompressorInputStream$$ExternalSyntheticLambda0 implements ByteUtils.ByteSupplier {
    public final /* synthetic */ FramedLZ4CompressorInputStream f$0;

    public /* synthetic */ FramedLZ4CompressorInputStream$$ExternalSyntheticLambda0(FramedLZ4CompressorInputStream framedLZ4CompressorInputStream) {
        this.f$0 = framedLZ4CompressorInputStream;
    }

    public final int getAsByte() {
        return this.f$0.readOneByte();
    }
}
