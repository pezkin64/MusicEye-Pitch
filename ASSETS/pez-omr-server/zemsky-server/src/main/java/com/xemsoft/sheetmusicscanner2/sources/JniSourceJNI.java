package com.xemsoft.sheetmusicscanner2.sources;

/**
 * Mirror of xemsoft JniSourceJNI.
 * Native method names must match the .so export symbols exactly:
 *   Java_com_xemsoft_sheetmusicscanner2_sources_JniSourceJNI_<method>
 */
public class JniSourceJNI {
    public static native long   sessionCreate();
    public static native void   sessionDestroy(long sessionPtr);
    public static native int    sessionAdd(long sessionPtr, long scorePtr);
    public static native int    sessionInsert(long sessionPtr, long scorePtr, int index);
    public static native long   nativeAnalyze(
        Object scanner,
        String templatesDir,
        String ocrModelPath,
        String keySigCModel,
        String keySigDigit,
        float  displayWidth,
        float  scaleFactor,
        long   pixPtr,
        long   sessionPtr,
        int    insertAtIndex,
        String testDataDir,
        int    pageIndex
    );
    public static native int    analysisResult_resultCode_get(long ptr, Object obj);
    public static native long   analysisResult_score_get(long ptr, Object obj);
    public static native void   delete_analysisResult(long ptr);
    public static native int    exportToMusicXml(
        String outputPath,
        long   sessionPtr,
        Object session,
        String title,
        String bpm,
        int    timeNumer,
        int    timeDenom,
        int    divisions,
        int    voiceCount
    );
}
