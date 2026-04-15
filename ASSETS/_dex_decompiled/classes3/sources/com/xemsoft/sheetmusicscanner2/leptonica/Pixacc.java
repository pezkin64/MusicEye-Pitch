package com.xemsoft.sheetmusicscanner2.leptonica;

public class Pixacc {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Pixacc(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Pixacc pixacc) {
        if (pixacc == null) {
            return 0;
        }
        return pixacc.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Pixacc(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setW(int i) {
        JniLeptonicaJNI.Pixacc_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.Pixacc_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.Pixacc_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.Pixacc_h_get(this.swigCPtr, this);
    }

    public void setOffset(int i) {
        JniLeptonicaJNI.Pixacc_offset_set(this.swigCPtr, this, i);
    }

    public int getOffset() {
        return JniLeptonicaJNI.Pixacc_offset_get(this.swigCPtr, this);
    }

    public void setPix(Pix pix) {
        JniLeptonicaJNI.Pixacc_pix_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPix() {
        long Pixacc_pix_get = JniLeptonicaJNI.Pixacc_pix_get(this.swigCPtr, this);
        if (Pixacc_pix_get == 0) {
            return null;
        }
        return new Pix(Pixacc_pix_get, false);
    }

    public Pixacc() {
        this(JniLeptonicaJNI.new_Pixacc(), true);
    }
}
