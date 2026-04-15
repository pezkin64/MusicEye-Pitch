package com.xemsoft.sheetmusicscanner2.persist;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.util.ArrayList;

public class PlayerSettings {
    public int bpm;
    public boolean isSwingOn;
    public MetronomeSettings metronome;
    public MixerSettings mixer;

    public PlayerSettings() {
        this.bpm = 80;
        this.isSwingOn = false;
        this.metronome = new MetronomeSettings();
        this.mixer = new MixerSettings();
    }

    public PlayerSettings(int i, boolean z, MetronomeSettings metronomeSettings, MixerSettings mixerSettings) {
        this.bpm = 80;
        this.isSwingOn = false;
        this.metronome = new MetronomeSettings();
        new MixerSettings();
        this.bpm = i;
        this.isSwingOn = z;
        this.metronome = metronomeSettings;
        this.mixer = mixerSettings;
    }

    public String toJson() {
        return new GsonBuilder().setPrettyPrinting().create().toJson((Object) this);
    }

    public boolean toJsonFile(String str) {
        return Utils.stringToFile(toJson(), str);
    }

    public static PlayerSettings fromJson(String str) {
        return (PlayerSettings) new Gson().fromJson(str, PlayerSettings.class);
    }

    public static PlayerSettings fromJsonFile(String str) {
        String fileToString = Utils.fileToString(str);
        if (fileToString == null) {
            return null;
        }
        return fromJson(fileToString);
    }

    public static PlayerSettings create(int i, int i2, int i3, int i4) {
        PlayerSettings playerSettings = new PlayerSettings();
        playerSettings.bpm = i;
        playerSettings.mixer.tracks = new ArrayList();
        for (int i5 = 0; i5 < i2; i5++) {
            playerSettings.mixer.tracks.add(new TrackSettings(i3, i4));
        }
        return playerSettings;
    }
}
