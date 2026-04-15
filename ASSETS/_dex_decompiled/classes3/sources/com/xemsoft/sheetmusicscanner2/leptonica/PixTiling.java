package com.xemsoft.sheetmusicscanner2.leptonica;

public class PixTiling {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PixTiling(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PixTiling pixTiling) {
        if (pixTiling == null) {
            return 0;
        }
        return pixTiling.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_PixTiling(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(Pix pix) {
        JniLeptonicaJNI.PixTiling_pix_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPix() {
        long PixTiling_pix_get = JniLeptonicaJNI.PixTiling_pix_get(this.swigCPtr, this);
        if (PixTiling_pix_get == 0) {
            return null;
        }
        return new Pix(PixTiling_pix_get, false);
    }

    public void setNx(int i) {
        JniLeptonicaJNI.PixTiling_nx_set(this.swigCPtr, this, i);
    }

    public int getNx() {
        return JniLeptonicaJNI.PixTiling_nx_get(this.swigCPtr, this);
    }

    public void setNy(int i) {
        JniLeptonicaJNI.PixTiling_ny_set(this.swigCPtr, this, i);
    }

    public int getNy() {
        return JniLeptonicaJNI.PixTiling_ny_get(this.swigCPtr, this);
    }

    public void setW(int i) {
        JniLeptonicaJNI.PixTiling_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.PixTiling_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.PixTiling_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.PixTiling_h_get(this.swigCPtr, this);
    }

    public void setXoverlap(int i) {
        JniLeptonicaJNI.PixTiling_xoverlap_set(this.swigCPtr, this, i);
    }

    public int getXoverlap() {
        return JniLeptonicaJNI.PixTiling_xoverlap_get(this.swigCPtr, this);
    }

    public void setYoverlap(int i) {
        JniLeptonicaJNI.PixTiling_yoverlap_set(this.swigCPtr, this, i);
    }

    public int getYoverlap() {
        return JniLeptonicaJNI.PixTiling_yoverlap_get(this.swigCPtr, this);
    }

    public void setStrip(int i) {
        JniLeptonicaJNI.PixTiling_strip_set(this.swigCPtr, this, i);
    }

    public int getStrip() {
        return JniLeptonicaJNI.PixTiling_strip_get(this.swigCPtr, this);
    }

    public PixTiling() {
        this(JniLeptonicaJNI.new_PixTiling(), true);
    }
}
