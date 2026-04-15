package cn.sherlock.com.sun.media.sound;

import java.util.TreeMap;
import jp.kshoji.javax.sound.midi.MidiDevice;
import jp.kshoji.javax.sound.midi.MidiMessage;
import jp.kshoji.javax.sound.midi.ShortMessage;

public class SoftReceiver implements MidiDeviceReceiver {
    private Object control_mutex;
    protected SoftMainMixer mainmixer;
    protected TreeMap<Long, Object> midimessages;
    protected boolean open = true;
    private SoftSynthesizer synth;

    public SoftReceiver(SoftSynthesizer softSynthesizer) {
        this.control_mutex = softSynthesizer.control_mutex;
        this.synth = softSynthesizer;
        SoftMainMixer mainMixer = softSynthesizer.getMainMixer();
        this.mainmixer = mainMixer;
        if (mainMixer != null) {
            this.midimessages = mainMixer.midimessages;
        }
    }

    public MidiDevice getMidiDevice() {
        return this.synth;
    }

    public void send(MidiMessage midiMessage, long j) {
        synchronized (this.control_mutex) {
            if (!this.open) {
                throw new IllegalStateException("Receiver is not open");
            }
        }
        if (j != -1) {
            synchronized (this.control_mutex) {
                this.mainmixer.activity();
                while (this.midimessages.get(Long.valueOf(j)) != null) {
                    j++;
                }
                if (!(midiMessage instanceof ShortMessage) || ((ShortMessage) midiMessage).getChannel() <= 15) {
                    this.midimessages.put(Long.valueOf(j), midiMessage.getMessage());
                } else {
                    this.midimessages.put(Long.valueOf(j), midiMessage.clone());
                }
            }
            return;
        }
        this.mainmixer.processMessage(midiMessage);
    }

    public void close() {
        synchronized (this.control_mutex) {
            this.open = false;
        }
        this.synth.removeReceiver(this);
    }
}
