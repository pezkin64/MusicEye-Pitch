package com.xemsoft.sheetmusicscanner2.util;

import android.util.Log;
import cn.sherlock.com.sun.media.sound.SoftShortMessage;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.ShortMessage;
import kotlinx.coroutines.scheduling.WorkQueueKt;

public class MidiUtils {
    private static final String LOGTAG = "MidiUtils.java";

    public static int hzToCents(double d) {
        return (int) ((Math.log(d / 440.0d) * 1200.0d) / Math.log(2.0d));
    }

    public static byte[] splitInt14(int i) {
        if (i < 0) {
            i = 0;
        } else if (i > 16383) {
            i = 16383;
        }
        return new byte[]{(byte) (i & WorkQueueKt.MASK), (byte) (i >> 7)};
    }

    public static ShortMessage getProgramChangeMessage(int i, int i2) {
        try {
            return new ShortMessage(ShortMessage.PROGRAM_CHANGE, i, i2, 0);
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getProgramChangeMessage()", e);
            return null;
        }
    }

    public static MidiEvent getProgramChangeEvent(int i, int i2, long j) {
        return messageToEvent(getProgramChangeMessage(i, i2), j);
    }

    public static ShortMessage getNoteOnMessage(int i, int i2, int i3) {
        try {
            return new SoftShortMessage(ShortMessage.NOTE_ON, i, i2, i3);
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getNoteOnMessage()", e);
            return null;
        }
    }

    public static MidiEvent getNoteOnEvent(int i, int i2, int i3, long j) {
        if (j == 0) {
            j = 1;
        }
        return messageToEvent(getNoteOnMessage(i, i2, i3), j);
    }

    public static ShortMessage getNoteOffMessage(int i, int i2) {
        try {
            return new SoftShortMessage(128, i, i2, 0);
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getNoteOffMessage()", e);
            return null;
        }
    }

    public static MidiEvent getNoteOffEvent(int i, int i2, long j) {
        return messageToEvent(getNoteOffMessage(i, i2), j);
    }

    public static ShortMessage getVolumeMessage(int i, float f) {
        try {
            return new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 7, Math.round(f * 127.0f));
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getTuneMessagesFix()", e);
            return null;
        }
    }

    public static MidiEvent getVolumeEvent(int i, float f, long j) {
        return messageToEvent(getVolumeMessage(i, f), j);
    }

    public static ShortMessage[] getTuneMessagesFix(int i, int i2) {
        int i3;
        int i4 = i2 / 100;
        int i5 = i2 % 100;
        SoftShortMessage[] softShortMessageArr = new SoftShortMessage[10];
        int i6 = i4 / 2;
        int i7 = i6 + 64;
        if (i4 % 2 == 0) {
            i3 = 0;
        } else {
            i3 = 64;
            if (i4 < 0) {
                i7 = i6 + 63;
            }
        }
        byte[] splitInt14 = splitInt14(Math.round(((float) ((i5 + 100) * 16383)) / 200.0f));
        try {
            softShortMessageArr[0] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, 0);
            softShortMessageArr[1] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, 2);
            softShortMessageArr[2] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 6, i7);
            softShortMessageArr[3] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 38, i3);
            softShortMessageArr[4] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, 0);
            softShortMessageArr[5] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, 1);
            softShortMessageArr[6] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 6, splitInt14[1]);
            softShortMessageArr[7] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 38, splitInt14[0]);
            softShortMessageArr[8] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, WorkQueueKt.MASK);
            softShortMessageArr[9] = new SoftShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, WorkQueueKt.MASK);
            return softShortMessageArr;
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getTuneMessagesFix()", e);
            return null;
        }
    }

    public static MidiEvent[] getTuneEventsFix(int i, int i2, long j) {
        return messagesToEvents(getTuneMessagesFix(i, i2), j);
    }

    public static ShortMessage[] getTuneMessages(int i, int i2) {
        ShortMessage[] shortMessageArr = new ShortMessage[9];
        int i3 = (i2 / 100) + 64;
        byte[] splitInt14 = splitInt14((((i2 % 100) + 100) * 16383) / 200);
        try {
            shortMessageArr[0] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, 0);
            shortMessageArr[1] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, 2);
            shortMessageArr[2] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 6, i3);
            shortMessageArr[3] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, 0);
            shortMessageArr[4] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, 1);
            shortMessageArr[5] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 6, splitInt14[1]);
            shortMessageArr[6] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 38, splitInt14[0]);
            shortMessageArr[7] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 100, WorkQueueKt.MASK);
            shortMessageArr[8] = new ShortMessage(ShortMessage.CONTROL_CHANGE, i, 101, WorkQueueKt.MASK);
            return shortMessageArr;
        } catch (InvalidMidiDataException e) {
            Log.w(LOGTAG, "getTuneMessages()", e);
            return null;
        }
    }

    public static MidiEvent[] getTuneEvents(int i, int i2, long j) {
        return messagesToEvents(getTuneMessages(i, i2), j);
    }

    private static MidiEvent messageToEvent(ShortMessage shortMessage, long j) {
        if (shortMessage != null) {
            return new MidiEvent(shortMessage, j);
        }
        return null;
    }

    private static MidiEvent[] messagesToEvents(ShortMessage[] shortMessageArr, long j) {
        if (shortMessageArr == null) {
            return null;
        }
        MidiEvent[] midiEventArr = new MidiEvent[shortMessageArr.length];
        for (int i = 0; i < shortMessageArr.length; i++) {
            midiEventArr[i] = new MidiEvent(shortMessageArr[i], j);
        }
        return midiEventArr;
    }
}
