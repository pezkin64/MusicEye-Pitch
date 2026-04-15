package org.apache.commons.compress.harmony.unpack200;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TimeZone;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPField;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethod;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFile;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.InnerClassesAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;

public class Segment {
    public static final int LOG_LEVEL_QUIET = 0;
    public static final int LOG_LEVEL_STANDARD = 1;
    public static final int LOG_LEVEL_VERBOSE = 2;
    private AttrDefinitionBands attrDefinitionBands;
    private BcBands bcBands;
    private ClassBands classBands;
    private byte[][] classFilesContents;
    private CpBands cpBands;
    private boolean deflateHint;
    private boolean doPreRead;
    private FileBands fileBands;
    private boolean[] fileDeflate;
    private boolean[] fileIsClass;
    private SegmentHeader header;
    private IcBands icBands;
    private InputStream internalBuffer;
    private int logLevel;
    private PrintWriter logStream;
    private boolean overrideDeflateHint;

    private ClassFile buildClassFile(int i) throws Pack200Exception {
        CPClass cPClass;
        CPUTF8 cputf8;
        CPClass cPClass2;
        String str;
        ClassFile classFile = new ClassFile();
        int[] classVersionMajor = this.classBands.getClassVersionMajor();
        int[] classVersionMinor = this.classBands.getClassVersionMinor();
        if (classVersionMajor != null) {
            classFile.major = classVersionMajor[i];
            classFile.minor = classVersionMinor[i];
        } else {
            classFile.major = this.header.getDefaultClassMajorVersion();
            classFile.minor = this.header.getDefaultClassMinorVersion();
        }
        ClassConstantPool classConstantPool = classFile.pool;
        int i2 = this.classBands.getClassThisInts()[i];
        String str2 = this.cpBands.getCpClass()[i2];
        int lastIndexOf = str2.lastIndexOf("/") + 1;
        ArrayList arrayList = this.classBands.getClassAttributes()[i];
        SourceFileAttribute sourceFileAttribute = null;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            if (((Attribute) arrayList.get(i3)).isSourceFileAttribute()) {
                sourceFileAttribute = (SourceFileAttribute) arrayList.get(i3);
            }
        }
        if (sourceFileAttribute != null) {
            classFile.attributes = new Attribute[]{(Attribute) classConstantPool.add(sourceFileAttribute)};
        } else if (this.attrDefinitionBands.getAttributeDefinitionMap().getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0).matches(this.classBands.getRawClassFlags()[i])) {
            int i4 = -1;
            for (int i5 = 0; i5 < str2.length(); i5++) {
                if (str2.charAt(i5) <= '$') {
                    i4 = i5;
                }
            }
            if (i4 <= -1 || lastIndexOf > i4) {
                str = str2.substring(lastIndexOf) + ".java";
            } else {
                str = str2.substring(lastIndexOf, i4) + ".java";
            }
            classFile.attributes = new Attribute[]{(Attribute) classConstantPool.add(new SourceFileAttribute(this.cpBands.cpUTF8Value(str, false)))};
        } else {
            classFile.attributes = new Attribute[0];
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (int i6 = 0; i6 < arrayList.size(); i6++) {
            Attribute attribute = (Attribute) arrayList.get(i6);
            if (!attribute.isSourceFileAttribute()) {
                arrayList2.add(attribute);
            }
        }
        Attribute[] attributeArr = classFile.attributes;
        classFile.attributes = new Attribute[(attributeArr.length + arrayList2.size())];
        System.arraycopy(attributeArr, 0, classFile.attributes, 0, attributeArr.length);
        for (int i7 = 0; i7 < arrayList2.size(); i7++) {
            Attribute attribute2 = (Attribute) arrayList2.get(i7);
            classConstantPool.add(attribute2);
            classFile.attributes[attributeArr.length + i7] = attribute2;
        }
        ClassFileEntry add = classConstantPool.add(this.cpBands.cpClassValue(i2));
        ClassFileEntry add2 = classConstantPool.add(this.cpBands.cpClassValue(this.classBands.getClassSuperInts()[i]));
        int length = this.classBands.getClassInterfacesInts()[i].length;
        ClassFileEntry[] classFileEntryArr = new ClassFileEntry[length];
        for (int i8 = 0; i8 < length; i8++) {
            classFileEntryArr[i8] = classConstantPool.add(this.cpBands.cpClassValue(this.classBands.getClassInterfacesInts()[i][i8]));
        }
        int i9 = this.classBands.getClassFieldCount()[i];
        ClassFileEntry[] classFileEntryArr2 = new ClassFileEntry[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            int i11 = this.classBands.getFieldDescrInts()[i][i10];
            classFileEntryArr2[i10] = classConstantPool.add(new CPField(this.cpBands.cpUTF8Value(this.cpBands.getCpDescriptorNameInts()[i11]), this.cpBands.cpSignatureValue(this.cpBands.getCpDescriptorTypeInts()[i11]), this.classBands.getFieldFlags()[i][i10], this.classBands.getFieldAttributes()[i][i10]));
        }
        int i12 = this.classBands.getClassMethodCount()[i];
        ClassFileEntry[] classFileEntryArr3 = new ClassFileEntry[i12];
        for (int i13 = 0; i13 < i12; i13++) {
            int i14 = this.classBands.getMethodDescrInts()[i][i13];
            classFileEntryArr3[i13] = classConstantPool.add(new CPMethod(this.cpBands.cpUTF8Value(this.cpBands.getCpDescriptorNameInts()[i14]), this.cpBands.cpSignatureValue(this.cpBands.getCpDescriptorTypeInts()[i14]), this.classBands.getMethodFlags()[i][i13], this.classBands.getMethodAttributes()[i][i13]));
        }
        classConstantPool.addNestedEntries();
        IcTuple[] icTupleArr = getClassBands().getIcLocal()[i];
        boolean z = icTupleArr != null;
        InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(AttributeLayout.ATTRIBUTE_INNER_CLASSES);
        IcTuple[] relevantIcTuples = getIcBands().getRelevantIcTuples(str2, classConstantPool);
        List computeIcStored = computeIcStored(icTupleArr, relevantIcTuples);
        int i15 = 0;
        boolean z2 = false;
        while (i15 < computeIcStored.size()) {
            IcTuple icTuple = (IcTuple) computeIcStored.get(i15);
            boolean z3 = z;
            int thisClassIndex = icTuple.thisClassIndex();
            ClassFileEntry[] classFileEntryArr4 = classFileEntryArr;
            int outerClassIndex = icTuple.outerClassIndex();
            List list = computeIcStored;
            int simpleClassNameIndex = icTuple.simpleClassNameIndex();
            int i16 = i15;
            String thisClassString = icTuple.thisClassString();
            ClassFileEntry[] classFileEntryArr5 = classFileEntryArr3;
            String outerClassString = icTuple.outerClassString();
            ClassFileEntry[] classFileEntryArr6 = classFileEntryArr2;
            String simpleClassName = icTuple.simpleClassName();
            int i17 = length;
            if (thisClassIndex != -1) {
                cPClass = this.cpBands.cpClassValue(thisClassIndex);
            } else {
                cPClass = this.cpBands.cpClassValue(thisClassString);
            }
            if (!icTuple.isAnonymous()) {
                cputf8 = simpleClassNameIndex != -1 ? this.cpBands.cpUTF8Value(simpleClassNameIndex) : this.cpBands.cpUTF8Value(simpleClassName);
            } else {
                cputf8 = null;
            }
            if (icTuple.isMember()) {
                cPClass2 = outerClassIndex != -1 ? this.cpBands.cpClassValue(outerClassIndex) : this.cpBands.cpClassValue(outerClassString);
            } else {
                cPClass2 = null;
            }
            innerClassesAttribute.addInnerClassesEntry(cPClass, cPClass2, cputf8, icTuple.F);
            i15 = i16 + 1;
            z = z3;
            classFileEntryArr = classFileEntryArr4;
            computeIcStored = list;
            classFileEntryArr3 = classFileEntryArr5;
            classFileEntryArr2 = classFileEntryArr6;
            length = i17;
            z2 = true;
        }
        int i18 = length;
        ClassFileEntry[] classFileEntryArr7 = classFileEntryArr3;
        boolean z4 = z;
        ClassFileEntry[] classFileEntryArr8 = classFileEntryArr;
        ClassFileEntry[] classFileEntryArr9 = classFileEntryArr2;
        if (z4 && icTupleArr.length == 0) {
            z2 = false;
        }
        if (!z4 && relevantIcTuples.length == 0) {
            z2 = false;
        }
        if (z2) {
            Attribute[] attributeArr2 = classFile.attributes;
            int length2 = attributeArr2.length;
            Attribute[] attributeArr3 = new Attribute[(length2 + 1)];
            for (int i19 = 0; i19 < attributeArr2.length; i19++) {
                attributeArr3[i19] = attributeArr2[i19];
            }
            attributeArr3[length2] = innerClassesAttribute;
            classFile.attributes = attributeArr3;
            classConstantPool.addWithNestedEntries(innerClassesAttribute);
        }
        classConstantPool.resolve(this);
        classFile.accessFlags = (int) this.classBands.getClassFlags()[i];
        classFile.thisClass = classConstantPool.indexOf(add);
        classFile.superClass = classConstantPool.indexOf(add2);
        int i20 = i18;
        classFile.interfaces = new int[i20];
        for (int i21 = 0; i21 < i20; i21++) {
            classFile.interfaces[i21] = classConstantPool.indexOf(classFileEntryArr8[i21]);
        }
        classFile.fields = classFileEntryArr9;
        classFile.methods = classFileEntryArr7;
        return classFile;
    }

    private List computeIcStored(IcTuple[] icTupleArr, IcTuple[] icTupleArr2) {
        ArrayList arrayList = new ArrayList(icTupleArr2.length);
        ArrayList arrayList2 = new ArrayList(icTupleArr2.length);
        HashSet hashSet = new HashSet(icTupleArr2.length);
        if (icTupleArr != null) {
            for (int i = 0; i < icTupleArr.length; i++) {
                if (hashSet.add(icTupleArr[i])) {
                    arrayList.add(icTupleArr[i]);
                }
            }
        }
        for (int i2 = 0; i2 < icTupleArr2.length; i2++) {
            if (hashSet.add(icTupleArr2[i2])) {
                arrayList.add(icTupleArr2[i2]);
            } else {
                arrayList2.add(icTupleArr2[i2]);
            }
        }
        for (int i3 = 0; i3 < arrayList2.size(); i3++) {
            arrayList.remove((IcTuple) arrayList2.get(i3));
        }
        return arrayList;
    }

    private void readSegment(InputStream inputStream) throws IOException, Pack200Exception {
        log(2, "-------");
        CpBands cpBands2 = new CpBands(this);
        this.cpBands = cpBands2;
        cpBands2.read(inputStream);
        AttrDefinitionBands attrDefinitionBands2 = new AttrDefinitionBands(this);
        this.attrDefinitionBands = attrDefinitionBands2;
        attrDefinitionBands2.read(inputStream);
        IcBands icBands2 = new IcBands(this);
        this.icBands = icBands2;
        icBands2.read(inputStream);
        ClassBands classBands2 = new ClassBands(this);
        this.classBands = classBands2;
        classBands2.read(inputStream);
        BcBands bcBands2 = new BcBands(this);
        this.bcBands = bcBands2;
        bcBands2.read(inputStream);
        FileBands fileBands2 = new FileBands(this);
        this.fileBands = fileBands2;
        fileBands2.read(inputStream);
        this.fileBands.processFileBits();
    }

    private void parseSegment() throws IOException, Pack200Exception {
        this.header.unpack();
        this.cpBands.unpack();
        this.attrDefinitionBands.unpack();
        this.icBands.unpack();
        this.classBands.unpack();
        this.bcBands.unpack();
        this.fileBands.unpack();
        int numberOfFiles = this.header.getNumberOfFiles();
        String[] fileName = this.fileBands.getFileName();
        int[] fileOptions = this.fileBands.getFileOptions();
        SegmentOptions options = this.header.getOptions();
        this.classFilesContents = new byte[numberOfFiles][];
        this.fileDeflate = new boolean[numberOfFiles];
        this.fileIsClass = new boolean[numberOfFiles];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        int i = 0;
        for (int i2 = 0; i2 < numberOfFiles; i2++) {
            String str = fileName[i2];
            boolean z = true;
            boolean z2 = str == null || str.equals(BuildConfig.FLAVOR);
            boolean z3 = (fileOptions[i2] & 2) == 2 || z2;
            if (z3 && z2) {
                fileName[i2] = this.cpBands.getCpClass()[this.classBands.getClassThisInts()[i]] + ".class";
            }
            if (!this.overrideDeflateHint) {
                boolean[] zArr = this.fileDeflate;
                if ((fileOptions[i2] & 1) != 1 && !options.shouldDeflate()) {
                    z = false;
                }
                zArr[i2] = z;
            } else {
                this.fileDeflate[i2] = this.deflateHint;
            }
            this.fileIsClass[i2] = z3;
            if (z3) {
                buildClassFile(i).write(dataOutputStream);
                dataOutputStream.flush();
                this.classFilesContents[i] = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.reset();
                i++;
            }
        }
    }

    public void unpack(InputStream inputStream, JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        unpackRead(inputStream);
        unpackProcess();
        unpackWrite(jarOutputStream);
    }

    /* access modifiers changed from: package-private */
    public void unpackRead(InputStream inputStream) throws IOException, Pack200Exception {
        if (!inputStream.markSupported()) {
            inputStream = new BufferedInputStream(inputStream);
        }
        SegmentHeader segmentHeader = new SegmentHeader(this);
        this.header = segmentHeader;
        segmentHeader.read(inputStream);
        int archiveSize = ((int) this.header.getArchiveSize()) - this.header.getArchiveSizeOffset();
        if (!this.doPreRead || this.header.getArchiveSize() == 0) {
            readSegment(inputStream);
            return;
        }
        byte[] bArr = new byte[archiveSize];
        inputStream.read(bArr);
        this.internalBuffer = new BufferedInputStream(new ByteArrayInputStream(bArr));
    }

    /* access modifiers changed from: package-private */
    public void unpackProcess() throws IOException, Pack200Exception {
        InputStream inputStream = this.internalBuffer;
        if (inputStream != null) {
            readSegment(inputStream);
        }
        parseSegment();
    }

    /* access modifiers changed from: package-private */
    public void unpackWrite(JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        writeJar(jarOutputStream);
        PrintWriter printWriter = this.logStream;
        if (printWriter != null) {
            printWriter.close();
        }
    }

    public void writeJar(JarOutputStream jarOutputStream) throws IOException, Pack200Exception {
        int[] iArr;
        String[] strArr;
        JarOutputStream jarOutputStream2 = jarOutputStream;
        String[] fileName = this.fileBands.getFileName();
        int[] fileModtime = this.fileBands.getFileModtime();
        long[] fileSize = this.fileBands.getFileSize();
        byte[][] fileBits = this.fileBands.getFileBits();
        int numberOfFiles = this.header.getNumberOfFiles();
        long archiveModtime = this.header.getArchiveModtime();
        int i = 0;
        int i2 = 0;
        while (i < numberOfFiles) {
            String str = fileName[i];
            long j = (((long) fileModtime[i]) + archiveModtime) * 1000;
            boolean z = this.fileDeflate[i];
            JarEntry jarEntry = new JarEntry(str);
            if (z) {
                jarEntry.setMethod(8);
                strArr = fileName;
                iArr = fileModtime;
            } else {
                jarEntry.setMethod(0);
                CRC32 crc32 = new CRC32();
                if (this.fileIsClass[i]) {
                    crc32.update(this.classFilesContents[i2]);
                    strArr = fileName;
                    iArr = fileModtime;
                    jarEntry.setSize((long) this.classFilesContents[i2].length);
                } else {
                    strArr = fileName;
                    iArr = fileModtime;
                    crc32.update(fileBits[i]);
                    jarEntry.setSize(fileSize[i]);
                }
                jarEntry.setCrc(crc32.getValue());
            }
            jarEntry.setTime(j - ((long) TimeZone.getDefault().getRawOffset()));
            jarOutputStream2.putNextEntry(jarEntry);
            if (this.fileIsClass[i]) {
                jarEntry.setSize((long) this.classFilesContents[i2].length);
                jarOutputStream2.write(this.classFilesContents[i2]);
                i2++;
            } else {
                jarEntry.setSize(fileSize[i]);
                jarOutputStream2.write(fileBits[i]);
            }
            i++;
            fileName = strArr;
            fileModtime = iArr;
        }
    }

    public SegmentConstantPool getConstantPool() {
        return this.cpBands.getConstantPool();
    }

    public SegmentHeader getSegmentHeader() {
        return this.header;
    }

    public void setPreRead(boolean z) {
        this.doPreRead = z;
    }

    /* access modifiers changed from: protected */
    public AttrDefinitionBands getAttrDefinitionBands() {
        return this.attrDefinitionBands;
    }

    /* access modifiers changed from: protected */
    public ClassBands getClassBands() {
        return this.classBands;
    }

    /* access modifiers changed from: protected */
    public CpBands getCpBands() {
        return this.cpBands;
    }

    /* access modifiers changed from: protected */
    public IcBands getIcBands() {
        return this.icBands;
    }

    public void setLogLevel(int i) {
        this.logLevel = i;
    }

    public void setLogStream(OutputStream outputStream) {
        this.logStream = new PrintWriter(outputStream);
    }

    public void log(int i, String str) {
        if (this.logLevel >= i) {
            this.logStream.println(str);
        }
    }

    public void overrideDeflateHint(boolean z) {
        this.overrideDeflateHint = true;
        this.deflateHint = z;
    }
}
