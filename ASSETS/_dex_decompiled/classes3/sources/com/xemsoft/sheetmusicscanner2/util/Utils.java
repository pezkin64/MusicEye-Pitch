package com.xemsoft.sheetmusicscanner2.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.documentfile.provider.DocumentFile;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import jp.kshoji.javax.sound.midi.Sequence;

public class Utils {
    private static final String LOGTAG = "Utils.java";

    public static String getDensityDpi(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        String str = i + " dpi";
        if (i == 120) {
            return "ldpi";
        }
        if (i == 160) {
            return "mdpi";
        }
        if (i == 240) {
            return "hdpi";
        }
        if (i == 320) {
            return "xhdpi";
        }
        if (i != 480) {
            return i != 640 ? str : "xxxhdpi";
        }
        return "xxhdpi";
    }

    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        return str + " " + str2;
    }

    public static String getOsVersion() {
        return Build.VERSION.RELEASE + "  API " + Build.VERSION.SDK_INT;
    }

    public static String getResolution(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        return i + "x" + i2;
    }

    public static int getScreenMinWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.min(displayMetrics.heightPixels, displayMetrics.widthPixels);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getDpWidth(Context context) {
        return (int) (((float) getScreenMinWidth(context)) / getDensity(context));
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "unknown";
        }
    }

    public static void hideSoftKeyboard(Context context, View view) {
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 2);
    }

    public static void hideSoftKeyboard(final Context context, final View view, int i) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Utils.hideSoftKeyboard(context, view);
            }
        }, (long) i);
    }

    public static void showSoftKeyboard(Context context, View view) {
        if (view.requestFocus()) {
            ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(view, 1);
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService("input_method");
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = new View(activity);
        }
        inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }

    public static String timeStamp(String str) {
        return new SimpleDateFormat(str, Locale.ENGLISH).format(new Date());
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030 A[SYNTHETIC, Splitter:B:18:0x0030] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003d A[SYNTHETIC, Splitter:B:25:0x003d] */
    /* JADX WARNING: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readByteArrayFromFile(java.lang.String r6) {
        /*
            java.lang.String r0 = "readByteArrayFromFile() - Close stream error"
            java.lang.String r1 = "Utils.java"
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            long r4 = r3.length()     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            int r6 = (int) r4     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            byte[] r6 = new byte[r6]     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            r4.<init>(r3)     // Catch:{ IOException -> 0x0027, all -> 0x0025 }
            r4.read(r6)     // Catch:{ IOException -> 0x0023 }
            r4.close()     // Catch:{ IOException -> 0x001d }
            goto L_0x0021
        L_0x001d:
            r2 = move-exception
            android.util.Log.w(r1, r0, r2)
        L_0x0021:
            r2 = r6
            goto L_0x0038
        L_0x0023:
            r6 = move-exception
            goto L_0x0029
        L_0x0025:
            r6 = move-exception
            goto L_0x003b
        L_0x0027:
            r6 = move-exception
            r4 = r2
        L_0x0029:
            java.lang.String r3 = "readByteArrayFromFile() - Read error"
            android.util.Log.w(r1, r3, r6)     // Catch:{ all -> 0x0039 }
            if (r4 == 0) goto L_0x0038
            r4.close()     // Catch:{ IOException -> 0x0034 }
            goto L_0x0038
        L_0x0034:
            r6 = move-exception
            android.util.Log.w(r1, r0, r6)
        L_0x0038:
            return r2
        L_0x0039:
            r6 = move-exception
            r2 = r4
        L_0x003b:
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ IOException -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r2 = move-exception
            android.util.Log.w(r1, r0, r2)
        L_0x0045:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.util.Utils.readByteArrayFromFile(java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x004c A[SYNTHETIC, Splitter:B:32:0x004c] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeByteArrayToFile(byte[] r5, java.lang.String r6) {
        /*
            java.lang.String r0 = "writeByteArrayToFile() - Error while closing stream"
            java.lang.String r1 = "Utils.java"
            java.io.File r2 = new java.io.File
            r2.<init>(r6)
            java.io.File r6 = r2.getParentFile()
            r6.mkdirs()
            r6 = 0
            r3 = 0
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x0040, IOException -> 0x002f }
            r4.<init>(r2)     // Catch:{ FileNotFoundException -> 0x0040, IOException -> 0x002f }
            r4.write(r5)     // Catch:{ FileNotFoundException -> 0x002a, IOException -> 0x0027, all -> 0x0024 }
            r4.close()     // Catch:{ IOException -> 0x001e }
            goto L_0x0022
        L_0x001e:
            r5 = move-exception
            android.util.Log.w(r1, r0, r5)
        L_0x0022:
            r6 = 1
            goto L_0x0049
        L_0x0024:
            r5 = move-exception
            r3 = r4
            goto L_0x004a
        L_0x0027:
            r5 = move-exception
            r3 = r4
            goto L_0x0030
        L_0x002a:
            r5 = move-exception
            r3 = r4
            goto L_0x0041
        L_0x002d:
            r5 = move-exception
            goto L_0x004a
        L_0x002f:
            r5 = move-exception
        L_0x0030:
            java.lang.String r2 = "writeByteArrayToFile() - Write error"
            android.util.Log.w(r1, r2, r5)     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0049
        L_0x0037:
            r3.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x0049
        L_0x003b:
            r5 = move-exception
            android.util.Log.w(r1, r0, r5)
            goto L_0x0049
        L_0x0040:
            r5 = move-exception
        L_0x0041:
            java.lang.String r2 = "writeByteArrayToFile() - File not found"
            android.util.Log.w(r1, r2, r5)     // Catch:{ all -> 0x002d }
            if (r3 == 0) goto L_0x0049
            goto L_0x0037
        L_0x0049:
            return r6
        L_0x004a:
            if (r3 == 0) goto L_0x0054
            r3.close()     // Catch:{ IOException -> 0x0050 }
            goto L_0x0054
        L_0x0050:
            r6 = move-exception
            android.util.Log.w(r1, r0, r6)
        L_0x0054:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.util.Utils.writeByteArrayToFile(byte[], java.lang.String):boolean");
    }

    public static boolean copyFile(String str, String str2) {
        byte[] readByteArrayFromFile = readByteArrayFromFile(str);
        if (readByteArrayFromFile != null) {
            return writeByteArrayToFile(readByteArrayFromFile, str2);
        }
        return false;
    }

    public static boolean copyDirectory(String str, String str2) {
        String[] list;
        File file = new File(str);
        new File(str2);
        if (!file.isDirectory() || (list = file.list()) == null) {
            return false;
        }
        new File(str2).mkdirs();
        for (String str3 : list) {
            String str4 = str + "/" + str3;
            String str5 = str2 + "/" + str3;
            File file2 = new File(str4);
            if (file2.isDirectory()) {
                file2.mkdirs();
                if (!copyDirectory(str4, str5)) {
                    return false;
                }
            } else if (!copyFile(str4, str5)) {
                return false;
            }
        }
        return true;
    }

    public static boolean removeDirectory(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        String[] list = file.list();
        if (list != null) {
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3.isDirectory()) {
                    if (!removeDirectory(file3)) {
                        return false;
                    }
                } else if (!file3.delete()) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean copyAssetFolder(Context context, String str, String str2) {
        try {
            String[] list = context.getAssets().list(str);
            if (list == null) {
                return false;
            }
            if (list.length == 0) {
                return copyAssetFile(context, str, str2);
            }
            boolean mkdirs = new File(str2).mkdirs();
            for (String str3 : list) {
                mkdirs &= copyAssetFolder(context, str + File.separator + str3, str2 + File.separator + str3);
            }
            return mkdirs;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAssetFile(Context context, String str, String str2) {
        Log.d(LOGTAG, "copyAssetFile - src:" + str + " dest:" + str2);
        try {
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2));
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read != -1) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    open.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean saveJpeg(Bitmap bitmap, int i, String str) {
        if (!(bitmap == null || str == null)) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static Bitmap loadJpeg(String str) {
        if (str == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inScaled = false;
        return BitmapFactory.decodeFile(str, options);
    }

    public static boolean savePng(Bitmap bitmap, String str) {
        if (!(bitmap == null || str == null)) {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }

    public static boolean copyFileToDirUri(Context context, Uri uri, String str, String str2, String str3) {
        DocumentFile createFile = DocumentFile.fromTreeUri(context, uri).createFile(str, str3);
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str2));
            OutputStream openOutputStream = context.getContentResolver().openOutputStream(createFile.getUri());
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    openOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    openOutputStream.close();
                    return true;
                }
            }
        } catch (IOException e) {
            Log.w(LOGTAG, "copyFileToDirUri()", e);
            return false;
        }
    }

    public static String getExtension(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1 || lastIndexOf == str.length() - 1) {
            return null;
        }
        return str.substring(lastIndexOf);
    }

    public static String fileToString(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        return fileToString(file);
    }

    public static String fileToString(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    fileInputStream.close();
                    return sb.toString();
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(LOGTAG, "fileToString() - File not found: " + e.toString());
            return null;
        } catch (IOException e2) {
            Log.e(LOGTAG, "fileToString() - Can not read file: " + e2.toString());
            return null;
        }
    }

    public static boolean stringToFile(String str, String str2) {
        return stringToFile(str, new File(str2));
    }

    public static boolean stringToFile(String str, File file) {
        PrintWriter printWriter;
        try {
            printWriter = new PrintWriter(file);
        } catch (FileNotFoundException unused) {
            Log.e(LOGTAG, "stringToFile()");
            printWriter = null;
        }
        if (printWriter == null) {
            return false;
        }
        printWriter.print(str);
        printWriter.flush();
        printWriter.close();
        return true;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i, boolean z) {
        if (i == 0) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate((float) i);
        Bitmap bitmap2 = bitmap;
        Bitmap createBitmap = Bitmap.createBitmap(bitmap2, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        if (!z) {
            return createBitmap;
        }
        if (createBitmap == null) {
            return bitmap2;
        }
        bitmap2.recycle();
        return createBitmap;
    }

    public static void dlgSetLowDim(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.15f;
        window.setAttributes(attributes);
    }

    public static void dlgSetNoDim(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = Sequence.PPQ;
        window.setAttributes(attributes);
    }

    public static void dlgSetNoDim(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = Sequence.PPQ;
        window.setAttributes(attributes);
    }

    public static void dlgSetTransparent(AlertDialog alertDialog) {
        try {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
    }

    public static void dlgSetTransparent(Dialog dialog) {
        try {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        } catch (Exception unused) {
        }
    }

    public static void makeVisible(ListView listView, int i) {
        if (listView != null && i >= 0 && i < listView.getCount()) {
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int lastVisiblePosition = listView.getLastVisiblePosition();
            if (i < firstVisiblePosition) {
                listView.setSelection(i);
            } else if (i >= lastVisiblePosition) {
                listView.setSelection((i + 1) - (lastVisiblePosition - firstVisiblePosition));
            }
        }
    }

    public static View getViewByPosition(int i, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int childCount = (listView.getChildCount() + firstVisiblePosition) - 1;
        if (i < firstVisiblePosition || i > childCount) {
            return listView.getAdapter().getView(i, (View) null, listView);
        }
        return listView.getChildAt(i - firstVisiblePosition);
    }

    public static void activateListItem(final int i, final ListView listView) {
        listView.postDelayed(new Runnable() {
            public void run() {
                Utils.getViewByPosition(i, listView).setActivated(true);
            }
        }, 500);
    }

    private static int dpToPx(int i, DisplayMetrics displayMetrics) {
        return Math.round(TypedValue.applyDimension(1, (float) i, displayMetrics));
    }

    public static void fadeInView(View view, int i) {
        view.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(Sequence.PPQ, view.getAlpha());
        alphaAnimation.setDuration((long) i);
        view.startAnimation(alphaAnimation);
    }

    public static void fadeOutView(final View view, int i) {
        view.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(view.getAlpha(), Sequence.PPQ);
        alphaAnimation.setDuration((long) i);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.setVisibility(4);
            }
        });
        view.startAnimation(alphaAnimation);
    }

    public static void crossFadeViews(final View view, View view2) {
        view.setVisibility(0);
        view2.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(view.getAlpha(), Sequence.PPQ);
        alphaAnimation.setDuration(500);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.setVisibility(4);
            }
        });
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(Sequence.PPQ, view2.getAlpha());
        alphaAnimation2.setDuration(500);
        view.startAnimation(alphaAnimation);
        view2.startAnimation(alphaAnimation2);
    }

    public static void fadeButtonText(final Button button, final String str) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(Sequence.PPQ, button.getAlpha());
        alphaAnimation.setDuration(300);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(button.getAlpha(), Sequence.PPQ);
        alphaAnimation2.setDuration(300);
        alphaAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                button.setText(str);
                button.startAnimation(alphaAnimation);
            }
        });
        button.startAnimation(alphaAnimation2);
    }

    public static void slideLeft(View view, View view2) {
        slideViews(view, view2, Sequence.PPQ, -1.0f, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 280);
    }

    public static void slideRight(View view, View view2) {
        slideViews(view, view2, Sequence.PPQ, 1.0f, Sequence.PPQ, Sequence.PPQ, -1.0f, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 280);
    }

    public static void slideUp(View view, View view2) {
        slideViews(view, view2, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, -1.0f, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ, 350);
    }

    public static void slideDown(View view, View view2) {
        slideViews(view, view2, Sequence.PPQ, Sequence.PPQ, Sequence.PPQ, 1.0f, Sequence.PPQ, Sequence.PPQ, -1.0f, Sequence.PPQ, 350);
    }

    private static void slideViews(final View view, View view2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int i) {
        TranslateAnimation translateAnimation;
        int i2 = i;
        if (view != null) {
            translateAnimation = new TranslateAnimation(2, f, 2, f2, 2, f3, 2, f4);
            translateAnimation.setDuration((long) i2);
            translateAnimation.setInterpolator(new LinearInterpolator());
        } else {
            translateAnimation = null;
        }
        TranslateAnimation translateAnimation2 = new TranslateAnimation(2, f5, 2, f6, 2, f7, 2, f8);
        translateAnimation2.setDuration((long) i2);
        translateAnimation2.setInterpolator(new LinearInterpolator());
        translateAnimation2.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                View view = view;
                if (view != null) {
                    view.setVisibility(4);
                }
            }
        });
        view2.setVisibility(0);
        view2.startAnimation(translateAnimation2);
        if (translateAnimation != null) {
            view.setVisibility(0);
            view.startAnimation(translateAnimation);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: android.net.Uri} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isShareIntent(android.content.Intent r1) {
        /*
            android.net.Uri r0 = r1.getData()
            if (r0 != 0) goto L_0x0015
            android.os.Bundle r1 = r1.getExtras()
            if (r1 == 0) goto L_0x0015
            java.lang.String r0 = "android.intent.extra.STREAM"
            java.lang.Object r1 = r1.get(r0)
            r0 = r1
            android.net.Uri r0 = (android.net.Uri) r0
        L_0x0015:
            if (r0 == 0) goto L_0x0019
            r1 = 1
            return r1
        L_0x0019:
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.util.Utils.isShareIntent(android.content.Intent):boolean");
    }

    public static int log2(int i) {
        return (int) ((Math.log((double) i) / Math.log(2.0d)) + 1.0E-10d);
    }

    public static void sortStringList(List<String> list) {
        Collections.sort(list, new Comparator() {
            public int compare(Object obj, Object obj2) {
                return ((String) obj).compareToIgnoreCase((String) obj2);
            }
        });
    }

    public static boolean hasCamera(Context context) {
        boolean hasSystemFeature = context.getPackageManager().hasSystemFeature("android.hardware.camera");
        Log.d(LOGTAG, "hasCamera:" + hasSystemFeature);
        return hasSystemFeature;
    }
}
