package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzar implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ExternalOfferAvailabilityListener zzb;

    public /* synthetic */ zzar(BillingClientImpl billingClientImpl, ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        this.zza = billingClientImpl;
        this.zzb = externalOfferAvailabilityListener;
    }

    public final void run() {
        this.zza.zzY(this.zzb);
    }
}
