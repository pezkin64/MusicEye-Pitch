package com.android.billingclient.api;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final class QueryPurchasesParams {
    private final String zza;

    /* compiled from: com.android.billingclient:billing@@7.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public String zza;

        private Builder() {
            throw null;
        }

        /* synthetic */ Builder(zzdi zzdi) {
        }

        public QueryPurchasesParams build() {
            if (this.zza != null) {
                return new QueryPurchasesParams(this, (zzdj) null);
            }
            throw new IllegalArgumentException("Product type must be set");
        }

        public Builder setProductType(String str) {
            this.zza = str;
            return this;
        }
    }

    /* synthetic */ QueryPurchasesParams(Builder builder, zzdj zzdj) {
        this.zza = builder.zza;
    }

    public static Builder newBuilder() {
        return new Builder((zzdi) null);
    }

    public final String zza() {
        return this.zza;
    }
}
