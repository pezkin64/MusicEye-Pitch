package com.xemsoft.sheetmusicscanner2.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class AnimatedProgressBar extends ProgressBar {
    private ValueAnimator m_Animator = null;
    private int m_Max = 0;
    private int m_Progress = 0;

    public AnimatedProgressBar(Context context) {
        super(context);
    }

    public AnimatedProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnimatedProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public synchronized void setProgress(int i) {
        this.m_Progress = i;
        if (i > 0) {
            animateProgress(i * 100);
        } else {
            super.setProgress(0);
        }
    }

    public synchronized int getProgress() {
        return this.m_Progress;
    }

    public synchronized int getMax() {
        return this.m_Max;
    }

    public synchronized void setMax(int i) {
        this.m_Max = i;
        super.setMax(i * 100);
    }

    private void animateProgress(int i) {
        ValueAnimator valueAnimator = this.m_Animator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i - 100, i});
        this.m_Animator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                AnimatedProgressBar.super.setProgress(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        });
        this.m_Animator.setDuration(500);
        this.m_Animator.start();
    }
}
