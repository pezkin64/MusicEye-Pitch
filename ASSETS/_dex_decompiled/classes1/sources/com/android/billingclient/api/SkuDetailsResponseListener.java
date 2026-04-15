package com.android.billingclient.api;

import java.util.List;

@Deprecated
/* compiled from: com.android.billingclient:billing@@7.0.0 */
public interface SkuDetailsResponseListener {
    void onSkuDetailsResponse(BillingResult billingResult, List<SkuDetails> list);
}
