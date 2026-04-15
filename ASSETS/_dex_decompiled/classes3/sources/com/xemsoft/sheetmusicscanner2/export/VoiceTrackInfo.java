package com.xemsoft.sheetmusicscanner2.export;

import com.google.firebase.encoders.json.BuildConfig;
import jp.kshoji.javax.sound.midi.Track;

public class VoiceTrackInfo {
    boolean active = false;
    int m_Channel1 = -1;
    int m_Channel2 = -1;
    String m_GmInstrumentName = BuildConfig.FLAVOR;
    int m_GmInstrumentProgram = 0;
    String m_InstrumentName = BuildConfig.FLAVOR;
    int m_InstrumentPitch = 0;
    int m_InstrumentProgram = 0;
    Track m_Track1 = null;
    Track m_Track2 = null;
    float m_Volume1 = 1.0f;
    float m_Volume2 = 1.0f;
    boolean split = false;
}
