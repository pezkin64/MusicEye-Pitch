package org.apache.commons.compress.harmony.pack200;

public class CPUTF8 extends ConstantPoolEntry implements Comparable {
    private final String string;

    public CPUTF8(String str) {
        this.string = str;
    }

    public int compareTo(Object obj) {
        return this.string.compareTo(((CPUTF8) obj).string);
    }

    public String toString() {
        return this.string;
    }

    public String getUnderlyingString() {
        return this.string;
    }
}
