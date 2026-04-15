package com.android.billingclient.api;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.play_billing.zzb;
import com.google.android.gms.internal.play_billing.zzhl;
import com.google.android.gms.internal.play_billing.zzr;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzbc implements ServiceConnection {
    final /* synthetic */ BillingClientImpl zza;
    private final Object zzb = new Object();
    private boolean zzc = false;
    private BillingClientStateListener zzd;

    /* synthetic */ zzbc(BillingClientImpl billingClientImpl, BillingClientStateListener billingClientStateListener, zzbb zzbb) {
        this.zza = billingClientImpl;
        this.zzd = billingClientStateListener;
    }

    private final void zzd(BillingResult billingResult) {
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingSetupFinished(billingResult);
            }
        }
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        zzb.zzj("BillingClient", "Billing service connected.");
        this.zza.zzg = zzr.zzu(iBinder);
        zzaz zzaz = new zzaz(this);
        zzba zzba = new zzba(this);
        BillingClientImpl billingClientImpl = this.zza;
        if (billingClientImpl.zzao(zzaz, 30000, zzba, billingClientImpl.zzaj()) == null) {
            BillingClientImpl billingClientImpl2 = this.zza;
            BillingResult zzi = billingClientImpl2.zzal();
            billingClientImpl2.zzap(zzcb.zza(25, 6, zzi));
            zzd(zzi);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzb.zzk("BillingClient", "Billing service disconnected.");
        this.zza.zzf.zze(zzhl.zzz());
        this.zza.zzg = null;
        this.zza.zza = 0;
        synchronized (this.zzb) {
            BillingClientStateListener billingClientStateListener = this.zzd;
            if (billingClientStateListener != null) {
                billingClientStateListener.onBillingServiceDisconnected();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x023f  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x0258  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object zza() throws java.lang.Exception {
        /*
            r14 = this;
            java.lang.Object r0 = r14.zzb
            monitor-enter(r0)
            boolean r1 = r14.zzc     // Catch:{ all -> 0x0269 }
            r2 = 0
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            goto L_0x0268
        L_0x000b:
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x001d
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "accountName"
            r0.putString(r1, r2)
            goto L_0x001e
        L_0x001d:
            r0 = r2
        L_0x001e:
            r1 = 6
            r3 = 3
            r4 = 0
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza     // Catch:{ Exception -> 0x01eb }
            android.content.Context r5 = r5.zze     // Catch:{ Exception -> 0x01eb }
            java.lang.String r5 = r5.getPackageName()     // Catch:{ Exception -> 0x01eb }
            r6 = 22
            r8 = r3
            r7 = r6
        L_0x002f:
            if (r7 < r3) goto L_0x0068
            if (r0 != 0) goto L_0x0040
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzs r9 = r9.zzg     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r10 = "subs"
            int r8 = r9.zzy(r7, r5, r10)     // Catch:{ Exception -> 0x01e8 }
            goto L_0x004c
        L_0x0040:
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzs r9 = r9.zzg     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r10 = "subs"
            int r8 = r9.zzc(r7, r5, r10, r0)     // Catch:{ Exception -> 0x01e8 }
        L_0x004c:
            if (r8 != 0) goto L_0x0065
            java.lang.String r9 = "BillingClient"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e8 }
            r10.<init>()     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r11 = "highestLevelSupportedForSubs: "
            r10.append(r11)     // Catch:{ Exception -> 0x01e8 }
            r10.append(r7)     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzb.zzj(r9, r10)     // Catch:{ Exception -> 0x01e8 }
            goto L_0x0069
        L_0x0065:
            int r7 = r7 + -1
            goto L_0x002f
        L_0x0068:
            r7 = r4
        L_0x0069:
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            r10 = 5
            r11 = 1
            if (r7 < r10) goto L_0x0071
            r10 = r11
            goto L_0x0072
        L_0x0071:
            r10 = r4
        L_0x0072:
            r9.zzj = r10     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r9 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            if (r7 < r3) goto L_0x007b
            r10 = r11
            goto L_0x007c
        L_0x007b:
            r10 = r4
        L_0x007c:
            r9.zzi = r10     // Catch:{ Exception -> 0x01e8 }
            r9 = 9
            if (r7 >= r3) goto L_0x008c
            java.lang.String r7 = "BillingClient"
            java.lang.String r10 = "In-app billing API does not support subscription on this device."
            com.google.android.gms.internal.play_billing.zzb.zzj(r7, r10)     // Catch:{ Exception -> 0x01e8 }
            r7 = r9
            goto L_0x008d
        L_0x008c:
            r7 = r11
        L_0x008d:
            r10 = r6
        L_0x008e:
            if (r10 < r3) goto L_0x00d2
            if (r0 != 0) goto L_0x009f
            com.android.billingclient.api.BillingClientImpl r12 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzs r12 = r12.zzg     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r13 = "inapp"
            int r8 = r12.zzy(r10, r5, r13)     // Catch:{ Exception -> 0x01e8 }
            goto L_0x00ab
        L_0x009f:
            com.android.billingclient.api.BillingClientImpl r12 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzs r12 = r12.zzg     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r13 = "inapp"
            int r8 = r12.zzc(r10, r5, r13, r0)     // Catch:{ Exception -> 0x01e8 }
        L_0x00ab:
            if (r8 != 0) goto L_0x00cf
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            r0.zzk = r10     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r0 = "BillingClient"
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r5.zzk     // Catch:{ Exception -> 0x01e8 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e8 }
            r10.<init>()     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r12 = "mHighestLevelSupportedForInApp: "
            r10.append(r12)     // Catch:{ Exception -> 0x01e8 }
            r10.append(r5)     // Catch:{ Exception -> 0x01e8 }
            java.lang.String r5 = r10.toString()     // Catch:{ Exception -> 0x01e8 }
            com.google.android.gms.internal.play_billing.zzb.zzj(r0, r5)     // Catch:{ Exception -> 0x01e8 }
            goto L_0x00d2
        L_0x00cf:
            int r10 = r10 + -1
            goto L_0x008e
        L_0x00d2:
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            if (r5 < r6) goto L_0x00dc
            r5 = r11
            goto L_0x00dd
        L_0x00dc:
            r5 = r4
        L_0x00dd:
            r0.zzy = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 21
            if (r5 < r6) goto L_0x00ec
            r5 = r11
            goto L_0x00ed
        L_0x00ec:
            r5 = r4
        L_0x00ed:
            r0.zzx = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 20
            if (r5 < r6) goto L_0x00fc
            r5 = r11
            goto L_0x00fd
        L_0x00fc:
            r5 = r4
        L_0x00fd:
            r0.zzw = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 19
            if (r5 < r6) goto L_0x010c
            r5 = r11
            goto L_0x010d
        L_0x010c:
            r5 = r4
        L_0x010d:
            r0.zzv = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 18
            if (r5 < r6) goto L_0x011c
            r5 = r11
            goto L_0x011d
        L_0x011c:
            r5 = r4
        L_0x011d:
            r0.zzu = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 17
            if (r5 < r6) goto L_0x012c
            r5 = r11
            goto L_0x012d
        L_0x012c:
            r5 = r4
        L_0x012d:
            r0.zzt = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 16
            if (r5 < r6) goto L_0x013c
            r5 = r11
            goto L_0x013d
        L_0x013c:
            r5 = r4
        L_0x013d:
            r0.zzs = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 15
            if (r5 < r6) goto L_0x014c
            r5 = r11
            goto L_0x014d
        L_0x014c:
            r5 = r4
        L_0x014d:
            r0.zzr = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 14
            if (r5 < r6) goto L_0x015c
            r5 = r11
            goto L_0x015d
        L_0x015c:
            r5 = r4
        L_0x015d:
            r0.zzq = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 12
            if (r5 < r6) goto L_0x016c
            r5 = r11
            goto L_0x016d
        L_0x016c:
            r5 = r4
        L_0x016d:
            r0.zzp = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 10
            if (r5 < r6) goto L_0x017c
            r5 = r11
            goto L_0x017d
        L_0x017c:
            r5 = r4
        L_0x017d:
            r0.zzo = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            if (r5 < r9) goto L_0x018a
            r5 = r11
            goto L_0x018b
        L_0x018a:
            r5 = r4
        L_0x018b:
            r0.zzn = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            r6 = 8
            if (r5 < r6) goto L_0x019a
            r5 = r11
            goto L_0x019b
        L_0x019a:
            r5 = r4
        L_0x019b:
            r0.zzm = r5     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r5 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            if (r5 < r1) goto L_0x01a7
            goto L_0x01a8
        L_0x01a7:
            r11 = r4
        L_0x01a8:
            r0.zzl = r11     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            int r0 = r0.zzk     // Catch:{ Exception -> 0x01e8 }
            if (r0 >= r3) goto L_0x01bc
            java.lang.String r0 = "BillingClient"
            java.lang.String r3 = "In-app billing API version 3 is not supported on this device."
            com.google.android.gms.internal.play_billing.zzb.zzk(r0, r3)     // Catch:{ Exception -> 0x01e8 }
            r7 = 36
        L_0x01bc:
            if (r8 != 0) goto L_0x01dc
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            r3 = 2
            r0.zza = r3     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.zzo r0 = r0.zzd     // Catch:{ Exception -> 0x01e8 }
            if (r0 == 0) goto L_0x01e6
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.zzo r0 = r0.zzd     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r3 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            boolean r3 = r3.zzx     // Catch:{ Exception -> 0x01e8 }
            r0.zzg(r3)     // Catch:{ Exception -> 0x01e8 }
            goto L_0x01e6
        L_0x01dc:
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            r0.zza = r4     // Catch:{ Exception -> 0x01e8 }
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza     // Catch:{ Exception -> 0x01e8 }
            r0.zzg = r2     // Catch:{ Exception -> 0x01e8 }
        L_0x01e6:
            r0 = r2
            goto L_0x024b
        L_0x01e8:
            r0 = move-exception
            r3 = r8
            goto L_0x01ec
        L_0x01eb:
            r0 = move-exception
        L_0x01ec:
            java.lang.String r5 = "BillingClient"
            java.lang.String r6 = "Exception while checking if billing is supported; try to reconnect"
            com.google.android.gms.internal.play_billing.zzb.zzl(r5, r6, r0)
            boolean r5 = r0 instanceof android.os.DeadObjectException
            r6 = 42
            if (r5 == 0) goto L_0x01fd
            r5 = 101(0x65, float:1.42E-43)
        L_0x01fb:
            r7 = r5
            goto L_0x020c
        L_0x01fd:
            boolean r5 = r0 instanceof android.os.RemoteException
            if (r5 == 0) goto L_0x0204
            r5 = 100
            goto L_0x01fb
        L_0x0204:
            boolean r5 = r0 instanceof java.lang.SecurityException
            if (r5 == 0) goto L_0x020b
            r5 = 102(0x66, float:1.43E-43)
            goto L_0x01fb
        L_0x020b:
            r7 = r6
        L_0x020c:
            if (r7 != r6) goto L_0x023f
            java.lang.Class r5 = r0.getClass()
            java.lang.String r5 = r5.getSimpleName()
            java.lang.String r0 = r0.getMessage()
            java.lang.String r0 = com.google.android.gms.internal.play_billing.zzab.zzb(r0)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r5)
            java.lang.String r5 = ": "
            r6.append(r5)
            r6.append(r0)
            java.lang.String r0 = r6.toString()
            int r5 = r0.length()
            r6 = 70
            if (r5 <= r6) goto L_0x0240
            java.lang.String r0 = r0.substring(r4, r6)
            goto L_0x0240
        L_0x023f:
            r0 = r2
        L_0x0240:
            com.android.billingclient.api.BillingClientImpl r5 = r14.zza
            r5.zza = r4
            com.android.billingclient.api.BillingClientImpl r4 = r14.zza
            r4.zzg = r2
            r8 = r3
        L_0x024b:
            if (r8 != 0) goto L_0x0258
            com.android.billingclient.api.BillingClientImpl r0 = r14.zza
            r0.zzaq(com.android.billingclient.api.zzcb.zzc(6))
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zzl
            r14.zzd(r0)
            goto L_0x0268
        L_0x0258:
            com.android.billingclient.api.BillingClientImpl r3 = r14.zza
            com.android.billingclient.api.BillingResult r4 = com.android.billingclient.api.zzce.zza
            com.google.android.gms.internal.play_billing.zzga r0 = com.android.billingclient.api.zzcb.zzb(r7, r1, r4, r0)
            r3.zzap(r0)
            com.android.billingclient.api.BillingResult r0 = com.android.billingclient.api.zzce.zza
            r14.zzd(r0)
        L_0x0268:
            return r2
        L_0x0269:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0269 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.zzbc.zza():java.lang.Object");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzb() {
        this.zza.zza = 0;
        this.zza.zzg = null;
        this.zza.zzap(zzcb.zza(24, 6, zzce.zzn));
        zzd(zzce.zzn);
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        synchronized (this.zzb) {
            this.zzd = null;
            this.zzc = true;
        }
    }
}
