package com.xemsoft.sheetmusicscanner2.leptonica;

public class Sela {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Sela(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Sela sela) {
        if (sela == null) {
            return 0;
        }
        return sela.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Sela(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Sela_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Sela_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Sela_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Sela_nalloc_get(this.swigCPtr, this);
    }

    public void setSel(SWIGTYPE_p_p_Sel sWIGTYPE_p_p_Sel) {
        JniLeptonicaJNI.Sela_sel_set(this.swigCPtr, this, SWIGTYPE_p_p_Sel.getCPtr(sWIGTYPE_p_p_Sel));
    }

    public SWIGTYPE_p_p_Sel getSel() {
        long Sela_sel_get = JniLeptonicaJNI.Sela_sel_get(this.swigCPtr, this);
        if (Sela_sel_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Sel(Sela_sel_get, false);
    }

    public Sela() {
        this(JniLeptonicaJNI.new_Sela(), true);
    }
}
