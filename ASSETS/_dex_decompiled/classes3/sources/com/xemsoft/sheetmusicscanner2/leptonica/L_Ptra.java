package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Ptra {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Ptra(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Ptra l_Ptra) {
        if (l_Ptra == null) {
            return 0;
        }
        return l_Ptra.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Ptra(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Ptra_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Ptra_nalloc_get(this.swigCPtr, this);
    }

    public void setImax(int i) {
        JniLeptonicaJNI.L_Ptra_imax_set(this.swigCPtr, this, i);
    }

    public int getImax() {
        return JniLeptonicaJNI.L_Ptra_imax_get(this.swigCPtr, this);
    }

    public void setNactual(int i) {
        JniLeptonicaJNI.L_Ptra_nactual_set(this.swigCPtr, this, i);
    }

    public int getNactual() {
        return JniLeptonicaJNI.L_Ptra_nactual_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_Ptra_array_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getArray() {
        long L_Ptra_array_get = JniLeptonicaJNI.L_Ptra_array_get(this.swigCPtr, this);
        if (L_Ptra_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_Ptra_array_get, false);
    }

    public L_Ptra() {
        this(JniLeptonicaJNI.new_L_Ptra(), true);
    }
}
