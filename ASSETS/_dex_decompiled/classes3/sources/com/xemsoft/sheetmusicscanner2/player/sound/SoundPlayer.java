package com.xemsoft.sheetmusicscanner2.player.sound;

import android.content.Context;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Numa;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_int;
import com.xemsoft.sheetmusicscanner2.persist.PlayerSettings;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_NUMA;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.baraa;
import com.xemsoft.sheetmusicscanner2.sources.playerListNode;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import com.xemsoft.sheetmusicscanner2.sources.timepoint;
import com.xemsoft.sheetmusicscanner2.synth.Synth;
import com.xemsoft.sheetmusicscanner2.util.OnDoneListener;
import com.xemsoft.sheetmusicscanner2.util.Swig;
import com.xemsoft.sheetmusicscanner2.util.TargetRequest;
import com.xemsoft.sheetmusicscanner2.util.TargetRequestParams;
import java.util.ArrayList;

public class SoundPlayer {
    private static final String LOGTAG = "SoundPlayer.java";
    private static final int METRONOME_MIDI_PROGRAM = 115;
    private static final int REQ_BAR_SELECT = 6;
    private static final int REQ_COUNT_IN_TICK = 8;
    private static final int REQ_FINISHED = 4;
    private static final int REQ_METRONOME_TICK = 7;
    private static final int REQ_PLAY = 1;
    private static final int REQ_REMOVE_HIGH = 5;
    private static final int REQ_SCHEDULE_BAR = 9;
    private static final int REQ_SCHEDULE_NEXT_BAR = 3;
    private static final int REQ_STOP = 2;
    private static SoundPlayer m_Instance;
    private boolean m_IsGroup = true;
    private boolean m_IsPlaying = false;
    /* access modifiers changed from: private */
    public SoundPlayerListener m_Listener = null;
    private TargetRequest m_Request;
    private TargetRequest.OnTargetRequestListener m_RequestListener = new TargetRequest.OnTargetRequestListener() {
        public void onRequest(TargetRequestParams targetRequestParams) {
            switch (targetRequestParams.m_Type) {
                case 1:
                    PlayParams playParams = (PlayParams) targetRequestParams;
                    SoundPlayer.this.playTimePoint(playParams.m_Score, playParams.m_Bar, playParams.m_Timepoint, playParams.m_Gain);
                    return;
                case 2:
                    SoundPlayer.this.stopNote(targetRequestParams.m_Key2, targetRequestParams.m_Key);
                    return;
                case 3:
                    ScheduleBarParams scheduleBarParams = (ScheduleBarParams) targetRequestParams;
                    playerListNode sessionStateMoveToNextBar = JniSource.sessionStateMoveToNextBar(SoundPlayer.this.m_Session);
                    if (sessionStateMoveToNextBar != null) {
                        SoundPlayer.this.scheduleBarForPlaying(sessionStateMoveToNextBar.getScore(), sessionStateMoveToNextBar.getBar());
                        return;
                    }
                    if (SoundPlayer.this.m_Listener != null) {
                        SoundPlayer.this.m_Listener.setBarButtonSelected(scheduleBarParams.m_Score, scheduleBarParams.m_Bar, true);
                    }
                    SoundPlayer.this.playingFinished();
                    return;
                case 4:
                    SoundPlayer.this.playingFinished();
                    return;
                case 5:
                    RemoveHighlightingParams removeHighlightingParams = (RemoveHighlightingParams) targetRequestParams;
                    if (SoundPlayer.this.m_Listener != null) {
                        SoundPlayer.this.m_Listener.setSoundVisibility(removeHighlightingParams.m_Score, removeHighlightingParams.m_Sound, true);
                        return;
                    }
                    return;
                case 6:
                    BarSelectParams barSelectParams = (BarSelectParams) targetRequestParams;
                    if (SoundPlayer.this.m_Listener != null) {
                        SoundPlayer.this.m_Listener.setBarButtonSelected(barSelectParams.m_Score, barSelectParams.m_Bar, barSelectParams.m_IsSelected);
                        return;
                    }
                    return;
                case 7:
                    SoundPlayer.this.playMetronomeTick();
                    return;
                case 8:
                    SoundPlayer.this.playCountInTick();
                    return;
                case 9:
                    ScheduleBarParams scheduleBarParams2 = (ScheduleBarParams) targetRequestParams;
                    SoundPlayer.this.scheduleBarForPlaying(scheduleBarParams2.m_Score, scheduleBarParams2.m_Bar);
                    return;
                default:
                    return;
            }
        }
    };
    /* access modifiers changed from: private */
    public session m_Session = null;
    private PlayerSettings m_Settings;
    private Synth m_Synth;

    public interface SoundPlayerListener {
        void playingFinished();

        void setBarButtonSelected(score score, bar bar, boolean z);

        void setSoundVisibility(score score, sound sound, boolean z);
    }

    public static float gainChecked(float f, int i) {
        if (i == 52 || i == 115 || i == 120 || i == 121) {
            return 1.0f;
        }
        return f;
    }

    private class PlayParams extends TargetRequestParams {
        public bar m_Bar;
        public float m_Gain;
        public score m_Score;
        public timepoint m_Timepoint;

        private PlayParams() {
        }
    }

    private class ScheduleBarParams extends TargetRequestParams {
        public bar m_Bar;
        public score m_Score;

        private ScheduleBarParams() {
        }
    }

    private class RemoveHighlightingParams extends TargetRequestParams {
        public score m_Score;
        public sound m_Sound;

        private RemoveHighlightingParams() {
        }
    }

    private class BarSelectParams extends TargetRequestParams {
        public bar m_Bar;
        boolean m_IsSelected;
        public score m_Score;

        private BarSelectParams() {
        }
    }

    private static class PlayInfo {
        int channel;
        float gain;
        int note;
        double soundDuration;

        public PlayInfo(int i, int i2, float f, double d) {
            this.channel = i;
            this.note = i2;
            this.gain = f;
            this.soundDuration = d;
        }
    }

    private static class DisplayInfo {
        sound _sound;
        double displayDuration;

        public DisplayInfo(sound sound, double d) {
            this._sound = sound;
            this.displayDuration = d;
        }
    }

    public SoundPlayer(Context context) {
        this.m_Synth = Synth.getInstance(context);
        TargetRequest targetRequest = new TargetRequest();
        this.m_Request = targetRequest;
        targetRequest.setListener(this.m_RequestListener);
    }

    public static SoundPlayer getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new SoundPlayer(context);
        }
        return m_Instance;
    }

    public void setListener(SoundPlayerListener soundPlayerListener) {
        this.m_Listener = soundPlayerListener;
    }

    public boolean isPlaying() {
        return this.m_IsPlaying;
    }

    public void setPitch(double d) {
        this.m_Synth.setPitchHz(d);
    }

    public void setBeatsPerMinute(int i) {
        synchronized (this.m_Settings) {
            this.m_Settings.bpm = i;
        }
    }

    public void setIsGroup(boolean z) {
        this.m_IsGroup = z;
    }

    public void openSynth() {
        this.m_Synth.open();
    }

    public void closeSynth() {
        this.m_Synth.close();
    }

    public void setup(PlayerSettings playerSettings) {
        this.m_Settings = playerSettings;
        this.m_Synth.setupTracksAsync(playerSettings.mixer.tracks, this.m_Settings.metronome, (OnDoneListener) null);
    }

    public void setTrackProgram(int i, int i2) {
        this.m_Synth.setTrackProgram(i, i2);
    }

    public void setVoice1Volume(int i, float f) {
        this.m_Synth.setVoice1Volume(i, f);
    }

    public void setVoice2Volume(int i, float f) {
        this.m_Synth.setVoice2Volume(i, f);
    }

    public void setMetronomeVolume(float f) {
        this.m_Synth.setMetronomeVolume(f);
    }

    public void setUpWithSession(session session, int i, boolean z) {
        session session2 = this.m_Session;
        if (session2 != null) {
            Swig.sessionDestroy(session2);
        }
        if (session != null) {
            this.m_Session = session;
            this.m_IsGroup = z;
        }
    }

    public void releaseSession() {
        session session = this.m_Session;
        if (session != null) {
            Swig.sessionDestroy(session);
            this.m_Session = null;
        }
    }

    public void playFromScore(score score, int i, int i2) {
        baraa groupBaraa = this.m_IsGroup ? score.getGroupBaraa() : score.getSingleBaraa();
        stop();
        int baraaGetBarCount = JniSource.baraaGetBarCount(groupBaraa, i);
        if (i2 >= 0 && i2 < baraaGetBarCount) {
            bar baraaGetBar = JniSource.baraaGetBar(groupBaraa, i, i2);
            if (JniSource.sessionStateSetCurrentBar(this.m_Session, score, baraaGetBar, this.m_IsGroup ? -1 : JniSource.scoreGetVoiceIndex(score, i)) == 0) {
                if (!this.m_Settings.metronome.enableCountIn) {
                    scheduleBarForPlaying(score, baraaGetBar);
                    this.m_IsPlaying = true;
                    return;
                }
                SWIGTYPE_p_int new_lintp = JniSource.new_lintp();
                Numa numa = new Numa(SWIGTYPE_p_NUMA.getCPtr(JniSource.sessionCreateMetronomeCountInIntervalsForBar(this.m_Session, baraaGetBar)), false);
                double d = 0.0d;
                for (int i3 = 0; i3 < numa.getN(); i3++) {
                    JniLeptonica.numaGetIValue(numa, i3, new_lintp);
                    double timePointToSeconds = JniSource.timePointToSeconds((double) ((float) JniSource.lintp_value(new_lintp)), this.m_Settings.bpm, this.m_Settings.isSwingOn);
                    if (i3 < numa.getN() - 1) {
                        playCountInTickDelayed(timePointToSeconds);
                    } else {
                        d = timePointToSeconds;
                    }
                }
                JniSource.delete_lintp(new_lintp);
                numa.finalize();
                SoundPlayerListener soundPlayerListener = this.m_Listener;
                if (soundPlayerListener != null) {
                    soundPlayerListener.setBarButtonSelected(score, baraaGetBar, true);
                }
                scheduleBarDelayed(score, baraaGetBar, d);
                this.m_IsPlaying = true;
            }
        }
    }

    public void stop() {
        this.m_Request.cancelAll();
        this.m_Synth.allNotesOff();
        this.m_IsPlaying = false;
    }

    /* access modifiers changed from: private */
    public void scheduleBarForPlaying(score score, bar bar) {
        SoundPlayerListener soundPlayerListener = this.m_Listener;
        if (soundPlayerListener != null) {
            soundPlayerListener.setBarButtonSelected(score, bar, true);
        }
        SWIGTYPE_p_int new_lintp = JniSource.new_lintp();
        Numa numa = new Numa(SWIGTYPE_p_NUMA.getCPtr(JniSource.sessionCreateMetronomeIntervalsForBar(this.m_Session, bar)), false);
        for (int i = 0; i < numa.getN(); i++) {
            JniLeptonica.numaGetIValue(numa, i, new_lintp);
            playMetronomeTickDelayed(JniSource.timePointToSeconds((double) ((float) JniSource.lintp_value(new_lintp)), this.m_Settings.bpm, this.m_Settings.isSwingOn));
        }
        JniSource.delete_lintp(new_lintp);
        numa.finalize();
        double offset = bar.getOffset();
        int i2 = 0;
        while (i2 < bar.getN()) {
            timepoint barGetTP = JniSource.barGetTP(bar, i2);
            playTimePointDelayed(score, bar, barGetTP, i2 == 0 ? 0.7f : 0.63f, JniSource.timePointToSeconds(barGetTP.getStartTime() - offset, this.m_Settings.bpm, this.m_Settings.isSwingOn));
            i2++;
        }
        double timePointToSeconds = JniSource.timePointToSeconds(bar.getLength(), this.m_Settings.bpm, this.m_Settings.isSwingOn);
        barSelectDelayed(score, bar, false, timePointToSeconds);
        scheduleNextBarDelayed(score, bar, timePointToSeconds);
    }

    /* access modifiers changed from: private */
    public void playTimePoint(score score, bar bar, timepoint timepoint, float f) {
        score score2 = score;
        timepoint timepoint2 = timepoint;
        ArrayList<PlayInfo> arrayList = new ArrayList<>();
        ArrayList<DisplayInfo> arrayList2 = new ArrayList<>();
        for (int i = 0; i < timepoint2.getN(); i++) {
            sound tpGetSound = JniSource.tpGetSound(timepoint2, i);
            int voiceIndex = tpGetSound.getVoiceIndex();
            int voiceSubindexSplit = tpGetSound.getVoiceSubindexSplit();
            boolean z = true;
            if (voiceSubindexSplit > 1) {
                voiceSubindexSplit = 1;
            }
            TrackSettings trackSettings = this.m_Settings.mixer.tracks.get(voiceIndex);
            if (!trackSettings.isSwitchedToMultiVoice) {
                voiceSubindexSplit = 0;
            }
            if (!trackSettings.voices.get(voiceSubindexSplit).isMuted) {
                arrayList2.add(new DisplayInfo(tpGetSound, JniSource.durationToSecondsWithStartTime(timepoint2.getStartTime() - bar.getOffset(), tpGetSound.getDisplayLength(), this.m_Settings.bpm, this.m_Settings.isSwingOn)));
                int i2 = 0;
                while (true) {
                    if (i2 >= timepoint2.getN()) {
                        z = false;
                        break;
                    }
                    sound tpGetSound2 = JniSource.tpGetSound(timepoint2, i2);
                    if (tpGetSound2.getVoiceIndex() == voiceIndex && tpGetSound2.getPitch() == tpGetSound.getPitch() && tpGetSound2.getLength() > tpGetSound.getLength()) {
                        break;
                    }
                    i2++;
                }
                if (tpGetSound.getIsRest() == 0 && tpGetSound.getIsTiedWithPrevious() == 0 && this.m_Settings.mixer.isAudibleTrack(tpGetSound.getVoiceIndex(), tpGetSound.getVoiceSubindexSplit()) && !z) {
                    int pitchToMidi = JniSource.pitchToMidi(tpGetSound.getPitch()) + trackSettings.instrumentPitch;
                    this.m_Request.cancelTypeKeys(2, pitchToMidi, this.m_Synth.getTrackChannel(voiceIndex, voiceSubindexSplit));
                    arrayList.add(new PlayInfo(this.m_Synth.getTrackChannel(voiceIndex, voiceSubindexSplit), pitchToMidi, gainChecked(f, trackSettings.instrumentProgram), JniSource.durationToSecondsWithStartTime(timepoint2.getStartTime() - bar.getOffset(), tpGetSound.getLength(), this.m_Settings.bpm, this.m_Settings.isSwingOn)));
                }
            }
            float f2 = f;
        }
        for (PlayInfo playInfo : arrayList) {
            playNote(playInfo.channel, playInfo.note, playInfo.gain);
        }
        for (PlayInfo playInfo2 : arrayList) {
            stopSoundDelayed(playInfo2.channel, playInfo2.note, playInfo2.soundDuration);
        }
        for (DisplayInfo displayInfo : arrayList2) {
            SoundPlayerListener soundPlayerListener = this.m_Listener;
            if (soundPlayerListener != null) {
                soundPlayerListener.setSoundVisibility(score2, displayInfo._sound, false);
            }
            removeHighlightingDelayed(score2, displayInfo._sound, displayInfo.displayDuration);
        }
    }

    private void playNote(int i, int i2, float f) {
        this.m_Synth.playNote(i, i2, f);
    }

    /* access modifiers changed from: private */
    public void stopNote(int i, int i2) {
        this.m_Synth.stopNote(i, i2);
    }

    /* access modifiers changed from: private */
    public void playMetronomeTick() {
        if (this.m_Settings.metronome.isEnabled) {
            this.m_Synth.playMetronomeTickWithGain(gainChecked(0.63f, 115));
        }
    }

    /* access modifiers changed from: private */
    public void playCountInTick() {
        this.m_Synth.playMetronomeTickWithGain(gainChecked(0.63f, 115));
    }

    /* access modifiers changed from: private */
    public void playingFinished() {
        this.m_IsPlaying = false;
        SoundPlayerListener soundPlayerListener = this.m_Listener;
        if (soundPlayerListener != null) {
            soundPlayerListener.playingFinished();
        }
    }

    private void playTimePointDelayed(score score, bar bar, timepoint timepoint, float f, double d) {
        PlayParams playParams = new PlayParams();
        playParams.m_Type = 1;
        playParams.m_Score = score;
        playParams.m_Bar = bar;
        playParams.m_Timepoint = timepoint;
        playParams.m_Gain = f;
        this.m_Request.requestDelayed(playParams, d);
    }

    private void stopSoundDelayed(int i, int i2, double d) {
        TargetRequestParams targetRequestParams = new TargetRequestParams();
        targetRequestParams.m_Type = 2;
        targetRequestParams.m_Key = i2;
        targetRequestParams.m_Key2 = i;
        this.m_Request.requestDelayed(targetRequestParams, d);
    }

    private void scheduleNextBarDelayed(score score, bar bar, double d) {
        ScheduleBarParams scheduleBarParams = new ScheduleBarParams();
        scheduleBarParams.m_Type = 3;
        scheduleBarParams.m_Score = score;
        scheduleBarParams.m_Bar = bar;
        this.m_Request.requestDelayed(scheduleBarParams, d);
    }

    private void scheduleBarDelayed(score score, bar bar, double d) {
        ScheduleBarParams scheduleBarParams = new ScheduleBarParams();
        scheduleBarParams.m_Type = 9;
        scheduleBarParams.m_Score = score;
        scheduleBarParams.m_Bar = bar;
        this.m_Request.requestDelayed(scheduleBarParams, d);
    }

    private void playingFinishedDelayed(double d) {
        TargetRequestParams targetRequestParams = new TargetRequestParams();
        targetRequestParams.m_Type = 4;
        this.m_Request.requestDelayed(targetRequestParams, d);
    }

    private void removeHighlightingDelayed(score score, sound sound, double d) {
        RemoveHighlightingParams removeHighlightingParams = new RemoveHighlightingParams();
        removeHighlightingParams.m_Type = 5;
        removeHighlightingParams.m_Score = score;
        removeHighlightingParams.m_Sound = sound;
        this.m_Request.requestDelayed(removeHighlightingParams, d);
    }

    private void barSelectDelayed(score score, bar bar, boolean z, double d) {
        BarSelectParams barSelectParams = new BarSelectParams();
        barSelectParams.m_Type = 6;
        barSelectParams.m_Score = score;
        barSelectParams.m_Bar = bar;
        barSelectParams.m_IsSelected = z;
        this.m_Request.requestDelayed(barSelectParams, d);
    }

    private void playMetronomeTickDelayed(double d) {
        TargetRequestParams targetRequestParams = new TargetRequestParams();
        targetRequestParams.m_Type = 7;
        this.m_Request.requestDelayed(targetRequestParams, d);
    }

    private void playCountInTickDelayed(double d) {
        TargetRequestParams targetRequestParams = new TargetRequestParams();
        targetRequestParams.m_Type = 8;
        this.m_Request.requestDelayed(targetRequestParams, d);
    }
}
