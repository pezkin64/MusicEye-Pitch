package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Ptraa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Ptraa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Ptraa l_Ptraa) {
        if (l_Ptraa == null) {
            return 0;
        }
        return l_Ptraa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Ptraa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Ptraa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Ptraa_nalloc_get(this.swigCPtr, this);
    }

    public void setPtra(SWIGTYPE_p_p_L_Ptra sWIGTYPE_p_p_L_Ptra) {
        JniLeptonicaJNI.L_Ptraa_ptra_set(this.swigCPtr, this, SWIGTYPE_p_p_L_Ptra.getCPtr(sWIGTYPE_p_p_L_Ptra));
    }

    public SWIGTYPE_p_p_L_Ptra getPtra() {
        long L_Ptraa_ptra_get = JniLeptonicaJNI.L_Ptraa_ptra_get(this.swigCPtr, this);
        if (L_Ptraa_ptra_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_L_Ptra(L_Ptraa_ptra_get, false);
    }

    public L_Ptraa() {
        this(JniLeptonicaJNI.new_L_Ptraa(), true);
    }
}
