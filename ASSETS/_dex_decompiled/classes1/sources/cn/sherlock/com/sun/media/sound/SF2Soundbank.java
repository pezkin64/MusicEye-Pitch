package cn.sherlock.com.sun.media.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import jp.kshoji.javax.sound.midi.Instrument;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;
import jp.kshoji.javax.sound.midi.SoundbankResource;

public class SF2Soundbank implements Soundbank {
    protected String comments;
    protected String copyright;
    protected String creationDate;
    protected String engineers;
    private List<SF2Instrument> instruments;
    private boolean largeFormat;
    private List<SF2Layer> layers;
    protected int major;
    protected int minor;
    protected String name;
    protected String product;
    protected String romName;
    protected int romVersionMajor;
    protected int romVersionMinor;
    private ModelByteBuffer sampleData;
    private ModelByteBuffer sampleData24;
    private File sampleFile;
    private List<SF2Sample> samples;
    protected String targetEngine;
    protected String tools;

    public SF2Soundbank() {
        this.major = 2;
        this.minor = 1;
        this.targetEngine = "EMU8000";
        this.name = "untitled";
        this.romName = null;
        this.romVersionMajor = -1;
        this.romVersionMinor = -1;
        this.creationDate = null;
        this.engineers = null;
        this.product = null;
        this.copyright = null;
        this.comments = null;
        this.tools = null;
        this.sampleData = null;
        this.sampleData24 = null;
        this.sampleFile = null;
        this.largeFormat = false;
        this.instruments = new ArrayList();
        this.layers = new ArrayList();
        this.samples = new ArrayList();
    }

    public SF2Soundbank(URL url) throws IOException {
        this.major = 2;
        this.minor = 1;
        this.targetEngine = "EMU8000";
        this.name = "untitled";
        this.romName = null;
        this.romVersionMajor = -1;
        this.romVersionMinor = -1;
        this.creationDate = null;
        this.engineers = null;
        this.product = null;
        this.copyright = null;
        this.comments = null;
        this.tools = null;
        this.sampleData = null;
        this.sampleData24 = null;
        this.sampleFile = null;
        this.largeFormat = false;
        this.instruments = new ArrayList();
        this.layers = new ArrayList();
        this.samples = new ArrayList();
        InputStream openStream = url.openStream();
        try {
            readSoundbank(openStream);
        } finally {
            openStream.close();
        }
    }

    public SF2Soundbank(File file) throws IOException {
        this.major = 2;
        this.minor = 1;
        this.targetEngine = "EMU8000";
        this.name = "untitled";
        this.romName = null;
        this.romVersionMajor = -1;
        this.romVersionMinor = -1;
        this.creationDate = null;
        this.engineers = null;
        this.product = null;
        this.copyright = null;
        this.comments = null;
        this.tools = null;
        this.sampleData = null;
        this.sampleData24 = null;
        this.sampleFile = null;
        this.largeFormat = false;
        this.instruments = new ArrayList();
        this.layers = new ArrayList();
        this.samples = new ArrayList();
        this.largeFormat = true;
        this.sampleFile = file;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            readSoundbank(fileInputStream);
        } finally {
            fileInputStream.close();
        }
    }

    public SF2Soundbank(InputStream inputStream) throws IOException {
        this.major = 2;
        this.minor = 1;
        this.targetEngine = "EMU8000";
        this.name = "untitled";
        this.romName = null;
        this.romVersionMajor = -1;
        this.romVersionMinor = -1;
        this.creationDate = null;
        this.engineers = null;
        this.product = null;
        this.copyright = null;
        this.comments = null;
        this.tools = null;
        this.sampleData = null;
        this.sampleData24 = null;
        this.sampleFile = null;
        this.largeFormat = false;
        this.instruments = new ArrayList();
        this.layers = new ArrayList();
        this.samples = new ArrayList();
        readSoundbank(inputStream);
    }

    private void readSoundbank(InputStream inputStream) throws IOException {
        RIFFReader rIFFReader = new RIFFReader(inputStream);
        if (!rIFFReader.getFormat().equals("RIFF")) {
            throw new RIFFInvalidFormatException("Input stream is not a valid RIFF stream!");
        } else if (rIFFReader.getType().equals("sfbk")) {
            while (rIFFReader.hasNextChunk()) {
                RIFFReader nextChunk = rIFFReader.nextChunk();
                if (nextChunk.getFormat().equals("LIST")) {
                    if (nextChunk.getType().equals("INFO")) {
                        readInfoChunk(nextChunk);
                    }
                    if (nextChunk.getType().equals("sdta")) {
                        readSdtaChunk(nextChunk);
                    }
                    if (nextChunk.getType().equals("pdta")) {
                        readPdtaChunk(nextChunk);
                    }
                }
            }
        } else {
            throw new RIFFInvalidFormatException("Input stream is not a valid SoundFont!");
        }
    }

    private void readInfoChunk(RIFFReader rIFFReader) throws IOException {
        while (rIFFReader.hasNextChunk()) {
            RIFFReader nextChunk = rIFFReader.nextChunk();
            String format = nextChunk.getFormat();
            if (format.equals("ifil")) {
                this.major = nextChunk.readUnsignedShort();
                this.minor = nextChunk.readUnsignedShort();
            } else if (format.equals("isng")) {
                this.targetEngine = nextChunk.readString(nextChunk.available());
            } else if (format.equals("INAM")) {
                this.name = nextChunk.readString(nextChunk.available());
            } else if (format.equals("irom")) {
                this.romName = nextChunk.readString(nextChunk.available());
            } else if (format.equals("iver")) {
                this.romVersionMajor = nextChunk.readUnsignedShort();
                this.romVersionMinor = nextChunk.readUnsignedShort();
            } else if (format.equals("ICRD")) {
                this.creationDate = nextChunk.readString(nextChunk.available());
            } else if (format.equals("IENG")) {
                this.engineers = nextChunk.readString(nextChunk.available());
            } else if (format.equals("IPRD")) {
                this.product = nextChunk.readString(nextChunk.available());
            } else if (format.equals("ICOP")) {
                this.copyright = nextChunk.readString(nextChunk.available());
            } else if (format.equals("ICMT")) {
                this.comments = nextChunk.readString(nextChunk.available());
            } else if (format.equals("ISFT")) {
                this.tools = nextChunk.readString(nextChunk.available());
            }
        }
    }

    private void readSdtaChunk(RIFFReader rIFFReader) throws IOException {
        while (rIFFReader.hasNextChunk()) {
            RIFFReader nextChunk = rIFFReader.nextChunk();
            int i = 0;
            if (nextChunk.getFormat().equals("smpl")) {
                if (!this.largeFormat) {
                    byte[] bArr = new byte[nextChunk.available()];
                    int available = nextChunk.available();
                    int i2 = 0;
                    while (i2 != available) {
                        int i3 = available - i2;
                        if (i3 > 65536) {
                            nextChunk.readFully(bArr, i2, 65536);
                            i2 += 65536;
                        } else {
                            nextChunk.readFully(bArr, i2, i3);
                            i2 = available;
                        }
                    }
                    this.sampleData = new ModelByteBuffer(bArr);
                } else {
                    this.sampleData = new ModelByteBuffer(this.sampleFile, nextChunk.getFilePointer(), (long) nextChunk.available());
                }
            }
            if (nextChunk.getFormat().equals("sm24")) {
                if (!this.largeFormat) {
                    byte[] bArr2 = new byte[nextChunk.available()];
                    int available2 = nextChunk.available();
                    while (i != available2) {
                        int i4 = available2 - i;
                        if (i4 > 65536) {
                            nextChunk.readFully(bArr2, i, 65536);
                            i += 65536;
                        } else {
                            nextChunk.readFully(bArr2, i, i4);
                            i = available2;
                        }
                    }
                    this.sampleData24 = new ModelByteBuffer(bArr2);
                } else {
                    this.sampleData24 = new ModelByteBuffer(this.sampleFile, nextChunk.getFilePointer(), (long) nextChunk.available());
                }
            }
        }
    }

    private void readPdtaChunk(RIFFReader rIFFReader) throws IOException {
        ArrayList arrayList;
        ArrayList arrayList2;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        ArrayList arrayList7 = new ArrayList();
        ArrayList arrayList8 = new ArrayList();
        ArrayList arrayList9 = new ArrayList();
        ArrayList arrayList10 = new ArrayList();
        while (rIFFReader.hasNextChunk()) {
            RIFFReader nextChunk = rIFFReader.nextChunk();
            String format = nextChunk.getFormat();
            int i = 20;
            int i2 = 0;
            if (format.equals("phdr")) {
                if (nextChunk.available() % 38 == 0) {
                    int available = nextChunk.available() / 38;
                    while (i2 < available) {
                        SF2Instrument sF2Instrument = new SF2Instrument(this);
                        sF2Instrument.name = nextChunk.readString(i);
                        sF2Instrument.preset = nextChunk.readUnsignedShort();
                        sF2Instrument.bank = nextChunk.readUnsignedShort();
                        arrayList4.add(Integer.valueOf(nextChunk.readUnsignedShort()));
                        int i3 = i2;
                        sF2Instrument.library = nextChunk.readUnsignedInt();
                        sF2Instrument.genre = nextChunk.readUnsignedInt();
                        sF2Instrument.morphology = nextChunk.readUnsignedInt();
                        arrayList3.add(sF2Instrument);
                        if (i3 != available - 1) {
                            this.instruments.add(sF2Instrument);
                        }
                        i2 = i3 + 1;
                        i = 20;
                    }
                } else {
                    throw new RIFFInvalidDataException();
                }
            } else if (!format.equals("pbag")) {
                arrayList2 = arrayList3;
                arrayList = arrayList4;
                if (format.equals("pmod")) {
                    for (int i4 = 0; i4 < arrayList6.size(); i4++) {
                        SF2Modulator sF2Modulator = new SF2Modulator();
                        sF2Modulator.sourceOperator = nextChunk.readUnsignedShort();
                        sF2Modulator.destinationOperator = nextChunk.readUnsignedShort();
                        sF2Modulator.amount = nextChunk.readShort();
                        sF2Modulator.amountSourceOperator = nextChunk.readUnsignedShort();
                        sF2Modulator.transportOperator = nextChunk.readUnsignedShort();
                        SF2InstrumentRegion sF2InstrumentRegion = (SF2InstrumentRegion) arrayList6.get(i4);
                        if (sF2InstrumentRegion != null) {
                            sF2InstrumentRegion.modulators.add(sF2Modulator);
                        }
                    }
                } else if (format.equals("pgen")) {
                    for (int i5 = 0; i5 < arrayList5.size(); i5++) {
                        int readUnsignedShort = nextChunk.readUnsignedShort();
                        short readShort = nextChunk.readShort();
                        SF2InstrumentRegion sF2InstrumentRegion2 = (SF2InstrumentRegion) arrayList5.get(i5);
                        if (sF2InstrumentRegion2 != null) {
                            sF2InstrumentRegion2.generators.put(Integer.valueOf(readUnsignedShort), Short.valueOf(readShort));
                        }
                    }
                } else if (format.equals("inst")) {
                    if (nextChunk.available() % 22 == 0) {
                        int available2 = nextChunk.available() / 22;
                        for (int i6 = 0; i6 < available2; i6++) {
                            SF2Layer sF2Layer = new SF2Layer(this);
                            sF2Layer.name = nextChunk.readString(20);
                            arrayList8.add(Integer.valueOf(nextChunk.readUnsignedShort()));
                            arrayList7.add(sF2Layer);
                            if (i6 != available2 - 1) {
                                this.layers.add(sF2Layer);
                            }
                        }
                    } else {
                        throw new RIFFInvalidDataException();
                    }
                } else if (format.equals("ibag")) {
                    if (nextChunk.available() % 4 == 0) {
                        int available3 = nextChunk.available() / 4;
                        int readUnsignedShort2 = nextChunk.readUnsignedShort();
                        int readUnsignedShort3 = nextChunk.readUnsignedShort();
                        while (arrayList9.size() < readUnsignedShort2) {
                            arrayList9.add((Object) null);
                        }
                        while (arrayList10.size() < readUnsignedShort3) {
                            arrayList10.add((Object) null);
                        }
                        int i7 = available3 - 1;
                        int i8 = 0;
                        int intValue = ((Integer) arrayList8.get(0)).intValue();
                        int i9 = 0;
                        while (i9 < intValue) {
                            if (i7 != 0) {
                                int readUnsignedShort4 = nextChunk.readUnsignedShort();
                                int readUnsignedShort5 = nextChunk.readUnsignedShort();
                                while (arrayList9.size() < readUnsignedShort4) {
                                    arrayList9.add((Object) null);
                                }
                                while (arrayList10.size() < readUnsignedShort5) {
                                    arrayList10.add((Object) null);
                                }
                                i7--;
                                i9++;
                            } else {
                                throw new RIFFInvalidDataException();
                            }
                        }
                        int i10 = 0;
                        while (i10 < arrayList8.size() - 1) {
                            int i11 = i10 + 1;
                            int intValue2 = ((Integer) arrayList8.get(i11)).intValue() - ((Integer) arrayList8.get(i10)).intValue();
                            SF2Layer sF2Layer2 = this.layers.get(i10);
                            int i12 = i8;
                            while (i12 < intValue2) {
                                if (i7 != 0) {
                                    int readUnsignedShort6 = nextChunk.readUnsignedShort();
                                    int readUnsignedShort7 = nextChunk.readUnsignedShort();
                                    SF2LayerRegion sF2LayerRegion = new SF2LayerRegion();
                                    int i13 = i7;
                                    sF2Layer2.regions.add(sF2LayerRegion);
                                    while (arrayList9.size() < readUnsignedShort6) {
                                        arrayList9.add(sF2LayerRegion);
                                    }
                                    while (arrayList10.size() < readUnsignedShort7) {
                                        arrayList10.add(sF2LayerRegion);
                                    }
                                    i7 = i13 - 1;
                                    i12++;
                                    i8 = 0;
                                } else {
                                    throw new RIFFInvalidDataException();
                                }
                            }
                            int i14 = i7;
                            i10 = i11;
                        }
                        continue;
                    } else {
                        throw new RIFFInvalidDataException();
                    }
                } else if (format.equals("imod")) {
                    for (int i15 = 0; i15 < arrayList10.size(); i15++) {
                        SF2Modulator sF2Modulator2 = new SF2Modulator();
                        sF2Modulator2.sourceOperator = nextChunk.readUnsignedShort();
                        sF2Modulator2.destinationOperator = nextChunk.readUnsignedShort();
                        sF2Modulator2.amount = nextChunk.readShort();
                        sF2Modulator2.amountSourceOperator = nextChunk.readUnsignedShort();
                        sF2Modulator2.transportOperator = nextChunk.readUnsignedShort();
                        SF2LayerRegion sF2LayerRegion2 = (SF2LayerRegion) arrayList9.get(i15);
                        if (sF2LayerRegion2 != null) {
                            sF2LayerRegion2.modulators.add(sF2Modulator2);
                        }
                    }
                } else if (format.equals("igen")) {
                    for (int i16 = 0; i16 < arrayList9.size(); i16++) {
                        int readUnsignedShort8 = nextChunk.readUnsignedShort();
                        short readShort2 = nextChunk.readShort();
                        SF2LayerRegion sF2LayerRegion3 = (SF2LayerRegion) arrayList9.get(i16);
                        if (sF2LayerRegion3 != null) {
                            sF2LayerRegion3.generators.put(Integer.valueOf(readUnsignedShort8), Short.valueOf(readShort2));
                        }
                    }
                } else if (!format.equals("shdr")) {
                    continue;
                } else if (nextChunk.available() % 46 == 0) {
                    int available4 = nextChunk.available() / 46;
                    for (int i17 = 0; i17 < available4; i17++) {
                        SF2Sample sF2Sample = new SF2Sample(this);
                        sF2Sample.name = nextChunk.readString(20);
                        long readUnsignedInt = nextChunk.readUnsignedInt();
                        long readUnsignedInt2 = nextChunk.readUnsignedInt();
                        sF2Sample.data = this.sampleData.subbuffer(readUnsignedInt * 2, readUnsignedInt2 * 2, true);
                        ModelByteBuffer modelByteBuffer = this.sampleData24;
                        if (modelByteBuffer != null) {
                            sF2Sample.data24 = modelByteBuffer.subbuffer(readUnsignedInt, readUnsignedInt2, true);
                        }
                        sF2Sample.startLoop = nextChunk.readUnsignedInt() - readUnsignedInt;
                        sF2Sample.endLoop = nextChunk.readUnsignedInt() - readUnsignedInt;
                        if (sF2Sample.startLoop < 0) {
                            sF2Sample.startLoop = -1;
                        }
                        if (sF2Sample.endLoop < 0) {
                            sF2Sample.endLoop = -1;
                        }
                        sF2Sample.sampleRate = nextChunk.readUnsignedInt();
                        sF2Sample.originalPitch = nextChunk.readUnsignedByte();
                        sF2Sample.pitchCorrection = nextChunk.readByte();
                        sF2Sample.sampleLink = nextChunk.readUnsignedShort();
                        sF2Sample.sampleType = nextChunk.readUnsignedShort();
                        if (i17 != available4 - 1) {
                            this.samples.add(sF2Sample);
                        }
                    }
                } else {
                    throw new RIFFInvalidDataException();
                }
                arrayList3 = arrayList2;
                arrayList4 = arrayList;
            } else if (nextChunk.available() % 4 == 0) {
                int available5 = nextChunk.available() / 4;
                int readUnsignedShort9 = nextChunk.readUnsignedShort();
                int readUnsignedShort10 = nextChunk.readUnsignedShort();
                while (arrayList5.size() < readUnsignedShort9) {
                    arrayList5.add((Object) null);
                }
                while (arrayList6.size() < readUnsignedShort10) {
                    arrayList6.add((Object) null);
                }
                int i18 = available5 - 1;
                int intValue3 = ((Integer) arrayList4.get(0)).intValue();
                int i19 = 0;
                while (i19 < intValue3) {
                    if (i18 != 0) {
                        int readUnsignedShort11 = nextChunk.readUnsignedShort();
                        int readUnsignedShort12 = nextChunk.readUnsignedShort();
                        int i20 = i18;
                        while (arrayList5.size() < readUnsignedShort11) {
                            arrayList5.add((Object) null);
                        }
                        while (arrayList6.size() < readUnsignedShort12) {
                            arrayList6.add((Object) null);
                        }
                        i18 = i20 - 1;
                        i19++;
                    } else {
                        throw new RIFFInvalidDataException();
                    }
                }
                int i21 = i18;
                int i22 = 0;
                while (i22 < arrayList4.size() - 1) {
                    int i23 = i22 + 1;
                    int intValue4 = ((Integer) arrayList4.get(i23)).intValue() - ((Integer) arrayList4.get(i22)).intValue();
                    SF2Instrument sF2Instrument2 = (SF2Instrument) arrayList3.get(i22);
                    int i24 = 0;
                    while (i24 < intValue4) {
                        if (i18 != 0) {
                            int readUnsignedShort13 = nextChunk.readUnsignedShort();
                            ArrayList arrayList11 = arrayList3;
                            int readUnsignedShort14 = nextChunk.readUnsignedShort();
                            ArrayList arrayList12 = arrayList4;
                            SF2InstrumentRegion sF2InstrumentRegion3 = new SF2InstrumentRegion();
                            int i25 = i18;
                            sF2Instrument2.regions.add(sF2InstrumentRegion3);
                            while (arrayList5.size() < readUnsignedShort13) {
                                arrayList5.add(sF2InstrumentRegion3);
                            }
                            while (arrayList6.size() < readUnsignedShort14) {
                                arrayList6.add(sF2InstrumentRegion3);
                            }
                            i18 = i25 - 1;
                            i24++;
                            arrayList3 = arrayList11;
                            arrayList4 = arrayList12;
                        } else {
                            throw new RIFFInvalidDataException();
                        }
                    }
                    int i26 = i18;
                    i22 = i23;
                }
            } else {
                throw new RIFFInvalidDataException();
            }
            arrayList2 = arrayList3;
            arrayList = arrayList4;
            arrayList3 = arrayList2;
            arrayList4 = arrayList;
        }
        for (SF2Layer next : this.layers) {
            SF2LayerRegion sF2LayerRegion4 = null;
            for (SF2LayerRegion next2 : next.regions) {
                if (next2.generators.get(53) != null) {
                    short shortValue = ((Short) next2.generators.get(53)).shortValue();
                    next2.generators.remove(53);
                    next2.sample = this.samples.get(shortValue);
                } else {
                    sF2LayerRegion4 = next2;
                }
            }
            if (sF2LayerRegion4 != null) {
                next.getRegions().remove(sF2LayerRegion4);
                SF2GlobalRegion sF2GlobalRegion = new SF2GlobalRegion();
                sF2GlobalRegion.generators = sF2LayerRegion4.generators;
                sF2GlobalRegion.modulators = sF2LayerRegion4.modulators;
                next.setGlobalZone(sF2GlobalRegion);
            }
        }
        for (SF2Instrument next3 : this.instruments) {
            SF2InstrumentRegion sF2InstrumentRegion4 = null;
            for (SF2InstrumentRegion next4 : next3.regions) {
                if (next4.generators.get(41) != null) {
                    short shortValue2 = ((Short) next4.generators.get(41)).shortValue();
                    next4.generators.remove(41);
                    next4.layer = this.layers.get(shortValue2);
                } else {
                    sF2InstrumentRegion4 = next4;
                }
            }
            if (sF2InstrumentRegion4 != null) {
                next3.getRegions().remove(sF2InstrumentRegion4);
                SF2GlobalRegion sF2GlobalRegion2 = new SF2GlobalRegion();
                sF2GlobalRegion2.generators = sF2InstrumentRegion4.generators;
                sF2GlobalRegion2.modulators = sF2InstrumentRegion4.modulators;
                next3.setGlobalZone(sF2GlobalRegion2);
            }
        }
    }

    public void save(String str) throws IOException {
        writeSoundbank(new RIFFWriter(str, "sfbk"));
    }

    public void save(File file) throws IOException {
        writeSoundbank(new RIFFWriter(file, "sfbk"));
    }

    public void save(OutputStream outputStream) throws IOException {
        writeSoundbank(new RIFFWriter(outputStream, "sfbk"));
    }

    private void writeSoundbank(RIFFWriter rIFFWriter) throws IOException {
        writeInfo(rIFFWriter.writeList("INFO"));
        writeSdtaChunk(rIFFWriter.writeList("sdta"));
        writePdtaChunk(rIFFWriter.writeList("pdta"));
        rIFFWriter.close();
    }

    private void writeInfoStringChunk(RIFFWriter rIFFWriter, String str, String str2) throws IOException {
        if (str2 != null) {
            RIFFWriter writeChunk = rIFFWriter.writeChunk(str);
            writeChunk.writeString(str2);
            int length = str2.getBytes("ascii").length;
            writeChunk.write(0);
            if ((length + 1) % 2 != 0) {
                writeChunk.write(0);
            }
        }
    }

    private void writeInfo(RIFFWriter rIFFWriter) throws IOException {
        if (this.targetEngine == null) {
            this.targetEngine = "EMU8000";
        }
        if (this.name == null) {
            this.name = "";
        }
        RIFFWriter writeChunk = rIFFWriter.writeChunk("ifil");
        writeChunk.writeUnsignedShort(this.major);
        writeChunk.writeUnsignedShort(this.minor);
        writeInfoStringChunk(rIFFWriter, "isng", this.targetEngine);
        writeInfoStringChunk(rIFFWriter, "INAM", this.name);
        writeInfoStringChunk(rIFFWriter, "irom", this.romName);
        if (this.romVersionMajor != -1) {
            RIFFWriter writeChunk2 = rIFFWriter.writeChunk("iver");
            writeChunk2.writeUnsignedShort(this.romVersionMajor);
            writeChunk2.writeUnsignedShort(this.romVersionMinor);
        }
        writeInfoStringChunk(rIFFWriter, "ICRD", this.creationDate);
        writeInfoStringChunk(rIFFWriter, "IENG", this.engineers);
        writeInfoStringChunk(rIFFWriter, "IPRD", this.product);
        writeInfoStringChunk(rIFFWriter, "ICOP", this.copyright);
        writeInfoStringChunk(rIFFWriter, "ICMT", this.comments);
        writeInfoStringChunk(rIFFWriter, "ISFT", this.tools);
        rIFFWriter.close();
    }

    private void writeSdtaChunk(RIFFWriter rIFFWriter) throws IOException {
        byte[] bArr = new byte[32];
        RIFFWriter writeChunk = rIFFWriter.writeChunk("smpl");
        for (SF2Sample dataBuffer : this.samples) {
            dataBuffer.getDataBuffer().writeTo(writeChunk);
            writeChunk.write(bArr);
            writeChunk.write(bArr);
        }
        int i = this.major;
        if (i >= 2) {
            if (i != 2 || this.minor >= 4) {
                for (SF2Sample data24Buffer : this.samples) {
                    if (data24Buffer.getData24Buffer() == null) {
                        return;
                    }
                }
                RIFFWriter writeChunk2 = rIFFWriter.writeChunk("sm24");
                for (SF2Sample data24Buffer2 : this.samples) {
                    data24Buffer2.getData24Buffer().writeTo(writeChunk2);
                    writeChunk.write(bArr);
                }
            }
        }
    }

    private void writeModulators(RIFFWriter rIFFWriter, List<SF2Modulator> list) throws IOException {
        for (SF2Modulator next : list) {
            rIFFWriter.writeUnsignedShort(next.sourceOperator);
            rIFFWriter.writeUnsignedShort(next.destinationOperator);
            rIFFWriter.writeShort(next.amount);
            rIFFWriter.writeUnsignedShort(next.amountSourceOperator);
            rIFFWriter.writeUnsignedShort(next.transportOperator);
        }
    }

    private void writeGenerators(RIFFWriter rIFFWriter, Map<Integer, Short> map) throws IOException {
        Short sh = map.get(43);
        Short sh2 = map.get(44);
        if (sh != null) {
            rIFFWriter.writeUnsignedShort(43);
            rIFFWriter.writeShort(sh.shortValue());
        }
        if (sh2 != null) {
            rIFFWriter.writeUnsignedShort(44);
            rIFFWriter.writeShort(sh2.shortValue());
        }
        for (Map.Entry next : map.entrySet()) {
            if (!(((Integer) next.getKey()).intValue() == 43 || ((Integer) next.getKey()).intValue() == 44)) {
                rIFFWriter.writeUnsignedShort(((Integer) next.getKey()).intValue());
                rIFFWriter.writeShort(((Short) next.getValue()).shortValue());
            }
        }
    }

    private void writePdtaChunk(RIFFWriter rIFFWriter) throws IOException {
        RIFFWriter writeChunk = rIFFWriter.writeChunk("phdr");
        int i = 0;
        int i2 = 0;
        for (SF2Instrument next : this.instruments) {
            writeChunk.writeString(next.name, 20);
            writeChunk.writeUnsignedShort(next.preset);
            writeChunk.writeUnsignedShort(next.bank);
            writeChunk.writeUnsignedShort(i2);
            if (next.getGlobalRegion() != null) {
                i2++;
            }
            i2 += next.getRegions().size();
            writeChunk.writeUnsignedInt(next.library);
            writeChunk.writeUnsignedInt(next.genre);
            writeChunk.writeUnsignedInt(next.morphology);
        }
        writeChunk.writeString("EOP", 20);
        writeChunk.writeUnsignedShort(0);
        writeChunk.writeUnsignedShort(0);
        writeChunk.writeUnsignedShort(i2);
        long j = 0;
        writeChunk.writeUnsignedInt(0);
        writeChunk.writeUnsignedInt(0);
        writeChunk.writeUnsignedInt(0);
        RIFFWriter writeChunk2 = rIFFWriter.writeChunk("pbag");
        int i3 = 0;
        int i4 = 0;
        for (SF2Instrument next2 : this.instruments) {
            if (next2.getGlobalRegion() != null) {
                writeChunk2.writeUnsignedShort(i3);
                writeChunk2.writeUnsignedShort(i4);
                i3 += next2.getGlobalRegion().getGenerators().size();
                i4 += next2.getGlobalRegion().getModulators().size();
            }
            for (SF2InstrumentRegion next3 : next2.getRegions()) {
                writeChunk2.writeUnsignedShort(i3);
                writeChunk2.writeUnsignedShort(i4);
                if (this.layers.indexOf(next3.layer) != -1) {
                    i3++;
                }
                i3 += next3.getGenerators().size();
                i4 += next3.getModulators().size();
            }
        }
        writeChunk2.writeUnsignedShort(i3);
        writeChunk2.writeUnsignedShort(i4);
        RIFFWriter writeChunk3 = rIFFWriter.writeChunk("pmod");
        for (SF2Instrument next4 : this.instruments) {
            if (next4.getGlobalRegion() != null) {
                writeModulators(writeChunk3, next4.getGlobalRegion().getModulators());
            }
            for (SF2InstrumentRegion modulators : next4.getRegions()) {
                writeModulators(writeChunk3, modulators.getModulators());
            }
        }
        writeChunk3.write(new byte[10]);
        RIFFWriter writeChunk4 = rIFFWriter.writeChunk("pgen");
        for (SF2Instrument next5 : this.instruments) {
            if (next5.getGlobalRegion() != null) {
                writeGenerators(writeChunk4, next5.getGlobalRegion().getGenerators());
            }
            for (SF2InstrumentRegion next6 : next5.getRegions()) {
                writeGenerators(writeChunk4, next6.getGenerators());
                int indexOf = this.layers.indexOf(next6.layer);
                if (indexOf != -1) {
                    writeChunk4.writeUnsignedShort(41);
                    writeChunk4.writeShort((short) indexOf);
                }
            }
        }
        writeChunk4.write(new byte[4]);
        RIFFWriter writeChunk5 = rIFFWriter.writeChunk("inst");
        int i5 = 0;
        for (SF2Layer next7 : this.layers) {
            writeChunk5.writeString(next7.name, 20);
            writeChunk5.writeUnsignedShort(i5);
            if (next7.getGlobalRegion() != null) {
                i5++;
            }
            i5 += next7.getRegions().size();
        }
        writeChunk5.writeString("EOI", 20);
        writeChunk5.writeUnsignedShort(i5);
        RIFFWriter writeChunk6 = rIFFWriter.writeChunk("ibag");
        int i6 = 0;
        for (SF2Layer next8 : this.layers) {
            if (next8.getGlobalRegion() != null) {
                writeChunk6.writeUnsignedShort(i);
                writeChunk6.writeUnsignedShort(i6);
                i += next8.getGlobalRegion().getGenerators().size();
                i6 += next8.getGlobalRegion().getModulators().size();
            }
            for (SF2LayerRegion next9 : next8.getRegions()) {
                writeChunk6.writeUnsignedShort(i);
                writeChunk6.writeUnsignedShort(i6);
                if (this.samples.indexOf(next9.sample) != -1) {
                    i++;
                }
                i += next9.getGenerators().size();
                i6 += next9.getModulators().size();
            }
        }
        writeChunk6.writeUnsignedShort(i);
        writeChunk6.writeUnsignedShort(i6);
        RIFFWriter writeChunk7 = rIFFWriter.writeChunk("imod");
        for (SF2Layer next10 : this.layers) {
            if (next10.getGlobalRegion() != null) {
                writeModulators(writeChunk7, next10.getGlobalRegion().getModulators());
            }
            for (SF2LayerRegion modulators2 : next10.getRegions()) {
                writeModulators(writeChunk7, modulators2.getModulators());
            }
        }
        writeChunk7.write(new byte[10]);
        RIFFWriter writeChunk8 = rIFFWriter.writeChunk("igen");
        for (SF2Layer next11 : this.layers) {
            if (next11.getGlobalRegion() != null) {
                writeGenerators(writeChunk8, next11.getGlobalRegion().getGenerators());
            }
            for (SF2LayerRegion next12 : next11.getRegions()) {
                writeGenerators(writeChunk8, next12.getGenerators());
                int indexOf2 = this.samples.indexOf(next12.sample);
                if (indexOf2 != -1) {
                    writeChunk8.writeUnsignedShort(53);
                    writeChunk8.writeShort((short) indexOf2);
                }
            }
        }
        writeChunk8.write(new byte[4]);
        RIFFWriter writeChunk9 = rIFFWriter.writeChunk("shdr");
        for (SF2Sample next13 : this.samples) {
            writeChunk9.writeString(next13.name, 20);
            long capacity = (next13.data.capacity() / 2) + j;
            long j2 = next13.startLoop + j;
            long j3 = next13.endLoop + j;
            if (j2 < j) {
                j2 = j;
            }
            if (j3 > capacity) {
                j3 = capacity;
            }
            writeChunk9.writeUnsignedInt(j);
            writeChunk9.writeUnsignedInt(capacity);
            writeChunk9.writeUnsignedInt(j2);
            writeChunk9.writeUnsignedInt(j3);
            writeChunk9.writeUnsignedInt(next13.sampleRate);
            writeChunk9.writeUnsignedByte(next13.originalPitch);
            writeChunk9.writeByte(next13.pitchCorrection);
            writeChunk9.writeUnsignedShort(next13.sampleLink);
            writeChunk9.writeUnsignedShort(next13.sampleType);
            j = capacity + 32;
        }
        writeChunk9.writeString("EOS", 20);
        writeChunk9.write(new byte[26]);
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.major + "." + this.minor;
    }

    public String getVendor() {
        return this.engineers;
    }

    public String getDescription() {
        return this.comments;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setVendor(String str) {
        this.engineers = str;
    }

    public void setDescription(String str) {
        this.comments = str;
    }

    public SoundbankResource[] getResources() {
        SoundbankResource[] soundbankResourceArr = new SoundbankResource[(this.layers.size() + this.samples.size())];
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.layers.size()) {
            soundbankResourceArr[i3] = (SoundbankResource) this.layers.get(i2);
            i2++;
            i3++;
        }
        while (i < this.samples.size()) {
            soundbankResourceArr[i3] = (SoundbankResource) this.samples.get(i);
            i++;
            i3++;
        }
        return soundbankResourceArr;
    }

    public SF2Instrument[] getInstruments() {
        List<SF2Instrument> list = this.instruments;
        SF2Instrument[] sF2InstrumentArr = (SF2Instrument[]) list.toArray(new SF2Instrument[list.size()]);
        Arrays.sort(sF2InstrumentArr, new ModelInstrumentComparator());
        return sF2InstrumentArr;
    }

    public SF2Layer[] getLayers() {
        List<SF2Layer> list = this.layers;
        return (SF2Layer[]) list.toArray(new SF2Layer[list.size()]);
    }

    public SF2Sample[] getSamples() {
        List<SF2Sample> list = this.samples;
        return (SF2Sample[]) list.toArray(new SF2Sample[list.size()]);
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

    public String getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(String str) {
        this.creationDate = str;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String str) {
        this.product = str;
    }

    public String getRomName() {
        return this.romName;
    }

    public void setRomName(String str) {
        this.romName = str;
    }

    public int getRomVersionMajor() {
        return this.romVersionMajor;
    }

    public void setRomVersionMajor(int i) {
        this.romVersionMajor = i;
    }

    public int getRomVersionMinor() {
        return this.romVersionMinor;
    }

    public void setRomVersionMinor(int i) {
        this.romVersionMinor = i;
    }

    public String getTargetEngine() {
        return this.targetEngine;
    }

    public void setTargetEngine(String str) {
        this.targetEngine = str;
    }

    public String getTools() {
        return this.tools;
    }

    public void setTools(String str) {
        this.tools = str;
    }

    public void addResource(SoundbankResource soundbankResource) {
        if (soundbankResource instanceof SF2Instrument) {
            this.instruments.add((SF2Instrument) soundbankResource);
        }
        if (soundbankResource instanceof SF2Layer) {
            this.layers.add((SF2Layer) soundbankResource);
        }
        if (soundbankResource instanceof SF2Sample) {
            this.samples.add((SF2Sample) soundbankResource);
        }
    }

    public void removeResource(SoundbankResource soundbankResource) {
        if (soundbankResource instanceof SF2Instrument) {
            this.instruments.remove((SF2Instrument) soundbankResource);
        }
        if (soundbankResource instanceof SF2Layer) {
            this.layers.remove((SF2Layer) soundbankResource);
        }
        if (soundbankResource instanceof SF2Sample) {
            this.samples.remove((SF2Sample) soundbankResource);
        }
    }

    public void addInstrument(SF2Instrument sF2Instrument) {
        this.instruments.add(sF2Instrument);
    }

    public void removeInstrument(SF2Instrument sF2Instrument) {
        this.instruments.remove(sF2Instrument);
    }
}
