package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.R;

public class SettingItem extends RelativeLayout {
    private TextView m_Title;

    public SettingItem(Context context) {
        super(context);
        init(context);
    }

    public SettingItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SettingItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.m_Title = (TextView) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.item_setting, this, true).findViewById(R.id.text_title);
    }

    public void setTitle(String str) {
        this.m_Title.setText(str);
    }
}
