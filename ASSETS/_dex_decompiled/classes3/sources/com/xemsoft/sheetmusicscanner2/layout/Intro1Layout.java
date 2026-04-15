package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import com.xemsoft.sheetmusicscanner2.R;

public class Intro1Layout extends PaywallBaseLayout {
    private static final String LOGTAG = "Intro1Layout";
    private Button mButContinue;
    Intro1LayoutListener mListener;

    public interface Intro1LayoutListener {
        void onContinue();
    }

    public void setListener(Intro1LayoutListener intro1LayoutListener) {
        this.mListener = intro1LayoutListener;
    }

    public Intro1Layout(Context context) {
        super(context);
        init(context);
    }

    public Intro1Layout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public Intro1Layout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        super.init(context);
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_intro1, this, true);
        this.mButContinue = (Button) inflate.findViewById(R.id.but_continue);
        setPads(inflate.findViewById(R.id.pad_left), inflate.findViewById(R.id.pad_right));
        this.mButContinue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (Intro1Layout.this.mListener != null) {
                    Intro1Layout.this.mListener.onContinue();
                }
            }
        });
        manageLayout();
    }
}
