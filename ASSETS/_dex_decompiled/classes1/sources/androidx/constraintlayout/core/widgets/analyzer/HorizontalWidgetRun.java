package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;

public class HorizontalWidgetRun extends WidgetRun {
    private static int[] tempDimensions = new int[2];

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.type = DependencyNode.Type.LEFT;
        this.end.type = DependencyNode.Type.RIGHT;
        this.orientation = 0;
    }

    public String toString() {
        return "HorizontalRun " + this.widget.getDebugName();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        this.start.clear();
        this.end.clear();
        this.dimension.clear();
        this.resolved = false;
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.resolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.dimension.resolved = false;
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        if (this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || this.widget.mMatchConstraintDefaultWidth == 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        ConstraintWidget parent;
        ConstraintWidget parent2;
        if (this.widget.measured) {
            this.dimension.resolve(this.widget.getWidth());
        }
        if (!this.dimension.resolved) {
            this.dimensionBehavior = this.widget.getHorizontalDimensionBehaviour();
            if (this.dimensionBehavior != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent2 = this.widget.getParent()) != null && (parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent2.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
                    int width = (parent2.getWidth() - this.widget.mLeft.getMargin()) - this.widget.mRight.getMargin();
                    addTarget(this.start, parent2.horizontalRun.start, this.widget.mLeft.getMargin());
                    addTarget(this.end, parent2.horizontalRun.end, -this.widget.mRight.getMargin());
                    this.dimension.resolve(width);
                    return;
                } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.FIXED) {
                    this.dimension.resolve(this.widget.getWidth());
                }
            }
        } else if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && (parent = this.widget.getParent()) != null && (parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.FIXED || parent.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.MATCH_PARENT)) {
            addTarget(this.start, parent.horizontalRun.start, this.widget.mLeft.getMargin());
            addTarget(this.end, parent.horizontalRun.end, -this.widget.mRight.getMargin());
            return;
        }
        if (!this.dimension.resolved || !this.widget.measured) {
            if (this.dimensionBehavior == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                int i = this.widget.mMatchConstraintDefaultWidth;
                if (i == 2) {
                    ConstraintWidget parent3 = this.widget.getParent();
                    if (parent3 != null) {
                        DimensionDependency dimensionDependency = parent3.verticalRun.dimension;
                        this.dimension.targets.add(dimensionDependency);
                        dimensionDependency.dependencies.add(this.dimension);
                        this.dimension.delegateToWidgetRun = true;
                        this.dimension.dependencies.add(this.start);
                        this.dimension.dependencies.add(this.end);
                    }
                } else if (i == 3) {
                    if (this.widget.mMatchConstraintDefaultHeight == 3) {
                        this.start.updateDelegate = this;
                        this.end.updateDelegate = this;
                        this.widget.verticalRun.start.updateDelegate = this;
                        this.widget.verticalRun.end.updateDelegate = this;
                        this.dimension.updateDelegate = this;
                        if (this.widget.isInVerticalChain()) {
                            this.dimension.targets.add(this.widget.verticalRun.dimension);
                            this.widget.verticalRun.dimension.dependencies.add(this.dimension);
                            this.widget.verticalRun.dimension.updateDelegate = this;
                            this.dimension.targets.add(this.widget.verticalRun.start);
                            this.dimension.targets.add(this.widget.verticalRun.end);
                            this.widget.verticalRun.start.dependencies.add(this.dimension);
                            this.widget.verticalRun.end.dependencies.add(this.dimension);
                        } else if (this.widget.isInHorizontalChain()) {
                            this.widget.verticalRun.dimension.targets.add(this.dimension);
                            this.dimension.dependencies.add(this.widget.verticalRun.dimension);
                        } else {
                            this.widget.verticalRun.dimension.targets.add(this.dimension);
                        }
                    } else {
                        DimensionDependency dimensionDependency2 = this.widget.verticalRun.dimension;
                        this.dimension.targets.add(dimensionDependency2);
                        dimensionDependency2.dependencies.add(this.dimension);
                        this.widget.verticalRun.start.dependencies.add(this.dimension);
                        this.widget.verticalRun.end.dependencies.add(this.dimension);
                        this.dimension.delegateToWidgetRun = true;
                        this.dimension.dependencies.add(this.start);
                        this.dimension.dependencies.add(this.end);
                        this.start.targets.add(this.dimension);
                        this.end.targets.add(this.dimension);
                    }
                }
            }
            if (this.widget.mListAnchors[0].mTarget == null || this.widget.mListAnchors[1].mTarget == null) {
                if (this.widget.mListAnchors[0].mTarget != null) {
                    DependencyNode target = getTarget(this.widget.mListAnchors[0]);
                    if (target != null) {
                        addTarget(this.start, target, this.widget.mListAnchors[0].getMargin());
                        addTarget(this.end, this.start, 1, this.dimension);
                    }
                } else if (this.widget.mListAnchors[1].mTarget != null) {
                    DependencyNode target2 = getTarget(this.widget.mListAnchors[1]);
                    if (target2 != null) {
                        addTarget(this.end, target2, -this.widget.mListAnchors[1].getMargin());
                        addTarget(this.start, this.end, -1, this.dimension);
                    }
                } else if (!(this.widget instanceof Helper) && this.widget.getParent() != null) {
                    addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
                    addTarget(this.end, this.start, 1, this.dimension);
                }
            } else if (this.widget.isInHorizontalChain()) {
                this.start.margin = this.widget.mListAnchors[0].getMargin();
                this.end.margin = -this.widget.mListAnchors[1].getMargin();
            } else {
                DependencyNode target3 = getTarget(this.widget.mListAnchors[0]);
                DependencyNode target4 = getTarget(this.widget.mListAnchors[1]);
                if (target3 != null) {
                    target3.addDependency(this);
                }
                if (target4 != null) {
                    target4.addDependency(this);
                }
                this.mRunType = WidgetRun.RunType.CENTER;
            }
        } else if (this.widget.mListAnchors[0].mTarget == null || this.widget.mListAnchors[1].mTarget == null) {
            if (this.widget.mListAnchors[0].mTarget != null) {
                DependencyNode target5 = getTarget(this.widget.mListAnchors[0]);
                if (target5 != null) {
                    addTarget(this.start, target5, this.widget.mListAnchors[0].getMargin());
                    addTarget(this.end, this.start, this.dimension.value);
                }
            } else if (this.widget.mListAnchors[1].mTarget != null) {
                DependencyNode target6 = getTarget(this.widget.mListAnchors[1]);
                if (target6 != null) {
                    addTarget(this.end, target6, -this.widget.mListAnchors[1].getMargin());
                    addTarget(this.start, this.end, -this.dimension.value);
                }
            } else if (!(this.widget instanceof Helper) && this.widget.getParent() != null && this.widget.getAnchor(ConstraintAnchor.Type.CENTER).mTarget == null) {
                addTarget(this.start, this.widget.getParent().horizontalRun.start, this.widget.getX());
                addTarget(this.end, this.start, this.dimension.value);
            }
        } else if (this.widget.isInHorizontalChain()) {
            this.start.margin = this.widget.mListAnchors[0].getMargin();
            this.end.margin = -this.widget.mListAnchors[1].getMargin();
        } else {
            DependencyNode target7 = getTarget(this.widget.mListAnchors[0]);
            if (target7 != null) {
                addTarget(this.start, target7, this.widget.mListAnchors[0].getMargin());
            }
            DependencyNode target8 = getTarget(this.widget.mListAnchors[1]);
            if (target8 != null) {
                addTarget(this.end, target8, -this.widget.mListAnchors[1].getMargin());
            }
            this.start.delegateToWidgetRun = true;
            this.end.delegateToWidgetRun = true;
        }
    }

    private void computeInsetRatio(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i2 - i;
        int i7 = i4 - i3;
        if (i5 == -1) {
            int i8 = (int) ((((float) i7) * f) + 0.5f);
            int i9 = (int) ((((float) i6) / f) + 0.5f);
            if (i8 <= i6) {
                iArr[0] = i8;
                iArr[1] = i7;
            } else if (i9 <= i7) {
                iArr[0] = i6;
                iArr[1] = i9;
            }
        } else if (i5 == 0) {
            iArr[0] = (int) ((((float) i7) * f) + 0.5f);
            iArr[1] = i7;
        } else if (i5 == 1) {
            iArr[0] = i6;
            iArr[1] = (int) ((((float) i6) * f) + 0.5f);
        }
    }

    /* renamed from: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                androidx.constraintlayout.core.widgets.analyzer.WidgetRun$RunType[] r0 = androidx.constraintlayout.core.widgets.analyzer.WidgetRun.RunType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType = r0
                androidx.constraintlayout.core.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.core.widgets.analyzer.WidgetRun.RunType.START     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.core.widgets.analyzer.WidgetRun.RunType.END     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.widgets.analyzer.WidgetRun$RunType r1 = androidx.constraintlayout.core.widgets.analyzer.WidgetRun.RunType.CENTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.AnonymousClass1.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02cf, code lost:
        if (r7 != 1) goto L_0x0339;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r14) {
        /*
            r13 = this;
            int[] r1 = androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun$RunType r2 = r13.mRunType
            int r2 = r2.ordinal()
            r1 = r1[r2]
            r2 = 2
            r3 = 3
            r8 = 0
            r9 = 1
            if (r1 == r9) goto L_0x0025
            if (r1 == r2) goto L_0x0021
            if (r1 == r3) goto L_0x0015
            goto L_0x0028
        L_0x0015:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mLeft
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mRight
            r13.updateRunCenter(r14, r1, r2, r8)
            return
        L_0x0021:
            r13.updateRunEnd(r14)
            goto L_0x0028
        L_0x0025:
            r13.updateRunStart(r14)
        L_0x0028:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            r10 = 1056964608(0x3f000000, float:0.5)
            if (r1 != 0) goto L_0x0339
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r4) goto L_0x0339
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            int r1 = r1.mMatchConstraintDefaultWidth
            if (r1 == r2) goto L_0x0316
            if (r1 == r3) goto L_0x0040
            goto L_0x0339
        L_0x0040:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            int r1 = r1.mMatchConstraintDefaultHeight
            r2 = -1
            if (r1 == 0) goto L_0x0096
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            int r1 = r1.mMatchConstraintDefaultHeight
            if (r1 != r3) goto L_0x004e
            goto L_0x0096
        L_0x004e:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            int r1 = r1.getDimensionRatioSide()
            if (r1 == r2) goto L_0x007d
            if (r1 == 0) goto L_0x006c
            if (r1 == r9) goto L_0x005c
            r1 = r8
            goto L_0x008f
        L_0x005c:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int r1 = r1.value
            float r1 = (float) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            float r2 = r2.getDimensionRatio()
            goto L_0x008c
        L_0x006c:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int r1 = r1.value
            float r1 = (float) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            float r2 = r2.getDimensionRatio()
            float r1 = r1 / r2
            goto L_0x008d
        L_0x007d:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int r1 = r1.value
            float r1 = (float) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            float r2 = r2.getDimensionRatio()
        L_0x008c:
            float r1 = r1 * r2
        L_0x008d:
            float r1 = r1 + r10
            int r1 = (int) r1
        L_0x008f:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
            goto L_0x0339
        L_0x0096:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r11 = r1.start
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r12 = r1.end
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r1 = r1.mTarget
            if (r1 == 0) goto L_0x00ac
            r1 = r9
            goto L_0x00ad
        L_0x00ac:
            r1 = r8
        L_0x00ad:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r3.mTarget
            if (r3 == 0) goto L_0x00b7
            r3 = r9
            goto L_0x00b8
        L_0x00b7:
            r3 = r8
        L_0x00b8:
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.mTarget
            if (r4 == 0) goto L_0x00c2
            r4 = r9
            goto L_0x00c3
        L_0x00c2:
            r4 = r8
        L_0x00c3:
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.mTarget
            if (r5 == 0) goto L_0x00cd
            r5 = r9
            goto L_0x00ce
        L_0x00cd:
            r5 = r8
        L_0x00ce:
            androidx.constraintlayout.core.widgets.ConstraintWidget r6 = r13.widget
            int r7 = r6.getDimensionRatioSide()
            if (r1 == 0) goto L_0x0215
            if (r3 == 0) goto L_0x0215
            if (r4 == 0) goto L_0x0215
            if (r5 == 0) goto L_0x0215
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            float r6 = r1.getDimensionRatio()
            boolean r1 = r11.resolved
            if (r1 == 0) goto L_0x0143
            boolean r1 = r12.resolved
            if (r1 == 0) goto L_0x0143
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x0464
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.readyToSolve
            if (r1 != 0) goto L_0x00f8
            goto L_0x0464
        L_0x00f8:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.margin
            int r2 = r2 + r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r3 = r1 - r3
            int r1 = r11.value
            int r4 = r11.margin
            int r4 = r4 + r1
            int r1 = r12.value
            int r5 = r12.margin
            int r5 = r1 - r5
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int[] r2 = tempDimensions
            r2 = r2[r9]
            r1.resolve(r2)
            return
        L_0x0143:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x01a3
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x01a3
            boolean r1 = r11.readyToSolve
            if (r1 == 0) goto L_0x0464
            boolean r1 = r12.readyToSolve
            if (r1 != 0) goto L_0x0159
            goto L_0x0464
        L_0x0159:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.margin
            int r2 = r2 + r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r3 = r1 - r3
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r11.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r4 = r11.margin
            int r4 = r4 + r1
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r12.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r5 = r12.margin
            int r5 = r1 - r5
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int[] r2 = tempDimensions
            r2 = r2[r9]
            r1.resolve(r2)
        L_0x01a3:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x0464
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x0464
            boolean r1 = r11.readyToSolve
            if (r1 == 0) goto L_0x0464
            boolean r1 = r12.readyToSolve
            if (r1 != 0) goto L_0x01b9
            goto L_0x0464
        L_0x01b9:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.margin
            int r2 = r2 + r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r3 = r1 - r3
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r11.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r4 = r11.margin
            int r4 = r4 + r1
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r12.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            int r1 = r1.value
            int r5 = r12.margin
            int r5 = r1 - r5
            int[] r1 = tempDimensions
            r0 = r13
            r0.computeInsetRatio(r1, r2, r3, r4, r5, r6, r7)
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int[] r2 = tempDimensions
            r2 = r2[r8]
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int[] r2 = tempDimensions
            r2 = r2[r9]
            r1.resolve(r2)
            goto L_0x0339
        L_0x0215:
            if (r1 == 0) goto L_0x029d
            if (r4 == 0) goto L_0x029d
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x0464
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.readyToSolve
            if (r1 != 0) goto L_0x0227
            goto L_0x0464
        L_0x0227:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            float r1 = r1.getDimensionRatio()
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r3 = r3.targets
            java.lang.Object r3 = r3.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r3
            int r3 = r3.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r13.start
            int r4 = r4.margin
            int r3 = r3 + r4
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r4 = r4.targets
            java.lang.Object r4 = r4.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r4
            int r4 = r4.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r5 = r13.end
            int r5 = r5.margin
            int r4 = r4 - r5
            if (r7 == r2) goto L_0x027a
            if (r7 == 0) goto L_0x027a
            if (r7 == r9) goto L_0x0257
            goto L_0x0339
        L_0x0257:
            int r4 = r4 - r3
            int r2 = r13.getLimitedDimension(r4, r8)
            float r3 = (float) r2
            float r3 = r3 / r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r9)
            if (r3 == r4) goto L_0x026a
            float r2 = (float) r4
            float r2 = r2 * r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x026a:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r4)
            goto L_0x0339
        L_0x027a:
            int r4 = r4 - r3
            int r2 = r13.getLimitedDimension(r4, r8)
            float r3 = (float) r2
            float r3 = r3 * r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r9)
            if (r3 == r4) goto L_0x028d
            float r2 = (float) r4
            float r2 = r2 / r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x028d:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r4)
            goto L_0x0339
        L_0x029d:
            if (r3 == 0) goto L_0x0339
            if (r5 == 0) goto L_0x0339
            boolean r1 = r11.readyToSolve
            if (r1 == 0) goto L_0x0464
            boolean r1 = r12.readyToSolve
            if (r1 != 0) goto L_0x02ab
            goto L_0x0464
        L_0x02ab:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            float r1 = r1.getDimensionRatio()
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r3 = r11.targets
            java.lang.Object r3 = r3.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r3
            int r3 = r3.value
            int r4 = r11.margin
            int r3 = r3 + r4
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r4 = r12.targets
            java.lang.Object r4 = r4.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r4
            int r4 = r4.value
            int r5 = r12.margin
            int r4 = r4 - r5
            if (r7 == r2) goto L_0x02f4
            if (r7 == 0) goto L_0x02d2
            if (r7 == r9) goto L_0x02f4
            goto L_0x0339
        L_0x02d2:
            int r4 = r4 - r3
            int r2 = r13.getLimitedDimension(r4, r9)
            float r3 = (float) r2
            float r3 = r3 * r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r8)
            if (r3 == r4) goto L_0x02e5
            float r2 = (float) r4
            float r2 = r2 / r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x02e5:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r2)
            goto L_0x0339
        L_0x02f4:
            int r4 = r4 - r3
            int r2 = r13.getLimitedDimension(r4, r9)
            float r3 = (float) r2
            float r3 = r3 / r1
            float r3 = r3 + r10
            int r3 = (int) r3
            int r4 = r13.getLimitedDimension(r3, r8)
            if (r3 == r4) goto L_0x0307
            float r2 = (float) r4
            float r2 = r2 * r1
            float r2 = r2 + r10
            int r2 = (int) r2
        L_0x0307:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r1 = r1.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            r1.resolve(r2)
            goto L_0x0339
        L_0x0316:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            if (r1 == 0) goto L_0x0339
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r2 = r1.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r2.dimension
            boolean r2 = r2.resolved
            if (r2 == 0) goto L_0x0339
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            float r2 = r2.mMatchConstraintPercentWidth
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r1 = r1.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r1.dimension
            int r1 = r1.value
            float r1 = (float) r1
            float r1 = r1 * r2
            float r1 = r1 + r10
            int r1 = (int) r1
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
        L_0x0339:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.readyToSolve
            if (r1 == 0) goto L_0x0464
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.readyToSolve
            if (r1 != 0) goto L_0x0347
            goto L_0x0464
        L_0x0347:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x035b
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x035b
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x035b
            goto L_0x0464
        L_0x035b:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x03a9
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r2) goto L_0x03a9
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            int r1 = r1.mMatchConstraintDefaultWidth
            if (r1 != 0) goto L_0x03a9
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r13.widget
            boolean r1 = r1.isInHorizontalChain()
            if (r1 != 0) goto L_0x03a9
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r2
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.start
            int r3 = r3.margin
            int r1 = r1 + r3
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r2 = r2 + r3
            int r3 = r2 - r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r13.start
            r4.resolve(r1)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            r1.resolve(r3)
            return
        L_0x03a9:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x040f
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r13.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r2) goto L_0x040f
            int r1 = r13.matchConstraintsType
            if (r1 != r9) goto L_0x040f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x040f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x040f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r2
            int r1 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.start
            int r3 = r3.margin
            int r1 = r1 + r3
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r13.end
            int r3 = r3.margin
            int r2 = r2 + r3
            int r2 = r2 - r1
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r1 = r1.wrapValue
            int r1 = java.lang.Math.min(r2, r1)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r13.widget
            int r2 = r2.mMatchConstraintMaxWidth
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r13.widget
            int r3 = r3.mMatchConstraintMinWidth
            int r1 = java.lang.Math.max(r3, r1)
            if (r2 <= 0) goto L_0x040a
            int r1 = java.lang.Math.min(r2, r1)
        L_0x040a:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r13.dimension
            r2.resolve(r1)
        L_0x040f:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0416
            goto L_0x0464
        L_0x0416:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r1 = r1.targets
            java.lang.Object r1 = r1.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.end
            java.util.List<androidx.constraintlayout.core.widgets.analyzer.DependencyNode> r2 = r2.targets
            java.lang.Object r2 = r2.get(r8)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = (androidx.constraintlayout.core.widgets.analyzer.DependencyNode) r2
            int r3 = r1.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r13.start
            int r4 = r4.margin
            int r3 = r3 + r4
            int r4 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r5 = r13.end
            int r5 = r5.margin
            int r4 = r4 + r5
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r13.widget
            float r5 = r5.getHorizontalBiasPercent()
            if (r1 != r2) goto L_0x0445
            int r3 = r1.value
            int r4 = r2.value
            r5 = r10
        L_0x0445:
            int r4 = r4 - r3
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r13.dimension
            int r1 = r1.value
            int r4 = r4 - r1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.start
            float r2 = (float) r3
            float r2 = r2 + r10
            float r3 = (float) r4
            float r3 = r3 * r5
            float r2 = r2 + r3
            int r2 = (int) r2
            r1.resolve(r2)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r13.end
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r13.start
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r13.dimension
            int r3 = r3.value
            int r2 = r2 + r3
            r1.resolve(r2)
        L_0x0464:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        if (this.start.resolved) {
            this.widget.setX(this.start.value);
        }
    }
}
