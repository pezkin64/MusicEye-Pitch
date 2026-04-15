package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Dna {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Dna(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Dna l_Dna) {
        if (l_Dna == null) {
            return 0;
        }
        return l_Dna.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Dna(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Dna_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Dna_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Dna_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Dna_n_get(this.swigCPtr, this);
    }

    public void setRefcount(int i) {
        JniLeptonicaJNI.L_Dna_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniLeptonicaJNI.L_Dna_refcount_get(this.swigCPtr, this);
    }

    public void setStartx(double d) {
        JniLeptonicaJNI.L_Dna_startx_set(this.swigCPtr, this, d);
    }

    public double getStartx() {
        return JniLeptonicaJNI.L_Dna_startx_get(this.swigCPtr, this);
    }

    public void setDelx(double d) {
        JniLeptonicaJNI.L_Dna_delx_set(this.swigCPtr, this, d);
    }

    public double getDelx() {
        return JniLeptonicaJNI.L_Dna_delx_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_double sWIGTYPE_p_double) {
        JniLeptonicaJNI.L_Dna_array_set(this.swigCPtr, this, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public SWIGTYPE_p_double getArray() {
        long L_Dna_array_get = JniLeptonicaJNI.L_Dna_array_get(this.swigCPtr, this);
        if (L_Dna_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_double(L_Dna_array_get, false);
    }

    public L_Dna() {
        this(JniLeptonicaJNI.new_L_Dna(), true);
    }
}
