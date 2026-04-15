package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

public class ExtraFieldUtils {
    static final ZipExtraField[] EMPTY_ZIP_EXTRA_FIELD_ARRAY = new ZipExtraField[0];
    private static final int WORD = 4;
    private static final Map<ZipShort, Class<?>> implementations = new ConcurrentHashMap();

    static {
        register(AsiExtraField.class);
        register(X5455_ExtendedTimestamp.class);
        register(X7875_NewUnix.class);
        register(JarMarker.class);
        register(UnicodePathExtraField.class);
        register(UnicodeCommentExtraField.class);
        register(Zip64ExtendedInformationExtraField.class);
        register(X000A_NTFS.class);
        register(X0014_X509Certificates.class);
        register(X0015_CertificateIdForFile.class);
        register(X0016_CertificateIdForCentralDirectory.class);
        register(X0017_StrongEncryptionHeader.class);
        register(X0019_EncryptionRecipientCertificateList.class);
        register(ResourceAlignmentExtraField.class);
    }

    public static void register(Class<?> cls) {
        try {
            implementations.put(((ZipExtraField) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException unused) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (InstantiationException unused2) {
            throw new RuntimeException(cls + " is not a concrete class");
        } catch (IllegalAccessException unused3) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        }
    }

    public static ZipExtraField createExtraField(ZipShort zipShort) throws InstantiationException, IllegalAccessException {
        ZipExtraField createExtraFieldNoDefault = createExtraFieldNoDefault(zipShort);
        if (createExtraFieldNoDefault != null) {
            return createExtraFieldNoDefault;
        }
        UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
        unrecognizedExtraField.setHeaderId(zipShort);
        return unrecognizedExtraField;
    }

    public static ZipExtraField createExtraFieldNoDefault(ZipShort zipShort) throws InstantiationException, IllegalAccessException {
        Class cls = implementations.get(zipShort);
        if (cls != null) {
            return (ZipExtraField) cls.newInstance();
        }
        return null;
    }

    public static ZipExtraField[] parse(byte[] bArr) throws ZipException {
        return parse(bArr, true, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z) throws ZipException {
        return parse(bArr, z, UnparseableExtraField.THROW);
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z, final UnparseableExtraField unparseableExtraField) throws ZipException {
        return parse(bArr, z, (ExtraFieldParsingBehavior) new ExtraFieldParsingBehavior() {
            public ZipExtraField onUnparseableExtraField(byte[] bArr, int i, int i2, boolean z, int i3) throws ZipException {
                return unparseableExtraField.onUnparseableExtraField(bArr, i, i2, z, i3);
            }

            public ZipExtraField createExtraField(ZipShort zipShort) throws ZipException, InstantiationException, IllegalAccessException {
                return ExtraFieldUtils.createExtraField(zipShort);
            }

            public ZipExtraField fill(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) throws ZipException {
                return ExtraFieldUtils.fillExtraField(zipExtraField, bArr, i, i2, z);
            }
        });
    }

    public static ZipExtraField[] parse(byte[] bArr, boolean z, ExtraFieldParsingBehavior extraFieldParsingBehavior) throws ZipException {
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        int i = 0;
        while (true) {
            if (i > length - 4) {
                break;
            }
            ZipShort zipShort = new ZipShort(bArr, i);
            int value = new ZipShort(bArr, i + 2).getValue();
            int i2 = i + 4;
            if (i2 + value > length) {
                ZipExtraField onUnparseableExtraField = extraFieldParsingBehavior.onUnparseableExtraField(bArr, i, length - i, z, value);
                if (onUnparseableExtraField != null) {
                    arrayList.add(onUnparseableExtraField);
                }
            } else {
                ExtraFieldParsingBehavior extraFieldParsingBehavior2 = extraFieldParsingBehavior;
                try {
                    ExtraFieldParsingBehavior extraFieldParsingBehavior3 = extraFieldParsingBehavior2;
                    boolean z2 = z;
                    int i3 = value;
                    byte[] bArr2 = bArr;
                    ZipExtraField fill = extraFieldParsingBehavior3.fill((ZipExtraField) Objects.requireNonNull(extraFieldParsingBehavior2.createExtraField(zipShort), "createExtraField must not return null"), bArr2, i2, i3, z2);
                    ExtraFieldParsingBehavior extraFieldParsingBehavior4 = extraFieldParsingBehavior3;
                    byte[] bArr3 = bArr2;
                    int i4 = i3;
                    boolean z3 = z2;
                    arrayList.add(Objects.requireNonNull(fill, "fill must not return null"));
                    i += i4 + 4;
                    extraFieldParsingBehavior = extraFieldParsingBehavior4;
                    bArr = bArr3;
                    z = z3;
                } catch (IllegalAccessException | InstantiationException e) {
                    ReflectiveOperationException reflectiveOperationException = e;
                    throw ((ZipException) new ZipException(reflectiveOperationException.getMessage()).initCause(reflectiveOperationException));
                }
            }
        }
        return (ZipExtraField[]) arrayList.toArray(EMPTY_ZIP_EXTRA_FIELD_ARRAY);
    }

    public static byte[] mergeLocalFileDataData(ZipExtraField[] zipExtraFieldArr) {
        byte[] localFileDataData;
        int length = zipExtraFieldArr.length;
        boolean z = length > 0 && (zipExtraFieldArr[length + -1] instanceof UnparseableExtraFieldData);
        int i = z ? length - 1 : length;
        int i2 = i * 4;
        for (ZipExtraField localFileDataLength : zipExtraFieldArr) {
            i2 += localFileDataLength.getLocalFileDataLength().getValue();
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            System.arraycopy(zipExtraFieldArr[i4].getHeaderId().getBytes(), 0, bArr, i3, 2);
            System.arraycopy(zipExtraFieldArr[i4].getLocalFileDataLength().getBytes(), 0, bArr, i3 + 2, 2);
            i3 += 4;
            byte[] localFileDataData2 = zipExtraFieldArr[i4].getLocalFileDataData();
            if (localFileDataData2 != null) {
                System.arraycopy(localFileDataData2, 0, bArr, i3, localFileDataData2.length);
                i3 += localFileDataData2.length;
            }
        }
        if (z && (localFileDataData = zipExtraFieldArr[length - 1].getLocalFileDataData()) != null) {
            System.arraycopy(localFileDataData, 0, bArr, i3, localFileDataData.length);
        }
        return bArr;
    }

    public static byte[] mergeCentralDirectoryData(ZipExtraField[] zipExtraFieldArr) {
        byte[] centralDirectoryData;
        int length = zipExtraFieldArr.length;
        boolean z = length > 0 && (zipExtraFieldArr[length + -1] instanceof UnparseableExtraFieldData);
        int i = z ? length - 1 : length;
        int i2 = i * 4;
        for (ZipExtraField centralDirectoryLength : zipExtraFieldArr) {
            i2 += centralDirectoryLength.getCentralDirectoryLength().getValue();
        }
        byte[] bArr = new byte[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            System.arraycopy(zipExtraFieldArr[i4].getHeaderId().getBytes(), 0, bArr, i3, 2);
            System.arraycopy(zipExtraFieldArr[i4].getCentralDirectoryLength().getBytes(), 0, bArr, i3 + 2, 2);
            i3 += 4;
            byte[] centralDirectoryData2 = zipExtraFieldArr[i4].getCentralDirectoryData();
            if (centralDirectoryData2 != null) {
                System.arraycopy(centralDirectoryData2, 0, bArr, i3, centralDirectoryData2.length);
                i3 += centralDirectoryData2.length;
            }
        }
        if (z && (centralDirectoryData = zipExtraFieldArr[length - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(centralDirectoryData, 0, bArr, i3, centralDirectoryData.length);
        }
        return bArr;
    }

    public static ZipExtraField fillExtraField(ZipExtraField zipExtraField, byte[] bArr, int i, int i2, boolean z) throws ZipException {
        if (z) {
            try {
                zipExtraField.parseFromLocalFileData(bArr, i, i2);
                return zipExtraField;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw ((ZipException) new ZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(zipExtraField.getHeaderId().getValue())).initCause(e));
            }
        } else {
            zipExtraField.parseFromCentralDirectoryData(bArr, i, i2);
            return zipExtraField;
        }
    }

    public static final class UnparseableExtraField implements UnparseableExtraFieldBehavior {
        public static final UnparseableExtraField READ = new UnparseableExtraField(2);
        public static final int READ_KEY = 2;
        public static final UnparseableExtraField SKIP = new UnparseableExtraField(1);
        public static final int SKIP_KEY = 1;
        public static final UnparseableExtraField THROW = new UnparseableExtraField(0);
        public static final int THROW_KEY = 0;
        private final int key;

        private UnparseableExtraField(int i) {
            this.key = i;
        }

        public int getKey() {
            return this.key;
        }

        public ZipExtraField onUnparseableExtraField(byte[] bArr, int i, int i2, boolean z, int i3) throws ZipException {
            int i4 = this.key;
            if (i4 == 0) {
                StringBuilder sb = new StringBuilder("Bad extra field starting at ");
                sb.append(i);
                sb.append(".  Block length of ");
                sb.append(i3);
                sb.append(" bytes exceeds remaining data of ");
                sb.append(i2 - 4);
                sb.append(" bytes.");
                throw new ZipException(sb.toString());
            } else if (i4 == 1) {
                return null;
            } else {
                if (i4 == 2) {
                    UnparseableExtraFieldData unparseableExtraFieldData = new UnparseableExtraFieldData();
                    if (z) {
                        unparseableExtraFieldData.parseFromLocalFileData(bArr, i, i2);
                        return unparseableExtraFieldData;
                    }
                    unparseableExtraFieldData.parseFromCentralDirectoryData(bArr, i, i2);
                    return unparseableExtraFieldData;
                }
                throw new ZipException("Unknown UnparseableExtraField key: " + this.key);
            }
        }
    }
}
