package com.xemsoft.sheetmusicscanner2.activity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;

public class AboutActivity extends AppCompatActivity {
    private static final String LOGTAG = "AboutActivity.java";
    /* access modifiers changed from: private */
    public TintImageButton m_ButBack;
    /* access modifiers changed from: private */
    public TintButton m_ButCredits;
    /* access modifiers changed from: private */
    public TintButton m_ButRate;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        /* JADX WARNING: type inference failed for: r2v1, types: [com.xemsoft.sheetmusicscanner2.activity.AboutActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r2v2, types: [com.xemsoft.sheetmusicscanner2.activity.AboutActivity, android.app.Activity] */
        public void onClick(View view) {
            if (view == AboutActivity.this.m_ButBack) {
                AboutActivity.this.finish();
            } else if (view == AboutActivity.this.m_ButCredits) {
                ActivityHelper.openCredits(AboutActivity.this);
            } else if (view == AboutActivity.this.m_ButRate) {
                ActivityHelper.rateApp(AboutActivity.this);
            }
        }
    };
    /* access modifiers changed from: private */
    public TextView m_TextCopyright;
    /* access modifiers changed from: private */
    public TextView m_TextDescription;

    /* JADX WARNING: type inference failed for: r1v0, types: [com.xemsoft.sheetmusicscanner2.activity.AboutActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        AboutActivity.super.onCreate(bundle);
        setContentView(R.layout.activity_about);
        ActivityHelper.fullScreen(this);
        ActivityHelper.setInTransition(this);
        this.m_ButBack = (TintImageButton) findViewById(R.id.but_back);
        this.m_ButCredits = (TintButton) findViewById(R.id.but_credits);
        this.m_ButRate = (TintButton) findViewById(R.id.but_rate);
        this.m_TextCopyright = (TextView) findViewById(R.id.text_copyright);
        this.m_TextDescription = (TextView) findViewById(R.id.text_description);
        this.m_ButBack.setOnClickListener(this.m_ClickListener);
        this.m_ButCredits.setOnClickListener(this.m_ClickListener);
        this.m_ButCredits.setOnClickListener(this.m_ClickListener);
        this.m_ButRate.setOnClickListener(this.m_ClickListener);
        this.m_TextCopyright.post(new Runnable() {
            public void run() {
                AboutActivity.this.m_TextCopyright.setText(HtmlCompat.fromHtml(AboutActivity.this.getString(R.string.aboutView_copyright), 0));
                Linkify.addLinks(AboutActivity.this.m_TextCopyright, 15);
                AboutActivity.this.m_TextDescription.setText(HtmlCompat.fromHtml(AboutActivity.this.getString(R.string.aboutView_description), 0));
                Linkify.addLinks(AboutActivity.this.m_TextDescription, 15);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.AboutActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    public void finish() {
        AboutActivity.super.finish();
        ActivityHelper.setOutTransition(this);
    }
}
