package org.apache.commons.compress.archivers.examples;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.util.Iterator;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class Expander {

    private interface ArchiveEntrySupplier {
        ArchiveEntry getNextReadableEntry() throws IOException;
    }

    private interface EntryWriter {
        void writeEntryDataTo(ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        r4.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0026, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        r5 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void expand(java.io.File r4, java.io.File r5) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r3 = this;
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            java.nio.file.Path r1 = r4.toPath()
            r2 = 0
            java.nio.file.OpenOption[] r2 = new java.nio.file.OpenOption[r2]
            java.io.InputStream r1 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r1, (java.nio.file.OpenOption[]) r2)
            r0.<init>(r1)
            java.lang.String r1 = org.apache.commons.compress.archivers.ArchiveStreamFactory.detect(r0)     // Catch:{ all -> 0x001b }
            r0.close()
            r3.expand((java.lang.String) r1, (java.io.File) r4, (java.io.File) r5)
            return
        L_0x001b:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x001d }
        L_0x001d:
            r5 = move-exception
            r0.close()     // Catch:{ all -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r0 = move-exception
            r4.addSuppressed(r0)
        L_0x0026:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(java.io.File, java.io.File):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0025, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0026, code lost:
        if (r5 != null) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0030, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004b, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0050, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0051, code lost:
        r4.addSuppressed(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void expand(java.lang.String r4, java.io.File r5, java.io.File r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r3 = this;
            boolean r0 = r3.prefersSeekableByteChannel(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0031
            java.nio.file.Path r5 = r5.toPath()
            r0 = 1
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r2 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m$3()
            r0[r1] = r2
            java.nio.channels.FileChannel r5 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r5, (java.nio.file.OpenOption[]) r0)
            org.apache.commons.compress.archivers.examples.CloseableConsumer r0 = org.apache.commons.compress.archivers.examples.CloseableConsumer.CLOSING_CONSUMER     // Catch:{ all -> 0x0023 }
            r3.expand((java.lang.String) r4, (java.nio.channels.SeekableByteChannel) r5, (java.io.File) r6, (org.apache.commons.compress.archivers.examples.CloseableConsumer) r0)     // Catch:{ all -> 0x0023 }
            if (r5 == 0) goto L_0x0022
            kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5)
        L_0x0022:
            return
        L_0x0023:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0025 }
        L_0x0025:
            r6 = move-exception
            if (r5 == 0) goto L_0x0030
            kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5)     // Catch:{ all -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0030:
            throw r6
        L_0x0031:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream
            java.nio.file.Path r5 = r5.toPath()
            java.nio.file.OpenOption[] r1 = new java.nio.file.OpenOption[r1]
            java.io.InputStream r5 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r5, (java.nio.file.OpenOption[]) r1)
            r0.<init>(r5)
            org.apache.commons.compress.archivers.examples.CloseableConsumer r5 = org.apache.commons.compress.archivers.examples.CloseableConsumer.CLOSING_CONSUMER     // Catch:{ all -> 0x0049 }
            r3.expand((java.lang.String) r4, (java.io.InputStream) r0, (java.io.File) r6, (org.apache.commons.compress.archivers.examples.CloseableConsumer) r5)     // Catch:{ all -> 0x0049 }
            r0.close()
            return
        L_0x0049:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x004b }
        L_0x004b:
            r5 = move-exception
            r0.close()     // Catch:{ all -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r6 = move-exception
            r4.addSuppressed(r6)
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(java.lang.String, java.io.File, java.io.File):void");
    }

    @Deprecated
    public void expand(InputStream inputStream, File file) throws IOException, ArchiveException {
        expand(inputStream, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void expand(java.io.InputStream r2, java.io.File r3, org.apache.commons.compress.archivers.examples.CloseableConsumer r4) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r1 = this;
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r0 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r0.<init>(r4)
            org.apache.commons.compress.archivers.ArchiveStreamFactory r4 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveInputStream r2 = r4.createArchiveInputStream(r2)     // Catch:{ all -> 0x0018 }
            java.io.Closeable r2 = r0.track(r2)     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveInputStream r2 = (org.apache.commons.compress.archivers.ArchiveInputStream) r2     // Catch:{ all -> 0x0018 }
            r1.expand((org.apache.commons.compress.archivers.ArchiveInputStream) r2, (java.io.File) r3)     // Catch:{ all -> 0x0018 }
            r0.close()
            return
        L_0x0018:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001a }
        L_0x001a:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0023:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(java.io.InputStream, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    @Deprecated
    public void expand(String str, InputStream inputStream, File file) throws IOException, ArchiveException {
        expand(str, inputStream, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        r2.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0023, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        r3 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void expand(java.lang.String r2, java.io.InputStream r3, java.io.File r4, org.apache.commons.compress.archivers.examples.CloseableConsumer r5) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r1 = this;
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r0 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r0.<init>(r5)
            org.apache.commons.compress.archivers.ArchiveStreamFactory r5 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveInputStream r2 = r5.createArchiveInputStream(r2, r3)     // Catch:{ all -> 0x0018 }
            java.io.Closeable r2 = r0.track(r2)     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveInputStream r2 = (org.apache.commons.compress.archivers.ArchiveInputStream) r2     // Catch:{ all -> 0x0018 }
            r1.expand((org.apache.commons.compress.archivers.ArchiveInputStream) r2, (java.io.File) r4)     // Catch:{ all -> 0x0018 }
            r0.close()
            return
        L_0x0018:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001a }
        L_0x001a:
            r3 = move-exception
            r0.close()     // Catch:{ all -> 0x001f }
            goto L_0x0023
        L_0x001f:
            r4 = move-exception
            r2.addSuppressed(r4)
        L_0x0023:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(java.lang.String, java.io.InputStream, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    @Deprecated
    public void expand(String str, SeekableByteChannel seekableByteChannel, File file) throws IOException, ArchiveException {
        expand(str, seekableByteChannel, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x007d, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0080, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void expand(java.lang.String r3, java.nio.channels.SeekableByteChannel r4, java.io.File r5, org.apache.commons.compress.archivers.examples.CloseableConsumer r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r2 = this;
            java.lang.String r0 = "Don't know how to handle format "
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r1 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r1.<init>(r6)
            boolean r6 = r2.prefersSeekableByteChannel(r3)     // Catch:{ all -> 0x0075 }
            if (r6 != 0) goto L_0x001b
            java.io.InputStream r4 = java.nio.channels.Channels.newInputStream(r4)     // Catch:{ all -> 0x0075 }
            java.io.Closeable r4 = r1.track(r4)     // Catch:{ all -> 0x0075 }
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x0075 }
            r2.expand((java.lang.String) r3, (java.io.InputStream) r4, (java.io.File) r5)     // Catch:{ all -> 0x0075 }
            goto L_0x005f
        L_0x001b:
            java.lang.String r6 = "tar"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x0075 }
            if (r6 == 0) goto L_0x0032
            org.apache.commons.compress.archivers.tar.TarFile r3 = new org.apache.commons.compress.archivers.tar.TarFile     // Catch:{ all -> 0x0075 }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x0075 }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x0075 }
            org.apache.commons.compress.archivers.tar.TarFile r3 = (org.apache.commons.compress.archivers.tar.TarFile) r3     // Catch:{ all -> 0x0075 }
            r2.expand((org.apache.commons.compress.archivers.tar.TarFile) r3, (java.io.File) r5)     // Catch:{ all -> 0x0075 }
            goto L_0x005f
        L_0x0032:
            java.lang.String r6 = "zip"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x0075 }
            if (r6 == 0) goto L_0x0049
            org.apache.commons.compress.archivers.zip.ZipFile r3 = new org.apache.commons.compress.archivers.zip.ZipFile     // Catch:{ all -> 0x0075 }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x0075 }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x0075 }
            org.apache.commons.compress.archivers.zip.ZipFile r3 = (org.apache.commons.compress.archivers.zip.ZipFile) r3     // Catch:{ all -> 0x0075 }
            r2.expand((org.apache.commons.compress.archivers.zip.ZipFile) r3, (java.io.File) r5)     // Catch:{ all -> 0x0075 }
            goto L_0x005f
        L_0x0049:
            java.lang.String r6 = "7z"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x0075 }
            if (r6 == 0) goto L_0x0063
            org.apache.commons.compress.archivers.sevenz.SevenZFile r3 = new org.apache.commons.compress.archivers.sevenz.SevenZFile     // Catch:{ all -> 0x0075 }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x0075 }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x0075 }
            org.apache.commons.compress.archivers.sevenz.SevenZFile r3 = (org.apache.commons.compress.archivers.sevenz.SevenZFile) r3     // Catch:{ all -> 0x0075 }
            r2.expand((org.apache.commons.compress.archivers.sevenz.SevenZFile) r3, (java.io.File) r5)     // Catch:{ all -> 0x0075 }
        L_0x005f:
            r1.close()
            return
        L_0x0063:
            org.apache.commons.compress.archivers.ArchiveException r4 = new org.apache.commons.compress.archivers.ArchiveException     // Catch:{ all -> 0x0075 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0075 }
            r5.<init>(r0)     // Catch:{ all -> 0x0075 }
            r5.append(r3)     // Catch:{ all -> 0x0075 }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x0075 }
            r4.<init>(r3)     // Catch:{ all -> 0x0075 }
            throw r4     // Catch:{ all -> 0x0075 }
        L_0x0075:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0077 }
        L_0x0077:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0080:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(java.lang.String, java.nio.channels.SeekableByteChannel, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    public void expand(ArchiveInputStream archiveInputStream, File file) throws IOException, ArchiveException {
        expand((ArchiveEntrySupplier) new Expander$$ExternalSyntheticLambda1(archiveInputStream), (EntryWriter) new Expander$$ExternalSyntheticLambda2(archiveInputStream), file);
    }

    static /* synthetic */ ArchiveEntry lambda$expand$0(ArchiveInputStream archiveInputStream) throws IOException {
        ArchiveEntry nextEntry = archiveInputStream.getNextEntry();
        while (nextEntry != null && !archiveInputStream.canReadEntryData(nextEntry)) {
            nextEntry = archiveInputStream.getNextEntry();
        }
        return nextEntry;
    }

    public void expand(TarFile tarFile, File file) throws IOException, ArchiveException {
        expand((ArchiveEntrySupplier) new Expander$$ExternalSyntheticLambda7(tarFile.getEntries().iterator()), (EntryWriter) new Expander$$ExternalSyntheticLambda8(tarFile), file);
    }

    static /* synthetic */ ArchiveEntry lambda$expand$2(Iterator it) throws IOException {
        if (it.hasNext()) {
            return (ArchiveEntry) it.next();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$expand$3(org.apache.commons.compress.archivers.tar.TarFile r0, org.apache.commons.compress.archivers.ArchiveEntry r1, java.io.OutputStream r2) throws java.io.IOException {
        /*
            org.apache.commons.compress.archivers.tar.TarArchiveEntry r1 = (org.apache.commons.compress.archivers.tar.TarArchiveEntry) r1
            java.io.InputStream r0 = r0.getInputStream(r1)
            org.apache.commons.compress.utils.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r2)     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000e
            r0.close()
        L_0x000e:
            return
        L_0x000f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ all -> 0x0018 }
            goto L_0x001c
        L_0x0018:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.lambda$expand$3(org.apache.commons.compress.archivers.tar.TarFile, org.apache.commons.compress.archivers.ArchiveEntry, java.io.OutputStream):void");
    }

    public void expand(ZipFile zipFile, File file) throws IOException, ArchiveException {
        expand((ArchiveEntrySupplier) new Expander$$ExternalSyntheticLambda3(zipFile.getEntries(), zipFile), (EntryWriter) new Expander$$ExternalSyntheticLambda4(zipFile), file);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0024 A[EDGE_INSN: B:11:0x0024->B:10:0x0024 ?: BREAK  
    EDGE_INSN: B:13:0x0024->B:10:0x0024 ?: BREAK  , RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ org.apache.commons.compress.archivers.ArchiveEntry lambda$expand$4(java.util.Enumeration r3, org.apache.commons.compress.archivers.zip.ZipFile r4) throws java.io.IOException {
        /*
            boolean r0 = r3.hasMoreElements()
            r1 = 0
            if (r0 == 0) goto L_0x000e
            java.lang.Object r0 = r3.nextElement()
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r0 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r0
            goto L_0x000f
        L_0x000e:
            r0 = r1
        L_0x000f:
            if (r0 == 0) goto L_0x0024
            boolean r2 = r4.canReadEntryData(r0)
            if (r2 != 0) goto L_0x0024
            boolean r0 = r3.hasMoreElements()
            if (r0 == 0) goto L_0x000e
            java.lang.Object r0 = r3.nextElement()
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r0 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r0
            goto L_0x000f
        L_0x0024:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.lambda$expand$4(java.util.Enumeration, org.apache.commons.compress.archivers.zip.ZipFile):org.apache.commons.compress.archivers.ArchiveEntry");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0019, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0011, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r0 != null) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void lambda$expand$5(org.apache.commons.compress.archivers.zip.ZipFile r0, org.apache.commons.compress.archivers.ArchiveEntry r1, java.io.OutputStream r2) throws java.io.IOException {
        /*
            org.apache.commons.compress.archivers.zip.ZipArchiveEntry r1 = (org.apache.commons.compress.archivers.zip.ZipArchiveEntry) r1
            java.io.InputStream r0 = r0.getInputStream(r1)
            org.apache.commons.compress.utils.IOUtils.copy((java.io.InputStream) r0, (java.io.OutputStream) r2)     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000e
            r0.close()
        L_0x000e:
            return
        L_0x000f:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0011 }
        L_0x0011:
            r2 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()     // Catch:{ all -> 0x0018 }
            goto L_0x001c
        L_0x0018:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001c:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.lambda$expand$5(org.apache.commons.compress.archivers.zip.ZipFile, org.apache.commons.compress.archivers.ArchiveEntry, java.io.OutputStream):void");
    }

    public void expand(SevenZFile sevenZFile, File file) throws IOException, ArchiveException {
        sevenZFile.getClass();
        expand((ArchiveEntrySupplier) new Expander$$ExternalSyntheticLambda5(sevenZFile), (EntryWriter) new Expander$$ExternalSyntheticLambda6(sevenZFile), file);
    }

    static /* synthetic */ void lambda$expand$6(SevenZFile sevenZFile, ArchiveEntry archiveEntry, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int read = sevenZFile.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private boolean prefersSeekableByteChannel(String str) {
        return ArchiveStreamFactory.TAR.equalsIgnoreCase(str) || ArchiveStreamFactory.ZIP.equalsIgnoreCase(str) || ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009a, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x009b, code lost:
        if (r2 != null) goto L_0x009d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a1, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a2, code lost:
        r7.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a5, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void expand(org.apache.commons.compress.archivers.examples.Expander.ArchiveEntrySupplier r7, org.apache.commons.compress.archivers.examples.Expander.EntryWriter r8, java.io.File r9) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = r9.getCanonicalPath()
            java.lang.String r1 = java.io.File.separator
            boolean r1 = r0.endsWith(r1)
            if (r1 != 0) goto L_0x001d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = java.io.File.separator
            r1.append(r0)
            java.lang.String r0 = r1.toString()
        L_0x001d:
            org.apache.commons.compress.archivers.ArchiveEntry r1 = r7.getNextReadableEntry()
        L_0x0021:
            if (r1 == 0) goto L_0x00c6
            java.io.File r2 = new java.io.File
            java.lang.String r3 = r1.getName()
            r2.<init>(r9, r3)
            java.lang.String r3 = r2.getCanonicalPath()
            boolean r3 = r3.startsWith(r0)
            if (r3 == 0) goto L_0x00a6
            boolean r3 = r1.isDirectory()
            java.lang.String r4 = "Failed to create directory "
            if (r3 == 0) goto L_0x005d
            boolean r1 = r2.isDirectory()
            if (r1 != 0) goto L_0x0093
            boolean r1 = r2.mkdirs()
            if (r1 == 0) goto L_0x004b
            goto L_0x0093
        L_0x004b:
            java.io.IOException r7 = new java.io.IOException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r4)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x005d:
            java.io.File r3 = r2.getParentFile()
            boolean r5 = r3.isDirectory()
            if (r5 != 0) goto L_0x0080
            boolean r5 = r3.mkdirs()
            if (r5 == 0) goto L_0x006e
            goto L_0x0080
        L_0x006e:
            java.io.IOException r7 = new java.io.IOException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r4)
            r8.append(r3)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x0080:
            java.nio.file.Path r2 = r2.toPath()
            r3 = 0
            java.nio.file.OpenOption[] r3 = new java.nio.file.OpenOption[r3]
            java.io.OutputStream r2 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r2, (java.nio.file.OpenOption[]) r3)
            r8.writeEntryDataTo(r1, r2)     // Catch:{ all -> 0x0098 }
            if (r2 == 0) goto L_0x0093
            r2.close()
        L_0x0093:
            org.apache.commons.compress.archivers.ArchiveEntry r1 = r7.getNextReadableEntry()
            goto L_0x0021
        L_0x0098:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x009a }
        L_0x009a:
            r8 = move-exception
            if (r2 == 0) goto L_0x00a5
            r2.close()     // Catch:{ all -> 0x00a1 }
            goto L_0x00a5
        L_0x00a1:
            r9 = move-exception
            r7.addSuppressed(r9)
        L_0x00a5:
            throw r8
        L_0x00a6:
            java.io.IOException r7 = new java.io.IOException
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r0 = "Expanding "
            r8.<init>(r0)
            java.lang.String r0 = r1.getName()
            r8.append(r0)
            java.lang.String r0 = " would create file outside of "
            r8.append(r0)
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.<init>(r8)
            throw r7
        L_0x00c6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Expander.expand(org.apache.commons.compress.archivers.examples.Expander$ArchiveEntrySupplier, org.apache.commons.compress.archivers.examples.Expander$EntryWriter, java.io.File):void");
    }
}
