package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewAttribute extends BCIRenumberedAttribute {
    private final List body = new ArrayList();
    private final int layoutIndex;
    private final List lengths = new ArrayList();
    private ClassConstantPool pool;

    /* access modifiers changed from: protected */
    public int[] getStartPCs() {
        return null;
    }

    public NewAttribute(CPUTF8 cputf8, int i) {
        super(cputf8);
        this.layoutIndex = i;
    }

    public int getLayoutIndex() {
        return this.layoutIndex;
    }

    /* access modifiers changed from: protected */
    public int getLength() {
        int i = 0;
        for (int i2 = 0; i2 < this.lengths.size(); i2++) {
            i += ((Integer) this.lengths.get(i2)).intValue();
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void writeBody(DataOutputStream dataOutputStream) throws IOException {
        long j;
        int i;
        for (int i2 = 0; i2 < this.lengths.size(); i2++) {
            int intValue = ((Integer) this.lengths.get(i2)).intValue();
            Object obj = this.body.get(i2);
            if (obj instanceof Long) {
                j = ((Long) obj).longValue();
            } else {
                if (obj instanceof ClassFileEntry) {
                    i = this.pool.indexOf((ClassFileEntry) obj);
                } else if (obj instanceof BCValue) {
                    i = ((BCValue) obj).actualValue;
                } else {
                    j = 0;
                }
                j = (long) i;
            }
            if (intValue == 1) {
                dataOutputStream.writeByte((int) j);
            } else if (intValue == 2) {
                dataOutputStream.writeShort((int) j);
            } else if (intValue == 4) {
                dataOutputStream.writeInt((int) j);
            } else if (intValue == 8) {
                dataOutputStream.writeLong(j);
            }
        }
    }

    public String toString() {
        return this.attributeName.underlyingString();
    }

    public void addInteger(int i, long j) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(Long.valueOf(j));
    }

    public void addBCOffset(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCOffset(i2));
    }

    public void addBCIndex(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCIndex(i2));
    }

    public void addBCLength(int i, int i2) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(new BCLength(i2));
    }

    public void addToBody(int i, Object obj) {
        this.lengths.add(Integer.valueOf(i));
        this.body.add(obj);
    }

    /* access modifiers changed from: protected */
    public void resolve(ClassConstantPool classConstantPool) {
        super.resolve(classConstantPool);
        for (int i = 0; i < this.body.size(); i++) {
            Object obj = this.body.get(i);
            if (obj instanceof ClassFileEntry) {
                ((ClassFileEntry) obj).resolve(classConstantPool);
            }
        }
        this.pool = classConstantPool;
    }

    /* access modifiers changed from: protected */
    public ClassFileEntry[] getNestedClassFileEntries() {
        int i = 1;
        int i2 = 1;
        for (int i3 = 0; i3 < this.body.size(); i3++) {
            if (this.body.get(i3) instanceof ClassFileEntry) {
                i2++;
            }
        }
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[i2];
        classFileEntryArr[0] = getAttributeName();
        for (int i4 = 0; i4 < this.body.size(); i4++) {
            Object obj = this.body.get(i4);
            if (obj instanceof ClassFileEntry) {
                classFileEntryArr[i] = (ClassFileEntry) obj;
                i++;
            }
        }
        return classFileEntryArr;
    }

    private static class BCOffset extends BCValue {
        /* access modifiers changed from: private */
        public int index;
        /* access modifiers changed from: private */
        public final int offset;

        public BCOffset(int i) {
            super();
            this.offset = i;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    private static class BCIndex extends BCValue {
        /* access modifiers changed from: private */
        public final int index;

        public BCIndex(int i) {
            super();
            this.index = i;
        }
    }

    private static class BCLength extends BCValue {
        /* access modifiers changed from: private */
        public final int length;

        public BCLength(int i) {
            super();
            this.length = i;
        }
    }

    private static abstract class BCValue {
        int actualValue;

        private BCValue() {
        }

        public void setActualValue(int i) {
            this.actualValue = i;
        }
    }

    public void renumber(List list) {
        if (!this.renumbered) {
            BCIndex bCIndex = null;
            for (Object next : this.body) {
                if (next instanceof BCIndex) {
                    BCIndex bCIndex2 = (BCIndex) next;
                    bCIndex2.setActualValue(((Integer) list.get(bCIndex2.index)).intValue());
                } else if (next instanceof BCOffset) {
                    BCOffset bCOffset = (BCOffset) next;
                    if (bCIndex instanceof BCIndex) {
                        int access$100 = bCIndex.index + bCOffset.offset;
                        bCOffset.setIndex(access$100);
                        bCOffset.setActualValue(((Integer) list.get(access$100)).intValue());
                    } else if (bCIndex instanceof BCOffset) {
                        int access$300 = ((BCOffset) bCIndex).index + bCOffset.offset;
                        bCOffset.setIndex(access$300);
                        bCOffset.setActualValue(((Integer) list.get(access$300)).intValue());
                    } else {
                        bCOffset.setActualValue(((Integer) list.get(bCOffset.offset)).intValue());
                    }
                } else if (next instanceof BCLength) {
                    BCLength bCLength = (BCLength) next;
                    BCIndex bCIndex3 = bCIndex;
                    bCLength.setActualValue(((Integer) list.get(bCIndex3.index + bCLength.length)).intValue() - bCIndex3.actualValue);
                }
                bCIndex = next;
            }
            this.renumbered = true;
        }
    }
}
