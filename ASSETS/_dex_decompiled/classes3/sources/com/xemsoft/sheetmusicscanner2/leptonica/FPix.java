package com.xemsoft.sheetmusicscanner2.leptonica;

public class FPix {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FPix(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(FPix fPix) {
        if (fPix == null) {
            return 0;
        }
        return fPix.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_FPix(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setW(int i) {
        JniLeptonicaJNI.FPix_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.FPix_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.FPix_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.FPix_h_get(this.swigCPtr, this);
    }

    public void setWpl(int i) {
        JniLeptonicaJNI.FPix_wpl_set(this.swigCPtr, this, i);
    }

    public int getWpl() {
        return JniLeptonicaJNI.FPix_wpl_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.FPix_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.FPix_refcount_get(this.swigCPtr, this);
    }

    public void setXres(int i) {
        JniLeptonicaJNI.FPix_xres_set(this.swigCPtr, this, i);
    }

    public int getXres() {
        return JniLeptonicaJNI.FPix_xres_get(this.swigCPtr, this);
    }

    public void setYres(int i) {
        JniLeptonicaJNI.FPix_yres_set(this.swigCPtr, this, i);
    }

    public int getYres() {
        return JniLeptonicaJNI.FPix_yres_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.FPix_data_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getData() {
        long FPix_data_get = JniLeptonicaJNI.FPix_data_get(this.swigCPtr, this);
        if (FPix_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(FPix_data_get, false);
    }

    public FPix() {
        this(JniLeptonicaJNI.new_FPix(), true);
    }
}
