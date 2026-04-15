package com.xemsoft.sheetmusicscanner2.player.resource;

import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.score;

public class BarButton {
    public int h;
    public boolean hidden = false;
    public boolean isFirstInStaff = false;
    public boolean isGroupButton = false;
    public boolean isLastInStaff = false;
    public long key = 0;
    private bar m_Bar;
    private int m_BarIndex;
    private score m_Score;
    private int m_StaffIndex;
    public int w;
    public int x;
    public int y;

    public BarButton(int i, int i2, int i3, int i4, score score, bar bar, int i5, int i6) {
        this.x = i;
        this.y = i2;
        this.w = i3;
        this.h = i4;
        this.m_Score = score;
        this.m_Bar = bar;
        this.m_StaffIndex = i5;
        this.m_BarIndex = i6;
    }

    public score getScore() {
        return this.m_Score;
    }

    public bar getBar() {
        return this.m_Bar;
    }

    public int getStaffIndex() {
        return this.m_StaffIndex;
    }

    public int getBarIndex() {
        return this.m_BarIndex;
    }
}
