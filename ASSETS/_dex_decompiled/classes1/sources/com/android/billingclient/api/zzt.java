package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzt implements Runnable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ AlternativeBillingOnlyInformationDialogListener zzb;

    public /* synthetic */ zzt(BillingClientImpl billingClientImpl, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        this.zza = billingClientImpl;
        this.zzb = alternativeBillingOnlyInformationDialogListener;
    }

    public final void run() {
        this.zza.zzad(this.zzb);
    }
}
