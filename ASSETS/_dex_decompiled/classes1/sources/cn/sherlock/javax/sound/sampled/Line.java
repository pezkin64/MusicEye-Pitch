package cn.sherlock.javax.sound.sampled;

public interface Line extends AutoCloseable {
    void close();

    Info getLineInfo();

    boolean isOpen();

    void open() throws LineUnavailableException;

    public static class Info {
        private final Class lineClass;

        public Info(Class<?> cls) {
            if (cls == null) {
                this.lineClass = Line.class;
            } else {
                this.lineClass = cls;
            }
        }

        public Class<?> getLineClass() {
            return this.lineClass;
        }

        public boolean matches(Info info) {
            if (getClass().isInstance(info) && getLineClass().isAssignableFrom(info.getLineClass())) {
                return true;
            }
            return false;
        }

        public String toString() {
            String str = new String(getLineClass().toString());
            int indexOf = str.indexOf("javax.sound.sampled.");
            if (indexOf == -1) {
                return str;
            }
            return str.substring(0, indexOf) + str.substring(indexOf + 20, str.length());
        }
    }
}
