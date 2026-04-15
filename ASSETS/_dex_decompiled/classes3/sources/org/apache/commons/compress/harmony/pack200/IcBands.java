package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.text.Typography;

public class IcBands extends BandSet {
    private int bit16Count = 0;
    private final CpBands cpBands;
    private final Set innerClasses = new TreeSet();
    private final Map outerToInner = new HashMap();

    public IcBands(SegmentHeader segmentHeader, CpBands cpBands2, int i) {
        super(i, segmentHeader);
        this.cpBands = cpBands2;
    }

    public void finaliseBands() {
        this.segmentHeader.setIc_count(this.innerClasses.size());
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing internal class bands...");
        int size = this.innerClasses.size();
        int[] iArr = new int[size];
        int size2 = this.innerClasses.size();
        int[] iArr2 = new int[size2];
        int i = this.bit16Count;
        int[] iArr3 = new int[i];
        int[] iArr4 = new int[i];
        ArrayList arrayList = new ArrayList(this.innerClasses);
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            IcTuple icTuple = (IcTuple) arrayList.get(i3);
            iArr[i3] = icTuple.C.getIndex();
            iArr2[i3] = icTuple.F;
            if ((icTuple.F & org.apache.commons.compress.harmony.unpack200.IcTuple.NESTED_CLASS_FLAG) != 0) {
                iArr3[i2] = icTuple.C2 == null ? 0 : icTuple.C2.getIndex() + 1;
                iArr4[i2] = icTuple.N == null ? 0 : icTuple.N.getIndex() + 1;
                i2++;
            }
        }
        byte[] encodeBandInt = encodeBandInt("ic_this_class", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from ic_this_class[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("ic_flags", iArr2, Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from ic_flags[" + size2 + "]");
        byte[] encodeBandInt3 = encodeBandInt("ic_outer_class", iArr3, Codec.DELTA5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from ic_outer_class[" + i + "]");
        byte[] encodeBandInt4 = encodeBandInt("ic_name", iArr4, Codec.DELTA5);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from ic_name[" + i + "]");
    }

    public void addInnerClass(String str, String str2, String str3, int i) {
        if (str2 == null && str3 == null) {
            IcTuple icTuple = new IcTuple(this.cpBands.getCPClass(str), i, (CPClass) null, (CPUTF8) null);
            addToMap(getOuter(str), icTuple);
            this.innerClasses.add(icTuple);
        } else if (namesArePredictable(str, str2, str3)) {
            IcTuple icTuple2 = new IcTuple(this.cpBands.getCPClass(str), i, (CPClass) null, (CPUTF8) null);
            addToMap(str2, icTuple2);
            this.innerClasses.add(icTuple2);
        } else {
            int i2 = i | org.apache.commons.compress.harmony.unpack200.IcTuple.NESTED_CLASS_FLAG;
            IcTuple icTuple3 = new IcTuple(this.cpBands.getCPClass(str), i2, this.cpBands.getCPClass(str2), this.cpBands.getCPUtf8(str3));
            if (this.innerClasses.add(icTuple3)) {
                this.bit16Count++;
                addToMap(str2, icTuple3);
            }
        }
    }

    public List getInnerClassesForOuter(String str) {
        return (List) this.outerToInner.get(str);
    }

    private String getOuter(String str) {
        return str.substring(0, str.lastIndexOf(36));
    }

    private void addToMap(String str, IcTuple icTuple) {
        List<IcTuple> list = (List) this.outerToInner.get(str);
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            this.outerToInner.put(str, arrayList);
            arrayList.add(icTuple);
            return;
        }
        for (IcTuple equals : list) {
            if (icTuple.equals(equals)) {
                return;
            }
        }
        list.add(icTuple);
    }

    private boolean namesArePredictable(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(Typography.dollar);
        sb.append(str3);
        return str.equals(sb.toString()) && str3.indexOf(36) == -1;
    }

    class IcTuple implements Comparable {
        protected CPClass C;
        protected CPClass C2;
        protected int F;
        protected CPUTF8 N;

        public IcTuple(CPClass cPClass, int i, CPClass cPClass2, CPUTF8 cputf8) {
            this.C = cPClass;
            this.F = i;
            this.C2 = cPClass2;
            this.N = cputf8;
        }

        public boolean equals(Object obj) {
            CPClass cPClass;
            if (obj instanceof IcTuple) {
                IcTuple icTuple = (IcTuple) obj;
                if (this.C.equals(icTuple.C) && this.F == icTuple.F && ((cPClass = this.C2) == null ? icTuple.C2 == null : cPClass.equals(icTuple.C2))) {
                    CPUTF8 cputf8 = this.N;
                    if (cputf8 != null) {
                        if (cputf8.equals(icTuple.N)) {
                            return true;
                        }
                    } else if (icTuple.N == null) {
                        return true;
                    }
                }
            }
            return false;
        }

        public String toString() {
            return this.C.toString();
        }

        public int compareTo(Object obj) {
            return this.C.compareTo(((IcTuple) obj).C);
        }

        public boolean isAnonymous() {
            String cPClass = this.C.toString();
            return Character.isDigit(cPClass.substring(cPClass.lastIndexOf(36) + 1).charAt(0));
        }
    }

    public IcTuple getIcTuple(CPClass cPClass) {
        for (IcTuple icTuple : this.innerClasses) {
            if (icTuple.C.equals(cPClass)) {
                return icTuple;
            }
        }
        return null;
    }
}
