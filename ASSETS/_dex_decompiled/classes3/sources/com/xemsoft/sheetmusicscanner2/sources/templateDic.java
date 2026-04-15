package com.xemsoft.sheetmusicscanner2.sources;

public class templateDic {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public templateDic(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(templateDic templatedic) {
        if (templatedic == null) {
            return 0;
        }
        return templatedic.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_templateDic(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setTypea(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.templateDic_typea_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getTypea() {
        long templateDic_typea_get = JniSourceJNI.templateDic_typea_get(this.swigCPtr, this);
        if (templateDic_typea_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(templateDic_typea_get, false);
    }

    public void setPixaa(SWIGTYPE_p_PIXAA sWIGTYPE_p_PIXAA) {
        JniSourceJNI.templateDic_pixaa_set(this.swigCPtr, this, SWIGTYPE_p_PIXAA.getCPtr(sWIGTYPE_p_PIXAA));
    }

    public SWIGTYPE_p_PIXAA getPixaa() {
        long templateDic_pixaa_get = JniSourceJNI.templateDic_pixaa_get(this.swigCPtr, this);
        if (templateDic_pixaa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXAA(templateDic_pixaa_get, false);
    }

    public void setAreasa(SWIGTYPE_p_NUMAA sWIGTYPE_p_NUMAA) {
        JniSourceJNI.templateDic_areasa_set(this.swigCPtr, this, SWIGTYPE_p_NUMAA.getCPtr(sWIGTYPE_p_NUMAA));
    }

    public SWIGTYPE_p_NUMAA getAreasa() {
        long templateDic_areasa_get = JniSourceJNI.templateDic_areasa_get(this.swigCPtr, this);
        if (templateDic_areasa_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMAA(templateDic_areasa_get, false);
    }

    public templateDic() {
        this(JniSourceJNI.new_templateDic(), true);
    }
}
