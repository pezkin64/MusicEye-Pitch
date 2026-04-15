package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class AlertBox {
    private static final String LOGTAG = "AlertBox.java";
    /* access modifiers changed from: private */
    public TintButton m_ButCancel;
    /* access modifiers changed from: private */
    public TintButton m_ButOk;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == AlertBox.this.m_ButOk) {
                AlertBox.this.close();
                if (AlertBox.this.m_Listener != null) {
                    AlertBox.this.m_Listener.onOk();
                }
            } else if (view == AlertBox.this.m_ButCancel) {
                AlertBox.this.close();
                if (AlertBox.this.m_Listener != null) {
                    AlertBox.this.m_Listener.onCancel();
                }
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    private LinearLayout m_LayoutButH;
    private LinearLayout m_LayoutButV;
    private RelativeLayout m_LayoutTop;
    /* access modifiers changed from: private */
    public OnAlertListener m_Listener;
    private Resources m_Res;
    private View m_Separator;
    private TextView m_TextMessage;
    private TextView m_TextTitle;

    public interface OnAlertListener {
        void onCancel();

        void onOk();
    }

    public AlertBox(Context context) {
        this.m_Context = context;
        this.m_Res = context.getResources();
    }

    public void open(String str, String str2, String str3, String str4, OnAlertListener onAlertListener) {
        open(str, str2, str3, str4, false, true, onAlertListener);
    }

    public void open(String str, String str2, String str3, String str4, boolean z, boolean z2, OnAlertListener onAlertListener) {
        this.m_Listener = onAlertListener;
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_alert_box, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_LayoutTop = (RelativeLayout) inflate.findViewById(R.id.layout_top);
        this.m_LayoutButH = (LinearLayout) inflate.findViewById(R.id.layout_but_h);
        this.m_LayoutButV = (LinearLayout) inflate.findViewById(R.id.layout_but_v);
        this.m_TextTitle = (TextView) inflate.findViewById(R.id.text_title);
        this.m_TextMessage = (TextView) inflate.findViewById(R.id.text_message);
        if (!z) {
            this.m_ButOk = (TintButton) inflate.findViewById(R.id.but_ok);
            this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
            this.m_Separator = inflate.findViewById(R.id.line2);
            this.m_LayoutButH.setVisibility(0);
            this.m_LayoutButV.setVisibility(8);
        } else {
            this.m_ButOk = (TintButton) inflate.findViewById(R.id.but_ok_v);
            this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel_v);
            this.m_Separator = inflate.findViewById(R.id.line3);
            this.m_LayoutButH.setVisibility(8);
            this.m_LayoutButV.setVisibility(0);
        }
        this.m_TextTitle.setText(str);
        this.m_TextMessage.setText(str2);
        if (str3 != null) {
            this.m_ButOk.setText(str3);
        } else {
            this.m_ButOk.setVisibility(8);
            this.m_Separator.setVisibility(8);
        }
        if (str4 != null) {
            this.m_ButCancel.setText(str4);
        } else {
            this.m_ButCancel.setVisibility(8);
            this.m_Separator.setVisibility(8);
        }
        this.m_ButOk.setOnClickListener(this.m_ClickListener);
        this.m_ButCancel.setOnClickListener(this.m_ClickListener);
        AlertDialog create = builder.create();
        this.m_Dlg = create;
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                AlertBox.this.close();
                if (AlertBox.this.m_Listener != null) {
                    AlertBox.this.m_Listener.onCancel();
                }
            }
        });
        try {
            this.m_Dlg.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
        this.m_Dlg.setCanceledOnTouchOutside(z2);
        this.m_Dlg.show();
    }

    public void close() {
        AlertDialog alertDialog = this.m_Dlg;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.m_Dlg = null;
        }
    }

    public void setOkRed() {
        if (this.m_Dlg != null) {
            this.m_ButOk.setDrawablesTint(this.m_Res.getColor(R.color.app_red));
        }
    }

    public void setDarkTheme() {
        if (this.m_Dlg != null) {
            this.m_LayoutTop.setBackground(this.m_Res.getDrawable(R.drawable.bg_dlg_black, (Resources.Theme) null));
            this.m_TextTitle.setTextColor(this.m_Res.getColor(R.color.app_white));
            this.m_TextMessage.setTextColor(this.m_Res.getColor(R.color.app_white));
            this.m_ButOk.setBackgroundColor(this.m_Res.getColor(R.color.app_black));
            this.m_ButCancel.setBackgroundColor(this.m_Res.getColor(R.color.app_black));
        }
    }
}
