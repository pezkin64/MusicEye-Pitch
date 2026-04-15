package jp.kshoji.javax.sound.midi;

import java.io.IOException;
import java.io.InputStream;

public interface Sequencer extends MidiDevice {
    public static final int LOOP_CONTINUOUSLY = -1;

    int[] addControllerEventListener(ControllerEventListener controllerEventListener, int[] iArr);

    boolean addMetaEventListener(MetaEventListener metaEventListener);

    int getLoopCount();

    long getLoopEndPoint();

    long getLoopStartPoint();

    SyncMode getMasterSyncMode();

    SyncMode[] getMasterSyncModes();

    long getMicrosecondLength();

    long getMicrosecondPosition();

    Sequence getSequence();

    SyncMode getSlaveSyncMode();

    SyncMode[] getSlaveSyncModes();

    float getTempoFactor();

    float getTempoInBPM();

    float getTempoInMPQ();

    long getTickLength();

    long getTickPosition();

    boolean getTrackMute(int i);

    boolean getTrackSolo(int i);

    boolean isRecording();

    boolean isRunning();

    void recordDisable(Track track);

    void recordEnable(Track track, int i);

    int[] removeControllerEventListener(ControllerEventListener controllerEventListener, int[] iArr);

    void removeMetaEventListener(MetaEventListener metaEventListener);

    void setLoopCount(int i);

    void setLoopEndPoint(long j);

    void setLoopStartPoint(long j);

    void setMasterSyncMode(SyncMode syncMode);

    void setMicrosecondPosition(long j);

    void setSequence(InputStream inputStream) throws IOException, InvalidMidiDataException;

    void setSequence(Sequence sequence) throws InvalidMidiDataException;

    void setSlaveSyncMode(SyncMode syncMode);

    void setTempoFactor(float f);

    void setTempoInBPM(float f);

    void setTempoInMPQ(float f);

    void setTickPosition(long j);

    void setTrackMute(int i, boolean z);

    void setTrackSolo(int i, boolean z);

    void start();

    void startRecording();

    void stop();

    void stopRecording();

    public static class SyncMode {
        public static final SyncMode INTERNAL_CLOCK = new SyncMode("Internal Clock");
        public static final SyncMode NO_SYNC = new SyncMode("No Sync");
        private String name;

        protected SyncMode(String str) {
            this.name = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SyncMode syncMode = (SyncMode) obj;
            String str = this.name;
            if (str == null) {
                if (syncMode.name != null) {
                    return false;
                }
            } else if (!str.equals(syncMode.name)) {
                return false;
            }
            return true;
        }

        public final int hashCode() {
            int hashCode = super.hashCode() * 31;
            String str = this.name;
            return hashCode + (str == null ? 0 : str.hashCode());
        }

        public final String toString() {
            return this.name;
        }
    }
}
