package com.xemsoft.sheetmusicscanner2.leptonica;

public class BMP_InfoHeader {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BMP_InfoHeader(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(BMP_InfoHeader bMP_InfoHeader) {
        if (bMP_InfoHeader == null) {
            return 0;
        }
        return bMP_InfoHeader.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_BMP_InfoHeader(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setBiSize(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biSize_set(this.swigCPtr, this, i);
    }

    public int getBiSize() {
        return JniLeptonicaJNI.BMP_InfoHeader_biSize_get(this.swigCPtr, this);
    }

    public void setBiWidth(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biWidth_set(this.swigCPtr, this, i);
    }

    public int getBiWidth() {
        return JniLeptonicaJNI.BMP_InfoHeader_biWidth_get(this.swigCPtr, this);
    }

    public void setBiHeight(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biHeight_set(this.swigCPtr, this, i);
    }

    public int getBiHeight() {
        return JniLeptonicaJNI.BMP_InfoHeader_biHeight_get(this.swigCPtr, this);
    }

    public void setBiPlanes(short s) {
        JniLeptonicaJNI.BMP_InfoHeader_biPlanes_set(this.swigCPtr, this, s);
    }

    public short getBiPlanes() {
        return JniLeptonicaJNI.BMP_InfoHeader_biPlanes_get(this.swigCPtr, this);
    }

    public void setBiBitCount(short s) {
        JniLeptonicaJNI.BMP_InfoHeader_biBitCount_set(this.swigCPtr, this, s);
    }

    public short getBiBitCount() {
        return JniLeptonicaJNI.BMP_InfoHeader_biBitCount_get(this.swigCPtr, this);
    }

    public void setBiCompression(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biCompression_set(this.swigCPtr, this, i);
    }

    public int getBiCompression() {
        return JniLeptonicaJNI.BMP_InfoHeader_biCompression_get(this.swigCPtr, this);
    }

    public void setBiSizeImage(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biSizeImage_set(this.swigCPtr, this, i);
    }

    public int getBiSizeImage() {
        return JniLeptonicaJNI.BMP_InfoHeader_biSizeImage_get(this.swigCPtr, this);
    }

    public void setBiXPelsPerMeter(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biXPelsPerMeter_set(this.swigCPtr, this, i);
    }

    public int getBiXPelsPerMeter() {
        return JniLeptonicaJNI.BMP_InfoHeader_biXPelsPerMeter_get(this.swigCPtr, this);
    }

    public void setBiYPelsPerMeter(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biYPelsPerMeter_set(this.swigCPtr, this, i);
    }

    public int getBiYPelsPerMeter() {
        return JniLeptonicaJNI.BMP_InfoHeader_biYPelsPerMeter_get(this.swigCPtr, this);
    }

    public void setBiClrUsed(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biClrUsed_set(this.swigCPtr, this, i);
    }

    public int getBiClrUsed() {
        return JniLeptonicaJNI.BMP_InfoHeader_biClrUsed_get(this.swigCPtr, this);
    }

    public void setBiClrImportant(int i) {
        JniLeptonicaJNI.BMP_InfoHeader_biClrImportant_set(this.swigCPtr, this, i);
    }

    public int getBiClrImportant() {
        return JniLeptonicaJNI.BMP_InfoHeader_biClrImportant_get(this.swigCPtr, this);
    }

    public BMP_InfoHeader() {
        this(JniLeptonicaJNI.new_BMP_InfoHeader(), true);
    }
}
