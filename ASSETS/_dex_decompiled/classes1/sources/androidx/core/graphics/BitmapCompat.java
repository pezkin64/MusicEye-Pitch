package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.os.Build;

public final class BitmapCompat {
    public static int sizeAtStep(int i, int i2, int i3, int i4) {
        return i3 == 0 ? i2 : i3 > 0 ? i * (1 << (i4 - i3)) : i2 << ((-i3) - 1);
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        return Api17Impl.hasMipMap(bitmap);
    }

    public static void setHasMipMap(Bitmap bitmap, boolean z) {
        Api17Impl.setHasMipMap(bitmap, z);
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        return Api19Impl.getAllocationByteCount(bitmap);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01a3, code lost:
        if (androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r10) == false) goto L_0x01b5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap createScaledBitmap(android.graphics.Bitmap r21, int r22, int r23, android.graphics.Rect r24, boolean r25) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            if (r1 <= 0) goto L_0x01ff
            if (r2 <= 0) goto L_0x01ff
            if (r3 == 0) goto L_0x0035
            boolean r4 = r3.isEmpty()
            if (r4 != 0) goto L_0x002d
            int r4 = r3.left
            if (r4 < 0) goto L_0x002d
            int r4 = r3.right
            int r5 = r0.getWidth()
            if (r4 > r5) goto L_0x002d
            int r4 = r3.top
            if (r4 < 0) goto L_0x002d
            int r4 = r3.bottom
            int r5 = r0.getHeight()
            if (r4 > r5) goto L_0x002d
            goto L_0x0035
        L_0x002d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "srcRect must be contained by srcBm!"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 27
            if (r4 < r5) goto L_0x0040
            android.graphics.Bitmap r4 = androidx.core.graphics.BitmapCompat.Api27Impl.copyBitmapIfHardware(r0)
            goto L_0x0041
        L_0x0040:
            r4 = r0
        L_0x0041:
            if (r3 == 0) goto L_0x0048
            int r6 = r3.width()
            goto L_0x004c
        L_0x0048:
            int r6 = r0.getWidth()
        L_0x004c:
            if (r3 == 0) goto L_0x0053
            int r7 = r3.height()
            goto L_0x0057
        L_0x0053:
            int r7 = r0.getHeight()
        L_0x0057:
            float r8 = (float) r1
            float r9 = (float) r6
            float r8 = r8 / r9
            float r9 = (float) r2
            float r10 = (float) r7
            float r9 = r9 / r10
            if (r3 == 0) goto L_0x0062
            int r11 = r3.left
            goto L_0x0063
        L_0x0062:
            r11 = 0
        L_0x0063:
            if (r3 == 0) goto L_0x0068
            int r3 = r3.top
            goto L_0x0069
        L_0x0068:
            r3 = 0
        L_0x0069:
            r12 = 1
            if (r11 != 0) goto L_0x008c
            if (r3 != 0) goto L_0x008c
            int r13 = r0.getWidth()
            if (r1 != r13) goto L_0x008c
            int r13 = r0.getHeight()
            if (r2 != r13) goto L_0x008c
            boolean r1 = r0.isMutable()
            if (r1 == 0) goto L_0x008b
            if (r0 != r4) goto L_0x008b
            android.graphics.Bitmap$Config r1 = r0.getConfig()
            android.graphics.Bitmap r0 = r0.copy(r1, r12)
            return r0
        L_0x008b:
            return r4
        L_0x008c:
            android.graphics.Paint r13 = new android.graphics.Paint
            r13.<init>(r12)
            r13.setFilterBitmap(r12)
            int r14 = android.os.Build.VERSION.SDK_INT
            r15 = 29
            if (r14 < r15) goto L_0x009e
            androidx.core.graphics.BitmapCompat.Api29Impl.setPaintBlendMode(r13)
            goto L_0x00a8
        L_0x009e:
            android.graphics.PorterDuffXfermode r14 = new android.graphics.PorterDuffXfermode
            android.graphics.PorterDuff$Mode r15 = android.graphics.PorterDuff.Mode.SRC
            r14.<init>(r15)
            r13.setXfermode(r14)
        L_0x00a8:
            if (r6 != r1) goto L_0x00c1
            if (r7 != r2) goto L_0x00c1
            android.graphics.Bitmap$Config r0 = r4.getConfig()
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r1, r2, r0)
            android.graphics.Canvas r1 = new android.graphics.Canvas
            r1.<init>(r0)
            int r2 = -r11
            float r2 = (float) r2
            int r3 = -r3
            float r3 = (float) r3
            r1.drawBitmap(r4, r2, r3, r13)
            return r0
        L_0x00c1:
            r14 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r14 = java.lang.Math.log(r14)
            r16 = 1065353216(0x3f800000, float:1.0)
            int r17 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r17 <= 0) goto L_0x00da
            r18 = r11
            double r10 = (double) r8
            double r10 = java.lang.Math.log(r10)
            double r10 = r10 / r14
            double r10 = java.lang.Math.ceil(r10)
            goto L_0x00e6
        L_0x00da:
            r18 = r11
            double r10 = (double) r8
            double r10 = java.lang.Math.log(r10)
            double r10 = r10 / r14
            double r10 = java.lang.Math.floor(r10)
        L_0x00e6:
            int r8 = (int) r10
            int r10 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r10 <= 0) goto L_0x00f6
            double r9 = (double) r9
            double r9 = java.lang.Math.log(r9)
            double r9 = r9 / r14
            double r9 = java.lang.Math.ceil(r9)
            goto L_0x0100
        L_0x00f6:
            double r9 = (double) r9
            double r9 = java.lang.Math.log(r9)
            double r9 = r9 / r14
            double r9 = java.lang.Math.floor(r9)
        L_0x0100:
            int r9 = (int) r9
            if (r25 == 0) goto L_0x0136
            int r10 = android.os.Build.VERSION.SDK_INT
            if (r10 < r5) goto L_0x0136
            boolean r10 = androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r0)
            if (r10 != 0) goto L_0x0136
            if (r8 <= 0) goto L_0x0114
            int r10 = sizeAtStep(r6, r1, r12, r8)
            goto L_0x0115
        L_0x0114:
            r10 = r6
        L_0x0115:
            if (r9 <= 0) goto L_0x011c
            int r11 = sizeAtStep(r7, r2, r12, r9)
            goto L_0x011d
        L_0x011c:
            r11 = r7
        L_0x011d:
            android.graphics.Bitmap r10 = androidx.core.graphics.BitmapCompat.Api27Impl.createBitmapWithSourceColorspace(r10, r11, r0, r12)
            android.graphics.Canvas r11 = new android.graphics.Canvas
            r11.<init>(r10)
            r14 = r18
            int r14 = -r14
            float r14 = (float) r14
            int r3 = -r3
            float r3 = (float) r3
            r11.drawBitmap(r4, r14, r3, r13)
            r3 = r10
            r10 = r4
            r4 = r3
            r14 = r12
            r3 = 0
            r11 = 0
            goto L_0x013b
        L_0x0136:
            r14 = r18
            r10 = 0
            r11 = r14
            r14 = 0
        L_0x013b:
            android.graphics.Rect r15 = new android.graphics.Rect
            r15.<init>(r11, r3, r6, r7)
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            r11 = r8
            r16 = r9
        L_0x0148:
            if (r11 != 0) goto L_0x0155
            if (r16 == 0) goto L_0x014d
            goto L_0x0155
        L_0x014d:
            if (r10 == r0) goto L_0x0154
            if (r10 == 0) goto L_0x0154
            r10.recycle()
        L_0x0154:
            return r4
        L_0x0155:
            if (r11 >= 0) goto L_0x015a
            int r11 = r11 + 1
            goto L_0x015e
        L_0x015a:
            if (r11 <= 0) goto L_0x015e
            int r11 = r11 + -1
        L_0x015e:
            if (r16 >= 0) goto L_0x0163
            int r16 = r16 + 1
            goto L_0x0167
        L_0x0163:
            if (r16 <= 0) goto L_0x0167
            int r16 = r16 + -1
        L_0x0167:
            r12 = r16
            int r5 = sizeAtStep(r6, r1, r11, r8)
            r18 = r11
            int r11 = sizeAtStep(r7, r2, r12, r9)
            r19 = r12
            r12 = 0
            r3.set(r12, r12, r5, r11)
            if (r18 != 0) goto L_0x017f
            if (r19 != 0) goto L_0x017f
            r5 = 1
            goto L_0x0180
        L_0x017f:
            r5 = r12
        L_0x0180:
            if (r10 == 0) goto L_0x0190
            int r11 = r10.getWidth()
            if (r11 != r1) goto L_0x0190
            int r11 = r10.getHeight()
            if (r11 != r2) goto L_0x0190
            r11 = 1
            goto L_0x0191
        L_0x0190:
            r11 = r12
        L_0x0191:
            if (r10 == 0) goto L_0x01b3
            if (r10 == r0) goto L_0x01b3
            if (r25 == 0) goto L_0x01a6
            int r12 = android.os.Build.VERSION.SDK_INT
            r20 = r5
            r5 = 27
            if (r12 < r5) goto L_0x01a8
            boolean r5 = androidx.core.graphics.BitmapCompat.Api27Impl.isAlreadyF16AndLinear(r10)
            if (r5 == 0) goto L_0x01b5
            goto L_0x01a8
        L_0x01a6:
            r20 = r5
        L_0x01a8:
            if (r20 == 0) goto L_0x01af
            if (r11 == 0) goto L_0x01b5
            if (r14 == 0) goto L_0x01af
            goto L_0x01b5
        L_0x01af:
            r5 = r10
            r12 = 27
            goto L_0x01ea
        L_0x01b3:
            r20 = r5
        L_0x01b5:
            if (r10 == r0) goto L_0x01bc
            if (r10 == 0) goto L_0x01bc
            r10.recycle()
        L_0x01bc:
            if (r18 <= 0) goto L_0x01c0
            r5 = r14
            goto L_0x01c2
        L_0x01c0:
            r5 = r18
        L_0x01c2:
            int r5 = sizeAtStep(r6, r1, r5, r8)
            if (r19 <= 0) goto L_0x01ca
            r10 = r14
            goto L_0x01cc
        L_0x01ca:
            r10 = r19
        L_0x01cc:
            int r10 = sizeAtStep(r7, r2, r10, r9)
            int r11 = android.os.Build.VERSION.SDK_INT
            r12 = 27
            if (r11 < r12) goto L_0x01e2
            if (r25 == 0) goto L_0x01dc
            if (r20 != 0) goto L_0x01dc
            r11 = 1
            goto L_0x01dd
        L_0x01dc:
            r11 = 0
        L_0x01dd:
            android.graphics.Bitmap r5 = androidx.core.graphics.BitmapCompat.Api27Impl.createBitmapWithSourceColorspace(r5, r10, r0, r11)
            goto L_0x01ea
        L_0x01e2:
            android.graphics.Bitmap$Config r11 = r4.getConfig()
            android.graphics.Bitmap r5 = android.graphics.Bitmap.createBitmap(r5, r10, r11)
        L_0x01ea:
            android.graphics.Canvas r10 = new android.graphics.Canvas
            r10.<init>(r5)
            r10.drawBitmap(r4, r15, r3, r13)
            r15.set(r3)
            r10 = r4
            r4 = r5
            r5 = r12
            r11 = r18
            r16 = r19
            r12 = 1
            goto L_0x0148
        L_0x01ff:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "dstW and dstH must be > 0!"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.BitmapCompat.createScaledBitmap(android.graphics.Bitmap, int, int, android.graphics.Rect, boolean):android.graphics.Bitmap");
    }

    private BitmapCompat() {
    }

    static class Api17Impl {
        private Api17Impl() {
        }

        static boolean hasMipMap(Bitmap bitmap) {
            return bitmap.hasMipMap();
        }

        static void setHasMipMap(Bitmap bitmap, boolean z) {
            bitmap.setHasMipMap(z);
        }
    }

    static class Api19Impl {
        private Api19Impl() {
        }

        static int getAllocationByteCount(Bitmap bitmap) {
            return bitmap.getAllocationByteCount();
        }
    }

    static class Api27Impl {
        private Api27Impl() {
        }

        static Bitmap createBitmapWithSourceColorspace(int i, int i2, Bitmap bitmap, boolean z) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                config = Bitmap.Config.ARGB_8888;
                if (Build.VERSION.SDK_INT >= 31) {
                    config = Api31Impl.getHardwareBitmapConfig(bitmap);
                }
            }
            return Bitmap.createBitmap(i, i2, config, bitmap.hasAlpha(), colorSpace);
        }

        static boolean isAlreadyF16AndLinear(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }

        static Bitmap copyBitmapIfHardware(Bitmap bitmap) {
            if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
                return bitmap;
            }
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            if (Build.VERSION.SDK_INT >= 31) {
                config = Api31Impl.getHardwareBitmapConfig(bitmap);
            }
            return bitmap.copy(config, true);
        }
    }

    static class Api29Impl {
        private Api29Impl() {
        }

        static void setPaintBlendMode(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    static class Api31Impl {
        private Api31Impl() {
        }

        static Bitmap.Config getHardwareBitmapConfig(Bitmap bitmap) {
            if (bitmap.getHardwareBuffer().getFormat() == 22) {
                return Bitmap.Config.RGBA_F16;
            }
            return Bitmap.Config.ARGB_8888;
        }
    }
}
