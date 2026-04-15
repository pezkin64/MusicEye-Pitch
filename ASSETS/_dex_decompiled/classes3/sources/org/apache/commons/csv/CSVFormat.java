package org.apache.commons.csv;

import com.google.firebase.encoders.json.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import kotlin.text.Typography;

public final class CSVFormat implements Serializable {
    public static final CSVFormat DEFAULT;
    public static final CSVFormat EXCEL;
    public static final CSVFormat INFORMIX_UNLOAD;
    public static final CSVFormat INFORMIX_UNLOAD_CSV;
    public static final CSVFormat MONGODB_CSV;
    public static final CSVFormat MONGODB_TSV;
    public static final CSVFormat MYSQL;
    public static final CSVFormat ORACLE;
    public static final CSVFormat POSTGRESQL_CSV;
    public static final CSVFormat POSTGRESQL_TEXT;
    public static final CSVFormat RFC4180;
    public static final CSVFormat TDF;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public final boolean allowDuplicateHeaderNames;
    /* access modifiers changed from: private */
    public final boolean allowMissingColumnNames;
    /* access modifiers changed from: private */
    public final boolean autoFlush;
    /* access modifiers changed from: private */
    public final Character commentMarker;
    /* access modifiers changed from: private */
    public final String delimiter;
    /* access modifiers changed from: private */
    public final Character escapeCharacter;
    /* access modifiers changed from: private */
    public final String[] header;
    /* access modifiers changed from: private */
    public final String[] headerComments;
    /* access modifiers changed from: private */
    public final boolean ignoreEmptyLines;
    /* access modifiers changed from: private */
    public final boolean ignoreHeaderCase;
    /* access modifiers changed from: private */
    public final boolean ignoreSurroundingSpaces;
    /* access modifiers changed from: private */
    public final String nullString;
    /* access modifiers changed from: private */
    public final Character quoteCharacter;
    /* access modifiers changed from: private */
    public final QuoteMode quoteMode;
    /* access modifiers changed from: private */
    public final String quotedNullString;
    /* access modifiers changed from: private */
    public final String recordSeparator;
    /* access modifiers changed from: private */
    public final boolean skipHeaderRecord;
    /* access modifiers changed from: private */
    public final boolean trailingDelimiter;
    /* access modifiers changed from: private */
    public final boolean trim;

    private static boolean isLineBreak(char c) {
        return c == 10 || c == 13;
    }

    /* synthetic */ CSVFormat(Builder builder, AnonymousClass1 r2) {
        this(builder);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public boolean allowDuplicateHeaderNames;
        /* access modifiers changed from: private */
        public boolean allowMissingColumnNames;
        /* access modifiers changed from: private */
        public boolean autoFlush;
        /* access modifiers changed from: private */
        public Character commentMarker;
        /* access modifiers changed from: private */
        public String delimiter;
        /* access modifiers changed from: private */
        public Character escapeCharacter;
        /* access modifiers changed from: private */
        public String[] headerComments;
        /* access modifiers changed from: private */
        public String[] headers;
        /* access modifiers changed from: private */
        public boolean ignoreEmptyLines;
        /* access modifiers changed from: private */
        public boolean ignoreHeaderCase;
        /* access modifiers changed from: private */
        public boolean ignoreSurroundingSpaces;
        /* access modifiers changed from: private */
        public String nullString;
        /* access modifiers changed from: private */
        public Character quoteCharacter;
        /* access modifiers changed from: private */
        public QuoteMode quoteMode;
        /* access modifiers changed from: private */
        public String quotedNullString;
        /* access modifiers changed from: private */
        public String recordSeparator;
        /* access modifiers changed from: private */
        public boolean skipHeaderRecord;
        /* access modifiers changed from: private */
        public boolean trailingDelimiter;
        /* access modifiers changed from: private */
        public boolean trim;

        public static Builder create() {
            return new Builder(CSVFormat.DEFAULT);
        }

        public static Builder create(CSVFormat cSVFormat) {
            return new Builder(cSVFormat);
        }

        private Builder(CSVFormat cSVFormat) {
            this.delimiter = cSVFormat.delimiter;
            this.quoteCharacter = cSVFormat.quoteCharacter;
            this.quoteMode = cSVFormat.quoteMode;
            this.commentMarker = cSVFormat.commentMarker;
            this.escapeCharacter = cSVFormat.escapeCharacter;
            this.ignoreSurroundingSpaces = cSVFormat.ignoreSurroundingSpaces;
            this.allowMissingColumnNames = cSVFormat.allowMissingColumnNames;
            this.ignoreEmptyLines = cSVFormat.ignoreEmptyLines;
            this.recordSeparator = cSVFormat.recordSeparator;
            this.nullString = cSVFormat.nullString;
            this.headerComments = cSVFormat.headerComments;
            this.headers = cSVFormat.header;
            this.skipHeaderRecord = cSVFormat.skipHeaderRecord;
            this.ignoreHeaderCase = cSVFormat.ignoreHeaderCase;
            this.trailingDelimiter = cSVFormat.trailingDelimiter;
            this.trim = cSVFormat.trim;
            this.autoFlush = cSVFormat.autoFlush;
            this.quotedNullString = cSVFormat.quotedNullString;
            this.allowDuplicateHeaderNames = cSVFormat.allowDuplicateHeaderNames;
        }

        public CSVFormat build() {
            return new CSVFormat(this, (AnonymousClass1) null);
        }

        public Builder setAllowDuplicateHeaderNames(boolean z) {
            this.allowDuplicateHeaderNames = z;
            return this;
        }

        public Builder setAllowMissingColumnNames(boolean z) {
            this.allowMissingColumnNames = z;
            return this;
        }

        public Builder setAutoFlush(boolean z) {
            this.autoFlush = z;
            return this;
        }

        public Builder setCommentMarker(char c) {
            setCommentMarker(Character.valueOf(c));
            return this;
        }

        public Builder setCommentMarker(Character ch) {
            if (!CSVFormat.isLineBreak(ch)) {
                this.commentMarker = ch;
                return this;
            }
            throw new IllegalArgumentException("The comment start marker character cannot be a line break");
        }

        public Builder setDelimiter(char c) {
            return setDelimiter(String.valueOf(c));
        }

        public Builder setDelimiter(String str) {
            if (!CSVFormat.containsLineBreak(str)) {
                this.delimiter = str;
                return this;
            }
            throw new IllegalArgumentException("The delimiter cannot be a line break");
        }

        public Builder setEscape(char c) {
            setEscape(Character.valueOf(c));
            return this;
        }

        public Builder setEscape(Character ch) {
            if (!CSVFormat.isLineBreak(ch)) {
                this.escapeCharacter = ch;
                return this;
            }
            throw new IllegalArgumentException("The escape character cannot be a line break");
        }

        public Builder setHeader(Class<? extends Enum<?>> cls) {
            String[] strArr;
            if (cls != null) {
                Enum[] enumArr = (Enum[]) cls.getEnumConstants();
                strArr = new String[enumArr.length];
                for (int i = 0; i < enumArr.length; i++) {
                    strArr[i] = enumArr[i].name();
                }
            } else {
                strArr = null;
            }
            return setHeader(strArr);
        }

        public Builder setHeader(ResultSet resultSet) throws SQLException {
            return setHeader(resultSet != null ? resultSet.getMetaData() : null);
        }

        public Builder setHeader(ResultSetMetaData resultSetMetaData) throws SQLException {
            String[] strArr;
            if (resultSetMetaData != null) {
                int columnCount = resultSetMetaData.getColumnCount();
                strArr = new String[columnCount];
                int i = 0;
                while (i < columnCount) {
                    int i2 = i + 1;
                    strArr[i] = resultSetMetaData.getColumnLabel(i2);
                    i = i2;
                }
            } else {
                strArr = null;
            }
            return setHeader(strArr);
        }

        public Builder setHeader(String... strArr) {
            this.headers = (String[]) CSVFormat.clone(strArr);
            return this;
        }

        public Builder setHeaderComments(Object... objArr) {
            this.headerComments = (String[]) CSVFormat.clone(CSVFormat.toStringArray(objArr));
            return this;
        }

        public Builder setHeaderComments(String... strArr) {
            this.headerComments = (String[]) CSVFormat.clone(strArr);
            return this;
        }

        public Builder setIgnoreEmptyLines(boolean z) {
            this.ignoreEmptyLines = z;
            return this;
        }

        public Builder setIgnoreHeaderCase(boolean z) {
            this.ignoreHeaderCase = z;
            return this;
        }

        public Builder setIgnoreSurroundingSpaces(boolean z) {
            this.ignoreSurroundingSpaces = z;
            return this;
        }

        public Builder setNullString(String str) {
            this.nullString = str;
            this.quotedNullString = this.quoteCharacter + str + this.quoteCharacter;
            return this;
        }

        public Builder setQuote(char c) {
            setQuote(Character.valueOf(c));
            return this;
        }

        public Builder setQuote(Character ch) {
            if (!CSVFormat.isLineBreak(ch)) {
                this.quoteCharacter = ch;
                return this;
            }
            throw new IllegalArgumentException("The quoteChar cannot be a line break");
        }

        public Builder setQuoteMode(QuoteMode quoteMode2) {
            this.quoteMode = quoteMode2;
            return this;
        }

        public Builder setRecordSeparator(char c) {
            this.recordSeparator = String.valueOf(c);
            return this;
        }

        public Builder setRecordSeparator(String str) {
            this.recordSeparator = str;
            return this;
        }

        public Builder setSkipHeaderRecord(boolean z) {
            this.skipHeaderRecord = z;
            return this;
        }

        public Builder setTrailingDelimiter(boolean z) {
            this.trailingDelimiter = z;
            return this;
        }

        public Builder setTrim(boolean z) {
            this.trim = z;
            return this;
        }
    }

    public enum Predefined {
        Default(CSVFormat.DEFAULT),
        Excel(CSVFormat.EXCEL),
        InformixUnload(CSVFormat.INFORMIX_UNLOAD),
        InformixUnloadCsv(CSVFormat.INFORMIX_UNLOAD_CSV),
        MongoDBCsv(CSVFormat.MONGODB_CSV),
        MongoDBTsv(CSVFormat.MONGODB_TSV),
        MySQL(CSVFormat.MYSQL),
        Oracle(CSVFormat.ORACLE),
        PostgreSQLCsv(CSVFormat.POSTGRESQL_CSV),
        PostgreSQLText(CSVFormat.POSTGRESQL_TEXT),
        RFC4180(CSVFormat.RFC4180),
        TDF(CSVFormat.TDF);
        
        private final CSVFormat format;

        private Predefined(CSVFormat cSVFormat) {
            this.format = cSVFormat;
        }

        public CSVFormat getFormat() {
            return this.format;
        }
    }

    static {
        CSVFormat cSVFormat = new CSVFormat(",", Constants.DOUBLE_QUOTE_CHAR, (QuoteMode) null, (Character) null, (Character) null, false, true, "\r\n", (String) null, (Object[]) null, (String[]) null, false, false, false, false, false, false, true);
        DEFAULT = cSVFormat;
        EXCEL = cSVFormat.builder().setIgnoreEmptyLines(false).setAllowMissingColumnNames(true).build();
        INFORMIX_UNLOAD = cSVFormat.builder().setDelimiter('|').setEscape('\\').setQuote(Constants.DOUBLE_QUOTE_CHAR).setRecordSeparator(10).build();
        INFORMIX_UNLOAD_CSV = cSVFormat.builder().setDelimiter(",").setQuote(Constants.DOUBLE_QUOTE_CHAR).setRecordSeparator(10).build();
        MONGODB_CSV = cSVFormat.builder().setDelimiter(",").setEscape(Constants.DOUBLE_QUOTE_CHAR).setQuote(Constants.DOUBLE_QUOTE_CHAR).setQuoteMode(QuoteMode.MINIMAL).setSkipHeaderRecord(false).build();
        MONGODB_TSV = cSVFormat.builder().setDelimiter(9).setEscape(Constants.DOUBLE_QUOTE_CHAR).setQuote(Constants.DOUBLE_QUOTE_CHAR).setQuoteMode(QuoteMode.MINIMAL).setSkipHeaderRecord(false).build();
        MYSQL = cSVFormat.builder().setDelimiter(9).setEscape('\\').setIgnoreEmptyLines(false).setQuote((Character) null).setRecordSeparator(10).setNullString("\\N").setQuoteMode(QuoteMode.ALL_NON_NULL).build();
        ORACLE = cSVFormat.builder().setDelimiter(",").setEscape('\\').setIgnoreEmptyLines(false).setQuote(Constants.DOUBLE_QUOTE_CHAR).setNullString("\\N").setTrim(true).setRecordSeparator(System.lineSeparator()).setQuoteMode(QuoteMode.MINIMAL).build();
        POSTGRESQL_CSV = cSVFormat.builder().setDelimiter(",").setEscape(Constants.DOUBLE_QUOTE_CHAR).setIgnoreEmptyLines(false).setQuote(Constants.DOUBLE_QUOTE_CHAR).setRecordSeparator(10).setNullString(BuildConfig.FLAVOR).setQuoteMode(QuoteMode.ALL_NON_NULL).build();
        POSTGRESQL_TEXT = cSVFormat.builder().setDelimiter(9).setEscape('\\').setIgnoreEmptyLines(false).setQuote(Constants.DOUBLE_QUOTE_CHAR).setRecordSeparator(10).setNullString("\\N").setQuoteMode(QuoteMode.ALL_NON_NULL).build();
        RFC4180 = cSVFormat.builder().setIgnoreEmptyLines(false).build();
        TDF = cSVFormat.builder().setDelimiter(9).setIgnoreSurroundingSpaces(true).build();
    }

    @SafeVarargs
    static <T> T[] clone(T... tArr) {
        if (tArr == null) {
            return null;
        }
        return (Object[]) tArr.clone();
    }

    private static boolean contains(String str, char c) {
        return ((String) Objects.requireNonNull(str, "source")).indexOf(c) >= 0;
    }

    /* access modifiers changed from: private */
    public static boolean containsLineBreak(String str) {
        return contains(str, 13) || contains(str, 10);
    }

    /* access modifiers changed from: private */
    public static boolean isLineBreak(Character ch) {
        return ch != null && isLineBreak(ch.charValue());
    }

    public static CSVFormat newFormat(char c) {
        return new CSVFormat(String.valueOf(c), (Character) null, (QuoteMode) null, (Character) null, (Character) null, false, false, (String) null, (String) null, (Object[]) null, (String[]) null, false, false, false, false, false, false, true);
    }

    static String[] toStringArray(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        String[] strArr = new String[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            strArr[i] = Objects.toString(objArr[i], (String) null);
        }
        return strArr;
    }

    static CharSequence trim(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String) charSequence).trim();
        }
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) <= ' ') {
            i++;
        }
        int i2 = length;
        while (i < i2 && charSequence.charAt(i2 - 1) <= ' ') {
            i2--;
        }
        if (i > 0 || i2 < length) {
            return charSequence.subSequence(i, i2);
        }
        return charSequence;
    }

    public static CSVFormat valueOf(String str) {
        return Predefined.valueOf(str).getFormat();
    }

    private CSVFormat(Builder builder) {
        this.delimiter = builder.delimiter;
        this.quoteCharacter = builder.quoteCharacter;
        this.quoteMode = builder.quoteMode;
        this.commentMarker = builder.commentMarker;
        this.escapeCharacter = builder.escapeCharacter;
        this.ignoreSurroundingSpaces = builder.ignoreSurroundingSpaces;
        this.allowMissingColumnNames = builder.allowMissingColumnNames;
        this.ignoreEmptyLines = builder.ignoreEmptyLines;
        this.recordSeparator = builder.recordSeparator;
        this.nullString = builder.nullString;
        this.headerComments = builder.headerComments;
        this.header = builder.headers;
        this.skipHeaderRecord = builder.skipHeaderRecord;
        this.ignoreHeaderCase = builder.ignoreHeaderCase;
        this.trailingDelimiter = builder.trailingDelimiter;
        this.trim = builder.trim;
        this.autoFlush = builder.autoFlush;
        this.quotedNullString = builder.quotedNullString;
        this.allowDuplicateHeaderNames = builder.allowDuplicateHeaderNames;
        validate();
    }

    private CSVFormat(String str, Character ch, QuoteMode quoteMode2, Character ch2, Character ch3, boolean z, boolean z2, String str2, String str3, Object[] objArr, String[] strArr, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.delimiter = str;
        this.quoteCharacter = ch;
        this.quoteMode = quoteMode2;
        this.commentMarker = ch2;
        this.escapeCharacter = ch3;
        this.ignoreSurroundingSpaces = z;
        this.allowMissingColumnNames = z4;
        this.ignoreEmptyLines = z2;
        this.recordSeparator = str2;
        this.nullString = str3;
        this.headerComments = toStringArray(objArr);
        this.header = (String[]) clone(strArr);
        this.skipHeaderRecord = z3;
        this.ignoreHeaderCase = z5;
        this.trailingDelimiter = z7;
        this.trim = z6;
        this.autoFlush = z8;
        this.quotedNullString = ch + str3 + ch;
        this.allowDuplicateHeaderNames = z9;
        validate();
    }

    private void append(char c, Appendable appendable) throws IOException {
        appendable.append(c);
    }

    private void append(CharSequence charSequence, Appendable appendable) throws IOException {
        appendable.append(charSequence);
    }

    public Builder builder() {
        return Builder.create(this);
    }

    /* access modifiers changed from: package-private */
    public CSVFormat copy() {
        return builder().build();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CSVFormat cSVFormat = (CSVFormat) obj;
            return this.allowDuplicateHeaderNames == cSVFormat.allowDuplicateHeaderNames && this.allowMissingColumnNames == cSVFormat.allowMissingColumnNames && this.autoFlush == cSVFormat.autoFlush && Objects.equals(this.commentMarker, cSVFormat.commentMarker) && Objects.equals(this.delimiter, cSVFormat.delimiter) && Objects.equals(this.escapeCharacter, cSVFormat.escapeCharacter) && Arrays.equals(this.header, cSVFormat.header) && Arrays.equals(this.headerComments, cSVFormat.headerComments) && this.ignoreEmptyLines == cSVFormat.ignoreEmptyLines && this.ignoreHeaderCase == cSVFormat.ignoreHeaderCase && this.ignoreSurroundingSpaces == cSVFormat.ignoreSurroundingSpaces && Objects.equals(this.nullString, cSVFormat.nullString) && Objects.equals(this.quoteCharacter, cSVFormat.quoteCharacter) && this.quoteMode == cSVFormat.quoteMode && Objects.equals(this.quotedNullString, cSVFormat.quotedNullString) && Objects.equals(this.recordSeparator, cSVFormat.recordSeparator) && this.skipHeaderRecord == cSVFormat.skipHeaderRecord && this.trailingDelimiter == cSVFormat.trailingDelimiter && this.trim == cSVFormat.trim;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0030, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0039, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String format(java.lang.Object... r4) {
        /*
            r3 = this;
            java.io.StringWriter r0 = new java.io.StringWriter
            r0.<init>()
            org.apache.commons.csv.CSVPrinter r1 = new org.apache.commons.csv.CSVPrinter     // Catch:{ IOException -> 0x003a }
            r1.<init>(r0, r3)     // Catch:{ IOException -> 0x003a }
            r1.printRecord((java.lang.Object[]) r4)     // Catch:{ all -> 0x002e }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x002e }
            java.lang.String r0 = r3.recordSeparator     // Catch:{ all -> 0x002e }
            if (r0 == 0) goto L_0x0021
            int r0 = r4.length()     // Catch:{ all -> 0x002e }
            java.lang.String r2 = r3.recordSeparator     // Catch:{ all -> 0x002e }
            int r2 = r2.length()     // Catch:{ all -> 0x002e }
            int r0 = r0 - r2
            goto L_0x0025
        L_0x0021:
            int r0 = r4.length()     // Catch:{ all -> 0x002e }
        L_0x0025:
            r2 = 0
            java.lang.String r4 = r4.substring(r2, r0)     // Catch:{ all -> 0x002e }
            r1.close()     // Catch:{ IOException -> 0x003a }
            return r4
        L_0x002e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r0 = move-exception
            r1.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r1 = move-exception
            r4.addSuppressed(r1)     // Catch:{ IOException -> 0x003a }
        L_0x0039:
            throw r0     // Catch:{ IOException -> 0x003a }
        L_0x003a:
            r4 = move-exception
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.csv.CSVFormat.format(java.lang.Object[]):java.lang.String");
    }

    public boolean getAllowDuplicateHeaderNames() {
        return this.allowDuplicateHeaderNames;
    }

    public boolean getAllowMissingColumnNames() {
        return this.allowMissingColumnNames;
    }

    public boolean getAutoFlush() {
        return this.autoFlush;
    }

    public Character getCommentMarker() {
        return this.commentMarker;
    }

    @Deprecated
    public char getDelimiter() {
        return this.delimiter.charAt(0);
    }

    public String getDelimiterString() {
        return this.delimiter;
    }

    public Character getEscapeCharacter() {
        return this.escapeCharacter;
    }

    public String[] getHeader() {
        String[] strArr = this.header;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        return null;
    }

    public String[] getHeaderComments() {
        String[] strArr = this.headerComments;
        if (strArr != null) {
            return (String[]) strArr.clone();
        }
        return null;
    }

    public boolean getIgnoreEmptyLines() {
        return this.ignoreEmptyLines;
    }

    public boolean getIgnoreHeaderCase() {
        return this.ignoreHeaderCase;
    }

    public boolean getIgnoreSurroundingSpaces() {
        return this.ignoreSurroundingSpaces;
    }

    public String getNullString() {
        return this.nullString;
    }

    public Character getQuoteCharacter() {
        return this.quoteCharacter;
    }

    public QuoteMode getQuoteMode() {
        return this.quoteMode;
    }

    public String getRecordSeparator() {
        return this.recordSeparator;
    }

    public boolean getSkipHeaderRecord() {
        return this.skipHeaderRecord;
    }

    public boolean getTrailingDelimiter() {
        return this.trailingDelimiter;
    }

    public boolean getTrim() {
        return this.trim;
    }

    public int hashCode() {
        Boolean valueOf = Boolean.valueOf(this.allowDuplicateHeaderNames);
        Boolean valueOf2 = Boolean.valueOf(this.allowMissingColumnNames);
        Boolean valueOf3 = Boolean.valueOf(this.autoFlush);
        Character ch = this.commentMarker;
        String str = this.delimiter;
        Character ch2 = this.escapeCharacter;
        Boolean valueOf4 = Boolean.valueOf(this.ignoreEmptyLines);
        Boolean valueOf5 = Boolean.valueOf(this.ignoreHeaderCase);
        Boolean valueOf6 = Boolean.valueOf(this.ignoreSurroundingSpaces);
        String str2 = this.nullString;
        Character ch3 = this.quoteCharacter;
        QuoteMode quoteMode2 = this.quoteMode;
        String str3 = this.quotedNullString;
        String str4 = this.recordSeparator;
        return ((((Arrays.hashCode(this.header) + 31) * 31) + Arrays.hashCode(this.headerComments)) * 31) + Objects.hash(new Object[]{valueOf, valueOf2, valueOf3, ch, str, ch2, valueOf4, valueOf5, valueOf6, str2, ch3, quoteMode2, str3, str4, Boolean.valueOf(this.skipHeaderRecord), Boolean.valueOf(this.trailingDelimiter), Boolean.valueOf(this.trim)});
    }

    public boolean isCommentMarkerSet() {
        return this.commentMarker != null;
    }

    private boolean isDelimiter(char c, CharSequence charSequence, int i, char[] cArr, int i2) {
        if (c != cArr[0] || i + i2 > charSequence.length()) {
            return false;
        }
        for (int i3 = 1; i3 < i2; i3++) {
            if (charSequence.charAt(i + i3) != cArr[i3]) {
                return false;
            }
        }
        return true;
    }

    public boolean isEscapeCharacterSet() {
        return this.escapeCharacter != null;
    }

    public boolean isNullStringSet() {
        return this.nullString != null;
    }

    public boolean isQuoteCharacterSet() {
        return this.quoteCharacter != null;
    }

    public CSVParser parse(Reader reader) throws IOException {
        return new CSVParser(reader, this);
    }

    public CSVPrinter print(Appendable appendable) throws IOException {
        return new CSVPrinter(appendable, this);
    }

    public CSVPrinter print(File file, Charset charset) throws IOException {
        return new CSVPrinter(new OutputStreamWriter(new FileOutputStream(file), charset), this);
    }

    public void print(Object obj, Appendable appendable, boolean z) throws IOException {
        CharSequence charSequence;
        if (obj == null) {
            if (this.nullString == null) {
                charSequence = BuildConfig.FLAVOR;
            } else if (QuoteMode.ALL == this.quoteMode) {
                charSequence = this.quotedNullString;
            } else {
                charSequence = this.nullString;
            }
        } else if (obj instanceof CharSequence) {
            charSequence = (CharSequence) obj;
        } else if (obj instanceof Reader) {
            print((Reader) obj, appendable, z);
            return;
        } else {
            charSequence = obj.toString();
        }
        if (getTrim()) {
            charSequence = trim(charSequence);
        }
        print(obj, charSequence, appendable, z);
    }

    private void print(Object obj, CharSequence charSequence, Appendable appendable, boolean z) throws IOException {
        int length = charSequence.length();
        if (!z) {
            appendable.append(getDelimiterString());
        }
        if (obj == null) {
            appendable.append(charSequence);
        } else if (isQuoteCharacterSet()) {
            printWithQuotes(obj, charSequence, appendable, z);
        } else if (isEscapeCharacterSet()) {
            printWithEscapes(charSequence, appendable);
        } else {
            appendable.append(charSequence, 0, length);
        }
    }

    public CSVPrinter print(Path path, Charset charset) throws IOException {
        return print(Files.newBufferedWriter(path, charset, new OpenOption[0]));
    }

    private void print(Reader reader, Appendable appendable, boolean z) throws IOException {
        if (!z) {
            append((CharSequence) getDelimiterString(), appendable);
        }
        if (isQuoteCharacterSet()) {
            printWithQuotes(reader, appendable);
        } else if (isEscapeCharacterSet()) {
            printWithEscapes(reader, appendable);
        } else if (appendable instanceof Writer) {
            IOUtils.copyLarge(reader, (Writer) appendable);
        } else {
            IOUtils.copy(reader, appendable);
        }
    }

    public CSVPrinter printer() throws IOException {
        return new CSVPrinter(System.out, this);
    }

    public void println(Appendable appendable) throws IOException {
        if (getTrailingDelimiter()) {
            append((CharSequence) getDelimiterString(), appendable);
        }
        String str = this.recordSeparator;
        if (str != null) {
            append((CharSequence) str, appendable);
        }
    }

    public void printRecord(Appendable appendable, Object... objArr) throws IOException {
        int i = 0;
        while (i < objArr.length) {
            print(objArr[i], appendable, i == 0);
            i++;
        }
        println(appendable);
    }

    private void printWithEscapes(CharSequence charSequence, Appendable appendable) throws IOException {
        int length = charSequence.length();
        char[] charArray = getDelimiterString().toCharArray();
        int length2 = charArray.length;
        char charValue = getEscapeCharacter().charValue();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            CharSequence charSequence2 = charSequence;
            boolean isDelimiter = isDelimiter(charAt, charSequence2, i, charArray, length2);
            if (charAt == 13 || charAt == 10 || charAt == charValue || isDelimiter) {
                if (i > i2) {
                    appendable.append(charSequence2, i2, i);
                }
                if (charAt == 10) {
                    charAt = 'n';
                } else if (charAt == 13) {
                    charAt = 'r';
                }
                appendable.append(charValue);
                appendable.append(charAt);
                if (isDelimiter) {
                    for (int i3 = 1; i3 < length2; i3++) {
                        i++;
                        char charAt2 = charSequence2.charAt(i);
                        appendable.append(charValue);
                        appendable.append(charAt2);
                    }
                }
                i2 = i + 1;
            }
            i++;
            charSequence = charSequence2;
        }
        CharSequence charSequence3 = charSequence;
        if (i > i2) {
            appendable.append(charSequence3, i2, i);
        }
    }

    private void printWithEscapes(Reader reader, Appendable appendable) throws IOException {
        Appendable appendable2 = appendable;
        ExtendedBufferedReader extendedBufferedReader = new ExtendedBufferedReader(reader);
        char[] charArray = getDelimiterString().toCharArray();
        int length = charArray.length;
        char charValue = getEscapeCharacter().charValue();
        StringBuilder sb = new StringBuilder(4096);
        int i = 0;
        int i2 = 0;
        while (true) {
            int read = extendedBufferedReader.read();
            if (-1 == read) {
                break;
            }
            char c = (char) read;
            sb.append(c);
            boolean isDelimiter = isDelimiter(c, sb.toString() + new String(extendedBufferedReader.lookAhead(length - 1)), i, charArray, length);
            if (read == 13 || read == 10 || read == charValue || isDelimiter) {
                if (i > i2) {
                    append((CharSequence) sb.substring(i2, i), appendable2);
                    sb.setLength(0);
                    i = -1;
                }
                if (read == 10) {
                    read = 110;
                } else if (read == 13) {
                    read = 114;
                }
                append(charValue, appendable2);
                append((char) read, appendable2);
                if (isDelimiter) {
                    for (int i3 = 1; i3 < length; i3++) {
                        int read2 = extendedBufferedReader.read();
                        append(charValue, appendable2);
                        append((char) read2, appendable2);
                    }
                }
                i2 = i + 1;
            }
            i++;
        }
        if (i > i2) {
            append((CharSequence) sb.substring(i2, i), appendable2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:40:0x008d, code lost:
        if (r4.charAt(r5) <= ' ') goto L_0x0091;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void printWithQuotes(java.lang.Object r12, java.lang.CharSequence r13, java.lang.Appendable r14, boolean r15) throws java.io.IOException {
        /*
            r11 = this;
            int r0 = r13.length()
            java.lang.String r1 = r11.getDelimiterString()
            char[] r6 = r1.toCharArray()
            int r7 = r6.length
            java.lang.Character r1 = r11.getQuoteCharacter()
            char r1 = r1.charValue()
            boolean r2 = r11.isEscapeCharacterSet()
            if (r2 == 0) goto L_0x0025
            java.lang.Character r2 = r11.getEscapeCharacter()
            char r2 = r2.charValue()
            r8 = r2
            goto L_0x0026
        L_0x0025:
            r8 = r1
        L_0x0026:
            org.apache.commons.csv.QuoteMode r2 = r11.getQuoteMode()
            if (r2 != 0) goto L_0x002e
            org.apache.commons.csv.QuoteMode r2 = org.apache.commons.csv.QuoteMode.MINIMAL
        L_0x002e:
            int[] r3 = org.apache.commons.csv.CSVFormat.AnonymousClass1.$SwitchMap$org$apache$commons$csv$QuoteMode
            int r4 = r2.ordinal()
            r3 = r3[r4]
            r9 = 1
            r10 = 0
            if (r3 == r9) goto L_0x00b8
            r4 = 2
            if (r3 == r4) goto L_0x00b8
            r4 = 3
            if (r3 == r4) goto L_0x00b2
            r12 = 4
            if (r3 == r12) goto L_0x00ac
            r12 = 5
            if (r3 != r12) goto L_0x0097
            if (r0 > 0) goto L_0x0050
            if (r15 == 0) goto L_0x004b
            goto L_0x0058
        L_0x004b:
            r4 = r13
            r5 = r10
            r9 = r5
        L_0x004e:
            r13 = r11
            goto L_0x0091
        L_0x0050:
            char r12 = r13.charAt(r10)
            r15 = 35
            if (r12 > r15) goto L_0x005b
        L_0x0058:
            r4 = r13
            r5 = r10
            goto L_0x004e
        L_0x005b:
            r5 = r10
        L_0x005c:
            if (r5 >= r0) goto L_0x0080
            char r3 = r13.charAt(r5)
            r12 = 10
            if (r3 == r12) goto L_0x007c
            r12 = 13
            if (r3 == r12) goto L_0x007c
            if (r3 == r1) goto L_0x007c
            if (r3 == r8) goto L_0x007c
            r2 = r11
            r4 = r13
            boolean r12 = r2.isDelimiter(r3, r4, r5, r6, r7)
            r13 = r2
            if (r12 == 0) goto L_0x0078
            goto L_0x007e
        L_0x0078:
            int r5 = r5 + 1
            r13 = r4
            goto L_0x005c
        L_0x007c:
            r4 = r13
            r13 = r11
        L_0x007e:
            r12 = r9
            goto L_0x0083
        L_0x0080:
            r4 = r13
            r13 = r11
            r12 = r10
        L_0x0083:
            if (r12 != 0) goto L_0x0090
            int r5 = r0 + -1
            char r15 = r4.charAt(r5)
            r2 = 32
            if (r15 > r2) goto L_0x0090
            goto L_0x0091
        L_0x0090:
            r9 = r12
        L_0x0091:
            if (r9 != 0) goto L_0x00bb
            r14.append(r4, r10, r0)
            return
        L_0x0097:
            r13 = r11
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "Unexpected Quote value: "
            r14.<init>(r15)
            r14.append(r2)
            java.lang.String r14 = r14.toString()
            r12.<init>(r14)
            throw r12
        L_0x00ac:
            r4 = r13
            r13 = r11
            r11.printWithEscapes((java.lang.CharSequence) r4, (java.lang.Appendable) r14)
            return
        L_0x00b2:
            r4 = r13
            r13 = r11
            boolean r12 = r12 instanceof java.lang.Number
            r9 = r9 ^ r12
            goto L_0x00ba
        L_0x00b8:
            r4 = r13
            r13 = r11
        L_0x00ba:
            r5 = r10
        L_0x00bb:
            if (r9 != 0) goto L_0x00c1
            r14.append(r4, r10, r0)
            return
        L_0x00c1:
            r14.append(r1)
        L_0x00c4:
            if (r5 >= r0) goto L_0x00d8
            char r12 = r4.charAt(r5)
            if (r12 == r1) goto L_0x00ce
            if (r12 != r8) goto L_0x00d5
        L_0x00ce:
            r14.append(r4, r10, r5)
            r14.append(r8)
            r10 = r5
        L_0x00d5:
            int r5 = r5 + 1
            goto L_0x00c4
        L_0x00d8:
            r14.append(r4, r10, r5)
            r14.append(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.csv.CSVFormat.printWithQuotes(java.lang.Object, java.lang.CharSequence, java.lang.Appendable, boolean):void");
    }

    /* renamed from: org.apache.commons.csv.CSVFormat$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$csv$QuoteMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.csv.QuoteMode[] r0 = org.apache.commons.csv.QuoteMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$csv$QuoteMode = r0
                org.apache.commons.csv.QuoteMode r1 = org.apache.commons.csv.QuoteMode.ALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$csv$QuoteMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.csv.QuoteMode r1 = org.apache.commons.csv.QuoteMode.ALL_NON_NULL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$csv$QuoteMode     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.csv.QuoteMode r1 = org.apache.commons.csv.QuoteMode.NON_NUMERIC     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$csv$QuoteMode     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.csv.QuoteMode r1 = org.apache.commons.csv.QuoteMode.NONE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$csv$QuoteMode     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.csv.QuoteMode r1 = org.apache.commons.csv.QuoteMode.MINIMAL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.csv.CSVFormat.AnonymousClass1.<clinit>():void");
        }
    }

    private void printWithQuotes(Reader reader, Appendable appendable) throws IOException {
        if (getQuoteMode() == QuoteMode.NONE) {
            printWithEscapes(reader, appendable);
            return;
        }
        char charValue = getQuoteCharacter().charValue();
        StringBuilder sb = new StringBuilder(4096);
        append(charValue, appendable);
        int i = 0;
        while (true) {
            int read = reader.read();
            if (-1 == read) {
                break;
            }
            char c = (char) read;
            sb.append(c);
            if (read == charValue) {
                if (i > 0) {
                    append((CharSequence) sb.substring(0, i), appendable);
                    append(charValue, appendable);
                    sb.setLength(0);
                    i = -1;
                }
                append(c, appendable);
            }
            i++;
        }
        if (i > 0) {
            append((CharSequence) sb.substring(0, i), appendable);
        }
        append(charValue, appendable);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Delimiter=<");
        sb.append(this.delimiter);
        sb.append(Typography.greater);
        if (isEscapeCharacterSet()) {
            sb.append(" Escape=<");
            sb.append(this.escapeCharacter);
            sb.append(Typography.greater);
        }
        if (isQuoteCharacterSet()) {
            sb.append(" QuoteChar=<");
            sb.append(this.quoteCharacter);
            sb.append(Typography.greater);
        }
        if (this.quoteMode != null) {
            sb.append(" QuoteMode=<");
            sb.append(this.quoteMode);
            sb.append(Typography.greater);
        }
        if (isCommentMarkerSet()) {
            sb.append(" CommentStart=<");
            sb.append(this.commentMarker);
            sb.append(Typography.greater);
        }
        if (isNullStringSet()) {
            sb.append(" NullString=<");
            sb.append(this.nullString);
            sb.append(Typography.greater);
        }
        if (this.recordSeparator != null) {
            sb.append(" RecordSeparator=<");
            sb.append(this.recordSeparator);
            sb.append(Typography.greater);
        }
        if (getIgnoreEmptyLines()) {
            sb.append(" EmptyLines:ignored");
        }
        if (getIgnoreSurroundingSpaces()) {
            sb.append(" SurroundingSpaces:ignored");
        }
        if (getIgnoreHeaderCase()) {
            sb.append(" IgnoreHeaderCase:ignored");
        }
        sb.append(" SkipHeaderRecord:");
        sb.append(this.skipHeaderRecord);
        if (this.headerComments != null) {
            sb.append(" HeaderComments:");
            sb.append(Arrays.toString(this.headerComments));
        }
        if (this.header != null) {
            sb.append(" Header:");
            sb.append(Arrays.toString(this.header));
        }
        return sb.toString();
    }

    private void validate() throws IllegalArgumentException {
        if (!containsLineBreak(this.delimiter)) {
            Character ch = this.quoteCharacter;
            if (ch == null || !contains(this.delimiter, ch.charValue())) {
                Character ch2 = this.escapeCharacter;
                if (ch2 == null || !contains(this.delimiter, ch2.charValue())) {
                    Character ch3 = this.commentMarker;
                    if (ch3 == null || !contains(this.delimiter, ch3.charValue())) {
                        Character ch4 = this.quoteCharacter;
                        if (ch4 == null || !ch4.equals(this.commentMarker)) {
                            Character ch5 = this.escapeCharacter;
                            if (ch5 != null && ch5.equals(this.commentMarker)) {
                                throw new IllegalArgumentException("The comment start and the escape character cannot be the same ('" + this.commentMarker + "')");
                            } else if (this.escapeCharacter == null && this.quoteMode == QuoteMode.NONE) {
                                throw new IllegalArgumentException("No quotes mode set but no escape character is set");
                            } else if (this.header != null && !this.allowDuplicateHeaderNames) {
                                HashSet hashSet = new HashSet();
                                String[] strArr = this.header;
                                int length = strArr.length;
                                int i = 0;
                                while (i < length) {
                                    String str = strArr[i];
                                    if (hashSet.add(str)) {
                                        i++;
                                    } else {
                                        throw new IllegalArgumentException("The header contains a duplicate entry: '" + str + "' in " + Arrays.toString(this.header));
                                    }
                                }
                            }
                        } else {
                            throw new IllegalArgumentException("The comment start character and the quoteChar cannot be the same ('" + this.commentMarker + "')");
                        }
                    } else {
                        throw new IllegalArgumentException("The comment start character and the delimiter cannot be the same ('" + this.commentMarker + "')");
                    }
                } else {
                    throw new IllegalArgumentException("The escape character and the delimiter cannot be the same ('" + this.escapeCharacter + "')");
                }
            } else {
                throw new IllegalArgumentException("The quoteChar character and the delimiter cannot be the same ('" + this.quoteCharacter + "')");
            }
        } else {
            throw new IllegalArgumentException("The delimiter cannot be a line break");
        }
    }

    @Deprecated
    public CSVFormat withAllowDuplicateHeaderNames() {
        return builder().setAllowDuplicateHeaderNames(true).build();
    }

    @Deprecated
    public CSVFormat withAllowDuplicateHeaderNames(boolean z) {
        return builder().setAllowDuplicateHeaderNames(z).build();
    }

    @Deprecated
    public CSVFormat withAllowMissingColumnNames() {
        return builder().setAllowMissingColumnNames(true).build();
    }

    @Deprecated
    public CSVFormat withAllowMissingColumnNames(boolean z) {
        return builder().setAllowMissingColumnNames(z).build();
    }

    @Deprecated
    public CSVFormat withAutoFlush(boolean z) {
        return builder().setAutoFlush(z).build();
    }

    @Deprecated
    public CSVFormat withCommentMarker(char c) {
        return builder().setCommentMarker(c).build();
    }

    @Deprecated
    public CSVFormat withCommentMarker(Character ch) {
        return builder().setCommentMarker(ch).build();
    }

    @Deprecated
    public CSVFormat withDelimiter(char c) {
        return builder().setDelimiter(c).build();
    }

    @Deprecated
    public CSVFormat withEscape(char c) {
        return builder().setEscape(c).build();
    }

    @Deprecated
    public CSVFormat withEscape(Character ch) {
        return builder().setEscape(ch).build();
    }

    @Deprecated
    public CSVFormat withFirstRecordAsHeader() {
        return builder().setHeader(new String[0]).setSkipHeaderRecord(true).build();
    }

    @Deprecated
    public CSVFormat withHeader(Class<? extends Enum<?>> cls) {
        return builder().setHeader(cls).build();
    }

    @Deprecated
    public CSVFormat withHeader(ResultSet resultSet) throws SQLException {
        return builder().setHeader(resultSet).build();
    }

    @Deprecated
    public CSVFormat withHeader(ResultSetMetaData resultSetMetaData) throws SQLException {
        return builder().setHeader(resultSetMetaData).build();
    }

    @Deprecated
    public CSVFormat withHeader(String... strArr) {
        return builder().setHeader(strArr).build();
    }

    @Deprecated
    public CSVFormat withHeaderComments(Object... objArr) {
        return builder().setHeaderComments(objArr).build();
    }

    @Deprecated
    public CSVFormat withIgnoreEmptyLines() {
        return builder().setIgnoreEmptyLines(true).build();
    }

    @Deprecated
    public CSVFormat withIgnoreEmptyLines(boolean z) {
        return builder().setIgnoreEmptyLines(z).build();
    }

    @Deprecated
    public CSVFormat withIgnoreHeaderCase() {
        return builder().setIgnoreHeaderCase(true).build();
    }

    @Deprecated
    public CSVFormat withIgnoreHeaderCase(boolean z) {
        return builder().setIgnoreHeaderCase(z).build();
    }

    @Deprecated
    public CSVFormat withIgnoreSurroundingSpaces() {
        return builder().setIgnoreSurroundingSpaces(true).build();
    }

    @Deprecated
    public CSVFormat withIgnoreSurroundingSpaces(boolean z) {
        return builder().setIgnoreSurroundingSpaces(z).build();
    }

    @Deprecated
    public CSVFormat withNullString(String str) {
        return builder().setNullString(str).build();
    }

    @Deprecated
    public CSVFormat withQuote(char c) {
        return builder().setQuote(c).build();
    }

    @Deprecated
    public CSVFormat withQuote(Character ch) {
        return builder().setQuote(ch).build();
    }

    @Deprecated
    public CSVFormat withQuoteMode(QuoteMode quoteMode2) {
        return builder().setQuoteMode(quoteMode2).build();
    }

    @Deprecated
    public CSVFormat withRecordSeparator(char c) {
        return builder().setRecordSeparator(c).build();
    }

    @Deprecated
    public CSVFormat withRecordSeparator(String str) {
        return builder().setRecordSeparator(str).build();
    }

    @Deprecated
    public CSVFormat withSkipHeaderRecord() {
        return builder().setSkipHeaderRecord(true).build();
    }

    @Deprecated
    public CSVFormat withSkipHeaderRecord(boolean z) {
        return builder().setSkipHeaderRecord(z).build();
    }

    @Deprecated
    public CSVFormat withSystemRecordSeparator() {
        return builder().setRecordSeparator(System.lineSeparator()).build();
    }

    @Deprecated
    public CSVFormat withTrailingDelimiter() {
        return builder().setTrailingDelimiter(true).build();
    }

    @Deprecated
    public CSVFormat withTrailingDelimiter(boolean z) {
        return builder().setTrailingDelimiter(z).build();
    }

    @Deprecated
    public CSVFormat withTrim() {
        return builder().setTrim(true).build();
    }

    @Deprecated
    public CSVFormat withTrim(boolean z) {
        return builder().setTrim(z).build();
    }
}
