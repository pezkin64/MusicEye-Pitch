#!/usr/bin/env python3
"""
Evaluate background/overlay pairs from Zemsky fixture assets.

Outputs:
- per_page_metrics.csv
- summary.json
- debug images for worst IoU pages
"""

from __future__ import annotations

import argparse
import csv
import json
import math
from dataclasses import dataclass
from pathlib import Path
from typing import Iterable

import numpy as np
from PIL import Image, ImageFilter


@dataclass
class Metrics:
    tp: int
    fp: int
    fn: int
    tn: int

    @property
    def precision(self) -> float:
        denom = self.tp + self.fp
        return self.tp / denom if denom else 0.0

    @property
    def recall(self) -> float:
        denom = self.tp + self.fn
        return self.tp / denom if denom else 0.0

    @property
    def f1(self) -> float:
        p = self.precision
        r = self.recall
        return (2 * p * r) / (p + r) if (p + r) else 0.0

    @property
    def iou(self) -> float:
        denom = self.tp + self.fp + self.fn
        return self.tp / denom if denom else 0.0


def otsu_threshold(gray: np.ndarray) -> int:
    hist = np.bincount(gray.ravel(), minlength=256).astype(np.float64)
    total = gray.size
    sum_total = np.dot(np.arange(256), hist)

    sum_bg = 0.0
    weight_bg = 0.0
    max_var = -1.0
    threshold = 127

    for t in range(256):
        weight_bg += hist[t]
        if weight_bg == 0:
            continue

        weight_fg = total - weight_bg
        if weight_fg == 0:
            break

        sum_bg += t * hist[t]
        mean_bg = sum_bg / weight_bg
        mean_fg = (sum_total - sum_bg) / weight_fg

        between = weight_bg * weight_fg * (mean_bg - mean_fg) ** 2
        if between > max_var:
            max_var = between
            threshold = t

    return int(threshold)


def preprocess_background(path: Path) -> np.ndarray:
    img = Image.open(path).convert("L")
    img = img.filter(ImageFilter.GaussianBlur(radius=0.8))
    arr = np.array(img, dtype=np.uint8)

    lo = np.percentile(arr, 1)
    hi = np.percentile(arr, 99)
    if hi > lo:
        arr = np.clip((arr.astype(np.float32) - lo) * 255.0 / (hi - lo), 0, 255).astype(np.uint8)

    thr = otsu_threshold(arr)
    # Sheet ink should generally be darker than paper.
    return arr <= thr


def overlay_to_mask(path: Path, mode: str) -> np.ndarray:
    arr = np.array(Image.open(path).convert("L"), dtype=np.uint8)

    if mode == "white":
        return arr >= 128
    if mode == "black":
        return arr < 128

    # auto: assume musical foreground is the minority class
    white = int((arr >= 128).sum())
    black = int((arr < 128).sum())
    return (arr >= 128) if white < black else (arr < 128)


def compute_metrics(pred_fg: np.ndarray, gt_fg: np.ndarray) -> Metrics:
    tp = int(np.logical_and(pred_fg, gt_fg).sum())
    fp = int(np.logical_and(pred_fg, np.logical_not(gt_fg)).sum())
    fn = int(np.logical_and(np.logical_not(pred_fg), gt_fg).sum())
    tn = int(np.logical_and(np.logical_not(pred_fg), np.logical_not(gt_fg)).sum())
    return Metrics(tp=tp, fp=fp, fn=fn, tn=tn)


def find_pairs(dataset_dir: Path) -> Iterable[tuple[Path, Path]]:
    backgrounds = sorted(dataset_dir.glob("background_*.jpeg"))
    for bg in backgrounds:
        suffix = bg.stem.split("background_")[-1]
        ov = dataset_dir / f"overlay_{suffix}.png"
        if ov.exists():
            yield bg, ov


def write_debug_image(bg_path: Path, pred_fg: np.ndarray, gt_fg: np.ndarray, out_path: Path) -> None:
    base = np.array(Image.open(bg_path).convert("RGB"), dtype=np.uint8)
    # TP green, FP red, FN blue
    tp = np.logical_and(pred_fg, gt_fg)
    fp = np.logical_and(pred_fg, np.logical_not(gt_fg))
    fn = np.logical_and(np.logical_not(pred_fg), gt_fg)

    base[tp] = [0, 220, 0]
    base[fp] = [240, 40, 40]
    base[fn] = [40, 80, 255]

    Image.fromarray(base).save(out_path)


def evaluate_dataset(dataset_dir: Path, overlay_mode: str, debug_count: int, out_root: Path) -> dict:
    pairs = list(find_pairs(dataset_dir))
    if not pairs:
        raise RuntimeError(f"No background/overlay pairs found in {dataset_dir}")

    out_root.mkdir(parents=True, exist_ok=True)
    debug_dir = out_root / "debug"
    debug_dir.mkdir(parents=True, exist_ok=True)

    rows = []
    totals = Metrics(tp=0, fp=0, fn=0, tn=0)

    for bg_path, ov_path in pairs:
        pred_fg = preprocess_background(bg_path)
        gt_fg = overlay_to_mask(ov_path, overlay_mode)

        if pred_fg.shape != gt_fg.shape:
            raise RuntimeError(f"Shape mismatch for {bg_path.name}: pred={pred_fg.shape}, gt={gt_fg.shape}")

        m = compute_metrics(pred_fg, gt_fg)
        totals.tp += m.tp
        totals.fp += m.fp
        totals.fn += m.fn
        totals.tn += m.tn

        rows.append(
            {
                "dataset": dataset_dir.name,
                "background": bg_path.name,
                "overlay": ov_path.name,
                "precision": m.precision,
                "recall": m.recall,
                "f1": m.f1,
                "iou": m.iou,
                "tp": m.tp,
                "fp": m.fp,
                "fn": m.fn,
                "tn": m.tn,
            }
        )

    rows_sorted = sorted(rows, key=lambda r: r["iou"])
    for row in rows_sorted[: max(0, debug_count)]:
        bg_path = dataset_dir / row["background"]
        ov_path = dataset_dir / row["overlay"]
        pred_fg = preprocess_background(bg_path)
        gt_fg = overlay_to_mask(ov_path, overlay_mode)
        write_debug_image(
            bg_path,
            pred_fg,
            gt_fg,
            debug_dir / f"{dataset_dir.name}_{bg_path.stem}_iou_{row['iou']:.3f}.png",
        )

    csv_path = out_root / f"{dataset_dir.name}_per_page_metrics.csv"
    with csv_path.open("w", newline="", encoding="utf-8") as f:
        writer = csv.DictWriter(
            f,
            fieldnames=[
                "dataset",
                "background",
                "overlay",
                "precision",
                "recall",
                "f1",
                "iou",
                "tp",
                "fp",
                "fn",
                "tn",
            ],
        )
        writer.writeheader()
        writer.writerows(rows)

    return {
        "dataset": dataset_dir.name,
        "pair_count": len(rows),
        "precision": totals.precision,
        "recall": totals.recall,
        "f1": totals.f1,
        "iou": totals.iou,
        "csv": str(csv_path),
        "debug_dir": str(debug_dir),
    }


def main() -> None:
    parser = argparse.ArgumentParser(description="Evaluate Zemsky fixture overlays against predicted foreground mask.")
    parser.add_argument(
        "--assets-root",
        type=Path,
        default=Path("ASSETS/base_unpacked/assets"),
        help="Root containing dataset folders like 36pages and turkish_march.",
    )
    parser.add_argument(
        "--datasets",
        nargs="*",
        default=["36pages", "turkish_march"],
        help="Dataset subfolders to evaluate.",
    )
    parser.add_argument(
        "--overlay-foreground",
        choices=["auto", "white", "black"],
        default="auto",
        help="How to interpret overlay foreground pixels.",
    )
    parser.add_argument(
        "--debug-count",
        type=int,
        default=5,
        help="How many worst pages (by IoU) to save as debug visuals.",
    )
    parser.add_argument(
        "--out-dir",
        type=Path,
        default=Path("ASSETS/eval_reports"),
        help="Directory for output reports.",
    )
    args = parser.parse_args()

    all_results = []
    for name in args.datasets:
        ds_dir = args.assets_root / name
        if not ds_dir.exists():
            raise RuntimeError(f"Dataset directory does not exist: {ds_dir}")
        result = evaluate_dataset(ds_dir, args.overlay_foreground, args.debug_count, args.out_dir)
        all_results.append(result)

    summary = {
        "assets_root": str(args.assets_root),
        "overlay_foreground": args.overlay_foreground,
        "datasets": all_results,
    }

    args.out_dir.mkdir(parents=True, exist_ok=True)
    summary_path = args.out_dir / "summary.json"
    summary_path.write_text(json.dumps(summary, indent=2), encoding="utf-8")

    print(json.dumps(summary, indent=2))
    print(f"\nSaved summary: {summary_path}")


if __name__ == "__main__":
    main()
