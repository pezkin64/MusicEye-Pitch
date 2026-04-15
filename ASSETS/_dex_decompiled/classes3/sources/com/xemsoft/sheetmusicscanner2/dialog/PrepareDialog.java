package com.xemsoft.sheetmusicscanner2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xemsoft.sheetmusicscanner2.R;

public class PrepareDialog {
    private Context m_Context;
    private Dialog m_Dlg = null;

    public PrepareDialog(Context context) {
        this.m_Context = context;
    }

    public void open() {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_prepare, (ViewGroup) null);
        Dialog dialog = new Dialog(this.m_Context, R.style.FullDialogThemeTransparent);
        this.m_Dlg = dialog;
        dialog.setContentView(inflate);
        this.m_Dlg.setCancelable(false);
        this.m_Dlg.setCanceledOnTouchOutside(false);
        this.m_Dlg.getWindow().setFlags(1024, 1024);
        this.m_Dlg.show();
    }

    public void close() {
        Dialog dialog = this.m_Dlg;
        if (dialog != null) {
            dialog.dismiss();
            this.m_Dlg = null;
        }
    }
}
