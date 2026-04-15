package com.xemsoft.sheetmusicscanner2.util;

import android.content.Context;

public class Logg {
    private static LogFile mLogFile;
    private static Logg sInstance;

    public static void d(String str, String str2) {
    }

    public static void e(String str, String str2) {
    }

    public static void e(String str, String str2, Exception exc) {
    }

    public static void i(String str, String str2) {
    }

    public static void w(String str, String str2) {
    }

    public static void w(String str, String str2, Exception exc) {
    }

    public static void init(Context context) {
        mLogFile = LogFile.getInstance(context);
    }
}
