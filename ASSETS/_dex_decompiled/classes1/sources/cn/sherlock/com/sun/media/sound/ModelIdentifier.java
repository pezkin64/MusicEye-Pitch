package cn.sherlock.com.sun.media.sound;

public class ModelIdentifier {
    private int instance;
    private String object;
    private String variable;

    public ModelIdentifier(String str) {
        this.variable = null;
        this.instance = 0;
        this.object = str;
    }

    public ModelIdentifier(String str, int i) {
        this.variable = null;
        this.object = str;
        this.instance = i;
    }

    public ModelIdentifier(String str, String str2) {
        this.instance = 0;
        this.object = str;
        this.variable = str2;
    }

    public ModelIdentifier(String str, String str2, int i) {
        this.object = str;
        this.variable = str2;
        this.instance = i;
    }

    public int getInstance() {
        return this.instance;
    }

    public void setInstance(int i) {
        this.instance = i;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String str) {
        this.object = str;
    }

    public String getVariable() {
        return this.variable;
    }

    public void setVariable(String str) {
        this.variable = str;
    }

    public int hashCode() {
        int i = this.instance;
        String str = this.object;
        if (str != null) {
            i |= str.hashCode();
        }
        String str2 = this.variable;
        return str2 != null ? i | str2.hashCode() : i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ModelIdentifier)) {
            return false;
        }
        ModelIdentifier modelIdentifier = (ModelIdentifier) obj;
        if ((this.object == null) != (modelIdentifier.object == null)) {
            return false;
        }
        if ((this.variable == null) != (modelIdentifier.variable == null) || modelIdentifier.getInstance() != getInstance()) {
            return false;
        }
        String str = this.object;
        if (str != null && !str.equals(modelIdentifier.object)) {
            return false;
        }
        String str2 = this.variable;
        if (str2 == null || str2.equals(modelIdentifier.variable)) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (this.variable == null) {
            return this.object + "[" + this.instance + "]";
        }
        return this.object + "[" + this.instance + "]." + this.variable;
    }
}
