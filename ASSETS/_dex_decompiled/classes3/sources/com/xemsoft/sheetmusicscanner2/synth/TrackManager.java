package com.xemsoft.sheetmusicscanner2.synth;

import android.util.Log;

public class TrackManager {
    private static final String LOGTAG = "TrackManager";
    public int trackCount;
    public TrackInfo[] tracks;

    public TrackManager(int i) {
        this.trackCount = i;
        this.tracks = new TrackInfo[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            TrackInfo trackInfo = new TrackInfo();
            int i4 = i2 + 1;
            trackInfo.channel1 = i2;
            i2 += 2;
            trackInfo.channel2 = i4;
            this.tracks[i3] = trackInfo;
        }
    }

    public int getChannel1(int i) {
        if (i < this.trackCount) {
            return this.tracks[i].channel1;
        }
        Log.e(LOGTAG, "getChannel1 - track index out of range");
        return 0;
    }

    public int getChannel2(int i) {
        if (i < this.trackCount) {
            return this.tracks[i].channel2;
        }
        Log.e(LOGTAG, "getChannel2 - track index out of range");
        return 0;
    }

    public int getProgram(int i) {
        if (i < this.trackCount) {
            return this.tracks[i].program;
        }
        Log.e(LOGTAG, "getProgram - track index out of range");
        return 0;
    }

    public void setProgram(int i, int i2) {
        if (i < this.trackCount) {
            this.tracks[i].program = i2;
        } else {
            Log.e(LOGTAG, "setProgram - track index out of range");
        }
    }

    public void setUsed(int i, boolean z) {
        if (i < this.trackCount) {
            this.tracks[i].used = z;
        } else {
            Log.e(LOGTAG, "setUsed - track index out of range");
        }
    }

    public void reset() {
        for (TrackInfo trackInfo : this.tracks) {
            trackInfo.used = false;
        }
    }

    public boolean isProgramUsedByOtherTrack(int i, int i2) {
        int i3 = 0;
        while (true) {
            TrackInfo[] trackInfoArr = this.tracks;
            if (i3 >= trackInfoArr.length) {
                return false;
            }
            if (i3 != i) {
                TrackInfo trackInfo = trackInfoArr[i];
                if (trackInfo.used && trackInfo.program == i2) {
                    return true;
                }
            }
            i3++;
        }
    }
}
