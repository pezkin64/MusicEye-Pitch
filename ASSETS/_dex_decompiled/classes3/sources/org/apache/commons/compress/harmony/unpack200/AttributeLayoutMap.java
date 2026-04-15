package org.apache.commons.compress.harmony.unpack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

public class AttributeLayoutMap {
    private final Map classLayouts;
    private final Map codeLayouts;
    private final Map fieldLayouts;
    private final Map[] layouts;
    private final Map layoutsToBands = new HashMap();
    private final Map methodLayouts;

    private static AttributeLayout[] getDefaultAttributeLayouts() throws Pack200Exception {
        return new AttributeLayout[]{new AttributeLayout(AttributeLayout.ACC_PUBLIC, 0, BuildConfig.FLAVOR, 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 1, BuildConfig.FLAVOR, 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 2, BuildConfig.FLAVOR, 0), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 0, BuildConfig.FLAVOR, 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 1, BuildConfig.FLAVOR, 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 2, BuildConfig.FLAVOR, 1), new AttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3, "NH[PHH]", 1), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 0, BuildConfig.FLAVOR, 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 1, BuildConfig.FLAVOR, 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 2, BuildConfig.FLAVOR, 2), new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3, "NH[PHOHRUHRSHH]", 2), new AttributeLayout(AttributeLayout.ACC_STATIC, 0, BuildConfig.FLAVOR, 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 1, BuildConfig.FLAVOR, 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 2, BuildConfig.FLAVOR, 3), new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3, "NH[PHOHRUHRSHH]", 3), new AttributeLayout(AttributeLayout.ACC_FINAL, 0, BuildConfig.FLAVOR, 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 1, BuildConfig.FLAVOR, 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 2, BuildConfig.FLAVOR, 4), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 0, BuildConfig.FLAVOR, 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 1, BuildConfig.FLAVOR, 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 2, BuildConfig.FLAVOR, 5), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 0, BuildConfig.FLAVOR, 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 1, BuildConfig.FLAVOR, 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 2, BuildConfig.FLAVOR, 6), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 0, BuildConfig.FLAVOR, 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 1, BuildConfig.FLAVOR, 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 2, BuildConfig.FLAVOR, 7), new AttributeLayout(AttributeLayout.ACC_NATIVE, 0, BuildConfig.FLAVOR, 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 1, BuildConfig.FLAVOR, 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 2, BuildConfig.FLAVOR, 8), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 0, BuildConfig.FLAVOR, 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 1, BuildConfig.FLAVOR, 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 2, BuildConfig.FLAVOR, 9), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 0, BuildConfig.FLAVOR, 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 1, BuildConfig.FLAVOR, 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 2, BuildConfig.FLAVOR, 10), new AttributeLayout(AttributeLayout.ACC_STRICT, 0, BuildConfig.FLAVOR, 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 1, BuildConfig.FLAVOR, 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 2, BuildConfig.FLAVOR, 11), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 0, BuildConfig.FLAVOR, 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 1, BuildConfig.FLAVOR, 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 2, BuildConfig.FLAVOR, 12), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 0, BuildConfig.FLAVOR, 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 1, BuildConfig.FLAVOR, 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 2, BuildConfig.FLAVOR, 13), new AttributeLayout(AttributeLayout.ACC_ENUM, 0, BuildConfig.FLAVOR, 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 1, BuildConfig.FLAVOR, 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 2, BuildConfig.FLAVOR, 14), new AttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0, "RUNH", 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1, "KQH", 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2, BuildConfig.FLAVOR, 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0, "RCHRDNH", 18), new AttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2, "NH[RCH]", 18), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 0, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 1, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 2, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0, BuildConfig.FLAVOR, 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1, BuildConfig.FLAVOR, 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2, BuildConfig.FLAVOR, 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 0, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 1, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 2, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 0, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 1, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 2, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0, BuildConfig.FLAVOR, 23), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, 2, "*", 23), new AttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0, BuildConfig.FLAVOR, 24), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, 2, "*", 24), new AttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT, 2, "*", 25)};
    }

    public AttributeLayoutMap() throws Pack200Exception {
        HashMap hashMap = new HashMap();
        this.classLayouts = hashMap;
        HashMap hashMap2 = new HashMap();
        this.fieldLayouts = hashMap2;
        HashMap hashMap3 = new HashMap();
        this.methodLayouts = hashMap3;
        HashMap hashMap4 = new HashMap();
        this.codeLayouts = hashMap4;
        this.layouts = new Map[]{hashMap, hashMap2, hashMap3, hashMap4};
        AttributeLayout[] defaultAttributeLayouts = getDefaultAttributeLayouts();
        for (AttributeLayout add : defaultAttributeLayouts) {
            add(add);
        }
    }

    public void add(AttributeLayout attributeLayout) {
        this.layouts[attributeLayout.getContext()].put(Integer.valueOf(attributeLayout.getIndex()), attributeLayout);
    }

    public void add(AttributeLayout attributeLayout, NewAttributeBands newAttributeBands) {
        add(attributeLayout);
        this.layoutsToBands.put(attributeLayout, newAttributeBands);
    }

    public AttributeLayout getAttributeLayout(String str, int i) throws Pack200Exception {
        for (AttributeLayout attributeLayout : this.layouts[i].values()) {
            if (attributeLayout.getName().equals(str)) {
                return attributeLayout;
            }
        }
        return null;
    }

    public AttributeLayout getAttributeLayout(int i, int i2) throws Pack200Exception {
        return (AttributeLayout) this.layouts[i2].get(Integer.valueOf(i));
    }

    public void checkMap() throws Pack200Exception {
        int i = 0;
        while (true) {
            Map[] mapArr = this.layouts;
            if (i < mapArr.length) {
                Collection values = mapArr[i].values();
                if (!(values instanceof List)) {
                    values = new ArrayList(values);
                }
                List list = (List) values;
                int i2 = 0;
                while (i2 < list.size()) {
                    AttributeLayout attributeLayout = (AttributeLayout) list.get(i2);
                    i2++;
                    int i3 = i2;
                    while (true) {
                        if (i3 < list.size()) {
                            AttributeLayout attributeLayout2 = (AttributeLayout) list.get(i3);
                            if (!attributeLayout.getName().equals(attributeLayout2.getName()) || !attributeLayout.getLayout().equals(attributeLayout2.getLayout())) {
                                i3++;
                            } else {
                                throw new Pack200Exception("Same layout/name combination: " + attributeLayout.getLayout() + "/" + attributeLayout.getName() + " exists twice for context: " + AttributeLayout.contextNames[attributeLayout.getContext()]);
                            }
                        }
                    }
                }
                i++;
            } else {
                return;
            }
        }
    }

    public NewAttributeBands getAttributeBands(AttributeLayout attributeLayout) {
        return (NewAttributeBands) this.layoutsToBands.get(attributeLayout);
    }
}
