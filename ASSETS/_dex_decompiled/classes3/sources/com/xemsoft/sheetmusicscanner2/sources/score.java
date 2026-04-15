package com.xemsoft.sheetmusicscanner2.sources;

public class score {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public score(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(score score) {
        if (score == null) {
            return 0;
        }
        return score.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_score(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setSingleBaraa(baraa baraa) {
        JniSourceJNI.score_singleBaraa_set(this.swigCPtr, this, baraa.getCPtr(baraa), baraa);
    }

    public baraa getSingleBaraa() {
        long score_singleBaraa_get = JniSourceJNI.score_singleBaraa_get(this.swigCPtr, this);
        if (score_singleBaraa_get == 0) {
            return null;
        }
        return new baraa(score_singleBaraa_get, false);
    }

    public void setGroupBaraa(baraa baraa) {
        JniSourceJNI.score_groupBaraa_set(this.swigCPtr, this, baraa.getCPtr(baraa), baraa);
    }

    public baraa getGroupBaraa() {
        long score_groupBaraa_get = JniSourceJNI.score_groupBaraa_get(this.swigCPtr, this);
        if (score_groupBaraa_get == 0) {
            return null;
        }
        return new baraa(score_groupBaraa_get, false);
    }

    public void setVoiceCounts(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.score_voiceCounts_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getVoiceCounts() {
        long score_voiceCounts_get = JniSourceJNI.score_voiceCounts_get(this.swigCPtr, this);
        if (score_voiceCounts_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(score_voiceCounts_get, false);
    }

    public void setVoiceIndexes(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.score_voiceIndexes_set(this.swigCPtr, this, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public SWIGTYPE_p_NUMA getVoiceIndexes() {
        long score_voiceIndexes_get = JniSourceJNI.score_voiceIndexes_get(this.swigCPtr, this);
        if (score_voiceIndexes_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(score_voiceIndexes_get, false);
    }

    public score() {
        this(JniSourceJNI.new_score(), true);
    }
}
