package com.xemsoft.sheetmusicscanner2.analysis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.xemsoft.sheetmusicscanner2.R;
import com.xemsoft.sheetmusicscanner2.util.IntWrap;
import com.xemsoft.sheetmusicscanner2.util.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import jp.kshoji.javax.sound.midi.Sequence;

public class ImageLoader {
    private static final String LOGTAG = "ImageLoader";
    public static final int MODE_EMPTY = 0;
    public static final int MODE_FILE = 1;
    public static final int MODE_PDF = 3;
    public static final int MODE_URI = 2;
    private Context m_Context;
    private ParcelFileDescriptor m_FileDescriptor = null;
    private int m_ImageCount = 0;
    private int m_ImageNext = 0;
    private int m_LastModeUsed = 0;
    private int m_Mode = 0;
    private String m_Name = "No file";
    private String m_Path = null;
    private PdfRenderer m_PdfRenderer = null;
    private Uri m_Uri = null;

    private int exifToDegrees(int i) {
        if (i == 3) {
            return 180;
        }
        if (i != 6) {
            return i != 8 ? 0 : 270;
        }
        return 90;
    }

    public ImageLoader(Context context) {
        this.m_Context = context;
    }

    public boolean open(String str) {
        File file = new File(str);
        this.m_ImageNext = 0;
        this.m_ImageCount = 0;
        if (file.exists()) {
            this.m_Mode = 1;
            this.m_LastModeUsed = 1;
            this.m_Path = str;
            this.m_Name = this.m_Context.getString(R.string.newSong_defaultName);
            this.m_ImageCount = 1;
            return true;
        }
        close();
        return false;
    }

    public boolean open(Uri uri) {
        return open(uri, UriManager.getUriInfo(this.m_Context, uri));
    }

    public boolean open(Uri uri, UriInfo uriInfo) {
        this.m_Name = uriInfo.m_DisplayName;
        this.m_ImageNext = 0;
        this.m_ImageCount = 0;
        int i = uriInfo.m_MimeType;
        if (i == 1) {
            this.m_Mode = 2;
            this.m_LastModeUsed = 2;
            this.m_Uri = uri;
            this.m_ImageCount = 1;
        } else if (i != 4) {
            close();
            return false;
        } else {
            this.m_Mode = 3;
            this.m_LastModeUsed = 3;
            if (!createPdfRenderer(uri)) {
                return false;
            }
        }
        return true;
    }

    public boolean open(Uri uri, int i) {
        try {
            this.m_Context.getContentResolver().takePersistableUriPermission(uri, i & 1);
        } catch (Exception e) {
            Log.w(LOGTAG, "open() - takePersistableUriPermissions failed: ", e);
        }
        return open(uri);
    }

    public boolean isOpen() {
        return this.m_Mode != 0;
    }

    public boolean isPdf() {
        return this.m_Mode == 3;
    }

    public boolean isCamera() {
        return this.m_Mode == 1;
    }

    public int getLastModeUsed() {
        return this.m_LastModeUsed;
    }

    public String getName() {
        int lastIndexOf = this.m_Name.lastIndexOf(".");
        return lastIndexOf == -1 ? this.m_Name : this.m_Name.substring(0, lastIndexOf);
    }

    public int getImageCount() {
        return this.m_ImageCount;
    }

    public int getLoadedCount() {
        return this.m_ImageNext;
    }

    public boolean hasNext() {
        return this.m_ImageNext < this.m_ImageCount;
    }

    public Bitmap loadNext(IntWrap intWrap) {
        int i = this.m_ImageNext;
        if (i >= this.m_ImageCount) {
            return null;
        }
        Bitmap loadImage = loadImage(i, intWrap);
        this.m_ImageNext++;
        return loadImage;
    }

    public Bitmap loadImage(int i) {
        return loadImage(i, (IntWrap) null);
    }

    public Bitmap loadImage(int i, IntWrap intWrap) {
        if (intWrap != null) {
            intWrap.val = 0;
        }
        if (i < 0 || i > this.m_ImageCount - 1) {
            return null;
        }
        int i2 = this.m_Mode;
        if (i2 == 1) {
            return loadImageFromFile(this.m_Path);
        }
        if (i2 == 2) {
            return loadImageFromUri(this.m_Uri, intWrap);
        }
        if (i2 != 3) {
            return null;
        }
        return loadImageFromPdf(i);
    }

    private Bitmap loadImageFromFile(String str) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        if (decodeFile == null) {
            return null;
        }
        return Utils.rotateBitmap(decodeFile, getOrientationDegreesFromFile(str), true);
    }

    private Bitmap loadImageFromUri(Uri uri, IntWrap intWrap) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.m_Context.getContentResolver(), uri);
            if (bitmap == null) {
                return null;
            }
            return Utils.rotateBitmap(bitmap, getOrientationDegreesFromUri(uri), true);
        } catch (FileNotFoundException e) {
            String str = LOGTAG;
            Log.w(str, "loadImageFromUri() - file not found", e);
            String message = e.getMessage();
            Log.w(str, "message:" + message);
            if (message.contains("connection_failure") && intWrap != null) {
                intWrap.val = -1;
            }
            return null;
        } catch (IOException e2) {
            Log.w(LOGTAG, "loadImageFromUri()", e2);
            return null;
        }
    }

    private Bitmap loadImageFromPdf(int i) {
        PdfRenderer pdfRenderer = this.m_PdfRenderer;
        if (pdfRenderer == null || pdfRenderer.getPageCount() <= i) {
            return null;
        }
        PdfRenderer.Page openPage = this.m_PdfRenderer.openPage(i);
        XY pdfSize = pdfSize((float) openPage.getWidth(), (float) openPage.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap((int) pdfSize.x, (int) pdfSize.y, Bitmap.Config.ARGB_8888);
        openPage.render(createBitmap, (Rect) null, (Matrix) null, 1);
        openPage.close();
        Bitmap addWhiteBg = addWhiteBg(createBitmap);
        createBitmap.recycle();
        return addWhiteBg;
    }

    private XY pdfSize(float f, float f2) {
        float sqrt = (float) Math.sqrt((double) (1.0E7f / (f * f2)));
        return new XY(f * sqrt, f2 * sqrt);
    }

    private boolean createPdfRenderer(Uri uri) {
        closePdfRenderer();
        try {
            ParcelFileDescriptor openFileDescriptor = this.m_Context.getContentResolver().openFileDescriptor(uri, "r");
            this.m_FileDescriptor = openFileDescriptor;
            if (openFileDescriptor != null) {
                try {
                    this.m_PdfRenderer = new PdfRenderer(this.m_FileDescriptor);
                } catch (IOException e) {
                    Log.w(LOGTAG, "createPdfRenderer()", e);
                    return false;
                }
            }
            this.m_ImageCount = this.m_PdfRenderer.getPageCount();
            return true;
        } catch (FileNotFoundException e2) {
            Log.w(LOGTAG, "createPdfRenderer()", e2);
            return false;
        }
    }

    private void closePdfRenderer() {
        PdfRenderer pdfRenderer = this.m_PdfRenderer;
        if (pdfRenderer != null) {
            pdfRenderer.close();
            this.m_PdfRenderer = null;
        }
        ParcelFileDescriptor parcelFileDescriptor = this.m_FileDescriptor;
        if (parcelFileDescriptor != null) {
            try {
                parcelFileDescriptor.close();
            } catch (IOException e) {
                Log.w(LOGTAG, "closePdfRenderer()", e);
            }
            this.m_FileDescriptor = null;
        }
    }

    public void close() {
        this.m_Mode = 0;
        this.m_Path = null;
        this.m_Uri = null;
        this.m_ImageCount = 0;
        closePdfRenderer();
    }

    private Bitmap addWhiteBg(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        canvas.drawBitmap(bitmap, Sequence.PPQ, Sequence.PPQ, (Paint) null);
        return createBitmap;
    }

    private int getOrientationDegreesFromFile(String str) {
        try {
            return exifToDegrees(new ExifInterface(str).getAttributeInt("Orientation", 0));
        } catch (IOException e) {
            Log.w(LOGTAG, "getOrientationDegreesFromFile()", e);
            return 0;
        }
    }

    private int getOrientationDegreesFromUri(Uri uri) {
        try {
            InputStream openInputStream = this.m_Context.getContentResolver().openInputStream(uri);
            int attributeInt = new ExifInterface(openInputStream).getAttributeInt("Orientation", 0);
            try {
                openInputStream.close();
            } catch (IOException e) {
                Log.w(LOGTAG, "getOrientationDegreesFromUri()", e);
            }
            return exifToDegrees(attributeInt);
        } catch (IOException e2) {
            Log.w(LOGTAG, "getOrientationDegreesFromUri()", e2);
            return 0;
        }
    }

    private void dump(Bitmap bitmap, int i) {
        Utils.savePng(bitmap, this.m_Context.getExternalFilesDir((String) null).getAbsolutePath() + "/pdf_" + i + ".png");
    }
}
