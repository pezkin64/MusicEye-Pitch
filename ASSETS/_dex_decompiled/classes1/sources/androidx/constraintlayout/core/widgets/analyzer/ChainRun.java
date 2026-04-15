package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class ChainRun extends WidgetRun {
    private int chainStyle;
    ArrayList<WidgetRun> widgets = new ArrayList<>();

    public ChainRun(ConstraintWidget constraintWidget, int i) {
        super(constraintWidget);
        this.orientation = i;
        build();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append(it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean supportsWrapComputation() {
        int size = this.widgets.size();
        for (int i = 0; i < size; i++) {
            if (!this.widgets.get(i).supportsWrapComputation()) {
                return false;
            }
        }
        return true;
    }

    public long getWrapDimension() {
        int size = this.widgets.size();
        long j = 0;
        for (int i = 0; i < size; i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            j = j + ((long) widgetRun.start.margin) + widgetRun.getWrapDimension() + ((long) widgetRun.end.margin);
        }
        return j;
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.widget;
        ConstraintWidget previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        while (true) {
            ConstraintWidget constraintWidget3 = previousChainMember;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            }
            previousChainMember = constraintWidget2.getPreviousChainMember(this.orientation);
        }
        this.widget = constraintWidget;
        this.widgets.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.widgets.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            WidgetRun next = it.next();
            if (this.orientation == 0) {
                next.widget.horizontalChainRun = this;
            } else if (this.orientation == 1) {
                next.widget.verticalChainRun = this;
            }
        }
        if (this.orientation == 0 && ((ConstraintWidgetContainer) this.widget.getParent()).isRtl() && this.widgets.size() > 1) {
            ArrayList<WidgetRun> arrayList = this.widgets;
            this.widget = arrayList.get(arrayList.size() - 1).widget;
        }
        this.chainStyle = this.orientation == 0 ? this.widget.getHorizontalChainStyle() : this.widget.getVerticalChainStyle();
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.runGroup = null;
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d1, code lost:
        if (r10.dimension.resolved != false) goto L_0x00d3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void update(androidx.constraintlayout.core.widgets.analyzer.Dependency r26) {
        /*
            r25 = this;
            r0 = r25
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.start
            boolean r1 = r1.resolved
            if (r1 == 0) goto L_0x0419
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r0.end
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x0010
            goto L_0x0419
        L_0x0010:
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r0.widget
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r1.getParent()
            boolean r2 = r1 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x0021
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r1 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r1
            boolean r1 = r1.isRtl()
            goto L_0x0022
        L_0x0021:
            r1 = 0
        L_0x0022:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r0.end
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r4 = r0.start
            int r4 = r4.value
            int r2 = r2 - r4
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r4 = r0.widgets
            int r4 = r4.size()
            r5 = 0
        L_0x0032:
            r6 = -1
            r7 = 8
            if (r5 >= r4) goto L_0x004a
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r8 = r0.widgets
            java.lang.Object r8 = r8.get(r5)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r8 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.widget
            int r8 = r8.getVisibility()
            if (r8 != r7) goto L_0x004b
            int r5 = r5 + 1
            goto L_0x0032
        L_0x004a:
            r5 = r6
        L_0x004b:
            int r8 = r4 + -1
            r9 = r8
        L_0x004e:
            if (r9 < 0) goto L_0x0064
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r9)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r10.widget
            int r10 = r10.getVisibility()
            if (r10 != r7) goto L_0x0063
            int r9 = r9 + -1
            goto L_0x004e
        L_0x0063:
            r6 = r9
        L_0x0064:
            r9 = 0
        L_0x0065:
            r11 = 2
            r12 = 1
            if (r9 >= r11) goto L_0x0109
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r17 = 0
        L_0x0070:
            if (r13 >= r4) goto L_0x00f9
            r26 = 0
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r10 = r0.widgets
            java.lang.Object r10 = r10.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r10 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r10
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r10.widget
            int r3 = r3.getVisibility()
            if (r3 != r7) goto L_0x0086
            goto L_0x00f2
        L_0x0086:
            int r16 = r16 + 1
            if (r13 <= 0) goto L_0x0091
            if (r13 < r5) goto L_0x0091
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r10.start
            int r3 = r3.margin
            int r14 = r14 + r3
        L_0x0091:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r10.dimension
            int r3 = r3.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = r10.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r7 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r11 == r7) goto L_0x009d
            r7 = r12
            goto L_0x009e
        L_0x009d:
            r7 = 0
        L_0x009e:
            if (r7 == 0) goto L_0x00c0
            int r11 = r0.orientation
            if (r11 != 0) goto L_0x00b0
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r10.widget
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r11 = r11.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r11.dimension
            boolean r11 = r11.resolved
            if (r11 != 0) goto L_0x00b0
            goto L_0x0419
        L_0x00b0:
            int r11 = r0.orientation
            if (r11 != r12) goto L_0x00d4
            androidx.constraintlayout.core.widgets.ConstraintWidget r11 = r10.widget
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r11 = r11.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r11.dimension
            boolean r11 = r11.resolved
            if (r11 != 0) goto L_0x00d4
            goto L_0x0419
        L_0x00c0:
            int r11 = r10.matchConstraintsType
            if (r11 != r12) goto L_0x00cd
            if (r9 != 0) goto L_0x00cd
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r3 = r10.dimension
            int r3 = r3.wrapValue
            int r15 = r15 + 1
            goto L_0x00d3
        L_0x00cd:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r11 = r10.dimension
            boolean r11 = r11.resolved
            if (r11 == 0) goto L_0x00d4
        L_0x00d3:
            r7 = r12
        L_0x00d4:
            if (r7 != 0) goto L_0x00e7
            int r15 = r15 + 1
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r10.widget
            float[] r3 = r3.mWeight
            int r7 = r0.orientation
            r3 = r3[r7]
            int r7 = (r3 > r26 ? 1 : (r3 == r26 ? 0 : -1))
            if (r7 < 0) goto L_0x00e8
            float r17 = r17 + r3
            goto L_0x00e8
        L_0x00e7:
            int r14 = r14 + r3
        L_0x00e8:
            if (r13 >= r8) goto L_0x00f2
            if (r13 >= r6) goto L_0x00f2
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r3 = r10.end
            int r3 = r3.margin
            int r3 = -r3
            int r14 = r14 + r3
        L_0x00f2:
            int r13 = r13 + 1
            r7 = 8
            r11 = 2
            goto L_0x0070
        L_0x00f9:
            r26 = 0
            if (r14 < r2) goto L_0x0106
            if (r15 != 0) goto L_0x0100
            goto L_0x0106
        L_0x0100:
            int r9 = r9 + 1
            r7 = 8
            goto L_0x0065
        L_0x0106:
            r3 = r16
            goto L_0x0110
        L_0x0109:
            r26 = 0
            r17 = r26
            r3 = 0
            r14 = 0
            r15 = 0
        L_0x0110:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.start
            int r7 = r7.value
            if (r1 == 0) goto L_0x011a
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r7 = r0.end
            int r7 = r7.value
        L_0x011a:
            r9 = 1056964608(0x3f000000, float:0.5)
            if (r14 <= r2) goto L_0x0131
            r10 = 1073741824(0x40000000, float:2.0)
            if (r1 == 0) goto L_0x012a
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 + r10
            goto L_0x0131
        L_0x012a:
            int r11 = r14 - r2
            float r11 = (float) r11
            float r11 = r11 / r10
            float r11 = r11 + r9
            int r10 = (int) r11
            int r7 = r7 - r10
        L_0x0131:
            if (r15 <= 0) goto L_0x0217
            int r10 = r2 - r14
            float r10 = (float) r10
            float r11 = (float) r15
            float r11 = r10 / r11
            float r11 = r11 + r9
            int r11 = (int) r11
            r13 = 0
            r16 = 0
        L_0x013e:
            if (r13 >= r4) goto L_0x01cd
            r19 = r9
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r9 = r9.get(r13)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r9.widget
            int r12 = r12.getVisibility()
            r21 = r1
            r1 = 8
            if (r12 != r1) goto L_0x0157
            goto L_0x01b8
        L_0x0157:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = r9.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r12) goto L_0x01b8
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r1 = r9.dimension
            boolean r1 = r1.resolved
            if (r1 != 0) goto L_0x01b8
            int r1 = (r17 > r26 ? 1 : (r17 == r26 ? 0 : -1))
            if (r1 <= 0) goto L_0x0176
            androidx.constraintlayout.core.widgets.ConstraintWidget r1 = r9.widget
            float[] r1 = r1.mWeight
            int r12 = r0.orientation
            r1 = r1[r12]
            float r1 = r1 * r10
            float r1 = r1 / r17
            float r1 = r1 + r19
            int r1 = (int) r1
            goto L_0x0177
        L_0x0176:
            r1 = r11
        L_0x0177:
            int r12 = r0.orientation
            if (r12 != 0) goto L_0x0186
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r9.widget
            int r12 = r12.mMatchConstraintMaxWidth
            r22 = r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r9.widget
            int r7 = r7.mMatchConstraintMinWidth
            goto L_0x0190
        L_0x0186:
            r22 = r7
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r9.widget
            int r12 = r7.mMatchConstraintMaxHeight
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r9.widget
            int r7 = r7.mMatchConstraintMinHeight
        L_0x0190:
            r23 = r10
            int r10 = r9.matchConstraintsType
            r24 = r11
            r11 = 1
            if (r10 != r11) goto L_0x01a2
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.wrapValue
            int r10 = java.lang.Math.min(r1, r10)
            goto L_0x01a3
        L_0x01a2:
            r10 = r1
        L_0x01a3:
            int r7 = java.lang.Math.max(r7, r10)
            if (r12 <= 0) goto L_0x01ad
            int r7 = java.lang.Math.min(r12, r7)
        L_0x01ad:
            if (r7 == r1) goto L_0x01b2
            int r16 = r16 + 1
            r1 = r7
        L_0x01b2:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r7 = r9.dimension
            r7.resolve(r1)
            goto L_0x01be
        L_0x01b8:
            r22 = r7
            r23 = r10
            r24 = r11
        L_0x01be:
            int r13 = r13 + 1
            r9 = r19
            r1 = r21
            r7 = r22
            r10 = r23
            r11 = r24
            r12 = 1
            goto L_0x013e
        L_0x01cd:
            r21 = r1
            r22 = r7
            r19 = r9
            if (r16 <= 0) goto L_0x020a
            int r15 = r15 - r16
            r1 = 0
            r7 = 0
        L_0x01d9:
            if (r1 >= r4) goto L_0x0209
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r9 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r9 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r9
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r9.widget
            int r10 = r10.getVisibility()
            r11 = 8
            if (r10 != r11) goto L_0x01ee
            goto L_0x0206
        L_0x01ee:
            if (r1 <= 0) goto L_0x01f7
            if (r1 < r5) goto L_0x01f7
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r10 = r9.start
            int r10 = r10.margin
            int r7 = r7 + r10
        L_0x01f7:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r9.dimension
            int r10 = r10.value
            int r7 = r7 + r10
            if (r1 >= r8) goto L_0x0206
            if (r1 >= r6) goto L_0x0206
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r9.end
            int r9 = r9.margin
            int r9 = -r9
            int r7 = r7 + r9
        L_0x0206:
            int r1 = r1 + 1
            goto L_0x01d9
        L_0x0209:
            r14 = r7
        L_0x020a:
            int r1 = r0.chainStyle
            r7 = 2
            if (r1 != r7) goto L_0x0215
            if (r16 != 0) goto L_0x0215
            r1 = 0
            r0.chainStyle = r1
            goto L_0x021f
        L_0x0215:
            r1 = 0
            goto L_0x021f
        L_0x0217:
            r21 = r1
            r22 = r7
            r19 = r9
            r1 = 0
            r7 = 2
        L_0x021f:
            if (r14 <= r2) goto L_0x0223
            r0.chainStyle = r7
        L_0x0223:
            if (r3 <= 0) goto L_0x022b
            if (r15 != 0) goto L_0x022b
            if (r5 != r6) goto L_0x022b
            r0.chainStyle = r7
        L_0x022b:
            int r7 = r0.chainStyle
            r11 = 1
            if (r7 != r11) goto L_0x02d0
            if (r3 <= r11) goto L_0x0236
            int r2 = r2 - r14
            int r3 = r3 - r11
            int r2 = r2 / r3
            goto L_0x023f
        L_0x0236:
            if (r3 != r11) goto L_0x023e
            int r2 = r2 - r14
            r18 = 2
            int r2 = r2 / 2
            goto L_0x023f
        L_0x023e:
            r2 = r1
        L_0x023f:
            if (r15 <= 0) goto L_0x0242
            r2 = r1
        L_0x0242:
            r3 = r1
            r7 = r22
        L_0x0245:
            if (r3 >= r4) goto L_0x0419
            if (r21 == 0) goto L_0x024e
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x024f
        L_0x024e:
            r1 = r3
        L_0x024f:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x026c
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x02cc
        L_0x026c:
            if (r3 <= 0) goto L_0x0273
            if (r21 == 0) goto L_0x0272
            int r7 = r7 - r2
            goto L_0x0273
        L_0x0272:
            int r7 = r7 + r2
        L_0x0273:
            if (r3 <= 0) goto L_0x0284
            if (r3 < r5) goto L_0x0284
            if (r21 == 0) goto L_0x027f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x0284
        L_0x027f:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x0284:
            if (r21 == 0) goto L_0x028c
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x0291
        L_0x028c:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x0291:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r9 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r11) goto L_0x02a4
            int r10 = r1.matchConstraintsType
            r11 = 1
            if (r10 != r11) goto L_0x02a4
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r9 = r9.wrapValue
        L_0x02a4:
            if (r21 == 0) goto L_0x02a8
            int r7 = r7 - r9
            goto L_0x02a9
        L_0x02a8:
            int r7 = r7 + r9
        L_0x02a9:
            if (r21 == 0) goto L_0x02b1
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            goto L_0x02b6
        L_0x02b1:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
        L_0x02b6:
            r11 = 1
            r1.resolved = r11
            if (r3 >= r8) goto L_0x02cc
            if (r3 >= r6) goto L_0x02cc
            if (r21 == 0) goto L_0x02c6
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x02cc
        L_0x02c6:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x02cc:
            int r3 = r3 + 1
            goto L_0x0245
        L_0x02d0:
            if (r7 != 0) goto L_0x0368
            int r2 = r2 - r14
            r20 = 1
            int r3 = r3 + 1
            int r2 = r2 / r3
            if (r15 <= 0) goto L_0x02db
            r2 = r1
        L_0x02db:
            r3 = r1
            r7 = r22
        L_0x02de:
            if (r3 >= r4) goto L_0x0419
            if (r21 == 0) goto L_0x02e7
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x02e8
        L_0x02e7:
            r1 = r3
        L_0x02e8:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r9 = r0.widgets
            java.lang.Object r1 = r9.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r1.widget
            int r9 = r9.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x0305
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            goto L_0x0364
        L_0x0305:
            if (r21 == 0) goto L_0x0309
            int r7 = r7 - r2
            goto L_0x030a
        L_0x0309:
            int r7 = r7 + r2
        L_0x030a:
            if (r3 <= 0) goto L_0x031b
            if (r3 < r5) goto L_0x031b
            if (r21 == 0) goto L_0x0316
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 - r9
            goto L_0x031b
        L_0x0316:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            int r9 = r9.margin
            int r7 = r7 + r9
        L_0x031b:
            if (r21 == 0) goto L_0x0323
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
            goto L_0x0328
        L_0x0323:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
        L_0x0328:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r9 = r1.dimension
            int r9 = r9.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r10 != r11) goto L_0x033f
            int r10 = r1.matchConstraintsType
            r11 = 1
            if (r10 != r11) goto L_0x033f
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r10 = r1.dimension
            int r10 = r10.wrapValue
            int r9 = java.lang.Math.min(r9, r10)
        L_0x033f:
            if (r21 == 0) goto L_0x0343
            int r7 = r7 - r9
            goto L_0x0344
        L_0x0343:
            int r7 = r7 + r9
        L_0x0344:
            if (r21 == 0) goto L_0x034c
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.start
            r9.resolve(r7)
            goto L_0x0351
        L_0x034c:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r9 = r1.end
            r9.resolve(r7)
        L_0x0351:
            if (r3 >= r8) goto L_0x0364
            if (r3 >= r6) goto L_0x0364
            if (r21 == 0) goto L_0x035e
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0364
        L_0x035e:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0364:
            int r3 = r3 + 1
            goto L_0x02de
        L_0x0368:
            r3 = 2
            if (r7 != r3) goto L_0x0419
            int r3 = r0.orientation
            if (r3 != 0) goto L_0x0376
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getHorizontalBiasPercent()
            goto L_0x037c
        L_0x0376:
            androidx.constraintlayout.core.widgets.ConstraintWidget r3 = r0.widget
            float r3 = r3.getVerticalBiasPercent()
        L_0x037c:
            if (r21 == 0) goto L_0x0382
            r7 = 1065353216(0x3f800000, float:1.0)
            float r3 = r7 - r3
        L_0x0382:
            int r2 = r2 - r14
            float r2 = (float) r2
            float r2 = r2 * r3
            float r2 = r2 + r19
            int r2 = (int) r2
            if (r2 < 0) goto L_0x038c
            if (r15 <= 0) goto L_0x038d
        L_0x038c:
            r2 = r1
        L_0x038d:
            if (r21 == 0) goto L_0x0392
            int r7 = r22 - r2
            goto L_0x0394
        L_0x0392:
            int r7 = r22 + r2
        L_0x0394:
            r3 = r1
        L_0x0395:
            if (r3 >= r4) goto L_0x0419
            if (r21 == 0) goto L_0x039e
            int r1 = r3 + 1
            int r1 = r4 - r1
            goto L_0x039f
        L_0x039e:
            r1 = r3
        L_0x039f:
            java.util.ArrayList<androidx.constraintlayout.core.widgets.analyzer.WidgetRun> r2 = r0.widgets
            java.lang.Object r1 = r2.get(r1)
            androidx.constraintlayout.core.widgets.analyzer.WidgetRun r1 = (androidx.constraintlayout.core.widgets.analyzer.WidgetRun) r1
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r1.widget
            int r2 = r2.getVisibility()
            r11 = 8
            if (r2 != r11) goto L_0x03bd
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            r1.resolve(r7)
            r10 = 1
            goto L_0x0415
        L_0x03bd:
            if (r3 <= 0) goto L_0x03ce
            if (r3 < r5) goto L_0x03ce
            if (r21 == 0) goto L_0x03c9
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 - r2
            goto L_0x03ce
        L_0x03c9:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            int r2 = r2.margin
            int r7 = r7 + r2
        L_0x03ce:
            if (r21 == 0) goto L_0x03d6
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
            goto L_0x03db
        L_0x03d6:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
        L_0x03db:
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r1.dimension
            int r2 = r2.value
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = r1.dimensionBehavior
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r10 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r9 != r10) goto L_0x03ef
            int r9 = r1.matchConstraintsType
            r10 = 1
            if (r9 != r10) goto L_0x03f0
            androidx.constraintlayout.core.widgets.analyzer.DimensionDependency r2 = r1.dimension
            int r2 = r2.wrapValue
            goto L_0x03f0
        L_0x03ef:
            r10 = 1
        L_0x03f0:
            if (r21 == 0) goto L_0x03f4
            int r7 = r7 - r2
            goto L_0x03f5
        L_0x03f4:
            int r7 = r7 + r2
        L_0x03f5:
            if (r21 == 0) goto L_0x03fd
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.start
            r2.resolve(r7)
            goto L_0x0402
        L_0x03fd:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r1.end
            r2.resolve(r7)
        L_0x0402:
            if (r3 >= r8) goto L_0x0415
            if (r3 >= r6) goto L_0x0415
            if (r21 == 0) goto L_0x040f
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 - r1
            goto L_0x0415
        L_0x040f:
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r1 = r1.end
            int r1 = r1.margin
            int r1 = -r1
            int r7 = r7 + r1
        L_0x0415:
            int r3 = r3 + 1
            goto L_0x0395
        L_0x0419:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.update(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    public void applyToWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            this.widgets.get(i).applyToWidget();
        }
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i = 0; i < this.widgets.size(); i++) {
            WidgetRun widgetRun = this.widgets.get(i);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.widgets.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = this.widgets.get(size);
            if (widgetRun.widget.getVisibility() != 8) {
                return widgetRun.widget;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void apply() {
        Iterator<WidgetRun> it = this.widgets.iterator();
        while (it.hasNext()) {
            it.next().apply();
        }
        int size = this.widgets.size();
        if (size >= 1) {
            ConstraintWidget constraintWidget = this.widgets.get(0).widget;
            ConstraintWidget constraintWidget2 = this.widgets.get(size - 1).widget;
            if (this.orientation == 0) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
                DependencyNode target = getTarget(constraintAnchor, 0);
                int margin = constraintAnchor.getMargin();
                ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
                if (firstVisibleWidget != null) {
                    margin = firstVisibleWidget.mLeft.getMargin();
                }
                if (target != null) {
                    addTarget(this.start, target, margin);
                }
                DependencyNode target2 = getTarget(constraintAnchor2, 0);
                int margin2 = constraintAnchor2.getMargin();
                ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
                if (lastVisibleWidget != null) {
                    margin2 = lastVisibleWidget.mRight.getMargin();
                }
                if (target2 != null) {
                    addTarget(this.end, target2, -margin2);
                }
            } else {
                ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
                DependencyNode target3 = getTarget(constraintAnchor3, 1);
                int margin3 = constraintAnchor3.getMargin();
                ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
                if (firstVisibleWidget2 != null) {
                    margin3 = firstVisibleWidget2.mTop.getMargin();
                }
                if (target3 != null) {
                    addTarget(this.start, target3, margin3);
                }
                DependencyNode target4 = getTarget(constraintAnchor4, 1);
                int margin4 = constraintAnchor4.getMargin();
                ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
                if (lastVisibleWidget2 != null) {
                    margin4 = lastVisibleWidget2.mBottom.getMargin();
                }
                if (target4 != null) {
                    addTarget(this.end, target4, -margin4);
                }
            }
            this.start.updateDelegate = this;
            this.end.updateDelegate = this;
        }
    }
}
