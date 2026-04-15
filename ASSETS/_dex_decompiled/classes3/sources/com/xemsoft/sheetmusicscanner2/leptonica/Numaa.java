package com.xemsoft.sheetmusicscanner2.leptonica;

public class Numaa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Numaa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(Numaa numaa) {
        if (numaa == null) {
            return 0;
        }
        return numaa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_Numaa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.Numaa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.Numaa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.Numaa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.Numaa_n_get(this.swigCPtr, this);
    }

    public void setNuma(SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        JniLeptonicaJNI.Numaa_numa_set(this.swigCPtr, this, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public SWIGTYPE_p_p_Numa getNuma() {
        long Numaa_numa_get = JniLeptonicaJNI.Numaa_numa_get(this.swigCPtr, this);
        if (Numaa_numa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Numa(Numaa_numa_get, false);
    }

    public Numaa() {
        this(JniLeptonicaJNI.new_Numaa(), true);
    }
}
