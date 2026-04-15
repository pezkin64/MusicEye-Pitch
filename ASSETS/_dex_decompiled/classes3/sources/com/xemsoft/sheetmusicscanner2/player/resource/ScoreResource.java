package com.xemsoft.sheetmusicscanner2.player.resource;

import android.graphics.Bitmap;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import java.util.ArrayList;
import java.util.List;

public class ScoreResource {
    private static final String LOGTAG = "ScoreResource.java";
    private Bitmap m_BackgroundImage = null;
    private List<BarButton> m_BarButtons = new ArrayList();
    private int m_Height;
    private int m_OrigH;
    private int m_OrigW;
    private Bitmap m_OverlayImage = null;
    private int m_PageIdx;
    private float m_Scale;
    private score m_Score;
    private List<SoundImage> m_SoundImages = new ArrayList();
    private int m_Width;

    public ScoreResource(int i, int i2, int i3, int i4, float f, int i5) {
        this.m_OrigW = i;
        this.m_OrigH = i2;
        this.m_Width = i3;
        this.m_Height = i4;
        this.m_Scale = f;
        this.m_PageIdx = i5;
    }

    public int getOrigW() {
        return this.m_OrigW;
    }

    public int getOrigH() {
        return this.m_OrigH;
    }

    public int getWidth() {
        return this.m_Width;
    }

    public int getHeight() {
        return this.m_Height;
    }

    public int getPageIdx() {
        return this.m_PageIdx;
    }

    public score getScore() {
        return this.m_Score;
    }

    public void setScore(score score) {
        this.m_Score = score;
    }

    public Bitmap getBackgroundImage() {
        return this.m_BackgroundImage;
    }

    public void setBackgroundImage(Bitmap bitmap) {
        this.m_BackgroundImage = bitmap;
    }

    public Bitmap getOverlayImage() {
        return this.m_OverlayImage;
    }

    public void setOverlayImage(Bitmap bitmap) {
        this.m_OverlayImage = bitmap;
    }

    public float getScale() {
        return this.m_Scale;
    }

    public void addSoundImage(SoundImage soundImage) {
        this.m_SoundImages.add(soundImage);
    }

    public SoundImage getSoundImage(int i) {
        return this.m_SoundImages.get(i);
    }

    public int getSoundImageCount() {
        return this.m_SoundImages.size();
    }

    public void addBarButton(BarButton barButton) {
        this.m_BarButtons.add(barButton);
    }

    public BarButton getBarButton(int i) {
        return this.m_BarButtons.get(i);
    }

    public int getBarButtonCount() {
        return this.m_BarButtons.size();
    }

    public SoundImage findSoundImage(sound sound) {
        long cPtr = sound.getCPtr(sound);
        for (int i = 0; i < this.m_SoundImages.size(); i++) {
            SoundImage soundImage = this.m_SoundImages.get(i);
            if (soundImage.key == cPtr) {
                return soundImage;
            }
        }
        return null;
    }

    public BarButton findBarButton(bar bar) {
        long cPtr = bar.getCPtr(bar);
        for (int i = 0; i < this.m_BarButtons.size(); i++) {
            BarButton barButton = this.m_BarButtons.get(i);
            if (barButton.key == cPtr) {
                return barButton;
            }
        }
        return null;
    }

    public BarButton findBarButton(int i, int i2, boolean z, int i3) {
        float f = ((float) this.m_Width) / ((float) i3);
        int i4 = (int) (((float) i) * f);
        int i5 = (int) (((float) i2) * f);
        for (int i6 = 0; i6 < this.m_BarButtons.size(); i6++) {
            BarButton barButton = this.m_BarButtons.get(i6);
            if (barButton.isGroupButton == z && i4 >= barButton.x && i4 < barButton.x + barButton.w && i5 >= barButton.y && i5 < barButton.y + barButton.h) {
                return barButton;
            }
        }
        return null;
    }
}
