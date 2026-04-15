package org.apache.commons.csv;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public final class CSVPrinter implements Flushable, Closeable {
    private final Appendable appendable;
    private final CSVFormat format;
    private boolean newRecord = true;

    public CSVPrinter(Appendable appendable2, CSVFormat cSVFormat) throws IOException {
        Objects.requireNonNull(appendable2, "appendable");
        Objects.requireNonNull(cSVFormat, "format");
        this.appendable = appendable2;
        this.format = cSVFormat.copy();
        if (cSVFormat.getHeaderComments() != null) {
            for (String printComment : cSVFormat.getHeaderComments()) {
                printComment(printComment);
            }
        }
        if (cSVFormat.getHeader() != null && !cSVFormat.getSkipHeaderRecord()) {
            printRecord((Object[]) cSVFormat.getHeader());
        }
    }

    public void close() throws IOException {
        close(false);
    }

    public void close(boolean z) throws IOException {
        if (z || this.format.getAutoFlush()) {
            flush();
        }
        Appendable appendable2 = this.appendable;
        if (appendable2 instanceof Closeable) {
            ((Closeable) appendable2).close();
        }
    }

    public void flush() throws IOException {
        Appendable appendable2 = this.appendable;
        if (appendable2 instanceof Flushable) {
            ((Flushable) appendable2).flush();
        }
    }

    public Appendable getOut() {
        return this.appendable;
    }

    public void print(Object obj) throws IOException {
        this.format.print(obj, this.appendable, this.newRecord);
        this.newRecord = false;
    }

    public void printComment(String str) throws IOException {
        if (str != null && this.format.isCommentMarkerSet()) {
            if (!this.newRecord) {
                println();
            }
            this.appendable.append(this.format.getCommentMarker().charValue());
            this.appendable.append(' ');
            int i = 0;
            while (i < str.length()) {
                char charAt = str.charAt(i);
                if (charAt != 10) {
                    if (charAt != 13) {
                        this.appendable.append(charAt);
                        i++;
                    } else {
                        int i2 = i + 1;
                        if (i2 < str.length() && str.charAt(i2) == 10) {
                            i = i2;
                        }
                    }
                }
                println();
                this.appendable.append(this.format.getCommentMarker().charValue());
                this.appendable.append(' ');
                i++;
            }
            println();
        }
    }

    public void printHeaders(ResultSet resultSet) throws IOException, SQLException {
        printRecord((Object[]) this.format.builder().setHeader(resultSet).build().getHeader());
    }

    public void println() throws IOException {
        this.format.println(this.appendable);
        this.newRecord = true;
    }

    public void printRecord(Iterable<?> iterable) throws IOException {
        for (Object print : iterable) {
            print(print);
        }
        println();
    }

    public void printRecord(Object... objArr) throws IOException {
        printRecord((Iterable<?>) Arrays.asList(objArr));
    }

    public void printRecords(Iterable<?> iterable) throws IOException {
        for (Object next : iterable) {
            if (next instanceof Object[]) {
                printRecord((Object[]) next);
            } else if (next instanceof Iterable) {
                printRecord((Iterable<?>) (Iterable) next);
            } else {
                printRecord(next);
            }
        }
    }

    public void printRecords(Object... objArr) throws IOException {
        printRecords((Iterable<?>) Arrays.asList(objArr));
    }

    public void printRecords(ResultSet resultSet) throws SQLException, IOException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnCount; i++) {
                Object object = resultSet.getObject(i);
                if (object instanceof Clob) {
                    object = ((Clob) object).getCharacterStream();
                }
                print(object);
            }
            println();
        }
    }

    public void printRecords(ResultSet resultSet, boolean z) throws SQLException, IOException {
        if (z) {
            printHeaders(resultSet);
        }
        printRecords(resultSet);
    }
}
