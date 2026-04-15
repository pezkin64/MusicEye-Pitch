package com.xemsoft.sheetmusicscanner2.sources;

public class staff {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public staff(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(staff staff) {
        if (staff == null) {
            return 0;
        }
        return staff.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_staff(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setIndex(int i) {
        JniSourceJNI.staff_index_set(this.swigCPtr, this, i);
    }

    public int getIndex() {
        return JniSourceJNI.staff_index_get(this.swigCPtr, this);
    }

    public void setLines(SWIGTYPE_p_p_staffline sWIGTYPE_p_p_staffline) {
        JniSourceJNI.staff_lines_set(this.swigCPtr, this, SWIGTYPE_p_p_staffline.getCPtr(sWIGTYPE_p_p_staffline));
    }

    public SWIGTYPE_p_p_staffline getLines() {
        long staff_lines_get = JniSourceJNI.staff_lines_get(this.swigCPtr, this);
        if (staff_lines_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_staffline(staff_lines_get, false);
    }

    public void setXOri(int i) {
        JniSourceJNI.staff_xOri_set(this.swigCPtr, this, i);
    }

    public int getXOri() {
        return JniSourceJNI.staff_xOri_get(this.swigCPtr, this);
    }

    public void setYOri(int i) {
        JniSourceJNI.staff_yOri_set(this.swigCPtr, this, i);
    }

    public int getYOri() {
        return JniSourceJNI.staff_yOri_get(this.swigCPtr, this);
    }

    public staff() {
        this(JniSourceJNI.new_staff(), true);
    }
}
