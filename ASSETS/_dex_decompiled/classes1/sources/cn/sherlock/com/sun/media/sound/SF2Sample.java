package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import cn.sherlock.javax.sound.sampled.AudioInputStream;
import java.io.InputStream;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.SoundbankResource;

public class SF2Sample extends SoundbankResource {
    protected ModelByteBuffer data;
    protected ModelByteBuffer data24;
    protected long endLoop = 0;
    protected String name = "";
    protected int originalPitch = 60;
    protected byte pitchCorrection = 0;
    protected int sampleLink = 0;
    protected long sampleRate = 44100;
    protected int sampleType = 0;
    protected long startLoop = 0;

    public SF2Sample(Soundbank soundbank) {
        super(soundbank, (String) null, AudioInputStream.class);
    }

    public SF2Sample() {
        super((Soundbank) null, (String) null, AudioInputStream.class);
    }

    public Object getData() {
        AudioFormat format = getFormat();
        InputStream inputStream = this.data.getInputStream();
        if (inputStream == null) {
            return null;
        }
        return new AudioInputStream(inputStream, format, this.data.capacity());
    }

    public ModelByteBuffer getDataBuffer() {
        return this.data;
    }

    public ModelByteBuffer getData24Buffer() {
        return this.data24;
    }

    public AudioFormat getFormat() {
        return new AudioFormat((float) this.sampleRate, 16, 1, true, false);
    }

    public void setData(ModelByteBuffer modelByteBuffer) {
        this.data = modelByteBuffer;
    }

    public void setData(byte[] bArr) {
        this.data = new ModelByteBuffer(bArr);
    }

    public void setData(byte[] bArr, int i, int i2) {
        this.data = new ModelByteBuffer(bArr, i, i2);
    }

    public void setData24(ModelByteBuffer modelByteBuffer) {
        this.data24 = modelByteBuffer;
    }

    public void setData24(byte[] bArr) {
        this.data24 = new ModelByteBuffer(bArr);
    }

    public void setData24(byte[] bArr, int i, int i2) {
        this.data24 = new ModelByteBuffer(bArr, i, i2);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public long getEndLoop() {
        return this.endLoop;
    }

    public void setEndLoop(long j) {
        this.endLoop = j;
    }

    public int getOriginalPitch() {
        return this.originalPitch;
    }

    public void setOriginalPitch(int i) {
        this.originalPitch = i;
    }

    public byte getPitchCorrection() {
        return this.pitchCorrection;
    }

    public void setPitchCorrection(byte b) {
        this.pitchCorrection = b;
    }

    public int getSampleLink() {
        return this.sampleLink;
    }

    public void setSampleLink(int i) {
        this.sampleLink = i;
    }

    public long getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(long j) {
        this.sampleRate = j;
    }

    public int getSampleType() {
        return this.sampleType;
    }

    public void setSampleType(int i) {
        this.sampleType = i;
    }

    public long getStartLoop() {
        return this.startLoop;
    }

    public void setStartLoop(long j) {
        this.startLoop = j;
    }

    public String toString() {
        return "Sample: " + this.name;
    }
}
