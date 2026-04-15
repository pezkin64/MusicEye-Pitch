package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Label;

public class BcBands extends BandSet {
    private static final int ALOAD_0 = 42;
    private static final int IINC = 132;
    private static final int INVOKEINTERFACE = 185;
    private static final int LOOKUPSWITCH = 171;
    private static final int MULTIANEWARRAY = 197;
    private static final int TABLESWITCH = 170;
    private static final int WIDE = 196;
    private static final int endMarker = 255;
    private final IntList bcByte = new IntList();
    private final IntList bcCaseCount = new IntList();
    private final IntList bcCaseValue = new IntList();
    private final List bcClassRef = new ArrayList();
    private final IntList bcCodes = new IntList();
    private final List bcDoubleRef = new ArrayList();
    private final List bcFieldRef = new ArrayList();
    private final List bcFloatRef = new ArrayList();
    private final List bcIMethodRef = new ArrayList();
    private List bcInitRef = new ArrayList();
    private final List bcIntref = new ArrayList();
    private final List bcLabel = new ArrayList();
    private final IntList bcLabelRelativeOffsets = new IntList();
    private final IntList bcLocal = new IntList();
    private final List bcLongRef = new ArrayList();
    private final List bcMethodRef = new ArrayList();
    private final IntList bcShort = new IntList();
    private final List bcStringRef = new ArrayList();
    private final List bcSuperField = new ArrayList();
    private List bcSuperMethod = new ArrayList();
    private List bcThisField = new ArrayList();
    private List bcThisMethod = new ArrayList();
    private final IntList bciRenumbering = new IntList();
    private int byteCodeOffset;
    private final CpBands cpBands;
    private String currentClass;
    private String currentNewClass;
    private final Map labelsToOffsets = new HashMap();
    private int renumberedOffset;
    private final Segment segment;
    private String superClass;

    public BcBands(CpBands cpBands2, Segment segment2, int i) {
        super(i, segment2.getSegmentHeader());
        this.cpBands = cpBands2;
        this.segment = segment2;
    }

    public void setCurrentClass(String str, String str2) {
        this.currentClass = str;
        this.superClass = str2;
    }

    public void finaliseBands() {
        this.bcThisField = getIndexInClass(this.bcThisField);
        this.bcThisMethod = getIndexInClass(this.bcThisMethod);
        this.bcSuperMethod = getIndexInClass(this.bcSuperMethod);
        this.bcInitRef = getIndexInClassForConstructor(this.bcInitRef);
    }

    public void pack(OutputStream outputStream) throws IOException, Pack200Exception {
        PackingUtils.log("Writing byte code bands...");
        byte[] encodeBandInt = encodeBandInt("bcCodes", this.bcCodes.toArray(), Codec.BYTE1);
        outputStream.write(encodeBandInt);
        PackingUtils.log("Wrote " + encodeBandInt.length + " bytes from bcCodes[" + this.bcCodes.size() + "]");
        byte[] encodeBandInt2 = encodeBandInt("bcCaseCount", this.bcCaseCount.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt2);
        PackingUtils.log("Wrote " + encodeBandInt2.length + " bytes from bcCaseCount[" + this.bcCaseCount.size() + "]");
        byte[] encodeBandInt3 = encodeBandInt("bcCaseValue", this.bcCaseValue.toArray(), Codec.DELTA5);
        outputStream.write(encodeBandInt3);
        PackingUtils.log("Wrote " + encodeBandInt3.length + " bytes from bcCaseValue[" + this.bcCaseValue.size() + "]");
        byte[] encodeBandInt4 = encodeBandInt("bcByte", this.bcByte.toArray(), Codec.BYTE1);
        outputStream.write(encodeBandInt4);
        PackingUtils.log("Wrote " + encodeBandInt4.length + " bytes from bcByte[" + this.bcByte.size() + "]");
        byte[] encodeBandInt5 = encodeBandInt("bcShort", this.bcShort.toArray(), Codec.DELTA5);
        outputStream.write(encodeBandInt5);
        PackingUtils.log("Wrote " + encodeBandInt5.length + " bytes from bcShort[" + this.bcShort.size() + "]");
        byte[] encodeBandInt6 = encodeBandInt("bcLocal", this.bcLocal.toArray(), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt6);
        PackingUtils.log("Wrote " + encodeBandInt6.length + " bytes from bcLocal[" + this.bcLocal.size() + "]");
        byte[] encodeBandInt7 = encodeBandInt("bcLabel", integerListToArray(this.bcLabel), Codec.BRANCH5);
        outputStream.write(encodeBandInt7);
        PackingUtils.log("Wrote " + encodeBandInt7.length + " bytes from bcLabel[" + this.bcLabel.size() + "]");
        byte[] encodeBandInt8 = encodeBandInt("bcIntref", cpEntryListToArray(this.bcIntref), Codec.DELTA5);
        outputStream.write(encodeBandInt8);
        PackingUtils.log("Wrote " + encodeBandInt8.length + " bytes from bcIntref[" + this.bcIntref.size() + "]");
        byte[] encodeBandInt9 = encodeBandInt("bcFloatRef", cpEntryListToArray(this.bcFloatRef), Codec.DELTA5);
        outputStream.write(encodeBandInt9);
        PackingUtils.log("Wrote " + encodeBandInt9.length + " bytes from bcFloatRef[" + this.bcFloatRef.size() + "]");
        byte[] encodeBandInt10 = encodeBandInt("bcLongRef", cpEntryListToArray(this.bcLongRef), Codec.DELTA5);
        outputStream.write(encodeBandInt10);
        PackingUtils.log("Wrote " + encodeBandInt10.length + " bytes from bcLongRef[" + this.bcLongRef.size() + "]");
        byte[] encodeBandInt11 = encodeBandInt("bcDoubleRef", cpEntryListToArray(this.bcDoubleRef), Codec.DELTA5);
        outputStream.write(encodeBandInt11);
        PackingUtils.log("Wrote " + encodeBandInt11.length + " bytes from bcDoubleRef[" + this.bcDoubleRef.size() + "]");
        byte[] encodeBandInt12 = encodeBandInt("bcStringRef", cpEntryListToArray(this.bcStringRef), Codec.DELTA5);
        outputStream.write(encodeBandInt12);
        PackingUtils.log("Wrote " + encodeBandInt12.length + " bytes from bcStringRef[" + this.bcStringRef.size() + "]");
        byte[] encodeBandInt13 = encodeBandInt("bcClassRef", cpEntryOrNullListToArray(this.bcClassRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt13);
        PackingUtils.log("Wrote " + encodeBandInt13.length + " bytes from bcClassRef[" + this.bcClassRef.size() + "]");
        byte[] encodeBandInt14 = encodeBandInt("bcFieldRef", cpEntryListToArray(this.bcFieldRef), Codec.DELTA5);
        outputStream.write(encodeBandInt14);
        PackingUtils.log("Wrote " + encodeBandInt14.length + " bytes from bcFieldRef[" + this.bcFieldRef.size() + "]");
        byte[] encodeBandInt15 = encodeBandInt("bcMethodRef", cpEntryListToArray(this.bcMethodRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt15);
        PackingUtils.log("Wrote " + encodeBandInt15.length + " bytes from bcMethodRef[" + this.bcMethodRef.size() + "]");
        byte[] encodeBandInt16 = encodeBandInt("bcIMethodRef", cpEntryListToArray(this.bcIMethodRef), Codec.DELTA5);
        outputStream.write(encodeBandInt16);
        PackingUtils.log("Wrote " + encodeBandInt16.length + " bytes from bcIMethodRef[" + this.bcIMethodRef.size() + "]");
        byte[] encodeBandInt17 = encodeBandInt("bcThisField", integerListToArray(this.bcThisField), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt17);
        PackingUtils.log("Wrote " + encodeBandInt17.length + " bytes from bcThisField[" + this.bcThisField.size() + "]");
        byte[] encodeBandInt18 = encodeBandInt("bcSuperField", integerListToArray(this.bcSuperField), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt18);
        PackingUtils.log("Wrote " + encodeBandInt18.length + " bytes from bcSuperField[" + this.bcSuperField.size() + "]");
        byte[] encodeBandInt19 = encodeBandInt("bcThisMethod", integerListToArray(this.bcThisMethod), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt19);
        PackingUtils.log("Wrote " + encodeBandInt19.length + " bytes from bcThisMethod[" + this.bcThisMethod.size() + "]");
        byte[] encodeBandInt20 = encodeBandInt("bcSuperMethod", integerListToArray(this.bcSuperMethod), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt20);
        PackingUtils.log("Wrote " + encodeBandInt20.length + " bytes from bcSuperMethod[" + this.bcSuperMethod.size() + "]");
        byte[] encodeBandInt21 = encodeBandInt("bcInitRef", integerListToArray(this.bcInitRef), Codec.UNSIGNED5);
        outputStream.write(encodeBandInt21);
        PackingUtils.log("Wrote " + encodeBandInt21.length + " bytes from bcInitRef[" + this.bcInitRef.size() + "]");
    }

    private List getIndexInClass(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClass()));
        }
        return arrayList;
    }

    private List getIndexInClassForConstructor(List list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((CPMethodOrField) list.get(i)).getIndexInClassForConstructor()));
        }
        return arrayList;
    }

    public void visitEnd() {
        for (int i = 0; i < this.bciRenumbering.size(); i++) {
            if (this.bciRenumbering.get(i) == -1) {
                this.bciRenumbering.remove(i);
                IntList intList = this.bciRenumbering;
                int i2 = this.renumberedOffset + 1;
                this.renumberedOffset = i2;
                intList.add(i, i2);
            }
        }
        int i3 = this.renumberedOffset;
        if (i3 == 0) {
            return;
        }
        if (i3 + 1 == this.bciRenumbering.size()) {
            for (int size = this.bcLabel.size() - 1; size >= 0; size--) {
                Object obj = this.bcLabel.get(size);
                if (obj instanceof Integer) {
                    break;
                }
                if (obj instanceof Label) {
                    this.bcLabel.remove(size);
                    this.bcLabel.add(size, Integer.valueOf(this.bciRenumbering.get(((Integer) this.labelsToOffsets.get(obj)).intValue()) - this.bciRenumbering.get(this.bcLabelRelativeOffsets.get(size))));
                }
            }
            this.bcCodes.add(255);
            this.segment.getClassBands().doBciRenumbering(this.bciRenumbering, this.labelsToOffsets);
            this.bciRenumbering.clear();
            this.labelsToOffsets.clear();
            this.byteCodeOffset = 0;
            this.renumberedOffset = 0;
            return;
        }
        throw new RuntimeException("Mistake made with renumbering");
    }

    public void visitLabel(Label label) {
        this.labelsToOffsets.put(label, Integer.valueOf(this.byteCodeOffset));
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0035  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitFieldInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            int r0 = r4.byteCodeOffset
            int r0 = r0 + 3
            r4.byteCodeOffset = r0
            r4.updateRenumbering()
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r0 = r0.size()
            r1 = 42
            if (r0 <= 0) goto L_0x002c
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            r3 = 1
            int r2 = r2 - r3
            int r0 = r0.get(r2)
            if (r0 != r1) goto L_0x002c
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            int r2 = r2 - r3
            r0.remove(r2)
            goto L_0x002d
        L_0x002c:
            r3 = 0
        L_0x002d:
            org.apache.commons.compress.harmony.pack200.CpBands r0 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r7 = r0.getCPField((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            if (r3 == 0) goto L_0x0037
            int r5 = r5 + 7
        L_0x0037:
            java.lang.String r8 = r4.currentClass
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0047
            int r5 = r5 + 24
            java.util.List r6 = r4.bcThisField
            r6.add(r7)
            goto L_0x0055
        L_0x0047:
            if (r3 == 0) goto L_0x0050
            int r5 = r5 + -7
            org.apache.commons.compress.harmony.pack200.IntList r6 = r4.bcCodes
            r6.add(r1)
        L_0x0050:
            java.util.List r6 = r4.bcFieldRef
            r6.add(r7)
        L_0x0055:
            org.apache.commons.compress.harmony.pack200.IntList r6 = r4.bcCodes
            r6.add(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitFieldInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    private void updateRenumbering() {
        if (this.bciRenumbering.isEmpty()) {
            this.bciRenumbering.add(0);
        }
        this.renumberedOffset++;
        for (int size = this.bciRenumbering.size(); size < this.byteCodeOffset; size++) {
            this.bciRenumbering.add(-1);
        }
        this.bciRenumbering.add(this.renumberedOffset);
    }

    public void visitIincInsn(int i, int i2) {
        if (i > 255 || i2 > 255) {
            this.byteCodeOffset += 6;
            this.bcCodes.add(WIDE);
            this.bcCodes.add(IINC);
            this.bcLocal.add(i);
            this.bcShort.add(i2);
        } else {
            this.byteCodeOffset += 3;
            this.bcCodes.add(IINC);
            this.bcLocal.add(i);
            this.bcByte.add(i2 & 255);
        }
        updateRenumbering();
    }

    public void visitInsn(int i) {
        if (i < 202) {
            this.bcCodes.add(i);
            this.byteCodeOffset++;
            updateRenumbering();
            return;
        }
        throw new RuntimeException("Non-standard bytecode instructions not supported");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r2 != 188) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitIntInsn(int r2, int r3) {
        /*
            r1 = this;
            r0 = 16
            if (r2 == r0) goto L_0x001e
            r0 = 17
            if (r2 == r0) goto L_0x000d
            r0 = 188(0xbc, float:2.63E-43)
            if (r2 == r0) goto L_0x001e
            goto L_0x0030
        L_0x000d:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r1.bcCodes
            r0.add(r2)
            org.apache.commons.compress.harmony.pack200.IntList r2 = r1.bcShort
            r2.add(r3)
            int r2 = r1.byteCodeOffset
            int r2 = r2 + 3
            r1.byteCodeOffset = r2
            goto L_0x0030
        L_0x001e:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r1.bcCodes
            r0.add(r2)
            org.apache.commons.compress.harmony.pack200.IntList r2 = r1.bcByte
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2.add(r3)
            int r2 = r1.byteCodeOffset
            int r2 = r2 + 2
            r1.byteCodeOffset = r2
        L_0x0030:
            r1.updateRenumbering()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitIntInsn(int, int):void");
    }

    public void visitJumpInsn(int i, Label label) {
        this.bcCodes.add(i);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.byteCodeOffset += 3;
        updateRenumbering();
    }

    public void visitLdcInsn(Object obj) {
        CPConstant constant = this.cpBands.getConstant(obj);
        if (this.segment.lastConstantHadWideIndex() || (constant instanceof CPLong) || (constant instanceof CPDouble)) {
            this.byteCodeOffset += 3;
            if (constant instanceof CPInt) {
                this.bcCodes.add(237);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(238);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPLong) {
                this.bcCodes.add(20);
                this.bcLongRef.add(constant);
            } else if (constant instanceof CPDouble) {
                this.bcCodes.add(239);
                this.bcDoubleRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(19);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(236);
                this.bcClassRef.add(constant);
            } else {
                throw new RuntimeException("Constant should not be null");
            }
        } else {
            this.byteCodeOffset += 2;
            if (constant instanceof CPInt) {
                this.bcCodes.add(234);
                this.bcIntref.add(constant);
            } else if (constant instanceof CPFloat) {
                this.bcCodes.add(235);
                this.bcFloatRef.add(constant);
            } else if (constant instanceof CPString) {
                this.bcCodes.add(18);
                this.bcStringRef.add(constant);
            } else if (constant instanceof CPClass) {
                this.bcCodes.add(233);
                this.bcClassRef.add(constant);
            }
        }
        updateRenumbering();
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        this.bcCodes.add(LOOKUPSWITCH);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseCount.add(iArr.length);
        int i = 0;
        for (int i2 = 0; i2 < labelArr.length; i2++) {
            this.bcCaseValue.add(iArr[i2]);
            this.bcLabel.add(labelArr[i2]);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i3 = this.byteCodeOffset;
        if ((i3 + 1) % 4 != 0) {
            i = 4 - ((i3 + 1) % 4);
        }
        this.byteCodeOffset = i3 + i + 9 + (iArr.length * 8);
        updateRenumbering();
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitMethodInsn(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r4 = this;
            int r0 = r4.byteCodeOffset
            int r1 = r0 + 3
            r4.byteCodeOffset = r1
            switch(r5) {
                case 182: goto L_0x0023;
                case 183: goto L_0x0023;
                case 184: goto L_0x0023;
                case 185: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x00e1
        L_0x000b:
            int r0 = r0 + 5
            r4.byteCodeOffset = r0
            org.apache.commons.compress.harmony.pack200.CpBands r5 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r5 = r5.getCPIMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            java.util.List r6 = r4.bcIMethodRef
            r6.add(r5)
            org.apache.commons.compress.harmony.pack200.IntList r5 = r4.bcCodes
            r6 = 185(0xb9, float:2.59E-43)
            r5.add(r6)
            goto L_0x00e1
        L_0x0023:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r0 = r0.size()
            r1 = 42
            if (r0 <= 0) goto L_0x0048
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            r3 = 1
            int r2 = r2 - r3
            int r0 = r0.get(r2)
            if (r0 != r1) goto L_0x0048
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            int r2 = r0.size()
            int r2 = r2 - r3
            r0.remove(r2)
            int r5 = r5 + 7
            goto L_0x0049
        L_0x0048:
            r3 = 0
        L_0x0049:
            java.lang.String r0 = r4.currentClass
            boolean r0 = r6.equals(r0)
            java.lang.String r2 = "<init>"
            if (r0 == 0) goto L_0x007a
            int r5 = r5 + 24
            boolean r0 = r7.equals(r2)
            if (r0 == 0) goto L_0x006e
            r0 = 207(0xcf, float:2.9E-43)
            if (r5 != r0) goto L_0x006e
            java.util.List r5 = r4.bcInitRef
            org.apache.commons.compress.harmony.pack200.CpBands r0 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r0.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r5.add(r6)
            r5 = 230(0xe6, float:3.22E-43)
            goto L_0x00dc
        L_0x006e:
            java.util.List r0 = r4.bcThisMethod
            org.apache.commons.compress.harmony.pack200.CpBands r1 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r1.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r0.add(r6)
            goto L_0x00dc
        L_0x007a:
            java.lang.String r0 = r4.superClass
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00a8
            int r5 = r5 + 38
            boolean r0 = r7.equals(r2)
            if (r0 == 0) goto L_0x009c
            r0 = 221(0xdd, float:3.1E-43)
            if (r5 != r0) goto L_0x009c
            java.util.List r5 = r4.bcInitRef
            org.apache.commons.compress.harmony.pack200.CpBands r0 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r0.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r5.add(r6)
            r5 = 231(0xe7, float:3.24E-43)
            goto L_0x00dc
        L_0x009c:
            java.util.List r0 = r4.bcSuperMethod
            org.apache.commons.compress.harmony.pack200.CpBands r1 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r1.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r0.add(r6)
            goto L_0x00dc
        L_0x00a8:
            if (r3 == 0) goto L_0x00b1
            int r5 = r5 + -7
            org.apache.commons.compress.harmony.pack200.IntList r0 = r4.bcCodes
            r0.add(r1)
        L_0x00b1:
            boolean r0 = r7.equals(r2)
            if (r0 == 0) goto L_0x00d1
            r0 = 183(0xb7, float:2.56E-43)
            if (r5 != r0) goto L_0x00d1
            java.lang.String r0 = r4.currentNewClass
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x00d1
            java.util.List r5 = r4.bcInitRef
            org.apache.commons.compress.harmony.pack200.CpBands r0 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r0.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r5.add(r6)
            r5 = 232(0xe8, float:3.25E-43)
            goto L_0x00dc
        L_0x00d1:
            java.util.List r0 = r4.bcMethodRef
            org.apache.commons.compress.harmony.pack200.CpBands r1 = r4.cpBands
            org.apache.commons.compress.harmony.pack200.CPMethodOrField r6 = r1.getCPMethod((java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8)
            r0.add(r6)
        L_0x00dc:
            org.apache.commons.compress.harmony.pack200.IntList r6 = r4.bcCodes
            r6.add(r5)
        L_0x00e1:
            r4.updateRenumbering()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitMethodInsn(int, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        this.byteCodeOffset += 4;
        updateRenumbering();
        this.bcCodes.add(MULTIANEWARRAY);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        this.bcByte.add(i & 255);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label[] labelArr) {
        this.bcCodes.add(TABLESWITCH);
        this.bcLabel.add(label);
        this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        this.bcCaseValue.add(i);
        this.bcCaseCount.add(r3);
        int i3 = 0;
        for (Label add : labelArr) {
            this.bcLabel.add(add);
            this.bcLabelRelativeOffsets.add(this.byteCodeOffset);
        }
        int i4 = this.byteCodeOffset;
        if (i4 % 4 != 0) {
            i3 = 4 - (i4 % 4);
        }
        this.byteCodeOffset = i4 + i3 + 12 + (labelArr.length * 4);
        updateRenumbering();
    }

    public void visitTypeInsn(int i, String str) {
        this.byteCodeOffset += 3;
        updateRenumbering();
        this.bcCodes.add(i);
        this.bcClassRef.add(this.cpBands.getCPClass(str));
        if (i == 187) {
            this.currentNewClass = str;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0031, code lost:
        r2.bcCodes.add((r3 + 17) + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        r2.bcCodes.add((r3 + 14) + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0043, code lost:
        r2.bcCodes.add((r3 + 11) + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x004c, code lost:
        r2.bcCodes.add((r3 + 8) + r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0055, code lost:
        r2.bcCodes.add((r3 + 5) + r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void visitVarInsn(int r3, int r4) {
        /*
            r2 = this;
            r0 = 255(0xff, float:3.57E-43)
            if (r4 <= r0) goto L_0x001c
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 4
            r2.byteCodeOffset = r0
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r1 = 196(0xc4, float:2.75E-43)
            r0.add(r1)
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r0.add(r3)
            org.apache.commons.compress.harmony.pack200.IntList r3 = r2.bcLocal
            r3.add(r4)
            goto L_0x006e
        L_0x001c:
            r0 = 3
            if (r4 > r0) goto L_0x005e
            r0 = 169(0xa9, float:2.37E-43)
            if (r3 != r0) goto L_0x0024
            goto L_0x005e
        L_0x0024:
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 1
            r2.byteCodeOffset = r0
            switch(r3) {
                case 21: goto L_0x0055;
                case 22: goto L_0x004c;
                case 23: goto L_0x0043;
                case 24: goto L_0x003a;
                case 25: goto L_0x0031;
                default: goto L_0x002d;
            }
        L_0x002d:
            switch(r3) {
                case 54: goto L_0x0055;
                case 55: goto L_0x004c;
                case 56: goto L_0x0043;
                case 57: goto L_0x003a;
                case 58: goto L_0x0031;
                default: goto L_0x0030;
            }
        L_0x0030:
            goto L_0x006e
        L_0x0031:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 17
            int r3 = r3 + r4
            r0.add(r3)
            goto L_0x006e
        L_0x003a:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 14
            int r3 = r3 + r4
            r0.add(r3)
            goto L_0x006e
        L_0x0043:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 11
            int r3 = r3 + r4
            r0.add(r3)
            goto L_0x006e
        L_0x004c:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 8
            int r3 = r3 + r4
            r0.add(r3)
            goto L_0x006e
        L_0x0055:
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            int r3 = r3 + 5
            int r3 = r3 + r4
            r0.add(r3)
            goto L_0x006e
        L_0x005e:
            int r0 = r2.byteCodeOffset
            int r0 = r0 + 2
            r2.byteCodeOffset = r0
            org.apache.commons.compress.harmony.pack200.IntList r0 = r2.bcCodes
            r0.add(r3)
            org.apache.commons.compress.harmony.pack200.IntList r3 = r2.bcLocal
            r3.add(r4)
        L_0x006e:
            r2.updateRenumbering()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.harmony.pack200.BcBands.visitVarInsn(int, int):void");
    }
}
