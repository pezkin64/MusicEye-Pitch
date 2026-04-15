package cn.sherlock.com.sun.media.sound;

public class AudioSynthesizerPropertyInfo {
    public Object[] choices = null;
    public String description = null;
    public String name;
    public Object value = null;
    public Class valueClass = null;

    public AudioSynthesizerPropertyInfo(String str, Object obj) {
        this.name = str;
        if (obj instanceof Class) {
            this.valueClass = (Class) obj;
            return;
        }
        this.value = obj;
        if (obj != null) {
            this.valueClass = obj.getClass();
        }
    }
}
