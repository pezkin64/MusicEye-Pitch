package com.xemsoft.sheetmusicscanner2.persist;

import java.util.ArrayList;
import java.util.List;

public class TrackSettings {
    public int instrumentPitch;
    public int instrumentProgram;
    public boolean isSwitchedToMultiVoice;
    public List<VoiceSettings> voices;

    public TrackSettings() {
        this.instrumentProgram = 0;
        this.instrumentPitch = 0;
        this.isSwitchedToMultiVoice = false;
        ArrayList arrayList = new ArrayList();
        this.voices = arrayList;
        arrayList.add(new VoiceSettings());
        this.voices.add(new VoiceSettings());
    }

    public TrackSettings(int i, int i2) {
        this.isSwitchedToMultiVoice = false;
        this.instrumentProgram = i;
        this.instrumentPitch = i2;
        ArrayList arrayList = new ArrayList();
        this.voices = arrayList;
        arrayList.add(new VoiceSettings());
        this.voices.add(new VoiceSettings());
    }

    public TrackSettings(int i, int i2, boolean z, List<VoiceSettings> list) {
        this.instrumentProgram = i;
        this.instrumentPitch = i2;
        this.isSwitchedToMultiVoice = z;
        this.voices = list;
    }

    public boolean areAllVoicesMuted() {
        if (this.voices.size() == 0) {
            return true;
        }
        if (!this.isSwitchedToMultiVoice) {
            return this.voices.get(0).isMuted;
        }
        for (VoiceSettings voiceSettings : this.voices) {
            if (!voiceSettings.isMuted) {
                return false;
            }
        }
        return true;
    }

    public boolean isAudible() {
        if (this.voices.size() == 0) {
            return false;
        }
        if (!this.isSwitchedToMultiVoice) {
            return this.voices.get(0).isAudible();
        }
        for (VoiceSettings isAudible : this.voices) {
            if (isAudible.isAudible()) {
                return true;
            }
        }
        return false;
    }

    public VoiceSettings getVoiceSettingsForVoiceIndex(int i) {
        VoiceSettings voiceSettings = (VoiceSettings) ((!this.isSwitchedToMultiVoice || i <= 0) ? this.voices.get(0) : this.voices.get(1));
        return voiceSettings == null ? this.voices.get(0) : voiceSettings;
    }
}
