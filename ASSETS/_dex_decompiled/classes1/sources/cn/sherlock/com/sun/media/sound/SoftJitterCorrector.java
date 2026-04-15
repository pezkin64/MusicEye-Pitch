package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;

public class SoftJitterCorrector extends AudioInputStream {

    private static class JitterStream extends InputStream {
        static int MAX_BUFFER_SIZE = 1048576;
        boolean active = true;
        byte[] bbuffer = null;
        int bbuffer_max = 0;
        int bbuffer_pos = 0;
        byte[][] buffers;
        Object buffers_mutex = new Object();
        int readpos = 0;
        AudioInputStream stream;
        Thread thread;
        int w = 0;
        int w_count = 1000;
        int w_max_tol = 10;
        int w_min = -1;
        int w_min_tol = 2;
        int writepos = 0;

        /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
            java.lang.Thread.sleep(1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
            r0 = r4.buffers_mutex;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002d, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r2 = r4.writepos;
            r3 = r4.readpos;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0032, code lost:
            if (r2 <= r3) goto L_0x0048;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0034, code lost:
            r4.w_min = -1;
            r4.w = r4.w_count - 1;
            r4.readpos = r3 + 1;
            r1 = r4.buffers;
            r1 = r1[r3 % r1.length];
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0046, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x004d, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public byte[] nextReadBuffer() {
            /*
                r4 = this;
                java.lang.Object r0 = r4.buffers_mutex
                monitor-enter(r0)
                int r1 = r4.writepos     // Catch:{ all -> 0x004f }
                int r2 = r4.readpos     // Catch:{ all -> 0x004f }
                if (r1 <= r2) goto L_0x001c
                int r1 = r1 - r2
                int r3 = r4.w_min     // Catch:{ all -> 0x004f }
                if (r1 >= r3) goto L_0x0010
                r4.w_min = r1     // Catch:{ all -> 0x004f }
            L_0x0010:
                int r1 = r2 + 1
                r4.readpos = r1     // Catch:{ all -> 0x004f }
                byte[][] r1 = r4.buffers     // Catch:{ all -> 0x004f }
                int r3 = r1.length     // Catch:{ all -> 0x004f }
                int r2 = r2 % r3
                r1 = r1[r2]     // Catch:{ all -> 0x004f }
                monitor-exit(r0)     // Catch:{ all -> 0x004f }
                return r1
            L_0x001c:
                r1 = -1
                r4.w_min = r1     // Catch:{ all -> 0x004f }
                int r2 = r4.w_count     // Catch:{ all -> 0x004f }
                int r2 = r2 + -1
                r4.w = r2     // Catch:{ all -> 0x004f }
                monitor-exit(r0)     // Catch:{ all -> 0x004f }
            L_0x0026:
                r2 = 1
                java.lang.Thread.sleep(r2)     // Catch:{ InterruptedException -> 0x004d }
                java.lang.Object r0 = r4.buffers_mutex
                monitor-enter(r0)
                int r2 = r4.writepos     // Catch:{ all -> 0x004a }
                int r3 = r4.readpos     // Catch:{ all -> 0x004a }
                if (r2 <= r3) goto L_0x0048
                r4.w_min = r1     // Catch:{ all -> 0x004a }
                int r1 = r4.w_count     // Catch:{ all -> 0x004a }
                int r1 = r1 + -1
                r4.w = r1     // Catch:{ all -> 0x004a }
                int r1 = r3 + 1
                r4.readpos = r1     // Catch:{ all -> 0x004a }
                byte[][] r1 = r4.buffers     // Catch:{ all -> 0x004a }
                int r2 = r1.length     // Catch:{ all -> 0x004a }
                int r3 = r3 % r2
                r1 = r1[r3]     // Catch:{ all -> 0x004a }
                monitor-exit(r0)     // Catch:{ all -> 0x004a }
                return r1
            L_0x0048:
                monitor-exit(r0)     // Catch:{ all -> 0x004a }
                goto L_0x0026
            L_0x004a:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x004a }
                throw r1
            L_0x004d:
                r0 = 0
                return r0
            L_0x004f:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x004f }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.nextReadBuffer():byte[]");
        }

        public byte[] nextWriteBuffer() {
            byte[] bArr;
            synchronized (this.buffers_mutex) {
                byte[][] bArr2 = this.buffers;
                bArr = bArr2[this.writepos % bArr2.length];
            }
            return bArr;
        }

        public void commit() {
            synchronized (this.buffers_mutex) {
                int i = this.writepos + 1;
                this.writepos = i;
                int i2 = this.readpos;
                int i3 = i - i2;
                byte[][] bArr = this.buffers;
                if (i3 > bArr.length) {
                    int max = Math.max(bArr.length * 2, (i - i2) + 10);
                    int[] iArr = new int[2];
                    iArr[1] = this.buffers[0].length;
                    iArr[0] = max;
                    this.buffers = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
                }
            }
        }

        public JitterStream(AudioInputStream audioInputStream, int i, int i2) {
            int i3 = i / i2;
            int i4 = i3 * 10;
            this.w_count = i4;
            if (i4 < 100) {
                this.w_count = 100;
            }
            int[] iArr = new int[2];
            iArr[1] = i2;
            iArr[0] = i3 + 10;
            this.buffers = (byte[][]) Array.newInstance(Byte.TYPE, iArr);
            this.bbuffer_max = MAX_BUFFER_SIZE / i2;
            this.stream = audioInputStream;
            Thread thread2 = new Thread(new Runnable() {
                /* JADX WARNING: Code restructure failed: missing block: B:10:0x0039, code lost:
                    monitor-enter(r7);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
                    r6 = r11.this$0.writepos - r11.this$0.readpos;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
                    if (r5 != 0) goto L_0x009e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:14:0x0045, code lost:
                    r11.this$0.w++;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x0054, code lost:
                    if (r11.this$0.w_min == Integer.MAX_VALUE) goto L_0x009e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:0x005e, code lost:
                    if (r11.this$0.w != r11.this$0.w_count) goto L_0x009e;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x0068, code lost:
                    if (r11.this$0.w_min >= r11.this$0.w_min_tol) goto L_0x007b;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x006a, code lost:
                    r5 = ((r11.this$0.w_min_tol + r11.this$0.w_max_tol) / 2) - r11.this$0.w_min;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
                    r5 = 0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x0084, code lost:
                    if (r11.this$0.w_min <= r11.this$0.w_max_tol) goto L_0x0096;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x0086, code lost:
                    r5 = ((r11.this$0.w_min_tol + r11.this$0.w_max_tol) / 2) - r11.this$0.w_min;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x0096, code lost:
                    r11.this$0.w = 0;
                    r11.this$0.w_min = Integer.MAX_VALUE;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:26:0x009e, code lost:
                    monitor-exit(r7);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a3, code lost:
                    if (r6 <= r11.this$0.bbuffer_max) goto L_0x00cd;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a5, code lost:
                    r6 = r11.this$0.buffers;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a9, code lost:
                    monitor-enter(r6);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
                    r7 = r11.this$0.writepos - r11.this$0.readpos;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:33:0x00b3, code lost:
                    monitor-exit(r6);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b4, code lost:
                    r8 = r11.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:35:0x00b6, code lost:
                    monitor-enter(r8);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:0x00bb, code lost:
                    if (r11.this$0.active != false) goto L_0x00bf;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bd, code lost:
                    monitor-exit(r8);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:41:0x00bf, code lost:
                    monitor-exit(r8);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
                    java.lang.Thread.sleep(1);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:55:0x00cd, code lost:
                    if (r5 >= 0) goto L_0x00d2;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:56:0x00cf, code lost:
                    r5 = r5 + 1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:57:0x00d2, code lost:
                    r6 = r11.this$0.nextWriteBuffer();
                    r7 = 0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:60:0x00da, code lost:
                    if (r7 == r6.length) goto L_0x00f5;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:61:0x00dc, code lost:
                    r8 = r11.this$0.stream.read(r6, r7, r6.length - r7);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:62:0x00e6, code lost:
                    if (r8 < 0) goto L_0x00ef;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e8, code lost:
                    if (r8 != 0) goto L_0x00ed;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ea, code lost:
                    java.lang.Thread.yield();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:65:0x00ed, code lost:
                    r7 = r7 + r8;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:67:0x00f4, code lost:
                    throw new java.io.EOFException();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:9:0x0035, code lost:
                    r7 = r11.this$0.buffers;
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r11 = this;
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r0 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        cn.sherlock.javax.sound.sampled.AudioInputStream r0 = r0.stream
                        cn.sherlock.javax.sound.sampled.AudioFormat r0 = r0.getFormat()
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r1 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        byte[][] r1 = r1.buffers
                        r2 = 0
                        r1 = r1[r2]
                        int r1 = r1.length
                        int r3 = r0.getFrameSize()
                        int r1 = r1 / r3
                        double r3 = (double) r1
                        r5 = 4741671816366391296(0x41cdcd6500000000, double:1.0E9)
                        double r3 = r3 * r5
                        float r0 = r0.getSampleRate()
                        double r0 = (double) r0
                        double r3 = r3 / r0
                        long r0 = (long) r3
                        long r3 = java.lang.System.nanoTime()
                        long r3 = r3 + r0
                        r5 = r2
                    L_0x0029:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        monitor-enter(r6)
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r7 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x011c }
                        boolean r7 = r7.active     // Catch:{ all -> 0x011c }
                        if (r7 != 0) goto L_0x0034
                        monitor-exit(r6)     // Catch:{ all -> 0x011c }
                        return
                    L_0x0034:
                        monitor-exit(r6)     // Catch:{ all -> 0x011c }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        byte[][] r7 = r6.buffers
                        monitor-enter(r7)
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r6 = r6.writepos     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.readpos     // Catch:{ all -> 0x0119 }
                        int r6 = r6 - r8
                        if (r5 != 0) goto L_0x009e
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r9 = r8.w     // Catch:{ all -> 0x0119 }
                        int r9 = r9 + 1
                        r8.w = r9     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_min     // Catch:{ all -> 0x0119 }
                        r9 = 2147483647(0x7fffffff, float:NaN)
                        if (r8 == r9) goto L_0x009e
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r10 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r10 = r10.w_count     // Catch:{ all -> 0x0119 }
                        if (r8 != r10) goto L_0x009e
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r5 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r5 = r5.w_min     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_min_tol     // Catch:{ all -> 0x0119 }
                        if (r5 >= r8) goto L_0x007b
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r5 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r5 = r5.w_min_tol     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_max_tol     // Catch:{ all -> 0x0119 }
                        int r5 = r5 + r8
                        int r5 = r5 / 2
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_min     // Catch:{ all -> 0x0119 }
                        int r5 = r5 - r8
                        goto L_0x007c
                    L_0x007b:
                        r5 = r2
                    L_0x007c:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_min     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r10 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r10 = r10.w_max_tol     // Catch:{ all -> 0x0119 }
                        if (r8 <= r10) goto L_0x0096
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r5 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r5 = r5.w_min_tol     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_max_tol     // Catch:{ all -> 0x0119 }
                        int r5 = r5 + r8
                        int r5 = r5 / 2
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        int r8 = r8.w_min     // Catch:{ all -> 0x0119 }
                        int r5 = r5 - r8
                    L_0x0096:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        r8.w = r2     // Catch:{ all -> 0x0119 }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x0119 }
                        r8.w_min = r9     // Catch:{ all -> 0x0119 }
                    L_0x009e:
                        monitor-exit(r7)     // Catch:{ all -> 0x0119 }
                    L_0x009f:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r7 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        int r7 = r7.bbuffer_max
                        if (r6 <= r7) goto L_0x00cd
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        byte[][] r6 = r6.buffers
                        monitor-enter(r6)
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r7 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x00ca }
                        int r7 = r7.writepos     // Catch:{ all -> 0x00ca }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x00ca }
                        int r8 = r8.readpos     // Catch:{ all -> 0x00ca }
                        int r7 = r7 - r8
                        monitor-exit(r6)     // Catch:{ all -> 0x00ca }
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        monitor-enter(r8)
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ all -> 0x00c7 }
                        boolean r6 = r6.active     // Catch:{ all -> 0x00c7 }
                        if (r6 != 0) goto L_0x00bf
                        monitor-exit(r8)     // Catch:{ all -> 0x00c7 }
                        goto L_0x00cd
                    L_0x00bf:
                        monitor-exit(r8)     // Catch:{ all -> 0x00c7 }
                        r8 = 1
                        java.lang.Thread.sleep(r8)     // Catch:{ InterruptedException -> 0x00c5 }
                    L_0x00c5:
                        r6 = r7
                        goto L_0x009f
                    L_0x00c7:
                        r0 = move-exception
                        monitor-exit(r8)     // Catch:{ all -> 0x00c7 }
                        throw r0
                    L_0x00ca:
                        r0 = move-exception
                        monitor-exit(r6)     // Catch:{ all -> 0x00ca }
                        throw r0
                    L_0x00cd:
                        if (r5 >= 0) goto L_0x00d2
                        int r5 = r5 + 1
                        goto L_0x00fa
                    L_0x00d2:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        byte[] r6 = r6.nextWriteBuffer()
                        r7 = r2
                    L_0x00d9:
                        int r8 = r6.length     // Catch:{ IOException -> 0x00f5 }
                        if (r7 == r8) goto L_0x00f5
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r8 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this     // Catch:{ IOException -> 0x00f5 }
                        cn.sherlock.javax.sound.sampled.AudioInputStream r8 = r8.stream     // Catch:{ IOException -> 0x00f5 }
                        int r9 = r6.length     // Catch:{ IOException -> 0x00f5 }
                        int r9 = r9 - r7
                        int r8 = r8.read(r6, r7, r9)     // Catch:{ IOException -> 0x00f5 }
                        if (r8 < 0) goto L_0x00ef
                        if (r8 != 0) goto L_0x00ed
                        java.lang.Thread.yield()     // Catch:{ IOException -> 0x00f5 }
                    L_0x00ed:
                        int r7 = r7 + r8
                        goto L_0x00d9
                    L_0x00ef:
                        java.io.EOFException r6 = new java.io.EOFException     // Catch:{ IOException -> 0x00f5 }
                        r6.<init>()     // Catch:{ IOException -> 0x00f5 }
                        throw r6     // Catch:{ IOException -> 0x00f5 }
                    L_0x00f5:
                        cn.sherlock.com.sun.media.sound.SoftJitterCorrector$JitterStream r6 = cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.this
                        r6.commit()
                    L_0x00fa:
                        if (r5 <= 0) goto L_0x0105
                        int r5 = r5 + -1
                        long r3 = java.lang.System.nanoTime()
                    L_0x0102:
                        long r3 = r3 + r0
                        goto L_0x0029
                    L_0x0105:
                        long r6 = java.lang.System.nanoTime()
                        long r6 = r3 - r6
                        r8 = 0
                        int r8 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                        if (r8 <= 0) goto L_0x0102
                        r8 = 1000000(0xf4240, double:4.940656E-318)
                        long r6 = r6 / r8
                        java.lang.Thread.sleep(r6)     // Catch:{ InterruptedException -> 0x0102 }
                        goto L_0x0102
                    L_0x0119:
                        r0 = move-exception
                        monitor-exit(r7)     // Catch:{ all -> 0x0119 }
                        throw r0
                    L_0x011c:
                        r0 = move-exception
                        monitor-exit(r6)     // Catch:{ all -> 0x011c }
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftJitterCorrector.JitterStream.AnonymousClass1.run():void");
                }
            });
            this.thread = thread2;
            thread2.setDaemon(true);
            this.thread.setPriority(10);
            this.thread.start();
        }

        public void close() throws IOException {
            synchronized (this) {
                this.active = false;
            }
            try {
                this.thread.join();
            } catch (InterruptedException unused) {
            }
            this.stream.close();
        }

        public int read() throws IOException {
            byte[] bArr = new byte[1];
            if (read(bArr) == -1) {
                return -1;
            }
            return bArr[0] & 255;
        }

        public void fillBuffer() {
            this.bbuffer = nextReadBuffer();
            this.bbuffer_pos = 0;
        }

        public int read(byte[] bArr, int i, int i2) {
            if (this.bbuffer == null) {
                fillBuffer();
            }
            int length = this.bbuffer.length;
            int i3 = i + i2;
            while (i < i3) {
                if (available() == 0) {
                    fillBuffer();
                } else {
                    byte[] bArr2 = this.bbuffer;
                    int i4 = this.bbuffer_pos;
                    while (i < i3 && i4 < length) {
                        bArr[i] = bArr2[i4];
                        i++;
                        i4++;
                    }
                    this.bbuffer_pos = i4;
                }
            }
            return i2;
        }

        public int available() {
            return this.bbuffer.length - this.bbuffer_pos;
        }
    }

    public SoftJitterCorrector(AudioInputStream audioInputStream, int i, int i2) {
        super(new JitterStream(audioInputStream, i, i2), audioInputStream.getFormat(), audioInputStream.getFrameLength());
    }
}
