package com.xemsoft.sheetmusicscanner2.sources;

public class sound {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public sound(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(sound sound) {
        if (sound == null) {
            return 0;
        }
        return sound.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_sound(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setId(int i) {
        JniSourceJNI.sound_id_set(this.swigCPtr, this, i);
    }

    public int getId() {
        return JniSourceJNI.sound_id_get(this.swigCPtr, this);
    }

    public void setPitch(int i) {
        JniSourceJNI.sound_pitch_set(this.swigCPtr, this, i);
    }

    public int getPitch() {
        return JniSourceJNI.sound_pitch_get(this.swigCPtr, this);
    }

    public void setPitchWithoutAccidentals(int i) {
        JniSourceJNI.sound_pitchWithoutAccidentals_set(this.swigCPtr, this, i);
    }

    public int getPitchWithoutAccidentals() {
        return JniSourceJNI.sound_pitchWithoutAccidentals_get(this.swigCPtr, this);
    }

    public void setIsRest(int i) {
        JniSourceJNI.sound_isRest_set(this.swigCPtr, this, i);
    }

    public int getIsRest() {
        return JniSourceJNI.sound_isRest_get(this.swigCPtr, this);
    }

    public void setLength(double d) {
        JniSourceJNI.sound_length_set(this.swigCPtr, this, d);
    }

    public double getLength() {
        return JniSourceJNI.sound_length_get(this.swigCPtr, this);
    }

    public void setShortLength(double d) {
        JniSourceJNI.sound_shortLength_set(this.swigCPtr, this, d);
    }

    public double getShortLength() {
        return JniSourceJNI.sound_shortLength_get(this.swigCPtr, this);
    }

    public void setDisplayLength(double d) {
        JniSourceJNI.sound_displayLength_set(this.swigCPtr, this, d);
    }

    public double getDisplayLength() {
        return JniSourceJNI.sound_displayLength_get(this.swigCPtr, this);
    }

    public void setVolume(int i) {
        JniSourceJNI.sound_volume_set(this.swigCPtr, this, i);
    }

    public int getVolume() {
        return JniSourceJNI.sound_volume_get(this.swigCPtr, this);
    }

    public void setPix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.sound_pix_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getPix() {
        long sound_pix_get = JniSourceJNI.sound_pix_get(this.swigCPtr, this);
        if (sound_pix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(sound_pix_get, false);
    }

    public void setBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.sound_box_set(this.swigCPtr, this, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public SWIGTYPE_p_BOX getBox() {
        long sound_box_get = JniSourceJNI.sound_box_get(this.swigCPtr, this);
        if (sound_box_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(sound_box_get, false);
    }

    public void setX0(int i) {
        JniSourceJNI.sound_x0_set(this.swigCPtr, this, i);
    }

    public int getX0() {
        return JniSourceJNI.sound_x0_get(this.swigCPtr, this);
    }

    public void setX1(int i) {
        JniSourceJNI.sound_x1_set(this.swigCPtr, this, i);
    }

    public int getX1() {
        return JniSourceJNI.sound_x1_get(this.swigCPtr, this);
    }

    public void setHeadY0(int i) {
        JniSourceJNI.sound_headY0_set(this.swigCPtr, this, i);
    }

    public int getHeadY0() {
        return JniSourceJNI.sound_headY0_get(this.swigCPtr, this);
    }

    public void setHeadY1(int i) {
        JniSourceJNI.sound_headY1_set(this.swigCPtr, this, i);
    }

    public int getHeadY1() {
        return JniSourceJNI.sound_headY1_get(this.swigCPtr, this);
    }

    public void setType(symbolType symboltype) {
        JniSourceJNI.sound_type_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getType() {
        return symbolType.swigToEnum(JniSourceJNI.sound_type_get(this.swigCPtr, this));
    }

    public void setDotCount(int i) {
        JniSourceJNI.sound_dotCount_set(this.swigCPtr, this, i);
    }

    public int getDotCount() {
        return JniSourceJNI.sound_dotCount_get(this.swigCPtr, this);
    }

    public void setAccidentalType(symbolType symboltype) {
        JniSourceJNI.sound_accidentalType_set(this.swigCPtr, this, symboltype.swigValue());
    }

    public symbolType getAccidentalType() {
        return symbolType.swigToEnum(JniSourceJNI.sound_accidentalType_get(this.swigCPtr, this));
    }

    public void setBeamCountUL(int i) {
        JniSourceJNI.sound_beamCountUL_set(this.swigCPtr, this, i);
    }

    public int getBeamCountUL() {
        return JniSourceJNI.sound_beamCountUL_get(this.swigCPtr, this);
    }

    public void setBeamCountUR(int i) {
        JniSourceJNI.sound_beamCountUR_set(this.swigCPtr, this, i);
    }

    public int getBeamCountUR() {
        return JniSourceJNI.sound_beamCountUR_get(this.swigCPtr, this);
    }

    public void setBeamHookCountUL(int i) {
        JniSourceJNI.sound_beamHookCountUL_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountUL() {
        return JniSourceJNI.sound_beamHookCountUL_get(this.swigCPtr, this);
    }

    public void setBeamHookCountUR(int i) {
        JniSourceJNI.sound_beamHookCountUR_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountUR() {
        return JniSourceJNI.sound_beamHookCountUR_get(this.swigCPtr, this);
    }

    public void setBeamCountDL(int i) {
        JniSourceJNI.sound_beamCountDL_set(this.swigCPtr, this, i);
    }

    public int getBeamCountDL() {
        return JniSourceJNI.sound_beamCountDL_get(this.swigCPtr, this);
    }

    public void setBeamCountDR(int i) {
        JniSourceJNI.sound_beamCountDR_set(this.swigCPtr, this, i);
    }

    public int getBeamCountDR() {
        return JniSourceJNI.sound_beamCountDR_get(this.swigCPtr, this);
    }

    public void setBeamHookCountDL(int i) {
        JniSourceJNI.sound_beamHookCountDL_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountDL() {
        return JniSourceJNI.sound_beamHookCountDL_get(this.swigCPtr, this);
    }

    public void setBeamHookCountDR(int i) {
        JniSourceJNI.sound_beamHookCountDR_set(this.swigCPtr, this, i);
    }

    public int getBeamHookCountDR() {
        return JniSourceJNI.sound_beamHookCountDR_get(this.swigCPtr, this);
    }

    public void setIsTiedWithPrevious(int i) {
        JniSourceJNI.sound_isTiedWithPrevious_set(this.swigCPtr, this, i);
    }

    public int getIsTiedWithPrevious() {
        return JniSourceJNI.sound_isTiedWithPrevious_get(this.swigCPtr, this);
    }

    public void setFirstTiedNote(sound sound) {
        JniSourceJNI.sound_firstTiedNote_set(this.swigCPtr, this, getCPtr(sound), sound);
    }

    public sound getFirstTiedNote() {
        long sound_firstTiedNote_get = JniSourceJNI.sound_firstTiedNote_get(this.swigCPtr, this);
        if (sound_firstTiedNote_get == 0) {
            return null;
        }
        return new sound(sound_firstTiedNote_get, false);
    }

    public void setNextTiedNote(sound sound) {
        JniSourceJNI.sound_nextTiedNote_set(this.swigCPtr, this, getCPtr(sound), sound);
    }

    public sound getNextTiedNote() {
        long sound_nextTiedNote_get = JniSourceJNI.sound_nextTiedNote_get(this.swigCPtr, this);
        if (sound_nextTiedNote_get == 0) {
            return null;
        }
        return new sound(sound_nextTiedNote_get, false);
    }

    public void setTiePix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.sound_tiePix_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getTiePix() {
        long sound_tiePix_get = JniSourceJNI.sound_tiePix_get(this.swigCPtr, this);
        if (sound_tiePix_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(sound_tiePix_get, false);
    }

    public void setTieBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.sound_tieBox_set(this.swigCPtr, this, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public SWIGTYPE_p_BOX getTieBox() {
        long sound_tieBox_get = JniSourceJNI.sound_tieBox_get(this.swigCPtr, this);
        if (sound_tieBox_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(sound_tieBox_get, false);
    }

    public void setVoiceIndex(int i) {
        JniSourceJNI.sound_voiceIndex_set(this.swigCPtr, this, i);
    }

    public int getVoiceIndex() {
        return JniSourceJNI.sound_voiceIndex_get(this.swigCPtr, this);
    }

    public int getVoiceSubindexSplit() {
        return JniSourceJNI.sound_voiceSubindexSplit_get(this.swigCPtr, this);
    }

    public void setLengthConfirmedByBeam(int i) {
        JniSourceJNI.sound_lengthConfirmedByBeam_set(this.swigCPtr, this, i);
    }

    public int getLengthConfirmedByBeam() {
        return JniSourceJNI.sound_lengthConfirmedByBeam_get(this.swigCPtr, this);
    }

    public sound() {
        this(JniSourceJNI.new_sound(), true);
    }
}
