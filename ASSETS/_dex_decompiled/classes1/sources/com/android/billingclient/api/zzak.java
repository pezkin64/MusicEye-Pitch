package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzak implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ExternalOfferReportingDetailsListener zzb;

    public /* synthetic */ zzak(BillingClientImpl billingClientImpl, ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        this.zza = billingClientImpl;
        this.zzb = externalOfferReportingDetailsListener;
    }

    public final void run() {
        this.zza.zzV(this.zzb);
    }
}
