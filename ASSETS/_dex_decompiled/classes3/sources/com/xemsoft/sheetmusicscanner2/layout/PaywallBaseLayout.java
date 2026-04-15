package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xemsoft.sheetmusicscanner2.util.Utils;

public class PaywallBaseLayout extends RelativeLayout {
    Context mContext;
    protected View mPadLeft;
    protected View mPadRight;

    public PaywallBaseLayout(Context context) {
        super(context);
    }

    public PaywallBaseLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PaywallBaseLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        this.mContext = context;
    }

    public void onConfigurationChanged(Configuration configuration) {
        manageLayout();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            manageLayout();
        }
    }

    /* access modifiers changed from: package-private */
    public void setPads(View view, View view2) {
        this.mPadLeft = view;
        this.mPadRight = view2;
    }

    /* access modifiers changed from: package-private */
    public void manageLayout() {
        float f = (((float) Utils.getDpWidth(this.mContext)) <= 540.0f || getResources().getConfiguration().orientation != 2) ? 1.0f : 3.0f;
        View view = this.mPadLeft;
        if (view != null) {
            setWeight(view, f);
        }
        View view2 = this.mPadRight;
        if (view2 != null) {
            setWeight(view2, f);
        }
    }

    private void setWeight(View view, float f) {
        view.setLayoutParams(new LinearLayout.LayoutParams(0, -1, f));
    }
}
