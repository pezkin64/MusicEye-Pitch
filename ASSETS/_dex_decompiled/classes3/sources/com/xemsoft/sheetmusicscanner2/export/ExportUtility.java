package com.xemsoft.sheetmusicscanner2.export;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.PlayerSettings;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.util.FlurryUtils;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import jp.kshoji.javax.sound.midi.io.StandardMidiFileWriter;

public class ExportUtility {
    private static long DAY_SECONDS = 86400;
    private static final String LOGTAG = "ExportUtility.java";
    private static ExportUtility m_Instance;
    private String EXPORT_DIR;
    private Context m_Context;
    private SessionUtility m_SUtil;
    private UserSettings m_Settings;

    public interface ExportListener {
        void onDone(String str, int i);
    }

    public ExportUtility(Context context) {
        this.m_Context = context;
        this.m_SUtil = SessionUtility.getInstance(context);
        this.m_Settings = UserSettings.getInstance(context);
        this.EXPORT_DIR = context.getExternalFilesDir((String) null).getAbsolutePath() + "/export";
        new File(this.EXPORT_DIR).mkdirs();
    }

    public static ExportUtility getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new ExportUtility(context);
        }
        return m_Instance;
    }

    public void exportFileAsync(CdSession cdSession, int i, String str, int i2, ExportListener exportListener) {
        new ExportTask(cdSession, i, i2, str, exportListener).execute(new Void[0]);
    }

    public void deleteOldFiles() {
        for (File file : new File(this.EXPORT_DIR).listFiles()) {
            if (file.isDirectory() && isDirDeletable(file)) {
                deleteDir(file);
            }
        }
    }

    public void deleteFile(String str) {
        File file = new File(str);
        File parentFile = file.getParentFile();
        file.delete();
        if (parentFile.exists() && parentFile.isDirectory()) {
            parentFile.delete();
        }
    }

    /* access modifiers changed from: private */
    public String exportFile(CdSession cdSession, int i, String str, int i2) {
        boolean z;
        String str2;
        CdSession cdSession2 = cdSession;
        String str3 = str;
        int i3 = i2;
        String str4 = this.EXPORT_DIR + "/" + getCurrentTimeStamp();
        File file = new File(str4);
        file.mkdirs();
        session loadSession = this.m_SUtil.loadSession(cdSession2.getSessionFolderName());
        String str5 = str4 + "/" + str3.replaceAll("[/\\?%*|\"<>]", "-") + ExportTypes.getExtension(i3);
        int bpm = cdSession2.getBpm();
        double pitch = (double) this.m_Settings.getPitch();
        FlurryUtils.valueEvent("Export", "format", ExportTypes.getMimeType(i3));
        if (i3 == 1) {
            PlayerSettings playerSettings = cdSession2.playerSettings;
            str2 = str5;
            z = exportToMidiFilePath(str2, loadSession, bpm, pitch, playerSettings, i);
        } else if (i3 != 5) {
            if (i3 == 6) {
                z = exportToPDFFilePath(str5, cdSession2.getSessionFolderName(), str3, loadSession.getN());
            } else if (i3 == 7) {
                z = new ExportImportMsca(this.m_Context).exportToFilePath(str5, cdSession2);
            } else if (i3 != 8) {
                PlayerSettings playerSettings2 = cdSession2.playerSettings;
                str2 = str5;
                z = exportToAudioFilePath(str2, i3, loadSession, bpm, pitch, playerSettings2, i);
            } else {
                ExportImportMsca exportImportMsca = new ExportImportMsca(this.m_Context);
                ArrayList arrayList = new ArrayList();
                arrayList.add(cdSession2);
                z = exportImportMsca.exportListToFilePath(str5, arrayList);
            }
            str2 = str5;
        } else {
            session session = loadSession;
            PlayerSettings playerSettings3 = cdSession2.playerSettings;
            str2 = str5;
            z = exportToMusicXmlFilePath2(str2, session, str3, bpm, playerSettings3, i);
        }
        if (z) {
            return str2;
        }
        deleteDir(file);
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0060 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0061 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean exportToMusicXmlFilePath(java.lang.String r11, com.xemsoft.sheetmusicscanner2.sources.session r12, java.lang.String r13, int r14, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings r15, int r16) {
        /*
            r10 = this;
            r7 = r16
            com.xemsoft.sheetmusicscanner2.persist.MixerSettings r0 = r15.mixer
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.TrackSettings> r0 = r0.tracks
            r1 = -1
            if (r7 == r1) goto L_0x0016
            int r15 = r0.size()
            if (r15 <= r7) goto L_0x0032
            java.lang.Object r15 = r0.get(r7)
            com.xemsoft.sheetmusicscanner2.persist.TrackSettings r15 = (com.xemsoft.sheetmusicscanner2.persist.TrackSettings) r15
            goto L_0x0033
        L_0x0016:
            com.xemsoft.sheetmusicscanner2.persist.MixerSettings r15 = r15.mixer
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.TrackSettings> r15 = r15.tracks
            java.util.Iterator r15 = r15.iterator()
        L_0x001e:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L_0x0032
            java.lang.Object r0 = r15.next()
            com.xemsoft.sheetmusicscanner2.persist.TrackSettings r0 = (com.xemsoft.sheetmusicscanner2.persist.TrackSettings) r0
            boolean r1 = r0.isAudible()
            if (r1 == 0) goto L_0x001e
            r15 = r0
            goto L_0x0033
        L_0x0032:
            r15 = 0
        L_0x0033:
            r8 = 0
            if (r15 == 0) goto L_0x0039
            int r0 = r15.instrumentProgram
            goto L_0x003a
        L_0x0039:
            r0 = r8
        L_0x003a:
            if (r15 == 0) goto L_0x0040
            int r15 = r15.instrumentPitch
            r5 = r15
            goto L_0x0041
        L_0x0040:
            r5 = r8
        L_0x0041:
            android.content.Context r15 = r10.m_Context
            com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility r15 = com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility.getInstance(r15)
            com.xemsoft.sheetmusicscanner2.player.sound.Instrument r15 = r15.findInstrumentByProgram(r0)
            java.lang.String r3 = r15.getName()
            int r15 = com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager.getGmInstrumentProgram(r0)
            r9 = 1
            int r4 = r15 + 1
            r0 = r11
            r1 = r12
            r2 = r13
            r6 = r14
            int r11 = com.xemsoft.sheetmusicscanner2.sources.JniSource.exportToMusicXml(r0, r1, r2, r3, r4, r5, r6, r7)
            if (r11 != 0) goto L_0x0061
            return r9
        L_0x0061:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.export.ExportUtility.exportToMusicXmlFilePath(java.lang.String, com.xemsoft.sheetmusicscanner2.sources.session, java.lang.String, int, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings, int):boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v0, resolved type: boolean[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean exportToMusicXmlFilePath2(java.lang.String r17, com.xemsoft.sheetmusicscanner2.sources.session r18, java.lang.String r19, int r20, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings r21, int r22) {
        /*
            r16 = this;
            r0 = r21
            r1 = r16
            r2 = r22
            android.content.Context r3 = r1.m_Context
            com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility r3 = com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility.getInstance(r3)
            int r4 = com.xemsoft.sheetmusicscanner2.sources.JniSource.sessionGetMaxVoiceIndex(r18)
            r5 = 1
            int r4 = r4 + r5
            java.lang.String[] r10 = new java.lang.String[r4]
            int[] r11 = new int[r4]
            int[] r12 = new int[r4]
            r6 = 2
            int[] r6 = new int[r6]
            r7 = 3
            r6[r5] = r7
            r14 = 0
            r6[r14] = r4
            java.lang.Class r4 = java.lang.Boolean.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r4, r6)
            r13 = r4
            boolean[][] r13 = (boolean[][]) r13
            r4 = r14
        L_0x002b:
            com.xemsoft.sheetmusicscanner2.persist.MixerSettings r6 = r0.mixer
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.TrackSettings> r6 = r6.tracks
            int r6 = r6.size()
            if (r4 >= r6) goto L_0x0087
            com.xemsoft.sheetmusicscanner2.persist.MixerSettings r6 = r0.mixer
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.TrackSettings> r6 = r6.tracks
            java.lang.Object r6 = r6.get(r4)
            com.xemsoft.sheetmusicscanner2.persist.TrackSettings r6 = (com.xemsoft.sheetmusicscanner2.persist.TrackSettings) r6
            int r7 = r6.instrumentProgram
            com.xemsoft.sheetmusicscanner2.player.sound.Instrument r7 = r3.findInstrumentByProgram(r7)
            java.lang.String r7 = r7.getName()
            r10[r4] = r7
            int r7 = r6.instrumentProgram
            int r7 = com.xemsoft.sheetmusicscanner2.export.VoiceTrackManager.getGmInstrumentProgram(r7)
            r11[r4] = r7
            int r7 = r6.instrumentPitch
            r12[r4] = r7
            r7 = r13[r4]
            boolean r8 = r6.isSwitchedToMultiVoice
            r7[r14] = r8
            r7 = r14
        L_0x005e:
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.VoiceSettings> r8 = r6.voices
            int r8 = r8.size()
            if (r7 >= r8) goto L_0x0084
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.VoiceSettings> r8 = r6.voices
            java.lang.Object r8 = r8.get(r7)
            com.xemsoft.sheetmusicscanner2.persist.VoiceSettings r8 = (com.xemsoft.sheetmusicscanner2.persist.VoiceSettings) r8
            r9 = r13[r4]
            int r7 = r7 + 1
            r15 = -1
            if (r2 == r15) goto L_0x0077
            if (r2 != r4) goto L_0x007d
        L_0x0077:
            boolean r8 = r8.isMuted
            if (r8 != 0) goto L_0x007d
            r8 = r5
            goto L_0x007e
        L_0x007d:
            r8 = r14
        L_0x007e:
            r9[r7] = r8
            boolean r8 = r6.isSwitchedToMultiVoice
            if (r8 != 0) goto L_0x005e
        L_0x0084:
            int r4 = r4 + 1
            goto L_0x002b
        L_0x0087:
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            com.xemsoft.sheetmusicscanner2.sources.JniSource.exportToMusicXml2(r6, r7, r8, r9, r10, r11, r12, r13)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.export.ExportUtility.exportToMusicXmlFilePath2(java.lang.String, com.xemsoft.sheetmusicscanner2.sources.session, java.lang.String, int, com.xemsoft.sheetmusicscanner2.persist.PlayerSettings, int):boolean");
    }

    public boolean exportToMidiFilePath(String str, session session, int i, double d, PlayerSettings playerSettings, int i2) {
        SequenceData musicSequenceCreateWithSession = new ExportToMidi(this.m_Context).musicSequenceCreateWithSession(session, i, d, playerSettings, i2, false);
        if (!(musicSequenceCreateWithSession == null || musicSequenceCreateWithSession.m_Sequence == null)) {
            StandardMidiFileWriter standardMidiFileWriter = new StandardMidiFileWriter();
            new File(str).delete();
            try {
                standardMidiFileWriter.write(musicSequenceCreateWithSession.m_Sequence, 1, new File(str));
                return true;
            } catch (IOException e) {
                Log.e(LOGTAG, "exportToMidiFilePath()", e);
            }
        }
        return false;
    }

    public boolean exportToAudioFilePath(String str, int i, session session, int i2, double d, PlayerSettings playerSettings, int i3) {
        SequenceData musicSequenceCreateWithSession = new ExportToMidi(this.m_Context).musicSequenceCreateWithSession(session, i2, d, playerSettings, i3, true);
        if (musicSequenceCreateWithSession == null || musicSequenceCreateWithSession.m_Sequence == null) {
            return false;
        }
        return new ExportToAudio(this.m_Context).midiToAudio(musicSequenceCreateWithSession.m_Sequence, musicSequenceCreateWithSession.m_Programs, i, str);
    }

    private boolean exportToPDFFilePath(String str, String str2, String str3, int i) {
        boolean z;
        PdfDocument pdfDocument = new PdfDocument();
        boolean z2 = false;
        int i2 = 0;
        while (i2 < i) {
            String backgroundImageFilePath = this.m_SUtil.getBackgroundImageFilePath(str2, i2);
            if (backgroundImageFilePath == null) {
                Log.e(LOGTAG, "exportToPDFFilePath() - path null");
            } else {
                Bitmap loadJpeg = Utils.loadJpeg(backgroundImageFilePath);
                if (loadJpeg == null) {
                    Log.e(LOGTAG, "exportToPDFFilePath() - pageImage null");
                } else {
                    float width = 866.1417f / ((float) loadJpeg.getWidth());
                    i2++;
                    PdfDocument.Page startPage = pdfDocument.startPage(new PdfDocument.PageInfo.Builder((int) 909.4488f, (int) ((((float) loadJpeg.getHeight()) * width) + 43.307087f), i2).create());
                    Canvas canvas = startPage.getCanvas();
                    canvas.drawColor(-1);
                    canvas.drawBitmap(loadJpeg, (Rect) null, new RectF(21.653543f, 21.653543f, (((float) loadJpeg.getWidth()) * width) + 21.653543f, (width * ((float) loadJpeg.getHeight())) + 21.653543f), (Paint) null);
                    pdfDocument.finishPage(startPage);
                }
            }
            z = false;
        }
        z = true;
        try {
            pdfDocument.writeTo(new FileOutputStream(str));
            z2 = z;
        } catch (IOException unused) {
            Log.e(LOGTAG, "exportToPDFFilePath() - write failed");
        }
        pdfDocument.close();
        return z2;
    }

    private String getCurrentTimeStamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }

    private boolean isDirDeletable(File file) {
        return Long.valueOf(file.getName()).longValue() + DAY_SECONDS < System.currentTimeMillis() / 1000;
    }

    private void deleteDir(File file) {
        for (File file2 : file.listFiles()) {
            if (file2.isFile()) {
                file2.delete();
            }
        }
        file.delete();
    }

    private class ExportTask extends AsyncTask<Void, Void, String> {
        private int m_ActiveVoiceIndex;
        private CdSession m_CdSession;
        private ExportListener m_Listener;
        private String m_Name;
        private int m_Type;

        public ExportTask(CdSession cdSession, int i, int i2, String str, ExportListener exportListener) {
            this.m_CdSession = cdSession;
            this.m_ActiveVoiceIndex = i;
            this.m_Type = i2;
            this.m_Name = str;
            this.m_Listener = exportListener;
        }

        /* access modifiers changed from: protected */
        public String doInBackground(Void... voidArr) {
            return ExportUtility.this.exportFile(this.m_CdSession, this.m_ActiveVoiceIndex, this.m_Name, this.m_Type);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(String str) {
            super.onPostExecute(str);
            ExportListener exportListener = this.m_Listener;
            if (exportListener != null) {
                exportListener.onDone(str, this.m_Type);
            }
        }
    }
}
