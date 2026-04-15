package com.android.billingclient.api;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.google.android.gms.internal.play_billing.zzb;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzaw extends ResultReceiver {
    final /* synthetic */ InAppMessageResponseListener zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzaw(BillingClientImpl billingClientImpl, Handler handler, InAppMessageResponseListener inAppMessageResponseListener) {
        super(handler);
        this.zza = inAppMessageResponseListener;
    }

    public final void onReceiveResult(int i, Bundle bundle) {
        this.zza.onInAppMessageResponse(zzb.zzf(bundle, "BillingClient"));
    }
}
