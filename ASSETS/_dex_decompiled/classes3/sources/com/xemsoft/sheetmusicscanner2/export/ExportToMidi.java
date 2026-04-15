package com.xemsoft.sheetmusicscanner2.export;

import android.content.Context;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Numa;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_int;
import com.xemsoft.sheetmusicscanner2.persist.PlayerSettings;
import com.xemsoft.sheetmusicscanner2.player.sound.SoundPlayer;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_NUMA;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.keySignature;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.MidiUtils;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.MetaMessage;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.Track;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class ExportToMidi {
    private static final String LOGTAG = "ExportToMidi.java";
    private final int MAX_NAME_LEN = WorkQueueKt.MASK;
    private final int RESOLUTION = 480;
    private Context m_Context;
    private VoiceTrackManager m_VTM;

    public ExportToMidi(Context context) {
        this.m_Context = context;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x02b4 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x02a1  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xemsoft.sheetmusicscanner2.export.SequenceData musicSequenceCreateWithSession(com.xemsoft.sheetmusicscanner2.sources.session r34, int r35, double r36, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings r38, int r39, boolean r40) {
        /*
            r33 = this;
            r1 = r33
            jp.kshoji.javax.sound.midi.Sequence r6 = new jp.kshoji.javax.sound.midi.Sequence     // Catch:{ InvalidMidiDataException -> 0x02e5 }
            r0 = 0
            r2 = 480(0x1e0, float:6.73E-43)
            r6.<init>(r0, r2)     // Catch:{ InvalidMidiDataException -> 0x02e5 }
            com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager r2 = new com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager
            android.content.Context r3 = r1.m_Context
            r11 = 1
            r5 = r34
            r8 = r36
            r4 = r38
            r7 = r39
            r10 = r40
            r2.<init>(r3, r4, r5, r6, r7, r8, r10, r11)
            r7 = r4
            r0 = r5
            r8 = r6
            r1.m_VTM = r2
            jp.kshoji.javax.sound.midi.Track r2 = r2.getTempoTrack()
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r9 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionGetTimeSignatureCopy(r0)
            int r5 = r9.getBeats()
            int r6 = r9.getBeatType()
            r3 = 0
            r1.addToTempoTrack(r2, r3, r5, r6)
            com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_p_timeSignature r3 = new com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_p_timeSignature
            long r9 = com.xemsoft.sheetmusicscanner2.sources.timeSignature.getCPtr(r9)
            r11 = 0
            r3.<init>(r9, r11)
            com.xemsoft.sheetmusicscanner2.sources.JniSource.tsDestroy(r3)
            r3 = 0
            r9 = r35
            r1.setBpm(r2, r9, r3)
            com.xemsoft.sheetmusicscanner2.sources.score r3 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionGetFirstNonEmptyGroupBarScore(r0)
            com.xemsoft.sheetmusicscanner2.sources.bar r4 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionGetFirstNonEmptyGroupBarBar(r0)
            r9 = -1
            int r10 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionStateSetCurrentBar(r0, r3, r4, r9)
            com.xemsoft.sheetmusicscanner2.persist.MetronomeSettings r3 = r7.metronome
            boolean r3 = r3.enableCountIn
            r13 = 0
            r15 = 1
            if (r3 == 0) goto L_0x0067
            double r16 = r1.generateCountInTicksForBar(r4, r0, r15, r7)
            r3 = r5
            r5 = r6
            goto L_0x006b
        L_0x0067:
            r3 = r5
            r5 = r6
            r16 = r13
        L_0x006b:
            r6 = 0
        L_0x006c:
            if (r4 == 0) goto L_0x02c6
            if (r10 != 0) goto L_0x02c6
            int r18 = (r16 > r13 ? 1 : (r16 == r13 ? 0 : -1))
            if (r18 != 0) goto L_0x00d3
            double r18 = r4.getLength()
            r20 = 4661014508095930368(0x40af400000000000, double:4000.0)
            r36 = r10
            double r9 = (double) r3
            double r9 = r9 * r20
            r37 = r11
            r20 = 0
            double r11 = (double) r5
            double r9 = r9 / r11
            int r9 = (r18 > r9 ? 1 : (r18 == r9 ? 0 : -1))
            if (r9 == 0) goto L_0x00ca
            double r9 = r4.getLength()
            r18 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r9 = r9 / r18
            double r9 = r9 * r11
            r11 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r9 = r9 / r11
            int r9 = (int) r9
            double r10 = r4.getLength()
            double r10 = r10 / r18
            int[] r9 = r1.guessTimeSignatureWithDurationInQuarterNotes(r10, r9, r5)
            if (r9 == 0) goto L_0x00b8
            r10 = r5
            r5 = r9[r37]
            r9 = r9[r15]
            r11 = r4
            r12 = r6
            r6 = r9
            r9 = r3
            r3 = r16
            r1.addToTempoTrack(r2, r3, r5, r6)
            r1 = r15
            goto L_0x00c0
        L_0x00b8:
            r9 = r3
            r11 = r4
            r10 = r5
            r12 = r6
            r3 = r16
            r1 = r37
        L_0x00c0:
            r16 = r2
            r17 = r10
            r10 = r9
            r9 = r1
            r1 = r33
            goto L_0x011f
        L_0x00ca:
            r9 = r3
            r11 = r4
            r10 = r5
            r12 = r6
            r1 = r33
            r3 = r16
            goto L_0x0118
        L_0x00d3:
            r9 = r3
            r12 = r6
            r36 = r10
            r37 = r11
            r20 = 0
            r11 = r4
            r10 = r5
            r3 = r16
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r1 = r11.getTimeSignature()
            if (r1 == 0) goto L_0x0116
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r1 = r11.getTimeSignature()
            int r1 = r1.getBeats()
            if (r1 != r9) goto L_0x00f9
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r1 = r11.getTimeSignature()
            int r1 = r1.getBeatType()
            if (r1 == r10) goto L_0x0116
        L_0x00f9:
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r1 = r11.getTimeSignature()
            int r5 = r1.getBeats()
            com.xemsoft.sheetmusicscanner2.sources.timeSignature r1 = r11.getTimeSignature()
            int r6 = r1.getBeatType()
            r1 = r33
            r1.addToTempoTrack(r2, r3, r5, r6)
            r16 = r2
            r9 = r37
            r10 = r5
            r17 = r6
            goto L_0x011f
        L_0x0116:
            r1 = r33
        L_0x0118:
            r16 = r2
            r17 = r10
            r10 = r9
            r9 = r37
        L_0x011f:
            com.xemsoft.sheetmusicscanner2.sources.keySignature r2 = r11.getKeySignature()
            boolean r2 = r1.ksEqual(r12, r2)
            if (r2 != 0) goto L_0x0160
            com.xemsoft.sheetmusicscanner2.sources.keySignature r2 = r11.getKeySignature()
            if (r2 == 0) goto L_0x0146
            com.xemsoft.sheetmusicscanner2.sources.keySignature r2 = r11.getKeySignature()
            int r2 = r2.getIsSharps()
            if (r2 == 0) goto L_0x013b
            r2 = r15
            goto L_0x013c
        L_0x013b:
            r2 = -1
        L_0x013c:
            com.xemsoft.sheetmusicscanner2.sources.keySignature r5 = r11.getKeySignature()
            int r5 = r5.getCount()
            int r2 = r2 * r5
            goto L_0x0148
        L_0x0146:
            r2 = r37
        L_0x0148:
            com.xemsoft.sheetmusicscanner2.sources.keySignature r6 = r11.getKeySignature()
            jp.kshoji.javax.sound.midi.Track[] r5 = r8.getTracks()
            int r12 = r5.length
            r13 = r37
        L_0x0153:
            if (r13 >= r12) goto L_0x015f
            r14 = r5[r13]
            if (r14 == 0) goto L_0x015c
            r1.addKeySignatureToTrack(r14, r3, r2)
        L_0x015c:
            int r13 = r13 + 1
            goto L_0x0153
        L_0x015f:
            r12 = r6
        L_0x0160:
            r2 = r37
        L_0x0162:
            int r5 = r11.getN()
            r13 = 60
            if (r2 >= r5) goto L_0x027a
            com.xemsoft.sheetmusicscanner2.sources.timepoint r5 = com.xemsoft.sheetmusicscanner2.sources.JniSource.barGetTP(r11, r2)
            double r21 = r5.getStartTime()
            double r23 = r11.getOffset()
            r31 = r3
            r4 = r2
            double r2 = r21 - r23
            boolean r6 = r7.isSwingOn
            double r13 = com.xemsoft.sheetmusicscanner2.sources.JniSource.timePointToSeconds(r2, r13, r6)
            r6 = r37
        L_0x0183:
            int r15 = r5.getN()
            if (r6 >= r15) goto L_0x026f
            com.xemsoft.sheetmusicscanner2.sources.sound r15 = com.xemsoft.sheetmusicscanner2.sources.JniSource.tpGetSound(r5, r6)
            int r21 = r15.getIsRest()
            if (r21 != 0) goto L_0x025d
            int r21 = r15.getIsTiedWithPrevious()
            if (r21 == 0) goto L_0x019b
            goto L_0x025d
        L_0x019b:
            int r0 = r15.getVoiceIndex()
            r25 = r2
            int r2 = r15.getVoiceSubindexSplit()
            com.xemsoft.sheetmusicscanner2.persist.MixerSettings r3 = r7.mixer
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.TrackSettings> r3 = r3.tracks
            java.lang.Object r3 = r3.get(r0)
            com.xemsoft.sheetmusicscanner2.persist.TrackSettings r3 = (com.xemsoft.sheetmusicscanner2.persist.TrackSettings) r3
            r21 = r4
            com.xemsoft.sheetmusicscanner2.persist.VoiceSettings r4 = r3.getVoiceSettingsForVoiceIndex(r2)
            boolean r4 = r4.isMuted
            if (r4 == 0) goto L_0x01bb
        L_0x01b9:
            goto L_0x0261
        L_0x01bb:
            r4 = r37
            r22 = r2
        L_0x01bf:
            int r2 = r5.getN()
            if (r4 >= r2) goto L_0x01f2
            com.xemsoft.sheetmusicscanner2.sources.sound r2 = com.xemsoft.sheetmusicscanner2.sources.JniSource.tpGetSound(r5, r4)
            r23 = r2
            int r2 = r23.getPitch()
            r24 = r4
            int r4 = r15.getPitch()
            if (r2 != r4) goto L_0x01ef
            int r2 = r23.getVoiceIndex()
            int r4 = r15.getVoiceIndex()
            if (r2 == r4) goto L_0x01e2
            goto L_0x01ef
        L_0x01e2:
            double r27 = r23.getLength()
            double r29 = r15.getLength()
            int r2 = (r27 > r29 ? 1 : (r27 == r29 ? 0 : -1))
            if (r2 <= 0) goto L_0x01ef
            goto L_0x01b9
        L_0x01ef:
            int r4 = r24 + 1
            goto L_0x01bf
        L_0x01f2:
            double r27 = r15.getLength()
            r29 = 60
            boolean r2 = r7.isSwingOn
            r30 = r2
            double r23 = com.xemsoft.sheetmusicscanner2.sources.JniSource.durationToSecondsWithStartTime(r25, r27, r29, r30)
            int r2 = r15.getPitch()
            int r4 = r3.instrumentPitch
            int r2 = r2 + r4
            int r2 = com.xemsoft.sheetmusicscanner2.sources.JniSource.pitchToMidi(r2)
            com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager r4 = r1.m_VTM
            if (r22 != 0) goto L_0x0214
            jp.kshoji.javax.sound.midi.Track r4 = r4.getVoiceTrack1(r0)
            goto L_0x0218
        L_0x0214:
            jp.kshoji.javax.sound.midi.Track r4 = r4.getVoiceTrack2(r0)
        L_0x0218:
            com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager r15 = r1.m_VTM
            if (r22 != 0) goto L_0x0221
            int r0 = r15.getVoiceChannel1(r0)
            goto L_0x0225
        L_0x0221:
            int r0 = r15.getVoiceChannel2(r0)
        L_0x0225:
            if (r4 == 0) goto L_0x0261
            r15 = 1059648963(0x3f28f5c3, float:0.66)
            if (r40 == 0) goto L_0x0232
            int r3 = r3.instrumentProgram
            float r15 = com.xemsoft.sheetmusicscanner2.player.sound.SoundPlayer.gainChecked(r15, r3)
        L_0x0232:
            r3 = 1123942400(0x42fe0000, float:127.0)
            float r15 = r15 * r3
            int r3 = java.lang.Math.round(r15)
            double r27 = r31 + r13
            r29 = 4647151865492930560(0x407e000000000000, double:480.0)
            r15 = r5
            r22 = r6
            double r5 = r27 * r29
            long r5 = (long) r5
            jp.kshoji.javax.sound.midi.MidiEvent r3 = com.xemsoft.sheetmusicscanner2.util.MidiUtils.getNoteOnEvent(r0, r2, r3, r5)
            if (r3 == 0) goto L_0x024c
            r4.add(r3)
        L_0x024c:
            r27 = r5
            double r5 = r23 * r29
            long r5 = (long) r5
            long r5 = r27 + r5
            jp.kshoji.javax.sound.midi.MidiEvent r0 = com.xemsoft.sheetmusicscanner2.util.MidiUtils.getNoteOffEvent(r0, r2, r5)
            if (r0 == 0) goto L_0x0264
            r4.add(r0)
            goto L_0x0264
        L_0x025d:
            r25 = r2
            r21 = r4
        L_0x0261:
            r15 = r5
            r22 = r6
        L_0x0264:
            int r6 = r22 + 1
            r0 = r34
            r5 = r15
            r4 = r21
            r2 = r25
            goto L_0x0183
        L_0x026f:
            r21 = r4
            int r2 = r21 + 1
            r0 = r34
            r3 = r31
            r15 = 1
            goto L_0x0162
        L_0x027a:
            r31 = r3
            com.xemsoft.sheetmusicscanner2.persist.MetronomeSettings r0 = r7.metronome
            boolean r0 = r0.isEnabled
            if (r0 == 0) goto L_0x028e
            r3 = r34
            r6 = r40
            r2 = r11
            r4 = r31
            r1.generateMetronomeTicksForBar(r2, r3, r4, r6, r7)
            r3 = r4
            goto L_0x0290
        L_0x028e:
            r3 = r31
        L_0x0290:
            double r0 = r11.getLength()
            boolean r2 = r7.isSwingOn
            double r0 = com.xemsoft.sheetmusicscanner2.sources.JniSource.timePointToSeconds(r0, r13, r2)
            double r3 = r3 + r0
            com.xemsoft.sheetmusicscanner2.sources.playerListNode r0 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionStateMoveToNextBar(r34)
            if (r0 == 0) goto L_0x02a6
            com.xemsoft.sheetmusicscanner2.sources.bar r0 = r0.getBar()
            goto L_0x02a8
        L_0x02a6:
            r0 = r20
        L_0x02a8:
            r1 = r33
            r5 = r10
            r2 = r16
            r6 = r17
            if (r9 == 0) goto L_0x02b4
            r1.addToTempoTrack(r2, r3, r5, r6)
        L_0x02b4:
            r10 = r36
            r11 = r37
            r16 = r3
            r3 = r5
            r5 = r6
            r6 = r12
            r9 = -1
            r13 = 0
            r15 = 1
            r4 = r0
            r0 = r34
            goto L_0x006c
        L_0x02c6:
            r37 = r11
            jp.kshoji.javax.sound.midi.Track[] r0 = r8.getTracks()
            r11 = r37
        L_0x02ce:
            int r2 = r0.length
            if (r11 >= r2) goto L_0x02d9
            r2 = r0[r11]
            jp.kshoji.javax.sound.midi.Track.TrackUtils.sortEvents(r2)
            int r11 = r11 + 1
            goto L_0x02ce
        L_0x02d9:
            com.xemsoft.sheetmusicscanner2.export.SequenceData r0 = new com.xemsoft.sheetmusicscanner2.export.SequenceData
            com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager r2 = r1.m_VTM
            java.util.List r2 = r2.getUsedPrograms()
            r0.<init>(r8, r2)
            return r0
        L_0x02e5:
            r0 = move-exception
            r20 = 0
            java.lang.String r2 = "ExportToMidi.java"
            java.lang.String r3 = "musicSequenceCreateWithSession()"
            android.util.Log.w(r2, r3, r0)
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.export.ExportToMidi.musicSequenceCreateWithSession(com.xemsoft.sheetmusicscanner2.sources.session, int, double, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings, int, boolean):com.xemsoft.sheetmusicscanner2.export.SequenceData");
    }

    private double generateCountInTicksForBar(bar bar, session session, boolean z, PlayerSettings playerSettings) {
        double d;
        float f = 0.66f;
        if (z) {
            f = SoundPlayer.gainChecked(0.66f, 115);
        }
        int round = Math.round(f * 127.0f);
        SWIGTYPE_p_int new_lintp = JniSource.new_lintp();
        long cPtr = SWIGTYPE_p_NUMA.getCPtr(JniSource.sessionCreateMetronomeCountInIntervalsForBar(session, bar));
        int i = 0;
        Numa numa = new Numa(cPtr, false);
        if (numa.getN() > 0) {
            while (i < numa.getN() - 1) {
                JniLeptonica.numaGetIValue(numa, i, new_lintp);
                float lintp_value = (float) JniSource.lintp_value(new_lintp);
                if (i < numa.getN() - 1) {
                    JniLeptonica.numaGetIValue(numa, i + 1, new_lintp);
                }
                float lintp_value2 = i < numa.getN() + -1 ? (float) JniSource.lintp_value(new_lintp) : (float) bar.getLength();
                double d2 = (double) lintp_value;
                double timePointToSeconds = JniSource.timePointToSeconds(d2, 60, playerSettings.isSwingOn);
                double durationToSecondsWithStartTime = JniSource.durationToSecondsWithStartTime(d2, (double) lintp_value2, 60, playerSettings.isSwingOn);
                long j = (long) (timePointToSeconds * 480.0d);
                MidiEvent noteOnEvent = MidiUtils.getNoteOnEvent(this.m_VTM.getMetronomeChannel(), 63, round, j);
                if (noteOnEvent != null) {
                    this.m_VTM.getMetronomeTrack().add(noteOnEvent);
                }
                MidiEvent noteOffEvent = MidiUtils.getNoteOffEvent(this.m_VTM.getMetronomeChannel(), 63, j + ((long) (durationToSecondsWithStartTime * 480.0d)));
                if (noteOffEvent != null) {
                    this.m_VTM.getMetronomeTrack().add(noteOffEvent);
                }
                i++;
            }
            JniLeptonica.numaGetIValue(numa, numa.getN() - 1, new_lintp);
            d = JniSource.timePointToSeconds((double) ((float) JniSource.lintp_value(new_lintp)), 60, playerSettings.isSwingOn);
        } else {
            d = 0.0d;
        }
        JniSource.delete_lintp(new_lintp);
        numa.finalize();
        return d;
    }

    private void generateMetronomeTicksForBar(bar bar, session session, double d, boolean z, PlayerSettings playerSettings) {
        PlayerSettings playerSettings2 = playerSettings;
        float f = 0.66f;
        if (z) {
            f = SoundPlayer.gainChecked(0.66f, 115);
        }
        int round = Math.round(f * 127.0f);
        SWIGTYPE_p_int new_lintp = JniSource.new_lintp();
        bar bar2 = bar;
        int i = 0;
        Numa numa = new Numa(SWIGTYPE_p_NUMA.getCPtr(JniSource.sessionCreateMetronomeCountInIntervalsForBar(session, bar2)), false);
        while (i < numa.getN()) {
            JniLeptonica.numaGetIValue(numa, i, new_lintp);
            float lintp_value = (float) JniSource.lintp_value(new_lintp);
            if (i < numa.getN() - 1) {
                JniLeptonica.numaGetIValue(numa, i + 1, new_lintp);
            }
            float lintp_value2 = i < numa.getN() + -1 ? (float) JniSource.lintp_value(new_lintp) : (float) bar2.getLength();
            double d2 = (double) lintp_value;
            double timePointToSeconds = JniSource.timePointToSeconds(d2, 60, playerSettings2.isSwingOn);
            double durationToSecondsWithStartTime = JniSource.durationToSecondsWithStartTime(d2, (double) (lintp_value2 - lintp_value), 60, playerSettings2.isSwingOn);
            long j = (long) ((d + timePointToSeconds) * 480.0d);
            MidiEvent noteOnEvent = MidiUtils.getNoteOnEvent(this.m_VTM.getMetronomeChannel(), 63, round, j);
            if (noteOnEvent != null) {
                this.m_VTM.getMetronomeTrack().add(noteOnEvent);
            }
            MidiEvent noteOffEvent = MidiUtils.getNoteOffEvent(this.m_VTM.getMetronomeChannel(), 63, j + ((long) (durationToSecondsWithStartTime * 480.0d)));
            if (noteOffEvent != null) {
                this.m_VTM.getMetronomeTrack().add(noteOffEvent);
            }
            i++;
        }
        JniSource.delete_lintp(new_lintp);
        numa.finalize();
    }

    private void setBpm(Track track, int i, long j) {
        long j2 = 60000000 / ((long) i);
        try {
            track.add(new MidiEvent(new MetaMessage(81, new byte[]{(byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) (j2 & 255))}, 3), j));
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "setBpm()", e);
        }
    }

    private void addToTempoTrack(Track track, double d, int i, int i2) {
        try {
            track.add(new MidiEvent(new MetaMessage(88, new byte[]{(byte) (i & 255), (byte) (Utils.log2(i2) & 255), (byte) ((1920 / i2) & 255), 8}, 4), (long) (d * 480.0d)));
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "addToTempoTrack()", e);
        }
    }

    private void addKeySignatureToTrack(Track track, double d, int i) {
        try {
            track.add(new MidiEvent(new MetaMessage(89, new byte[]{(byte) (i & 255), 0}, 2), (long) (d * 480.0d)));
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "addToTempoTrack()", e);
        }
    }

    private int[] guessTimeSignatureWithDurationInQuarterNotes(double d, int i, int i2) {
        int[] iArr = new int[2];
        if (d == (((double) i) * 4.0d) / ((double) i2)) {
            iArr[0] = i;
            iArr[1] = i2;
            return iArr;
        }
        for (int i3 = 2; i3 <= 16; i3 *= 2) {
            double d2 = (((double) i3) * d) / 4.0d;
            int i4 = (int) d2;
            if (((double) i4) == d2) {
                iArr[0] = i4;
                iArr[1] = i3;
                return iArr;
            }
        }
        return null;
    }

    private boolean ksEqual(keySignature keysignature, keySignature keysignature2) {
        return keysignature == null ? keysignature2 == null : keysignature2 == null ? keysignature == null : keysignature.getIsSharps() == keysignature2.getIsSharps() && keysignature.getCount() == keysignature2.getCount();
    }
}
