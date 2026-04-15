package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzo;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbq extends zzo {
    final ExternalOfferAvailabilityListener zza;
    final zzcc zzb;
    final int zzc;

    /* synthetic */ zzbq(ExternalOfferAvailabilityListener externalOfferAvailabilityListener, zzcc zzcc, int i, zzbp zzbp) {
        this.zza = externalOfferAvailabilityListener;
        this.zzb = zzcc;
        this.zzc = i;
    }

    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zzb(zzcb.zza(92, 23, zzce.zzj), this.zzc);
            this.zza.onExternalOfferAvailabilityResponse(zzce.zzj);
            return;
        }
        int zzb2 = zzb.zzb(bundle, "BillingClient");
        BillingResult zza2 = zzce.zza(zzb2, zzb.zzg(bundle, "BillingClient"));
        if (zzb2 != 0) {
            zzb.zzk("BillingClient", "isExternalOfferAvailableAsync() failed. Response code: " + zzb2);
            this.zzb.zzb(zzcb.zza(23, 23, zza2), this.zzc);
        }
        this.zza.onExternalOfferAvailabilityResponse(zza2);
    }
}
