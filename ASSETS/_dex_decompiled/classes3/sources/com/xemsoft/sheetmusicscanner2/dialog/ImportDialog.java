package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class ImportDialog {
    private static final String LOGTAG = "ImportDialog.java";
    private TintButton m_ButBrowse;
    private TintButton m_ButCamera;
    private TintButton m_ButCancel;
    private TintButton m_ButPhotos;
    private DialogInterface.OnCancelListener m_CancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            ImportDialog.this.close();
        }
    };
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id != 2131230861) {
                if (id != 2131230992) {
                    switch (id) {
                        case R.id.but_browse /*2131230832*/:
                            ImportDialog.this.close();
                            if (ImportDialog.this.m_Listener != null) {
                                ImportDialog.this.m_Listener.onBrowse();
                                return;
                            }
                            return;
                        case R.id.but_camera /*2131230833*/:
                            ImportDialog.this.close();
                            if (ImportDialog.this.m_Listener != null) {
                                ImportDialog.this.m_Listener.onCamera();
                                return;
                            }
                            return;
                        case R.id.but_cancel /*2131230834*/:
                            break;
                        default:
                            return;
                    }
                }
                ImportDialog.this.close();
                return;
            }
            ImportDialog.this.close();
            if (ImportDialog.this.m_Listener != null) {
                ImportDialog.this.m_Listener.onPhotos();
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    private FrameLayout m_FrameCancel;
    /* access modifiers changed from: private */
    public ImportDialogListener m_Listener = null;
    private View m_SepCamera;

    public interface ImportDialogListener {
        void onBrowse();

        void onCamera();

        void onPhotos();
    }

    public ImportDialog(Context context) {
        this.m_Context = context;
    }

    public void setListener(ImportDialogListener importDialogListener) {
        this.m_Listener = importDialogListener;
    }

    public void open(boolean z) {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_import, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_ButCamera = (TintButton) inflate.findViewById(R.id.but_camera);
        this.m_ButPhotos = (TintButton) inflate.findViewById(R.id.but_photos);
        this.m_ButBrowse = (TintButton) inflate.findViewById(R.id.but_browse);
        this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        this.m_FrameCancel = (FrameLayout) inflate.findViewById(R.id.frame_cancel);
        this.m_SepCamera = inflate.findViewById(R.id.line1);
        if (!z) {
            this.m_ButCamera.setVisibility(8);
            this.m_SepCamera.setVisibility(8);
        }
        AlertDialog create = builder.create();
        this.m_Dlg = create;
        create.setOnCancelListener(this.m_CancelListener);
        this.m_ButCamera.setOnClickListener(this.m_ClickListener);
        this.m_ButPhotos.setOnClickListener(this.m_ClickListener);
        this.m_ButBrowse.setOnClickListener(this.m_ClickListener);
        this.m_ButCancel.setOnClickListener(this.m_ClickListener);
        this.m_FrameCancel.setOnClickListener(this.m_ClickListener);
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
