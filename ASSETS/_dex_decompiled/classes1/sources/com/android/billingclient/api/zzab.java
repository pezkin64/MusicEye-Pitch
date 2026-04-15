package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzab implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ExternalOfferReportingDetailsListener zzb;

    public /* synthetic */ zzab(BillingClientImpl billingClientImpl, ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        this.zza = billingClientImpl;
        this.zzb = externalOfferReportingDetailsListener;
    }

    public final Object call() {
        this.zza.zzr(this.zzb);
        return null;
    }
}
