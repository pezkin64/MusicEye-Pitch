package com.xemsoft.sheetmusicscanner2.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Window;
import androidx.core.content.FileProvider;
import androidx.core.text.HtmlCompat;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.activity.AboutActivity;
import com.xemsoft.sheetmusicscanner2.activity.CreditsActivity;
import com.xemsoft.sheetmusicscanner2.activity.HelpActivity;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.player.Global;
import com.xemsoft.sheetmusicscanner2.sources.session;
import java.io.File;

public class ActivityHelper {
    private static final String ACTION_SCAN_CAMERA = "com.xemsoft.sheetmusicscanner2.action.SCAN_CAMERA";
    private static final String ACTION_SCAN_FILES = "com.xemsoft.sheetmusicscanner2.action.SCAN_FILES";
    private static final String ACTION_SCAN_PHOTOS = "com.xemsoft.sheetmusicscanner2.action.SCAN_PHOTOS";
    private static final String ACTION_SHARE_APP = "com.xemsoft.sheetmusicscanner2.action.SHARE_APP";
    public static final int ACTIVITY_REQUEST_CHOOSE_DIR = 768;
    public static final int ACTIVITY_REQUEST_IMAGE_CAPTURE = 256;
    public static final int ACTIVITY_REQUEST_PICK_CONTENT = 512;
    private static String CAMERA_PATH = null;
    private static final String LOGTAG = "ActivityHelper.java";

    public static void fullScreen(Activity activity) {
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT < 35) {
            window.setFlags(1024, 1024);
            if (Build.VERSION.SDK_INT >= 28) {
                window.getAttributes().layoutInDisplayCutoutMode = 1;
                return;
            }
            return;
        }
        window.setNavigationBarContrastEnforced(true);
    }

    public static void keepScreenOn(Activity activity, boolean z) {
        if (z) {
            activity.getWindow().addFlags(128);
        } else {
            activity.getWindow().clearFlags(128);
        }
    }

    public static void setOutTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    public static void setInTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }

    public static void setHoldTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.hold, R.anim.hold);
    }

    public static void setCrossfadeTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public static void setVerticalInTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
    }

    public static void setVerticalOutTransition(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_down, R.anim.slide_out_down);
    }

    public static void openHelp(Activity activity, String str) {
        Intent intent = new Intent(activity, HelpActivity.class);
        intent.putExtra(Constants.BUNDLE_SCROLL_ANCHOR, str);
        activity.startActivity(intent);
    }

    public static void openHelp(Activity activity) {
        openHelp(activity, (String) null);
    }

    public static Intent createPlayBackIntent(String str) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_PLAY_BACK, true);
        if (str != null) {
            intent.putExtra(Constants.BUNDLE_SONG_FOLDER, str);
        }
        return intent;
    }

    public static void openAbout(Activity activity) {
        openActivity(activity, AboutActivity.class);
    }

    public static void openCredits(Activity activity) {
        openActivity(activity, CreditsActivity.class);
    }

    public static Intent createPlayIntent(String str, int i, boolean z, boolean z2, int i2) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_SONG_FOLDER, str);
        intent.putExtra(Constants.BUNDLE_INSERT_IDX, i);
        intent.putExtra(Constants.BUNDLE_NEW_SONG, z);
        intent.putExtra(Constants.BUNDLE_PLAY_EDIT, z2);
        intent.putExtra(Constants.BUNDLE_ANALYZE_ERROR, i2);
        return intent;
    }

    public static Intent createAnalysisIntent(String str, String str2, int i, boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_ANALYSIS, true);
        intent.putExtra(Constants.BUNDLE_CAMERA_PATH, str);
        intent.putExtra(Constants.BUNDLE_SONG_FOLDER, str2);
        intent.putExtra(Constants.BUNDLE_INSERT_IDX, i);
        intent.putExtra(Constants.BUNDLE_NEW_SONG, z);
        intent.putExtra(Constants.BUNDLE_PLAY_EDIT, z2);
        return intent;
    }

    public static Intent createAnalysisIntent(Uri uri, String str, int i, boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_ANALYSIS, true);
        intent.putExtra(Constants.BUNDLE_URI, uri.toString());
        intent.putExtra(Constants.BUNDLE_SONG_FOLDER, str);
        intent.putExtra(Constants.BUNDLE_INSERT_IDX, i);
        intent.putExtra(Constants.BUNDLE_NEW_SONG, z);
        intent.putExtra(Constants.BUNDLE_PLAY_EDIT, z2);
        return intent;
    }

    public static Intent createAnalysisIntent(Uri uri, int i, String str, int i2, boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_ANALYSIS, true);
        intent.putExtra(Constants.BUNDLE_DOCUMENT_URI, uri.toString());
        intent.putExtra(Constants.BUNDLE_FLAGS, i);
        intent.putExtra(Constants.BUNDLE_SONG_FOLDER, str);
        intent.putExtra(Constants.BUNDLE_INSERT_IDX, i2);
        intent.putExtra(Constants.BUNDLE_NEW_SONG, z);
        intent.putExtra(Constants.BUNDLE_PLAY_EDIT, z2);
        return intent;
    }

    public static String getCameraPath(Activity activity) {
        if (CAMERA_PATH == null) {
            CAMERA_PATH = activity.getExternalFilesDir((String) null).getAbsolutePath() + "/camera_img.jpg";
        }
        return CAMERA_PATH;
    }

    public static void captureImage(Activity activity) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            File file = new File(getCameraPath(activity));
            intent.putExtra("output", FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file));
            intent.addFlags(1);
            UserSettings.getInstance(activity).setImport(1);
            activity.startActivityForResult(intent, 256);
        }
    }

    public static void pickImage(Activity activity) {
        Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        UserSettings.getInstance(activity).setImport(2);
        activity.startActivityForResult(intent, 512);
    }

    public static void browse(Activity activity) {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.putExtra("android.intent.extra.MIME_TYPES", new String[]{"image/*", "application/pdf", "application/x-zip", "application/octet-stream", "application/ie.xemsoft.scoreplayer.msca"});
        intent.setAction("android.intent.action.GET_CONTENT");
        UserSettings.getInstance(activity).setImport(3);
        activity.startActivityForResult(Intent.createChooser(intent, "Select File"), 512);
    }

    public static void share(Activity activity) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", activity.getString(R.string.shareEmail_body_appName));
        intent.putExtra("android.intent.extra.TEXT", activity.getString(R.string.shareTextMessageAlert_text));
        intent.setType("text/plain");
        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.shareMenu_title)));
    }

    public static boolean shareFile(Activity activity, String str, String str2) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        Uri uriForFile = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        intent.setType(str2);
        intent.addFlags(1);
        activity.startActivity(Intent.createChooser(intent, activity.getString(R.string.exportMenu_option_otherApp)));
        return true;
    }

    public static void chooseDir(Activity activity) {
        activity.startActivityForResult(Intent.createChooser(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), activity.getString(R.string.exportSaveDialog_option_browse)), ACTIVITY_REQUEST_CHOOSE_DIR);
    }

    public static boolean isShortcutIntent(Activity activity, Intent intent) {
        String action = intent.getAction();
        return (action.equals(ACTION_SCAN_CAMERA) && Utils.hasCamera(activity)) || action.equals(ACTION_SCAN_PHOTOS) || action.equals(ACTION_SCAN_FILES) || action.equals(ACTION_SHARE_APP);
    }

    public static void handleShortcutIntent(Activity activity, Intent intent) {
        String action = intent.getAction();
        Log.d(LOGTAG, "handleShortcutIntent()");
        Global instance = Global.getInstance();
        instance.setSession((session) null, activity);
        instance.setInsertAtIndex(0);
        if (action.equals(ACTION_SCAN_CAMERA)) {
            if (Utils.hasCamera(activity)) {
                captureImage(activity);
            }
        } else if (action.equals(ACTION_SCAN_PHOTOS)) {
            pickImage(activity);
        } else if (action.equals(ACTION_SCAN_FILES)) {
            browse(activity);
        } else if (action.equals(ACTION_SHARE_APP)) {
            share(activity);
        }
    }

    public static boolean emailReport(Activity activity) {
        int i;
        int i2;
        Bitmap bitmap;
        String string = activity.getString(R.string.feedbackEmail_subject);
        SessionUtility instance = SessionUtility.getInstance(activity);
        Uri tempImageUri = instance.getTempImageUri();
        if (tempImageUri != null) {
            bitmap = instance.loadTempImage();
            i2 = bitmap.getWidth();
            i = bitmap.getHeight();
            bitmap.recycle();
        } else {
            bitmap = null;
            i2 = 0;
            i = 0;
        }
        String str = "<html><br><br><i>" + activity.getString(R.string.feedbackEmail_message) + "</i><br><br>" + Utils.getDeviceName() + "<br>Android " + Utils.getOsVersion() + "<br>" + activity.getString(R.string.feedbackEmail_version) + " " + Uri.encode(Utils.getAppVersion(activity)) + "<br>" + activity.getString(R.string.feedbackEmail_size) + " " + i2 + " x " + i + "</html>";
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setSelector(new Intent("android.intent.action.SENDTO", Uri.parse("mailto:")));
            intent.putExtra("android.intent.extra.EMAIL", new String[]{Constants.EMAIL_ADDRESS});
            intent.putExtra("android.intent.extra.SUBJECT", string);
            intent.putExtra("android.intent.extra.TEXT", HtmlCompat.fromHtml(str, 0));
            if (bitmap != null) {
                intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", new File(tempImageUri.getPath())));
            }
            activity.startActivity(Intent.createChooser(intent, string));
            return true;
        } catch (Throwable unused) {
            Log.w(LOGTAG, "emailReport() - startActivity failed");
            return false;
        }
    }

    public static void rateApp(Activity activity) {
        try {
            activity.startActivity(rateIntentForUrl(activity, "market://details"));
        } catch (ActivityNotFoundException unused) {
            activity.startActivity(rateIntentForUrl(activity, "https://play.google.com/store/apps/details"));
        }
    }

    public static void takeSurvey(Activity activity) {
        activity.startActivity(viewUriIntent(activity, Uri.parse("https://sheetmusicscanner.typeform.com/to/GeYH6U")));
    }

    private static Intent rateIntentForUrl(Activity activity, String str) {
        return viewUriIntent(activity, Uri.parse(String.format("%s?id=%s", new Object[]{str, activity.getPackageName()})));
    }

    private static Intent viewUriIntent(Activity activity, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        intent.addFlags(1208483840);
        return intent;
    }

    private static void openActivity(Activity activity, Class<?> cls) {
        activity.startActivity(new Intent(activity, cls));
    }

    public static void killApp(Activity activity) {
        activity.finish();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                System.exit(0);
            }
        }, 500);
    }
}
