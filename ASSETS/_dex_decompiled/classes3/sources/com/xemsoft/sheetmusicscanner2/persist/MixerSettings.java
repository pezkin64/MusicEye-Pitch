package com.xemsoft.sheetmusicscanner2.persist;

import java.util.ArrayList;
import java.util.List;

public class MixerSettings {
    public List<TrackSettings> tracks;

    public MixerSettings() {
        ArrayList arrayList = new ArrayList();
        this.tracks = arrayList;
        arrayList.add(new TrackSettings());
    }

    public MixerSettings(List<TrackSettings> list) {
        this.tracks = list;
    }

    public boolean isAudibleTrack(int i, int i2) {
        return this.tracks.get(i).getVoiceSettingsForVoiceIndex(i2).isAudible();
    }
}
