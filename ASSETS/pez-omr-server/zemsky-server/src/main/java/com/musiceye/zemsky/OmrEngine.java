package com.musiceye.zemsky;

import com.xemsoft.sheetmusicscanner2.sources.JniSourceJNI;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonicaJNI;
import com.xemsoft.sheetmusicscanner2.analysis.Scanner;
import java.io.*;
import java.nio.file.*;

public class OmrEngine {

    private final String templatesDir;
    private final String ocrModelPath;
    private final String keySigCModelPath;
    private final String keySigDigitModelPath;
    private final String tmpDir;

    public OmrEngine(String templatesDir, String ocrModelPath,
                     String keySigCModelPath, String keySigDigitModelPath,
                     String tmpDir) {
        this.templatesDir         = templatesDir;
        this.ocrModelPath         = ocrModelPath;
        this.keySigCModelPath     = keySigCModelPath;
        this.keySigDigitModelPath = keySigDigitModelPath;
        this.tmpDir               = tmpDir;
    }

    /** Process an image file → MusicXML string. */
    public String processImage(String imagePath) throws Exception {
        System.out.println("[OmrEngine] processing: " + imagePath);

        long sessionPtr = JniSourceJNI.sessionCreate();
        if (sessionPtr == 0) throw new Exception("sessionCreate failed");

        // 1. Load image into Leptonica PIX*
        long pixPtr = JniLeptonicaJNI.pixRead(imagePath);
        if (pixPtr == 0) {
            JniSourceJNI.sessionDestroy(sessionPtr);
            throw new Exception("pixRead failed for: " + imagePath);
        }

        int w = JniLeptonicaJNI.pixGetWidth(pixPtr);
        int h = JniLeptonicaJNI.pixGetHeight(pixPtr);
        System.out.println("[OmrEngine] image " + w + "x" + h);

        // 2. Run engine
        Scanner scanner = new Scanner();
        long resultPtr;
        try {
            resultPtr = JniSourceJNI.nativeAnalyze(
                scanner,
                templatesDir, ocrModelPath, keySigCModelPath, keySigDigitModelPath,
                (float) w, 1.0f, pixPtr,
                sessionPtr, 0, "", 0
            );
        } finally {
            JniLeptonicaJNI.pixDestroy(pixPtr);
        }

        if (resultPtr == 0) {
            JniSourceJNI.sessionDestroy(sessionPtr);
            throw new Exception("nativeAnalyze returned null");
        }

        try {
            int code = JniSourceJNI.analysisResult_resultCode_get(resultPtr, new Object());
            System.out.println("[OmrEngine] result code: " + code);
            long scorePtr = JniSourceJNI.analysisResult_score_get(resultPtr, new Object());
            System.out.println("[OmrEngine] scorePtr from analysisResult: " + scorePtr);
            System.out.println("[OmrEngine] sessionPtr: " + sessionPtr);
            
            if (code == 2 && scorePtr == 0) {
                throw new Exception("No music content detected in image");
            }
            if (code == 2 && scorePtr != 0) {
                System.out.println("[OmrEngine] warning: result code=2 but score is present; attempting insert and export");
            }
            if (scorePtr == 0) throw new Exception("score pointer is null");

            // **CRITICAL:** Insert the score into the session BEFORE export
            System.out.println("[OmrEngine] calling sessionInsert(session=" + sessionPtr + ", score=" + scorePtr + ", index=0)");
            int insertResult = JniSourceJNI.sessionInsert(sessionPtr, scorePtr, 0);
            System.out.println("[OmrEngine] sessionInsert returned: " + insertResult);

            // 3. Export from the session that now contains the score
            Path xmlOut = Files.createTempFile(Paths.get(tmpDir), "zemsky_", ".xml");
            try {
                System.out.println("[OmrEngine] calling exportToMusicXml with sessionPtr=" + sessionPtr);
                int ok = JniSourceJNI.exportToMusicXml(
                    xmlOut.toString(), sessionPtr, null,
                    "Scanned Score", "120", 4, 4, 4, 1
                );
                System.out.println("[OmrEngine] exportToMusicXml returned: " + ok);
                if (ok == 0) throw new Exception("exportToMusicXml failed");

                byte[] bytes = Files.readAllBytes(xmlOut);
                String xml = new String(bytes, "UTF-8");
                System.out.println("[OmrEngine] XML output length: " + xml.length() + " chars");
                if (xml.trim().isEmpty()) {
                    throw new Exception("exportToMusicXml produced empty output");
                }

                System.out.println("[OmrEngine] produced " + xml.length() + " chars MusicXML");
                return xml;
            } finally {
                Files.deleteIfExists(xmlOut);
            }
        } finally {
            JniSourceJNI.delete_analysisResult(resultPtr);
            JniSourceJNI.sessionDestroy(sessionPtr);
        }
    }
}
