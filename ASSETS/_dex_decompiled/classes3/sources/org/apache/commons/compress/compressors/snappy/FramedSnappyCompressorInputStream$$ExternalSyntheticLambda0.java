package org.apache.commons.compress.compressors.snappy;

import org.apache.commons.compress.utils.ByteUtils;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class FramedSnappyCompressorInputStream$$ExternalSyntheticLambda0 implements ByteUtils.ByteSupplier {
    public final /* synthetic */ FramedSnappyCompressorInputStream f$0;

    public /* synthetic */ FramedSnappyCompressorInputStream$$ExternalSyntheticLambda0(FramedSnappyCompressorInputStream framedSnappyCompressorInputStream) {
        this.f$0 = framedSnappyCompressorInputStream;
    }

    public final int getAsByte() {
        return this.f$0.readOneByte();
    }
}
