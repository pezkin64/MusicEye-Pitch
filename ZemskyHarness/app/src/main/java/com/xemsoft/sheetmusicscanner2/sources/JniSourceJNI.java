package com.xemsoft.sheetmusicscanner2.sources;

public class JniSourceJNI {
    public static native long sessionCreate();
    public static native void sessionDestroy(long sessionPtr);
    public static native int session_n_get(long sessionPtr);
    public static native void sessionAdd(long sessionPtr, Object session, long scorePtr, Object score);
    public static native void sessionInsert(long sessionPtr, Object session, long scorePtr, Object score, int index);
    public static native long nativeAnalyze(
        float displayWidth,
        float scaleFactor,
        Object scanner,
        long pixPtr,
        String templatesDir,
        String ocrModelPath,
        String keySigCModel,
        String keySigDigit,
        long sessionPtr,
        int insertAtIndex,
        String testDataDir
    );
    public static native int analysisResult_resultCode_get(long ptr, Object obj);
    public static native long analysisResult_score_get(long ptr, Object obj);
    public static native void delete_analysisResult(long ptr);
    public static native int exportToMusicXml(
        String outputPath,
        long sessionPtr,
        Object session,
        String title,
        String bpm,
        int timeNumer,
        int timeDenom,
        int divisions,
        int voiceIndexFilter
    );
}
