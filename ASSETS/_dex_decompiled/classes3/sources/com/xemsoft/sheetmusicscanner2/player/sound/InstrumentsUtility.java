package com.xemsoft.sheetmusicscanner2.player.sound;

import android.content.Context;
import com.xemsoft.sheetmusicscanner2.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class InstrumentsUtility {
    private static final String DICT = "<dict>";
    private static final String END = "</";
    private static final int[] GROUP_RES_IDS = {R.drawable.strings_icon, R.drawable.woodwinds_icon, R.drawable.saxophones_icon, R.drawable.brass_icon, R.drawable.choir_icon, R.drawable.piano_icon, R.drawable.guitars_icon, R.drawable.drums_icon};
    private static final String[] GROUP_STRINGS = {"strings", "woodwinds", "saxophones", "brass", "choir", "piano", "guitars", "drums"};
    private static final String INTEGER = "<integer>";
    private static final String KEY = "<key>";
    private static final String LOGTAG = "InstrumentsUtility.java";
    private static final int PARSE_CONTINUE = 0;
    private static final int PARSE_DONE = 1;
    private static final int PARSE_ERROR = -1;
    private static final String STRING = "<string>";
    private static InstrumentsUtility m_Instance;
    private Context m_Context;
    private List<Instrument> m_InstrumentList = null;
    private int m_Rover = 0;
    private String m_Xml;

    public InstrumentsUtility(Context context) {
        this.m_Context = context;
        parseXmlList();
        sortInstrumentList();
        logList();
    }

    public static InstrumentsUtility getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new InstrumentsUtility(context);
        }
        return m_Instance;
    }

    public List instruments() {
        return this.m_InstrumentList;
    }

    public int iconWithInstrumentGroup(int i) {
        if (i < 0) {
            return 0;
        }
        int[] iArr = GROUP_RES_IDS;
        if (i < iArr.length) {
            return iArr[i];
        }
        return 0;
    }

    public int iconWithInstrumentProgram(int i) {
        Instrument findInstrumentByProgram = findInstrumentByProgram(i);
        if (findInstrumentByProgram != null) {
            return iconWithInstrumentGroup(findInstrumentByProgram.getGroup());
        }
        return 0;
    }

    public int pitchWithInstrumentProgram(int i) {
        Instrument findInstrumentByProgram = findInstrumentByProgram(i);
        if (findInstrumentByProgram != null) {
            return findInstrumentByProgram.getPitch();
        }
        return 0;
    }

    public int indexWithInstrumentProgram(int i) {
        for (int i2 = 0; i2 < this.m_InstrumentList.size(); i2++) {
            if (this.m_InstrumentList.get(i2).getProgram() == i) {
                return i2;
            }
        }
        return 0;
    }

    public Instrument findInstrumentByProgram(int i) {
        for (int i2 = 0; i2 < this.m_InstrumentList.size(); i2++) {
            Instrument instrument = this.m_InstrumentList.get(i2);
            if (instrument.getProgram() == i) {
                return instrument;
            }
        }
        return null;
    }

    private boolean parseXmlList() {
        int parseXmlInstrument;
        this.m_InstrumentList = new ArrayList();
        String string = this.m_Context.getString(R.string.instruments);
        this.m_Xml = string;
        int indexOf = string.indexOf(DICT);
        this.m_Rover = indexOf;
        if (indexOf == -1) {
            return false;
        }
        this.m_Rover = indexOf + 6;
        do {
            parseXmlInstrument = parseXmlInstrument();
        } while (parseXmlInstrument == 0);
        if (parseXmlInstrument == 1) {
            return true;
        }
        this.m_InstrumentList = null;
        return false;
    }

    private int parseXmlInstrument() {
        String nextXmlValue;
        String nextXmlValue2;
        String nextXmlValue3 = getNextXmlValue(KEY);
        if (nextXmlValue3 == null) {
            return 1;
        }
        String nextXmlValue4 = getNextXmlValue(STRING);
        if (nextXmlValue4 == null || (nextXmlValue = getNextXmlValue(INTEGER)) == null || (nextXmlValue2 = getNextXmlValue(STRING)) == null) {
            return -1;
        }
        this.m_InstrumentList.add(new Instrument(Integer.parseInt(nextXmlValue3), nextXmlValue4, Integer.parseInt(nextXmlValue), getGroupIdx(nextXmlValue2)));
        return 0;
    }

    private String getNextXmlValue(String str) {
        int indexOf = this.m_Xml.indexOf(str, this.m_Rover);
        this.m_Rover = indexOf;
        if (indexOf == -1) {
            return null;
        }
        int length = indexOf + str.length();
        this.m_Rover = length;
        int indexOf2 = this.m_Xml.indexOf(END, length);
        if (indexOf2 == -1) {
            return null;
        }
        String substring = this.m_Xml.substring(this.m_Rover, indexOf2);
        this.m_Rover = indexOf2;
        return substring;
    }

    private int getGroupIdx(String str) {
        int i = 0;
        while (true) {
            String[] strArr = GROUP_STRINGS;
            if (i >= strArr.length) {
                return 0;
            }
            if (str.compareTo(strArr[i]) == 0) {
                return i;
            }
            i++;
        }
    }

    private void sortInstrumentList() {
        Collections.sort(this.m_InstrumentList, new InstrumentComparator());
    }

    public class InstrumentComparator implements Comparator<Instrument> {
        public InstrumentComparator() {
        }

        public int compare(Instrument instrument, Instrument instrument2) {
            if (instrument.getGroup() < instrument2.getGroup()) {
                return -1;
            }
            if (instrument.getGroup() > instrument2.getGroup()) {
                return 1;
            }
            return instrument.getName().compareToIgnoreCase(instrument2.getName());
        }
    }

    private void logList() {
        for (int i = 0; i < this.m_InstrumentList.size(); i++) {
            Instrument instrument = this.m_InstrumentList.get(i);
        }
    }
}
