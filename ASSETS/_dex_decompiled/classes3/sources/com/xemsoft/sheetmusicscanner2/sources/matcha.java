package com.xemsoft.sheetmusicscanner2.sources;

public class matcha {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public matcha(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(matcha matcha) {
        if (matcha == null) {
            return 0;
        }
        return matcha.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_matcha(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.matcha_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.matcha_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.matcha_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.matcha_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_match sWIGTYPE_p_p_match) {
        JniSourceJNI.matcha_array_set(this.swigCPtr, this, SWIGTYPE_p_p_match.getCPtr(sWIGTYPE_p_p_match));
    }

    public SWIGTYPE_p_p_match getArray() {
        long matcha_array_get = JniSourceJNI.matcha_array_get(this.swigCPtr, this);
        if (matcha_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_match(matcha_array_get, false);
    }

    public matcha() {
        this(JniSourceJNI.new_matcha(), true);
    }
}
