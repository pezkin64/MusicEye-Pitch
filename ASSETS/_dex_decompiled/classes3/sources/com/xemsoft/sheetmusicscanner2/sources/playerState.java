package com.xemsoft.sheetmusicscanner2.sources;

public class playerState {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected playerState(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(playerState playerstate) {
        if (playerstate == null) {
            return 0;
        }
        return playerstate.swigCPtr;
    }
}
