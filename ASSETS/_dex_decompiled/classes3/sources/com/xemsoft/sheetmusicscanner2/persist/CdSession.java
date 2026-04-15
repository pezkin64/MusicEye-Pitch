package com.xemsoft.sheetmusicscanner2.persist;

import com.google.firebase.encoders.json.BuildConfig;

public class CdSession {
    public int bpm = 0;
    public String displayName = BuildConfig.FLAVOR;
    public long id = -1;
    public int instrument = 0;
    public int instrumentPitch = 0;
    public long lastEdited = 0;
    public int multipleVoicesOn = 1;
    public int order = 0;
    public PlayerSettings playerSettings;
    public String sessionFolderName = BuildConfig.FLAVOR;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int getBpm() {
        return this.bpm;
    }

    public void setBpm(int i) {
        this.bpm = i;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String str) {
        this.displayName = str;
    }

    public int getInstrument() {
        return this.instrument;
    }

    public void setInstrument(int i) {
        this.instrument = i;
    }

    public int getInstrumentPitch() {
        return this.instrumentPitch;
    }

    public void setInstrumentPitch(int i) {
        this.instrumentPitch = i;
    }

    public long getLastEdited() {
        return this.lastEdited;
    }

    public void setLastEdited(long j) {
        this.lastEdited = j;
    }

    public boolean isMultipleVoicesOn() {
        return this.multipleVoicesOn != 0;
    }

    public void setMultipleVoicesOn(boolean z) {
        int i = 1;
        if (!z) {
            i = 0;
        }
        this.multipleVoicesOn = i;
    }

    public int getOrder() {
        return this.order;
    }

    public void setOrder(int i) {
        this.order = i;
    }

    public String getSessionFolderName() {
        return this.sessionFolderName;
    }

    public void setSessionFolderName(String str) {
        this.sessionFolderName = str;
    }
}
