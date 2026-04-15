package com.xemsoft.sheetmusicscanner2.player;

import android.content.Context;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.session;

public class Global {
    private static final String LOGTAG = "Global";
    public static final int MODE_EDIT = 2;
    public static final int MODE_PLAYING = 1;
    public static final int MODE_STOPPED = 0;
    private static volatile int m_InsertAtIndex = -1;
    private static Global m_Instance = null;
    private static volatile boolean m_SessionValid = false;
    private int m_HighlightMode = 0;
    private boolean m_IsGroup = false;
    private int m_Mode = 0;

    public static Global getInstance() {
        if (m_Instance == null) {
            m_Instance = new Global();
            Log.d(LOGTAG, "new Global()");
        }
        return m_Instance;
    }

    public synchronized void setMode(int i) {
        this.m_Mode = i;
    }

    public synchronized int getMode() {
        return this.m_Mode;
    }

    public synchronized void setHighlightMode(int i) {
        this.m_HighlightMode = i;
    }

    public synchronized int getHighlightMode() {
        return this.m_HighlightMode;
    }

    public synchronized void setIsGroup(boolean z) {
        this.m_IsGroup = z;
    }

    public synchronized boolean getIsGroup() {
        return this.m_IsGroup;
    }

    public synchronized void setSession(session session, Context context) {
        if (session == null) {
            m_SessionValid = false;
            Log.d(LOGTAG, "setSession() - null");
        } else {
            JniSource.serialize(session, SessionUtility.getInstance(context).TMP_SESSION_PATH);
            m_SessionValid = true;
            String str = LOGTAG;
            Log.d(str, "setSession():" + session.getCPtr(session) + " n = " + session.getN());
        }
    }

    public synchronized session getSession(Context context) {
        if (!m_SessionValid) {
            Log.d(LOGTAG, "getSession() - null");
            return null;
        }
        session deserialize = JniSource.deserialize(SessionUtility.getInstance(context).TMP_SESSION_PATH);
        String str = LOGTAG;
        Log.d(str, "getSession():" + session.getCPtr(deserialize) + " n = " + deserialize.getN());
        return deserialize;
    }

    public synchronized void setInsertAtIndex(int i) {
        m_InsertAtIndex = i;
    }

    public synchronized int getInsertAtIndex() {
        return m_InsertAtIndex;
    }
}
