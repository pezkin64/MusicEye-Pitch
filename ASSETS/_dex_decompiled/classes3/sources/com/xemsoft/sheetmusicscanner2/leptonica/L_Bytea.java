package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Bytea {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Bytea(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Bytea l_Bytea) {
        if (l_Bytea == null) {
            return 0;
        }
        return l_Bytea.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Bytea(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(long j) {
        JniLeptonicaJNI.L_Bytea_nalloc_set(this.swigCPtr, this, j);
    }

    public long getNalloc() {
        return JniLeptonicaJNI.L_Bytea_nalloc_get(this.swigCPtr, this);
    }

    public void setSize(long j) {
        JniLeptonicaJNI.L_Bytea_size_set(this.swigCPtr, this, j);
    }

    public long getSize() {
        return JniLeptonicaJNI.L_Bytea_size_get(this.swigCPtr, this);
    }

    public void setRefcount(int i) {
        JniLeptonicaJNI.L_Bytea_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniLeptonicaJNI.L_Bytea_refcount_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.L_Bytea_data_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public SWIGTYPE_p_unsigned_char getData() {
        long L_Bytea_data_get = JniLeptonicaJNI.L_Bytea_data_get(this.swigCPtr, this);
        if (L_Bytea_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(L_Bytea_data_get, false);
    }

    public L_Bytea() {
        this(JniLeptonicaJNI.new_L_Bytea(), true);
    }
}
