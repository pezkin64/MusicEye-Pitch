package cn.sherlock.javax.sound.sampled;

import cn.sherlock.com.sun.media.sound.AudioFloatFormatConverter;
import cn.sherlock.com.sun.media.sound.WaveExtensibleFileReader;
import cn.sherlock.com.sun.media.sound.WaveFloatFileReader;
import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.spi.AudioFileReader;
import cn.sherlock.javax.sound.sampled.spi.FormatConversionProvider;
import cn.sherlock.media.SourceDataLineImpl;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AudioSystem {
    public static final int NOT_SPECIFIED = -1;

    private AudioSystem() {
    }

    public static SourceDataLine getSourceDataLine(AudioFormat audioFormat) throws LineUnavailableException {
        return new SourceDataLineImpl(audioFormat);
    }

    public static AudioFileFormat getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat audioFileFormat;
        List<AudioFileReader> audioFileReaders = getAudioFileReaders();
        int i = 0;
        while (true) {
            if (i >= audioFileReaders.size()) {
                audioFileFormat = null;
                break;
            }
            try {
                audioFileFormat = audioFileReaders.get(i).getAudioFileFormat(inputStream);
                break;
            } catch (UnsupportedAudioFileException unused) {
                i++;
            }
        }
        if (audioFileFormat != null) {
            return audioFileFormat;
        }
        throw new UnsupportedAudioFileException("file is not a supported file type");
    }

    public static AudioInputStream getAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream;
        List<AudioFileReader> audioFileReaders = getAudioFileReaders();
        int i = 0;
        while (true) {
            if (i >= audioFileReaders.size()) {
                audioInputStream = null;
                break;
            }
            try {
                audioInputStream = audioFileReaders.get(i).getAudioInputStream(inputStream);
                break;
            } catch (UnsupportedAudioFileException unused) {
                i++;
            }
        }
        if (audioInputStream != null) {
            return audioInputStream;
        }
        throw new UnsupportedAudioFileException("could not get audio input stream from input stream");
    }

    public static AudioInputStream getAudioInputStream(AudioFormat audioFormat, AudioInputStream audioInputStream) {
        if (audioInputStream.getFormat().matches(audioFormat)) {
            return audioInputStream;
        }
        List<FormatConversionProvider> formatConversionProviders = getFormatConversionProviders();
        for (int i = 0; i < formatConversionProviders.size(); i++) {
            FormatConversionProvider formatConversionProvider = formatConversionProviders.get(i);
            if (formatConversionProvider.isConversionSupported(audioFormat, audioInputStream.getFormat())) {
                return formatConversionProvider.getAudioInputStream(audioFormat, audioInputStream);
            }
        }
        throw new IllegalArgumentException("Unsupported conversion: " + audioFormat + " from " + audioInputStream.getFormat());
    }

    public static AudioFormat.Encoding[] getTargetEncodings(AudioFormat.Encoding encoding) {
        List<FormatConversionProvider> formatConversionProviders = getFormatConversionProviders();
        Vector vector = new Vector();
        for (int i = 0; i < formatConversionProviders.size(); i++) {
            FormatConversionProvider formatConversionProvider = formatConversionProviders.get(i);
            if (formatConversionProvider.isSourceEncodingSupported(encoding)) {
                AudioFormat.Encoding[] targetEncodings = formatConversionProvider.getTargetEncodings();
                for (AudioFormat.Encoding addElement : targetEncodings) {
                    vector.addElement(addElement);
                }
            }
        }
        return (AudioFormat.Encoding[]) vector.toArray(new AudioFormat.Encoding[0]);
    }

    public static AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream;
        List<AudioFileReader> audioFileReaders = getAudioFileReaders();
        int i = 0;
        while (true) {
            if (i >= audioFileReaders.size()) {
                audioInputStream = null;
                break;
            }
            try {
                audioInputStream = audioFileReaders.get(i).getAudioInputStream(file);
                break;
            } catch (UnsupportedAudioFileException unused) {
                i++;
            }
        }
        if (audioInputStream != null) {
            return audioInputStream;
        }
        throw new UnsupportedAudioFileException("could not get audio input stream from input file");
    }

    public static AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
        AudioInputStream audioInputStream;
        List<AudioFileReader> audioFileReaders = getAudioFileReaders();
        int i = 0;
        while (true) {
            if (i >= audioFileReaders.size()) {
                audioInputStream = null;
                break;
            }
            try {
                audioInputStream = audioFileReaders.get(i).getAudioInputStream(url);
                break;
            } catch (UnsupportedAudioFileException unused) {
                i++;
            }
        }
        if (audioInputStream != null) {
            return audioInputStream;
        }
        throw new UnsupportedAudioFileException("could not get audio input stream from input URL");
    }

    public static AudioFormat[] getTargetFormats(AudioFormat.Encoding encoding, AudioFormat audioFormat) {
        List<FormatConversionProvider> formatConversionProviders = getFormatConversionProviders();
        Vector vector = new Vector();
        int i = 0;
        for (int i2 = 0; i2 < formatConversionProviders.size(); i2++) {
            AudioFormat[] targetFormats = formatConversionProviders.get(i2).getTargetFormats(encoding, audioFormat);
            i += targetFormats.length;
            vector.addElement(targetFormats);
        }
        AudioFormat[] audioFormatArr = new AudioFormat[i];
        int i3 = 0;
        for (int i4 = 0; i4 < vector.size(); i4++) {
            AudioFormat[] audioFormatArr2 = (AudioFormat[]) vector.get(i4);
            int i5 = 0;
            while (i5 < audioFormatArr2.length) {
                audioFormatArr[i3] = audioFormatArr2[i5];
                i5++;
                i3++;
            }
        }
        return audioFormatArr;
    }

    private static List<FormatConversionProvider> getFormatConversionProviders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AudioFloatFormatConverter());
        return arrayList;
    }

    private static List<AudioFileReader> getAudioFileReaders() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WaveFloatFileReader());
        arrayList.add(new WaveExtensibleFileReader());
        return arrayList;
    }
}
