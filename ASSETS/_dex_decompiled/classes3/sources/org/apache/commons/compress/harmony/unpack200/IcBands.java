package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantPoolEntry;

public class IcBands extends BandSet {
    private final String[] cpClass;
    private final String[] cpUTF8;
    private IcTuple[] icAll;
    private Map outerClassToTuples;
    private Map thisClassToTuple;

    public IcBands(Segment segment) {
        super(segment);
        this.cpClass = segment.getCpBands().getCpClass();
        this.cpUTF8 = segment.getCpBands().getCpUTF8();
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        int i2;
        String str;
        String str2;
        InputStream inputStream2 = inputStream;
        int innerClassCount = this.header.getInnerClassCount();
        int[] decodeBandInt = decodeBandInt("ic_this_class", inputStream2, Codec.UDELTA5, innerClassCount);
        String[] references = getReferences(decodeBandInt, this.cpClass);
        int[] decodeBandInt2 = decodeBandInt("ic_flags", inputStream2, Codec.UNSIGNED5, innerClassCount);
        int countBit16 = SegmentUtils.countBit16(decodeBandInt2);
        int[] decodeBandInt3 = decodeBandInt("ic_outer_class", inputStream2, Codec.DELTA5, countBit16);
        String[] strArr = new String[countBit16];
        int i3 = 0;
        for (int i4 = 0; i4 < countBit16; i4++) {
            int i5 = decodeBandInt3[i4];
            if (i5 == 0) {
                strArr[i4] = null;
            } else {
                strArr[i4] = this.cpClass[i5 - 1];
            }
        }
        int[] decodeBandInt4 = decodeBandInt("ic_name", inputStream2, Codec.DELTA5, countBit16);
        String[] strArr2 = new String[countBit16];
        for (int i6 = 0; i6 < countBit16; i6++) {
            int i7 = decodeBandInt4[i6];
            if (i7 == 0) {
                strArr2[i6] = null;
            } else {
                strArr2[i6] = this.cpUTF8[i7 - 1];
            }
        }
        this.icAll = new IcTuple[references.length];
        int i8 = 0;
        while (i3 < references.length) {
            String str3 = references[i3];
            int i9 = decodeBandInt2[i3];
            int i10 = decodeBandInt[i3];
            if ((65536 & i9) != 0) {
                String str4 = strArr[i8];
                String str5 = strArr2[i8];
                i8++;
                i = decodeBandInt4[i8] - 1;
                i2 = decodeBandInt3[i8] - 1;
                str = str5;
                str2 = str4;
            } else {
                str2 = null;
                i2 = -1;
                i = -1;
                str = null;
            }
            int i11 = i3;
            this.icAll[i11] = new IcTuple(str3, i9, str2, str, i10, i2, i, i11);
            i3 = i11 + 1;
        }
    }

    public void unpack() throws IOException, Pack200Exception {
        IcTuple[] icTuples = getIcTuples();
        this.thisClassToTuple = new HashMap(icTuples.length);
        this.outerClassToTuples = new HashMap(icTuples.length);
        int i = 0;
        while (i < icTuples.length) {
            IcTuple icTuple = icTuples[i];
            if (this.thisClassToTuple.put(icTuple.thisClassString(), icTuple) == null) {
                if ((!icTuple.isAnonymous() && !icTuple.outerIsAnonymous()) || icTuple.nestedExplicitFlagSet()) {
                    String outerClassString = icTuple.outerClassString();
                    List list = (List) this.outerClassToTuples.get(outerClassString);
                    if (list == null) {
                        list = new ArrayList();
                        this.outerClassToTuples.put(outerClassString, list);
                    }
                    list.add(icTuple);
                }
                i++;
            } else {
                throw new Error("Collision detected in <thisClassString, IcTuple> mapping. There are at least two inner clases with the same name.");
            }
        }
    }

    public IcTuple[] getIcTuples() {
        return this.icAll;
    }

    public IcTuple[] getRelevantIcTuples(String str, ClassConstantPool classConstantPool) {
        IcTuple icTuple;
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        List list = (List) this.outerClassToTuples.get(str);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                IcTuple icTuple2 = (IcTuple) list.get(i);
                hashSet.add(icTuple2);
                arrayList.add(icTuple2);
            }
        }
        List entries = classConstantPool.entries();
        for (int i2 = 0; i2 < entries.size(); i2++) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) entries.get(i2);
            if ((constantPoolEntry instanceof CPClass) && (icTuple = (IcTuple) this.thisClassToTuple.get(((CPClass) constantPoolEntry).name)) != null && hashSet.add(icTuple)) {
                arrayList.add(icTuple);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        ArrayList arrayList3 = new ArrayList();
        while (arrayList2.size() > 0) {
            arrayList3.clear();
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                IcTuple icTuple3 = (IcTuple) arrayList2.get(i3);
                IcTuple icTuple4 = (IcTuple) this.thisClassToTuple.get(icTuple3.outerClassString());
                if (icTuple4 != null && !icTuple3.outerIsAnonymous()) {
                    arrayList3.add(icTuple4);
                }
            }
            arrayList2.clear();
            for (int i4 = 0; i4 < arrayList3.size(); i4++) {
                IcTuple icTuple5 = (IcTuple) arrayList3.get(i4);
                if (hashSet.add(icTuple5)) {
                    arrayList.add(icTuple5);
                    arrayList2.add(icTuple5);
                }
            }
        }
        Collections.sort(arrayList, new IcBands$$ExternalSyntheticLambda0());
        int size = arrayList.size();
        IcTuple[] icTupleArr = new IcTuple[size];
        for (int i5 = 0; i5 < size; i5++) {
            icTupleArr[i5] = (IcTuple) arrayList.get(i5);
        }
        return icTupleArr;
    }
}
