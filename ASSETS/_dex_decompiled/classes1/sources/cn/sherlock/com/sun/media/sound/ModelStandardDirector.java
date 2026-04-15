package cn.sherlock.com.sun.media.sound;

public class ModelStandardDirector implements ModelDirector {
    boolean noteOffUsed = false;
    boolean noteOnUsed = false;
    ModelPerformer[] performers;
    ModelDirectedPlayer player;

    public void close() {
    }

    public ModelStandardDirector(ModelPerformer[] modelPerformerArr, ModelDirectedPlayer modelDirectedPlayer) {
        this.performers = modelPerformerArr;
        this.player = modelDirectedPlayer;
        for (ModelPerformer isReleaseTriggered : modelPerformerArr) {
            if (isReleaseTriggered.isReleaseTriggered()) {
                this.noteOffUsed = true;
            } else {
                this.noteOnUsed = true;
            }
        }
    }

    public void noteOff(int i, int i2) {
        if (this.noteOffUsed) {
            int i3 = 0;
            while (true) {
                ModelPerformer[] modelPerformerArr = this.performers;
                if (i3 < modelPerformerArr.length) {
                    ModelPerformer modelPerformer = modelPerformerArr[i3];
                    if (modelPerformer.getKeyFrom() <= i && modelPerformer.getKeyTo() >= i && modelPerformer.getVelFrom() <= i2 && modelPerformer.getVelTo() >= i2 && modelPerformer.isReleaseTriggered()) {
                        this.player.play(i3, (ModelConnectionBlock[]) null);
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }

    public void noteOn(int i, int i2) {
        if (this.noteOnUsed) {
            int i3 = 0;
            while (true) {
                ModelPerformer[] modelPerformerArr = this.performers;
                if (i3 < modelPerformerArr.length) {
                    ModelPerformer modelPerformer = modelPerformerArr[i3];
                    if (modelPerformer.getKeyFrom() <= i && modelPerformer.getKeyTo() >= i && modelPerformer.getVelFrom() <= i2 && modelPerformer.getVelTo() >= i2 && !modelPerformer.isReleaseTriggered()) {
                        this.player.play(i3, (ModelConnectionBlock[]) null);
                    }
                    i3++;
                } else {
                    return;
                }
            }
        }
    }
}
