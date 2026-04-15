package com.xemsoft.sheetmusicscanner2.leptonica;

public class PixColormap {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PixColormap(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PixColormap pixColormap) {
        if (pixColormap == null) {
            return 0;
        }
        return pixColormap.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_PixColormap(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setArray(SWIGTYPE_p_void sWIGTYPE_p_void) {
        JniLeptonicaJNI.PixColormap_array_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getArray() {
        long PixColormap_array_get = JniLeptonicaJNI.PixColormap_array_get(this.swigCPtr, this);
        if (PixColormap_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(PixColormap_array_get, false);
    }

    public void setDepth(int i) {
        JniLeptonicaJNI.PixColormap_depth_set(this.swigCPtr, this, i);
    }

    public int getDepth() {
        return JniLeptonicaJNI.PixColormap_depth_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.PixColormap_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.PixColormap_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.PixColormap_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.PixColormap_n_get(this.swigCPtr, this);
    }

    public PixColormap() {
        this(JniLeptonicaJNI.new_PixColormap(), true);
    }
}
