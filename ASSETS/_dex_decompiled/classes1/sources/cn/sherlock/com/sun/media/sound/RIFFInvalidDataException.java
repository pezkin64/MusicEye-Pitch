package cn.sherlock.com.sun.media.sound;

public class RIFFInvalidDataException extends InvalidDataException {
    private static final long serialVersionUID = 1;

    public RIFFInvalidDataException() {
        super("Invalid Data!");
    }

    public RIFFInvalidDataException(String str) {
        super(str);
    }
}
