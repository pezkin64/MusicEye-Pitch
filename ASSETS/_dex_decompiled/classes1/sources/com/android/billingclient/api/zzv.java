package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzv implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ AcknowledgePurchaseResponseListener zzb;

    public /* synthetic */ zzv(BillingClientImpl billingClientImpl, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = acknowledgePurchaseResponseListener;
    }

    public final void run() {
        this.zza.zzR(this.zzb);
    }
}
