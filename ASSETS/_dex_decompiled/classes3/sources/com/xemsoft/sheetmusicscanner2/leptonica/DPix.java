package com.xemsoft.sheetmusicscanner2.leptonica;

public class DPix {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public DPix(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(DPix dPix) {
        if (dPix == null) {
            return 0;
        }
        return dPix.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_DPix(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setW(int i) {
        JniLeptonicaJNI.DPix_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.DPix_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.DPix_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.DPix_h_get(this.swigCPtr, this);
    }

    public void setWpl(int i) {
        JniLeptonicaJNI.DPix_wpl_set(this.swigCPtr, this, i);
    }

    public int getWpl() {
        return JniLeptonicaJNI.DPix_wpl_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.DPix_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.DPix_refcount_get(this.swigCPtr, this);
    }

    public void setXres(int i) {
        JniLeptonicaJNI.DPix_xres_set(this.swigCPtr, this, i);
    }

    public int getXres() {
        return JniLeptonicaJNI.DPix_xres_get(this.swigCPtr, this);
    }

    public void setYres(int i) {
        JniLeptonicaJNI.DPix_yres_set(this.swigCPtr, this, i);
    }

    public int getYres() {
        return JniLeptonicaJNI.DPix_yres_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_double sWIGTYPE_p_double) {
        JniLeptonicaJNI.DPix_data_set(this.swigCPtr, this, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public SWIGTYPE_p_double getData() {
        long DPix_data_get = JniLeptonicaJNI.DPix_data_get(this.swigCPtr, this);
        if (DPix_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_double(DPix_data_get, false);
    }

    public DPix() {
        this(JniLeptonicaJNI.new_DPix(), true);
    }
}
