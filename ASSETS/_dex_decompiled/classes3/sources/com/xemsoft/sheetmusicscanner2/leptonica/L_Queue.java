package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Queue {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Queue(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Queue l_Queue) {
        if (l_Queue == null) {
            return 0;
        }
        return l_Queue.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Queue(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNalloc(int i) {
        JniLeptonicaJNI.L_Queue_nalloc_set(this.swigCPtr, this, i);
    }

    public int getNalloc() {
        return JniLeptonicaJNI.L_Queue_nalloc_get(this.swigCPtr, this);
    }

    public void setNhead(int i) {
        JniLeptonicaJNI.L_Queue_nhead_set(this.swigCPtr, this, i);
    }

    public int getNhead() {
        return JniLeptonicaJNI.L_Queue_nhead_get(this.swigCPtr, this);
    }

    public void setNelem(int i) {
        JniLeptonicaJNI.L_Queue_nelem_set(this.swigCPtr, this, i);
    }

    public int getNelem() {
        return JniLeptonicaJNI.L_Queue_nelem_get(this.swigCPtr, this);
    }

    public void setArray(SWIGTYPE_p_p_void sWIGTYPE_p_p_void) {
        JniLeptonicaJNI.L_Queue_array_set(this.swigCPtr, this, SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void));
    }

    public SWIGTYPE_p_p_void getArray() {
        long L_Queue_array_get = JniLeptonicaJNI.L_Queue_array_get(this.swigCPtr, this);
        if (L_Queue_array_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(L_Queue_array_get, false);
    }

    public void setStack(L_Stack l_Stack) {
        JniLeptonicaJNI.L_Queue_stack_set(this.swigCPtr, this, L_Stack.getCPtr(l_Stack), l_Stack);
    }

    public L_Stack getStack() {
        long L_Queue_stack_get = JniLeptonicaJNI.L_Queue_stack_get(this.swigCPtr, this);
        if (L_Queue_stack_get == 0) {
            return null;
        }
        return new L_Stack(L_Queue_stack_get, false);
    }

    public L_Queue() {
        this(JniLeptonicaJNI.new_L_Queue(), true);
    }
}
