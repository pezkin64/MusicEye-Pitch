package com.xemsoft.sheetmusicscanner2.persist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class CdSessionTable {
    private static final String BPM = "bpm";
    private static final String CDSESSION_TABLE = "cdsession_table";
    private static final String DATABASE_CREATE = "create table cdsession_table(_id integer primary key autoincrement, bpm integer, display_name text not null, instrument integer, instrument_pitch integer, last_edited integer, multiple_voices_on integer, _order integer, session_folder_name text not null );";
    private static final String DISPLAY_NAME = "display_name";
    private static final String ID = "_id";
    private static final String INSTRUMENT = "instrument";
    private static final String INSTRUMENT_PITCH = "instrument_pitch";
    private static final String LAST_EDITED = "last_edited";
    private static final String LOGTAG = "CdSessionTable.java";
    private static final String MULTIPLE_VOICES_ON = "multiple_voices_on";
    private static final String ORDER = "_order";
    private static final String SESSION_FOLDER_NAME = "session_folder_name";

    public static void create(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(DATABASE_CREATE);
    }

    public static void upgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS cdsession_table");
        create(sQLiteDatabase);
    }

    private static ContentValues fillValues(CdSession cdSession) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(BPM, Integer.valueOf(cdSession.bpm));
        contentValues.put(DISPLAY_NAME, cdSession.displayName);
        contentValues.put(INSTRUMENT, Integer.valueOf(cdSession.instrument));
        contentValues.put(INSTRUMENT_PITCH, Integer.valueOf(cdSession.instrumentPitch));
        contentValues.put(LAST_EDITED, Long.valueOf(cdSession.lastEdited));
        contentValues.put(MULTIPLE_VOICES_ON, Integer.valueOf(cdSession.multipleVoicesOn));
        contentValues.put(ORDER, Integer.valueOf(cdSession.order));
        contentValues.put(SESSION_FOLDER_NAME, cdSession.sessionFolderName);
        return contentValues;
    }

    public static long createCdSession(SQLiteDatabase sQLiteDatabase, CdSession cdSession) {
        long insert = sQLiteDatabase.insert(CDSESSION_TABLE, (String) null, fillValues(cdSession));
        cdSession.id = insert;
        return insert;
    }

    public static int updateCdSession(SQLiteDatabase sQLiteDatabase, CdSession cdSession) {
        ContentValues fillValues = fillValues(cdSession);
        return sQLiteDatabase.update(CDSESSION_TABLE, fillValues, "_id = " + cdSession.getId(), (String[]) null);
    }

    public static int updateCdSessionDisplayName(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DISPLAY_NAME, str2);
        return sQLiteDatabase.update(CDSESSION_TABLE, contentValues, "session_folder_name = ?", new String[]{str});
    }

    public static void deleteAll(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(CDSESSION_TABLE, (String) null, (String[]) null);
    }

    public static int deleteCdSessionByFolder(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.delete(CDSESSION_TABLE, "session_folder_name = ?", new String[]{str});
    }

    public static void readCdSession(CdSession cdSession, Cursor cursor) {
        if (cdSession != null) {
            cdSession.id = cursor.getLong(cursor.getColumnIndex(ID));
            cdSession.bpm = cursor.getInt(cursor.getColumnIndex(BPM));
            cdSession.displayName = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
            cdSession.instrument = cursor.getInt(cursor.getColumnIndex(INSTRUMENT));
            cdSession.instrumentPitch = cursor.getInt(cursor.getColumnIndex(INSTRUMENT_PITCH));
            cdSession.lastEdited = cursor.getLong(cursor.getColumnIndex(LAST_EDITED));
            cdSession.multipleVoicesOn = cursor.getInt(cursor.getColumnIndex(MULTIPLE_VOICES_ON));
            cdSession.order = cursor.getInt(cursor.getColumnIndex(ORDER));
            cdSession.sessionFolderName = cursor.getString(cursor.getColumnIndex(SESSION_FOLDER_NAME));
        }
    }

    private static Cursor cdSessionByFolderCursor(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.rawQuery("SELECT * FROM cdsession_table WHERE session_folder_name = ?", new String[]{str});
    }

    public static CdSession getCdSessionByFolder(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor cdSessionByFolderCursor = cdSessionByFolderCursor(sQLiteDatabase, str);
        CdSession cdSession = null;
        if (cdSessionByFolderCursor != null) {
            if (cdSessionByFolderCursor.moveToFirst()) {
                cdSession = new CdSession();
                readCdSession(cdSession, cdSessionByFolderCursor);
            }
            cdSessionByFolderCursor.close();
        }
        return cdSession;
    }

    private static List<CdSession> getCursorCdSessionList(Cursor cursor) {
        ArrayList arrayList = new ArrayList();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    CdSession cdSession = new CdSession();
                    readCdSession(cdSession, cursor);
                    arrayList.add(cdSession);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return arrayList;
    }

    public static Cursor allCdSessionCursor(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.rawQuery("SELECT * FROM cdsession_table ORDER BY display_name COLLATE NOCASE", (String[]) null);
    }

    public static List<CdSession> getAllCdSessionList(SQLiteDatabase sQLiteDatabase) {
        return getCursorCdSessionList(allCdSessionCursor(sQLiteDatabase));
    }
}
