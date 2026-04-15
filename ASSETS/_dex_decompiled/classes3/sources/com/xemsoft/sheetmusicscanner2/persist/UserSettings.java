package com.xemsoft.sheetmusicscanner2.persist;

import android.content.Context;
import com.xemsoft.sheetmusicscanner2.player.sound.InstrumentsUtility;
import com.xemsoft.sheetmusicscanner2.util.Utils;

public class UserSettings extends Prefs {
    private static final int BPM_DEFAULT = 80;
    private static final String BPM_KEY = "Bpm";
    private static final String DISTORTION_WARNING_CANCEL_COUNT_KEY = "DistortionwarningCancelCount";
    private static final String DISTORTION_WARNING_SHOW_ME_COUNT_KEY = "DistortionwarningShowMeCount";
    private static final boolean HAS_PROVIDED_FEEDBACK_DEFAULT = false;
    private static final String HAS_PROVIDED_FEEDBACK_KEY = "HasProvidedFeedback";
    public static final int HIGHLIGHT_CONTRAST = 1;
    public static final int HIGHLIGHT_DEFAULT = 0;
    public static final int HIGHLIGHT_MODE_DEFAULT = 0;
    private static final String HIGHLIGHT_MODE_KEY = "Highlight";
    public static final int IMPORT_BROWSE = 3;
    public static final int IMPORT_CAMERA = 1;
    private static final String IMPORT_KEY = "Import";
    public static final int IMPORT_PHOTOS = 2;
    private static final long INSTALL_DATE_DEFAULT = 0;
    private static final String INSTALL_DATE_KEY = "InstallDate";
    private static final int INSTRUMENT_DEFAULT = 0;
    private static final String INSTRUMENT_KEY = "Instrument";
    private static final int INSTRUMENT_PITCH_DEFAULT = 0;
    private static final String INSTRUMENT_PITCH_KEY = "InstrumentPitch";
    private static final boolean MULTIPLE_VOICES_DEFAULT = true;
    private static final String MULTIPLE_VOICES_KEY = "MultipleVoices";
    private static final int PITCH_DEFAULT = 440;
    private static final String PITCH_KEY = "Pitch";
    private static final long RATING_DATE_DEFAULT = 0;
    private static final String RATING_DATE_KEY = "RatingDate";
    private static final boolean RATING_DIALOG_DISPLAYED_DEFAULT = false;
    private static final String RATING_DIALOG_DISPLAYED_KEY = "RatingDialogDisplayed";
    private static final int RATING_MIN_DAYS_TILL_DIALOG = 1;
    private static final int RATING_SUCCESS_COUNT_FOR_DIALOG = 2;
    private static final String SUBSCRIPTION_ACTIVE = "SubscriptionActive";
    private static final int SUCCESS_COUNT_DEFAULT = 0;
    private static final String SUCCESS_COUNT_KEY = "SuccessCount";
    private static final boolean SURVEY_DIALOG_DISPLAYED_DEFAULT = false;
    private static final String SURVEY_DIALOG_DISPLAYED_KEY = "SurveyDialogDisplayed";
    private static final int SURVEY_MIN_HOURS_SINCE_RATING_TILL_DIALOG = 1;
    private static final String TRIAL_AVAILABLE = "TrialAvailable";
    private static final int UNFINISHED_COUNT_DEFAULT = 0;
    private static final String UNFINISHED_COUNT_KEY = "UnfinishedCount";
    private static final String VERSION_DEFAULT = "";
    private static final String VERSION_KEY = "Version";
    private static UserSettings m_Instance;
    private Context m_Context;

    public boolean surveyShouldDisplayDialog() {
        return false;
    }

    public static UserSettings getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new UserSettings(context);
        }
        return m_Instance;
    }

    public UserSettings(Context context) {
        super(context, "MusicScannerSettings");
        this.m_Context = context;
    }

    public void setDefaults() {
        setPitch(PITCH_DEFAULT);
        setBpm(80);
        setInstrument(0);
        setInstrumentPitch(0);
        setUnfinishedAnalysisCount(0);
        setRatingDate(0);
        surveySetDialogDisplayed(false);
        setMultipleVoicesOn(true);
        setHighlightMode(0);
        if (getInstallDate() == 0) {
            setInstallDate(System.currentTimeMillis());
            ratingSetDialogDisplayed(false);
            setSuccessCount(0);
            setHasProvidedFeedback(false);
        }
        String appVersion = Utils.getAppVersion(this.m_Context);
        if (!appVersion.equals(getVersion())) {
            setHasProvidedFeedback(false);
            setVersion(appVersion);
        }
        setDistortionWarningCancelCount(0);
        setDistortionWarningShowMeCount(0);
    }

    public String getVersion() {
        return getOptionString(VERSION_KEY, "");
    }

    public void setVersion(String str) {
        setOptionString(VERSION_KEY, str);
    }

    public int getPitch() {
        return getOptionInt(PITCH_KEY, PITCH_DEFAULT);
    }

    public void setPitch(int i) {
        setOptionInt(PITCH_KEY, i);
    }

    public void resetPitch() {
        setPitch(PITCH_DEFAULT);
    }

    public int getBpm() {
        return getOptionInt(BPM_KEY, 80);
    }

    public void setBpm(int i) {
        if (i != 0) {
            setOptionInt(BPM_KEY, i);
        }
    }

    public int getInstrument() {
        int optionInt = getOptionInt(INSTRUMENT_KEY, 0);
        if (InstrumentsUtility.getInstance(this.m_Context).findInstrumentByProgram(optionInt) == null) {
            return 0;
        }
        return optionInt;
    }

    public void setInstrument(int i) {
        setOptionInt(INSTRUMENT_KEY, i);
    }

    public int getInstrumentPitch() {
        return getOptionInt(INSTRUMENT_PITCH_KEY, 0);
    }

    public void setInstrumentPitch(int i) {
        setOptionInt(INSTRUMENT_PITCH_KEY, i);
    }

    public boolean getMultipleVoicesOn() {
        return getOptionBool(MULTIPLE_VOICES_KEY, true);
    }

    public void setMultipleVoicesOn(boolean z) {
        setOptionBool(MULTIPLE_VOICES_KEY, z);
    }

    public int getHighlightMode() {
        return getOptionInt(HIGHLIGHT_MODE_KEY, 0);
    }

    public void setHighlightMode(int i) {
        setOptionInt(HIGHLIGHT_MODE_KEY, i);
    }

    public long getInstallDate() {
        return getOptionLong(INSTALL_DATE_KEY, 0);
    }

    public void setInstallDate(long j) {
        setOptionLong(INSTALL_DATE_KEY, j);
    }

    public int getSuccesCount() {
        return getOptionInt(SUCCESS_COUNT_KEY, 0);
    }

    public void setSuccessCount(int i) {
        setOptionInt(SUCCESS_COUNT_KEY, i);
    }

    public int getUnfinishedAnalysisCount() {
        return getOptionInt(UNFINISHED_COUNT_KEY, 0);
    }

    public void setUnfinishedAnalysisCount(int i) {
        setOptionInt(UNFINISHED_COUNT_KEY, i);
    }

    public long getRatingDate() {
        return getOptionLong(RATING_DATE_KEY, 0);
    }

    public void setRatingDate(long j) {
        setOptionLong(RATING_DATE_KEY, j);
    }

    public boolean getRatingDialogDisplayed() {
        return getOptionBool(RATING_DIALOG_DISPLAYED_KEY, false);
    }

    public boolean ratingShouldDisplayDialog() {
        if (getRatingDialogDisplayed() || getSuccesCount() < 2) {
            return false;
        }
        return 86400000 <= System.currentTimeMillis() - getInstallDate();
    }

    public void ratingIncreaseSuccesCount() {
        setSuccessCount(getSuccesCount() + 1);
    }

    public boolean ratingGetDialogDisplayed() {
        return getOptionBool(RATING_DIALOG_DISPLAYED_KEY, false);
    }

    public void ratingSetDialogDisplayed() {
        ratingSetDialogDisplayed(true);
    }

    public void ratingSetDialogDisplayed(boolean z) {
        setOptionBool(RATING_DIALOG_DISPLAYED_KEY, z);
        setRatingDate(System.currentTimeMillis());
    }

    public boolean getHasProvidedFeedback() {
        return getOptionBool(HAS_PROVIDED_FEEDBACK_KEY, false);
    }

    public void setHasProvidedFeedback() {
        setHasProvidedFeedback(true);
    }

    public void setHasProvidedFeedback(boolean z) {
        setOptionBool(HAS_PROVIDED_FEEDBACK_KEY, z);
    }

    public boolean surveyGetDialogDisplayed() {
        return getOptionBool(SURVEY_DIALOG_DISPLAYED_KEY, false);
    }

    public void surveySetDialogDisplayed() {
        surveySetDialogDisplayed(true);
    }

    public void surveySetDialogDisplayed(boolean z) {
        setOptionBool(SURVEY_DIALOG_DISPLAYED_KEY, z);
    }

    public void setImport(int i) {
        setOptionInt(IMPORT_KEY, i);
    }

    public int getImport() {
        return getOptionInt(IMPORT_KEY, 2);
    }

    public void setDistortionWarningCancelCount(int i) {
        setOptionInt(DISTORTION_WARNING_CANCEL_COUNT_KEY, i);
    }

    public int getDistortionWarningCancelCount() {
        return getOptionInt(DISTORTION_WARNING_CANCEL_COUNT_KEY, 0);
    }

    public void incrementDistortionWarningCancelCount() {
        setDistortionWarningCancelCount(getDistortionWarningCancelCount() + 1);
    }

    public void setDistortionWarningShowMeCount(int i) {
        setOptionInt(DISTORTION_WARNING_SHOW_ME_COUNT_KEY, i);
    }

    public int getDistortionWarningShowMeCount() {
        return getOptionInt(DISTORTION_WARNING_SHOW_ME_COUNT_KEY, 0);
    }

    public void incrementDistortionWarningShowMeCount() {
        setDistortionWarningShowMeCount(getDistortionWarningShowMeCount() + 1);
    }

    public void setTrialAvailable(boolean z) {
        setOptionBool(TRIAL_AVAILABLE, z);
    }

    public boolean getTrialAvailable() {
        return getOptionBool(TRIAL_AVAILABLE, true);
    }

    public void setSubscriptionActive(boolean z) {
        setOptionBool(SUBSCRIPTION_ACTIVE, z);
    }

    public boolean getSubscriptionActive() {
        return getOptionBool(SUBSCRIPTION_ACTIVE, false);
    }
}
