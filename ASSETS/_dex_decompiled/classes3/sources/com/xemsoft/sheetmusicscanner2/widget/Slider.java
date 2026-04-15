package com.xemsoft.sheetmusicscanner2.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import java.util.ArrayList;
import java.util.List;

public class Slider extends AppCompatSeekBar {
    private static final int IGNORE_TIME = 100;
    private SeekBar.OnSeekBarChangeListener m_SeekListener = new SeekBar.OnSeekBarChangeListener() {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z) {
                Slider.this.changePosition(i);
            }
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
            Slider.this.m_ValueTimeList.clear();
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            Slider.this.fixPosition();
        }
    };
    private SliderListener m_SliderListener = null;
    /* access modifiers changed from: private */
    public List<ValueTime> m_ValueTimeList = new ArrayList();

    public interface SliderListener {
        void onPositionChanged(Slider slider, int i);

        void onStopTracking(Slider slider, int i);
    }

    private class ValueTime {
        public long m_Time = System.currentTimeMillis();
        private int m_Value;

        public ValueTime(int i) {
            this.m_Value = i;
        }

        public int getValue() {
            return this.m_Value;
        }

        public long getTime() {
            return this.m_Time;
        }
    }

    public Slider(Context context) {
        super(context);
        init();
    }

    public Slider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public Slider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void setSliderListener(SliderListener sliderListener) {
        this.m_SliderListener = sliderListener;
    }

    private void init() {
        setOnSeekBarChangeListener(this.m_SeekListener);
    }

    /* access modifiers changed from: private */
    public void changePosition(int i) {
        this.m_ValueTimeList.add(new ValueTime(i));
        SliderListener sliderListener = this.m_SliderListener;
        if (sliderListener != null) {
            sliderListener.onPositionChanged(this, i);
        }
    }

    /* access modifiers changed from: private */
    public void fixPosition() {
        long currentTimeMillis = System.currentTimeMillis() - 100;
        for (int size = this.m_ValueTimeList.size() - 1; size >= 0; size--) {
            ValueTime valueTime = this.m_ValueTimeList.get(size);
            if (valueTime.getTime() < currentTimeMillis) {
                int value = valueTime.getValue();
                setProgress(value);
                SliderListener sliderListener = this.m_SliderListener;
                if (sliderListener != null) {
                    sliderListener.onPositionChanged(this, value);
                    this.m_SliderListener.onStopTracking(this, value);
                    return;
                }
                return;
            }
        }
        this.m_SliderListener.onStopTracking(this, getProgress());
    }
}
