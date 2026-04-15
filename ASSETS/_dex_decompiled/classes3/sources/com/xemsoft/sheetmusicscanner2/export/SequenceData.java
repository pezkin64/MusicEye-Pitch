package com.xemsoft.sheetmusicscanner2.export;

import java.util.List;
import jp.kshoji.javax.sound.midi.Sequence;

public class SequenceData {
    public List<Integer> m_Programs;
    public Sequence m_Sequence;

    public SequenceData(Sequence sequence, List<Integer> list) {
        this.m_Sequence = sequence;
        this.m_Programs = list;
    }
}
