package com.xemsoft.sheetmusicscanner2.leptonica;

public class CCBord {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public CCBord(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(CCBord cCBord) {
        if (cCBord == null) {
            return 0;
        }
        return cCBord.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_CCBord(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(Pix pix) {
        JniLeptonicaJNI.CCBord_pix_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPix() {
        long CCBord_pix_get = JniLeptonicaJNI.CCBord_pix_get(this.swigCPtr, this);
        if (CCBord_pix_get == 0) {
            return null;
        }
        return new Pix(CCBord_pix_get, false);
    }

    public void setBoxa(Boxa boxa) {
        JniLeptonicaJNI.CCBord_boxa_set(this.swigCPtr, this, Boxa.getCPtr(boxa), boxa);
    }

    public Boxa getBoxa() {
        long CCBord_boxa_get = JniLeptonicaJNI.CCBord_boxa_get(this.swigCPtr, this);
        if (CCBord_boxa_get == 0) {
            return null;
        }
        return new Boxa(CCBord_boxa_get, false);
    }

    public void setStart(Pta pta) {
        JniLeptonicaJNI.CCBord_start_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getStart() {
        long CCBord_start_get = JniLeptonicaJNI.CCBord_start_get(this.swigCPtr, this);
        if (CCBord_start_get == 0) {
            return null;
        }
        return new Pta(CCBord_start_get, false);
    }

    public void setRefcount(int i) {
        JniLeptonicaJNI.CCBord_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniLeptonicaJNI.CCBord_refcount_get(this.swigCPtr, this);
    }

    public void setLocal(Ptaa ptaa) {
        JniLeptonicaJNI.CCBord_local_set(this.swigCPtr, this, Ptaa.getCPtr(ptaa), ptaa);
    }

    public Ptaa getLocal() {
        long CCBord_local_get = JniLeptonicaJNI.CCBord_local_get(this.swigCPtr, this);
        if (CCBord_local_get == 0) {
            return null;
        }
        return new Ptaa(CCBord_local_get, false);
    }

    public void setGlobal(Ptaa ptaa) {
        JniLeptonicaJNI.CCBord_global_set(this.swigCPtr, this, Ptaa.getCPtr(ptaa), ptaa);
    }

    public Ptaa getGlobal() {
        long CCBord_global_get = JniLeptonicaJNI.CCBord_global_get(this.swigCPtr, this);
        if (CCBord_global_get == 0) {
            return null;
        }
        return new Ptaa(CCBord_global_get, false);
    }

    public void setStep(Numaa numaa) {
        JniLeptonicaJNI.CCBord_step_set(this.swigCPtr, this, Numaa.getCPtr(numaa), numaa);
    }

    public Numaa getStep() {
        long CCBord_step_get = JniLeptonicaJNI.CCBord_step_get(this.swigCPtr, this);
        if (CCBord_step_get == 0) {
            return null;
        }
        return new Numaa(CCBord_step_get, false);
    }

    public void setSplocal(Pta pta) {
        JniLeptonicaJNI.CCBord_splocal_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getSplocal() {
        long CCBord_splocal_get = JniLeptonicaJNI.CCBord_splocal_get(this.swigCPtr, this);
        if (CCBord_splocal_get == 0) {
            return null;
        }
        return new Pta(CCBord_splocal_get, false);
    }

    public void setSpglobal(Pta pta) {
        JniLeptonicaJNI.CCBord_spglobal_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getSpglobal() {
        long CCBord_spglobal_get = JniLeptonicaJNI.CCBord_spglobal_get(this.swigCPtr, this);
        if (CCBord_spglobal_get == 0) {
            return null;
        }
        return new Pta(CCBord_spglobal_get, false);
    }

    public CCBord() {
        this(JniLeptonicaJNI.new_CCBord(), true);
    }
}
