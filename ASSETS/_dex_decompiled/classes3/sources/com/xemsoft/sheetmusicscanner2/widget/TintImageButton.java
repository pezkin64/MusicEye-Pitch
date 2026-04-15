package com.xemsoft.sheetmusicscanner2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import com.xemsoft.sheetmusicscanner2.R;

public class TintImageButton extends AppCompatImageButton {
    private String LOGTAG = "TintButton.java";
    private ARGB m_ColorIcon = null;
    private ARGB m_DefColorIcon = null;
    private ARGB m_GrayColorIcon = null;
    public int tintIdx = 0;

    private int getTintByIndex(int i) {
        if (i > 255) {
            i = 255;
        }
        if (i < 0) {
            i = 0;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 |= i << (i3 * 8);
        }
        return i2;
    }

    public TintImageButton(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public TintImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public TintImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TintImageButton, i, 0);
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                this.m_DefColorIcon = new ARGB(color);
            }
        }
        this.m_GrayColorIcon = new ARGB(ContextCompat.getColor(context, R.color.app_gray_light));
        this.m_ColorIcon = this.m_DefColorIcon;
        setTintIdx(0);
    }

    public int getDrawableTint() {
        ARGB argb = this.m_ColorIcon;
        if (argb != null) {
            return argb.normal();
        }
        return 0;
    }

    public void setDrawableTint(int i) {
        this.m_ColorIcon = new ARGB(i);
        setTintIdx(0);
    }

    public int getTintIdx() {
        return this.tintIdx;
    }

    public void setTintIdx(int i) {
        this.tintIdx = i;
        int tintByIndex = getTintByIndex(i);
        ARGB argb = this.m_ColorIcon;
        if (argb != null) {
            tintByIndex = argb.tinted(tintByIndex);
        }
        setColorFilter(tintByIndex);
    }

    public void setEnabled(boolean z) {
        TintImageButton.super.setEnabled(z);
        this.m_ColorIcon = z ? this.m_DefColorIcon : this.m_GrayColorIcon;
        setTintIdx(0);
    }
}
