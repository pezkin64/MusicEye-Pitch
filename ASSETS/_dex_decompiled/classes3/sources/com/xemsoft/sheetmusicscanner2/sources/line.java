package com.xemsoft.sheetmusicscanner2.sources;

public class line {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public line(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(line line) {
        if (line == null) {
            return 0;
        }
        return line.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_line(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setA(double d) {
        JniSourceJNI.line_a_set(this.swigCPtr, this, d);
    }

    public double getA() {
        return JniSourceJNI.line_a_get(this.swigCPtr, this);
    }

    public void setB(double d) {
        JniSourceJNI.line_b_set(this.swigCPtr, this, d);
    }

    public double getB() {
        return JniSourceJNI.line_b_get(this.swigCPtr, this);
    }

    public void setC(double d) {
        JniSourceJNI.line_c_set(this.swigCPtr, this, d);
    }

    public double getC() {
        return JniSourceJNI.line_c_get(this.swigCPtr, this);
    }

    public line() {
        this(JniSourceJNI.new_line(), true);
    }
}
