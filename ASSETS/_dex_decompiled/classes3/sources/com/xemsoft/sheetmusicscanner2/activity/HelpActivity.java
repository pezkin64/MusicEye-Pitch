package com.xemsoft.sheetmusicscanner2.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.FeedbackDialogs;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class HelpActivity extends AppCompatActivity {
    private static final String LOGTAG = "HelpActivity.java";
    /* access modifiers changed from: private */
    public AlertBox m_AlertBox = null;
    /* access modifiers changed from: private */
    public TintButton m_ButClose;
    /* access modifiers changed from: private */
    public TintButton m_ButFeedback;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == HelpActivity.this.m_ButClose) {
                HelpActivity.this.finish();
            } else if (view == HelpActivity.this.m_ButFeedback) {
                HelpActivity.this.m_FbDialogs.open();
            }
        }
    };
    /* access modifiers changed from: private */
    public FeedbackDialogs m_FbDialogs = null;
    private boolean m_FirstRun = true;
    /* access modifiers changed from: private */
    public ViewTreeObserver.OnGlobalLayoutListener m_LayoutListener = null;
    /* access modifiers changed from: private */
    public int m_LoadCount = 0;
    /* access modifiers changed from: private */
    public String m_ScrollAnchor = null;
    /* access modifiers changed from: private */
    public WebView m_WebHelp;

    static /* synthetic */ int access$1204(HelpActivity helpActivity) {
        int i = helpActivity.m_LoadCount + 1;
        helpActivity.m_LoadCount = i;
        return i;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.content.Context, com.xemsoft.sheetmusicscanner2.activity.HelpActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        HelpActivity.super.onCreate(bundle);
        setContentView(R.layout.activity_help);
        ActivityHelper.fullScreen(this);
        ActivityHelper.setVerticalInTransition(this);
        getWindow().setFlags(1024, 1024);
        this.m_ScrollAnchor = getIntent().getStringExtra(Constants.BUNDLE_SCROLL_ANCHOR);
        this.m_ButFeedback = (TintButton) findViewById(R.id.but_feedback);
        this.m_ButClose = (TintButton) findViewById(R.id.but_close);
        this.m_WebHelp = (WebView) findViewById(R.id.web_help);
        this.m_ButFeedback.setOnClickListener(this.m_ClickListener);
        this.m_ButClose.setOnClickListener(this.m_ClickListener);
        this.m_AlertBox = new AlertBox(this);
        FeedbackDialogs feedbackDialogs = new FeedbackDialogs(this);
        this.m_FbDialogs = feedbackDialogs;
        feedbackDialogs.setListener(new FeedbackDialogs.OnFeedbackListener() {
            /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.HelpActivity, android.app.Activity] */
            public void onEmail() {
                if (!ActivityHelper.emailReport(HelpActivity.this)) {
                    HelpActivity.this.m_AlertBox.open(HelpActivity.this.getString(R.string.feedbackEmailErrorAlert_title), HelpActivity.this.getString(R.string.feedbackEmailErrorAlert_message), HelpActivity.this.getString(R.string.feedbackEmailErrorAlert_button_ok), (String) null, (AlertBox.OnAlertListener) null);
                }
            }

            /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.HelpActivity, android.app.Activity] */
            public void onRate() {
                ActivityHelper.rateApp(HelpActivity.this);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        HelpActivity.super.onResume();
        if (this.m_FirstRun) {
            this.m_FirstRun = false;
            setWeb();
        }
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.xemsoft.sheetmusicscanner2.activity.HelpActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity] */
    public void finish() {
        HelpActivity.super.finish();
        ActivityHelper.setVerticalOutTransition(this);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.m_AlertBox.close();
        this.m_FbDialogs.close();
        HelpActivity.super.onDestroy();
    }

    private void setWeb() {
        this.m_LayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                HelpActivity.this.m_WebHelp.getViewTreeObserver().removeOnGlobalLayoutListener(HelpActivity.this.m_LayoutListener);
                HelpActivity.this.setWebCore(false);
            }
        };
        this.m_WebHelp.getViewTreeObserver().addOnGlobalLayoutListener(this.m_LayoutListener);
    }

    /* access modifiers changed from: private */
    public void setWebCore(final boolean z) {
        this.m_WebHelp.postDelayed(new Runnable() {
            public void run() {
                HelpActivity.this.m_WebHelp.loadDataWithBaseURL("file:///android_asset/help/", HelpActivity.this.m_ScrollAnchor != null ? HelpActivity.this.buildData() : HelpActivity.this.getString(R.string.support), "text/html", "utf-8", (String) null);
                WebSettings settings = HelpActivity.this.m_WebHelp.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setDomStorageEnabled(true);
                settings.setAllowContentAccess(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setDefaultTextEncodingName("utf-8");
                settings.setBuiltInZoomControls(true);
                settings.setAllowFileAccessFromFileURLs(true);
                settings.setAllowUniversalAccessFromFileURLs(true);
                HelpActivity.this.m_WebHelp.setWebChromeClient(new MyWebChromeClient());
                HelpActivity.this.m_WebHelp.setWebViewClient(new MyWebViewClient());
                if (z) {
                    HelpActivity.this.scrollToAnchor();
                }
            }
        }, 250);
    }

    /* access modifiers changed from: private */
    public void scrollToAnchor() {
        if (this.m_ScrollAnchor != null) {
            this.m_WebHelp.postDelayed(new Runnable() {
                public void run() {
                    WebView access$500 = HelpActivity.this.m_WebHelp;
                    access$500.loadUrl("javascript:scrollToAnchor('" + HelpActivity.this.m_ScrollAnchor + "')");
                }
            }, 250);
        }
    }

    /* access modifiers changed from: private */
    public String buildData() {
        String string = getString(R.string.support);
        int lastIndexOf = string.lastIndexOf("</body>");
        String substring = string.substring(0, lastIndexOf);
        String substring2 = string.substring(lastIndexOf);
        return substring + "<script type=\"text/javascript\">function scrollToAnchor(id){ window.location = id; }</script>" + substring2;
    }

    private class MyWebChromeClient extends WebChromeClient {
        private MyWebChromeClient() {
        }

        public void onProgressChanged(WebView webView, int i) {
            if (i != 100) {
                super.onProgressChanged(webView, i);
            } else if (HelpActivity.this.m_LoadCount < 1) {
                HelpActivity.access$1204(HelpActivity.this);
                HelpActivity.this.setWebCore(true);
            }
        }
    }

    private class MyWebViewClient extends WebViewClient {
        private MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            Uri url = webResourceRequest.getUrl();
            if (!url.toString().contains("mailto")) {
                return false;
            }
            HelpActivity.this.startActivity(new Intent("android.intent.action.VIEW", url));
            return true;
        }
    }
}
