package cn.sherlock.com.sun.media.sound;

import cn.sherlock.javax.sound.sampled.AudioFormat;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;

public abstract class AudioFloatConverter {
    public static final AudioFormat.Encoding PCM_FLOAT = new AudioFormat.Encoding("PCM_FLOAT");
    private AudioFormat format;

    public abstract byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3);

    public abstract float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3);

    private static class AudioFloatLSBFilter extends AudioFloatConverter {
        private AudioFloatConverter converter;
        private final byte mask;
        private byte[] mask_buffer;
        private final int offset;
        private final int stepsize;

        public AudioFloatLSBFilter(AudioFloatConverter audioFloatConverter, AudioFormat audioFormat) {
            int sampleSizeInBits = audioFormat.getSampleSizeInBits();
            boolean isBigEndian = audioFormat.isBigEndian();
            this.converter = audioFloatConverter;
            int i = (sampleSizeInBits + 7) / 8;
            this.stepsize = i;
            this.offset = isBigEndian ? i - 1 : 0;
            int i2 = sampleSizeInBits % 8;
            if (i2 == 0) {
                this.mask = 0;
            } else if (i2 == 1) {
                this.mask = Byte.MIN_VALUE;
            } else if (i2 == 2) {
                this.mask = -64;
            } else if (i2 == 3) {
                this.mask = -32;
            } else if (i2 == 4) {
                this.mask = -16;
            } else if (i2 == 5) {
                this.mask = -8;
            } else if (i2 == 6) {
                this.mask = -4;
            } else if (i2 == 7) {
                this.mask = -2;
            } else {
                this.mask = -1;
            }
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2;
            byte[] bArr2 = bArr;
            int i5 = i3;
            byte[] byteArray = this.converter.toByteArray(fArr, i, i4, bArr2, i5);
            int i6 = i4 * this.stepsize;
            int i7 = i5 + this.offset;
            while (i7 < i6) {
                bArr2[i7] = (byte) (bArr2[i7] & this.mask);
                i7 += this.stepsize;
            }
            return byteArray;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            byte[] bArr2 = this.mask_buffer;
            if (bArr2 == null || bArr2.length < bArr.length) {
                this.mask_buffer = new byte[bArr.length];
            }
            System.arraycopy(bArr, 0, this.mask_buffer, 0, bArr.length);
            int i4 = this.stepsize * i3;
            int i5 = this.offset + i;
            while (i5 < i4) {
                byte[] bArr3 = this.mask_buffer;
                bArr3[i5] = (byte) (bArr3[i5] & this.mask);
                i5 += this.stepsize;
            }
            return this.converter.toFloatArray(this.mask_buffer, i, fArr, i2, i3);
        }
    }

    private static class AudioFloatConversion64L extends AudioFloatConverter {
        ByteBuffer bytebuffer;
        double[] double_buff;
        DoubleBuffer floatbuffer;

        private AudioFloatConversion64L() {
            this.bytebuffer = null;
            this.floatbuffer = null;
            this.double_buff = null;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asDoubleBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i3 + i2) {
                this.double_buff = new double[(i3 + i2)];
            }
            this.floatbuffer.get(this.double_buff, i2, i3);
            int i5 = i3 + i2;
            while (i2 < i5) {
                fArr[i2] = (float) this.double_buff[i2];
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asDoubleBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i + i2) {
                this.double_buff = new double[(i + i2)];
            }
            int i5 = i + i2;
            for (int i6 = i; i6 < i5; i6++) {
                this.double_buff[i6] = (double) fArr[i6];
            }
            this.floatbuffer.put(this.double_buff, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion64B extends AudioFloatConverter {
        ByteBuffer bytebuffer;
        double[] double_buff;
        DoubleBuffer floatbuffer;

        private AudioFloatConversion64B() {
            this.bytebuffer = null;
            this.floatbuffer = null;
            this.double_buff = null;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asDoubleBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i3 + i2) {
                this.double_buff = new double[(i3 + i2)];
            }
            this.floatbuffer.get(this.double_buff, i2, i3);
            int i5 = i3 + i2;
            while (i2 < i5) {
                fArr[i2] = (float) this.double_buff[i2];
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 8;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asDoubleBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            double[] dArr = this.double_buff;
            if (dArr == null || dArr.length < i + i2) {
                this.double_buff = new double[(i + i2)];
            }
            int i5 = i + i2;
            for (int i6 = i; i6 < i5; i6++) {
                this.double_buff[i6] = (double) fArr[i6];
            }
            this.floatbuffer.put(this.double_buff, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion32L extends AudioFloatConverter {
        ByteBuffer bytebuffer;
        FloatBuffer floatbuffer;

        private AudioFloatConversion32L() {
            this.bytebuffer = null;
            this.floatbuffer = null;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asFloatBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            this.floatbuffer.get(fArr, i2, i3);
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.LITTLE_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asFloatBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            this.floatbuffer.put(fArr, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion32B extends AudioFloatConverter {
        ByteBuffer bytebuffer;
        FloatBuffer floatbuffer;

        private AudioFloatConversion32B() {
            this.bytebuffer = null;
            this.floatbuffer = null;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asFloatBuffer();
            }
            this.bytebuffer.position(0);
            this.floatbuffer.position(0);
            this.bytebuffer.put(bArr, i, i4);
            this.floatbuffer.get(fArr, i2, i3);
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 * 4;
            ByteBuffer byteBuffer = this.bytebuffer;
            if (byteBuffer == null || byteBuffer.capacity() < i4) {
                ByteBuffer order = ByteBuffer.allocate(i4).order(ByteOrder.BIG_ENDIAN);
                this.bytebuffer = order;
                this.floatbuffer = order.asFloatBuffer();
            }
            this.floatbuffer.position(0);
            this.bytebuffer.position(0);
            this.floatbuffer.put(fArr, i, i2);
            this.bytebuffer.get(bArr, i3, i4);
            return bArr;
        }
    }

    private static class AudioFloatConversion8S extends AudioFloatConverter {
        private AudioFloatConversion8S() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = ((float) bArr[i]) * 0.007874016f;
                i4++;
                i2++;
                i++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                bArr[i3] = (byte) ((int) (fArr[i] * 127.0f));
                i4++;
                i3++;
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion8U extends AudioFloatConverter {
        private AudioFloatConversion8U() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                fArr[i2] = ((float) ((bArr[i] & 255) - Byte.MAX_VALUE)) * 0.007874016f;
                i4++;
                i2++;
                i++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                bArr[i3] = (byte) ((int) ((fArr[i] * 127.0f) + 127.0f));
                i4++;
                i3++;
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16SL extends AudioFloatConverter {
        private AudioFloatConversion16SL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = i3 + i2;
            while (i2 < i4) {
                int i5 = i + 1;
                i += 2;
                fArr[i2] = ((float) ((short) ((bArr[i5] << 8) | (bArr[i] & 255)))) * 3.051851E-5f;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = i2 + i;
            while (i < i4) {
                int i5 = (int) (((double) fArr[i]) * 32767.0d);
                int i6 = i3 + 1;
                bArr[i3] = (byte) i5;
                i3 += 2;
                bArr[i6] = (byte) (i5 >>> 8);
                i++;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16SB extends AudioFloatConverter {
        private AudioFloatConversion16SB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                i += 2;
                fArr[i2] = ((float) ((short) ((bArr[i5] & 255) | (bArr[i] << 8)))) * 3.051851E-5f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (((double) fArr[i]) * 32767.0d);
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 8);
                i3 += 2;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16UL extends AudioFloatConverter {
        private AudioFloatConversion16UL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                i += 2;
                fArr[i2] = ((float) ((((bArr[i5] & 255) << 8) | (bArr[i] & 255)) + Ascii.SOH)) * 3.051851E-5f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 32767.0d)) + 32767;
                int i7 = i3 + 1;
                bArr[i3] = (byte) i6;
                i3 += 2;
                bArr[i7] = (byte) (i6 >>> 8);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion16UB extends AudioFloatConverter {
        private AudioFloatConversion16UB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 1;
                i += 2;
                fArr[i2] = ((float) (((bArr[i5] & 255) | ((bArr[i] & 255) << 8)) + Ascii.SOH)) * 3.051851E-5f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 32767.0d)) + 32767;
                int i7 = i3 + 1;
                bArr[i3] = (byte) (i6 >>> 8);
                i3 += 2;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24SL extends AudioFloatConverter {
        private AudioFloatConversion24SL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 2;
                int i6 = (bArr[i + 1] & 255) << 8;
                i += 3;
                int i7 = i6 | (bArr[i] & 255) | ((bArr[i5] & 255) << Ascii.DLE);
                if (i7 > 8388607) {
                    i7 -= 16777216;
                }
                fArr[i2] = ((float) i7) * 1.192093E-7f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 8388607.0f);
                if (i6 < 0) {
                    i6 += 16777216;
                }
                bArr[i3] = (byte) i6;
                int i7 = i3 + 2;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                i3 += 3;
                bArr[i7] = (byte) (i6 >>> 16);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24SB extends AudioFloatConverter {
        private AudioFloatConversion24SB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = (bArr[i] & 255) << Ascii.DLE;
                int i6 = i + 2;
                i += 3;
                int i7 = ((bArr[i + 1] & 255) << 8) | i5 | (bArr[i6] & 255);
                if (i7 > 8388607) {
                    i7 -= 16777216;
                }
                fArr[i2] = ((float) i7) * 1.192093E-7f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 8388607.0f);
                if (i6 < 0) {
                    i6 += 16777216;
                }
                bArr[i3] = (byte) (i6 >>> 16);
                int i7 = i3 + 2;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                i3 += 3;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24UL extends AudioFloatConverter {
        private AudioFloatConversion24UL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + 2;
                int i6 = (bArr[i + 1] & 255) << 8;
                i += 3;
                fArr[i2] = ((float) (((i6 | (bArr[i] & 255)) | ((bArr[i5] & 255) << Ascii.DLE)) - 8388607)) * 1.192093E-7f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 8388607.0f)) + 8388607;
                bArr[i3] = (byte) i6;
                int i7 = i3 + 2;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                i3 += 3;
                bArr[i7] = (byte) (i6 >>> 16);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion24UB extends AudioFloatConverter {
        private AudioFloatConversion24UB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = (bArr[i] & 255) << Ascii.DLE;
                int i6 = i + 2;
                i += 3;
                fArr[i2] = ((float) (((((bArr[i + 1] & 255) << 8) | i5) | (bArr[i6] & 255)) - 8388607)) * 1.192093E-7f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 8388607.0f)) + 8388607;
                bArr[i3] = (byte) (i6 >>> 16);
                int i7 = i3 + 2;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                i3 += 3;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32SL extends AudioFloatConverter {
        private AudioFloatConversion32SL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
                int i5 = i + 3;
                i += 4;
                fArr[i2] = ((float) (b | ((bArr[i + 2] & 255) << Ascii.DLE) | ((bArr[i5] & 255) << Ascii.CAN))) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.14748365E9f);
                bArr[i3] = (byte) i6;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 16);
                i3 += 4;
                bArr[i7] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32SB extends AudioFloatConverter {
        private AudioFloatConversion32SB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN);
                int i5 = i + 3;
                i += 4;
                fArr[i2] = ((float) (b | ((bArr[i + 2] & 255) << 8) | (bArr[i5] & 255))) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.14748365E9f);
                bArr[i3] = (byte) (i6 >>> 24);
                bArr[i3 + 1] = (byte) (i6 >>> 16);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 8);
                i3 += 4;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32UL extends AudioFloatConverter {
        private AudioFloatConversion32UL() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
                int i5 = i + 3;
                i += 4;
                fArr[i2] = ((float) (((b | ((bArr[i + 2] & 255) << Ascii.DLE)) | ((bArr[i5] & 255) << Ascii.CAN)) - 2147483647)) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.14748365E9f)) + Integer.MAX_VALUE;
                bArr[i3] = (byte) i6;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 16);
                i3 += 4;
                bArr[i7] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32UB extends AudioFloatConverter {
        private AudioFloatConversion32UB() {
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN);
                int i5 = i + 3;
                i += 4;
                fArr[i2] = ((float) (((b | ((bArr[i + 2] & 255) << 8)) | (bArr[i5] & 255)) - 2147483647)) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.14748365E9f)) + Integer.MAX_VALUE;
                bArr[i3] = (byte) (i6 >>> 24);
                bArr[i3 + 1] = (byte) (i6 >>> 16);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 8);
                i3 += 4;
                bArr[i7] = (byte) i6;
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xSL extends AudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xSL(int i) {
            this.xbytes = i;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + this.xbytes;
                byte b = ((bArr[i5 + 1] & 255) << 8) | (bArr[i5] & 255);
                int i6 = i5 + 3;
                i = i5 + 4;
                fArr[i2] = ((float) (b | ((bArr[i5 + 2] & 255) << Ascii.DLE) | ((bArr[i6] & 255) << Ascii.CAN))) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.14748365E9f);
                int i7 = 0;
                while (i7 < this.xbytes) {
                    bArr[i3] = 0;
                    i7++;
                    i3++;
                }
                bArr[i3] = (byte) i6;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                int i8 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 16);
                i3 += 4;
                bArr[i8] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xSB extends AudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xSB(int i) {
            this.xbytes = i;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
                i = i + 4 + this.xbytes;
                fArr[i2] = ((float) b) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = (int) (fArr[i] * 2.14748365E9f);
                bArr[i3] = (byte) (i6 >>> 24);
                bArr[i3 + 1] = (byte) (i6 >>> 16);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 8);
                i3 += 4;
                bArr[i7] = (byte) i6;
                int i8 = 0;
                while (i8 < this.xbytes) {
                    bArr[i3] = 0;
                    i8++;
                    i3++;
                }
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xUL extends AudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xUL(int i) {
            this.xbytes = i;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                int i5 = i + this.xbytes;
                byte b = ((bArr[i5 + 1] & 255) << 8) | (bArr[i5] & 255);
                int i6 = i5 + 3;
                i = i5 + 4;
                fArr[i2] = ((float) (((b | ((bArr[i5 + 2] & 255) << Ascii.DLE)) | ((bArr[i6] & 255) << Ascii.CAN)) - 2147483647)) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (fArr[i] * 2.14748365E9f)) + Integer.MAX_VALUE;
                int i7 = 0;
                while (i7 < this.xbytes) {
                    bArr[i3] = 0;
                    i7++;
                    i3++;
                }
                bArr[i3] = (byte) i6;
                bArr[i3 + 1] = (byte) (i6 >>> 8);
                int i8 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 16);
                i3 += 4;
                bArr[i8] = (byte) (i6 >>> 24);
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    private static class AudioFloatConversion32xUB extends AudioFloatConverter {
        final int xbytes;

        public AudioFloatConversion32xUB(int i) {
            this.xbytes = i;
        }

        public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2, int i3) {
            int i4 = 0;
            while (i4 < i3) {
                byte b = ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 2] & 255) << 8) | (bArr[i + 3] & 255);
                i = i + 4 + this.xbytes;
                fArr[i2] = ((float) (b - 2147483647)) * 4.656613E-10f;
                i4++;
                i2++;
            }
            return fArr;
        }

        public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr, int i3) {
            int i4 = 0;
            while (i4 < i2) {
                int i5 = i + 1;
                int i6 = ((int) (((double) fArr[i]) * 2.147483647E9d)) + Integer.MAX_VALUE;
                bArr[i3] = (byte) (i6 >>> 24);
                bArr[i3 + 1] = (byte) (i6 >>> 16);
                int i7 = i3 + 3;
                bArr[i3 + 2] = (byte) (i6 >>> 8);
                i3 += 4;
                bArr[i7] = (byte) i6;
                int i8 = 0;
                while (i8 < this.xbytes) {
                    bArr[i3] = 0;
                    i8++;
                    i3++;
                }
                i4++;
                i = i5;
            }
            return bArr;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:121:0x0217  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.sherlock.com.sun.media.sound.AudioFloatConverter getConverter(cn.sherlock.javax.sound.sampled.AudioFormat r7) {
        /*
            int r0 = r7.getFrameSize()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            int r0 = r7.getFrameSize()
            int r2 = r7.getSampleSizeInBits()
            int r2 = r2 + 7
            r3 = 8
            int r2 = r2 / r3
            int r4 = r7.getChannels()
            int r2 = r2 * r4
            if (r0 == r2) goto L_0x001d
            return r1
        L_0x001d:
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r0 = r7.getEncoding()
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r2 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED
            boolean r0 = r0.equals(r2)
            r2 = 24
            r4 = 16
            r5 = 32
            if (r0 == 0) goto L_0x00e7
            boolean r0 = r7.isBigEndian()
            if (r0 == 0) goto L_0x008d
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r3) goto L_0x0041
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8S r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8S
            r0.<init>()
            goto L_0x0098
        L_0x0041:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r3) goto L_0x0053
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r4) goto L_0x0053
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16SB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16SB
            r0.<init>()
            goto L_0x0098
        L_0x0053:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r4) goto L_0x0065
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r2) goto L_0x0065
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24SB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24SB
            r0.<init>()
            goto L_0x0098
        L_0x0065:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r2) goto L_0x0077
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r5) goto L_0x0077
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32SB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32SB
            r0.<init>()
            goto L_0x0098
        L_0x0077:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r5) goto L_0x01f0
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xSB r1 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xSB
            int r0 = r7.getSampleSizeInBits()
            int r0 = r0 + 7
            int r0 = r0 / r3
            int r0 = r0 + -4
            r1.<init>(r0)
            goto L_0x01f0
        L_0x008d:
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r3) goto L_0x009b
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8S r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8S
            r0.<init>()
        L_0x0098:
            r1 = r0
            goto L_0x01f0
        L_0x009b:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r3) goto L_0x00ad
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r4) goto L_0x00ad
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16SL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16SL
            r0.<init>()
            goto L_0x0098
        L_0x00ad:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r4) goto L_0x00bf
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r2) goto L_0x00bf
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24SL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24SL
            r0.<init>()
            goto L_0x0098
        L_0x00bf:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r2) goto L_0x00d1
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r5) goto L_0x00d1
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32SL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32SL
            r0.<init>()
            goto L_0x0098
        L_0x00d1:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r5) goto L_0x01f0
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xSL r1 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xSL
            int r0 = r7.getSampleSizeInBits()
            int r0 = r0 + 7
            int r0 = r0 / r3
            int r0 = r0 + -4
            r1.<init>(r0)
            goto L_0x01f0
        L_0x00e7:
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r0 = r7.getEncoding()
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r6 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_UNSIGNED
            boolean r0 = r0.equals(r6)
            if (r0 == 0) goto L_0x01ae
            boolean r0 = r7.isBigEndian()
            if (r0 == 0) goto L_0x0153
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r3) goto L_0x0105
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8U r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8U
            r0.<init>()
            goto L_0x0098
        L_0x0105:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r3) goto L_0x0117
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r4) goto L_0x0117
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16UB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16UB
            r0.<init>()
            goto L_0x0098
        L_0x0117:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r4) goto L_0x012a
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r2) goto L_0x012a
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24UB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24UB
            r0.<init>()
            goto L_0x0098
        L_0x012a:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r2) goto L_0x013d
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r5) goto L_0x013d
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32UB r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32UB
            r0.<init>()
            goto L_0x0098
        L_0x013d:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r5) goto L_0x01f0
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xUB r1 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xUB
            int r0 = r7.getSampleSizeInBits()
            int r0 = r0 + 7
            int r0 = r0 / r3
            int r0 = r0 + -4
            r1.<init>(r0)
            goto L_0x01f0
        L_0x0153:
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r3) goto L_0x0160
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8U r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion8U
            r0.<init>()
            goto L_0x0098
        L_0x0160:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r3) goto L_0x0173
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r4) goto L_0x0173
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16UL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion16UL
            r0.<init>()
            goto L_0x0098
        L_0x0173:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r4) goto L_0x0186
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r2) goto L_0x0186
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24UL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion24UL
            r0.<init>()
            goto L_0x0098
        L_0x0186:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r2) goto L_0x0199
            int r0 = r7.getSampleSizeInBits()
            if (r0 > r5) goto L_0x0199
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32UL r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32UL
            r0.<init>()
            goto L_0x0098
        L_0x0199:
            int r0 = r7.getSampleSizeInBits()
            if (r0 <= r5) goto L_0x01f0
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xUL r1 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32xUL
            int r0 = r7.getSampleSizeInBits()
            int r0 = r0 + 7
            int r0 = r0 / r3
            int r0 = r0 + -4
            r1.<init>(r0)
            goto L_0x01f0
        L_0x01ae:
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r0 = r7.getEncoding()
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r2 = PCM_FLOAT
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x01f0
            int r0 = r7.getSampleSizeInBits()
            if (r0 != r5) goto L_0x01d4
            boolean r0 = r7.isBigEndian()
            if (r0 == 0) goto L_0x01cd
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32B r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32B
            r0.<init>()
            goto L_0x0098
        L_0x01cd:
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32L r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion32L
            r0.<init>()
            goto L_0x0098
        L_0x01d4:
            int r0 = r7.getSampleSizeInBits()
            r2 = 64
            if (r0 != r2) goto L_0x01f0
            boolean r0 = r7.isBigEndian()
            if (r0 == 0) goto L_0x01e9
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion64B r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion64B
            r0.<init>()
            goto L_0x0098
        L_0x01e9:
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion64L r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatConversion64L
            r0.<init>()
            goto L_0x0098
        L_0x01f0:
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r0 = r7.getEncoding()
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r2 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_SIGNED
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0208
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r0 = r7.getEncoding()
            cn.sherlock.javax.sound.sampled.AudioFormat$Encoding r2 = cn.sherlock.javax.sound.sampled.AudioFormat.Encoding.PCM_UNSIGNED
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x0215
        L_0x0208:
            int r0 = r7.getSampleSizeInBits()
            int r0 = r0 % r3
            if (r0 == 0) goto L_0x0215
            cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatLSBFilter r0 = new cn.sherlock.com.sun.media.sound.AudioFloatConverter$AudioFloatLSBFilter
            r0.<init>(r1, r7)
            r1 = r0
        L_0x0215:
            if (r1 == 0) goto L_0x0219
            r1.format = r7
        L_0x0219:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.AudioFloatConverter.getConverter(cn.sherlock.javax.sound.sampled.AudioFormat):cn.sherlock.com.sun.media.sound.AudioFloatConverter");
    }

    public AudioFormat getFormat() {
        return this.format;
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr, int i, int i2) {
        return toFloatArray(bArr, 0, fArr, i, i2);
    }

    public float[] toFloatArray(byte[] bArr, int i, float[] fArr, int i2) {
        return toFloatArray(bArr, i, fArr, 0, i2);
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr, int i) {
        return toFloatArray(bArr, 0, fArr, 0, i);
    }

    public float[] toFloatArray(byte[] bArr, float[] fArr) {
        return toFloatArray(bArr, 0, fArr, 0, fArr.length);
    }

    public byte[] toByteArray(float[] fArr, int i, byte[] bArr, int i2) {
        return toByteArray(fArr, 0, i, bArr, i2);
    }

    public byte[] toByteArray(float[] fArr, int i, int i2, byte[] bArr) {
        return toByteArray(fArr, i, i2, bArr, 0);
    }

    public byte[] toByteArray(float[] fArr, int i, byte[] bArr) {
        return toByteArray(fArr, 0, i, bArr, 0);
    }

    public byte[] toByteArray(float[] fArr, byte[] bArr) {
        return toByteArray(fArr, 0, fArr.length, bArr, 0);
    }
}
