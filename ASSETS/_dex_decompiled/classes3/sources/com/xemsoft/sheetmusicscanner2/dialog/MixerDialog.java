package com.xemsoft.sheetmusicscanner2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.dialog.InstrumentsPane;
import com.xemsoft.sheetmusicscanner2.dialog.MixerPane;
import com.xemsoft.sheetmusicscanner2.persist.MetronomeSettings;
import com.xemsoft.sheetmusicscanner2.persist.MixerSettings;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.Utils;

public class MixerDialog {
    private static final String LOGTAG = "com.xemsoft.sheetmusicscanner2.dialog.MixerDialog";
    private View m_BottomSpace;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.space_bottom /*2131231281*/:
                case R.id.space_top /*2131231282*/:
                    MixerDialog.this.close();
                    return;
                default:
                    return;
            }
        }
    };
    private Context m_Context;
    private Dialog m_Dlg = null;
    /* access modifiers changed from: private */
    public InstrumentsPane m_InstrumentsPane;
    private View m_Layout;
    /* access modifiers changed from: private */
    public MixerDialogListener m_Listener = null;
    /* access modifiers changed from: private */
    public MixerPane m_MixerPane;
    private View m_TopSpace;

    public interface MixerDialogListener {
        void onDone();

        void onSetMetronomeVolume(float f);

        void onSetTrackProgram(int i, int i2);

        void onSetTrackVolume1(int i, float f);

        void onSetTrackVolume2(int i, float f);
    }

    public MixerDialog(Context context) {
        this.m_Context = context;
    }

    public void setListener(MixerDialogListener mixerDialogListener) {
        this.m_Listener = mixerDialogListener;
    }

    public void open(MixerSettings mixerSettings, MetronomeSettings metronomeSettings, int i, session session) {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_mixer, (ViewGroup) null);
        this.m_Layout = inflate;
        this.m_TopSpace = inflate.findViewById(R.id.space_top);
        this.m_BottomSpace = this.m_Layout.findViewById(R.id.space_bottom);
        this.m_MixerPane = (MixerPane) this.m_Layout.findViewById(R.id.mixer_pane);
        this.m_InstrumentsPane = (InstrumentsPane) this.m_Layout.findViewById(R.id.instruments_pane);
        Dialog dialog = new Dialog(this.m_Context);
        this.m_Dlg = dialog;
        dialog.setContentView(this.m_Layout);
        this.m_Dlg.setCancelable(true);
        this.m_Dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                MixerDialog.this.close();
            }
        });
        Utils.dlgSetTransparent(this.m_Dlg);
        this.m_Dlg.setCanceledOnTouchOutside(true);
        this.m_Dlg.show();
        setup(mixerSettings, metronomeSettings, i, session);
        Utils.dlgSetNoDim(this.m_Dlg);
        this.m_Dlg.getWindow().setGravity(81);
        setTopSpace();
    }

    private void setup(MixerSettings mixerSettings, MetronomeSettings metronomeSettings, int i, session session) {
        this.m_TopSpace.setOnClickListener(this.m_ClickListener);
        this.m_BottomSpace.setOnClickListener(this.m_ClickListener);
        this.m_MixerPane.setListener(new MixerPane.MixerPaneListener() {
            public void onDone() {
                MixerDialog.this.close();
            }

            public void onSelectInstrument(int i, TrackSettings trackSettings) {
                MixerDialog.this.m_InstrumentsPane.open(i, trackSettings);
                Utils.slideLeft(MixerDialog.this.m_MixerPane, MixerDialog.this.m_InstrumentsPane);
            }

            public void onSetTrackVolume1(int i, float f) {
                if (MixerDialog.this.m_Listener != null) {
                    MixerDialog.this.m_Listener.onSetTrackVolume1(i, f);
                }
            }

            public void onSetTrackVolume2(int i, float f) {
                if (MixerDialog.this.m_Listener != null) {
                    MixerDialog.this.m_Listener.onSetTrackVolume2(i, f);
                }
            }

            public void onSetMetronomeVolume(float f) {
                if (MixerDialog.this.m_Listener != null) {
                    MixerDialog.this.m_Listener.onSetMetronomeVolume(f);
                }
            }
        });
        this.m_InstrumentsPane.setListener(new InstrumentsPane.InstrumentsPaneListener() {
            public void onBack() {
                Utils.slideRight(MixerDialog.this.m_InstrumentsPane, MixerDialog.this.m_MixerPane);
            }

            public void onSetTrackProgram(int i, int i2) {
                if (MixerDialog.this.m_Listener != null) {
                    MixerDialog.this.m_MixerPane.refresh();
                    MixerDialog.this.m_Listener.onSetTrackProgram(i, i2);
                }
            }
        });
        this.m_InstrumentsPane.setVisibility(4);
        this.m_MixerPane.setVisibility(0);
        this.m_MixerPane.open(mixerSettings, metronomeSettings, i, session);
    }

    public void close() {
        Dialog dialog = this.m_Dlg;
        if (dialog != null) {
            dialog.dismiss();
            this.m_Dlg = null;
        }
        MixerDialogListener mixerDialogListener = this.m_Listener;
        if (mixerDialogListener != null) {
            mixerDialogListener.onDone();
        }
    }

    public void rotate(Configuration configuration) {
        setTopSpace();
        if (this.m_Dlg != null) {
            this.m_InstrumentsPane.postDelayed(new Runnable() {
                public void run() {
                    MixerDialog.this.m_InstrumentsPane.rotate();
                }
            }, 300);
        }
    }

    private void setTopSpace() {
        boolean z = this.m_Context.getResources().getConfiguration().orientation == 2;
        float density = Utils.getDensity(this.m_Context);
        if (this.m_Dlg != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.m_TopSpace.getLayoutParams();
            layoutParams.height = (int) (density * (z ? 7.0f : 72.0f));
            this.m_TopSpace.setLayoutParams(layoutParams);
        }
    }
}
