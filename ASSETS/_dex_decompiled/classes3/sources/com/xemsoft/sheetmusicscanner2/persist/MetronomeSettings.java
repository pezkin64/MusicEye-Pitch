package com.xemsoft.sheetmusicscanner2.persist;

public class MetronomeSettings {
    public boolean doHighlightFirst;
    public boolean enableCountIn;
    public boolean isEnabled;
    public float volume;

    public MetronomeSettings() {
        this.isEnabled = false;
        this.volume = 1.0f;
        this.doHighlightFirst = false;
        this.enableCountIn = false;
    }

    public MetronomeSettings(boolean z, float f, boolean z2, boolean z3) {
        this.isEnabled = z;
        this.volume = f;
        this.doHighlightFirst = z2;
        this.enableCountIn = z3;
    }
}
