package cn.sherlock.javax.sound.sampled.spi;

import cn.sherlock.javax.sound.sampled.AudioFileFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AudioFileReader {
    public abstract AudioFileFormat getAudioFileFormat(File file) throws UnsupportedAudioFileException, IOException;

    public abstract AudioFileFormat getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException;

    public abstract AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException;

    public abstract AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException;

    public abstract AudioInputStream getAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException;

    public abstract AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException;
}
