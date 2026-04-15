package org.apache.commons.compress.harmony.archive.internal.nls;

import java.security.PrivilegedAction;
import java.util.Locale;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Messages$$ExternalSyntheticLambda0 implements PrivilegedAction {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Locale f$1;
    public final /* synthetic */ ClassLoader f$2;

    public /* synthetic */ Messages$$ExternalSyntheticLambda0(String str, Locale locale, ClassLoader classLoader) {
        this.f$0 = str;
        this.f$1 = locale;
        this.f$2 = classLoader;
    }

    public final Object run() {
        return Messages.lambda$setLocale$0(this.f$0, this.f$1, this.f$2);
    }
}
