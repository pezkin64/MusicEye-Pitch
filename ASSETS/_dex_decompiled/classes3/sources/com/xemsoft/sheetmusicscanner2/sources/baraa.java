package com.xemsoft.sheetmusicscanner2.sources;

public class baraa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public baraa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(baraa baraa) {
        if (baraa == null) {
            return 0;
        }
        return baraa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_baraa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.baraa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.baraa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.baraa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.baraa_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_bara sWIGTYPE_p_p_bara) {
        JniSourceJNI.baraa_array_set(this.swigCPtr, this, SWIGTYPE_p_p_bara.getCPtr(sWIGTYPE_p_p_bara));
    }

    public SWIGTYPE_p_p_bara getArray() {
        long baraa_array_get = JniSourceJNI.baraa_array_get(this.swigCPtr, this);
        if (baraa_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_bara(baraa_array_get, false);
    }

    public baraa() {
        this(JniSourceJNI.new_baraa(), true);
    }
}
