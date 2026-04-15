package cn.sherlock.media;

import android.media.AudioTrack;
import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.DataLine;
import cn.sherlock.javax.sound.sampled.LineUnavailableException;
import cn.sherlock.javax.sound.sampled.SourceDataLine;

public class SourceDataLineImpl implements SourceDataLine {
    private AudioTrack audioTrack;
    private int bufferSize;
    private AudioFormat format;

    public int available() {
        return 0;
    }

    @Deprecated
    public float getLevel() {
        return 0.0f;
    }

    @Deprecated
    public DataLine.Info getLineInfo() {
        return null;
    }

    public SourceDataLineImpl() {
        this.format = new AudioFormat(44100.0f, 16, 2, true, false);
    }

    public SourceDataLineImpl(AudioFormat audioFormat) {
        new AudioFormat(44100.0f, 16, 2, true, false);
        this.format = audioFormat;
    }

    public void drain() {
        flush();
    }

    public void flush() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.flush();
        }
    }

    public void start() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.play();
        }
    }

    public void stop() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.stop();
        }
    }

    public void close() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.stop();
            this.audioTrack.release();
            this.audioTrack = null;
        }
    }

    public boolean isOpen() {
        return this.audioTrack != null;
    }

    public boolean isRunning() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 == null || audioTrack2.getPlayState() != 3) {
            return false;
        }
        return true;
    }

    public boolean isActive() {
        return isRunning();
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public int getBufferSize() {
        if (this.audioTrack != null) {
            return this.bufferSize;
        }
        return 0;
    }

    public int getFramePosition() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            return audioTrack2.getPlaybackHeadPosition();
        }
        return 0;
    }

    public long getLongFramePosition() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            return (long) audioTrack2.getPlaybackHeadPosition();
        }
        return 0;
    }

    @Deprecated
    public long getMicrosecondPosition() {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            return (long) (audioTrack2.getPlaybackHeadPosition() * 1000);
        }
        return 0;
    }

    public void open() throws LineUnavailableException {
        int i;
        int sampleRate = (int) this.format.getSampleRate();
        if (this.format.getChannels() == 1) {
            i = 4;
        } else if (this.format.getChannels() == 2) {
            i = 12;
        } else {
            throw new IllegalArgumentException("format.getChannels() must in (1,2)");
        }
        int i2 = i;
        int minBufferSize = AudioTrack.getMinBufferSize(sampleRate, i2, 2);
        this.bufferSize = minBufferSize;
        this.audioTrack = new AudioTrack(3, sampleRate, i2, 2, minBufferSize, 1);
    }

    public void open(AudioFormat audioFormat, int i) throws LineUnavailableException {
        int i2;
        this.format = audioFormat;
        this.bufferSize = i;
        int sampleRate = (int) audioFormat.getSampleRate();
        if (audioFormat.getChannels() == 1) {
            i2 = 4;
        } else if (audioFormat.getChannels() == 2) {
            i2 = 12;
        } else {
            throw new IllegalArgumentException("format.getChannels() must in (1,2)");
        }
        this.audioTrack = new AudioTrack(3, sampleRate, i2, 2, i, 1);
    }

    public void open(AudioFormat audioFormat) throws LineUnavailableException {
        int i;
        this.format = audioFormat;
        int sampleRate = (int) audioFormat.getSampleRate();
        if (audioFormat.getChannels() == 1) {
            i = 4;
        } else if (audioFormat.getChannels() == 2) {
            i = 12;
        } else {
            throw new IllegalArgumentException("format.getChannels() must in (1,2)");
        }
        int i2 = i;
        int minBufferSize = AudioTrack.getMinBufferSize(sampleRate, i2, 2);
        this.bufferSize = minBufferSize;
        this.audioTrack = new AudioTrack(3, sampleRate, i2, 2, minBufferSize, 1);
    }

    public int write(byte[] bArr, int i, int i2) {
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            return audioTrack2.write(bArr, i, i2);
        }
        return 0;
    }
}
