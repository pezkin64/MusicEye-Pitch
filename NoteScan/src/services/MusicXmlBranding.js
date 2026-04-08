const SOFTWARE_TAG = '<software>Music-eye-zem</software>';

/**
 * Normalize MusicXML software metadata across all engines.
 * - Fixes malformed closing tags like </software>>
 * - Replaces existing software tags with Music-eye-zem
 * - Inserts a software tag under <encoding> when missing
 */
export function normalizeMusicXmlSoftwareTag(musicXml) {
  if (typeof musicXml !== 'string' || musicXml.length === 0) {
    return musicXml;
  }

  let xml = musicXml.replace(/<\/software>\s*>/g, '</software>');

  if (/<software\b[^>]*>[\s\S]*?<\/software>/i.test(xml)) {
    return xml.replace(/<software\b[^>]*>[\s\S]*?<\/software>/gi, SOFTWARE_TAG);
  }

  if (/<encoding\b[^>]*>/i.test(xml)) {
    return xml.replace(/<encoding\b[^>]*>/i, (match) => `${match}\n      ${SOFTWARE_TAG}`);
  }

  return xml;
}

/**
 * Inject deterministic xml:id attributes into note elements when missing.
 * This gives the parser and Verovio a shared stable id space for playback highlighting.
 */
export function injectDeterministicNoteIds(musicXml, prefix = 'n') {
  if (typeof musicXml !== 'string' || musicXml.length === 0) {
    return musicXml;
  }

  let noteIndex = 0;
  return musicXml.replace(/<note\b([^>]*)>/gi, (match, attrs) => {
    if (/\bxml:id\s*=\s*"[^"]+"/i.test(attrs) || /\bid\s*=\s*"[^"]+"/i.test(attrs)) {
      return match;
    }

    noteIndex += 1;
    const safeId = `${prefix}${noteIndex}`;
    return `<note xml:id="${safeId}"${attrs}>`;
  });
}
