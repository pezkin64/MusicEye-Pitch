package com.xemsoft.sheetmusicscanner2.sources;

public class timepoint {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public timepoint(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(timepoint timepoint) {
        if (timepoint == null) {
            return 0;
        }
        return timepoint.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_timepoint(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setStartTime(double d) {
        JniSourceJNI.timepoint_startTime_set(this.swigCPtr, this, d);
    }

    public double getStartTime() {
        return JniSourceJNI.timepoint_startTime_get(this.swigCPtr, this);
    }

    public void setTimeToNext(double d) {
        JniSourceJNI.timepoint_timeToNext_set(this.swigCPtr, this, d);
    }

    public double getTimeToNext() {
        return JniSourceJNI.timepoint_timeToNext_get(this.swigCPtr, this);
    }

    public void setX0(int i) {
        JniSourceJNI.timepoint_x0_set(this.swigCPtr, this, i);
    }

    public int getX0() {
        return JniSourceJNI.timepoint_x0_get(this.swigCPtr, this);
    }

    public void setX1(int i) {
        JniSourceJNI.timepoint_x1_set(this.swigCPtr, this, i);
    }

    public int getX1() {
        return JniSourceJNI.timepoint_x1_get(this.swigCPtr, this);
    }

    public void setIsVerified(int i) {
        JniSourceJNI.timepoint_isVerified_set(this.swigCPtr, this, i);
    }

    public int getIsVerified() {
        return JniSourceJNI.timepoint_isVerified_get(this.swigCPtr, this);
    }

    public void setDoesOwnSounds(int i) {
        JniSourceJNI.timepoint_doesOwnSounds_set(this.swigCPtr, this, i);
    }

    public int getDoesOwnSounds() {
        return JniSourceJNI.timepoint_doesOwnSounds_get(this.swigCPtr, this);
    }

    public void setNalloc(int i) {
        JniSourceJNI.timepoint_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.timepoint_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.timepoint_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.timepoint_n_get(this.swigCPtr, this);
    }

    public void setSounds(SWIGTYPE_p_p_sound sWIGTYPE_p_p_sound) {
        JniSourceJNI.timepoint_sounds_set(this.swigCPtr, this, SWIGTYPE_p_p_sound.getCPtr(sWIGTYPE_p_p_sound));
    }

    public SWIGTYPE_p_p_sound getSounds() {
        long timepoint_sounds_get = JniSourceJNI.timepoint_sounds_get(this.swigCPtr, this);
        if (timepoint_sounds_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_sound(timepoint_sounds_get, false);
    }

    public void setBeamGroupIds(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.timepoint_beamGroupIds_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBeamGroupIds() {
        long timepoint_beamGroupIds_get = JniSourceJNI.timepoint_beamGroupIds_get(this.swigCPtr, this);
        if (timepoint_beamGroupIds_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(timepoint_beamGroupIds_get, false);
    }

    public void setBeamOrders(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.timepoint_beamOrders_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBeamOrders() {
        long timepoint_beamOrders_get = JniSourceJNI.timepoint_beamOrders_get(this.swigCPtr, this);
        if (timepoint_beamOrders_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(timepoint_beamOrders_get, false);
    }

    public timepoint() {
        this(JniSourceJNI.new_timepoint(), true);
    }
}
