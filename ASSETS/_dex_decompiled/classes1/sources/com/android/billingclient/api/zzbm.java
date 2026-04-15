package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ResultReceiver;
import com.google.android.gms.internal.play_billing.zzab;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzk;
import java.lang.ref.WeakReference;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbm extends zzk {
    final WeakReference zza;
    final ResultReceiver zzb;

    /* synthetic */ zzbm(WeakReference weakReference, ResultReceiver resultReceiver, zzbl zzbl) {
        this.zza = weakReference;
        this.zzb = resultReceiver;
    }

    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.send(6, (Bundle) null);
        } else if (!bundle.containsKey("RESPONSE_CODE")) {
            zzb.zzk("BillingClient", "Response bundle doesn't contain a response code");
            this.zzb.send(6, bundle);
        } else {
            int zzb2 = zzb.zzb(bundle, "BillingClient");
            if (zzb2 != 0) {
                zzb.zzk("BillingClient", "Unable to launch intent for external offer dialog" + zzb2);
                this.zzb.send(zzb2, bundle);
                return;
            }
            PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable("EXTERNAL_PAYMENT_DIALOG_INTENT");
            if (pendingIntent == null) {
                zzb.zzk("BillingClient", "The PendingIntent for the external offer dialog is null");
                Bundle bundle2 = new Bundle();
                bundle2.putInt("RESPONSE_CODE", 6);
                bundle2.putString("DEBUG_MESSAGE", "An internal error occurred.");
                this.zzb.send(6, bundle2);
                return;
            }
            try {
                Activity activity = (Activity) this.zza.get();
                Intent intent = new Intent(activity, ProxyBillingActivityV2.class);
                intent.putExtra("external_payment_dialog_result_receiver", this.zzb);
                intent.putExtra("external_payment_dialog_pending_intent", pendingIntent);
                activity.startActivity(intent);
            } catch (RuntimeException e) {
                zzb.zzl("BillingClient", "Runtime error while launching intent for the external offer dialog.", e);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("RESPONSE_CODE", 6);
                bundle3.putString("DEBUG_MESSAGE", "An internal error occurred.");
                bundle3.putInt("INTERNAL_LOG_ERROR_REASON", 98);
                bundle3.putString("INTERNAL_LOG_ERROR_ADDITIONAL_DETAILS", String.format("%s: %s", new Object[]{e.getClass().getName(), zzab.zzb(e.getMessage())}));
                this.zzb.send(6, bundle3);
            }
        }
    }
}
