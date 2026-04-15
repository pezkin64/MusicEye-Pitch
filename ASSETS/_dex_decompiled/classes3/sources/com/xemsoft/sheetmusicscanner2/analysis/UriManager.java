package com.xemsoft.sheetmusicscanner2.analysis;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.compress.utils.CharsetNames;

public class UriManager {
    private static final String LOGTAG = "UriManager.java";
    private static final String[] m_DocumentProjection = {"_display_name", "_size", "document_id", "last_modified"};
    private static final String[] m_DropboxProjection = {"_display_name", "_size", "_id", "last_modified"};
    private static final String[] m_MediaProjection = {"_display_name", "_size", "_data", "date_modified"};
    private static final String[] m_OpenableProjection = {"_display_name", "_size"};

    private static void logColumnNames(String[] strArr) {
    }

    public static String getUriMagic(Context context, Uri uri) {
        UriInfo uriInfo = getUriInfo(context, uri);
        if (uriInfo == null) {
            return null;
        }
        return uriInfo.m_Magic;
    }

    public static int getUriValid(Context context, Uri uri) {
        return getUriInfo(context, uri).m_Valid;
    }

    public static UriInfo getUriInfo(Context context, Uri uri) {
        if (context == null || uri == null) {
            return null;
        }
        UriInfo uriInfo = new UriInfo();
        uriInfo.m_Uri = uri;
        getMimeType(context, uriInfo);
        String scheme = uri.getScheme();
        if (scheme.equalsIgnoreCase("file")) {
            getFileInfo(uriInfo);
        } else if (scheme.equalsIgnoreCase("content")) {
            getContentInfo(context, uriInfo);
        }
        fixDisplayName(uriInfo);
        makeMagic(uriInfo);
        return uriInfo;
    }

    private static void getMimeType(Context context, UriInfo uriInfo) {
        uriInfo.m_MimeTypeString = context.getContentResolver().getType(uriInfo.m_Uri);
        if (uriInfo.m_MimeTypeString.equals("image/jpeg") || uriInfo.m_MimeTypeString.equals("image/jpg") || uriInfo.m_MimeTypeString.equals("image/gif") || uriInfo.m_MimeTypeString.equals("image/png") || uriInfo.m_MimeTypeString.equals("image/x-ms-bmp") || uriInfo.m_MimeTypeString.equals("image/vnd.wap.wbmp") || uriInfo.m_MimeTypeString.equals("image/webp") || uriInfo.m_MimeTypeString.equals("image.jpeg")) {
            uriInfo.m_MimeType = 1;
        } else if (uriInfo.m_MimeTypeString.equals("image/tiff") || uriInfo.m_MimeTypeString.equals("image/tif")) {
            uriInfo.m_MimeType = 5;
        } else if (uriInfo.m_MimeTypeString.equals("application/pdf")) {
            uriInfo.m_MimeType = 4;
        } else {
            uriInfo.m_MimeType = 0;
        }
    }

    private static void getFileInfo(UriInfo uriInfo) {
        uriInfo.m_Type = 4;
        uriInfo.m_Valid = 0;
        String path = uriInfo.m_Uri.getPath();
        if (path != null) {
            File file = new File(path);
            uriInfo.m_DisplayName = file.getName();
            if (file.exists()) {
                long length = file.length();
                if (length > 0) {
                    uriInfo.m_Size = length;
                    uriInfo.m_DateModified = file.lastModified();
                    uriInfo.m_Id = path;
                    uriInfo.m_Valid = 1;
                }
            }
        }
    }

    private static void getContentInfo(Context context, UriInfo uriInfo) {
        Cursor cursor;
        boolean isDocumentUri = DocumentsContract.isDocumentUri(context, uriInfo.m_Uri);
        uriInfo.m_Type = 3;
        uriInfo.m_Valid = 0;
        try {
            cursor = context.getContentResolver().query(uriInfo.m_Uri, (String[]) null, (String) null, (String[]) null, (String) null);
        } catch (SecurityException unused) {
            cursor = null;
        }
        if (cursor != null) {
            String[] columnNames = cursor.getColumnNames();
            if (cursor.moveToFirst()) {
                if (isDocumentUri) {
                    getDBInfo(uriInfo, cursor, m_DocumentProjection, columnNames);
                    uriInfo.m_Type = 1;
                } else {
                    getDBInfo(uriInfo, cursor, m_MediaProjection, columnNames);
                    if (uriInfo.m_Valid == 1) {
                        uriInfo.m_Type = 2;
                    } else {
                        getDBInfo(uriInfo, cursor, m_DropboxProjection, columnNames);
                        if (uriInfo.m_Valid == 1) {
                            if (uriInfo.m_DateModified == 0) {
                                uriInfo.m_DateModified = 13356;
                            }
                            uriInfo.m_Id = uriInfo.m_DisplayName;
                            uriInfo.m_Type = 2;
                        } else {
                            getDBInfo(uriInfo, cursor, m_OpenableProjection, columnNames);
                        }
                    }
                }
            }
            cursor.close();
        }
    }

    private static void getDBInfo(UriInfo uriInfo, Cursor cursor, String[] strArr, String[] strArr2) {
        if (strArr2 == null || isStringSubset(strArr2, strArr)) {
            try {
                uriInfo.m_DisplayName = cursor.getString(cursor.getColumnIndex(strArr[0]));
                uriInfo.m_Size = cursor.getLong(cursor.getColumnIndex(strArr[1]));
                if (strArr.length == 4) {
                    uriInfo.m_Id = cursor.getString(cursor.getColumnIndex(strArr[2]));
                    uriInfo.m_DateModified = cursor.getLong(cursor.getColumnIndex(strArr[3]));
                }
                uriInfo.m_Valid = 1;
            } catch (Exception e) {
                Log.w(LOGTAG, "getDBInfo()) failed: ", e);
                uriInfo.m_Valid = 0;
            }
        } else {
            uriInfo.m_Valid = 0;
        }
    }

    private static void fixDisplayName(UriInfo uriInfo) {
        if (uriInfo.m_DisplayName == null || uriInfo.m_DisplayName.isEmpty()) {
            String lastPathSegment = uriInfo.m_Uri.getLastPathSegment();
            if (lastPathSegment == null) {
                lastPathSegment = uriInfo.m_Uri.toString();
            }
            uriInfo.m_DisplayName = lastPathSegment.substring(Math.max(0, lastPathSegment.lastIndexOf(47) + 1));
        }
    }

    private static boolean isStringSubset(String[] strArr, String[] strArr2) {
        if (!(strArr == null || strArr2 == null)) {
            int length = strArr.length;
            int length2 = strArr2.length;
            if (!(length == 0 || length2 == 0 || length2 > length)) {
                int i = 0;
                while (i < length2) {
                    String str = strArr2[i];
                    int i2 = 0;
                    while (i2 < length) {
                        if (str.equalsIgnoreCase(strArr[i2])) {
                            i++;
                        } else {
                            i2++;
                        }
                    }
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private static void makeMagic(UriInfo uriInfo) {
        if (uriInfo != null) {
            uriInfo.m_Magic = null;
            if (uriInfo.m_Id != null && uriInfo.m_Size != 0 && uriInfo.m_DateModified != 0) {
                try {
                    uriInfo.m_Magic = URLEncoder.encode(uriInfo.m_Id + "-" + uriInfo.m_Size + "-" + uriInfo.m_DateModified, CharsetNames.UTF_8);
                } catch (UnsupportedEncodingException unused) {
                    uriInfo.m_Magic = null;
                }
            }
        }
    }
}
