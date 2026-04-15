package com.google.android.gms.internal.play_billing;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzfp {
    static final long zza = ((long) zzz(byte[].class));
    static final boolean zzb;
    private static final Unsafe zzc;
    private static final Class zzd = Memory.class;
    private static final boolean zze;
    private static final zzfo zzf;
    private static final boolean zzg;
    private static final boolean zzh;

    /* JADX WARNING: Removed duplicated region for block: B:19:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0150  */
    static {
        /*
            sun.misc.Unsafe r0 = zzg()
            zzc = r0
            int r1 = com.google.android.gms.internal.play_billing.zzbb.zza
            java.lang.Class<libcore.io.Memory> r1 = libcore.io.Memory.class
            zzd = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = zzv(r1)
            zze = r1
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = zzv(r2)
            r3 = 0
            if (r0 != 0) goto L_0x001e
            goto L_0x002d
        L_0x001e:
            if (r1 == 0) goto L_0x0026
            com.google.android.gms.internal.play_billing.zzfn r3 = new com.google.android.gms.internal.play_billing.zzfn
            r3.<init>(r0)
            goto L_0x002d
        L_0x0026:
            if (r2 == 0) goto L_0x002d
            com.google.android.gms.internal.play_billing.zzfm r3 = new com.google.android.gms.internal.play_billing.zzfm
            r3.<init>(r0)
        L_0x002d:
            zzf = r3
            java.lang.String r0 = "getLong"
            java.lang.String r1 = "objectFieldOffset"
            r2 = 2
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x003a
        L_0x0038:
            r3 = r5
            goto L_0x0064
        L_0x003a:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x005f }
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x005f }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x005f }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x005f }
            r6[r4] = r7     // Catch:{ all -> 0x005f }
            r3.getMethod(r0, r6)     // Catch:{ all -> 0x005f }
            java.lang.reflect.Field r3 = zzB()     // Catch:{ all -> 0x005f }
            if (r3 != 0) goto L_0x005d
            goto L_0x0038
        L_0x005d:
            r3 = r4
            goto L_0x0064
        L_0x005f:
            r3 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.play_billing.zzfp.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r3.toString()))
            goto L_0x0038
        L_0x0064:
            zzg = r3
            com.google.android.gms.internal.play_billing.zzfo r3 = zzf
            if (r3 != 0) goto L_0x006d
        L_0x006a:
            r0 = r5
            goto L_0x00fb
        L_0x006d:
            sun.misc.Unsafe r3 = r3.zza
            java.lang.Class r3 = r3.getClass()     // Catch:{ all -> 0x00f5 }
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.reflect.Field> r7 = java.lang.reflect.Field.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "arrayBaseOffset"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "arrayIndexScale"
            java.lang.Class[] r6 = new java.lang.Class[r4]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Class> r7 = java.lang.Class.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "getInt"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r6[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r6[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r6)     // Catch:{ all -> 0x00f5 }
            java.lang.String r1 = "putInt"
            r6 = 3
            java.lang.Class[] r7 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            r7[r5] = r8     // Catch:{ all -> 0x00f5 }
            java.lang.Class r8 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r7[r4] = r8     // Catch:{ all -> 0x00f5 }
            java.lang.Class r8 = java.lang.Integer.TYPE     // Catch:{ all -> 0x00f5 }
            r7[r2] = r8     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r1, r7)     // Catch:{ all -> 0x00f5 }
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "putLong"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r1[r2] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "getObject"
            java.lang.Class[] r1 = new java.lang.Class[r2]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r7 = java.lang.Object.class
            r1[r5] = r7     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.String r0 = "putObject"
            java.lang.Class[] r1 = new java.lang.Class[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r6 = java.lang.Object.class
            r1[r5] = r6     // Catch:{ all -> 0x00f5 }
            java.lang.Class r7 = java.lang.Long.TYPE     // Catch:{ all -> 0x00f5 }
            r1[r4] = r7     // Catch:{ all -> 0x00f5 }
            r1[r2] = r6     // Catch:{ all -> 0x00f5 }
            r3.getMethod(r0, r1)     // Catch:{ all -> 0x00f5 }
            r0 = r4
            goto L_0x00fb
        L_0x00f5:
            r0 = move-exception
            java.util.logging.Logger.getLogger(com.google.android.gms.internal.play_billing.zzfp.class.getName()).logp(java.util.logging.Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(r0.toString()))
            goto L_0x006a
        L_0x00fb:
            zzh = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzz(r0)
            long r0 = (long) r0
            zza = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<int[]> r0 = int[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<long[]> r0 = long[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<float[]> r0 = float[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<double[]> r0 = double[].class
            zzz(r0)
            zzA(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzz(r0)
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            zzA(r0)
            java.lang.reflect.Field r0 = zzB()
            if (r0 == 0) goto L_0x0147
            com.google.android.gms.internal.play_billing.zzfo r1 = zzf
            if (r1 == 0) goto L_0x0147
            sun.misc.Unsafe r1 = r1.zza
            r1.objectFieldOffset(r0)
        L_0x0147:
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x0150
            goto L_0x0151
        L_0x0150:
            r4 = r5
        L_0x0151:
            zzb = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.play_billing.zzfp.<clinit>():void");
    }

    private zzfp() {
    }

    private static int zzA(Class cls) {
        if (zzh) {
            return zzf.zza.arrayIndexScale(cls);
        }
        return -1;
    }

    private static Field zzB() {
        int i = zzbb.zza;
        Field zzC = zzC(Buffer.class, "effectiveDirectAddress");
        if (zzC != null) {
            return zzC;
        }
        Field zzC2 = zzC(Buffer.class, "address");
        if (zzC2 == null || zzC2.getType() != Long.TYPE) {
            return null;
        }
        return zzC2;
    }

    private static Field zzC(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void zzD(Object obj, long j, byte b) {
        zzfo zzfo = zzf;
        long j2 = -4 & j;
        int i = zzfo.zza.getInt(obj, j2);
        int i2 = ((~((int) j)) & 3) << 3;
        zzfo.zza.putInt(obj, j2, ((255 & b) << i2) | (i & (~(255 << i2))));
    }

    /* access modifiers changed from: private */
    public static void zzE(Object obj, long j, byte b) {
        zzfo zzfo = zzf;
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zzfo.zza.putInt(obj, j2, ((255 & b) << i) | (zzfo.zza.getInt(obj, j2) & (~(255 << i))));
    }

    static double zza(Object obj, long j) {
        return zzf.zza(obj, j);
    }

    static float zzb(Object obj, long j) {
        return zzf.zzb(obj, j);
    }

    static int zzc(Object obj, long j) {
        return zzf.zza.getInt(obj, j);
    }

    static long zzd(Object obj, long j) {
        return zzf.zza.getLong(obj, j);
    }

    static Object zze(Class cls) {
        try {
            return zzc.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    static Object zzf(Object obj, long j) {
        return zzf.zza.getObject(obj, j);
    }

    static Unsafe zzg() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfl());
        } catch (Throwable unused) {
            return null;
        }
    }

    static void zzm(Object obj, long j, boolean z) {
        zzf.zzc(obj, j, z);
    }

    static void zzn(byte[] bArr, long j, byte b) {
        zzf.zzd(bArr, zza + j, b);
    }

    static void zzo(Object obj, long j, double d) {
        zzf.zze(obj, j, d);
    }

    static void zzp(Object obj, long j, float f) {
        zzf.zzf(obj, j, f);
    }

    static void zzq(Object obj, long j, int i) {
        zzf.zza.putInt(obj, j, i);
    }

    static void zzr(Object obj, long j, long j2) {
        zzf.zza.putLong(obj, j, j2);
    }

    static void zzs(Object obj, long j, Object obj2) {
        zzf.zza.putObject(obj, j, obj2);
    }

    static /* bridge */ /* synthetic */ boolean zzt(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) (((~j) & 3) << 3))) & 255)) != 0;
    }

    static /* bridge */ /* synthetic */ boolean zzu(Object obj, long j) {
        return ((byte) ((zzf.zza.getInt(obj, -4 & j) >>> ((int) ((j & 3) << 3))) & 255)) != 0;
    }

    static boolean zzv(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        int i = zzbb.zza;
        try {
            Class cls3 = zzd;
            cls3.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls3.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls3.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            Class cls4 = Integer.TYPE;
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls4, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static boolean zzw(Object obj, long j) {
        return zzf.zzg(obj, j);
    }

    static boolean zzx() {
        return zzh;
    }

    static boolean zzy() {
        return zzg;
    }

    private static int zzz(Class cls) {
        if (zzh) {
            return zzf.zza.arrayBaseOffset(cls);
        }
        return -1;
    }
}
