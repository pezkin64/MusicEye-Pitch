package org.apache.commons.compress.harmony.unpack200.bytecode.forms;

import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.R;
import java.util.HashMap;
import java.util.Map;
import jp.kshoji.javax.sound.midi.ShortMessage;
import kotlinx.coroutines.scheduling.WorkQueueKt;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.commons.compress.harmony.unpack200.bytecode.ByteCode;
import org.apache.commons.compress.harmony.unpack200.bytecode.CodeAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.OperandManager;

public abstract class ByteCodeForm {
    protected static final boolean WIDENED = true;
    protected static final ByteCodeForm[] byteCodeArray;
    protected static final Map byteCodesByName = new HashMap(256);
    private int firstOperandIndex;
    private final String name;
    private final int opcode;
    private int operandLength;
    private final int[] rewrite;

    public void fixUpByteCodeTargets(ByteCode byteCode, CodeAttribute codeAttribute) {
    }

    public boolean hasNoOperand() {
        return false;
    }

    public boolean nestedMustStartClassPool() {
        return false;
    }

    public abstract void setByteCodeOperands(ByteCode byteCode, OperandManager operandManager, int i);

    static {
        ByteCodeForm[] byteCodeFormArr = new ByteCodeForm[256];
        byteCodeArray = byteCodeFormArr;
        byteCodeFormArr[0] = new NoArgumentForm(0, "nop");
        byteCodeFormArr[1] = new NoArgumentForm(1, "aconst_null");
        byteCodeFormArr[2] = new NoArgumentForm(2, "iconst_m1");
        byteCodeFormArr[3] = new NoArgumentForm(3, "iconst_0");
        byteCodeFormArr[4] = new NoArgumentForm(4, "iconst_1");
        byteCodeFormArr[5] = new NoArgumentForm(5, "iconst_2");
        byteCodeFormArr[6] = new NoArgumentForm(6, "iconst_3");
        byteCodeFormArr[7] = new NoArgumentForm(7, "iconst_4");
        byteCodeFormArr[8] = new NoArgumentForm(8, "iconst_5");
        byteCodeFormArr[9] = new NoArgumentForm(9, "lconst_0");
        byteCodeFormArr[10] = new NoArgumentForm(10, "lconst_1");
        byteCodeFormArr[11] = new NoArgumentForm(11, "fconst_0");
        byteCodeFormArr[12] = new NoArgumentForm(12, "fconst_1");
        byteCodeFormArr[13] = new NoArgumentForm(13, "fconst_2");
        byteCodeFormArr[14] = new NoArgumentForm(14, "dconst_0");
        byteCodeFormArr[15] = new NoArgumentForm(15, "dconst_1");
        byteCodeFormArr[16] = new ByteForm(16, "bipush", new int[]{16, -1});
        byteCodeFormArr[17] = new ShortForm(17, "sipush", new int[]{17, -1, -1});
        byteCodeFormArr[18] = new StringRefForm(18, "ldc", new int[]{18, -1});
        byteCodeFormArr[19] = new StringRefForm(19, "ldc_w", new int[]{19, -1, -1}, true);
        byteCodeFormArr[20] = new LongForm(20, "ldc2_w", new int[]{20, -1, -1});
        byteCodeFormArr[21] = new LocalForm(21, "iload", new int[]{21, -1});
        byteCodeFormArr[22] = new LocalForm(22, "lload", new int[]{22, -1});
        byteCodeFormArr[23] = new LocalForm(23, "fload", new int[]{23, -1});
        byteCodeFormArr[24] = new LocalForm(24, "dload", new int[]{24, -1});
        byteCodeFormArr[25] = new LocalForm(25, "aload", new int[]{25, -1});
        byteCodeFormArr[26] = new NoArgumentForm(26, "iload_0");
        byteCodeFormArr[27] = new NoArgumentForm(27, "iload_1");
        byteCodeFormArr[28] = new NoArgumentForm(28, "iload_2");
        byteCodeFormArr[29] = new NoArgumentForm(29, "iload_3");
        byteCodeFormArr[30] = new NoArgumentForm(30, "lload_0");
        byteCodeFormArr[31] = new NoArgumentForm(31, "lload_1");
        byteCodeFormArr[32] = new NoArgumentForm(32, "lload_2");
        byteCodeFormArr[33] = new NoArgumentForm(33, "lload_3");
        byteCodeFormArr[34] = new NoArgumentForm(34, "fload_0");
        byteCodeFormArr[35] = new NoArgumentForm(35, "fload_1");
        byteCodeFormArr[36] = new NoArgumentForm(36, "fload_2");
        byteCodeFormArr[37] = new NoArgumentForm(37, "fload_3");
        byteCodeFormArr[38] = new NoArgumentForm(38, "dload_0");
        byteCodeFormArr[39] = new NoArgumentForm(39, "dload_1");
        byteCodeFormArr[40] = new NoArgumentForm(40, "dload_2");
        byteCodeFormArr[41] = new NoArgumentForm(41, "dload_3");
        byteCodeFormArr[42] = new NoArgumentForm(42, "aload_0");
        byteCodeFormArr[43] = new NoArgumentForm(43, "aload_1");
        byteCodeFormArr[44] = new NoArgumentForm(44, "aload_2");
        byteCodeFormArr[45] = new NoArgumentForm(45, "aload_3");
        byteCodeFormArr[46] = new NoArgumentForm(46, "iaload");
        byteCodeFormArr[47] = new NoArgumentForm(47, "laload");
        byteCodeFormArr[48] = new NoArgumentForm(48, "faload");
        byteCodeFormArr[49] = new NoArgumentForm(49, "daload");
        byteCodeFormArr[50] = new NoArgumentForm(50, "aaload");
        byteCodeFormArr[51] = new NoArgumentForm(51, "baload");
        byteCodeFormArr[52] = new NoArgumentForm(52, "caload");
        byteCodeFormArr[53] = new NoArgumentForm(53, "saload");
        byteCodeFormArr[54] = new LocalForm(54, "istore", new int[]{54, -1});
        byteCodeFormArr[55] = new LocalForm(55, "lstore", new int[]{55, -1});
        byteCodeFormArr[56] = new LocalForm(56, "fstore", new int[]{56, -1});
        byteCodeFormArr[57] = new LocalForm(57, "dstore", new int[]{57, -1});
        byteCodeFormArr[58] = new LocalForm(58, "astore", new int[]{58, -1});
        byteCodeFormArr[59] = new NoArgumentForm(59, "istore_0");
        byteCodeFormArr[60] = new NoArgumentForm(60, "istore_1");
        byteCodeFormArr[61] = new NoArgumentForm(61, "istore_2");
        byteCodeFormArr[62] = new NoArgumentForm(62, "istore_3");
        byteCodeFormArr[63] = new NoArgumentForm(63, "lstore_0");
        byteCodeFormArr[64] = new NoArgumentForm(64, "lstore_1");
        byteCodeFormArr[65] = new NoArgumentForm(65, "lstore_2");
        byteCodeFormArr[66] = new NoArgumentForm(66, "lstore_3");
        byteCodeFormArr[67] = new NoArgumentForm(67, "fstore_0");
        byteCodeFormArr[68] = new NoArgumentForm(68, "fstore_1");
        byteCodeFormArr[69] = new NoArgumentForm(69, "fstore_2");
        byteCodeFormArr[70] = new NoArgumentForm(70, "fstore_3");
        byteCodeFormArr[71] = new NoArgumentForm(71, "dstore_0");
        byteCodeFormArr[72] = new NoArgumentForm(72, "dstore_1");
        byteCodeFormArr[73] = new NoArgumentForm(73, "dstore_2");
        byteCodeFormArr[74] = new NoArgumentForm(74, "dstore_3");
        byteCodeFormArr[75] = new NoArgumentForm(75, "astore_0");
        byteCodeFormArr[76] = new NoArgumentForm(76, "astore_1");
        byteCodeFormArr[77] = new NoArgumentForm(77, "astore_2");
        byteCodeFormArr[78] = new NoArgumentForm(78, "astore_3");
        byteCodeFormArr[79] = new NoArgumentForm(79, "iastore");
        byteCodeFormArr[80] = new NoArgumentForm(80, "lastore");
        byteCodeFormArr[81] = new NoArgumentForm(81, "fastore");
        byteCodeFormArr[82] = new NoArgumentForm(82, "dastore");
        byteCodeFormArr[83] = new NoArgumentForm(83, "aastore");
        byteCodeFormArr[84] = new NoArgumentForm(84, "bastore");
        byteCodeFormArr[85] = new NoArgumentForm(85, "castore");
        byteCodeFormArr[86] = new NoArgumentForm(86, "sastore");
        byteCodeFormArr[87] = new NoArgumentForm(87, "pop");
        byteCodeFormArr[88] = new NoArgumentForm(88, "pop2");
        byteCodeFormArr[89] = new NoArgumentForm(89, "dup");
        byteCodeFormArr[90] = new NoArgumentForm(90, "dup_x1");
        byteCodeFormArr[91] = new NoArgumentForm(91, "dup_x2");
        byteCodeFormArr[92] = new NoArgumentForm(92, "dup2");
        byteCodeFormArr[93] = new NoArgumentForm(93, "dup2_x1");
        byteCodeFormArr[94] = new NoArgumentForm(94, "dup2_x2");
        byteCodeFormArr[95] = new NoArgumentForm(95, "swap");
        byteCodeFormArr[96] = new NoArgumentForm(96, "iadd");
        byteCodeFormArr[97] = new NoArgumentForm(97, "ladd");
        byteCodeFormArr[98] = new NoArgumentForm(98, "fadd");
        byteCodeFormArr[99] = new NoArgumentForm(99, "dadd");
        byteCodeFormArr[100] = new NoArgumentForm(100, "isub");
        byteCodeFormArr[101] = new NoArgumentForm(101, "lsub");
        byteCodeFormArr[102] = new NoArgumentForm(102, "fsub");
        byteCodeFormArr[103] = new NoArgumentForm(103, "dsub");
        byteCodeFormArr[104] = new NoArgumentForm(104, "imul");
        byteCodeFormArr[105] = new NoArgumentForm(105, "lmul");
        byteCodeFormArr[106] = new NoArgumentForm(106, "fmul");
        byteCodeFormArr[107] = new NoArgumentForm(107, "dmul");
        byteCodeFormArr[108] = new NoArgumentForm(108, "idiv");
        byteCodeFormArr[109] = new NoArgumentForm(109, "ldiv");
        byteCodeFormArr[110] = new NoArgumentForm(110, "fdiv");
        byteCodeFormArr[111] = new NoArgumentForm(111, "ddiv");
        byteCodeFormArr[112] = new NoArgumentForm(112, "irem");
        byteCodeFormArr[113] = new NoArgumentForm(113, "lrem");
        byteCodeFormArr[114] = new NoArgumentForm(114, "frem");
        byteCodeFormArr[115] = new NoArgumentForm(115, "drem");
        byteCodeFormArr[116] = new NoArgumentForm(116, BuildConfig.FLAVOR);
        byteCodeFormArr[117] = new NoArgumentForm(117, "lneg");
        byteCodeFormArr[118] = new NoArgumentForm(118, "fneg");
        byteCodeFormArr[119] = new NoArgumentForm(119, "dneg");
        byteCodeFormArr[120] = new NoArgumentForm(120, "ishl");
        byteCodeFormArr[121] = new NoArgumentForm(121, "lshl");
        byteCodeFormArr[122] = new NoArgumentForm(122, "ishr");
        byteCodeFormArr[123] = new NoArgumentForm(123, "lshr");
        byteCodeFormArr[124] = new NoArgumentForm(R.styleable.AppCompatTheme_windowMinWidthMajor, "iushr");
        byteCodeFormArr[125] = new NoArgumentForm(R.styleable.AppCompatTheme_windowMinWidthMinor, "lushr");
        byteCodeFormArr[126] = new NoArgumentForm(R.styleable.AppCompatTheme_windowNoTitle, "iand");
        byteCodeFormArr[127] = new NoArgumentForm(WorkQueueKt.MASK, "land");
        byteCodeFormArr[128] = new NoArgumentForm(128, "ior");
        byteCodeFormArr[129] = new NoArgumentForm(129, "lor");
        byteCodeFormArr[130] = new NoArgumentForm(130, "ixor");
        byteCodeFormArr[131] = new NoArgumentForm(TarConstants.PREFIXLEN_XSTAR, "lxor");
        byteCodeFormArr[132] = new IincForm(132, "iinc", new int[]{132, -1, -1});
        byteCodeFormArr[133] = new NoArgumentForm(133, "i2l");
        byteCodeFormArr[134] = new NoArgumentForm(134, "i2f");
        byteCodeFormArr[135] = new NoArgumentForm(135, "i2d");
        byteCodeFormArr[136] = new NoArgumentForm(136, "l2i");
        byteCodeFormArr[137] = new NoArgumentForm(137, "l2f");
        byteCodeFormArr[138] = new NoArgumentForm(138, "l2d");
        byteCodeFormArr[139] = new NoArgumentForm(139, "f2i");
        byteCodeFormArr[140] = new NoArgumentForm(140, "f2l");
        byteCodeFormArr[141] = new NoArgumentForm(141, "f2d");
        byteCodeFormArr[142] = new NoArgumentForm(142, "d2i");
        byteCodeFormArr[143] = new NoArgumentForm(143, "d2l");
        byteCodeFormArr[144] = new NoArgumentForm(ShortMessage.NOTE_ON, "d2f");
        byteCodeFormArr[145] = new NoArgumentForm(145, "i2b");
        byteCodeFormArr[146] = new NoArgumentForm(146, "i2c");
        byteCodeFormArr[147] = new NoArgumentForm(147, "i2s");
        byteCodeFormArr[148] = new NoArgumentForm(TarConstants.CHKSUM_OFFSET, "lcmp");
        byteCodeFormArr[149] = new NoArgumentForm(149, "fcmpl");
        byteCodeFormArr[150] = new NoArgumentForm(150, "fcmpg");
        byteCodeFormArr[151] = new NoArgumentForm(151, "dcmpl");
        byteCodeFormArr[152] = new NoArgumentForm(152, "dcmpg");
        byteCodeFormArr[153] = new LabelForm(153, "ifeq", new int[]{153, -1, -1});
        byteCodeFormArr[154] = new LabelForm(154, "ifne", new int[]{154, -1, -1});
        byteCodeFormArr[155] = new LabelForm(TarConstants.PREFIXLEN, "iflt", new int[]{TarConstants.PREFIXLEN, -1, -1});
        byteCodeFormArr[156] = new LabelForm(156, "ifge", new int[]{156, -1, -1});
        byteCodeFormArr[157] = new LabelForm(157, "ifgt", new int[]{157, -1, -1});
        byteCodeFormArr[158] = new LabelForm(158, "ifle", new int[]{158, -1, -1});
        byteCodeFormArr[159] = new LabelForm(159, "if_icmpeq", new int[]{159, -1, -1});
        byteCodeFormArr[160] = new LabelForm(ShortMessage.POLY_PRESSURE, "if_icmpne", new int[]{ShortMessage.POLY_PRESSURE, -1, -1});
        byteCodeFormArr[161] = new LabelForm(161, "if_icmplt", new int[]{161, -1, -1});
        byteCodeFormArr[162] = new LabelForm(162, "if_icmpge", new int[]{162, -1, -1});
        byteCodeFormArr[163] = new LabelForm(163, "if_icmpgt", new int[]{163, -1, -1});
        byteCodeFormArr[164] = new LabelForm(164, "if_icmple", new int[]{164, -1, -1});
        byteCodeFormArr[165] = new LabelForm(165, "if_acmpeq", new int[]{165, -1, -1});
        byteCodeFormArr[166] = new LabelForm(166, "if_acmpne", new int[]{166, -1, -1});
        byteCodeFormArr[167] = new LabelForm(167, "goto", new int[]{167, -1, -1});
        byteCodeFormArr[168] = new LabelForm(168, "jsr", new int[]{168, -1, -1});
        byteCodeFormArr[169] = new LocalForm(169, "ret", new int[]{169, -1});
        byteCodeFormArr[170] = new TableSwitchForm(170, "tableswitch");
        byteCodeFormArr[171] = new LookupSwitchForm(171, "lookupswitch");
        byteCodeFormArr[172] = new NoArgumentForm(172, "ireturn");
        byteCodeFormArr[173] = new NoArgumentForm(173, "lreturn");
        byteCodeFormArr[174] = new NoArgumentForm(174, "freturn");
        byteCodeFormArr[175] = new NoArgumentForm(175, "dreturn");
        byteCodeFormArr[176] = new NoArgumentForm(ShortMessage.CONTROL_CHANGE, "areturn");
        byteCodeFormArr[177] = new NoArgumentForm(177, "return");
        byteCodeFormArr[178] = new FieldRefForm(178, "getstatic", new int[]{178, -1, -1});
        byteCodeFormArr[179] = new FieldRefForm(179, "putstatic", new int[]{179, -1, -1});
        byteCodeFormArr[180] = new FieldRefForm(180, "getfield", new int[]{180, -1, -1});
        byteCodeFormArr[181] = new FieldRefForm(181, "putfield", new int[]{181, -1, -1});
        byteCodeFormArr[182] = new MethodRefForm(182, "invokevirtual", new int[]{182, -1, -1});
        byteCodeFormArr[183] = new MethodRefForm(183, "invokespecial", new int[]{183, -1, -1});
        byteCodeFormArr[184] = new MethodRefForm(184, "invokestatic", new int[]{184, -1, -1});
        byteCodeFormArr[185] = new IMethodRefForm(185, "invokeinterface", new int[]{185, -1, -1, -1, 0});
        byteCodeFormArr[186] = new NoArgumentForm(186, "xxxunusedxxx");
        byteCodeFormArr[187] = new NewClassRefForm(187, "new", new int[]{187, -1, -1});
        byteCodeFormArr[188] = new ByteForm(188, "newarray", new int[]{188, -1});
        byteCodeFormArr[189] = new ClassRefForm(189, "anewarray", new int[]{189, -1, -1});
        byteCodeFormArr[190] = new NoArgumentForm(190, "arraylength");
        byteCodeFormArr[191] = new NoArgumentForm(191, "athrow");
        byteCodeFormArr[192] = new ClassRefForm(ShortMessage.PROGRAM_CHANGE, "checkcast", new int[]{ShortMessage.PROGRAM_CHANGE, -1, -1});
        byteCodeFormArr[193] = new ClassRefForm(193, "instanceof", new int[]{193, -1, -1});
        byteCodeFormArr[194] = new NoArgumentForm(194, "monitorenter");
        byteCodeFormArr[195] = new NoArgumentForm(195, "monitorexit");
        byteCodeFormArr[196] = new WideForm(196, "wide");
        byteCodeFormArr[197] = new MultiANewArrayForm(197, "multianewarray", new int[]{197, -1, -1, -1});
        byteCodeFormArr[198] = new LabelForm(198, "ifnull", new int[]{198, -1, -1});
        byteCodeFormArr[199] = new LabelForm(199, "ifnonnull", new int[]{199, -1, -1});
        byteCodeFormArr[200] = new LabelForm(200, "goto_w", new int[]{200, -1, -1, -1, -1}, true);
        byteCodeFormArr[201] = new LabelForm(201, "jsr_w", new int[]{201, -1, -1, -1, -1}, true);
        byteCodeFormArr[202] = new ThisFieldRefForm(202, "getstatic_this", new int[]{178, -1, -1});
        byteCodeFormArr[203] = new ThisFieldRefForm(203, "putstatic_this", new int[]{179, -1, -1});
        byteCodeFormArr[204] = new ThisFieldRefForm(204, "getfield_this", new int[]{180, -1, -1});
        byteCodeFormArr[205] = new ThisFieldRefForm(205, "putfield_this", new int[]{181, -1, -1});
        byteCodeFormArr[206] = new ThisMethodRefForm(206, "invokevirtual_this", new int[]{182, -1, -1});
        byteCodeFormArr[207] = new ThisMethodRefForm(207, "invokespecial_this", new int[]{183, -1, -1});
        byteCodeFormArr[208] = new ThisMethodRefForm(ShortMessage.CHANNEL_PRESSURE, "invokestatic_this", new int[]{184, -1, -1});
        byteCodeFormArr[209] = new ThisFieldRefForm(209, "aload_0_getstatic_this", new int[]{42, 178, -1, -1});
        byteCodeFormArr[210] = new ThisFieldRefForm(210, "aload_0_putstatic_this", new int[]{42, 179, -1, -1});
        byteCodeFormArr[211] = new ThisFieldRefForm(211, "aload_0_getfield_this", new int[]{42, 180, -1, -1});
        byteCodeFormArr[212] = new ThisFieldRefForm(212, "aload_0_putfield_this", new int[]{42, 181, -1, -1});
        byteCodeFormArr[213] = new ThisMethodRefForm(213, "aload_0_invokevirtual_this", new int[]{42, 182, -1, -1});
        byteCodeFormArr[214] = new ThisMethodRefForm(214, "aload_0_invokespecial_this", new int[]{42, 183, -1, -1});
        byteCodeFormArr[215] = new ThisMethodRefForm(215, "aload_0_invokestatic_this", new int[]{42, 184, -1, -1});
        byteCodeFormArr[216] = new SuperFieldRefForm(216, "getstatic_super", new int[]{178, -1, -1});
        byteCodeFormArr[217] = new SuperFieldRefForm(217, "putstatic_super", new int[]{179, -1, -1});
        byteCodeFormArr[218] = new SuperFieldRefForm(218, "getfield_super", new int[]{180, -1, -1});
        byteCodeFormArr[219] = new SuperFieldRefForm(219, "putfield_super", new int[]{181, -1, -1});
        byteCodeFormArr[220] = new SuperMethodRefForm(220, "invokevirtual_super", new int[]{182, -1, -1});
        byteCodeFormArr[221] = new SuperMethodRefForm(221, "invokespecial_super", new int[]{183, -1, -1});
        byteCodeFormArr[222] = new SuperMethodRefForm(222, "invokestatic_super", new int[]{184, -1, -1});
        byteCodeFormArr[223] = new SuperFieldRefForm(223, "aload_0_getstatic_super", new int[]{42, 178, -1, -1});
        byteCodeFormArr[224] = new SuperFieldRefForm(ShortMessage.PITCH_BEND, "aload_0_putstatic_super", new int[]{42, 179, -1, -1});
        byteCodeFormArr[225] = new SuperFieldRefForm(225, "aload_0_getfield_super", new int[]{42, 180, -1, -1});
        byteCodeFormArr[226] = new SuperFieldRefForm(226, "aload_0_putfield_super", new int[]{42, 181, -1, -1});
        byteCodeFormArr[227] = new SuperMethodRefForm(227, "aload_0_invokevirtual_super", new int[]{42, 182, -1, -1});
        byteCodeFormArr[228] = new SuperMethodRefForm(228, "aload_0_invokespecial_super", new int[]{42, 183, -1, -1});
        byteCodeFormArr[229] = new SuperMethodRefForm(229, "aload_0_invokestatic_super", new int[]{42, 184, -1, -1});
        byteCodeFormArr[230] = new ThisInitMethodRefForm(230, "invokespecial_this_init", new int[]{183, -1, -1});
        byteCodeFormArr[231] = new SuperInitMethodRefForm(231, "invokespecial_super_init", new int[]{183, -1, -1});
        byteCodeFormArr[232] = new NewInitMethodRefForm(232, "invokespecial_new_init", new int[]{183, -1, -1});
        byteCodeFormArr[233] = new NarrowClassRefForm(233, "cldc", new int[]{18, -1});
        byteCodeFormArr[234] = new IntRefForm(234, "ildc", new int[]{18, -1});
        byteCodeFormArr[235] = new FloatRefForm(235, "fldc", new int[]{18, -1});
        byteCodeFormArr[236] = new NarrowClassRefForm(236, "cldc_w", new int[]{19, -1, -1}, true);
        byteCodeFormArr[237] = new IntRefForm(237, "ildc_w", new int[]{19, -1, -1}, true);
        byteCodeFormArr[238] = new FloatRefForm(238, "fldc_w", new int[]{19, -1, -1}, true);
        byteCodeFormArr[239] = new DoubleForm(239, "dldc2_w", new int[]{20, -1, -1});
        byteCodeFormArr[254] = new NoArgumentForm(ShortMessage.ACTIVE_SENSING, "impdep1");
        byteCodeFormArr[255] = new NoArgumentForm(255, "impdep2");
        int i = 0;
        while (true) {
            ByteCodeForm[] byteCodeFormArr2 = byteCodeArray;
            if (i < byteCodeFormArr2.length) {
                ByteCodeForm byteCodeForm = byteCodeFormArr2[i];
                if (byteCodeForm != null) {
                    byteCodesByName.put(byteCodeForm.getName(), byteCodeForm);
                }
                i++;
            } else {
                return;
            }
        }
    }

    public ByteCodeForm(int i, String str) {
        this(i, str, new int[]{i});
    }

    public ByteCodeForm(int i, String str, int[] iArr) {
        this.opcode = i;
        this.name = str;
        this.rewrite = iArr;
        calculateOperandPosition();
    }

    /* access modifiers changed from: protected */
    public void calculateOperandPosition() {
        int i = -1;
        this.firstOperandIndex = -1;
        this.operandLength = -1;
        int i2 = 0;
        while (true) {
            int[] iArr = this.rewrite;
            if (i2 >= iArr.length) {
                break;
            } else if (iArr[i2] < 0) {
                this.firstOperandIndex = i2;
                i2 = iArr.length;
            } else {
                i2++;
            }
        }
        int i3 = this.firstOperandIndex;
        if (i3 != -1) {
            while (true) {
                int[] iArr2 = this.rewrite;
                if (i3 >= iArr2.length) {
                    break;
                }
                if (iArr2[i3] < 0) {
                    i = i3;
                }
                i3++;
            }
            int i4 = i - this.firstOperandIndex;
            if (i4 >= 0) {
                this.operandLength = i4 + 1;
                return;
            }
            throw new Error("Logic error: not finding rewrite operands correctly");
        }
    }

    public static ByteCodeForm get(int i) {
        return byteCodeArray[i];
    }

    public String toString() {
        return getClass().getName() + "(" + getName() + ")";
    }

    public int getOpcode() {
        return this.opcode;
    }

    public String getName() {
        return this.name;
    }

    public int[] getRewrite() {
        return this.rewrite;
    }

    public int[] getRewriteCopy() {
        int[] iArr = this.rewrite;
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    public int firstOperandIndex() {
        return this.firstOperandIndex;
    }

    public int operandLength() {
        return this.operandLength;
    }

    public boolean hasMultipleByteCodes() {
        int[] iArr = this.rewrite;
        return iArr.length > 1 && iArr[0] == 42 && iArr[1] > 0;
    }
}
