package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

class TouchResponse {
    public static final int COMPLETE_MODE_CONTINUOUS_VELOCITY = 0;
    public static final int COMPLETE_MODE_SPRING = 1;
    private static final boolean DEBUG = false;
    private static final float EPSILON = 1.0E-7f;
    static final int FLAG_DISABLE_POST_SCROLL = 1;
    static final int FLAG_DISABLE_SCROLL = 2;
    static final int FLAG_SUPPORT_SCROLL_UP = 4;
    private static final int SEC_TO_MILLISECONDS = 1000;
    private static final int SIDE_BOTTOM = 3;
    private static final int SIDE_END = 6;
    private static final int SIDE_LEFT = 1;
    private static final int SIDE_MIDDLE = 4;
    private static final int SIDE_RIGHT = 2;
    private static final int SIDE_START = 5;
    private static final int SIDE_TOP = 0;
    private static final String TAG = "TouchResponse";
    private static final float[][] TOUCH_DIRECTION = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};
    private static final int TOUCH_DOWN = 1;
    private static final int TOUCH_END = 5;
    private static final int TOUCH_LEFT = 2;
    private static final int TOUCH_RIGHT = 3;
    private static final float[][] TOUCH_SIDES = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final int TOUCH_START = 4;
    private static final int TOUCH_UP = 0;
    private float[] mAnchorDpDt = new float[2];
    private int mAutoCompleteMode = 0;
    private float mDragScale = 1.0f;
    private boolean mDragStarted = false;
    private float mDragThreshold = 10.0f;
    private int mFlags = 0;
    boolean mIsRotateMode = false;
    private float mLastTouchX;
    private float mLastTouchY;
    private int mLimitBoundsTo = -1;
    private float mMaxAcceleration = 1.2f;
    private float mMaxVelocity = 4.0f;
    private final MotionLayout mMotionLayout;
    private boolean mMoveWhenScrollAtTop = true;
    private int mOnTouchUp = 0;
    float mRotateCenterX = 0.5f;
    float mRotateCenterY = 0.5f;
    private int mRotationCenterId = -1;
    private int mSpringBoundary = 0;
    private float mSpringDamping = 10.0f;
    private float mSpringMass = 1.0f;
    private float mSpringStiffness = Float.NaN;
    private float mSpringStopThreshold = Float.NaN;
    private int[] mTempLoc = new int[2];
    private int mTouchAnchorId = -1;
    private int mTouchAnchorSide = 0;
    private float mTouchAnchorX = 0.5f;
    private float mTouchAnchorY = 0.5f;
    private float mTouchDirectionX = 0.0f;
    private float mTouchDirectionY = 1.0f;
    private int mTouchRegionId = -1;
    private int mTouchSide = 0;

    TouchResponse(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.mMotionLayout = motionLayout;
        fillFromAttributeList(context, Xml.asAttributeSet(xmlPullParser));
    }

    public TouchResponse(MotionLayout motionLayout, OnSwipe onSwipe) {
        this.mMotionLayout = motionLayout;
        this.mTouchAnchorId = onSwipe.getTouchAnchorId();
        int touchAnchorSide = onSwipe.getTouchAnchorSide();
        this.mTouchAnchorSide = touchAnchorSide;
        if (touchAnchorSide != -1) {
            float[] fArr = TOUCH_SIDES[touchAnchorSide];
            this.mTouchAnchorX = fArr[0];
            this.mTouchAnchorY = fArr[1];
        }
        int dragDirection = onSwipe.getDragDirection();
        this.mTouchSide = dragDirection;
        float[][] fArr2 = TOUCH_DIRECTION;
        if (dragDirection < fArr2.length) {
            float[] fArr3 = fArr2[dragDirection];
            this.mTouchDirectionX = fArr3[0];
            this.mTouchDirectionY = fArr3[1];
        } else {
            this.mTouchDirectionY = Float.NaN;
            this.mTouchDirectionX = Float.NaN;
            this.mIsRotateMode = true;
        }
        this.mMaxVelocity = onSwipe.getMaxVelocity();
        this.mMaxAcceleration = onSwipe.getMaxAcceleration();
        this.mMoveWhenScrollAtTop = onSwipe.getMoveWhenScrollAtTop();
        this.mDragScale = onSwipe.getDragScale();
        this.mDragThreshold = onSwipe.getDragThreshold();
        this.mTouchRegionId = onSwipe.getTouchRegionId();
        this.mOnTouchUp = onSwipe.getOnTouchUp();
        this.mFlags = onSwipe.getNestedScrollFlags();
        this.mLimitBoundsTo = onSwipe.getLimitBoundsTo();
        this.mRotationCenterId = onSwipe.getRotationCenterId();
        this.mSpringBoundary = onSwipe.getSpringBoundary();
        this.mSpringDamping = onSwipe.getSpringDamping();
        this.mSpringMass = onSwipe.getSpringMass();
        this.mSpringStiffness = onSwipe.getSpringStiffness();
        this.mSpringStopThreshold = onSwipe.getSpringStopThreshold();
        this.mAutoCompleteMode = onSwipe.getAutoCompleteMode();
    }

    public void setRTL(boolean z) {
        if (z) {
            float[][] fArr = TOUCH_DIRECTION;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = TOUCH_SIDES;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = TOUCH_DIRECTION;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = TOUCH_SIDES;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[] fArr5 = TOUCH_SIDES[this.mTouchAnchorSide];
        this.mTouchAnchorX = fArr5[0];
        this.mTouchAnchorY = fArr5[1];
        int i = this.mTouchSide;
        float[][] fArr6 = TOUCH_DIRECTION;
        if (i < fArr6.length) {
            float[] fArr7 = fArr6[i];
            this.mTouchDirectionX = fArr7[0];
            this.mTouchDirectionY = fArr7[1];
        }
    }

    private void fillFromAttributeList(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        fill(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    private void fill(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArray.getIndex(i);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.mTouchAnchorId = typedArray.getResourceId(index, this.mTouchAnchorId);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i2 = typedArray.getInt(index, this.mTouchAnchorSide);
                this.mTouchAnchorSide = i2;
                float[] fArr = TOUCH_SIDES[i2];
                this.mTouchAnchorX = fArr[0];
                this.mTouchAnchorY = fArr[1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i3 = typedArray.getInt(index, this.mTouchSide);
                this.mTouchSide = i3;
                float[][] fArr2 = TOUCH_DIRECTION;
                if (i3 < fArr2.length) {
                    float[] fArr3 = fArr2[i3];
                    this.mTouchDirectionX = fArr3[0];
                    this.mTouchDirectionY = fArr3[1];
                } else {
                    this.mTouchDirectionY = Float.NaN;
                    this.mTouchDirectionX = Float.NaN;
                    this.mIsRotateMode = true;
                }
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.mMaxVelocity = typedArray.getFloat(index, this.mMaxVelocity);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.mMaxAcceleration = typedArray.getFloat(index, this.mMaxAcceleration);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.mMoveWhenScrollAtTop = typedArray.getBoolean(index, this.mMoveWhenScrollAtTop);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.mDragScale = typedArray.getFloat(index, this.mDragScale);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.mDragThreshold = typedArray.getFloat(index, this.mDragThreshold);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.mTouchRegionId = typedArray.getResourceId(index, this.mTouchRegionId);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.mOnTouchUp = typedArray.getInt(index, this.mOnTouchUp);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.mFlags = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.mLimitBoundsTo = typedArray.getResourceId(index, 0);
            } else if (index == R.styleable.OnSwipe_rotationCenterId) {
                this.mRotationCenterId = typedArray.getResourceId(index, this.mRotationCenterId);
            } else if (index == R.styleable.OnSwipe_springDamping) {
                this.mSpringDamping = typedArray.getFloat(index, this.mSpringDamping);
            } else if (index == R.styleable.OnSwipe_springMass) {
                this.mSpringMass = typedArray.getFloat(index, this.mSpringMass);
            } else if (index == R.styleable.OnSwipe_springStiffness) {
                this.mSpringStiffness = typedArray.getFloat(index, this.mSpringStiffness);
            } else if (index == R.styleable.OnSwipe_springStopThreshold) {
                this.mSpringStopThreshold = typedArray.getFloat(index, this.mSpringStopThreshold);
            } else if (index == R.styleable.OnSwipe_springBoundary) {
                this.mSpringBoundary = typedArray.getInt(index, this.mSpringBoundary);
            } else if (index == R.styleable.OnSwipe_autoCompleteMode) {
                this.mAutoCompleteMode = typedArray.getInt(index, this.mAutoCompleteMode);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setUpTouchEvent(float f, float f2) {
        this.mLastTouchX = f;
        this.mLastTouchY = f2;
        this.mDragStarted = false;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x02ae  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0314  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processTouchRotateEvent(android.view.MotionEvent r24, androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker r25, int r26, androidx.constraintlayout.motion.widget.MotionScene r27) {
        /*
            r23 = this;
            r0 = r23
            r1 = r25
            r2 = r24
            r1.addMovement(r2)
            int r3 = r2.getAction()
            r4 = 0
            if (r3 == 0) goto L_0x0327
            r6 = -1
            r7 = 1065353216(0x3f800000, float:1.0)
            r9 = 1073741824(0x40000000, float:2.0)
            r10 = 1
            if (r3 == r10) goto L_0x01c2
            r11 = 2
            if (r3 == r11) goto L_0x001d
            goto L_0x031e
        L_0x001d:
            r2.getRawY()
            r2.getRawX()
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            int r3 = r3.getWidth()
            float r3 = (float) r3
            float r3 = r3 / r9
            androidx.constraintlayout.motion.widget.MotionLayout r11 = r0.mMotionLayout
            int r11 = r11.getHeight()
            float r11 = (float) r11
            float r11 = r11 / r9
            int r12 = r0.mRotationCenterId
            if (r12 == r6) goto L_0x006c
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            android.view.View r3 = r3.findViewById(r12)
            androidx.constraintlayout.motion.widget.MotionLayout r11 = r0.mMotionLayout
            int[] r12 = r0.mTempLoc
            r11.getLocationOnScreen(r12)
            int[] r11 = r0.mTempLoc
            r11 = r11[r4]
            float r11 = (float) r11
            int r12 = r3.getLeft()
            int r13 = r3.getRight()
            int r12 = r12 + r13
            float r12 = (float) r12
            float r12 = r12 / r9
            float r11 = r11 + r12
            int[] r12 = r0.mTempLoc
            r12 = r12[r10]
            float r12 = (float) r12
            int r13 = r3.getTop()
            int r3 = r3.getBottom()
            int r13 = r13 + r3
            float r3 = (float) r13
            float r3 = r3 / r9
            float r3 = r3 + r12
            r22 = r11
            r11 = r3
            r3 = r22
            goto L_0x00b3
        L_0x006c:
            int r12 = r0.mTouchAnchorId
            if (r12 == r6) goto L_0x00b3
            androidx.constraintlayout.motion.widget.MotionLayout r13 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionController r12 = r13.getMotionController(r12)
            androidx.constraintlayout.motion.widget.MotionLayout r13 = r0.mMotionLayout
            int r12 = r12.getAnimateRelativeTo()
            android.view.View r12 = r13.findViewById(r12)
            if (r12 != 0) goto L_0x008a
            java.lang.String r9 = "TouchResponse"
            java.lang.String r12 = "could not find view to animate to"
            android.util.Log.e(r9, r12)
            goto L_0x00b3
        L_0x008a:
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            int[] r11 = r0.mTempLoc
            r3.getLocationOnScreen(r11)
            int[] r3 = r0.mTempLoc
            r3 = r3[r4]
            float r3 = (float) r3
            int r11 = r12.getLeft()
            int r13 = r12.getRight()
            int r11 = r11 + r13
            float r11 = (float) r11
            float r11 = r11 / r9
            float r3 = r3 + r11
            int[] r11 = r0.mTempLoc
            r11 = r11[r10]
            float r11 = (float) r11
            int r13 = r12.getTop()
            int r12 = r12.getBottom()
            int r13 = r13 + r12
            float r12 = (float) r13
            float r12 = r12 / r9
            float r11 = r11 + r12
        L_0x00b3:
            float r9 = r2.getRawX()
            float r9 = r9 - r3
            float r12 = r2.getRawY()
            float r12 = r12 - r11
            float r13 = r2.getRawY()
            float r13 = r13 - r11
            double r13 = (double) r13
            float r15 = r2.getRawX()
            float r15 = r15 - r3
            r27 = 1135869952(0x43b40000, float:360.0)
            double r4 = (double) r15
            double r4 = java.lang.Math.atan2(r13, r4)
            float r13 = r0.mLastTouchY
            float r13 = r13 - r11
            double r13 = (double) r13
            float r11 = r0.mLastTouchX
            float r11 = r11 - r3
            r15 = r9
            double r8 = (double) r11
            double r8 = java.lang.Math.atan2(r13, r8)
            double r8 = r4 - r8
            r13 = 4640537203540230144(0x4066800000000000, double:180.0)
            double r8 = r8 * r13
            r13 = 4614256656552045848(0x400921fb54442d18, double:3.141592653589793)
            double r8 = r8 / r13
            float r8 = (float) r8
            r9 = 1134886912(0x43a50000, float:330.0)
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 <= 0) goto L_0x00f4
            float r8 = r8 - r27
            goto L_0x00fc
        L_0x00f4:
            r9 = -1012596736(0xffffffffc3a50000, float:-330.0)
            int r9 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fc
            float r8 = r8 + r27
        L_0x00fc:
            float r9 = java.lang.Math.abs(r8)
            double r13 = (double) r9
            r16 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r9 = (r13 > r16 ? 1 : (r13 == r16 ? 0 : -1))
            if (r9 > 0) goto L_0x010e
            boolean r9 = r0.mDragStarted
            if (r9 == 0) goto L_0x031e
        L_0x010e:
            androidx.constraintlayout.motion.widget.MotionLayout r9 = r0.mMotionLayout
            float r9 = r9.getProgress()
            boolean r11 = r0.mDragStarted
            if (r11 != 0) goto L_0x011f
            r0.mDragStarted = r10
            androidx.constraintlayout.motion.widget.MotionLayout r11 = r0.mMotionLayout
            r11.setProgress(r9)
        L_0x011f:
            int r11 = r0.mTouchAnchorId
            if (r11 == r6) goto L_0x0147
            androidx.constraintlayout.motion.widget.MotionLayout r6 = r0.mMotionLayout
            float r13 = r0.mTouchAnchorX
            float r14 = r0.mTouchAnchorY
            float[] r3 = r0.mAnchorDpDt
            r21 = r3
            r16 = r6
            r18 = r9
            r17 = r11
            r19 = r13
            r20 = r14
            r16.getAnchorDpDt(r17, r18, r19, r20, r21)
            float[] r3 = r0.mAnchorDpDt
            r6 = r3[r10]
            double r13 = (double) r6
            double r13 = java.lang.Math.toDegrees(r13)
            float r6 = (float) r13
            r3[r10] = r6
            goto L_0x014d
        L_0x0147:
            r18 = r9
            float[] r3 = r0.mAnchorDpDt
            r3[r10] = r27
        L_0x014d:
            float r3 = r0.mDragScale
            float r8 = r8 * r3
            float[] r3 = r0.mAnchorDpDt
            r3 = r3[r10]
            float r8 = r8 / r3
            float r9 = r18 + r8
            float r3 = java.lang.Math.min(r9, r7)
            r6 = 0
            float r3 = java.lang.Math.max(r3, r6)
            androidx.constraintlayout.motion.widget.MotionLayout r8 = r0.mMotionLayout
            float r8 = r8.getProgress()
            int r9 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r9 == 0) goto L_0x01b0
            int r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0172
            int r7 = (r8 > r7 ? 1 : (r8 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x017b
        L_0x0172:
            androidx.constraintlayout.motion.widget.MotionLayout r7 = r0.mMotionLayout
            if (r6 != 0) goto L_0x0177
            goto L_0x0178
        L_0x0177:
            r10 = 0
        L_0x0178:
            r7.endTrigger(r10)
        L_0x017b:
            androidx.constraintlayout.motion.widget.MotionLayout r6 = r0.mMotionLayout
            r6.setProgress(r3)
            r3 = 1000(0x3e8, float:1.401E-42)
            r1.computeCurrentVelocity(r3)
            float r3 = r1.getXVelocity()
            float r1 = r1.getYVelocity()
            double r6 = (double) r1
            double r8 = (double) r3
            double r10 = java.lang.Math.hypot(r6, r8)
            double r6 = java.lang.Math.atan2(r6, r8)
            double r6 = r6 - r4
            double r3 = java.lang.Math.sin(r6)
            double r10 = r10 * r3
            double r3 = (double) r15
            double r5 = (double) r12
            double r3 = java.lang.Math.hypot(r3, r5)
            double r10 = r10 / r3
            float r1 = (float) r10
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            double r4 = (double) r1
            double r4 = java.lang.Math.toDegrees(r4)
            float r1 = (float) r4
            r3.mLastVelocity = r1
            goto L_0x01b5
        L_0x01b0:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            r3 = 0
            r1.mLastVelocity = r3
        L_0x01b5:
            float r1 = r2.getRawX()
            r0.mLastTouchX = r1
            float r1 = r2.getRawY()
            r0.mLastTouchY = r1
            return
        L_0x01c2:
            r27 = 1135869952(0x43b40000, float:360.0)
            r0.mDragStarted = r4
            r4 = 16
            r1.computeCurrentVelocity(r4)
            float r4 = r1.getXVelocity()
            float r1 = r1.getYVelocity()
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r0.mMotionLayout
            float r13 = r5.getProgress()
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r0.mMotionLayout
            int r5 = r5.getWidth()
            float r5 = (float) r5
            float r5 = r5 / r9
            androidx.constraintlayout.motion.widget.MotionLayout r8 = r0.mMotionLayout
            int r8 = r8.getHeight()
            float r8 = (float) r8
            float r8 = r8 / r9
            int r11 = r0.mRotationCenterId
            if (r11 == r6) goto L_0x0223
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r0.mMotionLayout
            android.view.View r5 = r5.findViewById(r11)
            androidx.constraintlayout.motion.widget.MotionLayout r8 = r0.mMotionLayout
            int[] r11 = r0.mTempLoc
            r8.getLocationOnScreen(r11)
            int[] r8 = r0.mTempLoc
            r11 = 0
            r8 = r8[r11]
            float r8 = (float) r8
            int r11 = r5.getLeft()
            int r12 = r5.getRight()
            int r11 = r11 + r12
            float r11 = (float) r11
            float r11 = r11 / r9
            float r8 = r8 + r11
            int[] r11 = r0.mTempLoc
            r11 = r11[r10]
            float r11 = (float) r11
            int r12 = r5.getTop()
            int r5 = r5.getBottom()
        L_0x0219:
            int r12 = r12 + r5
            float r5 = (float) r12
            float r5 = r5 / r9
            float r5 = r5 + r11
            r22 = r8
            r8 = r5
            r5 = r22
            goto L_0x025e
        L_0x0223:
            int r11 = r0.mTouchAnchorId
            if (r11 == r6) goto L_0x025e
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionController r5 = r5.getMotionController(r11)
            androidx.constraintlayout.motion.widget.MotionLayout r8 = r0.mMotionLayout
            int r5 = r5.getAnimateRelativeTo()
            android.view.View r5 = r8.findViewById(r5)
            androidx.constraintlayout.motion.widget.MotionLayout r8 = r0.mMotionLayout
            int[] r11 = r0.mTempLoc
            r8.getLocationOnScreen(r11)
            int[] r8 = r0.mTempLoc
            r11 = 0
            r8 = r8[r11]
            float r8 = (float) r8
            int r11 = r5.getLeft()
            int r12 = r5.getRight()
            int r11 = r11 + r12
            float r11 = (float) r11
            float r11 = r11 / r9
            float r8 = r8 + r11
            int[] r11 = r0.mTempLoc
            r11 = r11[r10]
            float r11 = (float) r11
            int r12 = r5.getTop()
            int r5 = r5.getBottom()
            goto L_0x0219
        L_0x025e:
            float r9 = r2.getRawX()
            float r9 = r9 - r5
            float r2 = r2.getRawY()
            float r2 = r2 - r8
            double r11 = (double) r2
            double r14 = (double) r9
            double r11 = java.lang.Math.atan2(r11, r14)
            double r17 = java.lang.Math.toDegrees(r11)
            int r12 = r0.mTouchAnchorId
            if (r12 == r6) goto L_0x0290
            androidx.constraintlayout.motion.widget.MotionLayout r11 = r0.mMotionLayout
            float r14 = r0.mTouchAnchorX
            float r15 = r0.mTouchAnchorY
            float[] r5 = r0.mAnchorDpDt
            r16 = r5
            r11.getAnchorDpDt(r12, r13, r14, r15, r16)
            float[] r5 = r0.mAnchorDpDt
            r6 = r5[r10]
            double r11 = (double) r6
            double r11 = java.lang.Math.toDegrees(r11)
            float r6 = (float) r11
            r5[r10] = r6
            goto L_0x0294
        L_0x0290:
            float[] r5 = r0.mAnchorDpDt
            r5[r10] = r27
        L_0x0294:
            float r1 = r1 + r2
            double r1 = (double) r1
            float r4 = r4 + r9
            double r4 = (double) r4
            double r1 = java.lang.Math.atan2(r1, r4)
            double r1 = java.lang.Math.toDegrees(r1)
            double r1 = r1 - r17
            float r1 = (float) r1
            r2 = 1115291648(0x427a0000, float:62.5)
            float r1 = r1 * r2
            boolean r2 = java.lang.Float.isNaN(r1)
            r4 = 1077936128(0x40400000, float:3.0)
            if (r2 != 0) goto L_0x02ba
            float r2 = r1 * r4
            float r5 = r0.mDragScale
            float r2 = r2 * r5
            float[] r5 = r0.mAnchorDpDt
            r5 = r5[r10]
            float r2 = r2 / r5
            float r2 = r2 + r13
            goto L_0x02bb
        L_0x02ba:
            r2 = r13
        L_0x02bb:
            r3 = 0
            int r5 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0314
            int r5 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r5 == 0) goto L_0x0314
            int r5 = r0.mOnTouchUp
            r6 = 3
            if (r5 == r6) goto L_0x0314
            float r6 = r0.mDragScale
            float r1 = r1 * r6
            float[] r6 = r0.mAnchorDpDt
            r6 = r6[r10]
            float r1 = r1 / r6
            double r8 = (double) r2
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x02da
            r2 = 0
            goto L_0x02db
        L_0x02da:
            r2 = r7
        L_0x02db:
            r6 = 6
            if (r5 != r6) goto L_0x02ea
            float r2 = r13 + r1
            r3 = 0
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x02e9
            float r1 = java.lang.Math.abs(r1)
        L_0x02e9:
            r2 = r7
        L_0x02ea:
            int r5 = r0.mOnTouchUp
            r6 = 7
            if (r5 != r6) goto L_0x02fb
            float r2 = r13 + r1
            int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r2 <= 0) goto L_0x02fa
            float r1 = java.lang.Math.abs(r1)
            float r1 = -r1
        L_0x02fa:
            r2 = 0
        L_0x02fb:
            androidx.constraintlayout.motion.widget.MotionLayout r5 = r0.mMotionLayout
            int r6 = r0.mOnTouchUp
            float r1 = r1 * r4
            r5.touchAnimateTo(r6, r2, r1)
            r3 = 0
            int r1 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r1 >= 0) goto L_0x030c
            int r1 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r1 > 0) goto L_0x031e
        L_0x030c:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionLayout$TransitionState r2 = androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED
            r1.setState(r2)
            return
        L_0x0314:
            r3 = 0
            int r1 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r1 >= 0) goto L_0x031f
            int r1 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x031e
            goto L_0x031f
        L_0x031e:
            return
        L_0x031f:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionLayout$TransitionState r2 = androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED
            r1.setState(r2)
            return
        L_0x0327:
            float r1 = r2.getRawX()
            r0.mLastTouchX = r1
            float r1 = r2.getRawY()
            r0.mLastTouchY = r1
            r11 = 0
            r0.mDragStarted = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.TouchResponse.processTouchRotateEvent(android.view.MotionEvent, androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker, int, androidx.constraintlayout.motion.widget.MotionScene):void");
    }

    /* JADX WARNING: type inference failed for: r24v1 */
    /* JADX WARNING: type inference failed for: r23v2 */
    /* JADX WARNING: type inference failed for: r23v3 */
    /* JADX WARNING: type inference failed for: r24v2 */
    /* JADX WARNING: type inference failed for: r23v4 */
    /* JADX WARNING: type inference failed for: r24v3 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processTouchEvent(android.view.MotionEvent r21, androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker r22, int r23, androidx.constraintlayout.motion.widget.MotionScene r24) {
        /*
            r20 = this;
            r0 = r20
            r1 = r22
            boolean r2 = r0.mIsRotateMode
            if (r2 == 0) goto L_0x000c
            r20.processTouchRotateEvent(r21, r22, r23, r24)
            return
        L_0x000c:
            r2 = r21
            r1.addMovement(r2)
            int r3 = r2.getAction()
            if (r3 == 0) goto L_0x020b
            r5 = 7
            r6 = 6
            r7 = -1
            r8 = 1000(0x3e8, float:1.401E-42)
            r9 = 1065353216(0x3f800000, float:1.0)
            r10 = 1
            r11 = 0
            if (r3 == r10) goto L_0x0146
            r12 = 2
            if (r3 == r12) goto L_0x0027
            goto L_0x0202
        L_0x0027:
            float r3 = r2.getRawY()
            float r12 = r0.mLastTouchY
            float r3 = r3 - r12
            float r12 = r2.getRawX()
            float r13 = r0.mLastTouchX
            float r12 = r12 - r13
            float r13 = r0.mTouchDirectionX
            float r13 = r13 * r12
            float r14 = r0.mTouchDirectionY
            float r14 = r14 * r3
            float r13 = r13 + r14
            float r13 = java.lang.Math.abs(r13)
            float r14 = r0.mDragThreshold
            int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
            if (r13 > 0) goto L_0x004a
            boolean r13 = r0.mDragStarted
            if (r13 == 0) goto L_0x0202
        L_0x004a:
            androidx.constraintlayout.motion.widget.MotionLayout r13 = r0.mMotionLayout
            float r13 = r13.getProgress()
            boolean r14 = r0.mDragStarted
            if (r14 != 0) goto L_0x005b
            r0.mDragStarted = r10
            androidx.constraintlayout.motion.widget.MotionLayout r14 = r0.mMotionLayout
            r14.setProgress(r13)
        L_0x005b:
            int r15 = r0.mTouchAnchorId
            if (r15 == r7) goto L_0x0077
            androidx.constraintlayout.motion.widget.MotionLayout r14 = r0.mMotionLayout
            float r7 = r0.mTouchAnchorX
            r23 = r10
            float r10 = r0.mTouchAnchorY
            r24 = 0
            float[] r4 = r0.mAnchorDpDt
            r19 = r4
            r17 = r7
            r18 = r10
            r16 = r13
            r14.getAnchorDpDt(r15, r16, r17, r18, r19)
            goto L_0x009a
        L_0x0077:
            r23 = r10
            r16 = r13
            r24 = 0
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            int r4 = r4.getWidth()
            androidx.constraintlayout.motion.widget.MotionLayout r7 = r0.mMotionLayout
            int r7 = r7.getHeight()
            int r4 = java.lang.Math.min(r4, r7)
            float r4 = (float) r4
            float[] r7 = r0.mAnchorDpDt
            float r10 = r0.mTouchDirectionY
            float r10 = r10 * r4
            r7[r23] = r10
            float r10 = r0.mTouchDirectionX
            float r4 = r4 * r10
            r7[r24] = r4
        L_0x009a:
            float r4 = r0.mTouchDirectionX
            float[] r7 = r0.mAnchorDpDt
            r10 = r7[r24]
            float r4 = r4 * r10
            float r10 = r0.mTouchDirectionY
            r7 = r7[r23]
            float r10 = r10 * r7
            float r4 = r4 + r10
            float r7 = r0.mDragScale
            float r4 = r4 * r7
            float r4 = java.lang.Math.abs(r4)
            double r13 = (double) r4
            r17 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            int r4 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            r7 = 1008981770(0x3c23d70a, float:0.01)
            if (r4 >= 0) goto L_0x00c1
            float[] r4 = r0.mAnchorDpDt
            r4[r24] = r7
            r4[r23] = r7
        L_0x00c1:
            float r4 = r0.mTouchDirectionX
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x00cd
            float[] r3 = r0.mAnchorDpDt
            r3 = r3[r24]
            float r12 = r12 / r3
            goto L_0x00d3
        L_0x00cd:
            float[] r4 = r0.mAnchorDpDt
            r4 = r4[r23]
            float r12 = r3 / r4
        L_0x00d3:
            float r13 = r16 + r12
            float r3 = java.lang.Math.min(r13, r9)
            float r3 = java.lang.Math.max(r3, r11)
            int r4 = r0.mOnTouchUp
            if (r4 != r6) goto L_0x00e5
            float r3 = java.lang.Math.max(r3, r7)
        L_0x00e5:
            int r4 = r0.mOnTouchUp
            if (r4 != r5) goto L_0x00f0
            r4 = 1065185444(0x3f7d70a4, float:0.99)
            float r3 = java.lang.Math.min(r3, r4)
        L_0x00f0:
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            float r4 = r4.getProgress()
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 == 0) goto L_0x0135
            int r5 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r5 == 0) goto L_0x0102
            int r4 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r4 != 0) goto L_0x010e
        L_0x0102:
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            if (r5 != 0) goto L_0x0109
            r5 = r23
            goto L_0x010b
        L_0x0109:
            r5 = r24
        L_0x010b:
            r4.endTrigger(r5)
        L_0x010e:
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            r4.setProgress(r3)
            r1.computeCurrentVelocity(r8)
            float r3 = r1.getXVelocity()
            float r1 = r1.getYVelocity()
            float r4 = r0.mTouchDirectionX
            int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x012a
            float[] r1 = r0.mAnchorDpDt
            r1 = r1[r24]
            float r3 = r3 / r1
            goto L_0x0130
        L_0x012a:
            float[] r3 = r0.mAnchorDpDt
            r3 = r3[r23]
            float r3 = r1 / r3
        L_0x0130:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            r1.mLastVelocity = r3
            goto L_0x0139
        L_0x0135:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            r1.mLastVelocity = r11
        L_0x0139:
            float r1 = r2.getRawX()
            r0.mLastTouchX = r1
            float r1 = r2.getRawY()
            r0.mLastTouchY = r1
            return
        L_0x0146:
            r23 = r10
            r2 = 0
            r0.mDragStarted = r2
            r1.computeCurrentVelocity(r8)
            float r2 = r1.getXVelocity()
            float r1 = r1.getYVelocity()
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            float r14 = r3.getProgress()
            int r13 = r0.mTouchAnchorId
            if (r13 == r7) goto L_0x0171
            androidx.constraintlayout.motion.widget.MotionLayout r12 = r0.mMotionLayout
            float r15 = r0.mTouchAnchorX
            float r3 = r0.mTouchAnchorY
            float[] r4 = r0.mAnchorDpDt
            r16 = r3
            r17 = r4
            r12.getAnchorDpDt(r13, r14, r15, r16, r17)
            r7 = 0
            goto L_0x018f
        L_0x0171:
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            int r3 = r3.getWidth()
            androidx.constraintlayout.motion.widget.MotionLayout r4 = r0.mMotionLayout
            int r4 = r4.getHeight()
            int r3 = java.lang.Math.min(r3, r4)
            float r3 = (float) r3
            float[] r4 = r0.mAnchorDpDt
            float r7 = r0.mTouchDirectionY
            float r7 = r7 * r3
            r4[r23] = r7
            float r7 = r0.mTouchDirectionX
            float r3 = r3 * r7
            r7 = 0
            r4[r7] = r3
        L_0x018f:
            float r3 = r0.mTouchDirectionX
            float[] r4 = r0.mAnchorDpDt
            r7 = r4[r7]
            r4 = r4[r23]
            int r3 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x019d
            float r2 = r2 / r7
            goto L_0x019f
        L_0x019d:
            float r2 = r1 / r4
        L_0x019f:
            boolean r1 = java.lang.Float.isNaN(r2)
            if (r1 != 0) goto L_0x01ab
            r1 = 1077936128(0x40400000, float:3.0)
            float r1 = r2 / r1
            float r1 = r1 + r14
            goto L_0x01ac
        L_0x01ab:
            r1 = r14
        L_0x01ac:
            int r3 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r3 == 0) goto L_0x01f9
            int r3 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r3 == 0) goto L_0x01f9
            int r3 = r0.mOnTouchUp
            r4 = 3
            if (r3 == r4) goto L_0x01f9
            double r7 = (double) r1
            r12 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r1 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r1 >= 0) goto L_0x01c2
            r1 = r11
            goto L_0x01c3
        L_0x01c2:
            r1 = r9
        L_0x01c3:
            if (r3 != r6) goto L_0x01d1
            float r1 = r14 + r2
            int r1 = (r1 > r11 ? 1 : (r1 == r11 ? 0 : -1))
            if (r1 >= 0) goto L_0x01d0
            float r1 = java.lang.Math.abs(r2)
            r2 = r1
        L_0x01d0:
            r1 = r9
        L_0x01d1:
            int r3 = r0.mOnTouchUp
            if (r3 != r5) goto L_0x01e2
            float r1 = r14 + r2
            int r1 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r1 <= 0) goto L_0x01e1
            float r1 = java.lang.Math.abs(r2)
            float r1 = -r1
            r2 = r1
        L_0x01e1:
            r1 = r11
        L_0x01e2:
            androidx.constraintlayout.motion.widget.MotionLayout r3 = r0.mMotionLayout
            int r4 = r0.mOnTouchUp
            r3.touchAnimateTo(r4, r1, r2)
            int r1 = (r11 > r14 ? 1 : (r11 == r14 ? 0 : -1))
            if (r1 >= 0) goto L_0x01f1
            int r1 = (r9 > r14 ? 1 : (r9 == r14 ? 0 : -1))
            if (r1 > 0) goto L_0x0202
        L_0x01f1:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionLayout$TransitionState r2 = androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED
            r1.setState(r2)
            return
        L_0x01f9:
            int r2 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r2 >= 0) goto L_0x0203
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0202
            goto L_0x0203
        L_0x0202:
            return
        L_0x0203:
            androidx.constraintlayout.motion.widget.MotionLayout r1 = r0.mMotionLayout
            androidx.constraintlayout.motion.widget.MotionLayout$TransitionState r2 = androidx.constraintlayout.motion.widget.MotionLayout.TransitionState.FINISHED
            r1.setState(r2)
            return
        L_0x020b:
            float r1 = r2.getRawX()
            r0.mLastTouchX = r1
            float r1 = r2.getRawY()
            r0.mLastTouchY = r1
            r2 = 0
            r0.mDragStarted = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.TouchResponse.processTouchEvent(android.view.MotionEvent, androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker, int, androidx.constraintlayout.motion.widget.MotionScene):void");
    }

    /* access modifiers changed from: package-private */
    public void setDown(float f, float f2) {
        this.mLastTouchX = f;
        this.mLastTouchY = f2;
    }

    /* access modifiers changed from: package-private */
    public float getProgressDirection(float f, float f2) {
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, this.mMotionLayout.getProgress(), this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f3 = this.mTouchDirectionX;
        if (f3 != 0.0f) {
            float[] fArr = this.mAnchorDpDt;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f * f3) / fArr[0];
        }
        float[] fArr2 = this.mAnchorDpDt;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f2 * this.mTouchDirectionY) / fArr2[1];
    }

    /* access modifiers changed from: package-private */
    public void scrollUp(float f, float f2) {
        boolean z = false;
        this.mDragStarted = false;
        float progress = this.mMotionLayout.getProgress();
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f3 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        float f4 = 0.0f;
        float f5 = f3 != 0.0f ? (f * f3) / fArr[0] : (f2 * this.mTouchDirectionY) / fArr[1];
        if (!Float.isNaN(f5)) {
            progress += f5 / 3.0f;
        }
        if (progress != 0.0f) {
            boolean z2 = progress != 1.0f;
            int i = this.mOnTouchUp;
            if (i != 3) {
                z = true;
            }
            if (z && z2) {
                MotionLayout motionLayout = this.mMotionLayout;
                if (((double) progress) >= 0.5d) {
                    f4 = 1.0f;
                }
                motionLayout.touchAnimateTo(i, f4, f5);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void scrollMove(float f, float f2) {
        float f3;
        float progress = this.mMotionLayout.getProgress();
        if (!this.mDragStarted) {
            this.mDragStarted = true;
            this.mMotionLayout.setProgress(progress);
        }
        this.mMotionLayout.getAnchorDpDt(this.mTouchAnchorId, progress, this.mTouchAnchorX, this.mTouchAnchorY, this.mAnchorDpDt);
        float f4 = this.mTouchDirectionX;
        float[] fArr = this.mAnchorDpDt;
        if (((double) Math.abs((f4 * fArr[0]) + (this.mTouchDirectionY * fArr[1]))) < 0.01d) {
            float[] fArr2 = this.mAnchorDpDt;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f5 = this.mTouchDirectionX;
        if (f5 != 0.0f) {
            f3 = (f * f5) / this.mAnchorDpDt[0];
        } else {
            f3 = (f2 * this.mTouchDirectionY) / this.mAnchorDpDt[1];
        }
        float max = Math.max(Math.min(progress + f3, 1.0f), 0.0f);
        if (max != this.mMotionLayout.getProgress()) {
            this.mMotionLayout.setProgress(max);
        }
    }

    /* access modifiers changed from: package-private */
    public void setupTouch() {
        View view;
        int i = this.mTouchAnchorId;
        if (i != -1) {
            view = this.mMotionLayout.findViewById(i);
            if (view == null) {
                Log.e(TAG, "cannot find TouchAnchorId @id/" + Debug.getName(this.mMotionLayout.getContext(), this.mTouchAnchorId));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new View.OnTouchListener(this) {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener(this) {
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                }
            });
        }
    }

    public void setAnchorId(int i) {
        this.mTouchAnchorId = i;
    }

    public int getAnchorId() {
        return this.mTouchAnchorId;
    }

    public void setTouchAnchorLocation(float f, float f2) {
        this.mTouchAnchorX = f;
        this.mTouchAnchorY = f2;
    }

    public void setMaxVelocity(float f) {
        this.mMaxVelocity = f;
    }

    public void setMaxAcceleration(float f) {
        this.mMaxAcceleration = f;
    }

    /* access modifiers changed from: package-private */
    public float getMaxAcceleration() {
        return this.mMaxAcceleration;
    }

    public float getMaxVelocity() {
        return this.mMaxVelocity;
    }

    /* access modifiers changed from: package-private */
    public boolean getMoveWhenScrollAtTop() {
        return this.mMoveWhenScrollAtTop;
    }

    public int getAutoCompleteMode() {
        return this.mAutoCompleteMode;
    }

    /* access modifiers changed from: package-private */
    public void setAutoCompleteMode(int i) {
        this.mAutoCompleteMode = i;
    }

    /* access modifiers changed from: package-private */
    public RectF getTouchRegion(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i = this.mTouchRegionId;
        if (i == -1 || (findViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set((float) findViewById.getLeft(), (float) findViewById.getTop(), (float) findViewById.getRight(), (float) findViewById.getBottom());
        return rectF;
    }

    /* access modifiers changed from: package-private */
    public int getTouchRegionId() {
        return this.mTouchRegionId;
    }

    /* access modifiers changed from: package-private */
    public RectF getLimitBoundsTo(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i = this.mLimitBoundsTo;
        if (i == -1 || (findViewById = viewGroup.findViewById(i)) == null) {
            return null;
        }
        rectF.set((float) findViewById.getLeft(), (float) findViewById.getTop(), (float) findViewById.getRight(), (float) findViewById.getBottom());
        return rectF;
    }

    /* access modifiers changed from: package-private */
    public int getLimitBoundsToId() {
        return this.mLimitBoundsTo;
    }

    /* access modifiers changed from: package-private */
    public float dot(float f, float f2) {
        return (f * this.mTouchDirectionX) + (f2 * this.mTouchDirectionY);
    }

    public String toString() {
        if (Float.isNaN(this.mTouchDirectionX)) {
            return Key.ROTATION;
        }
        return this.mTouchDirectionX + " , " + this.mTouchDirectionY;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public void setTouchUpMode(int i) {
        this.mOnTouchUp = i;
    }

    public float getSpringStiffness() {
        return this.mSpringStiffness;
    }

    public float getSpringMass() {
        return this.mSpringMass;
    }

    public float getSpringDamping() {
        return this.mSpringDamping;
    }

    public float getSpringStopThreshold() {
        return this.mSpringStopThreshold;
    }

    public int getSpringBoundary() {
        return this.mSpringBoundary;
    }

    /* access modifiers changed from: package-private */
    public boolean isDragStarted() {
        return this.mDragStarted;
    }
}
