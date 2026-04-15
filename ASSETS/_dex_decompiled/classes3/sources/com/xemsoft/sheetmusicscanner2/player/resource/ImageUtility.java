package com.xemsoft.sheetmusicscanner2.player.resource;

import android.graphics.Bitmap;
import android.graphics.Color;
import com.xemsoft.sheetmusicscanner2.leptonica.Box;
import com.xemsoft.sheetmusicscanner2.leptonica.JniLeptonica;
import com.xemsoft.sheetmusicscanner2.leptonica.Pix;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_p_Box;
import com.xemsoft.sheetmusicscanner2.leptonica.SWIGTYPE_p_unsigned_int;
import com.xemsoft.sheetmusicscanner2.sources.JniSource;
import com.xemsoft.sheetmusicscanner2.sources.sound;
import com.xemsoft.sheetmusicscanner2.util.Swig;

public class ImageUtility {
    public static Bitmap createSoundImage(sound sound, float f, Pix pix) {
        Box newBOX = Swig.newBOX(sound.getBox());
        int round = Math.round(((float) newBOX.getW()) * f);
        int round2 = Math.round(((float) newBOX.getH()) * f);
        if (round == 0 || round2 == 0) {
            return null;
        }
        Box box = new Box();
        box.setX(Math.round(((float) newBOX.getX()) * f));
        box.setY(Math.round(((float) newBOX.getY()) * f));
        box.setW(round);
        box.setH(round2);
        Pix pixClipRectangle = JniLeptonica.pixClipRectangle(pix, box, (SWIGTYPE_p_p_Box) null);
        box.delete();
        if (pixClipRectangle == null) {
            return null;
        }
        Pix pixScale = JniLeptonica.pixScale(Swig.newPIX(sound.getPix()), f, f);
        JniLeptonica.pixInvert(pixScale, pixScale);
        SWIGTYPE_p_unsigned_int new_uint32p = JniSource.new_uint32p();
        JniLeptonica.composeRGBPixel(255, 255, 255, new_uint32p);
        JniLeptonica.pixPaintThroughMask(pixClipRectangle, pixScale, 0, 0, JniSource.uint32p_value(new_uint32p));
        JniSource.delete_uint32p(new_uint32p);
        Swig.pixDestroy(pixScale);
        Bitmap writeBitmap = JniSource.writeBitmap(pixClipRectangle, true);
        Swig.pixDestroy(pixClipRectangle);
        return writeBitmap;
    }

    private static Bitmap makeTransparent(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        int i = width * height;
        int[] iArr = new int[i];
        bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = iArr[i2];
            int red = Color.red(i3);
            int green = Color.green(i3);
            int blue = Color.blue(i3);
            if (red > 200 && blue > 200 && green > 200) {
                iArr[i2] = Color.alpha(0);
            }
        }
        Bitmap bitmap2 = createBitmap;
        bitmap2.setPixels(iArr, 0, width, 0, 0, width, height);
        return bitmap2;
    }
}
