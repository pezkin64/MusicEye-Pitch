package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.widget.AnimatedProgressBar;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class LoadLayout extends RelativeLayout {
    /* access modifiers changed from: private */
    public TintButton m_ButCancel;
    /* access modifiers changed from: private */
    public CancelListener m_CancelListener = null;
    private Context m_Context;
    private AnimatedProgressBar m_Progress;
    private TextView m_Text;

    public interface CancelListener {
        void onCancel();
    }

    public LoadLayout(Context context) {
        super(context);
        init(context);
    }

    public LoadLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LoadLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        this.m_Context = context;
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_load, this, true);
        this.m_Text = (TextView) inflate.findViewById(R.id.text);
        this.m_Progress = (AnimatedProgressBar) inflate.findViewById(R.id.progress);
        TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_cancel);
        this.m_ButCancel = tintButton;
        tintButton.setVisibility(8);
        this.m_ButCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoadLayout.this.m_ButCancel.setVisibility(4);
                if (LoadLayout.this.m_CancelListener != null) {
                    LoadLayout.this.m_CancelListener.onCancel();
                }
            }
        });
    }

    public void open(String str) {
        setText(str);
        setProgress(0);
        this.m_ButCancel.setVisibility(8);
        setVisibility(0);
    }

    public boolean isOpen() {
        return getVisibility() == 0;
    }

    public void setText(String str) {
        setProgress(0);
        this.m_Text.setText(str);
    }

    public void setMaxProgress(int i) {
        setProgress(0);
        this.m_Progress.setMax(i);
    }

    public void setCancelListener(CancelListener cancelListener) {
        this.m_CancelListener = cancelListener;
        this.m_ButCancel.setVisibility(0);
    }

    public void close() {
        setVisibility(8);
    }

    public void setProgress(int i) {
        this.m_Progress.setProgress(i);
    }
}
