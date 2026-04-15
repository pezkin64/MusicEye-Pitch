package com.xemsoft.sheetmusicscanner2.export;

import android.content.Context;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Log;
import android.view.Surface;
import cn.sherlock.com.sun.media.sound.SoftSynthesizer;
import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import com.xemsoft.sheetmusicscanner2.lame.Lame;
import com.xemsoft.sheetmusicscanner2.synth.Synth;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import jp.kshoji.javax.sound.midi.MetaMessage;
import jp.kshoji.javax.sound.midi.MidiEvent;
import jp.kshoji.javax.sound.midi.MidiMessage;
import jp.kshoji.javax.sound.midi.MidiUnavailableException;
import jp.kshoji.javax.sound.midi.Receiver;
import jp.kshoji.javax.sound.midi.Sequence;
import jp.kshoji.javax.sound.midi.Track;

public class ExportToAudio {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOGTAG = "ExportToAudio.java";
    private Context m_Context;
    private SoftSynthesizer m_SoftSynth;
    private Synth m_Synth;

    public ExportToAudio(Context context) {
        this.m_Context = context;
        this.m_Synth = Synth.getInstance(context);
    }

    public boolean midiToAudio(Sequence sequence, List<Integer> list, int i, String str) {
        boolean z;
        int i2 = i;
        File file = new File(str);
        this.m_Synth.close();
        boolean z2 = false;
        if (i2 != 2 && i2 != 3 && i2 != 4) {
            return false;
        }
        SoftSynthesizer softSynthesizer = new SoftSynthesizer();
        this.m_SoftSynth = softSynthesizer;
        this.m_Synth.setSoftSynth(softSynthesizer);
        try {
            AudioInputStream openStream = this.m_SoftSynth.openStream((AudioFormat) null, (Map) null);
            if (this.m_Synth.loadProgramList(list) != 0) {
                return false;
            }
            try {
                double send = send(sequence, this.m_SoftSynth.getReceiver());
                AudioInputStream audioInputStream = new AudioInputStream(openStream, openStream.getFormat(), (long) (((double) openStream.getFormat().getFrameRate()) * (send + 1.0d)));
                if (i2 == 2) {
                    z = toAAC(audioInputStream, file);
                } else {
                    try {
                        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                        if (i2 == 3) {
                            z2 = toMp3(audioInputStream, dataOutputStream);
                        } else if (i2 == 4) {
                            z2 = toWave(audioInputStream, dataOutputStream);
                        }
                        closeStreams(audioInputStream, dataOutputStream);
                        z = z2;
                    } catch (FileNotFoundException e) {
                        Log.w(LOGTAG, "midiToWave()", e);
                        closeStreams(audioInputStream, (DataOutputStream) null);
                        return false;
                    }
                }
                this.m_Synth.close();
                return z;
            } catch (MidiUnavailableException e2) {
                Log.w(LOGTAG, "midiToWave()", e2);
                closeStreams(openStream, (DataOutputStream) null);
                return false;
            }
        } catch (MidiUnavailableException e3) {
            Log.w(LOGTAG, "midiToWave()", e3);
            return false;
        }
    }

    private boolean toWave(AudioInputStream audioInputStream, DataOutputStream dataOutputStream) {
        byte[] bArr = new byte[1024];
        try {
            dataOutputStream.write(createWavHeader(audioInputStream.getFormat(), audioInputStream.getFrameLength()), 0, 44);
            while (true) {
                int read = audioInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    return true;
                }
                if (read > 0) {
                    dataOutputStream.write(bArr, 0, read);
                }
            }
        } catch (IOException e) {
            Log.w(LOGTAG, "toWave()", e);
            return false;
        }
    }

    private boolean toMp3(AudioInputStream audioInputStream, DataOutputStream dataOutputStream) {
        byte[] bArr = new byte[1024];
        Lame lame = new Lame();
        boolean z = false;
        lame.open(2, 44100, 256, 0);
        while (true) {
            try {
                int read = audioInputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                } else if (read > 0) {
                    byte[] encode = lame.encode(byteToShort(bArr), 0, 512);
                    dataOutputStream.write(encode, 0, encode.length);
                }
            } catch (IOException e) {
                Log.w(LOGTAG, "midiToWave()", e);
            }
        }
        z = true;
        lame.close();
        return z;
    }

    private boolean toAAC(AudioInputStream audioInputStream, File file) {
        double d;
        char c;
        long j;
        boolean z;
        double d2;
        int i;
        char c2 = 2;
        try {
            MediaFormat createAudioFormat = MediaFormat.createAudioFormat("audio/mp4a-latm", 44100, 2);
            createAudioFormat.setInteger("aac-profile", 2);
            createAudioFormat.setInteger("bitrate", 64000);
            createAudioFormat.setInteger("max-input-size", 16384);
            MediaMuxer mediaMuxer = new MediaMuxer(file.getAbsolutePath(), 0);
            MediaCodec createEncoderByType = MediaCodec.createEncoderByType("audio/mp4a-latm");
            boolean z2 = true;
            createEncoderByType.configure(createAudioFormat, (Surface) null, (MediaCrypto) null, 1);
            createEncoderByType.start();
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            byte[] bArr = new byte[44100];
            double d3 = 0.0d;
            int i2 = 0;
            int i3 = 0;
            boolean z3 = true;
            while (true) {
                int i4 = i2;
                int i5 = 0;
                while (true) {
                    j = 5000;
                    if (i5 == -1 || !z3) {
                        char c3 = c;
                        double d4 = d;
                        AudioInputStream audioInputStream2 = audioInputStream;
                        int i6 = 0;
                    } else {
                        int dequeueInputBuffer = createEncoderByType.dequeueInputBuffer(5000);
                        if (dequeueInputBuffer >= 0) {
                            ByteBuffer inputBuffer = createEncoderByType.getInputBuffer(dequeueInputBuffer);
                            inputBuffer.clear();
                            char c4 = c;
                            int read = audioInputStream.read(bArr, 0, inputBuffer.limit());
                            if (read == -1) {
                                long j2 = (long) d;
                                double d5 = d;
                                i = dequeueInputBuffer;
                                d2 = d5;
                                createEncoderByType.queueInputBuffer(i, 0, 0, j2, 4);
                                z3 = false;
                            } else {
                                double d6 = d;
                                i = dequeueInputBuffer;
                                i4 += read;
                                inputBuffer.put(bArr, 0, read);
                                createEncoderByType.queueInputBuffer(i, 0, read, (long) d6, 0);
                                d2 = (double) ((((long) (i4 / 2)) * 1000000) / 44100);
                            }
                            i5 = i;
                            c = c4;
                            d = d2;
                        } else {
                            double d7 = d;
                            double d8 = d7;
                            char c5 = c;
                            AudioInputStream audioInputStream3 = audioInputStream;
                            i5 = dequeueInputBuffer;
                            d = d7;
                            c = c5;
                        }
                    }
                }
                char c32 = c;
                double d42 = d;
                AudioInputStream audioInputStream22 = audioInputStream;
                int i62 = 0;
                while (i62 != -1) {
                    i62 = createEncoderByType.dequeueOutputBuffer(bufferInfo, j);
                    if (i62 >= 0) {
                        z = z2;
                        ByteBuffer outputBuffer = createEncoderByType.getOutputBuffer(i62);
                        outputBuffer.position(bufferInfo.offset);
                        outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        if ((bufferInfo.flags & 2) == 0 || bufferInfo.size == 0) {
                            mediaMuxer.writeSampleData(i3, createEncoderByType.getOutputBuffer(i62), bufferInfo);
                            createEncoderByType.releaseOutputBuffer(i62, false);
                        } else {
                            createEncoderByType.releaseOutputBuffer(i62, false);
                        }
                    } else {
                        z = z2;
                        if (i62 == -2) {
                            i3 = mediaMuxer.addTrack(createEncoderByType.getOutputFormat());
                            mediaMuxer.start();
                        } else if (i62 != -1) {
                            Log.w(LOGTAG, "toAAC() - Unknown return code from dequeueOutputBuffer - " + i62);
                        }
                    }
                    z2 = z;
                    j = 5000;
                }
                boolean z4 = z2;
                if (bufferInfo.flags == 4) {
                    audioInputStream22.close();
                    mediaMuxer.stop();
                    mediaMuxer.release();
                    return z4;
                }
                z2 = z4;
                d3 = d42;
                i2 = i4;
                c2 = c32;
            }
        } catch (FileNotFoundException e) {
            Log.w(LOGTAG, "toAAC() - File not found", e);
            return false;
        } catch (IOException e2) {
            Log.w(LOGTAG, "toAAC() - IO exception", e2);
            return false;
        }
    }

    private short[] byteToShort(byte[] bArr) {
        short[] sArr = new short[(bArr.length / 2)];
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            sArr[i2] = (short) (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) & 65535);
            i += 2;
            i2++;
        }
        return sArr;
    }

    private static void closeStreams(AudioInputStream audioInputStream, DataOutputStream dataOutputStream) {
        if (audioInputStream != null) {
            try {
                audioInputStream.close();
            } catch (IOException unused) {
                return;
            }
        }
        if (dataOutputStream != null) {
            dataOutputStream.flush();
            dataOutputStream.close();
        }
    }

    private static double send(Sequence sequence, Receiver receiver) {
        Receiver receiver2 = receiver;
        float divisionType = sequence.getDivisionType();
        Track[] tracks = sequence.getTracks();
        int[] iArr = new int[tracks.length];
        int resolution = sequence.getResolution();
        byte b = 500000;
        long j = 0;
        long j2 = 0;
        while (true) {
            MidiEvent midiEvent = null;
            int i = -1;
            for (int i2 = 0; i2 < tracks.length; i2++) {
                int i3 = iArr[i2];
                Track track = tracks[i2];
                if (i3 < track.size()) {
                    MidiEvent midiEvent2 = track.get(i3);
                    if (midiEvent == null || midiEvent2.getTick() < midiEvent.getTick()) {
                        midiEvent = midiEvent2;
                        i = i2;
                    }
                }
            }
            if (i == -1) {
                return ((double) j) / 1000000.0d;
            }
            iArr[i] = iArr[i] + 1;
            long tick = midiEvent.getTick();
            int i4 = (divisionType > Sequence.PPQ ? 1 : (divisionType == Sequence.PPQ ? 0 : -1));
            j = i4 == 0 ? j + (((tick - j2) * ((long) b)) / ((long) resolution)) : (long) (((((double) tick) * 1000000.0d) * ((double) divisionType)) / ((double) resolution));
            MidiMessage message = midiEvent.getMessage();
            if (message instanceof MetaMessage) {
                if (i4 == 0) {
                    MetaMessage metaMessage = (MetaMessage) message;
                    if (metaMessage.getType() == 81) {
                        byte[] data = metaMessage.getData();
                        b = (data[2] & 255) | ((data[0] & 255) << 16) | ((data[1] & 255) << 8);
                    }
                }
            } else if (receiver2 != null) {
                receiver2.send(message, j);
            }
            j2 = tick;
        }
    }

    private static byte[] createWavHeader(AudioFormat audioFormat, long j) {
        int channels = audioFormat.getChannels();
        int sampleSizeInBits = audioFormat.getSampleSizeInBits();
        int frameRate = (int) audioFormat.getFrameRate();
        int i = ((frameRate * channels) * sampleSizeInBits) / 8;
        int frameSize = audioFormat.getFrameSize();
        long j2 = ((long) frameSize) * j;
        long j3 = 36 + j2;
        return new byte[]{82, 73, 70, 70, (byte) ((int) (j3 & 255)), (byte) ((int) ((j3 >> 8) & 255)), (byte) ((int) ((j3 >> 16) & 255)), (byte) ((int) ((j3 >> 24) & 255)), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) (channels & 255), 0, (byte) (frameRate & 255), (byte) ((frameRate >> 8) & 255), (byte) ((frameRate >> 16) & 255), (byte) ((frameRate >> 24) & 255), (byte) (i & 255), (byte) ((i >> 8) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 24) & 255), (byte) (frameSize & 255), 0, (byte) (sampleSizeInBits & 255), 0, 100, 97, 116, 97, (byte) ((int) (j2 & 255)), (byte) ((int) ((j2 >> 8) & 255)), (byte) ((int) ((j2 >> 16) & 255)), (byte) ((int) ((j2 >> 24) & 255))};
    }
}
