package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.activity.ScannerActivity;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.ImportDialog;
import com.xemsoft.sheetmusicscanner2.dialog.MixerDialog;
import com.xemsoft.sheetmusicscanner2.dialog.PlayerSettingsDialog;
import com.xemsoft.sheetmusicscanner2.dialog.RenameDialog;
import com.xemsoft.sheetmusicscanner2.dialog.SaveSongDialog;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.player.Global;
import com.xemsoft.sheetmusicscanner2.player.SessionManager;
import com.xemsoft.sheetmusicscanner2.player.SessionView;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResource;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResourceFactory;
import com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;
import java.util.List;

public class PlayLayout extends RelativeLayout {
    private static final String LOGTAG = "PlayLayout.java";
    /* access modifiers changed from: private */
    public ScannerActivity m_Activity = null;
    private AlertBox m_AlertBox;
    /* access modifiers changed from: private */
    public int m_AnalyzeError = 0;
    /* access modifiers changed from: private */
    public RelativeLayout m_BarQuickAdd;
    /* access modifiers changed from: private */
    public TintButton m_ButAddPage;
    private TintImageButton m_ButBack;
    /* access modifiers changed from: private */
    public TintButton m_ButEdit;
    private TintImageButton m_ButExport;
    private TintImageButton m_ButHelp;
    private TintImageButton m_ButMixer;
    /* access modifiers changed from: private */
    public TintImageButton m_ButPlay;
    private TintButton m_ButQuickAdd;
    private TintImageButton m_ButSettings;
    /* access modifiers changed from: private */
    public TintButton m_ButSongName;
    /* access modifiers changed from: private */
    public CdSession m_CdSession = null;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        /* JADX WARNING: type inference failed for: r9v11, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.but_add_page:
                    boolean unused = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.addPage();
                    return;
                case R.id.but_back:
                    PlayLayout.this.goBack(true);
                    return;
                case R.id.but_edit:
                    boolean unused2 = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.toggleEdit();
                    return;
                case R.id.but_export:
                    boolean unused3 = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.export();
                    return;
                case R.id.but_help:
                    boolean unused4 = PlayLayout.this.m_ShowQuickAdd = false;
                    ActivityHelper.openHelp(PlayLayout.this.m_Activity);
                    return;
                case R.id.but_instrument:
                    boolean unused5 = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.m_MixerDialog.open(PlayLayout.this.m_CdSession.playerSettings.mixer, PlayLayout.this.m_CdSession.playerSettings.metronome, PlayLayout.this.m_SessionManager.getVoiceIndex(), PlayLayout.this.m_Session);
                    return;
                case R.id.but_play:
                    PlayLayout.this.togglePlay();
                    return;
                case R.id.but_quick_add:
                    PlayLayout.this.quickAdd();
                    return;
                case R.id.but_settings:
                    boolean unused6 = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.m_PlayerSettingsDlg.open(PlayLayout.this.m_Prefs.getPitch(), PlayLayout.this.m_CdSession.getBpm(), PlayLayout.this.m_CdSession.playerSettings.isSwingOn, JniSource.sessionIsMultipleVoices(PlayLayout.this.m_Session) != 0, PlayLayout.this.m_CdSession.isMultipleVoicesOn());
                    return;
                case R.id.but_song_name:
                    boolean unused7 = PlayLayout.this.m_ShowQuickAdd = false;
                    PlayLayout.this.openRenameDialog();
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public Context m_Context;
    private SessionManager.SessionManagerListener m_ControllerListener = new SessionManager.SessionManagerListener() {
        public void onUpdateUI() {
            PlayLayout.this.updateUI();
        }

        public void onAddPage(int i) {
            int unused = PlayLayout.this.m_InsertIdx = i;
            if (PlayLayout.this.m_Listener != null) {
                Log.d(PlayLayout.LOGTAG, "onAddPage()");
                PlayLayout.this.storeSessionGlobals();
                PlayLayout.this.m_Listener.onImport(PlayLayout.this.m_CdSession.getSessionFolderName(), PlayLayout.this.m_InsertIdx, PlayLayout.this.m_NewSong, PlayLayout.this.m_Global.getMode() == 2, false);
            }
        }

        public void onDeletePage(int i) {
            PlayLayout.this.openDeletePageDialog(i);
        }

        public void onSaveSessionError() {
            PlayLayout.this.openSaveSessionErrorDialog();
        }

        public void onPlayStart() {
            boolean unused = PlayLayout.this.m_ShowQuickAdd = false;
            PlayLayout.this.updateUI();
        }

        public void onSetEditMode(boolean z) {
            PlayLayout.this.setEdit(z);
        }
    };
    /* access modifiers changed from: private */
    public Global m_Global;
    private InstrumentsUtility m_IUtil;
    private ImportDialog m_ImportDlg;
    /* access modifiers changed from: private */
    public int m_InsertIdx = 0;
    private boolean m_IsOpen = false;
    /* access modifiers changed from: private */
    public LinearLayout m_LayoutBut;
    /* access modifiers changed from: private */
    public PlayLayoutListener m_Listener = null;
    /* access modifiers changed from: private */
    public LoadLayout m_LoadLayout;
    /* access modifiers changed from: private */
    public MixerDialog m_MixerDialog;
    private MixerDialog.MixerDialogListener m_MixerListener = new MixerDialog.MixerDialogListener() {
        public void onSetTrackProgram(int i, int i2) {
            PlayLayout.this.m_SessionManager.setTrackProgram(i, i2);
        }

        public void onSetTrackVolume1(int i, float f) {
            PlayLayout.this.m_SessionManager.setVoice1Volume(i, f);
        }

        public void onSetTrackVolume2(int i, float f) {
            PlayLayout.this.m_SessionManager.setVoice2Volume(i, f);
        }

        public void onSetMetronomeVolume(float f) {
            PlayLayout.this.m_SessionManager.setMetronomeVolume(f);
        }

        public void onDone() {
            if (PlayLayout.this.m_CdSession != null) {
                PlayLayout.this.m_SUtil.cdUpdateSession(PlayLayout.this.m_CdSession);
                PlayLayout.this.m_SUtil.storePlayerSettings(PlayLayout.this.m_CdSession);
                PlayLayout.this.updateUI();
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean m_NewSong = false;
    private boolean m_PlayEdit = false;
    /* access modifiers changed from: private */
    public PlayerSettingsDialog m_PlayerSettingsDlg;
    private PlayerSettingsDialog.PlayerSettingsListener m_PlayerSettingsListener = new PlayerSettingsDialog.PlayerSettingsListener() {
        public void onHz(int i) {
            PlayLayout.this.m_SessionManager.setPitch((double) i);
            PlayLayout.this.m_Prefs.setPitch(i);
        }

        public int onResetHz() {
            PlayLayout.this.m_Prefs.resetPitch();
            int pitch = PlayLayout.this.m_Prefs.getPitch();
            PlayLayout.this.m_SessionManager.setPitch((double) pitch);
            return pitch;
        }

        public void onBpm(int i) {
            PlayLayout.this.m_SessionManager.setBeatsPerMinute(i);
            PlayLayout.this.m_CdSession.setBpm(i);
            PlayLayout.this.m_Prefs.setBpm(i);
        }

        public void onSwing(boolean z) {
            PlayLayout.this.m_CdSession.playerSettings.isSwingOn = z;
        }

        public void onIsGroup(boolean z) {
            PlayLayout.this.m_SessionManager.setIsGroup(z);
            PlayLayout.this.m_CdSession.setMultipleVoicesOn(z);
            PlayLayout.this.m_Prefs.setMultipleVoicesOn(z);
        }

        public void onDone() {
            if (PlayLayout.this.m_CdSession != null) {
                PlayLayout.this.m_SUtil.cdUpdateSession(PlayLayout.this.m_CdSession);
                PlayLayout.this.m_SUtil.storePlayerSettings(PlayLayout.this.m_CdSession);
                PlayLayout.this.updateUI();
            }
        }
    };
    /* access modifiers changed from: private */
    public UserSettings m_Prefs;
    private RenameDialog m_RenameDialog;
    /* access modifiers changed from: private */
    public Resources m_Res;
    /* access modifiers changed from: private */
    public SessionUtility m_SUtil;
    private SaveSongDialog m_SaveDialog;
    /* access modifiers changed from: private */
    public session m_Session = null;
    /* access modifiers changed from: private */
    public SessionManager m_SessionManager;
    /* access modifiers changed from: private */
    public SessionView m_SessionView;
    /* access modifiers changed from: private */
    public boolean m_ShowQuickAdd = false;
    private View[] m_ViewList = new View[10];

    public interface PlayLayoutListener {
        void onBack(Intent intent);

        void onExport(CdSession cdSession, int i);

        void onFeedback();

        void onImport(String str, int i, boolean z, boolean z2, boolean z3);
    }

    public PlayLayout(Context context) {
        super(context);
        init(context);
    }

    public PlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PlayLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_play, this, true);
        this.m_Context = context;
        this.m_Res = getResources();
        this.m_Global = Global.getInstance();
        this.m_Prefs = UserSettings.getInstance(context);
        this.m_SUtil = SessionUtility.getInstance(context);
        this.m_IUtil = InstrumentsUtility.getInstance(context);
        this.m_SessionView = (SessionView) findViewById(R.id.view_session);
        Object[] objArr = this.m_ViewList;
        TintImageButton tintImageButton = (TintImageButton) inflate.findViewById(R.id.but_back);
        this.m_ButBack = tintImageButton;
        int i = 0;
        objArr[0] = tintImageButton;
        Object[] objArr2 = this.m_ViewList;
        TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_song_name);
        this.m_ButSongName = tintButton;
        objArr2[1] = tintButton;
        Object[] objArr3 = this.m_ViewList;
        TintButton tintButton2 = (TintButton) inflate.findViewById(R.id.but_edit);
        this.m_ButEdit = tintButton2;
        objArr3[2] = tintButton2;
        this.m_LayoutBut = (LinearLayout) inflate.findViewById(R.id.layout_bot);
        Object[] objArr4 = this.m_ViewList;
        TintImageButton tintImageButton2 = (TintImageButton) inflate.findViewById(R.id.but_help);
        this.m_ButHelp = tintImageButton2;
        objArr4[3] = tintImageButton2;
        Object[] objArr5 = this.m_ViewList;
        TintImageButton tintImageButton3 = (TintImageButton) inflate.findViewById(R.id.but_export);
        this.m_ButExport = tintImageButton3;
        objArr5[4] = tintImageButton3;
        Object[] objArr6 = this.m_ViewList;
        TintImageButton tintImageButton4 = (TintImageButton) inflate.findViewById(R.id.but_instrument);
        this.m_ButMixer = tintImageButton4;
        objArr6[5] = tintImageButton4;
        Object[] objArr7 = this.m_ViewList;
        TintImageButton tintImageButton5 = (TintImageButton) inflate.findViewById(R.id.but_settings);
        this.m_ButSettings = tintImageButton5;
        objArr7[6] = tintImageButton5;
        Object[] objArr8 = this.m_ViewList;
        TintImageButton tintImageButton6 = (TintImageButton) inflate.findViewById(R.id.but_play);
        this.m_ButPlay = tintImageButton6;
        objArr8[7] = tintImageButton6;
        Object[] objArr9 = this.m_ViewList;
        TintButton tintButton3 = (TintButton) inflate.findViewById(R.id.but_add_page);
        this.m_ButAddPage = tintButton3;
        objArr9[8] = tintButton3;
        Object[] objArr10 = this.m_ViewList;
        TintButton tintButton4 = (TintButton) inflate.findViewById(R.id.but_quick_add);
        this.m_ButQuickAdd = tintButton4;
        objArr10[9] = tintButton4;
        this.m_BarQuickAdd = (RelativeLayout) inflate.findViewById(R.id.bar_quick_add);
        this.m_ButMixer.setImageResource(this.m_IUtil.iconWithInstrumentProgram(0));
        while (true) {
            View[] viewArr = this.m_ViewList;
            if (i < viewArr.length) {
                viewArr[i].setOnClickListener(this.m_ClickListener);
                i++;
            } else {
                this.m_LoadLayout = (LoadLayout) inflate.findViewById(R.id.load_layout);
                this.m_AlertBox = new AlertBox(context);
                this.m_RenameDialog = new RenameDialog(context);
                this.m_SaveDialog = new SaveSongDialog(context);
                this.m_ImportDlg = new ImportDialog(context);
                PlayerSettingsDialog playerSettingsDialog = new PlayerSettingsDialog(context);
                this.m_PlayerSettingsDlg = playerSettingsDialog;
                playerSettingsDialog.setListener(this.m_PlayerSettingsListener);
                MixerDialog mixerDialog = new MixerDialog(context);
                this.m_MixerDialog = mixerDialog;
                mixerDialog.setListener(this.m_MixerListener);
                SessionManager sessionManager = new SessionManager(context, this.m_SessionView);
                this.m_SessionManager = sessionManager;
                sessionManager.setListener(this.m_ControllerListener);
                return;
            }
        }
    }

    public void setActivity(ScannerActivity scannerActivity) {
        this.m_Activity = scannerActivity;
    }

    public void setListener(PlayLayoutListener playLayoutListener) {
        this.m_Listener = playLayoutListener;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [com.xemsoft.sheetmusicscanner2.widget.TintButton, android.view.View] */
    public void resume() {
        this.m_SessionManager.openSynth();
        Utils.hideSoftKeyboard(this.m_Context, this.m_ButSongName, 300);
        updateUI();
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    public void pause() {
        CdSession cdSession = this.m_CdSession;
        if (cdSession != null) {
            this.m_SUtil.cdUpdateSession(cdSession);
        }
        if (this.m_Global.getMode() != 2) {
            this.m_SessionManager.playingFinished();
        }
        this.m_SessionManager.closeSynth();
        ActivityHelper.keepScreenOn(this.m_Activity, false);
    }

    public void destroy() {
        this.m_SessionManager.destroy();
        this.m_LoadLayout.close();
        this.m_AlertBox.close();
        this.m_PlayerSettingsDlg.close();
        this.m_MixerDialog.close();
        this.m_RenameDialog.close();
        this.m_SaveDialog.close();
        this.m_ImportDlg.close();
        this.m_NewSong = false;
        this.m_IsOpen = false;
        this.m_ShowQuickAdd = false;
    }

    public void setIntent(Intent intent) {
        openIntent(intent);
        this.m_IsOpen = true;
    }

    public void onConfigurationChanged(Configuration configuration) {
        Log.w(LOGTAG, "onConfigurationChanged()");
        if (this.m_IsOpen) {
            this.m_MixerDialog.rotate(configuration);
            if (Global.getInstance().getMode() == 1) {
                this.m_SessionView.postDelayed(new Runnable() {
                    public void run() {
                        PlayLayout.this.m_SessionManager.scrollButtonToView();
                    }
                }, 300);
                return;
            }
            this.m_SessionManager.startRotate();
            this.m_SessionView.postDelayed(new Runnable() {
                public void run() {
                    PlayLayout.this.m_SessionManager.scrollOnRotate();
                }
            }, 300);
        }
    }

    private void openIntent(Intent intent) {
        boolean booleanExtra = intent.getBooleanExtra(Constants.BUNDLE_NEW_SONG, false);
        this.m_NewSong = booleanExtra;
        this.m_ShowQuickAdd = booleanExtra;
        this.m_InsertIdx = intent.getIntExtra(Constants.BUNDLE_INSERT_IDX, 0);
        this.m_PlayEdit = intent.getBooleanExtra(Constants.BUNDLE_PLAY_EDIT, false);
        this.m_AnalyzeError = intent.getIntExtra(Constants.BUNDLE_ANALYZE_ERROR, 0);
        String stringExtra = intent.getStringExtra(Constants.BUNDLE_SONG_FOLDER);
        if (stringExtra != null) {
            loadSession(stringExtra);
        } else {
            openLoadSessionErrorDialog();
        }
    }

    public void goBack(boolean z) {
        String str;
        log("goBack");
        if (this.m_NewSong) {
            openSaveDialog();
        } else if (z && this.m_Prefs.ratingShouldDisplayDialog()) {
            PlayLayoutListener playLayoutListener = this.m_Listener;
            if (playLayoutListener != null) {
                playLayoutListener.onFeedback();
            }
        } else {
            CdSession cdSession = this.m_CdSession;
            if (cdSession == null) {
                str = null;
            } else {
                str = cdSession.getSessionFolderName();
            }
            Intent createPlayBackIntent = ActivityHelper.createPlayBackIntent(str);
            PlayLayoutListener playLayoutListener2 = this.m_Listener;
            if (playLayoutListener2 != null) {
                playLayoutListener2.onBack(createPlayBackIntent);
            }
        }
    }

    private void loadSession(final String str) {
        Log.d(LOGTAG, "loadSession enter");
        CdSession cdGetSession = this.m_SUtil.cdGetSession(str);
        this.m_CdSession = cdGetSession;
        if (cdGetSession == null) {
            Log.w(LOGTAG, "loadSession - CdSession NULL");
            openLoadSessionErrorDialog();
            return;
        }
        log("loadSession " + getLoadTitle());
        this.m_LoadLayout.open(getLoadTitle());
        this.m_SessionView.post(new Runnable() {
            public void run() {
                PlayLayout.this.m_SUtil.loadSessionAsync(str, new SessionUtility.LoadSessionListener() {
                    public void onDone(session session) {
                        if (session != null) {
                            session unused = PlayLayout.this.m_Session = session;
                            PlayLayout.this.m_SUtil.loadPlayerSettings(PlayLayout.this.m_CdSession, session);
                            PlayLayout.this.loadResources();
                            return;
                        }
                        PlayLayout.this.openLoadSessionErrorDialog();
                        Log.w(PlayLayout.LOGTAG, "- load session failed: " + str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadResources() {
        Log.w(LOGTAG, "loadResources");
        this.m_LoadLayout.setMaxProgress(this.m_Session.getN());
        new ScoreResourceFactory(this.m_Context).getResourceListAsync(this.m_CdSession, this.m_Session, new ScoreResourceFactory.LoadProgressListener() {
            public void onProgress(int i) {
                PlayLayout.this.m_LoadLayout.setProgress(i);
            }

            public void onDone(List<ScoreResource> list) {
                if (list != null) {
                    PlayLayout.this.setResources(list);
                    PlayLayout.this.m_SessionView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                        /* access modifiers changed from: private */
                        public View.OnLayoutChangeListener _this = this;
                        /* access modifiers changed from: private */
                        public boolean m_Ended = false;
                        private boolean m_FirstEvent = true;
                        /* access modifiers changed from: private */
                        public volatile boolean m_LayoutChanged = false;

                        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                            if (this.m_FirstEvent) {
                                this.m_FirstEvent = false;
                                delay();
                                return;
                            }
                            this.m_LayoutChanged = true;
                        }

                        /* access modifiers changed from: private */
                        public void delay() {
                            PlayLayout.this.m_SessionView.postDelayed(new Runnable() {
                                public void run() {
                                    if (AnonymousClass1.this.m_Ended) {
                                        return;
                                    }
                                    if (!AnonymousClass1.this.m_LayoutChanged) {
                                        boolean unused = AnonymousClass1.this.m_Ended = true;
                                        PlayLayout.this.m_SessionView.removeOnLayoutChangeListener(AnonymousClass1.this._this);
                                        PlayLayout.this.m_SessionManager.scrollPageToView(PlayLayout.this.m_InsertIdx);
                                        PlayLayout.this.m_LoadLayout.close();
                                        PlayLayout.this.openAnalyzeErrorDialog();
                                        return;
                                    }
                                    boolean unused2 = AnonymousClass1.this.m_LayoutChanged = false;
                                    AnonymousClass1.this.delay();
                                }
                            }, 500);
                        }
                    });
                    return;
                }
                Log.w(PlayLayout.LOGTAG, "loadResources - load resources failed");
                PlayLayout.this.openLoadSessionErrorDialog();
            }
        });
    }

    private String getLoadTitle() {
        String str;
        String string = this.m_Res.getString(R.string.openingLabel);
        int lastIndexOf = string.lastIndexOf("%");
        if (lastIndexOf == -1) {
            str = string + " ";
        } else {
            str = string.substring(0, lastIndexOf);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        CdSession cdSession = this.m_CdSession;
        sb.append(cdSession == null ? BuildConfig.FLAVOR : cdSession.getDisplayName());
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public void setResources(List<ScoreResource> list) {
        Log.d(LOGTAG, "setResources");
        this.m_SessionManager.init(this.m_CdSession, this.m_Session, list);
        this.m_SessionManager.setEditMode(this.m_PlayEdit);
        this.m_SessionManager.openSynth();
        Log.d(LOGTAG, "- session loaded");
        updateUI();
    }

    /* access modifiers changed from: private */
    public void updateUI() {
        updateUI(true);
    }

    /* access modifiers changed from: private */
    public void updateUI(final boolean z) {
        log("UpdateUI");
        this.m_Activity.runOnUiThread(new Runnable() {
            /* JADX WARNING: type inference failed for: r1v3, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
            public void run() {
                int mode = PlayLayout.this.m_Global.getMode();
                int i = 4;
                boolean z = false;
                int i2 = mode == 2 ? 0 : 4;
                if (mode != 2) {
                    i = 0;
                }
                PlayLayout.this.log("Mode:" + mode + " Force:" + z);
                if (PlayLayout.this.m_CdSession != null) {
                    String displayName = PlayLayout.this.m_CdSession.getDisplayName();
                    if (z) {
                        PlayLayout.this.m_ButSongName.setText(displayName);
                    }
                }
                PlayLayout.this.m_ButEdit.setText(PlayLayout.this.m_Context.getString(mode == 2 ? R.string.gun_cc_fDh_normalTitle : R.string.fbw_DL_aB4_title));
                PlayLayout.this.m_ButPlay.setImageResource(mode == 1 ? R.drawable.pause_group : R.drawable.play_icon);
                PlayLayout.this.m_ButPlay.setEnabled(PlayLayout.this.m_SessionManager.getPageCount() > 0);
                PlayLayout.this.m_ButSongName.setClickable(mode == 2);
                PlayLayout.this.m_ButSongName.setTextColor(PlayLayout.this.m_Res.getColor(mode == 2 ? R.color.app_bar_but_active : R.color.app_gray_text));
                PlayLayout.this.m_BarQuickAdd.setVisibility((!PlayLayout.this.m_ShowQuickAdd || mode == 2) ? 8 : 0);
                if (z) {
                    PlayLayout.this.m_LayoutBut.setVisibility(i);
                    PlayLayout.this.m_ButAddPage.setVisibility(i2);
                    PlayLayout.this.invalidate();
                }
                ? access$2600 = PlayLayout.this.m_Activity;
                if (mode == 1) {
                    z = true;
                }
                ActivityHelper.keepScreenOn(access$2600, z);
            }
        });
    }

    public void stop() {
        if (this.m_Global.getMode() == 1) {
            this.m_SessionManager.playingFinished();
            updateUI();
        }
    }

    public void togglePlay() {
        int mode = this.m_Global.getMode();
        if (mode == 0) {
            this.m_SessionManager.playingStart();
            updateUI();
        } else if (mode == 1) {
            this.m_SessionManager.playingFinished();
            updateUI();
        }
    }

    /* access modifiers changed from: private */
    public void toggleEdit() {
        if (this.m_Global.getMode() != 2) {
            this.m_ShowQuickAdd = false;
            this.m_SessionManager.setEditMode(true);
        } else {
            this.m_SessionManager.setEditMode(false);
        }
        updateUI(false);
        crossFadeButtons();
    }

    /* access modifiers changed from: private */
    public void setEdit(boolean z) {
        int mode = this.m_Global.getMode();
        if (!z && mode == 2) {
            this.m_SessionManager.setEditMode(false);
        } else if (z && mode != 2) {
            this.m_ShowQuickAdd = false;
            this.m_SessionManager.setEditMode(true);
        } else {
            return;
        }
        updateUI(false);
        crossFadeButtons();
    }

    /* access modifiers changed from: private */
    public void addPage() {
        addPage(false);
    }

    /* access modifiers changed from: private */
    public void quickAdd() {
        addPage(true);
    }

    private void addPage(boolean z) {
        this.m_InsertIdx = this.m_Session.getN();
        if (this.m_Listener != null) {
            Log.d(LOGTAG, "addPage()");
            storeSessionGlobals();
            this.m_Listener.onImport(this.m_CdSession.getSessionFolderName(), this.m_InsertIdx, this.m_NewSong, this.m_Global.getMode() == 2, z);
        }
    }

    /* access modifiers changed from: private */
    public void storeSessionGlobals() {
        Global instance = Global.getInstance();
        instance.setSession(this.m_Session, this.m_Context);
        instance.setInsertAtIndex(this.m_InsertIdx);
    }

    /* access modifiers changed from: private */
    public void export() {
        PlayLayoutListener playLayoutListener = this.m_Listener;
        if (playLayoutListener != null) {
            playLayoutListener.onExport(this.m_CdSession, this.m_SessionManager.getVoiceIndex());
        }
    }

    /* access modifiers changed from: private */
    public void openRenameDialog() {
        this.m_RenameDialog.close();
        this.m_RenameDialog.open(this.m_Context.getString(R.string.songRenameDialog_title), this.m_Context.getString(R.string.songRenameDialog_message), this.m_CdSession.getDisplayName(), this.m_Context.getString(R.string.songRenameDialog_button_ok), this.m_Context.getString(R.string.songRenameDialog_button_cancel), new RenameDialog.OnRenameListener() {
            public void onCancel() {
            }

            public void onOk(String str) {
                PlayLayout.this.m_SUtil.cdUpdateSessionDisplayName(PlayLayout.this.m_CdSession.getSessionFolderName(), str);
                PlayLayout.this.m_CdSession.setDisplayName(str);
                PlayLayout.this.fadeSongName();
                PlayLayout.this.updateUI(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void openDeletePageDialog(final int i) {
        this.m_AlertBox.close();
        this.m_AlertBox.open(this.m_Res.getString(R.string.deleteConfirmationAlert_title), this.m_Res.getString(R.string.deleteConfirmationAlert_message), this.m_Context.getString(R.string.deleteConfirmationAlert_button_delete), this.m_Context.getString(R.string.deleteConfirmationAlert_button_cancel), new AlertBox.OnAlertListener() {
            public void onCancel() {
            }

            public void onOk() {
                PlayLayout.this.m_SessionManager.deletePage(i);
            }
        });
        this.m_AlertBox.setOkRed();
    }

    /* access modifiers changed from: private */
    public void openLoadSessionErrorDialog() {
        this.m_AlertBox.close();
        this.m_AlertBox.open(this.m_Context.getString(R.string.loadingErrorAlert_title), this.m_Context.getString(R.string.loadingErrorAlert_message), this.m_Context.getString(R.string.loadingErrorAlert_button_ok), (String) null, new AlertBox.OnAlertListener() {
            public void onOk() {
                PlayLayout.this.goBack(false);
            }

            public void onCancel() {
                PlayLayout.this.goBack(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void openSaveSessionErrorDialog() {
        this.m_AlertBox.close();
        this.m_AlertBox.open(this.m_Context.getString(R.string.sessionSaveErrorAlert_title), this.m_Context.getString(R.string.sessionSaveErrorAlert_message), this.m_Context.getString(R.string.sessionSaveErrorAlert_button_ok), (String) null, (AlertBox.OnAlertListener) null);
    }

    private void openSaveDialog() {
        this.m_AlertBox.close();
        this.m_SaveDialog.open(new SaveSongDialog.SaveSongDialogListener() {
            public void onCancel() {
            }

            public void onSave() {
                PlayLayout.this.openSaveLeaveDialog();
            }

            public void onDelete() {
                PlayLayout.this.openDeleteSongDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void openDeleteSongDialog() {
        this.m_AlertBox.close();
        AlertBox alertBox = this.m_AlertBox;
        String string = this.m_Context.getString(R.string.newSongDeleteAlert_title);
        alertBox.open(string, this.m_CdSession.getDisplayName() + "\n\n" + this.m_Context.getString(R.string.newSongDeleteAlert_message), this.m_Context.getString(R.string.newSongDeleteAlert_button_ok), this.m_Context.getString(R.string.newSongDeleteAlert_button_cancel), new AlertBox.OnAlertListener() {
            public void onCancel() {
            }

            public void onOk() {
                boolean unused = PlayLayout.this.m_NewSong = false;
                PlayLayout.this.m_SUtil.deleteSessionFolder(PlayLayout.this.m_CdSession.getSessionFolderName());
                PlayLayout.this.m_SUtil.cdDeleteSession(PlayLayout.this.m_CdSession.getSessionFolderName());
                CdSession unused2 = PlayLayout.this.m_CdSession = null;
                PlayLayout.this.goBack(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void openSaveLeaveDialog() {
        this.m_RenameDialog.close();
        this.m_RenameDialog.open(this.m_Context.getString(R.string.newSongSavePrompt_title), this.m_Context.getString(R.string.newSongSavePrompt_message), this.m_CdSession.getDisplayName(), this.m_Context.getString(R.string.newSongSavePrompt_button_save), this.m_Context.getString(R.string.newSongSavePrompt_button_cancel), new RenameDialog.OnRenameListener() {
            public void onCancel() {
            }

            public void onOk(String str) {
                boolean unused = PlayLayout.this.m_NewSong = false;
                PlayLayout.this.m_SUtil.cdUpdateSessionDisplayName(PlayLayout.this.m_CdSession.getSessionFolderName(), str);
                PlayLayout.this.m_CdSession.setDisplayName(str);
                PlayLayout.this.m_Prefs.ratingIncreaseSuccesCount();
                PlayLayout.this.log("openSaveLeaveDialog ok");
                PlayLayout.this.goBack(false);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void openAnalyzeErrorDialog() {
        /*
            r10 = this;
            int r0 = r10.m_AnalyzeError
            r1 = 0
            r2 = 2
            if (r0 == r2) goto L_0x0048
            r3 = 8
            if (r0 == r3) goto L_0x000b
            goto L_0x0043
        L_0x000b:
            com.xemsoft.sheetmusicscanner2.persist.UserSettings r0 = r10.m_Prefs
            int r0 = r0.getDistortionWarningCancelCount()
            if (r0 >= r2) goto L_0x0043
            com.xemsoft.sheetmusicscanner2.persist.UserSettings r0 = r10.m_Prefs
            int r0 = r0.getDistortionWarningShowMeCount()
            if (r0 != 0) goto L_0x0043
            android.content.Context r0 = r10.m_Context
            r1 = 2131755124(0x7f100074, float:1.9141118E38)
            java.lang.String r1 = r0.getString(r1)
            android.content.Context r0 = r10.m_Context
            r2 = 2131755123(0x7f100073, float:1.9141116E38)
            java.lang.String r0 = r0.getString(r2)
            android.content.Context r2 = r10.m_Context
            r3 = 2131755121(0x7f100071, float:1.9141112E38)
            java.lang.String r2 = r2.getString(r3)
            android.content.Context r3 = r10.m_Context
            r4 = 2131755122(0x7f100072, float:1.9141114E38)
            java.lang.String r3 = r3.getString(r4)
            r6 = r0
            r5 = r1
            r8 = r2
            goto L_0x0066
        L_0x0043:
            r5 = r1
            r6 = r5
            r7 = r6
            r8 = r7
            goto L_0x0067
        L_0x0048:
            android.content.Context r0 = r10.m_Context
            r2 = 2131755207(0x7f1000c7, float:1.9141287E38)
            java.lang.String r0 = r0.getString(r2)
            android.content.Context r2 = r10.m_Context
            r3 = 2131755206(0x7f1000c6, float:1.9141285E38)
            java.lang.String r2 = r2.getString(r3)
            android.content.Context r3 = r10.m_Context
            r4 = 2131755205(0x7f1000c5, float:1.9141283E38)
            java.lang.String r3 = r3.getString(r4)
            r5 = r0
            r8 = r1
            r6 = r2
        L_0x0066:
            r7 = r3
        L_0x0067:
            if (r5 == 0) goto L_0x0073
            com.xemsoft.sheetmusicscanner2.dialog.AlertBox r4 = r10.m_AlertBox
            com.xemsoft.sheetmusicscanner2.layout.PlayLayout$14 r9 = new com.xemsoft.sheetmusicscanner2.layout.PlayLayout$14
            r9.<init>()
            r4.open(r5, r6, r7, r8, r9)
        L_0x0073:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.PlayLayout.openAnalyzeErrorDialog():void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: com.xemsoft.sheetmusicscanner2.widget.TintButton} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: com.xemsoft.sheetmusicscanner2.widget.TintButton} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: android.widget.LinearLayout} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: android.widget.LinearLayout} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void crossFadeButtons() {
        /*
            r3 = this;
            com.xemsoft.sheetmusicscanner2.player.Global r0 = r3.m_Global
            int r0 = r0.getMode()
            r1 = 2
            if (r0 != r1) goto L_0x000c
            android.widget.LinearLayout r2 = r3.m_LayoutBut
            goto L_0x000e
        L_0x000c:
            com.xemsoft.sheetmusicscanner2.widget.TintButton r2 = r3.m_ButAddPage
        L_0x000e:
            if (r0 != r1) goto L_0x0013
            com.xemsoft.sheetmusicscanner2.widget.TintButton r0 = r3.m_ButAddPage
            goto L_0x0015
        L_0x0013:
            android.widget.LinearLayout r0 = r3.m_LayoutBut
        L_0x0015:
            com.xemsoft.sheetmusicscanner2.util.Utils.crossFadeViews(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.PlayLayout.crossFadeButtons():void");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.widget.TintButton, android.widget.Button] */
    /* access modifiers changed from: private */
    public void fadeSongName() {
        Utils.fadeButtonText(this.m_ButSongName, this.m_CdSession.getDisplayName());
    }

    /* access modifiers changed from: private */
    public void log(String str) {
        Logg.d(LOGTAG, str);
    }
}
