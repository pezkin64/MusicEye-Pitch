package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.R;

public class HighlightItem extends RelativeLayout {
    private ImageView m_Check;
    private TextView m_Description;
    private TextView m_Title;

    public HighlightItem(Context context) {
        super(context);
        init(context);
    }

    public HighlightItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public HighlightItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.item_highlight, this, true);
        this.m_Title = (TextView) inflate.findViewById(R.id.text_title);
        this.m_Description = (TextView) inflate.findViewById(R.id.text_description);
        this.m_Check = (ImageView) inflate.findViewById(R.id.img_check);
    }

    public void setTitle(String str) {
        this.m_Title.setText(str);
    }

    public void setDescription(String str) {
        this.m_Description.setText(str);
    }

    public void setCheck(boolean z) {
        this.m_Check.setVisibility(z ? 0 : 4);
    }
}
