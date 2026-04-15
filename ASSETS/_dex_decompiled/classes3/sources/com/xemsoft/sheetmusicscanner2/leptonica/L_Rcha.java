package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Rcha {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Rcha(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Rcha l_Rcha) {
        if (l_Rcha == null) {
            return 0;
        }
        return l_Rcha.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Rcha(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNaindex(Numa numa) {
        JniLeptonicaJNI.L_Rcha_naindex_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaindex() {
        long L_Rcha_naindex_get = JniLeptonicaJNI.L_Rcha_naindex_get(this.swigCPtr, this);
        if (L_Rcha_naindex_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_naindex_get, false);
    }

    public void setNascore(Numa numa) {
        JniLeptonicaJNI.L_Rcha_nascore_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNascore() {
        long L_Rcha_nascore_get = JniLeptonicaJNI.L_Rcha_nascore_get(this.swigCPtr, this);
        if (L_Rcha_nascore_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_nascore_get, false);
    }

    public void setSatext(Sarray sarray) {
        JniLeptonicaJNI.L_Rcha_satext_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getSatext() {
        long L_Rcha_satext_get = JniLeptonicaJNI.L_Rcha_satext_get(this.swigCPtr, this);
        if (L_Rcha_satext_get == 0) {
            return null;
        }
        return new Sarray(L_Rcha_satext_get, false);
    }

    public void setNasample(Numa numa) {
        JniLeptonicaJNI.L_Rcha_nasample_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNasample() {
        long L_Rcha_nasample_get = JniLeptonicaJNI.L_Rcha_nasample_get(this.swigCPtr, this);
        if (L_Rcha_nasample_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_nasample_get, false);
    }

    public void setNaxloc(Numa numa) {
        JniLeptonicaJNI.L_Rcha_naxloc_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaxloc() {
        long L_Rcha_naxloc_get = JniLeptonicaJNI.L_Rcha_naxloc_get(this.swigCPtr, this);
        if (L_Rcha_naxloc_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_naxloc_get, false);
    }

    public void setNayloc(Numa numa) {
        JniLeptonicaJNI.L_Rcha_nayloc_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNayloc() {
        long L_Rcha_nayloc_get = JniLeptonicaJNI.L_Rcha_nayloc_get(this.swigCPtr, this);
        if (L_Rcha_nayloc_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_nayloc_get, false);
    }

    public void setNawidth(Numa numa) {
        JniLeptonicaJNI.L_Rcha_nawidth_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNawidth() {
        long L_Rcha_nawidth_get = JniLeptonicaJNI.L_Rcha_nawidth_get(this.swigCPtr, this);
        if (L_Rcha_nawidth_get == 0) {
            return null;
        }
        return new Numa(L_Rcha_nawidth_get, false);
    }

    public L_Rcha() {
        this(JniLeptonicaJNI.new_L_Rcha(), true);
    }
}
