package org.apache.commons.csv;

import java.io.Closeable;
import java.io.IOException;
import org.apache.commons.csv.Token;

final class Lexer implements Closeable {
    private static final String CR_STRING = Character.toString(13);
    private static final char DISABLED = '￾';
    private static final String LF_STRING = Character.toString(10);
    private final char commentStart;
    private final char[] delimiter;
    private final char[] delimiterBuf;
    private final char escape;
    private final char[] escapeDelimiterBuf;
    private String firstEol;
    private final boolean ignoreEmptyLines;
    private final boolean ignoreSurroundingSpaces;
    private final char quoteChar;
    private final ExtendedBufferedReader reader;

    /* access modifiers changed from: package-private */
    public boolean isEndOfFile(int i) {
        return i == -1;
    }

    /* access modifiers changed from: package-private */
    public boolean isStartOfLine(int i) {
        return i == 10 || i == 13 || i == -2;
    }

    Lexer(CSVFormat cSVFormat, ExtendedBufferedReader extendedBufferedReader) {
        this.reader = extendedBufferedReader;
        char[] charArray = cSVFormat.getDelimiterString().toCharArray();
        this.delimiter = charArray;
        this.escape = mapNullToDisabled(cSVFormat.getEscapeCharacter());
        this.quoteChar = mapNullToDisabled(cSVFormat.getQuoteCharacter());
        this.commentStart = mapNullToDisabled(cSVFormat.getCommentMarker());
        this.ignoreSurroundingSpaces = cSVFormat.getIgnoreSurroundingSpaces();
        this.ignoreEmptyLines = cSVFormat.getIgnoreEmptyLines();
        this.delimiterBuf = new char[(charArray.length - 1)];
        this.escapeDelimiterBuf = new char[((charArray.length * 2) - 1)];
    }

    public void close() throws IOException {
        this.reader.close();
    }

    /* access modifiers changed from: package-private */
    public long getCharacterPosition() {
        return this.reader.getPosition();
    }

    /* access modifiers changed from: package-private */
    public long getCurrentLineNumber() {
        return this.reader.getCurrentLineNumber();
    }

    /* access modifiers changed from: package-private */
    public String getFirstEol() {
        return this.firstEol;
    }

    /* access modifiers changed from: package-private */
    public boolean isClosed() {
        return this.reader.isClosed();
    }

    /* access modifiers changed from: package-private */
    public boolean isCommentStart(int i) {
        return i == this.commentStart;
    }

    /* access modifiers changed from: package-private */
    public boolean isDelimiter(int i) throws IOException {
        char c;
        char[] cArr = this.delimiter;
        if (i != cArr[0]) {
            return false;
        }
        if (cArr.length == 1) {
            return true;
        }
        this.reader.lookAhead(this.delimiterBuf);
        int i2 = 0;
        do {
            char[] cArr2 = this.delimiterBuf;
            if (i2 < cArr2.length) {
                c = cArr2[i2];
                i2++;
            } else if (this.reader.read(cArr2, 0, cArr2.length) != -1) {
                return true;
            } else {
                return false;
            }
        } while (c == this.delimiter[i2]);
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean isEscape(int i) {
        return i == this.escape;
    }

    /* access modifiers changed from: package-private */
    public boolean isEscapeDelimiter() throws IOException {
        this.reader.lookAhead(this.escapeDelimiterBuf);
        if (this.escapeDelimiterBuf[0] != this.delimiter[0]) {
            return false;
        }
        int i = 1;
        while (true) {
            char[] cArr = this.delimiter;
            if (i < cArr.length) {
                char[] cArr2 = this.escapeDelimiterBuf;
                int i2 = i * 2;
                if (cArr2[i2] != cArr[i] || cArr2[i2 - 1] != this.escape) {
                    return false;
                }
                i++;
            } else {
                ExtendedBufferedReader extendedBufferedReader = this.reader;
                char[] cArr3 = this.escapeDelimiterBuf;
                return extendedBufferedReader.read(cArr3, 0, cArr3.length) != -1;
            }
        }
        return false;
    }

    private boolean isMetaChar(int i) {
        return i == this.escape || i == this.quoteChar || i == this.commentStart;
    }

    /* access modifiers changed from: package-private */
    public boolean isQuoteChar(int i) {
        return i == this.quoteChar;
    }

    private char mapNullToDisabled(Character ch) {
        return ch == null ? DISABLED : ch.charValue();
    }

    /* access modifiers changed from: package-private */
    public Token nextToken(Token token) throws IOException {
        int lastChar = this.reader.getLastChar();
        int read = this.reader.read();
        boolean readEndOfLine = readEndOfLine(read);
        if (this.ignoreEmptyLines) {
            while (readEndOfLine && isStartOfLine(lastChar)) {
                int read2 = this.reader.read();
                readEndOfLine = readEndOfLine(read2);
                if (isEndOfFile(read2)) {
                    token.type = Token.Type.EOF;
                    return token;
                }
                int i = read;
                read = read2;
                lastChar = i;
            }
        }
        if (isEndOfFile(lastChar) || (!isDelimiter(lastChar) && isEndOfFile(read))) {
            token.type = Token.Type.EOF;
            return token;
        } else if (!isStartOfLine(lastChar) || !isCommentStart(read)) {
            while (token.type == Token.Type.INVALID) {
                if (this.ignoreSurroundingSpaces) {
                    while (Character.isWhitespace((char) read) && !isDelimiter(read) && !readEndOfLine) {
                        read = this.reader.read();
                        readEndOfLine = readEndOfLine(read);
                    }
                }
                if (isDelimiter(read)) {
                    token.type = Token.Type.TOKEN;
                } else if (readEndOfLine) {
                    token.type = Token.Type.EORECORD;
                } else if (isQuoteChar(read)) {
                    parseEncapsulatedToken(token);
                } else if (isEndOfFile(read)) {
                    token.type = Token.Type.EOF;
                    token.isReady = true;
                } else {
                    parseSimpleToken(token, read);
                }
            }
            return token;
        } else {
            String readLine = this.reader.readLine();
            if (readLine == null) {
                token.type = Token.Type.EOF;
                return token;
            }
            token.content.append(readLine.trim());
            token.type = Token.Type.COMMENT;
            return token;
        }
    }

    private Token parseEncapsulatedToken(Token token) throws IOException {
        int read;
        token.isQuoted = true;
        long currentLineNumber = getCurrentLineNumber();
        while (true) {
            int read2 = this.reader.read();
            if (isEscape(read2)) {
                if (isEscapeDelimiter()) {
                    token.content.append(this.delimiter);
                } else {
                    int readEscape = readEscape();
                    if (readEscape == -1) {
                        StringBuilder sb = token.content;
                        sb.append((char) read2);
                        sb.append((char) this.reader.getLastChar());
                    } else {
                        token.content.append((char) readEscape);
                    }
                }
            } else if (isQuoteChar(read2)) {
                if (isQuoteChar(this.reader.lookAhead())) {
                    token.content.append((char) this.reader.read());
                } else {
                    do {
                        read = this.reader.read();
                        if (isDelimiter(read)) {
                            token.type = Token.Type.TOKEN;
                            return token;
                        } else if (isEndOfFile(read)) {
                            token.type = Token.Type.EOF;
                            token.isReady = true;
                            return token;
                        } else if (readEndOfLine(read)) {
                            token.type = Token.Type.EORECORD;
                            return token;
                        }
                    } while (Character.isWhitespace((char) read));
                    throw new IOException("(line " + getCurrentLineNumber() + ") invalid char between encapsulated token and delimiter");
                }
            } else if (!isEndOfFile(read2)) {
                token.content.append((char) read2);
            } else {
                throw new IOException("(startline " + currentLineNumber + ") EOF reached before encapsulated token finished");
            }
        }
    }

    private Token parseSimpleToken(Token token, int i) throws IOException {
        while (true) {
            if (readEndOfLine(i)) {
                token.type = Token.Type.EORECORD;
                break;
            } else if (isEndOfFile(i)) {
                token.type = Token.Type.EOF;
                token.isReady = true;
                break;
            } else if (isDelimiter(i)) {
                token.type = Token.Type.TOKEN;
                break;
            } else if (isEscape(i)) {
                if (isEscapeDelimiter()) {
                    token.content.append(this.delimiter);
                } else {
                    int readEscape = readEscape();
                    if (readEscape == -1) {
                        StringBuilder sb = token.content;
                        sb.append((char) i);
                        sb.append((char) this.reader.getLastChar());
                    } else {
                        token.content.append((char) readEscape);
                    }
                }
                i = this.reader.read();
            } else {
                token.content.append((char) i);
                i = this.reader.read();
            }
        }
        if (this.ignoreSurroundingSpaces) {
            trimTrailingSpaces(token.content);
        }
        return token;
    }

    /* access modifiers changed from: package-private */
    public boolean readEndOfLine(int i) throws IOException {
        if (i == 13 && this.reader.lookAhead() == 10) {
            i = this.reader.read();
            if (this.firstEol == null) {
                this.firstEol = "\r\n";
            }
        }
        if (this.firstEol == null) {
            if (i == 10) {
                this.firstEol = LF_STRING;
            } else if (i == 13) {
                this.firstEol = CR_STRING;
            }
        }
        return i == 10 || i == 13;
    }

    /* access modifiers changed from: package-private */
    public int readEscape() throws IOException {
        int read = this.reader.read();
        if (read == -1) {
            throw new IOException("EOF whilst processing escape sequence");
        } else if (read == 98) {
            return 8;
        } else {
            if (read == 102) {
                return 12;
            }
            if (read == 110) {
                return 10;
            }
            if (read == 114) {
                return 13;
            }
            if (read == 116) {
                return 9;
            }
            if (!(read == 12 || read == 13)) {
                switch (read) {
                    case 8:
                    case 9:
                    case 10:
                        break;
                    default:
                        if (isMetaChar(read)) {
                            return read;
                        }
                        return -1;
                }
            }
            return read;
        }
    }

    /* access modifiers changed from: package-private */
    public void trimTrailingSpaces(StringBuilder sb) {
        int length = sb.length();
        while (length > 0) {
            int i = length - 1;
            if (!Character.isWhitespace(sb.charAt(i))) {
                break;
            }
            length = i;
        }
        if (length != sb.length()) {
            sb.setLength(length);
        }
    }
}
