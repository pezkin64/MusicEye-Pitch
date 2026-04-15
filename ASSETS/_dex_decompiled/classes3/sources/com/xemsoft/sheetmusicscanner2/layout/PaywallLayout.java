package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.ScannerApplication;
import com.xemsoft.sheetmusicscanner2.billing.OfferText;
import com.xemsoft.sheetmusicscanner2.billing.SubscriptionUtility;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.util.Logg;

public class PaywallLayout extends PaywallBaseLayout {
    /* access modifiers changed from: private */
    public static final String LOGTAG = "PaywallLayout";
    private boolean mAutoDismiss = true;
    private View.OnClickListener mButClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean unused = PaywallLayout.this.mRestoreClicked = false;
            int id = view.getId();
            if (id != 2131230865) {
                if (id == 2131230868) {
                    boolean unused2 = PaywallLayout.this.mRestoreClicked = true;
                    PaywallLayout.this.postDelayed(new Runnable() {
                        public void run() {
                            boolean unused = PaywallLayout.this.mRestoreClicked = false;
                        }
                    }, 1000);
                    if (PaywallLayout.this.mListener != null) {
                        PaywallLayout.this.mListener.onRestore();
                    }
                } else if (id != 2131230879 || PaywallLayout.this.mListener == null) {
                } else {
                    if (PaywallLayout.this.mSelectedOfferId != null) {
                        PaywallLayout.this.mListener.onSubscribe(PaywallLayout.this.mSelectedOfferId);
                    } else {
                        Logg.e(PaywallLayout.LOGTAG, "Selected offer id null");
                    }
                }
            } else if (PaywallLayout.this.mListener != null) {
                PaywallLayout.this.mListener.onRefresh();
            }
        }
    };
    private Button mButRefresh;
    private Button mButRestore;
    private Button mButSubscribe;
    private LinearLayout mLayoutPanes;
    PaywallLayoutListener mListener;
    private View.OnClickListener mPaneClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            boolean unused = PaywallLayout.this.mRestoreClicked = false;
            PaywallLayout.this.radioSelectPane((OfferPane) view);
        }
    };
    private OfferPane[] mPanes = new OfferPane[4];
    private ProgressBar mProgress;
    /* access modifiers changed from: private */
    public boolean mRestoreClicked = false;
    /* access modifiers changed from: private */
    public String mSelectedOfferId = null;
    /* access modifiers changed from: private */
    public boolean mShowingInternetErrorAlert = false;
    private SubscriptionUtility mSubscriptionUtility;
    private TextView mTextSubscribe;
    private OfferText[] mTexts;
    private AlertBox m_AlertBox;

    public interface PaywallLayoutListener {
        void onRefresh();

        void onRestore();

        void onSubscribe(String str);
    }

    public PaywallLayout(Context context) {
        super(context);
        init(context);
    }

    public PaywallLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public PaywallLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        super.init(context);
        this.mSubscriptionUtility = ScannerApplication.getInstance().mAppServices.subscriptionUtility;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_paywall, this, true);
        this.mProgress = (ProgressBar) findViewById(R.id.progress);
        Button button = (Button) inflate.findViewById(R.id.but_restore);
        this.mButRestore = button;
        Button button2 = (Button) inflate.findViewById(R.id.but_refresh);
        this.mButRefresh = button2;
        Button button3 = (Button) inflate.findViewById(R.id.but_subscribe);
        this.mButSubscribe = button3;
        View[] viewArr = {button, button2, button3};
        this.mLayoutPanes = (LinearLayout) inflate.findViewById(R.id.layout_panes);
        this.mTextSubscribe = (TextView) inflate.findViewById(R.id.text_subscribe);
        this.mPanes[0] = (OfferPane) inflate.findViewById(R.id.pane_trial);
        this.mPanes[1] = (OfferPane) inflate.findViewById(R.id.pane_yearly_no_trial);
        this.mPanes[2] = (OfferPane) inflate.findViewById(R.id.pane_monthly);
        this.mPanes[3] = (OfferPane) inflate.findViewById(R.id.pane_yearly_with_trial);
        setPads(inflate.findViewById(R.id.pad_left), inflate.findViewById(R.id.pad_right));
        for (int i = 0; i < 3; i++) {
            viewArr[i].setOnClickListener(this.mButClickListener);
        }
        for (OfferPane onClickListener : this.mPanes) {
            onClickListener.setOnClickListener(this.mPaneClickListener);
        }
        this.m_AlertBox = new AlertBox(context);
        manageLayout();
    }

    public void setListener(PaywallLayoutListener paywallLayoutListener) {
        this.mListener = paywallLayoutListener;
    }

    public void resume() {
        update();
    }

    public void update() {
        updateUi();
    }

    private void updateUi() {
        Logg.d(LOGTAG, "updateUi()");
        SubscriptionUtility.ProductState productState = this.mSubscriptionUtility.getProductState();
        OfferText[] offerTextList = this.mSubscriptionUtility.getOfferTextList();
        boolean isNetworkConnected = this.mSubscriptionUtility.isNetworkConnected();
        if (isNetworkConnected && this.mShowingInternetErrorAlert) {
            this.m_AlertBox.close();
            this.mShowingInternetErrorAlert = false;
        }
        if (!isNetworkConnected) {
            if (!this.mShowingInternetErrorAlert) {
                noInternetErrorAlert();
                this.mShowingInternetErrorAlert = true;
            }
        } else if (productState == SubscriptionUtility.ProductState.STATE_WAITING || productState == SubscriptionUtility.ProductState.STATE_PENDING) {
            showOffers(false);
            this.mButRefresh.setVisibility(8);
            this.mProgress.setVisibility(0);
        } else if (productState == SubscriptionUtility.ProductState.STATE_ERROR) {
            loadingErrorAlert();
        } else if (offerTextList == null) {
            this.mButRefresh.setVisibility(8);
            this.mProgress.setVisibility(0);
            showOffers(false);
            PaywallLayoutListener paywallLayoutListener = this.mListener;
            if (paywallLayoutListener != null) {
                paywallLayoutListener.onRefresh();
            }
        } else {
            this.mProgress.setVisibility(8);
            this.mButRefresh.setVisibility(8);
            showOffers(true);
            setOfferTexts(offerTextList);
            if (this.mAutoDismiss) {
                this.m_AlertBox.close();
            }
            if (this.mRestoreClicked) {
                restoreAlert();
            }
        }
    }

    private void showOffers(boolean z) {
        int i = z ? 0 : 8;
        this.mLayoutPanes.setVisibility(i);
        this.mTextSubscribe.setVisibility(i);
        this.mButSubscribe.setVisibility(i);
    }

    public void destroy() {
        this.m_AlertBox.close();
    }

    private void setOfferTexts(OfferText[] offerTextArr) {
        this.mTexts = offerTextArr;
        for (int i = 0; i < 4; i++) {
            OfferText offerText = this.mTexts[i];
            OfferPane offerPane = this.mPanes[i];
            if (offerText == null) {
                offerPane.setVisibility(8);
            } else {
                offerPane.setVisibility(0);
                offerPane.setText(offerText);
            }
        }
        checkSelection();
    }

    /* access modifiers changed from: private */
    public void radioSelectPane(OfferPane offerPane) {
        for (int i = 0; i < 4; i++) {
            OfferPane offerPane2 = this.mPanes[i];
            if (offerPane == offerPane2) {
                offerPane.setSelected(true);
                this.mButSubscribe.setText(this.mTexts[i].mButton);
                this.mSelectedOfferId = this.mTexts[i].mOfferId;
            } else {
                offerPane2.setSelected(false);
            }
        }
    }

    private void checkSelection() {
        OfferPane[] offerPaneArr = this.mPanes;
        int length = offerPaneArr.length;
        int i = 0;
        while (i < length) {
            OfferPane offerPane = offerPaneArr[i];
            if (!offerPane.isSelected() || offerPane.getVisibility() != 0) {
                i++;
            } else {
                return;
            }
        }
        for (OfferPane offerPane2 : this.mPanes) {
            if (offerPane2.getVisibility() == 0) {
                radioSelectPane(offerPane2);
                return;
            }
        }
    }

    private void noInternetErrorAlert() {
        this.mProgress.setVisibility(8);
        this.mButRefresh.setVisibility(0);
        showOffers(false);
        Logg.w(LOGTAG, "No internet alert");
        this.m_AlertBox.close();
        this.m_AlertBox.open(this.mContext.getString(R.string.subscription_noInternetAlert_title), this.mContext.getString(R.string.subscription_noInternetAlert_message), this.mContext.getString(R.string.subscription_noInternetAlert_button_ok), (String) null, new AlertBox.OnAlertListener() {
            public void onOk() {
                boolean unused = PaywallLayout.this.mShowingInternetErrorAlert = false;
            }

            public void onCancel() {
                boolean unused = PaywallLayout.this.mShowingInternetErrorAlert = false;
            }
        });
        this.mAutoDismiss = true;
        this.mButRefresh.setVisibility(0);
    }

    private void loadingErrorAlert() {
        this.mProgress.setVisibility(8);
        this.mButRefresh.setVisibility(0);
        showOffers(false);
        this.m_AlertBox.close();
        Logg.w(LOGTAG, "Loading error alert");
        this.m_AlertBox.open(this.mContext.getString(R.string.subscription_loadingErrorAlert_title), this.mContext.getString(R.string.subscription_loadingErrorAlert_message), this.mContext.getString(R.string.subscription_loadingErrorAlert_button_retry), (String) null, (AlertBox.OnAlertListener) null);
        this.mAutoDismiss = false;
    }

    private void restoreAlert() {
        this.m_AlertBox.close();
        Logg.w(LOGTAG, "Restore alert");
        this.mRestoreClicked = false;
        this.m_AlertBox.open(this.mContext.getString(R.string.subscription_restoreNoSubscriptionAlert_title), this.mContext.getString(R.string.subscription_restoreNoSubscriptionAlert_message), this.mContext.getString(R.string.subscription_restoreNoSubscriptionAlert_button_ok), (String) null, (AlertBox.OnAlertListener) null);
        this.mAutoDismiss = false;
    }
}
