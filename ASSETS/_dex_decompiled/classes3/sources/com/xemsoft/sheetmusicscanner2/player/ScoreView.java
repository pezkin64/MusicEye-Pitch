package com.xemsoft.sheetmusicscanner2.player;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageView;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import jp.kshoji.javax.sound.midi.Sequence;

public class ScoreView extends AppCompatImageView {
    private static final String LOGTAG = "ScoreView.java";
    private Bitmap m_Bmp = null;
    private Context m_Context;
    boolean m_IsVisible = false;
    /* access modifiers changed from: private */
    public ScoreViewListener m_Listener = null;
    private View.OnTouchListener m_OnTouchListener = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked == 3 || actionMasked == 4) {
                        if (ScoreView.this.m_Listener == null) {
                            return false;
                        }
                        ScoreView.this.m_Listener.onCancel();
                        return false;
                    }
                } else if (ScoreView.this.m_Listener != null) {
                    ScoreView.this.m_Listener.onUp((int) motionEvent.getX(), (int) motionEvent.getY());
                }
            } else if (ScoreView.this.m_Listener != null) {
                ScoreView.this.m_Listener.onDown((int) motionEvent.getX(), (int) motionEvent.getY());
            }
            return true;
        }
    };
    float m_Ratio = Sequence.PPQ;

    public interface ScoreViewListener {
        void onCancel();

        void onDown(int i, int i2);

        void onUp(int i, int i2);
    }

    public ScoreView(Context context) {
        super(context);
        init(context);
    }

    public ScoreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ScoreView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.m_Bmp = bitmap;
        this.m_Ratio = ((float) bitmap.getHeight()) / ((float) bitmap.getWidth());
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int screenWidth = Utils.getScreenWidth(this.m_Context);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            screenWidth = size;
        } else if (mode == Integer.MIN_VALUE) {
            screenWidth = Math.min(screenWidth, size);
        }
        setMeasuredDimension(screenWidth, (int) (((float) screenWidth) * this.m_Ratio));
    }

    private void init(Context context) {
        this.m_Context = context;
        setOnTouchListener(this.m_OnTouchListener);
    }

    public void setListener(ScoreViewListener scoreViewListener) {
        this.m_Listener = scoreViewListener;
    }

    public void setVisible(boolean z) {
        if (this.m_IsVisible != z) {
            Bitmap bitmap = this.m_Bmp;
            if (bitmap != null) {
                if (!z) {
                    bitmap = null;
                }
                ScoreView.super.setImageBitmap(bitmap);
            }
            this.m_IsVisible = z;
        }
    }
}
