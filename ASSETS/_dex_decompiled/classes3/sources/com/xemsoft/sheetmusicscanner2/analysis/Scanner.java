package com.xemsoft.sheetmusicscanner2.analysis;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.player.Global;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_PIX;
import com.xemsoft.sheetmusicscanner2.sources.SWIGTYPE_p_p_session;
import com.xemsoft.sheetmusicscanner2.sources.analysisResult;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.Swig;
import com.xemsoft.sheetmusicscanner2.util.Utils;

public class Scanner {
    private static final String LOGTAG = "Scanner";
    public static final int PREPROCESSOR_DISTORTED = JniSource.getPREPROCESSOR_DISTORTED();
    public static final int PREPROCESSOR_PIX_EMPTY = JniSource.getPREPROCESSOR_PIX_EMPTY();
    public static final int PREPROCESSOR_RESOLUTION_LOW = JniSource.getPREPROCESSOR_RESOLUTION_LOW();
    public static final int PREPROCESSOR_ROTATED = JniSource.getPREPROCESSOR_ROTATED();
    public static final int SUCCESS = 0;
    private Context m_Context;
    private volatile boolean m_IsScanCanceled = false;
    /* access modifiers changed from: private */
    public ScanListener m_Listener = null;
    private int m_PageIndex = 0;
    private SessionUtility m_SUtil;
    private ScanTask m_ScanTask;

    public interface ScanListener {
        void onDone(analysisResult analysisresult);

        void onProgress(int i);
    }

    public Scanner(Context context) {
        this.m_Context = context;
        this.m_SUtil = SessionUtility.getInstance(context);
    }

    public void scan(Bitmap bitmap, ScanListener scanListener) {
        this.m_Listener = scanListener;
        ScanTask scanTask = new ScanTask(bitmap);
        this.m_ScanTask = scanTask;
        scanTask.execute(new Void[0]);
    }

    public void cancelScan() {
        this.m_IsScanCanceled = true;
    }

    /* access modifiers changed from: private */
    public analysisResult scanBitmap(Bitmap bitmap) {
        Pix readBitmap;
        this.m_IsScanCanceled = false;
        String str = LOGTAG;
        Log.d(str, "scanBitmap()");
        SWIGTYPE_p_p_session sWIGTYPE_p_p_session = null;
        if (bitmap == null || (readBitmap = JniSource.readBitmap(bitmap)) == null) {
            return null;
        }
        SWIGTYPE_p_PIX sWIGTYPE_p_PIX = new SWIGTYPE_p_PIX(Pix.getCPtr(readBitmap), false);
        Global instance = Global.getInstance();
        session session = instance.getSession(this.m_Context);
        if (session != null) {
            sWIGTYPE_p_p_session = new SWIGTYPE_p_p_session(session.getCPtr(session), false);
        }
        SWIGTYPE_p_p_session sWIGTYPE_p_p_session2 = sWIGTYPE_p_p_session;
        int insertAtIndex = instance.getInsertAtIndex();
        if (session != null) {
            Log.d(str, "session->n = " + session.getN() + "  insertIdx = " + insertAtIndex);
        } else {
            Log.d(str, "session = null  insertIdx = " + insertAtIndex);
        }
        String testDataDir = this.m_SUtil.getTestDataDir();
        int i = this.m_PageIndex;
        this.m_PageIndex = i + 1;
        analysisResult analyze = JniSource.analyze(this, this.m_SUtil.getTemplatesDir(), this.m_SUtil.getNNModelsDir() + "/ocr_model.json", this.m_SUtil.getNNModelsDir() + "/keySignatures_c_model.json", this.m_SUtil.getNNModelsDir() + "/keySignatures_digit_model.json", (float) Utils.getScreenMinWidth(this.m_Context), 1.0f, sWIGTYPE_p_PIX, sWIGTYPE_p_p_session2, insertAtIndex, testDataDir, i);
        Swig.pixDestroy(readBitmap);
        if (session != null) {
            Swig.sessionDestroy(session);
        }
        return analyze;
    }

    public void reportScanProgress(int i) {
        ScanListener scanListener = this.m_Listener;
        if (scanListener != null) {
            scanListener.onProgress(i);
        }
    }

    public boolean isScanCanceled() {
        return this.m_IsScanCanceled;
    }

    private class ScanTask extends AsyncTask<Void, Integer, analysisResult> {
        Bitmap m_Bmp;

        public ScanTask(Bitmap bitmap) {
            this.m_Bmp = bitmap;
        }

        /* access modifiers changed from: protected */
        public analysisResult doInBackground(Void... voidArr) {
            Bitmap bitmap = this.m_Bmp;
            if (bitmap == null) {
                return null;
            }
            return Scanner.this.scanBitmap(bitmap);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(analysisResult analysisresult) {
            if (Scanner.this.m_Listener != null) {
                Scanner.this.m_Listener.onDone(analysisresult);
            }
        }
    }
}
