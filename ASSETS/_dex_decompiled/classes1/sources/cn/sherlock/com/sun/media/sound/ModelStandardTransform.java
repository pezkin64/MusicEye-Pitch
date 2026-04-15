package cn.sherlock.com.sun.media.sound;

public class ModelStandardTransform implements ModelTransform {
    public static final boolean DIRECTION_MAX2MIN = true;
    public static final boolean DIRECTION_MIN2MAX = false;
    public static final boolean POLARITY_BIPOLAR = true;
    public static final boolean POLARITY_UNIPOLAR = false;
    public static final int TRANSFORM_ABSOLUTE = 4;
    public static final int TRANSFORM_CONCAVE = 1;
    public static final int TRANSFORM_CONVEX = 2;
    public static final int TRANSFORM_LINEAR = 0;
    public static final int TRANSFORM_SWITCH = 3;
    private boolean direction;
    private boolean polarity;
    private int transform;

    public ModelStandardTransform() {
        this.direction = false;
        this.polarity = false;
        this.transform = 0;
    }

    public ModelStandardTransform(boolean z) {
        this.polarity = false;
        this.transform = 0;
        this.direction = z;
    }

    public ModelStandardTransform(boolean z, boolean z2) {
        this.transform = 0;
        this.direction = z;
        this.polarity = z2;
    }

    public ModelStandardTransform(boolean z, boolean z2, int i) {
        this.direction = z;
        this.polarity = z2;
        this.transform = i;
    }

    public double transform(double d) {
        double d2 = 1.0d;
        if (this.direction) {
            d = 1.0d - d;
        }
        boolean z = this.polarity;
        if (z) {
            d = (d * 2.0d) - 1.0d;
        }
        int i = this.transform;
        if (i == 1) {
            double signum = Math.signum(d);
            double log = (-(0.4166666666666667d / Math.log(10.0d))) * Math.log(1.0d - Math.abs(d));
            if (log < 0.0d) {
                d2 = 0.0d;
            } else if (log <= 1.0d) {
                d2 = log;
            }
            return signum * d2;
        } else if (i == 2) {
            double signum2 = Math.signum(d);
            double log2 = ((0.4166666666666667d / Math.log(10.0d)) * Math.log(Math.abs(d))) + 1.0d;
            if (log2 < 0.0d) {
                d2 = 0.0d;
            } else if (log2 <= 1.0d) {
                d2 = log2;
            }
            return signum2 * d2;
        } else if (i != 3) {
            return i != 4 ? d : Math.abs(d);
        } else {
            if (z) {
                if (d > 0.0d) {
                    return 1.0d;
                }
                return -1.0d;
            } else if (d > 0.5d) {
                return 1.0d;
            } else {
                return 0.0d;
            }
        }
    }

    public boolean getDirection() {
        return this.direction;
    }

    public void setDirection(boolean z) {
        this.direction = z;
    }

    public boolean getPolarity() {
        return this.polarity;
    }

    public void setPolarity(boolean z) {
        this.polarity = z;
    }

    public int getTransform() {
        return this.transform;
    }

    public void setTransform(int i) {
        this.transform = i;
    }
}
