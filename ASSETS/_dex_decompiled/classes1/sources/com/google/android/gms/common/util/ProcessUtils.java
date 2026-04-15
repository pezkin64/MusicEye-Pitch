package com.google.android.gms.common.util;

import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class ProcessUtils {
    @Nullable
    private static String zza;
    private static int zzb;

    private ProcessUtils() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v7 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getMyProcessName() {
        /*
            java.lang.String r0 = "/proc/"
            java.lang.String r1 = zza
            if (r1 != 0) goto L_0x0062
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 < r2) goto L_0x0013
            java.lang.String r0 = androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0.m()
            zza = r0
            goto L_0x0062
        L_0x0013:
            int r1 = zzb
            if (r1 != 0) goto L_0x001d
            int r1 = android.os.Process.myPid()
            zzb = r1
        L_0x001d:
            r2 = 0
            if (r1 > 0) goto L_0x0021
            goto L_0x0060
        L_0x0021:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            r3.append(r1)     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            java.lang.String r0 = "/cmdline"
            r3.append(r0)     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            java.lang.String r0 = r3.toString()     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            android.os.StrictMode$ThreadPolicy r1 = android.os.StrictMode.allowThreadDiskReads()     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch:{ all -> 0x0052 }
            java.io.FileReader r4 = new java.io.FileReader     // Catch:{ all -> 0x0052 }
            r4.<init>(r0)     // Catch:{ all -> 0x0052 }
            r3.<init>(r4)     // Catch:{ all -> 0x0052 }
            android.os.StrictMode.setThreadPolicy(r1)     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            java.lang.String r0 = r3.readLine()     // Catch:{ IOException -> 0x005d, all -> 0x004f }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ IOException -> 0x005d, all -> 0x004f }
            java.lang.String r2 = r0.trim()     // Catch:{ IOException -> 0x005d, all -> 0x004f }
            goto L_0x005d
        L_0x004f:
            r0 = move-exception
            r2 = r3
            goto L_0x0058
        L_0x0052:
            r0 = move-exception
            android.os.StrictMode.setThreadPolicy(r1)     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
            throw r0     // Catch:{ IOException -> 0x005c, all -> 0x0057 }
        L_0x0057:
            r0 = move-exception
        L_0x0058:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r2)
            throw r0
        L_0x005c:
            r3 = r2
        L_0x005d:
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
        L_0x0060:
            zza = r2
        L_0x0062:
            java.lang.String r0 = zza
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.ProcessUtils.getMyProcessName():java.lang.String");
    }
}
