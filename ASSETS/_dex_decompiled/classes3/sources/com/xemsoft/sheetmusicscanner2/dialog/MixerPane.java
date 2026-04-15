package com.xemsoft.sheetmusicscanner2.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.persist.MetronomeSettings;
import com.xemsoft.sheetmusicscanner2.persist.MixerSettings;
import com.xemsoft.sheetmusicscanner2.persist.TrackSettings;
import com.xemsoft.sheetmusicscanner2.persist.VoiceSettings;
import com.xemsoft.sheetmusicscanner2.player.sound.Instrument;
import com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.widget.Slider;
import com.xemsoft.sheetmusicscanner2.widget.TintButton;
import com.xemsoft.sheetmusicscanner2.widget.TintImageButton;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;

public class MixerPane extends RelativeLayout {
    private static final String LOGTAG = "com.xemsoft.sheetmusicscanner2.dialog.MixerPane";
    /* access modifiers changed from: private */
    public static int speakerOffImg = 2131165488;
    /* access modifiers changed from: private */
    public static int speakerOnImg = 2131165487;
    /* access modifiers changed from: private */
    public TrackAdapter m_Adapter;
    private Button m_ButDone;
    /* access modifiers changed from: private */
    public Context m_Context;
    /* access modifiers changed from: private */
    public InstrumentsUtility m_IUtil;
    private View m_Layout;
    private ListView m_ListTracks;
    /* access modifiers changed from: private */
    public MixerPaneListener m_Listener = null;

    public interface MixerPaneListener {
        void onDone();

        void onSelectInstrument(int i, TrackSettings trackSettings);

        void onSetMetronomeVolume(float f);

        void onSetTrackVolume1(int i, float f);

        void onSetTrackVolume2(int i, float f);
    }

    public class TrackAdapter extends BaseAdapter {
        private int m_Count;
        private final List<Instrument> m_InstrumentList;
        /* access modifiers changed from: private */
        public MetronomeSettings m_Metronome;
        private MixerSettings m_Mixer;
        private session m_Session;
        private int m_VoiceIndex;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public TrackAdapter(MixerSettings mixerSettings, MetronomeSettings metronomeSettings, int i, session session) {
            this.m_InstrumentList = MixerPane.this.m_IUtil.instruments();
            this.m_Mixer = mixerSettings;
            this.m_Metronome = metronomeSettings;
            this.m_VoiceIndex = i;
            this.m_Session = session;
            this.m_Count = mixerSettings.tracks.size() + 1;
        }

        public int getCount() {
            return this.m_Count;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            boolean z;
            int i2;
            int i3;
            final int i4 = i;
            View inflate = LayoutInflater.from(MixerPane.this.m_Context).inflate(R.layout.item_mixer_track, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.overlay);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.img_instrument);
            TintButton tintButton = (TintButton) inflate.findViewById(R.id.but_instrument_name);
            TintImageButton tintImageButton = (TintImageButton) inflate.findViewById(R.id.but_instrument);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.layout_volume_1);
            TintImageButton tintImageButton2 = (TintImageButton) inflate.findViewById(R.id.but_mute_1);
            Slider slider = (Slider) inflate.findViewById(R.id.seek_volume_1);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(R.id.layout_volume_2);
            TintImageButton tintImageButton3 = (TintImageButton) inflate.findViewById(R.id.but_mute_2);
            Slider slider2 = (Slider) inflate.findViewById(R.id.seek_volume_2);
            TintButton tintButton2 = (TintButton) inflate.findViewById(R.id.but_multi);
            LinearLayout linearLayout3 = (LinearLayout) inflate.findViewById(R.id.layout_count_in);
            LinearLayout linearLayout4 = (LinearLayout) inflate.findViewById(R.id.layout_multi);
            Switch switchR = (Switch) inflate.findViewById(R.id.switch_count_in);
            Drawable thumbDrawable = switchR.getThumbDrawable();
            View view2 = inflate;
            slider.setThumb(thumbDrawable.getConstantState().newDrawable());
            slider2.setThumb(thumbDrawable.getConstantState().newDrawable());
            Switch switchR2 = switchR;
            if (i4 < this.m_Count - 1) {
                int i5 = this.m_VoiceIndex;
                boolean z2 = i5 == -1 || i5 == i4;
                if (z2) {
                    z = z2;
                    i2 = 0;
                } else {
                    z = z2;
                    i2 = MixerPane.this.m_Context.getColor(R.color.app_overlay);
                }
                findViewById.setBackgroundColor(i2);
                findViewById.setClickable(!z);
                final TrackSettings trackSettings = this.m_Mixer.tracks.get(i4);
                final VoiceSettings voiceSettings = trackSettings.voices.get(0);
                LinearLayout linearLayout5 = linearLayout3;
                TintButton tintButton3 = tintButton2;
                VoiceSettings voiceSettings2 = trackSettings.voices.get(1);
                Instrument instrument = this.m_InstrumentList.get(MixerPane.this.m_IUtil.indexWithInstrumentProgram(trackSettings.instrumentProgram));
                tintButton.setText(instrument.getName());
                imageView.setImageResource(InstrumentsUtility.getInstance(MixerPane.this.m_Context).iconWithInstrumentGroup(instrument.getGroup()));
                tintButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MixerPane.this.m_Listener != null) {
                            MixerPane.this.m_Listener.onSelectInstrument(i4, trackSettings);
                        }
                    }
                });
                tintImageButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MixerPane.this.m_Listener != null) {
                            MixerPane.this.m_Listener.onSelectInstrument(i4, trackSettings);
                        }
                    }
                });
                linearLayout.setVisibility(0);
                linearLayout2.setVisibility(trackSettings.isSwitchedToMultiVoice ? 0 : 8);
                tintImageButton2.setImageResource(trackSettings.voices.get(0).isMuted ? MixerPane.speakerOffImg : MixerPane.speakerOnImg);
                tintImageButton2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        trackSettings.voices.get(0).isMuted = !trackSettings.voices.get(0).isMuted;
                        MixerPane.this.m_Adapter.notifyDataSetChanged();
                    }
                });
                tintImageButton3.setImageResource(trackSettings.voices.get(1).isMuted ? MixerPane.speakerOffImg : MixerPane.speakerOnImg);
                tintImageButton3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        trackSettings.voices.get(1).isMuted = true ^ trackSettings.voices.get(1).isMuted;
                        MixerPane.this.m_Adapter.notifyDataSetChanged();
                    }
                });
                slider.setMax(TarArchiveEntry.MILLIS_PER_SECOND);
                slider.setProgress((int) (voiceSettings.volume * 1000.0f));
                slider.setSliderListener(new Slider.SliderListener() {
                    public void onStopTracking(Slider slider, int i) {
                    }

                    public void onPositionChanged(Slider slider, int i) {
                        voiceSettings.volume = ((float) i) / 1000.0f;
                        MixerPane.this.m_Listener.onSetTrackVolume1(i4, voiceSettings.volume);
                    }
                });
                slider2.setMax(TarArchiveEntry.MILLIS_PER_SECOND);
                final VoiceSettings voiceSettings3 = voiceSettings2;
                slider2.setProgress((int) (voiceSettings3.volume * 1000.0f));
                slider2.setSliderListener(new Slider.SliderListener() {
                    public void onStopTracking(Slider slider, int i) {
                    }

                    public void onPositionChanged(Slider slider, int i) {
                        voiceSettings3.volume = ((float) i) / 1000.0f;
                        MixerPane.this.m_Listener.onSetTrackVolume2(i4, voiceSettings3.volume);
                    }
                });
                if (JniSource.sessionGetVoiceSubindexSplitCount(this.m_Session, i4) > 1) {
                    TintButton tintButton4 = tintButton3;
                    tintButton4.setVisibility(0);
                    tintButton4.setText(MixerPane.this.m_Context.getString(trackSettings.isSwitchedToMultiVoice ? R.string._4yF_Ks_sa4_normalTitle : R.string.d7O_VZ_Ipt_normalTitle));
                    tintButton4.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            TrackSettings trackSettings = trackSettings;
                            trackSettings.isSwitchedToMultiVoice = !trackSettings.isSwitchedToMultiVoice;
                            MixerPane.this.m_Adapter.notifyDataSetChanged();
                        }
                    });
                    i3 = 8;
                } else {
                    i3 = 8;
                    tintButton3.setVisibility(8);
                }
                linearLayout5.setVisibility(i3);
                return view2;
            }
            tintButton.setText(MixerPane.this.m_Context.getString(R.string.vef_bb_x7T_text));
            tintButton.setDrawablesTint(MixerPane.this.m_Context.getColor(R.color.app_black));
            tintImageButton.setVisibility(4);
            imageView.setImageResource(R.drawable.metronome);
            linearLayout.setVisibility(0);
            linearLayout2.setVisibility(8);
            tintImageButton2.setImageResource(this.m_Metronome.isEnabled ? MixerPane.speakerOnImg : MixerPane.speakerOffImg);
            tintImageButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TrackAdapter.this.m_Metronome.isEnabled = !TrackAdapter.this.m_Metronome.isEnabled;
                    MixerPane.this.m_Adapter.notifyDataSetChanged();
                }
            });
            slider.setMax(TarArchiveEntry.MILLIS_PER_SECOND);
            slider.setProgress((int) (this.m_Metronome.volume * 1000.0f));
            slider.setSliderListener(new Slider.SliderListener() {
                public void onStopTracking(Slider slider, int i) {
                }

                public void onPositionChanged(Slider slider, int i) {
                    TrackAdapter.this.m_Metronome.volume = ((float) i) / 1000.0f;
                    MixerPane.this.m_Listener.onSetMetronomeVolume(TrackAdapter.this.m_Metronome.volume);
                }
            });
            linearLayout4.setVisibility(8);
            linearLayout3.setVisibility(0);
            Switch switchR3 = switchR2;
            switchR3.setChecked(this.m_Metronome.enableCountIn);
            switchR3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TrackAdapter.this.m_Metronome.enableCountIn = !TrackAdapter.this.m_Metronome.enableCountIn;
                }
            });
            return view2;
        }
    }

    public MixerPane(Context context) {
        super(context);
        init(context);
    }

    public MixerPane(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public MixerPane(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.m_Context = context;
        this.m_IUtil = InstrumentsUtility.getInstance(context);
        View inflate = LayoutInflater.from(this.m_Context).inflate(R.layout.pane_mixer, this);
        this.m_Layout = inflate;
        this.m_ButDone = (Button) inflate.findViewById(R.id.but_done);
        this.m_ListTracks = (ListView) this.m_Layout.findViewById(R.id.list_tracks);
        this.m_ButDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MixerPane.this.m_Listener != null) {
                    MixerPane.this.m_Listener.onDone();
                }
            }
        });
    }

    public void setListener(MixerPaneListener mixerPaneListener) {
        this.m_Listener = mixerPaneListener;
    }

    public void open(MixerSettings mixerSettings, MetronomeSettings metronomeSettings, int i, session session) {
        TrackAdapter trackAdapter = new TrackAdapter(mixerSettings, metronomeSettings, i, session);
        this.m_Adapter = trackAdapter;
        this.m_ListTracks.setAdapter(trackAdapter);
    }

    public void refresh() {
        this.m_Adapter.notifyDataSetChanged();
    }
}
