package com.android.billingclient.api;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zze;
import org.json.JSONException;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbg extends zze {
    final ExternalOfferReportingDetailsListener zza;
    final zzcc zzb;
    final int zzc;

    /* synthetic */ zzbg(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener, zzcc zzcc, int i, zzbf zzbf) {
        this.zza = externalOfferReportingDetailsListener;
        this.zzb = zzcc;
        this.zzc = i;
    }

    public final void zza(Bundle bundle) throws RemoteException {
        if (bundle == null) {
            this.zzb.zzb(zzcb.zza(95, 24, zzce.zzj), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(zzce.zzj, (ExternalOfferReportingDetails) null);
            return;
        }
        int zzb2 = zzb.zzb(bundle, "BillingClient");
        BillingResult zza2 = zzce.zza(zzb2, zzb.zzg(bundle, "BillingClient"));
        if (zzb2 != 0) {
            zzb.zzk("BillingClient", "createExternalOfferReportingDetailsAsync() failed. Response code: " + zzb2);
            this.zzb.zzb(zzcb.zza(23, 24, zza2), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(zza2, (ExternalOfferReportingDetails) null);
            return;
        }
        try {
            this.zza.onExternalOfferReportingDetailsResponse(zza2, new ExternalOfferReportingDetails(bundle.getString("CREATE_EXTERNAL_PAYMENT_REPORTING_DETAILS")));
        } catch (JSONException e) {
            zzb.zzl("BillingClient", "Error when parsing invalid external offer reporting details. \n Exception: ", e);
            this.zzb.zzb(zzcb.zza(104, 24, zzce.zzj), this.zzc);
            this.zza.onExternalOfferReportingDetailsResponse(zzce.zzj, (ExternalOfferReportingDetails) null);
        }
    }
}
