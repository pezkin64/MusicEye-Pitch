package com.xemsoft.sheetmusicscanner2.sources;

public class timeSignature {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public timeSignature(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(timeSignature timesignature) {
        if (timesignature == null) {
            return 0;
        }
        return timesignature.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_timeSignature(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setType(symbolType symboltype) {
        JniSourceJNI.timeSignature_type_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getType() {
        return symbolType.swigToEnum(JniSourceJNI.timeSignature_type_get(this.swigCPtr, this));
    }

    public void setBeats(int i) {
        JniSourceJNI.timeSignature_beats_set(this.swigCPtr, this, i);
    }

    public int getBeats() {
        return JniSourceJNI.timeSignature_beats_get(this.swigCPtr, this);
    }

    public void setBeatType(int i) {
        JniSourceJNI.timeSignature_beatType_set(this.swigCPtr, this, i);
    }

    public int getBeatType() {
        return JniSourceJNI.timeSignature_beatType_get(this.swigCPtr, this);
    }

    public timeSignature() {
        this(JniSourceJNI.new_timeSignature(), true);
    }
}
