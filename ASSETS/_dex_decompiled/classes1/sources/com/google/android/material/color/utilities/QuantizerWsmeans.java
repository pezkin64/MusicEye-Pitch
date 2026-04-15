package com.google.android.material.color.utilities;

public final class QuantizerWsmeans {
    private static final int MAX_ITERATIONS = 10;
    private static final double MIN_MOVEMENT_DISTANCE = 3.0d;

    private QuantizerWsmeans() {
    }

    private static final class Distance implements Comparable<Distance> {
        double distance = -1.0d;
        int index = -1;

        Distance() {
        }

        public int compareTo(Distance distance2) {
            return Double.valueOf(this.distance).compareTo(Double.valueOf(distance2.distance));
        }
    }

    /* JADX WARNING: type inference failed for: r16v0 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map<java.lang.Integer, java.lang.Integer> quantize(int[] r25, int[] r26, int r27) {
        /*
            r0 = r25
            r1 = r26
            java.util.Random r2 = new java.util.Random
            r3 = 272008(0x42688, double:1.3439E-318)
            r2.<init>(r3)
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>()
            int r4 = r0.length
            double[][] r4 = new double[r4][]
            int r5 = r0.length
            int[] r5 = new int[r5]
            com.google.android.material.color.utilities.PointProviderLab r6 = new com.google.android.material.color.utilities.PointProviderLab
            r6.<init>()
            r8 = 0
            r9 = 0
        L_0x001e:
            int r10 = r0.length
            r11 = 1
            if (r8 >= r10) goto L_0x0059
            r10 = r0[r8]
            java.lang.Integer r12 = java.lang.Integer.valueOf(r10)
            java.lang.Object r12 = r3.get(r12)
            java.lang.Integer r12 = (java.lang.Integer) r12
            if (r12 != 0) goto L_0x0046
            double[] r12 = r6.fromInt(r10)
            r4[r9] = r12
            r5[r9] = r10
            int r9 = r9 + 1
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r3.put(r10, r11)
            goto L_0x0056
        L_0x0046:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            int r12 = r12.intValue()
            int r12 = r12 + r11
            java.lang.Integer r11 = java.lang.Integer.valueOf(r12)
            r3.put(r10, r11)
        L_0x0056:
            int r8 = r8 + 1
            goto L_0x001e
        L_0x0059:
            int[] r0 = new int[r9]
            r8 = 0
        L_0x005c:
            if (r8 >= r9) goto L_0x0073
            r10 = r5[r8]
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            java.lang.Object r10 = r3.get(r10)
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r0[r8] = r10
            int r8 = r8 + 1
            goto L_0x005c
        L_0x0073:
            r8 = r27
            int r3 = java.lang.Math.min(r8, r9)
            int r5 = r1.length
            if (r5 == 0) goto L_0x0081
            int r5 = r1.length
            int r3 = java.lang.Math.min(r3, r5)
        L_0x0081:
            double[][] r5 = new double[r3][]
            r8 = 0
            r10 = 0
        L_0x0085:
            int r12 = r1.length
            if (r8 >= r12) goto L_0x0095
            r12 = r1[r8]
            double[] r12 = r6.fromInt(r12)
            r5[r8] = r12
            int r10 = r10 + 1
            int r8 = r8 + 1
            goto L_0x0085
        L_0x0095:
            int r1 = r3 - r10
            if (r1 <= 0) goto L_0x009f
            r8 = 0
        L_0x009a:
            if (r8 >= r1) goto L_0x009f
            int r8 = r8 + 1
            goto L_0x009a
        L_0x009f:
            int[] r1 = new int[r9]
            r8 = 0
        L_0x00a2:
            if (r8 >= r9) goto L_0x00ad
            int r10 = r2.nextInt(r3)
            r1[r8] = r10
            int r8 = r8 + 1
            goto L_0x00a2
        L_0x00ad:
            int[][] r2 = new int[r3][]
            r8 = 0
        L_0x00b0:
            if (r8 >= r3) goto L_0x00b9
            int[] r10 = new int[r3]
            r2[r8] = r10
            int r8 = r8 + 1
            goto L_0x00b0
        L_0x00b9:
            com.google.android.material.color.utilities.QuantizerWsmeans$Distance[][] r8 = new com.google.android.material.color.utilities.QuantizerWsmeans.Distance[r3][]
            r10 = 0
        L_0x00bc:
            if (r10 >= r3) goto L_0x00d4
            com.google.android.material.color.utilities.QuantizerWsmeans$Distance[] r12 = new com.google.android.material.color.utilities.QuantizerWsmeans.Distance[r3]
            r8[r10] = r12
            r12 = 0
        L_0x00c3:
            if (r12 >= r3) goto L_0x00d1
            r13 = r8[r10]
            com.google.android.material.color.utilities.QuantizerWsmeans$Distance r14 = new com.google.android.material.color.utilities.QuantizerWsmeans$Distance
            r14.<init>()
            r13[r12] = r14
            int r12 = r12 + 1
            goto L_0x00c3
        L_0x00d1:
            int r10 = r10 + 1
            goto L_0x00bc
        L_0x00d4:
            int[] r10 = new int[r3]
            r12 = 0
        L_0x00d7:
            r13 = 10
            if (r12 >= r13) goto L_0x022e
            r13 = 0
        L_0x00dc:
            if (r13 >= r3) goto L_0x0135
            int r14 = r13 + 1
            r15 = r14
        L_0x00e1:
            if (r15 >= r3) goto L_0x0112
            r16 = r11
            r11 = r5[r13]
            r7 = r5[r15]
            r25 = r0
            r26 = r1
            double r0 = r6.distance(r11, r7)
            r7 = r8[r15]
            r7 = r7[r13]
            r7.distance = r0
            r7 = r8[r15]
            r7 = r7[r13]
            r7.index = r13
            r7 = r8[r13]
            r7 = r7[r15]
            r7.distance = r0
            r0 = r8[r13]
            r0 = r0[r15]
            r0.index = r15
            int r15 = r15 + 1
            r0 = r25
            r1 = r26
            r11 = r16
            goto L_0x00e1
        L_0x0112:
            r25 = r0
            r26 = r1
            r16 = r11
            r0 = r8[r13]
            java.util.Arrays.sort(r0)
            r0 = 0
        L_0x011e:
            if (r0 >= r3) goto L_0x012d
            r1 = r2[r13]
            r7 = r8[r13]
            r7 = r7[r0]
            int r7 = r7.index
            r1[r0] = r7
            int r0 = r0 + 1
            goto L_0x011e
        L_0x012d:
            r0 = r25
            r1 = r26
            r13 = r14
            r11 = r16
            goto L_0x00dc
        L_0x0135:
            r25 = r0
            r26 = r1
            r16 = r11
            r0 = 0
            r1 = 0
        L_0x013d:
            if (r0 >= r9) goto L_0x019e
            r7 = r4[r0]
            r11 = r26[r0]
            r13 = r5[r11]
            double r13 = r6.distance(r7, r13)
            r18 = r0
            r19 = r13
            r0 = -1
            r15 = 0
        L_0x014f:
            if (r15 >= r3) goto L_0x0178
            r21 = r8[r11]
            r22 = r1
            r1 = r21[r15]
            r21 = r2
            double r1 = r1.distance
            r23 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r23 = r23 * r13
            int r1 = (r1 > r23 ? 1 : (r1 == r23 ? 0 : -1))
            if (r1 < 0) goto L_0x0164
            goto L_0x0171
        L_0x0164:
            r1 = r5[r15]
            double r1 = r6.distance(r7, r1)
            int r23 = (r1 > r19 ? 1 : (r1 == r19 ? 0 : -1))
            if (r23 >= 0) goto L_0x0171
            r19 = r1
            r0 = r15
        L_0x0171:
            int r15 = r15 + 1
            r2 = r21
            r1 = r22
            goto L_0x014f
        L_0x0178:
            r22 = r1
            r21 = r2
            r1 = -1
            if (r0 == r1) goto L_0x0197
            double r1 = java.lang.Math.sqrt(r19)
            double r13 = java.lang.Math.sqrt(r13)
            double r1 = r1 - r13
            double r1 = java.lang.Math.abs(r1)
            r13 = 4613937818241073152(0x4008000000000000, double:3.0)
            int r1 = (r1 > r13 ? 1 : (r1 == r13 ? 0 : -1))
            if (r1 <= 0) goto L_0x0197
            int r1 = r22 + 1
            r26[r18] = r0
            goto L_0x0199
        L_0x0197:
            r1 = r22
        L_0x0199:
            int r0 = r18 + 1
            r2 = r21
            goto L_0x013d
        L_0x019e:
            r22 = r1
            r21 = r2
            if (r22 != 0) goto L_0x01a8
            if (r12 == 0) goto L_0x01a8
            goto L_0x022e
        L_0x01a8:
            double[] r0 = new double[r3]
            double[] r1 = new double[r3]
            double[] r2 = new double[r3]
            r7 = 0
            java.util.Arrays.fill(r10, r7)
            r11 = r7
        L_0x01b3:
            if (r11 >= r9) goto L_0x01ea
            r14 = r26[r11]
            r15 = r4[r11]
            r17 = r7
            r7 = r25[r11]
            r18 = r10[r14]
            int r18 = r18 + r7
            r10[r14] = r18
            r18 = r0[r14]
            r22 = r15[r17]
            r20 = r14
            r27 = 2
            double r13 = (double) r7
            double r22 = r22 * r13
            double r18 = r18 + r22
            r0[r20] = r18
            r18 = r1[r20]
            r22 = r15[r16]
            double r22 = r22 * r13
            double r18 = r18 + r22
            r1[r20] = r18
            r18 = r2[r20]
            r22 = r15[r27]
            double r22 = r22 * r13
            double r18 = r18 + r22
            r2[r20] = r18
            int r11 = r11 + 1
            r7 = 0
            goto L_0x01b3
        L_0x01ea:
            r27 = 2
            r7 = 0
        L_0x01ed:
            if (r7 >= r3) goto L_0x0220
            r11 = r10[r7]
            if (r11 != 0) goto L_0x0201
            r11 = 3
            double[] r11 = new double[r11]
            r11 = {0, 0, 0} // fill-array
            r5[r7] = r11
            r15 = r0
            r18 = r1
            r17 = 0
            goto L_0x021a
        L_0x0201:
            r13 = r0[r7]
            r15 = r0
            r18 = r1
            double r0 = (double) r11
            double r13 = r13 / r0
            r19 = r18[r7]
            double r19 = r19 / r0
            r22 = r2[r7]
            double r22 = r22 / r0
            r0 = r5[r7]
            r17 = 0
            r0[r17] = r13
            r0[r16] = r19
            r0[r27] = r22
        L_0x021a:
            int r7 = r7 + 1
            r0 = r15
            r1 = r18
            goto L_0x01ed
        L_0x0220:
            r17 = 0
            int r12 = r12 + 1
            r0 = r25
            r1 = r26
            r11 = r16
            r2 = r21
            goto L_0x00d7
        L_0x022e:
            r17 = 0
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r7 = r17
        L_0x0237:
            if (r7 >= r3) goto L_0x025d
            r1 = r10[r7]
            if (r1 != 0) goto L_0x023e
            goto L_0x025a
        L_0x023e:
            r2 = r5[r7]
            int r2 = r6.toInt(r2)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r2)
            boolean r4 = r0.containsKey(r4)
            if (r4 == 0) goto L_0x024f
            goto L_0x025a
        L_0x024f:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.put(r2, r1)
        L_0x025a:
            int r7 = r7 + 1
            goto L_0x0237
        L_0x025d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.color.utilities.QuantizerWsmeans.quantize(int[], int[], int):java.util.Map");
    }
}
