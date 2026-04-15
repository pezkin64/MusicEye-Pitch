package com.xemsoft.sheetmusicscanner2.export;

public class ExportTypes {
    public static final int EXPORT_TYPE_M4A = 2;
    public static final int EXPORT_TYPE_MIDI = 1;
    public static final int EXPORT_TYPE_MP3 = 3;
    public static final int EXPORT_TYPE_MUSICXML = 5;
    public static final int EXPORT_TYPE_MUSICXML2 = 8;
    public static final int EXPORT_TYPE_PDF = 6;
    public static final int EXPORT_TYPE_SCANNER = 7;
    public static final int EXPORT_TYPE_SCANNER_MULTI = 8;
    public static final int EXPORT_TYPE_WAV = 4;

    public static String getMimeType(int i) {
        switch (i) {
            case 1:
                return "audio/midi";
            case 2:
                return "audio/mp4";
            case 3:
                return "audio/mpeg3";
            case 4:
                return "audio/x-wav";
            case 5:
            case 8:
                return "application/vnd.recordare.musicxml+xml";
            case 6:
                return "application/pdf";
            case 7:
                return "application/ie.xemsoft.scoreplayer.msca";
            default:
                return null;
        }
    }

    public static String getExtension(int i) {
        switch (i) {
            case 1:
                return ".mid";
            case 2:
                return ".m4a";
            case 3:
                return ".mp3";
            case 4:
                return ".wav";
            case 5:
                return ".musicxml";
            case 6:
                return ".pdf";
            case 7:
                return ".msca";
            case 8:
                return ".xml";
            default:
                return null;
        }
    }
}
