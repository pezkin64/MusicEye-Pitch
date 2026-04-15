package com.xemsoft.sheetmusicscanner2.layout;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xemsoft.sheetmusicscanner2.Constants;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.activity.ScannerActivity;
import com.xemsoft.sheetmusicscanner2.dialog.AlertBox;
import com.xemsoft.sheetmusicscanner2.dialog.ChoicePopup;
import com.xemsoft.sheetmusicscanner2.dialog.ImportDialog;
import com.xemsoft.sheetmusicscanner2.dialog.RenameDialog;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.persist.UserSettings;
import com.xemsoft.sheetmusicscanner2.util.ActivityHelper;
import com.xemsoft.sheetmusicscanner2.util.FlurryUtils;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;
import java.util.List;

public class SongListLayout extends RelativeLayout {
    private static final String LOGTAG = "SongListLayout.java";
    /* access modifiers changed from: private */
    public ScannerActivity m_Activity = null;
    /* access modifiers changed from: private */
    public ItemAdapter m_Adapter;
    private AlertBox m_AlertBox;
    /* access modifiers changed from: private */
    public TintImageButton m_ButAbout;
    /* access modifiers changed from: private */
    public TintImageButton m_ButBack;
    /* access modifiers changed from: private */
    public TintImageButton m_ButFeedback;
    /* access modifiers changed from: private */
    public TintImageButton m_ButHelp;
    /* access modifiers changed from: private */
    public TintButton m_ButScan;
    /* access modifiers changed from: private */
    public TintButton m_ButScanThin;
    /* access modifiers changed from: private */
    public TintImageButton m_ButShare;
    /* access modifiers changed from: private */
    public ChoicePopup m_Choice;
    private View.OnClickListener m_ClickListener = new View.OnClickListener() {
        /* JADX WARNING: type inference failed for: r2v6, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r2v8, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        /* JADX WARNING: type inference failed for: r2v10, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
        public void onClick(View view) {
            if (view == SongListLayout.this.m_ButBack) {
                if (SongListLayout.this.m_Listener != null) {
                    SongListLayout.this.m_Listener.onBack();
                }
            } else if (view == SongListLayout.this.m_ButScan || view == SongListLayout.this.m_ButScanThin) {
                if (SongListLayout.this.m_Listener != null) {
                    SongListLayout.this.m_Listener.onImport();
                }
            } else if (view == SongListLayout.this.m_ButFeedback) {
                if (SongListLayout.this.m_Listener != null) {
                    SongListLayout.this.m_Listener.onFeedback();
                }
            } else if (view == SongListLayout.this.m_ButShare) {
                ActivityHelper.share(SongListLayout.this.m_Activity);
            } else if (view == SongListLayout.this.m_ButHelp) {
                ActivityHelper.openHelp(SongListLayout.this.m_Activity);
            } else if (view == SongListLayout.this.m_ButAbout) {
                ActivityHelper.openAbout(SongListLayout.this.m_Activity);
            }
        }
    };
    private Context m_Context;
    private EditText m_EditSearch;
    private ImportDialog m_ImportDlg;
    /* access modifiers changed from: private */
    public boolean m_IsSongSelected = false;
    /* access modifiers changed from: private */
    public List<CdSession> m_List;
    /* access modifiers changed from: private */
    public ListView m_ListSessions;
    /* access modifiers changed from: private */
    public SongListLayoutListener m_Listener = null;
    /* access modifiers changed from: private */
    public UserSettings m_Prefs;
    private RenameDialog m_RenameDialog;
    /* access modifiers changed from: private */
    public SessionUtility m_SUtil;
    private TextView m_TextEmpty;
    private View[] m_ViewList = new View[7];
    /* access modifiers changed from: private */
    public long m_VisibleSessionId = -1;

    public interface SongListLayoutListener {
        void onBack();

        void onFeedback();

        void onImport();

        void onPlaySong(Intent intent);
    }

    public class ItemAdapter extends ArrayAdapter<CdSession> {
        private Context m_Context;
        private int m_Selection = -1;

        public ItemAdapter(Context context, List<CdSession> list) {
            super(context, 0, list);
            this.m_Context = context;
        }

        public void setSelection(int i) {
            this.m_Selection = i;
        }

        public int getSelection() {
            return this.m_Selection;
        }

        public void setSelectionById(long j) {
            this.m_Selection = getPosById(j);
        }

        public int getPosById(long j) {
            if (j == -1) {
                return -1;
            }
            for (int i = 0; i < SongListLayout.this.m_List.size(); i++) {
                if (((CdSession) SongListLayout.this.m_List.get(i)).getId() == j) {
                    return i;
                }
            }
            return -1;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final CdSession cdSession = (CdSession) getItem(i);
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.item_session, viewGroup, false);
            }
            TintImageButton tintImageButton = (TintImageButton) view.findViewById(R.id.but_more);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_root);
            ((TextView) view.findViewById(R.id.text_name)).setText(cdSession.getDisplayName());
            relativeLayout.setBackgroundResource(i == this.m_Selection ? R.color.app_gray_lightest2 : R.color.transparent);
            tintImageButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ItemAdapter.this.showChoice(view, cdSession, i);
                }
            });
            return view;
        }

        /* access modifiers changed from: private */
        public void showChoice(View view, final CdSession cdSession, final int i) {
            SongListLayout.this.m_Choice.close();
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            boolean z = true;
            int i2 = iArr[1];
            SongListLayout.this.m_ListSessions.getLocationOnScreen(iArr);
            if (((float) ((iArr[1] + SongListLayout.this.m_ListSessions.getHeight()) - i2)) >= ((float) ((int) (((float) view.getHeight()) * 2.5f)))) {
                z = false;
            }
            SongListLayout.this.m_Choice.open(view, z, new ChoicePopup.ChoicePopupListener() {
                public void onDelete() {
                    SongListLayout.this.openDeleteDialog(cdSession, i);
                }

                public void onRename() {
                    SongListLayout.this.openRenameDialog(cdSession);
                }
            });
        }
    }

    public SongListLayout(Context context) {
        super(context);
        init(context);
    }

    public SongListLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public SongListLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: package-private */
    public void init(Context context) {
        View inflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.layout_song_list, this, true);
        this.m_Context = context;
        this.m_Prefs = UserSettings.getInstance(context);
        this.m_SUtil = SessionUtility.getInstance(this.m_Context);
        this.m_EditSearch = (EditText) inflate.findViewById(R.id.edit_search);
        this.m_ListSessions = (ListView) inflate.findViewById(R.id.list_songs);
        this.m_TextEmpty = (TextView) inflate.findViewById(16908292);
        Object[] objArr = this.m_ViewList;
        TintImageButton tintImageButton = (TintImageButton) inflate.findViewById(R.id.but_back);
        this.m_ButBack = tintImageButton;
        int i = 0;
        objArr[0] = tintImageButton;
        Object[] objArr2 = this.m_ViewList;
        TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_scan_camera);
        this.m_ButScan = tintButton;
        objArr2[1] = tintButton;
        Object[] objArr3 = this.m_ViewList;
        TintButton tintButton2 = (TintButton) inflate.findViewById(R.id.but_scan_thin);
        this.m_ButScanThin = tintButton2;
        objArr3[2] = tintButton2;
        Object[] objArr4 = this.m_ViewList;
        TintImageButton tintImageButton2 = (TintImageButton) inflate.findViewById(R.id.but_feedback);
        this.m_ButFeedback = tintImageButton2;
        objArr4[3] = tintImageButton2;
        Object[] objArr5 = this.m_ViewList;
        TintImageButton tintImageButton3 = (TintImageButton) inflate.findViewById(R.id.but_settings);
        this.m_ButShare = tintImageButton3;
        objArr5[4] = tintImageButton3;
        Object[] objArr6 = this.m_ViewList;
        TintImageButton tintImageButton4 = (TintImageButton) inflate.findViewById(R.id.but_help);
        this.m_ButHelp = tintImageButton4;
        objArr6[5] = tintImageButton4;
        Object[] objArr7 = this.m_ViewList;
        TintImageButton tintImageButton5 = (TintImageButton) inflate.findViewById(R.id.but_about);
        this.m_ButAbout = tintImageButton5;
        objArr7[6] = tintImageButton5;
        while (true) {
            View[] viewArr = this.m_ViewList;
            if (i < viewArr.length) {
                viewArr[i].setOnClickListener(this.m_ClickListener);
                i++;
            } else {
                this.m_AlertBox = new AlertBox(this.m_Context);
                this.m_ImportDlg = new ImportDialog(this.m_Context);
                this.m_RenameDialog = new RenameDialog(this.m_Context);
                this.m_Choice = new ChoicePopup(this.m_Context);
                loadList();
                this.m_ListSessions.setEmptyView(this.m_TextEmpty);
                this.m_EditSearch.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    public void afterTextChanged(Editable editable) {
                        SongListLayout.this.search(editable.toString());
                    }
                });
                this.m_ListSessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (!SongListLayout.this.m_IsSongSelected) {
                            SongListLayout.this.onListItem(i);
                            boolean unused = SongListLayout.this.m_IsSongSelected = true;
                        }
                    }
                });
                return;
            }
        }
    }

    public void setActivity(ScannerActivity scannerActivity) {
        this.m_Activity = scannerActivity;
    }

    public void setListener(SongListLayoutListener songListLayoutListener) {
        this.m_Listener = songListLayoutListener;
    }

    public void resume(boolean z) {
        if (this.m_VisibleSessionId != -1) {
            this.m_Adapter.setSelection(-1);
        }
        loadList();
        this.m_IsSongSelected = false;
        if (z) {
            checkSurveyPrompt();
        }
    }

    public void destroy() {
        closeDialogs();
    }

    public void setIntent(Intent intent) {
        openIntent(intent);
        checkSurveyPrompt();
    }

    private void openIntent(Intent intent) {
        String stringExtra;
        CdSession cdGetSession;
        if (!(!intent.hasExtra(Constants.BUNDLE_SONG_FOLDER) || (stringExtra = intent.getStringExtra(Constants.BUNDLE_SONG_FOLDER)) == null || (cdGetSession = this.m_SUtil.cdGetSession(stringExtra)) == null)) {
            this.m_VisibleSessionId = cdGetSession.getId();
        }
        loadList();
    }

    private void closeDialogs() {
        this.m_AlertBox.close();
        this.m_ImportDlg.close();
        this.m_RenameDialog.close();
        this.m_Choice.close();
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

    private void manageLayout() {
        int screenMinWidth = (int) (((float) Utils.getScreenMinWidth(this.m_Context)) / Utils.getDensity(this.m_Context));
        if (getResources().getConfiguration().orientation != 2 || ((float) screenMinWidth) >= 540.0f) {
            this.m_ButScan.setVisibility(0);
            this.m_ButScanThin.setVisibility(8);
            return;
        }
        this.m_ButScan.setVisibility(8);
        this.m_ButScanThin.setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0023, code lost:
        r0 = r5.m_Adapter.getPosById(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadList() {
        /*
            r5 = this;
            com.xemsoft.sheetmusicscanner2.persist.SessionUtility r0 = r5.m_SUtil
            java.util.List r0 = r0.cdGetAllSessions()
            r5.m_List = r0
            com.xemsoft.sheetmusicscanner2.layout.SongListLayout$ItemAdapter r0 = new com.xemsoft.sheetmusicscanner2.layout.SongListLayout$ItemAdapter
            android.content.Context r1 = r5.m_Context
            java.util.List<com.xemsoft.sheetmusicscanner2.persist.CdSession> r2 = r5.m_List
            r0.<init>(r1, r2)
            r5.m_Adapter = r0
            android.widget.ListView r1 = r5.m_ListSessions
            r1.setAdapter(r0)
            r0 = 0
            r5.m_IsSongSelected = r0
            long r0 = r5.m_VisibleSessionId
            r2 = -1
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0038
            com.xemsoft.sheetmusicscanner2.layout.SongListLayout$ItemAdapter r2 = r5.m_Adapter
            int r0 = r2.getPosById(r0)
            r1 = -1
            if (r0 == r1) goto L_0x0038
            android.widget.ListView r1 = r5.m_ListSessions
            com.xemsoft.sheetmusicscanner2.layout.SongListLayout$4 r2 = new com.xemsoft.sheetmusicscanner2.layout.SongListLayout$4
            r2.<init>(r0)
            r3 = 200(0xc8, double:9.9E-322)
            r1.postDelayed(r2, r3)
        L_0x0038:
            r5.updateList()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xemsoft.sheetmusicscanner2.layout.SongListLayout.loadList():void");
    }

    /* access modifiers changed from: private */
    public void search(String str) {
        for (int i = 0; i < this.m_List.size(); i++) {
            if (this.m_List.get(i).getDisplayName().toLowerCase().contains(str.toLowerCase())) {
                this.m_ListSessions.setSelection(i);
                return;
            }
        }
        this.m_ListSessions.setSelection(this.m_List.size() - 1);
    }

    /* access modifiers changed from: private */
    public void onListItem(int i) {
        this.m_Adapter.setSelection(i);
        this.m_Adapter.notifyDataSetChanged();
        CdSession cdSession = this.m_List.get(i);
        this.m_VisibleSessionId = cdSession.getId();
        Intent createPlayIntent = ActivityHelper.createPlayIntent(cdSession.getSessionFolderName(), 0, false, false, 0);
        SongListLayoutListener songListLayoutListener = this.m_Listener;
        if (songListLayoutListener != null) {
            songListLayoutListener.onPlaySong(createPlayIntent);
        }
    }

    /* access modifiers changed from: private */
    public void updateList() {
        this.m_EditSearch.setEnabled(this.m_List.size() != 0);
    }

    private void checkSurveyPrompt() {
        if (this.m_Prefs.surveyShouldDisplayDialog()) {
            openSurveyDialog();
        }
    }

    /* access modifiers changed from: private */
    public void openDeleteDialog(final CdSession cdSession, final int i) {
        this.m_AlertBox.close();
        AlertBox alertBox = this.m_AlertBox;
        String string = this.m_Context.getString(R.string.songDeleteAlert_title);
        alertBox.open(string, cdSession.getDisplayName() + "\n\n" + this.m_Context.getString(R.string.songDeleteAlert_message), this.m_Context.getString(R.string.songDeleteAlert_button_ok), this.m_Context.getString(R.string.songDeleteAlert_button_cancel), new AlertBox.OnAlertListener() {
            public void onCancel() {
            }

            public void onOk() {
                SongListLayout.this.m_SUtil.deleteSessionFolder(cdSession.getSessionFolderName());
                SongListLayout.this.m_SUtil.cdDeleteSession(cdSession.getSessionFolderName());
                SongListLayout.this.m_List.remove(i);
                SongListLayout.this.m_Adapter.notifyDataSetChanged();
                SongListLayout.this.updateList();
            }
        });
    }

    /* access modifiers changed from: private */
    public void openRenameDialog(final CdSession cdSession) {
        this.m_RenameDialog.close();
        this.m_RenameDialog.open(this.m_Context.getString(R.string.songRenameDialog_title), this.m_Context.getString(R.string.songRenameDialog_message), cdSession.getDisplayName(), this.m_Context.getString(R.string.songRenameDialog_button_ok), this.m_Context.getString(R.string.songRenameDialog_button_cancel), new RenameDialog.OnRenameListener() {
            public void onCancel() {
            }

            public void onOk(String str) {
                SongListLayout.this.m_SUtil.cdUpdateSessionDisplayName(cdSession.getSessionFolderName(), str);
                cdSession.setDisplayName(str);
                long unused = SongListLayout.this.m_VisibleSessionId = cdSession.getId();
                SongListLayout.this.loadList();
            }
        });
    }

    private void openSurveyDialog() {
        FlurryUtils.event("SurveyDialog_Displayed");
        this.m_AlertBox.close();
        this.m_AlertBox.open(this.m_Context.getString(R.string.surveyAlert_title), this.m_Context.getString(R.string.surveyAlert_message), this.m_Context.getString(R.string.surveyAlert_button_ok), this.m_Context.getString(R.string.surveyAlert_button_dismiss), true, true, new AlertBox.OnAlertListener() {
            public void onCancel() {
            }

            /* JADX WARNING: type inference failed for: r0v4, types: [com.xemsoft.sheetmusicscanner2.activity.ScannerActivity, android.app.Activity] */
            public void onOk() {
                FlurryUtils.event("SurveyDialog_Ok");
                SongListLayout.this.m_Prefs.surveySetDialogDisplayed();
                ActivityHelper.takeSurvey(SongListLayout.this.m_Activity);
            }
        });
    }
}
