package cn.sherlock.com.sun.media.sound;

import java.io.IOException;

public class InvalidDataException extends IOException {
    private static final long serialVersionUID = 1;

    public InvalidDataException() {
        super("Invalid Data!");
    }

    public InvalidDataException(String str) {
        super(str);
    }
}
