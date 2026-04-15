package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InnerClassesAttribute extends Attribute {
    private static CPUTF8 attributeName;
    private final List innerClasses = new ArrayList();
    private final List nestedClassFileEntries;

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    private static class InnerClassesEntry {
        int inner_class_access_flags;
        CPClass inner_class_info;
        int inner_class_info_index = -1;
        CPUTF8 inner_class_name;
        int inner_name_index = -1;
        CPClass outer_class_info;
        int outer_class_info_index = -1;

        public InnerClassesEntry(CPClass cPClass, CPClass cPClass2, CPUTF8 cputf8, int i) {
            this.inner_class_info = cPClass;
            this.outer_class_info = cPClass2;
            this.inner_class_name = cputf8;
            this.inner_class_access_flags = i;
        }

        public void resolve(ClassConstantPool classConstantPool) {
            CPClass cPClass = this.inner_class_info;
            if (cPClass != null) {
                cPClass.resolve(classConstantPool);
                this.inner_class_info_index = classConstantPool.indexOf(this.inner_class_info);
            } else {
                this.inner_class_info_index = 0;
            }
            CPUTF8 cputf8 = this.inner_class_name;
            if (cputf8 != null) {
                cputf8.resolve(classConstantPool);
                this.inner_name_index = classConstantPool.indexOf(this.inner_class_name);
            } else {
                this.inner_name_index = 0;
            }
            CPClass cPClass2 = this.outer_class_info;
            if (cPClass2 != null) {
                cPClass2.resolve(classConstantPool);
                this.outer_class_info_index = classConstantPool.indexOf(this.outer_class_info);
                return;
            }
            this.outer_class_info_index = 0;
        }

        public void write(DataOutputStream dataOutputStream) throws IOException {
            dataOutputStream.writeShort(this.inner_class_info_index);
            dataOutputStream.writeShort(this.outer_class_info_index);
            dataOutputStream.writeShort(this.inner_name_index);
            dataOutputStream.writeShort(this.inner_class_access_flags);
        }
    }

    public InnerClassesAttribute(String str) {
        super(attributeName);
        ArrayList arrayList = new ArrayList();
        this.nestedClassFileEntries = arrayList;
        arrayList.add(getAttributeName());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj) || getClass() != obj.getClass()) {
            return false;
        }
        InnerClassesAttribute innerClassesAttribute = (InnerClassesAttribute) obj;
        if (getAttributeName() == null) {
            if (innerClassesAttribute.getAttributeName() != null) {
                return false;
            }
        } else if (!getAttributeName().equals(innerClassesAttribute.getAttributeName())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public int getLength() {
        return (this.innerClasses.size() * 8) + 2;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        int size = this.nestedClassFileEntries.size();
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[size];
        for (int i = 0; i < size; i++) {
            classFileEntryArr[i] = (ClassFileEntry) this.nestedClassFileEntries.get(i);
        }
        return classFileEntryArr;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + (getAttributeName() == null ? 0 : getAttributeName().hashCode());
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        for (int i = 0; i < this.innerClasses.size(); i++) {
            ((InnerClassesEntry) this.innerClasses.get(i)).resolve(classConstantPool);
        }
    }

    public String toString() {
        return "InnerClasses: " + getAttributeName();
    }

    /* access modifiers changed from: protected */
    public void doWrite(DataOutputStream dataOutputStream) throws IOException {
        super.doWrite(dataOutputStream);
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.innerClasses.size());
        for (int i = 0; i < this.innerClasses.size(); i++) {
            ((InnerClassesEntry) this.innerClasses.get(i)).write(dataOutputStream);
        }
    }

    public void addInnerClassesEntry(CPClass cPClass, CPClass cPClass2, CPUTF8 cputf8, int i) {
        if (cPClass != null) {
            this.nestedClassFileEntries.add(cPClass);
        }
        if (cPClass2 != null) {
            this.nestedClassFileEntries.add(cPClass2);
        }
        if (cputf8 != null) {
            this.nestedClassFileEntries.add(cputf8);
        }
        addInnerClassesEntry(new InnerClassesEntry(cPClass, cPClass2, cputf8, i));
    }

    private void addInnerClassesEntry(InnerClassesEntry innerClassesEntry) {
        this.innerClasses.add(innerClassesEntry);
    }
}
