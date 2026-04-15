package com.xemsoft.sheetmusicscanner2.sources;

public class symbol {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public symbol(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(symbol symbol) {
        if (symbol == null) {
            return 0;
        }
        return symbol.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_symbol(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setPix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.symbol_pix_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getPix() {
        long symbol_pix_get = JniSourceJNI.symbol_pix_get(this.swigCPtr, this);
        if (symbol_pix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(symbol_pix_get, false);
    }

    public void setBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.symbol_box_set(this.swigCPtr, this, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public SWIGTYPE_p_BOX getBox() {
        long symbol_box_get = JniSourceJNI.symbol_box_get(this.swigCPtr, this);
        if (symbol_box_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(symbol_box_get, false);
    }

    public void setRefcount(int i) {
        JniSourceJNI.symbol_refcount_set(this.swigCPtr, this, i);
    }

    public int getRefcount() {
        return JniSourceJNI.symbol_refcount_get(this.swigCPtr, this);
    }

    public void setType(symbolType symboltype) {
        JniSourceJNI.symbol_type_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getType() {
        return symbolType.swigToEnum(JniSourceJNI.symbol_type_get(this.swigCPtr, this));
    }

    public void setShortestStemType(symbolType symboltype) {
        JniSourceJNI.symbol_shortestStemType_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getShortestStemType() {
        return symbolType.swigToEnum(JniSourceJNI.symbol_shortestStemType_get(this.swigCPtr, this));
    }

    public void setIsNoteLengthRecognized(int i) {
        JniSourceJNI.symbol_isNoteLengthRecognized_set(this.swigCPtr, this, i);
    }

    public int getIsNoteLengthRecognized() {
        return JniSourceJNI.symbol_isNoteLengthRecognized_get(this.swigCPtr, this);
    }

    public void setIsLastHeadOnStem(int i) {
        JniSourceJNI.symbol_isLastHeadOnStem_set(this.swigCPtr, this, i);
    }

    public int getIsLastHeadOnStem() {
        return JniSourceJNI.symbol_isLastHeadOnStem_get(this.swigCPtr, this);
    }

    public void setDotCount(int i) {
        JniSourceJNI.symbol_dotCount_set(this.swigCPtr, this, i);
    }

    public int getDotCount() {
        return JniSourceJNI.symbol_dotCount_get(this.swigCPtr, this);
    }

    public void setHeadCenterX(float f) {
        JniSourceJNI.symbol_headCenterX_set(this.swigCPtr, this, f);
    }

    public float getHeadCenterX() {
        return JniSourceJNI.symbol_headCenterX_get(this.swigCPtr, this);
    }

    public void setHeadCenterY(float f) {
        JniSourceJNI.symbol_headCenterY_set(this.swigCPtr, this, f);
    }

    public float getHeadCenterY() {
        return JniSourceJNI.symbol_headCenterY_get(this.swigCPtr, this);
    }

    public void setYIndex(int i) {
        JniSourceJNI.symbol_yIndex_set(this.swigCPtr, this, i);
    }

    public int getYIndex() {
        return JniSourceJNI.symbol_yIndex_get(this.swigCPtr, this);
    }

    public void setHeadY0(int i) {
        JniSourceJNI.symbol_headY0_set(this.swigCPtr, this, i);
    }

    public int getHeadY0() {
        return JniSourceJNI.symbol_headY0_get(this.swigCPtr, this);
    }

    public void setHeadY1(int i) {
        JniSourceJNI.symbol_headY1_set(this.swigCPtr, this, i);
    }

    public int getHeadY1() {
        return JniSourceJNI.symbol_headY1_get(this.swigCPtr, this);
    }

    public void setHasCurlyBeam(int i) {
        JniSourceJNI.symbol_hasCurlyBeam_set(this.swigCPtr, this, i);
    }

    public int getHasCurlyBeam() {
        return JniSourceJNI.symbol_hasCurlyBeam_get(this.swigCPtr, this);
    }

    public void setExtendDotBox(int i) {
        JniSourceJNI.symbol_extendDotBox_set(this.swigCPtr, this, i);
    }

    public int getExtendDotBox() {
        return JniSourceJNI.symbol_extendDotBox_get(this.swigCPtr, this);
    }

    public void setX0Ori(int i) {
        JniSourceJNI.symbol_x0Ori_set(this.swigCPtr, this, i);
    }

    public int getX0Ori() {
        return JniSourceJNI.symbol_x0Ori_get(this.swigCPtr, this);
    }

    public void setX1Ori(int i) {
        JniSourceJNI.symbol_x1Ori_set(this.swigCPtr, this, i);
    }

    public int getX1Ori() {
        return JniSourceJNI.symbol_x1Ori_get(this.swigCPtr, this);
    }

    public void setHeadY0Ori(int i) {
        JniSourceJNI.symbol_headY0Ori_set(this.swigCPtr, this, i);
    }

    public int getHeadY0Ori() {
        return JniSourceJNI.symbol_headY0Ori_get(this.swigCPtr, this);
    }

    public void setHeadY1Ori(int i) {
        JniSourceJNI.symbol_headY1Ori_set(this.swigCPtr, this, i);
    }

    public int getHeadY1Ori() {
        return JniSourceJNI.symbol_headY1Ori_get(this.swigCPtr, this);
    }

    public void setHeadStemX0Ori(int i) {
        JniSourceJNI.symbol_headStemX0Ori_set(this.swigCPtr, this, i);
    }

    public int getHeadStemX0Ori() {
        return JniSourceJNI.symbol_headStemX0Ori_get(this.swigCPtr, this);
    }

    public void setHeadStemX1Ori(int i) {
        JniSourceJNI.symbol_headStemX1Ori_set(this.swigCPtr, this, i);
    }

    public int getHeadStemX1Ori() {
        return JniSourceJNI.symbol_headStemX1Ori_get(this.swigCPtr, this);
    }

    public void setHeadHasStemUp(int i) {
        JniSourceJNI.symbol_headHasStemUp_set(this.swigCPtr, this, i);
    }

    public int getHeadHasStemUp() {
        return JniSourceJNI.symbol_headHasStemUp_get(this.swigCPtr, this);
    }

    public void setHeadHasStemDown(int i) {
        JniSourceJNI.symbol_headHasStemDown_set(this.swigCPtr, this, i);
    }

    public int getHeadHasStemDown() {
        return JniSourceJNI.symbol_headHasStemDown_get(this.swigCPtr, this);
    }

    public void setTsBeats(int i) {
        JniSourceJNI.symbol_tsBeats_set(this.swigCPtr, this, i);
    }

    public int getTsBeats() {
        return JniSourceJNI.symbol_tsBeats_get(this.swigCPtr, this);
    }

    public void setTsBeatType(int i) {
        JniSourceJNI.symbol_tsBeatType_set(this.swigCPtr, this, i);
    }

    public int getTsBeatType() {
        return JniSourceJNI.symbol_tsBeatType_get(this.swigCPtr, this);
    }

    public void setBeamCountUL(int i) {
        JniSourceJNI.symbol_beamCountUL_set(this.swigCPtr, this, i);
    }

    public int getBeamCountUL() {
        return JniSourceJNI.symbol_beamCountUL_get(this.swigCPtr, this);
    }

    public void setBeamCountUR(int i) {
        JniSourceJNI.symbol_beamCountUR_set(this.swigCPtr, this, i);
    }

    public int getBeamCountUR() {
        return JniSourceJNI.symbol_beamCountUR_get(this.swigCPtr, this);
    }

    public void setBeamHookCountUL(int i) {
        JniSourceJNI.symbol_beamHookCountUL_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountUL() {
        return JniSourceJNI.symbol_beamHookCountUL_get(this.swigCPtr, this);
    }

    public void setBeamHookCountUR(int i) {
        JniSourceJNI.symbol_beamHookCountUR_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountUR() {
        return JniSourceJNI.symbol_beamHookCountUR_get(this.swigCPtr, this);
    }

    public void setBeamCountDL(int i) {
        JniSourceJNI.symbol_beamCountDL_set(this.swigCPtr, this, i);
    }

    public int getBeamCountDL() {
        return JniSourceJNI.symbol_beamCountDL_get(this.swigCPtr, this);
    }

    public void setBeamCountDR(int i) {
        JniSourceJNI.symbol_beamCountDR_set(this.swigCPtr, this, i);
    }

    public int getBeamCountDR() {
        return JniSourceJNI.symbol_beamCountDR_get(this.swigCPtr, this);
    }

    public void setBeamHookCountDL(int i) {
        JniSourceJNI.symbol_beamHookCountDL_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountDL() {
        return JniSourceJNI.symbol_beamHookCountDL_get(this.swigCPtr, this);
    }

    public void setBeamHookCountDR(int i) {
        JniSourceJNI.symbol_beamHookCountDR_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountDR() {
        return JniSourceJNI.symbol_beamHookCountDR_get(this.swigCPtr, this);
    }

    public void setStemBeamGroupIds(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.symbol_stemBeamGroupIds_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamGroupIds() {
        long symbol_stemBeamGroupIds_get = JniSourceJNI.symbol_stemBeamGroupIds_get(this.swigCPtr, this);
        if (symbol_stemBeamGroupIds_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(symbol_stemBeamGroupIds_get, false);
    }

    public void setStemBeamOrders(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.symbol_stemBeamOrders_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getStemBeamOrders() {
        long symbol_stemBeamOrders_get = JniSourceJNI.symbol_stemBeamOrders_get(this.swigCPtr, this);
        if (symbol_stemBeamOrders_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(symbol_stemBeamOrders_get, false);
    }

    public void setPrima(PRIMA prima) {
        JniSourceJNI.symbol_prima_set(this.swigCPtr, this, PRIMA.getCPtr(prima), prima);
    }

    public PRIMA getPrima() {
        long symbol_prima_get = JniSourceJNI.symbol_prima_get(this.swigCPtr, this);
        if (symbol_prima_get == 0) {
            return null;
        }
        return new PRIMA(symbol_prima_get, false);
    }

    public symbol() {
        this(JniSourceJNI.new_symbol(), true);
    }
}
