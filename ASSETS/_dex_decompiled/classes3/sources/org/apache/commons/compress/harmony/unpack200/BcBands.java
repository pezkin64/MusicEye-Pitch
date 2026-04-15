package org.apache.commons.compress.harmony.unpack200;

import java.util.List;

public class BcBands extends BandSet {
    private int[] bcByte;
    private int[] bcCaseCount;
    private int[] bcCaseValue;
    private int[] bcClassRef;
    private int[] bcDoubleRef;
    private int[][] bcEscByte;
    private int[] bcEscRef;
    private int[] bcEscRefSize;
    private int[] bcEscSize;
    private int[] bcFieldRef;
    private int[] bcFloatRef;
    private int[] bcIMethodRef;
    private int[] bcInitRef;
    private int[] bcIntRef;
    private int[] bcLabel;
    private int[] bcLocal;
    private int[] bcLongRef;
    private int[] bcMethodRef;
    private int[] bcShort;
    private int[] bcStringRef;
    private int[] bcSuperField;
    private int[] bcSuperMethod;
    private int[] bcThisField;
    private int[] bcThisMethod;
    private byte[][][] methodByteCodePacked;
    private List wideByteCodes;

    private boolean endsWithLoad(int i) {
        return i >= 21 && i <= 25;
    }

    private boolean endsWithStore(int i) {
        return i >= 54 && i <= 58;
    }

    private boolean startsWithIf(int i) {
        return (i >= 153 && i <= 166) || i == 198 || i == 199;
    }

    public BcBands(Segment segment) {
        super(segment);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0119, code lost:
        if (startsWithIf(r4) != false) goto L_0x0152;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x014c, code lost:
        r10 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x014f, code lost:
        r13 = r13 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0152, code lost:
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x015e, code lost:
        r11 = r11 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void read(java.io.InputStream r39) throws java.io.IOException, org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            r38 = this;
            r0 = r38
            r1 = r39
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            org.apache.commons.compress.harmony.unpack200.AttrDefinitionBands r2 = r2.getAttrDefinitionBands()
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r2 = r2.getAttributeDefinitionMap()
            org.apache.commons.compress.harmony.unpack200.SegmentHeader r3 = r0.header
            int r3 = r3.getClassCount()
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r4 = r4.getClassBands()
            long[][] r4 = r4.getMethodFlags()
            java.lang.String r5 = "ACC_ABSTRACT"
            r6 = 2
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r5 = r2.getAttributeLayout((java.lang.String) r5, (int) r6)
            java.lang.String r7 = "ACC_NATIVE"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r2 = r2.getAttributeLayout((java.lang.String) r7, (int) r6)
            byte[][][] r7 = new byte[r3][][]
            r0.methodByteCodePacked = r7
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r0.wideByteCodes = r8
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r26 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
        L_0x0061:
            if (r9 >= r3) goto L_0x01e3
            r6 = r4[r9]
            int r6 = r6.length
            r31 = r3
            byte[][][] r3 = r0.methodByteCodePacked
            r32 = r3
            byte[][] r3 = new byte[r6][]
            r32[r9] = r3
            r3 = r16
        L_0x0072:
            if (r3 >= r6) goto L_0x01d5
            r32 = r4[r9]
            r34 = r3
            r33 = r4
            r3 = r32[r34]
            boolean r32 = r5.matches(r3)
            if (r32 != 0) goto L_0x01ca
            boolean r3 = r2.matches(r3)
            if (r3 != 0) goto L_0x01ca
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
        L_0x008d:
            int r4 = r1.read()
            r4 = r4 & 255(0xff, float:3.57E-43)
            byte r4 = (byte) r4
            r32 = r2
            r2 = -1
            if (r4 == r2) goto L_0x009f
            r3.write(r4)
            r2 = r32
            goto L_0x008d
        L_0x009f:
            byte[][][] r2 = r0.methodByteCodePacked
            r2 = r2[r9]
            byte[] r3 = r3.toByteArray()
            r2[r34] = r3
            byte[][][] r2 = r0.methodByteCodePacked
            r2 = r2[r9]
            r2 = r2[r34]
            int r3 = r2.length
            int r2 = r2.length
            int[] r3 = new int[r2]
            r4 = r16
        L_0x00b5:
            if (r4 >= r2) goto L_0x00ca
            r35 = r2
            byte[][][] r2 = r0.methodByteCodePacked
            r2 = r2[r9]
            r2 = r2[r34]
            byte r2 = r2[r4]
            r2 = r2 & 255(0xff, float:3.57E-43)
            r3[r4] = r2
            int r4 = r4 + 1
            r2 = r35
            goto L_0x00b5
        L_0x00ca:
            r2 = r16
        L_0x00cc:
            byte[][][] r3 = r0.methodByteCodePacked
            r3 = r3[r9]
            r3 = r3[r34]
            int r4 = r3.length
            if (r2 >= r4) goto L_0x01c8
            byte r4 = r3[r2]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r35 = r2
            r2 = 132(0x84, float:1.85E-43)
            r36 = 1
            if (r4 == r2) goto L_0x01be
            r2 = 192(0xc0, float:2.69E-43)
            if (r4 == r2) goto L_0x016c
            r2 = 193(0xc1, float:2.7E-43)
            if (r4 == r2) goto L_0x016c
            r2 = 196(0xc4, float:2.75E-43)
            if (r4 == r2) goto L_0x016e
            r2 = 197(0xc5, float:2.76E-43)
            if (r4 == r2) goto L_0x016a
            r2 = 253(0xfd, float:3.55E-43)
            if (r4 == r2) goto L_0x0167
            r2 = 254(0xfe, float:3.56E-43)
            if (r4 == r2) goto L_0x0164
            switch(r4) {
                case 16: goto L_0x015e;
                case 17: goto L_0x015b;
                case 18: goto L_0x0158;
                case 19: goto L_0x0158;
                case 20: goto L_0x0155;
                default: goto L_0x00fc;
            }
        L_0x00fc:
            switch(r4) {
                case 167: goto L_0x0152;
                case 168: goto L_0x0152;
                case 169: goto L_0x014f;
                case 170: goto L_0x0145;
                case 171: goto L_0x013d;
                default: goto L_0x00ff;
            }
        L_0x00ff:
            switch(r4) {
                case 178: goto L_0x013a;
                case 179: goto L_0x013a;
                case 180: goto L_0x013a;
                case 181: goto L_0x013a;
                case 182: goto L_0x0137;
                case 183: goto L_0x0137;
                case 184: goto L_0x0137;
                case 185: goto L_0x0134;
                default: goto L_0x0102;
            }
        L_0x0102:
            switch(r4) {
                case 187: goto L_0x016c;
                case 188: goto L_0x015e;
                case 189: goto L_0x016c;
                default: goto L_0x0105;
            }
        L_0x0105:
            switch(r4) {
                case 200: goto L_0x0152;
                case 201: goto L_0x0152;
                case 202: goto L_0x0131;
                case 203: goto L_0x0131;
                case 204: goto L_0x0131;
                case 205: goto L_0x0131;
                case 206: goto L_0x012e;
                case 207: goto L_0x012e;
                case 208: goto L_0x012e;
                case 209: goto L_0x0131;
                case 210: goto L_0x0131;
                case 211: goto L_0x0131;
                case 212: goto L_0x0131;
                case 213: goto L_0x012e;
                case 214: goto L_0x012e;
                case 215: goto L_0x012e;
                case 216: goto L_0x012b;
                case 217: goto L_0x012b;
                case 218: goto L_0x012b;
                case 219: goto L_0x012b;
                case 220: goto L_0x0128;
                case 221: goto L_0x0128;
                case 222: goto L_0x0128;
                case 223: goto L_0x012b;
                case 224: goto L_0x012b;
                case 225: goto L_0x012b;
                case 226: goto L_0x012b;
                case 227: goto L_0x0128;
                case 228: goto L_0x0128;
                case 229: goto L_0x0128;
                case 230: goto L_0x0125;
                case 231: goto L_0x0125;
                case 232: goto L_0x0125;
                case 233: goto L_0x016c;
                case 234: goto L_0x0122;
                case 235: goto L_0x011f;
                case 236: goto L_0x016c;
                case 237: goto L_0x0122;
                case 238: goto L_0x011f;
                case 239: goto L_0x011c;
                default: goto L_0x0108;
            }
        L_0x0108:
            boolean r2 = r0.endsWithLoad(r4)
            if (r2 != 0) goto L_0x014f
            boolean r2 = r0.endsWithStore(r4)
            if (r2 == 0) goto L_0x0115
            goto L_0x014f
        L_0x0115:
            boolean r2 = r0.startsWithIf(r4)
            if (r2 == 0) goto L_0x0160
            goto L_0x0152
        L_0x011c:
            int r18 = r18 + 1
            goto L_0x0160
        L_0x011f:
            int r8 = r8 + 1
            goto L_0x0160
        L_0x0122:
            int r15 = r15 + 1
            goto L_0x0160
        L_0x0125:
            int r28 = r28 + 1
            goto L_0x0160
        L_0x0128:
            int r27 = r27 + 1
            goto L_0x0160
        L_0x012b:
            int r25 = r25 + 1
            goto L_0x0160
        L_0x012e:
            int r26 = r26 + 1
            goto L_0x0160
        L_0x0131:
            int r24 = r24 + 1
            goto L_0x0160
        L_0x0134:
            int r23 = r23 + 1
            goto L_0x0160
        L_0x0137:
            int r22 = r22 + 1
            goto L_0x0160
        L_0x013a:
            int r21 = r21 + 1
            goto L_0x0160
        L_0x013d:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r16)
            r7.add(r2)
            goto L_0x014c
        L_0x0145:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r36)
            r7.add(r2)
        L_0x014c:
            int r10 = r10 + 1
            goto L_0x0152
        L_0x014f:
            int r13 = r13 + 1
            goto L_0x0160
        L_0x0152:
            int r14 = r14 + 1
            goto L_0x0160
        L_0x0155:
            int r17 = r17 + 1
            goto L_0x0160
        L_0x0158:
            int r19 = r19 + 1
            goto L_0x0160
        L_0x015b:
            int r12 = r12 + 1
            goto L_0x0160
        L_0x015e:
            int r11 = r11 + 1
        L_0x0160:
            r2 = r35
            r4 = 2
            goto L_0x01c4
        L_0x0164:
            int r29 = r29 + 1
            goto L_0x0160
        L_0x0167:
            int r30 = r30 + 1
            goto L_0x0160
        L_0x016a:
            int r11 = r11 + 1
        L_0x016c:
            r4 = 2
            goto L_0x01b9
        L_0x016e:
            int r2 = r35 + 1
            byte r3 = r3[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.util.List r4 = r0.wideByteCodes
            r35 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r4.add(r2)
            r2 = 132(0x84, float:1.85E-43)
            if (r3 != r2) goto L_0x0189
            int r13 = r13 + 1
            int r12 = r12 + 1
            r4 = 2
            goto L_0x01bb
        L_0x0189:
            boolean r2 = r0.endsWithLoad(r3)
            if (r2 != 0) goto L_0x01b5
            boolean r2 = r0.endsWithStore(r3)
            if (r2 != 0) goto L_0x01b5
            r2 = 169(0xa9, float:2.37E-43)
            if (r3 != r2) goto L_0x019a
            goto L_0x01b5
        L_0x019a:
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r37 = r3
            java.lang.String r3 = "Found unhandled "
            r4.<init>(r3)
            org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode r3 = org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode.getByteCode(r37)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r4 = 2
            r2.log(r4, r3)
            goto L_0x01bb
        L_0x01b5:
            r4 = 2
            int r13 = r13 + 1
            goto L_0x01bb
        L_0x01b9:
            int r20 = r20 + 1
        L_0x01bb:
            r2 = r35
            goto L_0x01c4
        L_0x01be:
            r4 = 2
            int r13 = r13 + 1
            int r11 = r11 + 1
            goto L_0x01bb
        L_0x01c4:
            int r2 = r2 + 1
            goto L_0x00cc
        L_0x01c8:
            r4 = 2
            goto L_0x01cd
        L_0x01ca:
            r32 = r2
            goto L_0x01c8
        L_0x01cd:
            int r3 = r34 + 1
            r2 = r32
            r4 = r33
            goto L_0x0072
        L_0x01d5:
            r32 = r2
            r33 = r4
            r4 = 2
            int r9 = r9 + 1
            r6 = r4
            r3 = r31
            r4 = r33
            goto L_0x0061
        L_0x01e3:
            java.lang.String r2 = "bc_case_count"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r10)
            r0.bcCaseCount = r2
            r2 = r16
            r3 = r2
        L_0x01f0:
            int[] r4 = r0.bcCaseCount
            int r4 = r4.length
            if (r2 >= r4) goto L_0x020c
            java.lang.Object r4 = r7.get(r2)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0204
            int r3 = r3 + 1
            goto L_0x0209
        L_0x0204:
            int[] r4 = r0.bcCaseCount
            r4 = r4[r2]
            int r3 = r3 + r4
        L_0x0209:
            int r2 = r2 + 1
            goto L_0x01f0
        L_0x020c:
            java.lang.String r2 = "bc_case_value"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r4 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r4, (int) r3)
            r0.bcCaseValue = r2
            r2 = r16
        L_0x0218:
            if (r2 >= r10) goto L_0x0222
            int[] r3 = r0.bcCaseCount
            r3 = r3[r2]
            int r14 = r14 + r3
            int r2 = r2 + 1
            goto L_0x0218
        L_0x0222:
            java.lang.String r2 = "bc_byte"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r11)
            r0.bcByte = r2
            java.lang.String r2 = "bc_short"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r12)
            r0.bcShort = r2
            java.lang.String r2 = "bc_local"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r13)
            r0.bcLocal = r2
            java.lang.String r2 = "bc_label"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.BRANCH5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r14)
            r0.bcLabel = r2
            java.lang.String r2 = "bc_intref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r15)
            r0.bcIntRef = r2
            java.lang.String r2 = "bc_floatref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcFloatRef = r2
            java.lang.String r2 = "bc_longref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r17
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcLongRef = r2
            java.lang.String r2 = "bc_doubleref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r18
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcDoubleRef = r2
            java.lang.String r2 = "bc_stringref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r19
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcStringRef = r2
            java.lang.String r2 = "bc_classref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r20
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcClassRef = r2
            java.lang.String r2 = "bc_fieldref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r21
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcFieldRef = r2
            java.lang.String r2 = "bc_methodref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r22
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcMethodRef = r2
            java.lang.String r2 = "bc_imethodref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.DELTA5
            r8 = r23
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcIMethodRef = r2
            java.lang.String r2 = "bc_thisfield"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r24
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcThisField = r2
            java.lang.String r2 = "bc_superfield"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r25
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcSuperField = r2
            java.lang.String r2 = "bc_thismethod"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r26
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcThisMethod = r2
            java.lang.String r2 = "bc_supermethod"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r27
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcSuperMethod = r2
            java.lang.String r2 = "bc_initref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r28
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcInitRef = r2
            java.lang.String r2 = "bc_escref"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r30
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscRef = r2
            java.lang.String r2 = "bc_escrefsize"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscRefSize = r2
            java.lang.String r2 = "bc_escsize"
            org.apache.commons.compress.harmony.pack200.BHSDCodec r3 = org.apache.commons.compress.harmony.pack200.Codec.UNSIGNED5
            r8 = r29
            int[] r2 = r0.decodeBandInt((java.lang.String) r2, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r3, (int) r8)
            r0.bcEscSize = r2
            org.apache.commons.compress.harmony.pack200.BHSDCodec r2 = org.apache.commons.compress.harmony.pack200.Codec.BYTE1
            int[] r3 = r0.bcEscSize
            java.lang.String r4 = "bc_escbyte"
            int[][] r1 = r0.decodeBandInt((java.lang.String) r4, (java.io.InputStream) r1, (org.apache.commons.compress.harmony.pack200.BHSDCodec) r2, (int[]) r3)
            r0.bcEscByte = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.BcBands.read(java.io.InputStream):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x024b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void unpack() throws org.apache.commons.compress.harmony.pack200.Pack200Exception {
        /*
            r35 = this;
            r0 = r35
            org.apache.commons.compress.harmony.unpack200.SegmentHeader r1 = r0.header
            int r1 = r1.getClassCount()
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r2 = r2.getClassBands()
            long[][] r2 = r2.getMethodFlags()
            org.apache.commons.compress.harmony.unpack200.Segment r3 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r3 = r3.getClassBands()
            int[] r3 = r3.getCodeMaxNALocals()
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r4 = r4.getClassBands()
            int[] r4 = r4.getCodeMaxStack()
            org.apache.commons.compress.harmony.unpack200.Segment r5 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r5 = r5.getClassBands()
            java.util.ArrayList[][] r5 = r5.getMethodAttributes()
            org.apache.commons.compress.harmony.unpack200.Segment r6 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r6 = r6.getClassBands()
            java.lang.String[][] r6 = r6.getMethodDescr()
            org.apache.commons.compress.harmony.unpack200.Segment r7 = r0.segment
            org.apache.commons.compress.harmony.unpack200.AttrDefinitionBands r7 = r7.getAttrDefinitionBands()
            org.apache.commons.compress.harmony.unpack200.AttributeLayoutMap r7 = r7.getAttributeDefinitionMap()
            java.lang.String r8 = "ACC_ABSTRACT"
            r9 = 2
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r8 = r7.getAttributeLayout((java.lang.String) r8, (int) r9)
            java.lang.String r10 = "ACC_NATIVE"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r10 = r7.getAttributeLayout((java.lang.String) r10, (int) r9)
            java.lang.String r11 = "ACC_STATIC"
            org.apache.commons.compress.harmony.unpack200.AttributeLayout r7 = r7.getAttributeLayout((java.lang.String) r11, (int) r9)
            java.util.List r9 = r0.wideByteCodes
            int r9 = r9.size()
            int[] r11 = new int[r9]
            r33 = 0
            r12 = r33
        L_0x0063:
            if (r12 >= r9) goto L_0x0076
            java.util.List r13 = r0.wideByteCodes
            java.lang.Object r13 = r13.get(r12)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            r11[r12] = r13
            int r12 = r12 + 1
            goto L_0x0063
        L_0x0076:
            org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager r18 = new org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager
            int[] r12 = r0.bcCaseCount
            int[] r13 = r0.bcCaseValue
            int[] r14 = r0.bcByte
            int[] r15 = r0.bcShort
            int[] r9 = r0.bcLocal
            r34 = r2
            int[] r2 = r0.bcLabel
            r17 = r2
            int[] r2 = r0.bcIntRef
            r16 = r2
            int[] r2 = r0.bcFloatRef
            r19 = r2
            int[] r2 = r0.bcLongRef
            r20 = r2
            int[] r2 = r0.bcDoubleRef
            r21 = r2
            int[] r2 = r0.bcStringRef
            r22 = r2
            int[] r2 = r0.bcClassRef
            r23 = r2
            int[] r2 = r0.bcFieldRef
            r24 = r2
            int[] r2 = r0.bcMethodRef
            r25 = r2
            int[] r2 = r0.bcIMethodRef
            r26 = r2
            int[] r2 = r0.bcThisField
            r27 = r2
            int[] r2 = r0.bcSuperField
            r28 = r2
            int[] r2 = r0.bcThisMethod
            r29 = r2
            int[] r2 = r0.bcSuperMethod
            r30 = r2
            int[] r2 = r0.bcInitRef
            r31 = r2
            r32 = r11
            r11 = r18
            r18 = r16
            r16 = r9
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            r11.setSegment(r2)
            org.apache.commons.compress.harmony.unpack200.Segment r2 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r2 = r2.getClassBands()
            java.util.ArrayList r2 = r2.getOrderedCodeAttributes()
            org.apache.commons.compress.harmony.unpack200.Segment r9 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r9 = r9.getClassBands()
            int[] r9 = r9.getCodeHandlerCount()
            org.apache.commons.compress.harmony.unpack200.Segment r12 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r12 = r12.getClassBands()
            int[][] r12 = r12.getCodeHandlerStartP()
            org.apache.commons.compress.harmony.unpack200.Segment r13 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r13 = r13.getClassBands()
            int[][] r20 = r13.getCodeHandlerEndPO()
            org.apache.commons.compress.harmony.unpack200.Segment r13 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r13 = r13.getClassBands()
            int[][] r21 = r13.getCodeHandlerCatchPO()
            org.apache.commons.compress.harmony.unpack200.Segment r13 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r13 = r13.getClassBands()
            int[][] r22 = r13.getCodeHandlerClassRCN()
            org.apache.commons.compress.harmony.unpack200.Segment r13 = r0.segment
            org.apache.commons.compress.harmony.unpack200.SegmentHeader r13 = r13.getSegmentHeader()
            org.apache.commons.compress.harmony.unpack200.SegmentOptions r13 = r13.getOptions()
            boolean r23 = r13.hasAllCodeFlags()
            org.apache.commons.compress.harmony.unpack200.Segment r13 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r13 = r13.getClassBands()
            boolean[] r24 = r13.getCodeHasAttributes()
            r13 = r33
            r14 = r13
            r15 = r14
        L_0x0128:
            if (r13 >= r1) goto L_0x0298
            r25 = r1
            r1 = r34[r13]
            int r1 = r1.length
            r26 = r3
            r3 = r33
        L_0x0133:
            if (r3 >= r1) goto L_0x0283
            r16 = r34[r13]
            r28 = r3
            r27 = r4
            r3 = r16[r28]
            boolean r16 = r8.matches(r3)
            if (r16 != 0) goto L_0x0267
            boolean r16 = r10.matches(r3)
            if (r16 != 0) goto L_0x0267
            r16 = r14
            r14 = r27[r16]
            r17 = r26[r16]
            boolean r3 = r7.matches(r3)
            if (r3 != 0) goto L_0x0157
            int r17 = r17 + 1
        L_0x0157:
            r3 = r6[r13]
            r3 = r3[r28]
            int r3 = org.apache.commons.compress.harmony.unpack200.SegmentUtils.countInvokeInterfaceArgs(r3)
            int r17 = r17 + r3
            org.apache.commons.compress.harmony.unpack200.Segment r3 = r0.segment
            org.apache.commons.compress.harmony.unpack200.CpBands r3 = r3.getCpBands()
            java.lang.String[] r3 = r3.getCpClass()
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r4 = r4.getClassBands()
            int[] r4 = r4.getClassThisInts()
            r4 = r4[r13]
            r4 = r3[r4]
            r11.setCurrentClass(r4)
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.ClassBands r4 = r4.getClassBands()
            int[] r4 = r4.getClassSuperInts()
            r4 = r4[r13]
            r3 = r3[r4]
            r11.setSuperClass(r3)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            r29 = r1
            if (r9 == 0) goto L_0x01d6
            r4 = r33
        L_0x0198:
            r1 = r9[r16]
            if (r4 >= r1) goto L_0x01d6
            r1 = r22[r16]
            r1 = r1[r4]
            int r1 = r1 + -1
            r18 = r4
            r4 = -1
            if (r1 == r4) goto L_0x01b2
            org.apache.commons.compress.harmony.unpack200.Segment r4 = r0.segment
            org.apache.commons.compress.harmony.unpack200.CpBands r4 = r4.getCpBands()
            org.apache.commons.compress.harmony.unpack200.bytecode.CPClass r1 = r4.cpClassValue((int) r1)
            goto L_0x01b3
        L_0x01b2:
            r1 = 0
        L_0x01b3:
            org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry r4 = new org.apache.commons.compress.harmony.unpack200.bytecode.ExceptionTableEntry
            r19 = r12[r16]
            r30 = r5
            r5 = r19[r18]
            r19 = r20[r16]
            r31 = r6
            r6 = r19[r18]
            r19 = r21[r16]
            r32 = r7
            r7 = r19[r18]
            r4.<init>(r5, r6, r7, r1)
            r3.add(r4)
            int r4 = r18 + 1
            r5 = r30
            r6 = r31
            r7 = r32
            goto L_0x0198
        L_0x01d6:
            r30 = r5
            r31 = r6
            r32 = r7
            r1 = r13
            org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute r13 = new org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute
            byte[][][] r4 = r0.methodByteCodePacked
            r4 = r4[r1]
            r4 = r4[r28]
            org.apache.commons.compress.harmony.unpack200.Segment r5 = r0.segment
            r18 = r4
            r4 = r1
            r1 = r16
            r16 = r18
            r19 = r3
            r18 = r11
            r3 = r15
            r15 = r17
            r17 = r5
            r13.<init>(r14, r15, r16, r17, r18, r19)
            r5 = r30[r4]
            r5 = r5[r28]
            r6 = r33
            r7 = r6
        L_0x0201:
            int r14 = r5.size()
            if (r6 >= r14) goto L_0x0221
            java.lang.Object r14 = r5.get(r6)
            org.apache.commons.compress.harmony.unpack200.bytecode.Attribute r14 = (org.apache.commons.compress.harmony.unpack200.bytecode.Attribute) r14
            boolean r15 = r14 instanceof org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute
            if (r15 == 0) goto L_0x0221
            org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute r14 = (org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute) r14
            int r14 = r14.getLayoutIndex()
            r15 = 15
            if (r14 < r15) goto L_0x021c
            goto L_0x0221
        L_0x021c:
            int r7 = r7 + 1
            int r6 = r6 + 1
            goto L_0x0201
        L_0x0221:
            r5.add(r7, r13)
            java.util.List r5 = r13.byteCodeOffsets
            r13.renumber(r5)
            if (r23 == 0) goto L_0x0233
            java.lang.Object r5 = r2.get(r1)
            java.util.List r5 = (java.util.List) r5
        L_0x0231:
            r15 = r3
            goto L_0x0243
        L_0x0233:
            boolean r5 = r24[r1]
            if (r5 == 0) goto L_0x0240
            java.lang.Object r5 = r2.get(r3)
            java.util.List r5 = (java.util.List) r5
            int r15 = r3 + 1
            goto L_0x0243
        L_0x0240:
            java.util.List r5 = java.util.Collections.EMPTY_LIST
            goto L_0x0231
        L_0x0243:
            r3 = r33
        L_0x0245:
            int r6 = r5.size()
            if (r3 >= r6) goto L_0x0264
            java.lang.Object r6 = r5.get(r3)
            org.apache.commons.compress.harmony.unpack200.bytecode.Attribute r6 = (org.apache.commons.compress.harmony.unpack200.bytecode.Attribute) r6
            r13.addAttribute(r6)
            boolean r7 = r6.hasBCIRenumbering()
            if (r7 == 0) goto L_0x0261
            org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute r6 = (org.apache.commons.compress.harmony.unpack200.bytecode.BCIRenumberedAttribute) r6
            java.util.List r7 = r13.byteCodeOffsets
            r6.renumber(r7)
        L_0x0261:
            int r3 = r3 + 1
            goto L_0x0245
        L_0x0264:
            int r14 = r1 + 1
            goto L_0x0274
        L_0x0267:
            r29 = r1
            r30 = r5
            r31 = r6
            r32 = r7
            r4 = r13
            r1 = r14
            r3 = r15
            r14 = r1
            r15 = r3
        L_0x0274:
            int r3 = r28 + 1
            r13 = r4
            r4 = r27
            r1 = r29
            r5 = r30
            r6 = r31
            r7 = r32
            goto L_0x0133
        L_0x0283:
            r27 = r4
            r30 = r5
            r31 = r6
            r32 = r7
            r4 = r13
            r1 = r14
            r3 = r15
            int r13 = r4 + 1
            r1 = r25
            r3 = r26
            r4 = r27
            goto L_0x0128
        L_0x0298:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.unpack200.BcBands.unpack():void");
    }

    public byte[][][] getMethodByteCodePacked() {
        return this.methodByteCodePacked;
    }

    public int[] getBcCaseCount() {
        return this.bcCaseCount;
    }

    public int[] getBcCaseValue() {
        return this.bcCaseValue;
    }

    public int[] getBcByte() {
        return this.bcByte;
    }

    public int[] getBcClassRef() {
        return this.bcClassRef;
    }

    public int[] getBcDoubleRef() {
        return this.bcDoubleRef;
    }

    public int[] getBcFieldRef() {
        return this.bcFieldRef;
    }

    public int[] getBcFloatRef() {
        return this.bcFloatRef;
    }

    public int[] getBcIMethodRef() {
        return this.bcIMethodRef;
    }

    public int[] getBcInitRef() {
        return this.bcInitRef;
    }

    public int[] getBcIntRef() {
        return this.bcIntRef;
    }

    public int[] getBcLabel() {
        return this.bcLabel;
    }

    public int[] getBcLocal() {
        return this.bcLocal;
    }

    public int[] getBcLongRef() {
        return this.bcLongRef;
    }

    public int[] getBcMethodRef() {
        return this.bcMethodRef;
    }

    public int[] getBcShort() {
        return this.bcShort;
    }

    public int[] getBcStringRef() {
        return this.bcStringRef;
    }

    public int[] getBcSuperField() {
        return this.bcSuperField;
    }

    public int[] getBcSuperMethod() {
        return this.bcSuperMethod;
    }

    public int[] getBcThisField() {
        return this.bcThisField;
    }

    public int[] getBcThisMethod() {
        return this.bcThisMethod;
    }
}
