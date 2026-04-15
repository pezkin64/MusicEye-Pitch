package com.xemsoft.sheetmusicscanner2.lame;

public class Lame {
    private long handle;

    public native byte[] close();

    public native byte[] encode(short[] sArr, int i, int i2);

    public native void open(int i, int i2, int i3, int i4);

    static {
        System.loadLibrary("lame");
    }
}
