package com.google.android.gms.common.api.internal;

/* compiled from: com.google.android.gms:play-services-base@@18.3.0 */
abstract class zaav implements Runnable {
    final /* synthetic */ zaaw zab;

    /* synthetic */ zaav(zaaw zaaw, zaau zaau) {
        this.zab = zaaw;
    }

    public final void run() {
        this.zab.zab.lock();
        try {
            if (!Thread.interrupted()) {
                zaa();
            }
        } catch (RuntimeException e) {
            this.zab.zaa.zam(e);
        } catch (Throwable th) {
            this.zab.zab.unlock();
            throw th;
        }
        this.zab.zab.unlock();
    }

    /* access modifiers changed from: protected */
    public abstract void zaa();
}
