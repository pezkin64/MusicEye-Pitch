package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.key.MotionKey;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.key.MotionKeyTrigger;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.DifferentialInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.FloatRect;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.core.motion.utils.Rect;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.motion.utils.Utils;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.core.motion.utils.ViewState;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Motion implements TypedValues {
    static final int BOUNCE = 4;
    private static final boolean DEBUG = false;
    public static final int DRAW_PATH_AS_CONFIGURED = 4;
    public static final int DRAW_PATH_BASIC = 1;
    public static final int DRAW_PATH_CARTESIAN = 3;
    public static final int DRAW_PATH_NONE = 0;
    public static final int DRAW_PATH_RECTANGLE = 5;
    public static final int DRAW_PATH_RELATIVE = 2;
    public static final int DRAW_PATH_SCREEN = 6;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    private static final boolean FAVOR_FIXED_SIZE_VIEWS = false;
    public static final int HORIZONTAL_PATH_X = 2;
    public static final int HORIZONTAL_PATH_Y = 3;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int INTERPOLATOR_UNDEFINED = -3;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    public static final int PATH_PERCENT = 0;
    public static final int PATH_PERPENDICULAR = 1;
    public static final int ROTATION_LEFT = 2;
    public static final int ROTATION_RIGHT = 1;
    private static final int SPLINE_STRING = -1;
    private static final String TAG = "MotionController";
    public static final int VERTICAL_PATH_X = 4;
    public static final int VERTICAL_PATH_Y = 5;
    private int MAX_DIMENSION = 4;
    String[] attributeTable;
    private CurveFit mArcSpline;
    private int[] mAttributeInterpolatorCount;
    private String[] mAttributeNames;
    private HashMap<String, SplineSet> mAttributesMap;
    String mConstraintTag;
    float mCurrentCenterX;
    float mCurrentCenterY;
    private int mCurveFitType = -1;
    private HashMap<String, KeyCycleOscillator> mCycleMap;
    private MotionPaths mEndMotionPath = new MotionPaths();
    private MotionConstrainedPoint mEndPoint = new MotionConstrainedPoint();
    int mId;
    private double[] mInterpolateData;
    private int[] mInterpolateVariables;
    private double[] mInterpolateVelocity;
    private ArrayList<MotionKey> mKeyList = new ArrayList<>();
    private MotionKeyTrigger[] mKeyTriggers;
    private ArrayList<MotionPaths> mMotionPaths = new ArrayList<>();
    float mMotionStagger = Float.NaN;
    private boolean mNoMovement = false;
    private int mPathMotionArc = -1;
    private DifferentialInterpolator mQuantizeMotionInterpolator = null;
    private float mQuantizeMotionPhase = Float.NaN;
    private int mQuantizeMotionSteps = -1;
    private CurveFit[] mSpline;
    float mStaggerOffset = 0.0f;
    float mStaggerScale = 1.0f;
    private MotionPaths mStartMotionPath = new MotionPaths();
    private MotionConstrainedPoint mStartPoint = new MotionConstrainedPoint();
    Rect mTempRect = new Rect();
    private HashMap<String, TimeCycleSplineSet> mTimeCycleAttributesMap;
    private int mTransformPivotTarget = -1;
    private MotionWidget mTransformPivotView = null;
    private float[] mValuesBuff = new float[4];
    private float[] mVelocity = new float[1];
    MotionWidget mView;

    /* access modifiers changed from: package-private */
    public void endTrigger(boolean z) {
    }

    public int getId(String str) {
        return 0;
    }

    public boolean setValue(int i, float f) {
        return false;
    }

    public boolean setValue(int i, boolean z) {
        return false;
    }

    public int getTransformPivotTarget() {
        return this.mTransformPivotTarget;
    }

    public void setTransformPivotTarget(int i) {
        this.mTransformPivotTarget = i;
        this.mTransformPivotView = null;
    }

    public MotionPaths getKeyFrame(int i) {
        return this.mMotionPaths.get(i);
    }

    public Motion(MotionWidget motionWidget) {
        setView(motionWidget);
    }

    public float getStartX() {
        return this.mStartMotionPath.x;
    }

    public float getStartY() {
        return this.mStartMotionPath.y;
    }

    public float getFinalX() {
        return this.mEndMotionPath.x;
    }

    public float getFinalY() {
        return this.mEndMotionPath.y;
    }

    public float getStartWidth() {
        return this.mStartMotionPath.width;
    }

    public float getStartHeight() {
        return this.mStartMotionPath.height;
    }

    public float getFinalWidth() {
        return this.mEndMotionPath.width;
    }

    public float getFinalHeight() {
        return this.mEndMotionPath.height;
    }

    public int getAnimateRelativeTo() {
        return this.mStartMotionPath.mAnimateRelativeTo;
    }

    public void setupRelative(Motion motion) {
        this.mStartMotionPath.setupRelative(motion, motion.mStartMotionPath);
        this.mEndMotionPath.setupRelative(motion, motion.mEndMotionPath);
    }

    public float getCenterX() {
        return this.mCurrentCenterX;
    }

    public float getCenterY() {
        return this.mCurrentCenterY;
    }

    public void getCenter(double d, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.mSpline[0].getPos(d, dArr);
        this.mSpline[0].getSlope(d, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, dArr, fArr, dArr2, fArr2);
    }

    public void buildPath(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f;
        float f2 = 1.0f / ((float) (i2 - 1));
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap3 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap3 == null ? null : hashMap3.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            keyCycleOscillator = hashMap4.get("translationY");
        }
        int i3 = 0;
        while (i3 < i2) {
            float f3 = ((float) i3) * f2;
            float f4 = this.mStaggerScale;
            float f5 = 0.0f;
            if (f4 != f) {
                float f6 = this.mStaggerOffset;
                if (f3 < f6) {
                    f3 = 0.0f;
                }
                if (f3 > f6 && ((double) f3) < 1.0d) {
                    f3 = Math.min((f3 - f6) * f4, f);
                }
            }
            double d = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f7 = Float.NaN;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f3) {
                        Easing easing2 = next.mKeyFrameEasing;
                        f5 = next.time;
                        easing = easing2;
                    } else if (Float.isNaN(f7)) {
                        f7 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f7)) {
                    f7 = 1.0f;
                }
                float f8 = f7 - f5;
                d = (double) ((((float) easing.get((double) ((f3 - f5) / f8))) * f8) + f5);
            }
            this.mSpline[0].getPos(d, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            int i4 = i3 * 2;
            this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, i4);
            if (keyCycleOscillator2 != null) {
                fArr[i4] = fArr[i4] + keyCycleOscillator2.get(f3);
            } else if (splineSet != null) {
                fArr[i4] = fArr[i4] + splineSet.get(f3);
            }
            if (keyCycleOscillator != null) {
                int i5 = i4 + 1;
                fArr[i5] = fArr[i5] + keyCycleOscillator.get(f3);
            } else if (splineSet2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + splineSet2.get(f3);
            }
            i3++;
            f = 1.0f;
        }
    }

    /* access modifiers changed from: package-private */
    public double[] getPos(double d) {
        this.mSpline[0].getPos(d, this.mInterpolateData);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                curveFit.getPos(d, dArr);
            }
        }
        return this.mInterpolateData;
    }

    /* access modifiers changed from: package-private */
    public void buildBounds(float[] fArr, int i) {
        int i2 = i;
        float f = 1.0f / ((float) (i2 - 1));
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            SplineSet splineSet = hashMap.get("translationX");
        }
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        if (hashMap2 != null) {
            SplineSet splineSet2 = hashMap2.get("translationY");
        }
        HashMap<String, KeyCycleOscillator> hashMap3 = this.mCycleMap;
        if (hashMap3 != null) {
            KeyCycleOscillator keyCycleOscillator = hashMap3.get("translationX");
        }
        HashMap<String, KeyCycleOscillator> hashMap4 = this.mCycleMap;
        if (hashMap4 != null) {
            KeyCycleOscillator keyCycleOscillator2 = hashMap4.get("translationY");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            float f2 = ((float) i3) * f;
            float f3 = this.mStaggerScale;
            float f4 = 0.0f;
            if (f3 != 1.0f) {
                float f5 = this.mStaggerOffset;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && ((double) f2) < 1.0d) {
                    f2 = Math.min((f2 - f5) * f3, 1.0f);
                }
            }
            double d = (double) f2;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f6 = Float.NaN;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f2) {
                        easing = next.mKeyFrameEasing;
                        f4 = next.time;
                    } else if (Float.isNaN(f6)) {
                        f6 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f6)) {
                    f6 = 1.0f;
                }
                float f7 = f6 - f4;
                d = (double) ((((float) easing.get((double) ((f2 - f4) / f7))) * f7) + f4);
            }
            this.mSpline[0].getPos(d, this.mInterpolateData);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                }
            }
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i3 * 2);
        }
    }

    private float getPreCycleDistance() {
        float[] fArr = new float[2];
        float f = 1.0f / ((float) 99);
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        float f2 = 0.0f;
        while (i < 100) {
            float f3 = ((float) i) * f;
            double d3 = (double) f3;
            Easing easing = this.mStartMotionPath.mKeyFrameEasing;
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            float f4 = Float.NaN;
            float f5 = 0.0f;
            while (it.hasNext()) {
                MotionPaths next = it.next();
                if (next.mKeyFrameEasing != null) {
                    if (next.time < f3) {
                        easing = next.mKeyFrameEasing;
                        f5 = next.time;
                    } else if (Float.isNaN(f4)) {
                        f4 = next.time;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f4)) {
                    f4 = 1.0f;
                }
                float f6 = f4 - f5;
                d3 = (double) ((((float) easing.get((double) ((f3 - f5) / f6))) * f6) + f5);
            }
            this.mSpline[0].getPos(d3, this.mInterpolateData);
            int i2 = i;
            float f7 = f2;
            this.mStartMotionPath.getCenter(d3, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
            f2 = i2 > 0 ? (float) (((double) f7) + Math.hypot(d2 - ((double) fArr[1]), d - ((double) fArr[0]))) : f7;
            d = (double) fArr[0];
            d2 = (double) fArr[1];
            i = i2 + 1;
        }
        return f2;
    }

    /* access modifiers changed from: package-private */
    public MotionKeyPosition getPositionKeyframe(int i, int i2, float f, float f2) {
        float f3;
        float f4;
        int i3;
        int i4;
        FloatRect floatRect = new FloatRect();
        floatRect.left = this.mStartMotionPath.x;
        floatRect.top = this.mStartMotionPath.y;
        floatRect.right = floatRect.left + this.mStartMotionPath.width;
        floatRect.bottom = floatRect.top + this.mStartMotionPath.height;
        FloatRect floatRect2 = new FloatRect();
        floatRect2.left = this.mEndMotionPath.x;
        floatRect2.top = this.mEndMotionPath.y;
        floatRect2.right = floatRect2.left + this.mEndMotionPath.width;
        floatRect2.bottom = floatRect2.top + this.mEndMotionPath.height;
        Iterator<MotionKey> it = this.mKeyList.iterator();
        while (it.hasNext()) {
            MotionKey next = it.next();
            if (next instanceof MotionKeyPosition) {
                MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                i4 = i;
                i3 = i2;
                f4 = f;
                f3 = f2;
                if (motionKeyPosition.intersects(i4, i3, floatRect, floatRect2, f4, f3)) {
                    return motionKeyPosition;
                }
            } else {
                i4 = i;
                i3 = i2;
                f4 = f;
                f3 = f2;
            }
            i = i4;
            i2 = i3;
            f = f4;
            f2 = f3;
        }
        return null;
    }

    public int buildKeyFrames(float[] fArr, int[] iArr, int[] iArr2) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        if (iArr2 != null) {
            Iterator<MotionPaths> it2 = this.mMotionPaths.iterator();
            int i2 = 0;
            while (it2.hasNext()) {
                iArr2[i2] = (int) (it2.next().position * 100.0f);
                i2++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < timePoints.length; i4++) {
            this.mSpline[0].getPos(timePoints[i4], this.mInterpolateData);
            this.mStartMotionPath.getCenter(timePoints[i4], this.mInterpolateVariables, this.mInterpolateData, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    /* access modifiers changed from: package-private */
    public int buildKeyBounds(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] timePoints = this.mSpline[0].getTimePoints();
        if (iArr != null) {
            Iterator<MotionPaths> it = this.mMotionPaths.iterator();
            int i = 0;
            while (it.hasNext()) {
                iArr[i] = it.next().mMode;
                i++;
            }
        }
        int i2 = 0;
        for (double pos : timePoints) {
            this.mSpline[0].getPos(pos, this.mInterpolateData);
            this.mStartMotionPath.getBounds(this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
        }
        return i2 / 2;
    }

    /* access modifiers changed from: package-private */
    public int getAttributeValues(String str, float[] fArr, int i) {
        SplineSet splineSet = this.mAttributesMap.get(str);
        if (splineSet == null) {
            return -1;
        }
        for (int i2 = 0; i2 < fArr.length; i2++) {
            fArr[i2] = splineSet.get((float) (i2 / (fArr.length - 1)));
        }
        return fArr.length;
    }

    public void buildRect(float f, float[] fArr, int i) {
        this.mSpline[0].getPos((double) getAdjustedPosition(f, (float[]) null), this.mInterpolateData);
        this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i);
    }

    /* access modifiers changed from: package-private */
    public void buildRectangles(float[] fArr, int i) {
        float f = 1.0f / ((float) (i - 1));
        for (int i2 = 0; i2 < i; i2++) {
            this.mSpline[0].getPos((double) getAdjustedPosition(((float) i2) * f, (float[]) null), this.mInterpolateData);
            this.mStartMotionPath.getRect(this.mInterpolateVariables, this.mInterpolateData, fArr, i2 * 8);
        }
    }

    /* access modifiers changed from: package-private */
    public float getKeyFrameParameter(int i, float f, float f2) {
        float f3 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f4 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f5 = this.mStartMotionPath.x + (this.mStartMotionPath.width / 2.0f);
        float f6 = this.mStartMotionPath.y + (this.mStartMotionPath.height / 2.0f);
        float hypot = (float) Math.hypot((double) f3, (double) f4);
        if (((double) hypot) < 1.0E-7d) {
            return Float.NaN;
        }
        float f7 = f - f5;
        float f8 = f2 - f6;
        if (((float) Math.hypot((double) f7, (double) f8)) == 0.0f) {
            return 0.0f;
        }
        float f9 = (f7 * f3) + (f8 * f4);
        if (i == 0) {
            return f9 / hypot;
        }
        if (i == 1) {
            return (float) Math.sqrt((double) ((hypot * hypot) - (f9 * f9)));
        }
        if (i == 2) {
            return f7 / f3;
        }
        if (i == 3) {
            return f8 / f3;
        }
        if (i == 4) {
            return f7 / f4;
        }
        if (i != 5) {
            return 0.0f;
        }
        return f8 / f4;
    }

    private void insertKey(MotionPaths motionPaths) {
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        MotionPaths motionPaths2 = null;
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (motionPaths.position == next.position) {
                motionPaths2 = next;
            }
        }
        if (motionPaths2 != null) {
            this.mMotionPaths.remove(motionPaths2);
        }
        int binarySearch = Collections.binarySearch(this.mMotionPaths, motionPaths);
        if (binarySearch == 0) {
            Utils.loge(TAG, " KeyPath position \"" + motionPaths.position + "\" outside of range");
        }
        this.mMotionPaths.add((-binarySearch) - 1, motionPaths);
    }

    /* access modifiers changed from: package-private */
    public void addKeys(ArrayList<MotionKey> arrayList) {
        this.mKeyList.addAll(arrayList);
    }

    public void addKey(MotionKey motionKey) {
        this.mKeyList.add(motionKey);
    }

    public void setPathMotionArc(int i) {
        this.mPathMotionArc = i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v1, resolved type: double[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v46, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v47, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v2, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r20v3, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v16, resolved type: double[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v19, resolved type: double[]} */
    /* JADX WARNING: Code restructure failed: missing block: B:278:0x042a, code lost:
        r11 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x016d, code lost:
        r9 = (java.lang.Integer) r6.get(r8);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setup(int r20, int r21, float r22, long r23) {
        /*
            r19 = this;
            r0 = r19
            r1 = r23
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashSet r4 = new java.util.HashSet
            r4.<init>()
            java.util.HashSet r5 = new java.util.HashSet
            r5.<init>()
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            int r7 = r0.mPathMotionArc
            r8 = -1
            if (r7 == r8) goto L_0x0026
            androidx.constraintlayout.core.motion.MotionPaths r9 = r0.mStartMotionPath
            r9.mPathMotionArc = r7
        L_0x0026:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mStartPoint
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r9 = r0.mEndPoint
            r7.different(r9, r4)
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r7 = r0.mKeyList
            if (r7 == 0) goto L_0x0091
            java.util.Iterator r7 = r7.iterator()
            r10 = 0
        L_0x0036:
            boolean r11 = r7.hasNext()
            if (r11 == 0) goto L_0x0092
            java.lang.Object r11 = r7.next()
            androidx.constraintlayout.core.motion.key.MotionKey r11 = (androidx.constraintlayout.core.motion.key.MotionKey) r11
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyPosition
            if (r12 == 0) goto L_0x0069
            r16 = r11
            androidx.constraintlayout.core.motion.key.MotionKeyPosition r16 = (androidx.constraintlayout.core.motion.key.MotionKeyPosition) r16
            androidx.constraintlayout.core.motion.MotionPaths r13 = new androidx.constraintlayout.core.motion.MotionPaths
            androidx.constraintlayout.core.motion.MotionPaths r11 = r0.mStartMotionPath
            androidx.constraintlayout.core.motion.MotionPaths r12 = r0.mEndMotionPath
            r14 = r20
            r15 = r21
            r17 = r11
            r18 = r12
            r13.<init>(r14, r15, r16, r17, r18)
            r11 = r16
            r0.insertKey(r13)
            int r12 = r11.mCurveFit
            if (r12 == r8) goto L_0x0036
            int r11 = r11.mCurveFit
            r0.mCurveFitType = r11
            goto L_0x0036
        L_0x0069:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r12 == 0) goto L_0x0071
            r11.getAttributeNames(r5)
            goto L_0x0036
        L_0x0071:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r12 == 0) goto L_0x0079
            r11.getAttributeNames(r3)
            goto L_0x0036
        L_0x0079:
            boolean r12 = r11 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTrigger
            if (r12 == 0) goto L_0x008a
            if (r10 != 0) goto L_0x0084
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
        L_0x0084:
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger r11 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger) r11
            r10.add(r11)
            goto L_0x0036
        L_0x008a:
            r11.setInterpolation(r6)
            r11.getAttributeNames(r4)
            goto L_0x0036
        L_0x0091:
            r10 = 0
        L_0x0092:
            r7 = 0
            if (r10 == 0) goto L_0x009f
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r11 = new androidx.constraintlayout.core.motion.key.MotionKeyTrigger[r7]
            java.lang.Object[] r10 = r10.toArray(r11)
            androidx.constraintlayout.core.motion.key.MotionKeyTrigger[] r10 = (androidx.constraintlayout.core.motion.key.MotionKeyTrigger[]) r10
            r0.mKeyTriggers = r10
        L_0x009f:
            boolean r10 = r4.isEmpty()
            java.lang.String r11 = ","
            java.lang.String r12 = "CUSTOM,"
            r13 = 1
            if (r10 != 0) goto L_0x0189
            java.util.HashMap r10 = new java.util.HashMap
            r10.<init>()
            r0.mAttributesMap = r10
            java.util.Iterator r10 = r4.iterator()
        L_0x00b5:
            boolean r14 = r10.hasNext()
            if (r14 == 0) goto L_0x011e
            java.lang.Object r14 = r10.next()
            java.lang.String r14 = (java.lang.String) r14
            boolean r15 = r14.startsWith(r12)
            if (r15 == 0) goto L_0x0106
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r15 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r15.<init>()
            java.lang.String[] r16 = r14.split(r11)
            r22 = 0
            r9 = r16[r13]
            r20 = r13
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r13 = r0.mKeyList
            java.util.Iterator r13 = r13.iterator()
        L_0x00dc:
            boolean r16 = r13.hasNext()
            if (r16 == 0) goto L_0x0101
            java.lang.Object r16 = r13.next()
            r8 = r16
            androidx.constraintlayout.core.motion.key.MotionKey r8 = (androidx.constraintlayout.core.motion.key.MotionKey) r8
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r7 = r8.mCustom
            if (r7 != 0) goto L_0x00f1
        L_0x00ee:
            r7 = 0
            r8 = -1
            goto L_0x00dc
        L_0x00f1:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r7 = r8.mCustom
            java.lang.Object r7 = r7.get(r9)
            androidx.constraintlayout.core.motion.CustomVariable r7 = (androidx.constraintlayout.core.motion.CustomVariable) r7
            if (r7 == 0) goto L_0x00ee
            int r8 = r8.mFramePosition
            r15.append(r8, r7)
            goto L_0x00ee
        L_0x0101:
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r14, r15)
            goto L_0x010e
        L_0x0106:
            r20 = r13
            r22 = 0
            androidx.constraintlayout.core.motion.utils.SplineSet r7 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r14, r1)
        L_0x010e:
            if (r7 != 0) goto L_0x0111
            goto L_0x0119
        L_0x0111:
            r7.setType(r14)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r8 = r0.mAttributesMap
            r8.put(r14, r7)
        L_0x0119:
            r13 = r20
            r7 = 0
            r8 = -1
            goto L_0x00b5
        L_0x011e:
            r20 = r13
            r22 = 0
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r7 = r0.mKeyList
            if (r7 == 0) goto L_0x0140
            java.util.Iterator r7 = r7.iterator()
        L_0x012a:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0140
            java.lang.Object r8 = r7.next()
            androidx.constraintlayout.core.motion.key.MotionKey r8 = (androidx.constraintlayout.core.motion.key.MotionKey) r8
            boolean r9 = r8 instanceof androidx.constraintlayout.core.motion.key.MotionKeyAttributes
            if (r9 == 0) goto L_0x012a
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r9 = r0.mAttributesMap
            r8.addValues(r9)
            goto L_0x012a
        L_0x0140:
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mStartPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r8 = r0.mAttributesMap
            r9 = 0
            r7.addValues(r8, r9)
            androidx.constraintlayout.core.motion.MotionConstrainedPoint r7 = r0.mEndPoint
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r8 = r0.mAttributesMap
            r9 = 100
            r7.addValues(r8, r9)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r7 = r0.mAttributesMap
            java.util.Set r7 = r7.keySet()
            java.util.Iterator r7 = r7.iterator()
        L_0x015b:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x018d
            java.lang.Object r8 = r7.next()
            java.lang.String r8 = (java.lang.String) r8
            boolean r9 = r6.containsKey(r8)
            if (r9 == 0) goto L_0x017a
            java.lang.Object r9 = r6.get(r8)
            java.lang.Integer r9 = (java.lang.Integer) r9
            if (r9 == 0) goto L_0x017a
            int r9 = r9.intValue()
            goto L_0x017b
        L_0x017a:
            r9 = 0
        L_0x017b:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.SplineSet> r10 = r0.mAttributesMap
            java.lang.Object r8 = r10.get(r8)
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = (androidx.constraintlayout.core.motion.utils.SplineSet) r8
            if (r8 == 0) goto L_0x015b
            r8.setup(r9)
            goto L_0x015b
        L_0x0189:
            r20 = r13
            r22 = 0
        L_0x018d:
            boolean r7 = r3.isEmpty()
            if (r7 != 0) goto L_0x0253
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r7 = r0.mTimeCycleAttributesMap
            if (r7 != 0) goto L_0x019e
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            r0.mTimeCycleAttributesMap = r7
        L_0x019e:
            java.util.Iterator r3 = r3.iterator()
        L_0x01a2:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x01ff
            java.lang.Object r7 = r3.next()
            java.lang.String r7 = (java.lang.String) r7
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r8 = r0.mTimeCycleAttributesMap
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x01b7
            goto L_0x01a2
        L_0x01b7:
            boolean r8 = r7.startsWith(r12)
            if (r8 == 0) goto L_0x01f4
            androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar r8 = new androidx.constraintlayout.core.motion.utils.KeyFrameArray$CustomVar
            r8.<init>()
            java.lang.String[] r9 = r7.split(r11)
            r9 = r9[r20]
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r10 = r0.mKeyList
            java.util.Iterator r10 = r10.iterator()
        L_0x01ce:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x01ef
            java.lang.Object r13 = r10.next()
            androidx.constraintlayout.core.motion.key.MotionKey r13 = (androidx.constraintlayout.core.motion.key.MotionKey) r13
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r14 = r13.mCustom
            if (r14 != 0) goto L_0x01df
            goto L_0x01ce
        L_0x01df:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r14 = r13.mCustom
            java.lang.Object r14 = r14.get(r9)
            androidx.constraintlayout.core.motion.CustomVariable r14 = (androidx.constraintlayout.core.motion.CustomVariable) r14
            if (r14 == 0) goto L_0x01ce
            int r13 = r13.mFramePosition
            r8.append(r13, r14)
            goto L_0x01ce
        L_0x01ef:
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeCustomSplineSet(r7, r8)
            goto L_0x01f8
        L_0x01f4:
            androidx.constraintlayout.core.motion.utils.SplineSet r8 = androidx.constraintlayout.core.motion.utils.SplineSet.makeSpline(r7, r1)
        L_0x01f8:
            if (r8 != 0) goto L_0x01fb
            goto L_0x01a2
        L_0x01fb:
            r8.setType(r7)
            goto L_0x01a2
        L_0x01ff:
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r1 = r0.mKeyList
            if (r1 == 0) goto L_0x021f
            java.util.Iterator r1 = r1.iterator()
        L_0x0207:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x021f
            java.lang.Object r2 = r1.next()
            androidx.constraintlayout.core.motion.key.MotionKey r2 = (androidx.constraintlayout.core.motion.key.MotionKey) r2
            boolean r3 = r2 instanceof androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle
            if (r3 == 0) goto L_0x0207
            androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle r2 = (androidx.constraintlayout.core.motion.key.MotionKeyTimeCycle) r2
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r3 = r0.mTimeCycleAttributesMap
            r2.addTimeValues(r3)
            goto L_0x0207
        L_0x021f:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r1 = r0.mTimeCycleAttributesMap
            java.util.Set r1 = r1.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0229:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0253
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r6.containsKey(r2)
            if (r3 == 0) goto L_0x0246
            java.lang.Object r3 = r6.get(r2)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L_0x0247
        L_0x0246:
            r3 = 0
        L_0x0247:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet> r7 = r0.mTimeCycleAttributesMap
            java.lang.Object r2 = r7.get(r2)
            androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet r2 = (androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet) r2
            r2.setup(r3)
            goto L_0x0229
        L_0x0253:
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r1 = r0.mMotionPaths
            int r1 = r1.size()
            int r2 = r1 + 2
            androidx.constraintlayout.core.motion.MotionPaths[] r3 = new androidx.constraintlayout.core.motion.MotionPaths[r2]
            androidx.constraintlayout.core.motion.MotionPaths r6 = r0.mStartMotionPath
            r9 = 0
            r3[r9] = r6
            int r1 = r1 + 1
            androidx.constraintlayout.core.motion.MotionPaths r6 = r0.mEndMotionPath
            r3[r1] = r6
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r1 = r0.mMotionPaths
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0278
            int r1 = r0.mCurveFitType
            int r6 = androidx.constraintlayout.core.motion.key.MotionKey.UNSET
            if (r1 != r6) goto L_0x0278
            r0.mCurveFitType = r9
        L_0x0278:
            java.util.ArrayList<androidx.constraintlayout.core.motion.MotionPaths> r1 = r0.mMotionPaths
            java.util.Iterator r1 = r1.iterator()
            r6 = r20
        L_0x0280:
            boolean r7 = r1.hasNext()
            if (r7 == 0) goto L_0x0292
            java.lang.Object r7 = r1.next()
            androidx.constraintlayout.core.motion.MotionPaths r7 = (androidx.constraintlayout.core.motion.MotionPaths) r7
            int r8 = r6 + 1
            r3[r6] = r7
            r6 = r8
            goto L_0x0280
        L_0x0292:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            androidx.constraintlayout.core.motion.MotionPaths r6 = r0.mEndMotionPath
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r6 = r6.customAttributes
            java.util.Set r6 = r6.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x02a3:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x02cf
            java.lang.Object r7 = r6.next()
            java.lang.String r7 = (java.lang.String) r7
            androidx.constraintlayout.core.motion.MotionPaths r8 = r0.mStartMotionPath
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r8 = r8.customAttributes
            boolean r8 = r8.containsKey(r7)
            if (r8 == 0) goto L_0x02a3
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>(r12)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            boolean r8 = r4.contains(r8)
            if (r8 != 0) goto L_0x02a3
            r1.add(r7)
            goto L_0x02a3
        L_0x02cf:
            r9 = 0
            java.lang.String[] r4 = new java.lang.String[r9]
            java.lang.Object[] r1 = r1.toArray(r4)
            java.lang.String[] r1 = (java.lang.String[]) r1
            r0.mAttributeNames = r1
            int r1 = r1.length
            int[] r1 = new int[r1]
            r0.mAttributeInterpolatorCount = r1
            r1 = 0
        L_0x02e0:
            java.lang.String[] r4 = r0.mAttributeNames
            int r6 = r4.length
            if (r1 >= r6) goto L_0x0317
            r4 = r4[r1]
            int[] r6 = r0.mAttributeInterpolatorCount
            r9 = 0
            r6[r1] = r9
            r6 = 0
        L_0x02ed:
            if (r6 >= r2) goto L_0x0314
            r7 = r3[r6]
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r7 = r7.customAttributes
            boolean r7 = r7.containsKey(r4)
            if (r7 == 0) goto L_0x0311
            r7 = r3[r6]
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.CustomVariable> r7 = r7.customAttributes
            java.lang.Object r7 = r7.get(r4)
            androidx.constraintlayout.core.motion.CustomVariable r7 = (androidx.constraintlayout.core.motion.CustomVariable) r7
            if (r7 == 0) goto L_0x0311
            int[] r4 = r0.mAttributeInterpolatorCount
            r6 = r4[r1]
            int r7 = r7.numberOfInterpolatedValues()
            int r6 = r6 + r7
            r4[r1] = r6
            goto L_0x0314
        L_0x0311:
            int r6 = r6 + 1
            goto L_0x02ed
        L_0x0314:
            int r1 = r1 + 1
            goto L_0x02e0
        L_0x0317:
            r9 = 0
            r1 = r3[r9]
            int r1 = r1.mPathMotionArc
            r4 = -1
            if (r1 == r4) goto L_0x0322
            r1 = r20
            goto L_0x0323
        L_0x0322:
            r1 = 0
        L_0x0323:
            java.lang.String[] r4 = r0.mAttributeNames
            int r4 = r4.length
            r6 = 18
            int r6 = r6 + r4
            boolean[] r4 = new boolean[r6]
            r7 = r20
        L_0x032d:
            if (r7 >= r2) goto L_0x033d
            r8 = r3[r7]
            int r9 = r7 + -1
            r9 = r3[r9]
            java.lang.String[] r10 = r0.mAttributeNames
            r8.different(r9, r4, r10, r1)
            int r7 = r7 + 1
            goto L_0x032d
        L_0x033d:
            r7 = r20
            r1 = 0
        L_0x0340:
            if (r7 >= r6) goto L_0x034b
            boolean r8 = r4[r7]
            if (r8 == 0) goto L_0x0348
            int r1 = r1 + 1
        L_0x0348:
            int r7 = r7 + 1
            goto L_0x0340
        L_0x034b:
            int[] r7 = new int[r1]
            r0.mInterpolateVariables = r7
            r7 = 2
            int r1 = java.lang.Math.max(r7, r1)
            double[] r8 = new double[r1]
            r0.mInterpolateData = r8
            double[] r1 = new double[r1]
            r0.mInterpolateVelocity = r1
            r8 = r20
            r1 = 0
        L_0x035f:
            if (r8 >= r6) goto L_0x036f
            boolean r9 = r4[r8]
            if (r9 == 0) goto L_0x036c
            int[] r9 = r0.mInterpolateVariables
            int r10 = r1 + 1
            r9[r1] = r8
            r1 = r10
        L_0x036c:
            int r8 = r8 + 1
            goto L_0x035f
        L_0x036f:
            int[] r1 = r0.mInterpolateVariables
            int r1 = r1.length
            int[] r4 = new int[r7]
            r4[r20] = r1
            r9 = 0
            r4[r9] = r2
            java.lang.Class r1 = java.lang.Double.TYPE
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r4)
            double[][] r1 = (double[][]) r1
            double[] r4 = new double[r2]
            r6 = 0
        L_0x0384:
            if (r6 >= r2) goto L_0x0399
            r8 = r3[r6]
            r9 = r1[r6]
            int[] r10 = r0.mInterpolateVariables
            r8.fillStandard(r9, r10)
            r8 = r3[r6]
            float r8 = r8.time
            double r8 = (double) r8
            r4[r6] = r8
            int r6 = r6 + 1
            goto L_0x0384
        L_0x0399:
            r6 = 0
        L_0x039a:
            int[] r8 = r0.mInterpolateVariables
            int r9 = r8.length
            if (r6 >= r9) goto L_0x03dc
            r8 = r8[r6]
            java.lang.String[] r9 = androidx.constraintlayout.core.motion.MotionPaths.names
            int r9 = r9.length
            if (r8 >= r9) goto L_0x03d9
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String[] r9 = androidx.constraintlayout.core.motion.MotionPaths.names
            int[] r10 = r0.mInterpolateVariables
            r10 = r10[r6]
            r9 = r9[r10]
            r8.append(r9)
            java.lang.String r9 = " ["
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r9 = r8
            r8 = 0
        L_0x03c1:
            if (r8 >= r2) goto L_0x03d9
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r9)
            r9 = r1[r8]
            r11 = r9[r6]
            r10.append(r11)
            java.lang.String r9 = r10.toString()
            int r8 = r8 + 1
            goto L_0x03c1
        L_0x03d9:
            int r6 = r6 + 1
            goto L_0x039a
        L_0x03dc:
            java.lang.String[] r6 = r0.mAttributeNames
            int r6 = r6.length
            int r6 = r6 + 1
            androidx.constraintlayout.core.motion.utils.CurveFit[] r6 = new androidx.constraintlayout.core.motion.utils.CurveFit[r6]
            r0.mSpline = r6
            r6 = 0
        L_0x03e6:
            java.lang.String[] r8 = r0.mAttributeNames
            int r9 = r8.length
            if (r6 >= r9) goto L_0x0444
            r9 = r22
            double[][] r9 = (double[][]) r9
            r8 = r8[r6]
            r11 = r22
            r12 = r11
            r9 = 0
            r10 = 0
        L_0x03f6:
            if (r9 >= r2) goto L_0x042d
            r13 = r3[r9]
            boolean r13 = r13.hasCustomData(r8)
            if (r13 == 0) goto L_0x042a
            if (r12 != 0) goto L_0x0419
            double[] r11 = new double[r2]
            r12 = r3[r9]
            int r12 = r12.getCustomDataCount(r8)
            int[] r13 = new int[r7]
            r13[r20] = r12
            r12 = 0
            r13[r12] = r2
            java.lang.Class r12 = java.lang.Double.TYPE
            java.lang.Object r12 = java.lang.reflect.Array.newInstance(r12, r13)
            double[][] r12 = (double[][]) r12
        L_0x0419:
            r13 = r3[r9]
            float r13 = r13.time
            double r13 = (double) r13
            r11[r10] = r13
            r13 = r3[r9]
            r14 = r12[r10]
            r15 = 0
            r13.getCustomData(r8, r14, r15)
            int r10 = r10 + 1
        L_0x042a:
            int r9 = r9 + 1
            goto L_0x03f6
        L_0x042d:
            double[] r8 = java.util.Arrays.copyOf(r11, r10)
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r12, r10)
            double[][] r9 = (double[][]) r9
            androidx.constraintlayout.core.motion.utils.CurveFit[] r10 = r0.mSpline
            int r6 = r6 + 1
            int r11 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r8 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r11, r8, r9)
            r10[r6] = r8
            goto L_0x03e6
        L_0x0444:
            androidx.constraintlayout.core.motion.utils.CurveFit[] r6 = r0.mSpline
            int r8 = r0.mCurveFitType
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.get(r8, r4, r1)
            r9 = 0
            r6[r9] = r1
            r1 = r3[r9]
            int r1 = r1.mPathMotionArc
            r4 = -1
            if (r1 == r4) goto L_0x0494
            int[] r1 = new int[r2]
            double[] r4 = new double[r2]
            int[] r6 = new int[r7]
            r6[r20] = r7
            r6[r9] = r2
            java.lang.Class r7 = java.lang.Double.TYPE
            java.lang.Object r6 = java.lang.reflect.Array.newInstance(r7, r6)
            double[][] r6 = (double[][]) r6
            r9 = 0
        L_0x0469:
            if (r9 >= r2) goto L_0x048e
            r7 = r3[r9]
            int r7 = r7.mPathMotionArc
            r1[r9] = r7
            r7 = r3[r9]
            float r7 = r7.time
            double r7 = (double) r7
            r4[r9] = r7
            r7 = r6[r9]
            r8 = r3[r9]
            float r8 = r8.x
            double r10 = (double) r8
            r12 = 0
            r7[r12] = r10
            r7 = r6[r9]
            r8 = r3[r9]
            float r8 = r8.y
            double r10 = (double) r8
            r7[r20] = r10
            int r9 = r9 + 1
            goto L_0x0469
        L_0x048e:
            androidx.constraintlayout.core.motion.utils.CurveFit r1 = androidx.constraintlayout.core.motion.utils.CurveFit.getArc(r1, r4, r6)
            r0.mArcSpline = r1
        L_0x0494:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r0.mCycleMap = r1
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r1 = r0.mKeyList
            if (r1 == 0) goto L_0x0509
            java.util.Iterator r1 = r5.iterator()
            r2 = 2143289344(0x7fc00000, float:NaN)
        L_0x04a5:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x04d1
            java.lang.Object r3 = r1.next()
            java.lang.String r3 = (java.lang.String) r3
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r4 = androidx.constraintlayout.core.motion.utils.KeyCycleOscillator.makeWidgetCycle(r3)
            if (r4 != 0) goto L_0x04b8
            goto L_0x04a5
        L_0x04b8:
            boolean r5 = r4.variesByPath()
            if (r5 == 0) goto L_0x04c8
            boolean r5 = java.lang.Float.isNaN(r2)
            if (r5 == 0) goto L_0x04c8
            float r2 = r0.getPreCycleDistance()
        L_0x04c8:
            r4.setType(r3)
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r5 = r0.mCycleMap
            r5.put(r3, r4)
            goto L_0x04a5
        L_0x04d1:
            java.util.ArrayList<androidx.constraintlayout.core.motion.key.MotionKey> r1 = r0.mKeyList
            java.util.Iterator r1 = r1.iterator()
        L_0x04d7:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x04ef
            java.lang.Object r3 = r1.next()
            androidx.constraintlayout.core.motion.key.MotionKey r3 = (androidx.constraintlayout.core.motion.key.MotionKey) r3
            boolean r4 = r3 instanceof androidx.constraintlayout.core.motion.key.MotionKeyCycle
            if (r4 == 0) goto L_0x04d7
            androidx.constraintlayout.core.motion.key.MotionKeyCycle r3 = (androidx.constraintlayout.core.motion.key.MotionKeyCycle) r3
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r4 = r0.mCycleMap
            r3.addCycleValues(r4)
            goto L_0x04d7
        L_0x04ef:
            java.util.HashMap<java.lang.String, androidx.constraintlayout.core.motion.utils.KeyCycleOscillator> r1 = r0.mCycleMap
            java.util.Collection r1 = r1.values()
            java.util.Iterator r1 = r1.iterator()
        L_0x04f9:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x0509
            java.lang.Object r3 = r1.next()
            androidx.constraintlayout.core.motion.utils.KeyCycleOscillator r3 = (androidx.constraintlayout.core.motion.utils.KeyCycleOscillator) r3
            r3.setup(r2)
            goto L_0x04f9
        L_0x0509:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.motion.Motion.setup(int, int, float, long):void");
    }

    public String toString() {
        return " start: x: " + this.mStartMotionPath.x + " y: " + this.mStartMotionPath.y + " end: x: " + this.mEndMotionPath.x + " y: " + this.mEndMotionPath.y;
    }

    private void readView(MotionPaths motionPaths) {
        motionPaths.setBounds((float) this.mView.getX(), (float) this.mView.getY(), (float) this.mView.getWidth(), (float) this.mView.getHeight());
    }

    public void setView(MotionWidget motionWidget) {
        this.mView = motionWidget;
    }

    public MotionWidget getView() {
        return this.mView;
    }

    public void setStart(MotionWidget motionWidget) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mStartMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mStartMotionPath.applyParameters(motionWidget);
        this.mStartPoint.setState(motionWidget);
    }

    public void setEnd(MotionWidget motionWidget) {
        this.mEndMotionPath.time = 1.0f;
        this.mEndMotionPath.position = 1.0f;
        readView(this.mEndMotionPath);
        this.mEndMotionPath.setBounds((float) motionWidget.getLeft(), (float) motionWidget.getTop(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mEndMotionPath.applyParameters(motionWidget);
        this.mEndPoint.setState(motionWidget);
    }

    public void setStartState(ViewState viewState, MotionWidget motionWidget, int i, int i2, int i3) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        Rect rect = new Rect();
        if (i == 1) {
            int i4 = viewState.left + viewState.right;
            rect.left = ((viewState.top + viewState.bottom) - viewState.width()) / 2;
            rect.top = i2 - ((i4 + viewState.height()) / 2);
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        } else if (i == 2) {
            int i5 = viewState.left + viewState.right;
            rect.left = i3 - (((viewState.top + viewState.bottom) + viewState.width()) / 2);
            rect.top = (i5 - viewState.height()) / 2;
            rect.right = rect.left + viewState.width();
            rect.bottom = rect.top + viewState.height();
        }
        this.mStartMotionPath.setBounds((float) rect.left, (float) rect.top, (float) rect.width(), (float) rect.height());
        this.mStartPoint.setState(rect, motionWidget, i, viewState.rotation);
    }

    /* access modifiers changed from: package-private */
    public void rotate(Rect rect, Rect rect2, int i, int i2, int i3) {
        if (i == 1) {
            int i4 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i3 - ((i4 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 2) {
            int i5 = rect.left + rect.right;
            rect2.left = i2 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i5 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 3) {
            int i6 = rect.left + rect.right;
            int i7 = rect.top;
            int i8 = rect.bottom;
            rect2.left = ((rect.height() / 2) + rect.top) - (i6 / 2);
            rect2.top = i3 - ((i6 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        } else if (i == 4) {
            int i9 = rect.left + rect.right;
            rect2.left = i2 - (((rect.bottom + rect.top) + rect.width()) / 2);
            rect2.top = (i9 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
        }
    }

    private static DifferentialInterpolator getInterpolator(int i, String str, int i2) {
        if (i != -1) {
            return null;
        }
        final Easing interpolator = Easing.getInterpolator(str);
        return new DifferentialInterpolator() {
            float mX;

            public float getInterpolation(float f) {
                this.mX = f;
                return (float) interpolator.get((double) f);
            }

            public float getVelocity() {
                return (float) interpolator.getDiff((double) this.mX);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void setBothStates(MotionWidget motionWidget) {
        this.mStartMotionPath.time = 0.0f;
        this.mStartMotionPath.position = 0.0f;
        this.mNoMovement = true;
        this.mStartMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mEndMotionPath.setBounds((float) motionWidget.getX(), (float) motionWidget.getY(), (float) motionWidget.getWidth(), (float) motionWidget.getHeight());
        this.mStartPoint.setState(motionWidget);
        this.mEndPoint.setState(motionWidget);
    }

    private float getAdjustedPosition(float f, float[] fArr) {
        float f2 = 0.0f;
        float f3 = 1.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.mStaggerScale;
            if (((double) f4) != 1.0d) {
                float f5 = this.mStaggerOffset;
                if (f < f5) {
                    f = 0.0f;
                }
                if (f > f5 && ((double) f) < 1.0d) {
                    f = Math.min((f - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.mStartMotionPath.mKeyFrameEasing;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        float f6 = Float.NaN;
        while (it.hasNext()) {
            MotionPaths next = it.next();
            if (next.mKeyFrameEasing != null) {
                if (next.time < f) {
                    easing = next.mKeyFrameEasing;
                    f2 = next.time;
                } else if (Float.isNaN(f6)) {
                    f6 = next.time;
                }
            }
        }
        if (easing != null) {
            if (!Float.isNaN(f6)) {
                f3 = f6;
            }
            float f7 = f3 - f2;
            double d = (double) ((f - f2) / f7);
            f = (((float) easing.get(d)) * f7) + f2;
            if (fArr != null) {
                fArr[0] = (float) easing.getDiff(d);
            }
        }
        return f;
    }

    public boolean interpolate(MotionWidget motionWidget, float f, long j, KeyCache keyCache) {
        MotionWidget motionWidget2 = motionWidget;
        float adjustedPosition = getAdjustedPosition(f, (float[]) null);
        int i = this.mQuantizeMotionSteps;
        if (i != -1) {
            float f2 = 1.0f / ((float) i);
            float floor = ((float) Math.floor((double) (adjustedPosition / f2))) * f2;
            float f3 = (adjustedPosition % f2) / f2;
            if (!Float.isNaN(this.mQuantizeMotionPhase)) {
                f3 = (f3 + this.mQuantizeMotionPhase) % 1.0f;
            }
            DifferentialInterpolator differentialInterpolator = this.mQuantizeMotionInterpolator;
            adjustedPosition = ((differentialInterpolator != null ? differentialInterpolator.getInterpolation(f3) : ((double) f3) > 0.5d ? 1.0f : 0.0f) * f2) + floor;
        }
        float f4 = adjustedPosition;
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        if (hashMap != null) {
            for (SplineSet property : hashMap.values()) {
                property.setProperty(motionWidget2, f4);
            }
        }
        CurveFit[] curveFitArr = this.mSpline;
        if (curveFitArr != null) {
            double d = (double) f4;
            curveFitArr[0].getPos(d, this.mInterpolateData);
            this.mSpline[0].getSlope(d, this.mInterpolateVelocity);
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr = this.mInterpolateData;
                if (dArr.length > 0) {
                    curveFit.getPos(d, dArr);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                }
            }
            if (!this.mNoMovement) {
                float f5 = f4;
                MotionWidget motionWidget3 = motionWidget2;
                float f6 = f5;
                this.mStartMotionPath.setView(f6, motionWidget3, this.mInterpolateVariables, this.mInterpolateData, this.mInterpolateVelocity, (double[]) null);
                MotionWidget motionWidget4 = motionWidget3;
                f4 = f6;
                motionWidget2 = motionWidget4;
            }
            if (this.mTransformPivotTarget != -1) {
                if (this.mTransformPivotView == null) {
                    this.mTransformPivotView = motionWidget2.getParent().findViewById(this.mTransformPivotTarget);
                }
                MotionWidget motionWidget5 = this.mTransformPivotView;
                if (motionWidget5 != null) {
                    float top = ((float) (motionWidget5.getTop() + this.mTransformPivotView.getBottom())) / 2.0f;
                    float left = ((float) (this.mTransformPivotView.getLeft() + this.mTransformPivotView.getRight())) / 2.0f;
                    if (motionWidget2.getRight() - motionWidget2.getLeft() > 0 && motionWidget2.getBottom() - motionWidget2.getTop() > 0) {
                        motionWidget2.setPivotX(left - ((float) motionWidget2.getLeft()));
                        motionWidget2.setPivotY(top - ((float) motionWidget2.getTop()));
                    }
                }
            }
            int i2 = 1;
            while (true) {
                CurveFit[] curveFitArr2 = this.mSpline;
                if (i2 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i2].getPos(d, this.mValuesBuff);
                this.mStartMotionPath.customAttributes.get(this.mAttributeNames[i2 - 1]).setInterpolatedValue(motionWidget2, this.mValuesBuff);
                i2++;
            }
            if (this.mStartPoint.mVisibilityMode == 0) {
                if (f4 <= 0.0f) {
                    motionWidget2.setVisibility(this.mStartPoint.visibility);
                } else if (f4 >= 1.0f) {
                    motionWidget2.setVisibility(this.mEndPoint.visibility);
                } else if (this.mEndPoint.visibility != this.mStartPoint.visibility) {
                    motionWidget2.setVisibility(4);
                }
            }
            if (this.mKeyTriggers != null) {
                int i3 = 0;
                while (true) {
                    MotionKeyTrigger[] motionKeyTriggerArr = this.mKeyTriggers;
                    if (i3 >= motionKeyTriggerArr.length) {
                        break;
                    }
                    motionKeyTriggerArr[i3].conditionallyFire(f4, motionWidget2);
                    i3++;
                }
            }
        } else {
            float f7 = this.mStartMotionPath.x + ((this.mEndMotionPath.x - this.mStartMotionPath.x) * f4) + 0.5f;
            float f8 = this.mStartMotionPath.y + ((this.mEndMotionPath.y - this.mStartMotionPath.y) * f4) + 0.5f;
            motionWidget2.layout((int) f7, (int) f8, (int) (f7 + this.mStartMotionPath.width + ((this.mEndMotionPath.width - this.mStartMotionPath.width) * f4)), (int) (f8 + this.mStartMotionPath.height + ((this.mEndMotionPath.height - this.mStartMotionPath.height) * f4)));
        }
        HashMap<String, KeyCycleOscillator> hashMap2 = this.mCycleMap;
        if (hashMap2 != null) {
            for (KeyCycleOscillator next : hashMap2.values()) {
                if (next instanceof KeyCycleOscillator.PathRotateSet) {
                    double[] dArr2 = this.mInterpolateVelocity;
                    ((KeyCycleOscillator.PathRotateSet) next).setPathRotate(motionWidget2, f4, dArr2[0], dArr2[1]);
                } else {
                    next.setProperty(motionWidget2, f4);
                }
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void getDpDt(float f, float f2, float f3, float[] fArr) {
        double[] dArr;
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        CurveFit[] curveFitArr = this.mSpline;
        int i = 0;
        if (curveFitArr != null) {
            double d = (double) adjustedPosition;
            curveFitArr[0].getSlope(d, this.mInterpolateVelocity);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                dArr = this.mInterpolateVelocity;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = dArr[i] * ((double) f4);
                i++;
            }
            CurveFit curveFit = this.mArcSpline;
            if (curveFit != null) {
                double[] dArr2 = this.mInterpolateData;
                if (dArr2.length > 0) {
                    curveFit.getPos(d, dArr2);
                    this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                    this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
                    return;
                }
                return;
            }
            this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, dArr, this.mInterpolateData);
            return;
        }
        float f5 = f2;
        float f6 = f3;
        float[] fArr2 = fArr;
        float f7 = this.mEndMotionPath.x - this.mStartMotionPath.x;
        float f8 = this.mEndMotionPath.y - this.mStartMotionPath.y;
        float f9 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
        fArr2[0] = (f7 * (1.0f - f5)) + (((this.mEndMotionPath.width - this.mStartMotionPath.width) + f7) * f5);
        fArr2[1] = (f8 * (1.0f - f6)) + (f9 * f6);
    }

    /* access modifiers changed from: package-private */
    public void getPostLayoutDvDp(float f, int i, int i2, float f2, float f3, float[] fArr) {
        float adjustedPosition = getAdjustedPosition(f, this.mVelocity);
        HashMap<String, SplineSet> hashMap = this.mAttributesMap;
        KeyCycleOscillator keyCycleOscillator = null;
        SplineSet splineSet = hashMap == null ? null : hashMap.get("translationX");
        HashMap<String, SplineSet> hashMap2 = this.mAttributesMap;
        SplineSet splineSet2 = hashMap2 == null ? null : hashMap2.get("translationY");
        HashMap<String, SplineSet> hashMap3 = this.mAttributesMap;
        SplineSet splineSet3 = hashMap3 == null ? null : hashMap3.get("rotationZ");
        HashMap<String, SplineSet> hashMap4 = this.mAttributesMap;
        SplineSet splineSet4 = hashMap4 == null ? null : hashMap4.get("scaleX");
        HashMap<String, SplineSet> hashMap5 = this.mAttributesMap;
        SplineSet splineSet5 = hashMap5 == null ? null : hashMap5.get("scaleY");
        HashMap<String, KeyCycleOscillator> hashMap6 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator2 = hashMap6 == null ? null : hashMap6.get("translationX");
        HashMap<String, KeyCycleOscillator> hashMap7 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator3 = hashMap7 == null ? null : hashMap7.get("translationY");
        HashMap<String, KeyCycleOscillator> hashMap8 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator4 = hashMap8 == null ? null : hashMap8.get("rotationZ");
        HashMap<String, KeyCycleOscillator> hashMap9 = this.mCycleMap;
        KeyCycleOscillator keyCycleOscillator5 = hashMap9 == null ? null : hashMap9.get("scaleX");
        HashMap<String, KeyCycleOscillator> hashMap10 = this.mCycleMap;
        if (hashMap10 != null) {
            keyCycleOscillator = hashMap10.get("scaleY");
        }
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.clear();
        velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
        velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
        velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
        velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
        velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
        velocityMatrix.setScaleVelocity(keyCycleOscillator5, keyCycleOscillator, adjustedPosition);
        CurveFit curveFit = this.mArcSpline;
        if (curveFit != null) {
            double[] dArr = this.mInterpolateData;
            if (dArr.length > 0) {
                double d = (double) adjustedPosition;
                curveFit.getPos(d, dArr);
                this.mArcSpline.getSlope(d, this.mInterpolateVelocity);
                this.mStartMotionPath.setDpDt(f2, f3, fArr, this.mInterpolateVariables, this.mInterpolateVelocity, this.mInterpolateData);
            }
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
            return;
        }
        int i3 = 0;
        if (this.mSpline != null) {
            double adjustedPosition2 = (double) getAdjustedPosition(adjustedPosition, this.mVelocity);
            this.mSpline[0].getSlope(adjustedPosition2, this.mInterpolateVelocity);
            this.mSpline[0].getPos(adjustedPosition2, this.mInterpolateData);
            float f4 = this.mVelocity[0];
            while (true) {
                double[] dArr2 = this.mInterpolateVelocity;
                if (i3 < dArr2.length) {
                    dArr2[i3] = dArr2[i3] * ((double) f4);
                    i3++;
                } else {
                    float f5 = f2;
                    float f6 = f3;
                    float[] fArr2 = fArr;
                    this.mStartMotionPath.setDpDt(f5, f6, fArr2, this.mInterpolateVariables, dArr2, this.mInterpolateData);
                    velocityMatrix.applyTransform(f5, f6, i, i2, fArr2);
                    return;
                }
            }
        } else {
            float f7 = this.mEndMotionPath.x - this.mStartMotionPath.x;
            float f8 = this.mEndMotionPath.y - this.mStartMotionPath.y;
            float f9 = f7;
            float f10 = (this.mEndMotionPath.height - this.mStartMotionPath.height) + f8;
            fArr[0] = (f9 * (1.0f - f2)) + ((f9 + (this.mEndMotionPath.width - this.mStartMotionPath.width)) * f2);
            fArr[1] = (f8 * (1.0f - f3)) + (f10 * f3);
            velocityMatrix.clear();
            velocityMatrix.setRotationVelocity(splineSet3, adjustedPosition);
            velocityMatrix.setTranslationVelocity(splineSet, splineSet2, adjustedPosition);
            velocityMatrix.setScaleVelocity(splineSet4, splineSet5, adjustedPosition);
            velocityMatrix.setRotationVelocity(keyCycleOscillator4, adjustedPosition);
            velocityMatrix.setTranslationVelocity(keyCycleOscillator2, keyCycleOscillator3, adjustedPosition);
            velocityMatrix.setScaleVelocity(keyCycleOscillator5, keyCycleOscillator, adjustedPosition);
            velocityMatrix.applyTransform(f2, f3, i, i2, fArr);
        }
    }

    public int getDrawPath() {
        int i = this.mStartMotionPath.mDrawPath;
        Iterator<MotionPaths> it = this.mMotionPaths.iterator();
        while (it.hasNext()) {
            i = Math.max(i, it.next().mDrawPath);
        }
        return Math.max(i, this.mEndMotionPath.mDrawPath);
    }

    public void setDrawPath(int i) {
        this.mStartMotionPath.mDrawPath = i;
    }

    /* access modifiers changed from: package-private */
    public String name() {
        return this.mView.getName();
    }

    /* access modifiers changed from: package-private */
    public void positionKeyframe(MotionWidget motionWidget, MotionKeyPosition motionKeyPosition, float f, float f2, String[] strArr, float[] fArr) {
        FloatRect floatRect = new FloatRect();
        floatRect.left = this.mStartMotionPath.x;
        floatRect.top = this.mStartMotionPath.y;
        floatRect.right = floatRect.left + this.mStartMotionPath.width;
        floatRect.bottom = floatRect.top + this.mStartMotionPath.height;
        FloatRect floatRect2 = new FloatRect();
        floatRect2.left = this.mEndMotionPath.x;
        floatRect2.top = this.mEndMotionPath.y;
        floatRect2.right = floatRect2.left + this.mEndMotionPath.width;
        floatRect2.bottom = floatRect2.top + this.mEndMotionPath.height;
        motionKeyPosition.positionAttributes(motionWidget, floatRect, floatRect2, f, f2, strArr, fArr);
    }

    public int getKeyFramePositions(int[] iArr, float[] fArr) {
        Iterator<MotionKey> it = this.mKeyList.iterator();
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
            iArr[i] = next.mFramePosition + (next.mType * 1000);
            double d = (double) (((float) next.mFramePosition) / 100.0f);
            this.mSpline[0].getPos(d, this.mInterpolateData);
            this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, i2);
            i2 += 2;
            i++;
        }
        return i;
    }

    public int getKeyFrameInfo(int i, int[] iArr) {
        float[] fArr = new float[2];
        Iterator<MotionKey> it = this.mKeyList.iterator();
        int i2 = 0;
        int i3 = 0;
        while (it.hasNext()) {
            MotionKey next = it.next();
            if (next.mType == i || i != -1) {
                iArr[i3] = 0;
                iArr[i3 + 1] = next.mType;
                iArr[i3 + 2] = next.mFramePosition;
                double d = (double) (((float) next.mFramePosition) / 100.0f);
                this.mSpline[0].getPos(d, this.mInterpolateData);
                this.mStartMotionPath.getCenter(d, this.mInterpolateVariables, this.mInterpolateData, fArr, 0);
                iArr[i3 + 3] = Float.floatToIntBits(fArr[0]);
                int i4 = i3 + 4;
                iArr[i4] = Float.floatToIntBits(fArr[1]);
                if (next instanceof MotionKeyPosition) {
                    MotionKeyPosition motionKeyPosition = (MotionKeyPosition) next;
                    iArr[i3 + 5] = motionKeyPosition.mPositionType;
                    iArr[i3 + 6] = Float.floatToIntBits(motionKeyPosition.mPercentX);
                    i4 = i3 + 7;
                    iArr[i4] = Float.floatToIntBits(motionKeyPosition.mPercentY);
                }
                int i5 = i4 + 1;
                iArr[i3] = i5 - i3;
                i2++;
                i3 = i5;
            }
        }
        return i2;
    }

    public boolean setValue(int i, int i2) {
        if (i != 509) {
            return i == 704;
        }
        setPathMotionArc(i2);
        return true;
    }

    public boolean setValue(int i, String str) {
        if (705 == i) {
            PrintStream printStream = System.out;
            printStream.println("TYPE_INTERPOLATOR  " + str);
            this.mQuantizeMotionInterpolator = getInterpolator(-1, str, 0);
        }
        return false;
    }
}
