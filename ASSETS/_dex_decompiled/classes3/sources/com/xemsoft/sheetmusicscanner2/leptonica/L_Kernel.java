package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Kernel {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Kernel(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Kernel l_Kernel) {
        if (l_Kernel == null) {
            return 0;
        }
        return l_Kernel.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Kernel(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSy(int i) {
        JniLeptonicaJNI.L_Kernel_sy_set(this.swigCPtr, this, i);
    }

    public int getSy() {
        return JniLeptonicaJNI.L_Kernel_sy_get(this.swigCPtr, this);
    }

    public void setSx(int i) {
        JniLeptonicaJNI.L_Kernel_sx_set(this.swigCPtr, this, i);
    }

    public int getSx() {
        return JniLeptonicaJNI.L_Kernel_sx_get(this.swigCPtr, this);
    }

    public void setCy(int i) {
        JniLeptonicaJNI.L_Kernel_cy_set(this.swigCPtr, this, i);
    }

    public int getCy() {
        return JniLeptonicaJNI.L_Kernel_cy_get(this.swigCPtr, this);
    }

    public void setCx(int i) {
        JniLeptonicaJNI.L_Kernel_cx_set(this.swigCPtr, this, i);
    }

    public int getCx() {
        return JniLeptonicaJNI.L_Kernel_cx_get(this.swigCPtr, this);
    }

    public void setData(SWIGTYPE_p_p_float sWIGTYPE_p_p_float) {
        JniLeptonicaJNI.L_Kernel_data_set(this.swigCPtr, this, SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float));
    }

    public SWIGTYPE_p_p_float getData() {
        long L_Kernel_data_get = JniLeptonicaJNI.L_Kernel_data_get(this.swigCPtr, this);
        if (L_Kernel_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_float(L_Kernel_data_get, false);
    }

    public L_Kernel() {
        this(JniLeptonicaJNI.new_L_Kernel(), true);
    }
}
