package com.xemsoft.sheetmusicscanner2.leptonica;

public class Ptaa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Ptaa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Ptaa ptaa) {
        if (ptaa == null) {
            return 0;
        }
        return ptaa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Ptaa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Ptaa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Ptaa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Ptaa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Ptaa_nalloc_get(this.swigCPtr, this);
    }

    public void setPta(SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta) {
        JniLeptonicaJNI.Ptaa_pta_set(this.swigCPtr, this, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta));
    }

    public SWIGTYPE_p_p_Pta getPta() {
        long Ptaa_pta_get = JniLeptonicaJNI.Ptaa_pta_get(this.swigCPtr, this);
        if (Ptaa_pta_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Pta(Ptaa_pta_get, false);
    }

    public Ptaa() {
        this(JniLeptonicaJNI.new_Ptaa(), true);
    }
}
