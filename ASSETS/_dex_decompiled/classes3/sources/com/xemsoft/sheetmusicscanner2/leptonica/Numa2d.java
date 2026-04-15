package com.xemsoft.sheetmusicscanner2.leptonica;

public class Numa2d {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Numa2d(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Numa2d numa2d) {
        if (numa2d == null) {
            return 0;
        }
        return numa2d.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Numa2d(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNrows(int i) {
        JniLeptonicaJNI.Numa2d_nrows_set(this.swigCPtr, this, i);
    }

    public int getNrows() {
        return JniLeptonicaJNI.Numa2d_nrows_get(this.swigCPtr, this);
    }

    public void setNcols(int i) {
        JniLeptonicaJNI.Numa2d_ncols_set(this.swigCPtr, this, i);
    }

    public int getNcols() {
        return JniLeptonicaJNI.Numa2d_ncols_get(this.swigCPtr, this);
    }

    public void setInitsize(int i) {
        JniLeptonicaJNI.Numa2d_initsize_set(this.swigCPtr, this, i);
    }

    public int getInitsize() {
        return JniLeptonicaJNI.Numa2d_initsize_get(this.swigCPtr, this);
    }

    public void setNuma(SWIGTYPE_p_p_p_Numa sWIGTYPE_p_p_p_Numa) {
        JniLeptonicaJNI.Numa2d_numa_set(this.swigCPtr, this, SWIGTYPE_p_p_p_Numa.getCPtr(sWIGTYPE_p_p_p_Numa));
    }

    public SWIGTYPE_p_p_p_Numa getNuma() {
        long Numa2d_numa_get = JniLeptonicaJNI.Numa2d_numa_get(this.swigCPtr, this);
        if (Numa2d_numa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_p_Numa(Numa2d_numa_get, false);
    }

    public Numa2d() {
        this(JniLeptonicaJNI.new_Numa2d(), true);
    }
}
