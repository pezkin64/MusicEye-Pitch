package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Bmf {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Bmf(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Bmf l_Bmf) {
        if (l_Bmf == null) {
            return 0;
        }
        return l_Bmf.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Bmf(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPixa(Pixa pixa) {
        JniLeptonicaJNI.L_Bmf_pixa_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixa() {
        long L_Bmf_pixa_get = JniLeptonicaJNI.L_Bmf_pixa_get(this.swigCPtr, this);
        if (L_Bmf_pixa_get == 0) {
            return null;
        }
        return new Pixa(L_Bmf_pixa_get, false);
    }

    public void setSize(int i) {
        JniLeptonicaJNI.L_Bmf_size_set(this.swigCPtr, this, i);
    }

    public int getSize() {
        return JniLeptonicaJNI.L_Bmf_size_get(this.swigCPtr, this);
    }

    public void setDirectory(String str) {
        JniLeptonicaJNI.L_Bmf_directory_set(this.swigCPtr, this, str);
    }

    public String getDirectory() {
        return JniLeptonicaJNI.L_Bmf_directory_get(this.swigCPtr, this);
    }

    public void setBaseline1(int i) {
        JniLeptonicaJNI.L_Bmf_baseline1_set(this.swigCPtr, this, i);
    }

    public int getBaseline1() {
        return JniLeptonicaJNI.L_Bmf_baseline1_get(this.swigCPtr, this);
    }

    public void setBaseline2(int i) {
        JniLeptonicaJNI.L_Bmf_baseline2_set(this.swigCPtr, this, i);
    }

    public int getBaseline2() {
        return JniLeptonicaJNI.L_Bmf_baseline2_get(this.swigCPtr, this);
    }

    public void setBaseline3(int i) {
        JniLeptonicaJNI.L_Bmf_baseline3_set(this.swigCPtr, this, i);
    }

    public int getBaseline3() {
        return JniLeptonicaJNI.L_Bmf_baseline3_get(this.swigCPtr, this);
    }

    public void setLineheight(int i) {
        JniLeptonicaJNI.L_Bmf_lineheight_set(this.swigCPtr, this, i);
    }

    public int getLineheight() {
        return JniLeptonicaJNI.L_Bmf_lineheight_get(this.swigCPtr, this);
    }

    public void setKernwidth(int i) {
        JniLeptonicaJNI.L_Bmf_kernwidth_set(this.swigCPtr, this, i);
    }

    public int getKernwidth() {
        return JniLeptonicaJNI.L_Bmf_kernwidth_get(this.swigCPtr, this);
    }

    public void setSpacewidth(int i) {
        JniLeptonicaJNI.L_Bmf_spacewidth_set(this.swigCPtr, this, i);
    }

    public int getSpacewidth() {
        return JniLeptonicaJNI.L_Bmf_spacewidth_get(this.swigCPtr, this);
    }

    public void setVertlinesep(int i) {
        JniLeptonicaJNI.L_Bmf_vertlinesep_set(this.swigCPtr, this, i);
    }

    public int getVertlinesep() {
        return JniLeptonicaJNI.L_Bmf_vertlinesep_get(this.swigCPtr, this);
    }

    public void setFonttab(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Bmf_fonttab_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getFonttab() {
        long L_Bmf_fonttab_get = JniLeptonicaJNI.L_Bmf_fonttab_get(this.swigCPtr, this);
        if (L_Bmf_fonttab_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Bmf_fonttab_get, false);
    }

    public void setBaselinetab(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Bmf_baselinetab_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getBaselinetab() {
        long L_Bmf_baselinetab_get = JniLeptonicaJNI.L_Bmf_baselinetab_get(this.swigCPtr, this);
        if (L_Bmf_baselinetab_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Bmf_baselinetab_get, false);
    }

    public void setWidthtab(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Bmf_widthtab_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getWidthtab() {
        long L_Bmf_widthtab_get = JniLeptonicaJNI.L_Bmf_widthtab_get(this.swigCPtr, this);
        if (L_Bmf_widthtab_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Bmf_widthtab_get, false);
    }

    public L_Bmf() {
        this(JniLeptonicaJNI.new_L_Bmf(), true);
    }
}
