package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Compressed_Data {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Compressed_Data(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Compressed_Data l_Compressed_Data) {
        if (l_Compressed_Data == null) {
            return 0;
        }
        return l_Compressed_Data.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Compressed_Data(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(int i) {
        JniLeptonicaJNI.L_Compressed_Data_type_set(this.swigCPtr, this, i);
    }

    public int getType() {
        return JniLeptonicaJNI.L_Compressed_Data_type_get(this.swigCPtr, this);
    }

    public void setDatacomp(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.L_Compressed_Data_datacomp_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public SWIGTYPE_p_unsigned_char getDatacomp() {
        long L_Compressed_Data_datacomp_get = JniLeptonicaJNI.L_Compressed_Data_datacomp_get(this.swigCPtr, this);
        if (L_Compressed_Data_datacomp_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(L_Compressed_Data_datacomp_get, false);
    }

    public void setNbytescomp(long j) {
        JniLeptonicaJNI.L_Compressed_Data_nbytescomp_set(this.swigCPtr, this, j);
    }

    public long getNbytescomp() {
        return JniLeptonicaJNI.L_Compressed_Data_nbytescomp_get(this.swigCPtr, this);
    }

    public void setData85(String str) {
        JniLeptonicaJNI.L_Compressed_Data_data85_set(this.swigCPtr, this, str);
    }

    public String getData85() {
        return JniLeptonicaJNI.L_Compressed_Data_data85_get(this.swigCPtr, this);
    }

    public void setNbytes85(long j) {
        JniLeptonicaJNI.L_Compressed_Data_nbytes85_set(this.swigCPtr, this, j);
    }

    public long getNbytes85() {
        return JniLeptonicaJNI.L_Compressed_Data_nbytes85_get(this.swigCPtr, this);
    }

    public void setCmapdata85(String str) {
        JniLeptonicaJNI.L_Compressed_Data_cmapdata85_set(this.swigCPtr, this, str);
    }

    public String getCmapdata85() {
        return JniLeptonicaJNI.L_Compressed_Data_cmapdata85_get(this.swigCPtr, this);
    }

    public void setCmapdatahex(String str) {
        JniLeptonicaJNI.L_Compressed_Data_cmapdatahex_set(this.swigCPtr, this, str);
    }

    public String getCmapdatahex() {
        return JniLeptonicaJNI.L_Compressed_Data_cmapdatahex_get(this.swigCPtr, this);
    }

    public void setNcolors(int i) {
        JniLeptonicaJNI.L_Compressed_Data_ncolors_set(this.swigCPtr, this, i);
    }

    public int getNcolors() {
        return JniLeptonicaJNI.L_Compressed_Data_ncolors_get(this.swigCPtr, this);
    }

    public void setW(int i) {
        JniLeptonicaJNI.L_Compressed_Data_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.L_Compressed_Data_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.L_Compressed_Data_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.L_Compressed_Data_h_get(this.swigCPtr, this);
    }

    public void setBps(int i) {
        JniLeptonicaJNI.L_Compressed_Data_bps_set(this.swigCPtr, this, i);
    }

    public int getBps() {
        return JniLeptonicaJNI.L_Compressed_Data_bps_get(this.swigCPtr, this);
    }

    public void setSpp(int i) {
        JniLeptonicaJNI.L_Compressed_Data_spp_set(this.swigCPtr, this, i);
    }

    public int getSpp() {
        return JniLeptonicaJNI.L_Compressed_Data_spp_get(this.swigCPtr, this);
    }

    public void setMinisblack(int i) {
        JniLeptonicaJNI.L_Compressed_Data_minisblack_set(this.swigCPtr, this, i);
    }

    public int getMinisblack() {
        return JniLeptonicaJNI.L_Compressed_Data_minisblack_get(this.swigCPtr, this);
    }

    public void setPredictor(int i) {
        JniLeptonicaJNI.L_Compressed_Data_predictor_set(this.swigCPtr, this, i);
    }

    public int getPredictor() {
        return JniLeptonicaJNI.L_Compressed_Data_predictor_get(this.swigCPtr, this);
    }

    public void setNbytes(long j) {
        JniLeptonicaJNI.L_Compressed_Data_nbytes_set(this.swigCPtr, this, j);
    }

    public long getNbytes() {
        return JniLeptonicaJNI.L_Compressed_Data_nbytes_get(this.swigCPtr, this);
    }

    public void setRes(int i) {
        JniLeptonicaJNI.L_Compressed_Data_res_set(this.swigCPtr, this, i);
    }

    public int getRes() {
        return JniLeptonicaJNI.L_Compressed_Data_res_get(this.swigCPtr, this);
    }

    public L_Compressed_Data() {
        this(JniLeptonicaJNI.new_L_Compressed_Data(), true);
    }
}
