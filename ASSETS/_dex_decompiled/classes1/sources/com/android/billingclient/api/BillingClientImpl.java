package com.android.billingclient.api;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.BundleCompat;
import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.google.android.gms.internal.play_billing.zzaa;
import com.google.android.gms.internal.play_billing.zzab;
import com.google.android.gms.internal.play_billing.zzai;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzga;
import com.google.android.gms.internal.play_billing.zzge;
import com.google.android.gms.internal.play_billing.zzgt;
import com.google.android.gms.internal.play_billing.zzgu;
import com.google.android.gms.internal.play_billing.zzs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.json.JSONException;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
class BillingClientImpl extends BillingClient {
    private boolean zzA;
    private ExecutorService zzB;
    /* access modifiers changed from: private */
    public volatile int zza;
    private final String zzb;
    private final Handler zzc;
    /* access modifiers changed from: private */
    public volatile zzo zzd;
    /* access modifiers changed from: private */
    public Context zze;
    /* access modifiers changed from: private */
    public zzcc zzf;
    /* access modifiers changed from: private */
    public volatile zzs zzg;
    private volatile zzbc zzh;
    /* access modifiers changed from: private */
    public boolean zzi;
    /* access modifiers changed from: private */
    public boolean zzj;
    /* access modifiers changed from: private */
    public int zzk;
    /* access modifiers changed from: private */
    public boolean zzl;
    /* access modifiers changed from: private */
    public boolean zzm;
    /* access modifiers changed from: private */
    public boolean zzn;
    /* access modifiers changed from: private */
    public boolean zzo;
    /* access modifiers changed from: private */
    public boolean zzp;
    /* access modifiers changed from: private */
    public boolean zzq;
    /* access modifiers changed from: private */
    public boolean zzr;
    /* access modifiers changed from: private */
    public boolean zzs;
    /* access modifiers changed from: private */
    public boolean zzt;
    /* access modifiers changed from: private */
    public boolean zzu;
    /* access modifiers changed from: private */
    public boolean zzv;
    /* access modifiers changed from: private */
    public boolean zzw;
    /* access modifiers changed from: private */
    public boolean zzx;
    /* access modifiers changed from: private */
    public boolean zzy;
    private PendingPurchasesParams zzz;

    private BillingClientImpl(Activity activity, PendingPurchasesParams pendingPurchasesParams, String str) {
        this(activity.getApplicationContext(), pendingPurchasesParams, new zzbu(), str, (String) null, (UserChoiceBillingListener) null, (zzcc) null, (ExecutorService) null);
    }

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, PendingPurchasesParams pendingPurchasesParams, zzc zzc2, String str, zzcc zzcc) {
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(str);
        zzy2.zzm(this.zze.getPackageName());
        if (zzcc != null) {
            this.zzf = zzcc;
        } else {
            this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        }
        if (purchasesUpdatedListener == null) {
            zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        zzc zzc3 = zzc2;
        this.zzd = new zzo(this.zze, purchasesUpdatedListener, (zzck) null, zzc3, (UserChoiceBillingListener) null, this.zzf);
        this.zzz = pendingPurchasesParams;
        this.zzA = zzc3 != null;
        this.zze.getPackageName();
    }

    private int launchBillingFlowCpp(Activity activity, BillingFlowParams billingFlowParams) {
        return launchBillingFlow(activity, billingFlowParams).getResponseCode();
    }

    private void startConnection(long j) {
        zzbu zzbu = new zzbu(j);
        if (isReady()) {
            zzb.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            zzaq(zzcb.zzc(6));
            zzbu.onBillingSetupFinished(zzce.zzl);
            return;
        }
        int i = 1;
        if (this.zza == 1) {
            zzb.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            zzap(zzcb.zza(37, 6, zzce.zzd));
            zzbu.onBillingSetupFinished(zzce.zzd);
        } else if (this.zza == 3) {
            zzb.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            zzap(zzcb.zza(38, 6, zzce.zzm));
            zzbu.onBillingSetupFinished(zzce.zzm);
        } else {
            this.zza = 1;
            zzb.zzj("BillingClient", "Starting in-app billing setup.");
            this.zzh = new zzbc(this, zzbu, (zzbb) null);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            List<ResolveInfo> queryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                i = 41;
            } else {
                ResolveInfo resolveInfo = queryIntentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    String str2 = resolveInfo.serviceInfo.name;
                    if (!"com.android.vending".equals(str) || str2 == null) {
                        zzb.zzk("BillingClient", "The device doesn't have valid Play Store.");
                        i = 40;
                    } else {
                        ComponentName componentName = new ComponentName(str, str2);
                        Intent intent2 = new Intent(intent);
                        intent2.setComponent(componentName);
                        intent2.putExtra("playBillingLibraryVersion", this.zzb);
                        if (this.zze.bindService(intent2, this.zzh, 1)) {
                            zzb.zzj("BillingClient", "Service was bonded successfully.");
                            return;
                        } else {
                            zzb.zzk("BillingClient", "Connection to Billing service is blocked.");
                            i = 39;
                        }
                    }
                }
            }
            this.zza = 0;
            zzb.zzj("BillingClient", "Billing service unavailable on device.");
            zzap(zzcb.zza(i, 6, zzce.zzc));
            zzbu.onBillingSetupFinished(zzce.zzc);
        }
    }

    static /* bridge */ /* synthetic */ zzcz zzag(BillingClientImpl billingClientImpl, String str, int i) {
        Bundle bundle;
        BillingClientImpl billingClientImpl2 = billingClientImpl;
        zzb.zzj("BillingClient", "Querying owned items, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        Bundle zzc2 = zzb.zzc(billingClientImpl2.zzn, billingClientImpl2.zzv, billingClientImpl2.zzz.isEnabledForOneTimeProducts(), billingClientImpl2.zzz.isEnabledForPrepaidPlans(), billingClientImpl2.zzb);
        List list = null;
        String str2 = null;
        while (true) {
            try {
                if (billingClientImpl2.zzn) {
                    bundle = billingClientImpl2.zzg.zzj(true != billingClientImpl2.zzv ? 9 : 19, billingClientImpl2.zze.getPackageName(), str, str2, zzc2);
                    String str3 = str;
                } else {
                    bundle = billingClientImpl2.zzg.zzi(3, billingClientImpl2.zze.getPackageName(), str, str2);
                }
                zzda zza2 = zzdb.zza(bundle, "BillingClient", "getPurchase()");
                BillingResult zza3 = zza2.zza();
                if (zza3 != zzce.zzl) {
                    billingClientImpl2.zzap(zzcb.zza(zza2.zzb(), 9, zza3));
                    return new zzcz(zza3, list);
                }
                ArrayList<String> stringArrayList = bundle.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = bundle.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i2 = 0;
                boolean z = false;
                while (i2 < stringArrayList2.size()) {
                    String str4 = stringArrayList2.get(i2);
                    String str5 = stringArrayList3.get(i2);
                    zzb.zzj("BillingClient", "Sku is owned: ".concat(String.valueOf(stringArrayList.get(i2))));
                    try {
                        Purchase purchase = new Purchase(str4, str5);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            zzb.zzk("BillingClient", "BUG: empty/null token!");
                            z = true;
                        }
                        arrayList.add(purchase);
                        i2++;
                    } catch (JSONException e) {
                        zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        billingClientImpl2.zzap(zzcb.zza(51, 9, zzce.zzj));
                        return new zzcz(zzce.zzj, (List) null);
                    }
                }
                if (z) {
                    billingClientImpl2.zzap(zzcb.zza(26, 9, zzce.zzj));
                }
                str2 = bundle.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(str2)));
                if (TextUtils.isEmpty(str2)) {
                    return new zzcz(zzce.zzl, arrayList);
                }
                list = null;
            } catch (Exception e2) {
                billingClientImpl2.zzap(zzcb.zza(52, 9, zzce.zzm));
                zzb.zzl("BillingClient", "Got exception trying to get purchasesm try to reconnect", e2);
                return new zzcz(zzce.zzm, (List) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public final Handler zzaj() {
        return Looper.myLooper() == null ? this.zzc : new Handler(Looper.myLooper());
    }

    private final BillingResult zzak(BillingResult billingResult) {
        if (Thread.interrupted()) {
            return billingResult;
        }
        this.zzc.post(new zzq(this, billingResult));
        return billingResult;
    }

    /* access modifiers changed from: private */
    public final BillingResult zzal() {
        if (this.zza == 0 || this.zza == 3) {
            return zzce.zzm;
        }
        return zzce.zzj;
    }

    private final String zzam(QueryProductDetailsParams queryProductDetailsParams) {
        if (!TextUtils.isEmpty((CharSequence) null)) {
            return null;
        }
        return this.zze.getPackageName();
    }

    private static String zzan() {
        try {
            return (String) Class.forName("com.android.billingclient.ktx.BuildConfig").getField("VERSION_NAME").get((Object) null);
        } catch (Exception unused) {
            return BuildConfig.VERSION_NAME;
        }
    }

    /* access modifiers changed from: private */
    public final Future zzao(Callable callable, long j, Runnable runnable, Handler handler) {
        if (this.zzB == null) {
            this.zzB = Executors.newFixedThreadPool(zzb.zza, new zzat(this));
        }
        try {
            Future submit = this.zzB.submit(callable);
            handler.postDelayed(new zzy(submit, runnable), (long) (((double) j) * 0.95d));
            return submit;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Async task throws exception!", e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzap(zzga zzga) {
        this.zzf.zzb(zzga, this.zzk);
    }

    /* access modifiers changed from: private */
    public final void zzaq(zzge zzge) {
        this.zzf.zzd(zzge, this.zzk);
    }

    private final void zzar(String str, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 11, zzce.zzm));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzce.zzm, (List<PurchaseHistoryRecord>) null);
            return;
        }
        if (zzao(new zzav(this, str, purchaseHistoryResponseListener), 30000, new zzam(this, purchaseHistoryResponseListener), zzaj()) == null) {
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 11, zzal));
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzal, (List<PurchaseHistoryRecord>) null);
        }
    }

    private final void zzas(String str, PurchasesResponseListener purchasesResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 9, zzce.zzm));
            purchasesResponseListener.onQueryPurchasesResponse(zzce.zzm, zzai.zzk());
        } else if (TextUtils.isEmpty(str)) {
            zzb.zzk("BillingClient", "Please provide a valid product type.");
            zzap(zzcb.zza(50, 9, zzce.zzg));
            purchasesResponseListener.onQueryPurchasesResponse(zzce.zzg, zzai.zzk());
        } else {
            if (zzao(new zzau(this, str, purchasesResponseListener), 30000, new zzae(this, purchasesResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 9, zzal));
                purchasesResponseListener.onQueryPurchasesResponse(zzal, zzai.zzk());
            }
        }
    }

    private final boolean zzat() {
        return this.zzv && this.zzz.isEnabledForPrepaidPlans();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: com.google.android.gms.internal.play_billing.zzge} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: com.google.android.gms.internal.play_billing.zzga} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzau(com.android.billingclient.api.BillingResult r8, int r9, int r10) {
        /*
            r7 = this;
            int r0 = r8.getResponseCode()
            r1 = 0
            java.lang.String r2 = "Unable to create logging payload"
            java.lang.String r3 = "BillingLogger"
            r4 = 5
            if (r0 == 0) goto L_0x004d
            int r0 = com.android.billingclient.api.zzcb.zza
            com.google.android.gms.internal.play_billing.zzfz r0 = com.google.android.gms.internal.play_billing.zzga.zzy()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzgg r5 = com.google.android.gms.internal.play_billing.zzgk.zzy()     // Catch:{ Exception -> 0x0045 }
            int r6 = r8.getResponseCode()     // Catch:{ Exception -> 0x0045 }
            r5.zzn(r6)     // Catch:{ Exception -> 0x0045 }
            java.lang.String r8 = r8.getDebugMessage()     // Catch:{ Exception -> 0x0045 }
            r5.zzm(r8)     // Catch:{ Exception -> 0x0045 }
            r5.zzo(r9)     // Catch:{ Exception -> 0x0045 }
            r0.zzl(r5)     // Catch:{ Exception -> 0x0045 }
            r0.zzn(r4)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzgz r8 = com.google.android.gms.internal.play_billing.zzhb.zzy()     // Catch:{ Exception -> 0x0045 }
            r8.zzl(r10)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzcs r8 = r8.zzf()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzhb r8 = (com.google.android.gms.internal.play_billing.zzhb) r8     // Catch:{ Exception -> 0x0045 }
            r0.zzm(r8)     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzcs r8 = r0.zzf()     // Catch:{ Exception -> 0x0045 }
            com.google.android.gms.internal.play_billing.zzga r8 = (com.google.android.gms.internal.play_billing.zzga) r8     // Catch:{ Exception -> 0x0045 }
            r1 = r8
            goto L_0x0049
        L_0x0045:
            r8 = move-exception
            com.google.android.gms.internal.play_billing.zzb.zzl(r3, r2, r8)
        L_0x0049:
            r7.zzap(r1)
            return
        L_0x004d:
            int r8 = com.android.billingclient.api.zzcb.zza
            com.google.android.gms.internal.play_billing.zzgd r8 = com.google.android.gms.internal.play_billing.zzge.zzy()     // Catch:{ Exception -> 0x006e }
            r8.zzm(r4)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzgz r9 = com.google.android.gms.internal.play_billing.zzhb.zzy()     // Catch:{ Exception -> 0x006e }
            r9.zzl(r10)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzcs r9 = r9.zzf()     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzhb r9 = (com.google.android.gms.internal.play_billing.zzhb) r9     // Catch:{ Exception -> 0x006e }
            r8.zzl(r9)     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzcs r8 = r8.zzf()     // Catch:{ Exception -> 0x006e }
            com.google.android.gms.internal.play_billing.zzge r8 = (com.google.android.gms.internal.play_billing.zzge) r8     // Catch:{ Exception -> 0x006e }
            r1 = r8
            goto L_0x0072
        L_0x006e:
            r8 = move-exception
            com.google.android.gms.internal.play_billing.zzb.zzl(r3, r2, r8)
        L_0x0072:
            r7.zzaq(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.zzau(com.android.billingclient.api.BillingResult, int, int):void");
    }

    static /* bridge */ /* synthetic */ zzbt zzg(BillingClientImpl billingClientImpl, String str) {
        BillingClientImpl billingClientImpl2 = billingClientImpl;
        zzb.zzj("BillingClient", "Querying purchase history, item type: ".concat(String.valueOf(str)));
        ArrayList arrayList = new ArrayList();
        Bundle zzc2 = zzb.zzc(billingClientImpl2.zzn, billingClientImpl2.zzv, billingClientImpl2.zzz.isEnabledForOneTimeProducts(), billingClientImpl2.zzz.isEnabledForPrepaidPlans(), billingClientImpl2.zzb);
        String str2 = null;
        while (billingClientImpl2.zzl) {
            try {
                Bundle zzh2 = billingClientImpl2.zzg.zzh(6, billingClientImpl2.zze.getPackageName(), str, str2, zzc2);
                zzda zza2 = zzdb.zza(zzh2, "BillingClient", "getPurchaseHistory()");
                BillingResult zza3 = zza2.zza();
                if (zza3 != zzce.zzl) {
                    billingClientImpl2.zzap(zzcb.zza(zza2.zzb(), 11, zza3));
                    return new zzbt(zza3, (List) null);
                }
                ArrayList<String> stringArrayList = zzh2.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
                ArrayList<String> stringArrayList2 = zzh2.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
                ArrayList<String> stringArrayList3 = zzh2.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
                int i = 0;
                boolean z = false;
                while (i < stringArrayList2.size()) {
                    String str3 = stringArrayList2.get(i);
                    String str4 = stringArrayList3.get(i);
                    zzb.zzj("BillingClient", "Purchase record found for sku : ".concat(String.valueOf(stringArrayList.get(i))));
                    try {
                        PurchaseHistoryRecord purchaseHistoryRecord = new PurchaseHistoryRecord(str3, str4);
                        if (TextUtils.isEmpty(purchaseHistoryRecord.getPurchaseToken())) {
                            zzb.zzk("BillingClient", "BUG: empty/null token!");
                            z = true;
                        }
                        arrayList.add(purchaseHistoryRecord);
                        i++;
                    } catch (JSONException e) {
                        zzb.zzl("BillingClient", "Got an exception trying to decode the purchase!", e);
                        billingClientImpl2.zzap(zzcb.zza(51, 11, zzce.zzj));
                        return new zzbt(zzce.zzj, (List) null);
                    }
                }
                if (z) {
                    billingClientImpl2.zzap(zzcb.zza(26, 11, zzce.zzj));
                }
                str2 = zzh2.getString("INAPP_CONTINUATION_TOKEN");
                zzb.zzj("BillingClient", "Continuation token: ".concat(String.valueOf(str2)));
                if (TextUtils.isEmpty(str2)) {
                    return new zzbt(zzce.zzl, arrayList);
                }
            } catch (RemoteException e2) {
                zzb.zzl("BillingClient", "Got exception trying to get purchase history, try to reconnect", e2);
                billingClientImpl2.zzap(zzcb.zza(59, 11, zzce.zzm));
                return new zzbt(zzce.zzm, (List) null);
            }
        }
        zzb.zzk("BillingClient", "getPurchaseHistory is not supported on current device");
        return new zzbt(zzce.zzq, (List) null);
    }

    public final void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 3, zzce.zzm));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zzm);
        } else if (TextUtils.isEmpty(acknowledgePurchaseParams.getPurchaseToken())) {
            zzb.zzk("BillingClient", "Please provide a valid purchase token.");
            zzap(zzcb.zza(26, 3, zzce.zzi));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zzi);
        } else if (!this.zzn) {
            zzap(zzcb.zza(27, 3, zzce.zzb));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zzb);
        } else {
            if (zzao(new zzu(this, acknowledgePurchaseParams, acknowledgePurchaseResponseListener), 30000, new zzv(this, acknowledgePurchaseResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 3, zzal));
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzal);
            }
        }
    }

    public final void consumeAsync(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 4, zzce.zzm));
            consumeResponseListener.onConsumeResponse(zzce.zzm, consumeParams.getPurchaseToken());
            return;
        }
        if (zzao(new zzah(this, consumeParams, consumeResponseListener), 30000, new zzai(this, consumeResponseListener, consumeParams), zzaj()) == null) {
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 4, zzal));
            consumeResponseListener.onConsumeResponse(zzal, consumeParams.getPurchaseToken());
        }
    }

    public void createAlternativeBillingOnlyReportingDetailsAsync(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 15, zzce.zzm));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzce.zzm, (AlternativeBillingOnlyReportingDetails) null);
        } else if (!this.zzx) {
            zzb.zzk("BillingClient", "Current client doesn't support alternative billing only.");
            zzap(zzcb.zza(66, 15, zzce.zzE));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzce.zzE, (AlternativeBillingOnlyReportingDetails) null);
        } else {
            if (zzao(new zzz(this, alternativeBillingOnlyReportingDetailsListener), 30000, new zzaa(this, alternativeBillingOnlyReportingDetailsListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 15, zzal));
                alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzal, (AlternativeBillingOnlyReportingDetails) null);
            }
        }
    }

    public void createExternalOfferReportingDetailsAsync(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 24, zzce.zzm));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzce.zzm, (ExternalOfferReportingDetails) null);
        } else if (!this.zzy) {
            zzb.zzk("BillingClient", "Current client doesn't support external offer.");
            zzap(zzcb.zza(103, 24, zzce.zzy));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzce.zzy, (ExternalOfferReportingDetails) null);
        } else {
            if (zzao(new zzab(this, externalOfferReportingDetailsListener), 30000, new zzak(this, externalOfferReportingDetailsListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 24, zzal));
                externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzal, (ExternalOfferReportingDetails) null);
            }
        }
    }

    public final void endConnection() {
        zzaq(zzcb.zzc(12));
        try {
            if (this.zzd != null) {
                this.zzd.zzf();
            }
            if (this.zzh != null) {
                this.zzh.zzc();
            }
            if (!(this.zzh == null || this.zzg == null)) {
                zzb.zzj("BillingClient", "Unbinding from service.");
                this.zze.unbindService(this.zzh);
                this.zzh = null;
            }
            this.zzg = null;
            ExecutorService executorService = this.zzB;
            if (executorService != null) {
                executorService.shutdownNow();
                this.zzB = null;
            }
        } catch (Exception e) {
            zzb.zzl("BillingClient", "There was an exception while ending connection!", e);
        } catch (Throwable th) {
            this.zza = 3;
            throw th;
        }
        this.zza = 3;
    }

    public void getBillingConfigAsync(GetBillingConfigParams getBillingConfigParams, BillingConfigResponseListener billingConfigResponseListener) {
        if (!isReady()) {
            zzb.zzk("BillingClient", "Service disconnected.");
            zzap(zzcb.zza(2, 13, zzce.zzm));
            billingConfigResponseListener.onBillingConfigResponse(zzce.zzm, (BillingConfig) null);
        } else if (!this.zzu) {
            zzb.zzk("BillingClient", "Current client doesn't support get billing config.");
            zzap(zzcb.zza(32, 13, zzce.zzA));
            billingConfigResponseListener.onBillingConfigResponse(zzce.zzA, (BillingConfig) null);
        } else {
            String str = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            if (zzao(new zzw(this, bundle, billingConfigResponseListener), 30000, new zzx(this, billingConfigResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 13, zzal));
                billingConfigResponseListener.onBillingConfigResponse(zzal, (BillingConfig) null);
            }
        }
    }

    public final int getConnectionState() {
        return this.zza;
    }

    public void isAlternativeBillingOnlyAvailableAsync(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 14, zzce.zzm));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzce.zzm);
        } else if (!this.zzx) {
            zzb.zzk("BillingClient", "Current client doesn't support alternative billing only.");
            zzap(zzcb.zza(66, 14, zzce.zzE));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzce.zzE);
        } else {
            if (zzao(new zzaf(this, alternativeBillingOnlyAvailabilityListener), 30000, new zzag(this, alternativeBillingOnlyAvailabilityListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 14, zzal));
                alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzal);
            }
        }
    }

    public void isExternalOfferAvailableAsync(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 23, zzce.zzm));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzce.zzm);
        } else if (!this.zzy) {
            zzb.zzk("BillingClient", "Current client doesn't support external offer.");
            zzap(zzcb.zza(103, 23, zzce.zzy));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzce.zzy);
        } else {
            if (zzao(new zzaq(this, externalOfferAvailabilityListener), 30000, new zzar(this, externalOfferAvailabilityListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 23, zzal));
                externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzal);
            }
        }
    }

    public final BillingResult isFeatureSupported(String str) {
        BillingResult billingResult;
        BillingResult billingResult2;
        BillingResult billingResult3;
        BillingResult billingResult4;
        BillingResult billingResult5;
        BillingResult billingResult6;
        BillingResult billingResult7;
        BillingResult billingResult8;
        BillingResult billingResult9;
        BillingResult billingResult10;
        BillingResult billingResult11;
        BillingResult billingResult12;
        if (!isReady()) {
            BillingResult billingResult13 = zzce.zzm;
            if (billingResult13.getResponseCode() != 0) {
                zzap(zzcb.zza(2, 5, billingResult13));
            } else {
                zzaq(zzcb.zzc(5));
            }
            return zzce.zzm;
        }
        int i = zzce.zzG;
        switch (str.hashCode()) {
            case -422092961:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE)) {
                    if (this.zzj) {
                        billingResult = zzce.zzl;
                    } else {
                        billingResult = zzce.zzp;
                    }
                    zzau(billingResult, 10, 3);
                    return billingResult;
                }
                break;
            case 96321:
                if (str.equals("aaa")) {
                    if (this.zzr) {
                        billingResult2 = zzce.zzl;
                    } else {
                        billingResult2 = zzce.zzs;
                    }
                    zzau(billingResult2, 31, 6);
                    return billingResult2;
                }
                break;
            case 97314:
                if (str.equals(BillingClient.FeatureType.IN_APP_MESSAGING)) {
                    if (this.zzp) {
                        billingResult3 = zzce.zzl;
                    } else {
                        billingResult3 = zzce.zzw;
                    }
                    zzau(billingResult3, 30, 5);
                    return billingResult3;
                }
                break;
            case 98307:
                if (str.equals("ccc")) {
                    if (this.zzs) {
                        billingResult4 = zzce.zzl;
                    } else {
                        billingResult4 = zzce.zzt;
                    }
                    zzau(billingResult4, 19, 8);
                    return billingResult4;
                }
                break;
            case 99300:
                if (str.equals("ddd")) {
                    if (this.zzq) {
                        billingResult5 = zzce.zzl;
                    } else {
                        billingResult5 = zzce.zzu;
                    }
                    zzau(billingResult5, 21, 7);
                    return billingResult5;
                }
                break;
            case 100293:
                if (str.equals("eee")) {
                    if (this.zzs) {
                        billingResult6 = zzce.zzl;
                    } else {
                        billingResult6 = zzce.zzt;
                    }
                    zzau(billingResult6, 61, 9);
                    return billingResult6;
                }
                break;
            case 101286:
                if (str.equals(BillingClient.FeatureType.PRODUCT_DETAILS)) {
                    if (this.zzt) {
                        billingResult7 = zzce.zzl;
                    } else {
                        billingResult7 = zzce.zzv;
                    }
                    zzau(billingResult7, 20, 10);
                    return billingResult7;
                }
                break;
            case 102279:
                if (str.equals(BillingClient.FeatureType.BILLING_CONFIG)) {
                    if (this.zzu) {
                        billingResult8 = zzce.zzl;
                    } else {
                        billingResult8 = zzce.zzA;
                    }
                    zzau(billingResult8, 32, 11);
                    return billingResult8;
                }
                break;
            case 103272:
                if (str.equals("hhh")) {
                    if (this.zzu) {
                        billingResult9 = zzce.zzl;
                    } else {
                        billingResult9 = zzce.zzB;
                    }
                    zzau(billingResult9, 33, 12);
                    return billingResult9;
                }
                break;
            case 104265:
                if (str.equals("iii")) {
                    if (this.zzw) {
                        billingResult10 = zzce.zzl;
                    } else {
                        billingResult10 = zzce.zzD;
                    }
                    zzau(billingResult10, 60, 13);
                    return billingResult10;
                }
                break;
            case 105258:
                if (str.equals(BillingClient.FeatureType.ALTERNATIVE_BILLING_ONLY)) {
                    if (this.zzx) {
                        billingResult11 = zzce.zzl;
                    } else {
                        billingResult11 = zzce.zzE;
                    }
                    zzau(billingResult11, 66, 14);
                    return billingResult11;
                }
                break;
            case 106251:
                if (str.equals(BillingClient.FeatureType.EXTERNAL_OFFER)) {
                    BillingResult billingResult14 = this.zzy ? zzce.zzl : zzce.zzy;
                    zzau(billingResult14, 103, 18);
                    return billingResult14;
                }
                break;
            case 207616302:
                if (str.equals(BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION)) {
                    if (this.zzm) {
                        billingResult12 = zzce.zzl;
                    } else {
                        billingResult12 = zzce.zzr;
                    }
                    zzau(billingResult12, 35, 4);
                    return billingResult12;
                }
                break;
            case 1987365622:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS)) {
                    BillingResult billingResult15 = this.zzi ? zzce.zzl : zzce.zzo;
                    zzau(billingResult15, 9, 2);
                    return billingResult15;
                }
                break;
        }
        zzb.zzk("BillingClient", "Unsupported feature: ".concat(String.valueOf(str)));
        zzau(zzce.zzz, 34, 1);
        return zzce.zzz;
    }

    public final boolean isReady() {
        return (this.zza != 2 || this.zzg == null || this.zzh == null) ? false : true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:148:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03f9  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0401  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x043c  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0440  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x0481 A[SYNTHETIC, Splitter:B:181:0x0481] */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0493 A[Catch:{ CancellationException | TimeoutException -> 0x0500, Exception -> 0x04e9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.android.billingclient.api.BillingResult launchBillingFlow(android.app.Activity r34, com.android.billingclient.api.BillingFlowParams r35) {
        /*
            r33 = this;
            r1 = r33
            java.lang.String r0 = "proxyPackageVersion"
            java.lang.String r8 = "BUY_INTENT"
            com.android.billingclient.api.zzo r2 = r1.zzd
            r9 = 2
            if (r2 == 0) goto L_0x0519
            com.android.billingclient.api.zzo r2 = r1.zzd
            com.android.billingclient.api.PurchasesUpdatedListener r2 = r2.zzd()
            if (r2 == 0) goto L_0x0519
            boolean r2 = r1.isReady()
            if (r2 != 0) goto L_0x0028
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r9, r9, r0)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm
            r1.zzak(r0)
            return r0
        L_0x0028:
            java.util.ArrayList r2 = r35.zzf()
            java.util.List r3 = r35.zzg()
            r4 = 0
            java.lang.Object r5 = com.google.android.gms.internal.play_billing.zzan.zza(r2, r4)
            com.android.billingclient.api.SkuDetails r5 = (com.android.billingclient.api.SkuDetails) r5
            java.lang.Object r6 = com.google.android.gms.internal.play_billing.zzan.zza(r3, r4)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r6 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r6
            if (r5 == 0) goto L_0x0048
            java.lang.String r10 = r5.getSku()
            java.lang.String r11 = r5.getType()
            goto L_0x0058
        L_0x0048:
            com.android.billingclient.api.ProductDetails r10 = r6.zza()
            java.lang.String r10 = r10.getProductId()
            com.android.billingclient.api.ProductDetails r11 = r6.zza()
            java.lang.String r11 = r11.getProductType()
        L_0x0058:
            java.lang.String r12 = "subs"
            boolean r12 = r11.equals(r12)
            r13 = 9
            java.lang.String r14 = "BillingClient"
            if (r12 == 0) goto L_0x007d
            boolean r12 = r1.zzi
            if (r12 == 0) goto L_0x0069
            goto L_0x007d
        L_0x0069:
            java.lang.String r0 = "Current client doesn't support subscriptions."
            com.google.android.gms.internal.play_billing.zzb.zzk(r14, r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzo
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r13, r9, r0)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzo
            r1.zzak(r0)
            return r0
        L_0x007d:
            boolean r12 = r35.zzp()
            if (r12 == 0) goto L_0x009e
            boolean r12 = r1.zzl
            if (r12 == 0) goto L_0x0088
            goto L_0x009e
        L_0x0088:
            java.lang.String r0 = "Current client doesn't support extra params for buy intent."
            com.google.android.gms.internal.play_billing.zzb.zzk(r14, r0)
            r0 = 18
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzh
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r9, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzh
            r1.zzak(r0)
            return r0
        L_0x009e:
            int r12 = r2.size()
            r15 = 1
            if (r12 <= r15) goto L_0x00c0
            boolean r12 = r1.zzs
            if (r12 == 0) goto L_0x00aa
            goto L_0x00c0
        L_0x00aa:
            java.lang.String r0 = "Current client doesn't support multi-item purchases."
            com.google.android.gms.internal.play_billing.zzb.zzk(r14, r0)
            r0 = 19
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzt
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r9, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzt
            r1.zzak(r0)
            return r0
        L_0x00c0:
            boolean r12 = r3.isEmpty()
            if (r12 != 0) goto L_0x00e1
            boolean r12 = r1.zzt
            if (r12 == 0) goto L_0x00cb
            goto L_0x00e1
        L_0x00cb:
            java.lang.String r0 = "Current client doesn't support purchases with ProductDetails."
            com.google.android.gms.internal.play_billing.zzb.zzk(r14, r0)
            r0 = 20
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzv
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r9, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzv
            r1.zzak(r0)
            return r0
        L_0x00e1:
            boolean r12 = r1.zzl
            if (r12 == 0) goto L_0x046a
            boolean r12 = r1.zzn
            boolean r13 = r1.zzv
            com.android.billingclient.api.PendingPurchasesParams r9 = r1.zzz
            boolean r9 = r9.isEnabledForOneTimeProducts()
            r16 = r4
            com.android.billingclient.api.PendingPurchasesParams r4 = r1.zzz
            boolean r4 = r4.isEnabledForPrepaidPlans()
            boolean r15 = r1.zzA
            r18 = r4
            java.lang.String r4 = r1.zzb
            r19 = r6
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            r20 = r5
            java.lang.String r5 = "playBillingLibraryVersion"
            r6.putString(r5, r4)
            int r4 = r35.zza()
            if (r4 == 0) goto L_0x011a
            int r4 = r35.zza()
            java.lang.String r5 = "prorationMode"
            r6.putInt(r5, r4)
        L_0x011a:
            java.lang.String r4 = r35.zzb()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x012d
            java.lang.String r4 = r35.zzb()
            java.lang.String r5 = "accountId"
            r6.putString(r5, r4)
        L_0x012d:
            java.lang.String r4 = r35.zzc()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0140
            java.lang.String r4 = r35.zzc()
            java.lang.String r5 = "obfuscatedProfileId"
            r6.putString(r5, r4)
        L_0x0140:
            boolean r4 = r35.zzo()
            if (r4 == 0) goto L_0x014c
            java.lang.String r4 = "isOfferPersonalizedByDeveloper"
            r5 = 1
            r6.putBoolean(r4, r5)
        L_0x014c:
            boolean r4 = android.text.TextUtils.isEmpty(r16)
            if (r4 != 0) goto L_0x0164
            java.util.ArrayList r4 = new java.util.ArrayList
            java.lang.String[] r5 = new java.lang.String[]{r16}
            java.util.List r5 = java.util.Arrays.asList(r5)
            r4.<init>(r5)
            java.lang.String r5 = "skusToReplace"
            r6.putStringArrayList(r5, r4)
        L_0x0164:
            java.lang.String r4 = r35.zzd()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0177
            java.lang.String r4 = r35.zzd()
            java.lang.String r5 = "oldSkuPurchaseToken"
            r6.putString(r5, r4)
        L_0x0177:
            boolean r4 = android.text.TextUtils.isEmpty(r16)
            if (r4 != 0) goto L_0x0185
            java.lang.String r4 = "oldSkuPurchaseId"
            r5 = r16
            r6.putString(r4, r5)
            goto L_0x0187
        L_0x0185:
            r5 = r16
        L_0x0187:
            java.lang.String r4 = r35.zze()
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x019d
            java.lang.String r4 = r35.zze()
            r16 = r5
            java.lang.String r5 = "originalExternalTransactionId"
            r6.putString(r5, r4)
            goto L_0x019f
        L_0x019d:
            r16 = r5
        L_0x019f:
            boolean r4 = android.text.TextUtils.isEmpty(r16)
            if (r4 != 0) goto L_0x01ac
            java.lang.String r4 = "paymentsPurchaseParams"
            r5 = r16
            r6.putString(r4, r5)
        L_0x01ac:
            if (r12 == 0) goto L_0x01b7
            if (r9 == 0) goto L_0x01b7
            java.lang.String r4 = "enablePendingPurchases"
            r5 = 1
            r6.putBoolean(r4, r5)
            goto L_0x01b8
        L_0x01b7:
            r5 = 1
        L_0x01b8:
            if (r13 == 0) goto L_0x01c1
            if (r18 == 0) goto L_0x01c1
            java.lang.String r4 = "enablePendingPurchaseForSubscriptions"
            r6.putBoolean(r4, r5)
        L_0x01c1:
            if (r15 == 0) goto L_0x01c8
            java.lang.String r4 = "enableAlternativeBilling"
            r6.putBoolean(r4, r5)
        L_0x01c8:
            boolean r4 = r2.isEmpty()
            java.lang.String r5 = "additionalSkuTypes"
            java.lang.String r9 = "additionalSkus"
            java.lang.String r12 = "SKU_SERIALIZED_DOCID_LIST"
            java.lang.String r13 = "skuDetailsTokens"
            java.lang.String r15 = "SKU_OFFER_ID_TOKEN_LIST"
            r18 = r4
            if (r18 != 0) goto L_0x02e0
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r21 = r10
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r22 = r11
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r23 = r8
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r24 = r2.iterator()
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
        L_0x0205:
            boolean r29 = r24.hasNext()
            if (r29 == 0) goto L_0x0272
            java.lang.Object r29 = r24.next()
            com.android.billingclient.api.SkuDetails r29 = (com.android.billingclient.api.SkuDetails) r29
            java.lang.String r30 = r29.zzf()
            boolean r30 = r30.isEmpty()
            if (r30 != 0) goto L_0x0225
            r30 = r0
            java.lang.String r0 = r29.zzf()
            r4.add(r0)
            goto L_0x0227
        L_0x0225:
            r30 = r0
        L_0x0227:
            java.lang.String r0 = r29.zzc()
            r31 = r14
            java.lang.String r14 = r29.zzb()
            int r32 = r29.zza()
            java.lang.String r1 = r29.zze()
            r10.add(r0)
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r17 = 1
            r0 = r0 ^ 1
            r25 = r25 | r0
            r11.add(r14)
            boolean r0 = android.text.TextUtils.isEmpty(r14)
            r0 = r0 ^ 1
            r26 = r26 | r0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r32)
            r8.add(r0)
            if (r32 == 0) goto L_0x025d
            r0 = r17
            goto L_0x025e
        L_0x025d:
            r0 = 0
        L_0x025e:
            r27 = r27 | r0
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            r0 = r0 ^ 1
            r28 = r28 | r0
            r7.add(r1)
            r1 = r33
            r0 = r30
            r14 = r31
            goto L_0x0205
        L_0x0272:
            r30 = r0
            r31 = r14
            boolean r0 = r4.isEmpty()
            if (r0 != 0) goto L_0x027f
            r6.putStringArrayList(r13, r4)
        L_0x027f:
            if (r25 == 0) goto L_0x0284
            r6.putStringArrayList(r15, r10)
        L_0x0284:
            if (r26 == 0) goto L_0x028b
            java.lang.String r0 = "SKU_OFFER_ID_LIST"
            r6.putStringArrayList(r0, r11)
        L_0x028b:
            if (r27 == 0) goto L_0x0292
            java.lang.String r0 = "SKU_OFFER_TYPE_LIST"
            r6.putIntegerArrayList(r0, r8)
        L_0x0292:
            if (r28 == 0) goto L_0x0297
            r6.putStringArrayList(r12, r7)
        L_0x0297:
            int r0 = r2.size()
            r1 = 1
            if (r0 <= r1) goto L_0x0393
            java.util.ArrayList r0 = new java.util.ArrayList
            int r4 = r2.size()
            int r4 = r4 + -1
            r0.<init>(r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            int r7 = r2.size()
            int r7 = r7 + -1
            r4.<init>(r7)
            r7 = r1
        L_0x02b5:
            int r8 = r2.size()
            if (r7 >= r8) goto L_0x02d8
            java.lang.Object r8 = r2.get(r7)
            com.android.billingclient.api.SkuDetails r8 = (com.android.billingclient.api.SkuDetails) r8
            java.lang.String r8 = r8.getSku()
            r0.add(r8)
            java.lang.Object r8 = r2.get(r7)
            com.android.billingclient.api.SkuDetails r8 = (com.android.billingclient.api.SkuDetails) r8
            java.lang.String r8 = r8.getType()
            r4.add(r8)
            int r7 = r7 + 1
            goto L_0x02b5
        L_0x02d8:
            r6.putStringArrayList(r9, r0)
            r6.putStringArrayList(r5, r4)
            goto L_0x0393
        L_0x02e0:
            r30 = r0
            r23 = r8
            r21 = r10
            r22 = r11
            r31 = r14
            r1 = 1
            java.util.ArrayList r0 = new java.util.ArrayList
            int r2 = r3.size()
            int r2 = r2 + -1
            r0.<init>(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            int r4 = r3.size()
            int r4 = r4 + -1
            r2.<init>(r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r10 = 0
        L_0x0311:
            int r11 = r3.size()
            if (r10 >= r11) goto L_0x0372
            java.lang.Object r11 = r3.get(r10)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r11 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r11
            com.android.billingclient.api.ProductDetails r14 = r11.zza()
            java.lang.String r17 = r14.zzb()
            boolean r17 = r17.isEmpty()
            if (r17 != 0) goto L_0x0332
            java.lang.String r1 = r14.zzb()
            r4.add(r1)
        L_0x0332:
            java.lang.String r1 = r11.zzb()
            r7.add(r1)
            java.lang.String r1 = r14.zzc()
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x034a
            java.lang.String r1 = r14.zzc()
            r8.add(r1)
        L_0x034a:
            if (r10 <= 0) goto L_0x036e
            java.lang.Object r1 = r3.get(r10)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r1 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r1
            com.android.billingclient.api.ProductDetails r1 = r1.zza()
            java.lang.String r1 = r1.getProductId()
            r0.add(r1)
            java.lang.Object r1 = r3.get(r10)
            com.android.billingclient.api.BillingFlowParams$ProductDetailsParams r1 = (com.android.billingclient.api.BillingFlowParams.ProductDetailsParams) r1
            com.android.billingclient.api.ProductDetails r1 = r1.zza()
            java.lang.String r1 = r1.getProductType()
            r2.add(r1)
        L_0x036e:
            int r10 = r10 + 1
            r1 = 1
            goto L_0x0311
        L_0x0372:
            r6.putStringArrayList(r15, r7)
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x037e
            r6.putStringArrayList(r13, r4)
        L_0x037e:
            boolean r1 = r8.isEmpty()
            if (r1 != 0) goto L_0x0387
            r6.putStringArrayList(r12, r8)
        L_0x0387:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0393
            r6.putStringArrayList(r9, r0)
            r6.putStringArrayList(r5, r2)
        L_0x0393:
            boolean r0 = r6.containsKey(r15)
            r1 = r33
            if (r0 == 0) goto L_0x03b2
            boolean r0 = r1.zzq
            if (r0 == 0) goto L_0x03a0
            goto L_0x03b2
        L_0x03a0:
            r0 = 21
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzu
            r3 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r3, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzu
            r1.zzak(r0)
            return r0
        L_0x03b2:
            java.lang.String r0 = "skuPackageName"
            if (r20 == 0) goto L_0x03ca
            java.lang.String r2 = r20.zzd()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x03ca
            java.lang.String r2 = r20.zzd()
            r6.putString(r0, r2)
        L_0x03c7:
            r5 = 0
            r15 = 1
            goto L_0x03e8
        L_0x03ca:
            if (r19 == 0) goto L_0x03e6
            com.android.billingclient.api.ProductDetails r2 = r19.zza()
            java.lang.String r2 = r2.zza()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 != 0) goto L_0x03e6
            com.android.billingclient.api.ProductDetails r2 = r19.zza()
            java.lang.String r2 = r2.zza()
            r6.putString(r0, r2)
            goto L_0x03c7
        L_0x03e6:
            r5 = 0
            r15 = 0
        L_0x03e8:
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L_0x03f3
            java.lang.String r0 = "accountName"
            r6.putString(r0, r5)
        L_0x03f3:
            android.content.Intent r0 = r34.getIntent()
            if (r0 != 0) goto L_0x0401
            java.lang.String r0 = "Activity's intent is null."
            r7 = r31
            com.google.android.gms.internal.play_billing.zzb.zzk(r7, r0)
            goto L_0x0432
        L_0x0401:
            r7 = r31
            java.lang.String r2 = "PROXY_PACKAGE"
            java.lang.String r4 = r0.getStringExtra(r2)
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 != 0) goto L_0x0432
            java.lang.String r0 = r0.getStringExtra(r2)
            java.lang.String r2 = "proxyPackage"
            r6.putString(r2, r0)
            android.content.Context r2 = r1.zze     // Catch:{ NameNotFoundException -> 0x042b }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x042b }
            r4 = 0
            android.content.pm.PackageInfo r0 = r2.getPackageInfo(r0, r4)     // Catch:{ NameNotFoundException -> 0x042b }
            java.lang.String r0 = r0.versionName     // Catch:{ NameNotFoundException -> 0x042b }
            r2 = r30
            r6.putString(r2, r0)     // Catch:{ NameNotFoundException -> 0x042d }
            goto L_0x0432
        L_0x042b:
            r2 = r30
        L_0x042d:
            java.lang.String r0 = "package not found"
            r6.putString(r2, r0)
        L_0x0432:
            boolean r0 = r1.zzt
            if (r0 == 0) goto L_0x0440
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0440
            r13 = 17
        L_0x043e:
            r2 = r13
            goto L_0x0452
        L_0x0440:
            boolean r0 = r1.zzr
            if (r0 == 0) goto L_0x0449
            if (r15 == 0) goto L_0x0449
            r13 = 15
            goto L_0x043e
        L_0x0449:
            boolean r0 = r1.zzn
            if (r0 == 0) goto L_0x0450
            r2 = 9
            goto L_0x0452
        L_0x0450:
            r13 = 6
            goto L_0x043e
        L_0x0452:
            com.android.billingclient.api.zzas r0 = new com.android.billingclient.api.zzas
            r5 = r35
            r3 = r21
            r4 = r22
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r5 = 0
            android.os.Handler r6 = r1.zzc
            r3 = 5000(0x1388, double:2.4703E-320)
            r2 = r0
            java.util.concurrent.Future r0 = r1.zzao(r2, r3, r5, r6)
            r2 = 78
            goto L_0x047f
        L_0x046a:
            r23 = r8
            r3 = r10
            r4 = r11
            r7 = r14
            com.android.billingclient.api.zzr r2 = new com.android.billingclient.api.zzr
            r2.<init>(r1, r3, r4)
            r5 = 0
            android.os.Handler r6 = r1.zzc
            r3 = 5000(0x1388, double:2.4703E-320)
            java.util.concurrent.Future r0 = r1.zzao(r2, r3, r5, r6)
            r2 = 80
        L_0x047f:
            if (r0 != 0) goto L_0x0493
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r2 = 25
            r3 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r2, r3, r0)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r1.zzap(r0)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r1.zzak(r0)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            return r0
        L_0x0493:
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r4 = 5000(0x1388, double:2.4703E-320)
            java.lang.Object r0 = r0.get(r4, r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            android.os.Bundle r0 = (android.os.Bundle) r0     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            int r3 = com.google.android.gms.internal.play_billing.zzb.zzb(r0, r7)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            java.lang.String r4 = com.google.android.gms.internal.play_billing.zzb.zzg(r0, r7)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            if (r3 == 0) goto L_0x04cf
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r5.<init>()     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            java.lang.String r6 = "Unable to buy item, Error response code: "
            r5.append(r6)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r5.append(r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            java.lang.String r5 = r5.toString()     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            com.google.android.gms.internal.play_billing.zzb.zzk(r7, r5)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            com.android.billingclient.api.BillingResult r3 = com.android.billingclient.api.zzce.zza(r3, r4)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            if (r0 == 0) goto L_0x04c3
            r2 = 23
        L_0x04c3:
            r4 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r2, r4, r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r1.zzap(r0)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r1.zzak(r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            return r3
        L_0x04cf:
            android.content.Intent r2 = new android.content.Intent     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            java.lang.Class<com.android.billingclient.api.ProxyBillingActivity> r3 = com.android.billingclient.api.ProxyBillingActivity.class
            r4 = r34
            r2.<init>(r4, r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r3 = r23
            android.os.Parcelable r0 = r0.getParcelable(r3)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            android.app.PendingIntent r0 = (android.app.PendingIntent) r0     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r2.putExtra(r3, r0)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            r4.startActivity(r2)     // Catch:{ TimeoutException -> 0x0502, CancellationException -> 0x0500, Exception -> 0x04e9 }
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzl
            return r0
        L_0x04e9:
            r0 = move-exception
            java.lang.String r2 = "Exception while launching billing flow. Try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r7, r2, r0)
            r0 = 5
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzm
            r3 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r3, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzm
            r1.zzak(r0)
            return r0
        L_0x0500:
            r0 = move-exception
            goto L_0x0503
        L_0x0502:
            r0 = move-exception
        L_0x0503:
            java.lang.String r2 = "Time out while launching billing flow. Try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r7, r2, r0)
            r0 = 4
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzn
            r3 = 2
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r3, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzn
            r1.zzak(r0)
            return r0
        L_0x0519:
            r3 = r9
            r0 = 12
            com.android.billingclient.api.BillingResult r2 = com.android.billingclient.api.zzce.zzF
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zza(r0, r3, r2)
            r1.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzF
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.launchBillingFlow(android.app.Activity, com.android.billingclient.api.BillingFlowParams):com.android.billingclient.api.BillingResult");
    }

    public final void queryProductDetailsAsync(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 7, zzce.zzm));
            productDetailsResponseListener.onProductDetailsResponse(zzce.zzm, new ArrayList());
        } else if (!this.zzt) {
            zzb.zzk("BillingClient", "Querying product details is not supported.");
            zzap(zzcb.zza(20, 7, zzce.zzv));
            productDetailsResponseListener.onProductDetailsResponse(zzce.zzv, new ArrayList());
        } else {
            if (zzao(new zzan(this, queryProductDetailsParams, productDetailsResponseListener), 30000, new zzao(this, productDetailsResponseListener), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 7, zzal));
                productDetailsResponseListener.onProductDetailsResponse(zzal, new ArrayList());
            }
        }
    }

    public final void queryPurchaseHistoryAsync(QueryPurchaseHistoryParams queryPurchaseHistoryParams, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzar(queryPurchaseHistoryParams.zza(), purchaseHistoryResponseListener);
    }

    public final void queryPurchasesAsync(QueryPurchasesParams queryPurchasesParams, PurchasesResponseListener purchasesResponseListener) {
        zzas(queryPurchasesParams.zza(), purchasesResponseListener);
    }

    public final void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams, SkuDetailsResponseListener skuDetailsResponseListener) {
        if (!isReady()) {
            zzap(zzcb.zza(2, 8, zzce.zzm));
            skuDetailsResponseListener.onSkuDetailsResponse(zzce.zzm, (List<SkuDetails>) null);
            return;
        }
        String skuType = skuDetailsParams.getSkuType();
        List<String> skusList = skuDetailsParams.getSkusList();
        if (TextUtils.isEmpty(skuType)) {
            zzb.zzk("BillingClient", "Please fix the input params. SKU type can't be empty.");
            zzap(zzcb.zza(49, 8, zzce.zzf));
            skuDetailsResponseListener.onSkuDetailsResponse(zzce.zzf, (List<SkuDetails>) null);
        } else if (skusList == null) {
            zzb.zzk("BillingClient", "Please fix the input params. The list of SKUs can't be empty.");
            zzap(zzcb.zza(48, 8, zzce.zze));
            skuDetailsResponseListener.onSkuDetailsResponse(zzce.zze, (List<SkuDetails>) null);
        } else {
            SkuDetailsResponseListener skuDetailsResponseListener2 = skuDetailsResponseListener;
            SkuDetailsResponseListener skuDetailsResponseListener3 = skuDetailsResponseListener2;
            if (zzao(new zzac(this, skuType, skusList, (String) null, skuDetailsResponseListener2), 30000, new zzad(this, skuDetailsResponseListener3), zzaj()) == null) {
                BillingResult zzal = zzal();
                zzap(zzcb.zza(25, 8, zzal));
                skuDetailsResponseListener3.onSkuDetailsResponse(zzal, (List<SkuDetails>) null);
            }
        }
    }

    public final BillingResult showInAppMessages(Activity activity, InAppMessageParams inAppMessageParams, InAppMessageResponseListener inAppMessageResponseListener) {
        if (!isReady()) {
            zzb.zzk("BillingClient", "Service disconnected.");
            return zzce.zzm;
        } else if (!this.zzp) {
            zzb.zzk("BillingClient", "Current client doesn't support showing in-app messages.");
            return zzce.zzw;
        } else {
            View findViewById = activity.findViewById(16908290);
            IBinder windowToken = findViewById.getWindowToken();
            Rect rect = new Rect();
            findViewById.getGlobalVisibleRect(rect);
            Bundle bundle = new Bundle();
            BundleCompat.putBinder(bundle, "KEY_WINDOW_TOKEN", windowToken);
            bundle.putInt("KEY_DIMEN_LEFT", rect.left);
            bundle.putInt("KEY_DIMEN_TOP", rect.top);
            bundle.putInt("KEY_DIMEN_RIGHT", rect.right);
            bundle.putInt("KEY_DIMEN_BOTTOM", rect.bottom);
            bundle.putString("playBillingLibraryVersion", this.zzb);
            bundle.putIntegerArrayList("KEY_CATEGORY_IDS", inAppMessageParams.zza());
            zzao(new zzap(this, bundle, activity, new zzaw(this, this.zzc, inAppMessageResponseListener)), 5000, (Runnable) null, this.zzc);
            return zzce.zzl;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzR(AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        zzap(zzcb.zza(24, 3, zzce.zzn));
        acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzS(BillingResult billingResult) {
        if (this.zzd.zzd() != null) {
            this.zzd.zzd().onPurchasesUpdated(billingResult, (List<Purchase>) null);
        } else {
            zzb.zzk("BillingClient", "No valid listener is set in BroadcastManager");
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzT(ConsumeResponseListener consumeResponseListener, ConsumeParams consumeParams) {
        zzap(zzcb.zza(24, 4, zzce.zzn));
        consumeResponseListener.onConsumeResponse(zzce.zzn, consumeParams.getPurchaseToken());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzU(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) {
        zzap(zzcb.zza(24, 15, zzce.zzn));
        alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzce.zzn, (AlternativeBillingOnlyReportingDetails) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzV(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) {
        zzap(zzcb.zza(24, 24, zzce.zzn));
        externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzce.zzn, (ExternalOfferReportingDetails) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzW(BillingConfigResponseListener billingConfigResponseListener) {
        zzap(zzcb.zza(24, 13, zzce.zzn));
        billingConfigResponseListener.onBillingConfigResponse(zzce.zzn, (BillingConfig) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzX(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) {
        zzap(zzcb.zza(24, 14, zzce.zzn));
        alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzY(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) {
        zzap(zzcb.zza(24, 23, zzce.zzn));
        externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzZ(ProductDetailsResponseListener productDetailsResponseListener) {
        zzap(zzcb.zza(24, 7, zzce.zzn));
        productDetailsResponseListener.onProductDetailsResponse(zzce.zzn, new ArrayList());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaa(PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzap(zzcb.zza(24, 11, zzce.zzn));
        purchaseHistoryResponseListener.onPurchaseHistoryResponse(zzce.zzn, (List<PurchaseHistoryRecord>) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzab(PurchasesResponseListener purchasesResponseListener) {
        zzap(zzcb.zza(24, 9, zzce.zzn));
        purchasesResponseListener.onQueryPurchasesResponse(zzce.zzn, zzai.zzk());
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzac(SkuDetailsResponseListener skuDetailsResponseListener) {
        zzap(zzcb.zza(24, 8, zzce.zzn));
        skuDetailsResponseListener.onSkuDetailsResponse(zzce.zzn, (List<SkuDetails>) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzad(AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        zzap(zzcb.zza(24, 16, zzce.zzn));
        alternativeBillingOnlyInformationDialogListener.onAlternativeBillingOnlyInformationDialogResponse(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzae(ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        zzap(zzcb.zza(24, 25, zzce.zzn));
        externalOfferInformationDialogListener.onExternalOfferInformationDialogResponse(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Bundle zzc(int i, String str, String str2, BillingFlowParams billingFlowParams, Bundle bundle) throws Exception {
        return this.zzg.zzg(i, this.zze.getPackageName(), str, str2, (String) null, bundle);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Bundle zzd(String str, String str2) throws Exception {
        return this.zzg.zzf(3, this.zze.getPackageName(), str, str2, (String) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzk(AcknowledgePurchaseParams acknowledgePurchaseParams, AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) throws Exception {
        try {
            zzs zzs2 = this.zzg;
            String packageName = this.zze.getPackageName();
            String purchaseToken = acknowledgePurchaseParams.getPurchaseToken();
            String str = this.zzb;
            Bundle bundle = new Bundle();
            bundle.putString("playBillingLibraryVersion", str);
            Bundle zzd2 = zzs2.zzd(9, packageName, purchaseToken, bundle);
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zza(zzb.zzb(zzd2, "BillingClient"), zzb.zzg(zzd2, "BillingClient")));
            return null;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Error acknowledge purchase!", e);
            zzap(zzcb.zza(28, 3, zzce.zzm));
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(zzce.zzm);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzl(ConsumeParams consumeParams, ConsumeResponseListener consumeResponseListener) throws Exception {
        int i;
        String str;
        String purchaseToken = consumeParams.getPurchaseToken();
        try {
            zzb.zzj("BillingClient", "Consuming purchase with token: " + purchaseToken);
            if (this.zzn) {
                zzs zzs2 = this.zzg;
                String packageName = this.zze.getPackageName();
                boolean z = this.zzn;
                String str2 = this.zzb;
                Bundle bundle = new Bundle();
                if (z) {
                    bundle.putString("playBillingLibraryVersion", str2);
                }
                Bundle zze2 = zzs2.zze(9, packageName, purchaseToken, bundle);
                i = zze2.getInt("RESPONSE_CODE");
                str = zzb.zzg(zze2, "BillingClient");
            } else {
                i = this.zzg.zza(3, this.zze.getPackageName(), purchaseToken);
                str = "";
            }
            BillingResult zza2 = zzce.zza(i, str);
            if (i == 0) {
                zzb.zzj("BillingClient", "Successfully consumed purchase.");
                consumeResponseListener.onConsumeResponse(zza2, purchaseToken);
                return null;
            }
            zzb.zzk("BillingClient", "Error consuming purchase with token. Response code: " + i);
            zzap(zzcb.zza(23, 4, zza2));
            consumeResponseListener.onConsumeResponse(zza2, purchaseToken);
            return null;
        } catch (Exception e) {
            zzb.zzl("BillingClient", "Error consuming purchase!", e);
            zzap(zzcb.zza(29, 4, zzce.zzm));
            consumeResponseListener.onConsumeResponse(zzce.zzm, purchaseToken);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzm(Bundle bundle, BillingConfigResponseListener billingConfigResponseListener) throws Exception {
        try {
            this.zzg.zzp(18, this.zze.getPackageName(), bundle, new zzbk(billingConfigResponseListener, this.zzf, this.zzk, (zzbj) null));
        } catch (DeadObjectException e) {
            zzb.zzl("BillingClient", "getBillingConfig got a dead object exception (try to reconnect).", e);
            zzap(zzcb.zza(62, 13, zzce.zzm));
            billingConfigResponseListener.onBillingConfigResponse(zzce.zzm, (BillingConfig) null);
        } catch (Exception e2) {
            zzb.zzl("BillingClient", "getBillingConfig got an exception.", e2);
            zzap(zzcb.zza(62, 13, zzce.zzj));
            billingConfigResponseListener.onBillingConfigResponse(zzce.zzj, (BillingConfig) null);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzn(QueryProductDetailsParams queryProductDetailsParams, ProductDetailsResponseListener productDetailsResponseListener) throws Exception {
        String str;
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        String zzb2 = queryProductDetailsParams.zzb();
        zzai zza2 = queryProductDetailsParams.zza();
        int size = zza2.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                str = "";
                i = 0;
                break;
            }
            int i4 = i3 + 20;
            ArrayList arrayList2 = new ArrayList(zza2.subList(i3, i4 > size ? size : i4));
            ArrayList arrayList3 = new ArrayList();
            int size2 = arrayList2.size();
            for (int i5 = 0; i5 < size2; i5++) {
                arrayList3.add(((QueryProductDetailsParams.Product) arrayList2.get(i5)).zza());
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle.putString("playBillingLibraryVersion", this.zzb);
            try {
                zzs zzs2 = this.zzg;
                int i6 = true != this.zzw ? 17 : 20;
                String packageName = this.zze.getPackageName();
                boolean zzat = zzat();
                String str2 = this.zzb;
                zzam(queryProductDetailsParams);
                zzam(queryProductDetailsParams);
                zzam(queryProductDetailsParams);
                int i7 = i6;
                Bundle bundle2 = new Bundle();
                bundle2.putString("playBillingLibraryVersion", str2);
                bundle2.putBoolean("enablePendingPurchases", true);
                bundle2.putString("SKU_DETAILS_RESPONSE_FORMAT", "PRODUCT_DETAILS");
                if (zzat) {
                    bundle2.putBoolean("enablePendingPurchaseForSubscriptions", true);
                }
                ArrayList arrayList4 = new ArrayList();
                ArrayList arrayList5 = new ArrayList();
                int size3 = arrayList2.size();
                int i8 = 0;
                boolean z = false;
                boolean z2 = false;
                while (i8 < size3) {
                    zzs zzs3 = zzs2;
                    arrayList4.add((Object) null);
                    z |= !TextUtils.isEmpty((CharSequence) null);
                    zzai zzai = zza2;
                    if (((QueryProductDetailsParams.Product) arrayList2.get(i8)).zzb().equals("first_party")) {
                        zzaa.zzc((Object) null, "Serialized DocId is required for constructing ExtraParams to query ProductDetails for all first party products.");
                        arrayList5.add((Object) null);
                        z2 = true;
                    }
                    i8++;
                    zza2 = zzai;
                    zzs2 = zzs3;
                }
                zzai zzai2 = zza2;
                zzs zzs4 = zzs2;
                if (z) {
                    bundle2.putStringArrayList("SKU_OFFER_ID_TOKEN_LIST", arrayList4);
                }
                if (!arrayList5.isEmpty()) {
                    bundle2.putStringArrayList("SKU_SERIALIZED_DOCID_LIST", arrayList5);
                }
                if (z2 && !TextUtils.isEmpty((CharSequence) null)) {
                    bundle2.putString("accountName", (String) null);
                }
                Bundle zzl2 = zzs4.zzl(i7, packageName, zzb2, bundle, bundle2);
                i2 = 4;
                str = "Item is unavailable for purchase.";
                if (zzl2 == null) {
                    zzb.zzk("BillingClient", "queryProductDetailsAsync got empty product details response.");
                    zzap(zzcb.zza(44, 7, zzce.zzC));
                    break;
                } else if (!zzl2.containsKey("DETAILS_LIST")) {
                    i = zzb.zzb(zzl2, "BillingClient");
                    str = zzb.zzg(zzl2, "BillingClient");
                    if (i != 0) {
                        zzb.zzk("BillingClient", "getSkuDetails() failed for queryProductDetailsAsync. Response code: " + i);
                        zzap(zzcb.zza(23, 7, zzce.zza(i, str)));
                    } else {
                        zzb.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a product detail list for queryProductDetailsAsync.");
                        zzap(zzcb.zza(45, 7, zzce.zza(6, str)));
                        i = 6;
                    }
                } else {
                    ArrayList<String> stringArrayList = zzl2.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList == null) {
                        zzb.zzk("BillingClient", "queryProductDetailsAsync got null response list");
                        zzap(zzcb.zza(46, 7, zzce.zzC));
                        break;
                    }
                    int i9 = 0;
                    while (i9 < stringArrayList.size()) {
                        try {
                            ProductDetails productDetails = new ProductDetails(stringArrayList.get(i9));
                            zzb.zzj("BillingClient", "Got product details: ".concat(productDetails.toString()));
                            arrayList.add(productDetails);
                            i9++;
                        } catch (JSONException e) {
                            zzb.zzl("BillingClient", "Got a JSON exception trying to decode ProductDetails. \n Exception: ", e);
                            str = "Error trying to decode SkuDetails.";
                            i2 = 6;
                            zzap(zzcb.zza(47, 7, zzce.zza(6, str)));
                        }
                    }
                    i3 = i4;
                    zza2 = zzai2;
                }
            } catch (Exception e2) {
                i2 = 6;
                zzb.zzl("BillingClient", "queryProductDetailsAsync got a remote exception (try to reconnect).", e2);
                zzap(zzcb.zza(43, 7, zzce.zzj));
                str = "An internal error occurred.";
            }
        }
        i = i2;
        productDetailsResponseListener.onProductDetailsResponse(zzce.zza(i, str), arrayList);
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzo(String str, List list, String str2, SkuDetailsResponseListener skuDetailsResponseListener) throws Exception {
        ArrayList arrayList;
        String str3;
        int i;
        Bundle bundle;
        ArrayList arrayList2 = new ArrayList();
        int size = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                arrayList = null;
                str3 = "";
                i = 0;
                break;
            }
            int i3 = i2 + 20;
            ArrayList arrayList3 = new ArrayList(list.subList(i2, i3 > size ? size : i3));
            Bundle bundle2 = new Bundle();
            bundle2.putStringArrayList("ITEM_ID_LIST", arrayList3);
            bundle2.putString("playBillingLibraryVersion", this.zzb);
            try {
                if (this.zzo) {
                    zzs zzs2 = this.zzg;
                    String packageName = this.zze.getPackageName();
                    int i4 = this.zzk;
                    boolean isEnabledForOneTimeProducts = this.zzz.isEnabledForOneTimeProducts();
                    boolean zzat = zzat();
                    String str4 = this.zzb;
                    arrayList = null;
                    try {
                        Bundle bundle3 = new Bundle();
                        if (i4 >= 9) {
                            bundle3.putString("playBillingLibraryVersion", str4);
                        }
                        if (i4 >= 9 && isEnabledForOneTimeProducts) {
                            bundle3.putBoolean("enablePendingPurchases", true);
                        }
                        if (zzat) {
                            bundle3.putBoolean("enablePendingPurchaseForSubscriptions", true);
                        }
                        bundle = zzs2.zzl(10, packageName, str, bundle2, bundle3);
                        String str5 = str;
                    } catch (Exception e) {
                        e = e;
                        zzb.zzl("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect).", e);
                        zzap(zzcb.zza(43, 8, zzce.zzm));
                        str3 = "Service connection is disconnected.";
                        i = -1;
                        arrayList2 = arrayList;
                        skuDetailsResponseListener.onSkuDetailsResponse(zzce.zza(i, str3), arrayList2);
                        return arrayList;
                    }
                } else {
                    arrayList = null;
                    bundle = this.zzg.zzk(3, this.zze.getPackageName(), str, bundle2);
                }
                str3 = "Item is unavailable for purchase.";
                if (bundle == null) {
                    zzb.zzk("BillingClient", "querySkuDetailsAsync got null sku details list");
                    zzap(zzcb.zza(44, 8, zzce.zzC));
                    break;
                } else if (!bundle.containsKey("DETAILS_LIST")) {
                    int zzb2 = zzb.zzb(bundle, "BillingClient");
                    str3 = zzb.zzg(bundle, "BillingClient");
                    if (zzb2 != 0) {
                        zzb.zzk("BillingClient", "getSkuDetails() failed. Response code: " + zzb2);
                        zzap(zzcb.zza(23, 8, zzce.zza(zzb2, str3)));
                        i = zzb2;
                    } else {
                        zzb.zzk("BillingClient", "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                        zzap(zzcb.zza(45, 8, zzce.zza(6, str3)));
                        i = 6;
                    }
                } else {
                    ArrayList<String> stringArrayList = bundle.getStringArrayList("DETAILS_LIST");
                    if (stringArrayList == null) {
                        zzb.zzk("BillingClient", "querySkuDetailsAsync got null response list");
                        zzap(zzcb.zza(46, 8, zzce.zzC));
                        break;
                    }
                    int i5 = 0;
                    while (i5 < stringArrayList.size()) {
                        try {
                            SkuDetails skuDetails = new SkuDetails(stringArrayList.get(i5));
                            zzb.zzj("BillingClient", "Got sku details: ".concat(skuDetails.toString()));
                            arrayList2.add(skuDetails);
                            i5++;
                        } catch (JSONException e2) {
                            zzb.zzl("BillingClient", "Got a JSON exception trying to decode SkuDetails.", e2);
                            str3 = "Error trying to decode SkuDetails.";
                            zzap(zzcb.zza(47, 8, zzce.zza(6, str3)));
                            i = 6;
                        }
                    }
                    i2 = i3;
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
                zzb.zzl("BillingClient", "querySkuDetailsAsync got a remote exception (try to reconnect).", e);
                zzap(zzcb.zza(43, 8, zzce.zzm));
                str3 = "Service connection is disconnected.";
                i = -1;
                arrayList2 = arrayList;
                skuDetailsResponseListener.onSkuDetailsResponse(zzce.zza(i, str3), arrayList2);
                return arrayList;
            }
        }
        i = 4;
        arrayList2 = arrayList;
        skuDetailsResponseListener.onSkuDetailsResponse(zzce.zza(i, str3), arrayList2);
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object zzp(Bundle bundle, Activity activity, ResultReceiver resultReceiver) throws Exception {
        this.zzg.zzt(12, this.zze.getPackageName(), bundle, new zzbs(new WeakReference(activity), resultReceiver, (zzbr) null));
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzq(AlternativeBillingOnlyReportingDetailsListener alternativeBillingOnlyReportingDetailsListener) throws Exception {
        try {
            this.zzg.zzm(21, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbe(alternativeBillingOnlyReportingDetailsListener, this.zzf, this.zzk, (zzbd) null));
        } catch (Exception unused) {
            zzap(zzcb.zza(70, 15, zzce.zzj));
            alternativeBillingOnlyReportingDetailsListener.onAlternativeBillingOnlyTokenResponse(zzce.zzj, (AlternativeBillingOnlyReportingDetails) null);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzr(ExternalOfferReportingDetailsListener externalOfferReportingDetailsListener) throws Exception {
        try {
            this.zzg.zzn(22, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbg(externalOfferReportingDetailsListener, this.zzf, this.zzk, (zzbf) null));
        } catch (Exception e) {
            zzap(zzcb.zzb(94, 24, zzce.zzj, String.format("%s: %s", new Object[]{e.getClass().getName(), zzab.zzb(e.getMessage())})));
            externalOfferReportingDetailsListener.onExternalOfferReportingDetailsResponse(zzce.zzj, (ExternalOfferReportingDetails) null);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzs(AlternativeBillingOnlyAvailabilityListener alternativeBillingOnlyAvailabilityListener) throws Exception {
        try {
            this.zzg.zzr(21, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbo(alternativeBillingOnlyAvailabilityListener, this.zzf, this.zzk, (zzbn) null));
        } catch (Exception unused) {
            zzap(zzcb.zza(69, 14, zzce.zzj));
            alternativeBillingOnlyAvailabilityListener.onAlternativeBillingOnlyAvailabilityResponse(zzce.zzj);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzt(ExternalOfferAvailabilityListener externalOfferAvailabilityListener) throws Exception {
        try {
            this.zzg.zzs(22, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbq(externalOfferAvailabilityListener, this.zzf, this.zzk, (zzbp) null));
        } catch (Exception e) {
            zzap(zzcb.zzb(91, 23, zzce.zzj, String.format("%s: %s", new Object[]{e.getClass().getName(), zzab.zzb(e.getMessage())})));
            externalOfferAvailabilityListener.onExternalOfferAvailabilityResponse(zzce.zzj);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzu(Activity activity, ResultReceiver resultReceiver, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) throws Exception {
        try {
            this.zzg.zzo(21, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbi(new WeakReference(activity), resultReceiver, (zzbh) null));
        } catch (Exception unused) {
            zzap(zzcb.zza(74, 16, zzce.zzj));
            alternativeBillingOnlyInformationDialogListener.onAlternativeBillingOnlyInformationDialogResponse(zzce.zzj);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Void zzv(Activity activity, ResultReceiver resultReceiver, ExternalOfferInformationDialogListener externalOfferInformationDialogListener) throws Exception {
        try {
            this.zzg.zzq(22, this.zze.getPackageName(), zzb.zzd(this.zzb), new zzbm(new WeakReference(activity), resultReceiver, (zzbl) null));
        } catch (Exception e) {
            zzap(zzcb.zzb(98, 25, zzce.zzj, String.format("%s: %s", new Object[]{e.getClass().getName(), zzab.zzb(e.getMessage())})));
            externalOfferInformationDialogListener.onExternalOfferInformationDialogResponse(zzce.zzj);
        }
        return null;
    }

    public final void queryPurchaseHistoryAsync(String str, PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        zzar(str, purchaseHistoryResponseListener);
    }

    public final void queryPurchasesAsync(String str, PurchasesResponseListener purchasesResponseListener) {
        zzas(str, purchasesResponseListener);
    }

    public BillingResult showAlternativeBillingOnlyInformationDialog(Activity activity, AlternativeBillingOnlyInformationDialogListener alternativeBillingOnlyInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        } else if (!isReady()) {
            zzap(zzcb.zza(2, 16, zzce.zzm));
            return zzce.zzm;
        } else if (!this.zzx) {
            zzb.zzk("BillingClient", "Current Play Store version doesn't support alternative billing only.");
            zzap(zzcb.zza(66, 16, zzce.zzE));
            return zzce.zzE;
        } else {
            if (zzao(new zzs(this, activity, new zzax(this, this.zzc, alternativeBillingOnlyInformationDialogListener), alternativeBillingOnlyInformationDialogListener), 30000, new zzt(this, alternativeBillingOnlyInformationDialogListener), this.zzc) != null) {
                return zzce.zzl;
            }
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 16, zzal));
            return zzal;
        }
    }

    public BillingResult showExternalOfferInformationDialog(Activity activity, ExternalOfferInformationDialogListener externalOfferInformationDialogListener) {
        if (activity == null) {
            throw new IllegalArgumentException("Please provide a valid activity.");
        } else if (!isReady()) {
            zzap(zzcb.zza(2, 25, zzce.zzm));
            return zzce.zzm;
        } else if (!this.zzy) {
            zzb.zzk("BillingClient", "Current Play Store version doesn't support external offer.");
            zzap(zzcb.zza(103, 25, zzce.zzy));
            return zzce.zzy;
        } else {
            if (zzao(new zzaj(this, activity, new zzay(this, this.zzc, externalOfferInformationDialogListener), externalOfferInformationDialogListener), 30000, new zzal(this, externalOfferInformationDialogListener), this.zzc) != null) {
                return zzce.zzl;
            }
            BillingResult zzal = zzal();
            zzap(zzcb.zza(25, 25, zzal));
            return zzal;
        }
    }

    private BillingClientImpl(Context context, PendingPurchasesParams pendingPurchasesParams, PurchasesUpdatedListener purchasesUpdatedListener, String str, String str2, UserChoiceBillingListener userChoiceBillingListener, zzcc zzcc, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = str;
        initialize(context, purchasesUpdatedListener, pendingPurchasesParams, userChoiceBillingListener, str, (zzcc) null);
    }

    private BillingClientImpl(String str) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = str;
    }

    BillingClientImpl(String str, Context context, zzcc zzcc, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        String zzan = zzan();
        this.zzb = zzan;
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(zzan);
        zzy2.zzm(this.zze.getPackageName());
        this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        this.zze.getPackageName();
    }

    private void initialize(Context context, PurchasesUpdatedListener purchasesUpdatedListener, PendingPurchasesParams pendingPurchasesParams, UserChoiceBillingListener userChoiceBillingListener, String str, zzcc zzcc) {
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(str);
        zzy2.zzm(this.zze.getPackageName());
        if (zzcc != null) {
            this.zzf = zzcc;
        } else {
            this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        }
        if (purchasesUpdatedListener == null) {
            zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        }
        UserChoiceBillingListener userChoiceBillingListener2 = userChoiceBillingListener;
        this.zzd = new zzo(this.zze, purchasesUpdatedListener, (zzck) null, (zzc) null, userChoiceBillingListener2, this.zzf);
        this.zzz = pendingPurchasesParams;
        this.zzA = userChoiceBillingListener2 != null;
    }

    BillingClientImpl(String str, PendingPurchasesParams pendingPurchasesParams, Context context, zzck zzck, zzcc zzcc, ExecutorService executorService) {
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = zzan();
        this.zze = context.getApplicationContext();
        zzgt zzy2 = zzgu.zzy();
        zzy2.zzn(zzan());
        zzy2.zzm(this.zze.getPackageName());
        this.zzf = new zzch(this.zze, (zzgu) zzy2.zzf());
        zzb.zzk("BillingClient", "Billing client should have a valid listener but the provided is null.");
        this.zzd = new zzo(this.zze, (PurchasesUpdatedListener) null, (zzck) null, (zzc) null, (UserChoiceBillingListener) null, this.zzf);
        this.zzz = pendingPurchasesParams;
        this.zze.getPackageName();
    }

    BillingClientImpl(String str, PendingPurchasesParams pendingPurchasesParams, Context context, PurchasesUpdatedListener purchasesUpdatedListener, zzc zzc2, zzcc zzcc, ExecutorService executorService) {
        String zzan = zzan();
        this.zza = 0;
        this.zzc = new Handler(Looper.getMainLooper());
        this.zzk = 0;
        this.zzb = zzan;
        PurchasesUpdatedListener purchasesUpdatedListener2 = purchasesUpdatedListener;
        PendingPurchasesParams pendingPurchasesParams2 = pendingPurchasesParams;
        initialize(context, purchasesUpdatedListener2, pendingPurchasesParams2, (zzc) null, zzan, (zzcc) null);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    BillingClientImpl(java.lang.String r10, com.android.billingclient.api.PendingPurchasesParams r11, android.content.Context r12, com.android.billingclient.api.PurchasesUpdatedListener r13, com.android.billingclient.api.UserChoiceBillingListener r14, com.android.billingclient.api.zzcc r15, java.util.concurrent.ExecutorService r16) {
        /*
            r9 = this;
            java.lang.String r4 = zzan()
            r7 = 0
            r8 = 0
            r5 = 0
            r0 = r9
            r2 = r11
            r1 = r12
            r3 = r13
            r6 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.<init>(java.lang.String, com.android.billingclient.api.PendingPurchasesParams, android.content.Context, com.android.billingclient.api.PurchasesUpdatedListener, com.android.billingclient.api.UserChoiceBillingListener, com.android.billingclient.api.zzcc, java.util.concurrent.ExecutorService):void");
    }

    public final void startConnection(BillingClientStateListener billingClientStateListener) {
        if (isReady()) {
            zzb.zzj("BillingClient", "Service connection is valid. No need to re-initialize.");
            zzaq(zzcb.zzc(6));
            billingClientStateListener.onBillingSetupFinished(zzce.zzl);
            return;
        }
        int i = 1;
        if (this.zza == 1) {
            zzb.zzk("BillingClient", "Client is already in the process of connecting to billing service.");
            zzap(zzcb.zza(37, 6, zzce.zzd));
            billingClientStateListener.onBillingSetupFinished(zzce.zzd);
        } else if (this.zza == 3) {
            zzb.zzk("BillingClient", "Client was already closed and can't be reused. Please create another instance.");
            zzap(zzcb.zza(38, 6, zzce.zzm));
            billingClientStateListener.onBillingSetupFinished(zzce.zzm);
        } else {
            this.zza = 1;
            zzb.zzj("BillingClient", "Starting in-app billing setup.");
            this.zzh = new zzbc(this, billingClientStateListener, (zzbb) null);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            List<ResolveInfo> queryIntentServices = this.zze.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                i = 41;
            } else {
                ResolveInfo resolveInfo = queryIntentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    String str2 = resolveInfo.serviceInfo.name;
                    if (!"com.android.vending".equals(str) || str2 == null) {
                        zzb.zzk("BillingClient", "The device doesn't have valid Play Store.");
                        i = 40;
                    } else {
                        ComponentName componentName = new ComponentName(str, str2);
                        Intent intent2 = new Intent(intent);
                        intent2.setComponent(componentName);
                        intent2.putExtra("playBillingLibraryVersion", this.zzb);
                        if (this.zze.bindService(intent2, this.zzh, 1)) {
                            zzb.zzj("BillingClient", "Service was bonded successfully.");
                            return;
                        } else {
                            zzb.zzk("BillingClient", "Connection to Billing service is blocked.");
                            i = 39;
                        }
                    }
                }
            }
            this.zza = 0;
            zzb.zzj("BillingClient", "Billing service unavailable on device.");
            zzap(zzcb.zza(i, 6, zzce.zzc));
            billingClientStateListener.onBillingSetupFinished(zzce.zzc);
        }
    }
}
