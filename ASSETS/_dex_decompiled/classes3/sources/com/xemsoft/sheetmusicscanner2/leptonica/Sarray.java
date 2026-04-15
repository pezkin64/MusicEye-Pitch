package com.xemsoft.sheetmusicscanner2.leptonica;

public class Sarray {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Sarray(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Sarray sarray) {
        if (sarray == null) {
            return 0;
        }
        return sarray.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Sarray(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Sarray_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Sarray_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.Sarray_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Sarray_n_get(this.swigCPtr, this);
    }

    public void setRefcount(int i) {
        JniLeptonicaJNI.Sarray_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniLeptonicaJNI.Sarray_refcount_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        JniLeptonicaJNI.Sarray_array_set(this.swigCPtr, this, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public SWIGTYPE_p_p_char getArray() {
        long Sarray_array_get = JniLeptonicaJNI.Sarray_array_get(this.swigCPtr, this);
        if (Sarray_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Sarray_array_get, false);
    }

    public Sarray() {
        this(JniLeptonicaJNI.new_Sarray(), true);
    }
}
