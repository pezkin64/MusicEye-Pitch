package com.xemsoft.sheetmusicscanner2.sources;

public class keySignature {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public keySignature(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(keySignature keysignature) {
        if (keysignature == null) {
            return 0;
        }
        return keysignature.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_keySignature(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setIsSharps(int i) {
        JniSourceJNI.keySignature_isSharps_set(this.swigCPtr, this, i);
    }

    public int getIsSharps() {
        return JniSourceJNI.keySignature_isSharps_get(this.swigCPtr, this);
    }

    public void setCount(int i) {
        JniSourceJNI.keySignature_count_set(this.swigCPtr, this, i);
    }

    public int getCount() {
        return JniSourceJNI.keySignature_count_get(this.swigCPtr, this);
    }

    public keySignature() {
        this(JniSourceJNI.new_keySignature(), true);
    }
}
