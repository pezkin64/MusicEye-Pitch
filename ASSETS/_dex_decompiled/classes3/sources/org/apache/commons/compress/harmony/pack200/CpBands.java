package org.apache.commons.compress.harmony.pack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.compress.harmony.unpack200.AttributeLayout;
import org.objectweb.asm.Type;

public class CpBands extends BandSet {
    private final Set cp_Class = new TreeSet();
    private final Set cp_Descr = new TreeSet();
    private final Set cp_Double = new TreeSet();
    private final Set cp_Field = new TreeSet();
    private final Set cp_Float = new TreeSet();
    private final Set cp_Imethod = new TreeSet();
    private final Set cp_Int = new TreeSet();
    private final Set cp_Long = new TreeSet();
    private final Set cp_Method = new TreeSet();
    private final Set cp_Signature = new TreeSet();
    private final Set cp_String = new TreeSet();
    private final Set cp_Utf8 = new TreeSet();
    private final Set defaultAttributeNames;
    private final Map objectsToCPConstant = new HashMap();
    private final Segment segment;
    private final Map stringsToCpClass = new HashMap();
    private final Map stringsToCpField = new HashMap();
    private final Map stringsToCpIMethod = new HashMap();
    private final Map stringsToCpMethod = new HashMap();
    private final Map stringsToCpNameAndType = new HashMap();
    private final Map stringsToCpSignature = new HashMap();
    private final Map stringsToCpUtf8 = new HashMap();

    public CpBands(Segment segment2, int i) {
        super(i, segment2.getSegmentHeader());
        HashSet hashSet = new HashSet();
        this.defaultAttributeNames = hashSet;
        this.segment = segment2;
        hashSet.add(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT);
        hashSet.add(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS);
        hashSet.add(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS);
        hashSet.add(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS);
        hashSet.add(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS);
        hashSet.add(AttributeLayout.ATTRIBUTE_CODE);
        hashSet.add(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE);
        hashSet.add(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE);
        hashSet.add(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE);
        hashSet.add(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE);
        hashSet.add(AttributeLayout.ATTRIBUTE_DEPRECATED);
        hashSet.add(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD);
        hashSet.add(AttributeLayout.ATTRIBUTE_EXCEPTIONS);
        hashSet.add(AttributeLayout.ATTRIBUTE_INNER_CLASSES);
        hashSet.add(AttributeLayout.ATTRIBUTE_SIGNATURE);
        hashSet.add(AttributeLayout.ATTRIBUTE_SOURCE_FILE);
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing constant pool bands...");
        writeCpUtf8(outputStream);
        writeCpInt(outputStream);
        writeCpFloat(outputStream);
        writeCpLong(outputStream);
        writeCpDouble(outputStream);
        writeCpString(outputStream);
        writeCpClass(outputStream);
        writeCpSignature(outputStream);
        writeCpDescr(outputStream);
        writeCpMethodOrField(this.cp_Field, outputStream, "cp_Field");
        writeCpMethodOrField(this.cp_Method, outputStream, "cp_Method");
        writeCpMethodOrField(this.cp_Imethod, outputStream, "cp_Imethod");
    }

    private void writeCpUtf8(OutputStream outputStream) throws IOException, Pack200Exception {
        Object[] objArr;
        OutputStream outputStream2 = outputStream;
        PackingUtils.log("Writing " + this.cp_Utf8.size() + " UTF8 entries...");
        int i = 2;
        int size = this.cp_Utf8.size() - 2;
        int[] iArr = new int[size];
        int size2 = this.cp_Utf8.size() - 1;
        int[] iArr2 = new int[size2];
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Object[] array = this.cp_Utf8.toArray();
        String underlyingString = ((CPUTF8) array[1]).getUnderlyingString();
        int i2 = 0;
        iArr2[0] = underlyingString.length();
        addCharacters(arrayList, underlyingString.toCharArray());
        while (i < array.length) {
            int i3 = i - 1;
            char[] charArray = ((CPUTF8) array[i3]).getUnderlyingString().toCharArray();
            String underlyingString2 = ((CPUTF8) array[i]).getUnderlyingString();
            char[] charArray2 = underlyingString2.toCharArray();
            int i4 = i;
            int i5 = i3;
            int i6 = i2;
            int i7 = i6;
            while (true) {
                if (i2 >= charArray.length) {
                    objArr = array;
                    break;
                }
                objArr = array;
                if (charArray[i2] != charArray2[i2]) {
                    break;
                }
                i6++;
                i2++;
                array = objArr;
            }
            iArr[i4 - 2] = i6;
            char[] charArray3 = underlyingString2.substring(i6).toCharArray();
            if (charArray3.length > 1000) {
                iArr2[i5] = i7;
                arrayList2.add(Integer.valueOf(charArray3.length));
                addCharacters(arrayList3, charArray3);
            } else {
                iArr2[i5] = charArray3.length;
                addCharacters(arrayList, charArray3);
            }
            i = i4 + 1;
            i2 = i7;
            array = objArr;
        }
        int i8 = i2;
        int size3 = arrayList.size();
        int[] iArr3 = new int[size3];
        int size4 = arrayList2.size();
        int[] iArr4 = new int[size4];
        int size5 = arrayList2.size();
        int[][] iArr5 = new int[size5][];
        for (int i9 = i8; i9 < size3; i9++) {
            iArr3[i9] = ((Character) arrayList.get(i9)).charValue();
        }
        int i10 = i8;
        while (i10 < size4) {
            int intValue = ((Integer) arrayList2.get(i10)).intValue();
            iArr4[i10] = intValue;
            int i11 = i10;
            iArr5[i11] = new int[intValue];
            int i12 = i8;
            while (i12 < intValue) {
                int i13 = i12;
                int i14 = i8;
                iArr5[i11][i13] = ((Character) arrayList3.remove(i14)).charValue();
                i8 = i14;
                i12 = i13 + 1;
            }
            int i15 = i8;
            i10 = i11 + 1;
        }
        byte[] encodeBandInt = encodeBandInt("cpUtf8Prefix", iArr, Codec.DELTA5);
        outputStream2.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cpUtf8Prefix[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("cpUtf8Suffix", iArr2, Codec.UNSIGNED5);
        outputStream2.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from cpUtf8Suffix[" + size2 + "]");
        byte[] encodeBandInt3 = encodeBandInt("cpUtf8Chars", iArr3, Codec.CHAR3);
        outputStream2.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from cpUtf8Chars[" + size3 + "]");
        byte[] encodeBandInt4 = encodeBandInt("cpUtf8BigSuffix", iArr4, Codec.DELTA5);
        outputStream2.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from cpUtf8BigSuffix[" + size4 + "]");
        for (int i16 = i8; i16 < size5; i16++) {
            byte[] encodeBandInt5 = encodeBandInt("cpUtf8BigChars " + i16, iArr5[i16], Codec.DELTA5);
            outputStream2.write(encodeBandInt5);
            PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from cpUtf8BigChars" + i16 + "[" + iArr5[i16].length + "]");
        }
    }

    private void addCharacters(List list, char[] cArr) {
        for (char valueOf : cArr) {
            list.add(Character.valueOf(valueOf));
        }
    }

    private void writeCpInt(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Int.size() + " Integer entries...");
        int size = this.cp_Int.size();
        int[] iArr = new int[size];
        int i = 0;
        for (CPInt cPInt : this.cp_Int) {
            iArr[i] = cPInt.getInt();
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cp_Int", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cp_Int[" + size + "]");
    }

    private void writeCpFloat(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Float.size() + " Float entries...");
        int size = this.cp_Float.size();
        int[] iArr = new int[size];
        int i = 0;
        for (CPFloat cPFloat : this.cp_Float) {
            iArr[i] = Float.floatToIntBits(cPFloat.getFloat());
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cp_Float", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cp_Float[" + size + "]");
    }

    private void writeCpLong(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Long.size() + " Long entries...");
        int size = this.cp_Long.size();
        int[] iArr = new int[size];
        int size2 = this.cp_Long.size();
        int[] iArr2 = new int[size2];
        int i = 0;
        for (CPLong cPLong : this.cp_Long) {
            long j = cPLong.getLong();
            iArr[i] = (int) (j >> 32);
            iArr2[i] = (int) j;
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cp_Long_hi", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cp_Long_hi[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("cp_Long_lo", iArr2, Codec.DELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from cp_Long_lo[" + size2 + "]");
    }

    private void writeCpDouble(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Double.size() + " Double entries...");
        int size = this.cp_Double.size();
        int[] iArr = new int[size];
        int size2 = this.cp_Double.size();
        int[] iArr2 = new int[size2];
        int i = 0;
        for (CPDouble cPDouble : this.cp_Double) {
            long doubleToLongBits = Double.doubleToLongBits(cPDouble.getDouble());
            iArr[i] = (int) (doubleToLongBits >> 32);
            iArr2[i] = (int) doubleToLongBits;
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cp_Double_hi", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cp_Double_hi[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("cp_Double_lo", iArr2, Codec.DELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from cp_Double_lo[" + size2 + "]");
    }

    private void writeCpString(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_String.size() + " String entries...");
        int size = this.cp_String.size();
        int[] iArr = new int[size];
        int i = 0;
        for (CPString indexInCpUtf8 : this.cp_String) {
            iArr[i] = indexInCpUtf8.getIndexInCpUtf8();
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cpString", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cpString[" + size + "]");
    }

    private void writeCpClass(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Class.size() + " Class entries...");
        int size = this.cp_Class.size();
        int[] iArr = new int[size];
        int i = 0;
        for (CPClass indexInCpUtf8 : this.cp_Class) {
            iArr[i] = indexInCpUtf8.getIndexInCpUtf8();
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cpClass", iArr, Codec.UDELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cpClass[" + size + "]");
    }

    private void writeCpSignature(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Signature.size() + " Signature entries...");
        int size = this.cp_Signature.size();
        int[] iArr = new int[size];
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (CPSignature cPSignature : this.cp_Signature) {
            arrayList.addAll(cPSignature.getClasses());
            iArr[i] = cPSignature.getIndexInCpUtf8();
            i++;
        }
        int size2 = arrayList.size();
        int[] iArr2 = new int[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            iArr2[i2] = ((CPClass) arrayList.get(i2)).getIndex();
        }
        byte[] encodeBandInt = encodeBandInt("cpSignatureForm", iArr, Codec.DELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cpSignatureForm[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("cpSignatureClasses", iArr2, Codec.UDELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from cpSignatureClasses[" + size2 + "]");
    }

    private void writeCpDescr(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + this.cp_Descr.size() + " Descriptor entries...");
        int size = this.cp_Descr.size();
        int[] iArr = new int[size];
        int size2 = this.cp_Descr.size();
        int[] iArr2 = new int[size2];
        int i = 0;
        for (CPNameAndType cPNameAndType : this.cp_Descr) {
            iArr[i] = cPNameAndType.getNameIndex();
            iArr2[i] = cPNameAndType.getTypeIndex();
            i++;
        }
        byte[] encodeBandInt = encodeBandInt("cp_Descr_Name", iArr, Codec.DELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from cp_Descr_Name[" + size + "]");
        byte[] encodeBandInt2 = encodeBandInt("cp_Descr_Type", iArr2, Codec.UDELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from cp_Descr_Type[" + size2 + "]");
    }

    private void writeCpMethodOrField(Set set, OutputStream outputStream, String str) throws IOException, Pack200Exception {
        PackingUtils.log("Writing " + set.size() + " Method and Field entries...");
        int size = set.size();
        int[] iArr = new int[size];
        int size2 = set.size();
        int[] iArr2 = new int[size2];
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            CPMethodOrField cPMethodOrField = (CPMethodOrField) it.next();
            iArr[i] = cPMethodOrField.getClassIndex();
            iArr2[i] = cPMethodOrField.getDescIndex();
            i++;
        }
        byte[] encodeBandInt = encodeBandInt(str + "_class", iArr, Codec.DELTA5);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from " + str + "_class[" + size + "]");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("_desc");
        byte[] encodeBandInt2 = encodeBandInt(sb.toString(), iArr2, Codec.UDELTA5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from " + str + "_desc[" + size2 + "]");
    }

    public void finaliseBands() {
        addCPUtf8(BuildConfig.FLAVOR);
        removeSignaturesFromCpUTF8();
        addIndices();
        this.segmentHeader.setCp_Utf8_count(this.cp_Utf8.size());
        this.segmentHeader.setCp_Int_count(this.cp_Int.size());
        this.segmentHeader.setCp_Float_count(this.cp_Float.size());
        this.segmentHeader.setCp_Long_count(this.cp_Long.size());
        this.segmentHeader.setCp_Double_count(this.cp_Double.size());
        this.segmentHeader.setCp_String_count(this.cp_String.size());
        this.segmentHeader.setCp_Class_count(this.cp_Class.size());
        this.segmentHeader.setCp_Signature_count(this.cp_Signature.size());
        this.segmentHeader.setCp_Descr_count(this.cp_Descr.size());
        this.segmentHeader.setCp_Field_count(this.cp_Field.size());
        this.segmentHeader.setCp_Method_count(this.cp_Method.size());
        this.segmentHeader.setCp_Imethod_count(this.cp_Imethod.size());
    }

    private void removeSignaturesFromCpUTF8() {
        for (CPSignature cPSignature : this.cp_Signature) {
            String underlyingString = cPSignature.getUnderlyingString();
            if (!underlyingString.equals(cPSignature.getSignatureForm().getUnderlyingString())) {
                removeCpUtf8(underlyingString);
            }
        }
    }

    private void addIndices() {
        Set[] setArr = {this.cp_Utf8, this.cp_Int, this.cp_Float, this.cp_Long, this.cp_Double, this.cp_String, this.cp_Class, this.cp_Signature, this.cp_Descr, this.cp_Field, this.cp_Method, this.cp_Imethod};
        for (int i = 0; i < 12; i++) {
            int i2 = 0;
            for (ConstantPoolEntry index : setArr[i]) {
                index.setIndex(i2);
                i2++;
            }
        }
        HashMap hashMap = new HashMap();
        for (CPMethodOrField cPMethodOrField : this.cp_Field) {
            CPClass className = cPMethodOrField.getClassName();
            Integer num = (Integer) hashMap.get(className);
            if (num == null) {
                hashMap.put(className, 1);
                cPMethodOrField.setIndexInClass(0);
            } else {
                int intValue = num.intValue();
                cPMethodOrField.setIndexInClass(intValue);
                hashMap.put(className, Integer.valueOf(intValue + 1));
            }
        }
        hashMap.clear();
        HashMap hashMap2 = new HashMap();
        for (CPMethodOrField cPMethodOrField2 : this.cp_Method) {
            CPClass className2 = cPMethodOrField2.getClassName();
            Integer num2 = (Integer) hashMap.get(className2);
            if (num2 == null) {
                hashMap.put(className2, 1);
                cPMethodOrField2.setIndexInClass(0);
            } else {
                int intValue2 = num2.intValue();
                cPMethodOrField2.setIndexInClass(intValue2);
                hashMap.put(className2, Integer.valueOf(intValue2 + 1));
            }
            if (cPMethodOrField2.getDesc().getName().equals("<init>")) {
                Integer num3 = (Integer) hashMap2.get(className2);
                if (num3 == null) {
                    hashMap2.put(className2, 1);
                    cPMethodOrField2.setIndexInClassForConstructor(0);
                } else {
                    int intValue3 = num3.intValue();
                    cPMethodOrField2.setIndexInClassForConstructor(intValue3);
                    hashMap2.put(className2, Integer.valueOf(intValue3 + 1));
                }
            }
        }
    }

    private void removeCpUtf8(String str) {
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCpUtf8.get(str);
        if (cputf8 != null && this.stringsToCpClass.get(str) == null) {
            this.stringsToCpUtf8.remove(str);
            this.cp_Utf8.remove(cputf8);
        }
    }

    /* access modifiers changed from: package-private */
    public void addCPUtf8(String str) {
        getCPUtf8(str);
    }

    public CPUTF8 getCPUtf8(String str) {
        if (str == null) {
            return null;
        }
        CPUTF8 cputf8 = (CPUTF8) this.stringsToCpUtf8.get(str);
        if (cputf8 != null) {
            return cputf8;
        }
        CPUTF8 cputf82 = new CPUTF8(str);
        this.cp_Utf8.add(cputf82);
        this.stringsToCpUtf8.put(str, cputf82);
        return cputf82;
    }

    public CPSignature getCPSignature(String str) {
        CPUTF8 cputf8;
        CPClass cPClass;
        if (str == null) {
            return null;
        }
        CPSignature cPSignature = (CPSignature) this.stringsToCpSignature.get(str);
        if (cPSignature != null) {
            return cPSignature;
        }
        ArrayList arrayList = new ArrayList();
        if (str.length() <= 1 || str.indexOf(76) == -1) {
            cputf8 = getCPUtf8(str);
        } else {
            ArrayList<String> arrayList2 = new ArrayList<>();
            char[] charArray = str.toCharArray();
            StringBuffer stringBuffer = new StringBuffer();
            int i = 0;
            while (i < charArray.length) {
                stringBuffer.append(charArray[i]);
                if (charArray[i] == 'L') {
                    StringBuffer stringBuffer2 = new StringBuffer();
                    int i2 = i + 1;
                    while (true) {
                        if (i2 >= charArray.length) {
                            break;
                        }
                        char c = charArray[i2];
                        if (!Character.isLetter(c) && !Character.isDigit(c) && c != '/' && c != '$' && c != '_') {
                            arrayList2.add(stringBuffer2.toString());
                            i = i2 - 1;
                            break;
                        }
                        stringBuffer2.append(c);
                        i2++;
                    }
                }
                i++;
            }
            removeCpUtf8(str);
            for (String str2 : arrayList2) {
                if (str2 != null) {
                    String replace = str2.replace('.', '/');
                    cPClass = (CPClass) this.stringsToCpClass.get(replace);
                    if (cPClass == null) {
                        CPClass cPClass2 = new CPClass(getCPUtf8(replace));
                        this.cp_Class.add(cPClass2);
                        this.stringsToCpClass.put(replace, cPClass2);
                        cPClass = cPClass2;
                    }
                } else {
                    cPClass = null;
                }
                arrayList.add(cPClass);
            }
            cputf8 = getCPUtf8(stringBuffer.toString());
        }
        CPSignature cPSignature2 = new CPSignature(str, cputf8, arrayList);
        this.cp_Signature.add(cPSignature2);
        this.stringsToCpSignature.put(str, cPSignature2);
        return cPSignature2;
    }

    public CPClass getCPClass(String str) {
        if (str == null) {
            return null;
        }
        String replace = str.replace('.', '/');
        CPClass cPClass = (CPClass) this.stringsToCpClass.get(replace);
        if (cPClass == null) {
            CPClass cPClass2 = new CPClass(getCPUtf8(replace));
            this.cp_Class.add(cPClass2);
            this.stringsToCpClass.put(replace, cPClass2);
            cPClass = cPClass2;
        }
        if (cPClass.isInnerClass()) {
            this.segment.getClassBands().currentClassReferencesInnerClass(cPClass);
        }
        return cPClass;
    }

    public void addCPClass(String str) {
        getCPClass(str);
    }

    public CPNameAndType getCPNameAndType(String str, String str2) {
        String str3 = str + ":" + str2;
        CPNameAndType cPNameAndType = (CPNameAndType) this.stringsToCpNameAndType.get(str3);
        if (cPNameAndType != null) {
            return cPNameAndType;
        }
        CPNameAndType cPNameAndType2 = new CPNameAndType(getCPUtf8(str), getCPSignature(str2));
        this.stringsToCpNameAndType.put(str3, cPNameAndType2);
        this.cp_Descr.add(cPNameAndType2);
        return cPNameAndType2;
    }

    public CPMethodOrField getCPField(CPClass cPClass, String str, String str2) {
        String str3 = cPClass.toString() + ":" + str + ":" + str2;
        CPMethodOrField cPMethodOrField = (CPMethodOrField) this.stringsToCpField.get(str3);
        if (cPMethodOrField != null) {
            return cPMethodOrField;
        }
        CPMethodOrField cPMethodOrField2 = new CPMethodOrField(cPClass, getCPNameAndType(str, str2));
        this.cp_Field.add(cPMethodOrField2);
        this.stringsToCpField.put(str3, cPMethodOrField2);
        return cPMethodOrField2;
    }

    public CPConstant getConstant(Object obj) {
        CPConstant cPConstant = (CPConstant) this.objectsToCPConstant.get(obj);
        if (cPConstant == null) {
            if (obj instanceof Integer) {
                cPConstant = new CPInt(((Integer) obj).intValue());
                this.cp_Int.add(cPConstant);
            } else if (obj instanceof Long) {
                cPConstant = new CPLong(((Long) obj).longValue());
                this.cp_Long.add(cPConstant);
            } else if (obj instanceof Float) {
                cPConstant = new CPFloat(((Float) obj).floatValue());
                this.cp_Float.add(cPConstant);
            } else if (obj instanceof Double) {
                cPConstant = new CPDouble(((Double) obj).doubleValue());
                this.cp_Double.add(cPConstant);
            } else if (obj instanceof String) {
                cPConstant = new CPString(getCPUtf8((String) obj));
                this.cp_String.add(cPConstant);
            } else if (obj instanceof Type) {
                String className = ((Type) obj).getClassName();
                if (className.endsWith("[]")) {
                    String str = "[L" + className.substring(0, className.length() - 2);
                    while (str.endsWith("[]")) {
                        str = "[" + str.substring(0, str.length() - 2);
                    }
                    className = str + ";";
                }
                cPConstant = getCPClass(className);
            }
            this.objectsToCPConstant.put(obj, cPConstant);
        }
        return cPConstant;
    }

    public CPMethodOrField getCPMethod(CPClass cPClass, String str, String str2) {
        String str3 = cPClass.toString() + ":" + str + ":" + str2;
        CPMethodOrField cPMethodOrField = (CPMethodOrField) this.stringsToCpMethod.get(str3);
        if (cPMethodOrField != null) {
            return cPMethodOrField;
        }
        CPMethodOrField cPMethodOrField2 = new CPMethodOrField(cPClass, getCPNameAndType(str, str2));
        this.cp_Method.add(cPMethodOrField2);
        this.stringsToCpMethod.put(str3, cPMethodOrField2);
        return cPMethodOrField2;
    }

    public CPMethodOrField getCPIMethod(CPClass cPClass, String str, String str2) {
        String str3 = cPClass.toString() + ":" + str + ":" + str2;
        CPMethodOrField cPMethodOrField = (CPMethodOrField) this.stringsToCpIMethod.get(str3);
        if (cPMethodOrField != null) {
            return cPMethodOrField;
        }
        CPMethodOrField cPMethodOrField2 = new CPMethodOrField(cPClass, getCPNameAndType(str, str2));
        this.cp_Imethod.add(cPMethodOrField2);
        this.stringsToCpIMethod.put(str3, cPMethodOrField2);
        return cPMethodOrField2;
    }

    public CPMethodOrField getCPField(String str, String str2, String str3) {
        return getCPField(getCPClass(str), str2, str3);
    }

    public CPMethodOrField getCPMethod(String str, String str2, String str3) {
        return getCPMethod(getCPClass(str), str2, str3);
    }

    public CPMethodOrField getCPIMethod(String str, String str2, String str3) {
        return getCPIMethod(getCPClass(str), str2, str3);
    }

    public boolean existsCpClass(String str) {
        return ((CPClass) this.stringsToCpClass.get(str)) != null;
    }
}
