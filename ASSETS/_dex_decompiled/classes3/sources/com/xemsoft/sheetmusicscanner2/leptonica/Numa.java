package com.xemsoft.sheetmusicscanner2.leptonica;

public class Numa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Numa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Numa numa) {
        if (numa == null) {
            return 0;
        }
        return numa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Numa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Numa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Numa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.Numa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Numa_n_get(this.swigCPtr, this);
    }

    public void setRefcount(int i) {
        JniLeptonicaJNI.Numa_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniLeptonicaJNI.Numa_refcount_get(this.swigCPtr, this);
    }

    public void setStartx(float f) {
        JniLeptonicaJNI.Numa_startx_set(this.swigCPtr, this, f);
    }

    public float getStartx() {
        return JniLeptonicaJNI.Numa_startx_get(this.swigCPtr, this);
    }

    public void setDelx(float f) {
        JniLeptonicaJNI.Numa_delx_set(this.swigCPtr, this, f);
    }

    public float getDelx() {
        return JniLeptonicaJNI.Numa_delx_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.Numa_array_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getArray() {
        long Numa_array_get = JniLeptonicaJNI.Numa_array_get(this.swigCPtr, this);
        if (Numa_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(Numa_array_get, false);
    }

    public Numa() {
        this(JniLeptonicaJNI.new_Numa(), true);
    }
}
