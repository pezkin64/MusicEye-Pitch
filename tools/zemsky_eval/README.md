Zemsky Fixture Evaluator

Purpose
- Evaluates background and overlay pairs from base.zip-style assets.
- Produces per-page metrics and summary outputs for quick regression checks.

Inputs
- Dataset folders containing matching files:
  - background_XXX.jpeg
  - overlay_XXX.png

Default input root
- ASSETS/base_unpacked/assets

Outputs
- ASSETS/eval_reports/summary.json
- ASSETS/eval_reports/36pages_per_page_metrics.csv
- ASSETS/eval_reports/turkish_march_per_page_metrics.csv
- ASSETS/eval_reports/debug/*.png (worst pages by IoU)

Install dependencies
- python3 -m pip install --user -r tools/zemsky_eval/requirements.txt

Run
- python3 tools/zemsky_eval/evaluate_masks.py --assets-root ASSETS/base_unpacked/assets --datasets 36pages turkish_march --out-dir ASSETS/eval_reports --debug-count 5

Tips
- If overlays use the opposite foreground polarity, rerun with:
  --overlay-foreground white
  or
  --overlay-foreground black

What metrics mean
- precision: how much predicted foreground is correct
- recall: how much ground-truth foreground was recovered
- f1: balance of precision/recall
- iou: overlap quality (primary ranking metric)
