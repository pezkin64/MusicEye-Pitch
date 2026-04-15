package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Pdf_Data {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Pdf_Data(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Pdf_Data l_Pdf_Data) {
        if (l_Pdf_Data == null) {
            return 0;
        }
        return l_Pdf_Data.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Pdf_Data(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTitle(String str) {
        JniLeptonicaJNI.L_Pdf_Data_title_set(this.swigCPtr, this, str);
    }

    public String getTitle() {
        return JniLeptonicaJNI.L_Pdf_Data_title_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Pdf_Data_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Pdf_Data_n_get(this.swigCPtr, this);
    }

    public void setNcmap(int i) {
        JniLeptonicaJNI.L_Pdf_Data_ncmap_set(this.swigCPtr, this, i);
    }

    public int getNcmap() {
        return JniLeptonicaJNI.L_Pdf_Data_ncmap_get(this.swigCPtr, this);
    }

    public void setCida(L_Ptra l_Ptra) {
        JniLeptonicaJNI.L_Pdf_Data_cida_set(this.swigCPtr, this, L_Ptra.getCPtr(l_Ptra), l_Ptra);
    }

    public L_Ptra getCida() {
        long L_Pdf_Data_cida_get = JniLeptonicaJNI.L_Pdf_Data_cida_get(this.swigCPtr, this);
        if (L_Pdf_Data_cida_get == 0) {
            return null;
        }
        return new L_Ptra(L_Pdf_Data_cida_get, false);
    }

    public void setId(String str) {
        JniLeptonicaJNI.L_Pdf_Data_id_set(this.swigCPtr, this, str);
    }

    public String getId() {
        return JniLeptonicaJNI.L_Pdf_Data_id_get(this.swigCPtr, this);
    }

    public void setObj1(String str) {
        JniLeptonicaJNI.L_Pdf_Data_obj1_set(this.swigCPtr, this, str);
    }

    public String getObj1() {
        return JniLeptonicaJNI.L_Pdf_Data_obj1_get(this.swigCPtr, this);
    }

    public void setObj2(String str) {
        JniLeptonicaJNI.L_Pdf_Data_obj2_set(this.swigCPtr, this, str);
    }

    public String getObj2() {
        return JniLeptonicaJNI.L_Pdf_Data_obj2_get(this.swigCPtr, this);
    }

    public void setObj3(String str) {
        JniLeptonicaJNI.L_Pdf_Data_obj3_set(this.swigCPtr, this, str);
    }

    public String getObj3() {
        return JniLeptonicaJNI.L_Pdf_Data_obj3_get(this.swigCPtr, this);
    }

    public void setObj4(String str) {
        JniLeptonicaJNI.L_Pdf_Data_obj4_set(this.swigCPtr, this, str);
    }

    public String getObj4() {
        return JniLeptonicaJNI.L_Pdf_Data_obj4_get(this.swigCPtr, this);
    }

    public void setObj5(String str) {
        JniLeptonicaJNI.L_Pdf_Data_obj5_set(this.swigCPtr, this, str);
    }

    public String getObj5() {
        return JniLeptonicaJNI.L_Pdf_Data_obj5_get(this.swigCPtr, this);
    }

    public void setPoststream(String str) {
        JniLeptonicaJNI.L_Pdf_Data_poststream_set(this.swigCPtr, this, str);
    }

    public String getPoststream() {
        return JniLeptonicaJNI.L_Pdf_Data_poststream_get(this.swigCPtr, this);
    }

    public void setTrailer(String str) {
        JniLeptonicaJNI.L_Pdf_Data_trailer_set(this.swigCPtr, this, str);
    }

    public String getTrailer() {
        return JniLeptonicaJNI.L_Pdf_Data_trailer_get(this.swigCPtr, this);
    }

    public void setXy(Pta pta) {
        JniLeptonicaJNI.L_Pdf_Data_xy_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getXy() {
        long L_Pdf_Data_xy_get = JniLeptonicaJNI.L_Pdf_Data_xy_get(this.swigCPtr, this);
        if (L_Pdf_Data_xy_get == 0) {
            return null;
        }
        return new Pta(L_Pdf_Data_xy_get, false);
    }

    public void setWh(Pta pta) {
        JniLeptonicaJNI.L_Pdf_Data_wh_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getWh() {
        long L_Pdf_Data_wh_get = JniLeptonicaJNI.L_Pdf_Data_wh_get(this.swigCPtr, this);
        if (L_Pdf_Data_wh_get == 0) {
            return null;
        }
        return new Pta(L_Pdf_Data_wh_get, false);
    }

    public void setMediabox(Box box) {
        JniLeptonicaJNI.L_Pdf_Data_mediabox_set(this.swigCPtr, this, Box.getCPtr(box), box);
    }

    public Box getMediabox() {
        long L_Pdf_Data_mediabox_get = JniLeptonicaJNI.L_Pdf_Data_mediabox_get(this.swigCPtr, this);
        if (L_Pdf_Data_mediabox_get == 0) {
            return null;
        }
        return new Box(L_Pdf_Data_mediabox_get, false);
    }

    public void setSaprex(Sarray sarray) {
        JniLeptonicaJNI.L_Pdf_Data_saprex_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getSaprex() {
        long L_Pdf_Data_saprex_get = JniLeptonicaJNI.L_Pdf_Data_saprex_get(this.swigCPtr, this);
        if (L_Pdf_Data_saprex_get == 0) {
            return null;
        }
        return new Sarray(L_Pdf_Data_saprex_get, false);
    }

    public void setSacmap(Sarray sarray) {
        JniLeptonicaJNI.L_Pdf_Data_sacmap_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getSacmap() {
        long L_Pdf_Data_sacmap_get = JniLeptonicaJNI.L_Pdf_Data_sacmap_get(this.swigCPtr, this);
        if (L_Pdf_Data_sacmap_get == 0) {
            return null;
        }
        return new Sarray(L_Pdf_Data_sacmap_get, false);
    }

    public void setObjsize(L_Dna l_Dna) {
        JniLeptonicaJNI.L_Pdf_Data_objsize_set(this.swigCPtr, this, L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public L_Dna getObjsize() {
        long L_Pdf_Data_objsize_get = JniLeptonicaJNI.L_Pdf_Data_objsize_get(this.swigCPtr, this);
        if (L_Pdf_Data_objsize_get == 0) {
            return null;
        }
        return new L_Dna(L_Pdf_Data_objsize_get, false);
    }

    public void setObjloc(L_Dna l_Dna) {
        JniLeptonicaJNI.L_Pdf_Data_objloc_set(this.swigCPtr, this, L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public L_Dna getObjloc() {
        long L_Pdf_Data_objloc_get = JniLeptonicaJNI.L_Pdf_Data_objloc_get(this.swigCPtr, this);
        if (L_Pdf_Data_objloc_get == 0) {
            return null;
        }
        return new L_Dna(L_Pdf_Data_objloc_get, false);
    }

    public void setXrefloc(int i) {
        JniLeptonicaJNI.L_Pdf_Data_xrefloc_set(this.swigCPtr, this, i);
    }

    public int getXrefloc() {
        return JniLeptonicaJNI.L_Pdf_Data_xrefloc_get(this.swigCPtr, this);
    }

    public L_Pdf_Data() {
        this(JniLeptonicaJNI.new_L_Pdf_Data(), true);
    }
}
