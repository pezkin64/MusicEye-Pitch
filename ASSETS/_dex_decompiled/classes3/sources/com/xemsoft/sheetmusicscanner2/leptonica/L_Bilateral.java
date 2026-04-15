package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Bilateral {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Bilateral(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Bilateral l_Bilateral) {
        if (l_Bilateral == null) {
            return 0;
        }
        return l_Bilateral.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Bilateral(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPixs(Pix pix) {
        JniLeptonicaJNI.L_Bilateral_pixs_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixs() {
        long L_Bilateral_pixs_get = JniLeptonicaJNI.L_Bilateral_pixs_get(this.swigCPtr, this);
        if (L_Bilateral_pixs_get == 0) {
            return null;
        }
        return new Pix(L_Bilateral_pixs_get, false);
    }

    public void setPixsc(Pix pix) {
        JniLeptonicaJNI.L_Bilateral_pixsc_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixsc() {
        long L_Bilateral_pixsc_get = JniLeptonicaJNI.L_Bilateral_pixsc_get(this.swigCPtr, this);
        if (L_Bilateral_pixsc_get == 0) {
            return null;
        }
        return new Pix(L_Bilateral_pixsc_get, false);
    }

    public void setReduction(int i) {
        JniLeptonicaJNI.L_Bilateral_reduction_set(this.swigCPtr, this, i);
    }

    public int getReduction() {
        return JniLeptonicaJNI.L_Bilateral_reduction_get(this.swigCPtr, this);
    }

    public void setSpatial_stdev(float f) {
        JniLeptonicaJNI.L_Bilateral_spatial_stdev_set(this.swigCPtr, this, f);
    }

    public float getSpatial_stdev() {
        return JniLeptonicaJNI.L_Bilateral_spatial_stdev_get(this.swigCPtr, this);
    }

    public void setRange_stdev(float f) {
        JniLeptonicaJNI.L_Bilateral_range_stdev_set(this.swigCPtr, this, f);
    }

    public float getRange_stdev() {
        return JniLeptonicaJNI.L_Bilateral_range_stdev_get(this.swigCPtr, this);
    }

    public void setSpatial(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Bilateral_spatial_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getSpatial() {
        long L_Bilateral_spatial_get = JniLeptonicaJNI.L_Bilateral_spatial_get(this.swigCPtr, this);
        if (L_Bilateral_spatial_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Bilateral_spatial_get, false);
    }

    public void setRange(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Bilateral_range_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getRange() {
        long L_Bilateral_range_get = JniLeptonicaJNI.L_Bilateral_range_get(this.swigCPtr, this);
        if (L_Bilateral_range_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Bilateral_range_get, false);
    }

    public void setMinval(int i) {
        JniLeptonicaJNI.L_Bilateral_minval_set(this.swigCPtr, this, i);
    }

    public int getMinval() {
        return JniLeptonicaJNI.L_Bilateral_minval_get(this.swigCPtr, this);
    }

    public void setMaxval(int i) {
        JniLeptonicaJNI.L_Bilateral_maxval_set(this.swigCPtr, this, i);
    }

    public int getMaxval() {
        return JniLeptonicaJNI.L_Bilateral_maxval_get(this.swigCPtr, this);
    }

    public void setNcomps(int i) {
        JniLeptonicaJNI.L_Bilateral_ncomps_set(this.swigCPtr, this, i);
    }

    public int getNcomps() {
        return JniLeptonicaJNI.L_Bilateral_ncomps_get(this.swigCPtr, this);
    }

    public void setNc(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Bilateral_nc_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getNc() {
        long L_Bilateral_nc_get = JniLeptonicaJNI.L_Bilateral_nc_get(this.swigCPtr, this);
        if (L_Bilateral_nc_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Bilateral_nc_get, false);
    }

    public void setKindex(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Bilateral_kindex_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getKindex() {
        long L_Bilateral_kindex_get = JniLeptonicaJNI.L_Bilateral_kindex_get(this.swigCPtr, this);
        if (L_Bilateral_kindex_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Bilateral_kindex_get, false);
    }

    public void setKfract(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Bilateral_kfract_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getKfract() {
        long L_Bilateral_kfract_get = JniLeptonicaJNI.L_Bilateral_kfract_get(this.swigCPtr, this);
        if (L_Bilateral_kfract_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Bilateral_kfract_get, false);
    }

    public void setPixac(Pixa pixa) {
        JniLeptonicaJNI.L_Bilateral_pixac_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixac() {
        long L_Bilateral_pixac_get = JniLeptonicaJNI.L_Bilateral_pixac_get(this.swigCPtr, this);
        if (L_Bilateral_pixac_get == 0) {
            return null;
        }
        return new Pixa(L_Bilateral_pixac_get, false);
    }

    public void setLineset(SWIGTYPE_p_p_p_unsigned_int sWIGTYPE_p_p_p_unsigned_int) {
        JniLeptonicaJNI.L_Bilateral_lineset_set(this.swigCPtr, this, SWIGTYPE_p_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_p_unsigned_int));
    }

    public SWIGTYPE_p_p_p_unsigned_int getLineset() {
        long L_Bilateral_lineset_get = JniLeptonicaJNI.L_Bilateral_lineset_get(this.swigCPtr, this);
        if (L_Bilateral_lineset_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_p_unsigned_int(L_Bilateral_lineset_get, false);
    }

    public L_Bilateral() {
        this(JniLeptonicaJNI.new_L_Bilateral(), true);
    }
}
