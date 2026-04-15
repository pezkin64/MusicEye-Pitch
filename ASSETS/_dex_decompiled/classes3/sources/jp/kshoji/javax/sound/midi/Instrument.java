package jp.kshoji.javax.sound.midi;

public abstract class Instrument extends SoundbankResource {
    private final Patch patch;

    protected Instrument(Soundbank soundbank, Patch patch2, String str, Class<?> cls) {
        super(soundbank, str, cls);
        this.patch = patch2;
    }

    public Patch getPatch() {
        return this.patch;
    }
}
