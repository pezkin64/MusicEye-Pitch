package com.xemsoft.sheetmusicscanner2.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StatFs;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_PIX;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.Swig;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.io.File;
import java.util.List;
import java.util.UUID;
import jp.kshoji.javax.sound.midi.Sequence;

public class SessionUtility extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sheet_music_scanner.db";
    private static final int DATABASE_VERSION = 1;
    private static final String EXAMPLE_SESSION_NAME = "example_session";
    private static final String LOGTAG = "SessionUtility.java";
    private static final String NNMODELS_NAME = "nnModels";
    public static final String SESSION_FILENAME = "session.dat";
    public static final String SETTINGS_JSON = "settings.json";
    private static final String TEMPLATES_NAME = "templates";
    private static final String TEST_DATA_NAME = "turkish_march";
    private static SessionUtility m_Instance;
    private String EXAMPLE_SESSION_DIR;
    public String LAST_IMAGE_PATH;
    private String NNMODELS_DIR;
    public String ROOT;
    public String SESSION_DIR;
    private String TEMPLATES_DIR;
    private String TEST_DATA_DIR;
    public String TMP_DIR;
    public String TMP_SESSION_PATH;
    private Context m_Context;
    private ScoreImages m_Images0 = new ScoreImages();
    private ScoreImages m_Images1 = new ScoreImages();
    private session m_Session;

    public interface LoadSessionListener {
        void onDone(session session);
    }

    public static synchronized SessionUtility getInstance(Context context) {
        SessionUtility sessionUtility;
        synchronized (SessionUtility.class) {
            if (m_Instance == null) {
                m_Instance = new SessionUtility(context);
            }
            sessionUtility = m_Instance;
        }
        return sessionUtility;
    }

    public SessionUtility(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.m_Context = context;
        this.ROOT = context.getFilesDir().getAbsolutePath();
        this.SESSION_DIR = this.ROOT + "/session";
        this.TMP_DIR = this.ROOT + "/tmp";
        this.TMP_SESSION_PATH = this.TMP_DIR + "/tmp.dat";
        this.LAST_IMAGE_PATH = this.TMP_DIR + "/Image.jpg";
        this.TEMPLATES_DIR = this.ROOT + "/templates";
        this.NNMODELS_DIR = this.ROOT + "/nnModels";
        this.EXAMPLE_SESSION_DIR = this.SESSION_DIR + "/example_session";
        this.TEST_DATA_DIR = this.ROOT + "/turkish_march";
        new File(this.SESSION_DIR).mkdirs();
        new File(this.TMP_DIR).mkdirs();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        CdSessionTable.create(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        CdSessionTable.upgrade(sQLiteDatabase, i, i2);
        onCreate(sQLiteDatabase);
    }

    public String getPlayerSettingsFilePath(String str) {
        return this.SESSION_DIR + "/" + str + "/settings.json";
    }

    public boolean storePlayerSettings(CdSession cdSession) {
        if (cdSession == null) {
            return false;
        }
        return cdSession.playerSettings.toJsonFile(getPlayerSettingsFilePath(cdSession.getSessionFolderName()));
    }

    public void loadPlayerSettings(CdSession cdSession, session session) {
        PlayerSettings fromJsonFile = PlayerSettings.fromJsonFile(getPlayerSettingsFilePath(cdSession.sessionFolderName));
        int sessionGetMaxVoiceIndex = JniSource.sessionGetMaxVoiceIndex(session) + 1;
        if (fromJsonFile == null || sessionGetMaxVoiceIndex > fromJsonFile.mixer.tracks.size()) {
            fromJsonFile = PlayerSettings.create(cdSession.bpm, sessionGetMaxVoiceIndex, cdSession.instrument, cdSession.instrumentPitch);
        }
        cdSession.playerSettings = fromJsonFile;
    }

    public String createNewSessionName() {
        return Utils.timeStamp("yy-MM-dd_HH-mm-ss") + "__" + UUID.randomUUID().toString();
    }

    public CdSession cdCreateSession(String str, String str2, int i, int i2, int i3) {
        CdSession cdSession = new CdSession();
        cdSession.setSessionFolderName(str);
        if (str2 == null) {
            str2 = this.m_Context.getString(R.string.z0Q_lF_B8s_title);
        }
        cdSession.setDisplayName(str2);
        cdSession.setBpm(i);
        cdSession.setInstrument(i2);
        cdSession.setInstrumentPitch(i3);
        cdSession.setLastEdited(System.currentTimeMillis());
        cdSession.setMultipleVoicesOn(true);
        if (dbCreateCdSession(cdSession) == -1) {
            return null;
        }
        return cdSession;
    }

    public CdSession cdGetSession(String str) {
        Log.d(LOGTAG, "cdGetSession");
        return CdSessionTable.getCdSessionByFolder(getWritableDatabase(), str);
    }

    public boolean cdDeleteSession(String str) {
        return CdSessionTable.deleteCdSessionByFolder(getWritableDatabase(), str) != 0;
    }

    public int cdUpdateSession(CdSession cdSession) {
        return CdSessionTable.updateCdSession(getWritableDatabase(), cdSession);
    }

    public boolean cdUpdateSessionDisplayName(String str, String str2) {
        return CdSessionTable.updateCdSessionDisplayName(getWritableDatabase(), str, str2) != 0;
    }

    public List<CdSession> cdGetAllSessions() {
        return CdSessionTable.getAllCdSessionList(getWritableDatabase());
    }

    public void dbDeleteAll() {
        CdSessionTable.deleteAll(getWritableDatabase());
    }

    public long dbCreateCdSession(CdSession cdSession) {
        return CdSessionTable.createCdSession(getWritableDatabase(), cdSession);
    }

    public boolean saveSession(session session, String str) {
        if (session == null || str == null || JniSource.serialize(session, this.TMP_SESSION_PATH) != 0) {
            return false;
        }
        String str2 = this.TMP_SESSION_PATH;
        return Utils.copyFile(str2, this.SESSION_DIR + "/" + str + "/session.dat");
    }

    public boolean deleteSessionFolder(String str) {
        if (str == null) {
            return false;
        }
        return Utils.removeDirectory(new File(this.SESSION_DIR + "/" + str));
    }

    public session loadSession(String str) {
        Log.d(LOGTAG, "loadSession - deserialize session");
        if (str == null) {
            return null;
        }
        return JniSource.deserialize(this.SESSION_DIR + "/" + str + "/session.dat");
    }

    public void loadSessionAsync(String str, LoadSessionListener loadSessionListener) {
        new loadSessionTask(str, loadSessionListener).execute(new Void[0]);
    }

    private class loadSessionTask extends AsyncTask<Void, Void, session> {
        private String m_FolderName;
        private LoadSessionListener m_Listener;

        public loadSessionTask(String str, LoadSessionListener loadSessionListener) {
            this.m_FolderName = str;
            this.m_Listener = loadSessionListener;
        }

        /* access modifiers changed from: protected */
        public session doInBackground(Void... voidArr) {
            return SessionUtility.this.loadSession(this.m_FolderName);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(session session) {
            super.onPostExecute(session);
            LoadSessionListener loadSessionListener = this.m_Listener;
            if (loadSessionListener != null) {
                loadSessionListener.onDone(session);
            }
        }
    }

    public boolean loadImagesFromSessionFolder(String str, int i, ScoreImages scoreImages) {
        if (str == null || scoreImages == null) {
            Log.w(LOGTAG, "loadImagesFromSessionFolder() - invalid params");
            return false;
        }
        String backgroundImageFilePath = getBackgroundImageFilePath(str, i);
        String backgroundImageFilePathIos = getBackgroundImageFilePathIos(str, i);
        String overlayImageFilePath = getOverlayImageFilePath(str, i);
        String overlayImageFilePathIos = getOverlayImageFilePathIos(str, i);
        if (!new File(backgroundImageFilePath).exists()) {
            if (new File(backgroundImageFilePathIos).exists()) {
                backgroundImageFilePath = backgroundImageFilePathIos;
            } else {
                Log.w(LOGTAG, "loadImagesFromSessionFolder() - background file missing");
                return false;
            }
        }
        if (!new File(overlayImageFilePath).exists()) {
            if (new File(overlayImageFilePathIos).exists()) {
                overlayImageFilePath = overlayImageFilePathIos;
            } else {
                Log.w(LOGTAG, "loadImagesFromSessionFolder() - overlay file missing");
                return false;
            }
        }
        Bitmap loadJpeg = Utils.loadJpeg(backgroundImageFilePath);
        scoreImages.backgroundImage = loadJpeg;
        if (loadJpeg == null) {
            Log.w(LOGTAG, "loadImagesFromSessionFolder() - background load failed");
            return false;
        }
        SWIGTYPE_p_PIX loadPix = loadPix(overlayImageFilePath);
        scoreImages.overlayImage = loadPix;
        if (loadPix != null) {
            return true;
        }
        Log.w(LOGTAG, "loadImagesFromSessionFolder() - overlay load failed");
        return false;
    }

    public boolean saveImagesToSessionFolder(String str, int i, Bitmap bitmap, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        if (str == null || bitmap == null || sWIGTYPE_p_PIX == null) {
            Log.w(LOGTAG, "saveImagesToSessionFolder() - invalid params");
            return false;
        } else if (!Utils.saveJpeg(bitmap, 70, getBackgroundImageFilePath(str, i))) {
            Log.w(LOGTAG, "saveImagesToSessionFolder() - background save failed");
            return false;
        } else if (savePix(sWIGTYPE_p_PIX, getOverlayImageFilePath(str, i))) {
            return true;
        } else {
            Log.w(LOGTAG, "saveImagesToSessionFolder() - overlay save failed");
            return false;
        }
    }

    public boolean insertImagesToSessionFolder(String str, int i, int i2, Bitmap bitmap, SWIGTYPE_p_PIX sWIGTYPE_p_PIX) {
        if (str == null || bitmap == null || sWIGTYPE_p_PIX == null) {
            Log.w(LOGTAG, "insertImagesToSessionFolder() - invalid params");
            return false;
        }
        while (i2 >= i) {
            File file = new File(getBackgroundImageFilePath(str, i2));
            if (file.exists()) {
                File file2 = new File(getBackgroundImageFilePath(str, i2 + 1));
                if (file2.exists()) {
                    file2.delete();
                }
                if (!file.renameTo(file2)) {
                    return false;
                }
            }
            File file3 = new File(getOverlayImageFilePath(str, i2));
            if (file3.exists()) {
                File file4 = new File(getOverlayImageFilePath(str, i2 + 1));
                if (file4.exists()) {
                    file4.delete();
                }
                if (!file3.renameTo(file4)) {
                    return false;
                }
            }
            i2--;
        }
        return saveImagesToSessionFolder(str, i, bitmap, sWIGTYPE_p_PIX);
    }

    public boolean removeImagesFromSessionFolder(String str, int i, int i2) {
        if (str == null) {
            Log.w(LOGTAG, "removeImagesFromSessionFolder() - invalid params");
            return false;
        }
        for (int i3 = i; i3 <= i2; i3++) {
            File file = new File(getBackgroundImageFilePath(str, i3));
            if (file.exists()) {
                if (i3 == i) {
                    file.delete();
                } else {
                    File file2 = new File(getBackgroundImageFilePath(str, i3 - 1));
                    if (file2.exists()) {
                        file2.delete();
                    }
                    if (!file.renameTo(file2)) {
                        return false;
                    }
                }
            }
            File file3 = new File(getOverlayImageFilePath(str, i3));
            if (file3.exists()) {
                if (i3 == i) {
                    file3.delete();
                } else {
                    File file4 = new File(getOverlayImageFilePath(str, i3 - 1));
                    if (file4.exists()) {
                        file4.delete();
                    }
                    if (!file3.renameTo(file4)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean safeRemovePage(String str, session session, int i) {
        session copySession;
        boolean z = false;
        if (!(str == null || session == null || i < 0 || i >= session.getN() || (copySession = copySession(session)) == null)) {
            Swig.sessionRemoveById(copySession, i);
            if (saveSession(copySession, str)) {
                removeImagesFromSessionFolder(str, i, session.getN() - 1);
                Swig.sessionRemoveById(session, i);
                z = true;
            }
            Swig.sessionDestroy(copySession);
        }
        return z;
    }

    private session copySession(session session) {
        if (JniSource.serialize(session, this.TMP_SESSION_PATH) == 0) {
            return JniSource.deserialize(this.TMP_SESSION_PATH);
        }
        return null;
    }

    public boolean saveTempImage(Bitmap bitmap) {
        return Utils.saveJpeg(bitmap, 70, this.LAST_IMAGE_PATH);
    }

    public Bitmap loadTempImage() {
        return Utils.loadJpeg(this.LAST_IMAGE_PATH);
    }

    public Uri getTempImageUri() {
        File file = new File(this.LAST_IMAGE_PATH);
        if (file.exists()) {
            return Uri.fromFile(file);
        }
        return null;
    }

    public String getBackgroundImageFilePath(String str, int i) {
        return this.SESSION_DIR + "/" + str + "/" + getBackgroundName(i);
    }

    private String getOverlayImageFilePath(String str, int i) {
        return this.SESSION_DIR + "/" + str + "/" + getOverlayName(i);
    }

    public String getBackgroundImageFilePathIos(String str, int i) {
        return this.SESSION_DIR + "/" + str + "/" + getBackgroundNameIos(i);
    }

    private String getOverlayImageFilePathIos(String str, int i) {
        return this.SESSION_DIR + "/" + str + "/" + getOverlayNameIos(i);
    }

    public boolean loadAssets() {
        return Utils.copyAssetFolder(this.m_Context, TEMPLATES_NAME, this.TEMPLATES_DIR) && Utils.copyAssetFolder(this.m_Context, NNMODELS_NAME, this.NNMODELS_DIR);
    }

    public boolean areAssetsLoaded() {
        return new File(this.TEMPLATES_DIR).exists() && new File(this.NNMODELS_DIR).exists();
    }

    public String getTemplatesDir() {
        return this.TEMPLATES_DIR;
    }

    public String getNNModelsDir() {
        return this.NNMODELS_DIR;
    }

    public String getTmpDir() {
        return this.TMP_DIR;
    }

    public String getSessionDir() {
        return this.SESSION_DIR;
    }

    public long getFreeSpace() {
        StatFs statFs = new StatFs(this.ROOT);
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
    }

    private String getBackgroundName(int i) {
        return String.format("background_%03d.jpeg", new Object[]{Integer.valueOf(i)});
    }

    private String getOverlayName(int i) {
        return String.format("overlay_%03d.png", new Object[]{Integer.valueOf(i)});
    }

    private String getBackgroundNameIos(int i) {
        return String.format("background_%02d.jpeg", new Object[]{Integer.valueOf(i)});
    }

    private String getOverlayNameIos(int i) {
        return String.format("overlay_%02d.png", new Object[]{Integer.valueOf(i)});
    }

    private boolean savePix(SWIGTYPE_p_PIX sWIGTYPE_p_PIX, String str) {
        if (JniLeptonica.pixWritePng(str, new Pix(SWIGTYPE_p_PIX.getCPtr(sWIGTYPE_p_PIX), false), Sequence.PPQ) == 0) {
            return true;
        }
        return false;
    }

    private SWIGTYPE_p_PIX loadPix(String str) {
        Pix pixRead = JniLeptonica.pixRead(str);
        if (pixRead == null) {
            return null;
        }
        return new SWIGTYPE_p_PIX(Pix.getCPtr(pixRead), false);
    }

    public boolean loadTestData() {
        return Utils.copyAssetFolder(this.m_Context, TEST_DATA_NAME, this.TEST_DATA_DIR);
    }

    public boolean isTestDataLoaded() {
        return new File(this.TEST_DATA_DIR).exists();
    }

    public String getTestDataDir() {
        return this.TEST_DATA_DIR;
    }

    public boolean isTestDbCreated() {
        return new File(this.EXAMPLE_SESSION_DIR).exists();
    }

    public void createTestDb() {
        String[] strArr = {"Greensleeves", "Beatles - Yesterday", "Smoke On The Water"};
        List<CdSession> cdGetAllSessions = cdGetAllSessions();
        for (int i = 0; i < cdGetAllSessions.size(); i++) {
            deleteSessionFolder(cdGetAllSessions.get(i).getSessionFolderName());
        }
        dbDeleteAll();
        Utils.copyAssetFolder(this.m_Context, EXAMPLE_SESSION_NAME, this.EXAMPLE_SESSION_DIR);
        this.m_Session = loadSession(EXAMPLE_SESSION_NAME);
        loadImagesFromSessionFolder(EXAMPLE_SESSION_NAME, 0, this.m_Images0);
        loadImagesFromSessionFolder(EXAMPLE_SESSION_NAME, 1, this.m_Images1);
        saveTempImage(this.m_Images0.backgroundImage);
        for (int i2 = 0; i2 < 3; i2++) {
            createTestSession(strArr[i2], i2);
        }
        copyTestSession("36pages");
        cdGetAllSessions();
    }

    private void copyTestSession(String str) {
        String createNewSessionName = createNewSessionName();
        String str2 = str;
        cdCreateSession(createNewSessionName, str2, 120, 0, 0);
        Context context = this.m_Context;
        Utils.copyAssetFolder(context, str2, this.SESSION_DIR + "/" + createNewSessionName);
    }

    private void createTestSession(String str, int i) {
        String createNewSessionName = createNewSessionName();
        cdCreateSession(createNewSessionName, str, 120, 0, 0);
        saveSession(this.m_Session, createNewSessionName);
        saveImagesToSessionFolder(createNewSessionName, 0, this.m_Images0.backgroundImage, this.m_Images0.overlayImage);
        saveImagesToSessionFolder(createNewSessionName, 1, this.m_Images1.backgroundImage, this.m_Images1.overlayImage);
    }
}
