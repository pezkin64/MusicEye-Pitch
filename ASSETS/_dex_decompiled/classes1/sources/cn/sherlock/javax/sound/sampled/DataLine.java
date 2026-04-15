package cn.sherlock.javax.sound.sampled;

import cn.sherlock.javax.sound.sampled.Line;
import java.util.Arrays;

public interface DataLine extends Line {
    int available();

    void drain();

    void flush();

    int getBufferSize();

    AudioFormat getFormat();

    int getFramePosition();

    float getLevel();

    long getLongFramePosition();

    long getMicrosecondPosition();

    boolean isActive();

    boolean isRunning();

    void start();

    void stop();

    public static class Info extends Line.Info {
        private final AudioFormat[] formats;
        private final int maxBufferSize;
        private final int minBufferSize;

        public Info(Class<?> cls, AudioFormat[] audioFormatArr, int i, int i2) {
            super(cls);
            if (audioFormatArr == null) {
                this.formats = new AudioFormat[0];
            } else {
                this.formats = (AudioFormat[]) Arrays.copyOf(audioFormatArr, audioFormatArr.length);
            }
            this.minBufferSize = i;
            this.maxBufferSize = i2;
        }

        public Info(Class<?> cls, AudioFormat audioFormat, int i) {
            super(cls);
            if (audioFormat == null) {
                this.formats = new AudioFormat[0];
            } else {
                this.formats = new AudioFormat[]{audioFormat};
            }
            this.minBufferSize = i;
            this.maxBufferSize = i;
        }

        public Info(Class<?> cls, AudioFormat audioFormat) {
            this(cls, audioFormat, -1);
        }

        public AudioFormat[] getFormats() {
            AudioFormat[] audioFormatArr = this.formats;
            return (AudioFormat[]) Arrays.copyOf(audioFormatArr, audioFormatArr.length);
        }

        public boolean isFormatSupported(AudioFormat audioFormat) {
            int i = 0;
            while (true) {
                AudioFormat[] audioFormatArr = this.formats;
                if (i >= audioFormatArr.length) {
                    return false;
                }
                if (audioFormat.matches(audioFormatArr[i])) {
                    return true;
                }
                i++;
            }
        }

        public int getMinBufferSize() {
            return this.minBufferSize;
        }

        public int getMaxBufferSize() {
            return this.maxBufferSize;
        }

        public boolean matches(Line.Info info) {
            if (!super.matches(info)) {
                return false;
            }
            Info info2 = (Info) info;
            if (getMaxBufferSize() >= 0 && info2.getMaxBufferSize() >= 0 && getMaxBufferSize() > info2.getMaxBufferSize()) {
                return false;
            }
            if (getMinBufferSize() >= 0 && info2.getMinBufferSize() >= 0 && getMinBufferSize() < info2.getMinBufferSize()) {
                return false;
            }
            AudioFormat[] formats2 = getFormats();
            if (formats2 == null) {
                return true;
            }
            for (AudioFormat audioFormat : formats2) {
                if (audioFormat != null && !info2.isFormatSupported(audioFormat)) {
                    return false;
                }
            }
            return true;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            AudioFormat[] audioFormatArr = this.formats;
            if (audioFormatArr.length == 1 && audioFormatArr[0] != null) {
                stringBuffer.append(" supporting format " + this.formats[0]);
            } else if (getFormats().length > 1) {
                stringBuffer.append(" supporting " + getFormats().length + " audio formats");
            }
            int i = this.minBufferSize;
            if (i != -1 && this.maxBufferSize != -1) {
                stringBuffer.append(", and buffers of " + this.minBufferSize + " to " + this.maxBufferSize + " bytes");
            } else if (i != -1 && i > 0) {
                stringBuffer.append(", and buffers of at least " + this.minBufferSize + " bytes");
            } else if (this.maxBufferSize != -1) {
                stringBuffer.append(", and buffers of up to " + this.minBufferSize + " bytes");
            }
            return new String(super.toString() + stringBuffer);
        }
    }
}
