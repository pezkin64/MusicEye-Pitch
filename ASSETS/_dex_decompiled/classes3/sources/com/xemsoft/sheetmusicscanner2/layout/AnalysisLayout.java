package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.activity.ScannerActivity;
import com.xemsoft.sheetmusicscanner2.analysis.ImageLoader;
import com.xemsoft.sheetmusicscanner2.analysis.Scanner;
import com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder;
import com.xemsoft.sheetmusicscanner2.analysis.UriInfo;
import com.xemsoft.sheetmusicscanner2.analysis.UriManager;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.RotateDialog;
import com.xemsoft.sheetmusicscanner2.dialog.SplashDialog;
import com.xemsoft.sheetmusicscanner2.export.ExportImportMsca;
import com.xemsoft.sheetmusicscanner2.export.ExportTypes;
import com.xemsoft.sheetmusicscanner2.layout.LoadLayout;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.sources.analysisResult;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.FlurryUtils;
import com.xemsoft.sheetmusicscanner2.util.IntWrap;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.util.ArrayList;
import java.util.List;

public class AnalysisLayout extends RelativeLayout {
    private static final int CAUSE_CONNECTION_FAILURE = 1;
    private static final int CAUSE_UNKNOWN = 0;
    private static final String LOGTAG = "AnalysisLayout";
    /* access modifiers changed from: private */
    public ScannerActivity m_Activity = null;
    private AlertBox m_AlertBox;
    private SessionBuilder m_Builder = null;
    /* access modifiers changed from: private */
    public Context m_Context = null;
    /* access modifiers changed from: private */
    public int m_Error = 0;
    private int m_ErrorCause = 0;
    private int m_InsertIdx = 0;
    private boolean m_IsAnalyzing = false;
    /* access modifiers changed from: private */
    public int m_LastRotationDegrees = 0;
    private RelativeLayout m_LayoutRoot;
    private AnalysisLayoutListener m_Listener = null;
    /* access modifiers changed from: private */
    public LoadLayout m_LoadLayout;
    /* access modifiers changed from: private */
    public ImageLoader m_Loader = null;
    private ExportImportMsca m_Msca = null;
    private List<String> m_MscaSessionFolders;
    private boolean m_NewSong = false;
    /* access modifiers changed from: private */
    public List<Intent> m_PendingIntents;
    private boolean m_PlayEdit = false;
    private UserSettings m_Prefs;
    /* access modifiers changed from: private */
    public RotateDialog m_RotateDialog;
    /* access modifiers changed from: private */
    public Scanner m_Scanner = null;
    private String m_SongDir = null;
    private SplashDialog m_SplashDialog;

    public interface AnalysisLayoutListener {
        void onAbort(Intent intent, boolean z);

        void onMscaAbort();

        void onMscaSuccess(List<String> list);

        void onSuccess(Intent intent);
    }

    public AnalysisLayout(Context context) {
        super(context);
        init(context);
    }

    public AnalysisLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public AnalysisLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        this.m_Context = context;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_analysis, this, true);
        this.m_Prefs = UserSettings.getInstance(this.m_Context);
        this.m_PendingIntents = new ArrayList();
        this.m_LayoutRoot = (RelativeLayout) inflate.findViewById(R.id.layout_root);
        this.m_LoadLayout = (LoadLayout) inflate.findViewById(R.id.load_layout);
        this.m_SplashDialog = new SplashDialog(this.m_Context);
        this.m_AlertBox = new AlertBox(this.m_Context);
        this.m_RotateDialog = new RotateDialog(this.m_Context);
        log("onCreate");
    }

    public void setActivity(ScannerActivity scannerActivity) {
        this.m_Activity = scannerActivity;
    }

    public void setListener(AnalysisLayoutListener analysisLayoutListener) {
        this.m_Listener = analysisLayoutListener;
    }

    public void destroy() {
        log("onDestroy");
        closeLoader();
        closeBuilder();
        this.m_SplashDialog.close();
        this.m_AlertBox.close();
        this.m_RotateDialog.close();
        this.m_LoadLayout.close();
    }

    public void setIntent(Intent intent) {
        log("setIntent");
        if (!this.m_IsAnalyzing) {
            log("Analyzing == false");
            openIntent(intent);
            return;
        }
        log("Analyzing == true");
        this.m_PendingIntents.add(intent);
    }

    private void openIntent(Intent intent) {
        log("openIntent 1");
        Uri shareUri = getShareUri(intent);
        this.m_LastRotationDegrees = 0;
        openIntent(intent, shareUri);
    }

    /* JADX WARNING: type inference failed for: r8v3, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    private void openIntent(Intent intent, Uri uri) {
        String stringExtra;
        log("openIntent 2");
        log(String.format("Intent flags: %x", new Object[]{Integer.valueOf(intent.getFlags())}));
        this.m_NewSong = intent.getBooleanExtra(Constants.BUNDLE_NEW_SONG, true);
        this.m_PlayEdit = intent.getBooleanExtra(Constants.BUNDLE_PLAY_EDIT, false);
        this.m_Error = 0;
        this.m_ErrorCause = 0;
        UriInfo uriInfo = null;
        this.m_Builder = null;
        if (uri == null && (stringExtra = intent.getStringExtra(Constants.BUNDLE_URI)) != null) {
            uri = Uri.parse(stringExtra);
        }
        if (uri == null || (uriInfo = UriManager.getUriInfo(this.m_Context, uri)) == null || !Utils.getExtension(uriInfo.m_DisplayName).equalsIgnoreCase(ExportTypes.getExtension(7))) {
            this.m_IsAnalyzing = true;
            this.m_Loader = new ImageLoader(this.m_Context);
            String stringExtra2 = intent.getStringExtra(Constants.BUNDLE_CAMERA_PATH);
            if (stringExtra2 != null) {
                if (!this.m_Loader.open(stringExtra2)) {
                    this.m_Error = 1;
                }
            } else if (uri == null) {
                this.m_Error = 1;
                endAnalysis();
                return;
            } else if (!this.m_Loader.open(uri, uriInfo)) {
                this.m_Error = 1;
            }
            if (!this.m_LoadLayout.isOpen()) {
                this.m_LoadLayout.open(getScanTitle());
            } else {
                this.m_LoadLayout.setText(getScanTitle());
            }
            log("Open " + this.m_Loader.getName());
            if (this.m_Error != 0) {
                endAnalysis();
                return;
            }
            this.m_Scanner = new Scanner(this.m_Context);
            this.m_LoadLayout.setMaxProgress(this.m_Loader.getImageCount() * 10);
            if (this.m_Loader.isPdf()) {
                this.m_LoadLayout.setCancelListener(new LoadLayout.CancelListener() {
                    public void onCancel() {
                        int unused = AnalysisLayout.this.m_Error = 4;
                        AnalysisLayout.this.m_Scanner.cancelScan();
                        AnalysisLayout.this.m_LoadLayout.setText(AnalysisLayout.this.m_Context.getString(R.string.progressBarLabel_canceling));
                    }
                });
            }
            this.m_SongDir = intent.getStringExtra(Constants.BUNDLE_SONG_FOLDER);
            this.m_InsertIdx = intent.getIntExtra(Constants.BUNDLE_INSERT_IDX, 0);
            if (this.m_SongDir != null) {
                this.m_Builder = new SessionBuilder(this.m_Context, this.m_SongDir, this.m_InsertIdx);
            } else {
                this.m_Builder = new SessionBuilder(this.m_Context, this.m_Loader.getName());
            }
            ActivityHelper.keepScreenOn(this.m_Activity, true);
            scanPage();
        } else if (this.m_PlayEdit) {
            this.m_Error = 11;
            this.m_SongDir = intent.getStringExtra(Constants.BUNDLE_SONG_FOLDER);
            this.m_InsertIdx = intent.getIntExtra(Constants.BUNDLE_INSERT_IDX, 0);
            endMscaImport();
        } else if (SessionUtility.getInstance(this.m_Context).getFreeSpace() < 10000000) {
            this.m_Error = 6;
            endMscaImport();
        } else {
            importMsca(uri);
        }
    }

    private void importMsca(Uri uri) {
        this.m_MscaSessionFolders = new ArrayList();
        ExportImportMsca exportImportMsca = new ExportImportMsca(this.m_Context);
        this.m_Msca = exportImportMsca;
        this.m_Error = exportImportMsca.importFromUri(uri, this.m_MscaSessionFolders);
        endMscaImport();
    }

    private Uri getShareUri(Intent intent) {
        Bundle extras;
        Uri data = intent.getData();
        if (data == null && (extras = intent.getExtras()) != null) {
            data = (Uri) extras.get("android.intent.extra.STREAM");
        }
        if (data != null) {
            try {
                this.m_Activity.getContentResolver().takePersistableUriPermission(data, intent.getFlags() & 1);
                return data;
            } catch (Exception unused) {
                Log.w(LOGTAG, "- no permissions granted");
            }
        }
        return data;
    }

    private void scanPage() {
        int i = 1;
        if (!this.m_Loader.isOpen()) {
            this.m_Error = 1;
            endAnalysis();
        } else if (this.m_Loader.hasNext()) {
            IntWrap intWrap = new IntWrap(0);
            Bitmap loadNext = this.m_Loader.loadNext(intWrap);
            if (loadNext != null) {
                scanBitmap(loadNext);
                return;
            }
            this.m_Error = 1;
            if (intWrap.val != -1) {
                i = 0;
            }
            this.m_ErrorCause = i;
            endAnalysis();
        } else {
            endAnalysis();
        }
    }

    /* access modifiers changed from: private */
    public void scanBitmap(final Bitmap bitmap) {
        SessionUtility.getInstance(this.m_Context).saveTempImage(bitmap);
        FlurryUtils.startAnalysisEvent();
        this.m_Scanner.scan(bitmap, new Scanner.ScanListener() {
            public void onProgress(final int i) {
                AnalysisLayout.this.m_Activity.runOnUiThread(new Runnable() {
                    public void run() {
                        AnalysisLayout.this.m_LoadLayout.setProgress(((AnalysisLayout.this.m_Loader.getLoadedCount() - 1) * 10) + i);
                    }
                });
            }

            public void onDone(analysisResult analysisresult) {
                FlurryUtils.endAnalysisEvent(bitmap, analysisresult, AnalysisLayout.this.m_Loader.getLoadedCount());
                Bitmap bitmap = bitmap;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                AnalysisLayout.this.savePage(analysisresult);
            }
        });
    }

    /* access modifiers changed from: private */
    public void savePage(analysisResult analysisresult) {
        int addPage;
        if (this.m_Error == 4) {
            endAnalysis();
        } else if (validateAr(analysisresult) && (addPage = this.m_Builder.addPage(analysisresult)) != 0) {
            this.m_Error = addPage == 3 ? 6 : 5;
            endAnalysis();
        } else if (this.m_Loader.hasNext()) {
            scanPage();
        } else {
            endAnalysis();
        }
    }

    private boolean validateAr(analysisResult analysisresult) {
        int resultCode;
        if (analysisresult == null || (resultCode = analysisresult.getResultCode()) == Scanner.PREPROCESSOR_PIX_EMPTY) {
            return false;
        }
        if (resultCode == Scanner.PREPROCESSOR_ROTATED) {
            if (!this.m_Loader.isPdf()) {
                this.m_Error = 7;
            }
            return false;
        }
        this.m_Error = 0;
        if (!(analysisresult.getOverlayMask() == null || analysisresult.getBackground() == null || analysisresult.getScore() == null)) {
            if (resultCode == 0) {
                return true;
            }
            if (resultCode == Scanner.PREPROCESSOR_RESOLUTION_LOW) {
                if (!this.m_Loader.isPdf()) {
                    this.m_Error = 2;
                }
                return true;
            } else if (resultCode == Scanner.PREPROCESSOR_DISTORTED) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
    private void endAnalysis() {
        log("endAnalysis 1");
        this.m_LoadLayout.setProgress(this.m_Loader.getImageCount() * 10);
        ActivityHelper.keepScreenOn(this.m_Activity, false);
        this.m_LayoutRoot.postDelayed(new Runnable() {
            public void run() {
                if (AnalysisLayout.this.m_Error == 7 && AnalysisLayout.this.m_PendingIntents.size() == 0) {
                    Bitmap loadImage = AnalysisLayout.this.m_Loader.loadImage(0);
                    if (loadImage != null) {
                        AnalysisLayout.this.m_RotateDialog.open(loadImage, AnalysisLayout.this.m_LastRotationDegrees, new RotateDialog.RotateListener() {
                            public void onRescan(Bitmap bitmap, int i) {
                                int unused = AnalysisLayout.this.m_LastRotationDegrees = i;
                                AnalysisLayout.this.m_LoadLayout.setProgress(0);
                                AnalysisLayout.this.scanBitmap(bitmap);
                            }

                            public void onCancel() {
                                AnalysisLayout.this.endAnalysis2();
                            }
                        });
                        return;
                    }
                    int unused = AnalysisLayout.this.m_Error = 1;
                    AnalysisLayout.this.endAnalysis2();
                    return;
                }
                AnalysisLayout.this.endAnalysis2();
            }
        }, 300);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0076, code lost:
        r7 = r0;
        r9 = r2;
        r8 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0079, code lost:
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00e9, code lost:
        r7 = null;
        r8 = null;
        r9 = null;
        r10 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00ed, code lost:
        r0 = r12.m_Error;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ef, code lost:
        if (r0 == 0) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00f1, code lost:
        if (r0 == 2) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00f3, code lost:
        if (r0 != 8) goto L_0x00f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00f6, code lost:
        r0 = r12.m_Builder;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00f8, code lost:
        if (r0 == null) goto L_0x0103;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00fa, code lost:
        r0.deleteSession();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00fe, code lost:
        r12.m_Builder.destroySession();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0103, code lost:
        r12.m_IsAnalyzing = false;
        closeLoader();
        closeBuilder();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0112, code lost:
        if (r12.m_PendingIntents.size() <= 0) goto L_0x0120;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0114, code lost:
        openIntent(r12.m_PendingIntents.remove(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x011f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0120, code lost:
        if (r7 == null) goto L_0x012d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0122, code lost:
        r12.m_AlertBox.open(r7, r8, r9, r10, new com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout.AnonymousClass4(r12));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x012c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x012d, code lost:
        r12.m_LayoutRoot.postDelayed(new com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout.AnonymousClass5(r12), 300);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0139, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void endAnalysis2() {
        /*
            r12 = this;
            java.lang.String r0 = "endAnalysis 2"
            r12.log(r0)
            int r0 = r12.m_Error
            r1 = 8
            r2 = 3
            r3 = 2
            if (r0 == 0) goto L_0x0011
            if (r0 == r3) goto L_0x0011
            if (r0 != r1) goto L_0x001b
        L_0x0011:
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r0 = r12.m_Builder
            int r0 = r0.getScoreCount()
            if (r0 != 0) goto L_0x001b
            r12.m_Error = r2
        L_0x001b:
            com.xemsoft.sheetmusicscanner2.analysis.ImageLoader r0 = r12.m_Loader
            int r0 = r0.getLastModeUsed()
            if (r0 != r2) goto L_0x003d
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r0 = r12.m_Builder
            if (r0 == 0) goto L_0x003d
            int r0 = r0.getScoreCount()
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r2 = r12.m_Builder
            int r2 = r2.getInitialScoreCount()
            int r0 = r0 - r2
            java.lang.String r0 = java.lang.Integer.toString(r0)
            java.lang.String r2 = "PdfImport"
            java.lang.String r4 = "PagesImported"
            com.xemsoft.sheetmusicscanner2.util.FlurryUtils.valueEvent(r2, r4, r0)
        L_0x003d:
            int r0 = r12.m_Error
            r2 = 2131755362(0x7f100162, float:1.9141601E38)
            r4 = 2131755365(0x7f100165, float:1.9141607E38)
            r5 = 0
            switch(r0) {
                case 0: goto L_0x00db;
                case 1: goto L_0x00b4;
                case 2: goto L_0x00db;
                case 3: goto L_0x008b;
                case 4: goto L_0x007c;
                case 5: goto L_0x0061;
                case 6: goto L_0x004b;
                case 7: goto L_0x0049;
                case 8: goto L_0x00db;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x00e9
        L_0x004b:
            android.content.Context r0 = r12.m_Context
            java.lang.String r0 = r0.getString(r4)
            android.content.Context r4 = r12.m_Context
            r6 = 2131755364(0x7f100164, float:1.9141605E38)
            java.lang.String r4 = r4.getString(r6)
            android.content.Context r6 = r12.m_Context
            java.lang.String r2 = r6.getString(r2)
            goto L_0x0076
        L_0x0061:
            android.content.Context r0 = r12.m_Context
            java.lang.String r0 = r0.getString(r4)
            android.content.Context r4 = r12.m_Context
            r6 = 2131755363(0x7f100163, float:1.9141603E38)
            java.lang.String r4 = r4.getString(r6)
            android.content.Context r6 = r12.m_Context
            java.lang.String r2 = r6.getString(r2)
        L_0x0076:
            r7 = r0
            r9 = r2
            r8 = r4
        L_0x0079:
            r10 = r5
            goto L_0x00ed
        L_0x007c:
            com.xemsoft.sheetmusicscanner2.layout.LoadLayout r0 = r12.m_LoadLayout
            android.content.Context r2 = r12.m_Context
            r4 = 2131755343(0x7f10014f, float:1.9141563E38)
            java.lang.String r2 = r2.getString(r4)
            r0.setText(r2)
            goto L_0x00e9
        L_0x008b:
            android.content.Context r0 = r12.m_Context
            r2 = 2131755330(0x7f100142, float:1.9141536E38)
            java.lang.String r5 = r0.getString(r2)
            android.content.Context r0 = r12.m_Context
            r2 = 2131755329(0x7f100141, float:1.9141534E38)
            java.lang.String r0 = r0.getString(r2)
            android.content.Context r2 = r12.m_Context
            r4 = 2131755328(0x7f100140, float:1.9141532E38)
            java.lang.String r2 = r2.getString(r4)
            android.content.Context r4 = r12.m_Context
            r6 = 2131755327(0x7f10013f, float:1.914153E38)
            java.lang.String r4 = r4.getString(r6)
            r8 = r0
            r9 = r2
            r10 = r4
            r7 = r5
            goto L_0x00ed
        L_0x00b4:
            android.content.Context r0 = r12.m_Context
            r2 = 2131755193(0x7f1000b9, float:1.9141258E38)
            java.lang.String r0 = r0.getString(r2)
            android.content.Context r2 = r12.m_Context
            int r4 = r12.m_ErrorCause
            if (r4 != 0) goto L_0x00c7
            r4 = 2131755190(0x7f1000b6, float:1.9141252E38)
            goto L_0x00ca
        L_0x00c7:
            r4 = 2131755110(0x7f100066, float:1.914109E38)
        L_0x00ca:
            java.lang.String r2 = r2.getString(r4)
            android.content.Context r4 = r12.m_Context
            r6 = 2131755189(0x7f1000b5, float:1.914125E38)
            java.lang.String r4 = r4.getString(r6)
            r7 = r0
            r8 = r2
            r9 = r4
            goto L_0x0079
        L_0x00db:
            com.xemsoft.sheetmusicscanner2.layout.LoadLayout r0 = r12.m_LoadLayout
            android.content.Context r2 = r12.m_Context
            r4 = 2131755342(0x7f10014e, float:1.914156E38)
            java.lang.String r2 = r2.getString(r4)
            r0.setText(r2)
        L_0x00e9:
            r7 = r5
            r8 = r7
            r9 = r8
            r10 = r9
        L_0x00ed:
            int r0 = r12.m_Error
            if (r0 == 0) goto L_0x00fe
            if (r0 == r3) goto L_0x00fe
            if (r0 != r1) goto L_0x00f6
            goto L_0x00fe
        L_0x00f6:
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r0 = r12.m_Builder
            if (r0 == 0) goto L_0x0103
            r0.deleteSession()
            goto L_0x0103
        L_0x00fe:
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r0 = r12.m_Builder
            r0.destroySession()
        L_0x0103:
            r0 = 0
            r12.m_IsAnalyzing = r0
            r12.closeLoader()
            r12.closeBuilder()
            java.util.List<android.content.Intent> r1 = r12.m_PendingIntents
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x0120
            java.util.List<android.content.Intent> r1 = r12.m_PendingIntents
            java.lang.Object r0 = r1.remove(r0)
            android.content.Intent r0 = (android.content.Intent) r0
            r12.openIntent(r0)
            return
        L_0x0120:
            if (r7 == 0) goto L_0x012d
            com.xemsoft.sheetmusicscanner2.dialog.AlertBox r6 = r12.m_AlertBox
            com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$4 r11 = new com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$4
            r11.<init>()
            r6.open(r7, r8, r9, r10, r11)
            return
        L_0x012d:
            android.widget.RelativeLayout r0 = r12.m_LayoutRoot
            com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$5 r1 = new com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$5
            r1.<init>()
            r2 = 300(0x12c, double:1.48E-321)
            r0.postDelayed(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout.endAnalysis2():void");
    }

    /* access modifiers changed from: private */
    public void endAnalysis3() {
        endAnalysis3(false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v3, types: [android.content.Intent] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void endAnalysis3(boolean r6) {
        /*
            r5 = this;
            java.lang.String r0 = "endAnalysis 3"
            r5.log(r0)
            int r0 = r5.m_Error
            r1 = 0
            if (r0 == 0) goto L_0x0029
            r2 = 2
            if (r0 == r2) goto L_0x0029
            r2 = 8
            if (r0 != r2) goto L_0x0012
            goto L_0x0029
        L_0x0012:
            java.lang.String r0 = r5.m_SongDir
            if (r0 == 0) goto L_0x0021
            int r1 = r5.m_InsertIdx
            boolean r2 = r5.m_NewSong
            boolean r3 = r5.m_PlayEdit
            r4 = 0
            android.content.Intent r1 = com.xemsoft.sheetmusicscanner2.util.ActivityHelper.createPlayIntent(r0, r1, r2, r3, r4)
        L_0x0021:
            com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$AnalysisLayoutListener r0 = r5.m_Listener
            if (r0 == 0) goto L_0x0045
            r0.onAbort(r1, r6)
            return
        L_0x0029:
            com.xemsoft.sheetmusicscanner2.analysis.SessionBuilder r6 = r5.m_Builder
            if (r6 != 0) goto L_0x002e
            goto L_0x0032
        L_0x002e:
            java.lang.String r1 = r6.getSessionDir()
        L_0x0032:
            int r6 = r5.m_InsertIdx
            boolean r0 = r5.m_NewSong
            boolean r2 = r5.m_PlayEdit
            int r3 = r5.m_Error
            android.content.Intent r6 = com.xemsoft.sheetmusicscanner2.util.ActivityHelper.createPlayIntent(r1, r6, r0, r2, r3)
            com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$AnalysisLayoutListener r0 = r5.m_Listener
            if (r0 == 0) goto L_0x0045
            r0.onSuccess(r6)
        L_0x0045:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout.endAnalysis3(boolean):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void endMscaImport() {
        /*
            r9 = this;
            java.lang.String r0 = "endMascaImport"
            r9.log(r0)
            int r0 = r9.m_Error
            r1 = 1
            if (r0 != 0) goto L_0x0014
            java.util.List<java.lang.String> r0 = r9.m_MscaSessionFolders
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0014
            r9.m_Error = r1
        L_0x0014:
            int r0 = r9.m_Error
            r2 = 2131755189(0x7f1000b5, float:1.914125E38)
            r3 = 2131755193(0x7f1000b9, float:1.9141258E38)
            if (r0 == r1) goto L_0x0071
            r1 = 6
            if (r0 == r1) goto L_0x0055
            switch(r0) {
                case 9: goto L_0x003f;
                case 10: goto L_0x0029;
                case 11: goto L_0x0071;
                default: goto L_0x0024;
            }
        L_0x0024:
            r0 = 0
            r4 = r0
            r5 = r4
            r6 = r5
            goto L_0x0089
        L_0x0029:
            android.content.Context r0 = r9.m_Context
            java.lang.String r0 = r0.getString(r3)
            android.content.Context r1 = r9.m_Context
            r3 = 2131755192(0x7f1000b8, float:1.9141256E38)
            java.lang.String r1 = r1.getString(r3)
            android.content.Context r3 = r9.m_Context
            java.lang.String r2 = r3.getString(r2)
            goto L_0x0086
        L_0x003f:
            android.content.Context r0 = r9.m_Context
            java.lang.String r0 = r0.getString(r3)
            android.content.Context r1 = r9.m_Context
            r3 = 2131755191(0x7f1000b7, float:1.9141254E38)
            java.lang.String r1 = r1.getString(r3)
            android.content.Context r3 = r9.m_Context
            java.lang.String r2 = r3.getString(r2)
            goto L_0x0086
        L_0x0055:
            android.content.Context r0 = r9.m_Context
            r1 = 2131755365(0x7f100165, float:1.9141607E38)
            java.lang.String r0 = r0.getString(r1)
            android.content.Context r1 = r9.m_Context
            r2 = 2131755364(0x7f100164, float:1.9141605E38)
            java.lang.String r1 = r1.getString(r2)
            android.content.Context r2 = r9.m_Context
            r3 = 2131755362(0x7f100162, float:1.9141601E38)
            java.lang.String r2 = r2.getString(r3)
            goto L_0x0086
        L_0x0071:
            android.content.Context r0 = r9.m_Context
            java.lang.String r0 = r0.getString(r3)
            android.content.Context r1 = r9.m_Context
            r3 = 2131755190(0x7f1000b6, float:1.9141252E38)
            java.lang.String r1 = r1.getString(r3)
            android.content.Context r3 = r9.m_Context
            java.lang.String r2 = r3.getString(r2)
        L_0x0086:
            r4 = r0
            r5 = r1
            r6 = r2
        L_0x0089:
            java.util.List<android.content.Intent> r0 = r9.m_PendingIntents
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x009e
            java.util.List<android.content.Intent> r0 = r9.m_PendingIntents
            r1 = 0
            java.lang.Object r0 = r0.remove(r1)
            android.content.Intent r0 = (android.content.Intent) r0
            r9.openIntent(r0)
            return
        L_0x009e:
            if (r4 == 0) goto L_0x00ac
            com.xemsoft.sheetmusicscanner2.dialog.AlertBox r3 = r9.m_AlertBox
            com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$6 r8 = new com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout$6
            r8.<init>()
            r7 = 0
            r3.open(r4, r5, r6, r7, r8)
            return
        L_0x00ac:
            r9.endMscaImport2()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.AnalysisLayout.endMscaImport():void");
    }

    /* access modifiers changed from: private */
    public void endMscaImport2() {
        if (this.m_Error == 0) {
            AnalysisLayoutListener analysisLayoutListener = this.m_Listener;
            if (analysisLayoutListener != null) {
                analysisLayoutListener.onMscaSuccess(this.m_MscaSessionFolders);
                return;
            }
            return;
        }
        AnalysisLayoutListener analysisLayoutListener2 = this.m_Listener;
        if (analysisLayoutListener2 != null) {
            boolean z = this.m_PlayEdit;
            if (z) {
                this.m_Listener.onAbort(ActivityHelper.createPlayIntent(this.m_SongDir, this.m_InsertIdx, this.m_NewSong, z, 0), false);
                return;
            }
            analysisLayoutListener2.onMscaAbort();
        }
    }

    private void closeLoader() {
        ImageLoader imageLoader = this.m_Loader;
        if (imageLoader != null) {
            imageLoader.close();
        }
    }

    private void closeBuilder() {
        SessionBuilder sessionBuilder = this.m_Builder;
        if (sessionBuilder != null) {
            sessionBuilder.destroySession();
        }
    }

    private String getScanTitle() {
        String str;
        if (this.m_Loader.isCamera()) {
            return this.m_Context.getString(R.string.progressBarLabel_scanningPicture);
        }
        String string = this.m_Context.getString(R.string.progressBarLabel_scanningFile);
        int lastIndexOf = string.lastIndexOf("%");
        if (lastIndexOf == -1) {
            str = string + " ";
        } else {
            str = string.substring(0, lastIndexOf);
        }
        return str + this.m_Loader.getName();
    }

    private void log(String str) {
        if (this.m_Context != null) {
            Logg.d(LOGTAG, str);
        }
    }
}
