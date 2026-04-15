package com.android.billingclient.api;

import java.util.Objects;
import org.json.JSONObject;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final class zze {
    private final String zza;
    private final String zzb;
    private final String zzc;

    /* synthetic */ zze(JSONObject jSONObject, zzd zzd) {
        this.zza = jSONObject.optString("productId");
        this.zzb = jSONObject.optString("productType");
        String optString = jSONObject.optString("offerToken");
        this.zzc = true == optString.isEmpty() ? null : optString;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        zze zze = (zze) obj;
        return this.zza.equals(zze.zza) && this.zzb.equals(zze.zzb) && Objects.equals(this.zzc, zze.zzc);
    }

    public final int hashCode() {
        return Objects.hash(new Object[]{this.zza, this.zzb, this.zzc});
    }

    public final String toString() {
        return String.format("{id: %s, type: %s, offer token: %s}", new Object[]{this.zza, this.zzb, this.zzc});
    }
}
