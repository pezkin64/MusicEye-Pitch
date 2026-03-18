package com.xemsoft.sheetmusicscanner2.analysis;

public class Scanner {
    private volatile boolean cancelled = false;

    public void reportScanProgress(int pct) {
        android.util.Log.d("ZemskyHarness", "progress=" + pct);
    }

    public boolean isScanCanceled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}
