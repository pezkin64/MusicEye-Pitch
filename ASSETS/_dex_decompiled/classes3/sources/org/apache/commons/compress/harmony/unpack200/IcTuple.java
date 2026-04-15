package org.apache.commons.compress.harmony.unpack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.util.ArrayList;
import kotlin.text.Typography;

public class IcTuple {
    public static final int NESTED_CLASS_FLAG = 65536;
    protected String C;
    protected String C2;
    protected int F;
    protected String N;
    private boolean anonymous;
    private final int c2Index;
    private final int cIndex;
    private int cachedHashCode;
    private int cachedOuterClassIndex = -1;
    private String cachedOuterClassString;
    private String cachedSimpleClassName;
    private int cachedSimpleClassNameIndex = -1;
    private boolean hashcodeComputed;
    private boolean initialized;
    private boolean member = true;
    private final int nIndex;
    private boolean outerIsAnonymous;
    private boolean predictOuter;
    private boolean predictSimple;
    private final int tIndex;

    public IcTuple(String str, int i, String str2, String str3, int i2, int i3, int i4, int i5) {
        this.C = str;
        this.F = i;
        this.C2 = str2;
        this.N = str3;
        this.cIndex = i2;
        this.c2Index = i3;
        this.nIndex = i4;
        this.tIndex = i5;
        if (str3 == null) {
            this.predictSimple = true;
        }
        if (str2 == null) {
            this.predictOuter = true;
        }
        initializeClassStrings();
    }

    public boolean predicted() {
        return this.predictOuter || this.predictSimple;
    }

    public boolean nestedExplicitFlagSet() {
        return (this.F & NESTED_CLASS_FLAG) == 65536;
    }

    public String[] innerBreakAtDollar(String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) <= '$') {
                arrayList.add(str.substring(i2, i));
                i2 = i + 1;
            }
            i++;
            if (i >= str.length()) {
                arrayList.add(str.substring(i2));
            }
        }
        String[] strArr = new String[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            strArr[i3] = (String) arrayList.get(i3);
        }
        return strArr;
    }

    public String outerClassString() {
        return this.cachedOuterClassString;
    }

    public String simpleClassName() {
        return this.cachedSimpleClassName;
    }

    public String thisClassString() {
        if (predicted()) {
            return this.C;
        }
        return this.C2 + "$" + this.N;
    }

    public boolean isMember() {
        return this.member;
    }

    public boolean isAnonymous() {
        return this.anonymous;
    }

    public boolean outerIsAnonymous() {
        return this.outerIsAnonymous;
    }

    private boolean computeOuterIsAnonymous() {
        String[] innerBreakAtDollar = innerBreakAtDollar(this.cachedOuterClassString);
        if (innerBreakAtDollar.length != 0) {
            for (String isAllDigits : innerBreakAtDollar) {
                if (isAllDigits(isAllDigits)) {
                    return true;
                }
            }
            return false;
        }
        throw new Error("Should have an outer before checking if it's anonymous");
    }

    private void initializeClassStrings() {
        if (!this.initialized) {
            this.initialized = true;
            if (!this.predictSimple) {
                this.cachedSimpleClassName = this.N;
            }
            if (!this.predictOuter) {
                this.cachedOuterClassString = this.C2;
            }
            String[] innerBreakAtDollar = innerBreakAtDollar(this.C);
            int length = innerBreakAtDollar.length;
            int length2 = innerBreakAtDollar.length;
            if (innerBreakAtDollar.length >= 2) {
                int length3 = innerBreakAtDollar.length - 1;
                this.cachedSimpleClassName = innerBreakAtDollar[length3];
                this.cachedOuterClassString = BuildConfig.FLAVOR;
                int i = 0;
                while (i < length3) {
                    this.cachedOuterClassString += innerBreakAtDollar[i];
                    if (isAllDigits(innerBreakAtDollar[i])) {
                        this.member = false;
                    }
                    i++;
                    if (i != length3) {
                        this.cachedOuterClassString += Typography.dollar;
                    }
                }
                if (!this.predictSimple) {
                    this.cachedSimpleClassName = this.N;
                    this.cachedSimpleClassNameIndex = this.nIndex;
                }
                if (!this.predictOuter) {
                    this.cachedOuterClassString = this.C2;
                    this.cachedOuterClassIndex = this.c2Index;
                }
                if (isAllDigits(this.cachedSimpleClassName)) {
                    this.anonymous = true;
                    this.member = false;
                    if (nestedExplicitFlagSet()) {
                        this.member = true;
                    }
                }
                this.outerIsAnonymous = computeOuterIsAnonymous();
            }
        }
    }

    private boolean isAllDigits(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("IcTuple (");
        stringBuffer.append(simpleClassName());
        stringBuffer.append(" in ");
        stringBuffer.append(outerClassString());
        stringBuffer.append(')');
        return stringBuffer.toString();
    }

    public boolean nullSafeEquals(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        IcTuple icTuple = (IcTuple) obj;
        if (nullSafeEquals(this.C, icTuple.C) && nullSafeEquals(this.C2, icTuple.C2) && nullSafeEquals(this.N, icTuple.N)) {
            return true;
        }
        return false;
    }

    private void generateHashCode() {
        this.hashcodeComputed = true;
        this.cachedHashCode = 17;
        String str = this.C;
        if (str != null) {
            this.cachedHashCode = str.hashCode();
        }
        String str2 = this.C2;
        if (str2 != null) {
            this.cachedHashCode = str2.hashCode();
        }
        String str3 = this.N;
        if (str3 != null) {
            this.cachedHashCode = str3.hashCode();
        }
    }

    public int hashCode() {
        if (!this.hashcodeComputed) {
            generateHashCode();
        }
        return this.cachedHashCode;
    }

    public String getC() {
        return this.C;
    }

    public int getF() {
        return this.F;
    }

    public String getC2() {
        return this.C2;
    }

    public String getN() {
        return this.N;
    }

    public int getTupleIndex() {
        return this.tIndex;
    }

    public int thisClassIndex() {
        if (predicted()) {
            return this.cIndex;
        }
        return -1;
    }

    public int outerClassIndex() {
        return this.cachedOuterClassIndex;
    }

    public int simpleClassNameIndex() {
        return this.cachedSimpleClassNameIndex;
    }
}
