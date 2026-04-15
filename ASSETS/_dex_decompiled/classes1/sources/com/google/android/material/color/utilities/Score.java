package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class Score {
    private static final double CUTOFF_CHROMA = 15.0d;
    private static final double CUTOFF_EXCITED_PROPORTION = 0.01d;
    private static final double CUTOFF_TONE = 10.0d;
    private static final double TARGET_CHROMA = 48.0d;
    private static final double WEIGHT_CHROMA_ABOVE = 0.3d;
    private static final double WEIGHT_CHROMA_BELOW = 0.1d;
    private static final double WEIGHT_PROPORTION = 0.7d;

    private Score() {
    }

    public static List<Integer> score(Map<Integer, Integer> map) {
        double d = 0.0d;
        for (Map.Entry<Integer, Integer> value : map.entrySet()) {
            d += (double) ((Integer) value.getValue()).intValue();
        }
        HashMap hashMap = new HashMap();
        double[] dArr = new double[361];
        for (Map.Entry next : map.entrySet()) {
            Integer num = (Integer) next.getKey();
            Cam16 fromInt = Cam16.fromInt(num.intValue());
            hashMap.put(num, fromInt);
            int round = (int) Math.round(fromInt.getHue());
            dArr[round] = dArr[round] + (((double) ((Integer) next.getValue()).intValue()) / d);
        }
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry : hashMap.entrySet()) {
            Integer num2 = (Integer) entry.getKey();
            num2.intValue();
            int round2 = (int) Math.round(((Cam16) entry.getValue()).getHue());
            double d2 = 0.0d;
            for (int i = round2 - 15; i < round2 + 15; i++) {
                d2 += dArr[MathUtils.sanitizeDegreesInt(i)];
            }
            hashMap2.put(num2, Double.valueOf(d2));
        }
        HashMap hashMap3 = new HashMap();
        for (Map.Entry entry2 : hashMap.entrySet()) {
            Integer num3 = (Integer) entry2.getKey();
            num3.intValue();
            Cam16 cam16 = (Cam16) entry2.getValue();
            hashMap3.put(num3, Double.valueOf((((Double) hashMap2.get(num3)).doubleValue() * 100.0d * WEIGHT_PROPORTION) + ((cam16.getChroma() - TARGET_CHROMA) * (cam16.getChroma() < TARGET_CHROMA ? WEIGHT_CHROMA_BELOW : WEIGHT_CHROMA_ABOVE))));
        }
        List<Integer> filter = filter(hashMap2, hashMap);
        HashMap hashMap4 = new HashMap();
        for (Integer next2 : filter) {
            next2.intValue();
            hashMap4.put(next2, (Double) hashMap3.get(next2));
        }
        ArrayList<Map.Entry> arrayList = new ArrayList<>(hashMap4.entrySet());
        Collections.sort(arrayList, new ScoredComparator());
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry3 : arrayList) {
            Integer num4 = (Integer) entry3.getKey();
            num4.intValue();
            Cam16 cam162 = (Cam16) hashMap.get(num4);
            Iterator it = arrayList2.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (MathUtils.differenceDegrees(cam162.getHue(), ((Cam16) hashMap.get((Integer) it.next())).getHue()) < CUTOFF_CHROMA) {
                        break;
                    }
                } else {
                    arrayList2.add((Integer) entry3.getKey());
                    break;
                }
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2.add(-12417548);
        }
        return arrayList2;
    }

    private static List<Integer> filter(Map<Integer, Double> map, Map<Integer, Cam16> map2) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map2.entrySet()) {
            Integer num = (Integer) next.getKey();
            int intValue = num.intValue();
            double doubleValue = map.get(num).doubleValue();
            if (((Cam16) next.getValue()).getChroma() >= CUTOFF_CHROMA && ColorUtils.lstarFromArgb(intValue) >= CUTOFF_TONE && doubleValue >= CUTOFF_EXCITED_PROPORTION) {
                arrayList.add(num);
            }
        }
        return arrayList;
    }

    static class ScoredComparator implements Comparator<Map.Entry<Integer, Double>> {
        public int compare(Map.Entry<Integer, Double> entry, Map.Entry<Integer, Double> entry2) {
            return -entry.getValue().compareTo(entry2.getValue());
        }
    }
}
