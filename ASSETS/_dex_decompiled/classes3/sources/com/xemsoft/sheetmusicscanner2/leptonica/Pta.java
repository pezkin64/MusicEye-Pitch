package com.xemsoft.sheetmusicscanner2.leptonica;

public class Pta {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Pta(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Pta pta) {
        if (pta == null) {
            return 0;
        }
        return pta.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Pta(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Pta_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Pta_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Pta_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Pta_nalloc_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.Pta_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.Pta_refcount_get(this.swigCPtr, this);
    }

    public void setX(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.Pta_x_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getX() {
        long Pta_x_get = JniLeptonicaJNI.Pta_x_get(this.swigCPtr, this);
        if (Pta_x_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(Pta_x_get, false);
    }

    public void setY(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.Pta_y_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getY() {
        long Pta_y_get = JniLeptonicaJNI.Pta_y_get(this.swigCPtr, this);
        if (Pta_y_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(Pta_y_get, false);
    }

    public Pta() {
        this(JniLeptonicaJNI.new_Pta(), true);
    }
}
