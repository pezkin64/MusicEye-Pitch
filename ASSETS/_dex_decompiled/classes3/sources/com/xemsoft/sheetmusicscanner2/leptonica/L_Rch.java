package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Rch {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Rch(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Rch l_Rch) {
        if (l_Rch == null) {
            return 0;
        }
        return l_Rch.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Rch(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setIndex(int i) {
        JniLeptonicaJNI.L_Rch_index_set(this.swigCPtr, this, i);
    }

    public int getIndex() {
        return JniLeptonicaJNI.L_Rch_index_get(this.swigCPtr, this);
    }

    public void setScore(float f) {
        JniLeptonicaJNI.L_Rch_score_set(this.swigCPtr, this, f);
    }

    public float getScore() {
        return JniLeptonicaJNI.L_Rch_score_get(this.swigCPtr, this);
    }

    public void setText(String str) {
        JniLeptonicaJNI.L_Rch_text_set(this.swigCPtr, this, str);
    }

    public String getText() {
        return JniLeptonicaJNI.L_Rch_text_get(this.swigCPtr, this);
    }

    public void setSample(int i) {
        JniLeptonicaJNI.L_Rch_sample_set(this.swigCPtr, this, i);
    }

    public int getSample() {
        return JniLeptonicaJNI.L_Rch_sample_get(this.swigCPtr, this);
    }

    public void setXloc(int i) {
        JniLeptonicaJNI.L_Rch_xloc_set(this.swigCPtr, this, i);
    }

    public int getXloc() {
        return JniLeptonicaJNI.L_Rch_xloc_get(this.swigCPtr, this);
    }

    public void setYloc(int i) {
        JniLeptonicaJNI.L_Rch_yloc_set(this.swigCPtr, this, i);
    }

    public int getYloc() {
        return JniLeptonicaJNI.L_Rch_yloc_get(this.swigCPtr, this);
    }

    public void setWidth(int i) {
        JniLeptonicaJNI.L_Rch_width_set(this.swigCPtr, this, i);
    }

    public int getWidth() {
        return JniLeptonicaJNI.L_Rch_width_get(this.swigCPtr, this);
    }

    public L_Rch() {
        this(JniLeptonicaJNI.new_L_Rch(), true);
    }
}
