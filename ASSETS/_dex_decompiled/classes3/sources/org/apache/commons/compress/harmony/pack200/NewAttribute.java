package org.apache.commons.compress.harmony.pack200;

import com.google.firebase.encoders.json.BuildConfig;
import org.apache.commons.compress.harmony.pack200.Segment;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Label;

public class NewAttribute extends Attribute {
    private char[] buf;
    private ClassReader classReader;
    private int codeOff;
    private byte[] contents;
    private boolean contextClass = false;
    private boolean contextCode = false;
    private boolean contextField = false;
    private boolean contextMethod = false;
    private Label[] labels;
    private final String layout;

    public boolean isUnknown() {
        return false;
    }

    public NewAttribute(String str, String str2, int i) {
        super(str);
        this.layout = str2;
        addContext(i);
    }

    public NewAttribute(ClassReader classReader2, String str, String str2, byte[] bArr, char[] cArr, int i, Label[] labelArr) {
        super(str);
        this.classReader = classReader2;
        this.contents = bArr;
        this.layout = str2;
        this.codeOff = i;
        this.labels = labelArr;
        this.buf = cArr;
    }

    public void addContext(int i) {
        if (i == 0) {
            this.contextClass = true;
        } else if (i == 1) {
            this.contextField = true;
        } else if (i == 2) {
            this.contextMethod = true;
        } else if (i == 3) {
            this.contextCode = true;
        }
    }

    public boolean isContextClass() {
        return this.contextClass;
    }

    public boolean isContextMethod() {
        return this.contextMethod;
    }

    public boolean isContextField() {
        return this.contextField;
    }

    public boolean isContextCode() {
        return this.contextCode;
    }

    public String getLayout() {
        return this.layout;
    }

    public boolean isCodeAttribute() {
        return this.codeOff != -1;
    }

    /* access modifiers changed from: protected */
    public Attribute read(ClassReader classReader2, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
        byte[] bArr = new byte[i2];
        System.arraycopy(classReader2.b, i, bArr, 0, i2);
        return new NewAttribute(classReader2, this.type, this.layout, bArr, cArr, i3, labelArr);
    }

    public boolean isUnknown(int i) {
        boolean z;
        if (i == 0) {
            z = this.contextClass;
        } else if (i == 1) {
            z = this.contextField;
        } else if (i == 2) {
            z = this.contextMethod;
        } else if (i != 3) {
            return false;
        } else {
            z = this.contextCode;
        }
        return !z;
    }

    public String readUTF8(int i) {
        return this.classReader.readUTF8(i, this.buf);
    }

    public String readClass(int i) {
        return this.classReader.readClass(i, this.buf);
    }

    public Object readConst(int i) {
        return this.classReader.readConst(i, this.buf);
    }

    public byte[] getBytes() {
        return this.contents;
    }

    public Label getLabel(int i) {
        return this.labels[i];
    }

    public static class ErrorAttribute extends NewAttribute {
        public ErrorAttribute(String str, int i) {
            super(str, BuildConfig.FLAVOR, i);
        }

        /* access modifiers changed from: protected */
        public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            throw new Error("Attribute " + this.type + " was found");
        }
    }

    public static class StripAttribute extends NewAttribute {
        /* access modifiers changed from: protected */
        public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            return null;
        }

        public StripAttribute(String str, int i) {
            super(str, BuildConfig.FLAVOR, i);
        }
    }

    public static class PassAttribute extends NewAttribute {
        public PassAttribute(String str, int i) {
            super(str, BuildConfig.FLAVOR, i);
        }

        /* access modifiers changed from: protected */
        public Attribute read(ClassReader classReader, int i, int i2, char[] cArr, int i3, Label[] labelArr) {
            throw new Segment.PassException();
        }
    }
}
