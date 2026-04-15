package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import com.xemsoft.sheetmusicscanner2.R;

public class ChoicePopup {
    private static final String LOGTAG = "ChoicePopup.java";
    /* access modifiers changed from: private */
    public Button m_ButDelete;
    /* access modifiers changed from: private */
    public Button m_ButRename;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == ChoicePopup.this.m_ButDelete) {
                if (ChoicePopup.this.m_Listener != null) {
                    ChoicePopup.this.m_Listener.onDelete();
                }
            } else if (view == ChoicePopup.this.m_ButRename && ChoicePopup.this.m_Listener != null) {
                ChoicePopup.this.m_Listener.onRename();
            }
            ChoicePopup.this.close();
        }
    };
    private Context m_Context;
    private PopupWindow m_Dlg = null;
    /* access modifiers changed from: private */
    public ChoicePopupListener m_Listener = null;

    public interface ChoicePopupListener {
        void onDelete();

        void onRename();
    }

    public ChoicePopup(Context context) {
        this.m_Context = context;
    }

    public void open(View view, boolean z, ChoicePopupListener choicePopupListener) {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.popup_choice, (ViewGroup) null);
        this.m_Listener = choicePopupListener;
        this.m_Dlg = new PopupWindow(inflate, -2, -2);
        this.m_Dlg.setElevation(5.0f);
        this.m_ButDelete = (Button) inflate.findViewById(R.id.but_delete);
        this.m_ButRename = (Button) inflate.findViewById(R.id.but_rename);
        this.m_ButDelete.setOnClickListener(this.m_ClickListener);
        this.m_ButRename.setOnClickListener(this.m_ClickListener);
        this.m_Dlg.setFocusable(true);
        this.m_Dlg.showAsDropDown(view, 0, z ? -((int) (((float) view.getHeight()) * 3.3f)) : 0);
    }

    public void close() {
        PopupWindow popupWindow = this.m_Dlg;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.m_Dlg = null;
        }
    }
}
