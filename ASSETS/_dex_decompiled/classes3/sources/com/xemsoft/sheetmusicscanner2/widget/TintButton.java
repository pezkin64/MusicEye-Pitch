package com.xemsoft.sheetmusicscanner2.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.xemsoft.sheetmusicscanner2.R;

public class TintButton extends AppCompatButton {
    private String LOGTAG = "TintButton.java";
    private ARGB m_ColorIcon = null;
    private ARGB m_ColorText;
    private Context m_Context;
    private ARGB m_DefColorIcon = null;
    private ARGB m_DefColorText;
    private ARGB m_GrayColorIcon = null;
    private ARGB m_GrayColorText;
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

    public TintButton(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0);
    }

    public TintButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public TintButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    public void setEnabled(boolean z) {
        TintButton.super.setEnabled(z);
        if (z) {
            this.m_ColorText = this.m_DefColorText;
            this.m_ColorIcon = this.m_DefColorIcon;
        } else {
            this.m_ColorText = this.m_GrayColorText;
            this.m_ColorIcon = this.m_GrayColorIcon;
        }
        setTintIdx(0);
    }

    public void setClickable(boolean z) {
        TintButton.super.setClickable(z);
        TintButton.super.setEnabled(z);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.m_Context = context;
        this.m_DefColorText = new ARGB(getTextColors().getDefaultColor());
        this.m_GrayColorText = new ARGB(ContextCompat.getColor(this.m_Context, R.color.app_gray_light));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TintButton, i, 0);
            int color = obtainStyledAttributes.getColor(0, 0);
            obtainStyledAttributes.recycle();
            if (color != 0) {
                this.m_DefColorIcon = new ARGB(color);
            }
        }
        this.m_GrayColorIcon = new ARGB(ContextCompat.getColor(this.m_Context, R.color.app_gray_light));
        this.m_ColorText = this.m_DefColorText;
        this.m_ColorIcon = this.m_DefColorIcon;
        setTintIdx(0);
    }

    public int getDrawablesTint() {
        ARGB argb = this.m_ColorIcon;
        if (argb != null) {
            return argb.normal();
        }
        return 0;
    }

    public void setDrawablesTint(int i) {
        this.m_ColorIcon = new ARGB(i);
        this.m_ColorText = new ARGB(i);
        setTintIdx(0);
    }

    public int getTintIdx() {
        return this.tintIdx;
    }

    public void setTintIdx(int i) {
        this.tintIdx = i;
        int tintByIndex = getTintByIndex(i);
        int tintByIndex2 = getTintByIndex(i / 2);
        setTextColor(this.m_ColorText.tinted(tintByIndex2));
        for (Drawable drawable : getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setTintMode(PorterDuff.Mode.SRC_ATOP);
                Drawable mutate = DrawableCompat.wrap(drawable).mutate();
                ARGB argb = this.m_ColorIcon;
                DrawableCompat.setTint(mutate, argb != null ? argb.tinted(tintByIndex2) : tintByIndex);
            }
        }
    }
}
