/**
 * On-Device OMR Engine — Zemsky-inspired rule-based music recognition.
 *
 * Modules:
 *   - ImageUtils:      Binary image operations (binarize, RLE, CC, morphology)
 *   - StaffDetector:   Staff line detection via column-sliced projection
 *   - StaffRemover:    Staff line erasure preserving intersecting symbols
 *   - SymbolDetector:  Notehead/stem/beam/rest/dot/accidental detection
 *   - DurationAssigner: Duration from beam count, pitch from staff position
 *   - MusicXMLExporter: Standard MusicXML 4.0 output
 *   - OnDeviceOMR:     Main orchestrator tying the full pipeline together
 */

export { OnDeviceOMR } from './OnDeviceOMR';
export { detectStaffs, getStaffPosition } from './StaffDetector';
export { removeStaffLines } from './StaffRemover';
export {
  detectSymbols,
  associateStemsWithHeads,
  associateBeamsWithStems,
  associateDotsWithNotes,
  associateAccidentalsWithNotes,
} from './SymbolDetector';
export { assignDurations, staffPositionToPitch, refineVoices } from './DurationAssigner';
export { exportToMusicXML } from './MusicXMLExporter';
export * from './ImageUtils';
