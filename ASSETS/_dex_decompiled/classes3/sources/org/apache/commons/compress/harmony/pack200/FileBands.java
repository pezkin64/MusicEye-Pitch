package org.apache.commons.compress.harmony.pack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Archive;

public class FileBands extends BandSet {
    private final CpBands cpBands;
    private final List fileList;
    private final CPUTF8[] fileName;
    private final byte[][] file_bits;
    private final int[] file_modtime;
    private int[] file_name;
    private final int[] file_options;
    private final long[] file_size;
    private final PackingOptions options;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public FileBands(org.apache.commons.compress.harmony.pack200.CpBands r17, org.apache.commons.compress.harmony.pack200.SegmentHeader r18, org.apache.commons.compress.harmony.pack200.PackingOptions r19, org.apache.commons.compress.harmony.pack200.Archive.SegmentUnit r20, int r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            r2 = r19
            r3 = r18
            r4 = r21
            r0.<init>(r4, r3)
            java.util.List r4 = r20.getFileList()
            r0.fileList = r4
            r0.options = r2
            r0.cpBands = r1
            int r4 = r4.size()
            org.apache.commons.compress.harmony.pack200.CPUTF8[] r5 = new org.apache.commons.compress.harmony.pack200.CPUTF8[r4]
            r0.fileName = r5
            int[] r5 = new int[r4]
            r0.file_modtime = r5
            long[] r5 = new long[r4]
            r0.file_size = r5
            int[] r5 = new int[r4]
            r0.file_options = r5
            byte[][] r5 = new byte[r4][]
            r0.file_bits = r5
            int r3 = r3.getArchive_modtime()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.List r6 = r20.getClassList()
            java.util.Iterator r6 = r6.iterator()
        L_0x0040:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0054
            java.lang.Object r7 = r6.next()
            org.objectweb.asm.ClassReader r7 = (org.objectweb.asm.ClassReader) r7
            java.lang.String r7 = r7.getClassName()
            r5.add(r7)
            goto L_0x0040
        L_0x0054:
            java.lang.String r6 = ""
            org.apache.commons.compress.harmony.pack200.CPUTF8 r6 = r1.getCPUtf8(r6)
            java.lang.String r7 = "keep"
            java.lang.String r8 = r2.getModificationTime()
            boolean r7 = r7.equals(r8)
            r8 = 0
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = r8
        L_0x0068:
            if (r10 >= r4) goto L_0x00fd
            java.util.List r11 = r0.fileList
            java.lang.Object r11 = r11.get(r10)
            org.apache.commons.compress.harmony.pack200.Archive$PackingFile r11 = (org.apache.commons.compress.harmony.pack200.Archive.PackingFile) r11
            java.lang.String r12 = r11.getName()
            java.lang.String r13 = ".class"
            boolean r13 = r12.endsWith(r13)
            if (r13 == 0) goto L_0x00aa
            boolean r13 = r2.isPassFile(r12)
            if (r13 != 0) goto L_0x00aa
            int[] r13 = r0.file_options
            r14 = r13[r10]
            r14 = r14 | 2
            r13[r10] = r14
            int r13 = r12.length()
            int r13 = r13 + -6
            java.lang.String r13 = r12.substring(r8, r13)
            boolean r13 = r5.contains(r13)
            if (r13 == 0) goto L_0x00a1
            org.apache.commons.compress.harmony.pack200.CPUTF8[] r12 = r0.fileName
            r12[r10] = r6
            goto L_0x00b2
        L_0x00a1:
            org.apache.commons.compress.harmony.pack200.CPUTF8[] r13 = r0.fileName
            org.apache.commons.compress.harmony.pack200.CPUTF8 r12 = r1.getCPUtf8(r12)
            r13[r10] = r12
            goto L_0x00b2
        L_0x00aa:
            org.apache.commons.compress.harmony.pack200.CPUTF8[] r13 = r0.fileName
            org.apache.commons.compress.harmony.pack200.CPUTF8 r12 = r1.getCPUtf8(r12)
            r13[r10] = r12
        L_0x00b2:
            boolean r12 = r2.isKeepDeflateHint()
            if (r12 == 0) goto L_0x00c6
            boolean r12 = r11.isDefalteHint()
            if (r12 == 0) goto L_0x00c6
            int[] r12 = r0.file_options
            r13 = r12[r10]
            r13 = r13 | 1
            r12[r10] = r13
        L_0x00c6:
            byte[] r12 = r11.getContents()
            long[] r13 = r0.file_size
            int r12 = r12.length
            long r14 = (long) r12
            r13[r10] = r14
            long r12 = r11.getModtime()
            java.util.TimeZone r14 = java.util.TimeZone.getDefault()
            int r14 = r14.getRawOffset()
            long r14 = (long) r14
            long r12 = r12 + r14
            r14 = 1000(0x3e8, double:4.94E-321)
            long r12 = r12 / r14
            int[] r14 = r0.file_modtime
            r15 = r9
            long r8 = (long) r3
            long r12 = r12 - r8
            int r8 = (int) r12
            r14[r10] = r8
            if (r7 != 0) goto L_0x00ef
            if (r15 >= r8) goto L_0x00ef
            r9 = r8
            goto L_0x00f0
        L_0x00ef:
            r9 = r15
        L_0x00f0:
            byte[][] r8 = r0.file_bits
            byte[] r11 = r11.getContents()
            r8[r10] = r11
            int r10 = r10 + 1
            r8 = 0
            goto L_0x0068
        L_0x00fd:
            r15 = r9
            if (r7 != 0) goto L_0x010a
            r8 = 0
        L_0x0101:
            if (r8 >= r4) goto L_0x010a
            int[] r1 = r0.file_modtime
            r1[r8] = r15
            int r8 = r8 + 1
            goto L_0x0101
        L_0x010a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.FileBands.<init>(org.apache.commons.compress.harmony.pack200.CpBands, org.apache.commons.compress.harmony.pack200.SegmentHeader, org.apache.commons.compress.harmony.pack200.PackingOptions, org.apache.commons.compress.harmony.pack200.Archive$SegmentUnit, int):void");
    }

    public void finaliseBands() {
        this.file_name = new int[this.fileName.length];
        for (int i = 0; i < this.file_name.length; i++) {
            if (this.fileName[i].equals(this.cpBands.getCPUtf8(BuildConfig.FLAVOR))) {
                String name = ((Archive.PackingFile) this.fileList.get(i)).getName();
                if (this.options.isPassFile(name)) {
                    this.fileName[i] = this.cpBands.getCPUtf8(name);
                    int[] iArr = this.file_options;
                    iArr[i] = iArr[i] & -3;
                }
            }
            this.file_name[i] = this.fileName[i].getIndex();
        }
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing file bands...");
        byte[] encodeBandInt = encodeBandInt("file_name", this.file_name, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from file_name[" + this.file_name.length + "]");
        byte[] encodeFlags = encodeFlags("file_size", this.file_size, Codec.UNSIGNED5, Codec.UNSIGNED5, this.segmentHeader.have_file_size_hi());
        outputStream.write(encodeFlags);
        PackingUtils.log("Wrote " + encodeFlags.length + " bytes from file_size[" + this.file_size.length + "]");
        if (this.segmentHeader.have_file_modtime()) {
            byte[] encodeBandInt2 = encodeBandInt("file_modtime", this.file_modtime, Codec.DELTA5);
            outputStream.write(encodeBandInt2);
            PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from file_modtime[" + this.file_modtime.length + "]");
        }
        if (this.segmentHeader.have_file_options()) {
            byte[] encodeBandInt3 = encodeBandInt("file_options", this.file_options, Codec.UNSIGNED5);
            outputStream.write(encodeBandInt3);
            PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from file_options[" + this.file_options.length + "]");
        }
        byte[] encodeBandInt4 = encodeBandInt("file_bits", flatten(this.file_bits), Codec.BYTE1);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from file_bits[" + this.file_bits.length + "]");
    }

    private int[] flatten(byte[][] bArr) {
        int i = 0;
        for (byte[] length : bArr) {
            i += length.length;
        }
        int[] iArr = new int[i];
        int i2 = 0;
        for (byte[] bArr2 : bArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= bArr2.length) {
                    break;
                }
                iArr[i2] = bArr2[i3] & 255;
                i3++;
                i2++;
            }
        }
        return iArr;
    }
}
