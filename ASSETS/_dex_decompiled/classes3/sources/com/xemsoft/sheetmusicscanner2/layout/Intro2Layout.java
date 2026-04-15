package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import com.xemsoft.sheetmusicscanner2.R;

public class Intro2Layout extends PaywallBaseLayout {
    private static final String LOGTAG = "Intro2Layout";
    private Button mButContinue;
    Intro2LayoutListener mListener;
    private TextView mTextSubscription;

    public interface Intro2LayoutListener {
        void onContinue();
    }

    public Intro2Layout(Context context) {
        super(context);
        init(context);
    }

    public Intro2Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public Intro2Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        super.init(context);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_intro2, this, true);
        this.mButContinue = (Button) inflate.findViewById(R.id.but_continue);
        this.mTextSubscription = (TextView) inflate.findViewById(R.id.text_subscription);
        setPads(inflate.findViewById(R.id.pad_left), inflate.findViewById(R.id.pad_right));
        this.mTextSubscription.setText(HtmlCompat.fromHtml(this.mContext.getString(R.string.subscriptionIntroView_description), 0));
        this.mButContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Intro2Layout.this.mListener != null) {
                    Intro2Layout.this.mListener.onContinue();
                }
            }
        });
        manageLayout();
    }

    public void setListener(Intro2LayoutListener intro2LayoutListener) {
        this.mListener = intro2LayoutListener;
    }
}
