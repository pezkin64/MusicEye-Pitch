package androidx.media;

import android.content.Context;
import android.media.session.MediaSessionManager;
import androidx.media.MediaSessionManager;
import androidx.media.MediaSessionManagerImplBase;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;

class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
    MediaSessionManager mObject;

    MediaSessionManagerImplApi28(Context context) {
        super(context);
        this.mObject = (MediaSessionManager) context.getSystemService("media_session");
    }

    public boolean isTrustedForMediaControl(MediaSessionManager.RemoteUserInfoImpl remoteUserInfoImpl) {
        return super.isTrustedForMediaControl(remoteUserInfoImpl);
    }

    static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
        final MediaSessionManager.RemoteUserInfo mObject;

        RemoteUserInfoImplApi28(String str, int i, int i2) {
            super(str, i, i2);
            this.mObject = Trace$$ExternalSyntheticApiModelOutline0.m(str, i, i2);
        }

        RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            super(Trace$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo), Trace$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo), remoteUserInfo.getUid());
            this.mObject = remoteUserInfo;
        }

        static String getPackageName(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            return Trace$$ExternalSyntheticApiModelOutline0.m(remoteUserInfo);
        }
    }
}
