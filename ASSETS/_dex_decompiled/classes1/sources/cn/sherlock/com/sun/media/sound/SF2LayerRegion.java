package cn.sherlock.com.sun.media.sound;

public class SF2LayerRegion extends SF2Region {
    protected SF2Sample sample;

    public SF2Sample getSample() {
        return this.sample;
    }

    public void setSample(SF2Sample sF2Sample) {
        this.sample = sF2Sample;
    }
}
