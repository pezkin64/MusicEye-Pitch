package org.apache.commons.compress.harmony.unpack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;

public class CpBands extends BandSet {
    private int classOffset;
    private String[] cpClass;
    private int[] cpClassInts;
    private String[] cpDescriptor;
    private int[] cpDescriptorNameInts;
    private int[] cpDescriptorTypeInts;
    private double[] cpDouble;
    private String[] cpFieldClass;
    private int[] cpFieldClassInts;
    private String[] cpFieldDescriptor;
    private int[] cpFieldDescriptorInts;
    private float[] cpFloat;
    private String[] cpIMethodClass;
    private int[] cpIMethodClassInts;
    private String[] cpIMethodDescriptor;
    private int[] cpIMethodDescriptorInts;
    private int[] cpInt;
    private long[] cpLong;
    private String[] cpMethodClass;
    private int[] cpMethodClassInts;
    private String[] cpMethodDescriptor;
    private int[] cpMethodDescriptorInts;
    private String[] cpSignature;
    private int[] cpSignatureInts;
    private String[] cpString;
    private int[] cpStringInts;
    private String[] cpUTF8;
    private int descrOffset;
    private final Map descriptorsToCPNameAndTypes = new HashMap();
    private int doubleOffset;
    private final Map doublesToCPDoubles = new HashMap();
    private int fieldOffset;
    private int floatOffset;
    private final Map floatsToCPFloats = new HashMap();
    private int imethodOffset;
    private int intOffset;
    private final Map integersToCPIntegers = new HashMap();
    private int longOffset;
    private final Map longsToCPLongs = new HashMap();
    private Map mapClass;
    private Map mapDescriptor;
    private Map mapSignature;
    private Map mapUTF8;
    private int methodOffset;
    private final SegmentConstantPool pool = new SegmentConstantPool(this);
    private int signatureOffset;
    private int stringOffset;
    private final Map stringsToCPClass = new HashMap();
    private final Map stringsToCPStrings = new HashMap();
    private final Map stringsToCPUTF8 = new HashMap();

    public void unpack() {
    }

    public SegmentConstantPool getConstantPool() {
        return this.pool;
    }

    public CpBands(Segment segment) {
        super(segment);
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        parseCpUtf8(inputStream);
        parseCpInt(inputStream);
        parseCpFloat(inputStream);
        parseCpLong(inputStream);
        parseCpDouble(inputStream);
        parseCpString(inputStream);
        parseCpClass(inputStream);
        parseCpSignature(inputStream);
        parseCpDescriptor(inputStream);
        parseCpField(inputStream);
        parseCpMethod(inputStream);
        parseCpIMethod(inputStream);
        int length = this.cpUTF8.length;
        this.intOffset = length;
        int length2 = length + this.cpInt.length;
        this.floatOffset = length2;
        int length3 = length2 + this.cpFloat.length;
        this.longOffset = length3;
        int length4 = length3 + this.cpLong.length;
        this.doubleOffset = length4;
        int length5 = length4 + this.cpDouble.length;
        this.stringOffset = length5;
        int length6 = length5 + this.cpString.length;
        this.classOffset = length6;
        int length7 = length6 + this.cpClass.length;
        this.signatureOffset = length7;
        int length8 = length7 + this.cpSignature.length;
        this.descrOffset = length8;
        int length9 = length8 + this.cpDescriptor.length;
        this.fieldOffset = length9;
        int length10 = length9 + this.cpFieldClass.length;
        this.methodOffset = length10;
        this.imethodOffset = length10 + this.cpMethodClass.length;
    }

    private void parseCpClass(InputStream inputStream) throws IOException, Pack200Exception {
        int cpClassCount = this.header.getCpClassCount();
        this.cpClassInts = decodeBandInt("cp_Class", inputStream, Codec.UDELTA5, cpClassCount);
        this.cpClass = new String[cpClassCount];
        this.mapClass = new HashMap(cpClassCount);
        for (int i = 0; i < cpClassCount; i++) {
            String[] strArr = this.cpClass;
            String str = this.cpUTF8[this.cpClassInts[i]];
            strArr[i] = str;
            this.mapClass.put(str, Integer.valueOf(i));
        }
    }

    private void parseCpDescriptor(InputStream inputStream) throws IOException, Pack200Exception {
        int cpDescriptorCount = this.header.getCpDescriptorCount();
        this.cpDescriptorNameInts = decodeBandInt("cp_Descr_name", inputStream, Codec.DELTA5, cpDescriptorCount);
        this.cpDescriptorTypeInts = decodeBandInt("cp_Descr_type", inputStream, Codec.UDELTA5, cpDescriptorCount);
        String[] references = getReferences(this.cpDescriptorNameInts, this.cpUTF8);
        String[] references2 = getReferences(this.cpDescriptorTypeInts, this.cpSignature);
        this.cpDescriptor = new String[cpDescriptorCount];
        this.mapDescriptor = new HashMap(cpDescriptorCount);
        for (int i = 0; i < cpDescriptorCount; i++) {
            String[] strArr = this.cpDescriptor;
            strArr[i] = references[i] + ":" + references2[i];
            this.mapDescriptor.put(this.cpDescriptor[i], Integer.valueOf(i));
        }
    }

    private void parseCpDouble(InputStream inputStream) throws IOException, Pack200Exception {
        long[] parseFlags = parseFlags("cp_Double", inputStream, this.header.getCpDoubleCount(), Codec.UDELTA5, Codec.DELTA5);
        this.cpDouble = new double[parseFlags.length];
        for (int i = 0; i < parseFlags.length; i++) {
            this.cpDouble[i] = Double.longBitsToDouble(parseFlags[i]);
        }
    }

    private void parseCpField(InputStream inputStream) throws IOException, Pack200Exception {
        int cpFieldCount = this.header.getCpFieldCount();
        this.cpFieldClassInts = decodeBandInt("cp_Field_class", inputStream, Codec.DELTA5, cpFieldCount);
        this.cpFieldDescriptorInts = decodeBandInt("cp_Field_desc", inputStream, Codec.UDELTA5, cpFieldCount);
        this.cpFieldClass = new String[cpFieldCount];
        this.cpFieldDescriptor = new String[cpFieldCount];
        for (int i = 0; i < cpFieldCount; i++) {
            this.cpFieldClass[i] = this.cpClass[this.cpFieldClassInts[i]];
            this.cpFieldDescriptor[i] = this.cpDescriptor[this.cpFieldDescriptorInts[i]];
        }
    }

    private void parseCpFloat(InputStream inputStream) throws IOException, Pack200Exception {
        int cpFloatCount = this.header.getCpFloatCount();
        this.cpFloat = new float[cpFloatCount];
        int[] decodeBandInt = decodeBandInt("cp_Float", inputStream, Codec.UDELTA5, cpFloatCount);
        for (int i = 0; i < cpFloatCount; i++) {
            this.cpFloat[i] = Float.intBitsToFloat(decodeBandInt[i]);
        }
    }

    private void parseCpIMethod(InputStream inputStream) throws IOException, Pack200Exception {
        int cpIMethodCount = this.header.getCpIMethodCount();
        this.cpIMethodClassInts = decodeBandInt("cp_Imethod_class", inputStream, Codec.DELTA5, cpIMethodCount);
        this.cpIMethodDescriptorInts = decodeBandInt("cp_Imethod_desc", inputStream, Codec.UDELTA5, cpIMethodCount);
        this.cpIMethodClass = new String[cpIMethodCount];
        this.cpIMethodDescriptor = new String[cpIMethodCount];
        for (int i = 0; i < cpIMethodCount; i++) {
            this.cpIMethodClass[i] = this.cpClass[this.cpIMethodClassInts[i]];
            this.cpIMethodDescriptor[i] = this.cpDescriptor[this.cpIMethodDescriptorInts[i]];
        }
    }

    private void parseCpInt(InputStream inputStream) throws IOException, Pack200Exception {
        this.cpInt = decodeBandInt("cpInt", inputStream, Codec.UDELTA5, this.header.getCpIntCount());
    }

    private void parseCpLong(InputStream inputStream) throws IOException, Pack200Exception {
        this.cpLong = parseFlags("cp_Long", inputStream, this.header.getCpLongCount(), Codec.UDELTA5, Codec.DELTA5);
    }

    private void parseCpMethod(InputStream inputStream) throws IOException, Pack200Exception {
        int cpMethodCount = this.header.getCpMethodCount();
        this.cpMethodClassInts = decodeBandInt("cp_Method_class", inputStream, Codec.DELTA5, cpMethodCount);
        this.cpMethodDescriptorInts = decodeBandInt("cp_Method_desc", inputStream, Codec.UDELTA5, cpMethodCount);
        this.cpMethodClass = new String[cpMethodCount];
        this.cpMethodDescriptor = new String[cpMethodCount];
        for (int i = 0; i < cpMethodCount; i++) {
            this.cpMethodClass[i] = this.cpClass[this.cpMethodClassInts[i]];
            this.cpMethodDescriptor[i] = this.cpDescriptor[this.cpMethodDescriptorInts[i]];
        }
    }

    private void parseCpSignature(InputStream inputStream) throws IOException, Pack200Exception {
        int cpSignatureCount = this.header.getCpSignatureCount();
        int[] decodeBandInt = decodeBandInt("cp_Signature_form", inputStream, Codec.DELTA5, cpSignatureCount);
        this.cpSignatureInts = decodeBandInt;
        String[] references = getReferences(decodeBandInt, this.cpUTF8);
        this.cpSignature = new String[cpSignatureCount];
        this.mapSignature = new HashMap();
        int i = 0;
        for (int i2 = 0; i2 < cpSignatureCount; i2++) {
            char[] charArray = references[i2].toCharArray();
            for (char c : charArray) {
                if (c == 'L') {
                    this.cpSignatureInts[i2] = -1;
                    i++;
                }
            }
        }
        String[] parseReferences = parseReferences("cp_Signature_classes", inputStream, Codec.UDELTA5, i, this.cpClass);
        int i3 = 0;
        for (int i4 = 0; i4 < cpSignatureCount; i4++) {
            String str = references[i4];
            int length = str.length();
            StringBuffer stringBuffer = new StringBuffer(64);
            ArrayList arrayList = new ArrayList();
            for (int i5 = 0; i5 < length; i5++) {
                char charAt = str.charAt(i5);
                stringBuffer.append(charAt);
                if (charAt == 'L') {
                    String str2 = parseReferences[i3];
                    arrayList.add(str2);
                    stringBuffer.append(str2);
                    i3++;
                }
            }
            this.cpSignature[i4] = stringBuffer.toString();
            this.mapSignature.put(stringBuffer.toString(), Integer.valueOf(i4));
        }
    }

    private void parseCpString(InputStream inputStream) throws IOException, Pack200Exception {
        int cpStringCount = this.header.getCpStringCount();
        this.cpStringInts = decodeBandInt("cp_String", inputStream, Codec.UDELTA5, cpStringCount);
        this.cpString = new String[cpStringCount];
        for (int i = 0; i < cpStringCount; i++) {
            this.cpString[i] = this.cpUTF8[this.cpStringInts[i]];
        }
    }

    private void parseCpUtf8(InputStream inputStream) throws IOException, Pack200Exception {
        int cpUTF8Count = this.header.getCpUTF8Count();
        this.cpUTF8 = new String[cpUTF8Count];
        HashMap hashMap = new HashMap(cpUTF8Count + 1);
        this.mapUTF8 = hashMap;
        this.cpUTF8[0] = BuildConfig.FLAVOR;
        hashMap.put(BuildConfig.FLAVOR, 0);
        int[] decodeBandInt = decodeBandInt("cpUTF8Prefix", inputStream, Codec.DELTA5, cpUTF8Count - 2);
        int[] decodeBandInt2 = decodeBandInt("cpUTF8Suffix", inputStream, Codec.UNSIGNED5, cpUTF8Count - 1);
        int i = 0;
        int i2 = 0;
        for (int i3 : decodeBandInt2) {
            if (i3 == 0) {
                i2++;
            } else {
                i += i3;
            }
        }
        char[] cArr = new char[i];
        int[] decodeBandInt3 = decodeBandInt("cp_Utf8_chars", inputStream, Codec.CHAR3, i);
        for (int i4 = 0; i4 < i; i4++) {
            cArr[i4] = (char) decodeBandInt3[i4];
        }
        int[] decodeBandInt4 = decodeBandInt("cp_Utf8_big_suffix", inputStream, Codec.DELTA5, i2);
        int[][] iArr = new int[i2][];
        for (int i5 = 0; i5 < i2; i5++) {
            iArr[i5] = decodeBandInt("cp_Utf8_big_chars " + i5, inputStream, Codec.DELTA5, decodeBandInt4[i5]);
        }
        char[][] cArr2 = new char[i2][];
        for (int i6 = 0; i6 < i2; i6++) {
            cArr2[i6] = new char[iArr[i6].length];
            int i7 = 0;
            while (true) {
                int[] iArr2 = iArr[i6];
                if (i7 >= iArr2.length) {
                    break;
                }
                cArr2[i6][i7] = (char) iArr2[i7];
                i7++;
            }
        }
        int i8 = 0;
        int i9 = 0;
        int i10 = 1;
        while (i10 < cpUTF8Count) {
            String[] strArr = this.cpUTF8;
            int i11 = i10 - 1;
            String str = strArr[i11];
            if (decodeBandInt2[i11] == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.substring(0, i10 > 1 ? decodeBandInt[i10 - 2] : 0));
                sb.append(new String(cArr2[i9]));
                strArr[i10] = sb.toString();
                this.mapUTF8.put(this.cpUTF8[i10], Integer.valueOf(i10));
                i9++;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str.substring(0, i10 > 1 ? decodeBandInt[i10 - 2] : 0));
                sb2.append(new String(cArr, i8, decodeBandInt2[i11]));
                strArr[i10] = sb2.toString();
                i8 += decodeBandInt2[i11];
                this.mapUTF8.put(this.cpUTF8[i10], Integer.valueOf(i10));
            }
            i10++;
        }
    }

    public String[] getCpClass() {
        return this.cpClass;
    }

    public String[] getCpDescriptor() {
        return this.cpDescriptor;
    }

    public String[] getCpFieldClass() {
        return this.cpFieldClass;
    }

    public String[] getCpIMethodClass() {
        return this.cpIMethodClass;
    }

    public int[] getCpInt() {
        return this.cpInt;
    }

    public long[] getCpLong() {
        return this.cpLong;
    }

    public String[] getCpMethodClass() {
        return this.cpMethodClass;
    }

    public String[] getCpMethodDescriptor() {
        return this.cpMethodDescriptor;
    }

    public String[] getCpSignature() {
        return this.cpSignature;
    }

    public String[] getCpUTF8() {
        return this.cpUTF8;
    }

    public CPUTF8 cpUTF8Value(int i) {
        String str = this.cpUTF8[i];
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCPUTF8.get(str);
        if (cputf8 == null) {
            CPUTF8 cputf82 = new CPUTF8(str, i);
            this.stringsToCPUTF8.put(str, cputf82);
            return cputf82;
        }
        if (cputf8.getGlobalIndex() > i) {
            cputf8.setGlobalIndex(i);
        }
        return cputf8;
    }

    public CPUTF8 cpUTF8Value(String str) {
        return cpUTF8Value(str, true);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.lang.Integer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8 cpUTF8Value(java.lang.String r2, boolean r3) {
        /*
            r1 = this;
            java.util.Map r0 = r1.stringsToCPUTF8
            java.lang.Object r0 = r0.get(r2)
            org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8 r0 = (org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8) r0
            if (r0 != 0) goto L_0x0043
            if (r3 == 0) goto L_0x0015
            java.util.Map r0 = r1.mapUTF8
            java.lang.Object r0 = r0.get(r2)
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x0016
        L_0x0015:
            r0 = 0
        L_0x0016:
            if (r0 == 0) goto L_0x0021
            int r2 = r0.intValue()
            org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8 r2 = r1.cpUTF8Value((int) r2)
            return r2
        L_0x0021:
            if (r3 == 0) goto L_0x002c
            java.util.Map r3 = r1.mapSignature
            java.lang.Object r3 = r3.get(r2)
            r0 = r3
            java.lang.Integer r0 = (java.lang.Integer) r0
        L_0x002c:
            if (r0 == 0) goto L_0x0037
            int r2 = r0.intValue()
            org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8 r2 = r1.cpSignatureValue(r2)
            return r2
        L_0x0037:
            org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8 r3 = new org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8
            r0 = -1
            r3.<init>(r2, r0)
            java.util.Map r0 = r1.stringsToCPUTF8
            r0.put(r2, r3)
            return r3
        L_0x0043:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.CpBands.cpUTF8Value(java.lang.String, boolean):org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8");
    }

    public CPString cpStringValue(int i) {
        String str = this.cpString[i];
        int i2 = this.cpStringInts[i];
        int i3 = this.stringOffset + i;
        CPString cPString = (CPString) this.stringsToCPStrings.get(str);
        if (cPString != null) {
            return cPString;
        }
        CPString cPString2 = new CPString(cpUTF8Value(i2), i3);
        this.stringsToCPStrings.put(str, cPString2);
        return cPString2;
    }

    public CPLong cpLongValue(int i) {
        Long valueOf = Long.valueOf(this.cpLong[i]);
        CPLong cPLong = (CPLong) this.longsToCPLongs.get(valueOf);
        if (cPLong != null) {
            return cPLong;
        }
        CPLong cPLong2 = new CPLong(valueOf, i + this.longOffset);
        this.longsToCPLongs.put(valueOf, cPLong2);
        return cPLong2;
    }

    public CPInteger cpIntegerValue(int i) {
        Integer valueOf = Integer.valueOf(this.cpInt[i]);
        CPInteger cPInteger = (CPInteger) this.integersToCPIntegers.get(valueOf);
        if (cPInteger != null) {
            return cPInteger;
        }
        CPInteger cPInteger2 = new CPInteger(valueOf, i + this.intOffset);
        this.integersToCPIntegers.put(valueOf, cPInteger2);
        return cPInteger2;
    }

    public CPFloat cpFloatValue(int i) {
        Float valueOf = Float.valueOf(this.cpFloat[i]);
        CPFloat cPFloat = (CPFloat) this.floatsToCPFloats.get(valueOf);
        if (cPFloat != null) {
            return cPFloat;
        }
        CPFloat cPFloat2 = new CPFloat(valueOf, i + this.floatOffset);
        this.floatsToCPFloats.put(valueOf, cPFloat2);
        return cPFloat2;
    }

    public CPClass cpClassValue(int i) {
        String str = this.cpClass[i];
        int i2 = this.cpClassInts[i];
        int i3 = this.classOffset + i;
        CPClass cPClass = (CPClass) this.stringsToCPClass.get(str);
        if (cPClass != null) {
            return cPClass;
        }
        CPClass cPClass2 = new CPClass(cpUTF8Value(i2), i3);
        this.stringsToCPClass.put(str, cPClass2);
        return cPClass2;
    }

    public CPClass cpClassValue(String str) {
        CPClass cPClass = (CPClass) this.stringsToCPClass.get(str);
        if (cPClass != null) {
            return cPClass;
        }
        Integer num = (Integer) this.mapClass.get(str);
        if (num != null) {
            return cpClassValue(num.intValue());
        }
        CPClass cPClass2 = new CPClass(cpUTF8Value(str, false), -1);
        this.stringsToCPClass.put(str, cPClass2);
        return cPClass2;
    }

    public CPDouble cpDoubleValue(int i) {
        Double valueOf = Double.valueOf(this.cpDouble[i]);
        CPDouble cPDouble = (CPDouble) this.doublesToCPDoubles.get(valueOf);
        if (cPDouble != null) {
            return cPDouble;
        }
        CPDouble cPDouble2 = new CPDouble(valueOf, i + this.doubleOffset);
        this.doublesToCPDoubles.put(valueOf, cPDouble2);
        return cPDouble2;
    }

    public CPNameAndType cpNameAndTypeValue(int i) {
        String str = this.cpDescriptor[i];
        CPNameAndType cPNameAndType = (CPNameAndType) this.descriptorsToCPNameAndTypes.get(str);
        if (cPNameAndType != null) {
            return cPNameAndType;
        }
        CPNameAndType cPNameAndType2 = new CPNameAndType(cpUTF8Value(this.cpDescriptorNameInts[i]), cpSignatureValue(this.cpDescriptorTypeInts[i]), i + this.descrOffset);
        this.descriptorsToCPNameAndTypes.put(str, cPNameAndType2);
        return cPNameAndType2;
    }

    public CPInterfaceMethodRef cpIMethodValue(int i) {
        return new CPInterfaceMethodRef(cpClassValue(this.cpIMethodClassInts[i]), cpNameAndTypeValue(this.cpIMethodDescriptorInts[i]), i + this.imethodOffset);
    }

    public CPMethodRef cpMethodValue(int i) {
        return new CPMethodRef(cpClassValue(this.cpMethodClassInts[i]), cpNameAndTypeValue(this.cpMethodDescriptorInts[i]), i + this.methodOffset);
    }

    public CPFieldRef cpFieldValue(int i) {
        return new CPFieldRef(cpClassValue(this.cpFieldClassInts[i]), cpNameAndTypeValue(this.cpFieldDescriptorInts[i]), i + this.fieldOffset);
    }

    public CPUTF8 cpSignatureValue(int i) {
        int i2 = this.cpSignatureInts[i];
        if (i2 == -1) {
            i2 = this.signatureOffset + i;
        }
        String str = this.cpSignature[i];
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCPUTF8.get(str);
        if (cputf8 != null) {
            return cputf8;
        }
        CPUTF8 cputf82 = new CPUTF8(str, i2);
        this.stringsToCPUTF8.put(str, cputf82);
        return cputf82;
    }

    public CPNameAndType cpNameAndTypeValue(String str) {
        CPNameAndType cPNameAndType = (CPNameAndType) this.descriptorsToCPNameAndTypes.get(str);
        if (cPNameAndType != null) {
            return cPNameAndType;
        }
        Integer num = (Integer) this.mapDescriptor.get(str);
        if (num != null) {
            return cpNameAndTypeValue(num.intValue());
        }
        int indexOf = str.indexOf(58);
        CPNameAndType cPNameAndType2 = new CPNameAndType(cpUTF8Value(str.substring(0, indexOf), true), cpUTF8Value(str.substring(indexOf + 1), true), this.descrOffset - 1);
        this.descriptorsToCPNameAndTypes.put(str, cPNameAndType2);
        return cPNameAndType2;
    }

    public int[] getCpDescriptorNameInts() {
        return this.cpDescriptorNameInts;
    }

    public int[] getCpDescriptorTypeInts() {
        return this.cpDescriptorTypeInts;
    }
}
