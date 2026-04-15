package com.xemsoft.sheetmusicscanner2.leptonica;

public class GPlot {
    public transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GPlot(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static long getCPtr(GPlot gPlot) {
        if (gPlot == null) {
            return 0;
        }
        return gPlot.swigCPtr;
    }

    public void finalize() {
        delete();
    }

    public synchronized void delete() {
        long j = this.swigCPtr;
        if (j != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                JniLeptonicaJNI.delete_GPlot(j);
            }
            this.swigCPtr = 0;
        }
    }

    public void setRootname(String str) {
        JniLeptonicaJNI.GPlot_rootname_set(this.swigCPtr, this, str);
    }

    public String getRootname() {
        return JniLeptonicaJNI.GPlot_rootname_get(this.swigCPtr, this);
    }

    public void setCmdname(String str) {
        JniLeptonicaJNI.GPlot_cmdname_set(this.swigCPtr, this, str);
    }

    public String getCmdname() {
        return JniLeptonicaJNI.GPlot_cmdname_get(this.swigCPtr, this);
    }

    public void setCmddata(Sarray sarray) {
        JniLeptonicaJNI.GPlot_cmddata_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getCmddata() {
        long GPlot_cmddata_get = JniLeptonicaJNI.GPlot_cmddata_get(this.swigCPtr, this);
        if (GPlot_cmddata_get == 0) {
            return null;
        }
        return new Sarray(GPlot_cmddata_get, false);
    }

    public void setDatanames(Sarray sarray) {
        JniLeptonicaJNI.GPlot_datanames_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getDatanames() {
        long GPlot_datanames_get = JniLeptonicaJNI.GPlot_datanames_get(this.swigCPtr, this);
        if (GPlot_datanames_get == 0) {
            return null;
        }
        return new Sarray(GPlot_datanames_get, false);
    }

    public void setPlotdata(Sarray sarray) {
        JniLeptonicaJNI.GPlot_plotdata_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getPlotdata() {
        long GPlot_plotdata_get = JniLeptonicaJNI.GPlot_plotdata_get(this.swigCPtr, this);
        if (GPlot_plotdata_get == 0) {
            return null;
        }
        return new Sarray(GPlot_plotdata_get, false);
    }

    public void setPlottitles(Sarray sarray) {
        JniLeptonicaJNI.GPlot_plottitles_set(this.swigCPtr, this, Sarray.getCPtr(sarray), sarray);
    }

    public Sarray getPlottitles() {
        long GPlot_plottitles_get = JniLeptonicaJNI.GPlot_plottitles_get(this.swigCPtr, this);
        if (GPlot_plottitles_get == 0) {
            return null;
        }
        return new Sarray(GPlot_plottitles_get, false);
    }

    public void setPlotstyles(Numa numa) {
        JniLeptonicaJNI.GPlot_plotstyles_set(this.swigCPtr, this, Numa.getCPtr(numa), numa);
    }

    public Numa getPlotstyles() {
        long GPlot_plotstyles_get = JniLeptonicaJNI.GPlot_plotstyles_get(this.swigCPtr, this);
        if (GPlot_plotstyles_get == 0) {
            return null;
        }
        return new Numa(GPlot_plotstyles_get, false);
    }

    public void setNplots(int i) {
        JniLeptonicaJNI.GPlot_nplots_set(this.swigCPtr, this, i);
    }

    public int getNplots() {
        return JniLeptonicaJNI.GPlot_nplots_get(this.swigCPtr, this);
    }

    public void setOutname(String str) {
        JniLeptonicaJNI.GPlot_outname_set(this.swigCPtr, this, str);
    }

    public String getOutname() {
        return JniLeptonicaJNI.GPlot_outname_get(this.swigCPtr, this);
    }

    public void setOutformat(int i) {
        JniLeptonicaJNI.GPlot_outformat_set(this.swigCPtr, this, i);
    }

    public int getOutformat() {
        return JniLeptonicaJNI.GPlot_outformat_get(this.swigCPtr, this);
    }

    public void setScaling(int i) {
        JniLeptonicaJNI.GPlot_scaling_set(this.swigCPtr, this, i);
    }

    public int getScaling() {
        return JniLeptonicaJNI.GPlot_scaling_get(this.swigCPtr, this);
    }

    public void setTitle(String str) {
        JniLeptonicaJNI.GPlot_title_set(this.swigCPtr, this, str);
    }

    public String getTitle() {
        return JniLeptonicaJNI.GPlot_title_get(this.swigCPtr, this);
    }

    public void setXlabel(String str) {
        JniLeptonicaJNI.GPlot_xlabel_set(this.swigCPtr, this, str);
    }

    public String getXlabel() {
        return JniLeptonicaJNI.GPlot_xlabel_get(this.swigCPtr, this);
    }

    public void setYlabel(String str) {
        JniLeptonicaJNI.GPlot_ylabel_set(this.swigCPtr, this, str);
    }

    public String getYlabel() {
        return JniLeptonicaJNI.GPlot_ylabel_get(this.swigCPtr, this);
    }

    public GPlot() {
        this(JniLeptonicaJNI.new_GPlot(), true);
    }
}
