package org.apache.commons.compress.harmony.unpack200.bytecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.commons.compress.harmony.unpack200.Segment;

public class ClassConstantPool {
    private final List entries = new ArrayList(500);
    protected HashSet entriesContainsSet = new HashSet();
    protected Map indexCache;
    private final HashSet mustStartClassPool = new HashSet();
    private final List others = new ArrayList(500);
    protected HashSet othersContainsSet = new HashSet();
    private boolean resolved;

    public ClassFileEntry add(ClassFileEntry classFileEntry) {
        if (classFileEntry instanceof ByteCode) {
            return null;
        }
        if (classFileEntry instanceof ConstantPoolEntry) {
            if (this.entriesContainsSet.add(classFileEntry)) {
                this.entries.add(classFileEntry);
                return classFileEntry;
            }
        } else if (this.othersContainsSet.add(classFileEntry)) {
            this.others.add(classFileEntry);
        }
        return classFileEntry;
    }

    public void addNestedEntries() {
        ArrayList arrayList = new ArrayList(512);
        ArrayList arrayList2 = new ArrayList(512);
        arrayList.addAll(this.entries);
        arrayList.addAll(this.others);
        boolean z = true;
        while (true) {
            if (z || arrayList.size() > 0) {
                arrayList2.clear();
                int size = this.entries.size();
                int size2 = this.others.size();
                for (int i = 0; i < arrayList.size(); i++) {
                    ClassFileEntry classFileEntry = (ClassFileEntry) arrayList.get(i);
                    ClassFileEntry[] nestedClassFileEntries = classFileEntry.getNestedClassFileEntries();
                    arrayList2.addAll(Arrays.asList(nestedClassFileEntries));
                    if ((classFileEntry instanceof ByteCode) && ((ByteCode) classFileEntry).nestedMustStartClassPool()) {
                        this.mustStartClassPool.addAll(Arrays.asList(nestedClassFileEntries));
                    }
                    add(classFileEntry);
                }
                z = (this.entries.size() == size && this.others.size() == size2) ? false : true;
                arrayList.clear();
                arrayList.addAll(arrayList2);
            } else {
                return;
            }
        }
    }

    public int indexOf(ClassFileEntry classFileEntry) {
        if (this.resolved) {
            Map map = this.indexCache;
            if (map != null) {
                Integer num = (Integer) map.get(classFileEntry);
                if (num != null) {
                    return num.intValue() + 1;
                }
                return -1;
            }
            throw new IllegalStateException("Index cache is not initialized!");
        }
        throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
    }

    public int size() {
        return this.entries.size();
    }

    public ClassFileEntry get(int i) {
        if (this.resolved) {
            return (ClassFileEntry) this.entries.get(i - 1);
        }
        throw new IllegalStateException("Constant pool is not yet resolved; this does not make any sense");
    }

    public void resolve(Segment segment) {
        initialSort();
        sortClassPool();
        this.resolved = true;
        for (int i = 0; i < this.entries.size(); i++) {
            ((ClassFileEntry) this.entries.get(i)).resolve(this);
        }
        for (int i2 = 0; i2 < this.others.size(); i2++) {
            ((ClassFileEntry) this.others.get(i2)).resolve(this);
        }
    }

    private void initialSort() {
        TreeSet treeSet = new TreeSet(new ClassConstantPool$$ExternalSyntheticLambda0());
        TreeSet treeSet2 = new TreeSet(new ClassConstantPool$$ExternalSyntheticLambda1());
        TreeSet treeSet3 = new TreeSet(new ClassConstantPool$$ExternalSyntheticLambda2());
        for (int i = 0; i < this.entries.size(); i++) {
            ConstantPoolEntry constantPoolEntry = (ConstantPoolEntry) this.entries.get(i);
            if (constantPoolEntry.getGlobalIndex() != -1) {
                treeSet.add(constantPoolEntry);
            } else if (constantPoolEntry instanceof CPUTF8) {
                treeSet2.add(constantPoolEntry);
            } else if (constantPoolEntry instanceof CPClass) {
                treeSet3.add(constantPoolEntry);
            } else {
                throw new Error("error");
            }
        }
        this.entries.clear();
        this.entries.addAll(treeSet);
        this.entries.addAll(treeSet2);
        this.entries.addAll(treeSet3);
    }

    static /* synthetic */ int lambda$initialSort$0(Object obj, Object obj2) {
        return ((ConstantPoolEntry) obj).getGlobalIndex() - ((ConstantPoolEntry) obj2).getGlobalIndex();
    }

    public List entries() {
        return Collections.unmodifiableList(this.entries);
    }

    /* access modifiers changed from: protected */
    public void sortClassPool() {
        int i;
        ArrayList arrayList = new ArrayList(this.entries.size());
        ArrayList arrayList2 = new ArrayList(this.entries.size());
        for (int i2 = 0; i2 < this.entries.size(); i2++) {
            ClassFileEntry classFileEntry = (ClassFileEntry) this.entries.get(i2);
            if (this.mustStartClassPool.contains(classFileEntry)) {
                arrayList.add(classFileEntry);
            } else {
                arrayList2.add(classFileEntry);
            }
        }
        this.indexCache = new HashMap(this.entries.size());
        this.entries.clear();
        int i3 = 0;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            ClassFileEntry classFileEntry2 = (ClassFileEntry) arrayList.get(i4);
            this.indexCache.put(classFileEntry2, Integer.valueOf(i3));
            if ((classFileEntry2 instanceof CPLong) || (classFileEntry2 instanceof CPDouble)) {
                this.entries.add(classFileEntry2);
                this.entries.add(classFileEntry2);
                i3 += 2;
            } else {
                this.entries.add(classFileEntry2);
                i3++;
            }
        }
        for (int i5 = 0; i5 < arrayList2.size(); i5++) {
            ClassFileEntry classFileEntry3 = (ClassFileEntry) arrayList2.get(i5);
            this.indexCache.put(classFileEntry3, Integer.valueOf(i3));
            if ((classFileEntry3 instanceof CPLong) || (classFileEntry3 instanceof CPDouble)) {
                this.entries.add(classFileEntry3);
                this.entries.add(classFileEntry3);
                i = i3 + 2;
            } else {
                this.entries.add(classFileEntry3);
                i = i3 + 1;
            }
        }
    }

    public ClassFileEntry addWithNestedEntries(ClassFileEntry classFileEntry) {
        add(classFileEntry);
        ClassFileEntry[] nestedClassFileEntries = classFileEntry.getNestedClassFileEntries();
        for (ClassFileEntry addWithNestedEntries : nestedClassFileEntries) {
            addWithNestedEntries(addWithNestedEntries);
        }
        return classFileEntry;
    }
}
