package com.xemsoft.sheetmusicscanner2.leptonica;

public class JniLeptonicaJNI {
    public static native long pixRead(String filename);
    public static native void pixDestroy(long pixPtr);
    public static native int pixGetWidth(long pixPtr);
    public static native int pixGetHeight(long pixPtr);
}
