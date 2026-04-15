package com.xemsoft.sheetmusicscanner2.leptonica;

public class RGBA_Quad {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public RGBA_Quad(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(RGBA_Quad rGBA_Quad) {
        if (rGBA_Quad == null) {
            return 0;
        }
        return rGBA_Quad.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_RGBA_Quad(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setBlue(short s) {
        JniLeptonicaJNI.RGBA_Quad_blue_set(this.swigCPtr, this, s);
    }

    public short getBlue() {
        return JniLeptonicaJNI.RGBA_Quad_blue_get(this.swigCPtr, this);
    }

    public void setGreen(short s) {
        JniLeptonicaJNI.RGBA_Quad_green_set(this.swigCPtr, this, s);
    }

    public short getGreen() {
        return JniLeptonicaJNI.RGBA_Quad_green_get(this.swigCPtr, this);
    }

    public void setRed(short s) {
        JniLeptonicaJNI.RGBA_Quad_red_set(this.swigCPtr, this, s);
    }

    public short getRed() {
        return JniLeptonicaJNI.RGBA_Quad_red_get(this.swigCPtr, this);
    }

    public void setAlpha(short s) {
        JniLeptonicaJNI.RGBA_Quad_alpha_set(this.swigCPtr, this, s);
    }

    public short getAlpha() {
        return JniLeptonicaJNI.RGBA_Quad_alpha_get(this.swigCPtr, this);
    }

    public RGBA_Quad() {
        this(JniLeptonicaJNI.new_RGBA_Quad(), true);
    }
}
