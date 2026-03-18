package com.musiceye.zemskyharness;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class AssetExtractor {
    private AssetExtractor() {}

    public static File ensureAssetDir(Context context, String assetDirName) throws IOException {
        File outDir = new File(context.getFilesDir(), assetDirName);
        if (!outDir.exists() && !outDir.mkdirs()) {
            throw new IOException("Failed to create dir: " + outDir);
        }
        copyRecursively(context.getAssets(), assetDirName, outDir);
        return outDir;
    }

    private static void copyRecursively(AssetManager assets, String assetPath, File outDir) throws IOException {
        String[] children = assets.list(assetPath);
        if (children == null || children.length == 0) {
            copyFile(assets, assetPath, outDir);
            return;
        }
        for (String child : children) {
            String childAssetPath = assetPath + "/" + child;
            File childOut = new File(outDir, child);
            String[] grandChildren = assets.list(childAssetPath);
            if (grandChildren != null && grandChildren.length > 0) {
                if (!childOut.exists() && !childOut.mkdirs()) {
                    throw new IOException("Failed to create dir: " + childOut);
                }
                copyRecursively(assets, childAssetPath, childOut);
            } else {
                copyFile(assets, childAssetPath, childOut);
            }
        }
    }

    private static void copyFile(AssetManager assets, String assetPath, File outFile) throws IOException {
        if (outFile.exists() && outFile.length() > 0) {
            return;
        }
        try (InputStream in = assets.open(assetPath); FileOutputStream out = new FileOutputStream(outFile)) {
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
    }
}
