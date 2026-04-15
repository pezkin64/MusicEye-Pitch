package com.google.android.gms.common.internal;

import java.util.concurrent.ConcurrentHashMap;

@Deprecated
/* compiled from: com.google.android.gms:play-services-basement@@18.3.0 */
public class LibraryVersion {
    private static final GmsLogger zza = new GmsLogger("LibraryVersion", "");
    private static final LibraryVersion zzb = new LibraryVersion();
    private final ConcurrentHashMap zzc = new ConcurrentHashMap();

    protected LibraryVersion() {
    }

    public static LibraryVersion getInstance() {
        return zzb;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v8, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v12, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r4v1, types: [java.io.Closeable] */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00a1  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getVersion(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "LibraryVersion"
            java.lang.String r1 = "Failed to get app version for libraryName: "
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r9, r2)
            java.util.concurrent.ConcurrentHashMap r2 = r8.zzc
            boolean r2 = r2.containsKey(r9)
            if (r2 == 0) goto L_0x001a
            java.util.concurrent.ConcurrentHashMap r0 = r8.zzc
            java.lang.Object r9 = r0.get(r9)
            java.lang.String r9 = (java.lang.String) r9
            return r9
        L_0x001a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ IOException -> 0x0073 }
            r6 = 0
            r5[r6] = r9     // Catch:{ IOException -> 0x0073 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ IOException -> 0x0073 }
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch:{ IOException -> 0x0073 }
            if (r4 == 0) goto L_0x0057
            r2.load(r4)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r5.<init>()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r5.append(r9)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r6 = " version is "
            r5.append(r6)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r5.append(r3)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r2.v(r0, r5)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            goto L_0x0089
        L_0x0057:
            com.google.android.gms.common.internal.GmsLogger r2 = zza     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r5.<init>(r1)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r5.append(r9)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            r2.w(r0, r5)     // Catch:{ IOException -> 0x006c, all -> 0x0069 }
            goto L_0x0089
        L_0x0069:
            r9 = move-exception
            r3 = r4
            goto L_0x009f
        L_0x006c:
            r2 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L_0x0075
        L_0x0071:
            r9 = move-exception
            goto L_0x009f
        L_0x0073:
            r2 = move-exception
            r4 = r3
        L_0x0075:
            com.google.android.gms.common.internal.GmsLogger r5 = zza     // Catch:{ all -> 0x0071 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x0071 }
            r6.<init>(r1)     // Catch:{ all -> 0x0071 }
            r6.append(r9)     // Catch:{ all -> 0x0071 }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x0071 }
            r5.e(r0, r1, r2)     // Catch:{ all -> 0x0071 }
            r7 = r4
            r4 = r3
            r3 = r7
        L_0x0089:
            if (r4 == 0) goto L_0x008e
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r4)
        L_0x008e:
            if (r3 != 0) goto L_0x0099
            com.google.android.gms.common.internal.GmsLogger r1 = zza
            java.lang.String r2 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r1.d(r0, r2)
            java.lang.String r3 = "UNKNOWN"
        L_0x0099:
            java.util.concurrent.ConcurrentHashMap r0 = r8.zzc
            r0.put(r9, r3)
            return r3
        L_0x009f:
            if (r3 == 0) goto L_0x00a4
            com.google.android.gms.common.util.IOUtils.closeQuietly((java.io.Closeable) r3)
        L_0x00a4:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
