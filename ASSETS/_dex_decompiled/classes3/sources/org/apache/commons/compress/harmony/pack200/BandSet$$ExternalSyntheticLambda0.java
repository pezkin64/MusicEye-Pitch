package org.apache.commons.compress.harmony.pack200;

import java.util.Comparator;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class BandSet$$ExternalSyntheticLambda0 implements Comparator {
    public final /* synthetic */ Map f$0;

    public /* synthetic */ BandSet$$ExternalSyntheticLambda0(Map map) {
        this.f$0 = map;
    }

    public final int compare(Object obj, Object obj2) {
        return ((Integer) this.f$0.get(obj2)).compareTo((Integer) this.f$0.get(obj));
    }
}
