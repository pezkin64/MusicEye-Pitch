package com.xemsoft.sheetmusicscanner2.sources;

public class bar {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public bar(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(bar bar) {
        if (bar == null) {
            return 0;
        }
        return bar.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_bar(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setOffset(double d) {
        JniSourceJNI.bar_offset_set(this.swigCPtr, this, d);
    }

    public double getOffset() {
        return JniSourceJNI.bar_offset_get(this.swigCPtr, this);
    }

    public void setLength(double d) {
        JniSourceJNI.bar_length_set(this.swigCPtr, this, d);
    }

    public double getLength() {
        return JniSourceJNI.bar_length_get(this.swigCPtr, this);
    }

    public void setBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.bar_box_set(this.swigCPtr, this, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public SWIGTYPE_p_BOX getBox() {
        long bar_box_get = JniSourceJNI.bar_box_get(this.swigCPtr, this);
        if (bar_box_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(bar_box_get, false);
    }

    public void setIsWholeRestOnly(int i) {
        JniSourceJNI.bar_isWholeRestOnly_set(this.swigCPtr, this, i);
    }

    public int getIsWholeRestOnly() {
        return JniSourceJNI.bar_isWholeRestOnly_get(this.swigCPtr, this);
    }

    public void setClef(symbolType symboltype) {
        JniSourceJNI.bar_clef_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getClef() {
        return symbolType.swigToEnum(JniSourceJNI.bar_clef_get(this.swigCPtr, this));
    }

    public void setKeySignature(keySignature keysignature) {
        JniSourceJNI.bar_keySignature_set(this.swigCPtr, this, keySignature.getCPtr(keysignature), keysignature);
    }

    public keySignature getKeySignature() {
        long bar_keySignature_get = JniSourceJNI.bar_keySignature_get(this.swigCPtr, this);
        if (bar_keySignature_get == 0) {
            return null;
        }
        return new keySignature(bar_keySignature_get, false);
    }

    public void setTimeSignature(timeSignature timesignature) {
        JniSourceJNI.bar_timeSignature_set(this.swigCPtr, this, timeSignature.getCPtr(timesignature), timesignature);
    }

    public timeSignature getTimeSignature() {
        long bar_timeSignature_get = JniSourceJNI.bar_timeSignature_get(this.swigCPtr, this);
        if (bar_timeSignature_get == 0) {
            return null;
        }
        return new timeSignature(bar_timeSignature_get, false);
    }

    public void setNalloc(int i) {
        JniSourceJNI.bar_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniSourceJNI.bar_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniSourceJNI.bar_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniSourceJNI.bar_n_get(this.swigCPtr, this);
    }

    public void setTimepoints(SWIGTYPE_p_p_timepoint sWIGTYPE_p_p_timepoint) {
        JniSourceJNI.bar_timepoints_set(this.swigCPtr, this, SWIGTYPE_p_p_timepoint.getCPtr(sWIGTYPE_p_p_timepoint));
    }

    public SWIGTYPE_p_p_timepoint getTimepoints() {
        long bar_timepoints_get = JniSourceJNI.bar_timepoints_get(this.swigCPtr, this);
        if (bar_timepoints_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_timepoint(bar_timepoints_get, false);
    }

    public bar() {
        this(JniSourceJNI.new_bar(), true);
    }
}
