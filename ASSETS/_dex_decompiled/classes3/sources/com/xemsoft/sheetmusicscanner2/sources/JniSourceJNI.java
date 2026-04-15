package com.xemsoft.sheetmusicscanner2.sources;

import android.graphics.Bitmap;
import com.xemsoft.sheetmusicscanner2.analysis.Scanner;

public class JniSourceJNI {
    public static final native int BARAA_VERSION_NUMBER_get();

    public static final native int BARA_VERSION_NUMBER_get();

    public static final native int BAR_VERSION_NUMBER_get();

    public static final native int BOX_VERSION_NUMBER_get();

    public static final native int IMAT_GAP_WIDE_get();

    public static final native int IMAT_GAP_get();

    public static final native int IMAT_LINE_CHECKED_get();

    public static final native int IMAT_LINE_get();

    public static final native int KS_VERSION_NUMBER_get();

    public static final native int MAX_PATH_get();

    public static final native int NUMA_VERSION_NUMBER_get();

    public static final native int PIX_VERSION_NUMBER_get();

    public static final native int PREPROCESSOR_DISTORTED_get();

    public static final native int PREPROCESSOR_DISTORTION_NOT_RECOGNIZED_get();

    public static final native int PREPROCESSOR_DISTORTION_TOO_HIGH_get();

    public static final native int PREPROCESSOR_PIX_EMPTY_get();

    public static final native int PREPROCESSOR_RESOLUTION_LOW_get();

    public static final native int PREPROCESSOR_ROTATED_get();

    public static final native long PRIMA_array_get(long j, PRIMA prima);

    public static final native void PRIMA_array_set(long j, PRIMA prima, long j2);

    public static final native int PRIMA_doesOwnItems_get(long j, PRIMA prima);

    public static final native void PRIMA_doesOwnItems_set(long j, PRIMA prima, int i);

    public static final native int PRIMA_n_get(long j, PRIMA prima);

    public static final native void PRIMA_n_set(long j, PRIMA prima, int i);

    public static final native int PRIMA_nalloc_get(long j, PRIMA prima);

    public static final native void PRIMA_nalloc_set(long j, PRIMA prima, int i);

    public static final native int PRIM_accidentalYIndex_get(long j, PRIM prim);

    public static final native void PRIM_accidentalYIndex_set(long j, PRIM prim, int i);

    public static final native int PRIM_beamGroupId_get(long j, PRIM prim);

    public static final native void PRIM_beamGroupId_set(long j, PRIM prim, int i);

    public static final native int PRIM_beamHeight_get(long j, PRIM prim);

    public static final native void PRIM_beamHeight_set(long j, PRIM prim, int i);

    public static final native long PRIM_beamStaffIndexes_get(long j, PRIM prim);

    public static final native void PRIM_beamStaffIndexes_set(long j, PRIM prim, long j2);

    public static final native double PRIM_beamStartTimeRel_get(long j, PRIM prim);

    public static final native void PRIM_beamStartTimeRel_set(long j, PRIM prim, double d);

    public static final native long PRIM_beamStemsXOri_get(long j, PRIM prim);

    public static final native void PRIM_beamStemsXOri_set(long j, PRIM prim, long j2);

    public static final native long PRIM_beamTimesToNext_get(long j, PRIM prim);

    public static final native void PRIM_beamTimesToNext_set(long j, PRIM prim, long j2);

    public static final native int PRIM_beamY0L_get(long j, PRIM prim);

    public static final native void PRIM_beamY0L_set(long j, PRIM prim, int i);

    public static final native int PRIM_beamY0R_get(long j, PRIM prim);

    public static final native void PRIM_beamY0R_set(long j, PRIM prim, int i);

    public static final native long PRIM_box_get(long j, PRIM prim);

    public static final native void PRIM_box_set(long j, PRIM prim, long j2);

    public static final native int PRIM_hasStemHeadsOnTheLeft_get(long j, PRIM prim);

    public static final native void PRIM_hasStemHeadsOnTheLeft_set(long j, PRIM prim, int i);

    public static final native int PRIM_hasStemHeadsOnTheRight_get(long j, PRIM prim);

    public static final native void PRIM_hasStemHeadsOnTheRight_set(long j, PRIM prim, int i);

    public static final native long PRIM_headStemsL_get(long j, PRIM prim);

    public static final native void PRIM_headStemsL_set(long j, PRIM prim, long j2, PRIMA prima);

    public static final native long PRIM_headStemsR_get(long j, PRIM prim);

    public static final native void PRIM_headStemsR_set(long j, PRIM prim, long j2, PRIMA prima);

    public static final native int PRIM_id_get(long j, PRIM prim);

    public static final native void PRIM_id_set(long j, PRIM prim, int i);

    public static final native int PRIM_isStemDown_get(long j, PRIM prim);

    public static final native void PRIM_isStemDown_set(long j, PRIM prim, int i);

    public static final native int PRIM_isStemPureHalfNotes_get(long j, PRIM prim);

    public static final native void PRIM_isStemPureHalfNotes_set(long j, PRIM prim, int i);

    public static final native int PRIM_isStemUp_get(long j, PRIM prim);

    public static final native void PRIM_isStemUp_set(long j, PRIM prim, int i);

    public static final native long PRIM_matcha_get(long j, PRIM prim);

    public static final native void PRIM_matcha_set(long j, PRIM prim, long j2, matcha matcha);

    public static final native long PRIM_pix_get(long j, PRIM prim);

    public static final native void PRIM_pix_set(long j, PRIM prim, long j2);

    public static final native int PRIM_recognizedType_get(long j, PRIM prim);

    public static final native void PRIM_recognizedType_set(long j, PRIM prim, int i);

    public static final native int PRIM_refcount_get(long j, PRIM prim);

    public static final native void PRIM_refcount_set(long j, PRIM prim, int i);

    public static final native int PRIM_stemBeamCountL_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamCountL_set(long j, PRIM prim, int i);

    public static final native int PRIM_stemBeamCountR_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamCountR_set(long j, PRIM prim, int i);

    public static final native long PRIM_stemBeamDeltasL_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamDeltasL_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamDeltasR_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamDeltasR_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamGroupIds_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamGroupIds_set(long j, PRIM prim, long j2);

    public static final native int PRIM_stemBeamHookCountL_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamHookCountL_set(long j, PRIM prim, int i);

    public static final native int PRIM_stemBeamHookCountR_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamHookCountR_set(long j, PRIM prim, int i);

    public static final native long PRIM_stemBeamOrders_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamOrders_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamY0sL_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamY0sL_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamY0sR_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamY0sR_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamY1sL_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamY1sL_set(long j, PRIM prim, long j2);

    public static final native long PRIM_stemBeamY1sR_get(long j, PRIM prim);

    public static final native void PRIM_stemBeamY1sR_set(long j, PRIM prim, long j2);

    public static final native int PRIM_stemHeadY0_get(long j, PRIM prim);

    public static final native void PRIM_stemHeadY0_set(long j, PRIM prim, int i);

    public static final native int PRIM_stemHeadY1_get(long j, PRIM prim);

    public static final native void PRIM_stemHeadY1_set(long j, PRIM prim, int i);

    public static final native long PRIM_stemHeadsL_get(long j, PRIM prim);

    public static final native void PRIM_stemHeadsL_set(long j, PRIM prim, long j2, PRIMA prima);

    public static final native long PRIM_stemHeadsR_get(long j, PRIM prim);

    public static final native void PRIM_stemHeadsR_set(long j, PRIM prim, long j2, PRIMA prima);

    public static final native int PRIM_yIndex_get(long j, PRIM prim);

    public static final native void PRIM_yIndex_set(long j, PRIM prim, int i);

    public static final native int RLE_CONCAVE_get();

    public static final native int RLE_CONVEX_get();

    public static final native int RLE_NOT_RECOGNIZED_get();

    public static final native int SCORE_VERSION_NUMBER_get();

    public static final native double SERIALIZER_VERSION_NUMBER_get();

    public static final native int SES_VERSION_NUMBER_get();

    public static final native int SOUND_VERSION_NUMBER_get();

    public static final native int TP_VERSION_NUMBER_get();

    public static final native int TS_VERSION_NUMBER_get();

    public static final native int TessBaseAPIAdaptToWordStr(long j, int i, String str);

    public static final native long TessBaseAPIAllWordConfidences(long j);

    public static final native long TessBaseAPIAnalyseLayout(long j);

    public static final native void TessBaseAPIClear(long j);

    public static final native void TessBaseAPIClearAdaptiveClassifier(long j);

    public static final native long TessBaseAPICreate();

    public static final native void TessBaseAPIDelete(long j);

    public static final native void TessBaseAPIDumpPGM(long j, String str);

    public static final native void TessBaseAPIEnd(long j);

    public static final native long TessBaseAPIGetAvailableLanguagesAsVector(long j);

    public static final native int TessBaseAPIGetBoolVariable(long j, String str, long j2);

    public static final native String TessBaseAPIGetBoxText(long j, int i);

    public static final native long TessBaseAPIGetComponentImages(long j, int i, int i2, long j2, long j3);

    public static final native long TessBaseAPIGetComponentImages1(long j, int i, int i2, int i3, int i4, long j2, long j3, long j4);

    public static final native long TessBaseAPIGetConnectedComponents(long j, long j2);

    public static final native String TessBaseAPIGetDatapath(long j);

    public static final native int TessBaseAPIGetDoubleVariable(long j, String str, long j2);

    public static final native String TessBaseAPIGetHOCRText(long j, int i);

    public static final native String TessBaseAPIGetInitLanguagesAsString(long j);

    public static final native long TessBaseAPIGetInputImage(long j);

    public static final native String TessBaseAPIGetInputName(long j);

    public static final native int TessBaseAPIGetIntVariable(long j, String str, long j2);

    public static final native long TessBaseAPIGetIterator(long j);

    public static final native long TessBaseAPIGetLoadedLanguagesAsVector(long j);

    public static final native long TessBaseAPIGetMutableIterator(long j);

    public static final native long TessBaseAPIGetOpenCLDevice(long j, long j2);

    public static final native int TessBaseAPIGetPageSegMode(long j);

    public static final native long TessBaseAPIGetRegions(long j, long j2);

    public static final native int TessBaseAPIGetSourceYResolution(long j);

    public static final native String TessBaseAPIGetStringVariable(long j, String str);

    public static final native long TessBaseAPIGetStrips(long j, long j2, long j3);

    public static final native int TessBaseAPIGetTextDirection(long j, long j2, long j3);

    public static final native long TessBaseAPIGetTextlines(long j, long j2, long j3);

    public static final native long TessBaseAPIGetTextlines1(long j, int i, int i2, long j2, long j3, long j4);

    public static final native long TessBaseAPIGetThresholdedImage(long j);

    public static final native int TessBaseAPIGetThresholdedImageScaleFactor(long j);

    public static final native String TessBaseAPIGetUNLVText(long j);

    public static final native String TessBaseAPIGetUTF8Text(long j);

    public static final native String TessBaseAPIGetUnichar(long j, int i);

    public static final native long TessBaseAPIGetWords(long j, long j2);

    public static final native int TessBaseAPIInit1(long j, String str, String str2, int i, long j2, int i2);

    public static final native int TessBaseAPIInit2(long j, String str, String str2, int i);

    public static final native int TessBaseAPIInit3(long j, String str, String str2);

    public static final native int TessBaseAPIInit4(long j, String str, String str2, int i, long j2, int i2, long j3, long j4, long j5, int i3);

    public static final native void TessBaseAPIInitForAnalysePage(long j);

    public static final native int TessBaseAPIInitLangMod(long j, String str, String str2);

    public static final native int TessBaseAPIIsValidWord(long j, String str);

    public static final native int TessBaseAPIMeanTextConf(long j);

    public static final native void TessBaseAPIPrintVariables(long j, long j2);

    public static final native int TessBaseAPIPrintVariablesToFile(long j, String str);

    public static final native String TessBaseAPIProcessPage(long j, long j2, int i, String str, String str2, int i2);

    public static final native String TessBaseAPIProcessPages(long j, String str, String str2, int i);

    public static final native void TessBaseAPIReadConfigFile(long j, String str);

    public static final native void TessBaseAPIReadDebugConfigFile(long j, String str);

    public static final native int TessBaseAPIRecognize(long j, long j2);

    public static final native int TessBaseAPIRecognizeForChopTest(long j, long j2);

    public static final native String TessBaseAPIRect(long j, long j2, int i, int i2, int i3, int i4, int i5, int i6);

    public static final native int TessBaseAPISetDebugVariable(long j, String str, String str2);

    public static final native void TessBaseAPISetImage(long j, long j2, int i, int i2, int i3, int i4);

    public static final native void TessBaseAPISetImage2(long j, long j2);

    public static final native void TessBaseAPISetInputImage(long j, long j2);

    public static final native void TessBaseAPISetInputName(long j, String str);

    public static final native void TessBaseAPISetMinOrientationMargin(long j, double d);

    public static final native void TessBaseAPISetOutputName(long j, String str);

    public static final native void TessBaseAPISetPageSegMode(long j, int i);

    public static final native void TessBaseAPISetRectangle(long j, int i, int i2, int i3, int i4);

    public static final native void TessBaseAPISetSourceResolution(long j, int i);

    public static final native int TessBaseAPISetVariable(long j, String str, String str2);

    public static final native long TessBoxTextRendererCreate();

    public static final native void TessDeleteIntArray(long j);

    public static final native void TessDeleteResultRenderer(long j);

    public static final native void TessDeleteText(String str);

    public static final native void TessDeleteTextArray(long j);

    public static final native long TessHOcrRendererCreate();

    public static final native long TessPDFRendererCreate(String str);

    public static final native int TessPageIteratorBaseline(long j, int i, long j2, long j3, long j4, long j5);

    public static final native void TessPageIteratorBegin(long j);

    public static final native int TessPageIteratorBlockType(long j);

    public static final native int TessPageIteratorBoundingBox(long j, int i, long j2, long j3, long j4, long j5);

    public static final native long TessPageIteratorCopy(long j);

    public static final native void TessPageIteratorDelete(long j);

    public static final native long TessPageIteratorGetBinaryImage(long j, int i);

    public static final native long TessPageIteratorGetImage(long j, int i, int i2, long j2, long j3);

    public static final native int TessPageIteratorIsAtBeginningOf(long j, int i);

    public static final native int TessPageIteratorIsAtFinalElement(long j, int i, int i2);

    public static final native int TessPageIteratorNext(long j, int i);

    public static final native void TessPageIteratorOrientation(long j, long j2, long j3, long j4, long j5);

    public static final native float TessResultIteratorConfidence(long j, int i);

    public static final native long TessResultIteratorCopy(long j);

    public static final native void TessResultIteratorDelete(long j);

    public static final native long TessResultIteratorGetPageIterator(long j);

    public static final native long TessResultIteratorGetPageIteratorConst(long j);

    public static final native String TessResultIteratorGetUTF8Text(long j, int i);

    public static final native int TessResultIteratorSymbolIsDropcap(long j);

    public static final native int TessResultIteratorSymbolIsSubscript(long j);

    public static final native int TessResultIteratorSymbolIsSuperscript(long j);

    public static final native String TessResultIteratorWordFontAttributes(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9);

    public static final native int TessResultIteratorWordIsFromDictionary(long j);

    public static final native int TessResultIteratorWordIsNumeric(long j);

    public static final native int TessResultRendererAddImage(long j, long j2);

    public static final native int TessResultRendererBeginDocument(long j, String str);

    public static final native int TessResultRendererEndDocument(long j);

    public static final native String TessResultRendererExtention(long j);

    public static final native int TessResultRendererImageNum(long j);

    public static final native void TessResultRendererInsert(long j, long j2);

    public static final native long TessResultRendererNext(long j);

    public static final native String TessResultRendererTitle(long j);

    public static final native long TessTextRendererCreate();

    public static final native long TessUnlvRendererCreate();

    public static final native String TessVersion();

    public static final native long allocInt();

    public static final native long allocPixPtr();

    public static final native long allocScorePtr();

    public static final native long allocSessionPtr();

    public static final native long allocUint32();

    public static final native long analysisResult_background_get(long j, analysisResult analysisresult);

    public static final native void analysisResult_background_set(long j, analysisResult analysisresult, long j2);

    public static final native long analysisResult_overlayMask_get(long j, analysisResult analysisresult);

    public static final native void analysisResult_overlayMask_set(long j, analysisResult analysisresult, long j2);

    public static final native int analysisResult_resultCode_get(long j, analysisResult analysisresult);

    public static final native void analysisResult_resultCode_set(long j, analysisResult analysisresult, int i);

    public static final native long analysisResult_score_get(long j, analysisResult analysisresult);

    public static final native void analysisResult_score_set(long j, analysisResult analysisresult, long j2, score score);

    public static final native long arCreate(int i);

    public static final native void arDestroy(long j);

    public static final native int barAddTP(long j, bar bar, long j2, timepoint timepoint);

    public static final native long barCopy(long j, bar bar, long j2, timepoint timepoint);

    public static final native long barCreate();

    public static final native void barDestroy(long j);

    public static final native int barFixPreviousPartAfterExtending(long j, bar bar, int i, double d, long j2, baraa baraa, long j3, int i2, long j4, int i3);

    public static final native long barGetTP(long j, bar bar, int i);

    public static final native int barInsertTP(long j, bar bar, long j2, timepoint timepoint, int i);

    public static final native int barIsEmpty(long j, bar bar);

    public static final native int barMergeTPs(long j, bar bar, long j2, bar bar2, long j3, timepoint timepoint);

    public static final native int barRemoveTPByIndex(long j, bar bar, int i);

    public static final native int barScale(long j, bar bar, float f);

    public static final native long bar_box_get(long j, bar bar);

    public static final native void bar_box_set(long j, bar bar, long j2);

    public static final native int bar_clef_get(long j, bar bar);

    public static final native void bar_clef_set(long j, bar bar, int i);

    public static final native int bar_isWholeRestOnly_get(long j, bar bar);

    public static final native void bar_isWholeRestOnly_set(long j, bar bar, int i);

    public static final native long bar_keySignature_get(long j, bar bar);

    public static final native void bar_keySignature_set(long j, bar bar, long j2, keySignature keysignature);

    public static final native double bar_length_get(long j, bar bar);

    public static final native void bar_length_set(long j, bar bar, double d);

    public static final native int bar_n_get(long j, bar bar);

    public static final native void bar_n_set(long j, bar bar, int i);

    public static final native int bar_nalloc_get(long j, bar bar);

    public static final native void bar_nalloc_set(long j, bar bar, int i);

    public static final native double bar_offset_get(long j, bar bar);

    public static final native void bar_offset_set(long j, bar bar, double d);

    public static final native long bar_timeSignature_get(long j, bar bar);

    public static final native void bar_timeSignature_set(long j, bar bar, long j2, timeSignature timesignature);

    public static final native long bar_timepoints_get(long j, bar bar);

    public static final native void bar_timepoints_set(long j, bar bar, long j2);

    public static final native int baraAdd(long j, bara bara, long j2, bar bar);

    public static final native long baraCreate();

    public static final native void baraDestroy(long j);

    public static final native long baraGet(long j, bara bara, int i);

    public static final native int baraMergeBarWithNext(long j, bara bara, int i);

    public static final native int baraScale(long j, bara bara, float f);

    public static final native long bara_array_get(long j, bara bara);

    public static final native void bara_array_set(long j, bara bara, long j2);

    public static final native int bara_n_get(long j, bara bara);

    public static final native void bara_n_set(long j, bara bara, int i);

    public static final native int bara_nalloc_get(long j, bara bara);

    public static final native void bara_nalloc_set(long j, bara bara, int i);

    public static final native int baraaAdd(long j, baraa baraa, long j2, bara bara);

    public static final native int baraaAlignBarTPs(long j, baraa baraa, int i, long j2, long j3);

    public static final native int baraaAlignParallelBars(long j, baraa baraa, int i, int i2);

    public static final native long baraaCreate();

    public static final native void baraaDestroy(long j);

    public static final native long baraaFindNextBarOverlappingInX(long j, baraa baraa, long j2, bar bar, int i);

    public static final native long baraaFindPreviousBarOverlappingInX(long j, baraa baraa, long j2, bar bar, int i);

    public static final native int baraaFixWholeRestBars(long j, baraa baraa, int i, int i2, int i3);

    public static final native long baraaGet(long j, baraa baraa, int i);

    public static final native long baraaGetBar(long j, baraa baraa, int i, int i2);

    public static final native int baraaGetBarCount(long j, baraa baraa, int i);

    public static final native int baraaScale(long j, baraa baraa, float f);

    public static final native long baraa_array_get(long j, baraa baraa);

    public static final native void baraa_array_set(long j, baraa baraa, long j2);

    public static final native int baraa_n_get(long j, baraa baraa);

    public static final native void baraa_n_set(long j, baraa baraa, int i);

    public static final native int baraa_nalloc_get(long j, baraa baraa);

    public static final native void baraa_nalloc_set(long j, baraa baraa, int i);

    public static final native long binarize(long j);

    public static final native int boxDoesIntersect(long j, long j2);

    public static final native long boxScaleChecked(long j, float f);

    public static final native long boxaBoundingRegion(long j);

    public static final native long boxaBoundingRegionByIndexes(long j, long j2);

    public static final native long boxaFindIndexesByBB(long j, long j2, int i);

    public static final native int calculateLine(long j, point point, long j2, point point2, long j3, line line);

    public static final native int calculateLineParamsLS(long j, long j2, long j3);

    public static final native int calculateLineParamsLS2(long j, int i, int i2, long j2, long j3);

    public static final native int countPixels(long j);

    public static final native int countPixelsInRect(long j, int i, int i2, int i3, int i4);

    public static final native float coverage(long j);

    public static final native float coverageInRect(long j, int i, int i2, int i3, int i4);

    public static final native long createSkewedLinePix(int i, int i2, int i3);

    public static final native void delete_PRIM(long j);

    public static final native void delete_PRIMA(long j);

    public static final native void delete_analysisResult(long j);

    public static final native void delete_bar(long j);

    public static final native void delete_bara(long j);

    public static final native void delete_baraa(long j);

    public static final native void delete_dici(long j);

    public static final native void delete_dicipair(long j);

    public static final native void delete_imat(long j);

    public static final native void delete_keySignature(long j);

    public static final native void delete_line(long j);

    public static final native void delete_match(long j);

    public static final native void delete_matcha(long j);

    public static final native void delete_point(long j);

    public static final native void delete_score(long j);

    public static final native void delete_session(long j);

    public static final native void delete_sound(long j);

    public static final native void delete_staff(long j);

    public static final native void delete_staffa(long j);

    public static final native void delete_staffline(long j);

    public static final native void delete_syma(long j);

    public static final native void delete_symbol(long j);

    public static final native void delete_templ(long j);

    public static final native void delete_templa(long j);

    public static final native void delete_templateDic(long j);

    public static final native void delete_timeSignature(long j);

    public static final native void delete_timepoint(long j);

    public static final native void delete_vanishingPoint(long j);

    public static final native long deserialize(String str);

    public static final native long deserializeBar(long j, int i);

    public static final native long deserializeBara(long j, int i);

    public static final native long deserializeBaraa(long j, int i);

    public static final native long deserializeBox(long j, int i);

    public static final native double deserializeDouble(long j);

    public static final native int deserializeInt(long j);

    public static final native long deserializeKeySignature(long j, int i);

    public static final native long deserializeNuma(long j, int i);

    public static final native long deserializePix(long j, int i);

    public static final native long deserializeScore(long j, int i);

    public static final native long deserializeSession(long j, int i);

    public static final native long deserializeSound(long j, int i);

    public static final native long deserializeTP(long j, int i);

    public static final native long deserializeTimeSignature(long j, int i);

    public static final native long diciCreate();

    public static final native long diciCreateWithSize(int i);

    public static final native void diciDestroy(long j);

    public static final native int diciGetValue(long j, dici dici, int i);

    public static final native long diciIteratorCreate(long j, dici dici);

    public static final native void diciIteratorDestroy(long j);

    public static final native long diciIteratorNext(long j);

    public static final native void diciPrint(long j, dici dici);

    public static final native int diciSet(long j, dici dici, int i, int i2);

    public static final native int dici_hashSize_get(long j, dici dici);

    public static final native void dici_hashSize_set(long j, dici dici, int i);

    public static final native long dici_hashTable_get(long j, dici dici);

    public static final native void dici_hashTable_set(long j, dici dici, long j2);

    public static final native long dicipairCreate(int i, int i2);

    public static final native void dicipairDestroy(long j);

    public static final native int dicipair_key_get(long j, dicipair dicipair);

    public static final native void dicipair_key_set(long j, dicipair dicipair, int i);

    public static final native long dicipair_next_get(long j, dicipair dicipair);

    public static final native void dicipair_next_set(long j, dicipair dicipair, long j2, dicipair dicipair2);

    public static final native int dicipair_value_get(long j, dicipair dicipair);

    public static final native void dicipair_value_set(long j, dicipair dicipair, int i);

    public static final native int doLinesOverlap(int i, int i2, int i3, int i4);

    public static final native double durationToSeconds(double d, int i);

    public static final native double durationToSecondsWithStartTime(double d, double d2, int i, int i2);

    public static final native int equalWithTolerance(double d, double d2, double d3);

    public static final native int equalWithToleranceAbs(double d, double d2, double d3);

    public static final native int exportToMusicXml(String str, long j, session session, String str2, String str3, int i, int i2, int i3, int i4);

    public static final native int exportToMusicXml2(String str, long j, session session, String str2, int i, String[] strArr, int[] iArr, int[] iArr2, boolean[][] zArr);

    public static final native float findCorrelationScoreInBB(long j, long j2, long j3, float f, long j4, long j5, long j6);

    public static final native float findCorrelationScoreInBBBlack(long j, long j2, long j3, float f, long j4, long j5, long j6);

    public static final native long findGroups(long j, int i, int i2, int i3, int i4);

    public static final native int findLineEnd(long j, long j2, long j3, long j4, int i, int i2);

    public static final native long findLineGroups(long j, long j2, imat imat, int i);

    public static final native long findLines(long j, imat imat, float f, int i, int i2, int i3, long j2);

    public static final native long findPrimitives(long j, long j2, long j3, long j4, PRIMA prima, long j5, staff staff, long j6, templateDic templatedic, long j7, long j8, long j9);

    public static final native long findShortRest(long j, staff staff, long j2, long j3, long j4, long j5, int i, float f, float f2);

    public static final native long findStaffLines(long j, long j2, long j3, staff staff, int i);

    public static final native int fixBarEnds(long j, baraa baraa, int i, int i2, int i3, long j2, long j3);

    public static final native int fixLineEnds(long j, long j2);

    public static final native int fixPerspective(long j, long j2, long j3, long j4, vanishingPoint vanishingpoint, long j5, vanishingPoint vanishingpoint2, long j6);

    public static final native void freeInt(long j);

    public static final native void freePixPtr(long j);

    public static final native void freeScorePtr(long j);

    public static final native void freeSessionPtr(long j);

    public static final native void freeUint32(long j);

    public static final native long getVoiceCounts(long j, staffa staffa, long j2);

    public static final native long getVoiceIndexes(long j);

    public static final native long imatCreate(int i, int i2);

    public static final native int imatDestroy(long j);

    public static final native int imatFixGaps(long j, imat imat, int i);

    public static final native int imatGet(long j, imat imat, int i, int i2);

    public static final native long imatGetCol(long j, imat imat, int i);

    public static final native int imatGuessLineAndSpaceHeights(long j, imat imat, long j2, long j3);

    public static final native int imatSet(long j, imat imat, int i, int i2, int i3);

    public static final native long imat_data_get(long j, imat imat);

    public static final native void imat_data_set(long j, imat imat, long j2);

    public static final native int imat_height_get(long j, imat imat);

    public static final native void imat_height_set(long j, imat imat, int i);

    public static final native int imat_nalloc_get(long j, imat imat);

    public static final native void imat_nalloc_set(long j, imat imat, int i);

    public static final native int imat_width_get(long j, imat imat);

    public static final native void imat_width_set(long j, imat imat, int i);

    public static final native long isCrotchet(long j, long j2, long j3, staff staff, long j4, templateDic templatedic);

    public static final native int isDurationDot(long j, long j2, long j3, staff staff, long j4);

    public static final native long isFlat(long j, staff staff, long j2, long j3, long j4, long j5, int i, int i2, float f, float f2, long j6, long j7);

    public static final native long isHalfNoteHead(long j, long j2, long j3, PRIM prim, long j4, staff staff, long j5, long j6, long j7, PRIMA prima, long j8, templateDic templatedic, long j9, float f, float f2, float f3, long j10);

    public static final native long isNatural(long j, long j2, long j3, long j4, staff staff, int i, long j5, int i2, int i3, float f, long j6, long j7);

    public static final native long isSharp(long j, long j2, long j3, long j4, staff staff, int i, long j5, int i2, int i3, long j6, long j7);

    public static final native long isTimeSignature(long j, staff staff, long j2, long j3, long j4, long j5, long j6, long j7, long j8);

    public static final native long isWholeNoteHead(long j, long j2, long j3, staff staff, long j4, long j5, PRIMA prima, long j6, templateDic templatedic, float f, float f2, long j7);

    public static final native int kPAccFlat_get();

    public static final native int kPAccNatural_get();

    public static final native int kPAccSharp_get();

    public static final native int kPBarLineThick_get();

    public static final native int kPBarLine_get();

    public static final native int kPClefAlto_get();

    public static final native int kPClefBass_get();

    public static final native int kPClefTreble_get();

    public static final native int kPDurationDot_get();

    public static final native int kPHook16Down_get();

    public static final native int kPHook16Up_get();

    public static final native int kPHook32Down_get();

    public static final native int kPHook32Up_get();

    public static final native int kPHook8Down_get();

    public static final native int kPHook8Up_get();

    public static final native int kPKeySignature_get();

    public static final native int kPNotIdentified_get();

    public static final native int kPNote16_get();

    public static final native int kPNote32_get();

    public static final native int kPNote4_get();

    public static final native int kPNote8_get();

    public static final native int kPNoteBeam_get();

    public static final native int kPNoteHalf_get();

    public static final native int kPNoteStem_get();

    public static final native int kPNoteWhole_get();

    public static final native int kPRest16_get();

    public static final native int kPRest32_get();

    public static final native int kPRest8_get();

    public static final native int kPRestCrotchet_get();

    public static final native int kPRestHalf_get();

    public static final native int kPRestMeasure_get();

    public static final native int kPRestWhole_get();

    public static final native int kPTimeSignature_get();

    public static final native int kSAccFlat_get();

    public static final native int kSAccNatural_get();

    public static final native int kSAccSharp_get();

    public static final native int kSClefAlto_get();

    public static final native int kSClefBass_get();

    public static final native int kSClefTreble_get();

    public static final native int kSDurationDot_get();

    public static final native int kSNotIdentified_get();

    public static final native int kSNote16_get();

    public static final native int kSNote32_get();

    public static final native int kSNote4_get();

    public static final native int kSNote8_get();

    public static final native int kSNoteHalf_get();

    public static final native int kSNoteWhole_get();

    public static final native int kSRest16_get();

    public static final native int kSRest32_get();

    public static final native int kSRest8_get();

    public static final native int kSRestCrotchet_get();

    public static final native int kSRestHalf_get();

    public static final native int kSRestMeasure_get();

    public static final native int kSRestWhole_get();

    public static final native int kSTimeSignatureCommon_get();

    public static final native int kSTimeSignatureCut_get();

    public static final native int kSTimeSignatureNormal_get();

    public static final native int kSTimeSignatureUnIdentified_get();

    public static final native int keySignature_count_get(long j, keySignature keysignature);

    public static final native void keySignature_count_set(long j, keySignature keysignature, int i);

    public static final native int keySignature_isSharps_get(long j, keySignature keysignature);

    public static final native void keySignature_isSharps_set(long j, keySignature keysignature, int i);

    public static final native long ksCopy(long j, keySignature keysignature);

    public static final native long ksCreate(int i, int i2);

    public static final native void ksDestroy(long j);

    public static final native double line_a_get(long j, line line);

    public static final native void line_a_set(long j, line line, double d);

    public static final native double line_b_get(long j, line line);

    public static final native void line_b_set(long j, line line, double d);

    public static final native double line_c_get(long j, line line);

    public static final native void line_c_set(long j, line line, double d);

    public static final native long loadTemplates(float f);

    public static final native long loadTemplatesByStaffHeight(int i);

    public static final native void logBox(String str, String str2, long j, long j2);

    public static final native void logBoxFocused(String str, String str2, long j, long j2, long j3);

    public static final native void logBoxFocused2(String str, String str2, int i, int i2, int i3, int i4, long j);

    public static final native void logBoxa(String str, String str2, long j, long j2);

    public static final native void logBoxaFocused(String str, String str2, long j, long j2, long j3);

    public static final native void logBoxaFocusedDefault(String str, String str2, long j, long j2);

    public static final native void logImatFocused(String str, String str2, long j, int i, int i2, long j2, imat imat, int i3);

    public static final native void logMsg(String str, String str2);

    public static final native void logMsgFocused(String str, String str2, long j);

    public static final native void logMsgFocused2(String str, String str2, int i, int i2, int i3, int i4);

    public static final native void logNumaFFocused(String str, String str2, long j, long j2);

    public static final native void logNumaFocused(String str, String str2, long j, long j2);

    public static final native void logPix(String str, String str2, long j);

    public static final native void logPixFocused(String str, String str2, long j, long j2);

    public static final native void logPrimaMatchesFocused(String str, String str2, long j, long j2, PRIMA prima, int i);

    public static final native int logShouldLog(long j);

    public static final native void logStaff(String str, String str2, long j, long j2, staff staff);

    public static final native long matchCreate();

    public static final native void matchDestroy(long j);

    public static final native void matchPrint(long j, match match);

    public static final native int match_primType_get(long j, match match);

    public static final native void match_primType_set(long j, match match, int i);

    public static final native int match_score_get(long j, match match);

    public static final native void match_score_set(long j, match match, int i);

    public static final native int matchaAdd(long j, matcha matcha, long j2, match match);

    public static final native int matchaClear(long j, matcha matcha);

    public static final native long matchaCreate(int i);

    public static final native void matchaDestroy(long j);

    public static final native long matchaFind(long j, matcha matcha, int i);

    public static final native long matchaGet(long j, matcha matcha, int i);

    public static final native void matchaPrint(long j, matcha matcha);

    public static final native int matchaRemove(long j, matcha matcha, long j2);

    public static final native long matcha_array_get(long j, matcha matcha);

    public static final native void matcha_array_set(long j, matcha matcha, long j2);

    public static final native int matcha_n_get(long j, matcha matcha);

    public static final native void matcha_n_set(long j, matcha matcha, int i);

    public static final native int matcha_nalloc_get(long j, matcha matcha);

    public static final native void matcha_nalloc_set(long j, matcha matcha, int i);

    public static final native long nativeAnalyze(Scanner scanner, float f, float f2, long j, String str, String str2, String str3, String str4, long j2, int i, String str5, int i2);

    public static final native long nativeReadBitmap(Bitmap bitmap);

    public static final native boolean nativeWriteBitmap(long j, Bitmap bitmap, boolean z);

    public static final native long new_PRIM();

    public static final native long new_PRIMA();

    public static final native long new_analysisResult();

    public static final native long new_bar();

    public static final native long new_bara();

    public static final native long new_baraa();

    public static final native long new_dici();

    public static final native long new_dicipair();

    public static final native long new_imat();

    public static final native long new_keySignature();

    public static final native long new_line();

    public static final native long new_match();

    public static final native long new_matcha();

    public static final native long new_point();

    public static final native long new_score();

    public static final native long new_session();

    public static final native long new_sound();

    public static final native long new_staff();

    public static final native long new_staffa();

    public static final native long new_staffline();

    public static final native long new_syma();

    public static final native long new_symbol();

    public static final native long new_templ();

    public static final native long new_templa();

    public static final native long new_templateDic();

    public static final native long new_timeSignature();

    public static final native long new_timepoint();

    public static final native long new_vanishingPoint();

    public static final native int numaAddNumberDistinct(long j, int i);

    public static final native int numaCopyNumbers(long j, long j2);

    public static final native int numaCountEdges(long j, int i, long j2);

    public static final native int numaGetI(long j, int i);

    public static final native int numaGetIndex(long j, int i);

    public static final native long numaGetIndexes(long j, int i);

    public static final native int numaGetMinInRange(long j, int i, int i2, long j2, long j3);

    public static final native void numaSortAndDeduplicate(long j);

    public static final native int numaThreshold(long j, float f);

    public static final native long orderLinesByY(long j);

    public static final native double pitchToHz(int i, int i2);

    public static final native int pitchToMidi(int i);

    public static final native int pixErasePrim(long j, long j2, PRIM prim);

    public static final native long pixGetRow(long j, int i);

    public static final native int pixIsBW(long j);

    public static final native long pixReduceToLines(long j, int i, float f, float f2);

    public static final native void pixRenderPixBW(long j, long j2, int i, int i2, double d);

    public static final native void pixRenderPixWithOffsetAtScale(long j, long j2, int i, int i2, long j3, long j4, long j5, double d);

    public static final native void pixRenderPixa(long j, long j2, long j3, long j4, long j5, long j6);

    public static final native void pixRenderPrim(long j, long j2, PRIM prim, long j3, long j4, long j5);

    public static final native int pixRenderPtaa(long j, long j2);

    public static final native int pixRenderPtaaToLines(long j, long j2, long j3, long j4, long j5);

    public static final native long pixRenderReducedToLines(long j, imat imat, int i);

    public static final native int pixRenderReducedToLines2(long j, long j2, imat imat, int i);

    public static final native void pixRenderStaff(long j, long j2, staff staff);

    public static final native void pixRenderStaffBW(long j, long j2, staff staff, double d);

    public static final native void pixRenderStaffOri(long j, long j2, staff staff, long j3, long j4, long j5);

    public static final native void pixRenderStaffOriAtScale(long j, long j2, staff staff, long j3, long j4, long j5, double d);

    public static final native void pixRenderStaffa(long j, long j2, staffa staffa);

    public static final native void pixRenderSym(long j, long j2, symbol symbol, long j3, long j4, long j5);

    public static final native void pixRenderSymBW(long j, long j2, symbol symbol, int i, int i2, double d);

    public static final native void pixRenderSymWithOffset(long j, long j2, symbol symbol, int i, int i2, long j3, long j4, long j5);

    public static final native void pixRenderSymWithOffsetAtScale(long j, long j2, symbol symbol, int i, int i2, long j3, long j4, long j5, double d);

    public static final native void pixRenderTies(long j, long j2, score score);

    public static final native void pixRenderTiesAtScale(long j, long j2, score score, long j3, long j4, long j5, double d);

    public static final native void pixRenderTiesBW(long j, long j2, score score, double d);

    public static final native void pixRenderTiesForBara(long j, long j2, bara bara);

    public static final native void pixRenderTiesForBaraAtScale(long j, long j2, bara bara, long j3, long j4, long j5, double d);

    public static final native long pixScaleChecked(long j, float f);

    public static final native long pixSplitByStaffPixa(long j, long j2, staffa staffa, long j3);

    public static final native long pixaMerge(long j, long j2);

    public static final native long pixaMergeByIndexes(long j, long j2, long j3);

    public static final native long pixaScale(long j, float f);

    public static final native long playerListNode_bar_get(long j, playerListNode playerlistnode);

    public static final native long playerListNode_score_get(long j, playerListNode playerlistnode);

    public static final native int point_x_get(long j, point point);

    public static final native void point_x_set(long j, point point, int i);

    public static final native int point_y_get(long j, point point);

    public static final native void point_y_set(long j, point point, int i);

    public static final native int preprocess(long j, long j2, long j3, long j4);

    public static final native int preprocess0(long j, long j2, long j3, long j4, long j5, long j6);

    public static final native int primAddMatch(long j, PRIM prim, int i, float f);

    public static final native long primClone(long j, PRIM prim);

    public static final native long primCreate(long j, long j2);

    public static final native long primCreateByClipping(long j, long j2, int i);

    public static final native long primCreateByClippingAndMasking(long j, long j2, long j3, int i);

    public static final native void primDestroy(long j);

    public static final native int primDoesMatch(long j, PRIM prim, int i);

    public static final native void primGetRgb(long j, PRIM prim, long j2, long j3, long j4);

    public static final native void primPrint(long j, PRIM prim);

    public static final native int primRemoveMatch(long j, PRIM prim, int i);

    public static final native int primTypeIsAccidental(int i);

    public static final native int primTypeIsClef(int i);

    public static final native int primTypeIsNote(int i);

    public static final native int primTypeIsRest(int i);

    public static final native int primUpdate(long j, PRIM prim, long j2, long j3, int i);

    public static final native int primaAdd(long j, PRIMA prima, long j2, PRIM prim);

    public static final native void primaClear(long j, PRIMA prima);

    public static final native long primaCreate(int i);

    public static final native long primaCreateWithoutOwnership(int i);

    public static final native void primaDestroy(long j);

    public static final native long primaGet(long j, PRIMA prima, int i);

    public static final native void primaPrint(long j, PRIMA prima);

    public static final native int primaRemove(long j, PRIMA prima, long j2);

    public static final native int primaSort(long j, PRIMA prima, long j2, long j3);

    public static final native int primaSortByIndex(long j, PRIMA prima, long j2);

    public static final native void printBox(long j);

    public static final native void printBoxToTmp(long j, long j2);

    public static final native void printBoxaToTmp(long j, long j2);

    public static final native void printNuma(long j);

    public static final native void printPixToTmp(long j);

    public static final native void printPrimaToTmp(long j, long j2, PRIMA prima, int i);

    public static final native int printPta(long j);

    public static final native int printPtaa(long j);

    public static final native void printStaffToTmp(long j, long j2, staff staff);

    public static final native void printTime();

    public static final native void printTimeElapsed(long j);

    public static final native void printVP(long j, vanishingPoint vanishingpoint);

    public static final native long ptMake(int i, int i2);

    public static final native int readInt(long j);

    public static final native long readPixPtr(long j);

    public static final native long readScorePtr(long j);

    public static final native long readSessionPtr(long j);

    public static final native long readUint32(long j);

    public static final native long recognizeClefAlto(long j, staff staff, long j2, long j3, long j4);

    public static final native long removeVerticalOverlaps(long j, long j2, imat imat, int i);

    public static final native int renderVanishingPoint(long j, long j2, long j3, vanishingPoint vanishingpoint);

    public static final native int rleCollapseBlackRuns(long j, int i);

    public static final native int rleConvexOrConcave(long j);

    public static final native int rleEraseLongEvenRuns(long j, int i);

    public static final native int rleEraseLongOddRuns(long j, int i);

    public static final native int rleEraseShortEvenRuns(long j, int i);

    public static final native int rleEraseShortOddRuns(long j, int i);

    public static final native int rleFindMostFrequent(long j);

    public static final native int rleGetBlackRunCoords(long j, int i, long j2, long j3);

    public static final native int rleGetBlackRunCount(long j);

    public static final native long rleGetColRuns(long j, int i);

    public static final native long rleGetColRunsPartial(long j, int i, int i2, int i3);

    public static final native long rleGetContourBottom(long j);

    public static final native long rleGetContourLeft(long j);

    public static final native long rleGetContourRight(long j);

    public static final native long rleGetContourTop(long j);

    public static final native long rleGetFrequencies(long j);

    public static final native long rleGetNuma(long j);

    public static final native long rleGetRowRuns(long j, int i);

    public static final native long rleGetRuns(long j);

    public static final native long rleSortByFrequenciesDesc(long j, long j2);

    public static final native int rleSumConsecutive(long j, int i, int i2);

    public static final native int roundI(long j);

    public static final native void saveHistogram(long j, int i, String str);

    public static final native int scalePtaa(long j, double d, double d2, int i, int i2);

    public static final native int scoreAssignVoiceIndexesToSounds(long j, score score);

    public static final native int scoreCalculateVoiceIndexes(long j, score score, long j2, staffa staffa, long j3);

    public static final native long scoreCreate();

    public static final native void scoreDestroy(long j);

    public static final native int scoreFindAndInterpretTies(long j, score score, long j2, staffa staffa, long j3, long j4, syma syma);

    public static final native int scoreGetBarIndexes(long j, score score, long j2, bar bar, long j3, long j4);

    public static final native int scoreGetFirstBarStaffIndexForVoice(long j, score score, int i);

    public static final native int scoreGetFirstStaffIndexByGroupIndex(long j, score score, int i);

    public static final native int scoreGetGroupBarIndexes(long j, score score, long j2, bar bar, long j3, long j4);

    public static final native int scoreGetGroupIndexByStaffIndex(long j, score score, int i);

    public static final native int scoreGetVoiceIndex(long j, score score, int i);

    public static final native int scoreIsMultipleVoices(long j, score score);

    public static final native long scoreNextBar(long j, score score, long j2, bar bar);

    public static final native long scoreNextGroupBar(long j, score score, long j2, bar bar);

    public static final native int scoreScale(long j, score score, float f);

    public static final native int scoreSetUpGroupBars(long j, score score);

    public static final native long score_groupBaraa_get(long j, score score);

    public static final native void score_groupBaraa_set(long j, score score, long j2, baraa baraa);

    public static final native long score_singleBaraa_get(long j, score score);

    public static final native void score_singleBaraa_set(long j, score score, long j2, baraa baraa);

    public static final native long score_voiceCounts_get(long j, score score);

    public static final native void score_voiceCounts_set(long j, score score, long j2);

    public static final native long score_voiceIndexes_get(long j, score score);

    public static final native void score_voiceIndexes_set(long j, score score, long j2);

    public static final native int serialize(long j, session session, String str);

    public static final native int serializeBar(long j, long j2, bar bar, int i);

    public static final native int serializeBarV(long j, long j2, bar bar, int i, int i2);

    public static final native int serializeBara(long j, long j2, bara bara, int i);

    public static final native int serializeBaraV(long j, long j2, bara bara, int i, int i2);

    public static final native int serializeBaraa(long j, long j2, baraa baraa, int i);

    public static final native int serializeBaraaV(long j, long j2, baraa baraa, int i, int i2);

    public static final native int serializeBox(long j, long j2, int i);

    public static final native int serializeBoxV(long j, long j2, int i, int i2);

    public static final native int serializeDouble(long j, double d);

    public static final native int serializeInt(long j, int i);

    public static final native int serializeKeySignature(long j, long j2, keySignature keysignature, int i);

    public static final native int serializeKeySignatureV(long j, long j2, keySignature keysignature, int i, int i2);

    public static final native int serializeNuma(long j, long j2, int i);

    public static final native int serializeNumaV(long j, long j2, int i, int i2);

    public static final native int serializePix(long j, long j2, int i);

    public static final native int serializePixV(long j, long j2, int i, int i2);

    public static final native int serializeScore(long j, long j2, score score, int i);

    public static final native int serializeScoreV(long j, long j2, score score, int i, int i2);

    public static final native int serializeSession(long j, long j2, session session, int i);

    public static final native int serializeSessionV(long j, long j2, session session, int i, int i2);

    public static final native int serializeSound(long j, long j2, sound sound, int i);

    public static final native int serializeSoundV(long j, long j2, sound sound, int i, int i2);

    public static final native int serializeTP(long j, long j2, timepoint timepoint, int i);

    public static final native int serializeTPV(long j, long j2, timepoint timepoint, int i, int i2);

    public static final native int serializeTimeSignature(long j, long j2, timeSignature timesignature, int i);

    public static final native int serializeTimeSignatureV(long j, long j2, timeSignature timesignature, int i, int i2);

    public static final native int serializeV(long j, session session, String str, double d);

    public static final native int sessionAdd(long j, session session, long j2, score score);

    public static final native int sessionAlignVoiceIndexes(long j, session session, int i);

    public static final native long sessionCreate();

    public static final native long sessionCreateMetronomeCountInIntervalsForBar(long j, session session, long j2, bar bar);

    public static final native long sessionCreateMetronomeIntervalsForBar(long j, session session, long j2, bar bar);

    public static final native void sessionDestroy(long j);

    public static final native long sessionFindLastTS(long j, int i, int i2);

    public static final native long sessionGet(long j, session session, int i);

    public static final native long sessionGetFirstNonEmptyGroupBarBar(long j);

    public static final native long sessionGetFirstNonEmptyGroupBarScore(long j);

    public static final native int sessionGetMaxVoiceIndex(long j, session session);

    public static final native long sessionGetScoreForFirstBarForVoice(long j, session session, int i, long j2);

    public static final native int sessionGetScoreIndex(long j, session session, long j2, score score);

    public static final native long sessionGetTimeSignatureCopy(long j, session session);

    public static final native int sessionGetVoiceSubindexSplitCount(long j, session session, int i);

    public static final native int sessionInsert(long j, session session, long j2, score score, int i);

    public static final native int sessionIsMultipleVoices(long j, session session);

    public static final native int sessionRemove(long j, session session, long j2);

    public static final native long sessionStateMoveToNextBar(long j);

    public static final native int sessionStateSetCurrentBar(long j, long j2, long j3, int i);

    public static final native long session_array_get(long j, session session);

    public static final native void session_array_set(long j, session session, long j2);

    public static final native int session_n_get(long j, session session);

    public static final native void session_n_set(long j, session session, int i);

    public static final native int session_nalloc_get(long j, session session);

    public static final native void session_nalloc_set(long j, session session, int i);

    public static final native double session_serializerVersionAtCreation_get(long j, session session);

    public static final native void session_serializerVersionAtCreation_set(long j, session session, double d);

    public static final native long soundClone(long j, sound sound, long j2, timepoint timepoint);

    public static final native long soundCopy(long j, sound sound, long j2, timepoint timepoint);

    public static final native long soundCreate(int i, int i2, double d, long j, long j2, int i3, int i4, int i5);

    public static final native void soundDestroy(long j);

    public static final native int soundScale(long j, sound sound, float f);

    public static final native int sound_accidentalType_get(long j, sound sound);

    public static final native void sound_accidentalType_set(long j, sound sound, int i);

    public static final native int sound_beamCountDL_get(long j, sound sound);

    public static final native void sound_beamCountDL_set(long j, sound sound, int i);

    public static final native int sound_beamCountDR_get(long j, sound sound);

    public static final native void sound_beamCountDR_set(long j, sound sound, int i);

    public static final native int sound_beamCountUL_get(long j, sound sound);

    public static final native void sound_beamCountUL_set(long j, sound sound, int i);

    public static final native int sound_beamCountUR_get(long j, sound sound);

    public static final native void sound_beamCountUR_set(long j, sound sound, int i);

    public static final native int sound_beamHookCountDL_get(long j, sound sound);

    public static final native void sound_beamHookCountDL_set(long j, sound sound, int i);

    public static final native int sound_beamHookCountDR_get(long j, sound sound);

    public static final native void sound_beamHookCountDR_set(long j, sound sound, int i);

    public static final native int sound_beamHookCountUL_get(long j, sound sound);

    public static final native void sound_beamHookCountUL_set(long j, sound sound, int i);

    public static final native int sound_beamHookCountUR_get(long j, sound sound);

    public static final native void sound_beamHookCountUR_set(long j, sound sound, int i);

    public static final native long sound_box_get(long j, sound sound);

    public static final native void sound_box_set(long j, sound sound, long j2);

    public static final native double sound_displayLength_get(long j, sound sound);

    public static final native void sound_displayLength_set(long j, sound sound, double d);

    public static final native int sound_dotCount_get(long j, sound sound);

    public static final native void sound_dotCount_set(long j, sound sound, int i);

    public static final native long sound_firstTiedNote_get(long j, sound sound);

    public static final native void sound_firstTiedNote_set(long j, sound sound, long j2, sound sound2);

    public static final native int sound_headY0_get(long j, sound sound);

    public static final native void sound_headY0_set(long j, sound sound, int i);

    public static final native int sound_headY1_get(long j, sound sound);

    public static final native void sound_headY1_set(long j, sound sound, int i);

    public static final native int sound_id_get(long j, sound sound);

    public static final native void sound_id_set(long j, sound sound, int i);

    public static final native int sound_isRest_get(long j, sound sound);

    public static final native void sound_isRest_set(long j, sound sound, int i);

    public static final native int sound_isTiedWithPrevious_get(long j, sound sound);

    public static final native void sound_isTiedWithPrevious_set(long j, sound sound, int i);

    public static final native int sound_lengthConfirmedByBeam_get(long j, sound sound);

    public static final native void sound_lengthConfirmedByBeam_set(long j, sound sound, int i);

    public static final native double sound_length_get(long j, sound sound);

    public static final native void sound_length_set(long j, sound sound, double d);

    public static final native long sound_nextTiedNote_get(long j, sound sound);

    public static final native void sound_nextTiedNote_set(long j, sound sound, long j2, sound sound2);

    public static final native int sound_pitchWithoutAccidentals_get(long j, sound sound);

    public static final native void sound_pitchWithoutAccidentals_set(long j, sound sound, int i);

    public static final native int sound_pitch_get(long j, sound sound);

    public static final native void sound_pitch_set(long j, sound sound, int i);

    public static final native long sound_pix_get(long j, sound sound);

    public static final native void sound_pix_set(long j, sound sound, long j2);

    public static final native double sound_shortLength_get(long j, sound sound);

    public static final native void sound_shortLength_set(long j, sound sound, double d);

    public static final native long sound_tieBox_get(long j, sound sound);

    public static final native void sound_tieBox_set(long j, sound sound, long j2);

    public static final native long sound_tiePix_get(long j, sound sound);

    public static final native void sound_tiePix_set(long j, sound sound, long j2);

    public static final native int sound_type_get(long j, sound sound);

    public static final native void sound_type_set(long j, sound sound, int i);

    public static final native int sound_voiceIndex_get(long j, sound sound);

    public static final native void sound_voiceIndex_set(long j, sound sound, int i);

    public static final native int sound_voiceSubindexSplit_get(long j, sound sound);

    public static final native int sound_volume_get(long j, sound sound);

    public static final native void sound_volume_set(long j, sound sound, int i);

    public static final native int sound_x0_get(long j, sound sound);

    public static final native void sound_x0_set(long j, sound sound, int i);

    public static final native int sound_x1_get(long j, sound sound);

    public static final native void sound_x1_set(long j, sound sound, int i);

    public static final native int staffAlignYToGrid(long j, staff staff, int i, int i2);

    public static final native long staffBoundingBox(long j, staff staff);

    public static final native long staffCreate(int i);

    public static final native int staffDestroy(long j);

    public static final native float staffGetYByIndex(long j, staff staff, int i, int i2);

    public static final native int staffGetYIndex(long j, staff staff, int i, float f);

    public static final native int staffGetYIndexChecked(long j, staff staff, int i, float f);

    public static final native int staffHeightAtX(long j, staff staff, int i);

    public static final native int staffIsBoxOnLine(long j, staff staff, long j2);

    public static final native int staffIsBoxWithinXRange(long j, staff staff, long j2);

    public static final native int staffIsPointOnLine(long j, staff staff, int i, float f);

    public static final native int staffIsPointOnLineAccurate(long j, staff staff, int i, int i2);

    public static final native int staffIsWithinXRange(long j, staff staff, int i);

    public static final native float staffLineHeight(long j, staff staff);

    public static final native float staffLineHeightAtX(long j, staff staff, int i);

    public static final native int staffLineYs(long j, staff staff, int i, int i2, long j2, long j3);

    public static final native void staffPrint(long j, staff staff);

    public static final native int staffRemoveWithScan(long j, long j2, staff staff, int i, int i2);

    public static final native int staffSimpleClear(long j, long j2, staff staff, int i);

    public static final native float staffSpaceHeight(long j, staff staff);

    public static final native float staffSpaceHeightAtX(long j, staff staff, int i);

    public static final native int staffXMax(long j, staff staff);

    public static final native int staffYs(long j, staff staff, int i, long j2, long j3);

    public static final native int staff_index_get(long j, staff staff);

    public static final native void staff_index_set(long j, staff staff, int i);

    public static final native long staff_lines_get(long j, staff staff);

    public static final native void staff_lines_set(long j, staff staff, long j2);

    public static final native int staff_xOri_get(long j, staff staff);

    public static final native void staff_xOri_set(long j, staff staff, int i);

    public static final native int staff_yOri_get(long j, staff staff);

    public static final native void staff_yOri_set(long j, staff staff, int i);

    public static final native int staffaAdd(long j, staffa staffa, long j2, staff staff);

    public static final native long staffaCreate(int i);

    public static final native void staffaDestroy(long j);

    public static final native long staffaDetect(long j, int i, long j2, long j3);

    public static final native long staffaGet(long j, staffa staffa, int i);

    public static final native void staffaPrint(long j, staffa staffa);

    public static final native int staffaRemoveAtIndex(long j, staffa staffa, int i);

    public static final native long staffaSplitPixByCenters(long j, long j2, staffa staffa);

    public static final native long staffaSplitPixByMinima(long j, long j2, staffa staffa);

    public static final native long staffa_array_get(long j, staffa staffa);

    public static final native void staffa_array_set(long j, staffa staffa, long j2);

    public static final native int staffa_n_get(long j, staffa staffa);

    public static final native void staffa_n_set(long j, staffa staffa, int i);

    public static final native int staffa_nalloc_get(long j, staffa staffa);

    public static final native void staffa_nalloc_set(long j, staffa staffa, int i);

    public static final native int stafflineBottomY(long j, staffline staffline, int i);

    public static final native float stafflineCenterY(long j, staffline staffline, int i);

    public static final native int stafflineClear(long j, staffline staffline);

    public static final native long stafflineCreate(int i);

    public static final native int stafflineDestroy(long j);

    public static final native int stafflineGet(long j, staffline staffline, int i, long j2, long j3);

    public static final native int stafflineHeight(long j, staffline staffline, int i);

    public static final native int stafflineIsXInRange(long j, staffline staffline, int i);

    public static final native void stafflinePrint(long j, staffline staffline);

    public static final native int stafflineSet(long j, staffline staffline, int i, int i2, int i3);

    public static final native int stafflineTopY(long j, staffline staffline, int i);

    public static final native long staffline_bottomYs_get(long j, staffline staffline);

    public static final native void staffline_bottomYs_set(long j, staffline staffline, long j2);

    public static final native int staffline_hWidth_get(long j, staffline staffline);

    public static final native void staffline_hWidth_set(long j, staffline staffline, int i);

    public static final native long staffline_topYs_get(long j, staffline staffline);

    public static final native void staffline_topYs_set(long j, staffline staffline, long j2);

    public static final native int staffline_xLeft_get(long j, staffline staffline);

    public static final native void staffline_xLeft_set(long j, staffline staffline, int i);

    public static final native String stringFormat(String str);

    public static final native String stringFormatV(String str, String str2);

    public static final native String substring(String str, long j, long j2);

    public static final native long symClone(long j, symbol symbol);

    public static final native long symCreate(long j, PRIM prim, int i);

    public static final native void symDestroy(long j);

    public static final native void symGetRgb(long j, symbol symbol, long j2, long j3, long j4);

    public static final native int symIsDefaultClef(long j, symbol symbol);

    public static final native int symMergeWithPrim(long j, symbol symbol, long j2, PRIM prim);

    public static final native void symPrint(long j, symbol symbol);

    public static final native int symaAdd(long j, syma syma, long j2, symbol symbol);

    public static final native long symaCreate(int i);

    public static final native void symaDestroy(long j);

    public static final native long symaGet(long j, syma syma, int i);

    public static final native int symaGetIndex(long j, syma syma, long j2, symbol symbol);

    public static final native void symaPrint(long j, syma syma);

    public static final native int symaRemove(long j, syma syma, long j2);

    public static final native int symaSort(long j, syma syma, long j2, long j3);

    public static final native int symaSortByIndex(long j, syma syma, long j2);

    public static final native long syma_array_get(long j, syma syma);

    public static final native void syma_array_set(long j, syma syma, long j2);

    public static final native int syma_n_get(long j, syma syma);

    public static final native void syma_n_set(long j, syma syma, int i);

    public static final native int syma_nalloc_get(long j, syma syma);

    public static final native void syma_nalloc_set(long j, syma syma, int i);

    public static final native double symbolTypeDuration(int i);

    public static final native double symbolTypeDurationWithDots(int i, int i2);

    public static final native int symbolTypeIsAccidental(int i);

    public static final native int symbolTypeIsClef(int i);

    public static final native int symbolTypeIsNote(int i);

    public static final native int symbolTypeIsRest(int i);

    public static final native int symbolTypeIsTimeSignature(int i);

    public static final native int symbol_beamCountDL_get(long j, symbol symbol);

    public static final native void symbol_beamCountDL_set(long j, symbol symbol, int i);

    public static final native int symbol_beamCountDR_get(long j, symbol symbol);

    public static final native void symbol_beamCountDR_set(long j, symbol symbol, int i);

    public static final native int symbol_beamCountUL_get(long j, symbol symbol);

    public static final native void symbol_beamCountUL_set(long j, symbol symbol, int i);

    public static final native int symbol_beamCountUR_get(long j, symbol symbol);

    public static final native void symbol_beamCountUR_set(long j, symbol symbol, int i);

    public static final native int symbol_beamHookCountDL_get(long j, symbol symbol);

    public static final native void symbol_beamHookCountDL_set(long j, symbol symbol, int i);

    public static final native int symbol_beamHookCountDR_get(long j, symbol symbol);

    public static final native void symbol_beamHookCountDR_set(long j, symbol symbol, int i);

    public static final native int symbol_beamHookCountUL_get(long j, symbol symbol);

    public static final native void symbol_beamHookCountUL_set(long j, symbol symbol, int i);

    public static final native int symbol_beamHookCountUR_get(long j, symbol symbol);

    public static final native void symbol_beamHookCountUR_set(long j, symbol symbol, int i);

    public static final native long symbol_box_get(long j, symbol symbol);

    public static final native void symbol_box_set(long j, symbol symbol, long j2);

    public static final native int symbol_dotCount_get(long j, symbol symbol);

    public static final native void symbol_dotCount_set(long j, symbol symbol, int i);

    public static final native int symbol_extendDotBox_get(long j, symbol symbol);

    public static final native void symbol_extendDotBox_set(long j, symbol symbol, int i);

    public static final native int symbol_hasCurlyBeam_get(long j, symbol symbol);

    public static final native void symbol_hasCurlyBeam_set(long j, symbol symbol, int i);

    public static final native float symbol_headCenterX_get(long j, symbol symbol);

    public static final native void symbol_headCenterX_set(long j, symbol symbol, float f);

    public static final native float symbol_headCenterY_get(long j, symbol symbol);

    public static final native void symbol_headCenterY_set(long j, symbol symbol, float f);

    public static final native int symbol_headHasStemDown_get(long j, symbol symbol);

    public static final native void symbol_headHasStemDown_set(long j, symbol symbol, int i);

    public static final native int symbol_headHasStemUp_get(long j, symbol symbol);

    public static final native void symbol_headHasStemUp_set(long j, symbol symbol, int i);

    public static final native int symbol_headStemX0Ori_get(long j, symbol symbol);

    public static final native void symbol_headStemX0Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_headStemX1Ori_get(long j, symbol symbol);

    public static final native void symbol_headStemX1Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_headY0Ori_get(long j, symbol symbol);

    public static final native void symbol_headY0Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_headY0_get(long j, symbol symbol);

    public static final native void symbol_headY0_set(long j, symbol symbol, int i);

    public static final native int symbol_headY1Ori_get(long j, symbol symbol);

    public static final native void symbol_headY1Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_headY1_get(long j, symbol symbol);

    public static final native void symbol_headY1_set(long j, symbol symbol, int i);

    public static final native int symbol_isLastHeadOnStem_get(long j, symbol symbol);

    public static final native void symbol_isLastHeadOnStem_set(long j, symbol symbol, int i);

    public static final native int symbol_isNoteLengthRecognized_get(long j, symbol symbol);

    public static final native void symbol_isNoteLengthRecognized_set(long j, symbol symbol, int i);

    public static final native long symbol_pix_get(long j, symbol symbol);

    public static final native void symbol_pix_set(long j, symbol symbol, long j2);

    public static final native long symbol_prima_get(long j, symbol symbol);

    public static final native void symbol_prima_set(long j, symbol symbol, long j2, PRIMA prima);

    public static final native int symbol_refcount_get(long j, symbol symbol);

    public static final native void symbol_refcount_set(long j, symbol symbol, int i);

    public static final native int symbol_shortestStemType_get(long j, symbol symbol);

    public static final native void symbol_shortestStemType_set(long j, symbol symbol, int i);

    public static final native long symbol_stemBeamGroupIds_get(long j, symbol symbol);

    public static final native void symbol_stemBeamGroupIds_set(long j, symbol symbol, long j2);

    public static final native long symbol_stemBeamOrders_get(long j, symbol symbol);

    public static final native void symbol_stemBeamOrders_set(long j, symbol symbol, long j2);

    public static final native int symbol_tsBeatType_get(long j, symbol symbol);

    public static final native void symbol_tsBeatType_set(long j, symbol symbol, int i);

    public static final native int symbol_tsBeats_get(long j, symbol symbol);

    public static final native void symbol_tsBeats_set(long j, symbol symbol, int i);

    public static final native int symbol_type_get(long j, symbol symbol);

    public static final native void symbol_type_set(long j, symbol symbol, int i);

    public static final native int symbol_x0Ori_get(long j, symbol symbol);

    public static final native void symbol_x0Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_x1Ori_get(long j, symbol symbol);

    public static final native void symbol_x1Ori_set(long j, symbol symbol, int i);

    public static final native int symbol_yIndex_get(long j, symbol symbol);

    public static final native void symbol_yIndex_set(long j, symbol symbol, int i);

    public static final native long templClone(long j, templ templ);

    public static final native long templCreate(long j, int i);

    public static final native void templDestroy(long j);

    public static final native int templ_area_get(long j, templ templ);

    public static final native void templ_area_set(long j, templ templ, int i);

    public static final native long templ_pix_get(long j, templ templ);

    public static final native void templ_pix_set(long j, templ templ, long j2);

    public static final native int templ_refCount_get(long j, templ templ);

    public static final native void templ_refCount_set(long j, templ templ, int i);

    public static final native int templ_type_get(long j, templ templ);

    public static final native void templ_type_set(long j, templ templ, int i);

    public static final native int templaAdd(long j, templa templa, long j2, templ templ);

    public static final native long templaCreate(int i);

    public static final native void templaDestroy(long j);

    public static final native long templaFindType(long j, templa templa, int i);

    public static final native long templaFindTypes(long j, templa templa, long j2);

    public static final native long templaGet(long j, templa templa, int i);

    public static final native long templaLoad(float f);

    public static final native long templaLoadByStaffHeight(int i);

    public static final native long templa_array_get(long j, templa templa);

    public static final native void templa_array_set(long j, templa templa, long j2);

    public static final native int templa_n_get(long j, templa templa);

    public static final native void templa_n_set(long j, templa templa, int i);

    public static final native int templa_nalloc_get(long j, templa templa);

    public static final native void templa_nalloc_set(long j, templa templa, int i);

    public static final native int templateDicAdd(long j, templateDic templatedic, int i, long j2);

    public static final native long templateDicCreate(int i);

    public static final native void templateDicDestroy(long j);

    public static final native long templateDicGet(long j, templateDic templatedic, int i, long j2);

    public static final native long templateDic_areasa_get(long j, templateDic templatedic);

    public static final native void templateDic_areasa_set(long j, templateDic templatedic, long j2);

    public static final native long templateDic_pixaa_get(long j, templateDic templatedic);

    public static final native void templateDic_pixaa_set(long j, templateDic templatedic, long j2);

    public static final native long templateDic_typea_get(long j, templateDic templatedic);

    public static final native void templateDic_typea_set(long j, templateDic templatedic, long j2);

    public static final native double timePointToSeconds(double d, int i, int i2);

    public static final native int timeSignature_beatType_get(long j, timeSignature timesignature);

    public static final native void timeSignature_beatType_set(long j, timeSignature timesignature, int i);

    public static final native int timeSignature_beats_get(long j, timeSignature timesignature);

    public static final native void timeSignature_beats_set(long j, timeSignature timesignature, int i);

    public static final native int timeSignature_type_get(long j, timeSignature timesignature);

    public static final native void timeSignature_type_set(long j, timeSignature timesignature, int i);

    public static final native long timepoint_beamGroupIds_get(long j, timepoint timepoint);

    public static final native void timepoint_beamGroupIds_set(long j, timepoint timepoint, long j2);

    public static final native long timepoint_beamOrders_get(long j, timepoint timepoint);

    public static final native void timepoint_beamOrders_set(long j, timepoint timepoint, long j2);

    public static final native int timepoint_doesOwnSounds_get(long j, timepoint timepoint);

    public static final native void timepoint_doesOwnSounds_set(long j, timepoint timepoint, int i);

    public static final native int timepoint_isVerified_get(long j, timepoint timepoint);

    public static final native void timepoint_isVerified_set(long j, timepoint timepoint, int i);

    public static final native int timepoint_n_get(long j, timepoint timepoint);

    public static final native void timepoint_n_set(long j, timepoint timepoint, int i);

    public static final native int timepoint_nalloc_get(long j, timepoint timepoint);

    public static final native void timepoint_nalloc_set(long j, timepoint timepoint, int i);

    public static final native long timepoint_sounds_get(long j, timepoint timepoint);

    public static final native void timepoint_sounds_set(long j, timepoint timepoint, long j2);

    public static final native double timepoint_startTime_get(long j, timepoint timepoint);

    public static final native void timepoint_startTime_set(long j, timepoint timepoint, double d);

    public static final native double timepoint_timeToNext_get(long j, timepoint timepoint);

    public static final native void timepoint_timeToNext_set(long j, timepoint timepoint, double d);

    public static final native int timepoint_x0_get(long j, timepoint timepoint);

    public static final native void timepoint_x0_set(long j, timepoint timepoint, int i);

    public static final native int timepoint_x1_get(long j, timepoint timepoint);

    public static final native void timepoint_x1_set(long j, timepoint timepoint, int i);

    public static final native int tpAddSound(long j, timepoint timepoint, long j2, sound sound);

    public static final native long tpClone(long j, timepoint timepoint, long j2, timepoint timepoint2);

    public static final native long tpCopy(long j, timepoint timepoint, long j2, timepoint timepoint2);

    public static final native long tpCreate(double d, double d2);

    public static final native void tpDestroy(long j);

    public static final native long tpGetSound(long j, timepoint timepoint, int i);

    public static final native int tpScale(long j, timepoint timepoint, float f);

    public static final native long tsCopy(long j, timeSignature timesignature);

    public static final native long tsCreateCommon();

    public static final native long tsCreateNormal(int i, int i2);

    public static final native void tsDestroy(long j);

    public static final native long vanishingPoint_angleIfInfinite_get(long j, vanishingPoint vanishingpoint);

    public static final native void vanishingPoint_angleIfInfinite_set(long j, vanishingPoint vanishingpoint, long j2);

    public static final native int vanishingPoint_isInfinite_get(long j, vanishingPoint vanishingpoint);

    public static final native void vanishingPoint_isInfinite_set(long j, vanishingPoint vanishingpoint, int i);

    public static final native int vanishingPoint_isSlopeVerticalIfInfinite_get(long j, vanishingPoint vanishingpoint);

    public static final native void vanishingPoint_isSlopeVerticalIfInfinite_set(long j, vanishingPoint vanishingpoint, int i);

    public static final native int vanishingPoint_x_get(long j, vanishingPoint vanishingpoint);

    public static final native void vanishingPoint_x_set(long j, vanishingPoint vanishingpoint, int i);

    public static final native int vanishingPoint_y_get(long j, vanishingPoint vanishingpoint);

    public static final native void vanishingPoint_y_set(long j, vanishingPoint vanishingpoint, int i);

    public static final native void writeInt(long j, int i);

    public static final native void writePixPtr(long j, long j2);

    public static final native void writeScorePtr(long j, long j2);

    public static final native void writeSessionPtr(long j, long j2);

    public static final native void writeUint32(long j, long j2);

    static {
        System.loadLibrary("source-lib");
    }
}
