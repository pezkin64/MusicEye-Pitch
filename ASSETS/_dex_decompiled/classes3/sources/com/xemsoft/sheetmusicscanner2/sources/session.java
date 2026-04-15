package com.xemsoft.sheetmusicscanner2.sources;

public class session {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public session(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(session session) {
        if (session == null) {
            return 0;
        }
        return session.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_session(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSerializerVersionAtCreation(double d) {
        JniSourceJNI.session_serializerVersionAtCreation_set(this.swigCPtr, this, d);
    }

    public double getSerializerVersionAtCreation() {
        return JniSourceJNI.session_serializerVersionAtCreation_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniSourceJNI.session_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.session_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.session_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.session_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_score sWIGTYPE_p_p_score) {
        JniSourceJNI.session_array_set(this.swigCPtr, this, SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score));
    }

    public SWIGTYPE_p_p_score getArray() {
        long session_array_get = JniSourceJNI.session_array_get(this.swigCPtr, this);
        if (session_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_score(session_array_get, false);
    }

    public session() {
        this(JniSourceJNI.new_session(), true);
    }
}
