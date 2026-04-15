package org.apache.commons.compress.archivers.tar;

import java.util.Objects;

public final class TarArchiveStructSparse {
    private final long numbytes;
    private final long offset;

    public TarArchiveStructSparse(long j, long j2) {
        if (j < 0) {
            throw new IllegalArgumentException("offset must not be negative");
        } else if (j2 >= 0) {
            this.offset = j;
            this.numbytes = j2;
        } else {
            throw new IllegalArgumentException("numbytes must not be negative");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TarArchiveStructSparse tarArchiveStructSparse = (TarArchiveStructSparse) obj;
            return this.offset == tarArchiveStructSparse.offset && this.numbytes == tarArchiveStructSparse.numbytes;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.offset), Long.valueOf(this.numbytes)});
    }

    public String toString() {
        return "TarArchiveStructSparse{offset=" + this.offset + ", numbytes=" + this.numbytes + '}';
    }

    public long getOffset() {
        return this.offset;
    }

    public long getNumbytes() {
        return this.numbytes;
    }
}
