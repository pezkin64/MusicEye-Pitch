package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarOutputStream;

public class Archive {
    private boolean deflateHint;
    private String inputFileName;
    private InputStream inputStream;
    private FileOutputStream logFile;
    private int logLevel = 1;
    private String outputFileName;
    private final JarOutputStream outputStream;
    private boolean overrideDeflateHint;
    private boolean removePackFile;

    public Archive(String str, String str2) throws FileNotFoundException, IOException {
        this.inputFileName = str;
        this.outputFileName = str2;
        this.inputStream = new FileInputStream(str);
        this.outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(str2)));
    }

    public Archive(InputStream inputStream2, JarOutputStream jarOutputStream) throws IOException {
        this.inputStream = inputStream2;
        this.outputStream = jarOutputStream;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|(2:4|(1:6)(2:7|8))|9|(1:11)(1:12)|13|(1:15)|79|16|(4:19|(2:21|81)(1:82)|22|17)|80|23|(2:25|(2:26|(4:28|(2:31|29)|83|32)))(2:33|(10:36|(1:38)(1:39)|40|(1:42)|43|(1:45)|46|(2:48|86)(1:85)|84|34))|49|50|51|52|53|(2:56|57)|58|(3:61|(1:63)|(1:88)(2:65|66))(1:87)) */
    /* JADX WARNING: Can't wrap try/catch for region: R(8:67|68|69|70|71|72|(2:75|76)|77) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:51:0x0146 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:70:0x0177 */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x014f A[SYNTHETIC, Splitter:B:56:0x014f] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0180 A[SYNTHETIC, Splitter:B:75:0x0180] */
    /* JADX WARNING: Removed duplicated region for block: B:87:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unpack() throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r10 = this;
            java.util.jar.JarOutputStream r0 = r10.outputStream
            java.lang.String r1 = "PACK200"
            r0.setComment(r1)
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            boolean r0 = r0.markSupported()     // Catch:{ all -> 0x0171 }
            if (r0 != 0) goto L_0x0025
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0171 }
            java.io.InputStream r1 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r0.<init>(r1)     // Catch:{ all -> 0x0171 }
            r10.inputStream = r0     // Catch:{ all -> 0x0171 }
            boolean r0 = r0.markSupported()     // Catch:{ all -> 0x0171 }
            if (r0 == 0) goto L_0x001f
            goto L_0x0025
        L_0x001f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0171 }
            r0.<init>()     // Catch:{ all -> 0x0171 }
            throw r0     // Catch:{ all -> 0x0171 }
        L_0x0025:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r1 = 2
            r0.mark(r1)     // Catch:{ all -> 0x0171 }
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            int r0 = r0.read()     // Catch:{ all -> 0x0171 }
            r0 = r0 & 255(0xff, float:3.57E-43)
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x0171 }
            int r2 = r2.read()     // Catch:{ all -> 0x0171 }
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            r0 = r0 | r2
            r2 = 35615(0x8b1f, float:4.9907E-41)
            if (r0 != r2) goto L_0x0057
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r0.reset()     // Catch:{ all -> 0x0171 }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0171 }
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch:{ all -> 0x0171 }
            java.io.InputStream r3 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r2.<init>(r3)     // Catch:{ all -> 0x0171 }
            r0.<init>(r2)     // Catch:{ all -> 0x0171 }
            r10.inputStream = r0     // Catch:{ all -> 0x0171 }
            goto L_0x005c
        L_0x0057:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r0.reset()     // Catch:{ all -> 0x0171 }
        L_0x005c:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r2 = 4
            r0.mark(r2)     // Catch:{ all -> 0x0171 }
            r0 = 208(0xd0, float:2.91E-43)
            r3 = 13
            r4 = 202(0xca, float:2.83E-43)
            r5 = 254(0xfe, float:3.56E-43)
            int[] r0 = new int[]{r4, r5, r0, r3}     // Catch:{ all -> 0x0171 }
            int[] r3 = new int[r2]     // Catch:{ all -> 0x0171 }
            r4 = 0
            r5 = r4
        L_0x0072:
            if (r5 >= r2) goto L_0x007f
            java.io.InputStream r6 = r10.inputStream     // Catch:{ all -> 0x0171 }
            int r6 = r6.read()     // Catch:{ all -> 0x0171 }
            r3[r5] = r6     // Catch:{ all -> 0x0171 }
            int r5 = r5 + 1
            goto L_0x0072
        L_0x007f:
            r5 = r4
            r6 = r5
        L_0x0081:
            r7 = 1
            if (r5 >= r2) goto L_0x008e
            r8 = r3[r5]     // Catch:{ all -> 0x0171 }
            r9 = r0[r5]     // Catch:{ all -> 0x0171 }
            if (r8 == r9) goto L_0x008b
            r6 = r7
        L_0x008b:
            int r5 = r5 + 1
            goto L_0x0081
        L_0x008e:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r0.reset()     // Catch:{ all -> 0x0171 }
            if (r6 == 0) goto L_0x00c2
            java.util.jar.JarInputStream r0 = new java.util.jar.JarInputStream     // Catch:{ all -> 0x0171 }
            java.io.InputStream r1 = r10.inputStream     // Catch:{ all -> 0x0171 }
            r0.<init>(r1)     // Catch:{ all -> 0x0171 }
        L_0x009c:
            java.util.jar.JarEntry r1 = r0.getNextJarEntry()     // Catch:{ all -> 0x0171 }
            if (r1 == 0) goto L_0x0141
            java.util.jar.JarOutputStream r2 = r10.outputStream     // Catch:{ all -> 0x0171 }
            r2.putNextEntry(r1)     // Catch:{ all -> 0x0171 }
            r1 = 16384(0x4000, float:2.2959E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0171 }
            int r2 = r0.read(r1)     // Catch:{ all -> 0x0171 }
        L_0x00af:
            r3 = -1
            if (r2 == r3) goto L_0x00bc
            java.util.jar.JarOutputStream r3 = r10.outputStream     // Catch:{ all -> 0x0171 }
            r3.write(r1, r4, r2)     // Catch:{ all -> 0x0171 }
            int r2 = r0.read(r1)     // Catch:{ all -> 0x0171 }
            goto L_0x00af
        L_0x00bc:
            java.util.jar.JarOutputStream r1 = r10.outputStream     // Catch:{ all -> 0x0171 }
            r1.closeEntry()     // Catch:{ all -> 0x0171 }
            goto L_0x009c
        L_0x00c2:
            r0 = r4
        L_0x00c3:
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x0171 }
            boolean r2 = r10.available(r2)     // Catch:{ all -> 0x0171 }
            if (r2 == 0) goto L_0x0141
            int r0 = r0 + r7
            org.apache.commons.compress.harmony.unpack200.Segment r2 = new org.apache.commons.compress.harmony.unpack200.Segment     // Catch:{ all -> 0x0171 }
            r2.<init>()     // Catch:{ all -> 0x0171 }
            int r3 = r10.logLevel     // Catch:{ all -> 0x0171 }
            r2.setLogLevel(r3)     // Catch:{ all -> 0x0171 }
            java.io.FileOutputStream r3 = r10.logFile     // Catch:{ all -> 0x0171 }
            if (r3 == 0) goto L_0x00db
            goto L_0x00dd
        L_0x00db:
            java.io.PrintStream r3 = java.lang.System.out     // Catch:{ all -> 0x0171 }
        L_0x00dd:
            r2.setLogStream(r3)     // Catch:{ all -> 0x0171 }
            r2.setPreRead(r4)     // Catch:{ all -> 0x0171 }
            if (r0 != r7) goto L_0x0105
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0171 }
            r3.<init>()     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = "Unpacking from "
            r3.append(r5)     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = r10.inputFileName     // Catch:{ all -> 0x0171 }
            r3.append(r5)     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = " to "
            r3.append(r5)     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = r10.outputFileName     // Catch:{ all -> 0x0171 }
            r3.append(r5)     // Catch:{ all -> 0x0171 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0171 }
            r2.log(r1, r3)     // Catch:{ all -> 0x0171 }
        L_0x0105:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0171 }
            r3.<init>()     // Catch:{ all -> 0x0171 }
            java.lang.String r5 = "Reading segment "
            r3.append(r5)     // Catch:{ all -> 0x0171 }
            r3.append(r0)     // Catch:{ all -> 0x0171 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0171 }
            r2.log(r1, r3)     // Catch:{ all -> 0x0171 }
            boolean r3 = r10.overrideDeflateHint     // Catch:{ all -> 0x0171 }
            if (r3 == 0) goto L_0x0122
            boolean r3 = r10.deflateHint     // Catch:{ all -> 0x0171 }
            r2.overrideDeflateHint(r3)     // Catch:{ all -> 0x0171 }
        L_0x0122:
            java.io.InputStream r3 = r10.inputStream     // Catch:{ all -> 0x0171 }
            java.util.jar.JarOutputStream r5 = r10.outputStream     // Catch:{ all -> 0x0171 }
            r2.unpack(r3, r5)     // Catch:{ all -> 0x0171 }
            java.util.jar.JarOutputStream r2 = r10.outputStream     // Catch:{ all -> 0x0171 }
            r2.flush()     // Catch:{ all -> 0x0171 }
            java.io.InputStream r2 = r10.inputStream     // Catch:{ all -> 0x0171 }
            boolean r3 = r2 instanceof java.io.FileInputStream     // Catch:{ all -> 0x0171 }
            if (r3 == 0) goto L_0x00c3
            java.io.FileInputStream r2 = (java.io.FileInputStream) r2     // Catch:{ all -> 0x0171 }
            java.io.FileDescriptor r2 = r2.getFD()     // Catch:{ all -> 0x0171 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0171 }
            r10.inputFileName = r2     // Catch:{ all -> 0x0171 }
            goto L_0x00c3
        L_0x0141:
            java.io.InputStream r0 = r10.inputStream     // Catch:{ Exception -> 0x0146 }
            r0.close()     // Catch:{ Exception -> 0x0146 }
        L_0x0146:
            java.util.jar.JarOutputStream r0 = r10.outputStream     // Catch:{ Exception -> 0x014b }
            r0.close()     // Catch:{ Exception -> 0x014b }
        L_0x014b:
            java.io.FileOutputStream r0 = r10.logFile
            if (r0 == 0) goto L_0x0152
            r0.close()     // Catch:{ Exception -> 0x0152 }
        L_0x0152:
            boolean r0 = r10.removePackFile
            if (r0 == 0) goto L_0x0170
            java.lang.String r0 = r10.inputFileName
            if (r0 == 0) goto L_0x0165
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r10.inputFileName
            r0.<init>(r1)
            boolean r4 = r0.delete()
        L_0x0165:
            if (r4 == 0) goto L_0x0168
            goto L_0x0170
        L_0x0168:
            org.apache.commons.compress.harmony.pack200.Pack200Exception r0 = new org.apache.commons.compress.harmony.pack200.Pack200Exception
            java.lang.String r1 = "Failed to delete the input file."
            r0.<init>(r1)
            throw r0
        L_0x0170:
            return
        L_0x0171:
            r0 = move-exception
            java.io.InputStream r1 = r10.inputStream     // Catch:{ Exception -> 0x0177 }
            r1.close()     // Catch:{ Exception -> 0x0177 }
        L_0x0177:
            java.util.jar.JarOutputStream r1 = r10.outputStream     // Catch:{ Exception -> 0x017c }
            r1.close()     // Catch:{ Exception -> 0x017c }
        L_0x017c:
            java.io.FileOutputStream r1 = r10.logFile
            if (r1 == 0) goto L_0x0183
            r1.close()     // Catch:{ Exception -> 0x0183 }
        L_0x0183:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.Archive.unpack():void");
    }

    private boolean available(InputStream inputStream2) throws IOException {
        inputStream2.mark(1);
        int read = inputStream2.read();
        inputStream2.reset();
        if (read != -1) {
            return true;
        }
        return false;
    }

    public void setRemovePackFile(boolean z) {
        this.removePackFile = z;
    }

    public void setVerbose(boolean z) {
        if (z) {
            this.logLevel = 2;
        } else if (this.logLevel == 2) {
            this.logLevel = 1;
        }
    }

    public void setQuiet(boolean z) {
        if (z) {
            this.logLevel = 0;
        } else if (this.logLevel == 0) {
            this.logLevel = 0;
        }
    }

    public void setLogFile(String str) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str);
    }

    public void setLogFile(String str, boolean z) throws FileNotFoundException {
        this.logFile = new FileOutputStream(str, z);
    }

    public void setDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
