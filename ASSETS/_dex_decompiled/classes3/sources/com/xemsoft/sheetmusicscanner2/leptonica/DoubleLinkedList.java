package com.xemsoft.sheetmusicscanner2.leptonica;

public class DoubleLinkedList {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public DoubleLinkedList(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(DoubleLinkedList doubleLinkedList) {
        if (doubleLinkedList == null) {
            return 0;
        }
        return doubleLinkedList.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_DoubleLinkedList(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPrev(DoubleLinkedList doubleLinkedList) {
        JniLeptonicaJNI.DoubleLinkedList_prev_set(this.swigCPtr, this, getCPtr(doubleLinkedList), doubleLinkedList);
    }

    public DoubleLinkedList getPrev() {
        long DoubleLinkedList_prev_get = JniLeptonicaJNI.DoubleLinkedList_prev_get(this.swigCPtr, this);
        if (DoubleLinkedList_prev_get == 0) {
            return null;
        }
        return new DoubleLinkedList(DoubleLinkedList_prev_get, false);
    }

    public void setNext(DoubleLinkedList doubleLinkedList) {
        JniLeptonicaJNI.DoubleLinkedList_next_set(this.swigCPtr, this, getCPtr(doubleLinkedList), doubleLinkedList);
    }

    public DoubleLinkedList getNext() {
        long DoubleLinkedList_next_get = JniLeptonicaJNI.DoubleLinkedList_next_get(this.swigCPtr, this);
        if (DoubleLinkedList_next_get == 0) {
            return null;
        }
        return new DoubleLinkedList(DoubleLinkedList_next_get, false);
    }

    public void setData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        JniLeptonicaJNI.DoubleLinkedList_data_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SWIGTYPE_p_void getData() {
        long DoubleLinkedList_data_get = JniLeptonicaJNI.DoubleLinkedList_data_get(this.swigCPtr, this);
        if (DoubleLinkedList_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(DoubleLinkedList_data_get, false);
    }

    public DoubleLinkedList() {
        this(JniLeptonicaJNI.new_DoubleLinkedList(), true);
    }
}
