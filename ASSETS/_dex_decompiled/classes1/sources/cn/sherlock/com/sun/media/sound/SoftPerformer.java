package cn.sherlock.com.sun.media.sound;

import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SoftPerformer {
    static ModelConnectionBlock[] defaultconnections;
    private static KeySortComparator keySortComparator = new KeySortComparator();
    public ModelConnectionBlock[] connections;
    public int[] ctrl_connections;
    private List<Integer> ctrl_connections_list = new ArrayList();
    public int exclusiveClass = 0;
    public boolean forcedKeynumber = false;
    public boolean forcedVelocity = false;
    public int keyFrom = 0;
    public int keyTo = 127;
    public int[][] midi_connections;
    public int[][] midi_ctrl_connections;
    public Map<Integer, int[]> midi_nrpn_connections = new HashMap();
    public Map<Integer, int[]> midi_rpn_connections = new HashMap();
    public ModelOscillator[] oscillators;
    public ModelPerformer performer;
    public boolean selfNonExclusive = false;
    public int velFrom = 0;
    public int velTo = 127;

    static {
        ModelConnectionBlock[] modelConnectionBlockArr = new ModelConnectionBlock[42];
        defaultconnections = modelConnectionBlockArr;
        modelConnectionBlockArr[0] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("noteon", "on", 0), false, false, 0), 1.0d, new ModelDestination(new ModelIdentifier("eg", "on", 0)));
        defaultconnections[1] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("noteon", "on", 0), false, false, 0), 1.0d, new ModelDestination(new ModelIdentifier("eg", "on", 1)));
        defaultconnections[2] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("eg", "active", 0), false, false, 0), 1.0d, new ModelDestination(new ModelIdentifier("mixer", "active", 0)));
        defaultconnections[3] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("eg", 0), true, false, 0), -960.0d, new ModelDestination(new ModelIdentifier("mixer", "gain")));
        defaultconnections[4] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("noteon", "velocity"), true, false, 1), -960.0d, new ModelDestination(new ModelIdentifier("mixer", "gain")));
        defaultconnections[5] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi", "pitch"), false, true, 0), new ModelSource(new ModelIdentifier("midi_rpn", "0"), (ModelTransform) new ModelTransform() {
            public double transform(double d) {
                int i = (int) (d * 16384.0d);
                return (double) (((i >> 7) * 100) + (i & 127));
            }
        }), new ModelDestination(new ModelIdentifier("osc", "pitch")));
        String str = "gain";
        defaultconnections[6] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("noteon", "keynumber"), false, false, 0), 12800.0d, new ModelDestination(new ModelIdentifier("osc", "pitch")));
        defaultconnections[7] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "7"), true, false, 1), -960.0d, new ModelDestination(new ModelIdentifier("mixer", str)));
        defaultconnections[8] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "8"), false, false, 0), 1000.0d, new ModelDestination(new ModelIdentifier("mixer", "balance")));
        defaultconnections[9] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "10"), false, false, 0), 1000.0d, new ModelDestination(new ModelIdentifier("mixer", "pan")));
        defaultconnections[10] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "11"), true, false, 1), -960.0d, new ModelDestination(new ModelIdentifier("mixer", str)));
        defaultconnections[11] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "91"), false, false, 0), 1000.0d, new ModelDestination(new ModelIdentifier("mixer", "reverb")));
        defaultconnections[12] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "93"), false, false, 0), 1000.0d, new ModelDestination(new ModelIdentifier("mixer", "chorus")));
        defaultconnections[13] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "71"), false, true, 0), 200.0d, new ModelDestination(new ModelIdentifier("filter", "q")));
        defaultconnections[14] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "74"), false, true, 0), 9600.0d, new ModelDestination(new ModelIdentifier("filter", "freq")));
        defaultconnections[15] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "72"), false, true, 0), 6000.0d, new ModelDestination(new ModelIdentifier("eg", "release2")));
        defaultconnections[16] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "73"), false, true, 0), 2000.0d, new ModelDestination(new ModelIdentifier("eg", "attack2")));
        defaultconnections[17] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "75"), false, true, 0), 6000.0d, new ModelDestination(new ModelIdentifier("eg", "decay2")));
        defaultconnections[18] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "67"), false, false, 3), -50.0d, new ModelDestination(ModelDestination.DESTINATION_GAIN));
        defaultconnections[19] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_cc", "67"), false, false, 3), -2400.0d, new ModelDestination(ModelDestination.DESTINATION_FILTER_FREQ));
        defaultconnections[20] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_rpn", "1"), false, true, 0), 100.0d, new ModelDestination(new ModelIdentifier("osc", "pitch")));
        defaultconnections[21] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("midi_rpn", ExifInterface.GPS_MEASUREMENT_2D), false, true, 0), 12800.0d, new ModelDestination(new ModelIdentifier("osc", "pitch")));
        defaultconnections[22] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("master", "fine_tuning"), false, true, 0), 100.0d, new ModelDestination(new ModelIdentifier("osc", "pitch")));
        defaultconnections[23] = new ModelConnectionBlock(new ModelSource(new ModelIdentifier("master", "coarse_tuning"), false, true, 0), 12800.0d, new ModelDestination(new ModelIdentifier("osc", "pitch")));
        defaultconnections[24] = new ModelConnectionBlock(13500.0d, new ModelDestination(new ModelIdentifier("filter", "freq", 0)));
        defaultconnections[25] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "delay", 0)));
        defaultconnections[26] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "attack", 0)));
        defaultconnections[27] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "hold", 0)));
        defaultconnections[28] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "decay", 0)));
        defaultconnections[29] = new ModelConnectionBlock(1000.0d, new ModelDestination(new ModelIdentifier("eg", "sustain", 0)));
        defaultconnections[30] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "release", 0)));
        defaultconnections[31] = new ModelConnectionBlock((Math.log(0.015d) * 1200.0d) / Math.log(2.0d), new ModelDestination(new ModelIdentifier("eg", "shutdown", 0)));
        defaultconnections[32] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "delay", 1)));
        defaultconnections[33] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "attack", 1)));
        defaultconnections[34] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "hold", 1)));
        defaultconnections[35] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "decay", 1)));
        defaultconnections[36] = new ModelConnectionBlock(1000.0d, new ModelDestination(new ModelIdentifier("eg", "sustain", 1)));
        defaultconnections[37] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("eg", "release", 1)));
        defaultconnections[38] = new ModelConnectionBlock(-8.51318d, new ModelDestination(new ModelIdentifier("lfo", "freq", 0)));
        defaultconnections[39] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("lfo", "delay", 0)));
        defaultconnections[40] = new ModelConnectionBlock(-8.51318d, new ModelDestination(new ModelIdentifier("lfo", "freq", 1)));
        defaultconnections[41] = new ModelConnectionBlock(Double.NEGATIVE_INFINITY, new ModelDestination(new ModelIdentifier("lfo", "delay", 1)));
    }

    private static class KeySortComparator implements Comparator<ModelSource> {
        private KeySortComparator() {
        }

        public int compare(ModelSource modelSource, ModelSource modelSource2) {
            return modelSource.getIdentifier().toString().compareTo(modelSource2.getIdentifier().toString());
        }
    }

    private String extractKeys(ModelConnectionBlock modelConnectionBlock) {
        StringBuffer stringBuffer = new StringBuffer();
        if (modelConnectionBlock.getSources() != null) {
            stringBuffer.append("[");
            ModelSource[] sources = modelConnectionBlock.getSources();
            ModelSource[] modelSourceArr = new ModelSource[sources.length];
            for (int i = 0; i < sources.length; i++) {
                modelSourceArr[i] = sources[i];
            }
            Arrays.sort(modelSourceArr, keySortComparator);
            for (ModelSource identifier : sources) {
                stringBuffer.append(identifier.getIdentifier());
                stringBuffer.append(";");
            }
            stringBuffer.append("]");
        }
        stringBuffer.append(";");
        if (modelConnectionBlock.getDestination() != null) {
            stringBuffer.append(modelConnectionBlock.getDestination().getIdentifier());
        }
        stringBuffer.append(";");
        return stringBuffer.toString();
    }

    private void processSource(ModelSource modelSource, int i) {
        String object = modelSource.getIdentifier().getObject();
        if (object.equals("midi_cc")) {
            processMidiControlSource(modelSource, i);
        } else if (object.equals("midi_rpn")) {
            processMidiRpnSource(modelSource, i);
        } else if (object.equals("midi_nrpn")) {
            processMidiNrpnSource(modelSource, i);
        } else if (object.equals("midi")) {
            processMidiSource(modelSource, i);
        } else if (object.equals("noteon")) {
            processNoteOnSource(modelSource, i);
        } else if (!object.equals("osc") && !object.equals("mixer")) {
            this.ctrl_connections_list.add(Integer.valueOf(i));
        }
    }

    private void processMidiControlSource(ModelSource modelSource, int i) {
        String variable = modelSource.getIdentifier().getVariable();
        if (variable != null) {
            int parseInt = Integer.parseInt(variable);
            int[][] iArr = this.midi_ctrl_connections;
            int[] iArr2 = iArr[parseInt];
            if (iArr2 == null) {
                iArr[parseInt] = new int[]{i};
                return;
            }
            int length = iArr2.length;
            int[] iArr3 = new int[(length + 1)];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                iArr3[i2] = iArr2[i2];
            }
            iArr3[length] = i;
            this.midi_ctrl_connections[parseInt] = iArr3;
        }
    }

    private void processNoteOnSource(ModelSource modelSource, int i) {
        String variable = modelSource.getIdentifier().getVariable();
        char c = variable.equals("on") ? (char) 3 : 65535;
        if (variable.equals("keynumber")) {
            c = 4;
        }
        if (c != 65535) {
            int[][] iArr = this.midi_connections;
            int[] iArr2 = iArr[c];
            if (iArr2 == null) {
                iArr[c] = new int[]{i};
                return;
            }
            int length = iArr2.length;
            int[] iArr3 = new int[(length + 1)];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                iArr3[i2] = iArr2[i2];
            }
            iArr3[length] = i;
            this.midi_connections[c] = iArr3;
        }
    }

    private void processMidiSource(ModelSource modelSource, int i) {
        String variable = modelSource.getIdentifier().getVariable();
        char c = variable.equals("pitch") ? (char) 0 : 65535;
        if (variable.equals("channel_pressure")) {
            c = 1;
        }
        if (variable.equals("poly_pressure")) {
            c = 2;
        }
        if (c != 65535) {
            int[][] iArr = this.midi_connections;
            int[] iArr2 = iArr[c];
            if (iArr2 == null) {
                iArr[c] = new int[]{i};
                return;
            }
            int length = iArr2.length;
            int[] iArr3 = new int[(length + 1)];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                iArr3[i2] = iArr2[i2];
            }
            iArr3[length] = i;
            this.midi_connections[c] = iArr3;
        }
    }

    private void processMidiRpnSource(ModelSource modelSource, int i) {
        String variable = modelSource.getIdentifier().getVariable();
        if (variable != null) {
            int parseInt = Integer.parseInt(variable);
            if (this.midi_rpn_connections.get(Integer.valueOf(parseInt)) == null) {
                this.midi_rpn_connections.put(Integer.valueOf(parseInt), new int[]{i});
                return;
            }
            int[] iArr = this.midi_rpn_connections.get(Integer.valueOf(parseInt));
            int length = iArr.length;
            int[] iArr2 = new int[(length + 1)];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[i2];
            }
            iArr2[length] = i;
            this.midi_rpn_connections.put(Integer.valueOf(parseInt), iArr2);
        }
    }

    private void processMidiNrpnSource(ModelSource modelSource, int i) {
        String variable = modelSource.getIdentifier().getVariable();
        if (variable != null) {
            int parseInt = Integer.parseInt(variable);
            if (this.midi_nrpn_connections.get(Integer.valueOf(parseInt)) == null) {
                this.midi_nrpn_connections.put(Integer.valueOf(parseInt), new int[]{i});
                return;
            }
            int[] iArr = this.midi_nrpn_connections.get(Integer.valueOf(parseInt));
            int length = iArr.length;
            int[] iArr2 = new int[(length + 1)];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[i2];
            }
            iArr2[length] = i;
            this.midi_nrpn_connections.put(Integer.valueOf(parseInt), iArr2);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: int[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v11, resolved type: int[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v1, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x03f6  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x00f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public SoftPerformer(cn.sherlock.com.sun.media.sound.ModelPerformer r26) {
        /*
            r25 = this;
            r0 = r25
            r0.<init>()
            r1 = 0
            r0.keyFrom = r1
            r2 = 127(0x7f, float:1.78E-43)
            r0.keyTo = r2
            r0.velFrom = r1
            r0.velTo = r2
            r0.exclusiveClass = r1
            r0.selfNonExclusive = r1
            r0.forcedVelocity = r1
            r0.forcedKeynumber = r1
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.midi_rpn_connections = r2
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            r0.midi_nrpn_connections = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r0.ctrl_connections_list = r2
            r2 = r26
            r0.performer = r2
            int r3 = r2.getKeyFrom()
            r0.keyFrom = r3
            int r3 = r2.getKeyTo()
            r0.keyTo = r3
            int r3 = r2.getVelFrom()
            r0.velFrom = r3
            int r3 = r2.getVelTo()
            r0.velTo = r3
            int r3 = r2.getExclusiveClass()
            r0.exclusiveClass = r3
            boolean r3 = r2.isSelfNonExclusive()
            r0.selfNonExclusive = r3
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.List r5 = r2.getConnectionBlocks()
            r4.addAll(r5)
            boolean r5 = r2.isDefaultConnectionsEnabled()
            r7 = 1
            if (r5 == 0) goto L_0x034b
            r5 = r1
            r8 = r5
        L_0x006f:
            int r9 = r4.size()
            java.lang.String r12 = "5"
            java.lang.String r13 = "midi_rpn"
            java.lang.String r14 = "1"
            java.lang.String r15 = "midi_cc"
            if (r5 >= r9) goto L_0x00f5
            java.lang.Object r9 = r4.get(r5)
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r9 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r9
            r16 = 4643211215818981376(0x4070000000000000, double:256.0)
            cn.sherlock.com.sun.media.sound.ModelSource[] r10 = r9.getSources()
            cn.sherlock.com.sun.media.sound.ModelDestination r11 = r9.getDestination()
            if (r11 == 0) goto L_0x00c0
            if (r10 == 0) goto L_0x00c0
            int r11 = r10.length
            if (r11 <= r7) goto L_0x00c0
            r11 = r1
            r18 = 0
        L_0x0097:
            int r6 = r10.length
            if (r11 >= r6) goto L_0x00c2
            r6 = r10[r11]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = r6.getIdentifier()
            java.lang.String r6 = r6.getObject()
            boolean r6 = r6.equals(r15)
            if (r6 == 0) goto L_0x00bd
            r6 = r10[r11]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = r6.getIdentifier()
            java.lang.String r6 = r6.getVariable()
            boolean r6 = r6.equals(r14)
            if (r6 == 0) goto L_0x00bd
            r6 = r7
            r8 = r6
            goto L_0x00c3
        L_0x00bd:
            int r11 = r11 + 1
            goto L_0x0097
        L_0x00c0:
            r18 = 0
        L_0x00c2:
            r6 = r1
        L_0x00c3:
            if (r6 == 0) goto L_0x00f1
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r6 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            r6.<init>()
            cn.sherlock.com.sun.media.sound.ModelSource[] r10 = r9.getSources()
            r6.setSources(r10)
            cn.sherlock.com.sun.media.sound.ModelDestination r10 = r9.getDestination()
            r6.setDestination(r10)
            cn.sherlock.com.sun.media.sound.ModelSource r10 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r11 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r11.<init>((java.lang.String) r13, (java.lang.String) r12)
            r10.<init>(r11)
            r6.addSource(r10)
            double r9 = r9.getScale()
            double r9 = r9 * r16
            r6.setScale(r9)
            r4.set(r5, r6)
        L_0x00f1:
            int r5 = r5 + 1
            goto L_0x006f
        L_0x00f5:
            r16 = 4643211215818981376(0x4070000000000000, double:256.0)
            r18 = 0
            if (r8 != 0) goto L_0x013b
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r19 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            cn.sherlock.com.sun.media.sound.ModelSource r5 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = cn.sherlock.com.sun.media.sound.ModelSource.SOURCE_LFO1
            r5.<init>(r6, r1, r7, r1)
            cn.sherlock.com.sun.media.sound.ModelSource r6 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r8 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r8.<init>(r15, r14, r1)
            r6.<init>(r8, r1, r1, r1)
            cn.sherlock.com.sun.media.sound.ModelDestination r8 = new cn.sherlock.com.sun.media.sound.ModelDestination
            cn.sherlock.com.sun.media.sound.ModelIdentifier r9 = cn.sherlock.com.sun.media.sound.ModelDestination.DESTINATION_PITCH
            r8.<init>(r9)
            r22 = 4632233691727265792(0x4049000000000000, double:50.0)
            r20 = r5
            r21 = r6
            r24 = r8
            r19.<init>(r20, r21, r22, r24)
            r5 = r19
            cn.sherlock.com.sun.media.sound.ModelSource r6 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r8 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r8.<init>((java.lang.String) r13, (java.lang.String) r12)
            r6.<init>(r8)
            r5.addSource(r6)
            double r8 = r5.getScale()
            double r8 = r8 * r16
            r5.setScale(r8)
            r4.add(r5)
        L_0x013b:
            java.util.Iterator r5 = r4.iterator()
            r8 = r1
            r9 = r8
            r10 = r9
            r6 = r18
        L_0x0144:
            boolean r11 = r5.hasNext()
            java.lang.String r12 = "poly_pressure"
            java.lang.String r13 = "channel_pressure"
            r16 = r1
            java.lang.String r1 = "midi"
            if (r11 == 0) goto L_0x01b7
            java.lang.Object r11 = r5.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r11 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r11
            cn.sherlock.com.sun.media.sound.ModelSource[] r7 = r11.getSources()
            cn.sherlock.com.sun.media.sound.ModelDestination r19 = r11.getDestination()
            if (r19 == 0) goto L_0x01ad
            if (r7 == 0) goto L_0x01ad
            r19 = r4
            r2 = r16
        L_0x0168:
            int r4 = r7.length
            if (r2 >= r4) goto L_0x01af
            r4 = r7[r2]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r4 = r4.getIdentifier()
            r20 = r2
            java.lang.String r2 = r4.getObject()
            boolean r2 = r2.equals(r15)
            if (r2 == 0) goto L_0x018a
            java.lang.String r2 = r4.getVariable()
            boolean r2 = r2.equals(r14)
            if (r2 == 0) goto L_0x018a
            r6 = r11
            r10 = r20
        L_0x018a:
            java.lang.String r2 = r4.getObject()
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L_0x01aa
            java.lang.String r2 = r4.getVariable()
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x019f
            r8 = 1
        L_0x019f:
            java.lang.String r2 = r4.getVariable()
            boolean r2 = r2.equals(r12)
            if (r2 == 0) goto L_0x01aa
            r9 = 1
        L_0x01aa:
            int r2 = r20 + 1
            goto L_0x0168
        L_0x01ad:
            r19 = r4
        L_0x01af:
            r2 = r26
            r1 = r16
            r4 = r19
            r7 = 1
            goto L_0x0144
        L_0x01b7:
            r19 = r4
            if (r6 == 0) goto L_0x0235
            if (r8 != 0) goto L_0x01f8
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r2 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            r2.<init>()
            cn.sherlock.com.sun.media.sound.ModelDestination r4 = r6.getDestination()
            r2.setDestination(r4)
            double r4 = r6.getScale()
            r2.setScale(r4)
            cn.sherlock.com.sun.media.sound.ModelSource[] r4 = r6.getSources()
            int r5 = r4.length
            cn.sherlock.com.sun.media.sound.ModelSource[] r7 = new cn.sherlock.com.sun.media.sound.ModelSource[r5]
            r8 = r16
        L_0x01d9:
            if (r8 >= r5) goto L_0x01e2
            r11 = r4[r8]
            r7[r8] = r11
            int r8 = r8 + 1
            goto L_0x01d9
        L_0x01e2:
            cn.sherlock.com.sun.media.sound.ModelSource r4 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r5 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r5.<init>((java.lang.String) r1, (java.lang.String) r13)
            r4.<init>(r5)
            r7[r10] = r4
            r2.setSources(r7)
            java.lang.String r4 = r0.extractKeys(r2)
            r3.put(r4, r2)
        L_0x01f8:
            if (r9 != 0) goto L_0x0235
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r2 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            r2.<init>()
            cn.sherlock.com.sun.media.sound.ModelDestination r4 = r6.getDestination()
            r2.setDestination(r4)
            double r4 = r6.getScale()
            r2.setScale(r4)
            cn.sherlock.com.sun.media.sound.ModelSource[] r4 = r6.getSources()
            int r5 = r4.length
            cn.sherlock.com.sun.media.sound.ModelSource[] r6 = new cn.sherlock.com.sun.media.sound.ModelSource[r5]
            r7 = r16
        L_0x0216:
            if (r7 >= r5) goto L_0x021f
            r8 = r4[r7]
            r6[r7] = r8
            int r7 = r7 + 1
            goto L_0x0216
        L_0x021f:
            cn.sherlock.com.sun.media.sound.ModelSource r4 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r5 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r5.<init>((java.lang.String) r1, (java.lang.String) r12)
            r4.<init>(r5)
            r6[r10] = r4
            r2.setSources(r6)
            java.lang.String r1 = r0.extractKeys(r2)
            r3.put(r1, r2)
        L_0x0235:
            java.util.Iterator r1 = r19.iterator()
            r2 = r18
        L_0x023b:
            boolean r4 = r1.hasNext()
            java.lang.String r5 = "lfo"
            if (r4 == 0) goto L_0x02a9
            java.lang.Object r4 = r1.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r4 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r4
            cn.sherlock.com.sun.media.sound.ModelSource[] r6 = r4.getSources()
            int r7 = r6.length
            if (r7 == 0) goto L_0x023b
            r7 = r6[r16]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r7 = r7.getIdentifier()
            java.lang.String r7 = r7.getObject()
            boolean r5 = r7.equals(r5)
            if (r5 == 0) goto L_0x023b
            cn.sherlock.com.sun.media.sound.ModelDestination r5 = r4.getDestination()
            cn.sherlock.com.sun.media.sound.ModelIdentifier r5 = r5.getIdentifier()
            cn.sherlock.com.sun.media.sound.ModelIdentifier r7 = cn.sherlock.com.sun.media.sound.ModelDestination.DESTINATION_PITCH
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x023b
            if (r2 != 0) goto L_0x0273
            goto L_0x02a7
        L_0x0273:
            cn.sherlock.com.sun.media.sound.ModelSource[] r5 = r2.getSources()
            int r5 = r5.length
            int r7 = r6.length
            if (r5 <= r7) goto L_0x027c
            goto L_0x02a7
        L_0x027c:
            cn.sherlock.com.sun.media.sound.ModelSource[] r5 = r2.getSources()
            r5 = r5[r16]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r5 = r5.getIdentifier()
            int r5 = r5.getInstance()
            r7 = 1
            if (r5 >= r7) goto L_0x023b
            cn.sherlock.com.sun.media.sound.ModelSource[] r5 = r2.getSources()
            r5 = r5[r16]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r5 = r5.getIdentifier()
            int r5 = r5.getInstance()
            r6 = r6[r16]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = r6.getIdentifier()
            int r6 = r6.getInstance()
            if (r5 <= r6) goto L_0x023b
        L_0x02a7:
            r2 = r4
            goto L_0x023b
        L_0x02a9:
            if (r2 == 0) goto L_0x02bb
            cn.sherlock.com.sun.media.sound.ModelSource[] r1 = r2.getSources()
            r1 = r1[r16]
            cn.sherlock.com.sun.media.sound.ModelIdentifier r1 = r1.getIdentifier()
            int r1 = r1.getInstance()
            r7 = r1
            goto L_0x02bc
        L_0x02bb:
            r7 = 1
        L_0x02bc:
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r1 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            cn.sherlock.com.sun.media.sound.ModelSource r4 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            java.lang.String r8 = "78"
            r6.<init>((java.lang.String) r15, (java.lang.String) r8)
            r9 = r16
            r8 = 1
            r4.<init>(r6, r9, r8, r9)
            cn.sherlock.com.sun.media.sound.ModelDestination r6 = new cn.sherlock.com.sun.media.sound.ModelDestination
            cn.sherlock.com.sun.media.sound.ModelIdentifier r8 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            java.lang.String r9 = "delay2"
            r8.<init>(r5, r9, r7)
            r6.<init>(r8)
            r8 = 4656510908468559872(0x409f400000000000, double:2000.0)
            r1.<init>((cn.sherlock.com.sun.media.sound.ModelSource) r4, (double) r8, (cn.sherlock.com.sun.media.sound.ModelDestination) r6)
            java.lang.String r4 = r0.extractKeys(r1)
            r3.put(r4, r1)
            if (r2 != 0) goto L_0x02ed
            r1 = 0
            goto L_0x02f1
        L_0x02ed:
            double r1 = r2.getScale()
        L_0x02f1:
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r4 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            cn.sherlock.com.sun.media.sound.ModelSource r6 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r8 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            r8.<init>((java.lang.String) r5, (int) r7)
            r6.<init>(r8)
            cn.sherlock.com.sun.media.sound.ModelSource r8 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r9 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            java.lang.String r10 = "77"
            r9.<init>((java.lang.String) r15, (java.lang.String) r10)
            cn.sherlock.com.sun.media.sound.SoftPerformer$2 r10 = new cn.sherlock.com.sun.media.sound.SoftPerformer$2
            r10.<init>(r1)
            r8.<init>((cn.sherlock.com.sun.media.sound.ModelIdentifier) r9, (cn.sherlock.com.sun.media.sound.ModelTransform) r10)
            cn.sherlock.com.sun.media.sound.ModelDestination r1 = new cn.sherlock.com.sun.media.sound.ModelDestination
            cn.sherlock.com.sun.media.sound.ModelIdentifier r2 = cn.sherlock.com.sun.media.sound.ModelDestination.DESTINATION_PITCH
            r1.<init>(r2)
            r4.<init>((cn.sherlock.com.sun.media.sound.ModelSource) r6, (cn.sherlock.com.sun.media.sound.ModelSource) r8, (cn.sherlock.com.sun.media.sound.ModelDestination) r1)
            java.lang.String r1 = r0.extractKeys(r4)
            r3.put(r1, r4)
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r1 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock
            cn.sherlock.com.sun.media.sound.ModelSource r2 = new cn.sherlock.com.sun.media.sound.ModelSource
            cn.sherlock.com.sun.media.sound.ModelIdentifier r4 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            java.lang.String r6 = "76"
            r4.<init>((java.lang.String) r15, (java.lang.String) r6)
            r8 = 1
            r9 = 0
            r2.<init>(r4, r9, r8, r9)
            cn.sherlock.com.sun.media.sound.ModelDestination r4 = new cn.sherlock.com.sun.media.sound.ModelDestination
            cn.sherlock.com.sun.media.sound.ModelIdentifier r6 = new cn.sherlock.com.sun.media.sound.ModelIdentifier
            java.lang.String r8 = "freq"
            r6.<init>(r5, r8, r7)
            r4.<init>(r6)
            r5 = 4657496070887047168(0x40a2c00000000000, double:2400.0)
            r1.<init>((cn.sherlock.com.sun.media.sound.ModelSource) r2, (double) r5, (cn.sherlock.com.sun.media.sound.ModelDestination) r4)
            java.lang.String r2 = r0.extractKeys(r1)
            r3.put(r2, r1)
            goto L_0x034f
        L_0x034b:
            r19 = r4
            r18 = 0
        L_0x034f:
            boolean r1 = r26.isDefaultConnectionsEnabled()
            if (r1 == 0) goto L_0x0367
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock[] r1 = defaultconnections
            int r2 = r1.length
            r9 = 0
        L_0x0359:
            if (r9 >= r2) goto L_0x0367
            r4 = r1[r9]
            java.lang.String r5 = r0.extractKeys(r4)
            r3.put(r5, r4)
            int r9 = r9 + 1
            goto L_0x0359
        L_0x0367:
            java.util.Iterator r1 = r19.iterator()
        L_0x036b:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x037f
            java.lang.Object r2 = r1.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r2 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r2
            java.lang.String r4 = r0.extractKeys(r2)
            r3.put(r4, r2)
            goto L_0x036b
        L_0x037f:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 128(0x80, float:1.794E-43)
            int[][] r2 = new int[r2][]
            r0.midi_ctrl_connections = r2
            r9 = 0
        L_0x038b:
            int[][] r2 = r0.midi_ctrl_connections
            int r4 = r2.length
            if (r9 >= r4) goto L_0x0395
            r2[r9] = r18
            int r9 = r9 + 1
            goto L_0x038b
        L_0x0395:
            r2 = 5
            int[][] r2 = new int[r2][]
            r0.midi_connections = r2
            r9 = 0
        L_0x039b:
            int[][] r2 = r0.midi_connections
            int r4 = r2.length
            if (r9 >= r4) goto L_0x03a5
            r2[r9] = r18
            int r9 = r9 + 1
            goto L_0x039b
        L_0x03a5:
            java.util.Collection r2 = r3.values()
            java.util.Iterator r2 = r2.iterator()
            r7 = 0
        L_0x03ae:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0401
            java.lang.Object r3 = r2.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r3 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r3
            cn.sherlock.com.sun.media.sound.ModelDestination r4 = r3.getDestination()
            if (r4 == 0) goto L_0x03f3
            cn.sherlock.com.sun.media.sound.ModelDestination r4 = r3.getDestination()
            cn.sherlock.com.sun.media.sound.ModelIdentifier r4 = r4.getIdentifier()
            java.lang.String r5 = r4.getObject()
            java.lang.String r6 = "noteon"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x03f3
            java.lang.String r5 = r4.getVariable()
            java.lang.String r6 = "keynumber"
            boolean r5 = r5.equals(r6)
            r8 = 1
            if (r5 == 0) goto L_0x03e3
            r0.forcedKeynumber = r8
        L_0x03e3:
            java.lang.String r4 = r4.getVariable()
            java.lang.String r5 = "velocity"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x03f1
            r0.forcedVelocity = r8
        L_0x03f1:
            r7 = r8
            goto L_0x03f4
        L_0x03f3:
            r8 = 1
        L_0x03f4:
            if (r7 == 0) goto L_0x03fc
            r9 = 0
            r1.add(r9, r3)
            r7 = r9
            goto L_0x03ae
        L_0x03fc:
            r9 = 0
            r1.add(r3)
            goto L_0x03ae
        L_0x0401:
            r9 = 0
            java.util.Iterator r2 = r1.iterator()
            r3 = r9
        L_0x0407:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x042c
            java.lang.Object r4 = r2.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r4 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r4
            cn.sherlock.com.sun.media.sound.ModelSource[] r5 = r4.getSources()
            if (r5 == 0) goto L_0x0429
            cn.sherlock.com.sun.media.sound.ModelSource[] r4 = r4.getSources()
            r5 = r9
        L_0x041e:
            int r6 = r4.length
            if (r5 >= r6) goto L_0x0429
            r6 = r4[r5]
            r0.processSource(r6, r3)
            int r5 = r5 + 1
            goto L_0x041e
        L_0x0429:
            int r3 = r3 + 1
            goto L_0x0407
        L_0x042c:
            int r2 = r1.size()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock[] r2 = new cn.sherlock.com.sun.media.sound.ModelConnectionBlock[r2]
            r0.connections = r2
            r1.toArray(r2)
            java.util.List<java.lang.Integer> r2 = r0.ctrl_connections_list
            int r2 = r2.size()
            int[] r2 = new int[r2]
            r0.ctrl_connections = r2
            r2 = r9
        L_0x0442:
            int[] r3 = r0.ctrl_connections
            int r4 = r3.length
            if (r2 >= r4) goto L_0x0458
            java.util.List<java.lang.Integer> r4 = r0.ctrl_connections_list
            java.lang.Object r4 = r4.get(r2)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r3[r2] = r4
            int r2 = r2 + 1
            goto L_0x0442
        L_0x0458:
            java.util.List r2 = r26.getOscillators()
            int r2 = r2.size()
            cn.sherlock.com.sun.media.sound.ModelOscillator[] r2 = new cn.sherlock.com.sun.media.sound.ModelOscillator[r2]
            r0.oscillators = r2
            java.util.List r2 = r26.getOscillators()
            cn.sherlock.com.sun.media.sound.ModelOscillator[] r3 = r0.oscillators
            r2.toArray(r3)
            java.util.Iterator r1 = r1.iterator()
        L_0x0471:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x04c1
            java.lang.Object r2 = r1.next()
            cn.sherlock.com.sun.media.sound.ModelConnectionBlock r2 = (cn.sherlock.com.sun.media.sound.ModelConnectionBlock) r2
            cn.sherlock.com.sun.media.sound.ModelDestination r3 = r2.getDestination()
            if (r3 == 0) goto L_0x049a
            cn.sherlock.com.sun.media.sound.ModelDestination r3 = r2.getDestination()
            cn.sherlock.com.sun.media.sound.ModelTransform r3 = r3.getTransform()
            boolean r3 = isUnnecessaryTransform(r3)
            if (r3 == 0) goto L_0x049a
            cn.sherlock.com.sun.media.sound.ModelDestination r3 = r2.getDestination()
            r4 = r18
            r3.setTransform(r4)
        L_0x049a:
            cn.sherlock.com.sun.media.sound.ModelSource[] r3 = r2.getSources()
            if (r3 == 0) goto L_0x04bd
            cn.sherlock.com.sun.media.sound.ModelSource[] r2 = r2.getSources()
            int r3 = r2.length
            r4 = r9
        L_0x04a6:
            if (r4 >= r3) goto L_0x04bd
            r5 = r2[r4]
            cn.sherlock.com.sun.media.sound.ModelTransform r6 = r5.getTransform()
            boolean r6 = isUnnecessaryTransform(r6)
            if (r6 == 0) goto L_0x04b9
            r6 = 0
            r5.setTransform(r6)
            goto L_0x04ba
        L_0x04b9:
            r6 = 0
        L_0x04ba:
            int r4 = r4 + 1
            goto L_0x04a6
        L_0x04bd:
            r6 = 0
            r18 = r6
            goto L_0x0471
        L_0x04c1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.SoftPerformer.<init>(cn.sherlock.com.sun.media.sound.ModelPerformer):void");
    }

    private static boolean isUnnecessaryTransform(ModelTransform modelTransform) {
        if (modelTransform == null || !(modelTransform instanceof ModelStandardTransform)) {
            return false;
        }
        ModelStandardTransform modelStandardTransform = (ModelStandardTransform) modelTransform;
        if (modelStandardTransform.getDirection() || modelStandardTransform.getPolarity()) {
            return false;
        }
        modelStandardTransform.getTransform();
        return false;
    }
}
