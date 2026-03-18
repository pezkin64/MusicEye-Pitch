# Music Eye Commercial Readiness Checklist

This checklist defines release gates for serious quality and commercial readiness.
Use it for every release candidate (RC). A release should not ship unless all P0 gates pass.

## 1) Product Quality Gates (P0)

### 1.1 OMR Accuracy Gate
- Goal: measurable and repeatable quality, not anecdotal quality.
- Required:
  - Run evaluator on fixed benchmark set (same images every run).
  - Track: IoU, precision, recall, F1.
  - No release if aggregate IoU or F1 regresses vs previous release.
  - No release if worst-10 pages regress by more than 5% absolute IoU.

### 1.2 MusicXML Validity Gate
- Goal: generated XML must be ingestible by common notation tools and internal parser.
- Required:
  - 100% XML files parse without fatal errors by app parser.
  - 100% XML includes score-partwise root.
  - No release if parser failures increase vs previous release.

### 1.3 Crash-Free Scanning Gate
- Goal: stable scan experience on target Android devices.
- Required:
  - 50 consecutive scans on test set with no crash.
  - 0 native crashes in OMR path.
  - No release if crash-free rate < 99.5% in validation runs.

### 1.4 Latency Gate
- Goal: predictable user experience.
- Required:
  - P50 and P95 scan time recorded by dataset.
  - P95 under agreed threshold on reference device tier.
  - No release if P95 regresses by > 15% vs previous release.

### 1.5 Error Handling Gate
- Goal: graceful failure with actionable guidance.
- Required:
  - For each known native result code, user-facing message is mapped and understandable.
  - No raw internal exceptions shown to user.
  - Retries/fallbacks enabled for known transient failure modes.

## 2) OMR Parity Gates (P0/P1)

### 2.1 Preprocessing Parity (P0)
- EXIF orientation handling (done).
- Rotation fallback retries for difficult camera input.
- Resolution normalization and scale strategy.
- Perspective/skew compensation baseline.

### 2.2 JNI Contract Parity (P0)
- Argument order and types verified against native expectations.
- Session insertion semantics verified (session count checks).
- Export fallback behavior verified when native return code is unreliable.

### 2.3 Export Parity (P1)
- Multiple export parameter attempts for compatibility.
- Validation that non-empty valid XML can be accepted when native return code is 0.

### 2.4 Musical Post-Processing Parity (P1)
- Rhythm normalization checks.
- Voice consistency checks.
- Measure consistency checks.

## 3) Dataset and Evaluation Discipline (P0)

### 3.1 Fixed Datasets
- Keep immutable benchmark datasets:
  - 36pages
  - turkish_march
  - camera-hard-set (real phone captures)

### 3.2 Reporting Outputs
- For every RC generate:
  - per-page metrics CSV
  - summary JSON
  - worst-page debug overlays

### 3.3 Regression Policy
- Any statistically meaningful regression requires fix or explicit waiver.
- Waivers must include risk notes and owner sign-off.

## 4) Security, Privacy, and Licensing (P0)

### 4.1 Data Handling
- No unexpected upload of user score images without explicit consent.
- Clear local vs remote processing behavior in settings.

### 4.2 Licensing
- Verify license obligations for all embedded native libs and model assets.
- If using AGPL components in product mode, define compliance path before launch.

### 4.3 Secrets and Endpoints
- No hardcoded credentials.
- Server URL handling must reject malformed unsafe endpoints.

## 5) Release Process (P0)

### 5.1 RC Checklist
- Build reproducibility confirmed.
- Automated evaluation complete and archived.
- Manual smoke tests complete for:
  - camera scan
  - photo import scan
  - playback start/stop/seek
  - settings engine switching

### 5.2 Rollback Readiness
- Last known good APK retained.
- Feature flags or safe fallback path documented.

### 5.3 Monitoring Readiness
- Error logs include enough context for root cause triage.
- Top failure codes tracked release-over-release.

## 6) 30-Day Execution Plan

### Week 1
- Freeze benchmark datasets and baselines.
- Add camera-hard-set dataset.
- Define numeric thresholds for P95 latency and regression tolerance.

### Week 2
- Complete preprocessing parity improvements (rotation and perspective hard cases).
- Add result-code-to-user-message mapping table.

### Week 3
- Add musical post-processing sanity checks.
- Improve export consistency and parser guardrails.

### Week 4
- Run full RC gate with metrics.
- Fix top 3 worst-page failure clusters.
- Ship only if all P0 gates pass.

## 7) Current Baseline (from evaluator)
- 36pages IoU: ~0.45
- turkish_march IoU: ~0.37

Target direction: improve both aggregate IoU and worst-page IoU while preserving runtime stability.
