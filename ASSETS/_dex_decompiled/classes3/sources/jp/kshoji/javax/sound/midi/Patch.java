package jp.kshoji.javax.sound.midi;

public class Patch {
    private final int bank;
    private final int program;

    public Patch(int i, int i2) {
        this.bank = i;
        this.program = i2;
    }

    public int getBank() {
        return this.bank;
    }

    public int getProgram() {
        return this.program;
    }
}
