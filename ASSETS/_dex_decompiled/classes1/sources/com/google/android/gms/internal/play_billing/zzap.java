package com.google.android.gms.internal.play_billing;

import java.util.AbstractMap;
import java.util.Objects;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzap extends zzai {
    final /* synthetic */ zzaq zza;

    zzap(zzaq zzaq) {
        this.zza = zzaq;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        zzaa.zza(i, this.zza.zzc, "index");
        int i2 = i + i;
        return new AbstractMap.SimpleImmutableEntry(Objects.requireNonNull(this.zza.zzb[i2]), Objects.requireNonNull(this.zza.zzb[i2 + 1]));
    }

    public final int size() {
        return this.zza.zzc;
    }

    public final boolean zzf() {
        return true;
    }
}
