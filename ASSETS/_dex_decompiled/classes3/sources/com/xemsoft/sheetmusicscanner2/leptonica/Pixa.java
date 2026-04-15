package com.xemsoft.sheetmusicscanner2.leptonica;

public class Pixa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Pixa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Pixa pixa) {
        if (pixa == null) {
            return 0;
        }
        return pixa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Pixa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Pixa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Pixa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Pixa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Pixa_nalloc_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.Pixa_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.Pixa_refcount_get(this.swigCPtr, this);
    }

    public void setPix(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        JniLeptonicaJNI.Pixa_pix_set(this.swigCPtr, this, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public SWIGTYPE_p_p_Pix getPix() {
        long Pixa_pix_get = JniLeptonicaJNI.Pixa_pix_get(this.swigCPtr, this);
        if (Pixa_pix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Pix(Pixa_pix_get, false);
    }

    public void setBoxa(Boxa boxa) {
        JniLeptonicaJNI.Pixa_boxa_set(this.swigCPtr, this, Boxa.getCPtr(boxa), boxa);
    }

    public Boxa getBoxa() {
        long Pixa_boxa_get = JniLeptonicaJNI.Pixa_boxa_get(this.swigCPtr, this);
        if (Pixa_boxa_get == 0) {
            return null;
        }
        return new Boxa(Pixa_boxa_get, false);
    }

    public Pixa() {
        this(JniLeptonicaJNI.new_Pixa(), true);
    }
}
