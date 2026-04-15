package com.xemsoft.sheetmusicscanner2.leptonica;

public class JniLeptonica implements JniLeptonicaConstants {
    public static Pix pixBackgroundNormSimple(Pix pix, Pix pix2, Pix pix3) {
        long pixBackgroundNormSimple = JniLeptonicaJNI.pixBackgroundNormSimple(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixBackgroundNormSimple == 0) {
            return null;
        }
        return new Pix(pixBackgroundNormSimple, false);
    }

    public static Pix pixBackgroundNorm(Pix pix, Pix pix2, Pix pix3, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long pixBackgroundNorm = JniLeptonicaJNI.pixBackgroundNorm(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, i3, i4, i5, i6, i7);
        if (pixBackgroundNorm == 0) {
            return null;
        }
        return new Pix(pixBackgroundNorm, false);
    }

    public static Pix pixBackgroundNormMorph(Pix pix, Pix pix2, int i, int i2, int i3) {
        long pixBackgroundNormMorph = JniLeptonicaJNI.pixBackgroundNormMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3);
        if (pixBackgroundNormMorph == 0) {
            return null;
        }
        return new Pix(pixBackgroundNormMorph, false);
    }

    public static int pixBackgroundNormGrayArray(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5, int i6, int i7, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixBackgroundNormGrayArray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5, i6, i7, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixBackgroundNormRGBArrays(Pix pix, Pix pix2, Pix pix3, int i, int i2, int i3, int i4, int i5, int i6, int i7, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixBackgroundNormRGBArrays(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, i3, i4, i5, i6, i7, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static int pixBackgroundNormGrayArrayMorph(Pix pix, Pix pix2, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixBackgroundNormGrayArrayMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixBackgroundNormRGBArraysMorph(Pix pix, Pix pix2, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixBackgroundNormRGBArraysMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static int pixGetBackgroundGrayMap(Pix pix, Pix pix2, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixGetBackgroundGrayMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixGetBackgroundRGBMap(Pix pix, Pix pix2, Pix pix3, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixGetBackgroundRGBMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static int pixGetBackgroundGrayMapMorph(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixGetBackgroundGrayMapMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixGetBackgroundRGBMapMorph(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixGetBackgroundRGBMapMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static int pixFillMapHoles(Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixFillMapHoles(Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static Pix pixExtendByReplication(Pix pix, int i, int i2) {
        long pixExtendByReplication = JniLeptonicaJNI.pixExtendByReplication(Pix.getCPtr(pix), pix, i, i2);
        if (pixExtendByReplication == 0) {
            return null;
        }
        return new Pix(pixExtendByReplication, false);
    }

    public static int pixSmoothConnectedRegions(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSmoothConnectedRegions(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static Pix pixGetInvBackgroundMap(Pix pix, int i, int i2, int i3) {
        long pixGetInvBackgroundMap = JniLeptonicaJNI.pixGetInvBackgroundMap(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixGetInvBackgroundMap == 0) {
            return null;
        }
        return new Pix(pixGetInvBackgroundMap, false);
    }

    public static Pix pixApplyInvBackgroundGrayMap(Pix pix, Pix pix2, int i, int i2) {
        long pixApplyInvBackgroundGrayMap = JniLeptonicaJNI.pixApplyInvBackgroundGrayMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixApplyInvBackgroundGrayMap == 0) {
            return null;
        }
        return new Pix(pixApplyInvBackgroundGrayMap, false);
    }

    public static Pix pixApplyInvBackgroundRGBMap(Pix pix, Pix pix2, Pix pix3, Pix pix4, int i, int i2) {
        long pixApplyInvBackgroundRGBMap = JniLeptonicaJNI.pixApplyInvBackgroundRGBMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, Pix.getCPtr(pix4), pix4, i, i2);
        if (pixApplyInvBackgroundRGBMap == 0) {
            return null;
        }
        return new Pix(pixApplyInvBackgroundRGBMap, false);
    }

    public static Pix pixApplyVariableGrayMap(Pix pix, Pix pix2, int i) {
        long pixApplyVariableGrayMap = JniLeptonicaJNI.pixApplyVariableGrayMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
        if (pixApplyVariableGrayMap == 0) {
            return null;
        }
        return new Pix(pixApplyVariableGrayMap, false);
    }

    public static Pix pixGlobalNormRGB(Pix pix, Pix pix2, int i, int i2, int i3, int i4) {
        long pixGlobalNormRGB = JniLeptonicaJNI.pixGlobalNormRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4);
        if (pixGlobalNormRGB == 0) {
            return null;
        }
        return new Pix(pixGlobalNormRGB, false);
    }

    public static Pix pixGlobalNormNoSatRGB(Pix pix, Pix pix2, int i, int i2, int i3, int i4, float f) {
        long pixGlobalNormNoSatRGB = JniLeptonicaJNI.pixGlobalNormNoSatRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, f);
        if (pixGlobalNormNoSatRGB == 0) {
            return null;
        }
        return new Pix(pixGlobalNormNoSatRGB, false);
    }

    public static int pixThresholdSpreadNorm(Pix pix, int i, int i2, int i3, int i4, float f, int i5, int i6, int i7, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixThresholdSpreadNorm(Pix.getCPtr(pix), pix, i, i2, i3, i4, f, i5, i6, i7, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static Pix pixBackgroundNormFlex(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixBackgroundNormFlex = JniLeptonicaJNI.pixBackgroundNormFlex(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixBackgroundNormFlex == 0) {
            return null;
        }
        return new Pix(pixBackgroundNormFlex, false);
    }

    public static Pix pixContrastNorm(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5) {
        long pixContrastNorm = JniLeptonicaJNI.pixContrastNorm(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5);
        if (pixContrastNorm == 0) {
            return null;
        }
        return new Pix(pixContrastNorm, false);
    }

    public static int pixMinMaxTiles(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixMinMaxTiles(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static int pixSetLowContrast(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSetLowContrast(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static Pix pixLinearTRCTiled(Pix pix, Pix pix2, int i, int i2, Pix pix3, Pix pix4) {
        long pixLinearTRCTiled = JniLeptonicaJNI.pixLinearTRCTiled(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, Pix.getCPtr(pix3), pix3, Pix.getCPtr(pix4), pix4);
        if (pixLinearTRCTiled == 0) {
            return null;
        }
        return new Pix(pixLinearTRCTiled, false);
    }

    public static Pix pixAffineSampledPta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixAffineSampledPta = JniLeptonicaJNI.pixAffineSampledPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixAffineSampledPta == 0) {
            return null;
        }
        return new Pix(pixAffineSampledPta, false);
    }

    public static Pix pixAffineSampled(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixAffineSampled = JniLeptonicaJNI.pixAffineSampled(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixAffineSampled == 0) {
            return null;
        }
        return new Pix(pixAffineSampled, false);
    }

    public static Pix pixAffinePta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixAffinePta = JniLeptonicaJNI.pixAffinePta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixAffinePta == 0) {
            return null;
        }
        return new Pix(pixAffinePta, false);
    }

    public static Pix pixAffine(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixAffine = JniLeptonicaJNI.pixAffine(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixAffine == 0) {
            return null;
        }
        return new Pix(pixAffine, false);
    }

    public static Pix pixAffinePtaColor(Pix pix, Pta pta, Pta pta2, long j) {
        long pixAffinePtaColor = JniLeptonicaJNI.pixAffinePtaColor(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, j);
        if (pixAffinePtaColor == 0) {
            return null;
        }
        return new Pix(pixAffinePtaColor, false);
    }

    public static Pix pixAffineColor(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, long j) {
        long pixAffineColor = JniLeptonicaJNI.pixAffineColor(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), j);
        if (pixAffineColor == 0) {
            return null;
        }
        return new Pix(pixAffineColor, false);
    }

    public static Pix pixAffinePtaGray(Pix pix, Pta pta, Pta pta2, short s) {
        long pixAffinePtaGray = JniLeptonicaJNI.pixAffinePtaGray(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, s);
        if (pixAffinePtaGray == 0) {
            return null;
        }
        return new Pix(pixAffinePtaGray, false);
    }

    public static Pix pixAffineGray(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, short s) {
        long pixAffineGray = JniLeptonicaJNI.pixAffineGray(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), s);
        if (pixAffineGray == 0) {
            return null;
        }
        return new Pix(pixAffineGray, false);
    }

    public static Pix pixAffinePtaWithAlpha(Pix pix, Pta pta, Pta pta2, Pix pix2, float f, int i) {
        long pixAffinePtaWithAlpha = JniLeptonicaJNI.pixAffinePtaWithAlpha(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, Pix.getCPtr(pix2), pix2, f, i);
        if (pixAffinePtaWithAlpha == 0) {
            return null;
        }
        return new Pix(pixAffinePtaWithAlpha, false);
    }

    public static int getAffineXformCoeffs(Pta pta, Pta pta2, SWIGTYPE_p_p_float sWIGTYPE_p_p_float) {
        return JniLeptonicaJNI.getAffineXformCoeffs(Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float));
    }

    public static int affineInvertXform(SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_float sWIGTYPE_p_p_float) {
        return JniLeptonicaJNI.affineInvertXform(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float));
    }

    public static int affineXformSampledPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.affineXformSampledPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int affineXformPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.affineXformPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int linearInterpolatePixelColor(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, float f, float f2, long j, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2) {
        return JniLeptonicaJNI.linearInterpolatePixelColor(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, f, f2, j, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2));
    }

    public static int linearInterpolatePixelGray(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, float f, float f2, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.linearInterpolatePixelGray(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, f, f2, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int gaussjordan(SWIGTYPE_p_p_float sWIGTYPE_p_p_float, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        return JniLeptonicaJNI.gaussjordan(SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
    }

    public static Pix pixAffineSequential(Pix pix, Pta pta, Pta pta2, int i, int i2) {
        long pixAffineSequential = JniLeptonicaJNI.pixAffineSequential(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i, i2);
        if (pixAffineSequential == 0) {
            return null;
        }
        return new Pix(pixAffineSequential, false);
    }

    public static SWIGTYPE_p_float createMatrix2dTranslate(float f, float f2) {
        long createMatrix2dTranslate = JniLeptonicaJNI.createMatrix2dTranslate(f, f2);
        if (createMatrix2dTranslate == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(createMatrix2dTranslate, false);
    }

    public static SWIGTYPE_p_float createMatrix2dScale(float f, float f2) {
        long createMatrix2dScale = JniLeptonicaJNI.createMatrix2dScale(f, f2);
        if (createMatrix2dScale == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(createMatrix2dScale, false);
    }

    public static SWIGTYPE_p_float createMatrix2dRotate(float f, float f2, float f3) {
        long createMatrix2dRotate = JniLeptonicaJNI.createMatrix2dRotate(f, f2, f3);
        if (createMatrix2dRotate == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(createMatrix2dRotate, false);
    }

    public static Pta ptaTranslate(Pta pta, float f, float f2) {
        long ptaTranslate = JniLeptonicaJNI.ptaTranslate(Pta.getCPtr(pta), pta, f, f2);
        if (ptaTranslate == 0) {
            return null;
        }
        return new Pta(ptaTranslate, false);
    }

    public static Pta ptaScale(Pta pta, float f, float f2) {
        long ptaScale = JniLeptonicaJNI.ptaScale(Pta.getCPtr(pta), pta, f, f2);
        if (ptaScale == 0) {
            return null;
        }
        return new Pta(ptaScale, false);
    }

    public static Pta ptaRotate(Pta pta, float f, float f2, float f3) {
        long ptaRotate = JniLeptonicaJNI.ptaRotate(Pta.getCPtr(pta), pta, f, f2, f3);
        if (ptaRotate == 0) {
            return null;
        }
        return new Pta(ptaRotate, false);
    }

    public static Boxa boxaTranslate(Boxa boxa, float f, float f2) {
        long boxaTranslate = JniLeptonicaJNI.boxaTranslate(Boxa.getCPtr(boxa), boxa, f, f2);
        if (boxaTranslate == 0) {
            return null;
        }
        return new Boxa(boxaTranslate, false);
    }

    public static Boxa boxaScale(Boxa boxa, float f, float f2) {
        long boxaScale = JniLeptonicaJNI.boxaScale(Boxa.getCPtr(boxa), boxa, f, f2);
        if (boxaScale == 0) {
            return null;
        }
        return new Boxa(boxaScale, false);
    }

    public static Boxa boxaRotate(Boxa boxa, float f, float f2, float f3) {
        long boxaRotate = JniLeptonicaJNI.boxaRotate(Boxa.getCPtr(boxa), boxa, f, f2, f3);
        if (boxaRotate == 0) {
            return null;
        }
        return new Boxa(boxaRotate, false);
    }

    public static Pta ptaAffineTransform(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float) {
        long ptaAffineTransform = JniLeptonicaJNI.ptaAffineTransform(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
        if (ptaAffineTransform == 0) {
            return null;
        }
        return new Pta(ptaAffineTransform, false);
    }

    public static Boxa boxaAffineTransform(Boxa boxa, SWIGTYPE_p_float sWIGTYPE_p_float) {
        long boxaAffineTransform = JniLeptonicaJNI.boxaAffineTransform(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
        if (boxaAffineTransform == 0) {
            return null;
        }
        return new Boxa(boxaAffineTransform, false);
    }

    public static int l_productMatVec(SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, int i) {
        return JniLeptonicaJNI.l_productMatVec(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), i);
    }

    public static int l_productMat2(SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, int i) {
        return JniLeptonicaJNI.l_productMat2(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), i);
    }

    public static int l_productMat3(SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, int i) {
        return JniLeptonicaJNI.l_productMat3(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), i);
    }

    public static int l_productMat4(SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_float sWIGTYPE_p_float5, int i) {
        return JniLeptonicaJNI.l_productMat4(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float5), i);
    }

    public static int l_getDataBit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataBit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataBit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        JniLeptonicaJNI.l_setDataBit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_clearDataBit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        JniLeptonicaJNI.l_clearDataBit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataBitVal(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataBitVal(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static int l_getDataDibit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataDibit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataDibit(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataDibit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static void l_clearDataDibit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        JniLeptonicaJNI.l_clearDataDibit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static int l_getDataQbit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataQbit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataQbit(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataQbit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static void l_clearDataQbit(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        JniLeptonicaJNI.l_clearDataQbit(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static int l_getDataByte(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataByte(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataByte(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataByte(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static int l_getDataTwoBytes(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataTwoBytes(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataTwoBytes(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataTwoBytes(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static int l_getDataFourBytes(SWIGTYPE_p_void sWIGTYPE_p_void, int i) {
        return JniLeptonicaJNI.l_getDataFourBytes(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i);
    }

    public static void l_setDataFourBytes(SWIGTYPE_p_void sWIGTYPE_p_void, int i, int i2) {
        JniLeptonicaJNI.l_setDataFourBytes(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i, i2);
    }

    public static String barcodeDispatchDecoder(String str, int i, int i2) {
        return JniLeptonicaJNI.barcodeDispatchDecoder(str, i, i2);
    }

    public static int barcodeFormatIsSupported(int i) {
        return JniLeptonicaJNI.barcodeFormatIsSupported(i);
    }

    public static Numa pixFindBaselines(Pix pix, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, int i) {
        long pixFindBaselines = JniLeptonicaJNI.pixFindBaselines(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), i);
        if (pixFindBaselines == 0) {
            return null;
        }
        return new Numa(pixFindBaselines, false);
    }

    public static Pix pixDeskewLocal(Pix pix, int i, int i2, int i3, float f, float f2, float f3) {
        long pixDeskewLocal = JniLeptonicaJNI.pixDeskewLocal(Pix.getCPtr(pix), pix, i, i2, i3, f, f2, f3);
        if (pixDeskewLocal == 0) {
            return null;
        }
        return new Pix(pixDeskewLocal, false);
    }

    public static int pixGetLocalSkewTransform(Pix pix, int i, int i2, int i3, float f, float f2, float f3, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta2) {
        return JniLeptonicaJNI.pixGetLocalSkewTransform(Pix.getCPtr(pix), pix, i, i2, i3, f, f2, f3, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta2));
    }

    public static Numa pixGetLocalSkewAngles(Pix pix, int i, int i2, int i3, float f, float f2, float f3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        long pixGetLocalSkewAngles = JniLeptonicaJNI.pixGetLocalSkewAngles(Pix.getCPtr(pix), pix, i, i2, i3, f, f2, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
        if (pixGetLocalSkewAngles == 0) {
            return null;
        }
        return new Numa(pixGetLocalSkewAngles, false);
    }

    public static ByteBuffer bbufferCreate(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i) {
        long bbufferCreate = JniLeptonicaJNI.bbufferCreate(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i);
        if (bbufferCreate == 0) {
            return null;
        }
        return new ByteBuffer(bbufferCreate, false);
    }

    public static void bbufferDestroy(SWIGTYPE_p_p_ByteBuffer sWIGTYPE_p_p_ByteBuffer) {
        JniLeptonicaJNI.bbufferDestroy(SWIGTYPE_p_p_ByteBuffer.getCPtr(sWIGTYPE_p_p_ByteBuffer));
    }

    public static SWIGTYPE_p_unsigned_char bbufferDestroyAndSaveData(SWIGTYPE_p_p_ByteBuffer sWIGTYPE_p_p_ByteBuffer, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long bbufferDestroyAndSaveData = JniLeptonicaJNI.bbufferDestroyAndSaveData(SWIGTYPE_p_p_ByteBuffer.getCPtr(sWIGTYPE_p_p_ByteBuffer), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (bbufferDestroyAndSaveData == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(bbufferDestroyAndSaveData, false);
    }

    public static int bbufferRead(ByteBuffer byteBuffer, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i) {
        return JniLeptonicaJNI.bbufferRead(ByteBuffer.getCPtr(byteBuffer), byteBuffer, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i);
    }

    public static int bbufferReadStream(ByteBuffer byteBuffer, SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        return JniLeptonicaJNI.bbufferReadStream(ByteBuffer.getCPtr(byteBuffer), byteBuffer, SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
    }

    public static int bbufferExtendArray(ByteBuffer byteBuffer, int i) {
        return JniLeptonicaJNI.bbufferExtendArray(ByteBuffer.getCPtr(byteBuffer), byteBuffer, i);
    }

    public static int bbufferWrite(ByteBuffer byteBuffer, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.bbufferWrite(ByteBuffer.getCPtr(byteBuffer), byteBuffer, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int bbufferWriteStream(ByteBuffer byteBuffer, SWIGTYPE_p_FILE sWIGTYPE_p_FILE, long j, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.bbufferWriteStream(ByteBuffer.getCPtr(byteBuffer), byteBuffer, SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), j, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int bbufferBytesToWrite(ByteBuffer byteBuffer, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.bbufferBytesToWrite(ByteBuffer.getCPtr(byteBuffer), byteBuffer, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static Pix pixBilateral(Pix pix, float f, float f2, int i, int i2) {
        long pixBilateral = JniLeptonicaJNI.pixBilateral(Pix.getCPtr(pix), pix, f, f2, i, i2);
        if (pixBilateral == 0) {
            return null;
        }
        return new Pix(pixBilateral, false);
    }

    public static Pix pixBilateralGray(Pix pix, float f, float f2, int i, int i2) {
        long pixBilateralGray = JniLeptonicaJNI.pixBilateralGray(Pix.getCPtr(pix), pix, f, f2, i, i2);
        if (pixBilateralGray == 0) {
            return null;
        }
        return new Pix(pixBilateralGray, false);
    }

    public static Pix pixBilateralExact(Pix pix, L_Kernel l_Kernel, L_Kernel l_Kernel2) {
        long pixBilateralExact = JniLeptonicaJNI.pixBilateralExact(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2);
        if (pixBilateralExact == 0) {
            return null;
        }
        return new Pix(pixBilateralExact, false);
    }

    public static Pix pixBilateralGrayExact(Pix pix, L_Kernel l_Kernel, L_Kernel l_Kernel2) {
        long pixBilateralGrayExact = JniLeptonicaJNI.pixBilateralGrayExact(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2);
        if (pixBilateralGrayExact == 0) {
            return null;
        }
        return new Pix(pixBilateralGrayExact, false);
    }

    public static Pix pixBlockBilateralExact(Pix pix, float f, float f2) {
        long pixBlockBilateralExact = JniLeptonicaJNI.pixBlockBilateralExact(Pix.getCPtr(pix), pix, f, f2);
        if (pixBlockBilateralExact == 0) {
            return null;
        }
        return new Pix(pixBlockBilateralExact, false);
    }

    public static L_Kernel makeRangeKernel(float f) {
        long makeRangeKernel = JniLeptonicaJNI.makeRangeKernel(f);
        if (makeRangeKernel == 0) {
            return null;
        }
        return new L_Kernel(makeRangeKernel, false);
    }

    public static Pix pixBilinearSampledPta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixBilinearSampledPta = JniLeptonicaJNI.pixBilinearSampledPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixBilinearSampledPta == 0) {
            return null;
        }
        return new Pix(pixBilinearSampledPta, false);
    }

    public static Pix pixBilinearSampled(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixBilinearSampled = JniLeptonicaJNI.pixBilinearSampled(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixBilinearSampled == 0) {
            return null;
        }
        return new Pix(pixBilinearSampled, false);
    }

    public static Pix pixBilinearPta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixBilinearPta = JniLeptonicaJNI.pixBilinearPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixBilinearPta == 0) {
            return null;
        }
        return new Pix(pixBilinearPta, false);
    }

    public static Pix pixBilinear(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixBilinear = JniLeptonicaJNI.pixBilinear(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixBilinear == 0) {
            return null;
        }
        return new Pix(pixBilinear, false);
    }

    public static Pix pixBilinearPtaColor(Pix pix, Pta pta, Pta pta2, long j) {
        long pixBilinearPtaColor = JniLeptonicaJNI.pixBilinearPtaColor(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, j);
        if (pixBilinearPtaColor == 0) {
            return null;
        }
        return new Pix(pixBilinearPtaColor, false);
    }

    public static Pix pixBilinearColor(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, long j) {
        long pixBilinearColor = JniLeptonicaJNI.pixBilinearColor(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), j);
        if (pixBilinearColor == 0) {
            return null;
        }
        return new Pix(pixBilinearColor, false);
    }

    public static Pix pixBilinearPtaGray(Pix pix, Pta pta, Pta pta2, short s) {
        long pixBilinearPtaGray = JniLeptonicaJNI.pixBilinearPtaGray(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, s);
        if (pixBilinearPtaGray == 0) {
            return null;
        }
        return new Pix(pixBilinearPtaGray, false);
    }

    public static Pix pixBilinearGray(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, short s) {
        long pixBilinearGray = JniLeptonicaJNI.pixBilinearGray(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), s);
        if (pixBilinearGray == 0) {
            return null;
        }
        return new Pix(pixBilinearGray, false);
    }

    public static Pix pixBilinearPtaWithAlpha(Pix pix, Pta pta, Pta pta2, Pix pix2, float f, int i) {
        long pixBilinearPtaWithAlpha = JniLeptonicaJNI.pixBilinearPtaWithAlpha(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, Pix.getCPtr(pix2), pix2, f, i);
        if (pixBilinearPtaWithAlpha == 0) {
            return null;
        }
        return new Pix(pixBilinearPtaWithAlpha, false);
    }

    public static int getBilinearXformCoeffs(Pta pta, Pta pta2, SWIGTYPE_p_p_float sWIGTYPE_p_p_float) {
        return JniLeptonicaJNI.getBilinearXformCoeffs(Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float));
    }

    public static int bilinearXformSampledPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.bilinearXformSampledPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int bilinearXformPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.bilinearXformPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int pixOtsuAdaptiveThreshold(Pix pix, int i, int i2, int i3, int i4, float f, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixOtsuAdaptiveThreshold(Pix.getCPtr(pix), pix, i, i2, i3, i4, f, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static Pix pixOtsuThreshOnBackgroundNorm(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5, int i6, int i7, float f, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixOtsuThreshOnBackgroundNorm = JniLeptonicaJNI.pixOtsuThreshOnBackgroundNorm(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5, i6, i7, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixOtsuThreshOnBackgroundNorm == 0) {
            return null;
        }
        return new Pix(pixOtsuThreshOnBackgroundNorm, false);
    }

    public static Pix pixMaskedThreshOnBackgroundNorm(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5, int i6, float f, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixMaskedThreshOnBackgroundNorm = JniLeptonicaJNI.pixMaskedThreshOnBackgroundNorm(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5, i6, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixMaskedThreshOnBackgroundNorm == 0) {
            return null;
        }
        return new Pix(pixMaskedThreshOnBackgroundNorm, false);
    }

    public static int pixSauvolaBinarizeTiled(Pix pix, int i, float f, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixSauvolaBinarizeTiled(Pix.getCPtr(pix), pix, i, f, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static int pixSauvolaBinarize(Pix pix, int i, float f, int i2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix4) {
        return JniLeptonicaJNI.pixSauvolaBinarize(Pix.getCPtr(pix), pix, i, f, i2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix4));
    }

    public static Pix pixSauvolaGetThreshold(Pix pix, Pix pix2, float f, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixSauvolaGetThreshold = JniLeptonicaJNI.pixSauvolaGetThreshold(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixSauvolaGetThreshold == 0) {
            return null;
        }
        return new Pix(pixSauvolaGetThreshold, false);
    }

    public static Pix pixApplyLocalThreshold(Pix pix, Pix pix2, int i) {
        long pixApplyLocalThreshold = JniLeptonicaJNI.pixApplyLocalThreshold(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
        if (pixApplyLocalThreshold == 0) {
            return null;
        }
        return new Pix(pixApplyLocalThreshold, false);
    }

    public static int pixThresholdByConnComp(Pix pix, Pix pix2, int i, int i2, int i3, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, int i4) {
        return JniLeptonicaJNI.pixThresholdByConnComp(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), i4);
    }

    public static Pix pixExpandBinaryReplicate(Pix pix, int i) {
        long pixExpandBinaryReplicate = JniLeptonicaJNI.pixExpandBinaryReplicate(Pix.getCPtr(pix), pix, i);
        if (pixExpandBinaryReplicate == 0) {
            return null;
        }
        return new Pix(pixExpandBinaryReplicate, false);
    }

    public static Pix pixExpandBinaryPower2(Pix pix, int i) {
        long pixExpandBinaryPower2 = JniLeptonicaJNI.pixExpandBinaryPower2(Pix.getCPtr(pix), pix, i);
        if (pixExpandBinaryPower2 == 0) {
            return null;
        }
        return new Pix(pixExpandBinaryPower2, false);
    }

    public static Pix pixReduceBinary2(Pix pix, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        long pixReduceBinary2 = JniLeptonicaJNI.pixReduceBinary2(Pix.getCPtr(pix), pix, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
        if (pixReduceBinary2 == 0) {
            return null;
        }
        return new Pix(pixReduceBinary2, false);
    }

    public static Pix pixReduceRankBinaryCascade(Pix pix, int i, int i2, int i3, int i4) {
        long pixReduceRankBinaryCascade = JniLeptonicaJNI.pixReduceRankBinaryCascade(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixReduceRankBinaryCascade == 0) {
            return null;
        }
        return new Pix(pixReduceRankBinaryCascade, false);
    }

    public static Pix pixReduceRankBinary2(Pix pix, int i, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        long pixReduceRankBinary2 = JniLeptonicaJNI.pixReduceRankBinary2(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
        if (pixReduceRankBinary2 == 0) {
            return null;
        }
        return new Pix(pixReduceRankBinary2, false);
    }

    public static SWIGTYPE_p_unsigned_char makeSubsampleTab2x() {
        long makeSubsampleTab2x = JniLeptonicaJNI.makeSubsampleTab2x();
        if (makeSubsampleTab2x == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeSubsampleTab2x, false);
    }

    public static Pix pixBlend(Pix pix, Pix pix2, int i, int i2, float f) {
        long pixBlend = JniLeptonicaJNI.pixBlend(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f);
        if (pixBlend == 0) {
            return null;
        }
        return new Pix(pixBlend, false);
    }

    public static Pix pixBlendMask(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f, int i3) {
        long pixBlendMask = JniLeptonicaJNI.pixBlendMask(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f, i3);
        if (pixBlendMask == 0) {
            return null;
        }
        return new Pix(pixBlendMask, false);
    }

    public static Pix pixBlendGray(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f, int i3, int i4, long j) {
        long pixBlendGray = JniLeptonicaJNI.pixBlendGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f, i3, i4, j);
        if (pixBlendGray == 0) {
            return null;
        }
        return new Pix(pixBlendGray, false);
    }

    public static Pix pixBlendGrayInverse(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f) {
        long pixBlendGrayInverse = JniLeptonicaJNI.pixBlendGrayInverse(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f);
        if (pixBlendGrayInverse == 0) {
            return null;
        }
        return new Pix(pixBlendGrayInverse, false);
    }

    public static Pix pixBlendColor(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f, int i3, long j) {
        long pixBlendColor = JniLeptonicaJNI.pixBlendColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f, i3, j);
        if (pixBlendColor == 0) {
            return null;
        }
        return new Pix(pixBlendColor, false);
    }

    public static Pix pixBlendColorByChannel(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f, float f2, float f3, int i3, long j) {
        long pixBlendColorByChannel = JniLeptonicaJNI.pixBlendColorByChannel(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f, f2, f3, i3, j);
        if (pixBlendColorByChannel == 0) {
            return null;
        }
        return new Pix(pixBlendColorByChannel, false);
    }

    public static Pix pixBlendGrayAdapt(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f, int i3) {
        long pixBlendGrayAdapt = JniLeptonicaJNI.pixBlendGrayAdapt(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f, i3);
        if (pixBlendGrayAdapt == 0) {
            return null;
        }
        return new Pix(pixBlendGrayAdapt, false);
    }

    public static Pix pixFadeWithGray(Pix pix, Pix pix2, float f, int i) {
        long pixFadeWithGray = JniLeptonicaJNI.pixFadeWithGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixFadeWithGray == 0) {
            return null;
        }
        return new Pix(pixFadeWithGray, false);
    }

    public static Pix pixBlendHardLight(Pix pix, Pix pix2, Pix pix3, int i, int i2, float f) {
        long pixBlendHardLight = JniLeptonicaJNI.pixBlendHardLight(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, f);
        if (pixBlendHardLight == 0) {
            return null;
        }
        return new Pix(pixBlendHardLight, false);
    }

    public static int pixBlendCmap(Pix pix, Pix pix2, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixBlendCmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3);
    }

    public static Pix pixBlendWithGrayMask(Pix pix, Pix pix2, Pix pix3, int i, int i2) {
        long pixBlendWithGrayMask = JniLeptonicaJNI.pixBlendWithGrayMask(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2);
        if (pixBlendWithGrayMask == 0) {
            return null;
        }
        return new Pix(pixBlendWithGrayMask, false);
    }

    public static Pix pixBlendBackgroundToColor(Pix pix, Pix pix2, Box box, long j, float f, int i, int i2) {
        long pixBlendBackgroundToColor = JniLeptonicaJNI.pixBlendBackgroundToColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Box.getCPtr(box), box, j, f, i, i2);
        if (pixBlendBackgroundToColor == 0) {
            return null;
        }
        return new Pix(pixBlendBackgroundToColor, false);
    }

    public static Pix pixMultiplyByColor(Pix pix, Pix pix2, Box box, long j) {
        long pixMultiplyByColor = JniLeptonicaJNI.pixMultiplyByColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Box.getCPtr(box), box, j);
        if (pixMultiplyByColor == 0) {
            return null;
        }
        return new Pix(pixMultiplyByColor, false);
    }

    public static Pix pixAlphaBlendUniform(Pix pix, long j) {
        long pixAlphaBlendUniform = JniLeptonicaJNI.pixAlphaBlendUniform(Pix.getCPtr(pix), pix, j);
        if (pixAlphaBlendUniform == 0) {
            return null;
        }
        return new Pix(pixAlphaBlendUniform, false);
    }

    public static Pix pixAddAlphaToBlend(Pix pix, float f, int i) {
        long pixAddAlphaToBlend = JniLeptonicaJNI.pixAddAlphaToBlend(Pix.getCPtr(pix), pix, f, i);
        if (pixAddAlphaToBlend == 0) {
            return null;
        }
        return new Pix(pixAddAlphaToBlend, false);
    }

    public static Pix pixSetAlphaOverWhite(Pix pix) {
        long pixSetAlphaOverWhite = JniLeptonicaJNI.pixSetAlphaOverWhite(Pix.getCPtr(pix), pix);
        if (pixSetAlphaOverWhite == 0) {
            return null;
        }
        return new Pix(pixSetAlphaOverWhite, false);
    }

    public static L_Bmf bmfCreate(String str, int i) {
        long bmfCreate = JniLeptonicaJNI.bmfCreate(str, i);
        if (bmfCreate == 0) {
            return null;
        }
        return new L_Bmf(bmfCreate, false);
    }

    public static void bmfDestroy(SWIGTYPE_p_p_L_Bmf sWIGTYPE_p_p_L_Bmf) {
        JniLeptonicaJNI.bmfDestroy(SWIGTYPE_p_p_L_Bmf.getCPtr(sWIGTYPE_p_p_L_Bmf));
    }

    public static Pix bmfGetPix(L_Bmf l_Bmf, char c) {
        long bmfGetPix = JniLeptonicaJNI.bmfGetPix(L_Bmf.getCPtr(l_Bmf), l_Bmf, c);
        if (bmfGetPix == 0) {
            return null;
        }
        return new Pix(bmfGetPix, false);
    }

    public static int bmfGetWidth(L_Bmf l_Bmf, char c, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.bmfGetWidth(L_Bmf.getCPtr(l_Bmf), l_Bmf, c, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int bmfGetBaseline(L_Bmf l_Bmf, char c, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.bmfGetBaseline(L_Bmf.getCPtr(l_Bmf), l_Bmf, c, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pixa pixaGetFont(String str, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        long pixaGetFont = JniLeptonicaJNI.pixaGetFont(str, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
        if (pixaGetFont == 0) {
            return null;
        }
        return new Pixa(pixaGetFont, false);
    }

    public static int pixaSaveFont(String str, String str2, int i) {
        return JniLeptonicaJNI.pixaSaveFont(str, str2, i);
    }

    public static Pixa pixaGenerateFont(String str, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        long pixaGenerateFont = JniLeptonicaJNI.pixaGenerateFont(str, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
        if (pixaGenerateFont == 0) {
            return null;
        }
        return new Pixa(pixaGenerateFont, false);
    }

    public static Pix pixReadStreamBmp(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamBmp = JniLeptonicaJNI.pixReadStreamBmp(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamBmp == 0) {
            return null;
        }
        return new Pix(pixReadStreamBmp, false);
    }

    public static int pixWriteStreamBmp(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix) {
        return JniLeptonicaJNI.pixWriteStreamBmp(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix);
    }

    public static Pix pixReadMemBmp(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemBmp = JniLeptonicaJNI.pixReadMemBmp(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemBmp == 0) {
            return null;
        }
        return new Pix(pixReadMemBmp, false);
    }

    public static int pixWriteMemBmp(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix) {
        return JniLeptonicaJNI.pixWriteMemBmp(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix);
    }

    public static Box boxCreate(int i, int i2, int i3, int i4) {
        long boxCreate = JniLeptonicaJNI.boxCreate(i, i2, i3, i4);
        if (boxCreate == 0) {
            return null;
        }
        return new Box(boxCreate, false);
    }

    public static Box boxCreateValid(int i, int i2, int i3, int i4) {
        long boxCreateValid = JniLeptonicaJNI.boxCreateValid(i, i2, i3, i4);
        if (boxCreateValid == 0) {
            return null;
        }
        return new Box(boxCreateValid, false);
    }

    public static Box boxCopy(Box box) {
        long boxCopy = JniLeptonicaJNI.boxCopy(Box.getCPtr(box), box);
        if (boxCopy == 0) {
            return null;
        }
        return new Box(boxCopy, false);
    }

    public static Box boxClone(Box box) {
        long boxClone = JniLeptonicaJNI.boxClone(Box.getCPtr(box), box);
        if (boxClone == 0) {
            return null;
        }
        return new Box(boxClone, false);
    }

    public static void boxDestroy(SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        JniLeptonicaJNI.boxDestroy(SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int boxGetGeometry(Box box, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.boxGetGeometry(Box.getCPtr(box), box, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int boxSetGeometry(Box box, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.boxSetGeometry(Box.getCPtr(box), box, i, i2, i3, i4);
    }

    public static int boxGetSideLocation(Box box, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxGetSideLocation(Box.getCPtr(box), box, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxGetRefcount(Box box) {
        return JniLeptonicaJNI.boxGetRefcount(Box.getCPtr(box), box);
    }

    public static int boxChangeRefcount(Box box, int i) {
        return JniLeptonicaJNI.boxChangeRefcount(Box.getCPtr(box), box, i);
    }

    public static int boxIsValid(Box box, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxIsValid(Box.getCPtr(box), box, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Boxa boxaCreate(int i) {
        long boxaCreate = JniLeptonicaJNI.boxaCreate(i);
        if (boxaCreate == 0) {
            return null;
        }
        return new Boxa(boxaCreate, false);
    }

    public static Boxa boxaCopy(Boxa boxa, int i) {
        long boxaCopy = JniLeptonicaJNI.boxaCopy(Boxa.getCPtr(boxa), boxa, i);
        if (boxaCopy == 0) {
            return null;
        }
        return new Boxa(boxaCopy, false);
    }

    public static void boxaDestroy(SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        JniLeptonicaJNI.boxaDestroy(SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
    }

    public static int boxaAddBox(Boxa boxa, Box box, int i) {
        return JniLeptonicaJNI.boxaAddBox(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box, i);
    }

    public static int boxaExtendArray(Boxa boxa) {
        return JniLeptonicaJNI.boxaExtendArray(Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaExtendArrayToSize(Boxa boxa, int i) {
        return JniLeptonicaJNI.boxaExtendArrayToSize(Boxa.getCPtr(boxa), boxa, i);
    }

    public static int boxaGetCount(Boxa boxa) {
        return JniLeptonicaJNI.boxaGetCount(Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaGetValidCount(Boxa boxa) {
        return JniLeptonicaJNI.boxaGetValidCount(Boxa.getCPtr(boxa), boxa);
    }

    public static Box boxaGetBox(Boxa boxa, int i, int i2) {
        long boxaGetBox = JniLeptonicaJNI.boxaGetBox(Boxa.getCPtr(boxa), boxa, i, i2);
        if (boxaGetBox == 0) {
            return null;
        }
        return new Box(boxaGetBox, false);
    }

    public static Box boxaGetValidBox(Boxa boxa, int i, int i2) {
        long boxaGetValidBox = JniLeptonicaJNI.boxaGetValidBox(Boxa.getCPtr(boxa), boxa, i, i2);
        if (boxaGetValidBox == 0) {
            return null;
        }
        return new Box(boxaGetValidBox, false);
    }

    public static int boxaGetBoxGeometry(Boxa boxa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.boxaGetBoxGeometry(Boxa.getCPtr(boxa), boxa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int boxaIsFull(Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxaIsFull(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxaReplaceBox(Boxa boxa, int i, Box box) {
        return JniLeptonicaJNI.boxaReplaceBox(Boxa.getCPtr(boxa), boxa, i, Box.getCPtr(box), box);
    }

    public static int boxaInsertBox(Boxa boxa, int i, Box box) {
        return JniLeptonicaJNI.boxaInsertBox(Boxa.getCPtr(boxa), boxa, i, Box.getCPtr(box), box);
    }

    public static int boxaRemoveBox(Boxa boxa, int i) {
        return JniLeptonicaJNI.boxaRemoveBox(Boxa.getCPtr(boxa), boxa, i);
    }

    public static int boxaRemoveBoxAndSave(Boxa boxa, int i, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.boxaRemoveBoxAndSave(Boxa.getCPtr(boxa), boxa, i, SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static Boxa boxaSaveValid(Boxa boxa, int i) {
        long boxaSaveValid = JniLeptonicaJNI.boxaSaveValid(Boxa.getCPtr(boxa), boxa, i);
        if (boxaSaveValid == 0) {
            return null;
        }
        return new Boxa(boxaSaveValid, false);
    }

    public static int boxaInitFull(Boxa boxa, Box box) {
        return JniLeptonicaJNI.boxaInitFull(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box);
    }

    public static int boxaClear(Boxa boxa) {
        return JniLeptonicaJNI.boxaClear(Boxa.getCPtr(boxa), boxa);
    }

    public static Boxaa boxaaCreate(int i) {
        long boxaaCreate = JniLeptonicaJNI.boxaaCreate(i);
        if (boxaaCreate == 0) {
            return null;
        }
        return new Boxaa(boxaaCreate, false);
    }

    public static Boxaa boxaaCopy(Boxaa boxaa, int i) {
        long boxaaCopy = JniLeptonicaJNI.boxaaCopy(Boxaa.getCPtr(boxaa), boxaa, i);
        if (boxaaCopy == 0) {
            return null;
        }
        return new Boxaa(boxaaCopy, false);
    }

    public static void boxaaDestroy(SWIGTYPE_p_p_Boxaa sWIGTYPE_p_p_Boxaa) {
        JniLeptonicaJNI.boxaaDestroy(SWIGTYPE_p_p_Boxaa.getCPtr(sWIGTYPE_p_p_Boxaa));
    }

    public static int boxaaAddBoxa(Boxaa boxaa, Boxa boxa, int i) {
        return JniLeptonicaJNI.boxaaAddBoxa(Boxaa.getCPtr(boxaa), boxaa, Boxa.getCPtr(boxa), boxa, i);
    }

    public static int boxaaExtendArray(Boxaa boxaa) {
        return JniLeptonicaJNI.boxaaExtendArray(Boxaa.getCPtr(boxaa), boxaa);
    }

    public static int boxaaExtendArrayToSize(Boxaa boxaa, int i) {
        return JniLeptonicaJNI.boxaaExtendArrayToSize(Boxaa.getCPtr(boxaa), boxaa, i);
    }

    public static int boxaaGetCount(Boxaa boxaa) {
        return JniLeptonicaJNI.boxaaGetCount(Boxaa.getCPtr(boxaa), boxaa);
    }

    public static int boxaaGetBoxCount(Boxaa boxaa) {
        return JniLeptonicaJNI.boxaaGetBoxCount(Boxaa.getCPtr(boxaa), boxaa);
    }

    public static Boxa boxaaGetBoxa(Boxaa boxaa, int i, int i2) {
        long boxaaGetBoxa = JniLeptonicaJNI.boxaaGetBoxa(Boxaa.getCPtr(boxaa), boxaa, i, i2);
        if (boxaaGetBoxa == 0) {
            return null;
        }
        return new Boxa(boxaaGetBoxa, false);
    }

    public static Box boxaaGetBox(Boxaa boxaa, int i, int i2, int i3) {
        long boxaaGetBox = JniLeptonicaJNI.boxaaGetBox(Boxaa.getCPtr(boxaa), boxaa, i, i2, i3);
        if (boxaaGetBox == 0) {
            return null;
        }
        return new Box(boxaaGetBox, false);
    }

    public static int boxaaInitFull(Boxaa boxaa, Boxa boxa) {
        return JniLeptonicaJNI.boxaaInitFull(Boxaa.getCPtr(boxaa), boxaa, Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaaExtendWithInit(Boxaa boxaa, int i, Boxa boxa) {
        return JniLeptonicaJNI.boxaaExtendWithInit(Boxaa.getCPtr(boxaa), boxaa, i, Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaaReplaceBoxa(Boxaa boxaa, int i, Boxa boxa) {
        return JniLeptonicaJNI.boxaaReplaceBoxa(Boxaa.getCPtr(boxaa), boxaa, i, Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaaInsertBoxa(Boxaa boxaa, int i, Boxa boxa) {
        return JniLeptonicaJNI.boxaaInsertBoxa(Boxaa.getCPtr(boxaa), boxaa, i, Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaaRemoveBoxa(Boxaa boxaa, int i) {
        return JniLeptonicaJNI.boxaaRemoveBoxa(Boxaa.getCPtr(boxaa), boxaa, i);
    }

    public static int boxaaAddBox(Boxaa boxaa, int i, Box box, int i2) {
        return JniLeptonicaJNI.boxaaAddBox(Boxaa.getCPtr(boxaa), boxaa, i, Box.getCPtr(box), box, i2);
    }

    public static Boxaa boxaaReadFromFiles(String str, String str2, int i, int i2) {
        long boxaaReadFromFiles = JniLeptonicaJNI.boxaaReadFromFiles(str, str2, i, i2);
        if (boxaaReadFromFiles == 0) {
            return null;
        }
        return new Boxaa(boxaaReadFromFiles, false);
    }

    public static Boxaa boxaaRead(String str) {
        long boxaaRead = JniLeptonicaJNI.boxaaRead(str);
        if (boxaaRead == 0) {
            return null;
        }
        return new Boxaa(boxaaRead, false);
    }

    public static Boxaa boxaaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long boxaaReadStream = JniLeptonicaJNI.boxaaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (boxaaReadStream == 0) {
            return null;
        }
        return new Boxaa(boxaaReadStream, false);
    }

    public static int boxaaWrite(String str, Boxaa boxaa) {
        return JniLeptonicaJNI.boxaaWrite(str, Boxaa.getCPtr(boxaa), boxaa);
    }

    public static int boxaaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Boxaa boxaa) {
        return JniLeptonicaJNI.boxaaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Boxaa.getCPtr(boxaa), boxaa);
    }

    public static Boxa boxaRead(String str) {
        long boxaRead = JniLeptonicaJNI.boxaRead(str);
        if (boxaRead == 0) {
            return null;
        }
        return new Boxa(boxaRead, false);
    }

    public static Boxa boxaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long boxaReadStream = JniLeptonicaJNI.boxaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (boxaReadStream == 0) {
            return null;
        }
        return new Boxa(boxaReadStream, false);
    }

    public static Boxa boxaReadMem(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long boxaReadMem = JniLeptonicaJNI.boxaReadMem(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (boxaReadMem == 0) {
            return null;
        }
        return new Boxa(boxaReadMem, false);
    }

    public static int boxaWrite(String str, Boxa boxa) {
        return JniLeptonicaJNI.boxaWrite(str, Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Boxa boxa) {
        return JniLeptonicaJNI.boxaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Boxa.getCPtr(boxa), boxa);
    }

    public static int boxaWriteMem(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Boxa boxa) {
        return JniLeptonicaJNI.boxaWriteMem(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Boxa.getCPtr(boxa), boxa);
    }

    public static int boxPrintStreamInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Box box) {
        return JniLeptonicaJNI.boxPrintStreamInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Box.getCPtr(box), box);
    }

    public static int boxContains(Box box, Box box2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxContains(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxIntersects(Box box, Box box2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxIntersects(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Boxa boxaContainedInBox(Boxa boxa, Box box) {
        long boxaContainedInBox = JniLeptonicaJNI.boxaContainedInBox(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box);
        if (boxaContainedInBox == 0) {
            return null;
        }
        return new Boxa(boxaContainedInBox, false);
    }

    public static Boxa boxaIntersectsBox(Boxa boxa, Box box) {
        long boxaIntersectsBox = JniLeptonicaJNI.boxaIntersectsBox(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box);
        if (boxaIntersectsBox == 0) {
            return null;
        }
        return new Boxa(boxaIntersectsBox, false);
    }

    public static Boxa boxaClipToBox(Boxa boxa, Box box) {
        long boxaClipToBox = JniLeptonicaJNI.boxaClipToBox(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box);
        if (boxaClipToBox == 0) {
            return null;
        }
        return new Boxa(boxaClipToBox, false);
    }

    public static Boxa boxaCombineOverlaps(Boxa boxa) {
        long boxaCombineOverlaps = JniLeptonicaJNI.boxaCombineOverlaps(Boxa.getCPtr(boxa), boxa);
        if (boxaCombineOverlaps == 0) {
            return null;
        }
        return new Boxa(boxaCombineOverlaps, false);
    }

    public static Box boxOverlapRegion(Box box, Box box2) {
        long boxOverlapRegion = JniLeptonicaJNI.boxOverlapRegion(Box.getCPtr(box), box, Box.getCPtr(box2), box2);
        if (boxOverlapRegion == 0) {
            return null;
        }
        return new Box(boxOverlapRegion, false);
    }

    public static Box boxBoundingRegion(Box box, Box box2) {
        long boxBoundingRegion = JniLeptonicaJNI.boxBoundingRegion(Box.getCPtr(box), box, Box.getCPtr(box2), box2);
        if (boxBoundingRegion == 0) {
            return null;
        }
        return new Box(boxBoundingRegion, false);
    }

    public static int boxOverlapFraction(Box box, Box box2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.boxOverlapFraction(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int boxOverlapArea(Box box, Box box2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxOverlapArea(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Boxa boxaHandleOverlaps(Boxa boxa, int i, int i2, float f, float f2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        long boxaHandleOverlaps = JniLeptonicaJNI.boxaHandleOverlaps(Boxa.getCPtr(boxa), boxa, i, i2, f, f2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
        if (boxaHandleOverlaps == 0) {
            return null;
        }
        return new Boxa(boxaHandleOverlaps, false);
    }

    public static int boxSeparationDistance(Box box, Box box2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.boxSeparationDistance(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int boxContainsPt(Box box, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxContainsPt(Box.getCPtr(box), box, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Box boxaGetNearestToPt(Boxa boxa, int i, int i2) {
        long boxaGetNearestToPt = JniLeptonicaJNI.boxaGetNearestToPt(Boxa.getCPtr(boxa), boxa, i, i2);
        if (boxaGetNearestToPt == 0) {
            return null;
        }
        return new Box(boxaGetNearestToPt, false);
    }

    public static int boxGetCenter(Box box, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.boxGetCenter(Box.getCPtr(box), box, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int boxIntersectByLine(Box box, int i, int i2, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.boxIntersectByLine(Box.getCPtr(box), box, i, i2, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static Box boxClipToRectangle(Box box, int i, int i2) {
        long boxClipToRectangle = JniLeptonicaJNI.boxClipToRectangle(Box.getCPtr(box), box, i, i2);
        if (boxClipToRectangle == 0) {
            return null;
        }
        return new Box(boxClipToRectangle, false);
    }

    public static int boxClipToRectangleParams(Box box, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.boxClipToRectangleParams(Box.getCPtr(box), box, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static Box boxRelocateOneSide(Box box, Box box2, int i, int i2) {
        long boxRelocateOneSide = JniLeptonicaJNI.boxRelocateOneSide(Box.getCPtr(box), box, Box.getCPtr(box2), box2, i, i2);
        if (boxRelocateOneSide == 0) {
            return null;
        }
        return new Box(boxRelocateOneSide, false);
    }

    public static Box boxAdjustSides(Box box, Box box2, int i, int i2, int i3, int i4) {
        long boxAdjustSides = JniLeptonicaJNI.boxAdjustSides(Box.getCPtr(box), box, Box.getCPtr(box2), box2, i, i2, i3, i4);
        if (boxAdjustSides == 0) {
            return null;
        }
        return new Box(boxAdjustSides, false);
    }

    public static Boxa boxaSetSide(Boxa boxa, Boxa boxa2, int i, int i2, int i3) {
        long boxaSetSide = JniLeptonicaJNI.boxaSetSide(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2, i3);
        if (boxaSetSide == 0) {
            return null;
        }
        return new Boxa(boxaSetSide, false);
    }

    public static Boxa boxaAdjustWidthToTarget(Boxa boxa, Boxa boxa2, int i, int i2, int i3) {
        long boxaAdjustWidthToTarget = JniLeptonicaJNI.boxaAdjustWidthToTarget(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2, i3);
        if (boxaAdjustWidthToTarget == 0) {
            return null;
        }
        return new Boxa(boxaAdjustWidthToTarget, false);
    }

    public static Boxa boxaAdjustHeightToTarget(Boxa boxa, Boxa boxa2, int i, int i2, int i3) {
        long boxaAdjustHeightToTarget = JniLeptonicaJNI.boxaAdjustHeightToTarget(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2, i3);
        if (boxaAdjustHeightToTarget == 0) {
            return null;
        }
        return new Boxa(boxaAdjustHeightToTarget, false);
    }

    public static int boxEqual(Box box, Box box2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxEqual(Box.getCPtr(box), box, Box.getCPtr(box2), box2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxaEqual(Boxa boxa, Boxa boxa2, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxaEqual(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxSimilar(Box box, Box box2, int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxSimilar(Box.getCPtr(box), box, Box.getCPtr(box2), box2, i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxaSimilar(Boxa boxa, Boxa boxa2, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxaSimilar(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2, i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int boxaJoin(Boxa boxa, Boxa boxa2, int i, int i2) {
        return JniLeptonicaJNI.boxaJoin(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2);
    }

    public static int boxaaJoin(Boxaa boxaa, Boxaa boxaa2, int i, int i2) {
        return JniLeptonicaJNI.boxaaJoin(Boxaa.getCPtr(boxaa), boxaa, Boxaa.getCPtr(boxaa2), boxaa2, i, i2);
    }

    public static int boxaSplitEvenOdd(Boxa boxa, int i, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa2) {
        return JniLeptonicaJNI.boxaSplitEvenOdd(Boxa.getCPtr(boxa), boxa, i, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa2));
    }

    public static Boxa boxaMergeEvenOdd(Boxa boxa, Boxa boxa2, int i) {
        long boxaMergeEvenOdd = JniLeptonicaJNI.boxaMergeEvenOdd(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i);
        if (boxaMergeEvenOdd == 0) {
            return null;
        }
        return new Boxa(boxaMergeEvenOdd, false);
    }

    public static Boxa boxaTransform(Boxa boxa, int i, int i2, float f, float f2) {
        long boxaTransform = JniLeptonicaJNI.boxaTransform(Boxa.getCPtr(boxa), boxa, i, i2, f, f2);
        if (boxaTransform == 0) {
            return null;
        }
        return new Boxa(boxaTransform, false);
    }

    public static Box boxTransform(Box box, int i, int i2, float f, float f2) {
        long boxTransform = JniLeptonicaJNI.boxTransform(Box.getCPtr(box), box, i, i2, f, f2);
        if (boxTransform == 0) {
            return null;
        }
        return new Box(boxTransform, false);
    }

    public static Boxa boxaTransformOrdered(Boxa boxa, int i, int i2, float f, float f2, int i3, int i4, float f3, int i5) {
        long boxaTransformOrdered = JniLeptonicaJNI.boxaTransformOrdered(Boxa.getCPtr(boxa), boxa, i, i2, f, f2, i3, i4, f3, i5);
        if (boxaTransformOrdered == 0) {
            return null;
        }
        return new Boxa(boxaTransformOrdered, false);
    }

    public static Box boxTransformOrdered(Box box, int i, int i2, float f, float f2, int i3, int i4, float f3, int i5) {
        long boxTransformOrdered = JniLeptonicaJNI.boxTransformOrdered(Box.getCPtr(box), box, i, i2, f, f2, i3, i4, f3, i5);
        if (boxTransformOrdered == 0) {
            return null;
        }
        return new Box(boxTransformOrdered, false);
    }

    public static Boxa boxaRotateOrth(Boxa boxa, int i, int i2, int i3) {
        long boxaRotateOrth = JniLeptonicaJNI.boxaRotateOrth(Boxa.getCPtr(boxa), boxa, i, i2, i3);
        if (boxaRotateOrth == 0) {
            return null;
        }
        return new Boxa(boxaRotateOrth, false);
    }

    public static Box boxRotateOrth(Box box, int i, int i2, int i3) {
        long boxRotateOrth = JniLeptonicaJNI.boxRotateOrth(Box.getCPtr(box), box, i, i2, i3);
        if (boxRotateOrth == 0) {
            return null;
        }
        return new Box(boxRotateOrth, false);
    }

    public static Boxa boxaSort(Boxa boxa, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        long boxaSort = JniLeptonicaJNI.boxaSort(Boxa.getCPtr(boxa), boxa, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
        if (boxaSort == 0) {
            return null;
        }
        return new Boxa(boxaSort, false);
    }

    public static Boxa boxaBinSort(Boxa boxa, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        long boxaBinSort = JniLeptonicaJNI.boxaBinSort(Boxa.getCPtr(boxa), boxa, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
        if (boxaBinSort == 0) {
            return null;
        }
        return new Boxa(boxaBinSort, false);
    }

    public static Boxa boxaSortByIndex(Boxa boxa, Numa numa) {
        long boxaSortByIndex = JniLeptonicaJNI.boxaSortByIndex(Boxa.getCPtr(boxa), boxa, Numa.getCPtr(numa), numa);
        if (boxaSortByIndex == 0) {
            return null;
        }
        return new Boxa(boxaSortByIndex, false);
    }

    public static Boxaa boxaSort2d(Boxa boxa, SWIGTYPE_p_p_Numaa sWIGTYPE_p_p_Numaa, int i, int i2, int i3) {
        long boxaSort2d = JniLeptonicaJNI.boxaSort2d(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_p_Numaa.getCPtr(sWIGTYPE_p_p_Numaa), i, i2, i3);
        if (boxaSort2d == 0) {
            return null;
        }
        return new Boxaa(boxaSort2d, false);
    }

    public static Boxaa boxaSort2dByIndex(Boxa boxa, Numaa numaa) {
        long boxaSort2dByIndex = JniLeptonicaJNI.boxaSort2dByIndex(Boxa.getCPtr(boxa), boxa, Numaa.getCPtr(numaa), numaa);
        if (boxaSort2dByIndex == 0) {
            return null;
        }
        return new Boxaa(boxaSort2dByIndex, false);
    }

    public static int boxaExtractAsNuma(Boxa boxa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, int i) {
        return JniLeptonicaJNI.boxaExtractAsNuma(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), i);
    }

    public static int boxaExtractAsPta(Boxa boxa, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta2, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta3, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta4, int i) {
        return JniLeptonicaJNI.boxaExtractAsPta(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta2), SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta3), SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta4), i);
    }

    public static Box boxaGetRankSize(Boxa boxa, float f) {
        long boxaGetRankSize = JniLeptonicaJNI.boxaGetRankSize(Boxa.getCPtr(boxa), boxa, f);
        if (boxaGetRankSize == 0) {
            return null;
        }
        return new Box(boxaGetRankSize, false);
    }

    public static Box boxaGetMedian(Boxa boxa) {
        long boxaGetMedian = JniLeptonicaJNI.boxaGetMedian(Boxa.getCPtr(boxa), boxa);
        if (boxaGetMedian == 0) {
            return null;
        }
        return new Box(boxaGetMedian, false);
    }

    public static int boxaGetAverageSize(Boxa boxa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.boxaGetAverageSize(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int boxaaGetExtent(Boxaa boxaa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        return JniLeptonicaJNI.boxaaGetExtent(Boxaa.getCPtr(boxaa), boxaa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box), SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
    }

    public static Boxa boxaaFlattenToBoxa(Boxaa boxaa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i) {
        long boxaaFlattenToBoxa = JniLeptonicaJNI.boxaaFlattenToBoxa(Boxaa.getCPtr(boxaa), boxaa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i);
        if (boxaaFlattenToBoxa == 0) {
            return null;
        }
        return new Boxa(boxaaFlattenToBoxa, false);
    }

    public static Boxa boxaaFlattenAligned(Boxaa boxaa, int i, Box box, int i2) {
        long boxaaFlattenAligned = JniLeptonicaJNI.boxaaFlattenAligned(Boxaa.getCPtr(boxaa), boxaa, i, Box.getCPtr(box), box, i2);
        if (boxaaFlattenAligned == 0) {
            return null;
        }
        return new Boxa(boxaaFlattenAligned, false);
    }

    public static Boxaa boxaEncapsulateAligned(Boxa boxa, int i, int i2) {
        long boxaEncapsulateAligned = JniLeptonicaJNI.boxaEncapsulateAligned(Boxa.getCPtr(boxa), boxa, i, i2);
        if (boxaEncapsulateAligned == 0) {
            return null;
        }
        return new Boxaa(boxaEncapsulateAligned, false);
    }

    public static int boxaaAlignBox(Boxaa boxaa, Box box, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxaaAlignBox(Boxaa.getCPtr(boxaa), boxaa, Box.getCPtr(box), box, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixMaskConnComp(Pix pix, int i, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        long pixMaskConnComp = JniLeptonicaJNI.pixMaskConnComp(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
        if (pixMaskConnComp == 0) {
            return null;
        }
        return new Pix(pixMaskConnComp, false);
    }

    public static Pix pixMaskBoxa(Pix pix, Pix pix2, Boxa boxa, int i) {
        long pixMaskBoxa = JniLeptonicaJNI.pixMaskBoxa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Boxa.getCPtr(boxa), boxa, i);
        if (pixMaskBoxa == 0) {
            return null;
        }
        return new Pix(pixMaskBoxa, false);
    }

    public static Pix pixPaintBoxa(Pix pix, Boxa boxa, long j) {
        long pixPaintBoxa = JniLeptonicaJNI.pixPaintBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, j);
        if (pixPaintBoxa == 0) {
            return null;
        }
        return new Pix(pixPaintBoxa, false);
    }

    public static Pix pixSetBlackOrWhiteBoxa(Pix pix, Boxa boxa, int i) {
        long pixSetBlackOrWhiteBoxa = JniLeptonicaJNI.pixSetBlackOrWhiteBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i);
        if (pixSetBlackOrWhiteBoxa == 0) {
            return null;
        }
        return new Pix(pixSetBlackOrWhiteBoxa, false);
    }

    public static Pix pixPaintBoxaRandom(Pix pix, Boxa boxa) {
        long pixPaintBoxaRandom = JniLeptonicaJNI.pixPaintBoxaRandom(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa);
        if (pixPaintBoxaRandom == 0) {
            return null;
        }
        return new Pix(pixPaintBoxaRandom, false);
    }

    public static Pix pixBlendBoxaRandom(Pix pix, Boxa boxa, float f) {
        long pixBlendBoxaRandom = JniLeptonicaJNI.pixBlendBoxaRandom(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, f);
        if (pixBlendBoxaRandom == 0) {
            return null;
        }
        return new Pix(pixBlendBoxaRandom, false);
    }

    public static Pix pixDrawBoxa(Pix pix, Boxa boxa, int i, long j) {
        long pixDrawBoxa = JniLeptonicaJNI.pixDrawBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, j);
        if (pixDrawBoxa == 0) {
            return null;
        }
        return new Pix(pixDrawBoxa, false);
    }

    public static Pix pixDrawBoxaRandom(Pix pix, Boxa boxa, int i) {
        long pixDrawBoxaRandom = JniLeptonicaJNI.pixDrawBoxaRandom(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i);
        if (pixDrawBoxaRandom == 0) {
            return null;
        }
        return new Pix(pixDrawBoxaRandom, false);
    }

    public static Pix boxaaDisplay(Boxaa boxaa, int i, int i2, long j, long j2, int i3, int i4) {
        long boxaaDisplay = JniLeptonicaJNI.boxaaDisplay(Boxaa.getCPtr(boxaa), boxaa, i, i2, j, j2, i3, i4);
        if (boxaaDisplay == 0) {
            return null;
        }
        return new Pix(boxaaDisplay, false);
    }

    public static Boxa pixSplitIntoBoxa(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixSplitIntoBoxa = JniLeptonicaJNI.pixSplitIntoBoxa(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
        if (pixSplitIntoBoxa == 0) {
            return null;
        }
        return new Boxa(pixSplitIntoBoxa, false);
    }

    public static Boxa pixSplitComponentIntoBoxa(Pix pix, Box box, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixSplitComponentIntoBoxa = JniLeptonicaJNI.pixSplitComponentIntoBoxa(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5, i6);
        if (pixSplitComponentIntoBoxa == 0) {
            return null;
        }
        return new Boxa(pixSplitComponentIntoBoxa, false);
    }

    public static Boxa makeMosaicStrips(int i, int i2, int i3, int i4) {
        long makeMosaicStrips = JniLeptonicaJNI.makeMosaicStrips(i, i2, i3, i4);
        if (makeMosaicStrips == 0) {
            return null;
        }
        return new Boxa(makeMosaicStrips, false);
    }

    public static int boxaCompareRegions(Boxa boxa, Boxa boxa2, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.boxaCompareRegions(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Boxa boxaSelectRange(Boxa boxa, int i, int i2, int i3) {
        long boxaSelectRange = JniLeptonicaJNI.boxaSelectRange(Boxa.getCPtr(boxa), boxa, i, i2, i3);
        if (boxaSelectRange == 0) {
            return null;
        }
        return new Boxa(boxaSelectRange, false);
    }

    public static Boxaa boxaaSelectRange(Boxaa boxaa, int i, int i2, int i3) {
        long boxaaSelectRange = JniLeptonicaJNI.boxaaSelectRange(Boxaa.getCPtr(boxaa), boxaa, i, i2, i3);
        if (boxaaSelectRange == 0) {
            return null;
        }
        return new Boxaa(boxaaSelectRange, false);
    }

    public static Boxa boxaSelectBySize(Boxa boxa, int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long boxaSelectBySize = JniLeptonicaJNI.boxaSelectBySize(Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (boxaSelectBySize == 0) {
            return null;
        }
        return new Boxa(boxaSelectBySize, false);
    }

    public static Numa boxaMakeSizeIndicator(Boxa boxa, int i, int i2, int i3, int i4) {
        long boxaMakeSizeIndicator = JniLeptonicaJNI.boxaMakeSizeIndicator(Boxa.getCPtr(boxa), boxa, i, i2, i3, i4);
        if (boxaMakeSizeIndicator == 0) {
            return null;
        }
        return new Numa(boxaMakeSizeIndicator, false);
    }

    public static Boxa boxaSelectByArea(Boxa boxa, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long boxaSelectByArea = JniLeptonicaJNI.boxaSelectByArea(Boxa.getCPtr(boxa), boxa, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (boxaSelectByArea == 0) {
            return null;
        }
        return new Boxa(boxaSelectByArea, false);
    }

    public static Numa boxaMakeAreaIndicator(Boxa boxa, int i, int i2) {
        long boxaMakeAreaIndicator = JniLeptonicaJNI.boxaMakeAreaIndicator(Boxa.getCPtr(boxa), boxa, i, i2);
        if (boxaMakeAreaIndicator == 0) {
            return null;
        }
        return new Numa(boxaMakeAreaIndicator, false);
    }

    public static Boxa boxaSelectWithIndicator(Boxa boxa, Numa numa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long boxaSelectWithIndicator = JniLeptonicaJNI.boxaSelectWithIndicator(Boxa.getCPtr(boxa), boxa, Numa.getCPtr(numa), numa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (boxaSelectWithIndicator == 0) {
            return null;
        }
        return new Boxa(boxaSelectWithIndicator, false);
    }

    public static Boxa boxaPermutePseudorandom(Boxa boxa) {
        long boxaPermutePseudorandom = JniLeptonicaJNI.boxaPermutePseudorandom(Boxa.getCPtr(boxa), boxa);
        if (boxaPermutePseudorandom == 0) {
            return null;
        }
        return new Boxa(boxaPermutePseudorandom, false);
    }

    public static Boxa boxaPermuteRandom(Boxa boxa, Boxa boxa2) {
        long boxaPermuteRandom = JniLeptonicaJNI.boxaPermuteRandom(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2);
        if (boxaPermuteRandom == 0) {
            return null;
        }
        return new Boxa(boxaPermuteRandom, false);
    }

    public static int boxaSwapBoxes(Boxa boxa, int i, int i2) {
        return JniLeptonicaJNI.boxaSwapBoxes(Boxa.getCPtr(boxa), boxa, i, i2);
    }

    public static Pta boxaConvertToPta(Boxa boxa, int i) {
        long boxaConvertToPta = JniLeptonicaJNI.boxaConvertToPta(Boxa.getCPtr(boxa), boxa, i);
        if (boxaConvertToPta == 0) {
            return null;
        }
        return new Pta(boxaConvertToPta, false);
    }

    public static Boxa ptaConvertToBoxa(Pta pta, int i) {
        long ptaConvertToBoxa = JniLeptonicaJNI.ptaConvertToBoxa(Pta.getCPtr(pta), pta, i);
        if (ptaConvertToBoxa == 0) {
            return null;
        }
        return new Boxa(ptaConvertToBoxa, false);
    }

    public static Boxa boxaSmoothSequence(Boxa boxa, float f, int i, int i2, int i3) {
        long boxaSmoothSequence = JniLeptonicaJNI.boxaSmoothSequence(Boxa.getCPtr(boxa), boxa, f, i, i2, i3);
        if (boxaSmoothSequence == 0) {
            return null;
        }
        return new Boxa(boxaSmoothSequence, false);
    }

    public static Boxa boxaLinearFit(Boxa boxa, float f, int i) {
        long boxaLinearFit = JniLeptonicaJNI.boxaLinearFit(Boxa.getCPtr(boxa), boxa, f, i);
        if (boxaLinearFit == 0) {
            return null;
        }
        return new Boxa(boxaLinearFit, false);
    }

    public static Boxa boxaModifyWithBoxa(Boxa boxa, Boxa boxa2, int i, int i2) {
        long boxaModifyWithBoxa = JniLeptonicaJNI.boxaModifyWithBoxa(Boxa.getCPtr(boxa), boxa, Boxa.getCPtr(boxa2), boxa2, i, i2);
        if (boxaModifyWithBoxa == 0) {
            return null;
        }
        return new Boxa(boxaModifyWithBoxa, false);
    }

    public static Boxa boxaConstrainSize(Boxa boxa, int i, int i2, int i3, int i4) {
        long boxaConstrainSize = JniLeptonicaJNI.boxaConstrainSize(Boxa.getCPtr(boxa), boxa, i, i2, i3, i4);
        if (boxaConstrainSize == 0) {
            return null;
        }
        return new Boxa(boxaConstrainSize, false);
    }

    public static Boxa boxaReconcileEvenOddHeight(Boxa boxa, int i, int i2, int i3, float f) {
        long boxaReconcileEvenOddHeight = JniLeptonicaJNI.boxaReconcileEvenOddHeight(Boxa.getCPtr(boxa), boxa, i, i2, i3, f);
        if (boxaReconcileEvenOddHeight == 0) {
            return null;
        }
        return new Boxa(boxaReconcileEvenOddHeight, false);
    }

    public static int boxaPlotSides(Boxa boxa, String str, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, int i) {
        return JniLeptonicaJNI.boxaPlotSides(Boxa.getCPtr(boxa), boxa, str, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), i);
    }

    public static int boxaGetExtent(Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.boxaGetExtent(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int boxaGetCoverage(Boxa boxa, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.boxaGetCoverage(Boxa.getCPtr(boxa), boxa, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int boxaaSizeRange(Boxaa boxaa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.boxaaSizeRange(Boxaa.getCPtr(boxaa), boxaa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int boxaSizeRange(Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.boxaSizeRange(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int boxaLocationRange(Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.boxaLocationRange(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int boxaGetArea(Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.boxaGetArea(Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix boxaDisplayTiled(Boxa boxa, Pixa pixa, int i, int i2, float f, int i3, int i4, int i5, String str) {
        long boxaDisplayTiled = JniLeptonicaJNI.boxaDisplayTiled(Boxa.getCPtr(boxa), boxa, Pixa.getCPtr(pixa), pixa, i, i2, f, i3, i4, i5, str);
        if (boxaDisplayTiled == 0) {
            return null;
        }
        return new Pix(boxaDisplayTiled, false);
    }

    public static L_Bytea l_byteaCreate(long j) {
        long l_byteaCreate = JniLeptonicaJNI.l_byteaCreate(j);
        if (l_byteaCreate == 0) {
            return null;
        }
        return new L_Bytea(l_byteaCreate, false);
    }

    public static L_Bytea l_byteaInitFromMem(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long l_byteaInitFromMem = JniLeptonicaJNI.l_byteaInitFromMem(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (l_byteaInitFromMem == 0) {
            return null;
        }
        return new L_Bytea(l_byteaInitFromMem, false);
    }

    public static L_Bytea l_byteaInitFromFile(String str) {
        long l_byteaInitFromFile = JniLeptonicaJNI.l_byteaInitFromFile(str);
        if (l_byteaInitFromFile == 0) {
            return null;
        }
        return new L_Bytea(l_byteaInitFromFile, false);
    }

    public static L_Bytea l_byteaInitFromStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long l_byteaInitFromStream = JniLeptonicaJNI.l_byteaInitFromStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (l_byteaInitFromStream == 0) {
            return null;
        }
        return new L_Bytea(l_byteaInitFromStream, false);
    }

    public static L_Bytea l_byteaCopy(L_Bytea l_Bytea, int i) {
        long l_byteaCopy = JniLeptonicaJNI.l_byteaCopy(L_Bytea.getCPtr(l_Bytea), l_Bytea, i);
        if (l_byteaCopy == 0) {
            return null;
        }
        return new L_Bytea(l_byteaCopy, false);
    }

    public static void l_byteaDestroy(SWIGTYPE_p_p_L_Bytea sWIGTYPE_p_p_L_Bytea) {
        JniLeptonicaJNI.l_byteaDestroy(SWIGTYPE_p_p_L_Bytea.getCPtr(sWIGTYPE_p_p_L_Bytea));
    }

    public static long l_byteaGetSize(L_Bytea l_Bytea) {
        return JniLeptonicaJNI.l_byteaGetSize(L_Bytea.getCPtr(l_Bytea), l_Bytea);
    }

    public static SWIGTYPE_p_unsigned_char l_byteaGetData(L_Bytea l_Bytea, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_byteaGetData = JniLeptonicaJNI.l_byteaGetData(L_Bytea.getCPtr(l_Bytea), l_Bytea, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_byteaGetData == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_byteaGetData, false);
    }

    public static SWIGTYPE_p_unsigned_char l_byteaCopyData(L_Bytea l_Bytea, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_byteaCopyData = JniLeptonicaJNI.l_byteaCopyData(L_Bytea.getCPtr(l_Bytea), l_Bytea, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_byteaCopyData == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_byteaCopyData, false);
    }

    public static int l_byteaAppendData(L_Bytea l_Bytea, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        return JniLeptonicaJNI.l_byteaAppendData(L_Bytea.getCPtr(l_Bytea), l_Bytea, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
    }

    public static int l_byteaAppendString(L_Bytea l_Bytea, String str) {
        return JniLeptonicaJNI.l_byteaAppendString(L_Bytea.getCPtr(l_Bytea), l_Bytea, str);
    }

    public static int l_byteaJoin(L_Bytea l_Bytea, SWIGTYPE_p_p_L_Bytea sWIGTYPE_p_p_L_Bytea) {
        return JniLeptonicaJNI.l_byteaJoin(L_Bytea.getCPtr(l_Bytea), l_Bytea, SWIGTYPE_p_p_L_Bytea.getCPtr(sWIGTYPE_p_p_L_Bytea));
    }

    public static int l_byteaSplit(L_Bytea l_Bytea, long j, SWIGTYPE_p_p_L_Bytea sWIGTYPE_p_p_L_Bytea) {
        return JniLeptonicaJNI.l_byteaSplit(L_Bytea.getCPtr(l_Bytea), l_Bytea, j, SWIGTYPE_p_p_L_Bytea.getCPtr(sWIGTYPE_p_p_L_Bytea));
    }

    public static int l_byteaFindEachSequence(L_Bytea l_Bytea, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i, SWIGTYPE_p_p_L_Dna sWIGTYPE_p_p_L_Dna) {
        return JniLeptonicaJNI.l_byteaFindEachSequence(L_Bytea.getCPtr(l_Bytea), l_Bytea, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i, SWIGTYPE_p_p_L_Dna.getCPtr(sWIGTYPE_p_p_L_Dna));
    }

    public static int l_byteaWrite(String str, L_Bytea l_Bytea, long j, long j2) {
        return JniLeptonicaJNI.l_byteaWrite(str, L_Bytea.getCPtr(l_Bytea), l_Bytea, j, j2);
    }

    public static int l_byteaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Bytea l_Bytea, long j, long j2) {
        return JniLeptonicaJNI.l_byteaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Bytea.getCPtr(l_Bytea), l_Bytea, j, j2);
    }

    public static CCBorda ccbaCreate(Pix pix, int i) {
        long ccbaCreate = JniLeptonicaJNI.ccbaCreate(Pix.getCPtr(pix), pix, i);
        if (ccbaCreate == 0) {
            return null;
        }
        return new CCBorda(ccbaCreate, false);
    }

    public static void ccbaDestroy(SWIGTYPE_p_p_CCBorda sWIGTYPE_p_p_CCBorda) {
        JniLeptonicaJNI.ccbaDestroy(SWIGTYPE_p_p_CCBorda.getCPtr(sWIGTYPE_p_p_CCBorda));
    }

    public static CCBord ccbCreate(Pix pix) {
        long ccbCreate = JniLeptonicaJNI.ccbCreate(Pix.getCPtr(pix), pix);
        if (ccbCreate == 0) {
            return null;
        }
        return new CCBord(ccbCreate, false);
    }

    public static void ccbDestroy(SWIGTYPE_p_p_CCBord sWIGTYPE_p_p_CCBord) {
        JniLeptonicaJNI.ccbDestroy(SWIGTYPE_p_p_CCBord.getCPtr(sWIGTYPE_p_p_CCBord));
    }

    public static int ccbaAddCcb(CCBorda cCBorda, CCBord cCBord) {
        return JniLeptonicaJNI.ccbaAddCcb(CCBorda.getCPtr(cCBorda), cCBorda, CCBord.getCPtr(cCBord), cCBord);
    }

    public static int ccbaGetCount(CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaGetCount(CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static CCBord ccbaGetCcb(CCBorda cCBorda, int i) {
        long ccbaGetCcb = JniLeptonicaJNI.ccbaGetCcb(CCBorda.getCPtr(cCBorda), cCBorda, i);
        if (ccbaGetCcb == 0) {
            return null;
        }
        return new CCBord(ccbaGetCcb, false);
    }

    public static CCBorda pixGetAllCCBorders(Pix pix) {
        long pixGetAllCCBorders = JniLeptonicaJNI.pixGetAllCCBorders(Pix.getCPtr(pix), pix);
        if (pixGetAllCCBorders == 0) {
            return null;
        }
        return new CCBorda(pixGetAllCCBorders, false);
    }

    public static CCBord pixGetCCBorders(Pix pix, Box box) {
        long pixGetCCBorders = JniLeptonicaJNI.pixGetCCBorders(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixGetCCBorders == 0) {
            return null;
        }
        return new CCBord(pixGetCCBorders, false);
    }

    public static Ptaa pixGetOuterBordersPtaa(Pix pix) {
        long pixGetOuterBordersPtaa = JniLeptonicaJNI.pixGetOuterBordersPtaa(Pix.getCPtr(pix), pix);
        if (pixGetOuterBordersPtaa == 0) {
            return null;
        }
        return new Ptaa(pixGetOuterBordersPtaa, false);
    }

    public static Pta pixGetOuterBorderPta(Pix pix, Box box) {
        long pixGetOuterBorderPta = JniLeptonicaJNI.pixGetOuterBorderPta(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixGetOuterBorderPta == 0) {
            return null;
        }
        return new Pta(pixGetOuterBorderPta, false);
    }

    public static int pixGetOuterBorder(CCBord cCBord, Pix pix, Box box) {
        return JniLeptonicaJNI.pixGetOuterBorder(CCBord.getCPtr(cCBord), cCBord, Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixGetHoleBorder(CCBord cCBord, Pix pix, Box box, int i, int i2) {
        return JniLeptonicaJNI.pixGetHoleBorder(CCBord.getCPtr(cCBord), cCBord, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2);
    }

    public static int findNextBorderPixel(int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.findNextBorderPixel(i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static void locateOutsideSeedPixel(int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        JniLeptonicaJNI.locateOutsideSeedPixel(i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int ccbaGenerateGlobalLocs(CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaGenerateGlobalLocs(CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static int ccbaGenerateStepChains(CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaGenerateStepChains(CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static int ccbaStepChainsToPixCoords(CCBorda cCBorda, int i) {
        return JniLeptonicaJNI.ccbaStepChainsToPixCoords(CCBorda.getCPtr(cCBorda), cCBorda, i);
    }

    public static int ccbaGenerateSPGlobalLocs(CCBorda cCBorda, int i) {
        return JniLeptonicaJNI.ccbaGenerateSPGlobalLocs(CCBorda.getCPtr(cCBorda), cCBorda, i);
    }

    public static int ccbaGenerateSinglePath(CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaGenerateSinglePath(CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static Pta getCutPathForHole(Pix pix, Pta pta, Box box, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long cutPathForHole = JniLeptonicaJNI.getCutPathForHole(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Box.getCPtr(box), box, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (cutPathForHole == 0) {
            return null;
        }
        return new Pta(cutPathForHole, false);
    }

    public static Pix ccbaDisplayBorder(CCBorda cCBorda) {
        long ccbaDisplayBorder = JniLeptonicaJNI.ccbaDisplayBorder(CCBorda.getCPtr(cCBorda), cCBorda);
        if (ccbaDisplayBorder == 0) {
            return null;
        }
        return new Pix(ccbaDisplayBorder, false);
    }

    public static Pix ccbaDisplaySPBorder(CCBorda cCBorda) {
        long ccbaDisplaySPBorder = JniLeptonicaJNI.ccbaDisplaySPBorder(CCBorda.getCPtr(cCBorda), cCBorda);
        if (ccbaDisplaySPBorder == 0) {
            return null;
        }
        return new Pix(ccbaDisplaySPBorder, false);
    }

    public static Pix ccbaDisplayImage1(CCBorda cCBorda) {
        long ccbaDisplayImage1 = JniLeptonicaJNI.ccbaDisplayImage1(CCBorda.getCPtr(cCBorda), cCBorda);
        if (ccbaDisplayImage1 == 0) {
            return null;
        }
        return new Pix(ccbaDisplayImage1, false);
    }

    public static Pix ccbaDisplayImage2(CCBorda cCBorda) {
        long ccbaDisplayImage2 = JniLeptonicaJNI.ccbaDisplayImage2(CCBorda.getCPtr(cCBorda), cCBorda);
        if (ccbaDisplayImage2 == 0) {
            return null;
        }
        return new Pix(ccbaDisplayImage2, false);
    }

    public static int ccbaWrite(String str, CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaWrite(str, CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static int ccbaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static CCBorda ccbaRead(String str) {
        long ccbaRead = JniLeptonicaJNI.ccbaRead(str);
        if (ccbaRead == 0) {
            return null;
        }
        return new CCBorda(ccbaRead, false);
    }

    public static CCBorda ccbaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long ccbaReadStream = JniLeptonicaJNI.ccbaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (ccbaReadStream == 0) {
            return null;
        }
        return new CCBorda(ccbaReadStream, false);
    }

    public static int ccbaWriteSVG(String str, CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaWriteSVG(str, CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static String ccbaWriteSVGString(String str, CCBorda cCBorda) {
        return JniLeptonicaJNI.ccbaWriteSVGString(str, CCBorda.getCPtr(cCBorda), cCBorda);
    }

    public static Pix pixThin(Pix pix, int i, int i2, int i3) {
        long pixThin = JniLeptonicaJNI.pixThin(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixThin == 0) {
            return null;
        }
        return new Pix(pixThin, false);
    }

    public static Pix pixThinGeneral(Pix pix, int i, Sela sela, int i2) {
        long pixThinGeneral = JniLeptonicaJNI.pixThinGeneral(Pix.getCPtr(pix), pix, i, Sela.getCPtr(sela), sela, i2);
        if (pixThinGeneral == 0) {
            return null;
        }
        return new Pix(pixThinGeneral, false);
    }

    public static Pix pixThinExamples(Pix pix, int i, int i2, int i3, String str) {
        long pixThinExamples = JniLeptonicaJNI.pixThinExamples(Pix.getCPtr(pix), pix, i, i2, i3, str);
        if (pixThinExamples == 0) {
            return null;
        }
        return new Pix(pixThinExamples, false);
    }

    public static int jbCorrelation(String str, float f, float f2, int i, String str2, int i2, int i3, int i4) {
        return JniLeptonicaJNI.jbCorrelation(str, f, f2, i, str2, i2, i3, i4);
    }

    public static int jbRankHaus(String str, int i, float f, int i2, String str2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.jbRankHaus(str, i, f, i2, str2, i3, i4, i5);
    }

    public static JbClasser jbWordsInTextlines(String str, int i, int i2, int i3, float f, float f2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i4, int i5) {
        long jbWordsInTextlines = JniLeptonicaJNI.jbWordsInTextlines(str, i, i2, i3, f, f2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i4, i5);
        if (jbWordsInTextlines == 0) {
            return null;
        }
        return new JbClasser(jbWordsInTextlines, false);
    }

    public static int pixGetWordsInTextlines(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixGetWordsInTextlines(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int pixGetWordBoxesInTextlines(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixGetWordBoxesInTextlines(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Numaa boxaExtractSortedPattern(Boxa boxa, Numa numa) {
        long boxaExtractSortedPattern = JniLeptonicaJNI.boxaExtractSortedPattern(Boxa.getCPtr(boxa), boxa, Numa.getCPtr(numa), numa);
        if (boxaExtractSortedPattern == 0) {
            return null;
        }
        return new Numaa(boxaExtractSortedPattern, false);
    }

    public static int numaaCompareImagesByBoxes(Numaa numaa, Numaa numaa2, int i, int i2, int i3, int i4, int i5, int i6, SWIGTYPE_p_int sWIGTYPE_p_int, int i7) {
        return JniLeptonicaJNI.numaaCompareImagesByBoxes(Numaa.getCPtr(numaa), numaa, Numaa.getCPtr(numaa2), numaa2, i, i2, i3, i4, i5, i6, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i7);
    }

    public static int pixColorContent(Pix pix, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixColorContent(Pix.getCPtr(pix), pix, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static Pix pixColorMagnitude(Pix pix, int i, int i2, int i3, int i4) {
        long pixColorMagnitude = JniLeptonicaJNI.pixColorMagnitude(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixColorMagnitude == 0) {
            return null;
        }
        return new Pix(pixColorMagnitude, false);
    }

    public static Pix pixMaskOverColorPixels(Pix pix, int i, int i2) {
        long pixMaskOverColorPixels = JniLeptonicaJNI.pixMaskOverColorPixels(Pix.getCPtr(pix), pix, i, i2);
        if (pixMaskOverColorPixels == 0) {
            return null;
        }
        return new Pix(pixMaskOverColorPixels, false);
    }

    public static int pixColorFraction(Pix pix, int i, int i2, int i3, int i4, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixColorFraction(Pix.getCPtr(pix), pix, i, i2, i3, i4, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int pixNumSignificantGrayColors(Pix pix, int i, int i2, float f, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixNumSignificantGrayColors(Pix.getCPtr(pix), pix, i, i2, f, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixColorsForQuantization(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, int i2) {
        return JniLeptonicaJNI.pixColorsForQuantization(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), i2);
    }

    public static int pixNumColors(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixNumColors(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixGetMostPopulatedColors(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, SWIGTYPE_p_p_PixColormap sWIGTYPE_p_p_PixColormap) {
        return JniLeptonicaJNI.pixGetMostPopulatedColors(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), SWIGTYPE_p_p_PixColormap.getCPtr(sWIGTYPE_p_p_PixColormap));
    }

    public static Pix pixSimpleColorQuantize(Pix pix, int i, int i2, int i3) {
        long pixSimpleColorQuantize = JniLeptonicaJNI.pixSimpleColorQuantize(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixSimpleColorQuantize == 0) {
            return null;
        }
        return new Pix(pixSimpleColorQuantize, false);
    }

    public static Numa pixGetRGBHistogram(Pix pix, int i, int i2) {
        long pixGetRGBHistogram = JniLeptonicaJNI.pixGetRGBHistogram(Pix.getCPtr(pix), pix, i, i2);
        if (pixGetRGBHistogram == 0) {
            return null;
        }
        return new Numa(pixGetRGBHistogram, false);
    }

    public static int makeRGBIndexTables(SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int2, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int3, int i) {
        return JniLeptonicaJNI.makeRGBIndexTables(SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int2), SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int3), i);
    }

    public static int getRGBFromIndex(long j, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.getRGBFromIndex(j, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixHasHighlightRed(Pix pix, int i, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixHasHighlightRed(Pix.getCPtr(pix), pix, i, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Pix pixColorGrayRegions(Pix pix, Boxa boxa, int i, int i2, int i3, int i4, int i5) {
        long pixColorGrayRegions = JniLeptonicaJNI.pixColorGrayRegions(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, i5);
        if (pixColorGrayRegions == 0) {
            return null;
        }
        return new Pix(pixColorGrayRegions, false);
    }

    public static int pixColorGray(Pix pix, Box box, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixColorGray(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5);
    }

    public static Pix pixSnapColor(Pix pix, Pix pix2, long j, long j2, int i) {
        long pixSnapColor = JniLeptonicaJNI.pixSnapColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, j2, i);
        if (pixSnapColor == 0) {
            return null;
        }
        return new Pix(pixSnapColor, false);
    }

    public static Pix pixSnapColorCmap(Pix pix, Pix pix2, long j, long j2, int i) {
        long pixSnapColorCmap = JniLeptonicaJNI.pixSnapColorCmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, j2, i);
        if (pixSnapColorCmap == 0) {
            return null;
        }
        return new Pix(pixSnapColorCmap, false);
    }

    public static Pix pixLinearMapToTargetColor(Pix pix, Pix pix2, long j, long j2) {
        long pixLinearMapToTargetColor = JniLeptonicaJNI.pixLinearMapToTargetColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, j2);
        if (pixLinearMapToTargetColor == 0) {
            return null;
        }
        return new Pix(pixLinearMapToTargetColor, false);
    }

    public static int pixelLinearMapToTargetColor(long j, long j2, long j3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixelLinearMapToTargetColor(j, j2, j3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static Pix pixShiftByComponent(Pix pix, Pix pix2, long j, long j2) {
        long pixShiftByComponent = JniLeptonicaJNI.pixShiftByComponent(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, j2);
        if (pixShiftByComponent == 0) {
            return null;
        }
        return new Pix(pixShiftByComponent, false);
    }

    public static int pixelShiftByComponent(int i, int i2, int i3, long j, long j2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixelShiftByComponent(i, i2, i3, j, j2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixelFractionalShift(int i, int i2, int i3, float f, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixelFractionalShift(i, i2, i3, f, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static PixColormap pixcmapCreate(int i) {
        long pixcmapCreate = JniLeptonicaJNI.pixcmapCreate(i);
        if (pixcmapCreate == 0) {
            return null;
        }
        return new PixColormap(pixcmapCreate, false);
    }

    public static PixColormap pixcmapCreateRandom(int i, int i2, int i3) {
        long pixcmapCreateRandom = JniLeptonicaJNI.pixcmapCreateRandom(i, i2, i3);
        if (pixcmapCreateRandom == 0) {
            return null;
        }
        return new PixColormap(pixcmapCreateRandom, false);
    }

    public static PixColormap pixcmapCreateLinear(int i, int i2) {
        long pixcmapCreateLinear = JniLeptonicaJNI.pixcmapCreateLinear(i, i2);
        if (pixcmapCreateLinear == 0) {
            return null;
        }
        return new PixColormap(pixcmapCreateLinear, false);
    }

    public static PixColormap pixcmapCopy(PixColormap pixColormap) {
        long pixcmapCopy = JniLeptonicaJNI.pixcmapCopy(PixColormap.getCPtr(pixColormap), pixColormap);
        if (pixcmapCopy == 0) {
            return null;
        }
        return new PixColormap(pixcmapCopy, false);
    }

    public static void pixcmapDestroy(SWIGTYPE_p_p_PixColormap sWIGTYPE_p_p_PixColormap) {
        JniLeptonicaJNI.pixcmapDestroy(SWIGTYPE_p_p_PixColormap.getCPtr(sWIGTYPE_p_p_PixColormap));
    }

    public static int pixcmapAddColor(PixColormap pixColormap, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixcmapAddColor(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3);
    }

    public static int pixcmapAddRGBA(PixColormap pixColormap, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixcmapAddRGBA(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, i4);
    }

    public static int pixcmapAddNewColor(PixColormap pixColormap, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapAddNewColor(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapAddNearestColor(PixColormap pixColormap, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapAddNearestColor(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapUsableColor(PixColormap pixColormap, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapUsableColor(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapAddBlackOrWhite(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapAddBlackOrWhite(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapSetBlackAndWhite(PixColormap pixColormap, int i, int i2) {
        return JniLeptonicaJNI.pixcmapSetBlackAndWhite(PixColormap.getCPtr(pixColormap), pixColormap, i, i2);
    }

    public static int pixcmapGetCount(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapGetCount(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapGetFreeCount(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapGetFreeCount(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapGetDepth(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapGetDepth(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapGetMinDepth(PixColormap pixColormap, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapGetMinDepth(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapClear(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapClear(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapGetColor(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixcmapGetColor(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixcmapGetColor32(PixColormap pixColormap, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixcmapGetColor32(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixcmapGetRGBA(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixcmapGetRGBA(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int pixcmapGetRGBA32(PixColormap pixColormap, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixcmapGetRGBA32(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixcmapResetColor(PixColormap pixColormap, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixcmapResetColor(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, i4);
    }

    public static int pixcmapGetIndex(PixColormap pixColormap, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapGetIndex(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapHasColor(PixColormap pixColormap, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapHasColor(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapIsOpaque(PixColormap pixColormap, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapIsOpaque(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapCountGrayColors(PixColormap pixColormap, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapCountGrayColors(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapGetRankIntensity(PixColormap pixColormap, float f, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapGetRankIntensity(PixColormap.getCPtr(pixColormap), pixColormap, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapGetNearestIndex(PixColormap pixColormap, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapGetNearestIndex(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapGetNearestGrayIndex(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapGetNearestGrayIndex(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapGetComponentRange(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixcmapGetComponentRange(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixcmapGetExtremeValue(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixcmapGetExtremeValue(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static PixColormap pixcmapGrayToColor(long j) {
        long pixcmapGrayToColor = JniLeptonicaJNI.pixcmapGrayToColor(j);
        if (pixcmapGrayToColor == 0) {
            return null;
        }
        return new PixColormap(pixcmapGrayToColor, false);
    }

    public static PixColormap pixcmapColorToGray(PixColormap pixColormap, float f, float f2, float f3) {
        long pixcmapColorToGray = JniLeptonicaJNI.pixcmapColorToGray(PixColormap.getCPtr(pixColormap), pixColormap, f, f2, f3);
        if (pixcmapColorToGray == 0) {
            return null;
        }
        return new PixColormap(pixcmapColorToGray, false);
    }

    public static PixColormap pixcmapReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixcmapReadStream = JniLeptonicaJNI.pixcmapReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixcmapReadStream == 0) {
            return null;
        }
        return new PixColormap(pixcmapReadStream, false);
    }

    public static int pixcmapWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapToArrays(PixColormap pixColormap, SWIGTYPE_p_p_int sWIGTYPE_p_p_int, SWIGTYPE_p_p_int sWIGTYPE_p_p_int2, SWIGTYPE_p_p_int sWIGTYPE_p_p_int3, SWIGTYPE_p_p_int sWIGTYPE_p_p_int4) {
        return JniLeptonicaJNI.pixcmapToArrays(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int2), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int3), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int4));
    }

    public static int pixcmapToRGBTable(PixColormap pixColormap, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcmapToRGBTable(PixColormap.getCPtr(pixColormap), pixColormap, SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixcmapSerializeToMemory(PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char) {
        return JniLeptonicaJNI.pixcmapSerializeToMemory(PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char));
    }

    public static PixColormap pixcmapDeserializeFromMemory(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i, int i2) {
        long pixcmapDeserializeFromMemory = JniLeptonicaJNI.pixcmapDeserializeFromMemory(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i, i2);
        if (pixcmapDeserializeFromMemory == 0) {
            return null;
        }
        return new PixColormap(pixcmapDeserializeFromMemory, false);
    }

    public static String pixcmapConvertToHex(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i) {
        return JniLeptonicaJNI.pixcmapConvertToHex(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i);
    }

    public static int pixcmapGammaTRC(PixColormap pixColormap, float f, int i, int i2) {
        return JniLeptonicaJNI.pixcmapGammaTRC(PixColormap.getCPtr(pixColormap), pixColormap, f, i, i2);
    }

    public static int pixcmapContrastTRC(PixColormap pixColormap, float f) {
        return JniLeptonicaJNI.pixcmapContrastTRC(PixColormap.getCPtr(pixColormap), pixColormap, f);
    }

    public static int pixcmapShiftIntensity(PixColormap pixColormap, float f) {
        return JniLeptonicaJNI.pixcmapShiftIntensity(PixColormap.getCPtr(pixColormap), pixColormap, f);
    }

    public static int pixcmapShiftByComponent(PixColormap pixColormap, long j, long j2) {
        return JniLeptonicaJNI.pixcmapShiftByComponent(PixColormap.getCPtr(pixColormap), pixColormap, j, j2);
    }

    public static Pix pixColorMorph(Pix pix, int i, int i2, int i3) {
        long pixColorMorph = JniLeptonicaJNI.pixColorMorph(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixColorMorph == 0) {
            return null;
        }
        return new Pix(pixColorMorph, false);
    }

    public static Pix pixOctreeColorQuant(Pix pix, int i, int i2) {
        long pixOctreeColorQuant = JniLeptonicaJNI.pixOctreeColorQuant(Pix.getCPtr(pix), pix, i, i2);
        if (pixOctreeColorQuant == 0) {
            return null;
        }
        return new Pix(pixOctreeColorQuant, false);
    }

    public static Pix pixOctreeColorQuantGeneral(Pix pix, int i, int i2, float f, float f2) {
        long pixOctreeColorQuantGeneral = JniLeptonicaJNI.pixOctreeColorQuantGeneral(Pix.getCPtr(pix), pix, i, i2, f, f2);
        if (pixOctreeColorQuantGeneral == 0) {
            return null;
        }
        return new Pix(pixOctreeColorQuantGeneral, false);
    }

    public static int makeRGBToIndexTables(SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int2, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int3, int i) {
        return JniLeptonicaJNI.makeRGBToIndexTables(SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int2), SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int3), i);
    }

    public static void getOctcubeIndexFromRGB(int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int4) {
        JniLeptonicaJNI.getOctcubeIndexFromRGB(i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int4));
    }

    public static Pix pixOctreeQuantByPopulation(Pix pix, int i, int i2) {
        long pixOctreeQuantByPopulation = JniLeptonicaJNI.pixOctreeQuantByPopulation(Pix.getCPtr(pix), pix, i, i2);
        if (pixOctreeQuantByPopulation == 0) {
            return null;
        }
        return new Pix(pixOctreeQuantByPopulation, false);
    }

    public static Pix pixOctreeQuantNumColors(Pix pix, int i, int i2) {
        long pixOctreeQuantNumColors = JniLeptonicaJNI.pixOctreeQuantNumColors(Pix.getCPtr(pix), pix, i, i2);
        if (pixOctreeQuantNumColors == 0) {
            return null;
        }
        return new Pix(pixOctreeQuantNumColors, false);
    }

    public static Pix pixOctcubeQuantMixedWithGray(Pix pix, int i, int i2, int i3) {
        long pixOctcubeQuantMixedWithGray = JniLeptonicaJNI.pixOctcubeQuantMixedWithGray(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixOctcubeQuantMixedWithGray == 0) {
            return null;
        }
        return new Pix(pixOctcubeQuantMixedWithGray, false);
    }

    public static Pix pixFixedOctcubeQuant256(Pix pix, int i) {
        long pixFixedOctcubeQuant256 = JniLeptonicaJNI.pixFixedOctcubeQuant256(Pix.getCPtr(pix), pix, i);
        if (pixFixedOctcubeQuant256 == 0) {
            return null;
        }
        return new Pix(pixFixedOctcubeQuant256, false);
    }

    public static Pix pixFewColorsOctcubeQuant1(Pix pix, int i) {
        long pixFewColorsOctcubeQuant1 = JniLeptonicaJNI.pixFewColorsOctcubeQuant1(Pix.getCPtr(pix), pix, i);
        if (pixFewColorsOctcubeQuant1 == 0) {
            return null;
        }
        return new Pix(pixFewColorsOctcubeQuant1, false);
    }

    public static Pix pixFewColorsOctcubeQuant2(Pix pix, int i, Numa numa, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixFewColorsOctcubeQuant2 = JniLeptonicaJNI.pixFewColorsOctcubeQuant2(Pix.getCPtr(pix), pix, i, Numa.getCPtr(numa), numa, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixFewColorsOctcubeQuant2 == 0) {
            return null;
        }
        return new Pix(pixFewColorsOctcubeQuant2, false);
    }

    public static Pix pixFewColorsOctcubeQuantMixed(Pix pix, int i, int i2, int i3, int i4, float f, int i5) {
        long pixFewColorsOctcubeQuantMixed = JniLeptonicaJNI.pixFewColorsOctcubeQuantMixed(Pix.getCPtr(pix), pix, i, i2, i3, i4, f, i5);
        if (pixFewColorsOctcubeQuantMixed == 0) {
            return null;
        }
        return new Pix(pixFewColorsOctcubeQuantMixed, false);
    }

    public static Pix pixFixedOctcubeQuantGenRGB(Pix pix, int i) {
        long pixFixedOctcubeQuantGenRGB = JniLeptonicaJNI.pixFixedOctcubeQuantGenRGB(Pix.getCPtr(pix), pix, i);
        if (pixFixedOctcubeQuantGenRGB == 0) {
            return null;
        }
        return new Pix(pixFixedOctcubeQuantGenRGB, false);
    }

    public static Pix pixQuantFromCmap(Pix pix, PixColormap pixColormap, int i, int i2, int i3) {
        long pixQuantFromCmap = JniLeptonicaJNI.pixQuantFromCmap(Pix.getCPtr(pix), pix, PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3);
        if (pixQuantFromCmap == 0) {
            return null;
        }
        return new Pix(pixQuantFromCmap, false);
    }

    public static Pix pixOctcubeQuantFromCmap(Pix pix, PixColormap pixColormap, int i, int i2, int i3) {
        long pixOctcubeQuantFromCmap = JniLeptonicaJNI.pixOctcubeQuantFromCmap(Pix.getCPtr(pix), pix, PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3);
        if (pixOctcubeQuantFromCmap == 0) {
            return null;
        }
        return new Pix(pixOctcubeQuantFromCmap, false);
    }

    public static Pix pixOctcubeQuantFromCmapLUT(Pix pix, PixColormap pixColormap, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3) {
        long pixOctcubeQuantFromCmapLUT = JniLeptonicaJNI.pixOctcubeQuantFromCmapLUT(Pix.getCPtr(pix), pix, PixColormap.getCPtr(pixColormap), pixColormap, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3));
        if (pixOctcubeQuantFromCmapLUT == 0) {
            return null;
        }
        return new Pix(pixOctcubeQuantFromCmapLUT, false);
    }

    public static Numa pixOctcubeHistogram(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixOctcubeHistogram = JniLeptonicaJNI.pixOctcubeHistogram(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixOctcubeHistogram == 0) {
            return null;
        }
        return new Numa(pixOctcubeHistogram, false);
    }

    public static SWIGTYPE_p_int pixcmapToOctcubeLUT(PixColormap pixColormap, int i, int i2) {
        long pixcmapToOctcubeLUT = JniLeptonicaJNI.pixcmapToOctcubeLUT(PixColormap.getCPtr(pixColormap), pixColormap, i, i2);
        if (pixcmapToOctcubeLUT == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(pixcmapToOctcubeLUT, false);
    }

    public static int pixRemoveUnusedColors(Pix pix) {
        return JniLeptonicaJNI.pixRemoveUnusedColors(Pix.getCPtr(pix), pix);
    }

    public static int pixNumberOccupiedOctcubes(Pix pix, int i, int i2, float f, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixNumberOccupiedOctcubes(Pix.getCPtr(pix), pix, i, i2, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixMedianCutQuant(Pix pix, int i) {
        long pixMedianCutQuant = JniLeptonicaJNI.pixMedianCutQuant(Pix.getCPtr(pix), pix, i);
        if (pixMedianCutQuant == 0) {
            return null;
        }
        return new Pix(pixMedianCutQuant, false);
    }

    public static Pix pixMedianCutQuantGeneral(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixMedianCutQuantGeneral = JniLeptonicaJNI.pixMedianCutQuantGeneral(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
        if (pixMedianCutQuantGeneral == 0) {
            return null;
        }
        return new Pix(pixMedianCutQuantGeneral, false);
    }

    public static Pix pixMedianCutQuantMixed(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixMedianCutQuantMixed = JniLeptonicaJNI.pixMedianCutQuantMixed(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixMedianCutQuantMixed == 0) {
            return null;
        }
        return new Pix(pixMedianCutQuantMixed, false);
    }

    public static Pix pixFewColorsMedianCutQuantMixed(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixFewColorsMedianCutQuantMixed = JniLeptonicaJNI.pixFewColorsMedianCutQuantMixed(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
        if (pixFewColorsMedianCutQuantMixed == 0) {
            return null;
        }
        return new Pix(pixFewColorsMedianCutQuantMixed, false);
    }

    public static SWIGTYPE_p_int pixMedianCutHisto(Pix pix, int i, int i2) {
        long pixMedianCutHisto = JniLeptonicaJNI.pixMedianCutHisto(Pix.getCPtr(pix), pix, i, i2);
        if (pixMedianCutHisto == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(pixMedianCutHisto, false);
    }

    public static Pix pixColorSegment(Pix pix, int i, int i2, int i3, int i4) {
        long pixColorSegment = JniLeptonicaJNI.pixColorSegment(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixColorSegment == 0) {
            return null;
        }
        return new Pix(pixColorSegment, false);
    }

    public static Pix pixColorSegmentCluster(Pix pix, int i, int i2) {
        long pixColorSegmentCluster = JniLeptonicaJNI.pixColorSegmentCluster(Pix.getCPtr(pix), pix, i, i2);
        if (pixColorSegmentCluster == 0) {
            return null;
        }
        return new Pix(pixColorSegmentCluster, false);
    }

    public static int pixAssignToNearestColor(Pix pix, Pix pix2, Pix pix3, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixAssignToNearestColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixColorSegmentClean(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixColorSegmentClean(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixColorSegmentRemoveColors(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixColorSegmentRemoveColors(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static Pix pixConvertRGBToHSV(Pix pix, Pix pix2) {
        long pixConvertRGBToHSV = JniLeptonicaJNI.pixConvertRGBToHSV(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixConvertRGBToHSV == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToHSV, false);
    }

    public static Pix pixConvertHSVToRGB(Pix pix, Pix pix2) {
        long pixConvertHSVToRGB = JniLeptonicaJNI.pixConvertHSVToRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixConvertHSVToRGB == 0) {
            return null;
        }
        return new Pix(pixConvertHSVToRGB, false);
    }

    public static int convertRGBToHSV(int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.convertRGBToHSV(i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int convertHSVToRGB(int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.convertHSVToRGB(i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixcmapConvertRGBToHSV(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapConvertRGBToHSV(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapConvertHSVToRGB(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapConvertHSVToRGB(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static Pix pixConvertRGBToHue(Pix pix) {
        long pixConvertRGBToHue = JniLeptonicaJNI.pixConvertRGBToHue(Pix.getCPtr(pix), pix);
        if (pixConvertRGBToHue == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToHue, false);
    }

    public static Pix pixConvertRGBToSaturation(Pix pix) {
        long pixConvertRGBToSaturation = JniLeptonicaJNI.pixConvertRGBToSaturation(Pix.getCPtr(pix), pix);
        if (pixConvertRGBToSaturation == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToSaturation, false);
    }

    public static Pix pixConvertRGBToValue(Pix pix) {
        long pixConvertRGBToValue = JniLeptonicaJNI.pixConvertRGBToValue(Pix.getCPtr(pix), pix);
        if (pixConvertRGBToValue == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToValue, false);
    }

    public static Pix pixMakeRangeMaskHS(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixMakeRangeMaskHS = JniLeptonicaJNI.pixMakeRangeMaskHS(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixMakeRangeMaskHS == 0) {
            return null;
        }
        return new Pix(pixMakeRangeMaskHS, false);
    }

    public static Pix pixMakeRangeMaskHV(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixMakeRangeMaskHV = JniLeptonicaJNI.pixMakeRangeMaskHV(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixMakeRangeMaskHV == 0) {
            return null;
        }
        return new Pix(pixMakeRangeMaskHV, false);
    }

    public static Pix pixMakeRangeMaskSV(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixMakeRangeMaskSV = JniLeptonicaJNI.pixMakeRangeMaskSV(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixMakeRangeMaskSV == 0) {
            return null;
        }
        return new Pix(pixMakeRangeMaskSV, false);
    }

    public static Pix pixMakeHistoHS(Pix pix, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        long pixMakeHistoHS = JniLeptonicaJNI.pixMakeHistoHS(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
        if (pixMakeHistoHS == 0) {
            return null;
        }
        return new Pix(pixMakeHistoHS, false);
    }

    public static Pix pixMakeHistoHV(Pix pix, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        long pixMakeHistoHV = JniLeptonicaJNI.pixMakeHistoHV(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
        if (pixMakeHistoHV == 0) {
            return null;
        }
        return new Pix(pixMakeHistoHV, false);
    }

    public static Pix pixMakeHistoSV(Pix pix, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        long pixMakeHistoSV = JniLeptonicaJNI.pixMakeHistoSV(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
        if (pixMakeHistoSV == 0) {
            return null;
        }
        return new Pix(pixMakeHistoSV, false);
    }

    public static int pixFindHistoPeaksHSV(Pix pix, int i, int i2, int i3, int i4, float f, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        return JniLeptonicaJNI.pixFindHistoPeaksHSV(Pix.getCPtr(pix), pix, i, i2, i3, i4, f, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static Pix displayHSVColorRange(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        long displayHSVColorRange = JniLeptonicaJNI.displayHSVColorRange(i, i2, i3, i4, i5, i6, i7);
        if (displayHSVColorRange == 0) {
            return null;
        }
        return new Pix(displayHSVColorRange, false);
    }

    public static Pix pixConvertRGBToYUV(Pix pix, Pix pix2) {
        long pixConvertRGBToYUV = JniLeptonicaJNI.pixConvertRGBToYUV(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixConvertRGBToYUV == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToYUV, false);
    }

    public static Pix pixConvertYUVToRGB(Pix pix, Pix pix2) {
        long pixConvertYUVToRGB = JniLeptonicaJNI.pixConvertYUVToRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixConvertYUVToRGB == 0) {
            return null;
        }
        return new Pix(pixConvertYUVToRGB, false);
    }

    public static int convertRGBToYUV(int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.convertRGBToYUV(i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int convertYUVToRGB(int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.convertYUVToRGB(i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixcmapConvertRGBToYUV(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapConvertRGBToYUV(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixcmapConvertYUVToRGB(PixColormap pixColormap) {
        return JniLeptonicaJNI.pixcmapConvertYUVToRGB(PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixEqual(Pix pix, Pix pix2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixEqual(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixEqualWithAlpha(Pix pix, Pix pix2, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixEqualWithAlpha(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixEqualWithCmap(Pix pix, Pix pix2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixEqualWithCmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixUsesCmapColor(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixUsesCmapColor(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixCorrelationBinary(Pix pix, Pix pix2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixCorrelationBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Pix pixDisplayDiffBinary(Pix pix, Pix pix2) {
        long pixDisplayDiffBinary = JniLeptonicaJNI.pixDisplayDiffBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixDisplayDiffBinary == 0) {
            return null;
        }
        return new Pix(pixDisplayDiffBinary, false);
    }

    public static int pixCompareBinary(Pix pix, Pix pix2, int i, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixCompareBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixCompareGrayOrRGB(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixCompareGrayOrRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixCompareGray(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixCompareGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixCompareRGB(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixCompareRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int pixCompareTiled(Pix pix, Pix pix2, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixCompareTiled(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Numa pixCompareRankDifference(Pix pix, Pix pix2, int i) {
        long pixCompareRankDifference = JniLeptonicaJNI.pixCompareRankDifference(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
        if (pixCompareRankDifference == 0) {
            return null;
        }
        return new Numa(pixCompareRankDifference, false);
    }

    public static int pixTestForSimilarity(Pix pix, Pix pix2, int i, int i2, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int, int i3) {
        return JniLeptonicaJNI.pixTestForSimilarity(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i3);
    }

    public static int pixGetDifferenceStats(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, int i3) {
        return JniLeptonicaJNI.pixGetDifferenceStats(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), i3);
    }

    public static Numa pixGetDifferenceHistogram(Pix pix, Pix pix2, int i) {
        long pixGetDifferenceHistogram = JniLeptonicaJNI.pixGetDifferenceHistogram(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
        if (pixGetDifferenceHistogram == 0) {
            return null;
        }
        return new Numa(pixGetDifferenceHistogram, false);
    }

    public static int pixGetPerceptualDiff(Pix pix, Pix pix2, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixGetPerceptualDiff(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static int pixGetPSNR(Pix pix, Pix pix2, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixGetPSNR(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixCompareWithTranslation(Pix pix, Pix pix2, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_float sWIGTYPE_p_float, int i2) {
        return JniLeptonicaJNI.pixCompareWithTranslation(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i2);
    }

    public static int pixBestCorrelation(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_float sWIGTYPE_p_float, int i6) {
        return JniLeptonicaJNI.pixBestCorrelation(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i6);
    }

    public static Boxa pixConnComp(Pix pix, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, int i) {
        long pixConnComp = JniLeptonicaJNI.pixConnComp(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), i);
        if (pixConnComp == 0) {
            return null;
        }
        return new Boxa(pixConnComp, false);
    }

    public static Boxa pixConnCompPixa(Pix pix, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, int i) {
        long pixConnCompPixa = JniLeptonicaJNI.pixConnCompPixa(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), i);
        if (pixConnCompPixa == 0) {
            return null;
        }
        return new Boxa(pixConnCompPixa, false);
    }

    public static Boxa pixConnCompBB(Pix pix, int i) {
        long pixConnCompBB = JniLeptonicaJNI.pixConnCompBB(Pix.getCPtr(pix), pix, i);
        if (pixConnCompBB == 0) {
            return null;
        }
        return new Boxa(pixConnCompBB, false);
    }

    public static int pixCountConnComp(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixCountConnComp(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int nextOnPixelInRaster(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.nextOnPixelInRaster(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int nextOnPixelInRasterLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.nextOnPixelInRasterLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Box pixSeedfillBB(Pix pix, L_Stack l_Stack, int i, int i2, int i3) {
        long pixSeedfillBB = JniLeptonicaJNI.pixSeedfillBB(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2, i3);
        if (pixSeedfillBB == 0) {
            return null;
        }
        return new Box(pixSeedfillBB, false);
    }

    public static Box pixSeedfill4BB(Pix pix, L_Stack l_Stack, int i, int i2) {
        long pixSeedfill4BB = JniLeptonicaJNI.pixSeedfill4BB(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2);
        if (pixSeedfill4BB == 0) {
            return null;
        }
        return new Box(pixSeedfill4BB, false);
    }

    public static Box pixSeedfill8BB(Pix pix, L_Stack l_Stack, int i, int i2) {
        long pixSeedfill8BB = JniLeptonicaJNI.pixSeedfill8BB(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2);
        if (pixSeedfill8BB == 0) {
            return null;
        }
        return new Box(pixSeedfill8BB, false);
    }

    public static int pixSeedfill(Pix pix, L_Stack l_Stack, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixSeedfill(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2, i3);
    }

    public static int pixSeedfill4(Pix pix, L_Stack l_Stack, int i, int i2) {
        return JniLeptonicaJNI.pixSeedfill4(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2);
    }

    public static int pixSeedfill8(Pix pix, L_Stack l_Stack, int i, int i2) {
        return JniLeptonicaJNI.pixSeedfill8(Pix.getCPtr(pix), pix, L_Stack.getCPtr(l_Stack), l_Stack, i, i2);
    }

    public static int convertFilesTo1bpp(String str, String str2, int i, int i2, int i3, int i4, String str3, int i5) {
        return JniLeptonicaJNI.convertFilesTo1bpp(str, str2, i, i2, i3, i4, str3, i5);
    }

    public static Pix pixBlockconv(Pix pix, int i, int i2) {
        long pixBlockconv = JniLeptonicaJNI.pixBlockconv(Pix.getCPtr(pix), pix, i, i2);
        if (pixBlockconv == 0) {
            return null;
        }
        return new Pix(pixBlockconv, false);
    }

    public static Pix pixBlockconvGray(Pix pix, Pix pix2, int i, int i2) {
        long pixBlockconvGray = JniLeptonicaJNI.pixBlockconvGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixBlockconvGray == 0) {
            return null;
        }
        return new Pix(pixBlockconvGray, false);
    }

    public static Pix pixBlockconvAccum(Pix pix) {
        long pixBlockconvAccum = JniLeptonicaJNI.pixBlockconvAccum(Pix.getCPtr(pix), pix);
        if (pixBlockconvAccum == 0) {
            return null;
        }
        return new Pix(pixBlockconvAccum, false);
    }

    public static Pix pixBlockconvGrayUnnormalized(Pix pix, int i, int i2) {
        long pixBlockconvGrayUnnormalized = JniLeptonicaJNI.pixBlockconvGrayUnnormalized(Pix.getCPtr(pix), pix, i, i2);
        if (pixBlockconvGrayUnnormalized == 0) {
            return null;
        }
        return new Pix(pixBlockconvGrayUnnormalized, false);
    }

    public static Pix pixBlockconvTiled(Pix pix, int i, int i2, int i3, int i4) {
        long pixBlockconvTiled = JniLeptonicaJNI.pixBlockconvTiled(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixBlockconvTiled == 0) {
            return null;
        }
        return new Pix(pixBlockconvTiled, false);
    }

    public static Pix pixBlockconvGrayTile(Pix pix, Pix pix2, int i, int i2) {
        long pixBlockconvGrayTile = JniLeptonicaJNI.pixBlockconvGrayTile(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixBlockconvGrayTile == 0) {
            return null;
        }
        return new Pix(pixBlockconvGrayTile, false);
    }

    public static int pixWindowedStats(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix, SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix2) {
        return JniLeptonicaJNI.pixWindowedStats(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix), SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix2));
    }

    public static Pix pixWindowedMean(Pix pix, int i, int i2, int i3, int i4) {
        long pixWindowedMean = JniLeptonicaJNI.pixWindowedMean(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixWindowedMean == 0) {
            return null;
        }
        return new Pix(pixWindowedMean, false);
    }

    public static Pix pixWindowedMeanSquare(Pix pix, int i, int i2, int i3) {
        long pixWindowedMeanSquare = JniLeptonicaJNI.pixWindowedMeanSquare(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixWindowedMeanSquare == 0) {
            return null;
        }
        return new Pix(pixWindowedMeanSquare, false);
    }

    public static int pixWindowedVariance(Pix pix, Pix pix2, SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix, SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix2) {
        return JniLeptonicaJNI.pixWindowedVariance(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix), SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix2));
    }

    public static DPix pixMeanSquareAccum(Pix pix) {
        long pixMeanSquareAccum = JniLeptonicaJNI.pixMeanSquareAccum(Pix.getCPtr(pix), pix);
        if (pixMeanSquareAccum == 0) {
            return null;
        }
        return new DPix(pixMeanSquareAccum, false);
    }

    public static Pix pixBlockrank(Pix pix, Pix pix2, int i, int i2, float f) {
        long pixBlockrank = JniLeptonicaJNI.pixBlockrank(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f);
        if (pixBlockrank == 0) {
            return null;
        }
        return new Pix(pixBlockrank, false);
    }

    public static Pix pixBlocksum(Pix pix, Pix pix2, int i, int i2) {
        long pixBlocksum = JniLeptonicaJNI.pixBlocksum(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixBlocksum == 0) {
            return null;
        }
        return new Pix(pixBlocksum, false);
    }

    public static Pix pixCensusTransform(Pix pix, int i, Pix pix2) {
        long pixCensusTransform = JniLeptonicaJNI.pixCensusTransform(Pix.getCPtr(pix), pix, i, Pix.getCPtr(pix2), pix2);
        if (pixCensusTransform == 0) {
            return null;
        }
        return new Pix(pixCensusTransform, false);
    }

    public static Pix pixConvolve(Pix pix, L_Kernel l_Kernel, int i, int i2) {
        long pixConvolve = JniLeptonicaJNI.pixConvolve(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, i, i2);
        if (pixConvolve == 0) {
            return null;
        }
        return new Pix(pixConvolve, false);
    }

    public static Pix pixConvolveSep(Pix pix, L_Kernel l_Kernel, L_Kernel l_Kernel2, int i, int i2) {
        long pixConvolveSep = JniLeptonicaJNI.pixConvolveSep(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2, i, i2);
        if (pixConvolveSep == 0) {
            return null;
        }
        return new Pix(pixConvolveSep, false);
    }

    public static Pix pixConvolveRGB(Pix pix, L_Kernel l_Kernel) {
        long pixConvolveRGB = JniLeptonicaJNI.pixConvolveRGB(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel);
        if (pixConvolveRGB == 0) {
            return null;
        }
        return new Pix(pixConvolveRGB, false);
    }

    public static Pix pixConvolveRGBSep(Pix pix, L_Kernel l_Kernel, L_Kernel l_Kernel2) {
        long pixConvolveRGBSep = JniLeptonicaJNI.pixConvolveRGBSep(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2);
        if (pixConvolveRGBSep == 0) {
            return null;
        }
        return new Pix(pixConvolveRGBSep, false);
    }

    public static FPix fpixConvolve(FPix fPix, L_Kernel l_Kernel, int i) {
        long fpixConvolve = JniLeptonicaJNI.fpixConvolve(FPix.getCPtr(fPix), fPix, L_Kernel.getCPtr(l_Kernel), l_Kernel, i);
        if (fpixConvolve == 0) {
            return null;
        }
        return new FPix(fpixConvolve, false);
    }

    public static FPix fpixConvolveSep(FPix fPix, L_Kernel l_Kernel, L_Kernel l_Kernel2, int i) {
        long fpixConvolveSep = JniLeptonicaJNI.fpixConvolveSep(FPix.getCPtr(fPix), fPix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2, i);
        if (fpixConvolveSep == 0) {
            return null;
        }
        return new FPix(fpixConvolveSep, false);
    }

    public static Pix pixConvolveWithBias(Pix pix, L_Kernel l_Kernel, L_Kernel l_Kernel2, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixConvolveWithBias = JniLeptonicaJNI.pixConvolveWithBias(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel, L_Kernel.getCPtr(l_Kernel2), l_Kernel2, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixConvolveWithBias == 0) {
            return null;
        }
        return new Pix(pixConvolveWithBias, false);
    }

    public static void l_setConvolveSampling(int i, int i2) {
        JniLeptonicaJNI.l_setConvolveSampling(i, i2);
    }

    public static Pix pixAddGaussianNoise(Pix pix, float f) {
        long pixAddGaussianNoise = JniLeptonicaJNI.pixAddGaussianNoise(Pix.getCPtr(pix), pix, f);
        if (pixAddGaussianNoise == 0) {
            return null;
        }
        return new Pix(pixAddGaussianNoise, false);
    }

    public static float gaussDistribSampling() {
        return JniLeptonicaJNI.gaussDistribSampling();
    }

    public static void blockconvLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.blockconvLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void blockconvAccumLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.blockconvAccumLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static void blocksumLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.blocksumLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static int pixCorrelationScore(Pix pix, Pix pix2, int i, int i2, float f, float f2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixCorrelationScore(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f, f2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixCorrelationScoreThresholded(Pix pix, Pix pix2, int i, int i2, float f, float f2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, float f3) {
        return JniLeptonicaJNI.pixCorrelationScoreThresholded(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f, f2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), f3);
    }

    public static int pixCorrelationScoreSimple(Pix pix, Pix pix2, int i, int i2, float f, float f2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixCorrelationScoreSimple(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, f, f2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixCorrelationScoreShifted(Pix pix, Pix pix2, int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixCorrelationScoreShifted(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static L_Dewarp dewarpCreate(Pix pix, int i) {
        long dewarpCreate = JniLeptonicaJNI.dewarpCreate(Pix.getCPtr(pix), pix, i);
        if (dewarpCreate == 0) {
            return null;
        }
        return new L_Dewarp(dewarpCreate, false);
    }

    public static L_Dewarp dewarpCreateRef(int i, int i2) {
        long dewarpCreateRef = JniLeptonicaJNI.dewarpCreateRef(i, i2);
        if (dewarpCreateRef == 0) {
            return null;
        }
        return new L_Dewarp(dewarpCreateRef, false);
    }

    public static void dewarpDestroy(SWIGTYPE_p_p_L_Dewarp sWIGTYPE_p_p_L_Dewarp) {
        JniLeptonicaJNI.dewarpDestroy(SWIGTYPE_p_p_L_Dewarp.getCPtr(sWIGTYPE_p_p_L_Dewarp));
    }

    public static L_Dewarpa dewarpaCreate(int i, int i2, int i3, int i4, int i5) {
        long dewarpaCreate = JniLeptonicaJNI.dewarpaCreate(i, i2, i3, i4, i5);
        if (dewarpaCreate == 0) {
            return null;
        }
        return new L_Dewarpa(dewarpaCreate, false);
    }

    public static L_Dewarpa dewarpaCreateFromPixacomp(PixaComp pixaComp, int i, int i2, int i3, int i4) {
        long dewarpaCreateFromPixacomp = JniLeptonicaJNI.dewarpaCreateFromPixacomp(PixaComp.getCPtr(pixaComp), pixaComp, i, i2, i3, i4);
        if (dewarpaCreateFromPixacomp == 0) {
            return null;
        }
        return new L_Dewarpa(dewarpaCreateFromPixacomp, false);
    }

    public static void dewarpaDestroy(SWIGTYPE_p_p_L_Dewarpa sWIGTYPE_p_p_L_Dewarpa) {
        JniLeptonicaJNI.dewarpaDestroy(SWIGTYPE_p_p_L_Dewarpa.getCPtr(sWIGTYPE_p_p_L_Dewarpa));
    }

    public static int dewarpaDestroyDewarp(L_Dewarpa l_Dewarpa, int i) {
        return JniLeptonicaJNI.dewarpaDestroyDewarp(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i);
    }

    public static int dewarpaInsertDewarp(L_Dewarpa l_Dewarpa, L_Dewarp l_Dewarp) {
        return JniLeptonicaJNI.dewarpaInsertDewarp(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, L_Dewarp.getCPtr(l_Dewarp), l_Dewarp);
    }

    public static L_Dewarp dewarpaGetDewarp(L_Dewarpa l_Dewarpa, int i) {
        long dewarpaGetDewarp = JniLeptonicaJNI.dewarpaGetDewarp(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i);
        if (dewarpaGetDewarp == 0) {
            return null;
        }
        return new L_Dewarp(dewarpaGetDewarp, false);
    }

    public static int dewarpaSetCurvatures(L_Dewarpa l_Dewarpa, int i, int i2, int i3, int i4, int i5, int i6) {
        return JniLeptonicaJNI.dewarpaSetCurvatures(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i, i2, i3, i4, i5, i6);
    }

    public static int dewarpaUseBothArrays(L_Dewarpa l_Dewarpa, int i) {
        return JniLeptonicaJNI.dewarpaUseBothArrays(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i);
    }

    public static int dewarpaSetMaxDistance(L_Dewarpa l_Dewarpa, int i) {
        return JniLeptonicaJNI.dewarpaSetMaxDistance(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i);
    }

    public static L_Dewarp dewarpRead(String str) {
        long dewarpRead = JniLeptonicaJNI.dewarpRead(str);
        if (dewarpRead == 0) {
            return null;
        }
        return new L_Dewarp(dewarpRead, false);
    }

    public static L_Dewarp dewarpReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long dewarpReadStream = JniLeptonicaJNI.dewarpReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (dewarpReadStream == 0) {
            return null;
        }
        return new L_Dewarp(dewarpReadStream, false);
    }

    public static int dewarpWrite(String str, L_Dewarp l_Dewarp) {
        return JniLeptonicaJNI.dewarpWrite(str, L_Dewarp.getCPtr(l_Dewarp), l_Dewarp);
    }

    public static int dewarpWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Dewarp l_Dewarp) {
        return JniLeptonicaJNI.dewarpWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Dewarp.getCPtr(l_Dewarp), l_Dewarp);
    }

    public static L_Dewarpa dewarpaRead(String str) {
        long dewarpaRead = JniLeptonicaJNI.dewarpaRead(str);
        if (dewarpaRead == 0) {
            return null;
        }
        return new L_Dewarpa(dewarpaRead, false);
    }

    public static L_Dewarpa dewarpaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long dewarpaReadStream = JniLeptonicaJNI.dewarpaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (dewarpaReadStream == 0) {
            return null;
        }
        return new L_Dewarpa(dewarpaReadStream, false);
    }

    public static int dewarpaWrite(String str, L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaWrite(str, L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpBuildPageModel(L_Dewarp l_Dewarp, String str) {
        return JniLeptonicaJNI.dewarpBuildPageModel(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, str);
    }

    public static int dewarpFindVertDisparity(L_Dewarp l_Dewarp, Ptaa ptaa, int i) {
        return JniLeptonicaJNI.dewarpFindVertDisparity(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, Ptaa.getCPtr(ptaa), ptaa, i);
    }

    public static int dewarpFindHorizDisparity(L_Dewarp l_Dewarp, Ptaa ptaa) {
        return JniLeptonicaJNI.dewarpFindHorizDisparity(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, Ptaa.getCPtr(ptaa), ptaa);
    }

    public static Ptaa dewarpGetTextlineCenters(Pix pix, int i) {
        long dewarpGetTextlineCenters = JniLeptonicaJNI.dewarpGetTextlineCenters(Pix.getCPtr(pix), pix, i);
        if (dewarpGetTextlineCenters == 0) {
            return null;
        }
        return new Ptaa(dewarpGetTextlineCenters, false);
    }

    public static Ptaa dewarpRemoveShortLines(Pix pix, Ptaa ptaa, float f, int i) {
        long dewarpRemoveShortLines = JniLeptonicaJNI.dewarpRemoveShortLines(Pix.getCPtr(pix), pix, Ptaa.getCPtr(ptaa), ptaa, f, i);
        if (dewarpRemoveShortLines == 0) {
            return null;
        }
        return new Ptaa(dewarpRemoveShortLines, false);
    }

    public static int dewarpBuildLineModel(L_Dewarp l_Dewarp, int i, String str) {
        return JniLeptonicaJNI.dewarpBuildLineModel(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, i, str);
    }

    public static int dewarpaModelStatus(L_Dewarpa l_Dewarpa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.dewarpaModelStatus(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int dewarpaApplyDisparity(L_Dewarpa l_Dewarpa, int i, Pix pix, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, String str) {
        return JniLeptonicaJNI.dewarpaApplyDisparity(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i, Pix.getCPtr(pix), pix, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), str);
    }

    public static int dewarpMinimize(L_Dewarp l_Dewarp) {
        return JniLeptonicaJNI.dewarpMinimize(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp);
    }

    public static int dewarpPopulateFullRes(L_Dewarp l_Dewarp, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.dewarpPopulateFullRes(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, Pix.getCPtr(pix), pix, i, i2);
    }

    public static int dewarpSinglePage(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_L_Dewarpa sWIGTYPE_p_p_L_Dewarpa, int i4) {
        return JniLeptonicaJNI.dewarpSinglePage(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_L_Dewarpa.getCPtr(sWIGTYPE_p_p_L_Dewarpa), i4);
    }

    public static int dewarpaListPages(L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaListPages(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpaSetValidModels(L_Dewarpa l_Dewarpa, int i, int i2) {
        return JniLeptonicaJNI.dewarpaSetValidModels(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i, i2);
    }

    public static int dewarpaInsertRefModels(L_Dewarpa l_Dewarpa, int i, int i2) {
        return JniLeptonicaJNI.dewarpaInsertRefModels(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, i, i2);
    }

    public static int dewarpaStripRefModels(L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaStripRefModels(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpaRestoreModels(L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaRestoreModels(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpaInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Dewarpa l_Dewarpa) {
        return JniLeptonicaJNI.dewarpaInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa);
    }

    public static int dewarpaModelStats(L_Dewarpa l_Dewarpa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.dewarpaModelStats(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int dewarpaShowArrays(L_Dewarpa l_Dewarpa, float f, int i, int i2, String str) {
        return JniLeptonicaJNI.dewarpaShowArrays(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, f, i, i2, str);
    }

    public static int dewarpDebug(L_Dewarp l_Dewarp, String str, int i) {
        return JniLeptonicaJNI.dewarpDebug(L_Dewarp.getCPtr(l_Dewarp), l_Dewarp, str, i);
    }

    public static int dewarpShowResults(L_Dewarpa l_Dewarpa, Sarray sarray, Boxa boxa, int i, int i2, String str, String str2) {
        return JniLeptonicaJNI.dewarpShowResults(L_Dewarpa.getCPtr(l_Dewarpa), l_Dewarpa, Sarray.getCPtr(sarray), sarray, Boxa.getCPtr(boxa), boxa, i, i2, str, str2);
    }

    public static L_Dna l_dnaCreate(int i) {
        long l_dnaCreate = JniLeptonicaJNI.l_dnaCreate(i);
        if (l_dnaCreate == 0) {
            return null;
        }
        return new L_Dna(l_dnaCreate, false);
    }

    public static L_Dna l_dnaCreateFromIArray(SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long l_dnaCreateFromIArray = JniLeptonicaJNI.l_dnaCreateFromIArray(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
        if (l_dnaCreateFromIArray == 0) {
            return null;
        }
        return new L_Dna(l_dnaCreateFromIArray, false);
    }

    public static L_Dna l_dnaCreateFromDArray(SWIGTYPE_p_double sWIGTYPE_p_double, int i, int i2) {
        long l_dnaCreateFromDArray = JniLeptonicaJNI.l_dnaCreateFromDArray(SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), i, i2);
        if (l_dnaCreateFromDArray == 0) {
            return null;
        }
        return new L_Dna(l_dnaCreateFromDArray, false);
    }

    public static L_Dna l_dnaMakeSequence(double d, double d2, int i) {
        long l_dnaMakeSequence = JniLeptonicaJNI.l_dnaMakeSequence(d, d2, i);
        if (l_dnaMakeSequence == 0) {
            return null;
        }
        return new L_Dna(l_dnaMakeSequence, false);
    }

    public static void l_dnaDestroy(SWIGTYPE_p_p_L_Dna sWIGTYPE_p_p_L_Dna) {
        JniLeptonicaJNI.l_dnaDestroy(SWIGTYPE_p_p_L_Dna.getCPtr(sWIGTYPE_p_p_L_Dna));
    }

    public static L_Dna l_dnaCopy(L_Dna l_Dna) {
        long l_dnaCopy = JniLeptonicaJNI.l_dnaCopy(L_Dna.getCPtr(l_Dna), l_Dna);
        if (l_dnaCopy == 0) {
            return null;
        }
        return new L_Dna(l_dnaCopy, false);
    }

    public static L_Dna l_dnaClone(L_Dna l_Dna) {
        long l_dnaClone = JniLeptonicaJNI.l_dnaClone(L_Dna.getCPtr(l_Dna), l_Dna);
        if (l_dnaClone == 0) {
            return null;
        }
        return new L_Dna(l_dnaClone, false);
    }

    public static int l_dnaEmpty(L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaEmpty(L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static int l_dnaAddNumber(L_Dna l_Dna, double d) {
        return JniLeptonicaJNI.l_dnaAddNumber(L_Dna.getCPtr(l_Dna), l_Dna, d);
    }

    public static int l_dnaInsertNumber(L_Dna l_Dna, int i, double d) {
        return JniLeptonicaJNI.l_dnaInsertNumber(L_Dna.getCPtr(l_Dna), l_Dna, i, d);
    }

    public static int l_dnaRemoveNumber(L_Dna l_Dna, int i) {
        return JniLeptonicaJNI.l_dnaRemoveNumber(L_Dna.getCPtr(l_Dna), l_Dna, i);
    }

    public static int l_dnaReplaceNumber(L_Dna l_Dna, int i, double d) {
        return JniLeptonicaJNI.l_dnaReplaceNumber(L_Dna.getCPtr(l_Dna), l_Dna, i, d);
    }

    public static int l_dnaGetCount(L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaGetCount(L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static int l_dnaSetCount(L_Dna l_Dna, int i) {
        return JniLeptonicaJNI.l_dnaSetCount(L_Dna.getCPtr(l_Dna), l_Dna, i);
    }

    public static int l_dnaGetDValue(L_Dna l_Dna, int i, SWIGTYPE_p_double sWIGTYPE_p_double) {
        return JniLeptonicaJNI.l_dnaGetDValue(L_Dna.getCPtr(l_Dna), l_Dna, i, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public static int l_dnaGetIValue(L_Dna l_Dna, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.l_dnaGetIValue(L_Dna.getCPtr(l_Dna), l_Dna, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int l_dnaSetValue(L_Dna l_Dna, int i, double d) {
        return JniLeptonicaJNI.l_dnaSetValue(L_Dna.getCPtr(l_Dna), l_Dna, i, d);
    }

    public static int l_dnaShiftValue(L_Dna l_Dna, int i, double d) {
        return JniLeptonicaJNI.l_dnaShiftValue(L_Dna.getCPtr(l_Dna), l_Dna, i, d);
    }

    public static SWIGTYPE_p_int l_dnaGetIArray(L_Dna l_Dna) {
        long l_dnaGetIArray = JniLeptonicaJNI.l_dnaGetIArray(L_Dna.getCPtr(l_Dna), l_Dna);
        if (l_dnaGetIArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(l_dnaGetIArray, false);
    }

    public static SWIGTYPE_p_double l_dnaGetDArray(L_Dna l_Dna, int i) {
        long l_dnaGetDArray = JniLeptonicaJNI.l_dnaGetDArray(L_Dna.getCPtr(l_Dna), l_Dna, i);
        if (l_dnaGetDArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_double(l_dnaGetDArray, false);
    }

    public static int l_dnaGetRefcount(L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaGetRefcount(L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static int l_dnaChangeRefcount(L_Dna l_Dna, int i) {
        return JniLeptonicaJNI.l_dnaChangeRefcount(L_Dna.getCPtr(l_Dna), l_Dna, i);
    }

    public static int l_dnaGetParameters(L_Dna l_Dna, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_double sWIGTYPE_p_double2) {
        return JniLeptonicaJNI.l_dnaGetParameters(L_Dna.getCPtr(l_Dna), l_Dna, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double2));
    }

    public static int l_dnaSetParameters(L_Dna l_Dna, double d, double d2) {
        return JniLeptonicaJNI.l_dnaSetParameters(L_Dna.getCPtr(l_Dna), l_Dna, d, d2);
    }

    public static int l_dnaCopyParameters(L_Dna l_Dna, L_Dna l_Dna2) {
        return JniLeptonicaJNI.l_dnaCopyParameters(L_Dna.getCPtr(l_Dna), l_Dna, L_Dna.getCPtr(l_Dna2), l_Dna2);
    }

    public static L_Dna l_dnaRead(String str) {
        long l_dnaRead = JniLeptonicaJNI.l_dnaRead(str);
        if (l_dnaRead == 0) {
            return null;
        }
        return new L_Dna(l_dnaRead, false);
    }

    public static L_Dna l_dnaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long l_dnaReadStream = JniLeptonicaJNI.l_dnaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (l_dnaReadStream == 0) {
            return null;
        }
        return new L_Dna(l_dnaReadStream, false);
    }

    public static int l_dnaWrite(String str, L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaWrite(str, L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static int l_dnaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static L_Dnaa l_dnaaCreate(int i) {
        long l_dnaaCreate = JniLeptonicaJNI.l_dnaaCreate(i);
        if (l_dnaaCreate == 0) {
            return null;
        }
        return new L_Dnaa(l_dnaaCreate, false);
    }

    public static void l_dnaaDestroy(SWIGTYPE_p_p_L_Dnaa sWIGTYPE_p_p_L_Dnaa) {
        JniLeptonicaJNI.l_dnaaDestroy(SWIGTYPE_p_p_L_Dnaa.getCPtr(sWIGTYPE_p_p_L_Dnaa));
    }

    public static int l_dnaaAddDna(L_Dnaa l_Dnaa, L_Dna l_Dna, int i) {
        return JniLeptonicaJNI.l_dnaaAddDna(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, L_Dna.getCPtr(l_Dna), l_Dna, i);
    }

    public static int l_dnaaGetCount(L_Dnaa l_Dnaa) {
        return JniLeptonicaJNI.l_dnaaGetCount(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa);
    }

    public static int l_dnaaGetDnaCount(L_Dnaa l_Dnaa, int i) {
        return JniLeptonicaJNI.l_dnaaGetDnaCount(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, i);
    }

    public static int l_dnaaGetNumberCount(L_Dnaa l_Dnaa) {
        return JniLeptonicaJNI.l_dnaaGetNumberCount(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa);
    }

    public static L_Dna l_dnaaGetDna(L_Dnaa l_Dnaa, int i, int i2) {
        long l_dnaaGetDna = JniLeptonicaJNI.l_dnaaGetDna(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, i, i2);
        if (l_dnaaGetDna == 0) {
            return null;
        }
        return new L_Dna(l_dnaaGetDna, false);
    }

    public static int l_dnaaReplaceDna(L_Dnaa l_Dnaa, int i, L_Dna l_Dna) {
        return JniLeptonicaJNI.l_dnaaReplaceDna(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, i, L_Dna.getCPtr(l_Dna), l_Dna);
    }

    public static int l_dnaaGetValue(L_Dnaa l_Dnaa, int i, int i2, SWIGTYPE_p_double sWIGTYPE_p_double) {
        return JniLeptonicaJNI.l_dnaaGetValue(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, i, i2, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public static int l_dnaaAddNumber(L_Dnaa l_Dnaa, int i, double d) {
        return JniLeptonicaJNI.l_dnaaAddNumber(L_Dnaa.getCPtr(l_Dnaa), l_Dnaa, i, d);
    }

    public static L_Dnaa l_dnaaRead(String str) {
        long l_dnaaRead = JniLeptonicaJNI.l_dnaaRead(str);
        if (l_dnaaRead == 0) {
            return null;
        }
        return new L_Dnaa(l_dnaaRead, false);
    }

    public static L_Dnaa l_dnaaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long l_dnaaReadStream = JniLeptonicaJNI.l_dnaaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (l_dnaaReadStream == 0) {
            return null;
        }
        return new L_Dnaa(l_dnaaReadStream, false);
    }

    public static int l_dnaaWrite(String str, L_Dnaa l_Dnaa) {
        return JniLeptonicaJNI.l_dnaaWrite(str, L_Dnaa.getCPtr(l_Dnaa), l_Dnaa);
    }

    public static int l_dnaaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Dnaa l_Dnaa) {
        return JniLeptonicaJNI.l_dnaaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Dnaa.getCPtr(l_Dnaa), l_Dnaa);
    }

    public static L_Dna l_dnaMakeDelta(L_Dna l_Dna) {
        long l_dnaMakeDelta = JniLeptonicaJNI.l_dnaMakeDelta(L_Dna.getCPtr(l_Dna), l_Dna);
        if (l_dnaMakeDelta == 0) {
            return null;
        }
        return new L_Dna(l_dnaMakeDelta, false);
    }

    public static Numa l_dnaConvertToNuma(L_Dna l_Dna) {
        long l_dnaConvertToNuma = JniLeptonicaJNI.l_dnaConvertToNuma(L_Dna.getCPtr(l_Dna), l_Dna);
        if (l_dnaConvertToNuma == 0) {
            return null;
        }
        return new Numa(l_dnaConvertToNuma, false);
    }

    public static L_Dna numaConvertToDna(Numa numa) {
        long numaConvertToDna = JniLeptonicaJNI.numaConvertToDna(Numa.getCPtr(numa), numa);
        if (numaConvertToDna == 0) {
            return null;
        }
        return new L_Dna(numaConvertToDna, false);
    }

    public static int l_dnaJoin(L_Dna l_Dna, L_Dna l_Dna2, int i, int i2) {
        return JniLeptonicaJNI.l_dnaJoin(L_Dna.getCPtr(l_Dna), l_Dna, L_Dna.getCPtr(l_Dna2), l_Dna2, i, i2);
    }

    public static Pix pixMorphDwa_2(Pix pix, Pix pix2, int i, String str) {
        long pixMorphDwa_2 = JniLeptonicaJNI.pixMorphDwa_2(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, str);
        if (pixMorphDwa_2 == 0) {
            return null;
        }
        return new Pix(pixMorphDwa_2, false);
    }

    public static Pix pixFMorphopGen_2(Pix pix, Pix pix2, int i, String str) {
        long pixFMorphopGen_2 = JniLeptonicaJNI.pixFMorphopGen_2(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, str);
        if (pixFMorphopGen_2 == 0) {
            return null;
        }
        return new Pix(pixFMorphopGen_2, false);
    }

    public static int fmorphopgen_low_2(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        return JniLeptonicaJNI.fmorphopgen_low_2(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static Pix pixSobelEdgeFilter(Pix pix, int i) {
        long pixSobelEdgeFilter = JniLeptonicaJNI.pixSobelEdgeFilter(Pix.getCPtr(pix), pix, i);
        if (pixSobelEdgeFilter == 0) {
            return null;
        }
        return new Pix(pixSobelEdgeFilter, false);
    }

    public static Pix pixTwoSidedEdgeFilter(Pix pix, int i) {
        long pixTwoSidedEdgeFilter = JniLeptonicaJNI.pixTwoSidedEdgeFilter(Pix.getCPtr(pix), pix, i);
        if (pixTwoSidedEdgeFilter == 0) {
            return null;
        }
        return new Pix(pixTwoSidedEdgeFilter, false);
    }

    public static int pixMeasureEdgeSmoothness(Pix pix, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, String str) {
        return JniLeptonicaJNI.pixMeasureEdgeSmoothness(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), str);
    }

    public static Numa pixGetEdgeProfile(Pix pix, int i, String str) {
        long pixGetEdgeProfile = JniLeptonicaJNI.pixGetEdgeProfile(Pix.getCPtr(pix), pix, i, str);
        if (pixGetEdgeProfile == 0) {
            return null;
        }
        return new Numa(pixGetEdgeProfile, false);
    }

    public static int pixGetLastOffPixelInRun(Pix pix, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixGetLastOffPixelInRun(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixGetLastOnPixelInRun(Pix pix, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixGetLastOnPixelInRun(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixGammaTRC(Pix pix, Pix pix2, float f, int i, int i2) {
        long pixGammaTRC = JniLeptonicaJNI.pixGammaTRC(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i, i2);
        if (pixGammaTRC == 0) {
            return null;
        }
        return new Pix(pixGammaTRC, false);
    }

    public static Pix pixGammaTRCMasked(Pix pix, Pix pix2, Pix pix3, float f, int i, int i2) {
        long pixGammaTRCMasked = JniLeptonicaJNI.pixGammaTRCMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, f, i, i2);
        if (pixGammaTRCMasked == 0) {
            return null;
        }
        return new Pix(pixGammaTRCMasked, false);
    }

    public static Pix pixGammaTRCWithAlpha(Pix pix, Pix pix2, float f, int i, int i2) {
        long pixGammaTRCWithAlpha = JniLeptonicaJNI.pixGammaTRCWithAlpha(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i, i2);
        if (pixGammaTRCWithAlpha == 0) {
            return null;
        }
        return new Pix(pixGammaTRCWithAlpha, false);
    }

    public static Numa numaGammaTRC(float f, int i, int i2) {
        long numaGammaTRC = JniLeptonicaJNI.numaGammaTRC(f, i, i2);
        if (numaGammaTRC == 0) {
            return null;
        }
        return new Numa(numaGammaTRC, false);
    }

    public static Pix pixContrastTRC(Pix pix, Pix pix2, float f) {
        long pixContrastTRC = JniLeptonicaJNI.pixContrastTRC(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixContrastTRC == 0) {
            return null;
        }
        return new Pix(pixContrastTRC, false);
    }

    public static Pix pixContrastTRCMasked(Pix pix, Pix pix2, Pix pix3, float f) {
        long pixContrastTRCMasked = JniLeptonicaJNI.pixContrastTRCMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, f);
        if (pixContrastTRCMasked == 0) {
            return null;
        }
        return new Pix(pixContrastTRCMasked, false);
    }

    public static Numa numaContrastTRC(float f) {
        long numaContrastTRC = JniLeptonicaJNI.numaContrastTRC(f);
        if (numaContrastTRC == 0) {
            return null;
        }
        return new Numa(numaContrastTRC, false);
    }

    public static Pix pixEqualizeTRC(Pix pix, Pix pix2, float f, int i) {
        long pixEqualizeTRC = JniLeptonicaJNI.pixEqualizeTRC(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixEqualizeTRC == 0) {
            return null;
        }
        return new Pix(pixEqualizeTRC, false);
    }

    public static Numa numaEqualizeTRC(Pix pix, float f, int i) {
        long numaEqualizeTRC = JniLeptonicaJNI.numaEqualizeTRC(Pix.getCPtr(pix), pix, f, i);
        if (numaEqualizeTRC == 0) {
            return null;
        }
        return new Numa(numaEqualizeTRC, false);
    }

    public static int pixTRCMap(Pix pix, Pix pix2, Numa numa) {
        return JniLeptonicaJNI.pixTRCMap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Numa.getCPtr(numa), numa);
    }

    public static Pix pixUnsharpMasking(Pix pix, int i, float f) {
        long pixUnsharpMasking = JniLeptonicaJNI.pixUnsharpMasking(Pix.getCPtr(pix), pix, i, f);
        if (pixUnsharpMasking == 0) {
            return null;
        }
        return new Pix(pixUnsharpMasking, false);
    }

    public static Pix pixUnsharpMaskingGray(Pix pix, int i, float f) {
        long pixUnsharpMaskingGray = JniLeptonicaJNI.pixUnsharpMaskingGray(Pix.getCPtr(pix), pix, i, f);
        if (pixUnsharpMaskingGray == 0) {
            return null;
        }
        return new Pix(pixUnsharpMaskingGray, false);
    }

    public static Pix pixUnsharpMaskingFast(Pix pix, int i, float f, int i2) {
        long pixUnsharpMaskingFast = JniLeptonicaJNI.pixUnsharpMaskingFast(Pix.getCPtr(pix), pix, i, f, i2);
        if (pixUnsharpMaskingFast == 0) {
            return null;
        }
        return new Pix(pixUnsharpMaskingFast, false);
    }

    public static Pix pixUnsharpMaskingGrayFast(Pix pix, int i, float f, int i2) {
        long pixUnsharpMaskingGrayFast = JniLeptonicaJNI.pixUnsharpMaskingGrayFast(Pix.getCPtr(pix), pix, i, f, i2);
        if (pixUnsharpMaskingGrayFast == 0) {
            return null;
        }
        return new Pix(pixUnsharpMaskingGrayFast, false);
    }

    public static Pix pixUnsharpMaskingGray1D(Pix pix, int i, float f, int i2) {
        long pixUnsharpMaskingGray1D = JniLeptonicaJNI.pixUnsharpMaskingGray1D(Pix.getCPtr(pix), pix, i, f, i2);
        if (pixUnsharpMaskingGray1D == 0) {
            return null;
        }
        return new Pix(pixUnsharpMaskingGray1D, false);
    }

    public static Pix pixUnsharpMaskingGray2D(Pix pix, int i, float f) {
        long pixUnsharpMaskingGray2D = JniLeptonicaJNI.pixUnsharpMaskingGray2D(Pix.getCPtr(pix), pix, i, f);
        if (pixUnsharpMaskingGray2D == 0) {
            return null;
        }
        return new Pix(pixUnsharpMaskingGray2D, false);
    }

    public static Pix pixModifyHue(Pix pix, Pix pix2, float f) {
        long pixModifyHue = JniLeptonicaJNI.pixModifyHue(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixModifyHue == 0) {
            return null;
        }
        return new Pix(pixModifyHue, false);
    }

    public static Pix pixModifySaturation(Pix pix, Pix pix2, float f) {
        long pixModifySaturation = JniLeptonicaJNI.pixModifySaturation(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixModifySaturation == 0) {
            return null;
        }
        return new Pix(pixModifySaturation, false);
    }

    public static int pixMeasureSaturation(Pix pix, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixMeasureSaturation(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Pix pixModifyBrightness(Pix pix, Pix pix2, float f) {
        long pixModifyBrightness = JniLeptonicaJNI.pixModifyBrightness(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixModifyBrightness == 0) {
            return null;
        }
        return new Pix(pixModifyBrightness, false);
    }

    public static Pix pixColorShiftRGB(Pix pix, float f, float f2, float f3) {
        long pixColorShiftRGB = JniLeptonicaJNI.pixColorShiftRGB(Pix.getCPtr(pix), pix, f, f2, f3);
        if (pixColorShiftRGB == 0) {
            return null;
        }
        return new Pix(pixColorShiftRGB, false);
    }

    public static Pix pixMultConstantColor(Pix pix, float f, float f2, float f3) {
        long pixMultConstantColor = JniLeptonicaJNI.pixMultConstantColor(Pix.getCPtr(pix), pix, f, f2, f3);
        if (pixMultConstantColor == 0) {
            return null;
        }
        return new Pix(pixMultConstantColor, false);
    }

    public static Pix pixMultMatrixColor(Pix pix, L_Kernel l_Kernel) {
        long pixMultMatrixColor = JniLeptonicaJNI.pixMultMatrixColor(Pix.getCPtr(pix), pix, L_Kernel.getCPtr(l_Kernel), l_Kernel);
        if (pixMultMatrixColor == 0) {
            return null;
        }
        return new Pix(pixMultMatrixColor, false);
    }

    public static Pix pixHalfEdgeByBandpass(Pix pix, int i, int i2, int i3, int i4) {
        long pixHalfEdgeByBandpass = JniLeptonicaJNI.pixHalfEdgeByBandpass(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixHalfEdgeByBandpass == 0) {
            return null;
        }
        return new Pix(pixHalfEdgeByBandpass, false);
    }

    public static int fhmtautogen(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fhmtautogen(Sela.getCPtr(sela), sela, i, str);
    }

    public static int fhmtautogen1(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fhmtautogen1(Sela.getCPtr(sela), sela, i, str);
    }

    public static int fhmtautogen2(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fhmtautogen2(Sela.getCPtr(sela), sela, i, str);
    }

    public static Pix pixHMTDwa_1(Pix pix, Pix pix2, String str) {
        long pixHMTDwa_1 = JniLeptonicaJNI.pixHMTDwa_1(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, str);
        if (pixHMTDwa_1 == 0) {
            return null;
        }
        return new Pix(pixHMTDwa_1, false);
    }

    public static Pix pixFHMTGen_1(Pix pix, Pix pix2, String str) {
        long pixFHMTGen_1 = JniLeptonicaJNI.pixFHMTGen_1(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, str);
        if (pixFHMTGen_1 == 0) {
            return null;
        }
        return new Pix(pixFHMTGen_1, false);
    }

    public static int fhmtgen_low_1(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        return JniLeptonicaJNI.fhmtgen_low_1(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static int pixItalicWords(Pix pix, Boxa boxa, Pix pix2, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, int i) {
        return JniLeptonicaJNI.pixItalicWords(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), i);
    }

    public static int pixOrientDetect(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, int i, int i2) {
        return JniLeptonicaJNI.pixOrientDetect(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), i, i2);
    }

    public static int makeOrientDecision(float f, float f2, float f3, float f4, SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        return JniLeptonicaJNI.makeOrientDecision(f, f2, f3, f4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
    }

    public static int pixUpDownDetect(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2) {
        return JniLeptonicaJNI.pixUpDownDetect(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2);
    }

    public static int pixUpDownDetectGeneral(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixUpDownDetectGeneral(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, i3);
    }

    public static int pixOrientDetectDwa(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, int i, int i2) {
        return JniLeptonicaJNI.pixOrientDetectDwa(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), i, i2);
    }

    public static int pixUpDownDetectDwa(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2) {
        return JniLeptonicaJNI.pixUpDownDetectDwa(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2);
    }

    public static int pixUpDownDetectGeneralDwa(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixUpDownDetectGeneralDwa(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, i3);
    }

    public static int pixMirrorDetect(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2) {
        return JniLeptonicaJNI.pixMirrorDetect(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2);
    }

    public static int pixMirrorDetectDwa(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2) {
        return JniLeptonicaJNI.pixMirrorDetectDwa(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2);
    }

    public static Pix pixFlipFHMTGen(Pix pix, Pix pix2, String str) {
        long pixFlipFHMTGen = JniLeptonicaJNI.pixFlipFHMTGen(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, str);
        if (pixFlipFHMTGen == 0) {
            return null;
        }
        return new Pix(pixFlipFHMTGen, false);
    }

    public static int fmorphautogen(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fmorphautogen(Sela.getCPtr(sela), sela, i, str);
    }

    public static int fmorphautogen1(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fmorphautogen1(Sela.getCPtr(sela), sela, i, str);
    }

    public static int fmorphautogen2(Sela sela, int i, String str) {
        return JniLeptonicaJNI.fmorphautogen2(Sela.getCPtr(sela), sela, i, str);
    }

    public static Pix pixMorphDwa_1(Pix pix, Pix pix2, int i, String str) {
        long pixMorphDwa_1 = JniLeptonicaJNI.pixMorphDwa_1(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, str);
        if (pixMorphDwa_1 == 0) {
            return null;
        }
        return new Pix(pixMorphDwa_1, false);
    }

    public static Pix pixFMorphopGen_1(Pix pix, Pix pix2, int i, String str) {
        long pixFMorphopGen_1 = JniLeptonicaJNI.pixFMorphopGen_1(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, str);
        if (pixFMorphopGen_1 == 0) {
            return null;
        }
        return new Pix(pixFMorphopGen_1, false);
    }

    public static int fmorphopgen_low_1(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        return JniLeptonicaJNI.fmorphopgen_low_1(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static FPix fpixCreate(int i, int i2) {
        long fpixCreate = JniLeptonicaJNI.fpixCreate(i, i2);
        if (fpixCreate == 0) {
            return null;
        }
        return new FPix(fpixCreate, false);
    }

    public static FPix fpixCreateTemplate(FPix fPix) {
        long fpixCreateTemplate = JniLeptonicaJNI.fpixCreateTemplate(FPix.getCPtr(fPix), fPix);
        if (fpixCreateTemplate == 0) {
            return null;
        }
        return new FPix(fpixCreateTemplate, false);
    }

    public static FPix fpixClone(FPix fPix) {
        long fpixClone = JniLeptonicaJNI.fpixClone(FPix.getCPtr(fPix), fPix);
        if (fpixClone == 0) {
            return null;
        }
        return new FPix(fpixClone, false);
    }

    public static FPix fpixCopy(FPix fPix, FPix fPix2) {
        long fpixCopy = JniLeptonicaJNI.fpixCopy(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
        if (fpixCopy == 0) {
            return null;
        }
        return new FPix(fpixCopy, false);
    }

    public static int fpixResizeImageData(FPix fPix, FPix fPix2) {
        return JniLeptonicaJNI.fpixResizeImageData(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
    }

    public static void fpixDestroy(SWIGTYPE_p_p_FPix sWIGTYPE_p_p_FPix) {
        JniLeptonicaJNI.fpixDestroy(SWIGTYPE_p_p_FPix.getCPtr(sWIGTYPE_p_p_FPix));
    }

    public static int fpixGetDimensions(FPix fPix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fpixGetDimensions(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int fpixSetDimensions(FPix fPix, int i, int i2) {
        return JniLeptonicaJNI.fpixSetDimensions(FPix.getCPtr(fPix), fPix, i, i2);
    }

    public static int fpixGetWpl(FPix fPix) {
        return JniLeptonicaJNI.fpixGetWpl(FPix.getCPtr(fPix), fPix);
    }

    public static int fpixSetWpl(FPix fPix, int i) {
        return JniLeptonicaJNI.fpixSetWpl(FPix.getCPtr(fPix), fPix, i);
    }

    public static int fpixGetRefcount(FPix fPix) {
        return JniLeptonicaJNI.fpixGetRefcount(FPix.getCPtr(fPix), fPix);
    }

    public static int fpixChangeRefcount(FPix fPix, int i) {
        return JniLeptonicaJNI.fpixChangeRefcount(FPix.getCPtr(fPix), fPix, i);
    }

    public static int fpixGetResolution(FPix fPix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fpixGetResolution(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int fpixSetResolution(FPix fPix, int i, int i2) {
        return JniLeptonicaJNI.fpixSetResolution(FPix.getCPtr(fPix), fPix, i, i2);
    }

    public static int fpixCopyResolution(FPix fPix, FPix fPix2) {
        return JniLeptonicaJNI.fpixCopyResolution(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
    }

    public static SWIGTYPE_p_float fpixGetData(FPix fPix) {
        long fpixGetData = JniLeptonicaJNI.fpixGetData(FPix.getCPtr(fPix), fPix);
        if (fpixGetData == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(fpixGetData, false);
    }

    public static int fpixSetData(FPix fPix, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.fpixSetData(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int fpixGetPixel(FPix fPix, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.fpixGetPixel(FPix.getCPtr(fPix), fPix, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int fpixSetPixel(FPix fPix, int i, int i2, float f) {
        return JniLeptonicaJNI.fpixSetPixel(FPix.getCPtr(fPix), fPix, i, i2, f);
    }

    public static FPixa fpixaCreate(int i) {
        long fpixaCreate = JniLeptonicaJNI.fpixaCreate(i);
        if (fpixaCreate == 0) {
            return null;
        }
        return new FPixa(fpixaCreate, false);
    }

    public static FPixa fpixaCopy(FPixa fPixa, int i) {
        long fpixaCopy = JniLeptonicaJNI.fpixaCopy(FPixa.getCPtr(fPixa), fPixa, i);
        if (fpixaCopy == 0) {
            return null;
        }
        return new FPixa(fpixaCopy, false);
    }

    public static void fpixaDestroy(SWIGTYPE_p_p_FPixa sWIGTYPE_p_p_FPixa) {
        JniLeptonicaJNI.fpixaDestroy(SWIGTYPE_p_p_FPixa.getCPtr(sWIGTYPE_p_p_FPixa));
    }

    public static int fpixaAddFPix(FPixa fPixa, FPix fPix, int i) {
        return JniLeptonicaJNI.fpixaAddFPix(FPixa.getCPtr(fPixa), fPixa, FPix.getCPtr(fPix), fPix, i);
    }

    public static int fpixaGetCount(FPixa fPixa) {
        return JniLeptonicaJNI.fpixaGetCount(FPixa.getCPtr(fPixa), fPixa);
    }

    public static int fpixaChangeRefcount(FPixa fPixa, int i) {
        return JniLeptonicaJNI.fpixaChangeRefcount(FPixa.getCPtr(fPixa), fPixa, i);
    }

    public static FPix fpixaGetFPix(FPixa fPixa, int i, int i2) {
        long fpixaGetFPix = JniLeptonicaJNI.fpixaGetFPix(FPixa.getCPtr(fPixa), fPixa, i, i2);
        if (fpixaGetFPix == 0) {
            return null;
        }
        return new FPix(fpixaGetFPix, false);
    }

    public static int fpixaGetFPixDimensions(FPixa fPixa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fpixaGetFPixDimensions(FPixa.getCPtr(fPixa), fPixa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int fpixaGetPixel(FPixa fPixa, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.fpixaGetPixel(FPixa.getCPtr(fPixa), fPixa, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int fpixaSetPixel(FPixa fPixa, int i, int i2, int i3, float f) {
        return JniLeptonicaJNI.fpixaSetPixel(FPixa.getCPtr(fPixa), fPixa, i, i2, i3, f);
    }

    public static DPix dpixCreate(int i, int i2) {
        long dpixCreate = JniLeptonicaJNI.dpixCreate(i, i2);
        if (dpixCreate == 0) {
            return null;
        }
        return new DPix(dpixCreate, false);
    }

    public static DPix dpixCreateTemplate(DPix dPix) {
        long dpixCreateTemplate = JniLeptonicaJNI.dpixCreateTemplate(DPix.getCPtr(dPix), dPix);
        if (dpixCreateTemplate == 0) {
            return null;
        }
        return new DPix(dpixCreateTemplate, false);
    }

    public static DPix dpixClone(DPix dPix) {
        long dpixClone = JniLeptonicaJNI.dpixClone(DPix.getCPtr(dPix), dPix);
        if (dpixClone == 0) {
            return null;
        }
        return new DPix(dpixClone, false);
    }

    public static DPix dpixCopy(DPix dPix, DPix dPix2) {
        long dpixCopy = JniLeptonicaJNI.dpixCopy(DPix.getCPtr(dPix), dPix, DPix.getCPtr(dPix2), dPix2);
        if (dpixCopy == 0) {
            return null;
        }
        return new DPix(dpixCopy, false);
    }

    public static int dpixResizeImageData(DPix dPix, DPix dPix2) {
        return JniLeptonicaJNI.dpixResizeImageData(DPix.getCPtr(dPix), dPix, DPix.getCPtr(dPix2), dPix2);
    }

    public static void dpixDestroy(SWIGTYPE_p_p_DPix sWIGTYPE_p_p_DPix) {
        JniLeptonicaJNI.dpixDestroy(SWIGTYPE_p_p_DPix.getCPtr(sWIGTYPE_p_p_DPix));
    }

    public static int dpixGetDimensions(DPix dPix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.dpixGetDimensions(DPix.getCPtr(dPix), dPix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int dpixSetDimensions(DPix dPix, int i, int i2) {
        return JniLeptonicaJNI.dpixSetDimensions(DPix.getCPtr(dPix), dPix, i, i2);
    }

    public static int dpixGetWpl(DPix dPix) {
        return JniLeptonicaJNI.dpixGetWpl(DPix.getCPtr(dPix), dPix);
    }

    public static int dpixSetWpl(DPix dPix, int i) {
        return JniLeptonicaJNI.dpixSetWpl(DPix.getCPtr(dPix), dPix, i);
    }

    public static int dpixGetRefcount(DPix dPix) {
        return JniLeptonicaJNI.dpixGetRefcount(DPix.getCPtr(dPix), dPix);
    }

    public static int dpixChangeRefcount(DPix dPix, int i) {
        return JniLeptonicaJNI.dpixChangeRefcount(DPix.getCPtr(dPix), dPix, i);
    }

    public static int dpixGetResolution(DPix dPix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.dpixGetResolution(DPix.getCPtr(dPix), dPix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int dpixSetResolution(DPix dPix, int i, int i2) {
        return JniLeptonicaJNI.dpixSetResolution(DPix.getCPtr(dPix), dPix, i, i2);
    }

    public static int dpixCopyResolution(DPix dPix, DPix dPix2) {
        return JniLeptonicaJNI.dpixCopyResolution(DPix.getCPtr(dPix), dPix, DPix.getCPtr(dPix2), dPix2);
    }

    public static SWIGTYPE_p_double dpixGetData(DPix dPix) {
        long dpixGetData = JniLeptonicaJNI.dpixGetData(DPix.getCPtr(dPix), dPix);
        if (dpixGetData == 0) {
            return null;
        }
        return new SWIGTYPE_p_double(dpixGetData, false);
    }

    public static int dpixSetData(DPix dPix, SWIGTYPE_p_double sWIGTYPE_p_double) {
        return JniLeptonicaJNI.dpixSetData(DPix.getCPtr(dPix), dPix, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public static int dpixGetPixel(DPix dPix, int i, int i2, SWIGTYPE_p_double sWIGTYPE_p_double) {
        return JniLeptonicaJNI.dpixGetPixel(DPix.getCPtr(dPix), dPix, i, i2, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double));
    }

    public static int dpixSetPixel(DPix dPix, int i, int i2, double d) {
        return JniLeptonicaJNI.dpixSetPixel(DPix.getCPtr(dPix), dPix, i, i2, d);
    }

    public static FPix fpixRead(String str) {
        long fpixRead = JniLeptonicaJNI.fpixRead(str);
        if (fpixRead == 0) {
            return null;
        }
        return new FPix(fpixRead, false);
    }

    public static FPix fpixReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long fpixReadStream = JniLeptonicaJNI.fpixReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (fpixReadStream == 0) {
            return null;
        }
        return new FPix(fpixReadStream, false);
    }

    public static int fpixWrite(String str, FPix fPix) {
        return JniLeptonicaJNI.fpixWrite(str, FPix.getCPtr(fPix), fPix);
    }

    public static int fpixWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, FPix fPix) {
        return JniLeptonicaJNI.fpixWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), FPix.getCPtr(fPix), fPix);
    }

    public static FPix fpixEndianByteSwap(FPix fPix, FPix fPix2) {
        long fpixEndianByteSwap = JniLeptonicaJNI.fpixEndianByteSwap(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
        if (fpixEndianByteSwap == 0) {
            return null;
        }
        return new FPix(fpixEndianByteSwap, false);
    }

    public static DPix dpixRead(String str) {
        long dpixRead = JniLeptonicaJNI.dpixRead(str);
        if (dpixRead == 0) {
            return null;
        }
        return new DPix(dpixRead, false);
    }

    public static DPix dpixReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long dpixReadStream = JniLeptonicaJNI.dpixReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (dpixReadStream == 0) {
            return null;
        }
        return new DPix(dpixReadStream, false);
    }

    public static int dpixWrite(String str, DPix dPix) {
        return JniLeptonicaJNI.dpixWrite(str, DPix.getCPtr(dPix), dPix);
    }

    public static int dpixWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, DPix dPix) {
        return JniLeptonicaJNI.dpixWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), DPix.getCPtr(dPix), dPix);
    }

    public static DPix dpixEndianByteSwap(DPix dPix, DPix dPix2) {
        long dpixEndianByteSwap = JniLeptonicaJNI.dpixEndianByteSwap(DPix.getCPtr(dPix), dPix, DPix.getCPtr(dPix2), dPix2);
        if (dpixEndianByteSwap == 0) {
            return null;
        }
        return new DPix(dpixEndianByteSwap, false);
    }

    public static int fpixPrintStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, FPix fPix, int i) {
        return JniLeptonicaJNI.fpixPrintStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), FPix.getCPtr(fPix), fPix, i);
    }

    public static FPix pixConvertToFPix(Pix pix, int i) {
        long pixConvertToFPix = JniLeptonicaJNI.pixConvertToFPix(Pix.getCPtr(pix), pix, i);
        if (pixConvertToFPix == 0) {
            return null;
        }
        return new FPix(pixConvertToFPix, false);
    }

    public static DPix pixConvertToDPix(Pix pix, int i) {
        long pixConvertToDPix = JniLeptonicaJNI.pixConvertToDPix(Pix.getCPtr(pix), pix, i);
        if (pixConvertToDPix == 0) {
            return null;
        }
        return new DPix(pixConvertToDPix, false);
    }

    public static Pix fpixConvertToPix(FPix fPix, int i, int i2, int i3) {
        long fpixConvertToPix = JniLeptonicaJNI.fpixConvertToPix(FPix.getCPtr(fPix), fPix, i, i2, i3);
        if (fpixConvertToPix == 0) {
            return null;
        }
        return new Pix(fpixConvertToPix, false);
    }

    public static Pix fpixDisplayMaxDynamicRange(FPix fPix) {
        long fpixDisplayMaxDynamicRange = JniLeptonicaJNI.fpixDisplayMaxDynamicRange(FPix.getCPtr(fPix), fPix);
        if (fpixDisplayMaxDynamicRange == 0) {
            return null;
        }
        return new Pix(fpixDisplayMaxDynamicRange, false);
    }

    public static DPix fpixConvertToDPix(FPix fPix) {
        long fpixConvertToDPix = JniLeptonicaJNI.fpixConvertToDPix(FPix.getCPtr(fPix), fPix);
        if (fpixConvertToDPix == 0) {
            return null;
        }
        return new DPix(fpixConvertToDPix, false);
    }

    public static Pix dpixConvertToPix(DPix dPix, int i, int i2, int i3) {
        long dpixConvertToPix = JniLeptonicaJNI.dpixConvertToPix(DPix.getCPtr(dPix), dPix, i, i2, i3);
        if (dpixConvertToPix == 0) {
            return null;
        }
        return new Pix(dpixConvertToPix, false);
    }

    public static FPix dpixConvertToFPix(DPix dPix) {
        long dpixConvertToFPix = JniLeptonicaJNI.dpixConvertToFPix(DPix.getCPtr(dPix), dPix);
        if (dpixConvertToFPix == 0) {
            return null;
        }
        return new FPix(dpixConvertToFPix, false);
    }

    public static int fpixGetMin(FPix fPix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fpixGetMin(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int fpixGetMax(FPix fPix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fpixGetMax(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int dpixGetMin(DPix dPix, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.dpixGetMin(DPix.getCPtr(dPix), dPix, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int dpixGetMax(DPix dPix, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.dpixGetMax(DPix.getCPtr(dPix), dPix, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static FPix fpixScaleByInteger(FPix fPix, int i) {
        long fpixScaleByInteger = JniLeptonicaJNI.fpixScaleByInteger(FPix.getCPtr(fPix), fPix, i);
        if (fpixScaleByInteger == 0) {
            return null;
        }
        return new FPix(fpixScaleByInteger, false);
    }

    public static DPix dpixScaleByInteger(DPix dPix, int i) {
        long dpixScaleByInteger = JniLeptonicaJNI.dpixScaleByInteger(DPix.getCPtr(dPix), dPix, i);
        if (dpixScaleByInteger == 0) {
            return null;
        }
        return new DPix(dpixScaleByInteger, false);
    }

    public static FPix fpixLinearCombination(FPix fPix, FPix fPix2, FPix fPix3, float f, float f2) {
        long fpixLinearCombination = JniLeptonicaJNI.fpixLinearCombination(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2, FPix.getCPtr(fPix3), fPix3, f, f2);
        if (fpixLinearCombination == 0) {
            return null;
        }
        return new FPix(fpixLinearCombination, false);
    }

    public static int fpixAddMultConstant(FPix fPix, float f, float f2) {
        return JniLeptonicaJNI.fpixAddMultConstant(FPix.getCPtr(fPix), fPix, f, f2);
    }

    public static DPix dpixLinearCombination(DPix dPix, DPix dPix2, DPix dPix3, float f, float f2) {
        long dpixLinearCombination = JniLeptonicaJNI.dpixLinearCombination(DPix.getCPtr(dPix), dPix, DPix.getCPtr(dPix2), dPix2, DPix.getCPtr(dPix3), dPix3, f, f2);
        if (dpixLinearCombination == 0) {
            return null;
        }
        return new DPix(dpixLinearCombination, false);
    }

    public static int dpixAddMultConstant(DPix dPix, double d, double d2) {
        return JniLeptonicaJNI.dpixAddMultConstant(DPix.getCPtr(dPix), dPix, d, d2);
    }

    public static int fpixSetAllArbitrary(FPix fPix, float f) {
        return JniLeptonicaJNI.fpixSetAllArbitrary(FPix.getCPtr(fPix), fPix, f);
    }

    public static int dpixSetAllArbitrary(DPix dPix, double d) {
        return JniLeptonicaJNI.dpixSetAllArbitrary(DPix.getCPtr(dPix), dPix, d);
    }

    public static FPix fpixAddBorder(FPix fPix, int i, int i2, int i3, int i4) {
        long fpixAddBorder = JniLeptonicaJNI.fpixAddBorder(FPix.getCPtr(fPix), fPix, i, i2, i3, i4);
        if (fpixAddBorder == 0) {
            return null;
        }
        return new FPix(fpixAddBorder, false);
    }

    public static FPix fpixRemoveBorder(FPix fPix, int i, int i2, int i3, int i4) {
        long fpixRemoveBorder = JniLeptonicaJNI.fpixRemoveBorder(FPix.getCPtr(fPix), fPix, i, i2, i3, i4);
        if (fpixRemoveBorder == 0) {
            return null;
        }
        return new FPix(fpixRemoveBorder, false);
    }

    public static FPix fpixAddMirroredBorder(FPix fPix, int i, int i2, int i3, int i4) {
        long fpixAddMirroredBorder = JniLeptonicaJNI.fpixAddMirroredBorder(FPix.getCPtr(fPix), fPix, i, i2, i3, i4);
        if (fpixAddMirroredBorder == 0) {
            return null;
        }
        return new FPix(fpixAddMirroredBorder, false);
    }

    public static FPix fpixAddContinuedBorder(FPix fPix, int i, int i2, int i3, int i4) {
        long fpixAddContinuedBorder = JniLeptonicaJNI.fpixAddContinuedBorder(FPix.getCPtr(fPix), fPix, i, i2, i3, i4);
        if (fpixAddContinuedBorder == 0) {
            return null;
        }
        return new FPix(fpixAddContinuedBorder, false);
    }

    public static FPix fpixAddSlopeBorder(FPix fPix, int i, int i2, int i3, int i4) {
        long fpixAddSlopeBorder = JniLeptonicaJNI.fpixAddSlopeBorder(FPix.getCPtr(fPix), fPix, i, i2, i3, i4);
        if (fpixAddSlopeBorder == 0) {
            return null;
        }
        return new FPix(fpixAddSlopeBorder, false);
    }

    public static int fpixRasterop(FPix fPix, int i, int i2, int i3, int i4, FPix fPix2, int i5, int i6) {
        return JniLeptonicaJNI.fpixRasterop(FPix.getCPtr(fPix), fPix, i, i2, i3, i4, FPix.getCPtr(fPix2), fPix2, i5, i6);
    }

    public static FPix fpixRotateOrth(FPix fPix, int i) {
        long fpixRotateOrth = JniLeptonicaJNI.fpixRotateOrth(FPix.getCPtr(fPix), fPix, i);
        if (fpixRotateOrth == 0) {
            return null;
        }
        return new FPix(fpixRotateOrth, false);
    }

    public static FPix fpixRotate180(FPix fPix, FPix fPix2) {
        long fpixRotate180 = JniLeptonicaJNI.fpixRotate180(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
        if (fpixRotate180 == 0) {
            return null;
        }
        return new FPix(fpixRotate180, false);
    }

    public static FPix fpixRotate90(FPix fPix, int i) {
        long fpixRotate90 = JniLeptonicaJNI.fpixRotate90(FPix.getCPtr(fPix), fPix, i);
        if (fpixRotate90 == 0) {
            return null;
        }
        return new FPix(fpixRotate90, false);
    }

    public static FPix fpixFlipLR(FPix fPix, FPix fPix2) {
        long fpixFlipLR = JniLeptonicaJNI.fpixFlipLR(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
        if (fpixFlipLR == 0) {
            return null;
        }
        return new FPix(fpixFlipLR, false);
    }

    public static FPix fpixFlipTB(FPix fPix, FPix fPix2) {
        long fpixFlipTB = JniLeptonicaJNI.fpixFlipTB(FPix.getCPtr(fPix), fPix, FPix.getCPtr(fPix2), fPix2);
        if (fpixFlipTB == 0) {
            return null;
        }
        return new FPix(fpixFlipTB, false);
    }

    public static FPix fpixAffinePta(FPix fPix, Pta pta, Pta pta2, int i, float f) {
        long fpixAffinePta = JniLeptonicaJNI.fpixAffinePta(FPix.getCPtr(fPix), fPix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i, f);
        if (fpixAffinePta == 0) {
            return null;
        }
        return new FPix(fpixAffinePta, false);
    }

    public static FPix fpixAffine(FPix fPix, SWIGTYPE_p_float sWIGTYPE_p_float, float f) {
        long fpixAffine = JniLeptonicaJNI.fpixAffine(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), f);
        if (fpixAffine == 0) {
            return null;
        }
        return new FPix(fpixAffine, false);
    }

    public static FPix fpixProjectivePta(FPix fPix, Pta pta, Pta pta2, int i, float f) {
        long fpixProjectivePta = JniLeptonicaJNI.fpixProjectivePta(FPix.getCPtr(fPix), fPix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i, f);
        if (fpixProjectivePta == 0) {
            return null;
        }
        return new FPix(fpixProjectivePta, false);
    }

    public static FPix fpixProjective(FPix fPix, SWIGTYPE_p_float sWIGTYPE_p_float, float f) {
        long fpixProjective = JniLeptonicaJNI.fpixProjective(FPix.getCPtr(fPix), fPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), f);
        if (fpixProjective == 0) {
            return null;
        }
        return new FPix(fpixProjective, false);
    }

    public static int linearInterpolatePixelFloat(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, float f, float f2, float f3, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.linearInterpolatePixelFloat(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, f, f2, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static Pix fpixThresholdToPix(FPix fPix, float f) {
        long fpixThresholdToPix = JniLeptonicaJNI.fpixThresholdToPix(FPix.getCPtr(fPix), fPix, f);
        if (fpixThresholdToPix == 0) {
            return null;
        }
        return new Pix(fpixThresholdToPix, false);
    }

    public static FPix pixComponentFunction(Pix pix, float f, float f2, float f3, float f4, float f5, float f6) {
        long pixComponentFunction = JniLeptonicaJNI.pixComponentFunction(Pix.getCPtr(pix), pix, f, f2, f3, f4, f5, f6);
        if (pixComponentFunction == 0) {
            return null;
        }
        return new FPix(pixComponentFunction, false);
    }

    public static Pix pixReadStreamGif(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamGif = JniLeptonicaJNI.pixReadStreamGif(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamGif == 0) {
            return null;
        }
        return new Pix(pixReadStreamGif, false);
    }

    public static int pixWriteStreamGif(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix) {
        return JniLeptonicaJNI.pixWriteStreamGif(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix);
    }

    public static Pix pixReadMemGif(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemGif = JniLeptonicaJNI.pixReadMemGif(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemGif == 0) {
            return null;
        }
        return new Pix(pixReadMemGif, false);
    }

    public static int pixWriteMemGif(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix) {
        return JniLeptonicaJNI.pixWriteMemGif(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix);
    }

    public static GPlot gplotCreate(String str, int i, String str2, String str3, String str4) {
        long gplotCreate = JniLeptonicaJNI.gplotCreate(str, i, str2, str3, str4);
        if (gplotCreate == 0) {
            return null;
        }
        return new GPlot(gplotCreate, false);
    }

    public static void gplotDestroy(SWIGTYPE_p_p_GPlot sWIGTYPE_p_p_GPlot) {
        JniLeptonicaJNI.gplotDestroy(SWIGTYPE_p_p_GPlot.getCPtr(sWIGTYPE_p_p_GPlot));
    }

    public static int gplotAddPlot(GPlot gPlot, Numa numa, Numa numa2, int i, String str) {
        return JniLeptonicaJNI.gplotAddPlot(GPlot.getCPtr(gPlot), gPlot, Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, str);
    }

    public static int gplotSetScaling(GPlot gPlot, int i) {
        return JniLeptonicaJNI.gplotSetScaling(GPlot.getCPtr(gPlot), gPlot, i);
    }

    public static int gplotMakeOutput(GPlot gPlot) {
        return JniLeptonicaJNI.gplotMakeOutput(GPlot.getCPtr(gPlot), gPlot);
    }

    public static int gplotGenCommandFile(GPlot gPlot) {
        return JniLeptonicaJNI.gplotGenCommandFile(GPlot.getCPtr(gPlot), gPlot);
    }

    public static int gplotGenDataFiles(GPlot gPlot) {
        return JniLeptonicaJNI.gplotGenDataFiles(GPlot.getCPtr(gPlot), gPlot);
    }

    public static int gplotSimple1(Numa numa, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimple1(Numa.getCPtr(numa), numa, i, str, str2);
    }

    public static int gplotSimple2(Numa numa, Numa numa2, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimple2(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, str, str2);
    }

    public static int gplotSimpleN(Numaa numaa, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimpleN(Numaa.getCPtr(numaa), numaa, i, str, str2);
    }

    public static int gplotSimpleXY1(Numa numa, Numa numa2, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimpleXY1(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, str, str2);
    }

    public static int gplotSimpleXY2(Numa numa, Numa numa2, Numa numa3, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimpleXY2(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, Numa.getCPtr(numa3), numa3, i, str, str2);
    }

    public static int gplotSimpleXYN(Numa numa, Numaa numaa, int i, String str, String str2) {
        return JniLeptonicaJNI.gplotSimpleXYN(Numa.getCPtr(numa), numa, Numaa.getCPtr(numaa), numaa, i, str, str2);
    }

    public static GPlot gplotRead(String str) {
        long gplotRead = JniLeptonicaJNI.gplotRead(str);
        if (gplotRead == 0) {
            return null;
        }
        return new GPlot(gplotRead, false);
    }

    public static int gplotWrite(String str, GPlot gPlot) {
        return JniLeptonicaJNI.gplotWrite(str, GPlot.getCPtr(gPlot), gPlot);
    }

    public static Pta generatePtaLine(int i, int i2, int i3, int i4) {
        long generatePtaLine = JniLeptonicaJNI.generatePtaLine(i, i2, i3, i4);
        if (generatePtaLine == 0) {
            return null;
        }
        return new Pta(generatePtaLine, false);
    }

    public static Pta generatePtaWideLine(int i, int i2, int i3, int i4, int i5) {
        long generatePtaWideLine = JniLeptonicaJNI.generatePtaWideLine(i, i2, i3, i4, i5);
        if (generatePtaWideLine == 0) {
            return null;
        }
        return new Pta(generatePtaWideLine, false);
    }

    public static Pta generatePtaBox(Box box, int i) {
        long generatePtaBox = JniLeptonicaJNI.generatePtaBox(Box.getCPtr(box), box, i);
        if (generatePtaBox == 0) {
            return null;
        }
        return new Pta(generatePtaBox, false);
    }

    public static Pta generatePtaBoxa(Boxa boxa, int i, int i2) {
        long generatePtaBoxa = JniLeptonicaJNI.generatePtaBoxa(Boxa.getCPtr(boxa), boxa, i, i2);
        if (generatePtaBoxa == 0) {
            return null;
        }
        return new Pta(generatePtaBoxa, false);
    }

    public static Pta generatePtaHashBox(Box box, int i, int i2, int i3, int i4) {
        long generatePtaHashBox = JniLeptonicaJNI.generatePtaHashBox(Box.getCPtr(box), box, i, i2, i3, i4);
        if (generatePtaHashBox == 0) {
            return null;
        }
        return new Pta(generatePtaHashBox, false);
    }

    public static Pta generatePtaHashBoxa(Boxa boxa, int i, int i2, int i3, int i4, int i5) {
        long generatePtaHashBoxa = JniLeptonicaJNI.generatePtaHashBoxa(Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, i5);
        if (generatePtaHashBoxa == 0) {
            return null;
        }
        return new Pta(generatePtaHashBoxa, false);
    }

    public static Ptaa generatePtaaBoxa(Boxa boxa) {
        long generatePtaaBoxa = JniLeptonicaJNI.generatePtaaBoxa(Boxa.getCPtr(boxa), boxa);
        if (generatePtaaBoxa == 0) {
            return null;
        }
        return new Ptaa(generatePtaaBoxa, false);
    }

    public static Ptaa generatePtaaHashBoxa(Boxa boxa, int i, int i2, int i3, int i4) {
        long generatePtaaHashBoxa = JniLeptonicaJNI.generatePtaaHashBoxa(Boxa.getCPtr(boxa), boxa, i, i2, i3, i4);
        if (generatePtaaHashBoxa == 0) {
            return null;
        }
        return new Ptaa(generatePtaaHashBoxa, false);
    }

    public static Pta generatePtaPolyline(Pta pta, int i, int i2, int i3) {
        long generatePtaPolyline = JniLeptonicaJNI.generatePtaPolyline(Pta.getCPtr(pta), pta, i, i2, i3);
        if (generatePtaPolyline == 0) {
            return null;
        }
        return new Pta(generatePtaPolyline, false);
    }

    public static Pta convertPtaLineTo4cc(Pta pta) {
        long convertPtaLineTo4cc = JniLeptonicaJNI.convertPtaLineTo4cc(Pta.getCPtr(pta), pta);
        if (convertPtaLineTo4cc == 0) {
            return null;
        }
        return new Pta(convertPtaLineTo4cc, false);
    }

    public static Pta generatePtaFilledCircle(int i) {
        long generatePtaFilledCircle = JniLeptonicaJNI.generatePtaFilledCircle(i);
        if (generatePtaFilledCircle == 0) {
            return null;
        }
        return new Pta(generatePtaFilledCircle, false);
    }

    public static Pta generatePtaFilledSquare(int i) {
        long generatePtaFilledSquare = JniLeptonicaJNI.generatePtaFilledSquare(i);
        if (generatePtaFilledSquare == 0) {
            return null;
        }
        return new Pta(generatePtaFilledSquare, false);
    }

    public static Pta generatePtaLineFromPt(int i, int i2, double d, double d2) {
        long generatePtaLineFromPt = JniLeptonicaJNI.generatePtaLineFromPt(i, i2, d, d2);
        if (generatePtaLineFromPt == 0) {
            return null;
        }
        return new Pta(generatePtaLineFromPt, false);
    }

    public static int locatePtRadially(int i, int i2, double d, double d2, SWIGTYPE_p_double sWIGTYPE_p_double, SWIGTYPE_p_double sWIGTYPE_p_double2) {
        return JniLeptonicaJNI.locatePtRadially(i, i2, d, d2, SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double), SWIGTYPE_p_double.getCPtr(sWIGTYPE_p_double2));
    }

    public static int pixRenderPlotFromNuma(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, Numa numa, int i, int i2, int i3, long j) {
        return JniLeptonicaJNI.pixRenderPlotFromNuma(SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), Numa.getCPtr(numa), numa, i, i2, i3, j);
    }

    public static Pta makePlotPtaFromNuma(Numa numa, int i, int i2, int i3, int i4) {
        long makePlotPtaFromNuma = JniLeptonicaJNI.makePlotPtaFromNuma(Numa.getCPtr(numa), numa, i, i2, i3, i4);
        if (makePlotPtaFromNuma == 0) {
            return null;
        }
        return new Pta(makePlotPtaFromNuma, false);
    }

    public static int pixRenderPlotFromNumaGen(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, Numa numa, int i, int i2, int i3, int i4, int i5, long j) {
        return JniLeptonicaJNI.pixRenderPlotFromNumaGen(SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), Numa.getCPtr(numa), numa, i, i2, i3, i4, i5, j);
    }

    public static Pta makePlotPtaFromNumaGen(Numa numa, int i, int i2, int i3, int i4, int i5) {
        long makePlotPtaFromNumaGen = JniLeptonicaJNI.makePlotPtaFromNumaGen(Numa.getCPtr(numa), numa, i, i2, i3, i4, i5);
        if (makePlotPtaFromNumaGen == 0) {
            return null;
        }
        return new Pta(makePlotPtaFromNumaGen, false);
    }

    public static int pixRenderPta(Pix pix, Pta pta, int i) {
        return JniLeptonicaJNI.pixRenderPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i);
    }

    public static int pixRenderPtaArb(Pix pix, Pta pta, short s, short s2, short s3) {
        return JniLeptonicaJNI.pixRenderPtaArb(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, s, s2, s3);
    }

    public static int pixRenderPtaBlend(Pix pix, Pta pta, short s, short s2, short s3, float f) {
        return JniLeptonicaJNI.pixRenderPtaBlend(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, s, s2, s3, f);
    }

    public static int pixRenderLine(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        return JniLeptonicaJNI.pixRenderLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
    }

    public static int pixRenderLineArb(Pix pix, int i, int i2, int i3, int i4, int i5, short s, short s2, short s3) {
        return JniLeptonicaJNI.pixRenderLineArb(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, s, s2, s3);
    }

    public static int pixRenderLineBlend(Pix pix, int i, int i2, int i3, int i4, int i5, short s, short s2, short s3, float f) {
        return JniLeptonicaJNI.pixRenderLineBlend(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, s, s2, s3, f);
    }

    public static int pixRenderBox(Pix pix, Box box, int i, int i2) {
        return JniLeptonicaJNI.pixRenderBox(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2);
    }

    public static int pixRenderBoxArb(Pix pix, Box box, int i, short s, short s2, short s3) {
        return JniLeptonicaJNI.pixRenderBoxArb(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, s, s2, s3);
    }

    public static int pixRenderBoxBlend(Pix pix, Box box, int i, short s, short s2, short s3, float f) {
        return JniLeptonicaJNI.pixRenderBoxBlend(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, s, s2, s3, f);
    }

    public static int pixRenderBoxa(Pix pix, Boxa boxa, int i, int i2) {
        return JniLeptonicaJNI.pixRenderBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2);
    }

    public static int pixRenderBoxaArb(Pix pix, Boxa boxa, int i, short s, short s2, short s3) {
        return JniLeptonicaJNI.pixRenderBoxaArb(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, s, s2, s3);
    }

    public static int pixRenderBoxaBlend(Pix pix, Boxa boxa, int i, short s, short s2, short s3, float f, int i2) {
        return JniLeptonicaJNI.pixRenderBoxaBlend(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, s, s2, s3, f, i2);
    }

    public static int pixRenderHashBox(Pix pix, Box box, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixRenderHashBox(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5);
    }

    public static int pixRenderHashBoxArb(Pix pix, Box box, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return JniLeptonicaJNI.pixRenderHashBoxArb(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5, i6, i7);
    }

    public static int pixRenderHashBoxBlend(Pix pix, Box box, int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        return JniLeptonicaJNI.pixRenderHashBoxBlend(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5, i6, i7, f);
    }

    public static int pixRenderHashBoxa(Pix pix, Boxa boxa, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixRenderHashBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, i5);
    }

    public static int pixRenderHashBoxaArb(Pix pix, Boxa boxa, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return JniLeptonicaJNI.pixRenderHashBoxaArb(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, i5, i6, i7);
    }

    public static int pixRenderHashBoxaBlend(Pix pix, Boxa boxa, int i, int i2, int i3, int i4, int i5, int i6, int i7, float f) {
        return JniLeptonicaJNI.pixRenderHashBoxaBlend(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2, i3, i4, i5, i6, i7, f);
    }

    public static int pixRenderPolyline(Pix pix, Pta pta, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixRenderPolyline(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i, i2, i3);
    }

    public static int pixRenderPolylineArb(Pix pix, Pta pta, int i, short s, short s2, short s3, int i2) {
        return JniLeptonicaJNI.pixRenderPolylineArb(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i, s, s2, s3, i2);
    }

    public static int pixRenderPolylineBlend(Pix pix, Pta pta, int i, short s, short s2, short s3, float f, int i2, int i3) {
        return JniLeptonicaJNI.pixRenderPolylineBlend(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i, s, s2, s3, f, i2, i3);
    }

    public static Pix pixRenderRandomCmapPtaa(Pix pix, Ptaa ptaa, int i, int i2, int i3) {
        long pixRenderRandomCmapPtaa = JniLeptonicaJNI.pixRenderRandomCmapPtaa(Pix.getCPtr(pix), pix, Ptaa.getCPtr(ptaa), ptaa, i, i2, i3);
        if (pixRenderRandomCmapPtaa == 0) {
            return null;
        }
        return new Pix(pixRenderRandomCmapPtaa, false);
    }

    public static Pix pixRenderPolygon(Pta pta, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long pixRenderPolygon = JniLeptonicaJNI.pixRenderPolygon(Pta.getCPtr(pta), pta, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (pixRenderPolygon == 0) {
            return null;
        }
        return new Pix(pixRenderPolygon, false);
    }

    public static Pix pixFillPolygon(Pix pix, Pta pta, int i, int i2) {
        long pixFillPolygon = JniLeptonicaJNI.pixFillPolygon(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i, i2);
        if (pixFillPolygon == 0) {
            return null;
        }
        return new Pix(pixFillPolygon, false);
    }

    public static Pix pixRenderContours(Pix pix, int i, int i2, int i3) {
        long pixRenderContours = JniLeptonicaJNI.pixRenderContours(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixRenderContours == 0) {
            return null;
        }
        return new Pix(pixRenderContours, false);
    }

    public static Pix fpixAutoRenderContours(FPix fPix, int i) {
        long fpixAutoRenderContours = JniLeptonicaJNI.fpixAutoRenderContours(FPix.getCPtr(fPix), fPix, i);
        if (fpixAutoRenderContours == 0) {
            return null;
        }
        return new Pix(fpixAutoRenderContours, false);
    }

    public static Pix fpixRenderContours(FPix fPix, float f, float f2) {
        long fpixRenderContours = JniLeptonicaJNI.fpixRenderContours(FPix.getCPtr(fPix), fPix, f, f2);
        if (fpixRenderContours == 0) {
            return null;
        }
        return new Pix(fpixRenderContours, false);
    }

    public static Pix pixErodeGray(Pix pix, int i, int i2) {
        long pixErodeGray = JniLeptonicaJNI.pixErodeGray(Pix.getCPtr(pix), pix, i, i2);
        if (pixErodeGray == 0) {
            return null;
        }
        return new Pix(pixErodeGray, false);
    }

    public static Pix pixDilateGray(Pix pix, int i, int i2) {
        long pixDilateGray = JniLeptonicaJNI.pixDilateGray(Pix.getCPtr(pix), pix, i, i2);
        if (pixDilateGray == 0) {
            return null;
        }
        return new Pix(pixDilateGray, false);
    }

    public static Pix pixOpenGray(Pix pix, int i, int i2) {
        long pixOpenGray = JniLeptonicaJNI.pixOpenGray(Pix.getCPtr(pix), pix, i, i2);
        if (pixOpenGray == 0) {
            return null;
        }
        return new Pix(pixOpenGray, false);
    }

    public static Pix pixCloseGray(Pix pix, int i, int i2) {
        long pixCloseGray = JniLeptonicaJNI.pixCloseGray(Pix.getCPtr(pix), pix, i, i2);
        if (pixCloseGray == 0) {
            return null;
        }
        return new Pix(pixCloseGray, false);
    }

    public static Pix pixErodeGray3(Pix pix, int i, int i2) {
        long pixErodeGray3 = JniLeptonicaJNI.pixErodeGray3(Pix.getCPtr(pix), pix, i, i2);
        if (pixErodeGray3 == 0) {
            return null;
        }
        return new Pix(pixErodeGray3, false);
    }

    public static Pix pixDilateGray3(Pix pix, int i, int i2) {
        long pixDilateGray3 = JniLeptonicaJNI.pixDilateGray3(Pix.getCPtr(pix), pix, i, i2);
        if (pixDilateGray3 == 0) {
            return null;
        }
        return new Pix(pixDilateGray3, false);
    }

    public static Pix pixOpenGray3(Pix pix, int i, int i2) {
        long pixOpenGray3 = JniLeptonicaJNI.pixOpenGray3(Pix.getCPtr(pix), pix, i, i2);
        if (pixOpenGray3 == 0) {
            return null;
        }
        return new Pix(pixOpenGray3, false);
    }

    public static Pix pixCloseGray3(Pix pix, int i, int i2) {
        long pixCloseGray3 = JniLeptonicaJNI.pixCloseGray3(Pix.getCPtr(pix), pix, i, i2);
        if (pixCloseGray3 == 0) {
            return null;
        }
        return new Pix(pixCloseGray3, false);
    }

    public static void dilateGrayLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2) {
        JniLeptonicaJNI.dilateGrayLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2));
    }

    public static void erodeGrayLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2) {
        JniLeptonicaJNI.erodeGrayLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2));
    }

    public static Pix pixDitherToBinary(Pix pix) {
        long pixDitherToBinary = JniLeptonicaJNI.pixDitherToBinary(Pix.getCPtr(pix), pix);
        if (pixDitherToBinary == 0) {
            return null;
        }
        return new Pix(pixDitherToBinary, false);
    }

    public static Pix pixDitherToBinarySpec(Pix pix, int i, int i2) {
        long pixDitherToBinarySpec = JniLeptonicaJNI.pixDitherToBinarySpec(Pix.getCPtr(pix), pix, i, i2);
        if (pixDitherToBinarySpec == 0) {
            return null;
        }
        return new Pix(pixDitherToBinarySpec, false);
    }

    public static Pix pixThresholdToBinary(Pix pix, int i) {
        long pixThresholdToBinary = JniLeptonicaJNI.pixThresholdToBinary(Pix.getCPtr(pix), pix, i);
        if (pixThresholdToBinary == 0) {
            return null;
        }
        return new Pix(pixThresholdToBinary, false);
    }

    public static Pix pixVarThresholdToBinary(Pix pix, Pix pix2) {
        long pixVarThresholdToBinary = JniLeptonicaJNI.pixVarThresholdToBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixVarThresholdToBinary == 0) {
            return null;
        }
        return new Pix(pixVarThresholdToBinary, false);
    }

    public static Pix pixAdaptThresholdToBinary(Pix pix, Pix pix2, float f) {
        long pixAdaptThresholdToBinary = JniLeptonicaJNI.pixAdaptThresholdToBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixAdaptThresholdToBinary == 0) {
            return null;
        }
        return new Pix(pixAdaptThresholdToBinary, false);
    }

    public static Pix pixAdaptThresholdToBinaryGen(Pix pix, Pix pix2, float f, int i, int i2, int i3) {
        long pixAdaptThresholdToBinaryGen = JniLeptonicaJNI.pixAdaptThresholdToBinaryGen(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i, i2, i3);
        if (pixAdaptThresholdToBinaryGen == 0) {
            return null;
        }
        return new Pix(pixAdaptThresholdToBinaryGen, false);
    }

    public static Pix pixDitherToBinaryLUT(Pix pix, int i, int i2) {
        long pixDitherToBinaryLUT = JniLeptonicaJNI.pixDitherToBinaryLUT(Pix.getCPtr(pix), pix, i, i2);
        if (pixDitherToBinaryLUT == 0) {
            return null;
        }
        return new Pix(pixDitherToBinaryLUT, false);
    }

    public static Pix pixGenerateMaskByValue(Pix pix, int i, int i2) {
        long pixGenerateMaskByValue = JniLeptonicaJNI.pixGenerateMaskByValue(Pix.getCPtr(pix), pix, i, i2);
        if (pixGenerateMaskByValue == 0) {
            return null;
        }
        return new Pix(pixGenerateMaskByValue, false);
    }

    public static Pix pixGenerateMaskByBand(Pix pix, int i, int i2, int i3, int i4) {
        long pixGenerateMaskByBand = JniLeptonicaJNI.pixGenerateMaskByBand(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixGenerateMaskByBand == 0) {
            return null;
        }
        return new Pix(pixGenerateMaskByBand, false);
    }

    public static Pix pixDitherTo2bpp(Pix pix, int i) {
        long pixDitherTo2bpp = JniLeptonicaJNI.pixDitherTo2bpp(Pix.getCPtr(pix), pix, i);
        if (pixDitherTo2bpp == 0) {
            return null;
        }
        return new Pix(pixDitherTo2bpp, false);
    }

    public static Pix pixDitherTo2bppSpec(Pix pix, int i, int i2, int i3) {
        long pixDitherTo2bppSpec = JniLeptonicaJNI.pixDitherTo2bppSpec(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixDitherTo2bppSpec == 0) {
            return null;
        }
        return new Pix(pixDitherTo2bppSpec, false);
    }

    public static Pix pixThresholdTo2bpp(Pix pix, int i, int i2) {
        long pixThresholdTo2bpp = JniLeptonicaJNI.pixThresholdTo2bpp(Pix.getCPtr(pix), pix, i, i2);
        if (pixThresholdTo2bpp == 0) {
            return null;
        }
        return new Pix(pixThresholdTo2bpp, false);
    }

    public static Pix pixThresholdTo4bpp(Pix pix, int i, int i2) {
        long pixThresholdTo4bpp = JniLeptonicaJNI.pixThresholdTo4bpp(Pix.getCPtr(pix), pix, i, i2);
        if (pixThresholdTo4bpp == 0) {
            return null;
        }
        return new Pix(pixThresholdTo4bpp, false);
    }

    public static Pix pixThresholdOn8bpp(Pix pix, int i, int i2) {
        long pixThresholdOn8bpp = JniLeptonicaJNI.pixThresholdOn8bpp(Pix.getCPtr(pix), pix, i, i2);
        if (pixThresholdOn8bpp == 0) {
            return null;
        }
        return new Pix(pixThresholdOn8bpp, false);
    }

    public static Pix pixThresholdGrayArb(Pix pix, String str, int i, int i2, int i3, int i4) {
        long pixThresholdGrayArb = JniLeptonicaJNI.pixThresholdGrayArb(Pix.getCPtr(pix), pix, str, i, i2, i3, i4);
        if (pixThresholdGrayArb == 0) {
            return null;
        }
        return new Pix(pixThresholdGrayArb, false);
    }

    public static SWIGTYPE_p_int makeGrayQuantIndexTable(int i) {
        long makeGrayQuantIndexTable = JniLeptonicaJNI.makeGrayQuantIndexTable(i);
        if (makeGrayQuantIndexTable == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(makeGrayQuantIndexTable, false);
    }

    public static SWIGTYPE_p_int makeGrayQuantTargetTable(int i, int i2) {
        long makeGrayQuantTargetTable = JniLeptonicaJNI.makeGrayQuantTargetTable(i, i2);
        if (makeGrayQuantTargetTable == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(makeGrayQuantTargetTable, false);
    }

    public static int makeGrayQuantTableArb(Numa numa, int i, SWIGTYPE_p_p_int sWIGTYPE_p_p_int, SWIGTYPE_p_p_PixColormap sWIGTYPE_p_p_PixColormap) {
        return JniLeptonicaJNI.makeGrayQuantTableArb(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int), SWIGTYPE_p_p_PixColormap.getCPtr(sWIGTYPE_p_p_PixColormap));
    }

    public static int makeGrayQuantColormapArb(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, int i, SWIGTYPE_p_p_PixColormap sWIGTYPE_p_p_PixColormap) {
        return JniLeptonicaJNI.makeGrayQuantColormapArb(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, SWIGTYPE_p_p_PixColormap.getCPtr(sWIGTYPE_p_p_PixColormap));
    }

    public static Pix pixGenerateMaskByBand32(Pix pix, long j, int i, int i2, float f, float f2) {
        long pixGenerateMaskByBand32 = JniLeptonicaJNI.pixGenerateMaskByBand32(Pix.getCPtr(pix), pix, j, i, i2, f, f2);
        if (pixGenerateMaskByBand32 == 0) {
            return null;
        }
        return new Pix(pixGenerateMaskByBand32, false);
    }

    public static Pix pixGenerateMaskByDiscr32(Pix pix, long j, long j2, int i) {
        long pixGenerateMaskByDiscr32 = JniLeptonicaJNI.pixGenerateMaskByDiscr32(Pix.getCPtr(pix), pix, j, j2, i);
        if (pixGenerateMaskByDiscr32 == 0) {
            return null;
        }
        return new Pix(pixGenerateMaskByDiscr32, false);
    }

    public static Pix pixGrayQuantFromHisto(Pix pix, Pix pix2, Pix pix3, float f, int i) {
        long pixGrayQuantFromHisto = JniLeptonicaJNI.pixGrayQuantFromHisto(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, f, i);
        if (pixGrayQuantFromHisto == 0) {
            return null;
        }
        return new Pix(pixGrayQuantFromHisto, false);
    }

    public static Pix pixGrayQuantFromCmap(Pix pix, PixColormap pixColormap, int i) {
        long pixGrayQuantFromCmap = JniLeptonicaJNI.pixGrayQuantFromCmap(Pix.getCPtr(pix), pix, PixColormap.getCPtr(pixColormap), pixColormap, i);
        if (pixGrayQuantFromCmap == 0) {
            return null;
        }
        return new Pix(pixGrayQuantFromCmap, false);
    }

    public static void ditherToBinaryLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int4, int i5, int i6) {
        JniLeptonicaJNI.ditherToBinaryLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int4), i5, i6);
    }

    public static void ditherToBinaryLineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, int i2, int i3, int i4) {
        JniLeptonicaJNI.ditherToBinaryLineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), i2, i3, i4);
    }

    public static void thresholdToBinaryLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.thresholdToBinaryLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void thresholdToBinaryLineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3) {
        JniLeptonicaJNI.thresholdToBinaryLineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3);
    }

    public static void ditherToBinaryLUTLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        JniLeptonicaJNI.ditherToBinaryLUTLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static void ditherToBinaryLineLUTLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, int i2) {
        JniLeptonicaJNI.ditherToBinaryLineLUTLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), i2);
    }

    public static int make8To1DitherTables(SWIGTYPE_p_p_int sWIGTYPE_p_p_int, SWIGTYPE_p_p_int sWIGTYPE_p_p_int2, SWIGTYPE_p_p_int sWIGTYPE_p_p_int3, int i, int i2) {
        return JniLeptonicaJNI.make8To1DitherTables(SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int2), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int3), i, i2);
    }

    public static void ditherTo2bppLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        JniLeptonicaJNI.ditherTo2bppLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static void ditherTo2bppLineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, int i2) {
        JniLeptonicaJNI.ditherTo2bppLineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), i2);
    }

    public static int make8To2DitherTables(SWIGTYPE_p_p_int sWIGTYPE_p_p_int, SWIGTYPE_p_p_int sWIGTYPE_p_p_int2, SWIGTYPE_p_p_int sWIGTYPE_p_p_int3, int i, int i2) {
        return JniLeptonicaJNI.make8To2DitherTables(SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int2), SWIGTYPE_p_p_int.getCPtr(sWIGTYPE_p_p_int3), i, i2);
    }

    public static void thresholdTo2bppLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.thresholdTo2bppLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static void thresholdTo4bppLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.thresholdTo4bppLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static L_Heap lheapCreate(int i, int i2) {
        long lheapCreate = JniLeptonicaJNI.lheapCreate(i, i2);
        if (lheapCreate == 0) {
            return null;
        }
        return new L_Heap(lheapCreate, false);
    }

    public static void lheapDestroy(SWIGTYPE_p_p_L_Heap sWIGTYPE_p_p_L_Heap, int i) {
        JniLeptonicaJNI.lheapDestroy(SWIGTYPE_p_p_L_Heap.getCPtr(sWIGTYPE_p_p_L_Heap), i);
    }

    public static int lheapAdd(L_Heap l_Heap, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.lheapAdd(L_Heap.getCPtr(l_Heap), l_Heap, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static SWIGTYPE_p_void lheapRemove(L_Heap l_Heap) {
        long lheapRemove = JniLeptonicaJNI.lheapRemove(L_Heap.getCPtr(l_Heap), l_Heap);
        if (lheapRemove == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(lheapRemove, false);
    }

    public static int lheapGetCount(L_Heap l_Heap) {
        return JniLeptonicaJNI.lheapGetCount(L_Heap.getCPtr(l_Heap), l_Heap);
    }

    public static int lheapSwapUp(L_Heap l_Heap, int i) {
        return JniLeptonicaJNI.lheapSwapUp(L_Heap.getCPtr(l_Heap), l_Heap, i);
    }

    public static int lheapSwapDown(L_Heap l_Heap) {
        return JniLeptonicaJNI.lheapSwapDown(L_Heap.getCPtr(l_Heap), l_Heap);
    }

    public static int lheapSort(L_Heap l_Heap) {
        return JniLeptonicaJNI.lheapSort(L_Heap.getCPtr(l_Heap), l_Heap);
    }

    public static int lheapSortStrictOrder(L_Heap l_Heap) {
        return JniLeptonicaJNI.lheapSortStrictOrder(L_Heap.getCPtr(l_Heap), l_Heap);
    }

    public static int lheapPrint(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Heap l_Heap) {
        return JniLeptonicaJNI.lheapPrint(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Heap.getCPtr(l_Heap), l_Heap);
    }

    public static JbClasser jbRankHausInit(int i, int i2, int i3, int i4, float f) {
        long jbRankHausInit = JniLeptonicaJNI.jbRankHausInit(i, i2, i3, i4, f);
        if (jbRankHausInit == 0) {
            return null;
        }
        return new JbClasser(jbRankHausInit, false);
    }

    public static JbClasser jbCorrelationInit(int i, int i2, int i3, float f, float f2) {
        long jbCorrelationInit = JniLeptonicaJNI.jbCorrelationInit(i, i2, i3, f, f2);
        if (jbCorrelationInit == 0) {
            return null;
        }
        return new JbClasser(jbCorrelationInit, false);
    }

    public static JbClasser jbCorrelationInitWithoutComponents(int i, int i2, int i3, float f, float f2) {
        long jbCorrelationInitWithoutComponents = JniLeptonicaJNI.jbCorrelationInitWithoutComponents(i, i2, i3, f, f2);
        if (jbCorrelationInitWithoutComponents == 0) {
            return null;
        }
        return new JbClasser(jbCorrelationInitWithoutComponents, false);
    }

    public static int jbAddPages(JbClasser jbClasser, Sarray sarray) {
        return JniLeptonicaJNI.jbAddPages(JbClasser.getCPtr(jbClasser), jbClasser, Sarray.getCPtr(sarray), sarray);
    }

    public static int jbAddPage(JbClasser jbClasser, Pix pix) {
        return JniLeptonicaJNI.jbAddPage(JbClasser.getCPtr(jbClasser), jbClasser, Pix.getCPtr(pix), pix);
    }

    public static int jbAddPageComponents(JbClasser jbClasser, Pix pix, Boxa boxa, Pixa pixa) {
        return JniLeptonicaJNI.jbAddPageComponents(JbClasser.getCPtr(jbClasser), jbClasser, Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, Pixa.getCPtr(pixa), pixa);
    }

    public static int jbClassifyRankHaus(JbClasser jbClasser, Boxa boxa, Pixa pixa) {
        return JniLeptonicaJNI.jbClassifyRankHaus(JbClasser.getCPtr(jbClasser), jbClasser, Boxa.getCPtr(boxa), boxa, Pixa.getCPtr(pixa), pixa);
    }

    public static int pixHaustest(Pix pix, Pix pix2, Pix pix3, Pix pix4, float f, float f2, int i, int i2) {
        return JniLeptonicaJNI.pixHaustest(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, Pix.getCPtr(pix4), pix4, f, f2, i, i2);
    }

    public static int pixRankHaustest(Pix pix, Pix pix2, Pix pix3, Pix pix4, float f, float f2, int i, int i2, int i3, int i4, float f3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixRankHaustest(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, Pix.getCPtr(pix4), pix4, f, f2, i, i2, i3, i4, f3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int jbClassifyCorrelation(JbClasser jbClasser, Boxa boxa, Pixa pixa) {
        return JniLeptonicaJNI.jbClassifyCorrelation(JbClasser.getCPtr(jbClasser), jbClasser, Boxa.getCPtr(boxa), boxa, Pixa.getCPtr(pixa), pixa);
    }

    public static int jbGetComponents(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        return JniLeptonicaJNI.jbGetComponents(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static int pixWordMaskByDilation(Pix pix, int i, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixWordMaskByDilation(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixWordBoxesByDilation(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixWordBoxesByDilation(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pixa jbAccumulateComposites(Pixaa pixaa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta) {
        long jbAccumulateComposites = JniLeptonicaJNI.jbAccumulateComposites(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta));
        if (jbAccumulateComposites == 0) {
            return null;
        }
        return new Pixa(jbAccumulateComposites, false);
    }

    public static Pixa jbTemplatesFromComposites(Pixa pixa, Numa numa) {
        long jbTemplatesFromComposites = JniLeptonicaJNI.jbTemplatesFromComposites(Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa);
        if (jbTemplatesFromComposites == 0) {
            return null;
        }
        return new Pixa(jbTemplatesFromComposites, false);
    }

    public static JbClasser jbClasserCreate(int i, int i2) {
        long jbClasserCreate = JniLeptonicaJNI.jbClasserCreate(i, i2);
        if (jbClasserCreate == 0) {
            return null;
        }
        return new JbClasser(jbClasserCreate, false);
    }

    public static void jbClasserDestroy(SWIGTYPE_p_p_JbClasser sWIGTYPE_p_p_JbClasser) {
        JniLeptonicaJNI.jbClasserDestroy(SWIGTYPE_p_p_JbClasser.getCPtr(sWIGTYPE_p_p_JbClasser));
    }

    public static JbData jbDataSave(JbClasser jbClasser) {
        long jbDataSave = JniLeptonicaJNI.jbDataSave(JbClasser.getCPtr(jbClasser), jbClasser);
        if (jbDataSave == 0) {
            return null;
        }
        return new JbData(jbDataSave, false);
    }

    public static void jbDataDestroy(SWIGTYPE_p_p_JbData sWIGTYPE_p_p_JbData) {
        JniLeptonicaJNI.jbDataDestroy(SWIGTYPE_p_p_JbData.getCPtr(sWIGTYPE_p_p_JbData));
    }

    public static int jbDataWrite(String str, JbData jbData) {
        return JniLeptonicaJNI.jbDataWrite(str, JbData.getCPtr(jbData), jbData);
    }

    public static JbData jbDataRead(String str) {
        long jbDataRead = JniLeptonicaJNI.jbDataRead(str);
        if (jbDataRead == 0) {
            return null;
        }
        return new JbData(jbDataRead, false);
    }

    public static Pixa jbDataRender(JbData jbData, int i) {
        long jbDataRender = JniLeptonicaJNI.jbDataRender(JbData.getCPtr(jbData), jbData, i);
        if (jbDataRender == 0) {
            return null;
        }
        return new Pixa(jbDataRender, false);
    }

    public static int jbGetULCorners(JbClasser jbClasser, Pix pix, Boxa boxa) {
        return JniLeptonicaJNI.jbGetULCorners(JbClasser.getCPtr(jbClasser), jbClasser, Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa);
    }

    public static int jbGetLLCorners(JbClasser jbClasser) {
        return JniLeptonicaJNI.jbGetLLCorners(JbClasser.getCPtr(jbClasser), jbClasser);
    }

    public static int readHeaderJp2k(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.readHeaderJp2k(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int freadHeaderJp2k(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.freadHeaderJp2k(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int readHeaderMemJp2k(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.readHeaderMemJp2k(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int fgetJp2kResolution(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fgetJp2kResolution(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pix pixReadJp2k(String str, long j, Box box, int i) {
        long pixReadJp2k = JniLeptonicaJNI.pixReadJp2k(str, j, Box.getCPtr(box), box, i);
        if (pixReadJp2k == 0) {
            return null;
        }
        return new Pix(pixReadJp2k, false);
    }

    public static Pix pixReadStreamJp2k(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, long j, Box box, int i) {
        long pixReadStreamJp2k = JniLeptonicaJNI.pixReadStreamJp2k(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), j, Box.getCPtr(box), box, i);
        if (pixReadStreamJp2k == 0) {
            return null;
        }
        return new Pix(pixReadStreamJp2k, false);
    }

    public static int pixWriteJp2k(String str, Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixWriteJp2k(str, Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static int pixWriteStreamJp2k(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixWriteStreamJp2k(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static Pix pixReadMemJp2k(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, long j2, Box box, int i) {
        long pixReadMemJp2k = JniLeptonicaJNI.pixReadMemJp2k(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, j2, Box.getCPtr(box), box, i);
        if (pixReadMemJp2k == 0) {
            return null;
        }
        return new Pix(pixReadMemJp2k, false);
    }

    public static int pixWriteMemJp2k(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixWriteMemJp2k(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static Pix pixReadJpeg(String str, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, int i3) {
        long pixReadJpeg = JniLeptonicaJNI.pixReadJpeg(str, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i3);
        if (pixReadJpeg == 0) {
            return null;
        }
        return new Pix(pixReadJpeg, false);
    }

    public static Pix pixReadStreamJpeg(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, int i3) {
        long pixReadStreamJpeg = JniLeptonicaJNI.pixReadStreamJpeg(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i3);
        if (pixReadStreamJpeg == 0) {
            return null;
        }
        return new Pix(pixReadStreamJpeg, false);
    }

    public static int readHeaderJpeg(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.readHeaderJpeg(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int freadHeaderJpeg(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.freadHeaderJpeg(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int fgetJpegResolution(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fgetJpegResolution(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int fgetJpegComment(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char) {
        return JniLeptonicaJNI.fgetJpegComment(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char));
    }

    public static int pixWriteJpeg(String str, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteJpeg(str, Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixWriteStreamJpeg(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteStreamJpeg(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i, i2);
    }

    public static Pix pixReadMemJpeg(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, int i3) {
        long pixReadMemJpeg = JniLeptonicaJNI.pixReadMemJpeg(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i3);
        if (pixReadMemJpeg == 0) {
            return null;
        }
        return new Pix(pixReadMemJpeg, false);
    }

    public static int readHeaderMemJpeg(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.readHeaderMemJpeg(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int pixWriteMemJpeg(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteMemJpeg(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixSetChromaSampling(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetChromaSampling(Pix.getCPtr(pix), pix, i);
    }

    public static L_Kernel kernelCreate(int i, int i2) {
        long kernelCreate = JniLeptonicaJNI.kernelCreate(i, i2);
        if (kernelCreate == 0) {
            return null;
        }
        return new L_Kernel(kernelCreate, false);
    }

    public static void kernelDestroy(SWIGTYPE_p_p_L_Kernel sWIGTYPE_p_p_L_Kernel) {
        JniLeptonicaJNI.kernelDestroy(SWIGTYPE_p_p_L_Kernel.getCPtr(sWIGTYPE_p_p_L_Kernel));
    }

    public static L_Kernel kernelCopy(L_Kernel l_Kernel) {
        long kernelCopy = JniLeptonicaJNI.kernelCopy(L_Kernel.getCPtr(l_Kernel), l_Kernel);
        if (kernelCopy == 0) {
            return null;
        }
        return new L_Kernel(kernelCopy, false);
    }

    public static int kernelGetElement(L_Kernel l_Kernel, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.kernelGetElement(L_Kernel.getCPtr(l_Kernel), l_Kernel, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int kernelSetElement(L_Kernel l_Kernel, int i, int i2, float f) {
        return JniLeptonicaJNI.kernelSetElement(L_Kernel.getCPtr(l_Kernel), l_Kernel, i, i2, f);
    }

    public static int kernelGetParameters(L_Kernel l_Kernel, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.kernelGetParameters(L_Kernel.getCPtr(l_Kernel), l_Kernel, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int kernelSetOrigin(L_Kernel l_Kernel, int i, int i2) {
        return JniLeptonicaJNI.kernelSetOrigin(L_Kernel.getCPtr(l_Kernel), l_Kernel, i, i2);
    }

    public static int kernelGetSum(L_Kernel l_Kernel, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.kernelGetSum(L_Kernel.getCPtr(l_Kernel), l_Kernel, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int kernelGetMinMax(L_Kernel l_Kernel, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.kernelGetMinMax(L_Kernel.getCPtr(l_Kernel), l_Kernel, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static L_Kernel kernelNormalize(L_Kernel l_Kernel, float f) {
        long kernelNormalize = JniLeptonicaJNI.kernelNormalize(L_Kernel.getCPtr(l_Kernel), l_Kernel, f);
        if (kernelNormalize == 0) {
            return null;
        }
        return new L_Kernel(kernelNormalize, false);
    }

    public static L_Kernel kernelInvert(L_Kernel l_Kernel) {
        long kernelInvert = JniLeptonicaJNI.kernelInvert(L_Kernel.getCPtr(l_Kernel), l_Kernel);
        if (kernelInvert == 0) {
            return null;
        }
        return new L_Kernel(kernelInvert, false);
    }

    public static SWIGTYPE_p_p_float create2dFloatArray(int i, int i2) {
        long create2dFloatArray = JniLeptonicaJNI.create2dFloatArray(i, i2);
        if (create2dFloatArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_float(create2dFloatArray, false);
    }

    public static L_Kernel kernelRead(String str) {
        long kernelRead = JniLeptonicaJNI.kernelRead(str);
        if (kernelRead == 0) {
            return null;
        }
        return new L_Kernel(kernelRead, false);
    }

    public static L_Kernel kernelReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long kernelReadStream = JniLeptonicaJNI.kernelReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (kernelReadStream == 0) {
            return null;
        }
        return new L_Kernel(kernelReadStream, false);
    }

    public static int kernelWrite(String str, L_Kernel l_Kernel) {
        return JniLeptonicaJNI.kernelWrite(str, L_Kernel.getCPtr(l_Kernel), l_Kernel);
    }

    public static int kernelWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Kernel l_Kernel) {
        return JniLeptonicaJNI.kernelWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Kernel.getCPtr(l_Kernel), l_Kernel);
    }

    public static L_Kernel kernelCreateFromString(int i, int i2, int i3, int i4, String str) {
        long kernelCreateFromString = JniLeptonicaJNI.kernelCreateFromString(i, i2, i3, i4, str);
        if (kernelCreateFromString == 0) {
            return null;
        }
        return new L_Kernel(kernelCreateFromString, false);
    }

    public static L_Kernel kernelCreateFromFile(String str) {
        long kernelCreateFromFile = JniLeptonicaJNI.kernelCreateFromFile(str);
        if (kernelCreateFromFile == 0) {
            return null;
        }
        return new L_Kernel(kernelCreateFromFile, false);
    }

    public static L_Kernel kernelCreateFromPix(Pix pix, int i, int i2) {
        long kernelCreateFromPix = JniLeptonicaJNI.kernelCreateFromPix(Pix.getCPtr(pix), pix, i, i2);
        if (kernelCreateFromPix == 0) {
            return null;
        }
        return new L_Kernel(kernelCreateFromPix, false);
    }

    public static Pix kernelDisplayInPix(L_Kernel l_Kernel, int i, int i2) {
        long kernelDisplayInPix = JniLeptonicaJNI.kernelDisplayInPix(L_Kernel.getCPtr(l_Kernel), l_Kernel, i, i2);
        if (kernelDisplayInPix == 0) {
            return null;
        }
        return new Pix(kernelDisplayInPix, false);
    }

    public static Numa parseStringForNumbers(String str, String str2) {
        long parseStringForNumbers = JniLeptonicaJNI.parseStringForNumbers(str, str2);
        if (parseStringForNumbers == 0) {
            return null;
        }
        return new Numa(parseStringForNumbers, false);
    }

    public static L_Kernel makeFlatKernel(int i, int i2, int i3, int i4) {
        long makeFlatKernel = JniLeptonicaJNI.makeFlatKernel(i, i2, i3, i4);
        if (makeFlatKernel == 0) {
            return null;
        }
        return new L_Kernel(makeFlatKernel, false);
    }

    public static L_Kernel makeGaussianKernel(int i, int i2, float f, float f2) {
        long makeGaussianKernel = JniLeptonicaJNI.makeGaussianKernel(i, i2, f, f2);
        if (makeGaussianKernel == 0) {
            return null;
        }
        return new L_Kernel(makeGaussianKernel, false);
    }

    public static int makeGaussianKernelSep(int i, int i2, float f, float f2, SWIGTYPE_p_p_L_Kernel sWIGTYPE_p_p_L_Kernel, SWIGTYPE_p_p_L_Kernel sWIGTYPE_p_p_L_Kernel2) {
        return JniLeptonicaJNI.makeGaussianKernelSep(i, i2, f, f2, SWIGTYPE_p_p_L_Kernel.getCPtr(sWIGTYPE_p_p_L_Kernel), SWIGTYPE_p_p_L_Kernel.getCPtr(sWIGTYPE_p_p_L_Kernel2));
    }

    public static L_Kernel makeDoGKernel(int i, int i2, float f, float f2) {
        long makeDoGKernel = JniLeptonicaJNI.makeDoGKernel(i, i2, f, f2);
        if (makeDoGKernel == 0) {
            return null;
        }
        return new L_Kernel(makeDoGKernel, false);
    }

    public static String getImagelibVersions() {
        return JniLeptonicaJNI.getImagelibVersions();
    }

    public static void listDestroy(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList) {
        JniLeptonicaJNI.listDestroy(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList));
    }

    public static int listAddToHead(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.listAddToHead(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static int listAddToTail(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList2, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.listAddToTail(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList2), SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static int listInsertBefore(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, DoubleLinkedList doubleLinkedList, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.listInsertBefore(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static int listInsertAfter(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, DoubleLinkedList doubleLinkedList, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.listInsertAfter(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static SWIGTYPE_p_void listRemoveElement(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, DoubleLinkedList doubleLinkedList) {
        long listRemoveElement = JniLeptonicaJNI.listRemoveElement(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList);
        if (listRemoveElement == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(listRemoveElement, false);
    }

    public static SWIGTYPE_p_void listRemoveFromHead(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList) {
        long listRemoveFromHead = JniLeptonicaJNI.listRemoveFromHead(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList));
        if (listRemoveFromHead == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(listRemoveFromHead, false);
    }

    public static SWIGTYPE_p_void listRemoveFromTail(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList2) {
        long listRemoveFromTail = JniLeptonicaJNI.listRemoveFromTail(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList2));
        if (listRemoveFromTail == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(listRemoveFromTail, false);
    }

    public static DoubleLinkedList listFindElement(DoubleLinkedList doubleLinkedList, SWIGTYPE_p_void sWIGTYPE_p_void) {
        long listFindElement = JniLeptonicaJNI.listFindElement(DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
        if (listFindElement == 0) {
            return null;
        }
        return new DoubleLinkedList(listFindElement, false);
    }

    public static DoubleLinkedList listFindTail(DoubleLinkedList doubleLinkedList) {
        long listFindTail = JniLeptonicaJNI.listFindTail(DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList);
        if (listFindTail == 0) {
            return null;
        }
        return new DoubleLinkedList(listFindTail, false);
    }

    public static int listGetCount(DoubleLinkedList doubleLinkedList) {
        return JniLeptonicaJNI.listGetCount(DoubleLinkedList.getCPtr(doubleLinkedList), doubleLinkedList);
    }

    public static int listReverse(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList) {
        return JniLeptonicaJNI.listReverse(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList));
    }

    public static int listJoin(SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList, SWIGTYPE_p_p_DoubleLinkedList sWIGTYPE_p_p_DoubleLinkedList2) {
        return JniLeptonicaJNI.listJoin(SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList), SWIGTYPE_p_p_DoubleLinkedList.getCPtr(sWIGTYPE_p_p_DoubleLinkedList2));
    }

    public static Pix generateBinaryMaze(int i, int i2, int i3, int i4, float f, float f2) {
        long generateBinaryMaze = JniLeptonicaJNI.generateBinaryMaze(i, i2, i3, i4, f, f2);
        if (generateBinaryMaze == 0) {
            return null;
        }
        return new Pix(generateBinaryMaze, false);
    }

    public static Pta pixSearchBinaryMaze(Pix pix, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixSearchBinaryMaze = JniLeptonicaJNI.pixSearchBinaryMaze(Pix.getCPtr(pix), pix, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixSearchBinaryMaze == 0) {
            return null;
        }
        return new Pta(pixSearchBinaryMaze, false);
    }

    public static Pta pixSearchGrayMaze(Pix pix, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixSearchGrayMaze = JniLeptonicaJNI.pixSearchGrayMaze(Pix.getCPtr(pix), pix, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixSearchGrayMaze == 0) {
            return null;
        }
        return new Pta(pixSearchGrayMaze, false);
    }

    public static int pixFindLargestRectangle(Pix pix, int i, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box, String str) {
        return JniLeptonicaJNI.pixFindLargestRectangle(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box), str);
    }

    public static Pix pixDilate(Pix pix, Pix pix2, Sel sel) {
        long pixDilate = JniLeptonicaJNI.pixDilate(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixDilate == 0) {
            return null;
        }
        return new Pix(pixDilate, false);
    }

    public static Pix pixErode(Pix pix, Pix pix2, Sel sel) {
        long pixErode = JniLeptonicaJNI.pixErode(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixErode == 0) {
            return null;
        }
        return new Pix(pixErode, false);
    }

    public static Pix pixHMT(Pix pix, Pix pix2, Sel sel) {
        long pixHMT = JniLeptonicaJNI.pixHMT(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixHMT == 0) {
            return null;
        }
        return new Pix(pixHMT, false);
    }

    public static Pix pixOpen(Pix pix, Pix pix2, Sel sel) {
        long pixOpen = JniLeptonicaJNI.pixOpen(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixOpen == 0) {
            return null;
        }
        return new Pix(pixOpen, false);
    }

    public static Pix pixClose(Pix pix, Pix pix2, Sel sel) {
        long pixClose = JniLeptonicaJNI.pixClose(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixClose == 0) {
            return null;
        }
        return new Pix(pixClose, false);
    }

    public static Pix pixCloseSafe(Pix pix, Pix pix2, Sel sel) {
        long pixCloseSafe = JniLeptonicaJNI.pixCloseSafe(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixCloseSafe == 0) {
            return null;
        }
        return new Pix(pixCloseSafe, false);
    }

    public static Pix pixOpenGeneralized(Pix pix, Pix pix2, Sel sel) {
        long pixOpenGeneralized = JniLeptonicaJNI.pixOpenGeneralized(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixOpenGeneralized == 0) {
            return null;
        }
        return new Pix(pixOpenGeneralized, false);
    }

    public static Pix pixCloseGeneralized(Pix pix, Pix pix2, Sel sel) {
        long pixCloseGeneralized = JniLeptonicaJNI.pixCloseGeneralized(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Sel.getCPtr(sel), sel);
        if (pixCloseGeneralized == 0) {
            return null;
        }
        return new Pix(pixCloseGeneralized, false);
    }

    public static Pix pixDilateBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixDilateBrick = JniLeptonicaJNI.pixDilateBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixDilateBrick == 0) {
            return null;
        }
        return new Pix(pixDilateBrick, false);
    }

    public static Pix pixErodeBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixErodeBrick = JniLeptonicaJNI.pixErodeBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixErodeBrick == 0) {
            return null;
        }
        return new Pix(pixErodeBrick, false);
    }

    public static Pix pixOpenBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixOpenBrick = JniLeptonicaJNI.pixOpenBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixOpenBrick == 0) {
            return null;
        }
        return new Pix(pixOpenBrick, false);
    }

    public static Pix pixCloseBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseBrick = JniLeptonicaJNI.pixCloseBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseBrick == 0) {
            return null;
        }
        return new Pix(pixCloseBrick, false);
    }

    public static Pix pixCloseSafeBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseSafeBrick = JniLeptonicaJNI.pixCloseSafeBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseSafeBrick == 0) {
            return null;
        }
        return new Pix(pixCloseSafeBrick, false);
    }

    public static int selectComposableSels(int i, int i2, SWIGTYPE_p_p_Sel sWIGTYPE_p_p_Sel, SWIGTYPE_p_p_Sel sWIGTYPE_p_p_Sel2) {
        return JniLeptonicaJNI.selectComposableSels(i, i2, SWIGTYPE_p_p_Sel.getCPtr(sWIGTYPE_p_p_Sel), SWIGTYPE_p_p_Sel.getCPtr(sWIGTYPE_p_p_Sel2));
    }

    public static int selectComposableSizes(int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.selectComposableSizes(i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pix pixDilateCompBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixDilateCompBrick = JniLeptonicaJNI.pixDilateCompBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixDilateCompBrick == 0) {
            return null;
        }
        return new Pix(pixDilateCompBrick, false);
    }

    public static Pix pixErodeCompBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixErodeCompBrick = JniLeptonicaJNI.pixErodeCompBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixErodeCompBrick == 0) {
            return null;
        }
        return new Pix(pixErodeCompBrick, false);
    }

    public static Pix pixOpenCompBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixOpenCompBrick = JniLeptonicaJNI.pixOpenCompBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixOpenCompBrick == 0) {
            return null;
        }
        return new Pix(pixOpenCompBrick, false);
    }

    public static Pix pixCloseCompBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseCompBrick = JniLeptonicaJNI.pixCloseCompBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseCompBrick == 0) {
            return null;
        }
        return new Pix(pixCloseCompBrick, false);
    }

    public static Pix pixCloseSafeCompBrick(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseSafeCompBrick = JniLeptonicaJNI.pixCloseSafeCompBrick(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseSafeCompBrick == 0) {
            return null;
        }
        return new Pix(pixCloseSafeCompBrick, false);
    }

    public static void resetMorphBoundaryCondition(int i) {
        JniLeptonicaJNI.resetMorphBoundaryCondition(i);
    }

    public static long getMorphBorderPixelColor(int i, int i2) {
        return JniLeptonicaJNI.getMorphBorderPixelColor(i, i2);
    }

    public static Pix pixExtractBoundary(Pix pix, int i) {
        long pixExtractBoundary = JniLeptonicaJNI.pixExtractBoundary(Pix.getCPtr(pix), pix, i);
        if (pixExtractBoundary == 0) {
            return null;
        }
        return new Pix(pixExtractBoundary, false);
    }

    public static Pix pixMorphSequenceMasked(Pix pix, Pix pix2, String str, int i) {
        long pixMorphSequenceMasked = JniLeptonicaJNI.pixMorphSequenceMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, str, i);
        if (pixMorphSequenceMasked == 0) {
            return null;
        }
        return new Pix(pixMorphSequenceMasked, false);
    }

    public static Pix pixMorphSequenceByComponent(Pix pix, String str, int i, int i2, int i3, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        long pixMorphSequenceByComponent = JniLeptonicaJNI.pixMorphSequenceByComponent(Pix.getCPtr(pix), pix, str, i, i2, i3, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
        if (pixMorphSequenceByComponent == 0) {
            return null;
        }
        return new Pix(pixMorphSequenceByComponent, false);
    }

    public static Pixa pixaMorphSequenceByComponent(Pixa pixa, String str, int i, int i2) {
        long pixaMorphSequenceByComponent = JniLeptonicaJNI.pixaMorphSequenceByComponent(Pixa.getCPtr(pixa), pixa, str, i, i2);
        if (pixaMorphSequenceByComponent == 0) {
            return null;
        }
        return new Pixa(pixaMorphSequenceByComponent, false);
    }

    public static Pix pixMorphSequenceByRegion(Pix pix, Pix pix2, String str, int i, int i2, int i3, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        long pixMorphSequenceByRegion = JniLeptonicaJNI.pixMorphSequenceByRegion(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, str, i, i2, i3, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
        if (pixMorphSequenceByRegion == 0) {
            return null;
        }
        return new Pix(pixMorphSequenceByRegion, false);
    }

    public static Pixa pixaMorphSequenceByRegion(Pix pix, Pixa pixa, String str, int i, int i2) {
        long pixaMorphSequenceByRegion = JniLeptonicaJNI.pixaMorphSequenceByRegion(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, str, i, i2);
        if (pixaMorphSequenceByRegion == 0) {
            return null;
        }
        return new Pixa(pixaMorphSequenceByRegion, false);
    }

    public static Pix pixUnionOfMorphOps(Pix pix, Sela sela, int i) {
        long pixUnionOfMorphOps = JniLeptonicaJNI.pixUnionOfMorphOps(Pix.getCPtr(pix), pix, Sela.getCPtr(sela), sela, i);
        if (pixUnionOfMorphOps == 0) {
            return null;
        }
        return new Pix(pixUnionOfMorphOps, false);
    }

    public static Pix pixIntersectionOfMorphOps(Pix pix, Sela sela, int i) {
        long pixIntersectionOfMorphOps = JniLeptonicaJNI.pixIntersectionOfMorphOps(Pix.getCPtr(pix), pix, Sela.getCPtr(sela), sela, i);
        if (pixIntersectionOfMorphOps == 0) {
            return null;
        }
        return new Pix(pixIntersectionOfMorphOps, false);
    }

    public static Pix pixSelectiveConnCompFill(Pix pix, int i, int i2, int i3) {
        long pixSelectiveConnCompFill = JniLeptonicaJNI.pixSelectiveConnCompFill(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixSelectiveConnCompFill == 0) {
            return null;
        }
        return new Pix(pixSelectiveConnCompFill, false);
    }

    public static int pixRemoveMatchedPattern(Pix pix, Pix pix2, Pix pix3, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixRemoveMatchedPattern(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, i3);
    }

    public static Pix pixDisplayMatchedPattern(Pix pix, Pix pix2, Pix pix3, int i, int i2, long j, float f, int i3) {
        long pixDisplayMatchedPattern = JniLeptonicaJNI.pixDisplayMatchedPattern(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, j, f, i3);
        if (pixDisplayMatchedPattern == 0) {
            return null;
        }
        return new Pix(pixDisplayMatchedPattern, false);
    }

    public static Pix pixSeedfillMorph(Pix pix, Pix pix2, int i, int i2) {
        long pixSeedfillMorph = JniLeptonicaJNI.pixSeedfillMorph(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixSeedfillMorph == 0) {
            return null;
        }
        return new Pix(pixSeedfillMorph, false);
    }

    public static Numa pixRunHistogramMorph(Pix pix, int i, int i2, int i3) {
        long pixRunHistogramMorph = JniLeptonicaJNI.pixRunHistogramMorph(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixRunHistogramMorph == 0) {
            return null;
        }
        return new Numa(pixRunHistogramMorph, false);
    }

    public static Pix pixTophat(Pix pix, int i, int i2, int i3) {
        long pixTophat = JniLeptonicaJNI.pixTophat(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixTophat == 0) {
            return null;
        }
        return new Pix(pixTophat, false);
    }

    public static Pix pixHDome(Pix pix, int i, int i2) {
        long pixHDome = JniLeptonicaJNI.pixHDome(Pix.getCPtr(pix), pix, i, i2);
        if (pixHDome == 0) {
            return null;
        }
        return new Pix(pixHDome, false);
    }

    public static Pix pixFastTophat(Pix pix, int i, int i2, int i3) {
        long pixFastTophat = JniLeptonicaJNI.pixFastTophat(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixFastTophat == 0) {
            return null;
        }
        return new Pix(pixFastTophat, false);
    }

    public static Pix pixMorphGradient(Pix pix, int i, int i2, int i3) {
        long pixMorphGradient = JniLeptonicaJNI.pixMorphGradient(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixMorphGradient == 0) {
            return null;
        }
        return new Pix(pixMorphGradient, false);
    }

    public static Pta pixaCentroids(Pixa pixa) {
        long pixaCentroids = JniLeptonicaJNI.pixaCentroids(Pixa.getCPtr(pixa), pixa);
        if (pixaCentroids == 0) {
            return null;
        }
        return new Pta(pixaCentroids, false);
    }

    public static int pixCentroid(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixCentroid(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static Pix pixDilateBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixDilateBrickDwa = JniLeptonicaJNI.pixDilateBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixDilateBrickDwa == 0) {
            return null;
        }
        return new Pix(pixDilateBrickDwa, false);
    }

    public static Pix pixErodeBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixErodeBrickDwa = JniLeptonicaJNI.pixErodeBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixErodeBrickDwa == 0) {
            return null;
        }
        return new Pix(pixErodeBrickDwa, false);
    }

    public static Pix pixOpenBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixOpenBrickDwa = JniLeptonicaJNI.pixOpenBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixOpenBrickDwa == 0) {
            return null;
        }
        return new Pix(pixOpenBrickDwa, false);
    }

    public static Pix pixCloseBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseBrickDwa = JniLeptonicaJNI.pixCloseBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseBrickDwa == 0) {
            return null;
        }
        return new Pix(pixCloseBrickDwa, false);
    }

    public static Pix pixDilateCompBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixDilateCompBrickDwa = JniLeptonicaJNI.pixDilateCompBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixDilateCompBrickDwa == 0) {
            return null;
        }
        return new Pix(pixDilateCompBrickDwa, false);
    }

    public static Pix pixErodeCompBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixErodeCompBrickDwa = JniLeptonicaJNI.pixErodeCompBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixErodeCompBrickDwa == 0) {
            return null;
        }
        return new Pix(pixErodeCompBrickDwa, false);
    }

    public static Pix pixOpenCompBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixOpenCompBrickDwa = JniLeptonicaJNI.pixOpenCompBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixOpenCompBrickDwa == 0) {
            return null;
        }
        return new Pix(pixOpenCompBrickDwa, false);
    }

    public static Pix pixCloseCompBrickDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseCompBrickDwa = JniLeptonicaJNI.pixCloseCompBrickDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseCompBrickDwa == 0) {
            return null;
        }
        return new Pix(pixCloseCompBrickDwa, false);
    }

    public static Pix pixDilateCompBrickExtendDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixDilateCompBrickExtendDwa = JniLeptonicaJNI.pixDilateCompBrickExtendDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixDilateCompBrickExtendDwa == 0) {
            return null;
        }
        return new Pix(pixDilateCompBrickExtendDwa, false);
    }

    public static Pix pixErodeCompBrickExtendDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixErodeCompBrickExtendDwa = JniLeptonicaJNI.pixErodeCompBrickExtendDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixErodeCompBrickExtendDwa == 0) {
            return null;
        }
        return new Pix(pixErodeCompBrickExtendDwa, false);
    }

    public static Pix pixOpenCompBrickExtendDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixOpenCompBrickExtendDwa = JniLeptonicaJNI.pixOpenCompBrickExtendDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixOpenCompBrickExtendDwa == 0) {
            return null;
        }
        return new Pix(pixOpenCompBrickExtendDwa, false);
    }

    public static Pix pixCloseCompBrickExtendDwa(Pix pix, Pix pix2, int i, int i2) {
        long pixCloseCompBrickExtendDwa = JniLeptonicaJNI.pixCloseCompBrickExtendDwa(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixCloseCompBrickExtendDwa == 0) {
            return null;
        }
        return new Pix(pixCloseCompBrickExtendDwa, false);
    }

    public static int getExtendedCompositeParameters(int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.getExtendedCompositeParameters(i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static Pix pixMorphSequence(Pix pix, String str, int i) {
        long pixMorphSequence = JniLeptonicaJNI.pixMorphSequence(Pix.getCPtr(pix), pix, str, i);
        if (pixMorphSequence == 0) {
            return null;
        }
        return new Pix(pixMorphSequence, false);
    }

    public static Pix pixMorphCompSequence(Pix pix, String str, int i) {
        long pixMorphCompSequence = JniLeptonicaJNI.pixMorphCompSequence(Pix.getCPtr(pix), pix, str, i);
        if (pixMorphCompSequence == 0) {
            return null;
        }
        return new Pix(pixMorphCompSequence, false);
    }

    public static Pix pixMorphSequenceDwa(Pix pix, String str, int i) {
        long pixMorphSequenceDwa = JniLeptonicaJNI.pixMorphSequenceDwa(Pix.getCPtr(pix), pix, str, i);
        if (pixMorphSequenceDwa == 0) {
            return null;
        }
        return new Pix(pixMorphSequenceDwa, false);
    }

    public static Pix pixMorphCompSequenceDwa(Pix pix, String str, int i) {
        long pixMorphCompSequenceDwa = JniLeptonicaJNI.pixMorphCompSequenceDwa(Pix.getCPtr(pix), pix, str, i);
        if (pixMorphCompSequenceDwa == 0) {
            return null;
        }
        return new Pix(pixMorphCompSequenceDwa, false);
    }

    public static int morphSequenceVerify(Sarray sarray) {
        return JniLeptonicaJNI.morphSequenceVerify(Sarray.getCPtr(sarray), sarray);
    }

    public static Pix pixGrayMorphSequence(Pix pix, String str, int i, int i2) {
        long pixGrayMorphSequence = JniLeptonicaJNI.pixGrayMorphSequence(Pix.getCPtr(pix), pix, str, i, i2);
        if (pixGrayMorphSequence == 0) {
            return null;
        }
        return new Pix(pixGrayMorphSequence, false);
    }

    public static Pix pixColorMorphSequence(Pix pix, String str, int i, int i2) {
        long pixColorMorphSequence = JniLeptonicaJNI.pixColorMorphSequence(Pix.getCPtr(pix), pix, str, i, i2);
        if (pixColorMorphSequence == 0) {
            return null;
        }
        return new Pix(pixColorMorphSequence, false);
    }

    public static Numa numaCreate(int i) {
        long numaCreate = JniLeptonicaJNI.numaCreate(i);
        if (numaCreate == 0) {
            return null;
        }
        return new Numa(numaCreate, false);
    }

    public static Numa numaCreateFromIArray(SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long numaCreateFromIArray = JniLeptonicaJNI.numaCreateFromIArray(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
        if (numaCreateFromIArray == 0) {
            return null;
        }
        return new Numa(numaCreateFromIArray, false);
    }

    public static Numa numaCreateFromFArray(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2) {
        long numaCreateFromFArray = JniLeptonicaJNI.numaCreateFromFArray(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2);
        if (numaCreateFromFArray == 0) {
            return null;
        }
        return new Numa(numaCreateFromFArray, false);
    }

    public static void numaDestroy(SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        JniLeptonicaJNI.numaDestroy(SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Numa numaCopy(Numa numa) {
        long numaCopy = JniLeptonicaJNI.numaCopy(Numa.getCPtr(numa), numa);
        if (numaCopy == 0) {
            return null;
        }
        return new Numa(numaCopy, false);
    }

    public static Numa numaClone(Numa numa) {
        long numaClone = JniLeptonicaJNI.numaClone(Numa.getCPtr(numa), numa);
        if (numaClone == 0) {
            return null;
        }
        return new Numa(numaClone, false);
    }

    public static int numaEmpty(Numa numa) {
        return JniLeptonicaJNI.numaEmpty(Numa.getCPtr(numa), numa);
    }

    public static int numaAddNumber(Numa numa, float f) {
        return JniLeptonicaJNI.numaAddNumber(Numa.getCPtr(numa), numa, f);
    }

    public static int numaInsertNumber(Numa numa, int i, float f) {
        return JniLeptonicaJNI.numaInsertNumber(Numa.getCPtr(numa), numa, i, f);
    }

    public static int numaRemoveNumber(Numa numa, int i) {
        return JniLeptonicaJNI.numaRemoveNumber(Numa.getCPtr(numa), numa, i);
    }

    public static int numaReplaceNumber(Numa numa, int i, float f) {
        return JniLeptonicaJNI.numaReplaceNumber(Numa.getCPtr(numa), numa, i, f);
    }

    public static int numaGetCount(Numa numa) {
        return JniLeptonicaJNI.numaGetCount(Numa.getCPtr(numa), numa);
    }

    public static int numaSetCount(Numa numa, int i) {
        return JniLeptonicaJNI.numaSetCount(Numa.getCPtr(numa), numa, i);
    }

    public static int numaGetFValue(Numa numa, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaGetFValue(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaGetIValue(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetIValue(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaSetValue(Numa numa, int i, float f) {
        return JniLeptonicaJNI.numaSetValue(Numa.getCPtr(numa), numa, i, f);
    }

    public static int numaShiftValue(Numa numa, int i, float f) {
        return JniLeptonicaJNI.numaShiftValue(Numa.getCPtr(numa), numa, i, f);
    }

    public static SWIGTYPE_p_int numaGetIArray(Numa numa) {
        long numaGetIArray = JniLeptonicaJNI.numaGetIArray(Numa.getCPtr(numa), numa);
        if (numaGetIArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(numaGetIArray, false);
    }

    public static SWIGTYPE_p_float numaGetFArray(Numa numa, int i) {
        long numaGetFArray = JniLeptonicaJNI.numaGetFArray(Numa.getCPtr(numa), numa, i);
        if (numaGetFArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(numaGetFArray, false);
    }

    public static int numaGetRefcount(Numa numa) {
        return JniLeptonicaJNI.numaGetRefcount(Numa.getCPtr(numa), numa);
    }

    public static int numaChangeRefcount(Numa numa, int i) {
        return JniLeptonicaJNI.numaChangeRefcount(Numa.getCPtr(numa), numa, i);
    }

    public static int numaGetParameters(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.numaGetParameters(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int numaSetParameters(Numa numa, float f, float f2) {
        return JniLeptonicaJNI.numaSetParameters(Numa.getCPtr(numa), numa, f, f2);
    }

    public static int numaCopyParameters(Numa numa, Numa numa2) {
        return JniLeptonicaJNI.numaCopyParameters(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
    }

    public static Sarray numaConvertToSarray(Numa numa, int i, int i2, int i3, int i4) {
        long numaConvertToSarray = JniLeptonicaJNI.numaConvertToSarray(Numa.getCPtr(numa), numa, i, i2, i3, i4);
        if (numaConvertToSarray == 0) {
            return null;
        }
        return new Sarray(numaConvertToSarray, false);
    }

    public static Numa numaRead(String str) {
        long numaRead = JniLeptonicaJNI.numaRead(str);
        if (numaRead == 0) {
            return null;
        }
        return new Numa(numaRead, false);
    }

    public static Numa numaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long numaReadStream = JniLeptonicaJNI.numaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (numaReadStream == 0) {
            return null;
        }
        return new Numa(numaReadStream, false);
    }

    public static int numaWrite(String str, Numa numa) {
        return JniLeptonicaJNI.numaWrite(str, Numa.getCPtr(numa), numa);
    }

    public static int numaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Numa numa) {
        return JniLeptonicaJNI.numaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Numa.getCPtr(numa), numa);
    }

    public static Numaa numaaCreate(int i) {
        long numaaCreate = JniLeptonicaJNI.numaaCreate(i);
        if (numaaCreate == 0) {
            return null;
        }
        return new Numaa(numaaCreate, false);
    }

    public static Numaa numaaCreateFull(int i, int i2) {
        long numaaCreateFull = JniLeptonicaJNI.numaaCreateFull(i, i2);
        if (numaaCreateFull == 0) {
            return null;
        }
        return new Numaa(numaaCreateFull, false);
    }

    public static int numaaTruncate(Numaa numaa) {
        return JniLeptonicaJNI.numaaTruncate(Numaa.getCPtr(numaa), numaa);
    }

    public static void numaaDestroy(SWIGTYPE_p_p_Numaa sWIGTYPE_p_p_Numaa) {
        JniLeptonicaJNI.numaaDestroy(SWIGTYPE_p_p_Numaa.getCPtr(sWIGTYPE_p_p_Numaa));
    }

    public static int numaaAddNuma(Numaa numaa, Numa numa, int i) {
        return JniLeptonicaJNI.numaaAddNuma(Numaa.getCPtr(numaa), numaa, Numa.getCPtr(numa), numa, i);
    }

    public static int numaaExtendArray(Numaa numaa) {
        return JniLeptonicaJNI.numaaExtendArray(Numaa.getCPtr(numaa), numaa);
    }

    public static int numaaGetCount(Numaa numaa) {
        return JniLeptonicaJNI.numaaGetCount(Numaa.getCPtr(numaa), numaa);
    }

    public static int numaaGetNumaCount(Numaa numaa, int i) {
        return JniLeptonicaJNI.numaaGetNumaCount(Numaa.getCPtr(numaa), numaa, i);
    }

    public static int numaaGetNumberCount(Numaa numaa) {
        return JniLeptonicaJNI.numaaGetNumberCount(Numaa.getCPtr(numaa), numaa);
    }

    public static SWIGTYPE_p_p_Numa numaaGetPtrArray(Numaa numaa) {
        long numaaGetPtrArray = JniLeptonicaJNI.numaaGetPtrArray(Numaa.getCPtr(numaa), numaa);
        if (numaaGetPtrArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Numa(numaaGetPtrArray, false);
    }

    public static Numa numaaGetNuma(Numaa numaa, int i, int i2) {
        long numaaGetNuma = JniLeptonicaJNI.numaaGetNuma(Numaa.getCPtr(numaa), numaa, i, i2);
        if (numaaGetNuma == 0) {
            return null;
        }
        return new Numa(numaaGetNuma, false);
    }

    public static int numaaReplaceNuma(Numaa numaa, int i, Numa numa) {
        return JniLeptonicaJNI.numaaReplaceNuma(Numaa.getCPtr(numaa), numaa, i, Numa.getCPtr(numa), numa);
    }

    public static int numaaGetValue(Numaa numaa, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaaGetValue(Numaa.getCPtr(numaa), numaa, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaaAddNumber(Numaa numaa, int i, float f) {
        return JniLeptonicaJNI.numaaAddNumber(Numaa.getCPtr(numaa), numaa, i, f);
    }

    public static Numaa numaaRead(String str) {
        long numaaRead = JniLeptonicaJNI.numaaRead(str);
        if (numaaRead == 0) {
            return null;
        }
        return new Numaa(numaaRead, false);
    }

    public static Numaa numaaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long numaaReadStream = JniLeptonicaJNI.numaaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (numaaReadStream == 0) {
            return null;
        }
        return new Numaa(numaaReadStream, false);
    }

    public static int numaaWrite(String str, Numaa numaa) {
        return JniLeptonicaJNI.numaaWrite(str, Numaa.getCPtr(numaa), numaa);
    }

    public static int numaaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Numaa numaa) {
        return JniLeptonicaJNI.numaaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Numaa.getCPtr(numaa), numaa);
    }

    public static Numa2d numa2dCreate(int i, int i2, int i3) {
        long numa2dCreate = JniLeptonicaJNI.numa2dCreate(i, i2, i3);
        if (numa2dCreate == 0) {
            return null;
        }
        return new Numa2d(numa2dCreate, false);
    }

    public static void numa2dDestroy(SWIGTYPE_p_p_Numa2d sWIGTYPE_p_p_Numa2d) {
        JniLeptonicaJNI.numa2dDestroy(SWIGTYPE_p_p_Numa2d.getCPtr(sWIGTYPE_p_p_Numa2d));
    }

    public static int numa2dAddNumber(Numa2d numa2d, int i, int i2, float f) {
        return JniLeptonicaJNI.numa2dAddNumber(Numa2d.getCPtr(numa2d), numa2d, i, i2, f);
    }

    public static int numa2dGetCount(Numa2d numa2d, int i, int i2) {
        return JniLeptonicaJNI.numa2dGetCount(Numa2d.getCPtr(numa2d), numa2d, i, i2);
    }

    public static Numa numa2dGetNuma(Numa2d numa2d, int i, int i2) {
        long numa2dGetNuma = JniLeptonicaJNI.numa2dGetNuma(Numa2d.getCPtr(numa2d), numa2d, i, i2);
        if (numa2dGetNuma == 0) {
            return null;
        }
        return new Numa(numa2dGetNuma, false);
    }

    public static int numa2dGetFValue(Numa2d numa2d, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numa2dGetFValue(Numa2d.getCPtr(numa2d), numa2d, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numa2dGetIValue(Numa2d numa2d, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numa2dGetIValue(Numa2d.getCPtr(numa2d), numa2d, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static NumaHash numaHashCreate(int i, int i2) {
        long numaHashCreate = JniLeptonicaJNI.numaHashCreate(i, i2);
        if (numaHashCreate == 0) {
            return null;
        }
        return new NumaHash(numaHashCreate, false);
    }

    public static void numaHashDestroy(SWIGTYPE_p_p_NumaHash sWIGTYPE_p_p_NumaHash) {
        JniLeptonicaJNI.numaHashDestroy(SWIGTYPE_p_p_NumaHash.getCPtr(sWIGTYPE_p_p_NumaHash));
    }

    public static Numa numaHashGetNuma(NumaHash numaHash, long j) {
        long numaHashGetNuma = JniLeptonicaJNI.numaHashGetNuma(NumaHash.getCPtr(numaHash), numaHash, j);
        if (numaHashGetNuma == 0) {
            return null;
        }
        return new Numa(numaHashGetNuma, false);
    }

    public static int numaHashAdd(NumaHash numaHash, long j, float f) {
        return JniLeptonicaJNI.numaHashAdd(NumaHash.getCPtr(numaHash), numaHash, j, f);
    }

    public static Numa numaArithOp(Numa numa, Numa numa2, Numa numa3, int i) {
        long numaArithOp = JniLeptonicaJNI.numaArithOp(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, Numa.getCPtr(numa3), numa3, i);
        if (numaArithOp == 0) {
            return null;
        }
        return new Numa(numaArithOp, false);
    }

    public static Numa numaLogicalOp(Numa numa, Numa numa2, Numa numa3, int i) {
        long numaLogicalOp = JniLeptonicaJNI.numaLogicalOp(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, Numa.getCPtr(numa3), numa3, i);
        if (numaLogicalOp == 0) {
            return null;
        }
        return new Numa(numaLogicalOp, false);
    }

    public static Numa numaInvert(Numa numa, Numa numa2) {
        long numaInvert = JniLeptonicaJNI.numaInvert(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (numaInvert == 0) {
            return null;
        }
        return new Numa(numaInvert, false);
    }

    public static int numaSimilar(Numa numa, Numa numa2, float f, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaSimilar(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaAddToNumber(Numa numa, int i, float f) {
        return JniLeptonicaJNI.numaAddToNumber(Numa.getCPtr(numa), numa, i, f);
    }

    public static int numaGetMin(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetMin(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaGetMax(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetMax(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaGetSum(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaGetSum(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa numaGetPartialSums(Numa numa) {
        long numaGetPartialSums = JniLeptonicaJNI.numaGetPartialSums(Numa.getCPtr(numa), numa);
        if (numaGetPartialSums == 0) {
            return null;
        }
        return new Numa(numaGetPartialSums, false);
    }

    public static int numaGetSumOnInterval(Numa numa, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaGetSumOnInterval(Numa.getCPtr(numa), numa, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaHasOnlyIntegers(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaHasOnlyIntegers(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Numa numaSubsample(Numa numa, int i) {
        long numaSubsample = JniLeptonicaJNI.numaSubsample(Numa.getCPtr(numa), numa, i);
        if (numaSubsample == 0) {
            return null;
        }
        return new Numa(numaSubsample, false);
    }

    public static Numa numaMakeDelta(Numa numa) {
        long numaMakeDelta = JniLeptonicaJNI.numaMakeDelta(Numa.getCPtr(numa), numa);
        if (numaMakeDelta == 0) {
            return null;
        }
        return new Numa(numaMakeDelta, false);
    }

    public static Numa numaMakeSequence(float f, float f2, int i) {
        long numaMakeSequence = JniLeptonicaJNI.numaMakeSequence(f, f2, i);
        if (numaMakeSequence == 0) {
            return null;
        }
        return new Numa(numaMakeSequence, false);
    }

    public static Numa numaMakeConstant(float f, int i) {
        long numaMakeConstant = JniLeptonicaJNI.numaMakeConstant(f, i);
        if (numaMakeConstant == 0) {
            return null;
        }
        return new Numa(numaMakeConstant, false);
    }

    public static Numa numaMakeAbsValue(Numa numa, Numa numa2) {
        long numaMakeAbsValue = JniLeptonicaJNI.numaMakeAbsValue(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (numaMakeAbsValue == 0) {
            return null;
        }
        return new Numa(numaMakeAbsValue, false);
    }

    public static Numa numaAddBorder(Numa numa, int i, int i2, float f) {
        long numaAddBorder = JniLeptonicaJNI.numaAddBorder(Numa.getCPtr(numa), numa, i, i2, f);
        if (numaAddBorder == 0) {
            return null;
        }
        return new Numa(numaAddBorder, false);
    }

    public static Numa numaAddSpecifiedBorder(Numa numa, int i, int i2, int i3) {
        long numaAddSpecifiedBorder = JniLeptonicaJNI.numaAddSpecifiedBorder(Numa.getCPtr(numa), numa, i, i2, i3);
        if (numaAddSpecifiedBorder == 0) {
            return null;
        }
        return new Numa(numaAddSpecifiedBorder, false);
    }

    public static Numa numaRemoveBorder(Numa numa, int i, int i2) {
        long numaRemoveBorder = JniLeptonicaJNI.numaRemoveBorder(Numa.getCPtr(numa), numa, i, i2);
        if (numaRemoveBorder == 0) {
            return null;
        }
        return new Numa(numaRemoveBorder, false);
    }

    public static int numaGetNonzeroRange(Numa numa, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.numaGetNonzeroRange(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int numaGetCountRelativeToZero(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetCountRelativeToZero(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Numa numaClipToInterval(Numa numa, int i, int i2) {
        long numaClipToInterval = JniLeptonicaJNI.numaClipToInterval(Numa.getCPtr(numa), numa, i, i2);
        if (numaClipToInterval == 0) {
            return null;
        }
        return new Numa(numaClipToInterval, false);
    }

    public static Numa numaMakeThresholdIndicator(Numa numa, float f, int i) {
        long numaMakeThresholdIndicator = JniLeptonicaJNI.numaMakeThresholdIndicator(Numa.getCPtr(numa), numa, f, i);
        if (numaMakeThresholdIndicator == 0) {
            return null;
        }
        return new Numa(numaMakeThresholdIndicator, false);
    }

    public static Numa numaUniformSampling(Numa numa, int i) {
        long numaUniformSampling = JniLeptonicaJNI.numaUniformSampling(Numa.getCPtr(numa), numa, i);
        if (numaUniformSampling == 0) {
            return null;
        }
        return new Numa(numaUniformSampling, false);
    }

    public static Numa numaReverse(Numa numa, Numa numa2) {
        long numaReverse = JniLeptonicaJNI.numaReverse(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (numaReverse == 0) {
            return null;
        }
        return new Numa(numaReverse, false);
    }

    public static Numa numaLowPassIntervals(Numa numa, float f, float f2) {
        long numaLowPassIntervals = JniLeptonicaJNI.numaLowPassIntervals(Numa.getCPtr(numa), numa, f, f2);
        if (numaLowPassIntervals == 0) {
            return null;
        }
        return new Numa(numaLowPassIntervals, false);
    }

    public static Numa numaThresholdEdges(Numa numa, float f, float f2, float f3) {
        long numaThresholdEdges = JniLeptonicaJNI.numaThresholdEdges(Numa.getCPtr(numa), numa, f, f2, f3);
        if (numaThresholdEdges == 0) {
            return null;
        }
        return new Numa(numaThresholdEdges, false);
    }

    public static int numaGetSpanValues(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.numaGetSpanValues(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int numaGetEdgeValues(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.numaGetEdgeValues(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int numaInterpolateEqxVal(float f, float f2, Numa numa, int i, float f3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaInterpolateEqxVal(f, f2, Numa.getCPtr(numa), numa, i, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaInterpolateArbxVal(Numa numa, Numa numa2, int i, float f, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaInterpolateArbxVal(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaInterpolateEqxInterval(float f, float f2, Numa numa, int i, float f3, float f4, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaInterpolateEqxInterval(f, f2, Numa.getCPtr(numa), numa, i, f3, f4, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int numaInterpolateArbxInterval(Numa numa, Numa numa2, int i, float f, float f2, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaInterpolateArbxInterval(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, f, f2, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int numaFitMax(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, Numa numa2, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.numaFitMax(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), Numa.getCPtr(numa2), numa2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int numaDifferentiateInterval(Numa numa, Numa numa2, float f, float f2, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaDifferentiateInterval(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f, f2, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int numaIntegrateInterval(Numa numa, Numa numa2, float f, float f2, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaIntegrateInterval(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f, f2, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaSortGeneral(Numa numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, int i, int i2) {
        return JniLeptonicaJNI.numaSortGeneral(Numa.getCPtr(numa), numa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), i, i2);
    }

    public static Numa numaSortAutoSelect(Numa numa, int i) {
        long numaSortAutoSelect = JniLeptonicaJNI.numaSortAutoSelect(Numa.getCPtr(numa), numa, i);
        if (numaSortAutoSelect == 0) {
            return null;
        }
        return new Numa(numaSortAutoSelect, false);
    }

    public static Numa numaSortIndexAutoSelect(Numa numa, int i) {
        long numaSortIndexAutoSelect = JniLeptonicaJNI.numaSortIndexAutoSelect(Numa.getCPtr(numa), numa, i);
        if (numaSortIndexAutoSelect == 0) {
            return null;
        }
        return new Numa(numaSortIndexAutoSelect, false);
    }

    public static int numaChooseSortType(Numa numa) {
        return JniLeptonicaJNI.numaChooseSortType(Numa.getCPtr(numa), numa);
    }

    public static Numa numaSort(Numa numa, Numa numa2, int i) {
        long numaSort = JniLeptonicaJNI.numaSort(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i);
        if (numaSort == 0) {
            return null;
        }
        return new Numa(numaSort, false);
    }

    public static Numa numaBinSort(Numa numa, int i) {
        long numaBinSort = JniLeptonicaJNI.numaBinSort(Numa.getCPtr(numa), numa, i);
        if (numaBinSort == 0) {
            return null;
        }
        return new Numa(numaBinSort, false);
    }

    public static Numa numaGetSortIndex(Numa numa, int i) {
        long numaGetSortIndex = JniLeptonicaJNI.numaGetSortIndex(Numa.getCPtr(numa), numa, i);
        if (numaGetSortIndex == 0) {
            return null;
        }
        return new Numa(numaGetSortIndex, false);
    }

    public static Numa numaGetBinSortIndex(Numa numa, int i) {
        long numaGetBinSortIndex = JniLeptonicaJNI.numaGetBinSortIndex(Numa.getCPtr(numa), numa, i);
        if (numaGetBinSortIndex == 0) {
            return null;
        }
        return new Numa(numaGetBinSortIndex, false);
    }

    public static Numa numaSortByIndex(Numa numa, Numa numa2) {
        long numaSortByIndex = JniLeptonicaJNI.numaSortByIndex(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (numaSortByIndex == 0) {
            return null;
        }
        return new Numa(numaSortByIndex, false);
    }

    public static int numaIsSorted(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaIsSorted(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaSortPair(Numa numa, Numa numa2, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaSortPair(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static Numa numaInvertMap(Numa numa) {
        long numaInvertMap = JniLeptonicaJNI.numaInvertMap(Numa.getCPtr(numa), numa);
        if (numaInvertMap == 0) {
            return null;
        }
        return new Numa(numaInvertMap, false);
    }

    public static Numa numaPseudorandomSequence(int i, int i2) {
        long numaPseudorandomSequence = JniLeptonicaJNI.numaPseudorandomSequence(i, i2);
        if (numaPseudorandomSequence == 0) {
            return null;
        }
        return new Numa(numaPseudorandomSequence, false);
    }

    public static Numa numaRandomPermutation(Numa numa, int i) {
        long numaRandomPermutation = JniLeptonicaJNI.numaRandomPermutation(Numa.getCPtr(numa), numa, i);
        if (numaRandomPermutation == 0) {
            return null;
        }
        return new Numa(numaRandomPermutation, false);
    }

    public static int numaGetRankValue(Numa numa, float f, Numa numa2, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaGetRankValue(Numa.getCPtr(numa), numa, f, Numa.getCPtr(numa2), numa2, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaGetMedian(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaGetMedian(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaGetBinnedMedian(Numa numa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetBinnedMedian(Numa.getCPtr(numa), numa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaGetMode(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.numaGetMode(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int numaGetMedianVariation(Numa numa, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.numaGetMedianVariation(Numa.getCPtr(numa), numa, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int numaJoin(Numa numa, Numa numa2, int i, int i2) {
        return JniLeptonicaJNI.numaJoin(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, i, i2);
    }

    public static int numaaJoin(Numaa numaa, Numaa numaa2, int i, int i2) {
        return JniLeptonicaJNI.numaaJoin(Numaa.getCPtr(numaa), numaa, Numaa.getCPtr(numaa2), numaa2, i, i2);
    }

    public static Numa numaaFlattenToNuma(Numaa numaa) {
        long numaaFlattenToNuma = JniLeptonicaJNI.numaaFlattenToNuma(Numaa.getCPtr(numaa), numaa);
        if (numaaFlattenToNuma == 0) {
            return null;
        }
        return new Numa(numaaFlattenToNuma, false);
    }

    public static Numa numaErode(Numa numa, int i) {
        long numaErode = JniLeptonicaJNI.numaErode(Numa.getCPtr(numa), numa, i);
        if (numaErode == 0) {
            return null;
        }
        return new Numa(numaErode, false);
    }

    public static Numa numaDilate(Numa numa, int i) {
        long numaDilate = JniLeptonicaJNI.numaDilate(Numa.getCPtr(numa), numa, i);
        if (numaDilate == 0) {
            return null;
        }
        return new Numa(numaDilate, false);
    }

    public static Numa numaOpen(Numa numa, int i) {
        long numaOpen = JniLeptonicaJNI.numaOpen(Numa.getCPtr(numa), numa, i);
        if (numaOpen == 0) {
            return null;
        }
        return new Numa(numaOpen, false);
    }

    public static Numa numaClose(Numa numa, int i) {
        long numaClose = JniLeptonicaJNI.numaClose(Numa.getCPtr(numa), numa, i);
        if (numaClose == 0) {
            return null;
        }
        return new Numa(numaClose, false);
    }

    public static Numa numaTransform(Numa numa, float f, float f2) {
        long numaTransform = JniLeptonicaJNI.numaTransform(Numa.getCPtr(numa), numa, f, f2);
        if (numaTransform == 0) {
            return null;
        }
        return new Numa(numaTransform, false);
    }

    public static int numaWindowedStats(Numa numa, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4) {
        return JniLeptonicaJNI.numaWindowedStats(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4));
    }

    public static Numa numaWindowedMean(Numa numa, int i) {
        long numaWindowedMean = JniLeptonicaJNI.numaWindowedMean(Numa.getCPtr(numa), numa, i);
        if (numaWindowedMean == 0) {
            return null;
        }
        return new Numa(numaWindowedMean, false);
    }

    public static Numa numaWindowedMeanSquare(Numa numa, int i) {
        long numaWindowedMeanSquare = JniLeptonicaJNI.numaWindowedMeanSquare(Numa.getCPtr(numa), numa, i);
        if (numaWindowedMeanSquare == 0) {
            return null;
        }
        return new Numa(numaWindowedMeanSquare, false);
    }

    public static int numaWindowedVariance(Numa numa, Numa numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaWindowedVariance(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static Numa numaConvertToInt(Numa numa) {
        long numaConvertToInt = JniLeptonicaJNI.numaConvertToInt(Numa.getCPtr(numa), numa);
        if (numaConvertToInt == 0) {
            return null;
        }
        return new Numa(numaConvertToInt, false);
    }

    public static Numa numaMakeHistogram(Numa numa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long numaMakeHistogram = JniLeptonicaJNI.numaMakeHistogram(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (numaMakeHistogram == 0) {
            return null;
        }
        return new Numa(numaMakeHistogram, false);
    }

    public static Numa numaMakeHistogramAuto(Numa numa, int i) {
        long numaMakeHistogramAuto = JniLeptonicaJNI.numaMakeHistogramAuto(Numa.getCPtr(numa), numa, i);
        if (numaMakeHistogramAuto == 0) {
            return null;
        }
        return new Numa(numaMakeHistogramAuto, false);
    }

    public static Numa numaMakeHistogramClipped(Numa numa, float f, float f2) {
        long numaMakeHistogramClipped = JniLeptonicaJNI.numaMakeHistogramClipped(Numa.getCPtr(numa), numa, f, f2);
        if (numaMakeHistogramClipped == 0) {
            return null;
        }
        return new Numa(numaMakeHistogramClipped, false);
    }

    public static Numa numaRebinHistogram(Numa numa, int i) {
        long numaRebinHistogram = JniLeptonicaJNI.numaRebinHistogram(Numa.getCPtr(numa), numa, i);
        if (numaRebinHistogram == 0) {
            return null;
        }
        return new Numa(numaRebinHistogram, false);
    }

    public static Numa numaNormalizeHistogram(Numa numa, float f) {
        long numaNormalizeHistogram = JniLeptonicaJNI.numaNormalizeHistogram(Numa.getCPtr(numa), numa, f);
        if (numaNormalizeHistogram == 0) {
            return null;
        }
        return new Numa(numaNormalizeHistogram, false);
    }

    public static int numaGetStatsUsingHistogram(Numa numa, int i, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_float sWIGTYPE_p_float5, float f, SWIGTYPE_p_float sWIGTYPE_p_float6, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.numaGetStatsUsingHistogram(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float5), f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float6), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int numaGetHistogramStats(Numa numa, float f, float f2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4) {
        return JniLeptonicaJNI.numaGetHistogramStats(Numa.getCPtr(numa), numa, f, f2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4));
    }

    public static int numaGetHistogramStatsOnInterval(Numa numa, float f, float f2, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4) {
        return JniLeptonicaJNI.numaGetHistogramStatsOnInterval(Numa.getCPtr(numa), numa, f, f2, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4));
    }

    public static int numaMakeRankFromHistogram(float f, float f2, Numa numa, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaMakeRankFromHistogram(f, f2, Numa.getCPtr(numa), numa, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int numaHistogramGetRankFromVal(Numa numa, float f, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaHistogramGetRankFromVal(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaHistogramGetValFromRank(Numa numa, float f, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaHistogramGetValFromRank(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaDiscretizeRankAndIntensity(Numa numa, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4) {
        return JniLeptonicaJNI.numaDiscretizeRankAndIntensity(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4));
    }

    public static int numaGetRankBinValues(Numa numa, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.numaGetRankBinValues(Numa.getCPtr(numa), numa, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int numaSplitDistribution(Numa numa, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.numaSplitDistribution(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int numaEarthMoverDistance(Numa numa, Numa numa2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaEarthMoverDistance(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa numaFindPeaks(Numa numa, int i, float f, float f2) {
        long numaFindPeaks = JniLeptonicaJNI.numaFindPeaks(Numa.getCPtr(numa), numa, i, f, f2);
        if (numaFindPeaks == 0) {
            return null;
        }
        return new Numa(numaFindPeaks, false);
    }

    public static Numa numaFindExtrema(Numa numa, float f) {
        long numaFindExtrema = JniLeptonicaJNI.numaFindExtrema(Numa.getCPtr(numa), numa, f);
        if (numaFindExtrema == 0) {
            return null;
        }
        return new Numa(numaFindExtrema, false);
    }

    public static int numaCountReversals(Numa numa, float f, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaCountReversals(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int numaSelectCrossingThreshold(Numa numa, Numa numa2, float f, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaSelectCrossingThreshold(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa numaCrossingsByThreshold(Numa numa, Numa numa2, float f) {
        long numaCrossingsByThreshold = JniLeptonicaJNI.numaCrossingsByThreshold(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f);
        if (numaCrossingsByThreshold == 0) {
            return null;
        }
        return new Numa(numaCrossingsByThreshold, false);
    }

    public static Numa numaCrossingsByPeaks(Numa numa, Numa numa2, float f) {
        long numaCrossingsByPeaks = JniLeptonicaJNI.numaCrossingsByPeaks(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2, f);
        if (numaCrossingsByPeaks == 0) {
            return null;
        }
        return new Numa(numaCrossingsByPeaks, false);
    }

    public static int numaEvalBestHaarParameters(Numa numa, float f, int i, int i2, float f2, float f3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.numaEvalBestHaarParameters(Numa.getCPtr(numa), numa, f, i, i2, f2, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int numaEvalHaarSum(Numa numa, float f, float f2, float f3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.numaEvalHaarSum(Numa.getCPtr(numa), numa, f, f2, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixGetRegionsBinary(Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3, int i) {
        return JniLeptonicaJNI.pixGetRegionsBinary(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3), i);
    }

    public static Pix pixGenHalftoneMask(Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long pixGenHalftoneMask = JniLeptonicaJNI.pixGenHalftoneMask(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
        if (pixGenHalftoneMask == 0) {
            return null;
        }
        return new Pix(pixGenHalftoneMask, false);
    }

    public static Pix pixGenTextlineMask(Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        long pixGenTextlineMask = JniLeptonicaJNI.pixGenTextlineMask(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
        if (pixGenTextlineMask == 0) {
            return null;
        }
        return new Pix(pixGenTextlineMask, false);
    }

    public static Pix pixGenTextblockMask(Pix pix, Pix pix2, int i) {
        long pixGenTextblockMask = JniLeptonicaJNI.pixGenTextblockMask(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
        if (pixGenTextblockMask == 0) {
            return null;
        }
        return new Pix(pixGenTextblockMask, false);
    }

    public static Box pixFindPageForeground(Pix pix, int i, int i2, int i3, int i4, int i5, int i6, String str) {
        long pixFindPageForeground = JniLeptonicaJNI.pixFindPageForeground(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6, str);
        if (pixFindPageForeground == 0) {
            return null;
        }
        return new Box(pixFindPageForeground, false);
    }

    public static int pixSplitIntoCharacters(Pix pix, int i, int i2, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixSplitIntoCharacters(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Boxa pixSplitComponentWithProfile(Pix pix, int i, int i2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixSplitComponentWithProfile = JniLeptonicaJNI.pixSplitComponentWithProfile(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixSplitComponentWithProfile == 0) {
            return null;
        }
        return new Boxa(pixSplitComponentWithProfile, false);
    }

    public static int pixSetSelectCmap(Pix pix, Box box, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixSetSelectCmap(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4);
    }

    public static int pixColorGrayRegionsCmap(Pix pix, Boxa boxa, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixColorGrayRegionsCmap(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, i, i2, i3, i4);
    }

    public static int pixColorGrayCmap(Pix pix, Box box, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixColorGrayCmap(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4);
    }

    public static int addColorizedGrayToCmap(PixColormap pixColormap, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.addColorizedGrayToCmap(PixColormap.getCPtr(pixColormap), pixColormap, i, i2, i3, i4, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int pixSetSelectMaskedCmap(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5, int i6) {
        return JniLeptonicaJNI.pixSetSelectMaskedCmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5, i6);
    }

    public static int pixSetMaskedCmap(Pix pix, Pix pix2, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixSetMaskedCmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, i5);
    }

    public static String parseForProtos(String str, String str2) {
        return JniLeptonicaJNI.parseForProtos(str, str2);
    }

    public static Boxa boxaGetWhiteblocks(Boxa boxa, Box box, int i, int i2, float f, int i3, float f2, int i4) {
        long boxaGetWhiteblocks = JniLeptonicaJNI.boxaGetWhiteblocks(Boxa.getCPtr(boxa), boxa, Box.getCPtr(box), box, i, i2, f, i3, f2, i4);
        if (boxaGetWhiteblocks == 0) {
            return null;
        }
        return new Boxa(boxaGetWhiteblocks, false);
    }

    public static Boxa boxaPruneSortedOnOverlap(Boxa boxa, float f) {
        long boxaPruneSortedOnOverlap = JniLeptonicaJNI.boxaPruneSortedOnOverlap(Boxa.getCPtr(boxa), boxa, f);
        if (boxaPruneSortedOnOverlap == 0) {
            return null;
        }
        return new Boxa(boxaPruneSortedOnOverlap, false);
    }

    public static int convertFilesToPdf(String str, String str2, int i, float f, int i2, int i3, String str3, String str4) {
        return JniLeptonicaJNI.convertFilesToPdf(str, str2, i, f, i2, i3, str3, str4);
    }

    public static int saConvertFilesToPdf(Sarray sarray, int i, float f, int i2, int i3, String str, String str2) {
        return JniLeptonicaJNI.saConvertFilesToPdf(Sarray.getCPtr(sarray), sarray, i, f, i2, i3, str, str2);
    }

    public static int saConvertFilesToPdfData(Sarray sarray, int i, float f, int i2, int i3, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.saConvertFilesToPdfData(Sarray.getCPtr(sarray), sarray, i, f, i2, i3, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int selectDefaultPdfEncoding(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.selectDefaultPdfEncoding(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int convertUnscaledFilesToPdf(String str, String str2, String str3, String str4) {
        return JniLeptonicaJNI.convertUnscaledFilesToPdf(str, str2, str3, str4);
    }

    public static int saConvertUnscaledFilesToPdf(Sarray sarray, String str, String str2) {
        return JniLeptonicaJNI.saConvertUnscaledFilesToPdf(Sarray.getCPtr(sarray), sarray, str, str2);
    }

    public static int saConvertUnscaledFilesToPdfData(Sarray sarray, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.saConvertUnscaledFilesToPdfData(Sarray.getCPtr(sarray), sarray, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int convertUnscaledToPdfData(String str, String str2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.convertUnscaledToPdfData(str, str2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int pixaConvertToPdf(Pixa pixa, int i, float f, int i2, int i3, String str, String str2) {
        return JniLeptonicaJNI.pixaConvertToPdf(Pixa.getCPtr(pixa), pixa, i, f, i2, i3, str, str2);
    }

    public static int pixaConvertToPdfData(Pixa pixa, int i, float f, int i2, int i3, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.pixaConvertToPdfData(Pixa.getCPtr(pixa), pixa, i, f, i2, i3, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int convertToPdf(String str, int i, int i2, String str2, int i3, int i4, int i5, String str3, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.convertToPdf(str, i, i2, str2, i3, i4, i5, str3, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int convertImageDataToPdf(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i, int i2, String str, int i3, int i4, int i5, String str2, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.convertImageDataToPdf(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i, i2, str, i3, i4, i5, str2, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int convertToPdfData(String str, int i, int i2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, int i3, int i4, int i5, String str2, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.convertToPdfData(str, i, i2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), i3, i4, i5, str2, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int convertImageDataToPdfData(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i, int i2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, int i3, int i4, int i5, String str, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.convertImageDataToPdfData(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i, i2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), i3, i4, i5, str, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int pixConvertToPdf(Pix pix, int i, int i2, String str, int i3, int i4, int i5, String str2, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.pixConvertToPdf(Pix.getCPtr(pix), pix, i, i2, str, i3, i4, i5, str2, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int pixWriteStreamPdf(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i, String str) {
        return JniLeptonicaJNI.pixWriteStreamPdf(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i, str);
    }

    public static int pixWriteMemPdf(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i, String str) {
        return JniLeptonicaJNI.pixWriteMemPdf(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i, str);
    }

    public static int convertSegmentedFilesToPdf(String str, String str2, int i, int i2, int i3, Boxaa boxaa, int i4, float f, String str3, String str4) {
        return JniLeptonicaJNI.convertSegmentedFilesToPdf(str, str2, i, i2, i3, Boxaa.getCPtr(boxaa), boxaa, i4, f, str3, str4);
    }

    public static Boxaa convertNumberedMasksToBoxaa(String str, String str2, int i, int i2) {
        long convertNumberedMasksToBoxaa = JniLeptonicaJNI.convertNumberedMasksToBoxaa(str, str2, i, i2);
        if (convertNumberedMasksToBoxaa == 0) {
            return null;
        }
        return new Boxaa(convertNumberedMasksToBoxaa, false);
    }

    public static int convertToPdfSegmented(String str, int i, int i2, int i3, Boxa boxa, int i4, float f, String str2, String str3) {
        return JniLeptonicaJNI.convertToPdfSegmented(str, i, i2, i3, Boxa.getCPtr(boxa), boxa, i4, f, str2, str3);
    }

    public static int pixConvertToPdfSegmented(Pix pix, int i, int i2, int i3, Boxa boxa, int i4, float f, String str, String str2) {
        return JniLeptonicaJNI.pixConvertToPdfSegmented(Pix.getCPtr(pix), pix, i, i2, i3, Boxa.getCPtr(boxa), boxa, i4, f, str, str2);
    }

    public static int convertToPdfDataSegmented(String str, int i, int i2, int i3, Boxa boxa, int i4, float f, String str2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.convertToPdfDataSegmented(str, i, i2, i3, Boxa.getCPtr(boxa), boxa, i4, f, str2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int pixConvertToPdfDataSegmented(Pix pix, int i, int i2, int i3, Boxa boxa, int i4, float f, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.pixConvertToPdfDataSegmented(Pix.getCPtr(pix), pix, i, i2, i3, Boxa.getCPtr(boxa), boxa, i4, f, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int concatenatePdf(String str, String str2, String str3) {
        return JniLeptonicaJNI.concatenatePdf(str, str2, str3);
    }

    public static int saConcatenatePdf(Sarray sarray, String str) {
        return JniLeptonicaJNI.saConcatenatePdf(Sarray.getCPtr(sarray), sarray, str);
    }

    public static int ptraConcatenatePdf(L_Ptra l_Ptra, String str) {
        return JniLeptonicaJNI.ptraConcatenatePdf(L_Ptra.getCPtr(l_Ptra), l_Ptra, str);
    }

    public static int concatenatePdfToData(String str, String str2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.concatenatePdfToData(str, str2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int saConcatenatePdfToData(Sarray sarray, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.saConcatenatePdfToData(Sarray.getCPtr(sarray), sarray, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int pixConvertToPdfData(Pix pix, int i, int i2, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, int i3, int i4, int i5, String str, SWIGTYPE_p_p_L_Pdf_Data sWIGTYPE_p_p_L_Pdf_Data, int i6) {
        return JniLeptonicaJNI.pixConvertToPdfData(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), i3, i4, i5, str, SWIGTYPE_p_p_L_Pdf_Data.getCPtr(sWIGTYPE_p_p_L_Pdf_Data), i6);
    }

    public static int ptraConcatenatePdfToData(L_Ptra l_Ptra, Sarray sarray, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.ptraConcatenatePdfToData(L_Ptra.getCPtr(l_Ptra), l_Ptra, Sarray.getCPtr(sarray), sarray, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int l_generateCIDataForPdf(String str, Pix pix, int i, SWIGTYPE_p_p_L_Compressed_Data sWIGTYPE_p_p_L_Compressed_Data) {
        return JniLeptonicaJNI.l_generateCIDataForPdf(str, Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_L_Compressed_Data.getCPtr(sWIGTYPE_p_p_L_Compressed_Data));
    }

    public static L_Compressed_Data l_generateFlateDataPdf(String str) {
        long l_generateFlateDataPdf = JniLeptonicaJNI.l_generateFlateDataPdf(str);
        if (l_generateFlateDataPdf == 0) {
            return null;
        }
        return new L_Compressed_Data(l_generateFlateDataPdf, false);
    }

    public static L_Compressed_Data l_generateJpegData(String str, int i) {
        long l_generateJpegData = JniLeptonicaJNI.l_generateJpegData(str, i);
        if (l_generateJpegData == 0) {
            return null;
        }
        return new L_Compressed_Data(l_generateJpegData, false);
    }

    public static int l_generateCIData(String str, int i, int i2, int i3, SWIGTYPE_p_p_L_Compressed_Data sWIGTYPE_p_p_L_Compressed_Data) {
        return JniLeptonicaJNI.l_generateCIData(str, i, i2, i3, SWIGTYPE_p_p_L_Compressed_Data.getCPtr(sWIGTYPE_p_p_L_Compressed_Data));
    }

    public static int pixGenerateCIData(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_L_Compressed_Data sWIGTYPE_p_p_L_Compressed_Data) {
        return JniLeptonicaJNI.pixGenerateCIData(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_L_Compressed_Data.getCPtr(sWIGTYPE_p_p_L_Compressed_Data));
    }

    public static L_Compressed_Data l_generateFlateData(String str, int i) {
        long l_generateFlateData = JniLeptonicaJNI.l_generateFlateData(str, i);
        if (l_generateFlateData == 0) {
            return null;
        }
        return new L_Compressed_Data(l_generateFlateData, false);
    }

    public static L_Compressed_Data l_generateG4Data(String str, int i) {
        long l_generateG4Data = JniLeptonicaJNI.l_generateG4Data(str, i);
        if (l_generateG4Data == 0) {
            return null;
        }
        return new L_Compressed_Data(l_generateG4Data, false);
    }

    public static int cidConvertToPdfData(L_Compressed_Data l_Compressed_Data, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.cidConvertToPdfData(L_Compressed_Data.getCPtr(l_Compressed_Data), l_Compressed_Data, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static void l_CIDataDestroy(SWIGTYPE_p_p_L_Compressed_Data sWIGTYPE_p_p_L_Compressed_Data) {
        JniLeptonicaJNI.l_CIDataDestroy(SWIGTYPE_p_p_L_Compressed_Data.getCPtr(sWIGTYPE_p_p_L_Compressed_Data));
    }

    public static void l_pdfSetG4ImageMask(int i) {
        JniLeptonicaJNI.l_pdfSetG4ImageMask(i);
    }

    public static void l_pdfSetDateAndVersion(int i) {
        JniLeptonicaJNI.l_pdfSetDateAndVersion(i);
    }

    public static Pix pixCreate(int i, int i2, int i3) {
        long pixCreate = JniLeptonicaJNI.pixCreate(i, i2, i3);
        if (pixCreate == 0) {
            return null;
        }
        return new Pix(pixCreate, false);
    }

    public static Pix pixCreateNoInit(int i, int i2, int i3) {
        long pixCreateNoInit = JniLeptonicaJNI.pixCreateNoInit(i, i2, i3);
        if (pixCreateNoInit == 0) {
            return null;
        }
        return new Pix(pixCreateNoInit, false);
    }

    public static Pix pixCreateTemplate(Pix pix) {
        long pixCreateTemplate = JniLeptonicaJNI.pixCreateTemplate(Pix.getCPtr(pix), pix);
        if (pixCreateTemplate == 0) {
            return null;
        }
        return new Pix(pixCreateTemplate, false);
    }

    public static Pix pixCreateTemplateNoInit(Pix pix) {
        long pixCreateTemplateNoInit = JniLeptonicaJNI.pixCreateTemplateNoInit(Pix.getCPtr(pix), pix);
        if (pixCreateTemplateNoInit == 0) {
            return null;
        }
        return new Pix(pixCreateTemplateNoInit, false);
    }

    public static Pix pixCreateHeader(int i, int i2, int i3) {
        long pixCreateHeader = JniLeptonicaJNI.pixCreateHeader(i, i2, i3);
        if (pixCreateHeader == 0) {
            return null;
        }
        return new Pix(pixCreateHeader, false);
    }

    public static Pix pixClone(Pix pix) {
        long pixClone = JniLeptonicaJNI.pixClone(Pix.getCPtr(pix), pix);
        if (pixClone == 0) {
            return null;
        }
        return new Pix(pixClone, false);
    }

    public static void pixDestroy(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        JniLeptonicaJNI.pixDestroy(SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Pix pixCopy(Pix pix, Pix pix2) {
        long pixCopy = JniLeptonicaJNI.pixCopy(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixCopy == 0) {
            return null;
        }
        return new Pix(pixCopy, false);
    }

    public static int pixResizeImageData(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixResizeImageData(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixCopyColormap(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopyColormap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixSizesEqual(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixSizesEqual(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixTransferAllData(Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, int i, int i2) {
        return JniLeptonicaJNI.pixTransferAllData(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), i, i2);
    }

    public static int pixSwapAndDestroy(SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixSwapAndDestroy(SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static int pixGetWidth(Pix pix) {
        return JniLeptonicaJNI.pixGetWidth(Pix.getCPtr(pix), pix);
    }

    public static int pixSetWidth(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetWidth(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetHeight(Pix pix) {
        return JniLeptonicaJNI.pixGetHeight(Pix.getCPtr(pix), pix);
    }

    public static int pixSetHeight(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetHeight(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetDepth(Pix pix) {
        return JniLeptonicaJNI.pixGetDepth(Pix.getCPtr(pix), pix);
    }

    public static int pixSetDepth(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetDepth(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetDimensions(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixGetDimensions(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixSetDimensions(Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixSetDimensions(Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static int pixCopyDimensions(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopyDimensions(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixGetSpp(Pix pix) {
        return JniLeptonicaJNI.pixGetSpp(Pix.getCPtr(pix), pix);
    }

    public static int pixSetSpp(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetSpp(Pix.getCPtr(pix), pix, i);
    }

    public static int pixCopySpp(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopySpp(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixGetWpl(Pix pix) {
        return JniLeptonicaJNI.pixGetWpl(Pix.getCPtr(pix), pix);
    }

    public static int pixSetWpl(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetWpl(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetRefcount(Pix pix) {
        return JniLeptonicaJNI.pixGetRefcount(Pix.getCPtr(pix), pix);
    }

    public static int pixChangeRefcount(Pix pix, int i) {
        return JniLeptonicaJNI.pixChangeRefcount(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetXRes(Pix pix) {
        return JniLeptonicaJNI.pixGetXRes(Pix.getCPtr(pix), pix);
    }

    public static int pixSetXRes(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetXRes(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetYRes(Pix pix) {
        return JniLeptonicaJNI.pixGetYRes(Pix.getCPtr(pix), pix);
    }

    public static int pixSetYRes(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetYRes(Pix.getCPtr(pix), pix, i);
    }

    public static int pixGetResolution(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixGetResolution(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixSetResolution(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixSetResolution(Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixCopyResolution(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopyResolution(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int pixScaleResolution(Pix pix, float f, float f2) {
        return JniLeptonicaJNI.pixScaleResolution(Pix.getCPtr(pix), pix, f, f2);
    }

    public static int pixGetInputFormat(Pix pix) {
        return JniLeptonicaJNI.pixGetInputFormat(Pix.getCPtr(pix), pix);
    }

    public static int pixSetInputFormat(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetInputFormat(Pix.getCPtr(pix), pix, i);
    }

    public static int pixCopyInputFormat(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopyInputFormat(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static String pixGetText(Pix pix) {
        return JniLeptonicaJNI.pixGetText(Pix.getCPtr(pix), pix);
    }

    public static int pixSetText(Pix pix, String str) {
        return JniLeptonicaJNI.pixSetText(Pix.getCPtr(pix), pix, str);
    }

    public static int pixAddText(Pix pix, String str) {
        return JniLeptonicaJNI.pixAddText(Pix.getCPtr(pix), pix, str);
    }

    public static int pixCopyText(Pix pix, Pix pix2) {
        return JniLeptonicaJNI.pixCopyText(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static PixColormap pixGetColormap(Pix pix) {
        long pixGetColormap = JniLeptonicaJNI.pixGetColormap(Pix.getCPtr(pix), pix);
        if (pixGetColormap == 0) {
            return null;
        }
        return new PixColormap(pixGetColormap, false);
    }

    public static int pixSetColormap(Pix pix, PixColormap pixColormap) {
        return JniLeptonicaJNI.pixSetColormap(Pix.getCPtr(pix), pix, PixColormap.getCPtr(pixColormap), pixColormap);
    }

    public static int pixDestroyColormap(Pix pix) {
        return JniLeptonicaJNI.pixDestroyColormap(Pix.getCPtr(pix), pix);
    }

    public static SWIGTYPE_p_unsigned_int pixGetData(Pix pix) {
        long pixGetData = JniLeptonicaJNI.pixGetData(Pix.getCPtr(pix), pix);
        if (pixGetData == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(pixGetData, false);
    }

    public static int pixSetData(Pix pix, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixSetData(Pix.getCPtr(pix), pix, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static SWIGTYPE_p_unsigned_int pixExtractData(Pix pix) {
        long pixExtractData = JniLeptonicaJNI.pixExtractData(Pix.getCPtr(pix), pix);
        if (pixExtractData == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(pixExtractData, false);
    }

    public static int pixFreeData(Pix pix) {
        return JniLeptonicaJNI.pixFreeData(Pix.getCPtr(pix), pix);
    }

    public static SWIGTYPE_p_p_void pixGetLinePtrs(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixGetLinePtrs = JniLeptonicaJNI.pixGetLinePtrs(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixGetLinePtrs == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_void(pixGetLinePtrs, false);
    }

    public static int pixPrintStreamInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, String str) {
        return JniLeptonicaJNI.pixPrintStreamInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, str);
    }

    public static int pixGetPixel(Pix pix, int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixGetPixel(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixSetPixel(Pix pix, int i, int i2, long j) {
        return JniLeptonicaJNI.pixSetPixel(Pix.getCPtr(pix), pix, i, i2, j);
    }

    public static int pixGetRGBPixel(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixGetRGBPixel(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixSetRGBPixel(Pix pix, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixSetRGBPixel(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
    }

    public static int pixGetRandomPixel(Pix pix, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixGetRandomPixel(Pix.getCPtr(pix), pix, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixClearPixel(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixClearPixel(Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixFlipPixel(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixFlipPixel(Pix.getCPtr(pix), pix, i, i2);
    }

    public static void setPixelLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, long j) {
        JniLeptonicaJNI.setPixelLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, j);
    }

    public static int pixGetBlackOrWhiteVal(Pix pix, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixGetBlackOrWhiteVal(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixClearAll(Pix pix) {
        return JniLeptonicaJNI.pixClearAll(Pix.getCPtr(pix), pix);
    }

    public static int pixSetAll(Pix pix) {
        return JniLeptonicaJNI.pixSetAll(Pix.getCPtr(pix), pix);
    }

    public static int pixSetAllGray(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetAllGray(Pix.getCPtr(pix), pix, i);
    }

    public static int pixSetAllArbitrary(Pix pix, long j) {
        return JniLeptonicaJNI.pixSetAllArbitrary(Pix.getCPtr(pix), pix, j);
    }

    public static int pixSetBlackOrWhite(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetBlackOrWhite(Pix.getCPtr(pix), pix, i);
    }

    public static int pixSetComponentArbitrary(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixSetComponentArbitrary(Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixClearInRect(Pix pix, Box box) {
        return JniLeptonicaJNI.pixClearInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixSetInRect(Pix pix, Box box) {
        return JniLeptonicaJNI.pixSetInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixSetInRectArbitrary(Pix pix, Box box, long j) {
        return JniLeptonicaJNI.pixSetInRectArbitrary(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, j);
    }

    public static int pixBlendInRect(Pix pix, Box box, long j, float f) {
        return JniLeptonicaJNI.pixBlendInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, j, f);
    }

    public static int pixSetPadBits(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetPadBits(Pix.getCPtr(pix), pix, i);
    }

    public static int pixSetPadBitsBand(Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixSetPadBitsBand(Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static int pixSetOrClearBorder(Pix pix, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixSetOrClearBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
    }

    public static int pixSetBorderVal(Pix pix, int i, int i2, int i3, int i4, long j) {
        return JniLeptonicaJNI.pixSetBorderVal(Pix.getCPtr(pix), pix, i, i2, i3, i4, j);
    }

    public static int pixSetBorderRingVal(Pix pix, int i, long j) {
        return JniLeptonicaJNI.pixSetBorderRingVal(Pix.getCPtr(pix), pix, i, j);
    }

    public static int pixSetMirroredBorder(Pix pix, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixSetMirroredBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4);
    }

    public static Pix pixCopyBorder(Pix pix, Pix pix2, int i, int i2, int i3, int i4) {
        long pixCopyBorder = JniLeptonicaJNI.pixCopyBorder(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4);
        if (pixCopyBorder == 0) {
            return null;
        }
        return new Pix(pixCopyBorder, false);
    }

    public static Pix pixAddBorder(Pix pix, int i, long j) {
        long pixAddBorder = JniLeptonicaJNI.pixAddBorder(Pix.getCPtr(pix), pix, i, j);
        if (pixAddBorder == 0) {
            return null;
        }
        return new Pix(pixAddBorder, false);
    }

    public static Pix pixAddBlackOrWhiteBorder(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixAddBlackOrWhiteBorder = JniLeptonicaJNI.pixAddBlackOrWhiteBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixAddBlackOrWhiteBorder == 0) {
            return null;
        }
        return new Pix(pixAddBlackOrWhiteBorder, false);
    }

    public static Pix pixAddBorderGeneral(Pix pix, int i, int i2, int i3, int i4, long j) {
        long pixAddBorderGeneral = JniLeptonicaJNI.pixAddBorderGeneral(Pix.getCPtr(pix), pix, i, i2, i3, i4, j);
        if (pixAddBorderGeneral == 0) {
            return null;
        }
        return new Pix(pixAddBorderGeneral, false);
    }

    public static Pix pixRemoveBorder(Pix pix, int i) {
        long pixRemoveBorder = JniLeptonicaJNI.pixRemoveBorder(Pix.getCPtr(pix), pix, i);
        if (pixRemoveBorder == 0) {
            return null;
        }
        return new Pix(pixRemoveBorder, false);
    }

    public static Pix pixRemoveBorderGeneral(Pix pix, int i, int i2, int i3, int i4) {
        long pixRemoveBorderGeneral = JniLeptonicaJNI.pixRemoveBorderGeneral(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixRemoveBorderGeneral == 0) {
            return null;
        }
        return new Pix(pixRemoveBorderGeneral, false);
    }

    public static Pix pixRemoveBorderToSize(Pix pix, int i, int i2) {
        long pixRemoveBorderToSize = JniLeptonicaJNI.pixRemoveBorderToSize(Pix.getCPtr(pix), pix, i, i2);
        if (pixRemoveBorderToSize == 0) {
            return null;
        }
        return new Pix(pixRemoveBorderToSize, false);
    }

    public static Pix pixAddMirroredBorder(Pix pix, int i, int i2, int i3, int i4) {
        long pixAddMirroredBorder = JniLeptonicaJNI.pixAddMirroredBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixAddMirroredBorder == 0) {
            return null;
        }
        return new Pix(pixAddMirroredBorder, false);
    }

    public static Pix pixAddRepeatedBorder(Pix pix, int i, int i2, int i3, int i4) {
        long pixAddRepeatedBorder = JniLeptonicaJNI.pixAddRepeatedBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixAddRepeatedBorder == 0) {
            return null;
        }
        return new Pix(pixAddRepeatedBorder, false);
    }

    public static Pix pixAddMixedBorder(Pix pix, int i, int i2, int i3, int i4) {
        long pixAddMixedBorder = JniLeptonicaJNI.pixAddMixedBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixAddMixedBorder == 0) {
            return null;
        }
        return new Pix(pixAddMixedBorder, false);
    }

    public static Pix pixAddContinuedBorder(Pix pix, int i, int i2, int i3, int i4) {
        long pixAddContinuedBorder = JniLeptonicaJNI.pixAddContinuedBorder(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixAddContinuedBorder == 0) {
            return null;
        }
        return new Pix(pixAddContinuedBorder, false);
    }

    public static int pixShiftAndTransferAlpha(Pix pix, Pix pix2, float f, float f2) {
        return JniLeptonicaJNI.pixShiftAndTransferAlpha(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, f2);
    }

    public static Pix pixDisplayLayersRGBA(Pix pix, long j, int i) {
        long pixDisplayLayersRGBA = JniLeptonicaJNI.pixDisplayLayersRGBA(Pix.getCPtr(pix), pix, j, i);
        if (pixDisplayLayersRGBA == 0) {
            return null;
        }
        return new Pix(pixDisplayLayersRGBA, false);
    }

    public static Pix pixCreateRGBImage(Pix pix, Pix pix2, Pix pix3) {
        long pixCreateRGBImage = JniLeptonicaJNI.pixCreateRGBImage(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixCreateRGBImage == 0) {
            return null;
        }
        return new Pix(pixCreateRGBImage, false);
    }

    public static Pix pixGetRGBComponent(Pix pix, int i) {
        long pixGetRGBComponent = JniLeptonicaJNI.pixGetRGBComponent(Pix.getCPtr(pix), pix, i);
        if (pixGetRGBComponent == 0) {
            return null;
        }
        return new Pix(pixGetRGBComponent, false);
    }

    public static int pixSetRGBComponent(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSetRGBComponent(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static Pix pixGetRGBComponentCmap(Pix pix, int i) {
        long pixGetRGBComponentCmap = JniLeptonicaJNI.pixGetRGBComponentCmap(Pix.getCPtr(pix), pix, i);
        if (pixGetRGBComponentCmap == 0) {
            return null;
        }
        return new Pix(pixGetRGBComponentCmap, false);
    }

    public static int pixCopyRGBComponent(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixCopyRGBComponent(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static int composeRGBPixel(int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.composeRGBPixel(i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int composeRGBAPixel(int i, int i2, int i3, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.composeRGBAPixel(i, i2, i3, i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static void extractRGBValues(long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        JniLeptonicaJNI.extractRGBValues(j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static void extractRGBAValues(long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        JniLeptonicaJNI.extractRGBAValues(j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int extractMinMaxComponent(long j, int i) {
        return JniLeptonicaJNI.extractMinMaxComponent(j, i);
    }

    public static int pixGetRGBLine(Pix pix, int i, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char3) {
        return JniLeptonicaJNI.pixGetRGBLine(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char3));
    }

    public static Pix pixEndianByteSwapNew(Pix pix) {
        long pixEndianByteSwapNew = JniLeptonicaJNI.pixEndianByteSwapNew(Pix.getCPtr(pix), pix);
        if (pixEndianByteSwapNew == 0) {
            return null;
        }
        return new Pix(pixEndianByteSwapNew, false);
    }

    public static int pixEndianByteSwap(Pix pix) {
        return JniLeptonicaJNI.pixEndianByteSwap(Pix.getCPtr(pix), pix);
    }

    public static int lineEndianByteSwap(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i) {
        return JniLeptonicaJNI.lineEndianByteSwap(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i);
    }

    public static Pix pixEndianTwoByteSwapNew(Pix pix) {
        long pixEndianTwoByteSwapNew = JniLeptonicaJNI.pixEndianTwoByteSwapNew(Pix.getCPtr(pix), pix);
        if (pixEndianTwoByteSwapNew == 0) {
            return null;
        }
        return new Pix(pixEndianTwoByteSwapNew, false);
    }

    public static int pixEndianTwoByteSwap(Pix pix) {
        return JniLeptonicaJNI.pixEndianTwoByteSwap(Pix.getCPtr(pix), pix);
    }

    public static int pixGetRasterData(Pix pix, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.pixGetRasterData(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int pixAlphaIsOpaque(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixAlphaIsOpaque(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static SWIGTYPE_p_p_unsigned_char pixSetupByteProcessing(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long pixSetupByteProcessing = JniLeptonicaJNI.pixSetupByteProcessing(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (pixSetupByteProcessing == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_unsigned_char(pixSetupByteProcessing, false);
    }

    public static int pixCleanupByteProcessing(Pix pix, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char) {
        return JniLeptonicaJNI.pixCleanupByteProcessing(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char));
    }

    public static void l_setAlphaMaskBorder(float f, float f2) {
        JniLeptonicaJNI.l_setAlphaMaskBorder(f, f2);
    }

    public static int pixSetMasked(Pix pix, Pix pix2, long j) {
        return JniLeptonicaJNI.pixSetMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j);
    }

    public static int pixSetMaskedGeneral(Pix pix, Pix pix2, long j, int i, int i2) {
        return JniLeptonicaJNI.pixSetMaskedGeneral(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, i, i2);
    }

    public static int pixCombineMasked(Pix pix, Pix pix2, Pix pix3) {
        return JniLeptonicaJNI.pixCombineMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
    }

    public static int pixCombineMaskedGeneral(Pix pix, Pix pix2, Pix pix3, int i, int i2) {
        return JniLeptonicaJNI.pixCombineMaskedGeneral(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2);
    }

    public static int pixPaintThroughMask(Pix pix, Pix pix2, int i, int i2, long j) {
        return JniLeptonicaJNI.pixPaintThroughMask(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, j);
    }

    public static int pixPaintSelfThroughMask(Pix pix, Pix pix2, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixPaintSelfThroughMask(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4);
    }

    public static Pix pixMakeMaskFromLUT(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixMakeMaskFromLUT = JniLeptonicaJNI.pixMakeMaskFromLUT(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixMakeMaskFromLUT == 0) {
            return null;
        }
        return new Pix(pixMakeMaskFromLUT, false);
    }

    public static Pix pixSetUnderTransparency(Pix pix, long j, int i) {
        long pixSetUnderTransparency = JniLeptonicaJNI.pixSetUnderTransparency(Pix.getCPtr(pix), pix, j, i);
        if (pixSetUnderTransparency == 0) {
            return null;
        }
        return new Pix(pixSetUnderTransparency, false);
    }

    public static Pix pixInvert(Pix pix, Pix pix2) {
        long pixInvert = JniLeptonicaJNI.pixInvert(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixInvert == 0) {
            return null;
        }
        return new Pix(pixInvert, false);
    }

    public static Pix pixOr(Pix pix, Pix pix2, Pix pix3) {
        long pixOr = JniLeptonicaJNI.pixOr(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixOr == 0) {
            return null;
        }
        return new Pix(pixOr, false);
    }

    public static Pix pixAnd(Pix pix, Pix pix2, Pix pix3) {
        long pixAnd = JniLeptonicaJNI.pixAnd(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixAnd == 0) {
            return null;
        }
        return new Pix(pixAnd, false);
    }

    public static Pix pixXor(Pix pix, Pix pix2, Pix pix3) {
        long pixXor = JniLeptonicaJNI.pixXor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixXor == 0) {
            return null;
        }
        return new Pix(pixXor, false);
    }

    public static Pix pixSubtract(Pix pix, Pix pix2, Pix pix3) {
        long pixSubtract = JniLeptonicaJNI.pixSubtract(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixSubtract == 0) {
            return null;
        }
        return new Pix(pixSubtract, false);
    }

    public static int pixZero(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixZero(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixForegroundFraction(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixForegroundFraction(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaCountPixels(Pixa pixa) {
        long pixaCountPixels = JniLeptonicaJNI.pixaCountPixels(Pixa.getCPtr(pixa), pixa);
        if (pixaCountPixels == 0) {
            return null;
        }
        return new Numa(pixaCountPixels, false);
    }

    public static int pixCountPixels(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixCountPixels(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Numa pixCountByRow(Pix pix, Box box) {
        long pixCountByRow = JniLeptonicaJNI.pixCountByRow(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixCountByRow == 0) {
            return null;
        }
        return new Numa(pixCountByRow, false);
    }

    public static Numa pixCountByColumn(Pix pix, Box box) {
        long pixCountByColumn = JniLeptonicaJNI.pixCountByColumn(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixCountByColumn == 0) {
            return null;
        }
        return new Numa(pixCountByColumn, false);
    }

    public static Numa pixCountPixelsByRow(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixCountPixelsByRow = JniLeptonicaJNI.pixCountPixelsByRow(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixCountPixelsByRow == 0) {
            return null;
        }
        return new Numa(pixCountPixelsByRow, false);
    }

    public static Numa pixCountPixelsByColumn(Pix pix) {
        long pixCountPixelsByColumn = JniLeptonicaJNI.pixCountPixelsByColumn(Pix.getCPtr(pix), pix);
        if (pixCountPixelsByColumn == 0) {
            return null;
        }
        return new Numa(pixCountPixelsByColumn, false);
    }

    public static int pixCountPixelsInRow(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixCountPixelsInRow(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Numa pixGetMomentByColumn(Pix pix, int i) {
        long pixGetMomentByColumn = JniLeptonicaJNI.pixGetMomentByColumn(Pix.getCPtr(pix), pix, i);
        if (pixGetMomentByColumn == 0) {
            return null;
        }
        return new Numa(pixGetMomentByColumn, false);
    }

    public static int pixThresholdPixelSum(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixThresholdPixelSum(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static SWIGTYPE_p_int makePixelSumTab8() {
        long makePixelSumTab8 = JniLeptonicaJNI.makePixelSumTab8();
        if (makePixelSumTab8 == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(makePixelSumTab8, false);
    }

    public static SWIGTYPE_p_int makePixelCentroidTab8() {
        long makePixelCentroidTab8 = JniLeptonicaJNI.makePixelCentroidTab8();
        if (makePixelCentroidTab8 == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(makePixelCentroidTab8, false);
    }

    public static Numa pixAverageByRow(Pix pix, Box box, int i) {
        long pixAverageByRow = JniLeptonicaJNI.pixAverageByRow(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i);
        if (pixAverageByRow == 0) {
            return null;
        }
        return new Numa(pixAverageByRow, false);
    }

    public static Numa pixAverageByColumn(Pix pix, Box box, int i) {
        long pixAverageByColumn = JniLeptonicaJNI.pixAverageByColumn(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i);
        if (pixAverageByColumn == 0) {
            return null;
        }
        return new Numa(pixAverageByColumn, false);
    }

    public static int pixAverageInRect(Pix pix, Box box, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixAverageInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixVarianceByRow(Pix pix, Box box) {
        long pixVarianceByRow = JniLeptonicaJNI.pixVarianceByRow(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixVarianceByRow == 0) {
            return null;
        }
        return new Numa(pixVarianceByRow, false);
    }

    public static Numa pixVarianceByColumn(Pix pix, Box box) {
        long pixVarianceByColumn = JniLeptonicaJNI.pixVarianceByColumn(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixVarianceByColumn == 0) {
            return null;
        }
        return new Numa(pixVarianceByColumn, false);
    }

    public static int pixVarianceInRect(Pix pix, Box box, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixVarianceInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixAbsDiffByRow(Pix pix, Box box) {
        long pixAbsDiffByRow = JniLeptonicaJNI.pixAbsDiffByRow(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixAbsDiffByRow == 0) {
            return null;
        }
        return new Numa(pixAbsDiffByRow, false);
    }

    public static Numa pixAbsDiffByColumn(Pix pix, Box box) {
        long pixAbsDiffByColumn = JniLeptonicaJNI.pixAbsDiffByColumn(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (pixAbsDiffByColumn == 0) {
            return null;
        }
        return new Numa(pixAbsDiffByColumn, false);
    }

    public static int pixAbsDiffInRect(Pix pix, Box box, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixAbsDiffInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixAbsDiffOnLine(Pix pix, int i, int i2, int i3, int i4, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixAbsDiffOnLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixCountArbInRect(Pix pix, Box box, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixCountArbInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixMirroredTiling(Pix pix, int i, int i2) {
        long pixMirroredTiling = JniLeptonicaJNI.pixMirroredTiling(Pix.getCPtr(pix), pix, i, i2);
        if (pixMirroredTiling == 0) {
            return null;
        }
        return new Pix(pixMirroredTiling, false);
    }

    public static Numa pixGetGrayHistogram(Pix pix, int i) {
        long pixGetGrayHistogram = JniLeptonicaJNI.pixGetGrayHistogram(Pix.getCPtr(pix), pix, i);
        if (pixGetGrayHistogram == 0) {
            return null;
        }
        return new Numa(pixGetGrayHistogram, false);
    }

    public static Numa pixGetGrayHistogramMasked(Pix pix, Pix pix2, int i, int i2, int i3) {
        long pixGetGrayHistogramMasked = JniLeptonicaJNI.pixGetGrayHistogramMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3);
        if (pixGetGrayHistogramMasked == 0) {
            return null;
        }
        return new Numa(pixGetGrayHistogramMasked, false);
    }

    public static Numa pixGetGrayHistogramInRect(Pix pix, Box box, int i) {
        long pixGetGrayHistogramInRect = JniLeptonicaJNI.pixGetGrayHistogramInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i);
        if (pixGetGrayHistogramInRect == 0) {
            return null;
        }
        return new Numa(pixGetGrayHistogramInRect, false);
    }

    public static int pixGetColorHistogram(Pix pix, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3) {
        return JniLeptonicaJNI.pixGetColorHistogram(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3));
    }

    public static int pixGetColorHistogramMasked(Pix pix, Pix pix2, int i, int i2, int i3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3) {
        return JniLeptonicaJNI.pixGetColorHistogramMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3));
    }

    public static Numa pixGetCmapHistogram(Pix pix, int i) {
        long pixGetCmapHistogram = JniLeptonicaJNI.pixGetCmapHistogram(Pix.getCPtr(pix), pix, i);
        if (pixGetCmapHistogram == 0) {
            return null;
        }
        return new Numa(pixGetCmapHistogram, false);
    }

    public static Numa pixGetCmapHistogramMasked(Pix pix, Pix pix2, int i, int i2, int i3) {
        long pixGetCmapHistogramMasked = JniLeptonicaJNI.pixGetCmapHistogramMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3);
        if (pixGetCmapHistogramMasked == 0) {
            return null;
        }
        return new Numa(pixGetCmapHistogramMasked, false);
    }

    public static Numa pixGetCmapHistogramInRect(Pix pix, Box box, int i) {
        long pixGetCmapHistogramInRect = JniLeptonicaJNI.pixGetCmapHistogramInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i);
        if (pixGetCmapHistogramInRect == 0) {
            return null;
        }
        return new Numa(pixGetCmapHistogramInRect, false);
    }

    public static int pixGetRankValue(Pix pix, int i, float f, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixGetRankValue(Pix.getCPtr(pix), pix, i, f, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixGetRankValueMaskedRGB(Pix pix, Pix pix2, int i, int i2, int i3, float f, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.pixGetRankValueMaskedRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int pixGetRankValueMasked(Pix pix, Pix pix2, int i, int i2, int i3, float f, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixGetRankValueMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int pixGetAverageValue(Pix pix, int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int) {
        return JniLeptonicaJNI.pixGetAverageValue(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int));
    }

    public static int pixGetAverageMaskedRGB(Pix pix, Pix pix2, int i, int i2, int i3, int i4, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.pixGetAverageMaskedRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int pixGetAverageMasked(Pix pix, Pix pix2, int i, int i2, int i3, int i4, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixGetAverageMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3, i4, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixGetAverageTiledRGB(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix3) {
        return JniLeptonicaJNI.pixGetAverageTiledRGB(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix3));
    }

    public static Pix pixGetAverageTiled(Pix pix, int i, int i2, int i3) {
        long pixGetAverageTiled = JniLeptonicaJNI.pixGetAverageTiled(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixGetAverageTiled == 0) {
            return null;
        }
        return new Pix(pixGetAverageTiled, false);
    }

    public static int pixRowStats(Pix pix, Box box, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa5, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa6) {
        return JniLeptonicaJNI.pixRowStats(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa5), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa6));
    }

    public static int pixColumnStats(Pix pix, Box box, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa5, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa6) {
        return JniLeptonicaJNI.pixColumnStats(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa5), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa6));
    }

    public static int pixGetComponentRange(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixGetComponentRange(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixGetExtremeValue(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixGetExtremeValue(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int pixGetMaxValueInRect(Pix pix, Box box, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixGetMaxValueInRect(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixGetBinnedComponentRange(Pix pix, int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, String str) {
        return JniLeptonicaJNI.pixGetBinnedComponentRange(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), str);
    }

    public static int pixGetRankColorArray(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, int i4, String str) {
        return JniLeptonicaJNI.pixGetRankColorArray(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), i4, str);
    }

    public static int pixGetBinnedColor(Pix pix, Pix pix2, int i, int i2, Numa numa, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, int i3) {
        return JniLeptonicaJNI.pixGetBinnedColor(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, Numa.getCPtr(numa), numa, SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), i3);
    }

    public static Pix pixDisplayColorArray(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, String str) {
        long pixDisplayColorArray = JniLeptonicaJNI.pixDisplayColorArray(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, str);
        if (pixDisplayColorArray == 0) {
            return null;
        }
        return new Pix(pixDisplayColorArray, false);
    }

    public static Pix pixRankBinByStrip(Pix pix, int i, int i2, int i3, int i4) {
        long pixRankBinByStrip = JniLeptonicaJNI.pixRankBinByStrip(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixRankBinByStrip == 0) {
            return null;
        }
        return new Pix(pixRankBinByStrip, false);
    }

    public static Pix pixaGetAlignedStats(Pixa pixa, int i, int i2, int i3) {
        long pixaGetAlignedStats = JniLeptonicaJNI.pixaGetAlignedStats(Pixa.getCPtr(pixa), pixa, i, i2, i3);
        if (pixaGetAlignedStats == 0) {
            return null;
        }
        return new Pix(pixaGetAlignedStats, false);
    }

    public static int pixaExtractColumnFromEachPix(Pixa pixa, int i, Pix pix) {
        return JniLeptonicaJNI.pixaExtractColumnFromEachPix(Pixa.getCPtr(pixa), pixa, i, Pix.getCPtr(pix), pix);
    }

    public static int pixGetRowStats(Pix pix, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixGetRowStats(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixGetColumnStats(Pix pix, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixGetColumnStats(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixSetPixelColumn(Pix pix, int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixSetPixelColumn(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixThresholdForFgBg(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixThresholdForFgBg(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixSplitDistributionFgBg(Pix pix, float f, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, int i2) {
        return JniLeptonicaJNI.pixSplitDistributionFgBg(Pix.getCPtr(pix), pix, f, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), i2);
    }

    public static int pixaFindDimensions(Pixa pixa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.pixaFindDimensions(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static int pixFindAreaPerimRatio(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindAreaPerimRatio(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaFindPerimToAreaRatio(Pixa pixa) {
        long pixaFindPerimToAreaRatio = JniLeptonicaJNI.pixaFindPerimToAreaRatio(Pixa.getCPtr(pixa), pixa);
        if (pixaFindPerimToAreaRatio == 0) {
            return null;
        }
        return new Numa(pixaFindPerimToAreaRatio, false);
    }

    public static int pixFindPerimToAreaRatio(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindPerimToAreaRatio(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaFindPerimSizeRatio(Pixa pixa) {
        long pixaFindPerimSizeRatio = JniLeptonicaJNI.pixaFindPerimSizeRatio(Pixa.getCPtr(pixa), pixa);
        if (pixaFindPerimSizeRatio == 0) {
            return null;
        }
        return new Numa(pixaFindPerimSizeRatio, false);
    }

    public static int pixFindPerimSizeRatio(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindPerimSizeRatio(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaFindAreaFraction(Pixa pixa) {
        long pixaFindAreaFraction = JniLeptonicaJNI.pixaFindAreaFraction(Pixa.getCPtr(pixa), pixa);
        if (pixaFindAreaFraction == 0) {
            return null;
        }
        return new Numa(pixaFindAreaFraction, false);
    }

    public static int pixFindAreaFraction(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindAreaFraction(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaFindAreaFractionMasked(Pixa pixa, Pix pix, int i) {
        long pixaFindAreaFractionMasked = JniLeptonicaJNI.pixaFindAreaFractionMasked(Pixa.getCPtr(pixa), pixa, Pix.getCPtr(pix), pix, i);
        if (pixaFindAreaFractionMasked == 0) {
            return null;
        }
        return new Numa(pixaFindAreaFractionMasked, false);
    }

    public static int pixFindAreaFractionMasked(Pix pix, Box box, Pix pix2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindAreaFractionMasked(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static Numa pixaFindWidthHeightRatio(Pixa pixa) {
        long pixaFindWidthHeightRatio = JniLeptonicaJNI.pixaFindWidthHeightRatio(Pixa.getCPtr(pixa), pixa);
        if (pixaFindWidthHeightRatio == 0) {
            return null;
        }
        return new Numa(pixaFindWidthHeightRatio, false);
    }

    public static Numa pixaFindWidthHeightProduct(Pixa pixa) {
        long pixaFindWidthHeightProduct = JniLeptonicaJNI.pixaFindWidthHeightProduct(Pixa.getCPtr(pixa), pixa);
        if (pixaFindWidthHeightProduct == 0) {
            return null;
        }
        return new Numa(pixaFindWidthHeightProduct, false);
    }

    public static int pixFindOverlapFraction(Pix pix, Pix pix2, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixFindOverlapFraction(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Boxa pixFindRectangleComps(Pix pix, int i, int i2, int i3) {
        long pixFindRectangleComps = JniLeptonicaJNI.pixFindRectangleComps(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixFindRectangleComps == 0) {
            return null;
        }
        return new Boxa(pixFindRectangleComps, false);
    }

    public static int pixConformsToRectangle(Pix pix, Box box, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixConformsToRectangle(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pixa pixClipRectangles(Pix pix, Boxa boxa) {
        long pixClipRectangles = JniLeptonicaJNI.pixClipRectangles(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa);
        if (pixClipRectangles == 0) {
            return null;
        }
        return new Pixa(pixClipRectangles, false);
    }

    public static Pix pixClipRectangle(Pix pix, Box box, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        long pixClipRectangle = JniLeptonicaJNI.pixClipRectangle(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
        if (pixClipRectangle == 0) {
            return null;
        }
        return new Pix(pixClipRectangle, false);
    }

    public static Pix pixClipMasked(Pix pix, Pix pix2, int i, int i2, long j) {
        long pixClipMasked = JniLeptonicaJNI.pixClipMasked(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, j);
        if (pixClipMasked == 0) {
            return null;
        }
        return new Pix(pixClipMasked, false);
    }

    public static int pixCropToMatch(Pix pix, Pix pix2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixCropToMatch(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static Pix pixCropToSize(Pix pix, int i, int i2) {
        long pixCropToSize = JniLeptonicaJNI.pixCropToSize(Pix.getCPtr(pix), pix, i, i2);
        if (pixCropToSize == 0) {
            return null;
        }
        return new Pix(pixCropToSize, false);
    }

    public static Pix pixResizeToMatch(Pix pix, Pix pix2, int i, int i2) {
        long pixResizeToMatch = JniLeptonicaJNI.pixResizeToMatch(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixResizeToMatch == 0) {
            return null;
        }
        return new Pix(pixResizeToMatch, false);
    }

    public static int pixClipToForeground(Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.pixClipToForeground(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int pixTestClipToForeground(Pix pix, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixTestClipToForeground(Pix.getCPtr(pix), pix, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixClipBoxToForeground(Pix pix, Box box, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.pixClipBoxToForeground(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int pixScanForForeground(Pix pix, Box box, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixScanForForeground(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixClipBoxToEdges(Pix pix, Box box, int i, int i2, int i3, int i4, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.pixClipBoxToEdges(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int pixScanForEdge(Pix pix, Box box, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixScanForEdge(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, i2, i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Numa pixExtractOnLine(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixExtractOnLine = JniLeptonicaJNI.pixExtractOnLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixExtractOnLine == 0) {
            return null;
        }
        return new Numa(pixExtractOnLine, false);
    }

    public static float pixAverageOnLine(Pix pix, int i, int i2, int i3, int i4, int i5) {
        return JniLeptonicaJNI.pixAverageOnLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
    }

    public static Numa pixAverageIntensityProfile(Pix pix, float f, int i, int i2, int i3, int i4, int i5) {
        long pixAverageIntensityProfile = JniLeptonicaJNI.pixAverageIntensityProfile(Pix.getCPtr(pix), pix, f, i, i2, i3, i4, i5);
        if (pixAverageIntensityProfile == 0) {
            return null;
        }
        return new Numa(pixAverageIntensityProfile, false);
    }

    public static Numa pixReversalProfile(Pix pix, float f, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixReversalProfile = JniLeptonicaJNI.pixReversalProfile(Pix.getCPtr(pix), pix, f, i, i2, i3, i4, i5, i6);
        if (pixReversalProfile == 0) {
            return null;
        }
        return new Numa(pixReversalProfile, false);
    }

    public static int pixWindowedVarianceOnLine(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixWindowedVarianceOnLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int pixMinMaxNearLine(Pix pix, int i, int i2, int i3, int i4, int i5, int i6, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixMinMaxNearLine(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static Pix pixRankRowTransform(Pix pix) {
        long pixRankRowTransform = JniLeptonicaJNI.pixRankRowTransform(Pix.getCPtr(pix), pix);
        if (pixRankRowTransform == 0) {
            return null;
        }
        return new Pix(pixRankRowTransform, false);
    }

    public static Pix pixRankColumnTransform(Pix pix) {
        long pixRankColumnTransform = JniLeptonicaJNI.pixRankColumnTransform(Pix.getCPtr(pix), pix);
        if (pixRankColumnTransform == 0) {
            return null;
        }
        return new Pix(pixRankColumnTransform, false);
    }

    public static Pixa pixaCreate(int i) {
        long pixaCreate = JniLeptonicaJNI.pixaCreate(i);
        if (pixaCreate == 0) {
            return null;
        }
        return new Pixa(pixaCreate, false);
    }

    public static Pixa pixaCreateFromPix(Pix pix, int i, int i2, int i3) {
        long pixaCreateFromPix = JniLeptonicaJNI.pixaCreateFromPix(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixaCreateFromPix == 0) {
            return null;
        }
        return new Pixa(pixaCreateFromPix, false);
    }

    public static Pixa pixaCreateFromBoxa(Pix pix, Boxa boxa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaCreateFromBoxa = JniLeptonicaJNI.pixaCreateFromBoxa(Pix.getCPtr(pix), pix, Boxa.getCPtr(boxa), boxa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaCreateFromBoxa == 0) {
            return null;
        }
        return new Pixa(pixaCreateFromBoxa, false);
    }

    public static Pixa pixaSplitPix(Pix pix, int i, int i2, int i3, long j) {
        long pixaSplitPix = JniLeptonicaJNI.pixaSplitPix(Pix.getCPtr(pix), pix, i, i2, i3, j);
        if (pixaSplitPix == 0) {
            return null;
        }
        return new Pixa(pixaSplitPix, false);
    }

    public static void pixaDestroy(SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        JniLeptonicaJNI.pixaDestroy(SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static Pixa pixaCopy(Pixa pixa, int i) {
        long pixaCopy = JniLeptonicaJNI.pixaCopy(Pixa.getCPtr(pixa), pixa, i);
        if (pixaCopy == 0) {
            return null;
        }
        return new Pixa(pixaCopy, false);
    }

    public static int pixaAddPix(Pixa pixa, Pix pix, int i) {
        return JniLeptonicaJNI.pixaAddPix(Pixa.getCPtr(pixa), pixa, Pix.getCPtr(pix), pix, i);
    }

    public static int pixaAddBox(Pixa pixa, Box box, int i) {
        return JniLeptonicaJNI.pixaAddBox(Pixa.getCPtr(pixa), pixa, Box.getCPtr(box), box, i);
    }

    public static int pixaExtendArrayToSize(Pixa pixa, int i) {
        return JniLeptonicaJNI.pixaExtendArrayToSize(Pixa.getCPtr(pixa), pixa, i);
    }

    public static int pixaGetCount(Pixa pixa) {
        return JniLeptonicaJNI.pixaGetCount(Pixa.getCPtr(pixa), pixa);
    }

    public static int pixaChangeRefcount(Pixa pixa, int i) {
        return JniLeptonicaJNI.pixaChangeRefcount(Pixa.getCPtr(pixa), pixa, i);
    }

    public static Pix pixaGetPix(Pixa pixa, int i, int i2) {
        long pixaGetPix = JniLeptonicaJNI.pixaGetPix(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixaGetPix == 0) {
            return null;
        }
        return new Pix(pixaGetPix, false);
    }

    public static int pixaGetPixDimensions(Pixa pixa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixaGetPixDimensions(Pixa.getCPtr(pixa), pixa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static Boxa pixaGetBoxa(Pixa pixa, int i) {
        long pixaGetBoxa = JniLeptonicaJNI.pixaGetBoxa(Pixa.getCPtr(pixa), pixa, i);
        if (pixaGetBoxa == 0) {
            return null;
        }
        return new Boxa(pixaGetBoxa, false);
    }

    public static int pixaGetBoxaCount(Pixa pixa) {
        return JniLeptonicaJNI.pixaGetBoxaCount(Pixa.getCPtr(pixa), pixa);
    }

    public static Box pixaGetBox(Pixa pixa, int i, int i2) {
        long pixaGetBox = JniLeptonicaJNI.pixaGetBox(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixaGetBox == 0) {
            return null;
        }
        return new Box(pixaGetBox, false);
    }

    public static int pixaGetBoxGeometry(Pixa pixa, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixaGetBoxGeometry(Pixa.getCPtr(pixa), pixa, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int pixaSetBoxa(Pixa pixa, Boxa boxa, int i) {
        return JniLeptonicaJNI.pixaSetBoxa(Pixa.getCPtr(pixa), pixa, Boxa.getCPtr(boxa), boxa, i);
    }

    public static SWIGTYPE_p_p_Pix pixaGetPixArray(Pixa pixa) {
        long pixaGetPixArray = JniLeptonicaJNI.pixaGetPixArray(Pixa.getCPtr(pixa), pixa);
        if (pixaGetPixArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_Pix(pixaGetPixArray, false);
    }

    public static int pixaVerifyDepth(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaVerifyDepth(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaIsFull(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixaIsFull(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixaCountText(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaCountText(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static SWIGTYPE_p_p_p_void pixaGetLinePtrs(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaGetLinePtrs = JniLeptonicaJNI.pixaGetLinePtrs(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaGetLinePtrs == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_p_void(pixaGetLinePtrs, false);
    }

    public static int pixaReplacePix(Pixa pixa, int i, Pix pix, Box box) {
        return JniLeptonicaJNI.pixaReplacePix(Pixa.getCPtr(pixa), pixa, i, Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixaInsertPix(Pixa pixa, int i, Pix pix, Box box) {
        return JniLeptonicaJNI.pixaInsertPix(Pixa.getCPtr(pixa), pixa, i, Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixaRemovePix(Pixa pixa, int i) {
        return JniLeptonicaJNI.pixaRemovePix(Pixa.getCPtr(pixa), pixa, i);
    }

    public static int pixaRemovePixAndSave(Pixa pixa, int i, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box) {
        return JniLeptonicaJNI.pixaRemovePixAndSave(Pixa.getCPtr(pixa), pixa, i, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box));
    }

    public static int pixaInitFull(Pixa pixa, Pix pix, Box box) {
        return JniLeptonicaJNI.pixaInitFull(Pixa.getCPtr(pixa), pixa, Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
    }

    public static int pixaClear(Pixa pixa) {
        return JniLeptonicaJNI.pixaClear(Pixa.getCPtr(pixa), pixa);
    }

    public static int pixaJoin(Pixa pixa, Pixa pixa2, int i, int i2) {
        return JniLeptonicaJNI.pixaJoin(Pixa.getCPtr(pixa), pixa, Pixa.getCPtr(pixa2), pixa2, i, i2);
    }

    public static int pixaaJoin(Pixaa pixaa, Pixaa pixaa2, int i, int i2) {
        return JniLeptonicaJNI.pixaaJoin(Pixaa.getCPtr(pixaa), pixaa, Pixaa.getCPtr(pixaa2), pixaa2, i, i2);
    }

    public static Pixaa pixaaCreate(int i) {
        long pixaaCreate = JniLeptonicaJNI.pixaaCreate(i);
        if (pixaaCreate == 0) {
            return null;
        }
        return new Pixaa(pixaaCreate, false);
    }

    public static Pixaa pixaaCreateFromPixa(Pixa pixa, int i, int i2, int i3) {
        long pixaaCreateFromPixa = JniLeptonicaJNI.pixaaCreateFromPixa(Pixa.getCPtr(pixa), pixa, i, i2, i3);
        if (pixaaCreateFromPixa == 0) {
            return null;
        }
        return new Pixaa(pixaaCreateFromPixa, false);
    }

    public static void pixaaDestroy(SWIGTYPE_p_p_Pixaa sWIGTYPE_p_p_Pixaa) {
        JniLeptonicaJNI.pixaaDestroy(SWIGTYPE_p_p_Pixaa.getCPtr(sWIGTYPE_p_p_Pixaa));
    }

    public static int pixaaAddPixa(Pixaa pixaa, Pixa pixa, int i) {
        return JniLeptonicaJNI.pixaaAddPixa(Pixaa.getCPtr(pixaa), pixaa, Pixa.getCPtr(pixa), pixa, i);
    }

    public static int pixaaExtendArray(Pixaa pixaa) {
        return JniLeptonicaJNI.pixaaExtendArray(Pixaa.getCPtr(pixaa), pixaa);
    }

    public static int pixaaAddPix(Pixaa pixaa, int i, Pix pix, Box box, int i2) {
        return JniLeptonicaJNI.pixaaAddPix(Pixaa.getCPtr(pixaa), pixaa, i, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i2);
    }

    public static int pixaaAddBox(Pixaa pixaa, Box box, int i) {
        return JniLeptonicaJNI.pixaaAddBox(Pixaa.getCPtr(pixaa), pixaa, Box.getCPtr(box), box, i);
    }

    public static int pixaaGetCount(Pixaa pixaa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixaaGetCount(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Pixa pixaaGetPixa(Pixaa pixaa, int i, int i2) {
        long pixaaGetPixa = JniLeptonicaJNI.pixaaGetPixa(Pixaa.getCPtr(pixaa), pixaa, i, i2);
        if (pixaaGetPixa == 0) {
            return null;
        }
        return new Pixa(pixaaGetPixa, false);
    }

    public static Boxa pixaaGetBoxa(Pixaa pixaa, int i) {
        long pixaaGetBoxa = JniLeptonicaJNI.pixaaGetBoxa(Pixaa.getCPtr(pixaa), pixaa, i);
        if (pixaaGetBoxa == 0) {
            return null;
        }
        return new Boxa(pixaaGetBoxa, false);
    }

    public static Pix pixaaGetPix(Pixaa pixaa, int i, int i2, int i3) {
        long pixaaGetPix = JniLeptonicaJNI.pixaaGetPix(Pixaa.getCPtr(pixaa), pixaa, i, i2, i3);
        if (pixaaGetPix == 0) {
            return null;
        }
        return new Pix(pixaaGetPix, false);
    }

    public static int pixaaVerifyDepth(Pixaa pixaa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaaVerifyDepth(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaaIsFull(Pixaa pixaa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaaIsFull(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaaInitFull(Pixaa pixaa, Pixa pixa) {
        return JniLeptonicaJNI.pixaaInitFull(Pixaa.getCPtr(pixaa), pixaa, Pixa.getCPtr(pixa), pixa);
    }

    public static int pixaaReplacePixa(Pixaa pixaa, int i, Pixa pixa) {
        return JniLeptonicaJNI.pixaaReplacePixa(Pixaa.getCPtr(pixaa), pixaa, i, Pixa.getCPtr(pixa), pixa);
    }

    public static int pixaaClear(Pixaa pixaa) {
        return JniLeptonicaJNI.pixaaClear(Pixaa.getCPtr(pixaa), pixaa);
    }

    public static int pixaaTruncate(Pixaa pixaa) {
        return JniLeptonicaJNI.pixaaTruncate(Pixaa.getCPtr(pixaa), pixaa);
    }

    public static Pixa pixaRead(String str) {
        long pixaRead = JniLeptonicaJNI.pixaRead(str);
        if (pixaRead == 0) {
            return null;
        }
        return new Pixa(pixaRead, false);
    }

    public static Pixa pixaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixaReadStream = JniLeptonicaJNI.pixaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixaReadStream == 0) {
            return null;
        }
        return new Pixa(pixaReadStream, false);
    }

    public static int pixaWrite(String str, Pixa pixa) {
        return JniLeptonicaJNI.pixaWrite(str, Pixa.getCPtr(pixa), pixa);
    }

    public static int pixaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pixa pixa) {
        return JniLeptonicaJNI.pixaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pixa.getCPtr(pixa), pixa);
    }

    public static Pixaa pixaaReadFromFiles(String str, String str2, int i, int i2) {
        long pixaaReadFromFiles = JniLeptonicaJNI.pixaaReadFromFiles(str, str2, i, i2);
        if (pixaaReadFromFiles == 0) {
            return null;
        }
        return new Pixaa(pixaaReadFromFiles, false);
    }

    public static Pixaa pixaaRead(String str) {
        long pixaaRead = JniLeptonicaJNI.pixaaRead(str);
        if (pixaaRead == 0) {
            return null;
        }
        return new Pixaa(pixaaRead, false);
    }

    public static Pixaa pixaaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixaaReadStream = JniLeptonicaJNI.pixaaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixaaReadStream == 0) {
            return null;
        }
        return new Pixaa(pixaaReadStream, false);
    }

    public static int pixaaWrite(String str, Pixaa pixaa) {
        return JniLeptonicaJNI.pixaaWrite(str, Pixaa.getCPtr(pixaa), pixaa);
    }

    public static int pixaaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pixaa pixaa) {
        return JniLeptonicaJNI.pixaaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pixaa.getCPtr(pixaa), pixaa);
    }

    public static Pixacc pixaccCreate(int i, int i2, int i3) {
        long pixaccCreate = JniLeptonicaJNI.pixaccCreate(i, i2, i3);
        if (pixaccCreate == 0) {
            return null;
        }
        return new Pixacc(pixaccCreate, false);
    }

    public static Pixacc pixaccCreateFromPix(Pix pix, int i) {
        long pixaccCreateFromPix = JniLeptonicaJNI.pixaccCreateFromPix(Pix.getCPtr(pix), pix, i);
        if (pixaccCreateFromPix == 0) {
            return null;
        }
        return new Pixacc(pixaccCreateFromPix, false);
    }

    public static void pixaccDestroy(SWIGTYPE_p_p_Pixacc sWIGTYPE_p_p_Pixacc) {
        JniLeptonicaJNI.pixaccDestroy(SWIGTYPE_p_p_Pixacc.getCPtr(sWIGTYPE_p_p_Pixacc));
    }

    public static Pix pixaccFinal(Pixacc pixacc, int i) {
        long pixaccFinal = JniLeptonicaJNI.pixaccFinal(Pixacc.getCPtr(pixacc), pixacc, i);
        if (pixaccFinal == 0) {
            return null;
        }
        return new Pix(pixaccFinal, false);
    }

    public static Pix pixaccGetPix(Pixacc pixacc) {
        long pixaccGetPix = JniLeptonicaJNI.pixaccGetPix(Pixacc.getCPtr(pixacc), pixacc);
        if (pixaccGetPix == 0) {
            return null;
        }
        return new Pix(pixaccGetPix, false);
    }

    public static int pixaccGetOffset(Pixacc pixacc) {
        return JniLeptonicaJNI.pixaccGetOffset(Pixacc.getCPtr(pixacc), pixacc);
    }

    public static int pixaccAdd(Pixacc pixacc, Pix pix) {
        return JniLeptonicaJNI.pixaccAdd(Pixacc.getCPtr(pixacc), pixacc, Pix.getCPtr(pix), pix);
    }

    public static int pixaccSubtract(Pixacc pixacc, Pix pix) {
        return JniLeptonicaJNI.pixaccSubtract(Pixacc.getCPtr(pixacc), pixacc, Pix.getCPtr(pix), pix);
    }

    public static int pixaccMultConst(Pixacc pixacc, float f) {
        return JniLeptonicaJNI.pixaccMultConst(Pixacc.getCPtr(pixacc), pixacc, f);
    }

    public static int pixaccMultConstAccumulate(Pixacc pixacc, Pix pix, float f) {
        return JniLeptonicaJNI.pixaccMultConstAccumulate(Pixacc.getCPtr(pixacc), pixacc, Pix.getCPtr(pix), pix, f);
    }

    public static Pix pixSelectBySize(Pix pix, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixSelectBySize = JniLeptonicaJNI.pixSelectBySize(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixSelectBySize == 0) {
            return null;
        }
        return new Pix(pixSelectBySize, false);
    }

    public static Pixa pixaSelectBySize(Pixa pixa, int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectBySize = JniLeptonicaJNI.pixaSelectBySize(Pixa.getCPtr(pixa), pixa, i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectBySize == 0) {
            return null;
        }
        return new Pixa(pixaSelectBySize, false);
    }

    public static Numa pixaMakeSizeIndicator(Pixa pixa, int i, int i2, int i3, int i4) {
        long pixaMakeSizeIndicator = JniLeptonicaJNI.pixaMakeSizeIndicator(Pixa.getCPtr(pixa), pixa, i, i2, i3, i4);
        if (pixaMakeSizeIndicator == 0) {
            return null;
        }
        return new Numa(pixaMakeSizeIndicator, false);
    }

    public static Pix pixSelectByPerimToAreaRatio(Pix pix, float f, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixSelectByPerimToAreaRatio = JniLeptonicaJNI.pixSelectByPerimToAreaRatio(Pix.getCPtr(pix), pix, f, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixSelectByPerimToAreaRatio == 0) {
            return null;
        }
        return new Pix(pixSelectByPerimToAreaRatio, false);
    }

    public static Pixa pixaSelectByPerimToAreaRatio(Pixa pixa, float f, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectByPerimToAreaRatio = JniLeptonicaJNI.pixaSelectByPerimToAreaRatio(Pixa.getCPtr(pixa), pixa, f, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectByPerimToAreaRatio == 0) {
            return null;
        }
        return new Pixa(pixaSelectByPerimToAreaRatio, false);
    }

    public static Pix pixSelectByPerimSizeRatio(Pix pix, float f, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixSelectByPerimSizeRatio = JniLeptonicaJNI.pixSelectByPerimSizeRatio(Pix.getCPtr(pix), pix, f, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixSelectByPerimSizeRatio == 0) {
            return null;
        }
        return new Pix(pixSelectByPerimSizeRatio, false);
    }

    public static Pixa pixaSelectByPerimSizeRatio(Pixa pixa, float f, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectByPerimSizeRatio = JniLeptonicaJNI.pixaSelectByPerimSizeRatio(Pixa.getCPtr(pixa), pixa, f, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectByPerimSizeRatio == 0) {
            return null;
        }
        return new Pixa(pixaSelectByPerimSizeRatio, false);
    }

    public static Pix pixSelectByAreaFraction(Pix pix, float f, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixSelectByAreaFraction = JniLeptonicaJNI.pixSelectByAreaFraction(Pix.getCPtr(pix), pix, f, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixSelectByAreaFraction == 0) {
            return null;
        }
        return new Pix(pixSelectByAreaFraction, false);
    }

    public static Pixa pixaSelectByAreaFraction(Pixa pixa, float f, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectByAreaFraction = JniLeptonicaJNI.pixaSelectByAreaFraction(Pixa.getCPtr(pixa), pixa, f, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectByAreaFraction == 0) {
            return null;
        }
        return new Pixa(pixaSelectByAreaFraction, false);
    }

    public static Pix pixSelectByWidthHeightRatio(Pix pix, float f, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixSelectByWidthHeightRatio = JniLeptonicaJNI.pixSelectByWidthHeightRatio(Pix.getCPtr(pix), pix, f, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixSelectByWidthHeightRatio == 0) {
            return null;
        }
        return new Pix(pixSelectByWidthHeightRatio, false);
    }

    public static Pixa pixaSelectByWidthHeightRatio(Pixa pixa, float f, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectByWidthHeightRatio = JniLeptonicaJNI.pixaSelectByWidthHeightRatio(Pixa.getCPtr(pixa), pixa, f, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectByWidthHeightRatio == 0) {
            return null;
        }
        return new Pixa(pixaSelectByWidthHeightRatio, false);
    }

    public static Pixa pixaSelectWithIndicator(Pixa pixa, Numa numa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixaSelectWithIndicator = JniLeptonicaJNI.pixaSelectWithIndicator(Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixaSelectWithIndicator == 0) {
            return null;
        }
        return new Pixa(pixaSelectWithIndicator, false);
    }

    public static int pixRemoveWithIndicator(Pix pix, Pixa pixa, Numa numa) {
        return JniLeptonicaJNI.pixRemoveWithIndicator(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa);
    }

    public static int pixAddWithIndicator(Pix pix, Pixa pixa, Numa numa) {
        return JniLeptonicaJNI.pixAddWithIndicator(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa);
    }

    public static Pix pixaRenderComponent(Pix pix, Pixa pixa, int i) {
        long pixaRenderComponent = JniLeptonicaJNI.pixaRenderComponent(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, i);
        if (pixaRenderComponent == 0) {
            return null;
        }
        return new Pix(pixaRenderComponent, false);
    }

    public static Pixa pixaSort(Pixa pixa, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i3) {
        long pixaSort = JniLeptonicaJNI.pixaSort(Pixa.getCPtr(pixa), pixa, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i3);
        if (pixaSort == 0) {
            return null;
        }
        return new Pixa(pixaSort, false);
    }

    public static Pixa pixaBinSort(Pixa pixa, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i3) {
        long pixaBinSort = JniLeptonicaJNI.pixaBinSort(Pixa.getCPtr(pixa), pixa, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i3);
        if (pixaBinSort == 0) {
            return null;
        }
        return new Pixa(pixaBinSort, false);
    }

    public static Pixa pixaSortByIndex(Pixa pixa, Numa numa, int i) {
        long pixaSortByIndex = JniLeptonicaJNI.pixaSortByIndex(Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa, i);
        if (pixaSortByIndex == 0) {
            return null;
        }
        return new Pixa(pixaSortByIndex, false);
    }

    public static Pixaa pixaSort2dByIndex(Pixa pixa, Numaa numaa, int i) {
        long pixaSort2dByIndex = JniLeptonicaJNI.pixaSort2dByIndex(Pixa.getCPtr(pixa), pixa, Numaa.getCPtr(numaa), numaa, i);
        if (pixaSort2dByIndex == 0) {
            return null;
        }
        return new Pixaa(pixaSort2dByIndex, false);
    }

    public static Pixa pixaSelectRange(Pixa pixa, int i, int i2, int i3) {
        long pixaSelectRange = JniLeptonicaJNI.pixaSelectRange(Pixa.getCPtr(pixa), pixa, i, i2, i3);
        if (pixaSelectRange == 0) {
            return null;
        }
        return new Pixa(pixaSelectRange, false);
    }

    public static Pixaa pixaaSelectRange(Pixaa pixaa, int i, int i2, int i3) {
        long pixaaSelectRange = JniLeptonicaJNI.pixaaSelectRange(Pixaa.getCPtr(pixaa), pixaa, i, i2, i3);
        if (pixaaSelectRange == 0) {
            return null;
        }
        return new Pixaa(pixaaSelectRange, false);
    }

    public static Pixaa pixaaScaleToSize(Pixaa pixaa, int i, int i2) {
        long pixaaScaleToSize = JniLeptonicaJNI.pixaaScaleToSize(Pixaa.getCPtr(pixaa), pixaa, i, i2);
        if (pixaaScaleToSize == 0) {
            return null;
        }
        return new Pixaa(pixaaScaleToSize, false);
    }

    public static Pixaa pixaaScaleToSizeVar(Pixaa pixaa, Numa numa, Numa numa2) {
        long pixaaScaleToSizeVar = JniLeptonicaJNI.pixaaScaleToSizeVar(Pixaa.getCPtr(pixaa), pixaa, Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (pixaaScaleToSizeVar == 0) {
            return null;
        }
        return new Pixaa(pixaaScaleToSizeVar, false);
    }

    public static Pixa pixaScaleToSize(Pixa pixa, int i, int i2) {
        long pixaScaleToSize = JniLeptonicaJNI.pixaScaleToSize(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixaScaleToSize == 0) {
            return null;
        }
        return new Pixa(pixaScaleToSize, false);
    }

    public static Pixa pixaAddBorderGeneral(Pixa pixa, Pixa pixa2, int i, int i2, int i3, int i4, long j) {
        long pixaAddBorderGeneral = JniLeptonicaJNI.pixaAddBorderGeneral(Pixa.getCPtr(pixa), pixa, Pixa.getCPtr(pixa2), pixa2, i, i2, i3, i4, j);
        if (pixaAddBorderGeneral == 0) {
            return null;
        }
        return new Pixa(pixaAddBorderGeneral, false);
    }

    public static Pixa pixaaFlattenToPixa(Pixaa pixaa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i) {
        long pixaaFlattenToPixa = JniLeptonicaJNI.pixaaFlattenToPixa(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i);
        if (pixaaFlattenToPixa == 0) {
            return null;
        }
        return new Pixa(pixaaFlattenToPixa, false);
    }

    public static int pixaaSizeRange(Pixaa pixaa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixaaSizeRange(Pixaa.getCPtr(pixaa), pixaa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int pixaSizeRange(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixaSizeRange(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static Pixa pixaClipToPix(Pixa pixa, Pix pix) {
        long pixaClipToPix = JniLeptonicaJNI.pixaClipToPix(Pixa.getCPtr(pixa), pixa, Pix.getCPtr(pix), pix);
        if (pixaClipToPix == 0) {
            return null;
        }
        return new Pixa(pixaClipToPix, false);
    }

    public static int pixaGetRenderingDepth(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaGetRenderingDepth(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaHasColor(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaHasColor(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaAnyColormaps(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaAnyColormaps(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixaGetDepthInfo(Pixa pixa, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixaGetDepthInfo(Pixa.getCPtr(pixa), pixa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pixa pixaConvertToSameDepth(Pixa pixa) {
        long pixaConvertToSameDepth = JniLeptonicaJNI.pixaConvertToSameDepth(Pixa.getCPtr(pixa), pixa);
        if (pixaConvertToSameDepth == 0) {
            return null;
        }
        return new Pixa(pixaConvertToSameDepth, false);
    }

    public static int pixaEqual(Pixa pixa, Pixa pixa2, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixaEqual(Pixa.getCPtr(pixa), pixa, Pixa.getCPtr(pixa2), pixa2, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixaDisplay(Pixa pixa, int i, int i2) {
        long pixaDisplay = JniLeptonicaJNI.pixaDisplay(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixaDisplay == 0) {
            return null;
        }
        return new Pix(pixaDisplay, false);
    }

    public static Pix pixaDisplayOnColor(Pixa pixa, int i, int i2, long j) {
        long pixaDisplayOnColor = JniLeptonicaJNI.pixaDisplayOnColor(Pixa.getCPtr(pixa), pixa, i, i2, j);
        if (pixaDisplayOnColor == 0) {
            return null;
        }
        return new Pix(pixaDisplayOnColor, false);
    }

    public static Pix pixaDisplayRandomCmap(Pixa pixa, int i, int i2) {
        long pixaDisplayRandomCmap = JniLeptonicaJNI.pixaDisplayRandomCmap(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixaDisplayRandomCmap == 0) {
            return null;
        }
        return new Pix(pixaDisplayRandomCmap, false);
    }

    public static Pix pixaDisplayLinearly(Pixa pixa, int i, float f, int i2, int i3, int i4, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        long pixaDisplayLinearly = JniLeptonicaJNI.pixaDisplayLinearly(Pixa.getCPtr(pixa), pixa, i, f, i2, i3, i4, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
        if (pixaDisplayLinearly == 0) {
            return null;
        }
        return new Pix(pixaDisplayLinearly, false);
    }

    public static Pix pixaDisplayOnLattice(Pixa pixa, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa) {
        long pixaDisplayOnLattice = JniLeptonicaJNI.pixaDisplayOnLattice(Pixa.getCPtr(pixa), pixa, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa));
        if (pixaDisplayOnLattice == 0) {
            return null;
        }
        return new Pix(pixaDisplayOnLattice, false);
    }

    public static Pix pixaDisplayUnsplit(Pixa pixa, int i, int i2, int i3, long j) {
        long pixaDisplayUnsplit = JniLeptonicaJNI.pixaDisplayUnsplit(Pixa.getCPtr(pixa), pixa, i, i2, i3, j);
        if (pixaDisplayUnsplit == 0) {
            return null;
        }
        return new Pix(pixaDisplayUnsplit, false);
    }

    public static Pix pixaDisplayTiled(Pixa pixa, int i, int i2, int i3) {
        long pixaDisplayTiled = JniLeptonicaJNI.pixaDisplayTiled(Pixa.getCPtr(pixa), pixa, i, i2, i3);
        if (pixaDisplayTiled == 0) {
            return null;
        }
        return new Pix(pixaDisplayTiled, false);
    }

    public static Pix pixaDisplayTiledInRows(Pixa pixa, int i, int i2, float f, int i3, int i4, int i5) {
        long pixaDisplayTiledInRows = JniLeptonicaJNI.pixaDisplayTiledInRows(Pixa.getCPtr(pixa), pixa, i, i2, f, i3, i4, i5);
        if (pixaDisplayTiledInRows == 0) {
            return null;
        }
        return new Pix(pixaDisplayTiledInRows, false);
    }

    public static Pix pixaDisplayTiledAndScaled(Pixa pixa, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixaDisplayTiledAndScaled = JniLeptonicaJNI.pixaDisplayTiledAndScaled(Pixa.getCPtr(pixa), pixa, i, i2, i3, i4, i5, i6);
        if (pixaDisplayTiledAndScaled == 0) {
            return null;
        }
        return new Pix(pixaDisplayTiledAndScaled, false);
    }

    public static Pix pixaaDisplay(Pixaa pixaa, int i, int i2) {
        long pixaaDisplay = JniLeptonicaJNI.pixaaDisplay(Pixaa.getCPtr(pixaa), pixaa, i, i2);
        if (pixaaDisplay == 0) {
            return null;
        }
        return new Pix(pixaaDisplay, false);
    }

    public static Pix pixaaDisplayByPixa(Pixaa pixaa, int i, int i2, int i3) {
        long pixaaDisplayByPixa = JniLeptonicaJNI.pixaaDisplayByPixa(Pixaa.getCPtr(pixaa), pixaa, i, i2, i3);
        if (pixaaDisplayByPixa == 0) {
            return null;
        }
        return new Pix(pixaaDisplayByPixa, false);
    }

    public static Pixa pixaaDisplayTiledAndScaled(Pixaa pixaa, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixaaDisplayTiledAndScaled = JniLeptonicaJNI.pixaaDisplayTiledAndScaled(Pixaa.getCPtr(pixaa), pixaa, i, i2, i3, i4, i5, i6);
        if (pixaaDisplayTiledAndScaled == 0) {
            return null;
        }
        return new Pixa(pixaaDisplayTiledAndScaled, false);
    }

    public static Pixa pixaConvertTo1(Pixa pixa, int i) {
        long pixaConvertTo1 = JniLeptonicaJNI.pixaConvertTo1(Pixa.getCPtr(pixa), pixa, i);
        if (pixaConvertTo1 == 0) {
            return null;
        }
        return new Pixa(pixaConvertTo1, false);
    }

    public static Pixa pixaConvertTo8(Pixa pixa, int i) {
        long pixaConvertTo8 = JniLeptonicaJNI.pixaConvertTo8(Pixa.getCPtr(pixa), pixa, i);
        if (pixaConvertTo8 == 0) {
            return null;
        }
        return new Pixa(pixaConvertTo8, false);
    }

    public static Pixa pixaConvertTo8Color(Pixa pixa, int i) {
        long pixaConvertTo8Color = JniLeptonicaJNI.pixaConvertTo8Color(Pixa.getCPtr(pixa), pixa, i);
        if (pixaConvertTo8Color == 0) {
            return null;
        }
        return new Pixa(pixaConvertTo8Color, false);
    }

    public static Pixa pixaConvertTo32(Pixa pixa) {
        long pixaConvertTo32 = JniLeptonicaJNI.pixaConvertTo32(Pixa.getCPtr(pixa), pixa);
        if (pixaConvertTo32 == 0) {
            return null;
        }
        return new Pixa(pixaConvertTo32, false);
    }

    public static int convertToNUpFiles(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3, String str4) {
        return JniLeptonicaJNI.convertToNUpFiles(str, str2, i, i2, i3, i4, i5, str3, str4);
    }

    public static Pixa convertToNUpPixa(String str, String str2, int i, int i2, int i3, int i4, int i5, String str3) {
        long convertToNUpPixa = JniLeptonicaJNI.convertToNUpPixa(str, str2, i, i2, i3, i4, i5, str3);
        if (convertToNUpPixa == 0) {
            return null;
        }
        return new Pixa(convertToNUpPixa, false);
    }

    public static int pmsCreate(long j, long j2, Numa numa, String str) {
        return JniLeptonicaJNI.pmsCreate(j, j2, Numa.getCPtr(numa), numa, str);
    }

    public static void pmsDestroy() {
        JniLeptonicaJNI.pmsDestroy();
    }

    public static SWIGTYPE_p_void pmsCustomAlloc(long j) {
        long pmsCustomAlloc = JniLeptonicaJNI.pmsCustomAlloc(j);
        if (pmsCustomAlloc == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(pmsCustomAlloc, false);
    }

    public static void pmsCustomDealloc(SWIGTYPE_p_void sWIGTYPE_p_void) {
        JniLeptonicaJNI.pmsCustomDealloc(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static SWIGTYPE_p_void pmsGetAlloc(long j) {
        long pmsGetAlloc = JniLeptonicaJNI.pmsGetAlloc(j);
        if (pmsGetAlloc == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(pmsGetAlloc, false);
    }

    public static int pmsGetLevelForAlloc(long j, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pmsGetLevelForAlloc(j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pmsGetLevelForDealloc(SWIGTYPE_p_void sWIGTYPE_p_void, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pmsGetLevelForDealloc(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static void pmsLogInfo() {
        JniLeptonicaJNI.pmsLogInfo();
    }

    public static int pixAddConstantGray(Pix pix, int i) {
        return JniLeptonicaJNI.pixAddConstantGray(Pix.getCPtr(pix), pix, i);
    }

    public static int pixMultConstantGray(Pix pix, float f) {
        return JniLeptonicaJNI.pixMultConstantGray(Pix.getCPtr(pix), pix, f);
    }

    public static Pix pixAddGray(Pix pix, Pix pix2, Pix pix3) {
        long pixAddGray = JniLeptonicaJNI.pixAddGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixAddGray == 0) {
            return null;
        }
        return new Pix(pixAddGray, false);
    }

    public static Pix pixSubtractGray(Pix pix, Pix pix2, Pix pix3) {
        long pixSubtractGray = JniLeptonicaJNI.pixSubtractGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3);
        if (pixSubtractGray == 0) {
            return null;
        }
        return new Pix(pixSubtractGray, false);
    }

    public static Pix pixThresholdToValue(Pix pix, Pix pix2, int i, int i2) {
        long pixThresholdToValue = JniLeptonicaJNI.pixThresholdToValue(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixThresholdToValue == 0) {
            return null;
        }
        return new Pix(pixThresholdToValue, false);
    }

    public static Pix pixInitAccumulate(int i, int i2, long j) {
        long pixInitAccumulate = JniLeptonicaJNI.pixInitAccumulate(i, i2, j);
        if (pixInitAccumulate == 0) {
            return null;
        }
        return new Pix(pixInitAccumulate, false);
    }

    public static Pix pixFinalAccumulate(Pix pix, long j, int i) {
        long pixFinalAccumulate = JniLeptonicaJNI.pixFinalAccumulate(Pix.getCPtr(pix), pix, j, i);
        if (pixFinalAccumulate == 0) {
            return null;
        }
        return new Pix(pixFinalAccumulate, false);
    }

    public static Pix pixFinalAccumulateThreshold(Pix pix, long j, long j2) {
        long pixFinalAccumulateThreshold = JniLeptonicaJNI.pixFinalAccumulateThreshold(Pix.getCPtr(pix), pix, j, j2);
        if (pixFinalAccumulateThreshold == 0) {
            return null;
        }
        return new Pix(pixFinalAccumulateThreshold, false);
    }

    public static int pixAccumulate(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixAccumulate(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static int pixMultConstAccumulate(Pix pix, float f, long j) {
        return JniLeptonicaJNI.pixMultConstAccumulate(Pix.getCPtr(pix), pix, f, j);
    }

    public static Pix pixAbsDifference(Pix pix, Pix pix2) {
        long pixAbsDifference = JniLeptonicaJNI.pixAbsDifference(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixAbsDifference == 0) {
            return null;
        }
        return new Pix(pixAbsDifference, false);
    }

    public static Pix pixAddRGB(Pix pix, Pix pix2) {
        long pixAddRGB = JniLeptonicaJNI.pixAddRGB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixAddRGB == 0) {
            return null;
        }
        return new Pix(pixAddRGB, false);
    }

    public static Pix pixMinOrMax(Pix pix, Pix pix2, Pix pix3, int i) {
        long pixMinOrMax = JniLeptonicaJNI.pixMinOrMax(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i);
        if (pixMinOrMax == 0) {
            return null;
        }
        return new Pix(pixMinOrMax, false);
    }

    public static Pix pixMaxDynamicRange(Pix pix, int i) {
        long pixMaxDynamicRange = JniLeptonicaJNI.pixMaxDynamicRange(Pix.getCPtr(pix), pix, i);
        if (pixMaxDynamicRange == 0) {
            return null;
        }
        return new Pix(pixMaxDynamicRange, false);
    }

    public static SWIGTYPE_p_float makeLogBase2Tab() {
        long makeLogBase2Tab = JniLeptonicaJNI.makeLogBase2Tab();
        if (makeLogBase2Tab == 0) {
            return null;
        }
        return new SWIGTYPE_p_float(makeLogBase2Tab, false);
    }

    public static float getLogBase2(int i, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.getLogBase2(i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static PixComp pixcompCreateFromPix(Pix pix, int i) {
        long pixcompCreateFromPix = JniLeptonicaJNI.pixcompCreateFromPix(Pix.getCPtr(pix), pix, i);
        if (pixcompCreateFromPix == 0) {
            return null;
        }
        return new PixComp(pixcompCreateFromPix, false);
    }

    public static PixComp pixcompCreateFromString(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i) {
        long pixcompCreateFromString = JniLeptonicaJNI.pixcompCreateFromString(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i);
        if (pixcompCreateFromString == 0) {
            return null;
        }
        return new PixComp(pixcompCreateFromString, false);
    }

    public static PixComp pixcompCreateFromFile(String str, int i) {
        long pixcompCreateFromFile = JniLeptonicaJNI.pixcompCreateFromFile(str, i);
        if (pixcompCreateFromFile == 0) {
            return null;
        }
        return new PixComp(pixcompCreateFromFile, false);
    }

    public static void pixcompDestroy(SWIGTYPE_p_p_PixComp sWIGTYPE_p_p_PixComp) {
        JniLeptonicaJNI.pixcompDestroy(SWIGTYPE_p_p_PixComp.getCPtr(sWIGTYPE_p_p_PixComp));
    }

    public static int pixcompGetDimensions(PixComp pixComp, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixcompGetDimensions(PixComp.getCPtr(pixComp), pixComp, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixcompDetermineFormat(int i, int i2, int i3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixcompDetermineFormat(i, i2, i3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Pix pixCreateFromPixcomp(PixComp pixComp) {
        long pixCreateFromPixcomp = JniLeptonicaJNI.pixCreateFromPixcomp(PixComp.getCPtr(pixComp), pixComp);
        if (pixCreateFromPixcomp == 0) {
            return null;
        }
        return new Pix(pixCreateFromPixcomp, false);
    }

    public static PixaComp pixacompCreate(int i) {
        long pixacompCreate = JniLeptonicaJNI.pixacompCreate(i);
        if (pixacompCreate == 0) {
            return null;
        }
        return new PixaComp(pixacompCreate, false);
    }

    public static PixaComp pixacompCreateWithInit(int i, int i2, Pix pix, int i3) {
        long pixacompCreateWithInit = JniLeptonicaJNI.pixacompCreateWithInit(i, i2, Pix.getCPtr(pix), pix, i3);
        if (pixacompCreateWithInit == 0) {
            return null;
        }
        return new PixaComp(pixacompCreateWithInit, false);
    }

    public static PixaComp pixacompCreateFromPixa(Pixa pixa, int i, int i2) {
        long pixacompCreateFromPixa = JniLeptonicaJNI.pixacompCreateFromPixa(Pixa.getCPtr(pixa), pixa, i, i2);
        if (pixacompCreateFromPixa == 0) {
            return null;
        }
        return new PixaComp(pixacompCreateFromPixa, false);
    }

    public static PixaComp pixacompCreateFromFiles(String str, String str2, int i) {
        long pixacompCreateFromFiles = JniLeptonicaJNI.pixacompCreateFromFiles(str, str2, i);
        if (pixacompCreateFromFiles == 0) {
            return null;
        }
        return new PixaComp(pixacompCreateFromFiles, false);
    }

    public static PixaComp pixacompCreateFromSA(Sarray sarray, int i) {
        long pixacompCreateFromSA = JniLeptonicaJNI.pixacompCreateFromSA(Sarray.getCPtr(sarray), sarray, i);
        if (pixacompCreateFromSA == 0) {
            return null;
        }
        return new PixaComp(pixacompCreateFromSA, false);
    }

    public static void pixacompDestroy(SWIGTYPE_p_p_PixaComp sWIGTYPE_p_p_PixaComp) {
        JniLeptonicaJNI.pixacompDestroy(SWIGTYPE_p_p_PixaComp.getCPtr(sWIGTYPE_p_p_PixaComp));
    }

    public static int pixacompAddPix(PixaComp pixaComp, Pix pix, int i) {
        return JniLeptonicaJNI.pixacompAddPix(PixaComp.getCPtr(pixaComp), pixaComp, Pix.getCPtr(pix), pix, i);
    }

    public static int pixacompAddPixcomp(PixaComp pixaComp, PixComp pixComp) {
        return JniLeptonicaJNI.pixacompAddPixcomp(PixaComp.getCPtr(pixaComp), pixaComp, PixComp.getCPtr(pixComp), pixComp);
    }

    public static int pixacompReplacePix(PixaComp pixaComp, int i, Pix pix, int i2) {
        return JniLeptonicaJNI.pixacompReplacePix(PixaComp.getCPtr(pixaComp), pixaComp, i, Pix.getCPtr(pix), pix, i2);
    }

    public static int pixacompReplacePixcomp(PixaComp pixaComp, int i, PixComp pixComp) {
        return JniLeptonicaJNI.pixacompReplacePixcomp(PixaComp.getCPtr(pixaComp), pixaComp, i, PixComp.getCPtr(pixComp), pixComp);
    }

    public static int pixacompAddBox(PixaComp pixaComp, Box box, int i) {
        return JniLeptonicaJNI.pixacompAddBox(PixaComp.getCPtr(pixaComp), pixaComp, Box.getCPtr(box), box, i);
    }

    public static int pixacompGetCount(PixaComp pixaComp) {
        return JniLeptonicaJNI.pixacompGetCount(PixaComp.getCPtr(pixaComp), pixaComp);
    }

    public static PixComp pixacompGetPixcomp(PixaComp pixaComp, int i) {
        long pixacompGetPixcomp = JniLeptonicaJNI.pixacompGetPixcomp(PixaComp.getCPtr(pixaComp), pixaComp, i);
        if (pixacompGetPixcomp == 0) {
            return null;
        }
        return new PixComp(pixacompGetPixcomp, false);
    }

    public static Pix pixacompGetPix(PixaComp pixaComp, int i) {
        long pixacompGetPix = JniLeptonicaJNI.pixacompGetPix(PixaComp.getCPtr(pixaComp), pixaComp, i);
        if (pixacompGetPix == 0) {
            return null;
        }
        return new Pix(pixacompGetPix, false);
    }

    public static int pixacompGetPixDimensions(PixaComp pixaComp, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixacompGetPixDimensions(PixaComp.getCPtr(pixaComp), pixaComp, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static Boxa pixacompGetBoxa(PixaComp pixaComp, int i) {
        long pixacompGetBoxa = JniLeptonicaJNI.pixacompGetBoxa(PixaComp.getCPtr(pixaComp), pixaComp, i);
        if (pixacompGetBoxa == 0) {
            return null;
        }
        return new Boxa(pixacompGetBoxa, false);
    }

    public static int pixacompGetBoxaCount(PixaComp pixaComp) {
        return JniLeptonicaJNI.pixacompGetBoxaCount(PixaComp.getCPtr(pixaComp), pixaComp);
    }

    public static Box pixacompGetBox(PixaComp pixaComp, int i, int i2) {
        long pixacompGetBox = JniLeptonicaJNI.pixacompGetBox(PixaComp.getCPtr(pixaComp), pixaComp, i, i2);
        if (pixacompGetBox == 0) {
            return null;
        }
        return new Box(pixacompGetBox, false);
    }

    public static int pixacompGetBoxGeometry(PixaComp pixaComp, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.pixacompGetBoxGeometry(PixaComp.getCPtr(pixaComp), pixaComp, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int pixacompGetOffset(PixaComp pixaComp) {
        return JniLeptonicaJNI.pixacompGetOffset(PixaComp.getCPtr(pixaComp), pixaComp);
    }

    public static int pixacompSetOffset(PixaComp pixaComp, int i) {
        return JniLeptonicaJNI.pixacompSetOffset(PixaComp.getCPtr(pixaComp), pixaComp, i);
    }

    public static Pixa pixaCreateFromPixacomp(PixaComp pixaComp, int i) {
        long pixaCreateFromPixacomp = JniLeptonicaJNI.pixaCreateFromPixacomp(PixaComp.getCPtr(pixaComp), pixaComp, i);
        if (pixaCreateFromPixacomp == 0) {
            return null;
        }
        return new Pixa(pixaCreateFromPixacomp, false);
    }

    public static PixaComp pixacompRead(String str) {
        long pixacompRead = JniLeptonicaJNI.pixacompRead(str);
        if (pixacompRead == 0) {
            return null;
        }
        return new PixaComp(pixacompRead, false);
    }

    public static PixaComp pixacompReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixacompReadStream = JniLeptonicaJNI.pixacompReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixacompReadStream == 0) {
            return null;
        }
        return new PixaComp(pixacompReadStream, false);
    }

    public static int pixacompWrite(String str, PixaComp pixaComp) {
        return JniLeptonicaJNI.pixacompWrite(str, PixaComp.getCPtr(pixaComp), pixaComp);
    }

    public static int pixacompWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, PixaComp pixaComp) {
        return JniLeptonicaJNI.pixacompWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), PixaComp.getCPtr(pixaComp), pixaComp);
    }

    public static int pixacompConvertToPdf(PixaComp pixaComp, int i, float f, int i2, int i3, String str, String str2) {
        return JniLeptonicaJNI.pixacompConvertToPdf(PixaComp.getCPtr(pixaComp), pixaComp, i, f, i2, i3, str, str2);
    }

    public static int pixacompConvertToPdfData(PixaComp pixaComp, int i, float f, int i2, int i3, String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.pixacompConvertToPdfData(PixaComp.getCPtr(pixaComp), pixaComp, i, f, i2, i3, str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static int pixacompWriteStreamInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, PixaComp pixaComp, String str) {
        return JniLeptonicaJNI.pixacompWriteStreamInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), PixaComp.getCPtr(pixaComp), pixaComp, str);
    }

    public static int pixcompWriteStreamInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, PixComp pixComp, String str) {
        return JniLeptonicaJNI.pixcompWriteStreamInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), PixComp.getCPtr(pixComp), pixComp, str);
    }

    public static Pix pixacompDisplayTiledAndScaled(PixaComp pixaComp, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixacompDisplayTiledAndScaled = JniLeptonicaJNI.pixacompDisplayTiledAndScaled(PixaComp.getCPtr(pixaComp), pixaComp, i, i2, i3, i4, i5, i6);
        if (pixacompDisplayTiledAndScaled == 0) {
            return null;
        }
        return new Pix(pixacompDisplayTiledAndScaled, false);
    }

    public static Pix pixThreshold8(Pix pix, int i, int i2, int i3) {
        long pixThreshold8 = JniLeptonicaJNI.pixThreshold8(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixThreshold8 == 0) {
            return null;
        }
        return new Pix(pixThreshold8, false);
    }

    public static Pix pixRemoveColormapGeneral(Pix pix, int i, int i2) {
        long pixRemoveColormapGeneral = JniLeptonicaJNI.pixRemoveColormapGeneral(Pix.getCPtr(pix), pix, i, i2);
        if (pixRemoveColormapGeneral == 0) {
            return null;
        }
        return new Pix(pixRemoveColormapGeneral, false);
    }

    public static Pix pixRemoveColormap(Pix pix, int i) {
        long pixRemoveColormap = JniLeptonicaJNI.pixRemoveColormap(Pix.getCPtr(pix), pix, i);
        if (pixRemoveColormap == 0) {
            return null;
        }
        return new Pix(pixRemoveColormap, false);
    }

    public static int pixAddGrayColormap8(Pix pix) {
        return JniLeptonicaJNI.pixAddGrayColormap8(Pix.getCPtr(pix), pix);
    }

    public static Pix pixAddMinimalGrayColormap8(Pix pix) {
        long pixAddMinimalGrayColormap8 = JniLeptonicaJNI.pixAddMinimalGrayColormap8(Pix.getCPtr(pix), pix);
        if (pixAddMinimalGrayColormap8 == 0) {
            return null;
        }
        return new Pix(pixAddMinimalGrayColormap8, false);
    }

    public static Pix pixConvertRGBToLuminance(Pix pix) {
        long pixConvertRGBToLuminance = JniLeptonicaJNI.pixConvertRGBToLuminance(Pix.getCPtr(pix), pix);
        if (pixConvertRGBToLuminance == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToLuminance, false);
    }

    public static Pix pixConvertRGBToGray(Pix pix, float f, float f2, float f3) {
        long pixConvertRGBToGray = JniLeptonicaJNI.pixConvertRGBToGray(Pix.getCPtr(pix), pix, f, f2, f3);
        if (pixConvertRGBToGray == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToGray, false);
    }

    public static Pix pixConvertRGBToGrayFast(Pix pix) {
        long pixConvertRGBToGrayFast = JniLeptonicaJNI.pixConvertRGBToGrayFast(Pix.getCPtr(pix), pix);
        if (pixConvertRGBToGrayFast == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToGrayFast, false);
    }

    public static Pix pixConvertRGBToGrayMinMax(Pix pix, int i) {
        long pixConvertRGBToGrayMinMax = JniLeptonicaJNI.pixConvertRGBToGrayMinMax(Pix.getCPtr(pix), pix, i);
        if (pixConvertRGBToGrayMinMax == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToGrayMinMax, false);
    }

    public static Pix pixConvertRGBToGraySatBoost(Pix pix, int i) {
        long pixConvertRGBToGraySatBoost = JniLeptonicaJNI.pixConvertRGBToGraySatBoost(Pix.getCPtr(pix), pix, i);
        if (pixConvertRGBToGraySatBoost == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToGraySatBoost, false);
    }

    public static Pix pixConvertGrayToColormap(Pix pix) {
        long pixConvertGrayToColormap = JniLeptonicaJNI.pixConvertGrayToColormap(Pix.getCPtr(pix), pix);
        if (pixConvertGrayToColormap == 0) {
            return null;
        }
        return new Pix(pixConvertGrayToColormap, false);
    }

    public static Pix pixConvertGrayToColormap8(Pix pix, int i) {
        long pixConvertGrayToColormap8 = JniLeptonicaJNI.pixConvertGrayToColormap8(Pix.getCPtr(pix), pix, i);
        if (pixConvertGrayToColormap8 == 0) {
            return null;
        }
        return new Pix(pixConvertGrayToColormap8, false);
    }

    public static Pix pixColorizeGray(Pix pix, long j, int i) {
        long pixColorizeGray = JniLeptonicaJNI.pixColorizeGray(Pix.getCPtr(pix), pix, j, i);
        if (pixColorizeGray == 0) {
            return null;
        }
        return new Pix(pixColorizeGray, false);
    }

    public static Pix pixConvertRGBToColormap(Pix pix, int i) {
        long pixConvertRGBToColormap = JniLeptonicaJNI.pixConvertRGBToColormap(Pix.getCPtr(pix), pix, i);
        if (pixConvertRGBToColormap == 0) {
            return null;
        }
        return new Pix(pixConvertRGBToColormap, false);
    }

    public static int pixQuantizeIfFewColors(Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.pixQuantizeIfFewColors(Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static Pix pixConvert16To8(Pix pix, int i) {
        long pixConvert16To8 = JniLeptonicaJNI.pixConvert16To8(Pix.getCPtr(pix), pix, i);
        if (pixConvert16To8 == 0) {
            return null;
        }
        return new Pix(pixConvert16To8, false);
    }

    public static Pix pixConvertGrayToFalseColor(Pix pix, float f) {
        long pixConvertGrayToFalseColor = JniLeptonicaJNI.pixConvertGrayToFalseColor(Pix.getCPtr(pix), pix, f);
        if (pixConvertGrayToFalseColor == 0) {
            return null;
        }
        return new Pix(pixConvertGrayToFalseColor, false);
    }

    public static Pix pixUnpackBinary(Pix pix, int i, int i2) {
        long pixUnpackBinary = JniLeptonicaJNI.pixUnpackBinary(Pix.getCPtr(pix), pix, i, i2);
        if (pixUnpackBinary == 0) {
            return null;
        }
        return new Pix(pixUnpackBinary, false);
    }

    public static Pix pixConvert1To16(Pix pix, Pix pix2, int i, int i2) {
        long pixConvert1To16 = JniLeptonicaJNI.pixConvert1To16(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixConvert1To16 == 0) {
            return null;
        }
        return new Pix(pixConvert1To16, false);
    }

    public static Pix pixConvert1To32(Pix pix, Pix pix2, long j, long j2) {
        long pixConvert1To32 = JniLeptonicaJNI.pixConvert1To32(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, j, j2);
        if (pixConvert1To32 == 0) {
            return null;
        }
        return new Pix(pixConvert1To32, false);
    }

    public static Pix pixConvert1To2Cmap(Pix pix) {
        long pixConvert1To2Cmap = JniLeptonicaJNI.pixConvert1To2Cmap(Pix.getCPtr(pix), pix);
        if (pixConvert1To2Cmap == 0) {
            return null;
        }
        return new Pix(pixConvert1To2Cmap, false);
    }

    public static Pix pixConvert1To2(Pix pix, Pix pix2, int i, int i2) {
        long pixConvert1To2 = JniLeptonicaJNI.pixConvert1To2(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixConvert1To2 == 0) {
            return null;
        }
        return new Pix(pixConvert1To2, false);
    }

    public static Pix pixConvert1To4Cmap(Pix pix) {
        long pixConvert1To4Cmap = JniLeptonicaJNI.pixConvert1To4Cmap(Pix.getCPtr(pix), pix);
        if (pixConvert1To4Cmap == 0) {
            return null;
        }
        return new Pix(pixConvert1To4Cmap, false);
    }

    public static Pix pixConvert1To4(Pix pix, Pix pix2, int i, int i2) {
        long pixConvert1To4 = JniLeptonicaJNI.pixConvert1To4(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixConvert1To4 == 0) {
            return null;
        }
        return new Pix(pixConvert1To4, false);
    }

    public static Pix pixConvert1To8(Pix pix, Pix pix2, short s, short s2) {
        long pixConvert1To8 = JniLeptonicaJNI.pixConvert1To8(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, s, s2);
        if (pixConvert1To8 == 0) {
            return null;
        }
        return new Pix(pixConvert1To8, false);
    }

    public static Pix pixConvert2To8(Pix pix, short s, short s2, short s3, short s4, int i) {
        long pixConvert2To8 = JniLeptonicaJNI.pixConvert2To8(Pix.getCPtr(pix), pix, s, s2, s3, s4, i);
        if (pixConvert2To8 == 0) {
            return null;
        }
        return new Pix(pixConvert2To8, false);
    }

    public static Pix pixConvert4To8(Pix pix, int i) {
        long pixConvert4To8 = JniLeptonicaJNI.pixConvert4To8(Pix.getCPtr(pix), pix, i);
        if (pixConvert4To8 == 0) {
            return null;
        }
        return new Pix(pixConvert4To8, false);
    }

    public static Pix pixConvert8To16(Pix pix, int i) {
        long pixConvert8To16 = JniLeptonicaJNI.pixConvert8To16(Pix.getCPtr(pix), pix, i);
        if (pixConvert8To16 == 0) {
            return null;
        }
        return new Pix(pixConvert8To16, false);
    }

    public static Pix pixConvertTo1(Pix pix, int i) {
        long pixConvertTo1 = JniLeptonicaJNI.pixConvertTo1(Pix.getCPtr(pix), pix, i);
        if (pixConvertTo1 == 0) {
            return null;
        }
        return new Pix(pixConvertTo1, false);
    }

    public static Pix pixConvertTo1BySampling(Pix pix, int i, int i2) {
        long pixConvertTo1BySampling = JniLeptonicaJNI.pixConvertTo1BySampling(Pix.getCPtr(pix), pix, i, i2);
        if (pixConvertTo1BySampling == 0) {
            return null;
        }
        return new Pix(pixConvertTo1BySampling, false);
    }

    public static Pix pixConvertTo8(Pix pix, int i) {
        long pixConvertTo8 = JniLeptonicaJNI.pixConvertTo8(Pix.getCPtr(pix), pix, i);
        if (pixConvertTo8 == 0) {
            return null;
        }
        return new Pix(pixConvertTo8, false);
    }

    public static Pix pixConvertTo8BySampling(Pix pix, int i, int i2) {
        long pixConvertTo8BySampling = JniLeptonicaJNI.pixConvertTo8BySampling(Pix.getCPtr(pix), pix, i, i2);
        if (pixConvertTo8BySampling == 0) {
            return null;
        }
        return new Pix(pixConvertTo8BySampling, false);
    }

    public static Pix pixConvertTo8Color(Pix pix, int i) {
        long pixConvertTo8Color = JniLeptonicaJNI.pixConvertTo8Color(Pix.getCPtr(pix), pix, i);
        if (pixConvertTo8Color == 0) {
            return null;
        }
        return new Pix(pixConvertTo8Color, false);
    }

    public static Pix pixConvertTo16(Pix pix) {
        long pixConvertTo16 = JniLeptonicaJNI.pixConvertTo16(Pix.getCPtr(pix), pix);
        if (pixConvertTo16 == 0) {
            return null;
        }
        return new Pix(pixConvertTo16, false);
    }

    public static Pix pixConvertTo32(Pix pix) {
        long pixConvertTo32 = JniLeptonicaJNI.pixConvertTo32(Pix.getCPtr(pix), pix);
        if (pixConvertTo32 == 0) {
            return null;
        }
        return new Pix(pixConvertTo32, false);
    }

    public static Pix pixConvertTo32BySampling(Pix pix, int i) {
        long pixConvertTo32BySampling = JniLeptonicaJNI.pixConvertTo32BySampling(Pix.getCPtr(pix), pix, i);
        if (pixConvertTo32BySampling == 0) {
            return null;
        }
        return new Pix(pixConvertTo32BySampling, false);
    }

    public static Pix pixConvert8To32(Pix pix) {
        long pixConvert8To32 = JniLeptonicaJNI.pixConvert8To32(Pix.getCPtr(pix), pix);
        if (pixConvert8To32 == 0) {
            return null;
        }
        return new Pix(pixConvert8To32, false);
    }

    public static Pix pixConvertTo8Or32(Pix pix, int i, int i2) {
        long pixConvertTo8Or32 = JniLeptonicaJNI.pixConvertTo8Or32(Pix.getCPtr(pix), pix, i, i2);
        if (pixConvertTo8Or32 == 0) {
            return null;
        }
        return new Pix(pixConvertTo8Or32, false);
    }

    public static Pix pixConvert24To32(Pix pix) {
        long pixConvert24To32 = JniLeptonicaJNI.pixConvert24To32(Pix.getCPtr(pix), pix);
        if (pixConvert24To32 == 0) {
            return null;
        }
        return new Pix(pixConvert24To32, false);
    }

    public static Pix pixConvert32To24(Pix pix) {
        long pixConvert32To24 = JniLeptonicaJNI.pixConvert32To24(Pix.getCPtr(pix), pix);
        if (pixConvert32To24 == 0) {
            return null;
        }
        return new Pix(pixConvert32To24, false);
    }

    public static Pix pixRemoveAlpha(Pix pix) {
        long pixRemoveAlpha = JniLeptonicaJNI.pixRemoveAlpha(Pix.getCPtr(pix), pix);
        if (pixRemoveAlpha == 0) {
            return null;
        }
        return new Pix(pixRemoveAlpha, false);
    }

    public static Pix pixAddAlphaTo1bpp(Pix pix, Pix pix2) {
        long pixAddAlphaTo1bpp = JniLeptonicaJNI.pixAddAlphaTo1bpp(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixAddAlphaTo1bpp == 0) {
            return null;
        }
        return new Pix(pixAddAlphaTo1bpp, false);
    }

    public static Pix pixConvertLossless(Pix pix, int i) {
        long pixConvertLossless = JniLeptonicaJNI.pixConvertLossless(Pix.getCPtr(pix), pix, i);
        if (pixConvertLossless == 0) {
            return null;
        }
        return new Pix(pixConvertLossless, false);
    }

    public static Pix pixConvertForPSWrap(Pix pix) {
        long pixConvertForPSWrap = JniLeptonicaJNI.pixConvertForPSWrap(Pix.getCPtr(pix), pix);
        if (pixConvertForPSWrap == 0) {
            return null;
        }
        return new Pix(pixConvertForPSWrap, false);
    }

    public static Pix pixConvertToSubpixelRGB(Pix pix, float f, float f2, int i) {
        long pixConvertToSubpixelRGB = JniLeptonicaJNI.pixConvertToSubpixelRGB(Pix.getCPtr(pix), pix, f, f2, i);
        if (pixConvertToSubpixelRGB == 0) {
            return null;
        }
        return new Pix(pixConvertToSubpixelRGB, false);
    }

    public static Pix pixConvertGrayToSubpixelRGB(Pix pix, float f, float f2, int i) {
        long pixConvertGrayToSubpixelRGB = JniLeptonicaJNI.pixConvertGrayToSubpixelRGB(Pix.getCPtr(pix), pix, f, f2, i);
        if (pixConvertGrayToSubpixelRGB == 0) {
            return null;
        }
        return new Pix(pixConvertGrayToSubpixelRGB, false);
    }

    public static Pix pixConvertColorToSubpixelRGB(Pix pix, float f, float f2, int i) {
        long pixConvertColorToSubpixelRGB = JniLeptonicaJNI.pixConvertColorToSubpixelRGB(Pix.getCPtr(pix), pix, f, f2, i);
        if (pixConvertColorToSubpixelRGB == 0) {
            return null;
        }
        return new Pix(pixConvertColorToSubpixelRGB, false);
    }

    public static Pix pixConnCompTransform(Pix pix, int i, int i2) {
        long pixConnCompTransform = JniLeptonicaJNI.pixConnCompTransform(Pix.getCPtr(pix), pix, i, i2);
        if (pixConnCompTransform == 0) {
            return null;
        }
        return new Pix(pixConnCompTransform, false);
    }

    public static Pix pixConnCompAreaTransform(Pix pix, int i) {
        long pixConnCompAreaTransform = JniLeptonicaJNI.pixConnCompAreaTransform(Pix.getCPtr(pix), pix, i);
        if (pixConnCompAreaTransform == 0) {
            return null;
        }
        return new Pix(pixConnCompAreaTransform, false);
    }

    public static Pix pixLocToColorTransform(Pix pix) {
        long pixLocToColorTransform = JniLeptonicaJNI.pixLocToColorTransform(Pix.getCPtr(pix), pix);
        if (pixLocToColorTransform == 0) {
            return null;
        }
        return new Pix(pixLocToColorTransform, false);
    }

    public static PixTiling pixTilingCreate(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixTilingCreate = JniLeptonicaJNI.pixTilingCreate(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
        if (pixTilingCreate == 0) {
            return null;
        }
        return new PixTiling(pixTilingCreate, false);
    }

    public static void pixTilingDestroy(SWIGTYPE_p_p_PixTiling sWIGTYPE_p_p_PixTiling) {
        JniLeptonicaJNI.pixTilingDestroy(SWIGTYPE_p_p_PixTiling.getCPtr(sWIGTYPE_p_p_PixTiling));
    }

    public static int pixTilingGetCount(PixTiling pixTiling, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixTilingGetCount(PixTiling.getCPtr(pixTiling), pixTiling, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixTilingGetSize(PixTiling pixTiling, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixTilingGetSize(PixTiling.getCPtr(pixTiling), pixTiling, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pix pixTilingGetTile(PixTiling pixTiling, int i, int i2) {
        long pixTilingGetTile = JniLeptonicaJNI.pixTilingGetTile(PixTiling.getCPtr(pixTiling), pixTiling, i, i2);
        if (pixTilingGetTile == 0) {
            return null;
        }
        return new Pix(pixTilingGetTile, false);
    }

    public static int pixTilingNoStripOnPaint(PixTiling pixTiling) {
        return JniLeptonicaJNI.pixTilingNoStripOnPaint(PixTiling.getCPtr(pixTiling), pixTiling);
    }

    public static int pixTilingPaintTile(Pix pix, int i, int i2, Pix pix2, PixTiling pixTiling) {
        return JniLeptonicaJNI.pixTilingPaintTile(Pix.getCPtr(pix), pix, i, i2, Pix.getCPtr(pix2), pix2, PixTiling.getCPtr(pixTiling), pixTiling);
    }

    public static Pix pixReadStreamPng(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamPng = JniLeptonicaJNI.pixReadStreamPng(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamPng == 0) {
            return null;
        }
        return new Pix(pixReadStreamPng, false);
    }

    public static int readHeaderPng(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.readHeaderPng(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int freadHeaderPng(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.freadHeaderPng(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int readHeaderMemPng(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.readHeaderMemPng(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int fgetPngResolution(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.fgetPngResolution(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int isPngInterlaced(String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.isPngInterlaced(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixWritePng(String str, Pix pix, float f) {
        return JniLeptonicaJNI.pixWritePng(str, Pix.getCPtr(pix), pix, f);
    }

    public static int pixWriteStreamPng(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, float f) {
        return JniLeptonicaJNI.pixWriteStreamPng(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, f);
    }

    public static int pixSetZlibCompression(Pix pix, int i) {
        return JniLeptonicaJNI.pixSetZlibCompression(Pix.getCPtr(pix), pix, i);
    }

    public static void l_pngSetReadStrip16To8(int i) {
        JniLeptonicaJNI.l_pngSetReadStrip16To8(i);
    }

    public static Pix pixReadMemPng(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemPng = JniLeptonicaJNI.pixReadMemPng(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemPng == 0) {
            return null;
        }
        return new Pix(pixReadMemPng, false);
    }

    public static int pixWriteMemPng(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, float f) {
        return JniLeptonicaJNI.pixWriteMemPng(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, f);
    }

    public static Pix pixReadStreamPnm(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamPnm = JniLeptonicaJNI.pixReadStreamPnm(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamPnm == 0) {
            return null;
        }
        return new Pix(pixReadStreamPnm, false);
    }

    public static int readHeaderPnm(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.readHeaderPnm(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int freadHeaderPnm(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.freadHeaderPnm(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int pixWriteStreamPnm(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix) {
        return JniLeptonicaJNI.pixWriteStreamPnm(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix);
    }

    public static int pixWriteStreamAsciiPnm(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix) {
        return JniLeptonicaJNI.pixWriteStreamAsciiPnm(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix);
    }

    public static Pix pixReadMemPnm(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemPnm = JniLeptonicaJNI.pixReadMemPnm(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemPnm == 0) {
            return null;
        }
        return new Pix(pixReadMemPnm, false);
    }

    public static int readHeaderMemPnm(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.readHeaderMemPnm(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int pixWriteMemPnm(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix) {
        return JniLeptonicaJNI.pixWriteMemPnm(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix);
    }

    public static Pix pixProjectiveSampledPta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixProjectiveSampledPta = JniLeptonicaJNI.pixProjectiveSampledPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixProjectiveSampledPta == 0) {
            return null;
        }
        return new Pix(pixProjectiveSampledPta, false);
    }

    public static Pix pixProjectiveSampled(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixProjectiveSampled = JniLeptonicaJNI.pixProjectiveSampled(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixProjectiveSampled == 0) {
            return null;
        }
        return new Pix(pixProjectiveSampled, false);
    }

    public static Pix pixProjectivePta(Pix pix, Pta pta, Pta pta2, int i) {
        long pixProjectivePta = JniLeptonicaJNI.pixProjectivePta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i);
        if (pixProjectivePta == 0) {
            return null;
        }
        return new Pix(pixProjectivePta, false);
    }

    public static Pix pixProjective(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i) {
        long pixProjective = JniLeptonicaJNI.pixProjective(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i);
        if (pixProjective == 0) {
            return null;
        }
        return new Pix(pixProjective, false);
    }

    public static Pix pixProjectivePtaColor(Pix pix, Pta pta, Pta pta2, long j) {
        long pixProjectivePtaColor = JniLeptonicaJNI.pixProjectivePtaColor(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, j);
        if (pixProjectivePtaColor == 0) {
            return null;
        }
        return new Pix(pixProjectivePtaColor, false);
    }

    public static Pix pixProjectiveColor(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, long j) {
        long pixProjectiveColor = JniLeptonicaJNI.pixProjectiveColor(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), j);
        if (pixProjectiveColor == 0) {
            return null;
        }
        return new Pix(pixProjectiveColor, false);
    }

    public static Pix pixProjectivePtaGray(Pix pix, Pta pta, Pta pta2, short s) {
        long pixProjectivePtaGray = JniLeptonicaJNI.pixProjectivePtaGray(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, s);
        if (pixProjectivePtaGray == 0) {
            return null;
        }
        return new Pix(pixProjectivePtaGray, false);
    }

    public static Pix pixProjectiveGray(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, short s) {
        long pixProjectiveGray = JniLeptonicaJNI.pixProjectiveGray(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), s);
        if (pixProjectiveGray == 0) {
            return null;
        }
        return new Pix(pixProjectiveGray, false);
    }

    public static Pix pixProjectivePtaWithAlpha(Pix pix, Pta pta, Pta pta2, Pix pix2, float f, int i) {
        long pixProjectivePtaWithAlpha = JniLeptonicaJNI.pixProjectivePtaWithAlpha(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, Pix.getCPtr(pix2), pix2, f, i);
        if (pixProjectivePtaWithAlpha == 0) {
            return null;
        }
        return new Pix(pixProjectivePtaWithAlpha, false);
    }

    public static int getProjectiveXformCoeffs(Pta pta, Pta pta2, SWIGTYPE_p_p_float sWIGTYPE_p_p_float) {
        return JniLeptonicaJNI.getProjectiveXformCoeffs(Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, SWIGTYPE_p_p_float.getCPtr(sWIGTYPE_p_p_float));
    }

    public static int projectiveXformSampledPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.projectiveXformSampledPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int projectiveXformPt(SWIGTYPE_p_float sWIGTYPE_p_float, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.projectiveXformPt(SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static int convertFilesToPS(String str, String str2, int i, String str3) {
        return JniLeptonicaJNI.convertFilesToPS(str, str2, i, str3);
    }

    public static int sarrayConvertFilesToPS(Sarray sarray, int i, String str) {
        return JniLeptonicaJNI.sarrayConvertFilesToPS(Sarray.getCPtr(sarray), sarray, i, str);
    }

    public static int convertFilesFittedToPS(String str, String str2, float f, float f2, String str3) {
        return JniLeptonicaJNI.convertFilesFittedToPS(str, str2, f, f2, str3);
    }

    public static int sarrayConvertFilesFittedToPS(Sarray sarray, float f, float f2, String str) {
        return JniLeptonicaJNI.sarrayConvertFilesFittedToPS(Sarray.getCPtr(sarray), sarray, f, f2, str);
    }

    public static int writeImageCompressedToPSFile(String str, String str2, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.writeImageCompressedToPSFile(str, str2, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int convertSegmentedPagesToPS(String str, String str2, int i, String str3, String str4, int i2, int i3, int i4, float f, float f2, int i5, String str5) {
        return JniLeptonicaJNI.convertSegmentedPagesToPS(str, str2, i, str3, str4, i2, i3, i4, f, f2, i5, str5);
    }

    public static int pixWriteSegmentedPageToPS(Pix pix, Pix pix2, float f, float f2, int i, int i2, String str) {
        return JniLeptonicaJNI.pixWriteSegmentedPageToPS(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, f2, i, i2, str);
    }

    public static int pixWriteMixedToPS(Pix pix, Pix pix2, float f, int i, String str) {
        return JniLeptonicaJNI.pixWriteMixedToPS(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i, str);
    }

    public static int convertToPSEmbed(String str, String str2, int i) {
        return JniLeptonicaJNI.convertToPSEmbed(str, str2, i);
    }

    public static int pixaWriteCompressedToPS(Pixa pixa, String str, int i, int i2) {
        return JniLeptonicaJNI.pixaWriteCompressedToPS(Pixa.getCPtr(pixa), pixa, str, i, i2);
    }

    public static int pixWritePSEmbed(String str, String str2) {
        return JniLeptonicaJNI.pixWritePSEmbed(str, str2);
    }

    public static int pixWriteStreamPS(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, Box box, int i, float f) {
        return JniLeptonicaJNI.pixWriteStreamPS(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, f);
    }

    public static String pixWriteStringPS(Pix pix, Box box, int i, float f) {
        return JniLeptonicaJNI.pixWriteStringPS(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, f);
    }

    public static String generateUncompressedPS(String str, int i, int i2, int i3, int i4, int i5, float f, float f2, float f3, float f4, int i6) {
        return JniLeptonicaJNI.generateUncompressedPS(str, i, i2, i3, i4, i5, f, f2, f3, f4, i6);
    }

    public static void getScaledParametersPS(Box box, int i, int i2, int i3, float f, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4) {
        JniLeptonicaJNI.getScaledParametersPS(Box.getCPtr(box), box, i, i2, i3, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4));
    }

    public static void convertByteToHexAscii(short s, String str, String str2) {
        JniLeptonicaJNI.convertByteToHexAscii(s, str, str2);
    }

    public static int convertJpegToPSEmbed(String str, String str2) {
        return JniLeptonicaJNI.convertJpegToPSEmbed(str, str2);
    }

    public static int convertJpegToPS(String str, String str2, String str3, int i, int i2, int i3, float f, int i4, int i5) {
        return JniLeptonicaJNI.convertJpegToPS(str, str2, str3, i, i2, i3, f, i4, i5);
    }

    public static int convertJpegToPSString(String str, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_int sWIGTYPE_p_int, int i, int i2, int i3, float f, int i4, int i5) {
        return JniLeptonicaJNI.convertJpegToPSString(str, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, i2, i3, f, i4, i5);
    }

    public static String generateJpegPS(String str, L_Compressed_Data l_Compressed_Data, float f, float f2, float f3, float f4, int i, int i2) {
        return JniLeptonicaJNI.generateJpegPS(str, L_Compressed_Data.getCPtr(l_Compressed_Data), l_Compressed_Data, f, f2, f3, f4, i, i2);
    }

    public static int convertG4ToPSEmbed(String str, String str2) {
        return JniLeptonicaJNI.convertG4ToPSEmbed(str, str2);
    }

    public static int convertG4ToPS(String str, String str2, String str3, int i, int i2, int i3, float f, int i4, int i5, int i6) {
        return JniLeptonicaJNI.convertG4ToPS(str, str2, str3, i, i2, i3, f, i4, i5, i6);
    }

    public static int convertG4ToPSString(String str, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_int sWIGTYPE_p_int, int i, int i2, int i3, float f, int i4, int i5, int i6) {
        return JniLeptonicaJNI.convertG4ToPSString(str, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, i2, i3, f, i4, i5, i6);
    }

    public static String generateG4PS(String str, L_Compressed_Data l_Compressed_Data, float f, float f2, float f3, float f4, int i, int i2, int i3) {
        return JniLeptonicaJNI.generateG4PS(str, L_Compressed_Data.getCPtr(l_Compressed_Data), l_Compressed_Data, f, f2, f3, f4, i, i2, i3);
    }

    public static int convertTiffMultipageToPS(String str, String str2, String str3, float f) {
        return JniLeptonicaJNI.convertTiffMultipageToPS(str, str2, str3, f);
    }

    public static int convertFlateToPSEmbed(String str, String str2) {
        return JniLeptonicaJNI.convertFlateToPSEmbed(str, str2);
    }

    public static int convertFlateToPS(String str, String str2, String str3, int i, int i2, int i3, float f, int i4, int i5) {
        return JniLeptonicaJNI.convertFlateToPS(str, str2, str3, i, i2, i3, f, i4, i5);
    }

    public static int convertFlateToPSString(String str, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_int sWIGTYPE_p_int, int i, int i2, int i3, float f, int i4, int i5) {
        return JniLeptonicaJNI.convertFlateToPSString(str, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, i2, i3, f, i4, i5);
    }

    public static String generateFlatePS(String str, L_Compressed_Data l_Compressed_Data, float f, float f2, float f3, float f4, int i, int i2) {
        return JniLeptonicaJNI.generateFlatePS(str, L_Compressed_Data.getCPtr(l_Compressed_Data), l_Compressed_Data, f, f2, f3, f4, i, i2);
    }

    public static int pixWriteMemPS(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, Box box, int i, float f) {
        return JniLeptonicaJNI.pixWriteMemPS(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, f);
    }

    public static int getResLetterPage(int i, int i2, float f) {
        return JniLeptonicaJNI.getResLetterPage(i, i2, f);
    }

    public static int getResA4Page(int i, int i2, float f) {
        return JniLeptonicaJNI.getResA4Page(i, i2, f);
    }

    public static String encodeAscii85(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.encodeAscii85(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static SWIGTYPE_p_unsigned_char decodeAscii85(String str, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long decodeAscii85 = JniLeptonicaJNI.decodeAscii85(str, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (decodeAscii85 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(decodeAscii85, false);
    }

    public static void l_psWriteBoundingBox(int i) {
        JniLeptonicaJNI.l_psWriteBoundingBox(i);
    }

    public static Pta ptaCreate(int i) {
        long ptaCreate = JniLeptonicaJNI.ptaCreate(i);
        if (ptaCreate == 0) {
            return null;
        }
        return new Pta(ptaCreate, false);
    }

    public static Pta ptaCreateFromNuma(Numa numa, Numa numa2) {
        long ptaCreateFromNuma = JniLeptonicaJNI.ptaCreateFromNuma(Numa.getCPtr(numa), numa, Numa.getCPtr(numa2), numa2);
        if (ptaCreateFromNuma == 0) {
            return null;
        }
        return new Pta(ptaCreateFromNuma, false);
    }

    public static void ptaDestroy(SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta) {
        JniLeptonicaJNI.ptaDestroy(SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta));
    }

    public static Pta ptaCopy(Pta pta) {
        long ptaCopy = JniLeptonicaJNI.ptaCopy(Pta.getCPtr(pta), pta);
        if (ptaCopy == 0) {
            return null;
        }
        return new Pta(ptaCopy, false);
    }

    public static Pta ptaCopyRange(Pta pta, int i, int i2) {
        long ptaCopyRange = JniLeptonicaJNI.ptaCopyRange(Pta.getCPtr(pta), pta, i, i2);
        if (ptaCopyRange == 0) {
            return null;
        }
        return new Pta(ptaCopyRange, false);
    }

    public static Pta ptaClone(Pta pta) {
        long ptaClone = JniLeptonicaJNI.ptaClone(Pta.getCPtr(pta), pta);
        if (ptaClone == 0) {
            return null;
        }
        return new Pta(ptaClone, false);
    }

    public static int ptaEmpty(Pta pta) {
        return JniLeptonicaJNI.ptaEmpty(Pta.getCPtr(pta), pta);
    }

    public static int ptaAddPt(Pta pta, float f, float f2) {
        return JniLeptonicaJNI.ptaAddPt(Pta.getCPtr(pta), pta, f, f2);
    }

    public static int ptaInsertPt(Pta pta, int i, int i2, int i3) {
        return JniLeptonicaJNI.ptaInsertPt(Pta.getCPtr(pta), pta, i, i2, i3);
    }

    public static int ptaRemovePt(Pta pta, int i) {
        return JniLeptonicaJNI.ptaRemovePt(Pta.getCPtr(pta), pta, i);
    }

    public static int ptaGetRefcount(Pta pta) {
        return JniLeptonicaJNI.ptaGetRefcount(Pta.getCPtr(pta), pta);
    }

    public static int ptaChangeRefcount(Pta pta, int i) {
        return JniLeptonicaJNI.ptaChangeRefcount(Pta.getCPtr(pta), pta, i);
    }

    public static int ptaGetCount(Pta pta) {
        return JniLeptonicaJNI.ptaGetCount(Pta.getCPtr(pta), pta);
    }

    public static int ptaGetPt(Pta pta, int i, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.ptaGetPt(Pta.getCPtr(pta), pta, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int ptaGetIPt(Pta pta, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.ptaGetIPt(Pta.getCPtr(pta), pta, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int ptaSetPt(Pta pta, int i, float f, float f2) {
        return JniLeptonicaJNI.ptaSetPt(Pta.getCPtr(pta), pta, i, f, f2);
    }

    public static int ptaGetArrays(Pta pta, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2) {
        return JniLeptonicaJNI.ptaGetArrays(Pta.getCPtr(pta), pta, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2));
    }

    public static Pta ptaRead(String str) {
        long ptaRead = JniLeptonicaJNI.ptaRead(str);
        if (ptaRead == 0) {
            return null;
        }
        return new Pta(ptaRead, false);
    }

    public static Pta ptaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long ptaReadStream = JniLeptonicaJNI.ptaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (ptaReadStream == 0) {
            return null;
        }
        return new Pta(ptaReadStream, false);
    }

    public static int ptaWrite(String str, Pta pta, int i) {
        return JniLeptonicaJNI.ptaWrite(str, Pta.getCPtr(pta), pta, i);
    }

    public static int ptaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pta pta, int i) {
        return JniLeptonicaJNI.ptaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pta.getCPtr(pta), pta, i);
    }

    public static Ptaa ptaaCreate(int i) {
        long ptaaCreate = JniLeptonicaJNI.ptaaCreate(i);
        if (ptaaCreate == 0) {
            return null;
        }
        return new Ptaa(ptaaCreate, false);
    }

    public static void ptaaDestroy(SWIGTYPE_p_p_Ptaa sWIGTYPE_p_p_Ptaa) {
        JniLeptonicaJNI.ptaaDestroy(SWIGTYPE_p_p_Ptaa.getCPtr(sWIGTYPE_p_p_Ptaa));
    }

    public static int ptaaAddPta(Ptaa ptaa, Pta pta, int i) {
        return JniLeptonicaJNI.ptaaAddPta(Ptaa.getCPtr(ptaa), ptaa, Pta.getCPtr(pta), pta, i);
    }

    public static int ptaaGetCount(Ptaa ptaa) {
        return JniLeptonicaJNI.ptaaGetCount(Ptaa.getCPtr(ptaa), ptaa);
    }

    public static Pta ptaaGetPta(Ptaa ptaa, int i, int i2) {
        long ptaaGetPta = JniLeptonicaJNI.ptaaGetPta(Ptaa.getCPtr(ptaa), ptaa, i, i2);
        if (ptaaGetPta == 0) {
            return null;
        }
        return new Pta(ptaaGetPta, false);
    }

    public static int ptaaGetPt(Ptaa ptaa, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.ptaaGetPt(Ptaa.getCPtr(ptaa), ptaa, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int ptaaInitFull(Ptaa ptaa, Pta pta) {
        return JniLeptonicaJNI.ptaaInitFull(Ptaa.getCPtr(ptaa), ptaa, Pta.getCPtr(pta), pta);
    }

    public static int ptaaReplacePta(Ptaa ptaa, int i, Pta pta) {
        return JniLeptonicaJNI.ptaaReplacePta(Ptaa.getCPtr(ptaa), ptaa, i, Pta.getCPtr(pta), pta);
    }

    public static int ptaaAddPt(Ptaa ptaa, int i, float f, float f2) {
        return JniLeptonicaJNI.ptaaAddPt(Ptaa.getCPtr(ptaa), ptaa, i, f, f2);
    }

    public static int ptaaTruncate(Ptaa ptaa) {
        return JniLeptonicaJNI.ptaaTruncate(Ptaa.getCPtr(ptaa), ptaa);
    }

    public static Ptaa ptaaRead(String str) {
        long ptaaRead = JniLeptonicaJNI.ptaaRead(str);
        if (ptaaRead == 0) {
            return null;
        }
        return new Ptaa(ptaaRead, false);
    }

    public static Ptaa ptaaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long ptaaReadStream = JniLeptonicaJNI.ptaaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (ptaaReadStream == 0) {
            return null;
        }
        return new Ptaa(ptaaReadStream, false);
    }

    public static int ptaaWrite(String str, Ptaa ptaa, int i) {
        return JniLeptonicaJNI.ptaaWrite(str, Ptaa.getCPtr(ptaa), ptaa, i);
    }

    public static int ptaaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Ptaa ptaa, int i) {
        return JniLeptonicaJNI.ptaaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Ptaa.getCPtr(ptaa), ptaa, i);
    }

    public static Pta ptaSubsample(Pta pta, int i) {
        long ptaSubsample = JniLeptonicaJNI.ptaSubsample(Pta.getCPtr(pta), pta, i);
        if (ptaSubsample == 0) {
            return null;
        }
        return new Pta(ptaSubsample, false);
    }

    public static int ptaJoin(Pta pta, Pta pta2, int i, int i2) {
        return JniLeptonicaJNI.ptaJoin(Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2, i, i2);
    }

    public static int ptaaJoin(Ptaa ptaa, Ptaa ptaa2, int i, int i2) {
        return JniLeptonicaJNI.ptaaJoin(Ptaa.getCPtr(ptaa), ptaa, Ptaa.getCPtr(ptaa2), ptaa2, i, i2);
    }

    public static Pta ptaReverse(Pta pta, int i) {
        long ptaReverse = JniLeptonicaJNI.ptaReverse(Pta.getCPtr(pta), pta, i);
        if (ptaReverse == 0) {
            return null;
        }
        return new Pta(ptaReverse, false);
    }

    public static Pta ptaTranspose(Pta pta) {
        long ptaTranspose = JniLeptonicaJNI.ptaTranspose(Pta.getCPtr(pta), pta);
        if (ptaTranspose == 0) {
            return null;
        }
        return new Pta(ptaTranspose, false);
    }

    public static Pta ptaCyclicPerm(Pta pta, int i, int i2) {
        long ptaCyclicPerm = JniLeptonicaJNI.ptaCyclicPerm(Pta.getCPtr(pta), pta, i, i2);
        if (ptaCyclicPerm == 0) {
            return null;
        }
        return new Pta(ptaCyclicPerm, false);
    }

    public static Pta ptaSort(Pta pta, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        long ptaSort = JniLeptonicaJNI.ptaSort(Pta.getCPtr(pta), pta, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
        if (ptaSort == 0) {
            return null;
        }
        return new Pta(ptaSort, false);
    }

    public static int ptaGetSortIndex(Pta pta, int i, int i2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaGetSortIndex(Pta.getCPtr(pta), pta, i, i2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Pta ptaSortByIndex(Pta pta, Numa numa) {
        long ptaSortByIndex = JniLeptonicaJNI.ptaSortByIndex(Pta.getCPtr(pta), pta, Numa.getCPtr(numa), numa);
        if (ptaSortByIndex == 0) {
            return null;
        }
        return new Pta(ptaSortByIndex, false);
    }

    public static Pta ptaRemoveDuplicates(Pta pta, long j) {
        long ptaRemoveDuplicates = JniLeptonicaJNI.ptaRemoveDuplicates(Pta.getCPtr(pta), pta, j);
        if (ptaRemoveDuplicates == 0) {
            return null;
        }
        return new Pta(ptaRemoveDuplicates, false);
    }

    public static Ptaa ptaaSortByIndex(Ptaa ptaa, Numa numa) {
        long ptaaSortByIndex = JniLeptonicaJNI.ptaaSortByIndex(Ptaa.getCPtr(ptaa), ptaa, Numa.getCPtr(numa), numa);
        if (ptaaSortByIndex == 0) {
            return null;
        }
        return new Ptaa(ptaaSortByIndex, false);
    }

    public static Box ptaGetBoundingRegion(Pta pta) {
        long ptaGetBoundingRegion = JniLeptonicaJNI.ptaGetBoundingRegion(Pta.getCPtr(pta), pta);
        if (ptaGetBoundingRegion == 0) {
            return null;
        }
        return new Box(ptaGetBoundingRegion, false);
    }

    public static int ptaGetRange(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4) {
        return JniLeptonicaJNI.ptaGetRange(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4));
    }

    public static Pta ptaGetInsideBox(Pta pta, Box box) {
        long ptaGetInsideBox = JniLeptonicaJNI.ptaGetInsideBox(Pta.getCPtr(pta), pta, Box.getCPtr(box), box);
        if (ptaGetInsideBox == 0) {
            return null;
        }
        return new Pta(ptaGetInsideBox, false);
    }

    public static Pta pixFindCornerPixels(Pix pix) {
        long pixFindCornerPixels = JniLeptonicaJNI.pixFindCornerPixels(Pix.getCPtr(pix), pix);
        if (pixFindCornerPixels == 0) {
            return null;
        }
        return new Pta(pixFindCornerPixels, false);
    }

    public static int ptaContainsPt(Pta pta, int i, int i2) {
        return JniLeptonicaJNI.ptaContainsPt(Pta.getCPtr(pta), pta, i, i2);
    }

    public static int ptaTestIntersection(Pta pta, Pta pta2) {
        return JniLeptonicaJNI.ptaTestIntersection(Pta.getCPtr(pta), pta, Pta.getCPtr(pta2), pta2);
    }

    public static Pta ptaTransform(Pta pta, int i, int i2, float f, float f2) {
        long ptaTransform = JniLeptonicaJNI.ptaTransform(Pta.getCPtr(pta), pta, i, i2, f, f2);
        if (ptaTransform == 0) {
            return null;
        }
        return new Pta(ptaTransform, false);
    }

    public static int ptaPtInsidePolygon(Pta pta, float f, float f2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.ptaPtInsidePolygon(Pta.getCPtr(pta), pta, f, f2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static float l_angleBetweenVectors(float f, float f2, float f3, float f4) {
        return JniLeptonicaJNI.l_angleBetweenVectors(f, f2, f3, f4);
    }

    public static int ptaGetLinearLSF(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaGetLinearLSF(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int ptaGetQuadraticLSF(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaGetQuadraticLSF(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int ptaGetCubicLSF(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaGetCubicLSF(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int ptaGetQuarticLSF(Pta pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_float sWIGTYPE_p_float5, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaGetQuarticLSF(Pta.getCPtr(pta), pta, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float5), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int ptaNoisyLinearLSF(Pta pta, float f, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaNoisyLinearLSF(Pta.getCPtr(pta), pta, f, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int ptaNoisyQuadraticLSF(Pta pta, float f, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.ptaNoisyQuadraticLSF(Pta.getCPtr(pta), pta, f, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static int applyLinearFit(float f, float f2, float f3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.applyLinearFit(f, f2, f3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int applyQuadraticFit(float f, float f2, float f3, float f4, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.applyQuadraticFit(f, f2, f3, f4, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int applyCubicFit(float f, float f2, float f3, float f4, float f5, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.applyCubicFit(f, f2, f3, f4, f5, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int applyQuarticFit(float f, float f2, float f3, float f4, float f5, float f6, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.applyQuarticFit(f, f2, f3, f4, f5, f6, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixPlotAlongPta(Pix pix, Pta pta, int i, String str) {
        return JniLeptonicaJNI.pixPlotAlongPta(Pix.getCPtr(pix), pix, Pta.getCPtr(pta), pta, i, str);
    }

    public static Pta ptaGetPixelsFromPix(Pix pix, Box box) {
        long ptaGetPixelsFromPix = JniLeptonicaJNI.ptaGetPixelsFromPix(Pix.getCPtr(pix), pix, Box.getCPtr(box), box);
        if (ptaGetPixelsFromPix == 0) {
            return null;
        }
        return new Pta(ptaGetPixelsFromPix, false);
    }

    public static Pix pixGenerateFromPta(Pta pta, int i, int i2) {
        long pixGenerateFromPta = JniLeptonicaJNI.pixGenerateFromPta(Pta.getCPtr(pta), pta, i, i2);
        if (pixGenerateFromPta == 0) {
            return null;
        }
        return new Pix(pixGenerateFromPta, false);
    }

    public static Pta ptaGetBoundaryPixels(Pix pix, int i) {
        long ptaGetBoundaryPixels = JniLeptonicaJNI.ptaGetBoundaryPixels(Pix.getCPtr(pix), pix, i);
        if (ptaGetBoundaryPixels == 0) {
            return null;
        }
        return new Pta(ptaGetBoundaryPixels, false);
    }

    public static Ptaa ptaaGetBoundaryPixels(Pix pix, int i, int i2, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        long ptaaGetBoundaryPixels = JniLeptonicaJNI.ptaaGetBoundaryPixels(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
        if (ptaaGetBoundaryPixels == 0) {
            return null;
        }
        return new Ptaa(ptaaGetBoundaryPixels, false);
    }

    public static Pix pixDisplayPta(Pix pix, Pix pix2, Pta pta) {
        long pixDisplayPta = JniLeptonicaJNI.pixDisplayPta(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pta.getCPtr(pta), pta);
        if (pixDisplayPta == 0) {
            return null;
        }
        return new Pix(pixDisplayPta, false);
    }

    public static Pix pixDisplayPtaaPattern(Pix pix, Pix pix2, Ptaa ptaa, Pix pix3, int i, int i2) {
        long pixDisplayPtaaPattern = JniLeptonicaJNI.pixDisplayPtaaPattern(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Ptaa.getCPtr(ptaa), ptaa, Pix.getCPtr(pix3), pix3, i, i2);
        if (pixDisplayPtaaPattern == 0) {
            return null;
        }
        return new Pix(pixDisplayPtaaPattern, false);
    }

    public static Pix pixDisplayPtaPattern(Pix pix, Pix pix2, Pta pta, Pix pix3, int i, int i2, long j) {
        long pixDisplayPtaPattern = JniLeptonicaJNI.pixDisplayPtaPattern(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pta.getCPtr(pta), pta, Pix.getCPtr(pix3), pix3, i, i2, j);
        if (pixDisplayPtaPattern == 0) {
            return null;
        }
        return new Pix(pixDisplayPtaPattern, false);
    }

    public static Pta ptaReplicatePattern(Pta pta, Pix pix, Pta pta2, int i, int i2, int i3, int i4) {
        long ptaReplicatePattern = JniLeptonicaJNI.ptaReplicatePattern(Pta.getCPtr(pta), pta, Pix.getCPtr(pix), pix, Pta.getCPtr(pta2), pta2, i, i2, i3, i4);
        if (ptaReplicatePattern == 0) {
            return null;
        }
        return new Pta(ptaReplicatePattern, false);
    }

    public static Pix pixDisplayPtaa(Pix pix, Ptaa ptaa) {
        long pixDisplayPtaa = JniLeptonicaJNI.pixDisplayPtaa(Pix.getCPtr(pix), pix, Ptaa.getCPtr(ptaa), ptaa);
        if (pixDisplayPtaa == 0) {
            return null;
        }
        return new Pix(pixDisplayPtaa, false);
    }

    public static L_Ptra ptraCreate(int i) {
        long ptraCreate = JniLeptonicaJNI.ptraCreate(i);
        if (ptraCreate == 0) {
            return null;
        }
        return new L_Ptra(ptraCreate, false);
    }

    public static void ptraDestroy(SWIGTYPE_p_p_L_Ptra sWIGTYPE_p_p_L_Ptra, int i, int i2) {
        JniLeptonicaJNI.ptraDestroy(SWIGTYPE_p_p_L_Ptra.getCPtr(sWIGTYPE_p_p_L_Ptra), i, i2);
    }

    public static int ptraAdd(L_Ptra l_Ptra, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.ptraAdd(L_Ptra.getCPtr(l_Ptra), l_Ptra, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static int ptraInsert(L_Ptra l_Ptra, int i, SWIGTYPE_p_void sWIGTYPE_p_void, int i2) {
        return JniLeptonicaJNI.ptraInsert(L_Ptra.getCPtr(l_Ptra), l_Ptra, i, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i2);
    }

    public static SWIGTYPE_p_void ptraRemove(L_Ptra l_Ptra, int i, int i2) {
        long ptraRemove = JniLeptonicaJNI.ptraRemove(L_Ptra.getCPtr(l_Ptra), l_Ptra, i, i2);
        if (ptraRemove == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(ptraRemove, false);
    }

    public static SWIGTYPE_p_void ptraRemoveLast(L_Ptra l_Ptra) {
        long ptraRemoveLast = JniLeptonicaJNI.ptraRemoveLast(L_Ptra.getCPtr(l_Ptra), l_Ptra);
        if (ptraRemoveLast == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(ptraRemoveLast, false);
    }

    public static SWIGTYPE_p_void ptraReplace(L_Ptra l_Ptra, int i, SWIGTYPE_p_void sWIGTYPE_p_void, int i2) {
        long ptraReplace = JniLeptonicaJNI.ptraReplace(L_Ptra.getCPtr(l_Ptra), l_Ptra, i, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), i2);
        if (ptraReplace == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(ptraReplace, false);
    }

    public static int ptraSwap(L_Ptra l_Ptra, int i, int i2) {
        return JniLeptonicaJNI.ptraSwap(L_Ptra.getCPtr(l_Ptra), l_Ptra, i, i2);
    }

    public static int ptraCompactArray(L_Ptra l_Ptra) {
        return JniLeptonicaJNI.ptraCompactArray(L_Ptra.getCPtr(l_Ptra), l_Ptra);
    }

    public static int ptraReverse(L_Ptra l_Ptra) {
        return JniLeptonicaJNI.ptraReverse(L_Ptra.getCPtr(l_Ptra), l_Ptra);
    }

    public static int ptraJoin(L_Ptra l_Ptra, L_Ptra l_Ptra2) {
        return JniLeptonicaJNI.ptraJoin(L_Ptra.getCPtr(l_Ptra), l_Ptra, L_Ptra.getCPtr(l_Ptra2), l_Ptra2);
    }

    public static int ptraGetMaxIndex(L_Ptra l_Ptra, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.ptraGetMaxIndex(L_Ptra.getCPtr(l_Ptra), l_Ptra, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int ptraGetActualCount(L_Ptra l_Ptra, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.ptraGetActualCount(L_Ptra.getCPtr(l_Ptra), l_Ptra, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static SWIGTYPE_p_void ptraGetPtrToItem(L_Ptra l_Ptra, int i) {
        long ptraGetPtrToItem = JniLeptonicaJNI.ptraGetPtrToItem(L_Ptra.getCPtr(l_Ptra), l_Ptra, i);
        if (ptraGetPtrToItem == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(ptraGetPtrToItem, false);
    }

    public static L_Ptraa ptraaCreate(int i) {
        long ptraaCreate = JniLeptonicaJNI.ptraaCreate(i);
        if (ptraaCreate == 0) {
            return null;
        }
        return new L_Ptraa(ptraaCreate, false);
    }

    public static void ptraaDestroy(SWIGTYPE_p_p_L_Ptraa sWIGTYPE_p_p_L_Ptraa, int i, int i2) {
        JniLeptonicaJNI.ptraaDestroy(SWIGTYPE_p_p_L_Ptraa.getCPtr(sWIGTYPE_p_p_L_Ptraa), i, i2);
    }

    public static int ptraaGetSize(L_Ptraa l_Ptraa, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.ptraaGetSize(L_Ptraa.getCPtr(l_Ptraa), l_Ptraa, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int ptraaInsertPtra(L_Ptraa l_Ptraa, int i, L_Ptra l_Ptra) {
        return JniLeptonicaJNI.ptraaInsertPtra(L_Ptraa.getCPtr(l_Ptraa), l_Ptraa, i, L_Ptra.getCPtr(l_Ptra), l_Ptra);
    }

    public static L_Ptra ptraaGetPtra(L_Ptraa l_Ptraa, int i, int i2) {
        long ptraaGetPtra = JniLeptonicaJNI.ptraaGetPtra(L_Ptraa.getCPtr(l_Ptraa), l_Ptraa, i, i2);
        if (ptraaGetPtra == 0) {
            return null;
        }
        return new L_Ptra(ptraaGetPtra, false);
    }

    public static L_Ptra ptraaFlattenToPtra(L_Ptraa l_Ptraa) {
        long ptraaFlattenToPtra = JniLeptonicaJNI.ptraaFlattenToPtra(L_Ptraa.getCPtr(l_Ptraa), l_Ptraa);
        if (ptraaFlattenToPtra == 0) {
            return null;
        }
        return new L_Ptra(ptraaFlattenToPtra, false);
    }

    public static int pixQuadtreeMean(Pix pix, int i, Pix pix2, SWIGTYPE_p_p_FPixa sWIGTYPE_p_p_FPixa) {
        return JniLeptonicaJNI.pixQuadtreeMean(Pix.getCPtr(pix), pix, i, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_p_FPixa.getCPtr(sWIGTYPE_p_p_FPixa));
    }

    public static int pixQuadtreeVariance(Pix pix, int i, Pix pix2, DPix dPix, SWIGTYPE_p_p_FPixa sWIGTYPE_p_p_FPixa, SWIGTYPE_p_p_FPixa sWIGTYPE_p_p_FPixa2) {
        return JniLeptonicaJNI.pixQuadtreeVariance(Pix.getCPtr(pix), pix, i, Pix.getCPtr(pix2), pix2, DPix.getCPtr(dPix), dPix, SWIGTYPE_p_p_FPixa.getCPtr(sWIGTYPE_p_p_FPixa), SWIGTYPE_p_p_FPixa.getCPtr(sWIGTYPE_p_p_FPixa2));
    }

    public static int pixMeanInRectangle(Pix pix, Box box, Pix pix2, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixMeanInRectangle(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixVarianceInRectangle(Pix pix, Box box, Pix pix2, DPix dPix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixVarianceInRectangle(Pix.getCPtr(pix), pix, Box.getCPtr(box), box, Pix.getCPtr(pix2), pix2, DPix.getCPtr(dPix), dPix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static Boxaa boxaaQuadtreeRegions(int i, int i2, int i3) {
        long boxaaQuadtreeRegions = JniLeptonicaJNI.boxaaQuadtreeRegions(i, i2, i3);
        if (boxaaQuadtreeRegions == 0) {
            return null;
        }
        return new Boxaa(boxaaQuadtreeRegions, false);
    }

    public static int quadtreeGetParent(FPixa fPixa, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.quadtreeGetParent(FPixa.getCPtr(fPixa), fPixa, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int quadtreeGetChildren(FPixa fPixa, int i, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, SWIGTYPE_p_float sWIGTYPE_p_float4) {
        return JniLeptonicaJNI.quadtreeGetChildren(FPixa.getCPtr(fPixa), fPixa, i, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float4));
    }

    public static int quadtreeMaxLevels(int i, int i2) {
        return JniLeptonicaJNI.quadtreeMaxLevels(i, i2);
    }

    public static Pix fpixaDisplayQuadtree(FPixa fPixa, int i, String str) {
        long fpixaDisplayQuadtree = JniLeptonicaJNI.fpixaDisplayQuadtree(FPixa.getCPtr(fPixa), fPixa, i, str);
        if (fpixaDisplayQuadtree == 0) {
            return null;
        }
        return new Pix(fpixaDisplayQuadtree, false);
    }

    public static L_Queue lqueueCreate(int i) {
        long lqueueCreate = JniLeptonicaJNI.lqueueCreate(i);
        if (lqueueCreate == 0) {
            return null;
        }
        return new L_Queue(lqueueCreate, false);
    }

    public static void lqueueDestroy(SWIGTYPE_p_p_L_Queue sWIGTYPE_p_p_L_Queue, int i) {
        JniLeptonicaJNI.lqueueDestroy(SWIGTYPE_p_p_L_Queue.getCPtr(sWIGTYPE_p_p_L_Queue), i);
    }

    public static int lqueueAdd(L_Queue l_Queue, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.lqueueAdd(L_Queue.getCPtr(l_Queue), l_Queue, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static SWIGTYPE_p_void lqueueRemove(L_Queue l_Queue) {
        long lqueueRemove = JniLeptonicaJNI.lqueueRemove(L_Queue.getCPtr(l_Queue), l_Queue);
        if (lqueueRemove == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(lqueueRemove, false);
    }

    public static int lqueueGetCount(L_Queue l_Queue) {
        return JniLeptonicaJNI.lqueueGetCount(L_Queue.getCPtr(l_Queue), l_Queue);
    }

    public static int lqueuePrint(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Queue l_Queue) {
        return JniLeptonicaJNI.lqueuePrint(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Queue.getCPtr(l_Queue), l_Queue);
    }

    public static Pix pixRankFilter(Pix pix, int i, int i2, float f) {
        long pixRankFilter = JniLeptonicaJNI.pixRankFilter(Pix.getCPtr(pix), pix, i, i2, f);
        if (pixRankFilter == 0) {
            return null;
        }
        return new Pix(pixRankFilter, false);
    }

    public static Pix pixRankFilterRGB(Pix pix, int i, int i2, float f) {
        long pixRankFilterRGB = JniLeptonicaJNI.pixRankFilterRGB(Pix.getCPtr(pix), pix, i, i2, f);
        if (pixRankFilterRGB == 0) {
            return null;
        }
        return new Pix(pixRankFilterRGB, false);
    }

    public static Pix pixRankFilterGray(Pix pix, int i, int i2, float f) {
        long pixRankFilterGray = JniLeptonicaJNI.pixRankFilterGray(Pix.getCPtr(pix), pix, i, i2, f);
        if (pixRankFilterGray == 0) {
            return null;
        }
        return new Pix(pixRankFilterGray, false);
    }

    public static Pix pixMedianFilter(Pix pix, int i, int i2) {
        long pixMedianFilter = JniLeptonicaJNI.pixMedianFilter(Pix.getCPtr(pix), pix, i, i2);
        if (pixMedianFilter == 0) {
            return null;
        }
        return new Pix(pixMedianFilter, false);
    }

    public static Pix pixRankFilterWithScaling(Pix pix, int i, int i2, float f, float f2) {
        long pixRankFilterWithScaling = JniLeptonicaJNI.pixRankFilterWithScaling(Pix.getCPtr(pix), pix, i, i2, f, f2);
        if (pixRankFilterWithScaling == 0) {
            return null;
        }
        return new Pix(pixRankFilterWithScaling, false);
    }

    public static Sarray pixProcessBarcodes(Pix pix, int i, int i2, SWIGTYPE_p_p_Sarray sWIGTYPE_p_p_Sarray, int i3) {
        long pixProcessBarcodes = JniLeptonicaJNI.pixProcessBarcodes(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Sarray.getCPtr(sWIGTYPE_p_p_Sarray), i3);
        if (pixProcessBarcodes == 0) {
            return null;
        }
        return new Sarray(pixProcessBarcodes, false);
    }

    public static Pixa pixExtractBarcodes(Pix pix, int i) {
        long pixExtractBarcodes = JniLeptonicaJNI.pixExtractBarcodes(Pix.getCPtr(pix), pix, i);
        if (pixExtractBarcodes == 0) {
            return null;
        }
        return new Pixa(pixExtractBarcodes, false);
    }

    public static Sarray pixReadBarcodes(Pixa pixa, int i, int i2, SWIGTYPE_p_p_Sarray sWIGTYPE_p_p_Sarray, int i3) {
        long pixReadBarcodes = JniLeptonicaJNI.pixReadBarcodes(Pixa.getCPtr(pixa), pixa, i, i2, SWIGTYPE_p_p_Sarray.getCPtr(sWIGTYPE_p_p_Sarray), i3);
        if (pixReadBarcodes == 0) {
            return null;
        }
        return new Sarray(pixReadBarcodes, false);
    }

    public static Numa pixReadBarcodeWidths(Pix pix, int i, int i2) {
        long pixReadBarcodeWidths = JniLeptonicaJNI.pixReadBarcodeWidths(Pix.getCPtr(pix), pix, i, i2);
        if (pixReadBarcodeWidths == 0) {
            return null;
        }
        return new Numa(pixReadBarcodeWidths, false);
    }

    public static Boxa pixLocateBarcodes(Pix pix, int i, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        long pixLocateBarcodes = JniLeptonicaJNI.pixLocateBarcodes(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
        if (pixLocateBarcodes == 0) {
            return null;
        }
        return new Boxa(pixLocateBarcodes, false);
    }

    public static Pix pixDeskewBarcode(Pix pix, Pix pix2, Box box, int i, int i2, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        long pixDeskewBarcode = JniLeptonicaJNI.pixDeskewBarcode(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Box.getCPtr(box), box, i, i2, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
        if (pixDeskewBarcode == 0) {
            return null;
        }
        return new Pix(pixDeskewBarcode, false);
    }

    public static Numa pixExtractBarcodeWidths1(Pix pix, float f, float f2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, int i) {
        long pixExtractBarcodeWidths1 = JniLeptonicaJNI.pixExtractBarcodeWidths1(Pix.getCPtr(pix), pix, f, f2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), i);
        if (pixExtractBarcodeWidths1 == 0) {
            return null;
        }
        return new Numa(pixExtractBarcodeWidths1, false);
    }

    public static Numa pixExtractBarcodeWidths2(Pix pix, float f, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i) {
        long pixExtractBarcodeWidths2 = JniLeptonicaJNI.pixExtractBarcodeWidths2(Pix.getCPtr(pix), pix, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i);
        if (pixExtractBarcodeWidths2 == 0) {
            return null;
        }
        return new Numa(pixExtractBarcodeWidths2, false);
    }

    public static Numa pixExtractBarcodeCrossings(Pix pix, float f, int i) {
        long pixExtractBarcodeCrossings = JniLeptonicaJNI.pixExtractBarcodeCrossings(Pix.getCPtr(pix), pix, f, i);
        if (pixExtractBarcodeCrossings == 0) {
            return null;
        }
        return new Numa(pixExtractBarcodeCrossings, false);
    }

    public static Numa numaQuantizeCrossingsByWidth(Numa numa, float f, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, int i) {
        long numaQuantizeCrossingsByWidth = JniLeptonicaJNI.numaQuantizeCrossingsByWidth(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), i);
        if (numaQuantizeCrossingsByWidth == 0) {
            return null;
        }
        return new Numa(numaQuantizeCrossingsByWidth, false);
    }

    public static Numa numaQuantizeCrossingsByWindow(Numa numa, float f, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i) {
        long numaQuantizeCrossingsByWindow = JniLeptonicaJNI.numaQuantizeCrossingsByWindow(Numa.getCPtr(numa), numa, f, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i);
        if (numaQuantizeCrossingsByWindow == 0) {
            return null;
        }
        return new Numa(numaQuantizeCrossingsByWindow, false);
    }

    public static Pixa pixaReadFiles(String str, String str2) {
        long pixaReadFiles = JniLeptonicaJNI.pixaReadFiles(str, str2);
        if (pixaReadFiles == 0) {
            return null;
        }
        return new Pixa(pixaReadFiles, false);
    }

    public static Pixa pixaReadFilesSA(Sarray sarray) {
        long pixaReadFilesSA = JniLeptonicaJNI.pixaReadFilesSA(Sarray.getCPtr(sarray), sarray);
        if (pixaReadFilesSA == 0) {
            return null;
        }
        return new Pixa(pixaReadFilesSA, false);
    }

    public static Pix pixRead(String str) {
        long pixRead = JniLeptonicaJNI.pixRead(str);
        if (pixRead == 0) {
            return null;
        }
        return new Pix(pixRead, false);
    }

    public static Pix pixReadWithHint(String str, int i) {
        long pixReadWithHint = JniLeptonicaJNI.pixReadWithHint(str, i);
        if (pixReadWithHint == 0) {
            return null;
        }
        return new Pix(pixReadWithHint, false);
    }

    public static Pix pixReadIndexed(Sarray sarray, int i) {
        long pixReadIndexed = JniLeptonicaJNI.pixReadIndexed(Sarray.getCPtr(sarray), sarray, i);
        if (pixReadIndexed == 0) {
            return null;
        }
        return new Pix(pixReadIndexed, false);
    }

    public static Pix pixReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long pixReadStream = JniLeptonicaJNI.pixReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (pixReadStream == 0) {
            return null;
        }
        return new Pix(pixReadStream, false);
    }

    public static int pixReadHeader(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.pixReadHeader(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int findFileFormat(String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.findFileFormat(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int findFileFormatStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.findFileFormatStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int findFileFormatBuffer(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.findFileFormatBuffer(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int fileFormatIsTiff(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        return JniLeptonicaJNI.fileFormatIsTiff(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public static Pix pixReadMem(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMem = JniLeptonicaJNI.pixReadMem(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMem == 0) {
            return null;
        }
        return new Pix(pixReadMem, false);
    }

    public static int pixReadHeaderMem(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6) {
        return JniLeptonicaJNI.pixReadHeaderMem(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6));
    }

    public static int ioFormatTest(String str) {
        return JniLeptonicaJNI.ioFormatTest(str);
    }

    public static L_Recoga recogaCreateFromRecog(L_Recog l_Recog) {
        long recogaCreateFromRecog = JniLeptonicaJNI.recogaCreateFromRecog(L_Recog.getCPtr(l_Recog), l_Recog);
        if (recogaCreateFromRecog == 0) {
            return null;
        }
        return new L_Recoga(recogaCreateFromRecog, false);
    }

    public static L_Recoga recogaCreateFromPixaa(Pixaa pixaa, int i, int i2, int i3, int i4, int i5, String str) {
        long recogaCreateFromPixaa = JniLeptonicaJNI.recogaCreateFromPixaa(Pixaa.getCPtr(pixaa), pixaa, i, i2, i3, i4, i5, str);
        if (recogaCreateFromPixaa == 0) {
            return null;
        }
        return new L_Recoga(recogaCreateFromPixaa, false);
    }

    public static L_Recoga recogaCreate(int i) {
        long recogaCreate = JniLeptonicaJNI.recogaCreate(i);
        if (recogaCreate == 0) {
            return null;
        }
        return new L_Recoga(recogaCreate, false);
    }

    public static void recogaDestroy(SWIGTYPE_p_p_L_Recoga sWIGTYPE_p_p_L_Recoga) {
        JniLeptonicaJNI.recogaDestroy(SWIGTYPE_p_p_L_Recoga.getCPtr(sWIGTYPE_p_p_L_Recoga));
    }

    public static int recogaAddRecog(L_Recoga l_Recoga, L_Recog l_Recog) {
        return JniLeptonicaJNI.recogaAddRecog(L_Recoga.getCPtr(l_Recoga), l_Recoga, L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogReplaceInRecoga(SWIGTYPE_p_p_L_Recog sWIGTYPE_p_p_L_Recog, L_Recog l_Recog) {
        return JniLeptonicaJNI.recogReplaceInRecoga(SWIGTYPE_p_p_L_Recog.getCPtr(sWIGTYPE_p_p_L_Recog), L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static L_Recog recogaGetRecog(L_Recoga l_Recoga, int i) {
        long recogaGetRecog = JniLeptonicaJNI.recogaGetRecog(L_Recoga.getCPtr(l_Recoga), l_Recoga, i);
        if (recogaGetRecog == 0) {
            return null;
        }
        return new L_Recog(recogaGetRecog, false);
    }

    public static int recogaGetCount(L_Recoga l_Recoga) {
        return JniLeptonicaJNI.recogaGetCount(L_Recoga.getCPtr(l_Recoga), l_Recoga);
    }

    public static int recogGetCount(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogGetCount(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogGetIndex(L_Recog l_Recog, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.recogGetIndex(L_Recog.getCPtr(l_Recog), l_Recog, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static L_Recoga recogGetParent(L_Recog l_Recog) {
        long recogGetParent = JniLeptonicaJNI.recogGetParent(L_Recog.getCPtr(l_Recog), l_Recog);
        if (recogGetParent == 0) {
            return null;
        }
        return new L_Recoga(recogGetParent, false);
    }

    public static int recogSetBootflag(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogSetBootflag(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static L_Recog recogCreateFromRecog(L_Recog l_Recog, int i, int i2, int i3, int i4, int i5, String str) {
        long recogCreateFromRecog = JniLeptonicaJNI.recogCreateFromRecog(L_Recog.getCPtr(l_Recog), l_Recog, i, i2, i3, i4, i5, str);
        if (recogCreateFromRecog == 0) {
            return null;
        }
        return new L_Recog(recogCreateFromRecog, false);
    }

    public static L_Recog recogCreateFromPixa(Pixa pixa, int i, int i2, int i3, int i4, int i5, String str) {
        long recogCreateFromPixa = JniLeptonicaJNI.recogCreateFromPixa(Pixa.getCPtr(pixa), pixa, i, i2, i3, i4, i5, str);
        if (recogCreateFromPixa == 0) {
            return null;
        }
        return new L_Recog(recogCreateFromPixa, false);
    }

    public static L_Recog recogCreate(int i, int i2, int i3, int i4, int i5, String str) {
        long recogCreate = JniLeptonicaJNI.recogCreate(i, i2, i3, i4, i5, str);
        if (recogCreate == 0) {
            return null;
        }
        return new L_Recog(recogCreate, false);
    }

    public static void recogDestroy(SWIGTYPE_p_p_L_Recog sWIGTYPE_p_p_L_Recog) {
        JniLeptonicaJNI.recogDestroy(SWIGTYPE_p_p_L_Recog.getCPtr(sWIGTYPE_p_p_L_Recog));
    }

    public static int recogAppend(L_Recog l_Recog, L_Recog l_Recog2) {
        return JniLeptonicaJNI.recogAppend(L_Recog.getCPtr(l_Recog), l_Recog, L_Recog.getCPtr(l_Recog2), l_Recog2);
    }

    public static int recogGetClassIndex(L_Recog l_Recog, int i, String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.recogGetClassIndex(L_Recog.getCPtr(l_Recog), l_Recog, i, str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int recogStringToIndex(L_Recog l_Recog, String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.recogStringToIndex(L_Recog.getCPtr(l_Recog), l_Recog, str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int recogGetClassString(L_Recog l_Recog, int i, SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        return JniLeptonicaJNI.recogGetClassString(L_Recog.getCPtr(l_Recog), l_Recog, i, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public static int l_convertCharstrToInt(String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.l_convertCharstrToInt(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static L_Recoga recogaRead(String str) {
        long recogaRead = JniLeptonicaJNI.recogaRead(str);
        if (recogaRead == 0) {
            return null;
        }
        return new L_Recoga(recogaRead, false);
    }

    public static L_Recoga recogaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long recogaReadStream = JniLeptonicaJNI.recogaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (recogaReadStream == 0) {
            return null;
        }
        return new L_Recoga(recogaReadStream, false);
    }

    public static int recogaWrite(String str, L_Recoga l_Recoga) {
        return JniLeptonicaJNI.recogaWrite(str, L_Recoga.getCPtr(l_Recoga), l_Recoga);
    }

    public static int recogaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Recoga l_Recoga, String str) {
        return JniLeptonicaJNI.recogaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Recoga.getCPtr(l_Recoga), l_Recoga, str);
    }

    public static int recogaWritePixaa(String str, L_Recoga l_Recoga) {
        return JniLeptonicaJNI.recogaWritePixaa(str, L_Recoga.getCPtr(l_Recoga), l_Recoga);
    }

    public static L_Recog recogRead(String str) {
        long recogRead = JniLeptonicaJNI.recogRead(str);
        if (recogRead == 0) {
            return null;
        }
        return new L_Recog(recogRead, false);
    }

    public static L_Recog recogReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long recogReadStream = JniLeptonicaJNI.recogReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (recogReadStream == 0) {
            return null;
        }
        return new L_Recog(recogReadStream, false);
    }

    public static int recogWrite(String str, L_Recog l_Recog) {
        return JniLeptonicaJNI.recogWrite(str, L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Recog l_Recog, String str) {
        return JniLeptonicaJNI.recogWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Recog.getCPtr(l_Recog), l_Recog, str);
    }

    public static int recogWritePixa(String str, L_Recog l_Recog) {
        return JniLeptonicaJNI.recogWritePixa(str, L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogDecode(L_Recog l_Recog, Pix pix, int i, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogDecode(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogMakeDecodingArrays(L_Recog l_Recog, Pix pix, int i) {
        return JniLeptonicaJNI.recogMakeDecodingArrays(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, i);
    }

    public static int recogRunViterbi(L_Recog l_Recog, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogRunViterbi(L_Recog.getCPtr(l_Recog), l_Recog, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogCreateDid(L_Recog l_Recog, Pix pix) {
        return JniLeptonicaJNI.recogCreateDid(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix);
    }

    public static int recogDestroyDid(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogDestroyDid(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogDidExists(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogDidExists(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static L_Rdid recogGetDid(L_Recog l_Recog) {
        long recogGetDid = JniLeptonicaJNI.recogGetDid(L_Recog.getCPtr(l_Recog), l_Recog);
        if (recogGetDid == 0) {
            return null;
        }
        return new L_Rdid(recogGetDid, false);
    }

    public static int recogSetChannelParams(L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogSetChannelParams(L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int recogaIdentifyMultiple(L_Recoga l_Recoga, Pix pix, int i, int i2, int i3, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, int i4) {
        return JniLeptonicaJNI.recogaIdentifyMultiple(L_Recoga.getCPtr(l_Recoga), l_Recoga, Pix.getCPtr(pix), pix, i, i2, i3, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), i4);
    }

    public static int recogSplitIntoCharacters(L_Recog l_Recog, Pix pix, int i, int i2, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, int i3) {
        return JniLeptonicaJNI.recogSplitIntoCharacters(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), i3);
    }

    public static int recogCorrelationBestRow(L_Recog l_Recog, Pix pix, SWIGTYPE_p_p_Boxa sWIGTYPE_p_p_Boxa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Sarray sWIGTYPE_p_p_Sarray, int i) {
        return JniLeptonicaJNI.recogCorrelationBestRow(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Boxa.getCPtr(sWIGTYPE_p_p_Boxa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Sarray.getCPtr(sWIGTYPE_p_p_Sarray), i);
    }

    public static int recogCorrelationBestChar(L_Recog l_Recog, Pix pix, SWIGTYPE_p_p_Box sWIGTYPE_p_p_Box, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogCorrelationBestChar(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Box.getCPtr(sWIGTYPE_p_p_Box), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogaIdentifyPixa(L_Recoga l_Recoga, Pixa pixa, Numa numa, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogaIdentifyPixa(L_Recoga.getCPtr(l_Recoga), l_Recoga, Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogIdentifyPixa(L_Recog l_Recog, Pixa pixa, Numa numa, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogIdentifyPixa(L_Recog.getCPtr(l_Recog), l_Recog, Pixa.getCPtr(pixa), pixa, Numa.getCPtr(numa), numa, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogIdentifyPix(L_Recog l_Recog, Pix pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        return JniLeptonicaJNI.recogIdentifyPix(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
    }

    public static int recogSkipIdentify(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogSkipIdentify(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static void rchaDestroy(SWIGTYPE_p_p_L_Rcha sWIGTYPE_p_p_L_Rcha) {
        JniLeptonicaJNI.rchaDestroy(SWIGTYPE_p_p_L_Rcha.getCPtr(sWIGTYPE_p_p_L_Rcha));
    }

    public static void rchDestroy(SWIGTYPE_p_p_L_Rch sWIGTYPE_p_p_L_Rch) {
        JniLeptonicaJNI.rchDestroy(SWIGTYPE_p_p_L_Rch.getCPtr(sWIGTYPE_p_p_L_Rch));
    }

    public static int rchaExtract(L_Rcha l_Rcha, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Sarray sWIGTYPE_p_p_Sarray, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa5, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa6) {
        return JniLeptonicaJNI.rchaExtract(L_Rcha.getCPtr(l_Rcha), l_Rcha, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Sarray.getCPtr(sWIGTYPE_p_p_Sarray), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa5), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa6));
    }

    public static int rchExtract(L_Rch l_Rch, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.rchExtract(L_Rch.getCPtr(l_Rch), l_Rch, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static Pix recogProcessToIdentify(L_Recog l_Recog, Pix pix, int i) {
        long recogProcessToIdentify = JniLeptonicaJNI.recogProcessToIdentify(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, i);
        if (recogProcessToIdentify == 0) {
            return null;
        }
        return new Pix(recogProcessToIdentify, false);
    }

    public static Pix recogPreSplittingFilter(L_Recog l_Recog, Pix pix, float f, float f2, float f3, int i) {
        long recogPreSplittingFilter = JniLeptonicaJNI.recogPreSplittingFilter(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, f, f2, f3, i);
        if (recogPreSplittingFilter == 0) {
            return null;
        }
        return new Pix(recogPreSplittingFilter, false);
    }

    public static int recogSplittingFilter(L_Recog l_Recog, Pix pix, float f, float f2, float f3, SWIGTYPE_p_int sWIGTYPE_p_int, int i) {
        return JniLeptonicaJNI.recogSplittingFilter(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, f, f2, f3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i);
    }

    public static Sarray recogaExtractNumbers(L_Recoga l_Recoga, Boxa boxa, float f, int i, SWIGTYPE_p_p_Boxaa sWIGTYPE_p_p_Boxaa, SWIGTYPE_p_p_Numaa sWIGTYPE_p_p_Numaa) {
        long recogaExtractNumbers = JniLeptonicaJNI.recogaExtractNumbers(L_Recoga.getCPtr(l_Recoga), l_Recoga, Boxa.getCPtr(boxa), boxa, f, i, SWIGTYPE_p_p_Boxaa.getCPtr(sWIGTYPE_p_p_Boxaa), SWIGTYPE_p_p_Numaa.getCPtr(sWIGTYPE_p_p_Numaa));
        if (recogaExtractNumbers == 0) {
            return null;
        }
        return new Sarray(recogaExtractNumbers, false);
    }

    public static int recogSetTemplateType(L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogSetTemplateType(L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int recogSetScaling(L_Recog l_Recog, int i, int i2) {
        return JniLeptonicaJNI.recogSetScaling(L_Recog.getCPtr(l_Recog), l_Recog, i, i2);
    }

    public static int recogTrainLabelled(L_Recog l_Recog, Pix pix, Box box, String str, int i, int i2) {
        return JniLeptonicaJNI.recogTrainLabelled(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, str, i, i2);
    }

    public static int recogProcessMultLabelled(L_Recog l_Recog, Pix pix, Box box, String str, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, int i) {
        return JniLeptonicaJNI.recogProcessMultLabelled(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, str, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), i);
    }

    public static int recogProcessSingleLabelled(L_Recog l_Recog, Pix pix, Box box, String str, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        return JniLeptonicaJNI.recogProcessSingleLabelled(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, str, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static int recogAddSamples(L_Recog l_Recog, Pixa pixa, int i, int i2) {
        return JniLeptonicaJNI.recogAddSamples(L_Recog.getCPtr(l_Recog), l_Recog, Pixa.getCPtr(pixa), pixa, i, i2);
    }

    public static Pix recogScaleCharacter(L_Recog l_Recog, Pix pix) {
        long recogScaleCharacter = JniLeptonicaJNI.recogScaleCharacter(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix);
        if (recogScaleCharacter == 0) {
            return null;
        }
        return new Pix(recogScaleCharacter, false);
    }

    public static int recogAverageSamples(L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogAverageSamples(L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int pixaAccumulateSamples(Pixa pixa, Pta pta, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixaAccumulateSamples(Pixa.getCPtr(pixa), pixa, Pta.getCPtr(pta), pta, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int recogTrainingFinished(L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogTrainingFinished(L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int recogRemoveOutliers(L_Recog l_Recog, float f, float f2, int i) {
        return JniLeptonicaJNI.recogRemoveOutliers(L_Recog.getCPtr(l_Recog), l_Recog, f, f2, i);
    }

    public static int recogaTrainingDone(L_Recoga l_Recoga, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.recogaTrainingDone(L_Recoga.getCPtr(l_Recoga), l_Recoga, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int recogaFinishAveraging(L_Recoga l_Recoga) {
        return JniLeptonicaJNI.recogaFinishAveraging(L_Recoga.getCPtr(l_Recoga), l_Recoga);
    }

    public static int recogTrainUnlabelled(L_Recog l_Recog, L_Recog l_Recog2, Pix pix, Box box, int i, float f, int i2) {
        return JniLeptonicaJNI.recogTrainUnlabelled(L_Recog.getCPtr(l_Recog), l_Recog, L_Recog.getCPtr(l_Recog2), l_Recog2, Pix.getCPtr(pix), pix, Box.getCPtr(box), box, i, f, i2);
    }

    public static int recogPadTrainingSet(SWIGTYPE_p_p_L_Recog sWIGTYPE_p_p_L_Recog, int i) {
        return JniLeptonicaJNI.recogPadTrainingSet(SWIGTYPE_p_p_L_Recog.getCPtr(sWIGTYPE_p_p_L_Recog), i);
    }

    public static int recogBestCorrelForPadding(L_Recog l_Recog, L_Recoga l_Recoga, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa3, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa4, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        return JniLeptonicaJNI.recogBestCorrelForPadding(L_Recog.getCPtr(l_Recog), l_Recog, L_Recoga.getCPtr(l_Recoga), l_Recoga, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa3), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa4), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static int recogCorrelAverages(L_Recog l_Recog, L_Recog l_Recog2, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa2, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa) {
        return JniLeptonicaJNI.recogCorrelAverages(L_Recog.getCPtr(l_Recog), l_Recog, L_Recog.getCPtr(l_Recog2), l_Recog2, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa2), SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa));
    }

    public static int recogSetPadParams(L_Recog l_Recog, String str, String str2, String str3, int i, int i2, int i3) {
        return JniLeptonicaJNI.recogSetPadParams(L_Recog.getCPtr(l_Recog), l_Recog, str, str2, str3, i, i2, i3);
    }

    public static int recogaShowContent(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Recoga l_Recoga, int i) {
        return JniLeptonicaJNI.recogaShowContent(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Recoga.getCPtr(l_Recoga), l_Recoga, i);
    }

    public static int recogShowContent(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogShowContent(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int recogDebugAverages(L_Recog l_Recog, int i) {
        return JniLeptonicaJNI.recogDebugAverages(L_Recog.getCPtr(l_Recog), l_Recog, i);
    }

    public static int recogShowAverageTemplates(L_Recog l_Recog) {
        return JniLeptonicaJNI.recogShowAverageTemplates(L_Recog.getCPtr(l_Recog), l_Recog);
    }

    public static int recogShowMatchesInRange(L_Recog l_Recog, Pixa pixa, float f, float f2, int i) {
        return JniLeptonicaJNI.recogShowMatchesInRange(L_Recog.getCPtr(l_Recog), l_Recog, Pixa.getCPtr(pixa), pixa, f, f2, i);
    }

    public static Pix recogShowMatch(L_Recog l_Recog, Pix pix, Pix pix2, Box box, int i, float f) {
        long recogShowMatch = JniLeptonicaJNI.recogShowMatch(L_Recog.getCPtr(l_Recog), l_Recog, Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Box.getCPtr(box), box, i, f);
        if (recogShowMatch == 0) {
            return null;
        }
        return new Pix(recogShowMatch, false);
    }

    public static int recogMakeBmf(L_Recog l_Recog, String str, int i) {
        return JniLeptonicaJNI.recogMakeBmf(L_Recog.getCPtr(l_Recog), l_Recog, str, i);
    }

    public static int regTestSetup(int i, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_L_RegParams sWIGTYPE_p_p_L_RegParams) {
        return JniLeptonicaJNI.regTestSetup(i, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_L_RegParams.getCPtr(sWIGTYPE_p_p_L_RegParams));
    }

    public static int regTestCleanup(L_RegParams l_RegParams) {
        return JniLeptonicaJNI.regTestCleanup(L_RegParams.getCPtr(l_RegParams), l_RegParams);
    }

    public static int regTestCompareValues(L_RegParams l_RegParams, float f, float f2, float f3) {
        return JniLeptonicaJNI.regTestCompareValues(L_RegParams.getCPtr(l_RegParams), l_RegParams, f, f2, f3);
    }

    public static int regTestCompareStrings(L_RegParams l_RegParams, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2, long j2) {
        return JniLeptonicaJNI.regTestCompareStrings(L_RegParams.getCPtr(l_RegParams), l_RegParams, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2), j2);
    }

    public static int regTestComparePix(L_RegParams l_RegParams, Pix pix, Pix pix2) {
        return JniLeptonicaJNI.regTestComparePix(L_RegParams.getCPtr(l_RegParams), l_RegParams, Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
    }

    public static int regTestCompareSimilarPix(L_RegParams l_RegParams, Pix pix, Pix pix2, int i, float f, int i2) {
        return JniLeptonicaJNI.regTestCompareSimilarPix(L_RegParams.getCPtr(l_RegParams), l_RegParams, Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, f, i2);
    }

    public static int regTestCheckFile(L_RegParams l_RegParams, String str) {
        return JniLeptonicaJNI.regTestCheckFile(L_RegParams.getCPtr(l_RegParams), l_RegParams, str);
    }

    public static int regTestCompareFiles(L_RegParams l_RegParams, int i, int i2) {
        return JniLeptonicaJNI.regTestCompareFiles(L_RegParams.getCPtr(l_RegParams), l_RegParams, i, i2);
    }

    public static int regTestWritePixAndCheck(L_RegParams l_RegParams, Pix pix, int i) {
        return JniLeptonicaJNI.regTestWritePixAndCheck(L_RegParams.getCPtr(l_RegParams), l_RegParams, Pix.getCPtr(pix), pix, i);
    }

    public static int pixRasterop(Pix pix, int i, int i2, int i3, int i4, int i5, Pix pix2, int i6, int i7) {
        return JniLeptonicaJNI.pixRasterop(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, Pix.getCPtr(pix2), pix2, i6, i7);
    }

    public static int pixRasteropVip(Pix pix, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixRasteropVip(Pix.getCPtr(pix), pix, i, i2, i3, i4);
    }

    public static int pixRasteropHip(Pix pix, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixRasteropHip(Pix.getCPtr(pix), pix, i, i2, i3, i4);
    }

    public static Pix pixTranslate(Pix pix, Pix pix2, int i, int i2, int i3) {
        long pixTranslate = JniLeptonicaJNI.pixTranslate(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2, i3);
        if (pixTranslate == 0) {
            return null;
        }
        return new Pix(pixTranslate, false);
    }

    public static int pixRasteropIP(Pix pix, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixRasteropIP(Pix.getCPtr(pix), pix, i, i2, i3);
    }

    public static int pixRasteropFullImage(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixRasteropFullImage(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static void rasteropVipLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        JniLeptonicaJNI.rasteropVipLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5, i6, i7);
    }

    public static void rasteropHipLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5, int i6) {
        JniLeptonicaJNI.rasteropHipLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5, i6);
    }

    public static void shiftDataHorizontalLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3) {
        JniLeptonicaJNI.shiftDataHorizontalLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3);
    }

    public static void rasteropUniLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        JniLeptonicaJNI.rasteropUniLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public static void rasteropLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i10, int i11, int i12, int i13, int i14) {
        JniLeptonicaJNI.rasteropLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5, i6, i7, i8, i9, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i10, i11, i12, i13, i14);
    }

    public static Pix pixRotate(Pix pix, float f, int i, int i2, int i3, int i4) {
        long pixRotate = JniLeptonicaJNI.pixRotate(Pix.getCPtr(pix), pix, f, i, i2, i3, i4);
        if (pixRotate == 0) {
            return null;
        }
        return new Pix(pixRotate, false);
    }

    public static Pix pixEmbedForRotation(Pix pix, float f, int i, int i2, int i3) {
        long pixEmbedForRotation = JniLeptonicaJNI.pixEmbedForRotation(Pix.getCPtr(pix), pix, f, i, i2, i3);
        if (pixEmbedForRotation == 0) {
            return null;
        }
        return new Pix(pixEmbedForRotation, false);
    }

    public static Pix pixRotateBySampling(Pix pix, int i, int i2, float f, int i3) {
        long pixRotateBySampling = JniLeptonicaJNI.pixRotateBySampling(Pix.getCPtr(pix), pix, i, i2, f, i3);
        if (pixRotateBySampling == 0) {
            return null;
        }
        return new Pix(pixRotateBySampling, false);
    }

    public static Pix pixRotateBinaryNice(Pix pix, float f, int i) {
        long pixRotateBinaryNice = JniLeptonicaJNI.pixRotateBinaryNice(Pix.getCPtr(pix), pix, f, i);
        if (pixRotateBinaryNice == 0) {
            return null;
        }
        return new Pix(pixRotateBinaryNice, false);
    }

    public static Pix pixRotateWithAlpha(Pix pix, float f, Pix pix2, float f2) {
        long pixRotateWithAlpha = JniLeptonicaJNI.pixRotateWithAlpha(Pix.getCPtr(pix), pix, f, Pix.getCPtr(pix2), pix2, f2);
        if (pixRotateWithAlpha == 0) {
            return null;
        }
        return new Pix(pixRotateWithAlpha, false);
    }

    public static Pix pixRotateAM(Pix pix, float f, int i) {
        long pixRotateAM = JniLeptonicaJNI.pixRotateAM(Pix.getCPtr(pix), pix, f, i);
        if (pixRotateAM == 0) {
            return null;
        }
        return new Pix(pixRotateAM, false);
    }

    public static Pix pixRotateAMColor(Pix pix, float f, long j) {
        long pixRotateAMColor = JniLeptonicaJNI.pixRotateAMColor(Pix.getCPtr(pix), pix, f, j);
        if (pixRotateAMColor == 0) {
            return null;
        }
        return new Pix(pixRotateAMColor, false);
    }

    public static Pix pixRotateAMGray(Pix pix, float f, short s) {
        long pixRotateAMGray = JniLeptonicaJNI.pixRotateAMGray(Pix.getCPtr(pix), pix, f, s);
        if (pixRotateAMGray == 0) {
            return null;
        }
        return new Pix(pixRotateAMGray, false);
    }

    public static Pix pixRotateAMCorner(Pix pix, float f, int i) {
        long pixRotateAMCorner = JniLeptonicaJNI.pixRotateAMCorner(Pix.getCPtr(pix), pix, f, i);
        if (pixRotateAMCorner == 0) {
            return null;
        }
        return new Pix(pixRotateAMCorner, false);
    }

    public static Pix pixRotateAMColorCorner(Pix pix, float f, long j) {
        long pixRotateAMColorCorner = JniLeptonicaJNI.pixRotateAMColorCorner(Pix.getCPtr(pix), pix, f, j);
        if (pixRotateAMColorCorner == 0) {
            return null;
        }
        return new Pix(pixRotateAMColorCorner, false);
    }

    public static Pix pixRotateAMGrayCorner(Pix pix, float f, short s) {
        long pixRotateAMGrayCorner = JniLeptonicaJNI.pixRotateAMGrayCorner(Pix.getCPtr(pix), pix, f, s);
        if (pixRotateAMGrayCorner == 0) {
            return null;
        }
        return new Pix(pixRotateAMGrayCorner, false);
    }

    public static Pix pixRotateAMColorFast(Pix pix, float f, long j) {
        long pixRotateAMColorFast = JniLeptonicaJNI.pixRotateAMColorFast(Pix.getCPtr(pix), pix, f, j);
        if (pixRotateAMColorFast == 0) {
            return null;
        }
        return new Pix(pixRotateAMColorFast, false);
    }

    public static void rotateAMColorLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, long j) {
        JniLeptonicaJNI.rotateAMColorLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, j);
    }

    public static void rotateAMGrayLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, short s) {
        JniLeptonicaJNI.rotateAMGrayLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, s);
    }

    public static void rotateAMColorCornerLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, long j) {
        JniLeptonicaJNI.rotateAMColorCornerLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, j);
    }

    public static void rotateAMGrayCornerLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, short s) {
        JniLeptonicaJNI.rotateAMGrayCornerLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, s);
    }

    public static void rotateAMColorFastLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, long j) {
        JniLeptonicaJNI.rotateAMColorFastLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, j);
    }

    public static Pix pixRotateOrth(Pix pix, int i) {
        long pixRotateOrth = JniLeptonicaJNI.pixRotateOrth(Pix.getCPtr(pix), pix, i);
        if (pixRotateOrth == 0) {
            return null;
        }
        return new Pix(pixRotateOrth, false);
    }

    public static Pix pixRotate180(Pix pix, Pix pix2) {
        long pixRotate180 = JniLeptonicaJNI.pixRotate180(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixRotate180 == 0) {
            return null;
        }
        return new Pix(pixRotate180, false);
    }

    public static Pix pixRotate90(Pix pix, int i) {
        long pixRotate90 = JniLeptonicaJNI.pixRotate90(Pix.getCPtr(pix), pix, i);
        if (pixRotate90 == 0) {
            return null;
        }
        return new Pix(pixRotate90, false);
    }

    public static Pix pixFlipLR(Pix pix, Pix pix2) {
        long pixFlipLR = JniLeptonicaJNI.pixFlipLR(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixFlipLR == 0) {
            return null;
        }
        return new Pix(pixFlipLR, false);
    }

    public static Pix pixFlipTB(Pix pix, Pix pix2) {
        long pixFlipTB = JniLeptonicaJNI.pixFlipTB(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixFlipTB == 0) {
            return null;
        }
        return new Pix(pixFlipTB, false);
    }

    public static Pix pixRotateShear(Pix pix, int i, int i2, float f, int i3) {
        long pixRotateShear = JniLeptonicaJNI.pixRotateShear(Pix.getCPtr(pix), pix, i, i2, f, i3);
        if (pixRotateShear == 0) {
            return null;
        }
        return new Pix(pixRotateShear, false);
    }

    public static Pix pixRotate2Shear(Pix pix, int i, int i2, float f, int i3) {
        long pixRotate2Shear = JniLeptonicaJNI.pixRotate2Shear(Pix.getCPtr(pix), pix, i, i2, f, i3);
        if (pixRotate2Shear == 0) {
            return null;
        }
        return new Pix(pixRotate2Shear, false);
    }

    public static Pix pixRotate3Shear(Pix pix, int i, int i2, float f, int i3) {
        long pixRotate3Shear = JniLeptonicaJNI.pixRotate3Shear(Pix.getCPtr(pix), pix, i, i2, f, i3);
        if (pixRotate3Shear == 0) {
            return null;
        }
        return new Pix(pixRotate3Shear, false);
    }

    public static int pixRotateShearIP(Pix pix, int i, int i2, float f, int i3) {
        return JniLeptonicaJNI.pixRotateShearIP(Pix.getCPtr(pix), pix, i, i2, f, i3);
    }

    public static Pix pixRotateShearCenter(Pix pix, float f, int i) {
        long pixRotateShearCenter = JniLeptonicaJNI.pixRotateShearCenter(Pix.getCPtr(pix), pix, f, i);
        if (pixRotateShearCenter == 0) {
            return null;
        }
        return new Pix(pixRotateShearCenter, false);
    }

    public static int pixRotateShearCenterIP(Pix pix, float f, int i) {
        return JniLeptonicaJNI.pixRotateShearCenterIP(Pix.getCPtr(pix), pix, f, i);
    }

    public static Pix pixStrokeWidthTransform(Pix pix, int i, int i2, int i3) {
        long pixStrokeWidthTransform = JniLeptonicaJNI.pixStrokeWidthTransform(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixStrokeWidthTransform == 0) {
            return null;
        }
        return new Pix(pixStrokeWidthTransform, false);
    }

    public static Pix pixRunlengthTransform(Pix pix, int i, int i2, int i3) {
        long pixRunlengthTransform = JniLeptonicaJNI.pixRunlengthTransform(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixRunlengthTransform == 0) {
            return null;
        }
        return new Pix(pixRunlengthTransform, false);
    }

    public static int pixFindHorizontalRuns(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixFindHorizontalRuns(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixFindVerticalRuns(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.pixFindVerticalRuns(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static Numa pixFindMaxRuns(Pix pix, int i, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        long pixFindMaxRuns = JniLeptonicaJNI.pixFindMaxRuns(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
        if (pixFindMaxRuns == 0) {
            return null;
        }
        return new Numa(pixFindMaxRuns, false);
    }

    public static int pixFindMaxHorizontalRunOnLine(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixFindMaxHorizontalRunOnLine(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int pixFindMaxVerticalRunOnLine(Pix pix, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixFindMaxVerticalRunOnLine(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int runlengthMembershipOnLine(SWIGTYPE_p_int sWIGTYPE_p_int, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, int i3) {
        return JniLeptonicaJNI.runlengthMembershipOnLine(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), i3);
    }

    public static SWIGTYPE_p_int makeMSBitLocTab(int i) {
        long makeMSBitLocTab = JniLeptonicaJNI.makeMSBitLocTab(i);
        if (makeMSBitLocTab == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(makeMSBitLocTab, false);
    }

    public static Sarray sarrayCreate(int i) {
        long sarrayCreate = JniLeptonicaJNI.sarrayCreate(i);
        if (sarrayCreate == 0) {
            return null;
        }
        return new Sarray(sarrayCreate, false);
    }

    public static Sarray sarrayCreateInitialized(int i, String str) {
        long sarrayCreateInitialized = JniLeptonicaJNI.sarrayCreateInitialized(i, str);
        if (sarrayCreateInitialized == 0) {
            return null;
        }
        return new Sarray(sarrayCreateInitialized, false);
    }

    public static Sarray sarrayCreateWordsFromString(String str) {
        long sarrayCreateWordsFromString = JniLeptonicaJNI.sarrayCreateWordsFromString(str);
        if (sarrayCreateWordsFromString == 0) {
            return null;
        }
        return new Sarray(sarrayCreateWordsFromString, false);
    }

    public static Sarray sarrayCreateLinesFromString(String str, int i) {
        long sarrayCreateLinesFromString = JniLeptonicaJNI.sarrayCreateLinesFromString(str, i);
        if (sarrayCreateLinesFromString == 0) {
            return null;
        }
        return new Sarray(sarrayCreateLinesFromString, false);
    }

    public static void sarrayDestroy(SWIGTYPE_p_p_Sarray sWIGTYPE_p_p_Sarray) {
        JniLeptonicaJNI.sarrayDestroy(SWIGTYPE_p_p_Sarray.getCPtr(sWIGTYPE_p_p_Sarray));
    }

    public static Sarray sarrayCopy(Sarray sarray) {
        long sarrayCopy = JniLeptonicaJNI.sarrayCopy(Sarray.getCPtr(sarray), sarray);
        if (sarrayCopy == 0) {
            return null;
        }
        return new Sarray(sarrayCopy, false);
    }

    public static Sarray sarrayClone(Sarray sarray) {
        long sarrayClone = JniLeptonicaJNI.sarrayClone(Sarray.getCPtr(sarray), sarray);
        if (sarrayClone == 0) {
            return null;
        }
        return new Sarray(sarrayClone, false);
    }

    public static int sarrayAddString(Sarray sarray, String str, int i) {
        return JniLeptonicaJNI.sarrayAddString(Sarray.getCPtr(sarray), sarray, str, i);
    }

    public static String sarrayRemoveString(Sarray sarray, int i) {
        return JniLeptonicaJNI.sarrayRemoveString(Sarray.getCPtr(sarray), sarray, i);
    }

    public static int sarrayReplaceString(Sarray sarray, int i, String str, int i2) {
        return JniLeptonicaJNI.sarrayReplaceString(Sarray.getCPtr(sarray), sarray, i, str, i2);
    }

    public static int sarrayClear(Sarray sarray) {
        return JniLeptonicaJNI.sarrayClear(Sarray.getCPtr(sarray), sarray);
    }

    public static int sarrayGetCount(Sarray sarray) {
        return JniLeptonicaJNI.sarrayGetCount(Sarray.getCPtr(sarray), sarray);
    }

    public static SWIGTYPE_p_p_char sarrayGetArray(Sarray sarray, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        long sarrayGetArray = JniLeptonicaJNI.sarrayGetArray(Sarray.getCPtr(sarray), sarray, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
        if (sarrayGetArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(sarrayGetArray, false);
    }

    public static String sarrayGetString(Sarray sarray, int i, int i2) {
        return JniLeptonicaJNI.sarrayGetString(Sarray.getCPtr(sarray), sarray, i, i2);
    }

    public static int sarrayGetRefcount(Sarray sarray) {
        return JniLeptonicaJNI.sarrayGetRefcount(Sarray.getCPtr(sarray), sarray);
    }

    public static int sarrayChangeRefcount(Sarray sarray, int i) {
        return JniLeptonicaJNI.sarrayChangeRefcount(Sarray.getCPtr(sarray), sarray, i);
    }

    public static String sarrayToString(Sarray sarray, int i) {
        return JniLeptonicaJNI.sarrayToString(Sarray.getCPtr(sarray), sarray, i);
    }

    public static String sarrayToStringRange(Sarray sarray, int i, int i2, int i3) {
        return JniLeptonicaJNI.sarrayToStringRange(Sarray.getCPtr(sarray), sarray, i, i2, i3);
    }

    public static int sarrayConcatenate(Sarray sarray, Sarray sarray2) {
        return JniLeptonicaJNI.sarrayConcatenate(Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2);
    }

    public static int sarrayAppendRange(Sarray sarray, Sarray sarray2, int i, int i2) {
        return JniLeptonicaJNI.sarrayAppendRange(Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2, i, i2);
    }

    public static int sarrayPadToSameSize(Sarray sarray, Sarray sarray2, String str) {
        return JniLeptonicaJNI.sarrayPadToSameSize(Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2, str);
    }

    public static Sarray sarrayConvertWordsToLines(Sarray sarray, int i) {
        long sarrayConvertWordsToLines = JniLeptonicaJNI.sarrayConvertWordsToLines(Sarray.getCPtr(sarray), sarray, i);
        if (sarrayConvertWordsToLines == 0) {
            return null;
        }
        return new Sarray(sarrayConvertWordsToLines, false);
    }

    public static int sarraySplitString(Sarray sarray, String str, String str2) {
        return JniLeptonicaJNI.sarraySplitString(Sarray.getCPtr(sarray), sarray, str, str2);
    }

    public static Sarray sarraySelectBySubstring(Sarray sarray, String str) {
        long sarraySelectBySubstring = JniLeptonicaJNI.sarraySelectBySubstring(Sarray.getCPtr(sarray), sarray, str);
        if (sarraySelectBySubstring == 0) {
            return null;
        }
        return new Sarray(sarraySelectBySubstring, false);
    }

    public static Sarray sarraySelectByRange(Sarray sarray, int i, int i2) {
        long sarraySelectByRange = JniLeptonicaJNI.sarraySelectByRange(Sarray.getCPtr(sarray), sarray, i, i2);
        if (sarraySelectByRange == 0) {
            return null;
        }
        return new Sarray(sarraySelectByRange, false);
    }

    public static int sarrayParseRange(Sarray sarray, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, String str, int i2) {
        return JniLeptonicaJNI.sarrayParseRange(Sarray.getCPtr(sarray), sarray, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), str, i2);
    }

    public static Sarray sarraySort(Sarray sarray, Sarray sarray2, int i) {
        long sarraySort = JniLeptonicaJNI.sarraySort(Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2, i);
        if (sarraySort == 0) {
            return null;
        }
        return new Sarray(sarraySort, false);
    }

    public static Sarray sarraySortByIndex(Sarray sarray, Numa numa) {
        long sarraySortByIndex = JniLeptonicaJNI.sarraySortByIndex(Sarray.getCPtr(sarray), sarray, Numa.getCPtr(numa), numa);
        if (sarraySortByIndex == 0) {
            return null;
        }
        return new Sarray(sarraySortByIndex, false);
    }

    public static int stringCompareLexical(String str, String str2) {
        return JniLeptonicaJNI.stringCompareLexical(str, str2);
    }

    public static Sarray sarrayRead(String str) {
        long sarrayRead = JniLeptonicaJNI.sarrayRead(str);
        if (sarrayRead == 0) {
            return null;
        }
        return new Sarray(sarrayRead, false);
    }

    public static Sarray sarrayReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long sarrayReadStream = JniLeptonicaJNI.sarrayReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (sarrayReadStream == 0) {
            return null;
        }
        return new Sarray(sarrayReadStream, false);
    }

    public static int sarrayWrite(String str, Sarray sarray) {
        return JniLeptonicaJNI.sarrayWrite(str, Sarray.getCPtr(sarray), sarray);
    }

    public static int sarrayWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Sarray sarray) {
        return JniLeptonicaJNI.sarrayWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Sarray.getCPtr(sarray), sarray);
    }

    public static int sarrayAppend(String str, Sarray sarray) {
        return JniLeptonicaJNI.sarrayAppend(str, Sarray.getCPtr(sarray), sarray);
    }

    public static Sarray getNumberedPathnamesInDirectory(String str, String str2, int i, int i2, int i3) {
        long numberedPathnamesInDirectory = JniLeptonicaJNI.getNumberedPathnamesInDirectory(str, str2, i, i2, i3);
        if (numberedPathnamesInDirectory == 0) {
            return null;
        }
        return new Sarray(numberedPathnamesInDirectory, false);
    }

    public static Sarray getSortedPathnamesInDirectory(String str, String str2, int i, int i2) {
        long sortedPathnamesInDirectory = JniLeptonicaJNI.getSortedPathnamesInDirectory(str, str2, i, i2);
        if (sortedPathnamesInDirectory == 0) {
            return null;
        }
        return new Sarray(sortedPathnamesInDirectory, false);
    }

    public static Sarray convertSortedToNumberedPathnames(Sarray sarray, int i, int i2, int i3) {
        long convertSortedToNumberedPathnames = JniLeptonicaJNI.convertSortedToNumberedPathnames(Sarray.getCPtr(sarray), sarray, i, i2, i3);
        if (convertSortedToNumberedPathnames == 0) {
            return null;
        }
        return new Sarray(convertSortedToNumberedPathnames, false);
    }

    public static Sarray getFilenamesInDirectory(String str) {
        long filenamesInDirectory = JniLeptonicaJNI.getFilenamesInDirectory(str);
        if (filenamesInDirectory == 0) {
            return null;
        }
        return new Sarray(filenamesInDirectory, false);
    }

    public static Pix pixScale(Pix pix, float f, float f2) {
        long pixScale = JniLeptonicaJNI.pixScale(Pix.getCPtr(pix), pix, f, f2);
        if (pixScale == 0) {
            return null;
        }
        return new Pix(pixScale, false);
    }

    public static Pix pixScaleToSize(Pix pix, int i, int i2) {
        long pixScaleToSize = JniLeptonicaJNI.pixScaleToSize(Pix.getCPtr(pix), pix, i, i2);
        if (pixScaleToSize == 0) {
            return null;
        }
        return new Pix(pixScaleToSize, false);
    }

    public static Pix pixScaleGeneral(Pix pix, float f, float f2, float f3, int i) {
        long pixScaleGeneral = JniLeptonicaJNI.pixScaleGeneral(Pix.getCPtr(pix), pix, f, f2, f3, i);
        if (pixScaleGeneral == 0) {
            return null;
        }
        return new Pix(pixScaleGeneral, false);
    }

    public static Pix pixScaleLI(Pix pix, float f, float f2) {
        long pixScaleLI = JniLeptonicaJNI.pixScaleLI(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleLI == 0) {
            return null;
        }
        return new Pix(pixScaleLI, false);
    }

    public static Pix pixScaleColorLI(Pix pix, float f, float f2) {
        long pixScaleColorLI = JniLeptonicaJNI.pixScaleColorLI(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleColorLI == 0) {
            return null;
        }
        return new Pix(pixScaleColorLI, false);
    }

    public static Pix pixScaleColor2xLI(Pix pix) {
        long pixScaleColor2xLI = JniLeptonicaJNI.pixScaleColor2xLI(Pix.getCPtr(pix), pix);
        if (pixScaleColor2xLI == 0) {
            return null;
        }
        return new Pix(pixScaleColor2xLI, false);
    }

    public static Pix pixScaleColor4xLI(Pix pix) {
        long pixScaleColor4xLI = JniLeptonicaJNI.pixScaleColor4xLI(Pix.getCPtr(pix), pix);
        if (pixScaleColor4xLI == 0) {
            return null;
        }
        return new Pix(pixScaleColor4xLI, false);
    }

    public static Pix pixScaleGrayLI(Pix pix, float f, float f2) {
        long pixScaleGrayLI = JniLeptonicaJNI.pixScaleGrayLI(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleGrayLI == 0) {
            return null;
        }
        return new Pix(pixScaleGrayLI, false);
    }

    public static Pix pixScaleGray2xLI(Pix pix) {
        long pixScaleGray2xLI = JniLeptonicaJNI.pixScaleGray2xLI(Pix.getCPtr(pix), pix);
        if (pixScaleGray2xLI == 0) {
            return null;
        }
        return new Pix(pixScaleGray2xLI, false);
    }

    public static Pix pixScaleGray4xLI(Pix pix) {
        long pixScaleGray4xLI = JniLeptonicaJNI.pixScaleGray4xLI(Pix.getCPtr(pix), pix);
        if (pixScaleGray4xLI == 0) {
            return null;
        }
        return new Pix(pixScaleGray4xLI, false);
    }

    public static Pix pixScaleBySampling(Pix pix, float f, float f2) {
        long pixScaleBySampling = JniLeptonicaJNI.pixScaleBySampling(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleBySampling == 0) {
            return null;
        }
        return new Pix(pixScaleBySampling, false);
    }

    public static Pix pixScaleBySamplingToSize(Pix pix, int i, int i2) {
        long pixScaleBySamplingToSize = JniLeptonicaJNI.pixScaleBySamplingToSize(Pix.getCPtr(pix), pix, i, i2);
        if (pixScaleBySamplingToSize == 0) {
            return null;
        }
        return new Pix(pixScaleBySamplingToSize, false);
    }

    public static Pix pixScaleByIntSampling(Pix pix, int i) {
        long pixScaleByIntSampling = JniLeptonicaJNI.pixScaleByIntSampling(Pix.getCPtr(pix), pix, i);
        if (pixScaleByIntSampling == 0) {
            return null;
        }
        return new Pix(pixScaleByIntSampling, false);
    }

    public static Pix pixScaleRGBToGrayFast(Pix pix, int i, int i2) {
        long pixScaleRGBToGrayFast = JniLeptonicaJNI.pixScaleRGBToGrayFast(Pix.getCPtr(pix), pix, i, i2);
        if (pixScaleRGBToGrayFast == 0) {
            return null;
        }
        return new Pix(pixScaleRGBToGrayFast, false);
    }

    public static Pix pixScaleRGBToBinaryFast(Pix pix, int i, int i2) {
        long pixScaleRGBToBinaryFast = JniLeptonicaJNI.pixScaleRGBToBinaryFast(Pix.getCPtr(pix), pix, i, i2);
        if (pixScaleRGBToBinaryFast == 0) {
            return null;
        }
        return new Pix(pixScaleRGBToBinaryFast, false);
    }

    public static Pix pixScaleGrayToBinaryFast(Pix pix, int i, int i2) {
        long pixScaleGrayToBinaryFast = JniLeptonicaJNI.pixScaleGrayToBinaryFast(Pix.getCPtr(pix), pix, i, i2);
        if (pixScaleGrayToBinaryFast == 0) {
            return null;
        }
        return new Pix(pixScaleGrayToBinaryFast, false);
    }

    public static Pix pixScaleSmooth(Pix pix, float f, float f2) {
        long pixScaleSmooth = JniLeptonicaJNI.pixScaleSmooth(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleSmooth == 0) {
            return null;
        }
        return new Pix(pixScaleSmooth, false);
    }

    public static Pix pixScaleRGBToGray2(Pix pix, float f, float f2, float f3) {
        long pixScaleRGBToGray2 = JniLeptonicaJNI.pixScaleRGBToGray2(Pix.getCPtr(pix), pix, f, f2, f3);
        if (pixScaleRGBToGray2 == 0) {
            return null;
        }
        return new Pix(pixScaleRGBToGray2, false);
    }

    public static Pix pixScaleAreaMap(Pix pix, float f, float f2) {
        long pixScaleAreaMap = JniLeptonicaJNI.pixScaleAreaMap(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleAreaMap == 0) {
            return null;
        }
        return new Pix(pixScaleAreaMap, false);
    }

    public static Pix pixScaleAreaMap2(Pix pix) {
        long pixScaleAreaMap2 = JniLeptonicaJNI.pixScaleAreaMap2(Pix.getCPtr(pix), pix);
        if (pixScaleAreaMap2 == 0) {
            return null;
        }
        return new Pix(pixScaleAreaMap2, false);
    }

    public static Pix pixScaleBinary(Pix pix, float f, float f2) {
        long pixScaleBinary = JniLeptonicaJNI.pixScaleBinary(Pix.getCPtr(pix), pix, f, f2);
        if (pixScaleBinary == 0) {
            return null;
        }
        return new Pix(pixScaleBinary, false);
    }

    public static Pix pixScaleToGray(Pix pix, float f) {
        long pixScaleToGray = JniLeptonicaJNI.pixScaleToGray(Pix.getCPtr(pix), pix, f);
        if (pixScaleToGray == 0) {
            return null;
        }
        return new Pix(pixScaleToGray, false);
    }

    public static Pix pixScaleToGrayFast(Pix pix, float f) {
        long pixScaleToGrayFast = JniLeptonicaJNI.pixScaleToGrayFast(Pix.getCPtr(pix), pix, f);
        if (pixScaleToGrayFast == 0) {
            return null;
        }
        return new Pix(pixScaleToGrayFast, false);
    }

    public static Pix pixScaleToGray2(Pix pix) {
        long pixScaleToGray2 = JniLeptonicaJNI.pixScaleToGray2(Pix.getCPtr(pix), pix);
        if (pixScaleToGray2 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray2, false);
    }

    public static Pix pixScaleToGray3(Pix pix) {
        long pixScaleToGray3 = JniLeptonicaJNI.pixScaleToGray3(Pix.getCPtr(pix), pix);
        if (pixScaleToGray3 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray3, false);
    }

    public static Pix pixScaleToGray4(Pix pix) {
        long pixScaleToGray4 = JniLeptonicaJNI.pixScaleToGray4(Pix.getCPtr(pix), pix);
        if (pixScaleToGray4 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray4, false);
    }

    public static Pix pixScaleToGray6(Pix pix) {
        long pixScaleToGray6 = JniLeptonicaJNI.pixScaleToGray6(Pix.getCPtr(pix), pix);
        if (pixScaleToGray6 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray6, false);
    }

    public static Pix pixScaleToGray8(Pix pix) {
        long pixScaleToGray8 = JniLeptonicaJNI.pixScaleToGray8(Pix.getCPtr(pix), pix);
        if (pixScaleToGray8 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray8, false);
    }

    public static Pix pixScaleToGray16(Pix pix) {
        long pixScaleToGray16 = JniLeptonicaJNI.pixScaleToGray16(Pix.getCPtr(pix), pix);
        if (pixScaleToGray16 == 0) {
            return null;
        }
        return new Pix(pixScaleToGray16, false);
    }

    public static Pix pixScaleToGrayMipmap(Pix pix, float f) {
        long pixScaleToGrayMipmap = JniLeptonicaJNI.pixScaleToGrayMipmap(Pix.getCPtr(pix), pix, f);
        if (pixScaleToGrayMipmap == 0) {
            return null;
        }
        return new Pix(pixScaleToGrayMipmap, false);
    }

    public static Pix pixScaleMipmap(Pix pix, Pix pix2, float f) {
        long pixScaleMipmap = JniLeptonicaJNI.pixScaleMipmap(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f);
        if (pixScaleMipmap == 0) {
            return null;
        }
        return new Pix(pixScaleMipmap, false);
    }

    public static Pix pixExpandReplicate(Pix pix, int i) {
        long pixExpandReplicate = JniLeptonicaJNI.pixExpandReplicate(Pix.getCPtr(pix), pix, i);
        if (pixExpandReplicate == 0) {
            return null;
        }
        return new Pix(pixExpandReplicate, false);
    }

    public static Pix pixScaleGray2xLIThresh(Pix pix, int i) {
        long pixScaleGray2xLIThresh = JniLeptonicaJNI.pixScaleGray2xLIThresh(Pix.getCPtr(pix), pix, i);
        if (pixScaleGray2xLIThresh == 0) {
            return null;
        }
        return new Pix(pixScaleGray2xLIThresh, false);
    }

    public static Pix pixScaleGray2xLIDither(Pix pix) {
        long pixScaleGray2xLIDither = JniLeptonicaJNI.pixScaleGray2xLIDither(Pix.getCPtr(pix), pix);
        if (pixScaleGray2xLIDither == 0) {
            return null;
        }
        return new Pix(pixScaleGray2xLIDither, false);
    }

    public static Pix pixScaleGray4xLIThresh(Pix pix, int i) {
        long pixScaleGray4xLIThresh = JniLeptonicaJNI.pixScaleGray4xLIThresh(Pix.getCPtr(pix), pix, i);
        if (pixScaleGray4xLIThresh == 0) {
            return null;
        }
        return new Pix(pixScaleGray4xLIThresh, false);
    }

    public static Pix pixScaleGray4xLIDither(Pix pix) {
        long pixScaleGray4xLIDither = JniLeptonicaJNI.pixScaleGray4xLIDither(Pix.getCPtr(pix), pix);
        if (pixScaleGray4xLIDither == 0) {
            return null;
        }
        return new Pix(pixScaleGray4xLIDither, false);
    }

    public static Pix pixScaleGrayMinMax(Pix pix, int i, int i2, int i3) {
        long pixScaleGrayMinMax = JniLeptonicaJNI.pixScaleGrayMinMax(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixScaleGrayMinMax == 0) {
            return null;
        }
        return new Pix(pixScaleGrayMinMax, false);
    }

    public static Pix pixScaleGrayMinMax2(Pix pix, int i) {
        long pixScaleGrayMinMax2 = JniLeptonicaJNI.pixScaleGrayMinMax2(Pix.getCPtr(pix), pix, i);
        if (pixScaleGrayMinMax2 == 0) {
            return null;
        }
        return new Pix(pixScaleGrayMinMax2, false);
    }

    public static Pix pixScaleGrayRankCascade(Pix pix, int i, int i2, int i3, int i4) {
        long pixScaleGrayRankCascade = JniLeptonicaJNI.pixScaleGrayRankCascade(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixScaleGrayRankCascade == 0) {
            return null;
        }
        return new Pix(pixScaleGrayRankCascade, false);
    }

    public static Pix pixScaleGrayRank2(Pix pix, int i) {
        long pixScaleGrayRank2 = JniLeptonicaJNI.pixScaleGrayRank2(Pix.getCPtr(pix), pix, i);
        if (pixScaleGrayRank2 == 0) {
            return null;
        }
        return new Pix(pixScaleGrayRank2, false);
    }

    public static int pixScaleAndTransferAlpha(Pix pix, Pix pix2, float f, float f2) {
        return JniLeptonicaJNI.pixScaleAndTransferAlpha(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, f2);
    }

    public static Pix pixScaleWithAlpha(Pix pix, float f, float f2, Pix pix2, float f3) {
        long pixScaleWithAlpha = JniLeptonicaJNI.pixScaleWithAlpha(Pix.getCPtr(pix), pix, f, f2, Pix.getCPtr(pix2), pix2, f3);
        if (pixScaleWithAlpha == 0) {
            return null;
        }
        return new Pix(pixScaleWithAlpha, false);
    }

    public static void scaleColorLILow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.scaleColorLILow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void scaleGrayLILow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.scaleGrayLILow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void scaleColor2xLILow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleColor2xLILow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static void scaleColor2xLILineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleColor2xLILineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static void scaleGray2xLILow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleGray2xLILow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static void scaleGray2xLILineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleGray2xLILineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static void scaleGray4xLILow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleGray4xLILow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static void scaleGray4xLILineLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i2, int i3, int i4) {
        JniLeptonicaJNI.scaleGray4xLILineLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i2, i3, i4);
    }

    public static int scaleBySamplingLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6, int i7) {
        return JniLeptonicaJNI.scaleBySamplingLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6, i7);
    }

    public static int scaleSmoothLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6, int i7, int i8) {
        return JniLeptonicaJNI.scaleSmoothLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6, i7, i8);
    }

    public static void scaleRGBToGray2Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, float f, float f2, float f3) {
        JniLeptonicaJNI.scaleRGBToGray2Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, f, f2, f3);
    }

    public static void scaleColorAreaMapLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.scaleColorAreaMapLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void scaleGrayAreaMapLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        JniLeptonicaJNI.scaleGrayAreaMapLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void scaleAreaMapLow2(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.scaleAreaMapLow2(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static int scaleBinaryLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5, int i6) {
        return JniLeptonicaJNI.scaleBinaryLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5, i6);
    }

    public static void scaleToGray2Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.scaleToGray2Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public static SWIGTYPE_p_unsigned_int makeSumTabSG2() {
        long makeSumTabSG2 = JniLeptonicaJNI.makeSumTabSG2();
        if (makeSumTabSG2 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(makeSumTabSG2, false);
    }

    public static SWIGTYPE_p_unsigned_char makeValTabSG2() {
        long makeValTabSG2 = JniLeptonicaJNI.makeValTabSG2();
        if (makeValTabSG2 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeValTabSG2, false);
    }

    public static void scaleToGray3Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.scaleToGray3Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public static SWIGTYPE_p_unsigned_int makeSumTabSG3() {
        long makeSumTabSG3 = JniLeptonicaJNI.makeSumTabSG3();
        if (makeSumTabSG3 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(makeSumTabSG3, false);
    }

    public static SWIGTYPE_p_unsigned_char makeValTabSG3() {
        long makeValTabSG3 = JniLeptonicaJNI.makeValTabSG3();
        if (makeValTabSG3 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeValTabSG3, false);
    }

    public static void scaleToGray4Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.scaleToGray4Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public static SWIGTYPE_p_unsigned_int makeSumTabSG4() {
        long makeSumTabSG4 = JniLeptonicaJNI.makeSumTabSG4();
        if (makeSumTabSG4 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_int(makeSumTabSG4, false);
    }

    public static SWIGTYPE_p_unsigned_char makeValTabSG4() {
        long makeValTabSG4 = JniLeptonicaJNI.makeValTabSG4();
        if (makeValTabSG4 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeValTabSG4, false);
    }

    public static void scaleToGray6Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.scaleToGray6Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public static SWIGTYPE_p_unsigned_char makeValTabSG6() {
        long makeValTabSG6 = JniLeptonicaJNI.makeValTabSG6();
        if (makeValTabSG6 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeValTabSG6, false);
    }

    public static void scaleToGray8Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char) {
        JniLeptonicaJNI.scaleToGray8Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char));
    }

    public static SWIGTYPE_p_unsigned_char makeValTabSG8() {
        long makeValTabSG8 = JniLeptonicaJNI.makeValTabSG8();
        if (makeValTabSG8 == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(makeValTabSG8, false);
    }

    public static void scaleToGray16Low(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.scaleToGray16Low(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int scaleMipmapLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int3, int i5, float f) {
        return JniLeptonicaJNI.scaleMipmapLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int3), i5, f);
    }

    public static Pix pixSeedfillBinary(Pix pix, Pix pix2, Pix pix3, int i) {
        long pixSeedfillBinary = JniLeptonicaJNI.pixSeedfillBinary(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i);
        if (pixSeedfillBinary == 0) {
            return null;
        }
        return new Pix(pixSeedfillBinary, false);
    }

    public static Pix pixSeedfillBinaryRestricted(Pix pix, Pix pix2, Pix pix3, int i, int i2, int i3) {
        long pixSeedfillBinaryRestricted = JniLeptonicaJNI.pixSeedfillBinaryRestricted(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2, i3);
        if (pixSeedfillBinaryRestricted == 0) {
            return null;
        }
        return new Pix(pixSeedfillBinaryRestricted, false);
    }

    public static Pix pixHolesByFilling(Pix pix, int i) {
        long pixHolesByFilling = JniLeptonicaJNI.pixHolesByFilling(Pix.getCPtr(pix), pix, i);
        if (pixHolesByFilling == 0) {
            return null;
        }
        return new Pix(pixHolesByFilling, false);
    }

    public static Pix pixFillClosedBorders(Pix pix, int i) {
        long pixFillClosedBorders = JniLeptonicaJNI.pixFillClosedBorders(Pix.getCPtr(pix), pix, i);
        if (pixFillClosedBorders == 0) {
            return null;
        }
        return new Pix(pixFillClosedBorders, false);
    }

    public static Pix pixExtractBorderConnComps(Pix pix, int i) {
        long pixExtractBorderConnComps = JniLeptonicaJNI.pixExtractBorderConnComps(Pix.getCPtr(pix), pix, i);
        if (pixExtractBorderConnComps == 0) {
            return null;
        }
        return new Pix(pixExtractBorderConnComps, false);
    }

    public static Pix pixRemoveBorderConnComps(Pix pix, int i) {
        long pixRemoveBorderConnComps = JniLeptonicaJNI.pixRemoveBorderConnComps(Pix.getCPtr(pix), pix, i);
        if (pixRemoveBorderConnComps == 0) {
            return null;
        }
        return new Pix(pixRemoveBorderConnComps, false);
    }

    public static Pix pixFillBgFromBorder(Pix pix, int i) {
        long pixFillBgFromBorder = JniLeptonicaJNI.pixFillBgFromBorder(Pix.getCPtr(pix), pix, i);
        if (pixFillBgFromBorder == 0) {
            return null;
        }
        return new Pix(pixFillBgFromBorder, false);
    }

    public static Pix pixFillHolesToBoundingRect(Pix pix, int i, float f, float f2) {
        long pixFillHolesToBoundingRect = JniLeptonicaJNI.pixFillHolesToBoundingRect(Pix.getCPtr(pix), pix, i, f, f2);
        if (pixFillHolesToBoundingRect == 0) {
            return null;
        }
        return new Pix(pixFillHolesToBoundingRect, false);
    }

    public static int pixSeedfillGray(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSeedfillGray(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static int pixSeedfillGrayInv(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSeedfillGrayInv(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static int pixSeedfillGraySimple(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSeedfillGraySimple(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static int pixSeedfillGrayInvSimple(Pix pix, Pix pix2, int i) {
        return JniLeptonicaJNI.pixSeedfillGrayInvSimple(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i);
    }

    public static Pix pixSeedfillGrayBasin(Pix pix, Pix pix2, int i, int i2) {
        long pixSeedfillGrayBasin = JniLeptonicaJNI.pixSeedfillGrayBasin(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (pixSeedfillGrayBasin == 0) {
            return null;
        }
        return new Pix(pixSeedfillGrayBasin, false);
    }

    public static Pix pixDistanceFunction(Pix pix, int i, int i2, int i3) {
        long pixDistanceFunction = JniLeptonicaJNI.pixDistanceFunction(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixDistanceFunction == 0) {
            return null;
        }
        return new Pix(pixDistanceFunction, false);
    }

    public static Pix pixSeedspread(Pix pix, int i) {
        long pixSeedspread = JniLeptonicaJNI.pixSeedspread(Pix.getCPtr(pix), pix, i);
        if (pixSeedspread == 0) {
            return null;
        }
        return new Pix(pixSeedspread, false);
    }

    public static int pixLocalExtrema(Pix pix, int i, int i2, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixLocalExtrema(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static int pixSelectedLocalExtrema(Pix pix, int i, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix2) {
        return JniLeptonicaJNI.pixSelectedLocalExtrema(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix), SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix2));
    }

    public static Pix pixFindEqualValues(Pix pix, Pix pix2) {
        long pixFindEqualValues = JniLeptonicaJNI.pixFindEqualValues(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2);
        if (pixFindEqualValues == 0) {
            return null;
        }
        return new Pix(pixFindEqualValues, false);
    }

    public static int pixSelectMinInConnComp(Pix pix, Pix pix2, SWIGTYPE_p_p_Pta sWIGTYPE_p_p_Pta, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.pixSelectMinInConnComp(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, SWIGTYPE_p_p_Pta.getCPtr(sWIGTYPE_p_p_Pta), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Pix pixRemoveSeededComponents(Pix pix, Pix pix2, Pix pix3, int i, int i2) {
        long pixRemoveSeededComponents = JniLeptonicaJNI.pixRemoveSeededComponents(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, Pix.getCPtr(pix3), pix3, i, i2);
        if (pixRemoveSeededComponents == 0) {
            return null;
        }
        return new Pix(pixRemoveSeededComponents, false);
    }

    public static void seedfillBinaryLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i3, int i4, int i5) {
        JniLeptonicaJNI.seedfillBinaryLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i3, i4, i5);
    }

    public static void seedfillGrayLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.seedfillGrayLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static void seedfillGrayInvLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.seedfillGrayInvLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static void seedfillGrayLowSimple(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.seedfillGrayLowSimple(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static void seedfillGrayInvLowSimple(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.seedfillGrayInvLowSimple(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static void distanceFunctionLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, int i4, int i5) {
        JniLeptonicaJNI.distanceFunctionLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, i4, i5);
    }

    public static void seedspreadLow(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, int i, int i2, int i3, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int2, int i4, int i5) {
        JniLeptonicaJNI.seedspreadLow(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), i, i2, i3, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int2), i4, i5);
    }

    public static Sela selaCreate(int i) {
        long selaCreate = JniLeptonicaJNI.selaCreate(i);
        if (selaCreate == 0) {
            return null;
        }
        return new Sela(selaCreate, false);
    }

    public static void selaDestroy(SWIGTYPE_p_p_Sela sWIGTYPE_p_p_Sela) {
        JniLeptonicaJNI.selaDestroy(SWIGTYPE_p_p_Sela.getCPtr(sWIGTYPE_p_p_Sela));
    }

    public static Sel selCreate(int i, int i2, String str) {
        long selCreate = JniLeptonicaJNI.selCreate(i, i2, str);
        if (selCreate == 0) {
            return null;
        }
        return new Sel(selCreate, false);
    }

    public static void selDestroy(SWIGTYPE_p_p_Sel sWIGTYPE_p_p_Sel) {
        JniLeptonicaJNI.selDestroy(SWIGTYPE_p_p_Sel.getCPtr(sWIGTYPE_p_p_Sel));
    }

    public static Sel selCopy(Sel sel) {
        long selCopy = JniLeptonicaJNI.selCopy(Sel.getCPtr(sel), sel);
        if (selCopy == 0) {
            return null;
        }
        return new Sel(selCopy, false);
    }

    public static Sel selCreateBrick(int i, int i2, int i3, int i4, int i5) {
        long selCreateBrick = JniLeptonicaJNI.selCreateBrick(i, i2, i3, i4, i5);
        if (selCreateBrick == 0) {
            return null;
        }
        return new Sel(selCreateBrick, false);
    }

    public static Sel selCreateComb(int i, int i2, int i3) {
        long selCreateComb = JniLeptonicaJNI.selCreateComb(i, i2, i3);
        if (selCreateComb == 0) {
            return null;
        }
        return new Sel(selCreateComb, false);
    }

    public static SWIGTYPE_p_p_int create2dIntArray(int i, int i2) {
        long create2dIntArray = JniLeptonicaJNI.create2dIntArray(i, i2);
        if (create2dIntArray == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_int(create2dIntArray, false);
    }

    public static int selaAddSel(Sela sela, Sel sel, String str, int i) {
        return JniLeptonicaJNI.selaAddSel(Sela.getCPtr(sela), sela, Sel.getCPtr(sel), sel, str, i);
    }

    public static int selaGetCount(Sela sela) {
        return JniLeptonicaJNI.selaGetCount(Sela.getCPtr(sela), sela);
    }

    public static Sel selaGetSel(Sela sela, int i) {
        long selaGetSel = JniLeptonicaJNI.selaGetSel(Sela.getCPtr(sela), sela, i);
        if (selaGetSel == 0) {
            return null;
        }
        return new Sel(selaGetSel, false);
    }

    public static String selGetName(Sel sel) {
        return JniLeptonicaJNI.selGetName(Sel.getCPtr(sel), sel);
    }

    public static int selSetName(Sel sel, String str) {
        return JniLeptonicaJNI.selSetName(Sel.getCPtr(sel), sel, str);
    }

    public static int selaFindSelByName(Sela sela, String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_p_Sel sWIGTYPE_p_p_Sel) {
        return JniLeptonicaJNI.selaFindSelByName(Sela.getCPtr(sela), sela, str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_p_Sel.getCPtr(sWIGTYPE_p_p_Sel));
    }

    public static int selGetElement(Sel sel, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.selGetElement(Sel.getCPtr(sel), sel, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int selSetElement(Sel sel, int i, int i2, int i3) {
        return JniLeptonicaJNI.selSetElement(Sel.getCPtr(sel), sel, i, i2, i3);
    }

    public static int selGetParameters(Sel sel, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.selGetParameters(Sel.getCPtr(sel), sel, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static int selSetOrigin(Sel sel, int i, int i2) {
        return JniLeptonicaJNI.selSetOrigin(Sel.getCPtr(sel), sel, i, i2);
    }

    public static int selGetTypeAtOrigin(Sel sel, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.selGetTypeAtOrigin(Sel.getCPtr(sel), sel, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static String selaGetBrickName(Sela sela, int i, int i2) {
        return JniLeptonicaJNI.selaGetBrickName(Sela.getCPtr(sela), sela, i, i2);
    }

    public static String selaGetCombName(Sela sela, int i, int i2) {
        return JniLeptonicaJNI.selaGetCombName(Sela.getCPtr(sela), sela, i, i2);
    }

    public static int getCompositeParameters(int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_char sWIGTYPE_p_p_char2, SWIGTYPE_p_p_char sWIGTYPE_p_p_char3, SWIGTYPE_p_p_char sWIGTYPE_p_p_char4) {
        return JniLeptonicaJNI.getCompositeParameters(i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char2), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char3), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char4));
    }

    public static Sarray selaGetSelnames(Sela sela) {
        long selaGetSelnames = JniLeptonicaJNI.selaGetSelnames(Sela.getCPtr(sela), sela);
        if (selaGetSelnames == 0) {
            return null;
        }
        return new Sarray(selaGetSelnames, false);
    }

    public static int selFindMaxTranslations(Sel sel, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4) {
        return JniLeptonicaJNI.selFindMaxTranslations(Sel.getCPtr(sel), sel, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4));
    }

    public static Sel selRotateOrth(Sel sel, int i) {
        long selRotateOrth = JniLeptonicaJNI.selRotateOrth(Sel.getCPtr(sel), sel, i);
        if (selRotateOrth == 0) {
            return null;
        }
        return new Sel(selRotateOrth, false);
    }

    public static Sela selaRead(String str) {
        long selaRead = JniLeptonicaJNI.selaRead(str);
        if (selaRead == 0) {
            return null;
        }
        return new Sela(selaRead, false);
    }

    public static Sela selaReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long selaReadStream = JniLeptonicaJNI.selaReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (selaReadStream == 0) {
            return null;
        }
        return new Sela(selaReadStream, false);
    }

    public static Sel selRead(String str) {
        long selRead = JniLeptonicaJNI.selRead(str);
        if (selRead == 0) {
            return null;
        }
        return new Sel(selRead, false);
    }

    public static Sel selReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long selReadStream = JniLeptonicaJNI.selReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (selReadStream == 0) {
            return null;
        }
        return new Sel(selReadStream, false);
    }

    public static int selaWrite(String str, Sela sela) {
        return JniLeptonicaJNI.selaWrite(str, Sela.getCPtr(sela), sela);
    }

    public static int selaWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Sela sela) {
        return JniLeptonicaJNI.selaWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Sela.getCPtr(sela), sela);
    }

    public static int selWrite(String str, Sel sel) {
        return JniLeptonicaJNI.selWrite(str, Sel.getCPtr(sel), sel);
    }

    public static int selWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Sel sel) {
        return JniLeptonicaJNI.selWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Sel.getCPtr(sel), sel);
    }

    public static Sel selCreateFromString(String str, int i, int i2, String str2) {
        long selCreateFromString = JniLeptonicaJNI.selCreateFromString(str, i, i2, str2);
        if (selCreateFromString == 0) {
            return null;
        }
        return new Sel(selCreateFromString, false);
    }

    public static String selPrintToString(Sel sel) {
        return JniLeptonicaJNI.selPrintToString(Sel.getCPtr(sel), sel);
    }

    public static Sela selaCreateFromFile(String str) {
        long selaCreateFromFile = JniLeptonicaJNI.selaCreateFromFile(str);
        if (selaCreateFromFile == 0) {
            return null;
        }
        return new Sela(selaCreateFromFile, false);
    }

    public static Sel selCreateFromPta(Pta pta, int i, int i2, String str) {
        long selCreateFromPta = JniLeptonicaJNI.selCreateFromPta(Pta.getCPtr(pta), pta, i, i2, str);
        if (selCreateFromPta == 0) {
            return null;
        }
        return new Sel(selCreateFromPta, false);
    }

    public static Sel selCreateFromPix(Pix pix, int i, int i2, String str) {
        long selCreateFromPix = JniLeptonicaJNI.selCreateFromPix(Pix.getCPtr(pix), pix, i, i2, str);
        if (selCreateFromPix == 0) {
            return null;
        }
        return new Sel(selCreateFromPix, false);
    }

    public static Sel selReadFromColorImage(String str) {
        long selReadFromColorImage = JniLeptonicaJNI.selReadFromColorImage(str);
        if (selReadFromColorImage == 0) {
            return null;
        }
        return new Sel(selReadFromColorImage, false);
    }

    public static Sel selCreateFromColorPix(Pix pix, String str) {
        long selCreateFromColorPix = JniLeptonicaJNI.selCreateFromColorPix(Pix.getCPtr(pix), pix, str);
        if (selCreateFromColorPix == 0) {
            return null;
        }
        return new Sel(selCreateFromColorPix, false);
    }

    public static Pix selDisplayInPix(Sel sel, int i, int i2) {
        long selDisplayInPix = JniLeptonicaJNI.selDisplayInPix(Sel.getCPtr(sel), sel, i, i2);
        if (selDisplayInPix == 0) {
            return null;
        }
        return new Pix(selDisplayInPix, false);
    }

    public static Pix selaDisplayInPix(Sela sela, int i, int i2, int i3, int i4) {
        long selaDisplayInPix = JniLeptonicaJNI.selaDisplayInPix(Sela.getCPtr(sela), sela, i, i2, i3, i4);
        if (selaDisplayInPix == 0) {
            return null;
        }
        return new Pix(selaDisplayInPix, false);
    }

    public static Sela selaAddBasic(Sela sela) {
        long selaAddBasic = JniLeptonicaJNI.selaAddBasic(Sela.getCPtr(sela), sela);
        if (selaAddBasic == 0) {
            return null;
        }
        return new Sela(selaAddBasic, false);
    }

    public static Sela selaAddHitMiss(Sela sela) {
        long selaAddHitMiss = JniLeptonicaJNI.selaAddHitMiss(Sela.getCPtr(sela), sela);
        if (selaAddHitMiss == 0) {
            return null;
        }
        return new Sela(selaAddHitMiss, false);
    }

    public static Sela selaAddDwaLinear(Sela sela) {
        long selaAddDwaLinear = JniLeptonicaJNI.selaAddDwaLinear(Sela.getCPtr(sela), sela);
        if (selaAddDwaLinear == 0) {
            return null;
        }
        return new Sela(selaAddDwaLinear, false);
    }

    public static Sela selaAddDwaCombs(Sela sela) {
        long selaAddDwaCombs = JniLeptonicaJNI.selaAddDwaCombs(Sela.getCPtr(sela), sela);
        if (selaAddDwaCombs == 0) {
            return null;
        }
        return new Sela(selaAddDwaCombs, false);
    }

    public static Sela selaAddCrossJunctions(Sela sela, float f, float f2, int i, int i2) {
        long selaAddCrossJunctions = JniLeptonicaJNI.selaAddCrossJunctions(Sela.getCPtr(sela), sela, f, f2, i, i2);
        if (selaAddCrossJunctions == 0) {
            return null;
        }
        return new Sela(selaAddCrossJunctions, false);
    }

    public static Sela selaAddTJunctions(Sela sela, float f, float f2, int i, int i2) {
        long selaAddTJunctions = JniLeptonicaJNI.selaAddTJunctions(Sela.getCPtr(sela), sela, f, f2, i, i2);
        if (selaAddTJunctions == 0) {
            return null;
        }
        return new Sela(selaAddTJunctions, false);
    }

    public static Sel pixGenerateSelWithRuns(Pix pix, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixGenerateSelWithRuns = JniLeptonicaJNI.pixGenerateSelWithRuns(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6, i7, i8, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixGenerateSelWithRuns == 0) {
            return null;
        }
        return new Sel(pixGenerateSelWithRuns, false);
    }

    public static Sel pixGenerateSelRandom(Pix pix, float f, float f2, int i, int i2, int i3, int i4, int i5, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixGenerateSelRandom = JniLeptonicaJNI.pixGenerateSelRandom(Pix.getCPtr(pix), pix, f, f2, i, i2, i3, i4, i5, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixGenerateSelRandom == 0) {
            return null;
        }
        return new Sel(pixGenerateSelRandom, false);
    }

    public static Sel pixGenerateSelBoundary(Pix pix, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, SWIGTYPE_p_p_Pix sWIGTYPE_p_p_Pix) {
        long pixGenerateSelBoundary = JniLeptonicaJNI.pixGenerateSelBoundary(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6, i7, i8, SWIGTYPE_p_p_Pix.getCPtr(sWIGTYPE_p_p_Pix));
        if (pixGenerateSelBoundary == 0) {
            return null;
        }
        return new Sel(pixGenerateSelBoundary, false);
    }

    public static Numa pixGetRunCentersOnLine(Pix pix, int i, int i2, int i3) {
        long pixGetRunCentersOnLine = JniLeptonicaJNI.pixGetRunCentersOnLine(Pix.getCPtr(pix), pix, i, i2, i3);
        if (pixGetRunCentersOnLine == 0) {
            return null;
        }
        return new Numa(pixGetRunCentersOnLine, false);
    }

    public static Numa pixGetRunsOnLine(Pix pix, int i, int i2, int i3, int i4) {
        long pixGetRunsOnLine = JniLeptonicaJNI.pixGetRunsOnLine(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixGetRunsOnLine == 0) {
            return null;
        }
        return new Numa(pixGetRunsOnLine, false);
    }

    public static Pta pixSubsampleBoundaryPixels(Pix pix, int i) {
        long pixSubsampleBoundaryPixels = JniLeptonicaJNI.pixSubsampleBoundaryPixels(Pix.getCPtr(pix), pix, i);
        if (pixSubsampleBoundaryPixels == 0) {
            return null;
        }
        return new Pta(pixSubsampleBoundaryPixels, false);
    }

    public static int adjacentOnPixelInRaster(Pix pix, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.adjacentOnPixelInRaster(Pix.getCPtr(pix), pix, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pix pixDisplayHitMissSel(Pix pix, Sel sel, int i, long j, long j2) {
        long pixDisplayHitMissSel = JniLeptonicaJNI.pixDisplayHitMissSel(Pix.getCPtr(pix), pix, Sel.getCPtr(sel), sel, i, j, j2);
        if (pixDisplayHitMissSel == 0) {
            return null;
        }
        return new Pix(pixDisplayHitMissSel, false);
    }

    public static Pix pixHShear(Pix pix, Pix pix2, int i, float f, int i2) {
        long pixHShear = JniLeptonicaJNI.pixHShear(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, f, i2);
        if (pixHShear == 0) {
            return null;
        }
        return new Pix(pixHShear, false);
    }

    public static Pix pixVShear(Pix pix, Pix pix2, int i, float f, int i2) {
        long pixVShear = JniLeptonicaJNI.pixVShear(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, f, i2);
        if (pixVShear == 0) {
            return null;
        }
        return new Pix(pixVShear, false);
    }

    public static Pix pixHShearCorner(Pix pix, Pix pix2, float f, int i) {
        long pixHShearCorner = JniLeptonicaJNI.pixHShearCorner(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixHShearCorner == 0) {
            return null;
        }
        return new Pix(pixHShearCorner, false);
    }

    public static Pix pixVShearCorner(Pix pix, Pix pix2, float f, int i) {
        long pixVShearCorner = JniLeptonicaJNI.pixVShearCorner(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixVShearCorner == 0) {
            return null;
        }
        return new Pix(pixVShearCorner, false);
    }

    public static Pix pixHShearCenter(Pix pix, Pix pix2, float f, int i) {
        long pixHShearCenter = JniLeptonicaJNI.pixHShearCenter(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixHShearCenter == 0) {
            return null;
        }
        return new Pix(pixHShearCenter, false);
    }

    public static Pix pixVShearCenter(Pix pix, Pix pix2, float f, int i) {
        long pixVShearCenter = JniLeptonicaJNI.pixVShearCenter(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, i);
        if (pixVShearCenter == 0) {
            return null;
        }
        return new Pix(pixVShearCenter, false);
    }

    public static int pixHShearIP(Pix pix, int i, float f, int i2) {
        return JniLeptonicaJNI.pixHShearIP(Pix.getCPtr(pix), pix, i, f, i2);
    }

    public static int pixVShearIP(Pix pix, int i, float f, int i2) {
        return JniLeptonicaJNI.pixVShearIP(Pix.getCPtr(pix), pix, i, f, i2);
    }

    public static Pix pixHShearLI(Pix pix, int i, float f, int i2) {
        long pixHShearLI = JniLeptonicaJNI.pixHShearLI(Pix.getCPtr(pix), pix, i, f, i2);
        if (pixHShearLI == 0) {
            return null;
        }
        return new Pix(pixHShearLI, false);
    }

    public static Pix pixVShearLI(Pix pix, int i, float f, int i2) {
        long pixVShearLI = JniLeptonicaJNI.pixVShearLI(Pix.getCPtr(pix), pix, i, f, i2);
        if (pixVShearLI == 0) {
            return null;
        }
        return new Pix(pixVShearLI, false);
    }

    public static Pix pixDeskew(Pix pix, int i) {
        long pixDeskew = JniLeptonicaJNI.pixDeskew(Pix.getCPtr(pix), pix, i);
        if (pixDeskew == 0) {
            return null;
        }
        return new Pix(pixDeskew, false);
    }

    public static Pix pixFindSkewAndDeskew(Pix pix, int i, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        long pixFindSkewAndDeskew = JniLeptonicaJNI.pixFindSkewAndDeskew(Pix.getCPtr(pix), pix, i, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
        if (pixFindSkewAndDeskew == 0) {
            return null;
        }
        return new Pix(pixFindSkewAndDeskew, false);
    }

    public static Pix pixDeskewGeneral(Pix pix, int i, float f, float f2, int i2, int i3, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        long pixDeskewGeneral = JniLeptonicaJNI.pixDeskewGeneral(Pix.getCPtr(pix), pix, i, f, f2, i2, i3, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
        if (pixDeskewGeneral == 0) {
            return null;
        }
        return new Pix(pixDeskewGeneral, false);
    }

    public static int pixFindSkew(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2) {
        return JniLeptonicaJNI.pixFindSkew(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2));
    }

    public static int pixFindSkewSweep(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, int i, float f, float f2) {
        return JniLeptonicaJNI.pixFindSkewSweep(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), i, f, f2);
    }

    public static int pixFindSkewSweepAndSearch(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, int i, int i2, float f, float f2, float f3) {
        return JniLeptonicaJNI.pixFindSkewSweepAndSearch(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), i, i2, f, f2, f3);
    }

    public static int pixFindSkewSweepAndSearchScore(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, int i, int i2, float f, float f2, float f3, float f4) {
        return JniLeptonicaJNI.pixFindSkewSweepAndSearchScore(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), i, i2, f, f2, f3, f4);
    }

    public static int pixFindSkewSweepAndSearchScorePivot(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3, int i, int i2, float f, float f2, float f3, float f4, int i3) {
        return JniLeptonicaJNI.pixFindSkewSweepAndSearchScorePivot(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3), i, i2, f, f2, f3, f4, i3);
    }

    public static int pixFindSkewOrthogonalRange(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, int i, int i2, float f, float f2, float f3, float f4) {
        return JniLeptonicaJNI.pixFindSkewOrthogonalRange(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), i, i2, f, f2, f3, f4);
    }

    public static int pixFindDifferentialSquareSum(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float) {
        return JniLeptonicaJNI.pixFindDifferentialSquareSum(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float));
    }

    public static int pixFindNormalizedSquareSum(Pix pix, SWIGTYPE_p_float sWIGTYPE_p_float, SWIGTYPE_p_float sWIGTYPE_p_float2, SWIGTYPE_p_float sWIGTYPE_p_float3) {
        return JniLeptonicaJNI.pixFindNormalizedSquareSum(Pix.getCPtr(pix), pix, SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float2), SWIGTYPE_p_float.getCPtr(sWIGTYPE_p_float3));
    }

    public static Pix pixReadStreamSpix(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamSpix = JniLeptonicaJNI.pixReadStreamSpix(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamSpix == 0) {
            return null;
        }
        return new Pix(pixReadStreamSpix, false);
    }

    public static int readHeaderSpix(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.readHeaderSpix(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int freadHeaderSpix(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.freadHeaderSpix(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int sreadHeaderSpix(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5) {
        return JniLeptonicaJNI.sreadHeaderSpix(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5));
    }

    public static int pixWriteStreamSpix(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix) {
        return JniLeptonicaJNI.pixWriteStreamSpix(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix);
    }

    public static Pix pixReadMemSpix(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemSpix = JniLeptonicaJNI.pixReadMemSpix(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemSpix == 0) {
            return null;
        }
        return new Pix(pixReadMemSpix, false);
    }

    public static int pixWriteMemSpix(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix) {
        return JniLeptonicaJNI.pixWriteMemSpix(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix);
    }

    public static int pixSerializeToMemory(Pix pix, SWIGTYPE_p_p_unsigned_int sWIGTYPE_p_p_unsigned_int, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        return JniLeptonicaJNI.pixSerializeToMemory(Pix.getCPtr(pix), pix, SWIGTYPE_p_p_unsigned_int.getCPtr(sWIGTYPE_p_p_unsigned_int), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
    }

    public static Pix pixDeserializeFromMemory(SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, long j) {
        long pixDeserializeFromMemory = JniLeptonicaJNI.pixDeserializeFromMemory(SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), j);
        if (pixDeserializeFromMemory == 0) {
            return null;
        }
        return new Pix(pixDeserializeFromMemory, false);
    }

    public static L_Stack lstackCreate(int i) {
        long lstackCreate = JniLeptonicaJNI.lstackCreate(i);
        if (lstackCreate == 0) {
            return null;
        }
        return new L_Stack(lstackCreate, false);
    }

    public static void lstackDestroy(SWIGTYPE_p_p_L_Stack sWIGTYPE_p_p_L_Stack, int i) {
        JniLeptonicaJNI.lstackDestroy(SWIGTYPE_p_p_L_Stack.getCPtr(sWIGTYPE_p_p_L_Stack), i);
    }

    public static int lstackAdd(L_Stack l_Stack, SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.lstackAdd(L_Stack.getCPtr(l_Stack), l_Stack, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static SWIGTYPE_p_void lstackRemove(L_Stack l_Stack) {
        long lstackRemove = JniLeptonicaJNI.lstackRemove(L_Stack.getCPtr(l_Stack), l_Stack);
        if (lstackRemove == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(lstackRemove, false);
    }

    public static int lstackGetCount(L_Stack l_Stack) {
        return JniLeptonicaJNI.lstackGetCount(L_Stack.getCPtr(l_Stack), l_Stack);
    }

    public static int lstackPrint(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, L_Stack l_Stack) {
        return JniLeptonicaJNI.lstackPrint(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), L_Stack.getCPtr(l_Stack), l_Stack);
    }

    public static SWIGTYPE_p_int sudokuReadFile(String str) {
        long sudokuReadFile = JniLeptonicaJNI.sudokuReadFile(str);
        if (sudokuReadFile == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(sudokuReadFile, false);
    }

    public static SWIGTYPE_p_int sudokuReadString(String str) {
        long sudokuReadString = JniLeptonicaJNI.sudokuReadString(str);
        if (sudokuReadString == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(sudokuReadString, false);
    }

    public static L_Sudoku sudokuCreate(SWIGTYPE_p_int sWIGTYPE_p_int) {
        long sudokuCreate = JniLeptonicaJNI.sudokuCreate(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (sudokuCreate == 0) {
            return null;
        }
        return new L_Sudoku(sudokuCreate, false);
    }

    public static void sudokuDestroy(SWIGTYPE_p_p_L_Sudoku sWIGTYPE_p_p_L_Sudoku) {
        JniLeptonicaJNI.sudokuDestroy(SWIGTYPE_p_p_L_Sudoku.getCPtr(sWIGTYPE_p_p_L_Sudoku));
    }

    public static int sudokuSolve(L_Sudoku l_Sudoku) {
        return JniLeptonicaJNI.sudokuSolve(L_Sudoku.getCPtr(l_Sudoku), l_Sudoku);
    }

    public static int sudokuTestUniqueness(SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.sudokuTestUniqueness(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static L_Sudoku sudokuGenerate(SWIGTYPE_p_int sWIGTYPE_p_int, int i, int i2, int i3) {
        long sudokuGenerate = JniLeptonicaJNI.sudokuGenerate(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), i, i2, i3);
        if (sudokuGenerate == 0) {
            return null;
        }
        return new L_Sudoku(sudokuGenerate, false);
    }

    public static int sudokuOutput(L_Sudoku l_Sudoku, int i) {
        return JniLeptonicaJNI.sudokuOutput(L_Sudoku.getCPtr(l_Sudoku), l_Sudoku, i);
    }

    public static Pix pixAddSingleTextblock(Pix pix, L_Bmf l_Bmf, String str, long j, int i, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long pixAddSingleTextblock = JniLeptonicaJNI.pixAddSingleTextblock(Pix.getCPtr(pix), pix, L_Bmf.getCPtr(l_Bmf), l_Bmf, str, j, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (pixAddSingleTextblock == 0) {
            return null;
        }
        return new Pix(pixAddSingleTextblock, false);
    }

    public static Pix pixAddSingleTextline(Pix pix, L_Bmf l_Bmf, String str, long j, int i) {
        long pixAddSingleTextline = JniLeptonicaJNI.pixAddSingleTextline(Pix.getCPtr(pix), pix, L_Bmf.getCPtr(l_Bmf), l_Bmf, str, j, i);
        if (pixAddSingleTextline == 0) {
            return null;
        }
        return new Pix(pixAddSingleTextline, false);
    }

    public static int pixSetTextblock(Pix pix, L_Bmf l_Bmf, String str, long j, int i, int i2, int i3, int i4, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.pixSetTextblock(Pix.getCPtr(pix), pix, L_Bmf.getCPtr(l_Bmf), l_Bmf, str, j, i, i2, i3, i4, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int pixSetTextline(Pix pix, L_Bmf l_Bmf, String str, long j, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.pixSetTextline(Pix.getCPtr(pix), pix, L_Bmf.getCPtr(l_Bmf), l_Bmf, str, j, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static Pixa pixaAddTextNumber(Pixa pixa, L_Bmf l_Bmf, Numa numa, long j, int i) {
        long pixaAddTextNumber = JniLeptonicaJNI.pixaAddTextNumber(Pixa.getCPtr(pixa), pixa, L_Bmf.getCPtr(l_Bmf), l_Bmf, Numa.getCPtr(numa), numa, j, i);
        if (pixaAddTextNumber == 0) {
            return null;
        }
        return new Pixa(pixaAddTextNumber, false);
    }

    public static Pixa pixaAddTextline(Pixa pixa, L_Bmf l_Bmf, Sarray sarray, long j, int i) {
        long pixaAddTextline = JniLeptonicaJNI.pixaAddTextline(Pixa.getCPtr(pixa), pixa, L_Bmf.getCPtr(l_Bmf), l_Bmf, Sarray.getCPtr(sarray), sarray, j, i);
        if (pixaAddTextline == 0) {
            return null;
        }
        return new Pixa(pixaAddTextline, false);
    }

    public static Sarray bmfGetLineStrings(L_Bmf l_Bmf, String str, int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        long bmfGetLineStrings = JniLeptonicaJNI.bmfGetLineStrings(L_Bmf.getCPtr(l_Bmf), l_Bmf, str, i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
        if (bmfGetLineStrings == 0) {
            return null;
        }
        return new Sarray(bmfGetLineStrings, false);
    }

    public static Numa bmfGetWordWidths(L_Bmf l_Bmf, String str, Sarray sarray) {
        long bmfGetWordWidths = JniLeptonicaJNI.bmfGetWordWidths(L_Bmf.getCPtr(l_Bmf), l_Bmf, str, Sarray.getCPtr(sarray), sarray);
        if (bmfGetWordWidths == 0) {
            return null;
        }
        return new Numa(bmfGetWordWidths, false);
    }

    public static int bmfGetStringWidth(L_Bmf l_Bmf, String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.bmfGetStringWidth(L_Bmf.getCPtr(l_Bmf), l_Bmf, str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static Sarray splitStringToParagraphs(String str, int i) {
        long splitStringToParagraphs = JniLeptonicaJNI.splitStringToParagraphs(str, i);
        if (splitStringToParagraphs == 0) {
            return null;
        }
        return new Sarray(splitStringToParagraphs, false);
    }

    public static Pix pixReadTiff(String str, int i) {
        long pixReadTiff = JniLeptonicaJNI.pixReadTiff(str, i);
        if (pixReadTiff == 0) {
            return null;
        }
        return new Pix(pixReadTiff, false);
    }

    public static Pix pixReadStreamTiff(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i) {
        long pixReadStreamTiff = JniLeptonicaJNI.pixReadStreamTiff(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i);
        if (pixReadStreamTiff == 0) {
            return null;
        }
        return new Pix(pixReadStreamTiff, false);
    }

    public static int pixWriteTiff(String str, Pix pix, int i, String str2) {
        return JniLeptonicaJNI.pixWriteTiff(str, Pix.getCPtr(pix), pix, i, str2);
    }

    public static int pixWriteTiffCustom(String str, Pix pix, int i, String str2, Numa numa, Sarray sarray, Sarray sarray2, Numa numa2) {
        return JniLeptonicaJNI.pixWriteTiffCustom(str, Pix.getCPtr(pix), pix, i, str2, Numa.getCPtr(numa), numa, Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2, Numa.getCPtr(numa2), numa2);
    }

    public static int pixWriteStreamTiff(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i) {
        return JniLeptonicaJNI.pixWriteStreamTiff(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i);
    }

    public static Pixa pixaReadMultipageTiff(String str) {
        long pixaReadMultipageTiff = JniLeptonicaJNI.pixaReadMultipageTiff(str);
        if (pixaReadMultipageTiff == 0) {
            return null;
        }
        return new Pixa(pixaReadMultipageTiff, false);
    }

    public static int writeMultipageTiff(String str, String str2, String str3) {
        return JniLeptonicaJNI.writeMultipageTiff(str, str2, str3);
    }

    public static int writeMultipageTiffSA(Sarray sarray, String str) {
        return JniLeptonicaJNI.writeMultipageTiffSA(Sarray.getCPtr(sarray), sarray, str);
    }

    public static int fprintTiffInfo(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, String str) {
        return JniLeptonicaJNI.fprintTiffInfo(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), str);
    }

    public static int tiffGetCount(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.tiffGetCount(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int getTiffResolution(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.getTiffResolution(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static int readHeaderTiff(String str, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6, SWIGTYPE_p_int sWIGTYPE_p_int7) {
        return JniLeptonicaJNI.readHeaderTiff(str, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int7));
    }

    public static int freadHeaderTiff(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6, SWIGTYPE_p_int sWIGTYPE_p_int7) {
        return JniLeptonicaJNI.freadHeaderTiff(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int7));
    }

    public static int readHeaderMemTiff(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3, SWIGTYPE_p_int sWIGTYPE_p_int4, SWIGTYPE_p_int sWIGTYPE_p_int5, SWIGTYPE_p_int sWIGTYPE_p_int6, SWIGTYPE_p_int sWIGTYPE_p_int7) {
        return JniLeptonicaJNI.readHeaderMemTiff(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int4), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int5), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int6), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int7));
    }

    public static int findTiffCompression(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.findTiffCompression(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int extractG4DataFromFile(String str, SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.extractG4DataFromFile(str, SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static Pix pixReadMemTiff(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, int i) {
        long pixReadMemTiff = JniLeptonicaJNI.pixReadMemTiff(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, i);
        if (pixReadMemTiff == 0) {
            return null;
        }
        return new Pix(pixReadMemTiff, false);
    }

    public static int pixWriteMemTiff(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i) {
        return JniLeptonicaJNI.pixWriteMemTiff(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i);
    }

    public static int pixWriteMemTiffCustom(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i, Numa numa, Sarray sarray, Sarray sarray2, Numa numa2) {
        return JniLeptonicaJNI.pixWriteMemTiffCustom(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i, Numa.getCPtr(numa), numa, Sarray.getCPtr(sarray), sarray, Sarray.getCPtr(sarray2), sarray2, Numa.getCPtr(numa2), numa2);
    }

    public static int setMsgSeverity(int i) {
        return JniLeptonicaJNI.setMsgSeverity(i);
    }

    public static int returnErrorInt(String str, String str2, int i) {
        return JniLeptonicaJNI.returnErrorInt(str, str2, i);
    }

    public static float returnErrorFloat(String str, String str2, float f) {
        return JniLeptonicaJNI.returnErrorFloat(str, str2, f);
    }

    public static SWIGTYPE_p_void returnErrorPtr(String str, String str2, SWIGTYPE_p_void sWIGTYPE_p_void) {
        long returnErrorPtr = JniLeptonicaJNI.returnErrorPtr(str, str2, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
        if (returnErrorPtr == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(returnErrorPtr, false);
    }

    public static String stringNew(String str) {
        return JniLeptonicaJNI.stringNew(str);
    }

    public static int stringCopy(String str, String str2, int i) {
        return JniLeptonicaJNI.stringCopy(str, str2, i);
    }

    public static int stringReplace(SWIGTYPE_p_p_char sWIGTYPE_p_p_char, String str) {
        return JniLeptonicaJNI.stringReplace(SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), str);
    }

    public static int stringLength(String str, long j) {
        return JniLeptonicaJNI.stringLength(str, j);
    }

    public static int stringCat(String str, long j, String str2) {
        return JniLeptonicaJNI.stringCat(str, j, str2);
    }

    public static String stringConcatNew(String str) {
        return JniLeptonicaJNI.stringConcatNew(str);
    }

    public static String stringJoin(String str, String str2) {
        return JniLeptonicaJNI.stringJoin(str, str2);
    }

    public static String stringReverse(String str) {
        return JniLeptonicaJNI.stringReverse(str);
    }

    public static String strtokSafe(String str, String str2, SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        return JniLeptonicaJNI.strtokSafe(str, str2, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public static int stringSplitOnToken(String str, String str2, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_char sWIGTYPE_p_p_char2) {
        return JniLeptonicaJNI.stringSplitOnToken(str, str2, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char2));
    }

    public static String stringRemoveChars(String str, String str2) {
        return JniLeptonicaJNI.stringRemoveChars(str, str2);
    }

    public static int stringFindSubstr(String str, String str2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.stringFindSubstr(str, str2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static String stringReplaceSubstr(String str, String str2, String str3, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.stringReplaceSubstr(str, str2, str3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static String stringReplaceEachSubstr(String str, String str2, String str3, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.stringReplaceEachSubstr(str, str2, str3, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static L_Dna arrayFindEachSequence(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2, long j2) {
        long arrayFindEachSequence = JniLeptonicaJNI.arrayFindEachSequence(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2), j2);
        if (arrayFindEachSequence == 0) {
            return null;
        }
        return new L_Dna(arrayFindEachSequence, false);
    }

    public static int arrayFindSequence(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char2, long j2, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        return JniLeptonicaJNI.arrayFindSequence(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char2), j2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static SWIGTYPE_p_void reallocNew(SWIGTYPE_p_p_void sWIGTYPE_p_p_void, int i, int i2) {
        long reallocNew = JniLeptonicaJNI.reallocNew(SWIGTYPE_p_p_void.getCPtr(sWIGTYPE_p_p_void), i, i2);
        if (reallocNew == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(reallocNew, false);
    }

    public static SWIGTYPE_p_unsigned_char l_binaryRead(String str, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_binaryRead = JniLeptonicaJNI.l_binaryRead(str, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_binaryRead == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_binaryRead, false);
    }

    public static SWIGTYPE_p_unsigned_char l_binaryReadStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_binaryReadStream = JniLeptonicaJNI.l_binaryReadStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_binaryReadStream == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_binaryReadStream, false);
    }

    public static SWIGTYPE_p_unsigned_char l_binaryReadSelect(String str, long j, long j2, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_binaryReadSelect = JniLeptonicaJNI.l_binaryReadSelect(str, j, j2, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_binaryReadSelect == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_binaryReadSelect, false);
    }

    public static SWIGTYPE_p_unsigned_char l_binaryReadSelectStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, long j, long j2, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long l_binaryReadSelectStream = JniLeptonicaJNI.l_binaryReadSelectStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), j, j2, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (l_binaryReadSelectStream == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_binaryReadSelectStream, false);
    }

    public static int l_binaryWrite(String str, String str2, SWIGTYPE_p_void sWIGTYPE_p_void, long j) {
        return JniLeptonicaJNI.l_binaryWrite(str, str2, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void), j);
    }

    public static long nbytesInFile(String str) {
        return JniLeptonicaJNI.nbytesInFile(str);
    }

    public static long fnbytesInFile(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        return JniLeptonicaJNI.fnbytesInFile(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public static SWIGTYPE_p_unsigned_char l_binaryCopy(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long l_binaryCopy = JniLeptonicaJNI.l_binaryCopy(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (l_binaryCopy == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(l_binaryCopy, false);
    }

    public static int fileCopy(String str, String str2) {
        return JniLeptonicaJNI.fileCopy(str, str2);
    }

    public static int fileConcatenate(String str, String str2) {
        return JniLeptonicaJNI.fileConcatenate(str, str2);
    }

    public static int fileAppendString(String str, String str2) {
        return JniLeptonicaJNI.fileAppendString(str, str2);
    }

    public static int filesAreIdentical(String str, String str2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.filesAreIdentical(str, str2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int convertOnLittleEnd16(int i) {
        return JniLeptonicaJNI.convertOnLittleEnd16(i);
    }

    public static int convertOnBigEnd16(int i) {
        return JniLeptonicaJNI.convertOnBigEnd16(i);
    }

    public static long convertOnLittleEnd32(long j) {
        return JniLeptonicaJNI.convertOnLittleEnd32(j);
    }

    public static long convertOnBigEnd32(long j) {
        return JniLeptonicaJNI.convertOnBigEnd32(j);
    }

    public static SWIGTYPE_p_FILE fopenReadStream(String str) {
        long fopenReadStream = JniLeptonicaJNI.fopenReadStream(str);
        if (fopenReadStream == 0) {
            return null;
        }
        return new SWIGTYPE_p_FILE(fopenReadStream, false);
    }

    public static SWIGTYPE_p_FILE fopenWriteStream(String str, String str2) {
        long fopenWriteStream = JniLeptonicaJNI.fopenWriteStream(str, str2);
        if (fopenWriteStream == 0) {
            return null;
        }
        return new SWIGTYPE_p_FILE(fopenWriteStream, false);
    }

    public static SWIGTYPE_p_FILE lept_fopen(String str, String str2) {
        long lept_fopen = JniLeptonicaJNI.lept_fopen(str, str2);
        if (lept_fopen == 0) {
            return null;
        }
        return new SWIGTYPE_p_FILE(lept_fopen, false);
    }

    public static int lept_fclose(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        return JniLeptonicaJNI.lept_fclose(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
    }

    public static SWIGTYPE_p_void lept_calloc(long j, long j2) {
        long lept_calloc = JniLeptonicaJNI.lept_calloc(j, j2);
        if (lept_calloc == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(lept_calloc, false);
    }

    public static void lept_free(SWIGTYPE_p_void sWIGTYPE_p_void) {
        JniLeptonicaJNI.lept_free(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static int lept_mkdir(String str) {
        return JniLeptonicaJNI.lept_mkdir(str);
    }

    public static int lept_rmdir(String str) {
        return JniLeptonicaJNI.lept_rmdir(str);
    }

    public static void lept_direxists(String str, SWIGTYPE_p_int sWIGTYPE_p_int) {
        JniLeptonicaJNI.lept_direxists(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int lept_rm_match(String str, String str2) {
        return JniLeptonicaJNI.lept_rm_match(str, str2);
    }

    public static int lept_rm(String str, String str2) {
        return JniLeptonicaJNI.lept_rm(str, str2);
    }

    public static int lept_rmfile(String str) {
        return JniLeptonicaJNI.lept_rmfile(str);
    }

    public static int lept_mv(String str, String str2, String str3, SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        return JniLeptonicaJNI.lept_mv(str, str2, str3, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public static int lept_cp(String str, String str2, String str3, SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        return JniLeptonicaJNI.lept_cp(str, str2, str3, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public static int splitPathAtDirectory(String str, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_char sWIGTYPE_p_p_char2) {
        return JniLeptonicaJNI.splitPathAtDirectory(str, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char2));
    }

    public static int splitPathAtExtension(String str, SWIGTYPE_p_p_char sWIGTYPE_p_p_char, SWIGTYPE_p_p_char sWIGTYPE_p_p_char2) {
        return JniLeptonicaJNI.splitPathAtExtension(str, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char), SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char2));
    }

    public static String pathJoin(String str, String str2) {
        return JniLeptonicaJNI.pathJoin(str, str2);
    }

    public static String appendSubdirectory(String str, String str2) {
        return JniLeptonicaJNI.appendSubdirectory(str, str2);
    }

    public static int convertSepCharsInPath(String str, int i) {
        return JniLeptonicaJNI.convertSepCharsInPath(str, i);
    }

    public static String genPathname(String str, String str2) {
        return JniLeptonicaJNI.genPathname(str, str2);
    }

    public static int makeTempDirname(String str, long j, String str2) {
        return JniLeptonicaJNI.makeTempDirname(str, j, str2);
    }

    public static int modifyTrailingSlash(String str, long j, int i) {
        return JniLeptonicaJNI.modifyTrailingSlash(str, j, i);
    }

    public static String genTempFilename(String str, String str2, int i, int i2) {
        return JniLeptonicaJNI.genTempFilename(str, str2, i, i2);
    }

    public static int extractNumberFromFilename(String str, int i, int i2) {
        return JniLeptonicaJNI.extractNumberFromFilename(str, i, i2);
    }

    public static int fileCorruptByDeletion(String str, float f, float f2, String str2) {
        return JniLeptonicaJNI.fileCorruptByDeletion(str, f, f2, str2);
    }

    public static int fileCorruptByMutation(String str, float f, float f2, String str2) {
        return JniLeptonicaJNI.fileCorruptByMutation(str, f, f2, str2);
    }

    public static int genRandomIntegerInRange(int i, int i2, SWIGTYPE_p_int sWIGTYPE_p_int) {
        return JniLeptonicaJNI.genRandomIntegerInRange(i, i2, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int));
    }

    public static int lept_roundftoi(float f) {
        return JniLeptonicaJNI.lept_roundftoi(f);
    }

    public static long convertBinaryToGrayCode(long j) {
        return JniLeptonicaJNI.convertBinaryToGrayCode(j);
    }

    public static long convertGrayCodeToBinary(long j) {
        return JniLeptonicaJNI.convertGrayCodeToBinary(j);
    }

    public static String getLeptonicaVersion() {
        return JniLeptonicaJNI.getLeptonicaVersion();
    }

    public static void startTimer() {
        JniLeptonicaJNI.startTimer();
    }

    public static float stopTimer() {
        return JniLeptonicaJNI.stopTimer();
    }

    public static SWIGTYPE_p_void startTimerNested() {
        long startTimerNested = JniLeptonicaJNI.startTimerNested();
        if (startTimerNested == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(startTimerNested, false);
    }

    public static float stopTimerNested(SWIGTYPE_p_void sWIGTYPE_p_void) {
        return JniLeptonicaJNI.stopTimerNested(SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public static void l_getCurrentTime(SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2) {
        JniLeptonicaJNI.l_getCurrentTime(SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2));
    }

    public static String l_getFormattedDate() {
        return JniLeptonicaJNI.l_getFormattedDate();
    }

    public static int pixHtmlViewer(String str, String str2, String str3, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixHtmlViewer(str, str2, str3, i, i2, i3);
    }

    public static Pix pixSimpleCaptcha(Pix pix, int i, int i2, long j, long j2, int i3) {
        long pixSimpleCaptcha = JniLeptonicaJNI.pixSimpleCaptcha(Pix.getCPtr(pix), pix, i, i2, j, j2, i3);
        if (pixSimpleCaptcha == 0) {
            return null;
        }
        return new Pix(pixSimpleCaptcha, false);
    }

    public static Pix pixRandomHarmonicWarp(Pix pix, float f, float f2, float f3, float f4, int i, int i2, long j, int i3) {
        long pixRandomHarmonicWarp = JniLeptonicaJNI.pixRandomHarmonicWarp(Pix.getCPtr(pix), pix, f, f2, f3, f4, i, i2, j, i3);
        if (pixRandomHarmonicWarp == 0) {
            return null;
        }
        return new Pix(pixRandomHarmonicWarp, false);
    }

    public static Pix pixWarpStereoscopic(Pix pix, int i, int i2, int i3, int i4, int i5, int i6) {
        long pixWarpStereoscopic = JniLeptonicaJNI.pixWarpStereoscopic(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5, i6);
        if (pixWarpStereoscopic == 0) {
            return null;
        }
        return new Pix(pixWarpStereoscopic, false);
    }

    public static Pix pixStretchHorizontal(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixStretchHorizontal = JniLeptonicaJNI.pixStretchHorizontal(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixStretchHorizontal == 0) {
            return null;
        }
        return new Pix(pixStretchHorizontal, false);
    }

    public static Pix pixStretchHorizontalSampled(Pix pix, int i, int i2, int i3, int i4) {
        long pixStretchHorizontalSampled = JniLeptonicaJNI.pixStretchHorizontalSampled(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixStretchHorizontalSampled == 0) {
            return null;
        }
        return new Pix(pixStretchHorizontalSampled, false);
    }

    public static Pix pixStretchHorizontalLI(Pix pix, int i, int i2, int i3, int i4) {
        long pixStretchHorizontalLI = JniLeptonicaJNI.pixStretchHorizontalLI(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixStretchHorizontalLI == 0) {
            return null;
        }
        return new Pix(pixStretchHorizontalLI, false);
    }

    public static Pix pixQuadraticVShear(Pix pix, int i, int i2, int i3, int i4, int i5) {
        long pixQuadraticVShear = JniLeptonicaJNI.pixQuadraticVShear(Pix.getCPtr(pix), pix, i, i2, i3, i4, i5);
        if (pixQuadraticVShear == 0) {
            return null;
        }
        return new Pix(pixQuadraticVShear, false);
    }

    public static Pix pixQuadraticVShearSampled(Pix pix, int i, int i2, int i3, int i4) {
        long pixQuadraticVShearSampled = JniLeptonicaJNI.pixQuadraticVShearSampled(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixQuadraticVShearSampled == 0) {
            return null;
        }
        return new Pix(pixQuadraticVShearSampled, false);
    }

    public static Pix pixQuadraticVShearLI(Pix pix, int i, int i2, int i3, int i4) {
        long pixQuadraticVShearLI = JniLeptonicaJNI.pixQuadraticVShearLI(Pix.getCPtr(pix), pix, i, i2, i3, i4);
        if (pixQuadraticVShearLI == 0) {
            return null;
        }
        return new Pix(pixQuadraticVShearLI, false);
    }

    public static Pix pixStereoFromPair(Pix pix, Pix pix2, float f, float f2, float f3) {
        long pixStereoFromPair = JniLeptonicaJNI.pixStereoFromPair(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, f, f2, f3);
        if (pixStereoFromPair == 0) {
            return null;
        }
        return new Pix(pixStereoFromPair, false);
    }

    public static L_WShed wshedCreate(Pix pix, Pix pix2, int i, int i2) {
        long wshedCreate = JniLeptonicaJNI.wshedCreate(Pix.getCPtr(pix), pix, Pix.getCPtr(pix2), pix2, i, i2);
        if (wshedCreate == 0) {
            return null;
        }
        return new L_WShed(wshedCreate, false);
    }

    public static void wshedDestroy(SWIGTYPE_p_p_L_WShed sWIGTYPE_p_p_L_WShed) {
        JniLeptonicaJNI.wshedDestroy(SWIGTYPE_p_p_L_WShed.getCPtr(sWIGTYPE_p_p_L_WShed));
    }

    public static int wshedApply(L_WShed l_WShed) {
        return JniLeptonicaJNI.wshedApply(L_WShed.getCPtr(l_WShed), l_WShed);
    }

    public static int wshedBasins(L_WShed l_WShed, SWIGTYPE_p_p_Pixa sWIGTYPE_p_p_Pixa, SWIGTYPE_p_p_Numa sWIGTYPE_p_p_Numa) {
        return JniLeptonicaJNI.wshedBasins(L_WShed.getCPtr(l_WShed), l_WShed, SWIGTYPE_p_p_Pixa.getCPtr(sWIGTYPE_p_p_Pixa), SWIGTYPE_p_p_Numa.getCPtr(sWIGTYPE_p_p_Numa));
    }

    public static Pix wshedRenderFill(L_WShed l_WShed) {
        long wshedRenderFill = JniLeptonicaJNI.wshedRenderFill(L_WShed.getCPtr(l_WShed), l_WShed);
        if (wshedRenderFill == 0) {
            return null;
        }
        return new Pix(wshedRenderFill, false);
    }

    public static Pix wshedRenderColors(L_WShed l_WShed) {
        long wshedRenderColors = JniLeptonicaJNI.wshedRenderColors(L_WShed.getCPtr(l_WShed), l_WShed);
        if (wshedRenderColors == 0) {
            return null;
        }
        return new Pix(wshedRenderColors, false);
    }

    public static Pix pixReadStreamWebP(SWIGTYPE_p_FILE sWIGTYPE_p_FILE) {
        long pixReadStreamWebP = JniLeptonicaJNI.pixReadStreamWebP(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE));
        if (pixReadStreamWebP == 0) {
            return null;
        }
        return new Pix(pixReadStreamWebP, false);
    }

    public static Pix pixReadMemWebP(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j) {
        long pixReadMemWebP = JniLeptonicaJNI.pixReadMemWebP(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j);
        if (pixReadMemWebP == 0) {
            return null;
        }
        return new Pix(pixReadMemWebP, false);
    }

    public static int readHeaderWebP(String str, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.readHeaderWebP(str, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int readHeaderMemWebP(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_int sWIGTYPE_p_int, SWIGTYPE_p_int sWIGTYPE_p_int2, SWIGTYPE_p_int sWIGTYPE_p_int3) {
        return JniLeptonicaJNI.readHeaderMemWebP(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int2), SWIGTYPE_p_int.getCPtr(sWIGTYPE_p_int3));
    }

    public static int pixWriteWebP(String str, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteWebP(str, Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixWriteStreamWebP(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteStreamWebP(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixWriteMemWebP(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteMemWebP(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixaWriteFiles(String str, Pixa pixa, int i) {
        return JniLeptonicaJNI.pixaWriteFiles(str, Pixa.getCPtr(pixa), pixa, i);
    }

    public static int pixWrite(String str, Pix pix, int i) {
        return JniLeptonicaJNI.pixWrite(str, Pix.getCPtr(pix), pix, i);
    }

    public static int pixWriteStream(SWIGTYPE_p_FILE sWIGTYPE_p_FILE, Pix pix, int i) {
        return JniLeptonicaJNI.pixWriteStream(SWIGTYPE_p_FILE.getCPtr(sWIGTYPE_p_FILE), Pix.getCPtr(pix), pix, i);
    }

    public static int pixWriteImpliedFormat(String str, Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixWriteImpliedFormat(str, Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixWriteTempfile(String str, String str2, Pix pix, int i, SWIGTYPE_p_p_char sWIGTYPE_p_p_char) {
        return JniLeptonicaJNI.pixWriteTempfile(str, str2, Pix.getCPtr(pix), pix, i, SWIGTYPE_p_p_char.getCPtr(sWIGTYPE_p_p_char));
    }

    public static int pixChooseOutputFormat(Pix pix) {
        return JniLeptonicaJNI.pixChooseOutputFormat(Pix.getCPtr(pix), pix);
    }

    public static int getImpliedFileFormat(String str) {
        return JniLeptonicaJNI.getImpliedFileFormat(str);
    }

    public static String getFormatExtension(int i) {
        return JniLeptonicaJNI.getFormatExtension(i);
    }

    public static int pixWriteMem(SWIGTYPE_p_p_unsigned_char sWIGTYPE_p_p_unsigned_char, SWIGTYPE_p_size_t sWIGTYPE_p_size_t, Pix pix, int i) {
        return JniLeptonicaJNI.pixWriteMem(SWIGTYPE_p_p_unsigned_char.getCPtr(sWIGTYPE_p_p_unsigned_char), SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t), Pix.getCPtr(pix), pix, i);
    }

    public static int pixDisplay(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixDisplay(Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixDisplayWithTitle(Pix pix, int i, int i2, String str, int i3) {
        return JniLeptonicaJNI.pixDisplayWithTitle(Pix.getCPtr(pix), pix, i, i2, str, i3);
    }

    public static int pixDisplayMultiple(String str) {
        return JniLeptonicaJNI.pixDisplayMultiple(str);
    }

    public static int pixDisplayWrite(Pix pix, int i) {
        return JniLeptonicaJNI.pixDisplayWrite(Pix.getCPtr(pix), pix, i);
    }

    public static int pixDisplayWriteFormat(Pix pix, int i, int i2) {
        return JniLeptonicaJNI.pixDisplayWriteFormat(Pix.getCPtr(pix), pix, i, i2);
    }

    public static int pixSaveTiled(Pix pix, Pixa pixa, float f, int i, int i2, int i3) {
        return JniLeptonicaJNI.pixSaveTiled(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, f, i, i2, i3);
    }

    public static int pixSaveTiledOutline(Pix pix, Pixa pixa, float f, int i, int i2, int i3, int i4) {
        return JniLeptonicaJNI.pixSaveTiledOutline(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, f, i, i2, i3, i4);
    }

    public static int pixSaveTiledWithText(Pix pix, Pixa pixa, int i, int i2, int i3, int i4, L_Bmf l_Bmf, String str, long j, int i5) {
        return JniLeptonicaJNI.pixSaveTiledWithText(Pix.getCPtr(pix), pix, Pixa.getCPtr(pixa), pixa, i, i2, i3, i4, L_Bmf.getCPtr(l_Bmf), l_Bmf, str, j, i5);
    }

    public static void l_chooseDisplayProg(int i) {
        JniLeptonicaJNI.l_chooseDisplayProg(i);
    }

    public static SWIGTYPE_p_unsigned_char zlibCompress(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long zlibCompress = JniLeptonicaJNI.zlibCompress(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (zlibCompress == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(zlibCompress, false);
    }

    public static SWIGTYPE_p_unsigned_char zlibUncompress(SWIGTYPE_p_unsigned_char sWIGTYPE_p_unsigned_char, long j, SWIGTYPE_p_size_t sWIGTYPE_p_size_t) {
        long zlibUncompress = JniLeptonicaJNI.zlibUncompress(SWIGTYPE_p_unsigned_char.getCPtr(sWIGTYPE_p_unsigned_char), j, SWIGTYPE_p_size_t.getCPtr(sWIGTYPE_p_size_t));
        if (zlibUncompress == 0) {
            return null;
        }
        return new SWIGTYPE_p_unsigned_char(zlibUncompress, false);
    }

    public static SWIGTYPE_p_p_char getGplotstylenames() {
        long gplotstylenames_get = JniLeptonicaJNI.gplotstylenames_get();
        if (gplotstylenames_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(gplotstylenames_get, false);
    }

    public static SWIGTYPE_p_p_char getGplotfilestyles() {
        long gplotfilestyles_get = JniLeptonicaJNI.gplotfilestyles_get();
        if (gplotfilestyles_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(gplotfilestyles_get, false);
    }

    public static SWIGTYPE_p_p_char getGplotfileoutputs() {
        long gplotfileoutputs_get = JniLeptonicaJNI.gplotfileoutputs_get();
        if (gplotfileoutputs_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(gplotfileoutputs_get, false);
    }

    public static int getADDED_BORDER() {
        return JniLeptonicaJNI.ADDED_BORDER_get();
    }

    public static int getL_RED_SHIFT() {
        return JniLeptonicaJNI.L_RED_SHIFT_get();
    }

    public static int getL_GREEN_SHIFT() {
        return JniLeptonicaJNI.L_GREEN_SHIFT_get();
    }

    public static int getL_BLUE_SHIFT() {
        return JniLeptonicaJNI.L_BLUE_SHIFT_get();
    }

    public static int getL_ALPHA_SHIFT() {
        return JniLeptonicaJNI.L_ALPHA_SHIFT_get();
    }

    public static float getL_RED_WEIGHT() {
        return JniLeptonicaJNI.L_RED_WEIGHT_get();
    }

    public static float getL_GREEN_WEIGHT() {
        return JniLeptonicaJNI.L_GREEN_WEIGHT_get();
    }

    public static float getL_BLUE_WEIGHT() {
        return JniLeptonicaJNI.L_BLUE_WEIGHT_get();
    }

    public static int getL_NOCOPY() {
        return JniLeptonicaJNI.L_NOCOPY_get();
    }

    public static SWIGTYPE_p_int getSupportedBarcodeFormat() {
        long SupportedBarcodeFormat_get = JniLeptonicaJNI.SupportedBarcodeFormat_get();
        if (SupportedBarcodeFormat_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_int(SupportedBarcodeFormat_get, false);
    }

    public static SWIGTYPE_p_p_char getSupportedBarcodeFormatName() {
        long SupportedBarcodeFormatName_get = JniLeptonicaJNI.SupportedBarcodeFormatName_get();
        if (SupportedBarcodeFormatName_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(SupportedBarcodeFormatName_get, false);
    }

    public static int getNumSupportedBarcodeFormats() {
        return JniLeptonicaJNI.NumSupportedBarcodeFormats_get();
    }

    public static SWIGTYPE_p_p_char getCode2of5() {
        long Code2of5_get = JniLeptonicaJNI.Code2of5_get();
        if (Code2of5_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Code2of5_get, false);
    }

    public static int getC25_START() {
        return JniLeptonicaJNI.C25_START_get();
    }

    public static int getC25_STOP() {
        return JniLeptonicaJNI.C25_STOP_get();
    }

    public static SWIGTYPE_p_p_char getCodeI2of5() {
        long CodeI2of5_get = JniLeptonicaJNI.CodeI2of5_get();
        if (CodeI2of5_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(CodeI2of5_get, false);
    }

    public static int getCI25_START() {
        return JniLeptonicaJNI.CI25_START_get();
    }

    public static int getCI25_STOP() {
        return JniLeptonicaJNI.CI25_STOP_get();
    }

    public static SWIGTYPE_p_p_char getCode93() {
        long Code93_get = JniLeptonicaJNI.Code93_get();
        if (Code93_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Code93_get, false);
    }

    public static String getCode93Val() {
        return JniLeptonicaJNI.Code93Val_get();
    }

    public static int getC93_START() {
        return JniLeptonicaJNI.C93_START_get();
    }

    public static int getC93_STOP() {
        return JniLeptonicaJNI.C93_STOP_get();
    }

    public static SWIGTYPE_p_p_char getCode39() {
        long Code39_get = JniLeptonicaJNI.Code39_get();
        if (Code39_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Code39_get, false);
    }

    public static String getCode39Val() {
        return JniLeptonicaJNI.Code39Val_get();
    }

    public static int getC39_START() {
        return JniLeptonicaJNI.C39_START_get();
    }

    public static int getC39_STOP() {
        return JniLeptonicaJNI.C39_STOP_get();
    }

    public static SWIGTYPE_p_p_char getCodabar() {
        long Codabar_get = JniLeptonicaJNI.Codabar_get();
        if (Codabar_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Codabar_get, false);
    }

    public static void setPixMemoryManager(SWIGTYPE_p_f_size_t__p_void sWIGTYPE_p_f_size_t__p_void, SWIGTYPE_p_f_p_void__void sWIGTYPE_p_f_p_void__void) {
        JniLeptonicaJNI.setPixMemoryManager(SWIGTYPE_p_f_size_t__p_void.getCPtr(sWIGTYPE_p_f_size_t__p_void), SWIGTYPE_p_f_p_void__void.getCPtr(sWIGTYPE_p_f_p_void__void));
    }

    public static String getCodabarVal() {
        return JniLeptonicaJNI.CodabarVal_get();
    }

    public static SWIGTYPE_p_p_char getUpca() {
        long Upca_get = JniLeptonicaJNI.Upca_get();
        if (Upca_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Upca_get, false);
    }

    public static int getUPCA_START() {
        return JniLeptonicaJNI.UPCA_START_get();
    }

    public static int getUPCA_STOP() {
        return JniLeptonicaJNI.UPCA_STOP_get();
    }

    public static int getUPCA_MID() {
        return JniLeptonicaJNI.UPCA_MID_get();
    }

    public static SWIGTYPE_p_p_char getCode128() {
        long Code128_get = JniLeptonicaJNI.Code128_get();
        if (Code128_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_p_char(Code128_get, false);
    }

    public static int getC128_FUN_3() {
        return JniLeptonicaJNI.C128_FUN_3_get();
    }

    public static int getC128_FUNC_2() {
        return JniLeptonicaJNI.C128_FUNC_2_get();
    }

    public static int getC128_SHIFT() {
        return JniLeptonicaJNI.C128_SHIFT_get();
    }

    public static int getC128_GOTO_C() {
        return JniLeptonicaJNI.C128_GOTO_C_get();
    }

    public static int getC128_GOTO_B() {
        return JniLeptonicaJNI.C128_GOTO_B_get();
    }

    public static int getC128_GOTO_A() {
        return JniLeptonicaJNI.C128_GOTO_A_get();
    }

    public static int getC128_FUNC_1() {
        return JniLeptonicaJNI.C128_FUNC_1_get();
    }

    public static int getC128_START_A() {
        return JniLeptonicaJNI.C128_START_A_get();
    }

    public static int getC128_START_B() {
        return JniLeptonicaJNI.C128_START_B_get();
    }

    public static int getC128_START_C() {
        return JniLeptonicaJNI.C128_START_C_get();
    }

    public static int getC128_STOP() {
        return JniLeptonicaJNI.C128_STOP_get();
    }

    public static int getC128_SYMBOL_WIDTH() {
        return JniLeptonicaJNI.C128_SYMBOL_WIDTH_get();
    }

    public static void setLeptMsgSeverity(int i) {
        JniLeptonicaJNI.LeptMsgSeverity_set(i);
    }

    public static int getLeptMsgSeverity() {
        return JniLeptonicaJNI.LeptMsgSeverity_get();
    }
}
