package cn.sherlock.com.sun.media.sound;

public class ModelConnectionBlock {
    private static final ModelSource[] no_sources = new ModelSource[0];
    private ModelDestination destination;
    private double scale;
    private ModelSource[] sources;

    public ModelConnectionBlock() {
        this.sources = no_sources;
        this.scale = 1.0d;
    }

    public ModelConnectionBlock(double d, ModelDestination modelDestination) {
        this.sources = no_sources;
        this.scale = d;
        this.destination = modelDestination;
    }

    public ModelConnectionBlock(ModelSource modelSource, ModelDestination modelDestination) {
        this.sources = no_sources;
        this.scale = 1.0d;
        if (modelSource != null) {
            ModelSource[] modelSourceArr = new ModelSource[1];
            this.sources = modelSourceArr;
            modelSourceArr[0] = modelSource;
        }
        this.destination = modelDestination;
    }

    public ModelConnectionBlock(ModelSource modelSource, double d, ModelDestination modelDestination) {
        this.sources = no_sources;
        this.scale = 1.0d;
        if (modelSource != null) {
            ModelSource[] modelSourceArr = new ModelSource[1];
            this.sources = modelSourceArr;
            modelSourceArr[0] = modelSource;
        }
        this.scale = d;
        this.destination = modelDestination;
    }

    public ModelConnectionBlock(ModelSource modelSource, ModelSource modelSource2, ModelDestination modelDestination) {
        this.sources = no_sources;
        this.scale = 1.0d;
        if (modelSource != null) {
            if (modelSource2 == null) {
                ModelSource[] modelSourceArr = new ModelSource[1];
                this.sources = modelSourceArr;
                modelSourceArr[0] = modelSource;
            } else {
                ModelSource[] modelSourceArr2 = new ModelSource[2];
                this.sources = modelSourceArr2;
                modelSourceArr2[0] = modelSource;
                modelSourceArr2[1] = modelSource2;
            }
        }
        this.destination = modelDestination;
    }

    public ModelConnectionBlock(ModelSource modelSource, ModelSource modelSource2, double d, ModelDestination modelDestination) {
        this.sources = no_sources;
        this.scale = 1.0d;
        if (modelSource != null) {
            if (modelSource2 == null) {
                ModelSource[] modelSourceArr = new ModelSource[1];
                this.sources = modelSourceArr;
                modelSourceArr[0] = modelSource;
            } else {
                ModelSource[] modelSourceArr2 = new ModelSource[2];
                this.sources = modelSourceArr2;
                modelSourceArr2[0] = modelSource;
                modelSourceArr2[1] = modelSource2;
            }
        }
        this.scale = d;
        this.destination = modelDestination;
    }

    public ModelDestination getDestination() {
        return this.destination;
    }

    public void setDestination(ModelDestination modelDestination) {
        this.destination = modelDestination;
    }

    public double getScale() {
        return this.scale;
    }

    public void setScale(double d) {
        this.scale = d;
    }

    public ModelSource[] getSources() {
        return this.sources;
    }

    public void setSources(ModelSource[] modelSourceArr) {
        this.sources = modelSourceArr;
    }

    public void addSource(ModelSource modelSource) {
        ModelSource[] modelSourceArr = this.sources;
        this.sources = new ModelSource[(modelSourceArr.length + 1)];
        for (int i = 0; i < modelSourceArr.length; i++) {
            this.sources[i] = modelSourceArr[i];
        }
        ModelSource[] modelSourceArr2 = this.sources;
        modelSourceArr2[modelSourceArr2.length - 1] = modelSource;
    }
}
