package com.xemsoft.sheetmusicscanner2.sources;

public class templ {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public templ(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(templ templ) {
        if (templ == null) {
            return 0;
        }
        return templ.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_templ(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.templ_pix_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getPix() {
        long templ_pix_get = JniSourceJNI.templ_pix_get(this.swigCPtr, this);
        if (templ_pix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(templ_pix_get, false);
    }

    public void setType(primType primtype) {
        JniSourceJNI.templ_type_set(this.swigCPtr, this, primtype.swigValue());
    }

    public primType getType() {
        return primType.swigToEnum(JniSourceJNI.templ_type_get(this.swigCPtr, this));
    }

    public void setArea(int i) {
        JniSourceJNI.templ_area_set(this.swigCPtr, this, i);
    }

    public int getArea() {
        return JniSourceJNI.templ_area_get(this.swigCPtr, this);
    }

    public void setRefCount(int i) {
        JniSourceJNI.templ_refCount_set(this.swigCPtr, this, i);
    }

    public int getRefCount() {
        return JniSourceJNI.templ_refCount_get(this.swigCPtr, this);
    }

    public templ() {
        this(JniSourceJNI.new_templ(), true);
    }
}
