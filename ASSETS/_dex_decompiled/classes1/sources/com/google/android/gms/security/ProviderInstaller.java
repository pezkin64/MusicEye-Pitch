package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.reflect.Method;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class ProviderInstaller {
    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    /* access modifiers changed from: private */
    public static final GoogleApiAvailabilityLight zza = GoogleApiAvailabilityLight.getInstance();
    private static final Object zzb = new Object();
    private static Method zzc = null;
    private static Method zzd = null;

    /* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int i, Intent intent);

        void onProviderInstalled();
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void installIfNeeded(android.content.Context r15) throws com.google.android.gms.common.GooglePlayServicesRepairableException, com.google.android.gms.common.GooglePlayServicesNotAvailableException {
        /*
            java.lang.String r0 = "Context must not be null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r15, r0)
            com.google.android.gms.common.GoogleApiAvailabilityLight r0 = zza
            r1 = 11925000(0xb5f608, float:1.6710484E-38)
            r0.verifyGooglePlayServicesIsAvailable(r15, r1)
            java.lang.Object r0 = zzb
            monitor-enter(r0)
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00aa }
            r3 = 0
            com.google.android.gms.dynamite.DynamiteModule$VersionPolicy r4 = com.google.android.gms.dynamite.DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING     // Catch:{ LoadingException -> 0x0022 }
            java.lang.String r5 = "com.google.android.gms.providerinstaller.dynamite"
            com.google.android.gms.dynamite.DynamiteModule r4 = com.google.android.gms.dynamite.DynamiteModule.load(r15, r4, r5)     // Catch:{ LoadingException -> 0x0022 }
            android.content.Context r4 = r4.getModuleContext()     // Catch:{ LoadingException -> 0x0022 }
            goto L_0x0037
        L_0x0022:
            r4 = move-exception
            java.lang.String r5 = "ProviderInstaller"
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x00aa }
            java.lang.String r6 = "Failed to load providerinstaller module: "
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ all -> 0x00aa }
            java.lang.String r4 = r6.concat(r4)     // Catch:{ all -> 0x00aa }
            android.util.Log.w(r5, r4)     // Catch:{ all -> 0x00aa }
            r4 = r3
        L_0x0037:
            if (r4 == 0) goto L_0x0040
            java.lang.String r1 = "com.google.android.gms.providerinstaller.ProviderInstallerImpl"
            zzc(r4, r15, r1)     // Catch:{ all -> 0x00aa }
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            goto L_0x009a
        L_0x0040:
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ all -> 0x00aa }
            android.content.Context r6 = com.google.android.gms.common.GooglePlayServicesUtilLight.getRemoteContext(r15)     // Catch:{ all -> 0x00aa }
            if (r6 == 0) goto L_0x0092
            java.lang.reflect.Method r7 = zzd     // Catch:{ Exception -> 0x007e }
            r8 = 2
            r9 = 1
            r10 = 0
            r11 = 3
            if (r7 != 0) goto L_0x0068
            java.lang.String r7 = "com.google.android.gms.common.security.ProviderInstallerImpl"
            java.lang.String r12 = "reportRequestStats"
            java.lang.Class[] r13 = new java.lang.Class[r11]     // Catch:{ Exception -> 0x007e }
            java.lang.Class<android.content.Context> r14 = android.content.Context.class
            r13[r10] = r14     // Catch:{ Exception -> 0x007e }
            java.lang.Class r14 = java.lang.Long.TYPE     // Catch:{ Exception -> 0x007e }
            r13[r9] = r14     // Catch:{ Exception -> 0x007e }
            r13[r8] = r14     // Catch:{ Exception -> 0x007e }
            java.lang.reflect.Method r7 = zzb(r6, r7, r12, r13)     // Catch:{ Exception -> 0x007e }
            zzd = r7     // Catch:{ Exception -> 0x007e }
        L_0x0068:
            java.lang.reflect.Method r7 = zzd     // Catch:{ Exception -> 0x007e }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ Exception -> 0x007e }
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x007e }
            java.lang.Object[] r4 = new java.lang.Object[r11]     // Catch:{ Exception -> 0x007e }
            r4[r10] = r15     // Catch:{ Exception -> 0x007e }
            r4[r9] = r1     // Catch:{ Exception -> 0x007e }
            r4[r8] = r2     // Catch:{ Exception -> 0x007e }
            r7.invoke(r3, r4)     // Catch:{ Exception -> 0x007e }
            goto L_0x0092
        L_0x007e:
            r1 = move-exception
            java.lang.String r2 = "ProviderInstaller"
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x00aa }
            java.lang.String r3 = "Failed to report request stats: "
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch:{ all -> 0x00aa }
            java.lang.String r1 = r3.concat(r1)     // Catch:{ all -> 0x00aa }
            android.util.Log.w(r2, r1)     // Catch:{ all -> 0x00aa }
        L_0x0092:
            if (r6 == 0) goto L_0x009b
            java.lang.String r1 = "com.google.android.gms.common.security.ProviderInstallerImpl"
            zzc(r6, r15, r1)     // Catch:{ all -> 0x00aa }
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
        L_0x009a:
            return
        L_0x009b:
            java.lang.String r15 = "ProviderInstaller"
            java.lang.String r1 = "Failed to get remote context"
            android.util.Log.e(r15, r1)     // Catch:{ all -> 0x00aa }
            com.google.android.gms.common.GooglePlayServicesNotAvailableException r15 = new com.google.android.gms.common.GooglePlayServicesNotAvailableException     // Catch:{ all -> 0x00aa }
            r1 = 8
            r15.<init>(r1)     // Catch:{ all -> 0x00aa }
            throw r15     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r15 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00aa }
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.security.ProviderInstaller.installIfNeeded(android.content.Context):void");
    }

    public static void installIfNeededAsync(Context context, ProviderInstallListener providerInstallListener) {
        Preconditions.checkNotNull(context, "Context must not be null");
        Preconditions.checkNotNull(providerInstallListener, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context, providerInstallListener).execute(new Void[0]);
    }

    private static Method zzb(Context context, String str, String str2, Class[] clsArr) throws ClassNotFoundException, NoSuchMethodException {
        return context.getClassLoader().loadClass(str).getMethod(str2, clsArr);
    }

    private static void zzc(Context context, Context context2, String str) throws GooglePlayServicesNotAvailableException {
        try {
            if (zzc == null) {
                zzc = zzb(context, str, "insertProvider", new Class[]{Context.class});
            }
            zzc.invoke((Object) null, new Object[]{context});
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (Log.isLoggable("ProviderInstaller", 6)) {
                Log.e("ProviderInstaller", "Failed to install provider: ".concat(String.valueOf(cause == null ? e.getMessage() : cause.getMessage())));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}
