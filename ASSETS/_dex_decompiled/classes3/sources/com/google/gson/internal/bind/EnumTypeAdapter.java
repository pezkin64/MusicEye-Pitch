package com.google.gson.internal.bind;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.Enum;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class EnumTypeAdapter<T extends Enum<T>> extends TypeAdapter<T> {
    static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        /* JADX WARNING: type inference failed for: r3v0, types: [com.google.gson.reflect.TypeToken<T>, com.google.gson.reflect.TypeToken] */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <T> com.google.gson.TypeAdapter<T> create(com.google.gson.Gson r2, com.google.gson.reflect.TypeToken<T> r3) {
            /*
                r1 = this;
                java.lang.Class r2 = r3.getRawType()
                java.lang.Class<java.lang.Enum> r3 = java.lang.Enum.class
                boolean r3 = r3.isAssignableFrom(r2)
                r0 = 0
                if (r3 == 0) goto L_0x0022
                java.lang.Class<java.lang.Enum> r3 = java.lang.Enum.class
                if (r2 != r3) goto L_0x0012
                goto L_0x0022
            L_0x0012:
                boolean r3 = r2.isEnum()
                if (r3 != 0) goto L_0x001c
                java.lang.Class r2 = r2.getSuperclass()
            L_0x001c:
                com.google.gson.internal.bind.EnumTypeAdapter r3 = new com.google.gson.internal.bind.EnumTypeAdapter
                r3.<init>(r2)
                return r3
            L_0x0022:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.EnumTypeAdapter.AnonymousClass1.create(com.google.gson.Gson, com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
        }
    };
    private final Map<T, String> constantToName;
    private final Map<String, T> nameToConstant;
    private final Map<String, T> stringToConstant;

    private EnumTypeAdapter(Class<T> cls) {
        this.nameToConstant = new HashMap();
        this.stringToConstant = new HashMap();
        this.constantToName = new HashMap();
        try {
            Field[] declaredFields = cls.getDeclaredFields();
            int i = 0;
            for (Field field : declaredFields) {
                if (field.isEnumConstant()) {
                    declaredFields[i] = field;
                    i++;
                }
            }
            Field[] fieldArr = (Field[]) Arrays.copyOf(declaredFields, i);
            AccessibleObject.setAccessible(fieldArr, true);
            for (Field field2 : fieldArr) {
                Enum enumR = (Enum) field2.get((Object) null);
                String name = enumR.name();
                String str = enumR.toString();
                SerializedName serializedName = (SerializedName) field2.getAnnotation(SerializedName.class);
                if (serializedName != null) {
                    name = serializedName.value();
                    for (String put : serializedName.alternate()) {
                        this.nameToConstant.put(put, enumR);
                    }
                }
                this.nameToConstant.put(name, enumR);
                this.stringToConstant.put(str, enumR);
                this.constantToName.put(enumR, name);
            }
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public T read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        String nextString = jsonReader.nextString();
        T t = (Enum) this.nameToConstant.get(nextString);
        return t == null ? (Enum) this.stringToConstant.get(nextString) : t;
    }

    public void write(JsonWriter jsonWriter, T t) throws IOException {
        jsonWriter.value(t == null ? null : this.constantToName.get(t));
    }
}
