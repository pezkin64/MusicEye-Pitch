package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Recog {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Recog(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Recog l_Recog) {
        if (l_Recog == null) {
            return 0;
        }
        return l_Recog.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Recog(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setScalew(int i) {
        JniLeptonicaJNI.L_Recog_scalew_set(this.swigCPtr, this, i);
    }

    public int getScalew() {
        return JniLeptonicaJNI.L_Recog_scalew_get(this.swigCPtr, this);
    }

    public void setScaleh(int i) {
        JniLeptonicaJNI.L_Recog_scaleh_set(this.swigCPtr, this, i);
    }

    public int getScaleh() {
        return JniLeptonicaJNI.L_Recog_scaleh_get(this.swigCPtr, this);
    }

    public void setTempl_type(int i) {
        JniLeptonicaJNI.L_Recog_templ_type_set(this.swigCPtr, this, i);
    }

    public int getTempl_type() {
        return JniLeptonicaJNI.L_Recog_templ_type_get(this.swigCPtr, this);
    }

    public void setMaxarraysize(int i) {
        JniLeptonicaJNI.L_Recog_maxarraysize_set(this.swigCPtr, this, i);
    }

    public int getMaxarraysize() {
        return JniLeptonicaJNI.L_Recog_maxarraysize_get(this.swigCPtr, this);
    }

    public void setSetsize(int i) {
        JniLeptonicaJNI.L_Recog_setsize_set(this.swigCPtr, this, i);
    }

    public int getSetsize() {
        return JniLeptonicaJNI.L_Recog_setsize_get(this.swigCPtr, this);
    }

    public void setThreshold(int i) {
        JniLeptonicaJNI.L_Recog_threshold_set(this.swigCPtr, this, i);
    }

    public int getThreshold() {
        return JniLeptonicaJNI.L_Recog_threshold_get(this.swigCPtr, this);
    }

    public void setMaxyshift(int i) {
        JniLeptonicaJNI.L_Recog_maxyshift_set(this.swigCPtr, this, i);
    }

    public int getMaxyshift() {
        return JniLeptonicaJNI.L_Recog_maxyshift_get(this.swigCPtr, this);
    }

    public void setAsperity_fr(float f) {
        JniLeptonicaJNI.L_Recog_asperity_fr_set(this.swigCPtr, this, f);
    }

    public float getAsperity_fr() {
        return JniLeptonicaJNI.L_Recog_asperity_fr_get(this.swigCPtr, this);
    }

    public void setCharset_type(int i) {
        JniLeptonicaJNI.L_Recog_charset_type_set(this.swigCPtr, this, i);
    }

    public int getCharset_type() {
        return JniLeptonicaJNI.L_Recog_charset_type_get(this.swigCPtr, this);
    }

    public void setCharset_size(int i) {
        JniLeptonicaJNI.L_Recog_charset_size_set(this.swigCPtr, this, i);
    }

    public int getCharset_size() {
        return JniLeptonicaJNI.L_Recog_charset_size_get(this.swigCPtr, this);
    }

    public void setBootdir(String str) {
        JniLeptonicaJNI.L_Recog_bootdir_set(this.swigCPtr, this, str);
    }

    public String getBootdir() {
        return JniLeptonicaJNI.L_Recog_bootdir_get(this.swigCPtr, this);
    }

    public void setBootpattern(String str) {
        JniLeptonicaJNI.L_Recog_bootpattern_set(this.swigCPtr, this, str);
    }

    public String getBootpattern() {
        return JniLeptonicaJNI.L_Recog_bootpattern_get(this.swigCPtr, this);
    }

    public void setBootpath(String str) {
        JniLeptonicaJNI.L_Recog_bootpath_set(this.swigCPtr, this, str);
    }

    public String getBootpath() {
        return JniLeptonicaJNI.L_Recog_bootpath_get(this.swigCPtr, this);
    }

    public void setMin_nopad(int i) {
        JniLeptonicaJNI.L_Recog_min_nopad_set(this.swigCPtr, this, i);
    }

    public int getMin_nopad() {
        return JniLeptonicaJNI.L_Recog_min_nopad_get(this.swigCPtr, this);
    }

    public void setMax_afterpad(int i) {
        JniLeptonicaJNI.L_Recog_max_afterpad_set(this.swigCPtr, this, i);
    }

    public int getMax_afterpad() {
        return JniLeptonicaJNI.L_Recog_max_afterpad_get(this.swigCPtr, this);
    }

    public void setSamplenum(int i) {
        JniLeptonicaJNI.L_Recog_samplenum_set(this.swigCPtr, this, i);
    }

    public int getSamplenum() {
        return JniLeptonicaJNI.L_Recog_samplenum_get(this.swigCPtr, this);
    }

    public void setMinwidth_u(int i) {
        JniLeptonicaJNI.L_Recog_minwidth_u_set(this.swigCPtr, this, i);
    }

    public int getMinwidth_u() {
        return JniLeptonicaJNI.L_Recog_minwidth_u_get(this.swigCPtr, this);
    }

    public void setMaxwidth_u(int i) {
        JniLeptonicaJNI.L_Recog_maxwidth_u_set(this.swigCPtr, this, i);
    }

    public int getMaxwidth_u() {
        return JniLeptonicaJNI.L_Recog_maxwidth_u_get(this.swigCPtr, this);
    }

    public void setMinheight_u(int i) {
        JniLeptonicaJNI.L_Recog_minheight_u_set(this.swigCPtr, this, i);
    }

    public int getMinheight_u() {
        return JniLeptonicaJNI.L_Recog_minheight_u_get(this.swigCPtr, this);
    }

    public void setMaxheight_u(int i) {
        JniLeptonicaJNI.L_Recog_maxheight_u_set(this.swigCPtr, this, i);
    }

    public int getMaxheight_u() {
        return JniLeptonicaJNI.L_Recog_maxheight_u_get(this.swigCPtr, this);
    }

    public void setMinwidth(int i) {
        JniLeptonicaJNI.L_Recog_minwidth_set(this.swigCPtr, this, i);
    }

    public int getMinwidth() {
        return JniLeptonicaJNI.L_Recog_minwidth_get(this.swigCPtr, this);
    }

    public void setMaxwidth(int i) {
        JniLeptonicaJNI.L_Recog_maxwidth_set(this.swigCPtr, this, i);
    }

    public int getMaxwidth() {
        return JniLeptonicaJNI.L_Recog_maxwidth_get(this.swigCPtr, this);
    }

    public void setAve_done(int i) {
        JniLeptonicaJNI.L_Recog_ave_done_set(this.swigCPtr, this, i);
    }

    public int getAve_done() {
        return JniLeptonicaJNI.L_Recog_ave_done_get(this.swigCPtr, this);
    }

    public void setTrain_done(int i) {
        JniLeptonicaJNI.L_Recog_train_done_set(this.swigCPtr, this, i);
    }

    public int getTrain_done() {
        return JniLeptonicaJNI.L_Recog_train_done_get(this.swigCPtr, this);
    }

    public void setMin_splitw(int i) {
        JniLeptonicaJNI.L_Recog_min_splitw_set(this.swigCPtr, this, i);
    }

    public int getMin_splitw() {
        return JniLeptonicaJNI.L_Recog_min_splitw_get(this.swigCPtr, this);
    }

    public void setMin_splith(int i) {
        JniLeptonicaJNI.L_Recog_min_splith_set(this.swigCPtr, this, i);
    }

    public int getMin_splith() {
        return JniLeptonicaJNI.L_Recog_min_splith_get(this.swigCPtr, this);
    }

    public void setMax_splith(int i) {
        JniLeptonicaJNI.L_Recog_max_splith_set(this.swigCPtr, this, i);
    }

    public int getMax_splith() {
        return JniLeptonicaJNI.L_Recog_max_splith_get(this.swigCPtr, this);
    }

    public void setSa_text(Sarray sarray) {
        JniLeptonicaJNI.L_Recog_sa_text_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getSa_text() {
        long L_Recog_sa_text_get = JniLeptonicaJNI.L_Recog_sa_text_get(this.swigCPtr, this);
        if (L_Recog_sa_text_get == 0) {
            return null;
        }
        return new Sarray(L_Recog_sa_text_get, false);
    }

    public void setDna_tochar(L_Dna l_Dna) {
        JniLeptonicaJNI.L_Recog_dna_tochar_set(this.swigCPtr, this, L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public L_Dna getDna_tochar() {
        long L_Recog_dna_tochar_get = JniLeptonicaJNI.L_Recog_dna_tochar_get(this.swigCPtr, this);
        if (L_Recog_dna_tochar_get == 0) {
            return null;
        }
        return new L_Dna(L_Recog_dna_tochar_get, false);
    }

    public void setCenttab(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Recog_centtab_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getCenttab() {
        long L_Recog_centtab_get = JniLeptonicaJNI.L_Recog_centtab_get(this.swigCPtr, this);
        if (L_Recog_centtab_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Recog_centtab_get, false);
    }

    public void setSumtab(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Recog_sumtab_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getSumtab() {
        long L_Recog_sumtab_get = JniLeptonicaJNI.L_Recog_sumtab_get(this.swigCPtr, this);
        if (L_Recog_sumtab_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Recog_sumtab_get, false);
    }

    public void setFname(String str) {
        JniLeptonicaJNI.L_Recog_fname_set(this.swigCPtr, this, str);
    }

    public String getFname() {
        return JniLeptonicaJNI.L_Recog_fname_get(this.swigCPtr, this);
    }

    public void setPixaa_u(Pixaa pixaa) {
        JniLeptonicaJNI.L_Recog_pixaa_u_set(this.swigCPtr, this, Pixaa.getCPtr(pixaa), pixaa);
    }

    public Pixaa getPixaa_u() {
        long L_Recog_pixaa_u_get = JniLeptonicaJNI.L_Recog_pixaa_u_get(this.swigCPtr, this);
        if (L_Recog_pixaa_u_get == 0) {
            return null;
        }
        return new Pixaa(L_Recog_pixaa_u_get, false);
    }

    public void setPixa_u(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixa_u_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixa_u() {
        long L_Recog_pixa_u_get = JniLeptonicaJNI.L_Recog_pixa_u_get(this.swigCPtr, this);
        if (L_Recog_pixa_u_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixa_u_get, false);
    }

    public void setPtaa_u(Ptaa ptaa) {
        JniLeptonicaJNI.L_Recog_ptaa_u_set(this.swigCPtr, this, Ptaa.getCPtr(ptaa), ptaa);
    }

    public Ptaa getPtaa_u() {
        long L_Recog_ptaa_u_get = JniLeptonicaJNI.L_Recog_ptaa_u_get(this.swigCPtr, this);
        if (L_Recog_ptaa_u_get == 0) {
            return null;
        }
        return new Ptaa(L_Recog_ptaa_u_get, false);
    }

    public void setPta_u(Pta pta) {
        JniLeptonicaJNI.L_Recog_pta_u_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPta_u() {
        long L_Recog_pta_u_get = JniLeptonicaJNI.L_Recog_pta_u_get(this.swigCPtr, this);
        if (L_Recog_pta_u_get == 0) {
            return null;
        }
        return new Pta(L_Recog_pta_u_get, false);
    }

    public void setNaasum_u(Numaa numaa) {
        JniLeptonicaJNI.L_Recog_naasum_u_set(this.swigCPtr, this, Numaa.getCPtr(numaa), numaa);
    }

    public Numaa getNaasum_u() {
        long L_Recog_naasum_u_get = JniLeptonicaJNI.L_Recog_naasum_u_get(this.swigCPtr, this);
        if (L_Recog_naasum_u_get == 0) {
            return null;
        }
        return new Numaa(L_Recog_naasum_u_get, false);
    }

    public void setNasum_u(Numa numa) {
        JniLeptonicaJNI.L_Recog_nasum_u_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNasum_u() {
        long L_Recog_nasum_u_get = JniLeptonicaJNI.L_Recog_nasum_u_get(this.swigCPtr, this);
        if (L_Recog_nasum_u_get == 0) {
            return null;
        }
        return new Numa(L_Recog_nasum_u_get, false);
    }

    public void setPixaa(Pixaa pixaa) {
        JniLeptonicaJNI.L_Recog_pixaa_set(this.swigCPtr, this, Pixaa.getCPtr(pixaa), pixaa);
    }

    public Pixaa getPixaa() {
        long L_Recog_pixaa_get = JniLeptonicaJNI.L_Recog_pixaa_get(this.swigCPtr, this);
        if (L_Recog_pixaa_get == 0) {
            return null;
        }
        return new Pixaa(L_Recog_pixaa_get, false);
    }

    public void setPixa(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixa_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixa() {
        long L_Recog_pixa_get = JniLeptonicaJNI.L_Recog_pixa_get(this.swigCPtr, this);
        if (L_Recog_pixa_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixa_get, false);
    }

    public void setPtaa(Ptaa ptaa) {
        JniLeptonicaJNI.L_Recog_ptaa_set(this.swigCPtr, this, Ptaa.getCPtr(ptaa), ptaa);
    }

    public Ptaa getPtaa() {
        long L_Recog_ptaa_get = JniLeptonicaJNI.L_Recog_ptaa_get(this.swigCPtr, this);
        if (L_Recog_ptaa_get == 0) {
            return null;
        }
        return new Ptaa(L_Recog_ptaa_get, false);
    }

    public void setPta(Pta pta) {
        JniLeptonicaJNI.L_Recog_pta_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPta() {
        long L_Recog_pta_get = JniLeptonicaJNI.L_Recog_pta_get(this.swigCPtr, this);
        if (L_Recog_pta_get == 0) {
            return null;
        }
        return new Pta(L_Recog_pta_get, false);
    }

    public void setNaasum(Numaa numaa) {
        JniLeptonicaJNI.L_Recog_naasum_set(this.swigCPtr, this, Numaa.getCPtr(numaa), numaa);
    }

    public Numaa getNaasum() {
        long L_Recog_naasum_get = JniLeptonicaJNI.L_Recog_naasum_get(this.swigCPtr, this);
        if (L_Recog_naasum_get == 0) {
            return null;
        }
        return new Numaa(L_Recog_naasum_get, false);
    }

    public void setNasum(Numa numa) {
        JniLeptonicaJNI.L_Recog_nasum_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNasum() {
        long L_Recog_nasum_get = JniLeptonicaJNI.L_Recog_nasum_get(this.swigCPtr, this);
        if (L_Recog_nasum_get == 0) {
            return null;
        }
        return new Numa(L_Recog_nasum_get, false);
    }

    public void setPixa_tr(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixa_tr_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixa_tr() {
        long L_Recog_pixa_tr_get = JniLeptonicaJNI.L_Recog_pixa_tr_get(this.swigCPtr, this);
        if (L_Recog_pixa_tr_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixa_tr_get, false);
    }

    public void setPixadb_ave(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixadb_ave_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixadb_ave() {
        long L_Recog_pixadb_ave_get = JniLeptonicaJNI.L_Recog_pixadb_ave_get(this.swigCPtr, this);
        if (L_Recog_pixadb_ave_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixadb_ave_get, false);
    }

    public void setPixa_id(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixa_id_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixa_id() {
        long L_Recog_pixa_id_get = JniLeptonicaJNI.L_Recog_pixa_id_get(this.swigCPtr, this);
        if (L_Recog_pixa_id_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixa_id_get, false);
    }

    public void setPixdb_ave(Pix pix) {
        JniLeptonicaJNI.L_Recog_pixdb_ave_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixdb_ave() {
        long L_Recog_pixdb_ave_get = JniLeptonicaJNI.L_Recog_pixdb_ave_get(this.swigCPtr, this);
        if (L_Recog_pixdb_ave_get == 0) {
            return null;
        }
        return new Pix(L_Recog_pixdb_ave_get, false);
    }

    public void setPixdb_range(Pix pix) {
        JniLeptonicaJNI.L_Recog_pixdb_range_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPixdb_range() {
        long L_Recog_pixdb_range_get = JniLeptonicaJNI.L_Recog_pixdb_range_get(this.swigCPtr, this);
        if (L_Recog_pixdb_range_get == 0) {
            return null;
        }
        return new Pix(L_Recog_pixdb_range_get, false);
    }

    public void setPixadb_boot(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixadb_boot_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixadb_boot() {
        long L_Recog_pixadb_boot_get = JniLeptonicaJNI.L_Recog_pixadb_boot_get(this.swigCPtr, this);
        if (L_Recog_pixadb_boot_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixadb_boot_get, false);
    }

    public void setPixadb_split(Pixa pixa) {
        JniLeptonicaJNI.L_Recog_pixadb_split_set(this.swigCPtr, this, Pixa.getCPtr(pixa), pixa);
    }

    public Pixa getPixadb_split() {
        long L_Recog_pixadb_split_get = JniLeptonicaJNI.L_Recog_pixadb_split_get(this.swigCPtr, this);
        if (L_Recog_pixadb_split_get == 0) {
            return null;
        }
        return new Pixa(L_Recog_pixadb_split_get, false);
    }

    public void setFontdir(String str) {
        JniLeptonicaJNI.L_Recog_fontdir_set(this.swigCPtr, this, str);
    }

    public String getFontdir() {
        return JniLeptonicaJNI.L_Recog_fontdir_get(this.swigCPtr, this);
    }

    public void setBmf(L_Bmf l_Bmf) {
        JniLeptonicaJNI.L_Recog_bmf_set(this.swigCPtr, this, L_Bmf.getCPtr(l_Bmf), l_Bmf);
    }

    public L_Bmf getBmf() {
        long L_Recog_bmf_get = JniLeptonicaJNI.L_Recog_bmf_get(this.swigCPtr, this);
        if (L_Recog_bmf_get == 0) {
            return null;
        }
        return new L_Bmf(L_Recog_bmf_get, false);
    }

    public void setBmf_size(int i) {
        JniLeptonicaJNI.L_Recog_bmf_size_set(this.swigCPtr, this, i);
    }

    public int getBmf_size() {
        return JniLeptonicaJNI.L_Recog_bmf_size_get(this.swigCPtr, this);
    }

    public void setDid(L_Rdid l_Rdid) {
        JniLeptonicaJNI.L_Recog_did_set(this.swigCPtr, this, L_Rdid.getCPtr(l_Rdid), l_Rdid);
    }

    public L_Rdid getDid() {
        long L_Recog_did_get = JniLeptonicaJNI.L_Recog_did_get(this.swigCPtr, this);
        if (L_Recog_did_get == 0) {
            return null;
        }
        return new L_Rdid(L_Recog_did_get, false);
    }

    public void setRch(L_Rch l_Rch) {
        JniLeptonicaJNI.L_Recog_rch_set(this.swigCPtr, this, L_Rch.getCPtr(l_Rch), l_Rch);
    }

    public L_Rch getRch() {
        long L_Recog_rch_get = JniLeptonicaJNI.L_Recog_rch_get(this.swigCPtr, this);
        if (L_Recog_rch_get == 0) {
            return null;
        }
        return new L_Rch(L_Recog_rch_get, false);
    }

    public void setRcha(L_Rcha l_Rcha) {
        JniLeptonicaJNI.L_Recog_rcha_set(this.swigCPtr, this, L_Rcha.getCPtr(l_Rcha), l_Rcha);
    }

    public L_Rcha getRcha() {
        long L_Recog_rcha_get = JniLeptonicaJNI.L_Recog_rcha_get(this.swigCPtr, this);
        if (L_Recog_rcha_get == 0) {
            return null;
        }
        return new L_Rcha(L_Recog_rcha_get, false);
    }

    public void setBootrecog(int i) {
        JniLeptonicaJNI.L_Recog_bootrecog_set(this.swigCPtr, this, i);
    }

    public int getBootrecog() {
        return JniLeptonicaJNI.L_Recog_bootrecog_get(this.swigCPtr, this);
    }

    public void setIndex(int i) {
        JniLeptonicaJNI.L_Recog_index_set(this.swigCPtr, this, i);
    }

    public int getIndex() {
        return JniLeptonicaJNI.L_Recog_index_get(this.swigCPtr, this);
    }

    public void setParent(L_Recoga l_Recoga) {
        JniLeptonicaJNI.L_Recog_parent_set(this.swigCPtr, this, L_Recoga.getCPtr(l_Recoga), l_Recoga);
    }

    public L_Recoga getParent() {
        long L_Recog_parent_get = JniLeptonicaJNI.L_Recog_parent_get(this.swigCPtr, this);
        if (L_Recog_parent_get == 0) {
            return null;
        }
        return new L_Recoga(L_Recog_parent_get, false);
    }

    public L_Recog() {
        this(JniLeptonicaJNI.new_L_Recog(), true);
    }
}
