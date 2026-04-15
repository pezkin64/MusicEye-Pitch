package org.apache.commons.compress.archivers.ar;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.utils.ArchiveUtils;

public class ArArchiveOutputStream extends ArchiveOutputStream {
    public static final int LONGFILE_BSD = 1;
    public static final int LONGFILE_ERROR = 0;
    private long entryOffset;
    private boolean finished;
    private boolean haveUnclosedEntry;
    private int longFileMode = 0;
    private final OutputStream out;
    private ArArchiveEntry prevEntry;

    public ArArchiveOutputStream(OutputStream outputStream) {
        this.out = outputStream;
    }

    public void setLongFileMode(int i) {
        this.longFileMode = i;
    }

    private void writeArchiveHeader() throws IOException {
        this.out.write(ArchiveUtils.toAsciiBytes(ArArchiveEntry.HEADER));
    }

    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        } else if (this.prevEntry == null || !this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        } else {
            if (this.entryOffset % 2 != 0) {
                this.out.write(10);
            }
            this.haveUnclosedEntry = false;
        }
    }

    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (!this.finished) {
            ArArchiveEntry arArchiveEntry = (ArArchiveEntry) archiveEntry;
            ArArchiveEntry arArchiveEntry2 = this.prevEntry;
            if (arArchiveEntry2 == null) {
                writeArchiveHeader();
            } else if (arArchiveEntry2.getLength() != this.entryOffset) {
                throw new IOException("Length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset);
            } else if (this.haveUnclosedEntry) {
                closeArchiveEntry();
            }
            this.prevEntry = arArchiveEntry;
            writeEntryHeader(arArchiveEntry);
            this.entryOffset = 0;
            this.haveUnclosedEntry = true;
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    private long fill(long j, long j2, char c) throws IOException {
        long j3 = j2 - j;
        if (j3 > 0) {
            for (int i = 0; ((long) i) < j3; i++) {
                write(c);
            }
        }
        return j2;
    }

    private long write(String str) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        write(bytes);
        return (long) bytes.length;
    }

    private void writeEntryHeader(ArArchiveEntry arArchiveEntry) throws IOException {
        long j;
        String name = arArchiveEntry.getName();
        int length = name.length();
        int i = this.longFileMode;
        if (i != 0 || length <= 16) {
            boolean z = true;
            if (1 != i || (length <= 16 && !name.contains(" "))) {
                j = write(name);
                z = false;
            } else {
                j = write("#1/" + String.valueOf(length));
            }
            long fill = fill(j, 16, ' ');
            String str = BuildConfig.FLAVOR + arArchiveEntry.getLastModified();
            if (str.length() <= 12) {
                long fill2 = fill(write(str) + fill, 28, ' ');
                String str2 = BuildConfig.FLAVOR + arArchiveEntry.getUserId();
                if (str2.length() <= 6) {
                    long fill3 = fill(write(str2) + fill2, 34, ' ');
                    String str3 = BuildConfig.FLAVOR + arArchiveEntry.getGroupId();
                    if (str3.length() <= 6) {
                        long fill4 = fill(write(str3) + fill3, 40, ' ');
                        String str4 = BuildConfig.FLAVOR + Integer.toString(arArchiveEntry.getMode(), 8);
                        if (str4.length() <= 8) {
                            long fill5 = fill(write(str4) + fill4, 48, ' ');
                            long length2 = arArchiveEntry.getLength();
                            if (!z) {
                                length = 0;
                            }
                            String valueOf = String.valueOf(length2 + ((long) length));
                            if (valueOf.length() <= 10) {
                                fill(write(valueOf) + fill5, 58, ' ');
                                write(ArArchiveEntry.TRAILER);
                                if (z) {
                                    write(name);
                                    return;
                                }
                                return;
                            }
                            throw new IOException("Size too long");
                        }
                        throw new IOException("Filemode too long");
                    }
                    throw new IOException("Group id too long");
                }
                throw new IOException("User id too long");
            }
            throw new IOException("Last modified too long");
        }
        throw new IOException("File name too long, > 16 chars: " + name);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        count(i2);
        this.entryOffset += (long) i2;
    }

    public void close() throws IOException {
        try {
            if (!this.finished) {
                finish();
            }
        } finally {
            this.out.close();
            this.prevEntry = null;
        }
    }

    public ArchiveEntry createArchiveEntry(File file, String str) throws IOException {
        if (!this.finished) {
            return new ArArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }

    public ArchiveEntry createArchiveEntry(Path path, String str, LinkOption... linkOptionArr) throws IOException {
        if (!this.finished) {
            return new ArArchiveEntry(path, str, linkOptionArr);
        }
        throw new IOException("Stream has already been finished");
    }

    public void finish() throws IOException {
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        } else if (!this.finished) {
            this.finished = true;
        } else {
            throw new IOException("This archive has already been finished");
        }
    }
}
