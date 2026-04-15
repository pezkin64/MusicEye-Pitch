package com.xemsoft.sheetmusicscanner2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.synth.Synth;
import com.xemsoft.sheetmusicscanner2.util.OnDoneListener;

public class SplashDialog {
    /* access modifiers changed from: private */
    public Context m_Context;
    private Dialog m_Dlg = null;

    public SplashDialog(Context context) {
        this.m_Context = context;
    }

    public void open(final OnDoneListener onDoneListener) {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_splash, (ViewGroup) null);
        Dialog dialog = new Dialog(this.m_Context, R.style.FullDialogTheme);
        this.m_Dlg = dialog;
        dialog.setContentView(inflate);
        this.m_Dlg.setCancelable(false);
        this.m_Dlg.setCanceledOnTouchOutside(false);
        Window window = this.m_Dlg.getWindow();
        window.setFlags(1024, 1024);
        this.m_Dlg.show();
        window.setLayout(-1, -1);
        inflate.post(new Runnable() {
            public void run() {
                SplashDialog.this.load(onDoneListener);
            }
        });
    }

    public void close() {
        Dialog dialog = this.m_Dlg;
        if (dialog != null) {
            dialog.dismiss();
            this.m_Dlg = null;
        }
    }

    /* access modifiers changed from: private */
    public void load(final OnDoneListener onDoneListener) {
        final Handler handler = new Handler(Looper.getMainLooper());
        final Synth instance = Synth.getInstance(this.m_Context);
        final SessionUtility instance2 = SessionUtility.getInstance(this.m_Context);
        boolean isLoaded = instance.isLoaded();
        final boolean z = !isLoaded;
        boolean areAssetsLoaded = instance2.areAssetsLoaded();
        final boolean z2 = !areAssetsLoaded;
        if (!isLoaded || !areAssetsLoaded) {
            final OnDoneListener onDoneListener2 = onDoneListener;
            new Thread(new Runnable(false, false) {
                final /* synthetic */ boolean val$loadTestData;

                {
                    this.val$loadTestData = r6;
                }

                public void run() {
                    if (z) {
                        instance.load();
                    }
                    if (z2) {
                        instance2.loadAssets();
                        UserSettings.getInstance(SplashDialog.this.m_Context).setDefaults();
                    }
                    if (this.val$loadTestData) {
                        instance2.loadTestData();
                    }
                    if (false) {
                        instance2.createTestDb();
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            SplashDialog.this.close();
                            if (onDoneListener2 != null) {
                                onDoneListener2.onDone(0, (String) null);
                            }
                        }
                    });
                }
            }).start();
            return;
        }
        handler.postDelayed(new Runnable() {
            public void run() {
                SplashDialog.this.close();
                OnDoneListener onDoneListener = onDoneListener;
                if (onDoneListener != null) {
                    onDoneListener.onDone(0, (String) null);
                }
            }
        }, 500);
    }
}
