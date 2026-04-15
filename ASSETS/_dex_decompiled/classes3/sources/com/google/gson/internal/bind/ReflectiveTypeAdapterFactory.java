package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.ReflectionAccessFilter;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.ReflectionAccessFilterHelper;
import com.google.gson.internal.TroubleshootingGuide;
import com.google.gson.internal.reflect.ReflectionHelper;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.kshoji.javax.sound.midi.Sequence;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    private final List<ReflectionAccessFilter> reflectionFilters;

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor2, FieldNamingStrategy fieldNamingStrategy, Excluder excluder2, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterAnnotationTypeAdapterFactory, List<ReflectionAccessFilter> list) {
        this.constructorConstructor = constructorConstructor2;
        this.fieldNamingPolicy = fieldNamingStrategy;
        this.excluder = excluder2;
        this.jsonAdapterFactory = jsonAdapterAnnotationTypeAdapterFactory;
        this.reflectionFilters = list;
    }

    private boolean includeField(Field field, boolean z) {
        return !this.excluder.excludeField(field, z);
    }

    private List<String> getFieldNames(Field field) {
        SerializedName serializedName = (SerializedName) field.getAnnotation(SerializedName.class);
        if (serializedName == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(field));
        }
        String value = serializedName.value();
        String[] alternate = serializedName.alternate();
        if (alternate.length == 0) {
            return Collections.singletonList(value);
        }
        ArrayList arrayList = new ArrayList(alternate.length + 1);
        arrayList.add(value);
        Collections.addAll(arrayList, alternate);
        return arrayList;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Object.class.isAssignableFrom(rawType)) {
            return null;
        }
        if (ReflectionHelper.isAnonymousOrNonStaticLocal(rawType)) {
            return new TypeAdapter<T>() {
                public T read(JsonReader jsonReader) throws IOException {
                    jsonReader.skipValue();
                    return null;
                }

                public void write(JsonWriter jsonWriter, T t) throws IOException {
                    jsonWriter.nullValue();
                }

                public String toString() {
                    return "AnonymousOrNonStaticLocalClassAdapter";
                }
            };
        }
        ReflectionAccessFilter.FilterResult filterResult = ReflectionAccessFilterHelper.getFilterResult(this.reflectionFilters, rawType);
        if (filterResult != ReflectionAccessFilter.FilterResult.BLOCK_ALL) {
            boolean z = filterResult == ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE;
            if (ReflectionHelper.isRecord(rawType)) {
                return new RecordAdapter(rawType, getBoundFields(gson, typeToken, rawType, z, true), z);
            }
            TypeToken<T> typeToken2 = typeToken;
            return new FieldReflectionAdapter(this.constructorConstructor.get(typeToken2), getBoundFields(gson, typeToken2, rawType, z, false));
        }
        throw new JsonIOException("ReflectionAccessFilter does not permit using reflection for " + rawType + ". Register a TypeAdapter for this type or adjust the access filter.");
    }

    /* access modifiers changed from: private */
    public static <M extends AccessibleObject & Member> void checkAccessible(Object obj, M m) {
        if (Modifier.isStatic(((Member) m).getModifiers())) {
            obj = null;
        }
        if (!ReflectionAccessFilterHelper.canAccess(m, obj)) {
            String accessibleObjectDescription = ReflectionHelper.getAccessibleObjectDescription(m, true);
            throw new JsonIOException(accessibleObjectDescription + " is not accessible and ReflectionAccessFilter does not permit making it accessible. Register a TypeAdapter for the declaring type, adjust the access filter or increase the visibility of the element and its declaring type.");
        }
    }

    private BoundField createBoundField(Gson gson, Field field, Method method, String str, TypeToken<?> typeToken, boolean z, boolean z2) {
        boolean z3;
        final TypeAdapter<?> typeAdapter;
        TypeAdapter<?> typeAdapter2;
        final boolean isPrimitive = Primitives.isPrimitive(typeToken.getRawType());
        int modifiers = field.getModifiers();
        final boolean z4 = true;
        boolean z5 = false;
        if (!Modifier.isStatic(modifiers) || !Modifier.isFinal(modifiers)) {
            z3 = true;
            z4 = false;
        } else {
            z3 = true;
        }
        JsonAdapter jsonAdapter = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> typeAdapter3 = jsonAdapter != null ? this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, gson, typeToken, jsonAdapter, false) : null;
        if (typeAdapter3 != null) {
            z5 = z3;
        }
        TypeToken<?> typeToken2 = typeToken;
        if (typeAdapter3 == null) {
            typeAdapter3 = gson.getAdapter(typeToken2);
        }
        final TypeAdapter<?> typeAdapter4 = typeAdapter3;
        if (z) {
            if (z5) {
                typeAdapter2 = typeAdapter4;
            } else {
                typeAdapter2 = new TypeAdapterRuntimeTypeWrapper<>(gson, typeAdapter4, typeToken2.getType());
            }
            typeAdapter = typeAdapter2;
        } else {
            typeAdapter = typeAdapter4;
        }
        final Method method2 = method;
        final boolean z6 = z2;
        return new BoundField(str, field) {
            /* access modifiers changed from: package-private */
            public void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException {
                Object obj2;
                if (z6) {
                    Method method = method2;
                    if (method == null) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, method);
                    }
                }
                Method method2 = method2;
                if (method2 != null) {
                    try {
                        obj2 = method2.invoke(obj, (Object[]) null);
                    } catch (InvocationTargetException e) {
                        String accessibleObjectDescription = ReflectionHelper.getAccessibleObjectDescription(method2, false);
                        throw new JsonIOException("Accessor " + accessibleObjectDescription + " threw exception", e.getCause());
                    }
                } else {
                    obj2 = this.field.get(obj);
                }
                if (obj2 != obj) {
                    jsonWriter.name(this.serializedName);
                    typeAdapter.write(jsonWriter, obj2);
                }
            }

            /* access modifiers changed from: package-private */
            public void readIntoArray(JsonReader jsonReader, int i, Object[] objArr) throws IOException, JsonParseException {
                Object read = typeAdapter4.read(jsonReader);
                if (read != null || !isPrimitive) {
                    objArr[i] = read;
                    return;
                }
                throw new JsonParseException("null is not allowed as value for record component '" + this.fieldName + "' of primitive type; at path " + jsonReader.getPath());
            }

            /* access modifiers changed from: package-private */
            public void readIntoField(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
                Object read = typeAdapter4.read(jsonReader);
                if (read != null || !isPrimitive) {
                    if (z6) {
                        ReflectiveTypeAdapterFactory.checkAccessible(obj, this.field);
                    } else if (z4) {
                        String accessibleObjectDescription = ReflectionHelper.getAccessibleObjectDescription(this.field, false);
                        throw new JsonIOException("Cannot set value of 'static final' " + accessibleObjectDescription);
                    }
                    this.field.set(obj, read);
                }
            }
        };
    }

    private static class FieldsData {
        public static final FieldsData EMPTY = new FieldsData(Collections.EMPTY_MAP, Collections.EMPTY_LIST);
        public final Map<String, BoundField> deserializedFields;
        public final List<BoundField> serializedFields;

        public FieldsData(Map<String, BoundField> map, List<BoundField> list) {
            this.deserializedFields = map;
            this.serializedFields = list;
        }
    }

    private static IllegalArgumentException createDuplicateFieldException(Class<?> cls, String str, Field field, Field field2) {
        throw new IllegalArgumentException("Class " + cls.getName() + " declares multiple JSON fields named '" + str + "'; conflict is caused by fields " + ReflectionHelper.fieldToString(field) + " and " + ReflectionHelper.fieldToString(field2) + "\nSee " + TroubleshootingGuide.createUrl("duplicate-fields"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData getBoundFields(com.google.gson.Gson r20, com.google.gson.reflect.TypeToken<?> r21, java.lang.Class<?> r22, boolean r23, boolean r24) {
        /*
            r19 = this;
            r0 = r19
            r8 = r22
            boolean r1 = r8.isInterface()
            if (r1 == 0) goto L_0x000d
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r1 = com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.FieldsData.EMPTY
            return r1
        L_0x000d:
            java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
            r9.<init>()
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            r11 = r21
            r1 = r23
            r12 = r8
        L_0x001c:
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            if (r12 == r2) goto L_0x014f
            java.lang.reflect.Field[] r13 = r12.getDeclaredFields()
            r14 = 1
            r15 = 0
            if (r12 == r8) goto L_0x005e
            int r2 = r13.length
            if (r2 <= 0) goto L_0x005e
            java.util.List<com.google.gson.ReflectionAccessFilter> r1 = r0.reflectionFilters
            com.google.gson.ReflectionAccessFilter$FilterResult r1 = com.google.gson.internal.ReflectionAccessFilterHelper.getFilterResult(r1, r12)
            com.google.gson.ReflectionAccessFilter$FilterResult r2 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_ALL
            if (r1 == r2) goto L_0x003d
            com.google.gson.ReflectionAccessFilter$FilterResult r2 = com.google.gson.ReflectionAccessFilter.FilterResult.BLOCK_INACCESSIBLE
            if (r1 != r2) goto L_0x003b
            r1 = r14
            goto L_0x005e
        L_0x003b:
            r1 = r15
            goto L_0x005e
        L_0x003d:
            com.google.gson.JsonIOException r1 = new com.google.gson.JsonIOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "ReflectionAccessFilter does not permit using reflection for "
            r2.<init>(r3)
            r2.append(r12)
            java.lang.String r3 = " (supertype of "
            r2.append(r3)
            r2.append(r8)
            java.lang.String r3 = "). Register a TypeAdapter for this type or adjust the access filter."
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>((java.lang.String) r2)
            throw r1
        L_0x005e:
            r7 = r1
            int r1 = r13.length
            r2 = r15
        L_0x0061:
            if (r2 >= r1) goto L_0x0134
            r3 = r2
            r2 = r13[r3]
            boolean r6 = r0.includeField(r2, r14)
            boolean r4 = r0.includeField(r2, r15)
            if (r6 != 0) goto L_0x007b
            if (r4 != 0) goto L_0x007b
            r17 = r1
            r18 = r3
            r16 = r11
            r11 = r15
            goto L_0x0128
        L_0x007b:
            r5 = 0
            if (r24 == 0) goto L_0x00c1
            int r16 = r2.getModifiers()
            boolean r16 = java.lang.reflect.Modifier.isStatic(r16)
            if (r16 == 0) goto L_0x008a
            r14 = r15
            goto L_0x00c2
        L_0x008a:
            java.lang.reflect.Method r5 = com.google.gson.internal.reflect.ReflectionHelper.getAccessor(r12, r2)
            if (r7 != 0) goto L_0x0093
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r5)
        L_0x0093:
            java.lang.Class<com.google.gson.annotations.SerializedName> r14 = com.google.gson.annotations.SerializedName.class
            java.lang.annotation.Annotation r14 = r5.getAnnotation(r14)
            if (r14 == 0) goto L_0x00c1
            java.lang.Class<com.google.gson.annotations.SerializedName> r14 = com.google.gson.annotations.SerializedName.class
            java.lang.annotation.Annotation r14 = r2.getAnnotation(r14)
            if (r14 == 0) goto L_0x00a4
            goto L_0x00c1
        L_0x00a4:
            java.lang.String r1 = com.google.gson.internal.reflect.ReflectionHelper.getAccessibleObjectDescription(r5, r15)
            com.google.gson.JsonIOException r2 = new com.google.gson.JsonIOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "@SerializedName on "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = " is not supported"
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>((java.lang.String) r1)
            throw r2
        L_0x00c1:
            r14 = r4
        L_0x00c2:
            if (r7 != 0) goto L_0x00c9
            if (r5 != 0) goto L_0x00c9
            com.google.gson.internal.reflect.ReflectionHelper.makeAccessible(r2)
        L_0x00c9:
            java.lang.reflect.Type r4 = r11.getType()
            java.lang.reflect.Type r15 = r2.getGenericType()
            java.lang.reflect.Type r4 = com.google.gson.internal.C$Gson$Types.resolve(r4, r12, r15)
            java.util.List r15 = r0.getFieldNames(r2)
            r16 = r11
            r11 = 0
            java.lang.Object r17 = r15.get(r11)
            java.lang.String r17 = (java.lang.String) r17
            com.google.gson.reflect.TypeToken r4 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r4)
            r18 = r3
            r3 = r5
            r5 = r4
            r4 = r17
            r17 = r1
            r1 = r20
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r3 = r0.createBoundField(r1, r2, r3, r4, r5, r6, r7)
            if (r14 == 0) goto L_0x0116
            java.util.Iterator r0 = r15.iterator()
        L_0x00fa:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0116
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r5 = r9.put(r1, r3)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r5 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r5
            if (r5 != 0) goto L_0x010f
            goto L_0x00fa
        L_0x010f:
            java.lang.reflect.Field r0 = r5.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r8, r1, r0, r2)
            throw r0
        L_0x0116:
            if (r6 == 0) goto L_0x0128
            java.lang.Object r0 = r10.put(r4, r3)
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$BoundField r0 = (com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.BoundField) r0
            if (r0 != 0) goto L_0x0121
            goto L_0x0128
        L_0x0121:
            java.lang.reflect.Field r0 = r0.field
            java.lang.IllegalArgumentException r0 = createDuplicateFieldException(r8, r4, r0, r2)
            throw r0
        L_0x0128:
            int r2 = r18 + 1
            r0 = r19
            r15 = r11
            r11 = r16
            r1 = r17
            r14 = 1
            goto L_0x0061
        L_0x0134:
            r16 = r11
            java.lang.reflect.Type r0 = r16.getType()
            java.lang.reflect.Type r1 = r12.getGenericSuperclass()
            java.lang.reflect.Type r0 = com.google.gson.internal.C$Gson$Types.resolve(r0, r12, r1)
            com.google.gson.reflect.TypeToken r11 = com.google.gson.reflect.TypeToken.get((java.lang.reflect.Type) r0)
            java.lang.Class r12 = r11.getRawType()
            r0 = r19
            r1 = r7
            goto L_0x001c
        L_0x014f:
            com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData r0 = new com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.Collection r2 = r10.values()
            r1.<init>(r2)
            r0.<init>(r9, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(com.google.gson.Gson, com.google.gson.reflect.TypeToken, java.lang.Class, boolean, boolean):com.google.gson.internal.bind.ReflectiveTypeAdapterFactory$FieldsData");
    }

    static abstract class BoundField {
        final Field field;
        final String fieldName;
        final String serializedName;

        /* access modifiers changed from: package-private */
        public abstract void readIntoArray(JsonReader jsonReader, int i, Object[] objArr) throws IOException, JsonParseException;

        /* access modifiers changed from: package-private */
        public abstract void readIntoField(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        /* access modifiers changed from: package-private */
        public abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String str, Field field2) {
            this.serializedName = str;
            this.field = field2;
            this.fieldName = field2.getName();
        }
    }

    public static abstract class Adapter<T, A> extends TypeAdapter<T> {
        private final FieldsData fieldsData;

        /* access modifiers changed from: package-private */
        public abstract A createAccumulator();

        /* access modifiers changed from: package-private */
        public abstract T finalize(A a);

        /* access modifiers changed from: package-private */
        public abstract void readField(A a, JsonReader jsonReader, BoundField boundField) throws IllegalAccessException, IOException;

        Adapter(FieldsData fieldsData2) {
            this.fieldsData = fieldsData2;
        }

        public void write(JsonWriter jsonWriter, T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            try {
                for (BoundField write : this.fieldsData.serializedFields) {
                    write.write(jsonWriter, t);
                }
                jsonWriter.endObject();
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            }
        }

        public T read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            Object createAccumulator = createAccumulator();
            Map<String, BoundField> map = this.fieldsData.deserializedFields;
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    BoundField boundField = map.get(jsonReader.nextName());
                    if (boundField == null) {
                        jsonReader.skipValue();
                    } else {
                        readField(createAccumulator, jsonReader, boundField);
                    }
                }
                jsonReader.endObject();
                return finalize(createAccumulator);
            } catch (IllegalStateException e) {
                throw new JsonSyntaxException((Throwable) e);
            } catch (IllegalAccessException e2) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e2);
            }
        }
    }

    private static final class FieldReflectionAdapter<T> extends Adapter<T, T> {
        private final ObjectConstructor<T> constructor;

        /* access modifiers changed from: package-private */
        public T finalize(T t) {
            return t;
        }

        FieldReflectionAdapter(ObjectConstructor<T> objectConstructor, FieldsData fieldsData) {
            super(fieldsData);
            this.constructor = objectConstructor;
        }

        /* access modifiers changed from: package-private */
        public T createAccumulator() {
            return this.constructor.construct();
        }

        /* access modifiers changed from: package-private */
        public void readField(T t, JsonReader jsonReader, BoundField boundField) throws IllegalAccessException, IOException {
            boundField.readIntoField(jsonReader, t);
        }
    }

    private static final class RecordAdapter<T> extends Adapter<T, Object[]> {
        static final Map<Class<?>, Object> PRIMITIVE_DEFAULTS = primitiveDefaults();
        private final Map<String, Integer> componentIndices = new HashMap();
        private final Constructor<T> constructor;
        private final Object[] constructorArgsDefaults;

        RecordAdapter(Class<T> cls, FieldsData fieldsData, boolean z) {
            super(fieldsData);
            Constructor<T> canonicalRecordConstructor = ReflectionHelper.getCanonicalRecordConstructor(cls);
            this.constructor = canonicalRecordConstructor;
            if (z) {
                ReflectiveTypeAdapterFactory.checkAccessible((Object) null, canonicalRecordConstructor);
            } else {
                ReflectionHelper.makeAccessible(canonicalRecordConstructor);
            }
            String[] recordComponentNames = ReflectionHelper.getRecordComponentNames(cls);
            for (int i = 0; i < recordComponentNames.length; i++) {
                this.componentIndices.put(recordComponentNames[i], Integer.valueOf(i));
            }
            Class[] parameterTypes = this.constructor.getParameterTypes();
            this.constructorArgsDefaults = new Object[parameterTypes.length];
            for (int i2 = 0; i2 < parameterTypes.length; i2++) {
                this.constructorArgsDefaults[i2] = PRIMITIVE_DEFAULTS.get(parameterTypes[i2]);
            }
        }

        private static Map<Class<?>, Object> primitiveDefaults() {
            HashMap hashMap = new HashMap();
            hashMap.put(Byte.TYPE, (byte) 0);
            hashMap.put(Short.TYPE, (short) 0);
            hashMap.put(Integer.TYPE, 0);
            hashMap.put(Long.TYPE, 0L);
            hashMap.put(Float.TYPE, Float.valueOf(Sequence.PPQ));
            hashMap.put(Double.TYPE, Double.valueOf(0.0d));
            hashMap.put(Character.TYPE, 0);
            hashMap.put(Boolean.TYPE, false);
            return hashMap;
        }

        /* access modifiers changed from: package-private */
        public Object[] createAccumulator() {
            return (Object[]) this.constructorArgsDefaults.clone();
        }

        /* access modifiers changed from: package-private */
        public void readField(Object[] objArr, JsonReader jsonReader, BoundField boundField) throws IOException {
            Integer num = this.componentIndices.get(boundField.fieldName);
            if (num != null) {
                boundField.readIntoArray(jsonReader, num.intValue(), objArr);
                return;
            }
            throw new IllegalStateException("Could not find the index in the constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' for field with name '" + boundField.fieldName + "', unable to determine which argument in the constructor the field corresponds to. This is unexpected behavior, as we expect the RecordComponents to have the same names as the fields in the Java class, and that the order of the RecordComponents is the same as the order of the canonical constructor parameters.");
        }

        /* access modifiers changed from: package-private */
        public T finalize(Object[] objArr) {
            try {
                return this.constructor.newInstance(objArr);
            } catch (IllegalAccessException e) {
                throw ReflectionHelper.createExceptionForUnexpectedIllegalAccess(e);
            } catch (IllegalArgumentException | InstantiationException e2) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to invoke constructor '" + ReflectionHelper.constructorToString(this.constructor) + "' with args " + Arrays.toString(objArr), e3.getCause());
            }
        }
    }
}
