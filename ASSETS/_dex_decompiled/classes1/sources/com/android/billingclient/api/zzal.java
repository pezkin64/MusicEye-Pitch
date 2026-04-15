package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzal implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ ExternalOfferInformationDialogListener zzb;

    public /* synthetic */ zzal(BillingClientImpl billingClientImpl, ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        this.zza = billingClientImpl;
        this.zzb = externalOfferInformationDialogListener;
    }

    public final void run() {
        this.zza.zzae(this.zzb);
    }
}
