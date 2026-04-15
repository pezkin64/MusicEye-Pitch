package com.xemsoft.sheetmusicscanner2.sources;

import android.graphics.Bitmap;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.analysis.Scanner;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_int;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_p_Pix;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_unsigned_int;
import com.xemsoft.sheetmusicscanner2.util.Swig;

public class JniSource implements JniSourceConstants {
    private static final String LOGTAG = "JniSource";

    public static float findCorrelationScoreInBB(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniSourceJNI.findCorrelationScoreInBB(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static float findCorrelationScoreInBBBlack(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniSourceJNI.findCorrelationScoreInBBBlack(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int countPixels(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.countPixels(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static int countPixelsInRect(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, int i2, int i3, int i4) {
        return JniSourceJNI.countPixelsInRect(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, i2, i3, i4);
    }

    public static float coverage(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.coverage(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static float coverageInRect(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, int i2, int i3, int i4) {
        return JniSourceJNI.coverageInRect(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, i2, i3, i4);
    }

    public static keySignature ksCreate(int i, int i2) {
        long ksCreate = JniSourceJNI.ksCreate(i, i2);
        if (ksCreate == 0) {
            return null;
        }
        return new keySignature(ksCreate, false);
    }

    public static void ksDestroy(SWIGTYPE_p_p_keySignature sWIGTYPE_p_p_keySignature) {
        JniSourceJNI.ksDestroy(SWIGTYPE_p_p_keySignature.getCPtr(sWIGTYPE_p_p_keySignature));
    }

    public static keySignature ksCopy(keySignature keysignature) {
        long ksCopy = JniSourceJNI.ksCopy(keySignature.getCPtr(keysignature), keysignature);
        if (ksCopy == 0) {
            return null;
        }
        return new keySignature(ksCopy, false);
    }

    public static timeSignature tsCreateCommon() {
        long tsCreateCommon = JniSourceJNI.tsCreateCommon();
        if (tsCreateCommon == 0) {
            return null;
        }
        return new timeSignature(tsCreateCommon, false);
    }

    public static timeSignature tsCreateNormal(int i, int i2) {
        long tsCreateNormal = JniSourceJNI.tsCreateNormal(i, i2);
        if (tsCreateNormal == 0) {
            return null;
        }
        return new timeSignature(tsCreateNormal, false);
    }

    public static void tsDestroy(SWIGTYPE_p_p_timeSignature sWIGTYPE_p_p_timeSignature) {
        JniSourceJNI.tsDestroy(SWIGTYPE_p_p_timeSignature.getCPtr(sWIGTYPE_p_p_timeSignature));
    }

    public static timeSignature tsCopy(timeSignature timesignature) {
        long tsCopy = JniSourceJNI.tsCopy(timeSignature.getCPtr(timesignature), timesignature);
        if (tsCopy == 0) {
            return null;
        }
        return new timeSignature(tsCopy, false);
    }

    public static sound soundCreate(int i, int i2, double d, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, symbolType symboltype, int i3, int i4) {
        long soundCreate = JniSourceJNI.soundCreate(i, i2, d, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), symboltype.swigValue(), i3, i4);
        if (soundCreate == 0) {
            return null;
        }
        return new sound(soundCreate, false);
    }

    public static void soundDestroy(SWIGTYPE_p_p_sound sWIGTYPE_p_p_sound) {
        JniSourceJNI.soundDestroy(SWIGTYPE_p_p_sound.getCPtr(sWIGTYPE_p_p_sound));
    }

    public static sound soundClone(sound sound, timepoint timepoint) {
        long soundClone = JniSourceJNI.soundClone(sound.getCPtr(sound), sound, timepoint.getCPtr(timepoint), timepoint);
        if (soundClone == 0) {
            return null;
        }
        return new sound(soundClone, false);
    }

    public static sound soundCopy(sound sound, timepoint timepoint) {
        long soundCopy = JniSourceJNI.soundCopy(sound.getCPtr(sound), sound, timepoint.getCPtr(timepoint), timepoint);
        if (soundCopy == 0) {
            return null;
        }
        return new sound(soundCopy, false);
    }

    public static int soundScale(sound sound, float f) {
        return JniSourceJNI.soundScale(sound.getCPtr(sound), sound, f);
    }

    public static timepoint tpCreate(double d, double d2) {
        long tpCreate = JniSourceJNI.tpCreate(d, d2);
        if (tpCreate == 0) {
            return null;
        }
        return new timepoint(tpCreate, false);
    }

    public static void tpDestroy(SWIGTYPE_p_p_timepoint sWIGTYPE_p_p_timepoint) {
        JniSourceJNI.tpDestroy(SWIGTYPE_p_p_timepoint.getCPtr(sWIGTYPE_p_p_timepoint));
    }

    public static int tpAddSound(timepoint timepoint, sound sound) {
        return JniSourceJNI.tpAddSound(timepoint.getCPtr(timepoint), timepoint, sound.getCPtr(sound), sound);
    }

    public static sound tpGetSound(timepoint timepoint, int i) {
        long tpGetSound = JniSourceJNI.tpGetSound(timepoint.getCPtr(timepoint), timepoint, i);
        if (tpGetSound == 0) {
            return null;
        }
        return new sound(tpGetSound, false);
    }

    public static timepoint tpClone(timepoint timepoint, timepoint timepoint2) {
        long tpClone = JniSourceJNI.tpClone(timepoint.getCPtr(timepoint), timepoint, timepoint.getCPtr(timepoint2), timepoint2);
        if (tpClone == 0) {
            return null;
        }
        return new timepoint(tpClone, false);
    }

    public static timepoint tpCopy(timepoint timepoint, timepoint timepoint2) {
        long tpCopy = JniSourceJNI.tpCopy(timepoint.getCPtr(timepoint), timepoint, timepoint.getCPtr(timepoint2), timepoint2);
        if (tpCopy == 0) {
            return null;
        }
        return new timepoint(tpCopy, false);
    }

    public static int tpScale(timepoint timepoint, float f) {
        return JniSourceJNI.tpScale(timepoint.getCPtr(timepoint), timepoint, f);
    }

    public static bar barCreate() {
        long barCreate = JniSourceJNI.barCreate();
        if (barCreate == 0) {
            return null;
        }
        return new bar(barCreate, false);
    }

    public static void barDestroy(SWIGTYPE_p_p_bar sWIGTYPE_p_p_bar) {
        JniSourceJNI.barDestroy(SWIGTYPE_p_p_bar.getCPtr(sWIGTYPE_p_p_bar));
    }

    public static int barAddTP(bar bar, timepoint timepoint) {
        return JniSourceJNI.barAddTP(bar.getCPtr(bar), bar, timepoint.getCPtr(timepoint), timepoint);
    }

    public static timepoint barGetTP(bar bar, int i) {
        long barGetTP = JniSourceJNI.barGetTP(bar.getCPtr(bar), bar, i);
        if (barGetTP == 0) {
            return null;
        }
        return new timepoint(barGetTP, false);
    }

    public static int barInsertTP(bar bar, timepoint timepoint, int i) {
        return JniSourceJNI.barInsertTP(bar.getCPtr(bar), bar, timepoint.getCPtr(timepoint), timepoint, i);
    }

    public static int barRemoveTPByIndex(bar bar, int i) {
        return JniSourceJNI.barRemoveTPByIndex(bar.getCPtr(bar), bar, i);
    }

    public static bar barCopy(bar bar, timepoint timepoint) {
        long barCopy = JniSourceJNI.barCopy(bar.getCPtr(bar), bar, timepoint.getCPtr(timepoint), timepoint);
        if (barCopy == 0) {
            return null;
        }
        return new bar(barCopy, false);
    }

    public static int barScale(bar bar, float f) {
        return JniSourceJNI.barScale(bar.getCPtr(bar), bar, f);
    }

    public static int barIsEmpty(bar bar) {
        return JniSourceJNI.barIsEmpty(bar.getCPtr(bar), bar);
    }

    public static bara baraCreate() {
        long baraCreate = JniSourceJNI.baraCreate();
        if (baraCreate == 0) {
            return null;
        }
        return new bara(baraCreate, false);
    }

    public static void baraDestroy(SWIGTYPE_p_p_bara sWIGTYPE_p_p_bara) {
        JniSourceJNI.baraDestroy(SWIGTYPE_p_p_bara.getCPtr(sWIGTYPE_p_p_bara));
    }

    public static int baraAdd(bara bara, bar bar) {
        return JniSourceJNI.baraAdd(bara.getCPtr(bara), bara, bar.getCPtr(bar), bar);
    }

    public static bar baraGet(bara bara, int i) {
        long baraGet = JniSourceJNI.baraGet(bara.getCPtr(bara), bara, i);
        if (baraGet == 0) {
            return null;
        }
        return new bar(baraGet, false);
    }

    public static int baraMergeBarWithNext(bara bara, int i) {
        return JniSourceJNI.baraMergeBarWithNext(bara.getCPtr(bara), bara, i);
    }

    public static int baraScale(bara bara, float f) {
        return JniSourceJNI.baraScale(bara.getCPtr(bara), bara, f);
    }

    public static baraa baraaCreate() {
        long baraaCreate = JniSourceJNI.baraaCreate();
        if (baraaCreate == 0) {
            return null;
        }
        return new baraa(baraaCreate, false);
    }

    public static void baraaDestroy(SWIGTYPE_p_p_baraa sWIGTYPE_p_p_baraa) {
        JniSourceJNI.baraaDestroy(SWIGTYPE_p_p_baraa.getCPtr(sWIGTYPE_p_p_baraa));
    }

    public static int baraaAdd(baraa baraa, bara bara) {
        return JniSourceJNI.baraaAdd(baraa.getCPtr(baraa), baraa, bara.getCPtr(bara), bara);
    }

    public static bara baraaGet(baraa baraa, int i) {
        long baraaGet = JniSourceJNI.baraaGet(baraa.getCPtr(baraa), baraa, i);
        if (baraaGet == 0) {
            return null;
        }
        return new bara(baraaGet, false);
    }

    public static bar baraaGetBar(baraa baraa, int i, int i2) {
        long baraaGetBar = JniSourceJNI.baraaGetBar(baraa.getCPtr(baraa), baraa, i, i2);
        if (baraaGetBar == 0) {
            return null;
        }
        return new bar(baraaGetBar, false);
    }

    public static int baraaGetBarCount(baraa baraa, int i) {
        return JniSourceJNI.baraaGetBarCount(baraa.getCPtr(baraa), baraa, i);
    }

    public static bar baraaFindPreviousBarOverlappingInX(baraa baraa, bar bar, int i) {
        long baraaFindPreviousBarOverlappingInX = JniSourceJNI.baraaFindPreviousBarOverlappingInX(baraa.getCPtr(baraa), baraa, bar.getCPtr(bar), bar, i);
        if (baraaFindPreviousBarOverlappingInX == 0) {
            return null;
        }
        return new bar(baraaFindPreviousBarOverlappingInX, false);
    }

    public static bar baraaFindNextBarOverlappingInX(baraa baraa, bar bar, int i) {
        long baraaFindNextBarOverlappingInX = JniSourceJNI.baraaFindNextBarOverlappingInX(baraa.getCPtr(baraa), baraa, bar.getCPtr(bar), bar, i);
        if (baraaFindNextBarOverlappingInX == 0) {
            return null;
        }
        return new bar(baraaFindNextBarOverlappingInX, false);
    }

    public static int baraaScale(baraa baraa, float f) {
        return JniSourceJNI.baraaScale(baraa.getCPtr(baraa), baraa, f);
    }

    public static score scoreCreate() {
        long scoreCreate = JniSourceJNI.scoreCreate();
        if (scoreCreate == 0) {
            return null;
        }
        return new score(scoreCreate, false);
    }

    public static void scoreDestroy(SWIGTYPE_p_p_score sWIGTYPE_p_p_score) {
        JniSourceJNI.scoreDestroy(SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score));
    }

    public static bar scoreNextBar(score score, bar bar) {
        long scoreNextBar = JniSourceJNI.scoreNextBar(score.getCPtr(score), score, bar.getCPtr(bar), bar);
        if (scoreNextBar == 0) {
            return null;
        }
        return new bar(scoreNextBar, false);
    }

    public static bar scoreNextGroupBar(score score, bar bar) {
        long scoreNextGroupBar = JniSourceJNI.scoreNextGroupBar(score.getCPtr(score), score, bar.getCPtr(bar), bar);
        if (scoreNextGroupBar == 0) {
            return null;
        }
        return new bar(scoreNextGroupBar, false);
    }

    public static int scoreGetBarIndexes(score score, bar bar, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.scoreGetBarIndexes(score.getCPtr(score), score, bar.getCPtr(bar), bar, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int scoreGetGroupBarIndexes(score score, bar bar, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.scoreGetGroupBarIndexes(score.getCPtr(score), score, bar.getCPtr(bar), bar, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int scoreGetFirstBarStaffIndexForVoice(score score, int i) {
        return JniSourceJNI.scoreGetFirstBarStaffIndexForVoice(score.getCPtr(score), score, i);
    }

    public static int scoreGetVoiceIndex(score score, int i) {
        return JniSourceJNI.scoreGetVoiceIndex(score.getCPtr(score), score, i);
    }

    public static int scoreGetGroupIndexByStaffIndex(score score, int i) {
        return JniSourceJNI.scoreGetGroupIndexByStaffIndex(score.getCPtr(score), score, i);
    }

    public static int scoreGetFirstStaffIndexByGroupIndex(score score, int i) {
        return JniSourceJNI.scoreGetFirstStaffIndexByGroupIndex(score.getCPtr(score), score, i);
    }

    public static int scoreScale(score score, float f) {
        return JniSourceJNI.scoreScale(score.getCPtr(score), score, f);
    }

    public static int scoreIsMultipleVoices(score score) {
        return JniSourceJNI.scoreIsMultipleVoices(score.getCPtr(score), score);
    }

    public static session sessionCreate() {
        long sessionCreate = JniSourceJNI.sessionCreate();
        if (sessionCreate == 0) {
            return null;
        }
        return new session(sessionCreate, false);
    }

    public static void sessionDestroy(SWIGTYPE_p_p_session sWIGTYPE_p_p_session) {
        JniSourceJNI.sessionDestroy(SWIGTYPE_p_p_session.getCPtr(sWIGTYPE_p_p_session));
    }

    public static int sessionAlignVoiceIndexes(session session, int i) {
        return JniSourceJNI.sessionAlignVoiceIndexes(session.getCPtr(session), session, i);
    }

    public static int sessionGetVoiceSubindexSplitCount(session session, int i) {
        return JniSourceJNI.sessionGetVoiceSubindexSplitCount(session.getCPtr(session), session, i);
    }

    public static int sessionGetMaxVoiceIndex(session session) {
        return JniSourceJNI.sessionGetMaxVoiceIndex(session.getCPtr(session), session);
    }

    public static int sessionAdd(session session, score score) {
        return JniSourceJNI.sessionAdd(session.getCPtr(session), session, score.getCPtr(score), score);
    }

    public static int sessionInsert(session session, score score, int i) {
        return JniSourceJNI.sessionInsert(session.getCPtr(session), session, score.getCPtr(score), score, i);
    }

    public static score sessionGet(session session, int i) {
        long sessionGet = JniSourceJNI.sessionGet(session.getCPtr(session), session, i);
        if (sessionGet == 0) {
            return null;
        }
        return new score(sessionGet, false);
    }

    public static int sessionRemove(session session, SWIGTYPE_p_p_score sWIGTYPE_p_p_score) {
        return JniSourceJNI.sessionRemove(session.getCPtr(session), session, SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score));
    }

    public static int sessionIsMultipleVoices(session session) {
        return JniSourceJNI.sessionIsMultipleVoices(session.getCPtr(session), session);
    }

    public static int sessionGetScoreIndex(session session, score score) {
        return JniSourceJNI.sessionGetScoreIndex(session.getCPtr(session), session, score.getCPtr(score), score);
    }

    public static score sessionGetScoreForFirstBarForVoice(session session, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long sessionGetScoreForFirstBarForVoice = JniSourceJNI.sessionGetScoreForFirstBarForVoice(session.getCPtr(session), session, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (sessionGetScoreForFirstBarForVoice == 0) {
            return null;
        }
        return new score(sessionGetScoreForFirstBarForVoice, false);
    }

    public static timeSignature sessionGetTimeSignatureCopy(session session) {
        long sessionGetTimeSignatureCopy = JniSourceJNI.sessionGetTimeSignatureCopy(session.getCPtr(session), session);
        if (sessionGetTimeSignatureCopy == 0) {
            return null;
        }
        return new timeSignature(sessionGetTimeSignatureCopy, false);
    }

    public static double pitchToHz(int i, int i2) {
        return JniSourceJNI.pitchToHz(i, i2);
    }

    public static int pitchToMidi(int i) {
        return JniSourceJNI.pitchToMidi(i);
    }

    public static double durationToSeconds(double d, int i) {
        return JniSourceJNI.durationToSeconds(d, i);
    }

    public static double timePointToSeconds(double d, int i, boolean z) {
        return JniSourceJNI.timePointToSeconds(d, i, z ? 1 : 0);
    }

    public static double durationToSecondsWithStartTime(double d, double d2, int i, boolean z) {
        return JniSourceJNI.durationToSecondsWithStartTime(d, d2, i, z ? 1 : 0);
    }

    public static SWIGTYPE_p_NUMA sessionCreateMetronomeIntervalsForBar(session session, bar bar) {
        long sessionCreateMetronomeIntervalsForBar = JniSourceJNI.sessionCreateMetronomeIntervalsForBar(session.getCPtr(session), session, bar.getCPtr(bar), bar);
        if (sessionCreateMetronomeIntervalsForBar == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(sessionCreateMetronomeIntervalsForBar, false);
    }

    public static SWIGTYPE_p_NUMA sessionCreateMetronomeCountInIntervalsForBar(session session, bar bar) {
        long sessionCreateMetronomeCountInIntervalsForBar = JniSourceJNI.sessionCreateMetronomeCountInIntervalsForBar(session.getCPtr(session), session, bar.getCPtr(bar), bar);
        if (sessionCreateMetronomeCountInIntervalsForBar == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(sessionCreateMetronomeCountInIntervalsForBar, false);
    }

    public static SWIGTYPE_p_NUMA getVoiceCounts(staffa staffa, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long voiceCounts = JniSourceJNI.getVoiceCounts(staffa.getCPtr(staffa), staffa, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (voiceCounts == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(voiceCounts, false);
    }

    public static SWIGTYPE_p_NUMA getVoiceIndexes(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long voiceIndexes = JniSourceJNI.getVoiceIndexes(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (voiceIndexes == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(voiceIndexes, false);
    }

    public static int scoreCalculateVoiceIndexes(score score, staffa staffa, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.scoreCalculateVoiceIndexes(score.getCPtr(score), score, staffa.getCPtr(staffa), staffa, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static int scoreAssignVoiceIndexesToSounds(score score) {
        return JniSourceJNI.scoreAssignVoiceIndexesToSounds(score.getCPtr(score), score);
    }

    public static int scoreSetUpGroupBars(score score) {
        return JniSourceJNI.scoreSetUpGroupBars(score.getCPtr(score), score);
    }

    public static int baraaAlignParallelBars(baraa baraa, int i, int i2) {
        return JniSourceJNI.baraaAlignParallelBars(baraa.getCPtr(baraa), baraa, i, i2);
    }

    public static int baraaAlignBarTPs(baraa baraa, int i, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA2) {
        return JniSourceJNI.baraaAlignBarTPs(baraa.getCPtr(baraa), baraa, i, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA2));
    }

    public static int baraaFixWholeRestBars(baraa baraa, int i, int i2, int i3) {
        return JniSourceJNI.baraaFixWholeRestBars(baraa.getCPtr(baraa), baraa, i, i2, i3);
    }

    public static int barMergeTPs(bar bar, bar bar2, timepoint timepoint) {
        return JniSourceJNI.barMergeTPs(bar.getCPtr(bar), bar, bar.getCPtr(bar2), bar2, timepoint.getCPtr(timepoint), timepoint);
    }

    public static int barFixPreviousPartAfterExtending(bar bar, int i, double d, baraa baraa, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i2, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA2, int i3) {
        return JniSourceJNI.barFixPreviousPartAfterExtending(bar.getCPtr(bar), bar, i, d, baraa.getCPtr(baraa), baraa, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i2, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA2), i3);
    }

    public static int fixBarEnds(baraa baraa, int i, int i2, int i3, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA2) {
        return JniSourceJNI.fixBarEnds(baraa.getCPtr(baraa), baraa, i, i2, i3, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA2));
    }

    public static SWIGTYPE_p_NUMA rleGetRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long rleGetRuns = JniSourceJNI.rleGetRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (rleGetRuns == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetRuns, false);
    }

    public static SWIGTYPE_p_NUMA rleGetNuma(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long rleGetNuma = JniSourceJNI.rleGetNuma(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (rleGetNuma == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetNuma, false);
    }

    public static int rleEraseShortEvenRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.rleEraseShortEvenRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int rleEraseShortOddRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.rleEraseShortOddRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int rleEraseLongEvenRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.rleEraseLongEvenRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int rleEraseLongOddRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.rleEraseLongOddRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int rleSumConsecutive(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, int i2) {
        return JniSourceJNI.rleSumConsecutive(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, i2);
    }

    public static dici rleGetFrequencies(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long rleGetFrequencies = JniSourceJNI.rleGetFrequencies(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (rleGetFrequencies == 0) {
            return null;
        }
        return new dici(rleGetFrequencies, false);
    }

    public static int rleFindMostFrequent(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        return JniSourceJNI.rleFindMostFrequent(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static SWIGTYPE_p_NUMA rleSortByFrequenciesDesc(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, SWIGTYPE_p_p_NUMA sWIGTYPE_p_p_NUMA) {
        long rleSortByFrequenciesDesc = JniSourceJNI.rleSortByFrequenciesDesc(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), SWIGTYPE_p_p_NUMA.getCPtr(sWIGTYPE_p_p_NUMA));
        if (rleSortByFrequenciesDesc == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleSortByFrequenciesDesc, false);
    }

    public static SWIGTYPE_p_NUMA rleGetColRuns(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i) {
        long rleGetColRuns = JniSourceJNI.rleGetColRuns(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i);
        if (rleGetColRuns == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetColRuns, false);
    }

    public static SWIGTYPE_p_NUMA rleGetColRunsPartial(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, int i2, int i3) {
        long rleGetColRunsPartial = JniSourceJNI.rleGetColRunsPartial(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, i2, i3);
        if (rleGetColRunsPartial == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetColRunsPartial, false);
    }

    public static SWIGTYPE_p_NUMA rleGetRowRuns(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i) {
        long rleGetRowRuns = JniSourceJNI.rleGetRowRuns(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i);
        if (rleGetRowRuns == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetRowRuns, false);
    }

    public static int rleGetBlackRunCount(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        return JniSourceJNI.rleGetBlackRunCount(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static int rleGetBlackRunCoords(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.rleGetBlackRunCoords(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int rleCollapseBlackRuns(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.rleCollapseBlackRuns(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static SWIGTYPE_p_NUMA rleGetContourLeft(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long rleGetContourLeft = JniSourceJNI.rleGetContourLeft(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (rleGetContourLeft == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetContourLeft, false);
    }

    public static SWIGTYPE_p_NUMA rleGetContourRight(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long rleGetContourRight = JniSourceJNI.rleGetContourRight(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (rleGetContourRight == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetContourRight, false);
    }

    public static SWIGTYPE_p_NUMA rleGetContourTop(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long rleGetContourTop = JniSourceJNI.rleGetContourTop(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (rleGetContourTop == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetContourTop, false);
    }

    public static SWIGTYPE_p_NUMA rleGetContourBottom(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long rleGetContourBottom = JniSourceJNI.rleGetContourBottom(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (rleGetContourBottom == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(rleGetContourBottom, false);
    }

    public static int rleConvexOrConcave(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        return JniSourceJNI.rleConvexOrConcave(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static int primTypeIsRest(primType primtype) {
        return JniSourceJNI.primTypeIsRest(primtype.swigValue());
    }

    public static int primTypeIsNote(primType primtype) {
        return JniSourceJNI.primTypeIsNote(primtype.swigValue());
    }

    public static int primTypeIsClef(primType primtype) {
        return JniSourceJNI.primTypeIsClef(primtype.swigValue());
    }

    public static int primTypeIsAccidental(primType primtype) {
        return JniSourceJNI.primTypeIsAccidental(primtype.swigValue());
    }

    public static double symbolTypeDuration(symbolType symboltype) {
        return JniSourceJNI.symbolTypeDuration(symboltype.swigValue());
    }

    public static double symbolTypeDurationWithDots(symbolType symboltype, int i) {
        return JniSourceJNI.symbolTypeDurationWithDots(symboltype.swigValue(), i);
    }

    public static int symbolTypeIsRest(symbolType symboltype) {
        return JniSourceJNI.symbolTypeIsRest(symboltype.swigValue());
    }

    public static int symbolTypeIsNote(symbolType symboltype) {
        return JniSourceJNI.symbolTypeIsNote(symboltype.swigValue());
    }

    public static int symbolTypeIsClef(symbolType symboltype) {
        return JniSourceJNI.symbolTypeIsClef(symboltype.swigValue());
    }

    public static int symbolTypeIsAccidental(symbolType symboltype) {
        return JniSourceJNI.symbolTypeIsAccidental(symboltype.swigValue());
    }

    public static int symbolTypeIsTimeSignature(symbolType symboltype) {
        return JniSourceJNI.symbolTypeIsTimeSignature(symboltype.swigValue());
    }

    public static match matchCreate() {
        long matchCreate = JniSourceJNI.matchCreate();
        if (matchCreate == 0) {
            return null;
        }
        return new match(matchCreate, false);
    }

    public static void matchDestroy(SWIGTYPE_p_p_match sWIGTYPE_p_p_match) {
        JniSourceJNI.matchDestroy(SWIGTYPE_p_p_match.getCPtr(sWIGTYPE_p_p_match));
    }

    public static void matchPrint(match match) {
        JniSourceJNI.matchPrint(match.getCPtr(match), match);
    }

    public static matcha matchaCreate(int i) {
        long matchaCreate = JniSourceJNI.matchaCreate(i);
        if (matchaCreate == 0) {
            return null;
        }
        return new matcha(matchaCreate, false);
    }

    public static void matchaDestroy(SWIGTYPE_p_p_matcha sWIGTYPE_p_p_matcha) {
        JniSourceJNI.matchaDestroy(SWIGTYPE_p_p_matcha.getCPtr(sWIGTYPE_p_p_matcha));
    }

    public static int matchaAdd(matcha matcha, match match) {
        return JniSourceJNI.matchaAdd(matcha.getCPtr(matcha), matcha, match.getCPtr(match), match);
    }

    public static match matchaGet(matcha matcha, int i) {
        long matchaGet = JniSourceJNI.matchaGet(matcha.getCPtr(matcha), matcha, i);
        if (matchaGet == 0) {
            return null;
        }
        return new match(matchaGet, false);
    }

    public static match matchaFind(matcha matcha, primType primtype) {
        long matchaFind = JniSourceJNI.matchaFind(matcha.getCPtr(matcha), matcha, primtype.swigValue());
        if (matchaFind == 0) {
            return null;
        }
        return new match(matchaFind, false);
    }

    public static int matchaRemove(matcha matcha, SWIGTYPE_p_p_match sWIGTYPE_p_p_match) {
        return JniSourceJNI.matchaRemove(matcha.getCPtr(matcha), matcha, SWIGTYPE_p_p_match.getCPtr(sWIGTYPE_p_p_match));
    }

    public static int matchaClear(matcha matcha) {
        return JniSourceJNI.matchaClear(matcha.getCPtr(matcha), matcha);
    }

    public static void matchaPrint(matcha matcha) {
        JniSourceJNI.matchaPrint(matcha.getCPtr(matcha), matcha);
    }

    public static PRIM primCreate(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        long primCreate = JniSourceJNI.primCreate(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
        if (primCreate == 0) {
            return null;
        }
        return new PRIM(primCreate, false);
    }

    public static PRIM primClone(PRIM prim) {
        long primClone = JniSourceJNI.primClone(PRIM.getCPtr(prim), prim);
        if (primClone == 0) {
            return null;
        }
        return new PRIM(primClone, false);
    }

    public static void primDestroy(SWIGTYPE_p_p_prim sWIGTYPE_p_p_prim) {
        JniSourceJNI.primDestroy(SWIGTYPE_p_p_prim.getCPtr(sWIGTYPE_p_p_prim));
    }

    public static int primAddMatch(PRIM prim, primType primtype, float f) {
        return JniSourceJNI.primAddMatch(PRIM.getCPtr(prim), prim, primtype.swigValue(), f);
    }

    public static int primRemoveMatch(PRIM prim, primType primtype) {
        return JniSourceJNI.primRemoveMatch(PRIM.getCPtr(prim), prim, primtype.swigValue());
    }

    public static int primDoesMatch(PRIM prim, primType primtype) {
        return JniSourceJNI.primDoesMatch(PRIM.getCPtr(prim), prim, primtype.swigValue());
    }

    public static PRIM primCreateByClipping(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, primType primtype) {
        long primCreateByClipping = JniSourceJNI.primCreateByClipping(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), primtype.swigValue());
        if (primCreateByClipping == 0) {
            return null;
        }
        return new PRIM(primCreateByClipping, false);
    }

    public static int primUpdate(PRIM prim, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, primType primtype) {
        return JniSourceJNI.primUpdate(PRIM.getCPtr(prim), prim, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), primtype.swigValue());
    }

    public static void primPrint(PRIM prim) {
        JniSourceJNI.primPrint(PRIM.getCPtr(prim), prim);
    }

    public static PRIMA primaCreate(int i) {
        long primaCreate = JniSourceJNI.primaCreate(i);
        if (primaCreate == 0) {
            return null;
        }
        return new PRIMA(primaCreate, false);
    }

    public static PRIMA primaCreateWithoutOwnership(int i) {
        long primaCreateWithoutOwnership = JniSourceJNI.primaCreateWithoutOwnership(i);
        if (primaCreateWithoutOwnership == 0) {
            return null;
        }
        return new PRIMA(primaCreateWithoutOwnership, false);
    }

    public static void primaDestroy(SWIGTYPE_p_p_prima sWIGTYPE_p_p_prima) {
        JniSourceJNI.primaDestroy(SWIGTYPE_p_p_prima.getCPtr(sWIGTYPE_p_p_prima));
    }

    public static int primaAdd(PRIMA prima, PRIM prim) {
        return JniSourceJNI.primaAdd(PRIMA.getCPtr(prima), prima, PRIM.getCPtr(prim), prim);
    }

    public static PRIM primaGet(PRIMA prima, int i) {
        long primaGet = JniSourceJNI.primaGet(PRIMA.getCPtr(prima), prima, i);
        if (primaGet == 0) {
            return null;
        }
        return new PRIM(primaGet, false);
    }

    public static int primaRemove(PRIMA prima, SWIGTYPE_p_p_prim sWIGTYPE_p_p_prim) {
        return JniSourceJNI.primaRemove(PRIMA.getCPtr(prima), prima, SWIGTYPE_p_p_prim.getCPtr(sWIGTYPE_p_p_prim));
    }

    public static void primaClear(PRIMA prima) {
        JniSourceJNI.primaClear(PRIMA.getCPtr(prima), prima);
    }

    public static int primaSort(PRIMA prima, SWIGTYPE_p_l_int32 sWIGTYPE_p_l_int32, SWIGTYPE_p_l_int32 sWIGTYPE_p_l_int322) {
        return JniSourceJNI.primaSort(PRIMA.getCPtr(prima), prima, SWIGTYPE_p_l_int32.getCPtr(sWIGTYPE_p_l_int32), SWIGTYPE_p_l_int32.getCPtr(sWIGTYPE_p_l_int322));
    }

    public static int primaSortByIndex(PRIMA prima, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        return JniSourceJNI.primaSortByIndex(PRIMA.getCPtr(prima), prima, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static void primaPrint(PRIMA prima) {
        JniSourceJNI.primaPrint(PRIMA.getCPtr(prima), prima);
    }

    public static symbol symCreate(PRIM prim, symbolType symboltype) {
        long symCreate = JniSourceJNI.symCreate(PRIM.getCPtr(prim), prim, symboltype.swigValue());
        if (symCreate == 0) {
            return null;
        }
        return new symbol(symCreate, false);
    }

    public static void symDestroy(SWIGTYPE_p_p_symbol sWIGTYPE_p_p_symbol) {
        JniSourceJNI.symDestroy(SWIGTYPE_p_p_symbol.getCPtr(sWIGTYPE_p_p_symbol));
    }

    public static symbol symClone(symbol symbol) {
        long symClone = JniSourceJNI.symClone(symbol.getCPtr(symbol), symbol);
        if (symClone == 0) {
            return null;
        }
        return new symbol(symClone, false);
    }

    public static int symMergeWithPrim(symbol symbol, PRIM prim) {
        return JniSourceJNI.symMergeWithPrim(symbol.getCPtr(symbol), symbol, PRIM.getCPtr(prim), prim);
    }

    public static int symIsDefaultClef(symbol symbol) {
        return JniSourceJNI.symIsDefaultClef(symbol.getCPtr(symbol), symbol);
    }

    public static void symPrint(symbol symbol) {
        JniSourceJNI.symPrint(symbol.getCPtr(symbol), symbol);
    }

    public static syma symaCreate(int i) {
        long symaCreate = JniSourceJNI.symaCreate(i);
        if (symaCreate == 0) {
            return null;
        }
        return new syma(symaCreate, false);
    }

    public static void symaDestroy(SWIGTYPE_p_p_syma sWIGTYPE_p_p_syma) {
        JniSourceJNI.symaDestroy(SWIGTYPE_p_p_syma.getCPtr(sWIGTYPE_p_p_syma));
    }

    public static int symaAdd(syma syma, symbol symbol) {
        return JniSourceJNI.symaAdd(syma.getCPtr(syma), syma, symbol.getCPtr(symbol), symbol);
    }

    public static symbol symaGet(syma syma, int i) {
        long symaGet = JniSourceJNI.symaGet(syma.getCPtr(syma), syma, i);
        if (symaGet == 0) {
            return null;
        }
        return new symbol(symaGet, false);
    }

    public static int symaGetIndex(syma syma, symbol symbol) {
        return JniSourceJNI.symaGetIndex(syma.getCPtr(syma), syma, symbol.getCPtr(symbol), symbol);
    }

    public static int symaRemove(syma syma, SWIGTYPE_p_p_symbol sWIGTYPE_p_p_symbol) {
        return JniSourceJNI.symaRemove(syma.getCPtr(syma), syma, SWIGTYPE_p_p_symbol.getCPtr(sWIGTYPE_p_p_symbol));
    }

    public static int symaSort(syma syma, SWIGTYPE_p_l_int32 sWIGTYPE_p_l_int32, SWIGTYPE_p_l_int32 sWIGTYPE_p_l_int322) {
        return JniSourceJNI.symaSort(syma.getCPtr(syma), syma, SWIGTYPE_p_l_int32.getCPtr(sWIGTYPE_p_l_int32), SWIGTYPE_p_l_int32.getCPtr(sWIGTYPE_p_l_int322));
    }

    public static int symaSortByIndex(syma syma, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        return JniSourceJNI.symaSortByIndex(syma.getCPtr(syma), syma, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static void symaPrint(syma syma) {
        JniSourceJNI.symaPrint(syma.getCPtr(syma), syma);
    }

    public static templ templCreate(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, primType primtype) {
        long templCreate = JniSourceJNI.templCreate(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), primtype.swigValue());
        if (templCreate == 0) {
            return null;
        }
        return new templ(templCreate, false);
    }

    public static void templDestroy(SWIGTYPE_p_p_templ sWIGTYPE_p_p_templ) {
        JniSourceJNI.templDestroy(SWIGTYPE_p_p_templ.getCPtr(sWIGTYPE_p_p_templ));
    }

    public static templ templClone(templ templ) {
        long templClone = JniSourceJNI.templClone(templ.getCPtr(templ), templ);
        if (templClone == 0) {
            return null;
        }
        return new templ(templClone, false);
    }

    public static templa templaCreate(int i) {
        long templaCreate = JniSourceJNI.templaCreate(i);
        if (templaCreate == 0) {
            return null;
        }
        return new templa(templaCreate, false);
    }

    public static void templaDestroy(SWIGTYPE_p_p_templa sWIGTYPE_p_p_templa) {
        JniSourceJNI.templaDestroy(SWIGTYPE_p_p_templa.getCPtr(sWIGTYPE_p_p_templa));
    }

    public static int templaAdd(templa templa, templ templ) {
        return JniSourceJNI.templaAdd(templa.getCPtr(templa), templa, templ.getCPtr(templ), templ);
    }

    public static templ templaGet(templa templa, int i) {
        long templaGet = JniSourceJNI.templaGet(templa.getCPtr(templa), templa, i);
        if (templaGet == 0) {
            return null;
        }
        return new templ(templaGet, false);
    }

    public static templa templaFindType(templa templa, primType primtype) {
        long templaFindType = JniSourceJNI.templaFindType(templa.getCPtr(templa), templa, primtype.swigValue());
        if (templaFindType == 0) {
            return null;
        }
        return new templa(templaFindType, false);
    }

    public static templa templaFindTypes(templa templa, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long templaFindTypes = JniSourceJNI.templaFindTypes(templa.getCPtr(templa), templa, SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (templaFindTypes == 0) {
            return null;
        }
        return new templa(templaFindTypes, false);
    }

    public static templateDic templateDicCreate(int i) {
        long templateDicCreate = JniSourceJNI.templateDicCreate(i);
        if (templateDicCreate == 0) {
            return null;
        }
        return new templateDic(templateDicCreate, false);
    }

    public static void templateDicDestroy(SWIGTYPE_p_p_templateDic sWIGTYPE_p_p_templateDic) {
        JniSourceJNI.templateDicDestroy(SWIGTYPE_p_p_templateDic.getCPtr(sWIGTYPE_p_p_templateDic));
    }

    public static int templateDicAdd(templateDic templatedic, primType primtype, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.templateDicAdd(templateDic.getCPtr(templatedic), templatedic, primtype.swigValue(), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static SWIGTYPE_p_PIXA templateDicGet(templateDic templatedic, primType primtype, SWIGTYPE_p_p_NUMA sWIGTYPE_p_p_NUMA) {
        long templateDicGet = JniSourceJNI.templateDicGet(templateDic.getCPtr(templatedic), templatedic, primtype.swigValue(), SWIGTYPE_p_p_NUMA.getCPtr(sWIGTYPE_p_p_NUMA));
        if (templateDicGet == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXA(templateDicGet, false);
    }

    public static staffline stafflineCreate(int i) {
        long stafflineCreate = JniSourceJNI.stafflineCreate(i);
        if (stafflineCreate == 0) {
            return null;
        }
        return new staffline(stafflineCreate, false);
    }

    public static int stafflineDestroy(SWIGTYPE_p_p_staffline sWIGTYPE_p_p_staffline) {
        return JniSourceJNI.stafflineDestroy(SWIGTYPE_p_p_staffline.getCPtr(sWIGTYPE_p_p_staffline));
    }

    public static int stafflineClear(staffline staffline) {
        return JniSourceJNI.stafflineClear(staffline.getCPtr(staffline), staffline);
    }

    public static int stafflineSet(staffline staffline, int i, int i2, int i3) {
        return JniSourceJNI.stafflineSet(staffline.getCPtr(staffline), staffline, i, i2, i3);
    }

    public static int stafflineGet(staffline staffline, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.stafflineGet(staffline.getCPtr(staffline), staffline, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int stafflineTopY(staffline staffline, int i) {
        return JniSourceJNI.stafflineTopY(staffline.getCPtr(staffline), staffline, i);
    }

    public static int stafflineBottomY(staffline staffline, int i) {
        return JniSourceJNI.stafflineBottomY(staffline.getCPtr(staffline), staffline, i);
    }

    public static float stafflineCenterY(staffline staffline, int i) {
        return JniSourceJNI.stafflineCenterY(staffline.getCPtr(staffline), staffline, i);
    }

    public static int stafflineHeight(staffline staffline, int i) {
        return JniSourceJNI.stafflineHeight(staffline.getCPtr(staffline), staffline, i);
    }

    public static void stafflinePrint(staffline staffline) {
        JniSourceJNI.stafflinePrint(staffline.getCPtr(staffline), staffline);
    }

    public static int stafflineIsXInRange(staffline staffline, int i) {
        return JniSourceJNI.stafflineIsXInRange(staffline.getCPtr(staffline), staffline, i);
    }

    public static staff staffCreate(int i) {
        long staffCreate = JniSourceJNI.staffCreate(i);
        if (staffCreate == 0) {
            return null;
        }
        return new staff(staffCreate, false);
    }

    public static int staffDestroy(SWIGTYPE_p_p_staff sWIGTYPE_p_p_staff) {
        return JniSourceJNI.staffDestroy(SWIGTYPE_p_p_staff.getCPtr(sWIGTYPE_p_p_staff));
    }

    public static SWIGTYPE_p_BOX staffBoundingBox(staff staff) {
        long staffBoundingBox = JniSourceJNI.staffBoundingBox(staff.getCPtr(staff), staff);
        if (staffBoundingBox == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(staffBoundingBox, false);
    }

    public static int staffXMax(staff staff) {
        return JniSourceJNI.staffXMax(staff.getCPtr(staff), staff);
    }

    public static int staffHeightAtX(staff staff, int i) {
        return JniSourceJNI.staffHeightAtX(staff.getCPtr(staff), staff, i);
    }

    public static float staffLineHeightAtX(staff staff, int i) {
        return JniSourceJNI.staffLineHeightAtX(staff.getCPtr(staff), staff, i);
    }

    public static float staffSpaceHeightAtX(staff staff, int i) {
        return JniSourceJNI.staffSpaceHeightAtX(staff.getCPtr(staff), staff, i);
    }

    public static float staffLineHeight(staff staff) {
        return JniSourceJNI.staffLineHeight(staff.getCPtr(staff), staff);
    }

    public static float staffSpaceHeight(staff staff) {
        return JniSourceJNI.staffSpaceHeight(staff.getCPtr(staff), staff);
    }

    public static int staffIsBoxOnLine(staff staff, SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        return JniSourceJNI.staffIsBoxOnLine(staff.getCPtr(staff), staff, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static int staffIsPointOnLine(staff staff, int i, float f) {
        return JniSourceJNI.staffIsPointOnLine(staff.getCPtr(staff), staff, i, f);
    }

    public static int staffIsPointOnLineAccurate(staff staff, int i, int i2) {
        return JniSourceJNI.staffIsPointOnLineAccurate(staff.getCPtr(staff), staff, i, i2);
    }

    public static int staffGetYIndex(staff staff, int i, float f) {
        return JniSourceJNI.staffGetYIndex(staff.getCPtr(staff), staff, i, f);
    }

    public static int staffGetYIndexChecked(staff staff, int i, float f) {
        return JniSourceJNI.staffGetYIndexChecked(staff.getCPtr(staff), staff, i, f);
    }

    public static float staffGetYByIndex(staff staff, int i, int i2) {
        return JniSourceJNI.staffGetYByIndex(staff.getCPtr(staff), staff, i, i2);
    }

    public static int staffLineYs(staff staff, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.staffLineYs(staff.getCPtr(staff), staff, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int staffYs(staff staff, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.staffYs(staff.getCPtr(staff), staff, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int staffAlignYToGrid(staff staff, int i, int i2) {
        return JniSourceJNI.staffAlignYToGrid(staff.getCPtr(staff), staff, i, i2);
    }

    public static int staffIsWithinXRange(staff staff, int i) {
        return JniSourceJNI.staffIsWithinXRange(staff.getCPtr(staff), staff, i);
    }

    public static int staffIsBoxWithinXRange(staff staff, SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        return JniSourceJNI.staffIsBoxWithinXRange(staff.getCPtr(staff), staff, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static void staffPrint(staff staff) {
        JniSourceJNI.staffPrint(staff.getCPtr(staff), staff);
    }

    public static staffa staffaCreate(int i) {
        long staffaCreate = JniSourceJNI.staffaCreate(i);
        if (staffaCreate == 0) {
            return null;
        }
        return new staffa(staffaCreate, false);
    }

    public static void staffaDestroy(SWIGTYPE_p_p_staffa sWIGTYPE_p_p_staffa) {
        JniSourceJNI.staffaDestroy(SWIGTYPE_p_p_staffa.getCPtr(sWIGTYPE_p_p_staffa));
    }

    public static int staffaAdd(staffa staffa, staff staff) {
        return JniSourceJNI.staffaAdd(staffa.getCPtr(staffa), staffa, staff.getCPtr(staff), staff);
    }

    public static staff staffaGet(staffa staffa, int i) {
        long staffaGet = JniSourceJNI.staffaGet(staffa.getCPtr(staffa), staffa, i);
        if (staffaGet == 0) {
            return null;
        }
        return new staff(staffaGet, false);
    }

    public static int staffaRemoveAtIndex(staffa staffa, int i) {
        return JniSourceJNI.staffaRemoveAtIndex(staffa.getCPtr(staffa), staffa, i);
    }

    public static void staffaPrint(staffa staffa) {
        JniSourceJNI.staffaPrint(staffa.getCPtr(staffa), staffa);
    }

    public static imat imatCreate(int i, int i2) {
        long imatCreate = JniSourceJNI.imatCreate(i, i2);
        if (imatCreate == 0) {
            return null;
        }
        return new imat(imatCreate, false);
    }

    public static int imatDestroy(SWIGTYPE_p_p_imat sWIGTYPE_p_p_imat) {
        return JniSourceJNI.imatDestroy(SWIGTYPE_p_p_imat.getCPtr(sWIGTYPE_p_p_imat));
    }

    public static int imatGet(imat imat, int i, int i2) {
        return JniSourceJNI.imatGet(imat.getCPtr(imat), imat, i, i2);
    }

    public static int imatSet(imat imat, int i, int i2, int i3) {
        return JniSourceJNI.imatSet(imat.getCPtr(imat), imat, i, i2, i3);
    }

    public static SWIGTYPE_p_NUMA imatGetCol(imat imat, int i) {
        long imatGetCol = JniSourceJNI.imatGetCol(imat.getCPtr(imat), imat, i);
        if (imatGetCol == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(imatGetCol, false);
    }

    public static int equalWithTolerance(double d, double d2, double d3) {
        return JniSourceJNI.equalWithTolerance(d, d2, d3);
    }

    public static int equalWithToleranceAbs(double d, double d2, double d3) {
        return JniSourceJNI.equalWithToleranceAbs(d, d2, d3);
    }

    public static SWIGTYPE_p_NUMA pixGetRow(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i) {
        long pixGetRow = JniSourceJNI.pixGetRow(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i);
        if (pixGetRow == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(pixGetRow, false);
    }

    public static int numaGetI(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.numaGetI(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int numaGetIndex(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.numaGetIndex(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int numaAddNumberDistinct(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.numaAddNumberDistinct(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int numaCopyNumbers(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA2) {
        return JniSourceJNI.numaCopyNumbers(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA2));
    }

    public static SWIGTYPE_p_NUMA numaGetIndexes(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        long numaGetIndexes = JniSourceJNI.numaGetIndexes(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
        if (numaGetIndexes == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(numaGetIndexes, false);
    }

    public static int numaThreshold(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, float f) {
        return JniSourceJNI.numaThreshold(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), f);
    }

    public static int numaCountEdges(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniSourceJNI.numaCountEdges(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaGetMinInRange(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.numaGetMinInRange(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static void saveHistogram(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, histogramFormat histogramformat, String str) {
        JniSourceJNI.saveHistogram(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), histogramformat.swigValue(), str);
    }

    public static void printNuma(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.printNuma(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static void printBox(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.printBox(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static void printVP(vanishingPoint vanishingpoint) {
        JniSourceJNI.printVP(vanishingPoint.getCPtr(vanishingpoint), vanishingpoint);
    }

    public static void pixRenderStaff(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff) {
        JniSourceJNI.pixRenderStaff(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff);
    }

    public static void pixRenderStaffOri(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        JniSourceJNI.pixRenderStaffOri(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static void pixRenderStaffOriAtScale(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83, double d) {
        JniSourceJNI.pixRenderStaffOriAtScale(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83), d);
    }

    public static void pixRenderStaffBW(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, double d) {
        JniSourceJNI.pixRenderStaffBW(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, d);
    }

    public static void pixRenderStaffa(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staffa staffa) {
        JniSourceJNI.pixRenderStaffa(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staffa.getCPtr(staffa), staffa);
    }

    public static void pixRenderPrim(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, PRIM prim, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        JniSourceJNI.pixRenderPrim(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), PRIM.getCPtr(prim), prim, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static void pixRenderPixa(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        JniSourceJNI.pixRenderPixa(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static void pixRenderTies(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, score score) {
        JniSourceJNI.pixRenderTies(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), score.getCPtr(score), score);
    }

    public static void pixRenderTiesAtScale(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, score score, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83, double d) {
        JniSourceJNI.pixRenderTiesAtScale(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), score.getCPtr(score), score, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83), d);
    }

    public static void pixRenderTiesForBara(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, bara bara) {
        JniSourceJNI.pixRenderTiesForBara(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), bara.getCPtr(bara), bara);
    }

    public static void pixRenderTiesForBaraAtScale(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, bara bara, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83, double d) {
        JniSourceJNI.pixRenderTiesForBaraAtScale(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), bara.getCPtr(bara), bara, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83), d);
    }

    public static void pixRenderTiesBW(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, score score, double d) {
        JniSourceJNI.pixRenderTiesBW(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), score.getCPtr(score), score, d);
    }

    public static void primGetRgb(PRIM prim, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        JniSourceJNI.primGetRgb(PRIM.getCPtr(prim), prim, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static void pixRenderSymWithOffsetAtScale(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, symbol symbol, int i, int i2, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83, double d) {
        JniSourceJNI.pixRenderSymWithOffsetAtScale(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), symbol.getCPtr(symbol), symbol, i, i2, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83), d);
    }

    public static void pixRenderPixWithOffsetAtScale(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, int i, int i2, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83, double d) {
        JniSourceJNI.pixRenderPixWithOffsetAtScale(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), i, i2, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83), d);
    }

    public static void pixRenderSymBW(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, symbol symbol, int i, int i2, double d) {
        JniSourceJNI.pixRenderSymBW(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), symbol.getCPtr(symbol), symbol, i, i2, d);
    }

    public static void pixRenderPixBW(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, int i, int i2, double d) {
        JniSourceJNI.pixRenderPixBW(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), i, i2, d);
    }

    public static void symGetRgb(symbol symbol, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        JniSourceJNI.symGetRgb(symbol.getCPtr(symbol), symbol, SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static int boxDoesIntersect(SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX2) {
        return JniSourceJNI.boxDoesIntersect(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX2));
    }

    public static String substring(String str, long j, long j2) {
        return JniSourceJNI.substring(str, j, j2);
    }

    public static String stringFormat(String str) {
        return JniSourceJNI.stringFormat(str);
    }

    public static String stringFormatV(String str, String str2) {
        return JniSourceJNI.stringFormatV(str, str2);
    }

    public static SWIGTYPE_p_PIXA pixaScale(SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, float f) {
        long pixaScale = JniSourceJNI.pixaScale(SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), f);
        if (pixaScale == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXA(pixaScale, false);
    }

    public static SWIGTYPE_p_PIX pixRenderReducedToLines(imat imat, int i) {
        long pixRenderReducedToLines = JniSourceJNI.pixRenderReducedToLines(imat.getCPtr(imat), imat, i);
        if (pixRenderReducedToLines == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(pixRenderReducedToLines, false);
    }

    public static int pixRenderReducedToLines2(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, imat imat, int i) {
        return JniSourceJNI.pixRenderReducedToLines2(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), imat.getCPtr(imat), imat, i);
    }

    public static int pixRenderPtaa(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA) {
        return JniSourceJNI.pixRenderPtaa(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA));
    }

    public static int pixRenderPtaaToLines(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint8, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint82, SWIGTYPE_p_l_uint8 sWIGTYPE_p_l_uint83) {
        return JniSourceJNI.pixRenderPtaaToLines(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint8), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint82), SWIGTYPE_p_l_uint8.getCPtr(sWIGTYPE_p_l_uint83));
    }

    public static int renderVanishingPoint(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA, vanishingPoint vanishingpoint) {
        return JniSourceJNI.renderVanishingPoint(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA), vanishingPoint.getCPtr(vanishingpoint), vanishingpoint);
    }

    public static int printPta(SWIGTYPE_p_PTA sWIGTYPE_p_PTA) {
        return JniSourceJNI.printPta(SWIGTYPE_p_PTA.getCPtr(sWIGTYPE_p_PTA));
    }

    public static int printPtaa(SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA) {
        return JniSourceJNI.printPtaa(SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA));
    }

    public static templa templaLoad(float f) {
        long templaLoad = JniSourceJNI.templaLoad(f);
        if (templaLoad == 0) {
            return null;
        }
        return new templa(templaLoad, false);
    }

    public static templa templaLoadByStaffHeight(int i) {
        long templaLoadByStaffHeight = JniSourceJNI.templaLoadByStaffHeight(i);
        if (templaLoadByStaffHeight == 0) {
            return null;
        }
        return new templa(templaLoadByStaffHeight, false);
    }

    public static templateDic loadTemplates(float f) {
        long loadTemplates = JniSourceJNI.loadTemplates(f);
        if (loadTemplates == 0) {
            return null;
        }
        return new templateDic(loadTemplates, false);
    }

    public static templateDic loadTemplatesByStaffHeight(int i) {
        long loadTemplatesByStaffHeight = JniSourceJNI.loadTemplatesByStaffHeight(i);
        if (loadTemplatesByStaffHeight == 0) {
            return null;
        }
        return new templateDic(loadTemplatesByStaffHeight, false);
    }

    public static void printPixToTmp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.printPixToTmp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void printStaffToTmp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff) {
        JniSourceJNI.printStaffToTmp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff);
    }

    public static void printBoxToTmp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.printBoxToTmp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static void printBoxaToTmp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA) {
        JniSourceJNI.printBoxaToTmp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA));
    }

    public static void printPrimaToTmp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, PRIMA prima, primType primtype) {
        JniSourceJNI.printPrimaToTmp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), PRIMA.getCPtr(prima), prima, primtype.swigValue());
    }

    public static SWIGTYPE_p_NUMA boxaFindIndexesByBB(SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, int i) {
        long boxaFindIndexesByBB = JniSourceJNI.boxaFindIndexesByBB(SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), i);
        if (boxaFindIndexesByBB == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(boxaFindIndexesByBB, false);
    }

    public static SWIGTYPE_p_BOX boxaBoundingRegion(SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA) {
        long boxaBoundingRegion = JniSourceJNI.boxaBoundingRegion(SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA));
        if (boxaBoundingRegion == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(boxaBoundingRegion, false);
    }

    public static SWIGTYPE_p_BOX boxaBoundingRegionByIndexes(SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        long boxaBoundingRegionByIndexes = JniSourceJNI.boxaBoundingRegionByIndexes(SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
        if (boxaBoundingRegionByIndexes == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(boxaBoundingRegionByIndexes, false);
    }

    public static SWIGTYPE_p_PIX pixaMerge(SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_p_BOX sWIGTYPE_p_p_BOX) {
        long pixaMerge = JniSourceJNI.pixaMerge(SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_p_BOX.getCPtr(sWIGTYPE_p_p_BOX));
        if (pixaMerge == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(pixaMerge, false);
    }

    public static SWIGTYPE_p_PIX pixaMergeByIndexes(SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, SWIGTYPE_p_p_BOX sWIGTYPE_p_p_BOX) {
        long pixaMergeByIndexes = JniSourceJNI.pixaMergeByIndexes(SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), SWIGTYPE_p_p_BOX.getCPtr(sWIGTYPE_p_p_BOX));
        if (pixaMergeByIndexes == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(pixaMergeByIndexes, false);
    }

    public static int pixErasePrim(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, PRIM prim) {
        return JniSourceJNI.pixErasePrim(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), PRIM.getCPtr(prim), prim);
    }

    public static int calculateLineParamsLS(SWIGTYPE_p_PTA sWIGTYPE_p_PTA, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_double sWIGTYPE_p_double2) {
        return JniSourceJNI.calculateLineParamsLS(SWIGTYPE_p_PTA.getCPtr(sWIGTYPE_p_PTA), SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double2));
    }

    public static int calculateLineParamsLS2(SWIGTYPE_p_PTA sWIGTYPE_p_PTA, int i, int i2, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_double sWIGTYPE_p_double2) {
        return JniSourceJNI.calculateLineParamsLS2(SWIGTYPE_p_PTA.getCPtr(sWIGTYPE_p_PTA), i, i2, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double2));
    }

    public static int doLinesOverlap(int i, int i2, int i3, int i4) {
        return JniSourceJNI.doLinesOverlap(i, i2, i3, i4);
    }

    public static SWIGTYPE_p_PIX createSkewedLinePix(int i, int i2, int i3) {
        long createSkewedLinePix = JniSourceJNI.createSkewedLinePix(i, i2, i3);
        if (createSkewedLinePix == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(createSkewedLinePix, false);
    }

    public static SWIGTYPE_p_BOX boxScaleChecked(SWIGTYPE_p_BOX sWIGTYPE_p_BOX, float f) {
        long boxScaleChecked = JniSourceJNI.boxScaleChecked(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), f);
        if (boxScaleChecked == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(boxScaleChecked, false);
    }

    public static SWIGTYPE_p_PIX pixScaleChecked(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, float f) {
        long pixScaleChecked = JniSourceJNI.pixScaleChecked(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), f);
        if (pixScaleChecked == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(pixScaleChecked, false);
    }

    public static void numaSortAndDeduplicate(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.numaSortAndDeduplicate(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static int roundI(SWIGTYPE_p_long_double sWIGTYPE_p_long_double) {
        return JniSourceJNI.roundI(SWIGTYPE_p_long_double.getCPtr(sWIGTYPE_p_long_double));
    }

    public static void printTimeElapsed(SWIGTYPE_p_timeval sWIGTYPE_p_timeval) {
        JniSourceJNI.printTimeElapsed(SWIGTYPE_p_timeval.getCPtr(sWIGTYPE_p_timeval));
    }

    public static void printTime() {
        JniSourceJNI.printTime();
    }

    public static dicipair dicipairCreate(int i, int i2) {
        long dicipairCreate = JniSourceJNI.dicipairCreate(i, i2);
        if (dicipairCreate == 0) {
            return null;
        }
        return new dicipair(dicipairCreate, false);
    }

    public static void dicipairDestroy(SWIGTYPE_p_p_dicipair sWIGTYPE_p_p_dicipair) {
        JniSourceJNI.dicipairDestroy(SWIGTYPE_p_p_dicipair.getCPtr(sWIGTYPE_p_p_dicipair));
    }

    public static dici diciCreate() {
        long diciCreate = JniSourceJNI.diciCreate();
        if (diciCreate == 0) {
            return null;
        }
        return new dici(diciCreate, false);
    }

    public static dici diciCreateWithSize(int i) {
        long diciCreateWithSize = JniSourceJNI.diciCreateWithSize(i);
        if (diciCreateWithSize == 0) {
            return null;
        }
        return new dici(diciCreateWithSize, false);
    }

    public static void diciDestroy(SWIGTYPE_p_p_dici sWIGTYPE_p_p_dici) {
        JniSourceJNI.diciDestroy(SWIGTYPE_p_p_dici.getCPtr(sWIGTYPE_p_p_dici));
    }

    public static int diciGetValue(dici dici, int i) {
        return JniSourceJNI.diciGetValue(dici.getCPtr(dici), dici, i);
    }

    public static int diciSet(dici dici, int i, int i2) {
        return JniSourceJNI.diciSet(dici.getCPtr(dici), dici, i, i2);
    }

    public static void diciPrint(dici dici) {
        JniSourceJNI.diciPrint(dici.getCPtr(dici), dici);
    }

    public static SWIGTYPE_p_diciIterator diciIteratorCreate(dici dici) {
        long diciIteratorCreate = JniSourceJNI.diciIteratorCreate(dici.getCPtr(dici), dici);
        if (diciIteratorCreate == 0) {
            return null;
        }
        return new SWIGTYPE_p_diciIterator(diciIteratorCreate, false);
    }

    public static void diciIteratorDestroy(SWIGTYPE_p_p_diciIterator sWIGTYPE_p_p_diciIterator) {
        JniSourceJNI.diciIteratorDestroy(SWIGTYPE_p_p_diciIterator.getCPtr(sWIGTYPE_p_p_diciIterator));
    }

    public static dicipair diciIteratorNext(SWIGTYPE_p_diciIterator sWIGTYPE_p_diciIterator) {
        long diciIteratorNext = JniSourceJNI.diciIteratorNext(SWIGTYPE_p_diciIterator.getCPtr(sWIGTYPE_p_diciIterator));
        if (diciIteratorNext == 0) {
            return null;
        }
        return new dicipair(diciIteratorNext, false);
    }

    public static int scoreFindAndInterpretTies(score score, staffa staffa, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, syma syma) {
        return JniSourceJNI.scoreFindAndInterpretTies(score.getCPtr(score), score, staffa.getCPtr(staffa), staffa, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), syma.getCPtr(syma), syma);
    }

    public static point ptMake(int i, int i2) {
        return new point(JniSourceJNI.ptMake(i, i2), true);
    }

    public static SWIGTYPE_p_PIX binarize(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long binarize = JniSourceJNI.binarize(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (binarize == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(binarize, false);
    }

    public static int calculateLine(point point, point point2, line line) {
        return JniSourceJNI.calculateLine(point.getCPtr(point), point, point.getCPtr(point2), point2, line.getCPtr(line), line);
    }

    public static int fixPerspective(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX, SWIGTYPE_p_int sWIGTYPE_p_int, vanishingPoint vanishingpoint, vanishingPoint vanishingpoint2, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniSourceJNI.fixPerspective(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), vanishingPoint.getCPtr(vanishingpoint), vanishingpoint, vanishingPoint.getCPtr(vanishingpoint2), vanishingpoint2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int preprocess0(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX2, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX3, SWIGTYPE_p_p_BOX sWIGTYPE_p_p_BOX, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniSourceJNI.preprocess0(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX2), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX3), SWIGTYPE_p_p_BOX.getCPtr(sWIGTYPE_p_p_BOX), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int preprocess(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniSourceJNI.preprocess(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX), SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixIsBW(SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.pixIsBW(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static int serializeInt(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        return JniSourceJNI.serializeInt(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
    }

    public static int serializeDouble(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, double d) {
        return JniSourceJNI.serializeDouble(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), d);
    }

    public static int serializeBox(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, int i) {
        return JniSourceJNI.serializeBox(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), i);
    }

    public static int serializePix(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i) {
        return JniSourceJNI.serializePix(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i);
    }

    public static int serializeNuma(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i) {
        return JniSourceJNI.serializeNuma(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i);
    }

    public static int serializeTimeSignature(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, timeSignature timesignature, int i) {
        return JniSourceJNI.serializeTimeSignature(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), timeSignature.getCPtr(timesignature), timesignature, i);
    }

    public static int serializeKeySignature(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, keySignature keysignature, int i) {
        return JniSourceJNI.serializeKeySignature(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), keySignature.getCPtr(keysignature), keysignature, i);
    }

    public static int serializeSound(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, sound sound, int i) {
        return JniSourceJNI.serializeSound(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), sound.getCPtr(sound), sound, i);
    }

    public static int serializeTP(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, timepoint timepoint, int i) {
        return JniSourceJNI.serializeTP(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), timepoint.getCPtr(timepoint), timepoint, i);
    }

    public static int serializeBar(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, bar bar, int i) {
        return JniSourceJNI.serializeBar(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), bar.getCPtr(bar), bar, i);
    }

    public static int serializeBara(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, bara bara, int i) {
        return JniSourceJNI.serializeBara(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), bara.getCPtr(bara), bara, i);
    }

    public static int serializeBaraa(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, baraa baraa, int i) {
        return JniSourceJNI.serializeBaraa(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), baraa.getCPtr(baraa), baraa, i);
    }

    public static int serializeScore(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, score score, int i) {
        return JniSourceJNI.serializeScore(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), score.getCPtr(score), score, i);
    }

    public static int serializeSession(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, session session, int i) {
        return JniSourceJNI.serializeSession(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), session.getCPtr(session), session, i);
    }

    public static int deserializeInt(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        return JniSourceJNI.deserializeInt(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public static double deserializeDouble(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        return JniSourceJNI.deserializeDouble(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public static SWIGTYPE_p_BOX deserializeBox(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeBox = JniSourceJNI.deserializeBox(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeBox == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(deserializeBox, false);
    }

    public static SWIGTYPE_p_PIX deserializePix(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializePix = JniSourceJNI.deserializePix(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializePix == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIX(deserializePix, false);
    }

    public static SWIGTYPE_p_NUMA deserializeNuma(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeNuma = JniSourceJNI.deserializeNuma(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeNuma == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMA(deserializeNuma, false);
    }

    public static timeSignature deserializeTimeSignature(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeTimeSignature = JniSourceJNI.deserializeTimeSignature(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeTimeSignature == 0) {
            return null;
        }
        return new timeSignature(deserializeTimeSignature, false);
    }

    public static keySignature deserializeKeySignature(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeKeySignature = JniSourceJNI.deserializeKeySignature(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeKeySignature == 0) {
            return null;
        }
        return new keySignature(deserializeKeySignature, false);
    }

    public static sound deserializeSound(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeSound = JniSourceJNI.deserializeSound(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeSound == 0) {
            return null;
        }
        return new sound(deserializeSound, false);
    }

    public static timepoint deserializeTP(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeTP = JniSourceJNI.deserializeTP(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeTP == 0) {
            return null;
        }
        return new timepoint(deserializeTP, false);
    }

    public static bar deserializeBar(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeBar = JniSourceJNI.deserializeBar(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeBar == 0) {
            return null;
        }
        return new bar(deserializeBar, false);
    }

    public static bara deserializeBara(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeBara = JniSourceJNI.deserializeBara(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeBara == 0) {
            return null;
        }
        return new bara(deserializeBara, false);
    }

    public static baraa deserializeBaraa(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeBaraa = JniSourceJNI.deserializeBaraa(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeBaraa == 0) {
            return null;
        }
        return new baraa(deserializeBaraa, false);
    }

    public static score deserializeScore(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeScore = JniSourceJNI.deserializeScore(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeScore == 0) {
            return null;
        }
        return new score(deserializeScore, false);
    }

    public static session deserializeSession(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long deserializeSession = JniSourceJNI.deserializeSession(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (deserializeSession == 0) {
            return null;
        }
        return new session(deserializeSession, false);
    }

    public static int serialize(session session, String str) {
        return JniSourceJNI.serialize(session.getCPtr(session), session, str);
    }

    public static session deserialize(String str) {
        long deserialize = JniSourceJNI.deserialize(str);
        if (deserialize == 0) {
            return null;
        }
        return new session(deserialize, false);
    }

    public static int serializeBoxV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, int i, int i2) {
        return JniSourceJNI.serializeBoxV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), i, i2);
    }

    public static int serializePixV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, int i2) {
        return JniSourceJNI.serializePixV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, i2);
    }

    public static int serializeNumaV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, int i2) {
        return JniSourceJNI.serializeNumaV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, i2);
    }

    public static int serializeTimeSignatureV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, timeSignature timesignature, int i, int i2) {
        return JniSourceJNI.serializeTimeSignatureV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), timeSignature.getCPtr(timesignature), timesignature, i, i2);
    }

    public static int serializeKeySignatureV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, keySignature keysignature, int i, int i2) {
        return JniSourceJNI.serializeKeySignatureV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), keySignature.getCPtr(keysignature), keysignature, i, i2);
    }

    public static int serializeSoundV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, sound sound, int i, int i2) {
        return JniSourceJNI.serializeSoundV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), sound.getCPtr(sound), sound, i, i2);
    }

    public static int serializeTPV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, timepoint timepoint, int i, int i2) {
        return JniSourceJNI.serializeTPV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), timepoint.getCPtr(timepoint), timepoint, i, i2);
    }

    public static int serializeBarV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, bar bar, int i, int i2) {
        return JniSourceJNI.serializeBarV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), bar.getCPtr(bar), bar, i, i2);
    }

    public static int serializeBaraV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, bara bara, int i, int i2) {
        return JniSourceJNI.serializeBaraV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), bara.getCPtr(bara), bara, i, i2);
    }

    public static int serializeBaraaV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, baraa baraa, int i, int i2) {
        return JniSourceJNI.serializeBaraaV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), baraa.getCPtr(baraa), baraa, i, i2);
    }

    public static int serializeScoreV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, score score, int i, int i2) {
        return JniSourceJNI.serializeScoreV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), score.getCPtr(score), score, i, i2);
    }

    public static int serializeSessionV(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, session session, int i, int i2) {
        return JniSourceJNI.serializeSessionV(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), session.getCPtr(session), session, i, i2);
    }

    public static int serializeV(session session, String str, double d) {
        return JniSourceJNI.serializeV(session.getCPtr(session), session, str, d);
    }

    public static int staffRemoveWithScan(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, int i, int i2) {
        return JniSourceJNI.staffRemoveWithScan(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, i, i2);
    }

    public static int staffSimpleClear(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, int i) {
        return JniSourceJNI.staffSimpleClear(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, i);
    }

    public static int getPREPROCESSOR_DISTORTION_NOT_RECOGNIZED() {
        return JniSourceJNI.PREPROCESSOR_DISTORTION_NOT_RECOGNIZED_get();
    }

    public static int getPREPROCESSOR_DISTORTION_TOO_HIGH() {
        return JniSourceJNI.PREPROCESSOR_DISTORTION_TOO_HIGH_get();
    }

    public static int getPREPROCESSOR_RESOLUTION_LOW() {
        return JniSourceJNI.PREPROCESSOR_RESOLUTION_LOW_get();
    }

    public static int getPREPROCESSOR_PIX_EMPTY() {
        return JniSourceJNI.PREPROCESSOR_PIX_EMPTY_get();
    }

    public static int getPREPROCESSOR_DISTORTED() {
        return JniSourceJNI.PREPROCESSOR_DISTORTED_get();
    }

    public static int getPREPROCESSOR_ROTATED() {
        return JniSourceJNI.PREPROCESSOR_ROTATED_get();
    }

    public static int getIMAT_LINE() {
        return JniSourceJNI.IMAT_LINE_get();
    }

    public static int getIMAT_GAP() {
        return JniSourceJNI.IMAT_GAP_get();
    }

    public static int getIMAT_LINE_CHECKED() {
        return JniSourceJNI.IMAT_LINE_CHECKED_get();
    }

    public static int getIMAT_GAP_WIDE() {
        return JniSourceJNI.IMAT_GAP_WIDE_get();
    }

    public static int getRLE_CONVEX() {
        return JniSourceJNI.RLE_CONVEX_get();
    }

    public static int getRLE_CONCAVE() {
        return JniSourceJNI.RLE_CONCAVE_get();
    }

    public static int getRLE_NOT_RECOGNIZED() {
        return JniSourceJNI.RLE_NOT_RECOGNIZED_get();
    }

    public static int exportToMusicXml(String str, session session, String str2, String str3, int i, int i2, int i3, int i4) {
        return JniSourceJNI.exportToMusicXml(str, session.getCPtr(session), session, str2, str3, i, i2, i3, i4);
    }

    public static int exportToMusicXml2(String str, session session, String str2, int i, String[] strArr, int[] iArr, int[] iArr2, boolean[][] zArr) {
        return JniSourceJNI.exportToMusicXml2(str, session.getCPtr(session), session, str2, i, strArr, iArr, iArr2, zArr);
    }

    public static int logShouldLog(SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        return JniSourceJNI.logShouldLog(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static void logMsg(String str, String str2) {
        JniSourceJNI.logMsg(str, str2);
    }

    public static void logMsgFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX) {
        JniSourceJNI.logMsgFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX));
    }

    public static void logMsgFocused2(String str, String str2, int i, int i2, int i3, int i4) {
        JniSourceJNI.logMsgFocused2(str, str2, i, i2, i3, i4);
    }

    public static void logNumaFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.logNumaFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static void logNumaFFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA) {
        JniSourceJNI.logNumaFFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA));
    }

    public static void logPix(String str, String str2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logPix(str, str2, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logPixFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logPixFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBox(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBox(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBoxFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBoxFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX2), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBoxFocused2(String str, String str2, int i, int i2, int i3, int i4, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBoxFocused2(str, str2, i, i2, i3, i4, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBoxa(String str, String str2, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBoxa(str, str2, SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBoxaFocusedDefault(String str, String str2, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBoxaFocusedDefault(str, str2, SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logBoxaFocused(String str, String str2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        JniSourceJNI.logBoxaFocused(str, str2, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static void logPrimaMatchesFocused(String str, String str2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, PRIMA prima, primType primtype) {
        JniSourceJNI.logPrimaMatchesFocused(str, str2, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), PRIMA.getCPtr(prima), prima, primtype.swigValue());
    }

    public static void logStaff(String str, String str2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff) {
        JniSourceJNI.logStaff(str, str2, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff);
    }

    public static void logImatFocused(String str, String str2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, int i2, imat imat, int i3) {
        JniSourceJNI.logImatFocused(str, str2, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, i2, imat.getCPtr(imat), imat, i3);
    }

    public static SWIGTYPE_p_BOX findShortRest(staff staff, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, primType primtype, float f, float f2) {
        long findShortRest = JniSourceJNI.findShortRest(staff.getCPtr(staff), staff, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA2), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), primtype.swigValue(), f, f2);
        if (findShortRest == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(findShortRest, false);
    }

    public static SWIGTYPE_p_BOX isSharp(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, staff staff, int i, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, int i2, int i3, SWIGTYPE_p_p_prim sWIGTYPE_p_p_prim, SWIGTYPE_p_float sWIGTYPE_p_float) {
        long isSharp = JniSourceJNI.isSharp(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), staff.getCPtr(staff), staff, i, SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), i2, i3, SWIGTYPE_p_p_prim.getCPtr(sWIGTYPE_p_p_prim), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
        if (isSharp == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(isSharp, false);
    }

    public static SWIGTYPE_p_BOX isNatural(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, staff staff, int i, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, int i2, int i3, float f, SWIGTYPE_p_p_prim sWIGTYPE_p_p_prim, SWIGTYPE_p_float sWIGTYPE_p_float) {
        long isNatural = JniSourceJNI.isNatural(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), staff.getCPtr(staff), staff, i, SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), i2, i3, f, SWIGTYPE_p_p_prim.getCPtr(sWIGTYPE_p_p_prim), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
        if (isNatural == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(isNatural, false);
    }

    public static SWIGTYPE_p_BOX isCrotchet(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, staff staff, templateDic templatedic) {
        long isCrotchet = JniSourceJNI.isCrotchet(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), staff.getCPtr(staff), staff, templateDic.getCPtr(templatedic), templatedic);
        if (isCrotchet == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(isCrotchet, false);
    }

    public static SWIGTYPE_p_BOX isWholeNoteHead(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, staff staff, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, PRIMA prima, templateDic templatedic, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long isWholeNoteHead = JniSourceJNI.isWholeNoteHead(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), staff.getCPtr(staff), staff, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), PRIMA.getCPtr(prima), prima, templateDic.getCPtr(templatedic), templatedic, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (isWholeNoteHead == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(isWholeNoteHead, false);
    }

    public static SWIGTYPE_p_BOXA findStaffLines(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, staff staff, int i) {
        long findStaffLines = JniSourceJNI.findStaffLines(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), staff.getCPtr(staff), staff, i);
        if (findStaffLines == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOXA(findStaffLines, false);
    }

    public static SWIGTYPE_p_BOX isTimeSignature(staff staff, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_TessBaseAPI sWIGTYPE_p_TessBaseAPI, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        long isTimeSignature = JniSourceJNI.isTimeSignature(staff.getCPtr(staff), staff, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_TessBaseAPI.getCPtr(sWIGTYPE_p_TessBaseAPI), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
        if (isTimeSignature == 0) {
            return null;
        }
        return new SWIGTYPE_p_BOX(isTimeSignature, false);
    }

    public static int isDurationDot(SWIGTYPE_p_BOX sWIGTYPE_p_BOX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staff staff, SWIGTYPE_p_BOX sWIGTYPE_p_BOX2) {
        return JniSourceJNI.isDurationDot(SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staff.getCPtr(staff), staff, SWIGTYPE_p_BOX.getCPtr(sWIGTYPE_p_BOX2));
    }

    public static PRIM recognizeClefAlto(staff staff, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA, SWIGTYPE_p_BOXA sWIGTYPE_p_BOXA) {
        long recognizeClefAlto = JniSourceJNI.recognizeClefAlto(staff.getCPtr(staff), staff, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA), SWIGTYPE_p_BOXA.getCPtr(sWIGTYPE_p_BOXA));
        if (recognizeClefAlto == 0) {
            return null;
        }
        return new PRIM(recognizeClefAlto, false);
    }

    public static PRIMA findPrimitives(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_PIX sWIGTYPE_p_PIX2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX3, PRIMA prima, staff staff, templateDic templatedic, SWIGTYPE_p_p_PIX sWIGTYPE_p_p_PIX, SWIGTYPE_p_p_BOXA sWIGTYPE_p_p_BOXA, SWIGTYPE_p_p_PIXA sWIGTYPE_p_p_PIXA) {
        long findPrimitives = JniSourceJNI.findPrimitives(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX2), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX3), PRIMA.getCPtr(prima), prima, staff.getCPtr(staff), staff, templateDic.getCPtr(templatedic), templatedic, SWIGTYPE_p_p_PIX.getCPtr(sWIGTYPE_p_p_PIX), SWIGTYPE_p_p_BOXA.getCPtr(sWIGTYPE_p_p_BOXA), SWIGTYPE_p_p_PIXA.getCPtr(sWIGTYPE_p_p_PIXA));
        if (findPrimitives == 0) {
            return null;
        }
        return new PRIMA(findPrimitives, false);
    }

    public static staffa staffaDetect(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long staffaDetect = JniSourceJNI.staffaDetect(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (staffaDetect == 0) {
            return null;
        }
        return new staffa(staffaDetect, false);
    }

    public static SWIGTYPE_p_PIXA staffaSplitPixByCenters(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staffa staffa) {
        long staffaSplitPixByCenters = JniSourceJNI.staffaSplitPixByCenters(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staffa.getCPtr(staffa), staffa);
        if (staffaSplitPixByCenters == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXA(staffaSplitPixByCenters, false);
    }

    public static SWIGTYPE_p_PIXA staffaSplitPixByMinima(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staffa staffa) {
        long staffaSplitPixByMinima = JniSourceJNI.staffaSplitPixByMinima(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staffa.getCPtr(staffa), staffa);
        if (staffaSplitPixByMinima == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXA(staffaSplitPixByMinima, false);
    }

    public static SWIGTYPE_p_PIXA pixSplitByStaffPixa(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, staffa staffa, SWIGTYPE_p_PIXA sWIGTYPE_p_PIXA) {
        long pixSplitByStaffPixa = JniSourceJNI.pixSplitByStaffPixa(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), staffa.getCPtr(staffa), staffa, SWIGTYPE_p_PIXA.getCPtr(sWIGTYPE_p_PIXA));
        if (pixSplitByStaffPixa == 0) {
            return null;
        }
        return new SWIGTYPE_p_PIXA(pixSplitByStaffPixa, false);
    }

    public static imat pixReduceToLines(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, int i, float f, float f2) {
        long pixReduceToLines = JniSourceJNI.pixReduceToLines(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), i, f, f2);
        if (pixReduceToLines == 0) {
            return null;
        }
        return new imat(pixReduceToLines, false);
    }

    public static int imatFixGaps(imat imat, int i) {
        return JniSourceJNI.imatFixGaps(imat.getCPtr(imat), imat, i);
    }

    public static SWIGTYPE_p_PTAA findLines(imat imat, float f, int i, int i2, int i3, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        long findLines = JniSourceJNI.findLines(imat.getCPtr(imat), imat, f, i, i2, i3, SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
        if (findLines == 0) {
            return null;
        }
        return new SWIGTYPE_p_PTAA(findLines, false);
    }

    public static int findLineEnd(SWIGTYPE_p_PTA sWIGTYPE_p_PTA, SWIGTYPE_p_p_void sWIGTYPE_p_p_void, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, int i, int i2) {
        return JniSourceJNI.findLineEnd(SWIGTYPE_p_PTA.getCPtr(sWIGTYPE_p_PTA), SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), i, i2);
    }

    public static int fixLineEnds(SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        return JniSourceJNI.fixLineEnds(SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA), SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX));
    }

    public static SWIGTYPE_p_PTAA orderLinesByY(SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA) {
        long orderLinesByY = JniSourceJNI.orderLinesByY(SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA));
        if (orderLinesByY == 0) {
            return null;
        }
        return new SWIGTYPE_p_PTAA(orderLinesByY, false);
    }

    public static int scalePtaa(SWIGTYPE_p_PTAA sWIGTYPE_p_PTAA, double d, double d2, int i, int i2) {
        return JniSourceJNI.scalePtaa(SWIGTYPE_p_PTAA.getCPtr(sWIGTYPE_p_PTAA), d, d2, i, i2);
    }

    public static SWIGTYPE_p_NUMAA findGroups(SWIGTYPE_p_NUMA sWIGTYPE_p_NUMA, int i, int i2, int i3, int i4) {
        long findGroups = JniSourceJNI.findGroups(SWIGTYPE_p_NUMA.getCPtr(sWIGTYPE_p_NUMA), i, i2, i3, i4);
        if (findGroups == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMAA(findGroups, false);
    }

    public static SWIGTYPE_p_NUMAA findLineGroups(SWIGTYPE_p_NUMA2D sWIGTYPE_p_NUMA2D, imat imat, int i) {
        long findLineGroups = JniSourceJNI.findLineGroups(SWIGTYPE_p_NUMA2D.getCPtr(sWIGTYPE_p_NUMA2D), imat.getCPtr(imat), imat, i);
        if (findLineGroups == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMAA(findLineGroups, false);
    }

    public static SWIGTYPE_p_NUMAA removeVerticalOverlaps(SWIGTYPE_p_NUMAA sWIGTYPE_p_NUMAA, imat imat, int i) {
        long removeVerticalOverlaps = JniSourceJNI.removeVerticalOverlaps(SWIGTYPE_p_NUMAA.getCPtr(sWIGTYPE_p_NUMAA), imat.getCPtr(imat), imat, i);
        if (removeVerticalOverlaps == 0) {
            return null;
        }
        return new SWIGTYPE_p_NUMAA(removeVerticalOverlaps, false);
    }

    public static Pix readBitmap(Bitmap bitmap) {
        long nativeReadBitmap = JniSourceJNI.nativeReadBitmap(bitmap);
        if (nativeReadBitmap == 0) {
            return null;
        }
        return new Pix(nativeReadBitmap, false);
    }

    public static Bitmap writeBitmap(Pix pix, boolean z) {
        if (pix != null) {
            Bitmap createBitmap = Bitmap.createBitmap((int) pix.getW(), (int) pix.getH(), Bitmap.Config.ARGB_8888);
            if (JniSourceJNI.nativeWriteBitmap(Pix.getCPtr(pix), createBitmap, z)) {
                return createBitmap;
            }
            createBitmap.recycle();
            return null;
        }
        throw new IllegalArgumentException("Source pix must be non-null");
    }

    public static analysisResult arCreate(int i) {
        long arCreate = JniSourceJNI.arCreate(i);
        if (arCreate == 0) {
            return null;
        }
        return new analysisResult(arCreate, false);
    }

    public static void arDestroy(SWIGTYPE_p_p_analysisResult sWIGTYPE_p_p_analysisResult) {
        JniSourceJNI.arDestroy(SWIGTYPE_p_p_analysisResult.getCPtr(sWIGTYPE_p_p_analysisResult));
    }

    public static analysisResult analyze(Scanner scanner, String str, String str2, String str3, String str4, float f, float f2, SWIGTYPE_p_PIX sWIGTYPE_p_PIX, SWIGTYPE_p_p_session sWIGTYPE_p_p_session, int i, String str5, int i2) {
        long j;
        long cPtr = SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX);
        if (sWIGTYPE_p_p_session == null) {
            j = 0;
        } else {
            j = SWIGTYPE_p_p_session.getCPtr(sWIGTYPE_p_p_session);
        }
        String str6 = LOGTAG;
        StringBuilder sb = new StringBuilder("analyze() - sessionPtr: ");
        sb.append(j);
        sb.append(" insertAtIndex: ");
        int i3 = i;
        sb.append(i3);
        Log.d(str6, sb.toString());
        long nativeAnalyze = JniSourceJNI.nativeAnalyze(scanner, f, f2, cPtr, str, str2, str3, str4, j, i3, str5, i2);
        if (nativeAnalyze == 0) {
            return null;
        }
        return new analysisResult(nativeAnalyze, false);
    }

    public static int sessionStateSetCurrentBar(session session, score score, bar bar, int i) {
        return JniSourceJNI.sessionStateSetCurrentBar(session.getCPtr(session), score.getCPtr(score), bar.getCPtr(bar), i);
    }

    public static playerListNode sessionStateMoveToNextBar(session session) {
        long sessionStateMoveToNextBar = JniSourceJNI.sessionStateMoveToNextBar(session.getCPtr(session));
        if (sessionStateMoveToNextBar == 0) {
            return null;
        }
        return new playerListNode(sessionStateMoveToNextBar, false);
    }

    public static score sessionGetFirstNonEmptyGroupBarScore(session session) {
        long sessionGetFirstNonEmptyGroupBarScore = JniSourceJNI.sessionGetFirstNonEmptyGroupBarScore(session.getCPtr(session));
        if (sessionGetFirstNonEmptyGroupBarScore == 0) {
            return null;
        }
        return new score(sessionGetFirstNonEmptyGroupBarScore, false);
    }

    public static bar sessionGetFirstNonEmptyGroupBarBar(session session) {
        long sessionGetFirstNonEmptyGroupBarBar = JniSourceJNI.sessionGetFirstNonEmptyGroupBarBar(session.getCPtr(session));
        if (sessionGetFirstNonEmptyGroupBarBar == 0) {
            return null;
        }
        return new bar(sessionGetFirstNonEmptyGroupBarBar, false);
    }

    public static timeSignature sessionFindLastTS(session session, int i, int i2) {
        long sessionFindLastTS = JniSourceJNI.sessionFindLastTS(session.getCPtr(session), i, i2);
        if (sessionFindLastTS == 0) {
            return null;
        }
        return new timeSignature(sessionFindLastTS, false);
    }

    public static SWIGTYPE_p_int new_intp() {
        long allocInt = JniSourceJNI.allocInt();
        if (allocInt != 0) {
            return new SWIGTYPE_p_int(allocInt, false);
        }
        return null;
    }

    public static void intp_assign(SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            JniSourceJNI.writeInt(cPtr, i);
        }
    }

    public static int intp_value(SWIGTYPE_p_int sWIGTYPE_p_int) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            return JniSourceJNI.readInt(cPtr);
        }
        return 0;
    }

    public static void delete_intp(SWIGTYPE_p_int sWIGTYPE_p_int) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            JniSourceJNI.freeInt(cPtr);
        }
    }

    public static SWIGTYPE_p_int new_lintp() {
        long allocInt = JniSourceJNI.allocInt();
        if (allocInt != 0) {
            return new SWIGTYPE_p_int(allocInt, false);
        }
        return null;
    }

    public static void lintp_assign(SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            JniSourceJNI.writeInt(cPtr, i);
        }
    }

    public static int lintp_value(SWIGTYPE_p_int sWIGTYPE_p_int) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            return JniSourceJNI.readInt(cPtr);
        }
        return 0;
    }

    public static void delete_lintp(SWIGTYPE_p_int sWIGTYPE_p_int) {
        long cPtr = SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int);
        if (cPtr != 0) {
            JniSourceJNI.freeInt(cPtr);
        }
    }

    public static SWIGTYPE_p_unsigned_int new_uint32p() {
        long allocUint32 = JniSourceJNI.allocUint32();
        if (allocUint32 != 0) {
            return new SWIGTYPE_p_unsigned_int(allocUint32, false);
        }
        return null;
    }

    public static void uint32p_assign(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, long j) {
        long cPtr = SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int);
        if (cPtr != 0) {
            JniSourceJNI.writeUint32(cPtr, j);
        }
    }

    public static long uint32p_value(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        long cPtr = SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int);
        if (cPtr != 0) {
            return JniSourceJNI.readUint32(cPtr);
        }
        return 0;
    }

    public static void delete_uint32p(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        long cPtr = SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int);
        if (cPtr != 0) {
            JniSourceJNI.freeUint32(cPtr);
        }
    }

    public static SWIGTYPE_p_p_score new_scorepp() {
        long allocScorePtr = JniSourceJNI.allocScorePtr();
        if (allocScorePtr != 0) {
            return new SWIGTYPE_p_p_score(allocScorePtr, false);
        }
        return null;
    }

    public static void scorepp_assign(SWIGTYPE_p_p_score sWIGTYPE_p_p_score, score score) {
        long cPtr = SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score);
        long cPtr2 = score.getCPtr(score);
        if (cPtr != 0) {
            JniSourceJNI.writeScorePtr(cPtr, cPtr2);
        }
    }

    public static score scorepp_value(SWIGTYPE_p_p_score sWIGTYPE_p_p_score) {
        long cPtr = SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score);
        if (cPtr == 0) {
            return null;
        }
        return Swig.newScore(JniSourceJNI.readScorePtr(cPtr));
    }

    public static void delete_scorepp(SWIGTYPE_p_p_score sWIGTYPE_p_p_score) {
        long cPtr = SWIGTYPE_p_p_score.getCPtr(sWIGTYPE_p_p_score);
        if (cPtr != 0) {
            JniSourceJNI.freeScorePtr(cPtr);
        }
    }

    public static SWIGTYPE_p_p_session new_sessionpp() {
        long allocSessionPtr = JniSourceJNI.allocSessionPtr();
        if (allocSessionPtr != 0) {
            return new SWIGTYPE_p_p_session(allocSessionPtr, false);
        }
        return null;
    }

    public static void sessionpp_assign(SWIGTYPE_p_p_session sWIGTYPE_p_p_session, session session) {
        long cPtr = SWIGTYPE_p_p_session.getCPtr(sWIGTYPE_p_p_session);
        long cPtr2 = session.getCPtr(session);
        if (cPtr != 0) {
            JniSourceJNI.writeSessionPtr(cPtr, cPtr2);
        }
    }

    public static session sessionpp_value(SWIGTYPE_p_p_session sWIGTYPE_p_p_session) {
        long cPtr = SWIGTYPE_p_p_session.getCPtr(sWIGTYPE_p_p_session);
        if (cPtr == 0) {
            return null;
        }
        return Swig.newSession(JniSourceJNI.readSessionPtr(cPtr));
    }

    public static void delete_sessionpp(SWIGTYPE_p_p_session sWIGTYPE_p_p_session) {
        long cPtr = SWIGTYPE_p_p_session.getCPtr(sWIGTYPE_p_p_session);
        if (cPtr != 0) {
            JniSourceJNI.freeSessionPtr(cPtr);
        }
    }

    public static SWIGTYPE_p_p_Pix new_pixpp() {
        long allocPixPtr = JniSourceJNI.allocPixPtr();
        if (allocPixPtr != 0) {
            return new SWIGTYPE_p_p_Pix(allocPixPtr, false);
        }
        return null;
    }

    public static void pixpp_assign(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, Pix pix) {
        long cPtr = SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix);
        long cPtr2 = Pix.getCPtr(pix);
        if (cPtr != 0) {
            JniSourceJNI.writePixPtr(cPtr, cPtr2);
        }
    }

    public static Pix pixpp_value(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long cPtr = SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix);
        if (cPtr == 0) {
            return null;
        }
        return Swig.newPix(JniSourceJNI.readPixPtr(cPtr));
    }

    public static void delete_pixpp(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long cPtr = SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix);
        if (cPtr != 0) {
            JniSourceJNI.freePixPtr(cPtr);
        }
    }
}
