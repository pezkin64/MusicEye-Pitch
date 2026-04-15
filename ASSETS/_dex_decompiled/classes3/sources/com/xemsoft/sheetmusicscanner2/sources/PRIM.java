package com.xemsoft.sheetmusicscanner2.sources;

public class PRIM {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PRIM(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(PRIM prim) {
        if (prim == null) {
            return 0;
        }
        return prim.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_PRIM(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRefcount(int i) {
        JniSourceJNI.PRIM_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniSourceJNI.PRIM_refcount_get(this.swigCPtr, this);
    }

    public void setId(int i) {
        JniSourceJNI.PRIM_id_set(this.swigCPtr, this, i);
    }

    public int getId() {
        return JniSourceJNI.PRIM_id_get(this.swigCPtr, this);
    }

    public void setPix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.PRIM_pix_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getPix() {
        long PRIM_pix_get = JniSourceJNI.PRIM_pix_get(this.swigCPtr, this);
        if (PRIM_pix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(PRIM_pix_get, false);
    }

    public void setBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.PRIM_box_set(this.swigCPtr, this, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public SWIGTYPE_p_BOX getBox() {
        long PRIM_box_get = JniSourceJNI.PRIM_box_get(this.swigCPtr, this);
        if (PRIM_box_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(PRIM_box_get, false);
    }

    public void setMatcha(matcha matcha) {
        JniSourceJNI.PRIM_matcha_set(this.swigCPtr, this, matcha.getCPtr(matcha), matcha);
    }

    public matcha getMatcha() {
        long PRIM_matcha_get = JniSourceJNI.PRIM_matcha_get(this.swigCPtr, this);
        if (PRIM_matcha_get == 0) {
            return null;
        }
        return new matcha(PRIM_matcha_get, false);
    }

    public void setRecognizedType(primType primtype) {
        JniSourceJNI.PRIM_recognizedType_set(this.swigCPtr, this, primtype.swigValue());
    }

    public primType getRecognizedType() {
        return primType.swigToEnum(JniSourceJNI.PRIM_recognizedType_get(this.swigCPtr, this));
    }

    public void setYIndex(int i) {
        JniSourceJNI.PRIM_yIndex_set(this.swigCPtr, this, i);
    }

    public int getYIndex() {
        return JniSourceJNI.PRIM_yIndex_get(this.swigCPtr, this);
    }

    public void setHeadStemsL(PRIMA prima) {
        JniSourceJNI.PRIM_headStemsL_set(this.swigCPtr, this, PRIMA.getCPtr(prima), prima);
    }

    public PRIMA getHeadStemsL() {
        long PRIM_headStemsL_get = JniSourceJNI.PRIM_headStemsL_get(this.swigCPtr, this);
        if (PRIM_headStemsL_get == 0) {
            return null;
        }
        return new PRIMA(PRIM_headStemsL_get, false);
    }

    public void setHeadStemsR(PRIMA prima) {
        JniSourceJNI.PRIM_headStemsR_set(this.swigCPtr, this, PRIMA.getCPtr(prima), prima);
    }

    public PRIMA getHeadStemsR() {
        long PRIM_headStemsR_get = JniSourceJNI.PRIM_headStemsR_get(this.swigCPtr, this);
        if (PRIM_headStemsR_get == 0) {
            return null;
        }
        return new PRIMA(PRIM_headStemsR_get, false);
    }

    public void setStemHeadsL(PRIMA prima) {
        JniSourceJNI.PRIM_stemHeadsL_set(this.swigCPtr, this, PRIMA.getCPtr(prima), prima);
    }

    public PRIMA getStemHeadsL() {
        long PRIM_stemHeadsL_get = JniSourceJNI.PRIM_stemHeadsL_get(this.swigCPtr, this);
        if (PRIM_stemHeadsL_get == 0) {
            return null;
        }
        return new PRIMA(PRIM_stemHeadsL_get, false);
    }

    public void setStemHeadsR(PRIMA prima) {
        JniSourceJNI.PRIM_stemHeadsR_set(this.swigCPtr, this, PRIMA.getCPtr(prima), prima);
    }

    public PRIMA getStemHeadsR() {
        long PRIM_stemHeadsR_get = JniSourceJNI.PRIM_stemHeadsR_get(this.swigCPtr, this);
        if (PRIM_stemHeadsR_get == 0) {
            return null;
        }
        return new PRIMA(PRIM_stemHeadsR_get, false);
    }

    public void setStemHeadY0(int i) {
        JniSourceJNI.PRIM_stemHeadY0_set(this.swigCPtr, this, i);
    }

    public int getStemHeadY0() {
        return JniSourceJNI.PRIM_stemHeadY0_get(this.swigCPtr, this);
    }

    public void setStemHeadY1(int i) {
        JniSourceJNI.PRIM_stemHeadY1_set(this.swigCPtr, this, i);
    }

    public int getStemHeadY1() {
        return JniSourceJNI.PRIM_stemHeadY1_get(this.swigCPtr, this);
    }

    public void setIsStemPureHalfNotes(int i) {
        JniSourceJNI.PRIM_isStemPureHalfNotes_set(this.swigCPtr, this, i);
    }

    public int getIsStemPureHalfNotes() {
        return JniSourceJNI.PRIM_isStemPureHalfNotes_get(this.swigCPtr, this);
    }

    public void setHasStemHeadsOnTheLeft(int i) {
        JniSourceJNI.PRIM_hasStemHeadsOnTheLeft_set(this.swigCPtr, this, i);
    }

    public int getHasStemHeadsOnTheLeft() {
        return JniSourceJNI.PRIM_hasStemHeadsOnTheLeft_get(this.swigCPtr, this);
    }

    public void setHasStemHeadsOnTheRight(int i) {
        JniSourceJNI.PRIM_hasStemHeadsOnTheRight_set(this.swigCPtr, this, i);
    }

    public int getHasStemHeadsOnTheRight() {
        return JniSourceJNI.PRIM_hasStemHeadsOnTheRight_get(this.swigCPtr, this);
    }

    public void setIsStemUp(int i) {
        JniSourceJNI.PRIM_isStemUp_set(this.swigCPtr, this, i);
    }

    public int getIsStemUp() {
        return JniSourceJNI.PRIM_isStemUp_get(this.swigCPtr, this);
    }

    public void setIsStemDown(int i) {
        JniSourceJNI.PRIM_isStemDown_set(this.swigCPtr, this, i);
    }

    public int getIsStemDown() {
        return JniSourceJNI.PRIM_isStemDown_get(this.swigCPtr, this);
    }

    public void setStemBeamY0sL(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamY0sL_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamY0sL() {
        long PRIM_stemBeamY0sL_get = JniSourceJNI.PRIM_stemBeamY0sL_get(this.swigCPtr, this);
        if (PRIM_stemBeamY0sL_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamY0sL_get, false);
    }

    public void setStemBeamY1sL(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamY1sL_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamY1sL() {
        long PRIM_stemBeamY1sL_get = JniSourceJNI.PRIM_stemBeamY1sL_get(this.swigCPtr, this);
        if (PRIM_stemBeamY1sL_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamY1sL_get, false);
    }

    public void setStemBeamY0sR(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamY0sR_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamY0sR() {
        long PRIM_stemBeamY0sR_get = JniSourceJNI.PRIM_stemBeamY0sR_get(this.swigCPtr, this);
        if (PRIM_stemBeamY0sR_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamY0sR_get, false);
    }

    public void setStemBeamY1sR(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamY1sR_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamY1sR() {
        long PRIM_stemBeamY1sR_get = JniSourceJNI.PRIM_stemBeamY1sR_get(this.swigCPtr, this);
        if (PRIM_stemBeamY1sR_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamY1sR_get, false);
    }

    public void setStemBeamDeltasL(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamDeltasL_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamDeltasL() {
        long PRIM_stemBeamDeltasL_get = JniSourceJNI.PRIM_stemBeamDeltasL_get(this.swigCPtr, this);
        if (PRIM_stemBeamDeltasL_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamDeltasL_get, false);
    }

    public void setStemBeamDeltasR(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamDeltasR_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamDeltasR() {
        long PRIM_stemBeamDeltasR_get = JniSourceJNI.PRIM_stemBeamDeltasR_get(this.swigCPtr, this);
        if (PRIM_stemBeamDeltasR_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamDeltasR_get, false);
    }

    public void setStemBeamGroupIds(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamGroupIds_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamGroupIds() {
        long PRIM_stemBeamGroupIds_get = JniSourceJNI.PRIM_stemBeamGroupIds_get(this.swigCPtr, this);
        if (PRIM_stemBeamGroupIds_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamGroupIds_get, false);
    }

    public void setStemBeamOrders(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_stemBeamOrders_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamOrders() {
        long PRIM_stemBeamOrders_get = JniSourceJNI.PRIM_stemBeamOrders_get(this.swigCPtr, this);
        if (PRIM_stemBeamOrders_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_stemBeamOrders_get, false);
    }

    public void setStemBeamCountL(int i) {
        JniSourceJNI.PRIM_stemBeamCountL_set(this.swigCPtr, this, i);
    }

    public int getStemBeamCountL() {
        return JniSourceJNI.PRIM_stemBeamCountL_get(this.swigCPtr, this);
    }

    public void setStemBeamCountR(int i) {
        JniSourceJNI.PRIM_stemBeamCountR_set(this.swigCPtr, this, i);
    }

    public int getStemBeamCountR() {
        return JniSourceJNI.PRIM_stemBeamCountR_get(this.swigCPtr, this);
    }

    public void setStemBeamHookCountL(int i) {
        JniSourceJNI.PRIM_stemBeamHookCountL_set(this.swigCPtr, this, i);
    }

    public int getStemBeamHookCountL() {
        return JniSourceJNI.PRIM_stemBeamHookCountL_get(this.swigCPtr, this);
    }

    public void setStemBeamHookCountR(int i) {
        JniSourceJNI.PRIM_stemBeamHookCountR_set(this.swigCPtr, this, i);
    }

    public int getStemBeamHookCountR() {
        return JniSourceJNI.PRIM_stemBeamHookCountR_get(this.swigCPtr, this);
    }

    public void setAccidentalYIndex(int i) {
        JniSourceJNI.PRIM_accidentalYIndex_set(this.swigCPtr, this, i);
    }

    public int getAccidentalYIndex() {
        return JniSourceJNI.PRIM_accidentalYIndex_get(this.swigCPtr, this);
    }

    public void setBeamY0L(int i) {
        JniSourceJNI.PRIM_beamY0L_set(this.swigCPtr, this, i);
    }

    public int getBeamY0L() {
        return JniSourceJNI.PRIM_beamY0L_get(this.swigCPtr, this);
    }

    public void setBeamY0R(int i) {
        JniSourceJNI.PRIM_beamY0R_set(this.swigCPtr, this, i);
    }

    public int getBeamY0R() {
        return JniSourceJNI.PRIM_beamY0R_get(this.swigCPtr, this);
    }

    public void setBeamHeight(int i) {
        JniSourceJNI.PRIM_beamHeight_set(this.swigCPtr, this, i);
    }

    public int getBeamHeight() {
        return JniSourceJNI.PRIM_beamHeight_get(this.swigCPtr, this);
    }

    public void setBeamGroupId(int i) {
        JniSourceJNI.PRIM_beamGroupId_set(this.swigCPtr, this, i);
    }

    public int getBeamGroupId() {
        return JniSourceJNI.PRIM_beamGroupId_get(this.swigCPtr, this);
    }

    public void setBeamStaffIndexes(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_beamStaffIndexes_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBeamStaffIndexes() {
        long PRIM_beamStaffIndexes_get = JniSourceJNI.PRIM_beamStaffIndexes_get(this.swigCPtr, this);
        if (PRIM_beamStaffIndexes_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_beamStaffIndexes_get, false);
    }

    public void setBeamStemsXOri(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_beamStemsXOri_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBeamStemsXOri() {
        long PRIM_beamStemsXOri_get = JniSourceJNI.PRIM_beamStemsXOri_get(this.swigCPtr, this);
        if (PRIM_beamStemsXOri_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_beamStemsXOri_get, false);
    }

    public void setBeamStartTimeRel(double d) {
        JniSourceJNI.PRIM_beamStartTimeRel_set(this.swigCPtr, this, d);
    }

    public double getBeamStartTimeRel() {
        return JniSourceJNI.PRIM_beamStartTimeRel_get(this.swigCPtr, this);
    }

    public void setBeamTimesToNext(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.PRIM_beamTimesToNext_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getBeamTimesToNext() {
        long PRIM_beamTimesToNext_get = JniSourceJNI.PRIM_beamTimesToNext_get(this.swigCPtr, this);
        if (PRIM_beamTimesToNext_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(PRIM_beamTimesToNext_get, false);
    }

    public PRIM() {
        this(JniSourceJNI.new_PRIM(), true);
    }
}
