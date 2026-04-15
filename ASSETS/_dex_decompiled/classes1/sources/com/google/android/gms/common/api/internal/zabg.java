package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@18.3.0 */
abstract class zabg {
    private final zabf zaa;

    protected zabg(zabf zabf) {
        this.zaa = zabf;
    }

    /* access modifiers changed from: protected */
    public abstract void zaa();

    public final void zab(zabi zabi) {
        zabi.zai.lock();
        try {
            if (zabi.zan == this.zaa) {
                zaa();
            }
        } finally {
            zabi.zai.unlock();
        }
    }
}
