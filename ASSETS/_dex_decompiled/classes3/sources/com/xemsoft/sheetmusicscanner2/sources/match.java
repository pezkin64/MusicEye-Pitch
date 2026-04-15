package com.xemsoft.sheetmusicscanner2.sources;

public class match {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public match(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(match match) {
        if (match == null) {
            return 0;
        }
        return match.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_match(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPrimType(primType primtype) {
        JniSourceJNI.match_primType_set(this.swigCPtr, this, primtype.swigValue());
    }

    public primType getPrimType() {
        return primType.swigToEnum(JniSourceJNI.match_primType_get(this.swigCPtr, this));
    }

    public void setScore(int i) {
        JniSourceJNI.match_score_set(this.swigCPtr, this, i);
    }

    public int getScore() {
        return JniSourceJNI.match_score_get(this.swigCPtr, this);
    }

    public match() {
        this(JniSourceJNI.new_match(), true);
    }
}
