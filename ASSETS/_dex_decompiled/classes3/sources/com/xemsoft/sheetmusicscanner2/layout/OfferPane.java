package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.billing.OfferText;

public class OfferPane extends RelativeLayout {
    private static final String LOGTAG = "OfferPane";
    private TextView mLabel;
    private TextView mSubtitle;
    private TextView mTitle;
    private View mView;

    public OfferPane(Context context) {
        super(context);
        init(context);
    }

    public OfferPane(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public OfferPane(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.pane_offer, this, true);
        this.mView = inflate;
        this.mLabel = (TextView) inflate.findViewById(R.id.text_label);
        this.mTitle = (TextView) this.mView.findViewById(R.id.text_title);
        this.mSubtitle = (TextView) this.mView.findViewById(R.id.text_subtitle);
    }

    public void setText(OfferText offerText) {
        if (offerText.mLabel != null) {
            this.mLabel.setVisibility(0);
            this.mLabel.setText(offerText.mLabel);
        } else {
            this.mLabel.setVisibility(4);
        }
        this.mTitle.setText(offerText.mTitle);
        this.mSubtitle.setText(offerText.mSubtitle);
    }
}
