package com.xemsoft.sheetmusicscanner2.sources;

public class syma {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public syma(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(syma syma) {
        if (syma == null) {
            return 0;
        }
        return syma.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_syma(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.syma_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.syma_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.syma_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.syma_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_symbol sWIGTYPE_p_p_symbol) {
        JniSourceJNI.syma_array_set(this.swigCPtr, this, SWIGTYPE_p_p_symbol.getCPtr(sWIGTYPE_p_p_symbol));
    }

    public SWIGTYPE_p_p_symbol getArray() {
        long syma_array_get = JniSourceJNI.syma_array_get(this.swigCPtr, this);
        if (syma_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_symbol(syma_array_get, false);
    }

    public syma() {
        this(JniSourceJNI.new_syma(), true);
    }
}
