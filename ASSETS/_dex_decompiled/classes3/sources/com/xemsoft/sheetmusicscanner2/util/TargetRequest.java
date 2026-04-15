package com.xemsoft.sheetmusicscanner2.util;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;

public class TargetRequest {
    private static final String LOGTAG = "TargetRequest.java";
    private static final int MAX_DONE = 4;
    private int m_DoneCount = 0;
    /* access modifiers changed from: private */
    public OnTargetRequestListener m_Listener = null;
    private List<TargetRequestTimer> m_TimerList = new ArrayList();

    public interface OnTargetRequestListener {
        void onRequest(TargetRequestParams targetRequestParams);
    }

    static /* synthetic */ int access$104(TargetRequest targetRequest) {
        int i = targetRequest.m_DoneCount + 1;
        targetRequest.m_DoneCount = i;
        return i;
    }

    public void setListener(OnTargetRequestListener onTargetRequestListener) {
        this.m_Listener = onTargetRequestListener;
    }

    public void requestDelayed(TargetRequestParams targetRequestParams, double d) {
        final TargetRequestTimer targetRequestTimer = new TargetRequestTimer(targetRequestParams);
        if (d < 0.0d) {
            d = 0.0d;
        }
        synchronized (this.m_TimerList) {
            targetRequestTimer.schedule(new TimerTask() {
                public void run() {
                    if (!targetRequestTimer.isDone()) {
                        if (TargetRequest.this.m_Listener != null) {
                            TargetRequest.this.m_Listener.onRequest(targetRequestTimer.getParams());
                        }
                        targetRequestTimer.setDone();
                        TargetRequest.access$104(TargetRequest.this);
                        TargetRequest.this.cleanDone();
                    }
                }
            }, (long) (d * 1000.0d));
            this.m_TimerList.add(targetRequestTimer);
        }
    }

    public void cancelTypeKeys(int i, int i2, int i3) {
        synchronized (this.m_TimerList) {
            try {
                ListIterator<TargetRequestTimer> listIterator = this.m_TimerList.listIterator();
                while (listIterator.hasNext()) {
                    TargetRequestTimer next = listIterator.next();
                    TargetRequestParams params = next.getParams();
                    if (params.m_Type == i && params.m_Key == i2 && params.m_Key2 == i3) {
                        next.setDone();
                        next.cancel();
                        listIterator.remove();
                    }
                }
            } catch (Exception e) {
                Log.w(LOGTAG, "cancelTypeKeys()", e);
            }
        }
    }

    public void cancelTypeKey(int i, int i2) {
        synchronized (this.m_TimerList) {
            try {
                ListIterator<TargetRequestTimer> listIterator = this.m_TimerList.listIterator();
                while (listIterator.hasNext()) {
                    TargetRequestTimer next = listIterator.next();
                    TargetRequestParams params = next.getParams();
                    if (params.m_Type == i && params.m_Key == i2) {
                        next.setDone();
                        next.cancel();
                        listIterator.remove();
                    }
                }
            } catch (Exception e) {
                Log.w(LOGTAG, "cancelTypeKey()", e);
            }
        }
    }

    public void cancelType(int i) {
        synchronized (this.m_TimerList) {
            ListIterator<TargetRequestTimer> listIterator = this.m_TimerList.listIterator();
            while (listIterator.hasNext()) {
                TargetRequestTimer next = listIterator.next();
                if (next.getParams().m_Type == i) {
                    next.setDone();
                    next.cancel();
                    listIterator.remove();
                }
            }
        }
    }

    public void cancelAll() {
        synchronized (this.m_TimerList) {
            ListIterator<TargetRequestTimer> listIterator = this.m_TimerList.listIterator();
            while (listIterator.hasNext()) {
                TargetRequestTimer next = listIterator.next();
                next.setDone();
                next.cancel();
                listIterator.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public void cleanDone() {
        if (this.m_DoneCount > 4) {
            synchronized (this.m_TimerList) {
                ListIterator<TargetRequestTimer> listIterator = this.m_TimerList.listIterator();
                while (listIterator.hasNext()) {
                    if (listIterator.next().isDone()) {
                        listIterator.remove();
                    }
                }
            }
        }
        this.m_DoneCount = 0;
    }
}
