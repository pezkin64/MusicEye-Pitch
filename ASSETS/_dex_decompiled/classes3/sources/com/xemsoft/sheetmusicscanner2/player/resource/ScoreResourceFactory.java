package com.xemsoft.sheetmusicscanner2.player.resource;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.AsyncTask;
import com.xemsoft.sheetmusicscanner2.leptonica.Box;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.persist.CdSession;
import com.xemsoft.sheetmusicscanner2.persist.ScoreImages;
import com.xemsoft.sheetmusicscanner2.persist.SessionUtility;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.bar;
import com.xemsoft.sheetmusicscanner2.sources.baraa;
import com.xemsoft.sheetmusicscanner2.sources.score;
import com.xemsoft.sheetmusicscanner2.sources.session;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import com.xemsoft.sheetmusicscanner2.sources.timepoint;
import com.xemsoft.sheetmusicscanner2.util.Swig;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.util.ArrayList;
import java.util.List;
import jp.kshoji.javax.sound.midi.Sequence;

public class ScoreResourceFactory {
    private static final String LOGTAG = "ScoreResourceFactory.java";
    /* access modifiers changed from: private */
    public CdSession m_CdSession;
    /* access modifiers changed from: private */
    public Context m_Context;
    /* access modifiers changed from: private */
    public String m_DirName;
    private SessionUtility m_SUtil;
    /* access modifiers changed from: private */
    public int m_ScreenW;
    /* access modifiers changed from: private */
    public session m_Session;

    public interface LoadProgressListener {
        void onDone(List<ScoreResource> list);

        void onProgress(int i);
    }

    public ScoreResourceFactory(Context context) {
        this.m_Context = context;
        this.m_SUtil = SessionUtility.getInstance(context);
    }

    public List<ScoreResource> getResourceList(CdSession cdSession, session session) {
        this.m_CdSession = cdSession;
        this.m_Session = session;
        this.m_ScreenW = Utils.getScreenMinWidth(this.m_Context);
        this.m_DirName = cdSession.getSessionFolderName();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < session.getN(); i++) {
            if (!loadScoreResource(JniSource.sessionGet(session, i), arrayList, i)) {
                return null;
            }
        }
        return arrayList;
    }

    public void getResourceListAsync(CdSession cdSession, session session, LoadProgressListener loadProgressListener) {
        this.m_CdSession = cdSession;
        this.m_Session = session;
        this.m_ScreenW = Utils.getScreenMinWidth(this.m_Context);
        this.m_DirName = cdSession.getSessionFolderName();
        new GetResourceListTask(loadProgressListener).execute(new Void[0]);
    }

    public void destroyResourceList(List<ScoreResource> list) {
        int size;
        if (list != null && (size = list.size()) != 0) {
            for (int i = 0; i < size; i++) {
                destroyResource(list.get(i));
            }
            list.clear();
        }
    }

    private void destroyResource(ScoreResource scoreResource) {
        if (scoreResource != null) {
            Bitmap backgroundImage = scoreResource.getBackgroundImage();
            if (backgroundImage != null) {
                backgroundImage.recycle();
            }
            Bitmap overlayImage = scoreResource.getOverlayImage();
            if (overlayImage != null) {
                overlayImage.recycle();
            }
            for (int i = 0; i < scoreResource.getSoundImageCount(); i++) {
                Bitmap bitmap = scoreResource.getSoundImage(i).getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
    }

    private class GetResourceListTask extends AsyncTask<Void, Integer, List> {
        private LoadProgressListener m_Listener;

        public GetResourceListTask(LoadProgressListener loadProgressListener) {
            this.m_Listener = loadProgressListener;
        }

        /* access modifiers changed from: protected */
        public List doInBackground(Void... voidArr) {
            ArrayList arrayList = new ArrayList();
            ScoreResourceFactory scoreResourceFactory = ScoreResourceFactory.this;
            int unused = scoreResourceFactory.m_ScreenW = Utils.getScreenMinWidth(scoreResourceFactory.m_Context);
            ScoreResourceFactory scoreResourceFactory2 = ScoreResourceFactory.this;
            String unused2 = scoreResourceFactory2.m_DirName = scoreResourceFactory2.m_CdSession.getSessionFolderName();
            publishProgress(new Integer[]{0});
            int i = 0;
            while (i < ScoreResourceFactory.this.m_Session.getN()) {
                ScoreResourceFactory scoreResourceFactory3 = ScoreResourceFactory.this;
                if (!scoreResourceFactory3.loadScoreResource(JniSource.sessionGet(scoreResourceFactory3.m_Session, i), arrayList, i)) {
                    return null;
                }
                i++;
                publishProgress(new Integer[]{Integer.valueOf(i)});
            }
            return arrayList;
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
            LoadProgressListener loadProgressListener = this.m_Listener;
            if (loadProgressListener != null) {
                loadProgressListener.onProgress(numArr[0].intValue());
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(List list) {
            LoadProgressListener loadProgressListener = this.m_Listener;
            if (loadProgressListener != null) {
                loadProgressListener.onDone(list);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean loadScoreResource(score score, List<ScoreResource> list, int i) {
        Bitmap bitmap;
        ScoreImages scoreImages = new ScoreImages();
        if (!this.m_SUtil.loadImagesFromSessionFolder(this.m_DirName, i, scoreImages) || (bitmap = scoreImages.backgroundImage) == null) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int i2 = this.m_ScreenW;
        float f = ((float) i2) / ((float) width);
        int i3 = (int) (((float) height) * f);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, i2, i3), (Paint) null);
        bitmap.recycle();
        Pix newPIX = Swig.newPIX(scoreImages.overlayImage);
        Pix pixScale = JniLeptonica.pixScale(newPIX, f, f);
        Swig.pixDestroy(newPIX);
        Bitmap writeBitmap = JniSource.writeBitmap(pixScale, true);
        Swig.pixDestroy(pixScale);
        ScoreResource scoreResource = new ScoreResource(width, height, i2, i3, f, i);
        scoreResource.setScore(score);
        generateOverlay(writeBitmap, createBitmap, scoreResource);
        Pix readBitmap = JniSource.readBitmap(createBitmap);
        createBarButtons(score, readBitmap, scoreResource);
        Swig.pixDestroy(readBitmap);
        writeBitmap.recycle();
        createBitmap.recycle();
        list.add(scoreResource);
        return true;
    }

    private void generateOverlay(Bitmap bitmap, Bitmap bitmap2, ScoreResource scoreResource) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap2, Sequence.PPQ, Sequence.PPQ, (Paint) null);
        scoreResource.setBackgroundImage(createBitmap);
        Bitmap createBitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap2);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawBitmap(bitmap2, Sequence.PPQ, Sequence.PPQ, (Paint) null);
        canvas.drawBitmap(bitmap, Sequence.PPQ, Sequence.PPQ, paint);
        scoreResource.setOverlayImage(createBitmap2);
    }

    private void createBarButtons(score score, Pix pix, ScoreResource scoreResource) {
        if (score != null) {
            int i = 0;
            bar baraaGetBar = JniSource.baraaGetBar(score.getSingleBaraa(), 0, 0);
            if (baraaGetBar != null) {
                i = (int) (((double) Swig.newBOX(baraaGetBar.getBox()).getH()) * 1.6d);
            }
            int i2 = i;
            score score2 = score;
            Pix pix2 = pix;
            ScoreResource scoreResource2 = scoreResource;
            createBarButtons(score2, score.getSingleBaraa(), pix2, false, i2, scoreResource2);
            if (score2.getGroupBaraa() != null) {
                createBarButtons(score2, score2.getGroupBaraa(), pix2, true, i2, scoreResource2);
            }
        }
    }

    private void createBarButtons(score score, baraa baraa, Pix pix, boolean z, int i, ScoreResource scoreResource) {
        int i2;
        int i3;
        int i4;
        bar baraaFindNextBarOverlappingInX;
        bar baraaFindPreviousBarOverlappingInX;
        baraa baraa2 = baraa;
        boolean z2 = z;
        ScoreResource scoreResource2 = scoreResource;
        float scale = scoreResource2.getScale();
        if (baraa2 != null) {
            int i5 = 0;
            int i6 = 0;
            while (i6 < baraa2.getN()) {
                int baraaGetBarCount = JniSource.baraaGetBarCount(baraa2, i6);
                if (baraaGetBarCount > 0) {
                    bar baraaGetBar = JniSource.baraaGetBar(baraa2, i6, i5);
                    if (i6 <= 0 || (baraaFindPreviousBarOverlappingInX = JniSource.baraaFindPreviousBarOverlappingInX(baraa2, baraaGetBar, i6 - 1)) == null) {
                        i4 = i;
                    } else {
                        Box newBOX = Swig.newBOX(baraaFindPreviousBarOverlappingInX.getBox());
                        i4 = (int) (((float) ((Swig.newBOX(baraaGetBar.getBox()).getY() - newBOX.getY()) - newBOX.getH())) * 0.4f);
                    }
                    if (i6 >= baraa2.getN() - 1 || (baraaFindNextBarOverlappingInX = JniSource.baraaFindNextBarOverlappingInX(baraa2, baraaGetBar, i6 + 1)) == null) {
                        i3 = i;
                    } else {
                        Box newBOX2 = Swig.newBOX(baraaGetBar.getBox());
                        i3 = (int) (((float) ((Swig.newBOX(baraaFindNextBarOverlappingInX.getBox()).getY() - newBOX2.getY()) - newBOX2.getH())) * 0.4f);
                    }
                    i2 = i4;
                } else {
                    i3 = i;
                    i2 = i3;
                }
                int i7 = i5;
                while (i7 < baraaGetBarCount) {
                    bar baraaGetBar2 = JniSource.baraaGetBar(baraa2, i6, i7);
                    Box newBOX3 = Swig.newBOX(baraaGetBar2.getBox());
                    double x = (double) (((float) newBOX3.getX()) * scale);
                    double d = (double) scale;
                    Box box = newBOX3;
                    double d2 = d;
                    double max = Math.max(0.0d, ((double) (newBOX3.getY() - i2)) * d);
                    i6 = i6;
                    int i8 = i7;
                    bar bar = baraaGetBar2;
                    BarButton barButton = new BarButton((int) x, (int) max, (int) Math.min(Math.max(1.0d, ((double) box.getW()) * d2), ((double) scoreResource2.getWidth()) - x), (int) Math.min(Math.max(1.0d, ((double) (box.getH() + i2 + i3)) * d2), ((double) scoreResource2.getHeight()) - max), score, bar, i6, i8);
                    barButton.hidden = z2;
                    barButton.isGroupButton = z2;
                    if (i8 == 0) {
                        barButton.isFirstInStaff = true;
                    }
                    if (i8 == baraaGetBarCount - 1) {
                        barButton.isLastInStaff = true;
                    }
                    barButton.key = bar.getCPtr(bar);
                    scoreResource2.addBarButton(barButton);
                    for (int i9 = 0; i9 < bar.getN(); i9++) {
                        timepoint barGetTP = JniSource.barGetTP(bar, i9);
                        for (int i10 = 0; i10 < barGetTP.getN(); i10++) {
                            sound tpGetSound = JniSource.tpGetSound(barGetTP, i10);
                            Bitmap createSoundImage = ImageUtility.createSoundImage(tpGetSound, scale, pix);
                            if (createSoundImage != null) {
                                Box newBOX4 = Swig.newBOX(tpGetSound.getBox());
                                scoreResource2.addSoundImage(new SoundImage(createSoundImage, (int) (((float) newBOX4.getX()) * scale), (int) (((float) newBOX4.getY()) * scale), sound.getCPtr(tpGetSound)));
                            }
                        }
                        Pix pix2 = pix;
                    }
                    Pix pix3 = pix;
                    i7 = i8 + 1;
                }
                Pix pix4 = pix;
                i6++;
                i5 = 0;
            }
        }
    }
}
