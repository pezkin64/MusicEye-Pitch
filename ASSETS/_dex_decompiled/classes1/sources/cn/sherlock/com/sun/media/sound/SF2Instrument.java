package cn.sherlock.com.sun.media.sound;

import com.google.common.base.Ascii;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jp.kshoji.javax.sound.midi.Patch;
import jp.kshoji.javax.sound.midi.Soundbank;

public class SF2Instrument extends ModelInstrument {
    protected int bank = 0;
    protected long genre = 0;
    protected SF2GlobalRegion globalregion = null;
    protected long library = 0;
    protected long morphology = 0;
    protected String name = "";
    protected int preset = 0;
    protected List<SF2InstrumentRegion> regions = new ArrayList();

    public Object getData() {
        return null;
    }

    public SF2Instrument() {
        super((Soundbank) null, (Patch) null, (String) null, (Class<?>) null);
    }

    public SF2Instrument(SF2Soundbank sF2Soundbank) {
        super(sF2Soundbank, (Patch) null, (String) null, (Class<?>) null);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Patch getPatch() {
        if (this.bank == 128) {
            return new ModelPatch(0, this.preset, true);
        }
        return new ModelPatch(this.bank << 7, this.preset, false);
    }

    public void setPatch(Patch patch) {
        if (!(patch instanceof ModelPatch) || !((ModelPatch) patch).isPercussion()) {
            this.bank = patch.getBank() >> 7;
            this.preset = patch.getProgram();
            return;
        }
        this.bank = 128;
        this.preset = patch.getProgram();
    }

    public long getGenre() {
        return this.genre;
    }

    public void setGenre(long j) {
        this.genre = j;
    }

    public long getLibrary() {
        return this.library;
    }

    public void setLibrary(long j) {
        this.library = j;
    }

    public long getMorphology() {
        return this.morphology;
    }

    public void setMorphology(long j) {
        this.morphology = j;
    }

    public List<SF2InstrumentRegion> getRegions() {
        return this.regions;
    }

    public SF2GlobalRegion getGlobalRegion() {
        return this.globalregion;
    }

    public void setGlobalZone(SF2GlobalRegion sF2GlobalRegion) {
        this.globalregion = sF2GlobalRegion;
    }

    public String toString() {
        if (this.bank == 128) {
            return "Drumkit: " + this.name + " preset #" + this.preset;
        }
        return "Instrument: " + this.name + " bank #" + this.bank + " preset #" + this.preset;
    }

    public ModelPerformer[] getPerformers() {
        int i;
        byte b;
        byte b2;
        ModelPerformer[] modelPerformerArr;
        byte b3;
        Iterator<SF2LayerRegion> it;
        SF2GlobalRegion sF2GlobalRegion;
        SF2Layer sF2Layer;
        HashMap hashMap;
        int i2;
        short s;
        int i3 = 0;
        for (SF2InstrumentRegion layer : this.regions) {
            i3 += layer.getLayer().getRegions().size();
        }
        ModelPerformer[] modelPerformerArr2 = new ModelPerformer[i3];
        SF2GlobalRegion sF2GlobalRegion2 = this.globalregion;
        Iterator<SF2InstrumentRegion> it2 = this.regions.iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            SF2InstrumentRegion next = it2.next();
            HashMap hashMap2 = new HashMap();
            hashMap2.putAll(next.getGenerators());
            if (sF2GlobalRegion2 != null) {
                hashMap2.putAll(sF2GlobalRegion2.getGenerators());
            }
            SF2Layer layer2 = next.getLayer();
            SF2GlobalRegion globalRegion = layer2.getGlobalRegion();
            Iterator<SF2LayerRegion> it3 = layer2.getRegions().iterator();
            while (it3.hasNext()) {
                SF2LayerRegion next2 = it3.next();
                ModelPerformer modelPerformer = new ModelPerformer();
                if (next2.getSample() != null) {
                    modelPerformer.setName(next2.getSample().getName());
                } else {
                    modelPerformer.setName(layer2.getName());
                }
                int i5 = i4 + 1;
                modelPerformerArr2[i4] = modelPerformer;
                if (next2.contains(57)) {
                    modelPerformer.setExclusiveClass(next2.getInteger(57));
                }
                boolean contains = next2.contains(43);
                byte b4 = Ascii.DEL;
                if (contains) {
                    byte[] bytes = next2.getBytes(43);
                    byte b5 = bytes[0];
                    if (b5 < 0 || b5 <= 0) {
                        b5 = 0;
                    }
                    b = bytes[1];
                    if (b < 0 || b >= Byte.MAX_VALUE) {
                        byte b6 = b5;
                        i = 1;
                        b2 = b6;
                        b = Byte.MAX_VALUE;
                    } else {
                        byte b7 = b5;
                        i = 1;
                        b2 = b7;
                    }
                } else {
                    i = 1;
                    b = Byte.MAX_VALUE;
                    b2 = 0;
                }
                if (next2.contains(44)) {
                    byte[] bytes2 = next2.getBytes(44);
                    byte b8 = bytes2[0];
                    if (b8 < 0 || b8 <= 0) {
                        b8 = 0;
                    }
                    byte b9 = bytes2[i];
                    if (b9 < 0 || b9 >= Byte.MAX_VALUE) {
                        modelPerformerArr = modelPerformerArr2;
                    } else {
                        modelPerformerArr = modelPerformerArr2;
                        b4 = b9;
                    }
                    b3 = b8;
                } else {
                    modelPerformerArr = modelPerformerArr2;
                    b3 = 0;
                }
                if (next.contains(43)) {
                    byte[] bytes3 = next.getBytes(43);
                    byte b10 = bytes3[0];
                    if (b10 > b2) {
                        b2 = b10;
                    }
                    byte b11 = bytes3[i];
                    if (b11 < b) {
                        b = b11;
                    }
                }
                if (next.contains(44)) {
                    byte[] bytes4 = next.getBytes(44);
                    byte b12 = bytes4[0];
                    if (b12 > b3) {
                        b3 = b12;
                    }
                    byte b13 = bytes4[i];
                    if (b13 < b4) {
                        b4 = b13;
                    }
                }
                modelPerformer.setKeyFrom(b2);
                modelPerformer.setKeyTo(b);
                modelPerformer.setVelFrom(b3);
                modelPerformer.setVelTo(b4);
                short s2 = next2.getShort(0);
                short s3 = next2.getShort(i);
                short s4 = next2.getShort(2);
                short s5 = next2.getShort(3);
                int i6 = s2 + (next2.getShort(4) * 32768);
                int i7 = s3 + (next2.getShort(12) * 32768);
                int i8 = (s4 + (next2.getShort(45) * 32768)) - i6;
                int i9 = (s5 + (next2.getShort(50) * 32768)) - i6;
                SF2Sample sample = next2.getSample();
                SF2GlobalRegion sF2GlobalRegion3 = sF2GlobalRegion2;
                Iterator<SF2InstrumentRegion> it4 = it2;
                SF2InstrumentRegion sF2InstrumentRegion = next;
                int i10 = next2.getShort(58) != -1 ? next2.getShort(58) : sample.originalPitch;
                float f = (float) (((-i10) * 100) + sample.pitchCorrection);
                ModelByteBuffer dataBuffer = sample.getDataBuffer();
                int i11 = i10;
                ModelByteBuffer data24Buffer = sample.getData24Buffer();
                if (i6 == 0 && i7 == 0) {
                    hashMap = hashMap2;
                    sF2Layer = layer2;
                    sF2GlobalRegion = globalRegion;
                    it = it3;
                } else {
                    hashMap = hashMap2;
                    sF2Layer = layer2;
                    sF2GlobalRegion = globalRegion;
                    it = it3;
                    dataBuffer = dataBuffer.subbuffer((long) (i6 * 2), dataBuffer.capacity() + ((long) (i7 * 2)));
                    if (data24Buffer != null) {
                        data24Buffer = data24Buffer.subbuffer((long) i6, data24Buffer.capacity() + ((long) i7));
                        dataBuffer = dataBuffer;
                    } else {
                        ModelByteBuffer modelByteBuffer = dataBuffer;
                    }
                }
                ModelByteBufferWavetable modelByteBufferWavetable = new ModelByteBufferWavetable(dataBuffer, sample.getFormat(), f);
                if (data24Buffer != null) {
                    modelByteBufferWavetable.set8BitExtensionBuffer(data24Buffer);
                }
                HashMap hashMap3 = new HashMap();
                if (sF2GlobalRegion != null) {
                    hashMap3.putAll(sF2GlobalRegion.getGenerators());
                }
                hashMap3.putAll(next2.getGenerators());
                for (Map.Entry entry : hashMap.entrySet()) {
                    if (!hashMap3.containsKey(entry.getKey())) {
                        s = next2.getShort(((Integer) entry.getKey()).intValue());
                    } else {
                        s = ((Short) hashMap3.get(entry.getKey())).shortValue();
                    }
                    hashMap3.put((Integer) entry.getKey(), Short.valueOf((short) (s + ((Short) entry.getValue()).shortValue())));
                }
                short generatorValue = getGeneratorValue(hashMap3, 54);
                if ((generatorValue == 1 || generatorValue == 3) && sample.startLoop >= 0 && sample.endLoop > 0) {
                    long j = (long) i8;
                    modelByteBufferWavetable.setLoopStart((float) ((int) (sample.startLoop + j)));
                    modelByteBufferWavetable.setLoopLength((float) ((int) (((sample.endLoop - sample.startLoop) + ((long) i9)) - j)));
                    if (generatorValue == 1) {
                        modelByteBufferWavetable.setLoopType(1);
                    }
                    if (generatorValue == 3) {
                        modelByteBufferWavetable.setLoopType(2);
                    }
                }
                modelPerformer.getOscillators().add(modelByteBufferWavetable);
                short generatorValue2 = getGeneratorValue(hashMap3, 33);
                short generatorValue3 = getGeneratorValue(hashMap3, 34);
                short generatorValue4 = getGeneratorValue(hashMap3, 35);
                short generatorValue5 = getGeneratorValue(hashMap3, 36);
                short generatorValue6 = getGeneratorValue(hashMap3, 37);
                short generatorValue7 = getGeneratorValue(hashMap3, 38);
                short s6 = -12000;
                if (generatorValue4 != -12000) {
                    short generatorValue8 = getGeneratorValue(hashMap3, 39);
                    i2 = i5;
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_KEYNUMBER), (double) ((float) ((-generatorValue8) * 128)), new ModelDestination(ModelDestination.DESTINATION_EG1_HOLD)));
                    generatorValue4 = (short) (generatorValue4 + (generatorValue8 * 60));
                    s6 = -12000;
                } else {
                    i2 = i5;
                }
                if (generatorValue5 != s6) {
                    short generatorValue9 = getGeneratorValue(hashMap3, 40);
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_KEYNUMBER), (double) ((float) ((-generatorValue9) * 128)), new ModelDestination(ModelDestination.DESTINATION_EG1_DECAY)));
                    generatorValue5 = (short) (generatorValue5 + (generatorValue9 * 60));
                }
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG1_DELAY, generatorValue2);
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG1_ATTACK, generatorValue3);
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG1_HOLD, generatorValue4);
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG1_DECAY, generatorValue5);
                short s7 = (short) (1000 - generatorValue6);
                if (s7 < 0) {
                    s7 = 0;
                }
                if (s7 > 1000) {
                    s7 = 1000;
                }
                addValue(modelPerformer, ModelDestination.DESTINATION_EG1_SUSTAIN, s7);
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG1_RELEASE, generatorValue7);
                if (!(getGeneratorValue(hashMap3, 11) == 0 && getGeneratorValue(hashMap3, 7) == 0)) {
                    short generatorValue10 = getGeneratorValue(hashMap3, 25);
                    short generatorValue11 = getGeneratorValue(hashMap3, 26);
                    short generatorValue12 = getGeneratorValue(hashMap3, 27);
                    short generatorValue13 = getGeneratorValue(hashMap3, 28);
                    short generatorValue14 = getGeneratorValue(hashMap3, 29);
                    short generatorValue15 = getGeneratorValue(hashMap3, 30);
                    short s8 = -12000;
                    if (generatorValue12 != -12000) {
                        short generatorValue16 = getGeneratorValue(hashMap3, 31);
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_KEYNUMBER), (double) ((float) ((-generatorValue16) * 128)), new ModelDestination(ModelDestination.DESTINATION_EG2_HOLD)));
                        generatorValue12 = (short) (generatorValue12 + (generatorValue16 * 60));
                        s8 = -12000;
                    }
                    if (generatorValue13 != s8) {
                        short generatorValue17 = getGeneratorValue(hashMap3, 32);
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_KEYNUMBER), (double) ((float) ((-generatorValue17) * 128)), new ModelDestination(ModelDestination.DESTINATION_EG2_DECAY)));
                        generatorValue13 = (short) (generatorValue13 + (generatorValue17 * 60));
                    }
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG2_DELAY, generatorValue10);
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG2_ATTACK, generatorValue11);
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG2_HOLD, generatorValue12);
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG2_DECAY, generatorValue13);
                    if (generatorValue14 < 0) {
                        generatorValue14 = 0;
                    }
                    if (generatorValue14 > 1000) {
                        generatorValue14 = 1000;
                    }
                    addValue(modelPerformer, ModelDestination.DESTINATION_EG2_SUSTAIN, (double) (1000 - generatorValue14));
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_EG2_RELEASE, generatorValue15);
                    if (getGeneratorValue(hashMap3, 11) != 0) {
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_EG2), (double) getGeneratorValue(hashMap3, 11), new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ)));
                    }
                    if (getGeneratorValue(hashMap3, 7) != 0) {
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_EG2), (double) getGeneratorValue(hashMap3, 7), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                    }
                }
                if (!(getGeneratorValue(hashMap3, 10) == 0 && getGeneratorValue(hashMap3, 5) == 0 && getGeneratorValue(hashMap3, 13) == 0)) {
                    short generatorValue18 = getGeneratorValue(hashMap3, 22);
                    addTimecentValue(modelPerformer, ModelDestination.DESTINATION_LFO1_DELAY, getGeneratorValue(hashMap3, 21));
                    addValue(modelPerformer, ModelDestination.DESTINATION_LFO1_FREQ, generatorValue18);
                }
                short generatorValue19 = getGeneratorValue(hashMap3, 24);
                addTimecentValue(modelPerformer, ModelDestination.DESTINATION_LFO2_DELAY, getGeneratorValue(hashMap3, 23));
                addValue(modelPerformer, ModelDestination.DESTINATION_LFO2_FREQ, generatorValue19);
                if (getGeneratorValue(hashMap3, 6) != 0) {
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO2, false, true), (double) getGeneratorValue(hashMap3, 6), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                }
                if (getGeneratorValue(hashMap3, 10) != 0) {
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, true), (double) getGeneratorValue(hashMap3, 10), new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ)));
                }
                if (getGeneratorValue(hashMap3, 5) != 0) {
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, true), (double) getGeneratorValue(hashMap3, 5), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                }
                if (getGeneratorValue(hashMap3, 13) != 0) {
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO1, false, true), (double) getGeneratorValue(hashMap3, 13), new ModelDestination(ModelDestination.DESTINATION_GAIN)));
                }
                if (next2.getShort(46) != -1) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_KEYNUMBER, ((double) next2.getShort(46)) / 128.0d);
                }
                if (next2.getShort(47) != -1) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_VELOCITY, ((double) next2.getShort(47)) / 128.0d);
                }
                if (getGeneratorValue(hashMap3, 8) < 13500) {
                    short generatorValue20 = getGeneratorValue(hashMap3, 8);
                    short generatorValue21 = getGeneratorValue(hashMap3, 9);
                    addValue(modelPerformer, ModelDestination.DESTINATION_FILTER_FREQ, generatorValue20);
                    addValue(modelPerformer, ModelDestination.DESTINATION_FILTER_Q, generatorValue21);
                }
                int generatorValue22 = (getGeneratorValue(hashMap3, 51) * 100) + getGeneratorValue(hashMap3, 52);
                if (generatorValue22 != 0) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_PITCH, (short) generatorValue22);
                }
                if (getGeneratorValue(hashMap3, 17) != 0) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_PAN, getGeneratorValue(hashMap3, 17));
                }
                if (getGeneratorValue(hashMap3, 48) != 0) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_GAIN, (double) (((float) getGeneratorValue(hashMap3, 48)) * -0.376287f));
                }
                if (getGeneratorValue(hashMap3, 15) != 0) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_CHORUS, getGeneratorValue(hashMap3, 15));
                }
                if (getGeneratorValue(hashMap3, 16) != 0) {
                    addValue(modelPerformer, ModelDestination.DESTINATION_REVERB, getGeneratorValue(hashMap3, 16));
                }
                if (getGeneratorValue(hashMap3, 56) != 100) {
                    short generatorValue23 = getGeneratorValue(hashMap3, 56);
                    if (generatorValue23 == 0) {
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock((ModelSource) null, (double) (i11 * 100), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                    } else {
                        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock((ModelSource) null, (double) ((100 - generatorValue23) * i11), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                    }
                    modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_KEYNUMBER), (double) (generatorValue23 * 128), new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                }
                modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_NOTEON_VELOCITY, (ModelTransform) new ModelTransform() {
                    public double transform(double d) {
                        if (d < 0.5d) {
                            return 1.0d - (d * 2.0d);
                        }
                        return 0.0d;
                    }
                }), -2400.0d, new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ)));
                modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(new ModelSource(ModelSource.SOURCE_LFO2, false, true, 0), new ModelSource(new ModelIdentifier("midi_cc", "1", 0), false, false, 0), 50.0d, new ModelDestination(ModelDestination.DESTINATION_PITCH)));
                if (sF2Layer.getGlobalRegion() != null) {
                    for (SF2Modulator convertModulator : sF2Layer.getGlobalRegion().getModulators()) {
                        convertModulator(modelPerformer, convertModulator);
                    }
                }
                for (SF2Modulator convertModulator2 : next2.getModulators()) {
                    convertModulator(modelPerformer, convertModulator2);
                }
                if (sF2GlobalRegion3 != null) {
                    for (SF2Modulator convertModulator3 : sF2GlobalRegion3.getModulators()) {
                        convertModulator(modelPerformer, convertModulator3);
                    }
                }
                for (SF2Modulator convertModulator4 : sF2InstrumentRegion.getModulators()) {
                    convertModulator(modelPerformer, convertModulator4);
                }
                modelPerformerArr2 = modelPerformerArr;
                sF2GlobalRegion2 = sF2GlobalRegion3;
                it2 = it4;
                next = sF2InstrumentRegion;
                hashMap2 = hashMap;
                layer2 = sF2Layer;
                i4 = i2;
                globalRegion = sF2GlobalRegion;
                it3 = it;
            }
        }
        return modelPerformerArr2;
    }

    private void convertModulator(ModelPerformer modelPerformer, SF2Modulator sF2Modulator) {
        ModelSource convertSource = convertSource(sF2Modulator.getSourceOperator());
        ModelSource convertSource2 = convertSource(sF2Modulator.getAmountSourceOperator());
        if (convertSource == null && sF2Modulator.getSourceOperator() != 0) {
            return;
        }
        if (convertSource2 != null || sF2Modulator.getAmountSourceOperator() == 0) {
            ModelSource[] modelSourceArr = new ModelSource[1];
            double[] dArr = {1.0d};
            ModelDestination convertDestination = convertDestination(sF2Modulator.getDestinationOperator(), dArr, modelSourceArr);
            double amount = ((double) sF2Modulator.getAmount()) * dArr[0];
            if (convertDestination != null) {
                if (sF2Modulator.getTransportOperator() == 2) {
                    ((ModelStandardTransform) convertDestination.getTransform()).setTransform(4);
                }
                ModelConnectionBlock modelConnectionBlock = new ModelConnectionBlock(convertSource, convertSource2, amount, convertDestination);
                ModelSource modelSource = modelSourceArr[0];
                if (modelSource != null) {
                    modelConnectionBlock.addSource(modelSource);
                }
                modelPerformer.getConnectionBlocks().add(modelConnectionBlock);
            }
        }
    }

    private static ModelSource convertSource(int i) {
        ModelIdentifier modelIdentifier;
        if (i == 0) {
            return null;
        }
        int i2 = i & 127;
        if ((i & 128) != 0) {
            modelIdentifier = new ModelIdentifier("midi_cc", Integer.toString(i2));
        } else {
            modelIdentifier = i2 == 2 ? ModelSource.SOURCE_NOTEON_VELOCITY : null;
            if (i2 == 3) {
                modelIdentifier = ModelSource.SOURCE_NOTEON_KEYNUMBER;
            }
            if (i2 == 10) {
                modelIdentifier = ModelSource.SOURCE_MIDI_POLY_PRESSURE;
            }
            if (i2 == 13) {
                modelIdentifier = ModelSource.SOURCE_MIDI_CHANNEL_PRESSURE;
            }
            if (i2 == 14) {
                modelIdentifier = ModelSource.SOURCE_MIDI_PITCH;
            }
            if (i2 == 16) {
                modelIdentifier = new ModelIdentifier("midi_rpn", "0");
            }
        }
        if (modelIdentifier == null) {
            return null;
        }
        ModelSource modelSource = new ModelSource(modelIdentifier);
        ModelStandardTransform modelStandardTransform = (ModelStandardTransform) modelSource.getTransform();
        if ((i & 256) != 0) {
            modelStandardTransform.setDirection(true);
        } else {
            modelStandardTransform.setDirection(false);
        }
        if ((i & 512) != 0) {
            modelStandardTransform.setPolarity(true);
        } else {
            modelStandardTransform.setPolarity(false);
        }
        if ((i & 1024) != 0) {
            modelStandardTransform.setTransform(1);
        }
        if ((i & 2048) != 0) {
            modelStandardTransform.setTransform(2);
        }
        if ((i & SF2Modulator.SOURCE_TYPE_SWITCH) != 0) {
            modelStandardTransform.setTransform(3);
        }
        return modelSource;
    }

    protected static ModelDestination convertDestination(int i, double[] dArr, ModelSource[] modelSourceArr) {
        ModelIdentifier modelIdentifier;
        switch (i) {
            case 5:
                modelIdentifier = ModelDestination.DESTINATION_PITCH;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_LFO1, false, true);
                break;
            case 6:
                modelIdentifier = ModelDestination.DESTINATION_PITCH;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_LFO2, false, true);
                break;
            case 7:
                modelIdentifier = ModelDestination.DESTINATION_PITCH;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_EG2, false, true);
                break;
            case 8:
                modelIdentifier = ModelDestination.DESTINATION_FILTER_FREQ;
                break;
            case 9:
                modelIdentifier = ModelDestination.DESTINATION_FILTER_Q;
                break;
            case 10:
                modelIdentifier = ModelDestination.DESTINATION_FILTER_FREQ;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_LFO1, false, true);
                break;
            case 11:
                modelIdentifier = ModelDestination.DESTINATION_FILTER_FREQ;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_EG2, false, true);
                break;
            case 13:
                modelIdentifier = ModelDestination.DESTINATION_GAIN;
                dArr[0] = -0.3762870132923126d;
                modelSourceArr[0] = new ModelSource(ModelSource.SOURCE_LFO1, false, true);
                break;
            case 15:
                modelIdentifier = ModelDestination.DESTINATION_CHORUS;
                break;
            case 16:
                modelIdentifier = ModelDestination.DESTINATION_REVERB;
                break;
            case 17:
                modelIdentifier = ModelDestination.DESTINATION_PAN;
                break;
            case 21:
                modelIdentifier = ModelDestination.DESTINATION_LFO1_DELAY;
                break;
            case 22:
                modelIdentifier = ModelDestination.DESTINATION_LFO1_FREQ;
                break;
            case 23:
                modelIdentifier = ModelDestination.DESTINATION_LFO2_DELAY;
                break;
            case 24:
                modelIdentifier = ModelDestination.DESTINATION_LFO2_FREQ;
                break;
            case 25:
                modelIdentifier = ModelDestination.DESTINATION_EG2_DELAY;
                break;
            case 26:
                modelIdentifier = ModelDestination.DESTINATION_EG2_ATTACK;
                break;
            case 27:
                modelIdentifier = ModelDestination.DESTINATION_EG2_HOLD;
                break;
            case 28:
                modelIdentifier = ModelDestination.DESTINATION_EG2_DECAY;
                break;
            case 29:
                modelIdentifier = ModelDestination.DESTINATION_EG2_SUSTAIN;
                dArr[0] = -1.0d;
                break;
            case 30:
                modelIdentifier = ModelDestination.DESTINATION_EG2_RELEASE;
                break;
            case 33:
                modelIdentifier = ModelDestination.DESTINATION_EG1_DELAY;
                break;
            case 34:
                modelIdentifier = ModelDestination.DESTINATION_EG1_ATTACK;
                break;
            case 35:
                modelIdentifier = ModelDestination.DESTINATION_EG1_HOLD;
                break;
            case 36:
                modelIdentifier = ModelDestination.DESTINATION_EG1_DECAY;
                break;
            case 37:
                modelIdentifier = ModelDestination.DESTINATION_EG1_SUSTAIN;
                dArr[0] = -1.0d;
                break;
            case 38:
                modelIdentifier = ModelDestination.DESTINATION_EG1_RELEASE;
                break;
            case 46:
                modelIdentifier = ModelDestination.DESTINATION_KEYNUMBER;
                break;
            case 47:
                modelIdentifier = ModelDestination.DESTINATION_VELOCITY;
                break;
            case 48:
                modelIdentifier = ModelDestination.DESTINATION_GAIN;
                dArr[0] = -0.3762870132923126d;
                break;
            case 51:
                dArr[0] = 100.0d;
                modelIdentifier = ModelDestination.DESTINATION_PITCH;
                break;
            case 52:
                modelIdentifier = ModelDestination.DESTINATION_PITCH;
                break;
            default:
                modelIdentifier = null;
                break;
        }
        if (modelIdentifier != null) {
            return new ModelDestination(modelIdentifier);
        }
        return null;
    }

    private void addTimecentValue(ModelPerformer modelPerformer, ModelIdentifier modelIdentifier, short s) {
        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(s == -12000 ? Double.NEGATIVE_INFINITY : (double) s, new ModelDestination(modelIdentifier)));
    }

    private void addValue(ModelPerformer modelPerformer, ModelIdentifier modelIdentifier, short s) {
        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock((double) s, new ModelDestination(modelIdentifier)));
    }

    private void addValue(ModelPerformer modelPerformer, ModelIdentifier modelIdentifier, double d) {
        modelPerformer.getConnectionBlocks().add(new ModelConnectionBlock(d, new ModelDestination(modelIdentifier)));
    }

    private short getGeneratorValue(Map<Integer, Short> map, int i) {
        if (map.containsKey(Integer.valueOf(i))) {
            return map.get(Integer.valueOf(i)).shortValue();
        }
        return SF2Region.getDefaultValue(i);
    }
}
