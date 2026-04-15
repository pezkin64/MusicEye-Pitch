package com.xemsoft.sheetmusicscanner2.leptonica;

public class L_Sudoku {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public L_Sudoku(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(L_Sudoku l_Sudoku) {
        if (l_Sudoku == null) {
            return 0;
        }
        return l_Sudoku.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_L_Sudoku(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setNum(int i) {
        JniLeptonicaJNI.L_Sudoku_num_set(this.swigCPtr, this, i);
    }

    public int getNum() {
        return JniLeptonicaJNI.L_Sudoku_num_get(this.swigCPtr, this);
    }

    public void setLocs(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Sudoku_locs_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getLocs() {
        long L_Sudoku_locs_get = JniLeptonicaJNI.L_Sudoku_locs_get(this.swigCPtr, this);
        if (L_Sudoku_locs_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Sudoku_locs_get, false);
    }

    public void setCurrent(int i) {
        JniLeptonicaJNI.L_Sudoku_current_set(this.swigCPtr, this, i);
    }

    public int getCurrent() {
        return JniLeptonicaJNI.L_Sudoku_current_get(this.swigCPtr, this);
    }

    public void setInit(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Sudoku_init_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getInit() {
        long L_Sudoku_init_get = JniLeptonicaJNI.L_Sudoku_init_get(this.swigCPtr, this);
        if (L_Sudoku_init_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Sudoku_init_get, false);
    }

    public void setState(SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.L_Sudoku_state_set(this.swigCPtr, this, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public SWIGTYPE_p_int getState() {
        long L_Sudoku_state_get = JniLeptonicaJNI.L_Sudoku_state_get(this.swigCPtr, this);
        if (L_Sudoku_state_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(L_Sudoku_state_get, false);
    }

    public void setNguess(int i) {
        JniLeptonicaJNI.L_Sudoku_nguess_set(this.swigCPtr, this, i);
    }

    public int getNguess() {
        return JniLeptonicaJNI.L_Sudoku_nguess_get(this.swigCPtr, this);
    }

    public void setFinished(int i) {
        JniLeptonicaJNI.L_Sudoku_finished_set(this.swigCPtr, this, i);
    }

    public int getFinished() {
        return JniLeptonicaJNI.L_Sudoku_finished_get(this.swigCPtr, this);
    }

    public void setFailure(int i) {
        JniLeptonicaJNI.L_Sudoku_failure_set(this.swigCPtr, this, i);
    }

    public int getFailure() {
        return JniLeptonicaJNI.L_Sudoku_failure_get(this.swigCPtr, this);
    }

    public L_Sudoku() {
        this(JniLeptonicaJNI.new_L_Sudoku(), true);
    }
}
