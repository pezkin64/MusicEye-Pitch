package com.xemsoft.sheetmusicscanner2.leptonica;

public class PixaComp {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PixaComp(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PixaComp pixaComp) {
        if (pixaComp == null) {
            return 0;
        }
        return pixaComp.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_PixaComp(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.PixaComp_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.PixaComp_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.PixaComp_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.PixaComp_nalloc_get(this.swigCPtr, this);
    }

    public void setOffset(int i) {
        JniLeptonicaJNI.PixaComp_offset_set(this.swigCPtr, this, i);
    }

    public int getOffset() {
        return JniLeptonicaJNI.PixaComp_offset_get(this.swigCPtr, this);
    }

    public void setPixc(SWIGTYPE_p_p_PixComp sWIGTYPE_p_p_PixComp) {
        JniLeptonicaJNI.PixaComp_pixc_set(this.swigCPtr, this, SWIGTYPE_p_p_PixComp.getCPtr(sWIGTYPE_p_p_PixComp));
    }

    public SWIGTYPE_p_p_PixComp getPixc() {
        long PixaComp_pixc_get = JniLeptonicaJNI.PixaComp_pixc_get(this.swigCPtr, this);
        if (PixaComp_pixc_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_PixComp(PixaComp_pixc_get, false);
    }

    public void setBoxa(Boxa boxa) {
        JniLeptonicaJNI.PixaComp_boxa_set(this.swigCPtr, this, Boxa.getCPtr(boxa), boxa);
    }

    public Boxa getBoxa() {
        long PixaComp_boxa_get = JniLeptonicaJNI.PixaComp_boxa_get(this.swigCPtr, this);
        if (PixaComp_boxa_get == 0) {
            return null;
        }
        return new Boxa(PixaComp_boxa_get, false);
    }

    public PixaComp() {
        this(JniLeptonicaJNI.new_PixaComp(), true);
    }
}
