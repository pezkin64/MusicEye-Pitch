package com.xemsoft.sheetmusicscanner2.sources;

public class dicipair {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public dicipair(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(dicipair dicipair) {
        if (dicipair == null) {
            return 0;
        }
        return dicipair.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_dicipair(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNext(dicipair dicipair) {
        JniSourceJNI.dicipair_next_set(this.swigCPtr, this, getCPtr(dicipair), dicipair);
    }

    public dicipair getNext() {
        long dicipair_next_get = JniSourceJNI.dicipair_next_get(this.swigCPtr, this);
        if (dicipair_next_get == 0) {
            return null;
        }
        return new dicipair(dicipair_next_get, false);
    }

    public void setKey(int i) {
        JniSourceJNI.dicipair_key_set(this.swigCPtr, this, i);
    }

    public int getKey() {
        return JniSourceJNI.dicipair_key_get(this.swigCPtr, this);
    }

    public void setValue(int i) {
        JniSourceJNI.dicipair_value_set(this.swigCPtr, this, i);
    }

    public int getValue() {
        return JniSourceJNI.dicipair_value_get(this.swigCPtr, this);
    }

    public dicipair() {
        this(JniSourceJNI.new_dicipair(), true);
    }
}
