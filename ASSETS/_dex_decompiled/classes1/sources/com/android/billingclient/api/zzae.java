package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzae implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ PurchasesResponseListener zzb;

    public /* synthetic */ zzae(BillingClientImpl billingClientImpl, PurchasesResponseListener purchasesResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = purchasesResponseListener;
    }

    public final void run() {
        this.zza.zzab(this.zzb);
    }
}
