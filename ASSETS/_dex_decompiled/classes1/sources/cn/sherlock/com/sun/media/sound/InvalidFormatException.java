package cn.sherlock.com.sun.media.sound;

public class InvalidFormatException extends InvalidDataException {
    private static final long serialVersionUID = 1;

    public InvalidFormatException() {
        super("Invalid format!");
    }

    public InvalidFormatException(String str) {
        super(str);
    }
}
