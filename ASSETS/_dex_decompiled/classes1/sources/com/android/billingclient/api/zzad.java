package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzad implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ SkuDetailsResponseListener zzb;

    public /* synthetic */ zzad(BillingClientImpl billingClientImpl, SkuDetailsResponseListener skuDetailsResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = skuDetailsResponseListener;
    }

    public final void run() {
        this.zza.zzac(this.zzb);
    }
}
