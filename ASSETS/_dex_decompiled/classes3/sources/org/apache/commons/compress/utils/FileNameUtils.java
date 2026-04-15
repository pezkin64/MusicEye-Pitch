package org.apache.commons.compress.utils;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.File;

public class FileNameUtils {
    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return BuildConfig.FLAVOR;
        }
        return name.substring(lastIndexOf + 1);
    }

    public static String getBaseName(String str) {
        if (str == null) {
            return null;
        }
        String name = new File(str).getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf < 0) {
            return name;
        }
        return name.substring(0, lastIndexOf);
    }
}
