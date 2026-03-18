package com.musiceye.zemskyharness;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView statusText = findViewById(R.id.statusText);
        try {
            Intent serviceIntent = new Intent(this, OmrForegroundService.class);
            ContextCompat.startForegroundService(this, serviceIntent);
            statusText.setText("Service started on http://127.0.0.1:8084\nPOST /process and /process-json\nGET /docs");
        } catch (Exception e) {
            statusText.setText("Service start failed:\n" + e.getMessage());
        }
    }
}
