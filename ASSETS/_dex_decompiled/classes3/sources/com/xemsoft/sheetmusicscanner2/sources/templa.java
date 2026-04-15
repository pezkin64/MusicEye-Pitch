package com.xemsoft.sheetmusicscanner2.sources;

public class templa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public templa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(templa templa) {
        if (templa == null) {
            return 0;
        }
        return templa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_templa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.templa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.templa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.templa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.templa_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_templ sWIGTYPE_p_p_templ) {
        JniSourceJNI.templa_array_set(this.swigCPtr, this, SWIGTYPE_p_p_templ.getCPtr(sWIGTYPE_p_p_templ));
    }

    public SWIGTYPE_p_p_templ getArray() {
        long templa_array_get = JniSourceJNI.templa_array_get(this.swigCPtr, this);
        if (templa_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_templ(templa_array_get, false);
    }

    public templa() {
        this(JniSourceJNI.new_templa(), true);
    }
}
