package com.xemsoft.sheetmusicscanner2.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.AppCompatImageView;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;

public class RotateDialog {
    private Bitmap m_Bmp;
    private TintButton m_ButCancel;
    private TintImageButton m_ButLeft;
    private TintButton m_ButRescan;
    private TintImageButton m_ButRight;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.but_cancel /*2131230834*/:
                    RotateDialog.this.close();
                    if (RotateDialog.this.m_Listener != null) {
                        RotateDialog.this.m_Listener.onCancel();
                        return;
                    }
                    return;
                case R.id.but_left /*2131230849*/:
                    RotateDialog.this.rotate(-90);
                    return;
                case R.id.but_rescan /*2131230867*/:
                    RotateDialog.this.close();
                    if (RotateDialog.this.m_Listener != null) {
                        RotateDialog.this.m_Listener.onRescan(RotateDialog.this.m_RotBmp, RotateDialog.this.m_Degrees);
                        return;
                    }
                    return;
                case R.id.but_right /*2131230869*/:
                    RotateDialog.this.rotate(90);
                    return;
                default:
                    return;
            }
        }
    };
    private Context m_Context;
    /* access modifiers changed from: private */
    public int m_Degrees = 0;
    private Dialog m_Dlg = null;
    private AppCompatImageView m_Image;
    private int m_InitDegrees = 0;
    /* access modifiers changed from: private */
    public RotateListener m_Listener = null;
    /* access modifiers changed from: private */
    public Bitmap m_RotBmp = null;

    public interface RotateListener {
        void onCancel();

        void onRescan(Bitmap bitmap, int i);
    }

    public RotateDialog(Context context) {
        this.m_Context = context;
    }

    public void open(Bitmap bitmap, int i, RotateListener rotateListener) {
        this.m_Bmp = bitmap;
        this.m_Listener = rotateListener;
        this.m_InitDegrees = i;
        this.m_Degrees = i;
        this.m_Dlg = new Dialog(this.m_Context, R.style.FullDialogThemeBlack);
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_rotate, (ViewGroup) null);
        this.m_Dlg.setContentView(inflate);
        this.m_Dlg.setCancelable(true);
        this.m_Dlg.setCanceledOnTouchOutside(false);
        Window window = this.m_Dlg.getWindow();
        window.setFlags(1024, 1024);
        if (Build.VERSION.SDK_INT >= 28) {
            window.addFlags(67108864);
            window.getAttributes().layoutInDisplayCutoutMode = 1;
        }
        AppCompatImageView findViewById = inflate.findViewById(R.id.image);
        this.m_Image = findViewById;
        findViewById.setLayerType(1, (Paint) null);
        this.m_ButRight = (TintImageButton) inflate.findViewById(R.id.but_right);
        this.m_ButLeft = (TintImageButton) inflate.findViewById(R.id.but_left);
        this.m_ButRescan = (TintButton) inflate.findViewById(R.id.but_rescan);
        this.m_ButCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        this.m_ButRight.setOnClickListener(this.m_ClickListener);
        this.m_ButLeft.setOnClickListener(this.m_ClickListener);
        this.m_ButRescan.setOnClickListener(this.m_ClickListener);
        this.m_ButCancel.setOnClickListener(this.m_ClickListener);
        this.m_Dlg.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                RotateDialog.this.close();
                if (RotateDialog.this.m_Listener != null) {
                    RotateDialog.this.m_Listener.onCancel();
                }
            }
        });
        this.m_Dlg.show();
        Utils.dlgSetTransparent(this.m_Dlg);
        rotate(0);
    }

    public void close() {
        Dialog dialog = this.m_Dlg;
        if (dialog != null) {
            dialog.dismiss();
            this.m_Dlg = null;
        }
    }

    private void updateUI() {
        this.m_ButRescan.setEnabled(this.m_Degrees != this.m_InitDegrees);
    }

    /* access modifiers changed from: private */
    public void rotate(int i) {
        int i2 = this.m_Degrees + i;
        this.m_Degrees = i2;
        if (i2 >= 360) {
            this.m_Degrees = i2 - 360;
        } else if (i2 < 0) {
            this.m_Degrees = i2 + 360;
        }
        Bitmap bitmap = this.m_Bmp;
        if (bitmap != null) {
            Bitmap rotateBitmap = Utils.rotateBitmap(bitmap, this.m_Degrees, false);
            this.m_RotBmp = rotateBitmap;
            if (rotateBitmap != null) {
                this.m_Image.setImageBitmap(rotateBitmap);
            }
        }
        updateUI();
    }
}
