package com.xemsoft.sheetmusicscanner2.player;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResource;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;

public class ScorePageView extends RelativeLayout {
    private static final String LOGTAG = "ScorePageView.java";
    private TintImageButton m_ButAdd;
    private TintImageButton m_ButAddLast;
    private TintImageButton m_ButDelete;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.but_add:
                    if (ScorePageView.this.m_SessionListener != null) {
                        ScorePageView.this.m_SessionListener.onAddPage(ScorePageView.this.m_PageId);
                        return;
                    }
                    return;
                case R.id.but_add_last:
                    if (ScorePageView.this.m_SessionListener != null) {
                        ScorePageView.this.m_SessionListener.onAddPage(ScorePageView.this.m_PageId + 1);
                        return;
                    }
                    return;
                case R.id.but_delete:
                    if (ScorePageView.this.m_SessionListener != null) {
                        ScorePageView.this.m_SessionListener.onDeletePage(ScorePageView.this.m_PageId);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private Context m_Context;
    private FrameLayout m_FrameBottom;
    private boolean m_IsLastPage = false;
    /* access modifiers changed from: private */
    public int m_PageId = 0;
    private Resources m_Res;
    private ScoreView m_ScoreView;
    /* access modifiers changed from: private */
    public SessionListener m_SessionListener = null;
    private ScoreRenderer m_Sheet;
    private View m_ViewTop;

    public ScorePageView(Context context) {
        super(context);
        init(context);
    }

    public ScorePageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public ScorePageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.m_Context = context;
        this.m_Res = context.getResources();
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_score_page, this, true);
        this.m_ButAdd = (TintImageButton) inflate.findViewById(R.id.but_add);
        this.m_ButAddLast = (TintImageButton) inflate.findViewById(R.id.but_add_last);
        this.m_ButDelete = (TintImageButton) inflate.findViewById(R.id.but_delete);
        this.m_ViewTop = inflate.findViewById(R.id.view_top);
        this.m_FrameBottom = (FrameLayout) inflate.findViewById(R.id.frame_bottom);
        this.m_ScoreView = (ScoreView) inflate.findViewById(R.id.view_score);
        this.m_ButAdd.setOnClickListener(this.m_ClickListener);
        this.m_ButAddLast.setOnClickListener(this.m_ClickListener);
        this.m_ButDelete.setOnClickListener(this.m_ClickListener);
        this.m_Sheet = new ScoreRenderer(context, this.m_ScoreView);
    }

    public void setListener(SessionListener sessionListener) {
        this.m_SessionListener = sessionListener;
        this.m_Sheet.setSessionListener(sessionListener);
    }

    public void setScoreResource(ScoreResource scoreResource, int i) {
        this.m_PageId = i;
        this.m_Sheet.setScoreResource(scoreResource, i);
        this.m_ViewTop.setVisibility(this.m_PageId == 0 ? 0 : 8);
    }

    public void setPageId(int i) {
        this.m_PageId = i;
        this.m_Sheet.setPageId(i);
        this.m_ViewTop.setVisibility(this.m_PageId == 0 ? 0 : 8);
    }

    public void setIsLastPage(boolean z) {
        this.m_IsLastPage = z;
        this.m_FrameBottom.setVisibility(z ? 0 : 8);
    }

    public void setEditMode(boolean z) {
        this.m_Sheet.redraw();
        animateScoreMargins(z);
    }

    public void setSoundVisibility(score score, sound sound, boolean z) {
        this.m_Sheet.setSoundVisibility(score, sound, z);
    }

    public void setBarButtonSelected(score score, bar bar, boolean z) {
        this.m_Sheet.setBarButtonSelected(score, bar, z);
    }

    public void playingStart() {
        this.m_Sheet.playingStart();
    }

    public void playingFinished() {
        this.m_Sheet.playingFinished();
    }

    public void updateLayout() {
        Rect editMargins = getEditMargins();
        setScoreMargins(editMargins.left, editMargins.top, editMargins.right, editMargins.bottom);
    }

    public void setVisible(boolean z) {
        this.m_Sheet.setVisible(z);
    }

    /* access modifiers changed from: private */
    public Rect getEditMargins() {
        float f;
        float f2;
        int dimension = (int) this.m_Res.getDimension(R.dimen.edit_margin_left);
        if (this.m_PageId == 0) {
            f = this.m_Res.getDimension(R.dimen.edit_margin_top_first);
        } else {
            f = this.m_Res.getDimension(R.dimen.edit_margin_top);
        }
        int i = (int) f;
        int dimension2 = (int) this.m_Res.getDimension(R.dimen.edit_margin_right);
        if (this.m_IsLastPage) {
            f2 = this.m_Res.getDimension(R.dimen.edit_margin_bottom_last);
        } else {
            f2 = this.m_Res.getDimension(R.dimen.edit_margin_bottom);
        }
        return new Rect(dimension, i, dimension2, (int) f2);
    }

    /* access modifiers changed from: private */
    public void setScoreMargins(int i, int i2, int i3, int i4) {
        ((ViewGroup.MarginLayoutParams) this.m_ScoreView.getLayoutParams()).setMargins(i, i2, i3, i4);
        this.m_ScoreView.requestLayout();
        requestLayout();
    }

    private void animateScoreMargins(boolean z) {
        int i = 0;
        int i2 = z ? 0 : 100;
        if (z) {
            i = 100;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i2, i});
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int percent;
            private Rect rect;

            {
                this.rect = ScorePageView.this.getEditMargins();
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.percent = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ScorePageView.this.setScoreMargins((this.rect.left * this.percent) / 100, (this.rect.top * this.percent) / 100, (this.rect.right * this.percent) / 100, (this.rect.bottom * this.percent) / 100);
            }
        });
        ofInt.addListener(new Animator.AnimatorListener() {
            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (ScorePageView.this.m_SessionListener != null) {
                    ScorePageView.this.m_SessionListener.onEditTransitionDone();
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (ScorePageView.this.m_SessionListener != null) {
                    ScorePageView.this.m_SessionListener.onEditTransitionDone();
                }
            }
        });
        ofInt.setDuration(300);
        ofInt.start();
    }
}
