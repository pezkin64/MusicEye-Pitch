package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzan implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ QueryProductDetailsParams zzb;
    public final /* synthetic */ ProductDetailsResponseListener zzc;

    public /* synthetic */ zzan(BillingClientImpl billingClientImpl, QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) {
        this.zza = billingClientImpl;
        this.zzb = queryProductDetailsParams;
        this.zzc = productDetailsResponseListener;
    }

    public final Object call() {
        this.zza.zzn(this.zzb, this.zzc);
        return null;
    }
}
