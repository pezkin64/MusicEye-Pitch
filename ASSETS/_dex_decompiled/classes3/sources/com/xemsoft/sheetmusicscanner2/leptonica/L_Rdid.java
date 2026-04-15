package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Rdid {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Rdid(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Rdid l_Rdid) {
        if (l_Rdid == null) {
            return 0;
        }
        return l_Rdid.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Rdid(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPixs(Pix pix) {
        JniLeptonicaJNI.L_Rdid_pixs_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixs() {
        long L_Rdid_pixs_get = JniLeptonicaJNI.L_Rdid_pixs_get(this.swigCPtr, this);
        if (L_Rdid_pixs_get == 0) {
            return null;
        }
        return new Pix(L_Rdid_pixs_get, false);
    }

    public void setCounta(SWIGTYPE_p_p_int sWIGTYPE_p_p_int) {
        JniLeptonicaJNI.L_Rdid_counta_set(this.swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int));
    }

    public SWIGTYPE_p_p_int getCounta() {
        long L_Rdid_counta_get = JniLeptonicaJNI.L_Rdid_counta_get(this.swigCPtr, this);
        if (L_Rdid_counta_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_int(L_Rdid_counta_get, false);
    }

    public void setDelya(SWIGTYPE_p_p_int sWIGTYPE_p_p_int) {
        JniLeptonicaJNI.L_Rdid_delya_set(this.swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int));
    }

    public SWIGTYPE_p_p_int getDelya() {
        long L_Rdid_delya_get = JniLeptonicaJNI.L_Rdid_delya_get(this.swigCPtr, this);
        if (L_Rdid_delya_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_int(L_Rdid_delya_get, false);
    }

    public void setNarray(int i) {
        JniLeptonicaJNI.L_Rdid_narray_set(this.swigCPtr, this, i);
    }

    public int getNarray() {
        return JniLeptonicaJNI.L_Rdid_narray_get(this.swigCPtr, this);
    }

    public void setSize(int i) {
        JniLeptonicaJNI.L_Rdid_size_set(this.swigCPtr, this, i);
    }

    public int getSize() {
        return JniLeptonicaJNI.L_Rdid_size_get(this.swigCPtr, this);
    }

    public void setSetwidth(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Rdid_setwidth_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getSetwidth() {
        long L_Rdid_setwidth_get = JniLeptonicaJNI.L_Rdid_setwidth_get(this.swigCPtr, this);
        if (L_Rdid_setwidth_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Rdid_setwidth_get, false);
    }

    public void setNasum(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nasum_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNasum() {
        long L_Rdid_nasum_get = JniLeptonicaJNI.L_Rdid_nasum_get(this.swigCPtr, this);
        if (L_Rdid_nasum_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nasum_get, false);
    }

    public void setNamoment(Numa numa) {
        JniLeptonicaJNI.L_Rdid_namoment_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNamoment() {
        long L_Rdid_namoment_get = JniLeptonicaJNI.L_Rdid_namoment_get(this.swigCPtr, this);
        if (L_Rdid_namoment_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_namoment_get, false);
    }

    public void setFullarrays(int i) {
        JniLeptonicaJNI.L_Rdid_fullarrays_set(this.swigCPtr, this, i);
    }

    public int getFullarrays() {
        return JniLeptonicaJNI.L_Rdid_fullarrays_get(this.swigCPtr, this);
    }

    public void setBeta(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Rdid_beta_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getBeta() {
        long L_Rdid_beta_get = JniLeptonicaJNI.L_Rdid_beta_get(this.swigCPtr, this);
        if (L_Rdid_beta_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Rdid_beta_get, false);
    }

    public void setGamma(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Rdid_gamma_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getGamma() {
        long L_Rdid_gamma_get = JniLeptonicaJNI.L_Rdid_gamma_get(this.swigCPtr, this);
        if (L_Rdid_gamma_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Rdid_gamma_get, false);
    }

    public void setTrellisscore(SWIGTYPE_p_float sWIGTYPE_p_float) {
        JniLeptonicaJNI.L_Rdid_trellisscore_set(this.swigCPtr, this, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public SWIGTYPE_p_float getTrellisscore() {
        long L_Rdid_trellisscore_get = JniLeptonicaJNI.L_Rdid_trellisscore_get(this.swigCPtr, this);
        if (L_Rdid_trellisscore_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(L_Rdid_trellisscore_get, false);
    }

    public void setTrellistempl(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Rdid_trellistempl_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getTrellistempl() {
        long L_Rdid_trellistempl_get = JniLeptonicaJNI.L_Rdid_trellistempl_get(this.swigCPtr, this);
        if (L_Rdid_trellistempl_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Rdid_trellistempl_get, false);
    }

    public void setNatempl(Numa numa) {
        JniLeptonicaJNI.L_Rdid_natempl_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNatempl() {
        long L_Rdid_natempl_get = JniLeptonicaJNI.L_Rdid_natempl_get(this.swigCPtr, this);
        if (L_Rdid_natempl_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_natempl_get, false);
    }

    public void setNaxloc(Numa numa) {
        JniLeptonicaJNI.L_Rdid_naxloc_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaxloc() {
        long L_Rdid_naxloc_get = JniLeptonicaJNI.L_Rdid_naxloc_get(this.swigCPtr, this);
        if (L_Rdid_naxloc_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_naxloc_get, false);
    }

    public void setNadely(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nadely_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNadely() {
        long L_Rdid_nadely_get = JniLeptonicaJNI.L_Rdid_nadely_get(this.swigCPtr, this);
        if (L_Rdid_nadely_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nadely_get, false);
    }

    public void setNawidth(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nawidth_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNawidth() {
        long L_Rdid_nawidth_get = JniLeptonicaJNI.L_Rdid_nawidth_get(this.swigCPtr, this);
        if (L_Rdid_nawidth_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nawidth_get, false);
    }

    public void setNascore(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nascore_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNascore() {
        long L_Rdid_nascore_get = JniLeptonicaJNI.L_Rdid_nascore_get(this.swigCPtr, this);
        if (L_Rdid_nascore_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nascore_get, false);
    }

    public void setNatempl_r(Numa numa) {
        JniLeptonicaJNI.L_Rdid_natempl_r_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNatempl_r() {
        long L_Rdid_natempl_r_get = JniLeptonicaJNI.L_Rdid_natempl_r_get(this.swigCPtr, this);
        if (L_Rdid_natempl_r_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_natempl_r_get, false);
    }

    public void setNaxloc_r(Numa numa) {
        JniLeptonicaJNI.L_Rdid_naxloc_r_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaxloc_r() {
        long L_Rdid_naxloc_r_get = JniLeptonicaJNI.L_Rdid_naxloc_r_get(this.swigCPtr, this);
        if (L_Rdid_naxloc_r_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_naxloc_r_get, false);
    }

    public void setNadely_r(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nadely_r_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNadely_r() {
        long L_Rdid_nadely_r_get = JniLeptonicaJNI.L_Rdid_nadely_r_get(this.swigCPtr, this);
        if (L_Rdid_nadely_r_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nadely_r_get, false);
    }

    public void setNawidth_r(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nawidth_r_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNawidth_r() {
        long L_Rdid_nawidth_r_get = JniLeptonicaJNI.L_Rdid_nawidth_r_get(this.swigCPtr, this);
        if (L_Rdid_nawidth_r_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nawidth_r_get, false);
    }

    public void setNascore_r(Numa numa) {
        JniLeptonicaJNI.L_Rdid_nascore_r_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNascore_r() {
        long L_Rdid_nascore_r_get = JniLeptonicaJNI.L_Rdid_nascore_r_get(this.swigCPtr, this);
        if (L_Rdid_nascore_r_get == 0) {
            return null;
        }
        return new Numa(L_Rdid_nascore_r_get, false);
    }

    public L_Rdid() {
        this(JniLeptonicaJNI.new_L_Rdid(), true);
    }
}
