package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ConstantValueAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.DeprecatedAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.EnclosingMethodAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionsAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LineNumberTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.LocalVariableTypeTableAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SignatureAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

public class ClassBands extends BandSet {
    private final AttributeLayoutMap attrMap;
    private long[] classAccessFlags;
    private ArrayList[] classAttributes;
    private final int classCount = this.header.getClassCount();
    private int[] classFieldCount;
    private long[] classFlags;
    private int[][] classInterfacesInts;
    private int[] classMethodCount;
    private int[] classSuperInts;
    private String[] classThis;
    private int[] classThisInts;
    private int[] classVersionMajor;
    private int[] classVersionMinor;
    private List[] codeAttributes;
    private int[][] codeHandlerCatchPO;
    private int[][] codeHandlerClassRCN;
    private int[] codeHandlerCount;
    private int[][] codeHandlerEndPO;
    private int[][] codeHandlerStartP;
    private boolean[] codeHasAttributes;
    private int[] codeMaxNALocals;
    private int[] codeMaxStack;
    private final CpBands cpBands;
    private long[][] fieldAccessFlags;
    private ArrayList[][] fieldAttributes;
    private String[][] fieldDescr;
    private int[][] fieldDescrInts;
    private long[][] fieldFlags;
    private IcTuple[][] icLocal;
    private long[][] methodAccessFlags;
    private int[] methodAttrCalls;
    private ArrayList[][] methodAttributes;
    private String[][] methodDescr;
    private int[][] methodDescrInts;
    private long[][] methodFlags;
    private final SegmentOptions options = this.header.getOptions();

    public void unpack() {
    }

    public ClassBands(Segment segment) {
        super(segment);
        this.attrMap = segment.getAttrDefinitionBands().getAttributeDefinitionMap();
        this.cpBands = segment.getCpBands();
    }

    public void read(InputStream inputStream) throws IOException, Pack200Exception {
        int classCount2 = this.header.getClassCount();
        int[] decodeBandInt = decodeBandInt("class_this", inputStream, Codec.DELTA5, classCount2);
        this.classThisInts = decodeBandInt;
        this.classThis = getReferences(decodeBandInt, this.cpBands.getCpClass());
        this.classSuperInts = decodeBandInt("class_super", inputStream, Codec.DELTA5, classCount2);
        this.classInterfacesInts = decodeBandInt("class_interface", inputStream, Codec.DELTA5, decodeBandInt("class_interface_count", inputStream, Codec.DELTA5, classCount2));
        this.classFieldCount = decodeBandInt("class_field_count", inputStream, Codec.DELTA5, classCount2);
        this.classMethodCount = decodeBandInt("class_method_count", inputStream, Codec.DELTA5, classCount2);
        parseFieldBands(inputStream);
        parseMethodBands(inputStream);
        parseClassAttrBands(inputStream);
        parseCodeBands(inputStream);
    }

    private void parseFieldBands(InputStream inputStream) throws IOException, Pack200Exception {
        int[][] decodeBandInt = decodeBandInt("field_descr", inputStream, Codec.DELTA5, this.classFieldCount);
        this.fieldDescrInts = decodeBandInt;
        this.fieldDescr = getReferences(decodeBandInt, this.cpBands.getCpDescriptor());
        parseFieldAttrBands(inputStream);
    }

    private void parseFieldAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        int i2;
        AttributeLayout attributeLayout;
        int[] iArr;
        InputStream inputStream2 = inputStream;
        long[][] parseFlags = parseFlags("field_flags", inputStream2, this.classFieldCount, Codec.UNSIGNED5, this.options.hasFieldFlagsHi());
        this.fieldFlags = parseFlags;
        int i3 = 1;
        int[] decodeBandInt = decodeBandInt("field_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("field_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("field_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), this.fieldFlags, 1));
        this.fieldAttributes = new ArrayList[this.classCount][];
        for (int i4 = 0; i4 < this.classCount; i4++) {
            this.fieldAttributes[i4] = new ArrayList[this.fieldFlags[i4].length];
            for (int i5 = 0; i5 < this.fieldFlags[i4].length; i5++) {
                this.fieldAttributes[i4][i5] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1);
        int[] decodeBandInt2 = decodeBandInt("field_ConstantValue_KQ", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout2));
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 1);
        int[] decodeBandInt3 = decodeBandInt("field_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1);
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < this.classCount; i8++) {
            int i9 = 0;
            while (true) {
                long[] jArr = this.fieldFlags[i8];
                if (i9 >= jArr.length) {
                    break;
                }
                int i10 = i3;
                long j = jArr[i9];
                if (attributeLayout4.matches(j)) {
                    i2 = i10;
                    this.fieldAttributes[i8][i9].add(new DeprecatedAttribute());
                } else {
                    i2 = i10;
                }
                if (attributeLayout2.matches(j)) {
                    iArr = decodeBandInt3;
                    attributeLayout = attributeLayout4;
                    long j2 = (long) decodeBandInt2[i6];
                    String str = this.fieldDescr[i8][i9];
                    String substring = str.substring(str.indexOf(58) + 1);
                    if (substring.equals("B") || substring.equals("S") || substring.equals("C") || substring.equals("Z")) {
                        substring = "I";
                    }
                    this.fieldAttributes[i8][i9].add(new ConstantValueAttribute(attributeLayout2.getValue(j2, substring, this.cpBands.getConstantPool())));
                    i6++;
                } else {
                    iArr = decodeBandInt3;
                    attributeLayout = attributeLayout4;
                }
                if (attributeLayout3.matches(j)) {
                    String str2 = this.fieldDescr[i8][i9];
                    this.fieldAttributes[i8][i9].add(new SignatureAttribute((CPUTF8) attributeLayout3.getValue((long) iArr[i7], str2.substring(str2.indexOf(58) + 1), this.cpBands.getConstantPool())));
                    i7++;
                }
                i9++;
                i3 = i2;
                decodeBandInt3 = iArr;
                attributeLayout4 = attributeLayout;
            }
            int i11 = i3;
            int[] iArr2 = decodeBandInt3;
            AttributeLayout attributeLayout5 = attributeLayout4;
        }
        int i12 = i3;
        int parseFieldMetadataBands = parseFieldMetadataBands(inputStream2, decodeBandInt);
        int i13 = this.options.hasFieldFlagsHi() ? 62 : 31;
        int i14 = i13 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i14];
        int[] iArr3 = new int[i14];
        List[] listArr = new List[i14];
        int i15 = 0;
        while (i15 < i13) {
            int i16 = i12;
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i15, i16);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i15] = attributeLayout6;
                iArr3[i15] = SegmentUtils.countMatches(this.fieldFlags, (IMatcher) attributeLayout6);
            }
            i15++;
            i12 = i16;
        }
        for (int i17 = 0; i17 < i14; i17++) {
            if (iArr3[i17] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i17]);
                listArr[i17] = attributeBands.parseAttributes(inputStream2, iArr3[i17]);
                int numBackwardsCallables = attributeLayoutArr[i17].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr4 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt, parseFieldMetadataBands, iArr4, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr4);
                    parseFieldMetadataBands += numBackwardsCallables;
                }
            }
        }
        for (int i18 = 0; i18 < this.classCount; i18++) {
            int i19 = 0;
            while (true) {
                long[] jArr2 = this.fieldFlags[i18];
                if (i19 >= jArr2.length) {
                    break;
                }
                long j3 = jArr2[i19];
                int i20 = 0;
                for (int i21 = 0; i21 < i14; i21++) {
                    AttributeLayout attributeLayout7 = attributeLayoutArr[i21];
                    if (attributeLayout7 != null && attributeLayout7.matches(j3)) {
                        if (attributeLayoutArr[i21].getIndex() < 15) {
                            i = 0;
                            this.fieldAttributes[i18][i19].add(i20, listArr[i21].get(0));
                            i20++;
                        } else {
                            i = 0;
                            this.fieldAttributes[i18][i19].add(listArr[i21].get(0));
                        }
                        listArr[i21].remove(i);
                    }
                }
                i19++;
            }
        }
    }

    private void parseMethodBands(InputStream inputStream) throws IOException, Pack200Exception {
        int[][] decodeBandInt = decodeBandInt("method_descr", inputStream, Codec.MDELTA5, this.classMethodCount);
        this.methodDescrInts = decodeBandInt;
        this.methodDescr = getReferences(decodeBandInt, this.cpBands.getCpDescriptor());
        parseMethodAttrBands(inputStream);
    }

    private void parseMethodAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        AttributeLayout attributeLayout;
        InputStream inputStream2 = inputStream;
        long[][] parseFlags = parseFlags("method_flags", inputStream2, this.classMethodCount, Codec.UNSIGNED5, this.options.hasMethodFlagsHi());
        this.methodFlags = parseFlags;
        this.methodAttrCalls = decodeBandInt("method_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("method_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("method_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), this.methodFlags, 2));
        this.methodAttributes = new ArrayList[this.classCount][];
        for (int i2 = 0; i2 < this.classCount; i2++) {
            this.methodAttributes[i2] = new ArrayList[this.methodFlags[i2].length];
            for (int i3 = 0; i3 < this.methodFlags[i2].length; i3++) {
                this.methodAttributes[i2][i3] = new ArrayList();
            }
        }
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2);
        int[] decodeBandInt = decodeBandInt("method_Exceptions_n", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout2));
        int[][] decodeBandInt2 = decodeBandInt("method_Exceptions_RC", inputStream2, Codec.UNSIGNED5, decodeBandInt);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 2);
        int[] decodeBandInt3 = decodeBandInt("method_signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout3));
        AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2);
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.methodAttributes.length; i6++) {
            int i7 = 0;
            while (i7 < this.methodAttributes[i6].length) {
                int[] iArr = decodeBandInt;
                long j = this.methodFlags[i6][i7];
                if (attributeLayout2.matches(j)) {
                    int i8 = iArr[i4];
                    int[] iArr2 = decodeBandInt2[i4];
                    CPClass[] cPClassArr = new CPClass[i8];
                    int i9 = 0;
                    while (i9 < i8) {
                        int i10 = i9;
                        cPClassArr[i10] = this.cpBands.cpClassValue(iArr2[i10]);
                        i9 = i10 + 1;
                        attributeLayout2 = attributeLayout2;
                    }
                    attributeLayout = attributeLayout2;
                    this.methodAttributes[i6][i7].add(new ExceptionsAttribute(cPClassArr));
                    i4++;
                } else {
                    attributeLayout = attributeLayout2;
                }
                if (attributeLayout3.matches(j)) {
                    long j2 = (long) decodeBandInt3[i5];
                    String str = this.methodDescr[i6][i7];
                    String substring = str.substring(str.indexOf(58) + 1);
                    if (substring.equals("B") || substring.equals("H")) {
                        substring = "I";
                    }
                    this.methodAttributes[i6][i7].add(new SignatureAttribute((CPUTF8) attributeLayout3.getValue(j2, substring, this.cpBands.getConstantPool())));
                    i5++;
                }
                if (attributeLayout4.matches(j)) {
                    this.methodAttributes[i6][i7].add(new DeprecatedAttribute());
                }
                i7++;
                decodeBandInt = iArr;
                attributeLayout2 = attributeLayout;
            }
            AttributeLayout attributeLayout5 = attributeLayout2;
            int[] iArr3 = decodeBandInt;
        }
        int parseMethodMetadataBands = parseMethodMetadataBands(inputStream2, this.methodAttrCalls);
        int i11 = this.options.hasMethodFlagsHi() ? 62 : 31;
        int i12 = i11 + 1;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i12];
        int[] iArr4 = new int[i12];
        List[] listArr = new List[i12];
        for (int i13 = 0; i13 < i11; i13++) {
            AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(i13, 2);
            if (attributeLayout6 != null && !attributeLayout6.isDefaultLayout()) {
                attributeLayoutArr[i13] = attributeLayout6;
                iArr4[i13] = SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayout6);
            }
        }
        for (int i14 = 0; i14 < i12; i14++) {
            if (iArr4[i14] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i14]);
                listArr[i14] = attributeBands.parseAttributes(inputStream2, iArr4[i14]);
                int numBackwardsCallables = attributeLayoutArr[i14].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr5 = new int[numBackwardsCallables];
                    System.arraycopy(this.methodAttrCalls, parseMethodMetadataBands, iArr5, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr5);
                    parseMethodMetadataBands += numBackwardsCallables;
                }
            }
        }
        for (int i15 = 0; i15 < this.methodAttributes.length; i15++) {
            for (int i16 = 0; i16 < this.methodAttributes[i15].length; i16++) {
                long j3 = this.methodFlags[i15][i16];
                int i17 = 0;
                for (int i18 = 0; i18 < i12; i18++) {
                    AttributeLayout attributeLayout7 = attributeLayoutArr[i18];
                    if (attributeLayout7 != null && attributeLayout7.matches(j3)) {
                        if (attributeLayoutArr[i18].getIndex() < 15) {
                            i = 0;
                            this.methodAttributes[i15][i16].add(i17, listArr[i18].get(0));
                            i17++;
                        } else {
                            i = 0;
                            this.methodAttributes[i15][i16].add(listArr[i18].get(0));
                        }
                        listArr[i18].remove(i);
                    }
                }
            }
        }
    }

    private int getCallCount(int[][] iArr, long[][] jArr, int i) throws Pack200Exception {
        int i2 = 0;
        for (int[] iArr2 : iArr) {
            int i3 = 0;
            while (true) {
                if (i3 >= iArr2.length) {
                    break;
                }
                i2 += this.attrMap.getAttributeLayout(iArr2[i3], i).numBackwardsCallables();
                i3++;
            }
        }
        int i4 = 0;
        for (long[] jArr2 : jArr) {
            int i5 = 0;
            while (true) {
                if (i5 >= jArr2.length) {
                    break;
                }
                i4 = (int) (((long) i4) | jArr2[i5]);
                i5++;
            }
        }
        for (int i6 = 0; i6 < 26; i6++) {
            if (((1 << i6) & i4) != 0) {
                i2 += this.attrMap.getAttributeLayout(i6, i).numBackwardsCallables();
            }
        }
        return i2;
    }

    private void parseClassAttrBands(InputStream inputStream) throws IOException, Pack200Exception {
        int i;
        int i2;
        AttributeLayout attributeLayout;
        AttributeLayout attributeLayout2;
        int i3;
        int i4;
        int i5;
        int i6;
        AttributeLayout attributeLayout3;
        int i7;
        int i8;
        AttributeLayout attributeLayout4;
        int i9;
        int i10;
        int i11;
        String str;
        String str2;
        int i12;
        int i13;
        int i14;
        int[][] iArr;
        String[] cpUTF8 = this.cpBands.getCpUTF8();
        String[] cpClass = this.cpBands.getCpClass();
        this.classAttributes = new ArrayList[this.classCount];
        int i15 = 0;
        while (true) {
            i = this.classCount;
            if (i15 >= i) {
                break;
            }
            this.classAttributes[i15] = new ArrayList();
            i15++;
        }
        InputStream inputStream2 = inputStream;
        long[] parseFlags = parseFlags("class_flags", inputStream2, i, Codec.UNSIGNED5, this.options.hasClassFlagsHi());
        this.classFlags = parseFlags;
        int[] decodeBandInt = decodeBandInt("class_attr_calls", inputStream2, Codec.UNSIGNED5, getCallCount(decodeBandInt("class_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("class_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags))), new long[][]{this.classFlags}, 0));
        AttributeLayout attributeLayout5 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0);
        AttributeLayout attributeLayout6 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0);
        int[] decodeBandInt2 = decodeBandInt("class_SourceFile_RUN", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout6));
        AttributeLayout attributeLayout7 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0);
        int countMatches = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout7);
        int[] decodeBandInt3 = decodeBandInt("class_EnclosingMethod_RC", inputStream2, Codec.UNSIGNED5, countMatches);
        int[] decodeBandInt4 = decodeBandInt("class_EnclosingMethod_RDN", inputStream2, Codec.UNSIGNED5, countMatches);
        AttributeLayout attributeLayout8 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 0);
        boolean z = true;
        int[] decodeBandInt5 = decodeBandInt("class_Signature_RS", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout8));
        int parseClassMetadataBands = parseClassMetadataBands(inputStream2, decodeBandInt);
        int[] iArr2 = decodeBandInt5;
        AttributeLayout attributeLayout9 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0);
        String[] strArr = cpUTF8;
        int[] decodeBandInt6 = decodeBandInt("class_InnerClasses_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout9));
        int[][] decodeBandInt7 = decodeBandInt("class_InnerClasses_RC", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        String[] strArr2 = cpClass;
        int[][] decodeBandInt8 = decodeBandInt("class_InnerClasses_F", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[] iArr3 = decodeBandInt6;
        int[][] iArr4 = decodeBandInt7;
        int i16 = 0;
        int i17 = 0;
        while (i17 < decodeBandInt8.length) {
            int i18 = i16;
            int i19 = 0;
            while (true) {
                int[] iArr5 = decodeBandInt8[i17];
                iArr = decodeBandInt8;
                if (i19 >= iArr5.length) {
                    break;
                }
                if (iArr5[i19] != 0) {
                    i18++;
                }
                i19++;
                decodeBandInt8 = iArr;
            }
            i17++;
            i16 = i18;
            decodeBandInt8 = iArr;
        }
        int[][] iArr6 = decodeBandInt8;
        int[] decodeBandInt9 = decodeBandInt("class_InnerClasses_outer_RCN", inputStream2, Codec.UNSIGNED5, i16);
        int[] decodeBandInt10 = decodeBandInt("class_InnerClasses_name_RUN", inputStream2, Codec.UNSIGNED5, i16);
        AttributeLayout attributeLayout10 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0);
        int countMatches2 = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout10);
        int[] iArr7 = decodeBandInt9;
        int[] decodeBandInt11 = decodeBandInt("class_file_version_minor_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        int[] decodeBandInt12 = decodeBandInt("class_file_version_major_H", inputStream2, Codec.UNSIGNED5, countMatches2);
        if (countMatches2 > 0) {
            int i20 = this.classCount;
            this.classVersionMajor = new int[i20];
            this.classVersionMinor = new int[i20];
        }
        int defaultClassMajorVersion = this.header.getDefaultClassMajorVersion();
        int defaultClassMinorVersion = this.header.getDefaultClassMinorVersion();
        int i21 = defaultClassMajorVersion;
        int i22 = this.options.hasClassFlagsHi() ? 62 : 31;
        int[] iArr8 = decodeBandInt12;
        int i23 = i22 + 1;
        int[] iArr9 = decodeBandInt2;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i23];
        int[] iArr10 = new int[i23];
        List[] listArr = new List[i23];
        int i24 = 0;
        while (i24 < i22) {
            int i25 = i22;
            int[] iArr11 = decodeBandInt4;
            AttributeLayout attributeLayout11 = this.attrMap.getAttributeLayout(i24, 0);
            if (attributeLayout11 != null && !attributeLayout11.isDefaultLayout()) {
                attributeLayoutArr[i24] = attributeLayout11;
                iArr10[i24] = SegmentUtils.countMatches(this.classFlags, (IMatcher) attributeLayout11);
            }
            i24++;
            i22 = i25;
            decodeBandInt4 = iArr11;
        }
        int[] iArr12 = decodeBandInt4;
        int i26 = 0;
        while (i26 < i23) {
            if (iArr10[i26] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i26]);
                listArr[i26] = attributeBands.parseAttributes(inputStream2, iArr10[i26]);
                int numBackwardsCallables = attributeLayoutArr[i26].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    int[] iArr13 = new int[numBackwardsCallables];
                    i14 = i26;
                    System.arraycopy(decodeBandInt, parseClassMetadataBands, iArr13, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr13);
                    parseClassMetadataBands += numBackwardsCallables;
                    i26 = i14 + 1;
                    inputStream2 = inputStream;
                }
            }
            i14 = i26;
            i26 = i14 + 1;
            inputStream2 = inputStream;
        }
        this.icLocal = new IcTuple[this.classCount][];
        int i27 = 0;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = 0;
        int i32 = 0;
        int i33 = 0;
        while (i29 < this.classCount) {
            int i34 = i31;
            int[] iArr14 = decodeBandInt3;
            long j = this.classFlags[i29];
            if (attributeLayout5.matches(j)) {
                i2 = i28;
                this.classAttributes[i29].add(new DeprecatedAttribute());
            } else {
                i2 = i28;
            }
            if (attributeLayout6.matches(j)) {
                attributeLayout2 = attributeLayout5;
                Object value = attributeLayout6.getValue((long) iArr9[i27], this.cpBands.getConstantPool());
                if (value == null) {
                    String str3 = this.classThis[i29];
                    String substring = str3.substring(str3.lastIndexOf(47) + 1);
                    String substring2 = substring.substring(substring.lastIndexOf(46) + 1);
                    char[] charArray = substring2.toCharArray();
                    attributeLayout = attributeLayout6;
                    int i35 = 0;
                    while (true) {
                        if (i35 >= charArray.length) {
                            i35 = -1;
                            break;
                        }
                        char[] cArr = charArray;
                        if (charArray[i35] <= '-') {
                            break;
                        }
                        i35++;
                        charArray = cArr;
                    }
                    if (i35 > -1) {
                        substring2 = substring2.substring(0, i35);
                    }
                    value = this.cpBands.cpUTF8Value(substring2 + ".java", z);
                } else {
                    attributeLayout = attributeLayout6;
                    boolean z2 = z;
                }
                this.classAttributes[i29].add(new SourceFileAttribute((CPUTF8) value));
                i3 = i27 + 1;
            } else {
                attributeLayout2 = attributeLayout5;
                attributeLayout = attributeLayout6;
                boolean z3 = z;
                i3 = i27;
            }
            if (attributeLayout7.matches(j)) {
                CPClass cpClassValue = this.cpBands.cpClassValue(iArr14[i2]);
                int i36 = iArr12[i2];
                i4 = i3;
                this.classAttributes[i29].add(new EnclosingMethodAttribute(cpClassValue, i36 != 0 ? this.cpBands.cpNameAndTypeValue(i36 - 1) : null));
                i5 = i2 + 1;
            } else {
                i4 = i3;
                i5 = i2;
            }
            if (attributeLayout8.matches(j)) {
                i6 = i5;
                this.classAttributes[i29].add(new SignatureAttribute((CPUTF8) attributeLayout8.getValue((long) iArr2[i30], this.cpBands.getConstantPool())));
                i30++;
            } else {
                i6 = i5;
            }
            if (attributeLayout9.matches(j)) {
                this.icLocal[i29] = new IcTuple[iArr3[i34]];
                int i37 = 0;
                while (i37 < this.icLocal[i29].length) {
                    int i38 = iArr4[i34][i37];
                    String str4 = strArr2[i38];
                    int i39 = iArr6[i34][i37];
                    if (i39 != 0) {
                        int i40 = iArr7[i33];
                        int i41 = decodeBandInt10[i33];
                        i33++;
                        i9 = i37;
                        attributeLayout4 = attributeLayout9;
                        i11 = i40;
                        i10 = i41;
                        str2 = strArr2[i40];
                        str = strArr[i41];
                        i13 = i6;
                    } else {
                        i9 = i37;
                        IcTuple[] icTuples = this.segment.getIcBands().getIcTuples();
                        i13 = i6;
                        attributeLayout4 = attributeLayout9;
                        int i42 = 0;
                        while (i42 < icTuples.length) {
                            if (icTuples[i42].getC().equals(str4)) {
                                i39 = icTuples[i42].getF();
                                str2 = icTuples[i42].getC2();
                                str = icTuples[i42].getN();
                                i11 = -1;
                                i10 = -1;
                            } else {
                                i42++;
                            }
                        }
                        i12 = i39;
                        str2 = null;
                        str = null;
                        i11 = -1;
                        i10 = -1;
                        this.icLocal[i29][i9] = new IcTuple(str4, i12, str2, str, i38, i11, i10, i9);
                        i37 = i9 + 1;
                        i6 = i13;
                        attributeLayout9 = attributeLayout4;
                    }
                    i12 = i39;
                    this.icLocal[i29][i9] = new IcTuple(str4, i12, str2, str, i38, i11, i10, i9);
                    i37 = i9 + 1;
                    i6 = i13;
                    attributeLayout9 = attributeLayout4;
                }
                i7 = i6;
                attributeLayout3 = attributeLayout9;
                i8 = i34 + 1;
            } else {
                i7 = i6;
                attributeLayout3 = attributeLayout9;
                i8 = i34;
            }
            if (attributeLayout10.matches(j)) {
                this.classVersionMajor[i29] = iArr8[i32];
                this.classVersionMinor[i29] = decodeBandInt11[i32];
                i32++;
            } else {
                int[] iArr15 = this.classVersionMajor;
                if (iArr15 != null) {
                    iArr15[i29] = i21;
                    this.classVersionMinor[i29] = defaultClassMinorVersion;
                }
            }
            for (int i43 = 0; i43 < i23; i43++) {
                AttributeLayout attributeLayout12 = attributeLayoutArr[i43];
                if (attributeLayout12 != null && attributeLayout12.matches(j)) {
                    this.classAttributes[i29].add(listArr[i43].get(0));
                    listArr[i43].remove(0);
                }
            }
            i29++;
            i31 = i8;
            decodeBandInt3 = iArr14;
            i28 = i7;
            attributeLayout5 = attributeLayout2;
            attributeLayout6 = attributeLayout;
            i27 = i4;
            attributeLayout9 = attributeLayout3;
            z = true;
        }
    }

    private void parseCodeBands(InputStream inputStream) throws Pack200Exception, IOException {
        int i;
        InputStream inputStream2 = inputStream;
        int i2 = 2;
        int countMatches = SegmentUtils.countMatches(this.methodFlags, (IMatcher) this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2));
        int[] decodeBandInt = decodeBandInt("code_headers", inputStream2, Codec.BYTE1, countMatches);
        boolean hasAllCodeFlags = this.segment.getSegmentHeader().getOptions().hasAllCodeFlags();
        if (!hasAllCodeFlags) {
            this.codeHasAttributes = new boolean[countMatches];
        }
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < countMatches; i5++) {
            if (decodeBandInt[i5] == 0) {
                i4++;
                if (!hasAllCodeFlags) {
                    this.codeHasAttributes[i5] = true;
                }
            }
        }
        int[] decodeBandInt2 = decodeBandInt("code_max_stack", inputStream2, Codec.UNSIGNED5, i4);
        int[] decodeBandInt3 = decodeBandInt("code_max_na_locals", inputStream2, Codec.UNSIGNED5, i4);
        int[] decodeBandInt4 = decodeBandInt("code_handler_count", inputStream2, Codec.UNSIGNED5, i4);
        this.codeMaxStack = new int[countMatches];
        this.codeMaxNALocals = new int[countMatches];
        this.codeHandlerCount = new int[countMatches];
        int i6 = 0;
        int i7 = 0;
        while (i6 < countMatches) {
            int i8 = decodeBandInt[i6] & 255;
            if (i8 >= 0) {
                if (i8 == 0) {
                    this.codeMaxStack[i6] = decodeBandInt2[i7];
                    this.codeMaxNALocals[i6] = decodeBandInt3[i7];
                    this.codeHandlerCount[i6] = decodeBandInt4[i7];
                    i7++;
                    i = i2;
                } else {
                    i = i2;
                    if (i8 <= 144) {
                        int i9 = i8 - 1;
                        this.codeMaxStack[i6] = i9 % 12;
                        this.codeMaxNALocals[i6] = i9 / 12;
                        this.codeHandlerCount[i6] = 0;
                    } else if (i8 <= 208) {
                        int i10 = i8 - 145;
                        this.codeMaxStack[i6] = i10 % 8;
                        this.codeMaxNALocals[i6] = i10 / 8;
                        this.codeHandlerCount[i6] = 1;
                    } else if (i8 <= 255) {
                        int i11 = i8 - 209;
                        this.codeMaxStack[i6] = i11 % 7;
                        this.codeMaxNALocals[i6] = i11 / 7;
                        this.codeHandlerCount[i6] = i;
                    } else {
                        throw new IllegalStateException("Shouldn't get here either");
                    }
                }
                i6++;
                i2 = i;
            } else {
                throw new IllegalStateException("Shouldn't get here");
            }
        }
        this.codeHandlerStartP = decodeBandInt("code_handler_start_P", inputStream2, Codec.BCI5, this.codeHandlerCount);
        this.codeHandlerEndPO = decodeBandInt("code_handler_end_PO", inputStream2, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerCatchPO = decodeBandInt("code_handler_catch_PO", inputStream2, Codec.BRANCH5, this.codeHandlerCount);
        this.codeHandlerClassRCN = decodeBandInt("code_handler_class_RCN", inputStream2, Codec.UNSIGNED5, this.codeHandlerCount);
        if (!hasAllCodeFlags) {
            countMatches = i4;
        }
        this.codeAttributes = new List[countMatches];
        while (true) {
            List[] listArr = this.codeAttributes;
            if (i3 < listArr.length) {
                listArr[i3] = new ArrayList();
                i3++;
            } else {
                parseCodeAttrBands(inputStream2, countMatches);
                return;
            }
        }
    }

    private void parseCodeAttrBands(InputStream inputStream, int i) throws IOException, Pack200Exception {
        int i2;
        int i3;
        int i4;
        long[] jArr;
        InputStream inputStream2 = inputStream;
        long[] parseFlags = parseFlags("code_flags", inputStream2, i, Codec.UNSIGNED5, this.segment.getSegmentHeader().getOptions().hasCodeFlagsHi());
        int[][] decodeBandInt = decodeBandInt("code_attr_indexes", inputStream2, Codec.UNSIGNED5, decodeBandInt("code_attr_count", inputStream2, Codec.UNSIGNED5, SegmentUtils.countBit16(parseFlags)));
        int i5 = 0;
        for (int[] iArr : decodeBandInt) {
            int i6 = 0;
            while (true) {
                if (i6 >= iArr.length) {
                    break;
                }
                i5 += this.attrMap.getAttributeLayout(iArr[i6], 3).numBackwardsCallables();
                i6++;
            }
        }
        int[] decodeBandInt2 = decodeBandInt("code_attr_calls", inputStream2, Codec.UNSIGNED5, i5);
        AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3);
        int[] decodeBandInt3 = decodeBandInt("code_LineNumberTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout));
        int[][] decodeBandInt4 = decodeBandInt("code_LineNumberTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt3);
        int[][] decodeBandInt5 = decodeBandInt("code_LineNumberTable_line", inputStream2, Codec.UNSIGNED5, decodeBandInt3);
        AttributeLayout attributeLayout2 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3);
        AttributeLayout attributeLayout3 = this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3);
        int[] decodeBandInt6 = decodeBandInt("code_LocalVariableTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout2));
        int[][] decodeBandInt7 = decodeBandInt("code_LocalVariableTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt6);
        int[][] decodeBandInt8 = decodeBandInt("code_LocalVariableTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt6);
        CPUTF8[][] parseCPUTF8References = parseCPUTF8References("code_LocalVariableTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        CPUTF8[][] parseCPSignatureReferences = parseCPSignatureReferences("code_LocalVariableTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        CPUTF8[][] cputf8Arr = parseCPUTF8References;
        int[][] decodeBandInt9 = decodeBandInt("code_LocalVariableTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt6);
        int[] iArr2 = decodeBandInt3;
        int[] decodeBandInt10 = decodeBandInt("code_LocalVariableTypeTable_N", inputStream2, Codec.UNSIGNED5, SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout3));
        int[][] decodeBandInt11 = decodeBandInt("code_LocalVariableTypeTable_bci_P", inputStream2, Codec.BCI5, decodeBandInt10);
        int[][] decodeBandInt12 = decodeBandInt("code_LocalVariableTypeTable_span_O", inputStream2, Codec.BRANCH5, decodeBandInt10);
        CPUTF8[][] parseCPUTF8References2 = parseCPUTF8References("code_LocalVariableTypeTable_name_RU", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        CPUTF8[][] parseCPSignatureReferences2 = parseCPSignatureReferences("code_LocalVariableTypeTable_type_RS", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int[][] decodeBandInt13 = decodeBandInt("code_LocalVariableTypeTable_slot", inputStream2, Codec.UNSIGNED5, decodeBandInt10);
        int i7 = this.options.hasCodeFlagsHi() ? 62 : 31;
        int[] iArr3 = decodeBandInt10;
        int i8 = i7 + 1;
        int[][] iArr4 = decodeBandInt13;
        AttributeLayout[] attributeLayoutArr = new AttributeLayout[i8];
        int[] iArr5 = new int[i8];
        List[] listArr = new List[i8];
        int i9 = 0;
        while (i9 < i7) {
            int[][] iArr6 = decodeBandInt4;
            CPUTF8[][] cputf8Arr2 = parseCPSignatureReferences;
            AttributeLayout attributeLayout4 = this.attrMap.getAttributeLayout(i9, 3);
            if (attributeLayout4 != null && !attributeLayout4.isDefaultLayout()) {
                attributeLayoutArr[i9] = attributeLayout4;
                iArr5[i9] = SegmentUtils.countMatches(parseFlags, (IMatcher) attributeLayout4);
            }
            i9++;
            decodeBandInt4 = iArr6;
            parseCPSignatureReferences = cputf8Arr2;
        }
        int[][] iArr7 = decodeBandInt4;
        CPUTF8[][] cputf8Arr3 = parseCPSignatureReferences;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i8) {
            if (iArr5[i10] > 0) {
                NewAttributeBands attributeBands = this.attrMap.getAttributeBands(attributeLayoutArr[i10]);
                listArr[i10] = attributeBands.parseAttributes(inputStream2, iArr5[i10]);
                int numBackwardsCallables = attributeLayoutArr[i10].numBackwardsCallables();
                if (numBackwardsCallables > 0) {
                    jArr = parseFlags;
                    int[] iArr8 = new int[numBackwardsCallables];
                    System.arraycopy(decodeBandInt2, i11, iArr8, 0, numBackwardsCallables);
                    attributeBands.setBackwardsCalls(iArr8);
                    i11 += numBackwardsCallables;
                    i10++;
                    inputStream2 = inputStream;
                    parseFlags = jArr;
                }
            }
            jArr = parseFlags;
            i10++;
            inputStream2 = inputStream;
            parseFlags = jArr;
        }
        long[] jArr2 = parseFlags;
        int i12 = i;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        while (i14 < i12) {
            int i17 = i13;
            int i18 = i14;
            if (attributeLayout.matches(jArr2[i18])) {
                i2 = i15;
                this.codeAttributes[i18].add(new LineNumberTableAttribute(iArr2[i17], iArr7[i17], decodeBandInt5[i17]));
                i3 = i17 + 1;
            } else {
                i2 = i15;
                i3 = i17;
            }
            if (attributeLayout2.matches(jArr2[i18])) {
                i4 = i2 + 1;
                this.codeAttributes[i18].add(new LocalVariableTableAttribute(decodeBandInt6[i2], decodeBandInt7[i2], decodeBandInt8[i2], cputf8Arr[i2], cputf8Arr3[i2], decodeBandInt9[i2]));
            } else {
                i4 = i2;
            }
            int i19 = i3;
            if (attributeLayout3.matches(jArr2[i18])) {
                i16++;
                this.codeAttributes[i18].add(new LocalVariableTypeTableAttribute(iArr3[i16], decodeBandInt11[i16], decodeBandInt12[i16], parseCPUTF8References2[i16], parseCPSignatureReferences2[i16], iArr4[i16]));
            }
            int i20 = 0;
            while (i20 < i8) {
                AttributeLayout attributeLayout5 = attributeLayoutArr[i20];
                int i21 = i20;
                int i22 = i4;
                if (attributeLayout5 != null && attributeLayout5.matches(jArr2[i18])) {
                    this.codeAttributes[i18].add(listArr[i21].get(0));
                    listArr[i21].remove(0);
                }
                i20 = i21 + 1;
                i4 = i22;
            }
            i14 = i18 + 1;
            i13 = i19;
            i15 = i4;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0066  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseFieldMetadataBands(java.io.InputStream r13, int[] r14) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r12 = this;
            java.lang.String r0 = "RVA"
            java.lang.String r1 = "RIA"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r0 = r12.attrMap
            java.lang.String r1 = "RuntimeVisibleAnnotations"
            r8 = 1
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r0 = r0.getAttributeLayout((java.lang.String) r1, (int) r8)
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r1 = r12.attrMap
            java.lang.String r2 = "RuntimeInvisibleAnnotations"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r1 = r1.getAttributeLayout((java.lang.String) r2, (int) r8)
            long[][] r2 = r12.fieldFlags
            int r2 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[][]) r2, (org.apache.commons.compress.harmony.unpack200.IMatcher) r0)
            long[][] r3 = r12.fieldFlags
            int r3 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[][]) r3, (org.apache.commons.compress.harmony.unpack200.IMatcher) r1)
            int[] r5 = new int[]{r2, r3}
            r6 = 2
            r7 = r6
            int[] r6 = new int[r7]
            r9 = 0
            r6[r9] = r9
            r6[r8] = r9
            if (r2 <= 0) goto L_0x0042
            r2 = r14[r9]
            r6[r9] = r2
            if (r3 <= 0) goto L_0x0040
            r14 = r14[r8]
            r6[r8] = r14
            r14 = r7
            goto L_0x004a
        L_0x0040:
            r14 = r8
            goto L_0x004a
        L_0x0042:
            if (r3 <= 0) goto L_0x0049
            r14 = r14[r9]
            r6[r8] = r14
            goto L_0x0040
        L_0x0049:
            r14 = r9
        L_0x004a:
            java.lang.String r7 = "field"
            r2 = r12
            r3 = r13
            org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[] r13 = r2.parseMetadata(r3, r4, r5, r6, r7)
            r3 = r13[r9]
            java.util.List r3 = r3.getAttributes()
            r13 = r13[r8]
            java.util.List r13 = r13.getAttributes()
            r4 = r9
            r5 = r4
            r6 = r5
        L_0x0061:
            long[][] r7 = r2.fieldFlags
            int r7 = r7.length
            if (r4 >= r7) goto L_0x00a8
            r7 = r9
        L_0x0067:
            long[][] r8 = r2.fieldFlags
            r8 = r8[r4]
            int r10 = r8.length
            if (r7 >= r10) goto L_0x00a5
            r10 = r8[r7]
            boolean r8 = r0.matches(r10)
            if (r8 == 0) goto L_0x0086
            java.util.ArrayList[][] r8 = r2.fieldAttributes
            r8 = r8[r4]
            r8 = r8[r7]
            int r10 = r5 + 1
            java.lang.Object r5 = r3.get(r5)
            r8.add(r5)
            r5 = r10
        L_0x0086:
            long[][] r8 = r2.fieldFlags
            r8 = r8[r4]
            r10 = r8[r7]
            boolean r8 = r1.matches(r10)
            if (r8 == 0) goto L_0x00a2
            java.util.ArrayList[][] r8 = r2.fieldAttributes
            r8 = r8[r4]
            r8 = r8[r7]
            int r10 = r6 + 1
            java.lang.Object r6 = r13.get(r6)
            r8.add(r6)
            r6 = r10
        L_0x00a2:
            int r7 = r7 + 1
            goto L_0x0067
        L_0x00a5:
            int r4 = r4 + 1
            goto L_0x0061
        L_0x00a8:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseFieldMetadataBands(java.io.InputStream, int[]):int");
    }

    private MetadataBandGroup[] parseMetadata(InputStream inputStream, String[] strArr, int[] iArr, int[] iArr2, String str) throws IOException, Pack200Exception {
        int i;
        InputStream inputStream2 = inputStream;
        String[] strArr2 = strArr;
        String str2 = str;
        MetadataBandGroup[] metadataBandGroupArr = new MetadataBandGroup[strArr2.length];
        int i2 = 0;
        while (i2 < strArr2.length) {
            metadataBandGroupArr[i2] = new MetadataBandGroup(strArr2[i2], this.cpBands);
            String str3 = strArr2[i2];
            if (str3.indexOf(80) >= 0) {
                metadataBandGroupArr[i2].param_NB = decodeBandInt(str2 + "_" + str3 + "_param_NB", inputStream2, Codec.BYTE1, iArr[i2]);
            }
            if (!str3.equals("AD")) {
                metadataBandGroupArr[i2].anno_N = decodeBandInt(str2 + "_" + str3 + "_anno_N", inputStream2, Codec.UNSIGNED5, iArr[i2]);
                metadataBandGroupArr[i2].type_RS = parseCPSignatureReferences(str2 + "_" + str3 + "_type_RS", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                metadataBandGroupArr[i2].pair_N = decodeBandInt(str2 + "_" + str3 + "_pair_N", inputStream2, Codec.UNSIGNED5, metadataBandGroupArr[i2].anno_N);
                i = 0;
                for (int i3 = 0; i3 < metadataBandGroupArr[i2].pair_N.length; i3++) {
                    for (int i4 : metadataBandGroupArr[i2].pair_N[i3]) {
                        i += i4;
                    }
                }
                metadataBandGroupArr[i2].name_RU = parseCPUTF8References(str2 + "_" + str3 + "_name_RU", inputStream2, Codec.UNSIGNED5, i);
            } else {
                i = iArr[i2];
            }
            metadataBandGroupArr[i2].T = decodeBandInt(str2 + "_" + str3 + "_T", inputStream2, Codec.BYTE1, i + iArr2[i2]);
            MetadataBandGroup[] metadataBandGroupArr2 = metadataBandGroupArr;
            int i5 = i2;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i6 < metadataBandGroupArr2[i5].T.length) {
                char c = (char) metadataBandGroupArr2[i5].T[i6];
                int i16 = i6;
                if (c == '@') {
                    i11++;
                } else if (c != 'F') {
                    if (c != 'S') {
                        if (c == 'c') {
                            i14++;
                        } else if (c == 'e') {
                            i15++;
                        } else if (c == 's') {
                            i12++;
                        } else if (c != 'I') {
                            if (c == 'J') {
                                i13++;
                            } else if (c != 'Z') {
                                if (c != '[') {
                                    switch (c) {
                                        case 'B':
                                        case 'C':
                                            break;
                                        case 'D':
                                            i8++;
                                            break;
                                    }
                                } else {
                                    i10++;
                                }
                            }
                        }
                    }
                    i7++;
                } else {
                    i9++;
                }
                i6 = i16 + 1;
            }
            metadataBandGroupArr2[i5].caseI_KI = parseCPIntReferences(str2 + "_" + str3 + "_caseI_KI", inputStream2, Codec.UNSIGNED5, i7);
            metadataBandGroupArr2[i5].caseD_KD = parseCPDoubleReferences(str2 + "_" + str3 + "_caseD_KD", inputStream2, Codec.UNSIGNED5, i8);
            metadataBandGroupArr2[i5].caseF_KF = parseCPFloatReferences(str2 + "_" + str3 + "_caseF_KF", inputStream2, Codec.UNSIGNED5, i9);
            metadataBandGroupArr2[i5].caseJ_KJ = parseCPLongReferences(str2 + "_" + str3 + "_caseJ_KJ", inputStream2, Codec.UNSIGNED5, i13);
            metadataBandGroupArr2[i5].casec_RS = parseCPSignatureReferences(str2 + "_" + str3 + "_casec_RS", inputStream2, Codec.UNSIGNED5, i14);
            int i17 = i15;
            metadataBandGroupArr2[i5].caseet_RS = parseReferences(str2 + "_" + str3 + "_caseet_RS", inputStream2, Codec.UNSIGNED5, i17, this.cpBands.getCpSignature());
            inputStream2 = inputStream;
            metadataBandGroupArr2[i5].caseec_RU = parseReferences(str2 + "_" + str3 + "_caseec_RU", inputStream2, Codec.UNSIGNED5, i17, this.cpBands.getCpUTF8());
            metadataBandGroupArr2[i5].cases_RU = parseCPUTF8References(str2 + "_" + str3 + "_cases_RU", inputStream2, Codec.UNSIGNED5, i12);
            metadataBandGroupArr2[i5].casearray_N = decodeBandInt(str2 + "_" + str3 + "_casearray_N", inputStream2, Codec.UNSIGNED5, i10);
            metadataBandGroupArr2[i5].nesttype_RS = parseCPUTF8References(str2 + "_" + str3 + "_nesttype_RS", inputStream2, Codec.UNSIGNED5, i11);
            metadataBandGroupArr2[i5].nestpair_N = decodeBandInt(str2 + "_" + str3 + "_nestpair_N", inputStream2, Codec.UNSIGNED5, i11);
            int i18 = 0;
            for (int i19 : metadataBandGroupArr2[i5].nestpair_N) {
                i18 += i19;
            }
            metadataBandGroupArr2[i5].nestname_RU = parseCPUTF8References(str2 + "_" + str3 + "_nestname_RU", inputStream2, Codec.UNSIGNED5, i18);
            i2 = i5 + 1;
            strArr2 = strArr;
            metadataBandGroupArr = metadataBandGroupArr2;
        }
        return metadataBandGroupArr;
    }

    private int parseMethodMetadataBands(InputStream inputStream, int[] iArr) throws Pack200Exception, IOException {
        String[] strArr = {"RVA", "RIA", "RVPA", "RIPA", "AD"};
        int[] iArr2 = {0, 0, 0, 0, 0};
        AttributeLayout[] attributeLayoutArr = {this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, 2), this.attrMap.getAttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT, 2)};
        for (int i = 0; i < 5; i++) {
            iArr2[i] = SegmentUtils.countMatches(this.methodFlags, (IMatcher) attributeLayoutArr[i]);
        }
        int[] iArr3 = new int[5];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < 5; i4++) {
            if (iArr2[i4] > 0) {
                i2++;
                iArr3[i4] = iArr[i3];
                i3++;
            } else {
                iArr3[i4] = 0;
            }
        }
        MetadataBandGroup[] parseMetadata = parseMetadata(inputStream, strArr, iArr2, iArr3, "method");
        List[] listArr = new List[5];
        int[] iArr4 = new int[5];
        for (int i5 = 0; i5 < parseMetadata.length; i5++) {
            listArr[i5] = parseMetadata[i5].getAttributes();
            iArr4[i5] = 0;
        }
        for (int i6 = 0; i6 < this.methodFlags.length; i6++) {
            for (int i7 = 0; i7 < this.methodFlags[i6].length; i7++) {
                for (int i8 = 0; i8 < 5; i8++) {
                    if (attributeLayoutArr[i8].matches(this.methodFlags[i6][i7])) {
                        ArrayList arrayList = this.methodAttributes[i6][i7];
                        List list = listArr[i8];
                        int i9 = iArr4[i8];
                        iArr4[i8] = i9 + 1;
                        arrayList.add(list.get(i9));
                    }
                }
            }
        }
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0096 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int parseClassMetadataBands(java.io.InputStream r12, int[] r13) throws org.apache.commons.compress.harmony.pack200.Pack200Exception, java.io.IOException {
        /*
            r11 = this;
            java.lang.String r0 = "RVA"
            java.lang.String r1 = "RIA"
            java.lang.String[] r4 = new java.lang.String[]{r0, r1}
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r0 = r11.attrMap
            java.lang.String r1 = "RuntimeVisibleAnnotations"
            r8 = 0
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r0 = r0.getAttributeLayout((java.lang.String) r1, (int) r8)
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r1 = r11.attrMap
            java.lang.String r2 = "RuntimeInvisibleAnnotations"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r1 = r1.getAttributeLayout((java.lang.String) r2, (int) r8)
            long[] r2 = r11.classFlags
            int r2 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[]) r2, (org.apache.commons.compress.harmony.unpack200.IMatcher) r0)
            long[] r3 = r11.classFlags
            int r3 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countMatches((long[]) r3, (org.apache.commons.compress.harmony.unpack200.IMatcher) r1)
            int[] r5 = new int[]{r2, r3}
            r6 = 2
            r7 = r6
            int[] r6 = new int[r7]
            r6[r8] = r8
            r9 = 1
            r6[r9] = r8
            if (r2 <= 0) goto L_0x0042
            r2 = r13[r8]
            r6[r8] = r2
            if (r3 <= 0) goto L_0x0040
            r13 = r13[r9]
            r6[r9] = r13
            r13 = r7
            goto L_0x004a
        L_0x0040:
            r13 = r9
            goto L_0x004a
        L_0x0042:
            if (r3 <= 0) goto L_0x0049
            r13 = r13[r8]
            r6[r9] = r13
            goto L_0x0040
        L_0x0049:
            r13 = r8
        L_0x004a:
            java.lang.String r7 = "class"
            r2 = r11
            r3 = r12
            org.apache.commons.compress.harmony.unpack200.MetadataBandGroup[] r12 = r2.parseMetadata(r3, r4, r5, r6, r7)
            r3 = r12[r8]
            java.util.List r3 = r3.getAttributes()
            r12 = r12[r9]
            java.util.List r12 = r12.getAttributes()
            r4 = r8
            r5 = r4
        L_0x0060:
            long[] r6 = r2.classFlags
            int r7 = r6.length
            if (r8 >= r7) goto L_0x0096
            r9 = r6[r8]
            boolean r6 = r0.matches(r9)
            if (r6 == 0) goto L_0x007b
            java.util.ArrayList[] r6 = r2.classAttributes
            r6 = r6[r8]
            int r7 = r4 + 1
            java.lang.Object r4 = r3.get(r4)
            r6.add(r4)
            r4 = r7
        L_0x007b:
            long[] r6 = r2.classFlags
            r9 = r6[r8]
            boolean r6 = r1.matches(r9)
            if (r6 == 0) goto L_0x0093
            java.util.ArrayList[] r6 = r2.classAttributes
            r6 = r6[r8]
            int r7 = r5 + 1
            java.lang.Object r5 = r12.get(r5)
            r6.add(r5)
            r5 = r7
        L_0x0093:
            int r8 = r8 + 1
            goto L_0x0060
        L_0x0096:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.ClassBands.parseClassMetadataBands(java.io.InputStream, int[]):int");
    }

    public ArrayList[] getClassAttributes() {
        return this.classAttributes;
    }

    public int[] getClassFieldCount() {
        return this.classFieldCount;
    }

    public long[] getRawClassFlags() {
        return this.classFlags;
    }

    public long[] getClassFlags() throws Pack200Exception {
        if (this.classAccessFlags == null) {
            int i = 0;
            long j = 32767;
            for (int i2 = 0; i2 < 16; i2++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i2, 0);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i2));
                }
            }
            this.classAccessFlags = new long[this.classFlags.length];
            while (true) {
                long[] jArr = this.classFlags;
                if (i >= jArr.length) {
                    break;
                }
                this.classAccessFlags[i] = jArr[i] & j;
                i++;
            }
        }
        return this.classAccessFlags;
    }

    public int[][] getClassInterfacesInts() {
        return this.classInterfacesInts;
    }

    public int[] getClassMethodCount() {
        return this.classMethodCount;
    }

    public int[] getClassSuperInts() {
        return this.classSuperInts;
    }

    public int[] getClassThisInts() {
        return this.classThisInts;
    }

    public int[] getCodeMaxNALocals() {
        return this.codeMaxNALocals;
    }

    public int[] getCodeMaxStack() {
        return this.codeMaxStack;
    }

    public ArrayList[][] getFieldAttributes() {
        return this.fieldAttributes;
    }

    public int[][] getFieldDescrInts() {
        return this.fieldDescrInts;
    }

    public int[][] getMethodDescrInts() {
        return this.methodDescrInts;
    }

    public long[][] getFieldFlags() throws Pack200Exception {
        if (this.fieldAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 1);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.fieldAccessFlags = new long[this.fieldFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.fieldFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.fieldAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.fieldFlags[i2];
                    if (i3 >= jArr2.length) {
                        break;
                    }
                    this.fieldAccessFlags[i2][i3] = jArr2[i3] & j;
                    i3++;
                }
                i2++;
            }
        }
        return this.fieldAccessFlags;
    }

    public ArrayList getOrderedCodeAttributes() {
        ArrayList arrayList = new ArrayList(this.codeAttributes.length);
        for (int i = 0; i < this.codeAttributes.length; i++) {
            ArrayList arrayList2 = new ArrayList(this.codeAttributes[i].size());
            for (int i2 = 0; i2 < this.codeAttributes[i].size(); i2++) {
                arrayList2.add((Attribute) this.codeAttributes[i].get(i2));
            }
            arrayList.add(arrayList2);
        }
        return arrayList;
    }

    public ArrayList[][] getMethodAttributes() {
        return this.methodAttributes;
    }

    public String[][] getMethodDescr() {
        return this.methodDescr;
    }

    public long[][] getMethodFlags() throws Pack200Exception {
        if (this.methodAccessFlags == null) {
            long j = 32767;
            for (int i = 0; i < 16; i++) {
                AttributeLayout attributeLayout = this.attrMap.getAttributeLayout(i, 2);
                if (attributeLayout != null && !attributeLayout.isDefaultLayout()) {
                    j &= (long) (~(1 << i));
                }
            }
            this.methodAccessFlags = new long[this.methodFlags.length][];
            int i2 = 0;
            while (true) {
                long[][] jArr = this.methodFlags;
                if (i2 >= jArr.length) {
                    break;
                }
                this.methodAccessFlags[i2] = new long[jArr[i2].length];
                int i3 = 0;
                while (true) {
                    long[] jArr2 = this.methodFlags[i2];
                    if (i3 >= jArr2.length) {
                        break;
                    }
                    this.methodAccessFlags[i2][i3] = jArr2[i3] & j;
                    i3++;
                }
                i2++;
            }
        }
        return this.methodAccessFlags;
    }

    public int[] getClassVersionMajor() {
        return this.classVersionMajor;
    }

    public int[] getClassVersionMinor() {
        return this.classVersionMinor;
    }

    public int[] getCodeHandlerCount() {
        return this.codeHandlerCount;
    }

    public int[][] getCodeHandlerCatchPO() {
        return this.codeHandlerCatchPO;
    }

    public int[][] getCodeHandlerClassRCN() {
        return this.codeHandlerClassRCN;
    }

    public int[][] getCodeHandlerEndPO() {
        return this.codeHandlerEndPO;
    }

    public int[][] getCodeHandlerStartP() {
        return this.codeHandlerStartP;
    }

    public IcTuple[][] getIcLocal() {
        return this.icLocal;
    }

    public boolean[] getCodeHasAttributes() {
        return this.codeHasAttributes;
    }
}
