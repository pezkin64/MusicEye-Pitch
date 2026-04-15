package com.xemsoft.sheetmusicscanner2.activity;

import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.text.HtmlCompat;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.view.TouchView;
import com.xemsoft.sheetmusicscanner2.widget.LockableScrollView;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;
import java.io.IOException;
import java.io.InputStream;

public class CreditsActivity extends AppCompatActivity {
    private static final String LOGTAG = "CreditsActivity.java";
    private TintImageButton m_ButBack;
    /* access modifiers changed from: private */
    public CardView m_CatCard;
    private TouchView m_CatTrigger;
    /* access modifiers changed from: private */
    public LockableScrollView m_ScrollView;
    /* access modifiers changed from: private */
    public TextView m_TextCopyright;
    /* access modifiers changed from: private */
    public TextView m_TextCredits;

    /* JADX WARNING: type inference failed for: r1v0, types: [com.xemsoft.sheetmusicscanner2.activity.CreditsActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        CreditsActivity.super.onCreate(bundle);
        setContentView(R.layout.activity_credits);
        ActivityHelper.fullScreen(this);
        ActivityHelper.setInTransition(this);
        this.m_ScrollView = (LockableScrollView) findViewById(R.id.scroll_view);
        this.m_ButBack = (TintImageButton) findViewById(R.id.but_back);
        this.m_TextCredits = (TextView) findViewById(R.id.text_credits);
        this.m_TextCopyright = (TextView) findViewById(R.id.text_copyright);
        this.m_CatCard = findViewById(R.id.cat_card);
        this.m_CatTrigger = (TouchView) findViewById(R.id.cat_trigger);
        this.m_ButBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CreditsActivity.this.finish();
            }
        });
        this.m_ScrollView.setScrollingEnabled(true);
        this.m_CatCard.setVisibility(8);
        this.m_CatTrigger.setTouchListener(new TouchView.TouchViewListener() {
            public void onTouchDown() {
                CreditsActivity.this.m_ScrollView.setScrollingEnabled(false);
                Utils.fadeInView(CreditsActivity.this.m_CatCard, 200);
            }

            public void onTouchUp() {
                CreditsActivity.this.m_ScrollView.setScrollingEnabled(true);
                Utils.fadeOutView(CreditsActivity.this.m_CatCard, 200);
            }
        });
        this.m_TextCredits.post(new Runnable() {
            public void run() {
                CreditsActivity.this.m_TextCredits.setText(HtmlCompat.fromHtml(CreditsActivity.this.getString(R.string.creditsView_description), 0));
                Linkify.addLinks(CreditsActivity.this.m_TextCredits, 15);
                try {
                    InputStream open = CreditsActivity.this.getAssets().open("copyright.html");
                    byte[] bArr = new byte[open.available()];
                    open.read(bArr);
                    open.close();
                    CreditsActivity.this.m_TextCopyright.setText(HtmlCompat.fromHtml(new String(bArr), 0));
                } catch (IOException unused) {
                    Log.w(CreditsActivity.LOGTAG, "Load html failed");
                }
                Linkify.addLinks(CreditsActivity.this.m_TextCopyright, 15);
            }
        });
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.CreditsActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    public void finish() {
        CreditsActivity.super.finish();
        ActivityHelper.setOutTransition(this);
    }
}
