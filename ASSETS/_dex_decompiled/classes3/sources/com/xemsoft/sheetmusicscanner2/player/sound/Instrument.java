package com.xemsoft.sheetmusicscanner2.player.sound;

public class Instrument {
    public static final int GROUP_BRASS = 3;
    public static final int GROUP_CHOIR = 4;
    public static final int GROUP_DRUMS = 7;
    public static final int GROUP_GUITARS = 6;
    public static final int GROUP_PIANO = 5;
    public static final int GROUP_SAXOPHONES = 2;
    public static final int GROUP_STRINGS = 0;
    public static final int GROUP_WOODWINDS = 1;
    private int m_Group;
    private String m_Name;
    private int m_Pitch;
    private int m_Program;

    public Instrument(int i, String str, int i2, int i3) {
        this.m_Program = i;
        this.m_Name = str;
        this.m_Pitch = i2;
        this.m_Group = i3;
    }

    public int getProgram() {
        return this.m_Program;
    }

    public void setProgram(int i) {
        this.m_Program = i;
    }

    public String getName() {
        return this.m_Name;
    }

    public void setName(String str) {
        this.m_Name = str;
    }

    public int getPitch() {
        return this.m_Pitch;
    }

    public void setPitch(int i) {
        this.m_Pitch = i;
    }

    public int getGroup() {
        return this.m_Group;
    }

    public void setGroup(int i) {
        this.m_Group = i;
    }
}
