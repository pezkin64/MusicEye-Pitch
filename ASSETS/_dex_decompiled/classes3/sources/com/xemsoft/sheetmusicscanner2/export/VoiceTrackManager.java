package com.xemsoft.sheetmusicscanner2.export;

import android.content.Context;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.persist.PlayerSettings;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.persist.VoiceSettings;
import com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.MidiUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.MetaMessage;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.Sequence;
import jp.kshoji.javax.sound.midi.Track;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class VoiceTrackManager {
    private static final String LOGTAG = "VoiceTrackManager";
    private static final int MAX_MIDI_CHANNELS = 16;
    private final int MAX_NAME_LEN = WorkQueueKt.MASK;
    private VoiceTrackInfo[] m_InfoList;
    private int m_MetronomeChannel = -1;
    private Track m_MetronomeTrack;
    private Track m_TempoTrack;
    private int m_TrackIndex = 0;

    public static int getGmInstrumentProgram(int i) {
        if (i == 124 || i == 127) {
            return 0;
        }
        return i;
    }

    public VoiceTrackManager(Context context, PlayerSettings playerSettings, session session, Sequence sequence, int i, double d, boolean z, boolean z2) {
        int i2;
        int i3 = i;
        InstrumentsUtility instance = InstrumentsUtility.getInstance(context);
        int size = playerSettings.mixer.tracks.size();
        this.m_InfoList = new VoiceTrackInfo[size];
        this.m_TempoTrack = sequence.createTrack();
        if (playerSettings.metronome.isEnabled || playerSettings.metronome.enableCountIn) {
            this.m_MetronomeTrack = sequence.createTrack();
            this.m_MetronomeChannel = 0;
            this.m_MetronomeTrack.add(MidiUtils.getProgramChangeEvent(0, 115, 0));
            i2 = 1;
        } else {
            i2 = 0;
        }
        for (int i4 = 0; i4 < size; i4++) {
            TrackSettings trackSettings = playerSettings.mixer.tracks.get(i4);
            VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
            VoiceTrackInfo voiceTrackInfo = new VoiceTrackInfo();
            voiceTrackInfoArr[i4] = voiceTrackInfo;
            if ((i3 == -1 || i3 == i4) && trackSettings.isAudible()) {
                voiceTrackInfo.active = true;
                VoiceSettings voiceSettings = trackSettings.voices.get(0);
                VoiceSettings voiceSettings2 = trackSettings.voices.get(1);
                if (trackSettings.isSwitchedToMultiVoice) {
                    voiceTrackInfo.split = true;
                    if (voiceSettings.isAudible()) {
                        voiceTrackInfo.m_Track1 = sequence.createTrack();
                        voiceTrackInfo.m_Channel1 = i2;
                        voiceTrackInfo.m_Volume1 = voiceSettings.volume;
                        i2++;
                    }
                    if (voiceSettings2.isAudible()) {
                        voiceTrackInfo.m_Track2 = sequence.createTrack();
                        voiceTrackInfo.m_Channel2 = i2;
                        voiceTrackInfo.m_Volume2 = voiceSettings2.volume;
                        i2++;
                    }
                } else {
                    voiceTrackInfo.split = false;
                    if (voiceSettings.isAudible() || voiceSettings2.isAudible()) {
                        Track createTrack = sequence.createTrack();
                        voiceTrackInfo.m_Track2 = createTrack;
                        voiceTrackInfo.m_Track1 = createTrack;
                        int i5 = i2 + 1;
                        voiceTrackInfo.m_Channel2 = i2;
                        voiceTrackInfo.m_Channel1 = i2;
                        float f = voiceSettings.volume;
                        voiceTrackInfo.m_Volume2 = f;
                        voiceTrackInfo.m_Volume1 = f;
                        i2 = i5;
                    }
                }
                voiceTrackInfo.m_InstrumentPitch = trackSettings.instrumentPitch;
                voiceTrackInfo.m_InstrumentProgram = trackSettings.instrumentProgram;
                voiceTrackInfo.m_InstrumentName = instance.findInstrumentByProgram(trackSettings.instrumentProgram).getName();
                voiceTrackInfo.m_GmInstrumentProgram = getGmInstrumentProgram(trackSettings.instrumentProgram);
                voiceTrackInfo.m_GmInstrumentName = instance.findInstrumentByProgram(voiceTrackInfo.m_GmInstrumentProgram).getName();
            }
        }
        if (z2) {
            restrictChannels(i2);
        }
        prepareTracks(d, z);
        listChannelPrograms();
    }

    private void listChannelPrograms() {
        Log.d(LOGTAG, "*** Channel Programs Start ***");
        int i = 0;
        while (true) {
            VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
            if (i < voiceTrackInfoArr.length) {
                VoiceTrackInfo voiceTrackInfo = voiceTrackInfoArr[i];
                String str = LOGTAG;
                Log.d(str, "- Voice=" + i + " active=" + voiceTrackInfo.active);
                if (voiceTrackInfo.active) {
                    Log.d(str, "--- split=" + voiceTrackInfo.split + " program=" + voiceTrackInfo.m_InstrumentProgram + " " + voiceTrackInfo.m_InstrumentName);
                    StringBuilder sb = new StringBuilder("--- channel1=");
                    sb.append(voiceTrackInfo.m_Channel1);
                    sb.append(" channel2=");
                    sb.append(voiceTrackInfo.m_Channel2);
                    Log.d(str, sb.toString());
                }
                i++;
            } else {
                Log.d(LOGTAG, "*** Channel Programs End ***");
                return;
            }
        }
    }

    private void restrictChannels(int i) {
        if (i > 16) {
            int i2 = -1;
            for (VoiceTrackInfo voiceTrackInfo : this.m_InfoList) {
                if (voiceTrackInfo.active && (voiceTrackInfo.m_Channel1 >= 15 || voiceTrackInfo.m_Channel2 >= 15)) {
                    if (i2 == -1) {
                        i2 = voiceTrackInfo.m_InstrumentProgram;
                    } else {
                        voiceTrackInfo.m_InstrumentProgram = i2;
                    }
                    voiceTrackInfo.m_Channel2 = 15;
                    voiceTrackInfo.m_Channel1 = 15;
                    voiceTrackInfo.m_Volume2 = 1.0f;
                    voiceTrackInfo.m_Volume1 = 1.0f;
                }
            }
        }
    }

    private void prepareTracks(double d, boolean z) {
        int hzToCents = MidiUtils.hzToCents(d);
        for (VoiceTrackInfo voiceTrackInfo : this.m_InfoList) {
            if (voiceTrackInfo.active) {
                int i = z ? voiceTrackInfo.m_InstrumentProgram : voiceTrackInfo.m_GmInstrumentProgram;
                if (voiceTrackInfo.m_Track1 != null) {
                    addTrackName(voiceTrackInfo.m_Track1, voiceTrackInfo.m_InstrumentName);
                    voiceTrackInfo.m_Track1.add(MidiUtils.getProgramChangeEvent(voiceTrackInfo.m_Channel1, i, 0));
                    voiceTrackInfo.m_Track1.add(MidiUtils.getVolumeEvent(voiceTrackInfo.m_Channel1, voiceTrackInfo.m_Volume1, 0));
                    setTrackCents(voiceTrackInfo.m_Track1, voiceTrackInfo.m_Channel1, hzToCents, z);
                }
                if (voiceTrackInfo.split && voiceTrackInfo.m_Track2 != null) {
                    addTrackName(voiceTrackInfo.m_Track2, voiceTrackInfo.m_InstrumentName);
                    voiceTrackInfo.m_Track2.add(MidiUtils.getProgramChangeEvent(voiceTrackInfo.m_Channel2, i, 0));
                    voiceTrackInfo.m_Track2.add(MidiUtils.getVolumeEvent(voiceTrackInfo.m_Channel2, voiceTrackInfo.m_Volume2, 0));
                    setTrackCents(voiceTrackInfo.m_Track2, voiceTrackInfo.m_Channel2, hzToCents, z);
                }
            }
        }
    }

    private void setTrackCents(Track track, int i, int i2, boolean z) {
        MidiEvent[] midiEventArr;
        if (z) {
            midiEventArr = MidiUtils.getTuneEventsFix(i, i2, 0);
        } else {
            midiEventArr = MidiUtils.getTuneEvents(i, i2, 0);
        }
        if (midiEventArr != null) {
            for (MidiEvent add : midiEventArr) {
                track.add(add);
            }
        }
    }

    private void addTrackName(Track track, String str) {
        MetaMessage metaMessage;
        if (str.length() > 127) {
            str = str.substring(0, WorkQueueKt.MASK);
        }
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        try {
            metaMessage = new MetaMessage(3, bytes, bytes.length);
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "addTrackName()", e);
            metaMessage = null;
        }
        if (metaMessage != null) {
            track.add(new MidiEvent(metaMessage, 0));
        }
    }

    public Track getTempoTrack() {
        return this.m_TempoTrack;
    }

    public Track getVoiceTrack1(int i) {
        VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
        if (i < voiceTrackInfoArr.length) {
            return voiceTrackInfoArr[i].m_Track1;
        }
        return null;
    }

    public Track getVoiceTrack2(int i) {
        VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
        if (i < voiceTrackInfoArr.length) {
            return voiceTrackInfoArr[i].m_Track2;
        }
        return null;
    }

    public int getVoiceChannel1(int i) {
        VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
        return (i < voiceTrackInfoArr.length ? Integer.valueOf(voiceTrackInfoArr[i].m_Channel1) : null).intValue();
    }

    public int getVoiceChannel2(int i) {
        VoiceTrackInfo[] voiceTrackInfoArr = this.m_InfoList;
        return (i < voiceTrackInfoArr.length ? Integer.valueOf(voiceTrackInfoArr[i].m_Channel2) : null).intValue();
    }

    public Track getMetronomeTrack() {
        return this.m_MetronomeTrack;
    }

    public int getMetronomeChannel() {
        return this.m_MetronomeChannel;
    }

    public List<Integer> getUsedPrograms() {
        ArrayList arrayList = new ArrayList();
        for (VoiceTrackInfo voiceTrackInfo : this.m_InfoList) {
            Integer valueOf = Integer.valueOf(voiceTrackInfo.m_InstrumentProgram);
            if (!arrayList.contains(valueOf)) {
                arrayList.add(valueOf);
            }
        }
        if (this.m_MetronomeChannel != -1 && !arrayList.contains(115)) {
            arrayList.add(115);
        }
        return arrayList;
    }
}
