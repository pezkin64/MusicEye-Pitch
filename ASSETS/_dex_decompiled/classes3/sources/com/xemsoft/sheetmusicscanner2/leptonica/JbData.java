package com.xemsoft.sheetmusicscanner2.leptonica;

public class JbData {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public JbData(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(JbData jbData) {
        if (jbData == null) {
            return 0;
        }
        return jbData.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_JbData(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(Pix pix) {
        JniLeptonicaJNI.JbData_pix_set(this.swigCPtr, this, Pix.getCPtr(pix), pix);
    }

    public Pix getPix() {
        long JbData_pix_get = JniLeptonicaJNI.JbData_pix_get(this.swigCPtr, this);
        if (JbData_pix_get == 0) {
            return null;
        }
        return new Pix(JbData_pix_get, false);
    }

    public void setNpages(int i) {
        JniLeptonicaJNI.JbData_npages_set(this.swigCPtr, this, i);
    }

    public int getNpages() {
        return JniLeptonicaJNI.JbData_npages_get(this.swigCPtr, this);
    }

    public void setW(int i) {
        JniLeptonicaJNI.JbData_w_set(this.swigCPtr, this, i);
    }

    public int getW() {
        return JniLeptonicaJNI.JbData_w_get(this.swigCPtr, this);
    }

    public void setH(int i) {
        JniLeptonicaJNI.JbData_h_set(this.swigCPtr, this, i);
    }

    public int getH() {
        return JniLeptonicaJNI.JbData_h_get(this.swigCPtr, this);
    }

    public void setNclass(int i) {
        JniLeptonicaJNI.JbData_nclass_set(this.swigCPtr, this, i);
    }

    public int getNclass() {
        return JniLeptonicaJNI.JbData_nclass_get(this.swigCPtr, this);
    }

    public void setLatticew(int i) {
        JniLeptonicaJNI.JbData_latticew_set(this.swigCPtr, this, i);
    }

    public int getLatticew() {
        return JniLeptonicaJNI.JbData_latticew_get(this.swigCPtr, this);
    }

    public void setLatticeh(int i) {
        JniLeptonicaJNI.JbData_latticeh_set(this.swigCPtr, this, i);
    }

    public int getLatticeh() {
        return JniLeptonicaJNI.JbData_latticeh_get(this.swigCPtr, this);
    }

    public void setNaclass(Numa numa) {
        JniLeptonicaJNI.JbData_naclass_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNaclass() {
        long JbData_naclass_get = JniLeptonicaJNI.JbData_naclass_get(this.swigCPtr, this);
        if (JbData_naclass_get == 0) {
            return null;
        }
        return new Numa(JbData_naclass_get, false);
    }

    public void setNapage(Numa numa) {
        JniLeptonicaJNI.JbData_napage_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getNapage() {
        long JbData_napage_get = JniLeptonicaJNI.JbData_napage_get(this.swigCPtr, this);
        if (JbData_napage_get == 0) {
            return null;
        }
        return new Numa(JbData_napage_get, false);
    }

    public void setPtaul(Pta pta) {
        JniLeptonicaJNI.JbData_ptaul_set(this.swigCPtr, this, Pta.getCPtr(pta), pta);
    }

    public Pta getPtaul() {
        long JbData_ptaul_get = JniLeptonicaJNI.JbData_ptaul_get(this.swigCPtr, this);
        if (JbData_ptaul_get == 0) {
            return null;
        }
        return new Pta(JbData_ptaul_get, false);
    }

    public JbData() {
        this(JniLeptonicaJNI.new_JbData(), true);
    }
}
