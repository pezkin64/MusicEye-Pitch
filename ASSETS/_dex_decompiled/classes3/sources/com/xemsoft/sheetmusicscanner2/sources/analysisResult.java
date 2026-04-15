package com.xemsoft.sheetmusicscanner2.sources;

public class analysisResult {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected analysisResult(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(analysisResult analysisresult) {
        if (analysisresult == null) {
            return 0;
        }
        return analysisresult.swigCPtr;
    }

    /* access modifiers changed from: protected */
    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniSourceJNI.delete_analysisResult(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setScore(score score) {
        JniSourceJNI.analysisResult_score_set(this.swigCPtr, this, score.getCPtr(score), score);
    }

    public score getScore() {
        long analysisResult_score_get = JniSourceJNI.analysisResult_score_get(this.swigCPtr, this);
        if (analysisResult_score_get == 0) {
            return null;
        }
        return new score(analysisResult_score_get, false);
    }

    public void setOverlayMask(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.analysisResult_overlayMask_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getOverlayMask() {
        long analysisResult_overlayMask_get = JniSourceJNI.analysisResult_overlayMask_get(this.swigCPtr, this);
        if (analysisResult_overlayMask_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(analysisResult_overlayMask_get, false);
    }

    public void setBackground(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.analysisResult_background_set(this.swigCPtr, this, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public SWIGTYPE_p_PIX getBackground() {
        long analysisResult_background_get = JniSourceJNI.analysisResult_background_get(this.swigCPtr, this);
        if (analysisResult_background_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(analysisResult_background_get, false);
    }

    public void setResultCode(int i) {
        JniSourceJNI.analysisResult_resultCode_set(this.swigCPtr, this, i);
    }

    public int getResultCode() {
        return JniSourceJNI.analysisResult_resultCode_get(this.swigCPtr, this);
    }

    public analysisResult() {
        this(JniSourceJNI.new_analysisResult(), true);
    }
}
