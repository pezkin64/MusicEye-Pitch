package com.musiceye.zemskyharness;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class OmrForegroundService extends Service {
    private static final String TAG = "ZemskyHarness";
    private static final String CHANNEL_ID = "zemsky_harness_channel";
    private static final int NOTIFICATION_ID = 8084;

    private LocalOmrHttpServer server;

    static {
        System.loadLibrary("jpgt");
        System.loadLibrary("pngt");
        System.loadLibrary("lame");
        System.loadLibrary("lept");
        System.loadLibrary("source-lib");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        startForeground(NOTIFICATION_ID, buildNotification("Starting Zemsky OMR service..."));

        try {
            OmrEngine engine = new OmrEngine(this);
            server = new LocalOmrHttpServer(this, engine);
            server.start(5000, false);
            updateNotification("Running on :8084");
            Log.i(TAG, "Foreground OMR server started");
        } catch (Exception e) {
            Log.e(TAG, "Failed to start OMR server", e);
            updateNotification("Startup failed: " + e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (server != null) {
            server.stop();
            server = null;
        }
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Zemsky Harness",
                NotificationManager.IMPORTANCE_LOW
            );
            channel.setDescription("Keeps local OMR endpoint running for NoteScan");
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }
    }

    private Notification buildNotification(String text) {
        return new NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Zemsky Harness")
            .setContentText(text)
            .setSmallIcon(android.R.drawable.stat_notify_sync)
            .setOngoing(true)
            .build();
    }

    private void updateNotification(String text) {
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.notify(NOTIFICATION_ID, buildNotification(text));
        }
    }
}
