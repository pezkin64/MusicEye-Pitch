package com.google.gson.internal;

import com.google.gson.InstanceCreator;
import com.google.gson.JsonIOException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public final class ConstructorConstructor {
    private final Map<Type, InstanceCreator<?>> instanceCreators;
    private final List<ReflectionAccessFilter> reflectionFilters;
    private final boolean useJdkUnsafe;

    public ConstructorConstructor(Map<Type, InstanceCreator<?>> map, boolean z, List<ReflectionAccessFilter> list) {
        this.instanceCreators = map;
        this.useJdkUnsafe = z;
        this.reflectionFilters = list;
    }

    static String checkInstantiable(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: " + cls.getName();
        } else if (!Modifier.isAbstract(modifiers)) {
            return null;
        } else {
            return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName() + "\nSee " + TroubleshootingGuide.createUrl("r8-abstract-class");
        }
    }

    public <T> ObjectConstructor<T> get(TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        Class<? super T> rawType = typeToken.getRawType();
        InstanceCreator instanceCreator = this.instanceCreators.get(type);
        if (instanceCreator != null) {
            return new ConstructorConstructor$$ExternalSyntheticLambda4(instanceCreator, type);
        }
        InstanceCreator instanceCreator2 = this.instanceCreators.get(rawType);
        if (instanceCreator2 != null) {
            return new ConstructorConstructor$$ExternalSyntheticLambda5(instanceCreator2, type);
        }
        ObjectConstructor<T> newSpecialCollectionConstructor = newSpecialCollectionConstructor(type, rawType);
        if (newSpecialCollectionConstructor != null) {
            return newSpecialCollectionConstructor;
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        ObjectConstructor<T> newDefaultConstructor = newDefaultConstructor(rawType, filterResult);
        if (newDefaultConstructor != null) {
            return newDefaultConstructor;
        }
        ObjectConstructor<T> newDefaultImplementationConstructor = newDefaultImplementationConstructor(type, rawType);
        if (newDefaultImplementationConstructor != null) {
            return newDefaultImplementationConstructor;
        }
        String checkInstantiable = checkInstantiable(rawType);
        if (checkInstantiable != null) {
            return new ConstructorConstructor$$ExternalSyntheticLambda6(checkInstantiable);
        }
        if (filterResult == ReflectionAccessFilter.FilterResult.ALLOW) {
            return newUnsafeAllocator(rawType);
        }
        return new ConstructorConstructor$$ExternalSyntheticLambda7("Unable to create instance of " + rawType + "; ReflectionAccessFilter does not permit using reflection or Unsafe. Register an InstanceCreator or a TypeAdapter for this type or adjust the access filter to allow using reflection.");
    }

    static /* synthetic */ Object lambda$get$2(String str) {
        throw new JsonIOException(str);
    }

    static /* synthetic */ Object lambda$get$3(String str) {
        throw new JsonIOException(str);
    }

    private static <T> ObjectConstructor<T> newSpecialCollectionConstructor(Type type, Class<? super T> cls) {
        if (EnumSet.class.isAssignableFrom(cls)) {
            return new ConstructorConstructor$$ExternalSyntheticLambda2(type);
        }
        if (cls == EnumMap.class) {
            return new ConstructorConstructor$$ExternalSyntheticLambda3(type);
        }
        return null;
    }

    static /* synthetic */ Object lambda$newSpecialCollectionConstructor$4(Type type) {
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return EnumSet.noneOf((Class) type2);
            }
            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
        }
        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
    }

    static /* synthetic */ Object lambda$newSpecialCollectionConstructor$5(Type type) {
        if (type instanceof ParameterizedType) {
            Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
            if (type2 instanceof Class) {
                return new EnumMap((Class) type2);
            }
            throw new JsonIOException("Invalid EnumMap type: " + type.toString());
        }
        throw new JsonIOException("Invalid EnumMap type: " + type.toString());
    }

    private static <T> ObjectConstructor<T> newDefaultConstructor(Class<? super T> cls, ReflectionAccessFilter.FilterResult filterResult) {
        String tryMakeAccessible;
        if (Modifier.isAbstract(cls.getModifiers())) {
            return null;
        }
        try {
            Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor((Class[]) null);
            if (filterResult != ReflectionAccessFilter.FilterResult.ALLOW && (!ReflectionAccessFilterHelper.canAccess(declaredConstructor, (Object) null) || (filterResult == ReflectionAccessFilter.FilterResult.BLOCK_ALL && !Modifier.isPublic(declaredConstructor.getModifiers())))) {
                return new ConstructorConstructor$$ExternalSyntheticLambda8("Unable to invoke no-args constructor of " + cls + "; constructor is not accessible and ReflectionAccessFilter does not permit making it accessible. Register an InstanceCreator or a TypeAdapter for this type, change the visibility of the constructor or adjust the access filter.");
            } else if (filterResult != ReflectionAccessFilter.FilterResult.ALLOW || (tryMakeAccessible = ReflectionHelper.tryMakeAccessible(declaredConstructor)) == null) {
                return new ConstructorConstructor$$ExternalSyntheticLambda10(declaredConstructor);
            } else {
                return new ConstructorConstructor$$ExternalSyntheticLambda9(tryMakeAccessible);
            }
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    static /* synthetic */ Object lambda$newDefaultConstructor$6(String str) {
        throw new JsonIOException(str);
    }

    static /* synthetic */ Object lambda$newDefaultConstructor$7(String str) {
        throw new JsonIOException(str);
    }

    static /* synthetic */ Object lambda$newDefaultConstructor$8(Constructor constructor) {
        try {
            return constructor.newInstance((Object[]) null);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(constructor) + "' with no args", e2.getCause());
        } catch (IllegalAccessException e3) {
            throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e3);
        }
    }

    private static <T> ObjectConstructor<T> newDefaultImplementationConstructor(Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            if (SortedSet.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda12();
            }
            if (Set.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda13();
            }
            if (Queue.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda14();
            }
            return new ConstructorConstructor$$ExternalSyntheticLambda15();
        } else if (!Map.class.isAssignableFrom(cls)) {
            return null;
        } else {
            if (ConcurrentNavigableMap.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda16();
            }
            if (ConcurrentMap.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda17();
            }
            if (SortedMap.class.isAssignableFrom(cls)) {
                return new ConstructorConstructor$$ExternalSyntheticLambda18();
            }
            if (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(TypeToken.get(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType())) {
                return new ConstructorConstructor$$ExternalSyntheticLambda1();
            }
            return new ConstructorConstructor$$ExternalSyntheticLambda19();
        }
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$9() {
        return new TreeSet();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$10() {
        return new LinkedHashSet();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$11() {
        return new ArrayDeque();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$12() {
        return new ArrayList();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$13() {
        return new ConcurrentSkipListMap();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$14() {
        return new ConcurrentHashMap();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$15() {
        return new TreeMap();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$16() {
        return new LinkedHashMap();
    }

    static /* synthetic */ Object lambda$newDefaultImplementationConstructor$17() {
        return new LinkedTreeMap();
    }

    private <T> ObjectConstructor<T> newUnsafeAllocator(Class<? super T> cls) {
        if (this.useJdkUnsafe) {
            return new ConstructorConstructor$$ExternalSyntheticLambda0(cls);
        }
        String str = "Unable to create instance of " + cls + "; usage of JDK Unsafe is disabled. Registering an InstanceCreator or a TypeAdapter for this type, adding a no-args constructor, or enabling usage of JDK Unsafe may fix this problem.";
        if (cls.getDeclaredConstructors().length == 0) {
            str = str + " Or adjust your R8 configuration to keep the no-args constructor of the class.";
        }
        return new ConstructorConstructor$$ExternalSyntheticLambda11(str);
    }

    static /* synthetic */ Object lambda$newUnsafeAllocator$18(Class cls) {
        try {
            return UnsafeAllocator.INSTANCE.newInstance(cls);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create instance of " + cls + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e);
        }
    }

    static /* synthetic */ Object lambda$newUnsafeAllocator$19(String str) {
        throw new JsonIOException(str);
    }

    public String toString() {
        return this.instanceCreators.toString();
    }
}
