package com.xemsoft.sheetmusicscanner2.player;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.player.ScoreView;
import com.xemsoft.sheetmusicscanner2.player.resource.BarButton;
import com.xemsoft.sheetmusicscanner2.player.resource.ScoreResource;
import com.xemsoft.sheetmusicscanner2.player.resource.SoundImage;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.Sequence;

public class ScoreRenderer {
    private static final int ALPHA_ACTIVE_CONTRAST = 48;
    private static final int ALPHA_ACTIVE_DEFAULT = 77;
    private static final int ALPHA_DOWN_CONTRAST = 96;
    private static final int ALPHA_DOWN_DEFAULT = 126;
    private static final float CORNER_OFFSET = 6.0f;
    private static final float CORNER_SIZE = 60.0f;
    private static final float CORNER_STROKE = 12.0f;
    private static final String LOGTAG = "ScoreController.java";
    private BarButton m_ActiveButton = null;
    private Object m_ActiveLock = null;
    private Bitmap m_BmpWork = null;
    private Canvas m_CanvasWork = null;
    private Context m_Context;
    /* access modifiers changed from: private */
    public BarButton m_DownButton = null;
    ValueAnimator m_DownOffAnimator = null;
    /* access modifiers changed from: private */
    public Global m_Global;
    private int m_PageId = 0;
    Paint m_PaintBlue;
    Paint m_PaintButActive;
    Paint m_PaintButDown;
    Paint m_PaintCorners;
    Paint m_PaintDim;
    private Resources m_Res;
    private ScoreView.ScoreViewListener m_ScoreListener = new ScoreView.ScoreViewListener() {
        public void onDown(int i, int i2) {
            ScoreRenderer.this.scoreDown(i, i2);
        }

        public void onUp(int i, int i2) {
            ScoreRenderer.this.scoreUp(i, i2);
        }

        public void onCancel() {
            if (ScoreRenderer.this.m_Global.getMode() != 2) {
                ScoreRenderer.this.scoreCancel();
            }
        }
    };
    private ScoreResource m_ScoreRes = null;
    private ScoreView m_ScoreView;
    private SessionListener m_SessionListener = null;
    private List<SoundImage> m_SoundImageList = new ArrayList();

    public ScoreRenderer(Context context, ScoreView scoreView) {
        this.m_Context = context;
        this.m_Res = context.getResources();
        this.m_Global = Global.getInstance();
        Paint paint = new Paint(1);
        this.m_PaintButActive = paint;
        paint.setColor(this.m_Res.getColor(R.color.app_bar_but_active));
        this.m_PaintButActive.setAlpha(77);
        Paint paint2 = new Paint(1);
        this.m_PaintButDown = paint2;
        paint2.setColor(this.m_Res.getColor(R.color.app_bar_but_down));
        this.m_PaintButDown.setAlpha(126);
        Paint paint3 = new Paint(1);
        this.m_PaintCorners = paint3;
        paint3.setColor(this.m_Res.getColor(R.color.app_gray_lightest));
        this.m_PaintCorners.setStyle(Paint.Style.STROKE);
        this.m_PaintCorners.setStrokeWidth(CORNER_STROKE);
        Paint paint4 = new Paint();
        this.m_PaintDim = paint4;
        paint4.setAlpha(85);
        Paint paint5 = new Paint(1);
        this.m_PaintBlue = paint5;
        paint5.setColorFilter(new PorterDuffColorFilter(this.m_Res.getColor(R.color.app_blue_overlay), PorterDuff.Mode.SRC_ATOP));
        this.m_PaintBlue.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
        this.m_ScoreView = scoreView;
        scoreView.setListener(this.m_ScoreListener);
        this.m_ActiveLock = new Object();
    }

    public void setSessionListener(SessionListener sessionListener) {
        this.m_SessionListener = sessionListener;
    }

    public void setScoreResource(ScoreResource scoreResource, int i) {
        this.m_ScoreRes = scoreResource;
        this.m_PageId = i;
        Bitmap backgroundImage = scoreResource.getBackgroundImage();
        this.m_BmpWork = Bitmap.createBitmap(backgroundImage.getWidth(), backgroundImage.getHeight(), backgroundImage.getConfig());
        this.m_CanvasWork = new Canvas(this.m_BmpWork);
        this.m_ScoreView.setImageBitmap(this.m_BmpWork);
        playingFinished();
    }

    public void setPageId(int i) {
        this.m_PageId = i;
    }

    public void setSoundVisibility(score score, sound sound, boolean z) {
        if (score.getCPtr(score) == score.getCPtr(this.m_ScoreRes.getScore())) {
            SoundImage findSoundImage = this.m_ScoreRes.findSoundImage(sound);
            if (findSoundImage != null) {
                if (!z) {
                    addSoundImage(findSoundImage);
                } else {
                    removeSoundImage(findSoundImage);
                }
            }
            redraw();
        }
    }

    public void setBarButtonSelected(score score, bar bar, boolean z) {
        boolean z2 = true;
        boolean z3 = (this.m_ActiveButton == null && this.m_DownButton == null) ? false : true;
        if (z) {
            synchronized (this.m_ActiveLock) {
                this.m_DownButton = null;
                if (score.getCPtr(score) == score.getCPtr(this.m_ScoreRes.getScore())) {
                    BarButton findBarButton = this.m_ScoreRes.findBarButton(bar);
                    this.m_ActiveButton = findBarButton;
                    if (findBarButton != null) {
                        SessionListener sessionListener = this.m_SessionListener;
                        if (sessionListener != null) {
                            sessionListener.onActiveButton(findBarButton, this.m_PageId);
                        }
                    }
                } else {
                    this.m_ActiveButton = null;
                }
                z2 = z3;
            }
            z3 = z2;
        }
        if (z3) {
            redraw();
        }
    }

    public void playingStart() {
        synchronized (this.m_SoundImageList) {
            this.m_SoundImageList.clear();
        }
        redraw();
    }

    public void playingFinished() {
        synchronized (this.m_SoundImageList) {
            this.m_SoundImageList.clear();
        }
        redraw();
    }

    private void addSoundImage(SoundImage soundImage) {
        synchronized (this.m_SoundImageList) {
            this.m_SoundImageList.add(soundImage);
        }
    }

    private void removeSoundImage(SoundImage soundImage) {
        synchronized (this.m_SoundImageList) {
            for (int i = 0; i < this.m_SoundImageList.size(); i++) {
                if (this.m_SoundImageList.get(i).key == soundImage.key) {
                    this.m_SoundImageList.remove(i);
                    return;
                }
            }
        }
    }

    public void setVisible(boolean z) {
        this.m_ScoreView.setVisible(z);
    }

    public void redraw() {
        synchronized (this.m_CanvasWork) {
            this.m_CanvasWork.drawARGB(255, 255, 255, 255);
            Paint paint = null;
            this.m_CanvasWork.drawBitmap(this.m_ScoreRes.getBackgroundImage(), Sequence.PPQ, Sequence.PPQ, this.m_Global.getHighlightMode() == 0 ? this.m_PaintDim : null);
            int mode = this.m_Global.getMode();
            if (mode == 1) {
                redrawSoundImages();
            } else {
                Canvas canvas = this.m_CanvasWork;
                Bitmap overlayImage = this.m_ScoreRes.getOverlayImage();
                if (this.m_Global.getHighlightMode() != 0) {
                    paint = this.m_PaintBlue;
                }
                canvas.drawBitmap(overlayImage, Sequence.PPQ, Sequence.PPQ, paint);
            }
            if (mode == 2) {
                redrawCorners();
            } else {
                redrawActiveButton();
                redrawDownButton();
            }
        }
        this.m_ScoreView.invalidate();
    }

    private void redrawActiveButton() {
        this.m_PaintButActive.setAlpha(this.m_Global.getHighlightMode() == 0 ? 77 : 48);
        redrawButton(this.m_ActiveButton, this.m_PaintButActive);
    }

    private void redrawDownButton() {
        this.m_PaintButDown.setAlpha(this.m_Global.getHighlightMode() == 0 ? 126 : 96);
        redrawButton(this.m_DownButton, this.m_PaintButDown);
    }

    /* JADX INFO: finally extract failed */
    private void redrawButton(BarButton barButton, Paint paint) {
        synchronized (this.m_ActiveLock) {
            if (barButton != null) {
                try {
                    this.m_CanvasWork.drawRoundRect((float) barButton.x, (float) barButton.y, (float) (barButton.x + barButton.w), (float) (barButton.y + barButton.h), 8.0f, 8.0f, paint);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private void redrawSoundImages() {
        synchronized (this.m_SoundImageList) {
            for (int i = 0; i < this.m_SoundImageList.size(); i++) {
                SoundImage soundImage = this.m_SoundImageList.get(i);
                this.m_CanvasWork.drawBitmap(soundImage.getBitmap(), (float) soundImage.x, (float) soundImage.y, this.m_Global.getHighlightMode() == 0 ? null : this.m_PaintBlue);
            }
        }
    }

    private void redrawCorners() {
        float width = (float) this.m_CanvasWork.getWidth();
        float height = (float) this.m_CanvasWork.getHeight();
        RectF rectF = new RectF(-6.0f, -6.0f, 54.0f, 54.0f);
        this.m_CanvasWork.drawArc(rectF, 180.0f, 90.0f, false, this.m_PaintCorners);
        float f = (width - CORNER_SIZE) + CORNER_OFFSET;
        float f2 = width + CORNER_OFFSET;
        rectF.set(f, -6.0f, f2, 54.0f);
        this.m_CanvasWork.drawArc(rectF, 270.0f, 90.0f, false, this.m_PaintCorners);
        float f3 = (height - CORNER_SIZE) + CORNER_OFFSET;
        float f4 = height + CORNER_OFFSET;
        rectF.set(-6.0f, f3, 54.0f, f4);
        this.m_CanvasWork.drawArc(rectF, 90.0f, 90.0f, false, this.m_PaintCorners);
        rectF.set(f, f3, f2, f4);
        this.m_CanvasWork.drawArc(rectF, Sequence.PPQ, 90.0f, false, this.m_PaintCorners);
    }

    /* access modifiers changed from: private */
    public void scoreDown(int i, int i2) {
        if (this.m_Global.getMode() == 0) {
            this.m_DownButton = this.m_ScoreRes.findBarButton(i, i2, this.m_Global.getIsGroup(), this.m_ScoreView.getWidth());
            redraw();
            return;
        }
        this.m_DownButton = null;
    }

    /* access modifiers changed from: private */
    public void scoreUp(int i, int i2) {
        if (this.m_Global.getMode() == 2) {
            this.m_SessionListener.onSetEditMode(false);
            return;
        }
        SessionListener sessionListener = this.m_SessionListener;
        if (sessionListener != null) {
            sessionListener.onButtonTap(this.m_DownButton, this.m_PageId);
        }
        this.m_DownButton = null;
    }

    /* access modifiers changed from: private */
    public void scoreCancel() {
        animateDownOff();
    }

    private void animateDownOff() {
        ValueAnimator valueAnimator = this.m_DownOffAnimator;
        if (valueAnimator != null) {
            valueAnimator.end();
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{Global.getInstance().getHighlightMode() == 0 ? 126 : 96, 0});
        this.m_DownOffAnimator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue == 0) {
                    BarButton unused = ScoreRenderer.this.m_DownButton = null;
                    ScoreRenderer.this.m_PaintButDown.setAlpha(intValue);
                    ScoreRenderer.this.m_DownOffAnimator = null;
                } else {
                    ScoreRenderer.this.m_PaintButDown.setAlpha(intValue);
                }
                ScoreRenderer.this.redraw();
            }
        });
        this.m_DownOffAnimator.setDuration(300);
        this.m_DownOffAnimator.start();
    }

    private void drawAllSounds() {
        for (int i = 0; i < this.m_ScoreRes.getSoundImageCount(); i++) {
            SoundImage soundImage = this.m_ScoreRes.getSoundImage(i);
            this.m_CanvasWork.drawBitmap(soundImage.getBitmap(), (float) soundImage.getX(), (float) soundImage.getY(), (Paint) null);
        }
        this.m_ScoreView.invalidate();
    }

    public void drawAllButtons() {
        for (int i = 0; i < this.m_ScoreRes.getBarButtonCount(); i++) {
            BarButton barButton = this.m_ScoreRes.getBarButton(i);
            if (barButton.isGroupButton) {
                this.m_CanvasWork.drawRoundRect((float) barButton.x, (float) barButton.y, (float) (barButton.x + barButton.w), (float) (barButton.y + barButton.h), 8.0f, 8.0f, this.m_PaintButActive);
            }
        }
    }
}
