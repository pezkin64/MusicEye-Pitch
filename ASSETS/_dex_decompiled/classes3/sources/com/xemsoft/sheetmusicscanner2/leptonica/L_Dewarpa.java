package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Dewarpa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Dewarpa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Dewarpa l_Dewarpa) {
        if (l_Dewarpa == null) {
            return 0;
        }
        return l_Dewarpa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Dewarpa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Dewarpa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Dewarpa_nalloc_get(this.swigCPtr, this);
    }

    public void setMaxpage(int i) {
        JniLeptonicaJNI.L_Dewarpa_maxpage_set(this.swigCPtr, this, i);
    }

    public int getMaxpage() {
        return JniLeptonicaJNI.L_Dewarpa_maxpage_get(this.swigCPtr, this);
    }

    public void setDewarp(SWIGTYPE_p_p_L_Dewarp sWIGTYPE_p_p_L_Dewarp) {
        JniLeptonicaJNI.L_Dewarpa_dewarp_set(this.swigCPtr, this, SWIGTYPE_p_p_L_Dewarp.getCPtr(sWIGTYPE_p_p_L_Dewarp));
    }

    public SWIGTYPE_p_p_L_Dewarp getDewarp() {
        long L_Dewarpa_dewarp_get = JniLeptonicaJNI.L_Dewarpa_dewarp_get(this.swigCPtr, this);
        if (L_Dewarpa_dewarp_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_L_Dewarp(L_Dewarpa_dewarp_get, false);
    }

    public void setDewarpcache(SWIGTYPE_p_p_L_Dewarp sWIGTYPE_p_p_L_Dewarp) {
        JniLeptonicaJNI.L_Dewarpa_dewarpcache_set(this.swigCPtr, this, SWIGTYPE_p_p_L_Dewarp.getCPtr(sWIGTYPE_p_p_L_Dewarp));
    }

    public SWIGTYPE_p_p_L_Dewarp getDewarpcache() {
        long L_Dewarpa_dewarpcache_get = JniLeptonicaJNI.L_Dewarpa_dewarpcache_get(this.swigCPtr, this);
        if (L_Dewarpa_dewarpcache_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_L_Dewarp(L_Dewarpa_dewarpcache_get, false);
    }

    public void setNamodels(Numa numa) {
        JniLeptonicaJNI.L_Dewarpa_namodels_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNamodels() {
        long L_Dewarpa_namodels_get = JniLeptonicaJNI.L_Dewarpa_namodels_get(this.swigCPtr, this);
        if (L_Dewarpa_namodels_get == 0) {
            return null;
        }
        return new Numa(L_Dewarpa_namodels_get, false);
    }

    public void setNapages(Numa numa) {
        JniLeptonicaJNI.L_Dewarpa_napages_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNapages() {
        long L_Dewarpa_napages_get = JniLeptonicaJNI.L_Dewarpa_napages_get(this.swigCPtr, this);
        if (L_Dewarpa_napages_get == 0) {
            return null;
        }
        return new Numa(L_Dewarpa_napages_get, false);
    }

    public void setRedfactor(int i) {
        JniLeptonicaJNI.L_Dewarpa_redfactor_set(this.swigCPtr, this, i);
    }

    public int getRedfactor() {
        return JniLeptonicaJNI.L_Dewarpa_redfactor_get(this.swigCPtr, this);
    }

    public void setSampling(int i) {
        JniLeptonicaJNI.L_Dewarpa_sampling_set(this.swigCPtr, this, i);
    }

    public int getSampling() {
        return JniLeptonicaJNI.L_Dewarpa_sampling_get(this.swigCPtr, this);
    }

    public void setMinlines(int i) {
        JniLeptonicaJNI.L_Dewarpa_minlines_set(this.swigCPtr, this, i);
    }

    public int getMinlines() {
        return JniLeptonicaJNI.L_Dewarpa_minlines_get(this.swigCPtr, this);
    }

    public void setMaxdist(int i) {
        JniLeptonicaJNI.L_Dewarpa_maxdist_set(this.swigCPtr, this, i);
    }

    public int getMaxdist() {
        return JniLeptonicaJNI.L_Dewarpa_maxdist_get(this.swigCPtr, this);
    }

    public void setMax_linecurv(int i) {
        JniLeptonicaJNI.L_Dewarpa_max_linecurv_set(this.swigCPtr, this, i);
    }

    public int getMax_linecurv() {
        return JniLeptonicaJNI.L_Dewarpa_max_linecurv_get(this.swigCPtr, this);
    }

    public void setMin_diff_linecurv(int i) {
        JniLeptonicaJNI.L_Dewarpa_min_diff_linecurv_set(this.swigCPtr, this, i);
    }

    public int getMin_diff_linecurv() {
        return JniLeptonicaJNI.L_Dewarpa_min_diff_linecurv_get(this.swigCPtr, this);
    }

    public void setMax_diff_linecurv(int i) {
        JniLeptonicaJNI.L_Dewarpa_max_diff_linecurv_set(this.swigCPtr, this, i);
    }

    public int getMax_diff_linecurv() {
        return JniLeptonicaJNI.L_Dewarpa_max_diff_linecurv_get(this.swigCPtr, this);
    }

    public void setMax_edgeslope(int i) {
        JniLeptonicaJNI.L_Dewarpa_max_edgeslope_set(this.swigCPtr, this, i);
    }

    public int getMax_edgeslope() {
        return JniLeptonicaJNI.L_Dewarpa_max_edgeslope_get(this.swigCPtr, this);
    }

    public void setMax_edgecurv(int i) {
        JniLeptonicaJNI.L_Dewarpa_max_edgecurv_set(this.swigCPtr, this, i);
    }

    public int getMax_edgecurv() {
        return JniLeptonicaJNI.L_Dewarpa_max_edgecurv_get(this.swigCPtr, this);
    }

    public void setMax_diff_edgecurv(int i) {
        JniLeptonicaJNI.L_Dewarpa_max_diff_edgecurv_set(this.swigCPtr, this, i);
    }

    public int getMax_diff_edgecurv() {
        return JniLeptonicaJNI.L_Dewarpa_max_diff_edgecurv_get(this.swigCPtr, this);
    }

    public void setUseboth(int i) {
        JniLeptonicaJNI.L_Dewarpa_useboth_set(this.swigCPtr, this, i);
    }

    public int getUseboth() {
        return JniLeptonicaJNI.L_Dewarpa_useboth_get(this.swigCPtr, this);
    }

    public void setModelsready(int i) {
        JniLeptonicaJNI.L_Dewarpa_modelsready_set(this.swigCPtr, this, i);
    }

    public int getModelsready() {
        return JniLeptonicaJNI.L_Dewarpa_modelsready_get(this.swigCPtr, this);
    }

    public L_Dewarpa() {
        this(JniLeptonicaJNI.new_L_Dewarpa(), true);
    }
}
