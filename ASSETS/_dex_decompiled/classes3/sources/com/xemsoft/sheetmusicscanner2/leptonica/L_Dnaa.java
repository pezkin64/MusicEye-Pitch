package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Dnaa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Dnaa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Dnaa l_Dnaa) {
        if (l_Dnaa == null) {
            return 0;
        }
        return l_Dnaa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Dnaa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Dnaa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Dnaa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Dnaa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Dnaa_n_get(this.swigCPtr, this);
    }

    public void setDna(SWIGTYPE_p_p_L_Dna sWIGTYPE_p_p_L_Dna) {
        JniLeptonicaJNI.L_Dnaa_dna_set(this.swigCPtr, this, SWIGTYPE_p_p_L_Dna.getCPtr(sWIGTYPE_p_p_L_Dna));
    }

    public SWIGTYPE_p_p_L_Dna getDna() {
        long L_Dnaa_dna_get = JniLeptonicaJNI.L_Dnaa_dna_get(this.swigCPtr, this);
        if (L_Dnaa_dna_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_L_Dna(L_Dnaa_dna_get, false);
    }

    public L_Dnaa() {
        this(JniLeptonicaJNI.new_L_Dnaa(), true);
    }
}
