package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class RenameDialog {
    private static final String LOGTAG = "RenameDialog.java";
    /* access modifiers changed from: private */
    public TintButton m_ButCancel;
    /* access modifiers changed from: private */
    public TintButton m_ButOk;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == RenameDialog.this.m_ButOk) {
                RenameDialog.this.close();
                if (RenameDialog.this.m_Listener != null) {
                    RenameDialog.this.m_Listener.onOk(RenameDialog.this.m_EditName.getText().toString());
                }
            } else if (view == RenameDialog.this.m_ButCancel) {
                RenameDialog.this.close();
                if (RenameDialog.this.m_Listener != null) {
                    RenameDialog.this.m_Listener.onCancel();
                }
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    /* access modifiers changed from: private */
    public EditText m_EditName;
    private RelativeLayout m_LayoutTop;
    /* access modifiers changed from: private */
    public OnRenameListener m_Listener;
    private Resources m_Res;
    private TextView m_TextMessage;
    private TextView m_TextTitle;

    public interface OnRenameListener {
        void onCancel();

        void onOk(String str);
    }

    public RenameDialog(Context context) {
        this.m_Context = context;
        this.m_Res = context.getResources();
    }

    public void open(String str, String str2, String str3, String str4, String str5, OnRenameListener onRenameListener) {
        this.m_Listener = onRenameListener;
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_rename, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_LayoutTop = (RelativeLayout) inflate.findViewById(R.id.layout_top);
        this.m_TextTitle = (TextView) inflate.findViewById(R.id.text_title);
        this.m_TextMessage = (TextView) inflate.findViewById(R.id.text_message);
        this.m_EditName = (EditText) inflate.findViewById(R.id.edit_name);
        this.m_ButOk = (TintButton) inflate.findViewById(R.id.but_ok);
        this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        this.m_TextTitle.setText(str);
        this.m_TextMessage.setText(str2);
        this.m_EditName.append(str3);
        if (str4 != null) {
            this.m_ButOk.setText(str4);
        }
        if (str5 != null) {
            this.m_ButCancel.setText(str5);
        }
        this.m_ButOk.setOnClickListener(this.m_ClickListener);
        this.m_ButCancel.setOnClickListener(this.m_ClickListener);
        AlertDialog create = builder.create();
        this.m_Dlg = create;
        create.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                RenameDialog.this.close();
                if (RenameDialog.this.m_Listener != null) {
                    RenameDialog.this.m_Listener.onCancel();
                }
            }
        });
        this.m_EditName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                RenameDialog.this.m_ButOk.setEnabled(editable.toString().trim().length() != 0);
            }
        });
        try {
            this.m_Dlg.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
        this.m_Dlg.setCanceledOnTouchOutside(false);
        this.m_Dlg.show();
    }

    public void close() {
        if (this.m_Dlg != null) {
            Utils.hideSoftKeyboard(this.m_Context, this.m_EditName);
            this.m_Dlg.dismiss();
            this.m_Dlg = null;
        }
    }

    public void setDarkTheme() {
        this.m_LayoutTop.setBackground(this.m_Res.getDrawable(R.drawable.bg_dlg_black, (Resources.Theme) null));
        this.m_TextTitle.setTextColor(this.m_Res.getColor(R.color.app_white));
        this.m_TextMessage.setTextColor(this.m_Res.getColor(R.color.app_white));
        this.m_EditName.setTextColor(this.m_Res.getColor(R.color.app_white));
        this.m_ButOk.setBackgroundColor(this.m_Res.getColor(R.color.app_black));
        this.m_ButCancel.setBackgroundColor(this.m_Res.getColor(R.color.app_black));
    }
}
