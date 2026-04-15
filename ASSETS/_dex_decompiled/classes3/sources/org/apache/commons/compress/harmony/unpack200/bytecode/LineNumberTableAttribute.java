package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumberTableAttribute extends BCIRenumberedAttribute {
    private static CPUTF8 attributeName;
    private final int line_number_table_length;
    private final int[] line_numbers;
    private final int[] start_pcs;

    public boolean equals(Object obj) {
        return this == obj;
    }

    public static void setAttributeName(CPUTF8 cputf8) {
        attributeName = cputf8;
    }

    public LineNumberTableAttribute(int i, int[] iArr, int[] iArr2) {
        super(attributeName);
        this.line_number_table_length = i;
        this.start_pcs = iArr;
        this.line_numbers = iArr2;
    }

    /* access modifiers changed from: protected */
    public int getLength() {
        return (this.line_number_table_length * 4) + 2;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.line_number_table_length);
        for (int i = 0; i < this.line_number_table_length; i++) {
            dataOutputStream.writeShort(this.start_pcs[i]);
            dataOutputStream.writeShort(this.line_numbers[i]);
        }
    }

    public String toString() {
        return "LineNumberTable: " + this.line_number_table_length + " lines";
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        return new ClassFileEntry[]{getAttributeName()};
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
    }

    /* access modifiers changed from: protected */
    public int[] getStartPCs() {
        return this.start_pcs;
    }
}
