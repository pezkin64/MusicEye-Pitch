package com.xemsoft.sheetmusicscanner2.leptonica;

/**
 * Mirror of xemsoft JniLeptonicaJNI.
 * pixRead is the Linux entry point — loads an image from a file path into PIX*.
 */
public class JniLeptonicaJNI {
    public static native long pixRead(String filename);
    public static native void pixDestroy(long pixPtr);
    public static native int  pixGetWidth(long pixPtr);
    public static native int  pixGetHeight(long pixPtr);
}
