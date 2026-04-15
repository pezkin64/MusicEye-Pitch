package com.xemsoft.sheetmusicscanner2.sources;

public class PRIMA {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PRIMA(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PRIMA prima) {
        if (prima == null) {
            return 0;
        }
        return prima.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_PRIMA(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setDoesOwnItems(int i) {
        JniSourceJNI.PRIMA_doesOwnItems_set(this.swigCPtr, this, i);
    }

    public int getDoesOwnItems() {
        return JniSourceJNI.PRIMA_doesOwnItems_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniSourceJNI.PRIMA_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.PRIMA_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.PRIMA_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.PRIMA_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_prim sWIGTYPE_p_p_prim) {
        JniSourceJNI.PRIMA_array_set(this.swigCPtr, this, SWIGTYPE_p_p_prim.getCPtr(sWIGTYPE_p_p_prim));
    }

    public SWIGTYPE_p_p_prim getArray() {
        long PRIMA_array_get = JniSourceJNI.PRIMA_array_get(this.swigCPtr, this);
        if (PRIMA_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_prim(PRIMA_array_get, false);
    }

    public PRIMA() {
        this(JniSourceJNI.new_PRIMA(), true);
    }
}
