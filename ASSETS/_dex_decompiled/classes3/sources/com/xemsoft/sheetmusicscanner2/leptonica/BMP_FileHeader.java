package com.xemsoft.sheetmusicscanner2.leptonica;

public class BMP_FileHeader {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BMP_FileHeader(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(BMP_FileHeader bMP_FileHeader) {
        if (bMP_FileHeader == null) {
            return 0;
        }
        return bMP_FileHeader.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_BMP_FileHeader(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setBfType(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfType_set(this.swigCPtr, this, s);
    }

    public short getBfType() {
        return JniLeptonicaJNI.BMP_FileHeader_bfType_get(this.swigCPtr, this);
    }

    public void setBfSize(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfSize_set(this.swigCPtr, this, s);
    }

    public short getBfSize() {
        return JniLeptonicaJNI.BMP_FileHeader_bfSize_get(this.swigCPtr, this);
    }

    public void setBfFill1(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfFill1_set(this.swigCPtr, this, s);
    }

    public short getBfFill1() {
        return JniLeptonicaJNI.BMP_FileHeader_bfFill1_get(this.swigCPtr, this);
    }

    public void setBfReserved1(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfReserved1_set(this.swigCPtr, this, s);
    }

    public short getBfReserved1() {
        return JniLeptonicaJNI.BMP_FileHeader_bfReserved1_get(this.swigCPtr, this);
    }

    public void setBfReserved2(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfReserved2_set(this.swigCPtr, this, s);
    }

    public short getBfReserved2() {
        return JniLeptonicaJNI.BMP_FileHeader_bfReserved2_get(this.swigCPtr, this);
    }

    public void setBfOffBits(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfOffBits_set(this.swigCPtr, this, s);
    }

    public short getBfOffBits() {
        return JniLeptonicaJNI.BMP_FileHeader_bfOffBits_get(this.swigCPtr, this);
    }

    public void setBfFill2(short s) {
        JniLeptonicaJNI.BMP_FileHeader_bfFill2_set(this.swigCPtr, this, s);
    }

    public short getBfFill2() {
        return JniLeptonicaJNI.BMP_FileHeader_bfFill2_get(this.swigCPtr, this);
    }

    public BMP_FileHeader() {
        this(JniLeptonicaJNI.new_BMP_FileHeader(), true);
    }
}
