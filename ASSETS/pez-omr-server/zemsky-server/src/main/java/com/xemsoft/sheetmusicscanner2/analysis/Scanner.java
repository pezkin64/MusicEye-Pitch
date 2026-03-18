package com.xemsoft.sheetmusicscanner2.analysis;

/**
 * Scanner — passed as first arg to nativeAnalyze().
 * The .so calls reportScanProgress(int) and isScanCanceled() on this object.
 * Must live in com.xemsoft.sheetmusicscanner2.analysis — the .so resolves
 * the method IDs by this exact class name.
 */
public class Scanner {
    private volatile boolean cancelled = false;

    public void reportScanProgress(int pct) {
        System.out.println("[Zemsky] progress " + pct + "%");
    }

    public boolean isScanCanceled() {
        return cancelled;
    }

    public void cancel() { cancelled = true; }
}
