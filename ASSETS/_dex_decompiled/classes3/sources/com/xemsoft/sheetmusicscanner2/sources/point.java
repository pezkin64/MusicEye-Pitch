package com.xemsoft.sheetmusicscanner2.sources;

public class point {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public point(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(point point) {
        if (point == null) {
            return 0;
        }
        return point.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_point(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setX(int i) {
        JniSourceJNI.point_x_set(this.swigCPtr, this, i);
    }

    public int getX() {
        return JniSourceJNI.point_x_get(this.swigCPtr, this);
    }

    public void setY(int i) {
        JniSourceJNI.point_y_set(this.swigCPtr, this, i);
    }

    public int getY() {
        return JniSourceJNI.point_y_get(this.swigCPtr, this);
    }

    public point() {
        this(JniSourceJNI.new_point(), true);
    }
}
