package com.xemsoft.sheetmusicscanner2.sources;

public class staffa {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public staffa(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(staffa staffa) {
        if (staffa == null) {
            return 0;
        }
        return staffa.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_staffa(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniSourceJNI.staffa_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.staffa_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.staffa_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.staffa_n_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_staff sWIGTYPE_p_p_staff) {
        JniSourceJNI.staffa_array_set(this.swigCPtr, this, SWIGTYPE_p_p_staff.getCPtr(sWIGTYPE_p_p_staff));
    }

    public SWIGTYPE_p_p_staff getArray() {
        long staffa_array_get = JniSourceJNI.staffa_array_get(this.swigCPtr, this);
        if (staffa_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_staff(staffa_array_get, false);
    }

    public staffa() {
        this(JniSourceJNI.new_staffa(), true);
    }
}
