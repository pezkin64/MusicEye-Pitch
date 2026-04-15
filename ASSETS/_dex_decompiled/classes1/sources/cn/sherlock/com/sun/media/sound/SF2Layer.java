package cn.sherlock.com.sun.media.sound;

import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.SoundbankResource;

public class SF2Layer extends SoundbankResource {
    protected SF2GlobalRegion globalregion = null;
    protected String name = "";
    protected List<SF2LayerRegion> regions = new ArrayList();

    public Object getData() {
        return null;
    }

    public SF2Layer(SF2Soundbank sF2Soundbank) {
        super(sF2Soundbank, (String) null, (Class) null);
    }

    public SF2Layer() {
        super((Soundbank) null, (String) null, (Class) null);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<SF2LayerRegion> getRegions() {
        return this.regions;
    }

    public SF2GlobalRegion getGlobalRegion() {
        return this.globalregion;
    }

    public void setGlobalZone(SF2GlobalRegion sF2GlobalRegion) {
        this.globalregion = sF2GlobalRegion;
    }

    public String toString() {
        return "Layer: " + this.name;
    }
}
