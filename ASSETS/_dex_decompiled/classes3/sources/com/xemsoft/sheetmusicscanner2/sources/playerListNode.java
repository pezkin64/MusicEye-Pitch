package com.xemsoft.sheetmusicscanner2.sources;

public class playerListNode {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected playerListNode(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(playerListNode playerlistnode) {
        if (playerlistnode == null) {
            return 0;
        }
        return playerlistnode.swigCPtr;
    }

    public score getScore() {
        long playerListNode_score_get = JniSourceJNI.playerListNode_score_get(this.swigCPtr, this);
        if (playerListNode_score_get == 0) {
            return null;
        }
        return new score(playerListNode_score_get, false);
    }

    public bar getBar() {
        long playerListNode_bar_get = JniSourceJNI.playerListNode_bar_get(this.swigCPtr, this);
        if (playerListNode_bar_get == 0) {
            return null;
        }
        return new bar(playerListNode_bar_get, false);
    }
}
