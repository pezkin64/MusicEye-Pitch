package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Recoga {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Recoga(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Recoga l_Recoga) {
        if (l_Recoga == null) {
            return 0;
        }
        return l_Recoga.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Recoga(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Recoga_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Recoga_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Recoga_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Recoga_nalloc_get(this.swigCPtr, this);
    }

    public void setRecog(SWIGTYPE_p_p_L_Recog sWIGTYPE_p_p_L_Recog) {
        JniLeptonicaJNI.L_Recoga_recog_set(this.swigCPtr, this, SWIGTYPE_p_p_L_Recog.getCPtr(sWIGTYPE_p_p_L_Recog));
    }

    public SWIGTYPE_p_p_L_Recog getRecog() {
        long L_Recoga_recog_get = JniLeptonicaJNI.L_Recoga_recog_get(this.swigCPtr, this);
        if (L_Recoga_recog_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_L_Recog(L_Recoga_recog_get, false);
    }

    public void setRcha(L_Rcha l_Rcha) {
        JniLeptonicaJNI.L_Recoga_rcha_set(this.swigCPtr, this, L_Rcha.getCPtr(l_Rcha), l_Rcha);
    }

    public L_Rcha getRcha() {
        long L_Recoga_rcha_get = JniLeptonicaJNI.L_Recoga_rcha_get(this.swigCPtr, this);
        if (L_Recoga_rcha_get == 0) {
            return null;
        }
        return new L_Rcha(L_Recoga_rcha_get, false);
    }

    public L_Recoga() {
        this(JniLeptonicaJNI.new_L_Recoga(), true);
    }
}
