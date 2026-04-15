package com.xemsoft.sheetmusicscanner2.leptonica;

public class Sel {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Sel(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Sel sel) {
        if (sel == null) {
            return 0;
        }
        return sel.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Sel(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSy(int i) {
        JniLeptonicaJNI.Sel_sy_set(this.swigCPtr, this, i);
    }

    public int getSy() {
        return JniLeptonicaJNI.Sel_sy_get(this.swigCPtr, this);
    }

    public void setSx(int i) {
        JniLeptonicaJNI.Sel_sx_set(this.swigCPtr, this, i);
    }

    public int getSx() {
        return JniLeptonicaJNI.Sel_sx_get(this.swigCPtr, this);
    }

    public void setCy(int i) {
        JniLeptonicaJNI.Sel_cy_set(this.swigCPtr, this, i);
    }

    public int getCy() {
        return JniLeptonicaJNI.Sel_cy_get(this.swigCPtr, this);
    }

    public void setCx(int i) {
        JniLeptonicaJNI.Sel_cx_set(this.swigCPtr, this, i);
    }

    public int getCx() {
        return JniLeptonicaJNI.Sel_cx_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_p_int sWIGTYPE_p_p_int) {
        JniLeptonicaJNI.Sel_data_set(this.swigCPtr, this, SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int));
    }

    public SWIGTYPE_p_p_int getData() {
        long Sel_data_get = JniLeptonicaJNI.Sel_data_get(this.swigCPtr, this);
        if (Sel_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_int(Sel_data_get, false);
    }

    public void setName(String str) {
        JniLeptonicaJNI.Sel_name_set(this.swigCPtr, this, str);
    }

    public String getName() {
        return JniLeptonicaJNI.Sel_name_get(this.swigCPtr, this);
    }

    public Sel() {
        this(JniLeptonicaJNI.new_Sel(), true);
    }
}
