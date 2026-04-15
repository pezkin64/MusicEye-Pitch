package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzam implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ PurchaseHistoryResponseListener zzb;

    public /* synthetic */ zzam(BillingClientImpl billingClientImpl, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = purchaseHistoryResponseListener;
    }

    public final void run() {
        this.zza.zzaa(this.zzb);
    }
}
