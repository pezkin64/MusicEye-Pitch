package com.xemsoft.sheetmusicscanner2.player.resource;

import android.graphics.Bitmap;

public class SoundImage {
    public Bitmap bitmap;
    public long key;
    public int x;
    public int y;

    public SoundImage(Bitmap bitmap2, int i, int i2, long j) {
        this.bitmap = bitmap2;
        this.x = i;
        this.y = i2;
        this.key = j;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public void setBitmap(Bitmap bitmap2) {
        this.bitmap = bitmap2;
    }

    public long getKey() {
        return this.key;
    }

    public void setKey(long j) {
        this.key = j;
    }
}
