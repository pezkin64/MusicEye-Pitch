package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConstructorConstructor$$ExternalSyntheticLambda4 implements ObjectConstructor {
    public final /* synthetic */ InstanceCreator f$0;
    public final /* synthetic */ Type f$1;

    public /* synthetic */ ConstructorConstructor$$ExternalSyntheticLambda4(InstanceCreator instanceCreator, Type type) {
        this.f$0 = instanceCreator;
        this.f$1 = type;
    }

    public final Object construct() {
        return this.f$0.createInstance(this.f$1);
    }
}
