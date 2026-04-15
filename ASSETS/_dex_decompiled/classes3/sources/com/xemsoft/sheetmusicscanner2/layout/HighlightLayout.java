package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;

public class HighlightLayout extends RelativeLayout {
    private static final String LOGTAG = "HighlightLayout.java";
    /* access modifiers changed from: private */
    public TintImageButton m_ButBack;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == HighlightLayout.this.m_ButBack) {
                if (HighlightLayout.this.m_Listener != null) {
                    HighlightLayout.this.m_Listener.onBack();
                }
            } else if (view == HighlightLayout.this.m_HighlightDefault) {
                HighlightLayout.this.m_Prefs.setHighlightMode(0);
                HighlightLayout.this.updateUI();
                if (HighlightLayout.this.m_Listener != null) {
                    HighlightLayout.this.m_Listener.onDefault();
                }
            } else if (view == HighlightLayout.this.m_HighlightContrast) {
                HighlightLayout.this.m_Prefs.setHighlightMode(1);
                HighlightLayout.this.updateUI();
                if (HighlightLayout.this.m_Listener != null) {
                    HighlightLayout.this.m_Listener.onContrast();
                }
            }
        }
    };
    private Context m_Context;
    /* access modifiers changed from: private */
    public HighlightItem m_HighlightContrast;
    /* access modifiers changed from: private */
    public HighlightItem m_HighlightDefault;
    /* access modifiers changed from: private */
    public HighlightLayoutListener m_Listener = null;
    /* access modifiers changed from: private */
    public UserSettings m_Prefs;

    public interface HighlightLayoutListener {
        void onBack();

        void onContrast();

        void onDefault();
    }

    public HighlightLayout(Context context) {
        super(context);
        init(context);
    }

    public HighlightLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public HighlightLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_highlight, this, true);
        this.m_Prefs = UserSettings.getInstance(context);
        this.m_ButBack = (TintImageButton) inflate.findViewById(R.id.but_back);
        this.m_HighlightDefault = (HighlightItem) inflate.findViewById(R.id.highlight_default);
        this.m_HighlightContrast = (HighlightItem) inflate.findViewById(R.id.highlight_contrast);
        this.m_ButBack.setOnClickListener(this.m_ClickListener);
        this.m_HighlightDefault.setOnClickListener(this.m_ClickListener);
        this.m_HighlightContrast.setOnClickListener(this.m_ClickListener);
        this.m_HighlightDefault.setTitle(context.getString(R.string._0O0_xj_all_normalTitle));
        this.m_HighlightDefault.setDescription(context.getString(R.string.k58_PJ_k3F_text));
        this.m_HighlightContrast.setTitle(context.getString(R.string.otC_AB_XLp_normalTitle));
        this.m_HighlightContrast.setDescription(context.getString(R.string._3X8_Kd_2YW_text));
        updateUI();
    }

    public void setListener(HighlightLayoutListener highlightLayoutListener) {
        this.m_Listener = highlightLayoutListener;
    }

    /* access modifiers changed from: private */
    public void updateUI() {
        if (this.m_Prefs.getHighlightMode() == 0) {
            this.m_HighlightDefault.setCheck(true);
            this.m_HighlightContrast.setCheck(false);
            return;
        }
        this.m_HighlightDefault.setCheck(false);
        this.m_HighlightContrast.setCheck(true);
    }
}
