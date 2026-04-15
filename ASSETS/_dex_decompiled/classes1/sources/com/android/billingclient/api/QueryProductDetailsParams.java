package com.android.billingclient.api;

import com.google.android.gms.internal.play_billing.zzai;
import java.util.HashSet;
import java.util.List;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public final class QueryProductDetailsParams {
    private final zzai zza;

    /* compiled from: com.android.billingclient:billing@@7.0.0 */
    public static class Builder {
        /* access modifiers changed from: private */
        public zzai zza;

        private Builder() {
            throw null;
        }

        /* synthetic */ Builder(zzdc zzdc) {
        }

        public QueryProductDetailsParams build() {
            return new QueryProductDetailsParams(this, (zzdf) null);
        }

        public Builder setProductList(List<Product> list) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Product list cannot be empty.");
            }
            HashSet hashSet = new HashSet();
            for (Product next : list) {
                if (!"play_pass_subs".equals(next.zzb())) {
                    hashSet.add(next.zzb());
                }
            }
            if (hashSet.size() <= 1) {
                this.zza = zzai.zzj(list);
                return this;
            }
            throw new IllegalArgumentException("All products should be of the same product type.");
        }
    }

    /* compiled from: com.android.billingclient:billing@@7.0.0 */
    public static class Product {
        private final String zza;
        private final String zzb;

        /* compiled from: com.android.billingclient:billing@@7.0.0 */
        public static class Builder {
            /* access modifiers changed from: private */
            public String zza;
            /* access modifiers changed from: private */
            public String zzb;

            private Builder() {
                throw null;
            }

            /* synthetic */ Builder(zzdd zzdd) {
            }

            public Product build() {
                if ("first_party".equals(this.zzb)) {
                    throw new IllegalArgumentException("Serialized doc id must be provided for first party products.");
                } else if (this.zza == null) {
                    throw new IllegalArgumentException("Product id must be provided.");
                } else if (this.zzb != null) {
                    return new Product(this, (zzde) null);
                } else {
                    throw new IllegalArgumentException("Product type must be provided.");
                }
            }

            public Builder setProductId(String str) {
                this.zza = str;
                return this;
            }

            public Builder setProductType(String str) {
                this.zzb = str;
                return this;
            }
        }

        /* synthetic */ Product(Builder builder, zzde zzde) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
        }

        public static Builder newBuilder() {
            return new Builder((zzdd) null);
        }

        public final String zza() {
            return this.zza;
        }

        public final String zzb() {
            return this.zzb;
        }
    }

    /* synthetic */ QueryProductDetailsParams(Builder builder, zzdf zzdf) {
        this.zza = builder.zza;
    }

    public static Builder newBuilder() {
        return new Builder((zzdc) null);
    }

    public final zzai zza() {
        return this.zza;
    }

    public final String zzb() {
        return ((Product) this.zza.get(0)).zzb();
    }
}
