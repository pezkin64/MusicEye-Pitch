package com.xemsoft.sheetmusicscanner2.billing;

import com.android.billingclient.api.ProductDetails;
import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.util.List;

public class Offer {
    private static final String LOGTAG = "Offer";
    private boolean mAvailable = false;
    private String mCurrency = null;
    private ProductDetails.SubscriptionOfferDetails mDetails;
    private int mDiscount = 0;
    private String mFormatPrice = null;
    public String mId;
    private boolean mInitialized = false;
    private long mMicroPrice = 0;
    private String mMonthlyPrice = BuildConfig.FLAVOR;

    public Offer(String str) {
        this.mId = str;
    }

    public void setDetails(ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails) {
        this.mDetails = subscriptionOfferDetails;
        if (subscriptionOfferDetails != null) {
            try {
                List pricingPhaseList = subscriptionOfferDetails.getPricingPhases().getPricingPhaseList();
                ProductDetails.PricingPhase pricingPhase = (ProductDetails.PricingPhase) pricingPhaseList.get(pricingPhaseList.size() > 1 ? 1 : 0);
                if (pricingPhase != null) {
                    this.mMicroPrice = pricingPhase.getPriceAmountMicros();
                    String priceCurrencyCode = pricingPhase.getPriceCurrencyCode();
                    this.mCurrency = priceCurrencyCode;
                    this.mFormatPrice = BillingUtils.createFormatPrice(((float) this.mMicroPrice) / 1000000.0f, priceCurrencyCode);
                    this.mMonthlyPrice = BillingUtils.createFormatPrice(((float) this.mMicroPrice) / 1.2E7f, this.mCurrency);
                    this.mInitialized = true;
                }
            } catch (Exception e) {
                Logg.e(LOGTAG, "setDetails()", e);
            }
        }
    }

    public ProductDetails.SubscriptionOfferDetails getDetails() {
        return this.mDetails;
    }

    public boolean isInitialized() {
        return this.mInitialized;
    }

    public void setAvailable(boolean z) {
        this.mAvailable = z;
    }

    public boolean isAvailable() {
        return this.mAvailable;
    }

    public void setDiscount(int i) {
        this.mDiscount = i;
    }

    public int getDiscount() {
        return this.mDiscount;
    }

    public String getFormatPrice() {
        return this.mFormatPrice;
    }

    public long getMicroPrice() {
        return this.mMicroPrice;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public void setMonthlyPrice(String str) {
        this.mMonthlyPrice = str;
    }

    public String getMonthlyPrice() {
        return this.mMonthlyPrice;
    }
}
