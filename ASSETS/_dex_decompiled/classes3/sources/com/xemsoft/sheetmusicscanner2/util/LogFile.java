package com.xemsoft.sheetmusicscanner2.util;

import android.content.Context;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogFile {
    private static final boolean LOGGING = true;
    private static LogFile m_Instance;
    private String LOG_PATH;

    public static LogFile getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new LogFile(context);
        }
        return m_Instance;
    }

    public LogFile(Context context) {
        this.LOG_PATH = context.getExternalFilesDir((String) null).getAbsolutePath() + "/scanlog.txt";
        new File(this.LOG_PATH).delete();
    }

    public void log(String str) {
        try {
            FileWriter fileWriter = new FileWriter(this.LOG_PATH, true);
            fileWriter.write(System.getProperty("line.separator"));
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
