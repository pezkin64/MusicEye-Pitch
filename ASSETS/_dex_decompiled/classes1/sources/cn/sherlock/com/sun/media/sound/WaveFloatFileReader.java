package cn.sherlock.com.sun.media.sound;

import androidx.recyclerview.widget.ItemTouchHelper;
import cn.sherlock.javax.sound.sampled.AudioFileFormat;
import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.UnsupportedAudioFileException;
import cn.sherlock.javax.sound.sampled.spi.AudioFileReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WaveFloatFileReader extends AudioFileReader {
    public AudioFileFormat getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        inputStream.mark(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        try {
            return internal_getAudioFileFormat(inputStream);
        } finally {
            inputStream.reset();
        }
    }

    private AudioFileFormat internal_getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        RIFFReader rIFFReader = new RIFFReader(inputStream);
        if (!rIFFReader.getFormat().equals("RIFF")) {
            throw new UnsupportedAudioFileException();
        } else if (rIFFReader.getType().equals("WAVE")) {
            boolean z = false;
            long j = 1;
            boolean z2 = false;
            int i = 1;
            int i2 = 1;
            int i3 = 1;
            while (true) {
                if (!rIFFReader.hasNextChunk()) {
                    break;
                }
                RIFFReader nextChunk = rIFFReader.nextChunk();
                if (nextChunk.getFormat().equals("fmt ")) {
                    if (nextChunk.readUnsignedShort() == 3) {
                        int readUnsignedShort = nextChunk.readUnsignedShort();
                        long readUnsignedInt = nextChunk.readUnsignedInt();
                        nextChunk.readUnsignedInt();
                        int readUnsignedShort2 = nextChunk.readUnsignedShort();
                        i3 = nextChunk.readUnsignedShort();
                        i2 = readUnsignedShort2;
                        i = readUnsignedShort;
                        j = readUnsignedInt;
                        z2 = true;
                    } else {
                        throw new UnsupportedAudioFileException();
                    }
                }
                if (nextChunk.getFormat().equals("data")) {
                    z = true;
                    break;
                }
            }
            int i4 = i;
            int i5 = i2;
            int i6 = i3;
            if (!z2) {
                throw new UnsupportedAudioFileException();
            } else if (z) {
                float f = (float) j;
                return new AudioFileFormat(AudioFileFormat.Type.WAVE, new AudioFormat(AudioFloatConverter.PCM_FLOAT, f, i6, i4, i5, f, false), -1);
            } else {
                throw new UnsupportedAudioFileException();
            }
        } else {
            throw new UnsupportedAudioFileException();
        }
    }

    public AudioInputStream getAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat audioFileFormat = getAudioFileFormat(inputStream);
        RIFFReader rIFFReader = new RIFFReader(inputStream);
        if (!rIFFReader.getFormat().equals("RIFF")) {
            throw new UnsupportedAudioFileException();
        } else if (rIFFReader.getType().equals("WAVE")) {
            while (rIFFReader.hasNextChunk()) {
                RIFFReader nextChunk = rIFFReader.nextChunk();
                if (nextChunk.getFormat().equals("data")) {
                    return new AudioInputStream(nextChunk, audioFileFormat.getFormat(), nextChunk.getSize());
                }
            }
            throw new UnsupportedAudioFileException();
        } else {
            throw new UnsupportedAudioFileException();
        }
    }

    public AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException {
        InputStream openStream = url.openStream();
        try {
            return getAudioFileFormat((InputStream) new BufferedInputStream(openStream));
        } finally {
            openStream.close();
        }
    }

    public AudioFileFormat getAudioFileFormat(File file) throws UnsupportedAudioFileException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return getAudioFileFormat((InputStream) new BufferedInputStream(fileInputStream));
        } finally {
            fileInputStream.close();
        }
    }

    public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
        return getAudioInputStream((InputStream) new BufferedInputStream(url.openStream()));
    }

    public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
        return getAudioInputStream((InputStream) new BufferedInputStream(new FileInputStream(file)));
    }
}
