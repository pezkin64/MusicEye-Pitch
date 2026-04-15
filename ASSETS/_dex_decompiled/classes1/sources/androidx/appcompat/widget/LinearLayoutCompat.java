package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import androidx.appcompat.R;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    /* access modifiers changed from: package-private */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, (AttributeSet) null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context, R.styleable.LinearLayoutCompat, attributeSet, obtainStyledAttributes.getWrappedTypeArray(), i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersVertical(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i2))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                i = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                i = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int i3;
        int i4;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i5 = 0; i5 < virtualChildCount; i5++) {
            View virtualChildAt = getVirtualChildAt(i5);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i5))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    i4 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    i4 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, i4);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    i3 = virtualChildAt2.getLeft() - layoutParams2.leftMargin;
                    i2 = this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                    drawVerticalDivider(canvas, i);
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
                drawVerticalDivider(canvas, i);
            } else {
                i3 = getWidth() - getPaddingRight();
                i2 = this.mDividerWidth;
            }
            i = i3 - i2;
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: package-private */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: package-private */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i2 = this.mBaselineAlignedChildIndex;
        if (childCount > i2) {
            View childAt = getChildAt(i2);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i3 = this.mBaselineChildTop;
                if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
                    if (i == 16) {
                        i3 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                    } else if (i == 80) {
                        i3 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                    }
                }
                return i3 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    /* access modifiers changed from: package-private */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: package-private */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    public boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) != 0) {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0306, code lost:
        if (r13.width == -1) goto L_0x030d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0168  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0171  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureVertical(int r28, int r29) {
        /*
            r27 = this;
            r0 = r27
            r7 = 0
            r0.mTotalLength = r7
            int r8 = r0.getVirtualChildCount()
            int r9 = android.view.View.MeasureSpec.getMode(r28)
            int r10 = android.view.View.MeasureSpec.getMode(r29)
            int r11 = r0.mBaselineAlignedChildIndex
            boolean r12 = r0.mUseLargestChild
            r2 = r7
            r3 = r2
            r4 = r3
            r5 = r4
            r6 = r5
            r15 = r6
            r16 = r15
            r18 = r16
            r1 = 0
            r17 = 1
            r19 = 1
        L_0x0024:
            r20 = 0
            r7 = 8
            r14 = 1073741824(0x40000000, float:2.0)
            if (r2 >= r8) goto L_0x0192
            r21 = r1
            android.view.View r1 = r0.getVirtualChildAt(r2)
            if (r1 != 0) goto L_0x004f
            int r1 = r0.mTotalLength
            int r7 = r0.measureNullChild(r2)
            int r1 = r1 + r7
            r0.mTotalLength = r1
        L_0x003d:
            r22 = r4
            r26 = r8
            r24 = r10
            r25 = r12
            r1 = r21
            r4 = r3
            r10 = r5
            r3 = r28
            r5 = r29
            goto L_0x0183
        L_0x004f:
            int r13 = r1.getVisibility()
            if (r13 != r7) goto L_0x005b
            int r1 = r0.getChildrenSkipCount(r1, r2)
            int r2 = r2 + r1
            goto L_0x003d
        L_0x005b:
            boolean r7 = r0.hasDividerBeforeChildAt(r2)
            if (r7 == 0) goto L_0x0068
            int r7 = r0.mTotalLength
            int r13 = r0.mDividerHeight
            int r7 = r7 + r13
            r0.mTotalLength = r7
        L_0x0068:
            android.view.ViewGroup$LayoutParams r7 = r1.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r7 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r7
            float r13 = r7.weight
            float r13 = r21 + r13
            if (r10 != r14) goto L_0x00a6
            int r14 = r7.height
            if (r14 != 0) goto L_0x00a6
            float r14 = r7.weight
            int r14 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1))
            if (r14 <= 0) goto L_0x00a6
            int r14 = r0.mTotalLength
            r24 = r1
            int r1 = r7.topMargin
            int r1 = r1 + r14
            r16 = r1
            int r1 = r7.bottomMargin
            int r1 = r16 + r1
            int r1 = java.lang.Math.max(r14, r1)
            r0.mTotalLength = r1
            r26 = r8
            r25 = r12
            r21 = r13
            r16 = r19
            r1 = r24
            r12 = r3
            r8 = r6
            r24 = r10
            r3 = r28
            r10 = r5
            r5 = r29
            goto L_0x0110
        L_0x00a6:
            r24 = r1
            int r1 = r7.height
            if (r1 != 0) goto L_0x00b7
            float r1 = r7.weight
            int r1 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r1 <= 0) goto L_0x00b7
            r1 = -2
            r7.height = r1
            r14 = 0
            goto L_0x00b9
        L_0x00b7:
            r14 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00b9:
            int r1 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r1 != 0) goto L_0x00c5
            int r1 = r0.mTotalLength
            r21 = r6
            r6 = r1
            r1 = r21
            goto L_0x00c7
        L_0x00c5:
            r1 = r6
            r6 = 0
        L_0x00c7:
            r21 = r4
            r4 = 0
            r25 = r21
            r21 = r13
            r13 = r25
            r26 = r8
            r25 = r12
            r8 = r1
            r12 = r3
            r1 = r24
            r3 = r28
            r24 = r10
            r10 = r5
            r5 = r29
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r14 == r4) goto L_0x00e8
            r7.height = r14
        L_0x00e8:
            int r4 = r1.getMeasuredHeight()
            int r6 = r0.mTotalLength
            int r14 = r6 + r4
            r22 = r14
            int r14 = r7.topMargin
            int r14 = r22 + r14
            r22 = r14
            int r14 = r7.bottomMargin
            int r14 = r22 + r14
            int r22 = r0.getNextLocationOffset(r1)
            int r14 = r14 + r22
            int r6 = java.lang.Math.max(r6, r14)
            r0.mTotalLength = r6
            if (r25 == 0) goto L_0x010f
            int r4 = java.lang.Math.max(r4, r13)
            goto L_0x0110
        L_0x010f:
            r4 = r13
        L_0x0110:
            if (r11 < 0) goto L_0x011a
            int r6 = r2 + 1
            if (r11 != r6) goto L_0x011a
            int r6 = r0.mTotalLength
            r0.mBaselineChildTop = r6
        L_0x011a:
            if (r2 >= r11) goto L_0x012b
            float r6 = r7.weight
            int r6 = (r6 > r20 ? 1 : (r6 == r20 ? 0 : -1))
            if (r6 > 0) goto L_0x0123
            goto L_0x012b
        L_0x0123:
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r1.<init>(r2)
            throw r1
        L_0x012b:
            r6 = 1073741824(0x40000000, float:2.0)
            if (r9 == r6) goto L_0x0139
            int r6 = r7.width
            r13 = -1
            if (r6 != r13) goto L_0x0139
            r6 = r19
            r18 = r6
            goto L_0x013a
        L_0x0139:
            r6 = 0
        L_0x013a:
            int r13 = r7.leftMargin
            int r14 = r7.rightMargin
            int r13 = r13 + r14
            int r14 = r1.getMeasuredWidth()
            int r14 = r14 + r13
            int r10 = java.lang.Math.max(r10, r14)
            r22 = r4
            int r4 = r1.getMeasuredState()
            int r4 = android.view.View.combineMeasuredStates(r8, r4)
            if (r17 == 0) goto L_0x015e
            int r8 = r7.width
            r23 = r4
            r4 = -1
            if (r8 != r4) goto L_0x0160
            r17 = r19
            goto L_0x0162
        L_0x015e:
            r23 = r4
        L_0x0160:
            r17 = 0
        L_0x0162:
            float r4 = r7.weight
            int r4 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r4 <= 0) goto L_0x0171
            if (r6 == 0) goto L_0x016b
            goto L_0x016c
        L_0x016b:
            r13 = r14
        L_0x016c:
            int r4 = java.lang.Math.max(r12, r13)
            goto L_0x017a
        L_0x0171:
            if (r6 == 0) goto L_0x0174
            goto L_0x0175
        L_0x0174:
            r13 = r14
        L_0x0175:
            int r15 = java.lang.Math.max(r15, r13)
            r4 = r12
        L_0x017a:
            int r1 = r0.getChildrenSkipCount(r1, r2)
            int r2 = r2 + r1
            r1 = r21
            r6 = r23
        L_0x0183:
            int r2 = r2 + 1
            r3 = r4
            r5 = r10
            r4 = r22
            r10 = r24
            r12 = r25
            r8 = r26
            r7 = 0
            goto L_0x0024
        L_0x0192:
            r21 = r1
            r13 = r4
            r26 = r8
            r24 = r10
            r25 = r12
            r12 = r3
            r10 = r5
            r8 = r6
            r3 = r28
            r5 = r29
            int r1 = r0.mTotalLength
            if (r1 <= 0) goto L_0x01b6
            r1 = r26
            boolean r2 = r0.hasDividerBeforeChildAt(r1)
            if (r2 == 0) goto L_0x01b8
            int r2 = r0.mTotalLength
            int r4 = r0.mDividerHeight
            int r2 = r2 + r4
            r0.mTotalLength = r2
            goto L_0x01b8
        L_0x01b6:
            r1 = r26
        L_0x01b8:
            r2 = r24
            if (r25 == 0) goto L_0x0207
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r2 == r4) goto L_0x01c2
            if (r2 != 0) goto L_0x0207
        L_0x01c2:
            r4 = 0
            r0.mTotalLength = r4
            r4 = 0
        L_0x01c6:
            if (r4 >= r1) goto L_0x0207
            android.view.View r6 = r0.getVirtualChildAt(r4)
            if (r6 != 0) goto L_0x01d8
            int r6 = r0.mTotalLength
            int r11 = r0.measureNullChild(r4)
            int r6 = r6 + r11
            r0.mTotalLength = r6
            goto L_0x0202
        L_0x01d8:
            int r11 = r6.getVisibility()
            if (r11 != r7) goto L_0x01e4
            int r6 = r0.getChildrenSkipCount(r6, r4)
            int r4 = r4 + r6
            goto L_0x0202
        L_0x01e4:
            android.view.ViewGroup$LayoutParams r11 = r6.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r11 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r11
            int r14 = r0.mTotalLength
            int r22 = r14 + r13
            int r7 = r11.topMargin
            int r22 = r22 + r7
            int r7 = r11.bottomMargin
            int r22 = r22 + r7
            int r6 = r0.getNextLocationOffset(r6)
            int r6 = r22 + r6
            int r6 = java.lang.Math.max(r14, r6)
            r0.mTotalLength = r6
        L_0x0202:
            int r4 = r4 + 1
            r7 = 8
            goto L_0x01c6
        L_0x0207:
            int r4 = r0.mTotalLength
            int r6 = r0.getPaddingTop()
            int r7 = r0.getPaddingBottom()
            int r6 = r6 + r7
            int r4 = r4 + r6
            r0.mTotalLength = r4
            int r6 = r0.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r4, r6)
            r6 = 0
            int r4 = android.view.View.resolveSizeAndState(r4, r5, r6)
            r6 = 16777215(0xffffff, float:2.3509886E-38)
            r6 = r6 & r4
            int r7 = r0.mTotalLength
            int r6 = r6 - r7
            if (r16 != 0) goto L_0x026e
            if (r6 == 0) goto L_0x0232
            int r7 = (r21 > r20 ? 1 : (r21 == r20 ? 0 : -1))
            if (r7 <= 0) goto L_0x0232
            goto L_0x026e
        L_0x0232:
            int r6 = java.lang.Math.max(r15, r12)
            if (r25 == 0) goto L_0x034c
            r7 = 1073741824(0x40000000, float:2.0)
            if (r2 == r7) goto L_0x034c
            r7 = 0
        L_0x023d:
            if (r7 >= r1) goto L_0x034c
            android.view.View r2 = r0.getVirtualChildAt(r7)
            if (r2 == 0) goto L_0x026b
            int r11 = r2.getVisibility()
            r12 = 8
            if (r11 != r12) goto L_0x024e
            goto L_0x026b
        L_0x024e:
            android.view.ViewGroup$LayoutParams r11 = r2.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r11 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r11
            float r11 = r11.weight
            int r11 = (r11 > r20 ? 1 : (r11 == r20 ? 0 : -1))
            if (r11 <= 0) goto L_0x026b
            int r11 = r2.getMeasuredWidth()
            r12 = 1073741824(0x40000000, float:2.0)
            int r11 = android.view.View.MeasureSpec.makeMeasureSpec(r11, r12)
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r12)
            r2.measure(r11, r14)
        L_0x026b:
            int r7 = r7 + 1
            goto L_0x023d
        L_0x026e:
            float r7 = r0.mWeightSum
            int r11 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r11 <= 0) goto L_0x0276
            r21 = r7
        L_0x0276:
            r7 = 0
            r0.mTotalLength = r7
            r11 = r6
            r6 = r7
        L_0x027b:
            if (r6 >= r1) goto L_0x033d
            android.view.View r12 = r0.getVirtualChildAt(r6)
            int r13 = r12.getVisibility()
            r14 = 8
            if (r13 != r14) goto L_0x028f
            r22 = r2
            r16 = r6
            goto L_0x0336
        L_0x028f:
            android.view.ViewGroup$LayoutParams r13 = r12.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r13 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r13
            float r7 = r13.weight
            int r16 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r16 <= 0) goto L_0x02ed
            float r14 = (float) r11
            float r14 = r14 * r7
            float r14 = r14 / r21
            int r14 = (int) r14
            float r21 = r21 - r7
            int r11 = r11 - r14
            int r7 = r0.getPaddingLeft()
            int r16 = r0.getPaddingRight()
            int r7 = r7 + r16
            r16 = r6
            int r6 = r13.leftMargin
            int r7 = r7 + r6
            int r6 = r13.rightMargin
            int r7 = r7 + r6
            int r6 = r13.width
            int r6 = getChildMeasureSpec(r3, r7, r6)
            int r7 = r13.height
            if (r7 != 0) goto L_0x02d0
            r7 = 1073741824(0x40000000, float:2.0)
            if (r2 == r7) goto L_0x02c4
            goto L_0x02d2
        L_0x02c4:
            if (r14 <= 0) goto L_0x02c7
            goto L_0x02c8
        L_0x02c7:
            r14 = 0
        L_0x02c8:
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r7)
            r12.measure(r6, r14)
            goto L_0x02e2
        L_0x02d0:
            r7 = 1073741824(0x40000000, float:2.0)
        L_0x02d2:
            int r22 = r12.getMeasuredHeight()
            int r14 = r22 + r14
            if (r14 >= 0) goto L_0x02db
            r14 = 0
        L_0x02db:
            int r14 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r7)
            r12.measure(r6, r14)
        L_0x02e2:
            int r6 = r12.getMeasuredState()
            r6 = r6 & -256(0xffffffffffffff00, float:NaN)
            int r8 = android.view.View.combineMeasuredStates(r8, r6)
            goto L_0x02ef
        L_0x02ed:
            r16 = r6
        L_0x02ef:
            int r6 = r13.leftMargin
            int r7 = r13.rightMargin
            int r6 = r6 + r7
            int r7 = r12.getMeasuredWidth()
            int r7 = r7 + r6
            int r10 = java.lang.Math.max(r10, r7)
            r14 = 1073741824(0x40000000, float:2.0)
            if (r9 == r14) goto L_0x0309
            int r14 = r13.width
            r22 = r2
            r2 = -1
            if (r14 != r2) goto L_0x030c
            goto L_0x030d
        L_0x0309:
            r22 = r2
            r2 = -1
        L_0x030c:
            r6 = r7
        L_0x030d:
            int r6 = java.lang.Math.max(r15, r6)
            if (r17 == 0) goto L_0x031a
            int r7 = r13.width
            if (r7 != r2) goto L_0x031a
            r7 = r19
            goto L_0x031b
        L_0x031a:
            r7 = 0
        L_0x031b:
            int r14 = r0.mTotalLength
            int r15 = r12.getMeasuredHeight()
            int r15 = r15 + r14
            int r2 = r13.topMargin
            int r15 = r15 + r2
            int r2 = r13.bottomMargin
            int r15 = r15 + r2
            int r2 = r0.getNextLocationOffset(r12)
            int r15 = r15 + r2
            int r2 = java.lang.Math.max(r14, r15)
            r0.mTotalLength = r2
            r15 = r6
            r17 = r7
        L_0x0336:
            int r6 = r16 + 1
            r2 = r22
            r7 = 0
            goto L_0x027b
        L_0x033d:
            int r2 = r0.mTotalLength
            int r6 = r0.getPaddingTop()
            int r7 = r0.getPaddingBottom()
            int r6 = r6 + r7
            int r2 = r2 + r6
            r0.mTotalLength = r2
            r6 = r15
        L_0x034c:
            if (r17 != 0) goto L_0x0353
            r7 = 1073741824(0x40000000, float:2.0)
            if (r9 == r7) goto L_0x0353
            r10 = r6
        L_0x0353:
            int r2 = r0.getPaddingLeft()
            int r6 = r0.getPaddingRight()
            int r2 = r2 + r6
            int r10 = r10 + r2
            int r2 = r0.getSuggestedMinimumWidth()
            int r2 = java.lang.Math.max(r10, r2)
            int r2 = android.view.View.resolveSizeAndState(r2, r3, r8)
            r0.setMeasuredDimension(r2, r4)
            if (r18 == 0) goto L_0x0371
            r0.forceUniformWidth(r1, r5)
        L_0x0371:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int i, int i2) {
        int i3;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), BasicMeasure.EXACTLY);
        int i4 = 0;
        while (i4 < i) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i5 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    i3 = i2;
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i3, 0);
                    layoutParams.height = i5;
                    i4++;
                    i2 = i3;
                }
            }
            i3 = i2;
            i4++;
            i2 = i3;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x0461  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x0464  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r39, int r40) {
        /*
            r38 = this;
            r0 = r38
            r7 = 0
            r0.mTotalLength = r7
            int r8 = r0.getVirtualChildCount()
            int r9 = android.view.View.MeasureSpec.getMode(r39)
            int r10 = android.view.View.MeasureSpec.getMode(r40)
            int[] r1 = r0.mMaxAscent
            r11 = 4
            if (r1 == 0) goto L_0x001a
            int[] r1 = r0.mMaxDescent
            if (r1 != 0) goto L_0x0022
        L_0x001a:
            int[] r1 = new int[r11]
            r0.mMaxAscent = r1
            int[] r1 = new int[r11]
            r0.mMaxDescent = r1
        L_0x0022:
            int[] r12 = r0.mMaxAscent
            int[] r13 = r0.mMaxDescent
            r14 = 3
            r15 = -1
            r12[r14] = r15
            r16 = 2
            r12[r16] = r15
            r17 = 1
            r12[r17] = r15
            r12[r7] = r15
            r13[r14] = r15
            r13[r16] = r15
            r13[r17] = r15
            r13[r7] = r15
            boolean r1 = r0.mBaselineAligned
            boolean r2 = r0.mUseLargestChild
            r3 = 1073741824(0x40000000, float:2.0)
            if (r9 != r3) goto L_0x0047
            r18 = r17
            goto L_0x0049
        L_0x0047:
            r18 = r7
        L_0x0049:
            r19 = 0
            r4 = r2
            r2 = r7
            r6 = r2
            r15 = r6
            r22 = r15
            r24 = r22
            r26 = r24
            r20 = r11
            r21 = r14
            r25 = r17
            r5 = r19
            r11 = r26
            r14 = r11
        L_0x0060:
            r27 = r6
            r6 = 8
            if (r2 >= r8) goto L_0x020c
            r30 = r1
            android.view.View r1 = r0.getVirtualChildAt(r2)
            if (r1 != 0) goto L_0x0089
            int r1 = r0.mTotalLength
            int r6 = r0.measureNullChild(r2)
            int r1 = r1 + r6
            r0.mTotalLength = r1
        L_0x0077:
            r3 = r39
            r1 = r40
            r35 = r8
            r33 = r9
            r34 = r12
            r28 = r13
            r6 = r27
            r27 = r4
            goto L_0x01f9
        L_0x0089:
            int r7 = r1.getVisibility()
            if (r7 != r6) goto L_0x0095
            int r1 = r0.getChildrenSkipCount(r1, r2)
            int r2 = r2 + r1
            goto L_0x0077
        L_0x0095:
            boolean r6 = r0.hasDividerBeforeChildAt(r2)
            if (r6 == 0) goto L_0x00a2
            int r6 = r0.mTotalLength
            int r7 = r0.mDividerWidth
            int r6 = r6 + r7
            r0.mTotalLength = r6
        L_0x00a2:
            android.view.ViewGroup$LayoutParams r6 = r1.getLayoutParams()
            r7 = r6
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r7 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r7
            float r6 = r7.weight
            float r32 = r5 + r6
            if (r9 != r3) goto L_0x0102
            int r5 = r7.width
            if (r5 != 0) goto L_0x0102
            float r5 = r7.weight
            int r5 = (r5 > r19 ? 1 : (r5 == r19 ? 0 : -1))
            if (r5 <= 0) goto L_0x0102
            if (r18 == 0) goto L_0x00c6
            int r5 = r0.mTotalLength
            int r6 = r7.leftMargin
            int r3 = r7.rightMargin
            int r6 = r6 + r3
            int r5 = r5 + r6
            r0.mTotalLength = r5
            goto L_0x00d4
        L_0x00c6:
            int r3 = r0.mTotalLength
            int r5 = r7.leftMargin
            int r5 = r5 + r3
            int r6 = r7.rightMargin
            int r5 = r5 + r6
            int r3 = java.lang.Math.max(r3, r5)
            r0.mTotalLength = r3
        L_0x00d4:
            if (r30 == 0) goto L_0x00e9
            r3 = 0
            int r5 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r3)
            r1.measure(r5, r5)
            r3 = r39
            r35 = r8
            r33 = r9
            r34 = r12
            r28 = r13
            goto L_0x00f5
        L_0x00e9:
            r3 = r39
            r35 = r8
            r33 = r9
            r34 = r12
            r28 = r13
            r22 = r17
        L_0x00f5:
            r12 = r27
            r8 = 1073741824(0x40000000, float:2.0)
            r29 = -2
            r27 = r4
            r4 = r1
            r1 = r40
            goto L_0x0178
        L_0x0102:
            int r3 = r7.width
            if (r3 != 0) goto L_0x0111
            float r3 = r7.weight
            int r3 = (r3 > r19 ? 1 : (r3 == r19 ? 0 : -1))
            if (r3 <= 0) goto L_0x0111
            r3 = -2
            r7.width = r3
            r5 = 0
            goto L_0x0114
        L_0x0111:
            r3 = -2
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0114:
            int r6 = (r32 > r19 ? 1 : (r32 == r19 ? 0 : -1))
            if (r6 != 0) goto L_0x0120
            int r6 = r0.mTotalLength
            r37 = r6
            r6 = r4
            r4 = r37
            goto L_0x0122
        L_0x0120:
            r6 = r4
            r4 = 0
        L_0x0122:
            r28 = 0
            r29 = r3
            r35 = r8
            r33 = r9
            r34 = r12
            r12 = r27
            r8 = 1073741824(0x40000000, float:2.0)
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r39
            r27 = r6
            r6 = r28
            r28 = r13
            r13 = r5
            r5 = r40
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r4 = r1
            r1 = r5
            if (r13 == r9) goto L_0x0146
            r7.width = r13
        L_0x0146:
            int r5 = r4.getMeasuredWidth()
            if (r18 == 0) goto L_0x015d
            int r6 = r0.mTotalLength
            int r9 = r7.leftMargin
            int r9 = r9 + r5
            int r13 = r7.rightMargin
            int r9 = r9 + r13
            int r13 = r0.getNextLocationOffset(r4)
            int r9 = r9 + r13
            int r6 = r6 + r9
            r0.mTotalLength = r6
            goto L_0x0172
        L_0x015d:
            int r6 = r0.mTotalLength
            int r9 = r6 + r5
            int r13 = r7.leftMargin
            int r9 = r9 + r13
            int r13 = r7.rightMargin
            int r9 = r9 + r13
            int r13 = r0.getNextLocationOffset(r4)
            int r9 = r9 + r13
            int r6 = java.lang.Math.max(r6, r9)
            r0.mTotalLength = r6
        L_0x0172:
            if (r27 == 0) goto L_0x0178
            int r15 = java.lang.Math.max(r5, r15)
        L_0x0178:
            if (r10 == r8) goto L_0x0184
            int r5 = r7.height
            r6 = -1
            if (r5 != r6) goto L_0x0184
            r5 = r17
            r26 = r5
            goto L_0x0185
        L_0x0184:
            r5 = 0
        L_0x0185:
            int r6 = r7.topMargin
            int r9 = r7.bottomMargin
            int r6 = r6 + r9
            int r9 = r4.getMeasuredHeight()
            int r9 = r9 + r6
            int r13 = r4.getMeasuredState()
            r8 = r24
            int r24 = android.view.View.combineMeasuredStates(r8, r13)
            if (r30 == 0) goto L_0x01c8
            int r8 = r4.getBaseline()
            r13 = -1
            if (r8 == r13) goto L_0x01c8
            int r13 = r7.gravity
            if (r13 >= 0) goto L_0x01a9
            int r13 = r0.mGravity
            goto L_0x01ab
        L_0x01a9:
            int r13 = r7.gravity
        L_0x01ab:
            r13 = r13 & 112(0x70, float:1.57E-43)
            int r13 = r13 >> 4
            r13 = r13 & -2
            int r13 = r13 >> 1
            r36 = r5
            r5 = r34[r13]
            int r5 = java.lang.Math.max(r5, r8)
            r34[r13] = r5
            r5 = r28[r13]
            int r8 = r9 - r8
            int r5 = java.lang.Math.max(r5, r8)
            r28[r13] = r5
            goto L_0x01ca
        L_0x01c8:
            r36 = r5
        L_0x01ca:
            int r5 = java.lang.Math.max(r12, r9)
            if (r25 == 0) goto L_0x01d8
            int r8 = r7.height
            r13 = -1
            if (r8 != r13) goto L_0x01d8
            r25 = r17
            goto L_0x01da
        L_0x01d8:
            r25 = 0
        L_0x01da:
            float r7 = r7.weight
            int r7 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r7 <= 0) goto L_0x01e9
            if (r36 == 0) goto L_0x01e3
            goto L_0x01e4
        L_0x01e3:
            r6 = r9
        L_0x01e4:
            int r14 = java.lang.Math.max(r14, r6)
            goto L_0x01f1
        L_0x01e9:
            if (r36 == 0) goto L_0x01ec
            goto L_0x01ed
        L_0x01ec:
            r6 = r9
        L_0x01ed:
            int r11 = java.lang.Math.max(r11, r6)
        L_0x01f1:
            int r4 = r0.getChildrenSkipCount(r4, r2)
            int r2 = r2 + r4
            r6 = r5
            r5 = r32
        L_0x01f9:
            int r2 = r2 + 1
            r4 = r27
            r13 = r28
            r1 = r30
            r9 = r33
            r12 = r34
            r8 = r35
            r3 = 1073741824(0x40000000, float:2.0)
            r7 = 0
            goto L_0x0060
        L_0x020c:
            r3 = r39
            r30 = r1
            r35 = r8
            r33 = r9
            r34 = r12
            r28 = r13
            r8 = r24
            r12 = r27
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r29 = -2
            r1 = r40
            r27 = r4
            int r2 = r0.mTotalLength
            if (r2 <= 0) goto L_0x0238
            r2 = r35
            boolean r4 = r0.hasDividerBeforeChildAt(r2)
            if (r4 == 0) goto L_0x023a
            int r4 = r0.mTotalLength
            int r7 = r0.mDividerWidth
            int r4 = r4 + r7
            r0.mTotalLength = r4
            goto L_0x023a
        L_0x0238:
            r2 = r35
        L_0x023a:
            r4 = r34[r17]
            r13 = -1
            if (r4 != r13) goto L_0x0250
            r31 = 0
            r7 = r34[r31]
            if (r7 != r13) goto L_0x0250
            r7 = r34[r16]
            if (r7 != r13) goto L_0x0250
            r7 = r34[r21]
            if (r7 == r13) goto L_0x024e
            goto L_0x0250
        L_0x024e:
            r6 = r12
            goto L_0x027d
        L_0x0250:
            r7 = r34[r21]
            r31 = 0
            r13 = r34[r31]
            r6 = r34[r16]
            int r4 = java.lang.Math.max(r4, r6)
            int r4 = java.lang.Math.max(r13, r4)
            int r4 = java.lang.Math.max(r7, r4)
            r6 = r28[r21]
            r7 = r28[r31]
            r13 = r28[r17]
            r9 = r28[r16]
            int r9 = java.lang.Math.max(r13, r9)
            int r7 = java.lang.Math.max(r7, r9)
            int r6 = java.lang.Math.max(r6, r7)
            int r4 = r4 + r6
            int r6 = java.lang.Math.max(r12, r4)
        L_0x027d:
            if (r27 == 0) goto L_0x02ee
            r4 = r33
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 == r9) goto L_0x028b
            if (r4 != 0) goto L_0x0288
            goto L_0x028b
        L_0x0288:
            r32 = r5
            goto L_0x02f2
        L_0x028b:
            r7 = 0
            r0.mTotalLength = r7
            r7 = 0
        L_0x028f:
            if (r7 >= r2) goto L_0x0288
            android.view.View r9 = r0.getVirtualChildAt(r7)
            if (r9 != 0) goto L_0x02a1
            int r9 = r0.mTotalLength
            int r12 = r0.measureNullChild(r7)
            int r9 = r9 + r12
            r0.mTotalLength = r9
            goto L_0x02ae
        L_0x02a1:
            int r12 = r9.getVisibility()
            r13 = 8
            if (r12 != r13) goto L_0x02b3
            int r9 = r0.getChildrenSkipCount(r9, r7)
            int r7 = r7 + r9
        L_0x02ae:
            r32 = r5
        L_0x02b0:
            r33 = r6
            goto L_0x02e7
        L_0x02b3:
            android.view.ViewGroup$LayoutParams r12 = r9.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r12 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r12
            if (r18 == 0) goto L_0x02ce
            int r13 = r0.mTotalLength
            r32 = r5
            int r5 = r12.leftMargin
            int r5 = r5 + r15
            int r12 = r12.rightMargin
            int r5 = r5 + r12
            int r9 = r0.getNextLocationOffset(r9)
            int r5 = r5 + r9
            int r13 = r13 + r5
            r0.mTotalLength = r13
            goto L_0x02b0
        L_0x02ce:
            r32 = r5
            int r5 = r0.mTotalLength
            int r13 = r5 + r15
            r33 = r6
            int r6 = r12.leftMargin
            int r13 = r13 + r6
            int r6 = r12.rightMargin
            int r13 = r13 + r6
            int r6 = r0.getNextLocationOffset(r9)
            int r13 = r13 + r6
            int r5 = java.lang.Math.max(r5, r13)
            r0.mTotalLength = r5
        L_0x02e7:
            int r7 = r7 + 1
            r5 = r32
            r6 = r33
            goto L_0x028f
        L_0x02ee:
            r32 = r5
            r4 = r33
        L_0x02f2:
            r33 = r6
            int r5 = r0.mTotalLength
            int r6 = r0.getPaddingLeft()
            int r7 = r0.getPaddingRight()
            int r6 = r6 + r7
            int r5 = r5 + r6
            r0.mTotalLength = r5
            int r6 = r0.getSuggestedMinimumWidth()
            int r5 = java.lang.Math.max(r5, r6)
            r7 = 0
            int r5 = android.view.View.resolveSizeAndState(r5, r3, r7)
            r6 = 16777215(0xffffff, float:2.3509886E-38)
            r6 = r6 & r5
            int r7 = r0.mTotalLength
            int r6 = r6 - r7
            if (r22 != 0) goto L_0x0363
            if (r6 == 0) goto L_0x031f
            int r9 = (r32 > r19 ? 1 : (r32 == r19 ? 0 : -1))
            if (r9 <= 0) goto L_0x031f
            goto L_0x0363
        L_0x031f:
            int r6 = java.lang.Math.max(r11, r14)
            if (r27 == 0) goto L_0x035b
            r9 = 1073741824(0x40000000, float:2.0)
            if (r4 == r9) goto L_0x035b
            r4 = 0
        L_0x032a:
            if (r4 >= r2) goto L_0x035b
            android.view.View r9 = r0.getVirtualChildAt(r4)
            if (r9 == 0) goto L_0x0358
            int r11 = r9.getVisibility()
            r13 = 8
            if (r11 != r13) goto L_0x033b
            goto L_0x0358
        L_0x033b:
            android.view.ViewGroup$LayoutParams r11 = r9.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r11 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r11
            float r11 = r11.weight
            int r11 = (r11 > r19 ? 1 : (r11 == r19 ? 0 : -1))
            if (r11 <= 0) goto L_0x0358
            r11 = 1073741824(0x40000000, float:2.0)
            int r12 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r11)
            int r13 = r9.getMeasuredHeight()
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r13, r11)
            r9.measure(r12, r13)
        L_0x0358:
            int r4 = r4 + 1
            goto L_0x032a
        L_0x035b:
            r27 = r5
            r24 = r8
            r22 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            goto L_0x04e8
        L_0x0363:
            float r9 = r0.mWeightSum
            int r12 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r12 <= 0) goto L_0x036b
            r32 = r9
        L_0x036b:
            r23 = -1
            r34[r21] = r23
            r34[r16] = r23
            r34[r17] = r23
            r9 = 0
            r34[r9] = r23
            r28[r21] = r23
            r28[r16] = r23
            r28[r17] = r23
            r28[r9] = r23
            r0.mTotalLength = r9
            r9 = r8
            r8 = -1
            r12 = 0
        L_0x0383:
            if (r12 >= r2) goto L_0x048d
            android.view.View r13 = r0.getVirtualChildAt(r12)
            if (r13 == 0) goto L_0x0483
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x0395
            goto L_0x0483
        L_0x0395:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r14 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r14
            r22 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            float r7 = r14.weight
            int r24 = (r7 > r19 ? 1 : (r7 == r19 ? 0 : -1))
            if (r24 <= 0) goto L_0x03f5
            float r15 = (float) r6
            float r15 = r15 * r7
            float r15 = r15 / r32
            int r15 = (int) r15
            float r32 = r32 - r7
            int r6 = r6 - r15
            int r7 = r0.getPaddingTop()
            int r27 = r0.getPaddingBottom()
            int r7 = r7 + r27
            r27 = r5
            int r5 = r14.topMargin
            int r7 = r7 + r5
            int r5 = r14.bottomMargin
            int r7 = r7 + r5
            int r5 = r14.height
            int r5 = getChildMeasureSpec(r1, r7, r5)
            int r7 = r14.width
            if (r7 != 0) goto L_0x03d8
            r7 = 1073741824(0x40000000, float:2.0)
            if (r4 == r7) goto L_0x03cc
            goto L_0x03da
        L_0x03cc:
            if (r15 <= 0) goto L_0x03cf
            goto L_0x03d0
        L_0x03cf:
            r15 = 0
        L_0x03d0:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r7)
            r13.measure(r15, r5)
            goto L_0x03ea
        L_0x03d8:
            r7 = 1073741824(0x40000000, float:2.0)
        L_0x03da:
            int r33 = r13.getMeasuredWidth()
            int r15 = r33 + r15
            if (r15 >= 0) goto L_0x03e3
            r15 = 0
        L_0x03e3:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r7)
            r13.measure(r15, r5)
        L_0x03ea:
            int r5 = r13.getMeasuredState()
            r5 = r5 & r22
            int r9 = android.view.View.combineMeasuredStates(r9, r5)
            goto L_0x03f7
        L_0x03f5:
            r27 = r5
        L_0x03f7:
            if (r18 == 0) goto L_0x040e
            int r5 = r0.mTotalLength
            int r7 = r13.getMeasuredWidth()
            int r15 = r14.leftMargin
            int r7 = r7 + r15
            int r15 = r14.rightMargin
            int r7 = r7 + r15
            int r15 = r0.getNextLocationOffset(r13)
            int r7 = r7 + r15
            int r5 = r5 + r7
            r0.mTotalLength = r5
            goto L_0x0426
        L_0x040e:
            int r5 = r0.mTotalLength
            int r7 = r13.getMeasuredWidth()
            int r7 = r7 + r5
            int r15 = r14.leftMargin
            int r7 = r7 + r15
            int r15 = r14.rightMargin
            int r7 = r7 + r15
            int r15 = r0.getNextLocationOffset(r13)
            int r7 = r7 + r15
            int r5 = java.lang.Math.max(r5, r7)
            r0.mTotalLength = r5
        L_0x0426:
            r7 = 1073741824(0x40000000, float:2.0)
            if (r10 == r7) goto L_0x0432
            int r5 = r14.height
            r7 = -1
            if (r5 != r7) goto L_0x0432
            r5 = r17
            goto L_0x0433
        L_0x0432:
            r5 = 0
        L_0x0433:
            int r7 = r14.topMargin
            int r15 = r14.bottomMargin
            int r7 = r7 + r15
            int r15 = r13.getMeasuredHeight()
            int r15 = r15 + r7
            int r8 = java.lang.Math.max(r8, r15)
            if (r5 == 0) goto L_0x0444
            goto L_0x0445
        L_0x0444:
            r7 = r15
        L_0x0445:
            int r5 = java.lang.Math.max(r11, r7)
            if (r25 == 0) goto L_0x0453
            int r7 = r14.height
            r11 = -1
            if (r7 != r11) goto L_0x0454
            r7 = r17
            goto L_0x0455
        L_0x0453:
            r11 = -1
        L_0x0454:
            r7 = 0
        L_0x0455:
            if (r30 == 0) goto L_0x047f
            int r13 = r13.getBaseline()
            if (r13 == r11) goto L_0x047f
            int r11 = r14.gravity
            if (r11 >= 0) goto L_0x0464
            int r11 = r0.mGravity
            goto L_0x0466
        L_0x0464:
            int r11 = r14.gravity
        L_0x0466:
            r11 = r11 & 112(0x70, float:1.57E-43)
            int r11 = r11 >> 4
            r11 = r11 & -2
            int r11 = r11 >> 1
            r14 = r34[r11]
            int r14 = java.lang.Math.max(r14, r13)
            r34[r11] = r14
            r14 = r28[r11]
            int r15 = r15 - r13
            int r13 = java.lang.Math.max(r14, r15)
            r28[r11] = r13
        L_0x047f:
            r11 = r5
            r25 = r7
            goto L_0x0487
        L_0x0483:
            r27 = r5
            r22 = -16777216(0xffffffffff000000, float:-1.7014118E38)
        L_0x0487:
            int r12 = r12 + 1
            r5 = r27
            goto L_0x0383
        L_0x048d:
            r27 = r5
            r22 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            int r4 = r0.mTotalLength
            int r5 = r0.getPaddingLeft()
            int r6 = r0.getPaddingRight()
            int r5 = r5 + r6
            int r4 = r4 + r5
            r0.mTotalLength = r4
            r4 = r34[r17]
            r13 = -1
            if (r4 != r13) goto L_0x04b5
            r31 = 0
            r5 = r34[r31]
            if (r5 != r13) goto L_0x04b5
            r5 = r34[r16]
            if (r5 != r13) goto L_0x04b5
            r5 = r34[r21]
            if (r5 == r13) goto L_0x04b3
            goto L_0x04b5
        L_0x04b3:
            r6 = r8
            goto L_0x04e3
        L_0x04b5:
            r5 = r34[r21]
            r31 = 0
            r6 = r34[r31]
            r7 = r34[r16]
            int r4 = java.lang.Math.max(r4, r7)
            int r4 = java.lang.Math.max(r6, r4)
            int r4 = java.lang.Math.max(r5, r4)
            r5 = r28[r21]
            r6 = r28[r31]
            r7 = r28[r17]
            r12 = r28[r16]
            int r7 = java.lang.Math.max(r7, r12)
            int r6 = java.lang.Math.max(r6, r7)
            int r5 = java.lang.Math.max(r5, r6)
            int r4 = r4 + r5
            int r4 = java.lang.Math.max(r8, r4)
            r6 = r4
        L_0x04e3:
            r33 = r6
            r24 = r9
            r6 = r11
        L_0x04e8:
            if (r25 != 0) goto L_0x04ef
            r7 = 1073741824(0x40000000, float:2.0)
            if (r10 == r7) goto L_0x04ef
            goto L_0x04f1
        L_0x04ef:
            r6 = r33
        L_0x04f1:
            int r4 = r0.getPaddingTop()
            int r5 = r0.getPaddingBottom()
            int r4 = r4 + r5
            int r6 = r6 + r4
            int r4 = r0.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r6, r4)
            r5 = r24 & r22
            r5 = r27 | r5
            int r6 = r24 << 16
            int r1 = android.view.View.resolveSizeAndState(r4, r1, r6)
            r0.setMeasuredDimension(r5, r1)
            if (r26 == 0) goto L_0x0515
            r0.forceUniformHeight(r2, r3)
        L_0x0515:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int i3;
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), BasicMeasure.EXACTLY);
        int i4 = 0;
        while (i4 < i) {
            View virtualChildAt = getVirtualChildAt(i4);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i5 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    i3 = i2;
                    measureChildWithMargins(virtualChildAt, i3, 0, makeMeasureSpec, 0);
                    layoutParams.width = i5;
                    i4++;
                    i2 = i3;
                }
            }
            i3 = i2;
            i4++;
            i2 = i3;
        }
    }

    /* access modifiers changed from: package-private */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutVertical(int r12, int r13, int r14, int r15) {
        /*
            r11 = this;
            int r0 = r11.getPaddingLeft()
            int r14 = r14 - r12
            int r12 = r11.getPaddingRight()
            int r12 = r14 - r12
            int r14 = r14 - r0
            int r1 = r11.getPaddingRight()
            int r14 = r14 - r1
            int r1 = r11.getVirtualChildCount()
            int r2 = r11.mGravity
            r3 = r2 & 112(0x70, float:1.57E-43)
            r4 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r4
            r4 = 16
            if (r3 == r4) goto L_0x0035
            r4 = 80
            if (r3 == r4) goto L_0x002a
            int r13 = r11.getPaddingTop()
            goto L_0x0041
        L_0x002a:
            int r3 = r11.getPaddingTop()
            int r3 = r3 + r15
            int r3 = r3 - r13
            int r13 = r11.mTotalLength
            int r13 = r3 - r13
            goto L_0x0041
        L_0x0035:
            int r3 = r11.getPaddingTop()
            int r15 = r15 - r13
            int r13 = r11.mTotalLength
            int r15 = r15 - r13
            int r15 = r15 / 2
            int r13 = r3 + r15
        L_0x0041:
            r15 = 0
        L_0x0042:
            if (r15 >= r1) goto L_0x00b9
            android.view.View r4 = r11.getVirtualChildAt(r15)
            r9 = 1
            if (r4 != 0) goto L_0x0051
            int r3 = r11.measureNullChild(r15)
            int r13 = r13 + r3
            goto L_0x00b6
        L_0x0051:
            int r3 = r4.getVisibility()
            r5 = 8
            if (r3 == r5) goto L_0x00b6
            int r7 = r4.getMeasuredWidth()
            int r8 = r4.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r3 = r4.getLayoutParams()
            r10 = r3
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r10 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r10
            int r3 = r10.gravity
            if (r3 >= 0) goto L_0x006d
            r3 = r2
        L_0x006d:
            int r5 = androidx.core.view.ViewCompat.getLayoutDirection(r11)
            int r3 = androidx.core.view.GravityCompat.getAbsoluteGravity(r3, r5)
            r3 = r3 & 7
            if (r3 == r9) goto L_0x0085
            r5 = 5
            if (r3 == r5) goto L_0x0080
            int r3 = r10.leftMargin
            int r3 = r3 + r0
            goto L_0x0090
        L_0x0080:
            int r3 = r12 - r7
            int r5 = r10.rightMargin
            goto L_0x008f
        L_0x0085:
            int r3 = r14 - r7
            int r3 = r3 / 2
            int r3 = r3 + r0
            int r5 = r10.leftMargin
            int r3 = r3 + r5
            int r5 = r10.rightMargin
        L_0x008f:
            int r3 = r3 - r5
        L_0x0090:
            r5 = r3
            boolean r3 = r11.hasDividerBeforeChildAt(r15)
            if (r3 == 0) goto L_0x009a
            int r3 = r11.mDividerHeight
            int r13 = r13 + r3
        L_0x009a:
            int r3 = r10.topMargin
            int r13 = r13 + r3
            int r3 = r11.getLocationOffset(r4)
            int r6 = r13 + r3
            r3 = r11
            r3.setChildFrame(r4, r5, r6, r7, r8)
            int r5 = r10.bottomMargin
            int r8 = r8 + r5
            int r5 = r11.getNextLocationOffset(r4)
            int r8 = r8 + r5
            int r13 = r13 + r8
            int r4 = r11.getChildrenSkipCount(r4, r15)
            int r15 = r15 + r4
            goto L_0x00b7
        L_0x00b6:
            r3 = r11
        L_0x00b7:
            int r15 = r15 + r9
            goto L_0x0042
        L_0x00b9:
            r3 = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.layoutVertical(int, int, int, int):void");
    }

    /* JADX WARNING: type inference failed for: r25v2 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ff  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0104  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutHorizontal(int r22, int r23, int r24, int r25) {
        /*
            r21 = this;
            r0 = r21
            boolean r1 = androidx.appcompat.widget.ViewUtils.isLayoutRtl(r0)
            int r6 = r0.getPaddingTop()
            int r2 = r25 - r23
            int r3 = r0.getPaddingBottom()
            int r7 = r2 - r3
            int r2 = r2 - r6
            int r3 = r0.getPaddingBottom()
            int r8 = r2 - r3
            int r9 = r0.getVirtualChildCount()
            int r2 = r0.mGravity
            r3 = 8388615(0x800007, float:1.1754953E-38)
            r3 = r3 & r2
            r10 = r2 & 112(0x70, float:1.57E-43)
            boolean r11 = r0.mBaselineAligned
            int[] r12 = r0.mMaxAscent
            int[] r13 = r0.mMaxDescent
            int r2 = androidx.core.view.ViewCompat.getLayoutDirection(r0)
            int r2 = androidx.core.view.GravityCompat.getAbsoluteGravity(r3, r2)
            r14 = 2
            r15 = 1
            if (r2 == r15) goto L_0x004b
            r3 = 5
            if (r2 == r3) goto L_0x003f
            int r2 = r0.getPaddingLeft()
            goto L_0x0056
        L_0x003f:
            int r2 = r0.getPaddingLeft()
            int r2 = r2 + r24
            int r2 = r2 - r22
            int r3 = r0.mTotalLength
            int r2 = r2 - r3
            goto L_0x0056
        L_0x004b:
            int r2 = r0.getPaddingLeft()
            int r3 = r24 - r22
            int r4 = r0.mTotalLength
            int r3 = r3 - r4
            int r3 = r3 / r14
            int r2 = r2 + r3
        L_0x0056:
            r3 = 0
            if (r1 == 0) goto L_0x0060
            int r1 = r9 + -1
            r16 = r1
            r17 = -1
            goto L_0x0064
        L_0x0060:
            r16 = r3
            r17 = r15
        L_0x0064:
            r1 = r3
        L_0x0065:
            if (r1 >= r9) goto L_0x013f
            int r3 = r17 * r1
            int r3 = r16 + r3
            r5 = r1
            android.view.View r1 = r0.getVirtualChildAt(r3)
            if (r1 != 0) goto L_0x0082
            int r1 = r0.measureNullChild(r3)
            int r2 = r2 + r1
            r1 = r5
            r19 = r6
            r23 = r14
            r25 = r15
            r18 = -1
            goto L_0x0135
        L_0x0082:
            r23 = r14
            int r14 = r1.getVisibility()
            r25 = r15
            r15 = 8
            if (r14 == r15) goto L_0x012d
            int r14 = r1.getMeasuredWidth()
            r15 = r5
            int r5 = r1.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r18 = r1.getLayoutParams()
            r4 = r18
            androidx.appcompat.widget.LinearLayoutCompat$LayoutParams r4 = (androidx.appcompat.widget.LinearLayoutCompat.LayoutParams) r4
            r24 = r2
            if (r11 == 0) goto L_0x00af
            int r2 = r4.height
            r18 = r5
            r5 = -1
            if (r2 == r5) goto L_0x00b1
            int r5 = r1.getBaseline()
            goto L_0x00b2
        L_0x00af:
            r18 = r5
        L_0x00b1:
            r5 = -1
        L_0x00b2:
            int r2 = r4.gravity
            if (r2 >= 0) goto L_0x00b7
            r2 = r10
        L_0x00b7:
            r2 = r2 & 112(0x70, float:1.57E-43)
            r19 = r6
            r6 = 16
            if (r2 == r6) goto L_0x00ec
            r6 = 48
            if (r2 == r6) goto L_0x00de
            r6 = 80
            if (r2 == r6) goto L_0x00cb
            r2 = r19
            r6 = -1
            goto L_0x00f9
        L_0x00cb:
            int r2 = r7 - r18
            int r6 = r4.bottomMargin
            int r2 = r2 - r6
            r6 = -1
            if (r5 == r6) goto L_0x00f9
            int r20 = r1.getMeasuredHeight()
            int r20 = r20 - r5
            r5 = r13[r23]
            int r5 = r5 - r20
            goto L_0x00f8
        L_0x00de:
            r6 = -1
            int r2 = r4.topMargin
            int r2 = r19 + r2
            if (r5 == r6) goto L_0x00f9
            r20 = r12[r25]
            int r20 = r20 - r5
            int r2 = r2 + r20
            goto L_0x00f9
        L_0x00ec:
            r6 = -1
            int r2 = r8 - r18
            int r2 = r2 / 2
            int r2 = r19 + r2
            int r5 = r4.topMargin
            int r2 = r2 + r5
            int r5 = r4.bottomMargin
        L_0x00f8:
            int r2 = r2 - r5
        L_0x00f9:
            boolean r5 = r0.hasDividerBeforeChildAt(r3)
            if (r5 == 0) goto L_0x0104
            int r5 = r0.mDividerWidth
            int r5 = r24 + r5
            goto L_0x0106
        L_0x0104:
            r5 = r24
        L_0x0106:
            int r6 = r4.leftMargin
            int r6 = r6 + r5
            int r5 = r0.getLocationOffset(r1)
            int r5 = r5 + r6
            r22 = r6
            r6 = r4
            r4 = r14
            r14 = r3
            r3 = r2
            r2 = r5
            r5 = r18
            r18 = -1
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r2 = r6.rightMargin
            int r2 = r2 + r4
            int r3 = r0.getNextLocationOffset(r1)
            int r2 = r2 + r3
            int r6 = r22 + r2
            int r1 = r0.getChildrenSkipCount(r1, r14)
            int r1 = r1 + r15
            r2 = r6
            goto L_0x0135
        L_0x012d:
            r24 = r2
            r15 = r5
            r19 = r6
            r18 = -1
            r1 = r15
        L_0x0135:
            int r1 = r1 + 1
            r14 = r23
            r15 = r25
            r6 = r19
            goto L_0x0065
        L_0x013f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(ACCESSIBILITY_CLASS_NAME);
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2, f);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }
}
