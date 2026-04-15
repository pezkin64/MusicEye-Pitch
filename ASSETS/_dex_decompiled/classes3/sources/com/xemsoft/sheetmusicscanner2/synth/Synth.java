package com.xemsoft.sheetmusicscanner2.synth;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import cn.sherlock.com.sun.media.sound.SF2Soundbank;
import cn.sherlock.com.sun.media.sound.SoftShortMessage;
import cn.sherlock.com.sun.media.sound.SoftSynthesizer;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.persist.MetronomeSettings;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.persist.VoiceSettings;
import com.xemsoft.sheetmusicscanner2.util.MidiUtils;
import com.xemsoft.sheetmusicscanner2.util.OnDoneListener;
import java.io.IOException;
import java.util.List;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.MidiChannel;
import jp.kshoji.javax.sound.midi.MidiUnavailableException;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Receiver;
import jp.kshoji.javax.sound.midi.ShortMessage;

public class Synth {
    public static final int ERROR = -1;
    public static final int ERROR_INSTRUMENT = -3;
    public static final int ERROR_SOUNDBANK = -2;
    private static final String LOGTAG = "Synth.java";
    public static final int METRONOME_CHANNEL = 40;
    public static final int METRONOME_MIDI_PITCH = 63;
    public static final int METRONOME_MIDI_PROGRAM = 115;
    public static final int SUCCESS = 0;
    public static final int TRACK_COUNT = 20;
    private static Synth m_Instance;
    private Context m_Context;
    private Object m_MidiLock = new Object();
    private int m_PitchCents = 0;
    private Receiver m_Receiver = null;
    private SoftSynthesizer m_SoftSynth = null;
    private SF2Soundbank m_SoundFont = null;
    private TrackManager m_TrackManager = new TrackManager(20);
    private int m_TransCents = 0;

    public int getMetronomeChannel() {
        return 40;
    }

    public Synth(Context context) {
        this.m_Context = context;
    }

    public static Synth getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new Synth(context);
        }
        return m_Instance;
    }

    public boolean load() {
        boolean z;
        try {
            this.m_SoundFont = new SF2Soundbank(this.m_Context.getAssets().open(Constants.SOUNDFONT_FILE));
            z = true;
        } catch (IOException e) {
            Log.w(LOGTAG, "open()", e);
            z = false;
        }
        if (this.m_SoundFont == null) {
            return false;
        }
        return z;
    }

    public boolean isLoaded() {
        return this.m_SoundFont != null;
    }

    public void setSoftSynth(SoftSynthesizer softSynthesizer) {
        Receiver receiver;
        this.m_SoftSynth = softSynthesizer;
        if (softSynthesizer == null) {
            receiver = null;
        } else {
            try {
                receiver = softSynthesizer.getReceiver();
            } catch (MidiUnavailableException e) {
                Log.w(LOGTAG, "setSoftSynth()", e);
                return;
            }
        }
        this.m_Receiver = receiver;
    }

    public SoftSynthesizer getSoftSynth() {
        return this.m_SoftSynth;
    }

    public void openAsync(OnDoneListener onDoneListener) {
        new OpenTask(onDoneListener).execute(new Void[0]);
    }

    public boolean open() {
        try {
            SoftSynthesizer softSynthesizer = new SoftSynthesizer();
            this.m_SoftSynth = softSynthesizer;
            softSynthesizer.open();
            this.m_Receiver = this.m_SoftSynth.getReceiver();
            return true;
        } catch (MidiUnavailableException e) {
            Log.w(LOGTAG, "open()", e);
            return false;
        }
    }

    public void close() {
        SoftSynthesizer softSynthesizer = this.m_SoftSynth;
        if (softSynthesizer != null) {
            softSynthesizer.close();
            this.m_SoftSynth = null;
            this.m_Receiver = null;
        }
    }

    public int getTrackChannel1(int i) {
        return this.m_TrackManager.getChannel1(i);
    }

    public int getTrackChannel2(int i) {
        return this.m_TrackManager.getChannel2(i);
    }

    public int getTrackChannel(int i, int i2) {
        if (i2 > 1) {
            i2 = 1;
        }
        if (i2 == 0) {
            return this.m_TrackManager.getChannel1(i);
        }
        return this.m_TrackManager.getChannel2(i);
    }

    public void setupTracksAsync(List<TrackSettings> list, MetronomeSettings metronomeSettings, OnDoneListener onDoneListener) {
        new SetupTracksTask(list, metronomeSettings, onDoneListener).execute(new Void[0]);
    }

    public int setupTracks(List<TrackSettings> list, MetronomeSettings metronomeSettings) {
        int size = list.size();
        Patch[] patchArr = new Patch[(size + 1)];
        try {
            this.m_SoftSynth.unloadAllInstruments(this.m_SoundFont);
            for (int i = 0; i < size; i++) {
                patchArr[i] = new Patch(0, list.get(i).instrumentProgram);
            }
            patchArr[size] = new Patch(0, 115);
            try {
                this.m_SoftSynth.loadInstruments(this.m_SoundFont, patchArr);
                MidiChannel[] channels = this.m_SoftSynth.getChannels();
                this.m_TrackManager.reset();
                synchronized (this.m_MidiLock) {
                    for (int i2 = 0; i2 < size; i2++) {
                        int channel1 = this.m_TrackManager.getChannel1(i2);
                        int channel2 = this.m_TrackManager.getChannel2(i2);
                        MidiChannel midiChannel = channels[channel1];
                        MidiChannel midiChannel2 = channels[channel2];
                        int i3 = list.get(i2).instrumentProgram;
                        midiChannel.programChange(0, i3);
                        midiChannel2.programChange(0, i3);
                        List<VoiceSettings> list2 = list.get(i2).voices;
                        sendMidi(MidiUtils.getVolumeMessage(channel1, list2.get(0).volume));
                        sendMidi(MidiUtils.getVolumeMessage(channel2, list2.get(1).volume));
                        this.m_TrackManager.setProgram(i2, i3);
                        this.m_TrackManager.setUsed(i2, true);
                    }
                    channels[40].programChange(0, 115);
                    sendMidi(MidiUtils.getVolumeMessage(40, metronomeSettings.volume));
                }
                return 0;
            } catch (IllegalArgumentException e) {
                Log.w(LOGTAG, "setupAllTracks", e);
                return -3;
            }
        } catch (IllegalArgumentException e2) {
            Log.w(LOGTAG, "setupAllTracks", e2);
            return -2;
        }
    }

    public void setVoice1Volume(int i, float f) {
        synchronized (this.m_MidiLock) {
            sendMidi(MidiUtils.getVolumeMessage(this.m_TrackManager.getChannel1(i), f));
        }
    }

    public void setVoice2Volume(int i, float f) {
        synchronized (this.m_MidiLock) {
            sendMidi(MidiUtils.getVolumeMessage(this.m_TrackManager.getChannel2(i), f));
        }
    }

    public void setMetronomeVolume(float f) {
        synchronized (this.m_MidiLock) {
            sendMidi(MidiUtils.getVolumeMessage(40, f));
        }
    }

    public int setTrackProgram(int i, int i2) {
        if (i >= 41) {
            return -3;
        }
        MidiChannel[] channels = this.m_SoftSynth.getChannels();
        int program = this.m_TrackManager.getProgram(i);
        int channel1 = this.m_TrackManager.getChannel1(i);
        int channel2 = this.m_TrackManager.getChannel2(i);
        if (i2 == program) {
            return 0;
        }
        if (i2 == 115) {
            channels[channel1].programChange(0, i2);
            channels[channel2].programChange(0, i2);
            this.m_TrackManager.setProgram(i, i2);
            return 0;
        }
        boolean isProgramUsedByOtherTrack = this.m_TrackManager.isProgramUsedByOtherTrack(i, i2);
        boolean isProgramUsedByOtherTrack2 = this.m_TrackManager.isProgramUsedByOtherTrack(i, program);
        if (program != 115 && !isProgramUsedByOtherTrack2) {
            Patch patch = new Patch(0, program);
            this.m_SoftSynth.unloadInstruments(this.m_SoundFont, new Patch[]{patch});
        }
        if (!isProgramUsedByOtherTrack) {
            Patch patch2 = new Patch(0, i2);
            this.m_SoftSynth.loadInstruments(this.m_SoundFont, new Patch[]{patch2});
        }
        channels[channel1].programChange(0, i2);
        channels[channel2].programChange(0, i2);
        this.m_TrackManager.setProgram(i, i2);
        return 0;
    }

    public int loadPrograms(int[] iArr) {
        int length = iArr.length;
        Patch[] patchArr = new Patch[length];
        try {
            this.m_SoftSynth.unloadAllInstruments(this.m_SoundFont);
            for (int i = 0; i < length; i++) {
                patchArr[i] = new Patch(0, iArr[i]);
            }
            try {
                this.m_SoftSynth.loadInstruments(this.m_SoundFont, patchArr);
                return 0;
            } catch (IllegalArgumentException e) {
                Log.e(LOGTAG, "loadPrograms()", e);
                return -3;
            }
        } catch (IllegalArgumentException e2) {
            Log.w(LOGTAG, "setInstrumentChannels()", e2);
            return -2;
        }
    }

    public int loadProgramList(List<Integer> list) {
        int size = list.size();
        Patch[] patchArr = new Patch[size];
        try {
            this.m_SoftSynth.unloadAllInstruments(this.m_SoundFont);
            for (int i = 0; i < size; i++) {
                patchArr[i] = new Patch(0, list.get(i).intValue());
            }
            try {
                this.m_SoftSynth.loadInstruments(this.m_SoundFont, patchArr);
                return 0;
            } catch (IllegalArgumentException e) {
                Log.e(LOGTAG, "loadPrograms()", e);
                return -3;
            }
        } catch (IllegalArgumentException e2) {
            Log.w(LOGTAG, "setInstrumentChannels()", e2);
            return -2;
        }
    }

    public String getInstrumentName(int i, int i2) {
        Instrument instrument = this.m_SoundFont.getInstrument(new Patch(i, i2));
        if (instrument != null) {
            return instrument.getName();
        }
        return "Bank " + i + "  Prog " + i2;
    }

    public void playNote(int i, int i2, float f) {
        playNote(i, i2, Math.round(f * 127.0f));
    }

    public void playNote(int i, int i2, int i3) {
        if (i3 > 127) {
            i3 = 127;
        }
        sendMidi(MidiUtils.getNoteOnMessage(i, i2, i3));
    }

    public void stopNote(int i, int i2) {
        sendMidi(MidiUtils.getNoteOffMessage(i, i2));
    }

    public void playMetronomeTickWithGain(float f) {
        sendMidi(MidiUtils.getNoteOnMessage(40, 63, Math.round(f * 127.0f)));
    }

    public void allNotesOff() {
        synchronized (this.m_MidiLock) {
            for (int i = 0; i < 41; i++) {
                sendMidi(ShortMessage.CONTROL_CHANGE, i, 123, 0);
            }
        }
    }

    public void setPitchHz(double d) {
        this.m_PitchCents = (int) ((Math.log(d / 440.0d) * 1200.0d) / Math.log(2.0d));
        tune();
    }

    private void tune() {
        int i = this.m_TransCents + this.m_PitchCents;
        synchronized (this.m_MidiLock) {
            for (int i2 = 0; i2 < 41; i2++) {
                ShortMessage[] tuneMessagesFix = MidiUtils.getTuneMessagesFix(i2, i);
                if (tuneMessagesFix != null) {
                    for (ShortMessage sendMidi : tuneMessagesFix) {
                        sendMidi(sendMidi);
                    }
                }
            }
        }
    }

    private void sendMidi(ShortMessage shortMessage) {
        if (shortMessage != null) {
            this.m_Receiver.send(shortMessage, -1);
        }
    }

    private void sendMidi(int i, int i2, int i3, int i4) {
        if (this.m_Receiver != null) {
            try {
                SoftShortMessage softShortMessage = new SoftShortMessage();
                softShortMessage.setMessage(i, i2, i3, i4);
                this.m_Receiver.send(softShortMessage, -1);
            } catch (InvalidMidiDataException e) {
                Log.w(LOGTAG, "sendMidi", e);
            }
        }
    }

    private class OpenTask extends AsyncTask<Void, Void, Boolean> {
        private OnDoneListener m_Listener;

        public OpenTask(OnDoneListener onDoneListener) {
            this.m_Listener = onDoneListener;
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(Synth.this.open());
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            super.onPostExecute(bool);
            OnDoneListener onDoneListener = this.m_Listener;
            if (onDoneListener != null) {
                onDoneListener.onDone(bool.booleanValue() ? 0 : -1, bool.booleanValue() ? "Synth open" : "Open synth failed");
            }
        }
    }

    private class SetupTracksTask extends AsyncTask<Void, Void, Integer> {
        private OnDoneListener m_Listener;
        private MetronomeSettings m_Metronome;
        private List<TrackSettings> m_Tracks;

        public SetupTracksTask(List<TrackSettings> list, MetronomeSettings metronomeSettings, OnDoneListener onDoneListener) {
            this.m_Tracks = list;
            this.m_Metronome = metronomeSettings;
            this.m_Listener = onDoneListener;
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... voidArr) {
            return Integer.valueOf(Synth.this.setupTracks(this.m_Tracks, this.m_Metronome));
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            OnDoneListener onDoneListener = this.m_Listener;
            if (onDoneListener != null) {
                onDoneListener.onDone(num.intValue(), num.intValue() == 0 ? "Setup all tracks ok" : "Setup all tracks failed");
            }
        }
    }
}
