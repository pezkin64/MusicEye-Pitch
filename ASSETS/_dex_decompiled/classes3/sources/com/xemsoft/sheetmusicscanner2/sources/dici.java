package com.xemsoft.sheetmusicscanner2.sources;

public class dici {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public dici(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(dici dici) {
        if (dici == null) {
            return 0;
        }
        return dici.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_dici(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setHashSize(int i) {
        JniSourceJNI.dici_hashSize_set(this.swigCPtr, this, i);
    }

    public int getHashSize() {
        return JniSourceJNI.dici_hashSize_get(this.swigCPtr, this);
    }

    public void setHashTable(SWIGTYPE_p_p_dicipair sWIGTYPE_p_p_dicipair) {
        JniSourceJNI.dici_hashTable_set(this.swigCPtr, this, SWIGTYPE_p_p_dicipair.getCPtr(sWIGTYPE_p_p_dicipair));
    }

    public SWIGTYPE_p_p_dicipair getHashTable() {
        long dici_hashTable_get = JniSourceJNI.dici_hashTable_get(this.swigCPtr, this);
        if (dici_hashTable_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_dicipair(dici_hashTable_get, false);
    }

    public dici() {
        this(JniSourceJNI.new_dici(), true);
    }
}
