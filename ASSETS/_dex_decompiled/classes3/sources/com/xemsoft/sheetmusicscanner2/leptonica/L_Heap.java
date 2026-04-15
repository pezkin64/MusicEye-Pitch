package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Heap {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Heap(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Heap l_Heap) {
        if (l_Heap == null) {
            return 0;
        }
        return l_Heap.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Heap(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Heap_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Heap_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.L_Heap_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.L_Heap_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_Heap_array_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getArray() {
        long L_Heap_array_get = JniLeptonicaJNI.L_Heap_array_get(this.swigCPtr, this);
        if (L_Heap_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_Heap_array_get, false);
    }

    public void setDirection(int i) {
        JniLeptonicaJNI.L_Heap_direction_set(this.swigCPtr, this, i);
    }

    public int getDirection() {
        return JniLeptonicaJNI.L_Heap_direction_get(this.swigCPtr, this);
    }

    public L_Heap() {
        this(JniLeptonicaJNI.new_L_Heap(), true);
    }
}
