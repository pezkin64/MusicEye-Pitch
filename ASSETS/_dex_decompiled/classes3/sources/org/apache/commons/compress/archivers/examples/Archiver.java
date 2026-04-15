package org.apache.commons.compress.archivers.examples;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.Objects;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;
import kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;
import org.apache.commons.compress.utils.IOUtils;

public class Archiver {
    public static final EnumSet<FileVisitOption> EMPTY_FileVisitOption = EnumSet.noneOf(StreamsKt$$ExternalSyntheticApiModelOutline0.m$1());

    private static class ArchiverFileVisitor extends SimpleFileVisitor<Path> {
        private final Path directory;
        private final LinkOption[] linkOptions;
        private final ArchiveOutputStream target;

        public /* bridge */ /* synthetic */ FileVisitResult preVisitDirectory(Object obj, BasicFileAttributes basicFileAttributes) throws IOException {
            return preVisitDirectory(PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(obj), basicFileAttributes);
        }

        public /* bridge */ /* synthetic */ FileVisitResult visitFile(Object obj, BasicFileAttributes basicFileAttributes) throws IOException {
            return visitFile(PathTreeWalk$$ExternalSyntheticApiModelOutline0.m(obj), basicFileAttributes);
        }

        private ArchiverFileVisitor(ArchiveOutputStream archiveOutputStream, Path path, LinkOption... linkOptionArr) {
            this.target = archiveOutputStream;
            this.directory = path;
            this.linkOptions = linkOptionArr == null ? IOUtils.EMPTY_LINK_OPTIONS : (LinkOption[]) linkOptionArr.clone();
        }

        public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            return visit(path, basicFileAttributes, false);
        }

        /* access modifiers changed from: protected */
        public FileVisitResult visit(Path path, BasicFileAttributes basicFileAttributes, boolean z) throws IOException {
            Objects.requireNonNull(path);
            Objects.requireNonNull(basicFileAttributes);
            String replace = StreamsKt$$ExternalSyntheticApiModelOutline0.m(PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$1(this.directory, path)).replace('\\', '/');
            if (!replace.isEmpty()) {
                ArchiveOutputStream archiveOutputStream = this.target;
                if (!z && !replace.endsWith("/")) {
                    replace = replace + "/";
                }
                this.target.putArchiveEntry(archiveOutputStream.createArchiveEntry(path, replace, this.linkOptions));
                if (z) {
                    long unused = Files.copy(path, this.target);
                }
                this.target.closeArchiveEntry();
            }
            return PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$2();
        }

        public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
            return visit(path, basicFileAttributes, true);
        }
    }

    public void create(ArchiveOutputStream archiveOutputStream, File file) throws IOException, ArchiveException {
        create(archiveOutputStream, file.toPath(), EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(ArchiveOutputStream archiveOutputStream, Path path, EnumSet<FileVisitOption> enumSet, LinkOption... linkOptionArr) throws IOException {
        Path unused = Files.walkFileTree(path, enumSet, Integer.MAX_VALUE, new ArchiverFileVisitor(archiveOutputStream, path, linkOptionArr));
        archiveOutputStream.finish();
    }

    public void create(ArchiveOutputStream archiveOutputStream, Path path) throws IOException {
        create(archiveOutputStream, path, EMPTY_FileVisitOption, new LinkOption[0]);
    }

    public void create(SevenZOutputFile sevenZOutputFile, File file) throws IOException {
        create(sevenZOutputFile, file.toPath());
    }

    public void create(SevenZOutputFile sevenZOutputFile, Path path) throws IOException {
        final Path path2 = path;
        final SevenZOutputFile sevenZOutputFile2 = sevenZOutputFile;
        Path path3 = path;
        Path unused = Files.walkFileTree(path3, new ArchiverFileVisitor((ArchiveOutputStream) null, path3, new LinkOption[0]) {
            /* access modifiers changed from: protected */
            public FileVisitResult visit(Path path, BasicFileAttributes basicFileAttributes, boolean z) throws IOException {
                Objects.requireNonNull(path);
                Objects.requireNonNull(basicFileAttributes);
                String replace = StreamsKt$$ExternalSyntheticApiModelOutline0.m(PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$1(path2, path)).replace('\\', '/');
                if (!replace.isEmpty()) {
                    SevenZOutputFile sevenZOutputFile = sevenZOutputFile2;
                    if (!z && !replace.endsWith("/")) {
                        replace = replace + "/";
                    }
                    sevenZOutputFile2.putArchiveEntry(sevenZOutputFile.createArchiveEntry(path, replace, new LinkOption[0]));
                    if (z) {
                        sevenZOutputFile2.write(path, new OpenOption[0]);
                    }
                    sevenZOutputFile2.closeArchiveEntry();
                }
                return PathTreeWalk$$ExternalSyntheticApiModelOutline0.m$2();
            }
        });
        sevenZOutputFile2.finish();
    }

    public void create(String str, File file, File file2) throws IOException, ArchiveException {
        create(str, file.toPath(), file2.toPath());
    }

    @Deprecated
    public void create(String str, OutputStream outputStream, File file) throws IOException, ArchiveException {
        create(str, outputStream, file, CloseableConsumer.NULL_CONSUMER);
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
    public void create(java.lang.String r2, java.io.OutputStream r3, java.io.File r4, org.apache.commons.compress.archivers.examples.CloseableConsumer r5) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r1 = this;
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r0 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r0.<init>(r5)
            org.apache.commons.compress.archivers.ArchiveStreamFactory r5 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveOutputStream r2 = r5.createArchiveOutputStream(r2, r3)     // Catch:{ all -> 0x0018 }
            java.io.Closeable r2 = r0.track(r2)     // Catch:{ all -> 0x0018 }
            org.apache.commons.compress.archivers.ArchiveOutputStream r2 = (org.apache.commons.compress.archivers.ArchiveOutputStream) r2     // Catch:{ all -> 0x0018 }
            r1.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r2, (java.io.File) r4)     // Catch:{ all -> 0x0018 }
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
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.io.OutputStream, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r5 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0035, code lost:
        r4.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0038, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0054, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0055, code lost:
        if (r4 != null) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005c, code lost:
        r5.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005f, code lost:
        throw r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r4, java.nio.file.Path r5, java.nio.file.Path r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r3 = this;
            boolean r0 = r3.prefersSeekableByteChannel(r4)
            r1 = 0
            if (r0 == 0) goto L_0x0039
            r0 = 3
            java.nio.file.OpenOption[] r0 = new java.nio.file.OpenOption[r0]
            java.nio.file.StandardOpenOption r2 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m()
            r0[r1] = r2
            r1 = 1
            java.nio.file.StandardOpenOption r2 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m$1()
            r0[r1] = r2
            r1 = 2
            java.nio.file.StandardOpenOption r2 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m$2()
            r0[r1] = r2
            java.nio.channels.FileChannel r5 = kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r5, (java.nio.file.OpenOption[]) r0)
            r3.create((java.lang.String) r4, (java.nio.channels.SeekableByteChannel) r5, (java.nio.file.Path) r6)     // Catch:{ all -> 0x002b }
            if (r5 == 0) goto L_0x0051
            kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5)
            return
        L_0x002b:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x002d }
        L_0x002d:
            r6 = move-exception
            if (r5 == 0) goto L_0x0038
            kotlin.streams.jdk8.StreamsKt$$ExternalSyntheticApiModelOutline0.m((java.nio.channels.SeekableByteChannel) r5)     // Catch:{ all -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r5 = move-exception
            r4.addSuppressed(r5)
        L_0x0038:
            throw r6
        L_0x0039:
            org.apache.commons.compress.archivers.ArchiveStreamFactory r0 = org.apache.commons.compress.archivers.ArchiveStreamFactory.DEFAULT
            java.nio.file.OpenOption[] r2 = new java.nio.file.OpenOption[r1]
            java.io.OutputStream r5 = kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0.m((java.nio.file.Path) r5, (java.nio.file.OpenOption[]) r2)
            org.apache.commons.compress.archivers.ArchiveOutputStream r4 = r0.createArchiveOutputStream(r4, r5)
            java.util.EnumSet<java.nio.file.FileVisitOption> r5 = EMPTY_FileVisitOption     // Catch:{ all -> 0x0052 }
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r1]     // Catch:{ all -> 0x0052 }
            r3.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r4, (java.nio.file.Path) r6, (java.util.EnumSet<java.nio.file.FileVisitOption>) r5, (java.nio.file.LinkOption[]) r0)     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x0051
            r4.close()
        L_0x0051:
            return
        L_0x0052:
            r5 = move-exception
            throw r5     // Catch:{ all -> 0x0054 }
        L_0x0054:
            r6 = move-exception
            if (r4 == 0) goto L_0x005f
            r4.close()     // Catch:{ all -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r4 = move-exception
            r5.addSuppressed(r4)
        L_0x005f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.file.Path, java.nio.file.Path):void");
    }

    @Deprecated
    public void create(String str, SeekableByteChannel seekableByteChannel, File file) throws IOException, ArchiveException {
        create(str, seekableByteChannel, file, CloseableConsumer.NULL_CONSUMER);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0060, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0065, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0066, code lost:
        r3.addSuppressed(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0069, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r3, java.nio.channels.SeekableByteChannel r4, java.io.File r5, org.apache.commons.compress.archivers.examples.CloseableConsumer r6) throws java.io.IOException, org.apache.commons.compress.archivers.ArchiveException {
        /*
            r2 = this;
            java.lang.String r0 = "Don't know how to handle format "
            org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter r1 = new org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter
            r1.<init>(r6)
            boolean r6 = r2.prefersSeekableByteChannel(r3)     // Catch:{ all -> 0x005e }
            if (r6 != 0) goto L_0x001b
            java.io.OutputStream r4 = java.nio.channels.Channels.newOutputStream(r4)     // Catch:{ all -> 0x005e }
            java.io.Closeable r4 = r1.track(r4)     // Catch:{ all -> 0x005e }
            java.io.OutputStream r4 = (java.io.OutputStream) r4     // Catch:{ all -> 0x005e }
            r2.create((java.lang.String) r3, (java.io.OutputStream) r4, (java.io.File) r5)     // Catch:{ all -> 0x005e }
            goto L_0x0048
        L_0x001b:
            java.lang.String r6 = "zip"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x005e }
            if (r6 == 0) goto L_0x0032
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r3 = new org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream     // Catch:{ all -> 0x005e }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x005e }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x005e }
            org.apache.commons.compress.archivers.ArchiveOutputStream r3 = (org.apache.commons.compress.archivers.ArchiveOutputStream) r3     // Catch:{ all -> 0x005e }
            r2.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r3, (java.io.File) r5)     // Catch:{ all -> 0x005e }
            goto L_0x0048
        L_0x0032:
            java.lang.String r6 = "7z"
            boolean r6 = r6.equalsIgnoreCase(r3)     // Catch:{ all -> 0x005e }
            if (r6 == 0) goto L_0x004c
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r3 = new org.apache.commons.compress.archivers.sevenz.SevenZOutputFile     // Catch:{ all -> 0x005e }
            r3.<init>((java.nio.channels.SeekableByteChannel) r4)     // Catch:{ all -> 0x005e }
            java.io.Closeable r3 = r1.track(r3)     // Catch:{ all -> 0x005e }
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r3 = (org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r3     // Catch:{ all -> 0x005e }
            r2.create((org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r3, (java.io.File) r5)     // Catch:{ all -> 0x005e }
        L_0x0048:
            r1.close()
            return
        L_0x004c:
            org.apache.commons.compress.archivers.ArchiveException r4 = new org.apache.commons.compress.archivers.ArchiveException     // Catch:{ all -> 0x005e }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
            r5.<init>(r0)     // Catch:{ all -> 0x005e }
            r5.append(r3)     // Catch:{ all -> 0x005e }
            java.lang.String r3 = r5.toString()     // Catch:{ all -> 0x005e }
            r4.<init>(r3)     // Catch:{ all -> 0x005e }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005e:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0060 }
        L_0x0060:
            r4 = move-exception
            r1.close()     // Catch:{ all -> 0x0065 }
            goto L_0x0069
        L_0x0065:
            r5 = move-exception
            r3.addSuppressed(r5)
        L_0x0069:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.channels.SeekableByteChannel, java.io.File, org.apache.commons.compress.archivers.examples.CloseableConsumer):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001c, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001f, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003b, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0040, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0041, code lost:
        r3.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0044, code lost:
        throw r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void create(java.lang.String r2, java.nio.channels.SeekableByteChannel r3, java.nio.file.Path r4) throws java.io.IOException {
        /*
            r1 = this;
            java.lang.String r0 = "7z"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0020
            org.apache.commons.compress.archivers.sevenz.SevenZOutputFile r2 = new org.apache.commons.compress.archivers.sevenz.SevenZOutputFile
            r2.<init>((java.nio.channels.SeekableByteChannel) r3)
            r1.create((org.apache.commons.compress.archivers.sevenz.SevenZOutputFile) r2, (java.nio.file.Path) r4)     // Catch:{ all -> 0x0014 }
            r2.close()
            return
        L_0x0014:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0016 }
        L_0x0016:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x001b }
            goto L_0x001f
        L_0x001b:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x001f:
            throw r4
        L_0x0020:
            java.lang.String r0 = "zip"
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L_0x0045
            org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream r2 = new org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
            r2.<init>((java.nio.channels.SeekableByteChannel) r3)
            java.util.EnumSet<java.nio.file.FileVisitOption> r3 = EMPTY_FileVisitOption     // Catch:{ all -> 0x0039 }
            r0 = 0
            java.nio.file.LinkOption[] r0 = new java.nio.file.LinkOption[r0]     // Catch:{ all -> 0x0039 }
            r1.create((org.apache.commons.compress.archivers.ArchiveOutputStream) r2, (java.nio.file.Path) r4, (java.util.EnumSet<java.nio.file.FileVisitOption>) r3, (java.nio.file.LinkOption[]) r0)     // Catch:{ all -> 0x0039 }
            r2.close()
            return
        L_0x0039:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x003b }
        L_0x003b:
            r4 = move-exception
            r2.close()     // Catch:{ all -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r2 = move-exception
            r3.addSuppressed(r2)
        L_0x0044:
            throw r4
        L_0x0045:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            r3.<init>(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.examples.Archiver.create(java.lang.String, java.nio.channels.SeekableByteChannel, java.nio.file.Path):void");
    }

    private boolean prefersSeekableByteChannel(String str) {
        return ArchiveStreamFactory.ZIP.equalsIgnoreCase(str) || ArchiveStreamFactory.SEVEN_Z.equalsIgnoreCase(str);
    }
}
