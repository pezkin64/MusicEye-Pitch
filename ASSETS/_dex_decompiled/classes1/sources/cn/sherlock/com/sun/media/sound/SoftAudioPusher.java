package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioInputStream;
import cn.sherlock.javax.sound.sampled.SourceDataLine;
import java.io.IOException;

public class SoftAudioPusher implements Runnable {
    private volatile boolean active = false;
    private AudioInputStream ais;
    private Thread audiothread;
    private byte[] buffer;
    private SourceDataLine sourceDataLine = null;

    public SoftAudioPusher(SourceDataLine sourceDataLine2, AudioInputStream audioInputStream, int i) {
        this.ais = audioInputStream;
        this.buffer = new byte[i];
        this.sourceDataLine = sourceDataLine2;
    }

    public synchronized void start() {
        if (!this.active) {
            this.active = true;
            Thread thread = new Thread(this);
            this.audiothread = thread;
            thread.setDaemon(true);
            this.audiothread.setPriority(10);
            this.audiothread.start();
        }
    }

    public synchronized void stop() {
        if (this.active) {
            this.active = false;
            try {
                this.audiothread.join();
            } catch (InterruptedException unused) {
            }
        }
    }

    public void run() {
        byte[] bArr = this.buffer;
        AudioInputStream audioInputStream = this.ais;
        SourceDataLine sourceDataLine2 = this.sourceDataLine;
        while (this.active) {
            try {
                int read = audioInputStream.read(bArr);
                if (read >= 0) {
                    sourceDataLine2.write(bArr, 0, read);
                } else {
                    return;
                }
            } catch (IOException unused) {
                this.active = false;
                return;
            }
        }
    }
}
