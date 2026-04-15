package com.xemsoft.sheetmusicscanner2.util;

import java.util.Timer;

public class TargetRequestTimer extends Timer {
    private boolean m_IsDone = false;
    private TargetRequestParams m_Params;

    public TargetRequestTimer(TargetRequestParams targetRequestParams) {
        this.m_Params = targetRequestParams;
    }

    public TargetRequestParams getParams() {
        return this.m_Params;
    }

    public void setDone() {
        this.m_IsDone = true;
    }

    public boolean isDone() {
        return this.m_IsDone;
    }
}
