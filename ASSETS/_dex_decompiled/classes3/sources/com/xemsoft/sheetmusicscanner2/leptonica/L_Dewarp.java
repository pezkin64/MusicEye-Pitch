package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Dewarp {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Dewarp(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Dewarp l_Dewarp) {
        if (l_Dewarp == null) {
            return 0;
        }
        return l_Dewarp.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Dewarp(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDewa(L_Dewarpa l_Dewarpa) {
        JniLeptonicaJNI.L_Dewarp_dewa_set(this.swigCPtr, this, L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public L_Dewarpa getDewa() {
        long L_Dewarp_dewa_get = JniLeptonicaJNI.L_Dewarp_dewa_get(this.swigCPtr, this);
        if (L_Dewarp_dewa_get == 0) {
            return null;
        }
        return new L_Dewarpa(L_Dewarp_dewa_get, false);
    }

    public void setPixs(Pix pix) {
        JniLeptonicaJNI.L_Dewarp_pixs_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixs() {
        long L_Dewarp_pixs_get = JniLeptonicaJNI.L_Dewarp_pixs_get(this.swigCPtr, this);
        if (L_Dewarp_pixs_get == 0) {
            return null;
        }
        return new Pix(L_Dewarp_pixs_get, false);
    }

    public void setSampvdispar(FPix fPix) {
        JniLeptonicaJNI.L_Dewarp_sampvdispar_set(this.swigCPtr, this, FPix.getCPtr(fPix), fPix);
    }

    public FPix getSampvdispar() {
        long L_Dewarp_sampvdispar_get = JniLeptonicaJNI.L_Dewarp_sampvdispar_get(this.swigCPtr, this);
        if (L_Dewarp_sampvdispar_get == 0) {
            return null;
        }
        return new FPix(L_Dewarp_sampvdispar_get, false);
    }

    public void setSamphdispar(FPix fPix) {
        JniLeptonicaJNI.L_Dewarp_samphdispar_set(this.swigCPtr, this, FPix.getCPtr(fPix), fPix);
    }

    public FPix getSamphdispar() {
        long L_Dewarp_samphdispar_get = JniLeptonicaJNI.L_Dewarp_samphdispar_get(this.swigCPtr, this);
        if (L_Dewarp_samphdispar_get == 0) {
            return null;
        }
        return new FPix(L_Dewarp_samphdispar_get, false);
    }

    public void setFullvdispar(FPix fPix) {
        JniLeptonicaJNI.L_Dewarp_fullvdispar_set(this.swigCPtr, this, FPix.getCPtr(fPix), fPix);
    }

    public FPix getFullvdispar() {
        long L_Dewarp_fullvdispar_get = JniLeptonicaJNI.L_Dewarp_fullvdispar_get(this.swigCPtr, this);
        if (L_Dewarp_fullvdispar_get == 0) {
            return null;
        }
        return new FPix(L_Dewarp_fullvdispar_get, false);
    }

    public void setFullhdispar(FPix fPix) {
        JniLeptonicaJNI.L_Dewarp_fullhdispar_set(this.swigCPtr, this, FPix.getCPtr(fPix), fPix);
    }

    public FPix getFullhdispar() {
        long L_Dewarp_fullhdispar_get = JniLeptonicaJNI.L_Dewarp_fullhdispar_get(this.swigCPtr, this);
        if (L_Dewarp_fullhdispar_get == 0) {
            return null;
        }
        return new FPix(L_Dewarp_fullhdispar_get, false);
    }

    public void setNamidys(Numa numa) {
        JniLeptonicaJNI.L_Dewarp_namidys_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNamidys() {
        long L_Dewarp_namidys_get = JniLeptonicaJNI.L_Dewarp_namidys_get(this.swigCPtr, this);
        if (L_Dewarp_namidys_get == 0) {
            return null;
        }
        return new Numa(L_Dewarp_namidys_get, false);
    }

    public void setNacurves(Numa numa) {
        JniLeptonicaJNI.L_Dewarp_nacurves_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNacurves() {
        long L_Dewarp_nacurves_get = JniLeptonicaJNI.L_Dewarp_nacurves_get(this.swigCPtr, this);
        if (L_Dewarp_nacurves_get == 0) {
            return null;
        }
        return new Numa(L_Dewarp_nacurves_get, false);
    }

    public void setW(int i) {
        JniLeptonicaJNI.L_Dewarp_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.L_Dewarp_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.L_Dewarp_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.L_Dewarp_h_get(this.swigCPtr, this);
    }

    public void setPageno(int i) {
        JniLeptonicaJNI.L_Dewarp_pageno_set(this.swigCPtr, this, i);
    }

    public int getPageno() {
        return JniLeptonicaJNI.L_Dewarp_pageno_get(this.swigCPtr, this);
    }

    public void setSampling(int i) {
        JniLeptonicaJNI.L_Dewarp_sampling_set(this.swigCPtr, this, i);
    }

    public int getSampling() {
        return JniLeptonicaJNI.L_Dewarp_sampling_get(this.swigCPtr, this);
    }

    public void setRedfactor(int i) {
        JniLeptonicaJNI.L_Dewarp_redfactor_set(this.swigCPtr, this, i);
    }

    public int getRedfactor() {
        return JniLeptonicaJNI.L_Dewarp_redfactor_get(this.swigCPtr, this);
    }

    public void setMinlines(int i) {
        JniLeptonicaJNI.L_Dewarp_minlines_set(this.swigCPtr, this, i);
    }

    public int getMinlines() {
        return JniLeptonicaJNI.L_Dewarp_minlines_get(this.swigCPtr, this);
    }

    public void setNlines(int i) {
        JniLeptonicaJNI.L_Dewarp_nlines_set(this.swigCPtr, this, i);
    }

    public int getNlines() {
        return JniLeptonicaJNI.L_Dewarp_nlines_get(this.swigCPtr, this);
    }

    public void setMincurv(int i) {
        JniLeptonicaJNI.L_Dewarp_mincurv_set(this.swigCPtr, this, i);
    }

    public int getMincurv() {
        return JniLeptonicaJNI.L_Dewarp_mincurv_get(this.swigCPtr, this);
    }

    public void setMaxcurv(int i) {
        JniLeptonicaJNI.L_Dewarp_maxcurv_set(this.swigCPtr, this, i);
    }

    public int getMaxcurv() {
        return JniLeptonicaJNI.L_Dewarp_maxcurv_get(this.swigCPtr, this);
    }

    public void setLeftslope(int i) {
        JniLeptonicaJNI.L_Dewarp_leftslope_set(this.swigCPtr, this, i);
    }

    public int getLeftslope() {
        return JniLeptonicaJNI.L_Dewarp_leftslope_get(this.swigCPtr, this);
    }

    public void setRightslope(int i) {
        JniLeptonicaJNI.L_Dewarp_rightslope_set(this.swigCPtr, this, i);
    }

    public int getRightslope() {
        return JniLeptonicaJNI.L_Dewarp_rightslope_get(this.swigCPtr, this);
    }

    public void setLeftcurv(int i) {
        JniLeptonicaJNI.L_Dewarp_leftcurv_set(this.swigCPtr, this, i);
    }

    public int getLeftcurv() {
        return JniLeptonicaJNI.L_Dewarp_leftcurv_get(this.swigCPtr, this);
    }

    public void setRightcurv(int i) {
        JniLeptonicaJNI.L_Dewarp_rightcurv_set(this.swigCPtr, this, i);
    }

    public int getRightcurv() {
        return JniLeptonicaJNI.L_Dewarp_rightcurv_get(this.swigCPtr, this);
    }

    public void setNx(int i) {
        JniLeptonicaJNI.L_Dewarp_nx_set(this.swigCPtr, this, i);
    }

    public int getNx() {
        return JniLeptonicaJNI.L_Dewarp_nx_get(this.swigCPtr, this);
    }

    public void setNy(int i) {
        JniLeptonicaJNI.L_Dewarp_ny_set(this.swigCPtr, this, i);
    }

    public int getNy() {
        return JniLeptonicaJNI.L_Dewarp_ny_get(this.swigCPtr, this);
    }

    public void setHasref(int i) {
        JniLeptonicaJNI.L_Dewarp_hasref_set(this.swigCPtr, this, i);
    }

    public int getHasref() {
        return JniLeptonicaJNI.L_Dewarp_hasref_get(this.swigCPtr, this);
    }

    public void setRefpage(int i) {
        JniLeptonicaJNI.L_Dewarp_refpage_set(this.swigCPtr, this, i);
    }

    public int getRefpage() {
        return JniLeptonicaJNI.L_Dewarp_refpage_get(this.swigCPtr, this);
    }

    public void setVsuccess(int i) {
        JniLeptonicaJNI.L_Dewarp_vsuccess_set(this.swigCPtr, this, i);
    }

    public int getVsuccess() {
        return JniLeptonicaJNI.L_Dewarp_vsuccess_get(this.swigCPtr, this);
    }

    public void setHsuccess(int i) {
        JniLeptonicaJNI.L_Dewarp_hsuccess_set(this.swigCPtr, this, i);
    }

    public int getHsuccess() {
        return JniLeptonicaJNI.L_Dewarp_hsuccess_get(this.swigCPtr, this);
    }

    public void setVvalid(int i) {
        JniLeptonicaJNI.L_Dewarp_vvalid_set(this.swigCPtr, this, i);
    }

    public int getVvalid() {
        return JniLeptonicaJNI.L_Dewarp_vvalid_get(this.swigCPtr, this);
    }

    public void setHvalid(int i) {
        JniLeptonicaJNI.L_Dewarp_hvalid_set(this.swigCPtr, this, i);
    }

    public int getHvalid() {
        return JniLeptonicaJNI.L_Dewarp_hvalid_get(this.swigCPtr, this);
    }

    public void setDebug(int i) {
        JniLeptonicaJNI.L_Dewarp_debug_set(this.swigCPtr, this, i);
    }

    public int getDebug() {
        return JniLeptonicaJNI.L_Dewarp_debug_get(this.swigCPtr, this);
    }

    public L_Dewarp() {
        this(JniLeptonicaJNI.new_L_Dewarp(), true);
    }
}
