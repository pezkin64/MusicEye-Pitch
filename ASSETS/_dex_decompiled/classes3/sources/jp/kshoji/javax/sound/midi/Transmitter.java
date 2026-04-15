package jp.kshoji.javax.sound.midi;

public interface Transmitter {
    void close();

    Receiver getReceiver();

    void setReceiver(Receiver receiver);
}
