package com.musiceye.zemsky;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.javalin.Javalin;
import io.javalin.http.UploadedFile;

import java.io.*;
import java.nio.file.*;
import java.util.UUID;

/**
 * ZemskyServer — HTTP server wrapping libsource-lib.so.
 *
 * API (identical to audiveris server.py):
 *   GET  /docs      → { status: "ok", engine: "zemsky" }
 *   POST /process   → { musicxml: "...", notePositions: null, processedImage: null }
 */
public class ZemskyServer {

    static OmrEngine engine;
    static final ObjectMapper JSON = new ObjectMapper();

    // Asset paths from environment (with Docker defaults)
    static String SO_DIR      = env("ZEMSKY_SO_DIR",    "/native");
    static String ASSETS_DIR  = env("ZEMSKY_ASSETS_DIR","/assets");
    static String TMP_DIR     = env("ZEMSKY_TMP_DIR",   "/tmp/zemsky");
    static int    PORT        = Integer.parseInt(env("ZEMSKY_PORT", "8000"));

    public static void main(String[] args) throws Exception {
        System.out.println("[ZemskyServer] starting — loading native libs from " + SO_DIR);

        // Load .so files in dependency order
        System.load(SO_DIR + "/liblept.so");
        System.load(SO_DIR + "/libjpgt.so");
        System.load(SO_DIR + "/libpngt.so");
        System.load(SO_DIR + "/libsource-lib.so");
        System.out.println("[ZemskyServer] native libs loaded ✓");

        // Validate assets
        String templatesDir  = ASSETS_DIR + "/templates";
        String ocrModel      = ASSETS_DIR + "/nnModels/ocr_model.json";
        String keySigCModel  = ASSETS_DIR + "/nnModels/keySignatures_c_model.json";
        String keySigDModel  = ASSETS_DIR + "/nnModels/keySignatures_digit_model.json";

        for (String p : new String[]{templatesDir, ocrModel, keySigCModel, keySigDModel}) {
            if (!new File(p).exists()) throw new RuntimeException("Missing asset: " + p);
        }

        // Create tmp dir
        new File(TMP_DIR).mkdirs();

        // Create engine
        engine = new OmrEngine(templatesDir, ocrModel, keySigCModel, keySigDModel, TMP_DIR);
        System.out.println("[ZemskyServer] engine ready ✓");

        // Start HTTP server
        Javalin app = Javalin.create(cfg -> {
            cfg.showJavalinBanner = false;
            cfg.http.maxRequestSize = 50 * 1024 * 1024L;
        });

        app.get("/", ctx -> ctx.json(
            json("service","Zemsky OMR Server","version","1.0.0","status","ready")));

        // Health check — matches AudiverisService._getDocsUrl()
        app.get("/docs", ctx -> ctx.json(
            json("status","ok","engine","zemsky")));

        // Main OMR endpoint — matches AudiverisService._getProcessUrl()
        app.post("/process", ZemskyServer::handleProcess);

        app.exception(Exception.class, (e, ctx) -> {
            e.printStackTrace();
            ctx.status(500).result(errorJson("Internal error: " + e.getMessage()));
        });

        app.start("0.0.0.0", PORT);
        System.out.println("[ZemskyServer] listening on :" + PORT + " ✓");
    }

    static void handleProcess(io.javalin.http.Context ctx) {
        String jobId = UUID.randomUUID().toString().substring(0, 8);
        Path work = Paths.get(TMP_DIR, jobId);

        try {
            Files.createDirectories(work);

            UploadedFile upload = ctx.uploadedFile("file");
            if (upload == null) {
                ctx.status(400).result(errorJson("No 'file' field in multipart upload"));
                return;
            }

            // Save uploaded file
            String name = upload.filename() != null ? upload.filename() : "image.png";
            String ext  = name.contains(".") ? name.substring(name.lastIndexOf('.')) : ".png";
            Path input  = work.resolve("input" + ext);

            try (InputStream in = upload.content();
                 OutputStream out = Files.newOutputStream(input)) {
                in.transferTo(out);
            }

            System.out.println("[" + jobId + "] received " + name + " (" + Files.size(input) + "B)");

            // Run OMR
            String musicXml = engine.processImage(input.toString());

            // Return same JSON shape as audiveris server.py
            ObjectNode resp = JSON.createObjectNode();
            resp.put("musicxml", musicXml);
            resp.putNull("notePositions");
            resp.putNull("processedImage");

            ctx.status(200).contentType("application/json").result(resp.toString());

        } catch (Exception e) {
            System.err.println("[" + jobId + "] error: " + e.getMessage());
            boolean noMusic = e.getMessage() != null && e.getMessage().contains("No music");
            ctx.status(noMusic ? 422 : 500).result(errorJson(e.getMessage()));
        } finally {
            deleteDir(work.toFile());
        }
    }

    // ── helpers ───────────────────────────────────────────────────────────────
    static String env(String k, String def) {
        String v = System.getenv(k);
        return (v != null && !v.isEmpty()) ? v : def;
    }

    static String json(String... kv) {
        ObjectNode n = JSON.createObjectNode();
        for (int i = 0; i < kv.length; i += 2) n.put(kv[i], kv[i+1]);
        return n.toString();
    }

    static String errorJson(String msg) {
        return "{\"error\":" + JSON.valueToTree(msg) + "}";
    }

    static void deleteDir(File d) {
        if (d == null || !d.exists()) return;
        File[] c = d.listFiles();
        if (c != null) for (File f : c) deleteDir(f);
        d.delete();
    }
}
