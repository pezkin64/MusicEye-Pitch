package com.xemsoft.sheetmusicscanner2.analysis;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.player.Global;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.analysisResult;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.Swig;

public class SessionBuilder {
    public static final int ERROR_DISK_SPACE = 3;
    public static final int ERROR_SAVE = 2;
    private static final String LOGTAG = "SessionBuilder";
    public static final int SUCCESS = 0;
    private CdSession m_Cds = null;
    private Context m_Context;
    private int m_InitialScoreCount = 0;
    private int m_InsertIdx = 0;
    private boolean m_InsertMode = false;
    private SessionUtility m_SUtil;
    private volatile session m_Session = null;
    private String m_SessionDir = null;
    private String m_SessionName;

    public SessionBuilder(Context context, String str) {
        this.m_Context = context;
        this.m_SUtil = SessionUtility.getInstance(context);
        this.m_SessionName = str;
        this.m_InsertMode = false;
    }

    public SessionBuilder(Context context, String str, int i) {
        this.m_Context = context;
        this.m_SUtil = SessionUtility.getInstance(context);
        this.m_SessionDir = str;
        this.m_InsertIdx = i;
        this.m_InsertMode = true;
    }

    public int addPage(analysisResult analysisresult) {
        if (this.m_SUtil.getFreeSpace() < 10000000) {
            return 3;
        }
        return this.m_InsertMode ? insertPage(analysisresult) : appendPage(analysisresult) ? 0 : 2;
    }

    private boolean appendPage(analysisResult analysisresult) {
        Log.d(LOGTAG, "appendPage()");
        int i = 0;
        if (this.m_Session == null && !createSession()) {
            return false;
        }
        int n = this.m_Session.getN();
        JniSource.sessionAdd(this.m_Session, analysisresult.getScore());
        if (!this.m_SUtil.saveSession(this.m_Session, this.m_SessionDir)) {
            deleteSession();
            return false;
        }
        Pix newPIX = Swig.newPIX(analysisresult.getBackground());
        Pix newPIX2 = Swig.newPIX(analysisresult.getOverlayMask());
        Bitmap writeBitmap = JniSource.writeBitmap(newPIX, false);
        Swig.pixDestroy(newPIX);
        boolean saveImagesToSessionFolder = this.m_SUtil.saveImagesToSessionFolder(this.m_SessionDir, n, writeBitmap, analysisresult.getOverlayMask());
        Swig.pixDestroy(newPIX2);
        if (!saveImagesToSessionFolder) {
            deleteSession();
        }
        Global instance = Global.getInstance();
        instance.setSession(this.m_Session, this.m_Context);
        if (this.m_Session != null) {
            i = this.m_Session.getN();
        }
        instance.setInsertAtIndex(i);
        return saveImagesToSessionFolder;
    }

    private boolean insertPage(analysisResult analysisresult) {
        Log.d(LOGTAG, "insertPage()");
        if (this.m_Session == null && !loadSession()) {
            return false;
        }
        Pix newPIX = Swig.newPIX(analysisresult.getBackground());
        Pix newPIX2 = Swig.newPIX(analysisresult.getOverlayMask());
        Bitmap writeBitmap = JniSource.writeBitmap(newPIX, false);
        Swig.pixDestroy(newPIX);
        boolean insertImagesToSessionFolder = this.m_SUtil.insertImagesToSessionFolder(this.m_SessionDir, this.m_InsertIdx, this.m_Session.getN() - 1, writeBitmap, analysisresult.getOverlayMask());
        Swig.pixDestroy(newPIX2);
        if (!insertImagesToSessionFolder) {
            return insertImagesToSessionFolder;
        }
        JniSource.sessionInsert(this.m_Session, analysisresult.getScore(), this.m_InsertIdx);
        boolean saveSession = this.m_SUtil.saveSession(this.m_Session, this.m_SessionDir);
        if (!saveSession) {
            this.m_SUtil.removeImagesFromSessionFolder(this.m_SessionDir, this.m_InsertIdx, this.m_Session.getN() - 1);
            return saveSession;
        }
        this.m_InsertIdx++;
        Global instance = Global.getInstance();
        instance.setSession(this.m_Session, this.m_Context);
        instance.setInsertAtIndex(this.m_InsertIdx);
        return saveSession;
    }

    public String getSessionDir() {
        return this.m_SessionDir;
    }

    public int getScoreCount() {
        if (this.m_Session == null) {
            return 0;
        }
        return this.m_Session.getN();
    }

    public int getInitialScoreCount() {
        return this.m_InitialScoreCount;
    }

    public void destroySession() {
        if (this.m_Session != null) {
            Swig.sessionDestroy(this.m_Session);
            this.m_Session = null;
        }
    }

    public void deleteSession() {
        String str;
        destroySession();
        if (!this.m_InsertMode && (str = this.m_SessionDir) != null) {
            this.m_SUtil.deleteSessionFolder(str);
            this.m_SUtil.cdDeleteSession(this.m_SessionDir);
            this.m_SessionDir = null;
        }
    }

    private boolean createSession() {
        this.m_SessionDir = this.m_SUtil.createNewSessionName();
        UserSettings instance = UserSettings.getInstance(this.m_Context);
        this.m_Cds = this.m_SUtil.cdCreateSession(this.m_SessionDir, this.m_SessionName, instance.getBpm(), instance.getInstrument(), instance.getInstrumentPitch());
        this.m_Session = JniSource.sessionCreate();
        if (this.m_Cds != null && this.m_Session != null) {
            return true;
        }
        deleteSession();
        return false;
    }

    private boolean loadSession() {
        this.m_Session = this.m_SUtil.loadSession(this.m_SessionDir);
        this.m_InitialScoreCount = this.m_Session == null ? 0 : this.m_Session.getN();
        if (this.m_Session != null) {
            return true;
        }
        return false;
    }
}
