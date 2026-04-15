package com.xemsoft.sheetmusicscanner2.player;

import android.content.Context;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Numa;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_int;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.player.resource.BarButton;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResource;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResourceFactory;
import com.xemsoft.sheetmusicscanner2.player.sound.SoundPlayer;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_NUMA;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.baraa;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.util.List;

public class SessionManager {
    private static final String LOGTAG = "SessionManager.java";
    private BarButton m_ActiveButton = null;
    private Object m_ActiveLock = null;
    private int m_ActivePage = 0;
    private CdSession m_CdSession = null;
    private Context m_Context;
    /* access modifiers changed from: private */
    public Global m_Global;
    /* access modifiers changed from: private */
    public SessionManagerListener m_Listener = null;
    private SoundPlayer m_Player;
    private SoundPlayer.SoundPlayerListener m_PlayerListener = new SoundPlayer.SoundPlayerListener() {
        private int m_GraphDelay = 200;

        public void setSoundVisibility(final score score, final sound sound, final boolean z) {
            if (SessionManager.this.m_Global.getMode() == 1) {
                SessionManager.this.m_SessionView.postDelayed(new Runnable() {
                    public void run() {
                        SessionManager.this.m_SessionView.setSoundVisibility(score, sound, z);
                    }
                }, (long) this.m_GraphDelay);
            }
        }

        public void setBarButtonSelected(final score score, final bar bar, final boolean z) {
            if (SessionManager.this.m_Global.getMode() == 1) {
                SessionManager.this.m_SessionView.postDelayed(new Runnable() {
                    public void run() {
                        SessionManager.this.m_SessionView.setBarButtonSelected(score, bar, z);
                    }
                }, (long) this.m_GraphDelay);
            }
        }

        public void playingFinished() {
            SessionManager.this.m_SessionView.postDelayed(new Runnable() {
                public void run() {
                    SessionManager.this.m_Global.setMode(0);
                    SessionManager.this.m_SessionView.playingFinished();
                    if (SessionManager.this.m_Listener != null) {
                        SessionManager.this.m_Listener.onUpdateUI();
                    }
                }
            }, (long) this.m_GraphDelay);
        }
    };
    private List<ScoreResource> m_ResList;
    private SessionUtility m_SUtil;
    private session m_Session = null;
    private SessionListener m_SessionListener = new SessionListener() {
        public void onButtonTap(BarButton barButton, int i) {
            int mode = SessionManager.this.m_Global.getMode();
            if (mode != 0) {
                if (mode == 1) {
                    SessionManager.this.playingFinished();
                }
            } else if (barButton != null) {
                SessionManager.this.m_SessionView.setBarButtonSelected(barButton.getScore(), barButton.getBar(), true);
                SessionManager.this.setActiveButton(barButton);
                SessionManager.this.playingStart();
            }
        }

        public void onActiveButton(BarButton barButton, int i) {
            SessionManager.this.setActiveButton(barButton, i);
            SessionManager.this.scrollButtonToView();
        }

        public void onAddPage(int i) {
            if (SessionManager.this.m_Listener != null) {
                SessionManager.this.m_Listener.onAddPage(i);
            }
        }

        public void onDeletePage(int i) {
            if (SessionManager.this.m_Listener != null) {
                SessionManager.this.m_Listener.onDeletePage(i);
            }
        }

        public void onEditTransitionDone() {
            SessionManager.this.m_SessionView.postDelayed(new Runnable() {
                public void run() {
                    SessionManager.this.m_SessionView.setEditTransitionDone();
                }
            }, 300);
        }

        public void onSetEditMode(boolean z) {
            SessionManager.this.m_Listener.onSetEditMode(z);
        }
    };
    /* access modifiers changed from: private */
    public SessionView m_SessionView;

    public interface SessionManagerListener {
        void onAddPage(int i);

        void onDeletePage(int i);

        void onPlayStart();

        void onSaveSessionError();

        void onSetEditMode(boolean z);

        void onUpdateUI();
    }

    public SessionManager(Context context, SessionView sessionView) {
        this.m_Context = context;
        this.m_SessionView = sessionView;
        this.m_Global = Global.getInstance();
        this.m_SUtil = SessionUtility.getInstance(context);
        SoundPlayer instance = SoundPlayer.getInstance(context);
        this.m_Player = instance;
        instance.setListener(this.m_PlayerListener);
        this.m_ActiveLock = new Object();
    }

    public void setListener(SessionManagerListener sessionManagerListener) {
        this.m_Listener = sessionManagerListener;
    }

    public void init(CdSession cdSession, session session, List<ScoreResource> list) {
        Log.d(LOGTAG, "init");
        this.m_CdSession = cdSession;
        this.m_Session = session;
        this.m_ResList = list;
        this.m_Global.setIsGroup(cdSession.isMultipleVoicesOn());
        this.m_SessionView.setResourceList(list);
        this.m_SessionView.setSessionListener(this.m_SessionListener);
        this.m_Player.setUpWithSession(this.m_Session, this.m_CdSession.getBpm(), this.m_CdSession.isMultipleVoicesOn());
        setInitialButton();
        this.m_SessionView.setLastPage();
        this.m_SessionView.setScoreVisibility();
    }

    public void destroy() {
        releasePlayer();
        this.m_Player.closeSynth();
        this.m_SessionView.deleteAllPages();
        new ScoreResourceFactory(this.m_Context).destroyResourceList(this.m_ResList);
        this.m_ResList = null;
    }

    public void openSynth() {
        Logg.i(LOGTAG, "openSynth()");
        this.m_Player.openSynth();
        if (this.m_CdSession != null) {
            Logg.i(LOGTAG, "Tracks: " + this.m_CdSession.playerSettings.mixer.tracks.toString());
            this.m_Player.setup(this.m_CdSession.playerSettings);
        } else {
            Logg.i(LOGTAG, "cdSession null");
        }
        this.m_Player.setPitch((double) UserSettings.getInstance(this.m_Context).getPitch());
    }

    public void closeSynth() {
        this.m_Player.closeSynth();
    }

    private void releasePlayer() {
        this.m_Player.stop();
        this.m_Player.releaseSession();
    }

    public void setEditMode(boolean z) {
        if (z) {
            playingFinished();
            this.m_Global.setMode(2);
        } else {
            this.m_Global.setMode(0);
        }
        this.m_SessionView.setEditMode(z);
    }

    public void setPitch(double d) {
        this.m_Player.setPitch(d);
    }

    public void setBeatsPerMinute(int i) {
        this.m_Player.setBeatsPerMinute(i);
    }

    public void setIsGroup(boolean z) {
        this.m_Global.setIsGroup(z);
        boolean z2 = false;
        if (this.m_Global.getMode() == 1) {
            this.m_Global.setMode(0);
            this.m_Player.stop();
            z2 = true;
        }
        switchActiveButtonGroup();
        this.m_Player.setIsGroup(z);
        if (z2) {
            this.m_SessionView.postDelayed(new Runnable() {
                public void run() {
                    SessionManager.this.playingStart();
                }
            }, 100);
        }
    }

    public void setTrackProgram(int i, int i2) {
        this.m_Player.setTrackProgram(i, i2);
    }

    public void setVoice1Volume(int i, float f) {
        this.m_Player.setVoice1Volume(i, f);
    }

    public void setVoice2Volume(int i, float f) {
        this.m_Player.setVoice2Volume(i, f);
    }

    public void setMetronomeVolume(float f) {
        this.m_Player.setMetronomeVolume(f);
    }

    public int getPageCount() {
        List<ScoreResource> list = this.m_ResList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public int getVoiceIndex() {
        BarButton barButton;
        if (this.m_Global.getIsGroup() || (barButton = this.m_ActiveButton) == null) {
            return -1;
        }
        int staffIndex = barButton.getStaffIndex();
        Numa numa = new Numa(SWIGTYPE_p_NUMA.getCPtr(this.m_ActiveButton.getScore().getVoiceIndexes()), false);
        SWIGTYPE_p_int new_lintp = JniSource.new_lintp();
        JniLeptonica.numaGetIValue(numa, staffIndex, new_lintp);
        int lintp_value = JniSource.lintp_value(new_lintp);
        JniSource.delete_lintp(new_lintp);
        numa.finalize();
        Log.d(LOGTAG, "getVoiceIndex() - index:" + lintp_value);
        return lintp_value;
    }

    public void playingStart() {
        this.m_Global.setMode(1);
        this.m_SessionView.playingStart();
        playFromButton(this.m_ActiveButton);
        SessionManagerListener sessionManagerListener = this.m_Listener;
        if (sessionManagerListener != null) {
            sessionManagerListener.onPlayStart();
        }
    }

    public void playingFinished() {
        this.m_Global.setMode(0);
        this.m_Player.stop();
        this.m_SessionView.playingFinished();
        SessionManagerListener sessionManagerListener = this.m_Listener;
        if (sessionManagerListener != null) {
            sessionManagerListener.onUpdateUI();
        }
    }

    public void scrollButtonToView() {
        scrollButtonToView(this.m_ActiveButton, this.m_ActivePage);
    }

    public void scrollPageToView(int i) {
        this.m_SessionView.scrollPageToView(i);
    }

    public void scrollOnRotate() {
        this.m_SessionView.scrollOnRotate();
    }

    public void startRotate() {
        this.m_SessionView.startRotate();
    }

    public void deletePage(final int i) {
        final boolean z = score.getCPtr(this.m_ActiveButton.getScore()) == score.getCPtr(JniSource.sessionGet(this.m_Session, i));
        if (!this.m_SUtil.safeRemovePage(this.m_CdSession.getSessionFolderName(), this.m_Session, i)) {
            SessionManagerListener sessionManagerListener = this.m_Listener;
            if (sessionManagerListener != null) {
                sessionManagerListener.onSaveSessionError();
                return;
            }
            return;
        }
        this.m_SessionView.deletePage(i);
        this.m_SessionView.postDelayed(new Runnable() {
            public void run() {
                SessionManager.this.removePage(i, z);
            }
        }, 200);
    }

    /* access modifiers changed from: private */
    public void removePage(int i, boolean z) {
        if (z) {
            if (i > 0) {
                setInitialButton(i - 1);
            } else if (i < this.m_ResList.size() - 1) {
                setInitialButton(i);
            }
        }
        this.m_ResList.remove(i);
    }

    /* access modifiers changed from: private */
    public void setActiveButton(BarButton barButton) {
        synchronized (this.m_ActiveLock) {
            this.m_ActiveButton = barButton;
        }
    }

    /* access modifiers changed from: private */
    public void setActiveButton(BarButton barButton, int i) {
        synchronized (this.m_ActiveLock) {
            this.m_ActiveButton = barButton;
            this.m_ActivePage = i;
        }
    }

    private void setInitialButton() {
        setInitialButton(0);
    }

    private void setInitialButton(int i) {
        if (this.m_Session.getN() > 0) {
            score sessionGet = JniSource.sessionGet(this.m_Session, i);
            this.m_SessionView.setBarButtonSelected(sessionGet, JniSource.baraaGetBar(this.m_Global.getIsGroup() ? sessionGet.getGroupBaraa() : sessionGet.getSingleBaraa(), 0, 0), true);
        }
    }

    private void playFromButton(BarButton barButton) {
        synchronized (this.m_ActiveLock) {
            if (barButton != null) {
                this.m_Player.playFromScore(barButton.getScore(), barButton.getStaffIndex(), barButton.getBarIndex());
            }
        }
    }

    private void switchActiveButtonGroup() {
        synchronized (this.m_ActiveLock) {
            score score = this.m_ActiveButton.getScore();
            baraa singleBaraa = score.getSingleBaraa();
            baraa groupBaraa = score.getGroupBaraa();
            int n = singleBaraa.getN() / groupBaraa.getN();
            if (this.m_ActiveButton.isGroupButton && !this.m_Global.getIsGroup()) {
                this.m_SessionView.setBarButtonSelected(score, JniSource.baraaGetBar(singleBaraa, this.m_ActiveButton.getStaffIndex() * n, this.m_ActiveButton.getBarIndex()), true);
            } else if (!this.m_ActiveButton.isGroupButton && this.m_Global.getIsGroup()) {
                this.m_SessionView.setBarButtonSelected(score, JniSource.baraaGetBar(groupBaraa, this.m_ActiveButton.getStaffIndex() / n, this.m_ActiveButton.getBarIndex()), true);
            }
        }
    }

    private void scrollButtonToView(BarButton barButton, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += this.m_ResList.get(i3).getHeight();
        }
        float width = ((float) this.m_SessionView.getWidth()) / ((float) this.m_ResList.get(0).getWidth());
        int i4 = (int) (((float) barButton.h) * width);
        int i5 = ((int) (((float) i2) * width)) + ((int) (((float) barButton.y) * width));
        int i6 = i5 + i4;
        int height = this.m_SessionView.getHeight();
        int scrollY = this.m_SessionView.getScrollY();
        int i7 = scrollY + height;
        if (i6 < scrollY || i5 > i7) {
            this.m_SessionView.scrollTo(0, i5);
        } else if (i5 < scrollY) {
            if (i6 - scrollY < (i4 < height ? i4 - (i4 / 8) : height / 8)) {
                this.m_SessionView.smoothScrollTo(0, i5);
            }
        } else {
            if (i7 - i5 < (i4 < height ? i4 - (i4 / 8) : height / 8)) {
                this.m_SessionView.smoothScrollTo(0, i6 - height);
            }
        }
    }

    private void log(String str) {
        Logg.d(LOGTAG, str);
    }
}
