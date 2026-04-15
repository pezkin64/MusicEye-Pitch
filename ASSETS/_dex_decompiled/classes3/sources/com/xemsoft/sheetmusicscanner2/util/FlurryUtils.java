package com.xemsoft.sheetmusicscanner2.util;

import android.graphics.Bitmap;
import com.xemsoft.sheetmusicscanner2.sources.analysisResult;
import com.xemsoft.sheetmusicscanner2.sources.baraa;
import com.xemsoft.sheetmusicscanner2.sources.score;
import java.util.HashMap;

public class FlurryUtils {
    public static void event(String str) {
    }

    public static void startAnalysisEvent() {
    }

    public static void valueEvent(String str, String str2, String str3) {
        new HashMap().put(str2, str3);
    }

    public static void endAnalysisEvent(Bitmap bitmap, analysisResult analysisresult, int i) {
        if (analysisresult != null && bitmap != null) {
            score score = analysisresult.getScore();
            baraa singleBaraa = score != null ? score.getSingleBaraa() : null;
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            HashMap hashMap = new HashMap();
            String str = "N";
            hashMap.put("IsSuccess", score == null ? str : "Y");
            hashMap.put("Width", Integer.toString(width));
            hashMap.put("Height", Integer.toString(height));
            if (width > height) {
                str = "Y";
            }
            hashMap.put("IsPortrait", str);
            hashMap.put("BarCount", Integer.toString(singleBaraa != null ? singleBaraa.getN() : 0));
            hashMap.put("Result", Integer.toString(analysisresult.getResultCode()));
            hashMap.put("ScoreCount", Integer.toString(i));
        }
    }
}
