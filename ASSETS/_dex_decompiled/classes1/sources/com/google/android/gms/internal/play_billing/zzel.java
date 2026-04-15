package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzel {
    private static final zzel zza = new zzel();
    private final zzep zzb = new zzdu();
    private final ConcurrentMap zzc = new ConcurrentHashMap();

    private zzel() {
    }

    public static zzel zza() {
        return zza;
    }

    public final zzeo zzb(Class cls) {
        zzda.zzc(cls, "messageType");
        zzeo zzeo = (zzeo) this.zzc.get(cls);
        if (zzeo != null) {
            return zzeo;
        }
        zzeo zza2 = this.zzb.zza(cls);
        zzda.zzc(cls, "messageType");
        zzeo zzeo2 = (zzeo) this.zzc.putIfAbsent(cls, zza2);
        return zzeo2 == null ? zza2 : zzeo2;
    }
}
