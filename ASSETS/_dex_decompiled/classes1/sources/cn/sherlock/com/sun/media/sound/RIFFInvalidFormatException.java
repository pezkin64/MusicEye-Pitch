package cn.sherlock.com.sun.media.sound;

public class RIFFInvalidFormatException extends InvalidFormatException {
    private static final long serialVersionUID = 1;

    public RIFFInvalidFormatException() {
        super("Invalid format!");
    }

    public RIFFInvalidFormatException(String str) {
        super(str);
    }
}
