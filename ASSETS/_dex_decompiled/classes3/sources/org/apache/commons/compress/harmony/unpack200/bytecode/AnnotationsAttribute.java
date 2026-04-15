package org.apache.commons.compress.harmony.unpack200.bytecode;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AnnotationsAttribute extends Attribute {

    public static class Annotation {
        private final CPUTF8[] element_names;
        private final ElementValue[] element_values;
        private int[] name_indexes;
        private final int num_pairs;
        private final CPUTF8 type;
        private int type_index;

        public Annotation(int i, CPUTF8 cputf8, CPUTF8[] cputf8Arr, ElementValue[] elementValueArr) {
            this.num_pairs = i;
            this.type = cputf8;
            this.element_names = cputf8Arr;
            this.element_values = elementValueArr;
        }

        public int getLength() {
            int i = 4;
            for (int i2 = 0; i2 < this.num_pairs; i2++) {
                i = i + 2 + this.element_values[i2].getLength();
            }
            return i;
        }

        public void resolve(ClassConstantPool classConstantPool) {
            this.type.resolve(classConstantPool);
            this.type_index = classConstantPool.indexOf(this.type);
            this.name_indexes = new int[this.num_pairs];
            int i = 0;
            while (true) {
                CPUTF8[] cputf8Arr = this.element_names;
                if (i < cputf8Arr.length) {
                    cputf8Arr[i].resolve(classConstantPool);
                    this.name_indexes[i] = classConstantPool.indexOf(this.element_names[i]);
                    this.element_values[i].resolve(classConstantPool);
                    i++;
                } else {
                    return;
                }
            }
        }

        public void writeBody(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeShort(this.type_index);
            dataOutputStream.writeShort(this.num_pairs);
            for (int i = 0; i < this.num_pairs; i++) {
                dataOutputStream.writeShort(this.name_indexes[i]);
                this.element_values[i].writeBody(dataOutputStream);
            }
        }

        public List getClassFileEntries() {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                CPUTF8[] cputf8Arr = this.element_names;
                if (i < cputf8Arr.length) {
                    arrayList.add(cputf8Arr[i]);
                    arrayList.addAll(this.element_values[i].getClassFileEntries());
                    i++;
                } else {
                    arrayList.add(this.type);
                    return arrayList;
                }
            }
        }
    }

    public static class ElementValue {
        private int constant_value_index = -1;
        private final int tag;
        private final Object value;

        public ElementValue(int i, Object obj) {
            this.tag = i;
            this.value = obj;
        }

        public List getClassFileEntries() {
            ArrayList arrayList = new ArrayList(1);
            Object obj = this.value;
            if (obj instanceof CPNameAndType) {
                arrayList.add(((CPNameAndType) obj).name);
                arrayList.add(((CPNameAndType) this.value).descriptor);
                return arrayList;
            } else if (obj instanceof ClassFileEntry) {
                arrayList.add(obj);
                return arrayList;
            } else {
                if (obj instanceof ElementValue[]) {
                    ElementValue[] elementValueArr = (ElementValue[]) obj;
                    for (ElementValue classFileEntries : elementValueArr) {
                        arrayList.addAll(classFileEntries.getClassFileEntries());
                    }
                } else if (obj instanceof Annotation) {
                    arrayList.addAll(((Annotation) obj).getClassFileEntries());
                }
                return arrayList;
            }
        }

        public void resolve(ClassConstantPool classConstantPool) {
            Object obj = this.value;
            if (obj instanceof CPConstant) {
                ((CPConstant) obj).resolve(classConstantPool);
                this.constant_value_index = classConstantPool.indexOf((CPConstant) this.value);
            } else if (obj instanceof CPClass) {
                ((CPClass) obj).resolve(classConstantPool);
                this.constant_value_index = classConstantPool.indexOf((CPClass) this.value);
            } else if (obj instanceof CPUTF8) {
                ((CPUTF8) obj).resolve(classConstantPool);
                this.constant_value_index = classConstantPool.indexOf((CPUTF8) this.value);
            } else if (obj instanceof CPNameAndType) {
                ((CPNameAndType) obj).resolve(classConstantPool);
            } else if (obj instanceof Annotation) {
                ((Annotation) obj).resolve(classConstantPool);
            } else if (obj instanceof ElementValue[]) {
                ElementValue[] elementValueArr = (ElementValue[]) obj;
                for (ElementValue resolve : elementValueArr) {
                    resolve.resolve(classConstantPool);
                }
            }
        }

        public void writeBody(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeByte(this.tag);
            int i = this.constant_value_index;
            if (i != -1) {
                dataOutputStream.writeShort(i);
                return;
            }
            Object obj = this.value;
            if (obj instanceof CPNameAndType) {
                ((CPNameAndType) obj).writeBody(dataOutputStream);
            } else if (obj instanceof Annotation) {
                ((Annotation) obj).writeBody(dataOutputStream);
            } else if (obj instanceof ElementValue[]) {
                ElementValue[] elementValueArr = (ElementValue[]) obj;
                dataOutputStream.writeShort(elementValueArr.length);
                for (ElementValue writeBody : elementValueArr) {
                    writeBody.writeBody(dataOutputStream);
                }
            } else {
                throw new Error(BuildConfig.FLAVOR);
            }
        }

        public int getLength() {
            int i = this.tag;
            if (i == 64) {
                return ((Annotation) this.value).getLength() + 1;
            }
            int i2 = 3;
            if (!(i == 70 || i == 83 || i == 99)) {
                if (i == 101) {
                    return 5;
                }
                if (!(i == 115 || i == 73 || i == 74 || i == 90)) {
                    if (i != 91) {
                        switch (i) {
                            case 66:
                            case 67:
                            case 68:
                                break;
                            default:
                                return 0;
                        }
                    } else {
                        ElementValue[] elementValueArr = (ElementValue[]) this.value;
                        for (ElementValue length : elementValueArr) {
                            i2 += length.getLength();
                        }
                        return i2;
                    }
                }
            }
            return 3;
        }
    }

    public AnnotationsAttribute(CPUTF8 cputf8) {
        super(cputf8);
    }
}
