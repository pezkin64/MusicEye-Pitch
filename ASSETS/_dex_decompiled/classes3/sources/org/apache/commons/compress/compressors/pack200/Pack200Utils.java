package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Pack200Utils {
    private Pack200Utils() {
    }

    public static void normalize(File file) throws IOException {
        normalize(file, file, (Map<String, String>) null);
    }

    public static void normalize(File file, Map<String, String> map) throws IOException {
        normalize(file, file, map);
    }

    public static void normalize(File file, File file2) throws IOException {
        normalize(file, file2, (Map<String, String>) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0061, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006a, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0072, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        r5.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0076, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0079, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x007a, code lost:
        if (r1 != null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0084, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void normalize(java.io.File r5, java.io.File r6, java.util.Map<java.lang.String, java.lang.String> r7) throws java.io.IOException {
        /*
            if (r7 != 0) goto L_0x0007
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
        L_0x0007:
            java.lang.String r0 = "pack.segment.limit"
            java.lang.String r1 = "-1"
            r7.put(r0, r1)
            java.lang.String r0 = "commons-compress"
            java.lang.String r1 = "pack200normalize"
            java.io.File r0 = java.io.File.createTempFile(r0, r1)
            java.nio.file.Path r1 = r0.toPath()     // Catch:{ all -> 0x0085 }
            r2 = 0
            java.nio.file.OpenOption[] r3 = new java.nio.file.OpenOption[r2]     // Catch:{ all -> 0x0085 }
            java.io.OutputStream r1 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r1, (java.nio.file.OpenOption[]) r3)     // Catch:{ all -> 0x0085 }
            java.util.jar.JarFile r3 = new java.util.jar.JarFile     // Catch:{ all -> 0x0077 }
            r3.<init>(r5)     // Catch:{ all -> 0x0077 }
            org.apache.commons.compress.java.util.jar.Pack200$Packer r5 = org.apache.commons.compress.java.util.jar.Pack200.newPacker()     // Catch:{ all -> 0x006b }
            java.util.SortedMap r4 = r5.properties()     // Catch:{ all -> 0x006b }
            r4.putAll(r7)     // Catch:{ all -> 0x006b }
            r5.pack((java.util.jar.JarFile) r3, (java.io.OutputStream) r1)     // Catch:{ all -> 0x006b }
            r3.close()     // Catch:{ all -> 0x0077 }
            if (r1 == 0) goto L_0x003c
            r1.close()     // Catch:{ all -> 0x0085 }
        L_0x003c:
            org.apache.commons.compress.java.util.jar.Pack200$Unpacker r5 = org.apache.commons.compress.java.util.jar.Pack200.newUnpacker()     // Catch:{ all -> 0x0085 }
            java.util.jar.JarOutputStream r7 = new java.util.jar.JarOutputStream     // Catch:{ all -> 0x0085 }
            java.nio.file.Path r6 = r6.toPath()     // Catch:{ all -> 0x0085 }
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r2]     // Catch:{ all -> 0x0085 }
            java.io.OutputStream r6 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r6, (java.nio.file.OpenOption[]) r1)     // Catch:{ all -> 0x0085 }
            r7.<init>(r6)     // Catch:{ all -> 0x0085 }
            r5.unpack((java.io.File) r0, (java.util.jar.JarOutputStream) r7)     // Catch:{ all -> 0x005f }
            r7.close()     // Catch:{ all -> 0x0085 }
            boolean r5 = r0.delete()
            if (r5 != 0) goto L_0x005e
            r0.deleteOnExit()
        L_0x005e:
            return
        L_0x005f:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0061 }
        L_0x0061:
            r6 = move-exception
            r7.close()     // Catch:{ all -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch:{ all -> 0x0085 }
        L_0x006a:
            throw r6     // Catch:{ all -> 0x0085 }
        L_0x006b:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x006d }
        L_0x006d:
            r6 = move-exception
            r3.close()     // Catch:{ all -> 0x0072 }
            goto L_0x0076
        L_0x0072:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch:{ all -> 0x0077 }
        L_0x0076:
            throw r6     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r6 = move-exception
            if (r1 == 0) goto L_0x0084
            r1.close()     // Catch:{ all -> 0x0080 }
            goto L_0x0084
        L_0x0080:
            r7 = move-exception
            r5.addSuppressed(r7)     // Catch:{ all -> 0x0085 }
        L_0x0084:
            throw r6     // Catch:{ all -> 0x0085 }
        L_0x0085:
            r5 = move-exception
            boolean r6 = r0.delete()
            if (r6 != 0) goto L_0x008f
            r0.deleteOnExit()
        L_0x008f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.compressors.pack200.Pack200Utils.normalize(java.io.File, java.io.File, java.util.Map):void");
    }
}
