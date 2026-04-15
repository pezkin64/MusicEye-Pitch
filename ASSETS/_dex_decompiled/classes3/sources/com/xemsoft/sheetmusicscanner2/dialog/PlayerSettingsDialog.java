package com.xemsoft.sheetmusicscanner2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.Slider;

public class PlayerSettingsDialog {
    private static final int BPM_MAX = 300;
    private static final int BPM_OFFSET = 30;
    private static final int HZ_MAX = 100;
    private static final int HZ_OFFSET = 380;
    private static final String LOGTAG = "PlayerSettingsDialog.java";
    private Button m_ButDone;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == 2131230841 || id == 2131231281) {
                PlayerSettingsDialog.this.close();
                return;
            }
            switch (id) {
                case R.id.view_reset_1 /*2131231378*/:
                case R.id.view_reset_2 /*2131231379*/:
                case R.id.view_reset_3 /*2131231380*/:
                    if (PlayerSettingsDialog.this.m_Listener != null) {
                        int onResetHz = PlayerSettingsDialog.this.m_Listener.onResetHz();
                        PlayerSettingsDialog.this.m_SeekHz.setProgress(onResetHz - 380);
                        TextView access$200 = PlayerSettingsDialog.this.m_TextHz;
                        access$200.setText(onResetHz + BuildConfig.FLAVOR);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private Context m_Context;
    private Dialog m_Dlg = null;
    private RelativeLayout m_LayoutGroup;
    private View m_Line4;
    /* access modifiers changed from: private */
    public PlayerSettingsListener m_Listener = null;
    private Slider m_SeekBpm;
    /* access modifiers changed from: private */
    public Slider m_SeekHz;
    private Slider.SliderListener m_SliderListener = new Slider.SliderListener() {
        public void onPositionChanged(Slider slider, int i) {
            if (slider == PlayerSettingsDialog.this.m_SeekHz) {
                TextView access$200 = PlayerSettingsDialog.this.m_TextHz;
                access$200.setText((i + PlayerSettingsDialog.HZ_OFFSET) + BuildConfig.FLAVOR);
                return;
            }
            TextView access$300 = PlayerSettingsDialog.this.m_TextBpm;
            StringBuilder sb = new StringBuilder();
            int i2 = i + 30;
            sb.append(i2);
            sb.append(BuildConfig.FLAVOR);
            access$300.setText(sb.toString());
            if (PlayerSettingsDialog.this.m_Listener != null) {
                PlayerSettingsDialog.this.m_Listener.onBpm(i2);
            }
        }

        public void onStopTracking(Slider slider, int i) {
            if (slider == PlayerSettingsDialog.this.m_SeekHz && PlayerSettingsDialog.this.m_Listener != null) {
                PlayerSettingsDialog.this.m_Listener.onHz(i + PlayerSettingsDialog.HZ_OFFSET);
            }
        }
    };
    private View m_SpaceBottom;
    private Switch m_SwitchGroup;
    private Switch m_SwitchSwing;
    /* access modifiers changed from: private */
    public TextView m_TextBpm;
    /* access modifiers changed from: private */
    public TextView m_TextHz;
    private View m_ViewReset1;
    private View m_ViewReset2;
    private View m_ViewReset3;

    public interface PlayerSettingsListener {
        void onBpm(int i);

        void onDone();

        void onHz(int i);

        void onIsGroup(boolean z);

        int onResetHz();

        void onSwing(boolean z);
    }

    public PlayerSettingsDialog(Context context) {
        this.m_Context = context;
    }

    public void setListener(PlayerSettingsListener playerSettingsListener) {
        this.m_Listener = playerSettingsListener;
    }

    public void open(int i, int i2, boolean z, boolean z2, boolean z3) {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_player_settings, (ViewGroup) null);
        Dialog dialog = new Dialog(this.m_Context);
        this.m_Dlg = dialog;
        dialog.setContentView(inflate);
        this.m_Dlg.setCancelable(true);
        this.m_ButDone = (Button) inflate.findViewById(R.id.but_done);
        this.m_SeekHz = (Slider) inflate.findViewById(R.id.seek_hz);
        this.m_SeekBpm = (Slider) inflate.findViewById(R.id.seek_bpm);
        this.m_TextHz = (TextView) inflate.findViewById(R.id.text_hz);
        this.m_TextBpm = (TextView) inflate.findViewById(R.id.text_bpm);
        this.m_SwitchSwing = (Switch) inflate.findViewById(R.id.switch_swing);
        this.m_Line4 = inflate.findViewById(R.id.line4);
        this.m_LayoutGroup = (RelativeLayout) inflate.findViewById(R.id.layout_group);
        this.m_SwitchGroup = (Switch) inflate.findViewById(R.id.switch_group);
        this.m_ViewReset1 = inflate.findViewById(R.id.view_reset_1);
        this.m_ViewReset2 = inflate.findViewById(R.id.view_reset_2);
        this.m_ViewReset3 = inflate.findViewById(R.id.view_reset_3);
        this.m_SpaceBottom = inflate.findViewById(R.id.space_bottom);
        int i3 = z2 ? 0 : 8;
        this.m_Line4.setVisibility(i3);
        this.m_LayoutGroup.setVisibility(i3);
        this.m_Dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                PlayerSettingsDialog.this.close();
            }
        });
        Utils.dlgSetTransparent(this.m_Dlg);
        setUI(i, i2, z, z3);
        this.m_Dlg.setCanceledOnTouchOutside(true);
        this.m_Dlg.show();
        Utils.dlgSetNoDim(this.m_Dlg);
        this.m_Dlg.getWindow().setGravity(85);
        this.m_Dlg.getWindow().setLayout(-1, -2);
    }

    public void close() {
        Dialog dialog = this.m_Dlg;
        if (dialog != null) {
            dialog.dismiss();
            this.m_Dlg = null;
        }
        PlayerSettingsListener playerSettingsListener = this.m_Listener;
        if (playerSettingsListener != null) {
            playerSettingsListener.onDone();
        }
    }

    private void setUI(int i, int i2, boolean z, boolean z2) {
        this.m_SeekHz.setMax(100);
        this.m_SeekHz.setProgress(i - 380);
        this.m_SeekHz.setSliderListener(this.m_SliderListener);
        TextView textView = this.m_TextHz;
        textView.setText(i + BuildConfig.FLAVOR);
        this.m_SeekBpm.setMax(BPM_MAX);
        this.m_SeekBpm.setProgress(i2 + -30);
        this.m_SeekBpm.setSliderListener(this.m_SliderListener);
        TextView textView2 = this.m_TextBpm;
        textView2.setText(i2 + BuildConfig.FLAVOR);
        this.m_SwitchSwing.setChecked(z);
        this.m_SwitchSwing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (PlayerSettingsDialog.this.m_Listener != null) {
                    PlayerSettingsDialog.this.m_Listener.onSwing(z);
                }
            }
        });
        this.m_SwitchGroup.setChecked(z2);
        this.m_SwitchGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (PlayerSettingsDialog.this.m_Listener != null) {
                    PlayerSettingsDialog.this.m_Listener.onIsGroup(z);
                }
            }
        });
        this.m_ButDone.setOnClickListener(this.m_ClickListener);
        this.m_ViewReset1.setOnClickListener(this.m_ClickListener);
        this.m_ViewReset2.setOnClickListener(this.m_ClickListener);
        this.m_ViewReset3.setOnClickListener(this.m_ClickListener);
        this.m_SpaceBottom.setOnClickListener(this.m_ClickListener);
        Drawable thumbDrawable = this.m_SwitchGroup.getThumbDrawable();
        this.m_SeekHz.setThumb(thumbDrawable.getConstantState().newDrawable());
        this.m_SeekBpm.setThumb(thumbDrawable.getConstantState().newDrawable());
    }
}
