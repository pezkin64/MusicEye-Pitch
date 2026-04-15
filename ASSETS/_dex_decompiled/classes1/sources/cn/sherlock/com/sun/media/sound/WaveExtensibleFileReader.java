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
import java.util.HashMap;

public class WaveExtensibleFileReader extends AudioFileReader {
    private static GUID SUBTYPE_IEEE_FLOAT = new GUID(3, 0, 16, 128, 0, 0, 170, 0, 56, 155, 113);
    private static GUID SUBTYPE_PCM = new GUID(1, 0, 16, 128, 0, 0, 170, 0, 56, 155, 113);
    private static String[] allchannelnames = {"w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12", "w13", "w14", "w15", "w16", "w17", "w18", "w19", "w20", "w21", "w22", "w23", "w24", "w25", "w26", "w27", "w28", "w29", "w30", "w31", "w32", "w33", "w34", "w35", "w36", "w37", "w38", "w39", "w40", "w41", "w42", "w43", "w44", "w45", "w46", "w47", "w48", "w49", "w50", "w51", "w52", "w53", "w54", "w55", "w56", "w57", "w58", "w59", "w60", "w61", "w62", "w63", "w64"};
    private static String[] channelnames = {"FL", "FR", "FC", "LF", "BL", "BR", "FLC", "FLR", "BC", "SL", "SR", "TC", "TFL", "TFC", "TFR", "TBL", "TBC", "TBR"};

    private static class GUID {
        long i1;
        int s1;
        int s2;
        int x1;
        int x2;
        int x3;
        int x4;
        int x5;
        int x6;
        int x7;
        int x8;

        private GUID() {
        }

        public GUID(long j, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            this.i1 = j;
            this.s1 = i;
            this.s2 = i2;
            this.x1 = i3;
            this.x2 = i4;
            this.x3 = i5;
            this.x4 = i6;
            this.x5 = i7;
            this.x6 = i8;
            this.x7 = i9;
            this.x8 = i10;
        }

        public static GUID read(RIFFReader rIFFReader) throws IOException {
            GUID guid = new GUID();
            guid.i1 = rIFFReader.readUnsignedInt();
            guid.s1 = rIFFReader.readUnsignedShort();
            guid.s2 = rIFFReader.readUnsignedShort();
            guid.x1 = rIFFReader.readUnsignedByte();
            guid.x2 = rIFFReader.readUnsignedByte();
            guid.x3 = rIFFReader.readUnsignedByte();
            guid.x4 = rIFFReader.readUnsignedByte();
            guid.x5 = rIFFReader.readUnsignedByte();
            guid.x6 = rIFFReader.readUnsignedByte();
            guid.x7 = rIFFReader.readUnsignedByte();
            guid.x8 = rIFFReader.readUnsignedByte();
            return guid;
        }

        public int hashCode() {
            return (int) this.i1;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof GUID)) {
                return false;
            }
            GUID guid = (GUID) obj;
            if (this.i1 == guid.i1 && this.s1 == guid.s1 && this.s2 == guid.s2 && this.x1 == guid.x1 && this.x2 == guid.x2 && this.x3 == guid.x3 && this.x4 == guid.x4 && this.x5 == guid.x5 && this.x6 == guid.x6 && this.x7 == guid.x7 && this.x8 == guid.x8) {
                return true;
            }
            return false;
        }
    }

    private String decodeChannelMask(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = 1;
        for (int i = 0; i < allchannelnames.length; i++) {
            if ((j & j2) != 0) {
                if (i < channelnames.length) {
                    stringBuffer.append(channelnames[i] + " ");
                } else {
                    stringBuffer.append(allchannelnames[i] + " ");
                }
            }
            j2 *= 2;
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public AudioFileFormat getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        inputStream.mark(ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION);
        try {
            return internal_getAudioFileFormat(inputStream);
        } finally {
            inputStream.reset();
        }
    }

    private AudioFileFormat internal_getAudioFileFormat(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
        long j;
        AudioFormat audioFormat;
        RIFFReader rIFFReader = new RIFFReader(inputStream);
        if (!rIFFReader.getFormat().equals("RIFF")) {
            throw new UnsupportedAudioFileException();
        } else if (rIFFReader.getType().equals("WAVE")) {
            boolean z = false;
            long j2 = 1;
            GUID guid = null;
            boolean z2 = false;
            int i = 1;
            int i2 = 1;
            int i3 = 1;
            int i4 = 1;
            long j3 = 0;
            while (true) {
                if (!rIFFReader.hasNextChunk()) {
                    j = 0;
                    break;
                }
                RIFFReader nextChunk = rIFFReader.nextChunk();
                j = 0;
                if (nextChunk.getFormat().equals("fmt ")) {
                    if (nextChunk.readUnsignedShort() == 65534) {
                        i3 = nextChunk.readUnsignedShort();
                        long readUnsignedInt = nextChunk.readUnsignedInt();
                        nextChunk.readUnsignedInt();
                        i4 = nextChunk.readUnsignedShort();
                        i2 = nextChunk.readUnsignedShort();
                        if (nextChunk.readUnsignedShort() == 22) {
                            int readUnsignedShort = nextChunk.readUnsignedShort();
                            if (readUnsignedShort <= i2) {
                                i = readUnsignedShort;
                                j3 = nextChunk.readUnsignedInt();
                                guid = GUID.read(nextChunk);
                                j2 = readUnsignedInt;
                                z2 = true;
                            } else {
                                throw new UnsupportedAudioFileException();
                            }
                        } else {
                            throw new UnsupportedAudioFileException();
                        }
                    } else {
                        throw new UnsupportedAudioFileException();
                    }
                }
                if (nextChunk.getFormat().equals("data")) {
                    z = true;
                    break;
                }
            }
            int i5 = i3;
            int i6 = i4;
            if (!z2) {
                throw new UnsupportedAudioFileException();
            } else if (z) {
                HashMap hashMap = new HashMap();
                String decodeChannelMask = decodeChannelMask(j3);
                if (decodeChannelMask != null) {
                    hashMap.put("channelOrder", decodeChannelMask);
                }
                if (j3 != j) {
                    hashMap.put("channelMask", Long.valueOf(j3));
                }
                hashMap.put("validBitsPerSample", Integer.valueOf(i));
                if (!guid.equals(SUBTYPE_PCM)) {
                    HashMap hashMap2 = hashMap;
                    int i7 = i2;
                    if (guid.equals(SUBTYPE_IEEE_FLOAT)) {
                        float f = (float) j2;
                        audioFormat = new AudioFormat(AudioFloatConverter.PCM_FLOAT, f, i7, i5, i6, f, false, hashMap2);
                    } else {
                        throw new UnsupportedAudioFileException();
                    }
                } else if (i2 == 8) {
                    float f2 = (float) j2;
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_UNSIGNED, f2, i2, i5, i6, f2, false, hashMap);
                } else {
                    HashMap hashMap3 = hashMap;
                    float f3 = (float) j2;
                    audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, f3, i2, i5, i6, f3, false, hashMap3);
                }
                return new AudioFileFormat(AudioFileFormat.Type.WAVE, audioFormat, -1);
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
