package com.android.billingclient.api;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.gms.internal.play_billing.zzai;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzcd;
import com.google.android.gms.internal.play_billing.zzga;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzn extends BroadcastReceiver {
    final /* synthetic */ zzo zza;
    private boolean zzb;
    private final boolean zzc;

    zzn(zzo zzo, boolean z) {
        this.zza = zzo;
        this.zzc = z;
    }

    private final void zzd(Bundle bundle, BillingResult billingResult, int i) {
        if (bundle.getByteArray("FAILURE_LOGGING_PAYLOAD") != null) {
            try {
                this.zza.zze.zza(zzga.zzA(bundle.getByteArray("FAILURE_LOGGING_PAYLOAD"), zzcd.zza()));
            } catch (Throwable unused) {
                zzb.zzk("BillingBroadcastManager", "Failed parsing Api failure.");
            }
        } else {
            this.zza.zze.zza(zzcb.zza(23, i, billingResult));
        }
    }

    public final void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            zzb.zzk("BillingBroadcastManager", "Bundle is null.");
            this.zza.zze.zza(zzcb.zza(11, 1, zzce.zzj));
            zzo zzo = this.zza;
            if (zzo.zzb != null) {
                zzo.zzb.onPurchasesUpdated(zzce.zzj, (List<Purchase>) null);
                return;
            }
            return;
        }
        BillingResult zze = zzb.zze(intent, "BillingBroadcastManager");
        String action = intent.getAction();
        int i = true != Objects.equals(extras.getString("INTENT_SOURCE"), "LAUNCH_BILLING_FLOW") ? 1 : 2;
        if (action.equals("com.android.vending.billing.PURCHASES_UPDATED") || action.equals("com.android.vending.billing.LOCAL_BROADCAST_PURCHASES_UPDATED")) {
            List zzi = zzb.zzi(extras);
            if (zze.getResponseCode() == 0) {
                this.zza.zze.zzc(zzcb.zzc(i));
            } else {
                zzd(extras, zze, i);
            }
            this.zza.zzb.onPurchasesUpdated(zze, zzi);
        } else if (!action.equals("com.android.vending.billing.ALTERNATIVE_BILLING")) {
        } else {
            if (zze.getResponseCode() != 0) {
                zzd(extras, zze, i);
                this.zza.zzb.onPurchasesUpdated(zze, zzai.zzk());
                return;
            }
            zzo zzo2 = this.zza;
            if (zzo2.zzc == null && zzo2.zzd == null) {
                zzb.zzk("BillingBroadcastManager", "AlternativeBillingListener and UserChoiceBillingListener is null.");
                this.zza.zze.zza(zzcb.zza(77, i, zzce.zzj));
                this.zza.zzb.onPurchasesUpdated(zzce.zzj, zzai.zzk());
                return;
            }
            String string = extras.getString("ALTERNATIVE_BILLING_USER_CHOICE_DATA");
            if (string != null) {
                try {
                    if (this.zza.zzd != null) {
                        this.zza.zzd.userSelectedAlternativeBilling(new UserChoiceDetails(string));
                    } else {
                        JSONArray optJSONArray = new JSONObject(string).optJSONArray("products");
                        ArrayList arrayList = new ArrayList();
                        if (optJSONArray != null) {
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                                if (optJSONObject != null) {
                                    arrayList.add(new zze(optJSONObject, (zzd) null));
                                }
                            }
                        }
                        this.zza.zzc.zza();
                    }
                    this.zza.zze.zzc(zzcb.zzc(i));
                } catch (JSONException unused) {
                    zzb.zzk("BillingBroadcastManager", String.format("Error when parsing invalid user choice data: [%s]", new Object[]{string}));
                    this.zza.zze.zza(zzcb.zza(17, i, zzce.zzj));
                    this.zza.zzb.onPurchasesUpdated(zzce.zzj, zzai.zzk());
                }
            } else {
                zzb.zzk("BillingBroadcastManager", "Couldn't find alternative billing user choice data in bundle.");
                this.zza.zze.zza(zzcb.zza(16, i, zzce.zzj));
                this.zza.zzb.onPurchasesUpdated(zzce.zzj, zzai.zzk());
            }
        }
    }

    public final synchronized void zza(Context context, IntentFilter intentFilter) {
        if (!this.zzb) {
            if (Build.VERSION.SDK_INT >= 33) {
                Intent unused = context.registerReceiver(this, intentFilter, true != this.zzc ? 4 : 2);
            } else {
                context.registerReceiver(this, intentFilter);
            }
            this.zzb = true;
        }
    }

    public final synchronized void zzb(Context context, IntentFilter intentFilter, String str) {
        zzn zzn;
        try {
            if (!this.zzb) {
                if (Build.VERSION.SDK_INT >= 33) {
                    zzn = this;
                    try {
                        Intent unused = context.registerReceiver(zzn, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", (Handler) null, true != this.zzc ? 4 : 2);
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } else {
                    zzn = this;
                    context.registerReceiver(this, intentFilter, "com.google.android.finsky.permission.PLAY_BILLING_LIBRARY_BROADCAST", (Handler) null);
                }
                zzn.zzb = true;
            }
        } catch (Throwable th2) {
            th = th2;
            throw th;
        }
    }

    public final synchronized void zzc(Context context) {
        if (this.zzb) {
            context.unregisterReceiver(this);
            this.zzb = false;
            return;
        }
        zzb.zzk("BillingBroadcastManager", "Receiver is not registered.");
    }
}
