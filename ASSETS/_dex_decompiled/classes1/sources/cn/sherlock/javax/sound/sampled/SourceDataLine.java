package cn.sherlock.javax.sound.sampled;

public interface SourceDataLine extends DataLine {
    void open(AudioFormat audioFormat) throws LineUnavailableException;

    void open(AudioFormat audioFormat, int i) throws LineUnavailableException;

    int write(byte[] bArr, int i, int i2);
}
