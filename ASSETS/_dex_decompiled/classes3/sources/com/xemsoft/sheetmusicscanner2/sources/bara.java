package com.xemsoft.sheetmusicscanner2.sources;

public class bara {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public bara(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(bara bara) {
        if (bara == null) {
            return 0;
        }
        return bara.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_bara(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.bara_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.bara_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.bara_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.bara_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_bar sWIGTYPE_p_p_bar) {
        JniSourceJNI.bara_array_set(this.swigCPtr, this, SWIGTYPE_p_p_bar.getCPtr(sWIGTYPE_p_p_bar));
    }

    public SWIGTYPE_p_p_bar getArray() {
        long bara_array_get = JniSourceJNI.bara_array_get(this.swigCPtr, this);
        if (bara_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_bar(bara_array_get, false);
    }

    public bara() {
        this(JniSourceJNI.new_bara(), true);
    }
}
