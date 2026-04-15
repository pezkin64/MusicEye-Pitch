package com.xemsoft.sheetmusicscanner2.leptonica;

public class Boxa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Boxa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Boxa boxa) {
        if (boxa == null) {
            return 0;
        }
        return boxa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Boxa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Boxa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Boxa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Boxa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Boxa_nalloc_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.Boxa_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.Boxa_refcount_get(this.swigCPtr, this);
    }

    public void setBox(SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        JniLeptonicaJNI.Boxa_box_set(this.swigCPtr, this, SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public SWIGTYPE_p_p_Box getBox() {
        long Boxa_box_get = JniLeptonicaJNI.Boxa_box_get(this.swigCPtr, this);
        if (Boxa_box_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Box(Boxa_box_get, false);
    }

    public Boxa() {
        this(JniLeptonicaJNI.new_Boxa(), true);
    }
}
