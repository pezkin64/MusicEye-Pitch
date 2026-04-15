package com.xemsoft.sheetmusicscanner2.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.ScannerApplication;
import com.xemsoft.sheetmusicscanner2.billing.BillingUtils;
import com.xemsoft.sheetmusicscanner2.billing.SubscriptionUtility;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.ExportDialog;
import com.xemsoft.sheetmusicscanner2.dialog.FeedbackDialogs;
import com.xemsoft.sheetmusicscanner2.dialog.ImportDialog;
import com.xemsoft.sheetmusicscanner2.dialog.PrepareDialog;
import com.xemsoft.sheetmusicscanner2.dialog.RenameDialog;
import com.xemsoft.sheetmusicscanner2.dialog.SaveFileDialog;
import com.xemsoft.sheetmusicscanner2.dialog.SplashDialog;
import com.xemsoft.sheetmusicscanner2.export.ExportTypes;
import com.xemsoft.sheetmusicscanner2.export.ExportUtility;
import com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout;
import com.xemsoft.sheetmusicscanner2.layout.HighlightLayout;
import com.xemsoft.sheetmusicscanner2.layout.HomeLayout;
import com.xemsoft.sheetmusicscanner2.layout.Intro1Layout;
import com.xemsoft.sheetmusicscanner2.layout.Intro2Layout;
import com.xemsoft.sheetmusicscanner2.layout.PaywallLayout;
import com.xemsoft.sheetmusicscanner2.layout.PlayLayout;
import com.xemsoft.sheetmusicscanner2.layout.SettingsLayout;
import com.xemsoft.sheetmusicscanner2.layout.SongListLayout;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.player.Global;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.synth.Synth;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.FlurryUtils;
import com.xemsoft.sheetmusicscanner2.util.OnDoneListener;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ScannerActivity extends AppCompatActivity {
    private static final String LOGTAG = "ScannerActivity";
    private static final int MODE_ANALYSIS = 5;
    private static final int MODE_HIGHLIGHT = 8;
    private static final int MODE_HOME = 3;
    private static final int MODE_INTRO_1 = 0;
    private static final int MODE_INTRO_2 = 1;
    private static final int MODE_PAYWALL = 2;
    private static final int MODE_PLAY = 6;
    private static final int MODE_SETTINGS = 7;
    private static final int MODE_SONGLIST = 4;
    private static boolean m_IsCreated = false;
    private SubscriptionUtility.SubscriptionUtilityListener mBillingListener = new SubscriptionUtility.SubscriptionUtilityListener() {
        public void onBillingFailed() {
            ScannerActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    ScannerActivity.this.playStoreErrorAlert();
                }
            });
        }

        public void onUpdate() {
            ScannerActivity.this.log("onUpdate()");
            ScannerActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    if (ScannerActivity.this.mSubscriptionUtility.isBillingEnabled()) {
                        if (!ScannerActivity.this.mSubscriptionUtility.isUnlocked()) {
                            int access$500 = ScannerActivity.this.m_Mode;
                            if (access$500 == 0 || access$500 == 1) {
                                if (!ScannerActivity.this.mSubscriptionUtility.isTrialAvailable()) {
                                    ScannerActivity.this.openPaywall();
                                }
                            } else if (access$500 == 2) {
                                ScannerActivity.this.mPaywall.update();
                            }
                        } else if (ScannerActivity.this.m_Mode == 0 || ScannerActivity.this.m_Mode == 1 || ScannerActivity.this.m_Mode == 2) {
                            ScannerActivity.this.restoreAccess();
                        }
                    }
                }
            });
        }
    };
    private Intro1Layout mIntro1;
    private Intro1Layout.Intro1LayoutListener mIntro1Listener = new Intro1Layout.Intro1LayoutListener() {
        public void onContinue() {
            ScannerActivity.this.openIntro2();
        }
    };
    private Intro2Layout mIntro2;
    private Intro2Layout.Intro2LayoutListener mIntro2Listener = new Intro2Layout.Intro2LayoutListener() {
        public void onContinue() {
            ScannerActivity.this.openPaywall();
        }
    };
    /* access modifiers changed from: private */
    public PaywallLayout mPaywall;
    private PaywallLayout.PaywallLayoutListener mPaywallListener = new PaywallLayout.PaywallLayoutListener() {
        public void onRefresh() {
            if (ScannerActivity.this.mSubscriptionUtility.hasCapabilites()) {
                ScannerActivity.this.refreshSubscription();
            } else {
                ScannerActivity.this.mPaywall.update();
            }
        }

        public void onRestore() {
            if (ScannerActivity.this.mSubscriptionUtility.hasCapabilites()) {
                ScannerActivity.this.refreshSubscription();
            } else {
                ScannerActivity.this.mPaywall.update();
            }
        }

        /* JADX WARNING: type inference failed for: r1v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onSubscribe(String str) {
            ScannerActivity.this.mSubscriptionUtility.purchaseProduct((Activity) ScannerActivity.this, str);
        }
    };
    /* access modifiers changed from: private */
    public SubscriptionUtility mSubscriptionUtility;
    /* access modifiers changed from: private */
    public AlertBox m_AlertBox = null;
    /* access modifiers changed from: private */
    public AnalysisLayout m_Analysis = null;
    private AnalysisLayout.AnalysisLayoutListener m_AnalysisListener = new AnalysisLayout.AnalysisLayoutListener() {
        /* JADX WARNING: type inference failed for: r2v2, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onAbort(Intent intent, boolean z) {
            ScannerActivity.this.m_Analysis.destroy();
            if (intent != null) {
                ScannerActivity.this.openPlay(intent);
            } else {
                ScannerActivity.this.goBack((Intent) null);
            }
            if (z) {
                ActivityHelper.openHelp(ScannerActivity.this);
            }
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
        public void onMscaSuccess(List<String> list) {
            SessionUtility instance = SessionUtility.getInstance(ScannerActivity.this);
            ArrayList arrayList = new ArrayList();
            ScannerActivity.this.m_Analysis.destroy();
            for (String cdGetSession : list) {
                arrayList.add(instance.cdGetSession(cdGetSession).getDisplayName());
            }
            Utils.sortStringList(arrayList);
            Intent intent = new Intent();
            intent.putExtra(Constants.BUNDLE_SONG_FOLDER, (String) arrayList.get(0));
            ScannerActivity.this.openSongList(intent);
        }

        public void onMscaAbort() {
            ScannerActivity.this.m_Analysis.destroy();
            ScannerActivity.this.goBack((Intent) null);
        }

        public void onSuccess(Intent intent) {
            ScannerActivity.this.m_Analysis.destroy();
            ScannerActivity.this.openPlay(intent);
        }
    };
    /* access modifiers changed from: private */
    public int m_BackTo = 3;
    private ExportDialog m_ExportDlg;
    private String m_ExportFilePath = null;
    private int m_ExportType;
    /* access modifiers changed from: private */
    public FeedbackDialogs m_FbDialogs;
    private FeedbackDialogs.OnFeedbackListener m_FbListener = new FeedbackDialogs.OnFeedbackListener() {
        /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onEmail() {
            if (!ActivityHelper.emailReport(ScannerActivity.this)) {
                ScannerActivity.this.m_AlertBox.open(ScannerActivity.this.getString(R.string.feedbackEmailErrorAlert_title), ScannerActivity.this.getString(R.string.feedbackEmailErrorAlert_message), ScannerActivity.this.getString(R.string.feedbackEmailErrorAlert_button_ok), (String) null, (AlertBox.OnAlertListener) null);
            }
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onRate() {
            ActivityHelper.rateApp(ScannerActivity.this);
        }
    };
    private boolean m_FirstRun = true;
    private HighlightLayout m_Highlight = null;
    private HighlightLayout.HighlightLayoutListener m_HighlightListener = new HighlightLayout.HighlightLayoutListener() {
        public void onBack() {
            ScannerActivity.this.onBackPressed();
        }

        public void onDefault() {
            Global.getInstance().setHighlightMode(0);
        }

        public void onContrast() {
            Global.getInstance().setHighlightMode(1);
        }
    };
    private HomeLayout m_Home = null;
    private HomeLayout.HomeLayoutListener m_HomeListener = new HomeLayout.HomeLayoutListener() {
        /* JADX WARNING: type inference failed for: r0v6, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onCamera() {
            int unused = ScannerActivity.this.m_BackTo = 3;
            String unused2 = ScannerActivity.this.m_SongDir = null;
            int unused3 = ScannerActivity.this.m_InsertIdx = 0;
            boolean unused4 = ScannerActivity.this.m_NewSong = true;
            boolean unused5 = ScannerActivity.this.m_PlayEdit = false;
            ScannerActivity.this.clearSessionGlobals();
            ActivityHelper.captureImage(ScannerActivity.this);
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onPhotos() {
            int unused = ScannerActivity.this.m_BackTo = 3;
            String unused2 = ScannerActivity.this.m_SongDir = null;
            int unused3 = ScannerActivity.this.m_InsertIdx = 0;
            boolean unused4 = ScannerActivity.this.m_NewSong = true;
            boolean unused5 = ScannerActivity.this.m_PlayEdit = false;
            ScannerActivity.this.clearSessionGlobals();
            ActivityHelper.pickImage(ScannerActivity.this);
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onFiles() {
            int unused = ScannerActivity.this.m_BackTo = 3;
            String unused2 = ScannerActivity.this.m_SongDir = null;
            int unused3 = ScannerActivity.this.m_InsertIdx = 0;
            boolean unused4 = ScannerActivity.this.m_NewSong = true;
            boolean unused5 = ScannerActivity.this.m_PlayEdit = false;
            ScannerActivity.this.clearSessionGlobals();
            ActivityHelper.browse(ScannerActivity.this);
        }

        public void onSongList() {
            int unused = ScannerActivity.this.m_BackTo = 3;
            ScannerActivity.this.openSongList((Intent) null);
        }

        public void onSettings() {
            ScannerActivity.this.openSettings();
        }
    };
    private ImportDialog m_ImportDlg;
    /* access modifiers changed from: private */
    public int m_InsertIdx = 0;
    private boolean m_IsAborted = false;
    private MediaSessionCompat m_MediaSession;
    private MediaSessionCompat.Callback m_MediaSessionCallback = new MediaSessionCompat.Callback() {
        public void onPlay() {
            if (ScannerActivity.this.m_Mode == 6) {
                ScannerActivity.this.m_Play.togglePlay();
            } else {
                ScannerActivity.super.onPlay();
            }
        }

        public void onPause() {
            if (ScannerActivity.this.m_Mode == 6) {
                ScannerActivity.this.m_Play.togglePlay();
            } else {
                ScannerActivity.super.onPause();
            }
        }

        public void onStop() {
            if (ScannerActivity.this.m_Mode == 6) {
                ScannerActivity.this.m_Play.stop();
            } else {
                ScannerActivity.super.onStop();
            }
        }
    };
    private Handler m_MediaSessionHandler = new Handler();
    /* access modifiers changed from: private */
    public int m_Mode = -1;
    private Intent m_NewIntent = null;
    /* access modifiers changed from: private */
    public boolean m_NewSong = true;
    /* access modifiers changed from: private */
    public PlayLayout m_Play = null;
    /* access modifiers changed from: private */
    public boolean m_PlayEdit = false;
    private PlayLayout.PlayLayoutListener m_PlayListener = new PlayLayout.PlayLayoutListener() {
        public void onBack(Intent intent) {
            ScannerActivity.this.m_Play.destroy();
            ScannerActivity.this.goBack(intent);
        }

        /* JADX WARNING: type inference failed for: r2v5, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
        /* JADX WARNING: type inference failed for: r2v6, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r2v8, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r2v10, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onImport(String str, int i, boolean z, boolean z2, boolean z3) {
            String unused = ScannerActivity.this.m_SongDir = str;
            int unused2 = ScannerActivity.this.m_InsertIdx = i;
            boolean unused3 = ScannerActivity.this.m_NewSong = z;
            boolean unused4 = ScannerActivity.this.m_PlayEdit = z2;
            if (z3) {
                int i2 = ScannerActivity.this.m_Prefs.getImport();
                if (i2 == 1) {
                    ActivityHelper.captureImage(ScannerActivity.this);
                } else if (i2 == 2) {
                    ActivityHelper.pickImage(ScannerActivity.this);
                } else if (i2 == 3) {
                    ActivityHelper.browse(ScannerActivity.this);
                }
            } else {
                ? r2 = ScannerActivity.this;
                r2.openImportDialog(Utils.hasCamera(r2));
            }
        }

        public void onExport(CdSession cdSession, int i) {
            ScannerActivity.this.m_Play.pause();
            ScannerActivity.this.openExportDialog(cdSession, i);
        }

        public void onFeedback() {
            ScannerActivity.this.m_Play.pause();
            FlurryUtils.event("FeedbackDialog");
            ScannerActivity.this.m_FbDialogs.open(true);
        }
    };
    /* access modifiers changed from: private */
    public UserSettings m_Prefs;
    /* access modifiers changed from: private */
    public PrepareDialog m_PrepareDlg;
    private RenameDialog m_RenameDlg;
    private SaveFileDialog m_SaveFileDialog;
    private SettingsLayout m_Settings = null;
    private SettingsLayout.SettingsLayoutListener m_SettingsListener = new SettingsLayout.SettingsLayoutListener() {
        public void onBack() {
            ScannerActivity.this.onBackPressed();
        }

        public void onHighlight() {
            ScannerActivity.this.openHighlight();
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
        public void onSubscription() {
            BillingUtils.openGpSubscriptionPage(ScannerActivity.this, SubscriptionUtility.PRO_ACCESS_ID);
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onAbout() {
            ActivityHelper.openAbout(ScannerActivity.this);
        }
    };
    /* access modifiers changed from: private */
    public String m_SongDir = null;
    private SongListLayout m_SongList = null;
    private SongListLayout.SongListLayoutListener m_SongListener = new SongListLayout.SongListLayoutListener() {
        public void onBack() {
            ScannerActivity.this.onBackPressed();
        }

        public void onPlaySong(Intent intent) {
            int unused = ScannerActivity.this.m_BackTo = 4;
            ScannerActivity.this.openPlay(intent);
        }

        /* JADX WARNING: type inference failed for: r0v6, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
        public void onImport() {
            int unused = ScannerActivity.this.m_BackTo = 4;
            String unused2 = ScannerActivity.this.m_SongDir = null;
            int unused3 = ScannerActivity.this.m_InsertIdx = 0;
            boolean unused4 = ScannerActivity.this.m_NewSong = true;
            boolean unused5 = ScannerActivity.this.m_PlayEdit = false;
            ScannerActivity.this.clearSessionGlobals();
            ? r0 = ScannerActivity.this;
            r0.openImportDialog(Utils.hasCamera(r0));
        }

        public void onFeedback() {
            ScannerActivity.this.m_FbDialogs.open();
        }
    };
    private SplashDialog m_SplashDialog;
    private PlaybackStateCompat.Builder m_StateBuilder;

    /* access modifiers changed from: private */
    public void log(String str) {
    }

    static {
        System.loadLibrary("source-lib");
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        ScannerActivity.super.onCreate(bundle);
        setContentView(R.layout.activity_scanner);
        ActivityHelper.fullScreen(this);
        this.m_Prefs = UserSettings.getInstance(this);
        this.mSubscriptionUtility = ((ScannerApplication) getApplication()).mAppServices.subscriptionUtility;
        this.mIntro1 = (Intro1Layout) findViewById(R.id.layout_intro1);
        this.mIntro2 = (Intro2Layout) findViewById(R.id.layout_intro2);
        this.mPaywall = (PaywallLayout) findViewById(R.id.layout_paywall);
        this.m_Home = (HomeLayout) findViewById(R.id.home_layout);
        this.m_SongList = (SongListLayout) findViewById(R.id.song_list_layout);
        this.m_Analysis = (AnalysisLayout) findViewById(R.id.analysis_layout);
        this.m_Play = (PlayLayout) findViewById(R.id.play_layout);
        this.m_Settings = (SettingsLayout) findViewById(R.id.settings_layout);
        this.m_Highlight = (HighlightLayout) findViewById(R.id.highlight_layout);
        if (m_IsCreated) {
            this.m_IsAborted = true;
            log("Pass Intent to other ScannerActivity instance");
            Intent intent = getIntent();
            intent.addFlags(524288);
            startActivity(intent);
            finish();
            return;
        }
        m_IsCreated = true;
        this.mIntro1.setListener(this.mIntro1Listener);
        this.mIntro2.setListener(this.mIntro2Listener);
        this.mPaywall.setListener(this.mPaywallListener);
        this.m_Home.setActivity(this);
        this.m_Home.setListener(this.m_HomeListener);
        this.m_SongList.setActivity(this);
        this.m_SongList.setListener(this.m_SongListener);
        this.m_Analysis.setActivity(this);
        this.m_Analysis.setListener(this.m_AnalysisListener);
        this.m_Play.setActivity(this);
        this.m_Play.setListener(this.m_PlayListener);
        this.m_Settings.setListener(this.m_SettingsListener);
        this.m_Highlight.setListener(this.m_HighlightListener);
        this.m_AlertBox = new AlertBox(this);
        this.m_SplashDialog = new SplashDialog(this);
        this.m_ImportDlg = new ImportDialog(this);
        this.m_RenameDlg = new RenameDialog(this);
        this.m_ExportDlg = new ExportDialog(this);
        this.m_SaveFileDialog = new SaveFileDialog(this);
        this.m_PrepareDlg = new PrepareDialog(this);
        FeedbackDialogs feedbackDialogs = new FeedbackDialogs(this);
        this.m_FbDialogs = feedbackDialogs;
        feedbackDialogs.setListener(this.m_FbListener);
        initMediaSession();
        log("onCreate()");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        ScannerActivity.super.onStart();
        log("onStart()");
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        log("onStop()");
        ScannerActivity.super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        ScannerActivity.super.onResume();
        log("onResume()");
        if (!this.m_IsAborted) {
            Global.getInstance().setHighlightMode(this.m_Prefs.getHighlightMode());
            if (this.m_FirstRun) {
                log("first run");
                this.m_FirstRun = false;
                this.mSubscriptionUtility.setListener(this.mBillingListener);
                this.mSubscriptionUtility.connect();
                loadSynth(new OnDoneListener() {
                    /* JADX WARNING: type inference failed for: r2v1, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
                    /* JADX WARNING: type inference failed for: r3v4, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                    /* JADX WARNING: type inference failed for: r3v15, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                    public void onDone(int i, String str) {
                        ExportUtility.getInstance(ScannerActivity.this).deleteOldFiles();
                        Intent intent = ScannerActivity.this.getIntent();
                        if ((intent.getFlags() & 1048576) != 0) {
                            ScannerActivity.this.setIntent((Intent) null);
                            ScannerActivity.this.openHome();
                        } else if (ActivityHelper.isShortcutIntent(ScannerActivity.this, intent)) {
                            if (ScannerActivity.this.mSubscriptionUtility.isUnlocked()) {
                                ActivityHelper.handleShortcutIntent(ScannerActivity.this, intent);
                            } else {
                                ScannerActivity.this.managePaywall();
                            }
                        } else if (!Utils.isShareIntent(intent)) {
                            ScannerActivity.this.openHome();
                        } else if (ScannerActivity.this.mSubscriptionUtility.isUnlocked()) {
                            int unused = ScannerActivity.this.m_BackTo = 4;
                            ScannerActivity.this.openAnalysis(intent);
                        } else {
                            ScannerActivity.this.managePaywall();
                        }
                    }
                });
            } else {
                refreshSubscription();
            }
            int i = this.m_Mode;
            if (i == 2) {
                this.mPaywall.resume();
            } else if (i == 3) {
                this.m_Home.resume();
            } else if (i == 4) {
                this.m_SongList.resume(false);
            } else if (i == 6) {
                this.m_Play.resume();
            }
            FlurryUtils.valueEvent("Layout opened", "Name", getModeViewName(this.m_Mode));
            if (this.m_NewIntent != null) {
                openNewIntent();
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onPause() {
        ScannerActivity.super.onPause();
        log("onPause()");
        if (!this.mSubscriptionUtility.isBillingEnabled()) {
            ActivityHelper.killApp(this);
        }
        if (this.m_Mode == 6) {
            this.m_Play.pause();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        log("onDestroy");
        m_IsCreated = false;
        if (!this.m_IsAborted) {
            this.m_AlertBox.close();
            this.m_SplashDialog.close();
            this.m_ImportDlg.close();
            this.m_RenameDlg.close();
            this.m_ExportDlg.close();
            this.m_SaveFileDialog.close();
            this.m_PrepareDlg.close();
            this.m_FbDialogs.close();
            this.mPaywall.destroy();
            this.m_Home.destroy();
            this.m_SongList.destroy();
            this.m_Analysis.destroy();
            this.m_Play.destroy();
            this.m_MediaSession.release();
        }
        ScannerActivity.super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        ScannerActivity.super.onNewIntent(intent);
        log("onNewIntent");
        this.m_NewIntent = intent;
        this.m_BackTo = 4;
    }

    public void onBackPressed() {
        log("onBackPressed");
        log("m_Mode = " + this.m_Mode);
        int i = this.m_Mode;
        if (i == 3 || i == 0 || i == 1 || i == 2) {
            ScannerActivity.super.onBackPressed();
        } else if (i == 4) {
            openHome();
        } else if (i == 6) {
            this.m_Play.goBack(true);
        } else if (i == 8) {
            openSettings();
        } else if (i != 5) {
            goBack((Intent) null);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        ScannerActivity.super.onActivityResult(i, i2, intent);
        log("onActivityResult()");
        if (i2 != -1) {
            return;
        }
        if (i == 256) {
            if (this.m_Mode == 6) {
                this.m_Play.destroy();
            }
            openAnalysis(ActivityHelper.createAnalysisIntent(ActivityHelper.getCameraPath(this), this.m_SongDir, this.m_InsertIdx, this.m_NewSong, this.m_PlayEdit));
        } else if (i == 512) {
            if (this.m_Mode == 6) {
                this.m_Play.destroy();
            }
            openAnalysis(ActivityHelper.createAnalysisIntent(intent.getData(), this.m_SongDir, this.m_InsertIdx, this.m_NewSong, this.m_PlayEdit));
        } else if (i == 768) {
            saveToDir(intent.getData());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        ScannerActivity.super.onConfigurationChanged(configuration);
        this.m_ExportDlg.rotate(configuration.orientation == 2);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    private void initMediaSession() {
        this.m_MediaSession = new MediaSessionCompat(this, "MusicScannerSession");
        this.m_StateBuilder = new PlaybackStateCompat.Builder().setActions(519).setState(2, 0, 1.0f);
        this.m_MediaSession.setFlags(1);
        this.m_MediaSession.setPlaybackState(this.m_StateBuilder.build());
        this.m_MediaSession.setCallback(this.m_MediaSessionCallback, this.m_MediaSessionHandler);
        this.m_MediaSession.setActive(true);
    }

    private void setMediaSessionState(boolean z) {
        this.m_StateBuilder.setState(z ? 3 : 2, -1, 1.0f);
        this.m_MediaSession.setPlaybackState(this.m_StateBuilder.build());
    }

    /* access modifiers changed from: private */
    public void goBack(Intent intent) {
        if (this.m_BackTo != 3) {
            openSongList(intent);
        } else {
            openHome();
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    private void openNewIntent() {
        log("openNewIntent()");
        this.m_BackTo = 4;
        if (ActivityHelper.isShortcutIntent(this, this.m_NewIntent)) {
            if (this.m_Mode == 6) {
                this.m_Play.destroy();
            }
            if (this.mSubscriptionUtility.isUnlocked()) {
                ActivityHelper.handleShortcutIntent(this, this.m_NewIntent);
            } else {
                managePaywall();
            }
        } else if (Utils.isShareIntent(this.m_NewIntent)) {
            log("is share intent");
            if (this.m_Mode == 6) {
                this.m_Play.destroy();
            }
            if (this.mSubscriptionUtility.isUnlocked()) {
                openAnalysis(this.m_NewIntent);
            } else {
                managePaywall();
            }
        }
        this.m_NewIntent = null;
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    private void loadSynth(final OnDoneListener onDoneListener) {
        if (!Synth.getInstance(this).isLoaded()) {
            this.m_SplashDialog.open(new OnDoneListener() {
                public void onDone(int i, String str) {
                    OnDoneListener onDoneListener = onDoneListener;
                    if (onDoneListener != null) {
                        onDoneListener.onDone(0, (String) null);
                    }
                }
            });
        } else if (onDoneListener != null) {
            onDoneListener.onDone(0, (String) null);
        }
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    private void replaceLayout(int i) {
        if (this.m_Mode != i) {
            Utils.hideSoftKeyboard(this, this.m_Home, 300);
            View modeView = getModeView(this.m_Mode);
            View modeView2 = getModeView(i);
            FlurryUtils.valueEvent("Layout opened", "Name", getModeViewName(this.m_Mode));
            int i2 = this.m_Mode;
            if (i > i2) {
                if ((i2 == 5 && i == 6) || (i2 == 3 && i == 5)) {
                    modeView2.setVisibility(0);
                    modeView.setVisibility(4);
                } else {
                    Utils.slideLeft(modeView, modeView2);
                }
            } else if (i2 == 6 && i == 5) {
                modeView2.setVisibility(0);
                modeView.setVisibility(4);
            } else {
                Utils.slideRight(modeView, modeView2);
            }
            this.m_Mode = i;
        }
    }

    private View getModeView(int i) {
        switch (i) {
            case 0:
                return this.mIntro1;
            case 1:
                return this.mIntro2;
            case 2:
                return this.mPaywall;
            case 3:
                return this.m_Home;
            case 4:
                return this.m_SongList;
            case 5:
                return this.m_Analysis;
            case 6:
                return this.m_Play;
            case 7:
                return this.m_Settings;
            case 8:
                return this.m_Highlight;
            default:
                return null;
        }
    }

    private String getModeViewName(int i) {
        switch (i) {
            case 0:
                return "Intro_1";
            case 1:
                return "Intro_2";
            case 2:
                return "Paywall";
            case 3:
                return "Home";
            case 4:
                return "SongList";
            case 5:
                return "Analysis";
            case 6:
                return "Play";
            case 7:
                return "Settings";
            case 8:
                return "Highlight";
            default:
                return "None";
        }
    }

    private void openIntro1() {
        replaceLayout(0);
    }

    /* access modifiers changed from: private */
    public void openIntro2() {
        replaceLayout(1);
    }

    /* access modifiers changed from: private */
    public void openPaywall() {
        replaceLayout(2);
        this.mPaywall.resume();
    }

    /* access modifiers changed from: private */
    public void openHome() {
        log("openHome()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            replaceLayout(3);
            this.m_Home.resume();
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openSongList(Intent intent) {
        log("openSongList()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            replaceLayout(4);
            getWindow().setSoftInputMode(2);
            if (intent != null) {
                this.m_SongList.setIntent(intent);
            } else {
                this.m_SongList.resume(true);
            }
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openAnalysis(Intent intent) {
        log("openAnalysis()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            if (this.m_Mode == 5) {
                this.m_BackTo = 4;
            }
            replaceLayout(5);
            if (intent != null) {
                this.m_Analysis.setIntent(intent);
            }
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openPlay(Intent intent) {
        log("openPlay()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            replaceLayout(6);
            setMediaSessionState(false);
            if (intent != null) {
                this.m_Play.setIntent(intent);
            }
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openSettings() {
        log("openSettings()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            replaceLayout(7);
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openHighlight() {
        log("openHighlight()");
        if (this.mSubscriptionUtility.isUnlocked()) {
            replaceLayout(8);
            refreshSubscription();
            return;
        }
        managePaywall();
    }

    /* access modifiers changed from: private */
    public void openImportDialog(boolean z) {
        this.m_ImportDlg.open(z);
        this.m_ImportDlg.setListener(new ImportDialog.ImportDialogListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
            public void onCamera() {
                ActivityHelper.captureImage(ScannerActivity.this);
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
            public void onPhotos() {
                ActivityHelper.pickImage(ScannerActivity.this);
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
            public void onBrowse() {
                ActivityHelper.browse(ScannerActivity.this);
            }
        });
    }

    /* access modifiers changed from: private */
    public void openExportDialog(final CdSession cdSession, final int i) {
        this.m_ExportDlg.close();
        this.m_ExportDlg.open(new ExportDialog.ExportDialogListener() {
            public void onExport(int i) {
                ScannerActivity.this.openExportRenameDialog(cdSession, i, i);
            }

            public void onCancel() {
                if (ScannerActivity.this.m_Mode == 6) {
                    ScannerActivity.this.m_Play.resume();
                }
            }
        });
        this.m_ExportDlg.rotate(getResources().getConfiguration().orientation == 2);
    }

    /* access modifiers changed from: private */
    public void openExportRenameDialog(final CdSession cdSession, final int i, final int i2) {
        this.m_RenameDlg.close();
        this.m_RenameDlg.open(getString(R.string.exportFileNameAlert_title), getString(R.string.exportFileNameAlert_message), cdSession.getDisplayName(), getString(R.string.exportFileNameAlert_button_ok), getString(R.string.exportFileNameAlert_button_cancel), new RenameDialog.OnRenameListener() {
            public void onOk(String str) {
                ScannerActivity.this.exportSession(cdSession, i, str, i2);
            }

            public void onCancel() {
                if (ScannerActivity.this.m_Mode == 6) {
                    ScannerActivity.this.m_Play.resume();
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r7v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    /* access modifiers changed from: private */
    public void exportSession(CdSession cdSession, int i, String str, int i2) {
        this.m_PrepareDlg.open();
        ExportUtility.getInstance(this).exportFileAsync(cdSession, i, str, i2, new ExportUtility.ExportListener() {
            public void onDone(String str, int i) {
                ScannerActivity.this.m_PrepareDlg.close();
                if (str != null) {
                    ScannerActivity.this.openSaveFileDialog(str, i);
                } else {
                    ScannerActivity.this.openExportErrorDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void openSaveFileDialog(final String str, final int i) {
        this.m_SaveFileDialog.close();
        this.m_SaveFileDialog.open(new SaveFileDialog.SaveFileDialogListener() {
            public void onShare() {
                ScannerActivity.this.share(str, i);
            }

            public void onBrowse() {
                ScannerActivity.this.chooseDir(str, i);
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
            public void onCancel() {
                ExportUtility.getInstance(ScannerActivity.this).deleteFile(str);
                if (ScannerActivity.this.m_Mode == 6) {
                    ScannerActivity.this.m_Play.resume();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void openExportErrorDialog() {
        this.m_AlertBox.close();
        this.m_AlertBox.open(getString(R.string.exportErrorAlert_title), getString(R.string.exportErrorAlert_message), getString(R.string.exportErrorAlert_button_ok), (String) null, new AlertBox.OnAlertListener() {
            public void onOk() {
                if (ScannerActivity.this.m_Mode == 6) {
                    ScannerActivity.this.m_Play.resume();
                }
            }

            public void onCancel() {
                if (ScannerActivity.this.m_Mode == 6) {
                    ScannerActivity.this.m_Play.resume();
                }
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void share(String str, int i) {
        ActivityHelper.shareFile(this, str, ExportTypes.getMimeType(i));
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    /* access modifiers changed from: private */
    public void chooseDir(String str, int i) {
        this.m_ExportFilePath = str;
        this.m_ExportType = i;
        ActivityHelper.chooseDir(this);
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    private void saveToDir(Uri uri) {
        if (this.m_ExportFilePath != null) {
            if (Utils.copyFileToDirUri(this, uri, ExportTypes.getMimeType(this.m_ExportType), this.m_ExportFilePath, new File(this.m_ExportFilePath).getName())) {
                if (this.m_Mode == 6) {
                    this.m_Play.resume();
                    return;
                }
                return;
            }
        }
        openExportErrorDialog();
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    /* access modifiers changed from: private */
    public void clearSessionGlobals() {
        Global instance = Global.getInstance();
        instance.setSession((session) null, this);
        instance.setInsertAtIndex(0);
    }

    /* access modifiers changed from: private */
    public void refreshSubscription() {
        this.mSubscriptionUtility.refreshProduct();
        this.mSubscriptionUtility.refreshPurchases();
    }

    /* access modifiers changed from: private */
    public void managePaywall() {
        int i = this.m_Mode;
        if (i == 5) {
            this.m_Analysis.destroy();
        } else if (i == 6) {
            this.m_Play.pause();
            this.m_Play.destroy();
        }
        if (!this.mSubscriptionUtility.isBillingEnabled()) {
            openIntro1();
            return;
        }
        if (this.mSubscriptionUtility.isTrialAvailable()) {
            openIntro1();
        } else {
            openPaywall();
        }
        refreshSubscription();
    }

    /* access modifiers changed from: private */
    public void restoreAccess() {
        openHome();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
    /* access modifiers changed from: private */
    public void playStoreErrorAlert() {
        final Intent playStoreIntent = BillingUtils.getPlayStoreIntent(this);
        this.mSubscriptionUtility.disconnect();
        if (playStoreIntent != null) {
            this.m_AlertBox.open(getString(R.string.subscription_playStoreOutdatedAlert_title), getString(R.string.subscription_playStoreOutdatedAlert_message), getString(R.string.subscription_playStoreOutdatedAlert_button_open_app), getString(R.string.subscription_playStoreOutdatedAlert_button_cancel), true, false, new AlertBox.OnAlertListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.ScannerActivity] */
                /* JADX WARNING: type inference failed for: r0v1, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                public void onOk() {
                    BillingUtils.launchActivityIntent(ScannerActivity.this, playStoreIntent);
                    ActivityHelper.killApp(ScannerActivity.this);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                public void onCancel() {
                    ActivityHelper.killApp(ScannerActivity.this);
                }
            });
        } else {
            this.m_AlertBox.open(getString(R.string.subscription_playStoreDisabledAlert_title), getString(R.string.subscription_playStoreDisabledAlert_message), getString(R.string.subscription_playStoreDisabledAlert_button_ok), (String) null, true, false, new AlertBox.OnAlertListener() {
                /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                public void onOk() {
                    ActivityHelper.killApp(ScannerActivity.this);
                }

                /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
                public void onCancel() {
                    ActivityHelper.killApp(ScannerActivity.this);
                }
            });
        }
    }
}
