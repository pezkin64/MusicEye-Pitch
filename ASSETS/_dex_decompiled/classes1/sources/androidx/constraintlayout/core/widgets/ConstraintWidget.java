package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.state.WidgetFrame;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class ConstraintWidget {
    public static final int ANCHOR_BASELINE = 4;
    public static final int ANCHOR_BOTTOM = 3;
    public static final int ANCHOR_LEFT = 0;
    public static final int ANCHOR_RIGHT = 1;
    public static final int ANCHOR_TOP = 2;
    private static final boolean AUTOTAG_CENTER = false;
    public static final int BOTH = 2;
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static float DEFAULT_BIAS = 0.5f;
    static final int DIMENSION_HORIZONTAL = 0;
    static final int DIMENSION_VERTICAL = 1;
    protected static final int DIRECT = 2;
    public static final int GONE = 8;
    public static final int HORIZONTAL = 0;
    public static final int INVISIBLE = 4;
    public static final int MATCH_CONSTRAINT_PERCENT = 2;
    public static final int MATCH_CONSTRAINT_RATIO = 3;
    public static final int MATCH_CONSTRAINT_RATIO_RESOLVED = 4;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    protected static final int SOLVER = 1;
    public static final int UNKNOWN = -1;
    private static final boolean USE_WRAP_DIMENSION_FOR_SPREAD = false;
    public static final int VERTICAL = 1;
    public static final int VISIBLE = 0;
    private static final int WRAP = -2;
    public static final int WRAP_BEHAVIOR_HORIZONTAL_ONLY = 1;
    public static final int WRAP_BEHAVIOR_INCLUDED = 0;
    public static final int WRAP_BEHAVIOR_SKIPPED = 3;
    public static final int WRAP_BEHAVIOR_VERTICAL_ONLY = 2;
    private boolean OPTIMIZE_WRAP;
    private boolean OPTIMIZE_WRAP_ON_RESOLVED;
    public WidgetFrame frame;
    private boolean hasBaseline;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    public HorizontalWidgetRun horizontalRun;
    private boolean horizontalSolvingPass;
    private boolean inPlaceholder;
    public boolean[] isTerminalWidget;
    protected ArrayList<ConstraintAnchor> mAnchors;
    private boolean mAnimated;
    public ConstraintAnchor mBaseline;
    int mBaselineDistance;
    public ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    public ConstraintAnchor mCenter;
    ConstraintAnchor mCenterX;
    ConstraintAnchor mCenterY;
    private float mCircleConstraintAngle;
    private Object mCompanionWidget;
    private int mContainerItemSkip;
    private String mDebugName;
    public float mDimensionRatio;
    protected int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    boolean mGroupsToSolver;
    int mHeight;
    private int mHeightOverride;
    float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle;
    ConstraintWidget mHorizontalNextWidget;
    public int mHorizontalResolution;
    boolean mHorizontalWrapVisited;
    private boolean mInVirtualLayout;
    public boolean mIsHeightWrapContent;
    private boolean[] mIsInBarrier;
    public boolean mIsWidthWrapContent;
    private int mLastHorizontalMeasureSpec;
    private int mLastVerticalMeasureSpec;
    public ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    public ConstraintAnchor[] mListAnchors;
    public DimensionBehaviour[] mListDimensionBehaviors;
    protected ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    private int[] mMaxDimension;
    private boolean mMeasureRequested;
    protected int mMinHeight;
    protected int mMinWidth;
    protected ConstraintWidget[] mNextChainWidget;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    int mRelX;
    int mRelY;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    boolean mResolvedHasRatio;
    public int[] mResolvedMatchConstraintDefault;
    public ConstraintAnchor mRight;
    boolean mRightHasCentered;
    public ConstraintAnchor mTop;
    boolean mTopHasCentered;
    private String mType;
    float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle;
    ConstraintWidget mVerticalNextWidget;
    public int mVerticalResolution;
    boolean mVerticalWrapVisited;
    private int mVisibility;
    public float[] mWeight;
    int mWidth;
    private int mWidthOverride;
    private int mWrapBehaviorInParent;
    protected int mX;
    protected int mY;
    public boolean measured;
    private boolean resolvedHorizontal;
    private boolean resolvedVertical;
    public WidgetRun[] run;
    public String stringId;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public VerticalWidgetRun verticalRun;
    private boolean verticalSolvingPass;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public WidgetRun getRun(int i) {
        if (i == 0) {
            return this.horizontalRun;
        }
        if (i == 1) {
            return this.verticalRun;
        }
        return null;
    }

    public void setFinalFrame(int i, int i2, int i3, int i4, int i5, int i6) {
        setFrame(i, i2, i3, i4);
        setBaselineDistance(i5);
        if (i6 == 0) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = false;
        } else if (i6 == 1) {
            this.resolvedHorizontal = false;
            this.resolvedVertical = true;
        } else if (i6 == 2) {
            this.resolvedHorizontal = true;
            this.resolvedVertical = true;
        } else {
            this.resolvedHorizontal = false;
            this.resolvedVertical = false;
        }
    }

    public void setFinalLeft(int i) {
        this.mLeft.setFinalValue(i);
        this.mX = i;
    }

    public void setFinalTop(int i) {
        this.mTop.setFinalValue(i);
        this.mY = i;
    }

    public void resetSolvingPassFlag() {
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
    }

    public boolean isHorizontalSolvingPassDone() {
        return this.horizontalSolvingPass;
    }

    public boolean isVerticalSolvingPassDone() {
        return this.verticalSolvingPass;
    }

    public void markHorizontalSolvingPassDone() {
        this.horizontalSolvingPass = true;
    }

    public void markVerticalSolvingPassDone() {
        this.verticalSolvingPass = true;
    }

    public void setFinalHorizontal(int i, int i2) {
        if (!this.resolvedHorizontal) {
            this.mLeft.setFinalValue(i);
            this.mRight.setFinalValue(i2);
            this.mX = i;
            this.mWidth = i2 - i;
            this.resolvedHorizontal = true;
        }
    }

    public void setFinalVertical(int i, int i2) {
        if (!this.resolvedVertical) {
            this.mTop.setFinalValue(i);
            this.mBottom.setFinalValue(i2);
            this.mY = i;
            this.mHeight = i2 - i;
            if (this.hasBaseline) {
                this.mBaseline.setFinalValue(i + this.mBaselineDistance);
            }
            this.resolvedVertical = true;
        }
    }

    public void setFinalBaseline(int i) {
        if (this.hasBaseline) {
            int i2 = i - this.mBaselineDistance;
            int i3 = this.mHeight + i2;
            this.mY = i2;
            this.mTop.setFinalValue(i2);
            this.mBottom.setFinalValue(i3);
            this.mBaseline.setFinalValue(i);
            this.resolvedVertical = true;
        }
    }

    public boolean isResolvedHorizontally() {
        if (!this.resolvedHorizontal) {
            return this.mLeft.hasFinalValue() && this.mRight.hasFinalValue();
        }
        return true;
    }

    public boolean isResolvedVertically() {
        if (!this.resolvedVertical) {
            return this.mTop.hasFinalValue() && this.mBottom.hasFinalValue();
        }
        return true;
    }

    public void resetFinalResolution() {
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            this.mAnchors.get(i).resetFinalResolution();
        }
    }

    public void ensureMeasureRequested() {
        this.mMeasureRequested = true;
    }

    public boolean hasDependencies() {
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            if (this.mAnchors.get(i).hasDependents()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasDanglingDimension(int i) {
        if (i == 0) {
            return (this.mLeft.mTarget != null ? 1 : 0) + (this.mRight.mTarget != null ? 1 : 0) < 2;
        }
        return ((this.mTop.mTarget != null ? 1 : 0) + (this.mBottom.mTarget != null ? 1 : 0)) + (this.mBaseline.mTarget != null ? 1 : 0) < 2;
    }

    public boolean hasResolvedTargets(int i, int i2) {
        return i == 0 ? this.mLeft.mTarget != null && this.mLeft.mTarget.hasFinalValue() && this.mRight.mTarget != null && this.mRight.mTarget.hasFinalValue() && (this.mRight.mTarget.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i2 : this.mTop.mTarget != null && this.mTop.mTarget.hasFinalValue() && this.mBottom.mTarget != null && this.mBottom.mTarget.hasFinalValue() && (this.mBottom.mTarget.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i2;
    }

    public boolean isInVirtualLayout() {
        return this.mInVirtualLayout;
    }

    public void setInVirtualLayout(boolean z) {
        this.mInVirtualLayout = z;
    }

    public int getMaxHeight() {
        return this.mMaxDimension[1];
    }

    public int getMaxWidth() {
        return this.mMaxDimension[0];
    }

    public void setMaxWidth(int i) {
        this.mMaxDimension[0] = i;
    }

    public void setMaxHeight(int i) {
        this.mMaxDimension[1] = i;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void setHasBaseline(boolean z) {
        this.hasBaseline = z;
    }

    public boolean getHasBaseline() {
        return this.hasBaseline;
    }

    public boolean isInPlaceholder() {
        return this.inPlaceholder;
    }

    public void setInPlaceholder(boolean z) {
        this.inPlaceholder = z;
    }

    /* access modifiers changed from: protected */
    public void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public boolean isInBarrier(int i) {
        return this.mIsInBarrier[i];
    }

    public void setMeasureRequested(boolean z) {
        this.mMeasureRequested = z;
    }

    public boolean isMeasureRequested() {
        return this.mMeasureRequested && this.mVisibility != 8;
    }

    public void setWrapBehaviorInParent(int i) {
        if (i >= 0 && i <= 3) {
            this.mWrapBehaviorInParent = i;
        }
    }

    public int getWrapBehaviorInParent() {
        return this.mWrapBehaviorInParent;
    }

    public int getLastHorizontalMeasureSpec() {
        return this.mLastHorizontalMeasureSpec;
    }

    public int getLastVerticalMeasureSpec() {
        return this.mLastVerticalMeasureSpec;
    }

    public void setLastMeasureSpec(int i, int i2) {
        this.mLastHorizontalMeasureSpec = i;
        this.mLastVerticalMeasureSpec = i2;
        setMeasureRequested(false);
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr = this.mMaxDimension;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedHasRatio = false;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mGroupsToSolver = false;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr2 = this.mResolvedMatchConstraintDefault;
        iArr2[0] = 0;
        iArr2[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    private void serializeAnchor(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget != null) {
            sb.append(str);
            sb.append(" : [ '");
            sb.append(constraintAnchor.mTarget);
            sb.append("',");
            sb.append(constraintAnchor.mMargin);
            sb.append(",");
            sb.append(constraintAnchor.mGoneMargin);
            sb.append(",");
            sb.append(" ] ,\n");
        }
    }

    private void serializeCircle(StringBuilder sb, ConstraintAnchor constraintAnchor, float f) {
        if (constraintAnchor.mTarget != null) {
            sb.append("circle : [ '");
            sb.append(constraintAnchor.mTarget);
            sb.append("',");
            sb.append(constraintAnchor.mMargin);
            sb.append(",");
            sb.append(f);
            sb.append(",");
            sb.append(" ] ,\n");
        }
    }

    private void serializeAttribute(StringBuilder sb, String str, float f, float f2) {
        if (f != f2) {
            sb.append(str);
            sb.append(" :   ");
            sb.append(f);
            sb.append(",\n");
        }
    }

    private void serializeAttribute(StringBuilder sb, String str, int i, int i2) {
        if (i != i2) {
            sb.append(str);
            sb.append(" :   ");
            sb.append(i);
            sb.append(",\n");
        }
    }

    private void serializeDimensionRatio(StringBuilder sb, String str, float f, int i) {
        if (f != 0.0f) {
            sb.append(str);
            sb.append(" :  [");
            sb.append(f);
            sb.append(",");
            sb.append(i);
            sb.append("");
            sb.append("],\n");
        }
    }

    private void serializeSize(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "size", i, Integer.MIN_VALUE);
        serializeAttribute(sb, "min", i2, 0);
        serializeAttribute(sb, "max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "matchMin", i5, 0);
        serializeAttribute(sb, "matchDef", i6, 0);
        serializeAttribute(sb, "matchPercent", i6, 1);
        sb.append("},\n");
    }

    public StringBuilder serialize(StringBuilder sb) {
        sb.append("{\n");
        serializeAnchor(sb, "left", this.mLeft);
        serializeAnchor(sb, "top", this.mTop);
        serializeAnchor(sb, "right", this.mRight);
        serializeAnchor(sb, "bottom", this.mBottom);
        serializeAnchor(sb, "baseline", this.mBaseline);
        serializeAnchor(sb, "centerX", this.mCenterX);
        serializeAnchor(sb, "centerY", this.mCenterY);
        serializeCircle(sb, this.mCenter, this.mCircleConstraintAngle);
        StringBuilder sb2 = sb;
        serializeSize(sb2, "width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        serializeSize(sb, "height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        sb.append("}\n");
        return sb;
    }

    public boolean oppositeDimensionDependsOn(int i) {
        char c = i == 0 ? (char) 1 : 0;
        DimensionBehaviour[] dimensionBehaviourArr = this.mListDimensionBehaviors;
        return dimensionBehaviourArr[i] == DimensionBehaviour.MATCH_CONSTRAINT && dimensionBehaviourArr[c] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean oppositeDimensionsTied() {
        return this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean hasDimensionOverride() {
        return (this.mWidthOverride == -1 && this.mHeightOverride == -1) ? false : true;
    }

    public ConstraintWidget() {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
    }

    public ConstraintWidget(String str) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        addAnchors();
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2, int i3, int i4) {
        this.measured = false;
        this.run = new WidgetRun[2];
        this.horizontalRun = null;
        this.verticalRun = null;
        this.isTerminalWidget = new boolean[]{true, true};
        this.mResolvedHasRatio = false;
        this.mMeasureRequested = true;
        this.OPTIMIZE_WRAP = false;
        this.OPTIMIZE_WRAP_ON_RESOLVED = true;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
        this.frame = new WidgetFrame(this);
        this.resolvedHorizontal = false;
        this.resolvedVertical = false;
        this.horizontalSolvingPass = false;
        this.verticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.mCircleConstraintAngle = 0.0f;
        this.hasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
        this.mLeft = new ConstraintAnchor(this, ConstraintAnchor.Type.LEFT);
        this.mTop = new ConstraintAnchor(this, ConstraintAnchor.Type.TOP);
        this.mRight = new ConstraintAnchor(this, ConstraintAnchor.Type.RIGHT);
        this.mBottom = new ConstraintAnchor(this, ConstraintAnchor.Type.BOTTOM);
        this.mBaseline = new ConstraintAnchor(this, ConstraintAnchor.Type.BASELINE);
        this.mCenterX = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_X);
        this.mCenterY = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER_Y);
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, ConstraintAnchor.Type.CENTER);
        this.mCenter = constraintAnchor;
        this.mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, constraintAnchor};
        this.mAnchors = new ArrayList<>();
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
        this.mParent = null;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mRelX = 0;
        this.mRelY = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        float f = DEFAULT_BIAS;
        this.mHorizontalBiasPercent = f;
        this.mVerticalBiasPercent = f;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mAnimated = false;
        this.mDebugName = null;
        this.mType = null;
        this.mGroupsToSolver = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        this.mX = i;
        this.mY = i2;
        this.mWidth = i3;
        this.mHeight = i4;
        addAnchors();
    }

    public ConstraintWidget(String str, int i, int i2, int i3, int i4) {
        this(i, i2, i3, i4);
        setDebugName(str);
    }

    public ConstraintWidget(int i, int i2) {
        this(0, 0, i, i2);
    }

    public void ensureWidgetRuns() {
        if (this.horizontalRun == null) {
            this.horizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.verticalRun == null) {
            this.verticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintWidget(String str, int i, int i2) {
        this(i, i2);
        setDebugName(str);
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public boolean isRoot() {
        return this.mParent == null;
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget constraintWidget) {
        this.mParent = constraintWidget;
    }

    public void setWidthWrapContent(boolean z) {
        this.mIsWidthWrapContent = z;
    }

    public boolean isWidthWrapContent() {
        return this.mIsWidthWrapContent;
    }

    public void setHeightWrapContent(boolean z) {
        this.mIsHeightWrapContent = z;
    }

    public boolean isHeightWrapContent() {
        return this.mIsHeightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget constraintWidget, float f, int i) {
        immediateConnect(ConstraintAnchor.Type.CENTER, constraintWidget, ConstraintAnchor.Type.CENTER, i, 0);
        this.mCircleConstraintAngle = f;
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setVisibility(int i) {
        this.mVisibility = i;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public boolean isAnimated() {
        return this.mAnimated;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String str) {
        this.mDebugName = str;
    }

    public void setDebugSolverName(LinearSystem linearSystem, String str) {
        this.mDebugName = str;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mLeft);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(this.mTop);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mRight);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mBottom);
        createObjectVariable.setName(str + ".left");
        createObjectVariable2.setName(str + ".top");
        createObjectVariable3.setName(str + ".right");
        createObjectVariable4.setName(str + ".bottom");
        SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(this.mBaseline);
        createObjectVariable5.setName(str + ".baseline");
    }

    public void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (this.mType != null) {
            str = "type: " + this.mType + " ";
        } else {
            str = str2;
        }
        sb.append(str);
        if (this.mDebugName != null) {
            str2 = "id: " + this.mDebugName + " ";
        }
        sb.append(str2);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        return sb.toString();
    }

    public int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mX;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
    }

    public int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget == null || !(constraintWidget instanceof ConstraintWidgetContainer)) {
            return this.mY;
        }
        return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getOptimizerWrapWidth() {
        int i = this.mWidth;
        int i2 = 0;
        if (this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return i;
        }
        if (this.mMatchConstraintDefaultWidth == 1) {
            i2 = Math.max(this.mMatchConstraintMinWidth, i);
        } else {
            int i3 = this.mMatchConstraintMinWidth;
            if (i3 > 0) {
                this.mWidth = i3;
                i2 = i3;
            }
        }
        int i4 = this.mMatchConstraintMaxWidth;
        return (i4 <= 0 || i4 >= i2) ? i2 : i4;
    }

    public int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i = Math.max(this.mMatchConstraintMinHeight, i);
            } else {
                i = this.mMatchConstraintMinHeight;
                if (i > 0) {
                    this.mHeight = i;
                } else {
                    i = 0;
                }
            }
            int i2 = this.mMatchConstraintMaxHeight;
            return (i2 <= 0 || i2 >= i) ? i : i2;
        }
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getLength(int i) {
        if (i == 0) {
            return getWidth();
        }
        if (i == 1) {
            return getHeight();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int getRootX() {
        return this.mX + this.mOffsetX;
    }

    /* access modifiers changed from: protected */
    public int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getLeft() {
        return getX();
    }

    public int getTop() {
        return getY();
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public int getHorizontalMargin() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        int i = constraintAnchor != null ? constraintAnchor.mMargin : 0;
        ConstraintAnchor constraintAnchor2 = this.mRight;
        return constraintAnchor2 != null ? i + constraintAnchor2.mMargin : i;
    }

    public int getVerticalMargin() {
        int i = this.mLeft != null ? this.mTop.mMargin : 0;
        return this.mRight != null ? i + this.mBottom.mMargin : i;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public float getVerticalBiasPercent() {
        return this.mVerticalBiasPercent;
    }

    public float getBiasPercent(int i) {
        if (i == 0) {
            return this.mHorizontalBiasPercent;
        }
        if (i == 1) {
            return this.mVerticalBiasPercent;
        }
        return -1.0f;
    }

    public boolean hasBaseline() {
        return this.hasBaseline;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int i) {
        this.mX = i;
    }

    public void setY(int i) {
        this.mY = i;
    }

    public void setOrigin(int i, int i2) {
        this.mX = i;
        this.mY = i2;
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public void setGoneMargin(ConstraintAnchor.Type type, int i) {
        int i2 = AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[type.ordinal()];
        if (i2 == 1) {
            this.mLeft.mGoneMargin = i;
        } else if (i2 == 2) {
            this.mTop.mGoneMargin = i;
        } else if (i2 == 3) {
            this.mRight.mGoneMargin = i;
        } else if (i2 == 4) {
            this.mBottom.mGoneMargin = i;
        } else if (i2 == 5) {
            this.mBaseline.mGoneMargin = i;
        }
    }

    public void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public void setLength(int i, int i2) {
        if (i2 == 0) {
            setWidth(i);
        } else if (i2 == 1) {
            setHeight(i);
        }
    }

    public void setHorizontalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultWidth = i;
        this.mMatchConstraintMinWidth = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxWidth = i3;
        this.mMatchConstraintPercentWidth = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int i, int i2, int i3, float f) {
        this.mMatchConstraintDefaultHeight = i;
        this.mMatchConstraintMinHeight = i2;
        if (i3 == Integer.MAX_VALUE) {
            i3 = 0;
        }
        this.mMatchConstraintMaxHeight = i3;
        this.mMatchConstraintPercentHeight = f;
        if (f > 0.0f && f < 1.0f && i == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setDimensionRatio(java.lang.String r9) {
        /*
            r8 = this;
            r0 = 0
            if (r9 == 0) goto L_0x0090
            int r1 = r9.length()
            if (r1 != 0) goto L_0x000b
            goto L_0x0090
        L_0x000b:
            int r1 = r9.length()
            r2 = 44
            int r2 = r9.indexOf(r2)
            r3 = 0
            r4 = 1
            r5 = -1
            if (r2 <= 0) goto L_0x0039
            int r6 = r1 + -1
            if (r2 >= r6) goto L_0x0039
            java.lang.String r6 = r9.substring(r3, r2)
            java.lang.String r7 = "W"
            boolean r7 = r6.equalsIgnoreCase(r7)
            if (r7 == 0) goto L_0x002b
            goto L_0x0036
        L_0x002b:
            java.lang.String r3 = "H"
            boolean r3 = r6.equalsIgnoreCase(r3)
            if (r3 == 0) goto L_0x0035
            r3 = r4
            goto L_0x0036
        L_0x0035:
            r3 = r5
        L_0x0036:
            int r2 = r2 + r4
            r5 = r3
            r3 = r2
        L_0x0039:
            r2 = 58
            int r2 = r9.indexOf(r2)
            if (r2 < 0) goto L_0x0077
            int r1 = r1 - r4
            if (r2 >= r1) goto L_0x0077
            java.lang.String r1 = r9.substring(r3, r2)
            int r2 = r2 + r4
            java.lang.String r9 = r9.substring(r2)
            int r2 = r1.length()
            if (r2 <= 0) goto L_0x0086
            int r2 = r9.length()
            if (r2 <= 0) goto L_0x0086
            float r1 = java.lang.Float.parseFloat(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            int r2 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0086
            if (r5 != r4) goto L_0x0071
            float r9 = r9 / r1
            float r9 = java.lang.Math.abs(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0071:
            float r1 = r1 / r9
            float r9 = java.lang.Math.abs(r1)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0077:
            java.lang.String r9 = r9.substring(r3)
            int r1 = r9.length()
            if (r1 <= 0) goto L_0x0086
            float r9 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x0086 }
            goto L_0x0087
        L_0x0086:
            r9 = r0
        L_0x0087:
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x008f
            r8.mDimensionRatio = r9
            r8.mDimensionRatioSide = r5
        L_0x008f:
            return
        L_0x0090:
            r8.mDimensionRatio = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.setDimensionRatio(java.lang.String):void");
    }

    public void setDimensionRatio(float f, int i) {
        this.mDimensionRatio = f;
        this.mDimensionRatioSide = i;
    }

    public float getDimensionRatio() {
        return this.mDimensionRatio;
    }

    public int getDimensionRatioSide() {
        return this.mDimensionRatioSide;
    }

    public void setHorizontalBiasPercent(float f) {
        this.mHorizontalBiasPercent = f;
    }

    public void setVerticalBiasPercent(float f) {
        this.mVerticalBiasPercent = f;
    }

    public void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public void setDimension(int i, int i2) {
        this.mWidth = i;
        int i3 = this.mMinWidth;
        if (i < i3) {
            this.mWidth = i3;
        }
        this.mHeight = i2;
        int i4 = this.mMinHeight;
        if (i2 < i4) {
            this.mHeight = i4;
        }
    }

    public void setFrame(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7 = i3 - i;
        int i8 = i4 - i2;
        this.mX = i;
        this.mY = i2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i7 < (i6 = this.mWidth)) {
            i7 = i6;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i8 < (i5 = this.mHeight)) {
            i8 = i5;
        }
        this.mWidth = i7;
        this.mHeight = i8;
        int i9 = this.mMinHeight;
        if (i8 < i9) {
            this.mHeight = i9;
        }
        int i10 = this.mMinWidth;
        if (i7 < i10) {
            this.mWidth = i10;
        }
        if (this.mMatchConstraintMaxWidth > 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mWidth = Math.min(this.mWidth, this.mMatchConstraintMaxWidth);
        }
        if (this.mMatchConstraintMaxHeight > 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
            this.mHeight = Math.min(this.mHeight, this.mMatchConstraintMaxHeight);
        }
        int i11 = this.mWidth;
        if (i7 != i11) {
            this.mWidthOverride = i11;
        }
        int i12 = this.mHeight;
        if (i8 != i12) {
            this.mHeightOverride = i12;
        }
    }

    public void setFrame(int i, int i2, int i3) {
        if (i3 == 0) {
            setHorizontalDimension(i, i2);
        } else if (i3 == 1) {
            setVerticalDimension(i, i2);
        }
    }

    public void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    /* access modifiers changed from: package-private */
    public int getRelativePositioning(int i) {
        if (i == 0) {
            return this.mRelX;
        }
        if (i == 1) {
            return this.mRelY;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public void setRelativePositioning(int i, int i2) {
        if (i2 == 0) {
            this.mRelX = i;
        } else if (i2 == 1) {
            this.mRelY = i;
        }
    }

    public void setBaselineDistance(int i) {
        this.mBaselineDistance = i;
        this.hasBaseline = i > 0;
    }

    public void setCompanionWidget(Object obj) {
        this.mCompanionWidget = obj;
    }

    public void setContainerItemSkip(int i) {
        if (i >= 0) {
            this.mContainerItemSkip = i;
        } else {
            this.mContainerItemSkip = 0;
        }
    }

    public int getContainerItemSkip() {
        return this.mContainerItemSkip;
    }

    public void setHorizontalWeight(float f) {
        this.mWeight[0] = f;
    }

    public void setVerticalWeight(float f) {
        this.mWeight[1] = f;
    }

    public void setHorizontalChainStyle(int i) {
        this.mHorizontalChainStyle = i;
    }

    public int getHorizontalChainStyle() {
        return this.mHorizontalChainStyle;
    }

    public void setVerticalChainStyle(int i) {
        this.mVerticalChainStyle = i;
    }

    public int getVerticalChainStyle() {
        return this.mVerticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i, int i2) {
        getAnchor(type).connect(constraintWidget.getAnchor(type2), i, i2, true);
    }

    public void connect(ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i) {
        if (constraintAnchor.getOwner() == this) {
            connect(constraintAnchor.getType(), constraintAnchor2.getOwner(), constraintAnchor2.getType(), i);
        }
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2) {
        connect(type, constraintWidget, type2, 0);
    }

    public void connect(ConstraintAnchor.Type type, ConstraintWidget constraintWidget, ConstraintAnchor.Type type2, int i) {
        boolean z;
        if (type == ConstraintAnchor.Type.CENTER) {
            if (type2 == ConstraintAnchor.Type.CENTER) {
                ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
                ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
                ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
                ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                boolean z2 = true;
                if ((anchor == null || !anchor.isConnected()) && (anchor2 == null || !anchor2.isConnected())) {
                    connect(ConstraintAnchor.Type.LEFT, constraintWidget, ConstraintAnchor.Type.LEFT, 0);
                    connect(ConstraintAnchor.Type.RIGHT, constraintWidget, ConstraintAnchor.Type.RIGHT, 0);
                    z = true;
                } else {
                    z = false;
                }
                if ((anchor3 == null || !anchor3.isConnected()) && (anchor4 == null || !anchor4.isConnected())) {
                    connect(ConstraintAnchor.Type.TOP, constraintWidget, ConstraintAnchor.Type.TOP, 0);
                    connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, ConstraintAnchor.Type.BOTTOM, 0);
                } else {
                    z2 = false;
                }
                if (z && z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER), 0);
                } else if (z) {
                    getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_X), 0);
                } else if (z2) {
                    getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.CENTER_Y), 0);
                }
            } else if (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT) {
                connect(ConstraintAnchor.Type.LEFT, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.RIGHT, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            } else if (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM) {
                connect(ConstraintAnchor.Type.TOP, constraintWidget, type2, 0);
                connect(ConstraintAnchor.Type.BOTTOM, constraintWidget, type2, 0);
                getAnchor(ConstraintAnchor.Type.CENTER).connect(constraintWidget.getAnchor(type2), 0);
            }
        } else if (type == ConstraintAnchor.Type.CENTER_X && (type2 == ConstraintAnchor.Type.LEFT || type2 == ConstraintAnchor.Type.RIGHT)) {
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor6 = constraintWidget.getAnchor(type2);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.RIGHT);
            anchor5.connect(anchor6, 0);
            anchor7.connect(anchor6, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(anchor6, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && (type2 == ConstraintAnchor.Type.TOP || type2 == ConstraintAnchor.Type.BOTTOM)) {
            ConstraintAnchor anchor8 = constraintWidget.getAnchor(type2);
            getAnchor(ConstraintAnchor.Type.TOP).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(anchor8, 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(anchor8, 0);
        } else if (type == ConstraintAnchor.Type.CENTER_X && type2 == ConstraintAnchor.Type.CENTER_X) {
            getAnchor(ConstraintAnchor.Type.LEFT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.LEFT), 0);
            getAnchor(ConstraintAnchor.Type.RIGHT).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_X).connect(constraintWidget.getAnchor(type2), 0);
        } else if (type == ConstraintAnchor.Type.CENTER_Y && type2 == ConstraintAnchor.Type.CENTER_Y) {
            getAnchor(ConstraintAnchor.Type.TOP).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.TOP), 0);
            getAnchor(ConstraintAnchor.Type.BOTTOM).connect(constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM), 0);
            getAnchor(ConstraintAnchor.Type.CENTER_Y).connect(constraintWidget.getAnchor(type2), 0);
        } else {
            ConstraintAnchor anchor9 = getAnchor(type);
            ConstraintAnchor anchor10 = constraintWidget.getAnchor(type2);
            if (anchor9.isValidConnection(anchor10)) {
                if (type == ConstraintAnchor.Type.BASELINE) {
                    ConstraintAnchor anchor11 = getAnchor(ConstraintAnchor.Type.TOP);
                    ConstraintAnchor anchor12 = getAnchor(ConstraintAnchor.Type.BOTTOM);
                    if (anchor11 != null) {
                        anchor11.reset();
                    }
                    if (anchor12 != null) {
                        anchor12.reset();
                    }
                } else if (type == ConstraintAnchor.Type.TOP || type == ConstraintAnchor.Type.BOTTOM) {
                    ConstraintAnchor anchor13 = getAnchor(ConstraintAnchor.Type.BASELINE);
                    if (anchor13 != null) {
                        anchor13.reset();
                    }
                    ConstraintAnchor anchor14 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor14.getTarget() != anchor10) {
                        anchor14.reset();
                    }
                    ConstraintAnchor opposite = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor15 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
                    if (anchor15.isConnected()) {
                        opposite.reset();
                        anchor15.reset();
                    }
                } else if (type == ConstraintAnchor.Type.LEFT || type == ConstraintAnchor.Type.RIGHT) {
                    ConstraintAnchor anchor16 = getAnchor(ConstraintAnchor.Type.CENTER);
                    if (anchor16.getTarget() != anchor10) {
                        anchor16.reset();
                    }
                    ConstraintAnchor opposite2 = getAnchor(type).getOpposite();
                    ConstraintAnchor anchor17 = getAnchor(ConstraintAnchor.Type.CENTER_X);
                    if (anchor17.isConnected()) {
                        opposite2.reset();
                        anchor17.reset();
                    }
                }
                anchor9.connect(anchor10, i);
            }
        }
    }

    public void resetAllConstraints() {
        resetAnchors();
        setVerticalBiasPercent(DEFAULT_BIAS);
        setHorizontalBiasPercent(DEFAULT_BIAS);
    }

    public void resetAnchor(ConstraintAnchor constraintAnchor) {
        if (getParent() == null || !(getParent() instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            ConstraintAnchor anchor = getAnchor(ConstraintAnchor.Type.LEFT);
            ConstraintAnchor anchor2 = getAnchor(ConstraintAnchor.Type.RIGHT);
            ConstraintAnchor anchor3 = getAnchor(ConstraintAnchor.Type.TOP);
            ConstraintAnchor anchor4 = getAnchor(ConstraintAnchor.Type.BOTTOM);
            ConstraintAnchor anchor5 = getAnchor(ConstraintAnchor.Type.CENTER);
            ConstraintAnchor anchor6 = getAnchor(ConstraintAnchor.Type.CENTER_X);
            ConstraintAnchor anchor7 = getAnchor(ConstraintAnchor.Type.CENTER_Y);
            if (constraintAnchor == anchor5) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor.reset();
                    anchor2.reset();
                }
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor6) {
                if (anchor.isConnected() && anchor2.isConnected() && anchor.getTarget().getOwner() == anchor2.getTarget().getOwner()) {
                    anchor.reset();
                    anchor2.reset();
                }
                this.mHorizontalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor7) {
                if (anchor3.isConnected() && anchor4.isConnected() && anchor3.getTarget().getOwner() == anchor4.getTarget().getOwner()) {
                    anchor3.reset();
                    anchor4.reset();
                }
                this.mVerticalBiasPercent = 0.5f;
            } else if (constraintAnchor == anchor || constraintAnchor == anchor2) {
                if (anchor.isConnected() && anchor.getTarget() == anchor2.getTarget()) {
                    anchor5.reset();
                }
            } else if ((constraintAnchor == anchor3 || constraintAnchor == anchor4) && anchor3.isConnected() && anchor3.getTarget() == anchor4.getTarget()) {
                anchor5.reset();
            }
            constraintAnchor.reset();
        }
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int size = this.mAnchors.size();
            for (int i = 0; i < size; i++) {
                this.mAnchors.get(i).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(ConstraintAnchor.Type type) {
        switch (AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type[type.ordinal()]) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
            case 9:
                return null;
            default:
                throw new AssertionError(type.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public DimensionBehaviour getDimensionBehaviour(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour();
        }
        if (i == 1) {
            return getVerticalDimensionBehaviour();
        }
        return null;
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[0] = dimensionBehaviour;
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour dimensionBehaviour) {
        this.mListDimensionBehaviors[1] = dimensionBehaviour;
    }

    public boolean isInHorizontalChain() {
        if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != this.mLeft) {
            return this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight;
        }
        return true;
    }

    public ConstraintWidget getPreviousChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            if (this.mLeft.mTarget == null || this.mLeft.mTarget.mTarget != (constraintAnchor2 = this.mLeft)) {
                return null;
            }
            return constraintAnchor2.mTarget.mOwner;
        } else if (i == 1 && this.mTop.mTarget != null && this.mTop.mTarget.mTarget == (constraintAnchor = this.mTop)) {
            return constraintAnchor.mTarget.mOwner;
        } else {
            return null;
        }
    }

    public ConstraintWidget getNextChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            if (this.mRight.mTarget == null || this.mRight.mTarget.mTarget != (constraintAnchor2 = this.mRight)) {
                return null;
            }
            return constraintAnchor2.mTarget.mOwner;
        } else if (i == 1 && this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == (constraintAnchor = this.mBottom)) {
            return constraintAnchor.mTarget.mOwner;
        } else {
            return null;
        }
    }

    public ConstraintWidget getHorizontalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInHorizontalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.LEFT);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.RIGHT).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    public boolean isInVerticalChain() {
        if (this.mTop.mTarget == null || this.mTop.mTarget.mTarget != this.mTop) {
            return this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom;
        }
        return true;
    }

    public ConstraintWidget getVerticalChainControlWidget() {
        ConstraintAnchor constraintAnchor;
        ConstraintWidget constraintWidget;
        ConstraintAnchor constraintAnchor2;
        if (!isInVerticalChain()) {
            return null;
        }
        ConstraintWidget constraintWidget2 = this;
        ConstraintWidget constraintWidget3 = null;
        while (constraintWidget3 == null && constraintWidget2 != null) {
            ConstraintAnchor anchor = constraintWidget2.getAnchor(ConstraintAnchor.Type.TOP);
            if (anchor == null) {
                constraintAnchor = null;
            } else {
                constraintAnchor = anchor.getTarget();
            }
            if (constraintAnchor == null) {
                constraintWidget = null;
            } else {
                constraintWidget = constraintAnchor.getOwner();
            }
            if (constraintWidget == getParent()) {
                return constraintWidget2;
            }
            if (constraintWidget == null) {
                constraintAnchor2 = null;
            } else {
                constraintAnchor2 = constraintWidget.getAnchor(ConstraintAnchor.Type.BOTTOM).getTarget();
            }
            if (constraintAnchor2 == null || constraintAnchor2.getOwner() == constraintWidget2) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget3 = constraintWidget2;
            }
        }
        return constraintWidget3;
    }

    private boolean isChainHead(int i) {
        int i2 = i * 2;
        if (this.mListAnchors[i2].mTarget == null) {
            return false;
        }
        ConstraintAnchor constraintAnchor = this.mListAnchors[i2].mTarget.mTarget;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        if (constraintAnchor == constraintAnchorArr[i2]) {
            return false;
        }
        int i3 = i2 + 1;
        return constraintAnchorArr[i3].mTarget != null && this.mListAnchors[i3].mTarget.mTarget == this.mListAnchors[i3];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r33v0, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v1, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v14, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v16, resolved type: char} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v17, resolved type: char} */
    /* JADX WARNING: type inference failed for: r19v0 */
    /* JADX WARNING: type inference failed for: r19v10 */
    /* JADX WARNING: type inference failed for: r19v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        if (r13 != 3) goto L_0x0055;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0314  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x031e  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0323  */
    /* JADX WARNING: Removed duplicated region for block: B:207:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x034a  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x034c  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x037f  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0462  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x04a9  */
    /* JADX WARNING: Removed duplicated region for block: B:266:0x04b9  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x04bb  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x04c2  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0598  */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x059f  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x05d1  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addToSolver(androidx.constraintlayout.core.LinearSystem r45, boolean r46) {
        /*
            r44 = this;
            r0 = r44
            r1 = r45
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mLeft
            androidx.constraintlayout.core.SolverVariable r2 = r1.createObjectVariable(r2)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r0.mRight
            androidx.constraintlayout.core.SolverVariable r3 = r1.createObjectVariable(r3)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r0.mTop
            androidx.constraintlayout.core.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r5 = r1.createObjectVariable(r5)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r6 = r0.mBaseline
            androidx.constraintlayout.core.SolverVariable r6 = r1.createObjectVariable(r6)
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r0.mParent
            r8 = 2
            r9 = 3
            r10 = 1
            r11 = 0
            if (r7 == 0) goto L_0x0053
            if (r7 == 0) goto L_0x0036
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r7 = r7.mListDimensionBehaviors
            r7 = r7[r11]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r12 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r7 != r12) goto L_0x0036
            r7 = r10
            goto L_0x0037
        L_0x0036:
            r7 = r11
        L_0x0037:
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r0.mParent
            if (r12 == 0) goto L_0x0045
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r12 = r12.mListDimensionBehaviors
            r12 = r12[r10]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r12 != r13) goto L_0x0045
            r12 = r10
            goto L_0x0046
        L_0x0045:
            r12 = r11
        L_0x0046:
            int r13 = r0.mWrapBehaviorInParent
            if (r13 == r10) goto L_0x0051
            if (r13 == r8) goto L_0x004f
            if (r13 == r9) goto L_0x0053
            goto L_0x0055
        L_0x004f:
            r7 = r11
            goto L_0x0055
        L_0x0051:
            r12 = r11
            goto L_0x0055
        L_0x0053:
            r7 = r11
            r12 = r7
        L_0x0055:
            int r13 = r0.mVisibility
            r14 = 8
            if (r13 != r14) goto L_0x0070
            boolean r13 = r0.mAnimated
            if (r13 != 0) goto L_0x0070
            boolean r13 = r0.hasDependencies()
            if (r13 != 0) goto L_0x0070
            boolean[] r13 = r0.mIsInBarrier
            boolean r15 = r13[r11]
            if (r15 != 0) goto L_0x0070
            boolean r13 = r13[r10]
            if (r13 != 0) goto L_0x0070
            return
        L_0x0070:
            boolean r13 = r0.resolvedHorizontal
            r15 = 5
            if (r13 != 0) goto L_0x0079
            boolean r8 = r0.resolvedVertical
            if (r8 == 0) goto L_0x00f6
        L_0x0079:
            if (r13 == 0) goto L_0x00a8
            int r8 = r0.mX
            r1.addEquality(r2, r8)
            int r8 = r0.mX
            int r13 = r0.mWidth
            int r8 = r8 + r13
            r1.addEquality(r3, r8)
            if (r7 == 0) goto L_0x00a8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x00a8
            boolean r13 = r0.OPTIMIZE_WRAP_ON_RESOLVED
            if (r13 == 0) goto L_0x009f
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r8
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r0.mLeft
            r8.addHorizontalWrapMinVariable(r13)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r0.mRight
            r8.addHorizontalWrapMaxVariable(r13)
            goto L_0x00a8
        L_0x009f:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r8.mRight
            androidx.constraintlayout.core.SolverVariable r8 = r1.createObjectVariable(r8)
            r1.addGreaterThan(r8, r3, r11, r15)
        L_0x00a8:
            boolean r8 = r0.resolvedVertical
            if (r8 == 0) goto L_0x00e9
            int r8 = r0.mY
            r1.addEquality(r4, r8)
            int r8 = r0.mY
            int r13 = r0.mHeight
            int r8 = r8 + r13
            r1.addEquality(r5, r8)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r0.mBaseline
            boolean r8 = r8.hasDependents()
            if (r8 == 0) goto L_0x00c9
            int r8 = r0.mY
            int r13 = r0.mBaselineDistance
            int r8 = r8 + r13
            r1.addEquality(r6, r8)
        L_0x00c9:
            if (r12 == 0) goto L_0x00e9
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x00e9
            boolean r13 = r0.OPTIMIZE_WRAP_ON_RESOLVED
            if (r13 == 0) goto L_0x00e0
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r8
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r0.mTop
            r8.addVerticalWrapMinVariable(r13)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r13 = r0.mBottom
            r8.addVerticalWrapMaxVariable(r13)
            goto L_0x00e9
        L_0x00e0:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r8.mBottom
            androidx.constraintlayout.core.SolverVariable r8 = r1.createObjectVariable(r8)
            r1.addGreaterThan(r8, r5, r11, r15)
        L_0x00e9:
            boolean r8 = r0.resolvedHorizontal
            if (r8 == 0) goto L_0x00f6
            boolean r8 = r0.resolvedVertical
            if (r8 == 0) goto L_0x00f6
            r0.resolvedHorizontal = r11
            r0.resolvedVertical = r11
            return
        L_0x00f6:
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            r17 = 1
            if (r8 == 0) goto L_0x0107
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            r19 = r10
            long r9 = r8.widgets
            long r9 = r9 + r17
            r8.widgets = r9
            goto L_0x0109
        L_0x0107:
            r19 = r10
        L_0x0109:
            if (r46 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            if (r8 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r9 = r0.verticalRun
            if (r9 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.start
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.end
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r8 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.start
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x01a5
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r8 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.end
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x01a5
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            if (r8 == 0) goto L_0x013d
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            long r9 = r8.graphSolved
            long r9 = r9 + r17
            r8.graphSolved = r9
        L_0x013d:
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.start
            int r8 = r8.value
            r1.addEquality(r2, r8)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r2 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r1.addEquality(r3, r2)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.start
            int r2 = r2.value
            r1.addEquality(r4, r2)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r1.addEquality(r5, r2)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.baseline
            int r2 = r2.value
            r1.addEquality(r6, r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x01a0
            if (r7 == 0) goto L_0x0187
            boolean[] r2 = r0.isTerminalWidget
            boolean r2 = r2[r11]
            if (r2 == 0) goto L_0x0187
            boolean r2 = r0.isInHorizontalChain()
            if (r2 != 0) goto L_0x0187
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mRight
            androidx.constraintlayout.core.SolverVariable r2 = r1.createObjectVariable(r2)
            r1.addGreaterThan(r2, r3, r11, r14)
        L_0x0187:
            if (r12 == 0) goto L_0x01a0
            boolean[] r2 = r0.isTerminalWidget
            boolean r2 = r2[r19]
            if (r2 == 0) goto L_0x01a0
            boolean r2 = r0.isInVerticalChain()
            if (r2 != 0) goto L_0x01a0
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.core.SolverVariable r2 = r1.createObjectVariable(r2)
            r1.addGreaterThan(r2, r5, r11, r14)
        L_0x01a0:
            r0.resolvedHorizontal = r11
            r0.resolvedVertical = r11
            return
        L_0x01a5:
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            if (r8 == 0) goto L_0x01b1
            androidx.constraintlayout.core.Metrics r8 = androidx.constraintlayout.core.LinearSystem.sMetrics
            long r9 = r8.linearSolved
            long r9 = r9 + r17
            r8.linearSolved = r9
        L_0x01b1:
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x0226
            boolean r8 = r0.isChainHead(r11)
            if (r8 == 0) goto L_0x01c6
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r8 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r8
            r8.addChain(r0, r11)
            r8 = r19
            r9 = r8
            goto L_0x01cc
        L_0x01c6:
            boolean r8 = r0.isInHorizontalChain()
            r9 = r19
        L_0x01cc:
            boolean r10 = r0.isChainHead(r9)
            if (r10 == 0) goto L_0x01db
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r10 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r10
            r10.addChain(r0, r9)
            r9 = 1
            goto L_0x01df
        L_0x01db:
            boolean r9 = r0.isInVerticalChain()
        L_0x01df:
            if (r8 != 0) goto L_0x01ff
            if (r7 == 0) goto L_0x01ff
            int r10 = r0.mVisibility
            if (r10 == r14) goto L_0x01ff
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mLeft
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x01ff
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mRight
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x01ff
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mRight
            androidx.constraintlayout.core.SolverVariable r10 = r1.createObjectVariable(r10)
            r13 = 1
            r1.addGreaterThan(r10, r3, r11, r13)
        L_0x01ff:
            if (r9 != 0) goto L_0x0223
            if (r12 == 0) goto L_0x0223
            int r10 = r0.mVisibility
            if (r10 == r14) goto L_0x0223
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mTop
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x0223
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mBottom
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mTarget
            if (r10 != 0) goto L_0x0223
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mBaseline
            if (r10 != 0) goto L_0x0223
            androidx.constraintlayout.core.widgets.ConstraintWidget r10 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r10.mBottom
            androidx.constraintlayout.core.SolverVariable r10 = r1.createObjectVariable(r10)
            r13 = 1
            r1.addGreaterThan(r10, r5, r11, r13)
        L_0x0223:
            r20 = r8
            goto L_0x0229
        L_0x0226:
            r9 = r11
            r20 = r9
        L_0x0229:
            int r8 = r0.mWidth
            int r10 = r0.mMinWidth
            if (r8 >= r10) goto L_0x0230
            r8 = r10
        L_0x0230:
            int r10 = r0.mHeight
            int r13 = r0.mMinHeight
            if (r10 >= r13) goto L_0x0237
            r10 = r13
        L_0x0237:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r0.mListDimensionBehaviors
            r13 = r13[r11]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r15 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 == r15) goto L_0x0241
            r15 = 1
            goto L_0x0242
        L_0x0241:
            r15 = r11
        L_0x0242:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r0.mListDimensionBehaviors
            r19 = 1
            r13 = r13[r19]
            r21 = r11
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 == r11) goto L_0x0250
            r11 = 1
            goto L_0x0252
        L_0x0250:
            r11 = r21
        L_0x0252:
            int r13 = r0.mDimensionRatioSide
            r0.mResolvedDimensionRatioSide = r13
            float r13 = r0.mDimensionRatio
            r0.mResolvedDimensionRatio = r13
            int r14 = r0.mMatchConstraintDefaultWidth
            r23 = r4
            int r4 = r0.mMatchConstraintDefaultHeight
            r24 = 0
            int r13 = (r13 > r24 ? 1 : (r13 == r24 ? 0 : -1))
            r24 = r4
            if (r13 <= 0) goto L_0x02ff
            int r13 = r0.mVisibility
            r4 = 8
            if (r13 == r4) goto L_0x02ff
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r4 = r4[r21]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r13) goto L_0x0279
            if (r14 != 0) goto L_0x0279
            r14 = 3
        L_0x0279:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r4 = r0.mListDimensionBehaviors
            r19 = 1
            r4 = r4[r19]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r4 != r13) goto L_0x0287
            if (r24 != 0) goto L_0x0287
            r4 = 3
            goto L_0x0289
        L_0x0287:
            r4 = r24
        L_0x0289:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r13 = r0.mListDimensionBehaviors
            r13 = r13[r21]
            r26 = r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r13 != r5) goto L_0x02a6
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r19 = 1
            r5 = r5[r19]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r13) goto L_0x02a6
            r13 = 3
            if (r14 != r13) goto L_0x02a6
            if (r4 != r13) goto L_0x02a6
            r0.setupDimensionRatio(r7, r12, r15, r11)
            goto L_0x02fb
        L_0x02a6:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r5 = r5[r21]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r15 = 4
            if (r5 != r11) goto L_0x02cc
            r13 = 3
            if (r14 != r13) goto L_0x02cc
            r5 = r21
            r0.mResolvedDimensionRatioSide = r5
            float r5 = r0.mResolvedDimensionRatio
            int r8 = r0.mHeight
            float r8 = (float) r8
            float r5 = r5 * r8
            int r8 = (int) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r11 = 1
            r5 = r5[r11]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r28 = r10
            if (r5 == r13) goto L_0x02ca
            r14 = r15
            goto L_0x0305
        L_0x02ca:
            r5 = r11
            goto L_0x0306
        L_0x02cc:
            r11 = 1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r5 = r5[r11]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r13 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 != r13) goto L_0x02fb
            r13 = 3
            if (r4 != r13) goto L_0x02fb
            r0.mResolvedDimensionRatioSide = r11
            int r5 = r0.mDimensionRatioSide
            r10 = -1
            if (r5 != r10) goto L_0x02e6
            r5 = 1065353216(0x3f800000, float:1.0)
            float r10 = r0.mResolvedDimensionRatio
            float r5 = r5 / r10
            r0.mResolvedDimensionRatio = r5
        L_0x02e6:
            float r5 = r0.mResolvedDimensionRatio
            int r10 = r0.mWidth
            float r10 = (float) r10
            float r5 = r5 * r10
            int r10 = (int) r5
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r5 = r0.mListDimensionBehaviors
            r21 = 0
            r5 = r5[r21]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r5 == r11) goto L_0x02fb
            r28 = r10
            r4 = r15
            goto L_0x0305
        L_0x02fb:
            r28 = r10
            r5 = 1
            goto L_0x0306
        L_0x02ff:
            r26 = r5
            r28 = r10
            r4 = r24
        L_0x0305:
            r5 = 0
        L_0x0306:
            int[] r10 = r0.mResolvedMatchConstraintDefault
            r21 = 0
            r10[r21] = r14
            r19 = 1
            r10[r19] = r4
            r0.mResolvedHasRatio = r5
            if (r5 == 0) goto L_0x031e
            int r10 = r0.mResolvedDimensionRatioSide
            r11 = -1
            if (r10 == 0) goto L_0x031b
            if (r10 != r11) goto L_0x031f
        L_0x031b:
            r17 = 1
            goto L_0x0321
        L_0x031e:
            r11 = -1
        L_0x031f:
            r17 = 0
        L_0x0321:
            if (r5 == 0) goto L_0x032d
            int r10 = r0.mResolvedDimensionRatioSide
            r13 = 1
            if (r10 == r13) goto L_0x032a
            if (r10 != r11) goto L_0x032d
        L_0x032a:
            r29 = 1
            goto L_0x032f
        L_0x032d:
            r29 = 0
        L_0x032f:
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r0.mListDimensionBehaviors
            r21 = 0
            r10 = r10[r21]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r11 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r10 != r11) goto L_0x0343
            boolean r10 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r10 == 0) goto L_0x0343
            r11 = r20
            r20 = r9
            r9 = 1
            goto L_0x0348
        L_0x0343:
            r11 = r20
            r20 = r9
            r9 = 0
        L_0x0348:
            if (r9 == 0) goto L_0x034c
            r13 = 0
            goto L_0x034d
        L_0x034c:
            r13 = r8
        L_0x034d:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r0.mCenter
            boolean r8 = r8.isConnected()
            r19 = 1
            r27 = r8 ^ 1
            boolean[] r8 = r0.mIsInBarrier
            r21 = 0
            boolean r10 = r8[r21]
            boolean r30 = r8[r19]
            int r8 = r0.mHorizontalResolution
            r31 = 0
            r15 = 2
            if (r8 == r15) goto L_0x043a
            boolean r8 = r0.resolvedHorizontal
            if (r8 != 0) goto L_0x043a
            if (r46 == 0) goto L_0x03b5
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            if (r8 == 0) goto L_0x03b5
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.start
            boolean r8 = r8.resolved
            if (r8 == 0) goto L_0x03b5
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.end
            boolean r8 = r8.resolved
            if (r8 != 0) goto L_0x037f
            goto L_0x03b5
        L_0x037f:
            if (r46 == 0) goto L_0x043a
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.start
            int r8 = r8.value
            r1.addEquality(r2, r8)
            androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun r8 = r0.horizontalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r8 = r8.end
            int r8 = r8.value
            r1.addEquality(r3, r8)
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x043a
            if (r7 == 0) goto L_0x043a
            boolean[] r8 = r0.isTerminalWidget
            r9 = 0
            boolean r8 = r8[r9]
            if (r8 == 0) goto L_0x043a
            boolean r8 = r0.isInHorizontalChain()
            if (r8 != 0) goto L_0x043a
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r8.mRight
            androidx.constraintlayout.core.SolverVariable r8 = r1.createObjectVariable(r8)
            r10 = 8
            r1.addGreaterThan(r8, r3, r9, r10)
            goto L_0x043a
        L_0x03b5:
            r22 = 8
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r0.mParent
            if (r8 == 0) goto L_0x03c2
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r8.mRight
            androidx.constraintlayout.core.SolverVariable r8 = r1.createObjectVariable(r8)
            goto L_0x03c4
        L_0x03c2:
            r8 = r31
        L_0x03c4:
            androidx.constraintlayout.core.widgets.ConstraintWidget r15 = r0.mParent
            if (r15 == 0) goto L_0x03cf
            androidx.constraintlayout.core.widgets.ConstraintAnchor r15 = r15.mLeft
            androidx.constraintlayout.core.SolverVariable r15 = r1.createObjectVariable(r15)
            goto L_0x03d1
        L_0x03cf:
            r15 = r31
        L_0x03d1:
            boolean[] r1 = r0.isTerminalWidget
            r21 = 0
            boolean r1 = r1[r21]
            r24 = r1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r1 = r0.mListDimensionBehaviors
            r25 = r3
            r3 = r7
            r7 = r8
            r8 = r1[r21]
            r32 = r21
            r21 = r10
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mLeft
            r19 = r11
            r33 = 1
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r0.mRight
            r34 = r23
            r23 = r4
            r4 = r12
            int r12 = r0.mX
            r35 = r22
            r22 = r14
            int r14 = r0.mMinWidth
            r36 = r1
            int[] r1 = r0.mMaxDimension
            r1 = r1[r32]
            r37 = r1
            float r1 = r0.mHorizontalBiasPercent
            r38 = r1
            r1 = r36[r33]
            r36 = r2
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r1 != r2) goto L_0x0411
            r18 = r33
            goto L_0x0413
        L_0x0411:
            r18 = r32
        L_0x0413:
            r1 = 5
            int r2 = r0.mMatchConstraintMinWidth
            int r1 = r0.mMatchConstraintMaxWidth
            r39 = r1
            float r1 = r0.mMatchConstraintPercentWidth
            r40 = r5
            r5 = r24
            r24 = r2
            r2 = 1
            r43 = r6
            r6 = r15
            r32 = r25
            r42 = r26
            r41 = r34
            r15 = r37
            r16 = r38
            r25 = r39
            r26 = r1
            r1 = r45
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            goto L_0x044e
        L_0x043a:
            r36 = r2
            r32 = r3
            r40 = r5
            r43 = r6
            r3 = r7
            r19 = r11
            r22 = r14
            r41 = r23
            r42 = r26
            r23 = r4
            r4 = r12
        L_0x044e:
            if (r46 == 0) goto L_0x04a9
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            if (r2 == 0) goto L_0x04a9
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.start
            boolean r2 = r2.resolved
            if (r2 == 0) goto L_0x04a9
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.end
            boolean r2 = r2.resolved
            if (r2 == 0) goto L_0x04a9
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.start
            int r2 = r2.value
            r5 = r41
            r1.addEquality(r5, r2)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.end
            int r2 = r2.value
            r6 = r42
            r1.addEquality(r6, r2)
            androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun r2 = r0.verticalRun
            androidx.constraintlayout.core.widgets.analyzer.DependencyNode r2 = r2.baseline
            int r2 = r2.value
            r7 = r43
            r1.addEquality(r7, r2)
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x04a3
            if (r20 != 0) goto L_0x04a3
            if (r4 == 0) goto L_0x04a3
            boolean[] r8 = r0.isTerminalWidget
            r13 = 1
            boolean r8 = r8[r13]
            if (r8 == 0) goto L_0x049f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.core.SolverVariable r2 = r1.createObjectVariable(r2)
            r8 = 0
            r10 = 8
            r1.addGreaterThan(r2, r6, r8, r10)
            goto L_0x04a7
        L_0x049f:
            r8 = 0
            r10 = 8
            goto L_0x04a7
        L_0x04a3:
            r8 = 0
            r10 = 8
            r13 = 1
        L_0x04a7:
            r2 = r8
            goto L_0x04b4
        L_0x04a9:
            r5 = r41
            r6 = r42
            r7 = r43
            r8 = 0
            r10 = 8
            r13 = 1
            r2 = r13
        L_0x04b4:
            int r9 = r0.mVerticalResolution
            r15 = 2
            if (r9 != r15) goto L_0x04bb
            r11 = r8
            goto L_0x04bc
        L_0x04bb:
            r11 = r2
        L_0x04bc:
            if (r11 == 0) goto L_0x0598
            boolean r2 = r0.resolvedVertical
            if (r2 != 0) goto L_0x0598
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r0.mListDimensionBehaviors
            r2 = r2[r13]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r9 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT
            if (r2 != r9) goto L_0x04d0
            boolean r2 = r0 instanceof androidx.constraintlayout.core.widgets.ConstraintWidgetContainer
            if (r2 == 0) goto L_0x04d0
            r9 = r13
            goto L_0x04d1
        L_0x04d0:
            r9 = r8
        L_0x04d1:
            if (r9 == 0) goto L_0x04d5
            r11 = r8
            goto L_0x04d7
        L_0x04d5:
            r11 = r28
        L_0x04d7:
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x04e2
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mBottom
            androidx.constraintlayout.core.SolverVariable r2 = r1.createObjectVariable(r2)
            goto L_0x04e4
        L_0x04e2:
            r2 = r31
        L_0x04e4:
            androidx.constraintlayout.core.widgets.ConstraintWidget r12 = r0.mParent
            if (r12 == 0) goto L_0x04ee
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTop
            androidx.constraintlayout.core.SolverVariable r31 = r1.createObjectVariable(r12)
        L_0x04ee:
            int r12 = r0.mBaselineDistance
            if (r12 > 0) goto L_0x04f6
            int r12 = r0.mVisibility
            if (r12 != r10) goto L_0x0538
        L_0x04f6:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            if (r12 == 0) goto L_0x0523
            int r12 = r0.getBaselineDistance()
            r1.addEquality(r7, r5, r12, r10)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r12.mTarget
            androidx.constraintlayout.core.SolverVariable r12 = r1.createObjectVariable(r12)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r14 = r0.mBaseline
            int r14 = r14.getMargin()
            r1.addEquality(r7, r12, r14, r10)
            if (r4 == 0) goto L_0x0520
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r0.mBottom
            androidx.constraintlayout.core.SolverVariable r7 = r1.createObjectVariable(r7)
            r10 = 5
            r1.addGreaterThan(r2, r7, r8, r10)
        L_0x0520:
            r27 = r8
            goto L_0x0538
        L_0x0523:
            int r12 = r0.mVisibility
            if (r12 != r10) goto L_0x0531
            androidx.constraintlayout.core.widgets.ConstraintAnchor r12 = r0.mBaseline
            int r12 = r12.getMargin()
            r1.addEquality(r7, r5, r12, r10)
            goto L_0x0538
        L_0x0531:
            int r12 = r0.getBaselineDistance()
            r1.addEquality(r7, r5, r12, r10)
        L_0x0538:
            boolean[] r7 = r0.isTerminalWidget
            boolean r7 = r7[r13]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r10 = r0.mListDimensionBehaviors
            r21 = r8
            r8 = r10[r13]
            r12 = r10
            androidx.constraintlayout.core.widgets.ConstraintAnchor r10 = r0.mTop
            r33 = r13
            r13 = r11
            androidx.constraintlayout.core.widgets.ConstraintAnchor r11 = r0.mBottom
            r14 = r12
            int r12 = r0.mY
            r15 = r14
            int r14 = r0.mMinHeight
            int[] r1 = r0.mMaxDimension
            r1 = r1[r33]
            r46 = r1
            float r1 = r0.mVerticalBiasPercent
            r15 = r15[r21]
            r16 = r1
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r1 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r15 != r1) goto L_0x0563
            r18 = r33
            goto L_0x0565
        L_0x0563:
            r18 = r21
        L_0x0565:
            int r1 = r0.mMatchConstraintMinHeight
            int r15 = r0.mMatchConstraintMaxHeight
            r24 = r1
            float r1 = r0.mMatchConstraintPercentHeight
            r34 = r5
            r5 = r7
            r7 = r2
            r2 = 0
            r17 = r4
            r4 = r3
            r3 = r17
            r17 = r20
            r20 = r19
            r19 = r17
            r17 = r23
            r23 = r22
            r22 = r17
            r26 = r1
            r42 = r6
            r25 = r15
            r17 = r29
            r21 = r30
            r6 = r31
            r1 = r45
            r15 = r46
            r0.applyConstraints(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27)
            r7 = r0
            goto L_0x059d
        L_0x0598:
            r7 = r0
            r34 = r5
            r42 = r6
        L_0x059d:
            if (r40 == 0) goto L_0x05c7
            int r0 = r7.mResolvedDimensionRatioSide
            r6 = 8
            r13 = 1
            if (r0 != r13) goto L_0x05b6
            float r5 = r7.mResolvedDimensionRatio
            r0 = r45
            r3 = r32
            r2 = r34
            r4 = r36
            r1 = r42
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            goto L_0x05c7
        L_0x05b6:
            float r5 = r7.mResolvedDimensionRatio
            r0 = r45
            r1 = r32
            r4 = r34
            r2 = r36
            r3 = r42
            r0.addRatio(r1, r2, r3, r4, r5, r6)
            r1 = r0
            goto L_0x05c9
        L_0x05c7:
            r1 = r45
        L_0x05c9:
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r7.mCenter
            boolean r0 = r0.isConnected()
            if (r0 == 0) goto L_0x05ef
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r7.mCenter
            androidx.constraintlayout.core.widgets.ConstraintAnchor r0 = r0.getTarget()
            androidx.constraintlayout.core.widgets.ConstraintWidget r0 = r0.getOwner()
            float r2 = r7.mCircleConstraintAngle
            r3 = 1119092736(0x42b40000, float:90.0)
            float r2 = r2 + r3
            double r2 = (double) r2
            double r2 = java.lang.Math.toRadians(r2)
            float r2 = (float) r2
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r7.mCenter
            int r3 = r3.getMargin()
            r1.addCenterPoint(r7, r0, r2, r3)
        L_0x05ef:
            r5 = 0
            r7.resolvedHorizontal = r5
            r7.resolvedVertical = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.core.LinearSystem, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public boolean addFirst() {
        return (this instanceof VirtualLayout) || (this instanceof Guideline);
    }

    public void setupDimensionRatio(boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mResolvedDimensionRatioSide == -1) {
            if (z3 && !z4) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!z3 && z4) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && (!this.mLeft.isConnected() || !this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && (!this.mTop.isConnected() || !this.mBottom.isConnected() || !this.mLeft.isConnected() || !this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            int i = this.mMatchConstraintMinWidth;
            if (i > 0 && this.mMatchConstraintMinHeight == 0) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (i == 0 && this.mMatchConstraintMinHeight > 0) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:320:0x04e6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:341:0x0514  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x051d A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:369:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:372:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyConstraints(androidx.constraintlayout.core.LinearSystem r30, boolean r31, boolean r32, boolean r33, boolean r34, androidx.constraintlayout.core.SolverVariable r35, androidx.constraintlayout.core.SolverVariable r36, androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour r37, boolean r38, androidx.constraintlayout.core.widgets.ConstraintAnchor r39, androidx.constraintlayout.core.widgets.ConstraintAnchor r40, int r41, int r42, int r43, int r44, float r45, boolean r46, boolean r47, boolean r48, boolean r49, boolean r50, int r51, int r52, int r53, int r54, float r55, boolean r56) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            r12 = r39
            r13 = r40
            r14 = r43
            r2 = r44
            r4 = r53
            r5 = r54
            androidx.constraintlayout.core.SolverVariable r6 = r1.createObjectVariable(r12)
            androidx.constraintlayout.core.SolverVariable r7 = r1.createObjectVariable(r13)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r12.getTarget()
            androidx.constraintlayout.core.SolverVariable r15 = r1.createObjectVariable(r8)
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r13.getTarget()
            androidx.constraintlayout.core.SolverVariable r8 = r1.createObjectVariable(r8)
            androidx.constraintlayout.core.Metrics r9 = androidx.constraintlayout.core.LinearSystem.getMetrics()
            if (r9 == 0) goto L_0x003a
            androidx.constraintlayout.core.Metrics r9 = androidx.constraintlayout.core.LinearSystem.getMetrics()
            long r10 = r9.nonresolvedWidgets
            r16 = 1
            long r10 = r10 + r16
            r9.nonresolvedWidgets = r10
        L_0x003a:
            boolean r10 = r12.isConnected()
            boolean r11 = r13.isConnected()
            androidx.constraintlayout.core.widgets.ConstraintAnchor r9 = r0.mCenter
            boolean r16 = r9.isConnected()
            if (r11 == 0) goto L_0x004d
            int r9 = r10 + 1
            goto L_0x004e
        L_0x004d:
            r9 = r10
        L_0x004e:
            if (r16 == 0) goto L_0x0052
            int r9 = r9 + 1
        L_0x0052:
            r17 = r10
            if (r46 == 0) goto L_0x0059
            r18 = 3
            goto L_0x005b
        L_0x0059:
            r18 = r51
        L_0x005b:
            int[] r19 = androidx.constraintlayout.core.widgets.ConstraintWidget.AnonymousClass1.$SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour
            int r20 = r37.ordinal()
            r10 = r19[r20]
            r19 = r11
            r11 = 1
            if (r10 == r11) goto L_0x0071
            r11 = 2
            if (r10 == r11) goto L_0x0071
            r11 = 3
            if (r10 == r11) goto L_0x0071
            r11 = 4
            if (r10 == r11) goto L_0x0076
        L_0x0071:
            r10 = r18
        L_0x0073:
            r18 = 0
            goto L_0x007c
        L_0x0076:
            r10 = r18
            if (r10 == r11) goto L_0x0073
            r18 = 1
        L_0x007c:
            int r11 = r0.mWidthOverride
            r3 = -1
            if (r11 == r3) goto L_0x0088
            if (r31 == 0) goto L_0x0088
            r0.mWidthOverride = r3
            r18 = 0
            goto L_0x008a
        L_0x0088:
            r11 = r42
        L_0x008a:
            r22 = r8
            int r8 = r0.mHeightOverride
            if (r8 == r3) goto L_0x0097
            if (r31 != 0) goto L_0x0097
            r0.mHeightOverride = r3
            r18 = 0
            goto L_0x0098
        L_0x0097:
            r8 = r11
        L_0x0098:
            int r11 = r0.mVisibility
            r3 = 8
            if (r11 != r3) goto L_0x00a1
            r8 = 0
            r18 = 0
        L_0x00a1:
            if (r56 == 0) goto L_0x00ba
            if (r17 != 0) goto L_0x00af
            if (r19 != 0) goto L_0x00af
            if (r16 != 0) goto L_0x00af
            r11 = r41
            r1.addEquality(r6, r11)
            goto L_0x00ba
        L_0x00af:
            if (r17 == 0) goto L_0x00ba
            if (r19 != 0) goto L_0x00ba
            int r11 = r12.getMargin()
            r1.addEquality(r6, r15, r11, r3)
        L_0x00ba:
            if (r18 != 0) goto L_0x00e3
            if (r38 == 0) goto L_0x00d3
            r3 = 0
            r11 = 3
            r1.addEquality(r7, r6, r3, r11)
            r3 = 8
            if (r14 <= 0) goto L_0x00ca
            r1.addGreaterThan(r7, r6, r14, r3)
        L_0x00ca:
            r8 = 2147483647(0x7fffffff, float:NaN)
            if (r2 >= r8) goto L_0x00d6
            r1.addLowerThan(r7, r6, r2, r3)
            goto L_0x00d6
        L_0x00d3:
            r1.addEquality(r7, r6, r8, r3)
        L_0x00d6:
            r3 = r5
            r5 = r7
            r2 = r9
            r24 = r18
            r11 = r22
            r18 = r34
        L_0x00df:
            r22 = r4
            goto L_0x01cd
        L_0x00e3:
            r11 = 2
            if (r9 == r11) goto L_0x0106
            if (r46 != 0) goto L_0x0106
            r2 = 1
            if (r10 == r2) goto L_0x00ed
            if (r10 != 0) goto L_0x0106
        L_0x00ed:
            int r2 = java.lang.Math.max(r4, r8)
            if (r5 <= 0) goto L_0x00f7
            int r2 = java.lang.Math.min(r5, r2)
        L_0x00f7:
            r3 = 8
            r1.addEquality(r7, r6, r2, r3)
            r18 = r34
            r3 = r5
            r5 = r7
            r2 = r9
            r11 = r22
            r24 = 0
            goto L_0x00df
        L_0x0106:
            r2 = -2
            if (r4 != r2) goto L_0x010b
            r3 = r8
            goto L_0x010c
        L_0x010b:
            r3 = r4
        L_0x010c:
            if (r5 != r2) goto L_0x0110
            r2 = r8
            goto L_0x0111
        L_0x0110:
            r2 = r5
        L_0x0111:
            if (r8 <= 0) goto L_0x0117
            r4 = 1
            if (r10 == r4) goto L_0x0117
            r8 = 0
        L_0x0117:
            r4 = 8
            if (r3 <= 0) goto L_0x0122
            r1.addGreaterThan(r7, r6, r3, r4)
            int r8 = java.lang.Math.max(r8, r3)
        L_0x0122:
            r5 = 1
            if (r2 <= 0) goto L_0x0131
            if (r32 == 0) goto L_0x012a
            if (r10 != r5) goto L_0x012a
            goto L_0x012d
        L_0x012a:
            r1.addLowerThan(r7, r6, r2, r4)
        L_0x012d:
            int r8 = java.lang.Math.min(r8, r2)
        L_0x0131:
            if (r10 != r5) goto L_0x0157
            if (r32 == 0) goto L_0x0139
            r1.addEquality(r7, r6, r8, r4)
            goto L_0x014a
        L_0x0139:
            if (r48 == 0) goto L_0x0143
            r5 = 5
            r1.addEquality(r7, r6, r8, r5)
            r1.addLowerThan(r7, r6, r8, r4)
            goto L_0x014a
        L_0x0143:
            r5 = 5
            r1.addEquality(r7, r6, r8, r5)
            r1.addLowerThan(r7, r6, r8, r4)
        L_0x014a:
            r5 = r7
            r24 = r18
            r11 = r22
            r18 = r34
            r22 = r3
            r3 = r2
            r2 = r9
            goto L_0x01cd
        L_0x0157:
            r11 = 2
            if (r10 != r11) goto L_0x01bf
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = r12.getType()
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            if (r4 == r5) goto L_0x0184
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = r12.getType()
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            if (r4 != r5) goto L_0x016b
            goto L_0x0184
        L_0x016b:
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.getAnchor(r5)
            androidx.constraintlayout.core.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.getAnchor(r8)
            androidx.constraintlayout.core.SolverVariable r5 = r1.createObjectVariable(r5)
            goto L_0x019c
        L_0x0184:
            androidx.constraintlayout.core.widgets.ConstraintWidget r4 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP
            androidx.constraintlayout.core.widgets.ConstraintAnchor r4 = r4.getAnchor(r5)
            androidx.constraintlayout.core.SolverVariable r4 = r1.createObjectVariable(r4)
            androidx.constraintlayout.core.widgets.ConstraintWidget r5 = r0.mParent
            androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r8 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM
            androidx.constraintlayout.core.widgets.ConstraintAnchor r5 = r5.getAnchor(r8)
            androidx.constraintlayout.core.SolverVariable r5 = r1.createObjectVariable(r5)
        L_0x019c:
            r8 = r4
            androidx.constraintlayout.core.ArrayRow r4 = r1.createRow()
            r38 = r7
            r7 = r5
            r5 = r38
            r38 = r2
            r2 = r9
            r11 = r22
            r9 = r55
            androidx.constraintlayout.core.ArrayRow r4 = r4.createRowDimensionRatio(r5, r6, r7, r8, r9)
            r1.addConstraint(r4)
            if (r32 == 0) goto L_0x01b8
            r18 = 0
        L_0x01b8:
            r22 = r3
            r24 = r18
            r18 = r34
            goto L_0x01cb
        L_0x01bf:
            r38 = r2
            r5 = r7
            r2 = r9
            r11 = r22
            r22 = r3
            r24 = r18
            r18 = 1
        L_0x01cb:
            r3 = r38
        L_0x01cd:
            if (r56 == 0) goto L_0x0514
            if (r48 == 0) goto L_0x01da
            r12 = r35
            r4 = r36
            r11 = 2
            r13 = 8
            goto L_0x051b
        L_0x01da:
            if (r17 != 0) goto L_0x01e2
            if (r19 != 0) goto L_0x01e2
            if (r16 != 0) goto L_0x01e2
            goto L_0x04df
        L_0x01e2:
            if (r17 == 0) goto L_0x01fa
            if (r19 != 0) goto L_0x01fa
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r12.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner
            if (r32 == 0) goto L_0x01f3
            boolean r2 = r2 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r2 == 0) goto L_0x01f3
            r3 = 8
            goto L_0x01f4
        L_0x01f3:
            r3 = 5
        L_0x01f4:
            r4 = r32
            r2 = r11
            r11 = r3
            goto L_0x04e4
        L_0x01fa:
            if (r17 != 0) goto L_0x022f
            if (r19 == 0) goto L_0x022f
            int r2 = r13.getMargin()
            int r2 = -r2
            r3 = 8
            r1.addEquality(r5, r11, r2, r3)
            if (r32 == 0) goto L_0x04df
            boolean r2 = r0.OPTIMIZE_WRAP
            if (r2 == 0) goto L_0x0224
            boolean r2 = r6.isFinalValue
            if (r2 == 0) goto L_0x0224
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x0224
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r2 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r2
            if (r31 == 0) goto L_0x021f
            r2.addHorizontalWrapMinVariable(r12)
            goto L_0x04df
        L_0x021f:
            r2.addVerticalWrapMinVariable(r12)
            goto L_0x04df
        L_0x0224:
            r4 = r35
            r2 = 0
            r3 = 5
            r1.addGreaterThan(r6, r4, r2, r3)
            r7 = r3
            r2 = r11
            goto L_0x04e1
        L_0x022f:
            r4 = r35
            r2 = 0
            if (r17 == 0) goto L_0x04df
            if (r19 == 0) goto L_0x04df
            androidx.constraintlayout.core.widgets.ConstraintAnchor r7 = r12.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r7 = r7.mOwner
            androidx.constraintlayout.core.widgets.ConstraintAnchor r8 = r13.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r8 = r8.mOwner
            androidx.constraintlayout.core.widgets.ConstraintWidget r9 = r0.getParent()
            r16 = 6
            if (r24 == 0) goto L_0x0372
            if (r10 != 0) goto L_0x02a0
            if (r3 != 0) goto L_0x0273
            if (r22 != 0) goto L_0x0273
            boolean r3 = r15.isFinalValue
            if (r3 == 0) goto L_0x0266
            boolean r3 = r11.isFinalValue
            if (r3 == 0) goto L_0x0266
            int r2 = r12.getMargin()
            r3 = 8
            r1.addEquality(r6, r15, r2, r3)
            int r2 = r13.getMargin()
            int r2 = -r2
            r1.addEquality(r5, r11, r2, r3)
            return
        L_0x0266:
            r3 = 8
            r21 = r2
            r25 = r21
            r17 = r3
            r19 = r17
            r23 = 1
            goto L_0x027f
        L_0x0273:
            r3 = 8
            r23 = r2
            r17 = 5
            r19 = 5
            r21 = 1
            r25 = 1
        L_0x027f:
            boolean r2 = r7 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r2 != 0) goto L_0x028d
            boolean r2 = r8 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r2 == 0) goto L_0x0288
            goto L_0x028d
        L_0x0288:
            r2 = r6
            r6 = r11
            r26 = r23
            goto L_0x0293
        L_0x028d:
            r2 = r6
            r6 = r11
            r26 = r23
            r19 = 4
        L_0x0293:
            r11 = r36
            r23 = r21
            r21 = r17
            r17 = r3
            r3 = r15
            r15 = r16
            goto L_0x03c9
        L_0x02a0:
            r2 = 2
            r17 = 8
            if (r10 != r2) goto L_0x02c0
            boolean r2 = r7 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r2 != 0) goto L_0x02b6
            boolean r2 = r8 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r2 == 0) goto L_0x02ae
            goto L_0x02b6
        L_0x02ae:
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 5
            goto L_0x02bd
        L_0x02b6:
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 4
        L_0x02bd:
            r21 = 5
            goto L_0x02cc
        L_0x02c0:
            r2 = 1
            if (r10 != r2) goto L_0x02d2
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r21 = r17
            r19 = 4
        L_0x02cc:
            r23 = 1
            r25 = 1
            goto L_0x036d
        L_0x02d2:
            r2 = 3
            if (r10 != r2) goto L_0x035e
            int r2 = r0.mResolvedDimensionRatioSide
            r1 = -1
            if (r2 != r1) goto L_0x02fc
            if (r49 == 0) goto L_0x02e9
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r21 = r17
            if (r32 == 0) goto L_0x02e7
            r15 = 5
            goto L_0x02f2
        L_0x02e7:
            r15 = 4
            goto L_0x02f2
        L_0x02e9:
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r17
            r21 = r15
        L_0x02f2:
            r19 = 5
        L_0x02f4:
            r23 = 1
            r25 = 1
            r26 = 1
            goto L_0x036f
        L_0x02fc:
            if (r46 == 0) goto L_0x0325
            r1 = r52
            r2 = 2
            if (r1 == r2) goto L_0x0309
            r2 = 1
            if (r1 != r2) goto L_0x0307
            goto L_0x0309
        L_0x0307:
            r3 = 0
            goto L_0x030a
        L_0x0309:
            r3 = 1
        L_0x030a:
            if (r3 != 0) goto L_0x0310
            r1 = r17
            r2 = 5
            goto L_0x0312
        L_0x0310:
            r1 = 5
            r2 = 4
        L_0x0312:
            r21 = r1
            r19 = r2
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
        L_0x031b:
            r23 = 1
            r25 = 1
            r26 = 1
            r1 = r30
            goto L_0x036f
        L_0x0325:
            if (r3 <= 0) goto L_0x0331
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 5
            goto L_0x035b
        L_0x0331:
            if (r3 != 0) goto L_0x0352
            if (r22 != 0) goto L_0x0352
            if (r49 != 0) goto L_0x0341
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = r17
            goto L_0x035b
        L_0x0341:
            if (r7 == r9) goto L_0x0347
            if (r8 == r9) goto L_0x0347
            r1 = 4
            goto L_0x0348
        L_0x0347:
            r1 = 5
        L_0x0348:
            r21 = r1
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 4
            goto L_0x031b
        L_0x0352:
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 4
        L_0x035b:
            r21 = 5
            goto L_0x02f4
        L_0x035e:
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r15 = r16
            r19 = 4
            r21 = 5
            r23 = 0
            r25 = 0
        L_0x036d:
            r26 = 0
        L_0x036f:
            r11 = r36
            goto L_0x03c9
        L_0x0372:
            r17 = 8
            boolean r1 = r15.isFinalValue
            if (r1 == 0) goto L_0x03b6
            boolean r1 = r11.isFinalValue
            if (r1 == 0) goto L_0x03b6
            int r1 = r12.getMargin()
            int r2 = r13.getMargin()
            r3 = 8
            r46 = r30
            r50 = r45
            r49 = r1
            r53 = r2
            r54 = r3
            r52 = r5
            r47 = r6
            r51 = r11
            r48 = r15
            r46.addCentering(r47, r48, r49, r50, r51, r52, r53, r54)
            r1 = r46
            r6 = r51
            if (r32 == 0) goto L_0x0566
            if (r18 == 0) goto L_0x0566
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r13.mTarget
            if (r2 == 0) goto L_0x03ac
            int r3 = r13.getMargin()
            goto L_0x03ad
        L_0x03ac:
            r3 = 0
        L_0x03ad:
            r11 = r36
            if (r6 == r11) goto L_0x0566
            r2 = 5
            r1.addGreaterThan(r11, r5, r3, r2)
            return
        L_0x03b6:
            r1 = r30
            r2 = r6
            r6 = r11
            r3 = r15
            r11 = r36
            r15 = r16
            r19 = 4
            r21 = 5
            r23 = 1
            r25 = 1
            r26 = 0
        L_0x03c9:
            if (r25 == 0) goto L_0x03d4
            if (r3 != r6) goto L_0x03d4
            if (r7 == r9) goto L_0x03d4
            r25 = 0
            r27 = 0
            goto L_0x03d6
        L_0x03d4:
            r27 = 1
        L_0x03d6:
            if (r23 == 0) goto L_0x0411
            if (r24 != 0) goto L_0x03ec
            if (r47 != 0) goto L_0x03ec
            if (r49 != 0) goto L_0x03ec
            if (r3 != r4) goto L_0x03ec
            if (r6 != r11) goto L_0x03ec
            r23 = r9
            r9 = r17
            r21 = r9
            r15 = 0
            r27 = 0
            goto L_0x03f1
        L_0x03ec:
            r23 = r9
            r9 = r15
            r15 = r32
        L_0x03f1:
            int r4 = r12.getMargin()
            r28 = r8
            int r8 = r13.getMargin()
            r12 = r35
            r14 = r7
            r32 = r15
            r13 = r17
            r11 = r23
            r15 = r28
            r7 = r5
            r5 = r45
            r1.addCentering(r2, r3, r4, r5, r6, r7, r8, r9)
            r5 = r6
            r6 = r2
            r2 = r5
            r5 = r7
            goto L_0x041a
        L_0x0411:
            r11 = r6
            r6 = r2
            r2 = r11
            r12 = r4
            r14 = r7
            r15 = r8
            r11 = r9
            r13 = r17
        L_0x041a:
            r4 = r32
            int r7 = r0.mVisibility
            if (r7 != r13) goto L_0x0428
            boolean r7 = r40.hasDependents()
            if (r7 != 0) goto L_0x0428
            goto L_0x0566
        L_0x0428:
            if (r25 == 0) goto L_0x044e
            if (r4 == 0) goto L_0x043b
            if (r3 == r2) goto L_0x043b
            if (r24 != 0) goto L_0x043b
            boolean r7 = r14 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r7 != 0) goto L_0x0438
            boolean r7 = r15 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r7 == 0) goto L_0x043b
        L_0x0438:
            r7 = r16
            goto L_0x043d
        L_0x043b:
            r7 = r21
        L_0x043d:
            int r8 = r39.getMargin()
            r1.addGreaterThan(r6, r3, r8, r7)
            int r8 = r40.getMargin()
            int r8 = -r8
            r1.addLowerThan(r5, r2, r8, r7)
            r21 = r7
        L_0x044e:
            if (r4 == 0) goto L_0x0462
            if (r50 == 0) goto L_0x0462
            boolean r7 = r14 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r7 != 0) goto L_0x0462
            boolean r7 = r15 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r7 != 0) goto L_0x0462
            if (r15 == r11) goto L_0x0462
            r7 = r16
            r8 = r7
            r20 = 1
            goto L_0x0468
        L_0x0462:
            r7 = r19
            r8 = r21
            r20 = r27
        L_0x0468:
            if (r20 == 0) goto L_0x04b5
            if (r26 == 0) goto L_0x0495
            if (r49 == 0) goto L_0x0470
            if (r33 == 0) goto L_0x0495
        L_0x0470:
            if (r14 == r11) goto L_0x0477
            if (r15 != r11) goto L_0x0475
            goto L_0x0477
        L_0x0475:
            r16 = r7
        L_0x0477:
            boolean r9 = r14 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r9 != 0) goto L_0x047f
            boolean r9 = r15 instanceof androidx.constraintlayout.core.widgets.Guideline
            if (r9 == 0) goto L_0x0481
        L_0x047f:
            r16 = 5
        L_0x0481:
            boolean r9 = r14 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r9 != 0) goto L_0x0489
            boolean r9 = r15 instanceof androidx.constraintlayout.core.widgets.Barrier
            if (r9 == 0) goto L_0x048b
        L_0x0489:
            r16 = 5
        L_0x048b:
            if (r49 == 0) goto L_0x048f
            r9 = 5
            goto L_0x0491
        L_0x048f:
            r9 = r16
        L_0x0491:
            int r7 = java.lang.Math.max(r9, r7)
        L_0x0495:
            if (r4 == 0) goto L_0x04a5
            int r7 = java.lang.Math.min(r8, r7)
            if (r46 == 0) goto L_0x04a5
            if (r49 != 0) goto L_0x04a5
            if (r14 == r11) goto L_0x04a3
            if (r15 != r11) goto L_0x04a5
        L_0x04a3:
            r11 = 4
            goto L_0x04a6
        L_0x04a5:
            r11 = r7
        L_0x04a6:
            int r7 = r39.getMargin()
            r1.addEquality(r6, r3, r7, r11)
            int r7 = r40.getMargin()
            int r7 = -r7
            r1.addEquality(r5, r2, r7, r11)
        L_0x04b5:
            if (r4 == 0) goto L_0x04c5
            if (r12 != r3) goto L_0x04be
            int r7 = r39.getMargin()
            goto L_0x04bf
        L_0x04be:
            r7 = 0
        L_0x04bf:
            if (r3 == r12) goto L_0x04c5
            r3 = 5
            r1.addGreaterThan(r6, r12, r7, r3)
        L_0x04c5:
            if (r4 == 0) goto L_0x04dd
            if (r24 == 0) goto L_0x04dd
            if (r43 != 0) goto L_0x04dd
            if (r22 != 0) goto L_0x04dd
            if (r24 == 0) goto L_0x04d7
            r11 = 3
            if (r10 != r11) goto L_0x04d7
            r3 = 0
            r1.addGreaterThan(r5, r6, r3, r13)
            goto L_0x04dd
        L_0x04d7:
            r3 = 0
            r7 = 5
            r1.addGreaterThan(r5, r6, r3, r7)
            goto L_0x04e3
        L_0x04dd:
            r7 = 5
            goto L_0x04e3
        L_0x04df:
            r2 = r11
            r7 = 5
        L_0x04e1:
            r4 = r32
        L_0x04e3:
            r11 = r7
        L_0x04e4:
            if (r4 == 0) goto L_0x0566
            if (r18 == 0) goto L_0x0566
            r13 = r40
            androidx.constraintlayout.core.widgets.ConstraintAnchor r3 = r13.mTarget
            if (r3 == 0) goto L_0x04f3
            int r3 = r13.getMargin()
            goto L_0x04f4
        L_0x04f3:
            r3 = 0
        L_0x04f4:
            r4 = r36
            if (r2 == r4) goto L_0x0566
            boolean r2 = r0.OPTIMIZE_WRAP
            if (r2 == 0) goto L_0x0510
            boolean r2 = r5.isFinalValue
            if (r2 == 0) goto L_0x0510
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r0.mParent
            if (r2 == 0) goto L_0x0510
            androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r2 = (androidx.constraintlayout.core.widgets.ConstraintWidgetContainer) r2
            if (r31 == 0) goto L_0x050c
            r2.addHorizontalWrapMaxVariable(r13)
            return
        L_0x050c:
            r2.addVerticalWrapMaxVariable(r13)
            return
        L_0x0510:
            r1.addGreaterThan(r4, r5, r3, r11)
            return
        L_0x0514:
            r12 = r35
            r4 = r36
            r13 = 8
            r11 = 2
        L_0x051b:
            if (r2 >= r11) goto L_0x0566
            if (r32 == 0) goto L_0x0566
            if (r18 == 0) goto L_0x0566
            r3 = 0
            r1.addGreaterThan(r6, r12, r3, r13)
            if (r31 != 0) goto L_0x0530
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 != 0) goto L_0x052e
            goto L_0x0530
        L_0x052e:
            r3 = 0
            goto L_0x0531
        L_0x0530:
            r3 = 1
        L_0x0531:
            if (r31 != 0) goto L_0x055f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            if (r2 == 0) goto L_0x055f
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r0.mBaseline
            androidx.constraintlayout.core.widgets.ConstraintAnchor r2 = r2.mTarget
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r2.mOwner
            float r3 = r2.mDimensionRatio
            r6 = 0
            int r3 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r3 == 0) goto L_0x055d
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r3 = r2.mListDimensionBehaviors
            r21 = 0
            r3 = r3[r21]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r6 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r3 != r6) goto L_0x055d
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r2 = r2.mListDimensionBehaviors
            r20 = 1
            r2 = r2[r20]
            androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            if (r2 != r3) goto L_0x055d
            r11 = r20
            goto L_0x0560
        L_0x055d:
            r11 = 0
            goto L_0x0560
        L_0x055f:
            r11 = r3
        L_0x0560:
            if (r11 == 0) goto L_0x0566
            r3 = 0
            r1.addGreaterThan(r4, r5, r3, r13)
        L_0x0566:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.applyConstraints(androidx.constraintlayout.core.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour, boolean, androidx.constraintlayout.core.widgets.ConstraintAnchor, androidx.constraintlayout.core.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    /* renamed from: androidx.constraintlayout.core.widgets.ConstraintWidget$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type;
        static final /* synthetic */ int[] $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0062 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x006d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0083 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x008f */
        static {
            /*
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour[] r0 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour = r0
                r1 = 1
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r2 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.FIXED     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x001d }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r3 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.WRAP_CONTENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0028 }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r4 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintWidget$DimensionBehaviour     // Catch:{ NoSuchFieldError -> 0x0033 }
                androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour r5 = androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type[] r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.values()
                int r4 = r4.length
                int[] r4 = new int[r4]
                $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type = r4
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r5 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT     // Catch:{ NoSuchFieldError -> 0x0044 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0044 }
                r4[r5] = r1     // Catch:{ NoSuchFieldError -> 0x0044 }
            L_0x0044:
                int[] r1 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x004e }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r4 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP     // Catch:{ NoSuchFieldError -> 0x004e }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x004e }
                r1[r4] = r0     // Catch:{ NoSuchFieldError -> 0x004e }
            L_0x004e:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0058 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0062 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM     // Catch:{ NoSuchFieldError -> 0x0062 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0062 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0062 }
            L_0x0062:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x006d }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BASELINE     // Catch:{ NoSuchFieldError -> 0x006d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006d }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006d }
            L_0x006d:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0078 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x0083 }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER_X     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x008f }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER_Y     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$androidx$constraintlayout$core$widgets$ConstraintAnchor$Type     // Catch:{ NoSuchFieldError -> 0x009b }
                androidx.constraintlayout.core.widgets.ConstraintAnchor$Type r1 = androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.NONE     // Catch:{ NoSuchFieldError -> 0x009b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009b }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009b }
            L_0x009b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.AnonymousClass1.<clinit>():void");
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue = linearSystem.getObjectVariableValue(this.mLeft);
        int objectVariableValue2 = linearSystem.getObjectVariableValue(this.mTop);
        int objectVariableValue3 = linearSystem.getObjectVariableValue(this.mRight);
        int objectVariableValue4 = linearSystem.getObjectVariableValue(this.mBottom);
        if (z && (horizontalWidgetRun = this.horizontalRun) != null && horizontalWidgetRun.start.resolved && this.horizontalRun.end.resolved) {
            objectVariableValue = this.horizontalRun.start.value;
            objectVariableValue3 = this.horizontalRun.end.value;
        }
        if (z && (verticalWidgetRun = this.verticalRun) != null && verticalWidgetRun.start.resolved && this.verticalRun.end.resolved) {
            objectVariableValue2 = this.verticalRun.start.value;
            objectVariableValue4 = this.verticalRun.end.value;
        }
        int i = objectVariableValue4 - objectVariableValue2;
        if (objectVariableValue3 - objectVariableValue < 0 || i < 0 || objectVariableValue == Integer.MIN_VALUE || objectVariableValue == Integer.MAX_VALUE || objectVariableValue2 == Integer.MIN_VALUE || objectVariableValue2 == Integer.MAX_VALUE || objectVariableValue3 == Integer.MIN_VALUE || objectVariableValue3 == Integer.MAX_VALUE || objectVariableValue4 == Integer.MIN_VALUE || objectVariableValue4 == Integer.MAX_VALUE) {
            objectVariableValue = 0;
            objectVariableValue4 = 0;
            objectVariableValue2 = 0;
            objectVariableValue3 = 0;
        }
        setFrame(objectVariableValue, objectVariableValue2, objectVariableValue3, objectVariableValue4);
    }

    public void copy(ConstraintWidget constraintWidget, HashMap<ConstraintWidget, ConstraintWidget> hashMap) {
        this.mHorizontalResolution = constraintWidget.mHorizontalResolution;
        this.mVerticalResolution = constraintWidget.mVerticalResolution;
        this.mMatchConstraintDefaultWidth = constraintWidget.mMatchConstraintDefaultWidth;
        this.mMatchConstraintDefaultHeight = constraintWidget.mMatchConstraintDefaultHeight;
        int[] iArr = this.mResolvedMatchConstraintDefault;
        int[] iArr2 = constraintWidget.mResolvedMatchConstraintDefault;
        iArr[0] = iArr2[0];
        iArr[1] = iArr2[1];
        this.mMatchConstraintMinWidth = constraintWidget.mMatchConstraintMinWidth;
        this.mMatchConstraintMaxWidth = constraintWidget.mMatchConstraintMaxWidth;
        this.mMatchConstraintMinHeight = constraintWidget.mMatchConstraintMinHeight;
        this.mMatchConstraintMaxHeight = constraintWidget.mMatchConstraintMaxHeight;
        this.mMatchConstraintPercentHeight = constraintWidget.mMatchConstraintPercentHeight;
        this.mIsWidthWrapContent = constraintWidget.mIsWidthWrapContent;
        this.mIsHeightWrapContent = constraintWidget.mIsHeightWrapContent;
        this.mResolvedDimensionRatioSide = constraintWidget.mResolvedDimensionRatioSide;
        this.mResolvedDimensionRatio = constraintWidget.mResolvedDimensionRatio;
        int[] iArr3 = constraintWidget.mMaxDimension;
        this.mMaxDimension = Arrays.copyOf(iArr3, iArr3.length);
        this.mCircleConstraintAngle = constraintWidget.mCircleConstraintAngle;
        this.hasBaseline = constraintWidget.hasBaseline;
        this.inPlaceholder = constraintWidget.inPlaceholder;
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mListDimensionBehaviors = (DimensionBehaviour[]) Arrays.copyOf(this.mListDimensionBehaviors, 2);
        ConstraintWidget constraintWidget2 = null;
        this.mParent = this.mParent == null ? null : hashMap.get(constraintWidget.mParent);
        this.mWidth = constraintWidget.mWidth;
        this.mHeight = constraintWidget.mHeight;
        this.mDimensionRatio = constraintWidget.mDimensionRatio;
        this.mDimensionRatioSide = constraintWidget.mDimensionRatioSide;
        this.mX = constraintWidget.mX;
        this.mY = constraintWidget.mY;
        this.mRelX = constraintWidget.mRelX;
        this.mRelY = constraintWidget.mRelY;
        this.mOffsetX = constraintWidget.mOffsetX;
        this.mOffsetY = constraintWidget.mOffsetY;
        this.mBaselineDistance = constraintWidget.mBaselineDistance;
        this.mMinWidth = constraintWidget.mMinWidth;
        this.mMinHeight = constraintWidget.mMinHeight;
        this.mHorizontalBiasPercent = constraintWidget.mHorizontalBiasPercent;
        this.mVerticalBiasPercent = constraintWidget.mVerticalBiasPercent;
        this.mCompanionWidget = constraintWidget.mCompanionWidget;
        this.mContainerItemSkip = constraintWidget.mContainerItemSkip;
        this.mVisibility = constraintWidget.mVisibility;
        this.mAnimated = constraintWidget.mAnimated;
        this.mDebugName = constraintWidget.mDebugName;
        this.mType = constraintWidget.mType;
        this.mDistToTop = constraintWidget.mDistToTop;
        this.mDistToLeft = constraintWidget.mDistToLeft;
        this.mDistToRight = constraintWidget.mDistToRight;
        this.mDistToBottom = constraintWidget.mDistToBottom;
        this.mLeftHasCentered = constraintWidget.mLeftHasCentered;
        this.mRightHasCentered = constraintWidget.mRightHasCentered;
        this.mTopHasCentered = constraintWidget.mTopHasCentered;
        this.mBottomHasCentered = constraintWidget.mBottomHasCentered;
        this.mHorizontalWrapVisited = constraintWidget.mHorizontalWrapVisited;
        this.mVerticalWrapVisited = constraintWidget.mVerticalWrapVisited;
        this.mHorizontalChainStyle = constraintWidget.mHorizontalChainStyle;
        this.mVerticalChainStyle = constraintWidget.mVerticalChainStyle;
        this.mHorizontalChainFixedPosition = constraintWidget.mHorizontalChainFixedPosition;
        this.mVerticalChainFixedPosition = constraintWidget.mVerticalChainFixedPosition;
        float[] fArr = this.mWeight;
        float[] fArr2 = constraintWidget.mWeight;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[1];
        ConstraintWidget[] constraintWidgetArr = this.mListNextMatchConstraintsWidget;
        ConstraintWidget[] constraintWidgetArr2 = constraintWidget.mListNextMatchConstraintsWidget;
        constraintWidgetArr[0] = constraintWidgetArr2[0];
        constraintWidgetArr[1] = constraintWidgetArr2[1];
        ConstraintWidget[] constraintWidgetArr3 = this.mNextChainWidget;
        ConstraintWidget[] constraintWidgetArr4 = constraintWidget.mNextChainWidget;
        constraintWidgetArr3[0] = constraintWidgetArr4[0];
        constraintWidgetArr3[1] = constraintWidgetArr4[1];
        ConstraintWidget constraintWidget3 = constraintWidget.mHorizontalNextWidget;
        this.mHorizontalNextWidget = constraintWidget3 == null ? null : hashMap.get(constraintWidget3);
        ConstraintWidget constraintWidget4 = constraintWidget.mVerticalNextWidget;
        if (constraintWidget4 != null) {
            constraintWidget2 = hashMap.get(constraintWidget4);
        }
        this.mVerticalNextWidget = constraintWidget2;
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        boolean isResolved = z & this.horizontalRun.isResolved();
        boolean isResolved2 = z2 & this.verticalRun.isResolved();
        int i3 = this.horizontalRun.start.value;
        int i4 = this.verticalRun.start.value;
        int i5 = this.horizontalRun.end.value;
        int i6 = this.verticalRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (isResolved) {
            this.mX = i3;
        }
        if (isResolved2) {
            this.mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (isResolved) {
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i10 = this.mMinWidth;
            if (i8 < i10) {
                this.mWidth = i10;
            }
        }
        if (isResolved2) {
            if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && i9 < (i = this.mHeight)) {
                i9 = i;
            }
            this.mHeight = i9;
            int i11 = this.mMinHeight;
            if (i9 < i11) {
                this.mHeight = i11;
            }
        }
    }

    public void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet<ConstraintWidget> hashSet, int i, boolean z) {
        if (z) {
            if (hashSet.contains(this)) {
                Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
                hashSet.remove(this);
                addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
            } else {
                return;
            }
        }
        if (i == 0) {
            HashSet<ConstraintAnchor> dependents = this.mLeft.getDependents();
            if (dependents != null) {
                Iterator<ConstraintAnchor> it = dependents.iterator();
                while (it.hasNext()) {
                    it.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
            }
            HashSet<ConstraintAnchor> dependents2 = this.mRight.getDependents();
            if (dependents2 != null) {
                Iterator<ConstraintAnchor> it2 = dependents2.iterator();
                while (it2.hasNext()) {
                    it2.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
                }
                return;
            }
            return;
        }
        HashSet<ConstraintAnchor> dependents3 = this.mTop.getDependents();
        if (dependents3 != null) {
            Iterator<ConstraintAnchor> it3 = dependents3.iterator();
            while (it3.hasNext()) {
                it3.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents4 = this.mBottom.getDependents();
        if (dependents4 != null) {
            Iterator<ConstraintAnchor> it4 = dependents4.iterator();
            while (it4.hasNext()) {
                it4.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
        HashSet<ConstraintAnchor> dependents5 = this.mBaseline.getDependents();
        if (dependents5 != null) {
            Iterator<ConstraintAnchor> it5 = dependents5.iterator();
            while (it5.hasNext()) {
                it5.next().mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, i, true);
            }
        }
    }

    public void getSceneString(StringBuilder sb) {
        sb.append("  " + this.stringId + ":{\n");
        StringBuilder sb2 = new StringBuilder("    actualWidth:");
        sb2.append(this.mWidth);
        sb.append(sb2.toString());
        sb.append("\n");
        sb.append("    actualHeight:" + this.mHeight);
        sb.append("\n");
        sb.append("    actualLeft:" + this.mX);
        sb.append("\n");
        sb.append("    actualTop:" + this.mY);
        sb.append("\n");
        getSceneString(sb, "left", this.mLeft);
        getSceneString(sb, "top", this.mTop);
        getSceneString(sb, "right", this.mRight);
        getSceneString(sb, "bottom", this.mBottom);
        getSceneString(sb, "baseline", this.mBaseline);
        getSceneString(sb, "centerX", this.mCenterX);
        getSceneString(sb, "centerY", this.mCenterY);
        getSceneString(sb, "    width", this.mWidth, this.mMinWidth, this.mMaxDimension[0], this.mWidthOverride, this.mMatchConstraintMinWidth, this.mMatchConstraintDefaultWidth, this.mMatchConstraintPercentWidth, this.mWeight[0]);
        getSceneString(sb, "    height", this.mHeight, this.mMinHeight, this.mMaxDimension[1], this.mHeightOverride, this.mMatchConstraintMinHeight, this.mMatchConstraintDefaultHeight, this.mMatchConstraintPercentHeight, this.mWeight[1]);
        serializeDimensionRatio(sb, "    dimensionRatio", this.mDimensionRatio, this.mDimensionRatioSide);
        serializeAttribute(sb, "    horizontalBias", this.mHorizontalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    verticalBias", this.mVerticalBiasPercent, DEFAULT_BIAS);
        serializeAttribute(sb, "    horizontalChainStyle", this.mHorizontalChainStyle, 0);
        serializeAttribute(sb, "    verticalChainStyle", this.mVerticalChainStyle, 0);
        sb.append("  }");
    }

    private void getSceneString(StringBuilder sb, String str, int i, int i2, int i3, int i4, int i5, int i6, float f, float f2) {
        sb.append(str);
        sb.append(" :  {\n");
        serializeAttribute(sb, "      size", i, 0);
        serializeAttribute(sb, "      min", i2, 0);
        serializeAttribute(sb, "      max", i3, Integer.MAX_VALUE);
        serializeAttribute(sb, "      matchMin", i5, 0);
        serializeAttribute(sb, "      matchDef", i6, 0);
        serializeAttribute(sb, "      matchPercent", f, 1.0f);
        sb.append("    },\n");
    }

    private void getSceneString(StringBuilder sb, String str, ConstraintAnchor constraintAnchor) {
        if (constraintAnchor.mTarget != null) {
            sb.append("    ");
            sb.append(str);
            sb.append(" : [ '");
            sb.append(constraintAnchor.mTarget);
            sb.append("'");
            if (!(constraintAnchor.mGoneMargin == Integer.MIN_VALUE && constraintAnchor.mMargin == 0)) {
                sb.append(",");
                sb.append(constraintAnchor.mMargin);
                if (constraintAnchor.mGoneMargin != Integer.MIN_VALUE) {
                    sb.append(",");
                    sb.append(constraintAnchor.mGoneMargin);
                    sb.append(",");
                }
            }
            sb.append(" ] ,\n");
        }
    }
}
