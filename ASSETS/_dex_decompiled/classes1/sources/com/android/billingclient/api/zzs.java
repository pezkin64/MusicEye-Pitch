package com.android.billingclient.api;

import android.app.Activity;
import android.os.ResultReceiver;
import java.util.concurrent.Callable;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final /* synthetic */ class zzs implements Callable {
    public final /* synthetic */ BillingClientImpl zza;
    public final /* synthetic */ Activity zzb;
    public final /* synthetic */ ResultReceiver zzc;
    public final /* synthetic */ AlternativeBillingOnlyInformationDialogListener zzd;

    public /* synthetic */ zzs(BillingClientImpl billingClientImpl, Activity activity, ResultReceiver resultReceiver, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        this.zza = billingClientImpl;
        this.zzb = activity;
        this.zzc = resultReceiver;
        this.zzd = alternativeBillingOnlyInformationDialogListener;
    }

    public final Object call() {
        this.zza.zzu(this.zzb, this.zzc, this.zzd);
        return null;
    }
}
