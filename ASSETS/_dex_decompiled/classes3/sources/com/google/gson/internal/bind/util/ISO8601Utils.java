package com.google.gson.internal.bind.util;

import com.google.firebase.encoders.json.BuildConfig;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    private static final String UTC_ID = "UTC";

    private ISO8601Utils() {
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        char c = '-';
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int abs = Math.abs(i / 60);
            int abs2 = Math.abs(i % 60);
            if (offset >= 0) {
                c = '+';
            }
            sb.append(c);
            padInt(sb, abs, 2);
            sb.append(':');
            padInt(sb, abs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        TimeZone timeZone;
        char charAt;
        String str3 = str;
        ParsePosition parsePosition2 = parsePosition;
        try {
            int index = parsePosition2.getIndex();
            int i7 = index + 4;
            int parseInt = parseInt(str3, index, i7);
            if (checkOffset(str3, i7, '-')) {
                i7 = index + 5;
            }
            int i8 = i7 + 2;
            int parseInt2 = parseInt(str3, i7, i8);
            if (checkOffset(str3, i8, '-')) {
                i8 = i7 + 3;
            }
            int i9 = i8 + 2;
            int parseInt3 = parseInt(str3, i8, i9);
            boolean checkOffset = checkOffset(str3, i9, 'T');
            if (checkOffset || str3.length() > i9) {
                if (checkOffset) {
                    int i10 = i8 + 5;
                    int parseInt4 = parseInt(str3, i8 + 3, i10);
                    if (checkOffset(str3, i10, ':')) {
                        i10 = i8 + 6;
                    }
                    int i11 = i10 + 2;
                    int parseInt5 = parseInt(str3, i10, i11);
                    if (checkOffset(str3, i11, ':')) {
                        i11 = i10 + 3;
                    }
                    if (str3.length() <= i11 || (charAt = str3.charAt(i11)) == 'Z' || charAt == '+' || charAt == '-') {
                        int i12 = parseInt4;
                        i9 = i11;
                        i5 = i12;
                        i4 = parseInt5;
                        i3 = 0;
                        i2 = 0;
                    } else {
                        int i13 = i11 + 2;
                        i2 = parseInt(str3, i11, i13);
                        if (i2 > 59 && i2 < 63) {
                            i2 = 59;
                        }
                        if (checkOffset(str3, i13, '.')) {
                            int i14 = i11 + 3;
                            int indexOfNonDigit = indexOfNonDigit(str3, i11 + 4);
                            int min = Math.min(indexOfNonDigit, i11 + 6);
                            int parseInt6 = parseInt(str3, i14, min);
                            int i15 = min - i14;
                            if (i15 == 1) {
                                parseInt6 *= 100;
                            } else if (i15 == 2) {
                                parseInt6 *= 10;
                            }
                            i5 = parseInt4;
                            i9 = indexOfNonDigit;
                            i4 = parseInt5;
                            i3 = parseInt6;
                        } else {
                            i5 = parseInt4;
                            i9 = i13;
                            i4 = parseInt5;
                            i3 = 0;
                        }
                    }
                    i = 1;
                } else {
                    i = 1;
                    i5 = 0;
                    i4 = 0;
                    i3 = 0;
                    i2 = 0;
                }
                if (str3.length() > i9) {
                    char charAt2 = str3.charAt(i9);
                    if (charAt2 == 'Z') {
                        timeZone = TIMEZONE_UTC;
                        i6 = i9 + 1;
                    } else {
                        if (charAt2 != '+') {
                            if (charAt2 != '-') {
                                throw new IndexOutOfBoundsException("Invalid time zone indicator '" + charAt2 + "'");
                            }
                        }
                        String substring = str3.substring(i9);
                        if (substring.length() < 5) {
                            substring = substring + TarConstants.VERSION_POSIX;
                        }
                        i6 = i9 + substring.length();
                        if (!substring.equals("+0000")) {
                            if (!substring.equals("+00:00")) {
                                String str4 = "GMT" + substring;
                                TimeZone timeZone2 = TimeZone.getTimeZone(str4);
                                String id = timeZone2.getID();
                                if (!id.equals(str4)) {
                                    if (!id.replace(":", BuildConfig.FLAVOR).equals(str4)) {
                                        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str4 + " given, resolves to " + timeZone2.getID());
                                    }
                                }
                                timeZone = timeZone2;
                            }
                        }
                        timeZone = TIMEZONE_UTC;
                    }
                    GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
                    gregorianCalendar.setLenient(false);
                    int i16 = i;
                    gregorianCalendar.set(i16, parseInt);
                    gregorianCalendar.set(2, parseInt2 - i16);
                    gregorianCalendar.set(5, parseInt3);
                    gregorianCalendar.set(11, i5);
                    gregorianCalendar.set(12, i4);
                    gregorianCalendar.set(13, i2);
                    gregorianCalendar.set(14, i3);
                    parsePosition2.setIndex(i6);
                    return gregorianCalendar.getTime();
                }
                throw new IllegalArgumentException("No time zone indicator");
            }
            GregorianCalendar gregorianCalendar2 = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
            gregorianCalendar2.setLenient(false);
            parsePosition2.setIndex(i9);
            return gregorianCalendar2.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            if (str3 == null) {
                str2 = null;
            } else {
                str2 = "\"" + str3 + Typography.quote;
            }
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition2.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
