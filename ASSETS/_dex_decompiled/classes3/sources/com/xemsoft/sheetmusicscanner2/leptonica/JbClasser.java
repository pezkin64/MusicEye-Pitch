package com.xemsoft.sheetmusicscanner2.leptonica;

public class JbClasser {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public JbClasser(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(JbClasser jbClasser) {
        if (jbClasser == null) {
            return 0;
        }
        return jbClasser.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_JbClasser(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSafiles(Sarray sarray) {
        JniLeptonicaJNI.JbClasser_safiles_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getSafiles() {
        long JbClasser_safiles_get = JniLeptonicaJNI.JbClasser_safiles_get(this.swigCPtr, this);
        if (JbClasser_safiles_get == 0) {
            return null;
        }
        return new Sarray(JbClasser_safiles_get, false);
    }

    public void setMethod(int i) {
        JniLeptonicaJNI.JbClasser_method_set(this.swigCPtr, this, i);
    }

    public int getMethod() {
        return JniLeptonicaJNI.JbClasser_method_get(this.swigCPtr, this);
    }

    public void setComponents(int i) {
        JniLeptonicaJNI.JbClasser_components_set(this.swigCPtr, this, i);
    }

    public int getComponents() {
        return JniLeptonicaJNI.JbClasser_components_get(this.swigCPtr, this);
    }

    public void setMaxwidth(int i) {
        JniLeptonicaJNI.JbClasser_maxwidth_set(this.swigCPtr, this, i);
    }

    public int getMaxwidth() {
        return JniLeptonicaJNI.JbClasser_maxwidth_get(this.swigCPtr, this);
    }

    public void setMaxheight(int i) {
        JniLeptonicaJNI.JbClasser_maxheight_set(this.swigCPtr, this, i);
    }

    public int getMaxheight() {
        return JniLeptonicaJNI.JbClasser_maxheight_get(this.swigCPtr, this);
    }

    public void setNpages(int i) {
        JniLeptonicaJNI.JbClasser_npages_set(this.swigCPtr, this, i);
    }

    public int getNpages() {
        return JniLeptonicaJNI.JbClasser_npages_get(this.swigCPtr, this);
    }

    public void setBaseindex(int i) {
        JniLeptonicaJNI.JbClasser_baseindex_set(this.swigCPtr, this, i);
    }

    public int getBaseindex() {
        return JniLeptonicaJNI.JbClasser_baseindex_get(this.swigCPtr, this);
    }

    public void setNacomps(Numa numa) {
        JniLeptonicaJNI.JbClasser_nacomps_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNacomps() {
        long JbClasser_nacomps_get = JniLeptonicaJNI.JbClasser_nacomps_get(this.swigCPtr, this);
        if (JbClasser_nacomps_get == 0) {
            return null;
        }
        return new Numa(JbClasser_nacomps_get, false);
    }

    public void setSizehaus(int i) {
        JniLeptonicaJNI.JbClasser_sizehaus_set(this.swigCPtr, this, i);
    }

    public int getSizehaus() {
        return JniLeptonicaJNI.JbClasser_sizehaus_get(this.swigCPtr, this);
    }

    public void setRankhaus(float f) {
        JniLeptonicaJNI.JbClasser_rankhaus_set(this.swigCPtr, this, f);
    }

    public float getRankhaus() {
        return JniLeptonicaJNI.JbClasser_rankhaus_get(this.swigCPtr, this);
    }

    public void setThresh(float f) {
        JniLeptonicaJNI.JbClasser_thresh_set(this.swigCPtr, this, f);
    }

    public float getThresh() {
        return JniLeptonicaJNI.JbClasser_thresh_get(this.swigCPtr, this);
    }

    public void setWeightfactor(float f) {
        JniLeptonicaJNI.JbClasser_weightfactor_set(this.swigCPtr, this, f);
    }

    public float getWeightfactor() {
        return JniLeptonicaJNI.JbClasser_weightfactor_get(this.swigCPtr, this);
    }

    public void setNaarea(Numa numa) {
        JniLeptonicaJNI.JbClasser_naarea_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaarea() {
        long JbClasser_naarea_get = JniLeptonicaJNI.JbClasser_naarea_get(this.swigCPtr, this);
        if (JbClasser_naarea_get == 0) {
            return null;
        }
        return new Numa(JbClasser_naarea_get, false);
    }

    public void setW(int i) {
        JniLeptonicaJNI.JbClasser_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.JbClasser_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.JbClasser_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.JbClasser_h_get(this.swigCPtr, this);
    }

    public void setNclass(int i) {
        JniLeptonicaJNI.JbClasser_nclass_set(this.swigCPtr, this, i);
    }

    public int getNclass() {
        return JniLeptonicaJNI.JbClasser_nclass_get(this.swigCPtr, this);
    }

    public void setKeep_pixaa(int i) {
        JniLeptonicaJNI.JbClasser_keep_pixaa_set(this.swigCPtr, this, i);
    }

    public int getKeep_pixaa() {
        return JniLeptonicaJNI.JbClasser_keep_pixaa_get(this.swigCPtr, this);
    }

    public void setPixaa(Pixaa pixaa) {
        JniLeptonicaJNI.JbClasser_pixaa_set(this.swigCPtr, this, Pixaa.getCPtr(pixaa), pixaa);
    }

    public Pixaa getPixaa() {
        long JbClasser_pixaa_get = JniLeptonicaJNI.JbClasser_pixaa_get(this.swigCPtr, this);
        if (JbClasser_pixaa_get == 0) {
            return null;
        }
        return new Pixaa(JbClasser_pixaa_get, false);
    }

    public void setPixat(Pixa pixa) {
        JniLeptonicaJNI.JbClasser_pixat_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixat() {
        long JbClasser_pixat_get = JniLeptonicaJNI.JbClasser_pixat_get(this.swigCPtr, this);
        if (JbClasser_pixat_get == 0) {
            return null;
        }
        return new Pixa(JbClasser_pixat_get, false);
    }

    public void setPixatd(Pixa pixa) {
        JniLeptonicaJNI.JbClasser_pixatd_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixatd() {
        long JbClasser_pixatd_get = JniLeptonicaJNI.JbClasser_pixatd_get(this.swigCPtr, this);
        if (JbClasser_pixatd_get == 0) {
            return null;
        }
        return new Pixa(JbClasser_pixatd_get, false);
    }

    public void setNahash(NumaHash numaHash) {
        JniLeptonicaJNI.JbClasser_nahash_set(this.swigCPtr, this, NumaHash.getCPtr(numaHash), numaHash);
    }

    public NumaHash getNahash() {
        long JbClasser_nahash_get = JniLeptonicaJNI.JbClasser_nahash_get(this.swigCPtr, this);
        if (JbClasser_nahash_get == 0) {
            return null;
        }
        return new NumaHash(JbClasser_nahash_get, false);
    }

    public void setNafgt(Numa numa) {
        JniLeptonicaJNI.JbClasser_nafgt_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNafgt() {
        long JbClasser_nafgt_get = JniLeptonicaJNI.JbClasser_nafgt_get(this.swigCPtr, this);
        if (JbClasser_nafgt_get == 0) {
            return null;
        }
        return new Numa(JbClasser_nafgt_get, false);
    }

    public void setPtac(Pta pta) {
        JniLeptonicaJNI.JbClasser_ptac_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtac() {
        long JbClasser_ptac_get = JniLeptonicaJNI.JbClasser_ptac_get(this.swigCPtr, this);
        if (JbClasser_ptac_get == 0) {
            return null;
        }
        return new Pta(JbClasser_ptac_get, false);
    }

    public void setPtact(Pta pta) {
        JniLeptonicaJNI.JbClasser_ptact_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtact() {
        long JbClasser_ptact_get = JniLeptonicaJNI.JbClasser_ptact_get(this.swigCPtr, this);
        if (JbClasser_ptact_get == 0) {
            return null;
        }
        return new Pta(JbClasser_ptact_get, false);
    }

    public void setNaclass(Numa numa) {
        JniLeptonicaJNI.JbClasser_naclass_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaclass() {
        long JbClasser_naclass_get = JniLeptonicaJNI.JbClasser_naclass_get(this.swigCPtr, this);
        if (JbClasser_naclass_get == 0) {
            return null;
        }
        return new Numa(JbClasser_naclass_get, false);
    }

    public void setNapage(Numa numa) {
        JniLeptonicaJNI.JbClasser_napage_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNapage() {
        long JbClasser_napage_get = JniLeptonicaJNI.JbClasser_napage_get(this.swigCPtr, this);
        if (JbClasser_napage_get == 0) {
            return null;
        }
        return new Numa(JbClasser_napage_get, false);
    }

    public void setPtaul(Pta pta) {
        JniLeptonicaJNI.JbClasser_ptaul_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtaul() {
        long JbClasser_ptaul_get = JniLeptonicaJNI.JbClasser_ptaul_get(this.swigCPtr, this);
        if (JbClasser_ptaul_get == 0) {
            return null;
        }
        return new Pta(JbClasser_ptaul_get, false);
    }

    public void setPtall(Pta pta) {
        JniLeptonicaJNI.JbClasser_ptall_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtall() {
        long JbClasser_ptall_get = JniLeptonicaJNI.JbClasser_ptall_get(this.swigCPtr, this);
        if (JbClasser_ptall_get == 0) {
            return null;
        }
        return new Pta(JbClasser_ptall_get, false);
    }

    public JbClasser() {
        this(JniLeptonicaJNI.new_JbClasser(), true);
    }
}
