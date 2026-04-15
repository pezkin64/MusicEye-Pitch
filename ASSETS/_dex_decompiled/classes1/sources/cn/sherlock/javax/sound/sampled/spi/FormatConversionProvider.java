package cn.sherlock.javax.sound.sampled.spi;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;

public abstract class FormatConversionProvider {
    public abstract AudioInputStream getAudioInputStream(AudioFormat.Encoding encoding, AudioInputStream audioInputStream);

    public abstract AudioInputStream getAudioInputStream(AudioFormat audioFormat, AudioInputStream audioInputStream);

    public abstract AudioFormat.Encoding[] getSourceEncodings();

    public abstract AudioFormat.Encoding[] getTargetEncodings();

    public abstract AudioFormat.Encoding[] getTargetEncodings(AudioFormat audioFormat);

    public abstract AudioFormat[] getTargetFormats(AudioFormat.Encoding encoding, AudioFormat audioFormat);

    public boolean isSourceEncodingSupported(AudioFormat.Encoding encoding) {
        AudioFormat.Encoding[] sourceEncodings = getSourceEncodings();
        for (AudioFormat.Encoding equals : sourceEncodings) {
            if (encoding.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isTargetEncodingSupported(AudioFormat.Encoding encoding) {
        AudioFormat.Encoding[] targetEncodings = getTargetEncodings();
        for (AudioFormat.Encoding equals : targetEncodings) {
            if (encoding.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConversionSupported(AudioFormat.Encoding encoding, AudioFormat audioFormat) {
        AudioFormat.Encoding[] targetEncodings = getTargetEncodings(audioFormat);
        for (AudioFormat.Encoding equals : targetEncodings) {
            if (encoding.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConversionSupported(AudioFormat audioFormat, AudioFormat audioFormat2) {
        AudioFormat[] targetFormats = getTargetFormats(audioFormat.getEncoding(), audioFormat2);
        for (AudioFormat matches : targetFormats) {
            if (audioFormat.matches(matches)) {
                return true;
            }
        }
        return false;
    }
}
