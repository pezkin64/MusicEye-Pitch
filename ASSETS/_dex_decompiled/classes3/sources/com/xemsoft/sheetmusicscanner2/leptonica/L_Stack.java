package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Stack {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Stack(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Stack l_Stack) {
        if (l_Stack == null) {
            return 0;
        }
        return l_Stack.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Stack(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Stack_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Stack_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Stack_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Stack_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_Stack_array_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getArray() {
        long L_Stack_array_get = JniLeptonicaJNI.L_Stack_array_get(this.swigCPtr, this);
        if (L_Stack_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_Stack_array_get, false);
    }

    public void setAuxstack(L_Stack l_Stack) {
        JniLeptonicaJNI.L_Stack_auxstack_set(this.swigCPtr, this, getCPtr(l_Stack), l_Stack);
    }

    public L_Stack getAuxstack() {
        long L_Stack_auxstack_get = JniLeptonicaJNI.L_Stack_auxstack_get(this.swigCPtr, this);
        if (L_Stack_auxstack_get == 0) {
            return null;
        }
        return new L_Stack(L_Stack_auxstack_get, false);
    }

    public L_Stack() {
        this(JniLeptonicaJNI.new_L_Stack(), true);
    }
}
