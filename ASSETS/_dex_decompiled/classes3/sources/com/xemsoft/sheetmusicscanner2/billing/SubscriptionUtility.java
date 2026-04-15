package com.xemsoft.sheetmusicscanner2.billing;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Handler;
import android.os.Looper;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.billing.BillingDataSource;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.util.List;

public class SubscriptionUtility {
    /* access modifiers changed from: private */
    public static final String LOGTAG = "SubscriptionUtility";
    public static final int MONTHLY = 2;
    public static final String MONTHLY_ID = "monthly";
    public static final int OFFER_COUNT = 4;
    public static final String PRO_ACCESS_ID = "pro_access";
    public static final int WEEK_TRIAL = 0;
    public static final String WEEK_TRIAL_ID = "7-day-trial";
    public static final int YEARLY_NO_TRIAL = 1;
    public static final String YEARLY_NO_TRIAL_ID = "yearly-no-trial";
    public static final int YEARLY_WITH_TRIAL = 3;
    public static final String YEARLY_WITH_TRIAL_ID = "yearly-with-trial";
    /* access modifiers changed from: private */
    public static final Handler mHandler = new Handler(Looper.getMainLooper());
    private Application mApp;
    /* access modifiers changed from: private */
    public BillingDataSource mBilling;
    private BillingDataSource.BillingDataSourceListener mBillingListener = new BillingDataSource.BillingDataSourceListener() {
        public void onDisconnected() {
        }

        public void onConnected() {
            if (SubscriptionUtility.this.mBilling.hasCapabilites()) {
                SubscriptionUtility.this.refreshProduct();
                SubscriptionUtility.this.refreshPurchases();
                return;
            }
            boolean unused = SubscriptionUtility.this.mIsBillingEnabled = false;
            if (SubscriptionUtility.this.mListener != null) {
                SubscriptionUtility.this.mListener.onBillingFailed();
            }
        }

        public void onConnectFailed() {
            if (SubscriptionUtility.this.mListener != null) {
                SubscriptionUtility.this.mListener.onBillingFailed();
            }
        }

        public void onProductDetails(BillingResult billingResult, List<ProductDetails> list) {
            synchronized (this) {
                int responseCode = billingResult.getResponseCode();
                if (responseCode == 0) {
                    ProductDetails unused = SubscriptionUtility.this.mProductProDetails = list.get(0);
                    SubscriptionUtility.this.mOffers[0].setDetails(BillingUtils.findOffer(SubscriptionUtility.this.mProductProDetails, SubscriptionUtility.WEEK_TRIAL_ID, SubscriptionUtility.YEARLY_WITH_TRIAL_ID));
                    SubscriptionUtility.this.mOffers[3].setDetails(BillingUtils.findOffer(SubscriptionUtility.this.mProductProDetails, (String) null, SubscriptionUtility.YEARLY_WITH_TRIAL_ID));
                    SubscriptionUtility.this.mOffers[1].setDetails(BillingUtils.findOffer(SubscriptionUtility.this.mProductProDetails, (String) null, SubscriptionUtility.YEARLY_NO_TRIAL_ID));
                    SubscriptionUtility.this.mOffers[2].setDetails(BillingUtils.findOffer(SubscriptionUtility.this.mProductProDetails, (String) null, SubscriptionUtility.MONTHLY_ID));
                    SubscriptionUtility.this.calculateOfferDiscounts();
                    SubscriptionUtility.this.setOffersAvailability();
                    String productDetailsToString = BillingUtils.productDetailsToString(SubscriptionUtility.this.mProductProDetails);
                    if (!productDetailsToString.equals(SubscriptionUtility.this.mProductDetailsString)) {
                        String unused2 = SubscriptionUtility.this.mProductDetailsString = productDetailsToString;
                    }
                } else {
                    String access$900 = SubscriptionUtility.LOGTAG;
                    Logg.e(access$900, "Product details failed - code: " + responseCode + " error: " + billingResult.getDebugMessage());
                }
            }
        }

        public void onPurchases(BillingResult billingResult, List<Purchase> list) {
            synchronized (this) {
                int responseCode = billingResult.getResponseCode();
                if (responseCode == 0) {
                    boolean z = false;
                    for (Purchase next : list) {
                        for (String equals : next.getProducts()) {
                            if (equals.equals(SubscriptionUtility.PRO_ACCESS_ID)) {
                                Purchase unused = SubscriptionUtility.this.mPurchase = next;
                                SubscriptionUtility.this.handlePurchase(next);
                                z = true;
                            }
                        }
                    }
                    if (!z) {
                        ProductState unused2 = SubscriptionUtility.this.mProductState = ProductState.STATE_UNPURCHASED;
                        if (SubscriptionUtility.this.mListener != null) {
                            SubscriptionUtility.this.mListener.onUpdate();
                        }
                    }
                } else if (!(responseCode == 1 || responseCode == 3)) {
                    Logg.e(SubscriptionUtility.LOGTAG, "onPurchases() - Purchase list failed - code: " + responseCode + " error: " + billingResult.getDebugMessage());
                    ProductState unused3 = SubscriptionUtility.this.mProductState = ProductState.STATE_ERROR;
                    if (SubscriptionUtility.this.mListener != null) {
                        SubscriptionUtility.this.mListener.onUpdate();
                    }
                }
            }
        }

        public void onAcknowledge(BillingResult billingResult, Purchase purchase) {
            synchronized (this) {
                if (billingResult.getResponseCode() == 0) {
                    ProductState unused = SubscriptionUtility.this.mProductState = ProductState.STATE_VALID;
                    if (SubscriptionUtility.this.mListener != null) {
                        SubscriptionUtility.this.mListener.onUpdate();
                    }
                }
            }
        }
    };
    private ConnectivityManager mConnectivityManager;
    /* access modifiers changed from: private */
    public boolean mIsBillingEnabled = true;
    /* access modifiers changed from: private */
    public SubscriptionUtilityListener mListener = null;
    private ConnectivityManager.NetworkCallback mNetworkCallback = new ConnectivityManager.NetworkCallback() {
        public void onAvailable(Network network) {
            SubscriptionUtility.mHandler.post(new Runnable() {
                public void run() {
                    if (SubscriptionUtility.this.isNetworkConnected()) {
                        SubscriptionUtility.this.refreshProduct();
                        SubscriptionUtility.this.refreshPurchases();
                    }
                }
            });
        }

        public void onLost(Network network) {
            SubscriptionUtility.mHandler.post(new Runnable() {
                public void run() {
                    if (SubscriptionUtility.this.mListener != null) {
                        SubscriptionUtility.this.mListener.onUpdate();
                    }
                }
            });
        }

        public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
            SubscriptionUtility.mHandler.post(new Runnable() {
                public void run() {
                    if (SubscriptionUtility.this.isNetworkConnected()) {
                        SubscriptionUtility.this.refreshProduct();
                        SubscriptionUtility.this.refreshPurchases();
                    }
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public Offer[] mOffers = {new Offer(WEEK_TRIAL_ID), new Offer(YEARLY_NO_TRIAL_ID), new Offer(MONTHLY_ID), new Offer(YEARLY_WITH_TRIAL_ID)};
    private UserSettings mPrefs;
    /* access modifiers changed from: private */
    public String mProductDetailsString = BuildConfig.FLAVOR;
    /* access modifiers changed from: private */
    public ProductDetails mProductProDetails;
    /* access modifiers changed from: private */
    public ProductState mProductState = ProductState.STATE_UNKNOWN;
    /* access modifiers changed from: private */
    public Purchase mPurchase;

    public enum ProductState {
        STATE_UNKNOWN,
        STATE_WAITING,
        STATE_UNPURCHASED,
        STATE_PENDING,
        STATE_PURCHASED,
        STATE_VALID,
        STATE_ERROR
    }

    public interface SubscriptionUtilityListener {
        void onBillingFailed();

        void onUpdate();
    }

    public SubscriptionUtility(Application application, BillingDataSource billingDataSource) {
        this.mApp = application;
        this.mPrefs = UserSettings.getInstance(application);
        this.mBilling = billingDataSource;
        billingDataSource.setListener(this.mBillingListener);
        networkMonitorSetup();
    }

    public void setListener(SubscriptionUtilityListener subscriptionUtilityListener) {
        this.mListener = subscriptionUtilityListener;
    }

    public void connect() {
        this.mBilling.connectToPlayStore();
    }

    public void disconnect() {
        try {
            this.mConnectivityManager.unregisterNetworkCallback(this.mNetworkCallback);
        } catch (Exception e) {
            Logg.e(LOGTAG, "disconnect() - unregisterNetworkCallback", e);
        }
        this.mBilling.disconnectFromPlayStore();
    }

    public boolean hasCapabilites() {
        boolean hasCapabilites = this.mBilling.hasCapabilites();
        this.mIsBillingEnabled = hasCapabilites;
        return hasCapabilites;
    }

    public void refreshProduct() {
        this.mBilling.getProductDetails(PRO_ACCESS_ID);
    }

    public void refreshPurchases() {
        if (this.mProductState == ProductState.STATE_UNKNOWN) {
            this.mProductState = ProductState.STATE_WAITING;
            mHandler.postDelayed(new Runnable() {
                public void run() {
                    if (SubscriptionUtility.this.mProductState == ProductState.STATE_WAITING) {
                        ProductState unused = SubscriptionUtility.this.mProductState = ProductState.STATE_ERROR;
                        if (SubscriptionUtility.this.mListener != null) {
                            SubscriptionUtility.this.mListener.onUpdate();
                        }
                    }
                }
            }, 3000);
        }
        this.mBilling.getPurchases();
    }

    public ProductState getProductState() {
        return this.mProductState;
    }

    public void purchaseWeekTrial(Activity activity) {
        purchaseProduct(activity, 0);
    }

    public void purchaseYearlyWithTrial(Activity activity) {
        purchaseProduct(activity, 3);
    }

    public void purchaseYearlyNoTrial(Activity activity) {
        purchaseProduct(activity, 1);
    }

    public void purchaseMonthly(Activity activity) {
        purchaseProduct(activity, 2);
    }

    public void manageSubscription(Context context) {
        BillingUtils.openGpSubscriptionPage(context, PRO_ACCESS_ID);
    }

    public boolean isBillingEnabled() {
        return this.mIsBillingEnabled;
    }

    public synchronized boolean isTrialAvailable() {
        boolean trialAvailable;
        trialAvailable = this.mPrefs.getTrialAvailable();
        if (this.mOffers[0].isInitialized()) {
            trialAvailable = true;
        } else if (this.mOffers[3].isInitialized()) {
            trialAvailable = false;
        }
        this.mPrefs.setTrialAvailable(trialAvailable);
        return trialAvailable;
    }

    public synchronized boolean isUnlocked() {
        boolean subscriptionActive;
        subscriptionActive = this.mPrefs.getSubscriptionActive();
        if (this.mProductState != ProductState.STATE_PURCHASED) {
            if (this.mProductState != ProductState.STATE_VALID) {
                if (this.mProductState == ProductState.STATE_UNPURCHASED) {
                    subscriptionActive = false;
                }
                this.mPrefs.setSubscriptionActive(subscriptionActive);
            }
        }
        subscriptionActive = true;
        this.mPrefs.setSubscriptionActive(subscriptionActive);
        return subscriptionActive;
    }

    public synchronized boolean purchaseProduct(Activity activity, String str) {
        Offer[] offerArr = this.mOffers;
        int length = offerArr.length;
        int i = 0;
        while (i < length) {
            Offer offer = offerArr[i];
            if (!offer.mId.equals(str)) {
                i++;
            } else if (offer.isAvailable()) {
                purchaseProduct(activity, offer);
                return true;
            } else {
                Logg.e(LOGTAG, "purchaseProduct() - " + str + " not available");
                return false;
            }
        }
        Logg.e(LOGTAG, "purchaseProduct() - " + str + " not found");
        return false;
    }

    private boolean purchaseProduct(Activity activity, int i) {
        return purchaseProduct(activity, this.mOffers[i]);
    }

    private synchronized boolean purchaseProduct(Activity activity, Offer offer) {
        ProductDetails.SubscriptionOfferDetails details = offer.getDetails();
        if (details == null) {
            Logg.e(LOGTAG, "purchaseProduct() - offer not initialized");
            return false;
        } else if (details.getOfferToken() == null) {
            Logg.e(LOGTAG, "purchaseProduct() - token not found");
            return false;
        } else {
            BillingResult purchaseOffer = this.mBilling.purchaseOffer(activity, this.mProductProDetails, details.getOfferToken());
            if (purchaseOffer != null) {
                int responseCode = purchaseOffer.getResponseCode();
                if (responseCode == 0) {
                    return true;
                }
                String str = LOGTAG;
                Logg.e(str, "purchaseProduct() - purchaseOffer code: " + responseCode + " error: " + purchaseOffer.getDebugMessage());
                return false;
            }
            Logg.e(LOGTAG, "purchaseProduct() - purchaseOffer returned null");
            return false;
        }
    }

    /* access modifiers changed from: private */
    public synchronized void handlePurchase(Purchase purchase) {
        int purchaseState = purchase.getPurchaseState();
        if (purchaseState == 0) {
            this.mProductState = ProductState.STATE_ERROR;
        } else if (purchaseState != 1) {
            if (purchaseState != 2) {
                this.mProductState = ProductState.STATE_ERROR;
            } else {
                this.mProductState = ProductState.STATE_PENDING;
            }
        } else if (!purchase.isAcknowledged()) {
            this.mProductState = ProductState.STATE_PURCHASED;
            if (this.mBilling.isSignatureValid(purchase)) {
                this.mBilling.acknowledge(purchase);
            } else {
                String str = LOGTAG;
                Logg.w(str, "handlePurchase() - Invalid signature! " + purchase.getOriginalJson().toString());
            }
        } else {
            this.mProductState = ProductState.STATE_VALID;
        }
        SubscriptionUtilityListener subscriptionUtilityListener = this.mListener;
        if (subscriptionUtilityListener != null) {
            subscriptionUtilityListener.onUpdate();
        }
    }

    /* access modifiers changed from: private */
    public synchronized void calculateOfferDiscounts() {
        float microPrice = (float) this.mOffers[3].getMicroPrice();
        float microPrice2 = (float) this.mOffers[1].getMicroPrice();
        float microPrice3 = (float) (this.mOffers[2].getMicroPrice() * 12);
        int round = Math.round(((microPrice3 - microPrice2) / microPrice3) * 100.0f);
        this.mOffers[3].setDiscount(Math.round(((microPrice3 - microPrice) / microPrice3) * 100.0f));
        this.mOffers[1].setDiscount(round);
    }

    /* access modifiers changed from: private */
    public synchronized void setOffersAvailability() {
        if (isTrialAvailable()) {
            this.mOffers[0].setAvailable(true);
            this.mOffers[3].setAvailable(false);
            this.mOffers[1].setAvailable(true);
        } else {
            this.mOffers[0].setAvailable(false);
            this.mOffers[3].setAvailable(true);
            this.mOffers[1].setAvailable(false);
        }
        this.mOffers[2].setAvailable(true);
    }

    public OfferText[] getOfferTextList() {
        OfferText[] offerTextArr = new OfferText[4];
        boolean z = false;
        for (int i = 0; i < 4; i++) {
            OfferText offerText = BillingUtils.getOfferText(this.mApp, this.mOffers[i]);
            offerTextArr[i] = offerText;
            if (offerText != null) {
                z = true;
            }
        }
        if (z) {
            return offerTextArr;
        }
        return null;
    }

    public boolean isNetworkConnected() {
        NetworkCapabilities networkCapabilities;
        Network activeNetwork = this.mConnectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = this.mConnectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasCapability(16);
    }

    private void networkMonitorSetup() {
        this.mConnectivityManager = (ConnectivityManager) this.mApp.getSystemService("connectivity");
        this.mConnectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addCapability(12).addTransportType(1).addTransportType(0).build(), this.mNetworkCallback);
    }
}
