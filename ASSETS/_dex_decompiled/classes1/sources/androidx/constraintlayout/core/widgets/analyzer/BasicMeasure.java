package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

public class BasicMeasure {
    public static final int AT_MOST = Integer.MIN_VALUE;
    private static final boolean DEBUG = false;
    public static final int EXACTLY = 1073741824;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer constraintWidgetContainer;
    private Measure mMeasure = new Measure();
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>();

    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public ConstraintWidget.DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public ConstraintWidget.DimensionBehaviour verticalBehavior;
        public int verticalDimension;
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget constraintWidget, Measure measure);
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.mVariableDimensionsWidgets.clear();
        int size = constraintWidgetContainer2.mChildren.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer2.mChildren.get(i);
            if (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(constraintWidget);
            }
        }
        constraintWidgetContainer2.invalidateGraph();
    }

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer2) {
        this.constraintWidgetContainer = constraintWidgetContainer2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00a0, code lost:
        if (r8 != androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) goto L_0x00aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00a7, code lost:
        if (r5.mDimensionRatio <= 0.0f) goto L_0x00aa;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r12) {
        /*
            r11 = this;
            java.util.ArrayList r0 = r12.mChildren
            int r0 = r0.size()
            r1 = 64
            boolean r1 = r12.optimizeFor(r1)
            androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer r2 = r12.getMeasurer()
            r3 = 0
            r4 = r3
        L_0x0012:
            if (r4 >= r0) goto L_0x00c4
            java.util.ArrayList r5 = r12.mChildren
            java.lang.Object r5 = r5.get(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = (androidx.constraintlayout.core.widgets.ConstraintWidget) r5
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r6 == 0) goto L_0x0022
            goto L_0x00c0
        L_0x0022:
            boolean r6 = r5 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r6 == 0) goto L_0x0028
            goto L_0x00c0
        L_0x0028:
            boolean r6 = r5.isInVirtualLayout()
            if (r6 == 0) goto L_0x0030
            goto L_0x00c0
        L_0x0030:
            if (r1 == 0) goto L_0x004c
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r6 = r5.horizontalRun
            if (r6 == 0) goto L_0x004c
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r6 = r5.verticalRun
            if (r6 == 0) goto L_0x004c
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r6 = r5.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x004c
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r6 = r5.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r6 = r6.dimension
            boolean r6 = r6.resolved
            if (r6 == 0) goto L_0x004c
            goto L_0x00c0
        L_0x004c:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = r5.getDimensionBehaviour(r3)
            r7 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r8 = r5.getDimensionBehaviour(r7)
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r9) goto L_0x0067
            int r9 = r5.mMatchConstraintDefaultWidth
            if (r9 == r7) goto L_0x0067
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r9) goto L_0x0067
            int r9 = r5.mMatchConstraintDefaultHeight
            if (r9 == r7) goto L_0x0067
            r9 = r7
            goto L_0x0068
        L_0x0067:
            r9 = r3
        L_0x0068:
            if (r9 != 0) goto L_0x00aa
            boolean r10 = r12.optimizeFor(r7)
            if (r10 == 0) goto L_0x00aa
            boolean r10 = r5 instanceof androidx.constraintlayout.core.widgets.VirtualLayout
            if (r10 != 0) goto L_0x00aa
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 != r10) goto L_0x0087
            int r10 = r5.mMatchConstraintDefaultWidth
            if (r10 != 0) goto L_0x0087
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 == r10) goto L_0x0087
            boolean r10 = r5.isInHorizontalChain()
            if (r10 != 0) goto L_0x0087
            r9 = r7
        L_0x0087:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r10) goto L_0x009a
            int r10 = r5.mMatchConstraintDefaultHeight
            if (r10 != 0) goto L_0x009a
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 == r10) goto L_0x009a
            boolean r10 = r5.isInHorizontalChain()
            if (r10 != 0) goto L_0x009a
            r9 = r7
        L_0x009a:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r6 == r10) goto L_0x00a2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r8 != r6) goto L_0x00aa
        L_0x00a2:
            float r6 = r5.mDimensionRatio
            r8 = 0
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x00aa
            goto L_0x00ab
        L_0x00aa:
            r7 = r9
        L_0x00ab:
            if (r7 == 0) goto L_0x00ae
            goto L_0x00c0
        L_0x00ae:
            int r6 = androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure.SELF_DIMENSIONS
            r11.measure(r2, r5, r6)
            androidx.constraintlayout.core.Metrics r5 = r12.mMetrics
            if (r5 == 0) goto L_0x00c0
            androidx.constraintlayout.core.Metrics r5 = r12.mMetrics
            long r6 = r5.measuredWidgets
            r8 = 1
            long r6 = r6 + r8
            r5.measuredWidgets = r6
        L_0x00c0:
            int r4 = r4 + 1
            goto L_0x0012
        L_0x00c4:
            r2.didMeasures()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.measureChildren(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer):void");
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer2, String str, int i, int i2, int i3) {
        int minWidth = constraintWidgetContainer2.getMinWidth();
        int minHeight = constraintWidgetContainer2.getMinHeight();
        constraintWidgetContainer2.setMinWidth(0);
        constraintWidgetContainer2.setMinHeight(0);
        constraintWidgetContainer2.setWidth(i2);
        constraintWidgetContainer2.setHeight(i3);
        constraintWidgetContainer2.setMinWidth(minWidth);
        constraintWidgetContainer2.setMinHeight(minHeight);
        this.constraintWidgetContainer.setPass(i);
        this.constraintWidgetContainer.layout();
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        long j;
        int i10;
        boolean z;
        int i11;
        int i12;
        long j2;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z2;
        BasicMeasure basicMeasure = this;
        ConstraintWidgetContainer constraintWidgetContainer3 = constraintWidgetContainer2;
        int i17 = i;
        int i18 = i4;
        int i19 = i6;
        Measurer measurer = constraintWidgetContainer3.getMeasurer();
        int size = constraintWidgetContainer3.mChildren.size();
        int width = constraintWidgetContainer3.getWidth();
        int height = constraintWidgetContainer3.getHeight();
        boolean enabled = Optimizer.enabled(i17, 128);
        boolean z3 = enabled || Optimizer.enabled(i17, 64);
        if (z3) {
            int i20 = 0;
            while (true) {
                if (i20 >= size) {
                    break;
                }
                ConstraintWidget constraintWidget = (ConstraintWidget) constraintWidgetContainer3.mChildren.get(i20);
                boolean z4 = (constraintWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (constraintWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && constraintWidget.getDimensionRatio() > 0.0f;
                if ((!constraintWidget.isInHorizontalChain() || !z4) && ((!constraintWidget.isInVerticalChain() || !z4) && !(constraintWidget instanceof VirtualLayout) && !constraintWidget.isInHorizontalChain() && !constraintWidget.isInVerticalChain())) {
                    i20++;
                }
            }
            z3 = false;
        }
        if (z3 && LinearSystem.sMetrics != null) {
            LinearSystem.sMetrics.measures++;
        }
        boolean z5 = z3 & ((i18 == 1073741824 && i19 == 1073741824) || enabled);
        int i21 = 2;
        if (z5) {
            j = 1;
            int min = Math.min(constraintWidgetContainer3.getMaxWidth(), i5);
            int min2 = Math.min(constraintWidgetContainer3.getMaxHeight(), i7);
            if (i18 == 1073741824 && constraintWidgetContainer3.getWidth() != min) {
                constraintWidgetContainer3.setWidth(min);
                constraintWidgetContainer3.invalidateGraph();
            }
            if (i19 == 1073741824 && constraintWidgetContainer3.getHeight() != min2) {
                constraintWidgetContainer3.setHeight(min2);
                constraintWidgetContainer3.invalidateGraph();
            }
            if (i18 == 1073741824 && i19 == 1073741824) {
                z = constraintWidgetContainer3.directMeasure(enabled);
                i10 = 2;
            } else {
                boolean directMeasureSetup = constraintWidgetContainer3.directMeasureSetup(enabled);
                if (i18 == 1073741824) {
                    directMeasureSetup &= constraintWidgetContainer3.directMeasureWithOrientation(enabled, 0);
                    i10 = 1;
                } else {
                    i10 = 0;
                }
                if (i19 == 1073741824) {
                    z = constraintWidgetContainer3.directMeasureWithOrientation(enabled, 1) & directMeasureSetup;
                    i10++;
                } else {
                    z = directMeasureSetup;
                }
            }
            if (z) {
                constraintWidgetContainer3.updateFromRuns(i18 == 1073741824, i19 == 1073741824);
            }
        } else {
            j = 1;
            z = false;
            i10 = 0;
        }
        if (z && i10 == 2) {
            return 0;
        }
        int optimizationLevel = constraintWidgetContainer3.getOptimizationLevel();
        if (size > 0) {
            measureChildren(constraintWidgetContainer2);
        }
        updateHierarchy(constraintWidgetContainer2);
        int size2 = basicMeasure.mVariableDimensionsWidgets.size();
        if (size > 0) {
            int i22 = width;
            int i23 = height;
            basicMeasure.solveLinearSystem(constraintWidgetContainer3, "First pass", 0, i22, i23);
            i12 = i22;
            i11 = i23;
        } else {
            i12 = width;
            i11 = height;
        }
        if (size2 > 0) {
            boolean z6 = constraintWidgetContainer3.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean z7 = constraintWidgetContainer3.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int max = Math.max(constraintWidgetContainer3.getWidth(), basicMeasure.constraintWidgetContainer.getMinWidth());
            j2 = 0;
            int max2 = Math.max(constraintWidgetContainer3.getHeight(), basicMeasure.constraintWidgetContainer.getMinHeight());
            int i24 = 0;
            boolean z8 = false;
            while (i24 < size2) {
                ConstraintWidget constraintWidget2 = basicMeasure.mVariableDimensionsWidgets.get(i24);
                if (!(constraintWidget2 instanceof VirtualLayout)) {
                    z2 = z5;
                    i16 = i24;
                    i15 = i12;
                    i14 = i11;
                } else {
                    int width2 = constraintWidget2.getWidth();
                    z2 = z5;
                    int height2 = constraintWidget2.getHeight();
                    i16 = i24;
                    boolean measure = basicMeasure.measure(measurer, constraintWidget2, Measure.TRY_GIVEN_DIMENSIONS) | z8;
                    if (constraintWidgetContainer3.mMetrics != null) {
                        i15 = i12;
                        i14 = i11;
                        constraintWidgetContainer3.mMetrics.measuredMatchWidgets += j;
                    } else {
                        i15 = i12;
                        i14 = i11;
                    }
                    int width3 = constraintWidget2.getWidth();
                    int height3 = constraintWidget2.getHeight();
                    if (width3 != width2) {
                        constraintWidget2.setWidth(width3);
                        if (z6 && constraintWidget2.getRight() > max) {
                            max = Math.max(max, constraintWidget2.getRight() + constraintWidget2.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                        }
                        measure = true;
                    }
                    if (height3 != height2) {
                        constraintWidget2.setHeight(height3);
                        if (z7 && constraintWidget2.getBottom() > max2) {
                            max2 = Math.max(max2, constraintWidget2.getBottom() + constraintWidget2.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                        }
                        measure = true;
                    }
                    z8 = measure | ((VirtualLayout) constraintWidget2).needSolverPass();
                }
                i24 = i16 + 1;
                i12 = i15;
                i11 = i14;
                z5 = z2;
                i21 = 2;
            }
            boolean z9 = z5;
            int i25 = i12;
            int i26 = i11;
            int i27 = i21;
            int i28 = 0;
            while (true) {
                if (i28 >= i27) {
                    break;
                }
                int i29 = 0;
                while (i29 < size2) {
                    ConstraintWidget constraintWidget3 = basicMeasure.mVariableDimensionsWidgets.get(i29);
                    if ((!(constraintWidget3 instanceof Helper) || (constraintWidget3 instanceof VirtualLayout)) && !(constraintWidget3 instanceof Guideline) && constraintWidget3.getVisibility() != 8 && ((!z9 || !constraintWidget3.horizontalRun.dimension.resolved || !constraintWidget3.verticalRun.dimension.resolved) && !(constraintWidget3 instanceof VirtualLayout))) {
                        int width4 = constraintWidget3.getWidth();
                        int height4 = constraintWidget3.getHeight();
                        int baselineDistance = constraintWidget3.getBaselineDistance();
                        int i30 = Measure.TRY_GIVEN_DIMENSIONS;
                        i13 = i29;
                        if (i28 == 1) {
                            i30 = Measure.USE_GIVEN_DIMENSIONS;
                        }
                        boolean measure2 = basicMeasure.measure(measurer, constraintWidget3, i30) | z8;
                        if (constraintWidgetContainer3.mMetrics != null) {
                            constraintWidgetContainer3.mMetrics.measuredMatchWidgets += j;
                        }
                        int width5 = constraintWidget3.getWidth();
                        int height5 = constraintWidget3.getHeight();
                        if (width5 != width4) {
                            constraintWidget3.setWidth(width5);
                            if (z6 && constraintWidget3.getRight() > max) {
                                max = Math.max(max, constraintWidget3.getRight() + constraintWidget3.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                            }
                            measure2 = true;
                        }
                        if (height5 != height4) {
                            constraintWidget3.setHeight(height5);
                            if (z7 && constraintWidget3.getBottom() > max2) {
                                max2 = Math.max(max2, constraintWidget3.getBottom() + constraintWidget3.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                            }
                            measure2 = true;
                        }
                        z8 = (!constraintWidget3.hasBaseline() || baselineDistance == constraintWidget3.getBaselineDistance()) ? measure2 : true;
                    } else {
                        i13 = i29;
                    }
                    i29 = i13 + 1;
                    basicMeasure = this;
                    constraintWidgetContainer3 = constraintWidgetContainer2;
                }
                if (!z8) {
                    constraintWidgetContainer3 = constraintWidgetContainer2;
                    break;
                }
                i28++;
                ConstraintWidgetContainer constraintWidgetContainer4 = constraintWidgetContainer2;
                solveLinearSystem(constraintWidgetContainer4, "intermediate pass", i28, i25, i26);
                basicMeasure = this;
                constraintWidgetContainer3 = constraintWidgetContainer4;
                i27 = 2;
                z8 = false;
            }
        } else {
            j2 = 0;
        }
        constraintWidgetContainer3.setOptimizationLevel(optimizationLevel);
        return j2;
    }

    private boolean measure(Measurer measurer, ConstraintWidget constraintWidget, int i) {
        this.mMeasure.horizontalBehavior = constraintWidget.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget.getWidth();
        this.mMeasure.verticalDimension = constraintWidget.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.measureStrategy = i;
        boolean z = this.mMeasure.horizontalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z2 = this.mMeasure.verticalBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
        boolean z3 = z && constraintWidget.mDimensionRatio > 0.0f;
        boolean z4 = z2 && constraintWidget.mDimensionRatio > 0.0f;
        if (z3 && constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        if (z4 && constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = ConstraintWidget.DimensionBehaviour.FIXED;
        }
        measurer.measure(constraintWidget, this.mMeasure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        constraintWidget.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }
}
