package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.SourceDataLine;
import java.util.Map;
import jp.kshoji.javax.sound.midi.MidiUnavailableException;
import jp.kshoji.javax.sound.midi.Synthesizer;

public interface AudioSynthesizer extends Synthesizer {
    AudioFormat getFormat();

    AudioSynthesizerPropertyInfo[] getPropertyInfo(Map<String, Object> map);

    void open(SourceDataLine sourceDataLine, Map<String, Object> map) throws MidiUnavailableException;

    AudioInputStream openStream(AudioFormat audioFormat, Map<String, Object> map) throws MidiUnavailableException;
}
