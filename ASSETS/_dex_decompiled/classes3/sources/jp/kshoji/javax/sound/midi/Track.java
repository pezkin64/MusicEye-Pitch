package jp.kshoji.javax.sound.midi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Track {
    static final byte[] END_OF_TRACK = {-1, 47, 0};
    static final Comparator<MidiEvent> midiEventComparator = new Comparator<MidiEvent>() {
        public int compare(MidiEvent midiEvent, MidiEvent midiEvent2) {
            if (midiEvent.getTick() == 0 && midiEvent2.getTick() == 0) {
                byte[] message = midiEvent.getMessage().getMessage();
                byte[] message2 = midiEvent2.getMessage().getMessage();
                byte b = message[0] & 240;
                byte b2 = message2[0] & 240;
                if (b == 144) {
                    return 1;
                }
                if (b2 == 144) {
                    return -1;
                }
            }
            int tick = (int) (midiEvent.getTick() - midiEvent2.getTick());
            if (tick != 0) {
                return tick * 256;
            }
            byte[] message3 = midiEvent.getMessage().getMessage();
            byte[] message4 = midiEvent2.getMessage().getMessage();
            if (message3 == null || message3.length < 1) {
                message3 = new byte[]{0};
            }
            if (message4 == null || message4.length < 1) {
                message4 = new byte[]{0};
            }
            byte b3 = message3[0];
            byte b4 = b3 & 240;
            byte b5 = message4[0];
            return -(((b3 & 144) == 128 ? b4 | 16 : b3 & 224) - ((b5 & 144) == 128 ? (b5 & 240) | 16 : b5 & 224));
        }
    };
    final List<MidiEvent> events = new ArrayList();

    public static class TrackUtils {
        public static Track mergeSequenceToTrack(Sequencer sequencer, Map<Track, Set<Integer>> map) throws InvalidMidiDataException {
            Track[] trackArr;
            boolean z;
            Sequence sequence = sequencer.getSequence();
            Track track = new Track();
            if (sequence == null) {
                trackArr = new Track[0];
            } else {
                trackArr = sequence.getTracks();
            }
            int i = 0;
            while (true) {
                if (i >= trackArr.length) {
                    z = false;
                    break;
                } else if (sequencer.getTrackSolo(i)) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            for (int i2 = 0; i2 < trackArr.length; i2++) {
                if (!sequencer.getTrackMute(i2) && ((!z || sequencer.getTrackSolo(i2)) && (!sequencer.isRecording() || map.get(trackArr[i2]) == null || map.get(trackArr[i2]).size() <= 0))) {
                    track.events.addAll(trackArr[i2].events);
                }
            }
            sortEvents(track);
            return track;
        }

        public static void sortEvents(Track track) {
            synchronized (track.events) {
                ArrayList arrayList = new ArrayList();
                for (MidiEvent next : track.events) {
                    if (!Arrays.equals(Track.END_OF_TRACK, next.getMessage().getMessage())) {
                        arrayList.add(next);
                    }
                }
                track.events.clear();
                track.events.addAll(arrayList);
                Collections.sort(track.events, Track.midiEventComparator);
                if (track.events.size() == 0) {
                    track.events.add(new MidiEvent(new MetaMessage(Track.END_OF_TRACK), 0));
                } else {
                    track.events.add(new MidiEvent(new MetaMessage(Track.END_OF_TRACK), track.events.get(track.events.size() - 1).getTick() + 1));
                }
            }
        }
    }

    public boolean add(MidiEvent midiEvent) {
        boolean add;
        synchronized (this.events) {
            add = this.events.add(midiEvent);
        }
        return add;
    }

    public MidiEvent get(int i) throws ArrayIndexOutOfBoundsException {
        MidiEvent midiEvent;
        synchronized (this.events) {
            midiEvent = this.events.get(i);
        }
        return midiEvent;
    }

    public boolean remove(MidiEvent midiEvent) {
        boolean remove;
        synchronized (this.events) {
            remove = this.events.remove(midiEvent);
        }
        return remove;
    }

    public int size() {
        int size;
        synchronized (this.events) {
            size = this.events.size();
        }
        return size;
    }

    public long ticks() {
        TrackUtils.sortEvents(this);
        synchronized (this.events) {
            if (this.events.size() == 0) {
                return 0;
            }
            List<MidiEvent> list = this.events;
            long tick = list.get(list.size() - 1).getTick();
            return tick;
        }
    }
}
