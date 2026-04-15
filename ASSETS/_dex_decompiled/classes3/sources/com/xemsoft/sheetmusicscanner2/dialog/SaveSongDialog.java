package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class SaveSongDialog {
    private static final String LOGTAG = "SaveSongDialog.java";
    private TintButton m_ButCancel;
    private TintButton m_ButDelete;
    private TintButton m_ButSave;
    private DialogInterface.OnCancelListener m_CancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            SaveSongDialog.this.close();
            if (SaveSongDialog.this.m_Listener != null) {
                SaveSongDialog.this.m_Listener.onCancel();
            }
        }
    };
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == 2131230834) {
                SaveSongDialog.this.close();
                if (SaveSongDialog.this.m_Listener != null) {
                    SaveSongDialog.this.m_Listener.onCancel();
                }
            } else if (id == 2131230839) {
                SaveSongDialog.this.close();
                if (SaveSongDialog.this.m_Listener != null) {
                    SaveSongDialog.this.m_Listener.onDelete();
                }
            } else if (id == 2131230870) {
                SaveSongDialog.this.close();
                if (SaveSongDialog.this.m_Listener != null) {
                    SaveSongDialog.this.m_Listener.onSave();
                }
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    /* access modifiers changed from: private */
    public SaveSongDialogListener m_Listener = null;

    public interface SaveSongDialogListener {
        void onCancel();

        void onDelete();

        void onSave();
    }

    public SaveSongDialog(Context context) {
        this.m_Context = context;
    }

    public void open(SaveSongDialogListener saveSongDialogListener) {
        this.m_Listener = saveSongDialogListener;
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_save_song, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_ButSave = (TintButton) inflate.findViewById(R.id.but_save);
        this.m_ButDelete = (TintButton) inflate.findViewById(R.id.but_delete);
        this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        AlertDialog create = builder.create();
        this.m_Dlg = create;
        create.setOnCancelListener(this.m_CancelListener);
        this.m_ButSave.setOnClickListener(this.m_ClickListener);
        this.m_ButDelete.setOnClickListener(this.m_ClickListener);
        this.m_ButCancel.setOnClickListener(this.m_ClickListener);
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
