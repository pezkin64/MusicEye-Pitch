package com.xemsoft.sheetmusicscanner2.util;

import com.xemsoft.sheetmusicscanner2.leptonica.Box;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_p_Box;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_p_Pix;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_BOX;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_PIX;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_p_score;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_p_session;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.session;

public class Swig {
    public static Box newBOX(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        return new Box(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), false);
    }

    public static Pix newPIX(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return new Pix(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), false);
    }

    public static Pix newPix(long j) {
        return new Pix(j, false);
    }

    public static score newScore(long j) {
        return new score(j, false);
    }

    public static session newSession(long j) {
        return new session(j, false);
    }

    public static void sessionDestroy(session session) {
        JniSource.sessionDestroy(new SWIGTYPE_p_p_session(session.getCPtr(session), false));
    }

    public static void pixDestroy(Pix pix) {
        JniLeptonica.pixDestroy(new SWIGTYPE_p_p_Pix(Pix.getCPtr(pix), false));
    }

    public static void boxDestroy(Box box) {
        JniLeptonica.boxDestroy(new SWIGTYPE_p_p_Box(Box.getCPtr(box), false));
    }

    public static void sessionRemove(session session, score score) {
        SWIGTYPE_p_p_score new_scorepp = JniSource.new_scorepp();
        JniSource.scorepp_assign(new_scorepp, score);
        JniSource.sessionRemove(session, new_scorepp);
        JniSource.delete_scorepp(new_scorepp);
    }

    public static void sessionRemoveById(session session, int i) {
        sessionRemove(session, JniSource.sessionGet(session, i));
    }
}
