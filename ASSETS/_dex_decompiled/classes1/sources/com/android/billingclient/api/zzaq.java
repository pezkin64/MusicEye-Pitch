package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzaq implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ExternalOfferAvailabilityListener zzb;

    public /* synthetic */ zzaq(BillingClientImpl billingClientImpl, ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        this.zza = billingClientImpl;
        this.zzb = externalOfferAvailabilityListener;
    }

    public final Object call() {
        this.zza.zzt(this.zzb);
        return null;
    }
}
