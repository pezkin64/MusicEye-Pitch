package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzm;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbo extends zzm {
    final AlternativeBillingOnlyAvailabilityListener zza;
    final zzcc zzb;
    final int zzc;

    /* synthetic */ zzbo(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener, zzcc zzcc, int i, zzbn zzbn) {
        this.zza = alternativeBillingOnlyAvailabilityListener;
        this.zzb = zzcc;
        this.zzc = i;
    }

    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zzb(zzcb.zza(67, 14, zzce.zzj), this.zzc);
            this.zza.onAlternativeBillingOnlyAvailabilityResponse(zzce.zzj);
            return;
        }
        int zzb2 = zzb.zzb(bundle, "BillingClient");
        BillingResult zza2 = zzce.zza(zzb2, zzb.zzg(bundle, "BillingClient"));
        if (zzb2 != 0) {
            zzb.zzk("BillingClient", "isAlternativeBillingOnlyAvailableAsync() failed. Response code: " + zzb2);
            this.zzb.zzb(zzcb.zza(23, 14, zza2), this.zzc);
        }
        this.zza.onAlternativeBillingOnlyAvailabilityResponse(zza2);
    }
}
