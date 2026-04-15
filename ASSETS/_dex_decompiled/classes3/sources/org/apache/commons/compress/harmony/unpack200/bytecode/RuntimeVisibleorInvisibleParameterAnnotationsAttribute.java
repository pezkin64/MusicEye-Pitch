package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.compress.harmony.unpack200.bytecode.AnnotationsAttribute;

public class RuntimeVisibleorInvisibleParameterAnnotationsAttribute extends AnnotationsAttribute {
    private final int num_parameters;
    private final ParameterAnnotation[] parameter_annotations;

    public RuntimeVisibleorInvisibleParameterAnnotationsAttribute(CPUTF8 cputf8, ParameterAnnotation[] parameterAnnotationArr) {
        super(cputf8);
        this.num_parameters = parameterAnnotationArr.length;
        this.parameter_annotations = parameterAnnotationArr;
    }

    /* access modifiers changed from: protected */
    public int getLength() {
        int i = 1;
        for (int i2 = 0; i2 < this.num_parameters; i2++) {
            i += this.parameter_annotations[i2].getLength();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        int i = 0;
        while (true) {
            ParameterAnnotation[] parameterAnnotationArr = this.parameter_annotations;
            if (i < parameterAnnotationArr.length) {
                parameterAnnotationArr[i].resolve(classConstantPool);
                i++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.num_parameters);
        for (int i = 0; i < this.num_parameters; i++) {
            this.parameter_annotations[i].writeBody(dataOutputStream);
        }
    }

    public String toString() {
        return this.attributeName.underlyingString() + ": " + this.num_parameters + " parameter annotations";
    }

    public static class ParameterAnnotation {
        private final AnnotationsAttribute.Annotation[] annotations;
        private final int num_annotations;

        public ParameterAnnotation(AnnotationsAttribute.Annotation[] annotationArr) {
            this.num_annotations = annotationArr.length;
            this.annotations = annotationArr;
        }

        public void writeBody(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeShort(this.num_annotations);
            int i = 0;
            while (true) {
                AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
                if (i < annotationArr.length) {
                    annotationArr[i].writeBody(dataOutputStream);
                    i++;
                } else {
                    return;
                }
            }
        }

        public void resolve(ClassConstantPool classConstantPool) {
            int i = 0;
            while (true) {
                AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
                if (i < annotationArr.length) {
                    annotationArr[i].resolve(classConstantPool);
                    i++;
                } else {
                    return;
                }
            }
        }

        public int getLength() {
            int i = 2;
            int i2 = 0;
            while (true) {
                AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
                if (i2 >= annotationArr.length) {
                    return i;
                }
                i += annotationArr[i2].getLength();
                i2++;
            }
        }

        public List getClassFileEntries() {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                AnnotationsAttribute.Annotation[] annotationArr = this.annotations;
                if (i >= annotationArr.length) {
                    return arrayList;
                }
                arrayList.addAll(annotationArr[i].getClassFileEntries());
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.attributeName);
        int i = 0;
        while (true) {
            ParameterAnnotation[] parameterAnnotationArr = this.parameter_annotations;
            if (i >= parameterAnnotationArr.length) {
                break;
            }
            arrayList.addAll(parameterAnnotationArr[i].getClassFileEntries());
            i++;
        }
        int size = arrayList.size();
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[size];
        for (int i2 = 0; i2 < size; i2++) {
            classFileEntryArr[i2] = (ClassFileEntry) arrayList.get(i2);
        }
        return classFileEntryArr;
    }
}
