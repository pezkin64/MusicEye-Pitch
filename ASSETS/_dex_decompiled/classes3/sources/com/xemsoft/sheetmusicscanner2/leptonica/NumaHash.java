package com.xemsoft.sheetmusicscanner2.leptonica;

public class NumaHash {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public NumaHash(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(NumaHash numaHash) {
        if (numaHash == null) {
            return 0;
        }
        return numaHash.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_NumaHash(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNbuckets(int i) {
        JniLeptonicaJNI.NumaHash_nbuckets_set(this.swigCPtr, this, i);
    }

    public int getNbuckets() {
        return JniLeptonicaJNI.NumaHash_nbuckets_get(this.swigCPtr, this);
    }

    public void setInitsize(int i) {
        JniLeptonicaJNI.NumaHash_initsize_set(this.swigCPtr, this, i);
    }

    public int getInitsize() {
        return JniLeptonicaJNI.NumaHash_initsize_get(this.swigCPtr, this);
    }

    public void setNuma(SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        JniLeptonicaJNI.NumaHash_numa_set(this.swigCPtr, this, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public SWIGTYPE_p_p_Numa getNuma() {
        long NumaHash_numa_get = JniLeptonicaJNI.NumaHash_numa_get(this.swigCPtr, this);
        if (NumaHash_numa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Numa(NumaHash_numa_get, false);
    }

    public NumaHash() {
        this(JniLeptonicaJNI.new_NumaHash(), true);
    }
}
