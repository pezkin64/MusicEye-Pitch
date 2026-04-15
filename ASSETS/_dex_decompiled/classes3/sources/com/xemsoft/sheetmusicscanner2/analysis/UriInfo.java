package com.xemsoft.sheetmusicscanner2.analysis;

import android.net.Uri;

public class UriInfo {
    public static final int DOCUMENT = 1;
    public static final int FILE = 4;
    public static final int INVALID = 0;
    private static final String LOGTAG = "UriInfo.java";
    public static final int MAYBE = 2;
    public static final int MEDIA = 2;
    public static final int MIME_AUDIO = 3;
    public static final int MIME_IMAGE = 1;
    public static final int MIME_PDF = 4;
    public static final int MIME_TIFF = 5;
    public static final int MIME_UNKNOWN = 0;
    public static final int MIME_VIDEO = 2;
    public static final int OPENABLE = 3;
    public static final int UNKNOWN = 0;
    public static final int VALID = 1;
    public long m_DateModified = 0;
    public String m_DisplayName = null;
    public String m_Id = null;
    public String m_Magic = null;
    public int m_MimeType = 0;
    public String m_MimeTypeString = null;
    public long m_Size = 0;
    public int m_Type = 0;
    public Uri m_Uri = null;
    public int m_Valid = 2;

    public String toString() {
        Uri uri = this.m_Uri;
        String uri2 = uri == null ? "null" : uri.toString();
        return "uri: " + uri2 + "\ntype: " + this.m_Type + "\nid: " + this.m_Id + "\nname: " + this.m_DisplayName + "\ndate: " + this.m_DateModified + "\nsize: " + this.m_Size + "\nmagic: " + this.m_Magic + "\nmime-type: " + this.m_MimeTypeString;
    }
}
