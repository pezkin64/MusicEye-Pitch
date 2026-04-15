package com.xemsoft.sheetmusicscanner2.sources;

public class vanishingPoint {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public vanishingPoint(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(vanishingPoint vanishingpoint) {
        if (vanishingpoint == null) {
            return 0;
        }
        return vanishingpoint.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_vanishingPoint(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setX(int i) {
        JniSourceJNI.vanishingPoint_x_set(this.swigCPtr, this, i);
    }

    public int getX() {
        return JniSourceJNI.vanishingPoint_x_get(this.swigCPtr, this);
    }

    public void setY(int i) {
        JniSourceJNI.vanishingPoint_y_set(this.swigCPtr, this, i);
    }

    public int getY() {
        return JniSourceJNI.vanishingPoint_y_get(this.swigCPtr, this);
    }

    public void setIsInfinite(int i) {
        JniSourceJNI.vanishingPoint_isInfinite_set(this.swigCPtr, this, i);
    }

    public int getIsInfinite() {
        return JniSourceJNI.vanishingPoint_isInfinite_get(this.swigCPtr, this);
    }

    public void setAngleIfInfinite(SWIGTYPE_p_long_double sWIGTYPE_p_long_double) {
        JniSourceJNI.vanishingPoint_angleIfInfinite_set(this.swigCPtr, this, SWIGTYPE_p_long_double.getCPtr(sWIGTYPE_p_long_double));
    }

    public SWIGTYPE_p_long_double getAngleIfInfinite() {
        return new SWIGTYPE_p_long_double(JniSourceJNI.vanishingPoint_angleIfInfinite_get(this.swigCPtr, this), true);
    }

    public void setIsSlopeVerticalIfInfinite(int i) {
        JniSourceJNI.vanishingPoint_isSlopeVerticalIfInfinite_set(this.swigCPtr, this, i);
    }

    public int getIsSlopeVerticalIfInfinite() {
        return JniSourceJNI.vanishingPoint_isSlopeVerticalIfInfinite_get(this.swigCPtr, this);
    }

    public vanishingPoint() {
        this(JniSourceJNI.new_vanishingPoint(), true);
    }
}
