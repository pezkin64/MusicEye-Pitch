package com.xemsoft.sheetmusicscanner2.sources;

public class staffline {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public staffline(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(staffline staffline) {
        if (staffline == null) {
            return 0;
        }
        return staffline.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_staffline(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setXLeft(int i) {
        JniSourceJNI.staffline_xLeft_set(this.swigCPtr, this, i);
    }

    public int getXLeft() {
        return JniSourceJNI.staffline_xLeft_get(this.swigCPtr, this);
    }

    public void setHWidth(int i) {
        JniSourceJNI.staffline_hWidth_set(this.swigCPtr, this, i);
    }

    public int getHWidth() {
        return JniSourceJNI.staffline_hWidth_get(this.swigCPtr, this);
    }

    public void setTopYs(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.staffline_topYs_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getTopYs() {
        long staffline_topYs_get = JniSourceJNI.staffline_topYs_get(this.swigCPtr, this);
        if (staffline_topYs_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(staffline_topYs_get, false);
    }

    public void setBottomYs(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.staffline_bottomYs_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBottomYs() {
        long staffline_bottomYs_get = JniSourceJNI.staffline_bottomYs_get(this.swigCPtr, this);
        if (staffline_bottomYs_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(staffline_bottomYs_get, false);
    }

    public staffline() {
        this(JniSourceJNI.new_staffline(), true);
    }
}
