package com.xemsoft.sheetmusicscanner2.leptonica;

public class Pixaa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Pixaa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Pixaa pixaa) {
        if (pixaa == null) {
            return 0;
        }
        return pixaa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Pixaa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setN(int i) {
        JniLeptonicaJNI.Pixaa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Pixaa_n_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Pixaa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Pixaa_nalloc_get(this.swigCPtr, this);
    }

    public void setPixa(SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        JniLeptonicaJNI.Pixaa_pixa_set(this.swigCPtr, this, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public SWIGTYPE_p_p_Pixa getPixa() {
        long Pixaa_pixa_get = JniLeptonicaJNI.Pixaa_pixa_get(this.swigCPtr, this);
        if (Pixaa_pixa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Pixa(Pixaa_pixa_get, false);
    }

    public void setBoxa(Boxa boxa) {
        JniLeptonicaJNI.Pixaa_boxa_set(this.swigCPtr, this, Boxa.getCPtr(boxa), boxa);
    }

    public Boxa getBoxa() {
        long Pixaa_boxa_get = JniLeptonicaJNI.Pixaa_boxa_get(this.swigCPtr, this);
        if (Pixaa_boxa_get == 0) {
            return null;
        }
        return new Boxa(Pixaa_boxa_get, false);
    }

    public Pixaa() {
        this(JniLeptonicaJNI.new_Pixaa(), true);
    }
}
