package jp.kshoji.javax.sound.midi;

public abstract class SoundbankResource {
    private final Class<?> dataClass;
    private final String name;
    private final Soundbank soundbank;

    public abstract Object getData();

    protected SoundbankResource(Soundbank soundbank2, String str, Class<?> cls) {
        this.soundbank = soundbank2;
        this.name = str;
        this.dataClass = cls;
    }

    public Class<?> getDataClass() {
        return this.dataClass;
    }

    public String getName() {
        return this.name;
    }

    public Soundbank getSoundbank() {
        return this.soundbank;
    }
}
