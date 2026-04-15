package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;

public class SaveFileDialog {
    private static final String LOGTAG = "SaveFileDialog.java";
    private DialogInterface.OnCancelListener m_CancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            SaveFileDialog.this.close();
            if (SaveFileDialog.this.m_Listener != null) {
                SaveFileDialog.this.m_Listener.onCancel();
            }
        }
    };
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == 2131230832) {
                SaveFileDialog.this.close();
                if (SaveFileDialog.this.m_Listener != null) {
                    SaveFileDialog.this.m_Listener.onBrowse();
                }
            } else if (id == 2131230834) {
                SaveFileDialog.this.close();
                if (SaveFileDialog.this.m_Listener != null) {
                    SaveFileDialog.this.m_Listener.onCancel();
                }
            } else if (id == 2131230876) {
                SaveFileDialog.this.close();
                if (SaveFileDialog.this.m_Listener != null) {
                    SaveFileDialog.this.m_Listener.onShare();
                }
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    /* access modifiers changed from: private */
    public SaveFileDialogListener m_Listener = null;

    public interface SaveFileDialogListener {
        void onBrowse();

        void onCancel();

        void onShare();
    }

    public SaveFileDialog(Context context) {
        this.m_Context = context;
    }

    public void open(SaveFileDialogListener saveFileDialogListener) {
        this.m_Listener = saveFileDialogListener;
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_save_file, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        inflate.findViewById(R.id.but_settings).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_browse).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_cancel).setOnClickListener(this.m_ClickListener);
        AlertDialog create = builder.create();
        this.m_Dlg = create;
        create.setOnCancelListener(this.m_CancelListener);
        Utils.dlgSetTransparent(this.m_Dlg);
        this.m_Dlg.setCanceledOnTouchOutside(true);
        this.m_Dlg.show();
        this.m_Dlg.getWindow().setGravity(80);
    }

    public void close() {
        AlertDialog alertDialog = this.m_Dlg;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.m_Dlg = null;
        }
    }
}
