package com.android.billingclient.api;

import java.util.List;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
public interface ProductDetailsResponseListener {
    void onProductDetailsResponse(BillingResult billingResult, List<ProductDetails> list);
}
