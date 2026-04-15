package com.xemsoft.sheetmusicscanner2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class LockableScrollView extends ScrollView {
    private boolean m_Scrollable = true;

    public LockableScrollView(Context context) {
        super(context);
    }

    public LockableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LockableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setScrollingEnabled(boolean z) {
        this.m_Scrollable = z;
    }

    public boolean isScrollable() {
        return this.m_Scrollable;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.m_Scrollable) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.m_Scrollable) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
