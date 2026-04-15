package com.xemsoft.sheetmusicscanner2.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResource;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.Sequence;

public class SessionView extends ScrollView {
    private static final String LOGTAG = "SessionView.java";
    private float m_CenterPageHeight = Sequence.PPQ;
    private int m_CenterPageIdx = 0;
    private float m_CenterPageOff = Sequence.PPQ;
    private Context m_Context;
    boolean m_IsRotating = false;
    private boolean m_MaintainCenter = false;
    private int m_OldHScrollPos = 0;
    private float m_OldScoreHeight = Sequence.PPQ;
    private int m_OldVScrollPos = 0;
    /* access modifiers changed from: private */
    public List<ScorePageView> m_PageList = new ArrayList();
    /* access modifiers changed from: private */
    public LinearLayout m_ScoreLayout;

    public SessionView(Context context) {
        super(context);
        init(context);
    }

    public SessionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SessionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        if (!this.m_IsRotating) {
            this.m_OldHScrollPos = i;
            this.m_OldVScrollPos = i2;
            this.m_OldScoreHeight = getScoreHeight();
        }
        super.onScrollChanged(i, i2, i3, i4);
        setScoreVisibility();
    }

    public void setScoreVisibility() {
        int scrollY = getScrollY();
        int height = getHeight() + scrollY;
        int i = 0;
        int i2 = 0;
        while (i < this.m_PageList.size()) {
            ScorePageView scorePageView = this.m_PageList.get(i);
            int height2 = scorePageView.getHeight() + i2;
            if ((i2 < scrollY || i2 > height) && ((height2 < scrollY || height2 > height) && (i2 > scrollY || height2 < height))) {
                scorePageView.setVisible(false);
            } else {
                scorePageView.setVisible(true);
            }
            i++;
            i2 = height2;
        }
    }

    private float getScoreHeight() {
        float f = Sequence.PPQ;
        for (int i = 0; i < this.m_PageList.size(); i++) {
            f += (float) this.m_PageList.get(i).getHeight();
        }
        return f;
    }

    private void init(Context context) {
        this.m_Context = context;
        this.m_ScoreLayout = (LinearLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.view_session, this, true).findViewById(R.id.layout_score);
    }

    public void setResourceList(List<ScoreResource> list) {
        int size = list.size();
        int i = 0;
        while (i < size) {
            ScorePageView scorePageView = new ScorePageView(this.m_Context);
            scorePageView.setScoreResource(list.get(i), i);
            scorePageView.setId(i);
            scorePageView.setPageId(i);
            scorePageView.setIsLastPage(i == size + -1);
            scorePageView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.m_ScoreLayout.addView(scorePageView);
            this.m_PageList.add(scorePageView);
            i++;
        }
        this.m_ScoreLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                SessionView.this.maintainCenter();
            }
        });
    }

    public void setSessionListener(SessionListener sessionListener) {
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).setListener(sessionListener);
        }
    }

    public void setLastPage() {
        int size = this.m_PageList.size();
        int i = 0;
        while (i < size) {
            this.m_PageList.get(i).setIsLastPage(i == size + -1);
            i++;
        }
    }

    public void setEditMode(boolean z) {
        fling(0);
        setCenter();
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).setEditMode(z);
        }
    }

    public void setSoundVisibility(score score, sound sound, boolean z) {
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).setSoundVisibility(score, sound, z);
        }
    }

    public void setBarButtonSelected(score score, bar bar, boolean z) {
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).setBarButtonSelected(score, bar, z);
        }
    }

    public void playingStart() {
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).playingStart();
        }
    }

    public void playingFinished() {
        for (int i = 0; i < this.m_PageList.size(); i++) {
            this.m_PageList.get(i).playingFinished();
        }
    }

    public void deletePage(final int i) {
        int i2;
        if (i > 0) {
            i2 = -this.m_PageList.get(i - 1).getHeight();
        } else {
            i2 = i < this.m_PageList.size() + -1 ? this.m_PageList.get(i).getHeight() : 0;
        }
        if (i2 != 0) {
            smoothScrollBy(0, i2);
        }
        postDelayed(new Runnable() {
            public void run() {
                SessionView.this.m_ScoreLayout.removeView((View) SessionView.this.m_PageList.get(i));
                SessionView.this.m_PageList.remove(i);
                SessionView.this.reindexPageList();
            }
        }, 200);
    }

    public void deleteAllPages() {
        this.m_ScoreLayout.removeAllViews();
        this.m_PageList = new ArrayList();
    }

    public ScorePageView getPageView(int i) {
        if (i < 0 || i >= this.m_PageList.size()) {
            return null;
        }
        return this.m_PageList.get(i);
    }

    public int getPageCount() {
        return this.m_PageList.size();
    }

    /* access modifiers changed from: private */
    public void reindexPageList() {
        int size = this.m_PageList.size();
        int i = 0;
        while (i < size) {
            ScorePageView scorePageView = this.m_PageList.get(i);
            scorePageView.setId(i);
            scorePageView.setPageId(i);
            scorePageView.setIsLastPage(i == size + -1);
            scorePageView.updateLayout();
            i++;
        }
    }

    public void scrollOnRotate() {
        scrollTo(0, (int) (((float) this.m_OldVScrollPos) * (((float) this.m_ScoreLayout.getHeight()) / this.m_OldScoreHeight)));
        this.m_IsRotating = false;
    }

    public void startRotate() {
        this.m_IsRotating = true;
    }

    public void scrollPageToView(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += getPageView(i3).getHeight();
        }
        scrollTo(0, i2);
    }

    public void setEditTransitionDone() {
        this.m_MaintainCenter = false;
    }

    private void setCenter() {
        this.m_MaintainCenter = true;
        float scrollY = ((float) getScrollY()) + (((float) getHeight()) / 2.0f);
        float f = Sequence.PPQ;
        for (int i = 0; i < getPageCount(); i++) {
            float height = (float) getPageView(i).getHeight();
            f += height;
            if (f >= scrollY) {
                this.m_CenterPageIdx = i;
                this.m_CenterPageHeight = height;
                this.m_CenterPageOff = height - (f - scrollY);
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void maintainCenter() {
        ScorePageView pageView = getPageView(this.m_CenterPageIdx);
        if (!this.m_MaintainCenter) {
            return;
        }
        if (pageView != null) {
            float height = ((float) getPageView(this.m_CenterPageIdx).getHeight()) / this.m_CenterPageHeight;
            float f = Sequence.PPQ;
            for (int i = 0; i < this.m_CenterPageIdx; i++) {
                f += (float) getPageView(i).getHeight();
            }
            scrollTo(0, (int) ((f + (this.m_CenterPageOff * height)) - (((float) getHeight()) / 2.0f)));
            return;
        }
        this.m_MaintainCenter = false;
    }
}
