package com.xemsoft.sheetmusicscanner2.export;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ExportImportMsca {
    private static final int CURRENT_VERSION_INT = 3;
    private static final String LOGTAG = "ExportImportMsca";
    private static final String MSCA_DIR = "msca";
    private static final String MSCA_ZIP = "msca.zip";
    private static final String SESSIONS_CSV = "sessions.csv";
    private static final String SESSION_CSV = "session.csv";
    private static final int VERSION_2_INT = 2;
    private static final String VERSION_FILE = "version.txt";
    private Context m_Context;
    private List<Entry> m_EntryList;

    private class Entry {
        String epath;
        File file;

        public Entry(File file2, String str) {
            this.file = file2;
            this.epath = str;
        }
    }

    public ExportImportMsca(Context context) {
        this.m_Context = context;
    }

    public boolean exportToFilePath(String str, CdSession cdSession) {
        SessionUtility instance = SessionUtility.getInstance(this.m_Context);
        String str2 = instance.getTmpDir() + "/msca";
        File file = new File(str2);
        if (file.exists()) {
            Utils.removeDirectory(file);
        }
        file.mkdirs();
        ArrayList arrayList = new ArrayList();
        arrayList.add(cdSession);
        boolean sessionsToCsvFile = sessionsToCsvFile(arrayList, str2 + "/session.csv");
        if (!Utils.copyDirectory(instance.SESSION_DIR + "/" + cdSession.getSessionFolderName(), str2)) {
            sessionsToCsvFile = false;
        }
        if (sessionsToCsvFile) {
            sessionsToCsvFile = pack(str2, str);
        }
        Utils.removeDirectory(file);
        return sessionsToCsvFile;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean exportListToFilePath(java.lang.String r9, java.util.List<com.xemsoft.sheetmusicscanner2.persist.CdSession> r10) {
        /*
            r8 = this;
            android.content.Context r0 = r8.m_Context
            com.xemsoft.sheetmusicscanner2.persist.SessionUtility r0 = com.xemsoft.sheetmusicscanner2.persist.SessionUtility.getInstance(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r0.getTmpDir()
            r1.append(r2)
            java.lang.String r2 = "/msca"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.io.File r2 = new java.io.File
            r2.<init>(r1)
            boolean r3 = r2.exists()
            r4 = 1
            if (r3 != r4) goto L_0x002a
            com.xemsoft.sheetmusicscanner2.util.Utils.removeDirectory(r2)
        L_0x002a:
            r2.mkdirs()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r5 = "/version.txt"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "2"
            boolean r3 = com.xemsoft.sheetmusicscanner2.util.Utils.stringToFile((java.lang.String) r5, (java.lang.String) r3)
            if (r3 != r4) goto L_0x00a1
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r1)
            java.lang.String r5 = "/sessions.csv"
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            boolean r3 = r8.sessionsToCsvFile(r10, r3)
            if (r3 != r4) goto L_0x00a1
            java.util.Iterator r10 = r10.iterator()
        L_0x0061:
            boolean r3 = r10.hasNext()
            if (r3 == 0) goto L_0x00a1
            java.lang.Object r3 = r10.next()
            com.xemsoft.sheetmusicscanner2.persist.CdSession r3 = (com.xemsoft.sheetmusicscanner2.persist.CdSession) r3
            java.lang.String r3 = r3.getSessionFolderName()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r0.SESSION_DIR
            r5.append(r6)
            java.lang.String r6 = "/"
            r5.append(r6)
            r5.append(r3)
            java.lang.String r5 = r5.toString()
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r1)
            r7.append(r6)
            r7.append(r3)
            java.lang.String r3 = r7.toString()
            boolean r3 = com.xemsoft.sheetmusicscanner2.util.Utils.copyDirectory(r5, r3)
            if (r3 != 0) goto L_0x0061
            r10 = 0
            goto L_0x00a2
        L_0x00a1:
            r10 = r4
        L_0x00a2:
            if (r10 != r4) goto L_0x00a8
            boolean r10 = r8.pack(r1, r9)
        L_0x00a8:
            com.xemsoft.sheetmusicscanner2.util.Utils.removeDirectory(r2)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.export.ExportImportMsca.exportListToFilePath(java.lang.String, java.util.List):boolean");
    }

    public int importFromUri(Uri uri, List<String> list) {
        byte[] bArr = new byte[1024];
        String str = SessionUtility.getInstance(this.m_Context).getTmpDir() + "/msca";
        String str2 = str + "/msca.zip";
        File file = new File(str);
        if (file.exists()) {
            Utils.removeDirectory(file);
        }
        file.mkdirs();
        try {
            InputStream openInputStream = this.m_Context.getContentResolver().openInputStream(uri);
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            while (true) {
                int read = openInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            }
            openInputStream.close();
            fileOutputStream.close();
            int importFromFilePath = importFromFilePath(str2, list);
            if (file.exists()) {
                Utils.removeDirectory(file);
            }
            return importFromFilePath;
        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "importFromUri()", e);
            return 1;
        } catch (IOException e2) {
            Log.e(LOGTAG, "importFromUri()", e2);
            return 1;
        }
    }

    public int importFromFilePath(String str, List<String> list) {
        int version;
        File file;
        String str2 = SessionUtility.getInstance(this.m_Context).TMP_DIR + "/msca";
        if (!unpack(str, str2) || (version = getVersion(str2)) == -1) {
            return 1;
        }
        if (version < 2) {
            return 10;
        }
        if (version > 3) {
            return 9;
        }
        if (version == 3) {
            file = new File(str2 + "/session.csv");
        } else {
            file = new File(str2 + "/sessions.csv");
        }
        if (!file.exists()) {
            return 1;
        }
        ArrayList<CdSession> arrayList = new ArrayList<>();
        if (!parseSessions(file, arrayList)) {
            return 1;
        }
        for (CdSession importSession : arrayList) {
            importSession(importSession, version, str2, list);
        }
        return 0;
    }

    private int getVersion(String str) {
        File file = new File(str + "/version.txt");
        int parseVersionFile = file.exists() ? parseVersionFile(file) : -1;
        if (parseVersionFile == -1) {
            File file2 = new File(str + "/session.csv");
            if (file2.exists()) {
                return parseVersionCsv(file2);
            }
        }
        return parseVersionFile;
    }

    private int parseVersionFile(File file) {
        String fileToString = Utils.fileToString(file);
        if (fileToString == null) {
            return -1;
        }
        try {
            return Integer.parseInt(fileToString);
        } catch (NumberFormatException e) {
            Log.e(LOGTAG, "parseVersion()", e);
            return -1;
        }
    }

    private int parseVersionCsv(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            List<CSVRecord> records = CSVFormat.DEFAULT.parse(fileReader).getRecords();
            int i = 1;
            int i2 = -1;
            while (true) {
                if (i >= records.size()) {
                    break;
                }
                CSVRecord cSVRecord = records.get(i);
                if (cSVRecord.size() >= 9 && (i2 = Integer.parseInt(cSVRecord.get(8))) != 3) {
                    i2 = -1;
                    break;
                }
                i++;
            }
            fileReader.close();
            return i2;
        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "parseSessions()", e);
            return -1;
        } catch (IOException e2) {
            Log.e(LOGTAG, "parseSessions()", e2);
            return -1;
        } catch (NumberFormatException e3) {
            Log.e(LOGTAG, "parseSessions()", e3);
            return -1;
        }
    }

    /* JADX WARNING: type inference failed for: r18v6 */
    /* JADX WARNING: type inference failed for: r22v2 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean sessionsToCsvFile(java.util.List<com.xemsoft.sheetmusicscanner2.persist.CdSession> r21, java.lang.String r22) {
        /*
            r20 = this;
            r1 = 0
            org.apache.commons.csv.CSVPrinter r2 = new org.apache.commons.csv.CSVPrinter     // Catch:{ IOException -> 0x00c4 }
            java.io.FileWriter r0 = new java.io.FileWriter     // Catch:{ IOException -> 0x00c4 }
            r3 = r22
            r0.<init>(r3)     // Catch:{ IOException -> 0x00c4 }
            org.apache.commons.csv.CSVFormat r3 = org.apache.commons.csv.CSVFormat.EXCEL     // Catch:{ IOException -> 0x00c4 }
            r2.<init>(r0, r3)     // Catch:{ IOException -> 0x00c4 }
            r0 = 9
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "bpm"
            r3[r1] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "displayName"
            r5 = 1
            r3[r5] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "instrument"
            r6 = 2
            r3[r6] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "instrumentPitch"
            r7 = 3
            r3[r7] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "lastEdited"
            r8 = 4
            r3[r8] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "multipleVoicesOn"
            r9 = 5
            r3[r9] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "order"
            r10 = 6
            r3[r10] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "sessionFolderName"
            r11 = 7
            r3[r11] = r4     // Catch:{ all -> 0x00b5 }
            java.lang.String r4 = "version"
            r12 = 8
            r3[r12] = r4     // Catch:{ all -> 0x00b5 }
            r2.printRecord((java.lang.Object[]) r3)     // Catch:{ all -> 0x00b5 }
            java.util.Iterator r3 = r21.iterator()     // Catch:{ all -> 0x00b5 }
        L_0x0047:
            boolean r4 = r3.hasNext()     // Catch:{ all -> 0x00b5 }
            if (r4 == 0) goto L_0x00ad
            java.lang.Object r4 = r3.next()     // Catch:{ all -> 0x00b5 }
            com.xemsoft.sheetmusicscanner2.persist.CdSession r4 = (com.xemsoft.sheetmusicscanner2.persist.CdSession) r4     // Catch:{ all -> 0x00b5 }
            int r13 = r4.getBpm()     // Catch:{ all -> 0x00b5 }
            java.lang.String r13 = java.lang.Integer.toString(r13)     // Catch:{ all -> 0x00b5 }
            java.lang.String r14 = r4.getDisplayName()     // Catch:{ all -> 0x00b5 }
            int r15 = r4.getInstrument()     // Catch:{ all -> 0x00b5 }
            java.lang.String r15 = java.lang.Integer.toString(r15)     // Catch:{ all -> 0x00b5 }
            int r16 = r4.getInstrumentPitch()     // Catch:{ all -> 0x00b5 }
            java.lang.String r16 = java.lang.Integer.toString(r16)     // Catch:{ all -> 0x00b5 }
            long r17 = r4.getLastEdited()     // Catch:{ all -> 0x00b5 }
            java.lang.String r17 = java.lang.Long.toString(r17)     // Catch:{ all -> 0x00b5 }
            r18 = r1
            int r1 = r4.multipleVoicesOn     // Catch:{ all -> 0x00ab }
            java.lang.String r1 = java.lang.Integer.toString(r1)     // Catch:{ all -> 0x00ab }
            int r4 = r4.getOrder()     // Catch:{ all -> 0x00ab }
            java.lang.String r4 = java.lang.Integer.toString(r4)     // Catch:{ all -> 0x00ab }
            java.lang.Integer r19 = java.lang.Integer.valueOf(r7)     // Catch:{ all -> 0x00ab }
            r22 = r5
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch:{ all -> 0x00ab }
            r5[r18] = r13     // Catch:{ all -> 0x00ab }
            r5[r22] = r14     // Catch:{ all -> 0x00ab }
            r5[r6] = r15     // Catch:{ all -> 0x00ab }
            r5[r7] = r16     // Catch:{ all -> 0x00ab }
            r5[r8] = r17     // Catch:{ all -> 0x00ab }
            r5[r9] = r1     // Catch:{ all -> 0x00ab }
            r5[r10] = r4     // Catch:{ all -> 0x00ab }
            java.lang.String r1 = ""
            r5[r11] = r1     // Catch:{ all -> 0x00ab }
            r5[r12] = r19     // Catch:{ all -> 0x00ab }
            r2.printRecord((java.lang.Object[]) r5)     // Catch:{ all -> 0x00ab }
            r5 = r22
            r1 = r18
            goto L_0x0047
        L_0x00ab:
            r0 = move-exception
            goto L_0x00b8
        L_0x00ad:
            r18 = r1
            r22 = r5
            r2.close()     // Catch:{ IOException -> 0x00c2 }
            return r22
        L_0x00b5:
            r0 = move-exception
            r18 = r1
        L_0x00b8:
            r1 = r0
            r2.close()     // Catch:{ all -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch:{ IOException -> 0x00c2 }
        L_0x00c1:
            throw r1     // Catch:{ IOException -> 0x00c2 }
        L_0x00c2:
            r0 = move-exception
            goto L_0x00c7
        L_0x00c4:
            r0 = move-exception
            r18 = r1
        L_0x00c7:
            java.lang.String r1 = LOGTAG
            java.lang.String r2 = "sessionsToCsvFile()"
            android.util.Log.e(r1, r2, r0)
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.export.ExportImportMsca.sessionsToCsvFile(java.util.List, java.lang.String):boolean");
    }

    private boolean parseSessions(File file, List<CdSession> list) {
        list.clear();
        try {
            FileReader fileReader = new FileReader(file);
            List<CSVRecord> records = CSVFormat.DEFAULT.parse(fileReader).getRecords();
            boolean z = true;
            int i = 1;
            while (true) {
                if (i >= records.size()) {
                    break;
                }
                CSVRecord cSVRecord = records.get(i);
                if (cSVRecord.size() < 8) {
                    z = false;
                    break;
                }
                CdSession cdSession = new CdSession();
                cdSession.setBpm(Integer.parseInt(cSVRecord.get(0)));
                cdSession.setDisplayName(cSVRecord.get(1));
                cdSession.setInstrument(Integer.parseInt(cSVRecord.get(2)));
                cdSession.setInstrumentPitch(Integer.parseInt(cSVRecord.get(3)));
                cdSession.setMultipleVoicesOn(Integer.parseInt(cSVRecord.get(5)) != 0);
                cdSession.setOrder(Integer.parseInt(cSVRecord.get(6)));
                cdSession.setSessionFolderName(cSVRecord.get(7));
                list.add(cdSession);
                i++;
            }
            fileReader.close();
            return z;
        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "parseSessions()", e);
            return false;
        } catch (IOException e2) {
            Log.e(LOGTAG, "parseSessions()", e2);
            return false;
        } catch (NumberFormatException e3) {
            Log.e(LOGTAG, "parseSessions()", e3);
            return false;
        }
    }

    private boolean importSession(CdSession cdSession, int i, String str, List<String> list) {
        SessionUtility instance = SessionUtility.getInstance(this.m_Context);
        if (i != 3) {
            str = str + "/" + cdSession.getSessionFolderName();
        }
        String createNewSessionName = instance.createNewSessionName();
        String str2 = instance.getSessionDir() + "/" + createNewSessionName;
        if (!new File(str).exists()) {
            return false;
        }
        if (i == 3) {
            File file = new File(str + "/msca.zip");
            if (file.exists()) {
                file.delete();
            }
        }
        if (!Utils.copyDirectory(str, str2)) {
            return false;
        }
        cdSession.setSessionFolderName(createNewSessionName);
        if (instance.dbCreateCdSession(cdSession) == -1) {
            return false;
        }
        list.add(createNewSessionName);
        return true;
    }

    private void listDirFiles(File file, String str) {
        String str2;
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (str == null) {
                    str2 = file2.getName();
                } else {
                    str2 = str + "/" + file2.getName();
                }
                if (file2.isDirectory()) {
                    listDirFiles(file2, str2);
                } else {
                    this.m_EntryList.add(new Entry(file2, str2));
                }
            }
        }
    }

    private boolean pack(String str, String str2) {
        this.m_EntryList = new ArrayList();
        listDirFiles(new File(str), (String) null);
        try {
            ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream((OutputStream) new FileOutputStream(str2));
            for (Entry next : this.m_EntryList) {
                ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(next.file, next.epath);
                FileInputStream fileInputStream = new FileInputStream(next.file);
                zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
                IOUtils.copy((InputStream) fileInputStream, (OutputStream) zipArchiveOutputStream);
                zipArchiveOutputStream.closeArchiveEntry();
            }
            zipArchiveOutputStream.finish();
            return true;
        } catch (IOException e) {
            Log.e(LOGTAG, "pack()", e);
            return false;
        }
    }

    private boolean unpack(String str, String str2) {
        new File(str2).mkdirs();
        try {
            ZipArchiveInputStream zipArchiveInputStream = new ZipArchiveInputStream(new BufferedInputStream(new FileInputStream(str)));
            while (true) {
                ZipArchiveEntry nextZipEntry = zipArchiveInputStream.getNextZipEntry();
                if (nextZipEntry != null) {
                    String str3 = str2 + "/" + nextZipEntry.getName();
                    String str4 = LOGTAG;
                    Log.d(str4, "Entry name: " + nextZipEntry.getName());
                    Log.d(str4, "Is directory: " + nextZipEntry.isDirectory());
                    Log.d(str4, "Compressed size: " + nextZipEntry.getCompressedSize());
                    Log.d(str4, "Plain size: " + nextZipEntry.getSize());
                    Log.d(str4, "Out file: " + str3);
                    if (nextZipEntry.isDirectory()) {
                        new File(str3).mkdirs();
                    } else {
                        new File(str3).getParentFile().mkdirs();
                        FileOutputStream fileOutputStream = new FileOutputStream(str3);
                        IOUtils.copy((InputStream) zipArchiveInputStream, (OutputStream) fileOutputStream);
                        fileOutputStream.close();
                    }
                } else {
                    zipArchiveInputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            Log.e(LOGTAG, "unpack()", e);
            return false;
        }
    }
}
