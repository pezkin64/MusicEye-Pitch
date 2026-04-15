package cn.sherlock.com.sun.media.sound;

public class SF2InstrumentRegion extends SF2Region {
    protected SF2Layer layer;

    public SF2Layer getLayer() {
        return this.layer;
    }

    public void setLayer(SF2Layer sF2Layer) {
        this.layer = sF2Layer;
    }
}
