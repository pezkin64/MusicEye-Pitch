package com.xemsoft.sheetmusicscanner2.widget;

import android.graphics.Color;

public class ARGB {
    private int alpha;
    private int blue;
    private int green;
    private int red;

    public ARGB(int i) {
        this.alpha = Color.alpha(i);
        this.red = Color.red(i);
        this.green = Color.green(i);
        this.blue = Color.blue(i);
    }

    public int normal() {
        return Color.argb(this.alpha, this.red, this.green, this.blue);
    }

    public int tinted(int i) {
        ARGB argb = new ARGB(i);
        int i2 = this.alpha;
        int i3 = 255;
        int i4 = argb.alpha;
        int i5 = ((argb.red * i2) / 255) + (((255 - i4) * this.red) / 255);
        int i6 = ((argb.blue * i2) / 255) + (((255 - i4) * this.blue) / 255);
        int i7 = ((argb.green * i2) / 255) + (((255 - i4) * this.green) / 255);
        if (i5 > 255) {
            i5 = 255;
        }
        if (i6 > 255) {
            i6 = 255;
        }
        if (i7 <= 255) {
            i3 = i7;
        }
        return Color.argb(i2, i5, i3, i6);
    }
}
