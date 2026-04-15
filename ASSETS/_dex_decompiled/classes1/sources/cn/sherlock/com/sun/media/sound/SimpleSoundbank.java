package cn.sherlock.com.sun.media.sound;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.SoundbankResource;

public class SimpleSoundbank implements Soundbank {
    String description = "";
    List<Instrument> instruments = new ArrayList();
    String name = "";
    List<SoundbankResource> resources = new ArrayList();
    String vendor = "";
    String version = "";

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVendor() {
        return this.vendor;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setVendor(String str) {
        this.vendor = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public SoundbankResource[] getResources() {
        List<SoundbankResource> list = this.resources;
        return (SoundbankResource[]) list.toArray(new SoundbankResource[list.size()]);
    }

    public Instrument[] getInstruments() {
        Instrument[] instrumentArr = (Instrument[]) this.instruments.toArray(new Instrument[this.resources.size()]);
        Arrays.sort(instrumentArr, new ModelInstrumentComparator());
        return instrumentArr;
    }

    public Instrument getInstrument(Patch patch) {
        int program = patch.getProgram();
        int bank = patch.getBank();
        boolean isPercussion = patch instanceof ModelPatch ? ((ModelPatch) patch).isPercussion() : false;
        for (Instrument next : this.instruments) {
            ModelPatch patch2 = next.getPatch();
            int program2 = patch2.getProgram();
            int bank2 = patch2.getBank();
            if (program == program2 && bank == bank2) {
                if (isPercussion == (patch2 instanceof ModelPatch ? patch2.isPercussion() : false)) {
                    return next;
                }
            }
        }
        return null;
    }

    public void addResource(SoundbankResource soundbankResource) {
        if (soundbankResource instanceof Instrument) {
            this.instruments.add((Instrument) soundbankResource);
        } else {
            this.resources.add(soundbankResource);
        }
    }

    public void removeResource(SoundbankResource soundbankResource) {
        if (soundbankResource instanceof Instrument) {
            this.instruments.remove((Instrument) soundbankResource);
        } else {
            this.resources.remove(soundbankResource);
        }
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    public void removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
    }

    public void addAllInstruments(Soundbank soundbank) {
        for (Instrument addInstrument : soundbank.getInstruments()) {
            addInstrument(addInstrument);
        }
    }

    public void removeAllInstruments(Soundbank soundbank) {
        for (Instrument removeInstrument : soundbank.getInstruments()) {
            removeInstrument(removeInstrument);
        }
    }
}
