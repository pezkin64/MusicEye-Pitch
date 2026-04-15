package com.xemsoft.sheetmusicscanner2.leptonica;

public class Pix {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Pix(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Pix pix) {
        if (pix == null) {
            return 0;
        }
        return pix.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Pix(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setW(long j) {
        JniLeptonicaJNI.Pix_w_set(this.swigCPtr, this, j);
    }

    public long getW() {
        return JniLeptonicaJNI.Pix_w_get(this.swigCPtr, this);
    }

    public void setH(long j) {
        JniLeptonicaJNI.Pix_h_set(this.swigCPtr, this, j);
    }

    public long getH() {
        return JniLeptonicaJNI.Pix_h_get(this.swigCPtr, this);
    }

    public void setD(long j) {
        JniLeptonicaJNI.Pix_d_set(this.swigCPtr, this, j);
    }

    public long getD() {
        return JniLeptonicaJNI.Pix_d_get(this.swigCPtr, this);
    }

    public void setSpp(long j) {
        JniLeptonicaJNI.Pix_spp_set(this.swigCPtr, this, j);
    }

    public long getSpp() {
        return JniLeptonicaJNI.Pix_spp_get(this.swigCPtr, this);
    }

    public void setWpl(long j) {
        JniLeptonicaJNI.Pix_wpl_set(this.swigCPtr, this, j);
    }

    public long getWpl() {
        return JniLeptonicaJNI.Pix_wpl_get(this.swigCPtr, this);
    }

    public void setRefcount(long j) {
        JniLeptonicaJNI.Pix_refcount_set(this.swigCPtr, this, j);
    }

    public long getRefcount() {
        return JniLeptonicaJNI.Pix_refcount_get(this.swigCPtr, this);
    }

    public void setXres(int i) {
        JniLeptonicaJNI.Pix_xres_set(this.swigCPtr, this, i);
    }

    public int getXres() {
        return JniLeptonicaJNI.Pix_xres_get(this.swigCPtr, this);
    }

    public void setYres(int i) {
        JniLeptonicaJNI.Pix_yres_set(this.swigCPtr, this, i);
    }

    public int getYres() {
        return JniLeptonicaJNI.Pix_yres_get(this.swigCPtr, this);
    }

    public void setInformat(int i) {
        JniLeptonicaJNI.Pix_informat_set(this.swigCPtr, this, i);
    }

    public int getInformat() {
        return JniLeptonicaJNI.Pix_informat_get(this.swigCPtr, this);
    }

    public void setSpecial(int i) {
        JniLeptonicaJNI.Pix_special_set(this.swigCPtr, this, i);
    }

    public int getSpecial() {
        return JniLeptonicaJNI.Pix_special_get(this.swigCPtr, this);
    }

    public void setText(String str) {
        JniLeptonicaJNI.Pix_text_set(this.swigCPtr, this, str);
    }

    public String getText() {
        return JniLeptonicaJNI.Pix_text_get(this.swigCPtr, this);
    }

    public void setColormap(PixColormap pixColormap) {
        JniLeptonicaJNI.Pix_colormap_set(this.swigCPtr, this, PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public PixColormap getColormap() {
        long Pix_colormap_get = JniLeptonicaJNI.Pix_colormap_get(this.swigCPtr, this);
        if (Pix_colormap_get == 0) {
            return null;
        }
        return new PixColormap(Pix_colormap_get, false);
    }

    public void setData(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        JniLeptonicaJNI.Pix_data_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public SWIGTYPE_p_unsigned_int getData() {
        long Pix_data_get = JniLeptonicaJNI.Pix_data_get(this.swigCPtr, this);
        if (Pix_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(Pix_data_get, false);
    }

    public Pix() {
        this(JniLeptonicaJNI.new_Pix(), true);
    }
}
