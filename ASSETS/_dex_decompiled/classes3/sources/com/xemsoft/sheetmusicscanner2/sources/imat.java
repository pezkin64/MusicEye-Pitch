package com.xemsoft.sheetmusicscanner2.sources;

public class imat {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public imat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(imat imat) {
        if (imat == null) {
            return 0;
        }
        return imat.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_imat(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setWidth(int i) {
        JniSourceJNI.imat_width_set(this.swigCPtr, this, i);
    }

    public int getWidth() {
        return JniSourceJNI.imat_width_get(this.swigCPtr, this);
    }

    public void setHeight(int i) {
        JniSourceJNI.imat_height_set(this.swigCPtr, this, i);
    }

    public int getHeight() {
        return JniSourceJNI.imat_height_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniSourceJNI.imat_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.imat_nalloc_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniSourceJNI.imat_data_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getData() {
        long imat_data_get = JniSourceJNI.imat_data_get(this.swigCPtr, this);
        if (imat_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(imat_data_get, false);
    }

    public imat() {
        this(JniSourceJNI.new_imat(), true);
    }
}
