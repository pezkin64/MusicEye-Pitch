package com.xemsoft.sheetmusicscanner2.leptonica;

public class PixComp {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PixComp(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PixComp pixComp) {
        if (pixComp == null) {
            return 0;
        }
        return pixComp.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_PixComp(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setW(int i) {
        JniLeptonicaJNI.PixComp_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.PixComp_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.PixComp_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.PixComp_h_get(this.swigCPtr, this);
    }

    public void setD(int i) {
        JniLeptonicaJNI.PixComp_d_set(this.swigCPtr, this, i);
    }

    public int getD() {
        return JniLeptonicaJNI.PixComp_d_get(this.swigCPtr, this);
    }

    public void setXres(int i) {
        JniLeptonicaJNI.PixComp_xres_set(this.swigCPtr, this, i);
    }

    public int getXres() {
        return JniLeptonicaJNI.PixComp_xres_get(this.swigCPtr, this);
    }

    public void setYres(int i) {
        JniLeptonicaJNI.PixComp_yres_set(this.swigCPtr, this, i);
    }

    public int getYres() {
        return JniLeptonicaJNI.PixComp_yres_get(this.swigCPtr, this);
    }

    public void setComptype(int i) {
        JniLeptonicaJNI.PixComp_comptype_set(this.swigCPtr, this, i);
    }

    public int getComptype() {
        return JniLeptonicaJNI.PixComp_comptype_get(this.swigCPtr, this);
    }

    public void setText(String str) {
        JniLeptonicaJNI.PixComp_text_set(this.swigCPtr, this, str);
    }

    public String getText() {
        return JniLeptonicaJNI.PixComp_text_get(this.swigCPtr, this);
    }

    public void setCmapflag(int i) {
        JniLeptonicaJNI.PixComp_cmapflag_set(this.swigCPtr, this, i);
    }

    public int getCmapflag() {
        return JniLeptonicaJNI.PixComp_cmapflag_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.PixComp_data_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public SWIGTYPE_p_unsigned_char getData() {
        long PixComp_data_get = JniLeptonicaJNI.PixComp_data_get(this.swigCPtr, this);
        if (PixComp_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(PixComp_data_get, false);
    }

    public void setSize(long j) {
        JniLeptonicaJNI.PixComp_size_set(this.swigCPtr, this, j);
    }

    public long getSize() {
        return JniLeptonicaJNI.PixComp_size_get(this.swigCPtr, this);
    }

    public PixComp() {
        this(JniLeptonicaJNI.new_PixComp(), true);
    }
}
