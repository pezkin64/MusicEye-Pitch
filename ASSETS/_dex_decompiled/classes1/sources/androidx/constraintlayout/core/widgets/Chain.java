package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        int i2;
        ChainHead[] chainHeadArr;
        int i3;
        if (i == 0) {
            i3 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i2 = 0;
        } else {
            i3 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = 2;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            ChainHead chainHead = chainHeadArr[i4];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i2, chainHead);
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v0, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v1, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v11, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v2, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v14, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: androidx.constraintlayout.core.widgets.ConstraintWidget} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v103, resolved type: androidx.constraintlayout.core.SolverVariable} */
    /* JADX WARNING: type inference failed for: r5v13, types: [androidx.constraintlayout.core.SolverVariable] */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r3.mHorizontalChainStyle == 2) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004a, code lost:
        if (r3.mVerticalChainStyle == 2) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004e, code lost:
        r6 = false;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x036b  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x03c0  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x03c2  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0502  */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0507  */
    /* JADX WARNING: Removed duplicated region for block: B:290:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x0512  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0516  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0528  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static void applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r36, androidx.constraintlayout.core.LinearSystem r37, int r38, int r39, androidx.constraintlayout.core.widgets.ChainHead r40) {
        /*
            r0 = r36
            r1 = r37
            r9 = r38
            r2 = r40
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r2.mFirst
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r2.mLast
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r2.mFirstVisibleWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r13 = r2.mLastVisibleWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r2.mHead
            float r4 = r2.mTotalWeight
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r2.mFirstMatchConstraintWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r2.mLastMatchConstraintWidget
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r5 = r5[r9]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            r7 = 1
            if (r5 != r6) goto L_0x0023
            r5 = r7
            goto L_0x0024
        L_0x0023:
            r5 = 0
        L_0x0024:
            r6 = 2
            if (r9 != 0) goto L_0x003a
            int r14 = r3.mHorizontalChainStyle
            if (r14 != 0) goto L_0x002d
            r14 = r7
            goto L_0x002e
        L_0x002d:
            r14 = 0
        L_0x002e:
            int r15 = r3.mHorizontalChainStyle
            if (r15 != r7) goto L_0x0034
            r15 = r7
            goto L_0x0035
        L_0x0034:
            r15 = 0
        L_0x0035:
            int r8 = r3.mHorizontalChainStyle
            if (r8 != r6) goto L_0x004e
            goto L_0x004c
        L_0x003a:
            int r8 = r3.mVerticalChainStyle
            if (r8 != 0) goto L_0x0040
            r14 = r7
            goto L_0x0041
        L_0x0040:
            r14 = 0
        L_0x0041:
            int r8 = r3.mVerticalChainStyle
            if (r8 != r7) goto L_0x0047
            r15 = r7
            goto L_0x0048
        L_0x0047:
            r15 = 0
        L_0x0048:
            int r8 = r3.mVerticalChainStyle
            if (r8 != r6) goto L_0x004e
        L_0x004c:
            r6 = r7
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            r18 = r4
            r7 = r10
            r8 = 0
        L_0x0053:
            r21 = 0
            if (r8 != 0) goto L_0x0144
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            r4 = r4[r39]
            if (r6 == 0) goto L_0x0060
            r19 = 1
            goto L_0x0062
        L_0x0060:
            r19 = 4
        L_0x0062:
            int r22 = r4.getMargin()
            r23 = r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r7.mListDimensionBehaviors
            r5 = r5[r9]
            r24 = r6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r6) goto L_0x007a
            int[] r5 = r7.mResolvedMatchConstraintDefault
            r5 = r5[r9]
            if (r5 != 0) goto L_0x007a
            r5 = 1
            goto L_0x007b
        L_0x007a:
            r5 = 0
        L_0x007b:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r4.mTarget
            if (r6 == 0) goto L_0x0089
            if (r7 == r10) goto L_0x0089
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r4.mTarget
            int r6 = r6.getMargin()
            int r22 = r22 + r6
        L_0x0089:
            r6 = r22
            if (r24 == 0) goto L_0x0093
            if (r7 == r10) goto L_0x0093
            if (r7 == r12) goto L_0x0093
            r19 = 8
        L_0x0093:
            r22 = r5
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r4.mTarget
            if (r5 == 0) goto L_0x00d7
            if (r7 != r12) goto L_0x00aa
            androidx.constraintlayout.core.SolverVariable r5 = r4.mSolverVariable
            r25 = r8
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r8 = r8.mSolverVariable
            r26 = r14
            r14 = 6
            r1.addGreaterThan(r5, r8, r6, r14)
            goto L_0x00b9
        L_0x00aa:
            r25 = r8
            r26 = r14
            androidx.constraintlayout.core.SolverVariable r5 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r8 = r8.mSolverVariable
            r14 = 8
            r1.addGreaterThan(r5, r8, r6, r14)
        L_0x00b9:
            if (r22 == 0) goto L_0x00bf
            if (r24 != 0) goto L_0x00bf
            r19 = 5
        L_0x00bf:
            if (r7 != r12) goto L_0x00cb
            if (r24 == 0) goto L_0x00cb
            boolean r5 = r7.isInBarrier(r9)
            if (r5 == 0) goto L_0x00cb
            r5 = 5
            goto L_0x00cd
        L_0x00cb:
            r5 = r19
        L_0x00cd:
            androidx.constraintlayout.core.SolverVariable r8 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            r1.addEquality(r8, r4, r6, r5)
            goto L_0x00db
        L_0x00d7:
            r25 = r8
            r26 = r14
        L_0x00db:
            if (r23 == 0) goto L_0x0113
            int r4 = r7.getVisibility()
            r14 = 8
            if (r4 == r14) goto L_0x0101
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r7.mListDimensionBehaviors
            r4 = r4[r9]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r5) goto L_0x0101
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            r6 = 5
            r8 = 0
            r1.addGreaterThan(r4, r5, r8, r6)
            goto L_0x0102
        L_0x0101:
            r8 = 0
        L_0x0102:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            r4 = r4[r39]
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r0.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            r14 = 8
            r1.addGreaterThan(r4, r5, r8, r14)
        L_0x0113:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r7.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x0134
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r4.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r4.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x0134
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r4.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r5.mOwner
            if (r5 == r7) goto L_0x0132
            goto L_0x0134
        L_0x0132:
            r21 = r4
        L_0x0134:
            if (r21 == 0) goto L_0x013b
            r7 = r21
            r8 = r25
            goto L_0x013c
        L_0x013b:
            r8 = 1
        L_0x013c:
            r5 = r23
            r6 = r24
            r14 = r26
            goto L_0x0053
        L_0x0144:
            r23 = r5
            r24 = r6
            r26 = r14
            if (r13 == 0) goto L_0x01ab
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r11.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x01ab
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r13.mListAnchors
            r4 = r4[r5]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r6 = r13.mListDimensionBehaviors
            r6 = r6[r9]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r7) goto L_0x0180
            int[] r6 = r13.mResolvedMatchConstraintDefault
            r6 = r6[r9]
            if (r6 != 0) goto L_0x0180
            if (r24 != 0) goto L_0x0180
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r4.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 != r0) goto L_0x0180
            androidx.constraintlayout.core.SolverVariable r6 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r7 = r7.mSolverVariable
            int r8 = r4.getMargin()
            int r8 = -r8
            r14 = 5
            r1.addEquality(r6, r7, r8, r14)
            goto L_0x0198
        L_0x0180:
            r14 = 5
            if (r24 == 0) goto L_0x0198
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r4.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r6.mOwner
            if (r6 != r0) goto L_0x0198
            androidx.constraintlayout.core.SolverVariable r6 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r4.mTarget
            androidx.constraintlayout.core.SolverVariable r7 = r7.mSolverVariable
            int r8 = r4.getMargin()
            int r8 = -r8
            r14 = 4
            r1.addEquality(r6, r7, r8, r14)
        L_0x0198:
            androidx.constraintlayout.core.SolverVariable r6 = r4.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r11.mListAnchors
            r5 = r7[r5]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            int r4 = r4.getMargin()
            int r4 = -r4
            r14 = 6
            r1.addLowerThan(r6, r5, r4, r14)
        L_0x01ab:
            if (r23 == 0) goto L_0x01c8
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r0.mListAnchors
            int r4 = r39 + 1
            r0 = r0[r4]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r4]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r11.mListAnchors
            r4 = r6[r4]
            int r4 = r4.getMargin()
            r14 = 8
            r1.addGreaterThan(r0, r5, r4, r14)
        L_0x01c8:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.ConstraintWidget> r0 = r2.mWeightedMatchConstraintsWidgets
            if (r0 == 0) goto L_0x027b
            int r4 = r0.size()
            r5 = 1
            if (r4 <= r5) goto L_0x027b
            boolean r6 = r2.mHasUndefinedWeights
            if (r6 == 0) goto L_0x01e1
            boolean r6 = r2.mHasComplexMatchWeights
            if (r6 != 0) goto L_0x01e1
            int r6 = r2.mWidgetsMatchCount
            float r6 = (float) r6
            r29 = r6
            goto L_0x01e3
        L_0x01e1:
            r29 = r18
        L_0x01e3:
            r6 = 0
            r28 = r6
            r7 = r21
            r8 = 0
        L_0x01e9:
            if (r8 >= r4) goto L_0x027b
            java.lang.Object r14 = r0.get(r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget r14 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r14
            float[] r5 = r14.mWeight
            r5 = r5[r9]
            int r18 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r18 >= 0) goto L_0x021e
            boolean r5 = r2.mHasComplexMatchWeights
            if (r5 == 0) goto L_0x0216
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            int r18 = r39 + 1
            r5 = r5[r18]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r14 = r14.mListAnchors
            r14 = r14[r39]
            androidx.constraintlayout.core.SolverVariable r14 = r14.mSolverVariable
            r18 = r0
            r36 = r6
            r0 = 0
            r6 = 4
            r1.addEquality(r5, r14, r0, r6)
            r6 = r0
            goto L_0x0272
        L_0x0216:
            r18 = r0
            r36 = r6
            r6 = 4
            r5 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0223
        L_0x021e:
            r18 = r0
            r36 = r6
            r6 = 4
        L_0x0223:
            r30 = r5
            int r0 = (r30 > r36 ? 1 : (r30 == r36 ? 0 : -1))
            if (r0 != 0) goto L_0x023e
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            int r5 = r39 + 1
            r0 = r0[r5]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r14.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            r6 = 0
            r14 = 8
            r1.addEquality(r0, r5, r6, r14)
            goto L_0x0272
        L_0x023e:
            r6 = 0
            if (r7 == 0) goto L_0x026f
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r7.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r7.mListAnchors
            int r7 = r39 + 1
            r5 = r5[r7]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r6 = r6[r39]
            androidx.constraintlayout.core.SolverVariable r6 = r6.mSolverVariable
            r31 = r0
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r14.mListAnchors
            r0 = r0[r7]
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            androidx.constraintlayout.core.ArrayRow r27 = r1.createRow()
            r34 = r0
            r32 = r5
            r33 = r6
            r27.createRowEqualMatchDimensions(r28, r29, r30, r31, r32, r33, r34)
            r0 = r27
            r1.addConstraint(r0)
        L_0x026f:
            r7 = r14
            r28 = r30
        L_0x0272:
            int r8 = r8 + 1
            r6 = r36
            r0 = r18
            r5 = 1
            goto L_0x01e9
        L_0x027b:
            if (r12 == 0) goto L_0x02d0
            if (r12 == r13) goto L_0x0281
            if (r24 == 0) goto L_0x02d0
        L_0x0281:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r0 = r10.mListAnchors
            r0 = r0[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r11.mListAnchors
            int r4 = r39 + 1
            r2 = r2[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r0.mTarget
            if (r5 == 0) goto L_0x0294
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.mTarget
            androidx.constraintlayout.core.SolverVariable r0 = r0.mSolverVariable
            goto L_0x0296
        L_0x0294:
            r0 = r21
        L_0x0296:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x029f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            goto L_0x02a1
        L_0x029f:
            r5 = r21
        L_0x02a1:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r12.mListAnchors
            r6 = r6[r39]
            if (r13 == 0) goto L_0x02ab
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            r2 = r2[r4]
        L_0x02ab:
            if (r0 == 0) goto L_0x04e7
            if (r5 == 0) goto L_0x04e7
            if (r9 != 0) goto L_0x02b4
            float r3 = r3.mHorizontalBiasPercent
            goto L_0x02b6
        L_0x02b4:
            float r3 = r3.mVerticalBiasPercent
        L_0x02b6:
            r4 = r3
            int r3 = r6.getMargin()
            int r7 = r2.getMargin()
            androidx.constraintlayout.core.SolverVariable r6 = r6.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            r8 = 7
            r35 = r2
            r2 = r0
            r0 = r1
            r1 = r6
            r6 = r35
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x04e7
        L_0x02d0:
            if (r26 == 0) goto L_0x03ca
            if (r12 == 0) goto L_0x03ca
            int r0 = r2.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x02e1
            int r0 = r2.mWidgetsCount
            int r1 = r2.mWidgetsMatchCount
            if (r0 != r1) goto L_0x02e1
            r16 = 1
            goto L_0x02e3
        L_0x02e1:
            r16 = 0
        L_0x02e3:
            r0 = r12
            r14 = r0
        L_0x02e5:
            if (r14 == 0) goto L_0x04e7
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r1 = r14.mNextChainWidget
            r1 = r1[r9]
        L_0x02eb:
            if (r1 == 0) goto L_0x02fa
            int r2 = r1.getVisibility()
            r3 = 8
            if (r2 != r3) goto L_0x02fc
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r1 = r1.mNextChainWidget
            r1 = r1[r9]
            goto L_0x02eb
        L_0x02fa:
            r3 = 8
        L_0x02fc:
            if (r1 != 0) goto L_0x030a
            if (r14 != r13) goto L_0x0301
            goto L_0x030a
        L_0x0301:
            r18 = r0
            r17 = r1
            r9 = r3
        L_0x0306:
            r20 = 5
            goto L_0x03ba
        L_0x030a:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.core.SolverVariable r4 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x0319
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            goto L_0x031b
        L_0x0319:
            r5 = r21
        L_0x031b:
            if (r0 == r14) goto L_0x0326
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r0.mListAnchors
            int r6 = r39 + 1
            r5 = r5[r6]
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            goto L_0x033b
        L_0x0326:
            if (r14 != r12) goto L_0x033b
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r10.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x0339
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r10.mListAnchors
            r5 = r5[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            goto L_0x033b
        L_0x0339:
            r5 = r21
        L_0x033b:
            int r2 = r2.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            int r7 = r39 + 1
            r6 = r6[r7]
            int r6 = r6.getMargin()
            if (r1 == 0) goto L_0x0354
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r8 = r1.mListAnchors
            r8 = r8[r39]
            androidx.constraintlayout.core.SolverVariable r3 = r8.mSolverVariable
        L_0x0351:
            r36 = r1
            goto L_0x0363
        L_0x0354:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r7]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r3.mTarget
            if (r8 == 0) goto L_0x035f
            androidx.constraintlayout.core.SolverVariable r3 = r8.mSolverVariable
            goto L_0x0351
        L_0x035f:
            r36 = r1
            r3 = r21
        L_0x0363:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r14.mListAnchors
            r1 = r1[r7]
            androidx.constraintlayout.core.SolverVariable r1 = r1.mSolverVariable
            if (r8 == 0) goto L_0x0370
            int r8 = r8.getMargin()
            int r6 = r6 + r8
        L_0x0370:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r8 = r0.mListAnchors
            r8 = r8[r7]
            int r8 = r8.getMargin()
            int r2 = r2 + r8
            if (r4 == 0) goto L_0x03b2
            if (r5 == 0) goto L_0x03b2
            if (r3 == 0) goto L_0x03b2
            if (r1 == 0) goto L_0x03b2
            if (r14 != r12) goto L_0x038b
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r39]
            int r2 = r2.getMargin()
        L_0x038b:
            if (r14 != r13) goto L_0x0395
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r13.mListAnchors
            r6 = r6[r7]
            int r6 = r6.getMargin()
        L_0x0395:
            r7 = r6
            if (r16 == 0) goto L_0x039b
            r8 = 8
            goto L_0x039c
        L_0x039b:
            r8 = 5
        L_0x039c:
            r6 = r1
            r1 = r4
            r4 = 1056964608(0x3f000000, float:0.5)
            r9 = r3
            r3 = r2
            r2 = r5
            r5 = r9
            r17 = r36
            r18 = r0
            r9 = 8
            r20 = 5
            r0 = r37
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x03ba
        L_0x03b2:
            r17 = r36
            r18 = r0
            r9 = 8
            goto L_0x0306
        L_0x03ba:
            int r0 = r14.getVisibility()
            if (r0 == r9) goto L_0x03c2
            r0 = r14
            goto L_0x03c4
        L_0x03c2:
            r0 = r18
        L_0x03c4:
            r9 = r38
            r14 = r17
            goto L_0x02e5
        L_0x03ca:
            r9 = 8
            if (r15 == 0) goto L_0x04e7
            if (r12 == 0) goto L_0x04e7
            int r0 = r2.mWidgetsMatchCount
            if (r0 <= 0) goto L_0x03dd
            int r0 = r2.mWidgetsCount
            int r1 = r2.mWidgetsMatchCount
            if (r0 != r1) goto L_0x03dd
            r16 = 1
            goto L_0x03df
        L_0x03dd:
            r16 = 0
        L_0x03df:
            r0 = r12
            r14 = r0
        L_0x03e1:
            if (r14 == 0) goto L_0x0495
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r1 = r14.mNextChainWidget
            r1 = r1[r38]
        L_0x03e7:
            if (r1 == 0) goto L_0x03f4
            int r2 = r1.getVisibility()
            if (r2 != r9) goto L_0x03f4
            androidx.constraintlayout.core.widgets.ConstraintWidget[] r1 = r1.mNextChainWidget
            r1 = r1[r38]
            goto L_0x03e7
        L_0x03f4:
            if (r14 == r12) goto L_0x0481
            if (r14 == r13) goto L_0x0481
            if (r1 == 0) goto L_0x0481
            if (r1 != r13) goto L_0x03fe
            r1 = r21
        L_0x03fe:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r14.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.core.SolverVariable r3 = r2.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r2.mTarget
            if (r4 == 0) goto L_0x040c
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
        L_0x040c:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r4 = r0.mListAnchors
            int r5 = r39 + 1
            r4 = r4[r5]
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            int r2 = r2.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r14.mListAnchors
            r6 = r6[r5]
            int r6 = r6.getMargin()
            if (r1 == 0) goto L_0x0434
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r1.mListAnchors
            r7 = r7[r39]
            androidx.constraintlayout.core.SolverVariable r8 = r7.mSolverVariable
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r7.mTarget
            if (r9 == 0) goto L_0x0431
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r7.mTarget
            androidx.constraintlayout.core.SolverVariable r9 = r9.mSolverVariable
            goto L_0x0445
        L_0x0431:
            r9 = r21
            goto L_0x0445
        L_0x0434:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r13.mListAnchors
            r7 = r7[r39]
            if (r7 == 0) goto L_0x043d
            androidx.constraintlayout.core.SolverVariable r8 = r7.mSolverVariable
            goto L_0x043f
        L_0x043d:
            r8 = r21
        L_0x043f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r9 = r14.mListAnchors
            r9 = r9[r5]
            androidx.constraintlayout.core.SolverVariable r9 = r9.mSolverVariable
        L_0x0445:
            if (r7 == 0) goto L_0x044c
            int r7 = r7.getMargin()
            int r6 = r6 + r7
        L_0x044c:
            r7 = r6
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r6 = r0.mListAnchors
            r5 = r6[r5]
            int r5 = r5.getMargin()
            int r2 = r2 + r5
            r5 = r8
            if (r16 == 0) goto L_0x045c
            r8 = 8
            goto L_0x045d
        L_0x045c:
            r8 = 4
        L_0x045d:
            if (r3 == 0) goto L_0x0477
            if (r4 == 0) goto L_0x0477
            if (r5 == 0) goto L_0x0477
            if (r9 == 0) goto L_0x0477
            r6 = r1
            r1 = r3
            r3 = r2
            r2 = r4
            r4 = 1056964608(0x3f000000, float:0.5)
            r17 = r6
            r6 = r9
            r19 = 4
            r9 = r0
            r0 = r37
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L_0x047e
        L_0x0477:
            r9 = r0
            r17 = r1
            r19 = 4
            r0 = r37
        L_0x047e:
            r1 = r17
            goto L_0x0486
        L_0x0481:
            r9 = r0
            r19 = 4
            r0 = r37
        L_0x0486:
            int r2 = r14.getVisibility()
            r3 = 8
            if (r2 == r3) goto L_0x048f
            goto L_0x0490
        L_0x048f:
            r14 = r9
        L_0x0490:
            r9 = r3
            r0 = r14
            r14 = r1
            goto L_0x03e1
        L_0x0495:
            r0 = r37
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r10.mListAnchors
            r2 = r2[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r13.mListAnchors
            int r4 = r39 + 1
            r9 = r3[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r3 = r11.mListAnchors
            r3 = r3[r4]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r3.mTarget
            r8 = 5
            if (r2 == 0) goto L_0x04d6
            if (r12 == r13) goto L_0x04be
            androidx.constraintlayout.core.SolverVariable r3 = r1.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            int r1 = r1.getMargin()
            r0.addEquality(r3, r2, r1, r8)
            goto L_0x04d6
        L_0x04be:
            if (r10 == 0) goto L_0x04d6
            r3 = r1
            androidx.constraintlayout.core.SolverVariable r1 = r3.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r2 = r2.mSolverVariable
            int r3 = r3.getMargin()
            androidx.constraintlayout.core.SolverVariable r5 = r9.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r6 = r10.mSolverVariable
            int r7 = r9.getMargin()
            r4 = 1056964608(0x3f000000, float:0.5)
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x04d6:
            if (r10 == 0) goto L_0x04e9
            if (r12 == r13) goto L_0x04e9
            androidx.constraintlayout.core.SolverVariable r1 = r9.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r2 = r10.mSolverVariable
            int r3 = r9.getMargin()
            int r3 = -r3
            r0.addEquality(r1, r2, r3, r8)
            goto L_0x04e9
        L_0x04e7:
            r0 = r37
        L_0x04e9:
            if (r26 != 0) goto L_0x04ed
            if (r15 == 0) goto L_0x054c
        L_0x04ed:
            if (r12 == 0) goto L_0x054c
            if (r12 == r13) goto L_0x054c
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r39]
            if (r13 != 0) goto L_0x04f8
            r13 = r12
        L_0x04f8:
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r13.mListAnchors
            int r3 = r39 + 1
            r2 = r2[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r1.mTarget
            if (r4 == 0) goto L_0x0507
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r1.mTarget
            androidx.constraintlayout.core.SolverVariable r4 = r4.mSolverVariable
            goto L_0x0509
        L_0x0507:
            r4 = r21
        L_0x0509:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            if (r5 == 0) goto L_0x0512
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r2.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            goto L_0x0514
        L_0x0512:
            r5 = r21
        L_0x0514:
            if (r11 == r13) goto L_0x0526
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r5 = r11.mListAnchors
            r5 = r5[r3]
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r5.mTarget
            if (r6 == 0) goto L_0x0524
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            androidx.constraintlayout.core.SolverVariable r5 = r5.mSolverVariable
            r21 = r5
        L_0x0524:
            r5 = r21
        L_0x0526:
            if (r12 != r13) goto L_0x0530
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r1 = r12.mListAnchors
            r1 = r1[r39]
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r2 = r12.mListAnchors
            r2 = r2[r3]
        L_0x0530:
            if (r4 == 0) goto L_0x054c
            if (r5 == 0) goto L_0x054c
            r6 = r3
            int r3 = r1.getMargin()
            androidx.constraintlayout.core.widgets.ConstraintAnchor[] r7 = r13.mListAnchors
            r6 = r7[r6]
            int r7 = r6.getMargin()
            androidx.constraintlayout.core.SolverVariable r1 = r1.mSolverVariable
            androidx.constraintlayout.core.SolverVariable r6 = r2.mSolverVariable
            r8 = 5
            r2 = r4
            r4 = 1056964608(0x3f000000, float:0.5)
            r0.addCentering(r1, r2, r3, r4, r5, r6, r7, r8)
        L_0x054c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.Chain.applyChainConstraints(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.LinearSystem, int, int, androidx.constraintlayout.core.widgets.ChainHead):void");
    }
}
