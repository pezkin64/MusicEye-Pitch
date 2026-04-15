package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.util.FlurryUtils;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;

public class FeedbackDialogs {
    private static final String LOGTAG = "FeedbackDialog.java";
    /* access modifiers changed from: private */
    public TintButton m_ButEmailCancel;
    /* access modifiers changed from: private */
    public TintButton m_ButEmailSend;
    /* access modifiers changed from: private */
    public TintButton m_ButFbBetter;
    /* access modifiers changed from: private */
    public TintButton m_ButFbDismiss;
    /* access modifiers changed from: private */
    public TintButton m_ButFbGood;
    /* access modifiers changed from: private */
    public TintButton m_ButRate;
    /* access modifiers changed from: private */
    public TintButton m_ButRateCancel;
    private DialogInterface.OnCancelListener m_CancelListener = new DialogInterface.OnCancelListener() {
        public void onCancel(DialogInterface dialogInterface) {
            FeedbackDialogs.this.close();
        }
    };
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        public void onClick(View view) {
            if (view == FeedbackDialogs.this.m_ButFbGood) {
                FlurryUtils.valueEvent("Feedback", "good", "yes");
                FeedbackDialogs.this.closeFb();
                FeedbackDialogs.this.openRate();
            } else if (view == FeedbackDialogs.this.m_ButFbBetter) {
                FlurryUtils.valueEvent("Feedback", "good", "couldBeBetter");
                FeedbackDialogs.this.closeFb();
                FeedbackDialogs.this.openEmail();
            } else if (view == FeedbackDialogs.this.m_ButFbDismiss) {
                FeedbackDialogs.this.close();
            } else if (view == FeedbackDialogs.this.m_ButEmailSend) {
                FlurryUtils.valueEvent("SendFeedback", "accepted", "yes");
                FeedbackDialogs.this.close();
                if (FeedbackDialogs.this.m_Listener != null) {
                    FeedbackDialogs.this.m_Listener.onEmail();
                }
            } else if (view == FeedbackDialogs.this.m_ButEmailCancel) {
                FlurryUtils.valueEvent("SendFeedback", "accepted", "yes");
                FeedbackDialogs.this.close();
            } else if (view == FeedbackDialogs.this.m_ButRate) {
                FlurryUtils.valueEvent("Rating", "accepted", "yes");
                FeedbackDialogs.this.close();
                if (FeedbackDialogs.this.m_Listener != null) {
                    FeedbackDialogs.this.m_Listener.onRate();
                }
            } else if (view == FeedbackDialogs.this.m_ButRateCancel) {
                FlurryUtils.valueEvent("Rating", "accepted", "no");
                FeedbackDialogs.this.close();
            }
        }
    };
    private Context m_Context;
    private AlertDialog m_DlgEmail = null;
    private AlertDialog m_DlgFb = null;
    private AlertDialog m_DlgRate = null;
    /* access modifiers changed from: private */
    public OnFeedbackListener m_Listener = null;
    private UserSettings m_Prefs;

    public interface OnFeedbackListener {
        void onEmail();

        void onRate();
    }

    public FeedbackDialogs(Context context) {
        this.m_Context = context;
        this.m_Prefs = UserSettings.getInstance(context);
    }

    public void open(boolean z) {
        openFb(z);
    }

    public void open() {
        openFb(false);
    }

    public void close() {
        closeFb();
        closeEmail();
        closeRate();
    }

    public void setListener(OnFeedbackListener onFeedbackListener) {
        this.m_Listener = onFeedbackListener;
    }

    private void openFb(boolean z) {
        FlurryUtils.valueEvent("Feedback", "manually_invoked", !z ? "yes" : "no");
        View inflate = LayoutInflater.from(this.m_Context).inflate(!z ? R.layout.dialog_feedback : R.layout.dialog_feedback_alt, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_ButFbGood = (TintButton) inflate.findViewById(R.id.but_good);
        this.m_ButFbBetter = (TintButton) inflate.findViewById(R.id.but_better);
        this.m_ButFbDismiss = (TintButton) inflate.findViewById(R.id.but_dismiss);
        AlertDialog create = builder.create();
        this.m_DlgFb = create;
        create.setOnCancelListener(this.m_CancelListener);
        this.m_ButFbGood.setOnClickListener(this.m_ClickListener);
        this.m_ButFbBetter.setOnClickListener(this.m_ClickListener);
        this.m_ButFbDismiss.setOnClickListener(this.m_ClickListener);
        Utils.dlgSetTransparent(this.m_DlgFb);
        this.m_DlgFb.setCanceledOnTouchOutside(true);
        this.m_DlgFb.show();
        this.m_Prefs.ratingSetDialogDisplayed();
        if (!z) {
            this.m_DlgFb.getWindow().setGravity(80);
        }
    }

    /* access modifiers changed from: private */
    public void closeFb() {
        AlertDialog alertDialog = this.m_DlgFb;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.m_DlgFb = null;
        }
    }

    public void openEmail() {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_feedback_email, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_ButEmailSend = (TintButton) inflate.findViewById(R.id.but_send);
        this.m_ButEmailCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        AlertDialog create = builder.create();
        this.m_DlgEmail = create;
        create.setOnCancelListener(this.m_CancelListener);
        this.m_ButEmailSend.setOnClickListener(this.m_ClickListener);
        this.m_ButEmailCancel.setOnClickListener(this.m_ClickListener);
        Utils.dlgSetTransparent(this.m_DlgEmail);
        this.m_DlgEmail.setCanceledOnTouchOutside(true);
        this.m_DlgEmail.show();
    }

    private void closeEmail() {
        AlertDialog alertDialog = this.m_DlgEmail;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.m_DlgEmail = null;
        }
    }

    public void openRate() {
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.dialog_feedback_rate, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this.m_Context);
        builder.setView(inflate);
        builder.setCancelable(true);
        this.m_ButRate = (TintButton) inflate.findViewById(R.id.but_rate);
        this.m_ButRateCancel = (TintButton) inflate.findViewById(R.id.but_cancel);
        AlertDialog create = builder.create();
        this.m_DlgRate = create;
        create.setOnCancelListener(this.m_CancelListener);
        this.m_ButRate.setOnClickListener(this.m_ClickListener);
        this.m_ButRateCancel.setOnClickListener(this.m_ClickListener);
        Utils.dlgSetTransparent(this.m_DlgRate);
        this.m_DlgRate.setCanceledOnTouchOutside(true);
        this.m_DlgRate.show();
    }

    private void closeRate() {
        AlertDialog alertDialog = this.m_DlgRate;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.m_DlgRate = null;
        }
    }
}
