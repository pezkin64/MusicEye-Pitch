package cn.sherlock.com.sun.media.sound;

public class ModelStandardIndexedDirector implements ModelDirector {
    int[] counters;
    int[][] mat;
    boolean noteOffUsed = false;
    boolean noteOnUsed = false;
    ModelPerformer[] performers;
    ModelDirectedPlayer player;
    byte[][] trantables;

    private int restrict(int i) {
        if (i < 0) {
            return 0;
        }
        if (i > 127) {
            return 127;
        }
        return i;
    }

    public void close() {
    }

    public ModelStandardIndexedDirector(ModelPerformer[] modelPerformerArr, ModelDirectedPlayer modelDirectedPlayer) {
        this.performers = modelPerformerArr;
        this.player = modelDirectedPlayer;
        for (ModelPerformer isReleaseTriggered : modelPerformerArr) {
            if (isReleaseTriggered.isReleaseTriggered()) {
                this.noteOffUsed = true;
            } else {
                this.noteOnUsed = true;
            }
        }
        buildindex();
    }

    private int[] lookupIndex(int i, int i2) {
        if (i < 0 || i >= 128 || i2 < 0 || i2 >= 128) {
            return null;
        }
        byte[][] bArr = this.trantables;
        byte b = bArr[0][i];
        byte b2 = bArr[1][i2];
        if (b == -1 || b2 == -1) {
            return null;
        }
        return this.mat[b + (b2 * this.counters[0])];
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v8, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v7, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v9, resolved type: byte} */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r8v4, types: [byte] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void buildindex() {
        /*
            r16 = this;
            r0 = r16
            r1 = 2
            int[] r2 = new int[r1]
            r3 = 1
            r4 = 129(0x81, float:1.81E-43)
            r2[r3] = r4
            r4 = 0
            r2[r4] = r1
            java.lang.Class r1 = java.lang.Byte.TYPE
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r2)
            byte[][] r1 = (byte[][]) r1
            r0.trantables = r1
            int r1 = r1.length
            int[] r1 = new int[r1]
            r0.counters = r1
            cn.sherlock.com.sun.media.sound.ModelPerformer[] r1 = r0.performers
            int r2 = r1.length
            r5 = r4
        L_0x0020:
            if (r5 >= r2) goto L_0x005d
            r6 = r1[r5]
            int r7 = r6.getKeyFrom()
            int r8 = r6.getKeyTo()
            int r9 = r6.getVelFrom()
            int r6 = r6.getVelTo()
            if (r7 <= r8) goto L_0x0037
            goto L_0x005a
        L_0x0037:
            if (r9 <= r6) goto L_0x003a
            goto L_0x005a
        L_0x003a:
            int r7 = r0.restrict(r7)
            int r8 = r0.restrict(r8)
            int r9 = r0.restrict(r9)
            int r6 = r0.restrict(r6)
            byte[][] r10 = r0.trantables
            r11 = r10[r4]
            r11[r7] = r3
            int r8 = r8 + r3
            r11[r8] = r3
            r7 = r10[r3]
            r7[r9] = r3
            int r6 = r6 + r3
            r7[r6] = r3
        L_0x005a:
            int r5 = r5 + 1
            goto L_0x0020
        L_0x005d:
            r1 = r4
        L_0x005e:
            byte[][] r2 = r0.trantables
            int r5 = r2.length
            r6 = -1
            if (r1 >= r5) goto L_0x0091
            r2 = r2[r1]
            int r5 = r2.length
            int r7 = r5 + -1
        L_0x0069:
            if (r7 < 0) goto L_0x0077
            byte r8 = r2[r7]
            if (r8 != r3) goto L_0x0072
            r2[r7] = r6
            goto L_0x0077
        L_0x0072:
            r2[r7] = r6
            int r7 = r7 + -1
            goto L_0x0069
        L_0x0077:
            r7 = r4
            r8 = r6
        L_0x0079:
            if (r7 >= r5) goto L_0x008a
            byte r9 = r2[r7]
            if (r9 == 0) goto L_0x0084
            int r8 = r8 + 1
            if (r9 != r6) goto L_0x0084
            goto L_0x008a
        L_0x0084:
            byte r9 = (byte) r8
            r2[r7] = r9
            int r7 = r7 + 1
            goto L_0x0079
        L_0x008a:
            int[] r2 = r0.counters
            r2[r1] = r8
            int r1 = r1 + 1
            goto L_0x005e
        L_0x0091:
            int[] r1 = r0.counters
            r2 = r1[r4]
            r1 = r1[r3]
            int r2 = r2 * r1
            int[][] r1 = new int[r2][]
            r0.mat = r1
            cn.sherlock.com.sun.media.sound.ModelPerformer[] r1 = r0.performers
            int r2 = r1.length
            r5 = r4
            r7 = r5
        L_0x00a1:
            if (r5 >= r2) goto L_0x012a
            r8 = r1[r5]
            int r9 = r8.getKeyFrom()
            int r10 = r8.getKeyTo()
            int r11 = r8.getVelFrom()
            int r8 = r8.getVelTo()
            if (r9 <= r10) goto L_0x00b9
            goto L_0x0124
        L_0x00b9:
            if (r11 <= r8) goto L_0x00bc
            goto L_0x0124
        L_0x00bc:
            int r9 = r0.restrict(r9)
            int r10 = r0.restrict(r10)
            int r11 = r0.restrict(r11)
            int r8 = r0.restrict(r8)
            byte[][] r12 = r0.trantables
            r13 = r12[r4]
            byte r9 = r13[r9]
            int r10 = r10 + r3
            byte r10 = r13[r10]
            r12 = r12[r3]
            byte r11 = r12[r11]
            int r8 = r8 + r3
            byte r8 = r12[r8]
            if (r10 != r6) goto L_0x00e2
            int[] r10 = r0.counters
            r10 = r10[r4]
        L_0x00e2:
            if (r8 != r6) goto L_0x00e8
            int[] r8 = r0.counters
            r8 = r8[r3]
        L_0x00e8:
            if (r11 >= r8) goto L_0x0122
            int[] r12 = r0.counters
            r12 = r12[r4]
            int r12 = r12 * r11
            int r12 = r12 + r9
            r13 = r9
        L_0x00f1:
            if (r13 >= r10) goto L_0x011d
            int[][] r14 = r0.mat
            r15 = r14[r12]
            if (r15 != 0) goto L_0x0100
            int[] r15 = new int[]{r7}
            r14[r12] = r15
            goto L_0x0116
        L_0x0100:
            int r14 = r15.length
            int r3 = r14 + 1
            int[] r3 = new int[r3]
            r3[r14] = r7
            r14 = r4
        L_0x0108:
            int r4 = r15.length
            if (r14 >= r4) goto L_0x0112
            r4 = r15[r14]
            r3[r14] = r4
            int r14 = r14 + 1
            goto L_0x0108
        L_0x0112:
            int[][] r4 = r0.mat
            r4[r12] = r3
        L_0x0116:
            int r12 = r12 + 1
            int r13 = r13 + 1
            r3 = 1
            r4 = 0
            goto L_0x00f1
        L_0x011d:
            int r11 = r11 + 1
            r3 = 1
            r4 = 0
            goto L_0x00e8
        L_0x0122:
            int r7 = r7 + 1
        L_0x0124:
            int r5 = r5 + 1
            r3 = 1
            r4 = 0
            goto L_0x00a1
        L_0x012a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sherlock.com.sun.media.sound.ModelStandardIndexedDirector.buildindex():void");
    }

    public void noteOff(int i, int i2) {
        int[] lookupIndex;
        if (this.noteOffUsed && (lookupIndex = lookupIndex(i, i2)) != null) {
            for (int i3 : lookupIndex) {
                if (this.performers[i3].isReleaseTriggered()) {
                    this.player.play(i3, (ModelConnectionBlock[]) null);
                }
            }
        }
    }

    public void noteOn(int i, int i2) {
        int[] lookupIndex;
        if (this.noteOnUsed && (lookupIndex = lookupIndex(i, i2)) != null) {
            for (int i3 : lookupIndex) {
                if (!this.performers[i3].isReleaseTriggered()) {
                    this.player.play(i3, (ModelConnectionBlock[]) null);
                }
            }
        }
    }
}
