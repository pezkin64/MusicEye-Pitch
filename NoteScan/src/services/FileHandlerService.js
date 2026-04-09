/**
 * File Handler Service
 *
 * Handles different file types (images, PDFs, etc.)
 * and converts them to a format that can be processed by the OMR engine.
 */
import { File } from 'expo-file-system/next';
import * as FileSystem from 'expo-file-system';

export const FileTypes = {
  IMAGE_PNG: 'image/png',
  IMAGE_JPEG: 'image/jpeg',
  IMAGE_JPG: 'image/jpg',
  PDF: 'application/pdf',
};

const SUPPORTED_EXTENSIONS = {
  'png': FileTypes.IMAGE_PNG,
  'jpg': FileTypes.IMAGE_JPEG,
  'jpeg': FileTypes.IMAGE_JPEG,
  'pdf': FileTypes.PDF,
};

class FileHandlerServiceClass {
  /**
   * Detect file type from URI (by extension)
   */
  getFileType(fileUri) {
    if (!fileUri) return null;
    const filename = fileUri.split('/').pop() || '';
    const ext = filename.split('.').pop()?.toLowerCase() || '';
    return SUPPORTED_EXTENSIONS[ext] || null;
  }

  /**
   * Get file extension from URI
   */
  getFileExtension(fileUri) {
    if (!fileUri) return null;
    const filename = fileUri.split('/').pop() || '';
    return filename.split('.').pop()?.toLowerCase() || null;
  }

  /**
   * Check if file type is an image (processable by OMR directly)
   */
  isImage(fileUri) {
    const type = this.getFileType(fileUri);
    return type === FileTypes.IMAGE_PNG || 
           type === FileTypes.IMAGE_JPEG || 
           type === FileTypes.IMAGE_JPG;
  }

  /**
   * Check if file type is a PDF
   */
  isPDF(fileUri) {
    return this.getFileType(fileUri) === FileTypes.PDF;
  }

  /**
   * Get human-readable file type label
   */
  getFileTypeLabel(fileUri) {
    const ext = this.getFileExtension(fileUri);
    const typeMap = {
      'png': 'PNG Image',
      'jpg': 'JPEG Image',
      'jpeg': 'JPEG Image',
      'pdf': 'PDF Document',
    };
    return typeMap[ext] || 'Unknown File';
  }

  /**
   * Check if file is supported
   */
  isSupported(fileUri) {
    const type = this.getFileType(fileUri);
    return type !== null;
  }

  /**
   * Get file info (size, name, type)
   */
  async getFileInfo(fileUri) {
    try {
      const file = new File(fileUri);
      const filename = fileUri.split('/').pop() || 'unknown';
      const ext = this.getFileExtension(fileUri);
      const type = this.getFileType(fileUri);

      return {
        uri: fileUri,
        filename,
        extension: ext,
        type,
        typeLabel: this.getFileTypeLabel(fileUri),
        size: file.size,
        sizeKb: Math.round((file.size || 0) / 1024),
        isImage: this.isImage(fileUri),
        isPDF: this.isPDF(fileUri),
      };
    } catch (e) {
      console.warn('FileHandlerService: getFileInfo failed', e.message);
      return null;
    }
  }

  /**
   * For PDFs, provide guidance on handling
   * In the future, this could integrate with a PDF extraction library
   */
  getPDFInstructions() {
    return [
      `1. PDF processing works best when each page is converted to an image`,
      `2. You can either:`,
      `   • Use your device's Photos app to convert PDF pages to images`,
      `   • Use a PDF reader to export individual pages`,
      `   • Upload the PDF and we'll process it page-by-page`,
      `3. Or, select individual sheet music images directly`,
    ];
  }
}

export const FileHandlerService = new FileHandlerServiceClass();
