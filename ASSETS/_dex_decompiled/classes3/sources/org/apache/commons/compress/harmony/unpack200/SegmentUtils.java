package org.apache.commons.compress.harmony.unpack200;

public final class SegmentUtils {
    public static int countArgs(String str) {
        return countArgs(str, 1);
    }

    public static int countInvokeInterfaceArgs(String str) {
        return countArgs(str, 2);
    }

    protected static int countArgs(String str, int i) {
        int indexOf = str.indexOf(40);
        int indexOf2 = str.indexOf(41);
        if (indexOf == -1 || indexOf2 == -1 || indexOf2 < indexOf) {
            throw new IllegalArgumentException("No arguments");
        }
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        for (int i3 = indexOf + 1; i3 < indexOf2; i3++) {
            char charAt = str.charAt(i3);
            if (z && charAt == ';') {
                z = false;
                z2 = false;
            } else if (!z && charAt == 'L') {
                i2++;
                z = true;
            } else if (charAt == '[') {
                z2 = true;
            } else if (!z) {
                if (z2) {
                    i2++;
                    z2 = false;
                } else {
                    i2 = (charAt == 'D' || charAt == 'J') ? i2 + i : i2 + 1;
                }
            }
        }
        return i2;
    }

    public static int countMatches(long[] jArr, IMatcher iMatcher) {
        int i = 0;
        for (long matches : jArr) {
            if (iMatcher.matches(matches)) {
                i++;
            }
        }
        return i;
    }

    public static int countBit16(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            if ((i2 & IcTuple.NESTED_CLASS_FLAG) != 0) {
                i++;
            }
        }
        return i;
    }

    public static int countBit16(long[] jArr) {
        int i = 0;
        for (long j : jArr) {
            if ((j & 65536) != 0) {
                i++;
            }
        }
        return i;
    }

    public static int countBit16(long[][] jArr) {
        int i = 0;
        for (long[] jArr2 : jArr) {
            int i2 = 0;
            while (true) {
                if (i2 >= jArr2.length) {
                    break;
                }
                if ((jArr2[i2] & 65536) != 0) {
                    i++;
                }
                i2++;
            }
        }
        return i;
    }

    public static int countMatches(long[][] jArr, IMatcher iMatcher) {
        int i = 0;
        for (long[] countMatches : jArr) {
            i += countMatches(countMatches, iMatcher);
        }
        return i;
    }
}
