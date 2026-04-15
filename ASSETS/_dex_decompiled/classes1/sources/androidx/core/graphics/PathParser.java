package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import java.util.ArrayList;

public class PathParser {
    private static final String LOGTAG = "PathParser";

    static float[] copyOfRange(float[] fArr, int i, int i2) {
        if (i <= i2) {
            int length = fArr.length;
            if (i < 0 || i > length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i3 = i2 - i;
            int min = Math.min(i3, length - i);
            float[] fArr2 = new float[i3];
            System.arraycopy(fArr, i, fArr2, 0, min);
            return fArr2;
        }
        throw new IllegalArgumentException();
    }

    public static Path createPathFromPathData(String str) {
        Path path = new Path();
        PathDataNode[] createNodesFromPathData = createNodesFromPathData(str);
        if (createNodesFromPathData == null) {
            return null;
        }
        try {
            PathDataNode.nodesToPath(createNodesFromPathData, path);
            return path;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error in parsing " + str, e);
        }
    }

    public static PathDataNode[] createNodesFromPathData(String str) {
        if (str == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int i = 1;
        int i2 = 0;
        while (i < str.length()) {
            int nextStart = nextStart(str, i);
            String trim = str.substring(i2, nextStart).trim();
            if (trim.length() > 0) {
                addNode(arrayList, trim.charAt(0), getFloats(trim));
            }
            i2 = nextStart;
            i = nextStart + 1;
        }
        if (i - i2 == 1 && i2 < str.length()) {
            addNode(arrayList, str.charAt(i2), new float[0]);
        }
        return (PathDataNode[]) arrayList.toArray(new PathDataNode[arrayList.size()]);
    }

    public static PathDataNode[] deepCopyNodes(PathDataNode[] pathDataNodeArr) {
        if (pathDataNodeArr == null) {
            return null;
        }
        PathDataNode[] pathDataNodeArr2 = new PathDataNode[pathDataNodeArr.length];
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            pathDataNodeArr2[i] = new PathDataNode(pathDataNodeArr[i]);
        }
        return pathDataNodeArr2;
    }

    public static boolean canMorph(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr.length != pathDataNodeArr2.length) {
            return false;
        }
        for (int i = 0; i < pathDataNodeArr.length; i++) {
            if (pathDataNodeArr[i].mType != pathDataNodeArr2[i].mType || pathDataNodeArr[i].mParams.length != pathDataNodeArr2[i].mParams.length) {
                return false;
            }
        }
        return true;
    }

    public static void updateNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2) {
        for (int i = 0; i < pathDataNodeArr2.length; i++) {
            pathDataNodeArr[i].mType = pathDataNodeArr2[i].mType;
            for (int i2 = 0; i2 < pathDataNodeArr2[i].mParams.length; i2++) {
                pathDataNodeArr[i].mParams[i2] = pathDataNodeArr2[i].mParams[i2];
            }
        }
    }

    private static int nextStart(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (((charAt - 'A') * (charAt - 'Z') <= 0 || (charAt - 'a') * (charAt - 'z') <= 0) && charAt != 'e' && charAt != 'E') {
                break;
            }
            i++;
        }
        return i;
    }

    private static void addNode(ArrayList<PathDataNode> arrayList, char c, float[] fArr) {
        arrayList.add(new PathDataNode(c, fArr));
    }

    private static class ExtractFloatResult {
        int mEndPosition;
        boolean mEndWithNegOrDot;

        ExtractFloatResult() {
        }
    }

    private static float[] getFloats(String str) {
        if (str.charAt(0) == 'z' || str.charAt(0) == 'Z') {
            return new float[0];
        }
        try {
            float[] fArr = new float[str.length()];
            ExtractFloatResult extractFloatResult = new ExtractFloatResult();
            int length = str.length();
            int i = 1;
            int i2 = 0;
            while (i < length) {
                extract(str, i, extractFloatResult);
                int i3 = extractFloatResult.mEndPosition;
                if (i < i3) {
                    fArr[i2] = Float.parseFloat(str.substring(i, i3));
                    i2++;
                }
                i = extractFloatResult.mEndWithNegOrDot ? i3 : i3 + 1;
            }
            return copyOfRange(fArr, 0, i2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("error in parsing \"" + str + "\"", e);
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0031, code lost:
        r2 = false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003a A[LOOP:0: B:1:0x0007->B:20:0x003a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void extract(java.lang.String r8, int r9, androidx.core.graphics.PathParser.ExtractFloatResult r10) {
        /*
            r0 = 0
            r10.mEndWithNegOrDot = r0
            r1 = r9
            r2 = r0
            r3 = r2
            r4 = r3
        L_0x0007:
            int r5 = r8.length()
            if (r1 >= r5) goto L_0x003d
            char r5 = r8.charAt(r1)
            r6 = 32
            r7 = 1
            if (r5 == r6) goto L_0x0035
            r6 = 69
            if (r5 == r6) goto L_0x0033
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0033
            switch(r5) {
                case 44: goto L_0x0035;
                case 45: goto L_0x002a;
                case 46: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0031
        L_0x0022:
            if (r3 != 0) goto L_0x0027
            r2 = r0
            r3 = r7
            goto L_0x0037
        L_0x0027:
            r10.mEndWithNegOrDot = r7
            goto L_0x0035
        L_0x002a:
            if (r1 == r9) goto L_0x0031
            if (r2 != 0) goto L_0x0031
            r10.mEndWithNegOrDot = r7
            goto L_0x0035
        L_0x0031:
            r2 = r0
            goto L_0x0037
        L_0x0033:
            r2 = r7
            goto L_0x0037
        L_0x0035:
            r2 = r0
            r4 = r7
        L_0x0037:
            if (r4 == 0) goto L_0x003a
            goto L_0x003d
        L_0x003a:
            int r1 = r1 + 1
            goto L_0x0007
        L_0x003d:
            r10.mEndPosition = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.extract(java.lang.String, int, androidx.core.graphics.PathParser$ExtractFloatResult):void");
    }

    public static boolean interpolatePathDataNodes(PathDataNode[] pathDataNodeArr, PathDataNode[] pathDataNodeArr2, PathDataNode[] pathDataNodeArr3, float f) {
        if (pathDataNodeArr == null || pathDataNodeArr2 == null || pathDataNodeArr3 == null) {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes cannot be null");
        } else if (pathDataNodeArr.length == pathDataNodeArr2.length && pathDataNodeArr2.length == pathDataNodeArr3.length) {
            if (!canMorph(pathDataNodeArr2, pathDataNodeArr3)) {
                return false;
            }
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                pathDataNodeArr[i].interpolatePathDataNode(pathDataNodeArr2[i], pathDataNodeArr3[i], f);
            }
            return true;
        } else {
            throw new IllegalArgumentException("The nodes to be interpolated and resulting nodes must have the same length");
        }
    }

    public static class PathDataNode {
        public float[] mParams;
        public char mType;

        PathDataNode(char c, float[] fArr) {
            this.mType = c;
            this.mParams = fArr;
        }

        PathDataNode(PathDataNode pathDataNode) {
            this.mType = pathDataNode.mType;
            float[] fArr = pathDataNode.mParams;
            this.mParams = PathParser.copyOfRange(fArr, 0, fArr.length);
        }

        public static void nodesToPath(PathDataNode[] pathDataNodeArr, Path path) {
            float[] fArr = new float[6];
            char c = 'm';
            for (int i = 0; i < pathDataNodeArr.length; i++) {
                PathDataNode pathDataNode = pathDataNodeArr[i];
                addCommand(path, fArr, c, pathDataNode.mType, pathDataNode.mParams);
                c = pathDataNodeArr[i].mType;
            }
        }

        public void interpolatePathDataNode(PathDataNode pathDataNode, PathDataNode pathDataNode2, float f) {
            this.mType = pathDataNode.mType;
            int i = 0;
            while (true) {
                float[] fArr = pathDataNode.mParams;
                if (i < fArr.length) {
                    this.mParams[i] = (fArr[i] * (1.0f - f)) + (pathDataNode2.mParams[i] * f);
                    i++;
                } else {
                    return;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:0x0237, code lost:
            if (r11[r9 + 4] == r1) goto L_0x023c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:101:0x0239, code lost:
            r9 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:0x023c, code lost:
            r9 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:103:0x023e, code lost:
            r1 = r7;
            drawArc(r26, r1, r2, r3, r4, r5, r6, r0, r8, r9);
            r7 = r1 + r11[r12];
            r8 = r2 + r11[r13];
            r3 = r7;
            r4 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x0251, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r0.lineTo(r7, r11[r14]);
            r2 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:0x0260, code lost:
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:0x0263, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r9 = r14 + 1;
            r3 = r14 + 2;
            r5 = r14 + 3;
            r0.quadTo(r11[r14], r11[r9], r11[r3], r11[r5]);
            r1 = r11[r14];
            r2 = r11[r9];
            r7 = r11[r3];
            r8 = r11[r5];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:0x0287, code lost:
            r3 = r1;
            r4 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:108:0x028b, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r0.lineTo(r11[r14], r8);
            r7 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:109:0x029f, code lost:
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r9 = r14 + 2;
            r7 = r14 + 3;
            r8 = r14 + 4;
            r12 = r14 + 5;
            r0.cubicTo(r11[r14], r11[r14 + 1], r11[r9], r11[r7], r11[r8], r11[r12]);
            r0 = r11[r8];
            r1 = r11[r12];
            r2 = r11[r9];
            r3 = r11[r7];
            r7 = r0;
            r8 = r1;
            r4 = r3;
            r3 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ce, code lost:
            r1 = r7;
            r2 = r8;
            r21 = r12;
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r14 = r9;
            r12 = r14 + 5;
            r3 = r11[r12];
            r13 = r14 + 6;
            r4 = r11[r13];
            r5 = r11[r14];
            r6 = r11[r14 + 1];
            r7 = r11[r14 + 2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:111:0x02f2, code lost:
            if (r11[r14 + 3] == 0.0f) goto L_0x02f8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:112:0x02f4, code lost:
            r0 = 0.0f;
            r8 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:113:0x02f8, code lost:
            r0 = 0.0f;
            r8 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:115:0x0301, code lost:
            if (r11[r14 + 4] == r0) goto L_0x0306;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:116:0x0303, code lost:
            r9 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x0306, code lost:
            r9 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x0308, code lost:
            drawArc(r26, r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r3 = r11[r12];
            r7 = r3;
            r4 = r11[r13];
            r8 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x0315, code lost:
            r9 = r14 + r18;
            r0 = r26;
            r1 = r10;
            r12 = r21;
            r13 = r22;
            r14 = r23;
            r15 = r24;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
            if (r9 >= r11.length) goto L_0x0324;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:120:0x0324, code lost:
            r27[r12] = r7;
            r27[r13] = r8;
            r27[r14] = r3;
            r27[r15] = r4;
            r27[4] = r19;
            r27[5] = r20;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:121:0x033a, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0044, code lost:
            if (r10 == 'A') goto L_0x02ce;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0048, code lost:
            if (r10 == 'C') goto L_0x029f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x004c, code lost:
            if (r10 == 'H') goto L_0x028b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
            if (r10 == 'Q') goto L_0x0263;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0052, code lost:
            r21 = r12;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0056, code lost:
            if (r10 == 'V') goto L_0x0251;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
            if (r10 == 'a') goto L_0x0202;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x005e, code lost:
            if (r10 == 'c') goto L_0x01d3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0060, code lost:
            r22 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0064, code lost:
            if (r10 == 'h') goto L_0x01c4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
            if (r10 == 'q') goto L_0x019f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:2:0x001d, code lost:
            r18 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x006a, code lost:
            r23 = r14;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x006e, code lost:
            if (r10 == 'v') goto L_0x0193;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0072, code lost:
            if (r10 == 'L') goto L_0x0180;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x0076, code lost:
            if (r10 == 'M') goto L_0x0167;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
            r24 = r15;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0080, code lost:
            if (r10 == 'S') goto L_0x0135;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0086, code lost:
            if (r10 == 'T') goto L_0x0114;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x008a, code lost:
            if (r10 == 'l') goto L_0x0104;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:0x008e, code lost:
            if (r10 == 'm') goto L_0x00ef;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0090, code lost:
            if (r10 == 's') goto L_0x00bb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0092, code lost:
            if (r10 == 't') goto L_0x0097;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:46:0x0094, code lost:
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:47:0x0097, code lost:
            if (r1 == 'q') goto L_0x00a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:0x0099, code lost:
            if (r1 == 't') goto L_0x00a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x009b, code lost:
            if (r1 == 'Q') goto L_0x00a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x009d, code lost:
            if (r1 != 'T') goto L_0x00a0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a0, code lost:
            r1 = 0.0f;
            r5 = 0.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a3, code lost:
            r5 = r7 - r3;
            r1 = r8 - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a7, code lost:
            r3 = r9 + 1;
            r0.rQuadTo(r5, r1, r11[r9], r11[r3]);
            r5 = r5 + r7;
            r1 = r1 + r8;
            r7 = r7 + r11[r9];
            r8 = r8 + r11[r3];
            r4 = r1;
            r3 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x00bb, code lost:
            if (r1 == 'c') goto L_0x00cb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x00bd, code lost:
            if (r1 == 's') goto L_0x00cb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c1, code lost:
            if (r1 == 'C') goto L_0x00cb;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c5, code lost:
            if (r1 != 'S') goto L_0x00c8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c8, code lost:
            r1 = 0.0f;
            r2 = 0.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cb, code lost:
            r2 = r8 - r4;
            r1 = r7 - r3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d1, code lost:
            r12 = r9 + 1;
            r13 = r9 + 2;
            r14 = r9 + 3;
            r0.rCubicTo(r1, r2, r11[r9], r11[r12], r11[r13], r11[r14]);
            r1 = r11[r9] + r7;
            r2 = r11[r12] + r8;
            r7 = r7 + r11[r13];
            r3 = r11[r14];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ef, code lost:
            r1 = r11[r9];
            r7 = r7 + r1;
            r2 = r11[r9 + 1];
            r8 = r8 + r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00f7, code lost:
            if (r9 <= 0) goto L_0x00fd;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f9, code lost:
            r0.rLineTo(r1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fd, code lost:
            r0.rMoveTo(r1, r2);
            r19 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x0104, code lost:
            r2 = r9 + 1;
            r0.rLineTo(r11[r9], r11[r2]);
            r7 = r7 + r11[r9];
            r1 = r11[r2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x0112, code lost:
            r8 = r8 + r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x0114, code lost:
            if (r1 == 'q') goto L_0x011c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x0116, code lost:
            if (r1 == 't') goto L_0x011c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x0118, code lost:
            if (r1 == 'Q') goto L_0x011c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:72:0x011a, code lost:
            if (r1 != 'T') goto L_0x0122;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:73:0x011c, code lost:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:74:0x0122, code lost:
            r2 = r9 + 1;
            r0.quadTo(r7, r8, r11[r9], r11[r2]);
            r1 = r11[r9];
            r2 = r11[r2];
            r3 = r7;
            r4 = r8;
            r14 = r9;
            r7 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x0135, code lost:
            if (r1 == 'c') goto L_0x0141;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x0137, code lost:
            if (r1 == 's') goto L_0x0141;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x013b, code lost:
            if (r1 == 'C') goto L_0x0141;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x013f, code lost:
            if (r1 != 'S') goto L_0x0147;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x0141, code lost:
            r7 = (r7 * 2.0f) - r3;
            r8 = (r8 * 2.0f) - r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:0x0147, code lost:
            r7 = r9 + 1;
            r8 = r9 + 2;
            r12 = r9 + 3;
            r0.cubicTo(r7, r8, r11[r9], r11[r7], r11[r8], r11[r12]);
            r1 = r11[r9];
            r2 = r11[r7];
            r7 = r11[r8];
            r8 = r11[r12];
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:0x0167, code lost:
            r24 = r15;
            r1 = r11[r9];
            r2 = r11[r9 + 1];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x016f, code lost:
            if (r9 <= 0) goto L_0x0175;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:85:0x0171, code lost:
            r0.lineTo(r1, r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:86:0x0175, code lost:
            r0.moveTo(r1, r2);
            r7 = r1;
            r19 = r7;
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:87:0x017c, code lost:
            r20 = r8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x0180, code lost:
            r24 = r15;
            r2 = r9 + 1;
            r0.lineTo(r11[r9], r11[r2]);
            r1 = r11[r9];
            r2 = r11[r2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:89:0x018f, code lost:
            r7 = r1;
            r8 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0034, code lost:
            r18 = r7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:90:0x0193, code lost:
            r24 = r15;
            r0.rLineTo(0.0f, r11[r9]);
            r1 = r11[r9];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x019f, code lost:
            r23 = r14;
            r24 = r15;
            r2 = r9 + 1;
            r4 = r9 + 2;
            r6 = r9 + 3;
            r0.rQuadTo(r11[r9], r11[r2], r11[r4], r11[r6]);
            r1 = r11[r9] + r7;
            r2 = r11[r2] + r8;
            r7 = r7 + r11[r4];
            r3 = r11[r6];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x01bf, code lost:
            r8 = r8 + r3;
            r3 = r1;
            r4 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:93:0x01c4, code lost:
            r23 = r14;
            r24 = r15;
            r0.rLineTo(r11[r9], 0.0f);
            r7 = r7 + r11[r9];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:94:0x01d3, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r12 = r9 + 2;
            r13 = r9 + 3;
            r14 = r9 + 4;
            r15 = r9 + 5;
            r0.rCubicTo(r11[r9], r11[r9 + 1], r11[r12], r11[r13], r11[r14], r11[r15]);
            r0 = r11[r12] + r7;
            r1 = r11[r13] + r8;
            r7 = r7 + r11[r14];
            r8 = r8 + r11[r15];
            r3 = r0;
            r4 = r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:95:0x0202, code lost:
            r22 = r13;
            r23 = r14;
            r24 = r15;
            r12 = r9 + 5;
            r3 = r11[r12] + r7;
            r13 = r9 + 6;
            r4 = r11[r13] + r8;
            r5 = r11[r9];
            r6 = r11[r9 + 1];
            r0 = r11[r9 + 2];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x0225, code lost:
            if (r11[r9 + 3] == 0.0f) goto L_0x022c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:97:0x0227, code lost:
            r1 = 0.0f;
            r2 = r8;
            r8 = r22;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:0x022c, code lost:
            r1 = 0.0f;
            r2 = r8;
            r8 = r21;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:99:0x0230, code lost:
            r14 = r9;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0036, code lost:
            r7 = r1;
            r8 = r2;
            r19 = r5;
            r20 = r6;
            r9 = 0;
            r1 = r28;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void addCommand(android.graphics.Path r26, float[] r27, char r28, char r29, float[] r30) {
            /*
                r0 = r26
                r10 = r29
                r11 = r30
                r12 = 0
                r1 = r27[r12]
                r13 = 1
                r2 = r27[r13]
                r14 = 2
                r3 = r27[r14]
                r15 = 3
                r4 = r27[r15]
                r16 = 4
                r5 = r27[r16]
                r17 = 5
                r6 = r27[r17]
                switch(r10) {
                    case 65: goto L_0x0033;
                    case 67: goto L_0x0031;
                    case 72: goto L_0x002e;
                    case 76: goto L_0x001d;
                    case 77: goto L_0x001d;
                    case 81: goto L_0x002b;
                    case 83: goto L_0x002b;
                    case 84: goto L_0x001d;
                    case 86: goto L_0x002e;
                    case 90: goto L_0x0020;
                    case 97: goto L_0x0033;
                    case 99: goto L_0x0031;
                    case 104: goto L_0x002e;
                    case 108: goto L_0x001d;
                    case 109: goto L_0x001d;
                    case 113: goto L_0x002b;
                    case 115: goto L_0x002b;
                    case 116: goto L_0x001d;
                    case 118: goto L_0x002e;
                    case 122: goto L_0x0020;
                    default: goto L_0x001d;
                }
            L_0x001d:
                r18 = r14
                goto L_0x0036
            L_0x0020:
                r0.close()
                r0.moveTo(r5, r6)
                r1 = r5
                r3 = r1
                r2 = r6
                r4 = r2
                goto L_0x001d
            L_0x002b:
                r18 = r16
                goto L_0x0036
            L_0x002e:
                r18 = r13
                goto L_0x0036
            L_0x0031:
                r7 = 6
                goto L_0x0034
            L_0x0033:
                r7 = 7
            L_0x0034:
                r18 = r7
            L_0x0036:
                r7 = r1
                r8 = r2
                r19 = r5
                r20 = r6
                r9 = r12
                r1 = r28
            L_0x003f:
                int r2 = r11.length
                if (r9 >= r2) goto L_0x0324
                r2 = 65
                if (r10 == r2) goto L_0x02ce
                r2 = 67
                if (r10 == r2) goto L_0x029f
                r6 = 72
                if (r10 == r6) goto L_0x028b
                r6 = 81
                if (r10 == r6) goto L_0x0263
                r21 = r12
                r12 = 86
                if (r10 == r12) goto L_0x0251
                r12 = 97
                if (r10 == r12) goto L_0x0202
                r12 = 99
                if (r10 == r12) goto L_0x01d3
                r22 = r13
                r13 = 104(0x68, float:1.46E-43)
                if (r10 == r13) goto L_0x01c4
                r13 = 113(0x71, float:1.58E-43)
                if (r10 == r13) goto L_0x019f
                r23 = r14
                r14 = 118(0x76, float:1.65E-43)
                if (r10 == r14) goto L_0x0193
                r14 = 76
                if (r10 == r14) goto L_0x0180
                r14 = 77
                if (r10 == r14) goto L_0x0167
                r14 = 115(0x73, float:1.61E-43)
                r24 = r15
                r15 = 83
                r25 = 1073741824(0x40000000, float:2.0)
                if (r10 == r15) goto L_0x0135
                r5 = 116(0x74, float:1.63E-43)
                r15 = 84
                if (r10 == r15) goto L_0x0114
                r2 = 108(0x6c, float:1.51E-43)
                if (r10 == r2) goto L_0x0104
                r2 = 109(0x6d, float:1.53E-43)
                if (r10 == r2) goto L_0x00ef
                if (r10 == r14) goto L_0x00bb
                if (r10 == r5) goto L_0x0097
            L_0x0094:
                r14 = r9
                goto L_0x0315
            L_0x0097:
                if (r1 == r13) goto L_0x00a3
                if (r1 == r5) goto L_0x00a3
                if (r1 == r6) goto L_0x00a3
                if (r1 != r15) goto L_0x00a0
                goto L_0x00a3
            L_0x00a0:
                r1 = 0
                r5 = 0
                goto L_0x00a7
            L_0x00a3:
                float r5 = r7 - r3
                float r1 = r8 - r4
            L_0x00a7:
                r2 = r11[r9]
                int r3 = r9 + 1
                r4 = r11[r3]
                r0.rQuadTo(r5, r1, r2, r4)
                float r5 = r5 + r7
                float r1 = r1 + r8
                r2 = r11[r9]
                float r7 = r7 + r2
                r2 = r11[r3]
                float r8 = r8 + r2
                r4 = r1
                r3 = r5
                goto L_0x0094
            L_0x00bb:
                if (r1 == r12) goto L_0x00cb
                if (r1 == r14) goto L_0x00cb
                r2 = 67
                if (r1 == r2) goto L_0x00cb
                r2 = 83
                if (r1 != r2) goto L_0x00c8
                goto L_0x00cb
            L_0x00c8:
                r1 = 0
                r2 = 0
                goto L_0x00d1
            L_0x00cb:
                float r5 = r7 - r3
                float r1 = r8 - r4
                r2 = r1
                r1 = r5
            L_0x00d1:
                r3 = r11[r9]
                int r12 = r9 + 1
                r4 = r11[r12]
                int r13 = r9 + 2
                r5 = r11[r13]
                int r14 = r9 + 3
                r6 = r11[r14]
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r1 = r11[r9]
                float r1 = r1 + r7
                r2 = r11[r12]
                float r2 = r2 + r8
                r3 = r11[r13]
                float r7 = r7 + r3
                r3 = r11[r14]
                goto L_0x01bf
            L_0x00ef:
                r1 = r11[r9]
                float r7 = r7 + r1
                int r2 = r9 + 1
                r2 = r11[r2]
                float r8 = r8 + r2
                if (r9 <= 0) goto L_0x00fd
                r0.rLineTo(r1, r2)
                goto L_0x0094
            L_0x00fd:
                r0.rMoveTo(r1, r2)
                r19 = r7
                goto L_0x017c
            L_0x0104:
                r1 = r11[r9]
                int r2 = r9 + 1
                r5 = r11[r2]
                r0.rLineTo(r1, r5)
                r1 = r11[r9]
                float r7 = r7 + r1
                r1 = r11[r2]
            L_0x0112:
                float r8 = r8 + r1
                goto L_0x0094
            L_0x0114:
                if (r1 == r13) goto L_0x011c
                if (r1 == r5) goto L_0x011c
                if (r1 == r6) goto L_0x011c
                if (r1 != r15) goto L_0x0122
            L_0x011c:
                float r7 = r7 * r25
                float r7 = r7 - r3
                float r8 = r8 * r25
                float r8 = r8 - r4
            L_0x0122:
                r1 = r11[r9]
                int r2 = r9 + 1
                r3 = r11[r2]
                r0.quadTo(r7, r8, r1, r3)
                r1 = r11[r9]
                r2 = r11[r2]
                r3 = r7
                r4 = r8
                r14 = r9
                r7 = r1
                goto L_0x0260
            L_0x0135:
                if (r1 == r12) goto L_0x0141
                if (r1 == r14) goto L_0x0141
                r2 = 67
                if (r1 == r2) goto L_0x0141
                r2 = 83
                if (r1 != r2) goto L_0x0147
            L_0x0141:
                float r7 = r7 * r25
                float r7 = r7 - r3
                float r8 = r8 * r25
                float r8 = r8 - r4
            L_0x0147:
                r1 = r7
                r2 = r8
                r3 = r11[r9]
                int r7 = r9 + 1
                r4 = r11[r7]
                int r8 = r9 + 2
                r5 = r11[r8]
                int r12 = r9 + 3
                r6 = r11[r12]
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r1 = r11[r9]
                r2 = r11[r7]
                r3 = r11[r8]
                r4 = r11[r12]
                r7 = r3
                r8 = r4
                r14 = r9
                goto L_0x0287
            L_0x0167:
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r2 = r11[r2]
                if (r9 <= 0) goto L_0x0175
                r0.lineTo(r1, r2)
                goto L_0x018f
            L_0x0175:
                r0.moveTo(r1, r2)
                r7 = r1
                r19 = r7
                r8 = r2
            L_0x017c:
                r20 = r8
                goto L_0x0094
            L_0x0180:
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r5 = r11[r2]
                r0.lineTo(r1, r5)
                r1 = r11[r9]
                r2 = r11[r2]
            L_0x018f:
                r7 = r1
                r8 = r2
                goto L_0x0094
            L_0x0193:
                r24 = r15
                r1 = r11[r9]
                r2 = 0
                r0.rLineTo(r2, r1)
                r1 = r11[r9]
                goto L_0x0112
            L_0x019f:
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r3 = r11[r2]
                int r4 = r9 + 2
                r5 = r11[r4]
                int r6 = r9 + 3
                r12 = r11[r6]
                r0.rQuadTo(r1, r3, r5, r12)
                r1 = r11[r9]
                float r1 = r1 + r7
                r2 = r11[r2]
                float r2 = r2 + r8
                r3 = r11[r4]
                float r7 = r7 + r3
                r3 = r11[r6]
            L_0x01bf:
                float r8 = r8 + r3
                r3 = r1
                r4 = r2
                goto L_0x0094
            L_0x01c4:
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                r2 = 0
                r0.rLineTo(r1, r2)
                r1 = r11[r9]
                float r7 = r7 + r1
                goto L_0x0094
            L_0x01d3:
                r22 = r13
                r23 = r14
                r24 = r15
                r1 = r11[r9]
                int r2 = r9 + 1
                r2 = r11[r2]
                int r12 = r9 + 2
                r3 = r11[r12]
                int r13 = r9 + 3
                r4 = r11[r13]
                int r14 = r9 + 4
                r5 = r11[r14]
                int r15 = r9 + 5
                r6 = r11[r15]
                r0.rCubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r11[r12]
                float r0 = r0 + r7
                r1 = r11[r13]
                float r1 = r1 + r8
                r2 = r11[r14]
                float r7 = r7 + r2
                r2 = r11[r15]
                float r8 = r8 + r2
                r3 = r0
                r4 = r1
                goto L_0x0094
            L_0x0202:
                r22 = r13
                r23 = r14
                r24 = r15
                int r12 = r9 + 5
                r0 = r11[r12]
                float r3 = r0 + r7
                int r13 = r9 + 6
                r0 = r11[r13]
                float r4 = r0 + r8
                r5 = r11[r9]
                int r0 = r9 + 1
                r6 = r11[r0]
                int r0 = r9 + 2
                r0 = r11[r0]
                int r1 = r9 + 3
                r1 = r11[r1]
                r2 = 0
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 == 0) goto L_0x022c
                r1 = r2
                r2 = r8
                r8 = r22
                goto L_0x0230
            L_0x022c:
                r1 = r2
                r2 = r8
                r8 = r21
            L_0x0230:
                int r14 = r9 + 4
                r14 = r11[r14]
                int r1 = (r14 > r1 ? 1 : (r14 == r1 ? 0 : -1))
                r14 = r9
                if (r1 == 0) goto L_0x023c
                r9 = r22
                goto L_0x023e
            L_0x023c:
                r9 = r21
            L_0x023e:
                r1 = r7
                r7 = r0
                r0 = r26
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r3 = r11[r12]
                float r7 = r1 + r3
                r1 = r11[r13]
                float r8 = r2 + r1
                r3 = r7
                r4 = r8
                goto L_0x0315
            L_0x0251:
                r1 = r7
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r2 = r11[r14]
                r0.lineTo(r1, r2)
                r2 = r11[r14]
            L_0x0260:
                r8 = r2
                goto L_0x0315
            L_0x0263:
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                int r9 = r14 + 1
                r2 = r11[r9]
                int r3 = r14 + 2
                r4 = r11[r3]
                int r5 = r14 + 3
                r6 = r11[r5]
                r0.quadTo(r1, r2, r4, r6)
                r1 = r11[r14]
                r2 = r11[r9]
                r3 = r11[r3]
                r4 = r11[r5]
                r7 = r3
                r8 = r4
            L_0x0287:
                r3 = r1
                r4 = r2
                goto L_0x0315
            L_0x028b:
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                r0.lineTo(r1, r2)
                r1 = r11[r14]
                r7 = r1
                goto L_0x0315
            L_0x029f:
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                r1 = r11[r14]
                int r9 = r14 + 1
                r2 = r11[r9]
                int r9 = r14 + 2
                r3 = r11[r9]
                int r7 = r14 + 3
                r4 = r11[r7]
                int r8 = r14 + 4
                r5 = r11[r8]
                int r12 = r14 + 5
                r6 = r11[r12]
                r0.cubicTo(r1, r2, r3, r4, r5, r6)
                r0 = r11[r8]
                r1 = r11[r12]
                r2 = r11[r9]
                r3 = r11[r7]
                r7 = r0
                r8 = r1
                r4 = r3
                r3 = r2
                goto L_0x0315
            L_0x02ce:
                r1 = r7
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r14 = r9
                int r12 = r14 + 5
                r3 = r11[r12]
                int r13 = r14 + 6
                r4 = r11[r13]
                r5 = r11[r14]
                int r9 = r14 + 1
                r6 = r11[r9]
                int r9 = r14 + 2
                r7 = r11[r9]
                int r9 = r14 + 3
                r0 = r11[r9]
                r8 = 0
                int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
                if (r0 == 0) goto L_0x02f8
                r0 = r8
                r8 = r22
                goto L_0x02fb
            L_0x02f8:
                r0 = r8
                r8 = r21
            L_0x02fb:
                int r9 = r14 + 4
                r9 = r11[r9]
                int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
                if (r0 == 0) goto L_0x0306
                r9 = r22
                goto L_0x0308
            L_0x0306:
                r9 = r21
            L_0x0308:
                r0 = r26
                drawArc(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9)
                r0 = r11[r12]
                r1 = r11[r13]
                r3 = r0
                r7 = r3
                r4 = r1
                r8 = r4
            L_0x0315:
                int r9 = r14 + r18
                r0 = r26
                r1 = r10
                r12 = r21
                r13 = r22
                r14 = r23
                r15 = r24
                goto L_0x003f
            L_0x0324:
                r1 = r7
                r2 = r8
                r21 = r12
                r22 = r13
                r23 = r14
                r24 = r15
                r27[r21] = r1
                r27[r22] = r2
                r27[r23] = r3
                r27[r24] = r4
                r27[r16] = r19
                r27[r17] = r20
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.PathParser.PathDataNode.addCommand(android.graphics.Path, float[], char, char, float[]):void");
        }

        private static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
            double d;
            double d2;
            float f8 = f;
            float f9 = f3;
            float f10 = f7;
            double radians = Math.toRadians((double) f10);
            double cos = Math.cos(radians);
            double sin = Math.sin(radians);
            double d3 = (double) f8;
            float f11 = f2;
            double d4 = (double) f11;
            double d5 = d4;
            double d6 = d3;
            double d7 = (double) f5;
            double d8 = ((d3 * cos) + (d4 * sin)) / d7;
            double d9 = (double) f6;
            double d10 = ((((double) (-f8)) * sin) + (d5 * cos)) / d9;
            float f12 = f4;
            double d11 = (double) f12;
            double d12 = ((((double) f9) * cos) + (d11 * sin)) / d7;
            double d13 = ((((double) (-f9)) * sin) + (d11 * cos)) / d9;
            double d14 = d8 - d12;
            double d15 = d10 - d13;
            double d16 = (d8 + d12) / 2.0d;
            double d17 = (d10 + d13) / 2.0d;
            double d18 = d13;
            double d19 = (d14 * d14) + (d15 * d15);
            if (d19 == 0.0d) {
                Log.w(PathParser.LOGTAG, " Points are coincident");
                return;
            }
            double d20 = (1.0d / d19) - 0.25d;
            if (d20 < 0.0d) {
                Log.w(PathParser.LOGTAG, "Points are too far apart " + d19);
                float sqrt = (float) (Math.sqrt(d19) / 1.99999d);
                drawArc(path, f, f11, f9, f12, f5 * sqrt, sqrt * f6, f10, z, z2);
                return;
            }
            boolean z3 = z2;
            double sqrt2 = Math.sqrt(d20);
            double d21 = d14 * sqrt2;
            double d22 = sqrt2 * d15;
            if (z == z3) {
                d2 = d16 - d22;
                d = d17 + d21;
            } else {
                d2 = d16 + d22;
                d = d17 - d21;
            }
            double atan2 = Math.atan2(d10 - d, d8 - d2);
            double atan22 = Math.atan2(d18 - d, d12 - d2) - atan2;
            int i = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
            if (z3 != (i >= 0)) {
                atan22 = i > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
            }
            double d23 = d2 * d7;
            double d24 = d * d9;
            arcToBezier(path, (d23 * cos) - (d24 * sin), (d23 * sin) + (d24 * cos), d7, d9, d6, d5, radians, atan2, atan22);
        }

        private static void arcToBezier(Path path, double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
            double d10 = d3;
            int ceil = (int) Math.ceil(Math.abs((d9 * 4.0d) / 3.141592653589793d));
            double cos = Math.cos(d7);
            double sin = Math.sin(d7);
            double cos2 = Math.cos(d8);
            double sin2 = Math.sin(d8);
            double d11 = -d10;
            double d12 = d11 * cos;
            double d13 = d4 * sin;
            double d14 = (d12 * sin2) - (d13 * cos2);
            double d15 = d11 * sin;
            double d16 = d4 * cos;
            double d17 = (sin2 * d15) + (cos2 * d16);
            double d18 = d9 / ((double) ceil);
            double d19 = d17;
            double d20 = d14;
            int i = 0;
            double d21 = d5;
            double d22 = d6;
            double d23 = d8;
            while (i < ceil) {
                double d24 = d23 + d18;
                double sin3 = Math.sin(d24);
                double cos3 = Math.cos(d24);
                double d25 = (d + ((d10 * cos) * cos3)) - (d13 * sin3);
                int i2 = i;
                double d26 = d2 + (d3 * sin * cos3) + (d16 * sin3);
                double d27 = (d12 * sin3) - (d13 * cos3);
                double d28 = (sin3 * d15) + (cos3 * d16);
                double d29 = d24 - d23;
                double tan = Math.tan(d29 / 2.0d);
                double sin4 = (Math.sin(d29) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
                double d30 = d21 + (d20 * sin4);
                int i3 = ceil;
                double d31 = d22 + (d19 * sin4);
                double d32 = d15;
                Path path2 = path;
                path2.rLineTo(0.0f, 0.0f);
                Path path3 = path2;
                path3.cubicTo((float) d30, (float) d31, (float) (d25 - (sin4 * d27)), (float) (d26 - (sin4 * d28)), (float) d25, (float) d26);
                sin = sin;
                d18 = d18;
                d21 = d25;
                d15 = d32;
                d23 = d24;
                d19 = d28;
                cos = cos;
                d10 = d3;
                d22 = d26;
                i = i2 + 1;
                ceil = i3;
                d20 = d27;
            }
        }
    }

    private PathParser() {
    }
}
