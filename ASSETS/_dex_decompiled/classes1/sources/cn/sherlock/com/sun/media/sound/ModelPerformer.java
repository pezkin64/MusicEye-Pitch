package cn.sherlock.com.sun.media.sound;

import java.util.ArrayList;
import java.util.List;

public class ModelPerformer {
    private boolean addDefaultConnections = true;
    private List<ModelConnectionBlock> connectionBlocks = new ArrayList();
    private int exclusiveClass = 0;
    private int keyFrom = 0;
    private int keyTo = 127;
    private String name = null;
    private List<ModelOscillator> oscillators = new ArrayList();
    private boolean releaseTrigger = false;
    private boolean selfNonExclusive = false;
    private Object userObject = null;
    private int velFrom = 0;
    private int velTo = 127;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public List<ModelConnectionBlock> getConnectionBlocks() {
        return this.connectionBlocks;
    }

    public void setConnectionBlocks(List<ModelConnectionBlock> list) {
        this.connectionBlocks = list;
    }

    public List<ModelOscillator> getOscillators() {
        return this.oscillators;
    }

    public int getExclusiveClass() {
        return this.exclusiveClass;
    }

    public void setExclusiveClass(int i) {
        this.exclusiveClass = i;
    }

    public boolean isSelfNonExclusive() {
        return this.selfNonExclusive;
    }

    public void setSelfNonExclusive(boolean z) {
        this.selfNonExclusive = z;
    }

    public int getKeyFrom() {
        return this.keyFrom;
    }

    public void setKeyFrom(int i) {
        this.keyFrom = i;
    }

    public int getKeyTo() {
        return this.keyTo;
    }

    public void setKeyTo(int i) {
        this.keyTo = i;
    }

    public int getVelFrom() {
        return this.velFrom;
    }

    public void setVelFrom(int i) {
        this.velFrom = i;
    }

    public int getVelTo() {
        return this.velTo;
    }

    public void setVelTo(int i) {
        this.velTo = i;
    }

    public boolean isReleaseTriggered() {
        return this.releaseTrigger;
    }

    public void setReleaseTriggered(boolean z) {
        this.releaseTrigger = z;
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public void setUserObject(Object obj) {
        this.userObject = obj;
    }

    public boolean isDefaultConnectionsEnabled() {
        return this.addDefaultConnections;
    }

    public void setDefaultConnectionsEnabled(boolean z) {
        this.addDefaultConnections = z;
    }
}
