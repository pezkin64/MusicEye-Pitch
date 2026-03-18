package com.musiceye.zemskyharness;

import android.content.Context;
import android.util.Log;
import android.util.Base64;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class LocalOmrHttpServer extends NanoHTTPD {
    private static final String TAG = "ZemskyHarness";
    private final Context context;
    private final OmrEngine engine;

    public LocalOmrHttpServer(Context context, OmrEngine engine) {
        // Bind all interfaces to avoid loopback quirks across app processes on some Android builds.
        super("0.0.0.0", 8084);
        this.context = context;
        this.engine = engine;
    }

    @Override
    public Response serve(IHTTPSession session) {
        try {
            String uri = session.getUri();
            String method = session.getMethod().toString();
            Log.i(TAG, "📨 Incoming: " + method + " " + uri);
            
            if (Method.GET.equals(session.getMethod()) && "/docs".equals(uri)) {
                Log.i(TAG, "✅ /docs health check");
                return json(200, new JSONObject()
                    .put("status", "ok")
                    .put("engine", "zemsky-emulator").toString());
            }
            if (Method.GET.equals(session.getMethod()) && "/".equals(uri)) {
                Log.i(TAG, "✅ / root health check");
                return json(200, new JSONObject()
                    .put("service", "Zemsky Harness")
                    .put("status", "ready")
                    .put("port", 8084).toString());
            }
            if (Method.POST.equals(session.getMethod()) && "/process".equals(uri)) {
                String contentType = session.getHeaders().get("content-type");
                Log.i(TAG, "🎼 POST /process ContentType: " + (contentType != null ? contentType : "none"));
                if (contentType != null) {
                    String ct = contentType.toLowerCase();
                    if (ct.contains("application/json") || ct.contains("application/x-www-form-urlencoded") || ct.contains("text/plain")) {
                        Log.i(TAG, "→ Routing to JSON handler");
                        return handleProcessJson(session);
                    }
                }
                Log.i(TAG, "→ Routing to multipart handler");
                return handleProcess(session);
            }
            if (Method.POST.equals(session.getMethod()) && "/process-json".equals(uri)) {
                String contentType = session.getHeaders().get("content-type");
                if (contentType != null && contentType.toLowerCase().contains("multipart/form-data")) {
                    Log.i(TAG, "🎼 POST /process-json (multipart fallback)");
                    return handleProcess(session);
                }
                Log.i(TAG, "🎼 POST /process-json");
                return handleProcessJson(session);
            }
            Log.w(TAG, "❌ 404 unrecognized endpoint");
            return json(404, new JSONObject().put("error", "Not found").toString());
        } catch (Exception e) {
            Log.e(TAG, "❌ serve() exception", e);
            return json(500, errorJson(e.getMessage()));
        }
    }

    private Response handleProcess(IHTTPSession session) throws Exception {
        Map<String, String> files = new java.util.HashMap<>();
        session.parseBody(files);
        Log.i(TAG, "  ▪ multipart parseBody complete, files found: " + files.size());

        String filePath = files.get("file");
        if (filePath == null || filePath.isEmpty()) {
            filePath = findUploadedTempFile(session.getParameters(), files);
        }
        if (filePath == null || filePath.isEmpty()) {
            Log.w(TAG, "  ▪ ERROR: No 'file' field in multipart");
            return json(400, errorJson("No 'file' field in multipart upload"));
        }
        Log.i(TAG, "  ▪ file found at: " + filePath);

        File input = new File(filePath);
        if (!input.exists()) {
            Log.w(TAG, "  ▪ ERROR: Uploaded file missing on disk");
            return json(400, errorJson("Uploaded file missing on disk"));
        }

        File stableCopy = new File(context.getCacheDir(), "upload_" + System.currentTimeMillis() + ".img");
        copyFile(input, stableCopy);
        Log.i(TAG, "  ▪ copied to: " + stableCopy.getAbsolutePath() + " (" + stableCopy.length() + " bytes)");
        return processFromImageFile(stableCopy);
    }

    private static String findUploadedTempFile(Map<String, List<String>> params, Map<String, String> files) {
        if (params.containsKey("file")) {
            String key = params.get("file").isEmpty() ? null : params.get("file").get(0);
            if (key != null && files.containsKey(key)) {
                return files.get(key);
            }
        }
        for (String value : files.values()) {
            if (value != null && !value.isEmpty()) {
                return value;
            }
        }
        return null;
    }

    private Response handleProcessJson(IHTTPSession session) throws Exception {
        String body;
        try {
            body = readRequestBodyUtf8(session);
        } catch (Exception e) {
            Log.w(TAG, "  ▪ ERROR reading body: " + e.getMessage());
            return json(400, errorJson("Invalid request body"));
        }
        Log.i(TAG, "  ▪ JSON body read (" + (body != null ? body.length() : 0) + " chars)");
        
        if (body == null || body.trim().isEmpty()) {
            Log.w(TAG, "  ▪ ERROR: Missing JSON body");
            return json(400, errorJson("Missing JSON body"));
        }

        String base64 = extractBase64Payload(body);
        if (base64.isEmpty()) {
            Log.w(TAG, "  ▪ ERROR: Missing base64 payload");
            return json(400, errorJson("Missing base64 payload"));
        }
        Log.i(TAG, "  ▪ base64 extracted (" + base64.length() + " chars)");

        // Allow data URI values like data:image/jpeg;base64,/9j/...
        int commaIndex = base64.indexOf(',');
        if (base64.startsWith("data:") && commaIndex > 0) {
            base64 = base64.substring(commaIndex + 1);
        }

        // Handle URL-encoded '+' that can become spaces in some clients.
        base64 = base64.replace(" ", "+");

        byte[] imageBytes;
        try {
            imageBytes = Base64.decode(base64, Base64.DEFAULT);
        } catch (IllegalArgumentException badBase64) {
            Log.w(TAG, "  ▪ ERROR: Invalid base64 payload");
            return json(400, errorJson("Invalid base64 payload"));
        }
        Log.i(TAG, "  ▪ base64 decoded to " + imageBytes.length + " bytes");
        
        File stableCopy = new File(context.getCacheDir(), "upload_" + System.currentTimeMillis() + ".img");
        try (FileOutputStream out = new FileOutputStream(stableCopy)) {
            out.write(imageBytes);
        }
        Log.i(TAG, "  ▪ copied to: " + stableCopy.getAbsolutePath());

        return processFromImageFile(stableCopy);
    }

    private Response processFromImageFile(File imageFile) throws Exception {
        try {
            Log.i(TAG, "  ▪ calling native processImage()...");
            String musicXml = engine.processImage(imageFile.getAbsolutePath());
            Log.i(TAG, "  ✅ native returned " + (musicXml != null ? musicXml.length() : 0) + " chars MusicXML");
            JSONObject response = new JSONObject();
            response.put("musicxml", musicXml);
            response.put("notePositions", JSONObject.NULL);
            response.put("processedImage", JSONObject.NULL);
            return json(200, response.toString());
        } catch (Exception e) {
            Log.e(TAG, "  ❌ native processImage() failed", e);
            throw e;
        } finally {
            //noinspection ResultOfMethodCallIgnored
            imageFile.delete();
        }
    }

    private static Response json(int status, String body) {
        Response response = NanoHTTPD.newFixedLengthResponse(Response.Status.lookup(status), "application/json", body);
        response.addHeader("Access-Control-Allow-Origin", "*");
        return response;
    }

    private static String errorJson(String message) {
        try {
            return new JSONObject().put("error", message == null ? "Unknown error" : message).toString();
        } catch (Exception e) {
            return "{\"error\":\"Unknown error\"}";
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (FileInputStream in = new FileInputStream(source); FileOutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }

    private static String readFileUtf8(File file) throws IOException {
        try (FileInputStream in = new FileInputStream(file); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            return out.toString(StandardCharsets.UTF_8.name());
        }
    }

    private static String readRequestBodyUtf8(IHTTPSession session) throws IOException {
        String lenHeader = session.getHeaders().get("content-length");
        int length = 0;
        if (lenHeader != null && !lenHeader.isEmpty()) {
            try {
                length = Integer.parseInt(lenHeader.trim());
            } catch (NumberFormatException ignored) {
                length = 0;
            }
        }

        InputStream in = session.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];

        if (length > 0) {
            int remaining = length;
            while (remaining > 0) {
                int read = in.read(buffer, 0, Math.min(buffer.length, remaining));
                if (read <= 0) break;
                out.write(buffer, 0, read);
                remaining -= read;
            }
        } else {
            int read;
            while ((read = in.read(buffer)) > 0) {
                out.write(buffer, 0, read);
                if (read < buffer.length) break;
            }
        }

        return out.toString(StandardCharsets.UTF_8.name());
    }

    private static String extractBase64Payload(String body) {
        if (body == null) return "";
        String trimmed = body.trim();

        // Some clients may send a JSON string literal with escaped quotes.
        // Example: "{\"fileName\":...,\"base64\":\"...\"}"
        if (trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.contains("\\\"")) {
            try {
                JSONObject wrapped = new JSONObject("{\"value\":" + trimmed + "}");
                trimmed = wrapped.optString("value", trimmed);
            } catch (Exception ignored) {
                // keep original trimmed value
            }
        }

        // JSON payload: {"base64":"..."}
        if (trimmed.startsWith("{")) {
            try {
                JSONObject payload = new JSONObject(trimmed);
                return payload.optString("base64", "");
            } catch (Exception ignored) {
                // Fallback to regex/urlencoded parser below
            }

            // Regex fallback for malformed JSON that still contains a base64 field.
            Matcher m = Pattern.compile("\\\"base64\\\"\\s*:\\s*\\\"([^\\\"]+)\\\"").matcher(trimmed);
            if (m.find()) {
                return m.group(1);
            }
        }

        // URL-encoded payload: fileName=...&mimeType=...&base64=...
        String[] pairs = trimmed.split("&");
        for (String pair : pairs) {
            int eq = pair.indexOf('=');
            if (eq <= 0) continue;
            String key = pair.substring(0, eq);
            String value = pair.substring(eq + 1);
            if ("base64".equals(key)) {
                try {
                    return URLDecoder.decode(value, StandardCharsets.UTF_8.name());
                } catch (Exception ignored) {
                    return value;
                }
            }
        }

        return "";
    }
}
