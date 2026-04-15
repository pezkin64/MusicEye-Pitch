package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.AudioSystem;
import cn.sherlock.javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import jp.kshoji.javax.sound.midi.InvalidMidiDataException;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.spi.SoundbankReader;

public class AudioFileSoundbankReader extends SoundbankReader {
    public Soundbank getSoundbank(URL url) throws InvalidMidiDataException, IOException {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Soundbank soundbank = getSoundbank(audioInputStream);
            audioInputStream.close();
            return soundbank;
        } catch (UnsupportedAudioFileException | IOException unused) {
            return null;
        }
    }

    public Soundbank getSoundbank(InputStream inputStream) throws InvalidMidiDataException, IOException {
        inputStream.mark(512);
        try {
            Soundbank soundbank = getSoundbank(AudioSystem.getAudioInputStream(inputStream));
            if (soundbank != null) {
                return soundbank;
            }
        } catch (UnsupportedAudioFileException | IOException unused) {
        }
        inputStream.reset();
        return null;
    }

    public Soundbank getSoundbank(AudioInputStream audioInputStream) throws InvalidMidiDataException, IOException {
        byte[] bArr;
        try {
            if (audioInputStream.getFrameLength() == -1) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[(1024 - (1024 % audioInputStream.getFormat().getFrameSize()))];
                while (true) {
                    int read = audioInputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                audioInputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                bArr = new byte[((int) (audioInputStream.getFrameLength() * ((long) audioInputStream.getFormat().getFrameSize())))];
                new DataInputStream(audioInputStream).readFully(bArr);
            }
            ModelByteBufferWavetable modelByteBufferWavetable = new ModelByteBufferWavetable(new ModelByteBuffer(bArr), audioInputStream.getFormat(), -4800.0f);
            ModelPerformer modelPerformer = new ModelPerformer();
            modelPerformer.getOscillators().add(modelByteBufferWavetable);
            SimpleSoundbank simpleSoundbank = new SimpleSoundbank();
            SimpleInstrument simpleInstrument = new SimpleInstrument();
            simpleInstrument.add(modelPerformer);
            simpleSoundbank.addInstrument(simpleInstrument);
            return simpleSoundbank;
        } catch (Exception unused) {
            return null;
        }
    }

    public Soundbank getSoundbank(File file) throws InvalidMidiDataException, IOException {
        try {
            AudioSystem.getAudioInputStream(file).close();
            ModelByteBufferWavetable modelByteBufferWavetable = new ModelByteBufferWavetable(new ModelByteBuffer(file, 0, file.length()), -4800.0f);
            ModelPerformer modelPerformer = new ModelPerformer();
            modelPerformer.getOscillators().add(modelByteBufferWavetable);
            SimpleSoundbank simpleSoundbank = new SimpleSoundbank();
            SimpleInstrument simpleInstrument = new SimpleInstrument();
            simpleInstrument.add(modelPerformer);
            simpleSoundbank.addInstrument(simpleInstrument);
            return simpleSoundbank;
        } catch (UnsupportedAudioFileException | IOException unused) {
            return null;
        }
    }
}
