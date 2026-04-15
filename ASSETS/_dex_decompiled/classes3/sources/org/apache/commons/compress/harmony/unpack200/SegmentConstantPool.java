package org.apache.commons.compress.harmony.unpack200;

import java.util.List;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

public class SegmentConstantPool {
    public static final int ALL = 0;
    public static final int CP_CLASS = 7;
    public static final int CP_DESCR = 9;
    public static final int CP_DOUBLE = 5;
    public static final int CP_FIELD = 10;
    public static final int CP_FLOAT = 3;
    public static final int CP_IMETHOD = 12;
    public static final int CP_INT = 2;
    public static final int CP_LONG = 4;
    public static final int CP_METHOD = 11;
    public static final int CP_STRING = 6;
    protected static final String INITSTRING = "<init>";
    protected static final String REGEX_MATCH_ALL = ".*";
    protected static final String REGEX_MATCH_INIT = "^<init>.*";
    public static final int SIGNATURE = 8;
    public static final int UTF_8 = 1;
    private final SegmentConstantPoolArrayCache arrayCache = new SegmentConstantPoolArrayCache();
    private final CpBands bands;

    public SegmentConstantPool(CpBands cpBands) {
        this.bands = cpBands;
    }

    public ClassFileEntry getValue(int i, long j) throws Pack200Exception {
        int i2 = (int) j;
        if (i2 == -1) {
            return null;
        }
        if (i2 < 0) {
            throw new Pack200Exception("Cannot have a negative range");
        } else if (i == 1) {
            return this.bands.cpUTF8Value(i2);
        } else {
            if (i == 2) {
                return this.bands.cpIntegerValue(i2);
            }
            if (i == 3) {
                return this.bands.cpFloatValue(i2);
            }
            if (i == 4) {
                return this.bands.cpLongValue(i2);
            }
            if (i == 5) {
                return this.bands.cpDoubleValue(i2);
            }
            if (i == 6) {
                return this.bands.cpStringValue(i2);
            }
            if (i == 7) {
                return this.bands.cpClassValue(i2);
            }
            if (i == 8) {
                return this.bands.cpSignatureValue(i2);
            }
            if (i == 9) {
                return this.bands.cpNameAndTypeValue(i2);
            }
            throw new Error("Tried to get a value I don't know about: " + i);
        }
    }

    public ConstantPoolEntry getClassSpecificPoolEntry(int i, long j, String str) throws Pack200Exception {
        String[] strArr;
        int i2 = (int) j;
        if (i == 10) {
            strArr = this.bands.getCpFieldClass();
        } else if (i == 11) {
            strArr = this.bands.getCpMethodClass();
        } else if (i == 12) {
            strArr = this.bands.getCpIMethodClass();
        } else {
            throw new Error("Don't know how to handle " + i);
        }
        return getConstantPoolEntry(i, (long) matchSpecificPoolEntryIndex(strArr, str, i2));
    }

    public ConstantPoolEntry getClassPoolEntry(String str) {
        int matchSpecificPoolEntryIndex = matchSpecificPoolEntryIndex(this.bands.getCpClass(), str, 0);
        if (matchSpecificPoolEntryIndex == -1) {
            return null;
        }
        try {
            return getConstantPoolEntry(7, (long) matchSpecificPoolEntryIndex);
        } catch (Pack200Exception unused) {
            throw new Error("Error getting class pool entry");
        }
    }

    public ConstantPoolEntry getInitMethodPoolEntry(int i, long j, String str) throws Pack200Exception {
        if (i == 11) {
            return getConstantPoolEntry(i, (long) matchSpecificPoolEntryIndex(this.bands.getCpMethodClass(), this.bands.getCpMethodDescriptor(), str, REGEX_MATCH_INIT, (int) j));
        }
        throw new Error("Nothing but CP_METHOD can be an <init>");
    }

    /* access modifiers changed from: protected */
    public int matchSpecificPoolEntryIndex(String[] strArr, String str, int i) {
        return matchSpecificPoolEntryIndex(strArr, strArr, str, REGEX_MATCH_ALL, i);
    }

    /* access modifiers changed from: protected */
    public int matchSpecificPoolEntryIndex(String[] strArr, String[] strArr2, String str, String str2, int i) {
        List indexesForArrayKey = this.arrayCache.indexesForArrayKey(strArr, str);
        if (indexesForArrayKey.isEmpty()) {
            return -1;
        }
        int i2 = -1;
        for (int i3 = 0; i3 < indexesForArrayKey.size(); i3++) {
            int intValue = ((Integer) indexesForArrayKey.get(i3)).intValue();
            if (regexMatches(str2, strArr2[intValue]) && (i2 = i2 + 1) == i) {
                return intValue;
            }
        }
        return -1;
    }

    protected static boolean regexMatches(String str, String str2) {
        if (REGEX_MATCH_ALL.equals(str)) {
            return true;
        }
        if (!REGEX_MATCH_INIT.equals(str)) {
            throw new Error("regex trying to match a pattern I don't know: " + str);
        } else if (str2.length() < 6) {
            return false;
        } else {
            return INITSTRING.equals(str2.substring(0, 6));
        }
    }

    public ConstantPoolEntry getConstantPoolEntry(int i, long j) throws Pack200Exception {
        int i2 = (int) j;
        if (i2 == -1) {
            return null;
        }
        if (i2 < 0) {
            throw new Pack200Exception("Cannot have a negative range");
        } else if (i == 1) {
            return this.bands.cpUTF8Value(i2);
        } else {
            if (i == 2) {
                return this.bands.cpIntegerValue(i2);
            }
            if (i == 3) {
                return this.bands.cpFloatValue(i2);
            }
            if (i == 4) {
                return this.bands.cpLongValue(i2);
            }
            if (i == 5) {
                return this.bands.cpDoubleValue(i2);
            }
            if (i == 6) {
                return this.bands.cpStringValue(i2);
            }
            if (i == 7) {
                return this.bands.cpClassValue(i2);
            }
            if (i == 8) {
                throw new Error("I don't know what to do with signatures yet");
            } else if (i == 9) {
                throw new Error("I don't know what to do with descriptors yet");
            } else if (i == 10) {
                return this.bands.cpFieldValue(i2);
            } else {
                if (i == 11) {
                    return this.bands.cpMethodValue(i2);
                }
                if (i == 12) {
                    return this.bands.cpIMethodValue(i2);
                }
                throw new Error("Get value incomplete");
            }
        }
    }
}
