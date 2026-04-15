package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;

public class SettingsLayout extends RelativeLayout {
    private static final String LOGTAG = "SettingsLayout.java";
    /* access modifiers changed from: private */
    public TintImageButton m_ButBack;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == SettingsLayout.this.m_ButBack) {
                if (SettingsLayout.this.m_Listener != null) {
                    SettingsLayout.this.m_Listener.onBack();
                }
            } else if (view == SettingsLayout.this.m_SettingHighlight) {
                if (SettingsLayout.this.m_Listener != null) {
                    SettingsLayout.this.m_Listener.onHighlight();
                }
            } else if (view == SettingsLayout.this.m_SettingSubscription) {
                if (SettingsLayout.this.m_Listener != null) {
                    SettingsLayout.this.m_Listener.onSubscription();
                }
            } else if (view == SettingsLayout.this.m_SettingAbout && SettingsLayout.this.m_Listener != null) {
                SettingsLayout.this.m_Listener.onAbout();
            }
        }
    };
    private Context m_Context;
    /* access modifiers changed from: private */
    public SettingsLayoutListener m_Listener = null;
    /* access modifiers changed from: private */
    public SettingItem m_SettingAbout;
    /* access modifiers changed from: private */
    public SettingItem m_SettingHighlight;
    /* access modifiers changed from: private */
    public SettingItem m_SettingSubscription;

    public interface SettingsLayoutListener {
        void onAbout();

        void onBack();

        void onHighlight();

        void onSubscription();
    }

    public SettingsLayout(Context context) {
        super(context);
        init(context);
    }

    public SettingsLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SettingsLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_settings, this, true);
        this.m_ButBack = (TintImageButton) inflate.findViewById(R.id.but_back);
        this.m_SettingHighlight = (SettingItem) inflate.findViewById(R.id.setting_highlight);
        this.m_SettingSubscription = (SettingItem) inflate.findViewById(R.id.setting_subscription);
        this.m_SettingAbout = (SettingItem) inflate.findViewById(R.id.setting_about);
        this.m_ButBack.setOnClickListener(this.m_ClickListener);
        this.m_SettingHighlight.setOnClickListener(this.m_ClickListener);
        this.m_SettingSubscription.setOnClickListener(this.m_ClickListener);
        this.m_SettingAbout.setOnClickListener(this.m_ClickListener);
        this.m_SettingHighlight.setTitle(context.getString(R.string._0Ke_3O_a4c_normalTitle));
        this.m_SettingSubscription.setTitle(context.getString(R.string.bCN_7C_yVP_normalTitle));
        this.m_SettingAbout.setTitle(context.getString(R.string._4R7_4N_ybM_text));
    }

    public void setListener(SettingsLayoutListener settingsLayoutListener) {
        this.m_Listener = settingsLayoutListener;
    }
}
