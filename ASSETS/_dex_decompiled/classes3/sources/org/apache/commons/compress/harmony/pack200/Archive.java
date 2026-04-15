package org.apache.commons.compress.harmony.pack200;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.zip.GZIPOutputStream;

public class Archive {
    private long currentSegmentSize;
    private JarFile jarFile;
    private final JarInputStream jarInputStream;
    private final PackingOptions options;
    private final OutputStream outputStream;

    public Archive(JarInputStream jarInputStream2, OutputStream outputStream2, PackingOptions packingOptions) throws IOException {
        this.jarInputStream = jarInputStream2;
        packingOptions = packingOptions == null ? new PackingOptions() : packingOptions;
        this.options = packingOptions;
        this.outputStream = new BufferedOutputStream(packingOptions.isGzip() ? new GZIPOutputStream(outputStream2) : outputStream2);
        PackingUtils.config(packingOptions);
    }

    public Archive(JarFile jarFile2, OutputStream outputStream2, PackingOptions packingOptions) throws IOException {
        packingOptions = packingOptions == null ? new PackingOptions() : packingOptions;
        this.options = packingOptions;
        this.outputStream = new BufferedOutputStream(packingOptions.isGzip() ? new GZIPOutputStream(outputStream2) : outputStream2);
        this.jarFile = jarFile2;
        this.jarInputStream = null;
        PackingUtils.config(packingOptions);
    }

    public void pack() throws Pack200Exception, IOException {
        if (this.options.getEffort() == 0) {
            doZeroEffortPack();
        } else {
            doNormalPack();
        }
    }

    private void doZeroEffortPack() throws IOException, Pack200Exception {
        PackingUtils.log("Start to perform a zero-effort packing");
        JarInputStream jarInputStream2 = this.jarInputStream;
        if (jarInputStream2 != null) {
            PackingUtils.copyThroughJar(jarInputStream2, this.outputStream);
        } else {
            PackingUtils.copyThroughJar(this.jarFile, this.outputStream);
        }
    }

    private void doNormalPack() throws IOException, Pack200Exception {
        List list;
        PackingUtils.log("Start to perform a normal packing");
        JarInputStream jarInputStream2 = this.jarInputStream;
        if (jarInputStream2 != null) {
            list = PackingUtils.getPackingFileListFromJar(jarInputStream2, this.options.isKeepFileOrder());
        } else {
            list = PackingUtils.getPackingFileListFromJar(this.jarFile, this.options.isKeepFileOrder());
        }
        List splitIntoSegments = splitIntoSegments(list);
        int size = splitIntoSegments.size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            SegmentUnit segmentUnit = (SegmentUnit) splitIntoSegments.get(i3);
            new Segment().pack(segmentUnit, this.outputStream, this.options);
            i += segmentUnit.getByteAmount();
            i2 += segmentUnit.getPackedByteAmount();
        }
        PackingUtils.log("Total: Packed " + i + " input bytes of " + list.size() + " files into " + i2 + " bytes in " + size + " segments");
        this.outputStream.close();
    }

    private List splitIntoSegments(List list) throws IOException, Pack200Exception {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        long segmentLimit = this.options.getSegmentLimit();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PackingFile packingFile = (PackingFile) list.get(i);
            if (!addJarEntry(packingFile, arrayList2, arrayList3)) {
                arrayList.add(new SegmentUnit(arrayList2, arrayList3));
                arrayList2 = new ArrayList();
                arrayList3 = new ArrayList();
                this.currentSegmentSize = 0;
                addJarEntry(packingFile, arrayList2, arrayList3);
                this.currentSegmentSize = 0;
            } else if (segmentLimit == 0 && estimateSize(packingFile) > 0) {
                arrayList.add(new SegmentUnit(arrayList2, arrayList3));
                arrayList2 = new ArrayList();
                arrayList3 = new ArrayList();
            }
        }
        if (arrayList2.size() <= 0 && arrayList3.size() <= 0) {
            return arrayList;
        }
        arrayList.add(new SegmentUnit(arrayList2, arrayList3));
        return arrayList;
    }

    private boolean addJarEntry(PackingFile packingFile, List list, List list2) throws IOException, Pack200Exception {
        long segmentLimit = this.options.getSegmentLimit();
        if (!(segmentLimit == -1 || segmentLimit == 0)) {
            long estimateSize = estimateSize(packingFile);
            long j = this.currentSegmentSize;
            if (estimateSize + j > segmentLimit && j > 0) {
                return false;
            }
            this.currentSegmentSize = j + estimateSize;
        }
        String name = packingFile.getName();
        if (name.endsWith(".class") && !this.options.isPassFile(name)) {
            Pack200ClassReader pack200ClassReader = new Pack200ClassReader(packingFile.contents);
            pack200ClassReader.setFileName(name);
            list.add(pack200ClassReader);
            byte[] unused = packingFile.contents = new byte[0];
        }
        list2.add(packingFile);
        return true;
    }

    private long estimateSize(PackingFile packingFile) {
        String name = packingFile.getName();
        long j = 0;
        if (name.startsWith("META-INF") || name.startsWith("/META-INF")) {
            return 0;
        }
        long length = (long) packingFile.contents.length;
        if (length >= 0) {
            j = length;
        }
        return ((long) name.length()) + j + 5;
    }

    static class SegmentUnit {
        private int byteAmount = 0;
        private final List classList;
        private final List fileList;
        private int packedByteAmount = 0;

        public SegmentUnit(List list, List list2) {
            this.classList = list;
            this.fileList = list2;
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.byteAmount += ((Pack200ClassReader) it.next()).b.length;
            }
            for (PackingFile access$000 : this.fileList) {
                this.byteAmount += access$000.contents.length;
            }
        }

        public List getClassList() {
            return this.classList;
        }

        public int classListSize() {
            return this.classList.size();
        }

        public int fileListSize() {
            return this.fileList.size();
        }

        public List getFileList() {
            return this.fileList;
        }

        public int getByteAmount() {
            return this.byteAmount;
        }

        public int getPackedByteAmount() {
            return this.packedByteAmount;
        }

        public void addPackedByteAmount(int i) {
            this.packedByteAmount += i;
        }
    }

    static class PackingFile {
        /* access modifiers changed from: private */
        public byte[] contents;
        private final boolean deflateHint;
        private final boolean isDirectory;
        private final long modtime;
        private final String name;

        public PackingFile(String str, byte[] bArr, long j) {
            this.name = str;
            this.contents = bArr;
            this.modtime = j;
            this.deflateHint = false;
            this.isDirectory = false;
        }

        public PackingFile(byte[] bArr, JarEntry jarEntry) {
            this.name = jarEntry.getName();
            this.contents = bArr;
            this.modtime = jarEntry.getTime();
            this.deflateHint = jarEntry.getMethod() == 8;
            this.isDirectory = jarEntry.isDirectory();
        }

        public byte[] getContents() {
            return this.contents;
        }

        public String getName() {
            return this.name;
        }

        public long getModtime() {
            return this.modtime;
        }

        public void setContents(byte[] bArr) {
            this.contents = bArr;
        }

        public boolean isDefalteHint() {
            return this.deflateHint;
        }

        public boolean isDirectory() {
            return this.isDirectory;
        }

        public String toString() {
            return this.name;
        }
    }
}
