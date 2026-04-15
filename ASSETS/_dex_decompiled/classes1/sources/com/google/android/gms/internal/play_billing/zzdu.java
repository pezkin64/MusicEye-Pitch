package com.google.android.gms.internal.play_billing;

/* compiled from: com.android.billingclient:billing@@7.0.0 */
final class zzdu implements zzep {
    private static final zzea zza = new zzds();
    private final zzea zzb;

    public zzdu() {
        zzea zzea;
        zzea[] zzeaArr = new zzea[2];
        zzeaArr[0] = zzcm.zza();
        try {
            zzea = (zzea) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (Exception unused) {
            zzea = zza;
        }
        zzeaArr[1] = zzea;
        zzdt zzdt = new zzdt(zzeaArr);
        byte[] bArr = zzda.zzd;
        this.zzb = zzdt;
    }

    private static boolean zzb(zzdz zzdz) {
        return zzdz.zzc() + -1 != 1;
    }

    public final zzeo zza(Class cls) {
        zzeq.zzr(cls);
        zzdz zzb2 = this.zzb.zzb(cls);
        if (zzb2.zzb()) {
            if (zzcs.class.isAssignableFrom(cls)) {
                return zzeg.zzc(zzeq.zzn(), zzcg.zzb(), zzb2.zza());
            }
            return zzeg.zzc(zzeq.zzm(), zzcg.zza(), zzb2.zza());
        } else if (!zzcs.class.isAssignableFrom(cls)) {
            Class cls2 = cls;
            if (zzb(zzb2)) {
                return zzef.zzl(cls2, zzb2, zzej.zza(), zzdq.zzc(), zzeq.zzm(), zzcg.zza(), zzdy.zza());
            }
            return zzef.zzl(cls2, zzb2, zzej.zza(), zzdq.zzc(), zzeq.zzm(), (zzce) null, zzdy.zza());
        } else if (!zzb(zzb2)) {
            return zzef.zzl(cls, zzb2, zzej.zzb(), zzdq.zzd(), zzeq.zzn(), (zzce) null, zzdy.zzb());
        } else {
            return zzef.zzl(cls, zzb2, zzej.zzb(), zzdq.zzd(), zzeq.zzn(), zzcg.zzb(), zzdy.zzb());
        }
    }
}
