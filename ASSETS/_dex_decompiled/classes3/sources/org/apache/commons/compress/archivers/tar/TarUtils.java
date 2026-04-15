package org.apache.commons.compress.archivers.tar;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.IOUtils;

public class TarUtils {
    private static final int BYTE_MASK = 255;
    static final ZipEncoding DEFAULT_ENCODING = ZipEncodingHelper.getZipEncoding((String) null);
    static final ZipEncoding FALLBACK_ENCODING = new ZipEncoding() {
        public boolean canEncode(String str) {
            return true;
        }

        public ByteBuffer encode(String str) {
            int length = str.length();
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) str.charAt(i);
            }
            return ByteBuffer.wrap(bArr);
        }

        public String decode(byte[] bArr) {
            StringBuilder sb = new StringBuilder(bArr.length);
            for (byte b : bArr) {
                if (b == 0) {
                    break;
                }
                sb.append((char) (b & 255));
            }
            return sb.toString();
        }
    };

    private TarUtils() {
    }

    public static long parseOctal(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        if (i2 >= 2) {
            long j = 0;
            if (bArr[i] == 0) {
                return 0;
            }
            int i4 = i;
            while (i4 < i3 && bArr[i4] == 32) {
                i4++;
            }
            byte b = bArr[i3 - 1];
            while (i4 < i3 && (b == 0 || b == 32)) {
                b = bArr[i3 - 2];
                i3--;
            }
            while (i4 < i3) {
                byte b2 = bArr[i4];
                if (b2 < 48 || b2 > 55) {
                    throw new IllegalArgumentException(exceptionMessage(bArr, i, i2, i4, b2));
                }
                j = (j << 3) + ((long) (b2 - 48));
                i4++;
            }
            return j;
        }
        throw new IllegalArgumentException("Length " + i2 + " must be at least 2");
    }

    public static long parseOctalOrBinary(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        if ((b & 128) == 0) {
            return parseOctal(bArr, i, i2);
        }
        boolean z = b == -1;
        if (i2 < 9) {
            return parseBinaryLong(bArr, i, i2, z);
        }
        return parseBinaryBigInteger(bArr, i, i2, z);
    }

    private static long parseBinaryLong(byte[] bArr, int i, int i2, boolean z) {
        if (i2 < 9) {
            long j = 0;
            for (int i3 = 1; i3 < i2; i3++) {
                j = (j << 8) + ((long) (bArr[i + i3] & 255));
            }
            if (z) {
                j = (j - 1) ^ (((long) Math.pow(2.0d, ((double) (i2 - 1)) * 8.0d)) - 1);
            }
            return z ? -j : j;
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
    }

    private static long parseBinaryBigInteger(byte[] bArr, int i, int i2, boolean z) {
        int i3 = i2 - 1;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i + 1, bArr2, 0, i3);
        BigInteger bigInteger = new BigInteger(bArr2);
        if (z) {
            bigInteger = bigInteger.add(BigInteger.valueOf(-1)).not();
        }
        if (bigInteger.bitLength() <= 63) {
            long longValue = bigInteger.longValue();
            return z ? -longValue : longValue;
        }
        throw new IllegalArgumentException("At offset " + i + ", " + i2 + " byte binary number exceeds maximum signed long value");
    }

    public static boolean parseBoolean(byte[] bArr, int i) {
        return bArr[i] == 1;
    }

    private static String exceptionMessage(byte[] bArr, int i, int i2, int i3, byte b) {
        String replace = new String(bArr, i, i2).replace("\u0000", "{NUL}");
        return "Invalid byte " + b + " at offset " + (i3 - i) + " in '" + replace + "' len=" + i2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        return parseName(r1, r2, r3, FALLBACK_ENCODING);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String parseName(byte[] r1, int r2, int r3) {
        /*
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = DEFAULT_ENCODING     // Catch:{ IOException -> 0x0007 }
            java.lang.String r1 = parseName(r1, r2, r3, r0)     // Catch:{ IOException -> 0x0007 }
            return r1
        L_0x0007:
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = FALLBACK_ENCODING     // Catch:{ IOException -> 0x000e }
            java.lang.String r1 = parseName(r1, r2, r3, r0)     // Catch:{ IOException -> 0x000e }
            return r1
        L_0x000e:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.parseName(byte[], int, int):java.lang.String");
    }

    public static String parseName(byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int i3 = i;
        int i4 = 0;
        while (i4 < i2 && bArr[i3] != 0) {
            i4++;
            i3++;
        }
        if (i4 <= 0) {
            return BuildConfig.FLAVOR;
        }
        byte[] bArr2 = new byte[i4];
        System.arraycopy(bArr, i, bArr2, 0, i4);
        return zipEncoding.decode(bArr2);
    }

    public static TarArchiveStructSparse parseSparse(byte[] bArr, int i) {
        return new TarArchiveStructSparse(parseOctalOrBinary(bArr, i, 12), parseOctalOrBinary(bArr, i + 12, 12));
    }

    static List<TarArchiveStructSparse> readSparseStructs(byte[] bArr, int i, int i2) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i3 = 0;
        while (i3 < i2) {
            try {
                TarArchiveStructSparse parseSparse = parseSparse(bArr, (i3 * 24) + i);
                if (parseSparse.getOffset() < 0) {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative offset");
                } else if (parseSparse.getNumbytes() >= 0) {
                    arrayList.add(parseSparse);
                    i3++;
                } else {
                    throw new IOException("Corrupted TAR archive, sparse entry with negative numbytes");
                }
            } catch (IllegalArgumentException e) {
                throw new IOException("Corrupted TAR archive, sparse entry is invalid", e);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000d, code lost:
        return formatNameBytes(r1, r2, r3, r4, FALLBACK_ENCODING);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0014, code lost:
        throw new java.lang.RuntimeException(r1);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0007 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int formatNameBytes(java.lang.String r1, byte[] r2, int r3, int r4) {
        /*
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = DEFAULT_ENCODING     // Catch:{ IOException -> 0x0007 }
            int r1 = formatNameBytes(r1, r2, r3, r4, r0)     // Catch:{ IOException -> 0x0007 }
            return r1
        L_0x0007:
            org.apache.commons.compress.archivers.zip.ZipEncoding r0 = FALLBACK_ENCODING     // Catch:{ IOException -> 0x000e }
            int r1 = formatNameBytes(r1, r2, r3, r4, r0)     // Catch:{ IOException -> 0x000e }
            return r1
        L_0x000e:
            r1 = move-exception
            java.lang.RuntimeException r2 = new java.lang.RuntimeException
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.formatNameBytes(java.lang.String, byte[], int, int):int");
    }

    public static int formatNameBytes(String str, byte[] bArr, int i, int i2, ZipEncoding zipEncoding) throws IOException {
        int length = str.length();
        ByteBuffer encode = zipEncoding.encode(str);
        while (encode.limit() > i2 && length > 0) {
            length--;
            encode = zipEncoding.encode(str.substring(0, length));
        }
        int limit = encode.limit() - encode.position();
        System.arraycopy(encode.array(), encode.arrayOffset(), bArr, i, limit);
        while (limit < i2) {
            bArr[i + limit] = 0;
            limit++;
        }
        return i + i2;
    }

    public static void formatUnsignedOctalString(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4 = i2 - 1;
        if (j == 0) {
            i3 = i2 - 2;
            bArr[i4 + i] = TarConstants.LF_NORMAL;
        } else {
            long j2 = j;
            while (i4 >= 0 && j2 != 0) {
                bArr[i + i4] = (byte) (((byte) ((int) (7 & j2))) + TarConstants.LF_NORMAL);
                j2 >>>= 3;
                i4--;
            }
            if (j2 == 0) {
                i3 = i4;
            } else {
                throw new IllegalArgumentException(j + "=" + Long.toOctalString(j) + " will not fit in octal number buffer of length " + i2);
            }
        }
        while (i3 >= 0) {
            bArr[i + i3] = TarConstants.LF_NORMAL;
            i3--;
        }
    }

    public static int formatOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        bArr[(i2 - 1) + i] = 0;
        return i + i2;
    }

    public static int formatLongOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 1;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 32;
        return i + i2;
    }

    public static int formatLongOctalOrBinaryBytes(long j, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        byte[] bArr2;
        long j2 = i2 == 8 ? TarConstants.MAXID : TarConstants.MAXSIZE;
        boolean z = j < 0;
        if (!z && j <= j2) {
            return formatLongOctalBytes(j, bArr, i, i2);
        }
        if (i2 < 9) {
            bArr2 = bArr;
            i4 = i;
            i3 = i2;
            formatLongBinary(j, bArr2, i4, i3, z);
        } else {
            bArr2 = bArr;
            i4 = i;
            i3 = i2;
            formatBigIntegerBinary(j, bArr2, i4, i3, z);
        }
        bArr2[i4] = (byte) (z ? 255 : 128);
        return i4 + i3;
    }

    private static void formatLongBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        int i3 = (i2 - 1) * 8;
        long j2 = 1 << i3;
        long abs = Math.abs(j);
        if (abs < 0 || abs >= j2) {
            throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
        }
        if (z) {
            abs = ((abs ^ (j2 - 1)) + 1) | (255 << i3);
        }
        for (int i4 = (i2 + i) - 1; i4 >= i; i4--) {
            bArr[i4] = (byte) ((int) abs);
            abs >>= 8;
        }
    }

    private static void formatBigIntegerBinary(long j, byte[] bArr, int i, int i2, boolean z) {
        byte[] byteArray = BigInteger.valueOf(j).toByteArray();
        int length = byteArray.length;
        if (length <= i2 - 1) {
            int i3 = (i2 + i) - length;
            int i4 = 0;
            System.arraycopy(byteArray, 0, bArr, i3, length);
            if (z) {
                i4 = 255;
            }
            byte b = (byte) i4;
            while (true) {
                i++;
                if (i < i3) {
                    bArr[i] = b;
                } else {
                    return;
                }
            }
        } else {
            throw new IllegalArgumentException("Value " + j + " is too large for " + i2 + " byte field.");
        }
    }

    public static int formatCheckSumOctalBytes(long j, byte[] bArr, int i, int i2) {
        int i3 = i2 - 2;
        formatUnsignedOctalString(j, bArr, i, i3);
        bArr[i3 + i] = 0;
        bArr[(i2 - 1) + i] = 32;
        return i + i2;
    }

    public static long computeCheckSum(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j += (long) (b & 255);
        }
        return j;
    }

    public static boolean verifyCheckSum(byte[] bArr) {
        long parseOctal = parseOctal(bArr, TarConstants.CHKSUM_OFFSET, 8);
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            byte b = bArr[i];
            if (148 <= i && i < 156) {
                b = 32;
            }
            j += (long) (b & 255);
            j2 += (long) b;
        }
        if (parseOctal == j || parseOctal == j2) {
            return true;
        }
        return false;
    }

    @Deprecated
    protected static Map<String, String> parsePaxHeaders(InputStream inputStream, List<TarArchiveStructSparse> list, Map<String, String> map) throws IOException {
        return parsePaxHeaders(inputStream, list, map, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0155, code lost:
        r2 = -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.util.Map<java.lang.String, java.lang.String> parsePaxHeaders(java.io.InputStream r18, java.util.List<org.apache.commons.compress.archivers.tar.TarArchiveStructSparse> r19, java.util.Map<java.lang.String, java.lang.String> r20, long r21) throws java.io.IOException {
        /*
            r0 = r19
            java.util.HashMap r1 = new java.util.HashMap
            r2 = r20
            r1.<init>(r2)
            r3 = 0
            r4 = r3
            r5 = 0
        L_0x000c:
            r6 = r3
            r7 = r6
        L_0x000e:
            int r8 = r18.read()
            r9 = -1
            r10 = 0
            if (r8 == r9) goto L_0x0153
            r12 = 1
            int r6 = r6 + r12
            int r4 = r4 + 1
            r13 = 10
            if (r8 != r13) goto L_0x0024
            r2 = r9
            r9 = r18
            goto L_0x0156
        L_0x0024:
            r14 = 32
            if (r8 != r14) goto L_0x0139
            java.io.ByteArrayOutputStream r8 = new java.io.ByteArrayOutputStream
            r8.<init>()
        L_0x002d:
            int r14 = r18.read()
            if (r14 == r9) goto L_0x0135
            int r6 = r6 + r12
            int r4 = r4 + 1
            if (r4 < 0) goto L_0x0135
            int r15 = (r21 > r10 ? 1 : (r21 == r10 ? 0 : -1))
            if (r15 < 0) goto L_0x0043
            long r9 = (long) r4
            int r9 = (r9 > r21 ? 1 : (r9 == r21 ? 0 : -1))
            if (r9 < 0) goto L_0x0043
            goto L_0x0135
        L_0x0043:
            r9 = 61
            if (r14 != r9) goto L_0x0129
            java.lang.String r9 = "UTF-8"
            java.lang.String r8 = r8.toString(r9)
            int r7 = r7 - r6
            if (r7 > r12) goto L_0x0055
            r1.remove(r8)
            goto L_0x0135
        L_0x0055:
            if (r15 < 0) goto L_0x0079
            long r9 = (long) r7
            long r11 = (long) r4
            long r11 = r21 - r11
            int r6 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x0060
            goto L_0x0079
        L_0x0060:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Paxheader value size "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r2 = " exceeds size of header record"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0079:
            r9 = r18
            byte[] r6 = org.apache.commons.compress.utils.IOUtils.readRange((java.io.InputStream) r9, (int) r7)
            int r10 = r6.length
            if (r10 != r7) goto L_0x010d
            int r4 = r4 + r7
            int r7 = r7 + -1
            byte r10 = r6[r7]
            if (r10 != r13) goto L_0x0105
            java.lang.String r10 = new java.lang.String
            java.nio.charset.Charset r11 = java.nio.charset.StandardCharsets.UTF_8
            r10.<init>(r6, r3, r7, r11)
            r1.put(r8, r10)
            java.lang.String r6 = "GNU.sparse.offset"
            boolean r6 = r8.equals(r6)
            if (r6 == 0) goto L_0x00cb
            if (r5 == 0) goto L_0x00ac
            org.apache.commons.compress.archivers.tar.TarArchiveStructSparse r6 = new org.apache.commons.compress.archivers.tar.TarArchiveStructSparse
            long r11 = r5.longValue()
            r2 = 0
            r6.<init>(r11, r2)
            r0.add(r6)
            goto L_0x00ae
        L_0x00ac:
            r2 = 0
        L_0x00ae:
            java.lang.Long r5 = java.lang.Long.valueOf(r10)     // Catch:{ NumberFormatException -> 0x00c3 }
            long r6 = r5.longValue()
            int r6 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r6 < 0) goto L_0x00bb
            goto L_0x00cb
        L_0x00bb:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.GNU.sparse.offset contains negative value"
            r0.<init>(r1)
            throw r0
        L_0x00c3:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.GNU.sparse.offset contains a non-numeric value"
            r0.<init>(r1)
            throw r0
        L_0x00cb:
            java.lang.String r2 = "GNU.sparse.numbytes"
            boolean r2 = r8.equals(r2)
            if (r2 == 0) goto L_0x0137
            if (r5 == 0) goto L_0x00fd
            long r2 = java.lang.Long.parseLong(r10)     // Catch:{ NumberFormatException -> 0x00f5 }
            r16 = 0
            int r6 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r6 < 0) goto L_0x00ed
            org.apache.commons.compress.archivers.tar.TarArchiveStructSparse r6 = new org.apache.commons.compress.archivers.tar.TarArchiveStructSparse
            long r7 = r5.longValue()
            r6.<init>(r7, r2)
            r0.add(r6)
            r5 = 0
            goto L_0x0137
        L_0x00ed:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.GNU.sparse.numbytes contains negative value"
            r0.<init>(r1)
            throw r0
        L_0x00f5:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.GNU.sparse.numbytes contains a non-numeric value."
            r0.<init>(r1)
            throw r0
        L_0x00fd:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.GNU.sparse.offset is expected before GNU.sparse.numbytes shows up."
            r0.<init>(r1)
            throw r0
        L_0x0105:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader.Value should end with a newline"
            r0.<init>(r1)
            throw r0
        L_0x010d:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Failed to read Paxheader. Expected "
            r1.<init>(r2)
            r1.append(r7)
            java.lang.String r2 = " bytes, read "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0129:
            r9 = r18
            byte r2 = (byte) r14
            r8.write(r2)
            r3 = 0
            r9 = -1
            r10 = 0
            goto L_0x002d
        L_0x0135:
            r9 = r18
        L_0x0137:
            r8 = r14
            goto L_0x0155
        L_0x0139:
            r9 = r18
            r2 = 48
            if (r8 < r2) goto L_0x014b
            r2 = 57
            if (r8 > r2) goto L_0x014b
            int r7 = r7 * 10
            int r8 = r8 + -48
            int r7 = r7 + r8
            r3 = 0
            goto L_0x000e
        L_0x014b:
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to read Paxheader. Encountered a non-number while reading length"
            r0.<init>(r1)
            throw r0
        L_0x0153:
            r9 = r18
        L_0x0155:
            r2 = -1
        L_0x0156:
            if (r8 != r2) goto L_0x0169
            if (r5 == 0) goto L_0x0168
            org.apache.commons.compress.archivers.tar.TarArchiveStructSparse r2 = new org.apache.commons.compress.archivers.tar.TarArchiveStructSparse
            long r3 = r5.longValue()
            r5 = 0
            r2.<init>(r3, r5)
            r0.add(r2)
        L_0x0168:
            return r1
        L_0x0169:
            r3 = 0
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.compress.archivers.tar.TarUtils.parsePaxHeaders(java.io.InputStream, java.util.List, java.util.Map, long):java.util.Map");
    }

    protected static List<TarArchiveStructSparse> parsePAX01SparseHeaders(String str) {
        try {
            return parseFromPAX01SparseHeaders(str);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected static List<TarArchiveStructSparse> parseFromPAX01SparseHeaders(String str) throws IOException {
        ArrayList arrayList = new ArrayList();
        String[] split = str.split(",");
        if (split.length % 2 != 1) {
            int i = 0;
            while (i < split.length) {
                try {
                    long parseLong = Long.parseLong(split[i]);
                    if (parseLong >= 0) {
                        try {
                            long parseLong2 = Long.parseLong(split[i + 1]);
                            if (parseLong2 >= 0) {
                                arrayList.add(new TarArchiveStructSparse(parseLong, parseLong2));
                                i += 2;
                            } else {
                                throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains negative value");
                            }
                        } catch (NumberFormatException unused) {
                            throw new IOException("Corrupted TAR archive. Sparse struct numbytes contains a non-numeric value");
                        }
                    } else {
                        throw new IOException("Corrupted TAR archive. Sparse struct offset contains negative value");
                    }
                } catch (NumberFormatException unused2) {
                    throw new IOException("Corrupted TAR archive. Sparse struct offset contains a non-numeric value");
                }
            }
            return Collections.unmodifiableList(arrayList);
        }
        throw new IOException("Corrupted TAR archive. Bad format in GNU.sparse.map PAX Header");
    }

    protected static List<TarArchiveStructSparse> parsePAX1XSparseHeaders(InputStream inputStream, int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        long[] readLineOfNumberForPax1X = readLineOfNumberForPax1X(inputStream);
        long j = readLineOfNumberForPax1X[0];
        if (j >= 0) {
            long j2 = readLineOfNumberForPax1X[1];
            while (true) {
                long j3 = j - 1;
                if (j > 0) {
                    long[] readLineOfNumberForPax1X2 = readLineOfNumberForPax1X(inputStream);
                    long j4 = readLineOfNumberForPax1X2[0];
                    if (j4 >= 0) {
                        long j5 = j2 + readLineOfNumberForPax1X2[1];
                        long[] readLineOfNumberForPax1X3 = readLineOfNumberForPax1X(inputStream);
                        long j6 = readLineOfNumberForPax1X3[0];
                        if (j6 >= 0) {
                            j2 = j5 + readLineOfNumberForPax1X3[1];
                            arrayList.add(new TarArchiveStructSparse(j4, j6));
                            j = j3;
                        } else {
                            throw new IOException("Corrupted TAR archive. Sparse header block numbytes contains negative value");
                        }
                    } else {
                        throw new IOException("Corrupted TAR archive. Sparse header block offset contains negative value");
                    }
                } else {
                    long j7 = (long) i;
                    IOUtils.skip(inputStream, j7 - (j2 % j7));
                    return arrayList;
                }
            }
        } else {
            throw new IOException("Corrupted TAR archive. Negative value in sparse headers block");
        }
    }

    private static long[] readLineOfNumberForPax1X(InputStream inputStream) throws IOException {
        long j = 0;
        long j2 = 0;
        while (true) {
            int read = inputStream.read();
            if (read != 10) {
                j++;
                if (read == -1) {
                    throw new IOException("Unexpected EOF when reading parse information of 1.X PAX format");
                } else if (read >= 48 && read <= 57) {
                    j2 = (j2 * 10) + ((long) (read - 48));
                }
            } else {
                return new long[]{j2, j + 1};
            }
        }
        throw new IOException("Corrupted TAR archive. Non-numeric value in sparse headers block");
    }
}
