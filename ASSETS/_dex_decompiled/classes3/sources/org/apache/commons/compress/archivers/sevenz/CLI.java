package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.PrintStream;

public class CLI {

    private enum Mode {
        LIST("Analysing") {
            public void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) {
                System.out.print(sevenZArchiveEntry.getName());
                if (sevenZArchiveEntry.isDirectory()) {
                    System.out.print(" dir");
                } else {
                    PrintStream printStream = System.out;
                    printStream.print(" " + sevenZArchiveEntry.getCompressedSize() + "/" + sevenZArchiveEntry.getSize());
                }
                if (sevenZArchiveEntry.getHasLastModifiedDate()) {
                    PrintStream printStream2 = System.out;
                    printStream2.print(" " + sevenZArchiveEntry.getLastModifiedDate());
                } else {
                    System.out.print(" no last modified date");
                }
                if (!sevenZArchiveEntry.isDirectory()) {
                    PrintStream printStream3 = System.out;
                    printStream3.println(" " + getContentMethods(sevenZArchiveEntry));
                    return;
                }
                System.out.println();
            }

            private String getContentMethods(SevenZArchiveEntry sevenZArchiveEntry) {
                StringBuilder sb = new StringBuilder();
                boolean z = true;
                for (SevenZMethodConfiguration sevenZMethodConfiguration : sevenZArchiveEntry.getContentMethods()) {
                    if (!z) {
                        sb.append(", ");
                    }
                    sb.append(sevenZMethodConfiguration.getMethod());
                    if (sevenZMethodConfiguration.getOptions() != null) {
                        sb.append("(");
                        sb.append(sevenZMethodConfiguration.getOptions());
                        sb.append(")");
                    }
                    z = false;
                }
                return sb.toString();
            }
        };
        
        private final String message;

        public abstract void takeAction(SevenZFile sevenZFile, SevenZArchiveEntry sevenZArchiveEntry) throws IOException;

        private Mode(String str) {
            this.message = str;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0063, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0068, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0069, code lost:
        r0.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006c, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void main(java.lang.String[] r5) throws java.lang.Exception {
        /*
            int r0 = r5.length
            if (r0 != 0) goto L_0x0007
            usage()
            return
        L_0x0007:
            org.apache.commons.compress.archivers.sevenz.CLI$Mode r0 = grabMode(r5)
            java.io.PrintStream r1 = java.lang.System.out
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r0.getMessage()
            r2.append(r3)
            java.lang.String r3 = " "
            r2.append(r3)
            r3 = 0
            r4 = r5[r3]
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r1.println(r2)
            java.io.File r1 = new java.io.File
            r5 = r5[r3]
            r1.<init>(r5)
            boolean r5 = r1.isFile()
            if (r5 != 0) goto L_0x004e
            java.io.PrintStream r5 = java.lang.System.err
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r1)
            java.lang.String r3 = " doesn't exist or is a directory"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r5.println(r2)
        L_0x004e:
            org.apache.commons.compress.archivers.sevenz.SevenZFile r5 = new org.apache.commons.compress.archivers.sevenz.SevenZFile
            r5.<init>((java.io.File) r1)
        L_0x0053:
            org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry r1 = r5.getNextEntry()     // Catch:{ all -> 0x0061 }
            if (r1 == 0) goto L_0x005d
            r0.takeAction(r5, r1)     // Catch:{ all -> 0x0061 }
            goto L_0x0053
        L_0x005d:
            r5.close()
            return
        L_0x0061:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0063 }
        L_0x0063:
            r1 = move-exception
            r5.close()     // Catch:{ all -> 0x0068 }
            goto L_0x006c
        L_0x0068:
            r5 = move-exception
            r0.addSuppressed(r5)
        L_0x006c:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.sevenz.CLI.main(java.lang.String[]):void");
    }

    private static void usage() {
        System.out.println("Parameters: archive-name [list]");
    }

    private static Mode grabMode(String[] strArr) {
        if (strArr.length < 2) {
            return Mode.LIST;
        }
        return (Mode) Enum.valueOf(Mode.class, strArr[1].toUpperCase());
    }
}
