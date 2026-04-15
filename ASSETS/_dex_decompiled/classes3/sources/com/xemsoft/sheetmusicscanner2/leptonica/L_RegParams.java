package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_RegParams {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_RegParams(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_RegParams l_RegParams) {
        if (l_RegParams == null) {
            return 0;
        }
        return l_RegParams.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_RegParams(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setFp(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        JniLeptonicaJNI.L_RegParams_fp_set(this.swigCPtr, this, SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public SWIGTYPE_p_FILE getFp() {
        long L_RegParams_fp_get = JniLeptonicaJNI.L_RegParams_fp_get(this.swigCPtr, this);
        if (L_RegParams_fp_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_FILE(L_RegParams_fp_get, false);
    }

    public void setTestname(String str) {
        JniLeptonicaJNI.L_RegParams_testname_set(this.swigCPtr, this, str);
    }

    public String getTestname() {
        return JniLeptonicaJNI.L_RegParams_testname_get(this.swigCPtr, this);
    }

    public void setTempfile(String str) {
        JniLeptonicaJNI.L_RegParams_tempfile_set(this.swigCPtr, this, str);
    }

    public String getTempfile() {
        return JniLeptonicaJNI.L_RegParams_tempfile_get(this.swigCPtr, this);
    }

    public void setMode(int i) {
        JniLeptonicaJNI.L_RegParams_mode_set(this.swigCPtr, this, i);
    }

    public int getMode() {
        return JniLeptonicaJNI.L_RegParams_mode_get(this.swigCPtr, this);
    }

    public void setIndex(int i) {
        JniLeptonicaJNI.L_RegParams_index_set(this.swigCPtr, this, i);
    }

    public int getIndex() {
        return JniLeptonicaJNI.L_RegParams_index_get(this.swigCPtr, this);
    }

    public void setSuccess(int i) {
        JniLeptonicaJNI.L_RegParams_success_set(this.swigCPtr, this, i);
    }

    public int getSuccess() {
        return JniLeptonicaJNI.L_RegParams_success_get(this.swigCPtr, this);
    }

    public void setDisplay(int i) {
        JniLeptonicaJNI.L_RegParams_display_set(this.swigCPtr, this, i);
    }

    public int getDisplay() {
        return JniLeptonicaJNI.L_RegParams_display_get(this.swigCPtr, this);
    }

    public void setTstart(SWIGTYPE_p_void sWIGTYPE_p_void) {
        JniLeptonicaJNI.L_RegParams_tstart_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getTstart() {
        long L_RegParams_tstart_get = JniLeptonicaJNI.L_RegParams_tstart_get(this.swigCPtr, this);
        if (L_RegParams_tstart_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(L_RegParams_tstart_get, false);
    }

    public L_RegParams() {
        this(JniLeptonicaJNI.new_L_RegParams(), true);
    }
}
