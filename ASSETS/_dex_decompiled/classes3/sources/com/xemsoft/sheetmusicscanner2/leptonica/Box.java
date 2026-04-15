package com.xemsoft.sheetmusicscanner2.leptonica;

public class Box {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Box(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Box box) {
        if (box == null) {
            return 0;
        }
        return box.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Box(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setX(int i) {
        JniLeptonicaJNI.Box_x_set(this.swigCPtr, this, i);
    }

    public int getX() {
        return JniLeptonicaJNI.Box_x_get(this.swigCPtr, this);
    }

    public void setY(int i) {
        JniLeptonicaJNI.Box_y_set(this.swigCPtr, this, i);
    }

    public int getY() {
        return JniLeptonicaJNI.Box_y_get(this.swigCPtr, this);
    }

    public void setW(int i) {
        JniLeptonicaJNI.Box_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.Box_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.Box_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.Box_h_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.Box_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.Box_refcount_get(this.swigCPtr, this);
    }

    public Box() {
        this(JniLeptonicaJNI.new_Box(), true);
    }
}
