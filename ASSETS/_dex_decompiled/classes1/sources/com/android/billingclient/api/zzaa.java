package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzaa implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ AlternativeBillingOnlyReportingDetailsListener zzb;

    public /* synthetic */ zzaa(BillingClientImpl billingClientImpl, AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        this.zza = billingClientImpl;
        this.zzb = alternativeBillingOnlyReportingDetailsListener;
    }

    public final void run() {
        this.zza.zzU(this.zzb);
    }
}
