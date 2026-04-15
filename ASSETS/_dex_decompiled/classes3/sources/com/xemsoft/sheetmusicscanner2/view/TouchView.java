package com.xemsoft.sheetmusicscanner2.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View {
    private static final String LOGTAG = "TouchView.java";
    private TouchViewListener m_Listener = null;

    public interface TouchViewListener {
        void onTouchDown();

        void onTouchUp();
    }

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public TouchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.m_Listener != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                this.m_Listener.onTouchDown();
            } else if (actionMasked == 1 || actionMasked == 3) {
                this.m_Listener.onTouchUp();
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onWindowFocusChanged(boolean z) {
        if (!z) {
            requestFocus();
        }
        super.onWindowFocusChanged(z);
    }

    public void setTouchListener(TouchViewListener touchViewListener) {
        this.m_Listener = touchViewListener;
    }
}
