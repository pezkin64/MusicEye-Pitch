package com.xemsoft.sheetmusicscanner2.leptonica;

public class CCBorda {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public CCBorda(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(CCBorda cCBorda) {
        if (cCBorda == null) {
            return 0;
        }
        return cCBorda.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_CCBorda(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(Pix pix) {
        JniLeptonicaJNI.CCBorda_pix_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPix() {
        long CCBorda_pix_get = JniLeptonicaJNI.CCBorda_pix_get(this.swigCPtr, this);
        if (CCBorda_pix_get == 0) {
            return null;
        }
        return new Pix(CCBorda_pix_get, false);
    }

    public void setW(int i) {
        JniLeptonicaJNI.CCBorda_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.CCBorda_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.CCBorda_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.CCBorda_h_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.CCBorda_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.CCBorda_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.CCBorda_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.CCBorda_nalloc_get(this.swigCPtr, this);
    }

    public void setCcb(SWIGTYPE_p_p_CCBord sWIGTYPE_p_p_CCBord) {
        JniLeptonicaJNI.CCBorda_ccb_set(this.swigCPtr, this, SWIGTYPE_p_p_CCBord.getCPtr(sWIGTYPE_p_p_CCBord));
    }

    public SWIGTYPE_p_p_CCBord getCcb() {
        long CCBorda_ccb_get = JniLeptonicaJNI.CCBorda_ccb_get(this.swigCPtr, this);
        if (CCBorda_ccb_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_CCBord(CCBorda_ccb_get, false);
    }

    public CCBorda() {
        this(JniLeptonicaJNI.new_CCBorda(), true);
    }
}
