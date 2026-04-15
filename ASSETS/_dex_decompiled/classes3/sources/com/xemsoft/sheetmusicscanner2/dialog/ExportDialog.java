package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class ExportDialog {
    private static final String LOGTAG = "ImportDialog.java";
    private DialogInterface.OnCancelListener m_CancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            ExportDialog.this.close();
            if (ExportDialog.this.m_Listener != null) {
                ExportDialog.this.m_Listener.onCancel();
            }
        }
    };
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.but_aac /*2131230825*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(2);
                        return;
                    }
                    return;
                case R.id.but_cancel /*2131230834*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onCancel();
                        return;
                    }
                    return;
                case R.id.but_midi /*2131230850*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(1);
                        return;
                    }
                    return;
                case R.id.but_mp3 /*2131230852*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(3);
                        return;
                    }
                    return;
                case R.id.but_msca /*2131230853*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(7);
                        return;
                    }
                    return;
                case R.id.but_mxml /*2131230857*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(5);
                        return;
                    }
                    return;
                case R.id.but_pdf /*2131230860*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(6);
                        return;
                    }
                    return;
                case R.id.but_wav /*2131230881*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(4);
                        return;
                    }
                    return;
                case R.id.but_xml /*2131230882*/:
                    ExportDialog.this.close();
                    if (ExportDialog.this.m_Listener != null) {
                        ExportDialog.this.m_Listener.onExport(8);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_Dlg = null;
    /* access modifiers changed from: private */
    public ExportDialogListener m_Listener = null;
    private ScrollView m_ScrollBut;

    public interface ExportDialogListener {
        void onCancel();

        void onExport(int i);
    }

    public ExportDialog(Context context) {
        this.m_Context = context;
    }

    public void open(ExportDialogListener exportDialogListener) {
        this.m_Listener = exportDialogListener;
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_export, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        inflate.findViewById(R.id.but_aac).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_mp3).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_wav).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_midi).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_mxml).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_xml).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_pdf).setOnClickListener(this.m_ClickListener);
        inflate.findViewById(R.id.but_cancel).setOnClickListener(this.m_ClickListener);
        TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_msca);
        tintButton.setOnClickListener(this.m_ClickListener);
        tintButton.setText("MSCA (" + this.m_Context.getString(R.string.app_name_short) + ")");
        this.m_ScrollBut = (ScrollView) inflate.findViewById(R.id.scroll_but);
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

    public void rotate(boolean z) {
        ScrollView scrollView;
        if (this.m_Dlg != null && (scrollView = this.m_ScrollBut) != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) scrollView.getLayoutParams();
            layoutParams.height = z ? (int) (Utils.getDensity(this.m_Context) * 200.0f) : -2;
            this.m_ScrollBut.setLayoutParams(layoutParams);
        }
    }
}
