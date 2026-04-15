package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.reflect.TypeToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {
    private static final TypeAdapterFactory TREE_TYPE_CLASS_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private static final TypeAdapterFactory TREE_TYPE_FIELD_DUMMY_FACTORY = new DummyTypeAdapterFactory();
    private final ConcurrentMap<Class<?>, TypeAdapterFactory> adapterFactoryMap = new ConcurrentHashMap();
    private final ConstructorConstructor constructorConstructor;

    private static class DummyTypeAdapterFactory implements TypeAdapterFactory {
        private DummyTypeAdapterFactory() {
        }

        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            throw new AssertionError("Factory should not be used");
        }
    }

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor constructorConstructor2) {
        this.constructorConstructor = constructorConstructor2;
    }

    private static JsonAdapter getAnnotation(Class<?> cls) {
        return (JsonAdapter) cls.getAnnotation(JsonAdapter.class);
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        JsonAdapter annotation = getAnnotation(typeToken.getRawType());
        if (annotation == null) {
            return null;
        }
        return getTypeAdapter(this.constructorConstructor, gson, typeToken, annotation, true);
    }

    private static Object createAdapter(ConstructorConstructor constructorConstructor2, Class<?> cls) {
        return constructorConstructor2.get(TypeToken.get(cls)).construct();
    }

    private TypeAdapterFactory putFactoryAndGetCurrent(Class<?> cls, TypeAdapterFactory typeAdapterFactory) {
        TypeAdapterFactory putIfAbsent = this.adapterFactoryMap.putIfAbsent(cls, typeAdapterFactory);
        return putIfAbsent != null ? putIfAbsent : typeAdapterFactory;
    }

    /* access modifiers changed from: package-private */
    public TypeAdapter<?> getTypeAdapter(ConstructorConstructor constructorConstructor2, Gson gson, TypeToken<?> typeToken, JsonAdapter jsonAdapter, boolean z) {
        TypeAdapter<?> typeAdapter;
        TypeAdapterFactory typeAdapterFactory;
        Object createAdapter = createAdapter(constructorConstructor2, jsonAdapter.value());
        boolean nullSafe = jsonAdapter.nullSafe();
        if (createAdapter instanceof TypeAdapter) {
            typeAdapter = (TypeAdapter) createAdapter;
        } else if (createAdapter instanceof TypeAdapterFactory) {
            TypeAdapterFactory typeAdapterFactory2 = (TypeAdapterFactory) createAdapter;
            if (z) {
                typeAdapterFactory2 = putFactoryAndGetCurrent(typeToken.getRawType(), typeAdapterFactory2);
            }
            typeAdapter = typeAdapterFactory2.create(gson, typeToken);
        } else {
            boolean z2 = createAdapter instanceof JsonSerializer;
            if (z2 || (createAdapter instanceof JsonDeserializer)) {
                JsonDeserializer jsonDeserializer = null;
                JsonSerializer jsonSerializer = z2 ? (JsonSerializer) createAdapter : null;
                if (createAdapter instanceof JsonDeserializer) {
                    jsonDeserializer = (JsonDeserializer) createAdapter;
                }
                JsonDeserializer jsonDeserializer2 = jsonDeserializer;
                if (z) {
                    typeAdapterFactory = TREE_TYPE_CLASS_DUMMY_FACTORY;
                } else {
                    typeAdapterFactory = TREE_TYPE_FIELD_DUMMY_FACTORY;
                }
                TreeTypeAdapter treeTypeAdapter = new TreeTypeAdapter(jsonSerializer, jsonDeserializer2, gson, typeToken, typeAdapterFactory, nullSafe);
                nullSafe = false;
                typeAdapter = treeTypeAdapter;
            } else {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + createAdapter.getClass().getName() + " as a @JsonAdapter for " + typeToken.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
        }
        return (typeAdapter == null || !nullSafe) ? typeAdapter : typeAdapter.nullSafe();
    }

    public boolean isClassJsonAdapterFactory(TypeToken<?> typeToken, TypeAdapterFactory typeAdapterFactory) {
        Objects.requireNonNull(typeToken);
        Objects.requireNonNull(typeAdapterFactory);
        if (typeAdapterFactory == TREE_TYPE_CLASS_DUMMY_FACTORY) {
            return true;
        }
        Class<? super Object> rawType = typeToken.getRawType();
        TypeAdapterFactory typeAdapterFactory2 = (TypeAdapterFactory) this.adapterFactoryMap.get(rawType);
        if (typeAdapterFactory2 == null) {
            JsonAdapter annotation = getAnnotation(rawType);
            if (annotation == null) {
                return false;
            }
            Class<?> value = annotation.value();
            if (TypeAdapterFactory.class.isAssignableFrom(value) && putFactoryAndGetCurrent(rawType, (TypeAdapterFactory) createAdapter(this.constructorConstructor, value)) == typeAdapterFactory) {
                return true;
            }
            return false;
        } else if (typeAdapterFactory2 == typeAdapterFactory) {
            return true;
        } else {
            return false;
        }
    }
}
