package com.xemsoft.sheetmusicscanner2.billing;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.ProductDetailsResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesResponseListener;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.QueryProductDetailsParams;
import com.android.billingclient.api.QueryPurchasesParams;
import com.google.common.collect.ImmutableList;
import com.xemsoft.sheetmusicscanner2.billing.BillingUtils;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.util.List;

public class BillingDataSource {
    /* access modifiers changed from: private */
    public static final String LOGTAG = "BillingDataSource";
    private static final boolean MOCK_OUTDATED_PLAY_STORE = false;
    private static final long RECONNECT_TIMER_MAX_TIME_MILLISECONDS = 900000;
    private static final long RECONNECT_TIMER_START_MILLISECONDS = 1000;
    private static final Handler mHandler = new Handler(Looper.getMainLooper());
    private static volatile BillingDataSource sInstance;
    private BillingClient mBillingClient;
    private BillingClientStateListener mBillingClientStateListener = new BillingClientStateListener() {
        public void onBillingSetupFinished(BillingResult billingResult) {
            int responseCode = billingResult.getResponseCode();
            if (responseCode == 0) {
                long unused = BillingDataSource.this.mReconnectMilliseconds = BillingDataSource.RECONNECT_TIMER_START_MILLISECONDS;
                ConnectionState unused2 = BillingDataSource.this.mConnectionState = ConnectionState.STATE_CONNECTED;
                if (BillingDataSource.this.mListener != null) {
                    BillingDataSource.this.mListener.onConnected();
                    return;
                }
                return;
            }
            Logg.e(BillingDataSource.LOGTAG, "Connection to GP failed");
            if (responseCode == -2 || responseCode == 3) {
                ConnectionState unused3 = BillingDataSource.this.mConnectionState = ConnectionState.STATE_ABORTED;
                if (BillingDataSource.this.mListener != null) {
                    BillingDataSource.this.mListener.onConnectFailed();
                    return;
                }
                return;
            }
            ConnectionState unused4 = BillingDataSource.this.mConnectionState = ConnectionState.STATE_CONNECTING;
            BillingDataSource.this.retryConnectToGp();
        }

        public void onBillingServiceDisconnected() {
            if (BillingDataSource.this.mListener != null) {
                BillingDataSource.this.mListener.onDisconnected();
            }
            ConnectionState unused = BillingDataSource.this.mConnectionState = ConnectionState.STATE_CONNECTING;
            BillingDataSource.this.retryConnectToGp();
        }
    };
    /* access modifiers changed from: private */
    public ConnectionState mConnectionState = ConnectionState.STATE_CONNECTING;
    /* access modifiers changed from: private */
    public BillingDataSourceListener mListener;
    /* access modifiers changed from: private */
    public long mReconnectMilliseconds = RECONNECT_TIMER_START_MILLISECONDS;
    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
            if (BillingDataSource.this.mListener != null) {
                BillingDataSource.this.mListener.onPurchases(billingResult, list);
            }
        }
    };

    public interface BillingDataSourceListener {
        void onAcknowledge(BillingResult billingResult, Purchase purchase);

        void onConnectFailed();

        void onConnected();

        void onDisconnected();

        void onProductDetails(BillingResult billingResult, List<ProductDetails> list);

        void onPurchases(BillingResult billingResult, List<Purchase> list);
    }

    public enum ConnectionState {
        STATE_CONNECTING,
        STATE_CONNECTED,
        STATE_ABORTED,
        STATE_ENDED
    }

    public BillingDataSource(Application application) {
        this.mBillingClient = BillingClient.newBuilder(application).setListener(this.purchasesUpdatedListener).enablePendingPurchases().build();
    }

    public static BillingDataSource getInstance(Application application) {
        if (sInstance == null) {
            synchronized (BillingDataSource.class) {
                if (sInstance == null) {
                    sInstance = new BillingDataSource(application);
                }
            }
        }
        return sInstance;
    }

    public void setListener(BillingDataSourceListener billingDataSourceListener) {
        this.mListener = billingDataSourceListener;
    }

    public boolean hasCapabilites() {
        BillingResult isFeatureSupported = this.mBillingClient.isFeatureSupported("fff");
        int responseCode = isFeatureSupported.getResponseCode();
        isFeatureSupported.getDebugMessage();
        BillingResult isFeatureSupported2 = this.mBillingClient.isFeatureSupported("subscriptions");
        int responseCode2 = isFeatureSupported2.getResponseCode();
        isFeatureSupported2.getDebugMessage();
        BillingResult isFeatureSupported3 = this.mBillingClient.isFeatureSupported("subscriptionsUpdate");
        int responseCode3 = isFeatureSupported3.getResponseCode();
        isFeatureSupported3.getDebugMessage();
        boolean z = responseCode == 0 && responseCode2 == 0 && responseCode3 == 0;
        if (z) {
            return z;
        }
        Logg.e(LOGTAG, "hasCapabilities() - Capabilities failed");
        return z;
    }

    public void connectToPlayStore() {
        this.mBillingClient.startConnection(this.mBillingClientStateListener);
    }

    public void disconnectFromPlayStore() {
        this.mBillingClient.endConnection();
        this.mConnectionState = ConnectionState.STATE_ENDED;
    }

    public boolean isConnected() {
        return this.mConnectionState == ConnectionState.STATE_CONNECTED;
    }

    public ConnectionState getConnectionState() {
        return this.mConnectionState;
    }

    public void getProductDetails(String str) {
        if (isConnected()) {
            getProductDetailsList(str, new BillingUtils.BillingListListener<ProductDetails>() {
                public void onList(BillingResult billingResult, List<ProductDetails> list) {
                    if (BillingDataSource.this.mListener != null) {
                        BillingDataSource.this.mListener.onProductDetails(billingResult, list);
                    }
                }
            });
        } else {
            Logg.w(LOGTAG, "getProductDetails() - not connected");
        }
    }

    public void getPurchases() {
        if (isConnected()) {
            getPurchaseList(new BillingUtils.BillingListListener<Purchase>() {
                public void onList(BillingResult billingResult, List<Purchase> list) {
                    if (BillingDataSource.this.mListener != null) {
                        BillingDataSource.this.mListener.onPurchases(billingResult, list);
                    }
                }
            });
        } else {
            Logg.w(LOGTAG, "getPurchases() - not connected");
        }
    }

    public BillingResult purchaseOffer(Activity activity, ProductDetails productDetails, String str) {
        if (isConnected()) {
            return this.mBillingClient.launchBillingFlow(activity, BillingFlowParams.newBuilder().setProductDetailsParamsList(ImmutableList.of(BillingFlowParams.ProductDetailsParams.newBuilder().setProductDetails(productDetails).setOfferToken(str).build())).setIsOfferPersonalized(true).build());
        }
        Logg.w(LOGTAG, "purchaseOffer() - not connected");
        return null;
    }

    public boolean isSignatureValid(Purchase purchase) {
        return Security.verifyPurchase(purchase.getOriginalJson(), purchase.getSignature());
    }

    public void acknowledge(final Purchase purchase) {
        if (isConnected() && !purchase.isAcknowledged()) {
            this.mBillingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), new AcknowledgePurchaseResponseListener() {
                public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                    billingResult.getResponseCode();
                    if (BillingDataSource.this.mListener != null) {
                        BillingDataSource.this.mListener.onAcknowledge(billingResult, purchase);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void retryConnectToGp() {
        mHandler.postDelayed(new BillingDataSource$$ExternalSyntheticLambda0(this), this.mReconnectMilliseconds);
        this.mReconnectMilliseconds = Math.min(this.mReconnectMilliseconds * 2, RECONNECT_TIMER_MAX_TIME_MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$retryConnectToGp$0$com-xemsoft-sheetmusicscanner2-billing-BillingDataSource  reason: not valid java name */
    public /* synthetic */ void m0lambda$retryConnectToGp$0$comxemsoftsheetmusicscanner2billingBillingDataSource() {
        this.mBillingClient.startConnection(this.mBillingClientStateListener);
    }

    private void getProductDetailsList(String str, final BillingUtils.BillingListListener billingListListener) {
        if (isConnected()) {
            this.mBillingClient.queryProductDetailsAsync(QueryProductDetailsParams.newBuilder().setProductList(ImmutableList.of(QueryProductDetailsParams.Product.newBuilder().setProductId(str).setProductType("subs").build())).build(), new ProductDetailsResponseListener() {
                public void onProductDetailsResponse(BillingResult billingResult, List<ProductDetails> list) {
                    billingListListener.onList(billingResult, list);
                }
            });
            return;
        }
        Logg.w(LOGTAG, "retryConnectToGp() - not connected");
    }

    private void getPurchaseList(final BillingUtils.BillingListListener billingListListener) {
        if (isConnected()) {
            this.mBillingClient.queryPurchasesAsync(QueryPurchasesParams.newBuilder().setProductType("subs").build(), new PurchasesResponseListener() {
                public void onQueryPurchasesResponse(BillingResult billingResult, List<Purchase> list) {
                    billingListListener.onList(billingResult, list);
                }
            });
            return;
        }
        Logg.w(LOGTAG, "getPurchaseList() - not connected");
    }
}
