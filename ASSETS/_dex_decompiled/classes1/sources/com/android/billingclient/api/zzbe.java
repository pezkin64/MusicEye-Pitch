package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzc;
import org.json.JSONException;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbe extends zzc {
    final AlternativeBillingOnlyReportingDetailsListener zza;
    final zzcc zzb;
    final int zzc;

    /* synthetic */ zzbe(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener, zzcc zzcc, int i, zzbd zzbd) {
        this.zza = alternativeBillingOnlyReportingDetailsListener;
        this.zzb = zzcc;
        this.zzc = i;
    }

    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zzb(zzcb.zza(71, 15, zzce.zzj), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(zzce.zzj, (AlternativeBillingOnlyReportingDetails) null);
            return;
        }
        int zzb2 = zzb.zzb(bundle, "BillingClient");
        BillingResult zza2 = zzce.zza(zzb2, zzb.zzg(bundle, "BillingClient"));
        if (zzb2 != 0) {
            zzb.zzk("BillingClient", "createAlternativeBillingOnlyReportingDetailsAsync() failed. Response code: " + zzb2);
            this.zzb.zzb(zzcb.zza(23, 15, zza2), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(zza2, (AlternativeBillingOnlyReportingDetails) null);
            return;
        }
        try {
            this.zza.onAlternativeBillingOnlyTokenResponse(zza2, new AlternativeBillingOnlyReportingDetails(bundle.getString("CREATE_ALTERNATIVE_BILLING_ONLY_REPORTING_DETAILS")));
        } catch (JSONException e) {
            zzb.zzl("BillingClient", "Error when parsing invalid alternative billing only reporting details. \n Exception: ", e);
            this.zzb.zzb(zzcb.zza(72, 15, zzce.zzj), this.zzc);
            this.zza.onAlternativeBillingOnlyTokenResponse(zzce.zzj, (AlternativeBillingOnlyReportingDetails) null);
        }
    }
}
