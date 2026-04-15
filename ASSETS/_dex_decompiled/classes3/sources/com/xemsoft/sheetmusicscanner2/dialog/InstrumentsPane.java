package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.google.firebase.encoders.json.BuildConfig;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.player.sound.Instrument;
import com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility;
import com.xemsoft.sheetmusicscanner2.widget.Slider;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;
import java.util.List;

public class InstrumentsPane extends RelativeLayout {
    private static final String LOGTAG = "com.xemsoft.sheetmusicscanner2.dialog.InstrumentsPane";
    private static final int TRANS_MAX = 52;
    private static final int TRANS_OFFSET = -26;
    /* access modifiers changed from: private */
    public ItemAdapter m_Adapter;
    private TintImageButton m_ButBack;
    private Context m_Context;
    /* access modifiers changed from: private */
    public InstrumentsUtility m_IUtil;
    /* access modifiers changed from: private */
    public int m_InstrumentPitch = 0;
    private View m_Layout;
    private ListView m_ListInstruments;
    /* access modifiers changed from: private */
    public InstrumentsPaneListener m_Listener = null;
    private Slider m_SeekTranspose;
    private Slider.SliderListener m_SliderListener = new Slider.SliderListener() {
        public void onPositionChanged(Slider slider, int i) {
            TextView access$800 = InstrumentsPane.this.m_TextTranspose;
            access$800.setText((i + InstrumentsPane.TRANS_OFFSET) + BuildConfig.FLAVOR);
            InstrumentsPane.this.setUseInstrumentPitch(false);
            InstrumentsPane.this.m_SwitchActualPitch.setChecked(false);
        }

        public void onStopTracking(Slider slider, int i) {
            InstrumentsPane.this.m_Track.instrumentPitch = i + InstrumentsPane.TRANS_OFFSET;
        }
    };
    /* access modifiers changed from: private */
    public Switch m_SwitchActualPitch;
    /* access modifiers changed from: private */
    public TextView m_TextTranspose;
    /* access modifiers changed from: private */
    public TrackSettings m_Track;
    /* access modifiers changed from: private */
    public int m_TrackId = 0;
    private boolean m_UseInstrumentPitch = false;

    public interface InstrumentsPaneListener {
        void onBack();

        void onSetTrackProgram(int i, int i2);
    }

    public class ItemAdapter extends ArrayAdapter<Instrument> {
        private Context m_Context;
        private int m_Selection = -1;

        public ItemAdapter(Context context, List<Instrument> list) {
            super(context, 0, list);
            this.m_Context = context;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.m_Context).inflate(R.layout.item_instrument, viewGroup, false);
            }
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.layout_root);
            Instrument instrument = (Instrument) getItem(i);
            ((ImageView) view.findViewById(R.id.img_instrument)).setImageResource(InstrumentsUtility.getInstance(this.m_Context).iconWithInstrumentGroup(instrument.getGroup()));
            ((TextView) view.findViewById(R.id.text_name)).setText(instrument.getName());
            relativeLayout.setBackgroundResource(i == this.m_Selection ? R.color.app_gray_lightest2 : R.color.transparent);
            return view;
        }

        public void setSelection(int i) {
            this.m_Selection = i;
        }

        public int getSelection() {
            return this.m_Selection;
        }
    }

    public InstrumentsPane(Context context) {
        super(context);
        init(context);
    }

    public InstrumentsPane(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public InstrumentsPane(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.m_Context = context;
        this.m_IUtil = InstrumentsUtility.getInstance(context);
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.pane_instruments, this);
        this.m_Layout = inflate;
        this.m_ButBack = (TintImageButton) inflate.findViewById(R.id.but_back);
        this.m_ListInstruments = (ListView) this.m_Layout.findViewById(R.id.list_instruments);
        this.m_SeekTranspose = (Slider) this.m_Layout.findViewById(R.id.seek_transpose);
        this.m_TextTranspose = (TextView) this.m_Layout.findViewById(R.id.text_transpose);
        this.m_SwitchActualPitch = (Switch) this.m_Layout.findViewById(R.id.switch_pitch);
    }

    public void setListener(InstrumentsPaneListener instrumentsPaneListener) {
        this.m_Listener = instrumentsPaneListener;
    }

    public void setUseInstrumentPitch(boolean z) {
        this.m_UseInstrumentPitch = z;
    }

    public void open(int i, TrackSettings trackSettings) {
        this.m_TrackId = i;
        this.m_Track = trackSettings;
        this.m_InstrumentPitch = trackSettings.instrumentPitch;
        List instruments = InstrumentsUtility.getInstance(this.m_Context).instruments();
        ItemAdapter itemAdapter = new ItemAdapter(this.m_Context, instruments);
        this.m_Adapter = itemAdapter;
        this.m_ListInstruments.setAdapter(itemAdapter);
        setUseInstrumentPitch(this.m_InstrumentPitch == this.m_IUtil.pitchWithInstrumentProgram(this.m_Track.instrumentProgram));
        setUI(instruments);
    }

    public void rotate() {
        ItemAdapter itemAdapter = this.m_Adapter;
        if (itemAdapter != null) {
            this.m_ListInstruments.setSelection(itemAdapter.getSelection() - 1);
        }
    }

    private void setUI(final List<Instrument> list) {
        this.m_ButBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (InstrumentsPane.this.m_Listener != null) {
                    InstrumentsPane.this.m_Listener.onBack();
                }
            }
        });
        this.m_SeekTranspose.setMax(52);
        this.m_SeekTranspose.setProgress(this.m_Track.instrumentPitch + 26);
        this.m_SeekTranspose.setSliderListener(this.m_SliderListener);
        TextView textView = this.m_TextTranspose;
        textView.setText(this.m_Track.instrumentPitch + BuildConfig.FLAVOR);
        this.m_SwitchActualPitch.setChecked(this.m_UseInstrumentPitch);
        this.m_SwitchActualPitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    InstrumentsPane.this.setInstrumentPitch();
                }
                InstrumentsPane.this.setUseInstrumentPitch(z);
            }
        });
        int indexWithInstrumentProgram = this.m_IUtil.indexWithInstrumentProgram(this.m_Track.instrumentProgram);
        this.m_Adapter.setSelection(indexWithInstrumentProgram);
        this.m_ListInstruments.setSelection(indexWithInstrumentProgram - 1);
        this.m_ListInstruments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                InstrumentsPane.this.m_Adapter.setSelection(i);
                InstrumentsPane.this.m_Adapter.notifyDataSetChanged();
                if (InstrumentsPane.this.m_Listener != null) {
                    Instrument instrument = (Instrument) list.get(i);
                    InstrumentsPane instrumentsPane = InstrumentsPane.this;
                    int unused = instrumentsPane.m_InstrumentPitch = instrumentsPane.m_IUtil.pitchWithInstrumentProgram(instrument.getProgram());
                    InstrumentsPane.this.m_Track.instrumentProgram = instrument.getProgram();
                    if (InstrumentsPane.this.m_SwitchActualPitch.isChecked()) {
                        InstrumentsPane.this.setInstrumentPitch();
                    }
                    InstrumentsPane.this.m_Listener.onSetTrackProgram(InstrumentsPane.this.m_TrackId, InstrumentsPane.this.m_Track.instrumentProgram);
                }
            }
        });
        this.m_SeekTranspose.setThumb(this.m_SwitchActualPitch.getThumbDrawable().getConstantState().newDrawable());
    }

    /* access modifiers changed from: private */
    public void setInstrumentPitch() {
        this.m_SeekTranspose.setProgress(this.m_InstrumentPitch + 26);
        TextView textView = this.m_TextTranspose;
        textView.setText(this.m_InstrumentPitch + BuildConfig.FLAVOR);
        this.m_Track.instrumentPitch = this.m_InstrumentPitch;
    }
}
