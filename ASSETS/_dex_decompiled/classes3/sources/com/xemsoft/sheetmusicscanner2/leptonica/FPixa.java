package com.xemsoft.sheetmusicscanner2.leptonica;

public class FPixa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FPixa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(FPixa fPixa) {
        if (fPixa == null) {
            return 0;
        }
        return fPixa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_FPixa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.FPixa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.FPixa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.FPixa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.FPixa_nalloc_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.FPixa_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.FPixa_refcount_get(this.swigCPtr, this);
    }

    public void setFpix(SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix) {
        JniLeptonicaJNI.FPixa_fpix_set(this.swigCPtr, this, SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix));
    }

    public SWIGTYPE_p_p_FPix getFpix() {
        long FPixa_fpix_get = JniLeptonicaJNI.FPixa_fpix_get(this.swigCPtr, this);
        if (FPixa_fpix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_FPix(FPixa_fpix_get, false);
    }

    public FPixa() {
        this(JniLeptonicaJNI.new_FPixa(), true);
    }
}
