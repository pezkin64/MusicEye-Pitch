package com.android.billingclient.api;

import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzaf implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ AlternativeBillingOnlyAvailabilityListener zzb;

    public /* synthetic */ zzaf(BillingClientImpl billingClientImpl, AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        this.zza = billingClientImpl;
        this.zzb = alternativeBillingOnlyAvailabilityListener;
    }

    public final Object call() {
        this.zza.zzs(this.zzb);
        return null;
    }
}
