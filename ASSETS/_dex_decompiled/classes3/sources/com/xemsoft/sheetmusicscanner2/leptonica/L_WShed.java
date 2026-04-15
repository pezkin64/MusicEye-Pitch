package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_WShed {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_WShed(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_WShed l_WShed) {
        if (l_WShed == null) {
            return 0;
        }
        return l_WShed.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_WShed(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPixs(Pix pix) {
        JniLeptonicaJNI.L_WShed_pixs_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixs() {
        long L_WShed_pixs_get = JniLeptonicaJNI.L_WShed_pixs_get(this.swigCPtr, this);
        if (L_WShed_pixs_get == 0) {
            return null;
        }
        return new Pix(L_WShed_pixs_get, false);
    }

    public void setPixm(Pix pix) {
        JniLeptonicaJNI.L_WShed_pixm_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixm() {
        long L_WShed_pixm_get = JniLeptonicaJNI.L_WShed_pixm_get(this.swigCPtr, this);
        if (L_WShed_pixm_get == 0) {
            return null;
        }
        return new Pix(L_WShed_pixm_get, false);
    }

    public void setMindepth(int i) {
        JniLeptonicaJNI.L_WShed_mindepth_set(this.swigCPtr, this, i);
    }

    public int getMindepth() {
        return JniLeptonicaJNI.L_WShed_mindepth_get(this.swigCPtr, this);
    }

    public void setPixlab(Pix pix) {
        JniLeptonicaJNI.L_WShed_pixlab_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixlab() {
        long L_WShed_pixlab_get = JniLeptonicaJNI.L_WShed_pixlab_get(this.swigCPtr, this);
        if (L_WShed_pixlab_get == 0) {
            return null;
        }
        return new Pix(L_WShed_pixlab_get, false);
    }

    public void setPixt(Pix pix) {
        JniLeptonicaJNI.L_WShed_pixt_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixt() {
        long L_WShed_pixt_get = JniLeptonicaJNI.L_WShed_pixt_get(this.swigCPtr, this);
        if (L_WShed_pixt_get == 0) {
            return null;
        }
        return new Pix(L_WShed_pixt_get, false);
    }

    public void setLines8(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_WShed_lines8_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getLines8() {
        long L_WShed_lines8_get = JniLeptonicaJNI.L_WShed_lines8_get(this.swigCPtr, this);
        if (L_WShed_lines8_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_WShed_lines8_get, false);
    }

    public void setLinem1(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_WShed_linem1_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getLinem1() {
        long L_WShed_linem1_get = JniLeptonicaJNI.L_WShed_linem1_get(this.swigCPtr, this);
        if (L_WShed_linem1_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_WShed_linem1_get, false);
    }

    public void setLinelab32(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_WShed_linelab32_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getLinelab32() {
        long L_WShed_linelab32_get = JniLeptonicaJNI.L_WShed_linelab32_get(this.swigCPtr, this);
        if (L_WShed_linelab32_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_WShed_linelab32_get, false);
    }

    public void setLinet1(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_WShed_linet1_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getLinet1() {
        long L_WShed_linet1_get = JniLeptonicaJNI.L_WShed_linet1_get(this.swigCPtr, this);
        if (L_WShed_linet1_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_WShed_linet1_get, false);
    }

    public void setPixad(Pixa pixa) {
        JniLeptonicaJNI.L_WShed_pixad_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixad() {
        long L_WShed_pixad_get = JniLeptonicaJNI.L_WShed_pixad_get(this.swigCPtr, this);
        if (L_WShed_pixad_get == 0) {
            return null;
        }
        return new Pixa(L_WShed_pixad_get, false);
    }

    public void setPtas(Pta pta) {
        JniLeptonicaJNI.L_WShed_ptas_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtas() {
        long L_WShed_ptas_get = JniLeptonicaJNI.L_WShed_ptas_get(this.swigCPtr, this);
        if (L_WShed_ptas_get == 0) {
            return null;
        }
        return new Pta(L_WShed_ptas_get, false);
    }

    public void setNasi(Numa numa) {
        JniLeptonicaJNI.L_WShed_nasi_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNasi() {
        long L_WShed_nasi_get = JniLeptonicaJNI.L_WShed_nasi_get(this.swigCPtr, this);
        if (L_WShed_nasi_get == 0) {
            return null;
        }
        return new Numa(L_WShed_nasi_get, false);
    }

    public void setNash(Numa numa) {
        JniLeptonicaJNI.L_WShed_nash_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNash() {
        long L_WShed_nash_get = JniLeptonicaJNI.L_WShed_nash_get(this.swigCPtr, this);
        if (L_WShed_nash_get == 0) {
            return null;
        }
        return new Numa(L_WShed_nash_get, false);
    }

    public void setNamh(Numa numa) {
        JniLeptonicaJNI.L_WShed_namh_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNamh() {
        long L_WShed_namh_get = JniLeptonicaJNI.L_WShed_namh_get(this.swigCPtr, this);
        if (L_WShed_namh_get == 0) {
            return null;
        }
        return new Numa(L_WShed_namh_get, false);
    }

    public void setNalevels(Numa numa) {
        JniLeptonicaJNI.L_WShed_nalevels_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNalevels() {
        long L_WShed_nalevels_get = JniLeptonicaJNI.L_WShed_nalevels_get(this.swigCPtr, this);
        if (L_WShed_nalevels_get == 0) {
            return null;
        }
        return new Numa(L_WShed_nalevels_get, false);
    }

    public void setNseeds(int i) {
        JniLeptonicaJNI.L_WShed_nseeds_set(this.swigCPtr, this, i);
    }

    public int getNseeds() {
        return JniLeptonicaJNI.L_WShed_nseeds_get(this.swigCPtr, this);
    }

    public void setNother(int i) {
        JniLeptonicaJNI.L_WShed_nother_set(this.swigCPtr, this, i);
    }

    public int getNother() {
        return JniLeptonicaJNI.L_WShed_nother_get(this.swigCPtr, this);
    }

    public void setLut(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_WShed_lut_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getLut() {
        long L_WShed_lut_get = JniLeptonicaJNI.L_WShed_lut_get(this.swigCPtr, this);
        if (L_WShed_lut_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_WShed_lut_get, false);
    }

    public void setLinks(SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        JniLeptonicaJNI.L_WShed_links_set(this.swigCPtr, this, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public SWIGTYPE_p_p_Numa getLinks() {
        long L_WShed_links_get = JniLeptonicaJNI.L_WShed_links_get(this.swigCPtr, this);
        if (L_WShed_links_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Numa(L_WShed_links_get, false);
    }

    public void setArraysize(int i) {
        JniLeptonicaJNI.L_WShed_arraysize_set(this.swigCPtr, this, i);
    }

    public int getArraysize() {
        return JniLeptonicaJNI.L_WShed_arraysize_get(this.swigCPtr, this);
    }

    public void setDebug(int i) {
        JniLeptonicaJNI.L_WShed_debug_set(this.swigCPtr, this, i);
    }

    public int getDebug() {
        return JniLeptonicaJNI.L_WShed_debug_get(this.swigCPtr, this);
    }

    public L_WShed() {
        this(JniLeptonicaJNI.new_L_WShed(), true);
    }
}
