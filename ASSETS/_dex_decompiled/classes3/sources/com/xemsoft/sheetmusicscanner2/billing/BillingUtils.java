package com.xemsoft.sheetmusicscanner2.billing;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ProductDetails;
import com.android.billingclient.api.Purchase;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.Logg;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;

public class BillingUtils {
    private static final String LOGTAG = "BillingUtils";
    private static final String[] RECURRENCE_MODE_STRING = {"0 - UNKNOWN", "1 - INFINITE_RECURRING", "2 - FINITE_RECURRING", "3 - NON_RECURRING"};

    public interface BillingListListener<T> {
        void onList(BillingResult billingResult, List<T> list);
    }

    public static ProductDetails.SubscriptionOfferDetails findOffer(ProductDetails productDetails, String str, String str2) {
        for (ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails : productDetails.getSubscriptionOfferDetails()) {
            String offerId = subscriptionOfferDetails.getOfferId();
            String basePlanId = subscriptionOfferDetails.getBasePlanId();
            if (((offerId == null && str == null) || (offerId != null && offerId.equals(str))) && basePlanId != null && basePlanId.equals(str2)) {
                return subscriptionOfferDetails;
            }
        }
        return null;
    }

    public static String createFormatPrice(float f, String str) {
        try {
            NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
            currencyInstance.setMaximumFractionDigits(2);
            currencyInstance.setCurrency(Currency.getInstance(str));
            return currencyInstance.format((double) f);
        } catch (Exception e) {
            Logg.e(LOGTAG, "format failed", e);
            return "N/A";
        }
    }

    public static OfferText getOfferText(Context context, Offer offer) {
        OfferText offerText = new OfferText();
        if (offer == null || !offer.isAvailable()) {
            return null;
        }
        String formatPrice = offer.getFormatPrice();
        String monthlyPrice = offer.getMonthlyPrice();
        int discount = offer.getDiscount();
        offerText.mOfferId = offer.mId;
        if (offer.mId.equals(SubscriptionUtility.WEEK_TRIAL_ID)) {
            offerText.mTitle = context.getString(R.string.subscriptionOption_yearlyWithTrial_title, new Object[]{formatPrice});
            offerText.mSubtitle = context.getString(R.string.subscriptionOption_yearlyWithTrial_subtitle, new Object[]{monthlyPrice});
            offerText.mLabel = null;
            offerText.mButton = context.getString(R.string.subscribeButton_yearlyWithTrial);
            return offerText;
        } else if (offer.mId.equals(SubscriptionUtility.YEARLY_WITH_TRIAL_ID)) {
            offerText.mTitle = context.getString(R.string.subscriptionOption_yearly_title, new Object[]{Integer.valueOf(discount)});
            offerText.mSubtitle = context.getString(R.string.subscriptionOption_yearlyNoTrial_subtitle, new Object[]{monthlyPrice, formatPrice});
            offerText.mLabel = context.getString(R.string._5wo_kY_AxJ_text);
            offerText.mButton = context.getString(R.string.subscribeButton_yearlyNoTrial);
            return offerText;
        } else if (offer.mId.equals(SubscriptionUtility.YEARLY_NO_TRIAL_ID)) {
            offerText.mTitle = context.getString(R.string.subscriptionOption_yearlyNoTrial_title, new Object[]{Integer.valueOf(discount)});
            offerText.mSubtitle = context.getString(R.string.subscriptionOption_yearlyNoTrial_subtitle, new Object[]{monthlyPrice, formatPrice});
            offerText.mLabel = context.getString(R.string._5wo_kY_AxJ_text);
            offerText.mButton = context.getString(R.string.subscribeButton_yearlyNoTrial);
            return offerText;
        } else {
            if (offer.mId.equals(SubscriptionUtility.MONTHLY_ID)) {
                offerText.mTitle = context.getString(R.string.subscriptionOption_monthly_title);
                offerText.mSubtitle = context.getString(R.string.subscriptionOption_monthly_subtitle, new Object[]{formatPrice});
                offerText.mLabel = null;
                offerText.mButton = context.getString(R.string.subscribeButton_monthly);
            }
            return offerText;
        }
    }

    public static String productDetailsToString(ProductDetails productDetails) {
        StringBuilder sb = new StringBuilder("\n--- Product details ---");
        sb.append("\nname: " + productDetails.getName());
        sb.append("\nid: " + productDetails.getProductId());
        sb.append("\ntype: " + productDetails.getProductType());
        sb.append("\ntitle: " + productDetails.getTitle());
        sb.append("\ndescription: " + productDetails.getDescription());
        sb.append("\n--- Subscription offer details list ---");
        int i = 0;
        for (ProductDetails.SubscriptionOfferDetails subscriptionOfferDetailsToString : productDetails.getSubscriptionOfferDetails()) {
            sb.append("\n[" + i + "] " + subscriptionOfferDetailsToString(subscriptionOfferDetailsToString));
            i++;
        }
        return sb.toString();
    }

    public static String subscriptionOfferDetailsToString(ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails) {
        StringBuilder sb = new StringBuilder("\n--- Subscription offer details ---");
        sb.append("\nid: " + subscriptionOfferDetails.getOfferId());
        sb.append("\nbase plan id: " + subscriptionOfferDetails.getBasePlanId());
        sb.append("\ntoken: " + subscriptionOfferDetails.getOfferToken());
        List<String> offerTags = subscriptionOfferDetails.getOfferTags();
        if (offerTags != null) {
            sb.append("\n--- Tags ---");
            int i = 0;
            for (String str : offerTags) {
                sb.append("\n[" + i + "] " + str);
                i++;
            }
        }
        sb.append(pricingPhasesToString(subscriptionOfferDetails.getPricingPhases()));
        return sb.toString();
    }

    public static String pricingPhasesToString(ProductDetails.PricingPhases pricingPhases) {
        StringBuilder sb = new StringBuilder("\n--- Pricing phases --- ");
        int i = 0;
        for (ProductDetails.PricingPhase pricingPhaseToString : pricingPhases.getPricingPhaseList()) {
            sb.append("\n[" + i + "] " + pricingPhaseToString(pricingPhaseToString));
            i++;
        }
        return sb.toString();
    }

    public static String pricingPhaseToString(ProductDetails.PricingPhase pricingPhase) {
        StringBuilder sb = new StringBuilder("\n--- Pricing phase ---");
        sb.append("\nformatted price: " + pricingPhase.getFormattedPrice());
        sb.append("\namount micros: " + pricingPhase.getPriceAmountMicros());
        sb.append("\ncurrency code: " + pricingPhase.getPriceCurrencyCode());
        sb.append("\nbilling period: " + pricingPhase.getBillingPeriod());
        sb.append("\ncycle count: " + pricingPhase.getBillingCycleCount());
        sb.append("\nrecurrence mode: " + RECURRENCE_MODE_STRING[pricingPhase.getRecurrenceMode()]);
        return sb.toString();
    }

    public static String purchaseToString(Purchase purchase) {
        return purchase.getOriginalJson().toString();
    }

    public static void openGpSubscriptionPage(Context context, String str) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://play.google.com/store/account/subscriptions?sku=" + str + "&package=" + context.getPackageName())));
        } catch (ActivityNotFoundException unused) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/account/subscriptions?sku=" + str + "&package=" + context.getPackageName())));
        }
    }

    public static Intent getPlayStoreIntent(Context context) {
        return context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
    }

    public static void launchActivityIntent(Context context, Intent intent) {
        if (intent != null) {
            context.startActivity(intent);
        }
    }
}
