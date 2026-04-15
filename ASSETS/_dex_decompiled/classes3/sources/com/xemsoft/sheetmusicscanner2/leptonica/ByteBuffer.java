package com.xemsoft.sheetmusicscanner2.leptonica;

public class ByteBuffer {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ByteBuffer(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return 0;
        }
        return byteBuffer.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_ByteBuffer(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.ByteBuffer_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.ByteBuffer_nalloc_get(this.swigCPtr, this);
    }

    public void setN(int i) {
        JniLeptonicaJNI.ByteBuffer_n_set(this.swigCPtr, this, i);
    }

    public int getN() {
        return JniLeptonicaJNI.ByteBuffer_n_get(this.swigCPtr, this);
    }

    public void setNwritten(int i) {
        JniLeptonicaJNI.ByteBuffer_nwritten_set(this.swigCPtr, this, i);
    }

    public int getNwritten() {
        return JniLeptonicaJNI.ByteBuffer_nwritten_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.ByteBuffer_array_set(this.swigCPtr, this, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public SWIGTYPE_p_unsigned_char getArray() {
        long ByteBuffer_array_get = JniLeptonicaJNI.ByteBuffer_array_get(this.swigCPtr, this);
        if (ByteBuffer_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(ByteBuffer_array_get, false);
    }

    public ByteBuffer() {
        this(JniLeptonicaJNI.new_ByteBuffer(), true);
    }
}
