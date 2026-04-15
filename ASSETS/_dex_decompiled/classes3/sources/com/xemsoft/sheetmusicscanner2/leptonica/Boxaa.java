package com.xemsoft.sheetmusicscanner2.leptonica;

public class Boxaa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Boxaa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Boxaa boxaa) {
        if (boxaa == null) {
            return 0;
        }
        return boxaa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Boxaa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Boxaa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Boxaa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Boxaa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Boxaa_nalloc_get(this.swigCPtr, this);
    }

    public void setBoxa(SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        JniLeptonicaJNI.Boxaa_boxa_set(this.swigCPtr, this, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
    }

    public SWIGTYPE_p_p_Boxa getBoxa() {
        long Boxaa_boxa_get = JniLeptonicaJNI.Boxaa_boxa_get(this.swigCPtr, this);
        if (Boxaa_boxa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Boxa(Boxaa_boxa_get, false);
    }

    public Boxaa() {
        this(JniLeptonicaJNI.new_Boxaa(), true);
    }
}
