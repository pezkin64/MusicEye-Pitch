package com.xemsoft.sheetmusicscanner2;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.xemsoft.sheetmusicscanner2.billing.BillingDataSource;
import com.xemsoft.sheetmusicscanner2.billing.SubscriptionUtility;
import com.xemsoft.sheetmusicscanner2.util.Logg;

public class ScannerApplication extends Application {
    private static final String LOGTAG = "ScannerApplication";
    private static ScannerApplication sInstance;
    public AppServices mAppServices;

    public class AppServices {
        final BillingDataSource billingDataSource;
        public final SubscriptionUtility subscriptionUtility;

        public AppServices() {
            BillingDataSource instance = BillingDataSource.getInstance(ScannerApplication.this);
            this.billingDataSource = instance;
            this.subscriptionUtility = new SubscriptionUtility(ScannerApplication.this, instance);
        }
    }

    public static ScannerApplication getInstance() {
        return sInstance;
    }

    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Logg.init(this);
        if (Build.VERSION.SDK_INT >= 35) {
            setInsets();
        }
        this.mAppServices = new AppServices();
    }

    private void setInsets() {
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            public void onActivityDestroyed(Activity activity) {
            }

            public void onActivityPaused(Activity activity) {
            }

            public void onActivityResumed(Activity activity) {
            }

            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            public void onActivityStarted(Activity activity) {
            }

            public void onActivityStopped(Activity activity) {
            }

            public void onActivityCreated(Activity activity, Bundle bundle) {
                ViewCompat.setOnApplyWindowInsetsListener(activity.getWindow().getDecorView().getRootView(), new OnApplyWindowInsetsListener() {
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.navigationBars() | WindowInsetsCompat.Type.displayCutout());
                        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
                        return WindowInsetsCompat.CONSUMED;
                    }
                });
            }
        });
    }
}
