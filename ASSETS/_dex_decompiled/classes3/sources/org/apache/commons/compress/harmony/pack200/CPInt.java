package org.apache.commons.compress.harmony.pack200;

public class CPInt extends CPConstant {
    private final int theInt;

    public CPInt(int i) {
        this.theInt = i;
    }

    public int compareTo(Object obj) {
        int i = this.theInt;
        int i2 = ((CPInt) obj).theInt;
        if (i > i2) {
            return 1;
        }
        return i == i2 ? 0 : -1;
    }

    public int getInt() {
        return this.theInt;
    }
}
