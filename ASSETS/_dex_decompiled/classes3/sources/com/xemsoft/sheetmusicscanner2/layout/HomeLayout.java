package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.activity.ScannerActivity;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.ImportDialog;
import com.xemsoft.sheetmusicscanner2.dialog.RotateDialog;
import com.xemsoft.sheetmusicscanner2.dialog.SplashDialog;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class HomeLayout extends RelativeLayout {
    private static final String LOGTAG = "HomeLayout.java";
    /* access modifiers changed from: private */
    public ScannerActivity m_Activity = null;
    private AlertBox m_AlertBox = null;
    /* access modifiers changed from: private */
    public TintButton m_ButHelp;
    /* access modifiers changed from: private */
    public TintButton m_ButScanCamera;
    /* access modifiers changed from: private */
    public TintButton m_ButScanFiles;
    /* access modifiers changed from: private */
    public TintButton m_ButScanPhotos;
    /* access modifiers changed from: private */
    public TintButton m_ButSettings;
    /* access modifiers changed from: private */
    public TintButton m_ButSongList;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        /* JADX WARNING: type inference failed for: r2v25, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onClick(View view) {
            if (view == HomeLayout.this.m_ButHelp) {
                ActivityHelper.openHelp(HomeLayout.this.m_Activity);
            } else if (view == HomeLayout.this.m_ButScanCamera) {
                if (HomeLayout.this.m_Listener != null) {
                    HomeLayout.this.m_Listener.onCamera();
                }
            } else if (view == HomeLayout.this.m_ButScanPhotos) {
                if (HomeLayout.this.m_Listener != null) {
                    HomeLayout.this.m_Listener.onPhotos();
                }
            } else if (view == HomeLayout.this.m_ButScanFiles) {
                if (HomeLayout.this.m_Listener != null) {
                    HomeLayout.this.m_Listener.onFiles();
                }
            } else if (view == HomeLayout.this.m_ButSongList) {
                if (!HomeLayout.this.m_IsButtonClicked) {
                    boolean unused = HomeLayout.this.m_IsButtonClicked = true;
                    if (HomeLayout.this.m_Listener != null) {
                        HomeLayout.this.m_Listener.onSongList();
                    }
                }
            } else if (view == HomeLayout.this.m_ButSettings && HomeLayout.this.m_Listener != null) {
                HomeLayout.this.m_Listener.onSettings();
            }
        }
    };
    /* access modifiers changed from: private */
    public Context m_Context;
    private RotateDialog m_DlgRotate;
    private ImportDialog m_ImportDlg;
    /* access modifiers changed from: private */
    public boolean m_IsButtonClicked = false;
    private ConstraintLayout m_LayoutContent;
    /* access modifiers changed from: private */
    public HomeLayoutListener m_Listener;
    private View m_PadLeft;
    private View m_PadRight;
    private SplashDialog m_SplashDialog;
    private TextView m_TextTitle;
    private View[] m_ViewList = new View[6];

    public interface HomeLayoutListener {
        void onCamera();

        void onFiles();

        void onPhotos();

        void onSettings();

        void onSongList();
    }

    public HomeLayout(Context context) {
        super(context);
        init(context);
    }

    public HomeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public HomeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        this.m_Context = context;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_home, this, true);
        this.m_LayoutContent = findViewById(R.id.layout_content);
        this.m_TextTitle = (TextView) findViewById(R.id.text_title);
        this.m_PadLeft = findViewById(R.id.pad_left);
        this.m_PadRight = findViewById(R.id.pad_right);
        Object[] objArr = this.m_ViewList;
        TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_help);
        this.m_ButHelp = tintButton;
        int i = 0;
        objArr[0] = tintButton;
        Object[] objArr2 = this.m_ViewList;
        TintButton tintButton2 = (TintButton) inflate.findViewById(R.id.but_scan_camera);
        this.m_ButScanCamera = tintButton2;
        objArr2[1] = tintButton2;
        Object[] objArr3 = this.m_ViewList;
        TintButton tintButton3 = (TintButton) inflate.findViewById(R.id.but_scan_photos);
        this.m_ButScanPhotos = tintButton3;
        objArr3[2] = tintButton3;
        Object[] objArr4 = this.m_ViewList;
        TintButton tintButton4 = (TintButton) inflate.findViewById(R.id.but_scan_files);
        this.m_ButScanFiles = tintButton4;
        objArr4[3] = tintButton4;
        Object[] objArr5 = this.m_ViewList;
        TintButton tintButton5 = (TintButton) inflate.findViewById(R.id.but_song_list);
        this.m_ButSongList = tintButton5;
        objArr5[4] = tintButton5;
        Object[] objArr6 = this.m_ViewList;
        TintButton tintButton6 = (TintButton) inflate.findViewById(R.id.but_settings);
        this.m_ButSettings = tintButton6;
        objArr6[5] = tintButton6;
        while (true) {
            View[] viewArr = this.m_ViewList;
            if (i >= viewArr.length) {
                break;
            }
            viewArr[i].setOnClickListener(this.m_ClickListener);
            i++;
        }
        if (!Utils.hasCamera(context)) {
            this.m_ButScanCamera.setVisibility(4);
            this.m_ButScanCamera.setOnClickListener((View.OnClickListener) null);
        }
        this.m_SplashDialog = new SplashDialog(this.m_Context);
        this.m_ImportDlg = new ImportDialog(this.m_Context);
        this.m_AlertBox = new AlertBox(this.m_Context);
        Button button = (Button) inflate.findViewById(R.id.but_test);
        this.m_DlgRotate = new RotateDialog(this.m_Context);
        button.setVisibility(8);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeLayout.this.openTestDbDialog();
            }
        });
        manageLayout();
    }

    public void setActivity(ScannerActivity scannerActivity) {
        this.m_Activity = scannerActivity;
    }

    public void setListener(HomeLayoutListener homeLayoutListener) {
        this.m_Listener = homeLayoutListener;
    }

    public void resume() {
        this.m_IsButtonClicked = false;
    }

    public void destroy() {
        this.m_SplashDialog.close();
        this.m_ImportDlg.close();
        this.m_AlertBox.close();
    }

    public void onConfigurationChanged(Configuration configuration) {
        manageLayout();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            manageLayout();
        }
    }

    private void manageLayout() {
        float f;
        float f2;
        int screenMinWidth = (int) (((float) Utils.getScreenMinWidth(this.m_Context)) / Utils.getDensity(this.m_Context));
        Configuration configuration = getResources().getConfiguration();
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(this.m_LayoutContent);
        float f3 = 0.8f;
        int i = 8;
        int i2 = 0;
        if (((float) screenMinWidth) < 540.0f) {
            int i3 = configuration.orientation;
            f2 = 0.075f;
            f = 1.0f;
            if (i3 == 2) {
                f3 = 0.38f;
                i2 = 8;
                i = 0;
            }
        } else {
            f = 0.7f;
            if (configuration.orientation == 2) {
                f2 = 0.13f;
                f3 = 0.7f;
                f = 0.45f;
            } else {
                f2 = 0.15f;
            }
        }
        constraintSet.setVerticalBias(R.id.text_title, f2);
        constraintSet.setVerticalBias(R.id.layout_bot, f3);
        constraintSet.constrainPercentWidth(R.id.layout_bot, f);
        constraintSet.applyTo(this.m_LayoutContent);
        this.m_PadLeft.setVisibility(i);
        this.m_PadRight.setVisibility(i);
        this.m_TextTitle.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void openTestDbDialog() {
        this.m_AlertBox.open("Test DB", "Recreate testMidi database?", "OK", "Cancel", new AlertBox.OnAlertListener() {
            public void onCancel() {
            }

            public void onOk() {
                SessionUtility.getInstance(HomeLayout.this.m_Context).createTestDb();
                UserSettings instance = UserSettings.getInstance(HomeLayout.this.m_Context);
                instance.setInstallDate(0);
                instance.setDefaults();
            }
        });
    }
}
